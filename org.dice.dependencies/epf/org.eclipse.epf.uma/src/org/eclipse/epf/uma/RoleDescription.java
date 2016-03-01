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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Role Description</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.RoleDescription#getSkills <em>Skills</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.RoleDescription#getAssignmentApproaches <em>Assignment Approaches</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.RoleDescription#getSynonyms <em>Synonyms</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.uma.UmaPackage#getRoleDescription()
 * @model
 * @generated
 */
public interface RoleDescription extends ContentDescription {
	/**
	 * Returns the value of the '<em><b>Skills</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Lists of set of required skills a person needs to possess to fulfill that Role.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Skills</em>' attribute.
	 * @see #setSkills(String)
	 * @see org.eclipse.epf.uma.UmaPackage#getRoleDescription_Skills()
	 * @model default="" dataType="org.eclipse.epf.uma.String" ordered="false"
	 * @generated
	 */
	String getSkills();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.RoleDescription#getSkills <em>Skills</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Skills</em>' attribute.
	 * @see #getSkills()
	 * @generated
	 */
	void setSkills(String value);

	/**
	 * Returns the value of the '<em><b>Assignment Approaches</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Provides guidance on for assigning individuals to the Role in terms of what other roles these individuals could perform and what responsibility different individuals assigned to this role might have.  The guidance can also describe different assignment approaches for different types of projects, e.g. for large versus small teams where individuals could be allocated to roles full time versus sharing roles within the team.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Assignment Approaches</em>' attribute.
	 * @see #setAssignmentApproaches(String)
	 * @see org.eclipse.epf.uma.UmaPackage#getRoleDescription_AssignmentApproaches()
	 * @model default="" dataType="org.eclipse.epf.uma.String" ordered="false"
	 * @generated
	 */
	String getAssignmentApproaches();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.RoleDescription#getAssignmentApproaches <em>Assignment Approaches</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Assignment Approaches</em>' attribute.
	 * @see #getAssignmentApproaches()
	 * @generated
	 */
	void setAssignmentApproaches(String value);

	/**
	 * Returns the value of the '<em><b>Synonyms</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Lists synonyms, i.e. other names the Role might be referred by.  Tool support for the meta-model might support that a Role name can be consistently be replaced with one of its synonyms throught a Process.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Synonyms</em>' attribute.
	 * @see #setSynonyms(String)
	 * @see org.eclipse.epf.uma.UmaPackage#getRoleDescription_Synonyms()
	 * @model default="" dataType="org.eclipse.epf.uma.String" ordered="false"
	 * @generated
	 */
	String getSynonyms();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.RoleDescription#getSynonyms <em>Synonyms</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Synonyms</em>' attribute.
	 * @see #getSynonyms()
	 * @generated
	 */
	void setSynonyms(String value);

} // RoleDescription
