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

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EventObject;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.WrapperItemProvider;
import org.eclipse.emf.edit.ui.action.CopyAction;
import org.eclipse.emf.edit.ui.action.CutAction;
import org.eclipse.emf.edit.ui.action.DeleteAction;
import org.eclipse.emf.edit.ui.action.PasteAction;
import org.eclipse.emf.edit.ui.dnd.LocalTransfer;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.epf.authoring.ui.AuthoringUIExtensionManager;
import org.eclipse.epf.authoring.ui.AuthoringUIHelpContexts;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.UIActionDispatcher;
import org.eclipse.epf.authoring.ui.actions.AssignAction;
import org.eclipse.epf.authoring.ui.actions.CreateMethodElementCommand;
import org.eclipse.epf.authoring.ui.actions.CustomCategoryDeepCopyAction;
import org.eclipse.epf.authoring.ui.actions.ILibraryActionBarContributor;
import org.eclipse.epf.authoring.ui.actions.LayoutActionGroup;
import org.eclipse.epf.authoring.ui.actions.LibraryActionBarContributor;
import org.eclipse.epf.authoring.ui.actions.LibraryValidateAction;
import org.eclipse.epf.authoring.ui.actions.LibraryViewCopyAction;
import org.eclipse.epf.authoring.ui.actions.LibraryViewCutAction;
import org.eclipse.epf.authoring.ui.actions.LibraryViewDeleteAction;
import org.eclipse.epf.authoring.ui.actions.LibraryViewPasteAction;
import org.eclipse.epf.authoring.ui.actions.LibraryViewSimpleAction;
import org.eclipse.epf.authoring.ui.actions.NewPluginAction;
import org.eclipse.epf.authoring.ui.actions.ReassignAction;
import org.eclipse.epf.authoring.ui.actions.RenameAction;
import org.eclipse.epf.authoring.ui.actions.UnassignAction;
import org.eclipse.epf.authoring.ui.dialogs.MoveDialog;
import org.eclipse.epf.authoring.ui.dialogs.MoveDialog.MoveDialogExt;
import org.eclipse.epf.authoring.ui.dialogs.SwitchConfigDialog;
import org.eclipse.epf.authoring.ui.dialogs.VariabilitySelection;
import org.eclipse.epf.authoring.ui.dnd.LibraryViewerDragAdapter;
import org.eclipse.epf.authoring.ui.editors.EditorChooser;
import org.eclipse.epf.authoring.ui.editors.IEditorKeeper;
import org.eclipse.epf.authoring.ui.editors.MethodElementEditorInput;
import org.eclipse.epf.authoring.ui.editors.ProcessEditor;
import org.eclipse.epf.authoring.ui.preferences.ApplicationPreferenceConstants;
import org.eclipse.epf.authoring.ui.providers.MethodElementLabelDecorator;
import org.eclipse.epf.authoring.ui.util.RefreshHandler;
import org.eclipse.epf.authoring.ui.util.UIHelper;
import org.eclipse.epf.common.ui.util.MsgBox;
import org.eclipse.epf.library.ILibraryManager;
import org.eclipse.epf.library.LibraryPlugin;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.LibraryServiceException;
import org.eclipse.epf.library.edit.FeatureValueWrapperItemProvider;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.INamedFilter;
import org.eclipse.epf.library.edit.PluginUIPackageContext;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.navigator.MethodLibraryItemProvider;
import org.eclipse.epf.library.edit.navigator.PluginUIPackagesItemProvider;
import org.eclipse.epf.library.edit.ui.UserInteractionHelper;
import org.eclipse.epf.library.edit.util.ConfigurableComposedAdapterFactory;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.persistence.ILibraryResource;
import org.eclipse.epf.library.persistence.ILibraryResourceSet;
import org.eclipse.epf.library.persistence.PersistenceService;
import org.eclipse.epf.library.project.MethodLibraryProject;
import org.eclipse.epf.library.project.MethodLibraryProjectNature;
import org.eclipse.epf.library.ui.LibraryUIManager;
import org.eclipse.epf.library.ui.actions.LibraryLockingOperationRunner;
import org.eclipse.epf.library.ui.preferences.LibraryUIPreferences;
import org.eclipse.epf.library.util.ImportExportUtil;
import org.eclipse.epf.library.xmi.XMILibraryManager;
import org.eclipse.epf.library.xmi.XMILibraryUtil;
import org.eclipse.epf.persistence.FileManager;
import org.eclipse.epf.persistence.refresh.IRefreshHandler;
import org.eclipse.epf.persistence.refresh.RefreshJob;
import org.eclipse.epf.persistence.util.PersistenceUtil;
import org.eclipse.epf.services.Services;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.CapabilityPattern;
import org.eclipse.epf.uma.ContentCategory;
import org.eclipse.epf.uma.ContentDescription;
import org.eclipse.epf.uma.ContentElement;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.DeliveryProcess;
import org.eclipse.epf.uma.Discipline;
import org.eclipse.epf.uma.DisciplineGrouping;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.NamedElement;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.RoleSet;
import org.eclipse.epf.uma.RoleSetGrouping;
import org.eclipse.epf.uma.TeamProfile;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.WorkProductDescriptor;
import org.eclipse.epf.uma.ecore.impl.MultiResourceEObject;
import org.eclipse.epf.uma.edit.domain.TraceableAdapterFactoryEditingDomain;
import org.eclipse.epf.uma.provider.MethodPluginItemProvider;
import org.eclipse.epf.uma.util.AssociationHelper;
import org.eclipse.epf.uma.util.MessageException;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.epf.validation.LibraryEValidator;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.DecoratingLabelProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProviderChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.dnd.HTMLTransfer;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IPropertyListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.part.IShowInTarget;
import org.eclipse.ui.part.ShowInContext;
import org.eclipse.ui.views.navigator.ResourceNavigator;

/**
 * The Library view.
 * <p>
 * Displays the physical representation of a Method Library.
 * 
 * @author Phong Nguyen Le
 * @author Shilpa Toraskar
 * @author Kelvin Low
 * @author Jinhua Xi
 * @author Weiping Lu
 * @since 1.0
 */
public class LibraryView extends AbstractBaseView implements IShowInTarget, IRefreshHandler {

	/**
	 * The view ID.
	 */
	public static final String VIEW_ID = LibraryView.class.getName();

	private static LibraryView INSTANCE = null;
	
	private static boolean needRegisterChangeListenersInCreate = false;
	
	public static void setNeedRegisterChangeListenersInCreate(
			boolean needRegisterChangeListenersInCreate) {
		LibraryView.needRegisterChangeListenersInCreate = needRegisterChangeListenersInCreate;
	}

	private static boolean DEBUG = AuthoringUIPlugin.getDefault().isDebugging();

	protected TreeViewer treeViewer;

	protected IDoubleClickListener doubleClickListener = null;

	private ISelection selection;

	private LayoutActionGroup fLayoutActionSet;
	
	private String PERFORM_ID = "org.eclipse.epf.authoring.view.LibraryView.performValidatorAction"; //$NON-NLS-1$
	
	private Font italicFont = null;
	
	private IAction performLibraryValidationAction = new Action(
			AuthoringUIResources.Validate_method_library, AuthoringUIPlugin.getDefault().getImageDescriptor(
			"full/etool16/validate_method_lib.gif")) { //$NON-NLS-1$
		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.action.Action#run()
		 */
		public void run() {
			try {
//				ViewHelper.checkLibraryHealth();
				
				LibraryValidateAction validateAction = new LibraryValidateAction(LibraryService.getInstance().getCurrentMethodLibrary());
				((LibraryValidateAction)validateAction).putContextData(LibraryEValidator.CTX_ADAPTER_FACTORY_PROVIDER,
						ProcessEditor.adapterFactoryProvider);
	
				validateAction.run();
				
				
				
			} catch (Exception e) {
				AuthoringUIPlugin.getDefault().getLogger().logError(e);
			}
		}		
	};
	
	// Listen to process editor activation and prompt the user to load the
	// the process's default Configuration into the Configuration view.
	private IPartListener editorPartListener = new IPartListener() {

		public void partOpened(IWorkbenchPart part) {
		}

		public void partDeactivated(IWorkbenchPart part) {
		}

		public void partClosed(IWorkbenchPart part) {
		}

		public void partBroughtToTop(IWorkbenchPart part) {
		}

		public void partActivated(IWorkbenchPart part) {
			if (part instanceof IEditorPart) {
				SwitchConfigDialog.run(Display
						.getCurrent().getActiveShell(), (IEditorPart)part);
			}
		}

	};

