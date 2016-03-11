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

import java.util.Date;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Method Unit</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A Method Unit is a special Method Element that shall be maintained in a Method Library as a separate unit of control.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.MethodUnit#getAuthors <em>Authors</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.MethodUnit#getChangeDate <em>Change Date</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.MethodUnit#getChangeDescription <em>Change Description</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.MethodUnit#getVersion <em>Version</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.MethodUnit#getCopyrightStatement <em>Copyright Statement</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.uma.UmaPackage#getMethodUnit()
 * @model abstract="true"
 * @generated
 */
public interface MethodUnit extends MethodElement {
	/**
	 * Returns the value of the '<em><b>Authors</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Every Method Unit is being created and owned by an author or authoring team.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Authors</em>' attribute.
	 * @see #setAuthors(String)
	 * @see org.eclipse.epf.uma.UmaPackage#getMethodUnit_Authors()
	 * @model default="" dataType="org.eclipse.epf.uma.String" ordered="false"
	 * @generated
	 */
	String getAuthors();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.MethodUnit#getAuthors <em>Authors</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Authors</em>' attribute.
	 * @see #getAuthors()
	 * @generated
	 */
	void setAuthors(String value);

	/**
	 * Returns the value of the '<em><b>Change Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The date the last change that resulted into this version has been made.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Change Date</em>' attribute.
	 * @see #setChangeDate(Date)
	 * @see org.eclipse.epf.uma.UmaPackage#getMethodUnit_ChangeDate()
	 * @model dataType="org.eclipse.epf.uma.Date" ordered="false"
	 * @generated
	 */
	Date getChangeDate();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.MethodUnit#getChangeDate <em>Change Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Change Date</em>' attribute.
	 * @see #getChangeDate()
	 * @generated
	 */
	void setChangeDate(Date value);

	/**
	 * Returns the value of the '<em><b>Change Description</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The description of the last change that resulted into this version.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Change Description</em>' attribute.
	 * @see #setChangeDescription(String)
	 * @see org.eclipse.epf.uma.UmaPackage#getMethodUnit_ChangeDescription()
	 * @model default="" dataType="org.eclipse.epf.uma.String" ordered="false"
	 * @generated
	 */
	String getChangeDescription();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.MethodUnit#getChangeDescription <em>Change Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Change Description</em>' attribute.
	 * @see #getChangeDescription()
	 * @generated
	 */
	void setChangeDescription(String value);

	/**
	 * Returns the value of the '<em><b>Version</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Every Package has a version number used to track changes.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Version</em>' attribute.
	 * @see #setVersion(String)
	 * @see org.eclipse.epf.uma.UmaPackage#getMethodUnit_Version()
	 * @model default="" dataType="org.eclipse.epf.uma.String" ordered="false"
	 * @generated
	 */
	String getVersion();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.MethodUnit#getVersion <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Version</em>' attribute.
	 * @see #getVersion()
	 * @generated
	 */
	void setVersion(String value);

	/**
	 * Returns the value of the '<em><b>Copyright Statement</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Copyright Statement</em>' reference.
	 * @see #setCopyrightStatement(SupportingMaterial)
	 * @see org.eclipse.epf.uma.UmaPackage#getMethodUnit_CopyrightStatement()
	 * @model ordered="false"
	 * @generated
	 */
	SupportingMaterial getCopyrightStatement();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.MethodUnit#getCopyrightStatement <em>Copyright Statement</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Copyright Statement</em>' reference.
	 * @see #getCopyrightStatement()
	 * @generated
	 */
	void setCopyrightStatement(SupportingMaterial value);

} // MethodUnit
