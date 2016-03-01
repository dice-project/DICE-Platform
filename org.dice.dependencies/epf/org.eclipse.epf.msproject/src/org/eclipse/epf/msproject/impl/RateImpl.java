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

import java.math.BigDecimal;
import java.math.BigInteger;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.epf.msproject.MsprojectPackage;
import org.eclipse.epf.msproject.Rate;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Rate</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.msproject.impl.RateImpl#getRatesFrom <em>Rates From</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.RateImpl#getRatesTo <em>Rates To</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.RateImpl#getRateTable <em>Rate Table</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.RateImpl#getStandardRate <em>Standard Rate</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.RateImpl#getStandardRateFormat <em>Standard Rate Format</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.RateImpl#getOvertimeRate <em>Overtime Rate</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.RateImpl#getOvertimeRateFormat <em>Overtime Rate Format</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.RateImpl#getCostPerUse <em>Cost Per Use</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RateImpl extends EObjectImpl implements Rate {
	/**
	 * The default value of the '{@link #getRatesFrom() <em>Rates From</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRatesFrom()
	 * @generated
	 * @ordered
	 */
	protected static final Object RATES_FROM_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRatesFrom() <em>Rates From</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRatesFrom()
	 * @generated
	 * @ordered
	 */
	protected Object ratesFrom = RATES_FROM_EDEFAULT;

	/**
	 * The default value of the '{@link #getRatesTo() <em>Rates To</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRatesTo()
	 * @generated
	 * @ordered
	 */
	protected static final Object RATES_TO_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRatesTo() <em>Rates To</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRatesTo()
	 * @generated
	 * @ordered
	 */
	protected Object ratesTo = RATES_TO_EDEFAULT;

	/**
	 * The default value of the '{@link #getRateTable() <em>Rate Table</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRateTable()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger RATE_TABLE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRateTable() <em>Rate Table</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRateTable()
	 * @generated
	 * @ordered
	 */
	protected BigInteger rateTable = RATE_TABLE_EDEFAULT;

	/**
	 * The default value of the '{@link #getStandardRate() <em>Standard Rate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStandardRate()
	 * @generated
	 * @ordered
	 */
	protected static final BigDecimal STANDARD_RATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStandardRate() <em>Standard Rate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStandardRate()
	 * @generated
	 * @ordered
	 */
	protected BigDecimal standardRate = STANDARD_RATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getStandardRateFormat() <em>Standard Rate Format</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStandardRateFormat()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger STANDARD_RATE_FORMAT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStandardRateFormat() <em>Standard Rate Format</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStandardRateFormat()
	 * @generated
	 * @ordered
	 */
	protected BigInteger standardRateFormat = STANDARD_RATE_FORMAT_EDEFAULT;

	/**
	 * The default value of the '{@link #getOvertimeRate() <em>Overtime Rate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOvertimeRate()
	 * @generated
	 * @ordered
	 */
	protected static final BigDecimal OVERTIME_RATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOvertimeRate() <em>Overtime Rate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOvertimeRate()
	 * @generated
	 * @ordered
	 */
	protected BigDecimal overtimeRate = OVERTIME_RATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getOvertimeRateFormat() <em>Overtime Rate Format</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOvertimeRateFormat()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger OVERTIME_RATE_FORMAT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOvertimeRateFormat() <em>Overtime Rate Format</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOvertimeRateFormat()
	 * @generated
	 * @ordered
	 */
	protected BigInteger overtimeRateFormat = OVERTIME_RATE_FORMAT_EDEFAULT;

	/**
	 * The default value of the '{@link #getCostPerUse() <em>Cost Per Use</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCostPerUse()
	 * @generated
	 * @ordered
	 */
	protected static final BigDecimal COST_PER_USE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCostPerUse() <em>Cost Per Use</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCostPerUse()
	 * @generated
	 * @ordered
	 */
	protected BigDecimal costPerUse = COST_PER_USE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RateImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return MsprojectPackage.eINSTANCE.getRate();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getRatesFrom() {
		return ratesFrom;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRatesFrom(Object newRatesFrom) {
		Object oldRatesFrom = ratesFrom;
		ratesFrom = newRatesFrom;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RATE__RATES_FROM, oldRatesFrom, ratesFrom));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getRatesTo() {
		return ratesTo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRatesTo(Object newRatesTo) {
		Object oldRatesTo = ratesTo;
		ratesTo = newRatesTo;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RATE__RATES_TO, oldRatesTo, ratesTo));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getRateTable() {
		return rateTable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRateTable(BigInteger newRateTable) {
		BigInteger oldRateTable = rateTable;
		rateTable = newRateTable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RATE__RATE_TABLE, oldRateTable, rateTable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigDecimal getStandardRate() {
		return standardRate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStandardRate(BigDecimal newStandardRate) {
		BigDecimal oldStandardRate = standardRate;
		standardRate = newStandardRate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RATE__STANDARD_RATE, oldStandardRate, standardRate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getStandardRateFormat() {
		return standardRateFormat;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStandardRateFormat(BigInteger newStandardRateFormat) {
		BigInteger oldStandardRateFormat = standardRateFormat;
		standardRateFormat = newStandardRateFormat;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RATE__STANDARD_RATE_FORMAT, oldStandardRateFormat, standardRateFormat));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigDecimal getOvertimeRate() {
		return overtimeRate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOvertimeRate(BigDecimal newOvertimeRate) {
		BigDecimal oldOvertimeRate = overtimeRate;
		overtimeRate = newOvertimeRate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RATE__OVERTIME_RATE, oldOvertimeRate, overtimeRate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getOvertimeRateFormat() {
		return overtimeRateFormat;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOvertimeRateFormat(BigInteger newOvertimeRateFormat) {
		BigInteger oldOvertimeRateFormat = overtimeRateFormat;
		overtimeRateFormat = newOvertimeRateFormat;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RATE__OVERTIME_RATE_FORMAT, oldOvertimeRateFormat, overtimeRateFormat));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigDecimal getCostPerUse() {
		return costPerUse;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCostPerUse(BigDecimal newCostPerUse) {
		BigDecimal oldCostPerUse = costPerUse;
		costPerUse = newCostPerUse;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RATE__COST_PER_USE, oldCostPerUse, costPerUse));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case MsprojectPackage.RATE__RATES_FROM:
				return getRatesFrom();
			case MsprojectPackage.RATE__RATES_TO:
				return getRatesTo();
			case MsprojectPackage.RATE__RATE_TABLE:
				return getRateTable();
			case MsprojectPackage.RATE__STANDARD_RATE:
				return getStandardRate();
			case MsprojectPackage.RATE__STANDARD_RATE_FORMAT:
				return getStandardRateFormat();
			case MsprojectPackage.RATE__OVERTIME_RATE:
				return getOvertimeRate();
			case MsprojectPackage.RATE__OVERTIME_RATE_FORMAT:
				return getOvertimeRateFormat();
			case MsprojectPackage.RATE__COST_PER_USE:
				return getCostPerUse();
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
			case MsprojectPackage.RATE__RATES_FROM:
				setRatesFrom((Object)newValue);
				return;
			case MsprojectPackage.RATE__RATES_TO:
				setRatesTo((Object)newValue);
				return;
			case MsprojectPackage.RATE__RATE_TABLE:
				setRateTable((BigInteger)newValue);
				return;
			case MsprojectPackage.RATE__STANDARD_RATE:
				setStandardRate((BigDecimal)newValue);
				return;
			case MsprojectPackage.RATE__STANDARD_RATE_FORMAT:
				setStandardRateFormat((BigInteger)newValue);
				return;
			case MsprojectPackage.RATE__OVERTIME_RATE:
				setOvertimeRate((BigDecimal)newValue);
				return;
			case MsprojectPackage.RATE__OVERTIME_RATE_FORMAT:
				setOvertimeRateFormat((BigInteger)newValue);
				return;
			case MsprojectPackage.RATE__COST_PER_USE:
				setCostPerUse((BigDecimal)newValue);
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
			case MsprojectPackage.RATE__RATES_FROM:
				setRatesFrom(RATES_FROM_EDEFAULT);
				return;
			case MsprojectPackage.RATE__RATES_TO:
				setRatesTo(RATES_TO_EDEFAULT);
				return;
			case MsprojectPackage.RATE__RATE_TABLE:
				setRateTable(RATE_TABLE_EDEFAULT);
				return;
			case MsprojectPackage.RATE__STANDARD_RATE:
				setStandardRate(STANDARD_RATE_EDEFAULT);
				return;
			case MsprojectPackage.RATE__STANDARD_RATE_FORMAT:
				setStandardRateFormat(STANDARD_RATE_FORMAT_EDEFAULT);
				return;
			case MsprojectPackage.RATE__OVERTIME_RATE:
				setOvertimeRate(OVERTIME_RATE_EDEFAULT);
				return;
			case MsprojectPackage.RATE__OVERTIME_RATE_FORMAT:
				setOvertimeRateFormat(OVERTIME_RATE_FORMAT_EDEFAULT);
				return;
			case MsprojectPackage.RATE__COST_PER_USE:
				setCostPerUse(COST_PER_USE_EDEFAULT);
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
			case MsprojectPackage.RATE__RATES_FROM:
				return RATES_FROM_EDEFAULT == null ? ratesFrom != null : !RATES_FROM_EDEFAULT.equals(ratesFrom);
			case MsprojectPackage.RATE__RATES_TO:
				return RATES_TO_EDEFAULT == null ? ratesTo != null : !RATES_TO_EDEFAULT.equals(ratesTo);
			case MsprojectPackage.RATE__RATE_TABLE:
				return RATE_TABLE_EDEFAULT == null ? rateTable != null : !RATE_TABLE_EDEFAULT.equals(rateTable);
			case MsprojectPackage.RATE__STANDARD_RATE:
				return STANDARD_RATE_EDEFAULT == null ? standardRate != null : !STANDARD_RATE_EDEFAULT.equals(standardRate);
			case MsprojectPackage.RATE__STANDARD_RATE_FORMAT:
				return STANDARD_RATE_FORMAT_EDEFAULT == null ? standardRateFormat != null : !STANDARD_RATE_FORMAT_EDEFAULT.equals(standardRateFormat);
			case MsprojectPackage.RATE__OVERTIME_RATE:
				return OVERTIME_RATE_EDEFAULT == null ? overtimeRate != null : !OVERTIME_RATE_EDEFAULT.equals(overtimeRate);
			case MsprojectPackage.RATE__OVERTIME_RATE_FORMAT:
				return OVERTIME_RATE_FORMAT_EDEFAULT == null ? overtimeRateFormat != null : !OVERTIME_RATE_FORMAT_EDEFAULT.equals(overtimeRateFormat);
			case MsprojectPackage.RATE__COST_PER_USE:
				return COST_PER_USE_EDEFAULT == null ? costPerUse != null : !COST_PER_USE_EDEFAULT.equals(costPerUse);
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
		result.append(" (ratesFrom: ");
		result.append(ratesFrom);
		result.append(", ratesTo: ");
		result.append(ratesTo);
		result.append(", rateTable: ");
		result.append(rateTable);
		result.append(", standardRate: ");
		result.append(standardRate);
		result.append(", standardRateFormat: ");
		result.append(standardRateFormat);
		result.append(", overtimeRate: ");
		result.append(overtimeRate);
		result.append(", overtimeRateFormat: ");
		result.append(overtimeRateFormat);
		result.append(", costPerUse: ");
		result.append(costPerUse);
		result.append(')');
		return result.toString();
	}

} //RateImpl
