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
 * A representation of the model object '<em><b>Activity</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A Work Breakdown Element and Work Definition which supports the nesting and logical grouping of related Breakdown Elements forming breakdown structures.  Although Activity is a concrete meta-class, other classes which represent breakdown structures derive from it; such as Phase, Iteration, Delivery Process, or Capability Pattern.
 * Activity represents a grouping element for other Breakdown Elements such as Activities, Descriptors, Milestones, etc.  It is not per-se a 'high-level' grouping of only work as in other meta-models, but groups any kind of Breakdown Elements.  For example, one can define valid Activities that group only Work Products Descriptors without any matching Task Descriptors.  Activities also inherit all properties from Work Breakdown Element and indirectly from Process Element; i.e. Activity is ready to have a full content description attached to it.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.xml.uma.Activity#getPrecondition <em>Precondition</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.Activity#getPostcondition <em>Postcondition</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.Activity#getGroup3 <em>Group3</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.Activity#getBreakdownElement <em>Breakdown Element</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.Activity#getRoadmap <em>Roadmap</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.Activity#isIsEnactable <em>Is Enactable</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.Activity#getVariabilityBasedOnElement <em>Variability Based On Element</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.Activity#getVariabilityType <em>Variability Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.xml.uma.UmaPackage#getActivity()
 * @model extendedMetaData="name='Activity' kind='elementOnly'"
 * @generated
 */
public interface Activity extends WorkBreakdownElement {
	/**
	 * Returns the value of the '<em><b>Precondition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Precondition</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Precondition</em>' attribute.
	 * @see #setPrecondition(String)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getActivity_Precondition()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='Precondition'"
	 * @generated
	 */
	String getPrecondition();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.Activity#getPrecondition <em>Precondition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Precondition</em>' attribute.
	 * @see #getPrecondition()
	 * @generated
	 */
	void setPrecondition(String value);

	/**
	 * Returns the value of the '<em><b>Postcondition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Postcondition</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Postcondition</em>' attribute.
	 * @see #setPostcondition(String)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getActivity_Postcondition()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='Postcondition'"
	 * @generated
	 */
	String getPostcondition();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.Activity#getPostcondition <em>Postcondition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Postcondition</em>' attribute.
	 * @see #getPostcondition()
	 * @generated
	 */
	void setPostcondition(String value);

	/**
	 * Returns the value of the '<em><b>Group3</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Group3</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Group3</em>' attribute list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getActivity_Group3()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='group:37'"
	 * @generated
	 */
	FeatureMap getGroup3();

	/**
	 * Returns the value of the '<em><b>Breakdown Element</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.epf.xml.uma.BreakdownElement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Breakdown Element</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Breakdown Element</em>' containment reference list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getActivity_BreakdownElement()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='BreakdownElement' group='#group:37'"
	 * @generated
	 */
	EList<BreakdownElement> getBreakdownElement();

	/**
	 * Returns the value of the '<em><b>Roadmap</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Roadmap</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Roadmap</em>' attribute list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getActivity_Roadmap()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Roadmap' group='#group:37'"
	 * @generated
	 */
	EList<String> getRoadmap();

	/**
	 * Returns the value of the '<em><b>Is Enactable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Enactable</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Enactable</em>' attribute.
	 * @see #isSetIsEnactable()
	 * @see #unsetIsEnactable()
	 * @see #setIsEnactable(boolean)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getActivity_IsEnactable()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='IsEnactable'"
	 * @generated
	 */
	boolean isIsEnactable();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.Activity#isIsEnactable <em>Is Enactable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Enactable</em>' attribute.
	 * @see #isSetIsEnactable()
	 * @see #unsetIsEnactable()
	 * @see #isIsEnactable()
	 * @generated
	 */
	void setIsEnactable(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.xml.uma.Activity#isIsEnactable <em>Is Enactable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetIsEnactable()
	 * @see #isIsEnactable()
	 * @see #setIsEnactable(boolean)
	 * @generated
	 */
	void unsetIsEnactable();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.xml.uma.Activity#isIsEnactable <em>Is Enactable</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Is Enactable</em>' attribute is set.
	 * @see #unsetIsEnactable()
	 * @see #isIsEnactable()
	 * @see #setIsEnactable(boolean)
	 * @generated
	 */
	boolean isSetIsEnactable();

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
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getActivity_VariabilityBasedOnElement()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='variabilityBasedOnElement'"
	 * @generated
	 */
	String getVariabilityBasedOnElement();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.Activity#getVariabilityBasedOnElement <em>Variability Based On Element</em>}' attribute.
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
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getActivity_VariabilityType()
	 * @model default="na" unsettable="true"
	 *        extendedMetaData="kind='attribute' name='variabilityType'"
	 * @generated
	 */
	VariabilityType getVariabilityType();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.Activity#getVariabilityType <em>Variability Type</em>}' attribute.
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
	 * Unsets the value of the '{@link org.eclipse.epf.xml.uma.Activity#getVariabilityType <em>Variability Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetVariabilityType()
	 * @see #getVariabilityType()
	 * @see #setVariabilityType(VariabilityType)
	 * @generated
	 */
	void unsetVariabilityType();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.xml.uma.Activity#getVariabilityType <em>Variability Type</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Variability Type</em>' attribute is set.
	 * @see #unsetVariabilityType()
	 * @see #getVariabilityType()
	 * @see #setVariabilityType(VariabilityType)
	 * @generated
	 */
	boolean isSetVariabilityType();

} // Activity
