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

import java.util.List;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Task</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A Task is a content element that describes work being performed by Roles.  It defines one default performing Role as well as many additional performers.  A Task is associated to input and output work products.  Inputs are differentiated in mandatory versus optional inputs.  The associations to Work Products are not instantiatable/variable-like parameters.  They rather express (hyper-)links to the descriptions of the work products types that are related to the Task as inputs and outputs.  In other words, these associations are not intended to be used to capture which concrete instances will be passed when instantiating the method in a project.  All of the Task's default associations can be overridden in an actual process definition.
 * A Task describes an assignable unit of work.  Every Task is assigned to specific Roles.  The granularity of a Task is generally a few hours to a few days.  It usually affects one or only a small number of work products. A Task is used as an element of defining a process. Tasks are further used for planning and tracking progress; therefore, if they are defined too fine-grained, they will be neglected, and if they are too large, progress would have to be expressed in terms of a Task's parts (e.g. Steps, which is not recommended). 
 * A Task has a clear purpose in which the performing roles achieve a well defined goal.  It provides complete step-by-step explanations of doing all the work that needs to be done to achieve this goal.  This description is complete, independent of when in a process lifecycle the work would actually be done.  It therefore does not describe when you do what work at what point of time, but describes all the work that gets done throughout the development lifecycle that contributes to the achievement of this goal.  When the Task is being applied in a process then this process application (defined as Task Descriptor) provides the information of which pieces of the Task will actually be performed at any particular point in time. This assumes that the Task will be performed in the process over and over again, but each time with a slightly different emphasis on different steps or aspects of the task description. 
 * For example, a Task such as "Develop Use Case Model" describes all the work that needs to be done to develop a complete use case model. This would comprise of the identification and naming of use cases and actors, the writing of a brief description, the modeling of use cases and their relationships in diagrams, the detailed description of a basic flow, the detailed description of alternatives flows, performing of walkthroughs workshops and reviews, etc.  All of these parts contribute to the development goal of developing the use case model, but the parts will be performed at different points in time in a process.  Identification, naming, and brief descriptions would be performed early in a typical development process versus the writing of detailed alternative flows which would be performed much later.  All these parts or steps within the same Task define the "method" of Developing a Use Case Model.  Applying such a method in a lifecycle (i.e. in a process) is defining which steps are done when going from one iteration to the next.
 * 
 * This is the Guidance Types package's extension of Task (defined in Content Elements) providing additional associations.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.Task#getPerformedBy <em>Performed By</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.Task#getMandatoryInput <em>Mandatory Input</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.Task#getOutput <em>Output</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.Task#getAdditionallyPerformedBy <em>Additionally Performed By</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.Task#getOptionalInput <em>Optional Input</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.Task#getSteps <em>Steps</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.Task#getToolMentors <em>Tool Mentors</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.Task#getEstimationConsiderations <em>Estimation Considerations</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.uma.UmaPackage#getTask()
 * @model
 * @generated
 */
public interface Task extends ContentElement, WorkDefinition {
	/**
	 * Returns the value of the '<em><b>Performed By</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.Role}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Performed By</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Performed By</em>' reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getTask_PerformedBy()
	 * @model ordered="false"
	 * @generated
	 */
	List<Role> getPerformedBy();

	/**
	 * Returns the value of the '<em><b>Mandatory Input</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.WorkProduct}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mandatory Input</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mandatory Input</em>' reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getTask_MandatoryInput()
	 * @model ordered="false"
	 * @generated
	 */
	List<WorkProduct> getMandatoryInput();

	/**
	 * Returns the value of the '<em><b>Output</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.WorkProduct}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Output</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Output</em>' reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getTask_Output()
	 * @model ordered="false"
	 * @generated
	 */
	List<WorkProduct> getOutput();

	/**
	 * Returns the value of the '<em><b>Additionally Performed By</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.Role}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Additionally Performed By</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Additionally Performed By</em>' reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getTask_AdditionallyPerformedBy()
	 * @model ordered="false"
	 * @generated
	 */
	List<Role> getAdditionallyPerformedBy();

	/**
	 * Returns the value of the '<em><b>Optional Input</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.WorkProduct}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Optional Input</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Optional Input</em>' reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getTask_OptionalInput()
	 * @model ordered="false"
	 * @generated
	 */
	List<WorkProduct> getOptionalInput();

	/**
	 * Returns the value of the '<em><b>Steps</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.Step}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Steps</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Steps</em>' reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getTask_Steps()
	 * @model transient="true" volatile="true" derived="true" ordered="false"
	 * @generated
	 */
	List<Step> getSteps();

	/**
	 * Returns the value of the '<em><b>Tool Mentors</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.ToolMentor}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tool Mentors</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tool Mentors</em>' reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getTask_ToolMentors()
	 * @model ordered="false"
	 * @generated
	 */
	List<ToolMentor> getToolMentors();

	/**
	 * Returns the value of the '<em><b>Estimation Considerations</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.EstimationConsiderations}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Estimation Considerations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Estimation Considerations</em>' reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getTask_EstimationConsiderations()
	 * @model ordered="false"
	 * @generated
	 */
	List<EstimationConsiderations> getEstimationConsiderations();

} // Task
