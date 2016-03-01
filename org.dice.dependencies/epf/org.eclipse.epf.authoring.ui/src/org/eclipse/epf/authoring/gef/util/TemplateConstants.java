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
package org.eclipse.epf.authoring.gef.util;

import java.util.Locale;

import org.eclipse.core.runtime.Platform;
import org.eclipse.epf.library.LibraryResources;
import org.eclipse.swt.graphics.Font;

/**
 * Constants used in authoring diagram
 * @author Phong Nguyen Le
 * @since 1.0
 */
public final class TemplateConstants {

	public static final String ACTIVITY = "activity template"; //$NON-NLS-1$

	public static final String ITERATION = "iteration template"; //$NON-NLS-1$

	public static final String PHASE = "phase template"; //$NON-NLS-1$

	public static final String SYNCH_BAR = "synchonization bar"; //$NON-NLS-1$

	public static final String DECISION_NODE = "decision node"; //$NON-NLS-1$

	public static final String START_NODE = "start node"; //$NON-NLS-1$

	public static final String END_NODE = "end node"; //$NON-NLS-1$

	public static final String WORK_PRODUCT = "work product template"; //$NON-NLS-1$

	public static final String ROLE_DESCRIPTOR = "role descriptor template"; //$NON-NLS-1$

	public static final String TASK_DESCRIPTOR = "task descriptor template"; //$NON-NLS-1$

	public static final String WORK_PRODUCT_DESCRIPTOR = "work product descriptor template"; //$NON-NLS-1$

	public static final String FREE_TEXT = "free text"; //$NON-NLS-1$

	// TODO: move these constants to other file. This class define only template
	// constants
	//
	public static final String PROPERTY_FONT_NAME = "property_font_name"; //$NON-NLS-1$

	public static final String PROPERTY_FONT_STYLE = "property_font_style"; //$NON-NLS-1$

	public static final String PROPERTY_FONT_HEIGHT = "property_font_height"; //$NON-NLS-1$

	public static final String PROPERTY_FONT_RED = "property_font_red"; //$NON-NLS-1$

	public static final String PROPERTY_FONT_BLUE = "property_font_blue"; //$NON-NLS-1$

	public static final String PROPERTY_FONT_GREEN = "property_font_green"; //$NON-NLS-1$

	// For default fonts.
	public static final String DEFAULT_FONT_NAME = getFontName();

	public static final int DEFAULT_FONT_SIZE = 8;

	public static final Font DEFAULT_FONT = new Font(null, DEFAULT_FONT_NAME,
			DEFAULT_FONT_SIZE, java.awt.Font.PLAIN);

	public static final Font DEFAULT_FONT_ITALIC = new Font(null,
			DEFAULT_FONT_NAME, DEFAULT_FONT_SIZE, java.awt.Font.ITALIC);

	public static final String WIN_2K = "Windows 2000"; //$NON-NLS-1$

	public static final String getFontName() {
		String name = "Arial"; //$NON-NLS-1$

		// Corrupted Japanese characters in Activity Diagrams
		String currentLocale = Platform.getNL();
		if (currentLocale != null
				&& currentLocale.startsWith(Locale.JAPANESE.getLanguage())) {
			if (WIN_2K.equalsIgnoreCase(System.getProperty("os.name"))) { //$NON-NLS-1$
				name = LibraryResources.roleDiagramFont_name_win32;
			}
		}
		return name;
	}
	public static final String ADD_ROLE_TASKS_BOX_BG_COLOR_RGB = "ADD_ROLE_TASKS_BOX_BG_COLOR_RGB"; //$NON-NLS-1$
	public static final String ADD_WP_BOX_BG_COLOR_RGB = "ADD_WP_BOX_BG_COLOR_RGB"; //$NON-NLS-1$
}
