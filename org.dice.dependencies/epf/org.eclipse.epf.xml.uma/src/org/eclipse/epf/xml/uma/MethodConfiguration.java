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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Method Configuration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A collection of selected Method Models and MethodPackages. A configuration can be exported into its own standalone library when it includes the full transitive closure of all elements all other elements depend on.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.xml.uma.MethodConfiguration#getBaseConfiguration <em>Base Configuration</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.MethodConfiguration#getMethodPluginSelection <em>Method Plugin Selection</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.MethodConfiguration#getMethodPackageSelection <em>Method Package Selection</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.MethodConfiguration#getDefaultView <em>Default View</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.MethodConfiguration#getProcessView <em>Process View</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.MethodConfiguration#getSubtractedCategory <em>Subtracted Category</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.MethodConfiguration#getAddedCategory <em>Added Category</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.xml.uma.UmaPackage#getMethodConfiguration()
 * @model extendedMetaData="name='MethodConfiguration' kind='elementOnly'"
 * @generated
 */
public interface MethodConfiguration extends MethodUnit {
	/**
	 * Returns the value of the '<em><b>Base Configuration</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Base Configuration</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Base Configuration</em>' attribute list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getMethodConfiguration_BaseConfiguration()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='BaseConfiguration'"
	 * @generated
	 */
	EList<String> getBaseConfiguration();

	/**
	 * Returns the value of the '<em><b>Method Plugin Selection</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Method Plugin Selection</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Method Plugin Selection</em>' attribute list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getMethodConfiguration_MethodPluginSelection()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='MethodPluginSelection'"
	 * @generated
	 */
	EList<String> getMethodPluginSelection();

	/**
	 * Returns the value of the '<em><b>Method Package Selection</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Method Package Selection</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Method Package Selection</em>' attribute list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getMethodConfiguration_MethodPackageSelection()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='MethodPackageSelection'"
	 * @generated
	 */
	EList<String> getMethodPackageSelection();

	/**
	 * Returns the value of the '<em><b>Default View</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default View</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Default View</em>' attribute.
	 * @see #setDefaultView(String)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getMethodConfiguration_DefaultView()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='DefaultView'"
	 * @generated
	 */
	String getDefaultView();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.MethodConfiguration#getDefaultView <em>Default View</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default View</em>' attribute.
	 * @see #getDefaultView()
	 * @generated
	 */
	void setDefaultView(String value);

	/**
	 * Returns the value of the '<em><b>Process View</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Process View</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Process View</em>' attribute list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getMethodConfiguration_ProcessView()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='ProcessView'"
	 * @generated
	 */
	EList<String> getProcessView();

	/**
	 * Returns the value of the '<em><b>Subtracted Category</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Subtracted Category</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Subtracted Category</em>' attribute list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getMethodConfiguration_SubtractedCategory()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='SubtractedCategory'"
	 * @generated
	 */
	EList<String> getSubtractedCategory();

	/**
	 * Returns the value of the '<em><b>Added Category</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Added Category</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Added Category</em>' attribute list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getMethodConfiguration_AddedCategory()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='AddedCategory'"
	 * @generated
	 */
	EList<String> getAddedCategory();

} // MethodConfiguration
