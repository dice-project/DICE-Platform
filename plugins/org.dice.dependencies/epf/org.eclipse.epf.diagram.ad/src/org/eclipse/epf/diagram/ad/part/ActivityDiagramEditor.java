/*
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial implementation
 *
 */
package org.eclipse.epf.diagram.ad.part;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.DelegatingLayout;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.LayeredPane;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreUtil.UsageCrossReferencer;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.epf.diagram.ad.edit.parts.ControlFlowEditPart;
import org.eclipse.epf.diagram.ad.edit.parts.ControlFlowNameEditPart;
import org.eclipse.epf.diagram.ad.providers.UMLElementTypes;
import org.eclipse.epf.diagram.add.ADDImages;
import org.eclipse.epf.diagram.add.part.ActivityDetailDiagramEditorPlugin;
import org.eclipse.epf.diagram.core.DiagramCorePlugin;
import org.eclipse.epf.diagram.core.DiagramCoreResources;
import org.eclipse.epf.diagram.core.bridge.BridgeHelper;
import org.eclipse.epf.diagram.core.bridge.DiagramAdapter;
import org.eclipse.epf.diagram.core.bridge.NodeAdapter;
import org.eclipse.epf.diagram.core.part.AbstractDiagramEditor;
import org.eclipse.epf.diagram.core.part.DiagramEditorInput;
import org.eclipse.epf.diagram.core.part.DiagramFileEditorInputProxy;
import org.eclipse.epf.diagram.core.part.util.DiagramEditorUtil;
import org.eclipse.epf.diagram.core.util.DiagramConstants;
import org.eclipse.epf.diagram.model.util.TxUtil;
import org.eclipse.epf.library.edit.process.ActivityWrapperItemProvider;
import org.eclipse.epf.library.edit.util.IDiagramManager;
import org.eclipse.epf.library.edit.util.Suppression;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.CapabilityPattern;
import org.eclipse.epf.uma.DeliveryProcess;
import org.eclipse.epf.uma.Iteration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Phase;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.VariabilityType;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.diagram.ui.actions.ActionIds;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramRootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateUnspecifiedTypeConnectionRequest;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDocumentProvider;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorMatchingStrategy;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.SaveAsDialog;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ActivityParameterNode;
import org.eclipse.uml2.uml.ControlFlow;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * @modified
 * @author skannoor
 * @author Phong Nguyen Le
 */
public class ActivityDiagramEditor extends AbstractDiagramEditor {

	/**
	 * @generated
	 */
	public static final String ID = "org.eclipse.epf.diagram.ad.part.ActivityDiagramEditorID"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public ActivityDiagramEditor() {
		super(true);
	}

	/**
	 * @modified
	 */
	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();
		DiagramRootEditPart root = (DiagramRootEditPart) getDiagramGraphicalViewer()
				.getRootEditPart();
		LayeredPane printableLayers = (LayeredPane) root
				.getLayer(LayerConstants.PRINTABLE_LAYERS);
		FreeformLayer extLabelsLayer = new FreeformLayer();
		extLabelsLayer.setLayoutManager(new DelegatingLayout());
//		printableLayers.addLayerAfter(extLabelsLayer,
//				UMLEditPartFactory.EXTERNAL_NODE_LABELS_LAYER,
//				LayerConstants.PRIMARY_LAYER);
		LayeredPane scalableLayers = (LayeredPane) root
				.getLayer(LayerConstants.SCALABLE_LAYERS);
		FreeformLayer scaledFeedbackLayer = new FreeformLayer();
		scaledFeedbackLayer.setEnabled(false);
		scalableLayers.addLayerAfter(scaledFeedbackLayer,
				LayerConstants.SCALED_FEEDBACK_LAYER,
				DiagramRootEditPart.DECORATION_UNPRINTABLE_LAYER);
	}
	
	/**
	 * @generated
	 */
