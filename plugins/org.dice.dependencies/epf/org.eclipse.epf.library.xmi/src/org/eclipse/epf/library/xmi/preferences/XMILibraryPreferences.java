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
package org.eclipse.epf.library.xmi.preferences;

import org.eclipse.epf.common.preferences.IPreferenceStoreWrapper;
import org.eclipse.epf.common.utils.FileUtil;
import org.eclipse.epf.library.xmi.XMILibraryPlugin;

/**
 * The XML Method Library preferences.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class XMILibraryPreferences {

	/**
	 * The default library path preference key.
	 */
	public static final String DEFAULT_LIBRARY_PATH = "defaultLibraryPath"; //$NON-NLS-1$

	static {
		// Initialize the default preference values.
		IPreferenceStoreWrapper store = XMILibraryPlugin.getDefault()
				.getPreferenceStore();
		String defaultLibraryPath = System.getProperty("user.home") //$NON-NLS-1$
				// FIXME! Avoid using hard coded constant
				+ FileUtil.FILE_SEP + "EPF" //$NON-NLS-1$
				+ FileUtil.FILE_SEP + "Method Libraries"; //$NON-NLS-1$
		store.setDefault(DEFAULT_LIBRARY_PATH, defaultLibraryPath);
	}

	/**
	 * Gets the default library path preference.
	 */
	public static String getDefaultLibraryPath() {
		return XMILibraryPlugin.getDefault().getPreferenceStore().getString(
				DEFAULT_LIBRARY_PATH);
	}

}
