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
package org.eclipse.epf.importing.xml.preferences;

import org.eclipse.epf.importing.xml.ImportXMLPlugin;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * The Export XML preferences.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class ImportXMLPreferences {

	/**
	 * The import XML file preference key.
	 */
	public static final String XML_FILE = "xmlFile"; //$NON-NLS-1$

	/**
	 * The merge option preference key.
	 */
	public static final String MERGE_OPTION = "mergeOption"; //$NON-NLS-1$
	
	/**
	 * The merge level preference key.
	 */
	public static final String MERGE_LEVEL = "mergeLevel"; //$NON-NLS-1$
	
	/**
	 * last import check plugins option preference key
	 */
	public static final String CHECK_BASE_PLUGINS_OPTION = "checkBasePluginsOption"; //$NON-NLS-1$

	static {
		// Initialize the default preference values.
		IPreferenceStore store = ImportXMLPlugin.getDefault()
				.getPreferenceStore();
		store.setDefault(XML_FILE, ""); //$NON-NLS-1$
		store.setDefault(MERGE_OPTION, false);
		store.setDefault(MERGE_LEVEL, 0); 
		store.setDefault(CHECK_BASE_PLUGINS_OPTION, true); 	
	}

	/**
	 * Returns the import XML file preference.
	 * 
	 * @return the import XML file preference
	 */
	public static String getXMLFile() {
		return ImportXMLPlugin.getDefault().getPreferenceStore().getString(
				XML_FILE);
	}

	/**
	 * Sets the import XML file preference.
	 * 
	 * @param path
	 *            the absolute path to a XML file
	 */
	public static void setXMLFile(String path) {
		ImportXMLPlugin.getDefault().getPreferenceStore().setValue(XML_FILE,
				path);
	}

	/**
	 * Returns the merge option file preference.
	 * 
	 * @return the merge option file preference
	 */
	public static boolean getMergeOption() {
		return ImportXMLPlugin.getDefault().getPreferenceStore().getBoolean(
				MERGE_OPTION);
	}

	/**
	 * Sets the merge option file preference.
	 * 
	 * @param mergeOption
	 *            the user specified merge option
	 */
	public static void setMergeOption(boolean mergeOption) {
		ImportXMLPlugin.getDefault().getPreferenceStore().setValue(
				MERGE_OPTION, mergeOption);
	}
	
	/**
	 * Returns the checkBasePlugins option file preference.
	 * 
	 * @return the checkBasePlugins option file preference
	 */
	public static boolean getCheckBasePluginsOption() {
		return ImportXMLPlugin.getDefault().getPreferenceStore().getBoolean(
				CHECK_BASE_PLUGINS_OPTION);
	}

	/**
	 * Sets the checkBasePlugins option file preference.
	 * 
	 * @param b
	 *       the user specified checkBasePlugins option
	 */
	public static void setCheckBasePluginsOption(boolean b) {
		ImportXMLPlugin.getDefault().getPreferenceStore().setValue(
				CHECK_BASE_PLUGINS_OPTION, b);
	}
	
	/**
	 * Returns the merge level file preference.
	 * 
	 * @return the merge level file preference
	 */
	public static int getMergeLevel() {
		return ImportXMLPlugin.getDefault().getPreferenceStore().getInt(
				MERGE_LEVEL);
	}

	/**
	 * Sets the merge level file preference.
	 * 
	 * @param mergeLevel
	 *            the user specified merge level
	 */
	public static void setMergeLevel(int mergeLevel) {
		ImportXMLPlugin.getDefault().getPreferenceStore().setValue(
				MERGE_LEVEL, mergeLevel);
	}

}
