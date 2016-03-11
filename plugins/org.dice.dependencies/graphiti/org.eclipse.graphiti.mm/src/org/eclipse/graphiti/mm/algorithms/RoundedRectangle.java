/**
 * <copyright>
 * 
 * Copyright (c) 2005, 2010 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 * 
 * </copyright>
 */
package org.eclipse.graphiti.mm.algorithms;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Rounded Rectangle</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.RoundedRectangle#getCornerHeight <em>Corner Height</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.RoundedRectangle#getCornerWidth <em>Corner Width</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.graphiti.mm.algorithms.AlgorithmsPackage#getRoundedRectangle()
 * @model
 * @generated
 */
public interface RoundedRectangle extends GraphicsAlgorithm {
	/**
	 * Returns the value of the '<em><b>Corner Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Corner Height</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Corner Height</em>' attribute.
	 * @see #setCornerHeight(int)
	 * @see org.eclipse.graphiti.mm.algorithms.AlgorithmsPackage#getRoundedRectangle_CornerHeight()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	int getCornerHeight();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.algorithms.RoundedRectangle#getCornerHeight <em>Corner Height</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Corner Height</em>' attribute.
	 * @see #getCornerHeight()
	 * @generated
	 */
	void setCornerHeight(int value);

	/**
	 * Returns the value of the '<em><b>Corner Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Corner Width</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Corner Width</em>' attribute.
	 * @see #setCornerWidth(int)
	 * @see org.eclipse.graphiti.mm.algorithms.AlgorithmsPackage#getRoundedRectangle_CornerWidth()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	int getCornerWidth();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.algorithms.RoundedRectangle#getCornerWidth <em>Corner Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Corner Width</em>' attribute.
	 * @see #getCornerWidth()
	 * @generated
	 */
	void setCornerWidth(int value);

} // RoundedRectangle
