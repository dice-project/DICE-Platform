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

import java.util.Collection;
import java.util.List;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import java.util.Set;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.epf.uma.ApplicableMetaClassInfo;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.epf.uma.Kind;
import org.eclipse.epf.uma.UmaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Kind</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.impl.KindImpl#getApplicableMetaClassInfo <em>Applicable Meta Class Info</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class KindImpl extends ContentElementImpl implements Kind {
	/**
	 * The cached value of the '{@link #getApplicableMetaClassInfo() <em>Applicable Meta Class Info</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getApplicableMetaClassInfo()
	 * @generated
	 * @ordered
	 */
	protected EList<ApplicableMetaClassInfo> applicableMetaClassInfo;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected KindImpl() {
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
		return UmaPackage.Literals.KIND;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<ApplicableMetaClassInfo> getApplicableMetaClassInfo() {
		if (applicableMetaClassInfo == null) {
			applicableMetaClassInfo = new EObjectContainmentEList.Resolving<ApplicableMetaClassInfo>(
					ApplicableMetaClassInfo.class, this,
					UmaPackage.KIND__APPLICABLE_META_CLASS_INFO);
		}
		return applicableMetaClassInfo;
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
		case UmaPackage.KIND__APPLICABLE_META_CLASS_INFO:
			return ((InternalEList<?>) getApplicableMetaClassInfo())
					.basicRemove(otherEnd, msgs);
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
		case UmaPackage.KIND__APPLICABLE_META_CLASS_INFO:
			return getApplicableMetaClassInfo();
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
		case UmaPackage.KIND__APPLICABLE_META_CLASS_INFO:
			getApplicableMetaClassInfo().clear();
			getApplicableMetaClassInfo().addAll(
					(Collection<? extends ApplicableMetaClassInfo>) newValue);
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
		case UmaPackage.KIND__APPLICABLE_META_CLASS_INFO:
			getApplicableMetaClassInfo().clear();
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
		case UmaPackage.KIND__APPLICABLE_META_CLASS_INFO:
			return applicableMetaClassInfo != null
					&& !applicableMetaClassInfo.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //KindImpl
