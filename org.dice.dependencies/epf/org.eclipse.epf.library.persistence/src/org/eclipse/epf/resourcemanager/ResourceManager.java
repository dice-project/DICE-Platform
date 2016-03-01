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

import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Resource Manager</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.resourcemanager.ResourceManager#getGuid <em>Guid</em>}</li>
 *   <li>{@link org.eclipse.epf.resourcemanager.ResourceManager#getUriBase <em>Uri Base</em>}</li>
 *   <li>{@link org.eclipse.epf.resourcemanager.ResourceManager#getSubManagers <em>Sub Managers</em>}</li>
 *   <li>{@link org.eclipse.epf.resourcemanager.ResourceManager#getResourceDescriptors <em>Resource Descriptors</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.resourcemanager.ResourcemanagerPackage#getResourceManager()
 * @model
 * @generated
 */
public interface ResourceManager extends EObject {
	
	/**
	 * Returns the value of the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Guid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Guid</em>' attribute.
	 * @see #setGuid(String)
	 * @see org.eclipse.epf.resourcemanager.ResourcemanagerPackage#getResourceManager_Guid()
	 * @model
	 * @generated
	 */
	String getGuid();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.resourcemanager.ResourceManager#getGuid <em>Guid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Guid</em>' attribute.
	 * @see #getGuid()
	 * @generated
	 */
	void setGuid(String value);

	/**
	 * Returns the value of the '<em><b>Uri Base</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Uri Base</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Uri Base</em>' attribute.
	 * @see #setUriBase(String)
	 * @see org.eclipse.epf.resourcemanager.ResourcemanagerPackage#getResourceManager_UriBase()
	 * @model
	 * @generated
	 */
	String getUriBase();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.resourcemanager.ResourceManager#getUriBase <em>Uri Base</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Uri Base</em>' attribute.
	 * @see #getUriBase()
	 * @generated
	 */
	void setUriBase(String value);

	/**
	 * Returns the value of the '<em><b>Sub Managers</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.epf.resourcemanager.ResourceManager}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub Managers</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sub Managers</em>' containment reference list.
	 * @see org.eclipse.epf.resourcemanager.ResourcemanagerPackage#getResourceManager_SubManagers()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	List<ResourceManager> getSubManagers();

	/**
	 * Returns the value of the '<em><b>Resource Descriptors</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.epf.resourcemanager.ResourceDescriptor}.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Resource Descriptors</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Resource Descriptors</em>' containment reference list.
	 * @see org.eclipse.epf.resourcemanager.ResourcemanagerPackage#getResourceManager_ResourceDescriptors()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
    List<ResourceDescriptor> getResourceDescriptors();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Gets direct ResourceDescriptor whose id is the given guid
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	ResourceDescriptor getResourceDescriptor(String guid);

    /**
     * Removes the ResourceDescriptor identified by the given uri. If clearFolder is true, this call will remove
     * all other ResourceDescriptors whose URI shares the same folder of the given uri.
     * 
     * @param uri
     * @param clearFolder
     * @return list of ResourceDescriptors that had been removed
     */
    List<ResourceDescriptor> removeResourceDescriptor(URI uri, boolean clearFolder);

	void dispose();
	
}
