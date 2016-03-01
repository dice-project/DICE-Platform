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
/**
 * 
 */
package org.eclipse.epf.diagram.core;

import org.eclipse.osgi.util.NLS;

/**
 * @author Shashidhar Kannoori
 * @since 1.2
 *
 */
public class DiagramCoreResources extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.epf.diagram.core.Resources";//$NON-NLS-1$
	
	/**
	 * 
	 */
	public DiagramCoreResources() {
	}

	static {
		NLS.initializeMessages(BUNDLE_NAME, DiagramCoreResources.class);
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
	
	public static String ActivityDiagram_Palette_flows_title;
	public static String ActivityDiagram_Palette_flows_create_text;
	public static String ActivityDiagram_Palette_control_flow_create_text;
	public static String ActivityDiagram_Palette_control_flow_text;
	public static String ActivityDiagram_Palette_nodes_group_text;
	public static String ActivityDiagram_Palette_nodes_titles;
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
	public static String Progress_Saving_message;
	public static String Warning_Saving_Diagram;
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
	public static String DeleteDiagram_AD;
	public static String DeleteDiagram_ADD;
	public static String DeleteDiagram_WPDD;
	public static String DeleteDiagram_prompt_new;
	public static String align_bend_point_text;
	public static String ConfigurationEditorInput_configeditor0;
	public static String diagram_node_property_value_problem_msg;
	public static String diagram_errorDialog_title;
	public static String ActivityDetailDiagramEditor_ResetDiagramLayout_text;
	public static String errorDialog_title;
	public static String command_cannotEdit;
	public static String AbstractDiagramEditor_ParentEditorClose_title;
	public static String AbstractDiagramEditor_ParentEditorClose_text;
	
	public static String DiagramValidation_err_cannot_connect_text;
	public static String DiagramValidation_err_samepredandsuc_text;
	public static String DiagramValidation_err_cannot_delete_text;
	public static String deleteCommand_label;
	public static String deleteLinkCommand_label;

	public static String ActivityDiagram_Milestone_text;
	public static String ActivityDiagram_Milestone_tooltip;
	public static String ActivityDiagram_Join_Node_text;
	public static String ActivityDiagram_Join_Node_tooltip;
	public static String ActivityDiagram_Merge_Node_text;
	public static String ActivityDiagram_Merge_Node_tooltip;
	public static String ActivityDiagram_Fork_Node_text;
	public static String ActivityDiagram_Fork_Node_tooltip;
	public static String ActivityDiagram_Partition_Node_text;
	public static String ActivityDiagram_Partition_Node_tooltip;
	
	public static String ActivityDiagram_New_Task_Descriptor;
	public static String ActivityDiagram_New_Milestone;
	public static String WPDD_New_WorkProductDescriptor;
	public static String ActivityDiagram_New_Activity;
	public static String ActivityDiagram_New_Phase;
	public static String ActivityDiagram_New_Iteration;

	
	public static String ActivityDiagram_kind;
	public static String ActivityDetail_Diagram_kind;
	public static String WorkProductDependency_Diagram_kind;
	
	public static String WPDD_Palette_control_flow_text;
	public static String WPDD_Palette_control_flow_create_text;

}
