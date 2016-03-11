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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.epf.msproject.AvailabilityPeriod;
import org.eclipse.epf.msproject.MsprojectPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Availability Period</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.msproject.impl.AvailabilityPeriodImpl#getAvailableFrom <em>Available From</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.AvailabilityPeriodImpl#getAvailableTo <em>Available To</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.AvailabilityPeriodImpl#getAvailableUnits <em>Available Units</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AvailabilityPeriodImpl extends EObjectImpl implements AvailabilityPeriod {
	/**
	 * The default value of the '{@link #getAvailableFrom() <em>Available From</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAvailableFrom()
	 * @generated
	 * @ordered
	 */
	protected static final Object AVAILABLE_FROM_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAvailableFrom() <em>Available From</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAvailableFrom()
	 * @generated
	 * @ordered
	 */
	protected Object availableFrom = AVAILABLE_FROM_EDEFAULT;

	/**
	 * The default value of the '{@link #getAvailableTo() <em>Available To</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAvailableTo()
	 * @generated
	 * @ordered
	 */
	protected static final Object AVAILABLE_TO_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAvailableTo() <em>Available To</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAvailableTo()
	 * @generated
	 * @ordered
	 */
	protected Object availableTo = AVAILABLE_TO_EDEFAULT;

	/**
	 * The default value of the '{@link #getAvailableUnits() <em>Available Units</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAvailableUnits()
	 * @generated
	 * @ordered
	 */
	protected static final float AVAILABLE_UNITS_EDEFAULT = 0.0F;

	/**
	 * The cached value of the '{@link #getAvailableUnits() <em>Available Units</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAvailableUnits()
	 * @generated
	 * @ordered
	 */
	protected float availableUnits = AVAILABLE_UNITS_EDEFAULT;

	/**
	 * This is true if the Available Units attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean availableUnitsESet = false;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AvailabilityPeriodImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return MsprojectPackage.eINSTANCE.getAvailabilityPeriod();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getAvailableFrom() {
		return availableFrom;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAvailableFrom(Object newAvailableFrom) {
		Object oldAvailableFrom = availableFrom;
		availableFrom = newAvailableFrom;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.AVAILABILITY_PERIOD__AVAILABLE_FROM, oldAvailableFrom, availableFrom));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getAvailableTo() {
		return availableTo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAvailableTo(Object newAvailableTo) {
		Object oldAvailableTo = availableTo;
		availableTo = newAvailableTo;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.AVAILABILITY_PERIOD__AVAILABLE_TO, oldAvailableTo, availableTo));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getAvailableUnits() {
		return availableUnits;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAvailableUnits(float newAvailableUnits) {
		float oldAvailableUnits = availableUnits;
		availableUnits = newAvailableUnits;
		boolean oldAvailableUnitsESet = availableUnitsESet;
		availableUnitsESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.AVAILABILITY_PERIOD__AVAILABLE_UNITS, oldAvailableUnits, availableUnits, !oldAvailableUnitsESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetAvailableUnits() {
		float oldAvailableUnits = availableUnits;
		boolean oldAvailableUnitsESet = availableUnitsESet;
		availableUnits = AVAILABLE_UNITS_EDEFAULT;
		availableUnitsESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.AVAILABILITY_PERIOD__AVAILABLE_UNITS, oldAvailableUnits, AVAILABLE_UNITS_EDEFAULT, oldAvailableUnitsESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetAvailableUnits() {
		return availableUnitsESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case MsprojectPackage.AVAILABILITY_PERIOD__AVAILABLE_FROM:
				return getAvailableFrom();
			case MsprojectPackage.AVAILABILITY_PERIOD__AVAILABLE_TO:
				return getAvailableTo();
			case MsprojectPackage.AVAILABILITY_PERIOD__AVAILABLE_UNITS:
				return new Float(getAvailableUnits());
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
			case MsprojectPackage.AVAILABILITY_PERIOD__AVAILABLE_FROM:
				setAvailableFrom((Object)newValue);
				return;
			case MsprojectPackage.AVAILABILITY_PERIOD__AVAILABLE_TO:
				setAvailableTo((Object)newValue);
				return;
			case MsprojectPackage.AVAILABILITY_PERIOD__AVAILABLE_UNITS:
				setAvailableUnits(((Float)newValue).floatValue());
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
			case MsprojectPackage.AVAILABILITY_PERIOD__AVAILABLE_FROM:
				setAvailableFrom(AVAILABLE_FROM_EDEFAULT);
				return;
			case MsprojectPackage.AVAILABILITY_PERIOD__AVAILABLE_TO:
				setAvailableTo(AVAILABLE_TO_EDEFAULT);
				return;
			case MsprojectPackage.AVAILABILITY_PERIOD__AVAILABLE_UNITS:
				unsetAvailableUnits();
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
			case MsprojectPackage.AVAILABILITY_PERIOD__AVAILABLE_FROM:
				return AVAILABLE_FROM_EDEFAULT == null ? availableFrom != null : !AVAILABLE_FROM_EDEFAULT.equals(availableFrom);
			case MsprojectPackage.AVAILABILITY_PERIOD__AVAILABLE_TO:
				return AVAILABLE_TO_EDEFAULT == null ? availableTo != null : !AVAILABLE_TO_EDEFAULT.equals(availableTo);
			case MsprojectPackage.AVAILABILITY_PERIOD__AVAILABLE_UNITS:
				return isSetAvailableUnits();
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
		result.append(" (availableFrom: ");
		result.append(availableFrom);
		result.append(", availableTo: ");
		result.append(availableTo);
		result.append(", availableUnits: ");
		if (availableUnitsESet) result.append(availableUnits); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //AvailabilityPeriodImpl
