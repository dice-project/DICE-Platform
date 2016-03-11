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

import org.eclipse.graphiti.mm.algorithms.styles.Font;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Text</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.AbstractText#getFont <em>Font</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.AbstractText#getHorizontalAlignment <em>Horizontal Alignment</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.AbstractText#getVerticalAlignment <em>Vertical Alignment</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.AbstractText#getAngle <em>Angle</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.AbstractText#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.graphiti.mm.algorithms.AlgorithmsPackage#getAbstractText()
 * @model abstract="true"
 * @generated
 */
public interface AbstractText extends GraphicsAlgorithm {
	/**
	 * Returns the value of the '<em><b>Font</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Font</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Font</em>' containment reference.
	 * @see #setFont(Font)
	 * @see org.eclipse.graphiti.mm.algorithms.AlgorithmsPackage#getAbstractText_Font()
	 * @model containment="true" resolveProxies="true" ordered="false"
	 * @generated
	 */
	Font getFont();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.algorithms.AbstractText#getFont <em>Font</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Font</em>' containment reference.
	 * @see #getFont()
	 * @generated
	 */
	void setFont(Font value);

	/**
	 * Returns the value of the '<em><b>Horizontal Alignment</b></em>' attribute.
	 * The default value is <code>"ALIGNMENT_LEFT"</code>.
	 * The literals are from the enumeration {@link org.eclipse.graphiti.mm.algorithms.styles.Orientation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Horizontal Alignment</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Horizontal Alignment</em>' attribute.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.Orientation
	 * @see #setHorizontalAlignment(Orientation)
	 * @see org.eclipse.graphiti.mm.algorithms.AlgorithmsPackage#getAbstractText_HorizontalAlignment()
	 * @model default="ALIGNMENT_LEFT" unique="false" ordered="false"
	 * @generated
	 */
	Orientation getHorizontalAlignment();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.algorithms.AbstractText#getHorizontalAlignment <em>Horizontal Alignment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Horizontal Alignment</em>' attribute.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.Orientation
	 * @see #getHorizontalAlignment()
	 * @generated
	 */
	void setHorizontalAlignment(Orientation value);

	/**
	 * Returns the value of the '<em><b>Vertical Alignment</b></em>' attribute.
	 * The default value is <code>"ALIGNMENT_CENTER"</code>.
	 * The literals are from the enumeration {@link org.eclipse.graphiti.mm.algorithms.styles.Orientation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Vertical Alignment</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Vertical Alignment</em>' attribute.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.Orientation
	 * @see #setVerticalAlignment(Orientation)
	 * @see org.eclipse.graphiti.mm.algorithms.AlgorithmsPackage#getAbstractText_VerticalAlignment()
	 * @model default="ALIGNMENT_CENTER" unique="false" ordered="false"
	 * @generated
	 */
	Orientation getVerticalAlignment();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.algorithms.AbstractText#getVerticalAlignment <em>Vertical Alignment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Vertical Alignment</em>' attribute.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.Orientation
	 * @see #getVerticalAlignment()
	 * @generated
	 */
	void setVerticalAlignment(Orientation value);

	/**
	 * Returns the value of the '<em><b>Angle</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Angle</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Angle</em>' attribute.
	 * @see #setAngle(Integer)
	 * @see org.eclipse.graphiti.mm.algorithms.AlgorithmsPackage#getAbstractText_Angle()
	 * @model default="0" unique="false" ordered="false"
	 * @generated
	 */
	Integer getAngle();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.algorithms.AbstractText#getAngle <em>Angle</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Angle</em>' attribute.
	 * @see #getAngle()
	 * @generated
	 */
	void setAngle(Integer value);

	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(String)
	 * @see org.eclipse.graphiti.mm.algorithms.AlgorithmsPackage#getAbstractText_Value()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	String getValue();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.algorithms.AbstractText#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(String value);

} // AbstractText
