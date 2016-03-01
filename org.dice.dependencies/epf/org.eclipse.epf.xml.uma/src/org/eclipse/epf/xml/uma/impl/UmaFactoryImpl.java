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
package org.eclipse.epf.xml.uma.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.epf.xml.uma.Activity;
import org.eclipse.epf.xml.uma.ActivityDescription;
import org.eclipse.epf.xml.uma.ApplicableMetaClassInfo;
import org.eclipse.epf.xml.uma.Artifact;
import org.eclipse.epf.xml.uma.ArtifactDescription;
import org.eclipse.epf.xml.uma.BreakdownElement;
import org.eclipse.epf.xml.uma.BreakdownElementDescription;
import org.eclipse.epf.xml.uma.CapabilityPattern;
import org.eclipse.epf.xml.uma.Checklist;
import org.eclipse.epf.xml.uma.CompositeRole;
import org.eclipse.epf.xml.uma.Concept;
import org.eclipse.epf.xml.uma.Constraint;
import org.eclipse.epf.xml.uma.ContentCategory;
import org.eclipse.epf.xml.uma.ContentCategoryPackage;
import org.eclipse.epf.xml.uma.ContentDescription;
import org.eclipse.epf.xml.uma.ContentElement;
import org.eclipse.epf.xml.uma.ContentPackage;
import org.eclipse.epf.xml.uma.CustomCategory;
import org.eclipse.epf.xml.uma.Deliverable;
import org.eclipse.epf.xml.uma.DeliverableDescription;
import org.eclipse.epf.xml.uma.DeliveryProcess;
import org.eclipse.epf.xml.uma.DeliveryProcessDescription;
import org.eclipse.epf.xml.uma.DescribableElement;
import org.eclipse.epf.xml.uma.Descriptor;
import org.eclipse.epf.xml.uma.DescriptorDescription;
import org.eclipse.epf.xml.uma.Discipline;
import org.eclipse.epf.xml.uma.DisciplineGrouping;
import org.eclipse.epf.xml.uma.DocumentRoot;
import org.eclipse.epf.xml.uma.Domain;
import org.eclipse.epf.xml.uma.Element;
import org.eclipse.epf.xml.uma.Estimate;
import org.eclipse.epf.xml.uma.EstimatingMetric;
import org.eclipse.epf.xml.uma.EstimationConsiderations;
import org.eclipse.epf.xml.uma.Example;
import org.eclipse.epf.xml.uma.Guidance;
import org.eclipse.epf.xml.uma.GuidanceDescription;
import org.eclipse.epf.xml.uma.Guideline;
import org.eclipse.epf.xml.uma.Iteration;
import org.eclipse.epf.xml.uma.Kind;
import org.eclipse.epf.xml.uma.MethodConfiguration;
import org.eclipse.epf.xml.uma.MethodElement;
import org.eclipse.epf.xml.uma.MethodElementProperty;
import org.eclipse.epf.xml.uma.MethodLibrary;
import org.eclipse.epf.xml.uma.MethodPackage;
import org.eclipse.epf.xml.uma.MethodPlugin;
import org.eclipse.epf.xml.uma.MethodUnit;
import org.eclipse.epf.xml.uma.Milestone;
import org.eclipse.epf.xml.uma.NamedElement;
import org.eclipse.epf.xml.uma.Outcome;
import org.eclipse.epf.xml.uma.PackageableElement;
import org.eclipse.epf.xml.uma.Phase;
import org.eclipse.epf.xml.uma.PlanningData;
import org.eclipse.epf.xml.uma.Practice;
import org.eclipse.epf.xml.uma.PracticeDescription;
import org.eclipse.epf.xml.uma.ProcessComponent;
import org.eclipse.epf.xml.uma.ProcessComponentInterface;
import org.eclipse.epf.xml.uma.ProcessDescription;
import org.eclipse.epf.xml.uma.ProcessElement;
import org.eclipse.epf.xml.uma.ProcessPackage;
import org.eclipse.epf.xml.uma.ProcessPlanningTemplate;
import org.eclipse.epf.xml.uma.Report;
import org.eclipse.epf.xml.uma.ReusableAsset;
import org.eclipse.epf.xml.uma.Roadmap;
import org.eclipse.epf.xml.uma.Role;
import org.eclipse.epf.xml.uma.RoleDescription;
import org.eclipse.epf.xml.uma.RoleDescriptor;
import org.eclipse.epf.xml.uma.RoleSet;
import org.eclipse.epf.xml.uma.RoleSetGrouping;
import org.eclipse.epf.xml.uma.Section;
import org.eclipse.epf.xml.uma.SupportingMaterial;
import org.eclipse.epf.xml.uma.Task;
import org.eclipse.epf.xml.uma.TaskDescription;
import org.eclipse.epf.xml.uma.TaskDescriptor;
import org.eclipse.epf.xml.uma.TeamProfile;
import org.eclipse.epf.xml.uma.Template;
import org.eclipse.epf.xml.uma.TermDefinition;
import org.eclipse.epf.xml.uma.Tool;
import org.eclipse.epf.xml.uma.ToolMentor;
import org.eclipse.epf.xml.uma.UmaFactory;
import org.eclipse.epf.xml.uma.UmaPackage;
import org.eclipse.epf.xml.uma.VariabilityType;
import org.eclipse.epf.xml.uma.Whitepaper;
import org.eclipse.epf.xml.uma.WorkBreakdownElement;
import org.eclipse.epf.xml.uma.WorkDefinition;
import org.eclipse.epf.xml.uma.WorkOrder;
import org.eclipse.epf.xml.uma.WorkOrderType;
import org.eclipse.epf.xml.uma.WorkProduct;
import org.eclipse.epf.xml.uma.WorkProductDescription;
import org.eclipse.epf.xml.uma.WorkProductDescriptor;
import org.eclipse.epf.xml.uma.WorkProductType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class UmaFactoryImpl extends EFactoryImpl implements UmaFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static UmaFactory init() {
		try {
			UmaFactory theUmaFactory = (UmaFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.eclipse.org/epf/uma/1.0.6"); 
			if (theUmaFactory != null) {
				return theUmaFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new UmaFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UmaFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case UmaPackage.ACTIVITY: return (EObject)createActivity();
			case UmaPackage.ACTIVITY_DESCRIPTION: return (EObject)createActivityDescription();
			case UmaPackage.APPLICABLE_META_CLASS_INFO: return (EObject)createApplicableMetaClassInfo();
			case UmaPackage.ARTIFACT: return (EObject)createArtifact();
			case UmaPackage.ARTIFACT_DESCRIPTION: return (EObject)createArtifactDescription();
			case UmaPackage.BREAKDOWN_ELEMENT: return (EObject)createBreakdownElement();
			case UmaPackage.BREAKDOWN_ELEMENT_DESCRIPTION: return (EObject)createBreakdownElementDescription();
			case UmaPackage.CAPABILITY_PATTERN: return (EObject)createCapabilityPattern();
			case UmaPackage.CHECKLIST: return (EObject)createChecklist();
			case UmaPackage.COMPOSITE_ROLE: return (EObject)createCompositeRole();
			case UmaPackage.CONCEPT: return (EObject)createConcept();
			case UmaPackage.CONSTRAINT: return (EObject)createConstraint();
			case UmaPackage.CONTENT_CATEGORY: return (EObject)createContentCategory();
			case UmaPackage.CONTENT_CATEGORY_PACKAGE: return (EObject)createContentCategoryPackage();
			case UmaPackage.CONTENT_DESCRIPTION: return (EObject)createContentDescription();
			case UmaPackage.CONTENT_ELEMENT: return (EObject)createContentElement();
			case UmaPackage.CONTENT_PACKAGE: return (EObject)createContentPackage();
			case UmaPackage.CUSTOM_CATEGORY: return (EObject)createCustomCategory();
			case UmaPackage.DELIVERABLE: return (EObject)createDeliverable();
			case UmaPackage.DELIVERABLE_DESCRIPTION: return (EObject)createDeliverableDescription();
			case UmaPackage.DELIVERY_PROCESS: return (EObject)createDeliveryProcess();
			case UmaPackage.DELIVERY_PROCESS_DESCRIPTION: return (EObject)createDeliveryProcessDescription();
			case UmaPackage.DESCRIBABLE_ELEMENT: return (EObject)createDescribableElement();
			case UmaPackage.DESCRIPTOR: return (EObject)createDescriptor();
			case UmaPackage.DESCRIPTOR_DESCRIPTION: return (EObject)createDescriptorDescription();
			case UmaPackage.DISCIPLINE: return (EObject)createDiscipline();
			case UmaPackage.DISCIPLINE_GROUPING: return (EObject)createDisciplineGrouping();
			case UmaPackage.DOCUMENT_ROOT: return (EObject)createDocumentRoot();
			case UmaPackage.DOMAIN: return (EObject)createDomain();
			case UmaPackage.ELEMENT: return (EObject)createElement();
			case UmaPackage.ESTIMATE: return (EObject)createEstimate();
			case UmaPackage.ESTIMATING_METRIC: return (EObject)createEstimatingMetric();
			case UmaPackage.ESTIMATION_CONSIDERATIONS: return (EObject)createEstimationConsiderations();
			case UmaPackage.EXAMPLE: return (EObject)createExample();
			case UmaPackage.GUIDANCE: return (EObject)createGuidance();
			case UmaPackage.GUIDANCE_DESCRIPTION: return (EObject)createGuidanceDescription();
			case UmaPackage.GUIDELINE: return (EObject)createGuideline();
			case UmaPackage.ITERATION: return (EObject)createIteration();
			case UmaPackage.KIND: return (EObject)createKind();
			case UmaPackage.METHOD_CONFIGURATION: return (EObject)createMethodConfiguration();
			case UmaPackage.METHOD_ELEMENT: return (EObject)createMethodElement();
			case UmaPackage.METHOD_ELEMENT_PROPERTY: return (EObject)createMethodElementProperty();
			case UmaPackage.METHOD_LIBRARY: return (EObject)createMethodLibrary();
			case UmaPackage.METHOD_PACKAGE: return (EObject)createMethodPackage();
			case UmaPackage.METHOD_PLUGIN: return (EObject)createMethodPlugin();
			case UmaPackage.METHOD_UNIT: return (EObject)createMethodUnit();
			case UmaPackage.MILESTONE: return (EObject)createMilestone();
			case UmaPackage.NAMED_ELEMENT: return (EObject)createNamedElement();
			case UmaPackage.OUTCOME: return (EObject)createOutcome();
			case UmaPackage.PACKAGEABLE_ELEMENT: return (EObject)createPackageableElement();
			case UmaPackage.PHASE: return (EObject)createPhase();
			case UmaPackage.PLANNING_DATA: return (EObject)createPlanningData();
			case UmaPackage.PRACTICE: return (EObject)createPractice();
			case UmaPackage.PRACTICE_DESCRIPTION: return (EObject)createPracticeDescription();
			case UmaPackage.PROCESS: return (EObject)createProcess();
			case UmaPackage.PROCESS_COMPONENT: return (EObject)createProcessComponent();
			case UmaPackage.PROCESS_COMPONENT_INTERFACE: return (EObject)createProcessComponentInterface();
			case UmaPackage.PROCESS_DESCRIPTION: return (EObject)createProcessDescription();
			case UmaPackage.PROCESS_ELEMENT: return (EObject)createProcessElement();
			case UmaPackage.PROCESS_PACKAGE: return (EObject)createProcessPackage();
			case UmaPackage.PROCESS_PLANNING_TEMPLATE: return (EObject)createProcessPlanningTemplate();
			case UmaPackage.REPORT: return (EObject)createReport();
			case UmaPackage.REUSABLE_ASSET: return (EObject)createReusableAsset();
			case UmaPackage.ROADMAP: return (EObject)createRoadmap();
			case UmaPackage.ROLE: return (EObject)createRole();
			case UmaPackage.ROLE_DESCRIPTION: return (EObject)createRoleDescription();
			case UmaPackage.ROLE_DESCRIPTOR: return (EObject)createRoleDescriptor();
			case UmaPackage.ROLE_SET: return (EObject)createRoleSet();
			case UmaPackage.ROLE_SET_GROUPING: return (EObject)createRoleSetGrouping();
			case UmaPackage.SECTION: return (EObject)createSection();
			case UmaPackage.SUPPORTING_MATERIAL: return (EObject)createSupportingMaterial();
			case UmaPackage.TASK: return (EObject)createTask();
			case UmaPackage.TASK_DESCRIPTION: return (EObject)createTaskDescription();
			case UmaPackage.TASK_DESCRIPTOR: return (EObject)createTaskDescriptor();
			case UmaPackage.TEAM_PROFILE: return (EObject)createTeamProfile();
			case UmaPackage.TEMPLATE: return (EObject)createTemplate();
			case UmaPackage.TERM_DEFINITION: return (EObject)createTermDefinition();
			case UmaPackage.TOOL: return (EObject)createTool();
			case UmaPackage.TOOL_MENTOR: return (EObject)createToolMentor();
			case UmaPackage.WHITEPAPER: return (EObject)createWhitepaper();
			case UmaPackage.WORK_BREAKDOWN_ELEMENT: return (EObject)createWorkBreakdownElement();
			case UmaPackage.WORK_DEFINITION: return (EObject)createWorkDefinition();
			case UmaPackage.WORK_ORDER: return (EObject)createWorkOrder();
			case UmaPackage.WORK_PRODUCT: return (EObject)createWorkProduct();
			case UmaPackage.WORK_PRODUCT_DESCRIPTION: return (EObject)createWorkProductDescription();
			case UmaPackage.WORK_PRODUCT_DESCRIPTOR: return (EObject)createWorkProductDescriptor();
			case UmaPackage.WORK_PRODUCT_TYPE: return (EObject)createWorkProductType();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case UmaPackage.VARIABILITY_TYPE:
				return createVariabilityTypeFromString(eDataType, initialValue);
			case UmaPackage.WORK_ORDER_TYPE:
				return createWorkOrderTypeFromString(eDataType, initialValue);
			case UmaPackage.VARIABILITY_TYPE_OBJECT:
				return createVariabilityTypeObjectFromString(eDataType, initialValue);
			case UmaPackage.WORK_ORDER_TYPE_OBJECT:
				return createWorkOrderTypeObjectFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case UmaPackage.VARIABILITY_TYPE:
				return convertVariabilityTypeToString(eDataType, instanceValue);
			case UmaPackage.WORK_ORDER_TYPE:
				return convertWorkOrderTypeToString(eDataType, instanceValue);
			case UmaPackage.VARIABILITY_TYPE_OBJECT:
				return convertVariabilityTypeObjectToString(eDataType, instanceValue);
			case UmaPackage.WORK_ORDER_TYPE_OBJECT:
				return convertWorkOrderTypeObjectToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Activity createActivity() {
		ActivityImpl activity = new ActivityImpl();
		return activity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivityDescription createActivityDescription() {
		ActivityDescriptionImpl activityDescription = new ActivityDescriptionImpl();
		return activityDescription;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ApplicableMetaClassInfo createApplicableMetaClassInfo() {
		ApplicableMetaClassInfoImpl applicableMetaClassInfo = new ApplicableMetaClassInfoImpl();
		return applicableMetaClassInfo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Artifact createArtifact() {
		ArtifactImpl artifact = new ArtifactImpl();
		return artifact;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArtifactDescription createArtifactDescription() {
		ArtifactDescriptionImpl artifactDescription = new ArtifactDescriptionImpl();
		return artifactDescription;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BreakdownElement createBreakdownElement() {
		BreakdownElementImpl breakdownElement = new BreakdownElementImpl();
		return breakdownElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BreakdownElementDescription createBreakdownElementDescription() {
		BreakdownElementDescriptionImpl breakdownElementDescription = new BreakdownElementDescriptionImpl();
		return breakdownElementDescription;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CapabilityPattern createCapabilityPattern() {
		CapabilityPatternImpl capabilityPattern = new CapabilityPatternImpl();
		return capabilityPattern;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Checklist createChecklist() {
		ChecklistImpl checklist = new ChecklistImpl();
		return checklist;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CompositeRole createCompositeRole() {
		CompositeRoleImpl compositeRole = new CompositeRoleImpl();
		return compositeRole;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Concept createConcept() {
		ConceptImpl concept = new ConceptImpl();
		return concept;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Constraint createConstraint() {
		ConstraintImpl constraint = new ConstraintImpl();
		return constraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ContentCategory createContentCategory() {
		ContentCategoryImpl contentCategory = new ContentCategoryImpl();
		return contentCategory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ContentCategoryPackage createContentCategoryPackage() {
		ContentCategoryPackageImpl contentCategoryPackage = new ContentCategoryPackageImpl();
		return contentCategoryPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ContentDescription createContentDescription() {
		ContentDescriptionImpl contentDescription = new ContentDescriptionImpl();
		return contentDescription;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ContentElement createContentElement() {
		ContentElementImpl contentElement = new ContentElementImpl();
		return contentElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ContentPackage createContentPackage() {
		ContentPackageImpl contentPackage = new ContentPackageImpl();
		return contentPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CustomCategory createCustomCategory() {
		CustomCategoryImpl customCategory = new CustomCategoryImpl();
		return customCategory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Deliverable createDeliverable() {
		DeliverableImpl deliverable = new DeliverableImpl();
		return deliverable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeliverableDescription createDeliverableDescription() {
		DeliverableDescriptionImpl deliverableDescription = new DeliverableDescriptionImpl();
		return deliverableDescription;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeliveryProcess createDeliveryProcess() {
		DeliveryProcessImpl deliveryProcess = new DeliveryProcessImpl();
		return deliveryProcess;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeliveryProcessDescription createDeliveryProcessDescription() {
		DeliveryProcessDescriptionImpl deliveryProcessDescription = new DeliveryProcessDescriptionImpl();
		return deliveryProcessDescription;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DescribableElement createDescribableElement() {
		DescribableElementImpl describableElement = new DescribableElementImpl();
		return describableElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Descriptor createDescriptor() {
		DescriptorImpl descriptor = new DescriptorImpl();
		return descriptor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DescriptorDescription createDescriptorDescription() {
		DescriptorDescriptionImpl descriptorDescription = new DescriptorDescriptionImpl();
		return descriptorDescription;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Discipline createDiscipline() {
		DisciplineImpl discipline = new DisciplineImpl();
		return discipline;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DisciplineGrouping createDisciplineGrouping() {
		DisciplineGroupingImpl disciplineGrouping = new DisciplineGroupingImpl();
		return disciplineGrouping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DocumentRoot createDocumentRoot() {
		DocumentRootImpl documentRoot = new DocumentRootImpl();
		return documentRoot;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Domain createDomain() {
		DomainImpl domain = new DomainImpl();
		return domain;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Element createElement() {
		ElementImpl element = new ElementImpl();
		return element;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Estimate createEstimate() {
		EstimateImpl estimate = new EstimateImpl();
		return estimate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EstimatingMetric createEstimatingMetric() {
		EstimatingMetricImpl estimatingMetric = new EstimatingMetricImpl();
		return estimatingMetric;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EstimationConsiderations createEstimationConsiderations() {
		EstimationConsiderationsImpl estimationConsiderations = new EstimationConsiderationsImpl();
		return estimationConsiderations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Example createExample() {
		ExampleImpl example = new ExampleImpl();
		return example;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Guidance createGuidance() {
		GuidanceImpl guidance = new GuidanceImpl();
		return guidance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GuidanceDescription createGuidanceDescription() {
		GuidanceDescriptionImpl guidanceDescription = new GuidanceDescriptionImpl();
		return guidanceDescription;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Guideline createGuideline() {
		GuidelineImpl guideline = new GuidelineImpl();
		return guideline;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Iteration createIteration() {
		IterationImpl iteration = new IterationImpl();
		return iteration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Kind createKind() {
		KindImpl kind = new KindImpl();
		return kind;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MethodConfiguration createMethodConfiguration() {
		MethodConfigurationImpl methodConfiguration = new MethodConfigurationImpl();
		return methodConfiguration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MethodElement createMethodElement() {
		MethodElementImpl methodElement = new MethodElementImpl();
		return methodElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MethodElementProperty createMethodElementProperty() {
		MethodElementPropertyImpl methodElementProperty = new MethodElementPropertyImpl();
		return methodElementProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MethodLibrary createMethodLibrary() {
		MethodLibraryImpl methodLibrary = new MethodLibraryImpl();
		return methodLibrary;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MethodPackage createMethodPackage() {
		MethodPackageImpl methodPackage = new MethodPackageImpl();
		return methodPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MethodPlugin createMethodPlugin() {
		MethodPluginImpl methodPlugin = new MethodPluginImpl();
		return methodPlugin;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MethodUnit createMethodUnit() {
		MethodUnitImpl methodUnit = new MethodUnitImpl();
		return methodUnit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Milestone createMilestone() {
		MilestoneImpl milestone = new MilestoneImpl();
		return milestone;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NamedElement createNamedElement() {
		NamedElementImpl namedElement = new NamedElementImpl();
		return namedElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Outcome createOutcome() {
		OutcomeImpl outcome = new OutcomeImpl();
		return outcome;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PackageableElement createPackageableElement() {
		PackageableElementImpl packageableElement = new PackageableElementImpl();
		return packageableElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Phase createPhase() {
		PhaseImpl phase = new PhaseImpl();
		return phase;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PlanningData createPlanningData() {
		PlanningDataImpl planningData = new PlanningDataImpl();
		return planningData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Practice createPractice() {
		PracticeImpl practice = new PracticeImpl();
		return practice;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PracticeDescription createPracticeDescription() {
		PracticeDescriptionImpl practiceDescription = new PracticeDescriptionImpl();
		return practiceDescription;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.eclipse.epf.xml.uma.Process createProcess() {
		ProcessImpl process = new ProcessImpl();
		return process;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcessComponent createProcessComponent() {
		ProcessComponentImpl processComponent = new ProcessComponentImpl();
		return processComponent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcessComponentInterface createProcessComponentInterface() {
		ProcessComponentInterfaceImpl processComponentInterface = new ProcessComponentInterfaceImpl();
		return processComponentInterface;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcessDescription createProcessDescription() {
		ProcessDescriptionImpl processDescription = new ProcessDescriptionImpl();
		return processDescription;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcessElement createProcessElement() {
		ProcessElementImpl processElement = new ProcessElementImpl();
		return processElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcessPackage createProcessPackage() {
		ProcessPackageImpl processPackage = new ProcessPackageImpl();
		return processPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcessPlanningTemplate createProcessPlanningTemplate() {
		ProcessPlanningTemplateImpl processPlanningTemplate = new ProcessPlanningTemplateImpl();
		return processPlanningTemplate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Report createReport() {
		ReportImpl report = new ReportImpl();
		return report;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReusableAsset createReusableAsset() {
		ReusableAssetImpl reusableAsset = new ReusableAssetImpl();
		return reusableAsset;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Roadmap createRoadmap() {
		RoadmapImpl roadmap = new RoadmapImpl();
		return roadmap;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Role createRole() {
		RoleImpl role = new RoleImpl();
		return role;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RoleDescription createRoleDescription() {
		RoleDescriptionImpl roleDescription = new RoleDescriptionImpl();
		return roleDescription;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RoleDescriptor createRoleDescriptor() {
		RoleDescriptorImpl roleDescriptor = new RoleDescriptorImpl();
		return roleDescriptor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RoleSet createRoleSet() {
		RoleSetImpl roleSet = new RoleSetImpl();
		return roleSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RoleSetGrouping createRoleSetGrouping() {
		RoleSetGroupingImpl roleSetGrouping = new RoleSetGroupingImpl();
		return roleSetGrouping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Section createSection() {
		SectionImpl section = new SectionImpl();
		return section;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SupportingMaterial createSupportingMaterial() {
		SupportingMaterialImpl supportingMaterial = new SupportingMaterialImpl();
		return supportingMaterial;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Task createTask() {
		TaskImpl task = new TaskImpl();
		return task;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TaskDescription createTaskDescription() {
		TaskDescriptionImpl taskDescription = new TaskDescriptionImpl();
		return taskDescription;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TaskDescriptor createTaskDescriptor() {
		TaskDescriptorImpl taskDescriptor = new TaskDescriptorImpl();
		return taskDescriptor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TeamProfile createTeamProfile() {
		TeamProfileImpl teamProfile = new TeamProfileImpl();
		return teamProfile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Template createTemplate() {
		TemplateImpl template = new TemplateImpl();
		return template;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TermDefinition createTermDefinition() {
		TermDefinitionImpl termDefinition = new TermDefinitionImpl();
		return termDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Tool createTool() {
		ToolImpl tool = new ToolImpl();
		return tool;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ToolMentor createToolMentor() {
		ToolMentorImpl toolMentor = new ToolMentorImpl();
		return toolMentor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Whitepaper createWhitepaper() {
		WhitepaperImpl whitepaper = new WhitepaperImpl();
		return whitepaper;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WorkBreakdownElement createWorkBreakdownElement() {
		WorkBreakdownElementImpl workBreakdownElement = new WorkBreakdownElementImpl();
		return workBreakdownElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WorkDefinition createWorkDefinition() {
		WorkDefinitionImpl workDefinition = new WorkDefinitionImpl();
		return workDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WorkOrder createWorkOrder() {
		WorkOrderImpl workOrder = new WorkOrderImpl();
		return workOrder;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WorkProduct createWorkProduct() {
		WorkProductImpl workProduct = new WorkProductImpl();
		return workProduct;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WorkProductDescription createWorkProductDescription() {
		WorkProductDescriptionImpl workProductDescription = new WorkProductDescriptionImpl();
		return workProductDescription;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WorkProductDescriptor createWorkProductDescriptor() {
		WorkProductDescriptorImpl workProductDescriptor = new WorkProductDescriptorImpl();
		return workProductDescriptor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WorkProductType createWorkProductType() {
		WorkProductTypeImpl workProductType = new WorkProductTypeImpl();
		return workProductType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariabilityType createVariabilityTypeFromString(EDataType eDataType, String initialValue) {
		VariabilityType result = VariabilityType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertVariabilityTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WorkOrderType createWorkOrderTypeFromString(EDataType eDataType, String initialValue) {
		WorkOrderType result = WorkOrderType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertWorkOrderTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariabilityType createVariabilityTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createVariabilityTypeFromString(UmaPackage.Literals.VARIABILITY_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertVariabilityTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertVariabilityTypeToString(UmaPackage.Literals.VARIABILITY_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WorkOrderType createWorkOrderTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createWorkOrderTypeFromString(UmaPackage.Literals.WORK_ORDER_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertWorkOrderTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertWorkOrderTypeToString(UmaPackage.Literals.WORK_ORDER_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UmaPackage getUmaPackage() {
		return (UmaPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static UmaPackage getPackage() {
		return UmaPackage.eINSTANCE;
	}

} //UmaFactoryImpl
