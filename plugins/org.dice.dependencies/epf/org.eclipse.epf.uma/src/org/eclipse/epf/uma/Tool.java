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
 * A representation of the model object '<em><b>Tool</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A Tool is a container/aggregate for ToolMentors.  It can also provide general descriptions of the tool and its general capabilities.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.Tool#getToolMentors <em>Tool Mentors</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.uma.UmaPackage#getTool()
 * @model
 * @generated
 */
public interface Tool extends ContentCategory {
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
	 * @see org.eclipse.epf.uma.UmaPackage#getTool_ToolMentors()
	 * @model ordered="false"
	 * @generated
	 */
	List<ToolMentor> getToolMentors();

} // Tool
