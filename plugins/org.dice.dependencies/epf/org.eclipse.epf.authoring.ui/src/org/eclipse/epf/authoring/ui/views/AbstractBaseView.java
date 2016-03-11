//------------------------------------------------------------------------------
// Copyright (c) 2005, 2006 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.authoring.ui.views;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.UIActionDispatcher;
import org.eclipse.epf.authoring.ui.actions.ILibraryActionBarContributor;
import org.eclipse.epf.authoring.ui.providers.IContentProviderFactory;
import org.eclipse.epf.library.ILibraryManager;
import org.eclipse.epf.library.ILibraryServiceListener;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.ui.LibraryUIManager;
import org.eclipse.epf.library.ui.LibraryUIUtil;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWindowListener;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.ide.IGotoMarker;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.PropertySheetPage;
import org.osgi.framework.Bundle;

/**
 * The abstract base class for all Method Library views.
 * 
 * @author Phong Nguyen Le
 * @author Shilpa Toraskar
 * @author Kelvin Low
 * @since 1.0
 */
public abstract class AbstractBaseView extends SaveableLibraryViewPart
		implements IEditingDomainProvider, ISelectionProvider,
		ILibraryServiceListener, IMenuListener, IViewerProvider, IGotoMarker {

	protected AdapterFactoryEditingDomain editingDomain;

	protected PropertySheetPage propertySheetPage;

	protected UIActionDispatcher actionDispatcher = null;

	protected AdapterFactory adapterFactory;

	protected ILibraryActionBarContributor actionBarContributor;

	protected ISelectionChangedListener selectionChangedListener;

	protected IPartListener partListener = new IPartListener() {
		public void partActivated(IWorkbenchPart part) {
			if (part == AbstractBaseView.this) {
				handleActivate(part);
			}
		}

		public void partBroughtToTop(IWorkbenchPart part) {
		}

		public void partClosed(IWorkbenchPart part) {
		}

		public void partDeactivated(IWorkbenchPart part) {
		}

		public void partOpened(IWorkbenchPart part) {
		}
	};
		
	/**
	 * Handles the activation of this view.
	 */
	protected void handleActivate(IWorkbenchPart part) {
		// Set the right adapter factory for the editing domain.
		editingDomain.setAdapterFactory(adapterFactory);

		// Recompute the read only state.
		if (editingDomain.getResourceToReadOnlyMap() != null) {
			editingDomain.getResourceToReadOnlyMap().clear();

			// Refresh any actions that may become enabled or disabled.
			setSelection(getSelection());
		}
	}

	// this window listener is for opening the library when the 
	// view first becomes visible
	private IWindowListener windowListener = new IWindowListener() {

		public void windowActivated(IWorkbenchWindow window) {
		}

		public void windowClosed(IWorkbenchWindow window) {
		}

		public void windowDeactivated(IWorkbenchWindow window) {
		}

		public void windowOpened(IWorkbenchWindow window) {
			window.getWorkbench().removeWindowListener(this);
			boolean isVisible = getSite().getPage().isPartVisible(AbstractBaseView.this);
			if (isVisible) {
				LibraryUIManager.getInstance().startupOpenLibrary();
			} else {
				LibraryUIManager.getInstance().addMethodViewPartListener(getViewId());
			}
		}
	};

	private IContentProviderFactory contentProviderFactory;

	/**
	 * Displays a dialog that asks if conflicting changes should be discarded.
	 */
	protected boolean handleDirtyConflict() {
		String title = AuthoringUIResources._UI_FileConflict_label; 
		String msg = AuthoringUIResources._WARN_FileConflict; 
		return AuthoringUIPlugin.getDefault().getMsgDialog().displayPrompt(
				title, msg);
	}

	/**
	 * Handles changed resources when this view is activated.
	 */
	protected void handleChangedResources() {
	}

	/**
	 * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(Composite)
	 */
	public void createPartControl(Composite parent) {
		init();

		actionDispatcher = new UIActionDispatcher(this);

		// Create the viewer.
		createViewer(parent);

		// Create and initialize the action bar contributor.
		try {
			actionBarContributor = createActionBarContributor();
			actionBarContributor.init(getViewSite().getActionBars(), getSite()
					.getPage());
		} catch (Exception e) {
			AuthoringUIPlugin.getDefault().getLogger().logError(e);
		}

		// Add a selection change listener for the viewer.
		Viewer viewer = getViewer();
		if (viewer != null) {
			if (selectionChangedListener == null) {
				selectionChangedListener = new ISelectionChangedListener() {
					public void selectionChanged(
							SelectionChangedEvent selectionChangedEvent) {
						setSelection(selectionChangedEvent.getSelection());
					}
				};
			}
			viewer.addSelectionChangedListener(selectionChangedListener);
		}

		// Add a library service listener.
		LibraryService.getInstance().addListener(this);

		Object library = LibraryService.getInstance().getCurrentMethodLibrary();
		if (library != null) {
			setInputForViewer(library);
			LibraryUIUtil.updateShellTitle();
		}
		IWorkbench workbench = AuthoringUIPlugin.getDefault().getWorkbench();
		if (workbench != null) {
			workbench.addWindowListener(windowListener);
		}
	}

	private void init() {
		ILibraryManager manager = (ILibraryManager) LibraryService
				.getInstance().getCurrentLibraryManager();
		if (manager != null) {
			editingDomain = manager.getEditingDomain();
			adapterFactory = (ComposedAdapterFactory) editingDomain
					.getAdapterFactory();
		}

		IViewSite site = (IViewSite) this.getSite();
		site.setSelectionProvider(this);
		site.getPage().addPartListener(partListener);
	}

	/**
	 * creates a context menu for the viewer
	 * @param viewer
	 */
	public void createContextMenuFor(final StructuredViewer viewer) {
		MenuManager contextMenu = new MenuManager("#PopUp"); //$NON-NLS-1$
		contextMenu.add(new Separator("additions")); //$NON-NLS-1$
		contextMenu.setRemoveAllWhenShown(true);
		contextMenu.addMenuListener(this);
		Menu menu = contextMenu.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(contextMenu, viewer);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.action.IMenuListener#menuAboutToShow(org.eclipse.jface.action.IMenuManager)
	 */
	public void menuAboutToShow(IMenuManager menuManager) {
		try {
			actionBarContributor.setActiveView(this);
			actionBarContributor.menuAboutToShow(menuManager);
		} catch (RuntimeException e) {
			AuthoringUIPlugin.getDefault().getLogger().logError(e);
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	public void setFocus() {
	}

	/**
	 * @see org.eclipse.emf.edit.domain.IEditingDomainProvider#getEditingDomain()
	 */
	public EditingDomain getEditingDomain() {
		return editingDomain;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ISelectionProvider#addSelectionChangedListener(org.eclipse.jface.viewers.ISelectionChangedListener)
	 */
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		actionDispatcher.addSelectionChangedListener(listener);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ISelectionProvider#removeSelectionChangedListener(org.eclipse.jface.viewers.ISelectionChangedListener)
	 */
	public void removeSelectionChangedListener(
			ISelectionChangedListener listener) {
		actionDispatcher.removeSelectionChangedListener(listener);
	}

	/**
	 * @see org.eclipse.ui.part.WorkbenchPart#getAdapter(Class)
	 */
	public Object getAdapter(Class key) {
		if (key.equals(IGotoMarker.class)) {
			return this;
		} else {
			return super.getAdapter(key);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ISelectionProvider#getSelection()
	 */
	public ISelection getSelection() {
		return actionDispatcher.getSelection();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ISelectionProvider#setSelection(org.eclipse.jface.viewers.ISelection)
	 */
	public void setSelection(ISelection selection) {
		setSelection(this, selection);
	}

	private void setSelection(ISelectionProvider source, ISelection selection) {
		actionDispatcher.setSelection(source, selection);
		UIActionDispatcher.getInstance().setSelection(source, selection);
	}

	/**
	 * @see org.eclipse.epf.library.ILibraryServiceListener#libraryCreated(MethodLibrary)
	 */
	public void libraryCreated(MethodLibrary library) {
	}

	/**
	 * @see org.eclipse.epf.library.ILibraryServiceListener#libraryOpened(MethodLibrary)
	 */
	public void libraryOpened(MethodLibrary library) {
	}

	/**
	 * @see org.eclipse.epf.library.ILibraryServiceListener#libraryReopened(MethodLibrary)
	 */
	public void libraryReopened(MethodLibrary library) {
	}

	/**
	 * @see org.eclipse.epf.library.ILibraryServiceListener#libraryClosed(MethodLibrary)
	 */
	public void libraryClosed(MethodLibrary library) {
		setInputForViewer(null);
		if (adapterFactory instanceof ComposedAdapterFactory) {
			((ComposedAdapterFactory) adapterFactory).dispose();
		}
	}

	/**
	 * @see org.eclipse.epf.library.ILibraryServiceListener#librarySet(MethodLibrary)
	 */
	public void librarySet(MethodLibrary library) {
		setInputForViewer(library);
		if (library != null) {
			actionBarContributor.setActiveView(AbstractBaseView.this);
		}
	}

	/**
	 * @see org.eclipse.epf.library.ILibraryServiceListener#configurationSet(MethodConfiguration)
	 */
	public void configurationSet(MethodConfiguration config) {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#dispose()
	 */
	public void dispose() {
		// ResourcesPlugin.getWorkspace().removeResourceChangeListener(
		// resourceChangeListener);

		getSite().getPage().removePartListener(partListener);

		if (adapterFactory instanceof ComposedAdapterFactory) {
			((ComposedAdapterFactory) adapterFactory).dispose();
		}

		if (actionBarContributor.getActiveView() == this) {
			actionBarContributor.setActiveView(null);
		}

		if (propertySheetPage != null) {
			propertySheetPage.dispose();
		}

		IWorkbench workbench = AuthoringUIPlugin.getDefault().getWorkbench();
		if (workbench != null) {
			workbench.removeWindowListener(windowListener);
		}

		LibraryService.getInstance().removeListener(this);

		super.dispose();
	}

	/**
	 * This accesses a cached version of the property sheet.
	 */
	public IPropertySheetPage getPropertySheetPage() {
		if (propertySheetPage == null) {
			propertySheetPage = new PropertySheetPage() {
				public void makeContributions(IMenuManager menuManager,
						IToolBarManager toolBarManager,
						IStatusLineManager statusLineManager) {
					super.makeContributions(menuManager, toolBarManager,
							statusLineManager);
				}

				public void setActionBars(IActionBars actionBars) {
					super.setActionBars(actionBars);
					if (actionBarContributor != null) {
						actionBarContributor.shareGlobalActions(this,
								actionBars);
					}
				}
			};
			propertySheetPage
					.setPropertySourceProvider(new AdapterFactoryContentProvider(
							adapterFactory));
		}
		return propertySheetPage;
	}

	/**
	 * @see org.eclipse.ui.ide.IGotoMarker#gotoMarker(IMarker)
	 */
	public void gotoMarker(IMarker marker) {
		try {
			if (marker.getType().equals(EValidator.MARKER)) {
				String uriAttribute = marker.getAttribute(
						EValidator.URI_ATTRIBUTE, null);
				if (uriAttribute != null) {
					URI uri = URI.createURI(uriAttribute);
					EObject eObject = editingDomain.getResourceSet()
							.getEObject(uri, true);
					if (eObject != null) {
						setSelectionToViewer(Collections
								.singleton(editingDomain.getWrapper(eObject)));
					}
				}
			}
		} catch (CoreException e) {
			AuthoringUIPlugin.getDefault().getLogger().logError(e);
		}
	}

	/**
	 * This sets the selection into whichever viewer is active.
	 */
	public void setSelectionToViewer(Collection collection) {
		final Collection theSelection = collection;
		if (theSelection != null && !theSelection.isEmpty()) {
			Runnable runnable = new Runnable() {
				public void run() {
					// Try to select the items in the current content viewer of
					// the editor.
					Viewer viewer = getViewer();
					if (viewer != null) {
						viewer.setSelection(new StructuredSelection(
								theSelection.toArray()), true);
					}
				}
			};
			runnable.run();
		}
	}

	protected IContentProviderFactory getContentProviderFactory() {
		if(contentProviderFactory == null) {
			// Process the contributors.
			//
			IExtensionRegistry extensionRegistry = Platform
					.getExtensionRegistry();
			IExtensionPoint extensionPoint = extensionRegistry
					.getExtensionPoint(AuthoringUIPlugin.getDefault().getId(),
							"contentProviderFactories"); //$NON-NLS-1$
			if (extensionPoint != null) {
				IExtension[] extensions = extensionPoint.getExtensions();
				Object ext = null;
				ext_walk:
				for (int i = 0; i < extensions.length; i++) {
					IExtension extension = extensions[i];
					String pluginId = extension.getNamespaceIdentifier();
					Bundle bundle = Platform.getBundle(pluginId);
					IConfigurationElement[] configElements = extension
							.getConfigurationElements();
					for (int j = 0; j < configElements.length; j++) {
						IConfigurationElement configElement = configElements[j];
						try {
							String viewId = configElement.getAttribute("view"); //$NON-NLS-1$
							if (getViewId().equals(viewId)) {
								String className = configElement
										.getAttribute("class"); //$NON-NLS-1$
								if (className != null) {
									ext = bundle.loadClass(className)
											.newInstance();
									if (ext instanceof IContentProviderFactory) {
										contentProviderFactory = (IContentProviderFactory) ext;
										break ext_walk;
									}
								}
							}
						} catch (Exception e) {
							AuthoringUIPlugin.getDefault().getLogger()
									.logError(e);
						}
					}
				}
			}
		}
		
		return contentProviderFactory;		
	}

	/**
	 * @return a new AdapterFactoryContentProvider
	 */
	protected AdapterFactoryContentProvider createContentProvider() {
		IContentProviderFactory factory = getContentProviderFactory();		
		AdapterFactoryContentProvider cp = null;
		if(factory != null) {
			IContentProvider contentProvider = factory.createProvider(adapterFactory, this);
			if(contentProvider instanceof AdapterFactoryContentProvider) {
				cp = (AdapterFactoryContentProvider) contentProvider;
			}
		}
		return cp != null ? cp : new AdapterFactoryContentProvider(adapterFactory);
	}

	/**
	 * @return the viewer
	 */
	public abstract Viewer getViewer();

	/**
	 * Creates the viewer in the given parent
	 * @param parent
	 */
	public abstract void createViewer(Composite parent);

	/**
	 * @return a new actionBarContributer
	 */
	public abstract ILibraryActionBarContributor createActionBarContributor();

	/**
	 * Sets the input for the viewer
	 * @param model new input for the viewer
	 */
	public abstract void setInputForViewer(Object model);

	public abstract String getViewId();
	
}
