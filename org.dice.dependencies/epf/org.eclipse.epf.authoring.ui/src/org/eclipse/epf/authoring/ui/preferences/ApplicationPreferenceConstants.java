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

import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.library.edit.util.LibraryEditConstants;
import org.eclipse.epf.library.prefs.PreferenceConstants;

/**
 * 
 * Preference constants for application
 * 
 * @author Phong Nguyen Le
 * @author Shilpa Toraskar
 * @author Jinhua Xi
 * @since 1.0
 * 
 */
public class ApplicationPreferenceConstants implements PreferenceConstants {
	
	public static final String PREF_ENABLE_PROCESS_CONTRIBUTION = LibraryEditConstants.PREF_ENABLE_PROCESS_CONTRIBUTION;

	public static final String PREF_INHERIT_SUPPRESSION_STATE = LibraryEditConstants.PREF_INHERIT_SUPPRESSION_STATE;
	
	public static final String PREF_SYN_FREE = LibraryEditConstants.PREF_SYN_FREE;
	
	public static final String PREF_LIB_VIEW_LAYOUT = "layout"; //$NON-NLS-1$
	
	public static final String PREF_LIB_VIEW_DND_DEFAULT_LINKTYPE = "libViewDNDDefaultLinkType"; //$NON-NLS-1$

	/**
	 * Saves the URI of the method library that was opened in the last session.
	 */
	public static void setLayout(String value) {
		AuthoringUIPlugin.getDefault().getPreferenceStore().setValue(
				PREF_LIB_VIEW_LAYOUT, value);
	}

	/**
	 * Gets the type of the method library that was opened in the last session.
	 */
	public static String getLayout() {
		return AuthoringUIPlugin.getDefault().getPreferenceStore().getString(
				PREF_LIB_VIEW_LAYOUT);
	}


}
