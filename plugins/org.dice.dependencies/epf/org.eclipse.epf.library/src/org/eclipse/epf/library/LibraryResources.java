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
package org.eclipse.epf.library;

import org.eclipse.osgi.util.NLS;

public final class LibraryResources extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.epf.library.Resources";//$NON-NLS-1$

	private LibraryResources() {
		// Do not instantiate
	}

	public static String libraryProblemMonitor;
	public static String libUpgradeRequired_err_msg;
	public static String oldToolVersion_err_msg;
	public static String toolVersionMismatch_err_msg;
	public static String unknown_text;
	public static String emptyLibraryPathError_reason;
	public static String missingLibraryFileError_reason;
	public static String unresolvedProxyError_reason;
	public static String configClosureWarning_msg1;
	public static String configClosureWarning_msg2;
	public static String configClosureWarning_msg3;
	public static String loadResourcesError_msg;
	public static String unsupportedGuidanceTypeError_msg;
	public static String convertGuidanceError_msg;
	public static String saveConvertedGuidanceError_msg;
	public static String convertActivityError_title;
	public static String convertActivityError_msg;
	public static String saveConvertedActivityError_msg;
	public static String error_reason;
	public static String unsupportedGuidanceTypeError_reason;
	public static String invalidLibraryPathError_reason;
	public static String couldNotLockLibrary_reason;
	public static String copyingAttachmentsTask_name;
	public static String convertGuidanceDialog_title;
	public static String convertGuidanceDialog_text;
	public static String checklist_text;
	public static String concept_text;
	public static String example_text;
	public static String guideline_text;
	public static String estimate_text;
	public static String estimatingMetric_text;
	public static String estimationConsiderations_text;
	public static String report_text;
	public static String template_text;
	public static String supportingMaterial_text;
	public static String toolMentor_text;
	public static String whitepaper_text;
	public static String termDefinition_text;
	public static String practice_text;
	public static String reusableAsset_text;
	public static String ActivityLayout_primaryTasks_text;
	public static String ActivityLayout_additionalTasks_text;
	public static String ActivityLayout_assistTasks_text;
	public static String ActivityLayout_performAs_text;	
	public static String convertGuidanceError_title;
	public static String loadResourcesError_title;
	public static String loadResourcesError_reason;
	public static String roleDiagramPerforms_text;
	public static String roleDiagramResponsibleFor_text;
	public static String DefaultContentValidator_MSG0;
	public static String DefaultContentValidator_MSG1;
	public static String DefaultContentValidator_MSG4;
	public static String DefaultContentValidator_MSG7;
	public static String DefaultContentValidator_MSG10;
	public static String DefaultContentValidator_MSG11;
	public static String DefaultContentValidator_MSG12;
	public static String DefaultContentValidator_MSG15;
	public static String DefaultContentValidator_MSG17;
	public static String DefaultContentValidator_MSG19;
	public static String ImportExportUtil_MSG8;
	public static String ImportExportUtil_MSG9;
	public static String unknownGuidance_text;
	public static String CopyAttachmentsToNewLocation_log_noContentPath;
	public static String LibraryModificationHelper_cannotUpdate;
	public static String warningDlg_title;
	public static String errorDlg_title;
	public static String errorDlg_saveError;
	public static String roleDiagramFont_name;
	public static String roleDiagramFont_name_win32;
	public static String roleDiagramFont_name_ja;
	public static String roleDiagramFont_name_win32_ja;
	public static String roleDiagramFont_name_ko;
	public static String roleDiagramFont_name_win32_ko;
	public static String roleDiagramFont_name_zh;
	public static String roleDiagramFont_name_win32_zh;
	public static String roleDiagramFont_name_zh_TW;
	public static String roleDiagramFont_name_win32_zh_TW;
	public static String roleDiagramFont_size;
	public static String roleDiagramFont_size_win32;
	public static String roleDiagram_xMargin;
	public static String roleDiagram_xSpacing;
	public static String roleDiagram_ySpacing;
	public static String activityDiagramName;
	public static String activityDetailDiagramName;
	public static String wpDependencyDiagramName;
	public static String colon_with_space;
	
	public static String ElementError_missing_element;
	public static String ElementError_contributor_missing_base;
	public static String ElementError_extender_missing_base;
	public static String ElementError_replacer_missing_base;	
	public static String ElementError_missing_primary_performer;
	public static String ElementError_missing_mandatory_input;
	public static String ElementError_missing_output;
	public static String ElementError_missing_responsible_for_workProduct;
	public static String ElementError_having_multiple_replacers;
	
	public static String WorkProductSlot_text;
	public static String references_unresolved_base_txt;
	public static String configFreeProcessView_title;
	public static String systemCreatedCustomCategory_brief;
	
	public static String UndeclaredDep_MarkerTxt0;
	public static String UndeclaredDep_MarkerTxt1;
	public static String UndeclaredDep_MarkerTxt2;
	
	static {
		NLS.initializeMessages(BUNDLE_NAME, LibraryResources.class);
	}
}