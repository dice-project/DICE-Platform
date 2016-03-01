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
 * A representation of the model object '<em><b>Calendar</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.msproject.Calendar#getUID <em>UID</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Calendar#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Calendar#isIsBaseCalendar <em>Is Base Calendar</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Calendar#getBaseCalendarUID <em>Base Calendar UID</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Calendar#getWeekDays <em>Week Days</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.msproject.MsprojectPackage#getCalendar()
 * @model extendedMetaData="name='Calendar_._type' kind='elementOnly'"
 * @generated
 */
public interface Calendar extends EObject {
	/**
	 * Returns the value of the '<em><b>UID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The unique identifier of the calendar.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>UID</em>' attribute.
	 * @see #setUID(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getCalendar_UID()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Integer" required="true"
	 *        extendedMetaData="kind='element' name='UID' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getUID();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Calendar#getUID <em>UID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>UID</em>' attribute.
	 * @see #getUID()
	 * @generated
	 */
	void setUID(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The name of the calendar.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getCalendar_Name()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.NameType"
	 *        extendedMetaData="kind='element' name='Name' namespace='##targetNamespace'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Calendar#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Is Base Calendar</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Whether the calendar is a base calendar.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is Base Calendar</em>' attribute.
	 * @see #isSetIsBaseCalendar()
	 * @see #unsetIsBaseCalendar()
	 * @see #setIsBaseCalendar(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getCalendar_IsBaseCalendar()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='IsBaseCalendar' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isIsBaseCalendar();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Calendar#isIsBaseCalendar <em>Is Base Calendar</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Base Calendar</em>' attribute.
	 * @see #isSetIsBaseCalendar()
	 * @see #unsetIsBaseCalendar()
	 * @see #isIsBaseCalendar()
	 * @generated
	 */
	void setIsBaseCalendar(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Calendar#isIsBaseCalendar <em>Is Base Calendar</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetIsBaseCalendar()
	 * @see #isIsBaseCalendar()
	 * @see #setIsBaseCalendar(boolean)
	 * @generated
	 */
	void unsetIsBaseCalendar();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Calendar#isIsBaseCalendar <em>Is Base Calendar</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Is Base Calendar</em>' attribute is set.
	 * @see #unsetIsBaseCalendar()
	 * @see #isIsBaseCalendar()
	 * @see #setIsBaseCalendar(boolean)
	 * @generated
	 */
	boolean isSetIsBaseCalendar();

	/**
	 * Returns the value of the '<em><b>Base Calendar UID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The unique identifier of the base calendar 
	 *                                 on which this calendar depends. Only applicable if the calendar is 
	 *                                 not a base calendar.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Base Calendar UID</em>' attribute.
	 * @see #setBaseCalendarUID(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getCalendar_BaseCalendarUID()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Integer"
	 *        extendedMetaData="kind='element' name='BaseCalendarUID' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getBaseCalendarUID();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Calendar#getBaseCalendarUID <em>Base Calendar UID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Base Calendar UID</em>' attribute.
	 * @see #getBaseCalendarUID()
	 * @generated
	 */
	void setBaseCalendarUID(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Week Days</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The collection of Weekdays that defines this calendar.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Week Days</em>' containment reference.
	 * @see #setWeekDays(WeekDays)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getCalendar_WeekDays()
	 * @model containment="true" resolveProxies="false"
	 *        extendedMetaData="kind='element' name='WeekDays' namespace='##targetNamespace'"
	 * @generated
	 */
	WeekDays getWeekDays();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Calendar#getWeekDays <em>Week Days</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Week Days</em>' containment reference.
	 * @see #getWeekDays()
	 * @generated
	 */
	void setWeekDays(WeekDays value);

} // Calendar
