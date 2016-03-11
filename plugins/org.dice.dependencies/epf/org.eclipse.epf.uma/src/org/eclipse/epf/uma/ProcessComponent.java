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
 * A representation of the model object '<em><b>Process Component</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A Process Component is a special Process Package that applies the principles of encapsulation.  A Process Component realizes one or more Interfaces which specify inputs and outputs of the component. There might be many components realizing the same interfaces, but using different techniques to achieve similar outputs for similar inputs.  Whereas the Component Interfaces represent component specifications (black box descriptions of the component), good candidates for component realizations can be found in Capability Patterns (white box descriptions for the component).
 * UMA supports replaceable and reusable Process Components realizing the principles of encapsulation. Certain situations in a software development project might require that concrete realizations of parts of the process remain undecided or will be decided by the executing team itself (e.g. in outsourcing situations).  UMA provides a unique component concept defining interfaces for work product input and output, allowing treating the actual definition of the work that produces the outputs as a "black box".  At any point during a project the component "realization" detailing the work can be added to the process.  The component approach also allows that different styles or techniques of doing work can be replaced with one another.  For example, a software code output of a component could be produced with a model-driven development or a code-centric technique.  The component concept encapsulates the actual work and lets the development team choose the appropriate technique and fill the component's realization with their choice of Activities that produce the required outputs.
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.ProcessComponent#getInterfaces <em>Interfaces</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.ProcessComponent#getProcess <em>Process</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.uma.UmaPackage#getProcessComponent()
 * @model
 * @generated
 */
public interface ProcessComponent extends ProcessPackage, MethodUnit {
	/**
	 * Returns the value of the '<em><b>Interfaces</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.ProcessComponentInterface}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Interfaces</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Interfaces</em>' reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getProcessComponent_Interfaces()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	List<ProcessComponentInterface> getInterfaces();

	/**
	 * Returns the value of the '<em><b>Process</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Process</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Process</em>' containment reference.
	 * @see #setProcess(org.eclipse.epf.uma.Process)
	 * @see org.eclipse.epf.uma.UmaPackage#getProcessComponent_Process()
	 * @model containment="true" resolveProxies="true" required="true" ordered="false"
	 * @generated
	 */
	org.eclipse.epf.uma.Process getProcess();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.ProcessComponent#getProcess <em>Process</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Process</em>' containment reference.
	 * @see #getProcess()
	 * @generated
	 */
	void setProcess(org.eclipse.epf.uma.Process value);

} // ProcessComponent
