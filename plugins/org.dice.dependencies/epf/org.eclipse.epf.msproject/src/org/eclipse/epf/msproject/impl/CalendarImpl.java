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
import org.eclipse.epf.msproject.Calendar;
import org.eclipse.epf.msproject.MsprojectPackage;
import org.eclipse.epf.msproject.WeekDays;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Calendar</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.msproject.impl.CalendarImpl#getUID <em>UID</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.CalendarImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.CalendarImpl#isIsBaseCalendar <em>Is Base Calendar</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.CalendarImpl#getBaseCalendarUID <em>Base Calendar UID</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.CalendarImpl#getWeekDays <em>Week Days</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CalendarImpl extends EObjectImpl implements Calendar {
	/**
	 * The default value of the '{@link #getUID() <em>UID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUID()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger UID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUID() <em>UID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUID()
	 * @generated
	 * @ordered
	 */
	protected BigInteger uID = UID_EDEFAULT;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #isIsBaseCalendar() <em>Is Base Calendar</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsBaseCalendar()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_BASE_CALENDAR_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsBaseCalendar() <em>Is Base Calendar</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsBaseCalendar()
	 * @generated
	 * @ordered
	 */
	protected boolean isBaseCalendar = IS_BASE_CALENDAR_EDEFAULT;

	/**
	 * This is true if the Is Base Calendar attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean isBaseCalendarESet = false;

	/**
	 * The default value of the '{@link #getBaseCalendarUID() <em>Base Calendar UID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBaseCalendarUID()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger BASE_CALENDAR_UID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBaseCalendarUID() <em>Base Calendar UID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBaseCalendarUID()
	 * @generated
	 * @ordered
	 */
	protected BigInteger baseCalendarUID = BASE_CALENDAR_UID_EDEFAULT;

	/**
	 * The cached value of the '{@link #getWeekDays() <em>Week Days</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWeekDays()
	 * @generated
	 * @ordered
	 */
	protected WeekDays weekDays = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CalendarImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return MsprojectPackage.eINSTANCE.getCalendar();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getUID() {
		return uID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUID(BigInteger newUID) {
		BigInteger oldUID = uID;
		uID = newUID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.CALENDAR__UID, oldUID, uID));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.CALENDAR__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsBaseCalendar() {
		return isBaseCalendar;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsBaseCalendar(boolean newIsBaseCalendar) {
		boolean oldIsBaseCalendar = isBaseCalendar;
		isBaseCalendar = newIsBaseCalendar;
		boolean oldIsBaseCalendarESet = isBaseCalendarESet;
		isBaseCalendarESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.CALENDAR__IS_BASE_CALENDAR, oldIsBaseCalendar, isBaseCalendar, !oldIsBaseCalendarESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetIsBaseCalendar() {
		boolean oldIsBaseCalendar = isBaseCalendar;
		boolean oldIsBaseCalendarESet = isBaseCalendarESet;
		isBaseCalendar = IS_BASE_CALENDAR_EDEFAULT;
		isBaseCalendarESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.CALENDAR__IS_BASE_CALENDAR, oldIsBaseCalendar, IS_BASE_CALENDAR_EDEFAULT, oldIsBaseCalendarESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetIsBaseCalendar() {
		return isBaseCalendarESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getBaseCalendarUID() {
		return baseCalendarUID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBaseCalendarUID(BigInteger newBaseCalendarUID) {
		BigInteger oldBaseCalendarUID = baseCalendarUID;
		baseCalendarUID = newBaseCalendarUID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.CALENDAR__BASE_CALENDAR_UID, oldBaseCalendarUID, baseCalendarUID));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WeekDays getWeekDays() {
		return weekDays;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetWeekDays(WeekDays newWeekDays, NotificationChain msgs) {
		WeekDays oldWeekDays = weekDays;
		weekDays = newWeekDays;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MsprojectPackage.CALENDAR__WEEK_DAYS, oldWeekDays, newWeekDays);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWeekDays(WeekDays newWeekDays) {
		if (newWeekDays != weekDays) {
			NotificationChain msgs = null;
			if (weekDays != null)
				msgs = ((InternalEObject)weekDays).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MsprojectPackage.CALENDAR__WEEK_DAYS, null, msgs);
			if (newWeekDays != null)
				msgs = ((InternalEObject)newWeekDays).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MsprojectPackage.CALENDAR__WEEK_DAYS, null, msgs);
			msgs = basicSetWeekDays(newWeekDays, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.CALENDAR__WEEK_DAYS, newWeekDays, newWeekDays));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case MsprojectPackage.CALENDAR__WEEK_DAYS:
					return basicSetWeekDays(null, msgs);
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
			case MsprojectPackage.CALENDAR__UID:
				return getUID();
			case MsprojectPackage.CALENDAR__NAME:
				return getName();
			case MsprojectPackage.CALENDAR__IS_BASE_CALENDAR:
				return isIsBaseCalendar() ? Boolean.TRUE : Boolean.FALSE;
			case MsprojectPackage.CALENDAR__BASE_CALENDAR_UID:
				return getBaseCalendarUID();
			case MsprojectPackage.CALENDAR__WEEK_DAYS:
				return getWeekDays();
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
			case MsprojectPackage.CALENDAR__UID:
				setUID((BigInteger)newValue);
				return;
			case MsprojectPackage.CALENDAR__NAME:
				setName((String)newValue);
				return;
			case MsprojectPackage.CALENDAR__IS_BASE_CALENDAR:
				setIsBaseCalendar(((Boolean)newValue).booleanValue());
				return;
			case MsprojectPackage.CALENDAR__BASE_CALENDAR_UID:
				setBaseCalendarUID((BigInteger)newValue);
				return;
			case MsprojectPackage.CALENDAR__WEEK_DAYS:
				setWeekDays((WeekDays)newValue);
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
			case MsprojectPackage.CALENDAR__UID:
				setUID(UID_EDEFAULT);
				return;
			case MsprojectPackage.CALENDAR__NAME:
				setName(NAME_EDEFAULT);
				return;
			case MsprojectPackage.CALENDAR__IS_BASE_CALENDAR:
				unsetIsBaseCalendar();
				return;
			case MsprojectPackage.CALENDAR__BASE_CALENDAR_UID:
				setBaseCalendarUID(BASE_CALENDAR_UID_EDEFAULT);
				return;
			case MsprojectPackage.CALENDAR__WEEK_DAYS:
				setWeekDays((WeekDays)null);
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
			case MsprojectPackage.CALENDAR__UID:
				return UID_EDEFAULT == null ? uID != null : !UID_EDEFAULT.equals(uID);
			case MsprojectPackage.CALENDAR__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case MsprojectPackage.CALENDAR__IS_BASE_CALENDAR:
				return isSetIsBaseCalendar();
			case MsprojectPackage.CALENDAR__BASE_CALENDAR_UID:
				return BASE_CALENDAR_UID_EDEFAULT == null ? baseCalendarUID != null : !BASE_CALENDAR_UID_EDEFAULT.equals(baseCalendarUID);
			case MsprojectPackage.CALENDAR__WEEK_DAYS:
				return weekDays != null;
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
		result.append(" (uID: ");
		result.append(uID);
		result.append(", name: ");
		result.append(name);
		result.append(", isBaseCalendar: ");
		if (isBaseCalendarESet) result.append(isBaseCalendar); else result.append("<unset>");
		result.append(", baseCalendarUID: ");
		result.append(baseCalendarUID);
		result.append(')');
		return result.toString();
	}

} //CalendarImpl
