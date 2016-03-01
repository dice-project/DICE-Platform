/*
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial implementation
 *
 */
package org.eclipse.epf.diagram.ad.part;

import org.eclipse.epf.diagram.core.util.DiagramCoreUtil;
import org.eclipse.gmf.runtime.diagram.ui.preferences.DiagramPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * @generated
 */
public class UMLDiagramPreferenceInitializer extends
		DiagramPreferenceInitializer {

	/**
	 * @generated
	 */
	protected IPreferenceStore getPreferenceStore() {
		return org.eclipse.epf.diagram.ad.part.ActivityDiagramEditorPlugin
				.getInstance().getPreferenceStore();
	}
	
	@Override
	public void initializeDefaultPreferences() {
		super.initializeDefaultPreferences();
		
		DiagramCoreUtil.setDefaultFontPreference(getPreferenceStore());
		DiagramCoreUtil.setDefaultLineStyle(getPreferenceStore());
		
		// turn off connection handles to show by default
		DiagramCoreUtil.hideConnectionHandles(getPreferenceStore());
	}
}
