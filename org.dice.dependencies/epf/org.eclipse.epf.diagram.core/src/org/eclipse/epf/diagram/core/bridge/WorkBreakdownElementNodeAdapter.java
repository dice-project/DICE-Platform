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
package org.eclipse.epf.diagram.core.bridge;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.epf.diagram.model.Diagram;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.util.MethodElementPropertyHelper;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodElementProperty;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.WorkBreakdownElement;
import org.eclipse.epf.uma.WorkOrder;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ControlNode;

/**
 * @author Phong Nguyen Le
 *
 * @since 1.2
 */
public class WorkBreakdownElementNodeAdapter extends NodeAdapter {
	private class WorkBreakdownElementAdapter extends MethodElementAdapter {
		protected Collection handleNotification(Notification msg) {
			WorkOrder obj;
			switch (msg.getFeatureID(WorkBreakdownElement.class)) {
			case UmaPackage.WORK_BREAKDOWN_ELEMENT__LINK_TO_PREDECESSOR:
				switch (msg.getEventType()) {
				case Notification.ADD:
					obj = (WorkOrder) msg.getNewValue();
					if(isValid(obj)) {
						addIncomingConnection(obj.getPred());
					}
					break;
				case Notification.REMOVE:
					obj = (WorkOrder) msg.getOldValue();
					removeIncomingConnection(obj.getPred());
					break;
				case Notification.ADD_MANY:
					Collection<?> collection = (Collection<?>) msg.getNewValue();
					for (Iterator<?> iter = collection.iterator(); iter.hasNext();) {
						obj = (WorkOrder) iter.next();
						if(isValid(obj)) {
							addIncomingConnection(obj.getPred());
						}
					}
					break;
				case Notification.REMOVE_MANY:
					collection = (Collection) msg.getOldValue();
					for (Iterator iter = collection.iterator(); iter.hasNext();) {
						obj = (WorkOrder) iter.next();
						removeIncomingConnection(obj.getPred());
					}
					break;
				}
				break;
			case UmaPackage.WORK_BREAKDOWN_ELEMENT__SUPPRESSED:
				switch (msg.getEventType()) {
				case Notification.SET:
//					Boolean b = (Boolean)msg.getNewValue();
//					handleSuppressed(b.booleanValue());
					break;
				}
				break;
			default:
				return super.handleNotification(msg);
			}
			return Collections.EMPTY_LIST;
		}
	}

	/**
	 * @param e
	 */
	public WorkBreakdownElementNodeAdapter(MethodElement e) {
		super(e);
	}
	
	/**
	 * Checks if the specified work order could be valid for the diagram to create
	 * new connection.
	 * 
	 * @param wo
	 * @return
	 */
	private boolean isValid(WorkOrder wo) {
		MethodElementProperty prop = MethodElementPropertyHelper.getProperty(wo, MethodElementPropertyHelper.WORK_ORDER__PREDECESSOR_IS_SIBLING);
		if(prop != null) {
			return true;
		}
		ActivityNode node = getNode();
		org.eclipse.uml2.uml.Activity diagram = getDiagram();
		ActivityDiagramAdapter diagramAdapter = (ActivityDiagramAdapter) BridgeHelper.getDiagramAdapter(diagram);
		List<WorkBreakdownElement> localPredecessors = diagramAdapter.getLocalPredecessors(node);
		return localPredecessors.contains(wo.getPred());
	}

	public void handleSuppressed(boolean b) {
		if(b){
			List incomings = getNode().getIncomings();
			for (int i =0; i<incomings.size(); i++) {
				Object obj = incomings.get(i);
				ActivityEdge link = null;
				if(obj != null ){
					link = (ActivityEdge)obj;
				}
				if(link != null && link.getSource() != null){
					NodeAdapter sourceNodeAdapter = BridgeHelper.getNodeAdapter(link.getSource());
					boolean oldNotify1 = sourceNodeAdapter != null ? sourceNodeAdapter.notificationEnabled : false;
					try {
						if(sourceNodeAdapter != null) sourceNodeAdapter.notificationEnabled = false;
						link.setTarget(null);
						getNode().getIncomings().remove(link);
					} finally {
						if(sourceNodeAdapter != null) sourceNodeAdapter.notificationEnabled = oldNotify1;
					}
				}
			}
			List outGoings = getNode().getOutgoings();
			for (int i =0; i<outGoings.size(); i++) {
				Object obj = outGoings.get(i);
				ActivityEdge link = null;
				if(obj != null ){
					link = (ActivityEdge)obj;
				}
				if(link != null && link.getTarget() != null){
					NodeAdapter nodeAdapter = BridgeHelper.getNodeAdapter(link.getTarget());
					boolean oldNotify1 = nodeAdapter != null ? nodeAdapter.notificationEnabled : false;
					try {
						if(nodeAdapter != null) nodeAdapter.notificationEnabled = false;
						link.setSource(null);
						getNode().getIncomings().remove(link);
					} finally {
						if(nodeAdapter != null) nodeAdapter.notificationEnabled = oldNotify1;
					}
				}
			}
		}else{
			List nodes = getDiagram().getNodes();
			for (Iterator iter = nodes.iterator(); iter.hasNext();) {
				ActivityNode element = (ActivityNode) iter.next();
				if(element != getNode() && target instanceof MethodElement){
					MethodElement me = BridgeHelper.getMethodElement(element);
					if(me != null){
						addIncomingConnection(me);
						addOutgoingConnection(me);
					}
				}
			}
		}
	}

