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
package org.eclipse.epf.library.persistence.synch.internal;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.epf.library.persistence.synch.ISynchronizationHelper;
import org.eclipse.epf.persistence.FileManager;
import org.eclipse.epf.persistence.IFileInfo;
import org.eclipse.epf.persistence.MultiFileXMIResourceImpl;

/**
 * @author Phong Nguyen Le
 * @sine 1.2
 */
public class SynchronizationHelper implements ISynchronizationHelper {

	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.persistence.synch.ISynchronizationHelper#getModificationStamp(org.eclipse.emf.ecore.resource.Resource)
	 */
	public long getModificationStamp(Resource resource) {
		if(resource instanceof MultiFileXMIResourceImpl) {
			IFileInfo info = FileManager.getInstance().getFileInfo(resource);
			return info != null ? info.getModificationStamp() : IResource.NULL_STAMP;
		}
		return IResource.NULL_STAMP;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.persistence.synch.ISynchronizationHelper#isSynchronized(org.eclipse.emf.ecore.resource.Resource)
	 */
	public boolean isSynchronized(Resource resource) {
		if(resource instanceof MultiFileXMIResourceImpl) {
			return ((MultiFileXMIResourceImpl)resource).isSynchronized();
		}
		IFile file = WorkspaceSynchronizer.getFile(resource);
		if(file != null) {
			return file.isSynchronized(IResource.DEPTH_ZERO);
		}
		return true;
	}

}
