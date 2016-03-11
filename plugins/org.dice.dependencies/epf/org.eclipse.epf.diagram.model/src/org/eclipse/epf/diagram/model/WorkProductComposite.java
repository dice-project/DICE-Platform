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

import org.eclipse.epf.uma.WorkProduct;

/**
 * Node that represents a group of {@link WorkProduct}
 * 
 * @author Shashidhar Kannoori
 * @since 1.0
 * @model
 */
public interface WorkProductComposite extends NodeContainer {

	public static final int INPUTS = 1;

	public static final int OUTPUTS = 2;

	/**
	 * Gets type.
	 * 
	 * @return {@link #INPUTS} or {@link #OUTPUTS}
	 * @model
	 */
	int getType();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.diagram.model.WorkProductComposite#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(int value);

}
