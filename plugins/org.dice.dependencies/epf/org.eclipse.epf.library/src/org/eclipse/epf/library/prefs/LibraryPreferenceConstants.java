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
package org.eclipse.epf.library.prefs;

/**
 * @author Bingxue Xu
 * @since 1.0
 */
public interface LibraryPreferenceConstants extends PreferenceConstants {

	public static final String PREF_LAST_LIBRARY_PARENT_DIRECTORY = PREFIX + "last_library__parent_directory"; //$NON-NLS-1$
    public static final String PREF_LAST_DEFAULT_DIRECTORY_CHECKED = PREFIX + "last_default_directory_checked"; //$NON-NLS-1$
    public static final String PREF_RADIO_SAVE_CHOICE = PREFIX + "radio_save_choice"; //$NON-NLS-1$
	
	public static final String PREF_LAST_TRANSLATION_EXPORT_DIRECTORY = PREFIX + "last_translation_export_directory"; //$NON-NLS-1$

    public static final String PREF_LAST_PUBLISH_FOLDER = PREFIX + "last_publish_folder"; //$NON-NLS-1$
	
	public static final String PREF_DEFAULT_PUBLISH_DIRECTORY = PREFIX + "default_publish_directory"; //$NON-NLS-1$
	public static final String PREF_DEFAULT_FEEDBACK_URL = PREFIX + "default_feedback_url"; //$NON-NLS-1$
	
	public static final String PREF_PROMPT_FOR_LIBRARY_AT_STARTUP = PREFIX + "prompt_for_library_at_startup"; //$NON-NLS-1$
	
	public static final String PREF_SELECTED_CONFIG_IN_LAST_SESSION = PREFIX + "selected_config_in_last_session"; //$NON-NLS-1$
	
	//public static final String PREF_DEFAULT_HISTORY_SIZE = PREFIX + "default_list_length_label";
	
}
