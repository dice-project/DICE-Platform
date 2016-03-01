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
 * A representation of the model object '<em><b>Working Time</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.msproject.WorkingTime#getFromTime <em>From Time</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.WorkingTime#getToTime <em>To Time</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.msproject.MsprojectPackage#getWorkingTime()
 * @model extendedMetaData="name='WorkingTime_._type' kind='elementOnly'"
 * @generated
 */
public interface WorkingTime extends EObject {
	/**
	 * Returns the value of the '<em><b>From Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The beginning of the working time.
	 *                                                                                      
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>From Time</em>' attribute.
	 * @see #setFromTime(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getWorkingTime_FromTime()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Time"
	 *        extendedMetaData="kind='element' name='FromTime' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getFromTime();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.WorkingTime#getFromTime <em>From Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>From Time</em>' attribute.
	 * @see #getFromTime()
	 * @generated
	 */
	void setFromTime(Object value);

	/**
	 * Returns the value of the '<em><b>To Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The end of the working  time.
	 *                                                                                      
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>To Time</em>' attribute.
	 * @see #setToTime(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getWorkingTime_ToTime()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Time"
	 *        extendedMetaData="kind='element' name='ToTime' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getToTime();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.WorkingTime#getToTime <em>To Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>To Time</em>' attribute.
	 * @see #getToTime()
	 * @generated
	 */
	void setToTime(Object value);

} // WorkingTime
