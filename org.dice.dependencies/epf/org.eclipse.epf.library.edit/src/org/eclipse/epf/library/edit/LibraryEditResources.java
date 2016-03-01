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
package org.eclipse.epf.library.edit;

import org.eclipse.osgi.util.NLS;

/**
 * The Library Edit message resource bundle accessor class.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public final class LibraryEditResources extends NLS {

	private static String BUNDLE_NAME = LibraryEditResources.class.getPackage()
			.getName()
			+ ".Resources"; //$NON-NLS-1$

	public static String activity_deep_copy_variability_prompt;

	public static String activity_variability_error_msg;

	public static String activity_variability_error_msg1;

	public static String activity_variability_error_msg2;

	public static String add_to_default_config_dlg_title;

	public static String cannot_copy_or_extend_delivery_process;

	public static String confirmDescriptorsDeletion_msg;

	public static String confirmDescriptorsDeletion_title;

	public static String savingResources_msg;
	
	public static String variability_error_msg;
	
	public static String circular_dependency_error_msg;
	
	public static String variability_element_circular_loop_error_msg;
	
	public static String  replacing_ancestor_error_msg;

	public static String activity_variability_error_msg3;

	public static String apply_pattern_error_msg;

	public static String apply_pattern_error_msg1;

	public static String duplicateFeatureValue;

	public static String selectDescriptorsToDelete_msg;
	
	public static String copyingActivities_msg;

	public static String localReplacementAndDeepCopy_text;

	public static String deepCopy_promptMsg;

	public static String deepCopy_title;

	public static String moveDialog_title;
	
	public static String moveDialog_addRefPluginsText;

	public static String moveDialog_addRefPluginsWarningText;
	
	public static String moveDialog_addRefPluginsWarningText1;
	
	public static String deleteDialog_title;

	public static String errorDialog_title;

	public static String errorDialog_cannotRename;

	public static String deleteReferencesDialog_title;

	public static String deleteReferencesDialog_text;

	public static String resolveNameConflictDialog_title;

	public static String resolveNameConflictDialog_text;

	public static String selectDeliverablesDialog_title;

	public static String selectDeliverablesDialog_text;

	public static String newProcessComponentDialog_title;

	public static String nameLabel_text;

	public static String defaultConfigLabel_text;

	public static String baseProcessLabel_text;

	public static String basedOnProcessesLabel_text;

	public static String selectAssociationLabel_text;

	public static String removeAssociationDialog_text;

	public static String selectTeamsDialog_title;

	public static String selectTeamsDialog_text;

	public static String taskSelectionDialog_title;

	public static String titleLabel_text;

	public static String emptyElementNameError_msg;

	public static String emptyElementNameError_simple_msg;

	public static String duplicateElementNameError_msg;
	public static String duplicateElementNameError_msg2;
	
	public static String duplicateElementNameError_simple_msg;

	public static String invalidElementNameError1_msg;

	public static String invalidElementNameError2_msg;

	public static String invalidElementNameError3_msg;
	
	public static String invalidElementNameError4_msg;
	
	public static String invalidPluginNameError_msg;
	
	public static String filePathNameTooLong_msg;
	
	public static String noConfigError_msg;

	public static String noDeliveryProcessError_msg;

	public static String noDefaultConfigError_msg;

	public static String noBaseProcessError_msg;

	public static String createProcessError_msg;

	public static String createElementError_msg;

	public static String deleteElementError_msg;

	public static String duplicateContentFileError_msg;

	public static String duplicateContentFileError_simple_msg;

	public static String undoCommandError_msg;

	public static String invalidPredecessorError_msg;

	public static String update_outofsynch_msg;

	public static String update_outofsynch_title;

	public static String UserInteractionHelper_errRelationshipExists;

	public static String error_msgWithDetails;

	public static String error_msg;

	public static String Suppression_nameDuplication;

	public static String Suppression_presentationNameDuplication;

	public static String error_reason;

	public static String saveProcessError_reason;

	public static String deleteReferencesError_reason;

	public static String saveFileError_reason;

	public static String invalidReferencesError_reason;

	public static String unresolvedObjectError_reason;

	public static String MethodElementAddCommand_originalNotFoundWarning_msg;

	public static String ActivityAddCommand_originalNotFoundWarning_msg;

	public static String ActivityDropCommand_deepCopy_promptConfigurationMsg;

	public static String creatingProcessComponentTask_name;

	public static String processingReferencesTask_name;

	public static String deletingElementsTask_name;

	public static String checkingReferencesTask_name;

	public static String removingReferencestask_name;

	public static String savingModifiedFilesTask_name;

	public static String checkAffectedResourcesTask_name;

	public static String movingTask_name;

	public static String movingFilesTask_name;

	public static String copyingResourcesTask_name;

	public static String savingFileTask_name;

	public static String element_text;

	public static String createElement_text;

	public static String createProcess_text;

	public static String contributesTo_text;

	public static String localContributesTo_text;

	public static String extends_text;

	public static String replaces_text;
	
	public static String extends_replaces_text;

	public static String localReplaces_text;

	public static String elementType_text;

	public static String deliverables_text;

	public static String contributeToActivity_text;

	public static String ActivityDropCommand_label;

	public static String replaceActivity_text;

	public static String file_text;

	public static String directory_text;

	public static String unresolved_text;

	public static String assists_text;

	public static String unknown_text;

	public static String command_done;

	public static String Util_labelpath_variabilitywithplugin_info;

	public static String Util_labelpath_variability_info;

	public static String ui_ref_delete;

	public static String ui_ref_delete2;

	public static String ui_references;

	public static String ui_workproduct_descriptor_description;

	public static String ui_workproductdescriptor_options;

	public static String ui_workproductdescriptor_outputlabel;

	public static String ui_workproductdescriptor_delete_task;

	public static String ui_UserInteractionHelper_defaultconfigcheck;

	public static String ui_UserInteractionHelper_genericErr_message_text;

	public static String ProcessAutoSynchronizeAction_noDescriptorToSynch;

	public static String util_configurablecomposedadapter_refershingviewer;

	public static String confirm_remove_references_text;

	public static String util_configurablecomposedadapter_unhandled_exception;

	public static String util_ProcessUtil_err_same_breakdown_element;

	public static String ui_UserInteractionHelper_wplistdlg_msg;

	public static String ui_UserInteractionHelper_rolelistdlg_msg;

	public static String util_configurablecomposedadapter_fatalerr;

	public static String ui_UserInteractionHelper_workproducts;

	public static String util_ProcessUtil_err_same_sub_element;

	public static String util_ProcessUtil_err_wrong_element;

	public static String util_ProcessUtil_err_child_element;

	public static String ui_UserInteractionHelper_tasks;

	public static String ui_association_task_selection;

	public static String util_ProcessUtil_childadapter;

	public static String util_ProcessUtil_err_setparent;

	public static String util_tngutil_cannot_edit_resource;

	public static String util_ProcessUtil_contributesto;

	public static String util_ProcessUtil_localContributesto;

	public static String process_extends;

	public static String process_replaces;

	public static String process_localReplaces;

	public static String FilterConstants_disciplines_text;

	public static String FilterConstants_disciplinegroupings_text;

	public static String FilterConstants_whitepapers_text;

	public static String FilterConstants_toolmentors_text;

	public static String FilterConstants_tools_text;

	public static String FilterConstants_roles_text;

	public static String FilterConstants_tasks_text;

	public static String FilterConstants_reusableassets_text;

	public static String FilterConstants_all_text;

	public static String FilterConstants_processes_text;
	
	public static String FilterConstants_activities_text;

	public static String FilterConstants_practices_text;

	public static String FilterConstants_UDTs_text;
	
	public static String FilterConstants_templates_text;

	public static String FilterConstants_workproducts_text;

	public static String FilterConstants_checklists_text;

	public static String FilterConstants_guidelines_text;

	public static String FilterConstants_rolesets_text;

	public static String FilterConstants_guidance_text;

	public static String FilterConstants_concepts_text;

	public static String FilterConstants_examples_text;

	public static String FilterConstants_contentpackages_text;

	public static String FilterConstants_roledescriptors_text;

	public static String FilterConstants_rolesetgropuings_text;

	public static String FilterConstants_taskdescriptors_text;

	public static String FilterConstants_contentelements_text;

	public static String ProcessAutoSynchronizeAction_prepare;

	public static String FilterConstants_workproductdescriptors_text;

	public static String FilterConstants_allcontentelements_text;

	public static String FilterConstants_supportingmaterials_text;

	public static String MethodElementAddCommand_errorSavingFiles;

	public static String FilterConstants_customcategories_text;

	public static String FilterConstants_workproducttypes_text;

	public static String DeleteMethodElementCommand_warningMsg;

	public static String FilterConstants_methodplugins_text;

	public static String UserInteractionHelper_lockedPlugin;

	public static String FilterConstants_domains_text;

	public static String FilterConstants_roadmap_text;

	public static String FilterConstants_reports_text;

	public static String AutoSynchronizeCommand_label;

	public static String ManualSynchronizeCommand_label;

	public static String AutoSynchronizeCommand_sucessful;
	
	public static String SynchronizeCompleteDialog_Title;

	public static String UserInteractionHelper_ProcessPackage_Name;

	public static String newElement_text;
	
	public static String CreateProcessComponentCommand_Message;
	
	public static String DeliveryProcessLabel;
	
	public static String CapabilityPatternLabel;
	
	public static String SortType_Label;

	public static String SortType_Manual;

	public static String SortType_Alphabetic;
	
	public static String SortType_ReverseAlphabetic;
	
	public static String SortType_MethodType;
	
	public static String SpecifyNameText;
	
	public static String scope_defualtName;
	
	public static String scope_PluginsName;
	
	public static String WorkOrderTypeAbbreviation_FINISH_TO_START;
	public static String WorkOrderTypeAbbreviation_FINISH_TO_FINISH;
	public static String WorkOrderTypeAbbreviation_START_TO_FINISH;
	public static String WorkOrderTypeAbbreviation_START_TO_START;

	static {
		NLS.initializeMessages(BUNDLE_NAME, LibraryEditResources.class);
	}
}