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
package org.eclipse.epf.search.ui.preferences;

import org.eclipse.epf.search.ui.SearchUIPlugin;

/**
 * Manages the Search UI preferences.
 * 
 * @author Kelvin Low
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class SearchUIPreferences {

	/**
	 * The Search UI preference keys.
	 */
	public static final String SEARCH_STRING = "searchString"; //$NON-NLS-1$

	public static final String NAME_PATTERN = "namePattern"; //$NON-NLS-1$

	public static final String CASE_SENSITIVE = "caseSensitive"; //$NON-NLS-1$	

	/**
	 * Returns the saved search string.
	 */
	public static String getSearchString() {
		return SearchUIPlugin.getDefault().getPreferenceStore().getString(
				SEARCH_STRING);
	}

	/**
	 * Saves the current search string.
	 */
	public static void setSearchString(String searchString) {
		SearchUIPlugin.getDefault().getPreferenceStore().setValue(
				SEARCH_STRING, searchString);
	}

	/**
	 * Returns the saved name pattern string.
	 */
	public static String getNamePattern() {
		return SearchUIPlugin.getDefault().getPreferenceStore().getString(
				NAME_PATTERN);
	}

	/**
	 * Saves the current name pattern string.
	 */
	public static void setNamePattern(String namePattern) {
		SearchUIPlugin.getDefault().getPreferenceStore().setValue(NAME_PATTERN,
				namePattern);
	}

	/**
	 * Returns the saved ase sensitive option.
	 */
	public static boolean getCaseSensitive() {
		return SearchUIPlugin.getDefault().getPreferenceStore().getBoolean(
				CASE_SENSITIVE);
	}

	/**
	 * Saves the current case sensitive option.
	 */
	public static void setCaseSensitive(boolean caseSensitive) {
		SearchUIPlugin.getDefault().getPreferenceStore().setValue(
				CASE_SENSITIVE, caseSensitive);
	}
}