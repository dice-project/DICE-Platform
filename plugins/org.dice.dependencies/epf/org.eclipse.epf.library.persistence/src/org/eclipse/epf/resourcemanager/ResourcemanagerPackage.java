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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.epf.resourcemanager.ResourcemanagerFactory
 * @model kind="package"
 * @generated
 */
public interface ResourcemanagerPackage extends EPackage {
	
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    String eNAME = "resourcemanager"; //$NON-NLS-1$

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    String eNS_URI = "http:///org/eclipse/epf/uma/resourcemanager.ecore"; //$NON-NLS-1$

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    String eNS_PREFIX = "org.eclipse.epf.uma.resourcemanager"; //$NON-NLS-1$

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    ResourcemanagerPackage eINSTANCE = org.eclipse.epf.resourcemanager.impl.ResourcemanagerPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.epf.resourcemanager.impl.ResourceDescriptorImpl <em>Resource Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.epf.resourcemanager.impl.ResourceDescriptorImpl
	 * @see org.eclipse.epf.resourcemanager.impl.ResourcemanagerPackageImpl#getResourceDescriptor()
	 * @generated
	 */
    int RESOURCE_DESCRIPTOR = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int RESOURCE_DESCRIPTOR__ID = 0;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int RESOURCE_DESCRIPTOR__URI = 1;

	/**
	 * The number of structural features of the '<em>Resource Descriptor</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int RESOURCE_DESCRIPTOR_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.resourcemanager.impl.ResourceManagerImpl <em>Resource Manager</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.epf.resourcemanager.impl.ResourceManagerImpl
	 * @see org.eclipse.epf.resourcemanager.impl.ResourcemanagerPackageImpl#getResourceManager()
	 * @generated
	 */
    int RESOURCE_MANAGER = 1;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_MANAGER__GUID = 0;

	/**
	 * The feature id for the '<em><b>Uri Base</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_MANAGER__URI_BASE = 1;

	/**
	 * The feature id for the '<em><b>Sub Managers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_MANAGER__SUB_MANAGERS = 2;

	/**
	 * The feature id for the '<em><b>Resource Descriptors</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int RESOURCE_MANAGER__RESOURCE_DESCRIPTORS = 3;

	/**
	 * The number of structural features of the '<em>Resource Manager</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int RESOURCE_MANAGER_FEATURE_COUNT = 4;


	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.resourcemanager.ResourceDescriptor <em>Resource Descriptor</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resource Descriptor</em>'.
	 * @see org.eclipse.epf.resourcemanager.ResourceDescriptor
	 * @generated
	 */
    EClass getResourceDescriptor();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.resourcemanager.ResourceDescriptor#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.epf.resourcemanager.ResourceDescriptor#getId()
	 * @see #getResourceDescriptor()
	 * @generated
	 */
    EAttribute getResourceDescriptor_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.resourcemanager.ResourceDescriptor#getUri <em>Uri</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Uri</em>'.
	 * @see org.eclipse.epf.resourcemanager.ResourceDescriptor#getUri()
	 * @see #getResourceDescriptor()
	 * @generated
	 */
    EAttribute getResourceDescriptor_Uri();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.resourcemanager.ResourceManager <em>Resource Manager</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resource Manager</em>'.
	 * @see org.eclipse.epf.resourcemanager.ResourceManager
	 * @generated
	 */
    EClass getResourceManager();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.resourcemanager.ResourceManager#getGuid <em>Guid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Guid</em>'.
	 * @see org.eclipse.epf.resourcemanager.ResourceManager#getGuid()
	 * @see #getResourceManager()
	 * @generated
	 */
	EAttribute getResourceManager_Guid();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.resourcemanager.ResourceManager#getUriBase <em>Uri Base</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Uri Base</em>'.
	 * @see org.eclipse.epf.resourcemanager.ResourceManager#getUriBase()
	 * @see #getResourceManager()
	 * @generated
	 */
	EAttribute getResourceManager_UriBase();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.resourcemanager.ResourceManager#getSubManagers <em>Sub Managers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Sub Managers</em>'.
	 * @see org.eclipse.epf.resourcemanager.ResourceManager#getSubManagers()
	 * @see #getResourceManager()
	 * @generated
	 */
	EReference getResourceManager_SubManagers();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.resourcemanager.ResourceManager#getResourceDescriptors <em>Resource Descriptors</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Resource Descriptors</em>'.
	 * @see org.eclipse.epf.resourcemanager.ResourceManager#getResourceDescriptors()
	 * @see #getResourceManager()
	 * @generated
	 */
    EReference getResourceManager_ResourceDescriptors();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
    ResourcemanagerFactory getResourcemanagerFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals  {
		/**
		 * The meta object literal for the '{@link org.eclipse.epf.resourcemanager.impl.ResourceDescriptorImpl <em>Resource Descriptor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.resourcemanager.impl.ResourceDescriptorImpl
		 * @see org.eclipse.epf.resourcemanager.impl.ResourcemanagerPackageImpl#getResourceDescriptor()
		 * @generated
		 */
		EClass RESOURCE_DESCRIPTOR = eINSTANCE.getResourceDescriptor();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE_DESCRIPTOR__ID = eINSTANCE.getResourceDescriptor_Id();

		/**
		 * The meta object literal for the '<em><b>Uri</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE_DESCRIPTOR__URI = eINSTANCE.getResourceDescriptor_Uri();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.resourcemanager.impl.ResourceManagerImpl <em>Resource Manager</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.resourcemanager.impl.ResourceManagerImpl
		 * @see org.eclipse.epf.resourcemanager.impl.ResourcemanagerPackageImpl#getResourceManager()
		 * @generated
		 */
		EClass RESOURCE_MANAGER = eINSTANCE.getResourceManager();

		/**
		 * The meta object literal for the '<em><b>Guid</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE_MANAGER__GUID = eINSTANCE.getResourceManager_Guid();

		/**
		 * The meta object literal for the '<em><b>Uri Base</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE_MANAGER__URI_BASE = eINSTANCE.getResourceManager_UriBase();

		/**
		 * The meta object literal for the '<em><b>Sub Managers</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE_MANAGER__SUB_MANAGERS = eINSTANCE.getResourceManager_SubManagers();

		/**
		 * The meta object literal for the '<em><b>Resource Descriptors</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE_MANAGER__RESOURCE_DESCRIPTORS = eINSTANCE.getResourceManager_ResourceDescriptors();

	}

}
