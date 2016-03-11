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

package org.eclipse.epf.diagram.add.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.epf.diagram.add.part.ActivityDetailDiagramEditorPlugin;
import org.eclipse.epf.diagram.core.util.DiagramCoreUtil;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * @generated
 */
public class DiagramPreferenceInitializer extends AbstractPreferenceInitializer {

	/**
	 * @modified
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = getPreferenceStore();
		DiagramPrintingPreferencePage.initDefaults(store);
		DiagramGeneralPreferencePage.initDefaults(store);
		DiagramAppearancePreferencePage.initDefaults(store);
		DiagramConnectionsPreferencePage.initDefaults(store);
		DiagramRulersAndGridPreferencePage.initDefaults(store);
		DiagramCoreUtil.setDefaultFontPreference(getPreferenceStore());
		DiagramCoreUtil.setDefaultLineStyle(getPreferenceStore());
	}

	/**
	 * @generated
	 */
	protected IPreferenceStore getPreferenceStore() {
		return ActivityDetailDiagramEditorPlugin.getInstance()
				.getPreferenceStore();
	}
}
