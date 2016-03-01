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

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.editors.ProcessEditor;
import org.eclipse.jface.preference.IPreferenceStore;


/**
 * PreferenceInitializer for AuthoringPlugin.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class AuthoringPreferenceInitializer extends
		AbstractPreferenceInitializer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = AuthoringUIPlugin.getDefault()
				.getPreferenceStore();

		// We don't need it here. It's been moved to libraryPlugin
//		store.setDefault(ApplicationPreferenceConstants.PREF_WBS_COLUMNS,
//				ProcessEditorPreferencePage
//						.toString(ProcessEditor.DEFAULT_WBS_COLUMNS));
//		store.setDefault(ApplicationPreferenceConstants.PREF_TBS_COLUMNS,
//				ProcessEditorPreferencePage
//						.toString(ProcessEditor.DEFAULT_TBS_COLUMNS));
//		store.setDefault(ApplicationPreferenceConstants.PREF_WPBS_COLUMNS,
//				ProcessEditorPreferencePage
//						.toString(ProcessEditor.DEFAULT_WPBS_COLUMNS));
//		store.setDefault(
//				ApplicationPreferenceConstants.PREF_INHERIT_SUPPRESSION_STATE,
//				true);
	}

}
