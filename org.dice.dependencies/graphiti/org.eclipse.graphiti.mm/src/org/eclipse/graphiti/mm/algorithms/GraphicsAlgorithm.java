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

import org.eclipse.emf.common.util.EList;

import org.eclipse.graphiti.mm.GraphicsAlgorithmContainer;

import org.eclipse.graphiti.mm.algorithms.styles.AbstractStyle;
import org.eclipse.graphiti.mm.algorithms.styles.Style;

import org.eclipse.graphiti.mm.pictograms.PictogramElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Graphics Algorithm</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm#getGraphicsAlgorithmChildren <em>Graphics Algorithm Children</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm#getParentGraphicsAlgorithm <em>Parent Graphics Algorithm</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm#getPictogramElement <em>Pictogram Element</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm#getWidth <em>Width</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm#getHeight <em>Height</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm#getX <em>X</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm#getY <em>Y</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm#getStyle <em>Style</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.graphiti.mm.algorithms.AlgorithmsPackage#getGraphicsAlgorithm()
 * @model abstract="true"
 * @generated
 */
public interface GraphicsAlgorithm extends GraphicsAlgorithmContainer, AbstractStyle {
	/**
	 * Returns the value of the '<em><b>Graphics Algorithm Children</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm#getParentGraphicsAlgorithm <em>Parent Graphics Algorithm</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Graphics Algorithm Children</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Graphics Algorithm Children</em>' containment reference list.
	 * @see org.eclipse.graphiti.mm.algorithms.AlgorithmsPackage#getGraphicsAlgorithm_GraphicsAlgorithmChildren()
	 * @see org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm#getParentGraphicsAlgorithm
	 * @model opposite="parentGraphicsAlgorithm" containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<GraphicsAlgorithm> getGraphicsAlgorithmChildren();

	/**
	 * Returns the value of the '<em><b>Parent Graphics Algorithm</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm#getGraphicsAlgorithmChildren <em>Graphics Algorithm Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent Graphics Algorithm</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent Graphics Algorithm</em>' container reference.
	 * @see #setParentGraphicsAlgorithm(GraphicsAlgorithm)
	 * @see org.eclipse.graphiti.mm.algorithms.AlgorithmsPackage#getGraphicsAlgorithm_ParentGraphicsAlgorithm()
	 * @see org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm#getGraphicsAlgorithmChildren
	 * @model opposite="graphicsAlgorithmChildren" transient="false" ordered="false"
	 * @generated
	 */
	GraphicsAlgorithm getParentGraphicsAlgorithm();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm#getParentGraphicsAlgorithm <em>Parent Graphics Algorithm</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent Graphics Algorithm</em>' container reference.
	 * @see #getParentGraphicsAlgorithm()
	 * @generated
	 */
	void setParentGraphicsAlgorithm(GraphicsAlgorithm value);

	/**
	 * Returns the value of the '<em><b>Pictogram Element</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.graphiti.mm.pictograms.PictogramElement#getGraphicsAlgorithm <em>Graphics Algorithm</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pictogram Element</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pictogram Element</em>' container reference.
	 * @see #setPictogramElement(PictogramElement)
	 * @see org.eclipse.graphiti.mm.algorithms.AlgorithmsPackage#getGraphicsAlgorithm_PictogramElement()
	 * @see org.eclipse.graphiti.mm.pictograms.PictogramElement#getGraphicsAlgorithm
	 * @model opposite="graphicsAlgorithm" transient="false" ordered="false"
	 * @generated
	 */
	PictogramElement getPictogramElement();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm#getPictogramElement <em>Pictogram Element</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pictogram Element</em>' container reference.
	 * @see #getPictogramElement()
	 * @generated
	 */
	void setPictogramElement(PictogramElement value);

	/**
	 * Returns the value of the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Width</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Width</em>' attribute.
	 * @see #setWidth(int)
	 * @see org.eclipse.graphiti.mm.algorithms.AlgorithmsPackage#getGraphicsAlgorithm_Width()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	int getWidth();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm#getWidth <em>Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Width</em>' attribute.
	 * @see #getWidth()
	 * @generated
	 */
	void setWidth(int value);

	/**
	 * Returns the value of the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Height</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Height</em>' attribute.
	 * @see #setHeight(int)
	 * @see org.eclipse.graphiti.mm.algorithms.AlgorithmsPackage#getGraphicsAlgorithm_Height()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	int getHeight();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm#getHeight <em>Height</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Height</em>' attribute.
	 * @see #getHeight()
	 * @generated
	 */
	void setHeight(int value);

	/**
	 * Returns the value of the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>X</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>X</em>' attribute.
	 * @see #setX(int)
	 * @see org.eclipse.graphiti.mm.algorithms.AlgorithmsPackage#getGraphicsAlgorithm_X()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	int getX();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm#getX <em>X</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>X</em>' attribute.
	 * @see #getX()
	 * @generated
	 */
	void setX(int value);

	/**
	 * Returns the value of the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Y</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Y</em>' attribute.
	 * @see #setY(int)
	 * @see org.eclipse.graphiti.mm.algorithms.AlgorithmsPackage#getGraphicsAlgorithm_Y()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	int getY();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm#getY <em>Y</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Y</em>' attribute.
	 * @see #getY()
	 * @generated
	 */
	void setY(int value);

	/**
	 * Returns the value of the '<em><b>Style</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Style</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Style</em>' reference.
	 * @see #setStyle(Style)
	 * @see org.eclipse.graphiti.mm.algorithms.AlgorithmsPackage#getGraphicsAlgorithm_Style()
	 * @model ordered="false"
	 * @generated
	 */
	Style getStyle();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm#getStyle <em>Style</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Style</em>' reference.
	 * @see #getStyle()
	 * @generated
	 */
	void setStyle(Style value);

} // GraphicsAlgorithm
