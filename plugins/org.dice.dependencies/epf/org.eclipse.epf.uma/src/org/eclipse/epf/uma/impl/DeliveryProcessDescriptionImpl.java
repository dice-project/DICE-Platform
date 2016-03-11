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
import org.eclipse.epf.uma.DeliveryProcessDescription;
import org.eclipse.epf.uma.UmaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Delivery Process Description</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.impl.DeliveryProcessDescriptionImpl#getScale <em>Scale</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.DeliveryProcessDescriptionImpl#getProjectCharacteristics <em>Project Characteristics</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.DeliveryProcessDescriptionImpl#getRiskLevel <em>Risk Level</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.DeliveryProcessDescriptionImpl#getEstimatingTechnique <em>Estimating Technique</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.DeliveryProcessDescriptionImpl#getProjectMemberExpertise <em>Project Member Expertise</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.DeliveryProcessDescriptionImpl#getTypeOfContract <em>Type Of Contract</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DeliveryProcessDescriptionImpl extends ProcessDescriptionImpl
		implements DeliveryProcessDescription {
	/**
	 * The default value of the '{@link #getScale() <em>Scale</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScale()
	 * @generated
	 * @ordered
	 */
	protected static final String SCALE_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getScale() <em>Scale</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScale()
	 * @generated
	 * @ordered
	 */
	protected String scale = SCALE_EDEFAULT;

	/**
	 * The default value of the '{@link #getProjectCharacteristics() <em>Project Characteristics</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProjectCharacteristics()
	 * @generated
	 * @ordered
	 */
	protected static final String PROJECT_CHARACTERISTICS_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getProjectCharacteristics() <em>Project Characteristics</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProjectCharacteristics()
	 * @generated
	 * @ordered
	 */
	protected String projectCharacteristics = PROJECT_CHARACTERISTICS_EDEFAULT;

	/**
	 * The default value of the '{@link #getRiskLevel() <em>Risk Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRiskLevel()
	 * @generated
	 * @ordered
	 */
	protected static final String RISK_LEVEL_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getRiskLevel() <em>Risk Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRiskLevel()
	 * @generated
	 * @ordered
	 */
	protected String riskLevel = RISK_LEVEL_EDEFAULT;

	/**
	 * The default value of the '{@link #getEstimatingTechnique() <em>Estimating Technique</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEstimatingTechnique()
	 * @generated
	 * @ordered
	 */
	protected static final String ESTIMATING_TECHNIQUE_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getEstimatingTechnique() <em>Estimating Technique</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEstimatingTechnique()
	 * @generated
	 * @ordered
	 */
	protected String estimatingTechnique = ESTIMATING_TECHNIQUE_EDEFAULT;

	/**
	 * The default value of the '{@link #getProjectMemberExpertise() <em>Project Member Expertise</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProjectMemberExpertise()
	 * @generated
	 * @ordered
	 */
	protected static final String PROJECT_MEMBER_EXPERTISE_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getProjectMemberExpertise() <em>Project Member Expertise</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProjectMemberExpertise()
	 * @generated
	 * @ordered
	 */
	protected String projectMemberExpertise = PROJECT_MEMBER_EXPERTISE_EDEFAULT;

	/**
	 * The default value of the '{@link #getTypeOfContract() <em>Type Of Contract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypeOfContract()
	 * @generated
	 * @ordered
	 */
	protected static final String TYPE_OF_CONTRACT_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getTypeOfContract() <em>Type Of Contract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypeOfContract()
	 * @generated
	 * @ordered
	 */
	protected String typeOfContract = TYPE_OF_CONTRACT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DeliveryProcessDescriptionImpl() {
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
		return UmaPackage.Literals.DELIVERY_PROCESS_DESCRIPTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getScale() {
		return scale;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setScale(String newScale) {
		String oldScale = scale;
		scale = newScale;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.DELIVERY_PROCESS_DESCRIPTION__SCALE, oldScale,
					scale));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getProjectCharacteristics() {
		return projectCharacteristics;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProjectCharacteristics(String newProjectCharacteristics) {
		String oldProjectCharacteristics = projectCharacteristics;
		projectCharacteristics = newProjectCharacteristics;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(
					this,
					Notification.SET,
					UmaPackage.DELIVERY_PROCESS_DESCRIPTION__PROJECT_CHARACTERISTICS,
					oldProjectCharacteristics, projectCharacteristics));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRiskLevel() {
		return riskLevel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRiskLevel(String newRiskLevel) {
		String oldRiskLevel = riskLevel;
		riskLevel = newRiskLevel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.DELIVERY_PROCESS_DESCRIPTION__RISK_LEVEL,
					oldRiskLevel, riskLevel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEstimatingTechnique() {
		return estimatingTechnique;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEstimatingTechnique(String newEstimatingTechnique) {
		String oldEstimatingTechnique = estimatingTechnique;
		estimatingTechnique = newEstimatingTechnique;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(
					this,
					Notification.SET,
					UmaPackage.DELIVERY_PROCESS_DESCRIPTION__ESTIMATING_TECHNIQUE,
					oldEstimatingTechnique, estimatingTechnique));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getProjectMemberExpertise() {
		return projectMemberExpertise;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProjectMemberExpertise(String newProjectMemberExpertise) {
		String oldProjectMemberExpertise = projectMemberExpertise;
		projectMemberExpertise = newProjectMemberExpertise;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(
					this,
					Notification.SET,
					UmaPackage.DELIVERY_PROCESS_DESCRIPTION__PROJECT_MEMBER_EXPERTISE,
					oldProjectMemberExpertise, projectMemberExpertise));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTypeOfContract() {
		return typeOfContract;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTypeOfContract(String newTypeOfContract) {
		String oldTypeOfContract = typeOfContract;
		typeOfContract = newTypeOfContract;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.DELIVERY_PROCESS_DESCRIPTION__TYPE_OF_CONTRACT,
					oldTypeOfContract, typeOfContract));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case UmaPackage.DELIVERY_PROCESS_DESCRIPTION__SCALE:
			return getScale();
		case UmaPackage.DELIVERY_PROCESS_DESCRIPTION__PROJECT_CHARACTERISTICS:
			return getProjectCharacteristics();
		case UmaPackage.DELIVERY_PROCESS_DESCRIPTION__RISK_LEVEL:
			return getRiskLevel();
		case UmaPackage.DELIVERY_PROCESS_DESCRIPTION__ESTIMATING_TECHNIQUE:
			return getEstimatingTechnique();
		case UmaPackage.DELIVERY_PROCESS_DESCRIPTION__PROJECT_MEMBER_EXPERTISE:
			return getProjectMemberExpertise();
		case UmaPackage.DELIVERY_PROCESS_DESCRIPTION__TYPE_OF_CONTRACT:
			return getTypeOfContract();
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
		case UmaPackage.DELIVERY_PROCESS_DESCRIPTION__SCALE:
			setScale((String) newValue);
			return;
		case UmaPackage.DELIVERY_PROCESS_DESCRIPTION__PROJECT_CHARACTERISTICS:
			setProjectCharacteristics((String) newValue);
			return;
		case UmaPackage.DELIVERY_PROCESS_DESCRIPTION__RISK_LEVEL:
			setRiskLevel((String) newValue);
			return;
		case UmaPackage.DELIVERY_PROCESS_DESCRIPTION__ESTIMATING_TECHNIQUE:
			setEstimatingTechnique((String) newValue);
			return;
		case UmaPackage.DELIVERY_PROCESS_DESCRIPTION__PROJECT_MEMBER_EXPERTISE:
			setProjectMemberExpertise((String) newValue);
			return;
		case UmaPackage.DELIVERY_PROCESS_DESCRIPTION__TYPE_OF_CONTRACT:
			setTypeOfContract((String) newValue);
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
		case UmaPackage.DELIVERY_PROCESS_DESCRIPTION__SCALE:
			setScale(SCALE_EDEFAULT);
			return;
		case UmaPackage.DELIVERY_PROCESS_DESCRIPTION__PROJECT_CHARACTERISTICS:
			setProjectCharacteristics(PROJECT_CHARACTERISTICS_EDEFAULT);
			return;
		case UmaPackage.DELIVERY_PROCESS_DESCRIPTION__RISK_LEVEL:
			setRiskLevel(RISK_LEVEL_EDEFAULT);
			return;
		case UmaPackage.DELIVERY_PROCESS_DESCRIPTION__ESTIMATING_TECHNIQUE:
			setEstimatingTechnique(ESTIMATING_TECHNIQUE_EDEFAULT);
			return;
		case UmaPackage.DELIVERY_PROCESS_DESCRIPTION__PROJECT_MEMBER_EXPERTISE:
			setProjectMemberExpertise(PROJECT_MEMBER_EXPERTISE_EDEFAULT);
			return;
		case UmaPackage.DELIVERY_PROCESS_DESCRIPTION__TYPE_OF_CONTRACT:
			setTypeOfContract(TYPE_OF_CONTRACT_EDEFAULT);
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
		case UmaPackage.DELIVERY_PROCESS_DESCRIPTION__SCALE:
			return SCALE_EDEFAULT == null ? scale != null : !SCALE_EDEFAULT
					.equals(scale);
		case UmaPackage.DELIVERY_PROCESS_DESCRIPTION__PROJECT_CHARACTERISTICS:
			return PROJECT_CHARACTERISTICS_EDEFAULT == null ? projectCharacteristics != null
					: !PROJECT_CHARACTERISTICS_EDEFAULT
							.equals(projectCharacteristics);
		case UmaPackage.DELIVERY_PROCESS_DESCRIPTION__RISK_LEVEL:
			return RISK_LEVEL_EDEFAULT == null ? riskLevel != null
					: !RISK_LEVEL_EDEFAULT.equals(riskLevel);
		case UmaPackage.DELIVERY_PROCESS_DESCRIPTION__ESTIMATING_TECHNIQUE:
			return ESTIMATING_TECHNIQUE_EDEFAULT == null ? estimatingTechnique != null
					: !ESTIMATING_TECHNIQUE_EDEFAULT
							.equals(estimatingTechnique);
		case UmaPackage.DELIVERY_PROCESS_DESCRIPTION__PROJECT_MEMBER_EXPERTISE:
			return PROJECT_MEMBER_EXPERTISE_EDEFAULT == null ? projectMemberExpertise != null
					: !PROJECT_MEMBER_EXPERTISE_EDEFAULT
							.equals(projectMemberExpertise);
		case UmaPackage.DELIVERY_PROCESS_DESCRIPTION__TYPE_OF_CONTRACT:
			return TYPE_OF_CONTRACT_EDEFAULT == null ? typeOfContract != null
					: !TYPE_OF_CONTRACT_EDEFAULT.equals(typeOfContract);
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
		result.append(" (scale: "); //$NON-NLS-1$
		result.append(scale);
		result.append(", projectCharacteristics: "); //$NON-NLS-1$
		result.append(projectCharacteristics);
		result.append(", riskLevel: "); //$NON-NLS-1$
		result.append(riskLevel);
		result.append(", estimatingTechnique: "); //$NON-NLS-1$
		result.append(estimatingTechnique);
		result.append(", projectMemberExpertise: "); //$NON-NLS-1$
		result.append(projectMemberExpertise);
		result.append(", typeOfContract: "); //$NON-NLS-1$
		result.append(typeOfContract);
		result.append(')');
		return result.toString();
	}

} //DeliveryProcessDescriptionImpl
