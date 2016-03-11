/**
 * <copyright>
 * 
 * Copyright (c) 2005, 2010 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 * 
 * </copyright>
 */
package org.eclipse.graphiti.mm.pictograms.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;

import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.AnchorContainer;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.PictogramsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Anchor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.impl.AnchorImpl#getParent <em>Parent</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.impl.AnchorImpl#getOutgoingConnections <em>Outgoing Connections</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.impl.AnchorImpl#getIncomingConnections <em>Incoming Connections</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.impl.AnchorImpl#getReferencedGraphicsAlgorithm <em>Referenced Graphics Algorithm</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class AnchorImpl extends PictogramElementImpl implements Anchor {
	/**
	 * The cached value of the '{@link #getOutgoingConnections() <em>Outgoing Connections</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutgoingConnections()
	 * @generated
	 * @ordered
	 */
	protected EList<Connection> outgoingConnections;

	/**
	 * The cached value of the '{@link #getIncomingConnections() <em>Incoming Connections</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIncomingConnections()
	 * @generated
	 * @ordered
	 */
	protected EList<Connection> incomingConnections;

	/**
	 * The cached value of the '{@link #getReferencedGraphicsAlgorithm() <em>Referenced Graphics Algorithm</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferencedGraphicsAlgorithm()
	 * @generated
	 * @ordered
	 */
	protected GraphicsAlgorithm referencedGraphicsAlgorithm;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AnchorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PictogramsPackage.Literals.ANCHOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AnchorContainer getParent() {
		if (eContainerFeatureID() != PictogramsPackage.ANCHOR__PARENT) return null;
		return (AnchorContainer)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AnchorContainer basicGetParent() {
		if (eContainerFeatureID() != PictogramsPackage.ANCHOR__PARENT) return null;
		return (AnchorContainer)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetParent(AnchorContainer newParent, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newParent, PictogramsPackage.ANCHOR__PARENT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParent(AnchorContainer newParent) {
		if (newParent != eInternalContainer() || (eContainerFeatureID() != PictogramsPackage.ANCHOR__PARENT && newParent != null)) {
			if (EcoreUtil.isAncestor(this, newParent))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newParent != null)
				msgs = ((InternalEObject)newParent).eInverseAdd(this, PictogramsPackage.ANCHOR_CONTAINER__ANCHORS, AnchorContainer.class, msgs);
			msgs = basicSetParent(newParent, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PictogramsPackage.ANCHOR__PARENT, newParent, newParent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Connection> getOutgoingConnections() {
		if (outgoingConnections == null) {
			outgoingConnections = new EObjectWithInverseResolvingEList<Connection>(Connection.class, this, PictogramsPackage.ANCHOR__OUTGOING_CONNECTIONS, PictogramsPackage.CONNECTION__START);
		}
		return outgoingConnections;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Connection> getIncomingConnections() {
		if (incomingConnections == null) {
			incomingConnections = new EObjectWithInverseResolvingEList<Connection>(Connection.class, this, PictogramsPackage.ANCHOR__INCOMING_CONNECTIONS, PictogramsPackage.CONNECTION__END);
		}
		return incomingConnections;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GraphicsAlgorithm getReferencedGraphicsAlgorithm() {
		if (referencedGraphicsAlgorithm != null && referencedGraphicsAlgorithm.eIsProxy()) {
			InternalEObject oldReferencedGraphicsAlgorithm = (InternalEObject)referencedGraphicsAlgorithm;
			referencedGraphicsAlgorithm = (GraphicsAlgorithm)eResolveProxy(oldReferencedGraphicsAlgorithm);
			if (referencedGraphicsAlgorithm != oldReferencedGraphicsAlgorithm) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PictogramsPackage.ANCHOR__REFERENCED_GRAPHICS_ALGORITHM, oldReferencedGraphicsAlgorithm, referencedGraphicsAlgorithm));
			}
		}
		return referencedGraphicsAlgorithm;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GraphicsAlgorithm basicGetReferencedGraphicsAlgorithm() {
		return referencedGraphicsAlgorithm;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReferencedGraphicsAlgorithm(GraphicsAlgorithm newReferencedGraphicsAlgorithm) {
		GraphicsAlgorithm oldReferencedGraphicsAlgorithm = referencedGraphicsAlgorithm;
		referencedGraphicsAlgorithm = newReferencedGraphicsAlgorithm;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PictogramsPackage.ANCHOR__REFERENCED_GRAPHICS_ALGORITHM, oldReferencedGraphicsAlgorithm, referencedGraphicsAlgorithm));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PictogramsPackage.ANCHOR__PARENT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetParent((AnchorContainer)otherEnd, msgs);
			case PictogramsPackage.ANCHOR__OUTGOING_CONNECTIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOutgoingConnections()).basicAdd(otherEnd, msgs);
			case PictogramsPackage.ANCHOR__INCOMING_CONNECTIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getIncomingConnections()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PictogramsPackage.ANCHOR__PARENT:
				return basicSetParent(null, msgs);
			case PictogramsPackage.ANCHOR__OUTGOING_CONNECTIONS:
				return ((InternalEList<?>)getOutgoingConnections()).basicRemove(otherEnd, msgs);
			case PictogramsPackage.ANCHOR__INCOMING_CONNECTIONS:
				return ((InternalEList<?>)getIncomingConnections()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case PictogramsPackage.ANCHOR__PARENT:
				return eInternalContainer().eInverseRemove(this, PictogramsPackage.ANCHOR_CONTAINER__ANCHORS, AnchorContainer.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PictogramsPackage.ANCHOR__PARENT:
				if (resolve) return getParent();
				return basicGetParent();
			case PictogramsPackage.ANCHOR__OUTGOING_CONNECTIONS:
				return getOutgoingConnections();
			case PictogramsPackage.ANCHOR__INCOMING_CONNECTIONS:
				return getIncomingConnections();
			case PictogramsPackage.ANCHOR__REFERENCED_GRAPHICS_ALGORITHM:
				if (resolve) return getReferencedGraphicsAlgorithm();
				return basicGetReferencedGraphicsAlgorithm();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case PictogramsPackage.ANCHOR__PARENT:
				setParent((AnchorContainer)newValue);
				return;
			case PictogramsPackage.ANCHOR__OUTGOING_CONNECTIONS:
				getOutgoingConnections().clear();
				getOutgoingConnections().addAll((Collection<? extends Connection>)newValue);
				return;
			case PictogramsPackage.ANCHOR__INCOMING_CONNECTIONS:
				getIncomingConnections().clear();
				getIncomingConnections().addAll((Collection<? extends Connection>)newValue);
				return;
			case PictogramsPackage.ANCHOR__REFERENCED_GRAPHICS_ALGORITHM:
				setReferencedGraphicsAlgorithm((GraphicsAlgorithm)newValue);
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
			case PictogramsPackage.ANCHOR__PARENT:
				setParent((AnchorContainer)null);
				return;
			case PictogramsPackage.ANCHOR__OUTGOING_CONNECTIONS:
				getOutgoingConnections().clear();
				return;
			case PictogramsPackage.ANCHOR__INCOMING_CONNECTIONS:
				getIncomingConnections().clear();
				return;
			case PictogramsPackage.ANCHOR__REFERENCED_GRAPHICS_ALGORITHM:
				setReferencedGraphicsAlgorithm((GraphicsAlgorithm)null);
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
			case PictogramsPackage.ANCHOR__PARENT:
				return basicGetParent() != null;
			case PictogramsPackage.ANCHOR__OUTGOING_CONNECTIONS:
				return outgoingConnections != null && !outgoingConnections.isEmpty();
			case PictogramsPackage.ANCHOR__INCOMING_CONNECTIONS:
				return incomingConnections != null && !incomingConnections.isEmpty();
			case PictogramsPackage.ANCHOR__REFERENCED_GRAPHICS_ALGORITHM:
				return referencedGraphicsAlgorithm != null;
		}
		return super.eIsSet(featureID);
	}

} //AnchorImpl
