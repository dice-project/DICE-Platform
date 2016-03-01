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
package org.eclipse.epf.uma;

import java.util.Date;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Planning Data</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Planning Data is a Method Element that adds planning data to Breakdown Elements when it is used for a Process Planning Template.  For Delivery Processes and Capability Patterns this class can either not be instantiated or populated with default data.
 * Planning Data factors out specific optional data needed for representing planning templates.  This association allows to access planning data if it is stored for the Breakdown Element.
 * (NOTE, THE ATTRIBUTES FOR THIS CLASS ARE NOT COMPLETE, YET)
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.PlanningData#getStartDate <em>Start Date</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.PlanningData#getFinishDate <em>Finish Date</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.PlanningData#getRank <em>Rank</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.uma.UmaPackage#getPlanningData()
 * @model
 * @generated
 */
public interface PlanningData extends ProcessElement {
	/**
	 * Returns the value of the '<em><b>Start Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The default start date for a planed Task.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Start Date</em>' attribute.
	 * @see #setStartDate(Date)
	 * @see org.eclipse.epf.uma.UmaPackage#getPlanningData_StartDate()
	 * @model dataType="org.eclipse.epf.uma.Date" required="true" ordered="false"
	 * @generated
	 */
	Date getStartDate();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.PlanningData#getStartDate <em>Start Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Start Date</em>' attribute.
	 * @see #getStartDate()
	 * @generated
	 */
	void setStartDate(Date value);

	/**
	 * Returns the value of the '<em><b>Finish Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The default finish date for a planed Task.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Finish Date</em>' attribute.
	 * @see #setFinishDate(Date)
	 * @see org.eclipse.epf.uma.UmaPackage#getPlanningData_FinishDate()
	 * @model dataType="org.eclipse.epf.uma.Date" required="true" ordered="false"
	 * @generated
	 */
	Date getFinishDate();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.PlanningData#getFinishDate <em>Finish Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Finish Date</em>' attribute.
	 * @see #getFinishDate()
	 * @generated
	 */
	void setFinishDate(Date value);

	/**
	 * Returns the value of the '<em><b>Rank</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The default rank for a planed Task.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Rank</em>' attribute.
	 * @see #setRank(int)
	 * @see org.eclipse.epf.uma.UmaPackage#getPlanningData_Rank()
	 * @model dataType="org.eclipse.epf.uma.Integer" required="true" ordered="false"
	 * @generated
	 */
	int getRank();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.PlanningData#getRank <em>Rank</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rank</em>' attribute.
	 * @see #getRank()
	 * @generated
	 */
	void setRank(int value);

} // PlanningData
