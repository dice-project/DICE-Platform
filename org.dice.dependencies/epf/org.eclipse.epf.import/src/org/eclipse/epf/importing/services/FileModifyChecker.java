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
package org.eclipse.epf.importing.services;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.epf.common.ui.util.MsgBox;
import org.eclipse.epf.common.ui.util.MsgDialog;
import org.eclipse.epf.importing.ImportPlugin;
import org.eclipse.epf.importing.ImportResources;
import org.eclipse.epf.library.services.SafeUpdateController;
import org.eclipse.epf.services.IFileManager;
import org.eclipse.epf.services.Services;


/**
 * utility class to check files for modifications
 * 
 * @author Jinhua Xi
 * @since 1.0
 *
 */
public class FileModifyChecker {

	/**
	 * Checks modification status of the given list of files.
	 */
	public static IStatus checkModify(List modifiedFiles) {
		
		try {
			String[] paths = new String[modifiedFiles.size()];
			modifiedFiles.toArray(paths);
			
			IFileManager fileMgr = Services.getFileManager();
			IStatus status = fileMgr.checkModify(paths, MsgBox.getDefaultShell());

			// make sure all the files are updatable			
			List readonlyFiles = new ArrayList();
			for (int i = 0; i < paths.length; i++ ) {
				File f = new File(paths[i]);
				if ( f.exists() && !f.canWrite() ) {
					readonlyFiles.add(paths[i]);
				}
			}
			
			if ( readonlyFiles.size() > 0 ) {
				String title = ImportResources.FileModifyChecker_Checkout_failed_title; 
				String msg = ImportResources.FileModifyChecker_Checkout_failed_msg; 
				if ( new MsgDialog(ImportPlugin.getDefault()).displayPrompt(title, msg) ) {
					return checkModify(readonlyFiles);
				}
			}		
			return status;
			
		} catch (Throwable e) {
			e.printStackTrace();
			return new Status(IStatus.ERROR, ImportPlugin.getDefault()
					.getId(), 0, e.getMessage() == null ? "" : e.getMessage(), null); //$NON-NLS-1$
		}
	}
	
	/**
	 * Checks modification status of the given list of files.
	 */
	public static IStatus syncExecCheckModify(final List modifiedFiles) {
		final IStatus[] ret = new IStatus[1];
		SafeUpdateController.syncExec(new Runnable() {
			public void run() {
				ret[0] = FileModifyChecker.checkModify(modifiedFiles);
			}
		});
		return ret[0];
	}
	
	/**
	 * Retruns the modified file list of the given resource set.
	 */
	public static List getModifiedFiles(ResourceSet resourceSet) {
		List ret = new ArrayList();
		for (Iterator it = resourceSet.getResources().iterator(); it.hasNext();) {
			Resource res = (Resource) it.next();
			if (res != null && res.isModified()) {
				ret.add(res.getURI().toFileString());
			}
		}
		return ret;
	}
}