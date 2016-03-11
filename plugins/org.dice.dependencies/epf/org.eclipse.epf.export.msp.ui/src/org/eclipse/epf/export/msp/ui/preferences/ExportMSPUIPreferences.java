/*******************************************************************************
 * Copyright (c) 2005, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial implementation
 *******************************************************************************/
package org.eclipse.epf.export.msp.ui.preferences;

import java.util.List;

import org.eclipse.epf.common.ui.util.PreferenceUtil;
import org.eclipse.epf.common.utils.FileUtil;
import org.eclipse.epf.export.msp.ui.ExportMSPUIPlugin;
import org.eclipse.epf.library.ui.preferences.LibraryUIPreferences;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * The Export Microsoft Project UI preferences.
 * 
 * @author Kelvin Low
 * @since 1.2
 */
public class ExportMSPUIPreferences {

	// The publish web site preference key.
	private static final String PUBLISH_WEBSITE = "publishWebSite"; //$NON-NLS-1$

	// The publish configuration preference key.
	private static final String PUBLISH_CONFIGURATION = "publishConfiguration"; //$NON-NLS-1$

	// The export only planned WBS elements preference key.
	private static final String EXPORT_ONLY_PLANNED_WBS_ELEMENTS = "exportOnlyPlannedWBSElements"; //$NON-NLS-1$

	// The process name preference key.
	private static final String PROCESS_NAME = "processName"; //$NON-NLS-1$

	// The method configuration namr preference key.
	private static final String CONFIGURATION_NAME = "configurationName"; //$NON-NLS-1$

	// The Microsoft Project names preference key.
	private static final String MS_PROJECT_NAMES = "msprojectNames"; //$NON-NLS-1$

	// The export directories preference key.
	private static final String EXPORT_DIRECTORIES = "exportDirs"; //$NON-NLS-1$

	// The default export MSP directory name.
	private static final String DEFAULT_EXPORT_DIR_NAME = "Export" + FileUtil.FILE_SEP + "MSP"; //$NON-NLS-1$ //$NON-NLS-2$	 
	
	// The plug-in specific preference store.
	private static IPreferenceStore prefStore = ExportMSPUIPlugin.getDefault()
			.getPreferenceStore();

	// The default export MSP directory.
	private static String defaultExportDir = null;
	
	static {
		prefStore.setDefault(PUBLISH_WEBSITE, true);
		prefStore.setDefault(PUBLISH_CONFIGURATION, true);
		prefStore.setDefault(EXPORT_ONLY_PLANNED_WBS_ELEMENTS, false);
		prefStore.setDefault(CONFIGURATION_NAME, ""); //$NON-NLS-1$
		prefStore.setDefault(PROCESS_NAME, ""); //$NON-NLS-1$
		prefStore.setDefault(MS_PROJECT_NAMES, ""); //$NON-NLS-1$
		prefStore.setDefault(EXPORT_DIRECTORIES, getDefaultExportDir());
	}

	/**
	 * Gets the publish web site preference value.
	 * 
	 * @return <code>true</code> is the preference is set.
	 */
	public static boolean getPublishWebSite() {
		return prefStore.getBoolean(PUBLISH_WEBSITE);
	}

	/**
	 * Sets the publish web site preference value.
	 * 
	 * @param value
	 *            the preference value
	 */
	public static void setPublishWebSite(boolean value) {
		prefStore.setValue(PUBLISH_WEBSITE, value);
	}

	/**
	 * Gets the publish configuration preference value.
	 * 
	 * @return <code>true</code> is the preference is set
	 */
	public static boolean getPublishConfiguration() {
		return prefStore.getBoolean(PUBLISH_CONFIGURATION);
	}

	/**
	 * Sets the publish configuration preference value.
	 * 
	 * @param value
	 *            the preference value
	 */
	public static void setPublishConfiguration(boolean value) {
		prefStore.setValue(PUBLISH_CONFIGURATION, value);
	}

	/**
	 * Gets the export only planned WBS elements preference value.
	 * 
	 * @return <code>true</code> is the preference is set
	 */
	public static boolean getExportOnlyPlannedWBSElements() {
		return prefStore.getBoolean(EXPORT_ONLY_PLANNED_WBS_ELEMENTS);
	}

	/**
	 * Sets the export only planned WBS elements preference value.
	 * 
	 * @param value
	 *            the preference value
	 */
	public static void setExportOnlyPlannedWBSElements(boolean value) {
		prefStore.setValue(EXPORT_ONLY_PLANNED_WBS_ELEMENTS, value);
	}

	/**
	 * Gets the process name preference value.
	 * 
	 * @return the preference value
	 */
	public static String getProcessName() {
		return prefStore.getString(PROCESS_NAME);
	}

	/**
	 * Sets the process name preference value.
	 * 
	 * @param value
	 *            the preference value
	 */
	public static void setProcessName(String value) {
		prefStore.setValue(PROCESS_NAME, value);
	}

	/**
	 * Gets the method configuration name preference value.
	 * 
	 * @return the preference value
	 */
	public static String getConfigurationName() {
		return prefStore.getString(CONFIGURATION_NAME);
	}

	/**
	 * Sets the method configuration name preference value.
	 * 
	 * @param value
	 *            the preference value
	 */
	public static void setConfigurationName(String value) {
		prefStore.setValue(CONFIGURATION_NAME, value);
	}

	/**
	 * Gets the Microsoft Project names preference value.
	 * 
	 * @return the preference value
	 */
	public static String[] getMSProjectNames() {
		return PreferenceUtil.getStringValues(prefStore, MS_PROJECT_NAMES);
	}

	/**
	 * Gets the Microsoft Project names preference value.
	 * 
	 * @return a collection of Microsoft Project names
	 */
	public static List<String> getMSProjectNamesList() {
		return PreferenceUtil.getList(prefStore, MS_PROJECT_NAMES);
	}

	/**
	 * Adds a Microsoft Project name to the Microsoft Project names preference.
	 * 
	 * @param name
	 *            a template name
	 */
	public static void addMSProjectName(String name) {
		PreferenceUtil.addToList(prefStore, MS_PROJECT_NAMES, name);
	}

	/**
	 * Gets the default export MSP directory.
	 * 
	 * @return the default export MSP directory
	 */
	public static String getDefaultExportDir() {
		if (defaultExportDir == null) {
			defaultExportDir = System.getProperty("user.home"); //$NON-NLS-1$
			String appName = LibraryUIPreferences.getApplicationShortName();
			if (appName != null && appName.length() > 0) {
				defaultExportDir = defaultExportDir + FileUtil.FILE_SEP
						+ appName + FileUtil.FILE_SEP + DEFAULT_EXPORT_DIR_NAME;
			} else {
				defaultExportDir = defaultExportDir + FileUtil.FILE_SEP
						+ DEFAULT_EXPORT_DIR_NAME;
			}
		}
		return defaultExportDir;
	}

	/**
	 * Gets the export directories preference value.
	 * 
	 * @return the preference value
	 */
	public static String[] getExportDirectories() {
		return PreferenceUtil.getStringValues(prefStore, EXPORT_DIRECTORIES);
	}

	/**
	 * Gets the export directories preference value.
	 * 
	 * @return a collection of export directories
	 */
	public static List<String> getExportDirectoriesList() {
		return PreferenceUtil.getList(prefStore, EXPORT_DIRECTORIES);
	}

	/**
	 * Adds a export directory to the export directories preference.
	 * 
	 * @param dir
	 *            an export directory
	 */
	public static void addExportDir(String dir) {
		PreferenceUtil.addToList(prefStore, EXPORT_DIRECTORIES, dir);
	}

}
