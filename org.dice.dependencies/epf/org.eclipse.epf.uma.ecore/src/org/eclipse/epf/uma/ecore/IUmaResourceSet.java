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
package org.eclipse.epf.uma.ecore;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;

/**
 * The interface for a UMA resource set.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public interface IUmaResourceSet extends ResourceSet {

	/**
	 * Gets the model object resolved by a URI.
	 * 
	 * @param resolver
	 *            the proxy resolver
	 * @param uri
	 *            the URI used to resolve the proxy
	 * @param loadOnDemand
	 *            if <code>true</code>, create and load the resource if it
	 *            does not exists
	 * @return the model object resolved by the URI
	 * @see org.eclipse.emf.ecore.resource.ResourceSet#getEObject(EObject, URI, boolean)
	 */
	EObject getEObject(EObject resolver, URI uri, boolean loadOnDemand);

}
