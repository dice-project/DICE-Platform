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
package org.eclipse.epf.authoring.ui;

/**
 * Authoring UI help contexts
 * 
 * @author Shilpa Toraskar
 * @since 1.0
 *
 */
public class AuthoringUIHelpContexts {

	private final static String ROOT_CONTEXT = AuthoringUIHelpContexts.class.getPackage().getName() + ".";  //$NON-NLS-1$
	
	public final static String  FILE_NEW_LIBRARY_CONTEXT = ROOT_CONTEXT +  "file_new_library_context"; //$NON-NLS-1$ 
	public final static String	FILE_NEW_PLUGIN_CONTEXT = ROOT_CONTEXT +  "file_new_plugin_context"; //$NON-NLS-1$
	public final static String	FILE_NEW_CONFIGURATION_CONTEXT = ROOT_CONTEXT +  "file_new_configuration_context"; //$NON-NLS-1$  
	public final static String  FILE_OPEN_LIBRARY_CONTEXT = ROOT_CONTEXT +  "file_open_library_context"; //$NON-NLS-1$
	public final static String  FILE_OPEN_CONFIGURATION_CONTEXT = ROOT_CONTEXT +  "file_open_configuration_context"; //$NON-NLS-1$
	public final static String  FILE_IMPORT_CONTEXT = ROOT_CONTEXT + "file_import_context"; //$NON-NLS-1$
	public final static String  FILE_EXPORT_CONTEXT = ROOT_CONTEXT + "file_export_context"; //$NON-NLS-1$
	public final static String  LIBRARY_NAVIGATOR_VIEW_CONTEXT = ROOT_CONTEXT +  "library_navigator_view_context"; //$NON-NLS-1$  
	public final static String  CONFIGURATION_VIEW_CONTEXT = ROOT_CONTEXT +  "configuration_view_context"; //$NON-NLS-1$  
	public final static String  PLUGIN_EDITOR_DESCRIPTION_ALL_CONTEXT = ROOT_CONTEXT +  "plugin_editor_description_all_context"; //$NON-NLS-1$  
	public final static String  CONFIGURATION_EDITOR_ALL_CONTEXT = ROOT_CONTEXT +  "configuration_editor_all_context"; //$NON-NLS-1$  
	public final static String  CONTENT_PACKAGE_EDITOR_ALL_CONTEXT = ROOT_CONTEXT +  "content_package_editor_all_context"; //$NON-NLS-1$  
	public final static String  EDITOR_ROLE_CONTEXT_HELP_ID = ROOT_CONTEXT +  "editor_role_context_help_ID"; //$NON-NLS-1$  
	public final static String  EDITOR_TASK_CONTEXT_HELP_ID = ROOT_CONTEXT +  "editor_task_context_help_ID"; //$NON-NLS-1$  
	public final static String  EDITOR_WORK_PRODUCT_CONTEXT_HELP_ID = ROOT_CONTEXT +  "editor_work_product_context_help_ID"; //$NON-NLS-1$  
	public final static String  EDITOR_GUIDANCE_CONTEXT_HELP_ID = ROOT_CONTEXT +  "editor_guidance_context_help_ID"; //$NON-NLS-1$  
	public final static String  EDITOR_DISCIPLINE_CONTEXT_HELP_ID = ROOT_CONTEXT +  "editor_discipline_context_help_ID"; //$NON-NLS-1$  
	public final static String  EDITOR_DISCIPLINE_GROUPING_CONTEXT_HELP_ID = ROOT_CONTEXT +  "editor_discipline_grouping_context_help_ID"; //$NON-NLS-1$  
	public final static String  EDITOR_DOMAIN_CONTEXT_HELP_ID = ROOT_CONTEXT +  "editor_domain_context_help_ID"; //$NON-NLS-1$  
	public final static String  EDITOR_WORK_PRODUCT_TYPE_CONTEXT_HELP_ID = ROOT_CONTEXT +  "editor_work_product_type_context_help_ID"; //$NON-NLS-1$  
	public final static String  EDITOR_ROLE_SET_CONTEXT_HELP_ID = ROOT_CONTEXT +  "editor_role_set_context_help_ID"; //$NON-NLS-1$  
	public final static String  EDITOR_ROLSE_SET_GROUPING_CONTEXT_HELP_ID = ROOT_CONTEXT +  "editor_rolse_set_grouping_context_help_ID"; //$NON-NLS-1$  
	public final static String  EDITOR_TOOL_CONTEXT_HELP_ID = ROOT_CONTEXT +  "editor_tool_context_help_ID"; //$NON-NLS-1$ 
	public final static String  EDITOR_CUSTOM_CATEGORY_CONTEXT_HELP_ID = ROOT_CONTEXT +  "editor_custom_category_context_help_ID" ; //$NON-NLS-1$ 
	public final static String  FORM_EDITOR_DESCRIPTION_GENERAL_CONTEXT = ROOT_CONTEXT +  "form_editor_description_general_context" ; //$NON-NLS-1$ 
	public final static String  FORM_EDITOR_DESCRIPTION_DETAIL_CONTEXT = ROOT_CONTEXT +  "form_editor_description_detail_context"  ; //$NON-NLS-1$
	public final static String  FORM_EDITOR_DESCRIPTION_NOTATION_CONTEXT = ROOT_CONTEXT +  "form_editor_description_notation_context"  ; //$NON-NLS-1$
	public final static String  FORM_EDITOR_DESCRIPTION_TAILORING_CONTEXT = ROOT_CONTEXT +  "form_editor_description_tailoring_context" ; //$NON-NLS-1$ 
	public final static String  FORM_EDITOR_DESCRIPTION_VERSION_CONTEXT = ROOT_CONTEXT +  "form_editor_description_version_context" ; //$NON-NLS-1$ 
	public final static String  FORM_EDITOR_DESCRIPTION_ELEMENT_CONTEXT = ROOT_CONTEXT +  "form_editor_description_element_context" ; //$NON-NLS-1$ 
	public final static String  FORM_EDITOR_DESCRIPTION_ICON_CONTEXT = ROOT_CONTEXT +  "form_editor_description_icon_context" ; //$NON-NLS-1$ 
	public final static String  EDITOR_CP_DESCRIPTION_CONTEXT_HELP_ID = ROOT_CONTEXT +  "editor_cp_description_context_help_ID" ; //$NON-NLS-1$ 
	public final static String  EDITOR_DP_DESCRIPTION_CONTEXT_HELP_ID = ROOT_CONTEXT +  "editor_dp_description_context_help_ID" ; //$NON-NLS-1$ 
	public final static String  FORM_EDITOR_PROCESS_WBS_CONTEXT = ROOT_CONTEXT +  "form_editor_process_wbs_context"  ; //$NON-NLS-1$
	public final static String  FORM_EDITOR_PROCESS_OBS_CONTEXT = ROOT_CONTEXT +  "form_editor_process_obs_context" ; //$NON-NLS-1$ 
	public final static String  FORM_EDITOR_PROCESS_PBS_CONTEXT = ROOT_CONTEXT +  "form_editor_process_pbs_context"  ; //$NON-NLS-1$
	public final static String  FORM_EDITOR_PROCESS_ALLBS_CONTEXT = ROOT_CONTEXT + "form_editor_process_allbs_context"; //$NON-NLS-1$
	public final static String  CONFIGURATION_PUBLISH_WIZARD_ALL_PAGES_CONTEXT = ROOT_CONTEXT +  "configuration_publish_wizard_all_pages_context" ; //$NON-NLS-1$ 
	public final static String  RICH_TEXT_EDITOR_CONTEXT_ID = ROOT_CONTEXT +  "rich_text_editor_context_ID"  ; //$NON-NLS-1$
	public final static String  FORM_EDITOR_ACTIVITY_DIAGRAM_CONTEXT = ROOT_CONTEXT + "form_editor_activity_diagram_context"; //$NON-NLS-1$
	public final static String  FORM_EDITOR_ACTIVITY_DETAIL_DIAGRAM_CONTEXT = ROOT_CONTEXT +"form_editor_activity_detail_diagram_context";//$NON-NLS-1$
	public final static String  FORM_EDITOR_WP_DEPENDENCY_DIAGRAM_CONTEXT = ROOT_CONTEXT + "form_editor_wp_dependency_diagram_context";//$NON-NLS-1$
	public final static String  LIBRARY_VIEW_NODE_CONTEXT_NOT_IMPLEMENTED = ROOT_CONTEXT + "library_view_node_context_not_implemented"; //$NON-NLS-1$
	public static final String  LAYOUT_FLAT_ACTION = ROOT_CONTEXT + "layout_flat_action"; 	 //$NON-NLS-1$
	public static final String  LAYOUT_HIERARCHICAL_ACTION = ROOT_CONTEXT + "layout_hierarchical_action"; 	 //$NON-NLS-1$	

}
