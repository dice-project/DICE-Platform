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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.Reference#getIsIndividualRepresentation <em>Is Individual Representation</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.Reference#getReferenced <em>Referenced</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.uma.UmaPackage#getReference()
 * @model
 * @generated
 */
public interface Reference extends DiagramElement {
	/**
	 * Returns the value of the '<em><b>Is Individual Representation</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Individual Representation</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Individual Representation</em>' attribute.
	 * @see #setIsIndividualRepresentation(Boolean)
	 * @see org.eclipse.epf.uma.UmaPackage#getReference_IsIndividualRepresentation()
	 * @model default="false" dataType="org.eclipse.epf.uma.Boolean" required="true" ordered="false"
	 * @generated
	 */
	Boolean getIsIndividualRepresentation();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.Reference#getIsIndividualRepresentation <em>Is Individual Representation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Individual Representation</em>' attribute.
	 * @see #getIsIndividualRepresentation()
	 * @generated
	 */
	void setIsIndividualRepresentation(Boolean value);

	/**
	 * Returns the value of the '<em><b>Referenced</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.epf.uma.DiagramElement#getReference <em>Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Referenced</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Referenced</em>' reference.
	 * @see #setReferenced(DiagramElement)
	 * @see org.eclipse.epf.uma.UmaPackage#getReference_Referenced()
	 * @see org.eclipse.epf.uma.DiagramElement#getReference
	 * @model opposite="reference" required="true" ordered="false"
	 * @generated
	 */
	DiagramElement getReferenced();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.Reference#getReferenced <em>Referenced</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Referenced</em>' reference.
	 * @see #getReferenced()
	 * @generated
	 */
	void setReferenced(DiagramElement value);

} // Reference
