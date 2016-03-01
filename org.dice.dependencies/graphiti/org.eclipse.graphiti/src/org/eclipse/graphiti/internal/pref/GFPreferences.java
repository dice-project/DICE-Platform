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

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.graphiti.internal.GraphitiPlugin;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class GFPreferences {

	public static final String CPU_PROFILING_TRACE_ACTIVE = "CPU_PROFILING_TRACE_ACTIVE"; //$NON-NLS-1$
	public static final String RECURSIVE_CHECK_FOR_UPDATE_ACTIVE = "RECURSIVE_CHECK_FOR_UPDATE_ACTIVE"; //$NON-NLS-1$
	public static final String INVISIBLE_RECTANGLES_SHOWN = "INVISIBLE_RECTANGLES_SHOWN"; //$NON-NLS-1$
	public static final String VISUAL_STATE_RENDERING = "VISUAL_STATE_RENDERING"; //$NON-NLS-1$
	public static final String PROGRESS_DIALOG_ACTIVE = "PROGRESS_DIALOG_ACTIVE"; //$NON-NLS-1$
	public static final String CONTEXT_BUTTON_PAD_DECLARATION = "CONTEXT_BUTTON_PAD_DECLARATION"; //$NON-NLS-1$
	public static final String ZOOM_ANIMATION_STEPS = "ZOOM_ANIMATION_STEPS"; //$NON-NLS-1$
	public static final String DEBUG_ACTIONS_ACTIVE = "DEBUG_ACTIONS_ACTIVE"; //$NON-NLS-1$
	public static final String MOF_EXAMPLE_SHOWING_ALL_PROPERTIES = "MOF_EXAMPLE_SHOWING_ALL_PROPERTIES"; //$NON-NLS-1$
	public static final String GENERIC_PROPERTY_SHEET_ACTIVE = "GENERIC_PROPERTY_SHEET_ACTIVE"; //$NON-NLS-1$
	public static final String GENERIC_OUTLINE_ACTIVE = "GENERIC_OUTLINE_ACTIVE"; //$NON-NLS-1$
	public static final String POLYLINE_ROUNDING = "POLYLINE_ROUNDING"; //$NON-NLS-1$

	IEclipsePreferences node;

	private static GFPreferences pref = new GFPreferences();

	//Singleton.
	private GFPreferences() {
		//
	}

	public static GFPreferences getInstance() {
		return pref;
	}

	public boolean areDebugActionsActive() {
		return getNode().getBoolean(DEBUG_ACTIONS_ACTIVE, false);
	}

	public boolean areInvisibleRectanglesShown() {
		return getNode().getBoolean(INVISIBLE_RECTANGLES_SHOWN, false);
	}

	public int getContextButtonPadDeclaration() {
		return getNode().getInt(CONTEXT_BUTTON_PAD_DECLARATION, 0);
	}

	public int getPolylineRounding() {
		return getNode().getInt(POLYLINE_ROUNDING, 0);
	}

	public int getVisualStateRendering() {
		return getNode().getInt(VISUAL_STATE_RENDERING, 0);
	}

	public int getZoomAnimationSteps() {
		return getNode().getInt(ZOOM_ANIMATION_STEPS, 0);
	}

	public boolean isCPUProfilingTraceActive() {
		return getNode().getBoolean(CPU_PROFILING_TRACE_ACTIVE, false);
	}

	public boolean isGenericOutlineActive() {
		return getNode().getBoolean(GENERIC_OUTLINE_ACTIVE, false);
	}

	public boolean isGenericPropertySheetActive() {
		return getNode().getBoolean(GENERIC_PROPERTY_SHEET_ACTIVE, false);
	}

	public boolean isMofExampleShowingAllProperties() {
		return getNode().getBoolean(MOF_EXAMPLE_SHOWING_ALL_PROPERTIES, false);
	}

	public boolean isProgressDialogActive() {
		return getNode().getBoolean(PROGRESS_DIALOG_ACTIVE, false);
	}

	public boolean isRecursiveCheckForUpdateActive() {
		return getNode().getBoolean(RECURSIVE_CHECK_FOR_UPDATE_ACTIVE, false);
	}

	private IEclipsePreferences getNode() {
		if (node == null) {
			if (Platform.getBundle("org.eclipse.graphiti.examples.common") != null) { //$NON-NLS-1$
				node = new InstanceScope().getNode(GraphitiPlugin.PLUGIN_ID);
			} else {
				// If the samples plug-in is not installed, we want to have defaults
				// since there is no UI for editing the preferences.
				node = new DefaultScope().getNode(GraphitiPlugin.PLUGIN_ID);
			}
		}
		return node;
	}

}
