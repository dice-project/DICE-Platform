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

import java.io.File;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.epf.services.IFileManager;

/**
 * @author Phong Nguyen Le - Oct 10, 2006
 * @since  1.0
 */
public class DelegateFileManager implements IFileManager {

	/* (non-Javadoc)
	 * @see org.eclipse.epf.uma.util.IFileManager#checkModify(java.lang.String, java.lang.Object)
	 */
	public IStatus checkModify(String path, Object context) {
		return FileManager.getInstance().checkModify(path, context);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.uma.util.IFileManager#checkModify(java.lang.String[], java.lang.Object)
	 */
	public IStatus checkModify(String[] paths, Object context) {
		return FileManager.getInstance().checkModify(paths, context);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.uma.util.IFileManager#delete(java.lang.String)
	 */
	public boolean delete(String path) {
		return FileManager.getInstance().delete(path);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.uma.util.IFileManager#move(java.lang.String, java.lang.String)
	 */
	public boolean move(String oldPath, String newPath) {
		return FileManager.getInstance().move(oldPath, newPath);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.uma.util.IFileManager#rename(java.io.File, java.io.File)
	 */
	public boolean rename(File oldFile, File newFile) {
		return FileManager.getInstance().rename(oldFile, newFile);
	}

}
