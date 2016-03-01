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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.epf.authoring.gef.edit.ActivityDiagramEditPart;
import org.eclipse.epf.authoring.gef.edit.ActivityDiagramEditPartFactory;
import org.eclipse.epf.authoring.gef.util.DiagramUIResources;
import org.eclipse.epf.authoring.gef.util.TemplateConstants;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.diagram.model.Diagram;
import org.eclipse.epf.diagram.model.LinkedObject;
import org.eclipse.epf.diagram.model.ModelFactory;
import org.eclipse.epf.diagram.model.Node;
import org.eclipse.epf.diagram.model.TypedNode;
import org.eclipse.epf.diagram.model.WorkBreakdownElementNode;
import org.eclipse.epf.diagram.model.util.GraphicalDataHelper;
import org.eclipse.epf.library.edit.util.Suppression;
import org.eclipse.epf.library.util.ResourceHelper;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.CapabilityPattern;
import org.eclipse.epf.uma.DeliveryProcess;
import org.eclipse.epf.uma.Iteration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Phase;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.ConnectionCreationToolEntry;
import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.PaletteSeparator;
import org.eclipse.gef.palette.PanningSelectionToolEntry;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;


/**
 * An editor for graphical workflow
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class GraphicalWorkflowEditor extends AbstractDiagramEditor {

	private static final String OPEN_WORKFLOW_DETAIL_DIAGRAM = "open_workflow_detail_diagram"; //$NON-NLS-1$

	private static final String CREATE_START_NODE = "create_start_node"; //$NON-NLS-1$

	private static final String CREATE_END_NODE = "create_end_node"; //$NON-NLS-1$

	private static final String CREATE_SYNC_BAR = "create_sync_bar"; //$NON-NLS-1$

	private static final String CREATE_DECISION_BAR = "create_decision_bar"; //$NON-NLS-1$

	private static final String CREATE_ACTIVITY = "create_activity"; //$NON-NLS-1$

	private static final String CREATE_PHASE = "create_phase"; //$NON-NLS-1$

	private static final String CREATE_ITERATION = "create_iteration"; //$NON-NLS-1$

	private static final String CREATE_TASK_DESCRIPTOR = "create_task_descriptor"; //$NON-NLS-1$

	private CreationFactory activityNodeCreationFactory;

	private CreationFactory phaseNodeCreationFactory;

	private CreationFactory iterationNodeCreationFactory;

	private CreationFactory synchBarCreationFactory;

	private CreationFactory decisionNodeCreationFactory;

	private CreationFactory startNodeCreationFactory;

	private CreationFactory endNodeCreationFactory;

	private CreationFactory taskDescriptorNodeCreationFactory;

	/**
	 * Create an instance
	 */
	public GraphicalWorkflowEditor() {
		super();
	}

	protected void initializeCreationFactories() {
		super.initializeCreationFactories();

		if (activityNodeCreationFactory == null)
			activityNodeCreationFactory = new CreationFactory() {

				public Object getNewObject() {
					// ActivityNode node =
					// ModelFactory.eINSTANCE.createActivityNode();
					WorkBreakdownElementNode node = ModelFactory.eINSTANCE
							.createWorkBreakdownElementNode();
					Diagram diagram = (Diagram) editPart.getModel();
					node.setUMADiagram(diagram.getUMADiagram());
					node.setDiagram(diagram);
					node.setObject(UmaFactory.eINSTANCE.createActivity());
					return node;
				}

				public Object getObjectType() {
					return TemplateConstants.ACTIVITY;
				}

			};
		CreationFactory creationFactory = activityNodeCreationFactory;
		templateNameToCreationFactoryMap.put(creationFactory.getObjectType(),
				creationFactory);

		if (phaseNodeCreationFactory == null)
			phaseNodeCreationFactory = new CreationFactory() {

				public Object getNewObject() {
					// ActivityNode node =
					// ModelFactory.eINSTANCE.createActivityNode();
					WorkBreakdownElementNode node = ModelFactory.eINSTANCE
							.createWorkBreakdownElementNode();
					Diagram diagram = (Diagram) editPart.getModel();
					node.setUMADiagram(diagram.getUMADiagram());
					node.setDiagram(diagram);
					node.setObject(UmaFactory.eINSTANCE.createPhase());
					return node;
				}

				public Object getObjectType() {
					return TemplateConstants.PHASE;
				}

			};
		creationFactory = phaseNodeCreationFactory;
		templateNameToCreationFactoryMap.put(creationFactory.getObjectType(),
				creationFactory);

		if (iterationNodeCreationFactory == null)
			iterationNodeCreationFactory = new CreationFactory() {

				public Object getNewObject() {
					// ActivityNode node =
					// ModelFactory.eINSTANCE.createActivityNode();
					WorkBreakdownElementNode node = ModelFactory.eINSTANCE
							.createWorkBreakdownElementNode();
					Diagram diagram = (Diagram) editPart.getModel();
					node.setUMADiagram(diagram.getUMADiagram());
					node.setDiagram(diagram);
					node.setObject(UmaFactory.eINSTANCE.createIteration());
					return node;
				}

				public Object getObjectType() {
					return TemplateConstants.ITERATION;
				}

			};
		creationFactory = iterationNodeCreationFactory;
		templateNameToCreationFactoryMap.put(creationFactory.getObjectType(),
				creationFactory);

		if (synchBarCreationFactory == null)
			synchBarCreationFactory = new CreationFactory() {

				public Object getNewObject() {
					TypedNode node = ModelFactory.eINSTANCE.createTypedNode();
					node.setType(TypedNode.SYNCH_BAR);
					node.setObject(GraphicalDataHelper
							.newTypedGraphNode(TypedNode.SYNCH_BAR));
					return node;
				}

				public Object getObjectType() {
					return TemplateConstants.SYNCH_BAR;
				}

			};
		creationFactory = synchBarCreationFactory;
		templateNameToCreationFactoryMap.put(creationFactory.getObjectType(),
				creationFactory);

		if (decisionNodeCreationFactory == null)
			decisionNodeCreationFactory = new CreationFactory() {

				public Object getNewObject() {
					TypedNode node = ModelFactory.eINSTANCE.createTypedNode();
					node.setType(TypedNode.DECISION);
					node.setObject(GraphicalDataHelper
							.newTypedGraphNode(TypedNode.DECISION));
					return node;
				}

				public Object getObjectType() {
					return TemplateConstants.DECISION_NODE;
				}

			};
		creationFactory = decisionNodeCreationFactory;
		templateNameToCreationFactoryMap.put(creationFactory.getObjectType(),
				creationFactory);

		if (startNodeCreationFactory == null)
			startNodeCreationFactory = new CreationFactory() {

				public Object getNewObject() {
					TypedNode node = ModelFactory.eINSTANCE.createTypedNode();
					node.setType(TypedNode.START);
					node.setObject(GraphicalDataHelper
							.newTypedGraphNode(TypedNode.START));
					return node;
				}

				public Object getObjectType() {
					return TemplateConstants.START_NODE;
				}

			};
		creationFactory = startNodeCreationFactory;
		templateNameToCreationFactoryMap.put(creationFactory.getObjectType(),
				creationFactory);

		if (endNodeCreationFactory == null)
			endNodeCreationFactory = new CreationFactory() {

				public Object getNewObject() {
					TypedNode node = ModelFactory.eINSTANCE.createTypedNode();
					node.setType(TypedNode.END);
					node.setObject(GraphicalDataHelper
							.newTypedGraphNode(TypedNode.END));
					return node;
				}

				public Object getObjectType() {
					return TemplateConstants.END_NODE;
				}

			};
		creationFactory = endNodeCreationFactory;
		templateNameToCreationFactoryMap.put(creationFactory.getObjectType(),
				creationFactory);

		if (taskDescriptorNodeCreationFactory == null)
			taskDescriptorNodeCreationFactory = new CreationFactory() {

				public Object getNewObject() {
					WorkBreakdownElementNode node = ModelFactory.eINSTANCE
							.createWorkBreakdownElementNode();
					Diagram diagram = (Diagram) editPart.getModel();
					node.setUMADiagram(diagram.getUMADiagram());
					node.setDiagram(diagram);
					node.setObject(UmaFactory.eINSTANCE.createTaskDescriptor());
					return node;
				}

				public Object getObjectType() {
					return TemplateConstants.TASK_DESCRIPTOR;
				}

			};
		creationFactory = taskDescriptorNodeCreationFactory;
		templateNameToCreationFactoryMap.put(creationFactory.getObjectType(),
				creationFactory);
	}

	protected void contributeToContextMenu(IMenuManager menu) {
		super.contributeToContextMenu(menu);

		boolean canModify = !isReadOnly();

		menu.insertAfter(GEFActionConstants.MB_ADDITIONS, new Separator(
				GEFActionConstants.MB_ADDITIONS));

		IAction action = getActionRegistry().getAction(
				OPEN_WORKFLOW_DETAIL_DIAGRAM);
		if (action.isEnabled()) {
			menu.appendToGroup(GEFActionConstants.MB_ADDITIONS, action);
		}
		menu.insertAfter(GEFActionConstants.MB_ADDITIONS, new Separator(
				GEFActionConstants.MB_ADDITIONS));

		action = getActionRegistry().getAction(CREATE_START_NODE);
		if (action.isEnabled() && canModify) {
			menu.appendToGroup(GEFActionConstants.MB_ADDITIONS, action);
		}

		// action = getActionRegistry().getAction(CREATE_FREE_TEXT);
		// if(action.isEnabled()) {
		// menu.appendToGroup(GEFActionConstants.MB_ADDITIONS, action);
		// }

		action = getActionRegistry().getAction(CREATE_SYNC_BAR);
		if (action.isEnabled() && canModify) {
			menu.appendToGroup(GEFActionConstants.MB_ADDITIONS, action);
		}

		action = getActionRegistry().getAction(CREATE_DECISION_BAR);
		if (action.isEnabled() && canModify) {
			menu.appendToGroup(GEFActionConstants.MB_ADDITIONS, action);
		}

		action = getActionRegistry().getAction(CREATE_END_NODE);
		if (action.isEnabled() && canModify) {
			menu.appendToGroup(GEFActionConstants.MB_ADDITIONS, action);
		}
		menu.insertAfter(GEFActionConstants.MB_ADDITIONS, new Separator(
				GEFActionConstants.MB_ADDITIONS));

		action = getActionRegistry().getAction(CREATE_ACTIVITY);
		if (action.isEnabled() && canModify) {
			menu.appendToGroup(GEFActionConstants.MB_ADDITIONS, action);
		}
		action = getActionRegistry().getAction(CREATE_ITERATION);
		if (action.isEnabled() && canModify) {
			menu.appendToGroup(GEFActionConstants.MB_ADDITIONS, action);
		}

		action = getActionRegistry().getAction(CREATE_PHASE);
		if (action.isEnabled() && canModify) {
			menu.appendToGroup(GEFActionConstants.MB_ADDITIONS, action);
		}

		action = getActionRegistry().getAction(CREATE_TASK_DESCRIPTOR);
		if (action.isEnabled() && canModify) {
			menu.appendToGroup(GEFActionConstants.MB_ADDITIONS, action);
		}
	}

	protected void createActions() {
		super.createActions();

		// Open workflow detail action
		//
		IAction action = new Action(
				DiagramUIResources.ActivityDiagram_openActivityDetailDiagram_text) { 
			public String getId() {
				return OPEN_WORKFLOW_DETAIL_DIAGRAM;
			}

			public void run() {
				try {
					try {
						EditPart selectedEditPart = (EditPart) getGraphicalViewer()
								.getSelectedEditParts().get(0);
						IEditorPart parent = getSite().getPage()
								.getActiveEditor();
						IEditorInput input = new ActivityDetailDiagramEditor.EditorInput(
								(MethodElement) ((Node) selectedEditPart
										.getModel()).getObject(),
								((Diagram) editPart.getModel())
										.getSuppression());
						IEditorPart part = getSite()
								.getPage()
								.openEditor(
										input,
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

			public boolean isEnabled() {
				List editParts = getGraphicalViewer().getSelectedEditParts();
				if (editParts.size() != 1)
					return false;
				EditPart editPart = (EditPart) editParts.get(0);
				return (editPart.getModel() instanceof WorkBreakdownElementNode && ((LinkedObject) editPart
						.getModel()).getObject() instanceof Activity);
			}
		};
		getActionRegistry().registerAction(action);

		// IAction startNodeAction = new Action(DiagramUIResources.
		// getString("DiagramUI.ActivityDiagram.StartNode.text")){ //$NON-NLS-1$
		//			
		// public void run(){
		// CreateRequest request = new CreateRequest();
		// //CreationFactory factory = getFactory(template);
		// if (startNodeCreationFactory == null)
		// return;
		// request.setFactory(startNodeCreationFactory);
		// request.setLocation(new Point(10,10));
		//				
		// if (request.getNewObject() instanceof Node) {
		// Point loc = request.getLocation();
		// //((GraphicalEditPart)getHost()).getFigure().translateToRelative(loc);
		// Diagram parent = (Diagram)editPart.getModel();
		// org.eclipse.gef.commands.Command c = new
		// CreateNodeCommand((Node)request.getNewObject(),
		// parent, loc);
		// getCommandStack().execute(c);
		// }
		// }
		// public String getId(){
		// return CREATE_START_NODE;
		// }
		// public boolean isEnabled(){
		// return true;
		// }
		// public String getToolTipText() {
		// return
		// DiagramUIResources.getString("DiagramUI.ActivityDiagram.StartNode.tooltip");
		// }
		//			
		// };
		// getActionRegistry().registerAction(startNodeAction);
		//		
		//		
		// IAction endNodeAction = new Action(DiagramUIResources.
		// getString("DiagramUI.ActivityDiagram.EndNode.text")){ //$NON-NLS-1$
		//			
		// public void run(){
		// CreateRequest request = new CreateRequest();
		// if (endNodeCreationFactory == null)
		// return;
		// request.setFactory(endNodeCreationFactory);
		// request.setLocation(new Point(10,10));
		//				
		// if (request.getNewObject() instanceof Node) {
		// Point loc = request.getLocation();
		// Diagram parent = (Diagram)editPart.getModel();
		// org.eclipse.gef.commands.Command c = new
		// CreateNodeCommand((Node)request.getNewObject(),
		// parent, loc);
		// getCommandStack().execute(c);
		// }
		// }
		// public String getId(){
		// return CREATE_END_NODE;
		// }
		// public boolean isEnabled(){
		// return true;
		// }
		// public String getToolTipText() {
		// return
		// DiagramUIResources.getString("DiagramUI.ActivityDiagram.EndNode.tooltip");
		// }
		// };
		// getActionRegistry().registerAction(endNodeAction);
		//		
		// IAction syncBarAction = new Action(DiagramUIResources.
		// getString("DiagramUI.ActivityDiagram.SyncBar.text")){ //$NON-NLS-1$
		//			
		// public void run(){
		// CreateRequest request = new CreateRequest();
		// //CreationFactory factory = getFactory(template);
		// if (synchBarCreationFactory == null)
		// return;
		// request.setFactory(synchBarCreationFactory);
		// request.setLocation(new Point(10,10));
		//				
		// if (request.getNewObject() instanceof Node) {
		// Point loc = request.getLocation();
		// //((GraphicalEditPart)getHost()).getFigure().translateToRelative(loc);
		// Diagram parent = (Diagram)editPart.getModel();
		// org.eclipse.gef.commands.Command c = new
		// CreateNodeCommand((Node)request.getNewObject(),
		// parent, loc);
		// getCommandStack().execute(c);
		// }
		// }
		// public String getId(){
		// return CREATE_SYNC_BAR;
		// }
		// public boolean isEnabled(){
		// return true;
		// }
		// public String getToolTipText() {
		// return
		// DiagramUIResources.getString("DiagramUI.ActivityDiagram.SyncBar.tooltip");
		// }
		//			
		// };
		// getActionRegistry().registerAction(syncBarAction);
		//		
		//		
		// IAction decisionBarAction = new Action(DiagramUIResources.
		// getString("DiagramUI.ActivityDiagram.DecisionBar.text")){
		// //$NON-NLS-1$
		//			
		// public void run(){
		// CreateRequest request = new CreateRequest();
		// //CreationFactory factory = getFactory(template);
		// if (decisionNodeCreationFactory == null)
		// return;
		// request.setFactory(decisionNodeCreationFactory);
		// request.setLocation(new Point(10,10));
		//				
		// if (request.getNewObject() instanceof Node) {
		// Point loc = request.getLocation();
		// //((GraphicalEditPart)getHost()).getFigure().translateToRelative(loc);
		// Diagram parent = (Diagram)editPart.getModel();
		// org.eclipse.gef.commands.Command c = new
		// CreateNodeCommand((Node)request.getNewObject(),
		// parent, loc);
		// getCommandStack().execute(c);
		// }
		// }
		// public String getId(){
		// return CREATE_DECISION_BAR;
		// }
		// public boolean isEnabled(){
		// return true;
		// }
		// public String getToolTipText() {
		// return
		// DiagramUIResources.getString("DiagramUI.ActivityDiagram.DecisionBar.tooltip");
		// }
		//			
		// };
		// getActionRegistry().registerAction(decisionBarAction);

		action = createAnAction(
				DiagramUIResources.ActivityDiagram_StartNode_text, startNodeCreationFactory, 
				CREATE_START_NODE,
				DiagramUIResources.ActivityDiagram_StartNode_tooltip, 
				AuthoringUIPlugin.getDefault().getImageDescriptor(
						"stnode_pal16.gif")); //$NON-NLS-1$
		getActionRegistry().registerAction(action);

		action = createAnAction(
				DiagramUIResources.AbstractDiagram_FreeText_text, freeTxtNodeCreationFactory, 
				CREATE_FREE_TEXT,
				DiagramUIResources.AbstractDiagram_FreeText_tooltip, 
				AuthoringUIPlugin.getDefault().getImageDescriptor(
						"freetext_pal16.gif")); //$NON-NLS-1$
		getActionRegistry().registerAction(action);

		action = createAnAction(
				DiagramUIResources.ActivityDiagram_SyncBar_text, synchBarCreationFactory, 
				CREATE_SYNC_BAR, DiagramUIResources.ActivityDiagram_SyncBar_tooltip
				, AuthoringUIPlugin.getDefault().getImageDescriptor(
						"syncbar_pal16.gif")); //$NON-NLS-1$
		getActionRegistry().registerAction(action);

		action = createAnAction(
				DiagramUIResources.ActivityDiagram_DecisionNode_text, decisionNodeCreationFactory, 
				CREATE_DECISION_BAR,
				DiagramUIResources.ActivityDiagram_DecisionNode_tooltip
				, AuthoringUIPlugin.getDefault().getImageDescriptor(
						"decnode_pal16.gif")); //$NON-NLS-1$
		getActionRegistry().registerAction(action);

		action = createAnAction(
				DiagramUIResources.ActivityDiagram_EndNode_text, endNodeCreationFactory, 
				CREATE_END_NODE, DiagramUIResources.ActivityDiagram_EndNode_tooltip
				, AuthoringUIPlugin.getDefault().getImageDescriptor(
						"endnode_pal16.gif")); //$NON-NLS-1$
		getActionRegistry().registerAction(action);

		action = createAnAction(
				DiagramUIResources.ActivityDiagram_Activity_text, activityNodeCreationFactory, 
				CREATE_ACTIVITY,
				DiagramUIResources.ActivityDiagram_Activity_tooltip
				, AuthoringUIPlugin.getDefault().getImageDescriptor(
						"activity16.gif")); //$NON-NLS-1$
		getActionRegistry().registerAction(action);

		action = createAnAction(
				DiagramUIResources.ActivityDiagram_Iteration_text, iterationNodeCreationFactory, 
				CREATE_ITERATION,
				DiagramUIResources.ActivityDiagram_Iteration_tooltip
				, AuthoringUIPlugin.getDefault().getImageDescriptor(
						"Iteration16.gif")); //$NON-NLS-1$
		getActionRegistry().registerAction(action);

		action = createAnAction(
				DiagramUIResources.ActivityDiagram_Phase_text, phaseNodeCreationFactory, 
				CREATE_PHASE, DiagramUIResources.ActivityDiagram_Phase_tooltip
				, AuthoringUIPlugin.getDefault().getImageDescriptor(
						"Phase16.gif")); //$NON-NLS-1$
		getActionRegistry().registerAction(action);

		action = createAnAction(
				DiagramUIResources.AbstractDiagram_TaskDescriptor_text, taskDescriptorNodeCreationFactory, 
				CREATE_TASK_DESCRIPTOR,
				DiagramUIResources.AbstractDiagram_TaskDescriptor_tooltip
				, AuthoringUIPlugin.getDefault().getImageDescriptor(
						"TaskDescriptor.gif")); //$NON-NLS-1$
		getActionRegistry().registerAction(action);
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
		WorkBreakdownElementNode node = null;
		if (editPart.getModel() instanceof WorkBreakdownElementNode
				&& (node = (WorkBreakdownElementNode) editPart.getModel()).getObject() instanceof Activity) {
			// open activity diagram of the selected activity in a new editor
			//
			try {
				try {
					BreakdownElementEditorInput editorInput = (BreakdownElementEditorInput) getEditorInput();
					Object object = node.isReadOnly() ? node.getWrapper() : node.getObject();
					IEditorInput input = new EditorInput(object, editorInput.getSuppression());
					IEditorPart parent = getSite().getPage().getActiveEditor();
					IEditorPart part = getSite().getPage().openEditor(input,
							ProcessEditor.WORKFLOW_EDITOR_ID);
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
	}

	protected PaletteContainer createControlGroup(PaletteRoot root) {
		initializeCreationFactories();

		PaletteGroup controlGroup = new PaletteGroup("Control Group"); //$NON-NLS-1$

		List entries = new ArrayList();

		ToolEntry tool = new PanningSelectionToolEntry(DiagramUIResources.AbstractDiagram_Select_text, 
				DiagramUIResources.AbstractDiagram_Select_tooltip
		);
		tool.setSmallIcon(AuthoringUIPlugin.getDefault().getImageDescriptor(
				"select_pal16.gif")); //$NON-NLS-1$
		tool.setLargeIcon(AuthoringUIPlugin.getDefault().getImageDescriptor(
				"select_pal24.gif")); //$NON-NLS-1$
		entries.add(tool);
		root.setDefaultEntry(tool);

		tool = new MarqueeToolEntry();
		tool.setSmallIcon(AuthoringUIPlugin.getDefault().getImageDescriptor(
				"marq_pal16.gif")); //$NON-NLS-1$
		tool.setLargeIcon(AuthoringUIPlugin.getDefault().getImageDescriptor(
				"marq_pal24.gif")); //$NON-NLS-1$
		entries.add(tool);

		tool = new ConnectionCreationToolEntry(DiagramUIResources.AbstractDiagram_Link_text, 
				DiagramUIResources.AbstractDiagram_Link_tooltip, 
				null, AuthoringUIPlugin.getDefault().getImageDescriptor(
						"link_pal16.gif"), //$NON-NLS-1$
				AuthoringUIPlugin.getDefault().getImageDescriptor(
						"link_pal24.gif")//$NON-NLS-1$
		);
		entries.add(tool);

		CombinedTemplateCreationEntry combined = new CombinedTemplateCreationEntry(
				DiagramUIResources.ActivityDiagram_StartNode_text, 
				DiagramUIResources.ActivityDiagram_StartNode_tooltip, 
				TemplateConstants.START_NODE, startNodeCreationFactory,
				AuthoringUIPlugin.getDefault().getImageDescriptor(
						"stnode_pal16.gif"), //$NON-NLS-1$
				AuthoringUIPlugin.getDefault().getImageDescriptor(
						"stnode_pal24.gif")//$NON-NLS-1$
		);
		entries.add(combined);

		combined = new CombinedTemplateCreationEntry(
				DiagramUIResources.AbstractDiagram_FreeText_text, 
				DiagramUIResources.AbstractDiagram_FreeText_tooltip, 
				TemplateConstants.FREE_TEXT, freeTxtNodeCreationFactory,
				AuthoringUIPlugin.getDefault().getImageDescriptor(
						"freetext_pal16.gif"), //$NON-NLS-1$
				AuthoringUIPlugin.getDefault().getImageDescriptor(
						"freetext_pal24.gif")//$NON-NLS-1$
		);
		entries.add(combined);

		combined = new CombinedTemplateCreationEntry(
				DiagramUIResources.ActivityDiagram_SyncBar_text, 
				DiagramUIResources.ActivityDiagram_SyncBar_tooltip, 
				TemplateConstants.SYNCH_BAR, synchBarCreationFactory,
				AuthoringUIPlugin.getDefault().getImageDescriptor(
						"syncbar_pal16.gif"), //$NON-NLS-1$
				AuthoringUIPlugin.getDefault().getImageDescriptor(
						"syncbar_pal24.gif")//$NON-NLS-1$
		);
		entries.add(combined);

		combined = new CombinedTemplateCreationEntry(
				DiagramUIResources.ActivityDiagram_DecisionNode_text, 
				DiagramUIResources.ActivityDiagram_DecisionNode_tooltip, 
				TemplateConstants.DECISION_NODE, decisionNodeCreationFactory,
				AuthoringUIPlugin.getDefault().getImageDescriptor(
						"decnode_pal16.gif"), //$NON-NLS-1$
				AuthoringUIPlugin.getDefault().getImageDescriptor(
						"decnode_pal24.gif")//$NON-NLS-1$
		);
		entries.add(combined);

		combined = new CombinedTemplateCreationEntry(
				DiagramUIResources.ActivityDiagram_EndNode_text, 
				DiagramUIResources.ActivityDiagram_EndNode_tooltip, 
				TemplateConstants.END_NODE, endNodeCreationFactory,
				AuthoringUIPlugin.getDefault().getImageDescriptor(
						"endnode_pal16.gif"), //$NON-NLS-1$
				AuthoringUIPlugin.getDefault().getImageDescriptor(
						"endnode_pal24.gif")//$NON-NLS-1$
		);
		entries.add(combined);

		PaletteSeparator sep = new PaletteSeparator(
				GraphicalWorkflowEditor.class.getName() + "sep1"); //$NON-NLS-1$
		sep
				.setUserModificationPermission(PaletteEntry.PERMISSION_NO_MODIFICATION);
		entries.add(sep); 

		combined = new CombinedTemplateCreationEntry(
				DiagramUIResources.ActivityDiagram_Activity_text, 
				DiagramUIResources.ActivityDiagram_Activity_tooltip, 
				TemplateConstants.ACTIVITY, activityNodeCreationFactory,
				AuthoringUIPlugin.getDefault().getImageDescriptor(
						"activity16.gif"), //$NON-NLS-1$
				AuthoringUIPlugin.getDefault().getImageDescriptor(
						"activity24.gif")//$NON-NLS-1$
		);
		entries.add(combined);

		combined = new CombinedTemplateCreationEntry(
				DiagramUIResources.ActivityDiagram_Iteration_text, 
				DiagramUIResources.ActivityDiagram_Iteration_tooltip, 
				TemplateConstants.ITERATION, iterationNodeCreationFactory,
				AuthoringUIPlugin.getDefault().getImageDescriptor(
						"Iteration16.gif"), //$NON-NLS-1$
				AuthoringUIPlugin.getDefault().getImageDescriptor(
						"activity24.gif")//$NON-NLS-1$
		);
		entries.add(combined);

		combined = new CombinedTemplateCreationEntry(DiagramUIResources.ActivityDiagram_Phase_text, 
				DiagramUIResources.ActivityDiagram_Phase_tooltip, 
				TemplateConstants.PHASE, phaseNodeCreationFactory,
				AuthoringUIPlugin.getDefault()
						.getImageDescriptor("Phase16.gif"), //$NON-NLS-1$
				AuthoringUIPlugin.getDefault().getImageDescriptor(
						"activity24.gif")//$NON-NLS-1$
		);
		entries.add(combined);

		combined = new CombinedTemplateCreationEntry(
				DiagramUIResources.AbstractDiagram_TaskDescriptor_text, 
				DiagramUIResources.AbstractDiagram_TaskDescriptor_tooltip, 
				TemplateConstants.TASK_DESCRIPTOR,
				taskDescriptorNodeCreationFactory, AuthoringUIPlugin
						.getDefault().getImageDescriptor("TaskDescriptor.gif"), //$NON-NLS-1$
				AuthoringUIPlugin.getDefault().getImageDescriptor(
						"activity24.gif")//$NON-NLS-1$
		);
		entries.add(combined);

		controlGroup.addAll(entries);
		return controlGroup;
	}

	protected String getPartNamePrefix() {
		MethodElement e = ((MethodElementEditorInput) getEditorInput())
				.getMethodElement();
		if (e instanceof Activity) {
			return getTypeName((Activity) e) + ": "; //$NON-NLS-1$
		}
		return ""; //$NON-NLS-1$
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.epf.authoring.ui.editors.AbstractDiagramEditor#createDiagramEditPart()
	 */
	protected EditPart createDiagramEditPart() {
		ActivityDiagramEditPart adEditPart = new ActivityDiagramEditPart(ModelFactory.eINSTANCE
				.createActivityDiagram());
		adEditPart.markDirty(true);
		return adEditPart;
	}

//	protected EditPart createEditPart(Object e) {
//		ActivityDiagram diagram = ModelFactory.eINSTANCE
//				.createActivityDiagram();
//		diagram.setObject(e);
//		EditPart part = new ActivityDiagramEditPart(diagram);
//		part.setModel(diagram);
//		diagram.addConsumer(this);
//		return part;
//	}

	private static String getTypeName(Activity act) {
		if (act instanceof DeliveryProcess) {
			return DiagramUIResources.ActvitivityDiagram_DeliveryProcess_text; 
		} else if (act instanceof CapabilityPattern) {
			return DiagramUIResources.ActvitivityDiagram_CapabilityPattern_text; 
		} else if (act instanceof Phase) {
			return DiagramUIResources.ActvitivityDiagram_Phase_tex; 
		} else if (act instanceof Iteration) {
			return DiagramUIResources.ActvitivityDiagram_Iteration_text; 
		}
		return DiagramUIResources.ActvitivityDiagram_Activity_text; 
	}

//	private static Image getTypeImage(Object object) {
//		IItemLabelProvider adapter = (IItemLabelProvider) TngAdapterFactory.INSTANCE
//				.getWBS_ComposedAdapterFactory().adapt(object,
//						IItemLabelProvider.class);
//		if (adapter != null) {
//			// return new Image(Display.getCurrent(),
//			// ((URL)adapter.getImage(object)).getContent());
//		}
//		return null;
//	}

	/**
	 * An editor input for grahical workflow editor
	 */
	public static class EditorInput extends BreakdownElementEditorInput {

		/**
		 * @param e
		 */
		public EditorInput(Object e, Suppression suppression) {
			super(e, suppression);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.authoring.ui.editors.AbstractDiagramEditor#createEditPartFactory()
	 */
	protected EditPartFactory createEditPartFactory() {
		return new ActivityDiagramEditPartFactory();
	}

	protected String getDiagramType() {
		return ResourceHelper.DIAGRAM_TYPE_WORKFLOW;
	}

}