//	protected PaletteRoot createPaletteRoot(PaletteRoot existingPaletteRoot) {
//		PaletteRoot root = super.createPaletteRoot(existingPaletteRoot);
//		new UMLPaletteFactory().fillPalette(root);
//		return root;
//	}

	/**
	 * @modified
	 */
	protected PreferencesHint getPreferencesHint() {
		return new PreferencesHint(ActivityDiagramEditorPlugin.ID);
	}

	/**
	 * @modified
	 */
	public Object getAdapter(Class type) {
//		if (type == IPropertySheetPage.class) {
//			return null;
//		}
		return super.getAdapter(type);
	}

	/**
	 * @modified
	 */
	protected IDocumentProvider getDocumentProvider(IEditorInput input) {
//		if (input instanceof IFileEditorInput
//				|| input instanceof URIEditorInput) {
//			return ActivityDiagramEditorPlugin.getInstance()
//					.getDocumentProvider();
//		}
		return super.getDocumentProvider(input);
	}

	/**
	 * @modified
	 */
	public TransactionalEditingDomain getEditingDomain() {
//		IDocument document = getEditorInput() != null ? getDocumentProvider()
//				.getDocument(getEditorInput()) : null;
//		if (document instanceof IDiagramDocument) {
//			return ((IDiagramDocument) document).getEditingDomain();
//		}
//		return super.getEditingDomain();
		return getDiagramManager().getEditingDomain();
	}

	/**
	 * @modified
	 */
	protected void setDocumentProvider(IEditorInput input) {
//		if (input instanceof IFileEditorInput
//				|| input instanceof URIEditorInput) {
//			setDocumentProvider(ActivityDiagramEditorPlugin.getInstance()
//					.getDocumentProvider());
//		} else {
//			super.setDocumentProvider(input);
//		}
		super.setDocumentProvider(input);
	}

	/**
	 * @generated
	 */
