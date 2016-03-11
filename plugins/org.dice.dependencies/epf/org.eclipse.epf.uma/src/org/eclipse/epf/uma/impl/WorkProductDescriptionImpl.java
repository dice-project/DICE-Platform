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
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.WorkProductDescription;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Work Product Description</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.impl.WorkProductDescriptionImpl#getPurpose <em>Purpose</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.WorkProductDescriptionImpl#getImpactOfNotHaving <em>Impact Of Not Having</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.WorkProductDescriptionImpl#getReasonsForNotNeeding <em>Reasons For Not Needing</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class WorkProductDescriptionImpl extends ContentDescriptionImpl
		implements WorkProductDescription {
	/**
	 * The default value of the '{@link #getPurpose() <em>Purpose</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPurpose()
	 * @generated
	 * @ordered
	 */
	protected static final String PURPOSE_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getPurpose() <em>Purpose</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPurpose()
	 * @generated
	 * @ordered
	 */
	protected String purpose = PURPOSE_EDEFAULT;

	/**
	 * The default value of the '{@link #getImpactOfNotHaving() <em>Impact Of Not Having</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImpactOfNotHaving()
	 * @generated
	 * @ordered
	 */
	protected static final String IMPACT_OF_NOT_HAVING_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getImpactOfNotHaving() <em>Impact Of Not Having</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImpactOfNotHaving()
	 * @generated
	 * @ordered
	 */
	protected String impactOfNotHaving = IMPACT_OF_NOT_HAVING_EDEFAULT;

	/**
	 * The default value of the '{@link #getReasonsForNotNeeding() <em>Reasons For Not Needing</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReasonsForNotNeeding()
	 * @generated
	 * @ordered
	 */
	protected static final String REASONS_FOR_NOT_NEEDING_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getReasonsForNotNeeding() <em>Reasons For Not Needing</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReasonsForNotNeeding()
	 * @generated
	 * @ordered
	 */
	protected String reasonsForNotNeeding = REASONS_FOR_NOT_NEEDING_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WorkProductDescriptionImpl() {
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
		return UmaPackage.Literals.WORK_PRODUCT_DESCRIPTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPurpose() {
		return purpose;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPurpose(String newPurpose) {
		String oldPurpose = purpose;
		purpose = newPurpose;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.WORK_PRODUCT_DESCRIPTION__PURPOSE, oldPurpose,
					purpose));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getImpactOfNotHaving() {
		return impactOfNotHaving;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setImpactOfNotHaving(String newImpactOfNotHaving) {
		String oldImpactOfNotHaving = impactOfNotHaving;
		impactOfNotHaving = newImpactOfNotHaving;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.WORK_PRODUCT_DESCRIPTION__IMPACT_OF_NOT_HAVING,
					oldImpactOfNotHaving, impactOfNotHaving));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getReasonsForNotNeeding() {
		return reasonsForNotNeeding;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReasonsForNotNeeding(String newReasonsForNotNeeding) {
		String oldReasonsForNotNeeding = reasonsForNotNeeding;
		reasonsForNotNeeding = newReasonsForNotNeeding;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(
					this,
					Notification.SET,
					UmaPackage.WORK_PRODUCT_DESCRIPTION__REASONS_FOR_NOT_NEEDING,
					oldReasonsForNotNeeding, reasonsForNotNeeding));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case UmaPackage.WORK_PRODUCT_DESCRIPTION__PURPOSE:
			return getPurpose();
		case UmaPackage.WORK_PRODUCT_DESCRIPTION__IMPACT_OF_NOT_HAVING:
			return getImpactOfNotHaving();
		case UmaPackage.WORK_PRODUCT_DESCRIPTION__REASONS_FOR_NOT_NEEDING:
			return getReasonsForNotNeeding();
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
		case UmaPackage.WORK_PRODUCT_DESCRIPTION__PURPOSE:
			setPurpose((String) newValue);
			return;
		case UmaPackage.WORK_PRODUCT_DESCRIPTION__IMPACT_OF_NOT_HAVING:
			setImpactOfNotHaving((String) newValue);
			return;
		case UmaPackage.WORK_PRODUCT_DESCRIPTION__REASONS_FOR_NOT_NEEDING:
			setReasonsForNotNeeding((String) newValue);
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
		case UmaPackage.WORK_PRODUCT_DESCRIPTION__PURPOSE:
			setPurpose(PURPOSE_EDEFAULT);
			return;
		case UmaPackage.WORK_PRODUCT_DESCRIPTION__IMPACT_OF_NOT_HAVING:
			setImpactOfNotHaving(IMPACT_OF_NOT_HAVING_EDEFAULT);
			return;
		case UmaPackage.WORK_PRODUCT_DESCRIPTION__REASONS_FOR_NOT_NEEDING:
			setReasonsForNotNeeding(REASONS_FOR_NOT_NEEDING_EDEFAULT);
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
		case UmaPackage.WORK_PRODUCT_DESCRIPTION__PURPOSE:
			return PURPOSE_EDEFAULT == null ? purpose != null
					: !PURPOSE_EDEFAULT.equals(purpose);
		case UmaPackage.WORK_PRODUCT_DESCRIPTION__IMPACT_OF_NOT_HAVING:
			return IMPACT_OF_NOT_HAVING_EDEFAULT == null ? impactOfNotHaving != null
					: !IMPACT_OF_NOT_HAVING_EDEFAULT.equals(impactOfNotHaving);
		case UmaPackage.WORK_PRODUCT_DESCRIPTION__REASONS_FOR_NOT_NEEDING:
			return REASONS_FOR_NOT_NEEDING_EDEFAULT == null ? reasonsForNotNeeding != null
					: !REASONS_FOR_NOT_NEEDING_EDEFAULT
							.equals(reasonsForNotNeeding);
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
		result.append(" (purpose: "); //$NON-NLS-1$
		result.append(purpose);
		result.append(", impactOfNotHaving: "); //$NON-NLS-1$
		result.append(impactOfNotHaving);
		result.append(", reasonsForNotNeeding: "); //$NON-NLS-1$
		result.append(reasonsForNotNeeding);
		result.append(')');
		return result.toString();
	}

} //WorkProductDescriptionImpl
