//------------------------------------------------------------------------------
// Copyright (c) 2005, 2007 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.library.edit.command;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.DragAndDropCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.epf.library.edit.util.TngUtil;

/**
 * @author Phong Nguyen Le
 * @sine 1.2
 */
public class ResourceAwareDragAndDropCommand extends DragAndDropCommand 
implements IResourceAwareCommand
{

	private Set<Resource> modifiedResources;

	/**
	 * @param domain
	 * @param owner
	 * @param location
	 * @param operations
	 * @param operation
	 * @param collection
	 */
	public ResourceAwareDragAndDropCommand(EditingDomain domain, Object owner,
			float location, int operations, int operation, Collection<?> collection) {
		super(domain, owner, location, operations, operation, collection);
	}

	/**
	 * @param domain
	 * @param owner
	 * @param location
	 * @param operations
	 * @param operation
	 * @param collection
	 * @param optimize
	 */
	public ResourceAwareDragAndDropCommand(EditingDomain domain, Object owner,
			float location, int operations, int operation,
			Collection<?> collection, boolean optimize) {
		super(domain, owner, location, operations, operation, collection,
				optimize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.command.IResourceAwareCommand#getModifiedResources()
	 */
	@SuppressWarnings("unchecked") 
	public Collection<Resource> getModifiedResources() {
		modifiedResources = null;	//wlu: Disable cache - cache without update for change would not work
		
		if(modifiedResources == null) {
			if(dropCommand != null) {
				modifiedResources = TngUtil.getModifiedResources(dropCommand);
			}
			if(dragCommand != null) {
				Set<Resource> resources = TngUtil.getModifiedResources(dragCommand);
				if (modifiedResources != null) {
					modifiedResources.addAll(resources);
				} else {
					modifiedResources = resources;
				}
			}
			if(modifiedResources == null || modifiedResources.isEmpty()) {
				modifiedResources = Collections.EMPTY_SET;
			}
		}
		return modifiedResources;
	}

}
