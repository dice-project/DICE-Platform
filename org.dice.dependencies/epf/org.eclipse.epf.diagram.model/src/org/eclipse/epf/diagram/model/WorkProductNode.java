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
 * This class represents a node in Work Product Dependency Diagram
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 * @model
 */
public interface WorkProductNode extends NamedNode {

	public static final int ARTIFACT = 1;

	public static final int DELIVERABLE = 2;

	public static final int OUTCOME = 3;

	/**
	 * Gets the work product type of this node. This must be one of the
	 * following constants: {@link #ARTIFACT}, {@link #DELIVERABLE}, {@link #OUTCOME}
	 * 
	 * @model
	 */
	int getType();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.diagram.model.WorkProductNode#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(int value);

}
