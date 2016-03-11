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
package org.eclipse.epf.msproject;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Rate</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.msproject.Rate#getRatesFrom <em>Rates From</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Rate#getRatesTo <em>Rates To</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Rate#getRateTable <em>Rate Table</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Rate#getStandardRate <em>Standard Rate</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Rate#getStandardRateFormat <em>Standard Rate Format</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Rate#getOvertimeRate <em>Overtime Rate</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Rate#getOvertimeRateFormat <em>Overtime Rate Format</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Rate#getCostPerUse <em>Cost Per Use</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.msproject.MsprojectPackage#getRate()
 * @model extendedMetaData="name='Rate_._type' kind='elementOnly'"
 * @generated
 */
public interface Rate extends EObject {
	/**
	 * Returns the value of the '<em><b>Rates From</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The date that the rate becomes effective.
	 *                                                         
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Rates From</em>' attribute.
	 * @see #setRatesFrom(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getRate_RatesFrom()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.DateTime" required="true"
	 *        extendedMetaData="kind='element' name='RatesFrom' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getRatesFrom();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Rate#getRatesFrom <em>Rates From</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rates From</em>' attribute.
	 * @see #getRatesFrom()
	 * @generated
	 */
	void setRatesFrom(Object value);

	/**
	 * Returns the value of the '<em><b>Rates To</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The last date that the rate is effective.
	 *                                                         
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Rates To</em>' attribute.
	 * @see #setRatesTo(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getRate_RatesTo()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.DateTime" required="true"
	 *        extendedMetaData="kind='element' name='RatesTo' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getRatesTo();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Rate#getRatesTo <em>Rates To</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rates To</em>' attribute.
	 * @see #getRatesTo()
	 * @generated
	 */
	void setRatesTo(Object value);

	/**
	 * Returns the value of the '<em><b>Rate Table</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The unique identifier of the rate table for the resource. 
	 *                                                         Values are: 0=A, 1=B, 2=C, 3=D, 4=E
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Rate Table</em>' attribute.
	 * @see #setRateTable(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getRate_RateTable()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.RateTableType"
	 *        extendedMetaData="kind='element' name='RateTable' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getRateTable();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Rate#getRateTable <em>Rate Table</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rate Table</em>' attribute.
	 * @see #getRateTable()
	 * @generated
	 */
	void setRateTable(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Standard Rate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The standard rate for the resource for the period specified.
	 *                                                         
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Standard Rate</em>' attribute.
	 * @see #setStandardRate(BigDecimal)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getRate_StandardRate()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Decimal"
	 *        extendedMetaData="kind='element' name='StandardRate' namespace='##targetNamespace'"
	 * @generated
	 */
	BigDecimal getStandardRate();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Rate#getStandardRate <em>Standard Rate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Standard Rate</em>' attribute.
	 * @see #getStandardRate()
	 * @generated
	 */
	void setStandardRate(BigDecimal value);

	/**
	 * Returns the value of the '<em><b>Standard Rate Format</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The units used by Microsoft Project to display the standard rate.  
	 *                                                         1=m, 2=h, 3=d, 4=w, 5=mo, 7=y
	 *                                                         
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Standard Rate Format</em>' attribute.
	 * @see #setStandardRateFormat(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getRate_StandardRateFormat()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.StandardRateFormatType"
	 *        extendedMetaData="kind='element' name='StandardRateFormat' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getStandardRateFormat();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Rate#getStandardRateFormat <em>Standard Rate Format</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Standard Rate Format</em>' attribute.
	 * @see #getStandardRateFormat()
	 * @generated
	 */
	void setStandardRateFormat(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Overtime Rate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The overtime rate for the resource for the period specified.
	 *                                                         
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Overtime Rate</em>' attribute.
	 * @see #setOvertimeRate(BigDecimal)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getRate_OvertimeRate()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Decimal"
	 *        extendedMetaData="kind='element' name='OvertimeRate' namespace='##targetNamespace'"
	 * @generated
	 */
	BigDecimal getOvertimeRate();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Rate#getOvertimeRate <em>Overtime Rate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Overtime Rate</em>' attribute.
	 * @see #getOvertimeRate()
	 * @generated
	 */
	void setOvertimeRate(BigDecimal value);

	/**
	 * Returns the value of the '<em><b>Overtime Rate Format</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The units used by Microsoft Project to display the overtime rate.  
	 *                                                         1=m, 2=h, 3=d, 4=w, 5=mo, 7=y
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Overtime Rate Format</em>' attribute.
	 * @see #setOvertimeRateFormat(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getRate_OvertimeRateFormat()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.OvertimeRateFormatType"
	 *        extendedMetaData="kind='element' name='OvertimeRateFormat' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getOvertimeRateFormat();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Rate#getOvertimeRateFormat <em>Overtime Rate Format</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Overtime Rate Format</em>' attribute.
	 * @see #getOvertimeRateFormat()
	 * @generated
	 */
	void setOvertimeRateFormat(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Cost Per Use</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The cost per use of the resource. 
	 *                                                         This value is as of the current date if a rate table exists for the resource.
	 *                                                         
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Cost Per Use</em>' attribute.
	 * @see #setCostPerUse(BigDecimal)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getRate_CostPerUse()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Decimal"
	 *        extendedMetaData="kind='element' name='CostPerUse' namespace='##targetNamespace'"
	 * @generated
	 */
	BigDecimal getCostPerUse();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Rate#getCostPerUse <em>Cost Per Use</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cost Per Use</em>' attribute.
	 * @see #getCostPerUse()
	 * @generated
	 */
	void setCostPerUse(BigDecimal value);

} // Rate
