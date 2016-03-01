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
package org.eclipse.epf.authoring.ui.editors;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.AbsoluteBendpoint;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.epf.authoring.gef.commands.ChangeBoundsCommand;
import org.eclipse.epf.authoring.gef.commands.CreateBendpointCommand;
import org.eclipse.epf.authoring.gef.commands.CreateLinkCommand;
import org.eclipse.epf.authoring.gef.commands.CreateNodeCommand;
import org.eclipse.epf.authoring.gef.commands.MoveBendpointCommand;
import org.eclipse.epf.authoring.gef.edit.DecisionNodeEditPart;
import org.eclipse.epf.authoring.gef.edit.DiagramActionService;
import org.eclipse.epf.authoring.gef.edit.FreeTextNodeEditPart;
import org.eclipse.epf.authoring.gef.edit.LinkEditPart;
import org.eclipse.epf.authoring.gef.edit.NodeContainerEditPart;
import org.eclipse.epf.authoring.gef.edit.NodeEditPart;
import org.eclipse.epf.authoring.gef.edit.SynchBarNodeEditPart;
import org.eclipse.epf.authoring.gef.figures.FreeTextFigure;
import org.eclipse.epf.authoring.gef.util.DiagramUIResources;
import org.eclipse.epf.authoring.gef.util.TemplateConstants;
import org.eclipse.epf.authoring.ui.AuthoringUIHelpContexts;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.actions.DiagramPrintAction;
import org.eclipse.epf.authoring.ui.views.ViewHelper;
import org.eclipse.epf.diagram.model.Diagram;
import org.eclipse.epf.diagram.model.Link;
import org.eclipse.epf.diagram.model.ModelFactory;
import org.eclipse.epf.diagram.model.NamedNode;
import org.eclipse.epf.diagram.model.Node;
import org.eclipse.epf.diagram.model.TypedNode;
import org.eclipse.epf.diagram.model.util.GraphicalDataHelper;
import org.eclipse.epf.library.ILibraryManager;
import org.eclipse.epf.library.ILibraryServiceListener;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.LibraryServiceUtil;
import org.eclipse.epf.library.configuration.ProcessAuthoringConfigurator;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.events.ILibraryChangeListener;
import org.eclipse.epf.library.ui.actions.ProcessDeleteAction;
import org.eclipse.epf.library.util.ResourceHelper;
import org.eclipse.epf.services.ILibraryPersister;
import org.eclipse.epf.ui.editors.IMethodEditor;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.GraphNode;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.Property;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.WorkProductDescriptor;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.KeyHandler;
import org.eclipse.gef.KeyStroke;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CommandStackEvent;
import org.eclipse.gef.commands.CommandStackEventListener;
import org.eclipse.gef.dnd.TemplateTransferDragSourceListener;
import org.eclipse.gef.dnd.TemplateTransferDropTargetListener;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.CopyTemplateAction;
import org.eclipse.gef.ui.actions.DeleteAction;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.gef.ui.palette.PaletteViewerProvider;
import org.eclipse.gef.ui.palette.FlyoutPaletteComposite.FlyoutPreferences;
import org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette;
import org.eclipse.gef.ui.parts.GraphicalViewerKeyHandler;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.util.SafeRunnable;
import org.eclipse.jface.util.TransferDropTargetListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FontDialog;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;

/**
 * Abstract base class for all diagram editors
 * 
 * @author Phong Nguyen Le
 * @author Jim Thario
 * @author Jinhua Xi
 * @since 1.0
 */
