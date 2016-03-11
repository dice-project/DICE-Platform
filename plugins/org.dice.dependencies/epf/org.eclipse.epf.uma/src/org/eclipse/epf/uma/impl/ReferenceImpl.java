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
package org.eclipse.epf.uma.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.epf.uma.DiagramElement;
import org.eclipse.epf.uma.Reference;
import org.eclipse.epf.uma.UmaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Reference</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.impl.ReferenceImpl#getIsIndividualRepresentation <em>Is Individual Representation</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.ReferenceImpl#getReferenced <em>Referenced</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ReferenceImpl extends DiagramElementImpl implements Reference {
	/**
	 * The default value of the '{@link #getIsIndividualRepresentation() <em>Is Individual Representation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsIndividualRepresentation()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_INDIVIDUAL_REPRESENTATION_EDEFAULT = Boolean.FALSE;

	/**
	 * The cached value of the '{@link #getIsIndividualRepresentation() <em>Is Individual Representation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsIndividualRepresentation()
	 * @generated
	 * @ordered
	 */
	protected Boolean isIndividualRepresentation = IS_INDIVIDUAL_REPRESENTATION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getReferenced() <em>Referenced</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferenced()
	 * @generated
	 * @ordered
	 */
	protected DiagramElement referenced;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ReferenceImpl() {
		super();

		//UMA-->
		reassignDefaultValues();
		//UMA<--  
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UmaPackage.Literals.REFERENCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getIsIndividualRepresentation() {
		return isIndividualRepresentation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsIndividualRepresentation(
			Boolean newIsIndividualRepresentation) {
		Boolean oldIsIndividualRepresentation = isIndividualRepresentation;
		isIndividualRepresentation = newIsIndividualRepresentation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.REFERENCE__IS_INDIVIDUAL_REPRESENTATION,
					oldIsIndividualRepresentation, isIndividualRepresentation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DiagramElement getReferenced() {
		if (referenced != null && ((EObject) referenced).eIsProxy()) {
			InternalEObject oldReferenced = (InternalEObject) referenced;
			referenced = (DiagramElement) eResolveProxy(oldReferenced);
			if (referenced != oldReferenced) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							UmaPackage.REFERENCE__REFERENCED, oldReferenced,
							referenced));
			}
		}
		return referenced;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DiagramElement basicGetReferenced() {
		return referenced;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetReferenced(DiagramElement newReferenced,
			NotificationChain msgs) {
		DiagramElement oldReferenced = referenced;
		referenced = newReferenced;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
					Notification.SET, UmaPackage.REFERENCE__REFERENCED,
					oldReferenced, newReferenced);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReferenced(DiagramElement newReferenced) {
		if (newReferenced != referenced) {
			NotificationChain msgs = null;
			if (referenced != null)
				msgs = ((InternalEObject) referenced).eInverseRemove(this,
						UmaPackage.DIAGRAM_ELEMENT__REFERENCE,
						DiagramElement.class, msgs);
			if (newReferenced != null)
				msgs = ((InternalEObject) newReferenced).eInverseAdd(this,
						UmaPackage.DIAGRAM_ELEMENT__REFERENCE,
						DiagramElement.class, msgs);
			msgs = basicSetReferenced(newReferenced, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.REFERENCE__REFERENCED, newReferenced,
					newReferenced));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
		case UmaPackage.REFERENCE__REFERENCED:
			if (referenced != null)
				msgs = ((InternalEObject) referenced).eInverseRemove(this,
						UmaPackage.DIAGRAM_ELEMENT__REFERENCE,
						DiagramElement.class, msgs);
			return basicSetReferenced((DiagramElement) otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
		case UmaPackage.REFERENCE__REFERENCED:
			return basicSetReferenced(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case UmaPackage.REFERENCE__IS_INDIVIDUAL_REPRESENTATION:
			return getIsIndividualRepresentation();
		case UmaPackage.REFERENCE__REFERENCED:
			if (resolve)
				return getReferenced();
			return basicGetReferenced();
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
		case UmaPackage.REFERENCE__IS_INDIVIDUAL_REPRESENTATION:
			setIsIndividualRepresentation((Boolean) newValue);
			return;
		case UmaPackage.REFERENCE__REFERENCED:
			setReferenced((DiagramElement) newValue);
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
		case UmaPackage.REFERENCE__IS_INDIVIDUAL_REPRESENTATION:
			setIsIndividualRepresentation(IS_INDIVIDUAL_REPRESENTATION_EDEFAULT);
			return;
		case UmaPackage.REFERENCE__REFERENCED:
			setReferenced((DiagramElement) null);
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
		//UMA-->
		EStructuralFeature feature = getFeatureWithOverridenDefaultValue(featureID);
		if (feature != null) {
			return isFeatureWithOverridenDefaultValueSet(feature);
		}
		//UMA<--		
		switch (featureID) {
		case UmaPackage.REFERENCE__IS_INDIVIDUAL_REPRESENTATION:
			return IS_INDIVIDUAL_REPRESENTATION_EDEFAULT == null ? isIndividualRepresentation != null
					: !IS_INDIVIDUAL_REPRESENTATION_EDEFAULT
							.equals(isIndividualRepresentation);
		case UmaPackage.REFERENCE__REFERENCED:
			return referenced != null;
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
		if (eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (isIndividualRepresentation: "); //$NON-NLS-1$
		result.append(isIndividualRepresentation);
		result.append(')');
		return result.toString();
	}

} //ReferenceImpl
