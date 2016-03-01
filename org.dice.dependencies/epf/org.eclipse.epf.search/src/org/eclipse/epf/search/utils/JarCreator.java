//------------------------------------------------------------------------------
//Copyright (c) 2005, 2007 IBM Corporation and others.
//All rights reserved. This program and the accompanying materials
//are made available under the terms of the Eclipse Public License v1.0
//which accompanies this distribution, and is available at
//http://www.eclipse.org/legal/epl-v10.html
//
//Contributors:
//IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.search.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class JarCreator {

	public static final String INDEX_JAR = "index.jar"; //$NON-NLS-1$

	public static void main(String[] args) {
		jarFolder(args[0]);
	}

	public static void jarFolder(String foldername) {
		try {
			System.out.println("attempting to jar stuff"); //$NON-NLS-1$
			File jarDir = new File(foldername);
			File jarFile = new File(foldername + File.separator + INDEX_JAR);

			jarFiles(jarDir, jarFile);
		} catch (Exception e1) {
			System.out.println("Exception in the jar thingy"); //$NON-NLS-1$
			e1.printStackTrace();
		}
	}

	/**
	 * method to jar the specified dir into a jar file. non-sub-folders will be
	 * jared.
	 * 
	 * @param jarDir
	 * @param jarFile
	 * @throws IOException
	 */
	public static void jarFiles(File jarDir, File jarFile) throws IOException {
		File[] files = jarDir.listFiles();
		if (jarFile.exists()) {
			jarFile.delete();
		}
		BufferedOutputStream bStream = new BufferedOutputStream(new FileOutputStream(
				jarFile));
		ZipOutputStream zipperStream = new ZipOutputStream(bStream);

		byte[] bytes = new byte[4096];
		for (int i = 0; i < files.length; i++) {
			File currentFile = files[i];
			if (currentFile.isDirectory()) {
				continue;
			}
			ZipEntry currEntry = new ZipEntry(currentFile.getName());
			zipperStream.putNextEntry(currEntry);
			BufferedInputStream biStream = new BufferedInputStream(new FileInputStream(
					currentFile));
			while (biStream.available() > 0) {
				int num = biStream.read(bytes);
				zipperStream.write(bytes, 0, num);
			}
			biStream.close();
			zipperStream.closeEntry();
		}
		zipperStream.close();
		bStream.close();
	}	
}
