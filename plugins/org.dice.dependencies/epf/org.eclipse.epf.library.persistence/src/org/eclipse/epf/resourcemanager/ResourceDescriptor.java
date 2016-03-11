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

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Resource Descriptor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.resourcemanager.ResourceDescriptor#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.epf.resourcemanager.ResourceDescriptor#getUri <em>Uri</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.resourcemanager.ResourcemanagerPackage#getResourceDescriptor()
 * @model
 * @generated
 */
public interface ResourceDescriptor extends EObject {
	
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Id</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.eclipse.epf.resourcemanager.ResourcemanagerPackage#getResourceDescriptor_Id()
	 * @model
	 * @generated
	 */
    String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.resourcemanager.ResourceDescriptor#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
    void setId(String value);

	/**
	 * Returns the value of the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Uri</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Uri</em>' attribute.
	 * @see #setUri(String)
	 * @see org.eclipse.epf.resourcemanager.ResourcemanagerPackage#getResourceDescriptor_Uri()
	 * @model
	 * @generated
	 */
    String getUri();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.resourcemanager.ResourceDescriptor#getUri <em>Uri</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Uri</em>' attribute.
	 * @see #getUri()
	 * @generated
	 */
    void setUri(String value);

    URI getResolvedURI();
    
    void setResolvedURI(URI uri);
	
	/**
	 * Clears the resolved URI that has been cached in previous called of getResolvedURI()
	 * so the next call to getResolvedURI() will update the resolvedURI.
	 *
	 */
	void clearResolvedURI();

}
