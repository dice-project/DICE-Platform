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
 * A representation of the model object '<em><b>Image</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.Image#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.Image#getStretchH <em>Stretch H</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.Image#getStretchV <em>Stretch V</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.Image#getProportional <em>Proportional</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.graphiti.mm.algorithms.AlgorithmsPackage#getImage()
 * @model
 * @generated
 */
public interface Image extends GraphicsAlgorithm {
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.eclipse.graphiti.mm.algorithms.AlgorithmsPackage#getImage_Id()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.algorithms.Image#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Stretch H</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Stretch H</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stretch H</em>' attribute.
	 * @see #setStretchH(Boolean)
	 * @see org.eclipse.graphiti.mm.algorithms.AlgorithmsPackage#getImage_StretchH()
	 * @model unique="false" ordered="false"
	 * @generated
	 */
	Boolean getStretchH();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.algorithms.Image#getStretchH <em>Stretch H</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stretch H</em>' attribute.
	 * @see #getStretchH()
	 * @generated
	 */
	void setStretchH(Boolean value);

	/**
	 * Returns the value of the '<em><b>Stretch V</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Stretch V</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stretch V</em>' attribute.
	 * @see #setStretchV(Boolean)
	 * @see org.eclipse.graphiti.mm.algorithms.AlgorithmsPackage#getImage_StretchV()
	 * @model unique="false" ordered="false"
	 * @generated
	 */
	Boolean getStretchV();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.algorithms.Image#getStretchV <em>Stretch V</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stretch V</em>' attribute.
	 * @see #getStretchV()
	 * @generated
	 */
	void setStretchV(Boolean value);

	/**
	 * Returns the value of the '<em><b>Proportional</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Proportional</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Proportional</em>' attribute.
	 * @see #setProportional(Boolean)
	 * @see org.eclipse.graphiti.mm.algorithms.AlgorithmsPackage#getImage_Proportional()
	 * @model unique="false" ordered="false"
	 * @generated
	 */
	Boolean getProportional();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.algorithms.Image#getProportional <em>Proportional</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Proportional</em>' attribute.
	 * @see #getProportional()
	 * @generated
	 */
	void setProportional(Boolean value);

} // Image
