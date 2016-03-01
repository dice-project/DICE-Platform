//------------------------------------------------------------------------------
// Copyright (c) 2005, 2006 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.uma;

import java.util.List;

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
 *   <li>{@link org.eclipse.epf.uma.MethodLibrary#getMethodPlugins <em>Method Plugins</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.MethodLibrary#getPredefinedConfigurations <em>Predefined Configurations</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.uma.UmaPackage#getMethodLibrary()
 * @model
 * @generated
 */
public interface MethodLibrary extends MethodUnit, org.eclipse.epf.uma.Package {
	/**
	 * Returns the value of the '<em><b>Method Plugins</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.MethodPlugin}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Method Plugins</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Method Plugins</em>' containment reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getMethodLibrary_MethodPlugins()
	 * @model containment="true" resolveProxies="true" ordered="false"
	 * @generated
	 */
	List<MethodPlugin> getMethodPlugins();

	/**
	 * Returns the value of the '<em><b>Predefined Configurations</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.MethodConfiguration}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Predefined Configurations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Predefined Configurations</em>' containment reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getMethodLibrary_PredefinedConfigurations()
	 * @model containment="true" resolveProxies="true" ordered="false"
	 * @generated
	 */
	List<MethodConfiguration> getPredefinedConfigurations();

} // MethodLibrary
