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
 * A representation of the model object '<em><b>Process Component Interface</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A Process Component Interface comprises of a list of interface specifications (similar to operation declarations) that express inputs and outputs for a process component.  These interface specifications are expressed using Task Descriptors which are not linked to Tasks that are related to Work Product Descriptors as well as optional a Role Descriptor.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.ProcessComponentInterface#getInterfaceSpecifications <em>Interface Specifications</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.ProcessComponentInterface#getInterfaceIO <em>Interface IO</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.uma.UmaPackage#getProcessComponentInterface()
 * @model
 * @generated
 */
public interface ProcessComponentInterface extends BreakdownElement {
	/**
	 * Returns the value of the '<em><b>Interface Specifications</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.TaskDescriptor}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Interface Specifications</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Interface Specifications</em>' containment reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getProcessComponentInterface_InterfaceSpecifications()
	 * @model containment="true" resolveProxies="true" ordered="false"
	 * @generated
	 */
	List<TaskDescriptor> getInterfaceSpecifications();

	/**
	 * Returns the value of the '<em><b>Interface IO</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.WorkProductDescriptor}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Interface IO</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Interface IO</em>' containment reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getProcessComponentInterface_InterfaceIO()
	 * @model containment="true" resolveProxies="true" ordered="false"
	 * @generated
	 */
	List<WorkProductDescriptor> getInterfaceIO();

} // ProcessComponentInterface
