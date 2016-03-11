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

import org.eclipse.graphiti.mm.StyleContainer;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Style</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.styles.Style#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.styles.Style#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.styles.Style#getFont <em>Font</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.styles.Style#getHorizontalAlignment <em>Horizontal Alignment</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.styles.Style#getVerticalAlignment <em>Vertical Alignment</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.styles.Style#getAngle <em>Angle</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.styles.Style#getStretchH <em>Stretch H</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.styles.Style#getStretchV <em>Stretch V</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.styles.Style#getProportional <em>Proportional</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.styles.Style#getStyleContainer <em>Style Container</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.graphiti.mm.algorithms.styles.StylesPackage#getStyle()
 * @model
 * @generated
 */
public interface Style extends StyleContainer, AbstractStyle {
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
	 * @see org.eclipse.graphiti.mm.algorithms.styles.StylesPackage#getStyle_Id()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.algorithms.styles.Style#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see org.eclipse.graphiti.mm.algorithms.styles.StylesPackage#getStyle_Description()
	 * @model unique="false" ordered="false"
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.algorithms.styles.Style#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

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
	 * @see org.eclipse.graphiti.mm.algorithms.styles.StylesPackage#getStyle_Font()
	 * @model containment="true" resolveProxies="true" ordered="false"
	 * @generated
	 */
	Font getFont();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.algorithms.styles.Style#getFont <em>Font</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Font</em>' containment reference.
	 * @see #getFont()
	 * @generated
	 */
	void setFont(Font value);

	/**
	 * Returns the value of the '<em><b>Horizontal Alignment</b></em>' attribute.
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
	 * @see org.eclipse.graphiti.mm.algorithms.styles.StylesPackage#getStyle_HorizontalAlignment()
	 * @model unique="false" ordered="false"
	 * @generated
	 */
	Orientation getHorizontalAlignment();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.algorithms.styles.Style#getHorizontalAlignment <em>Horizontal Alignment</em>}' attribute.
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
	 * @see org.eclipse.graphiti.mm.algorithms.styles.StylesPackage#getStyle_VerticalAlignment()
	 * @model unique="false" ordered="false"
	 * @generated
	 */
	Orientation getVerticalAlignment();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.algorithms.styles.Style#getVerticalAlignment <em>Vertical Alignment</em>}' attribute.
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
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Angle</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Angle</em>' attribute.
	 * @see #setAngle(Integer)
	 * @see org.eclipse.graphiti.mm.algorithms.styles.StylesPackage#getStyle_Angle()
	 * @model unique="false" ordered="false"
	 * @generated
	 */
	Integer getAngle();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.algorithms.styles.Style#getAngle <em>Angle</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Angle</em>' attribute.
	 * @see #getAngle()
	 * @generated
	 */
	void setAngle(Integer value);

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
	 * @see org.eclipse.graphiti.mm.algorithms.styles.StylesPackage#getStyle_StretchH()
	 * @model unique="false" ordered="false"
	 * @generated
	 */
	Boolean getStretchH();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.algorithms.styles.Style#getStretchH <em>Stretch H</em>}' attribute.
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
	 * @see org.eclipse.graphiti.mm.algorithms.styles.StylesPackage#getStyle_StretchV()
	 * @model unique="false" ordered="false"
	 * @generated
	 */
	Boolean getStretchV();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.algorithms.styles.Style#getStretchV <em>Stretch V</em>}' attribute.
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
	 * @see org.eclipse.graphiti.mm.algorithms.styles.StylesPackage#getStyle_Proportional()
	 * @model unique="false" ordered="false"
	 * @generated
	 */
	Boolean getProportional();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.algorithms.styles.Style#getProportional <em>Proportional</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Proportional</em>' attribute.
	 * @see #getProportional()
	 * @generated
	 */
	void setProportional(Boolean value);

	/**
	 * Returns the value of the '<em><b>Style Container</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.graphiti.mm.StyleContainer#getStyles <em>Styles</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Style Container</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Style Container</em>' container reference.
	 * @see #setStyleContainer(StyleContainer)
	 * @see org.eclipse.graphiti.mm.algorithms.styles.StylesPackage#getStyle_StyleContainer()
	 * @see org.eclipse.graphiti.mm.StyleContainer#getStyles
	 * @model opposite="styles" required="true" transient="false" ordered="false"
	 * @generated
	 */
	StyleContainer getStyleContainer();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.algorithms.styles.Style#getStyleContainer <em>Style Container</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Style Container</em>' container reference.
	 * @see #getStyleContainer()
	 * @generated
	 */
	void setStyleContainer(StyleContainer value);

} // Style
