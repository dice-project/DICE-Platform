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

import org.eclipse.jface.dialogs.MessageDialogWithToggle;

/**
 * Defines and caches the commonly referenced text in the Authoring UI.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class AuthoringUIText {

	/**
	 * Editor form page titles.
	 */
	public static final String ASSIGN_PAGE_TITLE = AuthoringUIResources.assignPage_title; 

	public static final String CATEGORIES_PAGE_TITLE = AuthoringUIResources.categoriesPage_title; 

	public static final String DELIVERABLE_PARTS_PAGE_TITLE = AuthoringUIResources.deliverablePartsPage_title; 

	public static final String DESCRIPTION_PAGE_TITLE = AuthoringUIResources.descriptionPage_title; 

	public static final String DISCIPLINES_PAGE_TITLE = AuthoringUIResources.disciplinesPage_title; 

	public static final String GUIDANCE_PAGE_TITLE = AuthoringUIResources.guidancePage_title; 

	public static final String PREVIEW_PAGE_TITLE = AuthoringUIResources.previewPage_title; 

	public static final String REFERENCES_PAGE_TITLE = AuthoringUIResources.referencesPage_title; 

	public static final String REFERENCE_WORKFLOW_PAGE_TITLE = AuthoringUIResources.referenceWorkflowPage_title; 

	public static final String ROLES_PAGE_TITLE = AuthoringUIResources.rolesPage_title; 

	public static final String ROLE_SETS_PAGE_TITLE = AuthoringUIResources.roleSetsPage_title; 

	public static final String STEPS_PAGE_TITLE = AuthoringUIResources.stepsPage_title; 

	public static final String TASKS_PAGE_TITLE = AuthoringUIResources.tasksPage_title; 

	public static final String TOOL_MENTORS_PAGE_TITLE = AuthoringUIResources.toolMentorsPage_title; 

	public static final String WORK_PRODUCTS_PAGE_TITLE = AuthoringUIResources.workProductsPage_title; 
	
	public static final String WORK_PRODUCT_STATES_PAGE_TITLE = AuthoringUIResources.workProductStatesPage_title;

	/**
	 * Editor section names and description.
	 */
	public static final String CHECK_ITEMS_SECTION_NAME = AuthoringUIResources.checkItemsSection_name; 

	public static final String CHECK_ITEMS_SECTION_DESC = AuthoringUIResources.checkItemsSection_desc; 

	public static final String DEPENDENCIES_SECTION_NAME = AuthoringUIResources.dependenciesSection_name; 

	public static final String DEPENDENCIES_SECTION_DESC = AuthoringUIResources.dependenciesSection_desc; 

	public static final String DETAIL_SECTION_NAME = AuthoringUIResources.detailSection_name; 

	public static final String DETAIL_SECTION_DESC = AuthoringUIResources.detailSection_desc; 

	public static final String GENERAL_INFO_SECTION_NAME = AuthoringUIResources.generalInfoSection_name; 

	public static final String GENERAL_INFO_SECTION_DESC = AuthoringUIResources.generalInfoSection_desc; 

	public static final String ICON_SECTION_NAME = AuthoringUIResources.iconSection_name; 

	public static final String ICON_SECTION_DESC = AuthoringUIResources.iconSection_desc; 

	public static final String NOTATION_SECTION_NAME = AuthoringUIResources.notationSection_name; 

	public static final String NOTATION_SECTION_DESC = AuthoringUIResources.notationSection_desc; 

	public static final String REFERENCED_PLUGINS_SECTION_NAME = AuthoringUIResources.referencedPluginsSection_name; 

	public static final String REFERENCED_PLUGINS_SECTION_DESC = AuthoringUIResources.referencedPluginsSection_desc; 

	public static final String STAFFING_SECTION_NAME = AuthoringUIResources.staffingSection_name; 

	public static final String STAFFING_SECTION_DESC = AuthoringUIResources.staffingSection_desc; 

	public static final String STEPS_SECTION_NAME = AuthoringUIResources.stepsSection_name; 

	public static final String STEPS_SECTION_DESC = AuthoringUIResources.stepsSection_desc; 

	public static final String TAILORING_SECTION_NAME = AuthoringUIResources.tailoringSection_name; 

	public static final String TAILORING_SECTION_DESC = AuthoringUIResources.tailoringSection_desc; 

	public static final String VARIABILITY_SECTION_NAME = AuthoringUIResources.variabilitySection_name; 

	public static final String VARIABILITY_SECTION_DESC = AuthoringUIResources.variabilitySection_desc; 

	public static final String VERSION_INFO_SECTION_NAME = AuthoringUIResources.versionInfoSection_name; 

	public static final String VERSION_INFO_SECTION_DESC = AuthoringUIResources.versionInfoSection_desc;
	
	public static final String SLOT_SECTION_NAME = AuthoringUIResources.slotSection_name;

	public static final String SLOT_SECTION_DESC = AuthoringUIResources.slotSection_desc;
	
	public static final String STATES_SECTION_NAME = AuthoringUIResources.statesSection_name;
	
	public static final String STATES_SECTION_DESC = AuthoringUIResources.statesSection_desc;
	
	/**
	 * UI and element attribute labels.
	 */
	public static final String ADDITIONAL_INFO_TEXT = AuthoringUIResources.additionalInfo_text; 

	public static final String APPLICATION_TEXT = AuthoringUIResources.application_text; 

	public static final String ALTERNATIVES_TEXT = AuthoringUIResources.alternatives_text; 

	public static final String ASSIGNMENT_APPROACHES_TEXT = AuthoringUIResources.assignmentApproaches_text; 

	public static final String AUTHORS_TEXT = AuthoringUIResources.authors_text; 

	public static final String BACKGROUND_TEXT = AuthoringUIResources.background_text; 

	public static final String BASE_ELEMENT_TEXT = AuthoringUIResources.baseElement_text; 

	public static final String BASE_GUIDANCE_TEXT = AuthoringUIResources.baseGuidance_text; 

	public static final String BRIEF_DESCRIPTION_TEXT = AuthoringUIResources.briefDescription_text; 

	public static final String BRIEF_OUTLINE_TEXT = AuthoringUIResources.briefOutline_text; 

	public static final String CHANGE_DATE_TEXT = AuthoringUIResources.changeDate_text; 

	public static final String CHANGE_DESCRIPTION_TEXT = AuthoringUIResources.changeDescription_text; 

	public static final String CHECK_ITEMS_TEXT = AuthoringUIResources.checkItems_text; 

	public static final String CONTRIBUTES_TEXT = AuthoringUIResources.contributes_text; 
	
	public static final String LOCAL_CONTRIBUTES_TEXT = AuthoringUIResources.localContributes_text;  

	public static final String COPYRIGHT_TEXT = AuthoringUIResources.copyright_text; 

	public static final String DESCRIPTION_TEXT = AuthoringUIResources.description_text; 

	public static final String EDITOR_TEXT = AuthoringUIResources.editor_text; 

	public static final String EXTENDS_TEXT = AuthoringUIResources.extends_text; 

	public static final String EXTERNAL_DESCRIPTION_TEXT = AuthoringUIResources.externalDescription_text; 

	public static final String EXTERNAL_ID_TEXT = AuthoringUIResources.externalId_text; 

	public static final String GOALS_TEXT = AuthoringUIResources.goals_text; 

	public static final String IMPACT_OF_NOT_HAVING_TEXT = AuthoringUIResources.impactOfNotHaving_text; 

	public static final String KEY_CONSIDERATIONS_TEXT = AuthoringUIResources.keyConsiderations_text; 

	public static final String LEVEL_OF_ADOPTION_TEXT = AuthoringUIResources.levelsOfAdoption_text; 

	public static final String MAIN_DESCRIPTION_TEXT = AuthoringUIResources.mainDescription_text; 

	public static final String NAME_TEXT = AuthoringUIResources.name_text; 

	public static final String NEW_ELEMENT_TEXT = AuthoringUIResources.newElement_text; 

	public static final String NOT_APPLICABLE_TEXT = AuthoringUIResources.notApplicable_text; 

	public static final String NOTATION_TEXT = AuthoringUIResources.notation_text; 

	public static final String PACKAGING_GUIDANCE_TEXT = AuthoringUIResources.packagingGuidance_text; 

	public static final String PRESENTATION_NAME_TEXT = AuthoringUIResources.presentationName_text; 
	
	public static final String LONG_PRESENTATION_NAME_TEXT = AuthoringUIResources.long_presentationName_text;

	public static final String PROBLEM_TEXT = AuthoringUIResources.problem_text; 

	public static final String PURPOSE_TEXT = AuthoringUIResources.purpose_text; 

	public static final String REASON_FOR_NOT_NEEDING_TEXT = AuthoringUIResources.reasonForNotNeeding_text; 

	public static final String REPLACES_TEXT = AuthoringUIResources.replaces_text; 

	public static final String REPRESENTATION_TEXT = AuthoringUIResources.representation_text; 

	public static final String REPRESENTATION_OPTIONS_TEXT = AuthoringUIResources.representationOptions_text; 

	public static final String SKILLS_TEXT = AuthoringUIResources.skills_text; 

	public static final String STEPS_TEXT = AuthoringUIResources.steps_text; 

	public static final String STEP_NAME_TEXT = AuthoringUIResources.steps_name_text; 

	public static final String SYNONYMS_TEXT = AuthoringUIResources.synonyms_text; 

	public static final String ATTACHED_FILE_TEXT = AuthoringUIResources.attachedFile_text; 

	public static final String TYPE_TEXT = AuthoringUIResources.type_text; 

	public static final String VARIABILITY_TYPE_TEXT = AuthoringUIResources.variabilityType_text; 

	public static final String VERSION_TEXT = AuthoringUIResources.version_text; 
	
	public static final String STATES_NAME_TEXT = AuthoringUIResources.states_name_text;
	
	public static final String STATES_WP_STATE_TEXT = AuthoringUIResources.states_wp_state_text;
	
	public static final String STATES_GLOBAL_STATE_TEXT = AuthoringUIResources.states_global_state_text;
	
	public static final String STATES_GLOBAL_NOTES_TEXT = AuthoringUIResources.states_global_notes_text;
	
	public static final String STATES_DES_TEXT = AuthoringUIResources.states_des_text;

	/**
	 * Button labels.
	 */
	public static final String ADD_BUTTON_TEXT = AuthoringUIResources.addButton_text; 

	public static final String ATTACH_BUTTON_TEXT = AuthoringUIResources.attachButton_text; 
	
	public static final String ATTACH_URL_BUTTON_TEXT = AuthoringUIResources.attachUrlButton_text;

	public static final String BROWSE_BUTTON_TEXT = AuthoringUIResources.browseButton_text; 

	public static final String CHANGE_TYPE_BUTTON_TEXT = AuthoringUIResources.changeTypeButton_text; 

	public static final String CLEAR_BUTTON_TEXT = AuthoringUIResources.clearButton_text; 

	public static final String DELETE_BUTTON_TEXT = AuthoringUIResources.deleteButton_text; 

	public static final String DESELECT_BUTTON_TEXT = AuthoringUIResources.deselectButton_text; 

	public static final String DETACH_BUTTON_TEXT = AuthoringUIResources.detachButton_text; 

	public static final String DOWN_BUTTON_TEXT = AuthoringUIResources.downButton_text; 

	public static final String ORDER_BUTTON_TEXT = AuthoringUIResources.orderButton_text; 

	public static final String EDIT_BUTTON_TEXT = AuthoringUIResources.editButton_text; 
	
	public static final String REMOVE_BUTTON_TEXT = AuthoringUIResources.removeButton_text; 

	public static final String SELECT_BUTTON_TEXT = AuthoringUIResources.selectButton_text; 

	public static final String UP_BUTTON_TEXT = AuthoringUIResources.upButton_text; 

	public static final String VIEW_HISTORY_BUTTON_TEXT = AuthoringUIResources.viewHistoryButton_text; 

	public static final String ALWAYS_BUTTON_TEXT = MessageDialogWithToggle.ALWAYS;

	public static final String NEVER_BUTTON_TEXT = MessageDialogWithToggle.NEVER;

	public static final String PROMPT_BUTTON_TEXT = MessageDialogWithToggle.PROMPT;

	public static final String PUBLISH_CATEGORIES_TEXT = AuthoringUIResources.publish_categories_text;
	
	public static final String PUBLISH_PRACTICES_TEXT = AuthoringUIResources.publish_practices_text;
	
	public static final String PUBLISH_PRACTICES_FOR_UDT_TEXT = AuthoringUIResources.publish_practices_for_udt_text;
	
	public static final String STATES_ADD_TEXT = AuthoringUIResources.states_add_text;
	
	public static final String STATES_DELETE_TEXT = AuthoringUIResources.states_delete_text;
	
	public static final String STATES_MANAGE_TEXT = AuthoringUIResources.states_manage_text;
	
	public static final String STATES_ASSIGN_TEXT = AuthoringUIResources.states_assign_text;
	
	public static final String STATES_UNASSIGN_TEXT= AuthoringUIResources.states_unassign_text;
	
}
