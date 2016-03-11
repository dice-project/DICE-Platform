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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Time Period</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.msproject.TimePeriod#getFromDate <em>From Date</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.TimePeriod#getToDate <em>To Date</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.msproject.MsprojectPackage#getTimePeriod()
 * @model extendedMetaData="name='TimePeriod_._type' kind='elementOnly'"
 * @generated
 */
public interface TimePeriod extends EObject {
	/**
	 * Returns the value of the '<em><b>From Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The beginning of the exception time.
	 *                                                                      
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>From Date</em>' attribute.
	 * @see #setFromDate(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTimePeriod_FromDate()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.DateTime"
	 *        extendedMetaData="kind='element' name='FromDate' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getFromDate();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.TimePeriod#getFromDate <em>From Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>From Date</em>' attribute.
	 * @see #getFromDate()
	 * @generated
	 */
	void setFromDate(Object value);

	/**
	 * Returns the value of the '<em><b>To Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The end of the exception time.
	 *                                                                      
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>To Date</em>' attribute.
	 * @see #setToDate(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTimePeriod_ToDate()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.DateTime"
	 *        extendedMetaData="kind='element' name='ToDate' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getToDate();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.TimePeriod#getToDate <em>To Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>To Date</em>' attribute.
	 * @see #getToDate()
	 * @generated
	 */
	void setToDate(Object value);

} // TimePeriod
