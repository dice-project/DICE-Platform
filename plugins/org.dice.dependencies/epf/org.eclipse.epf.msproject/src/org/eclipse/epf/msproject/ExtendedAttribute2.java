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
 * A representation of the model object '<em><b>Extended Attribute2</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.msproject.ExtendedAttribute2#getUID <em>UID</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.ExtendedAttribute2#getFieldID <em>Field ID</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.ExtendedAttribute2#getValue <em>Value</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.ExtendedAttribute2#getValueID <em>Value ID</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.ExtendedAttribute2#getDurationFormat <em>Duration Format</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.msproject.MsprojectPackage#getExtendedAttribute2()
 * @model extendedMetaData="name='ExtendedAttribute_._1_._type' kind='elementOnly'"
 * @generated
 */
public interface ExtendedAttribute2 extends EObject {
	/**
	 * Returns the value of the '<em><b>UID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The unique ID of the value in the extended attribute collection.
	 *                                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>UID</em>' attribute.
	 * @see #setUID(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getExtendedAttribute2_UID()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Integer"
	 *        extendedMetaData="kind='element' name='UID' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getUID();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.ExtendedAttribute2#getUID <em>UID</em>}' attribute.
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
	 * The PID of the custom field.
	 *                                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Field ID</em>' attribute.
	 * @see #setFieldID(String)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getExtendedAttribute2_FieldID()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='FieldID' namespace='##targetNamespace'"
	 * @generated
	 */
	String getFieldID();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.ExtendedAttribute2#getFieldID <em>Field ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Field ID</em>' attribute.
	 * @see #getFieldID()
	 * @generated
	 */
	void setFieldID(String value);

	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The actual value of the extended attribute.
	 *                                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(String)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getExtendedAttribute2_Value()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='Value' namespace='##targetNamespace'"
	 * @generated
	 */
	String getValue();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.ExtendedAttribute2#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(String value);

	/**
	 * Returns the value of the '<em><b>Value ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The ID of the value in the extended attribut lookup table.
	 *                                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Value ID</em>' attribute.
	 * @see #setValueID(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getExtendedAttribute2_ValueID()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Integer"
	 *        extendedMetaData="kind='element' name='ValueID' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getValueID();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.ExtendedAttribute2#getValueID <em>Value ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value ID</em>' attribute.
	 * @see #getValueID()
	 * @generated
	 */
	void setValueID(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Duration Format</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The format for expressing the bulk duration.  
	 *                                             Values are: 3=m, 4=em, 5=h, 6=eh, 7=d, 8=ed, 9=w, 10=ew, 11=mo, 12=emo, 
	 *                                             19=%, 20=e%, 21=null, 35=m?, 36=em?, 37=h?, 38=eh?, 39=d?, 40=ed?, 41=w?, 
	 *                                             42=ew?, 43=mo?, 44=emo?, 51=%?, 52=e%? and 53=null.
	 *                                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Duration Format</em>' attribute.
	 * @see #setDurationFormat(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getExtendedAttribute2_DurationFormat()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.DurationFormatType2"
	 *        extendedMetaData="kind='element' name='DurationFormat' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getDurationFormat();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.ExtendedAttribute2#getDurationFormat <em>Duration Format</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Duration Format</em>' attribute.
	 * @see #getDurationFormat()
	 * @generated
	 */
	void setDurationFormat(BigInteger value);

} // ExtendedAttribute2
