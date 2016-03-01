/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.epf.authoring.gef.util;

import org.eclipse.osgi.util.NLS;

/**
 * Resource Class for the Authoring Diagram (GEF) packages.
 * 
 */
public final class DiagramUIResources extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.epf.authoring.gef.util.Resources";//$NON-NLS-1$

	private DiagramUIResources() {
		// Do not instantiate
	}

	public static String previous_name_null1;
	public static String err_name_empty;
	public static String moveAction_label;
	public static String AbstractDiagram_Select_text;
	public static String AbstractDiagram_Select_tooltip;
	public static String AbstractDiagram_AddNode_title;
	public static String AbstractDiagram_AddNode_err_msg;
	public static String AbstractDiagram_Link_text;
	public static String AbstractDiagram_Link_select;
	public static String AbstractDiagram_Link_tooltip;
	public static String ActivityDiagram_StartNode_text;
	public static String ActivityDiagram_StartNode_tooltip;
	public static String AbstractDiagram_FreeText_text;
	public static String AbstractDiagram_FreeText_tooltip;
	public static String ActivityDiagram_SyncBar_text;
	public static String ActivityDiagram_SyncBar_tooltip;
	public static String ActivityDiagram_DecisionNode_text;
	public static String ActivityDiagram_DecisionNode_tooltip;
	public static String ActivityDiagram_EndNode_text;
	public static String ActivityDetailDiagram_prefix;
	public static String ActivityDiagram_EndNode_tooltip;
	public static String ActivityDiagram_Activity_text;
	public static String ActivityDiagram_Activity_tooltip;
	public static String ActivityDiagram_Iteration_text;
	public static String ActivityDiagram_Iteration_tooltip;
	public static String ActivityDiagram_Phase_text;
	public static String ActivityDiagram_Phase_tooltip;
	public static String AbstractDiagram_TaskDescriptor_text;
	public static String AbstractDiagram_RoleDescriptor_text;
	public static String WorkProductDependencyDiagram_prefix;
	public static String AbstractDiagram_RoleDescriptor_tooltip;
	public static String AbstractDiagram_TaskDescriptor_tooltip;
	public static String ActvitivityDiagram_DeliveryProcess_text;
	public static String ActvitivityDiagram_CapabilityPattern_text;
	public static String ActvitivityDiagram_Phase_tex;
	public static String ActvitivityDiagram_Iteration_text;
	public static String ActvitivityDiagram_Activity_text;
	public static String AbstractDiagram_WorkProductDescriptor_text;
	public static String AbstractDiagram_WorkProductDescriptor_tooltip;
	public static String AbstractDiagramEditor_alignMenu_text;
	public static String AbstractDiagramEditor_refreshFromBaseActivity_text;
	public static String AbstractDiagramEditor_formatTextBoxActivity_text;
	public static String AbstractDiagramEditor_hAlignAverageAction_text;
	public static String AbstractDiagramEditor_hAlignFirstSelectedAction_text;
	public static String AbstractDiagramEditor_vAlignAverageAction_text;
	public static String AbstractDiagramEditor_vAlignFirstSelectedAction_text;
	public static String AbstractDiagramEditor_Save_text;
	public static String AbstractDiagramEditor_Save_message;
	public static String AbstractDiagramEditor_MoveNode_label;
	public static String AbstractDiagramEditorrefreshfrombase;
	public static String AbstractDiagram_BendPoint_create;
	public static String ActivityDiagram_openActivityDetailDiagram_text;
	public static String refreshAction_label;
	public static String refreshFromBaseCmd_label;
	public static String diagram_saveError;
	public static String DeleteDiagram_text;
	public static String DeleteDiagram_prompt;
	public static String align_bend_point_text;
	public static String ConfigurationEditorInput_configeditor0;
	public static String diagram_node_property_value_problem_msg;
	public static String diagram_errorDialog_title_text0;
	public static String ActivityDetailDiagramEditor_ResetDiagramLayout_text;
	public static String errorDialog_title;
	public static String command_cannotEdit;
	public static String AbstractDiagramEditor_ParentEditorClose_title;
	public static String AbstractDiagramEditor_ParentEditorClose_text;

	static {
		NLS.initializeMessages(BUNDLE_NAME, DiagramUIResources.class);
	}
	
	/**
	 * Returns the localized string associated with a resource key and formatted
	 * with a given object.
	 * 
	 * @param key
	 *            A resource key.
	 * @param data
	 *            An object.
	 * @return A formatted localized string.
	 */
	public static String bind(String key, Object data) {
		return NLS.bind(key, new Object[] { data });
	}
}