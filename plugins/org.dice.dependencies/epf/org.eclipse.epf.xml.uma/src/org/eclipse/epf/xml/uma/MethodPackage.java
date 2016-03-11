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
 * A representation of the model object '<em><b>Method Package</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * An abstract class for packaging Method Elements.  All Method Elements shall be located in exactly one of Method Package's concrete specializations (e.g. Content Package).   Method Package defines common properties for all of its specializations. Elements are organized in Method Packages to structure large scale of method content and processes as well as to define a mechanism for reuse.  Method Elements from one package can reuse element from other packages by defining a reusedPackages link.  For example, a work product defined in one package can be used as an input for Tasks defined in other packages.  By reusing it from one common place (i.e. the package in which it has been defined) ensures that no redundant definitions of the same elements are required.  Also maintenance of method content is greatly improved as changes can be performed in only one place.  Note, that other packages will introduce more specializations of Method Package, e.g. Process Package and Process Component.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.xml.uma.MethodPackage#getGroup1 <em>Group1</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.MethodPackage#getReusedPackage <em>Reused Package</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.MethodPackage#getMethodPackage <em>Method Package</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.MethodPackage#isGlobal <em>Global</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.xml.uma.UmaPackage#getMethodPackage()
 * @model extendedMetaData="name='MethodPackage' kind='elementOnly'"
 * @generated
 */
public interface MethodPackage extends MethodElement {
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
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getMethodPackage_Group1()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='group:9'"
	 * @generated
	 */
	FeatureMap getGroup1();

	/**
	 * Returns the value of the '<em><b>Reused Package</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reused Package</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reused Package</em>' attribute list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getMethodPackage_ReusedPackage()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='ReusedPackage' group='#group:9'"
	 * @generated
	 */
	EList<String> getReusedPackage();

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
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getMethodPackage_MethodPackage()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='MethodPackage' group='#group:9'"
	 * @generated
	 */
	EList<MethodPackage> getMethodPackage();

	/**
	 * Returns the value of the '<em><b>Global</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Method Packages can have a global scope.  This means that every element of every other Method Package can see the global package's contents.  Global Method Packages are primarily used to store commonly used category definitions such as for Disciplines or Domains, which are used by many Task and Work Products respectively.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Global</em>' attribute.
	 * @see #isSetGlobal()
	 * @see #unsetGlobal()
	 * @see #setGlobal(boolean)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getMethodPackage_Global()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='global'"
	 * @generated
	 */
	boolean isGlobal();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.MethodPackage#isGlobal <em>Global</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Global</em>' attribute.
	 * @see #isSetGlobal()
	 * @see #unsetGlobal()
	 * @see #isGlobal()
	 * @generated
	 */
	void setGlobal(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.xml.uma.MethodPackage#isGlobal <em>Global</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetGlobal()
	 * @see #isGlobal()
	 * @see #setGlobal(boolean)
	 * @generated
	 */
	void unsetGlobal();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.xml.uma.MethodPackage#isGlobal <em>Global</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Global</em>' attribute is set.
	 * @see #unsetGlobal()
	 * @see #isGlobal()
	 * @see #setGlobal(boolean)
	 * @generated
	 */
	boolean isSetGlobal();

} // MethodPackage
