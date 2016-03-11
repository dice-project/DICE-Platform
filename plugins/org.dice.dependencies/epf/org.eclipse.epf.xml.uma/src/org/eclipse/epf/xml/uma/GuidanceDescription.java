/*******************************************************************************
 * Copyright (c) 2005, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial implementation
 *******************************************************************************/
package org.eclipse.epf.xml.uma;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Guidance Description</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A generalized Content Description that is used to store the textual description for a Guidance.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.xml.uma.GuidanceDescription#getAttachment <em>Attachment</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.xml.uma.UmaPackage#getGuidanceDescription()
 * @model extendedMetaData="name='GuidanceDescription' kind='elementOnly'"
 * @generated
 */
public interface GuidanceDescription extends ContentDescription {
	/**
	 * Returns the value of the '<em><b>Attachment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * This field is primarily used for attachments augmenting the information provided for guidance.  In particular the attribute is used for Templates, Examples, and Reusable Assets to contain the actual attachment described in the mainDescription.  It can additionally contain representations of the guidance in just a third party format, e.g. PDF, MS Word, or Word Perfect.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Attachment</em>' attribute.
	 * @see #setAttachment(String)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getGuidanceDescription_Attachment()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='Attachment'"
	 * @generated
	 */
	String getAttachment();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.GuidanceDescription#getAttachment <em>Attachment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Attachment</em>' attribute.
	 * @see #getAttachment()
	 * @generated
	 */
	void setAttachment(String value);

} // GuidanceDescription
