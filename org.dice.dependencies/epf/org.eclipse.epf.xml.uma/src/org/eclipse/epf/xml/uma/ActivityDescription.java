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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Activity Description</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A generalized Breakdown Element Description that is used to store the textual description for an Activity.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.xml.uma.ActivityDescription#getAlternatives <em>Alternatives</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.ActivityDescription#getHowToStaff <em>How To Staff</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.ActivityDescription#getPurpose <em>Purpose</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.xml.uma.UmaPackage#getActivityDescription()
 * @model extendedMetaData="name='ActivityDescription' kind='elementOnly'"
 * @generated
 */
public interface ActivityDescription extends BreakdownElementDescription {
	/**
	 * Returns the value of the '<em><b>Alternatives</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Comprises of summaries describing important exceptional and non-standard ways of doing the work of this Activity not covered by the Activity's Tasks.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Alternatives</em>' attribute.
	 * @see #setAlternatives(String)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getActivityDescription_Alternatives()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='Alternatives'"
	 * @generated
	 */
	String getAlternatives();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.ActivityDescription#getAlternatives <em>Alternatives</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Alternatives</em>' attribute.
	 * @see #getAlternatives()
	 * @generated
	 */
	void setAlternatives(String value);

	/**
	 * Returns the value of the '<em><b>How To Staff</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Provides background on who should be involved in this activity what are the required skills, experience,  and perhaps attitudes.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>How To Staff</em>' attribute.
	 * @see #setHowToStaff(String)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getActivityDescription_HowToStaff()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='HowToStaff'"
	 * @generated
	 */
	String getHowToStaff();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.ActivityDescription#getHowToStaff <em>How To Staff</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>How To Staff</em>' attribute.
	 * @see #getHowToStaff()
	 * @generated
	 */
	void setHowToStaff(String value);

	/**
	 * Returns the value of the '<em><b>Purpose</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Summarizes the main reason for performing this Activity, describes what the activity as a whole is intended to achieve.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Purpose</em>' attribute.
	 * @see #setPurpose(String)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getActivityDescription_Purpose()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='Purpose'"
	 * @generated
	 */
	String getPurpose();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.ActivityDescription#getPurpose <em>Purpose</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Purpose</em>' attribute.
	 * @see #getPurpose()
	 * @generated
	 */
	void setPurpose(String value);

} // ActivityDescription
