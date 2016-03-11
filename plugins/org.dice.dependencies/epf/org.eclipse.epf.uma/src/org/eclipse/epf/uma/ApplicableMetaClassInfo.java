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
 * A representation of the model object '<em><b>Applicable Meta Class Info</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.ApplicableMetaClassInfo#getIsPrimaryExtension <em>Is Primary Extension</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.uma.UmaPackage#getApplicableMetaClassInfo()
 * @model
 * @generated
 */
public interface ApplicableMetaClassInfo extends Classifier {
	/**
	 * Returns the value of the '<em><b>Is Primary Extension</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Primary Extension</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Primary Extension</em>' attribute.
	 * @see #setIsPrimaryExtension(Boolean)
	 * @see org.eclipse.epf.uma.UmaPackage#getApplicableMetaClassInfo_IsPrimaryExtension()
	 * @model dataType="org.eclipse.epf.uma.Boolean" required="true" ordered="false"
	 * @generated
	 */
	Boolean getIsPrimaryExtension();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.ApplicableMetaClassInfo#getIsPrimaryExtension <em>Is Primary Extension</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Primary Extension</em>' attribute.
	 * @see #getIsPrimaryExtension()
	 * @generated
	 */
	void setIsPrimaryExtension(Boolean value);

} // ApplicableMetaClassInfo
