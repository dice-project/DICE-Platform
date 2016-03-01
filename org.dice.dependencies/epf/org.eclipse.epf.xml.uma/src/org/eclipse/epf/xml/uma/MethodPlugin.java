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
 * A representation of the model object '<em><b>Method Plugin</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A special Method Unit that represents a physical container for Method Packages.  It defines a granularity level for the modularization and organization of method content and processes.  A Method Plugin can extend many other Method Plugins and it can be extended by many Method Plugins.  It can also be used stand-alone, i.e. with no Extension relationship to other plug-ins.
 * Method Plugin conceptually represents a unit for configuration, modularization, extension, packaging, and deployment of method content and processes.  A Process Engineer shall design his Plugins and allocate his content to these Plugins with requirements for extensibility, modularity, reuse, and maintainability in mind.
 * Special extensibility mechanisms defined for the meta-classes Variability Element and Process Contribution allow Plugin content to directly contribute new content, replace existing content, or to cross-reference to any Content Element or Process within another Plugin that it extends.  Similar to UML 2.0's 'package merge' mechanism transformation interpretations, interpreting these Method Plugin mechanisms results into new extended Method Content and Processes.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.xml.uma.MethodPlugin#getReferencedMethodPlugin <em>Referenced Method Plugin</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.MethodPlugin#getMethodPackage <em>Method Package</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.MethodPlugin#isSupporting <em>Supporting</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.MethodPlugin#isUserChangeable <em>User Changeable</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.xml.uma.UmaPackage#getMethodPlugin()
 * @model extendedMetaData="name='MethodPlugin' kind='elementOnly'"
 * @generated
 */
public interface MethodPlugin extends MethodUnit {
	/**
	 * Returns the value of the '<em><b>Referenced Method Plugin</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Referenced Method Plugin</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Referenced Method Plugin</em>' attribute list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getMethodPlugin_ReferencedMethodPlugin()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='ReferencedMethodPlugin'"
	 * @generated
	 */
	EList<String> getReferencedMethodPlugin();

	/**
	 * Returns the value of the '<em><b>Method Package</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.epf.xml.uma.MethodPackage}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Method Package</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Method Package</em>' containment reference list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getMethodPlugin_MethodPackage()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='MethodPackage'"
	 * @generated
	 */
	EList<MethodPackage> getMethodPackage();

	/**
	 * Returns the value of the '<em><b>Supporting</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Supporting</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Supporting</em>' attribute.
	 * @see #isSetSupporting()
	 * @see #unsetSupporting()
	 * @see #setSupporting(boolean)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getMethodPlugin_Supporting()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='supporting'"
	 * @generated
	 */
	boolean isSupporting();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.MethodPlugin#isSupporting <em>Supporting</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Supporting</em>' attribute.
	 * @see #isSetSupporting()
	 * @see #unsetSupporting()
	 * @see #isSupporting()
	 * @generated
	 */
	void setSupporting(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.xml.uma.MethodPlugin#isSupporting <em>Supporting</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetSupporting()
	 * @see #isSupporting()
	 * @see #setSupporting(boolean)
	 * @generated
	 */
	void unsetSupporting();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.xml.uma.MethodPlugin#isSupporting <em>Supporting</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Supporting</em>' attribute is set.
	 * @see #unsetSupporting()
	 * @see #isSupporting()
	 * @see #setSupporting(boolean)
	 * @generated
	 */
	boolean isSetSupporting();

	/**
	 * Returns the value of the '<em><b>User Changeable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>User Changeable</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>User Changeable</em>' attribute.
	 * @see #isSetUserChangeable()
	 * @see #unsetUserChangeable()
	 * @see #setUserChangeable(boolean)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getMethodPlugin_UserChangeable()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='userChangeable'"
	 * @generated
	 */
	boolean isUserChangeable();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.MethodPlugin#isUserChangeable <em>User Changeable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>User Changeable</em>' attribute.
	 * @see #isSetUserChangeable()
	 * @see #unsetUserChangeable()
	 * @see #isUserChangeable()
	 * @generated
	 */
	void setUserChangeable(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.xml.uma.MethodPlugin#isUserChangeable <em>User Changeable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetUserChangeable()
	 * @see #isUserChangeable()
	 * @see #setUserChangeable(boolean)
	 * @generated
	 */
	void unsetUserChangeable();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.xml.uma.MethodPlugin#isUserChangeable <em>User Changeable</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>User Changeable</em>' attribute is set.
	 * @see #unsetUserChangeable()
	 * @see #isUserChangeable()
	 * @see #setUserChangeable(boolean)
	 * @generated
	 */
	boolean isSetUserChangeable();

} // MethodPlugin
