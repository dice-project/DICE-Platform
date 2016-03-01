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
package org.eclipse.epf.authoring.ui.editors;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.NotificationImpl;
import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.common.util.AbstractTreeIterator;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.provider.DelegatingWrapperItemProvider;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.emf.edit.ui.action.CommandActionHandler;
import org.eclipse.emf.edit.ui.action.CopyAction;
import org.eclipse.emf.edit.ui.action.CutAction;
import org.eclipse.emf.edit.ui.action.DeleteAction;
import org.eclipse.emf.edit.ui.action.PasteAction;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.actions.IModifyingAction;
import org.eclipse.epf.authoring.ui.actions.IWorkbenchPartAction;
import org.eclipse.epf.authoring.ui.actions.IndentAction;
import org.eclipse.epf.authoring.ui.actions.MethodLibraryActionBarContributor;
import org.eclipse.epf.authoring.ui.actions.MoveDownAction;
import org.eclipse.epf.authoring.ui.actions.MoveUpAction;
import org.eclipse.epf.authoring.ui.actions.OutdentAction;
import org.eclipse.epf.authoring.ui.actions.ProcessAutoSynchronizeAction;
import org.eclipse.epf.authoring.ui.actions.SynchronizationAction;
import org.eclipse.epf.authoring.ui.actions.UpdateSuppressionFromBaseAction;
import org.eclipse.epf.authoring.ui.dialogs.DialogHelper;
import org.eclipse.epf.authoring.ui.dialogs.UserDefinedDiagramDialog;
import org.eclipse.epf.authoring.ui.forms.ProcessBreakdownStructureFormPage;
import org.eclipse.epf.authoring.ui.properties.EPFPropertySheetPage;
import org.eclipse.epf.authoring.ui.views.LibraryView;
import org.eclipse.epf.authoring.ui.views.ProcessViewer;
import org.eclipse.epf.authoring.ui.views.ViewHelper;
import org.eclipse.epf.diagram.ad.ADImages;
import org.eclipse.epf.diagram.add.ADDImages;
import org.eclipse.epf.diagram.core.DiagramCorePlugin;
import org.eclipse.epf.diagram.core.DiagramCoreResources;
import org.eclipse.epf.diagram.core.part.DiagramEditorInput;
import org.eclipse.epf.diagram.core.part.util.DiagramEditorUtil;
import org.eclipse.epf.diagram.core.services.DiagramHelper;
import org.eclipse.epf.diagram.core.services.DiagramManager;
import org.eclipse.epf.diagram.model.util.GraphicalDataHelper;
import org.eclipse.epf.diagram.model.util.GraphicalDataManager;
import org.eclipse.epf.diagram.ui.service.DiagramEditorHelper;
import org.eclipse.epf.diagram.wpdd.part.WPDDImages;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.configuration.ActivityDeepCopyConfigurator;
import org.eclipse.epf.library.configuration.ConfigurationHelper;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.LibraryEditResources;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.command.IResourceAwareCommand;
import org.eclipse.epf.library.edit.command.ResourceAwarePasteFromClipboardCommand;
import org.eclipse.epf.library.edit.process.ActivityWrapperItemProvider;
import org.eclipse.epf.library.edit.process.BreakdownElementWrapperItemProvider;
import org.eclipse.epf.library.edit.process.IBSItemProvider;
import org.eclipse.epf.library.edit.process.command.ActivityDropCommand;
import org.eclipse.epf.library.edit.process.command.ContributeToActivityCommand;
import org.eclipse.epf.library.edit.process.command.CustomizeDescriptorCommand;
import org.eclipse.epf.library.edit.process.command.LocallyReplaceAndDeepCopyCommand;
import org.eclipse.epf.library.edit.process.command.ReplaceActivityCommand;
import org.eclipse.epf.library.edit.ui.IActionTypeProvider;
import org.eclipse.epf.library.edit.ui.UserInteractionHelper;
import org.eclipse.epf.library.edit.util.DescriptorPropUtil;
import org.eclipse.epf.library.edit.util.DiagramOptions;
import org.eclipse.epf.library.edit.util.ExposedAdapterFactory;
import org.eclipse.epf.library.edit.util.IDiagramManager;
import org.eclipse.epf.library.edit.util.ProcessScopeUtil;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.Suppression;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.edit.util.Suppression.SuppressionCommand;
import org.eclipse.epf.library.edit.validation.DependencyChecker;
import org.eclipse.epf.library.ui.actions.LibraryLockingOperationRunner;
import org.eclipse.epf.library.ui.actions.ProcessDeleteAction;
import org.eclipse.epf.persistence.FileManager;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.Diagram;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Milestone;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.VariabilityType;
import org.eclipse.epf.uma.edit.domain.TraceableAdapterFactoryEditingDomain;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.dialogs.ListSelectionDialog;
import org.eclipse.ui.forms.editor.IFormPage;

/**
 * Action bar contribtutor for process editor
 * 
 * @author Phong Nguyen Le
 * @sine 1.2
 */
