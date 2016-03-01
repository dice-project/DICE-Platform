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
package org.eclipse.epf.library.persistence.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.epf.library.persistence.ILibraryResourceSet;
import org.eclipse.epf.persistence.FileManager;
import org.eclipse.epf.services.IAccessController;

/**
 * @author Phong Nguyen Le - Oct 9, 2006
 * @since  1.0
 */
public class AccessController implements IAccessController {

	public AccessController() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.uma.util.IAccessController#checkModify(java.util.Collection, java.lang.Object)
	 */
	public IStatus checkModify(Collection eObjects, Object context) {
		if(eObjects.isEmpty()) {
			return Status.OK_STATUS;
		}
		ILibraryResourceSet resourceSet = null;
		for (Iterator iter = eObjects.iterator(); iter.hasNext();) {
			EObject o = (EObject) iter.next();
			Resource resource = o.eResource();
			if(resource != null && resource.getResourceSet() instanceof ILibraryResourceSet) {
				resourceSet = (ILibraryResourceSet) resource.getResourceSet();
				if(resourceSet != null) {
					break;
				}
			}
		}
		if(resourceSet != null) {
			return resourceSet.checkModify(eObjects, context);
		}
		return Status.OK_STATUS;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.uma.util.IAccessController#checkModify(org.eclipse.emf.ecore.resource.Resource[], java.lang.Object)
	 */
	public IStatus checkModify(Resource[] resources, Object context) {
		if(resources.length == 0) {
			return Status.OK_STATUS;
		}
		ArrayList<String> paths = new ArrayList<String>();
		for (int i = 0; i < resources.length; i++) {
			Resource resource = resources[i];
			if (resource != null) {
				String path = FileManager.toFileString(resource.getURI());
				if(path != null) {
					paths.add(path);
				}
			}
		}
		if(!paths.isEmpty()) {
			String[] arr = new String[paths.size()];
			paths.toArray(arr);
			return FileManager.getInstance().checkModify(arr, context);
		}
		return Status.OK_STATUS;	
		
//		MultiStatus multiStatus = new MultiStatus(PersistencePlugin.getDefault().getId(),
//				IStatus.OK, PersistenceResources.modifyFilesError_msg,
//				null);
//
//		HashMap<ILibraryResourceSet, Set<Resource>> resourceSetToResourcesMap = new HashMap<ILibraryResourceSet, Set<Resource>>();
//		Set<Resource> resourcesWithoutLibraryResourceSet = null;
//		for (int i = 0; i < resources.length; i++) {
//			Resource resource = resources[i];
//			if(resource.getResourceSet() instanceof ILibraryResourceSet) {
//				Set set = resourceSetToResourcesMap.get(resource.getResourceSet());
//				if(set == null) {
//					set = new HashSet<Resource>();
//					resourceSetToResourcesMap.put((ILibraryResourceSet) resource.getResourceSet(), set);
//				}
//				set.add(resource);				
//			}
//			else {
//				if(resourcesWithoutLibraryResourceSet == null) {
//					resourcesWithoutLibraryResourceSet = new HashSet<Resource>();
//				}
//				resourcesWithoutLibraryResourceSet.add(resource);
//			}
//		}
//		if(!resourceSetToResourcesMap.isEmpty()) {
//			for (Iterator iter = resourceSetToResourcesMap.entrySet().iterator(); iter
//					.hasNext();) {
//				Map.Entry<ILibraryResourceSet, Set<Resource>> entry = (Entry<ILibraryResourceSet, Set<Resource>>) iter.next();
//				Resource[] arr = new Resource[entry.getValue().size()];
//				entry.getValue().toArray(arr);
//				entry.getKey().checkModify(arr, context);
//			}
//		}
	}

}
