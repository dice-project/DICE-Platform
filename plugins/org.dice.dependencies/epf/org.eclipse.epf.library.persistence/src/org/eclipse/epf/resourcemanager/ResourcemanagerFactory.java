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
package org.eclipse.epf.resourcemanager;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.epf.resourcemanager.ResourcemanagerPackage
 * @generated
 */
public interface ResourcemanagerFactory extends EFactory {
	
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    ResourcemanagerFactory eINSTANCE = org.eclipse.epf.resourcemanager.impl.ResourcemanagerFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Resource Descriptor</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Resource Descriptor</em>'.
	 * @generated
	 */
    ResourceDescriptor createResourceDescriptor();

	/**
	 * Returns a new object of class '<em>Resource Manager</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Resource Manager</em>'.
	 * @generated
	 */
    ResourceManager createResourceManager();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
    ResourcemanagerPackage getResourcemanagerPackage();

}
