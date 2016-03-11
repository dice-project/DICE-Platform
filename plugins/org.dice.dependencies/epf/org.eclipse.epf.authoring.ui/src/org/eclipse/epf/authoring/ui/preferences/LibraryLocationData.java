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
package org.eclipse.epf.authoring.ui.preferences;

import java.io.File;

import org.eclipse.epf.common.preferences.IPreferenceStoreWrapper;
import org.eclipse.epf.common.ui.CommonUIPlugin;
import org.eclipse.epf.library.LibraryPlugin;
import org.eclipse.epf.library.prefs.LibraryPreferenceConstants;
import org.eclipse.epf.library.ui.preferences.LibraryUIPreferences;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * Location Data for library 
 * 
 * @author BingXue Xu
 * @since 1.0
 * fix for https://bugs.eclipse.org/bugs/show_bug.cgi?id=157401
 */
public class LibraryLocationData {

	private String parentFolder;

	private String libName;

	private String libBriefDescription;

	private boolean isSingleLibFile;

	private boolean defLibDirChecked;

	protected static final String libraryFile = "library.xmi"; //$NON-NLS-1$

	/**
	 * @return Returns the isOkPressed.
	 */
	public boolean isOkPressed() {
		return isOkPressed;
	}

	/**
	 * @param isOkPressed
	 *            The isOkPressed to set.
	 */
	public void setOkPressed(boolean isOkPressed) {
		this.isOkPressed = isOkPressed;
	}

	private boolean isOkPressed;

	/**
	 * @return Returns the isSingleLibFile.
	 */
	public boolean isSingleLibFile() {
		return isSingleLibFile;
	}

	/**
	 * @param isSingleLibFile
	 *            The isSingleLibFile to set.
	 */
	public void setSingleLibFile(boolean isSingleLibFile) {
		this.isSingleLibFile = isSingleLibFile;
	}

	/**
	 * @return Returns the libName.
	 */
	public String getLibName() {
		return libName;
	}

	/**
	 * 
	 * @return Full path of library.xmi
	 */
	public String getLibraryFile() {
		return getParentFolder() + File.separator + libraryFile;
	}

	/**
	 * @param libName
	 *            The libName to set.
	 */
	public void setLibName(String libName) {
		this.libName = libName;
	}

	/**
	 * @return Returns the parentFolder.
	 */
	public String getParentFolder() {
		return parentFolder;
	}

	/**
	 * @param parentFolder
	 *            The parentFolder to set.
	 */
	public void setParentFolder(String parentFolder) {
		this.parentFolder = parentFolder;
	}

	/**
	 * @return Returns the libBriefDescription.
	 */
	public String getLibBriefDescription() {
		return libBriefDescription;
	}

	/**
	 * @param libBriefDescription
	 *            The libBriefDescription to set.
	 */
	public void setLibBriefDescription(String libBriefDescription) {
		this.libBriefDescription = libBriefDescription;
	}

	/**
	 * @return Returns the defLibDirChecked.
	 */
	public boolean isDefLibDirChecked() {
		return defLibDirChecked;
	}

	/**
	 * @param defLibDirChecked
	 *            The defLibDirChecked to set.
	 */
	public void setDefLibDirChecked(boolean defLibDirChecked) {
		this.defLibDirChecked = defLibDirChecked;
	}

	/**
	 * Loads preferences from the store
	 *
	 */
	public void loadFromPreferenceStore() {
		IPreferenceStoreWrapper store = LibraryPlugin.getDefault()
				.getPreferenceStore();

		String lastDir = store
				.getString(LibraryPreferenceConstants.PREF_LAST_LIBRARY_PARENT_DIRECTORY);
		this.setParentFolder(lastDir);

		boolean lastChecked = store
				.getBoolean(LibraryPreferenceConstants.PREF_LAST_DEFAULT_DIRECTORY_CHECKED);
		// the contains method does not seem to work so use last dir as an
		// approximation if
		// (store.contains(LibaryPreferenceConstants.PREF_LAST_DEFAULT_DIRECTORY_CHECKED))
		if (lastDir == null || lastDir.trim().length() == 0)
			this.setDefLibDirChecked(true);
		else
			this.setDefLibDirChecked(lastChecked);
	}

	/**
	 * Saves dialog choices to the preference store
	 *
	 */
	public void saveToPreferenceStore() {
		IPreferenceStoreWrapper store = LibraryPlugin.getDefault()
				.getPreferenceStore();
		File file = new File(this.getParentFolder());
		LibraryUIPreferences.setSavedLibraryPath(file.getAbsolutePath());
		store.setValue(
				LibraryPreferenceConstants.PREF_LAST_LIBRARY_PARENT_DIRECTORY,
				file.getParent());
		store.setValue(
				LibraryPreferenceConstants.PREF_LAST_DEFAULT_DIRECTORY_CHECKED,
				this.isDefLibDirChecked());

		LibraryPlugin.getDefault().savePluginPreferences();
	}

}
