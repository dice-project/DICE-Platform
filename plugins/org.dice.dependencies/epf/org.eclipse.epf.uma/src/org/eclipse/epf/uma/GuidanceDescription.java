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
 * A representation of the model object '<em><b>Guidance Description</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.GuidanceDescription#getAttachments <em>Attachments</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.uma.UmaPackage#getGuidanceDescription()
 * @model
 * @generated
 */
public interface GuidanceDescription extends ContentDescription {
	/**
	 * Returns the value of the '<em><b>Attachments</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * This field is primarily used for attachments augmenting the information provided for guidance.  In particular the attribute is used for Templates, Examples, and Reusable Assets to contain the actual attachment described in the mainDescription.  It can additionally contain representations of the guidance in just a third party format, e.g. PDF, MS Word, or Word Perfect.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Attachments</em>' attribute.
	 * @see #setAttachments(String)
	 * @see org.eclipse.epf.uma.UmaPackage#getGuidanceDescription_Attachments()
	 * @model default="" dataType="org.eclipse.epf.uma.String" ordered="false"
	 * @generated
	 */
	String getAttachments();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.GuidanceDescription#getAttachments <em>Attachments</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Attachments</em>' attribute.
	 * @see #getAttachments()
	 * @generated
	 */
	void setAttachments(String value);

} // GuidanceDescription
