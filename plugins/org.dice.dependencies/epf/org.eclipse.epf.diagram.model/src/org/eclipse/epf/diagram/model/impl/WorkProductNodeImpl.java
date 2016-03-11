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
import org.eclipse.epf.diagram.model.WorkProductNode;
import org.eclipse.epf.diagram.model.util.GraphicalDataHelper;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.WorkProductDescriptor;


/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Work Product Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.diagram.model.impl.WorkProductNodeImpl#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class WorkProductNodeImpl extends NamedNodeImpl implements
		WorkProductNode {
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

	private class WorkProductDescriptorAdapter extends MethodElementAdapter {

		public Node getNode() {
			return WorkProductNodeImpl.this;
		}

		public void notifyChanged(Notification msg) {
			if (!notificationEnabled)
				return;
			notificationEnabled = false;

			try {
				switch (msg.getFeatureID(WorkProductDescriptor.class)) {
				case UmaPackage.WORK_PRODUCT_DESCRIPTOR__IMPACTED_BY:
					MethodElement obj;
					switch (msg.getEventType()) {
					case Notification.ADD:
						obj = (MethodElement) msg.getNewValue();
						addIncomingConnection(obj);
						return;
					case Notification.REMOVE:
						obj = (MethodElement) msg.getOldValue();
						removeIncomingConnection(obj);
						return;
					case Notification.ADD_MANY:
						Collection collection = (Collection) msg.getNewValue();
						for (Iterator iter = collection.iterator(); iter
								.hasNext();) {
							obj = (MethodElement) iter.next();
							addIncomingConnection(obj);
						}
						return;
					case Notification.REMOVE_MANY:
						collection = (Collection) msg.getOldValue();
						for (Iterator iter = collection.iterator(); iter
								.hasNext();) {
							obj = (MethodElement) iter.next();
							removeIncomingConnection(obj);
						}
						return;
					}
				case UmaPackage.WORK_PRODUCT__SUPPRESSED:
					switch (msg.getEventType()) {
					case Notification.SET:
						Boolean b = (Boolean)msg.getNewValue();
						if(b.booleanValue()==true){
							removeLinks();
						}
					}
				}
				super.notifyChanged(msg);
			} finally {
				notificationEnabled = true;
			}
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 */
	protected WorkProductNodeImpl() {
		super();

		methodElementAdapter = new WorkProductDescriptorAdapter();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.WORK_PRODUCT_NODE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.WORK_PRODUCT_NODE__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelPackage.WORK_PRODUCT_NODE__TYPE:
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
			case ModelPackage.WORK_PRODUCT_NODE__TYPE:
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
			case ModelPackage.WORK_PRODUCT_NODE__TYPE:
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
			case ModelPackage.WORK_PRODUCT_NODE__TYPE:
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

	protected boolean addToUMA(Link link) {
		if (!super.addToUMA(link))
			return false;

		WorkProductDescriptor descriptor = (WorkProductDescriptor) link
				.getSource().getObject();

		NodeImpl source = (NodeImpl) link.getSource();
		NodeImpl target = (NodeImpl) link.getTarget();
		boolean notifySource = source.notificationEnabled;
		boolean notifyTarget = target.notificationEnabled;
		try {
			source.notificationEnabled = target.notificationEnabled = false;
			descriptor.getImpacts().add((WorkProductDescriptor) link.getTarget().getObject());
		} finally {
			source.notificationEnabled = notifySource;
			target.notificationEnabled = notifyTarget;
		}

		return true;
	}

	protected void removeFromUMA(Link link, Node oldSource, Node oldTarget) {
		WorkProductDescriptor descriptor = (WorkProductDescriptor) oldTarget
				.getObject();
		// disable internal notification of the target node before removing its
		// descriptor from the dependency list
		//
		NamedNodeImpl nodeImpl = (NamedNodeImpl) oldSource;
		boolean notify = nodeImpl.notificationEnabled;
		try {
			nodeImpl.notificationEnabled = false;
			descriptor.getImpactedBy().remove(nodeImpl.getObject());
		} finally {
			nodeImpl.notificationEnabled = notify;
		}

		super.removeFromUMA(link, oldSource, oldTarget);
	}

	protected void removeLinks() {
		for (Iterator iter = getIncomingConnections().iterator(); iter
				.hasNext();) {
			Link link = (Link) iter.next();
			if (link.getSource() != null) {
				GraphicalDataHelper.removeGraphicalData(link);
				// disable internal notification of the source node
				//
				NodeImpl sourceNode = (NodeImpl) link.getSource();
				boolean oldNotify = sourceNode.notificationEnabled;
				try {
					sourceNode.notificationEnabled = false;
					link.setSource(null);
				} finally {
					sourceNode.notificationEnabled = oldNotify;
				}
				//link.setTarget(null);
				link.setObject(null);
			}
		}

		for (Iterator iter = getOutgoingConnections().iterator(); iter
				.hasNext();) {
			Link link = (Link) iter.next();
			if (link.getTarget() != null) {
				GraphicalDataHelper.removeGraphicalData(link);
				// disable internal notification of the source node
				//
				NodeImpl targetNode = (NodeImpl) link.getTarget();
				boolean oldNotify = targetNode.notificationEnabled;
				try {
					targetNode.notificationEnabled = false;
					link.setTarget(null);
				} finally {
					targetNode.notificationEnabled = oldNotify;
				}
				//link.setSource(null);
				link.setObject(null);
			}
		}
	}
} // WorkProductNodeImpl
