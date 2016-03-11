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
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.epf.diagram.model.Link;
import org.eclipse.epf.diagram.model.ModelPackage;
import org.eclipse.epf.diagram.model.Node;
import org.eclipse.epf.diagram.model.TypedNode;
import org.eclipse.epf.diagram.model.WorkBreakdownElementNode;
import org.eclipse.epf.diagram.model.util.GraphicalDataHelper;
import org.eclipse.epf.uma.GraphNode;
import org.eclipse.epf.uma.WorkBreakdownElement;
import org.eclipse.epf.uma.util.UmaUtil;


/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Typed Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.diagram.model.impl.TypedNodeImpl#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TypedNodeImpl extends NodeImpl implements TypedNode {
	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final int TYPE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected int type = TYPE_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected TypedNodeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.TYPED_NODE;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public int getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(int newType) {
		int oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TYPED_NODE__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelPackage.TYPED_NODE__TYPE:
				return new Integer(getType());
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ModelPackage.TYPED_NODE__TYPE:
				setType(((Integer)newValue).intValue());
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ModelPackage.TYPED_NODE__TYPE:
				setType(TYPE_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ModelPackage.TYPED_NODE__TYPE:
				return type != TYPE_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (type: "); //$NON-NLS-1$
		result.append(type);
		result.append(')');
		return result.toString();
	}

	protected void basicSetObject(Object newObject) {
		//populate data from linked GraphNode
		// 
		graphNode = (GraphNode) newObject;
		setWidth(graphNode.getSize().getWidth().intValue());
		setHeight(graphNode.getSize().getHeight().intValue());
		setLocation(GraphicalDataHelper.toPoint(graphNode.getPosition()));
//		 this.eAdapters().add(new AdapterImpl() {
		// /* (non-Javadoc)
		// * @see
		// org.eclipse.emf.common.notify.impl.AdapterImpl#notifyChanged(org.eclipse.emf.common.notify.Notification)
		// */
		// public void notifyChanged(Notification msg) {
		// GraphNode graphNode = (GraphNode) getObject();
		// switch(msg.getFeatureID(TypedNode.class)) {
		// case ModelPackage.TYPED_NODE__WIDTH:
		// graphNode.getSize().setWidth(new Double(msg.getNewIntValue()));
		// return;
		//                    
		// case ModelPackage.TYPED_NODE__HEIGHT:
		// graphNode.getSize().setHeight(new Double(msg.getNewIntValue()));
		// return;
		//                        
		// case ModelPackage.TYPED_NODE__LOCATION:
		// Point p = (Point) msg.getNewValue();
		// System.out.println("TypedNodeImpl$.notifyChanged(): new location: " +
		// p + ". " + TypedNodeImpl.this);
		// graphNode.getPosition().setX(new Double(p.x));
		// graphNode.getPosition().setY(new Double(p.y));
		// return;
		//                    
		// case ModelPackage.TYPED_NODE__INCOMING_CONNECTIONS:
		// Link link;
		// switch(msg.getEventType()) {
		// case Notification.ADD:
		// addToUMA((Link) msg.getNewValue());
		// break;
		// case Notification.ADD_MANY:
		// for (Iterator iter = ((Collection)msg.getNewValue()).iterator(); iter
		// .hasNext();) {
		// addToUMA((Link) iter.next());
		// }
		// break;
		// case Notification.REMOVE:
		// link = (Link) msg.getOldValue();
		// if(link.getSource() != null) {
		// ((NodeImpl)link.getSource()).removeFromUMA(link, link.getSource(),
		// (Node)msg.getNotifier());
		// }
		// break;
		// case Notification.REMOVE_MANY:
		// for (Iterator iter = ((Collection)msg.getOldValue()).iterator(); iter
		// .hasNext();) {
		// link = (Link) iter.next();
		// if(link.getSource() != null) {
		// ((NodeImpl)link.getSource()).removeFromUMA(link, link.getSource(),
		// (Node)msg.getNotifier());
		// }
		// }
		// break;
		// }
		// return;
		//
		// case ModelPackage.TYPED_NODE__OUTGOING_CONNECTIONS:
		// switch(msg.getEventType()) {
		// case Notification.ADD:
		// addToUMA((Link) msg.getNewValue());
		// break;
		// case Notification.ADD_MANY:
		// for (Iterator iter = ((Collection)msg.getNewValue()).iterator(); iter
		// .hasNext();) {
		// addToUMA((Link) iter.next());
		// }
		// break;
		//
		// case Notification.REMOVE:
		// link = (Link) msg.getOldValue();
		// if(link.getTarget() != null) {
		// removeFromUMA(link, (Node)msg.getNotifier(), link.getTarget());
		// }
		// break;
		// case Notification.REMOVE_MANY:
		// for (Iterator iter = ((Collection)msg.getOldValue()).iterator(); iter
		// .hasNext();) {
		// link = (Link) iter.next();
		// if(link.getTarget() != null) {
		// removeFromUMA(link, (Node)msg.getNotifier(), link.getTarget());
		// }
		// }
		// break;
		// }
		// return;
		// }
		// }
		//
		// });
		eAdapters().add(new NodeAdapter());
	}
	
	public Class getMethodElementAdapterType() {
		return null;
	}

	protected boolean addToUMA(Link link) {
		if (!super.addToUMA(link))
			return false;

		if (link.getSource() instanceof WorkBreakdownElementNode) {
			if (link.getTarget() instanceof TypedNode
					&& ((TypedNode) link.getTarget()).getType() == TypedNode.SYNCH_BAR) {
				Collection targetActNodes = new ArrayList();
				GraphicalDataHelper.getSyncBarTargetNodes((TypedNode) link
						.getTarget(), targetActNodes);
				for (Iterator iter = targetActNodes.iterator(); iter.hasNext();) {
					NamedNodeImpl node = ((NamedNodeImpl) iter.next());
					if (UmaUtil.findWorkOrder((WorkBreakdownElement) node
							.getObject(), (WorkBreakdownElement) link
							.getSource().getObject()) == null) {
						addDefaultWorkOrder(node, (WorkBreakdownElement) link
								.getSource().getObject());
					}
				}
			}
		} else if (link.getSource() instanceof TypedNode) {
			// Predecessor should be created only in case of
			// Syncronization Bar, not for DecisionPoint.
			if (((TypedNode) link.getSource()).getType() == TypedNode.SYNCH_BAR) {
				Collection srcActNodes = new ArrayList();
				GraphicalDataHelper.getSyncBarSourceNodes((TypedNode) link
						.getSource(), srcActNodes);
				Collection targetActNodes = new ArrayList();
				GraphicalDataHelper.getSyncBarTargetNodes((TypedNode) link
						.getTarget(), targetActNodes);

				for (Iterator iter = targetActNodes.iterator(); iter.hasNext();) {
					NamedNodeImpl node = ((NamedNodeImpl) iter.next());
					for (Iterator iterator = srcActNodes.iterator(); iterator
							.hasNext();) {
						WorkBreakdownElement pred = (WorkBreakdownElement) ((Node) iterator
								.next()).getObject();
						if (UmaUtil.findWorkOrder((WorkBreakdownElement) node
								.getObject(), pred) == null) {
							addDefaultWorkOrder(node, pred);
						}
					}
				}
			}
		}

		return true;
	}

	protected void removeFromUMA(Link link, Node oldSource, Node oldTarget) {
		TypedNode typedNode = (TypedNode) oldSource;
		Collection srcActNodes = GraphicalDataHelper.getSourceNodes(typedNode,
				WorkBreakdownElementNode.class);

		if (oldTarget instanceof WorkBreakdownElementNode) {
			for (Iterator iterator = srcActNodes.iterator(); iterator.hasNext();) {
				// Object pred = ((Node) iterator.next()).getObject();
				// GraphicalDataHelper.removeWorkOrder((NamedNodeImpl)
				// oldTarget, pred);
				Node node = (Node) iterator.next();
				if (GraphicalDataHelper
						.canRemoveAllPreds(link, node, oldTarget)) {
					while (UmaUtil.findWorkOrder(
							(WorkBreakdownElement) oldTarget.getObject(),
							(WorkBreakdownElement) node.getObject()) != null) {
						UmaUtil.removeWorkOrder(
								(WorkBreakdownElement) oldTarget.getObject(),
								(WorkBreakdownElement) node.getObject());
					}
				}
			}
		} else if (oldTarget instanceof TypedNode) {
			Collection targetActNodes = GraphicalDataHelper.getTargetNodes(
					(TypedNode) oldTarget, WorkBreakdownElementNode.class);

			// remove the work orders of target activities that have the
			// predecessor in srcActNodes
			//
			for (Iterator iter = targetActNodes.iterator(); iter.hasNext();) {
				NamedNodeImpl node = ((NamedNodeImpl) iter.next());
				for (Iterator iterator = srcActNodes.iterator(); iterator
						.hasNext();) {
					// Object pred = ((Node) iterator.next()).getObject();
					// GraphicalDataHelper.removeWorkOrder(node, pred);
					Node prednode = (Node) iterator.next();
					if (GraphicalDataHelper.canRemoveAllPreds(link, prednode,
							node)) {
						while (UmaUtil.findWorkOrder(
								(WorkBreakdownElement) node.getObject(),
								(WorkBreakdownElement) prednode.getObject()) != null) {
							UmaUtil
									.removeWorkOrder(
											(WorkBreakdownElement) node
													.getObject(),
											(WorkBreakdownElement) prednode
													.getObject());
						}
					}
				}
			}
		}

		super.removeFromUMA(link, oldSource, oldTarget);
	}
} // TypedNodeImpl
