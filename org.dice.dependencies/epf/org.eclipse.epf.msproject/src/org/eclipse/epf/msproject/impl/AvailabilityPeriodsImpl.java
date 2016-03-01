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

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.epf.msproject.AvailabilityPeriod;
import org.eclipse.epf.msproject.AvailabilityPeriods;
import org.eclipse.epf.msproject.MsprojectPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Availability Periods</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.msproject.impl.AvailabilityPeriodsImpl#getAvailabilityPeriod <em>Availability Period</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AvailabilityPeriodsImpl extends EObjectImpl implements AvailabilityPeriods {
	/**
	 * The cached value of the '{@link #getAvailabilityPeriod() <em>Availability Period</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAvailabilityPeriod()
	 * @generated
	 * @ordered
	 */
	protected EList availabilityPeriod = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AvailabilityPeriodsImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return MsprojectPackage.eINSTANCE.getAvailabilityPeriods();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getAvailabilityPeriod() {
		if (availabilityPeriod == null) {
			availabilityPeriod = new EObjectContainmentEList(AvailabilityPeriod.class, this, MsprojectPackage.AVAILABILITY_PERIODS__AVAILABILITY_PERIOD);
		}
		return availabilityPeriod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case MsprojectPackage.AVAILABILITY_PERIODS__AVAILABILITY_PERIOD:
					return ((InternalEList)getAvailabilityPeriod()).basicRemove(otherEnd, msgs);
				default:
					return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
			}
		}
		return eBasicSetContainer(null, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case MsprojectPackage.AVAILABILITY_PERIODS__AVAILABILITY_PERIOD:
				return getAvailabilityPeriod();
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
			case MsprojectPackage.AVAILABILITY_PERIODS__AVAILABILITY_PERIOD:
				getAvailabilityPeriod().clear();
				getAvailabilityPeriod().addAll((Collection)newValue);
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
			case MsprojectPackage.AVAILABILITY_PERIODS__AVAILABILITY_PERIOD:
				getAvailabilityPeriod().clear();
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
			case MsprojectPackage.AVAILABILITY_PERIODS__AVAILABILITY_PERIOD:
				return availabilityPeriod != null && !availabilityPeriod.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

} //AvailabilityPeriodsImpl
