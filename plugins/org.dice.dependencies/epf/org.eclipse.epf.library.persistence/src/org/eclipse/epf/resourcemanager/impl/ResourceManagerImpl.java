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

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.epf.persistence.MultiFileSaveUtil;
import org.eclipse.epf.resourcemanager.ResourceDescriptor;
import org.eclipse.epf.resourcemanager.ResourceManager;
import org.eclipse.epf.resourcemanager.ResourcemanagerPackage;
import org.eclipse.epf.uma.ecore.impl.MultiResourceEObject;
import org.eclipse.epf.uma.util.UmaUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Resource Manager</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.resourcemanager.impl.ResourceManagerImpl#getGuid <em>Guid</em>}</li>
 *   <li>{@link org.eclipse.epf.resourcemanager.impl.ResourceManagerImpl#getUriBase <em>Uri Base</em>}</li>
 *   <li>{@link org.eclipse.epf.resourcemanager.impl.ResourceManagerImpl#getSubManagers <em>Sub Managers</em>}</li>
 *   <li>{@link org.eclipse.epf.resourcemanager.impl.ResourceManagerImpl#getResourceDescriptors <em>Resource Descriptors</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ResourceManagerImpl extends MultiResourceEObject implements ResourceManager {
	
	private static final long serialVersionUID = -5164471475509169204L;

	/**
	 * The default value of the '{@link #getGuid() <em>Guid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGuid()
	 * @generated
	 * @ordered
	 */
	protected static final String GUID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getGuid() <em>Guid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGuid()
	 * @generated
	 * @ordered
	 */
	protected String guid = GUID_EDEFAULT;

	/**
	 * The default value of the '{@link #getUriBase() <em>Uri Base</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUriBase()
	 * @generated
	 * @ordered
	 */
	protected static final String URI_BASE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUriBase() <em>Uri Base</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUriBase()
	 * @generated
	 * @ordered
	 */
	protected String uriBase = URI_BASE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSubManagers() <em>Sub Managers</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubManagers()
	 * @generated
	 * @ordered
	 */
	protected EList<ResourceManager> subManagers;

	/**
	 * The cached value of the '{@link #getResourceDescriptors() <em>Resource Descriptors</em>}' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getResourceDescriptors()
	 * @generated
	 * @ordered
	 */
    protected EList<ResourceDescriptor> resourceDescriptors;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 */
    protected ResourceManagerImpl() {
		super();
		
		guid = UmaUtil.generateGUID();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
				protected EClass eStaticClass() {
		return ResourcemanagerPackage.Literals.RESOURCE_MANAGER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getGuid() {
		return guid;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGuid(String newGuid) {
		String oldGuid = guid;
		guid = newGuid;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ResourcemanagerPackage.RESOURCE_MANAGER__GUID, oldGuid, guid));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getUriBase() {
		return uriBase;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUriBase(String newUriBase) {
		String oldUriBase = uriBase;
		uriBase = newUriBase;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ResourcemanagerPackage.RESOURCE_MANAGER__URI_BASE, oldUriBase, uriBase));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<ResourceManager> getSubManagers() {
		if (subManagers == null) {
			subManagers = new EObjectContainmentEList.Resolving<ResourceManager>(ResourceManager.class, this, ResourcemanagerPackage.RESOURCE_MANAGER__SUB_MANAGERS);
		}
		return subManagers;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public List<ResourceDescriptor> getResourceDescriptors() {
		if (resourceDescriptors == null) {
			resourceDescriptors = new EObjectContainmentEList.Resolving<ResourceDescriptor>(ResourceDescriptor.class, this, ResourcemanagerPackage.RESOURCE_MANAGER__RESOURCE_DESCRIPTORS);
		}
		return resourceDescriptors;
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
		result.append(" (guid: "); //$NON-NLS-1$
		result.append(guid);
		result.append(", uriBase: "); //$NON-NLS-1$
		result.append(uriBase);
		result.append(')');
		return result.toString();
	}

	//
	// Start custom code
	//

	/* (non-Javadoc)
	 * @see com.ibm.umaf.ecore.impl.MultiResourceEObject#eResolveProxy(org.eclipse.emf.ecore.InternalEObject)
	 */
	public EObject eResolveProxy(InternalEObject proxy) {
		return super.eResolveProxy(proxy);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.epf.uma.resourcemanager.ResourceManager#resolve()
	 */
	public void resolve() {
		URI base = MultiFileSaveUtil.getFinalURI(eResource());
        for (Iterator iter = getResourceDescriptors().iterator(); iter.hasNext();) {
            ResourceDescriptorImpl desc = (ResourceDescriptorImpl) iter.next();
            desc.resolvedURI = URI.createURI(desc.getUri()).resolve(base);
        }
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.uma.resourcemanager.ResourceManager#deresolve()
	 */
	public void deresolve() {
		URI base = MultiFileSaveUtil.getFinalURI(eResource());
        // convert path in all descriptors to relative
        //
        for (Iterator iter = getResourceDescriptors().iterator(); iter.hasNext();) {
            ResourceDescriptor desc = (ResourceDescriptor) iter.next();
            desc.setUri(desc.getResolvedURI().deresolve(base).toString());
        }
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.epf.uma.resourcemanager.ResourceManager#removeResourceDescriptor(org.eclipse.emf.common.util.URI, boolean)
	 */
	public List removeResourceDescriptor(URI uri, boolean clearFolder) {
		ArrayList removedList = new ArrayList();
//		ResourceDescriptor removedDescriptor = null;
        for (Iterator iter = getResourceDescriptors().iterator(); iter.hasNext();) {
            ResourceDescriptor desc = (ResourceDescriptor) iter.next();
            if(uri.equals(desc.getResolvedURI().trimFragment())) {
                iter.remove();
//                removedDescriptor = desc;
                removedList.add(desc);
            }
        }
//        if(removedDescriptor != null) {        	
        	if(clearFolder) {
                // remove all child descriptors of the removed descriptor
        		//
        		String oldDir = new File(uri.toFileString()).getParent() + File.separator;
        		for (Iterator iter = getResourceDescriptors().iterator(); iter.hasNext();) {
        			ResourceDescriptor desc = (ResourceDescriptor) iter.next();
        			if(desc.getResolvedURI().toFileString().startsWith(oldDir)) {
        				iter.remove();
        				removedList.add(desc);
        			}
        		}
        	}
//        	else {
//        		// remove all the descriptors of the same resource
//        		//
//        		URI resUri = removedDescriptor.getResolvedURI().trimFragment();
//        		for (Iterator iter = getResourceDescriptors().iterator(); iter.hasNext();) {
//        			ResourceDescriptor desc = (ResourceDescriptor) iter.next();
//        			if(desc.getResolvedURI().trimFragment().equals(resUri)) {
//        				iter.remove();
//        				removedList.add(desc);
//        			}
//        		}        		
//        	}
//        }
        return removedList;
	}	

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public ResourceDescriptor getResourceDescriptor(String guid) {
		if(idToResourceDescriptorMap == null) {
			idToResourceDescriptorMap = new HashMap();
			
			eAdapters().add(new AdapterImpl() {
		        public void notifyChanged(Notification msg) {
		            ResourceDescriptor desc;
		            switch(msg.getFeatureID(ResourceManager.class)) {
		                case ResourcemanagerPackage.RESOURCE_MANAGER__RESOURCE_DESCRIPTORS:
		                    switch(msg.getEventType()) {
		                        case Notification.ADD:
		                            desc = (ResourceDescriptor)msg.getNewValue();
									idToResourceDescriptorMap.put(desc.getId(), desc);
		                            return;
		                        case Notification.ADD_MANY:
		                            for (Iterator iter = ((Collection)msg.getNewValue()).iterator(); iter
		                                    .hasNext();) {
										desc = (ResourceDescriptor) iter.next();
										idToResourceDescriptorMap.put(desc.getId(), desc);
		                            }
		                            return;
		                        case Notification.REMOVE:
		                            idToResourceDescriptorMap.remove(((ResourceDescriptor)msg.getOldValue()).getId());
		                            return;
		                        case Notification.REMOVE_MANY:
		                            for (Iterator iter = ((Collection)msg.getOldValue()).iterator(); iter
		                                    .hasNext();) {
		                                idToResourceDescriptorMap.remove(((ResourceDescriptor) iter.next()).getId());
		                            }
		                            return;
		                    }
		            }
		        }

			});
			
			// populate the map
			//
			for (Iterator iter = getResourceDescriptors().iterator(); iter.hasNext();) {
				ResourceDescriptor element = (ResourceDescriptor) iter.next();
				idToResourceDescriptorMap.put(element.getId(), element);
			}
		}
		
		return (ResourceDescriptor) idToResourceDescriptorMap.get(guid);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ResourcemanagerPackage.RESOURCE_MANAGER__SUB_MANAGERS:
				return ((InternalEList<?>)getSubManagers()).basicRemove(otherEnd, msgs);
			case ResourcemanagerPackage.RESOURCE_MANAGER__RESOURCE_DESCRIPTORS:
				return ((InternalEList<?>)getResourceDescriptors()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ResourcemanagerPackage.RESOURCE_MANAGER__GUID:
				return getGuid();
			case ResourcemanagerPackage.RESOURCE_MANAGER__URI_BASE:
				return getUriBase();
			case ResourcemanagerPackage.RESOURCE_MANAGER__SUB_MANAGERS:
				return getSubManagers();
			case ResourcemanagerPackage.RESOURCE_MANAGER__RESOURCE_DESCRIPTORS:
				return getResourceDescriptors();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
		@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ResourcemanagerPackage.RESOURCE_MANAGER__GUID:
				setGuid((String)newValue);
				return;
			case ResourcemanagerPackage.RESOURCE_MANAGER__URI_BASE:
				setUriBase((String)newValue);
				return;
			case ResourcemanagerPackage.RESOURCE_MANAGER__SUB_MANAGERS:
				getSubManagers().clear();
				getSubManagers().addAll((Collection<? extends ResourceManager>)newValue);
				return;
			case ResourcemanagerPackage.RESOURCE_MANAGER__RESOURCE_DESCRIPTORS:
				getResourceDescriptors().clear();
				getResourceDescriptors().addAll((Collection<? extends ResourceDescriptor>)newValue);
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
			case ResourcemanagerPackage.RESOURCE_MANAGER__GUID:
				setGuid(GUID_EDEFAULT);
				return;
			case ResourcemanagerPackage.RESOURCE_MANAGER__URI_BASE:
				setUriBase(URI_BASE_EDEFAULT);
				return;
			case ResourcemanagerPackage.RESOURCE_MANAGER__SUB_MANAGERS:
				getSubManagers().clear();
				return;
			case ResourcemanagerPackage.RESOURCE_MANAGER__RESOURCE_DESCRIPTORS:
				getResourceDescriptors().clear();
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
			case ResourcemanagerPackage.RESOURCE_MANAGER__GUID:
				return GUID_EDEFAULT == null ? guid != null : !GUID_EDEFAULT.equals(guid);
			case ResourcemanagerPackage.RESOURCE_MANAGER__URI_BASE:
				return URI_BASE_EDEFAULT == null ? uriBase != null : !URI_BASE_EDEFAULT.equals(uriBase);
			case ResourcemanagerPackage.RESOURCE_MANAGER__SUB_MANAGERS:
				return subManagers != null && !subManagers.isEmpty();
			case ResourcemanagerPackage.RESOURCE_MANAGER__RESOURCE_DESCRIPTORS:
				return resourceDescriptors != null && !resourceDescriptors.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.uma.resourcemanager.ResourceManager#dispose()
	 */
	public void dispose() {
		if(idToResourceDescriptorMap != null) {
			idToResourceDescriptorMap.clear();
			idToResourceDescriptorMap = null;
		}
		
		eAdapters().clear();		
		
		// dispose the sub managers
		//
		for (Iterator iter = ((InternalEList)getSubManagers()).basicIterator(); iter.hasNext();) {
			ResourceManager subMgr = (ResourceManager) iter.next();
			if(!((InternalEObject)subMgr).eIsProxy()) {
				subMgr.dispose();
			}
		}
	}

	private Map idToResourceDescriptorMap;

}