	protected MethodElementAdapter createMethodElementAdapter() {
		return new WorkBreakdownElementAdapter();
	}
	
	@Override
	protected void removeFromUMA(ActivityEdge link, ActivityNode oldSource,
			ActivityNode oldTarget) {
		org.eclipse.epf.uma.Activity diagramActivity = (org.eclipse.epf.uma.Activity) BridgeHelper.getMethodElement(getDiagram());
		ProcessPackage pkg = (ProcessPackage) diagramActivity.eContainer();
		MethodElement targetElement = BridgeHelper.getMethodElement(oldTarget);
		if (targetElement instanceof WorkBreakdownElement) {
			// this is a direct link
			// remove WorkOrder
			//
			WorkBreakdownElement succ = (WorkBreakdownElement) targetElement;
			if(diagramActivity != succ.getSuperActivities()) {
				Object sourceElement = BridgeHelper.getMethodElement(oldSource);
				if(sourceElement instanceof WorkBreakdownElement) {
					WorkBreakdownElement pred = (WorkBreakdownElement) sourceElement;
					WorkOrder order = ProcessUtil.findWorkOrder(diagramActivity, succ, pred);
					if(order != null) {
						getActionManager().doAction(IActionManager.REMOVE, pkg, 
								UmaPackage.Literals.PROCESS_PACKAGE__PROCESS_ELEMENTS, order, -1);
					}
				}
			}
			else {
				NodeAdapter targetNodeAdapter = BridgeHelper.getNodeAdapter(oldTarget);
				boolean notify = targetNodeAdapter != null ? targetNodeAdapter.notificationEnabled : false;
				try {
					if(targetNodeAdapter != null) {
						targetNodeAdapter.notificationEnabled = false;
					}
					if (BridgeHelper.canRemoveAllPreds(link, oldSource,
							oldTarget)) {
						Object pred = BridgeHelper.getMethodElement(oldSource);
						if(pred instanceof WorkBreakdownElement) {
							WorkBreakdownElement e = (WorkBreakdownElement) BridgeHelper.getMethodElement(oldTarget);
							Collection<WorkOrder> workOrders = UmaUtil.findWorkOrder(e, (WorkBreakdownElement) pred, true);
							if(!workOrders.isEmpty()) {
								getActionManager().doAction(IActionManager.REMOVE_MANY, e, 
										UmaPackage.Literals.WORK_BREAKDOWN_ELEMENT__LINK_TO_PREDECESSOR, workOrders, -1);
							}
						}
					}
				} finally {
					if(targetNodeAdapter != null) {
						targetNodeAdapter.notificationEnabled = notify;
					}
				}
			}
		} else if(oldTarget instanceof ControlNode && BridgeHelper.isSynchBar(oldTarget)) {
			// get all the WorkBreakdownElementNodes that this synch bar is
			// coming to and
			// remove the WorkOrders with this WorkBreakdownElementNode's
			// activity as predecessor from them
			//
			Collection<ActivityNode> actNodes = new ArrayList<ActivityNode>();
			BridgeHelper.getSuccessorNodes(actNodes, oldTarget);
			Object pred = BridgeHelper.getMethodElement(oldSource);
			for (ActivityNode node : actNodes) {
				WorkBreakdownElement e = (WorkBreakdownElement) BridgeHelper.getMethodElement(node);
				if(diagramActivity == e.getSuperActivities()) {
					NodeAdapter nodeAdapter = BridgeHelper.getNodeAdapter(node);
					boolean notify = nodeAdapter != null ? nodeAdapter.notificationEnabled : false;
					try {
						if(nodeAdapter != null) {
							nodeAdapter.notificationEnabled = false;
						}
						if (BridgeHelper.canRemoveAllPreds(link, oldSource, node)) {
							WorkOrder wo;
							while ((wo = UmaUtil.findWorkOrder(e, pred)) != null) {
								getActionManager().doAction(IActionManager.REMOVE, e, 
										UmaPackage.Literals.WORK_BREAKDOWN_ELEMENT__LINK_TO_PREDECESSOR, wo, -1);
							}
						}
					} finally {
						if(nodeAdapter != null) {
							nodeAdapter.notificationEnabled = notify;
						}
					}
				}
				else {
					if(pred instanceof WorkBreakdownElement) {
						// remove custom work order if there is any
						//
						WorkOrder wo = ProcessUtil.findWorkOrder(diagramActivity, e, (WorkBreakdownElement) pred);
						if(wo != null) {
							getActionManager().doAction(IActionManager.REMOVE, pkg, 
									UmaPackage.Literals.PROCESS_PACKAGE__PROCESS_ELEMENTS, wo, -1);
						}
					}
				}
			}
		}
	}
	
