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
import org.eclipse.epf.msproject.ExtendedAttribute4;
import org.eclipse.epf.msproject.MsprojectPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Extended Attribute4</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.msproject.impl.ExtendedAttribute4Impl#getUID <em>UID</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ExtendedAttribute4Impl#getFieldID <em>Field ID</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ExtendedAttribute4Impl#getValue <em>Value</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ExtendedAttribute4Impl#getValueID <em>Value ID</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ExtendedAttribute4Impl#getDurationFormat <em>Duration Format</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExtendedAttribute4Impl extends EObjectImpl implements ExtendedAttribute4 {
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
	 * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected static final String VALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected String value = VALUE_EDEFAULT;

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
	 * The default value of the '{@link #getDurationFormat() <em>Duration Format</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDurationFormat()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger DURATION_FORMAT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDurationFormat() <em>Duration Format</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDurationFormat()
	 * @generated
	 * @ordered
	 */
	protected BigInteger durationFormat = DURATION_FORMAT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExtendedAttribute4Impl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return MsprojectPackage.eINSTANCE.getExtendedAttribute4();
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.EXTENDED_ATTRIBUTE4__UID, oldUID, uID));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.EXTENDED_ATTRIBUTE4__FIELD_ID, oldFieldID, fieldID));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getValue() {
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValue(String newValue) {
		String oldValue = value;
		value = newValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.EXTENDED_ATTRIBUTE4__VALUE, oldValue, value));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.EXTENDED_ATTRIBUTE4__VALUE_ID, oldValueID, valueID));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getDurationFormat() {
		return durationFormat;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDurationFormat(BigInteger newDurationFormat) {
		BigInteger oldDurationFormat = durationFormat;
		durationFormat = newDurationFormat;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.EXTENDED_ATTRIBUTE4__DURATION_FORMAT, oldDurationFormat, durationFormat));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case MsprojectPackage.EXTENDED_ATTRIBUTE4__UID:
				return getUID();
			case MsprojectPackage.EXTENDED_ATTRIBUTE4__FIELD_ID:
				return getFieldID();
			case MsprojectPackage.EXTENDED_ATTRIBUTE4__VALUE:
				return getValue();
			case MsprojectPackage.EXTENDED_ATTRIBUTE4__VALUE_ID:
				return getValueID();
			case MsprojectPackage.EXTENDED_ATTRIBUTE4__DURATION_FORMAT:
				return getDurationFormat();
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
			case MsprojectPackage.EXTENDED_ATTRIBUTE4__UID:
				setUID((BigInteger)newValue);
				return;
			case MsprojectPackage.EXTENDED_ATTRIBUTE4__FIELD_ID:
				setFieldID((String)newValue);
				return;
			case MsprojectPackage.EXTENDED_ATTRIBUTE4__VALUE:
				setValue((String)newValue);
				return;
			case MsprojectPackage.EXTENDED_ATTRIBUTE4__VALUE_ID:
				setValueID((BigInteger)newValue);
				return;
			case MsprojectPackage.EXTENDED_ATTRIBUTE4__DURATION_FORMAT:
				setDurationFormat((BigInteger)newValue);
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
			case MsprojectPackage.EXTENDED_ATTRIBUTE4__UID:
				setUID(UID_EDEFAULT);
				return;
			case MsprojectPackage.EXTENDED_ATTRIBUTE4__FIELD_ID:
				setFieldID(FIELD_ID_EDEFAULT);
				return;
			case MsprojectPackage.EXTENDED_ATTRIBUTE4__VALUE:
				setValue(VALUE_EDEFAULT);
				return;
			case MsprojectPackage.EXTENDED_ATTRIBUTE4__VALUE_ID:
				setValueID(VALUE_ID_EDEFAULT);
				return;
			case MsprojectPackage.EXTENDED_ATTRIBUTE4__DURATION_FORMAT:
				setDurationFormat(DURATION_FORMAT_EDEFAULT);
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
			case MsprojectPackage.EXTENDED_ATTRIBUTE4__UID:
				return UID_EDEFAULT == null ? uID != null : !UID_EDEFAULT.equals(uID);
			case MsprojectPackage.EXTENDED_ATTRIBUTE4__FIELD_ID:
				return FIELD_ID_EDEFAULT == null ? fieldID != null : !FIELD_ID_EDEFAULT.equals(fieldID);
			case MsprojectPackage.EXTENDED_ATTRIBUTE4__VALUE:
				return VALUE_EDEFAULT == null ? value != null : !VALUE_EDEFAULT.equals(value);
			case MsprojectPackage.EXTENDED_ATTRIBUTE4__VALUE_ID:
				return VALUE_ID_EDEFAULT == null ? valueID != null : !VALUE_ID_EDEFAULT.equals(valueID);
			case MsprojectPackage.EXTENDED_ATTRIBUTE4__DURATION_FORMAT:
				return DURATION_FORMAT_EDEFAULT == null ? durationFormat != null : !DURATION_FORMAT_EDEFAULT.equals(durationFormat);
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
		result.append(", value: ");
		result.append(value);
		result.append(", valueID: ");
		result.append(valueID);
		result.append(", durationFormat: ");
		result.append(durationFormat);
		result.append(')');
		return result.toString();
	}

} //ExtendedAttribute4Impl
