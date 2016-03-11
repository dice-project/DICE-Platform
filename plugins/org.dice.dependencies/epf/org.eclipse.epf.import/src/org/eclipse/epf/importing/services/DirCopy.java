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
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.epf.common.utils.FileUtil;
import org.eclipse.epf.persistence.MultiFileSaveUtil;
import org.eclipse.epf.services.IFileManager;
import org.eclipse.epf.services.Services;


/**
 * utility class to copy files from one dir to another, and delete the un-needed files from source dir.
 *
 * @author Jinhua Xi
 * @since 1.0
 */
public class DirCopy {

	//private IStatus fileCheckedOutStatus = null;
	
	File srcDir;
	File targetDir;
	
	// new files to be added to the target
	List newFiles = new ArrayList();
	
	// files exists in both src and target dir
	List oldFiles = new ArrayList();
	
	// files only in target dir, these files are treated as deleted in the src folder.
	List deletedFiles = new ArrayList();
	
	/**
	 * Creates a new instance.
	 */
	public DirCopy(File srcDir, File targetDir) {
		this.srcDir = srcDir;
		this.targetDir = targetDir;
		
		prepare();
	}
	
	private static String CP_FODLER = MultiFileSaveUtil.CAPABILITY_PATTERN_PATH + "/"; //$NON-NLS-1$
	private static String DP_FODLER = MultiFileSaveUtil.DELIVERY_PROCESS_PATH + "/"; //$NON-NLS-1$
	
	/**
	 * check out the existing files, delete the files from cm, and add new files, copy the files over
	 *
	 */
	public IStatus execute() {
			
		// check out files
		List files = new ArrayList();
		for ( Iterator it = oldFiles.iterator(); it.hasNext(); ) {
			String path = (String)it.next();						
			if (needCopy(new File(srcDir, path), new File(targetDir, path))) {	//154321
				files.add(new File(targetDir, path).getAbsolutePath());
			}
		}
		
		IStatus fileCheckedOutStatus = FileModifyChecker.checkModify(files);
		
		if ( !fileCheckedOutStatus.isOK() ) {
			return fileCheckedOutStatus;
		}
			
		// delete files
		IFileManager fileMgr = Services.getFileManager();
		for ( Iterator it = deletedFiles.iterator(); it.hasNext(); ) {
			String path = (String)it.next();
			File f = new File(targetDir, path);
			fileMgr.delete(f.getAbsolutePath());
			
			// delete the empty CP and DP folder
			path = path.replace(File.separatorChar, '/'); 
			if ( path.startsWith(CP_FODLER) || path.startsWith(DP_FODLER) ) {
				File folder = f.getParentFile();
				String[] items = folder.list();
				if ( items == null || items.length == 0 ) {
					fileMgr.delete(folder.getAbsolutePath());				
				}
			}
		}	
		
		// copy old files
		for ( Iterator it = oldFiles.iterator(); it.hasNext(); ) {
			String path = (String)it.next();
			FileUtil.copyFile(new File(srcDir, path), new File(targetDir, path));
		}
		
		// copy new files
		for ( Iterator it = newFiles.iterator(); it.hasNext(); ) {
			String path = (String)it.next();
			FileUtil.copyFile(new File(srcDir, path), new File(targetDir, path));
		}
		
		return fileCheckedOutStatus;
	}
	
	
	private void prepare() {
		List allSrcFiles = getAllFiles(srcDir);
		List allTargetFiles = getAllFiles(targetDir);

		while ( allTargetFiles.size() > 0 ) {
			String path = (String)allTargetFiles.remove(0);
			if ( allSrcFiles.contains(path) ) {
				oldFiles.add(path);
				allSrcFiles.remove(path);
			} else {
				deletedFiles.add(path);
			}
		}
		
		// the remaining files in the target list is the new files
		newFiles.addAll(allSrcFiles);
	}
	
	/**
	 * get all the files with relative path to the root
	 * @param dir the root dir
	 * @return List all files in the dir, with relative path to the root
	 */
	private List getAllFiles(File dir) {
		List files = new ArrayList();
		FileUtil.getAllFiles(dir, files, true);

		// convert to list of path, not File
		URI baseUrl = dir.toURI();
		
		int i = 0;
		while ( i < files.size() ) {
			File f = (File)files.get(i);
			if ( canIgnore(f.getAbsolutePath()) || canIgnore(f)) {
				files.remove(i);
			} else {
				URI rel = baseUrl.relativize(f.toURI());
				files.set(i, rel.getPath());	
				i++;
			}
		}

		return files;
	}
	
	private static final String CVS_FOLDER = File.separator + "CVS" + File.separator; //$NON-NLS-1$
	private static final String SVN_FOLDER = File.separator + ".svn" + File.separator; //$NON-NLS-1$

	private boolean canIgnore(String path) {
		if ( path.indexOf(CVS_FOLDER) >=0 ) {
			return true;
		}
		if ( path.indexOf(SVN_FOLDER) >=0 ) {
			return true;
		}
		
		return false;
	}
	
	private static final String[] ignoreFiles = {
									".copyarea.dat", //$NON-NLS-1$
									".copyarea.db" //$NON-NLS-1$
									};			
	private boolean canIgnore(File file) {
		String name = file.getName();
		for (int i=0; i<ignoreFiles.length; i++) {
			if (name.equals(ignoreFiles[i])) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Checks to see if the copy action is needed.
	 */
	public static boolean needCopy(File source, File dest) {
		boolean ret = true;
		if (dest.exists()) {
			ret = (dest.lastModified() != source.lastModified())
					|| (dest.length() != source.length());
		}
		return ret;
	}
}