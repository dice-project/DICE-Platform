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
package org.eclipse.epf.uma.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.CapabilityPattern;
import org.eclipse.epf.uma.Checklist;
import org.eclipse.epf.uma.Concept;
import org.eclipse.epf.uma.ContentElement;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.Discipline;
import org.eclipse.epf.uma.Example;
import org.eclipse.epf.uma.FulfillableElement;
import org.eclipse.epf.uma.Guideline;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodPackage;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.Report;
import org.eclipse.epf.uma.ReusableAsset;
import org.eclipse.epf.uma.Roadmap;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.RoleSet;
import org.eclipse.epf.uma.Section;
import org.eclipse.epf.uma.SupportingMaterial;
import org.eclipse.epf.uma.Task;
import org.eclipse.epf.uma.Template;
import org.eclipse.epf.uma.Tool;
import org.eclipse.epf.uma.ToolMentor;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.WorkBreakdownElement;
import org.eclipse.epf.uma.WorkOrder;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.WorkProductDescriptor;
import org.eclipse.epf.uma.ecore.impl.MultiResourceEObject;
import org.eclipse.epf.uma.ecore.util.OppositeFeature;

/**
 * A helper class for navigating and querying bidirectional associations among
 * the model objects.
 * 
 * @author Phong Nguyen Le
 * @author Kelvin Low
 * @since 1.0
 */
public final class AssociationHelper {

	/**
	 * An opposite feature used for retrieving the disciplines which a task is
	 * categorized under.
	 */
	public static final OppositeFeature Task_Disciplines = new OppositeFeature(
			Task.class,
			"Task_Disciplines", UmaPackage.eINSTANCE.getDiscipline_Tasks(), true); //$NON-NLS-1$

	/**
	 * An opposite feature used for retrieving the task descriptors which are
	 * created from a specific task.
	 */
	public static final OppositeFeature Task_TaskDescriptors = new OppositeFeature(
			Task.class,
			"Task_TaskDescriptors", UmaPackage.eINSTANCE.getTaskDescriptor_Task(), true, false); //$NON-NLS-1$

	/**
	 * An opposite feature used for retrieving the domains which a work product
	 * is categorized under.
	 */
	public static final OppositeFeature WorkProduct_Domains = new OppositeFeature(
			WorkProduct.class,
			"WorkProduct_Domains", UmaPackage.eINSTANCE.getDomain_WorkProducts(), true); //$NON-NLS-1$

	/**
	 * An opposite feature used for retrieving the tasks which produce a work
	 * product as an output.
	 */
	public static final OppositeFeature WorkProduct_OutputFrom_Tasks = new OppositeFeature(
			WorkProduct.class,
			"WorkProduct_OutputFrom_Tasks", UmaPackage.eINSTANCE.getTask_Output(), true); //$NON-NLS-1$

	/**
	 * An opposite feature used for retrieving the tasks which use a work
	 * product as a mandatory input.
	 */
	public static final OppositeFeature WorkProduct_MandatoryInputTo_Tasks = new OppositeFeature(
			WorkProduct.class,
			"WorkProduct_InputTo_Tasks", UmaPackage.eINSTANCE.getTask_MandatoryInput(), true); //$NON-NLS-1$

	/**
	 * An opposite feature used for retrieving the work product types which a
	 * work product is categorized under.
	 */
	public static final OppositeFeature WorkProduct_WorkProductTypes = new OppositeFeature(
			WorkProduct.class,
			"WorkProduct_WorkProductTypes", UmaPackage.eINSTANCE.getWorkProductType_WorkProducts(), true); //$NON-NLS-1$

	/**
	 * An opposite feature used for retrieving the responsible roles for a work
	 * product.
	 */
	public static final OppositeFeature WorkProduct_ResponsibleRoles = new OppositeFeature(
			WorkProduct.class,
			"WorkProduct_ResponsibleRoles", UmaPackage.eINSTANCE.getRole_ResponsibleFor(), true); //$NON-NLS-1$

	/**
	 * An opposite feature used for retrieving the deliverables which a work
	 * product is contained in.
	 */
	public static final OppositeFeature WorkProduct_Deliverables = new OppositeFeature(
			WorkProduct.class,
			"WorkProduct_Deliverables", UmaPackage.eINSTANCE.getDeliverable_DeliveredWorkProducts(), true); //$NON-NLS-1$

	/**
	 * An opposite feature used for retrieving the tasks that use a work product
	 * as an optional input.
	 */
	public static final OppositeFeature WorkProduct_OptionalInputTo_Tasks = new OppositeFeature(
			WorkProduct.class,
			"WorkProduct_OptionalInputTo_Tasks", UmaPackage.eINSTANCE.getTask_OptionalInput(), true); //$NON-NLS-1$

	/**
	 * An opposite feature used for retrieving the work product descriptors
	 * which are created from a work product.
	 */
	public static final OppositeFeature WorkProduct_WorkProductDescriptors = new OppositeFeature(
			WorkProduct.class,
			"WorkProduct_WorkProductDescriptors", UmaPackage.eINSTANCE.getWorkProductDescriptor_WorkProduct(), true, false); //$NON-NLS-1$

	/**
	 * An opposite feature used for retrieving the primary tasks of a role.
	 */
	public static final OppositeFeature Role_Primary_Tasks = new OppositeFeature(
			Role.class,
			"Role_Primary_Tasks", UmaPackage.eINSTANCE.getTask_PerformedBy(), true); //$NON-NLS-1$

	/**
	 * An opposite feature used for retrieving the secondary tasks of a role.
	 */
	public static final OppositeFeature Role_Secondary_Tasks = new OppositeFeature(
			Role.class,
			"Role_Secondary_Tasks", UmaPackage.eINSTANCE.getTask_AdditionallyPerformedBy(), true); //$NON-NLS-1$

	/**
	 * An opposite feature used for retrieving the role sets which a role is
	 * categorized under.
	 */
	public static final OppositeFeature Role_RoleSets = new OppositeFeature(
			Role.class,
			"Role_RoleSets", UmaPackage.eINSTANCE.getRoleSet_Roles(), true); //$NON-NLS-1$

