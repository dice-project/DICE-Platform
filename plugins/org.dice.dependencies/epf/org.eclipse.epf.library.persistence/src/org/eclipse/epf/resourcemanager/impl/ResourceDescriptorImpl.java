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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.epf.persistence.MultiFileSaveUtil;
import org.eclipse.epf.resourcemanager.ResourceDescriptor;
import org.eclipse.epf.resourcemanager.ResourcemanagerPackage;
import org.eclipse.epf.uma.ecore.impl.MultiResourceEObject;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Resource Descriptor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.resourcemanager.impl.ResourceDescriptorImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.epf.resourcemanager.impl.ResourceDescriptorImpl#getUri <em>Uri</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ResourceDescriptorImpl extends MultiResourceEObject implements ResourceDescriptor {
	
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 3256725082662385462L;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
    protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
    protected String id = ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getUri() <em>Uri</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getUri()
	 * @generated
	 * @ordered
	 */
    protected static final String URI_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUri() <em>Uri</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getUri()
	 * @generated
	 * @ordered
	 */
    protected String uri = URI_EDEFAULT;

	protected URI resolvedURI;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected ResourceDescriptorImpl() {
		super();
  
 //UMA-->
 		reassignDefaultValues();
 //UMA<--  
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
				protected EClass eStaticClass() {
		return ResourcemanagerPackage.Literals.RESOURCE_DESCRIPTOR;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ResourcemanagerPackage.RESOURCE_DESCRIPTOR__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 */
    public String getUri() {
		if(resolvedURI != null && (uri == null || uri.startsWith("../"))) { //$NON-NLS-1$
			uri = resolvedURI.deresolve(MultiFileSaveUtil.getFinalURI(eResource())).toString();
		}
		return uri;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setUri(String newUri) {
		String oldUri = uri;
		uri = newUri;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ResourcemanagerPackage.RESOURCE_DESCRIPTOR__URI, oldUri, uri));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ResourcemanagerPackage.RESOURCE_DESCRIPTOR__ID:
				return getId();
			case ResourcemanagerPackage.RESOURCE_DESCRIPTOR__URI:
				return getUri();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ResourcemanagerPackage.RESOURCE_DESCRIPTOR__ID:
				setId((String)newValue);
				return;
			case ResourcemanagerPackage.RESOURCE_DESCRIPTOR__URI:
				setUri((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ResourcemanagerPackage.RESOURCE_DESCRIPTOR__ID:
				setId(ID_EDEFAULT);
				return;
			case ResourcemanagerPackage.RESOURCE_DESCRIPTOR__URI:
				setUri(URI_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
//UMA-->
		EStructuralFeature feature = getFeatureWithOverridenDefaultValue(featureID);
		if(feature != null) {
			return isFeatureWithOverridenDefaultValueSet(feature);
		}
//UMA<--		
		switch (featureID) {
			case ResourcemanagerPackage.RESOURCE_DESCRIPTOR__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case ResourcemanagerPackage.RESOURCE_DESCRIPTOR__URI:
				return URI_EDEFAULT == null ? uri != null : !URI_EDEFAULT.equals(uri);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
				public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (id: "); //$NON-NLS-1$
		result.append(id);
		result.append(", uri: "); //$NON-NLS-1$
		result.append(uri);
		result.append(')');
		return result.toString();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.uma.resourcemanager.ResourceDescriptor#getURI()
	 */
	public URI getResolvedURI() {
		if(resolvedURI == null) {
			Resource resource = eResource();
			if(resource != null) {
				URI base = MultiFileSaveUtil.getFinalURI(eResource());
				resolvedURI = URI.createURI(getUri()).resolve(base);
			}
			else {
				resolvedURI = URI.createURI(getUri());
			}
		}
		return resolvedURI;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.uma.resourcemanager.ResourceDescriptor#setURI(org.eclipse.emf.common.util.URI)
	 */
	public void setResolvedURI(URI uri) {		
		resolvedURI = uri;
		String newUri = null;
		if(resolvedURI != null) {
			Resource resource = eResource();	
			if(resource != null) {
				URI baseURI = MultiFileSaveUtil.getFinalURI(resource);
				newUri = resolvedURI.deresolve(baseURI).toString();
			}
		}
		setUri(newUri);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.uma.resourcemanager.ResourceDescriptor#clearResolvedURI()
	 */
	public void clearResolvedURI() {
		resolvedURI = null;
	}
	
}
