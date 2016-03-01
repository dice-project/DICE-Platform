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
 * A representation of the model object '<em><b>Task Descriptor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A Task Descriptor is a Descriptor and Work Breakdown Element that represents a proxy for a Task in the context of one specific Activity.  Every breakdown structure can define different relationships of Task Descriptors to Work Product Descriptors and Role Descriptors. Therefore one Task can be represented by many Task Descriptors each within the context of an Activity with its own set of relationships.
 * A key difference between Method Content and Process is that a Content Element such as Task describes all aspects of doing work defined around this Task.  This description is managed in steps, which are modeled as Sections of the Tasks' Content Descriptions.  When applying a Task in a Process' Activity with a Task Descriptor a Process Engineer needs to indicate that at that particular point in time in the Process definition for which the Task Descriptor has been created, only a subset of steps shall be performed.  He defines this selection using the selectedSteps association.  If he wants to add steps to a Task Descriptor, he can describe these either pragmatically in the refinedDescription attribute or 'properly' create a contributing Task to the Task the Task Descriptor refers to.
 * 
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.TaskDescriptor#getTask <em>Task</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.TaskDescriptor#getAdditionallyPerformedBy <em>Additionally Performed By</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.TaskDescriptor#getAssistedBy <em>Assisted By</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.TaskDescriptor#getExternalInput <em>External Input</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.TaskDescriptor#getMandatoryInput <em>Mandatory Input</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.TaskDescriptor#getOptionalInput <em>Optional Input</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.TaskDescriptor#getOutput <em>Output</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.TaskDescriptor#getPerformedPrimarilyBy <em>Performed Primarily By</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.TaskDescriptor#getSelectedSteps <em>Selected Steps</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.TaskDescriptor#getPerformedPrimarilyByExcluded <em>Performed Primarily By Excluded</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.TaskDescriptor#getAdditionallyPerformedByExclude <em>Additionally Performed By Exclude</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.TaskDescriptor#getMandatoryInputExclude <em>Mandatory Input Exclude</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.TaskDescriptor#getOptionalInputExclude <em>Optional Input Exclude</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.TaskDescriptor#getOutputExclude <em>Output Exclude</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.TaskDescriptor#getSelectedStepsExclude <em>Selected Steps Exclude</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.uma.UmaPackage#getTaskDescriptor()
 * @model
 * @generated
 */
public interface TaskDescriptor extends WorkBreakdownElement, Descriptor {
	/**
	 * Returns the value of the '<em><b>Task</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Task</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Task</em>' reference.
	 * @see #setTask(Task)
	 * @see org.eclipse.epf.uma.UmaPackage#getTaskDescriptor_Task()
	 * @model ordered="false"
	 * @generated
	 */
	Task getTask();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.TaskDescriptor#getTask <em>Task</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Task</em>' reference.
	 * @see #getTask()
	 * @generated
	 */
	void setTask(Task value);

	/**
	 * Returns the value of the '<em><b>Additionally Performed By</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.RoleDescriptor}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Additionally Performed By</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Additionally Performed By</em>' reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getTaskDescriptor_AdditionallyPerformedBy()
	 * @model ordered="false"
	 * @generated
	 */
	List<RoleDescriptor> getAdditionallyPerformedBy();

	/**
	 * Returns the value of the '<em><b>Assisted By</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.RoleDescriptor}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Assisted By</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Assisted By</em>' reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getTaskDescriptor_AssistedBy()
	 * @model ordered="false"
	 * @generated
	 */
	List<RoleDescriptor> getAssistedBy();

	/**
	 * Returns the value of the '<em><b>External Input</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.WorkProductDescriptor}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>External Input</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>External Input</em>' reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getTaskDescriptor_ExternalInput()
	 * @model ordered="false"
	 * @generated
	 */
	List<WorkProductDescriptor> getExternalInput();

	/**
	 * Returns the value of the '<em><b>Mandatory Input</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.WorkProductDescriptor}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mandatory Input</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mandatory Input</em>' reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getTaskDescriptor_MandatoryInput()
	 * @model ordered="false"
	 * @generated
	 */
	List<WorkProductDescriptor> getMandatoryInput();

	/**
	 * Returns the value of the '<em><b>Optional Input</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.WorkProductDescriptor}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Optional Input</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Optional Input</em>' reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getTaskDescriptor_OptionalInput()
	 * @model ordered="false"
	 * @generated
	 */
	List<WorkProductDescriptor> getOptionalInput();

	/**
	 * Returns the value of the '<em><b>Output</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.WorkProductDescriptor}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Output</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Output</em>' reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getTaskDescriptor_Output()
	 * @model ordered="false"
	 * @generated
	 */
	List<WorkProductDescriptor> getOutput();

	/**
	 * Returns the value of the '<em><b>Performed Primarily By</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.RoleDescriptor}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Performed Primarily By</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Performed Primarily By</em>' reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getTaskDescriptor_PerformedPrimarilyBy()
	 * @model ordered="false"
	 * @generated
	 */
	List<RoleDescriptor> getPerformedPrimarilyBy();

	/**
	 * Returns the value of the '<em><b>Selected Steps</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.Section}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Selected Steps</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Selected Steps</em>' reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getTaskDescriptor_SelectedSteps()
	 * @model ordered="false"
	 * @generated
	 */
	List<Section> getSelectedSteps();

	/**
	 * Returns the value of the '<em><b>Performed Primarily By Excluded</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.Role}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Performed Primarily By Excluded</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Performed Primarily By Excluded</em>' reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getTaskDescriptor_PerformedPrimarilyByExcluded()
	 * @model ordered="false"
	 * @generated
	 */
	List<Role> getPerformedPrimarilyByExcluded();

	/**
	 * Returns the value of the '<em><b>Additionally Performed By Exclude</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.Role}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Additionally Performed By Exclude</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Additionally Performed By Exclude</em>' reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getTaskDescriptor_AdditionallyPerformedByExclude()
	 * @model ordered="false"
	 * @generated
	 */
	List<Role> getAdditionallyPerformedByExclude();

	/**
	 * Returns the value of the '<em><b>Mandatory Input Exclude</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.WorkProduct}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mandatory Input Exclude</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mandatory Input Exclude</em>' reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getTaskDescriptor_MandatoryInputExclude()
	 * @model ordered="false"
	 * @generated
	 */
	List<WorkProduct> getMandatoryInputExclude();

	/**
	 * Returns the value of the '<em><b>Optional Input Exclude</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.WorkProduct}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Optional Input Exclude</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Optional Input Exclude</em>' reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getTaskDescriptor_OptionalInputExclude()
	 * @model ordered="false"
	 * @generated
	 */
	List<WorkProduct> getOptionalInputExclude();

	/**
	 * Returns the value of the '<em><b>Output Exclude</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.WorkProduct}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Output Exclude</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Output Exclude</em>' reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getTaskDescriptor_OutputExclude()
	 * @model ordered="false"
	 * @generated
	 */
	List<WorkProduct> getOutputExclude();

	/**
	 * Returns the value of the '<em><b>Selected Steps Exclude</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.Section}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Selected Steps Exclude</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Selected Steps Exclude</em>' reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getTaskDescriptor_SelectedStepsExclude()
	 * @model ordered="false"
	 * @generated
	 */
	List<Section> getSelectedStepsExclude();

} // TaskDescriptor
