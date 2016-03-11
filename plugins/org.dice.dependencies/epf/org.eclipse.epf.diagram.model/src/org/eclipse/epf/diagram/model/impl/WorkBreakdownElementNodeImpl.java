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
package org.eclipse.epf.diagram.model.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.epf.diagram.model.Link;
import org.eclipse.epf.diagram.model.ModelPackage;
import org.eclipse.epf.diagram.model.Node;
import org.eclipse.epf.diagram.model.TypedNode;
import org.eclipse.epf.diagram.model.WorkBreakdownElementNode;
import org.eclipse.epf.diagram.model.util.GraphicalDataHelper;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.WorkBreakdownElement;
import org.eclipse.epf.uma.WorkOrder;
import org.eclipse.epf.uma.util.UmaUtil;


/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Work Breakdown Element Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class WorkBreakdownElementNodeImpl extends NamedNodeImpl implements
		WorkBreakdownElementNode {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 */
	protected WorkBreakdownElementNodeImpl() {
		super();

		methodElementAdapter = new WorkBreakdownElementAdapter();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.WORK_BREAKDOWN_ELEMENT_NODE;
	}

	private class WorkBreakdownElementAdapter extends MethodElementAdapter {
		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.emf.common.notify.impl.AdapterImpl#notifyChanged(org.eclipse.emf.common.notify.Notification)
		 */
		public void notifyChanged(Notification msg) {
			if (!notificationEnabled)
				return;
			notificationEnabled = false;
			try {
				WorkOrder obj;
				switch (msg.getFeatureID(WorkBreakdownElement.class)) {
				case UmaPackage.WORK_BREAKDOWN_ELEMENT__LINK_TO_PREDECESSOR:
					switch (msg.getEventType()) {
					case Notification.ADD:
						obj = (WorkOrder) msg.getNewValue();
						addIncomingConnection(obj.getPred());
						return;
					case Notification.REMOVE:
						obj = (WorkOrder) msg.getOldValue();
						removeIncomingConnection(obj.getPred());
						return;
					case Notification.ADD_MANY:
						Collection collection = (Collection) msg.getNewValue();
						for (Iterator iter = collection.iterator(); iter
								.hasNext();) {
							obj = (WorkOrder) iter.next();
							addIncomingConnection(obj.getPred());
						}
						return;
					case Notification.REMOVE_MANY:
						collection = (Collection) msg.getOldValue();
						for (Iterator iter = collection.iterator(); iter
								.hasNext();) {
							obj = (WorkOrder) iter.next();
							removeIncomingConnection(obj.getPred());
						}
						return;
					}
					break;

				}
			} finally {
				notificationEnabled = true;
			}

			super.notifyChanged(msg);
		}

	}

	protected void removeFromUMA(Link link, Node oldSource, Node oldTarget) {
		if (oldTarget instanceof WorkBreakdownElementNode) {
			// this is a direct link
			// remove WorkOrder
			//
			// GraphEdge edge = (GraphEdge) link.getObject();
			// WorkOrder order = (WorkOrder)
			// ((UMASemanticModelBridge)edge.getSemanticModel()).getElement();
			// WorkBreakdownElement be = UmaUtil.getOwner(order);
			// if(be != null) {
			NamedNodeImpl target = (NamedNodeImpl) oldTarget;
			boolean notify = target.notificationEnabled;
			try {
				target.notificationEnabled = false;
				// be.getLinkToPredecessor().remove(order);
				if (GraphicalDataHelper.canRemoveAllPreds(link, oldSource,
						oldTarget)) {
					while (UmaUtil.findWorkOrder((WorkBreakdownElement) target
							.getObject(), oldSource.getObject()) != null) {
						UmaUtil.removeWorkOrder((WorkBreakdownElement) target
								.getObject(), oldSource.getObject());
					}
				}
			} finally {
				target.notificationEnabled = notify;
			}
			// }
		} else if (oldTarget instanceof TypedNode) {
			// get all the WorkBreakdownElementNodes that this TypedNode are
			// coming to and
			// remove the WorkOrders with this WorkBreakdownElementNode's
			// activity as predecessor from them
			//
			Collection actNodes = GraphicalDataHelper.getTargetNodes(
					(TypedNode) oldTarget, WorkBreakdownElementNode.class);
			for (Iterator iter = actNodes.iterator(); iter.hasNext();) {
				NamedNodeImpl node = (NamedNodeImpl) iter.next();
				WorkBreakdownElement e = (WorkBreakdownElement) node
						.getObject();
				boolean notify = node.notificationEnabled;
				try {
					node.notificationEnabled = false;
					if (GraphicalDataHelper.canRemoveAllPreds(link, oldSource,
							node)) {
						while (UmaUtil.findWorkOrder(e, oldSource.getObject()) != null) {
							UmaUtil.removeWorkOrder(e, oldSource.getObject());
						}
					}
				} finally {
					node.notificationEnabled = notify;
				}
			}
		}
		// remove graphical data from UMA model
		//
		super.removeFromUMA(link, oldSource, oldTarget);
	}

	/**
	 * @param link
	 */
	protected boolean addToUMA(Link link) {
		if (!super.addToUMA(link))
			return false;

		Node src = link.getSource();
		if (src instanceof WorkBreakdownElementNode) {
			// this is a direct link
			// add new WorkOrder object to UMA
			//
			WorkOrder order = null;
			WorkBreakdownElement succ = (WorkBreakdownElement) link.getTarget()
					.getObject();
			WorkBreakdownElement pred = (WorkBreakdownElement) src.getObject();
			if (UmaUtil.findWorkOrder(succ, pred) == null) {
				NodeImpl sourceNode = ((NodeImpl) src);
				boolean sourceNotify = sourceNode.notificationEnabled;
				try {
					sourceNode.notificationEnabled = false;
					//UmaUtil.createDefaultWorkOrder(succ, pred);
					//Create a workorder and disable notification flag.
					addDefaultWorkOrder((NamedNodeImpl)link.getTarget(), pred);
				} finally {
					sourceNode.notificationEnabled = sourceNotify;
				}
			}

			// set the WorkOrder as the element of the SemanticModel of the
			// link's GraphEdge
			//
			GraphicalDataHelper.setSemanticModel(link, order);
		} else if (src instanceof TypedNode) {
			// get all WorkBreakdownElementNodes that are comming to this
			// TypedNode, both directly and indirectly
			// and create work orders with their activity as predecessor
			//

			// Predecessor should be created only in case of
			// Syncronization Bar, not for DecisionPoint.
			if (((TypedNode) src).getType() == TypedNode.SYNCH_BAR) {
				
				Collection actNodes = new ArrayList();
				// Get the collection incoming connection of syncbar 
				// excluding decisionpoint incoming connection
				GraphicalDataHelper.getSyncBarSourceNodes((TypedNode)src, actNodes);
				WorkBreakdownElement succ = (WorkBreakdownElement) link
						.getTarget().getObject();
				for (Iterator iter = actNodes.iterator(); iter.hasNext();) {
					Node node = (Node) iter.next();
					WorkBreakdownElement pred = (WorkBreakdownElement) node
							.getObject();
					if (UmaUtil.findWorkOrder(succ, pred) == null) {
						NodeImpl sourceNode = ((NodeImpl) node);
						boolean sourceNotify = sourceNode.notificationEnabled;
						try {
							sourceNode.notificationEnabled = false;
							//UmaUtil.createDefaultWorkOrder(succ, pred);
							// Create a workorder and disable notification flag.
							addDefaultWorkOrder((NamedNodeImpl)link.getTarget(),pred);
						} finally {
							sourceNode.notificationEnabled = sourceNotify;
						}
					}
				}
			}
		}

		return true;
	}

	protected Link addIncomingConnection(MethodElement source) {
		Link link = super.addIncomingConnection(source);
		if (link == null)
			return link;

		WorkOrder workOrder = UmaUtil.findWorkOrder(
				(WorkBreakdownElement) getObject(), source);
		GraphicalDataHelper.setSemanticModel(link, workOrder);

		return link;
	}

	protected Link addOutgoingConnection(MethodElement target) {
		Link link = super.addOutgoingConnection(target);

		WorkOrder workOrder = UmaUtil.findWorkOrder(
				(WorkBreakdownElement) target, getObject());
		GraphicalDataHelper.setSemanticModel(link, workOrder);

		return link;
	}

} // WorkBreakdownElementNodeImpl
