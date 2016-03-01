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
package org.eclipse.epf.library.ui;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.impl.ActivityImpl;
import org.eclipse.epf.uma.impl.ArtifactImpl;
import org.eclipse.epf.uma.impl.CapabilityPatternImpl;
import org.eclipse.epf.uma.impl.ChecklistImpl;
import org.eclipse.epf.uma.impl.CompositeRoleImpl;
import org.eclipse.epf.uma.impl.ConceptImpl;
import org.eclipse.epf.uma.impl.ContentPackageImpl;
import org.eclipse.epf.uma.impl.CustomCategoryImpl;
import org.eclipse.epf.uma.impl.DeliverableImpl;
import org.eclipse.epf.uma.impl.DeliveryProcessImpl;
import org.eclipse.epf.uma.impl.DisciplineGroupingImpl;
import org.eclipse.epf.uma.impl.DisciplineImpl;
import org.eclipse.epf.uma.impl.DomainImpl;
import org.eclipse.epf.uma.impl.EstimationConsiderationsImpl;
import org.eclipse.epf.uma.impl.ExampleImpl;
import org.eclipse.epf.uma.impl.GuidelineImpl;
import org.eclipse.epf.uma.impl.IterationImpl;
import org.eclipse.epf.uma.impl.MethodConfigurationImpl;
import org.eclipse.epf.uma.impl.MethodLibraryImpl;
import org.eclipse.epf.uma.impl.MethodPackageImpl;
import org.eclipse.epf.uma.impl.MethodPluginImpl;
import org.eclipse.epf.uma.impl.MilestoneImpl;
import org.eclipse.epf.uma.impl.OutcomeImpl;
import org.eclipse.epf.uma.impl.PhaseImpl;
import org.eclipse.epf.uma.impl.PracticeImpl;
import org.eclipse.epf.uma.impl.ProcessFamilyImpl;
import org.eclipse.epf.uma.impl.ProcessPackageImpl;
import org.eclipse.epf.uma.impl.ReportImpl;
import org.eclipse.epf.uma.impl.ReusableAssetImpl;
import org.eclipse.epf.uma.impl.RoadmapImpl;
import org.eclipse.epf.uma.impl.RoleDescriptorImpl;
import org.eclipse.epf.uma.impl.RoleImpl;
import org.eclipse.epf.uma.impl.RoleSetGroupingImpl;
import org.eclipse.epf.uma.impl.RoleSetImpl;
import org.eclipse.epf.uma.impl.StepImpl;
import org.eclipse.epf.uma.impl.SupportingMaterialImpl;
import org.eclipse.epf.uma.impl.TaskDescriptorImpl;
import org.eclipse.epf.uma.impl.TaskImpl;
import org.eclipse.epf.uma.impl.TeamProfileImpl;
import org.eclipse.epf.uma.impl.TemplateImpl;
import org.eclipse.epf.uma.impl.TermDefinitionImpl;
import org.eclipse.epf.uma.impl.ToolImpl;
import org.eclipse.epf.uma.impl.ToolMentorImpl;
import org.eclipse.epf.uma.impl.WhitepaperImpl;
import org.eclipse.epf.uma.impl.WorkProductDescriptorImpl;
import org.eclipse.epf.uma.impl.WorkProductImpl;
import org.eclipse.epf.uma.impl.WorkProductTypeImpl;

