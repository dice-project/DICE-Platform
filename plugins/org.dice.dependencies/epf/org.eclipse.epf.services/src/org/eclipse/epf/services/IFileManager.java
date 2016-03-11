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

import java.io.File;

import org.eclipse.core.runtime.IStatus;

/**
 * The interface for a File Manager.
 * <p>
 * A File Manager is responsible for managing the XMI files associated with the
 * method elements.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public interface IFileManager {

	/**
	 * Checks whether the given method element path can be modified.
	 * 
	 * @param path
	 *            an absolute path to a method element
	 * @param context
	 *            the <code>org.eclipse.swt.widgets.Shell</code> that is to be
	 *            used to parent any dialogs with the user, or <code>null</code>
	 *            if there is no UI context (declared as an <code>Object</code>
	 *            to avoid any direct references on the SWT component)
	 * @return a status object
	 */
	public IStatus checkModify(String path, Object context);

	/**
	 * Checks whether the given method element path can be modified.
	 * 
	 * @param paths
	 *            an array of path fragments that point to a method element
	 * @param context
	 *            the <code>org.eclipse.swt.widgets.Shell</code> that is to be
	 *            used to parent any dialogs with the user, or <code>null</code>
	 *            if there is no UI context (declared as an <code>Object</code>
	 *            to avoid any direct references on the SWT component)
	 * @return a status object
	 */
	public IStatus checkModify(String[] paths, Object context);

	/**
	 * Deletes the given method element path.
	 * 
	 * @param path
	 *            an absolute path to a method element
	 * @return <code>true</code> if the deletion is successful
	 */
	public boolean delete(String path);

	/**
	 * Renames the path of a method element.
	 * 
	 * @param oldFile
	 *            a <code>File</code> object that contains a method element's
	 *            old path
	 * @param newFile
	 *            a <code>File</code> object that contains a method element's
	 *            new path
	 * @return <code>true</code> if the renaming is successful
	 */
	public boolean rename(File oldFile, File newFile);

	/**
	 * Moves a method element to a new location.
	 * 
	 * @param oldPath
	 *            a method element's old path
	 * @param newName
	 *            a method element's new path
	 * @return <code>true</code> if the move is successful
	 */
	public boolean move(String oldPath, String newPath);

}