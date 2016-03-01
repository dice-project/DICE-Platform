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

package org.eclipse.epf.diagram.core.util;

import java.util.Locale;

import org.eclipse.core.runtime.Platform;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.epf.diagram.core.DiagramCorePlugin;
import org.eclipse.epf.library.LibraryResources;
import org.eclipse.gmf.runtime.notation.FontStyle;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;

/**
 * @author Shashidhar Kannoori
 * @since 1.2
 *
 */
public class DiagramConstants {

	public static final String diagramDeleteActionID = "deleteDiagram"; //$NON-NLS-1$
	public static final String Delete_Diagram_Element="deleteDiagramElement"; //$NON-NLS-1$
	public static final String DELETE_DIAGRAM_ID= "org.eclipse.epf.diagram.core.actions.DeleteDiagramAction"; //$NON-NLS-1$
	public static final String RESET_DIAGRAM_LAYOUT = "reset_diagram_layout"; //$NON-NLS-1$
	public static final String REFRESH = "org.eclipse.epf.diagram.core.actions.RefreshAction"; //$NON-NLS-1$
	public static final String DIAGRAM_ADD_MENU_GENERAL_GROUP = "addDiagramGeneralGroup"; //$NON-NLS-1$
	public static final String REPARENT_COMMAND = "reparent command"; //$NON-NLS-1$
	
	public static final Color READ_ONLY_FONT_COLOR = ColorConstants.darkGreen;
	public static final Color DEFAULT_FONT_COLOR = ColorConstants.black;
	public static final Color SUPPRESSED_FONT_COLOR = ColorConstants.gray;
	
	
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
	
	public static final String getFontName(FontStyle style) {
		String name = "Arial"; //$NON-NLS-1$
		if (style != null) {	
			name = style.getFontName();
		}

		// Corrupted Japanese characters in Activity Diagrams
		String currentLocale = Platform.getNL();
		if (currentLocale != null
				&& currentLocale.startsWith(Locale.JAPANESE.getLanguage())) {
			if (WIN_2K.equalsIgnoreCase(System.getProperty("os.name"))) { //$NON-NLS-1$
				name = LibraryResources.roleDiagramFont_name_win32_ja;
			}
		}
		return name;
	}
	
	
	/**
	 * Get default font data
	 * @return
	 */
	public static final FontData getDefaultFontData() {
	
		String fontName = DEFAULT_FONT_NAME;
		
		// Corrupted Japanese characters in Activity Diagrams
		String currentLocale = Platform.getNL();
		if (currentLocale != null
				&& currentLocale.startsWith(Locale.JAPANESE.getLanguage())) {
			if (WIN_2K.equalsIgnoreCase(System.getProperty("os.name"))) { //$NON-NLS-1$
				fontName = LibraryResources.roleDiagramFont_name_win32_ja;
			}
		}
		FontData fontData = new FontData(fontName, DEFAULT_FONT_SIZE, SWT.NORMAL);
		return fontData;
	}
	
	public static final String CREATE_START_NODE = "create_start_node"; //$NON-NLS-1$

	public static final String CREATE_END_NODE = "create_end_node"; //$NON-NLS-1$

	public static final String CREATE_JOIN_NODE = "create_join_node"; //$NON-NLS-1$

	public static final String CREATE_DECISION_NODE = "create_decision_node"; //$NON-NLS-1$

	public static final String CREATE_ACTIVITY = "create_activity"; //$NON-NLS-1$

	public static final String CREATE_PHASE = "create_phase"; //$NON-NLS-1$

	public static final String CREATE_ITERATION = "create_iteration"; //$NON-NLS-1$

	public static final String CREATE_TASK_DESCRIPTOR = "create_task_descriptor"; //$NON-NLS-1$

	public static final String CREATE_MILESTONE = "create_milestone"; //$NON-NLS-1$
	
	public static final String CREATE_FORK_NODE="create_fork_node"; //$NON-NLS-1$
	
	public static final String CREATE_MERGE_NODE="create_merge_node"; //$NON-NLS-1$
	
	public static final String CREATE_PARTITION ="create_partition"; //$NON-NLS-1$
	
	public static final String OPEN_ACTIVITY_DETAIL_DIAGRAM = "open_activity_detail_diagram"; //$NON-NLS-1$
	
	public static final String CREATE_WORK_PRODUCT_DESCRIPTOR_NODE="create_work_product_descriptor"; //$NON-NLS-1$
	
	public static final String CREATE_LINK = "create_diagram_link"; //$NON-NLS-1$

	public static final String MOVE_ACTION = "move_action_id"; //$NON-NLS-1$

	/**
	 * Below defaults variables are to exclude from diagram.
	 */
	public static final int FILL_COLOR_EDEFAULT = 16777215;
	public static final int LINE_COLOR_EDEFAULT = 11579568;
	
	public static final ImageDescriptor CONTROL_FLOW_IMAGE_DESCRIPTOR = DiagramCorePlugin.getDefault()
										.getImageDescriptor("link.gif"); //$NON-NLS-1$
}
