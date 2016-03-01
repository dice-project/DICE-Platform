/*******************************************************************************
 * Copyright (c) 2005, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial implementation
 *******************************************************************************/
package org.eclipse.epf.xml.uma;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.epf.xml.uma.UmaPackage
 * @generated
 */
public interface UmaFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	UmaFactory eINSTANCE = org.eclipse.epf.xml.uma.impl.UmaFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Activity</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Activity</em>'.
	 * @generated
	 */
	Activity createActivity();

	/**
	 * Returns a new object of class '<em>Activity Description</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Activity Description</em>'.
	 * @generated
	 */
	ActivityDescription createActivityDescription();

	/**
	 * Returns a new object of class '<em>Applicable Meta Class Info</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Applicable Meta Class Info</em>'.
	 * @generated
	 */
	ApplicableMetaClassInfo createApplicableMetaClassInfo();

	/**
	 * Returns a new object of class '<em>Artifact</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Artifact</em>'.
	 * @generated
	 */
	Artifact createArtifact();

	/**
	 * Returns a new object of class '<em>Artifact Description</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Artifact Description</em>'.
	 * @generated
	 */
	ArtifactDescription createArtifactDescription();

	/**
	 * Returns a new object of class '<em>Breakdown Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Breakdown Element</em>'.
	 * @generated
	 */
	BreakdownElement createBreakdownElement();

	/**
	 * Returns a new object of class '<em>Breakdown Element Description</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Breakdown Element Description</em>'.
	 * @generated
	 */
	BreakdownElementDescription createBreakdownElementDescription();

	/**
	 * Returns a new object of class '<em>Capability Pattern</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Capability Pattern</em>'.
	 * @generated
	 */
	CapabilityPattern createCapabilityPattern();

	/**
	 * Returns a new object of class '<em>Checklist</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Checklist</em>'.
	 * @generated
	 */
	Checklist createChecklist();

	/**
	 * Returns a new object of class '<em>Composite Role</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Composite Role</em>'.
	 * @generated
	 */
	CompositeRole createCompositeRole();

	/**
	 * Returns a new object of class '<em>Concept</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Concept</em>'.
	 * @generated
	 */
	Concept createConcept();

	/**
	 * Returns a new object of class '<em>Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Constraint</em>'.
	 * @generated
	 */
	Constraint createConstraint();

	/**
	 * Returns a new object of class '<em>Content Category</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Content Category</em>'.
	 * @generated
	 */
	ContentCategory createContentCategory();

	/**
	 * Returns a new object of class '<em>Content Category Package</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Content Category Package</em>'.
	 * @generated
	 */
	ContentCategoryPackage createContentCategoryPackage();

	/**
	 * Returns a new object of class '<em>Content Description</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Content Description</em>'.
	 * @generated
	 */
	ContentDescription createContentDescription();

	/**
	 * Returns a new object of class '<em>Content Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Content Element</em>'.
	 * @generated
	 */
	ContentElement createContentElement();

	/**
	 * Returns a new object of class '<em>Content Package</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Content Package</em>'.
	 * @generated
	 */
	ContentPackage createContentPackage();

	/**
	 * Returns a new object of class '<em>Custom Category</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Custom Category</em>'.
	 * @generated
	 */
	CustomCategory createCustomCategory();

	/**
	 * Returns a new object of class '<em>Deliverable</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Deliverable</em>'.
	 * @generated
	 */
	Deliverable createDeliverable();

	/**
	 * Returns a new object of class '<em>Deliverable Description</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Deliverable Description</em>'.
	 * @generated
	 */
	DeliverableDescription createDeliverableDescription();

	/**
	 * Returns a new object of class '<em>Delivery Process</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Delivery Process</em>'.
	 * @generated
	 */
	DeliveryProcess createDeliveryProcess();

	/**
	 * Returns a new object of class '<em>Delivery Process Description</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Delivery Process Description</em>'.
	 * @generated
	 */
	DeliveryProcessDescription createDeliveryProcessDescription();

	/**
	 * Returns a new object of class '<em>Describable Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Describable Element</em>'.
	 * @generated
	 */
	DescribableElement createDescribableElement();

	/**
	 * Returns a new object of class '<em>Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Descriptor</em>'.
	 * @generated
	 */
	Descriptor createDescriptor();

	/**
	 * Returns a new object of class '<em>Descriptor Description</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Descriptor Description</em>'.
	 * @generated
	 */
	DescriptorDescription createDescriptorDescription();

	/**
	 * Returns a new object of class '<em>Discipline</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Discipline</em>'.
	 * @generated
	 */
	Discipline createDiscipline();

	/**
	 * Returns a new object of class '<em>Discipline Grouping</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Discipline Grouping</em>'.
	 * @generated
	 */
	DisciplineGrouping createDisciplineGrouping();

	/**
	 * Returns a new object of class '<em>Document Root</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Document Root</em>'.
	 * @generated
	 */
	DocumentRoot createDocumentRoot();

	/**
	 * Returns a new object of class '<em>Domain</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Domain</em>'.
	 * @generated
	 */
	Domain createDomain();

	/**
	 * Returns a new object of class '<em>Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Element</em>'.
	 * @generated
	 */
	Element createElement();

	/**
	 * Returns a new object of class '<em>Estimate</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Estimate</em>'.
	 * @generated
	 */
	Estimate createEstimate();

	/**
	 * Returns a new object of class '<em>Estimating Metric</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Estimating Metric</em>'.
	 * @generated
	 */
	EstimatingMetric createEstimatingMetric();

	/**
	 * Returns a new object of class '<em>Estimation Considerations</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Estimation Considerations</em>'.
	 * @generated
	 */
	EstimationConsiderations createEstimationConsiderations();

	/**
	 * Returns a new object of class '<em>Example</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Example</em>'.
	 * @generated
	 */
	Example createExample();

	/**
	 * Returns a new object of class '<em>Guidance</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Guidance</em>'.
	 * @generated
	 */
	Guidance createGuidance();

	/**
	 * Returns a new object of class '<em>Guidance Description</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Guidance Description</em>'.
	 * @generated
	 */
	GuidanceDescription createGuidanceDescription();

	/**
	 * Returns a new object of class '<em>Guideline</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Guideline</em>'.
	 * @generated
	 */
	Guideline createGuideline();

	/**
	 * Returns a new object of class '<em>Iteration</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Iteration</em>'.
	 * @generated
	 */
	Iteration createIteration();

	/**
	 * Returns a new object of class '<em>Kind</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Kind</em>'.
	 * @generated
	 */
	Kind createKind();

	/**
	 * Returns a new object of class '<em>Method Configuration</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Method Configuration</em>'.
	 * @generated
	 */
	MethodConfiguration createMethodConfiguration();

	/**
	 * Returns a new object of class '<em>Method Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Method Element</em>'.
	 * @generated
	 */
	MethodElement createMethodElement();

	/**
	 * Returns a new object of class '<em>Method Element Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Method Element Property</em>'.
	 * @generated
	 */
	MethodElementProperty createMethodElementProperty();

	/**
	 * Returns a new object of class '<em>Method Library</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Method Library</em>'.
	 * @generated
	 */
	MethodLibrary createMethodLibrary();

	/**
	 * Returns a new object of class '<em>Method Package</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Method Package</em>'.
	 * @generated
	 */
	MethodPackage createMethodPackage();

	/**
	 * Returns a new object of class '<em>Method Plugin</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Method Plugin</em>'.
	 * @generated
	 */
	MethodPlugin createMethodPlugin();

	/**
	 * Returns a new object of class '<em>Method Unit</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Method Unit</em>'.
	 * @generated
	 */
	MethodUnit createMethodUnit();

	/**
	 * Returns a new object of class '<em>Milestone</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Milestone</em>'.
	 * @generated
	 */
	Milestone createMilestone();

	/**
	 * Returns a new object of class '<em>Named Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Named Element</em>'.
	 * @generated
	 */
	NamedElement createNamedElement();

	/**
	 * Returns a new object of class '<em>Outcome</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Outcome</em>'.
	 * @generated
	 */
	Outcome createOutcome();

	/**
	 * Returns a new object of class '<em>Packageable Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Packageable Element</em>'.
	 * @generated
	 */
	PackageableElement createPackageableElement();

	/**
	 * Returns a new object of class '<em>Phase</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Phase</em>'.
	 * @generated
	 */
	Phase createPhase();

	/**
	 * Returns a new object of class '<em>Planning Data</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Planning Data</em>'.
	 * @generated
	 */
	PlanningData createPlanningData();

	/**
	 * Returns a new object of class '<em>Practice</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Practice</em>'.
	 * @generated
	 */
	Practice createPractice();

	/**
	 * Returns a new object of class '<em>Practice Description</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Practice Description</em>'.
	 * @generated
	 */
	PracticeDescription createPracticeDescription();

	/**
	 * Returns a new object of class '<em>Process</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Process</em>'.
	 * @generated
	 */
	org.eclipse.epf.xml.uma.Process createProcess();

	/**
	 * Returns a new object of class '<em>Process Component</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Process Component</em>'.
	 * @generated
	 */
	ProcessComponent createProcessComponent();

	/**
	 * Returns a new object of class '<em>Process Component Interface</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Process Component Interface</em>'.
	 * @generated
	 */
	ProcessComponentInterface createProcessComponentInterface();

	/**
	 * Returns a new object of class '<em>Process Description</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Process Description</em>'.
	 * @generated
	 */
	ProcessDescription createProcessDescription();

	/**
	 * Returns a new object of class '<em>Process Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Process Element</em>'.
	 * @generated
	 */
	ProcessElement createProcessElement();

	/**
	 * Returns a new object of class '<em>Process Package</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Process Package</em>'.
	 * @generated
	 */
	ProcessPackage createProcessPackage();

	/**
	 * Returns a new object of class '<em>Process Planning Template</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Process Planning Template</em>'.
	 * @generated
	 */
	ProcessPlanningTemplate createProcessPlanningTemplate();

	/**
	 * Returns a new object of class '<em>Report</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Report</em>'.
	 * @generated
	 */
	Report createReport();

	/**
	 * Returns a new object of class '<em>Reusable Asset</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Reusable Asset</em>'.
	 * @generated
	 */
	ReusableAsset createReusableAsset();

	/**
	 * Returns a new object of class '<em>Roadmap</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Roadmap</em>'.
	 * @generated
	 */
	Roadmap createRoadmap();

	/**
	 * Returns a new object of class '<em>Role</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Role</em>'.
	 * @generated
	 */
	Role createRole();

	/**
	 * Returns a new object of class '<em>Role Description</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Role Description</em>'.
	 * @generated
	 */
	RoleDescription createRoleDescription();

	/**
	 * Returns a new object of class '<em>Role Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Role Descriptor</em>'.
	 * @generated
	 */
	RoleDescriptor createRoleDescriptor();

	/**
	 * Returns a new object of class '<em>Role Set</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Role Set</em>'.
	 * @generated
	 */
	RoleSet createRoleSet();

	/**
	 * Returns a new object of class '<em>Role Set Grouping</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Role Set Grouping</em>'.
	 * @generated
	 */
	RoleSetGrouping createRoleSetGrouping();

	/**
	 * Returns a new object of class '<em>Section</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Section</em>'.
	 * @generated
	 */
	Section createSection();

	/**
	 * Returns a new object of class '<em>Supporting Material</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Supporting Material</em>'.
	 * @generated
	 */
	SupportingMaterial createSupportingMaterial();

	/**
	 * Returns a new object of class '<em>Task</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Task</em>'.
	 * @generated
	 */
	Task createTask();

	/**
	 * Returns a new object of class '<em>Task Description</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Task Description</em>'.
	 * @generated
	 */
	TaskDescription createTaskDescription();

	/**
	 * Returns a new object of class '<em>Task Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Task Descriptor</em>'.
	 * @generated
	 */
	TaskDescriptor createTaskDescriptor();

	/**
	 * Returns a new object of class '<em>Team Profile</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Team Profile</em>'.
	 * @generated
	 */
	TeamProfile createTeamProfile();

	/**
	 * Returns a new object of class '<em>Template</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Template</em>'.
	 * @generated
	 */
	Template createTemplate();

	/**
	 * Returns a new object of class '<em>Term Definition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Term Definition</em>'.
	 * @generated
	 */
	TermDefinition createTermDefinition();

	/**
	 * Returns a new object of class '<em>Tool</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tool</em>'.
	 * @generated
	 */
	Tool createTool();

	/**
	 * Returns a new object of class '<em>Tool Mentor</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tool Mentor</em>'.
	 * @generated
	 */
	ToolMentor createToolMentor();

	/**
	 * Returns a new object of class '<em>Whitepaper</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Whitepaper</em>'.
	 * @generated
	 */
	Whitepaper createWhitepaper();

	/**
	 * Returns a new object of class '<em>Work Breakdown Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Work Breakdown Element</em>'.
	 * @generated
	 */
	WorkBreakdownElement createWorkBreakdownElement();

	/**
	 * Returns a new object of class '<em>Work Definition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Work Definition</em>'.
	 * @generated
	 */
	WorkDefinition createWorkDefinition();

	/**
	 * Returns a new object of class '<em>Work Order</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Work Order</em>'.
	 * @generated
	 */
	WorkOrder createWorkOrder();

	/**
	 * Returns a new object of class '<em>Work Product</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Work Product</em>'.
	 * @generated
	 */
	WorkProduct createWorkProduct();

	/**
	 * Returns a new object of class '<em>Work Product Description</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Work Product Description</em>'.
	 * @generated
	 */
	WorkProductDescription createWorkProductDescription();

	/**
	 * Returns a new object of class '<em>Work Product Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Work Product Descriptor</em>'.
	 * @generated
	 */
	WorkProductDescriptor createWorkProductDescriptor();

	/**
	 * Returns a new object of class '<em>Work Product Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Work Product Type</em>'.
	 * @generated
	 */
	WorkProductType createWorkProductType();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	UmaPackage getUmaPackage();

} //UmaFactory
