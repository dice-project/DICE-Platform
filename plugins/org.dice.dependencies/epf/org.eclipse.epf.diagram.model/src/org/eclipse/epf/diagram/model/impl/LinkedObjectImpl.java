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
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.epf.diagram.model.LinkedObject;
import org.eclipse.epf.diagram.model.ModelPackage;


import org.eclipse.epf.uma.MethodElement;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Linked Object</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.diagram.model.impl.LinkedObjectImpl#getLinkedElement <em>Linked Element</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class LinkedObjectImpl extends EObjectImpl implements
		LinkedObject {
	/**
	 * The cached value of the '{@link #getLinkedElement() <em>Linked Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLinkedElement()
	 * @generated
	 * @ordered
	 */
	protected MethodElement linkedElement;
	protected Object object;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected LinkedObjectImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.LINKED_OBJECT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MethodElement getLinkedElement() {
		if (linkedElement != null && ((EObject)linkedElement).eIsProxy()) {
			InternalEObject oldLinkedElement = (InternalEObject)linkedElement;
			linkedElement = (MethodElement)eResolveProxy(oldLinkedElement);
			if (linkedElement != oldLinkedElement) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelPackage.LINKED_OBJECT__LINKED_ELEMENT, oldLinkedElement, linkedElement));
			}
		}
		return linkedElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MethodElement basicGetLinkedElement() {
		return linkedElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLinkedElement(MethodElement newLinkedElement) {
		MethodElement oldLinkedElement = linkedElement;
		linkedElement = newLinkedElement;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.LINKED_OBJECT__LINKED_ELEMENT, oldLinkedElement, linkedElement));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelPackage.LINKED_OBJECT__LINKED_ELEMENT:
				if (resolve) return getLinkedElement();
				return basicGetLinkedElement();
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
			case ModelPackage.LINKED_OBJECT__LINKED_ELEMENT:
				setLinkedElement((MethodElement)newValue);
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
			case ModelPackage.LINKED_OBJECT__LINKED_ELEMENT:
				setLinkedElement((MethodElement)null);
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
			case ModelPackage.LINKED_OBJECT__LINKED_ELEMENT:
				return linkedElement != null;
		}
		return super.eIsSet(featureID);
	}
	
	public Object getObject() {
		return object;
	}
	
	public void setObject(Object newObject) {
		this.object = newObject;
	}

} // LinkedObjectImpl