	/**
	 * An opposite feature used for retrieving the composite roles which
	 * includes a role.
	 */
	public static final OppositeFeature Role_CompositeRoles = new OppositeFeature(
			Role.class,
			"Role_CompositeRoles", UmaPackage.eINSTANCE.getCompositeRole_AggregatedRoles(), true); //$NON-NLS-1$

	/**
	 * An opposite feature used for retrieving the role descriptors which are
	 * created from a role.
	 */
	public static final OppositeFeature Role_RoleDescriptors = new OppositeFeature(
			Role.class,
			"Role_RoleDescriptors", UmaPackage.eINSTANCE.getRoleDescriptor_Role(), true, false); //$NON-NLS-1$

	/**
	 * An opposite feature used for retrieving the content elements which a
	 * checklist is associated with.
	 */
	public static final OppositeFeature Checklist_ContentElements = new OppositeFeature(
			Checklist.class,
			"Checklist_ContentElements", UmaPackage.eINSTANCE.getContentElement_Checklists(), true); //$NON-NLS-1$

	/**
	 * An opposite feature used for retrieving the activities which a checklist is
	 * associated with.
	 */
	public static final OppositeFeature Checklist_BreakdownElements = new OppositeFeature(
			Checklist.class,
			"Checklist_BreakdownElements", UmaPackage.eINSTANCE.getBreakdownElement_Checklists(), true); //$NON-NLS-1$

	/**
	 * An opposite feature used for retrieving the activities which a concept is
	 * associated with.
	 */
	public static final OppositeFeature Concept_BreakdownElements = new OppositeFeature(
			Concept.class,
			"Concept_BreakdownElements", UmaPackage.eINSTANCE.getBreakdownElement_Concepts(), true, false); //$NON-NLS-1$

	/**
	 * An opposite feature used for retrieving the activities which an example is
	 * associated with.
	 */
	public static final OppositeFeature Example_BreakdownElements = new OppositeFeature(
			Concept.class,
			"Example_BreakdownElements", UmaPackage.eINSTANCE.getBreakdownElement_Examples(), true, false); //$NON-NLS-1$

	/**
	 * An opposite feature used for retrieving the activities which a ReusableAsset is
	 * associated with.
	 */
	public static final OppositeFeature ReusableAsset_BreakdownElements = new OppositeFeature(
			Concept.class,
			"ReusableAsset_BreakdownElements", UmaPackage.eINSTANCE.getBreakdownElement_ReusableAssets(), true, false); //$NON-NLS-1$


	/**
	 * An opposite feature used for retrieving the content elements which a
	 * concept is associated with.
	 */
	public static final OppositeFeature Concept_ContentElements = new OppositeFeature(
			Concept.class,
			"Concept_ContentElements", UmaPackage.eINSTANCE.getContentElement_ConceptsAndPapers(), true); //$NON-NLS-1$

	/**
	 * An opposite feature used for retrieving the content elements which an
	 * example is associated with.
	 */
	public static final OppositeFeature Example_ContentElements = new OppositeFeature(
			Example.class,
			"Example_ContentElements", UmaPackage.eINSTANCE.getContentElement_Examples(), true); //$NON-NLS-1$

	/**
	 * An opposite feature used for retrieving the content elements which a
	 * report is associated with.
	 */
	public static final OppositeFeature Report_WorkProducts = new OppositeFeature(
			Report.class,
			"Report_WorkProducts", UmaPackage.eINSTANCE.getWorkProduct_Reports(), true); //$NON-NLS-1$

	/**
	 * An opposite feature used for retrieving the tasks which a
	 * EstimationConsiderations is associated with.
	 */
	public static final OppositeFeature EstimationConsiderations_Tasks = new OppositeFeature(
			Report.class,
			"EstimationConsiderations_Tasks", UmaPackage.eINSTANCE.getTask_EstimationConsiderations(), true); //$NON-NLS-1$
	
	/**
	 * An opposite feature used for retrieving the work products which a
	 * EstimationConsiderations is associated with.
	 */
	public static final OppositeFeature EstimationConsiderations_WorkProducts = new OppositeFeature(
			Report.class,
			"EstimationConsiderations_WorkProducts", UmaPackage.eINSTANCE.getWorkProduct_EstimationConsiderations(), true); //$NON-NLS-1$

	/**
	 * An opposite feature used for retrieving the content elements which a
	 * reusable asset is associated with.
	 */
	public static final OppositeFeature ReusableAsset_ContentElements = new OppositeFeature(
			ReusableAsset.class,
			"ReusableAsset_ContentElements", UmaPackage.eINSTANCE.getContentElement_Assets(), true); //$NON-NLS-1$

	/**
	 * An opposite feature used for retrieving the activities which a roadmap is
	 * associated with.
	 */
	public static final OppositeFeature Roadmap_Activites = new OppositeFeature(
			Roadmap.class,
			"Roadmap_Activites", UmaPackage.eINSTANCE.getActivity_Roadmaps(), true); //$NON-NLS-1$

	
	/**
	 * An opposite feature used for retrieving the fulfills elements which a workproduct is
	 * associated with.
	 */
	public static final OppositeFeature FulFills_FullFillableElements = new OppositeFeature(
			FulfillableElement.class,
			"FulFills_FullFillableElements", UmaPackage.eINSTANCE.getFulfillableElement_Fulfills(), true); //$NON-NLS-1$

	/**
	 * An opposite feature used for retrieving the activities which a supporting
	 * material is associated with.
	 */
	public static final OppositeFeature SupportingMaterial_BreakdownElements = new OppositeFeature(
			SupportingMaterial.class,
			"SupportingMaterial_BreakdownElements", UmaPackage.eINSTANCE.getBreakdownElement_SupportingMaterials(), true, false); //$NON-NLS-1$

	/**
	 * An opposite feature used for retrieving the content elements which a
	 * supporting material is associated with.
	 */
	public static final OppositeFeature SupportingMaterial_ContentElements = new OppositeFeature(
			SupportingMaterial.class,
			"SupportingMaterial_ContentElements", UmaPackage.eINSTANCE.getContentElement_SupportingMaterials(), true); //$NON-NLS-1$

