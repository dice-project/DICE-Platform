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
package org.eclipse.epf.diagram.ui.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.diagram.ad.edit.parts.ActivityFinalNodeEditPart;
import org.eclipse.epf.diagram.ad.edit.parts.ActivityFinalNodeNameEditPart;
import org.eclipse.epf.diagram.ad.edit.parts.ActivityParameterNode2EditPart;
import org.eclipse.epf.diagram.ad.edit.parts.ActivityParameterNodeEditPart;
import org.eclipse.epf.diagram.ad.edit.parts.ActivityParameterNodeName2EditPart;
import org.eclipse.epf.diagram.ad.edit.parts.ActivityParameterNodeNameEditPart;
import org.eclipse.epf.diagram.ad.edit.parts.ControlFlowEditPart;
import org.eclipse.epf.diagram.ad.edit.parts.ControlFlowNameEditPart;
import org.eclipse.epf.diagram.ad.edit.parts.DecisionNodeEditPart;
import org.eclipse.epf.diagram.ad.edit.parts.DecisionNodeNameEditPart;
import org.eclipse.epf.diagram.ad.edit.parts.ForkNodeEditPart;
import org.eclipse.epf.diagram.ad.edit.parts.InitialNodeEditPart;
import org.eclipse.epf.diagram.ad.edit.parts.InitialNodeNameEditPart;
import org.eclipse.epf.diagram.ad.edit.parts.JoinNodeEditPart;
import org.eclipse.epf.diagram.ad.edit.parts.MergeNodeEditPart;
import org.eclipse.epf.diagram.ad.edit.parts.MergeNodeNameEditPart;
import org.eclipse.epf.diagram.ad.edit.parts.StructuredActivityNode2EditPart;
import org.eclipse.epf.diagram.ad.edit.parts.StructuredActivityNode3EditPart;
import org.eclipse.epf.diagram.ad.edit.parts.StructuredActivityNodeEditPart;
import org.eclipse.epf.diagram.ad.edit.parts.StructuredActivityNodeName2EditPart;
import org.eclipse.epf.diagram.ad.edit.parts.StructuredActivityNodeName3EditPart;
import org.eclipse.epf.diagram.ad.edit.parts.StructuredActivityNodeNameEditPart;
import org.eclipse.epf.diagram.ad.part.ActivityDiagramEditorPlugin;
import org.eclipse.epf.diagram.core.bridge.BridgeHelper;
import org.eclipse.epf.diagram.core.services.DiagramManager;
import org.eclipse.epf.diagram.ui.DiagramUIPlugin;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Iteration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Milestone;
import org.eclipse.epf.uma.Phase;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.WorkBreakdownElement;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.uml2.uml.ActivityFinalNode;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ActivityParameterNode;
import org.eclipse.uml2.uml.ControlFlow;
import org.eclipse.uml2.uml.DecisionNode;
import org.eclipse.uml2.uml.FinalNode;
import org.eclipse.uml2.uml.ForkNode;
import org.eclipse.uml2.uml.InitialNode;
import org.eclipse.uml2.uml.JoinNode;
import org.eclipse.uml2.uml.MergeNode;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * A UI service for external ecore map to acess EPF Diagrams and creating
 * diagram elements.
 * 
 * @author Chinh Vo
 * @author Shashidhar Kannoori
 * @author Shilpa Toraskar
 * @since 1.2
 */
public class DiagramUIService implements IDiagramUIService {

	static boolean debug = DiagramUIPlugin.getDefault().isDebugging();

	public static HashMap<String, Class> nodeTypesMap = new HashMap<String, Class>();

