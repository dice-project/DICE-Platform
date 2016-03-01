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

import java.math.BigInteger;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Week Day</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.msproject.WeekDay#getDayType <em>Day Type</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.WeekDay#isDayWorking <em>Day Working</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.WeekDay#getTimePeriod <em>Time Period</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.WeekDay#getWorkingTimes <em>Working Times</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.msproject.MsprojectPackage#getWeekDay()
 * @model extendedMetaData="name='WeekDay_._type' kind='elementOnly'"
 * @generated
 */
public interface WeekDay extends EObject {
	/**
	 * Returns the value of the '<em><b>Day Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The type of day. 
	 *                                                          Values are: 0=Exception, 1=Monday, 2=Tuesday, 
	 *                                                          3=Wednesday, 4=Thursday, 5=Friday, 6=Saturday, 
	 *                                                          7=Sunday
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Day Type</em>' attribute.
	 * @see #setDayType(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getWeekDay_DayType()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.DayTypeType" required="true"
	 *        extendedMetaData="kind='element' name='DayType' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getDayType();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.WeekDay#getDayType <em>Day Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Day Type</em>' attribute.
	 * @see #getDayType()
	 * @generated
	 */
	void setDayType(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Day Working</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Whether the specified date or day 
	 *                                                          type is working.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Day Working</em>' attribute.
	 * @see #isSetDayWorking()
	 * @see #unsetDayWorking()
	 * @see #setDayWorking(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getWeekDay_DayWorking()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='DayWorking' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isDayWorking();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.WeekDay#isDayWorking <em>Day Working</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Day Working</em>' attribute.
	 * @see #isSetDayWorking()
	 * @see #unsetDayWorking()
	 * @see #isDayWorking()
	 * @generated
	 */
	void setDayWorking(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.WeekDay#isDayWorking <em>Day Working</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetDayWorking()
	 * @see #isDayWorking()
	 * @see #setDayWorking(boolean)
	 * @generated
	 */
	void unsetDayWorking();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.WeekDay#isDayWorking <em>Day Working</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Day Working</em>' attribute is set.
	 * @see #unsetDayWorking()
	 * @see #isDayWorking()
	 * @see #setDayWorking(boolean)
	 * @generated
	 */
	boolean isSetDayWorking();

	/**
	 * Returns the value of the '<em><b>Time Period</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Defines a contiguous set of exception days.
	 *                                                          
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Time Period</em>' containment reference.
	 * @see #setTimePeriod(TimePeriod)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getWeekDay_TimePeriod()
	 * @model containment="true" resolveProxies="false"
	 *        extendedMetaData="kind='element' name='TimePeriod' namespace='##targetNamespace'"
	 * @generated
	 */
	TimePeriod getTimePeriod();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.WeekDay#getTimePeriod <em>Time Period</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Time Period</em>' containment reference.
	 * @see #getTimePeriod()
	 * @generated
	 */
	void setTimePeriod(TimePeriod value);

	/**
	 * Returns the value of the '<em><b>Working Times</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The collection of working times that define the time worked 
	 *                                                          on the weekday.  One of these must be present, and there may be no more than five.
	 *                                                          
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Working Times</em>' containment reference.
	 * @see #setWorkingTimes(WorkingTimes)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getWeekDay_WorkingTimes()
	 * @model containment="true" resolveProxies="false"
	 *        extendedMetaData="kind='element' name='WorkingTimes' namespace='##targetNamespace'"
	 * @generated
	 */
	WorkingTimes getWorkingTimes();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.WeekDay#getWorkingTimes <em>Working Times</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Working Times</em>' containment reference.
	 * @see #getWorkingTimes()
	 * @generated
	 */
	void setWorkingTimes(WorkingTimes value);

} // WeekDay
