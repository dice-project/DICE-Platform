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

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.epf.xml.uma.UmaPackage;
import org.eclipse.epf.xml.uma.WorkProductDescriptor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Work Product Descriptor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.WorkProductDescriptorImpl#getWorkProduct <em>Work Product</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.WorkProductDescriptorImpl#getResponsibleRole <em>Responsible Role</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.WorkProductDescriptorImpl#getGroup2 <em>Group2</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.WorkProductDescriptorImpl#getExternalInputTo <em>External Input To</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.WorkProductDescriptorImpl#getImpactedBy <em>Impacted By</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.WorkProductDescriptorImpl#getImpacts <em>Impacts</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.WorkProductDescriptorImpl#getMandatoryInputTo <em>Mandatory Input To</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.WorkProductDescriptorImpl#getOptionalInputTo <em>Optional Input To</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.WorkProductDescriptorImpl#getOutputFrom <em>Output From</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.WorkProductDescriptorImpl#getDeliverableParts <em>Deliverable Parts</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.WorkProductDescriptorImpl#getActivityEntryState <em>Activity Entry State</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.WorkProductDescriptorImpl#getActivityExitState <em>Activity Exit State</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class WorkProductDescriptorImpl extends DescriptorImpl implements WorkProductDescriptor {
	/**
	 * The default value of the '{@link #getWorkProduct() <em>Work Product</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWorkProduct()
	 * @generated
	 * @ordered
	 */
	protected static final String WORK_PRODUCT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getWorkProduct() <em>Work Product</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWorkProduct()
	 * @generated
	 * @ordered
	 */
	protected String workProduct = WORK_PRODUCT_EDEFAULT;

	/**
	 * The default value of the '{@link #getResponsibleRole() <em>Responsible Role</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResponsibleRole()
	 * @generated
	 * @ordered
	 */
	protected static final String RESPONSIBLE_ROLE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getResponsibleRole() <em>Responsible Role</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResponsibleRole()
	 * @generated
	 * @ordered
	 */
	protected String responsibleRole = RESPONSIBLE_ROLE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getGroup2() <em>Group2</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroup2()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap group2;

	/**
	 * The default value of the '{@link #getActivityEntryState() <em>Activity Entry State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActivityEntryState()
	 * @generated
	 * @ordered
	 */
	protected static final String ACTIVITY_ENTRY_STATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getActivityEntryState() <em>Activity Entry State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActivityEntryState()
	 * @generated
	 * @ordered
	 */
	protected String activityEntryState = ACTIVITY_ENTRY_STATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getActivityExitState() <em>Activity Exit State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActivityExitState()
	 * @generated
	 * @ordered
	 */
	protected static final String ACTIVITY_EXIT_STATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getActivityExitState() <em>Activity Exit State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActivityExitState()
	 * @generated
	 * @ordered
	 */
	protected String activityExitState = ACTIVITY_EXIT_STATE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WorkProductDescriptorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UmaPackage.Literals.WORK_PRODUCT_DESCRIPTOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getWorkProduct() {
		return workProduct;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWorkProduct(String newWorkProduct) {
		String oldWorkProduct = workProduct;
		workProduct = newWorkProduct;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UmaPackage.WORK_PRODUCT_DESCRIPTOR__WORK_PRODUCT, oldWorkProduct, workProduct));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getResponsibleRole() {
		return responsibleRole;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResponsibleRole(String newResponsibleRole) {
		String oldResponsibleRole = responsibleRole;
		responsibleRole = newResponsibleRole;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UmaPackage.WORK_PRODUCT_DESCRIPTOR__RESPONSIBLE_ROLE, oldResponsibleRole, responsibleRole));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getGroup2() {
		if (group2 == null) {
			group2 = new BasicFeatureMap(this, UmaPackage.WORK_PRODUCT_DESCRIPTOR__GROUP2);
		}
		return group2;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getExternalInputTo() {
		return getGroup2().list(UmaPackage.Literals.WORK_PRODUCT_DESCRIPTOR__EXTERNAL_INPUT_TO);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getImpactedBy() {
		return getGroup2().list(UmaPackage.Literals.WORK_PRODUCT_DESCRIPTOR__IMPACTED_BY);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getImpacts() {
		return getGroup2().list(UmaPackage.Literals.WORK_PRODUCT_DESCRIPTOR__IMPACTS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getMandatoryInputTo() {
		return getGroup2().list(UmaPackage.Literals.WORK_PRODUCT_DESCRIPTOR__MANDATORY_INPUT_TO);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getOptionalInputTo() {
		return getGroup2().list(UmaPackage.Literals.WORK_PRODUCT_DESCRIPTOR__OPTIONAL_INPUT_TO);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getOutputFrom() {
		return getGroup2().list(UmaPackage.Literals.WORK_PRODUCT_DESCRIPTOR__OUTPUT_FROM);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getDeliverableParts() {
		return getGroup2().list(UmaPackage.Literals.WORK_PRODUCT_DESCRIPTOR__DELIVERABLE_PARTS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getActivityEntryState() {
		return activityEntryState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActivityEntryState(String newActivityEntryState) {
		String oldActivityEntryState = activityEntryState;
		activityEntryState = newActivityEntryState;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UmaPackage.WORK_PRODUCT_DESCRIPTOR__ACTIVITY_ENTRY_STATE, oldActivityEntryState, activityEntryState));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getActivityExitState() {
		return activityExitState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActivityExitState(String newActivityExitState) {
		String oldActivityExitState = activityExitState;
		activityExitState = newActivityExitState;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UmaPackage.WORK_PRODUCT_DESCRIPTOR__ACTIVITY_EXIT_STATE, oldActivityExitState, activityExitState));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UmaPackage.WORK_PRODUCT_DESCRIPTOR__GROUP2:
				return ((InternalEList<?>)getGroup2()).basicRemove(otherEnd, msgs);
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
			case UmaPackage.WORK_PRODUCT_DESCRIPTOR__WORK_PRODUCT:
				return getWorkProduct();
			case UmaPackage.WORK_PRODUCT_DESCRIPTOR__RESPONSIBLE_ROLE:
				return getResponsibleRole();
			case UmaPackage.WORK_PRODUCT_DESCRIPTOR__GROUP2:
				if (coreType) return getGroup2();
				return ((FeatureMap.Internal)getGroup2()).getWrapper();
			case UmaPackage.WORK_PRODUCT_DESCRIPTOR__EXTERNAL_INPUT_TO:
				return getExternalInputTo();
			case UmaPackage.WORK_PRODUCT_DESCRIPTOR__IMPACTED_BY:
				return getImpactedBy();
			case UmaPackage.WORK_PRODUCT_DESCRIPTOR__IMPACTS:
				return getImpacts();
			case UmaPackage.WORK_PRODUCT_DESCRIPTOR__MANDATORY_INPUT_TO:
				return getMandatoryInputTo();
			case UmaPackage.WORK_PRODUCT_DESCRIPTOR__OPTIONAL_INPUT_TO:
				return getOptionalInputTo();
			case UmaPackage.WORK_PRODUCT_DESCRIPTOR__OUTPUT_FROM:
				return getOutputFrom();
			case UmaPackage.WORK_PRODUCT_DESCRIPTOR__DELIVERABLE_PARTS:
				return getDeliverableParts();
			case UmaPackage.WORK_PRODUCT_DESCRIPTOR__ACTIVITY_ENTRY_STATE:
				return getActivityEntryState();
			case UmaPackage.WORK_PRODUCT_DESCRIPTOR__ACTIVITY_EXIT_STATE:
				return getActivityExitState();
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
			case UmaPackage.WORK_PRODUCT_DESCRIPTOR__WORK_PRODUCT:
				setWorkProduct((String)newValue);
				return;
			case UmaPackage.WORK_PRODUCT_DESCRIPTOR__RESPONSIBLE_ROLE:
				setResponsibleRole((String)newValue);
				return;
			case UmaPackage.WORK_PRODUCT_DESCRIPTOR__GROUP2:
				((FeatureMap.Internal)getGroup2()).set(newValue);
				return;
			case UmaPackage.WORK_PRODUCT_DESCRIPTOR__EXTERNAL_INPUT_TO:
				getExternalInputTo().clear();
				getExternalInputTo().addAll((Collection<? extends String>)newValue);
				return;
			case UmaPackage.WORK_PRODUCT_DESCRIPTOR__IMPACTED_BY:
				getImpactedBy().clear();
				getImpactedBy().addAll((Collection<? extends String>)newValue);
				return;
			case UmaPackage.WORK_PRODUCT_DESCRIPTOR__IMPACTS:
				getImpacts().clear();
				getImpacts().addAll((Collection<? extends String>)newValue);
				return;
			case UmaPackage.WORK_PRODUCT_DESCRIPTOR__MANDATORY_INPUT_TO:
				getMandatoryInputTo().clear();
				getMandatoryInputTo().addAll((Collection<? extends String>)newValue);
				return;
			case UmaPackage.WORK_PRODUCT_DESCRIPTOR__OPTIONAL_INPUT_TO:
				getOptionalInputTo().clear();
				getOptionalInputTo().addAll((Collection<? extends String>)newValue);
				return;
			case UmaPackage.WORK_PRODUCT_DESCRIPTOR__OUTPUT_FROM:
				getOutputFrom().clear();
				getOutputFrom().addAll((Collection<? extends String>)newValue);
				return;
			case UmaPackage.WORK_PRODUCT_DESCRIPTOR__DELIVERABLE_PARTS:
				getDeliverableParts().clear();
				getDeliverableParts().addAll((Collection<? extends String>)newValue);
				return;
			case UmaPackage.WORK_PRODUCT_DESCRIPTOR__ACTIVITY_ENTRY_STATE:
				setActivityEntryState((String)newValue);
				return;
			case UmaPackage.WORK_PRODUCT_DESCRIPTOR__ACTIVITY_EXIT_STATE:
				setActivityExitState((String)newValue);
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
			case UmaPackage.WORK_PRODUCT_DESCRIPTOR__WORK_PRODUCT:
				setWorkProduct(WORK_PRODUCT_EDEFAULT);
				return;
			case UmaPackage.WORK_PRODUCT_DESCRIPTOR__RESPONSIBLE_ROLE:
				setResponsibleRole(RESPONSIBLE_ROLE_EDEFAULT);
				return;
			case UmaPackage.WORK_PRODUCT_DESCRIPTOR__GROUP2:
				getGroup2().clear();
				return;
			case UmaPackage.WORK_PRODUCT_DESCRIPTOR__EXTERNAL_INPUT_TO:
				getExternalInputTo().clear();
				return;
			case UmaPackage.WORK_PRODUCT_DESCRIPTOR__IMPACTED_BY:
				getImpactedBy().clear();
				return;
			case UmaPackage.WORK_PRODUCT_DESCRIPTOR__IMPACTS:
				getImpacts().clear();
				return;
			case UmaPackage.WORK_PRODUCT_DESCRIPTOR__MANDATORY_INPUT_TO:
				getMandatoryInputTo().clear();
				return;
			case UmaPackage.WORK_PRODUCT_DESCRIPTOR__OPTIONAL_INPUT_TO:
				getOptionalInputTo().clear();
				return;
			case UmaPackage.WORK_PRODUCT_DESCRIPTOR__OUTPUT_FROM:
				getOutputFrom().clear();
				return;
			case UmaPackage.WORK_PRODUCT_DESCRIPTOR__DELIVERABLE_PARTS:
				getDeliverableParts().clear();
				return;
			case UmaPackage.WORK_PRODUCT_DESCRIPTOR__ACTIVITY_ENTRY_STATE:
				setActivityEntryState(ACTIVITY_ENTRY_STATE_EDEFAULT);
				return;
			case UmaPackage.WORK_PRODUCT_DESCRIPTOR__ACTIVITY_EXIT_STATE:
				setActivityExitState(ACTIVITY_EXIT_STATE_EDEFAULT);
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
			case UmaPackage.WORK_PRODUCT_DESCRIPTOR__WORK_PRODUCT:
				return WORK_PRODUCT_EDEFAULT == null ? workProduct != null : !WORK_PRODUCT_EDEFAULT.equals(workProduct);
			case UmaPackage.WORK_PRODUCT_DESCRIPTOR__RESPONSIBLE_ROLE:
				return RESPONSIBLE_ROLE_EDEFAULT == null ? responsibleRole != null : !RESPONSIBLE_ROLE_EDEFAULT.equals(responsibleRole);
			case UmaPackage.WORK_PRODUCT_DESCRIPTOR__GROUP2:
				return group2 != null && !group2.isEmpty();
			case UmaPackage.WORK_PRODUCT_DESCRIPTOR__EXTERNAL_INPUT_TO:
				return !getExternalInputTo().isEmpty();
			case UmaPackage.WORK_PRODUCT_DESCRIPTOR__IMPACTED_BY:
				return !getImpactedBy().isEmpty();
			case UmaPackage.WORK_PRODUCT_DESCRIPTOR__IMPACTS:
				return !getImpacts().isEmpty();
			case UmaPackage.WORK_PRODUCT_DESCRIPTOR__MANDATORY_INPUT_TO:
				return !getMandatoryInputTo().isEmpty();
			case UmaPackage.WORK_PRODUCT_DESCRIPTOR__OPTIONAL_INPUT_TO:
				return !getOptionalInputTo().isEmpty();
			case UmaPackage.WORK_PRODUCT_DESCRIPTOR__OUTPUT_FROM:
				return !getOutputFrom().isEmpty();
			case UmaPackage.WORK_PRODUCT_DESCRIPTOR__DELIVERABLE_PARTS:
				return !getDeliverableParts().isEmpty();
			case UmaPackage.WORK_PRODUCT_DESCRIPTOR__ACTIVITY_ENTRY_STATE:
				return ACTIVITY_ENTRY_STATE_EDEFAULT == null ? activityEntryState != null : !ACTIVITY_ENTRY_STATE_EDEFAULT.equals(activityEntryState);
			case UmaPackage.WORK_PRODUCT_DESCRIPTOR__ACTIVITY_EXIT_STATE:
				return ACTIVITY_EXIT_STATE_EDEFAULT == null ? activityExitState != null : !ACTIVITY_EXIT_STATE_EDEFAULT.equals(activityExitState);
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
		result.append(" (workProduct: ");
		result.append(workProduct);
		result.append(", responsibleRole: ");
		result.append(responsibleRole);
		result.append(", group2: ");
		result.append(group2);
		result.append(", activityEntryState: ");
		result.append(activityEntryState);
		result.append(", activityExitState: ");
		result.append(activityExitState);
		result.append(')');
		return result.toString();
	}

} //WorkProductDescriptorImpl
