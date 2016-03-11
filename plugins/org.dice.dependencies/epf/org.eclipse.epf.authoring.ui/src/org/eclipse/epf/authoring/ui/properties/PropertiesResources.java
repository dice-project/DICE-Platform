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
package org.eclipse.epf.authoring.ui.properties;

import org.eclipse.osgi.util.NLS;
/**
 * Properties resources
 * 
 * @author Shilpa Toraskar
 * @since 1.0
 */
public final class PropertiesResources extends NLS {

	private static final String BUNDLE_NAME = PropertiesResources.class.getPackage().getName()
								+ ".Resources"; //$NON-NLS-1$
	
	private PropertiesResources() {
		// Do not instantiate
	}

	public static String Process_generalInformationTitle;
	public static String Process_DocumentInformationTitle;
	public static String Activity_guidanceInformationTitle;
	public static String BreakdownElement_optionInformationTitle;
	public static String TaskDescriptor_stepInformationTitle;
	public static String Process_generalInformationDescription;
	public static String Process_documentInformationDescription;
	public static String Descriptor_generalInformationDescription;
	public static String Descriptor_detailInformationDescription;
	public static String Milestone_generalInformationDescription;
	public static String Milestone_detailInformationDescription;
	public static String TaskDescriptor_stepInformationDescription;
	public static String BreakdownElement_generalInformationDescription;
	public static String WorkBreakdownElement_generalInformationDescription;
	public static String Descriptor_generalInformationDescripton;
	public static String Activity_generalInformationDescription;
	public static String BreakdownElement_DocumentInformationDescription;
	public static String Activity_DocumentInformationDescription;
	public static String Descriptor_documentInformationDescription;
	public static String Process_None;
	public static String Process_name;
	public static String Process_name_restore;
	public static String Process_PresentationName;
	public static String Process_PresentationName_restore;
	public static String Process_briefDescription;
	public static String Process_mainDescription;
	public static String Process_purpose;
	public static String Process_prefix;
	public static String BreakdownElement_Type_Name;
	public static String Process_Type_Task;
	public static String Process_Type_Role;
	public static String Process_Type_WorkProduct;
	public static String Linked_Element;	
	public static String WorkProduct_Type;
	public static String Activity_ModelInfo;
	public static String Activity_Type;
	public static String Activity_presentationName;
	public static String Activity_alternatives;
	public static String Activity_howToStaff;
	public static String BreakdownElement_keyConsiderations;
	public static String Process_usageGuidance;
	public static String Activity_RoadmapTitle;
	public static String Activity_RoadmapDescription;
	public static String Activity_GeneralGuidanceTitle;
	public static String Activity_GeneralGuidanceDescription;
	public static String Activity_CommunicationMaterialTitle;
	public static String Activity_CommunicationMaterialDescription;
	public static String Activity_EducationMaterialTitle;
	public static String Activity_EducationMaterialDescription;
	public static String Activity_Selected_Roadmap;
	public static String Activity_Selected_GeneralGuidance;
	public static String Activity_Selected_CommunicationMaterial;
	public static String Activity_Selected_EducationMaterial;
	public static String Activity_DiagramTitle;
	public static String Activity_DiagramDescription;
	public static String Activity_ADImage;
	public static String Activity_ADDImage;
	public static String Activity_WPDImage;
	public static String Activity_UseADImage;
	public static String Activity_UseADDImage;
	public static String Activity_UseWPDImage;
	public static String Activity_Assign;
	
