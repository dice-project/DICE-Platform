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
import org.eclipse.epf.xml.uma.WorkBreakdownElement;
import org.eclipse.epf.xml.uma.WorkOrder;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Work Breakdown Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.WorkBreakdownElementImpl#getGroup2 <em>Group2</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.WorkBreakdownElementImpl#getPredecessor <em>Predecessor</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.WorkBreakdownElementImpl#isIsEventDriven <em>Is Event Driven</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.WorkBreakdownElementImpl#isIsOngoing <em>Is Ongoing</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.WorkBreakdownElementImpl#isIsRepeatable <em>Is Repeatable</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class WorkBreakdownElementImpl extends BreakdownElementImpl implements WorkBreakdownElement {
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
	 * The default value of the '{@link #isIsEventDriven() <em>Is Event Driven</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsEventDriven()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_EVENT_DRIVEN_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsEventDriven() <em>Is Event Driven</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsEventDriven()
	 * @generated
	 * @ordered
	 */
	protected boolean isEventDriven = IS_EVENT_DRIVEN_EDEFAULT;

	/**
	 * This is true if the Is Event Driven attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean isEventDrivenESet;

	/**
	 * The default value of the '{@link #isIsOngoing() <em>Is Ongoing</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsOngoing()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_ONGOING_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsOngoing() <em>Is Ongoing</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsOngoing()
	 * @generated
	 * @ordered
	 */
	protected boolean isOngoing = IS_ONGOING_EDEFAULT;

	/**
	 * This is true if the Is Ongoing attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean isOngoingESet;

	/**
	 * The default value of the '{@link #isIsRepeatable() <em>Is Repeatable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsRepeatable()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_REPEATABLE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsRepeatable() <em>Is Repeatable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsRepeatable()
	 * @generated
	 * @ordered
	 */
	protected boolean isRepeatable = IS_REPEATABLE_EDEFAULT;

	/**
	 * This is true if the Is Repeatable attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean isRepeatableESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WorkBreakdownElementImpl() {
		super();
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
	public FeatureMap getGroup2() {
		if (group2 == null) {
			group2 = new BasicFeatureMap(this, UmaPackage.WORK_BREAKDOWN_ELEMENT__GROUP2);
		}
		return group2;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<WorkOrder> getPredecessor() {
		return getGroup2().list(UmaPackage.Literals.WORK_BREAKDOWN_ELEMENT__PREDECESSOR);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsEventDriven() {
		return isEventDriven;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsEventDriven(boolean newIsEventDriven) {
		boolean oldIsEventDriven = isEventDriven;
		isEventDriven = newIsEventDriven;
		boolean oldIsEventDrivenESet = isEventDrivenESet;
		isEventDrivenESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UmaPackage.WORK_BREAKDOWN_ELEMENT__IS_EVENT_DRIVEN, oldIsEventDriven, isEventDriven, !oldIsEventDrivenESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetIsEventDriven() {
		boolean oldIsEventDriven = isEventDriven;
		boolean oldIsEventDrivenESet = isEventDrivenESet;
		isEventDriven = IS_EVENT_DRIVEN_EDEFAULT;
		isEventDrivenESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, UmaPackage.WORK_BREAKDOWN_ELEMENT__IS_EVENT_DRIVEN, oldIsEventDriven, IS_EVENT_DRIVEN_EDEFAULT, oldIsEventDrivenESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetIsEventDriven() {
		return isEventDrivenESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsOngoing() {
		return isOngoing;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsOngoing(boolean newIsOngoing) {
		boolean oldIsOngoing = isOngoing;
		isOngoing = newIsOngoing;
		boolean oldIsOngoingESet = isOngoingESet;
		isOngoingESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UmaPackage.WORK_BREAKDOWN_ELEMENT__IS_ONGOING, oldIsOngoing, isOngoing, !oldIsOngoingESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetIsOngoing() {
		boolean oldIsOngoing = isOngoing;
		boolean oldIsOngoingESet = isOngoingESet;
		isOngoing = IS_ONGOING_EDEFAULT;
		isOngoingESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, UmaPackage.WORK_BREAKDOWN_ELEMENT__IS_ONGOING, oldIsOngoing, IS_ONGOING_EDEFAULT, oldIsOngoingESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetIsOngoing() {
		return isOngoingESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsRepeatable() {
		return isRepeatable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsRepeatable(boolean newIsRepeatable) {
		boolean oldIsRepeatable = isRepeatable;
		isRepeatable = newIsRepeatable;
		boolean oldIsRepeatableESet = isRepeatableESet;
		isRepeatableESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UmaPackage.WORK_BREAKDOWN_ELEMENT__IS_REPEATABLE, oldIsRepeatable, isRepeatable, !oldIsRepeatableESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetIsRepeatable() {
		boolean oldIsRepeatable = isRepeatable;
		boolean oldIsRepeatableESet = isRepeatableESet;
		isRepeatable = IS_REPEATABLE_EDEFAULT;
		isRepeatableESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, UmaPackage.WORK_BREAKDOWN_ELEMENT__IS_REPEATABLE, oldIsRepeatable, IS_REPEATABLE_EDEFAULT, oldIsRepeatableESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetIsRepeatable() {
		return isRepeatableESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UmaPackage.WORK_BREAKDOWN_ELEMENT__GROUP2:
				return ((InternalEList<?>)getGroup2()).basicRemove(otherEnd, msgs);
			case UmaPackage.WORK_BREAKDOWN_ELEMENT__PREDECESSOR:
				return ((InternalEList<?>)getPredecessor()).basicRemove(otherEnd, msgs);
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
			case UmaPackage.WORK_BREAKDOWN_ELEMENT__GROUP2:
				if (coreType) return getGroup2();
				return ((FeatureMap.Internal)getGroup2()).getWrapper();
			case UmaPackage.WORK_BREAKDOWN_ELEMENT__PREDECESSOR:
				return getPredecessor();
			case UmaPackage.WORK_BREAKDOWN_ELEMENT__IS_EVENT_DRIVEN:
				return isIsEventDriven() ? Boolean.TRUE : Boolean.FALSE;
			case UmaPackage.WORK_BREAKDOWN_ELEMENT__IS_ONGOING:
				return isIsOngoing() ? Boolean.TRUE : Boolean.FALSE;
			case UmaPackage.WORK_BREAKDOWN_ELEMENT__IS_REPEATABLE:
				return isIsRepeatable() ? Boolean.TRUE : Boolean.FALSE;
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
			case UmaPackage.WORK_BREAKDOWN_ELEMENT__GROUP2:
				((FeatureMap.Internal)getGroup2()).set(newValue);
				return;
			case UmaPackage.WORK_BREAKDOWN_ELEMENT__PREDECESSOR:
				getPredecessor().clear();
				getPredecessor().addAll((Collection<? extends WorkOrder>)newValue);
				return;
			case UmaPackage.WORK_BREAKDOWN_ELEMENT__IS_EVENT_DRIVEN:
				setIsEventDriven(((Boolean)newValue).booleanValue());
				return;
			case UmaPackage.WORK_BREAKDOWN_ELEMENT__IS_ONGOING:
				setIsOngoing(((Boolean)newValue).booleanValue());
				return;
			case UmaPackage.WORK_BREAKDOWN_ELEMENT__IS_REPEATABLE:
				setIsRepeatable(((Boolean)newValue).booleanValue());
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
			case UmaPackage.WORK_BREAKDOWN_ELEMENT__GROUP2:
				getGroup2().clear();
				return;
			case UmaPackage.WORK_BREAKDOWN_ELEMENT__PREDECESSOR:
				getPredecessor().clear();
				return;
			case UmaPackage.WORK_BREAKDOWN_ELEMENT__IS_EVENT_DRIVEN:
				unsetIsEventDriven();
				return;
			case UmaPackage.WORK_BREAKDOWN_ELEMENT__IS_ONGOING:
				unsetIsOngoing();
				return;
			case UmaPackage.WORK_BREAKDOWN_ELEMENT__IS_REPEATABLE:
				unsetIsRepeatable();
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
			case UmaPackage.WORK_BREAKDOWN_ELEMENT__GROUP2:
				return group2 != null && !group2.isEmpty();
			case UmaPackage.WORK_BREAKDOWN_ELEMENT__PREDECESSOR:
				return !getPredecessor().isEmpty();
			case UmaPackage.WORK_BREAKDOWN_ELEMENT__IS_EVENT_DRIVEN:
				return isSetIsEventDriven();
			case UmaPackage.WORK_BREAKDOWN_ELEMENT__IS_ONGOING:
				return isSetIsOngoing();
			case UmaPackage.WORK_BREAKDOWN_ELEMENT__IS_REPEATABLE:
				return isSetIsRepeatable();
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
		result.append(" (group2: ");
		result.append(group2);
		result.append(", isEventDriven: ");
		if (isEventDrivenESet) result.append(isEventDriven); else result.append("<unset>");
		result.append(", isOngoing: ");
		if (isOngoingESet) result.append(isOngoing); else result.append("<unset>");
		result.append(", isRepeatable: ");
		if (isRepeatableESet) result.append(isRepeatable); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //WorkBreakdownElementImpl
