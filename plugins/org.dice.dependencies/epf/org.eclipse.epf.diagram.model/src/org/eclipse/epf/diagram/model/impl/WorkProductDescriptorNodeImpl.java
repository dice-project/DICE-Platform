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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.epf.diagram.model.ActivityDetailDiagram;
import org.eclipse.epf.diagram.model.Link;
import org.eclipse.epf.diagram.model.ModelPackage;
import org.eclipse.epf.diagram.model.Node;
import org.eclipse.epf.diagram.model.WorkProductComposite;
import org.eclipse.epf.diagram.model.WorkProductDescriptorNode;
import org.eclipse.epf.diagram.model.util.GraphicalDataHelper;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.WorkProductDescriptor;


/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Work Product Descriptor Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.diagram.model.impl.WorkProductDescriptorNodeImpl#getState <em>State</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class WorkProductDescriptorNodeImpl extends NamedNodeImpl implements
		WorkProductDescriptorNode {
	/**
	 * The default value of the '{@link #getState() <em>State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getState()
	 * @generated
	 * @ordered
	 */
	protected static final String STATE_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getState() <em>State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getState()
	 * @generated
	 * @ordered
	 */
	protected String state = STATE_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 */
	protected WorkProductDescriptorNodeImpl() {
		super();

		methodElementAdapter = new WorkProductDescriptorAdapter();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.WORK_PRODUCT_DESCRIPTOR_NODE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getState() {
		return state;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setState(String newState) {
		String oldState = state;
		state = newState;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.WORK_PRODUCT_DESCRIPTOR_NODE__STATE, oldState, state));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelPackage.WORK_PRODUCT_DESCRIPTOR_NODE__STATE:
				return getState();
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
			case ModelPackage.WORK_PRODUCT_DESCRIPTOR_NODE__STATE:
				setState((String)newValue);
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
			case ModelPackage.WORK_PRODUCT_DESCRIPTOR_NODE__STATE:
				setState(STATE_EDEFAULT);
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
			case ModelPackage.WORK_PRODUCT_DESCRIPTOR_NODE__STATE:
				return STATE_EDEFAULT == null ? state != null : !STATE_EDEFAULT.equals(state);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (state: "); //$NON-NLS-1$
		result.append(state);
		result.append(')');
		return result.toString();
	}

	private class WorkProductDescriptorAdapter extends MethodElementAdapter {
		
		@Override
		protected void handleNotification(Notification msg) {
			switch (msg.getFeatureID(WorkProductDescriptor.class)) {
			case UmaPackage.WORK_PRODUCT_DESCRIPTOR__SUPPRESSED:
				switch (msg.getEventType()) {
				case Notification.SET:
					Boolean b = (Boolean)msg.getNewValue();
					// TODO: implement handling suppress.
				}
				break;
			case UmaPackage.WORK_PRODUCT_DESCRIPTOR__ACTIVITY_ENTRY_STATE:
				if(msg.getEventType() == Notification.SET && isInput()) {
					setState(msg.getNewStringValue());
				}
				break;
			case UmaPackage.WORK_PRODUCT_DESCRIPTOR__ACTIVITY_EXIT_STATE:
				if(msg.getEventType() == Notification.SET && isOutput()) {
					setState(msg.getNewStringValue());
				}
				break;
			}
			super.handleNotification(msg);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.epf.diagram.model.impl.NamedNodeImpl.MethodElementAdapter#getNode()
		 */
		public Node getNode() {
			return WorkProductDescriptorNodeImpl.this;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.diagram.model.impl.NamedNodeImpl#findNode(org.eclipse.epf.uma.MethodElement)
	 */
	protected Node findNode(MethodElement linkedElement) {
		return GraphicalDataHelper.findNode(
				(ActivityDetailDiagram) getDiagram(), linkedElement);
	}

	public boolean isOutput() {
		EObject container = eContainer();
		return container instanceof WorkProductComposite && ((WorkProductComposite) container).getType() == WorkProductComposite.OUTPUTS;
	}

	public boolean isInput() {
		EObject container = eContainer();
		return container instanceof WorkProductComposite && ((WorkProductComposite) container).getType() == WorkProductComposite.INPUTS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.diagram.model.impl.NodeImpl#getMethodElementAdapterType()
	 */
	public Class getMethodElementAdapterType() {
		return WorkProductDescriptorAdapter.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.diagram.model.impl.NodeImpl#addToUMA(org.eclipse.epf.diagram.model.Link)
	 */
	protected boolean addToUMA(Link link) {
		if (!super.addToUMA(link))
			return false;

		WorkProductDescriptor wpd = (WorkProductDescriptor) getObject();

		// disable notification of source node before changing the UMA object
		// associated with it
		// so it will not create duplicate links
		//
		NodeImpl nodeImpl = (NodeImpl) link.getSource();
		boolean notify = nodeImpl.notificationEnabled;
		boolean notifyTarget = notificationEnabled;
		try {
			nodeImpl.notificationEnabled = false;
			notificationEnabled = false;
//			wpd.getOutputFrom().add(link.getSource().getObject());
			TaskDescriptor taskDesc = (TaskDescriptor) link.getSource().getObject();
			if(taskDesc != null) {
				taskDesc.getOutput().add(wpd);
			}
		} finally {
			nodeImpl.notificationEnabled = notify;
			notificationEnabled = notifyTarget;
		}

		// TaskDescriptor td = (TaskDescriptor) link.getSource().getObject();
		// td.getOutput().add(getObject());

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.diagram.model.impl.NodeImpl#removeFromUMA(org.eclipse.epf.diagram.model.Link,
	 *      org.eclipse.epf.diagram.model.Node, org.eclipse.epf.diagram.model.Node)
	 */
	protected void removeFromUMA(Link link, Node oldSource, Node oldTarget) {
		WorkProductDescriptor wpd = (WorkProductDescriptor) getObject();
//		wpd.getMandatoryInputTo().remove(oldTarget.getObject());
		TaskDescriptor taskDesc = (TaskDescriptor) oldTarget.getObject();
		if(taskDesc != null) {
			taskDesc.getMandatoryInput().remove(wpd);
		}

		super.removeFromUMA(link, oldSource, oldTarget);
	}
	
	@Override
	protected void dispose() {
		super.dispose();
	}
} // WorkProductDescriptorNodeImpl
