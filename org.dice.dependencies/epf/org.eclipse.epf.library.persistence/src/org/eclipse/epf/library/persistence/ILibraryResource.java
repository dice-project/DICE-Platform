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

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * @author Phong Nguyen Le
 * @since  1.0
 */
public interface ILibraryResource extends Resource {
	/**
	 * Gets load stamp.
	 * 
	 * @return
	 */
	long getLoadStamp();
	
	/**
	 * Gets proxy URI for the specified object.
	 * 
	 * @param object
	 * @return
	 */
	URI getProxyURI(EObject object);
}
