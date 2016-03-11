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
 * A representation of the model object '<em><b>Mask</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.msproject.Mask#getLevel <em>Level</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Mask#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Mask#getLength <em>Length</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Mask#getSeparator <em>Separator</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.msproject.MsprojectPackage#getMask()
 * @model extendedMetaData="name='Mask_._type' kind='elementOnly'"
 * @generated
 */
public interface Mask extends EObject {
	/**
	 * Returns the value of the '<em><b>Level</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The level of the mask.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Level</em>' attribute.
	 * @see #setLevel(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getMask_Level()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Integer"
	 *        extendedMetaData="kind='element' name='Level' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getLevel();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Mask#getLevel <em>Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Level</em>' attribute.
	 * @see #getLevel()
	 * @generated
	 */
	void setLevel(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The type of mask. Values are: 0=Numbers, 1=Upper Case Letters, 2=Lower Case Letters, 3=Characters
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getMask_Type()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.TypeType5"
	 *        extendedMetaData="kind='element' name='Type' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getType();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Mask#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The maximum length in characters of the outline code values.  If length is any, the value is zero.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Length</em>' attribute.
	 * @see #setLength(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getMask_Length()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Integer"
	 *        extendedMetaData="kind='element' name='Length' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getLength();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Mask#getLength <em>Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Length</em>' attribute.
	 * @see #getLength()
	 * @generated
	 */
	void setLength(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Separator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The separator value of the code values.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Separator</em>' attribute.
	 * @see #setSeparator(String)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getMask_Separator()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='Separator' namespace='##targetNamespace'"
	 * @generated
	 */
	String getSeparator();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Mask#getSeparator <em>Separator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Separator</em>' attribute.
	 * @see #getSeparator()
	 * @generated
	 */
	void setSeparator(String value);

} // Mask
