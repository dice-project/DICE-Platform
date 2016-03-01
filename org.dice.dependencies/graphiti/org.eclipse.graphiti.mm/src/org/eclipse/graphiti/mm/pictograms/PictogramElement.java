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

import org.eclipse.graphiti.mm.GraphicsAlgorithmContainer;

import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Pictogram Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.PictogramElement#isVisible <em>Visible</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.PictogramElement#getGraphicsAlgorithm <em>Graphics Algorithm</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.PictogramElement#isActive <em>Active</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.PictogramElement#getLink <em>Link</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.graphiti.mm.pictograms.PictogramsPackage#getPictogramElement()
 * @model abstract="true"
 * @generated
 */
public interface PictogramElement extends GraphicsAlgorithmContainer {
	/**
	 * Returns the value of the '<em><b>Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * This method is reserved for future usage and is currently not supported by the framework.
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Visible</em>' attribute.
	 * @see #setVisible(boolean)
	 * @see org.eclipse.graphiti.mm.pictograms.PictogramsPackage#getPictogramElement_Visible()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	boolean isVisible();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.pictograms.PictogramElement#isVisible <em>Visible</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * This method is reserved for future usage and is currently not supported by the framework.
	 * </p>
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Visible</em>' attribute.
	 * @see #isVisible()
	 * @generated
	 */
	void setVisible(boolean value);

	/**
	 * Returns the value of the '<em><b>Graphics Algorithm</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm#getPictogramElement <em>Pictogram Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Graphics Algorithm</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Graphics Algorithm</em>' containment reference.
	 * @see #setGraphicsAlgorithm(GraphicsAlgorithm)
	 * @see org.eclipse.graphiti.mm.pictograms.PictogramsPackage#getPictogramElement_GraphicsAlgorithm()
	 * @see org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm#getPictogramElement
	 * @model opposite="pictogramElement" containment="true" resolveProxies="true" ordered="false"
	 * @generated
	 */
	GraphicsAlgorithm getGraphicsAlgorithm();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.pictograms.PictogramElement#getGraphicsAlgorithm <em>Graphics Algorithm</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Graphics Algorithm</em>' containment reference.
	 * @see #getGraphicsAlgorithm()
	 * @generated
	 */
	void setGraphicsAlgorithm(GraphicsAlgorithm value);

	/**
	 * Returns the value of the '<em><b>Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Active</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Active</em>' attribute.
	 * @see #setActive(boolean)
	 * @see org.eclipse.graphiti.mm.pictograms.PictogramsPackage#getPictogramElement_Active()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	boolean isActive();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.pictograms.PictogramElement#isActive <em>Active</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Active</em>' attribute.
	 * @see #isActive()
	 * @generated
	 */
	void setActive(boolean value);

	/**
	 * Returns the value of the '<em><b>Link</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.graphiti.mm.pictograms.PictogramLink#getPictogramElement <em>Pictogram Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Link</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Link</em>' containment reference.
	 * @see #setLink(PictogramLink)
	 * @see org.eclipse.graphiti.mm.pictograms.PictogramsPackage#getPictogramElement_Link()
	 * @see org.eclipse.graphiti.mm.pictograms.PictogramLink#getPictogramElement
	 * @model opposite="pictogramElement" containment="true" resolveProxies="true" ordered="false"
	 * @generated
	 */
	PictogramLink getLink();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.pictograms.PictogramElement#getLink <em>Link</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Link</em>' containment reference.
	 * @see #getLink()
	 * @generated
	 */
	void setLink(PictogramLink value);

} // PictogramElement
