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
package org.eclipse.epf.library.layout;

import java.io.File;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Copy;
import org.apache.tools.ant.taskdefs.Expand;
import org.apache.tools.ant.types.FileSet;
import org.eclipse.epf.common.utils.FileUtil;
import org.eclipse.epf.library.LibraryPlugin;
import org.eclipse.epf.publish.layout.LayoutPlugin;

import com.ibm.icu.util.Calendar;


/**
 * a utility class to handle layout related resources.
 * 
 * @author Jinhua Xi
 * @since 1.0
 */
public class LayoutResources {

	private static ResourceBundle resourceBundle;

	private static ResourceBundle shapeiconBundle;

	private static String TEMP_LAYOUT_DIR = ""; //$NON-NLS-1$
	static {
		try {
			String pkg = LayoutResources.class.getPackage().getName();
			String resource = LayoutResources.class.getName();
			resourceBundle = ResourceBundle.getBundle(resource);
			shapeiconBundle = ResourceBundle.getBundle(pkg
					+ ".DefaultShapeicons"); //$NON-NLS-1$

			/*
			 * TEMP_LAYOUT_DIR = getString("temp_layout_dir"); if (
			 * TEMP_LAYOUT_DIR != null ) { TEMP_LAYOUT_DIR =
			 * TEMP_LAYOUT_DIR.replace('/', File.separatorChar); }
			 */

			// Multiple instances of EPF running different libs are sharing the same layout directory
			// use timestamp to identify each instance
			// we need to clean the files when the application exit			
			String userHome = System.getProperty("user.home"); //$NON-NLS-1$
			TEMP_LAYOUT_DIR = userHome + File.separator
					+ "EPF" + File.separator + "layout" + File.separator //$NON-NLS-1$ //$NON-NLS-2$
					+ Long.toHexString(Calendar.getInstance().getTimeInMillis()) + File.separator;
			
			File rupTmpDir = new File(TEMP_LAYOUT_DIR);
			if (!rupTmpDir.exists()) {
				rupTmpDir.mkdirs();
			} else {
				FileUtil.deleteAllFiles(rupTmpDir.getAbsolutePath());
			}

		} catch (MissingResourceException x) {
			x.printStackTrace();
			resourceBundle = null;
		}
	}

	/**
	 * call this when the application exit
	 */
	public static void clear()
	{
		try {
			FileUtil.deleteAllFiles(TEMP_LAYOUT_DIR);
			
			// also delete the folder
			new File(TEMP_LAYOUT_DIR).delete();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// also reset the browser settings so that the layout data will be copied
		BrowsingLayoutSettings.INSTANCE.setChanged();
	}
	
	/**
	 * get the default publish path
	 */
	public static String getDefaultPublishDir() {
		return TEMP_LAYOUT_DIR;
	}

	/**
	 * get the xsl uri for the given keys
	 * @param key String
	 * @param secondKey String
	 * @return String
	 */
	public static String getDefaultXslPath(String key, String secondKey) {
		String url = getXslUri(key, secondKey);

		if (url != null) {
			// resolve the file
			return LayoutPlugin.getDefault().getLayoutXslPath() + url;
		}

		return ""; //$NON-NLS-1$
	}

	/**
	 * get the xsl uri for the given keys
	 * @param key String
	 * @param secondKey String
	 * @return String
	 */
	public static String getXslUri(String key, String secondKey) {
		String url = getString(resourceBundle, key.toLowerCase());
		if ((url == null) && (secondKey != null)) {
			url = getString(resourceBundle, secondKey.toLowerCase());
		}

		if (url == null) {
			url = getString(resourceBundle, "default"); //$NON-NLS-1$
		}


		return url; 
	}
	
	/**
	 * get the default shape icon url
	 * @param key String
	 * @return String
	 */
	public static String getDefaultShapeiconUrl(String key) {
		String url = getString(shapeiconBundle, key);
		if (url == null) {
			url = getString(shapeiconBundle, "general"); //$NON-NLS-1$
		}

		return url;
	}

	private static String getString(ResourceBundle bundle, String key) {
		try {
			return (bundle != null) ? bundle.getString(key) : null;
		} catch (MissingResourceException e) {
			return null;
		}
	}

	/**
	 * get resource string for the key
	 * @param key
	 * @return String
	 */
	public static String getString(String key) {
		return getString(resourceBundle, key);
	}

	/**
	 * copy files from one dir to another
	 * @param fromDir String
	 * @param toDir String
	 */
	public static void copyDir(String fromDir, String toDir) {

		String includes = "*.*, **/css/*.*, **/icons/*.*, **/images/*.*, **/resources/*.*, **/stylesheets/*.*, **/scripts/*.*"; //$NON-NLS-1$
		copyDir(fromDir, toDir, includes, null);
	}

	/**
	 * copy files from one dir to another, with filters
	 * @param fromDir String
	 * @param toDir String
	 * @param includes String
	 * @param excludes String
	 */
	public static void copyDir(String fromDir, String toDir, String includes,
			String excludes) {
		copyDir(new File(fromDir), new File(toDir), includes, excludes);
	}

	/**
	 * copy dir
	 * 
	 * @param fromDir File
	 * @param toDir File
	 * @param includes String
	 * @param excludes String
	 */
	public static void copyDir(File fromDir, File toDir, String includes,
			String excludes)
	{
		copyDir(fromDir, toDir, includes, excludes, true);
	}
	
	/**
	 * copy dir
	 * @param fromDir File
	 * @param toDir File
	 * @param includes String
	 * @param excludes String
	 * @param overwrite boolean true to override existing files
	 */
	public static void copyDir(File fromDir, File toDir, String includes,
			String excludes, boolean overwrite) {
		Copy cp = new Copy();
		cp.setOverwrite(overwrite); // only cpoy new files or newer files
		if (includes != null || excludes != null) {
			FileSet set = new FileSet();
			if (includes != null) {
				set.setIncludes(includes);
			}

			if (excludes != null) {
				set.setExcludes(excludes);
			}

			set.setDir(fromDir);
			cp.addFileset(set);
		}

		cp.setTodir(toDir);
		cp.setProject(new Project());
		cp.execute();

	}

	/**
	 * copy the layout files from the library layout path to the specified
	 * publish folder
	 * 
	 * @param toDir
	 */
	public static void copyLayoutFiles(String toDir) {
		// copy the layout files to the temp folder
		String sourceDir = LayoutPlugin.getDefault().getLayoutPath();
		copyDir(sourceDir, toDir);

		try {
			LibraryPlugin.getDefault().copyLocalizedFiles(
					LayoutPlugin.LAYOUT_SCRIPTS_PATH,
					new File(toDir, LayoutPlugin.LAYOUT_SCRIPTS_FOLDER), true,
					false);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void unzip(File zipFile, File toDir) {
		Expand exp = new Expand();
		exp.setOverwrite(true); 
		exp.setSrc(zipFile);
		exp.setDest(toDir);
		exp.setProject(new Project());
		exp.execute();

	}
}
