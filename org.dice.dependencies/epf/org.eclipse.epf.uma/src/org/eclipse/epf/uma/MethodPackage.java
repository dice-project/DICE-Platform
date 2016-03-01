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
 * A representation of the model object '<em><b>Method Package</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A Method Package is an abstract class for packaging Method Elements.  All Method Elements shall be located in exactly one of Method Package's concrete specializations (e.g. Content Package).   Method Package defines common properties for all of its specializations. Elements are organized in Method Packages to structure large scale of method content and processes as well as to define a mechanism for reuse.  Method Elements from one package can reuse element from other packages by defining a reusedPackages link.  For example, a work product defined in one package can be used as an input for Tasks defined in other packages.  By reusing it from one common place (i.e. the package in which it has been defined) ensures that no redundant definitions of the same elements are required.  Also maintenance of method content is greatly improved as changes can be performed in only one place.  Note, that other packages will introduce more specializations of Method Package, e.g. Process Package and Process Component.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.MethodPackage#getGlobal <em>Global</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.MethodPackage#getReusedPackages <em>Reused Packages</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.MethodPackage#getChildPackages <em>Child Packages</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.uma.UmaPackage#getMethodPackage()
 * @model abstract="true"
 * @generated
 */
public interface MethodPackage extends MethodElement,
		org.eclipse.epf.uma.Package {
	/**
	 * Returns the value of the '<em><b>Global</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Method Packages can have a global scope.  This means that every element of every other Method Package can see the global package's contents.  Global Method Packages are primarily used to store commonly used category definitions such as for Disciplines or Domains, which are used by many Task and Work Products respectively.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Global</em>' attribute.
	 * @see #setGlobal(Boolean)
	 * @see org.eclipse.epf.uma.UmaPackage#getMethodPackage_Global()
	 * @model default="false" dataType="org.eclipse.epf.uma.Boolean" required="true" ordered="false"
	 * @generated
	 */
	Boolean getGlobal();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.MethodPackage#getGlobal <em>Global</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Global</em>' attribute.
	 * @see #getGlobal()
	 * @generated
	 */
	void setGlobal(Boolean value);

	/**
	 * Returns the value of the '<em><b>Reused Packages</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.MethodPackage}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reused Packages</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reused Packages</em>' reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getMethodPackage_ReusedPackages()
	 * @model ordered="false"
	 * @generated
	 */
	List<MethodPackage> getReusedPackages();

	/**
	 * Returns the value of the '<em><b>Child Packages</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.MethodPackage}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Child Packages</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Child Packages</em>' containment reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getMethodPackage_ChildPackages()
	 * @model containment="true" resolveProxies="true" ordered="false"
	 * @generated
	 */
	List<MethodPackage> getChildPackages();

	MethodPackage getParentPackage();

} // MethodPackage