	/**
	 * An opposite feature used for retrieving the delivery processes which a
	 * communication supporting material is associated with.
	 */
	public static final OppositeFeature SupportingMaterial_Communications_DeliveryProcesses = new OppositeFeature(
			SupportingMaterial.class,
			"SupportingMaterial_Communications_DeliveryProcesses", UmaPackage.eINSTANCE.getDeliveryProcess_CommunicationsMaterials(), true); //$NON-NLS-1$

	/**
	 * An opposite feature used for retrieving the delivery processes which an
	 * education supporting material is associated with.
	 */
	public static final OppositeFeature SupportingMaterial_Education_DeliveryProcesses = new OppositeFeature(
			SupportingMaterial.class,
			"SupportingMaterial_Education_DeliveryProcesses", UmaPackage.eINSTANCE.getDeliveryProcess_EducationMaterials(), true); //$NON-NLS-1$

	/**
	 * An opposite feature used for retrieving the method units which a
	 * copyright supporting material is associated with.
	 */
	public static final OppositeFeature SupportingMaterial_CopyrightStatement_MethodUnits = new OppositeFeature(
			SupportingMaterial.class,
			"SupportingMaterial_CopyrightStatement_MethodPlugins", UmaPackage.eINSTANCE.getMethodUnit_CopyrightStatement(), true); //$NON-NLS-1$

	/**
	 * An opposite feature used for retrieving the content elements which a
	 * guideline is associated with.
	 */
	public static final OppositeFeature Guideline_ContentElements = new OppositeFeature(
			Guideline.class,
			"Guideline_ContentElements", UmaPackage.eINSTANCE.getContentElement_Guidelines(), true); //$NON-NLS-1$

	/**
	 * An opposite feature used for retrieving the activities which a guideline
	 * is associated with.
	 */
	public static final OppositeFeature Guideline_BreakdownElements = new OppositeFeature(
			Guideline.class,
			"Guideline_BreakdownElements", UmaPackage.eINSTANCE.getBreakdownElement_Guidelines(), true, false); //$NON-NLS-1$

	/**
	 * An opposite feature used for retrieving the work products which a
	 * template is associated with.
	 */
	public static final OppositeFeature Template_WorkProducts = new OppositeFeature(
			Template.class,
			"Template_WorkProducts", UmaPackage.eINSTANCE.getWorkProduct_Templates(), true); //$NON-NLS-1$

	/**
	 * An opposite feature used for retrieving the tool which a tool mentor is
	 * categorized under.
	 */
	public static final OppositeFeature ToolMentor_Tools = new OppositeFeature(
			ToolMentor.class,
			"ToolMentor_Tools", UmaPackage.eINSTANCE.getTool_ToolMentors(), true); //$NON-NLS-1$

	/**
	 * An opposite feature used for retrieving the tasks which a tool mentor is
	 * associated with.
	 */
	public static final OppositeFeature ToolMentor_Tasks = new OppositeFeature(
			ToolMentor.class,
			"ToolMentor_Tasks", UmaPackage.eINSTANCE.getTask_ToolMentors(), true); //$NON-NLS-1$

	/**
	 * An opposite feature used for retrieving the work products which a tool
	 * mentor is associated with.
	 */
	public static final OppositeFeature ToolMentor_WorkProducts = new OppositeFeature(
			Template.class,
			"ToolMentor_WorkProducts", UmaPackage.eINSTANCE.getWorkProduct_ToolMentors(), true); //$NON-NLS-1$	

	/**
	 * An opposite feature used for retrieving the custom categories which a
	 * describable element is associated with.
	 */
	public static final OppositeFeature DescribableElement_CustomCategories = new OppositeFeature(
			DescribableElement.class,
			"ContentElement_CustomCategories", UmaPackage.eINSTANCE.getCustomCategory_CategorizedElements(), true); //$NON-NLS-1$

	/**
	 * An opposite feature used for retrieving the practices referenced by a
	 * content element.
	 */
	public static final OppositeFeature ContentElement_Practices = new OppositeFeature(
			ContentElement.class,
			"ContentElement_Practices", UmaPackage.eINSTANCE.getPractice_ContentReferences(), true); //$NON-NLS-1$

	/**
	 * An opposite feature used for retrieving the method plug-ins referenced by
	 * a method plug-in.
	 */
	public static final OppositeFeature MethodPlugin_DirectExtensions = new OppositeFeature(
			MethodPlugin.class,
			"MethodPlugin_DirectExtensions", UmaPackage.eINSTANCE.getMethodPlugin_Bases(), true); //$NON-NLS-1$

	/**
	 * An opposite feature used for retrieving the method configurations which
	 * contain a reference to a method plug-in.
	 */
	public static final OppositeFeature MethodPlugin_MethodConfigurations = new OppositeFeature(
			MethodPlugin.class,
			"MethodPlugin_MethodConfigurations", UmaPackage.eINSTANCE.getMethodConfiguration_MethodPluginSelection(), true); //$NON-NLS-1$

	/**
	 * An opposite feature used for retrieving the method packages
	 * used/referenced by a method package.
	 */
	public static final OppositeFeature MethodPackage_UsingPackages = new OppositeFeature(
			MethodPackage.class,
			"MethodPackage_UsingPackages", UmaPackage.eINSTANCE.getMethodPackage_ReusedPackages(), true); //$NON-NLS-1$

	/**
	 * An opposite feature used for retrieving the method configurations which
	 * contain a reference to a method package.
	 */
	public static final OppositeFeature MethodPackage_MethodConfigurations = new OppositeFeature(
			MethodPackage.class,
			"MethodPackage_MethodConfigurations", UmaPackage.eINSTANCE.getMethodConfiguration_MethodPackageSelection(), true); //$NON-NLS-1$

	/**
	 * An opposite feature used for retrieving the method configurations which
	 * contain a reference to a CustomCategory.
	 */
	public static final OppositeFeature CustomCategory_MethodConfigurations = new OppositeFeature(
			CustomCategory.class,
			"CustomCategory_MethodConfigurations", UmaPackage.eINSTANCE.getMethodConfiguration_ProcessViews(), true); //$NON-NLS-1$

	/**
	 * An opposite feature used for retrieving the discipline groupings which
	 * contain a reference to a discipline.
	 */
	public static final OppositeFeature Discipline_DisciplineGroupings = new OppositeFeature(
			Discipline.class,
			"Discipline_DisciplineGroupings", UmaPackage.eINSTANCE.getDisciplineGrouping_Disciplines(), true); //$NON-NLS-1$