/**
 * Provides access to cached copy of commonly referenced text in the library UI.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class LibraryUIText {

	public static final String TEXT_ACTIVITY = LibraryUIResources.activity_text;

	public static final String TEXT_ARTIFACT = LibraryUIResources.artifact_text;

	public static final String TEXT_CAPABILITY_PATTERN = LibraryUIResources.capabilityPattern_text;

	public static final String TEXT_CHECKLIST = LibraryUIResources.checklist_text;

	public static final String TEXT_CHECK_ITEM = LibraryUIResources.checkItem_text;

	public static final String TEXT_COMPOSITE_ROLE = LibraryUIResources.compositeRole_text;

	public static final String TEXT_CONCEPT = LibraryUIResources.concept_text;

	public static final String TEXT_CONTENT_PACKAGE = LibraryUIResources.contentPackage_text;

	public static final String TEXT_CUSTOM_CATEGORY = LibraryUIResources.customCategory_text;

	public static final String TEXT_DELIVERABLE = LibraryUIResources.deliverable_text;

	public static final String TEXT_DELIVERY_PROCESS = LibraryUIResources.deliveryProcess_text;

	public static final String TEXT_DESCRIPTOR = LibraryUIResources.descriptor_text;

	public static final String TEXT_DISCIPLINE = LibraryUIResources.discipline_text;

	public static final String TEXT_DISCIPLINE_GROUPING = LibraryUIResources.disciplineGrouping_text;

	public static final String TEXT_DOMAIN = LibraryUIResources.domain_text;

	public static final String TEXT_ESTIMATE = LibraryUIResources.example_text;

	public static final String TEXT_ESTIMATING_METRIC = LibraryUIResources.estimatingMetric_text;

	public static final String TEXT_ESTIMATION_CONSIDERATIONS = LibraryUIResources.estimationConsiderations_text;

	public static final String TEXT_EXAMPLE = LibraryUIResources.example_text;

	public static final String TEXT_GUIDANCE = LibraryUIResources.guidance_text;

	public static final String TEXT_UDT = LibraryUIResources.UDT_text;
	
	public static final String TEXT_GUIDELINE = LibraryUIResources.guideline_text;

	public static final String TEXT_ITERATION = LibraryUIResources.iteration_text;

	public static final String TEXT_METHOD_CONFIGURATON = LibraryUIResources.methodConfiguration_text;

	public static final String TEXT_METHOD_CONTENT = LibraryUIResources.methodContent_text;

	public static final String TEXT_METHOD_LIBARARY = LibraryUIResources.methodLibrary_text;

	public static final String TEXT_METHOD_PACKAGE = LibraryUIResources.methodPackage_text;

	public static final String TEXT_METHOD_PLUGIN = LibraryUIResources.methodPlugin_text;

	public static final String TEXT_MILESTONE = LibraryUIResources.milestone_text;

	public static final String TEXT_OUTCOME = LibraryUIResources.outcome_text;

	public static final String TEXT_PHASE = LibraryUIResources.phase_text;

	public static final String TEXT_PRACTICE = LibraryUIResources.practice_text;

	public static final String TEXT_PROCESS = LibraryUIResources.process_text;

	public static final String TEXT_PROCESS_CONTRIBUTION = LibraryUIResources.processContribution_text;

	public static final String TEXT_PROCESS_FAMILY = LibraryUIResources.processFamily_text;

	public static final String TEXT_PROCESS_PACKAGE = LibraryUIResources.processPackage_text;

	public static final String TEXT_REPORT = LibraryUIResources.report_text;

	public static final String TEXT_REUSABLE_ASSET = LibraryUIResources.reusableAsset_text;

	public static final String TEXT_ROADMAP = LibraryUIResources.roadmap_text;

	public static final String TEXT_ROLE = LibraryUIResources.role_text;

	public static final String TEXT_ROLE_DESCRIPTOR = LibraryUIResources.roleDescriptor_text;

	public static final String TEXT_ROLE_SET = LibraryUIResources.roleSet_text;

	public static final String TEXT_ROLE_SET_GROUPING = LibraryUIResources.roleSetGrouping_text;

	public static final String TEXT_STANDARD_CATEGORY = LibraryUIResources.standardCategory_text;

	public static final String TEXT_STEP = LibraryUIResources.step_text;

	public static final String TEXT_SUPPORTING_MATERIAL = LibraryUIResources.supportingMaterial_text;

	public static final String TEXT_TASK = LibraryUIResources.task_text;

	public static final String TEXT_TASK_DESCRIPTOR = LibraryUIResources.taskDescriptor_text;

	public static final String TEXT_TEAM_PROFILE = LibraryUIResources.teamProfile_text;

	public static final String TEXT_TEMPLATE = LibraryUIResources.template_text;

	public static final String TEXT_TERM_DEFINITION = LibraryUIResources.termDefinition_text;

	public static final String TEXT_TOOL = LibraryUIResources.tool_text;

	public static final String TEXT_TOOL_MENTOR = LibraryUIResources.toolMentor_text;

	public static final String TEXT_WHITEPAPER = LibraryUIResources.whitepaper_text;

	public static final String TEXT_WORK_PRODUCT = LibraryUIResources.workProduct_text;

	public static final String TEXT_WORK_PRODUCT_DESCRIPTOR = LibraryUIResources.workProductDescriptor_text;

	public static final String TEXT_WORK_PRODUCT_ELEMENT = LibraryUIResources.workProductElement_text;

	public static final String TEXT_WORK_PRODUCT_TYPE = LibraryUIResources.workProductType_text;

	public static final String TEXT_ACTIVITY_PLURAL = LibraryUIResources.activity_text_plural;

	public static final String TEXT_ARTIFACT_PLURAL = LibraryUIResources.artifact_text_plural;

	public static final String TEXT_CAPABILITY_PATTERN_PLURAL = LibraryUIResources.capabilityPattern_text_plural;

	public static final String TEXT_CHECKLIST_PLURAL = LibraryUIResources.checklist_text_plural;

	public static final String TEXT_CHECK_ITEM_PLURAL = LibraryUIResources.checkItem_text_plural;

	public static final String TEXT_COMPOSITE_ROLE_PLURAL = LibraryUIResources.compositeRole_text_plural;

	public static final String TEXT_CONCEPT_PLURAL = LibraryUIResources.concept_text_plural;

	public static final String TEXT_CONTENT_PACKAGE_PLURAL = LibraryUIResources.contentPackage_text_plural;

	public static final String TEXT_CUSTOM_CATEGORY_PLURAL = LibraryUIResources.customCategory_text_plural;

	public static final String TEXT_DELIVERABLE_PLURAL = LibraryUIResources.deliverable_text_plural;

	public static final String TEXT_DELIVERY_PROCESS_PLURAL = LibraryUIResources.deliveryProcess_text_plural;

	public static final String TEXT_DESCRIPTOR_PLURAL = LibraryUIResources.descriptor_text_plural;

	public static final String TEXT_DISCIPLINE_PLURAL = LibraryUIResources.discipline_text_plural;

	public static final String TEXT_DISCIPLINE_GROUPING_PLURAL = LibraryUIResources.disciplineGrouping_text_plural;

	public static final String TEXT_DOMAIN_PLURAL = LibraryUIResources.domain_text_plural;

	public static final String TEXT_ESTIMATE_PLURAL = LibraryUIResources.example_text_plural;

	public static final String TEXT_ESTIMATING_METRIC_PLURAL = LibraryUIResources.estimatingMetric_text_plural;

	public static final String TEXT_ESTIMATION_CONSIDERATIONS_PLURAL = LibraryUIResources.estimationConsiderations_text_plural;

	public static final String TEXT_EXAMPLE_PLURAL = LibraryUIResources.example_text_plural;

	public static final String TEXT_GUIDANCE_PLURAL = LibraryUIResources.guidance_text_plural;

	public static final String TEXT_GUIDELINE_PLURAL = LibraryUIResources.guideline_text_plural;

	public static final String TEXT_ITERATION_PLURAL = LibraryUIResources.iteration_text_plural;

	public static final String TEXT_METHOD_CONFIGURATON_PLURAL = LibraryUIResources.methodConfiguration_text_plural;

	public static final String TEXT_METHOD_CONTENT_PLURAL = LibraryUIResources.methodContent_text_plural;

	public static final String TEXT_METHOD_LIBARARY_PLURAL = LibraryUIResources.methodLibrary_text_plural;

	public static final String TEXT_METHOD_PACKAGE_PLURAL = LibraryUIResources.methodPackage_text_plural;

	public static final String TEXT_METHOD_PLUGIN_PLURAL = LibraryUIResources.methodPlugin_text_plural;

	public static final String TEXT_MILESTONE_PLURAL = LibraryUIResources.milestone_text_plural;

	public static final String TEXT_OUTCOME_PLURAL = LibraryUIResources.outcome_text_plural;

	public static final String TEXT_PHASE_PLURAL = LibraryUIResources.phase_text_plural;

	public static final String TEXT_PRACTICE_PLURAL = LibraryUIResources.practice_text_plural;

	public static final String TEXT_PROCESS_PLURAL = LibraryUIResources.process_text_plural;

	public static final String TEXT_PROCESS_CONTRIBUTION_PLURAL = LibraryUIResources.processContribution_text_plural;

	public static final String TEXT_PROCESS_FAMILY_PLURAL = LibraryUIResources.processFamily_text_plural;

	public static final String TEXT_PROCESS_PACKAGE_PLURAL = LibraryUIResources.processPackage_text_plural;

	public static final String TEXT_REPORT_PLURAL = LibraryUIResources.report_text_plural;

	public static final String TEXT_REUSABLE_ASSET_PLURAL = LibraryUIResources.reusableAsset_text_plural;

	public static final String TEXT_ROADMAP_PLURAL = LibraryUIResources.roadmap_text_plural;

	public static final String TEXT_ROLE_PLURAL = LibraryUIResources.role_text_plural;

	public static final String TEXT_ROLE_DESCRIPTOR_PLURAL = LibraryUIResources.roleDescriptor_text_plural;

	public static final String TEXT_ROLE_SET_PLURAL = LibraryUIResources.roleSet_text_plural;

	public static final String TEXT_ROLE_SET_GROUPING_PLURAL = LibraryUIResources.roleSetGrouping_text_plural;

	public static final String TEXT_STANDARD_CATEGORY_PLURAL = LibraryUIResources.standardCategory_text_plural;

	public static final String TEXT_STEP_PLURAL = LibraryUIResources.step_text_plural;

	public static final String TEXT_SUPPORTING_MATERIAL_PLURAL = LibraryUIResources.supportingMaterial_text_plural;

	public static final String TEXT_TASK_PLURAL = LibraryUIResources.task_text_plural;

	public static final String TEXT_TASK_DESCRIPTOR_PLURAL = LibraryUIResources.taskDescriptor_text_plural;

	public static final String TEXT_TEAM_PROFILE_PLURAL = LibraryUIResources.teamProfile_text_plural;

	public static final String TEXT_TEMPLATE_PLURAL = LibraryUIResources.template_text_plural;

	public static final String TEXT_TERM_DEFINITION_PLURAL = LibraryUIResources.termDefinition_text_plural;

	public static final String TEXT_TOOL_PLURAL = LibraryUIResources.tool_text_plural;

	public static final String TEXT_TOOL_MENTOR_PLURAL = LibraryUIResources.toolMentor_text_plural;

	public static final String TEXT_WHITEPAPER_PLURAL = LibraryUIResources.whitepaper_text_plural;

	public static final String TEXT_WORK_PRODUCT_PLURAL = LibraryUIResources.workProduct_text_plural;

	public static final String TEXT_WORK_PRODUCT_DESCRIPTOR_PLURAL = LibraryUIResources.workProductDescriptor_text_plural;

	public static final String TEXT_WORK_PRODUCT_ELEMENT_PLURAL = LibraryUIResources.workProductElement_text_plural;

	public static final String TEXT_WORK_PRODUCT_TYPE_PLURAL = LibraryUIResources.workProductType_text_plural;

	private static final Map<Class, String> ELEMENT_TEXT = new HashMap<Class, String>();

	private static final Map<Class, String> ELEMENT_TEXT_PLURAL = new HashMap<Class, String>();

	static {
		ELEMENT_TEXT.put(ActivityImpl.class, TEXT_ACTIVITY);
		ELEMENT_TEXT.put(ArtifactImpl.class, TEXT_ARTIFACT);
		ELEMENT_TEXT.put(CapabilityPatternImpl.class, TEXT_CAPABILITY_PATTERN);
		ELEMENT_TEXT.put(ChecklistImpl.class, TEXT_CHECKLIST);
		ELEMENT_TEXT.put(CompositeRoleImpl.class, TEXT_COMPOSITE_ROLE);
		ELEMENT_TEXT.put(ConceptImpl.class, TEXT_CONCEPT);
		ELEMENT_TEXT.put(ContentPackageImpl.class, TEXT_CONTENT_PACKAGE);
		ELEMENT_TEXT.put(CustomCategoryImpl.class, TEXT_CUSTOM_CATEGORY);
		ELEMENT_TEXT.put(DeliverableImpl.class, TEXT_DELIVERABLE);
		ELEMENT_TEXT.put(DeliveryProcessImpl.class, TEXT_DELIVERY_PROCESS);
		ELEMENT_TEXT.put(DisciplineImpl.class, TEXT_DISCIPLINE);
		ELEMENT_TEXT
				.put(DisciplineGroupingImpl.class, TEXT_DISCIPLINE_GROUPING);
		ELEMENT_TEXT.put(DomainImpl.class, TEXT_DOMAIN);
		ELEMENT_TEXT.put(EstimationConsiderationsImpl.class,
				TEXT_ESTIMATION_CONSIDERATIONS);
		ELEMENT_TEXT.put(ExampleImpl.class, TEXT_EXAMPLE);
		ELEMENT_TEXT.put(GuidelineImpl.class, TEXT_GUIDELINE);
		ELEMENT_TEXT.put(IterationImpl.class, TEXT_ITERATION);
		ELEMENT_TEXT.put(MethodConfigurationImpl.class,
				TEXT_METHOD_CONFIGURATON);
		ELEMENT_TEXT.put(MethodLibraryImpl.class, TEXT_METHOD_LIBARARY);
		ELEMENT_TEXT.put(MethodPackageImpl.class, TEXT_METHOD_PACKAGE);
		ELEMENT_TEXT.put(MethodPluginImpl.class, TEXT_METHOD_PLUGIN);
		ELEMENT_TEXT.put(MilestoneImpl.class, TEXT_MILESTONE);
		ELEMENT_TEXT.put(OutcomeImpl.class, TEXT_OUTCOME);
		ELEMENT_TEXT.put(PhaseImpl.class, TEXT_PHASE);
		ELEMENT_TEXT.put(PracticeImpl.class, TEXT_PRACTICE);
		ELEMENT_TEXT.put(ProcessFamilyImpl.class, TEXT_PROCESS_FAMILY);
		ELEMENT_TEXT.put(ProcessPackageImpl.class, TEXT_PROCESS_PACKAGE);
		ELEMENT_TEXT.put(ReportImpl.class, TEXT_REPORT);
		ELEMENT_TEXT.put(ReusableAssetImpl.class, TEXT_REUSABLE_ASSET);
		ELEMENT_TEXT.put(RoadmapImpl.class, TEXT_ROADMAP);
		ELEMENT_TEXT.put(RoleImpl.class, TEXT_ROLE);
		ELEMENT_TEXT.put(RoleDescriptorImpl.class, TEXT_ROLE_DESCRIPTOR);
		ELEMENT_TEXT.put(RoleSetImpl.class, TEXT_ROLE_SET);
		ELEMENT_TEXT.put(RoleSetGroupingImpl.class, TEXT_ROLE_SET_GROUPING);
		ELEMENT_TEXT.put(StepImpl.class, TEXT_STEP);
		ELEMENT_TEXT
				.put(SupportingMaterialImpl.class, TEXT_SUPPORTING_MATERIAL);
		ELEMENT_TEXT.put(TaskImpl.class, TEXT_TASK);
		ELEMENT_TEXT.put(TaskDescriptorImpl.class, TEXT_TASK_DESCRIPTOR);
		ELEMENT_TEXT.put(TeamProfileImpl.class, TEXT_TEAM_PROFILE);
		ELEMENT_TEXT.put(TemplateImpl.class, TEXT_TEMPLATE);
		ELEMENT_TEXT.put(TermDefinitionImpl.class, TEXT_TERM_DEFINITION);
		ELEMENT_TEXT.put(TemplateImpl.class, TEXT_TEMPLATE);
		ELEMENT_TEXT.put(ToolImpl.class, TEXT_TOOL);
		ELEMENT_TEXT.put(ToolMentorImpl.class, TEXT_TOOL_MENTOR);
		ELEMENT_TEXT.put(WhitepaperImpl.class, TEXT_WHITEPAPER);
		ELEMENT_TEXT.put(WorkProductImpl.class, TEXT_WORK_PRODUCT);
		ELEMENT_TEXT.put(WorkProductDescriptorImpl.class,
				TEXT_WORK_PRODUCT_DESCRIPTOR);
		ELEMENT_TEXT.put(WorkProductTypeImpl.class, TEXT_WORK_PRODUCT_TYPE);

		// plural
		ELEMENT_TEXT_PLURAL.put(ActivityImpl.class, TEXT_ACTIVITY_PLURAL);
		ELEMENT_TEXT_PLURAL.put(ArtifactImpl.class, TEXT_ARTIFACT_PLURAL);
		ELEMENT_TEXT_PLURAL.put(CapabilityPatternImpl.class,
				TEXT_CAPABILITY_PATTERN_PLURAL);
		ELEMENT_TEXT_PLURAL.put(ChecklistImpl.class, TEXT_CHECKLIST_PLURAL);
		ELEMENT_TEXT_PLURAL.put(CompositeRoleImpl.class,
				TEXT_COMPOSITE_ROLE_PLURAL);
		ELEMENT_TEXT_PLURAL.put(ConceptImpl.class, TEXT_CONCEPT_PLURAL);
		ELEMENT_TEXT_PLURAL.put(ContentPackageImpl.class,
				TEXT_CONTENT_PACKAGE_PLURAL);
		ELEMENT_TEXT_PLURAL.put(CustomCategoryImpl.class,
				TEXT_CUSTOM_CATEGORY_PLURAL);
		ELEMENT_TEXT_PLURAL.put(DeliverableImpl.class, TEXT_DELIVERABLE_PLURAL);
		ELEMENT_TEXT_PLURAL.put(DeliveryProcessImpl.class,
				TEXT_DELIVERY_PROCESS_PLURAL);
		ELEMENT_TEXT_PLURAL.put(DisciplineImpl.class, TEXT_DISCIPLINE_PLURAL);
		ELEMENT_TEXT_PLURAL.put(DisciplineGroupingImpl.class,
				TEXT_DISCIPLINE_GROUPING_PLURAL);
		ELEMENT_TEXT_PLURAL.put(DomainImpl.class, TEXT_DOMAIN_PLURAL);
		ELEMENT_TEXT_PLURAL.put(EstimationConsiderationsImpl.class,
				TEXT_ESTIMATION_CONSIDERATIONS_PLURAL);
		ELEMENT_TEXT_PLURAL.put(ExampleImpl.class, TEXT_EXAMPLE_PLURAL);
		ELEMENT_TEXT_PLURAL.put(GuidelineImpl.class, TEXT_GUIDELINE_PLURAL);
		ELEMENT_TEXT_PLURAL.put(IterationImpl.class, TEXT_ITERATION_PLURAL);
		ELEMENT_TEXT_PLURAL.put(MethodConfigurationImpl.class,
				TEXT_METHOD_CONFIGURATON_PLURAL);
		ELEMENT_TEXT_PLURAL.put(MethodLibraryImpl.class,
				TEXT_METHOD_LIBARARY_PLURAL);
		ELEMENT_TEXT_PLURAL.put(MethodPackageImpl.class,
				TEXT_METHOD_PACKAGE_PLURAL);
		ELEMENT_TEXT_PLURAL.put(MethodPluginImpl.class,
				TEXT_METHOD_PLUGIN_PLURAL);
		ELEMENT_TEXT_PLURAL.put(MilestoneImpl.class, TEXT_MILESTONE_PLURAL);
		ELEMENT_TEXT_PLURAL.put(OutcomeImpl.class, TEXT_OUTCOME_PLURAL);
		ELEMENT_TEXT_PLURAL.put(PhaseImpl.class, TEXT_PHASE_PLURAL);
		ELEMENT_TEXT_PLURAL.put(PracticeImpl.class, TEXT_PRACTICE_PLURAL);
		ELEMENT_TEXT_PLURAL.put(ProcessFamilyImpl.class,
				TEXT_PROCESS_FAMILY_PLURAL);
		ELEMENT_TEXT_PLURAL.put(ProcessPackageImpl.class,
				TEXT_PROCESS_PACKAGE_PLURAL);
		ELEMENT_TEXT_PLURAL.put(ReportImpl.class, TEXT_REPORT_PLURAL);
		ELEMENT_TEXT_PLURAL.put(ReusableAssetImpl.class,
				TEXT_REUSABLE_ASSET_PLURAL);
		ELEMENT_TEXT_PLURAL.put(RoadmapImpl.class, TEXT_ROADMAP_PLURAL);
		ELEMENT_TEXT_PLURAL.put(RoleImpl.class, TEXT_ROLE_PLURAL);
		ELEMENT_TEXT_PLURAL.put(RoleDescriptorImpl.class,
				TEXT_ROLE_DESCRIPTOR_PLURAL);
		ELEMENT_TEXT_PLURAL.put(RoleSetImpl.class, TEXT_ROLE_SET_PLURAL);
		ELEMENT_TEXT_PLURAL.put(RoleSetGroupingImpl.class,
				TEXT_ROLE_SET_GROUPING_PLURAL);
		ELEMENT_TEXT_PLURAL.put(StepImpl.class, TEXT_STEP_PLURAL);
		ELEMENT_TEXT_PLURAL.put(SupportingMaterialImpl.class,
				TEXT_SUPPORTING_MATERIAL_PLURAL);
		ELEMENT_TEXT_PLURAL.put(TaskImpl.class, TEXT_TASK_PLURAL);
		ELEMENT_TEXT_PLURAL.put(TaskDescriptorImpl.class,
				TEXT_TASK_DESCRIPTOR_PLURAL);
		ELEMENT_TEXT_PLURAL
				.put(TeamProfileImpl.class, TEXT_TEAM_PROFILE_PLURAL);
		ELEMENT_TEXT_PLURAL.put(TemplateImpl.class, TEXT_TEMPLATE_PLURAL);
		ELEMENT_TEXT_PLURAL.put(TermDefinitionImpl.class,
				TEXT_TERM_DEFINITION_PLURAL);
		ELEMENT_TEXT_PLURAL.put(TemplateImpl.class, TEXT_TEMPLATE_PLURAL);
		ELEMENT_TEXT_PLURAL.put(ToolImpl.class, TEXT_TOOL_PLURAL);
		ELEMENT_TEXT_PLURAL.put(ToolMentorImpl.class, TEXT_TOOL_MENTOR_PLURAL);
		ELEMENT_TEXT_PLURAL.put(WhitepaperImpl.class, TEXT_WHITEPAPER_PLURAL);
		ELEMENT_TEXT_PLURAL
				.put(WorkProductImpl.class, TEXT_WORK_PRODUCT_PLURAL);
		ELEMENT_TEXT_PLURAL.put(WorkProductDescriptorImpl.class,
				TEXT_WORK_PRODUCT_DESCRIPTOR_PLURAL);
		ELEMENT_TEXT_PLURAL.put(WorkProductTypeImpl.class,
				TEXT_WORK_PRODUCT_TYPE_PLURAL);
	}

	/**
	 * Gets the display name for a method element.
	 * 
	 * @param element
	 *            a method element
	 * @param plural
	 *            if <code>true</code>, return the plural form of the display
	 *            name
	 */
	public static String getUIText(MethodElement element, boolean plural) {
		if (plural) {
			String label = (String) ELEMENT_TEXT_PLURAL.get(element.getClass());
			return (label == null) ? element.getName() : label;
		} else {
			String label = (String) ELEMENT_TEXT.get(element.getClass());
			return (label == null) ? element.getName() : label;
		}
	}

	/**
	 * Gets the display name for a method element.
	 * 
	 * @param element
	 *            a method element
	 */
	public static String getUIText(MethodElement element) {
		return getUIText(element, false);
	}

	/**
	 * Gets the display name for a method element in lower case.
	 * <p>
	 * Note: This method does not applies to the German language since it has no
	 * distinction between a upper case and lower case noun.
	 * 
	 * @param element
	 *            a method element
	 * @param plural
	 *            if <code>true</code>, return the plural form of the display
	 *            name
	 */
	public static String getUITextLower(MethodElement element, boolean plural) {
		String label;
		if (plural) {
			label = (String) ELEMENT_TEXT_PLURAL.get(element.getClass());
			label = (label == null) ? element.getName() : label;
		} else {
			label = (String) ELEMENT_TEXT.get(element.getClass());
			label = (label == null) ? element.getName() : label;
		}

		return StrUtil.toLower(label);
	}

	/**
	 * Gets the display name for a method element in lower case.
	 * <p>
	 * Note: This method does not applies to the German language since it has no
	 * distinction between a upper case and lower case noun.
	 * 
	 * @param element
	 *            a method element
	 */
	public static String getUITextLower(MethodElement element) {
		return getUITextLower(element, false);
	}

}
