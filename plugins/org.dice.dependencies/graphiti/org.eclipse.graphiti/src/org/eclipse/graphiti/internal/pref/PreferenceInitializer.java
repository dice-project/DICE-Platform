/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2010 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.internal.pref;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.graphiti.internal.GraphitiPlugin;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#
	 * initializeDefaultPreferences()
	 */
	@Override
	public void initializeDefaultPreferences() {
		IEclipsePreferences node = new DefaultScope().getNode(GraphitiPlugin.PLUGIN_ID);
		node.putBoolean(GFPreferences.CPU_PROFILING_TRACE_ACTIVE, false);
		node.putBoolean(GFPreferences.RECURSIVE_CHECK_FOR_UPDATE_ACTIVE, false);
		node.putBoolean(GFPreferences.INVISIBLE_RECTANGLES_SHOWN, false);
		node.putInt(GFPreferences.VISUAL_STATE_RENDERING, 0);
		node.putBoolean(GFPreferences.PROGRESS_DIALOG_ACTIVE, false);
		node.putInt(GFPreferences.CONTEXT_BUTTON_PAD_DECLARATION, 0);
		node.putInt(GFPreferences.ZOOM_ANIMATION_STEPS, 0);
		node.putBoolean(GFPreferences.DEBUG_ACTIONS_ACTIVE, false);
		node.putBoolean(GFPreferences.GENERIC_OUTLINE_ACTIVE, false);
		node.putBoolean(GFPreferences.GENERIC_PROPERTY_SHEET_ACTIVE, false);
		node.putBoolean(GFPreferences.MOF_EXAMPLE_SHOWING_ALL_PROPERTIES, false);
		node.putInt(GFPreferences.POLYLINE_ROUNDING, 0);

	}
}
