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
 * A representation of the model object '<em><b>Breakdown Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * An abstract generalization for any type of Method Element that is part of a breakdown structure.  It defines a set of properties available to all of its specializations.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.xml.uma.BreakdownElement#getPresentedAfter <em>Presented After</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.BreakdownElement#getPresentedBefore <em>Presented Before</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.BreakdownElement#getPlanningData <em>Planning Data</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.BreakdownElement#getSuperActivity <em>Super Activity</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.BreakdownElement#getGroup1 <em>Group1</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.BreakdownElement#getChecklist <em>Checklist</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.BreakdownElement#getConcept <em>Concept</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.BreakdownElement#getExample <em>Example</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.BreakdownElement#getGuideline <em>Guideline</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.BreakdownElement#getReusableAsset <em>Reusable Asset</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.BreakdownElement#getSupportingMaterial <em>Supporting Material</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.BreakdownElement#getWhitepaper <em>Whitepaper</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.BreakdownElement#isHasMultipleOccurrences <em>Has Multiple Occurrences</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.BreakdownElement#isIsOptional <em>Is Optional</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.BreakdownElement#isIsPlanned <em>Is Planned</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.BreakdownElement#getPrefix <em>Prefix</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.xml.uma.UmaPackage#getBreakdownElement()
 * @model extendedMetaData="name='BreakdownElement' kind='elementOnly'"
 * @generated
 */
public interface BreakdownElement extends ProcessElement {
	/**
	 * Returns the value of the '<em><b>Presented After</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Presented After</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Presented After</em>' attribute.
	 * @see #setPresentedAfter(String)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getBreakdownElement_PresentedAfter()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='PresentedAfter'"
	 * @generated
	 */
	String getPresentedAfter();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.BreakdownElement#getPresentedAfter <em>Presented After</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Presented After</em>' attribute.
	 * @see #getPresentedAfter()
	 * @generated
	 */
	void setPresentedAfter(String value);

	/**
	 * Returns the value of the '<em><b>Presented Before</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Presented Before</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Presented Before</em>' attribute.
	 * @see #setPresentedBefore(String)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getBreakdownElement_PresentedBefore()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='PresentedBefore'"
	 * @generated
	 */
	String getPresentedBefore();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.BreakdownElement#getPresentedBefore <em>Presented Before</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Presented Before</em>' attribute.
	 * @see #getPresentedBefore()
	 * @generated
	 */
	void setPresentedBefore(String value);

	/**
	 * Returns the value of the '<em><b>Planning Data</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Planning Data</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Planning Data</em>' attribute.
	 * @see #setPlanningData(String)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getBreakdownElement_PlanningData()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='PlanningData'"
	 * @generated
	 */
	String getPlanningData();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.BreakdownElement#getPlanningData <em>Planning Data</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Planning Data</em>' attribute.
	 * @see #getPlanningData()
	 * @generated
	 */
	void setPlanningData(String value);

	/**
	 * Returns the value of the '<em><b>Super Activity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Super Activity</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Super Activity</em>' attribute.
	 * @see #setSuperActivity(String)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getBreakdownElement_SuperActivity()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='SuperActivity'"
	 * @generated
	 */
	String getSuperActivity();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.BreakdownElement#getSuperActivity <em>Super Activity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Super Activity</em>' attribute.
	 * @see #getSuperActivity()
	 * @generated
	 */
	void setSuperActivity(String value);

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
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getBreakdownElement_Group1()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='group:18'"
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
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getBreakdownElement_Checklist()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Checklist' group='#group:18'"
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
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getBreakdownElement_Concept()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Concept' group='#group:18'"
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
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getBreakdownElement_Example()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Example' group='#group:18'"
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
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getBreakdownElement_Guideline()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Guideline' group='#group:18'"
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
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getBreakdownElement_ReusableAsset()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='ReusableAsset' group='#group:18'"
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
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getBreakdownElement_SupportingMaterial()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='SupportingMaterial' group='#group:18'"
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
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getBreakdownElement_Whitepaper()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Whitepaper' group='#group:18'"
	 * @generated
	 */
	EList<String> getWhitepaper();

