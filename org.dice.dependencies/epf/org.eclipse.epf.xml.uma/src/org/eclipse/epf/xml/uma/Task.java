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
 * A representation of the model object '<em><b>Task</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A Content Element that describes work being performed by Roles.  It defines one default performing Role as well as many additional performers.  A Task is associated to input and output work products.  Inputs are differentiated in mandatory versus optional inputs.  The associations to Work Products are not instantiatable/variable-like parameters.  They rather express (hyper-)links to the descriptions of the work products types that are related to the Task as inputs and outputs.  In other words, these associations are not intended to be used to capture which concrete instances will be passed when instantiating the method in a project.  All of the Task's default associations can be overridden in an actual process definition.
 * A Task describes an assignable unit of work.  Every Task is assigned to specific Roles.  The granularity of a Task is generally a few hours to a few days.  It usually affects one or only a small number of work products. A Task is used as an element of defining a process. Tasks are further used for planning and tracking progress; therefore, if they are defined too fine-grained, they will be neglected, and if they are too large, progress would have to be expressed in terms of a Task's parts (e.g. Steps, which is not recommended). 
 * A Task has a clear purpose in which the performing roles achieve a well defined goal.  It provides complete step-by-step explanations of doing all the work that needs to be done to achieve this goal.  This description is complete, independent of when in a process lifecycle the work would actually be done.  It therefore does not describe when you do what work at what point of time, but describes all the work that gets done throughout the development lifecycle that contributes to the achievement of this goal.  When the Task is being applied in a process then this process application (defined as Task Descriptor) provides the information of which pieces of the Task will actually be performed at any particular point in time. This assumes that the Task will be performed in the process over and over again, but each time with a slightly different emphasis on different steps or aspects of the task description. 
 * For example, a Task such as "Develop Use Case Model" describes all the work that needs to be done to develop a complete use case model. This would comprise of the identification and naming of use cases and actors, the writing of a brief description, the modeling of use cases and their relationships in diagrams, the detailed description of a basic flow, the detailed description of alternatives flows, performing of walkthroughs workshops and reviews, etc.  All of these parts contribute to the development goal of developing the use case model, but the parts will be performed at different points in time in a process.  Identification, naming, and brief descriptions would be performed early in a typical development process versus the writing of detailed alternative flows which would be performed much later.  All these parts or steps within the same Task define the "method" of Developing a Use Case Model.  Applying such a method in a lifecycle (i.e. in a process) is defining which steps are done when going from one iteration to the next.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.xml.uma.Task#getPrecondition <em>Precondition</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.Task#getPostcondition <em>Postcondition</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.Task#getPerformedBy <em>Performed By</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.Task#getGroup2 <em>Group2</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.Task#getMandatoryInput <em>Mandatory Input</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.Task#getOutput <em>Output</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.Task#getAdditionallyPerformedBy <em>Additionally Performed By</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.Task#getOptionalInput <em>Optional Input</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.Task#getEstimate <em>Estimate</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.Task#getEstimationConsiderations <em>Estimation Considerations</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.Task#getToolMentor <em>Tool Mentor</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.xml.uma.UmaPackage#getTask()
 * @model extendedMetaData="name='Task' kind='elementOnly'"
 * @generated
 */
public interface Task extends ContentElement {
	/**
	 * Returns the value of the '<em><b>Precondition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Precondition</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Precondition</em>' attribute.
	 * @see #setPrecondition(String)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getTask_Precondition()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='Precondition'"
	 * @generated
	 */
	String getPrecondition();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.Task#getPrecondition <em>Precondition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Precondition</em>' attribute.
	 * @see #getPrecondition()
	 * @generated
	 */
	void setPrecondition(String value);

	/**
	 * Returns the value of the '<em><b>Postcondition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Postcondition</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Postcondition</em>' attribute.
	 * @see #setPostcondition(String)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getTask_Postcondition()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='Postcondition'"
	 * @generated
	 */
	String getPostcondition();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.Task#getPostcondition <em>Postcondition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Postcondition</em>' attribute.
	 * @see #getPostcondition()
	 * @generated
	 */
	void setPostcondition(String value);

	/**
	 * Returns the value of the '<em><b>Performed By</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Performed By</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Performed By</em>' attribute list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getTask_PerformedBy()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='PerformedBy'"
	 * @generated
	 */
	EList<String> getPerformedBy();

	/**
	 * Returns the value of the '<em><b>Group2</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Group2</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Group2</em>' attribute list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getTask_Group2()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='group:27'"
	 * @generated
	 */
	FeatureMap getGroup2();

	/**
	 * Returns the value of the '<em><b>Mandatory Input</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mandatory Input</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mandatory Input</em>' attribute list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getTask_MandatoryInput()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='MandatoryInput' group='#group:27'"
	 * @generated
	 */
	EList<String> getMandatoryInput();

	/**
	 * Returns the value of the '<em><b>Output</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Output</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Output</em>' attribute list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getTask_Output()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Output' group='#group:27'"
	 * @generated
	 */
	EList<String> getOutput();

	/**
	 * Returns the value of the '<em><b>Additionally Performed By</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Additionally Performed By</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Additionally Performed By</em>' attribute list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getTask_AdditionallyPerformedBy()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='AdditionallyPerformedBy' group='#group:27'"
	 * @generated
	 */
	EList<String> getAdditionallyPerformedBy();

	/**
	 * Returns the value of the '<em><b>Optional Input</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Optional Input</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Optional Input</em>' attribute list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getTask_OptionalInput()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='OptionalInput' group='#group:27'"
	 * @generated
	 */
	EList<String> getOptionalInput();

	/**
	 * Returns the value of the '<em><b>Estimate</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Estimate</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Estimate</em>' attribute list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getTask_Estimate()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Estimate' group='#group:27'"
	 * @generated
	 */
	EList<String> getEstimate();

	/**
	 * Returns the value of the '<em><b>Estimation Considerations</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Estimation Considerations</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Estimation Considerations</em>' attribute list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getTask_EstimationConsiderations()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='EstimationConsiderations' group='#group:27'"
	 * @generated
	 */
	EList<String> getEstimationConsiderations();

	/**
	 * Returns the value of the '<em><b>Tool Mentor</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tool Mentor</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tool Mentor</em>' attribute list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getTask_ToolMentor()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='ToolMentor' group='#group:27'"
	 * @generated
	 */
	EList<String> getToolMentor();

} // Task
