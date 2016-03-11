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
package org.eclipse.graphiti.mm.pictograms;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Box Relative Anchor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor#getRelativeWidth <em>Relative Width</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor#getRelativeHeight <em>Relative Height</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.graphiti.mm.pictograms.PictogramsPackage#getBoxRelativeAnchor()
 * @model
 * @generated
 */
public interface BoxRelativeAnchor extends Anchor {
	/**
	 * Returns the value of the '<em><b>Relative Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Relative Width</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Relative Width</em>' attribute.
	 * @see #setRelativeWidth(double)
	 * @see org.eclipse.graphiti.mm.pictograms.PictogramsPackage#getBoxRelativeAnchor_RelativeWidth()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	double getRelativeWidth();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor#getRelativeWidth <em>Relative Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Relative Width</em>' attribute.
	 * @see #getRelativeWidth()
	 * @generated
	 */
	void setRelativeWidth(double value);

	/**
	 * Returns the value of the '<em><b>Relative Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Relative Height</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Relative Height</em>' attribute.
	 * @see #setRelativeHeight(double)
	 * @see org.eclipse.graphiti.mm.pictograms.PictogramsPackage#getBoxRelativeAnchor_RelativeHeight()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	double getRelativeHeight();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor#getRelativeHeight <em>Relative Height</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Relative Height</em>' attribute.
	 * @see #getRelativeHeight()
	 * @generated
	 */
	void setRelativeHeight(double value);

} // BoxRelativeAnchor
