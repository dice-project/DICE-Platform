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
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.WorkBreakdownElement;
import org.eclipse.epf.uma.WorkOrder;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Work Breakdown Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.impl.WorkBreakdownElementImpl#getIsRepeatable <em>Is Repeatable</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.WorkBreakdownElementImpl#getIsOngoing <em>Is Ongoing</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.WorkBreakdownElementImpl#getIsEventDriven <em>Is Event Driven</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.WorkBreakdownElementImpl#getLinkToPredecessor <em>Link To Predecessor</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class WorkBreakdownElementImpl extends BreakdownElementImpl
		implements WorkBreakdownElement {
	/**
	 * The default value of the '{@link #getIsRepeatable() <em>Is Repeatable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsRepeatable()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_REPEATABLE_EDEFAULT = Boolean.FALSE;

	/**
	 * The cached value of the '{@link #getIsRepeatable() <em>Is Repeatable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsRepeatable()
	 * @generated
	 * @ordered
	 */
	protected Boolean isRepeatable = IS_REPEATABLE_EDEFAULT;

	/**
	 * The default value of the '{@link #getIsOngoing() <em>Is Ongoing</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsOngoing()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_ONGOING_EDEFAULT = Boolean.FALSE;

	/**
	 * The cached value of the '{@link #getIsOngoing() <em>Is Ongoing</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsOngoing()
	 * @generated
	 * @ordered
	 */
	protected Boolean isOngoing = IS_ONGOING_EDEFAULT;

	/**
	 * The default value of the '{@link #getIsEventDriven() <em>Is Event Driven</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsEventDriven()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_EVENT_DRIVEN_EDEFAULT = Boolean.FALSE;

	/**
	 * The cached value of the '{@link #getIsEventDriven() <em>Is Event Driven</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsEventDriven()
	 * @generated
	 * @ordered
	 */
	protected Boolean isEventDriven = IS_EVENT_DRIVEN_EDEFAULT;

	/**
	 * The cached value of the '{@link #getLinkToPredecessor() <em>Link To Predecessor</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLinkToPredecessor()
	 * @generated
	 * @ordered
	 */
	protected EList<WorkOrder> linkToPredecessor;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WorkBreakdownElementImpl() {
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
		return UmaPackage.Literals.WORK_BREAKDOWN_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getIsRepeatable() {
		return isRepeatable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsRepeatable(Boolean newIsRepeatable) {
		Boolean oldIsRepeatable = isRepeatable;
		isRepeatable = newIsRepeatable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.WORK_BREAKDOWN_ELEMENT__IS_REPEATABLE,
					oldIsRepeatable, isRepeatable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getIsOngoing() {
		return isOngoing;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsOngoing(Boolean newIsOngoing) {
		Boolean oldIsOngoing = isOngoing;
		isOngoing = newIsOngoing;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.WORK_BREAKDOWN_ELEMENT__IS_ONGOING,
					oldIsOngoing, isOngoing));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getIsEventDriven() {
		return isEventDriven;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsEventDriven(Boolean newIsEventDriven) {
		Boolean oldIsEventDriven = isEventDriven;
		isEventDriven = newIsEventDriven;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.WORK_BREAKDOWN_ELEMENT__IS_EVENT_DRIVEN,
					oldIsEventDriven, isEventDriven));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<WorkOrder> getLinkToPredecessor() {
		if (linkToPredecessor == null) {
			linkToPredecessor = new EObjectResolvingEList<WorkOrder>(
					WorkOrder.class, this,
					UmaPackage.WORK_BREAKDOWN_ELEMENT__LINK_TO_PREDECESSOR);
		}
		return linkToPredecessor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case UmaPackage.WORK_BREAKDOWN_ELEMENT__IS_REPEATABLE:
			return getIsRepeatable();
		case UmaPackage.WORK_BREAKDOWN_ELEMENT__IS_ONGOING:
			return getIsOngoing();
		case UmaPackage.WORK_BREAKDOWN_ELEMENT__IS_EVENT_DRIVEN:
			return getIsEventDriven();
		case UmaPackage.WORK_BREAKDOWN_ELEMENT__LINK_TO_PREDECESSOR:
			return getLinkToPredecessor();
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
		case UmaPackage.WORK_BREAKDOWN_ELEMENT__IS_REPEATABLE:
			setIsRepeatable((Boolean) newValue);
			return;
		case UmaPackage.WORK_BREAKDOWN_ELEMENT__IS_ONGOING:
			setIsOngoing((Boolean) newValue);
			return;
		case UmaPackage.WORK_BREAKDOWN_ELEMENT__IS_EVENT_DRIVEN:
			setIsEventDriven((Boolean) newValue);
			return;
		case UmaPackage.WORK_BREAKDOWN_ELEMENT__LINK_TO_PREDECESSOR:
			getLinkToPredecessor().clear();
			getLinkToPredecessor().addAll(
					(Collection<? extends WorkOrder>) newValue);
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
		case UmaPackage.WORK_BREAKDOWN_ELEMENT__IS_REPEATABLE:
			setIsRepeatable(IS_REPEATABLE_EDEFAULT);
			return;
		case UmaPackage.WORK_BREAKDOWN_ELEMENT__IS_ONGOING:
			setIsOngoing(IS_ONGOING_EDEFAULT);
			return;
		case UmaPackage.WORK_BREAKDOWN_ELEMENT__IS_EVENT_DRIVEN:
			setIsEventDriven(IS_EVENT_DRIVEN_EDEFAULT);
			return;
		case UmaPackage.WORK_BREAKDOWN_ELEMENT__LINK_TO_PREDECESSOR:
			getLinkToPredecessor().clear();
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
		case UmaPackage.WORK_BREAKDOWN_ELEMENT__IS_REPEATABLE:
			return IS_REPEATABLE_EDEFAULT == null ? isRepeatable != null
					: !IS_REPEATABLE_EDEFAULT.equals(isRepeatable);
		case UmaPackage.WORK_BREAKDOWN_ELEMENT__IS_ONGOING:
			return IS_ONGOING_EDEFAULT == null ? isOngoing != null
					: !IS_ONGOING_EDEFAULT.equals(isOngoing);
		case UmaPackage.WORK_BREAKDOWN_ELEMENT__IS_EVENT_DRIVEN:
			return IS_EVENT_DRIVEN_EDEFAULT == null ? isEventDriven != null
					: !IS_EVENT_DRIVEN_EDEFAULT.equals(isEventDriven);
		case UmaPackage.WORK_BREAKDOWN_ELEMENT__LINK_TO_PREDECESSOR:
			return linkToPredecessor != null && !linkToPredecessor.isEmpty();
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
		result.append(" (isRepeatable: "); //$NON-NLS-1$
		result.append(isRepeatable);
		result.append(", isOngoing: "); //$NON-NLS-1$
		result.append(isOngoing);
		result.append(", isEventDriven: "); //$NON-NLS-1$
		result.append(isEventDriven);
		result.append(')');
		return result.toString();
	}

} //WorkBreakdownElementImpl