	/**
	 * An opposite feature used for retrieving the disciplines which
	 * contain a reference to a discipline (ie, a subdiscipline).
	 */
	public static final OppositeFeature Subdiscipline_Discipline = new OppositeFeature(
			Discipline.class,
			"Subdiscipline_Discipline", UmaPackage.eINSTANCE.getDiscipline_Subdiscipline(), true); //$NON-NLS-1$

	/**
	 * An opposite feature used for retrieving the role set groupings which
	 * contain a reference to a role set.
	 */
	public static final OppositeFeature RoleSet_RoleSetGrouppings = new OppositeFeature(
			RoleSet.class,
			"RoleSet_RoleSetGrouppings", UmaPackage.eINSTANCE.getRoleSetGrouping_RoleSets(), true); //$NON-NLS-1$

	/**
	 * An opposite feature used for retrieving the successor of a work order.
	 */
	public static final OppositeFeature WorkOrder_Successor = new OppositeFeature(
			WorkOrder.class,
			"WorkOrder_Successor", UmaPackage.eINSTANCE.getWorkBreakdownElement_LinkToPredecessor(), true); //$NON-NLS-1$

	/**
	 * An opposite feature used for retrieving the successor link of a work
	 * breakdown element.
	 */
	public static final OppositeFeature WorkBreakdownElement_LinkToSuccessor = new OppositeFeature(
			WorkBreakdownElement.class,
			"WorkBreakdownElement_LinkToSuccessor", UmaPackage.eINSTANCE.getWorkOrder_Pred(), true); //$NON-NLS-1$

	/**
	 * An opposite feature used for retrieving the immediate variaties of a
	 * variability element.
	 */
	public static final OppositeFeature VariabilityElement_ImmediateVarieties = new OppositeFeature(
			VariabilityElement.class,
			"VariabilityElement_ImmediateVarieties", UmaPackage.eINSTANCE.getVariabilityElement_VariabilityBasedOnElement(), true); //$NON-NLS-1$

	/**
	 * An opposite feature used for retrieving the successors to a section.
	 */
	public static final OppositeFeature Section_Successors = new OppositeFeature(
			Section.class,
			"Section_Successors", UmaPackage.eINSTANCE.getSection_Predecessor(), true); //$NON-NLS-1$

	/**
	 * An opposite feature used for retrieving the task descriptors which a
	 * section is associated with.
	 */
	public static final OppositeFeature Section_TaskDescriptors = new OppositeFeature(
			Section.class,
			"Section_TaskDescriptors", UmaPackage.eINSTANCE.getTaskDescriptor_SelectedSteps(), true, false); //$NON-NLS-1$

	/**
	 * An opposite feature used for retrieving the team profiles which includes
	 * a specific role descriptor.
	 */
	public static final OppositeFeature RoleDescriptor_TeamProfiles = new OppositeFeature(
			RoleDescriptor.class,
			"RoleDescriptor_TeamProfiles", UmaPackage.eINSTANCE.getTeamProfile_TeamRoles(), true); //$NON-NLS-1$

	/**
	 * An opposite feature used for retrieving the primary task descriptors
	 * performed by a role descriptor.
	 */
	public static final OppositeFeature RoleDescriptor_PrimaryTaskDescriptors = new OppositeFeature(
			RoleDescriptor.class,
			"RoleDescriptor_PrimaryTaskDescriptors", UmaPackage.eINSTANCE.getTaskDescriptor_PerformedPrimarilyBy(), true); //$NON-NLS-1$

	/**
	 * An opposite feature used for retrieving the additional task descriptors
	 * performed by a role descriptor.
	 */
	public static final OppositeFeature RoleDescriptor_AdditionalTaskDescriptors = new OppositeFeature(
			RoleDescriptor.class,
			"RoleDescriptor_AdditionalTaskDescriptors", UmaPackage.eINSTANCE.getTaskDescriptor_AdditionallyPerformedBy(), true); //$NON-NLS-1$

	/**
	 * An opposite feature used for retrieving the assisting task descriptors
	 * performed by a role descriptor.
	 */
	public static final OppositeFeature RoleDescriptor_AssistsIn_TaskDescriptors = new OppositeFeature(
			RoleDescriptor.class,
			"RoleDescriptor_AssistsIn_TaskDescriptors", UmaPackage.eINSTANCE.getTaskDescriptor_AssistedBy(), true); //$NON-NLS-1$

	/**
	 * An opposite feature used for retrieving the dependent processes
	 * referenced in a method configuration.
	 */
	public static final OppositeFeature MethodConfiguration_DependentProcesses = new OppositeFeature(
			MethodConfiguration.class,
			"MethodConfiguration_DependentProcesses", UmaPackage.eINSTANCE.getProcess_DefaultContext(), true); //$NON-NLS-1$

	/**
	 * An opposite feature used for retrieving the valid processes referenced in
	 * a method configuration.
	 */
	public static final OppositeFeature MethodConfiguration_ValidContext_Processes = new OppositeFeature(
			MethodConfiguration.class,
			"MethodConfiguration_ValidContext_Processes", UmaPackage.eINSTANCE.getProcess_ValidContext(), true); //$NON-NLS-1$

	/**
	 * An opposite feature used for retrieving the task descriptors which
	 * produce a work product descriptor as an output.
	 */
	public static final OppositeFeature WorkProductDescriptor_OutputFrom_TaskDescriptors = new OppositeFeature(
			WorkProductDescriptor.class,
			"WorkProductDescriptor_OutputFrom_TaskDescriptors", UmaPackage.eINSTANCE.getTaskDescriptor_Output(), true); //$NON-NLS-1$

	/**
	 * An opposite feature used for retrieving the task descriptors which use a
	 * work product descriptor as an optional input.
	 */
	public static final OppositeFeature WorkProductDescriptor_OptionalInputTo_TaskDescriptors = new OppositeFeature(
			WorkProductDescriptor.class,
			"WorkProductDescriptor_OptionalInputTo_TaskDescriptors", UmaPackage.eINSTANCE.getTaskDescriptor_OptionalInput(), true); //$NON-NLS-1$

