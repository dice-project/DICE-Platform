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

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.epf.uma.ApplicableMetaClassInfo;
import org.eclipse.epf.uma.UmaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Applicable Meta Class Info</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.impl.ApplicableMetaClassInfoImpl#getIsPrimaryExtension <em>Is Primary Extension</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ApplicableMetaClassInfoImpl extends ClassifierImpl implements
		ApplicableMetaClassInfo {
	/**
	 * The default value of the '{@link #getIsPrimaryExtension() <em>Is Primary Extension</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsPrimaryExtension()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_PRIMARY_EXTENSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIsPrimaryExtension() <em>Is Primary Extension</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsPrimaryExtension()
	 * @generated
	 * @ordered
	 */
	protected Boolean isPrimaryExtension = IS_PRIMARY_EXTENSION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ApplicableMetaClassInfoImpl() {
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
		return UmaPackage.Literals.APPLICABLE_META_CLASS_INFO;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getIsPrimaryExtension() {
		return isPrimaryExtension;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsPrimaryExtension(Boolean newIsPrimaryExtension) {
		Boolean oldIsPrimaryExtension = isPrimaryExtension;
		isPrimaryExtension = newIsPrimaryExtension;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(
					this,
					Notification.SET,
					UmaPackage.APPLICABLE_META_CLASS_INFO__IS_PRIMARY_EXTENSION,
					oldIsPrimaryExtension, isPrimaryExtension));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case UmaPackage.APPLICABLE_META_CLASS_INFO__IS_PRIMARY_EXTENSION:
			return getIsPrimaryExtension();
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
		case UmaPackage.APPLICABLE_META_CLASS_INFO__IS_PRIMARY_EXTENSION:
			setIsPrimaryExtension((Boolean) newValue);
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
		case UmaPackage.APPLICABLE_META_CLASS_INFO__IS_PRIMARY_EXTENSION:
			setIsPrimaryExtension(IS_PRIMARY_EXTENSION_EDEFAULT);
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
		case UmaPackage.APPLICABLE_META_CLASS_INFO__IS_PRIMARY_EXTENSION:
			return IS_PRIMARY_EXTENSION_EDEFAULT == null ? isPrimaryExtension != null
					: !IS_PRIMARY_EXTENSION_EDEFAULT.equals(isPrimaryExtension);
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
		result.append(" (isPrimaryExtension: "); //$NON-NLS-1$
		result.append(isPrimaryExtension);
		result.append(')');
		return result.toString();
	}

} //ApplicableMetaClassInfoImpl