public class ProcessEditorActionBarContributor extends
		MethodLibraryActionBarContributor {
	protected Collection<IAction> registeredActions = new ArrayList<IAction>();

	protected boolean locked;

	protected IAction rollupAction = new Action(
			AuthoringUIResources.ProcessEditor_Action_RollUp) {

		public void run() {
			boolean wasRolledUp = bsItemProvider.isRolledUp();
			bsItemProvider.setRolledUp(!bsItemProvider.isRolledUp());
			((IChangeNotifier) bsItemProvider)
					.fireNotifyChanged(new ViewerNotification(
							new ENotificationImpl(
									(InternalEObject) selectedActivity,
									Notification.ADD, null, null, null, false),
							selectedActivity, true, false));

			if (bsItemProvider instanceof DelegatingWrapperItemProvider) {
				// Refreshing for green activities during rollup
				DelegatingWrapperItemProvider provider = (DelegatingWrapperItemProvider) bsItemProvider;
				provider.fireNotifyChanged(new ViewerNotification(
						new NotificationImpl(Notification.SET, provider,
								provider), provider, true, false));
			}
			if (wasRolledUp) {
				ProcessViewer viewer = (ProcessViewer) ((ProcessEditor) activeEditor).currentViewer;
				viewer.expandToLevel(selectedActivity,
						AbstractTreeViewer.ALL_LEVELS);

				// eventhough we switched off creating new activities in
				// WBS,
				// user can still create thru AD. Hence we need to update
				// IDs
				// afterwards
				doRefresh();
			}
		}

	};

	protected MoveUpAction moveUpAction = new MoveUpAction() {
		@Override
		protected boolean accept(IStructuredSelection selection) {
			if(!isEditingAllowed()) {
				return false;
			}
			return super.accept(selection);
		}		
	};

	protected MoveDownAction moveDownAction = new MoveDownAction() {
		@Override
		protected boolean accept(IStructuredSelection selection) {
			if(!isEditingAllowed()) {
				return false;
			}
			return super.accept(selection);
		}
	};

	private IAction preferenceAction = new Action(
			AuthoringUIResources.ProcessEditor_Action_Preferences) {
		public void run() {
			// open process preference page
			org.eclipse.ui.dialogs.PreferencesUtil
					.createPreferenceDialogOn(
							getActiveEditor().getEditorSite().getShell(),
							"org.eclipse.epf.authoring.ui.preferences.ProcessEditorPreferencePage", //$NON-NLS-1$
							null, null).open();
		}
	};

	protected IAction openBaseActivity = new Action(
			AuthoringUIResources.ProcessEditor_Action_OpenBaseActivity) {
		public void run() {
			VariabilityElement base = selectedActivity
					.getVariabilityBasedOnElement();
			Process proc = TngUtil.getOwningProcess((BreakdownElement) base);
			try {
				int id = ((ProcessEditor) activeEditor).getActivePage();
				ISelection selection = new StructuredSelection(base);

				IEditorInput input = new MethodElementEditorInput(
						(MethodElement) proc.eContainer());
				ProcessEditor editor = (ProcessEditor) getPage().openEditor(
						input, ProcessEditor.EDITOR_ID);
				editor.setActivePage(id);
				editor.bsPages[id - 1].getViewer()
						.setSelection(selection, true);
			} catch (PartInitException e) {
				AuthoringUIPlugin.getDefault().getLogger().logError(e);
			}
		}
	};

	protected IAction openWorkflowEditorAction = new Action(
			AuthoringUIResources.ProcessEditor_Action_OpenActivityDiagram) {
		public void run() {
			try {
				try {
					IEditorPart parent = getPage().getActiveEditor();
					IEditorInput input = new GraphicalWorkflowEditor.EditorInput(
							selectedObject, getSuppression());
					IEditorPart part = getPage().openEditor(input,
							ProcessEditor.WORKFLOW_EDITOR_ID);
					if (part instanceof AbstractDiagramEditor) {
						AbstractDiagramEditor editor = (AbstractDiagramEditor) part;
						editor.setParentEditor(parent);
					}
				} catch (PartInitException exception) {
					AuthoringUIPlugin.getDefault().getLogger().logError(
							exception);
				}
			} catch (RuntimeException e) {
				AuthoringUIPlugin.getDefault().getLogger().logError(e);
			}
		}
	};
	
	private class DeleteDiagramAction extends Action {
		private int diagramType;
		private final String DELETE_ICON_PATH = "elcl16/delete_diagram.gif"; //$NON-NLS-1$
		
		public DeleteDiagramAction(String text, int diagramType) {
			super(text);
			this.diagramType = diagramType;
			setImageDescriptor(DiagramCorePlugin.getDefault().getImageDescriptor(DELETE_ICON_PATH));
		}
		
		public void run() {
			DiagramManager mgr = DiagramManager.getInstance(getProcess(), this);
		
			try {
				List<org.eclipse.gmf.runtime.notation.Diagram> diagrams = mgr.getDiagrams(selectedActivity, diagramType);
				if (diagrams.size() > 0) {
					String msg = NLS.bind(DiagramCoreResources.DeleteDiagram_prompt_new, getDiagramName(diagramType));					
					boolean result = DiagramCorePlugin.getDefault().getMsgDialog().displayPrompt(
							DiagramCoreResources.DeleteDiagram_text, msg);
					if (result) {	
						IEditorPart[] editors = getPage().getEditors();
						for (IEditorPart editor: editors) {
							if (editor instanceof org.eclipse.epf.diagram.core.part.AbstractDiagramEditor) {
								getPage().closeEditor(editor, false);
							}
						}						
						
						DiagramHelper.deleteDiagram(diagrams.get(0), true);
					}
				}			
			} catch (Exception e) {
				AuthoringUIPlugin.getDefault().getLogger().logError(e);
			}			
		}
		
		private String getDiagramName(int type) {
			switch(type) {
			case IDiagramManager.ACTIVITY_DIAGRAM:
				return DiagramCoreResources.DeleteDiagram_AD;
			case IDiagramManager.ACTIVITY_DETAIL_DIAGRAM:
				return DiagramCoreResources.DeleteDiagram_ADD;
			case IDiagramManager.WORK_PRODUCT_DEPENDENCY_DIAGRAM:
				return DiagramCoreResources.DeleteDiagram_WPDD;
			default:
				return null;
			}
		}
	}
	
	private IAction deleteActivityDiagram = new DeleteDiagramAction(
			AuthoringUIResources.ProcessEditor_Action_delete_AD_text,
			IDiagramManager.ACTIVITY_DIAGRAM);
	private IAction deleteActivityDetailDiagram = new DeleteDiagramAction(
			AuthoringUIResources.ProcessEditor_Action_delete_ADD_text,
			IDiagramManager.ACTIVITY_DETAIL_DIAGRAM);	
	private IAction deleteWPDiagram = new DeleteDiagramAction(
			AuthoringUIResources.ProcessEditor_Action_delete_WPDD_text,
			IDiagramManager.WORK_PRODUCT_DEPENDENCY_DIAGRAM);
	
	private class OpenDiagramEditorAction extends Action {

		private int diagramType;

		public OpenDiagramEditorAction(String text, int diagramType) {
			super(text);
			this.diagramType = diagramType;
			setImageDescriptor(getImageDescriptor(diagramType));
			setDisabledImageDescriptor(getDisabledImageDescriptor(diagramType));
		}
		
		private ImageDescriptor getImageDescriptor(int diagramType) {
			switch(diagramType) {
			case IDiagramManager.ACTIVITY_DIAGRAM:
				return ADImages.IMG_DESC_EDITOR;
			case IDiagramManager.ACTIVITY_DETAIL_DIAGRAM:
				return ADDImages.IMG_DESC_EDITOR;
			case IDiagramManager.WORK_PRODUCT_DEPENDENCY_DIAGRAM:
				return WPDDImages.IMG_DESC_EDITOR;
			}
			return null;
		}
		
		private ImageDescriptor getDisabledImageDescriptor(int diagramType) {
			switch(diagramType) {
			case IDiagramManager.ACTIVITY_DIAGRAM:
				return ADImages.DISABLED_IMG_DESC_EDITOR;
			case IDiagramManager.ACTIVITY_DETAIL_DIAGRAM:
				return ADDImages.DISABLED_IMG_DESC_EDITOR;
			case IDiagramManager.WORK_PRODUCT_DEPENDENCY_DIAGRAM:
				return WPDDImages.DISABLED_IMG_DESC_EDITOR;
			}
			return null;
		}

		
		@Override
		public void run() {
			DiagramManager mgr = null;
			try {
				if(!ProcessUtil.isInherited(selectedObject)) {
					mgr = DiagramManager.getInstance(getProcess(), this);
					Resource resource = mgr.getResource();
					if(mgr.isNewResource() && !new File(FileManager.toFileString(resource.getURI())).exists()) {
						boolean result = AuthoringUIPlugin
						.getDefault()
						.getMsgDialog()
						.displayConfirmation(AuthoringUIResources.NewDiagramFile_title,
								AuthoringUIResources.promptCreateNewDiagramFile_msg);
						if(result) {
							resource.save(Collections.EMPTY_MAP);

							// still keep the flag resourceIsNew set so editor will be marked dirty
							//
							mgr.setResourceIsNew(true);
						}
						else {
							return;
						}
					}
				}
				openDiagram(diagramType);
			} catch (Exception exception) {
				AuthoringUIPlugin.getDefault().getLogger().logError(
						exception);
			}
			finally {
				if(mgr != null) {
					mgr.removeConsumer(this);
				}
			}
		}
	}
	
	private IAction newActivityDiagramEditor = new OpenDiagramEditorAction(
			AuthoringUIResources.ProcessEditor_Action_OpenActivityDiagram, IDiagramManager.ACTIVITY_DIAGRAM);

	private IAction newActivityDetailDiagramEditor = new OpenDiagramEditorAction(
			AuthoringUIResources.ProcessEditor_Action_OpenActivityDetailDiagram, IDiagramManager.ACTIVITY_DETAIL_DIAGRAM);

	private IAction newWPDiagramEditor = new OpenDiagramEditorAction(
			AuthoringUIResources.ProcessEditor_Action_OpenWorkProductDependencyDiagram,
			IDiagramManager.WORK_PRODUCT_DEPENDENCY_DIAGRAM);

	// Open workflow detail action
	//
	protected IAction openWorkflowDetailEditorAction = new Action(
			AuthoringUIResources.ProcessEditor_Action_OpenActivityDetailDiagram) {
		public void run() {
			try {
				try {
					IEditorPart parent = getPage().getActiveEditor();
					IEditorInput input = new ActivityDetailDiagramEditor.EditorInput(
							selectedObject, getSuppression());
					IEditorPart part = getPage().openEditor(input,
							ProcessEditor.ACTIVITY_DETAIL_DIAGRAM_EDITOR_ID);
					if (part instanceof AbstractDiagramEditor) {
						AbstractDiagramEditor editor = (AbstractDiagramEditor) part;
						editor.setParentEditor(parent);
					}
				} catch (PartInitException exception) {
					exception.printStackTrace();
				}
			} catch (RuntimeException e) {
				e.printStackTrace();
			}

		}
	};

	protected IAction openWPDependencyEditorAction = new Action(
			AuthoringUIResources.ProcessEditor_Action_OpenWorkProductDependencyDiagram) {
		public void run() {
			try {
				try {
					IEditorPart parent = getPage().getActiveEditor();
					MethodElementEditorInput input = new BreakdownElementEditorInput(
							selectedObject, getSuppression()) {
					};
					IEditorPart part = getPage().openEditor(input,
							ProcessEditor.WPDEPENDENCY_EDITOR_ID);
					if (part instanceof AbstractDiagramEditor) {
						AbstractDiagramEditor editor = (AbstractDiagramEditor) part;
						editor.setParentEditor(parent);
					}
				} catch (PartInitException exception) {
					exception.printStackTrace();
				}
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
		}
	};

	protected IAction replaceAction = new Action(
			AuthoringUIResources.ProcessEditor_Action_Replace) {
		public void run() {
			IResourceAwareCommand cmd = null;
			try {
				cmd = new ReplaceActivityCommand(
						(BreakdownElementWrapperItemProvider) bsItemProvider);
				getActionManager().execute(cmd);
			} catch (Exception e) {
				AuthoringUIPlugin.getDefault().getLogger().logError(e);
			} finally {
				if (cmd != null) {
					try {
						cmd.dispose();
					} catch (Exception e) {
						AuthoringUIPlugin.getDefault().getLogger().logError(e);
					}
				}
			}
		}
	};

	protected IAction localReplacementAndDeepCopy = new Action(
			LibraryEditResources.localReplacementAndDeepCopy_text) {
		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.action.Action#run()
		 */
		public void run() {
			Runnable runnable = new Runnable() {

				public void run() {
					IResourceAwareCommand cmd = null;
					try {
						cmd = new LocallyReplaceAndDeepCopyCommand(
								(BreakdownElementWrapperItemProvider) bsItemProvider);
						getActionManager().execute(cmd);
					} catch (Exception e) {
						AuthoringUIPlugin.getDefault().getLogger().logError(e);
					} finally {
						if (cmd != null) {
							try {
								cmd.dispose();
							} catch (Exception e) {
								AuthoringUIPlugin.getDefault().getLogger()
										.logError(e);
							}
						}
					}
				}

			};

			UserInteractionHelper.runInUI(runnable, getText());
		}
	};

	protected IAction contributeAction = new Action(
			AuthoringUIResources.ProcessEditor_Action_Contribute) {
		public void run() {
			IResourceAwareCommand cmd = null;
			try {
				cmd = new ContributeToActivityCommand(
						(BreakdownElementWrapperItemProvider) bsItemProvider);
				getActionManager().execute(cmd);
				// ProcessUtil.contributeToActivity((BreakdownElementWrapperItemProvider)bsItemProvider,
				// ((ProcessFormEditor)activeEditor).editingDomain.getAdapterFactory());
			} catch (Exception e) {
				AuthoringUIPlugin.getDefault().getLogger().logError(e);
			} finally {
				if (cmd != null) {
					try {
						cmd.dispose();
					} catch (Exception e) {
						AuthoringUIPlugin.getDefault().getLogger().logError(e);
					}
				}
			}
		}
	};

	private IAction customizeAction = new Action(
			AuthoringUIResources.ProcessEditor_Action_Customize) {
		public void run() {
			if (!(bsItemProvider instanceof BreakdownElementWrapperItemProvider)) {
				return;
			}
			CustomizeDescriptorCommand command = new CustomizeDescriptorCommand(
					(BreakdownElementWrapperItemProvider) bsItemProvider);
			getActionManager().execute(command);			
		}
	};
	
	private class EditorSuppressionCommand extends SuppressionCommand {
		EditorSuppressionCommand(Suppression suppression, List selection,
				boolean suppressed) {
			super(suppression, selection, suppressed);
		}

		private void refresh() {
			if (!getResult().isEmpty()) {
				ProcessEditor editor = (ProcessEditor) getActiveEditor();
				if (isReadOnlyElementAffected()) {
					if (ProcessEditor.hasInherited(selection)) {
						editor.refreshAllProcessEditors();
					} else {
						editor.doRefreshAll(false);
					}
				}
				editor.firePropertyChange(IEditorPart.PROP_DIRTY);

				// Do Diagram Editors refresh
				List<IEditorReference> list = new ArrayList<IEditorReference>();
				for (Iterator iter = selection.iterator(); iter.hasNext();) {
					Object element = iter.next();
					DiagramEditorHelper.closeDiagramEditors(element, list);
					DiagramEditorHelper.refreshParentDiagramEditors(element,
							list, true);
					list.clear();
				}
			}
		}

		/**
		 * @see org.eclipse.epf.library.edit.util.Suppression.SuppressionCommand#didExecute()
		 */
		protected void didExecute() {
			refresh();
		}

		/**
		 * @see org.eclipse.epf.library.edit.util.Suppression.SuppressionCommand#didUndo()
		 */
		protected void didUndo() {
			refresh();
		}

	}

	//TODO: move this action to its own CommandActionHandler class, see IndenAction
	private IAction suppressTaskAction = new Action(
			AuthoringUIResources.ProcessEditor_Action_Suppress_Task) {

		public void run() {
			// check if suppressing the selected elements will cause
			// duplicate descriptors.
			// If so, prompt user to delete the duplicates before
			// continuing.
			//

			final List taskDescriptors = ProcessUtil
					.getTaskDescriptors(selection);
			
			// NO MORE CHECK FOR DUPLICATION OF NAME, PRESENTATION NAME, OR LINKED ELEMENT

			BusyIndicator.showWhile(getActiveEditor().getEditorSite()
					.getShell().getDisplay(), new Runnable() {

				public void run() {
					EditorSuppressionCommand cmd = new EditorSuppressionCommand(
							getSuppression(), taskDescriptors, true);
					cmd.setLabel(getText());
					getActionManager().execute(cmd);
				}
			});

			boolean show = true;
			// update context bar menu after performing suppress
			if (getAdapterFactory() == TngAdapterFactory.INSTANCE
					.getWBS_ComposedAdapterFactory()) {
				for (int i = 0; i < selection.size(); i++) {
					if (!(selection.get(i) instanceof Activity || selection
							.get(i) instanceof ActivityWrapperItemProvider))
						show = false;
				}
			} else
				show = false;

			revealTaskAction.setEnabled(show);
			suppressTaskAction.setEnabled(show);

			// enable synchronize actions correctly
			autoSynchronize.setEnabled(autoSynchronize
					.updateSelection(new StructuredSelection(selection
							.toArray())));
			manualSynchronizeAction.setEnabled(manualSynchronizeAction
					.updateSelection(new StructuredSelection(selection
							.toArray())));
		}
	};

	private IAction suppressAction = new Action(
			AuthoringUIResources.ProcessEditor_Action_Suppress) {

		public void run() {
			// NO MORE CHECK FOR DUPLICATION OF NAME, PRESENTATION NAME, OR LINKED ELEMENT
			
			BusyIndicator.showWhile(getActiveEditor().getEditorSite()
					.getShell().getDisplay(), new Runnable() {

				public void run() {
					// if (getSuppression().suppress(selection)) {
					// if (hasInherited(selection)) {
					// editor.refreshAllProcessEditors();
					// } else {
					// editor.doRefreshAll(false);
					// }
					// }
					// editor.firePropertyChange(PROP_DIRTY);

					EditorSuppressionCommand cmd = new EditorSuppressionCommand(
							getSuppression(), selection, true);
					cmd.setLabel(getText());
					getActionManager().execute(cmd);
				}

			});

			// update context bar menu after performing suppress
			revealAction.setEnabled(getSuppression().canReveal(selection));
			suppressAction.setEnabled(getSuppression().canSuppress(selection));

			// enable synchronize actions correctly
			autoSynchronize.setEnabled(autoSynchronize
					.updateSelection(new StructuredSelection(selection
							.toArray())));
			manualSynchronizeAction.setEnabled(manualSynchronizeAction
					.updateSelection(new StructuredSelection(selection
							.toArray())));
		}
	};

	protected UpdateSuppressionFromBaseAction updateSuppressionFromBaseAction = new UpdateSuppressionFromBaseAction();

	protected IAction revealAction = new Action(
			AuthoringUIResources.ProcessEditor_Action_Reveal) {

		public void run() {
			if (ProcessEditor.canReveal(selection, getAdapterFactory(), getSuppression())) {
				// final ProcessEditor editor = (ProcessEditor)
				// getActiveEditor();

				BusyIndicator.showWhile(getActiveEditor().getEditorSite()
						.getShell().getDisplay(), new Runnable() {

					public void run() {
						// if (getSuppression().reveal(selection)) {
						// if (hasInherited(selection)) {
						// editor.refreshAllProcessEditors();
						// } else {
						// editor.doRefreshAll(false);
						// }
						// }
						// editor.firePropertyChange(PROP_DIRTY);

						EditorSuppressionCommand cmd = new EditorSuppressionCommand(
								getSuppression(), selection, false);
						cmd.setLabel(getText());
						getActionManager().execute(cmd);
					}

				});
				// update context bar menu after performing reveal
				revealAction.setEnabled(getSuppression().canReveal(selection));
				suppressAction.setEnabled(getSuppression().canSuppress(
						selection));
				// enable synchronize actions correctly
				autoSynchronize.setEnabled(autoSynchronize
						.updateSelection(new StructuredSelection(selection
								.toArray())));
				manualSynchronizeAction.setEnabled(manualSynchronizeAction
						.updateSelection(new StructuredSelection(selection
								.toArray())));
			}
		}
	};

	private IAction revealTaskAction = new Action(
			AuthoringUIResources.ProcessEditor_Action_Reveal_Task) {

		public void run() {
			final List taskDescriptors = ProcessUtil
					.getTaskDescriptors(selection);
			if (ProcessEditor.canReveal(taskDescriptors, getAdapterFactory(),
					getSuppression())) {

				BusyIndicator.showWhile(getActiveEditor().getEditorSite()
						.getShell().getDisplay(), new Runnable() {

					public void run() {
						EditorSuppressionCommand cmd = new EditorSuppressionCommand(
								getSuppression(), taskDescriptors, false);
						cmd.setLabel(getText());
						getActionManager().execute(cmd);
					}

				});

				// enable synchronize actions correctly
				autoSynchronize.setEnabled(autoSynchronize
						.updateSelection(new StructuredSelection(selection
								.toArray())));
				manualSynchronizeAction.setEnabled(manualSynchronizeAction
						.updateSelection(new StructuredSelection(selection
								.toArray())));
			}
		}
	};

	protected IBSItemProvider bsItemProvider = null;

	protected Object selectedElement = null;

	protected Activity selectedActivity = null;

	protected BreakdownElement selectedBreakdownElement = null;

	protected List selection;

	protected IAction suppressDiagramAction = new Action(
			AuthoringUIResources.ProcessEditor_Action_Suppress2) {
		public boolean isEnabled() {
			if (!super.isEnabled()) {
				return false;
			}
			return !(bsItemProvider instanceof BreakdownElementWrapperItemProvider && ((BreakdownElementWrapperItemProvider) bsItemProvider)
					.isReadOnly());
		}

		public void run() {
			if (!promptSaveActiveEditor()) {
				return;
			}

			IStructuredContentProvider contentProvider = new ArrayContentProvider();

			ILabelProvider labelProvider = new AdapterFactoryLabelProvider(
					TngUtil.umaItemProviderAdapterFactory) {
				public String getColumnText(Object object, int columnIndex) {
					return GraphicalDataHelper
							.getDiagramTypeText((Diagram) object);
				}
			};

			Collection diagrams = GraphicalDataHelper
					.getDiagrams(selectedActivity);
			ArrayList unsuppressedDiagrams = new ArrayList();
			for (Iterator iter = diagrams.iterator(); iter.hasNext();) {
				Diagram diagram = (Diagram) iter.next();
				if (!diagram.getSuppressed().booleanValue()) {
					unsuppressedDiagrams.add(diagram);
				}
			}

			ListSelectionDialog dlg = new ListSelectionDialog(activeEditor
					.getEditorSite().getShell(), diagrams, contentProvider,
					labelProvider,
					AuthoringUIResources.ProcessEditor_SuppressDialog_Message);
			dlg.setInitialElementSelections(unsuppressedDiagrams);
			dlg
					.setTitle(AuthoringUIResources.ProcessEditor_SuppressDialog_Title);
			dlg.setBlockOnOpen(true);
			dlg.open();

			Object[] diagramsToReveal = dlg.getResult();

			if (diagramsToReveal == null) {
				return;
			}

			boolean changed = true;

			// check if there is really any change
			//
			if (diagramsToReveal.length == unsuppressedDiagrams.size()) {
				for (int i = 0; i < diagramsToReveal.length; i++) {
					if (!unsuppressedDiagrams.contains(diagramsToReveal[i])) {
						changed = false;
						break;
					}
				}
			}

			if (changed) {
				for (Iterator iter = diagrams.iterator(); iter.hasNext();) {
					Diagram diagram = (Diagram) iter.next();
					diagram.setSuppressed(Boolean.TRUE);
				}

				for (int i = 0; i < diagramsToReveal.length; i++) {
					Diagram diagram = (Diagram) diagramsToReveal[i];
					diagram.setSuppressed(Boolean.FALSE);
				}

				// save the editor
				//
				saveActiveEditor();
			}
		}
	};

	private IAction newSuppressDiagramAction = new Action(
			AuthoringUIResources.ProcessEditor_Action_Suppress2) {
		public boolean isEnabled() {
			if (!super.isEnabled()) {
				return false;
			}
			return !(bsItemProvider instanceof BreakdownElementWrapperItemProvider && ((BreakdownElementWrapperItemProvider) bsItemProvider)
					.isReadOnly());
		}

		public void run() {
			if (!promptSaveActiveEditor()) {
				return;
			}

			IStructuredContentProvider contentProvider = new ArrayContentProvider();

			ILabelProvider labelProvider = new AdapterFactoryLabelProvider(
					TngUtil.umaItemProviderAdapterFactory) {
				public String getColumnText(Object object, int columnIndex) {
					// TODO: Change to exact string.
					return DiagramEditorHelper
							.getDiagramTypeString((org.eclipse.gmf.runtime.notation.Diagram) object);
				}
			};

			Collection diagrams = ((ProcessEditor) getActiveEditor())
					.getDiagramEditorHelper().getDiagrams(selectedActivity);
			ArrayList<org.eclipse.gmf.runtime.notation.Diagram> publishDiagrams = new ArrayList<org.eclipse.gmf.runtime.notation.Diagram>();

			for (Iterator iter = diagrams.iterator(); iter.hasNext();) {
				org.eclipse.gmf.runtime.notation.Diagram diagram = (org.eclipse.gmf.runtime.notation.Diagram) iter
						.next();
				switch (DiagramEditorHelper.getDiagramType(diagram)) {
				case IDiagramManager.ACTIVITY_DIAGRAM:
					if (DiagramOptions.isPublishAD(selectedActivity)) {
						publishDiagrams.add(diagram);
					}
					break;
				case IDiagramManager.ACTIVITY_DETAIL_DIAGRAM:
					if (DiagramOptions.isPublishADD(selectedActivity)) {
						publishDiagrams.add(diagram);
					}
					break;
				case IDiagramManager.WORK_PRODUCT_DEPENDENCY_DIAGRAM:
					if (DiagramOptions.isPublishWPDD(selectedActivity)) {
						publishDiagrams.add(diagram);
					}
					break;
				}
			}

			ListSelectionDialog dlg = new ListSelectionDialog(activeEditor
					.getEditorSite().getShell(), diagrams, contentProvider,
					labelProvider,
					AuthoringUIResources.ProcessEditor_SuppressDialog_Message);
			dlg.setInitialElementSelections(publishDiagrams);
			dlg
					.setTitle(AuthoringUIResources.ProcessEditor_SuppressDialog_Title);
			dlg.setBlockOnOpen(true);
			dlg.open();

			Object[] diagramsToReveal = dlg.getResult();

			if (diagramsToReveal == null) {
				return;
			}

			boolean changed = true;

			// check if there is really any change
			//
			if (diagramsToReveal.length == publishDiagrams.size()) {
				for (int i = 0; i < diagramsToReveal.length; i++) {
					if (!publishDiagrams.contains(diagramsToReveal[i])) {
						changed = false;
						break;
					}
				}
			}

			if (changed) {
				for (Iterator iter = diagrams.iterator(); iter.hasNext();) {
					org.eclipse.gmf.runtime.notation.Diagram diagram = (org.eclipse.gmf.runtime.notation.Diagram) iter
							.next();
					switch (DiagramEditorHelper.getDiagramType(diagram)) {
					case IDiagramManager.ACTIVITY_DIAGRAM:
						DiagramOptions.setPublishAD(selectedActivity, false);
						break;
					case IDiagramManager.ACTIVITY_DETAIL_DIAGRAM:
						DiagramOptions.setPublishADD(selectedActivity, false);
						break;
					case IDiagramManager.WORK_PRODUCT_DEPENDENCY_DIAGRAM:
						DiagramOptions.setPublishWPDD(selectedActivity, false);
						break;
					}
				}

				for (int i = 0; i < diagramsToReveal.length; i++) {
					org.eclipse.gmf.runtime.notation.Diagram diagram = (org.eclipse.gmf.runtime.notation.Diagram) diagramsToReveal[i];
					switch (DiagramEditorHelper.getDiagramType(diagram)) {
					case IDiagramManager.ACTIVITY_DIAGRAM:
						DiagramOptions.setPublishAD(selectedActivity, true);
						break;
					case IDiagramManager.ACTIVITY_DETAIL_DIAGRAM:
						DiagramOptions.setPublishADD(selectedActivity, true);
						break;
					case IDiagramManager.WORK_PRODUCT_DEPENDENCY_DIAGRAM:
						DiagramOptions.setPublishWPDD(selectedActivity, true);
						break;
					}
				}
				// save the editor
				//
				saveActiveEditor();
			}
		}
	};

	private IAction newAssignUserDiagram = new Action(
			AuthoringUIResources.ProcessEditor_Action_AssignUserDiagram) {
		/**
		 * @see org.eclipse.jface.action.Action#run()
		 */
		public void run() {
			// TODO: necessary changes UserDefinedDiagramDialog's DiagramInfo.
			UserDefinedDiagramDialog dialog = new UserDefinedDiagramDialog(
					Display.getCurrent().getActiveShell(), activeEditor,
					selectedActivity, getProcess().getDefaultContext(), TngUtil
							.isWrapped(selectedObject));
			dialog.open();
		}
	};

	private IAction diagramPublishNone = new Action(
			AuthoringUIResources.ProcessEditor_Action_SuppressAll) {
		public void run() {
			publishDiagramsForProcess(false);
		}
	};

	private IAction diagramsPublishAll = new Action(
			AuthoringUIResources.ProcessEditor_Action_RevealAll) {
		public void run() {
			publishDiagramsForProcess(true);
		}
	};

	protected IAction suppressAllDiagrams = new Action(
			AuthoringUIResources.ProcessEditor_Action_SuppressAll) {
		public void run() {
			setAllDiagramSuppressed(true);
		}
	};

	protected IAction revealAllDiagrams = new Action(
			AuthoringUIResources.ProcessEditor_Action_RevealAll) {
		public void run() {
			setAllDiagramSuppressed(false);
		}
	};

	protected IAction addAction = new Action(
			AuthoringUIResources.ProcessEditor_Action_Add) {
		public void run() {
			LibraryLockingOperationRunner runner = new LibraryLockingOperationRunner();
			runner.run(new IRunnableWithProgress() {

				public void run(IProgressMonitor monitor)
						throws InvocationTargetException, InterruptedException {
					AdapterFactory adapterFactory = getAdapterFactory();
					if (adapterFactory != null) {
						MethodConfiguration config = LibraryService
								.getInstance().getCurrentMethodConfiguration();
						if (config == null) {
							config = TngUtil.getOwningProcess(
									selectedBreakdownElement)
									.getDefaultContext();
						}
						List selection = DialogHelper.selectElementsFor(
								selectedBreakdownElement, config,
								adapterFactory);

						if (selection == null || selection.isEmpty()) {
							return;
						}

						Object adapter = adapterFactory.adapt(
								selectedBreakdownElement,
								IEditingDomainItemProvider.class);
						if (adapter instanceof IBSItemProvider) {
							IResourceAwareCommand cmd = ((IBSItemProvider) adapter)
									.createDropCommand(
											selectedBreakdownElement, selection);
							getActionManager().execute(cmd);
						}
					}
				}

			});

		}
	};

	protected IAction copyActivityAction = new Action(
			AuthoringUIResources.ProcessEditor_Action_Copy) {
		public void run() {
			MethodConfiguration config = LibraryService.getInstance()
					.getCurrentMethodConfiguration();
			if (config == null) {
				config = TngUtil.getOwningProcess(selectedActivity)
						.getDefaultContext();
			}
			List selection = DialogHelper.selectActivitiesFor(selectedActivity,
					config, IActionTypeProvider.COPY);

			if (selection == null || selection.isEmpty()) {
				return;
			} else {
				for (Iterator itor = selection.iterator(); itor.hasNext();) {
					Object o = itor.next();

					if (o instanceof Activity) {
						Activity act = (Activity) o;

						// Process proc =
						// TngUtil.getOwningProcess(selectedBreakdownElement);
						// if (DependencyChecker.hasCyclicDependency(act, proc))
						// {
						// Object[] args = { selectedActivity.getName(),
						// act.getName() };
						// String message = AuthoringUIResources
						// .bind(
						// AuthoringUIResources.apply_pattern_error_msg,
						// args);
						//
						// String title =
						// AuthoringUIResources.apply_pattern_error_title;
						// AuthoringUIPlugin.getDefault().getMsgDialog()
						// .displayError(title, message, ""); //$NON-NLS-1$
						// itor.remove();
						// }
						// else if (ProcessUtil.hasContributorOrReplacer(act)) {
						// Object[] args = { selectedActivity.getName(),
						// act.getName() };
						// String message = AuthoringUIResources
						// .bind(
						// AuthoringUIResources.activity_variability_error_msg,
						// args);
						//
						// String title =
						// AuthoringUIResources.activity_variability_error_title;
						// AuthoringUIPlugin.getDefault().getMsgDialog()
						// .displayError(title, message, ""); //$NON-NLS-1$
						//
						// itor.remove();
						// return;
						// }

						IStatus status = DependencyChecker
								.checkCircularDependency(act, selectedActivity);
						if (!status.isOK()) {
							String title = AuthoringUIResources.activity_variability_error_title;
							AuthoringUIPlugin.getDefault().getMsgDialog()
									.displayError(title, status.getMessage());
							itor.remove();
						}
					}
					if (selection.isEmpty()) {
						return;
					}
				}
			}

			ActivityDropCommand cmd = new ActivityDropCommand(selectedActivity,
					selection, null, getAdapterFactory());
			IStatus status = cmd.checkCopy();
			if (!status.isOK()) {
				String title = AuthoringUIResources.activity_variability_error_title;
				AuthoringUIPlugin.getDefault().getMsgDialog().displayError(
						title, status.getMessage());
				return;
			}
			cmd.setType(IActionTypeProvider.COPY);
			getActionManager().execute(cmd);
		}
	};

	protected IAction extendActivityAction = new Action(
			AuthoringUIResources.ProcessEditor_Action_Extend) {
		public void run() {
			MethodConfiguration config = LibraryService.getInstance()
					.getCurrentMethodConfiguration();
			if (config == null) {
				config = TngUtil.getOwningProcess(selectedActivity)
						.getDefaultContext();
			}
			List selection = DialogHelper.selectActivitiesFor(selectedActivity,
					config, IActionTypeProvider.EXTEND);

			if (selection == null || selection.isEmpty()) {
				return;
			} else {
				for (Iterator itor = selection.iterator(); itor.hasNext();) {
					Object o = itor.next();

					if (o instanceof Activity) {
						Activity act = (Activity) o;

						// Process proc =
						// TngUtil.getOwningProcess(selectedBreakdownElement);
						// if (DependencyChecker.hasCyclicDependency(act, proc))
						// {
						// Object[] args = { selectedActivity.getName(),
						// act.getName() };
						// String message = AuthoringUIResources
						// .bind(
						// AuthoringUIResources.apply_pattern_error_msg,
						// args);
						//
						// String title =
						// AuthoringUIResources.apply_pattern_error_title;
						// AuthoringUIPlugin.getDefault().getMsgDialog()
						// .displayError(title, message, ""); //$NON-NLS-1$
						// itor.remove();
						// }
						// else if (ProcessUtil.hasContributorOrReplacer(act)) {
						// Object[] args = { selectedActivity.getName(),
						// act.getName() };
						// String message = AuthoringUIResources
						// .bind(
						// AuthoringUIResources.activity_variability_error_msg,
						// args);
						//
						// String title =
						// AuthoringUIResources.activity_variability_error_title;
						// AuthoringUIPlugin.getDefault().getMsgDialog()
						// .displayError(title, message, ""); //$NON-NLS-1$
						//
						// itor.remove();
						// return;
						// }

						/*
						 * IStatus status = DependencyChecker.newCheckAct ?
						 * DependencyChecker.checkCircularDependencyAfterFilterSelection
						 * (act, selectedActivity,
						 * VariabilityType.EXTENDS_LITERAL) :
						 * DependencyChecker.checkCircularDependency(act,
						 * selectedActivity);
						 */
						IStatus status = DependencyChecker.newCheckAct ? DependencyChecker
								.checkCircularForApplyingVariabilityElement(
										selectedActivity, act, true)
								: DependencyChecker.checkCircularDependency(
										act, selectedActivity);
						if (!status.isOK()) {
							String title = AuthoringUIResources.activity_variability_error_title;
							AuthoringUIPlugin.getDefault().getMsgDialog()
									.displayError(title, status);
							itor.remove();
						}
					}
					if (selection.isEmpty()) {
						return;
					}
				}
			}
			ActivityDropCommand cmd = new ActivityDropCommand(selectedActivity,
					selection, null, getAdapterFactory());
			IStatus status = cmd.checkExtend();
			if (!status.isOK()) {
				String title = AuthoringUIResources.activity_variability_error_title;
				AuthoringUIPlugin.getDefault().getMsgDialog().displayError(
						title, status.getMessage());
				return;
			}
			cmd.setType(IActionTypeProvider.EXTEND);
			getActionManager().execute(cmd);
		}
	};

	protected IAction deepCopyActivityAction = new Action(
			AuthoringUIResources.ProcessEditor_action_deepCopy) {
		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.action.Action#run()
		 */
		public void run() {
			LibraryLockingOperationRunner runner = new LibraryLockingOperationRunner();
			runner.run(new IRunnableWithProgress() {

				public void run(IProgressMonitor monitor)
						throws InvocationTargetException, InterruptedException {
					MethodConfiguration config = LibraryService.getInstance()
							.getCurrentMethodConfiguration();
					if (config == null) {
						config = TngUtil.getOwningProcess(selectedActivity)
								.getDefaultContext();
					}
					List selection = DialogHelper.selectActivitiesFor(
							selectedActivity, config,
							IActionTypeProvider.DEEP_COPY);

					if (selection == null || selection.isEmpty()) {
						return;
					} else {
						for (Iterator itor = selection.iterator(); itor
								.hasNext();) {
							Object o = itor.next();

							if (o instanceof Activity) {
								Activity act = (Activity) o;

								// Process proc =
								// TngUtil.getOwningProcess(selectedBreakdownElement);
								// if (ProcessUtil.hasCyclicDependency(act,
								// proc)) {
								// Object[] args = { selectedActivity.getName(),
								// act.getName() };
								// String message = AuthoringUIResources
								// .bind(
								// AuthoringUIResources.apply_pattern_error_msg,
								// args);
								//
								// String title =
								// AuthoringUIResources.apply_pattern_error_title;
								// AuthoringUIPlugin.getDefault().getMsgDialog()
								// .displayError(title, message, "");
								// //$NON-NLS-1$
								// itor.remove();
								// }

								IStatus status = DependencyChecker
										.checkCircularDependency(act,
												selectedActivity);
								if (!status.isOK()) {
									String title = AuthoringUIResources.activity_variability_error_title;
									AuthoringUIPlugin.getDefault()
											.getMsgDialog().displayError(title,
													status.getMessage());
									itor.remove();
								}
							}
							if (selection.isEmpty()) {
								return;
							}
						}
					}

					ActivityDropCommand cmd = new ActivityDropCommand(
							selectedActivity, selection, null,
							getAdapterFactory(),new ActivityDeepCopyConfigurator(config));
					cmd.setType(IActionTypeProvider.DEEP_COPY);
					getActionManager().execute(cmd);

				}
			});
		}
	};

	protected Object selectedObject;

	protected IAction expandAllAction = new Action(
			AuthoringUIResources.ProcessFormEditor_expanAllActionLabel) {
		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.action.Action#run()
		 */
		public void run() {
			Viewer viewer = ((ProcessEditor) activeEditor).currentViewer;
			if (viewer instanceof TreeViewer) {
				((TreeViewer) viewer).expandToLevel(selectedObject,
						AbstractTreeViewer.ALL_LEVELS);
			}
		}
	};

	protected IAction collapseAllAction = new Action(
			AuthoringUIResources.ProcessFormEditor_collapseAllActionLabel) {
		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.action.Action#run()
		 */
		public void run() {
			Viewer viewer = ((ProcessEditor) activeEditor).currentViewer;
			if (viewer instanceof TreeViewer) {
				((TreeViewer) viewer).collapseToLevel(selectedObject,
						AbstractTreeViewer.ALL_LEVELS);
			}
		}
	};

	protected SynchronizationAction manualSynchronizeAction = new SynchronizationAction() {
		public void run() {
			super.run();

			// refresh properties view after synch
			EPFPropertySheetPage page = (EPFPropertySheetPage) ((ProcessEditor) activeEditor)
					.getPropertySheetPage();
			if (page != null) {
				page.refresh();
			}
		}
	};

	protected ProcessAutoSynchronizeAction autoSynchronize = new ProcessAutoSynchronizeAction() {
		private void superRun() {
			super.run();
		}

		/**
		 * @see org.eclipse.epf.authoring.ui.actions.ProcessAutoSynchronizeAction#run()
		 */
		public void run() {
			LibraryLockingOperationRunner runner = new LibraryLockingOperationRunner();
			runner.run(new IRunnableWithProgress() {

				public void run(IProgressMonitor monitor)
						throws InvocationTargetException, InterruptedException {
					if (!AuthoringUIPlugin
							.getDefault()
							.getMsgDialog()
							.displayConfirmation(
									AuthoringUIResources.ProcessEditor_defaultSynchronization,
									AuthoringUIResources.ProcessEditor_confirmAutoSynch)) {
						return;
					}
					// ((SynchronizeCommand)command).setUIContext(getActiveEditor().getSite().getShell());
					superRun();

					// refresh properties view after synch
					EPFPropertySheetPage page = (EPFPropertySheetPage) ((ProcessEditor) activeEditor)
							.getPropertySheetPage();
					if (page != null) {
						page.refresh();
					}
				}
			});

		}
	};

	private IAction showInLibraryView = new Action(
			AuthoringUIResources.ProcessEditor_Action_ShowLinkedElementinLibraryView,
			AuthoringUIPlugin.getDefault().getImageDescriptor(
					"full/etool16/show_linked_element.gif")) { //$NON-NLS-1$   //) 

		public void run() {
			final Object linkedElement = ProcessUtil
					.getAssociatedElement((Descriptor) selectedBreakdownElement);
			if (linkedElement != null) {
				Display.getCurrent().asyncExec(new Runnable() {

					public void run() {
						LibraryView.getView().setSelectionToViewer(
								linkedElement);
					}

				});
			}
		}

	};

	private IAction assignUserDiagram = new Action(
			AuthoringUIResources.ProcessEditor_Action_AssignUserDiagram) {
		/**
		 * @see org.eclipse.jface.action.Action#run()
		 */
		public void run() {
			UserDefinedDiagramDialog dialog = new UserDefinedDiagramDialog(
					Display.getCurrent().getActiveShell(), activeEditor,
					selectedActivity, getProcess().getDefaultContext(), TngUtil
							.isWrapped(selectedObject));

			dialog.open();
		}
	};

	// private IAction activityDetailDiagramInfo = new
	// ActivityDetailDiagramInfoAction(){
	// public Object getObject() {
	// return selectedActivity;
	// }
	// public IWorkbenchPage getPagex() {
	// // TODO Auto-generated method stub
	// return getPage();
	// }
	// };

	private boolean oldLocked;

	private IndentAction indentAction = new IndentAction() {
		public boolean updateSelection(IStructuredSelection selection) {
			if (!isEditingAllowed()) {
				return false;
			}
			return super.updateSelection(selection);
		}
	};

	private OutdentAction outdentAction = new OutdentAction() {
		@Override
		public boolean updateSelection(IStructuredSelection selection) {
			if (!isEditingAllowed()) {
				return false;
			}
			return super.updateSelection(selection);
		}
	};

	public ProcessEditorActionBarContributor() {
		super(AuthoringUIResources.ProcessEditor);

		// don't show validate action
		//
		validateAction = null;

		// set disabled image descriptors
		showInLibraryView.setDisabledImageDescriptor(AuthoringUIPlugin
				.getDefault().getImageDescriptor(
						"full/etool16/show_linked_element_disabled.gif")); //$NON-NLS-1$			
	}

	public ProcessEditorActionBarContributor(String name) {
        super(name);
    }

	protected void openDiagram(int diagramType) {
		try {
			DiagramEditorInput input = new DiagramEditorInput(
					selectedObject, getSuppression(), diagramType);
			DiagramEditorUtil.openDiagramEditor(getPage(), input,
					DiagramEditorHelper.getDiagramPreferencesHint(diagramType),
					new NullProgressMonitor());
		} catch (RuntimeException e) {
			AuthoringUIPlugin.getDefault().getLogger().logError(e);
		}
	}

	protected void registerAction(IAction action) {
		if (action != null && !registeredActions.contains(action)) {
			registeredActions.add(action);
		}
	}

	protected void registerActions() {
		registerAction(autoSynchronize);
		registerAction(manualSynchronizeAction);
		registerAction(updateSuppressionFromBaseAction);
		registerAction(indentAction);
		registerAction(outdentAction);
		registerAction(moveDownAction);
		registerAction(moveUpAction);
	}

	/**
	 * @see org.eclipse.emf.edit.ui.action.EditingDomainActionBarContributor#update()
	 */
	public void update() {
		super.update();

		ISelectionProvider selectionProvider = activeEditor instanceof ISelectionProvider ? (ISelectionProvider) activeEditor
				: activeEditor.getEditorSite().getSelectionProvider();
		ISelection selection = selectionProvider.getSelection();
		IStructuredSelection structuredSelection = selection instanceof IStructuredSelection ? (IStructuredSelection) selection
				: StructuredSelection.EMPTY;

		boolean editingAllowed = isEditingAllowed();
		for (IAction action : registeredActions) {
			if (action instanceof CommandActionHandler) {
				action.setEnabled(((CommandActionHandler) action)
						.updateSelection(structuredSelection));
			}
			else if (action instanceof IModifyingAction) {
				action.setEnabled(editingAllowed);
			}
		}
	}

	/**
	 * @see org.eclipse.emf.edit.ui.action.EditingDomainActionBarContributor#activate()
	 */
	public void activate() {
		manualSynchronizeAction.setProcess(getProcess());
		ISelectionProvider selectionProvider = activeEditor instanceof ISelectionProvider ? (ISelectionProvider) activeEditor
				: activeEditor.getEditorSite().getSelectionProvider();
		for (Iterator iter = registeredActions.iterator(); iter.hasNext();) {
			Object action = (Object) iter.next();
			if (action instanceof IWorkbenchPartAction) {
				((IWorkbenchPartAction) action)
						.setActiveWorkbenchPart(activeEditor);
			}
			if (action instanceof ISelectionChangedListener) {
				selectionProvider
						.addSelectionChangedListener((ISelectionChangedListener) action);
			}
		}
		super.activate();

		// call set Active page explicitly in order to get correct action
		// bars
		setActivePage(activeEditor);
	}

	/**
	 * @see org.eclipse.emf.edit.ui.action.EditingDomainActionBarContributor#deactivate()
	 */
	public void deactivate() {
		super.deactivate();

		ISelectionProvider selectionProvider = activeEditor instanceof ISelectionProvider ? (ISelectionProvider) activeEditor
				: activeEditor.getEditorSite().getSelectionProvider();

		for (Iterator iter = registeredActions.iterator(); iter.hasNext();) {
			Object action = (Object) iter.next();
			if (action instanceof IWorkbenchPartAction) {
				((IWorkbenchPartAction) action).setActiveWorkbenchPart(null);
			}
			if (action instanceof ISelectionChangedListener) {
				selectionProvider
						.removeSelectionChangedListener((ISelectionChangedListener) action);
			}
		}
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.actions.MethodLibraryActionBarContributor#init(org.eclipse.ui.IActionBars)
	 */
	public void init(IActionBars actionBars) {
		super.init(actionBars);
		registerActions();
	}

	protected IActionManager getActionManager() {
		return ((MethodElementEditor) activeEditor).getActionManager();
	}

	protected AdapterFactory getAdapterFactory() {
		return ((ProcessEditor) activeEditor).getAdapterFactory();
	}

	protected void setAllDiagramSuppressed(final boolean suppressed) {
		String title = AuthoringUIResources.processFormEditorSaveDialog_title;
		String message = AuthoringUIResources.processFormEditorSaveDialog_message1;

		if (!AuthoringUIPlugin.getDefault().getMsgDialog().displayConfirmation(
				title, message)) {
			return;
		}

		BusyIndicator.showWhile(activeEditor.getEditorSite().getShell()
				.getDisplay(), new Runnable() {

			public void run() {
				GraphicalDataHelper.setAllDiagramSuppressed(getProcess(),
						suppressed);
				doSaveActiveEditor();
			}

		});
	}

	private void publishDiagramsForProcess(final boolean publish) {
		String title = AuthoringUIResources.processFormEditorSaveDialog_title;
		String message = AuthoringUIResources.processFormEditorSaveDialog_message1;

		if (!AuthoringUIPlugin.getDefault().getMsgDialog().displayConfirmation(
				title, message)) {
			return;
		}

		BusyIndicator.showWhile(activeEditor.getEditorSite().getShell()
				.getDisplay(), new Runnable() {

			public void run() {

				Iterator iterator = new AbstractTreeIterator(getProcess()) {

					/**
					 * Comment for <code>serialVersionUID</code>
					 */
					private static final long serialVersionUID = -618949014476371114L;

					protected Iterator getChildren(Object object) {
						Activity act = (Activity) object;
						ArrayList children = new ArrayList();
						for (Iterator iterator = act.getBreakdownElements()
								.iterator(); iterator.hasNext();) {
							Object element = iterator.next();
							if (element instanceof Activity) {
								children.add(element);
							}
						}
						return children.iterator();
					}

				};
				try {
					while (iterator.hasNext()) {
						Activity activity = (Activity) iterator.next();
						DiagramOptions.setPublishAD(activity, publish);
						DiagramOptions.setPublishADD(activity, publish);
						DiagramOptions.setPublishWPDD(activity, publish);
					}
				} catch (Exception e) {
					AuthoringUIPlugin.getDefault().getLogger().logError(e);
				}
				doSaveActiveEditor();
			}
		});
	}

	private boolean promptSaveActiveEditor() {
		String title = AuthoringUIResources.processFormEditorSaveDialog_title;
		String message = AuthoringUIResources.processFormEditorSaveDialog_message2;
		if (activeEditor.isDirty()) {
			return AuthoringUIPlugin.getDefault().getMsgDialog()
					.displayConfirmation(title, message);
		}
		return true;
	}

	protected void saveActiveEditor() {
		// save the editor
		//
		BusyIndicator.showWhile(activeEditor.getEditorSite().getShell()
				.getDisplay(), new Runnable() {

			public void run() {
				doSaveActiveEditor();
			}

		});
	}

	protected void doSaveActiveEditor() {
		((ProcessEditor) activeEditor).resourcesToSave.add(getProcess()
				.eResource());
		activeEditor.doSave(new NullProgressMonitor());
	}

	protected DeleteAction createDeleteAction() {
		return new ProcessDeleteAction() {
			protected void saveCurrentEditor() {
				if (activeEditor.isDirty()) {
					BusyIndicator.showWhile(activeEditor.getEditorSite()
							.getShell().getDisplay(), new Runnable() {

						public void run() {
							activeEditor.doSave(new NullProgressMonitor());
						}

					});
				}
			}

			public void run() {
				IFormPage activePage = ((ProcessEditor) activeEditor)
						.getActivePageInstance();
				if (activePage != null) {
					String id = activePage.getId();
					if (id == ProcessEditor.WBS_FORM_ID
							|| id == ProcessEditor.TA_FORM_ID
							|| id == ProcessEditor.WPBS_FORM_ID
							|| id == ProcessEditor.CONSOLIDATED_FORM_ID) {
						// for all other pages, disable DELETE
						super.run();
					}
				}
			}
		};
	}

	protected CutAction createCutAction() {
		return new CutAction() {
			public boolean updateSelection(IStructuredSelection selection) {
				return super.updateSelection(ProcessDeleteAction
						.filterSelection(selection));
			}

			@Override
			public Command createCommand(Collection selection) {
				boolean unexecutable = false;
				for (Iterator iter = selection.iterator(); iter.hasNext();) {
					Object element = (Object) iter.next();
					if (element instanceof Activity) {
						unexecutable = true;
					}
				}
				if (unexecutable) {
					return UnexecutableCommand.INSTANCE;
				} else {
					return super.createCommand(selection);
				}
			}
		};
	}

	protected PasteAction createPasteAction() {
		return new PasteAction() {
			public boolean updateSelection(IStructuredSelection selection) {
				return super.updateSelection(ProcessDeleteAction
						.filterSelection(selection));
			}

			/**
			 * Overrided to use ResourceAwarePasteFromClipboardCommand
			 * 
			 * @see org.eclipse.emf.edit.ui.action.PasteAction#createCommand(java.util.Collection)
			 * @see org.eclipse.epf.library.edit.command.ResourceAwarePasteFromClipboardCommand
			 */
			public Command createCommand(Collection selection) {
				if (selection.size() == 1) {
					return new ResourceAwarePasteFromClipboardCommand(domain,
							selection.iterator().next(), null,
							CommandParameter.NO_INDEX);
				} else {
					return UnexecutableCommand.INSTANCE;
				}
			}
		};
	}

	protected CopyAction createCopyAction() {
		return new CopyAction() {

			public void run() {
				if (domain instanceof TraceableAdapterFactoryEditingDomain) {
					((TraceableAdapterFactoryEditingDomain) domain)
							.resetCopyMaps();
				}
				super.run();
			}

		};
	}

	/**
	 * @see org.eclipse.emf.edit.ui.action.EditingDomainActionBarContributor#addGlobalActions(org.eclipse.jface.action.IMenuManager)
	 */
	protected void addGlobalActions(IMenuManager menuManager) {
		super.addGlobalActions(menuManager);
		menuManager.insertAfter("additions", new Separator("fixed-additions")); //$NON-NLS-1$ //$NON-NLS-2$
		// menuManager.insertBefore("fixed-additions",
		// insertNewPhaseAction);
	}

	public void contributeToToolBar(IToolBarManager toolBarManager) {
		super.contributeToToolBar(toolBarManager);

		showInLibraryView
				.setToolTipText(AuthoringUIResources.ProcessEditor_Action_ShowLinkedElementinLibraryView);
		toolBarManager.add(moveUpAction);
		toolBarManager.add(moveDownAction);
		toolBarManager.add(indentAction);
		toolBarManager.add(outdentAction);
		toolBarManager.add(showInLibraryView);
	}

	protected boolean isEditingAllowed() {
		IFormPage activePage = ((ProcessEditor) activeEditor)
				.getActivePageInstance();
		return !(activePage instanceof ProcessBreakdownStructureFormPage && ((ProcessBreakdownStructureFormPage) activePage)
				.basicIsReadOnly());
	}

	/**
	 * @see org.eclipse.jface.action.IMenuListener#menuAboutToShow(org.eclipse.jface.action.IMenuManager)
	 */
	public void menuAboutToShow(IMenuManager menuManager) {
		IFormPage activePage = ((ProcessEditor) activeEditor)
				.getActivePageInstance();
		if (!isEditingAllowed()) {
			menuManager.add(new Separator(
					IWorkbenchActionConstants.MB_ADDITIONS));
			menuManager.add(showInLibraryView);
			if (bsItemProvider != null
			// && ((ITreeItemContentProvider)
			// bsItemProvider).hasChildren(selectedObject)
			) {
				menuManager.add(expandAllAction);
				menuManager.add(collapseAllAction);
			}
			menuManager.add(new Separator());
			refreshViewerAction.setEnabled(refreshViewerAction.isEnabled());
			menuManager.add(refreshViewerAction);
			menuManager.add(showPropertiesViewAction);
			return;
		}

		ISelectionProvider selectionProvider = (ISelectionProvider) activeEditor;

		if (selectedActivity == null
				&& (selectionProvider.getSelection() == null || selectionProvider
						.getSelection().isEmpty())) {
			// fake a selection change event with the process selected if no
			// element is selected yet.
			//
			selectionChanged(new SelectionChangedEvent(selectionProvider,
					new StructuredSelection(getProcess())));
		}
		// disable all new action if it's rolled up
		boolean isRolledUP = bsItemProvider != null
				&& bsItemProvider.isRolledUp();
		super.setEnabled(!isRolledUP);
		super.menuAboutToShow(menuManager);

		// add markers
		//
		menuManager.insertAfter("fixed-additions", new Separator("preference")); //$NON-NLS-1$ //$NON-NLS-2$	
		menuManager.appendToGroup("preference", preferenceAction); //$NON-NLS-1$

		menuManager.insertAfter("fixed-additions", new Separator("open")); //$NON-NLS-1$ //$NON-NLS-2$

		if (bsItemProvider instanceof BreakdownElementWrapperItemProvider
				&& TngUtil.unwrap(bsItemProvider) instanceof Activity) {
			menuManager.insertBefore(IWorkbenchActionConstants.MB_ADDITIONS,
					contributeAction);
			menuManager.insertBefore(IWorkbenchActionConstants.MB_ADDITIONS,
					replaceAction);
			menuManager.insertBefore(IWorkbenchActionConstants.MB_ADDITIONS,
					localReplacementAndDeepCopy);
		}
		
		addCustomizeActionToMenu(menuManager);
				
		if (!isRolledUP) {
			if (createChildActions != null && !createChildActions.isEmpty()) {
				menuManager.insertBefore(
						IWorkbenchActionConstants.MB_ADDITIONS, addAction);
			}
		}

		if (revealAction.isEnabled()) {
			menuManager.insertBefore("fixed-additions", revealAction); //$NON-NLS-1$
		}

		if (suppressAction.isEnabled()) {
			menuManager.insertBefore("fixed-additions", suppressAction); //$NON-NLS-1$
		}
		if (!isRolledUP) {
			if (suppressTaskAction.isEnabled()) {
				menuManager.insertBefore("fixed-additions", suppressTaskAction); //$NON-NLS-1$
			}
			if (revealTaskAction.isEnabled()) {
				menuManager.insertBefore("fixed-additions", revealTaskAction); //$NON-NLS-1$
			}
		}
		if (updateSuppressionFromBaseAction.isEnabled()) {
			menuManager.insertBefore(
					"fixed-additions", updateSuppressionFromBaseAction); //$NON-NLS-1$
		}
		
		boolean toAdd = true;
		if (selectedBreakdownElement instanceof TaskDescriptor) {
			if (DescriptorPropUtil.getDesciptorPropUtil().getGreenParent(
					(TaskDescriptor) selectedBreakdownElement) != null) {
				toAdd = false;
			}
		}

		if (toAdd && (selectedBreakdownElement != null) && (bsItemProvider != null)) {
			menuManager.insertBefore("fixed-additions", moveUpAction); //$NON-NLS-1$
			menuManager.insertBefore("fixed-additions", moveDownAction); //$NON-NLS-1$
		}

		// Indent/outdent
		//
		menuManager.insertBefore("fixed-additions", indentAction); //$NON-NLS-1$				
		menuManager.insertBefore("fixed-additions", outdentAction); //$NON-NLS-1$

		menuManager.appendToGroup("open", showInLibraryView); //$NON-NLS-1$

		if ((selectedActivity != null) && (bsItemProvider != null)) {
			if (bsItemProvider.isRolledUp()) {
				rollupAction
						.setText(AuthoringUIResources.ProcessEditor_Action_RollDown);
			} else {
				rollupAction
						.setText(AuthoringUIResources.ProcessEditor_Action_RollUp);
			}
			menuManager.appendToGroup("open", rollupAction); //$NON-NLS-1$				
		}

		if (selectedObject instanceof Activity) {
			// "Reuse" menu
			//
			MenuManager reuseSubMenu = new MenuManager(
					AuthoringUIResources.ProcessEditor_Action_ApplyPattern);
			reuseSubMenu.add(copyActivityAction);
			reuseSubMenu.add(extendActivityAction);
			reuseSubMenu.add(deepCopyActivityAction);

			menuManager.insertBefore(IWorkbenchActionConstants.MB_ADDITIONS,
					reuseSubMenu);
		}

		if (autoSynchronize.isEnabled() && ! ProcessUtil.isSynFree()) {
			menuManager.insertBefore(IWorkbenchActionConstants.MB_ADDITIONS,
					autoSynchronize);
		}
		if (manualSynchronizeAction.isEnabled() && ! ProcessUtil.isSynFree()) {
			menuManager.insertBefore(IWorkbenchActionConstants.MB_ADDITIONS,
					manualSynchronizeAction);
		}

		if (!(selectedElement instanceof Milestone)) {
			// MenuManager diagramSubMenu = new MenuManager(
			// AuthoringUIResources.ProcessEditor_Action_Diagrams);
			if (selectedActivity != null) {
				if (selectedActivity.getVariabilityBasedOnElement() != null) {
					menuManager.appendToGroup("open", openBaseActivity); //$NON-NLS-1$
				}

				// diagramSubMenu.add(openWorkflowEditorAction);
				// diagramSubMenu.add(openWorkflowDetailEditorAction);
				// diagramSubMenu.add(openWPDependencyEditorAction);
				// diagramSubMenu.add(suppressDiagramAction);
				// diagramSubMenu.add(assignUserDiagram);
			}
			// if (selectedActivity == getProcess()) {
			// diagramSubMenu.add(suppressAllDiagrams);
			// diagramSubMenu.add(revealAllDiagrams);
			// }
			// Enable this code if ActivityDetailDiagramInfoDialog is need.
			// if(selectedActivity != null){
			// diagramSubMenu.add(new Separator());
			// diagramSubMenu.add(activityDetailDiagramInfo);
			// }
			// menuManager.appendToGroup("open", diagramSubMenu); //$NON-NLS-1$

			if (selectedActivity != null) {
				menuManager.appendToGroup("open", newActivityDiagramEditor); //$NON-NLS-1$
			}
			
			MenuManager newDiagramSubMenu = new MenuManager(
					AuthoringUIResources.ProcessEditor_Action_Diagrams); 
			if (selectedActivity != null) {
				//newDiagramSubMenu.add(newActivityDiagramEditor);				
				newDiagramSubMenu.add(newActivityDetailDiagramEditor);
				newDiagramSubMenu.add(newWPDiagramEditor);
				newDiagramSubMenu.add(new Separator());
				newDiagramSubMenu.add(deleteActivityDiagram);
				newDiagramSubMenu.add(deleteActivityDetailDiagram);				
				newDiagramSubMenu.add(deleteWPDiagram);
				newDiagramSubMenu.add(new Separator());
				newDiagramSubMenu.add(newSuppressDiagramAction);
				newDiagramSubMenu.add(newAssignUserDiagram);
			}

			if (selectedActivity == getProcess()) {
				newDiagramSubMenu.add(diagramsPublishAll);
				newDiagramSubMenu.add(diagramPublishNone);
			}
			menuManager.appendToGroup("open", newDiagramSubMenu); //$NON-NLS-1$
		}

		if (bsItemProvider != null
		// this is an expensive check, removed it
		//
		// && ((ITreeItemContentProvider)
		// bsItemProvider).hasChildren(selectedObject)
		) {
			menuManager.appendToGroup("open", expandAllAction); //$NON-NLS-1$
			menuManager.appendToGroup("open", collapseAllAction); //$NON-NLS-1$
		}
	}

	private void addCustomizeActionToMenu(IMenuManager menuManager) {
		if (! ProcessUtil.isSynFree()) {
			return;
		}

		if (!(bsItemProvider instanceof BreakdownElementWrapperItemProvider)) {
			return;
		}

		if (!(TngUtil.unwrap(bsItemProvider) instanceof TaskDescriptor)) {
			return;
		}

		BreakdownElementWrapperItemProvider provider = (BreakdownElementWrapperItemProvider) bsItemProvider;
		if (!ProcessUtil.isInherited(provider)) {
			return;
		}

		Object parentObj = provider.getParent(null);
		Activity parentAct = parentObj instanceof Activity ? (Activity) parentObj
				: null;
		if (parentAct == null) {
			return;
		}

		VariabilityType extendType = parentAct.getVariabilityType();
		if (extendType != VariabilityType.LOCAL_CONTRIBUTION
				&& extendType != VariabilityType.EXTENDS) {
			return;
		}

		if (provider.getTopItem() != ProcessUtil.getProcess(parentAct)) {
			return;
		}
		menuManager.appendToGroup(IWorkbenchActionConstants.MB_ADDITIONS,
				customizeAction); //$NON-NLS-1$		

	}

	/**
	 * @return
	 */
	protected Process getProcess() {
		return ((ProcessComponent) ((MethodElementEditorInput) activeEditor
				.getEditorInput()).getMethodElement()).getProcess();
	}

	protected Suppression getSuppression() {
		return ((ProcessEditor) activeEditor).suppression;
	}

	protected void depopulateDynamicMenuItems() {
		// Remove any menu items for old selection.
		//
		if (createChildMenuManager != null) {
			depopulateManager(createChildMenuManager, createChildActions);
		}
		if (createSiblingMenuManager != null) {
			depopulateManager(createSiblingMenuManager, createSiblingActions);
		}
		createChildActions = null;
		createSiblingActions = null;
	}

	/**
	 * Set action state to enabled or disabled
	 * 
	 * @param locked
	 */
	protected void setActionState() {
		if (locked) {
			// disable modifying actions
			pasteAction.setEnabled(false);
			cutAction.setEnabled(false);
			deleteAction.setEnabled(false);
			
			for(IAction action : registeredActions) {
				if(action instanceof IModifyingAction) {
					action.setEnabled(false);
				}
			}
		} else if (!oldLocked) {
			// update state of modifying actions
			//
			IStructuredSelection selection = (IStructuredSelection) selectionProvider
					.getSelection();
			cutAction.setEnabled(cutAction.updateSelection(selection));
			pasteAction.setEnabled(pasteAction.updateSelection(selection));
			deleteAction.setEnabled(deleteAction.updateSelection(selection));
			
			for(IAction action : registeredActions) {
				if(action instanceof IModifyingAction) {
					action.setEnabled(action instanceof CommandActionHandler ? 
							((CommandActionHandler)action).updateSelection(selection) : true);
				}
			}
		}

		boolean enabled = !locked;

		addAction.setEnabled(enabled);
		contributeAction.setEnabled(enabled);

		// state of these actions is set in updateActions()
		//
		// moveDownAction.setEnabled(enabled);
		// moveUpAction.setEnabled(enabled);
		// revealAction.setEnabled(enabled);
		// suppressAction.setEnabled(enabled);
		replaceAction.setEnabled(enabled);
		localReplacementAndDeepCopy.setEnabled(enabled);
		revealAllDiagrams.setEnabled(enabled);
		suppressAllDiagrams.setEnabled(enabled);
		suppressDiagramAction.setEnabled(enabled);
		copyActivityAction.setEnabled(enabled);
		extendActivityAction.setEnabled(enabled);
		deepCopyActivityAction.setEnabled(enabled);
	}

	@Override
	public void setActiveEditor(IEditorPart part) {
		super.setActiveEditor(part);
		if (! ProcessUtil.isSynFree() || part == null) {
			return;
		}
		IEditorPart editor = getActiveEditor();
		if (editor instanceof ProcessEditor) {
			ProcessEditor pEditor = (ProcessEditor) editor;
			Process proc = pEditor.getSelectedProcess();
			boolean specialUpdateDueToBrowsed = false;
			if (ProcessScopeUtil.getInstance().isConfigFree(proc)) {
				if (ConfigurationHelper.getDelegate().isAutoSyncedByBrowsing()) {
					specialUpdateDueToBrowsed = true;
				}
			} 
			if (specialUpdateDueToBrowsed) {
				pEditor.updateConfigFreeProcessModelAndRefresh();
				ConfigurationHelper.getDelegate().setAutoSyncedByBrowsing(false);
			} else {
				pEditor.updateAndRefreshProcessModel();				
			}
		}
	}
	
	protected Collection generateCreateChildActions(Collection descriptors,
			ISelection selection) {
		if (locked) {
			return null;
		}
		return super.generateCreateChildActions(descriptors, selection);
	}

	protected Collection generateCreateSiblingActions(Collection descriptors,
			ISelection selection) {
		if (locked) {
			return null;
		}
		return super.generateCreateSiblingActions(descriptors, selection);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.actions.MethodLibraryActionBarContributor#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
	 */
	public void selectionChanged(SelectionChangedEvent event) {
		ISelection sel = event.getSelection();
		if (sel instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) sel;
			oldLocked = locked;
			locked = ViewHelper.isLocked(structuredSelection);

			selection = ((IStructuredSelection) sel).toList();
			if (selection.size() == 1) {
				selectedObject = ((IStructuredSelection) sel).getFirstElement();
				Object obj = TngUtil.unwrap(selectedObject);
				super.selectionChanged(event);
				selectedElement = obj;
				if (obj instanceof Activity) {
					selectedActivity = (Activity) obj;
				} else {
					// depopulateDynamicMenuItems();
					// bsItemProvider = null;
					selectedActivity = null;
				}
				AdapterFactoryEditingDomain domain = (AdapterFactoryEditingDomain) ((IEditingDomainProvider) activeEditor)
						.getEditingDomain();
				Object providerObj = domain.getAdapterFactory().adapt(
						selectedObject, ITreeItemContentProvider.class);
				if (providerObj instanceof IBSItemProvider)
					bsItemProvider = (IBSItemProvider) providerObj;

				if (obj instanceof BreakdownElement) {
					selectedBreakdownElement = (BreakdownElement) obj;
				} else {
					selectedBreakdownElement = null;
				}
			} else {
				depopulateDynamicMenuItems();
				bsItemProvider = null;
				selectedElement = null;
				selectedActivity = null;
				selectedBreakdownElement = null;
				selectedObject = null;
			}

			updateActions();

		} else {
			depopulateDynamicMenuItems();
			bsItemProvider = null;
			selectedElement = null;
			selectedActivity = null;
			selectedBreakdownElement = null;
		}

		// // test code
		// //
		// if(selectedActivity != null) {
		// for (Iterator iter =
		// selectedActivity.eClass().getEAllAttributes().iterator();
		// iter.hasNext();) {
		// EAttribute attrib = (EAttribute) iter.next();
		// if(String.class.isAssignableFrom(attrib.getEAttributeType().getInstanceClass()))
		// {
		// List values =
		// ConfigurationHelper.getStringAttribute(selectedActivity, attrib);
		// System.out.println();
		// System.out.println(attrib.getName() + ": " + values);
		// }
		// }
		// }
	}

	/**
	 * Updates actions including enabling/disabling them based on selection
	 */
	protected void updateActions() {
		showInLibraryView
				.setEnabled(selectedBreakdownElement instanceof Descriptor
						&& ProcessUtil
								.getAssociatedElement((Descriptor) selectedBreakdownElement) != null);

		if (!locked && selection != null && !selection.isEmpty()) {
			if (selection.size() == 1) {				
				Object o = selection.get(0);
				AdapterFactory adapterFactory = getAdapterFactory();
				if(ProcessUtil.isRolledUpDescriptor(o, adapterFactory)) {
					revealAction.setEnabled(false);
					suppressAction.setEnabled(false);
				}
				else {
					if (getSuppression().canReveal(selection)) {
						revealAction.setEnabled(true);
						suppressAction.setEnabled(false);
					} else if (getSuppression().canSuppress(selection)) {
						suppressAction.setEnabled(true);
						revealAction.setEnabled(false);
					} else {
						revealAction.setEnabled(false);
						suppressAction.setEnabled(false);
					}
				}
			} else {
				revealAction.setEnabled(getSuppression().canReveal(selection));
				suppressAction.setEnabled(getSuppression().canSuppress(
						selection));
			}

			// Action Enable/Disable for Suppress Task/Reveal Task context menu
			boolean show = true;
			for (int i = 0; i < selection.size(); i++) {
				if (getAdapterFactory() == TngAdapterFactory.INSTANCE
						.getWBS_ComposedAdapterFactory()) {

					if (!(selection.get(i) instanceof Activity || selection
							.get(i) instanceof ActivityWrapperItemProvider)) {
						show = false;
						break;
					}
				} else
					show = false;
			}
			if (show) {
				List taskDescriptors = ProcessUtil
						.getTaskDescriptors(selection);
				suppressTaskAction.setEnabled(getSuppression().canSuppress(
						taskDescriptors));
				revealTaskAction.setEnabled(getSuppression().canReveal(
						taskDescriptors));
			} else {
				suppressTaskAction.setEnabled(false);
				revealTaskAction.setEnabled(false);
			}

			// boolean hasInherited = false;
			// for (Iterator iter = selection.iterator(); iter.hasNext();) {
			// Object item = iter.next();
			// if (item instanceof BreakdownElementWrapperItemProvider
			// && ((BreakdownElementWrapperItemProvider) item)
			// .isReadOnly()) {
			// hasInherited = true;
			// break;
			// }
			// }
			// updateSuppressionFromBaseAction.setEnabled(hasInherited);
		} else {
			revealAction.setEnabled(false);
			suppressAction.setEnabled(false);
			suppressTaskAction.setEnabled(false);
			revealTaskAction.setEnabled(false);
		}

		// Set Activity Detail Diagram Action enable state.
		if (selectedObject instanceof Activity
				|| selectedObject instanceof ActivityWrapperItemProvider) {
//			setActionStateForADD();
			setDiagramsContextMenuState();
		}

		// change action state for locked plugin
		setActionState();
	}

	public void setActivePage(IEditorPart part) {
		if (this.activeEditor != null) {
			int page = ((ProcessEditor) this.activeEditor).getActivePage();
			IActionBars actionBars = this.activeEditor.getEditorSite()
					.getActionBars();

			// TODO - need to get IFormPage and compare on that rather than
			// integer page id. These id will change if we reposition pages.
			if (page == 0) {			
				actionBars.setGlobalActionHandler(ActionFactory.DELETE.getId(),
						null);
				actionBars.setGlobalActionHandler(ActionFactory.CUT.getId(),
						null);
				actionBars.setGlobalActionHandler(ActionFactory.COPY.getId(),
						null);
				actionBars.setGlobalActionHandler(ActionFactory.PASTE.getId(),
						null);
				actionBars.setGlobalActionHandler(ActionFactory.UNDO.getId(),
						null);
				actionBars.setGlobalActionHandler(ActionFactory.REDO.getId(),
						null);
			} else if (page == 4) {
				actionBars.clearGlobalActionHandlers();
			}
			else {
				actionBars.setGlobalActionHandler(ActionFactory.DELETE.getId(),
						deleteAction);
				actionBars.setGlobalActionHandler(ActionFactory.CUT.getId(),
						cutAction);
				actionBars.setGlobalActionHandler(ActionFactory.COPY.getId(),
						copyAction);
				actionBars.setGlobalActionHandler(ActionFactory.PASTE.getId(),
						pasteAction);
				actionBars.setGlobalActionHandler(ActionFactory.UNDO.getId(),
						undoAction);
				actionBars.setGlobalActionHandler(ActionFactory.REDO.getId(),
						redoAction);
			}
			actionBars.updateActionBars();
		}
	}
	
	private void superDoRefresh() {
		super.doRefresh();
	}

	protected void doRefresh() {
		if (activeEditor instanceof IViewerProvider) {
			Display display = activeEditor.getEditorSite().getShell().getDisplay();
			BusyIndicator.showWhile(display, new Runnable() {

				public void run() {
					// update IDs
					//
					AdapterFactory adapterFactory = getAdapterFactory();
					if (adapterFactory == TngAdapterFactory.INSTANCE
							.getWBS_ComposedAdapterFactory()
							|| adapterFactory == TngAdapterFactory.INSTANCE
									.getProcessComposedAdapterFactory()) {
						Process proc = getProcess();
						ProcessUtil.updateIDs(adapterFactory, proc);
//						long time = System.currentTimeMillis();
						ProcessUtil.refreshPredeccessorLists(adapterFactory, proc);
//						System.out.println("refreshPredeccessorLists() took: " + (System.currentTimeMillis() - time));
					}

					superDoRefresh();
				}
				
			});
		}

	}

	/*
	 * Set Action state for Activity Detail Diagram in Context Menu on selection
	 * of Activity. If an Activity consist atleast one taskdescriptor and its
	 * primary performer in current configuration, then state is enabled else
	 * state is disabled in context menu (process editor).
	 */
	protected void setActionStateForADD() {

		// set ADD Action state false
		openWorkflowDetailEditorAction.setEnabled(false);

		// set AD Action state true always
		openWorkflowEditorAction.setEnabled(true);

		// set WPD Diagram Menu Action state tru always
		openWPDependencyEditorAction.setEnabled(true);

		if (selectedObject != null) {

			if (getSuppression().isSuppressed(selectedObject)) {
				openWorkflowDetailEditorAction.setEnabled(false);
				openWorkflowEditorAction.setEnabled(false);
				openWPDependencyEditorAction.setEnabled(false);
				return;
			}

			Activity activity = null;
			List list = new ArrayList();

			if (selectedObject instanceof Activity) {
				activity = selectedActivity;
			} else if (selectedObject instanceof ActivityWrapperItemProvider) {
				activity = (Activity) ((ActivityWrapperItemProvider) selectedObject)
						.getValue();
			}
			if (activity == null)
				return;

			while (!activity.getVariabilityType().equals(
					VariabilityType.NA)) {

				VariabilityElement ve = activity.getVariabilityBasedOnElement();
				// If Activity is set to local contribution,
				// need to get breakdown elements of local contributed
				// activity + base activity. And base activity breakdown
				// elements
				// are collected after while loop ends)
				// if (VariabilityType.CONTRIBUTES_LITERAL.equals(activity
				// .getVariabilityType())) {
				// list.addAll(activity.getBreakdownElements());
				// }
				list.addAll(activity.getBreakdownElements());

				if ((ve != null) && (ve instanceof Activity)) {
					activity = (Activity) ve;
				} else {
					break;
				}
			}

			// For AD diagram menu option, if Base Activity donot have any
			// AD diagram, donot show AD diagram option in context menu for
			// readonly activity.
			Diagram diagram = GraphicalDataManager.getInstance().getUMADiagram(
					activity, GraphicalDataHelper.ACTIVITY_DIAGRAM, false);
			if (diagram == null
					&& selectedObject instanceof ActivityWrapperItemProvider) {
				openWorkflowEditorAction.setEnabled(false);
			}

			// For WPD diagram menu option, if Base Activity donot have any
			// WPD diagram, donot show WPD diagram option in context menu
			// for readonly activity.
			diagram = GraphicalDataManager.getInstance().getUMADiagram(
					activity,
					GraphicalDataHelper.WORK_PRODUCT_DEPENDENCY_DIAGRAM, false);
			if (diagram == null
					&& selectedObject instanceof ActivityWrapperItemProvider) {
				openWPDependencyEditorAction.setEnabled(false);
			}

			// Collect the breakdown elements to verifty taskdescriptors are
			// not.
			list.addAll(activity.getBreakdownElements());

			// For ADD diagram menu option, if Base Activity donot have any
			// ADD diagram, donot show ADD diagram option in context menu
			// for
			// readonly activity.
			diagram = GraphicalDataManager.getInstance().getUMADiagram(
					activity, GraphicalDataHelper.ACTIVITY_DETAIL_DIAGRAM,
					false);
			if (diagram == null
					&& selectedObject instanceof ActivityWrapperItemProvider) {
				openWorkflowDetailEditorAction.setEnabled(false);
			} else {
				// Iterate through the List, to find taskdescriptor and has
				// primary role within current configuration.
				// then enable the action.
				findRoleDesc:
				for (Iterator<?> iterator = list.iterator(); iterator.hasNext();) {
					Object obj = iterator.next();
					if (obj instanceof TaskDescriptor) {
						if (!getSuppression().isSuppressed(obj)) {
							List<RoleDescriptor> primaryPerformers = ((TaskDescriptor) obj)
									.getPerformedPrimarilyBy();
							AdapterFactoryEditingDomain domain = (AdapterFactoryEditingDomain) ((IEditingDomainProvider) activeEditor)
									.getEditingDomain();
							AdapterFactory factory = domain.getAdapterFactory();
							if (factory instanceof ExposedAdapterFactory) {
								IFilter filter = ((ExposedAdapterFactory) factory)
										.getFilter();
								for (RoleDescriptor e : primaryPerformers) {
									if (filter != null && filter.accept(e)
											&& !getSuppression().isSuppressed(e)) {
										openWorkflowDetailEditorAction
												.setEnabled(true);
										break findRoleDesc;
									}									
								}
							}
						}
					}
				}
			}
		}
	}

	/*
	 * Set Action state for Open Diagram Actions in Context Menu on
	 * selection of Activity. Checking condition to handle if activity suppressed, if activity don't have
	 * any taskdescriptor and primary performer, if base activity don't have diagram. 
	 */
	private void setDiagramsContextMenuState() {

		// set ADD Action state false
		newActivityDetailDiagramEditor.setEnabled(false);

		// set AD Action state true always
		newActivityDiagramEditor.setEnabled(true);

		// set WPD Diagram Menu Action state tru always
		newWPDiagramEditor.setEnabled(true);

		if (selectedObject != null) {

			if (getSuppression().isSuppressed(selectedObject)) {
				newActivityDetailDiagramEditor.setEnabled(false);
				newActivityDiagramEditor.setEnabled(false);
				newWPDiagramEditor.setEnabled(false);
				return;
			}

			Object unwrapped = TngUtil.unwrap(selectedObject);
			if(!(unwrapped instanceof Activity)) {
				return;
			}
			Activity activity = (Activity) unwrapped;
			List<Object> list = new ArrayList<Object>();
			
			//TODO: check for existing diagrams in immediate base instead of root base
			
			while (!activity.getVariabilityType().equals(
					VariabilityType.NA)) {

				VariabilityElement ve = activity.getVariabilityBasedOnElement();
				list.addAll(activity.getBreakdownElements());

				if ((ve != null) && (ve instanceof Activity)) {
					activity = (Activity) ve;
				} else {
					break;
				}
			}

			org.eclipse.gmf.runtime.notation.Diagram diagram = ((ProcessEditor) getActiveEditor())
					.getDiagramEditorHelper().getDiagram(activity,
							IDiagramManager.ACTIVITY_DIAGRAM);
			if (diagram == null
					&& selectedObject instanceof ActivityWrapperItemProvider) {
				newActivityDiagramEditor.setEnabled(false);
			}

			// For WPD diagram menu option, if Base Activity donot have any
			// WPD diagram, donot show WPD diagram option in context menu
			// for readonly activity.
			diagram = ((ProcessEditor) getActiveEditor())
					.getDiagramEditorHelper().getDiagram(activity,
							IDiagramManager.WORK_PRODUCT_DEPENDENCY_DIAGRAM);
			if (diagram == null
					&& selectedObject instanceof ActivityWrapperItemProvider) {
				newWPDiagramEditor.setEnabled(false);
			}

			// Collect the breakdown elements to verifty taskdescriptors are
			// not.
			list.addAll(activity.getBreakdownElements());

			// For ADD diagram menu option, if Base Activity donot have any
			// ADD diagram, donot show ADD diagram option in context menu
			// for
			// readonly activity.
			diagram = ((ProcessEditor) getActiveEditor())
					.getDiagramEditorHelper().getDiagram(activity,
							IDiagramManager.ACTIVITY_DETAIL_DIAGRAM);

			if (diagram == null
					&& selectedObject instanceof ActivityWrapperItemProvider) {
				newActivityDetailDiagramEditor.setEnabled(false);
			} else {
				// Iterate through the List, to find taskdescriptor and has
				// primary role within current configuration.
				// then enable the action.
				findRoleDesc:
				for (Iterator<?> iterator = list.iterator(); iterator.hasNext();) {
					Object obj = iterator.next();
					if (obj instanceof TaskDescriptor) {
						if (!getSuppression().isSuppressed(obj)) {
							List<RoleDescriptor> primaryPerformers = ((TaskDescriptor) obj)
									.getPerformedPrimarilyBy();
							AdapterFactoryEditingDomain domain = (AdapterFactoryEditingDomain) ((IEditingDomainProvider) activeEditor)
									.getEditingDomain();
							AdapterFactory factory = domain.getAdapterFactory();
							if (factory instanceof ExposedAdapterFactory) {
								IFilter filter = ((ExposedAdapterFactory) factory)
										.getFilter();
								for (RoleDescriptor e : primaryPerformers) {
									if (filter != null && filter.accept(e)
											&& !getSuppression().isSuppressed(e)) {
										newActivityDetailDiagramEditor
												.setEnabled(true);
										break findRoleDesc;
									}
								}
							}
						}
					}
				}
			}
		}
		
		//for the diagram delete menus
		updateDiagramDeleteMenu();
	}
	
	private void updateDiagramDeleteMenu() {
		deleteActivityDiagram.setEnabled(false);
		deleteActivityDetailDiagram.setEnabled(false);				
		deleteWPDiagram.setEnabled(false);

		DiagramManager mgr = DiagramManager.getInstance(getProcess(), this);		
		try {
			List<org.eclipse.gmf.runtime.notation.Diagram> diagrams = mgr.getDiagrams(selectedActivity,
					IDiagramManager.ACTIVITY_DIAGRAM);
			if (diagrams.size() > 0) {
				deleteActivityDiagram.setEnabled(true);
			}
			
			diagrams = mgr.getDiagrams(selectedActivity, IDiagramManager.ACTIVITY_DETAIL_DIAGRAM);
			if (diagrams.size() > 0) {
				deleteActivityDetailDiagram.setEnabled(true);
			}
			
			diagrams = mgr.getDiagrams(selectedActivity, IDiagramManager.WORK_PRODUCT_DEPENDENCY_DIAGRAM);
			if (diagrams.size() > 0) {
				deleteWPDiagram.setEnabled(true);
			}			
		} catch (Exception e) {
			if (e instanceof IllegalArgumentException) {
				//this can happen and is normal when selection is an green activity
				return;
			}
			AuthoringUIPlugin.getDefault().getLogger().logError(e);
		}		
	}
}