	private long lastRefreshTimeStamp;

	private IResourceChangeListener resourceListener = new IResourceChangeListener() {
		public void resourceChanged(IResourceChangeEvent event) {
			if (event.getType() == IResourceChangeEvent.PRE_CLOSE ||
					event.getType() == IResourceChangeEvent.PRE_DELETE) {
				// project about to be closed or deleted
				IProject project = (IProject)event.getResource();
				try {
					if (project.exists() && project.isOpen() && project.hasNature(MethodLibraryProjectNature.NATURE_ID)) {
						EditorChooser.getInstance().closeAllMethodEditors();
						IProject currProject = MethodLibraryProject.findProject(LibraryService.getInstance().getCurrentMethodLibraryLocation());
						if (project == currProject) {
							LibraryService.getInstance().closeCurrentMethodLibrary();
						}
					}
				} catch (CoreException cex) {
					LibraryPlugin.getDefault().getLogger().logError(cex);
				} catch (LibraryServiceException lex) {
					LibraryPlugin.getDefault().getLogger().logError(lex);
				}
				return;
				
			}
			IResourceDelta delta = event.getDelta();
			if (delta == null)
				return;
						
			try {
				class ResourceDeltaVisitor implements IResourceDeltaVisitor {
					public boolean visit(IResourceDelta delta)
							throws CoreException {
						if ((delta.getFlags() & IResourceDelta.MARKERS) == IResourceDelta.MARKERS) {
							switch (delta.getKind()) {
							case IResourceDelta.ADDED:
							case IResourceDelta.CHANGED:
								// show Problems View
								UIHelper.showProblemsView();
								return false;
							}
						}
						// watch for project move                                                         
						else if (delta.getResource().getType() == IResource.PROJECT) {  
							if (delta.getKind() == IResourceDelta.CHANGED) {
								if (((delta.getFlags() & IResourceDelta.REPLACED) == 0) &&                
										((delta.getFlags() & IResourceDelta.DESCRIPTION) != 0)) {
									// tested using the Move.. command in the Resource Navigator view             
									// the REPLACED flag only seemed to occur when the library was moved          
									// outside of EPF and EPF was re-started, so make sure it's clear, otherwise  
									// we reload the library right after loading it when starting EPF             
									IProject project = (IProject) delta.getResource();
									ILibraryManager libMgr = LibraryService.getInstance().getCurrentLibraryManager();
									if(libMgr instanceof XMILibraryManager) {
										XMILibraryManager xmiLibMgr = ((XMILibraryManager)libMgr);
										if(project.equals(xmiLibMgr.getMethodLibraryProject())) {	
											xmiLibMgr.handleLibraryMoved();
											return false;
										}
									}
								//} else if ((delta.getFlags() & IResourceDelta.OPEN) != 0) {
								} else if ((delta.getFlags() & IResourceDelta.OPEN) != 0 && !ResourcesPlugin.getWorkspace().isTreeLocked()) {
									// project's open state was changed
									IProject project = (IProject) delta.getResource();
									if (project.isOpen() && project.hasNature(MethodLibraryProjectNature.NATURE_ID) &&
											LibraryService.getInstance().getCurrentMethodLibrary() == null) {
										// project is now open, open the library
										java.net.URI projectLocation = project.getLocationURI();
										try {
											LibraryService.getInstance().openMethodLibrary("xmi", projectLocation); //$NON-NLS-1$
										} catch (Exception e) {
											LibraryPlugin.getDefault().getLogger().logError(e);
										}
										return false;
									}
								}
							}
						}
						return true;
					}				
				};
				
				ResourceDeltaVisitor visitor = new ResourceDeltaVisitor();
				delta.accept(visitor);

			} catch (CoreException e) {
				CommonPlugin.INSTANCE.log(e);
			}

		}

	};
	
	private RefreshHandler refreshHandler = new RefreshHandler(this) {
		
		@Override
		public synchronized void doRefresh(
				Collection<Resource> removedResources,
				Collection<Resource> changedResources,
				Collection<IResource> addedWorkspaceResources,
				boolean refreshViews, IProgressMonitor monitor) {
			if (!refreshViews) {
				for (Iterator<Resource> iter = changedResources.iterator(); iter.hasNext();) {
					Resource resource = (Resource) iter.next();
					if (resource instanceof ILibraryResource
							&& ((ILibraryResource) resource).getLoadStamp() > lastRefreshTimeStamp) {
						refreshViews = true;
						break;
					}
				}
			}
			
			super.doRefresh(removedResources, changedResources, addedWorkspaceResources,
					refreshViews, monitor);
		}
		
		@Override
		protected void refreshViews() {
			LibraryView.this.refreshViews();
		}
	};

	private IPropertyListener propertyListener = new IPropertyListener() {
		public void propertyChanged(Object source, int propId) {
			// TODO: Replace hardcoded constant with variable.
			if (propId == 1) {
				firePropertyChange(PROP_DIRTY);
			}
		}
	};

	
	private LibraryViewExtender extender;
	public LibraryViewExtender getExtender() {
		return extender;
	}

	/**
	 * Creates a new instance.
	 */
	public LibraryView() {
		// read layout pref
		if (PluginUIPackageContext.FLAT_LAYOUT.equals(ApplicationPreferenceConstants.getLayout())) {
			PluginUIPackageContext.INSTANCE.setLayoutFlat();
		} else if (PluginUIPackageContext.HIERARCHICAL_LAYOUT.equals(ApplicationPreferenceConstants.getLayout())) {
			PluginUIPackageContext.INSTANCE.setLayoutHierarchical();
		}

		INSTANCE = this;
		
		extender = AuthoringUIExtensionManager.getInstance().createLibraryViewExtender(this);
	}
	
