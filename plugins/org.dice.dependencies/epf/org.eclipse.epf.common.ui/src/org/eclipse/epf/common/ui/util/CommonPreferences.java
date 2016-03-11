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
package org.eclipse.epf.common.ui.util;

import org.eclipse.epf.common.ui.CommonUIPlugin;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * Manages the common preferences.
 * 
 * @author Kelvin Low
 * @since 1.2
 */
public class CommonPreferences {

	/**
	 * Preference key for storing the preference history size.
	 */
	private static final String PREFERENCE_HISTORY_SIZE = "preferenceHistorySize"; //$NON-NLS-1$

	// The plug-in specific preference store.
	private static IPreferenceStore prefStore = CommonUIPlugin.getDefault()
			.getPreferenceStore();

	static {
		// Initialize the default preference values.
		prefStore.setDefault(PREFERENCE_HISTORY_SIZE,
				getDefaultPreferenceHistorySize());
	}

	/**
	 * Gets the default preference history size.
	 */
	public static int getDefaultPreferenceHistorySize() {
		return 10;
	}

	/**
	 * Gets the preference history size.
	 */
	public static int getPreferenceHistorySize() {
		return prefStore.getInt(PREFERENCE_HISTORY_SIZE);
	}

	/**
	 * Saves the preference history size.
	 */
	public static void setPreferenceHistorySize(int value) {
		prefStore.setValue(PREFERENCE_HISTORY_SIZE, value);
	}

}