public abstract class AbstractDiagramEditor extends
		GraphicalEditorWithFlyoutPalette implements IMethodEditor {

	private static final String REFRESH_FROM_BASE_ACTIVITY = "refresh_from_base_activity"; //$NON-NLS-1$
	// protected static final String ALIGN_HORZ_AVERAGE =
	// "align_horizontal_to_average"; //$NON-NLS-1$
	// protected static final String ALIGN_HORZ_FIRST_SELECTED =
	// "align_horizontal_to_first_selection"; //$NON-NLS-1$
	// protected static final String ALIGN_VERT_AVERAGE =
	// "align_vertical_to_average"; //$NON-NLS-1$
	// protected static final String ALIGN_VERT_FIRST_SELECTED =
	// "align_vertical_to_first_selection"; //$NON-NLS-1$

	private static final String FONT_DIALOG = "font_dialog"; //$NON-NLS-1$

	private static final String CREATE_LINK = "create_link"; //$NON-NLS-1$

	private static final String SELECT_LINK = "select_link"; //$NON-NLS-1$

	private static final String CREATE_BEND_POINT = "create_bend_point"; //$NON-NLS-1$	

	protected static final String CREATE_FREE_TEXT = "create_free_text"; //$NON-NLS-1$

	public static final String MOVE = "move"; //$NON-NLS-1$

	protected static final String REFRESH = "refresh"; //$NON-NLS-1$

	protected static final String ALIGN_BEND_POINT = "align_bend_point"; //$NON-NLS-1$

	protected static final String PALETTE_DOCK_LOCATION = "Dock location"; //$NON-NLS-1$

	protected static final String PALETTE_SIZE = "Palette Size"; //$NON-NLS-1$

	protected static final String PALETTE_STATE = "Palette state"; //$NON-NLS-1$

	protected static final int DEFAULT_PALETTE_SIZE = 130;

	protected static final String DELETE_DIAGRAM = "delete_actvity_detail_diagram"; //$NON-NLS-1$

	private KeyHandler sharedKeyHandler;

	private PaletteRoot paletteRoot;

	protected EditPart editPart;

	private CommandStackEventListener cmdStackEventListener;

	protected CreationFactory freeTxtNodeCreationFactory;

	protected DiagramActionService actionService = null;

	private boolean disposed;

	/**
	 * The parent is the editor from where the user opened this editor.
	 */
	protected IEditorPart parentEditor = null;

	/**
	 * This is used to listen for our parent editor and our own closing. When it
	 * does, we ask the user if they want to close this diagram as well.
	 */
	protected IPartListener partListener = new IPartListener() {
		IEditorPart me = AbstractDiagramEditor.this;

		public void partOpened(IWorkbenchPart part) {
		}

		public void partDeactivated(IWorkbenchPart part) {
		}

		public void partClosed(IWorkbenchPart part) {
			// check if the editor closed as a result of user action or the
			// workbench closing
			// IWorkbench wb = PlatformUI.getWorkbench();
			if (part == me) {
				setParentEditor(null);
				IWorkbenchPage page = getSite().getPage();
				page.removePartListener(partListener);
				// remove this diagram if it is new
				Diagram diagram = (Diagram) ((EditPart) getGraphicalViewer()
						.getContents()).getModel();
				// make sure we remove the UMA diagram from the model if it is
				// new (never saved)
				if (diagram.isNew()) {
					if (diagram.getUMADiagram() != null) {
					EcoreUtil.remove(diagram.getUMADiagram());
					}
					if(isResourceChangedByOther()) {
					doSave(new NullProgressMonitor());
					}
				}
			}
			// else if (!wb.isClosing()) {
			// // are we being notified about our parent editor object?
			// if (getParentEditor() != null) {
			// if (part == getParentEditor()) {
			// IWorkbenchPage page = getSite().getPage();
			// // yes, ask the user if they want to close this
			// // editor
			// if (AuthoringUIPlugin
			// .getDefault()
			// .getMsgDialog()
			// .displayPrompt(
			// DiagramUIResources
			// .getString("DiagramUI.AbstractDiagramEditor.ParentEditorClose.title"),
			// //$NON-NLS-1$
			// DiagramUIResources
			// .formatString(
			// "DiagramUI.AbstractDiagramEditor.ParentEditorClose.text",
			// getParentEditor().getTitle(), me.getTitle()))) { //$NON-NLS-1$
			// // yes, close this diagram, ask to save changes
			// page.closeEditor(me, true);
			// }
			// // either way, our parent is closed and we can stop
			// // listening
			// page.removePartListener(partListener);
			// }
			// }
			// }
		}

		public void partBroughtToTop(IWorkbenchPart part) {
		}

		public void partActivated(IWorkbenchPart part) {
		}
	};

	private Font font;

	private Color color;

	private Command refreshFromBaseCommand = new Command(DiagramUIResources.AbstractDiagramEditorrefreshfrombase) { 
		private ArrayList oldContent = new ArrayList();

		public void execute() {
			Diagram diagram = (Diagram) editPart.getModel();

			// back up old diagram content
			//
			oldContent.clear();
			oldContent.addAll(diagram.getUMADiagram().getContained());

			GraphicalDataHelper.refreshFromBase(diagram.getUMADiagram());
			if (editPart.isActive()) {
				editPart.deactivate();
			}
			diagram.removeConsumer(this);
			createEditPart();
			getGraphicalViewer().setContents(editPart);
		}

		public void undo() {
			Diagram diagram = (Diagram) editPart.getModel();
			diagram.getUMADiagram().getContained().clear();
			diagram.getUMADiagram().getContained().addAll(oldContent);
			if (editPart.isActive()) {
				editPart.deactivate();
			}
			diagram.removeConsumer(this);
			createEditPart();
			getGraphicalViewer().setContents(editPart);
		}
	};

	protected Map templateNameToCreationFactoryMap;
	/**
	 * Records time when the model first has been changed by this editor. This will be used later
	 * to determine if save is needed to reverse the changes.
	 */
	protected long changeTime = -1;
	protected MethodConfiguration currentConfig;

	class DiagramContextMenuProvider extends ContextMenuProvider {

		private ActionRegistry actionRegistry;

		/**
		 * Creates a new WorkflowContextMenuProvider assoicated with the given
		 * viewer and action registry.
		 * 
		 * @param viewer
		 *            the viewer
		 * @param registry
		 *            the action registry
		 */
		public DiagramContextMenuProvider(EditPartViewer viewer,
				ActionRegistry registry) {
			super(viewer);
			setActionRegistry(registry);
		}

		/**
		 * @see ContextMenuProvider#buildContextMenu(org.eclipse.jface.action.IMenuManager)
		 */
		public void buildContextMenu(IMenuManager menu) {
			GEFActionConstants.addStandardActionGroups(menu);

			IAction action;
			action = getActionRegistry().getAction(ActionFactory.UNDO.getId());
			menu.appendToGroup(GEFActionConstants.GROUP_UNDO, action);

			action = getActionRegistry().getAction(ActionFactory.REDO.getId());
			menu.appendToGroup(GEFActionConstants.GROUP_UNDO, action);

			boolean canModify = !isReadOnly();

			action = getActionRegistry()
					.getAction(ActionFactory.DELETE.getId());
			if (action.isEnabled() && canModify) {
				if (!TngUtil.isLocked(getMethodElementFromInput())) {
					menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);
				}
			}

			action = getActionRegistry().getAction(REFRESH);
			menu.appendToGroup(GEFActionConstants.GROUP_VIEW, action);

			action = getActionRegistry().getAction(FONT_DIALOG);
			if (action.isEnabled() && canModify) {
				menu.appendToGroup(GEFActionConstants.MB_ADDITIONS, action);
			}

			IAction createFreeTextAction = getActionRegistry().getAction(
					CREATE_FREE_TEXT);
			if (createFreeTextAction.isEnabled() && canModify) {
				menu.appendToGroup(GEFActionConstants.MB_ADDITIONS,
						createFreeTextAction);
			}

			// IAction moveAction = getActionRegistry().getAction(MOVE);
			// if(moveAction.isEnabled()){
			// menu.appendToGroup(GEFActionConstants.MB_ADDITIONS,moveAction);
			// }
			IAction linkAction = getActionRegistry().getAction(CREATE_LINK);
			if (linkAction.isEnabled() && canModify) {
				menu.appendToGroup(GEFActionConstants.MB_ADDITIONS, linkAction);
			}
			IAction selectlinkAction = getActionRegistry().getAction(
					SELECT_LINK);
			if (selectlinkAction.isEnabled()) {
				menu.appendToGroup(GEFActionConstants.MB_ADDITIONS,
						selectlinkAction);
			}
			IAction bendpointAction = getActionRegistry().getAction(
					CREATE_BEND_POINT);
			if (bendpointAction.isEnabled() && canModify) {
				menu.appendToGroup(GEFActionConstants.MB_ADDITIONS,
						bendpointAction);
			}
			IAction alignbendpointaction = getActionRegistry().getAction(
					ALIGN_BEND_POINT);
			if (alignbendpointaction.isEnabled() && canModify) {
				menu.appendToGroup(GEFActionConstants.MB_ADDITIONS,
						alignbendpointaction);
			}
			
			//Print Action for diagram through Context Menu.
			// Enable this code when print required for diagram.
//			action = getActionRegistry().getAction(ActionFactory.PRINT.getId());
//			if(action.isEnabled()){
//				menu.appendToGroup(GEFActionConstants.GROUP_PRINT, action);
//			}
			
			contributeToContextMenu(menu);
		}

		private ActionRegistry getActionRegistry() {
			return actionRegistry;
		}

		/**
		 * Sets the action registry
		 * 
		 * @param registry
		 *            the action registry
		 */
		public void setActionRegistry(ActionRegistry registry) {
			actionRegistry = registry;
		}

	}

	/**
	 * Creates an instance
	 *
	 */
	public AbstractDiagramEditor() {
		super();
		setEditDomain(new DefaultEditDomain(this));
		getEditDomain().setCommandStack(new CommandStack() {
			public boolean isDirty() {
				Command cmd = getUndoCommand();
				if (cmd != null && !cmd.canUndo()) {
					return false;
				}
				return super.isDirty();
			}
			
			/* (non-Javadoc)
			 * @see org.eclipse.gef.commands.CommandStack#execute(org.eclipse.gef.commands.Command)
			 */
			public void execute(Command command) {
				if(command == null || !command.canExecute()) {
					return;
				}
				if(changeTime == -1) {
					changeTime = System.currentTimeMillis();
				}
				super.execute(command);
			}
		});

		// add CommandStackEventListener
		//
		cmdStackEventListener = new CommandStackEventListener() {

			public void stackChanged(CommandStackEvent event) {
				if (event.getDetail() == CommandStack.POST_EXECUTE) {
					firePropertyChange(PROP_DIRTY);
				}
			}

		};
		getEditDomain().getCommandStack().addCommandStackEventListener(
				cmdStackEventListener);
		// add our part listener if either parent or this closes
		IWorkbench wb = PlatformUI.getWorkbench();
		IWorkbenchWindow win = wb.getActiveWorkbenchWindow();
		IWorkbenchPage page = win.getActivePage();
		page.addPartListener(partListener);
	}

	/**
	 * 
	 * @see org.eclipse.gef.ui.parts.GraphicalEditor#dispose()
	 */
	public void dispose() {
		getCommandStack()
				.removeCommandStackEventListener(cmdStackEventListener);

		if (isDirty()) {
			// changes discarded, reverse to saved
			//
			reverseToSaved();
		}

		if (editPart != null) {
			Diagram diagram = (Diagram) editPart.getModel();
			if (diagram != null) {
				diagram.removeConsumer(this);
			}
		}

		if (font != null && !font.isDisposed()) {
			font.dispose();
		}
		if (color != null && !color.isDisposed()) {
			color.dispose();
		}
		
		ILibraryManager manager = LibraryService.getInstance().getCurrentLibraryManager();
		if (manager != null) {
			manager.removeListener(libraryListener);
		}
		LibraryService.getInstance().removeListener(libSvcListener);
		
		super.dispose();
		disposed = true;
	}
	
	private boolean isResourceChangedByOther() {
		Resource resource = getMethodElementFromInput().eResource();
		if(resource != null && resource.getURI().isFile()){
			File file = new File(resource.getURI().toFileString());
			return file.lastModified() > changeTime;
		}
		return false;
	}

	/**
	 * 
	 */
	private void reverseToSaved() {
		// save the undone changes only if the resource has been changed outside this editor
		//
		boolean saveNeeded = getCommandStack().isDirty() && isResourceChangedByOther();
		changeTime = -1;
		while (getCommandStack().isDirty()) {
			getCommandStack().undo();
		}
		if (saveNeeded) {
			ILibraryPersister.FailSafeMethodLibraryPersister persister = LibraryServiceUtil.getCurrentPersister().getFailSafePersister();
			try {
				persister.save(getMethodElementFromInput().eResource());
				persister.commit();
			} catch (Exception e) {
				CommonPlugin.INSTANCE.log(e);
				try {
					persister.rollback();
				} catch (Exception ex) {
					ViewHelper.reloadCurrentLibrary(getSite().getShell(), null);
				}
			}
		}
	}

	protected void contributeToContextMenu(IMenuManager menu) {
		boolean canModify = !isReadOnly();
		IAction action = getActionRegistry().getAction(
				REFRESH_FROM_BASE_ACTIVITY);
		if (action.isEnabled() && canModify) {
			menu.appendToGroup(GEFActionConstants.MB_ADDITIONS, action);
		}
		// the align menu is a cascading menu
		IContributionItem ci = createAlignMenu();
		if (ci.isEnabled() && canModify) {
			menu
					.appendToGroup(GEFActionConstants.MB_ADDITIONS,
							new Separator());
			menu.appendToGroup(GEFActionConstants.MB_ADDITIONS, ci);
			menu
					.appendToGroup(GEFActionConstants.MB_ADDITIONS,
							new Separator());
		}
		action = getActionRegistry().getAction(DELETE_DIAGRAM);
		if (action.isEnabled() && canModify) {
			menu
					.appendToGroup(GEFActionConstants.MB_ADDITIONS,
							new Separator());
			menu.appendToGroup(GEFActionConstants.MB_ADDITIONS, action);
			menu
					.appendToGroup(GEFActionConstants.MB_ADDITIONS,
							new Separator());
		}
	}

	protected IContributionItem createAlignMenu() {
		// create a new menu manager for the cascading menu
		MenuManager alignMenuManager = new MenuManager(DiagramUIResources.AbstractDiagramEditor_alignMenu_text) { 

			public boolean isEnabled() {
				int total = 0;
				// enable the menu only if 2 or more nodes or node containers
				// are selected
				List editParts = getGraphicalViewer().getSelectedEditParts();
				// need at least two things selected to align
				if (editParts.size() > 1) {
					for (int a = 0; a < editParts.size(); a++) {
						EditPart editPart = (EditPart) editParts.get(a);
						// we can align nodes and containers
						if (editPart instanceof NodeEditPart
								|| editPart instanceof NodeContainerEditPart) {
							// add up the elements we need, there may be more
							// elements selected (links, etc.)
							total++;
							if (total > 1) {
								// we only need to know there is more than 1, so
								// we can stop here
								break;
							}
						}
					}
				}
				return total > 1;
			}
		};
		// add the actions to this menu
		alignMenuManager.add(getActionRegistry().getAction(
				DiagramActionService.ALIGN_HORZ_AVERAGE));
		alignMenuManager.add(getActionRegistry().getAction(
				DiagramActionService.ALIGN_HORZ_FIRST_SELECTED));
		alignMenuManager.add(getActionRegistry().getAction(
				DiagramActionService.ALIGN_VERT_AVERAGE));
		alignMenuManager.add(getActionRegistry().getAction(
				DiagramActionService.ALIGN_VERT_FIRST_SELECTED));
		return alignMenuManager;
	}

	protected void refreshFromBase() {
		getCommandStack().execute(refreshFromBaseCommand);

		// Diagram diagram = (Diagram) editPart.getModel();
		// GraphicalDataHelper.refreshFromBase(diagram.getUMADiagram());
		// Object act = diagram.getObject();
		// if(editPart.isActive()) {
		// editPart.deactivate();
		// }
		// diagram.removeConsumer(this);
		// editPart = createEditPart(act);
		// getGraphicalViewer().setContents(editPart);
	}

	protected void createActions() {
		super.createActions();

		ActionRegistry registry = getActionRegistry();
		IAction action;

		// replace default delete action with a custom one
		//
		action = registry.getAction(ActionFactory.DELETE.getId());
		if (action != null) {
			registry.removeAction(action);
			getSelectionActions().remove(action);
		}
		action = new DeleteAction((IWorkbenchPart) this) {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.gef.ui.actions.WorkbenchPartAction#isEnabled()
			 */
			public boolean isEnabled() {
				return super.isEnabled();
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.gef.ui.actions.DeleteAction#calculateEnabled()
			 */
			// protected boolean calculateEnabled() {
			// for (Iterator iter = getSelectedObjects().iterator();
			// iter.hasNext();) {
			// Object editPart = iter.next();
			// if(editPart instanceof EditPart) {
			// Object obj = ((EditPart)editPart).getModel();
			// if(obj instanceof NamedNode) {
			// Object element = ((NamedNode)obj).getObject();
			// if(element instanceof BreakdownElement) {
			// return false;
			// }
			// }
			// }
			// }
			// return super.calculateEnabled();
			// }
			// TODO: uncomment this code if we are ready to test deleting
			// BreakdownElement node
			//
			// /* (non-Javadoc)
			// * @see org.eclipse.gef.ui.actions.DeleteAction#run()
			// */
			public void run() {
				List elements = new ArrayList();
				for (Iterator iter = getSelectedObjects().iterator(); iter
						.hasNext();) {
					Object editPart = iter.next();
					if (editPart instanceof EditPart) {
						Object obj = ((EditPart) editPart).getModel();
						if (obj instanceof NamedNode) {
							Object element = ((NamedNode) obj).getObject();
							if (element instanceof BreakdownElement) {
								elements.add(element);
							}
						}
					}
				}
				if (!elements.isEmpty()) {
					ProcessDeleteAction deleteAction = new ProcessDeleteAction() {
						/*
						 * (non-Javadoc)
						 * 
						 * @see org.eclipse.epf.authoring.ui.actions.MethodElementDeleteAction#createCommand(java.util.Collection)
						 */
						public org.eclipse.emf.common.command.Command createCommand(
								Collection selection) {
							domain = null;
							for (Iterator iter = selection.iterator(); iter
									.hasNext();) {
								Object element = iter.next();
								if (element instanceof WorkProductDescriptor) {
									domain = new AdapterFactoryEditingDomain(
											TngAdapterFactory.INSTANCE
													.getPBS_ComposedAdapterFactory(),
											new BasicCommandStack());
									break;
								}
							}
							if (domain == null) {
								domain = new AdapterFactoryEditingDomain(
										TngAdapterFactory.INSTANCE
												.getWBS_ComposedAdapterFactory(),
										new BasicCommandStack());
							}
							return super.createCommand(selection);
						}

					};
					deleteAction.updateSelection(new StructuredSelection(
							elements));
					deleteAction.run();
					if (deleteAction.isDeletionConfirmed()) {
						super.run();

						// save the editor
						// 
						BusyIndicator.showWhile(getEditorSite().getShell()
								.getDisplay(), new Runnable() {

							public void run() {
								doSave(new NullProgressMonitor());
							}
						});
					}

				} else {
					super.run();
				}
			}
		};
		registry.registerAction(action);
		getSelectionActions().add(action.getId());

		action = new CopyTemplateAction(this);
		registry.registerAction(action);

		// refresh diagram from base action
		//
		action = new Action(
				DiagramUIResources.AbstractDiagramEditor_refreshFromBaseActivity_text) { 
			public String getId() {
				return REFRESH_FROM_BASE_ACTIVITY;
			}

			public void run() {
				refreshFromBase();
			}

			public boolean isEnabled() {
				Activity act = (Activity) getMethodElementFromInput();
				return act.getVariabilityBasedOnElement() != null;
			}
		};
		registry.registerAction(action);

		// action = new MatchWidthAction(this);
		// registry.registerAction(action);
		// getSelectionActions().add(action.getId());

		// action = new MatchHeightAction(this);
		// registry.registerAction(action);
		// getSelectionActions().add(action.getId());

		// action = new LogicPasteTemplateAction(this);
		// registry.registerAction(action);
		// getSelectionActions().add(action.getId());

		// action = new IncrementDecrementAction(this, true);
		// registry.registerAction(action);
		// getSelectionActions().add(action.getId());
		//
		// action = new IncrementDecrementAction(this, false);
		// registry.registerAction(action);
		// getSelectionActions().add(action.getId());
		//
		// action = new DirectEditAction((IWorkbenchPart)this);
		// registry.registerAction(action);
		// getSelectionActions().add(action.getId());
		//
		// action = new AlignmentAction((IWorkbenchPart)this,
		// PositionConstants.LEFT);
		// registry.registerAction(action);
		// getSelectionActions().add(action.getId());
		//
		// action = new AlignmentAction((IWorkbenchPart)this,
		// PositionConstants.RIGHT);
		// registry.registerAction(action);
		// getSelectionActions().add(action.getId());
		//
		// action = new AlignmentAction((IWorkbenchPart)this,
		// PositionConstants.TOP);
		// registry.registerAction(action);
		// getSelectionActions().add(action.getId());
		//
		// action = new AlignmentAction((IWorkbenchPart)this,
		// PositionConstants.BOTTOM);
		// registry.registerAction(action);
		// getSelectionActions().add(action.getId());
		//
		// action = new AlignmentAction((IWorkbenchPart)this,
		// PositionConstants.CENTER);
		// registry.registerAction(action);
		// getSelectionActions().add(action.getId());
		//
		// action = new AlignmentAction((IWorkbenchPart)this,
		// PositionConstants.MIDDLE);
		// registry.registerAction(action);
		// getSelectionActions().add(action.getId());

		IAction action1 = new Action(
				DiagramUIResources.AbstractDiagramEditor_formatTextBoxActivity_text) { 
			public String getId() {
				return FONT_DIALOG;
			}

			public void run() {
				try {
					try {
						EditPart selectedEditPart = (EditPart) getGraphicalViewer()
								.getSelectedEditParts().get(0);
						
						FontDialog fd = new FontDialog(PlatformUI
								.getWorkbench().getActiveWorkbenchWindow()
								.getShell());
						// Restoring old values.
						if(selectedEditPart != null){
							restoreFontData(selectedEditPart, fd);
						}
						
						fd.open();
						if (selectedEditPart instanceof FreeTextNodeEditPart) {
							if (fd.getFontList() != null) {
								FontData fData = fd.getFontList()[0];
								if (fData != null) {
									if (font != null && !font.isDisposed()) {
										font.dispose();
									}
									// if(color != null && !font.isDisposed()){
									// color.dispose();
									// }
									font = new Font(PlatformUI.getWorkbench()
											.getDisplay(), fData);
									
									if(fd.getRGB() != null)
									color = new Color(null, fd.getRGB());
									
									((FreeTextFigure) ((FreeTextNodeEditPart) selectedEditPart)
											.getFigure()).setFont(font);
									
									if(color != null)
									((FreeTextFigure) ((FreeTextNodeEditPart) selectedEditPart)
											.getFigure())
											.setForegroundColor(color);

									GraphNode graphNode = ((Node) selectedEditPart
											.getModel()).getGraphNode();
									List list = graphNode
											.getList(UmaPackage.GRAPH_NODE__PROPERTY);

									String text = ((FreeTextFigure) ((FreeTextNodeEditPart) selectedEditPart)
											.getFigure()).getText();
									Property property = getPropertyByKey(
											list,
											GraphicalDataHelper.GRAPH_NODE_FREE_TEXT);
									if (property != null) {
										property.setValue(text);
										list.add(property);
									}
									setProperty(
											list,
											TemplateConstants.PROPERTY_FONT_NAME,
											fData.getName());
									setProperty(
											list,
											TemplateConstants.PROPERTY_FONT_STYLE,
											new Integer(fData.getStyle())
													.toString());
									setProperty(
											list,
											TemplateConstants.PROPERTY_FONT_HEIGHT,
											new Integer(fData.getHeight())
													.toString());
									if(fd.getRGB() != null){
									setProperty(
											list,
											TemplateConstants.PROPERTY_FONT_RED,
											new Integer(fd.getRGB().red)
													.toString());
									setProperty(
											list,
											TemplateConstants.PROPERTY_FONT_BLUE,
											new Integer(fd.getRGB().blue)
													.toString());
									setProperty(
											list,
											TemplateConstants.PROPERTY_FONT_GREEN,
											new Integer(fd.getRGB().green)
													.toString());
									}
									selectedEditPart.refresh();
								}
							}
						}

					} catch (Exception exception) {
						exception.printStackTrace();
					}
				} catch (RuntimeException e) {
					e.printStackTrace();
				}

			}

			public boolean isEnabled() {
				List editParts = getGraphicalViewer().getSelectedEditParts();
				if (editParts.size() != 1)
					return false;
				EditPart editPart = (EditPart) editParts.get(0);
				if (editPart.getModel() instanceof TypedNode) {
					return (((TypedNode) editPart.getModel()).getType() == TypedNode.FREE_TEXT);
				}
				return false;
			}

			public Property getPropertyByKey(List list, String key) {
				if (!list.isEmpty()) {
					for (Iterator iror = list.iterator(); iror.hasNext();) {
						Property property = (Property) iror.next();
						if (property != null) {
							if (property.getKey().equals(key)) {
								return property;
							} 
						}
					}
				}
				return null;
			}

			public void setProperty(List list, String key, String value) {

				Property property = getPropertyByKey(list, key);
				if (property != null) {
					property.setValue(value);

				} else {
					property = UmaFactory.eINSTANCE.createProperty();
					property.setKey(key);
					property.setValue(value);
				}
				list.add(property);
			}
			public void restoreFontData(EditPart selectedEditPart, FontDialog fd){
				GraphNode graphNode = ((Node) selectedEditPart
						.getModel()).getGraphNode();
					List list = graphNode
						.getList(UmaPackage.GRAPH_NODE__PROPERTY);
					if(list != null && !list.isEmpty()){
						Property property = null;
						String fontname = null, fontStyle = null, fontHeight = null, red = null, blue = null, green = null;

						property = getPropertyByKey(list, TemplateConstants.PROPERTY_FONT_NAME);
						if(property != null ){
							fontname = property.getValue();
						}else{
							return;
						}
						
						property = getPropertyByKey(list, TemplateConstants.PROPERTY_FONT_STYLE);
						if(property != null ){
							fontStyle = property.getValue();
						}
						property = getPropertyByKey(list, TemplateConstants.PROPERTY_FONT_HEIGHT);
						if(property != null){
							fontHeight = property.getValue();
						}
						property = getPropertyByKey(list, TemplateConstants.PROPERTY_FONT_RED);
						if(property != null){
							red = property.getValue();
						}
						property = getPropertyByKey(list, TemplateConstants.PROPERTY_FONT_BLUE);
						if(property != null){
							blue = property.getValue();
						}
						property = getPropertyByKey(list, TemplateConstants.PROPERTY_FONT_GREEN);
						if(property != null){
							green = property.getValue();
						}
						
						FontData fData = new FontData();
						fData.setName(fontname);
						fData.setHeight((new Integer(fontHeight)).intValue());
						fData.setStyle((new Integer(fontStyle)).intValue());
						
						//, (new Integer(fontStyle)).intValue(), (new Integer(fontHeight)).intValue());
						if( red != null && blue != null && green != null){
							RGB rgb = new RGB(new Integer(red).intValue(),
									new Integer(green).intValue(),
									 new Integer(blue).intValue()
									 );
							fd.setRGB(rgb);
//							if(rgb != null){
//								rgb.red = new Integer(red).intValue();
//								rgb.blue = new Integer(blue).intValue();
//								rgb.green = new Integer(green).intValue();
//							}
						}
						fd.setFontList(new FontData[]{fData});
					}
			}
		};
		getActionRegistry().registerAction(action1);

		// Jinhua Xi
		// added this to consolidate common actions into a service class
		actionService = new DiagramActionService(getGraphicalViewer(),
				getEditDomain(), registry);
		actionService.registerHorizontalAlignAverageAction();
		actionService.registerHorizontalAlignFirstSelectedAction();
		actionService.registerVerticalAlignAverageAction();
		actionService.registerVerticalAlignFirstSelectedAction();

		// // align horizontally to average y-value of all nodes
		// IAction hAlignAverageAction = new Action(
		// DiagramUIResources
		// .getString("DiagramUI.AbstractDiagramEditor.hAlignAverageAction.text"))
		// { //$NON-NLS-1$
		// public void run() {
		// horizAlignToAverageSelected();
		// }
		//
		// public String getId() {
		// return ALIGN_HORZ_AVERAGE;
		// }
		// };
		// getActionRegistry().registerAction(hAlignAverageAction);
		//
		// // align horizontally to y-value of first selected node
		// IAction hAlignFirstSelectedAction = new Action(
		// DiagramUIResources
		// .getString("DiagramUI.AbstractDiagramEditor.hAlignFirstSelectedAction.text"))
		// { //$NON-NLS-1$
		// public void run() {
		// horzAlignToFirstSelected();
		// }
		//
		// public String getId() {
		// return ALIGN_HORZ_FIRST_SELECTED;
		// }
		// };
		// getActionRegistry().registerAction(hAlignFirstSelectedAction);
		//
		// // align vertically to average x-value of all selected nodes
		// IAction vAlignAverageAction = new Action(
		// DiagramUIResources
		// .getString("DiagramUI.AbstractDiagramEditor.vAlignAverageAction.text"))
		// { //$NON-NLS-1$
		// public void run() {
		// verticalAlignToAverageSelected();
		// }
		//
		// public String getId() {
		// return ALIGN_VERT_AVERAGE;
		// }
		// };
		// getActionRegistry().registerAction(vAlignAverageAction);
		//
		// // align vertically to x-value of first selected node
		// IAction vAlignFirstSelectedAction = new Action(
		// DiagramUIResources
		// .getString("DiagramUI.AbstractDiagramEditor.vAlignFirstSelectedAction.text"))
		// { //$NON-NLS-1$
		// public void run() {
		// verticalAlignToFirstSelected();
		// }
		//
		// public String getId() {
		// return ALIGN_VERT_FIRST_SELECTED;
		// }
		// };
		// getActionRegistry().registerAction(vAlignFirstSelectedAction);

		action = createAnAction(
				DiagramUIResources.AbstractDiagram_FreeText_text, freeTxtNodeCreationFactory, 
				CREATE_FREE_TEXT,
				DiagramUIResources.AbstractDiagram_FreeText_tooltip, 
				AuthoringUIPlugin.getDefault().getImageDescriptor(
						"freetext_pal16.gif")); //$NON-NLS-1$
		getActionRegistry().registerAction(action);

		action = new Action(DiagramUIResources.moveAction_label) { 

			public void setImageDescriptor(ImageDescriptor newImage) {
				super.setImageDescriptor(newImage);
			}

			public void runWithEvent(Event event) {
				super.runWithEvent(event);

			}

			public void run() {
				super.run();
			}

			public boolean isEnabled() {
				List editParts = getGraphicalViewer().getSelectedEditParts();
				if (editParts.size() > 0)
					return true;
				return false;
			}

			public String getId() {
				return MOVE;
			}

		};
		getActionRegistry().registerAction(action);

		IAction linkAction = new Action(DiagramUIResources.AbstractDiagram_Link_text) { 
			public void run() {
				List editParts = getGraphicalViewer().getSelectedEditParts();
				if (editParts.size() == 2) {
					EditPart sourceEditPart = (EditPart) editParts.get(0);
					EditPart targetEditPart = (EditPart) editParts.get(1);
					List targetList = new ArrayList();
					if (sourceEditPart instanceof FreeTextNodeEditPart
							|| targetEditPart instanceof FreeTextNodeEditPart)
						return;
					if (sourceEditPart instanceof NodeEditPart) {
						targetList = ((NodeEditPart) sourceEditPart)
								.getSourceConnections();
						if (!targetList.isEmpty()) {
							for (Iterator itor = targetList.iterator(); itor
									.hasNext();) {
								LinkEditPart obj = (LinkEditPart) itor.next();
								if (targetEditPart.equals(obj.getSource()))
									return;
								if (targetEditPart.equals(obj.getTarget()))
									return;
							}
						}
						targetList = ((NodeEditPart) sourceEditPart)
								.getTargetConnections();
						if (!targetList.isEmpty()) {
							for (Iterator itor = targetList.iterator(); itor
									.hasNext();) {
								LinkEditPart obj = (LinkEditPart) itor.next();
								if (targetEditPart.equals(obj.getSource()))
									return;
								if (targetEditPart.equals(obj.getTarget()))
									return;
							}
						}
					}
					Link link = ModelFactory.eINSTANCE.createLink();
					if (sourceEditPart.getModel() instanceof Node)
						link.setSource((Node) sourceEditPart.getModel());
					if (targetEditPart.getModel() instanceof Node)
						link.setTarget((Node) targetEditPart.getModel());
					org.eclipse.gef.commands.Command c = new CreateLinkCommand(
							link, (Node) sourceEditPart.getModel());
					getCommandStack().execute(c);
				}
			}

			public boolean isEnabled() {
				List editParts = getGraphicalViewer().getSelectedEditParts();
				for (Iterator itor = editParts.iterator(); itor.hasNext();) {
					Object part = itor.next();
					if (part instanceof FreeTextNodeEditPart)
						return false;
				}
				if (editParts.size() == 2)
					return true;
				return false;
			}

			public String getId() {
				return CREATE_LINK;
			}
		};
		getActionRegistry().registerAction(linkAction);

		IAction selectLinkAction = new Action(DiagramUIResources.AbstractDiagram_Link_select) { 

			public void run() {
				List editParts = getGraphicalViewer().getSelectedEditParts();
				if (editParts.size() == 1) {
					EditPart part = (EditPart) editParts.get(0);
					if (part instanceof NodeEditPart) {
						List list = ((NodeEditPart) part)
								.getSourceConnections();
						if (list != null && !list.isEmpty()) {
							LinkEditPart linkpart = (LinkEditPart) list.get(0);
							linkpart.setSelected(1);
							getGraphicalViewer().select(linkpart);
							// part.setSelected(0);
						}
					}
				}
			}

			public boolean isEnabled() {
				List editParts = getGraphicalViewer().getSelectedEditParts();
				if (editParts.size() == 1) {
					return true;
				}
				return false;
			}

			public String getId() {
				return SELECT_LINK;
			}

		};
		getActionRegistry().registerAction(selectLinkAction);

		IAction createBendPointAction = new Action(DiagramUIResources.AbstractDiagram_BendPoint_create) { 

			public void run() {
				List editParts = getGraphicalViewer().getSelectedEditParts();
				if (editParts.size() == 1) {
					EditPart part = (EditPart) editParts.get(0);
					if (part instanceof LinkEditPart) {
						// return true;
						Link link = (Link) ((LinkEditPart) part).getModel();
						List bendpoints = link.getBendpoints();
						if (bendpoints == null || bendpoints.size() < 1) {
							Point sp = link.getSourceEndPoint();
							Point tp = link.getTargetEndPoint();
							if (sp == null) {
								if (link.getSource() == null) {
									sp = ((Node) link.eContainer())
											.getLocation();
								} else {
									sp = link.getSource().getLocation();
								}
							}
							if (tp == null) {
								tp = link.getTarget().getLocation();
							}
							Point bPoint = new Point();
							bPoint.x = (sp.x + tp.x) / 2;
							bPoint.y = (sp.y + tp.y) / 2;
							org.eclipse.gef.commands.Command c = new CreateBendpointCommand(
									link, bPoint, 0);
							getCommandStack().execute(c);

						}
					}
				}
			}

			public boolean isEnabled() {
				List editParts = getGraphicalViewer().getSelectedEditParts();
				if (editParts.size() == 1) {
					EditPart part = (EditPart) editParts.get(0);
					if (part instanceof LinkEditPart) {
						return true;
					}
				}
				return false;
			}

			public String getId() {
				return CREATE_BEND_POINT;
			}
		};
		getActionRegistry().registerAction(createBendPointAction);

		IAction refreshAction = new Action(DiagramUIResources.refreshAction_label) { 
			public void run() {
				Diagram diagram = (Diagram) editPart.getModel();
				if (editPart.isActive()) {
					editPart.deactivate();
				}
				diagram.removeConsumer(AbstractDiagramEditor.this);
				createEditPart();
				getGraphicalViewer().setContents(editPart);
			}

			public String getId() {
				return REFRESH;
			}
		};
		getActionRegistry().registerAction(refreshAction);
		IAction deleteDiagramAction = new Action(DiagramUIResources.DeleteDiagram_text) { 

			public void run() {
				if (AuthoringUIPlugin
						.getDefault()
						.getMsgDialog()
						.displayPrompt(
								DiagramUIResources.DeleteDiagram_text, 
								DiagramUIResources.DeleteDiagram_prompt)) { 
					Diagram diagram = (Diagram) ((EditPart) getGraphicalViewer()
							.getContents()).getModel();
					EcoreUtil.remove(diagram.getUMADiagram());
					doSave(new NullProgressMonitor());
					getSite().getPage().closeEditor(
							getSite().getPage().getActiveEditor(), false);
				}
			}

			public boolean isEnabled() {
				return getGraphicalViewer().getSelectedEditParts().size() == 0;
			}

			public String getId() {
				return DELETE_DIAGRAM;
			}

		};
		getActionRegistry().registerAction(deleteDiagramAction);
		IAction alignBendPointAction = new Action(DiagramUIResources.align_bend_point_text) { 

			public void run() {
				EditPart part = (EditPart) getGraphicalViewer()
						.getSelectedEditParts().get(0);
				if (part instanceof LinkEditPart) {
					LinkEditPart linkpart = ((LinkEditPart) part);
					Link link = (Link) linkpart.getModel();
					if (link.getBendpoints().size() > 0) {

						GraphicalEditPart spart = ((GraphicalEditPart) linkpart
								.getSource());
						GraphicalEditPart tpart = ((GraphicalEditPart) linkpart
								.getTarget());

						Point sp = spart.getFigure().getBounds().getCenter();
						Point tp = tpart.getFigure().getBounds().getCenter();

						if (spart instanceof DecisionNodeEditPart
								|| spart instanceof SynchBarNodeEditPart) {
							sp = ((TypedNode) spart.getModel()).getLocation();
							List list = ((NodeEditPart) spart)
									.getSourceConnections();
							for (Iterator itor = list.iterator(); itor
									.hasNext();) {
								LinkEditPart lp = (LinkEditPart) itor.next();
								if (lp.getTarget().equals(tpart)) {
									Point sourceEndPoint = ((Link) lp
											.getModel()).getSourceEndPoint();
									sp = sp.getTranslated(sourceEndPoint);
								}
							}
						}

						if (tpart instanceof DecisionNodeEditPart
								|| tpart instanceof SynchBarNodeEditPart) {
							tp = ((TypedNode) tpart.getModel()).getLocation();
							List list = ((NodeEditPart) tpart)
									.getTargetConnections();
							for (Iterator itor = list.iterator(); itor
									.hasNext();) {
								LinkEditPart lp = (LinkEditPart) itor.next();
								if (lp.getSource().equals(spart)) {
									Point targetEndPoint = ((Link) lp
											.getModel()).getTargetEndPoint();
									tp = tp.getTranslated(targetEndPoint);
								}
							}
						}
						for (int i = 0; i < link.getBendpoints().size(); i++) {
							AbsoluteBendpoint oldbp = (AbsoluteBendpoint) link
									.getBendpoints().get(i);
							AbsoluteBendpoint bp = new AbsoluteBendpoint(
									new Point(-1, -1));
							AbsoluteBendpoint previousbp = null;
							int delta = 40;
							int position = 0;

							if (i == 0) {
								position = sp.getPosition(oldbp);
							} else {
								previousbp = (AbsoluteBendpoint) link
										.getBendpoints().get(i - 1);
								position = previousbp.getPosition(oldbp);
							}

							if (position == PositionConstants.SOUTH
									|| position == PositionConstants.NORTH) {
								if (i == 0) {
									if (sp.x - delta < oldbp.x
											&& sp.x + delta > oldbp.x
											&& !(sp.x == oldbp.x)) {
										bp.x = sp.x;
									}
								} else {
									if (previousbp.x - delta < oldbp.x
											&& previousbp.x + delta > oldbp.x
											&& !(previousbp.x == oldbp.x)) {
										bp.x = previousbp.x;
									}
								}
								if (link.getBendpoints().size() - 1 == i) {
									if (tp.y - delta < oldbp.y
											&& tp.y + delta > oldbp.y
											&& !(tp.y == oldbp.y)) {
										bp.y = tp.y;
									}
								} else {
									bp.y = oldbp.y;
								}
							}
							if (position == PositionConstants.EAST
									|| position == PositionConstants.WEST) {

								if (i == 0) {
									if (sp.y - delta < oldbp.y
											&& sp.y + delta > oldbp.y
											&& !(sp.y == oldbp.y)) {
										bp.y = sp.y;
									}
								} else {
									if (previousbp.y - delta < oldbp.y
											&& previousbp.y + delta > oldbp.y
											&& !(previousbp.y == oldbp.y)) {
										bp.y = previousbp.y;
									}
								}
								if (link.getBendpoints().size() - 1 == i) {
									if (tp.x - delta < oldbp.x
											&& tp.x + delta > oldbp.x
											&& !(tp.x == oldbp.x)) {
										bp.x = tp.x;
									}
								} else {
									bp.x = oldbp.x;
								}
							}
							if (bp.x == -1 && bp.y != -1) {
								org.eclipse.gef.commands.Command c = new MoveBendpointCommand(
										link, new Point(oldbp.x, bp.y), i);
								// ((RootEditPart)getRoot()).getViewer().getEditDomain().getCommandStack().execute(c);
								getCommandStack().execute(c);
							}
							if (bp.x != -1 && bp.y == -1) {
								org.eclipse.gef.commands.Command c = new MoveBendpointCommand(
										link, new Point(bp.x, oldbp.y), i);
								getCommandStack().execute(c);
							}
							if (bp.x != -1 && bp.y != -1) {
								org.eclipse.gef.commands.Command c = new MoveBendpointCommand(
										link, new Point(bp.x, bp.y), i);
								getCommandStack().execute(c);
							}
						}
					}
				}
			}

			public boolean isEnabled() {
				List list = getGraphicalViewer().getSelectedEditParts();
				if (list.size() == 1) {
					if (list.get(0) instanceof LinkEditPart) {
						LinkEditPart part = (LinkEditPart) list.get(0);
						Link link = (Link) part.getModel();
						if (link.getBendpoints().size() > 0)
							return true;
					}
				}
				return false;
			}

			public String getId() {
				return ALIGN_BEND_POINT;
			}

		};
		
		// Viewer Print Action
		action = new DiagramPrintAction(this, getGraphicalViewer());
		registry.registerAction(action);
		getActionRegistry().registerAction(alignBendPointAction);
	}

	protected IAction createAnAction(String actionName,
			CreationFactory creationFactory, String actionId,
			String tooltipString, ImageDescriptor imagedesc) {
		final CreationFactory factory = creationFactory;
		final String id = actionId;
		final String tooltip = tooltipString;
		final ImageDescriptor imagedescriptor = imagedesc;
		return new Action(actionName) { 

			public void run() {
				CreateRequest request = new CreateRequest();
				// CreationFactory factory = getFactory(template);
				if (factory == null)
					return;
				request.setFactory(factory);
				request.setLocation(new Point(10, 10));

				if (request.getNewObject() instanceof Node) {
					Point loc = request.getLocation();
					Diagram parent = (Diagram) editPart.getModel();
					org.eclipse.gef.commands.Command c = new CreateNodeCommand(
							(Node) request.getNewObject(), parent, loc);
					getCommandStack().execute(c);
				}
			}

			public String getId() {
				return id;
			}

			public boolean isEnabled() {
				if (TngUtil.isLocked(getMethodElementFromInput())) {
					return false;
				} else
					return true;
			}

			public String getToolTipText() {
				return tooltip;
			}

			public void setImageDescriptor(
					org.eclipse.jface.resource.ImageDescriptor newImage) {
				super.setImageDescriptor(imagedescriptor);
			}

			public ImageDescriptor getImageDescriptor() {
				return imagedescriptor;
			}
		};
	}

	protected PaletteViewerProvider createPaletteViewerProvider() {
		return new PaletteViewerProvider(getEditDomain()) {
			private IMenuListener menuListener;

			protected void configurePaletteViewer(PaletteViewer viewer) {
				super.configurePaletteViewer(viewer);
				// TODO viewer.setCustomizer(new LogicPaletteCustomizer());
				viewer
						.addDragSourceListener(new TemplateTransferDragSourceListener(
								viewer));
			}

			protected void hookPaletteViewer(PaletteViewer viewer) {
				super.hookPaletteViewer(viewer);
				final CopyTemplateAction copy = (CopyTemplateAction) getActionRegistry()
						.getAction(ActionFactory.COPY.getId());
				viewer.addSelectionChangedListener(copy);
				if (menuListener == null)
					menuListener = new IMenuListener() {
						public void menuAboutToShow(IMenuManager manager) {
							manager.appendToGroup(
									GEFActionConstants.GROUP_COPY, copy);
						}
					};
				viewer.getContextMenu().addMenuListener(menuListener);
			}
		};
	}

	/**
	 * 
	 * @see org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette#getPalettePreferences()
	 */
	protected FlyoutPreferences getPalettePreferences() {
		return new FlyoutPreferences() {
			public int getDockLocation() {
				return AuthoringUIPlugin.getDefault().getPreferenceStore()
						.getInt(PALETTE_DOCK_LOCATION);
			}

			public int getPaletteState() {
				return AuthoringUIPlugin.getDefault().getPreferenceStore()
						.getInt(PALETTE_STATE);
			}

			public int getPaletteWidth() {
				return AuthoringUIPlugin.getDefault().getPreferenceStore()
						.getInt(PALETTE_SIZE);
			}

			public void setDockLocation(int location) {
				AuthoringUIPlugin.getDefault().getPreferenceStore().setValue(
						PALETTE_DOCK_LOCATION, location);
			}

			public void setPaletteState(int state) {
				AuthoringUIPlugin.getDefault().getPreferenceStore().setValue(
						PALETTE_STATE, state);
			}

			public void setPaletteWidth(int width) {
				AuthoringUIPlugin.getDefault().getPreferenceStore().setValue(
						PALETTE_SIZE, width);
			}
		};
	}

	protected KeyHandler getCommonKeyHandler() {
		if (sharedKeyHandler == null) {
			sharedKeyHandler = new KeyHandler() {

				public boolean keyPressed(KeyEvent event) {
					if ((event.stateMask == SWT.CTRL)
							&& (event.keyCode == SWT.ARROW_RIGHT)) {
						move(1);
					}
					if ((event.stateMask == SWT.CTRL)
							&& (event.keyCode == SWT.ARROW_DOWN)) {
						move(2);

					}
					if ((event.stateMask == SWT.CTRL)
							&& (event.keyCode == SWT.ARROW_UP)) {
						move(3);
					}
					if ((event.stateMask == SWT.CTRL)
							&& (event.keyCode == SWT.ARROW_LEFT)) {
						move(4);
					}

					return super.keyPressed(event);
				}

				public boolean move(int direction) {
					List list = getGraphicalViewer().getSelectedEditParts();
					GraphicalEditPart part = (GraphicalEditPart) list.get(0);
					if (part.getModel() instanceof Node) {
						Node node = (Node) part.getModel();
						Point point = node.getLocation();
						int newx = point.x;
						int newy = point.y;
						int presslength = 1;
						switch (direction) {
						case 1:
							// node.setLocation(new Point(point.x+10,point.y));
							newx = point.x + presslength;
							break;
						case 2:
							// node.setLocation(new Point(point.x,point.y+10));
							newy += presslength;
							break;
						case 3:
							// node.setLocation(new Point(point.x,point.y-10));
							newy -= presslength;
							break;
						case 4:
							// node.setLocation(new Point(point.x-10,point.y));
							newx -= presslength;
							break;
						default:
							break;
						}
						org.eclipse.gef.commands.Command c = new ChangeBoundsCommand(
								node, new Point(newx, newy), node.getWidth());
						getCommandStack().execute(c);
						return true;
					}
					// TODO- check adding more bendpoint, capture bendpoint
					// index on fly
					if (part.getModel() instanceof Link) {
						Link link = (Link) part.getModel();
						List blist = link.getBendpoints();
						int index = 0;
						if (blist != null && blist.size() > 0) {
							AbsoluteBendpoint point = (AbsoluteBendpoint) blist
									.get(index);
							int newx = point.x;
							int newy = point.y;
							int presslength = 5;
							switch (direction) {
							case 1:
								// node.setLocation(new
								// Point(point.x+10,point.y));
								newx = point.x + presslength;
								break;
							case 2:
								// node.setLocation(new
								// Point(point.x,point.y+10));
								newy += presslength;
								break;
							case 3:
								// node.setLocation(new
								// Point(point.x,point.y-10));
								newy -= presslength;
								break;
							case 4:
								// node.setLocation(new
								// Point(point.x-10,point.y));
								newx -= presslength;
								break;
							default:
								break;
							}
							org.eclipse.gef.commands.Command c = new MoveBendpointCommand(
									link, new Point(newx, newy), index);
							getCommandStack().execute(c);
						}
					}
					return true;
				}

			};
			sharedKeyHandler
					.put(KeyStroke.getPressed(SWT.DEL, 127, 0),
							getActionRegistry().getAction(
									ActionFactory.DELETE.getId()));
			sharedKeyHandler.put(KeyStroke.getPressed(SWT.F2, 0),
					getActionRegistry().getAction(
							GEFActionConstants.DIRECT_EDIT));

		}
		return sharedKeyHandler;
	}

	/**
	 * Sets the graphicalViewer for this EditorPart.
	 * 
	 * @param viewer
	 *            the graphical viewer
	 */
	protected void setGraphicalViewer(GraphicalViewer viewer) {
		super.setGraphicalViewer(viewer);
		if (actionService != null) {
			actionService.setGraphicalViewer(viewer);
		}
	}

	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();
		ScrollingGraphicalViewer viewer = (ScrollingGraphicalViewer) getGraphicalViewer();

		ScalableFreeformRootEditPart root = new ScalableFreeformRootEditPart();

		// List zoomLevels = new ArrayList(3);
		// zoomLevels.add(ZoomManager.FIT_ALL);
		// zoomLevels.add(ZoomManager.FIT_WIDTH);
		// zoomLevels.add(ZoomManager.FIT_HEIGHT);
		// root.getZoomManager().setZoomLevelContributions(zoomLevels);
		//
		// IAction zoomIn = new ZoomInAction(root.getZoomManager());
		// IAction zoomOut = new ZoomOutAction(root.getZoomManager());
		// getActionRegistry().registerAction(zoomIn);
		// getActionRegistry().registerAction(zoomOut);
		// getSite().getKeyBindingService().registerAction(zoomIn);
		// getSite().getKeyBindingService().registerAction(zoomOut);

		viewer.setRootEditPart(root);

		// viewer.setEditPartFactory(new MethodElementEditPartFactory());
		viewer.setEditPartFactory(createEditPartFactory());

		ContextMenuProvider provider = new DiagramContextMenuProvider(
				getGraphicalViewer(), getActionRegistry());
		getGraphicalViewer().setContextMenu(provider);
		getSite().registerContextMenu(
				"org.eclipse.epf.authoring.ui.editors.workflow.contextmenu", //$NON-NLS-1$
				provider, getGraphicalViewer());

		viewer.setKeyHandler(new GraphicalViewerKeyHandler(viewer)
				.setParent(getCommonKeyHandler()));

		// loadProperties();

		// Actions
		// IAction showRulers = new
		// ToggleRulerVisibilityAction(getGraphicalViewer());
		// getActionRegistry().registerAction(showRulers);
		//    	
		// IAction snapAction = new
		// ToggleSnapToGeometryAction(getGraphicalViewer());
		// getActionRegistry().registerAction(snapAction);
		//
		// IAction showGrid = new ToggleGridAction(getGraphicalViewer());
		// getActionRegistry().registerAction(showGrid);

		Listener listener = new Listener() {
			public void handleEvent(Event event) {
				handleActivationChanged(event);
			}
		};

		getGraphicalControl().addListener(SWT.Activate, listener);
		getGraphicalControl().addListener(SWT.Deactivate, listener);

		getGraphicalControl().addListener(SWT.MouseDoubleClick, new Listener() {

			public void handleEvent(Event event) {
				handleDoubleClick(event);
			}

		});
		if (getDiagramType().equalsIgnoreCase(
				ResourceHelper.DIAGRAM_TYPE_ACTIVITY_DETAIL))
			PlatformUI
					.getWorkbench()
					.getHelpSystem()
					.setHelp(
							getGraphicalControl(),
							AuthoringUIHelpContexts.FORM_EDITOR_ACTIVITY_DETAIL_DIAGRAM_CONTEXT);
		else if (getDiagramType().equalsIgnoreCase(
				ResourceHelper.DIAGRAM_TYPE_WORKFLOW))
			PlatformUI.getWorkbench().getHelpSystem().setHelp(
					getGraphicalControl(), AuthoringUIHelpContexts.FORM_EDITOR_ACTIVITY_DIAGRAM_CONTEXT); 
		else if (getDiagramType().equalsIgnoreCase(
				ResourceHelper.DIAGRAM_TYPE_WP_DEPENDENCY))
			PlatformUI
					.getWorkbench()
					.getHelpSystem()
					.setHelp(getGraphicalControl(), AuthoringUIHelpContexts.FORM_EDITOR_WP_DEPENDENCY_DIAGRAM_CONTEXT);

		Display display = getGraphicalControl().getDisplay();
		if (!(display == null || display.isDisposed())) {
			display.asyncExec(new Runnable() {
				public void run() {
					if (getPaletteRoot() != null) {
						Object obj = getMethodElementFromInput();
						if (TngUtil.isLocked((EObject) obj)) {
							List list = getPaletteRoot().getChildren();
							for (Iterator itor = list.iterator(); itor
									.hasNext();) {
								PaletteEntry entry = (PaletteEntry) itor.next();
								entry.setVisible(false);
							}

						} else {
							List list = getPaletteRoot().getChildren();
							for (Iterator itor = list.iterator(); itor
									.hasNext();) {
								PaletteEntry entry = (PaletteEntry) itor.next();
								entry.setVisible(true);
							}
						}
					}
				}
			});
		}
		
		currentConfig = LibraryService.getInstance().getCurrentMethodConfiguration();
		
		// listen to change for current selection of MethodConfiguration or changes in 
		// current configuration.
		//
		LibraryService.getInstance().addListener(libSvcListener);
		ILibraryManager manager = LibraryService.getInstance().getCurrentLibraryManager();
		if (manager != null) {
			manager.addListener(libraryListener);
		}
		
		IAction action = getActionRegistry().getAction(ActionFactory.PRINT.getId());
		if(action instanceof DiagramPrintAction){
			((DiagramPrintAction)action).setViewer(viewer);
		}

	}

	/**
	 * @return
	 */
	protected abstract EditPartFactory createEditPartFactory();

	/**
	 * @param event
	 */
	protected void handleDoubleClick(Event event) {
	}

	protected void initializeGraphicalViewer() {
		super.initializeGraphicalViewer();
		getGraphicalViewer().setContents(editPart);

		TransferDropTargetListener listener = createDropTargetListener();
		if (listener != null) {
			getGraphicalViewer().addDropTargetListener(listener);
		}

		// AbstractEditPartViewer
		// getGraphicalViewer().addDropTargetListener(
		// new TextTransferDropTargetListener(getGraphicalViewer(),
		// TextTransfer.getInstance()));
	}

	protected void initializeCreationFactories() {
		templateNameToCreationFactoryMap = new HashMap();

		if (freeTxtNodeCreationFactory == null)
			freeTxtNodeCreationFactory = new CreationFactory() {

				public Object getNewObject() {
					TypedNode node = ModelFactory.eINSTANCE.createTypedNode();
					node.setType(TypedNode.FREE_TEXT);
					node.setObject(GraphicalDataHelper
							.newTypedGraphNode(TypedNode.FREE_TEXT));
					return node;
				}

				public Object getObjectType() {
					return TemplateConstants.FREE_TEXT;
				}

			};
		CreationFactory creationFactory = freeTxtNodeCreationFactory;
		templateNameToCreationFactoryMap.put(creationFactory.getObjectType(),
				creationFactory);

	}

	/**
	 * 
	 * @see org.eclipse.epf.authoring.ui.editors.AbstractDiagramEditor#createDropTargetListener()
	 */
	protected TransferDropTargetListener createDropTargetListener() {
		return new TemplateTransferDropTargetListener(getGraphicalViewer()) {

			protected CreationFactory getFactory(Object template) {
				return (CreationFactory) templateNameToCreationFactoryMap
						.get(template);
			}

		};
	}

	protected void handleActivationChanged(Event event) {
		IAction copy = null;
		if (event.type == SWT.Deactivate)
			copy = getActionRegistry().getAction(ActionFactory.COPY.getId());
		if (getEditorSite().getActionBars().getGlobalActionHandler(
				ActionFactory.COPY.getId()) != copy) {
			getEditorSite().getActionBars().setGlobalActionHandler(
					ActionFactory.COPY.getId(), copy);
			getEditorSite().getActionBars().updateActionBars();
		}
	}

	/**
	 * @see org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette#getPaletteRoot()
	 */
	protected PaletteRoot getPaletteRoot() {
		if (paletteRoot == null) {
			createPaletteRoot();
		}
		return paletteRoot;
	}

	/**
	 * 
	 */
	private void createPaletteRoot() {
		paletteRoot = new PaletteRoot();
		paletteRoot.addAll(createCategories(paletteRoot));
	}

	private List createCategories(PaletteRoot root) {
		List categories = new ArrayList();

		PaletteContainer paletteContainer = createControlGroup(root);
		if (paletteContainer != null) {
			categories.add(paletteContainer);
		}

		return categories;
	}

	protected PaletteContainer createControlGroup(PaletteRoot root) {
		return null;
	}

	protected MethodElement getMethodElementFromInput() {
		IEditorInput input = getEditorInput();
		if (input instanceof MethodElementEditorInput) {
			return ((MethodElementEditorInput) input).getMethodElement();
		}
		return null;
	}

	/**
	 * @see org.eclipse.ui.ISaveablePart#doSave(org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void doSave(final IProgressMonitor monitor) {

		// saveDiagram();
		// saveScreen();

		final MethodElement e = getMethodElementFromInput();
		if (e == null)
			return;
		SafeRunnable.run(new SafeRunnable() {

			public void run() throws Exception {
				try {
					monitor
							.beginTask(
									DiagramUIResources.AbstractDiagramEditor_Save_text, IProgressMonitor.UNKNOWN); 
					monitor
							.setTaskName(DiagramUIResources.bind(DiagramUIResources.AbstractDiagramEditor_Save_message
									, e.eResource().getURI().isFile() ? e.eResource().getURI().toFileString() : e.getName())); 
					ILibraryPersister.FailSafeMethodLibraryPersister persister = LibraryServiceUtil.getCurrentPersister().getFailSafePersister();
					try {
						persister.save(e.eResource());
						persister.commit();
						Diagram diagram = (Diagram) ((EditPart) getGraphicalViewer()
								.getContents()).getModel();
						diagram.setNew(false);
					} catch (Exception e) {
						AuthoringUIPlugin.getDefault().getLogger().logError(e);
						try {
							persister.rollback();
						} catch (Exception ex) {
							AuthoringUIPlugin.getDefault().getLogger()
									.logError(ex);
							ViewHelper
									.reloadCurrentLibaryOnRollbackError(getSite()
											.getShell());
							return;
						}
						AuthoringUIPlugin
								.getDefault()
								.getMsgDialog()
								.displayWarning(
										getSite().getShell().getText(),
										DiagramUIResources.diagram_saveError
										, e.getMessage(), e);
						return;
					}

					// Refresh the necessary state.
					//
					//getEditDomain().getCommandStack().markSaveLocation();
					getEditDomain().getCommandStack().flush();
					changeTime = -1;
					firePropertyChange(IEditorPart.PROP_DIRTY);

				} finally {
					monitor.done();
				}
			}

		});
	}

	/** 
	 * @see org.eclipse.ui.ISaveablePart#doSaveAs()
	 */
	public void doSaveAs() {
	}

	/**
	 * @see org.eclipse.ui.ISaveablePart#isDirty()
	 */
	public boolean isDirty() {
		return getEditDomain().getCommandStack().isDirty();
	}

	/** 
	 * @see org.eclipse.ui.ISaveablePart#isSaveAsAllowed()
	 */
	public boolean isSaveAsAllowed() {
		return false;
	}

	protected boolean isReadOnly() {
		if (editPart != null) {
			Diagram diagram = (Diagram) editPart.getModel();
			return diagram != null && diagram.isReadOnly();
		}
		return false;
	}

	private void createEditPart() {
		BreakdownElementEditorInput editorInput = (BreakdownElementEditorInput) getEditorInput();
		Object object;
		if (editorInput.getWrapper() != null) {
			object = editorInput.getWrapper();
		} else {
			object = editorInput.getMethodElement();
		}
		
		changeTime = System.currentTimeMillis();
		editPart = createDiagramEditPart();
		Diagram diagram = (Diagram) editPart.getModel();
		// pass along the Suppression instance from input to diagram model
		//
		diagram.setSuppression(editorInput.getSuppression());
		diagram.setObject(object);
		diagram.addConsumer(this);
		
		if(!diagram.isNew()) {
			changeTime = -1;
		}
	}

	/**
	 * @see org.eclipse.ui.part.EditorPart#setInput(org.eclipse.ui.IEditorInput)
	 */
	protected void setInput(IEditorInput input) {
		super.setInput(input);

		MethodElement e = getMethodElementFromInput();
		if (e != null) {
			createEditPart();

			BreakdownElementEditorInput editorInput = (BreakdownElementEditorInput) getEditorInput();
			setPartName(getPartNamePrefix()
					+ e.getName()
					+ ", " + editorInput.getSuppression().getProcess().getName()); //$NON-NLS-1$
			// setTitleImage(getTypeImage(e));
		}
	}

	protected abstract EditPart createDiagramEditPart();

	protected abstract String getPartNamePrefix();

	protected abstract String getDiagramType();

	/**
	 * Get parent editor
	 * @return editor
	 */
	public IEditorPart getParentEditor() {
		return parentEditor;
	}

	public void setParentEditor(IEditorPart parentEditor) {
		this.parentEditor = parentEditor;
	}
	
	//Adding librarychnaged listener, if configuration got changed diagram needs to refresh
	private ILibraryChangeListener libraryListener = new ILibraryChangeListener() {
		public void libraryChanged(int option, Collection collection) {
			switch (option) {
			case ILibraryChangeListener.OPTION_CHANGED: {
				if (collection != null && collection.contains(currentConfig)) {
					MethodConfiguration config = ProcessAuthoringConfigurator.INSTANCE.getMethodConfiguration();
					try {
						ProcessAuthoringConfigurator.INSTANCE.setMethodConfiguration(currentConfig);					
						IAction action = getActionRegistry().getAction(REFRESH);
						action.run();
					}
					finally {
						ProcessAuthoringConfigurator.INSTANCE.setMethodConfiguration(config);
					}
				}		
				break;
			}
			// handled by libSvcListener
			//
//			case ILibraryChangeListener.OPTION_CONFIGURATION_SELECTED: {
//				configChanged();
//				break;
//			}
			}
		}
	};
	
	private ILibraryServiceListener libSvcListener = new ILibraryServiceListener() {

		public void configurationSet(MethodConfiguration config) {
			configChanged();
		}

		public void libraryClosed(MethodLibrary library) {
		}

		public void libraryCreated(MethodLibrary library) {
		}

		public void libraryOpened(MethodLibrary library) {
		}

		public void libraryReopened(MethodLibrary library) {
		}

		public void librarySet(MethodLibrary library) {
		}		
	};
	
	private void configChanged() {
		MethodConfiguration config = LibraryService.getInstance().getCurrentMethodConfiguration();
		if(currentConfig != config) {
			// refresh only if the active part is this diagram editor or process editor of the process
			// that owns this diagram
			//
			IWorkbenchPart activePart = getSite().getWorkbenchWindow().getPartService().getActivePart();
			boolean refresh =  activePart == this;
			if(!refresh) {
				if(activePart instanceof ProcessEditor) {
					MethodElementEditorInput input = (MethodElementEditorInput) ((ProcessEditor)activePart).getEditorInput();
					Object procComp = input.getMethodElement();
					EObject diagram = ((Diagram)editPart.getModel()).getUMADiagram();
					refresh = UmaUtil.isContainedBy(diagram, procComp);
				}
			}
			if(refresh) {
				currentConfig = config;
				IAction action = getActionRegistry().getAction(REFRESH);
				action.run();
			}
		}
	}
	
	/**
	 * @see org.eclipse.ui.ISelectionListener#selectionChanged(org.eclipse.ui.IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
	 * override to donot allow any node selection in Activity Detail Diagram 
	 * if activity is extends.
	 */
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		// TODO Auto-generated method stub
		super.selectionChanged(part, selection);
		if(editPart != null){
			Diagram diagram = (Diagram)editPart.getModel();
			if(diagram.isReadOnly() || TngUtil.isLocked(getMethodElementFromInput())){
				getGraphicalViewer().select(editPart);
			}
		}
	}
	
	public void refreshDiagram(){
		getActionRegistry().getAction(REFRESH).run();
	}

	public boolean isDisposed() {
		return disposed;
	}

	
