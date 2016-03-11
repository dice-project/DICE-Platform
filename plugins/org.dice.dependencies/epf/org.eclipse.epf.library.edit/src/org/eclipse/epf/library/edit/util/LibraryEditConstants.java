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
package org.eclipse.epf.library.edit.util;

import org.eclipse.epf.library.edit.LibraryEditPlugin;

/**
 * Constants for library edit plugin
 * 
 * @author Phong Nguyen Le Jun 30, 2005
 * @since 1.0
 */
public final class LibraryEditConstants {
	public static final String[] UNCATEGORIZED_TASKS_PATH = {
			LibraryEditPlugin.INSTANCE.getString("_UI_Content_group") //$NON-NLS-1$
			,
			LibraryEditPlugin.INSTANCE
					.getString("_UI_Standard_Categories_group") //$NON-NLS-1$
			,
			LibraryEditPlugin.INSTANCE.getString("_UI_Disciplines_group") //$NON-NLS-1$
			,
			LibraryEditPlugin.INSTANCE
					.getString("_UI_Uncategorized_Tasks_text") //$NON-NLS-1$
	};

	public static final String[] UNCATEGORIZED_DOMAIN_WORKPRODUCTS_PATH = {
			LibraryEditPlugin.INSTANCE.getString("_UI_Content_group") //$NON-NLS-1$
			,
			LibraryEditPlugin.INSTANCE
					.getString("_UI_Standard_Categories_group") //$NON-NLS-1$
			, LibraryEditPlugin.INSTANCE.getString("_UI_Domains_group") //$NON-NLS-1$
			, LibraryEditPlugin.INSTANCE.getString("_UI_Uncategorized_text") //$NON-NLS-1$				
	};

	public static final String[] UNCATEGORIZED_WORKPRODUCTTYPE_WORKPRODUCTS_PATH = {
			LibraryEditPlugin.INSTANCE.getString("_UI_Content_group") //$NON-NLS-1$
			,
			LibraryEditPlugin.INSTANCE
					.getString("_UI_Standard_Categories_group") //$NON-NLS-1$
			,
			LibraryEditPlugin.INSTANCE.getString("_UI_WorkProductTypes_group") //$NON-NLS-1$
			, LibraryEditPlugin.INSTANCE.getString("_UI_Uncategorized_text") //$NON-NLS-1$				
	};

	public static final String[] UNCATEGORIZED_ROLES_PATH = {
			LibraryEditPlugin.INSTANCE.getString("_UI_Content_group") //$NON-NLS-1$
			,
			LibraryEditPlugin.INSTANCE
					.getString("_UI_Standard_Categories_group") //$NON-NLS-1$
			,
			LibraryEditPlugin.INSTANCE.getString("_UI_Role_Sets_group") //$NON-NLS-1$
			,
			LibraryEditPlugin.INSTANCE
					.getString("_UI_Uncategorized_Roles_text") //$NON-NLS-1$		
	};

	public static final String[] UNCATEGORIZED_TOOLMENTORS_PATH = {
			LibraryEditPlugin.INSTANCE.getString("_UI_Content_group") //$NON-NLS-1$
			,
			LibraryEditPlugin.INSTANCE
					.getString("_UI_Standard_Categories_group") //$NON-NLS-1$
			, LibraryEditPlugin.INSTANCE.getString("_UI_Tools_group") //$NON-NLS-1$
			, LibraryEditPlugin.INSTANCE.getString("_UI_Uncategorized_text") //$NON-NLS-1$				
	};

	private static final String PREF_PREFIX = "org.eclipse.epf.library.edit."; //$NON-NLS-1$

	public static final String PREF_ENABLE_PROCESS_CONTRIBUTION = PREF_PREFIX
			+ "enable_process_contribution"; //$NON-NLS-1$

	public static final String PREF_ENABLE_HEALTH_CHECK = PREF_PREFIX
			+ "enable_health_check"; //$NON-NLS-1$

