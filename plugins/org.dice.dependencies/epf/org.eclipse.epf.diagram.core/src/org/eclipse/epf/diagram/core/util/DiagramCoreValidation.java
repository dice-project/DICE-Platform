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
package org.eclipse.epf.diagram.core.util;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.AdapterFactoryTreeIterator;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.epf.diagram.core.DiagramCoreResources;
import org.eclipse.epf.diagram.core.bridge.BridgeHelper;
import org.eclipse.epf.diagram.core.bridge.NodeAdapter;
import org.eclipse.epf.diagram.core.services.DiagramHelper;
import org.eclipse.epf.diagram.model.ActivityDetailDiagram;
import org.eclipse.epf.diagram.model.ActivityDiagram;
import org.eclipse.epf.diagram.model.Link;
import org.eclipse.epf.diagram.model.RoleNode;
import org.eclipse.epf.diagram.model.TaskNode;
import org.eclipse.epf.diagram.model.TypedNode;
import org.eclipse.epf.diagram.model.WorkBreakdownElementNode;
import org.eclipse.epf.diagram.model.util.GraphicalDataHelper;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.process.IBSItemProvider;
import org.eclipse.epf.library.edit.util.IDiagramManager;
import org.eclipse.epf.library.edit.util.MethodElementPropertyHelper;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.WorkBreakdownElement;
import org.eclipse.epf.uma.WorkOrder;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ControlNode;
import org.eclipse.uml2.uml.FinalNode;
import org.eclipse.uml2.uml.ForkNode;
import org.eclipse.uml2.uml.InitialNode;
import org.eclipse.uml2.uml.JoinNode;

/**
 * Validating routines for diagram editing
 * 
 * @author Phong Nguyen Le
 * @author Shashidhar Kannoori
 * @since 1.0
 */
public class DiagramCoreValidation {

	private static final String errMsg_CanNotConnect = DiagramCoreResources.DiagramValidation_err_cannot_connect_text; //$NON-NLS-1$

	private static final String errMsg_SamePredAndSucc = DiagramCoreResources.DiagramValidation_err_samepredandsuc_text; //$NON-NLS-1$

	private static final String errMsg_CanNotDelete = DiagramCoreResources.DiagramValidation_err_cannot_delete_text; //$NON-NLS-1$

	public static boolean isConnectionToReadOnlyTargetAllowed() {
//		return DiagramPreferences.getADAllowConnectionToReadOnlyElements();
		return true;
	}
	
	public static String isTargetReadonly(EditPart part){
		View view = (View)part.getModel();
		if(BridgeHelper.isReadOnly(view)) return errMsg_CanNotConnect;
		return null;
	}
	
	public static String canConnect(EditPart targetEditPart, EObject sourceElement, EObject targetElement){
		
		if(targetEditPart == null) return errMsg_CanNotConnect;
		
		EditPart part = targetEditPart.getParent();
		Diagram diagram = null;
		while(part != null && !(part instanceof DiagramEditPart)){
				part  = part.getParent();
		}
		if(part instanceof DiagramEditPart){
			diagram = (Diagram)((DiagramEditPart)part).getModel();
		}

		if(diagram == null) return errMsg_CanNotConnect;
		return checkConnect(diagram,  sourceElement, targetElement);
	}
	
