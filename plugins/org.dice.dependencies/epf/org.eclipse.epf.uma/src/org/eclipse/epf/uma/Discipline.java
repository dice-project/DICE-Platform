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
 * A representation of the model object '<em><b>Discipline</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A Discipline is a categorization of work (i.e. Tasks for Method Content), based upon similarity of concerns and cooperation of work effort.
 * A discipline is a collection of Tasks that are related to a major 'area of concern' within the overall project. The grouping of Tasks into disciplines is mainly an aid to understanding the project from a 'traditional' waterfall perspective. However, typically, for example, it is more common to perform certain requirements activities in close coordination with analysis and design activities. Separating these activities into separate disciplines makes the activities easier to comprehend.
 * 
 * Discipline is a categorization of Tasks based upon similarity of concerns and cooperation of work effort.  This is the extensions of Discipline defined in the Method Core package adding an additional association to Activities, which represent typical standard or reference ways of meaningful groupings of the Discipline's Tasks into workflows.
 * Tasks represent descriptions of work, which are categorized by Disciplines.  The reason that several Tasks are all categorized by the same Discipline is that they all represent a part in achieving a higher goal or performing work that is all related to each other.  Every Discipline defines standard ways of doing the work it categorizes.  Such standard ways are express by Activities or Capability Patterns defining how the Tasks categorized by the Discipline 'work together' in the most generic way.  These reference workflows are often used for educating and teaching practitioners. 
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.Discipline#getTasks <em>Tasks</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.Discipline#getSubdiscipline <em>Subdiscipline</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.Discipline#getReferenceWorkflows <em>Reference Workflows</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.uma.UmaPackage#getDiscipline()
 * @model
 * @generated
 */
public interface Discipline extends ContentCategory {
	/**
	 * Returns the value of the '<em><b>Tasks</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.Task}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tasks</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tasks</em>' reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getDiscipline_Tasks()
	 * @model ordered="false"
	 * @generated
	 */
	List<Task> getTasks();

	/**
	 * Returns the value of the '<em><b>Subdiscipline</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.Discipline}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Subdiscipline</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Subdiscipline</em>' containment reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getDiscipline_Subdiscipline()
	 * @model containment="true" resolveProxies="true" ordered="false"
	 * @generated
	 */
	List<Discipline> getSubdiscipline();

	/**
	 * Returns the value of the '<em><b>Reference Workflows</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.Activity}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reference Workflows</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reference Workflows</em>' reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getDiscipline_ReferenceWorkflows()
	 * @model ordered="false"
	 * @generated
	 */
	List<Activity> getReferenceWorkflows();

} // Discipline
