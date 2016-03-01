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
package org.eclipse.epf.xml.uma.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.epf.xml.uma.ApplicableMetaClassInfo;
import org.eclipse.epf.xml.uma.UmaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Applicable Meta Class Info</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.ApplicableMetaClassInfoImpl#isIsPrimaryExtension <em>Is Primary Extension</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ApplicableMetaClassInfoImpl extends PackageableElementImpl implements ApplicableMetaClassInfo {
	/**
	 * The default value of the '{@link #isIsPrimaryExtension() <em>Is Primary Extension</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsPrimaryExtension()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_PRIMARY_EXTENSION_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsPrimaryExtension() <em>Is Primary Extension</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsPrimaryExtension()
	 * @generated
	 * @ordered
	 */
	protected boolean isPrimaryExtension = IS_PRIMARY_EXTENSION_EDEFAULT;

	/**
	 * This is true if the Is Primary Extension attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean isPrimaryExtensionESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ApplicableMetaClassInfoImpl() {
		super();
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
	public boolean isIsPrimaryExtension() {
		return isPrimaryExtension;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsPrimaryExtension(boolean newIsPrimaryExtension) {
		boolean oldIsPrimaryExtension = isPrimaryExtension;
		isPrimaryExtension = newIsPrimaryExtension;
		boolean oldIsPrimaryExtensionESet = isPrimaryExtensionESet;
		isPrimaryExtensionESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UmaPackage.APPLICABLE_META_CLASS_INFO__IS_PRIMARY_EXTENSION, oldIsPrimaryExtension, isPrimaryExtension, !oldIsPrimaryExtensionESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetIsPrimaryExtension() {
		boolean oldIsPrimaryExtension = isPrimaryExtension;
		boolean oldIsPrimaryExtensionESet = isPrimaryExtensionESet;
		isPrimaryExtension = IS_PRIMARY_EXTENSION_EDEFAULT;
		isPrimaryExtensionESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, UmaPackage.APPLICABLE_META_CLASS_INFO__IS_PRIMARY_EXTENSION, oldIsPrimaryExtension, IS_PRIMARY_EXTENSION_EDEFAULT, oldIsPrimaryExtensionESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetIsPrimaryExtension() {
		return isPrimaryExtensionESet;
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
				return isIsPrimaryExtension() ? Boolean.TRUE : Boolean.FALSE;
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
				setIsPrimaryExtension(((Boolean)newValue).booleanValue());
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
				unsetIsPrimaryExtension();
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
			case UmaPackage.APPLICABLE_META_CLASS_INFO__IS_PRIMARY_EXTENSION:
				return isSetIsPrimaryExtension();
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
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (isPrimaryExtension: ");
		if (isPrimaryExtensionESet) result.append(isPrimaryExtension); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //ApplicableMetaClassInfoImpl