	public static String checkConnect(Diagram diagram, EObject sourceElement, EObject targetElement){
		switch(DiagramHelper.getDiagramType(diagram)){
		case IDiagramManager.ACTIVITY_DIAGRAM:
			if(alwaysAllowed(sourceElement, targetElement)) {
				return null;
			}
			
			// If target Diagram is read-only - do not connect
			if(BridgeHelper.isReadOnly(diagram)) return errMsg_CanNotConnect;
				
			NodeAdapter sourceAdapter = BridgeHelper.getNodeAdapter(sourceElement);
			NodeAdapter targetAdapter = BridgeHelper.getNodeAdapter(targetElement);
			
			// if adapters of source and target is null - no connect.
			if(sourceAdapter == null || targetAdapter == null) return errMsg_CanNotConnect;
			
			Diagram sourceDiagram = sourceAdapter.getView().getDiagram();
			
			// If source and target diagrams not equal - do not connect.
			if (!diagram.equals(sourceDiagram))
								return errMsg_CanNotConnect;

			AdapterFactory adapterFactory = TngAdapterFactory.INSTANCE
					.getWBS_ComposedAdapterFactory();
			Object adapter = adapterFactory.adapt(BridgeHelper.getMethodElement(sourceDiagram)
					, ITreeItemContentProvider.class);
			Object proc = null;
			if (adapter instanceof IBSItemProvider) {
				proc = ((IBSItemProvider) adapter).getTopItem();
			}
			// get all breakdown elements in this process
			//
			List<Object> allElements = new ArrayList<Object>();
			for (Iterator iter = new AdapterFactoryTreeIterator(adapterFactory,
					proc); iter.hasNext();) {
				Object obj = iter.next();
				allElements.add(TngUtil.unwrap(obj));
			}
			if(proc != null){
				EObject source = sourceAdapter.getElement();

				EObject target = null;
				if (targetAdapter != null) {
					target = targetAdapter.getElement();
				}
				if (source instanceof 
						WorkBreakdownElement) {
					if (target instanceof WorkBreakdownElement) {
						if (ProcessUtil.checkCircular(
								(WorkBreakdownElement) target,
								(WorkBreakdownElement) source, allElements))
							return errMsg_SamePredAndSucc;

						if (!isConnectionToReadOnlyTargetAllowed() && targetAdapter.isTargetReadOnly())
							return errMsg_CanNotConnect;
					} else {
						// target node might be control node
						Collection<ActivityNode> actNodes = new ArrayList<ActivityNode>();
						BridgeHelper.getSuccessorNodes(actNodes,
								(ActivityNode) targetElement);
						for (ActivityNode node : actNodes) {
							if (BridgeHelper.getNodeAdapter(node).isTargetReadOnly() && !isConnectionToReadOnlyTargetAllowed())
								return errMsg_CanNotConnect;
							if (ProcessUtil.checkCircular(
									(WorkBreakdownElement) BridgeHelper
											.getMethodElement(node),
									(WorkBreakdownElement) source, allElements)) {
								return errMsg_SamePredAndSucc;
							}
						}
					}
				} else {
					if (target instanceof WorkBreakdownElement) {
						Collection<ActivityNode> srcNodes = new ArrayList<ActivityNode>();
						BridgeHelper.getPredecessorNodes(srcNodes,
								(ActivityNode) sourceElement);
						if (!srcNodes.isEmpty()) {
							if (targetAdapter.isTargetReadOnly() && !isConnectionToReadOnlyTargetAllowed())
								return errMsg_CanNotConnect;
							for (ActivityNode predNode : srcNodes) {
								MethodElement pred = BridgeHelper
										.getMethodElement(predNode);
								if (ProcessUtil.checkCircular(
										(WorkBreakdownElement) target,
										(WorkBreakdownElement) pred,
										allElements)) {
									return errMsg_SamePredAndSucc;
								}
							}
						}
					} else {
						if (checkSyncBarCircularLooop((ActivityNode)sourceElement,
								(ActivityNode) targetElement) != null) {
							return errMsg_CanNotConnect;
						}

						// should not allow incoming connection to start node
						// and FreeText.
						if (targetElement instanceof InitialNode) {
							return errMsg_CanNotConnect;
						}

						Collection<ActivityNode> srcNodes = new ArrayList<ActivityNode>(); 
						BridgeHelper.getPredecessorNodes(srcNodes, (ActivityNode) sourceElement);
						if (!srcNodes.isEmpty()) {
							Collection<ActivityNode> targetNodes = new ArrayList<ActivityNode>();
							BridgeHelper.getSuccessorNodes(targetNodes,
									(ActivityNode) target);
							for (ActivityNode node : targetNodes) {
								NodeAdapter nodeAdapter = BridgeHelper
										.getNodeAdapter(node);
								if (nodeAdapter.isTargetReadOnly() && !isConnectionToReadOnlyTargetAllowed())
									return errMsg_CanNotConnect;
								WorkBreakdownElement succ = (WorkBreakdownElement) nodeAdapter
										.getElement();
								for (ActivityNode prednode : srcNodes) {
									NodeAdapter predAdapter = BridgeHelper
											.getNodeAdapter(prednode);
									WorkBreakdownElement pred = (WorkBreakdownElement) predAdapter
											.getElement();
									if (ProcessUtil.checkCircular(succ, pred,
											allElements)) {
										return errMsg_SamePredAndSucc;
									}
								}
							}
						}
					}
				}
				
			}
		}
			
		return null;
	}
	
