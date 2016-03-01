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
package org.eclipse.epf.authoring.ui.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.epf.authoring.ui.editors.ProcessEditor;
import org.eclipse.epf.common.preferences.IPreferenceStoreWrapper;
import org.eclipse.epf.library.LibraryPlugin;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.prefs.LibraryPreferenceConstants;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * Initializer for library preferences
 * 
 * @author BingXue Xu
 * @author Shilpa Toraskar
 * @since 1.0
 */
public class LibraryPreferenceInitializer extends AbstractPreferenceInitializer {

	/**
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStoreWrapper store = LibraryPlugin.getDefault()
				.getPreferenceStore();
		store.setDefault(LibraryPreferenceConstants.PREF_RADIO_SAVE_CHOICE, 1);
		
		store.setDefault(ApplicationPreferenceConstants.PREF_WBS_COLUMNS,
				ProcessEditorPreferencePage
						.toString(ProcessEditor.DEFAULT_WBS_COLUMNS));
		store.setDefault(ApplicationPreferenceConstants.PREF_TBS_COLUMNS,
				ProcessEditorPreferencePage
						.toString(ProcessEditor.DEFAULT_TBS_COLUMNS));
		store.setDefault(ApplicationPreferenceConstants.PREF_WPBS_COLUMNS,
				ProcessEditorPreferencePage
						.toString(ProcessEditor.DEFAULT_WPBS_COLUMNS));
		store.setDefault(
				ApplicationPreferenceConstants.PREF_INHERIT_SUPPRESSION_STATE,
				true);
		store.setDefault(
				ApplicationPreferenceConstants.PREF_SYN_FREE,
				false);
		
	}

}
