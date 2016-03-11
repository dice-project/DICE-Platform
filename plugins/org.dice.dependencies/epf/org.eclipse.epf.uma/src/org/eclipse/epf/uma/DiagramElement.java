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
package org.eclipse.epf.uma;

import java.util.List;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Diagram Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.DiagramElement#getIsVisible <em>Is Visible</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.DiagramElement#getContainer <em>Container</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.DiagramElement#getReference <em>Reference</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.DiagramElement#getProperty <em>Property</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.uma.UmaPackage#getDiagramElement()
 * @model abstract="true"
 * @generated
 */
public interface DiagramElement extends MethodElement {
	/**
	 * Returns the value of the '<em><b>Is Visible</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Visible</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Visible</em>' attribute.
	 * @see #setIsVisible(Boolean)
	 * @see org.eclipse.epf.uma.UmaPackage#getDiagramElement_IsVisible()
	 * @model default="true" dataType="org.eclipse.epf.uma.Boolean" required="true" ordered="false"
	 * @generated
	 */
	Boolean getIsVisible();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.DiagramElement#getIsVisible <em>Is Visible</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Visible</em>' attribute.
	 * @see #getIsVisible()
	 * @generated
	 */
	void setIsVisible(Boolean value);

	/**
	 * Returns the value of the '<em><b>Container</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.epf.uma.GraphElement#getContained <em>Contained</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Container</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Container</em>' container reference.
	 * @see #setContainer(GraphElement)
	 * @see org.eclipse.epf.uma.UmaPackage#getDiagramElement_Container()
	 * @see org.eclipse.epf.uma.GraphElement#getContained
	 * @model opposite="contained" transient="false" ordered="false"
	 * @generated
	 */
	GraphElement getContainer();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.DiagramElement#getContainer <em>Container</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Container</em>' container reference.
	 * @see #getContainer()
	 * @generated
	 */
	void setContainer(GraphElement value);

	/**
	 * Returns the value of the '<em><b>Reference</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.Reference}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.epf.uma.Reference#getReferenced <em>Referenced</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reference</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reference</em>' reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getDiagramElement_Reference()
	 * @see org.eclipse.epf.uma.Reference#getReferenced
	 * @model opposite="referenced" ordered="false"
	 * @generated
	 */
	List<Reference> getReference();

	/**
	 * Returns the value of the '<em><b>Property</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.Property}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Property</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Property</em>' containment reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getDiagramElement_Property()
	 * @model containment="true" resolveProxies="true" ordered="false"
	 * @generated
	 */
	List<Property> getProperty();

} // DiagramElement
