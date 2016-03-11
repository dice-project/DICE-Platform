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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Delivery Process</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A special Process describing a complete and integrated approach for performing a specific project type.  It describes a complete project lifecycle end-to-end and shall be used as a reference for running projects with similar characteristics as defined for the process.  A Delivery Process is related to specific supporting information such as Roadmaps (inherited via Activity) as well as Communications and Education Material.
 * A Delivery Process is a Process that covers a whole development lifecycle from beginning to end.  A Delivery Process shall be used as a template for planning and running a project.  It provides a complete lifecycle model with predefined phases, iterations, and activities that have been detailed by sequencing referencing method content in breakdown structures.  It is defined on the basis of experience with past projects or engagements, and/or the best practice use of a development or delivery approach.  It defines what gets produced, how those items are produced, and the required staffing in the form of integrated Work, Work Product, and Team Breakdown Structures.  For example, a process engineer can define alternative Delivery Processes for software development projects that differ in the scale of the engagement and staffing necessary, the type of the software application to be developed, the development methods and technologies to be used, etc.  Although, the Delivery Process aims to cover a whole project it keeps certain decision that are too project specific open.  For example, the breakdown structure defines which Breakdown Elements have multiple occurrences or is repeatable via it respective attributes, but does not say how many occurrences and how many repeats/iterations it will have.  These decisions have to be done by a project manager when planning a concrete project, project phase, or project iterations.  A Delivery Process is always a complete description of a process in terms of completeness of the lifecycle, as well as in terms of all three views on the process which are the Work Breakdown Structure, Work Product Breakdown Structure, and Team Breakdown Structure have to be fully and consistently populated.  Consistency of a Delivery Process is actually ensured by the fact that all three breakdowns are represented by one single data structure and one particular breakdown such as Team Breakdown is just a view on that data structure.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.xml.uma.DeliveryProcess#getGroup4 <em>Group4</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.DeliveryProcess#getCommunicationsMaterial <em>Communications Material</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.DeliveryProcess#getEducationMaterial <em>Education Material</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.xml.uma.UmaPackage#getDeliveryProcess()
 * @model extendedMetaData="name='DeliveryProcess' kind='elementOnly'"
 * @generated
 */
public interface DeliveryProcess extends org.eclipse.epf.xml.uma.Process {
	/**
	 * Returns the value of the '<em><b>Group4</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Group4</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Group4</em>' attribute list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getDeliveryProcess_Group4()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='group:47'"
	 * @generated
	 */
	FeatureMap getGroup4();

	/**
	 * Returns the value of the '<em><b>Communications Material</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Communications Material</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Communications Material</em>' attribute list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getDeliveryProcess_CommunicationsMaterial()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='CommunicationsMaterial' group='#group:47'"
	 * @generated
	 */
	EList<String> getCommunicationsMaterial();

	/**
	 * Returns the value of the '<em><b>Education Material</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Education Material</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Education Material</em>' attribute list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getDeliveryProcess_EducationMaterial()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='EducationMaterial' group='#group:47'"
	 * @generated
	 */
	EList<String> getEducationMaterial();

} // DeliveryProcess
