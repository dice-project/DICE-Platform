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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Content Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A Describable Element that represents an abstract generalization for all elements that are considered to be and managed as Method Content.
 * Content Elements represents reusable Method Content that is supposed to be managed in Content Packages.  The separation of Content Element from Process Element allows to clearly distinguish between pure method content from content that is represented in processes.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.xml.uma.ContentElement#getGroup1 <em>Group1</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.ContentElement#getChecklist <em>Checklist</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.ContentElement#getConcept <em>Concept</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.ContentElement#getExample <em>Example</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.ContentElement#getGuideline <em>Guideline</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.ContentElement#getReusableAsset <em>Reusable Asset</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.ContentElement#getSupportingMaterial <em>Supporting Material</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.ContentElement#getWhitepaper <em>Whitepaper</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.ContentElement#getVariabilityBasedOnElement <em>Variability Based On Element</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.ContentElement#getVariabilityType <em>Variability Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.xml.uma.UmaPackage#getContentElement()
 * @model extendedMetaData="name='ContentElement' kind='elementOnly'"
 * @generated
 */
public interface ContentElement extends DescribableElement {
	/**
	 * Returns the value of the '<em><b>Group1</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Group1</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Group1</em>' attribute list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getContentElement_Group1()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='group:14'"
	 * @generated
	 */
	FeatureMap getGroup1();

	/**
	 * Returns the value of the '<em><b>Checklist</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Checklist</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Checklist</em>' attribute list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getContentElement_Checklist()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Checklist' group='#group:14'"
	 * @generated
	 */
	EList<String> getChecklist();

	/**
	 * Returns the value of the '<em><b>Concept</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Concept</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Concept</em>' attribute list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getContentElement_Concept()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Concept' group='#group:14'"
	 * @generated
	 */
	EList<String> getConcept();

	/**
	 * Returns the value of the '<em><b>Example</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Example</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Example</em>' attribute list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getContentElement_Example()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Example' group='#group:14'"
	 * @generated
	 */
	EList<String> getExample();

	/**
	 * Returns the value of the '<em><b>Guideline</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Guideline</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Guideline</em>' attribute list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getContentElement_Guideline()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Guideline' group='#group:14'"
	 * @generated
	 */
	EList<String> getGuideline();

	/**
	 * Returns the value of the '<em><b>Reusable Asset</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reusable Asset</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reusable Asset</em>' attribute list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getContentElement_ReusableAsset()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='ReusableAsset' group='#group:14'"
	 * @generated
	 */
	EList<String> getReusableAsset();

	/**
	 * Returns the value of the '<em><b>Supporting Material</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Supporting Material</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Supporting Material</em>' attribute list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getContentElement_SupportingMaterial()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='SupportingMaterial' group='#group:14'"
	 * @generated
	 */
	EList<String> getSupportingMaterial();

	/**
	 * Returns the value of the '<em><b>Whitepaper</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Whitepaper</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Whitepaper</em>' attribute list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getContentElement_Whitepaper()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Whitepaper' group='#group:14'"
	 * @generated
	 */
	EList<String> getWhitepaper();

	/**
	 * Returns the value of the '<em><b>Variability Based On Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Variability Based On Element</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variability Based On Element</em>' attribute.
	 * @see #setVariabilityBasedOnElement(String)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getContentElement_VariabilityBasedOnElement()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='variabilityBasedOnElement'"
	 * @generated
	 */
	String getVariabilityBasedOnElement();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.ContentElement#getVariabilityBasedOnElement <em>Variability Based On Element</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Variability Based On Element</em>' attribute.
	 * @see #getVariabilityBasedOnElement()
	 * @generated
	 */
	void setVariabilityBasedOnElement(String value);

	/**
	 * Returns the value of the '<em><b>Variability Type</b></em>' attribute.
	 * The default value is <code>"na"</code>.
	 * The literals are from the enumeration {@link org.eclipse.epf.xml.uma.VariabilityType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Variability Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variability Type</em>' attribute.
	 * @see org.eclipse.epf.xml.uma.VariabilityType
	 * @see #isSetVariabilityType()
	 * @see #unsetVariabilityType()
	 * @see #setVariabilityType(VariabilityType)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getContentElement_VariabilityType()
	 * @model default="na" unsettable="true"
	 *        extendedMetaData="kind='attribute' name='variabilityType'"
	 * @generated
	 */
	VariabilityType getVariabilityType();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.ContentElement#getVariabilityType <em>Variability Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Variability Type</em>' attribute.
	 * @see org.eclipse.epf.xml.uma.VariabilityType
	 * @see #isSetVariabilityType()
	 * @see #unsetVariabilityType()
	 * @see #getVariabilityType()
	 * @generated
	 */
	void setVariabilityType(VariabilityType value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.xml.uma.ContentElement#getVariabilityType <em>Variability Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetVariabilityType()
	 * @see #getVariabilityType()
	 * @see #setVariabilityType(VariabilityType)
	 * @generated
	 */
	void unsetVariabilityType();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.xml.uma.ContentElement#getVariabilityType <em>Variability Type</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Variability Type</em>' attribute is set.
	 * @see #unsetVariabilityType()
	 * @see #getVariabilityType()
	 * @see #setVariabilityType(VariabilityType)
	 * @generated
	 */
	boolean isSetVariabilityType();

} // ContentElement
