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
package org.eclipse.epf.xml.uma;

import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Planning Data</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A Process Element that adds planning data to Breakdown Elements when it is used for a Process Planning Template.  For Delivery Processes and Capability Patterns this class can either not be instantiated or populated with default data.
 * Planning Data factors out specific optional data needed for representing planning templates.  This association allows to access planning data if it is stored for the Breakdown Element.
 * (NOTE, THE ATTRIBUTES FOR THIS CLASS ARE NOT COMPLETE, YET)
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.xml.uma.PlanningData#getFinishDate <em>Finish Date</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.PlanningData#getRank <em>Rank</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.PlanningData#getStartDate <em>Start Date</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.xml.uma.UmaPackage#getPlanningData()
 * @model extendedMetaData="name='PlanningData' kind='elementOnly'"
 * @generated
 */
public interface PlanningData extends ProcessElement {
	/**
	 * Returns the value of the '<em><b>Finish Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The default finish date for a planed Task.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Finish Date</em>' attribute.
	 * @see #setFinishDate(XMLGregorianCalendar)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getPlanningData_FinishDate()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.DateTime"
	 *        extendedMetaData="kind='attribute' name='finishDate'"
	 * @generated
	 */
	XMLGregorianCalendar getFinishDate();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.PlanningData#getFinishDate <em>Finish Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Finish Date</em>' attribute.
	 * @see #getFinishDate()
	 * @generated
	 */
	void setFinishDate(XMLGregorianCalendar value);

	/**
	 * Returns the value of the '<em><b>Rank</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The default rank for a planed Task.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Rank</em>' attribute.
	 * @see #setRank(String)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getPlanningData_Rank()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='rank'"
	 * @generated
	 */
	String getRank();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.PlanningData#getRank <em>Rank</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rank</em>' attribute.
	 * @see #getRank()
	 * @generated
	 */
	void setRank(String value);

	/**
	 * Returns the value of the '<em><b>Start Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The default start date for a planed Task.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Start Date</em>' attribute.
	 * @see #setStartDate(XMLGregorianCalendar)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getPlanningData_StartDate()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.DateTime"
	 *        extendedMetaData="kind='attribute' name='startDate'"
	 * @generated
	 */
	XMLGregorianCalendar getStartDate();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.PlanningData#getStartDate <em>Start Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Start Date</em>' attribute.
	 * @see #getStartDate()
	 * @generated
	 */
	void setStartDate(XMLGregorianCalendar value);

} // PlanningData
