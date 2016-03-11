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
package org.eclipse.graphiti.mm.algorithms.styles;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Style</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.styles.AbstractStyle#getBackground <em>Background</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.styles.AbstractStyle#getForeground <em>Foreground</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.styles.AbstractStyle#getLineWidth <em>Line Width</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.styles.AbstractStyle#getLineStyle <em>Line Style</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.styles.AbstractStyle#getFilled <em>Filled</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.styles.AbstractStyle#getLineVisible <em>Line Visible</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.styles.AbstractStyle#getRenderingStyle <em>Rendering Style</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.styles.AbstractStyle#getTransparency <em>Transparency</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.graphiti.mm.algorithms.styles.StylesPackage#getAbstractStyle()
 * @model abstract="true"
 * @generated
 */
public interface AbstractStyle extends EObject {
	/**
	 * Returns the value of the '<em><b>Background</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Background</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Background</em>' reference.
	 * @see #setBackground(Color)
	 * @see org.eclipse.graphiti.mm.algorithms.styles.StylesPackage#getAbstractStyle_Background()
	 * @model ordered="false"
	 * @generated
	 */
	Color getBackground();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.algorithms.styles.AbstractStyle#getBackground <em>Background</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Background</em>' reference.
	 * @see #getBackground()
	 * @generated
	 */
	void setBackground(Color value);

	/**
	 * Returns the value of the '<em><b>Foreground</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Foreground</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Foreground</em>' reference.
	 * @see #setForeground(Color)
	 * @see org.eclipse.graphiti.mm.algorithms.styles.StylesPackage#getAbstractStyle_Foreground()
	 * @model ordered="false"
	 * @generated
	 */
	Color getForeground();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.algorithms.styles.AbstractStyle#getForeground <em>Foreground</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Foreground</em>' reference.
	 * @see #getForeground()
	 * @generated
	 */
	void setForeground(Color value);

	/**
	 * Returns the value of the '<em><b>Line Width</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Line Width</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Line Width</em>' attribute.
	 * @see #setLineWidth(Integer)
	 * @see org.eclipse.graphiti.mm.algorithms.styles.StylesPackage#getAbstractStyle_LineWidth()
	 * @model default="1" unique="false" ordered="false"
	 * @generated
	 */
	Integer getLineWidth();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.algorithms.styles.AbstractStyle#getLineWidth <em>Line Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Line Width</em>' attribute.
	 * @see #getLineWidth()
	 * @generated
	 */
	void setLineWidth(Integer value);

	/**
	 * Returns the value of the '<em><b>Line Style</b></em>' attribute.
	 * The default value is <code>"SOLID"</code>.
	 * The literals are from the enumeration {@link org.eclipse.graphiti.mm.algorithms.styles.LineStyle}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Line Style</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Line Style</em>' attribute.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.LineStyle
	 * @see #setLineStyle(LineStyle)
	 * @see org.eclipse.graphiti.mm.algorithms.styles.StylesPackage#getAbstractStyle_LineStyle()
	 * @model default="SOLID" unique="false" ordered="false"
	 * @generated
	 */
	LineStyle getLineStyle();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.algorithms.styles.AbstractStyle#getLineStyle <em>Line Style</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Line Style</em>' attribute.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.LineStyle
	 * @see #getLineStyle()
	 * @generated
	 */
	void setLineStyle(LineStyle value);

	/**
	 * Returns the value of the '<em><b>Filled</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Filled</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Filled</em>' attribute.
	 * @see #setFilled(Boolean)
	 * @see org.eclipse.graphiti.mm.algorithms.styles.StylesPackage#getAbstractStyle_Filled()
	 * @model default="true" unique="false" ordered="false"
	 * @generated
	 */
	Boolean getFilled();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.algorithms.styles.AbstractStyle#getFilled <em>Filled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Filled</em>' attribute.
	 * @see #getFilled()
	 * @generated
	 */
	void setFilled(Boolean value);

	/**
	 * Returns the value of the '<em><b>Line Visible</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Line Visible</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Line Visible</em>' attribute.
	 * @see #setLineVisible(Boolean)
	 * @see org.eclipse.graphiti.mm.algorithms.styles.StylesPackage#getAbstractStyle_LineVisible()
	 * @model default="true" unique="false" ordered="false"
	 * @generated
	 */
	Boolean getLineVisible();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.algorithms.styles.AbstractStyle#getLineVisible <em>Line Visible</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Line Visible</em>' attribute.
	 * @see #getLineVisible()
	 * @generated
	 */
	void setLineVisible(Boolean value);

	/**
	 * Returns the value of the '<em><b>Rendering Style</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rendering Style</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rendering Style</em>' containment reference.
	 * @see #setRenderingStyle(RenderingStyle)
	 * @see org.eclipse.graphiti.mm.algorithms.styles.StylesPackage#getAbstractStyle_RenderingStyle()
	 * @model containment="true" resolveProxies="true" ordered="false"
	 * @generated
	 */
	RenderingStyle getRenderingStyle();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.algorithms.styles.AbstractStyle#getRenderingStyle <em>Rendering Style</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rendering Style</em>' containment reference.
	 * @see #getRenderingStyle()
	 * @generated
	 */
	void setRenderingStyle(RenderingStyle value);

	/**
	 * Returns the value of the '<em><b>Transparency</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transparency</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Transparency</em>' attribute.
	 * @see #setTransparency(Double)
	 * @see org.eclipse.graphiti.mm.algorithms.styles.StylesPackage#getAbstractStyle_Transparency()
	 * @model default="0" unique="false" ordered="false"
	 * @generated
	 */
	Double getTransparency();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.algorithms.styles.AbstractStyle#getTransparency <em>Transparency</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Transparency</em>' attribute.
	 * @see #getTransparency()
	 * @generated
	 */
	void setTransparency(Double value);

} // AbstractStyle
