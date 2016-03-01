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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.epf.msproject.MsprojectPackage;
import org.eclipse.epf.msproject.WBSMask;
import org.eclipse.epf.msproject.WBSMasks;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>WBS Masks</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.msproject.impl.WBSMasksImpl#isVerifyUniqueCodes <em>Verify Unique Codes</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.WBSMasksImpl#isGenerateCodes <em>Generate Codes</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.WBSMasksImpl#getPrefix <em>Prefix</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.WBSMasksImpl#getWBSMask <em>WBS Mask</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class WBSMasksImpl extends EObjectImpl implements WBSMasks {
	/**
	 * The default value of the '{@link #isVerifyUniqueCodes() <em>Verify Unique Codes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isVerifyUniqueCodes()
	 * @generated
	 * @ordered
	 */
	protected static final boolean VERIFY_UNIQUE_CODES_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isVerifyUniqueCodes() <em>Verify Unique Codes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isVerifyUniqueCodes()
	 * @generated
	 * @ordered
	 */
	protected boolean verifyUniqueCodes = VERIFY_UNIQUE_CODES_EDEFAULT;

	/**
	 * This is true if the Verify Unique Codes attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean verifyUniqueCodesESet = false;

	/**
	 * The default value of the '{@link #isGenerateCodes() <em>Generate Codes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isGenerateCodes()
	 * @generated
	 * @ordered
	 */
	protected static final boolean GENERATE_CODES_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isGenerateCodes() <em>Generate Codes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isGenerateCodes()
	 * @generated
	 * @ordered
	 */
	protected boolean generateCodes = GENERATE_CODES_EDEFAULT;

	/**
	 * This is true if the Generate Codes attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean generateCodesESet = false;

	/**
	 * The default value of the '{@link #getPrefix() <em>Prefix</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrefix()
	 * @generated
	 * @ordered
	 */
	protected static final String PREFIX_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPrefix() <em>Prefix</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrefix()
	 * @generated
	 * @ordered
	 */
	protected String prefix = PREFIX_EDEFAULT;

	/**
	 * The cached value of the '{@link #getWBSMask() <em>WBS Mask</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWBSMask()
	 * @generated
	 * @ordered
	 */
	protected EList wBSMask = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WBSMasksImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return MsprojectPackage.eINSTANCE.getWBSMasks();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isVerifyUniqueCodes() {
		return verifyUniqueCodes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVerifyUniqueCodes(boolean newVerifyUniqueCodes) {
		boolean oldVerifyUniqueCodes = verifyUniqueCodes;
		verifyUniqueCodes = newVerifyUniqueCodes;
		boolean oldVerifyUniqueCodesESet = verifyUniqueCodesESet;
		verifyUniqueCodesESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.WBS_MASKS__VERIFY_UNIQUE_CODES, oldVerifyUniqueCodes, verifyUniqueCodes, !oldVerifyUniqueCodesESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetVerifyUniqueCodes() {
		boolean oldVerifyUniqueCodes = verifyUniqueCodes;
		boolean oldVerifyUniqueCodesESet = verifyUniqueCodesESet;
		verifyUniqueCodes = VERIFY_UNIQUE_CODES_EDEFAULT;
		verifyUniqueCodesESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.WBS_MASKS__VERIFY_UNIQUE_CODES, oldVerifyUniqueCodes, VERIFY_UNIQUE_CODES_EDEFAULT, oldVerifyUniqueCodesESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetVerifyUniqueCodes() {
		return verifyUniqueCodesESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isGenerateCodes() {
		return generateCodes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGenerateCodes(boolean newGenerateCodes) {
		boolean oldGenerateCodes = generateCodes;
		generateCodes = newGenerateCodes;
		boolean oldGenerateCodesESet = generateCodesESet;
		generateCodesESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.WBS_MASKS__GENERATE_CODES, oldGenerateCodes, generateCodes, !oldGenerateCodesESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetGenerateCodes() {
		boolean oldGenerateCodes = generateCodes;
		boolean oldGenerateCodesESet = generateCodesESet;
		generateCodes = GENERATE_CODES_EDEFAULT;
		generateCodesESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.WBS_MASKS__GENERATE_CODES, oldGenerateCodes, GENERATE_CODES_EDEFAULT, oldGenerateCodesESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetGenerateCodes() {
		return generateCodesESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPrefix() {
		return prefix;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPrefix(String newPrefix) {
		String oldPrefix = prefix;
		prefix = newPrefix;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.WBS_MASKS__PREFIX, oldPrefix, prefix));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getWBSMask() {
		if (wBSMask == null) {
			wBSMask = new EObjectContainmentEList(WBSMask.class, this, MsprojectPackage.WBS_MASKS__WBS_MASK);
		}
		return wBSMask;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case MsprojectPackage.WBS_MASKS__WBS_MASK:
					return ((InternalEList)getWBSMask()).basicRemove(otherEnd, msgs);
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
			case MsprojectPackage.WBS_MASKS__VERIFY_UNIQUE_CODES:
				return isVerifyUniqueCodes() ? Boolean.TRUE : Boolean.FALSE;
			case MsprojectPackage.WBS_MASKS__GENERATE_CODES:
				return isGenerateCodes() ? Boolean.TRUE : Boolean.FALSE;
			case MsprojectPackage.WBS_MASKS__PREFIX:
				return getPrefix();
			case MsprojectPackage.WBS_MASKS__WBS_MASK:
				return getWBSMask();
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
			case MsprojectPackage.WBS_MASKS__VERIFY_UNIQUE_CODES:
				setVerifyUniqueCodes(((Boolean)newValue).booleanValue());
				return;
			case MsprojectPackage.WBS_MASKS__GENERATE_CODES:
				setGenerateCodes(((Boolean)newValue).booleanValue());
				return;
			case MsprojectPackage.WBS_MASKS__PREFIX:
				setPrefix((String)newValue);
				return;
			case MsprojectPackage.WBS_MASKS__WBS_MASK:
				getWBSMask().clear();
				getWBSMask().addAll((Collection)newValue);
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
			case MsprojectPackage.WBS_MASKS__VERIFY_UNIQUE_CODES:
				unsetVerifyUniqueCodes();
				return;
			case MsprojectPackage.WBS_MASKS__GENERATE_CODES:
				unsetGenerateCodes();
				return;
			case MsprojectPackage.WBS_MASKS__PREFIX:
				setPrefix(PREFIX_EDEFAULT);
				return;
			case MsprojectPackage.WBS_MASKS__WBS_MASK:
				getWBSMask().clear();
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
			case MsprojectPackage.WBS_MASKS__VERIFY_UNIQUE_CODES:
				return isSetVerifyUniqueCodes();
			case MsprojectPackage.WBS_MASKS__GENERATE_CODES:
				return isSetGenerateCodes();
			case MsprojectPackage.WBS_MASKS__PREFIX:
				return PREFIX_EDEFAULT == null ? prefix != null : !PREFIX_EDEFAULT.equals(prefix);
			case MsprojectPackage.WBS_MASKS__WBS_MASK:
				return wBSMask != null && !wBSMask.isEmpty();
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
		result.append(" (verifyUniqueCodes: ");
		if (verifyUniqueCodesESet) result.append(verifyUniqueCodes); else result.append("<unset>");
		result.append(", generateCodes: ");
		if (generateCodesESet) result.append(generateCodes); else result.append("<unset>");
		result.append(", prefix: ");
		result.append(prefix);
		result.append(')');
		return result.toString();
	}

} //WBSMasksImpl
