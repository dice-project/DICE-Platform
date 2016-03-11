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
package org.eclipse.epf.export.wizards;

import java.util.List;

import org.eclipse.epf.common.ui.util.PreferenceUtil;
import org.eclipse.epf.common.utils.FileUtil;
import org.eclipse.epf.export.ExportPlugin;
import org.eclipse.epf.library.ui.preferences.LibraryUIPreferences;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * Manages the Export UI preferences.
 * 
 * @author Kelvin Low
 * @since 1.2
 */
public class ExportUIPreferences {

	// The export plug-in directories preference key.
	private static final String EXPORT_PLUGIN_DIRS = "exportPluginDirs"; //$NON-NLS-1$

	// The export configuration directories preference key.
	private static final String EXPORT_CONFIG_DIRS = "exportConfigDirs"; //$NON-NLS-1$

	// The default export plug-in directory name.
	private static final String DEFAULT_EXPORT_PLUGIN_DIR_NAME = "Export" + FileUtil.FILE_SEP + "Plugins"; //$NON-NLS-1$ //$NON-NLS-2$	 

	// The default export configuration directory name.
	private static final String DEFAULT_EXPORT_CONFIG_DIR_NAME = "Export" + FileUtil.FILE_SEP + "Configurations"; //$NON-NLS-1$ //$NON-NLS-2$	 

	// The default export plug-in directory.
	private static String defaultExportPluginDir = null;

	// The default export configuration directory.
	private static String defaultExportConfigDir = null;

	// The plug-in specific preference store.
	private static IPreferenceStore prefStore = ExportPlugin.getDefault()
			.getPreferenceStore();

	static {
		prefStore.setDefault(EXPORT_PLUGIN_DIRS, getDefaultExportPluginDir());
		prefStore.setDefault(EXPORT_CONFIG_DIRS, getDefaultExportConfigDir());
	}

	/**
	 * Gets the default export plug-in directory.
	 * 
	 * @return the default export plug-in directory
	 */
	public static String getDefaultExportPluginDir() {
		if (defaultExportPluginDir == null) {
			defaultExportPluginDir = System.getProperty("user.home"); //$NON-NLS-1$
			String appName = LibraryUIPreferences.getApplicationShortName();
			if (appName != null && appName.length() > 0) {
				defaultExportPluginDir = defaultExportPluginDir
						+ FileUtil.FILE_SEP + appName + FileUtil.FILE_SEP
						+ DEFAULT_EXPORT_PLUGIN_DIR_NAME;
			} else {
				defaultExportPluginDir = defaultExportPluginDir
						+ FileUtil.FILE_SEP + DEFAULT_EXPORT_PLUGIN_DIR_NAME;
			}
		}
		return defaultExportPluginDir;
	}

	/**
	 * Gets the export plug-in directories preference value.
	 * 
	 * @return the preference value
	 */
	public static String[] getExportPluginDirs() {
		return PreferenceUtil.getStringValues(prefStore, EXPORT_PLUGIN_DIRS);
	}

	/**
	 * Gets the export plug-in directories preference value.
	 * 
	 * @return a collection of export plug-in directories
	 */
	public static List<String> getExportPluginDirsList() {
		return PreferenceUtil.getList(prefStore, EXPORT_PLUGIN_DIRS);
	}

	/**
	 * Adds a export directory to the export plug-in directories preference.
	 * 
	 * @param dir
	 *            an export directory
	 */
	public static void addExportPluginDir(String dir) {
		PreferenceUtil.addToList(prefStore, EXPORT_PLUGIN_DIRS, dir);
	}

	/**
	 * Gets the default export configuration directory.
	 * 
	 * @return the default export configuration directory
	 */
	public static String getDefaultExportConfigDir() {
		if (defaultExportConfigDir == null) {
			defaultExportConfigDir = System.getProperty("user.home"); //$NON-NLS-1$
			String appName = LibraryUIPreferences.getApplicationShortName();
			if (appName != null && appName.length() > 0) {
				defaultExportConfigDir = defaultExportConfigDir
						+ FileUtil.FILE_SEP + appName + FileUtil.FILE_SEP
						+ DEFAULT_EXPORT_CONFIG_DIR_NAME;
			} else {
				defaultExportConfigDir = defaultExportConfigDir
						+ FileUtil.FILE_SEP + DEFAULT_EXPORT_CONFIG_DIR_NAME;
			}
		}
		return defaultExportConfigDir;
	}

	/**
	 * Gets the export configuration directories preference value.
	 * 
	 * @return the preference value
	 */
	public static String[] getExportConfigDirs() {
		return PreferenceUtil.getStringValues(prefStore, EXPORT_CONFIG_DIRS);
	}

	/**
	 * Gets the export configuration directories preference value.
	 * 
	 * @return a collection of export configuration directories
	 */
	public static List<String> getExportConfigDirsList() {
		return PreferenceUtil.getList(prefStore, EXPORT_CONFIG_DIRS);
	}

	/**
	 * Adds a export directory to the export configuration directories
	 * preference.
	 * 
	 * @param dir
	 *            an export directory
	 */
	public static void addExportConfigDir(String dir) {
		PreferenceUtil.addToList(prefStore, EXPORT_CONFIG_DIRS, dir);
	}

}
