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
 * A representation of the model object '<em><b>Work Product</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * An abstract class which provides a generalization for the content element types Artifact, Outcome, and Deliverable.  The meta-model class Work Product actually represents work product types, i.e. an instance of Work Product is a description of a specific type of work product and not an individual work product instance.  However, for simplicity reasons and because of low risk of misinterpretation we did not append the word 'type' to every meta-class.
 * A work product is an abstraction for descriptions of content elements that are used to define anything used, produced, or modified by a task.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.xml.uma.WorkProduct#getGroup2 <em>Group2</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.WorkProduct#getEstimate <em>Estimate</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.WorkProduct#getEstimationConsiderations <em>Estimation Considerations</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.WorkProduct#getReport <em>Report</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.WorkProduct#getTemplate <em>Template</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.WorkProduct#getToolMentor <em>Tool Mentor</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.xml.uma.UmaPackage#getWorkProduct()
 * @model extendedMetaData="name='WorkProduct' kind='elementOnly'"
 * @generated
 */
public interface WorkProduct extends ContentElement {
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
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getWorkProduct_Group2()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='group:24'"
	 * @generated
	 */
	FeatureMap getGroup2();

	/**
	 * Returns the value of the '<em><b>Estimate</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Estimate</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Estimate</em>' attribute list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getWorkProduct_Estimate()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Estimate' group='#group:24'"
	 * @generated
	 */
	EList<String> getEstimate();

	/**
	 * Returns the value of the '<em><b>Estimation Considerations</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Estimation Considerations</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Estimation Considerations</em>' attribute list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getWorkProduct_EstimationConsiderations()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='EstimationConsiderations' group='#group:24'"
	 * @generated
	 */
	EList<String> getEstimationConsiderations();

	/**
	 * Returns the value of the '<em><b>Report</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Report</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Report</em>' attribute list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getWorkProduct_Report()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Report' group='#group:24'"
	 * @generated
	 */
	EList<String> getReport();

	/**
	 * Returns the value of the '<em><b>Template</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Template</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Template</em>' attribute list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getWorkProduct_Template()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Template' group='#group:24'"
	 * @generated
	 */
	EList<String> getTemplate();

	/**
	 * Returns the value of the '<em><b>Tool Mentor</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tool Mentor</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tool Mentor</em>' attribute list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getWorkProduct_ToolMentor()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='ToolMentor' group='#group:24'"
	 * @generated
	 */
	EList<String> getToolMentor();

} // WorkProduct
