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
package org.eclipse.epf.migration.diagram;

import org.eclipse.osgi.util.NLS;

/**
 * Migration resources
 * 
 * @author Shilpa Toraskar
 * @since 1.2
 *
 */
public final class MigrationResources extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.epf.migration.diagram.Resources";//$NON-NLS-1$
	private MigrationResources() {
		// Do not instantiate
	}
	

	public static String workflow_export_wizard_title;
	public static String workflow_export_wizard_text;
	public static String workflow_export_wizard_processGroup_text;
	public static String workflow_export_wizard_capabilityPatternRadioButton_text;
	public static String workflow_export_wizard_deliveryProcessRadioButton_text;
	public static String workflow_export_wizard_processNameLabel_text;
	public static String workflow_export_wizard_contextNameLabel_text;
	public static String workflow_export_wizard_templateGroup_text;
	public static String workflow_export_wizard_templateNameLabel_text;
	public static String workflow_export_wizard_dirLabel_text;
	public static String workflow_export_wizard_browseButton_text;
	public static String workflow_overwriteTextDialog_title;
	public static String workflow_overwriteText_msg;
	public static String workflow_export_wizard_activitiesNameLabel_text;

		
	public static String open_method_library_error_title;
	public static String open_method_library_error_text;
	
	public static String GRAPH_NODE_FORK;
	public static String GRAPH_NODE_INITIAL;
	public static String GRAPH_NODE_FINAL;
	public static String GRAPH_NODE_DECISION;
	public static String GRAPH_NODE_FREE_TEXT;
	public static String GRAPH_NODE_MERGE;
	public static String GRAPH_NODE_JOIN;
	public static String GRAPH_NODE_LINK;
	
	static {
		NLS.initializeMessages(BUNDLE_NAME, MigrationResources.class);
	}
}