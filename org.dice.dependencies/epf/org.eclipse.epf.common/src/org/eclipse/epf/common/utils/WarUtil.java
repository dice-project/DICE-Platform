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
package org.eclipse.epf.common.utils;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.runtime.IProgressMonitor;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.War;
import org.apache.tools.ant.types.FileSet;


public class WarUtil {

	/**
	 * Creates a war file.
	 * 
	 * @param outputWarFile
	 *            an absolute path to the target war file
	 * @param webAppDirectory
	 *            an absolute path to a directory containing the files and sub
	 *            directories to be wared
	 * @param monitor
	 *            a progress monitor
	 * @throws IOException
	 *             if an error occurs while creating the zip file
	 */
	public static void buildWarFile(String outputWarFile, String webAppDirectory,
			IProgressMonitor monitor) throws IOException {

		try {
			Project antProject = new Project();
			antProject.init();
			War warTask = new War();
			warTask.setProject(antProject);
			warTask.setDestFile(new File(outputWarFile));
			warTask.setWebxml(new File(webAppDirectory + File.separator + "WEB-INF" //$NON-NLS-1$
					+ File.separator + "web.xml")); //$NON-NLS-1$
			FileSet webFiles = new FileSet();
			webFiles.setDir(new File(webAppDirectory));
			warTask.addFileset(webFiles);
			warTask.execute();
		} catch (Exception e) {
			throw new IOException(e.getMessage());
		}
	}
}