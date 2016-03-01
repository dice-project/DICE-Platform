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

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Connection</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.impl.ConnectionImpl#getStart <em>Start</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.impl.ConnectionImpl#getEnd <em>End</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.impl.ConnectionImpl#getParent <em>Parent</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.impl.ConnectionImpl#getConnectionDecorators <em>Connection Decorators</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConnectionImpl extends AnchorContainerImpl implements Connection {
	/**
	 * The cached value of the '{@link #getStart() <em>Start</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStart()
	 * @generated
	 * @ordered
	 */
	protected Anchor start;

	/**
	 * The cached value of the '{@link #getEnd() <em>End</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnd()
	 * @generated
	 * @ordered
	 */
	protected Anchor end;

	/**
	 * The cached value of the '{@link #getConnectionDecorators() <em>Connection Decorators</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnectionDecorators()
	 * @generated
	 * @ordered
	 */
	protected EList<ConnectionDecorator> connectionDecorators;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConnectionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PictogramsPackage.Literals.CONNECTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Anchor getStart() {
		if (start != null && start.eIsProxy()) {
			InternalEObject oldStart = (InternalEObject)start;
			start = (Anchor)eResolveProxy(oldStart);
			if (start != oldStart) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PictogramsPackage.CONNECTION__START, oldStart, start));
			}
		}
		return start;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Anchor basicGetStart() {
		return start;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStart(Anchor newStart, NotificationChain msgs) {
		Anchor oldStart = start;
		start = newStart;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PictogramsPackage.CONNECTION__START, oldStart, newStart);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStart(Anchor newStart) {
		if (newStart != start) {
			NotificationChain msgs = null;
			if (start != null)
				msgs = ((InternalEObject)start).eInverseRemove(this, PictogramsPackage.ANCHOR__OUTGOING_CONNECTIONS, Anchor.class, msgs);
			if (newStart != null)
				msgs = ((InternalEObject)newStart).eInverseAdd(this, PictogramsPackage.ANCHOR__OUTGOING_CONNECTIONS, Anchor.class, msgs);
			msgs = basicSetStart(newStart, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PictogramsPackage.CONNECTION__START, newStart, newStart));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Anchor getEnd() {
		if (end != null && end.eIsProxy()) {
			InternalEObject oldEnd = (InternalEObject)end;
			end = (Anchor)eResolveProxy(oldEnd);
			if (end != oldEnd) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PictogramsPackage.CONNECTION__END, oldEnd, end));
			}
		}
		return end;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Anchor basicGetEnd() {
		return end;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEnd(Anchor newEnd, NotificationChain msgs) {
		Anchor oldEnd = end;
		end = newEnd;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PictogramsPackage.CONNECTION__END, oldEnd, newEnd);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEnd(Anchor newEnd) {
		if (newEnd != end) {
			NotificationChain msgs = null;
			if (end != null)
				msgs = ((InternalEObject)end).eInverseRemove(this, PictogramsPackage.ANCHOR__INCOMING_CONNECTIONS, Anchor.class, msgs);
			if (newEnd != null)
				msgs = ((InternalEObject)newEnd).eInverseAdd(this, PictogramsPackage.ANCHOR__INCOMING_CONNECTIONS, Anchor.class, msgs);
			msgs = basicSetEnd(newEnd, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PictogramsPackage.CONNECTION__END, newEnd, newEnd));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Diagram getParent() {
		if (eContainerFeatureID() != PictogramsPackage.CONNECTION__PARENT) return null;
		return (Diagram)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Diagram basicGetParent() {
		if (eContainerFeatureID() != PictogramsPackage.CONNECTION__PARENT) return null;
		return (Diagram)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetParent(Diagram newParent, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newParent, PictogramsPackage.CONNECTION__PARENT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParent(Diagram newParent) {
		if (newParent != eInternalContainer() || (eContainerFeatureID() != PictogramsPackage.CONNECTION__PARENT && newParent != null)) {
			if (EcoreUtil.isAncestor(this, newParent))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newParent != null)
				msgs = ((InternalEObject)newParent).eInverseAdd(this, PictogramsPackage.DIAGRAM__CONNECTIONS, Diagram.class, msgs);
			msgs = basicSetParent(newParent, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PictogramsPackage.CONNECTION__PARENT, newParent, newParent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ConnectionDecorator> getConnectionDecorators() {
		if (connectionDecorators == null) {
			connectionDecorators = new EObjectContainmentWithInverseEList.Resolving<ConnectionDecorator>(ConnectionDecorator.class, this, PictogramsPackage.CONNECTION__CONNECTION_DECORATORS, PictogramsPackage.CONNECTION_DECORATOR__CONNECTION);
		}
		return connectionDecorators;
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
			case PictogramsPackage.CONNECTION__START:
				if (start != null)
					msgs = ((InternalEObject)start).eInverseRemove(this, PictogramsPackage.ANCHOR__OUTGOING_CONNECTIONS, Anchor.class, msgs);
				return basicSetStart((Anchor)otherEnd, msgs);
			case PictogramsPackage.CONNECTION__END:
				if (end != null)
					msgs = ((InternalEObject)end).eInverseRemove(this, PictogramsPackage.ANCHOR__INCOMING_CONNECTIONS, Anchor.class, msgs);
				return basicSetEnd((Anchor)otherEnd, msgs);
			case PictogramsPackage.CONNECTION__PARENT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetParent((Diagram)otherEnd, msgs);
			case PictogramsPackage.CONNECTION__CONNECTION_DECORATORS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getConnectionDecorators()).basicAdd(otherEnd, msgs);
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
			case PictogramsPackage.CONNECTION__START:
				return basicSetStart(null, msgs);
			case PictogramsPackage.CONNECTION__END:
				return basicSetEnd(null, msgs);
			case PictogramsPackage.CONNECTION__PARENT:
				return basicSetParent(null, msgs);
			case PictogramsPackage.CONNECTION__CONNECTION_DECORATORS:
				return ((InternalEList<?>)getConnectionDecorators()).basicRemove(otherEnd, msgs);
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
			case PictogramsPackage.CONNECTION__PARENT:
				return eInternalContainer().eInverseRemove(this, PictogramsPackage.DIAGRAM__CONNECTIONS, Diagram.class, msgs);
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
			case PictogramsPackage.CONNECTION__START:
				if (resolve) return getStart();
				return basicGetStart();
			case PictogramsPackage.CONNECTION__END:
				if (resolve) return getEnd();
				return basicGetEnd();
			case PictogramsPackage.CONNECTION__PARENT:
				if (resolve) return getParent();
				return basicGetParent();
			case PictogramsPackage.CONNECTION__CONNECTION_DECORATORS:
				return getConnectionDecorators();
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
			case PictogramsPackage.CONNECTION__START:
				setStart((Anchor)newValue);
				return;
			case PictogramsPackage.CONNECTION__END:
				setEnd((Anchor)newValue);
				return;
			case PictogramsPackage.CONNECTION__PARENT:
				setParent((Diagram)newValue);
				return;
			case PictogramsPackage.CONNECTION__CONNECTION_DECORATORS:
				getConnectionDecorators().clear();
				getConnectionDecorators().addAll((Collection<? extends ConnectionDecorator>)newValue);
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
			case PictogramsPackage.CONNECTION__START:
				setStart((Anchor)null);
				return;
			case PictogramsPackage.CONNECTION__END:
				setEnd((Anchor)null);
				return;
			case PictogramsPackage.CONNECTION__PARENT:
				setParent((Diagram)null);
				return;
			case PictogramsPackage.CONNECTION__CONNECTION_DECORATORS:
				getConnectionDecorators().clear();
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
			case PictogramsPackage.CONNECTION__START:
				return start != null;
			case PictogramsPackage.CONNECTION__END:
				return end != null;
			case PictogramsPackage.CONNECTION__PARENT:
				return basicGetParent() != null;
			case PictogramsPackage.CONNECTION__CONNECTION_DECORATORS:
				return connectionDecorators != null && !connectionDecorators.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ConnectionImpl
