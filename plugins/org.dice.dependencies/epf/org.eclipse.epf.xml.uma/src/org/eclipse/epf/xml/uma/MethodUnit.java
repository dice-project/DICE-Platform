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

import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Method Unit</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A special Method Element that shall be maintained in a Method Library as a separate unit of control.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.xml.uma.MethodUnit#getCopyright <em>Copyright</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.MethodUnit#getAuthors <em>Authors</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.MethodUnit#getChangeDate <em>Change Date</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.MethodUnit#getChangeDescription <em>Change Description</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.MethodUnit#getVersion <em>Version</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.xml.uma.UmaPackage#getMethodUnit()
 * @model extendedMetaData="name='MethodUnit' kind='elementOnly'"
 * @generated
 */
public interface MethodUnit extends MethodElement {
	/**
	 * Returns the value of the '<em><b>Copyright</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Copyright</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Copyright</em>' attribute.
	 * @see #setCopyright(String)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getMethodUnit_Copyright()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='Copyright'"
	 * @generated
	 */
	String getCopyright();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.MethodUnit#getCopyright <em>Copyright</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Copyright</em>' attribute.
	 * @see #getCopyright()
	 * @generated
	 */
	void setCopyright(String value);

	/**
	 * Returns the value of the '<em><b>Authors</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Every Method Unit is being created and owned by an author or authoring team.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Authors</em>' attribute.
	 * @see #setAuthors(String)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getMethodUnit_Authors()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='authors'"
	 * @generated
	 */
	String getAuthors();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.MethodUnit#getAuthors <em>Authors</em>}' attribute.
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
	 * @see #setChangeDate(XMLGregorianCalendar)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getMethodUnit_ChangeDate()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.DateTime"
	 *        extendedMetaData="kind='attribute' name='changeDate'"
	 * @generated
	 */
	XMLGregorianCalendar getChangeDate();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.MethodUnit#getChangeDate <em>Change Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Change Date</em>' attribute.
	 * @see #getChangeDate()
	 * @generated
	 */
	void setChangeDate(XMLGregorianCalendar value);

	/**
	 * Returns the value of the '<em><b>Change Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The description of the last change that resulted into this version.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Change Description</em>' attribute.
	 * @see #setChangeDescription(String)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getMethodUnit_ChangeDescription()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='changeDescription'"
	 * @generated
	 */
	String getChangeDescription();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.MethodUnit#getChangeDescription <em>Change Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Change Description</em>' attribute.
	 * @see #getChangeDescription()
	 * @generated
	 */
	void setChangeDescription(String value);

	/**
	 * Returns the value of the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Every Package has a version number used to track changes.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Version</em>' attribute.
	 * @see #setVersion(String)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getMethodUnit_Version()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='version'"
	 * @generated
	 */
	String getVersion();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.MethodUnit#getVersion <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Version</em>' attribute.
	 * @see #getVersion()
	 * @generated
	 */
	void setVersion(String value);

} // MethodUnit
