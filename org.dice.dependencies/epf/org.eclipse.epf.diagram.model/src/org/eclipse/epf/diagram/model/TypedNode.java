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
 * Node that is not linked to any method element (not a {@link LinkedObject}).
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 * @model
 */
public interface TypedNode extends Node {
	static final int SYNCH_BAR = 1;

	static final int DECISION = 2;

	static final int START = 3;

	static final int END = 4;

	static final int STICKY_NOTE = 5;

	static final int FREE_TEXT = 6;

	/**
	 * Gets type. The type must be one of the constants defined in this
	 * interface.
	 * 
	 * @model
	 */
	int getType();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.diagram.model.TypedNode#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(int value);

}
