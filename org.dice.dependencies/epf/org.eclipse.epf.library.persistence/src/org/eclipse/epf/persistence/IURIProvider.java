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
package org.eclipse.epf.persistence;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;

/**
 * Interface to provide URI for an EObject.
 * 
 * @author Phong Nguyen Le - Aug 8, 2006
 * @since  1.0
 */
public interface IURIProvider {
	
	/**
	 * Gets the URI for the given object if this object has or should have a direct resource
	 * 
	 * @param object
	 * @return the URI or null if the given object does/will not have a direct resource
	 */
	URI getURI(EObject object);
}