	/**
	 * An opposite feature used for retrieving the task descriptors which use a
	 * work product descriptor as an mandatory input.
	 */
	public static final OppositeFeature WorkProductDescriptor_MandatoryInputTo_TaskDescriptors = new OppositeFeature(
			WorkProductDescriptor.class,
			"WorkProductDescriptor_MandatoryInputTo_TaskDescriptors", //$NON-NLS-1$
			UmaPackage.eINSTANCE.getTaskDescriptor_MandatoryInput(), true);

	/**
	 * An opposite feature used for retrieving the task descriptors which use a
	 * work product descriptor as an external input.
	 */
	public static final OppositeFeature WorkProductDescriptor_ExternalInputTo_TaskDescriptors = new OppositeFeature(
			WorkProductDescriptor.class,
			"WorkProductDescriptor_ExternalInputTo_TaskDescriptors", //$NON-NLS-1$
			UmaPackage.eINSTANCE.getTaskDescriptor_ExternalInput(), true);

	/**
	 * An opposite feature used for retrieving the role descriptors which are
	 * responsible for a work product descriptor.
	 */
	public static final OppositeFeature WorkProductDescriptor_ResponsibleRoleDescriptors = new OppositeFeature(
			WorkProductDescriptor.class,
			"WorkProductDescriptor_ResponsibleRoleDescriptors", //$NON-NLS-1$
			UmaPackage.eINSTANCE.getRoleDescriptor_ResponsibleFor(), true);

	/**
	 * An opposite feature used for retrieving the deliverable descriptors which
	 * contain a specific work product descriptor.
	 */
	public static final OppositeFeature WorkProductDescriptor_DeliverableDescriptors = new OppositeFeature(
			WorkProductDescriptor.class,
			"WorkProductDescriptor_DeliverableDescriptors", UmaPackage.eINSTANCE.getWorkProductDescriptor_DeliverableParts(), true); //$NON-NLS-1$

	/**
	 * An opposite feature used for retrieving the disciplines which an activity
	 * is associated with.
	 */
	public static final OppositeFeature Activity_Disciplines = new OppositeFeature(
			Activity.class,
			"Activity_Disciplines", UmaPackage.eINSTANCE.getDiscipline_ReferenceWorkflows(), true); //$NON-NLS-1$

	/**
	 * An opposite feature used for retrieving the practices which an activity
	 * is associated with.
	 */
	public static final OppositeFeature Activity_Pratices = new OppositeFeature(
			Activity.class,
			"Activity_Pratices", UmaPackage.eINSTANCE.getPractice_ActivityReferences(), true); //$NON-NLS-1$

	/**
	 * An opposite feature used for retrieving the process planning templates
	 * which a process is associated with.
	 */
	public static final OppositeFeature Process_ProcessPlanningTemplates = new OppositeFeature(
			Process.class,
			"Process_ProcessPlanningTemplates", UmaPackage.eINSTANCE.getProcessPlanningTemplate_BasedOnProcesses(), true); //$NON-NLS-1$

	/**
	 * An opposite feature used for retrieving the procesess which references a
	 * capability pattern.
	 */
	public static final OppositeFeature CapabilityPattern_ConsumingProcesses = new OppositeFeature(
			CapabilityPattern.class,
			"CapabilityPattern_ConsumingProcesses", UmaPackage.eINSTANCE.getProcess_IncludesPatterns(), true); //$NON-NLS-1$

	/**
	 * An opposite feature used for retrieving the breakdown element which is
	 * displayed before a breakdown element.
	 */
	public static final OppositeFeature BreakdownElement_PresentedBefore = new OppositeFeature(
			BreakdownElement.class,
			"BreakdownElement_PresentedBefore", //$NON-NLS-1$
			UmaPackage.eINSTANCE.getBreakdownElement_PresentedAfter(), true,
			false);

	/**
	 * An opposite feature used for retrieving the breakdown element which is
	 * displayed after a breakdown element.
	 */
	public static final OppositeFeature BreakdownElement_PresentedAfter = new OppositeFeature(
			BreakdownElement.class,
			"BreakdownElement_PresentedAfter", //$NON-NLS-1$
			UmaPackage.eINSTANCE.getBreakdownElement_PresentedBefore(), true,
			false);

	/**
	 * An opposite feature used for retrieving the UMA sematic model bridges
	 * referenced by a method element.
	 */
	public static final OppositeFeature MethodElement_UMASematicModelBridges = new OppositeFeature(
			MethodElement.class,
			"MethodElement_UMASematicModelBridges", //$NON-NLS-1$
			UmaPackage.eINSTANCE.getUMASemanticModelBridge_Element(), true,
			false);

	private static ArrayList predefinedOppositeFeatures;

	static {
		registerPredefinedOppositeFeatures();
	}

