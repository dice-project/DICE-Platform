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
package org.eclipse.epf.diagram.model;

/**
 * A diagram node with name
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 * @model
 */
public interface NamedNode extends Node {
	/**
	 * Gets node name
	 * 
	 * @return the name of this node
	 * @model
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.diagram.model.NamedNode#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Gets the suppression status of this node
	 * 
	 * @return <code>true</code> if it is suppressed, <code>false</code>
	 *         otherwise
	 */
	boolean isSuppressed();

	/**
	 * Gets the wrapper of the method element that this node represents
	 * 
	 * @return the wrapper
	 */
	Object getWrapper();
}
