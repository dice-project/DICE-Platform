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
 * A representation of the model object '<em><b>Applicable Meta Class Info</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.xml.uma.ApplicableMetaClassInfo#isIsPrimaryExtension <em>Is Primary Extension</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.xml.uma.UmaPackage#getApplicableMetaClassInfo()
 * @model extendedMetaData="name='ApplicableMetaClassInfo' kind='empty'"
 * @generated
 */
public interface ApplicableMetaClassInfo extends PackageableElement {
	/**
	 * Returns the value of the '<em><b>Is Primary Extension</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Primary Extension</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Primary Extension</em>' attribute.
	 * @see #isSetIsPrimaryExtension()
	 * @see #unsetIsPrimaryExtension()
	 * @see #setIsPrimaryExtension(boolean)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getApplicableMetaClassInfo_IsPrimaryExtension()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='isPrimaryExtension'"
	 * @generated
	 */
	boolean isIsPrimaryExtension();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.ApplicableMetaClassInfo#isIsPrimaryExtension <em>Is Primary Extension</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Primary Extension</em>' attribute.
	 * @see #isSetIsPrimaryExtension()
	 * @see #unsetIsPrimaryExtension()
	 * @see #isIsPrimaryExtension()
	 * @generated
	 */
	void setIsPrimaryExtension(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.xml.uma.ApplicableMetaClassInfo#isIsPrimaryExtension <em>Is Primary Extension</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetIsPrimaryExtension()
	 * @see #isIsPrimaryExtension()
	 * @see #setIsPrimaryExtension(boolean)
	 * @generated
	 */
	void unsetIsPrimaryExtension();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.xml.uma.ApplicableMetaClassInfo#isIsPrimaryExtension <em>Is Primary Extension</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Is Primary Extension</em>' attribute is set.
	 * @see #unsetIsPrimaryExtension()
	 * @see #isIsPrimaryExtension()
	 * @see #setIsPrimaryExtension(boolean)
	 * @generated
	 */
	boolean isSetIsPrimaryExtension();

} // ApplicableMetaClassInfo