//	/*
//	 * Method sets the context menu contributing items state.
//	 * This is efficient way instead of doing at each action. 
//	 * Uncomment this code, once all the place isEnabled Code is removed.
//	 */
//	public void setStateMenuContributeItems(){
//		
//		List editParts = getGraphicalViewer().getSelectedEditParts();
//		if(editParts.size() == 0){
//			getActionRegistry().getAction(DELETE_DIAGRAM).setEnabled(true);
//		}
//		if(editParts.size() == 1){
//			EditPart editPart = (EditPart)editParts.get(0);
//			getActionRegistry().getAction(SELECT_LINK).setEnabled(true);
//			if (editPart.getModel() instanceof TypedNode) {
//				if(((TypedNode) editPart.getModel()).getType() == TypedNode.FREE_TEXT)
//				getActionRegistry().getAction(FONT_DIALOG).setEnabled(true);
//				getActionRegistry().getAction(SELECT_LINK).setEnabled(false);
//			}
//			if(editPart instanceof LinkEditPart){
//				getActionRegistry().getAction(CREATE_BEND_POINT).setEnabled(true);
//				Link link = (Link)editPart.getModel();
//				if(link.getBendpoints().size() > 0){
//					getActionRegistry().getAction(ALIGN_BEND_POINT).setEnabled(true);
//				}
//			}
//			
//		}
//		if(editParts.size() == 2){
//			getActionRegistry().getAction(CREATE_LINK).setEnabled(true); 
//			for (Iterator itor = editParts.iterator(); itor.hasNext();) {
//				Object part = itor.next();
//				if (part instanceof FreeTextEditPart)
//					getActionRegistry().getAction(CREATE_LINK).setEnabled(false);
//			}
//		}
//	}
	
//	public void setFocus() {
//		// TODO Auto-generated method stub
//		super.setFocus();
//		// Refresh is expensive operation.
//		// TODO - find process solution for notification between 
//		// base diagram and extend diagram.     
//		MethodElement element = getMethodElementFromInput();
//		if(((Diagram)editPart.getModel()).isReadOnly()){
//			getActionRegistry().getAction(REFRESH).run();
//		}
//		else if(element instanceof VariabilityElement){
//			if(((VariabilityElement)element).getVariabilityType() != null){
//				if(!((VariabilityElement)element).getVariabilityType().equals(VariabilityType.NA_LITERAL)){
//					getActionRegistry().getAction(REFRESH).run();
//				}
//			}
//				
//		}
//	}
	
}