	/**
	 * Registers the predefined opposite features.
	 */
	public static final void registerPredefinedOppositeFeatures() {
		predefinedOppositeFeatures = new ArrayList();
		AssociationHelper object = new AssociationHelper();
		Field[] fields = object.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			int mod = field.getModifiers();
			if (Modifier.isPublic(mod) && Modifier.isStatic(mod)
					&& Modifier.isFinal(mod)
					&& field.getType() == OppositeFeature.class) {
				try {
					predefinedOppositeFeatures.add(field.get(object));
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}

		/*
		 * System.out
		 * .println("AssociationHelper.registerPredefinedOppositeFeatures():
		 * defined opposite features: " + predefinedOppositeFeatures.size());
		 * //$NON-NLS-1$ System.out.println(predefinedOppositeFeatures);
		 */

		for (Iterator iter = predefinedOppositeFeatures.iterator(); iter
				.hasNext();) {
			OppositeFeature feature = (OppositeFeature) iter.next();
			OppositeFeature.registerOppositeFeature(feature);
		}
	}

	/**
	 * Gets the predefined opposite features.
	 * 
	 * @return the predefined opposite features
	 */
	public static Collection getPredefinedOppositeFeatures() {
		return predefinedOppositeFeatures;
	}

	/**
	 * Gets the deliverable descriptors which contain the given work product
	 * descriptor.
	 * 
	 * @param wpd
	 *            a work descriptor
	 * @return a list of deliverable descriptors
	 */
	public static List getDeliverableDescriptors(WorkProductDescriptor wpd) {
		return (List) ((MultiResourceEObject) wpd)
				.getOppositeFeatureValue(WorkProductDescriptor_DeliverableDescriptors);
	}

	/**
	 * Gets the task descriptors which use a work product descriptor as an
	 * external input.
	 * 
	 * @param wpd
	 *            a work descriptor
	 * @return a list of task descriptors
	 */
	public static List getExternalInputTo(WorkProductDescriptor wpd) {
		return (List) ((MultiResourceEObject) wpd)
				.getOppositeFeatureValue(WorkProductDescriptor_ExternalInputTo_TaskDescriptors);
	}

	/**
	 * Gets the task descriptors which use a work product descriptor as a
	 * mandatory input.
	 * 
	 * @param wpd
	 *            a work descriptor
	 * @return a list of task descriptors
	 */
	public static List getMandatoryInputTo(WorkProductDescriptor wpd) {
		return (List) ((MultiResourceEObject) wpd)
				.getOppositeFeatureValue(WorkProductDescriptor_MandatoryInputTo_TaskDescriptors);
	}

	/**
	 * Gets the task descriptors which use a work product descriptor as an
	 * optional input.
	 * 
	 * @param wpd
	 *            a work descriptor
	 * @return a list of task descriptors
	 */
	public static List getOptionalInputTo(WorkProductDescriptor wpd) {
		return (List) ((MultiResourceEObject) wpd)
				.getOppositeFeatureValue(WorkProductDescriptor_OptionalInputTo_TaskDescriptors);
	}

	/**
	 * Gets the task descriptors which which produce a work product descriptor
	 * as an output.
	 * 
	 * @param wpd
	 *            a work descriptor
	 * @return a list of task descriptors
	 */
	public static List getOutputFrom(WorkProductDescriptor wpd) {
		return (List) ((MultiResourceEObject) wpd)
				.getOppositeFeatureValue(WorkProductDescriptor_OutputFrom_TaskDescriptors);
	}

	/**
	 * Gets the role descriptors which are responsible for a work product
	 * descriptor.
	 * 
	 * @param wpd
	 *            a work descriptor
	 * @return a list of role descriptors
	 */
	public static List getResponsibleRoleDescriptors(WorkProductDescriptor wpd) {
		return (List) wpd
				.getOppositeFeatureValue(WorkProductDescriptor_ResponsibleRoleDescriptors);
	}

	/**
	 * Gets the the dependent processes referenced in a method configuration.
	 * 
	 * @param e
	 *            a method configuration
	 * @return a list of processes
	 */
	public static List getDependentProcesses(MethodConfiguration e) {
		return (List) ((MultiResourceEObject) e)
				.getOppositeFeatureValue(MethodConfiguration_DependentProcesses);
	}

	/**
	 * Gets the team profiles which includes a specific role descriptor.
	 * 
	 * @param e
	 *            a role descriptor
	 * @return a list of team profiles
	 */
	public static List getTeamProfiles(RoleDescriptor e) {
		return (List) ((MultiResourceEObject) e)
				.getOppositeFeatureValue(RoleDescriptor_TeamProfiles);
	}

	/**
	 * Gets the practices referenced by a content element.
	 * 
	 * @param e
	 *            a content element
	 * @return a list of practices
	 */
	public static List getPractices(ContentElement e) {
		return (List) ((MultiResourceEObject) e)
				.getOppositeFeatureValue(ContentElement_Practices);
	}

	/**
	 * Gets the tool which a tool mentor is categorized under.
	 * 
	 * @param e
	 *            a tool mentor
	 * @return a tool
	 */
	public static List<? extends ToolMentor> getTools(ToolMentor toolMentor) {
		return (List<? extends ToolMentor>) ((MultiResourceEObject) toolMentor)
				.getOppositeFeatureValue(ToolMentor_Tools);
	}

	/**
	 * Gets the successors to a section.
	 * 
	 * @param e
	 *            a section
	 * @return a list of sections that are postioned after the given section
	 */
	public static List getSuccessors(Section section) {
		return (List) ((MultiResourceEObject) section)
				.getOppositeFeatureValue(Section_Successors);
	}

	/**
	 * Gets the immediate variaties of a variability element.
	 * 
	 * @param e
	 *            a variability element
	 * @return a list of immediate variaties
	 */
	public static List getImmediateVarieties(VariabilityElement e) {
		return (List) ((MultiResourceEObject) e)
				.getOppositeFeatureValue(VariabilityElement_ImmediateVarieties);
	}

	/**
	 * Gets the primary tasks of a role.
	 * 
	 * @param role
	 *            a role
	 * @return a list of tasks
	 */
	public static List getPrimaryTasks(Role role) {
		return (List) ((MultiResourceEObject) role)
				.getOppositeFeatureValue(Role_Primary_Tasks);
	}

	/**
	 * Gets the secondary tasks of a role.
	 * 
	 * @param role
	 *            a role
	 * @return a list of tasks
	 */
	public static List getSecondaryTasks(Role role) {
		return (List) ((MultiResourceEObject) role)
				.getOppositeFeatureValue(Role_Secondary_Tasks);
	}

	/**
	 * Gets the successor link of a work breakdown element.
	 * 
	 * @param e
	 *            a break down element
	 * @return a list of links to work breakdown elements
	 */
	public static List getLinkToSuccessor(BreakdownElement e) {
		return (List) ((MultiResourceEObject) e)
				.getOppositeFeatureValue(WorkBreakdownElement_LinkToSuccessor);
	}

	/**
	 * Gets the successor of a work order.
	 * 
	 * @param workOrder
	 *            a work order
	 * @return a work breakdown element
	 */
	public static WorkBreakdownElement getSuccessor(WorkOrder workOrder) {
		return (WorkBreakdownElement) ((MultiResourceEObject) workOrder)
				.getOppositeFeatureValue(WorkOrder_Successor);
	}

	/**
	 * Gets the tasks which produce a work product as an output.
	 * 
	 * @param wp
	 *            a work product
	 * @return a list of tasks
	 */
	public static List getOutputtingTasks(WorkProduct wp) {
		return (List) ((MultiResourceEObject) wp)
				.getOppositeFeatureValue(WorkProduct_OutputFrom_Tasks);
	}

	/**
	 * Gets the tasks which use a work product as a mandatory input.
	 * 
	 * @param wp
	 *            a work product
	 * @return a list of tasks
	 */
	public static List getMandatoryInputToTasks(WorkProduct wp) {
		return (List) ((MultiResourceEObject) wp)
				.getOppositeFeatureValue(WorkProduct_MandatoryInputTo_Tasks);
	}

	/**
	 * Gets the responsible roles for a work product.
	 * 
	 * @param wp
	 *            a work product
	 * @return a list of roles
	 */
	public static List getResponsibleRoles(WorkProduct wp) {
		return (List) wp.getOppositeFeatureValue(WorkProduct_ResponsibleRoles);
	}
	
	/**
	 * Gets list of work products for which given work product fulfills.
	 * 
	 * @param wp
	 *            a work product
	 * @return a list of work products
	 */
	public static List getFullFills(WorkProduct wp) {
		return (List) wp.getOppositeFeatureValue(FulFills_FullFillableElements);
	}

	/**
	 * Gets the work products modified by a role.
	 * 
	 * @param role
	 *            a role
	 * @return a list of work products
	 */
	public static List getModifiedWorkProducts(Role role) {
		List performs = getPrimaryTasks(role);
		List modifies = new BasicEList();
		if (performs != null && performs.size() > 0) {
			for (Iterator i = performs.iterator(); i.hasNext();) {
				Task task = (Task) i.next();

				// for each task, get output work product;
				List outputWPs = task.getOutput();
				if (outputWPs != null && outputWPs.size() > 0) {
					for (Iterator it = outputWPs.iterator(); it.hasNext();) {
						WorkProduct wp = (WorkProduct) it.next();
						if (wp != null) {
							if (!modifies.contains(wp)) {
								modifies.add(wp);
							}
						}
					}
				}
			}
		}
		return modifies;

	}

	/**
	 * Gets the roles that modify a work product.
	 * 
	 * @param wp
	 *            a work product
	 * @return a list of roles
	 */
	public static List getModifiedBy(WorkProduct wp) {
		List outputFrom = AssociationHelper.getOutputtingTasks(wp);
		UniqueEList<Role> modifies = new UniqueEList<Role>();
		if (outputFrom != null && outputFrom.size() > 0) {
			for (Iterator it = outputFrom.iterator(); it.hasNext();) {
				//	for each task get, get primary performer role
				Task task = (Task) it.next();
				modifies.addAll(task.getPerformedBy());
			}
		}
		return modifies;
	}

	/**
	 * Gets the discipline groupings which contain a reference to a discipline.
	 * 
	 * @param discipline
	 *            a discipline
	 * @return a list of discipline groupings
	 */
	public static List getDisciplineGroups(Discipline discipline) {
		return (List) ((MultiResourceEObject) discipline)
				.getOppositeFeatureValue(Discipline_DisciplineGroupings);
	}

	/**
	 * Gets the discipline which contain a reference to a subdiscipline.
	 * 
	 * @param discipline
	 *            a discipline
	 * @return a list of discipline groupings
	 */
	public static List getDiscipline(Discipline subdiscipline) {
		return (List) ((MultiResourceEObject) subdiscipline)
				.getOppositeFeatureValue(Subdiscipline_Discipline);
	}

	/**
	 * Gets the domains which a work product is categorized under.
	 * 
	 * @param wp
	 *            a work product
	 * @return a domain
	 */
	public static List getDomains(WorkProduct wp) {
		return (List) ((MultiResourceEObject) wp)
				.getOppositeFeatureValue(WorkProduct_Domains);
	}

	/**
	 * Gets the role set groupings which contain a reference to a role set.
	 * 
	 * @param roleSet
	 *            a role set
	 * @return a list of role set groupings
	 */
	public static List getRoleSetGroups(RoleSet roleSet) {
		return (List) ((MultiResourceEObject) roleSet)
				.getOppositeFeatureValue(RoleSet_RoleSetGrouppings);
	}

	/**
	 * Gets the role sets which a role is categorized under.
	 * 
	 * @param role
	 *            a role
	 * @return a list of role sets
	 */
	public static List getRoleSets(Role role) {
		return (List) ((MultiResourceEObject) role)
				.getOppositeFeatureValue(Role_RoleSets);
	}

	/**
	 * Gets the task descriptors which have been created from a task.
	 * 
	 * @param task
	 *            a task
	 * @return a list of task descriptors
	 */
	public static List getTaskDescriptors(Task task) {
		return (List) task.getOppositeFeatureValue(Task_TaskDescriptors);
	}

	/**
	 * Gets the disciplines which a task is categorized under.
	 * 
	 * @param task
	 *            a task
	 * @return a list of disciplines
	 */
	public static List getDisciplines(Task task) {
		return (List) task.getOppositeFeatureValue(Task_Disciplines);
	}

	/**
	 * Gets the disciplines which an activity is associated with.
	 * 
	 * @param activity
	 *            an activity
	 * @return a list of disciplines
	 */
	public static List getDisciplines(Activity activity) {
		return (List) activity.getOppositeFeatureValue(Activity_Disciplines);
	}

	/**
	 * Gets the primary task descriptors performed by a role descriptor.
	 * 
	 * @param roleDesc
	 *            a role descriptor
	 * @return a list of task descriptors
	 */
	public static List getPrimaryTaskDescriptors(RoleDescriptor roleDesc) {
		return (List) ((MultiResourceEObject) roleDesc)
				.getOppositeFeatureValue(RoleDescriptor_PrimaryTaskDescriptors);
	}

	/**
	 * Gets the additional task descriptors performed by a role descriptor.
	 * 
	 * @param roleDesc
	 *            a role descriptor
	 * @return a list of task descriptors
	 */
	public static List getAdditionalTaskDescriptors(RoleDescriptor roleDesc) {
		return (List) ((MultiResourceEObject) roleDesc)
				.getOppositeFeatureValue(RoleDescriptor_AdditionalTaskDescriptors);
	}

	/**
	 * Gets the assisting task descriptors performed by a role descriptor.
	 * 
	 * @param roleDesc
	 *            a role descriptor
	 * @return a list of task descriptors
	 */
	public static List getAssistedTaskDescriptors(RoleDescriptor roleDesc) {
		return (List) ((MultiResourceEObject) roleDesc)
				.getOppositeFeatureValue(RoleDescriptor_AssistsIn_TaskDescriptors);
	}

	/**
	 * Gets the work product types which a work product is categorized under.
	 * 
	 * @param wp
	 *            a work product
	 * @return a list of work product types
	 */
	public static List getWorkProductTypes(WorkProduct wp) {
		return (List) ((MultiResourceEObject) wp)
				.getOppositeFeatureValue(WorkProduct_WorkProductTypes);
	}

	/**
	 * Gets the custom categories which a describable element is associated
	 * with.
	 * 
	 * @param wp
	 *            a describable element
	 * @return a list of custom categories
	 */
	public static List getCustomCategories(DescribableElement element) {
		return (List) ((MultiResourceEObject) element)
				.getOppositeFeatureValue(DescribableElement_CustomCategories);
	}

	/**
	 * Gets the Deliverables which a WorkProduct element is associated with.
	 * 
	 * @param wp
	 *            a WorkProduct element
	 * @return a list of Deliverables
	 */
	public static List getDeliverables(WorkProduct element) {
		return (List) ((MultiResourceEObject) element)
				.getOppositeFeatureValue(WorkProduct_Deliverables);
	}

	/**
	 * Gets the method plug-ins referenced by a method plug-in.
	 * 
	 * @param plugin
	 *            a method plug-in
	 * @return a list of method plug-ins
	 */
	public static List<MethodPlugin> getPluginDirectExtensions(MethodPlugin plugin) {
		return (List<MethodPlugin>) ((MultiResourceEObject) plugin)
				.getOppositeFeatureValue(MethodPlugin_DirectExtensions);
	}

	/**
	 * Gets all the references to a method element.
	 * 
	 * @param element
	 *            a method element
	 * @return a collection of method element references
	 */
	public static Collection getReferences(MethodElement element) {
		Set references = new HashSet();
		MultiResourceEObject eObj = (MultiResourceEObject) element;

		if (eObj.basicGetOppositeFeatureMap() == null)
			return references;

		for (Iterator iter = eObj.getOppositeFeatureMap().entrySet().iterator(); iter
				.hasNext();) {
			Map.Entry entry = (Map.Entry) iter.next();
			OppositeFeature oppositeFeature = ((OppositeFeature) entry.getKey());
			oppositeFeature.getTargetFeature();
			if (oppositeFeature.isMany()) {
				references.addAll((Collection) eObj
						.getOppositeFeatureValue(oppositeFeature));
			} else {
				EObject obj = (EObject) eObj
						.getOppositeFeatureValue(oppositeFeature);
				if (obj != null) {
					references.add(obj);
				}
			}
		}

		return references;
	}

	/**
	 * Gets all the references to a method element.
	 * 
	 * @param element
	 *            a method element
	 * @return a map containing method element references
	 */
	public static Map<EObject, Collection<EStructuralFeature>> getReferenceMap(MethodElement element) {
		Map<EObject, Collection<EStructuralFeature>> objectToFeaturesMap = new HashMap<EObject, Collection<EStructuralFeature>>();

		MultiResourceEObject eObj = (MultiResourceEObject) element;

		if (eObj.basicGetOppositeFeatureMap() == null) {
			return objectToFeaturesMap;
		}

		for (Iterator iter = eObj.getOppositeFeatureMap().entrySet().iterator(); iter
				.hasNext();) {
			Map.Entry entry = (Map.Entry) iter.next();
			OppositeFeature oppositeFeature = ((OppositeFeature) entry.getKey());
			EStructuralFeature feature = oppositeFeature.getTargetFeature();
			if (oppositeFeature.isMany()) {
				for (Iterator iterator = ((Collection) eObj
						.getOppositeFeatureValue(oppositeFeature)).iterator(); iterator
						.hasNext();) {
					EObject object = (EObject) iterator.next();
					Collection<EStructuralFeature> features = objectToFeaturesMap
							.get(object);
					if (features == null) {
						features = new ArrayList<EStructuralFeature>();
						objectToFeaturesMap.put(object, features);
					}
					features.add(feature);
				}
				;
			} else {
				EObject object = (EObject) eObj
						.getOppositeFeatureValue(oppositeFeature);
				if (object != null) {
					Collection<EStructuralFeature> features = objectToFeaturesMap
							.get(object);
					if (features == null) {
						features = new ArrayList<EStructuralFeature>();
						objectToFeaturesMap.put(object, features);
					}
					features.add(feature);
				}
			}
		}

		return objectToFeaturesMap;
	}

	public static final void init() {
	}

	/**
	 * Given a set of elements, returns the set of those elements plus any
	 * variability elements associated with them.
	 * 
	 * @param elementSet
	 *            a set of method elements for which to retrieve the variability
	 *            elements
	 * @param getBases
	 *            if <code>true</code>, recurse into the variability base
	 *            elements ("up the tree")
	 * @param getChildren
	 *            if <code>true</code>, recurse into the varieties ("down the
	 *            tree")
	 * @return a collective set of method elements plus the associated
	 *         variability elements
	 */
	public static Set getVariabilityElements(Set elementSet, boolean getBases,
			boolean getChildren) {
		if (elementSet != null && !elementSet.isEmpty()) {
			Set varSet = new HashSet();
			for (Iterator iter = elementSet.iterator(); iter.hasNext();) {
				Object o = iter.next();
				if (o instanceof VariabilityElement) {
					VariabilityElement element = (VariabilityElement) o;
					if (getChildren)
						varSet.addAll(getImmediateVarieties(element));
					if (getBases
							&& element.getVariabilityBasedOnElement() != null)
						varSet.add(element.getVariabilityBasedOnElement());
				}
			}
			if (varSet.isEmpty())
				return elementSet;
			if (elementSet.containsAll(varSet))
				return elementSet;
			elementSet.addAll(varSet);
			elementSet.addAll(getVariabilityElements(elementSet, getBases,
					getChildren));
		}
		return elementSet;
	}

}