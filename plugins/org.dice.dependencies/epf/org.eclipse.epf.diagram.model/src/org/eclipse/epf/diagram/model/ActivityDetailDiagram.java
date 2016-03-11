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
 * Interface for activity detail diagram model object
 * 
 * @author Phong Nguyen Le
 * @since  1.0
 * @model
 */
public interface ActivityDetailDiagram extends Diagram {

	/**
	 * Returns the value of the '<em><b>Auto Layout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Auto Layout</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Auto Layout</em>' attribute.
	 * @see #setAutoLayout(boolean)
	 * @see org.eclipse.epf.diagram.model.ModelPackage#getActivityDetailDiagram_AutoLayout()
	 * @model
	 * @generated
	 */
	boolean isAutoLayout();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.diagram.model.ActivityDetailDiagram#isAutoLayout <em>Auto Layout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Auto Layout</em>' attribute.
	 * @see #isAutoLayout()
	 * @generated
	 */
	void setAutoLayout(boolean value);

}