	/*
	 * To avoid circular looping between synchronization bars. If SycnBar1 ->
	 * SyncBar2 (connected) then SyncBar2 -> SyncBar1 cannot connect. if
	 * syncbar1 -> syncbar2->syncbar3 then syncbar3 -> syncbar1 disallowed.
	 * 
	 */
	public static String checkSyncBarCircularLooop(ActivityNode sNode,
			ActivityNode tNode) {
		List list = tNode.getOutgoings();
		if (!list.isEmpty() && list.size() > 0) {
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				ActivityEdge link = (ActivityEdge) iterator.next();
				ActivityNode typednode = link.getTarget();
				if (sNode.equals(typednode))
					return errMsg_CanNotConnect;
				if (typednode instanceof ControlNode) {
					return checkSyncBarCircularLooop(sNode,
							(ControlNode)typednode);
				}
			}
		}
		return null;
	}
	
	/*
	 * Method to Check if SyncBar inComming connections have 
	 * any source is a readonly. 
	 * @return
	 */
	public static String checkSyncBarIncomingLinks(ActivityNode typedNode){
		for (Iterator iter = typedNode.getIncomings().iterator(); iter.hasNext();) {
			ActivityEdge link = (ActivityEdge) iter.next();
			ActivityNode source = link.getSource();
			NodeAdapter adapter = BridgeHelper.getNodeAdapter(source);
			if (adapter != null) {
				if(adapter.getElement() instanceof WorkBreakdownElement){
					if(adapter.isTargetReadOnly())
						return errMsg_CanNotDelete;
				}else if(source instanceof ControlNode){
					if(source instanceof ForkNode || source instanceof JoinNode)
						if(checkSyncBarIncomingLinks(source)!= null)
							return errMsg_CanNotDelete;
				}
			}
		}
		return null;
	}
	/*
	 *Method to check if synchronization bar outgoing connection
	 *has any target is readonly.  
	 */
	public static String checkSyncBarOutgoingLinks(ActivityNode typedNode){
		for (Iterator iter = typedNode.getOutgoings().iterator(); iter.hasNext();) {
			ActivityEdge link = (ActivityEdge) iter.next();
			ActivityNode target = link.getTarget();
			NodeAdapter adapter = BridgeHelper.getNodeAdapter(target);
			if (adapter != null) {
				if(adapter.getElement() instanceof WorkBreakdownElement){
					if(adapter.isTargetReadOnly())
						return errMsg_CanNotDelete;
				}else if(target instanceof ControlNode){
					if(target instanceof ForkNode || target instanceof JoinNode)
						if(checkSyncBarOutgoingLinks(target)!= null)
							return errMsg_CanNotDelete;
				}
			}
		}
		return null;
	}
	
	
	public static String checkDelete(Node node) {
		if (BridgeHelper.isReadOnly(node))
			return errMsg_CanNotDelete;
		Diagram diagram = node.getDiagram();
		if (diagram instanceof ActivityDiagram) {
			if (node.getElement() instanceof ControlNode) {
				
				Collection<ActivityNode> targetNodes = new ArrayList<ActivityNode>(); 
				BridgeHelper.getSuccessorNodes(targetNodes, (ActivityNode)node.getElement());
						
				for (ActivityNode targetNode : targetNodes) {
					NodeAdapter adapter = BridgeHelper.getNodeAdapter(targetNode);
					if(adapter != null && adapter.isTargetReadOnly()){
						return errMsg_CanNotDelete;
					}
				}
			}
		} else if (diagram instanceof ActivityDetailDiagram) {
			if (node.getElement() instanceof RoleNode) {
				return errMsg_CanNotDelete;
			}
		}
		return null;
	}
	
	public static String checkReconnect(Node source, Node target, Edge link) {
		if (source.getDiagram() instanceof ActivityDiagram) {
			if (link.getTarget() != null) {
				if (BridgeHelper.isReadOnly(link.getTarget())) {
					return errMsg_CanNotConnect;
				} else {
					if (link.getTarget() instanceof TypedNode) {
						Collection targetNodes = GraphicalDataHelper
								.getTargetNodes((TypedNode) link.getTarget(),
										WorkBreakdownElementNode.class);
						for (Iterator iter = targetNodes.iterator(); iter
								.hasNext();) {
							Node node = (Node) iter.next();
							if (BridgeHelper.isReadOnly(node)) {
								return errMsg_CanNotConnect;
							}
						}
					}
				}
			}
		}

		return checkConnect(source, target, link);
	}
	
	/*
	 * Method will not allow multiple link between two nodes. Needs to call
	 * CreakLinkCommand and ReconnectLinkCommand. Any action on a Link should
	 * call this checkConnect(Node source, Node target, Link link) before allow
	 * to connect.
	 */
	public static String checkConnect(Node source, Node target, Edge link) {

		if (source == target && !(BridgeHelper.getMethodElement(source) instanceof Activity)) {
			return errMsg_CanNotConnect;
		}

		List links = source.getTargetEdges();
		if (links != null) {
			// This is need for reconnect to same target node.
			if (links.contains(link)) {
				if (link.getTarget().equals(target)) {
					return null;
				}
			}
		// This is need for new connect and reconnect to different target node.
		for (Iterator iter = links.iterator(); iter.hasNext();) {
			Link linkx = (Link) iter.next();
			Object linkxtarget = linkx.getTarget();
			if (linkxtarget != null && linkxtarget.equals(target)) {
				return errMsg_CanNotConnect;
				}
			}
		}
		return checkConnect(source.getDiagram(), source.getElement(), target.getElement());
	}
	
	private static boolean alwaysAllowed(Object sourceElement, Object targetElement) {
		return sourceElement instanceof InitialNode 
		|| targetElement instanceof FinalNode
		|| (sourceElement instanceof ControlNode && !BridgeHelper.isSynchBar((ControlNode)sourceElement))
		|| (targetElement instanceof ControlNode && !BridgeHelper.isSynchBar((ControlNode)targetElement));
	}
	
	/*
	 * Checks if the given edge is a regular edge, means, it is not a special
	 * edge that is created in extended diagram with a inherited, read-only
	 * target.
	 */
	private static boolean isRegularEdge(Edge edge) {
		MethodElement workOrder = BridgeHelper.getMethodElement(edge);
		return workOrder == null || MethodElementPropertyHelper.getProperty(workOrder, MethodElementPropertyHelper.WORK_ORDER__SUCCESSOR) == null;
	}
	
	public static String checkDelete(Edge edge) {
		View source = edge.getSource();
		View target = edge.getTarget();

		if(target != null){
			Diagram diagram = target.getDiagram();
			switch(DiagramHelper.getDiagramType(diagram)){
			case IDiagramManager.ACTIVITY_DIAGRAM:				
				EObject sourceElement = source.getElement();
				EObject targetElement = target.getElement();
				if(alwaysAllowed(sourceElement, targetElement)) {
					return null;
				}
				if(BridgeHelper.isInherited(edge)) {
					return errMsg_CanNotDelete;
				} else if(BridgeHelper.isReadOnly(source) && BridgeHelper.isReadOnly(target)) {
					// check if this edge is a custom one
					//
					MethodElement me = BridgeHelper.getMethodElement(edge);
					if(me instanceof WorkOrder && ProcessUtil.isCustomWorkOrder((WorkOrder) me)) {
						return null;
					} else {
						// edge is automatically created for 2 inherited
						// predecessor/successor and cannot be deleted
						//
						return errMsg_CanNotDelete;
					}
				}
				
				// Since making connection to a inherited, read-only target is
				// allowed now, the check logic below is no longer needed. We
				// only need to prevented inherited connections and nodes from
				// deletion.
				
//				// check if this edge is representing any work order
//				//
//				if (sourceElement instanceof ActivityNode
//						&& BridgeHelper.getMethodElement(sourceElement) == null) {
//					Collection<ActivityNode> actNodes = new ArrayList<ActivityNode>();
//					BridgeHelper.getSourceNodes(actNodes,
//							(ActivityNode) sourceElement,
//							WorkBreakdownElement.class);
//					if (actNodes.isEmpty()) {
//						return null;
//					}
//				}
//				
//				// the edge does represent a work order
//				
//				if(BridgeHelper.isReadOnly(target)) {
//					if(BridgeHelper.getMethodElement(sourceElement) instanceof WorkBreakdownElement)
//					return errMsg_CanNotDelete;
//				}
//				
//				if(targetElement instanceof ActivityNode) {
//					NodeAdapter adapter = BridgeHelper.getNodeAdapter(targetElement);
//					if(adapter != null && adapter.getElement() instanceof WorkBreakdownElement) {
//						return null;
//					}
////					// target does not represent a work breakdown element
////					// disallow deletion of the edge if one of the target nodes of the target is read-only or inherited
////					//
////					Collection<ActivityNode> actNodes = new ArrayList<ActivityNode>();
////					BridgeHelper.getSuccessorNodes(actNodes, (ActivityNode) targetElement);
////					for (ActivityNode activityNode : actNodes) {
////						View view = BridgeHelper.getView(diagram, activityNode);
////						if(view != null && BridgeHelper.isReadOnly(view)) {
////							return errMsg_CanNotDelete;
////						}
////					}
//					
//					if(BridgeHelper.isSynchBar((ActivityNode)targetElement)) {
//						return checkSyncBarOutgoingLinks((ActivityNode)targetElement);
//					}
//
//				}
				
				return null;
			case IDiagramManager.ACTIVITY_DETAIL_DIAGRAM:
				return errMsg_CanNotDelete;
				
			case IDiagramManager.WORK_PRODUCT_DEPENDENCY_DIAGRAM:
				if(BridgeHelper.isReadOnly(target))
					return errMsg_CanNotDelete;
				return null;
			}
		}
		return null;
	}
	
	/**
	 * Check to see link is readonly.
	 */
	public static boolean isReadOnly(Edge edge) {
		
		View target = edge.getTarget();
		View source = edge.getSource();
		if (target != null
				&& target.getDiagram().getElement() instanceof ActivityDetailDiagram) {
			if(source != null){
				Object sourceElement = source.getElement();
				if(sourceElement != null && sourceElement instanceof TaskNode
						&& ((TaskNode)sourceElement).isReadOnly()){
					return true;
				}
			}
			if(target != null){
				Object targetElement = target.getElement();
				if(targetElement != null && targetElement instanceof TaskNode
						&& ((TaskNode)targetElement).isReadOnly()){
					return true;
				}
			}
		} else {
			return checkDelete(edge) != null;
		}
		return false;
	}
	
	/**
	 * Check if any duplicate links between two ends already exists.
	 * 	 
	 * */
	public static boolean isDuplicateRelationship(EObject source, EObject target) {
		EObject container = source.eContainer();
		if (container instanceof org.eclipse.uml2.uml.Activity) {
			org.eclipse.uml2.uml.Activity activity = (org.eclipse.uml2.uml.Activity) container;
			List<ActivityEdge> list = activity.getEdges();
			for (Iterator<ActivityEdge> iter = list.iterator(); iter.hasNext();) {
				ActivityEdge element = (ActivityEdge) iter.next();
				if ((element.getSource() == source)
						&& element.getTarget() == target) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean hasVisibleTarget(ActivityNode source) {
		List<ActivityEdge> list = source.getOutgoings();
		if (list != null && list.size() >= 1) {
			// ignore outgoing connections from invisible nodes
			//
			for (ActivityEdge edge : list) {
				ActivityNode node = edge.getTarget();
				NodeAdapter nodeAdapter = BridgeHelper.getNodeAdapter(node);
				if(nodeAdapter != null) {
					View view = nodeAdapter.getView();
					if(view != null && view.isVisible()) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public static boolean hasVisibleSource(ActivityNode target) {
		List<ActivityEdge> list = target.getIncomings();
		if (list != null && list.size() >= 1) {
			// ignore incoming connection from invisible nodes
			//
			for (ActivityEdge conn : list) {
				ActivityNode node = conn.getSource();
				NodeAdapter nodeAdapter = BridgeHelper.getNodeAdapter(node);
				if(nodeAdapter != null) {
					View view = nodeAdapter.getView();
					if(view != null && view.isVisible()) {
						return true;
					}
				}
			}
		}
		return false;
	}
}
