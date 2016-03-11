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

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.epf.common.CommonPlugin;
import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.diagram.model.Link;
import org.eclipse.epf.diagram.model.ModelFactory;
import org.eclipse.epf.diagram.model.ModelPackage;
import org.eclipse.epf.diagram.model.NamedNode;
import org.eclipse.epf.diagram.model.Node;
import org.eclipse.epf.diagram.model.util.GraphicalDataHelper;
import org.eclipse.epf.diagram.model.util.TxUtil;
import org.eclipse.epf.library.edit.process.BreakdownElementWrapperItemProvider;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.Suppression;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VariabilityElement;


/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Named Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.diagram.model.impl.NamedNodeImpl#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NamedNodeImpl extends NodeImpl implements NamedNode {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected NamedNodeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.NAMED_NODE;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.NAMED_NODE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelPackage.NAMED_NODE__NAME:
				return getName();
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
			case ModelPackage.NAMED_NODE__NAME:
				setName((String)newValue);
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
			case ModelPackage.NAMED_NODE__NAME:
				setName(NAME_EDEFAULT);
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
			case ModelPackage.NAMED_NODE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
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
		result.append(" (name: "); //$NON-NLS-1$
		result.append(name);
		result.append(')');
		return result.toString();
	}

	// -------------------//
	// Start custom code  //
	// -------------------//

	protected class MethodElementAdapter extends TransactionalNodeLink {
		protected void handleNotification(Notification msg) {
			switch (msg.getFeatureID(DescribableElement.class)) {
			case UmaPackage.DESCRIBABLE_ELEMENT__PRESENTATION_NAME:
				setName(msg.getNewStringValue());
				return;
			}
		}
		
		public void notifyChanged(final Notification msg) {
			if (!notificationEnabled)
				return;
			notificationEnabled = false;
			try {
				TxUtil.runInTransaction(getNode(), 
						new Runnable() {

							public void run() {
								handleNotification(msg);
							}
					
				});
			} catch (Exception e) {
				CommonPlugin.getDefault().getLogger().logError(e);
			} finally {
				notificationEnabled = true;
			}
		}

		public Node getNode() {
			return NamedNodeImpl.this;
		}

	}

	protected Link addIncomingConnection(MethodElement source) {
		Node srcNode = findNode(source);
		if (srcNode == null)
			return null;
		Link link = ModelFactory.eINSTANCE.createLink();
		link.setTarget(this);

		// disable notification of srcNode before associate it with the link
		// so it will not create duplicate UMA data
		//
		NodeImpl srcNodeImpl = (NodeImpl) srcNode;
		boolean notify = srcNodeImpl.notificationEnabled;
		try {
			srcNodeImpl.notificationEnabled = false;
			link.setSource(srcNode);
		} finally {
			srcNodeImpl.notificationEnabled = notify;
		}

		if(isGraphicalDataRequired()) {
			GraphicalDataHelper.addGraphicalData(link);
		}
		return link;
	}

	protected Node findNode(MethodElement linkedElement) {
		return GraphicalDataHelper.findNode(getDiagram(), linkedElement);
	}

	protected Link addOutgoingConnection(MethodElement target) {
		Node targetNode = findNode(target);
		if (targetNode == null)
			return null;
		Link link = ModelFactory.eINSTANCE.createLink();

		// disable notification of targetNode before associate it with the link
		// so it will not create duplicate UMA data
		//
		NodeImpl nodeImpl = (NodeImpl) targetNode;
		boolean notify = nodeImpl.notificationEnabled;
		try {
			nodeImpl.notificationEnabled = false;
			link.setTarget(targetNode);
		} finally {
			nodeImpl.notificationEnabled = notify;
		}

		link.setSource(this);
		if(isGraphicalDataRequired()) {
			GraphicalDataHelper.addGraphicalData(link);
		}
		return link;
	}

	protected boolean removeIncomingConnection(MethodElement source) {
		// look for the incoming connection with source as linked object of the
		// source node
		//
		for (Iterator iter = getIncomingConnections().iterator(); iter
				.hasNext();) {
			Link link = (Link) iter.next();
			if (link.getSource() != null
					&& link.getSource().getObject() == source) {
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
				link.setTarget(null);
				link.setObject(null);
				return true;
			}
		}
		return false;
	}

	protected boolean removeOutgoingConnection(MethodElement target) {
		// look for the incoming connection with source as linked object of the
		// source node
		//
		for (Iterator iter = getOutgoingConnections().iterator(); iter
				.hasNext();) {
			Link link = (Link) iter.next();
			if (link.getTarget() != null
					&& link.getTarget().getObject() == target) {
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
				link.setSource(null);
				link.setObject(null);
				return true;
			}
		}
		return false;
	}

	public void setObject(Object newObject) {
		super.setObject(newObject);

		if (newObject == null)
			return;
		String name = null;

		// use presentation name
		if (newObject instanceof DescribableElement) {
			DescribableElement element = (DescribableElement) newObject;
			name = ProcessUtil.getPresentationName((BreakdownElement) element);
		}

		// if it's blank then use name
		if (StrUtil.isBlank(name)) {
			MethodElement element = (MethodElement) newObject;
			name = element.getName();
		}

		this.name = name;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.diagram.model.impl.NodeImpl#basicSetObject(java.lang.Object)
	 */
	protected void basicSetObject(Object newObject) {
		super.basicSetObject(newObject);

		if (newObject instanceof VariabilityElement
				&& ProcessUtil
						.isExtendingOrLocallyContributing((BreakdownElement) newObject)) {
			// listen to change in the base
			//
			VariabilityElement base = ((VariabilityElement) newObject)
					.getVariabilityBasedOnElement();
			if (base != null) {
				if (baseListener == null) {
					baseListener = new AdapterImpl() {
						public void notifyChanged(Notification msg) {
							switch (msg.getFeatureID(DescribableElement.class)) {
							case UmaPackage.DESCRIBABLE_ELEMENT__PRESENTATION_NAME:
								DescribableElement e = (DescribableElement) getObject();
								if (StrUtil.isBlank(e.getPresentationName())) {
									String oldName = name;
									name = msg.getNewStringValue();
									if (!name.equals(oldName)) {
									eNotify(new ENotificationImpl(
											NamedNodeImpl.this, -1,
											ModelPackage.NAMED_NODE__NAME,
											oldName, name));
									}
								}
								break;
							}
						}
					};
				}
				base.eAdapters().add(baseListener);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.diagram.model.impl.NodeImpl#dispose()
	 */
	protected void dispose() {
		EObject obj = (EObject) getObject();
		if (obj != null) {
			if (baseListener != null) {
				obj.eAdapters().remove(baseListener);
			}
		}

		super.dispose();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.diagram.model.impl.NodeImpl#createNodeAdapter()
	 */
	protected Adapter createNodeAdapter() {
		return new NodeAdapter() {
			public void notifyChanged(Notification msg) {
				if (!notificationEnabled)
					return;
				notificationEnabled = false;
				try {
					switch (msg.getFeatureID(NamedNode.class)) {
					case ModelPackage.NAMED_NODE__NAME:
						if (msg.getEventType() == Notification.SET
								&& getObject() instanceof BreakdownElement) {
							String newName = msg.getNewStringValue();
							BreakdownElement e = ((BreakdownElement) getObject());
							e.setPresentationName(newName);
							if (StrUtil.isBlank(e.getName())) {
								e.setName(newName);
							}
						}
						return;
					}
					super.notifyChanged(msg);
				} finally {
					notificationEnabled = true;
				}

			}
		};

	}

	public boolean isNotificationEnabled() {
		return notificationEnabled;
	}

	public void setNotificationEnabled(boolean notificationEnabled) {
		this.notificationEnabled = notificationEnabled;
	}

	public boolean isSuppressed() {
		Suppression suppression = getDiagram().getSuppression();
		if (suppression != null) {
			Object o = itemProvider != null ? itemProvider : getLinkedElement();
			return getDiagram().getSuppression().isSuppressed(o);
		}
		return false;
	}
	public Object getWrapper() {
		return itemProvider;
	}

	/**
	 * Sets the item provider for the this node if there is a read-only wrapper
	 * for the node in the given element collection
	 * 
	 * @param node
	 * @param allElements
	 */
	void setItemProvider(Collection allElements) {
		Object object = GraphicalDataHelper.findElement(allElements,
				getObject());
		if (object instanceof BreakdownElementWrapperItemProvider) {
			BreakdownElementWrapperItemProvider wrapper = (BreakdownElementWrapperItemProvider) object;
			if (wrapper.isReadOnly()) {
				itemProvider = wrapper;
				readOnly = true;
			}
		}
	}

	private AdapterImpl baseListener;

	/**
	 * Item provider of the linked object
	 */
	protected BreakdownElementWrapperItemProvider itemProvider;

} // NamedNodeImpl