//	public void gotoMarker(IMarker marker) {
//		MarkerNavigationService.getInstance().gotoMarker(this, marker);
//	}

	/**
	 * @generated
	 */
	public boolean isSaveAsAllowed() {
		return super.isSaveAsAllowed();
	}

	/**
	 * @modified
	 */
	public void doSaveAs() {
//		super.doSave(new NullProgressMonitor())
		performSaveAs(new NullProgressMonitor());
	}

	/**
	 * @generated
	 */
	protected void performSaveAs(IProgressMonitor progressMonitor) {
		Shell shell = getSite().getShell();
		IEditorInput input = getEditorInput();
		SaveAsDialog dialog = new SaveAsDialog(shell);
		IFile original = input instanceof IFileEditorInput ? ((IFileEditorInput) input)
				.getFile()
				: null;
		if (original != null) {
			dialog.setOriginalFile(original);
		}
		dialog.create();
		IDocumentProvider provider = getDocumentProvider();
		if (provider == null) {
			// editor has been programmatically closed while the dialog was open
			return;
		}
		if (provider.isDeleted(input) && original != null) {
			String message = NLS.bind(
					"The original file ''{0}'' has been deleted.", original
							.getName());
			dialog.setErrorMessage(null);
			dialog.setMessage(message, IMessageProvider.WARNING);
		}
		if (dialog.open() == Window.CANCEL) {
			if (progressMonitor != null) {
				progressMonitor.setCanceled(true);
			}
			return;
		}
		IPath filePath = dialog.getResult();
		if (filePath == null) {
			if (progressMonitor != null) {
				progressMonitor.setCanceled(true);
			}
			return;
		}
		IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		IFile file = workspaceRoot.getFile(filePath);
		final IEditorInput newInput = new FileEditorInput(file);
		// Check if the editor is already open
		IEditorMatchingStrategy matchingStrategy = getEditorDescriptor()
				.getEditorMatchingStrategy();
		IEditorReference[] editorRefs = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage()
				.getEditorReferences();
		for (int i = 0; i < editorRefs.length; i++) {
			if (matchingStrategy.matches(editorRefs[i], newInput)) {
				MessageDialog
						.openWarning(shell, "Problem During Save As...",
								"Save could not be completed. Target file is already open in another editor.");
				return;
			}
		}
		boolean success = false;
		try {
			provider.aboutToChange(newInput);
			getDocumentProvider(newInput).saveDocument(progressMonitor,
					newInput,
					getDocumentProvider().getDocument(getEditorInput()), true);
			success = true;
		} catch (CoreException x) {
			IStatus status = x.getStatus();
			if (status == null || status.getSeverity() != IStatus.CANCEL) {
				ErrorDialog.openError(shell, "Save Problems",
						"Could not save file.", x.getStatus());
			}
		} finally {
			provider.changed(newInput);
			if (success) {
				setInput(newInput);
			}
		}
		if (progressMonitor != null) {
			progressMonitor.setCanceled(!success);
		}
	}

	/**
	 * @param event
	 */
	protected void handleDoubleClick(Event event) {
		List editParts = getGraphicalViewer().getSelectedEditParts();

		// do nothing if 0 or more than 1 edit parts are selected
		//
		if (editParts.size() != 1)
			return;

		EditPart editPart = (EditPart) editParts.get(0);
		Node node = (Node) editPart.getModel();
		EObject model = node.getElement();
		DiagramFileEditorInputProxy editorInput = (DiagramFileEditorInputProxy) getEditorInput();
		DiagramEditorInput dInput = editorInput.getDiagramEditorInput();

		if (model instanceof org.eclipse.uml2.uml.Activity) {
			NodeAdapter nodeAdapter = BridgeHelper.getNodeAdapter(model);
			Object e = null;
			// TODO: handle Wrapper change below line after wrapper implement
			e = nodeAdapter.getElement();
			if (e == null) {
				e = BridgeHelper.getMethodElement((EModelElement) model);
			}
			if (e != null) {
				//				IEditorPart parent = getSite().getPage().getActiveEditor();
				DiagramEditorInput input = new org.eclipse.epf.diagram.core.part.DiagramEditorInput(
						e, dInput.getSuppression(),
						IDiagramManager.ACTIVITY_DIAGRAM);
				DiagramEditorUtil.openDiagramEditor(getSite().getPage(), input,
						ActivityDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT,
						new NullProgressMonitor());

			}
		}

	}

	@Override
	protected void createActions() {
		super.createActions();
		IAction action = new Action(
				DiagramCoreResources.ActivityDiagram_openActivityDetailDiagram_text) { //$NON-NLS-1$
			public String getId() {
				return DiagramConstants.OPEN_ACTIVITY_DETAIL_DIAGRAM;
			}

			public void run() {
				try {
					EditPart selectedPart = (EditPart) getGraphicalViewer()
							.getSelectedEditParts().get(0);
					//					IEditorPart parent = getSite().getPage().getActiveEditor();
					if (selectedPart != null) {
						Node view = (Node) selectedPart.getModel();
						DiagramEditPart diagramEditPart = getDiagramEditPart();
						Diagram diagram = (Diagram) diagramEditPart.getModel();
						DiagramAdapter diagramAdapter = BridgeHelper
								.getDiagramAdapter(diagram.getElement());

						NodeAdapter adapter = BridgeHelper.getNodeAdapter(view
								.getElement());
						Object selectedObject = adapter.getWrapper();
						if (selectedObject == null) {
							selectedObject = adapter.getElement();
						}
						// IEditorPart parent =
						// PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
						DiagramEditorInput input = new org.eclipse.epf.diagram.core.part.DiagramEditorInput(
								selectedObject,
								diagramAdapter.getSuppression(),
								IDiagramManager.ACTIVITY_DETAIL_DIAGRAM);
						DiagramEditorUtil
								.openDiagramEditor(
										getSite().getPage(),
										input,
										ActivityDetailDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT,
										new NullProgressMonitor());
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
				View node = (View) editPart.getModel();
				if (node != null
						&& node.getElement() != null
						&& node.getElement() instanceof org.eclipse.uml2.uml.StructuredActivityNode) {
					if (enableADDMenu(node.getElement())) {
						return true;
					}
				}
				return false;
			}
		};
		action.setImageDescriptor(ADDImages.IMG_DESC_EDITOR);
		action.setDisabledImageDescriptor(ADDImages.DISABLED_IMG_DESC_EDITOR);
		getActionRegistry().registerAction(action);

		List<IElementType> elementTypes = new ArrayList<IElementType>();
		elementTypes.clear();
		elementTypes.add(UMLElementTypes.ActivityPartition_1008);
		elementTypes.add(UMLElementTypes.ActivityPartition_2001);
		action = createAnAction(
				DiagramCoreResources.ActivityDiagram_Partition_Node_text,
				elementTypes, //$NON-NLS-1$
				DiagramConstants.CREATE_PARTITION,
				DiagramCoreResources.ActivityDiagram_Partition_Node_tooltip,
				UMLElementTypes.getImageDescriptor(UMLPackage.eINSTANCE
						.getActivityPartition(),
						UMLElementTypes.ActivityPartition_2001)); //$NON-NLS-1$

		getActionRegistry().registerAction(action);

		elementTypes = new ArrayList<IElementType>();
		elementTypes.add(UMLElementTypes.InitialNode_1004);
		action = createAnAction(
				DiagramCoreResources.ActivityDiagram_StartNode_text,
				elementTypes, //$NON-NLS-1$
				DiagramConstants.CREATE_START_NODE,
				DiagramCoreResources.ActivityDiagram_StartNode_tooltip, //$NON-NLS-1$
				UMLElementTypes.getImageDescriptor(UMLPackage.eINSTANCE
						.getInitialNode(), UMLElementTypes.InitialNode_1004)); //$NON-NLS-1$
		getActionRegistry().registerAction(action);

		elementTypes = new ArrayList<IElementType>();
		elementTypes.add(UMLElementTypes.ForkNode_1003);
		action = createAnAction(
				DiagramCoreResources.ActivityDiagram_Fork_Node_text,
				elementTypes, //$NON-NLS-1$
				DiagramConstants.CREATE_FORK_NODE,
				DiagramCoreResources.ActivityDiagram_Fork_Node_tooltip,
				UMLElementTypes.getImageDescriptor(UMLPackage.eINSTANCE
						.getForkNode(), UMLElementTypes.ForkNode_1003)); //$NON-NLS-1$

		getActionRegistry().registerAction(action);

		elementTypes = new ArrayList<IElementType>();
		elementTypes.add(UMLElementTypes.JoinNode_1006);
		action = createAnAction(
				DiagramCoreResources.ActivityDiagram_Join_Node_text,
				elementTypes, //$NON-NLS-1$
				DiagramConstants.CREATE_JOIN_NODE,
				DiagramCoreResources.ActivityDiagram_Join_Node_tooltip,
				UMLElementTypes.getImageDescriptor(UMLPackage.eINSTANCE
						.getJoinNode(), UMLElementTypes.JoinNode_1006)); //$NON-NLS-1$

		getActionRegistry().registerAction(action);

		elementTypes = new ArrayList<IElementType>();
		elementTypes.add(UMLElementTypes.DecisionNode_1005);
		action = createAnAction(
				DiagramCoreResources.ActivityDiagram_DecisionNode_text,
				elementTypes, //$NON-NLS-1$
				DiagramConstants.CREATE_DECISION_NODE,
				DiagramCoreResources.ActivityDiagram_DecisionNode_tooltip,
				UMLElementTypes.getImageDescriptor(UMLPackage.eINSTANCE
						.getDecisionNode(), UMLElementTypes.DecisionNode_1005)); //$NON-NLS-1$

		getActionRegistry().registerAction(action);

		elementTypes = new ArrayList<IElementType>();
		elementTypes.add(UMLElementTypes.MergeNode_1002);
		action = createAnAction(
				DiagramCoreResources.ActivityDiagram_Merge_Node_text,
				elementTypes, //$NON-NLS-1$
				DiagramConstants.CREATE_MERGE_NODE,
				DiagramCoreResources.ActivityDiagram_Merge_Node_tooltip,
				UMLElementTypes.getImageDescriptor(UMLPackage.eINSTANCE
						.getDecisionNode(), UMLElementTypes.MergeNode_1002)); //$NON-NLS-1$

		getActionRegistry().registerAction(action);

		elementTypes = new ArrayList<IElementType>();
		elementTypes.add(UMLElementTypes.ActivityFinalNode_1001);
		action = createAnAction(
				DiagramCoreResources.ActivityDiagram_EndNode_text,
				elementTypes, //$NON-NLS-1$
				DiagramConstants.CREATE_END_NODE,
				DiagramCoreResources.ActivityDiagram_EndNode_tooltip,
				UMLElementTypes.getImageDescriptor(UMLPackage.eINSTANCE
						.getActivityFinalNode(),
						UMLElementTypes.ActivityFinalNode_1001)); //$NON-NLS-1$

		getActionRegistry().registerAction(action);

		elementTypes = new ArrayList<IElementType>();
		elementTypes.add(UMLElementTypes.StructuredActivityNode_1007);
		action = createAnAction(
				DiagramCoreResources.ActivityDiagram_Activity_text,
				elementTypes, //$NON-NLS-1$
				DiagramConstants.CREATE_ACTIVITY,
				DiagramCoreResources.ActivityDiagram_Activity_tooltip,
				UMLElementTypes.getImageDescriptor(null,
						UMLElementTypes.StructuredActivityNode_1007)); //$NON-NLS-1$

		getActionRegistry().registerAction(action);

		elementTypes = new ArrayList<IElementType>();
		elementTypes.add(UMLElementTypes.StructuredActivityNode_1011);
		action = createAnAction(
				DiagramCoreResources.ActivityDiagram_Iteration_text,
				elementTypes, //$NON-NLS-1$
				DiagramConstants.CREATE_ITERATION,
				DiagramCoreResources.ActivityDiagram_Iteration_tooltip,
				UMLElementTypes.getImageDescriptor(null,
						UMLElementTypes.StructuredActivityNode_1011)); //$NON-NLS-1$

		getActionRegistry().registerAction(action);

		elementTypes = new ArrayList<IElementType>();
		elementTypes.add(UMLElementTypes.StructuredActivityNode_1010);
		action = createAnAction(
				DiagramCoreResources.ActivityDiagram_Phase_text,
				elementTypes, //$NON-NLS-1$
				DiagramConstants.CREATE_PHASE,
				DiagramCoreResources.ActivityDiagram_Phase_tooltip,
				UMLElementTypes.getImageDescriptor(null,
						UMLElementTypes.StructuredActivityNode_1010)); //$NON-NLS-1$

		getActionRegistry().registerAction(action);

		elementTypes = new ArrayList<IElementType>();
		elementTypes.add(UMLElementTypes.ActivityParameterNode_1009);
		action = createAnAction(
				DiagramCoreResources.AbstractDiagram_TaskDescriptor_text,
				elementTypes, //$NON-NLS-1$
				DiagramConstants.CREATE_TASK_DESCRIPTOR,
				DiagramCoreResources.AbstractDiagram_TaskDescriptor_tooltip,
				UMLElementTypes.getImageDescriptor(null,
						UMLElementTypes.ActivityParameterNode_1009)); //$NON-NLS-1$

		getActionRegistry().registerAction(action);

		elementTypes = new ArrayList<IElementType>();
		elementTypes.add(UMLElementTypes.ActivityParameterNode_1012);
		action = createAnAction(
				DiagramCoreResources.ActivityDiagram_Milestone_text,
				elementTypes, //$NON-NLS-1$
				DiagramConstants.CREATE_MILESTONE,
				DiagramCoreResources.ActivityDiagram_Milestone_tooltip,
				UMLElementTypes.getImageDescriptor(null,
						UMLElementTypes.ActivityParameterNode_1012)); //$NON-NLS-1$

		getActionRegistry().registerAction(action);

	}

	protected boolean enableADDMenu(EObject element) {
		NodeAdapter adapter = BridgeHelper
				.getNodeAdapter((ActivityNode) element);
		if (adapter != null) {
			Object selectedObject = adapter.getWrapper();
			if (selectedObject == null) {
				selectedObject = adapter.getElement();
			}
			Activity activity = null;
			List<BreakdownElement> list = new ArrayList<BreakdownElement>();

			if (selectedObject instanceof Activity) {
				activity = (Activity) selectedObject;
			} else if (selectedObject instanceof ActivityWrapperItemProvider) {
				activity = (Activity) ((ActivityWrapperItemProvider) selectedObject)
						.getValue();
			}
			if (activity == null)
				return false;
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
			//Collect the breakdown elements to verify taskdescriptors are
			// not.
			list.addAll(activity.getBreakdownElements());
			Diagram diagram = getDiagram(activity,
					IDiagramManager.ACTIVITY_DETAIL_DIAGRAM);

			if (diagram == null
					&& selectedObject instanceof ActivityWrapperItemProvider) {
				return false;
			} else {
				// Iterate through the List, to find taskdescriptor and has
				// primary role within current configuration.
				// then enable the action.
				DiagramAdapter diagramAdapter = BridgeHelper
						.getDiagramAdapter(getDiagram().getElement());
				Suppression suppression = diagramAdapter.getSuppression();
				for (Iterator<?> iterator = list.iterator(); iterator.hasNext();) {
					Object obj = iterator.next();
					if (obj instanceof TaskDescriptor) {
						if (suppression != null) {
							if (suppression.isSuppressed(obj))
								return false;
						}
						if (diagramAdapter.getSuppression() != null) {
							List<RoleDescriptor> roleDescList = ((TaskDescriptor) obj)
									.getPerformedPrimarilyBy();
							for (RoleDescriptor roleDescriptor : roleDescList) {
								if (!diagramAdapter.getSuppression()
										.isSuppressed(roleDescriptor)) {
									return true;
								}
							}
							return false;
						}
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public void contributeToContextMenu(IMenuManager menu) {
		super.contributeToContextMenu(menu);
		addToMenu(menu, DiagramConstants.OPEN_ACTIVITY_DETAIL_DIAGRAM,
				GEFActionConstants.MB_ADDITIONS, true, true);
		IContributionItem item = menu.find(ActionIds.MENU_DIAGRAM_ADD);
		if (item != null && item instanceof IMenuManager) {
			IMenuManager addMenu = (IMenuManager) item;
			String groupName = DiagramConstants.DIAGRAM_ADD_MENU_GENERAL_GROUP;
			if (getGraphicalViewer().getSelectedEditParts().size() == 0) {
				addToMenu(addMenu, null, groupName, true, true);
				addToMenu(addMenu, DiagramConstants.CREATE_PARTITION,
						groupName, false, true);
				addToMenu(addMenu, DiagramConstants.CREATE_START_NODE,
						groupName, false, true);
				addToMenu(addMenu, DiagramConstants.CREATE_END_NODE, groupName,
						false, true);		
				addToMenu(addMenu, DiagramConstants.CREATE_FORK_NODE,
						groupName, false, true);
				addToMenu(addMenu, DiagramConstants.CREATE_JOIN_NODE, 
						groupName, false, true);
				addToMenu(addMenu, DiagramConstants.CREATE_DECISION_NODE,
						groupName, false, true);
				addToMenu(addMenu, DiagramConstants.CREATE_MERGE_NODE,
						groupName, true, true);	
				addToMenu(addMenu, DiagramConstants.CREATE_ACTIVITY, groupName,
						false, true);
				addToMenu(addMenu, DiagramConstants.CREATE_ITERATION,
						groupName, false, true);
				addToMenu(addMenu, DiagramConstants.CREATE_PHASE, groupName,
						false, true);
				addToMenu(addMenu, DiagramConstants.CREATE_MILESTONE,
						groupName, false, true);
				addToMenu(addMenu, DiagramConstants.CREATE_TASK_DESCRIPTOR,
						groupName, true, true);
				
			}
		}
	}

	@Override
	protected String getPartNamePrefix() {
		MethodElement e = getMethodElementFromInput();
		if (e instanceof Activity) {
			return getTypeName((Activity) e) + ": "; //$NON-NLS-1$
		}
		return ""; //$NON-NLS-1$
	}

	/**
	 * @return
	 */
	protected Image getPartImage() {
		return ActivityDiagramEditorPlugin.getInstance().getSharedImage(
				"full/etool16/ADEditor.gif"); //$NON-NLS-1$
	}

	private static String getTypeName(Activity act) {
		if (act instanceof DeliveryProcess) {
			return DiagramCoreResources.ActvitivityDiagram_DeliveryProcess_text; //$NON-NLS-1$
		} else if (act instanceof CapabilityPattern) {
			return DiagramCoreResources.ActvitivityDiagram_CapabilityPattern_text; //$NON-NLS-1$
		} else if (act instanceof Phase) {
			return DiagramCoreResources.ActvitivityDiagram_Phase_tex; //$NON-NLS-1$
		} else if (act instanceof Iteration) {
			return DiagramCoreResources.ActvitivityDiagram_Iteration_text; //$NON-NLS-1$
		}
		return DiagramCoreResources.ActvitivityDiagram_Activity_text; //$NON-NLS-1$
	}

	/**
	 * Create edge for AD diagram
	 * 
	 * @param sourceNode
	 * @param targetNode
	 * @param actEdge
	 * @return
	 */
	public Edge createEdge(Node sourceNode, Node targetNode,
			ActivityEdge actEdge) {
		if (actEdge instanceof ControlFlow) {
			Edge edge = ViewService.createEdge(sourceNode, targetNode, actEdge,
					new Integer(ControlFlowEditPart.VISUAL_ID).toString(),
					ActivityDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
			ViewService.createNode(edge, actEdge, new Integer(
					ControlFlowNameEditPart.VISUAL_ID).toString(),
					ActivityDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
			return edge;
		}
		return null;
	}

	protected CreateUnspecifiedTypeConnectionRequest getCreateControlFlowRequest() {
		List<IElementType> list = new ArrayList<IElementType>();
		list.add(UMLElementTypes.ControlFlow_3001);
		CreateUnspecifiedTypeConnectionRequest connectionRequest = new CreateUnspecifiedTypeConnectionRequest(
				list, false,
				ActivityDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
		return connectionRequest;
	}
	
	@Override
	protected boolean isOrphan(EObject modelElement) {
		return (modelElement instanceof StructuredActivityNode || modelElement instanceof ActivityParameterNode)
				&& BridgeHelper.getMethodElement(modelElement) == null;
	}
}
