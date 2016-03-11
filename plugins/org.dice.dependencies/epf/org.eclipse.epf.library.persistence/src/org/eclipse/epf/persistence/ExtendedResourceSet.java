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
 * A extended resource set whose objects can references objects of other resource set
 * 
 * @author Phong Nguyen Le - Aug 9, 2006
 * @since  1.0
 */
public class ExtendedResourceSet extends MultiFileResourceSetImpl {
	public static final int URI_TYPE_UNKNOWN = 0;
	public static final int URI_TYPE_LOCAL = 1;
	public static final int URI_TYPE_EXTERNAL = 2;
	
	protected MultiFileResourceSetImpl referencedResourceSet;

	/**
	 * Creates an {@link ExtendedResourceSet} that can have reference to objects in the given resource set 
	 */
	public ExtendedResourceSet(MultiFileResourceSetImpl referencedResourceSet) {
		super();
		this.referencedResourceSet = referencedResourceSet;
	}
	
	/**
	 * Gets the type of the given URI.
	 * 
	 * @param uri
	 * @return one of URI type constants: {@link #URI_TYPE_EXTERNAL}, {@link #URI_TYPE_LOCAL}, {@link #URI_TYPE_UNKNOWN}
	 */
	protected int getURIType(URI uri) {
		return URI_TYPE_UNKNOWN;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.epf.persistence.MultiFileResourceSetImpl#getEObject(org.eclipse.emf.ecore.EObject, org.eclipse.emf.common.util.URI, boolean)
	 */
	public EObject getEObject(EObject resolver, URI uri, boolean loadOnDemand) {
		EObject object = null;
		UnnormalizedURIException ex = null;
		int uriType = getURIType(uri);
		if(uriType == URI_TYPE_UNKNOWN || uriType == URI_TYPE_LOCAL) {
			try {
				object = super.getEObject(resolver, uri, loadOnDemand);
			} catch (UnnormalizedURIException e) {
				ex = e;
			}
			if(object != null) {
				return object;
			}
		}
		if(uriType == URI_TYPE_UNKNOWN || uriType == URI_TYPE_EXTERNAL) {
			try {
				object = referencedResourceSet.getEObject(resolver, uri,
						loadOnDemand);
			} catch (UnnormalizedURIException e) {
				ex = e;
			}
		} 
		
		if(ex != null) {
			throw ex;
		}
		return object;
	}
}