	public DiagramUIService() {

		nodeTypesMap.put(IDiagramUIService.ACTIVITY_NODE, Activity.class);
		nodeTypesMap.put(IDiagramUIService.PHASE_NODE, Phase.class);
		nodeTypesMap.put(IDiagramUIService.ITERATION_NODE, Iteration.class);
		nodeTypesMap.put(IDiagramUIService.TASK_DESCRIPTOR_NODE,
				TaskDescriptor.class);
		nodeTypesMap.put(IDiagramUIService.MILESTONE_NODE, Milestone.class);
		nodeTypesMap.put(IDiagramUIService.INITIAL_NODE, InitialNode.class);
		nodeTypesMap.put(IDiagramUIService.FINAL_NODE, FinalNode.class);
		nodeTypesMap.put(IDiagramUIService.FORK_NODE, ForkNode.class);
		nodeTypesMap.put(IDiagramUIService.JOIN_NODE, JoinNode.class);
		nodeTypesMap.put(IDiagramUIService.MERGE_NODE, MergeNode.class);
		nodeTypesMap.put(IDiagramUIService.DECISION_NODE, DecisionNode.class);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.diagram.ui.service.IDiagramUIService#createActivityDiagram(org.eclipse.epf.uma.Activity)
	 */
	public Diagram createActivityDiagram(Activity activity) {	
		EObject umlActivity = UMLFactory.eINSTANCE.create(UMLPackage.eINSTANCE
				.getActivity());
		if (umlActivity instanceof org.eclipse.uml2.uml.Activity) {
			((org.eclipse.uml2.uml.Activity)umlActivity).setName(activity.getName());
		}
		if (umlActivity instanceof EModelElement) {
			BridgeHelper.associate((EModelElement) umlActivity, activity);
		}
		
		// Create diagram
		Diagram diagram = ViewService.createDiagram(umlActivity, DiagramManager.AD_kind,
				ActivityDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
		diagram.setElement(umlActivity);
		if (umlActivity instanceof org.eclipse.uml2.uml.Activity) {
			diagram.setName(activity.getName());
		}
		return diagram;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.diagram.ui.service.IDiagramUIService#createEdge(org.eclipse.gmf.runtime.notation.Diagram,
	 *      org.eclipse.gmf.runtime.notation.View,
	 *      org.eclipse.gmf.runtime.notation.View)
	 */
	public Edge createEdge(Diagram diagram, View source, View target,
			String linkName) {
		org.eclipse.uml2.uml.Activity parentActivity = null;
		EObject obj = diagram.getElement();
		if (obj instanceof org.eclipse.uml2.uml.Activity) {
			parentActivity = (org.eclipse.uml2.uml.Activity) obj;
		}
		if (parentActivity != null) {
			ControlFlow flow = (org.eclipse.uml2.uml.ControlFlow) parentActivity
					.createEdge(linkName = (linkName != null) ? linkName : "",	//$NON-NLS-1$
							UMLPackage.eINSTANCE.getControlFlow());
			if (source != null) {
				if (source.getElement() != null && source.getElement() instanceof ActivityNode)
					flow.setSource((ActivityNode) source.getElement());
			}
			if (target != null) {
				if (target.getElement() != null && target.getElement() instanceof ActivityNode)
					flow.setTarget((ActivityNode) target.getElement());
			}
			if (flow instanceof ControlFlow) {
				Edge edge = ViewService.createEdge(source, target, flow,
						new Integer(ControlFlowEditPart.VISUAL_ID).toString(),
						ActivityDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
				ViewService.createNode(edge, flow, new Integer(
						ControlFlowNameEditPart.VISUAL_ID).toString(),
						ActivityDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
				
				if (source != null) {
					edge.setSource(source);				
				}
				if (target != null) {
					edge.setTarget(target);					
				}
				return edge;
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.diagram.ui.service.IDiagramUIService#createNode(org.eclipse.gmf.runtime.notation.Diagram,
	 *      java.lang.String, java.lang.String)
	 */
	public Node createNode(Diagram diagram, String nodeType, String nodeName) {
		org.eclipse.uml2.uml.Activity parentActivity = null;
		Node notationNode = null;
		EObject obj = diagram.getElement();
		if (obj instanceof org.eclipse.uml2.uml.Activity) {
			parentActivity = (org.eclipse.uml2.uml.Activity) obj;
		}

		if (parentActivity != null) {
			if (nodeType == INITIAL_NODE) {
				ActivityNode umlNode = (InitialNode) parentActivity.createOwnedNode(
						nodeName = (nodeName != null) ? nodeName : "",	//$NON-NLS-1$
						UMLPackage.eINSTANCE.getInitialNode());
				notationNode = ViewService.createNode(diagram, umlNode,
						new Integer(InitialNodeEditPart.VISUAL_ID).toString(),
						ActivityDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
				ViewService.createNode(notationNode, umlNode, new Integer(
						InitialNodeNameEditPart.VISUAL_ID).toString(),
						ActivityDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
			} else if (nodeType == FINAL_NODE) {
				ActivityNode umlNode = (ActivityFinalNode) parentActivity
						.createOwnedNode(nodeName = (nodeName != null) ? nodeName
								: "", UMLPackage.eINSTANCE.getActivityFinalNode());	//$NON-NLS-1$
				notationNode = ViewService.createNode(diagram, umlNode,
						new Integer(ActivityFinalNodeEditPart.VISUAL_ID)
								.toString(),
						ActivityDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
				ViewService.createNode(notationNode, umlNode, new Integer(
						ActivityFinalNodeNameEditPart.VISUAL_ID).toString(),
						ActivityDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
			} else if (nodeType == FORK_NODE) {
				ActivityNode umlNode = (ForkNode) parentActivity.createOwnedNode(
						nodeName = (nodeName != null) ? nodeName : "",	//$NON-NLS-1$
						UMLPackage.eINSTANCE.getForkNode());
				notationNode = ViewService.createNode(diagram, umlNode,
						new Integer(ForkNodeEditPart.VISUAL_ID).toString(),
						ActivityDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
			} else if (nodeType == JOIN_NODE) {
				ActivityNode umlNode = (JoinNode) parentActivity.createOwnedNode(
						nodeName = (nodeName != null) ? nodeName : "",	//$NON-NLS-1$
						UMLPackage.eINSTANCE.getJoinNode());
				notationNode = ViewService.createNode(diagram, umlNode,
						new Integer(JoinNodeEditPart.VISUAL_ID).toString(),
						ActivityDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
			} else if (nodeType == DECISION_NODE) {
				ActivityNode umlNode = (DecisionNode) parentActivity
						.createOwnedNode(nodeName = (nodeName != null) ? nodeName
								: "", UMLPackage.eINSTANCE.getDecisionNode());	//$NON-NLS-1$
				notationNode = ViewService.createNode(diagram, umlNode,
						new Integer(DecisionNodeEditPart.VISUAL_ID).toString(),
						ActivityDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
				ViewService.createNode(notationNode, umlNode, new Integer(
						DecisionNodeNameEditPart.VISUAL_ID).toString(),
						ActivityDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
			} else if (nodeType == MERGE_NODE) {
				ActivityNode umlNode = (MergeNode) parentActivity.createOwnedNode(
						nodeName = (nodeName != null) ? nodeName : "",			//$NON-NLS-1$
						UMLPackage.eINSTANCE.getMergeNode());
				notationNode = ViewService.createNode(diagram, umlNode,
						new Integer(MergeNodeEditPart.VISUAL_ID).toString(),
						ActivityDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
				ViewService.createNode(notationNode, umlNode, new Integer(
						MergeNodeNameEditPart.VISUAL_ID).toString(),
						ActivityDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
			}
		}

		return notationNode;
	}

	/*	
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.diagram.ui.service.IDiagramUIService#createNode(org.eclipse.gmf.runtime.notation.Diagram,
	 *      org.eclipse.epf.uma.WorkBreakdownElement)
	 */
	public Node createNode(Diagram diagram, WorkBreakdownElement e) {
		org.eclipse.uml2.uml.Activity parentActivity = null;
		Node notationNode = null;
		EObject obj = diagram.getElement();
		if (obj instanceof org.eclipse.uml2.uml.Activity) {
			parentActivity = (org.eclipse.uml2.uml.Activity) obj;
		}
		String name = "";	//$NON-NLS-1$
		if(e.getPresentationName() != null){
			name = e.getPresentationName();
		}else{
			name = e.getName();
		}
		
		if (parentActivity != null) {
			if (e instanceof Phase) {
				// Phase
				ActivityNode umlNode = (StructuredActivityNode) parentActivity
						.createOwnedNode(name,
								UMLPackage.eINSTANCE
										.getStructuredActivityNode());

				BridgeHelper.associate(umlNode, ((MethodElement) e));

				notationNode = ViewService.createNode(diagram, umlNode,
						new Integer(StructuredActivityNode2EditPart.VISUAL_ID)
								.toString(),
						ActivityDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
				ViewService.createNode(notationNode, umlNode, new Integer(
						StructuredActivityNodeName2EditPart.VISUAL_ID)
						.toString(),
						ActivityDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);

			} else if (e instanceof Iteration) {
				// Iteration
				ActivityNode umlNode = (StructuredActivityNode) parentActivity
						.createOwnedNode(name,
								UMLPackage.eINSTANCE
										.getStructuredActivityNode());

				BridgeHelper.associate(umlNode, ((MethodElement) e));

				notationNode = ViewService.createNode(diagram, umlNode,
						new Integer(StructuredActivityNode3EditPart.VISUAL_ID)
								.toString(),
						ActivityDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
				ViewService.createNode(notationNode, umlNode, new Integer(
						StructuredActivityNodeName3EditPart.VISUAL_ID)
						.toString(),
						ActivityDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
			} else if (e instanceof Activity) {
				// Activity
				ActivityNode umlNode = (StructuredActivityNode) parentActivity
						.createOwnedNode(name,
								UMLPackage.eINSTANCE
										.getStructuredActivityNode());

				BridgeHelper.associate(umlNode, ((MethodElement) e));

				notationNode = ViewService.createNode(diagram, umlNode,
						new Integer(StructuredActivityNodeEditPart.VISUAL_ID)
								.toString(),
						ActivityDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
				ViewService.createNode(notationNode, umlNode, new Integer(
						StructuredActivityNodeNameEditPart.VISUAL_ID)
						.toString(),
						ActivityDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
			} else if (e instanceof TaskDescriptor) {
				// Task descriptor
				ActivityNode umlNode = (ActivityParameterNode) parentActivity
						.createOwnedNode(name,
								UMLPackage.eINSTANCE.getActivityParameterNode());

				BridgeHelper.associate(umlNode, ((MethodElement) e));

				notationNode = ViewService.createNode(diagram, umlNode,
						new Integer(ActivityParameterNodeEditPart.VISUAL_ID)
								.toString(),
						ActivityDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
				ViewService
						.createNode(
								notationNode,
								umlNode,
								new Integer(
										ActivityParameterNodeNameEditPart.VISUAL_ID)
										.toString(),
								ActivityDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
			} else if (e instanceof Milestone) {
				// Milestone
				ActivityNode umlNode = (ActivityParameterNode) parentActivity
						.createOwnedNode(name,
								UMLPackage.eINSTANCE.getActivityParameterNode());

				BridgeHelper.associate(umlNode, ((MethodElement) e));

				notationNode = ViewService.createNode(diagram, umlNode,
						new Integer(ActivityParameterNode2EditPart.VISUAL_ID)
								.toString(),
						ActivityDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
				ViewService.createNode(notationNode, umlNode, new Integer(
						ActivityParameterNodeName2EditPart.VISUAL_ID)
						.toString(),
						ActivityDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
			}
		}

		return notationNode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.diagram.ui.service.IDiagramUIService#getEdges(org.eclipse.gmf.runtime.notation.Diagram)
	 */
	public EList getEdges(Diagram diagram) {
		return diagram.getPersistedEdges();
	}

	/**
	 * Returns all diagram nodes in the diagram
	 * 
	 * @param diagram
	 * @param nodeType
	 * @return
	 */
	public EList getAllNodes(Diagram diagram) {
		if (diagram != null) {
			return diagram.getPersistedChildren();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.diagram.ui.service.IDiagramUIService#getNodes(org.eclipse.gmf.runtime.notation.Diagram,
	 *      org.eclipse.epf.uma.Activity, java.lang.String)
	 */
	public List<Node> getNodes(Diagram diagram, Activity activity,
			String nodeType) {
		List<Node> nodes = new ArrayList<Node>();
		if (diagram != null) {
			List allNodes = diagram.getPersistedChildren();

			for (Iterator iter = allNodes.iterator(); iter.hasNext();) {
				Node node = (Node) iter.next();
				EObject element = node.getElement();
				Class classType = (Class) nodeTypesMap.get(nodeType);
				if (element instanceof EModelElement) {
					MethodElement ex = BridgeHelper
							.getMethodElementFromAnnotation(
									(EModelElement) element, activity
											.eResource().getResourceSet());
					if (ex != null && classType.isInstance(ex)) {
						nodes.add(node);
					} else {
						if (classType != null && classType.isInstance(element)) {
							nodes.add(node);
						}
					}
				}
			}
		}
		return nodes;
	}

//	private static Diagram getDiagram(Activity activity, int type,
//			boolean create) {
//		Diagram diagram = null;
//		DiagramManager dMgr = DiagramManager.getInstance(TngUtil
//				.getOwningProcess(activity));
//
//		if (dMgr != null) {
//			try {
//				List list = dMgr.getDiagrams(activity, type);
//				if (!list.isEmpty()) {
//					diagram = (Diagram) list.get(0);
//				}
//				if (diagram == null && create) {
//					diagram = dMgr.createDiagram(activity, type,
//							getDiagramPreferencesHint(type));
//				}
//			} catch (Exception e) {
//				if (debug) {
//					System.out.println("Core error creating a diagram:" //$NON-NLS-1$
//							+ e.getLocalizedMessage());
//				}
//				DiagramUIPlugin.getDefault().getLogger().logError(
//						"Core error creating a diagram:", e); //$NON-NLS-1$
//			} finally {
//				if (dMgr != null) {
//					dMgr.release();
//				}
//			}
//		}
//		return diagram;
//	}

//	public static PreferencesHint getDiagramPreferencesHint(int diagramType) {
//		switch (diagramType) {
//		case IDiagramManager.ACTIVITY_DIAGRAM:
//			return ActivityDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT;
//		case IDiagramManager.ACTIVITY_DETAIL_DIAGRAM:
//			return ActivityDetailDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT;
//		case IDiagramManager.WORK_PRODUCT_DEPENDENCY_DIAGRAM:
//			return WPDDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT;
//		}
//		return null;
//	}

//	public static void openDiagram(IEditorPart part, int diagramType,
//			Object selectedObject, Suppression suppression) {
//		try {
//			DiagramEditorInput input = new org.eclipse.epf.diagram.core.part.DiagramEditorInput(
//					selectedObject, suppression, diagramType);
//			DiagramEditorUtil.openDiagramEditor(part.getSite().getPage(),
//					input, getDiagramPreferencesHint(diagramType),
//					new NullProgressMonitor());
//		} catch (RuntimeException e) {
//			DiagramUIPlugin.getDefault().getLogger().logError(e);
//		}
//	}

//	public static Diagram getDiagram(Activity activity, int type) {
//		return getDiagram(activity, type, false);
//	}
}
