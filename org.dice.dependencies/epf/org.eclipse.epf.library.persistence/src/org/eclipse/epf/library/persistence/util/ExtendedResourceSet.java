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
package org.eclipse.epf.library.persistence.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.epf.common.CommonPlugin;
import org.eclipse.epf.persistence.UnnormalizedURIException;

/**
 * A extended resource set whose objects can references objects of other resource set
 * 
 * @author Phong Nguyen Le - Aug 9, 2006
 * @since  1.0
 */
public class ExtendedResourceSet extends ResourceSetImpl {
	public static final int URI_TYPE_UNKNOWN = 0;
	public static final int URI_TYPE_LOCAL = 1;
	public static final int URI_TYPE_EXTERNAL = 2;
	
	protected List<ResourceSet> referencedResourceSets = new ArrayList<ResourceSet>();

	
	/**
	 * Gets the type of the given URI.
	 * 
	 * @param uri
	 * @return one of URI type constants: {@link #URI_TYPE_EXTERNAL}, {@link #URI_TYPE_LOCAL}, {@link #URI_TYPE_UNKNOWN}
	 */
	protected int getURIType(URI uri) {
		return URI_TYPE_UNKNOWN;
	}
	
	public boolean add(ResourceSet referencedResourceSet) {
		if(!referencedResourceSets.contains(referencedResourceSet)) {
			return referencedResourceSets.add(referencedResourceSet);
		}
		return false;
	}
	
	@Override
	public EObject getEObject(URI uri, boolean loadOnDemand) {
		EObject object = null;
		UnnormalizedURIException ex = null;
		int uriType = getURIType(uri);
		if(uriType == URI_TYPE_UNKNOWN || uriType == URI_TYPE_LOCAL) {
			try {
				object = super.getEObject(uri, loadOnDemand);
			} catch (UnnormalizedURIException e) {
				ex = e;
			}
			if(object != null) {
				return object;
			}
		}
		if(uriType == URI_TYPE_UNKNOWN || uriType == URI_TYPE_EXTERNAL) {
			for (Iterator iter = referencedResourceSets.iterator(); iter.hasNext();) {
				ResourceSet resourceSet = (ResourceSet) iter.next();
				try {
					object = resourceSet.getEObject(uri, loadOnDemand);
					if(object != null) {
						return object;
					}
				} catch (UnnormalizedURIException e) {
					ex = e;
				}
			}
		} 
		
		if(ex != null) {
			throw ex;
		}
		return object;
	}	
	
	public void dispose() {
		for (Iterator iter = getResources().iterator(); iter.hasNext();) {
			Resource resource = (Resource) iter.next();
			try{
				resource.unload();			
			}
			catch(Exception e) {
				CommonPlugin.getDefault().getLogger().logError(e);
			}
		}
		referencedResourceSets.clear();
	}
}
