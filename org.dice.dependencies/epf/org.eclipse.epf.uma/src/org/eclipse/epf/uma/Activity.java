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
 * A representation of the model object '<em><b>Activity</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * An Activity is a Work Breakdown Element and Work Definition which supports the nesting and logical grouping of related Breakdown Elements forming breakdown structures.  Although Activity is a concrete meta-class, other classes which represent breakdown structures derive from it; such as Phase, Iteration, Delivery Process, or Capability Pattern.
 * Activity represents a grouping element for other Breakdown Elements such as Activities, Descriptors, Milestones, etc.  It is not per-se a 'high-level' grouping of only work as in other meta-models, but groups any kind of Breakdown Elements.  For example, one can define valid Activities that group only Work Products Descriptors without any matching Task Descriptors.  Activities also inherit all properties from Work Breakdown Element and indirectly from Process Element; i.e. Activity is ready to have a full content description attached to it.
 * 
 * This definition of Activity extends Activity introduced in the package Breakdown with new capabilities and is the basis for the definition of the class Process.
 * Extends Activity with an association to Roadmap.  This definition of Activity also participates in the extension of the Discipline category.
 * 
 * Activity in the package Method Plugin inherits from Variability Element to extend Activity with new capabilities for variability.
 * Activity inherits the semantics of Variability Element which provides key mechanism to enable dynamic binding of Capability Patterns in Processes as well as Process Contributions.  Variability is used in the following way for Activities:
 * - Extend: To apply a Capability Pattern to a process one would create an Activity which extends the pattern's top-level activity.  Through extension the extending Activity inherits the patterns Breakdown Elements.
 * - Replace: To replace an Activity of an applied Capability Pattern or an existing process for which one develops a Process Contribution (or a combination of both) one would create an Activity and specify the replace variability specialization between them.
 * - Contribute: To contribute new Breakdown Elements to an existing Activity one would define an Activity with Breakdown Elements that relates via the Contributes Variability Specialization to this existing Activity.  The contributing Activity's Breakdown Elements will be added to the contributed Activity.
 * 
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.Activity#getBreakdownElements <em>Breakdown Elements</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.Activity#getRoadmaps <em>Roadmaps</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.uma.UmaPackage#getActivity()
 * @model
 * @generated
 */
public interface Activity extends WorkBreakdownElement, FulfillableElement,
		VariabilityElement, WorkDefinition {
	/**
	 * Returns the value of the '<em><b>Breakdown Elements</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.BreakdownElement}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.epf.uma.BreakdownElement#getSuperActivities <em>Super Activities</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Breakdown Elements</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Breakdown Elements</em>' reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getActivity_BreakdownElements()
	 * @see org.eclipse.epf.uma.BreakdownElement#getSuperActivities
	 * @model opposite="superActivities" ordered="false"
	 * @generated
	 */
	List<BreakdownElement> getBreakdownElements();

	/**
	 * Returns the value of the '<em><b>Roadmaps</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.Roadmap}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Roadmaps</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Roadmaps</em>' reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getActivity_Roadmaps()
	 * @model ordered="false"
	 * @generated
	 */
	List<Roadmap> getRoadmaps();

} // Activity
