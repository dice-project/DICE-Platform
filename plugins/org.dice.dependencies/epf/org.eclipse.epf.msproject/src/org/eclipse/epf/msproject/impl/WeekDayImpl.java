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

import java.math.BigInteger;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.epf.msproject.MsprojectPackage;
import org.eclipse.epf.msproject.TimePeriod;
import org.eclipse.epf.msproject.WeekDay;
import org.eclipse.epf.msproject.WorkingTimes;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Week Day</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.msproject.impl.WeekDayImpl#getDayType <em>Day Type</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.WeekDayImpl#isDayWorking <em>Day Working</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.WeekDayImpl#getTimePeriod <em>Time Period</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.WeekDayImpl#getWorkingTimes <em>Working Times</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class WeekDayImpl extends EObjectImpl implements WeekDay {
	/**
	 * The default value of the '{@link #getDayType() <em>Day Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDayType()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger DAY_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDayType() <em>Day Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDayType()
	 * @generated
	 * @ordered
	 */
	protected BigInteger dayType = DAY_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #isDayWorking() <em>Day Working</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDayWorking()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DAY_WORKING_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isDayWorking() <em>Day Working</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDayWorking()
	 * @generated
	 * @ordered
	 */
	protected boolean dayWorking = DAY_WORKING_EDEFAULT;

	/**
	 * This is true if the Day Working attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean dayWorkingESet = false;

	/**
	 * The cached value of the '{@link #getTimePeriod() <em>Time Period</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimePeriod()
	 * @generated
	 * @ordered
	 */
	protected TimePeriod timePeriod = null;

	/**
	 * The cached value of the '{@link #getWorkingTimes() <em>Working Times</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWorkingTimes()
	 * @generated
	 * @ordered
	 */
	protected WorkingTimes workingTimes = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WeekDayImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return MsprojectPackage.eINSTANCE.getWeekDay();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getDayType() {
		return dayType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDayType(BigInteger newDayType) {
		BigInteger oldDayType = dayType;
		dayType = newDayType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.WEEK_DAY__DAY_TYPE, oldDayType, dayType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isDayWorking() {
		return dayWorking;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDayWorking(boolean newDayWorking) {
		boolean oldDayWorking = dayWorking;
		dayWorking = newDayWorking;
		boolean oldDayWorkingESet = dayWorkingESet;
		dayWorkingESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.WEEK_DAY__DAY_WORKING, oldDayWorking, dayWorking, !oldDayWorkingESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetDayWorking() {
		boolean oldDayWorking = dayWorking;
		boolean oldDayWorkingESet = dayWorkingESet;
		dayWorking = DAY_WORKING_EDEFAULT;
		dayWorkingESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.WEEK_DAY__DAY_WORKING, oldDayWorking, DAY_WORKING_EDEFAULT, oldDayWorkingESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetDayWorking() {
		return dayWorkingESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TimePeriod getTimePeriod() {
		return timePeriod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTimePeriod(TimePeriod newTimePeriod, NotificationChain msgs) {
		TimePeriod oldTimePeriod = timePeriod;
		timePeriod = newTimePeriod;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MsprojectPackage.WEEK_DAY__TIME_PERIOD, oldTimePeriod, newTimePeriod);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTimePeriod(TimePeriod newTimePeriod) {
		if (newTimePeriod != timePeriod) {
			NotificationChain msgs = null;
			if (timePeriod != null)
				msgs = ((InternalEObject)timePeriod).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MsprojectPackage.WEEK_DAY__TIME_PERIOD, null, msgs);
			if (newTimePeriod != null)
				msgs = ((InternalEObject)newTimePeriod).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MsprojectPackage.WEEK_DAY__TIME_PERIOD, null, msgs);
			msgs = basicSetTimePeriod(newTimePeriod, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.WEEK_DAY__TIME_PERIOD, newTimePeriod, newTimePeriod));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WorkingTimes getWorkingTimes() {
		return workingTimes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetWorkingTimes(WorkingTimes newWorkingTimes, NotificationChain msgs) {
		WorkingTimes oldWorkingTimes = workingTimes;
		workingTimes = newWorkingTimes;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MsprojectPackage.WEEK_DAY__WORKING_TIMES, oldWorkingTimes, newWorkingTimes);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWorkingTimes(WorkingTimes newWorkingTimes) {
		if (newWorkingTimes != workingTimes) {
			NotificationChain msgs = null;
			if (workingTimes != null)
				msgs = ((InternalEObject)workingTimes).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MsprojectPackage.WEEK_DAY__WORKING_TIMES, null, msgs);
			if (newWorkingTimes != null)
				msgs = ((InternalEObject)newWorkingTimes).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MsprojectPackage.WEEK_DAY__WORKING_TIMES, null, msgs);
			msgs = basicSetWorkingTimes(newWorkingTimes, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.WEEK_DAY__WORKING_TIMES, newWorkingTimes, newWorkingTimes));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case MsprojectPackage.WEEK_DAY__TIME_PERIOD:
					return basicSetTimePeriod(null, msgs);
				case MsprojectPackage.WEEK_DAY__WORKING_TIMES:
					return basicSetWorkingTimes(null, msgs);
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
			case MsprojectPackage.WEEK_DAY__DAY_TYPE:
				return getDayType();
			case MsprojectPackage.WEEK_DAY__DAY_WORKING:
				return isDayWorking() ? Boolean.TRUE : Boolean.FALSE;
			case MsprojectPackage.WEEK_DAY__TIME_PERIOD:
				return getTimePeriod();
			case MsprojectPackage.WEEK_DAY__WORKING_TIMES:
				return getWorkingTimes();
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
			case MsprojectPackage.WEEK_DAY__DAY_TYPE:
				setDayType((BigInteger)newValue);
				return;
			case MsprojectPackage.WEEK_DAY__DAY_WORKING:
				setDayWorking(((Boolean)newValue).booleanValue());
				return;
			case MsprojectPackage.WEEK_DAY__TIME_PERIOD:
				setTimePeriod((TimePeriod)newValue);
				return;
			case MsprojectPackage.WEEK_DAY__WORKING_TIMES:
				setWorkingTimes((WorkingTimes)newValue);
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
			case MsprojectPackage.WEEK_DAY__DAY_TYPE:
				setDayType(DAY_TYPE_EDEFAULT);
				return;
			case MsprojectPackage.WEEK_DAY__DAY_WORKING:
				unsetDayWorking();
				return;
			case MsprojectPackage.WEEK_DAY__TIME_PERIOD:
				setTimePeriod((TimePeriod)null);
				return;
			case MsprojectPackage.WEEK_DAY__WORKING_TIMES:
				setWorkingTimes((WorkingTimes)null);
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
			case MsprojectPackage.WEEK_DAY__DAY_TYPE:
				return DAY_TYPE_EDEFAULT == null ? dayType != null : !DAY_TYPE_EDEFAULT.equals(dayType);
			case MsprojectPackage.WEEK_DAY__DAY_WORKING:
				return isSetDayWorking();
			case MsprojectPackage.WEEK_DAY__TIME_PERIOD:
				return timePeriod != null;
			case MsprojectPackage.WEEK_DAY__WORKING_TIMES:
				return workingTimes != null;
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
		result.append(" (dayType: ");
		result.append(dayType);
		result.append(", dayWorking: ");
		if (dayWorkingESet) result.append(dayWorking); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //WeekDayImpl
