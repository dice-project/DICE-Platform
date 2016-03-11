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
 * A representation of the model object '<em><b>Role Descriptor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A Role Descriptor represents a Role in the context of one specific Activity.  Every breakdown structure can define different relationships of Role Descriptors to Task Descriptors and Work Product Descriptors.  Therefore one Role can be represented by many Role Descriptors each within the context of an Activity with its own set of relationships.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.RoleDescriptor#getRole <em>Role</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.RoleDescriptor#getModifies <em>Modifies</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.RoleDescriptor#getResponsibleFor <em>Responsible For</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.RoleDescriptor#getResponsibleForExclude <em>Responsible For Exclude</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.uma.UmaPackage#getRoleDescriptor()
 * @model
 * @generated
 */
public interface RoleDescriptor extends Descriptor {
	/**
	 * Returns the value of the '<em><b>Role</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Role</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Role</em>' reference.
	 * @see #setRole(Role)
	 * @see org.eclipse.epf.uma.UmaPackage#getRoleDescriptor_Role()
	 * @model ordered="false"
	 * @generated
	 */
	Role getRole();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.RoleDescriptor#getRole <em>Role</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Role</em>' reference.
	 * @see #getRole()
	 * @generated
	 */
	void setRole(Role value);

	/**
	 * Returns the value of the '<em><b>Modifies</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.WorkProductDescriptor}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Modifies</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Modifies</em>' reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getRoleDescriptor_Modifies()
	 * @model volatile="true" derived="true" ordered="false"
	 * @generated
	 */
	List<WorkProductDescriptor> getModifies();

	/**
	 * Returns the value of the '<em><b>Responsible For</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.WorkProductDescriptor}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Responsible For</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Responsible For</em>' reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getRoleDescriptor_ResponsibleFor()
	 * @model ordered="false"
	 * @generated
	 */
	List<WorkProductDescriptor> getResponsibleFor();

	/**
	 * Returns the value of the '<em><b>Responsible For Exclude</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.WorkProduct}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Responsible For Exclude</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Responsible For Exclude</em>' reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getRoleDescriptor_ResponsibleForExclude()
	 * @model ordered="false"
	 * @generated
	 */
	List<WorkProduct> getResponsibleForExclude();

} // RoleDescriptor
