//------------------------------------------------------------------------------
// Copyright (c) 2005, 2007 IBM Corporation and others.
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
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.IdentityCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.ui.action.CopyAction;
import org.eclipse.emf.edit.ui.dnd.LocalTransfer;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.epf.authoring.ui.AuthoringPerspective;
import org.eclipse.epf.authoring.ui.AuthoringUIExtensionManager;
import org.eclipse.epf.authoring.ui.AuthoringUIHelpContexts;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.BrowsingPerspective;
import org.eclipse.epf.authoring.ui.PerspectiveListUtil;
import org.eclipse.epf.authoring.ui.UIActionDispatcher;
import org.eclipse.epf.authoring.ui.actions.ConfigurationViewEditAction;
import org.eclipse.epf.authoring.ui.actions.ILibraryActionBarContributor;
import org.eclipse.epf.authoring.ui.actions.IMenuAction;
import org.eclipse.epf.authoring.ui.actions.LibraryActionBarContributor;
import org.eclipse.epf.authoring.ui.actions.LibraryViewCopyAction;
import org.eclipse.epf.authoring.ui.actions.LibraryViewFindElementAction;
import org.eclipse.epf.authoring.ui.dnd.LibraryViewerDragAdapter;
import org.eclipse.epf.authoring.ui.providers.ConfigurationDecoratingLabelProvider;
import org.eclipse.epf.authoring.ui.providers.IContentProviderFactory;
import org.eclipse.epf.common.ui.util.ClipboardUtil;
import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.library.ILibraryManager;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.LibraryServiceUtil;
import org.eclipse.epf.library.configuration.ConfigurationHelper;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.util.ExtensionManager;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.events.ILibraryChangeListener;
import org.eclipse.epf.library.ui.LibraryUIManager;
import org.eclipse.epf.library.ui.actions.ConfigurationContributionItem;
import org.eclipse.epf.library.util.ResourceHelper;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.HTMLTransfer;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IPerspectiveListener;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;

/**
 * Displays the content of the current method configuration.
 * <p>
 * The current configuration is set via the Configuration combobox in the system
 * toolbar.
 * 
 * @author Jinhua Xi
 * @author Phong Nguyen Le
 * @author Kelvin Low
 * @since 1.0
 */
public class ConfigurationView extends AbstractBaseView implements
		ILibraryChangeListener {

	/**
	 * The view ID.
	 */
	public static final String VIEW_ID = ConfigurationView.class.getName();

	private String configName;

	private TreeViewer treeViewer;

	private IDoubleClickListener doubleClickListener;

	private IFilter configFilter;

	private List<Action> menuActionProviders;
	
	private String refreshedPersID;

	/**
	 * Creates a new instance.
	 */
	public ConfigurationView() {
		extender = AuthoringUIExtensionManager.getInstance()
				.createConfigurationViewExtender(this);
		
		IWorkbenchWindow window = AuthoringUIPlugin.getDefault().getWorkbench()
				.getActiveWorkbenchWindow();
		if (window != null) {
			window.addPerspectiveListener(new IPerspectiveListener() {
				public void perspectiveActivated(IWorkbenchPage page,
						IPerspectiveDescriptor desc) {
				}

				public void perspectiveChanged(IWorkbenchPage page,
						IPerspectiveDescriptor desc, String id) {
					String newID = desc.getId();
					if (AuthoringPerspective.PERSPECTIVE_ID.equals(newID)
							|| BrowsingPerspective.PERSPECTIVE_ID.equals(newID)) {
						if (! newID.equals(refreshedPersID)) {
							refreshedPersID = newID;
							refresh();
						}
					}

				}
			});
		}
	}

	/**
	 * @see org.eclipse.ui.part.ViewPart#init(IViewSite)
	 */
	public void init(IViewSite site) throws PartInitException {
		super.init(site);
		createActions(site);
		LibraryUIManager.getInstance();
	}

	private IAction collapseViewAction;
	public IAction getCollapseViewAction() {
		if (collapseViewAction == null) {
			collapseViewAction = new Action() {
				public void run() {
					PlatformUI.getWorkbench().getDisplay().syncExec(
							new Runnable() {
								public void run() {
									if (getViewer() instanceof TreeViewer) {
										((TreeViewer) getViewer())
												.collapseAll();
									}
								}
							});
				}
			};
		}
		return collapseViewAction;
	}
	
	/**
	 * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(Composite)
	 */
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);

		PlatformUI.getWorkbench().getHelpSystem().setHelp(parent,
				AuthoringUIHelpContexts.CONFIGURATION_VIEW_CONTEXT);

