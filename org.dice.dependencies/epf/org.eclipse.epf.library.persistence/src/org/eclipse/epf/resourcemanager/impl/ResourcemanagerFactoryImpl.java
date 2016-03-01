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
package org.eclipse.epf.resourcemanager.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.epf.resourcemanager.*;
import org.eclipse.epf.resourcemanager.ResourceDescriptor;
import org.eclipse.epf.resourcemanager.ResourceManager;
import org.eclipse.epf.resourcemanager.ResourcemanagerFactory;
import org.eclipse.epf.resourcemanager.ResourcemanagerPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ResourcemanagerFactoryImpl extends EFactoryImpl implements ResourcemanagerFactory {
	
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ResourcemanagerFactory init() {
		try {
			ResourcemanagerFactory theResourcemanagerFactory = (ResourcemanagerFactory)EPackage.Registry.INSTANCE.getEFactory("http:///org/eclipse/epf/uma/resourcemanager.ecore"); //$NON-NLS-1$ 
			if (theResourcemanagerFactory != null) {
				return theResourcemanagerFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ResourcemanagerFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ResourcemanagerFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
				public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case ResourcemanagerPackage.RESOURCE_DESCRIPTOR: return createResourceDescriptor();
			case ResourcemanagerPackage.RESOURCE_MANAGER: return createResourceManager();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ResourceDescriptor createResourceDescriptor() {
		ResourceDescriptorImpl resourceDescriptor = new ResourceDescriptorImpl();
		return resourceDescriptor;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ResourceManager createResourceManager() {
		ResourceManagerImpl resourceManager = new ResourceManagerImpl();
		return resourceManager;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ResourcemanagerPackage getResourcemanagerPackage() {
		return (ResourcemanagerPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
    @Deprecated
				public static ResourcemanagerPackage getPackage() {
		return ResourcemanagerPackage.eINSTANCE;
	}

}
