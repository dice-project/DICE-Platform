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
package org.eclipse.epf.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.eclipse.core.runtime.IProgressMonitor;

/**
 * Utility class for reading and writing zip files.
 * 
 * @author Kelvin Low
 * @since 1.2
 */
public class ZipUtil {

	/**
	 * Creates a zip file.
	 * 
	 * @param zipFilePath
	 *            an absolute path to the target zip file
	 * @param contentPath
	 *            an absolute path to a directory containing the files and sub
	 *            directories to be zipped
	 * @param monitor
	 *            a progress monitor
	 * @throws IOException
	 *             if an error occurs while creating the zip file
	 */
	public static void createZipFile(String zipFilePath, String contentPath,
			IProgressMonitor monitor) throws IOException {
		ZipOutputStream out = null;
		try {
			out = new ZipOutputStream(new FileOutputStream(
					zipFilePath));
			File contentDir = new File(contentPath);
			populateZipFile(out, contentDir.getAbsolutePath(), contentDir,
					monitor);
		} catch (IOException e) {
			throw e;
		} catch (Exception e) {
			throw new IOException(e.getMessage());
		}
		finally {
			if (out != null) {
				try {
					out.close();
				}
				catch (Exception e) {
				}
			}
		}
	}

	/**
	 * Populates a zip file with directories and files.
	 * 
	 * @param out
	 *            a zip file output stream
	 * @param rootPath
	 *            a root path that is used to compute the relative paths of the
	 *            zip content
	 * @param file
	 *            a file or directory
	 * @param monitor
	 *            a progress monitor
	 */
	public static void populateZipFile(ZipOutputStream out, String rootPath,
			File file, IProgressMonitor monitor) throws IOException {
		File[] files = file.listFiles();
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					populateZipFile(out, rootPath, files[i], monitor);
				} else {
					String filePath = files[i].getAbsolutePath();
					FileInputStream in = new FileInputStream(filePath);
					String relativePath = filePath
							.substring(rootPath.length() + 1);
					out.putNextEntry(new ZipEntry(relativePath));
					byte[] buffer = new byte[1024];
					int len;
					while ((len = in.read(buffer)) > 0) {
						out.write(buffer, 0, len);
					}
					out.closeEntry();
					in.close();
				}
			}
		}
	}

}
