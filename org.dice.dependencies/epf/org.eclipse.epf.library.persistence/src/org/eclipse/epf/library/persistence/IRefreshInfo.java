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
package org.eclipse.epf.library.persistence;

import java.util.Collection;

import org.eclipse.emf.ecore.resource.Resource;

/**
 * @author Phong Nguyen Le - Oct 3, 2006
 * @since  1.0
 */
public interface IRefreshInfo {
	/**
	 * Gets existing resources that reappears in workspace
	 * 
	 * @return the addedResources
	 */
	public Collection getAddedResources();
	
	/**
	 * Gets newly added workspace resources
	 * 
	 * @return
	 */
	public Collection getAddedWorkspaceResources();
	
	/**
	 * @return Returns the changedResources.
	 */
	public Collection getChangedResources();
	
	/**
	 * @return the loadedBeforeRefreshResources
	 */
	public Collection getReloadedBeforeRefreshResources();
	
	/**
	 * @return Returns the removedResources.
	 */
	public Collection getRemovedResources();
	
	/**
	 * Notifies this manager that the given resource just has been saved.
	 * 
	 * @param resource
	 */
	public void resourceSaved(Resource resource);	
}
