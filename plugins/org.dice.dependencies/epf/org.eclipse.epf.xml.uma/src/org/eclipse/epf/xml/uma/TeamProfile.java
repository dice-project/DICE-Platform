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
 * A representation of the model object '<em><b>Team Profile</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A Breakdown Element that groups Role Descriptors or Resource Definitions defining a nested hierarchy of teams and team members.
 * Work assignments and Work Product responsibilities can be different from Activity to Activity in a development project. Different phases require different staffing profiles, i.e. different skills and resources doing different types of work.  Therefore, a process needs to define such different profiles in a flexible manner.  Whereas Core Method Content defines standard responsibilities and assignments, a process express by a breakdown structures needs to be able refine and redefine these throughout its definition.  Role Descriptors, Resource Definitions, as well as Team Profiles provide the data structure necessary to achieve this flexibility and to provide a process user with the capability to define different teams and role relationships for every Activity (including Activities on any nesting-level as well as Iterations or Phases).
 * Hence, in addition to the work breakdown and work product breakdown structures defined so far, Team Profiles are used to define a third type of breakdown structure: team breakdown structures.  These are created as an Activity specific hierarchy of Team Profiles comprising of Role Descriptors and Resource Definitions.  These structures can be presented as well-known Org-Charts.  Just as with any other Breakdown Element and Descriptors, Team Profiles can be defined within the scope of any Activity in a breakdown structure.  In other words every Activity can define its own Team Profiles consisting of Activity specific Role Descriptors and Resource Definitions.  Typically, Team Profiles are defined on the level of Iterations or Phases or other higher-level Activity.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.xml.uma.TeamProfile#getGroup2 <em>Group2</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.TeamProfile#getRole <em>Role</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.TeamProfile#getSuperTeam <em>Super Team</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.TeamProfile#getSubTeam <em>Sub Team</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.xml.uma.UmaPackage#getTeamProfile()
 * @model extendedMetaData="name='TeamProfile' kind='elementOnly'"
 * @generated
 */
public interface TeamProfile extends BreakdownElement {
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
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getTeamProfile_Group2()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='group:30'"
	 * @generated
	 */
	FeatureMap getGroup2();

	/**
	 * Returns the value of the '<em><b>Role</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Role</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Role</em>' attribute list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getTeamProfile_Role()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Role' group='#group:30'"
	 * @generated
	 */
	EList<String> getRole();

	/**
	 * Returns the value of the '<em><b>Super Team</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Super Team</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Super Team</em>' attribute list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getTeamProfile_SuperTeam()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='SuperTeam' group='#group:30'"
	 * @generated
	 */
	EList<String> getSuperTeam();

	/**
	 * Returns the value of the '<em><b>Sub Team</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub Team</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sub Team</em>' attribute list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getTeamProfile_SubTeam()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='SubTeam' group='#group:30'"
	 * @generated
	 */
	EList<String> getSubTeam();

} // TeamProfile
