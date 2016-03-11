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
package org.eclipse.epf.msproject;

import java.math.BigInteger;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Outline Code3</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.msproject.OutlineCode3#getUID <em>UID</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.OutlineCode3#getFieldID <em>Field ID</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.OutlineCode3#getValueID <em>Value ID</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.msproject.MsprojectPackage#getOutlineCode3()
 * @model extendedMetaData="name='OutlineCode_._2_._type' kind='elementOnly'"
 * @generated
 */
public interface OutlineCode3 extends EObject {
	/**
	 * Returns the value of the '<em><b>UID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The unique ID of the value in the outline code collection.
	 *                                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>UID</em>' attribute.
	 * @see #setUID(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getOutlineCode3_UID()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Integer"
	 *        extendedMetaData="kind='element' name='UID' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getUID();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.OutlineCode3#getUID <em>UID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>UID</em>' attribute.
	 * @see #getUID()
	 * @generated
	 */
	void setUID(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Field ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The field ID in the localised language.
	 *                                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Field ID</em>' attribute.
	 * @see #setFieldID(String)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getOutlineCode3_FieldID()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='FieldID' namespace='##targetNamespace'"
	 * @generated
	 */
	String getFieldID();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.OutlineCode3#getFieldID <em>Field ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Field ID</em>' attribute.
	 * @see #getFieldID()
	 * @generated
	 */
	void setFieldID(String value);

	/**
	 * Returns the value of the '<em><b>Value ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The unique ID in the value list associated with the definition 
	 *                                             in the outline code collection.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Value ID</em>' attribute.
	 * @see #setValueID(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getOutlineCode3_ValueID()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Integer"
	 *        extendedMetaData="kind='element' name='ValueID' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getValueID();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.OutlineCode3#getValueID <em>Value ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value ID</em>' attribute.
	 * @see #getValueID()
	 * @generated
	 */
	void setValueID(BigInteger value);

} // OutlineCode3
