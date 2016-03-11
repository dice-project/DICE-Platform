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
 * A representation of the model object '<em><b>Work Product Descriptor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A special Descriptor that represents a Work Product in the context of one specific Activity.  Every breakdown structure can define different relationships of Work Product Descriptors to Task Descriptors and Role Descriptors.  Therefore one Work Product can be represented by many Work Product Descriptors each within the context of an Activity with its own set of relationships.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.xml.uma.WorkProductDescriptor#getWorkProduct <em>Work Product</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.WorkProductDescriptor#getResponsibleRole <em>Responsible Role</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.WorkProductDescriptor#getGroup2 <em>Group2</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.WorkProductDescriptor#getExternalInputTo <em>External Input To</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.WorkProductDescriptor#getImpactedBy <em>Impacted By</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.WorkProductDescriptor#getImpacts <em>Impacts</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.WorkProductDescriptor#getMandatoryInputTo <em>Mandatory Input To</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.WorkProductDescriptor#getOptionalInputTo <em>Optional Input To</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.WorkProductDescriptor#getOutputFrom <em>Output From</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.WorkProductDescriptor#getDeliverableParts <em>Deliverable Parts</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.WorkProductDescriptor#getActivityEntryState <em>Activity Entry State</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.WorkProductDescriptor#getActivityExitState <em>Activity Exit State</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.xml.uma.UmaPackage#getWorkProductDescriptor()
 * @model extendedMetaData="name='WorkProductDescriptor' kind='elementOnly'"
 * @generated
 */
public interface WorkProductDescriptor extends Descriptor {
	/**
	 * Returns the value of the '<em><b>Work Product</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Work Product</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Work Product</em>' attribute.
	 * @see #setWorkProduct(String)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getWorkProductDescriptor_WorkProduct()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='WorkProduct'"
	 * @generated
	 */
	String getWorkProduct();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.WorkProductDescriptor#getWorkProduct <em>Work Product</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Work Product</em>' attribute.
	 * @see #getWorkProduct()
	 * @generated
	 */
	void setWorkProduct(String value);

	/**
	 * Returns the value of the '<em><b>Responsible Role</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Responsible Role</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Responsible Role</em>' attribute.
	 * @see #setResponsibleRole(String)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getWorkProductDescriptor_ResponsibleRole()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='ResponsibleRole'"
	 * @generated
	 */
	String getResponsibleRole();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.WorkProductDescriptor#getResponsibleRole <em>Responsible Role</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Responsible Role</em>' attribute.
	 * @see #getResponsibleRole()
	 * @generated
	 */
	void setResponsibleRole(String value);

	/**
	 * Returns the value of the '<em><b>Group2</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Group2</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Group2</em>' attribute list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getWorkProductDescriptor_Group2()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='group:33'"
	 * @generated
	 */
	FeatureMap getGroup2();

	/**
	 * Returns the value of the '<em><b>External Input To</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>External Input To</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>External Input To</em>' attribute list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getWorkProductDescriptor_ExternalInputTo()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='ExternalInputTo' group='#group:33'"
	 * @generated
	 */
	EList<String> getExternalInputTo();

	/**
	 * Returns the value of the '<em><b>Impacted By</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Impacted By</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Impacted By</em>' attribute list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getWorkProductDescriptor_ImpactedBy()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='ImpactedBy' group='#group:33'"
	 * @generated
	 */
	EList<String> getImpactedBy();

	/**
	 * Returns the value of the '<em><b>Impacts</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Impacts</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Impacts</em>' attribute list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getWorkProductDescriptor_Impacts()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Impacts' group='#group:33'"
	 * @generated
	 */
	EList<String> getImpacts();

	/**
	 * Returns the value of the '<em><b>Mandatory Input To</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mandatory Input To</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mandatory Input To</em>' attribute list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getWorkProductDescriptor_MandatoryInputTo()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='MandatoryInputTo' group='#group:33'"
	 * @generated
	 */
	EList<String> getMandatoryInputTo();

	/**
	 * Returns the value of the '<em><b>Optional Input To</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Optional Input To</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Optional Input To</em>' attribute list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getWorkProductDescriptor_OptionalInputTo()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='OptionalInputTo' group='#group:33'"
	 * @generated
	 */
	EList<String> getOptionalInputTo();

	/**
	 * Returns the value of the '<em><b>Output From</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Output From</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Output From</em>' attribute list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getWorkProductDescriptor_OutputFrom()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='OutputFrom' group='#group:33'"
	 * @generated
	 */
	EList<String> getOutputFrom();

	/**
	 * Returns the value of the '<em><b>Deliverable Parts</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Deliverable Parts</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Deliverable Parts</em>' attribute list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getWorkProductDescriptor_DeliverableParts()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='DeliverableParts' group='#group:33'"
	 * @generated
	 */
	EList<String> getDeliverableParts();

	/**
	 * Returns the value of the '<em><b>Activity Entry State</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Given that an instance of Work Product Descriptor has been created for a specific Activity, then the Activity Entry State attribute specifies the desired state of instances of the referenced Work Product when work on the Activity is initiated (i.e. work on the Activity's Task Descriptors is being initiated that use this Work Product Descriptor as input).  
	 * For some Work Products state is expressed in percentage of completion, compliance to work product checklist, informal state descriptions, etc.  Others have very specific states expressed as enumerations such as [identified, briefly described, outlined, detailed] for use cases.  Other Work Product states relate to some quality measures or lifecycle states such as [reviewed, implemented, tested].
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Activity Entry State</em>' attribute.
	 * @see #setActivityEntryState(String)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getWorkProductDescriptor_ActivityEntryState()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='activityEntryState'"
	 * @generated
	 */
	String getActivityEntryState();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.WorkProductDescriptor#getActivityEntryState <em>Activity Entry State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Activity Entry State</em>' attribute.
	 * @see #getActivityEntryState()
	 * @generated
	 */
	void setActivityEntryState(String value);

	/**
	 * Returns the value of the '<em><b>Activity Exit State</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Given that an instance of Work Product Descriptor has been created for a specific Activity, then the Activity Exist State attribute specifies the desired state of instances of the referenced Work Product when work on the Activity is finished (i.e. work on the Activity's Task Descriptors has finished that have this Work Product Descriptor as output).
	 * For some Work Products state is expressed in percentage of completion, compliance to work product checklist, informal state descriptions, etc.  Others have very specific states expressed as enumerations such as [identified, briefly described, outlined, detailed] for use cases.  Other Work Product states relate to some quality measures or lifecycle states such as [reviewed, implemented, tested].
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Activity Exit State</em>' attribute.
	 * @see #setActivityExitState(String)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getWorkProductDescriptor_ActivityExitState()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='activityExitState'"
	 * @generated
	 */
	String getActivityExitState();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.WorkProductDescriptor#getActivityExitState <em>Activity Exit State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Activity Exit State</em>' attribute.
	 * @see #getActivityExitState()
	 * @generated
	 */
	void setActivityExitState(String value);

} // WorkProductDescriptor