//		editingDomain = new AdapterFactoryEditingDomain(adapterFactory,
//				new BasicCommandStack());				
		editingDomain = newEditingDomain();
		
		adapterFactory = (ComposedAdapterFactory) editingDomain
				.getAdapterFactory();

		// Open the current configuration.
		setConfiguration(null);
		
		loadMenuActionProviders();
		IToolBarManager mgr= getViewSite().getActionBars().getToolBarManager();

		for (int i = 0; i < menuActionProviders.size(); i++) {
			try {
				Object actionProvider  = menuActionProviders.get(i);
				if (actionProvider instanceof Action) {
					mgr.add((Action)actionProvider);
					if (actionProvider instanceof IMenuAction)
						((IMenuAction) actionProvider).init(this);
				}
				
			} catch (Exception e) {
				AuthoringUIPlugin.getDefault().getLogger().logError(e);
			}
		}
	}
	
	protected AdapterFactoryEditingDomain newEditingDomain() {
		return new AdapterFactoryEditingDomain(adapterFactory,
				new BasicCommandStack());
	}
	
	/** 
	 * Get all menu providers
	 * @return
	 */
	private List<Action> loadMenuActionProviders() {
		if (menuActionProviders == null) {
			menuActionProviders = ExtensionManager.getExtensions(AuthoringUIPlugin.getDefault().getId(), "configurationViewMenuProvider", Action.class); //$NON-NLS-1$	
		}
		return menuActionProviders;
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
		MethodConfiguration config = LibraryService.getInstance().getCurrentMethodConfiguration();
		if(config != null && config.eContainer() != library) {
			setMethodConfiguration(null);
		}
		// Add a library change listener.
		ILibraryManager manager = LibraryService.getInstance()
				.getCurrentLibraryManager();
		if (manager != null) {
			manager.addListener(this);
		}
	}

	/**
	 * @see org.eclipse.epf.library.events.ILibraryChangeListener#libraryChanged(int,
	 *      Collection)
	 */
	public void libraryChanged(int option, Collection changedItems) {
		switch (option) {
		case ILibraryChangeListener.OPTION_DELETED:
			if (changedItems != null) {
				for (Iterator it = changedItems.iterator(); it.hasNext();) {
					Object element = it.next();
					if (element instanceof MethodConfiguration) {
						if (((MethodConfiguration) element).getName().equals(
								getCurrentConfig())) {
							setMethodConfiguration(null);
						}
					}
				}
			}
			break;                                                          
			// Bugzilla defect 164739                                         
		case ILibraryChangeListener.OPTION_CHANGED:                       
			if (changedItems != null) {                                     
				for (Iterator it = changedItems.iterator(); it.hasNext();) {  
					Object element = it.next();                                 
					if (element instanceof MethodConfiguration) {               
						if (((MethodConfiguration) element)                       
								.equals(LibraryService.getInstance()                  
										.getCurrentMethodConfiguration())) {              
							setContentDescription(((MethodConfiguration) element)   
									.getName());                                        
						}                                                         
					}                                                           
				}                                                             
			}                                                               
			break;                                                          
		}
	}

	/**
	 * @see org.eclipse.epf.library.ILibraryServiceListener#configurationSet(MethodConfiguration)
	 */
	public void configurationSet(MethodConfiguration config) {
		setMethodConfiguration(config);
		ConfigurationContributionItem configCombo = LibraryUIManager.getInstance().getConfigCombo();
		if (configCombo != null) {
			configCombo.setCollapseConfigViewAction(getCollapseViewAction());
		}
	}

	/**
	 * @return the name of the currently viewed configuration
	 */
	public String getCurrentConfig() {
		return configName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.authoring.ui.views.AbstractBaseView#setFocus()
	 */
	public void setFocus() {
		actionBarContributor.setActiveView(this);

		IStructuredSelection selected = (IStructuredSelection) getSelection();
		if (selected.isEmpty())
			actionBarContributor.disableGlobalEditMenu();
		else
			actionBarContributor.enableGlobalEditMenu();

		if (treeViewer != null) {
			treeViewer.getControl().setFocus();
		}
	}

	private boolean firstButtonClicked = true;
	public boolean isFirstButtonClicked() {
		return firstButtonClicked;
	}

	private void setFirstButtonClicked(boolean firstButtonClicked) {
		this.firstButtonClicked = firstButtonClicked;
	}

	/**
	 * Creates the viewer.
	 */
	public void createViewer(Composite parent) {
		// Create the controls.
		Composite content = new Composite(parent, SWT.NONE);
		content.setLayoutData(new GridData(GridData.FILL_BOTH));
		GridLayout layout = new GridLayout();
		content.setLayout(layout);

		treeViewer = new TreeViewer(content) {
			protected void hookControl(Control control) {
				super.hookControl(control);
				getTree().addMouseListener(new MouseAdapter() {
					public void mouseDown(MouseEvent e) {
						boolean b = e != null && e.button == 1;
						setFirstButtonClicked(b);
					}
				});
			}
		};
		
		treeViewer.setUseHashlookup(true);
		treeViewer.getTree().setLayoutData(new GridData(GridData.FILL_BOTH));

		// Add drag-and-drop support.
		int dndOperations = DND.DROP_COPY | DND.DROP_MOVE ;
		Transfer[] transfers = new Transfer[] { HTMLTransfer.getInstance(), TextTransfer.getInstance(),
				LocalTransfer.getInstance() };
		treeViewer.addDragSupport(dndOperations, transfers,
				new LibraryViewerDragAdapter(treeViewer));

		// Add a double click listener.
		doubleClickListener = new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				UIActionDispatcher.getInstance().handleDoubleClickEvent(event);
			}
		};
		treeViewer.addDoubleClickListener(doubleClickListener);

		createContextMenuFor(treeViewer);
	}

	/**
	 * Creates the actions.
	 */
	public void createActions(IViewSite viewSite) {
		IAction editAction = new Action(AuthoringUIResources.actionLabel_edit) {
			public void run() {
				UIActionDispatcher.editConfiguration(configName);
			}
		};
		editAction
				.setToolTipText(AuthoringUIResources.editConfiguration_tooltip);
	}

	private void reset() {
		// Prevent memory leak.
		configName = null;
		if (adapterFactory != null) {
			if (adapterFactory instanceof IDisposable) {
				((IDisposable) adapterFactory).dispose();
			}
			adapterFactory = null;
		}
		editingDomain.setAdapterFactory(null);
		IContentProvider contentProvider = treeViewer.getContentProvider();
		if (contentProvider != null) {
			contentProvider.dispose();
		}
		IBaseLabelProvider labelProvider = treeViewer.getLabelProvider();
		if (labelProvider != null) {
			labelProvider.dispose();
		}
		StructuredSelection emptySelection = new StructuredSelection();
		actionDispatcher.setSelection(emptySelection);
		UIActionDispatcher.getInstance().setSelection(emptySelection);
	}
	
	private IFilter createFilter(MethodConfiguration config) {
		IContentProviderFactory cpFactory = getContentProviderFactory();
		IFilter filter = null;
		if(cpFactory != null) {
			filter = cpFactory.createFilter(config, treeViewer);
		}
		return filter != null ? filter : new ConfigurationViewFilter(config, treeViewer);
	}
	
	/**
	 * Sets the given Method Configuration as this viewer's input
	 * 
	 * @param config
	 */
	public void setMethodConfiguration(MethodConfiguration config) {
		Control ctrl = this.getViewer().getControl();
		if (ctrl == null || ctrl.isDisposed()) {
			return;
		}

		if (treeViewer.getInput() == config)
			return;
		String title = ""; //$NON-NLS-1$

		if (config != null) {
			this.configName = config.getName();
			configFilter = createFilter(config);
			adapterFactory = TngAdapterFactory.INSTANCE
					.getConfigurationView_AdapterFactory(configFilter);
			editingDomain.setAdapterFactory(adapterFactory);
			AdapterFactoryContentProvider contentProvider = (AdapterFactoryContentProvider) treeViewer.getContentProvider();			
			if(contentProvider == null) {
				contentProvider = createContentProvider();
			}
			else {
				contentProvider.setAdapterFactory(adapterFactory);
			}
			treeViewer.setContentProvider(contentProvider);
			treeViewer.setLabelProvider(new ConfigurationDecoratingLabelProvider(config,
					adapterFactory));
			title = config.getName();
		} else {
			// Prevent memory leak.
			configName = null;
			if (adapterFactory != null) {
				if (adapterFactory instanceof IDisposable) {
					((IDisposable) adapterFactory).dispose();
				}
				adapterFactory = null;
			}
			editingDomain.setAdapterFactory(null);
			IContentProvider contentProvider = treeViewer.getContentProvider();
			if (contentProvider != null) {
				contentProvider.dispose();
			}
			IBaseLabelProvider labelProvider = treeViewer.getLabelProvider();
			if (labelProvider != null) {
				labelProvider.dispose();
			}
			StructuredSelection emptySelection = new StructuredSelection();
			actionDispatcher.setSelection(emptySelection);
			UIActionDispatcher.getInstance().setSelection(emptySelection);
		}

		treeViewer.setInput(config);
		setContentDescription(title);
	}

	/**
	 * Sets this viewer's input to the Method Configuration with the given name
	 * 
	 * @param name
	 */
	public void setConfiguration(String name) {
		configName = name;

		if (name == null) {
			MethodConfiguration config = LibraryService.getInstance()
					.getCurrentMethodConfiguration();
			if (config != null) {
				configName = config.getName();
			}
		}

		if (configName != null) {
			MethodConfiguration config = LibraryServiceUtil
					.getMethodConfiguration(LibraryService.getInstance()
							.getCurrentMethodLibrary(), configName);
			if (config != null) {
				setMethodConfiguration(config);
				return;
			}
		}

		this.configName = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.authoring.ui.views.AbstractBaseView#dispose()
	 */
	public void dispose() {
		super.dispose();
		if (doubleClickListener != null) {
			treeViewer.removeDoubleClickListener(doubleClickListener);
		}
	}

	/**
	 * @return the ConfigurationView object
	 */
	public static ConfigurationView getView() {
//		try {
//			IWorkbenchPage activePage = PlatformUI.getWorkbench()
//					.getActiveWorkbenchWindow().getActivePage();
//			if (activePage != null) {
//				IViewPart view = activePage.findView(VIEW_ID);
//				if (view == null) {
//					view = activePage.showView(VIEW_ID);
//				}
//				return (ConfigurationView) view;
//			}
//
//		} catch (Exception e) {
//			AuthoringUIPlugin.getDefault().getLogger().logError(e);
//		}
//
//		return null;
		
		boolean show = ViewHelper.isViewInCurrentPerspective(VIEW_ID);
		return (ConfigurationView)ViewHelper.findView(VIEW_ID, show);
	}

	public static ConfigurationView getView(String viewId) {
		boolean show = ViewHelper.isViewInCurrentPerspective(viewId);
		return (ConfigurationView)ViewHelper.findView(viewId, show);
	}
	
	/**
	 * @see org.eclipse.epf.authoring.ui.views.AbstractBaseView#getViewer()
	 */
	public Viewer getViewer() {
		return treeViewer;
	}

	private class ConfigurationViewActionBarContributor extends
			LibraryActionBarContributor {

		private LibraryViewFindElementAction libraryViewFindElementAction;

		public ConfigurationViewActionBarContributor(EditingDomain editingDomain) {
			super(editingDomain);
			
			// don't show validate action
			//
			validateAction = null;
		}

		/**
		 * @see org.eclipse.epf.authoring.ui.actions.LibraryActionBarContributor#init(IActionBars)
		 */
		public void init(IActionBars actionBars) {
			ISharedImages sharedImages = PlatformUI.getWorkbench()
					.getSharedImages();
			copyAction = createCopyAction();
			copyAction.setImageDescriptor(sharedImages
					.getImageDescriptor(ISharedImages.IMG_TOOL_COPY));
			actionBars.setGlobalActionHandler(ActionFactory.COPY.getId(),
					copyAction);

			viewActionBars = actionBars;
			configViewEditAction = new ConfigurationViewEditAction(
					AuthoringUIResources.actionLabel_edit);
			actionBars.setGlobalActionHandler(
					"org.eclipse.epf.authoring.ui.actions.openElement", //$NON-NLS-1$
					configViewEditAction);
			libraryViewFindElementAction = new LibraryViewFindElementAction(
					AuthoringUIResources.actionLabel_findElementInLibNav);
			actionBars.setGlobalActionHandler(
					LibraryViewFindElementAction.ACTION_ID,
					libraryViewFindElementAction);
		}

		/**
		 * @see org.eclipse.epf.authoring.ui.actions.LibraryActionBarContributor#activate()
		 */
		public void activate() {
			activeViewPart.addPropertyListener(this);
			ISelectionProvider selectionProvider = activeViewPart instanceof ISelectionProvider ? (ISelectionProvider) activeViewPart
					: activeViewPart.getSite().getSelectionProvider();
			selectionProvider.addSelectionChangedListener(configViewEditAction);
			selectionProvider
					.addSelectionChangedListener(libraryViewFindElementAction);
			copyAction.setEditingDomain(editingDomain);
			selectionProvider.addSelectionChangedListener(copyAction);
			update();
		}

		/**
		 * @see LibraryActionBarContributor#deactivate()
		 */
		public void deactivate() {
			activeViewPart.removePropertyListener(this);
			ISelectionProvider selectionProvider = activeViewPart instanceof ISelectionProvider ? (ISelectionProvider) activeViewPart
					: activeViewPart.getSite().getSelectionProvider();
			selectionProvider
					.removeSelectionChangedListener(configViewEditAction);
			selectionProvider
					.removeSelectionChangedListener(libraryViewFindElementAction);
			copyAction.setEditingDomain(null);
			selectionProvider.removeSelectionChangedListener(copyAction);
		}

		/**
		 * @see LibraryActionBarContributor#update()
		 */
		public void update() {
			ISelectionProvider selectionProvider = activeViewPart instanceof ISelectionProvider ? (ISelectionProvider) activeViewPart
					: activeViewPart.getSite().getSelectionProvider();
			ISelection selection = selectionProvider.getSelection();
			IStructuredSelection structuredSelection = selection instanceof IStructuredSelection ? (IStructuredSelection) selection
					: StructuredSelection.EMPTY;
			configViewEditAction.selectionChanged(structuredSelection);
			libraryViewFindElementAction.selectionChanged(structuredSelection);
			copyAction.updateSelection(structuredSelection);
		}

		/**
		 * @see LibraryActionBarContributor#selectionChanged(SelectionChangedEvent)
		 */
		public void selectionChanged(SelectionChangedEvent event) {
		}

		/**
		 * @see LibraryActionBarContributor#disableGlobalEditMenu()
		 */
		public void disableGlobalEditMenu() {
			configViewEditAction.setEnabled(false);
		}

		/**
		 * @see LibraryActionBarContributor#enableGlobalEditMenu()
		 */
		public void enableGlobalEditMenu() {
			configViewEditAction.setEnabled(true);
		}

		/**
		 * @see LibraryActionBarContributor#menuAboutToShow(IMenuManager)
		 */
		public void menuAboutToShow(IMenuManager menuManager) {
			menuAboutToShow_(menuManager);
			getExtender().getActionBarExtender().menuAboutToShow(menuManager);
		}
		
		private void menuAboutToShow_(IMenuManager menuManager) {
			// Add our standard markers.
			menuManager.add(new Separator("additions")); //$NON-NLS-1$
			menuManager.add(new Separator("edit")); //$NON-NLS-1$

			// Add our other standard marker.
			menuManager.add(new Separator("additions-end")); //$NON-NLS-1$

			addGlobalActions(menuManager);

			menuManager.add(new Separator());
			menuManager.add(new ActionContributionItem(configViewEditAction));
			if (PerspectiveListUtil.isAuthoringPerspective()) {
				menuManager.add(new ActionContributionItem(
						libraryViewFindElementAction));
			}
		}

		/**
		 * @see org.eclipse.epf.authoring.ui.actions.LibraryActionBarContributor#createCopyAction()
		 */
		protected CopyAction createCopyAction() {
			return new LibraryViewCopyAction() {
				@Override
				public Command createCommand(Collection selection) {
					return new IdentityCommand(selection);
				}

				@Override
				public void run() {
					String links = ""; //$NON-NLS-1$
					for (Iterator iter = ((IdentityCommand) command)
							.getResult().iterator(); iter.hasNext();) {
						Object item = TngUtil.unwrap(iter.next());
						if (item instanceof MethodElement) {
							MethodElement e = (MethodElement) item;
							String href = ResourceHelper
									.getUrl(e, null, "html"); //$NON-NLS-1$
							if (links.length() > 0) {
								links += StrUtil.LINE_FEED;
							}
							links += ResourceHelper.getElementLink(e, true,
									"file://" + href); //$NON-NLS-1$
						}
					}
					if (links.length() > 0) {
						ClipboardUtil.copyTextHTMLToClipboard(links);
					}
				}
			};
		}

		@Override
		protected void refreshViewer(Viewer viewer) {
			refresh();
		}
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.views.AbstractBaseView#createActionBarContributor()
	 */
	public ILibraryActionBarContributor createActionBarContributor() {
		return new ConfigurationViewActionBarContributor(editingDomain);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.views.AbstractBaseView#setInputForViewer(Object)
	 */
	public void setInputForViewer(Object model) {
		if ((model == null) || (model instanceof MethodConfiguration)) {
			setMethodConfiguration((MethodConfiguration) model);
		} else {
			reset();
		}
	}
	
	public void refresh() {
		ConfigurationHelper.getDelegate().configViewRefreshNotified();
		
		if(configFilter instanceof ConfigurationViewFilter) {
			((ConfigurationViewFilter)configFilter).refreshViewer();
		}
		else {
			getViewer().refresh();
		}
	}

	@Override
	public String getViewId() {
		return VIEW_ID;
	}
	
	private ConfigurationViewExtender extender;
	protected ConfigurationViewExtender getExtender() {
		return extender;
	}
	
	@Override
	public void setSelection(ISelection selection) {
		getExtender().getActionBarExtender().updateSelection(selection);
		super.setSelection(selection);
	}
	
	//temp code
	public void setDiffName(String name) {
		setPartName(name);
	}
	
}
