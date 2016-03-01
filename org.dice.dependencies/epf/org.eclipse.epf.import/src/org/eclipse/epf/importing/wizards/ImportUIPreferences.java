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
package org.eclipse.epf.importing.wizards;

import java.util.List;

import org.eclipse.epf.common.ui.util.PreferenceUtil;
import org.eclipse.epf.common.utils.FileUtil;
import org.eclipse.epf.importing.ImportPlugin;
import org.eclipse.epf.library.ui.preferences.LibraryUIPreferences;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * Manages the Import UI preferences.
 * 
 * @author Kelvin Low
 * @author Bingxue Xu
 * @since 1.2
 */
public class ImportUIPreferences {

	// The import plug-in directories preference key.
	private static final String IMPORT_PLUGIN_DIRS = "importPluginDirs"; //$NON-NLS-1$

	// The import configuration directories preference key.
	private static final String IMPORT_CONFIG_DIRS = "importConfigDirs"; //$NON-NLS-1$

	// The check base plug-ins preference key
	public static final String CHECK_BASE_PLUGINS = "checkBasePlugins"; //$NON-NLS-1$

	// The default import plug-in directory name.
	private static final String DEFAULT_IMPORT_PLUGIN_DIR_NAME = "Export" + FileUtil.FILE_SEP + "Plugins"; //$NON-NLS-1$ //$NON-NLS-2$	 

	// The default import configuration directory name.
	private static final String DEFAULT_IMPORT_CONFIG_DIR_NAME = "Export" + FileUtil.FILE_SEP + "Configurations"; //$NON-NLS-1$ //$NON-NLS-2$	 

	// The default import plug-in directory.
	private static String defaultImportPluginDir = null;

	// The default import configuration directory.
	private static String defaultImportConfigDir = null;

	// The plug-in specific preference store.
	private static IPreferenceStore prefStore = ImportPlugin.getDefault()
			.getPreferenceStore();

	static {
		prefStore.setDefault(IMPORT_PLUGIN_DIRS, getDefaultImportPluginDir());
		prefStore.setDefault(IMPORT_CONFIG_DIRS, getDefaultImportConfigDir());
		prefStore.setDefault(CHECK_BASE_PLUGINS, true);
	}

	/**
	 * Gets the default import plug-in directory.
	 * 
	 * @return the default import plug-in directory
	 */
	public static String getDefaultImportPluginDir() {
		if (defaultImportPluginDir == null) {
			defaultImportPluginDir = System.getProperty("user.home"); //$NON-NLS-1$
			String appName = LibraryUIPreferences.getApplicationShortName();
			if (appName != null && appName.length() > 0) {
				defaultImportPluginDir = defaultImportPluginDir
						+ FileUtil.FILE_SEP + appName + FileUtil.FILE_SEP
						+ DEFAULT_IMPORT_PLUGIN_DIR_NAME;
			} else {
				defaultImportPluginDir = defaultImportPluginDir
						+ FileUtil.FILE_SEP + DEFAULT_IMPORT_PLUGIN_DIR_NAME;
			}
		}
		return defaultImportPluginDir;
	}

	/**
	 * Gets the import plug-in directories preference value.
	 * 
	 * @return the preference value
	 */
	public static String[] getImportPluginDirs() {
		return PreferenceUtil.getStringValues(prefStore, IMPORT_PLUGIN_DIRS);
	}

	/**
	 * Gets the import plug-in directories preference value.
	 * 
	 * @return a collection of import plug-in directories
	 */
	public static List<String> getImportPluginDirsList() {
		return PreferenceUtil.getList(prefStore, IMPORT_PLUGIN_DIRS);
	}

	/**
	 * Adds a import directory to the import plug-in directories preference.
	 * 
	 * @param dir
	 *            an import directory
	 */
	public static void addImportPluginDir(String dir) {
		PreferenceUtil.addToList(prefStore, IMPORT_PLUGIN_DIRS, dir);
	}

	/**
	 * Gets the default import configuration directory.
	 * 
	 * @return the default import configuration directory
	 */
	public static String getDefaultImportConfigDir() {
		if (defaultImportConfigDir == null) {
			defaultImportConfigDir = System.getProperty("user.home"); //$NON-NLS-1$
			String appName = LibraryUIPreferences.getApplicationShortName();
			if (appName != null && appName.length() > 0) {
				defaultImportConfigDir = defaultImportConfigDir
						+ FileUtil.FILE_SEP + appName + FileUtil.FILE_SEP
						+ DEFAULT_IMPORT_CONFIG_DIR_NAME;
			} else {
				defaultImportConfigDir = defaultImportConfigDir
						+ FileUtil.FILE_SEP + DEFAULT_IMPORT_CONFIG_DIR_NAME;
			}
		}
		return defaultImportConfigDir;
	}

	/**
	 * Gets the import configuration directories preference value.
	 * 
	 * @return the preference value
	 */
	public static String[] getImportConfigDirs() {
		return PreferenceUtil.getStringValues(prefStore, IMPORT_CONFIG_DIRS);
	}

	/**
	 * Gets the import configuration directories preference value.
	 * 
	 * @return a collection of import configuration directories
	 */
	public static List<String> getImportConfigDirsList() {
		return PreferenceUtil.getList(prefStore, IMPORT_CONFIG_DIRS);
	}

	/**
	 * Adds a import directory to the import configuration directories
	 * preference.
	 * 
	 * @param dir
	 *            an import directory
	 */
	public static void addImportConfigDir(String dir) {
		PreferenceUtil.addToList(prefStore, IMPORT_CONFIG_DIRS, dir);
	}

	/**
	 * Gets the check base plug-ins preference value.
	 * 
	 * @return the preference value
	 */
	public static boolean getCheckBasePlugins() {
		return prefStore.getBoolean(CHECK_BASE_PLUGINS);
	}

	/**
	 * Sets the notCheckBasePlugins option file preference.
	 * 
	 * @param value
	 *            the preference value
	 */
	public static void setCheckBasePlugins(boolean value) {
		prefStore.setValue(CHECK_BASE_PLUGINS, value);
	}

}