	/**
	 * Returns the value of the '<em><b>Has Multiple Occurrences</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Just as the isPlanned attribute the hasMultipleOccurrences attribute has an impact on generating plans from a Process.  When this attribute is set to True for a Breakdown Element then it will typically occur multiple times within the same Activity.  For example, a Task such as "Detail Use Case" would be performed for every use case identified for a particular Iteration or Activity.  Generating a plan would list one Task instance per use case.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Has Multiple Occurrences</em>' attribute.
	 * @see #isSetHasMultipleOccurrences()
	 * @see #unsetHasMultipleOccurrences()
	 * @see #setHasMultipleOccurrences(boolean)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getBreakdownElement_HasMultipleOccurrences()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='hasMultipleOccurrences'"
	 * @generated
	 */
	boolean isHasMultipleOccurrences();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.BreakdownElement#isHasMultipleOccurrences <em>Has Multiple Occurrences</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Has Multiple Occurrences</em>' attribute.
	 * @see #isSetHasMultipleOccurrences()
	 * @see #unsetHasMultipleOccurrences()
	 * @see #isHasMultipleOccurrences()
	 * @generated
	 */
	void setHasMultipleOccurrences(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.xml.uma.BreakdownElement#isHasMultipleOccurrences <em>Has Multiple Occurrences</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetHasMultipleOccurrences()
	 * @see #isHasMultipleOccurrences()
	 * @see #setHasMultipleOccurrences(boolean)
	 * @generated
	 */
	void unsetHasMultipleOccurrences();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.xml.uma.BreakdownElement#isHasMultipleOccurrences <em>Has Multiple Occurrences</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Has Multiple Occurrences</em>' attribute is set.
	 * @see #unsetHasMultipleOccurrences()
	 * @see #isHasMultipleOccurrences()
	 * @see #setHasMultipleOccurrences(boolean)
	 * @generated
	 */
	boolean isSetHasMultipleOccurrences();

	/**
	 * Returns the value of the '<em><b>Is Optional</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Indicates that the Breakdown Element describes work, a work result, or even work resources, which inclusion is not mandatory when performing a project that is planned based on a process containing this element.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is Optional</em>' attribute.
	 * @see #isSetIsOptional()
	 * @see #unsetIsOptional()
	 * @see #setIsOptional(boolean)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getBreakdownElement_IsOptional()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='isOptional'"
	 * @generated
	 */
	boolean isIsOptional();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.BreakdownElement#isIsOptional <em>Is Optional</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Optional</em>' attribute.
	 * @see #isSetIsOptional()
	 * @see #unsetIsOptional()
	 * @see #isIsOptional()
	 * @generated
	 */
	void setIsOptional(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.xml.uma.BreakdownElement#isIsOptional <em>Is Optional</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetIsOptional()
	 * @see #isIsOptional()
	 * @see #setIsOptional(boolean)
	 * @generated
	 */
	void unsetIsOptional();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.xml.uma.BreakdownElement#isIsOptional <em>Is Optional</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Is Optional</em>' attribute is set.
	 * @see #unsetIsOptional()
	 * @see #isIsOptional()
	 * @see #setIsOptional(boolean)
	 * @generated
	 */
	boolean isSetIsOptional();

	/**
	 * Returns the value of the '<em><b>Is Planned</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A key application for Development Processes expressed with Breakdown structures is to generate a project plan from it.  A process as defined in UMA (cf. with Sections XX and 5.2) is a multi-dimensional structure defining what work is being performed at what time by which roles using which input and producing what outputs.  A project plan as it is represented in project planning tools such as IBM Rational Portfolio Manager or Microsoft Project normally does not need all this information and is normally limited to just representing a subset.  For example, a typical MS Project plan only represents the work breakdown consisting of Tasks and Activities (sometimes referred to as summary tasks).  It does not show the input and output Work Products for a Task, but it can show which roles shall be staffed for performing the Task.  However, such role allocation need to be replaced with concrete resources when instantiating the plan for a concrete project.  Sometimes project plans can then again be organized differently by organizing work by deliverables in which Work Products are mapped to the plan's summary tasks and Task that have these work products as output mapped below such as summary task.  Therefore, a process can make recommendations about which elements to include and which to exclude when generating a plan.  When the isPlanned attribute is set to False for an instance of a Breakdown Element, then this element shall not be not included when a concrete project plan is being generated from the breakdown structure that contains this element.
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is Planned</em>' attribute.
	 * @see #isSetIsPlanned()
	 * @see #unsetIsPlanned()
	 * @see #setIsPlanned(boolean)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getBreakdownElement_IsPlanned()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='isPlanned'"
	 * @generated
	 */
	boolean isIsPlanned();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.BreakdownElement#isIsPlanned <em>Is Planned</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Planned</em>' attribute.
	 * @see #isSetIsPlanned()
	 * @see #unsetIsPlanned()
	 * @see #isIsPlanned()
	 * @generated
	 */
	void setIsPlanned(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.xml.uma.BreakdownElement#isIsPlanned <em>Is Planned</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetIsPlanned()
	 * @see #isIsPlanned()
	 * @see #setIsPlanned(boolean)
	 * @generated
	 */
	void unsetIsPlanned();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.xml.uma.BreakdownElement#isIsPlanned <em>Is Planned</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Is Planned</em>' attribute is set.
	 * @see #unsetIsPlanned()
	 * @see #isIsPlanned()
	 * @see #setIsPlanned(boolean)
	 * @generated
	 */
	boolean isSetIsPlanned();

	/**
	 * Returns the value of the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Prefix represents an additional label that will be presented as a prefix to any Breakdown Element to indicate a user-defined sub-type for the element.  For example, if the process engineer would like to distinguish his Activities by 'Module' (as done in the IBM Rational Summit Ascendant Method), he can define a different prefix for every model to be used in addition to naming Activities, e.g. "SRA.Establish Requirements" with SRA indicating that this Activity belongs to the "Software Requirements Analysis" module.  Another common application for prefix is to qualify roles in Role Descriptors.  For example, "Customer.Architect" would define a "Customer" prefix for the Role Descriptor "Architect" expressing that this is an architect on the customer side and not the development team side.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Prefix</em>' attribute.
	 * @see #setPrefix(String)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getBreakdownElement_Prefix()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='prefix'"
	 * @generated
	 */
	String getPrefix();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.BreakdownElement#getPrefix <em>Prefix</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Prefix</em>' attribute.
	 * @see #getPrefix()
	 * @generated
	 */
	void setPrefix(String value);

} // BreakdownElement
