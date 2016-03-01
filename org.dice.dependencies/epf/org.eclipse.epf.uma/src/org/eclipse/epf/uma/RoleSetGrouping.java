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
 * A representation of the model object '<em><b>Role Set Grouping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Role Sets can be categorized into Role Set Groupings.  For example, different methods might define similar Role Sets, which however need to be distinguished from each other on a global scale.  Thus, Role Set Groupings allow distinguishing, for example, Software Services Manager Role Sets from Software Development Organization Manager Role Sets.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.RoleSetGrouping#getRoleSets <em>Role Sets</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.uma.UmaPackage#getRoleSetGrouping()
 * @model
 * @generated
 */
public interface RoleSetGrouping extends ContentCategory {
	/**
	 * Returns the value of the '<em><b>Role Sets</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.RoleSet}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Role Sets</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Role Sets</em>' reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getRoleSetGrouping_RoleSets()
	 * @model ordered="false"
	 * @generated
	 */
	List<RoleSet> getRoleSets();

} // RoleSetGrouping
