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
 * A representation of the model object '<em><b>Method Library</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A Method Library is a physical container for Method Plugins and Method Configuration definitions.  All Method Elements are stored in a Method Library.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.xml.uma.MethodLibrary#getMethodPlugin <em>Method Plugin</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.MethodLibrary#getMethodConfiguration <em>Method Configuration</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.MethodLibrary#getTool <em>Tool</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.xml.uma.UmaPackage#getMethodLibrary()
 * @model extendedMetaData="name='MethodLibrary' kind='elementOnly'"
 * @generated
 */
public interface MethodLibrary extends MethodUnit {
	/**
	 * Returns the value of the '<em><b>Method Plugin</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.epf.xml.uma.MethodPlugin}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Method Plugin</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Method Plugin</em>' containment reference list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getMethodLibrary_MethodPlugin()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='MethodPlugin'"
	 * @generated
	 */
	EList<MethodPlugin> getMethodPlugin();

	/**
	 * Returns the value of the '<em><b>Method Configuration</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.epf.xml.uma.MethodConfiguration}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Method Configuration</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Method Configuration</em>' containment reference list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getMethodLibrary_MethodConfiguration()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='MethodConfiguration'"
	 * @generated
	 */
	EList<MethodConfiguration> getMethodConfiguration();

	/**
	 * Returns the value of the '<em><b>Tool</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tool</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The tool that generated the XML file.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Tool</em>' attribute.
	 * @see #setTool(String)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getMethodLibrary_Tool()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='tool'"
	 * @generated
	 */
	String getTool();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.MethodLibrary#getTool <em>Tool</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tool</em>' attribute.
	 * @see #getTool()
	 * @generated
	 */
	void setTool(String value);

} // MethodLibrary
