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
package org.eclipse.epf.persistence.refresh.internal;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.epf.persistence.refresh.IRefreshEvent;

/**
 * Implementation class of IRefreshEvent
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class RefreshEvent implements IRefreshEvent {

	private Collection<Resource> refreshedResources = Collections.emptyList();
	
	private Collection<Resource> unloadedResources = Collections.emptyList();

	private Collection<EObject> unloadedObjects = Collections.emptyList();
	
	private Collection<EObject> refreshedObjects = Collections.emptyList();
	
	public RefreshEvent(Collection<Resource> unloadedResources) {
		this.unloadedResources = unloadedResources;
	}

	public RefreshEvent(Collection<Resource> refreshedResources,
			Collection<EObject> unloadedObjects) {
		this(refreshedResources, unloadedObjects, Collections.EMPTY_LIST);
	}	

	/**
	 * @param refreshedResources
	 * @param unloadedObjects
	 * @param refreshedObjects
	 */
	public RefreshEvent(Collection<Resource> refreshedResources, Collection<EObject> unloadedObjects, Collection<EObject> refreshedObjects) {
		super();
		this.refreshedResources = refreshedResources;
		this.unloadedObjects = unloadedObjects;
		this.refreshedObjects = refreshedObjects;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.persistence.refresh.IRefreshEvent#getRefreshedResources()
	 */
	public Collection<Resource> getRefreshedResources() {
		return refreshedResources;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.persistence.refresh.IRefreshEvent#getUnloadedObjects()
	 */
	public Collection<EObject> getUnloadedObjects() {
		return unloadedObjects;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.persistence.refresh.IRefreshEvent#getRefreshedObjects()
	 */
	public Collection<EObject> getRefreshedObjects() {
		return refreshedObjects;
	}

	public Collection<Resource> getUnloadedResources() {
		return unloadedResources;
	}
	
}
