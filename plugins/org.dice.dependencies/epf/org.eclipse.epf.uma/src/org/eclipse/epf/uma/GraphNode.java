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
 * A representation of the model object '<em><b>Graph Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.GraphNode#getSize <em>Size</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.uma.UmaPackage#getGraphNode()
 * @model
 * @generated
 */
public interface GraphNode extends GraphElement {
	/**
	 * Returns the value of the '<em><b>Size</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Size</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Size</em>' reference.
	 * @see #setSize(Dimension)
	 * @see org.eclipse.epf.uma.UmaPackage#getGraphNode_Size()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Dimension getSize();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.GraphNode#getSize <em>Size</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Size</em>' reference.
	 * @see #getSize()
	 * @generated
	 */
	void setSize(Dimension value);

} // GraphNode
