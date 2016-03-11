/*******************************************************************************
 * Copyright (c) 2005, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial implementation
 *******************************************************************************/
package org.eclipse.epf.msproject.impl;

import java.math.BigInteger;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.epf.msproject.MsprojectPackage;
import org.eclipse.epf.msproject.OutlineCode2;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Outline Code2</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.msproject.impl.OutlineCode2Impl#getUID <em>UID</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.OutlineCode2Impl#getFieldID <em>Field ID</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.OutlineCode2Impl#getValueID <em>Value ID</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OutlineCode2Impl extends EObjectImpl implements OutlineCode2 {
	/**
	 * The default value of the '{@link #getUID() <em>UID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUID()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger UID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUID() <em>UID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUID()
	 * @generated
	 * @ordered
	 */
	protected BigInteger uID = UID_EDEFAULT;

	/**
	 * The default value of the '{@link #getFieldID() <em>Field ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFieldID()
	 * @generated
	 * @ordered
	 */
	protected static final String FIELD_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFieldID() <em>Field ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFieldID()
	 * @generated
	 * @ordered
	 */
	protected String fieldID = FIELD_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getValueID() <em>Value ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValueID()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger VALUE_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getValueID() <em>Value ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValueID()
	 * @generated
	 * @ordered
	 */
	protected BigInteger valueID = VALUE_ID_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OutlineCode2Impl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return MsprojectPackage.eINSTANCE.getOutlineCode2();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getUID() {
		return uID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUID(BigInteger newUID) {
		BigInteger oldUID = uID;
		uID = newUID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.OUTLINE_CODE2__UID, oldUID, uID));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFieldID() {
		return fieldID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFieldID(String newFieldID) {
		String oldFieldID = fieldID;
		fieldID = newFieldID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.OUTLINE_CODE2__FIELD_ID, oldFieldID, fieldID));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getValueID() {
		return valueID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValueID(BigInteger newValueID) {
		BigInteger oldValueID = valueID;
		valueID = newValueID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.OUTLINE_CODE2__VALUE_ID, oldValueID, valueID));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case MsprojectPackage.OUTLINE_CODE2__UID:
				return getUID();
			case MsprojectPackage.OUTLINE_CODE2__FIELD_ID:
				return getFieldID();
			case MsprojectPackage.OUTLINE_CODE2__VALUE_ID:
				return getValueID();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case MsprojectPackage.OUTLINE_CODE2__UID:
				setUID((BigInteger)newValue);
				return;
			case MsprojectPackage.OUTLINE_CODE2__FIELD_ID:
				setFieldID((String)newValue);
				return;
			case MsprojectPackage.OUTLINE_CODE2__VALUE_ID:
				setValueID((BigInteger)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case MsprojectPackage.OUTLINE_CODE2__UID:
				setUID(UID_EDEFAULT);
				return;
			case MsprojectPackage.OUTLINE_CODE2__FIELD_ID:
				setFieldID(FIELD_ID_EDEFAULT);
				return;
			case MsprojectPackage.OUTLINE_CODE2__VALUE_ID:
				setValueID(VALUE_ID_EDEFAULT);
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case MsprojectPackage.OUTLINE_CODE2__UID:
				return UID_EDEFAULT == null ? uID != null : !UID_EDEFAULT.equals(uID);
			case MsprojectPackage.OUTLINE_CODE2__FIELD_ID:
				return FIELD_ID_EDEFAULT == null ? fieldID != null : !FIELD_ID_EDEFAULT.equals(fieldID);
			case MsprojectPackage.OUTLINE_CODE2__VALUE_ID:
				return VALUE_ID_EDEFAULT == null ? valueID != null : !VALUE_ID_EDEFAULT.equals(valueID);
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (uID: ");
		result.append(uID);
		result.append(", fieldID: ");
		result.append(fieldID);
		result.append(", valueID: ");
		result.append(valueID);
		result.append(')');
		return result.toString();
	}

} //OutlineCode2Impl