	public static String Activity_WorkRollup;
	public static String Activity_TeamRollup;
	public static String Activity_WorkProductsRollup;
	public static String Activity_WorkRollupDescription;
	public static String Activity_TeamRollupDescription;
	public static String Activity_WorkProductsRollupDescription;
	public static String BreakdownElement_Option_MultipleOcurrance;
	public static String BreakdownElement_Option_Optional;
	public static String BreakdownElement_Option_Planned;
	public static String BreakdownElement_Option_Supressed;
	public static String WorkBreakdownElement_EventDriven;
	public static String WorkBreakdownElement_Ongoing;
	public static String WorkBreakdownElement_Repeatable;
	public static String WorkBreakdownElement_Predecessors;
	public static String WorkBreakdownElement_Dependency;
	public static String WorkBreakdownElement_Dependency_COL_ID_TEXT;
	public static String WorkBreakdownElement_Dependency_COL_PRESENTATION_NAME_TEXT;
	public static String WorkBreakdownElement_Dependency_COL_DEPENDENCY_TEXT;
	public static String WorkBreakdownElement_Dependency_Add;
	public static String WorkBreakdownElement_Dependency_Edit;
	public static String WorkBreakdownElement_Dependency_Remove;
	public static String WorkOrderType_FINISH_TO_START;
	public static String WorkOrderType_FINISH_TO_FINISH;
	public static String WorkOrderType_START_TO_FINISH;
	public static String WorkOrderType_START_TO_START;
	public static String BreakdownElement_Option_Synchronized;
	public static String Descriptor_RefinedDescription;
	public static String TaskDescriptor_Roles_SectionTitle;
	public static String TaskDescriptor_Roles_SectionDescription;
	public static String TaskDescriptor_Roles_Table1;
	public static String TaskDescriptor_Roles_Table2;
	public static String TaskDescriptor_Roles_Table3;
	public static String TaskDescriptor_Selected_Steps;
	public static String TaskDescriptor_StepDialogTitle;
	public static String TaskDescriptor_StepDialogMessage;
	public static String TaskDescriptor_WorkProducts_SectionTitle;
	public static String TaskDescriptor_WorkProducts_SectionDescription;
	public static String TaskDescriptor_WorkProducts_Table1;
	public static String TaskDescriptor_WorkProducts_Table2;
	public static String TaskDescriptor_WorkProducts_Table3;
	public static String TaskDescriptor_WorkProducts_Table4;
	public static String RoleDescriptor_Tasks_SectionTitle;
	public static String RoleDescriptor_Tasks_SectionDescription;
	public static String RoleDescriptor_Tasks_Table1;
	public static String RoleDescriptor_Tasks_Table2;
	public static String RoleDescriptor_Tasks_Table3;
	public static String RoleDescriptor_WorkProducts_SectionTitle;
	public static String RoleDescriptor_WorkProducts_SectionDescription;
	public static String RoleDescriptor_WorkProducts_Table1;
	public static String RoleDescriptor_WorkProducts_Table2;
	public static String WorkProductDescriptor_Roles_SectionTitle;
	public static String WorkProductDescriptor_Roles_SectionDescription;
	public static String WorkProductDescriptor_Roles_Table1;
	public static String WorkProductDescriptor_Roles_Table2;
	public static String WorkProductDescriptor_Type;
	public static String WorkProductDescriptor_ActivityEntryState;
	public static String WorkProductDescriptor_ActivityExitState;
	public static String WPDescriptor_DeliverablePart_SectionTitle;
	public static String WPDescriptor_DeliverablePart_SectionDescription;
	public static String WPDescriptor_DeliverablePart_Table1;
	public static String RoleDescriptor_Team_SectionTitle;
	public static String RoleDescriptor_Team_SectionDescription;
	public static String RoleDescriptor_Team_Table1;
	public static String CompositeRole_Role_SectionTitle;
	public static String CompositeRole_Role_SectionDescription;
	public static String CompositeRole_Role_Table1;
	public static String TeamProfile_Role_SectionTitle;
	public static String TeamProfile_Role_SectionDescription;
	public static String TeamProfile_Role_Table1;
	public static String Milestone_WorkProducts_SectionDescription;
	public static String Milestone_WorkProducts_Table1;
	public static String Process_RichText_CollapseImage;
	public static String Process_RichText_ExpandImage;
	public static String Process_Add;
	public static String Process_AddFromProcess;
	public static String Process_Rename_Message;
	public static String Process_Remove;
	public static String Process_AssignState;
	public static String Process_SelectStateDialog_Message;
	public static String Process_SelectStateDialog_Title;
	public static String Process_SelectStateDialog_Label_Description;
	public static String Process_UnassignState_Body_Text;
	public static String Process_UnassignState_Description;
	public static String Process_Up;
	public static String Process_Down;
	public static String Process_MultipleSelection;
	public static String Process_Button_LinkMethodElement;
	public static String Process_Button_ClearMethodElement;
	public static String Process_InvalidNameTitle;
	public static String Process_InvalidName;
	public static String Process_Rename_Title;
	public static String Process_InvalidPresentationName;
	public static String Process_LinkMethodElementTitle;
	public static String Process_InvalidLinkMethodElement;
	public static String Process_AssignmentInfoTitle;
	public static String Process_TeamAssignError;
	public static String Process_TeamDialogMessage;
	public static String Process_TeamDialogTitle;
	public static String Process_DeliverableAssignError;
	public static String Process_CompositeRoleAssignError;
	public static String Process_predecessors_validNumberMessage;
	public static String Process_predecessors_validNumberTitle;
	public static String Process_NoItems;
	public static String Process_ItemsSelected;
	public static String Process_NoPropertiesAvailable;
	public static String PredecessorDialog_Title;
	public static String PredecessorDialog_HeaderMessage;
	public static String PredecessorDialog_Index;
	public static String PredecessorDialog_PresentationName;
	public static String PredecessorDialog_Dependency;
	public static String PredecessorDialog_PredecessorErrorDialogTitle;
	public static String PredecessorDialog_PredecessorErrorMessage;
	public static String ProcessEditorFormProperties_textEditCell_problem_msg;
	public static String ProcessEditorFormProperties_cellEditor_invalidPredcessor_problem_msg;
	
	public static String true_text;
	public static String false_text;
	
	public static String Process_SyncFree_FontStyle;

	static {
		NLS.initializeMessages(BUNDLE_NAME, PropertiesResources.class);
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