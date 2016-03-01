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
 * A representation of the model object '<em><b>Practice</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A Practice represents a proven way or strategy of doing work to achieve a goal that has a positive impact on work product or process quality.  Practices are defined orthogonal to methods and processes.  They could summarize aspects that impact many different parts of a method or specific processes.  Examples for practices would be "Manage Risks", "Continuously verify quality", "Architecture-centric and component-based development", etc.
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.Practice#getSubPractices <em>Sub Practices</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.Practice#getContentReferences <em>Content References</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.Practice#getActivityReferences <em>Activity References</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.uma.UmaPackage#getPractice()
 * @model
 * @generated
 */
public interface Practice extends Guidance {
	/**
	 * Returns the value of the '<em><b>Sub Practices</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.Practice}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub Practices</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sub Practices</em>' containment reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getPractice_SubPractices()
	 * @model containment="true" resolveProxies="true" ordered="false"
	 * @generated
	 */
	List<Practice> getSubPractices();

	/**
	 * Returns the value of the '<em><b>Content References</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.ContentElement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Content References</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Content References</em>' reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getPractice_ContentReferences()
	 * @model ordered="false"
	 * @generated
	 */
	List<ContentElement> getContentReferences();

	/**
	 * Returns the value of the '<em><b>Activity References</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.Activity}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Activity References</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Activity References</em>' reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getPractice_ActivityReferences()
	 * @model ordered="false"
	 * @generated
	 */
	List<Activity> getActivityReferences();

} // Practice