	protected boolean addToUMA(final ActivityEdge link) {
		if (!super.addToUMA(link))
			return false;
		
		MethodElement e = BridgeHelper.getMethodElement(link.getSource());
		Activity diagramActivity = (Activity) BridgeHelper.getMethodElement(getDiagram());
		ProcessPackage pkg = (ProcessPackage) diagramActivity.eContainer();
		if (e instanceof WorkBreakdownElement) {
			// this is a direct link
			// add new WorkOrder object to UMA
			//
			WorkBreakdownElement succ = (WorkBreakdownElement) BridgeHelper.getMethodElement(link.getTarget());
			WorkBreakdownElement pred = (WorkBreakdownElement) e;
			WorkOrder order = addWorkOrder(link.getTarget(), succ, link.getSource(), pred, diagramActivity, link);			
			// set the WorkOrder as the element of the link's edge
//			BridgeHelper.setSemanticModel(link, order);
		} 
		else if (BridgeHelper.isSynchBar(link.getSource())) {
			// get all WorkBreakdownElementNodes that are comming to this
			// join node, both directly and indirectly
			// and create work orders with their activity as predecessor
			//

			// Predecessor should be created only in case of
			// Syncronization Bar, not for DecisionPoint.
			// TODO: review
			
				
				Collection actNodes = new ArrayList();
				// Get the collection incoming connection of syncbar 
				// excluding decisionpoint incoming connection
				BridgeHelper.getSyncBarSourceNodes(link.getSource(), actNodes);
				WorkBreakdownElement succ = (WorkBreakdownElement) BridgeHelper.getMethodElement(link.getTarget());
				for (Iterator<?> iter = actNodes.iterator(); iter.hasNext();) {
					ActivityNode node = (ActivityNode) iter.next();
					WorkBreakdownElement pred = (WorkBreakdownElement) BridgeHelper.getMethodElement(node);
					addWorkOrder(link.getTarget(), succ, node, pred, diagramActivity, null);
				}
		}
		return true;
	}
	
	protected ActivityEdge addIncomingConnection(MethodElement source) {
		ActivityEdge link = super.addIncomingConnection(source);
		if (link == null)
			return link;

		WorkOrder workOrder = UmaUtil.findWorkOrder(
				(WorkBreakdownElement) getElement(), source);
		BridgeHelper.setSemanticModel(link, workOrder);

		return link;
	}

	protected ActivityEdge addOutgoingConnection(MethodElement target) {
		ActivityEdge link = super.addOutgoingConnection(target);

		WorkOrder workOrder = UmaUtil.findWorkOrder(
				(WorkBreakdownElement) target, getElement());
		BridgeHelper.setSemanticModel(link, workOrder);

		return link;
	}

	protected boolean removeIncomingConnection(MethodElement source) {
		// look for the incoming connection with source as linked object of the
		// source node
		//
		for (ActivityEdge link : getNode().getIncomings()) {
			if (link.getSource() != null
					&& BridgeHelper.getMethodElement(link.getSource()) == source) {
				// disable internal notification of the source node
				//
				NodeAdapter nodeAdapter = BridgeHelper.getNodeAdapter(link.getSource());
				boolean oldNotify = nodeAdapter != null ? nodeAdapter.notificationEnabled : false;
				try {
					if(nodeAdapter != null) nodeAdapter.notificationEnabled = false;
					link.setSource(null);
				} finally {
					if(nodeAdapter != null) nodeAdapter.notificationEnabled = oldNotify;
				}
				link.setTarget(null);
				BridgeHelper.setSemanticModel(link, null);
				getDiagram().getEdges().remove(link);
				return true;
			}
		}
		return false;
	}
	
//	@Override
//	protected void handleNotification(Notification msg) {
//		switch(msg.getFeatureID(ActivityParameterNode.class)) {
//		case UMLPackage.ACTIVITY_PARAMETER_NODE__EANNOTATIONS:
//			switch(msg.getEventType()) {
//			case Notification.ADD:
//				ActivityParameterNode node = (ActivityParameterNode) msg.getNotifier();
//				String type = BridgeHelper.getType(node);
//				MethodElement e = BridgeHelper.getMethodElement(node);				
//				if(e instanceof BreakdownElement && 
//						type != null && !type.equals(BridgeHelper.getType(e))) {
//					// replace the currently linked method element with the right one
//					//
//					EClass rightType = BridgeHelper.getType(type);
//					if(rightType != null) {
//						Object rightElement = UmaFactory.eINSTANCE.create(rightType);
//						List list = ((BreakdownElement)e).getSuperActivities().getBreakdownElements();
//						list.set(list.indexOf(e), rightElement);
//						return;
//					}
//					
//				}
//			}
//		}
//		
//		super.handleNotification(msg);
//	}
}