	public static final String PREF_INHERIT_SUPPRESSION_STATE = PREF_PREFIX
			+ "inherit_suppression_state"; //$NON-NLS-1$

	public static final String PREF_SYN_FREE = PREF_PREFIX
			+ "syn_free"; //$NON-NLS-1$

	public static final String PREF_WBS_COLUMNS = PREF_PREFIX + "wbs_columns"; //$NON-NLS-1$

	public static final String PREF_TBS_COLUMNS = PREF_PREFIX + "tbs_columns"; //$NON-NLS-1$

	public static final String PREF_WPBS_COLUMNS = PREF_PREFIX + "wpbs_columns"; //$NON-NLS-1$

	public static final String NEW_CUSTOM_CATEGORY = "new_custom_category"; //$NON-NLS-1$

	public static final String NEW_DISCIPLINE = "new_discipline"; //$NON-NLS-1$

	public static final String NEW_DISCIPLINE_GROUPING = "new_discipline_grouping"; //$NON-NLS-1$

	public static final String NEW_DOMAIN = "new_domain"; //$NON-NLS-1$

	public static final String NEW_ROLE_SET = "new_role_set"; //$NON-NLS-1$

	public static final String NEW_ROLE_SET_GROUPING = "new_role_set_grouping"; //$NON-NLS-1$

	public static final String NEW_TOOL = "new_tool"; //$NON-NLS-1$

	public static final String NEW_WORK_PRODUCT_TYPE = "new_work_product_kind"; //$NON-NLS-1$

	public static final String NEW_ROLE = "new_role"; //$NON-NLS-1$

	public static final String NEW_TASK = "new_task"; //$NON-NLS-1$

	public static final String NEW_WORKPRODUCT = "new_workproduct"; //$NON-NLS-1$

	public static final String NEW_ARTIFACT = "new_artifact"; //$NON-NLS-1$

	public static final String NEW_DELIVERABLE = "new_deliverable"; //$NON-NLS-1$

	public static final String NEW_OUTCOME = "new_outcome"; //$NON-NLS-1$

	public static final String NEW_CHECKLIST = "new_checklist"; //$NON-NLS-1$

	public static final String NEW_WHITEPAPER = "new_whitepaper"; //$NON-NLS-1$

	public static final String NEW_CONCEPT = "new_concept"; //$NON-NLS-1$

	public static final String NEW_ESTIMATE = "new_estimate"; //$NON-NLS-1$

	public static final String NEW_ESTIMATION_CONSIDERATION = "new_estimation_considerations"; //$NON-NLS-1$

	public static final String NEW_ESTIMATATING_METRIC = "new_estimating_metric"; //$NON-NLS-1$

	public static final String NEW_EXAMPLE = "new_example"; //$NON-NLS-1$

	public static final String NEW_PRACTICE = "new_practice"; //$NON-NLS-1$

	public static final String NEW_REPORT = "new_report"; //$NON-NLS-1$

	public static final String NEW_ROADMAP = "new_roadmap"; //$NON-NLS-1$

	public static final String NEW_REUSABLE_ASSET = "new_reusable_asset"; //$NON-NLS-1$

	public static final String NEW_SUPPORTING_MATERIAL = "new_supporting_material"; //$NON-NLS-1$

	public static final String NEW_TEMPLATE = "new_template"; //$NON-NLS-1$

	public static final String NEW_TERM_DEFINITION = "new_term_definition"; //$NON-NLS-1$

	public static final String NEW_TOOL_MENTOR = "new_toolmentor"; //$NON-NLS-1$

	public static final String NEW_GUIDELINE = "new_guideline"; //$NON-NLS-1$

	public static final String NEW_GUIDANCE = "new_guidance"; //$NON-NLS-1$

	public static final String NEW_CONTENT_PACKAGE = "new_content_package"; //$NON-NLS-1$

	public static final String NEW_CONFIGURATION = "new_configuration"; //$NON-NLS-1$

	public static final String NEW_PROCESS_FAMILY = "new_process_family"; //$NON-NLS-1$
}
