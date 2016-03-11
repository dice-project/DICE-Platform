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
package org.eclipse.epf.services;

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * Controls access to library objects.
 * 
 * @author Phong Nguyen Le - Oct 9, 2006
 * @since 1.0
 */
public interface IAccessController {
	/**
	 * Checks if the caller can modify the given EObjects in the specified
	 * collection.
	 * 
	 * @param eObjects collection of the EObjects to check
	 * @param context the context of the caller 
	 * @return
	 */
	IStatus checkModify(Collection<EObject> eObjects, Object context);
	
	IStatus checkModify(Resource[] resources, Object context);
}