	/**
	 * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(Composite)
	 */
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);
		
		makeActions();
		
		PlatformUI.getWorkbench().getHelpSystem().setHelp(parent,
				AuthoringUIHelpContexts.LIBRARY_NAVIGATOR_VIEW_CONTEXT);

		RefreshJob.getInstance().setRefreshHandler(refreshHandler);
		
		if (needRegisterChangeListenersInCreate) {
			needRegisterChangeListenersInCreate = false; 
			registerChangeListeners();
		}
	}
	
	private void makeActions() {
		fLayoutActionSet= new LayoutActionGroup(this);
		IActionBars actionBars= getViewSite().getActionBars();
		fLayoutActionSet.fillActionBars(actionBars);		
		getExtender().getActionBarExtender().contributeToViewMenu(actionBars.getMenuManager());
	}

	/**
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	public void setFocus() {
		// This is required to make the global edit action work the first time.
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

	/**
	 * Returns the underlying tree viewer.
	 */
	public Viewer getViewer() {
		return treeViewer;
	}

	/**
	 * Creates the underlying tree viewer.
	 */
	public void createViewer(Composite parent) {
		treeViewer = new TreeViewer(parent) {
			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.jface.viewers.StructuredViewer#handleLabelProviderChanged(org.eclipse.jface.viewers.LabelProviderChangedEvent)
			 */
			protected void handleLabelProviderChanged(
					LabelProviderChangedEvent event) {
				if (event.getElement() instanceof IResource
						&& event.getSource() instanceof ILabelDecorator) {
					this.refresh();
					return;
				}
				super.handleLabelProviderChanged(event);
			}
		};

		adapterFactory = TngAdapterFactory.INSTANCE
				.getNavigatorView_ComposedAdapterFactory();

		// Create the command stack that will notify this editor as commands are
		// executed.
		BasicCommandStack commandStack = new BasicCommandStack();

		// Add a listener to set the most recent command's affected objects to
		// be the selection of the viewer with focus.
		commandStack.addCommandStackListener(new CommandStackListener() {
			public void commandStackChanged(final EventObject event) {
				Runnable runnable = new Runnable() {
					public void run() {
						firePropertyChange(IEditorPart.PROP_DIRTY);

						// Try to select the affected objects.
						Command mostRecentCommand = ((CommandStack) event
								.getSource()).getMostRecentCommand();
						if (mostRecentCommand != null) {
							setSelectionToViewer(mostRecentCommand
									.getAffectedObjects());
							
							if(mostRecentCommand instanceof CreateMethodElementCommand) {								
								// Work-around to refresh icons of newly created elements, whose decoration
								// sometimes got out of sync.
								//
								Viewer viewer = getViewer();
								if(viewer instanceof StructuredViewer) {
									Object[] elements = mostRecentCommand
											.getAffectedObjects().toArray();
									((StructuredViewer)viewer).update(elements, null);
								}
							}
						}
						if (propertySheetPage != null
								&& propertySheetPage.getControl() != null
								&& !propertySheetPage.getControl().isDisposed()
								&& propertySheetPage.getControl().isVisible()) {
							propertySheetPage.refresh();
						}
					}
				};
				if (Display.getCurrent() != null) {
					Display.getCurrent().asyncExec(runnable);
				} else {
					runnable.run();
				}
			}
		});

		// Create the editing domain with a special command stack.
		ResourceSet resourceSet = PersistenceService.INSTANCE
				.createResourceSet(Services.getDefaultLibraryPersistenceType());

		editingDomain = new TraceableAdapterFactoryEditingDomain(
				adapterFactory, commandStack, resourceSet);


		treeViewer.setContentProvider(new AdapterFactoryContentProvider(
				adapterFactory));

		// treeViewer.setLabelProvider(new
		// AdapterFactoryLabelProvider(adapterFactory));
		treeViewer.setLabelProvider(new DecoratingLabelProvider(
				new AdapterFactoryLabelProvider(adapterFactory),
				new MethodElementLabelDecorator()) {
		    
			public Font getFont(Object element) {
				if (customCategoryOwnedByOtherPlugin(element)) {
					return getItalicFont();
				}				
				return super.getFont(element);
			}
			
			private Font getItalicFont() {
				if (italicFont == null) {
					italicFont = createFont(SWT.ITALIC);
				}
				
				return italicFont;
			}
			
			private Font createFont(int style) {
				FontData[] fontdata = Display.getCurrent().getSystemFont().getFontData();
				for (FontData data : fontdata) {
					data.setStyle(style);
				}
				
				return new Font(Display.getCurrent(), fontdata);    	
		    }
		});

		// Add a double click listener for launching the Method editors.
		doubleClickListener = new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				UIActionDispatcher.getInstance().handleDoubleClickEvent(event);
			}
		};
		treeViewer.addDoubleClickListener(doubleClickListener);

		getSite().getPage().addPartListener(editorPartListener);

		ResourcesPlugin.getWorkspace()
				.addResourceChangeListener(resourceListener);
		
		createContextMenuFor(treeViewer);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.views.AbstractBaseView#setInputForViewer(Object)
	 */
	public void setInputForViewer(Object model) {
		Control ctrl = this.getViewer().getControl();
		if (ctrl.isDisposed()) {
			return;
		}
		if (model == null || model instanceof MethodLibrary) {
			// Prevent memory leak.
			StructuredSelection emptySelection = new StructuredSelection();
			actionDispatcher.setSelection(emptySelection);
			UIActionDispatcher.getInstance().setSelection(emptySelection);

			getViewer().setInput(model);
			
			if(adapterFactory instanceof ConfigurableComposedAdapterFactory) {
				IFilter filter = ((ConfigurableComposedAdapterFactory) adapterFactory).getFilter();
				if(filter instanceof INamedFilter) {
					String name = ((INamedFilter) filter).getName();
					if(name != null) {
						setContentDescription(name);
					}
				}
			}
			updateLastRefreshTimestamp();
		} else {
			AuthoringUIPlugin
					.getDefault()
					.getLogger()
					.logError(
							"Library viewer input is not a MethodLibrary object: " + model); //$NON-NLS-1$
		}
	}

	/**
	 * 
	 */
	private void updateLastRefreshTimestamp() {
		lastRefreshTimeStamp = System.currentTimeMillis();
	}

	
	/**
	 * Returns this view.
	 */
	public static LibraryView getView() {
//		return (LibraryView) ViewHelper.openView(VIEW_ID);
		boolean show = ViewHelper.isViewInCurrentPerspective(VIEW_ID);
		return (LibraryView)ViewHelper.findView(VIEW_ID, show);

	}
	
	/**
	 * Returns this view.
	 */
	public static LibraryView getViewInstance() {
		return INSTANCE;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.views.AbstractBaseView#setSelection(ISelection)
	 */
	public void setSelection(ISelection selection) {
		((LibraryViewActionBarContributor) actionBarContributor)
				.updateSelection(selection);

		super.setSelection(selection);
		this.selection = selection;		

//		((LibraryViewActionBarContributor) actionBarContributor).checkLocked();
		
		// if only one element is selected, show path to its file in status line
		//
		IStatusLineManager statusLineManager = getViewSite().getActionBars()
				.getStatusLineManager();
		if (statusLineManager != null) {
			String path = ""; //$NON-NLS-1$
			if (selection instanceof IStructuredSelection) {
				IStructuredSelection structuredSelection = (IStructuredSelection) selection;
				if (structuredSelection.size() == 1) {
					Object o = TngUtil.unwrap(structuredSelection
							.getFirstElement());
					if (o instanceof EObject) {
						Resource resource = ((EObject) o).eResource();
						if (resource != null) {
							URI uri = resource.getURI();
							if (uri != null && uri.isFile()) {
								path = uri.toFileString();
								if (o instanceof ContentElement) {
									ContentDescription content = ((ContentElement) o)
											.getPresentation();
									if (content != null) {
										resource = content.eResource();
										if (resource != null) {
											URI contentURI = resource.getURI();
											if (contentURI != null) {
												path = path
														+ ",." + File.separatorChar + contentURI.deresolve(uri).toFileString(); //$NON-NLS-1$
											}
										}
									}
								}
							}
						}
					}
				}
			}
			statusLineManager.setMessage(path);
		}
	}
	
	public void registerChangeListeners() {
		ILibraryManager manager = (ILibraryManager) LibraryService
		.getInstance().getCurrentLibraryManager();
		if (manager != null) {
			manager.registerEditingDomain(editingDomain);
			manager.addPropertyListener(propertyListener);
		}
	}

	/**
	 * @see org.eclipse.epf.library.ILibraryServiceListener#libraryReopened(MethodLibrary)
	 */
	public void libraryReopened(MethodLibrary library) {
		refreshViews();
	}
	
	@Override
	public void librarySet(MethodLibrary library) {
		super.librarySet(library);
		registerChangeListeners();
	}
	
	@Override
	public void libraryClosed(MethodLibrary library) {
		ILibraryManager manager = (ILibraryManager) LibraryService
				.getInstance().getCurrentLibraryManager();
		if (manager != null) {
			manager.unregisterEditingDomain(editingDomain);
			manager.removePropertyListener(propertyListener);
		}
		super.libraryClosed(library);
	}

	/**
	 * The action bar for the library view
	 */
	protected class LibraryViewActionBarContributor extends LibraryActionBarContributor {

		protected IAction newPluginAction = new NewPluginAction(
				AuthoringUIResources.new_plugin);

		private IAction moveAction = new Action(AuthoringUIResources.move) {

			private void doMove() {
				Collection elementsToMove = new ArrayList();
				IStructuredSelection selection = (IStructuredSelection) LibraryView.this.selection;
				for (Iterator iter = selection.iterator(); iter.hasNext();) {
					Object element = iter.next();
					if (element instanceof MethodElement
							|| (element = TngUtil.unwrap(element)) instanceof CustomCategory) {
						// Handle CustomCategory specially.
						EObject container = ((EObject) element).eContainer();
						IStatus status = UserInteractionHelper.checkModify(
								container, getSite().getShell());
						if (container != null && !status.isOK()) {
							AuthoringUIPlugin
									.getDefault()
									.getMsgDialog()
									.displayError(
											AuthoringUIResources.errorDialog_title,
											AuthoringUIResources.errorDialog_moveError,
											status);
							return;
						}
						elementsToMove.add(element);
					}
				}

				// TODO: Prompt the user the save editing changes for
				// elements that will be moved.

				// Remove elements from the move list if their containers are
				// already in the move list.
				for (Iterator iterator = elementsToMove.iterator(); iterator
						.hasNext();) {
					EObject element = (EObject) iterator.next();
					boolean isContained = false;
					check_contained: for (Iterator iter = elementsToMove
							.iterator(); iter.hasNext();) {
						Object e = (Object) iter.next();
						if (UmaUtil.isContainedBy(element, e)) {
							isContained = true;
							break check_contained;
						}
					}
					if (isContained) {
						iterator.remove();
					}
				}

//				MoveDialog dlg = new MoveDialog(getSite().getShell(),
//						elementsToMove, editingDomain);

				boolean movingCCerror = false;
				List<CustomCategory> movingCCsrcParents = new ArrayList<CustomCategory>();
				int sz = elementsToMove == null ? 0 : elementsToMove.size();
				if (sz == 0) {
					return;
				}
				if (elementsToMove.iterator().next() instanceof CustomCategory) {
					List uiList = ((IStructuredSelection) getSelection()).toList();
					if (uiList.size() == sz) {
						int i = -1;
						for (Object element : elementsToMove) {
							i++;
							Object ui = uiList.get(i);
							if (ui instanceof WrapperItemProvider) {
								if (TngUtil.unwrap(ui) != element) {
									movingCCerror = true;
									break;
								}
								Object parent = ((WrapperItemProvider) ui).getParent(null);
								if (TngUtil.unwrap(parent) instanceof CustomCategory) {
									movingCCsrcParents.add((CustomCategory) TngUtil.unwrap(parent));
								} else {
									movingCCerror = true;
									break;
								}
							} else {
								movingCCerror = true;
								break;
							}
						}
					} else {
						movingCCerror = true;
					}
				}
				
				if (movingCCerror) {
					AuthoringUIPlugin
					.getDefault()
					.getMsgDialog()
					.displayError(
							AuthoringUIResources.errorDialog_title,
							AuthoringUIResources.errorDialog_moveError);
						return;
				}
						
				MoveDialog dlg = ! movingCCsrcParents.isEmpty() ? new MoveDialogExt(getSite()
						.getShell(), elementsToMove, editingDomain, movingCCsrcParents)
						: new MoveDialog(getSite().getShell(), elementsToMove,
								editingDomain);				
				dlg.open();
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.jface.action.Action#run()
			 */
			public void run() {
				LibraryLockingOperationRunner runner = new LibraryLockingOperationRunner();
				runner.setProgressMonitor(getActionBars()
						.getStatusLineManager().getProgressMonitor());
				runner.run(new IRunnableWithProgress() {

					public void run(IProgressMonitor monitor)
							throws InvocationTargetException,
							InterruptedException {
						doMove();
					}

				});
			}
		};

		private LibraryViewSimpleAction assignAction = new AssignAction(LibraryView.this);
		
		private LibraryViewSimpleAction unassignAction = new UnassignAction(LibraryView.this);
		
		private LibraryViewSimpleAction reassignAction = new ReassignAction(LibraryView.this);

		private LibraryViewSimpleAction customCategoryDeepCopyAction = new CustomCategoryDeepCopyAction(LibraryView.this);
		
		private RenameAction renameAction = new RenameAction();

		private IAction replaceAction = new Action(
				AuthoringUIResources.ElementsView_replaceAction_text) {

			public void run() {
				if (!getPage().closeAllEditors(true)) {
					return;
				}

				final ProcessComponent procComp = (ProcessComponent) ((IStructuredSelection) selection)
						.getFirstElement();
				Process proc = procComp.getProcess();
				final String typeStr;
				if (proc instanceof CapabilityPattern) {
					typeStr = AuthoringUIResources.ElementsView_20;
				} else if (proc instanceof DeliveryProcess) {
					typeStr = AuthoringUIResources.ElementsView_21;
				} else {
					typeStr = AuthoringUIResources.ElementsView_22;
				}
				DirectoryDialog dirDlg = new DirectoryDialog(getSite()
						.getShell());
				dirDlg.setText(AuthoringUIResources.ElementsView_replace_text);
				dirDlg
						.setMessage(MessageFormat
								.format(
										AuthoringUIResources.ElementsView_migration_dir_dlg_description_text,
										new Object[] { typeStr }));
				final String dir = dirDlg.open();
				if (dir == null) {
					return;
				}

				boolean ret = UserInteractionHelper
						.runWithProgress(
								new org.eclipse.epf.library.edit.util.IRunnableWithProgress() {

									public void run(IProgressMonitor monitor)
											throws InvocationTargetException,
											InterruptedException {
										try {
											ImportExportUtil.replace(procComp,
													dir);
										} catch (Exception e) {
											throw new WrappedException(e);
										}
									}
								},
								MessageFormat
										.format(
												AuthoringUIResources.ElementsView_replaceingwithformat_text,
												new Object[] { typeStr }));

				if (ret) {
					// Reopen the library.
					LibraryUIManager.getInstance().openLibrary(
							LibraryService.getInstance()
									.getCurrentMethodLibraryLocation());
				}
			}
		};

		private IAction openVariabilityDialogAction = new Action(
				AuthoringUIResources.ElementsView_openVariabilityDialogAction_text) {
			public void run() {
				Object obj = TngUtil.unwrap(((IStructuredSelection) selection)
						.getFirstElement());
				if (obj instanceof VariabilityElement) {
					VariabilityElement element = (VariabilityElement) obj;
					VariabilitySelection variabilitySelection = new VariabilitySelection();
					Object selectedObject = variabilitySelection
							.getSelectedVariability(element);
					if (selectedObject != null) {
						EditorChooser.getInstance().openEditor(selectedObject);
					}
				}
			}
		};

		private boolean locked;

		private boolean canMove;
		
		private boolean canAssign;

		private IAction showInResourceNavigatorAction = new Action(
				AuthoringUIResources.showInResourceNavigatorAction_label) {
			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.jface.action.Action#run()
			 */
			public void run() {
				ResourceNavigator navigator;
				try {
					navigator = (ResourceNavigator) getPage().showView(
							"org.eclipse.ui.views.ResourceNavigator"); //$NON-NLS-1$
					Object obj = TngUtil
							.unwrap(((IStructuredSelection) selection)
									.getFirstElement());
					IResource wsRes = PersistenceUtil.getWorkspaceResource(obj);
					if (wsRes != null) {
						navigator.getViewer().setSelection(
								new StructuredSelection(wsRes), true);
					}
				} catch (PartInitException e) {
					AuthoringUIPlugin.getDefault().getLogger().logError(e);
				}
			}
		};
		
		private boolean canRename;

		/**
		 * Creates a new instance.
		 */
		public LibraryViewActionBarContributor(EditingDomain editingDomain) {
			super(editingDomain);
		}

		/**
		 * @see org.eclipse.epf.authoring.ui.actions.LibraryActionBarContributor#createDeleteAction()
		 */
		protected DeleteAction createDeleteAction() {
			LibraryViewDeleteAction action = new LibraryViewDeleteAction() {
				/**
				 * @see org.eclipse.epf.authoring.ui.actions.ElementsViewPasteAction#updateSelection(IStructuredSelection)
				 */
				public boolean updateSelection(IStructuredSelection selection) {
					if (locked) {
						return false;
					}
					return super.updateSelection(selection);
				}

				public void run() {
					String title = AuthoringUIResources.actions_LibraryActionBarContributor_deleteErrorTitle;
					try {
						super.run();
					} catch (MessageException e) {
						AuthoringUIPlugin.getDefault().getMsgDialog()
								.displayError(title, e.getMessage());
					} catch (Exception e) {
						String details = TngUtil.toStackTraceString(e);
						String message = AuthoringUIResources.actions_LibraryActionBarContributor_deleteErrorMessage;
						String reason = AuthoringUIResources.actions_LibraryActionBarContributor_deleteErrorReason;
						AuthoringUIPlugin.getDefault().getMsgDialog()
								.displayError(title, message, reason, details,
										e);
					}
				}

				protected void deleteFailed() {
					// Library need to be reloaded.
					ViewHelper.reloadCurrentLibrary(getSite().getShell(), AuthoringUIResources.msg_reloadLibrary);

					// User tried to select the old selection.
					if (activeViewPart instanceof IViewerProvider) {
						final ArrayList elements = new ArrayList();
						for (Iterator iter = selection.iterator(); iter
								.hasNext();) {
							Object e = iter.next();
							if (e instanceof MethodElement) {
								String guid = ((MethodElement) e).getGuid();
								ILibraryManager manager = LibraryService
										.getInstance()
										.getCurrentLibraryManager();
								if (manager != null) {
									MethodElement element = manager
											.getMethodElement(guid);
									if (element != null) {
										elements.add(element);
									}
								}
							}
						}
						if (!elements.isEmpty()) {
							Viewer viewer = ((IViewerProvider) activeViewPart)
									.getViewer();
							viewer.setSelection(new StructuredSelection(
									elements), true);
						}
					}
				}
			};
			action.setProgressMonitor(getActionBars().getStatusLineManager()
					.getProgressMonitor());
			return action;
		}

		/**
		 * @see org.eclipse.epf.authoring.ui.actions.LibraryActionBarContributor#createCutAction()
		 */
		protected CutAction createCutAction() {
			return new LibraryViewCutAction() {
				/**
				 * @see org.eclipse.epf.authoring.ui.actions.ElementsViewPasteAction#updateSelection(IStructuredSelection)
				 */
				public boolean updateSelection(IStructuredSelection selection) {
					if (locked) {
						return false;
					}
					return super.updateSelection(selection);
				}
			};
		}

		/**
		 * @see org.eclipse.epf.authoring.ui.actions.LibraryActionBarContributor#createCopyAction()
		 */
		protected CopyAction createCopyAction() {
			return new LibraryViewCopyAction(){

				@Override
				public boolean updateSelection(IStructuredSelection selection) {
					if( ViewHelper.hasCustomizedLocker((IStructuredSelection) selection) && locked ){	
						return false;
					}
					return super.updateSelection(selection);
				}
				
			};
		}

		/**
		 * @see org.eclipse.epf.authoring.ui.actions.LibraryActionBarContributor#createPasteAction()
		 */
		protected PasteAction createPasteAction() {
			LibraryViewPasteAction action = new LibraryViewPasteAction() {
				/**
				 * @see org.eclipse.epf.authoring.ui.actions.ElementsViewPasteAction#updateSelection(IStructuredSelection)
				 */
				public boolean updateSelection(IStructuredSelection selection) {
					if (locked) {
						return false;
					}
					return super.updateSelection(selection);
				}
			};
			action.setProgressMonitor(getActionBars().getStatusLineManager()
					.getProgressMonitor());
			return action;
		}

		private void checkLocked() {
			if (selection instanceof IStructuredSelection) {
				boolean wasLocked = locked;
//				locked = ViewHelper.isLocked((IStructuredSelection) selection);
				if(ViewHelper.hasCustomizedLocker((IStructuredSelection) selection) ){					
					locked = ViewHelper.isLockedWithCustomizedLocker((IStructuredSelection) selection);					
				}else{
					locked = ViewHelper.isLocked((IStructuredSelection) selection);
				}
				if (wasLocked != locked) {
					deleteAction.setEnabled(deleteAction
							.updateSelection((IStructuredSelection) selection));
					cutAction.setEnabled(cutAction
							.updateSelection((IStructuredSelection) selection));
					pasteAction.setEnabled(pasteAction
							.updateSelection((IStructuredSelection) selection));
					boolean enabled = !locked;
					renameAction.setEnabled(enabled);
					moveAction.setEnabled(enabled);
					
					//assignAction.setEnabled(enabled);
					unassignAction.setEnabled(enabled);
					reassignAction.setEnabled(enabled);
					//customCategoryDeepCopyAction.setEnabled(enabled);
				}
			}
		}
		
		void updateSelection(ISelection selection) {
			updateSelection_(selection);
			getExtender().getActionBarExtender().updateSelection(selection);
		}
		
		void updateSelection_(ISelection selection) {			
			IStructuredSelection sel = (IStructuredSelection) selection;
			
			renameAction.setActiveWorkbenchPart(LibraryView.this);
			canRename = renameAction.updateSelection(sel);

//			locked = ViewHelper.isLocked(sel);
			if(ViewHelper.hasCustomizedLocker(sel) ){					
				locked = ViewHelper.isLockedWithCustomizedLocker(sel);
			}else{
				locked = ViewHelper.isLocked(sel);
			}
			
			boolean enabled = !locked;
			if(canRename && !enabled) {
				renameAction.setEnabled(enabled);
			}
			moveAction.setEnabled(enabled);
			
			unassignAction.setEnabled(enabled);
			reassignAction.setEnabled(enabled);

			canMove = canMove(sel);
			
			canAssign = assignAction.updateSelection(sel);

			if (!locked && sel.size() == 1
					&& sel.getFirstElement() instanceof ProcessComponent) {
				replaceAction.setEnabled(true);
			} else {
				replaceAction.setEnabled(false);
			}

			if (sel.size() == 1) {
				Object obj = TngUtil.unwrap(((IStructuredSelection) selection)
						.getFirstElement());
				openVariabilityDialogAction
						.setEnabled(obj instanceof VariabilityElement
								&& !TngUtil.isPredefined((MethodElement) obj));
				showInResourceNavigatorAction
						.setEnabled(obj instanceof MethodElement);
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.epf.authoring.ui.actions.LibraryActionBarContributor#enableGlobalEditMenu()
		 */
		public void enableGlobalEditMenu() {
		}

		/**
		 * @see org.eclipse.emf.edit.ui.action.EditingDomainActionBarContributor#addGlobalActions(IMenuManager)
		 */
		protected void addGlobalActions(IMenuManager menuManager) {
			super.addGlobalActions(menuManager);
			menuManager.insertAfter("fixed-additions", newPluginAction); //$NON-NLS-1$
		} 
		
		private boolean canMove(IStructuredSelection selection) {		
			int ix = canMoveCheck4CustomCategories(selection);
			if (ix != 0) {
				return ix == 1;
			}
			
			MethodPlugin srcPlugin = null;
			for (Iterator iter = selection.iterator(); iter.hasNext();) {
				Object element = iter.next();
				if (element instanceof MethodElement) {
					MethodPlugin plugin = UmaUtil.getMethodPlugin((MethodElement) element);
					if (plugin == null) {
						return false;
					} else if (plugin != srcPlugin) {
						if (srcPlugin != null) {
							return false;
						}
						srcPlugin = plugin;
					}
				}
			}
			
			for (Iterator iter = selection.iterator(); iter.hasNext();) {
				Object element = iter.next();
				if ((element instanceof MethodElement)
						&& !(element instanceof ContentCategory)
						&& !(element instanceof MethodPlugin)
						&& !(element instanceof MethodConfiguration)
						&& !TngUtil.isPredefined((MethodElement) element)) {
					return true;
				}
			}
			return false;
		}
		
		//-1: cannot move
		//0:  need futhre check
		//1:  can move
		private int canMoveCheck4CustomCategories(IStructuredSelection selection) {
			boolean hasCC = false;
			boolean hasOther = false;
			for (Iterator iter = selection.iterator(); iter.hasNext();) {
				Object obj = iter.next();
				if (obj instanceof WrapperItemProvider && TngUtil.unwrap(obj) instanceof CustomCategory) {
					if (customCategoryOwnedByOtherPlugin(obj)) {
						return -1;
					}
					hasCC = true;
				} else {
					hasOther = true;
				}
				if (hasCC && hasOther) {
					return -1;
				}
			}			
			return hasCC ? 1 : 0;
		}
		

		/**
		 * Adds Separators for editor additions to the tool bar.
		 */
		public void contributeToToolBar(IToolBarManager toolBarManager) {
//			toolBarManager.add(new Separator("method-settings")); //$NON-NLS-1$
//			toolBarManager.add(new Separator("method-additions")); //$NON-NLS-1$
			performLibraryValidationAction.setId(PERFORM_ID);
//			if (AuthoringUIPreferences.getEnableLibraryValidation()) {
				toolBarManager.add(performLibraryValidationAction);
//			}
			
		}
		
		/**
		 * @see LibraryActionBarContributor#menuAboutToShow(IMenuManager)
		 */
		public void menuAboutToShow(IMenuManager menuManager) {
			menuAboutToShow_(menuManager);
			getExtender().getActionBarExtender().menuAboutToShow(menuManager);
		}
		
		private void menuAboutToShow_(IMenuManager menuManager) {
			// This is needed to enable Paste after Copy but the selection has not been changed yet
			//
			updatePasteAction();
			
			checkLocked();

			// Add our standard marker.
			menuManager.add(new Separator("fixed-additions")); //$NON-NLS-1$
			menuManager.add(new Separator("fixed-additions-end")); //$NON-NLS-1$
			menuManager.add(new Separator("edit")); //$NON-NLS-1$

			// Add the edit menu actions.
			menuManager.add(new ActionContributionItem(libraryViewEditAction));
			menuManager.add(new ActionContributionItem(copyAction));
			menuManager.add(new ActionContributionItem(pasteAction));
			menuManager.add(new Separator());
			menuManager.add(new ActionContributionItem(deleteAction));
			if (canRename) {
				menuManager.add(new ActionContributionItem(renameAction));
			}
			if (canMove) {
				menuManager.add(new ActionContributionItem(moveAction));
			}
			
			if (canAssign) {
				menuManager.add(new ActionContributionItem(assignAction));
				menuManager.add(new ActionContributionItem(unassignAction));
				menuManager.add(new ActionContributionItem(reassignAction));
				menuManager.add(new ActionContributionItem(customCategoryDeepCopyAction));				
			}

			menuManager.add(new Separator("view")); //$NON-NLS-1$
			if (openVariabilityDialogAction.isEnabled()) {
				menuManager.add(new ActionContributionItem(
						openVariabilityDialogAction));
			}
			if (showInResourceNavigatorAction.isEnabled()) {
				menuManager.add(new ActionContributionItem(
						showInResourceNavigatorAction));
			}
			menuManager.add(new Separator());

			// Add our other standard marker where other plugins will contribute
			// menu
			//
			menuManager.add(new Separator("additions")); //$NON-NLS-1$
			menuManager.add(new Separator("additions-end")); //$NON-NLS-1$

			addGlobalActions(menuManager);

			if (!locked) {
				MenuManager submenuManager = null;
				submenuManager = new MenuManager(
						AuthoringUIResources._UI_CreateChild_menu_item,
						AuthoringUIResources._UI_CreateChild_menu_item);
				populateManager(submenuManager, createChildActions, null);
				menuManager.insertBefore("fixed-additions", submenuManager); //$NON-NLS-1$
			}

//			menuManager.add(new Action("Test") {
//				/*
//				 * (non-Javadoc)
//				 *
//				 * @see org.eclipse.jface.action.Action#run()
//				 */
//				public void run() {
//					// reload
//					//
////					ISelection selection = getSelection();
////					if (selection instanceof IStructuredSelection) {
////						Object object = ((IStructuredSelection) selection)
////						.getFirstElement();
////						object = TngUtil.unwrap(object);
////						if (object instanceof InternalEObject) {
////							InternalEObject e = ((InternalEObject) object);
////							ILibraryResourceSet resourceSet = (ILibraryResourceSet) e
////							.eResource().getResourceSet();
////							resourceSet.unload(e);
////							URI uri = e.eProxyURI();
////							EObject newObject = resourceSet.getEObject(e, uri,
////									true);
////							refreshViews();
////						}
////					}
//					
//					// plugin/config folder
//					//
//					ILibraryPersister persister = LibraryServiceUtil.getCurrentPersister();
//					if(persister instanceof IFileBasedLibraryPersister) {
//						IFileBasedLibraryPersister filePersister = (IFileBasedLibraryPersister) persister;
//						MethodLibrary library = LibraryService.getInstance().getCurrentMethodLibrary();
//						try {
//							File file = filePersister.createMethodPluginFolder("plugin100", library);
//							System.out.println("plugin dir: " + file);
//						}
//						catch(Exception e) {
//							e.printStackTrace();
//						}
//						try {
//							File file = filePersister.getDefaultMethodConfigurationFolder(library);
//							System.out.println("config dir: " + file);
//						}
//						catch(Exception e) {
//							e.printStackTrace();
//						}
//					}
//				}
//			});
			
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.epf.authoring.ui.actions.LibraryActionBarContributor#refreshViewer(org.eclipse.jface.viewers.Viewer)
		 */
		protected void refreshViewer(final Viewer viewer) {
			if (viewer == LibraryView.this.getViewer()) {
				ILibraryManager manager = (ILibraryManager) LibraryService
						.getInstance().getCurrentLibraryManager();
				if (manager != null) {
					ResourceSet resourceSet = manager.getEditingDomain()
							.getResourceSet();
					if (resourceSet instanceof ILibraryResourceSet) {
						final ILibraryResourceSet libResourceSet = (ILibraryResourceSet) resourceSet;
						if(libResourceSet.getPersistenceType().equals(Services.XMI_PERSISTENCE_TYPE)) {
							IRunnableWithProgress runnable = new IRunnableWithProgress() {

								public void run(IProgressMonitor monitor)
										throws InvocationTargetException,
										InterruptedException {
									List<Resource> resources = new ArrayList<Resource>(libResourceSet.getResources());
									int totalWorks = resources.size() + 3;
									monitor.beginTask(AuthoringUIResources._UI_RefreshViewer_menu_item, totalWorks); //$NON-NLS-1$
									try {
										// Refresh all loaded resources and load newly added resources
										//
										monitor.subTask("Loading new resources...");
										monitor.worked(1);
										libResourceSet.loadNewResources();

										List<Resource> removedResources = new ArrayList<Resource>();
										List<Resource> changedResources = new ArrayList<Resource>();
										for (Resource resource : resources) {
											String loc = resource.getURI().toFileString();
											monitor.subTask(NLS.bind("Checking resource ''{0}''...", loc));
											monitor.worked(1);
											IResource wsRes = FileManager.getResourceForLocation(loc);
											if (wsRes == null) {
												removedResources.add(resource);
											} else if (!wsRes.isSynchronized(IResource.DEPTH_ZERO)) {
												changedResources.add(resource);
											}
										}
										monitor.subTask("Refreshing UI...");
										monitor.worked(2);
										if (removedResources.isEmpty()
												&& changedResources.isEmpty()) {
											viewer.refresh();
										} else {
											refreshHandler.doRefresh(removedResources,
													changedResources, null, false, new NullProgressMonitor());
										}
									} finally {
										monitor.done();
									}
								}

							};
							IRunnableContext context = new ProgressMonitorDialog(getSite()
									.getShell());
							try {
								getSite().getWorkbenchWindow().getWorkbench()
										.getProgressService().runInUI(context, runnable,
												null);
							} catch (Exception e) {
								AuthoringUIPlugin.getDefault().getLogger().logError(e);
								String title = AuthoringUIResources.ProcessEditor_refreshErrorTitle;
								AuthoringUIPlugin.getDefault().getMsgDialog().displayError(
										title, e.toString(), e);
							}
							return;
						}
					}
				}
			}
			super.refreshViewer(viewer);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.authoring.ui.views.AbstractBaseView#createActionBarContributor()
	 */
	public ILibraryActionBarContributor createActionBarContributor() {
		ILibraryActionBarContributor actionBar = new LibraryViewActionBarContributor(
				editingDomain);
		return actionBar;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.authoring.ui.views.AbstractBaseView#createContextMenuFor(org.eclipse.jface.viewers.StructuredViewer)
	 */
	public void createContextMenuFor(final StructuredViewer viewer) {
		super.createContextMenuFor(viewer);
		int dndOperations = DND.DROP_MOVE | DND.DROP_COPY | DND.DROP_LINK;
		Transfer[] transfers = new Transfer[] { HTMLTransfer.getInstance(),
				TextTransfer.getInstance(), LocalTransfer.getInstance() };
		viewer.addDragSupport(dndOperations, transfers,
				createDragSourceListener(viewer));
	}
	
	protected DragSourceListener createDragSourceListener(Viewer viewer) {
		return new LibraryViewerDragAdapter(viewer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.authoring.ui.views.AbstractBaseView#menuAboutToShow(org.eclipse.jface.action.IMenuManager)
	 */
	public void menuAboutToShow(IMenuManager menuManager) {
		super.menuAboutToShow(menuManager);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.ViewPart#init(org.eclipse.ui.IViewSite)
	 */
	public void init(IViewSite site) throws PartInitException {
		super.init(site);
	
		// add property change listener for library validation preference
		AuthoringUIPlugin.getDefault().getPreferenceStore()
				.addPropertyChangeListener(new IPropertyChangeListener() {
					public void propertyChange(PropertyChangeEvent event) {
//						if (event
//								.getProperty()
//								.equals(
//										AuthoringUIPreferences.ENABLE_LIBRARY_VALIDATION)) {
//							Boolean enabled = (Boolean) event.getNewValue();
//							IToolBarManager toolBarManager = getViewSite()
//								.getActionBars().getToolBarManager();
//					
//							if (enabled != null && enabled.booleanValue()) {		//always on now
//								performLibraryValidationAction
//										.setId(PERFORM_ID);
//								toolBarManager.insertAfter("additions", performLibraryValidationAction); //$NON-NLS-1$
//							} else {
//								IContributionItem[] items = toolBarManager.getItems();
//								for (int i = 0; i < items.length; i++) {
//									IContributionItem item = (IContributionItem) items[i];
//									if (item.getId().equals(PERFORM_ID))
//										toolBarManager.remove(item);
//								}
//							}
//							toolBarManager.update(true);
//						}
					}
				});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.authoring.ui.views.AbstractBaseView#dispose()
	 */
	public void dispose() {
		RefreshJob.getInstance().setRefreshHandler(null);

		if (fLayoutActionSet != null)	
			fLayoutActionSet.dispose();

		super.dispose();
		if (doubleClickListener != null) {
			treeViewer.removeDoubleClickListener(doubleClickListener);
		}
		
    	if (italicFont != null) {
    		italicFont.dispose();
    	}		
	}

	/**
	 * @param obj
	 *            ItemProvider
	 * @return ProcessComponent associated with the given obj
	 */
	public ProcessComponent getProcessComponent(Object obj) {
		Object parent;
		for (parent = obj; (parent != null && !(parent instanceof ProcessComponent));) {
			ItemProviderAdapter adapter = (ItemProviderAdapter) adapterFactory
					.adapt(parent, ITreeItemContentProvider.class);
			parent = adapter.getParent(parent);
		}
		return (ProcessComponent) parent;
	}

	private static abstract class StatusWorkspaceModifyOperation extends
			WorkspaceModifyOperation {

		protected String errMsg;

		public String getErrorMessage() {
			return errMsg;
		}
	}

	/**
	 * opens a Method Library
	 * 
	 * @param path
	 *            path to the library
	 */
	public void openLibrary(final String path) {
		// Do the work within an operation because this is a long running
		// activity that modifies the workbench.
		StatusWorkspaceModifyOperation operation = new StatusWorkspaceModifyOperation() {

			// This is the method that gets invoked when the operation runs.
			public void execute(IProgressMonitor monitor) {
				monitor.beginTask(AuthoringUIResources.openingLibraryTask_name,
						2);
				try {
					monitor.worked(1);
					LibraryService.getInstance().closeCurrentMethodLibrary();
					MethodLibrary library = XMILibraryUtil
							.openMethodLibrary(path);
					LibraryService.getInstance().setCurrentMethodLibrary(
							library);
					LibraryUIPreferences.setSavedLibraryPath(path);
				} catch (Exception ex) {
					if (ex instanceof IOException) {
						String message = ex.getMessage();
						if (message.startsWith("###")) { //$NON-NLS-1$
							String projectFileName = message.substring(3);
							String prompt = AuthoringUIResources.bind(
									AuthoringUIResources.ElementsView_35,
									(new Object[] { projectFileName }));
							if (MsgBox.prompt(prompt, SWT.RETRY | SWT.CANCEL) == SWT.RETRY) {
								openLibrary(path);
							}
						}
					} else {
						AuthoringUIPlugin
								.getDefault()
								.getMsgDialog()
								.displayError(
										AuthoringUIResources.errorDialog_title,
										AuthoringUIResources.openLibraryError_msg,
										ex);
					}
				} finally {
					monitor.done();
				}
			}
		};

		try {
			// This runs the options, and shows progress.
			new ProgressMonitorDialog(getSite().getShell()).run(true, false,
					operation);
			if (operation.getErrorMessage() == null) {
				getSite().getPage().closeAllEditors(false);
			}
		} catch (Exception e) {
			AuthoringUIPlugin.getDefault().getLogger().logError(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.authoring.ui.views.AbstractBaseView#handleActivate(org.eclipse.ui.IWorkbenchPart)
	 */
	protected void handleActivate(IWorkbenchPart part) {
		super.handleActivate(part);

		// TODO: Review implementation.
		// Make sure that the method library open is not closed.
		XMILibraryUtil.openMethodLibraryProject(LibraryService.getInstance()
				.getCurrentMethodLibrary(), getViewSite().getActionBars()
				.getStatusLineManager().getProgressMonitor());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.authoring.ui.views.SaveableLibraryViewPart#isSaveAsAllowed()
	 */
	public boolean isSaveAsAllowed() {
		// This view should not participate in all the save commands.
		return false;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.views.SaveableLibraryViewPart#doSaveAs()
	 */
	public void doSaveAs() {
	}
	
	/**
	 * Used by org.eclipse.epf.authoring.ui.actions.LinkWithEditor Given an
	 * object that is a MethodElement, selects that object in the Lib Nav
	 * 
	 * @param o
	 */
	public void setSelectionToViewer(Object object) {
		Object o = TngUtil.unwrap(object);
		if (!(o instanceof MethodElement))
			return;
		if (o instanceof MethodConfiguration) {
			super.setSelectionToViewer(Arrays.asList(new Object[] { o }));
			return;
		}
		try {
			MethodPlugin plugin = UmaUtil.getMethodPlugin((MethodElement) o);

			MethodPluginItemProvider pluginAdapter = (MethodPluginItemProvider) TngUtil
					.getAdapter(plugin, MethodPluginItemProvider.class);

			MethodLibraryItemProvider libraryAdapter = (MethodLibraryItemProvider) TngUtil
					.getAdapter(LibraryService.getInstance().getCurrentMethodLibrary(), MethodLibraryItemProvider.class);

			// expand plugin packages
			ItemProviderAdapter pluginTreeAdapter = libraryAdapter.getPluginItemProvider(plugin);
			while (pluginTreeAdapter instanceof PluginUIPackagesItemProvider) {
				treeViewer.setExpandedState(pluginTreeAdapter, true);
				pluginTreeAdapter = ((PluginUIPackagesItemProvider)pluginTreeAdapter).getPluginItemProvider(plugin);
			}
			treeViewer.setExpandedState(plugin, true);

			
			if (o instanceof BreakdownElement) {				
				Process proc = TngUtil.getOwningProcess((BreakdownElement)o);
				if(proc != null) {
					o = proc.eContainer();
				}
			}
			if (o instanceof ProcessComponent || o instanceof ProcessPackage) {

				if (pluginAdapter != null) {	//Check null pointer here. To do: fix the root cause so that pluginAdapte would not be null here.
					treeViewer.setExpandedState(pluginAdapter.getChildren(plugin)
							.toArray()[1], true);
					// Expand the process packages.
					expandTreeViewerPackages(((MethodElement) o).eContainer());
				}

			} else if (pluginAdapter != null) {
				ITreeItemContentProvider methodContentPkg = (ITreeItemContentProvider) pluginAdapter
						.getChildren(plugin).toArray()[0];
				treeViewer.setExpandedState(methodContentPkg, true);

				if (o instanceof CustomCategory) {					
					CustomCategory rootCC = TngUtil
							.getRootCustomCategory(plugin);
										
					// find the tree path to the custom category from the root custom category
					//
					final List<Object> treePath = new ArrayList<Object>();
					for(CustomCategory cc = (CustomCategory) o; cc != rootCC;) {
						treePath.add(0, cc);
						cc = (CustomCategory) AssociationHelper.getCustomCategories(cc).get(0);						 
					}
					
					o = expandTree(rootCC, treePath);
					
				} else if (o instanceof ContentCategory) {
					Object stdCats = methodContentPkg.getChildren(o).toArray()[1];
								
					// find the tree path to the category from the root "Standard Categories" category
					//
					final List<Object> treePath = new ArrayList<Object>();
					if (o instanceof Discipline) {
						// disciplines can nest
						Object dis = o;
						do {
							treePath.add(0, dis);
							List parentDisc = AssociationHelper.getDiscipline((Discipline)dis);
							if (parentDisc.isEmpty()) break;
							dis = parentDisc.get(0);						 
						} while (true);
						// get discipline group
						if (!AssociationHelper.getDisciplineGroups((Discipline)dis).isEmpty()) {
							DisciplineGrouping dg = (DisciplineGrouping) AssociationHelper.getDisciplineGroups((Discipline)dis).get(0);
							treePath.add(0, dg);
						}
						// get "Disciplines"
						treePath.add(0, TngUtil.getDisciplineCategoriesItemProvider(plugin));
						o = expandTree(stdCats, treePath);
					} else if (o instanceof RoleSet) {
						// rolesets cannot nest
						treePath.add(0, o);
						RoleSetGrouping rsg = (RoleSetGrouping) AssociationHelper.getRoleSetGroups((RoleSet)o).get(0);						 
						treePath.add(0, rsg);
						// get "RoleSets"
						treePath.add(0, TngUtil.getRoleSetsItemProvider(plugin));
						o = expandTree(stdCats, treePath);
					} else {
						treeViewer.setExpandedState(stdCats, true);
					}

				} else {
					Object coreContent = methodContentPkg.getChildren(o)
							.toArray()[0];
					treeViewer.setExpandedState(coreContent, true);

					// Expand the content packages.
					expandTreeViewerPackages(((MethodElement) o).eContainer());
				}
			}
		} catch (Exception ex) {
			AuthoringUIPlugin.getDefault().getLogger().logError(ex);
		}

		super.setSelectionToViewer(Arrays.asList(new Object[] { o }));

		if(o instanceof ProcessComponent && object instanceof BreakdownElement) {
			// open the process in its editor and select the breakdown element
			//
			IEditorKeeper.REFERENCE.getEditorKeeper().openEditor(o);
			IEditorPart editor = getSite().getPage().findEditor(new MethodElementEditorInput((MethodElement) o));
			if(editor instanceof ProcessEditor) {
				ProcessEditor procEditor = ((ProcessEditor)editor);
				String pageId;
				if(object instanceof RoleDescriptor || object instanceof TeamProfile) {
					pageId = ProcessEditor.TA_FORM_ID;
				}
				else if(object instanceof WorkProductDescriptor) {
					pageId = ProcessEditor.WPBS_FORM_ID;
				}
				else {
					pageId = ProcessEditor.WBS_FORM_ID;
				}
				procEditor.setActivePage(pageId);
				procEditor.setSelectionToViewer(Collections.singletonList(object));
			}
		}
	}
	
	private Object expandTree(Object root, List treePath) {
		Object parent = root;
		int size = treePath.size();
		for (int i = 0; i < size; i++) {
			Object e = treePath.get(i);
			treeViewer.setExpandedState(parent, true);
			// get wrapper
			//
			List notifyChangedListeners = TngUtil.getNotifyChangedListeners(adapterFactory, e);
			if (!notifyChangedListeners.isEmpty()) {
				find_wrapper:
				for (Iterator iterator = notifyChangedListeners.iterator(); iterator.hasNext();) {
					Object listener = iterator.next();
					if(listener instanceof FeatureValueWrapperItemProvider
							&& TngUtil.unwrap(listener) == e) {
						FeatureValueWrapperItemProvider wrapper = (FeatureValueWrapperItemProvider) listener;
						for(;wrapper != null && wrapper.getOwner() != parent; 
							wrapper = TngUtil.getWrapper(wrapper.getNotifyChangedListeners(), e)) {									
						}
						if(wrapper != null) {
							parent = wrapper;
							break find_wrapper;
						}
					}
				}
			} else {
				parent = e;
			}
		}
		return parent;

	}
	
	private void expandTreeViewerPackages(EObject e) {
		if (e == null)
			return;
		if (e instanceof ContentPackage || e instanceof ProcessPackage) {
			expandTreeViewerPackages(e.eContainer());
			treeViewer.setExpandedState(e, true);
		}
	}

	/**
	 * Refreshes Library and Configuration views if necessary
	 * 
	 */
	public synchronized void refreshViews() {
		EObject input = (EObject) getViewer().getInput();
		if (input != null) {
			if (input.eIsProxy()) {
				setInputForViewer(RefreshJob.getInstance().resolve(input));
			} else {
				ISelection selection = getViewer().getSelection();
				getViewer().refresh();
				updateLastRefreshTimestamp();
				if (selection instanceof IStructuredSelection) {
					IStructuredSelection structuredSelection = (IStructuredSelection) selection;
					Object o = null;
					if (structuredSelection.size() == 1
							&& (o = structuredSelection.getFirstElement()) instanceof EObject
							&& ((EObject) o).eIsProxy()) {
						o = RefreshJob.getInstance().resolve((EObject) o);
						try {
							setSelectionToViewer(o);
						} catch (Exception e) {
							if (MultiResourceEObject.epfDebug(1)) {
								AuthoringUIPlugin.getDefault().getLogger()
										.logError(e);
							}
						}
					} else {
						ViewHelper.restoreSelection(getViewer(), selection);
					}
				}
			}
		}

		ConfigurationView configView = ConfigurationView.getView();
		if (configView != null) {
			Control ctrl = configView.getViewer().getControl();
			if (ctrl != null && !ctrl.isDisposed()) {
				input = (EObject) configView.getViewer().getInput();
				if (input != null) {
					if (input.eIsProxy()) {
						configView.setInputForViewer(RefreshJob.getInstance()
								.resolve(input));
					} else {
						ISelection selection = configView.getViewer()
								.getSelection();
						configView.getViewer().refresh();
						ViewHelper.restoreSelection(configView.getViewer(), selection);
					}
				}
			}
		}
	}

	private Object[] selectDirtyEditors(List<?> dirtyEditors) {
		return RefreshHandler.selectDirtyEditors(dirtyEditors, getSite().getShell());
	}

	/**
	 * rename an element
	 * 
	 * @param e
	 *            element to rename
	 * @param newName
	 *            the new name
	 */
	public static void runRename(final NamedElement e, final String newName) {
		Display.getDefault().syncExec(new Runnable() {
			public void run() {
				RenameAction.doRename(e, newName);
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.IShowInTarget#show(org.eclipse.ui.part.ShowInContext)
	 */
	public boolean show(ShowInContext context) {
		// special handling for ShowInContext from problems view
		//
//		if (context.getInput() instanceof MarkerAdapter) {
//			IViewPart view = getViewSite().getPage().findView(
//					"org.eclipse.ui.views.ProblemView"); //$NON-NLS-1$
//			ISelectionProvider selectionProvider = view.getViewSite()
//					.getSelectionProvider();
//			StructuredSelection selection = (StructuredSelection) selectionProvider
//					.getSelection();
//			Object o = selection.getFirstElement();
//			if (o instanceof IMarker) {
//				IMarker marker = (IMarker) o;
//				try {
//					String guid = (String) marker
//							.getAttribute(LibraryValidationMarkerHelper.GUID);
//					if (guid != null) {
//						IResource wsRes = marker.getResource();
//						ILibraryManager libMgr = LibraryService.getInstance().getCurrentLibraryManager();						
//						Resource resource = libMgr.getEditingDomain().getResourceSet()
//								.getResource(
//										URI.createFileURI(wsRes.getLocation()
//												.toString()), false);
//						Object element;
//						if(resource != null) {
//							element = resource.getEObject(guid);
//						}
//						else {
//							element = libMgr.getMethodElement(guid);
//						}
//						if(element != null) {
//							setSelectionToViewer(element);
//							return true;
//						}
//					}
//				} catch (CoreException e) {
//					AuthoringUIPlugin.getDefault().getLogger().logError(e);
//				}
//			}
//		}
		ISelection contextSelection = context.getSelection();
		if (context.getSelection() instanceof TreeSelection) {
			// from search results view
			TreeSelection sel = (TreeSelection)context.getSelection();
			setSelectionToViewer(sel.getFirstElement());
		} else if (contextSelection instanceof StructuredSelection) {
			// from problems view, perhaps others?
			StructuredSelection sel = (StructuredSelection)contextSelection;
			Object firstObj = sel.getFirstElement();
			if (firstObj instanceof IResource) {
				IResource wsRes = (IResource)firstObj;
				ILibraryManager libMgr = LibraryService.getInstance().getCurrentLibraryManager();						
				Resource resource = libMgr.getEditingDomain().getResourceSet()
						.getResource(
								URI.createFileURI(wsRes.getLocation()
										.toString()), false);
				Object element = PersistenceUtil.getMethodElement(resource);
				if(element != null) {
					setSelectionToViewer(element);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public String getViewId() {
		return VIEW_ID;
	}

	public TreeViewer getTreeViewer() {
		return treeViewer;
	}
	
	@Override
	public void setContentDescription(String description) {
		super.setContentDescription(description);
	}

	public void refresh(IProgressMonitor monitor) {
		refreshHandler.refresh(monitor);
	}
	
	private boolean customCategoryOwnedByOtherPlugin(Object element) {
		if (!(element instanceof WrapperItemProvider)) {
			return false;
		}
		Object unwrapped = TngUtil.unwrap(element);
		if (! (unwrapped instanceof CustomCategory)) {
			return false;
		}
		CustomCategory cc = (CustomCategory) unwrapped;
		
		while (element instanceof WrapperItemProvider) {
			element = ((WrapperItemProvider) element).getParent(null);
		}
		if (element instanceof CustomCategory) {
			return UmaUtil.getMethodPlugin(cc) != UmaUtil.getMethodPlugin((CustomCategory) element); 
		}
		return false;
	}
	
	public ISelection getCachedSelection() {
		return selection;
	}
}
