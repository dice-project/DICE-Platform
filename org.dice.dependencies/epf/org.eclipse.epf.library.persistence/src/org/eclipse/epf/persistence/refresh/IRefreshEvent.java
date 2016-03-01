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
package org.eclipse.epf.persistence.refresh;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * Instance of this class provides data about a refresh event.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public interface IRefreshEvent {

	/**
	 * Gets resources that had been refreshed.
	 * 
	 * @return refreshed resources
	 */
	Collection<Resource> getRefreshedResources();
	
	Collection<Resource> getUnloadedResources();

	/**
	 * Gets objects that had been unloaded and become proxies after refresh
	 * 
	 * @return unloaded objects
	 */
	Collection<EObject> getUnloadedObjects();

	/**
	 * Gets objects that had been refreshed.
	 * 
	 * @return refreshed objects
	 */
	Collection<EObject> getRefreshedObjects();
}
