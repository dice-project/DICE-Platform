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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.WorkProductDescriptor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Work Product Descriptor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.impl.WorkProductDescriptorImpl#getActivityEntryState <em>Activity Entry State</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.WorkProductDescriptorImpl#getActivityExitState <em>Activity Exit State</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.WorkProductDescriptorImpl#getWorkProduct <em>Work Product</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.WorkProductDescriptorImpl#getImpactedBy <em>Impacted By</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.WorkProductDescriptorImpl#getImpacts <em>Impacts</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.WorkProductDescriptorImpl#getDeliverableParts <em>Deliverable Parts</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.WorkProductDescriptorImpl#getDeliverablePartsExclude <em>Deliverable Parts Exclude</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class WorkProductDescriptorImpl extends DescriptorImpl implements
		WorkProductDescriptor {
	/**
	 * The default value of the '{@link #getActivityEntryState() <em>Activity Entry State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActivityEntryState()
	 * @generated
	 * @ordered
	 */
	protected static final String ACTIVITY_ENTRY_STATE_EDEFAULT = ""; //$NON-NLS-1$

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
	protected static final String ACTIVITY_EXIT_STATE_EDEFAULT = ""; //$NON-NLS-1$

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
	 * The cached value of the '{@link #getWorkProduct() <em>Work Product</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWorkProduct()
	 * @generated
	 * @ordered
	 */
	protected WorkProduct workProduct;

	/**
	 * The cached value of the '{@link #getImpactedBy() <em>Impacted By</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImpactedBy()
	 * @generated
	 * @ordered
	 */
	protected EList<WorkProductDescriptor> impactedBy;

	/**
	 * The cached value of the '{@link #getImpacts() <em>Impacts</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImpacts()
	 * @generated
	 * @ordered
	 */
	protected EList<WorkProductDescriptor> impacts;

	/**
	 * The cached value of the '{@link #getDeliverableParts() <em>Deliverable Parts</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeliverableParts()
	 * @generated
	 * @ordered
	 */
	protected EList<WorkProductDescriptor> deliverableParts;

	/**
	 * The cached value of the '{@link #getDeliverablePartsExclude() <em>Deliverable Parts Exclude</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeliverablePartsExclude()
	 * @generated
	 * @ordered
	 */
	protected EList<WorkProduct> deliverablePartsExclude;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WorkProductDescriptorImpl() {
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
		return UmaPackage.Literals.WORK_PRODUCT_DESCRIPTOR;
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
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.WORK_PRODUCT_DESCRIPTOR__ACTIVITY_ENTRY_STATE,
					oldActivityEntryState, activityEntryState));
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
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.WORK_PRODUCT_DESCRIPTOR__ACTIVITY_EXIT_STATE,
					oldActivityExitState, activityExitState));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WorkProduct getWorkProduct() {
		if (workProduct != null && ((EObject) workProduct).eIsProxy()) {
			InternalEObject oldWorkProduct = (InternalEObject) workProduct;
			workProduct = (WorkProduct) eResolveProxy(oldWorkProduct);
			if (workProduct != oldWorkProduct) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							UmaPackage.WORK_PRODUCT_DESCRIPTOR__WORK_PRODUCT,
							oldWorkProduct, workProduct));
			}
		}
		return workProduct;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WorkProduct basicGetWorkProduct() {
		return workProduct;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWorkProduct(WorkProduct newWorkProduct) {
		WorkProduct oldWorkProduct = workProduct;
		workProduct = newWorkProduct;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.WORK_PRODUCT_DESCRIPTOR__WORK_PRODUCT,
					oldWorkProduct, workProduct));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<WorkProductDescriptor> getImpactedBy() {
		if (impactedBy == null) {
			impactedBy = new EObjectWithInverseResolvingEList.ManyInverse<WorkProductDescriptor>(
					WorkProductDescriptor.class, this,
					UmaPackage.WORK_PRODUCT_DESCRIPTOR__IMPACTED_BY,
					UmaPackage.WORK_PRODUCT_DESCRIPTOR__IMPACTS);
		}
		return impactedBy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<WorkProductDescriptor> getImpacts() {
		if (impacts == null) {
			impacts = new EObjectWithInverseResolvingEList.ManyInverse<WorkProductDescriptor>(
					WorkProductDescriptor.class, this,
					UmaPackage.WORK_PRODUCT_DESCRIPTOR__IMPACTS,
					UmaPackage.WORK_PRODUCT_DESCRIPTOR__IMPACTED_BY);
		}
		return impacts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<WorkProductDescriptor> getDeliverableParts() {
		if (deliverableParts == null) {
			deliverableParts = new EObjectResolvingEList<WorkProductDescriptor>(
					WorkProductDescriptor.class, this,
					UmaPackage.WORK_PRODUCT_DESCRIPTOR__DELIVERABLE_PARTS);
		}
		return deliverableParts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<WorkProduct> getDeliverablePartsExclude() {
		if (deliverablePartsExclude == null) {
			deliverablePartsExclude = new EObjectResolvingEList<WorkProduct>(
					WorkProduct.class,
					this,
					UmaPackage.WORK_PRODUCT_DESCRIPTOR__DELIVERABLE_PARTS_EXCLUDE);
		}
		return deliverablePartsExclude;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
		case UmaPackage.WORK_PRODUCT_DESCRIPTOR__IMPACTED_BY:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getImpactedBy())
					.basicAdd(otherEnd, msgs);
		case UmaPackage.WORK_PRODUCT_DESCRIPTOR__IMPACTS:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getImpacts())
					.basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
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
		case UmaPackage.WORK_PRODUCT_DESCRIPTOR__IMPACTED_BY:
			return ((InternalEList<?>) getImpactedBy()).basicRemove(otherEnd,
					msgs);
		case UmaPackage.WORK_PRODUCT_DESCRIPTOR__IMPACTS:
			return ((InternalEList<?>) getImpacts())
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
		case UmaPackage.WORK_PRODUCT_DESCRIPTOR__ACTIVITY_ENTRY_STATE:
			return getActivityEntryState();
		case UmaPackage.WORK_PRODUCT_DESCRIPTOR__ACTIVITY_EXIT_STATE:
			return getActivityExitState();
		case UmaPackage.WORK_PRODUCT_DESCRIPTOR__WORK_PRODUCT:
			if (resolve)
				return getWorkProduct();
			return basicGetWorkProduct();
		case UmaPackage.WORK_PRODUCT_DESCRIPTOR__IMPACTED_BY:
			return getImpactedBy();
		case UmaPackage.WORK_PRODUCT_DESCRIPTOR__IMPACTS:
			return getImpacts();
		case UmaPackage.WORK_PRODUCT_DESCRIPTOR__DELIVERABLE_PARTS:
			return getDeliverableParts();
		case UmaPackage.WORK_PRODUCT_DESCRIPTOR__DELIVERABLE_PARTS_EXCLUDE:
			return getDeliverablePartsExclude();
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
		case UmaPackage.WORK_PRODUCT_DESCRIPTOR__ACTIVITY_ENTRY_STATE:
			setActivityEntryState((String) newValue);
			return;
		case UmaPackage.WORK_PRODUCT_DESCRIPTOR__ACTIVITY_EXIT_STATE:
			setActivityExitState((String) newValue);
			return;
		case UmaPackage.WORK_PRODUCT_DESCRIPTOR__WORK_PRODUCT:
			setWorkProduct((WorkProduct) newValue);
			return;
		case UmaPackage.WORK_PRODUCT_DESCRIPTOR__IMPACTED_BY:
			getImpactedBy().clear();
			getImpactedBy().addAll(
					(Collection<? extends WorkProductDescriptor>) newValue);
			return;
		case UmaPackage.WORK_PRODUCT_DESCRIPTOR__IMPACTS:
			getImpacts().clear();
			getImpacts().addAll(
					(Collection<? extends WorkProductDescriptor>) newValue);
			return;
		case UmaPackage.WORK_PRODUCT_DESCRIPTOR__DELIVERABLE_PARTS:
			getDeliverableParts().clear();
			getDeliverableParts().addAll(
					(Collection<? extends WorkProductDescriptor>) newValue);
			return;
		case UmaPackage.WORK_PRODUCT_DESCRIPTOR__DELIVERABLE_PARTS_EXCLUDE:
			getDeliverablePartsExclude().clear();
			getDeliverablePartsExclude().addAll(
					(Collection<? extends WorkProduct>) newValue);
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
		case UmaPackage.WORK_PRODUCT_DESCRIPTOR__ACTIVITY_ENTRY_STATE:
			setActivityEntryState(ACTIVITY_ENTRY_STATE_EDEFAULT);
			return;
		case UmaPackage.WORK_PRODUCT_DESCRIPTOR__ACTIVITY_EXIT_STATE:
			setActivityExitState(ACTIVITY_EXIT_STATE_EDEFAULT);
			return;
		case UmaPackage.WORK_PRODUCT_DESCRIPTOR__WORK_PRODUCT:
			setWorkProduct((WorkProduct) null);
			return;
		case UmaPackage.WORK_PRODUCT_DESCRIPTOR__IMPACTED_BY:
			getImpactedBy().clear();
			return;
		case UmaPackage.WORK_PRODUCT_DESCRIPTOR__IMPACTS:
			getImpacts().clear();
			return;
		case UmaPackage.WORK_PRODUCT_DESCRIPTOR__DELIVERABLE_PARTS:
			getDeliverableParts().clear();
			return;
		case UmaPackage.WORK_PRODUCT_DESCRIPTOR__DELIVERABLE_PARTS_EXCLUDE:
			getDeliverablePartsExclude().clear();
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
		case UmaPackage.WORK_PRODUCT_DESCRIPTOR__ACTIVITY_ENTRY_STATE:
			return ACTIVITY_ENTRY_STATE_EDEFAULT == null ? activityEntryState != null
					: !ACTIVITY_ENTRY_STATE_EDEFAULT.equals(activityEntryState);
		case UmaPackage.WORK_PRODUCT_DESCRIPTOR__ACTIVITY_EXIT_STATE:
			return ACTIVITY_EXIT_STATE_EDEFAULT == null ? activityExitState != null
					: !ACTIVITY_EXIT_STATE_EDEFAULT.equals(activityExitState);
		case UmaPackage.WORK_PRODUCT_DESCRIPTOR__WORK_PRODUCT:
			return workProduct != null;
		case UmaPackage.WORK_PRODUCT_DESCRIPTOR__IMPACTED_BY:
			return impactedBy != null && !impactedBy.isEmpty();
		case UmaPackage.WORK_PRODUCT_DESCRIPTOR__IMPACTS:
			return impacts != null && !impacts.isEmpty();
		case UmaPackage.WORK_PRODUCT_DESCRIPTOR__DELIVERABLE_PARTS:
			return deliverableParts != null && !deliverableParts.isEmpty();
		case UmaPackage.WORK_PRODUCT_DESCRIPTOR__DELIVERABLE_PARTS_EXCLUDE:
			return deliverablePartsExclude != null
					&& !deliverablePartsExclude.isEmpty();
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
		result.append(" (activityEntryState: "); //$NON-NLS-1$
		result.append(activityEntryState);
		result.append(", activityExitState: "); //$NON-NLS-1$
		result.append(activityExitState);
		result.append(')');
		return result.toString();
	}

} //WorkProductDescriptorImpl
