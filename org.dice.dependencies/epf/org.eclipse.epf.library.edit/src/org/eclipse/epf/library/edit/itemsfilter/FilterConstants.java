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
package org.eclipse.epf.library.edit.itemsfilter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.LibraryEditResources;

/**
 * Defines the global constants used in the method element selection dialogs.
 * 
 * @author Shashidhar Kannoori
 * @since 1.0
 */
public class FilterConstants {

	public static final String CUSTOM_CATEGORIES = LibraryEditResources.FilterConstants_customcategories_text; 

	public static final String METHO_PLUGINS = LibraryEditResources.FilterConstants_methodplugins_text; 

	public static final String CONTENT_PACKAGES = LibraryEditResources.FilterConstants_contentpackages_text; 

	public static final String ROLESETS = LibraryEditResources.FilterConstants_rolesets_text; 

	public static final String DISCIPLINES = LibraryEditResources.FilterConstants_disciplines_text; 

	public static final String WORKPRODUCTTYPES = LibraryEditResources.FilterConstants_workproducttypes_text; 

	public static final String DOMAINS = LibraryEditResources.FilterConstants_domains_text; 

	public static final String TOOLS = LibraryEditResources.FilterConstants_tools_text; 

	public static final String ALL_ELEMENTS = LibraryEditResources.FilterConstants_all_text; 

	public static final String ROLES = LibraryEditResources.FilterConstants_roles_text; 

	public static final String TASKS = LibraryEditResources.FilterConstants_tasks_text; 

	public static final String WORKPRODUCTS = LibraryEditResources.FilterConstants_workproducts_text; 

	public static final String UDTs = LibraryEditResources.FilterConstants_UDTs_text; 
	
	public static final String GUIDANCE = LibraryEditResources.FilterConstants_guidance_text; 

	public static final String PROCESSES = LibraryEditResources.FilterConstants_processes_text; 

	public static final String ACTIVITIES = LibraryEditResources.FilterConstants_activities_text; 

	public static final String WORK_PRODUCT_DESCRIPTORS = LibraryEditResources.FilterConstants_workproductdescriptors_text; 

	public static final String ROLE_DESCRIPTORS = LibraryEditResources.FilterConstants_roledescriptors_text; 

	public static final String TASK_DESCRIPTORS = LibraryEditResources.FilterConstants_taskdescriptors_text; 

	public static final String ONLY_CONTENT_ELEMENTS = LibraryEditResources.FilterConstants_contentelements_text; 

	public static final String CONFIG_CONTENT_ELEMENT = LibraryEditResources.FilterConstants_allcontentelements_text; 

	public static final String ROADMAP = LibraryEditResources.FilterConstants_roadmap_text; 

	public static final String CHECKLISTS = LibraryEditResources.FilterConstants_checklists_text; 

	public static final String CONCEPTS = LibraryEditResources.FilterConstants_concepts_text; 

	public static final String PRACTICES = LibraryEditResources.FilterConstants_practices_text; 	
	
	public static final String REPORTS = LibraryEditResources.FilterConstants_reports_text; 

	public static final String REUSABLE_ASSETS = LibraryEditResources.FilterConstants_reusableassets_text; 

	public static final String WHITE_PAPERS = LibraryEditResources.FilterConstants_whitepapers_text; 

	public static final String TEMPLATES = LibraryEditResources.FilterConstants_templates_text; 

	public static final String GUIDELINES = LibraryEditResources.FilterConstants_guidelines_text; 

	public static final String SUPPORTING_MATERIALS = LibraryEditResources.FilterConstants_supportingmaterials_text; 

	public static final String TOOL_MENTORS = LibraryEditResources.FilterConstants_toolmentors_text; 

	public static final String EXAMPLES = LibraryEditResources.FilterConstants_examples_text; 

	public static final String ROLE_SET_GROUPINGS = LibraryEditResources.FilterConstants_rolesetgropuings_text; 

	public static final String DISCIPLINE_GROUPINGS = LibraryEditResources.FilterConstants_disciplinegroupings_text; 

	// public static final String

	// private static final String[] GUIDANCE_TYPES = new String[]{
	// CHECKLISTS, CONCEPTS, EXAMPLES, PRACTICES, REPORTS, REUSABLE_ASSETS,
	// SUPPORTING_MATERIALS, TEMPLATES, TOOL_MENTORS, WHITE_PAPERS,
	// GUIDELINES,ROADMAP};

	public static final String[] CONTENT_ELEMENTS = new String[] { ROLES,
			TASKS, WORKPRODUCTS, GUIDANCE, SUPPORTING_MATERIALS,
			ONLY_CONTENT_ELEMENTS, TOOL_MENTORS };

	public static final String[] CONTENT_CATEGORIES = new String[] { ROLESETS,
			WORKPRODUCTTYPES, DISCIPLINES, TOOLS, DOMAINS };

	public static final String[] BREAKDOWN_ELEMENTS = new String[] {
			WORK_PRODUCT_DESCRIPTORS, TASK_DESCRIPTORS, ROLE_DESCRIPTORS };

	public static final List contentElementStrs = Arrays
			.asList(CONTENT_ELEMENTS);

	public static final List categoryStrs = Arrays.asList(CONTENT_CATEGORIES);

	public static final List breakdownElements = Arrays
			.asList(BREAKDOWN_ELEMENTS);

	public static HashMap hashMap = new HashMap();
	
	public static final String space = "-"; //$NON-NLS-1$

	public static final String ESTIMATE_CONSIDERATIONS = LibraryEditPlugin
			.getPlugin().getString("_UI_Guidances_EstimationConsiderations"); //$NON-NLS-1$

	public static final String TERM_DEFINITIONS = LibraryEditPlugin.getPlugin()
			.getString("_UI_Guidances_TermDefinitions"); //$NON-NLS-1$
	
	public static final String ANY_STRING = "*";   //$NON-NLS-1$
	public static final String ANY_CHARACTER = "?";  //$NON-NLS-1$
}
