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
 * A representation of the model object '<em><b>Role</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A Role is a content element that defines a set of related skills, competencies, and responsibilities.  Roles are used by Tasks to define who performs them as well as define a set of work products they are responsible for.  
 * A Role defines a set of related skills, competencies, and responsibilities of an individual or a set of individuals.  Roles are not individuals or resources.  Individual members of the development organization will wear different hats, or perform different roles. The mapping from individual to role, performed by the project manager when planning and staffing for a project, allows different individuals to act as several different roles, and for a role to be played by several individuals.
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.Role#getModifies <em>Modifies</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.Role#getResponsibleFor <em>Responsible For</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.uma.UmaPackage#getRole()
 * @model
 * @generated
 */
public interface Role extends ContentElement, FulfillableElement {
	/**
	 * Returns the value of the '<em><b>Modifies</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.WorkProduct}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Modifies</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Modifies</em>' reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getRole_Modifies()
	 * @model transient="true" volatile="true" derived="true" ordered="false"
	 * @generated
	 */
	List<WorkProduct> getModifies();

	/**
	 * Returns the value of the '<em><b>Responsible For</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.WorkProduct}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Responsible For</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Responsible For</em>' reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getRole_ResponsibleFor()
	 * @model ordered="false"
	 * @generated
	 */
	List<WorkProduct> getResponsibleFor();

} // Role
