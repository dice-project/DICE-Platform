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
package org.eclipse.epf.uma.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.ActivityDescription;
import org.eclipse.epf.uma.ApplicableMetaClassInfo;
import org.eclipse.epf.uma.Artifact;
import org.eclipse.epf.uma.ArtifactDescription;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.BreakdownElementDescription;
import org.eclipse.epf.uma.CapabilityPattern;
import org.eclipse.epf.uma.Checklist;
import org.eclipse.epf.uma.Classifier;
import org.eclipse.epf.uma.CompositeRole;
import org.eclipse.epf.uma.Concept;
import org.eclipse.epf.uma.Constraint;
import org.eclipse.epf.uma.ContentCategory;
import org.eclipse.epf.uma.ContentDescription;
import org.eclipse.epf.uma.ContentElement;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.CoreSemanticModelBridge;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.Deliverable;
import org.eclipse.epf.uma.DeliverableDescription;
import org.eclipse.epf.uma.DeliveryProcess;
import org.eclipse.epf.uma.DeliveryProcessDescription;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.DescriptorDescription;
import org.eclipse.epf.uma.Diagram;
import org.eclipse.epf.uma.DiagramElement;
import org.eclipse.epf.uma.DiagramLink;
import org.eclipse.epf.uma.Dimension;
import org.eclipse.epf.uma.Discipline;
import org.eclipse.epf.uma.DisciplineGrouping;
import org.eclipse.epf.uma.Domain;
import org.eclipse.epf.uma.Element;
import org.eclipse.epf.uma.Ellipse;
import org.eclipse.epf.uma.EstimationConsiderations;
import org.eclipse.epf.uma.Example;
import org.eclipse.epf.uma.FulfillableElement;
import org.eclipse.epf.uma.GraphConnector;
import org.eclipse.epf.uma.GraphEdge;
import org.eclipse.epf.uma.GraphElement;
import org.eclipse.epf.uma.GraphNode;
import org.eclipse.epf.uma.GraphicPrimitive;
import org.eclipse.epf.uma.Guidance;
import org.eclipse.epf.uma.GuidanceDescription;
import org.eclipse.epf.uma.Guideline;
import org.eclipse.epf.uma.Image;
import org.eclipse.epf.uma.Iteration;
import org.eclipse.epf.uma.Kind;
import org.eclipse.epf.uma.LeafElement;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodElementProperty;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPackage;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.MethodUnit;
import org.eclipse.epf.uma.Milestone;
import org.eclipse.epf.uma.NamedElement;
import org.eclipse.epf.uma.Namespace;
import org.eclipse.epf.uma.Outcome;
import org.eclipse.epf.uma.PackageableElement;
import org.eclipse.epf.uma.Phase;
import org.eclipse.epf.uma.PlanningData;
import org.eclipse.epf.uma.Point;
import org.eclipse.epf.uma.Polyline;
import org.eclipse.epf.uma.Practice;
import org.eclipse.epf.uma.PracticeDescription;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.ProcessComponentDescriptor;
import org.eclipse.epf.uma.ProcessComponentInterface;
import org.eclipse.epf.uma.ProcessDescription;
import org.eclipse.epf.uma.ProcessElement;
import org.eclipse.epf.uma.ProcessFamily;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.ProcessPlanningTemplate;
import org.eclipse.epf.uma.Property;
import org.eclipse.epf.uma.Reference;
import org.eclipse.epf.uma.Report;
import org.eclipse.epf.uma.ReusableAsset;
import org.eclipse.epf.uma.Roadmap;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.RoleDescription;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.RoleSet;
import org.eclipse.epf.uma.RoleSetGrouping;
import org.eclipse.epf.uma.Section;
import org.eclipse.epf.uma.SemanticModelBridge;
import org.eclipse.epf.uma.SimpleSemanticModelElement;
import org.eclipse.epf.uma.Step;
import org.eclipse.epf.uma.SupportingMaterial;
import org.eclipse.epf.uma.Task;
import org.eclipse.epf.uma.TaskDescription;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.TeamProfile;
import org.eclipse.epf.uma.Template;
import org.eclipse.epf.uma.TermDefinition;
import org.eclipse.epf.uma.TextElement;
import org.eclipse.epf.uma.Tool;
import org.eclipse.epf.uma.ToolMentor;
import org.eclipse.epf.uma.Type;
import org.eclipse.epf.uma.UMASemanticModelBridge;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.VariabilityType;
import org.eclipse.epf.uma.Whitepaper;
import org.eclipse.epf.uma.WorkBreakdownElement;
import org.eclipse.epf.uma.WorkDefinition;
import org.eclipse.epf.uma.WorkOrder;
import org.eclipse.epf.uma.WorkOrderType;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.WorkProductDescription;
import org.eclipse.epf.uma.WorkProductDescriptor;
import org.eclipse.epf.uma.WorkProductType;
import org.eclipse.epf.uma.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class UmaPackageImpl extends EPackageImpl implements UmaPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass classifierEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass typeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass elementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass namedElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass packageableElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass packageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass namespaceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass methodElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass constraintEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass methodElementPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass kindEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass contentElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass describableElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass contentDescriptionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sectionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass roleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass workProductEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass fulfillableElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass taskEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass workDefinitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stepEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass guidanceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass artifactEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass deliverableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass outcomeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass methodPackageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass contentPackageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass artifactDescriptionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass workProductDescriptionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass deliverableDescriptionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass roleDescriptionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass taskDescriptionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass guidanceDescriptionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass practiceDescriptionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass activityEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass workBreakdownElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass breakdownElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass milestoneEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iterationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass phaseEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass teamProfileEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass roleDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass workOrderEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass processElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass planningDataEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass descriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass taskDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass workProductDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass compositeRoleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass breakdownElementDescriptionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass activityDescriptionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass deliveryProcessDescriptionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass processDescriptionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass descriptorDescriptionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass conceptEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass checklistEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass exampleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass guidelineEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass estimationConsiderationsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass reportEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass templateEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass supportingMaterialEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass toolMentorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass whitepaperEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass termDefinitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass applicableMetaClassInfoEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass practiceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass reusableAssetEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass disciplineEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass contentCategoryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass roleSetEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass domainEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass workProductTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass disciplineGroupingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass toolEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass roleSetGroupingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass customCategoryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass deliveryProcessEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass processEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass capabilityPatternEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass processPlanningTemplateEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass roadmapEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass processComponentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass processPackageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass processComponentInterfaceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass processComponentDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass methodPluginEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass variabilityElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass methodUnitEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass methodConfigurationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass processFamilyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass methodLibraryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass pointEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass graphElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass diagramElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass diagramLinkEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass graphConnectorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass semanticModelBridgeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dimensionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass referenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass propertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass graphEdgeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass diagramEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass graphNodeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass simpleSemanticModelElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass umaSemanticModelBridgeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass coreSemanticModelBridgeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass leafElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass textElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass imageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass graphicPrimitiveEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass polylineEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ellipseEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum workOrderTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum variabilityTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType dateEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType uriEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType stringEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType booleanEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType setEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType sequenceEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType integerEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType doubleEDataType = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.epf.uma.UmaPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private UmaPackageImpl() {
		super(eNS_URI, UmaFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link UmaPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static UmaPackage init() {
		if (isInited)
			return (UmaPackage) EPackage.Registry.INSTANCE
					.getEPackage(UmaPackage.eNS_URI);

		// Obtain or create and register package
		UmaPackageImpl theUmaPackage = (UmaPackageImpl) (EPackage.Registry.INSTANCE
				.get(eNS_URI) instanceof UmaPackageImpl ? EPackage.Registry.INSTANCE
				.get(eNS_URI)
				: new UmaPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theUmaPackage.createPackageContents();

		// Initialize created meta-data
		theUmaPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theUmaPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(UmaPackage.eNS_URI, theUmaPackage);
		return theUmaPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getClassifier() {
		return classifierEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getClassifier_IsAbstract() {
		return (EAttribute) classifierEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getType() {
		return typeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getElement() {
		return elementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNamedElement() {
		return namedElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNamedElement_Name() {
		return (EAttribute) namedElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPackageableElement() {
		return packageableElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPackage() {
		return packageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNamespace() {
		return namespaceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMethodElement() {
		return methodElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethodElement_Guid() {
		return (EAttribute) methodElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethodElement_PresentationName() {
		return (EAttribute) methodElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethodElement_BriefDescription() {
		return (EAttribute) methodElementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodElement_OwnedRules() {
		return (EReference) methodElementEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodElement_MethodElementProperty() {
		return (EReference) methodElementEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodElement_Kind() {
		return (EReference) methodElementEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethodElement_Suppressed() {
		return (EAttribute) methodElementEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethodElement_OrderingGuide() {
		return (EAttribute) methodElementEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConstraint() {
		return constraintEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConstraint_Body() {
		return (EAttribute) constraintEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMethodElementProperty() {
		return methodElementPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethodElementProperty_Value() {
		return (EAttribute) methodElementPropertyEClass
				.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getKind() {
		return kindEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getKind_ApplicableMetaClassInfo() {
		return (EReference) kindEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getContentElement() {
		return contentElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContentElement_SupportingMaterials() {
		return (EReference) contentElementEClass.getEStructuralFeatures()
				.get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContentElement_ConceptsAndPapers() {
		return (EReference) contentElementEClass.getEStructuralFeatures()
				.get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContentElement_Checklists() {
		return (EReference) contentElementEClass.getEStructuralFeatures()
				.get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContentElement_Guidelines() {
		return (EReference) contentElementEClass.getEStructuralFeatures()
				.get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContentElement_Examples() {
		return (EReference) contentElementEClass.getEStructuralFeatures()
				.get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContentElement_Assets() {
		return (EReference) contentElementEClass.getEStructuralFeatures()
				.get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContentElement_Termdefinition() {
		return (EReference) contentElementEClass.getEStructuralFeatures()
				.get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDescribableElement() {
		return describableElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDescribableElement_Presentation() {
		return (EReference) describableElementEClass.getEStructuralFeatures()
				.get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDescribableElement_Shapeicon() {
		return (EAttribute) describableElementEClass.getEStructuralFeatures()
				.get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDescribableElement_Nodeicon() {
		return (EAttribute) describableElementEClass.getEStructuralFeatures()
				.get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getContentDescription() {
		return contentDescriptionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getContentDescription_MainDescription() {
		return (EAttribute) contentDescriptionEClass.getEStructuralFeatures()
				.get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContentDescription_Sections() {
		return (EReference) contentDescriptionEClass.getEStructuralFeatures()
				.get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getContentDescription_ExternalId() {
		return (EAttribute) contentDescriptionEClass.getEStructuralFeatures()
				.get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getContentDescription_KeyConsiderations() {
		return (EAttribute) contentDescriptionEClass.getEStructuralFeatures()
				.get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getContentDescription_LongPresentationName() {
		return (EAttribute) contentDescriptionEClass.getEStructuralFeatures()
				.get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSection() {
		return sectionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSection_SectionName() {
		return (EAttribute) sectionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSection_SectionDescription() {
		return (EAttribute) sectionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSection_SubSections() {
		return (EReference) sectionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSection_Predecessor() {
		return (EReference) sectionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRole() {
		return roleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRole_Modifies() {
		return (EReference) roleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRole_ResponsibleFor() {
		return (EReference) roleEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWorkProduct() {
		return workProductEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWorkProduct_Reports() {
		return (EReference) workProductEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWorkProduct_Templates() {
		return (EReference) workProductEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWorkProduct_ToolMentors() {
		return (EReference) workProductEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWorkProduct_EstimationConsiderations() {
		return (EReference) workProductEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFulfillableElement() {
		return fulfillableElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFulfillableElement_Fulfills() {
		return (EReference) fulfillableElementEClass.getEStructuralFeatures()
				.get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTask() {
		return taskEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTask_PerformedBy() {
		return (EReference) taskEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTask_MandatoryInput() {
		return (EReference) taskEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTask_Output() {
		return (EReference) taskEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTask_AdditionallyPerformedBy() {
		return (EReference) taskEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTask_OptionalInput() {
		return (EReference) taskEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTask_Steps() {
		return (EReference) taskEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTask_ToolMentors() {
		return (EReference) taskEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTask_EstimationConsiderations() {
		return (EReference) taskEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWorkDefinition() {
		return workDefinitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWorkDefinition_Precondition() {
		return (EReference) workDefinitionEClass.getEStructuralFeatures()
				.get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWorkDefinition_Postcondition() {
		return (EReference) workDefinitionEClass.getEStructuralFeatures()
				.get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStep() {
		return stepEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGuidance() {
		return guidanceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getArtifact() {
		return artifactEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArtifact_ContainerArtifact() {
		return (EReference) artifactEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArtifact_ContainedArtifacts() {
		return (EReference) artifactEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDeliverable() {
		return deliverableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDeliverable_DeliveredWorkProducts() {
		return (EReference) deliverableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOutcome() {
		return outcomeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMethodPackage() {
		return methodPackageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethodPackage_Global() {
		return (EAttribute) methodPackageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodPackage_ReusedPackages() {
		return (EReference) methodPackageEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodPackage_ChildPackages() {
		return (EReference) methodPackageEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getContentPackage() {
		return contentPackageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContentPackage_ContentElements() {
		return (EReference) contentPackageEClass.getEStructuralFeatures()
				.get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getArtifactDescription() {
		return artifactDescriptionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArtifactDescription_BriefOutline() {
		return (EAttribute) artifactDescriptionEClass.getEStructuralFeatures()
				.get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArtifactDescription_RepresentationOptions() {
		return (EAttribute) artifactDescriptionEClass.getEStructuralFeatures()
				.get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArtifactDescription_Representation() {
		return (EAttribute) artifactDescriptionEClass.getEStructuralFeatures()
				.get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArtifactDescription_Notation() {
		return (EAttribute) artifactDescriptionEClass.getEStructuralFeatures()
				.get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWorkProductDescription() {
		return workProductDescriptionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkProductDescription_Purpose() {
		return (EAttribute) workProductDescriptionEClass
				.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkProductDescription_ImpactOfNotHaving() {
		return (EAttribute) workProductDescriptionEClass
				.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkProductDescription_ReasonsForNotNeeding() {
		return (EAttribute) workProductDescriptionEClass
				.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDeliverableDescription() {
		return deliverableDescriptionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDeliverableDescription_ExternalDescription() {
		return (EAttribute) deliverableDescriptionEClass
				.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDeliverableDescription_PackagingGuidance() {
		return (EAttribute) deliverableDescriptionEClass
				.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRoleDescription() {
		return roleDescriptionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRoleDescription_Skills() {
		return (EAttribute) roleDescriptionEClass.getEStructuralFeatures().get(
				0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRoleDescription_AssignmentApproaches() {
		return (EAttribute) roleDescriptionEClass.getEStructuralFeatures().get(
				1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRoleDescription_Synonyms() {
		return (EAttribute) roleDescriptionEClass.getEStructuralFeatures().get(
				2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTaskDescription() {
		return taskDescriptionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTaskDescription_Purpose() {
		return (EAttribute) taskDescriptionEClass.getEStructuralFeatures().get(
				0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTaskDescription_Alternatives() {
		return (EAttribute) taskDescriptionEClass.getEStructuralFeatures().get(
				1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGuidanceDescription() {
		return guidanceDescriptionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGuidanceDescription_Attachments() {
		return (EAttribute) guidanceDescriptionEClass.getEStructuralFeatures()
				.get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPracticeDescription() {
		return practiceDescriptionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPracticeDescription_AdditionalInfo() {
		return (EAttribute) practiceDescriptionEClass.getEStructuralFeatures()
				.get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPracticeDescription_Problem() {
		return (EAttribute) practiceDescriptionEClass.getEStructuralFeatures()
				.get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPracticeDescription_Background() {
		return (EAttribute) practiceDescriptionEClass.getEStructuralFeatures()
				.get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPracticeDescription_Goals() {
		return (EAttribute) practiceDescriptionEClass.getEStructuralFeatures()
				.get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPracticeDescription_Application() {
		return (EAttribute) practiceDescriptionEClass.getEStructuralFeatures()
				.get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPracticeDescription_LevelsOfAdoption() {
		return (EAttribute) practiceDescriptionEClass.getEStructuralFeatures()
				.get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getActivity() {
		return activityEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActivity_BreakdownElements() {
		return (EReference) activityEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActivity_Roadmaps() {
		return (EReference) activityEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWorkBreakdownElement() {
		return workBreakdownElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkBreakdownElement_IsRepeatable() {
		return (EAttribute) workBreakdownElementEClass.getEStructuralFeatures()
				.get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkBreakdownElement_IsOngoing() {
		return (EAttribute) workBreakdownElementEClass.getEStructuralFeatures()
				.get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkBreakdownElement_IsEventDriven() {
		return (EAttribute) workBreakdownElementEClass.getEStructuralFeatures()
				.get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWorkBreakdownElement_LinkToPredecessor() {
		return (EReference) workBreakdownElementEClass.getEStructuralFeatures()
				.get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBreakdownElement() {
		return breakdownElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBreakdownElement_Prefix() {
		return (EAttribute) breakdownElementEClass.getEStructuralFeatures()
				.get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBreakdownElement_IsPlanned() {
		return (EAttribute) breakdownElementEClass.getEStructuralFeatures()
				.get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBreakdownElement_HasMultipleOccurrences() {
		return (EAttribute) breakdownElementEClass.getEStructuralFeatures()
				.get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBreakdownElement_IsOptional() {
		return (EAttribute) breakdownElementEClass.getEStructuralFeatures()
				.get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBreakdownElement_PresentedAfter() {
		return (EReference) breakdownElementEClass.getEStructuralFeatures()
				.get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBreakdownElement_PresentedBefore() {
		return (EReference) breakdownElementEClass.getEStructuralFeatures()
				.get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBreakdownElement_PlanningData() {
		return (EReference) breakdownElementEClass.getEStructuralFeatures()
				.get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBreakdownElement_SuperActivities() {
		return (EReference) breakdownElementEClass.getEStructuralFeatures()
				.get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBreakdownElement_Checklists() {
		return (EReference) breakdownElementEClass.getEStructuralFeatures()
				.get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBreakdownElement_Concepts() {
		return (EReference) breakdownElementEClass.getEStructuralFeatures()
				.get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBreakdownElement_Examples() {
		return (EReference) breakdownElementEClass.getEStructuralFeatures()
				.get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBreakdownElement_Guidelines() {
		return (EReference) breakdownElementEClass.getEStructuralFeatures()
				.get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBreakdownElement_ReusableAssets() {
		return (EReference) breakdownElementEClass.getEStructuralFeatures()
				.get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBreakdownElement_SupportingMaterials() {
		return (EReference) breakdownElementEClass.getEStructuralFeatures()
				.get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBreakdownElement_Templates() {
		return (EReference) breakdownElementEClass.getEStructuralFeatures()
				.get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBreakdownElement_Reports() {
		return (EReference) breakdownElementEClass.getEStructuralFeatures()
				.get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBreakdownElement_Estimationconsiderations() {
		return (EReference) breakdownElementEClass.getEStructuralFeatures()
				.get(16);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBreakdownElement_Toolmentor() {
		return (EReference) breakdownElementEClass.getEStructuralFeatures()
				.get(17);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMilestone() {
		return milestoneEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMilestone_RequiredResults() {
		return (EReference) milestoneEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIteration() {
		return iterationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPhase() {
		return phaseEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTeamProfile() {
		return teamProfileEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTeamProfile_TeamRoles() {
		return (EReference) teamProfileEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTeamProfile_SuperTeam() {
		return (EReference) teamProfileEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTeamProfile_SubTeam() {
		return (EReference) teamProfileEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRoleDescriptor() {
		return roleDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRoleDescriptor_Role() {
		return (EReference) roleDescriptorEClass.getEStructuralFeatures()
				.get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRoleDescriptor_Modifies() {
		return (EReference) roleDescriptorEClass.getEStructuralFeatures()
				.get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRoleDescriptor_ResponsibleFor() {
		return (EReference) roleDescriptorEClass.getEStructuralFeatures()
				.get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRoleDescriptor_ResponsibleForExclude() {
		return (EReference) roleDescriptorEClass.getEStructuralFeatures()
				.get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWorkOrder() {
		return workOrderEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkOrder_LinkType() {
		return (EAttribute) workOrderEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWorkOrder_Pred() {
		return (EReference) workOrderEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProcessElement() {
		return processElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPlanningData() {
		return planningDataEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPlanningData_StartDate() {
		return (EAttribute) planningDataEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPlanningData_FinishDate() {
		return (EAttribute) planningDataEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPlanningData_Rank() {
		return (EAttribute) planningDataEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDescriptor() {
		return descriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDescriptor_IsSynchronizedWithSource() {
		return (EAttribute) descriptorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDescriptor_GuidanceExclude() {
		return (EReference) descriptorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDescriptor_GuidanceAdditional() {
		return (EReference) descriptorEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTaskDescriptor() {
		return taskDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTaskDescriptor_Task() {
		return (EReference) taskDescriptorEClass.getEStructuralFeatures()
				.get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTaskDescriptor_AdditionallyPerformedBy() {
		return (EReference) taskDescriptorEClass.getEStructuralFeatures()
				.get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTaskDescriptor_AssistedBy() {
		return (EReference) taskDescriptorEClass.getEStructuralFeatures()
				.get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTaskDescriptor_ExternalInput() {
		return (EReference) taskDescriptorEClass.getEStructuralFeatures()
				.get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTaskDescriptor_MandatoryInput() {
		return (EReference) taskDescriptorEClass.getEStructuralFeatures()
				.get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTaskDescriptor_OptionalInput() {
		return (EReference) taskDescriptorEClass.getEStructuralFeatures()
				.get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTaskDescriptor_Output() {
		return (EReference) taskDescriptorEClass.getEStructuralFeatures()
				.get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTaskDescriptor_PerformedPrimarilyBy() {
		return (EReference) taskDescriptorEClass.getEStructuralFeatures()
				.get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTaskDescriptor_SelectedSteps() {
		return (EReference) taskDescriptorEClass.getEStructuralFeatures()
				.get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTaskDescriptor_PerformedPrimarilyByExcluded() {
		return (EReference) taskDescriptorEClass.getEStructuralFeatures()
				.get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTaskDescriptor_AdditionallyPerformedByExclude() {
		return (EReference) taskDescriptorEClass.getEStructuralFeatures().get(
				10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTaskDescriptor_MandatoryInputExclude() {
		return (EReference) taskDescriptorEClass.getEStructuralFeatures().get(
				11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTaskDescriptor_OptionalInputExclude() {
		return (EReference) taskDescriptorEClass.getEStructuralFeatures().get(
				12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTaskDescriptor_OutputExclude() {
		return (EReference) taskDescriptorEClass.getEStructuralFeatures().get(
				13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTaskDescriptor_SelectedStepsExclude() {
		return (EReference) taskDescriptorEClass.getEStructuralFeatures().get(
				14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWorkProductDescriptor() {
		return workProductDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkProductDescriptor_ActivityEntryState() {
		return (EAttribute) workProductDescriptorEClass
				.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkProductDescriptor_ActivityExitState() {
		return (EAttribute) workProductDescriptorEClass
				.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWorkProductDescriptor_WorkProduct() {
		return (EReference) workProductDescriptorEClass
				.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWorkProductDescriptor_ImpactedBy() {
		return (EReference) workProductDescriptorEClass
				.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWorkProductDescriptor_Impacts() {
		return (EReference) workProductDescriptorEClass
				.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWorkProductDescriptor_DeliverableParts() {
		return (EReference) workProductDescriptorEClass
				.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWorkProductDescriptor_DeliverablePartsExclude() {
		return (EReference) workProductDescriptorEClass
				.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCompositeRole() {
		return compositeRoleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompositeRole_AggregatedRoles() {
		return (EReference) compositeRoleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBreakdownElementDescription() {
		return breakdownElementDescriptionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBreakdownElementDescription_UsageGuidance() {
		return (EAttribute) breakdownElementDescriptionEClass
				.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getActivityDescription() {
		return activityDescriptionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getActivityDescription_Purpose() {
		return (EAttribute) activityDescriptionEClass.getEStructuralFeatures()
				.get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getActivityDescription_Alternatives() {
		return (EAttribute) activityDescriptionEClass.getEStructuralFeatures()
				.get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getActivityDescription_HowtoStaff() {
		return (EAttribute) activityDescriptionEClass.getEStructuralFeatures()
				.get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDeliveryProcessDescription() {
		return deliveryProcessDescriptionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDeliveryProcessDescription_Scale() {
		return (EAttribute) deliveryProcessDescriptionEClass
				.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDeliveryProcessDescription_ProjectCharacteristics() {
		return (EAttribute) deliveryProcessDescriptionEClass
				.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDeliveryProcessDescription_RiskLevel() {
		return (EAttribute) deliveryProcessDescriptionEClass
				.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDeliveryProcessDescription_EstimatingTechnique() {
		return (EAttribute) deliveryProcessDescriptionEClass
				.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDeliveryProcessDescription_ProjectMemberExpertise() {
		return (EAttribute) deliveryProcessDescriptionEClass
				.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDeliveryProcessDescription_TypeOfContract() {
		return (EAttribute) deliveryProcessDescriptionEClass
				.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProcessDescription() {
		return processDescriptionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProcessDescription_Scope() {
		return (EAttribute) processDescriptionEClass.getEStructuralFeatures()
				.get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProcessDescription_UsageNotes() {
		return (EAttribute) processDescriptionEClass.getEStructuralFeatures()
				.get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDescriptorDescription() {
		return descriptorDescriptionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDescriptorDescription_RefinedDescription() {
		return (EAttribute) descriptorDescriptionEClass
				.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConcept() {
		return conceptEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getChecklist() {
		return checklistEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExample() {
		return exampleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGuideline() {
		return guidelineEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEstimationConsiderations() {
		return estimationConsiderationsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getReport() {
		return reportEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTemplate() {
		return templateEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSupportingMaterial() {
		return supportingMaterialEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getToolMentor() {
		return toolMentorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWhitepaper() {
		return whitepaperEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTermDefinition() {
		return termDefinitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getApplicableMetaClassInfo() {
		return applicableMetaClassInfoEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getApplicableMetaClassInfo_IsPrimaryExtension() {
		return (EAttribute) applicableMetaClassInfoEClass
				.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPractice() {
		return practiceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPractice_SubPractices() {
		return (EReference) practiceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPractice_ContentReferences() {
		return (EReference) practiceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPractice_ActivityReferences() {
		return (EReference) practiceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getReusableAsset() {
		return reusableAssetEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDiscipline() {
		return disciplineEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDiscipline_Tasks() {
		return (EReference) disciplineEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDiscipline_Subdiscipline() {
		return (EReference) disciplineEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDiscipline_ReferenceWorkflows() {
		return (EReference) disciplineEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getContentCategory() {
		return contentCategoryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRoleSet() {
		return roleSetEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRoleSet_Roles() {
		return (EReference) roleSetEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDomain() {
		return domainEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDomain_WorkProducts() {
		return (EReference) domainEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDomain_Subdomains() {
		return (EReference) domainEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWorkProductType() {
		return workProductTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWorkProductType_WorkProducts() {
		return (EReference) workProductTypeEClass.getEStructuralFeatures().get(
				0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDisciplineGrouping() {
		return disciplineGroupingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDisciplineGrouping_Disciplines() {
		return (EReference) disciplineGroupingEClass.getEStructuralFeatures()
				.get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTool() {
		return toolEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTool_ToolMentors() {
		return (EReference) toolEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRoleSetGrouping() {
		return roleSetGroupingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRoleSetGrouping_RoleSets() {
		return (EReference) roleSetGroupingEClass.getEStructuralFeatures().get(
				0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCustomCategory() {
		return customCategoryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCustomCategory_CategorizedElements() {
		return (EReference) customCategoryEClass.getEStructuralFeatures()
				.get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCustomCategory_SubCategories() {
		return (EReference) customCategoryEClass.getEStructuralFeatures()
				.get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDeliveryProcess() {
		return deliveryProcessEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDeliveryProcess_EducationMaterials() {
		return (EReference) deliveryProcessEClass.getEStructuralFeatures().get(
				0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDeliveryProcess_CommunicationsMaterials() {
		return (EReference) deliveryProcessEClass.getEStructuralFeatures().get(
				1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProcess() {
		return processEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcess_IncludesPatterns() {
		return (EReference) processEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcess_DefaultContext() {
		return (EReference) processEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcess_ValidContext() {
		return (EReference) processEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCapabilityPattern() {
		return capabilityPatternEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProcessPlanningTemplate() {
		return processPlanningTemplateEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessPlanningTemplate_BasedOnProcesses() {
		return (EReference) processPlanningTemplateEClass
				.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRoadmap() {
		return roadmapEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProcessComponent() {
		return processComponentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessComponent_Interfaces() {
		return (EReference) processComponentEClass.getEStructuralFeatures()
				.get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessComponent_Process() {
		return (EReference) processComponentEClass.getEStructuralFeatures()
				.get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProcessPackage() {
		return processPackageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessPackage_ProcessElements() {
		return (EReference) processPackageEClass.getEStructuralFeatures()
				.get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessPackage_Diagrams() {
		return (EReference) processPackageEClass.getEStructuralFeatures()
				.get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProcessComponentInterface() {
		return processComponentInterfaceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessComponentInterface_InterfaceSpecifications() {
		return (EReference) processComponentInterfaceEClass
				.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessComponentInterface_InterfaceIO() {
		return (EReference) processComponentInterfaceEClass
				.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProcessComponentDescriptor() {
		return processComponentDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessComponentDescriptor__processComponent() {
		return (EReference) processComponentDescriptorEClass
				.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMethodPlugin() {
		return methodPluginEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethodPlugin_UserChangeable() {
		return (EAttribute) methodPluginEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodPlugin_MethodPackages() {
		return (EReference) methodPluginEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodPlugin_Bases() {
		return (EReference) methodPluginEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethodPlugin_Supporting() {
		return (EAttribute) methodPluginEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVariabilityElement() {
		return variabilityElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getVariabilityElement_VariabilityType() {
		return (EAttribute) variabilityElementEClass.getEStructuralFeatures()
				.get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVariabilityElement_VariabilityBasedOnElement() {
		return (EReference) variabilityElementEClass.getEStructuralFeatures()
				.get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMethodUnit() {
		return methodUnitEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethodUnit_Authors() {
		return (EAttribute) methodUnitEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethodUnit_ChangeDate() {
		return (EAttribute) methodUnitEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethodUnit_ChangeDescription() {
		return (EAttribute) methodUnitEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethodUnit_Version() {
		return (EAttribute) methodUnitEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodUnit_CopyrightStatement() {
		return (EReference) methodUnitEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMethodConfiguration() {
		return methodConfigurationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodConfiguration_MethodPluginSelection() {
		return (EReference) methodConfigurationEClass.getEStructuralFeatures()
				.get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodConfiguration_MethodPackageSelection() {
		return (EReference) methodConfigurationEClass.getEStructuralFeatures()
				.get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodConfiguration_ProcessViews() {
		return (EReference) methodConfigurationEClass.getEStructuralFeatures()
				.get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodConfiguration_DefaultView() {
		return (EReference) methodConfigurationEClass.getEStructuralFeatures()
				.get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodConfiguration_BaseConfigurations() {
		return (EReference) methodConfigurationEClass.getEStructuralFeatures()
				.get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodConfiguration_SubtractedCategory() {
		return (EReference) methodConfigurationEClass.getEStructuralFeatures()
				.get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodConfiguration_AddedCategory() {
		return (EReference) methodConfigurationEClass.getEStructuralFeatures()
				.get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProcessFamily() {
		return processFamilyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessFamily_DeliveryProcesses() {
		return (EReference) processFamilyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMethodLibrary() {
		return methodLibraryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodLibrary_MethodPlugins() {
		return (EReference) methodLibraryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodLibrary_PredefinedConfigurations() {
		return (EReference) methodLibraryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPoint() {
		return pointEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPoint_X() {
		return (EAttribute) pointEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPoint_Y() {
		return (EAttribute) pointEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGraphElement() {
		return graphElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGraphElement_Position() {
		return (EReference) graphElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGraphElement_Contained() {
		return (EReference) graphElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGraphElement_Link() {
		return (EReference) graphElementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGraphElement_Anchorage() {
		return (EReference) graphElementEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGraphElement_SemanticModel() {
		return (EReference) graphElementEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDiagramElement() {
		return diagramElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDiagramElement_IsVisible() {
		return (EAttribute) diagramElementEClass.getEStructuralFeatures()
				.get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDiagramElement_Container() {
		return (EReference) diagramElementEClass.getEStructuralFeatures()
				.get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDiagramElement_Reference() {
		return (EReference) diagramElementEClass.getEStructuralFeatures()
				.get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDiagramElement_Property() {
		return (EReference) diagramElementEClass.getEStructuralFeatures()
				.get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDiagramLink() {
		return diagramLinkEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDiagramLink_Zoom() {
		return (EAttribute) diagramLinkEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDiagramLink_Viewport() {
		return (EReference) diagramLinkEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDiagramLink_GraphElement() {
		return (EReference) diagramLinkEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDiagramLink_Diagram() {
		return (EReference) diagramLinkEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGraphConnector() {
		return graphConnectorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGraphConnector_GraphElement() {
		return (EReference) graphConnectorEClass.getEStructuralFeatures()
				.get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGraphConnector_GraphEdge() {
		return (EReference) graphConnectorEClass.getEStructuralFeatures()
				.get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSemanticModelBridge() {
		return semanticModelBridgeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSemanticModelBridge_Presentation() {
		return (EAttribute) semanticModelBridgeEClass.getEStructuralFeatures()
				.get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSemanticModelBridge_GraphElement() {
		return (EReference) semanticModelBridgeEClass.getEStructuralFeatures()
				.get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSemanticModelBridge_Diagram() {
		return (EReference) semanticModelBridgeEClass.getEStructuralFeatures()
				.get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDimension() {
		return dimensionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDimension_Width() {
		return (EAttribute) dimensionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDimension_Height() {
		return (EAttribute) dimensionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getReference() {
		return referenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getReference_IsIndividualRepresentation() {
		return (EAttribute) referenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getReference_Referenced() {
		return (EReference) referenceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProperty() {
		return propertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProperty_Key() {
		return (EAttribute) propertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProperty_Value() {
		return (EAttribute) propertyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGraphEdge() {
		return graphEdgeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGraphEdge_Anchor() {
		return (EReference) graphEdgeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGraphEdge_Waypoints() {
		return (EReference) graphEdgeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDiagram() {
		return diagramEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDiagram_Zoom() {
		return (EAttribute) diagramEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDiagram_Viewpoint() {
		return (EReference) diagramEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDiagram_DiagramLink() {
		return (EReference) diagramEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDiagram_Namespace() {
		return (EReference) diagramEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGraphNode() {
		return graphNodeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGraphNode_Size() {
		return (EReference) graphNodeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSimpleSemanticModelElement() {
		return simpleSemanticModelElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSimpleSemanticModelElement_TypeInfo() {
		return (EAttribute) simpleSemanticModelElementEClass
				.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUMASemanticModelBridge() {
		return umaSemanticModelBridgeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUMASemanticModelBridge_Element() {
		return (EReference) umaSemanticModelBridgeEClass
				.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCoreSemanticModelBridge() {
		return coreSemanticModelBridgeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCoreSemanticModelBridge_Element() {
		return (EReference) coreSemanticModelBridgeEClass
				.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLeafElement() {
		return leafElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTextElement() {
		return textElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTextElement_Text() {
		return (EAttribute) textElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getImage() {
		return imageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getImage_Uri() {
		return (EAttribute) imageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getImage_MimeType() {
		return (EAttribute) imageEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGraphicPrimitive() {
		return graphicPrimitiveEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPolyline() {
		return polylineEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPolyline_Closed() {
		return (EAttribute) polylineEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPolyline_Waypoints() {
		return (EReference) polylineEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEllipse() {
		return ellipseEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEllipse_RadiusX() {
		return (EAttribute) ellipseEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEllipse_RadiusY() {
		return (EAttribute) ellipseEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEllipse_Rotation() {
		return (EAttribute) ellipseEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEllipse_StartAngle() {
		return (EAttribute) ellipseEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEllipse_EndAngle() {
		return (EAttribute) ellipseEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEllipse_Center() {
		return (EReference) ellipseEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getWorkOrderType() {
		return workOrderTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getVariabilityType() {
		return variabilityTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getDate() {
		return dateEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getUri() {
		return uriEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getString() {
		return stringEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getBoolean() {
		return booleanEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getSet() {
		return setEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getSequence() {
		return sequenceEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getInteger() {
		return integerEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getDouble() {
		return doubleEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UmaFactory getUmaFactory() {
		return (UmaFactory) getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated)
			return;
		isCreated = true;

		// Create classes and their features
		classifierEClass = createEClass(CLASSIFIER);
		createEAttribute(classifierEClass, CLASSIFIER__IS_ABSTRACT);

		typeEClass = createEClass(TYPE);

		packageableElementEClass = createEClass(PACKAGEABLE_ELEMENT);

		namedElementEClass = createEClass(NAMED_ELEMENT);
		createEAttribute(namedElementEClass, NAMED_ELEMENT__NAME);

		elementEClass = createEClass(ELEMENT);

		packageEClass = createEClass(PACKAGE);

		namespaceEClass = createEClass(NAMESPACE);

		constraintEClass = createEClass(CONSTRAINT);
		createEAttribute(constraintEClass, CONSTRAINT__BODY);

		methodElementEClass = createEClass(METHOD_ELEMENT);
		createEAttribute(methodElementEClass, METHOD_ELEMENT__GUID);
		createEAttribute(methodElementEClass, METHOD_ELEMENT__PRESENTATION_NAME);
		createEAttribute(methodElementEClass, METHOD_ELEMENT__BRIEF_DESCRIPTION);
		createEReference(methodElementEClass, METHOD_ELEMENT__OWNED_RULES);
		createEReference(methodElementEClass,
				METHOD_ELEMENT__METHOD_ELEMENT_PROPERTY);
		createEReference(methodElementEClass, METHOD_ELEMENT__KIND);
		createEAttribute(methodElementEClass, METHOD_ELEMENT__SUPPRESSED);
		createEAttribute(methodElementEClass, METHOD_ELEMENT__ORDERING_GUIDE);

		methodElementPropertyEClass = createEClass(METHOD_ELEMENT_PROPERTY);
		createEAttribute(methodElementPropertyEClass,
				METHOD_ELEMENT_PROPERTY__VALUE);

		kindEClass = createEClass(KIND);
		createEReference(kindEClass, KIND__APPLICABLE_META_CLASS_INFO);

		contentElementEClass = createEClass(CONTENT_ELEMENT);
		createEReference(contentElementEClass,
				CONTENT_ELEMENT__SUPPORTING_MATERIALS);
		createEReference(contentElementEClass,
				CONTENT_ELEMENT__CONCEPTS_AND_PAPERS);
		createEReference(contentElementEClass, CONTENT_ELEMENT__CHECKLISTS);
		createEReference(contentElementEClass, CONTENT_ELEMENT__GUIDELINES);
		createEReference(contentElementEClass, CONTENT_ELEMENT__EXAMPLES);
		createEReference(contentElementEClass, CONTENT_ELEMENT__ASSETS);
		createEReference(contentElementEClass, CONTENT_ELEMENT__TERMDEFINITION);

		describableElementEClass = createEClass(DESCRIBABLE_ELEMENT);
		createEReference(describableElementEClass,
				DESCRIBABLE_ELEMENT__PRESENTATION);
		createEAttribute(describableElementEClass,
				DESCRIBABLE_ELEMENT__SHAPEICON);
		createEAttribute(describableElementEClass,
				DESCRIBABLE_ELEMENT__NODEICON);

		contentDescriptionEClass = createEClass(CONTENT_DESCRIPTION);
		createEAttribute(contentDescriptionEClass,
				CONTENT_DESCRIPTION__MAIN_DESCRIPTION);
		createEReference(contentDescriptionEClass,
				CONTENT_DESCRIPTION__SECTIONS);
		createEAttribute(contentDescriptionEClass,
				CONTENT_DESCRIPTION__EXTERNAL_ID);
		createEAttribute(contentDescriptionEClass,
				CONTENT_DESCRIPTION__KEY_CONSIDERATIONS);
		createEAttribute(contentDescriptionEClass,
				CONTENT_DESCRIPTION__LONG_PRESENTATION_NAME);

		methodUnitEClass = createEClass(METHOD_UNIT);
		createEAttribute(methodUnitEClass, METHOD_UNIT__AUTHORS);
		createEAttribute(methodUnitEClass, METHOD_UNIT__CHANGE_DATE);
		createEAttribute(methodUnitEClass, METHOD_UNIT__CHANGE_DESCRIPTION);
		createEAttribute(methodUnitEClass, METHOD_UNIT__VERSION);
		createEReference(methodUnitEClass, METHOD_UNIT__COPYRIGHT_STATEMENT);

		supportingMaterialEClass = createEClass(SUPPORTING_MATERIAL);

		guidanceEClass = createEClass(GUIDANCE);

		sectionEClass = createEClass(SECTION);
		createEAttribute(sectionEClass, SECTION__SECTION_NAME);
		createEAttribute(sectionEClass, SECTION__SECTION_DESCRIPTION);
		createEReference(sectionEClass, SECTION__SUB_SECTIONS);
		createEReference(sectionEClass, SECTION__PREDECESSOR);

		variabilityElementEClass = createEClass(VARIABILITY_ELEMENT);
		createEAttribute(variabilityElementEClass,
				VARIABILITY_ELEMENT__VARIABILITY_TYPE);
		createEReference(variabilityElementEClass,
				VARIABILITY_ELEMENT__VARIABILITY_BASED_ON_ELEMENT);

		conceptEClass = createEClass(CONCEPT);

		checklistEClass = createEClass(CHECKLIST);

		guidelineEClass = createEClass(GUIDELINE);

		exampleEClass = createEClass(EXAMPLE);

		reusableAssetEClass = createEClass(REUSABLE_ASSET);

		termDefinitionEClass = createEClass(TERM_DEFINITION);

		applicableMetaClassInfoEClass = createEClass(APPLICABLE_META_CLASS_INFO);
		createEAttribute(applicableMetaClassInfoEClass,
				APPLICABLE_META_CLASS_INFO__IS_PRIMARY_EXTENSION);

		artifactEClass = createEClass(ARTIFACT);
		createEReference(artifactEClass, ARTIFACT__CONTAINER_ARTIFACT);
		createEReference(artifactEClass, ARTIFACT__CONTAINED_ARTIFACTS);

		workProductEClass = createEClass(WORK_PRODUCT);
		createEReference(workProductEClass, WORK_PRODUCT__REPORTS);
		createEReference(workProductEClass, WORK_PRODUCT__TEMPLATES);
		createEReference(workProductEClass, WORK_PRODUCT__TOOL_MENTORS);
		createEReference(workProductEClass,
				WORK_PRODUCT__ESTIMATION_CONSIDERATIONS);

		fulfillableElementEClass = createEClass(FULFILLABLE_ELEMENT);
		createEReference(fulfillableElementEClass,
				FULFILLABLE_ELEMENT__FULFILLS);

		reportEClass = createEClass(REPORT);

		templateEClass = createEClass(TEMPLATE);

		toolMentorEClass = createEClass(TOOL_MENTOR);

		estimationConsiderationsEClass = createEClass(ESTIMATION_CONSIDERATIONS);

		deliverableEClass = createEClass(DELIVERABLE);
		createEReference(deliverableEClass,
				DELIVERABLE__DELIVERED_WORK_PRODUCTS);

		outcomeEClass = createEClass(OUTCOME);

		stepEClass = createEClass(STEP);

		workDefinitionEClass = createEClass(WORK_DEFINITION);
		createEReference(workDefinitionEClass, WORK_DEFINITION__PRECONDITION);
		createEReference(workDefinitionEClass, WORK_DEFINITION__POSTCONDITION);

		whitepaperEClass = createEClass(WHITEPAPER);

		taskEClass = createEClass(TASK);
		createEReference(taskEClass, TASK__PERFORMED_BY);
		createEReference(taskEClass, TASK__MANDATORY_INPUT);
		createEReference(taskEClass, TASK__OUTPUT);
		createEReference(taskEClass, TASK__ADDITIONALLY_PERFORMED_BY);
		createEReference(taskEClass, TASK__OPTIONAL_INPUT);
		createEReference(taskEClass, TASK__STEPS);
		createEReference(taskEClass, TASK__TOOL_MENTORS);
		createEReference(taskEClass, TASK__ESTIMATION_CONSIDERATIONS);

		roleEClass = createEClass(ROLE);
		createEReference(roleEClass, ROLE__MODIFIES);
		createEReference(roleEClass, ROLE__RESPONSIBLE_FOR);

		artifactDescriptionEClass = createEClass(ARTIFACT_DESCRIPTION);
		createEAttribute(artifactDescriptionEClass,
				ARTIFACT_DESCRIPTION__BRIEF_OUTLINE);
		createEAttribute(artifactDescriptionEClass,
				ARTIFACT_DESCRIPTION__REPRESENTATION_OPTIONS);
		createEAttribute(artifactDescriptionEClass,
				ARTIFACT_DESCRIPTION__REPRESENTATION);
		createEAttribute(artifactDescriptionEClass,
				ARTIFACT_DESCRIPTION__NOTATION);

		workProductDescriptionEClass = createEClass(WORK_PRODUCT_DESCRIPTION);
		createEAttribute(workProductDescriptionEClass,
				WORK_PRODUCT_DESCRIPTION__PURPOSE);
		createEAttribute(workProductDescriptionEClass,
				WORK_PRODUCT_DESCRIPTION__IMPACT_OF_NOT_HAVING);
		createEAttribute(workProductDescriptionEClass,
				WORK_PRODUCT_DESCRIPTION__REASONS_FOR_NOT_NEEDING);

		deliverableDescriptionEClass = createEClass(DELIVERABLE_DESCRIPTION);
		createEAttribute(deliverableDescriptionEClass,
				DELIVERABLE_DESCRIPTION__EXTERNAL_DESCRIPTION);
		createEAttribute(deliverableDescriptionEClass,
				DELIVERABLE_DESCRIPTION__PACKAGING_GUIDANCE);

		roleDescriptionEClass = createEClass(ROLE_DESCRIPTION);
		createEAttribute(roleDescriptionEClass, ROLE_DESCRIPTION__SKILLS);
		createEAttribute(roleDescriptionEClass,
				ROLE_DESCRIPTION__ASSIGNMENT_APPROACHES);
		createEAttribute(roleDescriptionEClass, ROLE_DESCRIPTION__SYNONYMS);

		taskDescriptionEClass = createEClass(TASK_DESCRIPTION);
		createEAttribute(taskDescriptionEClass, TASK_DESCRIPTION__PURPOSE);
		createEAttribute(taskDescriptionEClass, TASK_DESCRIPTION__ALTERNATIVES);

		guidanceDescriptionEClass = createEClass(GUIDANCE_DESCRIPTION);
		createEAttribute(guidanceDescriptionEClass,
				GUIDANCE_DESCRIPTION__ATTACHMENTS);

		practiceDescriptionEClass = createEClass(PRACTICE_DESCRIPTION);
		createEAttribute(practiceDescriptionEClass,
				PRACTICE_DESCRIPTION__ADDITIONAL_INFO);
		createEAttribute(practiceDescriptionEClass,
				PRACTICE_DESCRIPTION__PROBLEM);
		createEAttribute(practiceDescriptionEClass,
				PRACTICE_DESCRIPTION__BACKGROUND);
		createEAttribute(practiceDescriptionEClass, PRACTICE_DESCRIPTION__GOALS);
		createEAttribute(practiceDescriptionEClass,
				PRACTICE_DESCRIPTION__APPLICATION);
		createEAttribute(practiceDescriptionEClass,
				PRACTICE_DESCRIPTION__LEVELS_OF_ADOPTION);

		roleSetEClass = createEClass(ROLE_SET);
		createEReference(roleSetEClass, ROLE_SET__ROLES);

		contentCategoryEClass = createEClass(CONTENT_CATEGORY);

		domainEClass = createEClass(DOMAIN);
		createEReference(domainEClass, DOMAIN__WORK_PRODUCTS);
		createEReference(domainEClass, DOMAIN__SUBDOMAINS);

		workProductTypeEClass = createEClass(WORK_PRODUCT_TYPE);
		createEReference(workProductTypeEClass,
				WORK_PRODUCT_TYPE__WORK_PRODUCTS);

		disciplineGroupingEClass = createEClass(DISCIPLINE_GROUPING);
		createEReference(disciplineGroupingEClass,
				DISCIPLINE_GROUPING__DISCIPLINES);

		disciplineEClass = createEClass(DISCIPLINE);
		createEReference(disciplineEClass, DISCIPLINE__TASKS);
		createEReference(disciplineEClass, DISCIPLINE__SUBDISCIPLINE);
		createEReference(disciplineEClass, DISCIPLINE__REFERENCE_WORKFLOWS);

		activityEClass = createEClass(ACTIVITY);
		createEReference(activityEClass, ACTIVITY__BREAKDOWN_ELEMENTS);
		createEReference(activityEClass, ACTIVITY__ROADMAPS);

		workBreakdownElementEClass = createEClass(WORK_BREAKDOWN_ELEMENT);
		createEAttribute(workBreakdownElementEClass,
				WORK_BREAKDOWN_ELEMENT__IS_REPEATABLE);
		createEAttribute(workBreakdownElementEClass,
				WORK_BREAKDOWN_ELEMENT__IS_ONGOING);
		createEAttribute(workBreakdownElementEClass,
				WORK_BREAKDOWN_ELEMENT__IS_EVENT_DRIVEN);
		createEReference(workBreakdownElementEClass,
				WORK_BREAKDOWN_ELEMENT__LINK_TO_PREDECESSOR);

		breakdownElementEClass = createEClass(BREAKDOWN_ELEMENT);
		createEAttribute(breakdownElementEClass, BREAKDOWN_ELEMENT__PREFIX);
		createEAttribute(breakdownElementEClass, BREAKDOWN_ELEMENT__IS_PLANNED);
		createEAttribute(breakdownElementEClass,
				BREAKDOWN_ELEMENT__HAS_MULTIPLE_OCCURRENCES);
		createEAttribute(breakdownElementEClass, BREAKDOWN_ELEMENT__IS_OPTIONAL);
		createEReference(breakdownElementEClass,
				BREAKDOWN_ELEMENT__PRESENTED_AFTER);
		createEReference(breakdownElementEClass,
				BREAKDOWN_ELEMENT__PRESENTED_BEFORE);
		createEReference(breakdownElementEClass,
				BREAKDOWN_ELEMENT__PLANNING_DATA);
		createEReference(breakdownElementEClass,
				BREAKDOWN_ELEMENT__SUPER_ACTIVITIES);
		createEReference(breakdownElementEClass, BREAKDOWN_ELEMENT__CHECKLISTS);
		createEReference(breakdownElementEClass, BREAKDOWN_ELEMENT__CONCEPTS);
		createEReference(breakdownElementEClass, BREAKDOWN_ELEMENT__EXAMPLES);
		createEReference(breakdownElementEClass, BREAKDOWN_ELEMENT__GUIDELINES);
		createEReference(breakdownElementEClass,
				BREAKDOWN_ELEMENT__REUSABLE_ASSETS);
		createEReference(breakdownElementEClass,
				BREAKDOWN_ELEMENT__SUPPORTING_MATERIALS);
		createEReference(breakdownElementEClass, BREAKDOWN_ELEMENT__TEMPLATES);
		createEReference(breakdownElementEClass, BREAKDOWN_ELEMENT__REPORTS);
		createEReference(breakdownElementEClass,
				BREAKDOWN_ELEMENT__ESTIMATIONCONSIDERATIONS);
		createEReference(breakdownElementEClass, BREAKDOWN_ELEMENT__TOOLMENTOR);

		processElementEClass = createEClass(PROCESS_ELEMENT);

		planningDataEClass = createEClass(PLANNING_DATA);
		createEAttribute(planningDataEClass, PLANNING_DATA__START_DATE);
		createEAttribute(planningDataEClass, PLANNING_DATA__FINISH_DATE);
		createEAttribute(planningDataEClass, PLANNING_DATA__RANK);

		workOrderEClass = createEClass(WORK_ORDER);
		createEAttribute(workOrderEClass, WORK_ORDER__LINK_TYPE);
		createEReference(workOrderEClass, WORK_ORDER__PRED);

		roadmapEClass = createEClass(ROADMAP);

		toolEClass = createEClass(TOOL);
		createEReference(toolEClass, TOOL__TOOL_MENTORS);

		roleSetGroupingEClass = createEClass(ROLE_SET_GROUPING);
		createEReference(roleSetGroupingEClass, ROLE_SET_GROUPING__ROLE_SETS);

		customCategoryEClass = createEClass(CUSTOM_CATEGORY);
		createEReference(customCategoryEClass,
				CUSTOM_CATEGORY__CATEGORIZED_ELEMENTS);
		createEReference(customCategoryEClass, CUSTOM_CATEGORY__SUB_CATEGORIES);

		methodPackageEClass = createEClass(METHOD_PACKAGE);
		createEAttribute(methodPackageEClass, METHOD_PACKAGE__GLOBAL);
		createEReference(methodPackageEClass, METHOD_PACKAGE__REUSED_PACKAGES);
		createEReference(methodPackageEClass, METHOD_PACKAGE__CHILD_PACKAGES);

		contentPackageEClass = createEClass(CONTENT_PACKAGE);
		createEReference(contentPackageEClass,
				CONTENT_PACKAGE__CONTENT_ELEMENTS);

		milestoneEClass = createEClass(MILESTONE);
		createEReference(milestoneEClass, MILESTONE__REQUIRED_RESULTS);

		workProductDescriptorEClass = createEClass(WORK_PRODUCT_DESCRIPTOR);
		createEAttribute(workProductDescriptorEClass,
				WORK_PRODUCT_DESCRIPTOR__ACTIVITY_ENTRY_STATE);
		createEAttribute(workProductDescriptorEClass,
				WORK_PRODUCT_DESCRIPTOR__ACTIVITY_EXIT_STATE);
		createEReference(workProductDescriptorEClass,
				WORK_PRODUCT_DESCRIPTOR__WORK_PRODUCT);
		createEReference(workProductDescriptorEClass,
				WORK_PRODUCT_DESCRIPTOR__IMPACTED_BY);
		createEReference(workProductDescriptorEClass,
				WORK_PRODUCT_DESCRIPTOR__IMPACTS);
		createEReference(workProductDescriptorEClass,
				WORK_PRODUCT_DESCRIPTOR__DELIVERABLE_PARTS);
		createEReference(workProductDescriptorEClass,
				WORK_PRODUCT_DESCRIPTOR__DELIVERABLE_PARTS_EXCLUDE);

		descriptorEClass = createEClass(DESCRIPTOR);
		createEAttribute(descriptorEClass,
				DESCRIPTOR__IS_SYNCHRONIZED_WITH_SOURCE);
		createEReference(descriptorEClass, DESCRIPTOR__GUIDANCE_EXCLUDE);
		createEReference(descriptorEClass, DESCRIPTOR__GUIDANCE_ADDITIONAL);

		iterationEClass = createEClass(ITERATION);

		phaseEClass = createEClass(PHASE);

		teamProfileEClass = createEClass(TEAM_PROFILE);
		createEReference(teamProfileEClass, TEAM_PROFILE__TEAM_ROLES);
		createEReference(teamProfileEClass, TEAM_PROFILE__SUPER_TEAM);
		createEReference(teamProfileEClass, TEAM_PROFILE__SUB_TEAM);

		roleDescriptorEClass = createEClass(ROLE_DESCRIPTOR);
		createEReference(roleDescriptorEClass, ROLE_DESCRIPTOR__ROLE);
		createEReference(roleDescriptorEClass, ROLE_DESCRIPTOR__MODIFIES);
		createEReference(roleDescriptorEClass, ROLE_DESCRIPTOR__RESPONSIBLE_FOR);
		createEReference(roleDescriptorEClass,
				ROLE_DESCRIPTOR__RESPONSIBLE_FOR_EXCLUDE);

		taskDescriptorEClass = createEClass(TASK_DESCRIPTOR);
		createEReference(taskDescriptorEClass, TASK_DESCRIPTOR__TASK);
		createEReference(taskDescriptorEClass,
				TASK_DESCRIPTOR__ADDITIONALLY_PERFORMED_BY);
		createEReference(taskDescriptorEClass, TASK_DESCRIPTOR__ASSISTED_BY);
		createEReference(taskDescriptorEClass, TASK_DESCRIPTOR__EXTERNAL_INPUT);
		createEReference(taskDescriptorEClass, TASK_DESCRIPTOR__MANDATORY_INPUT);
		createEReference(taskDescriptorEClass, TASK_DESCRIPTOR__OPTIONAL_INPUT);
		createEReference(taskDescriptorEClass, TASK_DESCRIPTOR__OUTPUT);
		createEReference(taskDescriptorEClass,
				TASK_DESCRIPTOR__PERFORMED_PRIMARILY_BY);
		createEReference(taskDescriptorEClass, TASK_DESCRIPTOR__SELECTED_STEPS);
		createEReference(taskDescriptorEClass,
				TASK_DESCRIPTOR__PERFORMED_PRIMARILY_BY_EXCLUDED);
		createEReference(taskDescriptorEClass,
				TASK_DESCRIPTOR__ADDITIONALLY_PERFORMED_BY_EXCLUDE);
		createEReference(taskDescriptorEClass,
				TASK_DESCRIPTOR__MANDATORY_INPUT_EXCLUDE);
		createEReference(taskDescriptorEClass,
				TASK_DESCRIPTOR__OPTIONAL_INPUT_EXCLUDE);
		createEReference(taskDescriptorEClass, TASK_DESCRIPTOR__OUTPUT_EXCLUDE);
		createEReference(taskDescriptorEClass,
				TASK_DESCRIPTOR__SELECTED_STEPS_EXCLUDE);

		compositeRoleEClass = createEClass(COMPOSITE_ROLE);
		createEReference(compositeRoleEClass, COMPOSITE_ROLE__AGGREGATED_ROLES);

		deliveryProcessEClass = createEClass(DELIVERY_PROCESS);
		createEReference(deliveryProcessEClass,
				DELIVERY_PROCESS__EDUCATION_MATERIALS);
		createEReference(deliveryProcessEClass,
				DELIVERY_PROCESS__COMMUNICATIONS_MATERIALS);

		processEClass = createEClass(PROCESS);
		createEReference(processEClass, PROCESS__INCLUDES_PATTERNS);
		createEReference(processEClass, PROCESS__DEFAULT_CONTEXT);
		createEReference(processEClass, PROCESS__VALID_CONTEXT);

		capabilityPatternEClass = createEClass(CAPABILITY_PATTERN);

		methodConfigurationEClass = createEClass(METHOD_CONFIGURATION);
		createEReference(methodConfigurationEClass,
				METHOD_CONFIGURATION__METHOD_PLUGIN_SELECTION);
		createEReference(methodConfigurationEClass,
				METHOD_CONFIGURATION__METHOD_PACKAGE_SELECTION);
		createEReference(methodConfigurationEClass,
				METHOD_CONFIGURATION__PROCESS_VIEWS);
		createEReference(methodConfigurationEClass,
				METHOD_CONFIGURATION__DEFAULT_VIEW);
		createEReference(methodConfigurationEClass,
				METHOD_CONFIGURATION__BASE_CONFIGURATIONS);
		createEReference(methodConfigurationEClass,
				METHOD_CONFIGURATION__SUBTRACTED_CATEGORY);
		createEReference(methodConfigurationEClass,
				METHOD_CONFIGURATION__ADDED_CATEGORY);

		methodPluginEClass = createEClass(METHOD_PLUGIN);
		createEAttribute(methodPluginEClass, METHOD_PLUGIN__USER_CHANGEABLE);
		createEReference(methodPluginEClass, METHOD_PLUGIN__METHOD_PACKAGES);
		createEReference(methodPluginEClass, METHOD_PLUGIN__BASES);
		createEAttribute(methodPluginEClass, METHOD_PLUGIN__SUPPORTING);

		processPlanningTemplateEClass = createEClass(PROCESS_PLANNING_TEMPLATE);
		createEReference(processPlanningTemplateEClass,
				PROCESS_PLANNING_TEMPLATE__BASED_ON_PROCESSES);

		practiceEClass = createEClass(PRACTICE);
		createEReference(practiceEClass, PRACTICE__SUB_PRACTICES);
		createEReference(practiceEClass, PRACTICE__CONTENT_REFERENCES);
		createEReference(practiceEClass, PRACTICE__ACTIVITY_REFERENCES);

		breakdownElementDescriptionEClass = createEClass(BREAKDOWN_ELEMENT_DESCRIPTION);
		createEAttribute(breakdownElementDescriptionEClass,
				BREAKDOWN_ELEMENT_DESCRIPTION__USAGE_GUIDANCE);

		activityDescriptionEClass = createEClass(ACTIVITY_DESCRIPTION);
		createEAttribute(activityDescriptionEClass,
				ACTIVITY_DESCRIPTION__PURPOSE);
		createEAttribute(activityDescriptionEClass,
				ACTIVITY_DESCRIPTION__ALTERNATIVES);
		createEAttribute(activityDescriptionEClass,
				ACTIVITY_DESCRIPTION__HOWTO_STAFF);

		deliveryProcessDescriptionEClass = createEClass(DELIVERY_PROCESS_DESCRIPTION);
		createEAttribute(deliveryProcessDescriptionEClass,
				DELIVERY_PROCESS_DESCRIPTION__SCALE);
		createEAttribute(deliveryProcessDescriptionEClass,
				DELIVERY_PROCESS_DESCRIPTION__PROJECT_CHARACTERISTICS);
		createEAttribute(deliveryProcessDescriptionEClass,
				DELIVERY_PROCESS_DESCRIPTION__RISK_LEVEL);
		createEAttribute(deliveryProcessDescriptionEClass,
				DELIVERY_PROCESS_DESCRIPTION__ESTIMATING_TECHNIQUE);
		createEAttribute(deliveryProcessDescriptionEClass,
				DELIVERY_PROCESS_DESCRIPTION__PROJECT_MEMBER_EXPERTISE);
		createEAttribute(deliveryProcessDescriptionEClass,
				DELIVERY_PROCESS_DESCRIPTION__TYPE_OF_CONTRACT);

		processDescriptionEClass = createEClass(PROCESS_DESCRIPTION);
		createEAttribute(processDescriptionEClass, PROCESS_DESCRIPTION__SCOPE);
		createEAttribute(processDescriptionEClass,
				PROCESS_DESCRIPTION__USAGE_NOTES);

		descriptorDescriptionEClass = createEClass(DESCRIPTOR_DESCRIPTION);
		createEAttribute(descriptorDescriptionEClass,
				DESCRIPTOR_DESCRIPTION__REFINED_DESCRIPTION);

		processComponentDescriptorEClass = createEClass(PROCESS_COMPONENT_DESCRIPTOR);
		createEReference(processComponentDescriptorEClass,
				PROCESS_COMPONENT_DESCRIPTOR__PROCESS_COMPONENT);

		processComponentEClass = createEClass(PROCESS_COMPONENT);
		createEReference(processComponentEClass, PROCESS_COMPONENT__INTERFACES);
		createEReference(processComponentEClass, PROCESS_COMPONENT__PROCESS);

		processPackageEClass = createEClass(PROCESS_PACKAGE);
		createEReference(processPackageEClass,
				PROCESS_PACKAGE__PROCESS_ELEMENTS);
		createEReference(processPackageEClass, PROCESS_PACKAGE__DIAGRAMS);

		diagramEClass = createEClass(DIAGRAM);
		createEReference(diagramEClass, DIAGRAM__DIAGRAM_LINK);
		createEReference(diagramEClass, DIAGRAM__NAMESPACE);
		createEAttribute(diagramEClass, DIAGRAM__ZOOM);
		createEReference(diagramEClass, DIAGRAM__VIEWPOINT);

		graphNodeEClass = createEClass(GRAPH_NODE);
		createEReference(graphNodeEClass, GRAPH_NODE__SIZE);

		graphElementEClass = createEClass(GRAPH_ELEMENT);
		createEReference(graphElementEClass, GRAPH_ELEMENT__CONTAINED);
		createEReference(graphElementEClass, GRAPH_ELEMENT__POSITION);
		createEReference(graphElementEClass, GRAPH_ELEMENT__LINK);
		createEReference(graphElementEClass, GRAPH_ELEMENT__ANCHORAGE);
		createEReference(graphElementEClass, GRAPH_ELEMENT__SEMANTIC_MODEL);

		diagramElementEClass = createEClass(DIAGRAM_ELEMENT);
		createEAttribute(diagramElementEClass, DIAGRAM_ELEMENT__IS_VISIBLE);
		createEReference(diagramElementEClass, DIAGRAM_ELEMENT__CONTAINER);
		createEReference(diagramElementEClass, DIAGRAM_ELEMENT__REFERENCE);
		createEReference(diagramElementEClass, DIAGRAM_ELEMENT__PROPERTY);

		referenceEClass = createEClass(REFERENCE);
		createEAttribute(referenceEClass,
				REFERENCE__IS_INDIVIDUAL_REPRESENTATION);
		createEReference(referenceEClass, REFERENCE__REFERENCED);

		propertyEClass = createEClass(PROPERTY);
		createEAttribute(propertyEClass, PROPERTY__KEY);
		createEAttribute(propertyEClass, PROPERTY__VALUE);

		pointEClass = createEClass(POINT);
		createEAttribute(pointEClass, POINT__X);
		createEAttribute(pointEClass, POINT__Y);

		diagramLinkEClass = createEClass(DIAGRAM_LINK);
		createEAttribute(diagramLinkEClass, DIAGRAM_LINK__ZOOM);
		createEReference(diagramLinkEClass, DIAGRAM_LINK__VIEWPORT);
		createEReference(diagramLinkEClass, DIAGRAM_LINK__DIAGRAM);
		createEReference(diagramLinkEClass, DIAGRAM_LINK__GRAPH_ELEMENT);

		graphConnectorEClass = createEClass(GRAPH_CONNECTOR);
		createEReference(graphConnectorEClass, GRAPH_CONNECTOR__GRAPH_EDGE);
		createEReference(graphConnectorEClass, GRAPH_CONNECTOR__GRAPH_ELEMENT);

		graphEdgeEClass = createEClass(GRAPH_EDGE);
		createEReference(graphEdgeEClass, GRAPH_EDGE__WAYPOINTS);
		createEReference(graphEdgeEClass, GRAPH_EDGE__ANCHOR);

		semanticModelBridgeEClass = createEClass(SEMANTIC_MODEL_BRIDGE);
		createEAttribute(semanticModelBridgeEClass,
				SEMANTIC_MODEL_BRIDGE__PRESENTATION);
		createEReference(semanticModelBridgeEClass,
				SEMANTIC_MODEL_BRIDGE__DIAGRAM);
		createEReference(semanticModelBridgeEClass,
				SEMANTIC_MODEL_BRIDGE__GRAPH_ELEMENT);

		dimensionEClass = createEClass(DIMENSION);
		createEAttribute(dimensionEClass, DIMENSION__WIDTH);
		createEAttribute(dimensionEClass, DIMENSION__HEIGHT);

		processComponentInterfaceEClass = createEClass(PROCESS_COMPONENT_INTERFACE);
		createEReference(processComponentInterfaceEClass,
				PROCESS_COMPONENT_INTERFACE__INTERFACE_SPECIFICATIONS);
		createEReference(processComponentInterfaceEClass,
				PROCESS_COMPONENT_INTERFACE__INTERFACE_IO);

		simpleSemanticModelElementEClass = createEClass(SIMPLE_SEMANTIC_MODEL_ELEMENT);
		createEAttribute(simpleSemanticModelElementEClass,
				SIMPLE_SEMANTIC_MODEL_ELEMENT__TYPE_INFO);

		umaSemanticModelBridgeEClass = createEClass(UMA_SEMANTIC_MODEL_BRIDGE);
		createEReference(umaSemanticModelBridgeEClass,
				UMA_SEMANTIC_MODEL_BRIDGE__ELEMENT);

		coreSemanticModelBridgeEClass = createEClass(CORE_SEMANTIC_MODEL_BRIDGE);
		createEReference(coreSemanticModelBridgeEClass,
				CORE_SEMANTIC_MODEL_BRIDGE__ELEMENT);

		leafElementEClass = createEClass(LEAF_ELEMENT);

		textElementEClass = createEClass(TEXT_ELEMENT);
		createEAttribute(textElementEClass, TEXT_ELEMENT__TEXT);

		imageEClass = createEClass(IMAGE);
		createEAttribute(imageEClass, IMAGE__URI);
		createEAttribute(imageEClass, IMAGE__MIME_TYPE);

		graphicPrimitiveEClass = createEClass(GRAPHIC_PRIMITIVE);

		polylineEClass = createEClass(POLYLINE);
		createEAttribute(polylineEClass, POLYLINE__CLOSED);
		createEReference(polylineEClass, POLYLINE__WAYPOINTS);

		ellipseEClass = createEClass(ELLIPSE);
		createEReference(ellipseEClass, ELLIPSE__CENTER);
		createEAttribute(ellipseEClass, ELLIPSE__RADIUS_X);
		createEAttribute(ellipseEClass, ELLIPSE__RADIUS_Y);
		createEAttribute(ellipseEClass, ELLIPSE__ROTATION);
		createEAttribute(ellipseEClass, ELLIPSE__START_ANGLE);
		createEAttribute(ellipseEClass, ELLIPSE__END_ANGLE);

		processFamilyEClass = createEClass(PROCESS_FAMILY);
		createEReference(processFamilyEClass,
				PROCESS_FAMILY__DELIVERY_PROCESSES);

		methodLibraryEClass = createEClass(METHOD_LIBRARY);
		createEReference(methodLibraryEClass, METHOD_LIBRARY__METHOD_PLUGINS);
		createEReference(methodLibraryEClass,
				METHOD_LIBRARY__PREDEFINED_CONFIGURATIONS);

		// Create enums
		variabilityTypeEEnum = createEEnum(VARIABILITY_TYPE);
		workOrderTypeEEnum = createEEnum(WORK_ORDER_TYPE);

		// Create data types
		stringEDataType = createEDataType(STRING);
		booleanEDataType = createEDataType(BOOLEAN);
		dateEDataType = createEDataType(DATE);
		uriEDataType = createEDataType(URI);
		setEDataType = createEDataType(SET);
		sequenceEDataType = createEDataType(SEQUENCE);
		integerEDataType = createEDataType(INTEGER);
		doubleEDataType = createEDataType(DOUBLE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized)
			return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		classifierEClass.getESuperTypes().add(this.getType());
		typeEClass.getESuperTypes().add(this.getPackageableElement());
		packageableElementEClass.getESuperTypes().add(this.getNamedElement());
		namedElementEClass.getESuperTypes().add(this.getElement());
		packageEClass.getESuperTypes().add(this.getNamespace());
		packageEClass.getESuperTypes().add(this.getPackageableElement());
		namespaceEClass.getESuperTypes().add(this.getNamedElement());
		constraintEClass.getESuperTypes().add(this.getMethodElement());
		methodElementEClass.getESuperTypes().add(this.getPackageableElement());
		methodElementPropertyEClass.getESuperTypes().add(
				this.getPackageableElement());
		kindEClass.getESuperTypes().add(this.getContentElement());
		contentElementEClass.getESuperTypes().add(this.getDescribableElement());
		contentElementEClass.getESuperTypes().add(this.getVariabilityElement());
		describableElementEClass.getESuperTypes().add(this.getMethodElement());
		describableElementEClass.getESuperTypes().add(this.getClassifier());
		contentDescriptionEClass.getESuperTypes().add(this.getMethodUnit());
		methodUnitEClass.getESuperTypes().add(this.getMethodElement());
		supportingMaterialEClass.getESuperTypes().add(this.getGuidance());
		guidanceEClass.getESuperTypes().add(this.getContentElement());
		sectionEClass.getESuperTypes().add(this.getVariabilityElement());
		variabilityElementEClass.getESuperTypes().add(this.getMethodElement());
		conceptEClass.getESuperTypes().add(this.getGuidance());
		checklistEClass.getESuperTypes().add(this.getGuidance());
		guidelineEClass.getESuperTypes().add(this.getGuidance());
		exampleEClass.getESuperTypes().add(this.getGuidance());
		reusableAssetEClass.getESuperTypes().add(this.getGuidance());
		termDefinitionEClass.getESuperTypes().add(this.getGuidance());
		applicableMetaClassInfoEClass.getESuperTypes()
				.add(this.getClassifier());
		artifactEClass.getESuperTypes().add(this.getWorkProduct());
		workProductEClass.getESuperTypes().add(this.getContentElement());
		workProductEClass.getESuperTypes().add(this.getFulfillableElement());
		fulfillableElementEClass.getESuperTypes().add(
				this.getDescribableElement());
		reportEClass.getESuperTypes().add(this.getGuidance());
		templateEClass.getESuperTypes().add(this.getGuidance());
		toolMentorEClass.getESuperTypes().add(this.getGuidance());
		estimationConsiderationsEClass.getESuperTypes().add(this.getGuidance());
		deliverableEClass.getESuperTypes().add(this.getWorkProduct());
		outcomeEClass.getESuperTypes().add(this.getWorkProduct());
		stepEClass.getESuperTypes().add(this.getSection());
		stepEClass.getESuperTypes().add(this.getWorkDefinition());
		workDefinitionEClass.getESuperTypes().add(this.getMethodElement());
		whitepaperEClass.getESuperTypes().add(this.getConcept());
		taskEClass.getESuperTypes().add(this.getContentElement());
		taskEClass.getESuperTypes().add(this.getWorkDefinition());
		roleEClass.getESuperTypes().add(this.getContentElement());
		roleEClass.getESuperTypes().add(this.getFulfillableElement());
		artifactDescriptionEClass.getESuperTypes().add(
				this.getWorkProductDescription());
		workProductDescriptionEClass.getESuperTypes().add(
				this.getContentDescription());
		deliverableDescriptionEClass.getESuperTypes().add(
				this.getWorkProductDescription());
		roleDescriptionEClass.getESuperTypes()
				.add(this.getContentDescription());
		taskDescriptionEClass.getESuperTypes()
				.add(this.getContentDescription());
		guidanceDescriptionEClass.getESuperTypes().add(
				this.getContentDescription());
		practiceDescriptionEClass.getESuperTypes().add(
				this.getContentDescription());
		roleSetEClass.getESuperTypes().add(this.getContentCategory());
		contentCategoryEClass.getESuperTypes().add(this.getContentElement());
		domainEClass.getESuperTypes().add(this.getContentCategory());
		workProductTypeEClass.getESuperTypes().add(this.getContentCategory());
		disciplineGroupingEClass.getESuperTypes()
				.add(this.getContentCategory());
		disciplineEClass.getESuperTypes().add(this.getContentCategory());
		activityEClass.getESuperTypes().add(this.getWorkBreakdownElement());
		activityEClass.getESuperTypes().add(this.getFulfillableElement());
		activityEClass.getESuperTypes().add(this.getVariabilityElement());
		activityEClass.getESuperTypes().add(this.getWorkDefinition());
		workBreakdownElementEClass.getESuperTypes().add(
				this.getBreakdownElement());
		breakdownElementEClass.getESuperTypes().add(this.getProcessElement());
		processElementEClass.getESuperTypes().add(this.getDescribableElement());
		planningDataEClass.getESuperTypes().add(this.getProcessElement());
		workOrderEClass.getESuperTypes().add(this.getProcessElement());
		roadmapEClass.getESuperTypes().add(this.getGuidance());
		toolEClass.getESuperTypes().add(this.getContentCategory());
		roleSetGroupingEClass.getESuperTypes().add(this.getContentCategory());
		customCategoryEClass.getESuperTypes().add(this.getContentCategory());
		methodPackageEClass.getESuperTypes().add(this.getMethodElement());
		methodPackageEClass.getESuperTypes().add(this.getPackage());
		contentPackageEClass.getESuperTypes().add(this.getMethodPackage());
		milestoneEClass.getESuperTypes().add(this.getWorkBreakdownElement());
		workProductDescriptorEClass.getESuperTypes().add(this.getDescriptor());
		descriptorEClass.getESuperTypes().add(this.getBreakdownElement());
		iterationEClass.getESuperTypes().add(this.getActivity());
		phaseEClass.getESuperTypes().add(this.getActivity());
		teamProfileEClass.getESuperTypes().add(this.getBreakdownElement());
		roleDescriptorEClass.getESuperTypes().add(this.getDescriptor());
		taskDescriptorEClass.getESuperTypes().add(
				this.getWorkBreakdownElement());
		taskDescriptorEClass.getESuperTypes().add(this.getDescriptor());
		compositeRoleEClass.getESuperTypes().add(this.getRoleDescriptor());
		deliveryProcessEClass.getESuperTypes().add(this.getProcess());
		processEClass.getESuperTypes().add(this.getActivity());
		capabilityPatternEClass.getESuperTypes().add(this.getProcess());
		methodConfigurationEClass.getESuperTypes().add(this.getMethodUnit());
		methodPluginEClass.getESuperTypes().add(this.getMethodUnit());
		methodPluginEClass.getESuperTypes().add(this.getPackage());
		processPlanningTemplateEClass.getESuperTypes().add(this.getProcess());
		practiceEClass.getESuperTypes().add(this.getGuidance());
		breakdownElementDescriptionEClass.getESuperTypes().add(
				this.getContentDescription());
		activityDescriptionEClass.getESuperTypes().add(
				this.getBreakdownElementDescription());
		deliveryProcessDescriptionEClass.getESuperTypes().add(
				this.getProcessDescription());
		processDescriptionEClass.getESuperTypes().add(
				this.getActivityDescription());
		descriptorDescriptionEClass.getESuperTypes().add(
				this.getBreakdownElementDescription());
		processComponentDescriptorEClass.getESuperTypes().add(
				this.getDescriptor());
		processComponentEClass.getESuperTypes().add(this.getProcessPackage());
		processComponentEClass.getESuperTypes().add(this.getMethodUnit());
		processPackageEClass.getESuperTypes().add(this.getMethodPackage());
		diagramEClass.getESuperTypes().add(this.getGraphNode());
		graphNodeEClass.getESuperTypes().add(this.getGraphElement());
		graphElementEClass.getESuperTypes().add(this.getDiagramElement());
		diagramElementEClass.getESuperTypes().add(this.getMethodElement());
		referenceEClass.getESuperTypes().add(this.getDiagramElement());
		propertyEClass.getESuperTypes().add(this.getDiagramElement());
		diagramLinkEClass.getESuperTypes().add(this.getDiagramElement());
		graphConnectorEClass.getESuperTypes().add(this.getGraphElement());
		graphEdgeEClass.getESuperTypes().add(this.getGraphElement());
		semanticModelBridgeEClass.getESuperTypes()
				.add(this.getDiagramElement());
		processComponentInterfaceEClass.getESuperTypes().add(
				this.getBreakdownElement());
		simpleSemanticModelElementEClass.getESuperTypes().add(
				this.getSemanticModelBridge());
		umaSemanticModelBridgeEClass.getESuperTypes().add(
				this.getSemanticModelBridge());
		coreSemanticModelBridgeEClass.getESuperTypes().add(
				this.getSemanticModelBridge());
		leafElementEClass.getESuperTypes().add(this.getDiagramElement());
		textElementEClass.getESuperTypes().add(this.getLeafElement());
		imageEClass.getESuperTypes().add(this.getLeafElement());
		graphicPrimitiveEClass.getESuperTypes().add(this.getLeafElement());
		polylineEClass.getESuperTypes().add(this.getGraphicPrimitive());
		ellipseEClass.getESuperTypes().add(this.getGraphicPrimitive());
		processFamilyEClass.getESuperTypes().add(this.getMethodConfiguration());
		methodLibraryEClass.getESuperTypes().add(this.getMethodUnit());
		methodLibraryEClass.getESuperTypes().add(this.getPackage());

		// Initialize classes and features; add operations and parameters
		initEClass(
				classifierEClass,
				Classifier.class,
				"Classifier", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getClassifier_IsAbstract(),
				this.getBoolean(),
				"isAbstract", "false", 1, 1, Classifier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(typeEClass, Type.class,
				"Type", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				packageableElementEClass,
				PackageableElement.class,
				"PackageableElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				namedElementEClass,
				NamedElement.class,
				"NamedElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getNamedElement_Name(),
				this.getString(),
				"name", "", 0, 1, NamedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(
				elementEClass,
				Element.class,
				"Element", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				packageEClass,
				org.eclipse.epf.uma.Package.class,
				"Package", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				namespaceEClass,
				Namespace.class,
				"Namespace", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				constraintEClass,
				Constraint.class,
				"Constraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getConstraint_Body(),
				this.getString(),
				"body", "", 0, 1, Constraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(
				methodElementEClass,
				MethodElement.class,
				"MethodElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getMethodElement_Guid(),
				this.getString(),
				"guid", "", 0, 1, MethodElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getMethodElement_PresentationName(),
				this.getString(),
				"presentationName", "", 0, 1, MethodElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getMethodElement_BriefDescription(),
				this.getString(),
				"briefDescription", "", 0, 1, MethodElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(
				getMethodElement_OwnedRules(),
				this.getConstraint(),
				null,
				"ownedRules", null, 0, -1, MethodElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getMethodElement_MethodElementProperty(),
				this.getMethodElementProperty(),
				null,
				"methodElementProperty", null, 0, -1, MethodElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getMethodElement_Kind(),
				this.getKind(),
				null,
				"kind", null, 0, -1, MethodElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEAttribute(
				getMethodElement_Suppressed(),
				this.getBoolean(),
				"suppressed", "false", 1, 1, MethodElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getMethodElement_OrderingGuide(),
				this.getString(),
				"orderingGuide", "", 0, 1, MethodElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(
				methodElementPropertyEClass,
				MethodElementProperty.class,
				"MethodElementProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getMethodElementProperty_Value(),
				this.getString(),
				"value", "", 0, 1, MethodElementProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(
				kindEClass,
				Kind.class,
				"Kind", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getKind_ApplicableMetaClassInfo(),
				this.getApplicableMetaClassInfo(),
				null,
				"applicableMetaClassInfo", null, 0, -1, Kind.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				contentElementEClass,
				ContentElement.class,
				"ContentElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getContentElement_SupportingMaterials(),
				this.getSupportingMaterial(),
				null,
				"supportingMaterials", null, 0, -1, ContentElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getContentElement_ConceptsAndPapers(),
				this.getConcept(),
				null,
				"conceptsAndPapers", null, 0, -1, ContentElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getContentElement_Checklists(),
				this.getChecklist(),
				null,
				"checklists", null, 0, -1, ContentElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getContentElement_Guidelines(),
				this.getGuideline(),
				null,
				"guidelines", null, 0, -1, ContentElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getContentElement_Examples(),
				this.getExample(),
				null,
				"examples", null, 0, -1, ContentElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getContentElement_Assets(),
				this.getReusableAsset(),
				null,
				"assets", null, 0, -1, ContentElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getContentElement_Termdefinition(),
				this.getTermDefinition(),
				null,
				"termdefinition", null, 0, -1, ContentElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				describableElementEClass,
				DescribableElement.class,
				"DescribableElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getDescribableElement_Presentation(),
				this.getContentDescription(),
				null,
				"presentation", null, 0, 1, DescribableElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEAttribute(
				getDescribableElement_Shapeicon(),
				this.getUri(),
				"shapeicon", null, 0, 1, DescribableElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEAttribute(
				getDescribableElement_Nodeicon(),
				this.getUri(),
				"nodeicon", null, 0, 1, DescribableElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				contentDescriptionEClass,
				ContentDescription.class,
				"ContentDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getContentDescription_MainDescription(),
				this.getString(),
				"mainDescription", "", 0, 1, ContentDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(
				getContentDescription_Sections(),
				this.getSection(),
				null,
				"sections", null, 0, -1, ContentDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEAttribute(
				getContentDescription_ExternalId(),
				this.getString(),
				"externalId", "", 0, 1, ContentDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getContentDescription_KeyConsiderations(),
				this.getString(),
				"keyConsiderations", "", 0, 1, ContentDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getContentDescription_LongPresentationName(),
				this.getString(),
				"longPresentationName", null, 1, 1, ContentDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				methodUnitEClass,
				MethodUnit.class,
				"MethodUnit", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getMethodUnit_Authors(),
				this.getString(),
				"authors", "", 0, 1, MethodUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getMethodUnit_ChangeDate(),
				this.getDate(),
				"changeDate", null, 0, 1, MethodUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEAttribute(
				getMethodUnit_ChangeDescription(),
				this.getString(),
				"changeDescription", "", 0, 1, MethodUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getMethodUnit_Version(),
				this.getString(),
				"version", "", 0, 1, MethodUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(
				getMethodUnit_CopyrightStatement(),
				this.getSupportingMaterial(),
				null,
				"copyrightStatement", null, 0, 1, MethodUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				supportingMaterialEClass,
				SupportingMaterial.class,
				"SupportingMaterial", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				guidanceEClass,
				Guidance.class,
				"Guidance", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				sectionEClass,
				Section.class,
				"Section", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getSection_SectionName(),
				this.getString(),
				"sectionName", "", 0, 1, Section.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getSection_SectionDescription(),
				this.getString(),
				"sectionDescription", "", 0, 1, Section.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(
				getSection_SubSections(),
				this.getSection(),
				null,
				"subSections", null, 0, -1, Section.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getSection_Predecessor(),
				this.getSection(),
				null,
				"predecessor", null, 0, 1, Section.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				variabilityElementEClass,
				VariabilityElement.class,
				"VariabilityElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getVariabilityElement_VariabilityType(),
				this.getVariabilityType(),
				"variabilityType", "na", 1, 1, VariabilityElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(
				getVariabilityElement_VariabilityBasedOnElement(),
				this.getVariabilityElement(),
				null,
				"variabilityBasedOnElement", null, 1, 1, VariabilityElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				conceptEClass,
				Concept.class,
				"Concept", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				checklistEClass,
				Checklist.class,
				"Checklist", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				guidelineEClass,
				Guideline.class,
				"Guideline", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				exampleEClass,
				Example.class,
				"Example", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				reusableAssetEClass,
				ReusableAsset.class,
				"ReusableAsset", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				termDefinitionEClass,
				TermDefinition.class,
				"TermDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				applicableMetaClassInfoEClass,
				ApplicableMetaClassInfo.class,
				"ApplicableMetaClassInfo", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getApplicableMetaClassInfo_IsPrimaryExtension(),
				this.getBoolean(),
				"isPrimaryExtension", null, 1, 1, ApplicableMetaClassInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				artifactEClass,
				Artifact.class,
				"Artifact", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getArtifact_ContainerArtifact(),
				this.getArtifact(),
				this.getArtifact_ContainedArtifacts(),
				"containerArtifact", null, 0, 1, Artifact.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getArtifact_ContainedArtifacts(),
				this.getArtifact(),
				this.getArtifact_ContainerArtifact(),
				"containedArtifacts", null, 0, -1, Artifact.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				workProductEClass,
				WorkProduct.class,
				"WorkProduct", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getWorkProduct_Reports(),
				this.getReport(),
				null,
				"reports", null, 0, -1, WorkProduct.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getWorkProduct_Templates(),
				this.getTemplate(),
				null,
				"templates", null, 0, -1, WorkProduct.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getWorkProduct_ToolMentors(),
				this.getToolMentor(),
				null,
				"toolMentors", null, 0, -1, WorkProduct.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getWorkProduct_EstimationConsiderations(),
				this.getEstimationConsiderations(),
				null,
				"estimationConsiderations", null, 0, -1, WorkProduct.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				fulfillableElementEClass,
				FulfillableElement.class,
				"FulfillableElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getFulfillableElement_Fulfills(),
				this.getFulfillableElement(),
				null,
				"fulfills", null, 0, -1, FulfillableElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				reportEClass,
				Report.class,
				"Report", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				templateEClass,
				Template.class,
				"Template", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				toolMentorEClass,
				ToolMentor.class,
				"ToolMentor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				estimationConsiderationsEClass,
				EstimationConsiderations.class,
				"EstimationConsiderations", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				deliverableEClass,
				Deliverable.class,
				"Deliverable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getDeliverable_DeliveredWorkProducts(),
				this.getWorkProduct(),
				null,
				"deliveredWorkProducts", null, 0, -1, Deliverable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				outcomeEClass,
				Outcome.class,
				"Outcome", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				stepEClass,
				Step.class,
				"Step", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				workDefinitionEClass,
				WorkDefinition.class,
				"WorkDefinition", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getWorkDefinition_Precondition(),
				this.getConstraint(),
				null,
				"precondition", null, 0, 1, WorkDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getWorkDefinition_Postcondition(),
				this.getConstraint(),
				null,
				"postcondition", null, 0, 1, WorkDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				whitepaperEClass,
				Whitepaper.class,
				"Whitepaper", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				taskEClass,
				Task.class,
				"Task", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getTask_PerformedBy(),
				this.getRole(),
				null,
				"performedBy", null, 0, -1, Task.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getTask_MandatoryInput(),
				this.getWorkProduct(),
				null,
				"mandatoryInput", null, 0, -1, Task.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getTask_Output(),
				this.getWorkProduct(),
				null,
				"output", null, 0, -1, Task.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getTask_AdditionallyPerformedBy(),
				this.getRole(),
				null,
				"additionallyPerformedBy", null, 0, -1, Task.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getTask_OptionalInput(),
				this.getWorkProduct(),
				null,
				"optionalInput", null, 0, -1, Task.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getTask_Steps(),
				this.getStep(),
				null,
				"steps", null, 0, -1, Task.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getTask_ToolMentors(),
				this.getToolMentor(),
				null,
				"toolMentors", null, 0, -1, Task.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getTask_EstimationConsiderations(),
				this.getEstimationConsiderations(),
				null,
				"estimationConsiderations", null, 0, -1, Task.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				roleEClass,
				Role.class,
				"Role", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getRole_Modifies(),
				this.getWorkProduct(),
				null,
				"modifies", null, 0, -1, Role.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getRole_ResponsibleFor(),
				this.getWorkProduct(),
				null,
				"responsibleFor", null, 0, -1, Role.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				artifactDescriptionEClass,
				ArtifactDescription.class,
				"ArtifactDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getArtifactDescription_BriefOutline(),
				this.getString(),
				"briefOutline", "", 0, 1, ArtifactDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getArtifactDescription_RepresentationOptions(),
				this.getString(),
				"representationOptions", "", 0, 1, ArtifactDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getArtifactDescription_Representation(),
				this.getString(),
				"representation", "", 0, 1, ArtifactDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getArtifactDescription_Notation(),
				this.getString(),
				"notation", "", 0, 1, ArtifactDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(
				workProductDescriptionEClass,
				WorkProductDescription.class,
				"WorkProductDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getWorkProductDescription_Purpose(),
				this.getString(),
				"purpose", "", 0, 1, WorkProductDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getWorkProductDescription_ImpactOfNotHaving(),
				this.getString(),
				"impactOfNotHaving", "", 0, 1, WorkProductDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getWorkProductDescription_ReasonsForNotNeeding(),
				this.getString(),
				"reasonsForNotNeeding", "", 0, 1, WorkProductDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(
				deliverableDescriptionEClass,
				DeliverableDescription.class,
				"DeliverableDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getDeliverableDescription_ExternalDescription(),
				this.getString(),
				"externalDescription", "", 0, 1, DeliverableDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getDeliverableDescription_PackagingGuidance(),
				this.getString(),
				"packagingGuidance", "", 0, 1, DeliverableDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(
				roleDescriptionEClass,
				RoleDescription.class,
				"RoleDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getRoleDescription_Skills(),
				this.getString(),
				"skills", "", 0, 1, RoleDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getRoleDescription_AssignmentApproaches(),
				this.getString(),
				"assignmentApproaches", "", 0, 1, RoleDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getRoleDescription_Synonyms(),
				this.getString(),
				"synonyms", "", 0, 1, RoleDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(
				taskDescriptionEClass,
				TaskDescription.class,
				"TaskDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getTaskDescription_Purpose(),
				this.getString(),
				"purpose", "", 0, 1, TaskDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getTaskDescription_Alternatives(),
				this.getString(),
				"alternatives", "", 0, 1, TaskDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(
				guidanceDescriptionEClass,
				GuidanceDescription.class,
				"GuidanceDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getGuidanceDescription_Attachments(),
				this.getString(),
				"attachments", "", 0, 1, GuidanceDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(
				practiceDescriptionEClass,
				PracticeDescription.class,
				"PracticeDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getPracticeDescription_AdditionalInfo(),
				this.getString(),
				"additionalInfo", "", 0, 1, PracticeDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getPracticeDescription_Problem(),
				this.getString(),
				"problem", "", 0, 1, PracticeDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getPracticeDescription_Background(),
				this.getString(),
				"background", "", 0, 1, PracticeDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getPracticeDescription_Goals(),
				this.getString(),
				"goals", "", 0, 1, PracticeDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getPracticeDescription_Application(),
				this.getString(),
				"application", "", 0, 1, PracticeDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getPracticeDescription_LevelsOfAdoption(),
				this.getString(),
				"levelsOfAdoption", "", 0, 1, PracticeDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(
				roleSetEClass,
				RoleSet.class,
				"RoleSet", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getRoleSet_Roles(),
				this.getRole(),
				null,
				"roles", null, 0, -1, RoleSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				contentCategoryEClass,
				ContentCategory.class,
				"ContentCategory", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				domainEClass,
				Domain.class,
				"Domain", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getDomain_WorkProducts(),
				this.getWorkProduct(),
				null,
				"workProducts", null, 0, -1, Domain.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getDomain_Subdomains(),
				this.getDomain(),
				null,
				"subdomains", null, 0, -1, Domain.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				workProductTypeEClass,
				WorkProductType.class,
				"WorkProductType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getWorkProductType_WorkProducts(),
				this.getWorkProduct(),
				null,
				"workProducts", null, 0, -1, WorkProductType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				disciplineGroupingEClass,
				DisciplineGrouping.class,
				"DisciplineGrouping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getDisciplineGrouping_Disciplines(),
				this.getDiscipline(),
				null,
				"disciplines", null, 0, -1, DisciplineGrouping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				disciplineEClass,
				Discipline.class,
				"Discipline", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getDiscipline_Tasks(),
				this.getTask(),
				null,
				"tasks", null, 0, -1, Discipline.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getDiscipline_Subdiscipline(),
				this.getDiscipline(),
				null,
				"subdiscipline", null, 0, -1, Discipline.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getDiscipline_ReferenceWorkflows(),
				this.getActivity(),
				null,
				"referenceWorkflows", null, 0, -1, Discipline.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				activityEClass,
				Activity.class,
				"Activity", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getActivity_BreakdownElements(),
				this.getBreakdownElement(),
				this.getBreakdownElement_SuperActivities(),
				"breakdownElements", null, 0, -1, Activity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getActivity_Roadmaps(),
				this.getRoadmap(),
				null,
				"roadmaps", null, 0, -1, Activity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				workBreakdownElementEClass,
				WorkBreakdownElement.class,
				"WorkBreakdownElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getWorkBreakdownElement_IsRepeatable(),
				this.getBoolean(),
				"isRepeatable", "false", 1, 1, WorkBreakdownElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getWorkBreakdownElement_IsOngoing(),
				this.getBoolean(),
				"isOngoing", "false", 1, 1, WorkBreakdownElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getWorkBreakdownElement_IsEventDriven(),
				this.getBoolean(),
				"isEventDriven", "false", 1, 1, WorkBreakdownElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(
				getWorkBreakdownElement_LinkToPredecessor(),
				this.getWorkOrder(),
				null,
				"linkToPredecessor", null, 0, -1, WorkBreakdownElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				breakdownElementEClass,
				BreakdownElement.class,
				"BreakdownElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getBreakdownElement_Prefix(),
				this.getString(),
				"prefix", "", 0, 1, BreakdownElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getBreakdownElement_IsPlanned(),
				this.getBoolean(),
				"isPlanned", "true", 1, 1, BreakdownElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getBreakdownElement_HasMultipleOccurrences(),
				this.getBoolean(),
				"hasMultipleOccurrences", "false", 1, 1, BreakdownElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getBreakdownElement_IsOptional(),
				this.getBoolean(),
				"isOptional", "false", 1, 1, BreakdownElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(
				getBreakdownElement_PresentedAfter(),
				this.getBreakdownElement(),
				null,
				"presentedAfter", null, 0, 1, BreakdownElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getBreakdownElement_PresentedBefore(),
				this.getBreakdownElement(),
				null,
				"presentedBefore", null, 0, 1, BreakdownElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getBreakdownElement_PlanningData(),
				this.getPlanningData(),
				null,
				"planningData", null, 0, 1, BreakdownElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getBreakdownElement_SuperActivities(),
				this.getActivity(),
				this.getActivity_BreakdownElements(),
				"superActivities", null, 1, 1, BreakdownElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getBreakdownElement_Checklists(),
				this.getChecklist(),
				null,
				"checklists", null, 0, -1, BreakdownElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getBreakdownElement_Concepts(),
				this.getConcept(),
				null,
				"concepts", null, 0, -1, BreakdownElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getBreakdownElement_Examples(),
				this.getExample(),
				null,
				"examples", null, 0, -1, BreakdownElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getBreakdownElement_Guidelines(),
				this.getGuideline(),
				null,
				"guidelines", null, 0, -1, BreakdownElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getBreakdownElement_ReusableAssets(),
				this.getReusableAsset(),
				null,
				"reusableAssets", null, 0, -1, BreakdownElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getBreakdownElement_SupportingMaterials(),
				this.getSupportingMaterial(),
				null,
				"supportingMaterials", null, 0, -1, BreakdownElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getBreakdownElement_Templates(),
				this.getTemplate(),
				null,
				"templates", null, 0, -1, BreakdownElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getBreakdownElement_Reports(),
				this.getReport(),
				null,
				"reports", null, 0, -1, BreakdownElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getBreakdownElement_Estimationconsiderations(),
				this.getEstimationConsiderations(),
				null,
				"estimationconsiderations", null, 0, -1, BreakdownElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getBreakdownElement_Toolmentor(),
				this.getToolMentor(),
				null,
				"toolmentor", null, 0, -1, BreakdownElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				processElementEClass,
				ProcessElement.class,
				"ProcessElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				planningDataEClass,
				PlanningData.class,
				"PlanningData", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getPlanningData_StartDate(),
				this.getDate(),
				"startDate", null, 1, 1, PlanningData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEAttribute(
				getPlanningData_FinishDate(),
				this.getDate(),
				"finishDate", null, 1, 1, PlanningData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEAttribute(
				getPlanningData_Rank(),
				this.getInteger(),
				"rank", null, 1, 1, PlanningData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				workOrderEClass,
				WorkOrder.class,
				"WorkOrder", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getWorkOrder_LinkType(),
				this.getWorkOrderType(),
				"linkType", "finishToStart", 1, 1, WorkOrder.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(
				getWorkOrder_Pred(),
				this.getWorkBreakdownElement(),
				null,
				"pred", null, 1, 1, WorkOrder.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				roadmapEClass,
				Roadmap.class,
				"Roadmap", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				toolEClass,
				Tool.class,
				"Tool", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getTool_ToolMentors(),
				this.getToolMentor(),
				null,
				"toolMentors", null, 0, -1, Tool.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				roleSetGroupingEClass,
				RoleSetGrouping.class,
				"RoleSetGrouping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getRoleSetGrouping_RoleSets(),
				this.getRoleSet(),
				null,
				"roleSets", null, 0, -1, RoleSetGrouping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				customCategoryEClass,
				CustomCategory.class,
				"CustomCategory", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getCustomCategory_CategorizedElements(),
				this.getDescribableElement(),
				null,
				"categorizedElements", null, 0, -1, CustomCategory.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getCustomCategory_SubCategories(),
				this.getContentCategory(),
				null,
				"subCategories", null, 0, -1, CustomCategory.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				methodPackageEClass,
				MethodPackage.class,
				"MethodPackage", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getMethodPackage_Global(),
				this.getBoolean(),
				"global", "false", 1, 1, MethodPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(
				getMethodPackage_ReusedPackages(),
				this.getMethodPackage(),
				null,
				"reusedPackages", null, 0, -1, MethodPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getMethodPackage_ChildPackages(),
				this.getMethodPackage(),
				null,
				"childPackages", null, 0, -1, MethodPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				contentPackageEClass,
				ContentPackage.class,
				"ContentPackage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getContentPackage_ContentElements(),
				this.getContentElement(),
				null,
				"contentElements", null, 0, -1, ContentPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				milestoneEClass,
				Milestone.class,
				"Milestone", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getMilestone_RequiredResults(),
				this.getWorkProductDescriptor(),
				null,
				"requiredResults", null, 0, -1, Milestone.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				workProductDescriptorEClass,
				WorkProductDescriptor.class,
				"WorkProductDescriptor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getWorkProductDescriptor_ActivityEntryState(),
				this.getString(),
				"activityEntryState", "", 0, 1, WorkProductDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getWorkProductDescriptor_ActivityExitState(),
				this.getString(),
				"activityExitState", "", 0, 1, WorkProductDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(
				getWorkProductDescriptor_WorkProduct(),
				this.getWorkProduct(),
				null,
				"WorkProduct", null, 0, 1, WorkProductDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getWorkProductDescriptor_ImpactedBy(),
				this.getWorkProductDescriptor(),
				this.getWorkProductDescriptor_Impacts(),
				"impactedBy", null, 0, -1, WorkProductDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getWorkProductDescriptor_Impacts(),
				this.getWorkProductDescriptor(),
				this.getWorkProductDescriptor_ImpactedBy(),
				"impacts", null, 0, -1, WorkProductDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getWorkProductDescriptor_DeliverableParts(),
				this.getWorkProductDescriptor(),
				null,
				"deliverableParts", null, 0, -1, WorkProductDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getWorkProductDescriptor_DeliverablePartsExclude(),
				this.getWorkProduct(),
				null,
				"deliverablePartsExclude", null, 0, -1, WorkProductDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				descriptorEClass,
				org.eclipse.epf.uma.Descriptor.class,
				"Descriptor", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getDescriptor_IsSynchronizedWithSource(),
				this.getBoolean(),
				"isSynchronizedWithSource", "true", 1, 1, org.eclipse.epf.uma.Descriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(
				getDescriptor_GuidanceExclude(),
				this.getGuidance(),
				null,
				"guidanceExclude", null, 0, -1, org.eclipse.epf.uma.Descriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getDescriptor_GuidanceAdditional(),
				this.getGuidance(),
				null,
				"guidanceAdditional", null, 0, -1, org.eclipse.epf.uma.Descriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				iterationEClass,
				Iteration.class,
				"Iteration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				phaseEClass,
				Phase.class,
				"Phase", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				teamProfileEClass,
				TeamProfile.class,
				"TeamProfile", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getTeamProfile_TeamRoles(),
				this.getRoleDescriptor(),
				null,
				"teamRoles", null, 0, -1, TeamProfile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getTeamProfile_SuperTeam(),
				this.getTeamProfile(),
				this.getTeamProfile_SubTeam(),
				"superTeam", null, 1, 1, TeamProfile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getTeamProfile_SubTeam(),
				this.getTeamProfile(),
				this.getTeamProfile_SuperTeam(),
				"subTeam", null, 0, -1, TeamProfile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				roleDescriptorEClass,
				RoleDescriptor.class,
				"RoleDescriptor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getRoleDescriptor_Role(),
				this.getRole(),
				null,
				"Role", null, 0, 1, RoleDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getRoleDescriptor_Modifies(),
				this.getWorkProductDescriptor(),
				null,
				"modifies", null, 0, -1, RoleDescriptor.class, !IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getRoleDescriptor_ResponsibleFor(),
				this.getWorkProductDescriptor(),
				null,
				"responsibleFor", null, 0, -1, RoleDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getRoleDescriptor_ResponsibleForExclude(),
				this.getWorkProduct(),
				null,
				"responsibleForExclude", null, 0, -1, RoleDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				taskDescriptorEClass,
				TaskDescriptor.class,
				"TaskDescriptor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getTaskDescriptor_Task(),
				this.getTask(),
				null,
				"Task", null, 0, 1, TaskDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getTaskDescriptor_AdditionallyPerformedBy(),
				this.getRoleDescriptor(),
				null,
				"additionallyPerformedBy", null, 0, -1, TaskDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getTaskDescriptor_AssistedBy(),
				this.getRoleDescriptor(),
				null,
				"assistedBy", null, 0, -1, TaskDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getTaskDescriptor_ExternalInput(),
				this.getWorkProductDescriptor(),
				null,
				"externalInput", null, 0, -1, TaskDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getTaskDescriptor_MandatoryInput(),
				this.getWorkProductDescriptor(),
				null,
				"mandatoryInput", null, 0, -1, TaskDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getTaskDescriptor_OptionalInput(),
				this.getWorkProductDescriptor(),
				null,
				"optionalInput", null, 0, -1, TaskDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getTaskDescriptor_Output(),
				this.getWorkProductDescriptor(),
				null,
				"output", null, 0, -1, TaskDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getTaskDescriptor_PerformedPrimarilyBy(),
				this.getRoleDescriptor(),
				null,
				"performedPrimarilyBy", null, 0, -1, TaskDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getTaskDescriptor_SelectedSteps(),
				this.getSection(),
				null,
				"selectedSteps", null, 0, -1, TaskDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getTaskDescriptor_PerformedPrimarilyByExcluded(),
				this.getRole(),
				null,
				"performedPrimarilyByExcluded", null, 0, -1, TaskDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getTaskDescriptor_AdditionallyPerformedByExclude(),
				this.getRole(),
				null,
				"additionallyPerformedByExclude", null, 0, -1, TaskDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getTaskDescriptor_MandatoryInputExclude(),
				this.getWorkProduct(),
				null,
				"mandatoryInputExclude", null, 0, -1, TaskDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getTaskDescriptor_OptionalInputExclude(),
				this.getWorkProduct(),
				null,
				"optionalInputExclude", null, 0, -1, TaskDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getTaskDescriptor_OutputExclude(),
				this.getWorkProduct(),
				null,
				"outputExclude", null, 0, -1, TaskDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getTaskDescriptor_SelectedStepsExclude(),
				this.getSection(),
				null,
				"selectedStepsExclude", null, 0, -1, TaskDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				compositeRoleEClass,
				CompositeRole.class,
				"CompositeRole", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getCompositeRole_AggregatedRoles(),
				this.getRole(),
				null,
				"aggregatedRoles", null, 0, -1, CompositeRole.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				deliveryProcessEClass,
				DeliveryProcess.class,
				"DeliveryProcess", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getDeliveryProcess_EducationMaterials(),
				this.getSupportingMaterial(),
				null,
				"educationMaterials", null, 0, -1, DeliveryProcess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getDeliveryProcess_CommunicationsMaterials(),
				this.getSupportingMaterial(),
				null,
				"communicationsMaterials", null, 0, -1, DeliveryProcess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				processEClass,
				org.eclipse.epf.uma.Process.class,
				"Process", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getProcess_IncludesPatterns(),
				this.getCapabilityPattern(),
				null,
				"includesPatterns", null, 0, -1, org.eclipse.epf.uma.Process.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getProcess_DefaultContext(),
				this.getMethodConfiguration(),
				null,
				"defaultContext", null, 1, 1, org.eclipse.epf.uma.Process.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getProcess_ValidContext(),
				this.getMethodConfiguration(),
				null,
				"validContext", null, 0, -1, org.eclipse.epf.uma.Process.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				capabilityPatternEClass,
				CapabilityPattern.class,
				"CapabilityPattern", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				methodConfigurationEClass,
				MethodConfiguration.class,
				"MethodConfiguration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getMethodConfiguration_MethodPluginSelection(),
				this.getMethodPlugin(),
				null,
				"methodPluginSelection", null, 1, -1, MethodConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getMethodConfiguration_MethodPackageSelection(),
				this.getMethodPackage(),
				null,
				"methodPackageSelection", null, 1, -1, MethodConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getMethodConfiguration_ProcessViews(),
				this.getContentCategory(),
				null,
				"processViews", null, 0, -1, MethodConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getMethodConfiguration_DefaultView(),
				this.getContentCategory(),
				null,
				"defaultView", null, 1, 1, MethodConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getMethodConfiguration_BaseConfigurations(),
				this.getMethodConfiguration(),
				null,
				"baseConfigurations", null, 0, -1, MethodConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getMethodConfiguration_SubtractedCategory(),
				this.getContentCategory(),
				null,
				"subtractedCategory", null, 0, -1, MethodConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getMethodConfiguration_AddedCategory(),
				this.getContentCategory(),
				null,
				"addedCategory", null, 0, -1, MethodConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				methodPluginEClass,
				MethodPlugin.class,
				"MethodPlugin", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getMethodPlugin_UserChangeable(),
				this.getBoolean(),
				"userChangeable", "true", 1, 1, MethodPlugin.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(
				getMethodPlugin_MethodPackages(),
				this.getMethodPackage(),
				null,
				"methodPackages", null, 1, -1, MethodPlugin.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getMethodPlugin_Bases(),
				this.getMethodPlugin(),
				null,
				"bases", null, 0, -1, MethodPlugin.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEAttribute(
				getMethodPlugin_Supporting(),
				ecorePackage.getEBoolean(),
				"supporting", null, 0, 1, MethodPlugin.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				processPlanningTemplateEClass,
				ProcessPlanningTemplate.class,
				"ProcessPlanningTemplate", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getProcessPlanningTemplate_BasedOnProcesses(),
				this.getProcess(),
				null,
				"basedOnProcesses", null, 0, -1, ProcessPlanningTemplate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				practiceEClass,
				Practice.class,
				"Practice", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getPractice_SubPractices(),
				this.getPractice(),
				null,
				"subPractices", null, 0, -1, Practice.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getPractice_ContentReferences(),
				this.getContentElement(),
				null,
				"contentReferences", null, 0, -1, Practice.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getPractice_ActivityReferences(),
				this.getActivity(),
				null,
				"activityReferences", null, 0, -1, Practice.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				breakdownElementDescriptionEClass,
				BreakdownElementDescription.class,
				"BreakdownElementDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getBreakdownElementDescription_UsageGuidance(),
				this.getString(),
				"usageGuidance", "", 0, 1, BreakdownElementDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(
				activityDescriptionEClass,
				ActivityDescription.class,
				"ActivityDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getActivityDescription_Purpose(),
				this.getString(),
				"purpose", "", 0, 1, ActivityDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getActivityDescription_Alternatives(),
				this.getString(),
				"alternatives", "", 0, 1, ActivityDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getActivityDescription_HowtoStaff(),
				this.getString(),
				"howtoStaff", "", 0, 1, ActivityDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(
				deliveryProcessDescriptionEClass,
				DeliveryProcessDescription.class,
				"DeliveryProcessDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getDeliveryProcessDescription_Scale(),
				this.getString(),
				"scale", "", 0, 1, DeliveryProcessDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getDeliveryProcessDescription_ProjectCharacteristics(),
				this.getString(),
				"projectCharacteristics", "", 0, 1, DeliveryProcessDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getDeliveryProcessDescription_RiskLevel(),
				this.getString(),
				"riskLevel", "", 0, 1, DeliveryProcessDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getDeliveryProcessDescription_EstimatingTechnique(),
				this.getString(),
				"estimatingTechnique", "", 0, 1, DeliveryProcessDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getDeliveryProcessDescription_ProjectMemberExpertise(),
				this.getString(),
				"projectMemberExpertise", "", 0, 1, DeliveryProcessDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getDeliveryProcessDescription_TypeOfContract(),
				this.getString(),
				"typeOfContract", "", 0, 1, DeliveryProcessDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(
				processDescriptionEClass,
				ProcessDescription.class,
				"ProcessDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getProcessDescription_Scope(),
				this.getString(),
				"scope", "", 0, 1, ProcessDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getProcessDescription_UsageNotes(),
				this.getString(),
				"usageNotes", "", 0, 1, ProcessDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(
				descriptorDescriptionEClass,
				DescriptorDescription.class,
				"DescriptorDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getDescriptorDescription_RefinedDescription(),
				this.getString(),
				"refinedDescription", "", 0, 1, DescriptorDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(
				processComponentDescriptorEClass,
				ProcessComponentDescriptor.class,
				"ProcessComponentDescriptor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getProcessComponentDescriptor__processComponent(),
				this.getProcessComponent(),
				null,
				"_processComponent", null, 1, 1, ProcessComponentDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				processComponentEClass,
				ProcessComponent.class,
				"ProcessComponent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getProcessComponent_Interfaces(),
				this.getProcessComponentInterface(),
				null,
				"interfaces", null, 1, -1, ProcessComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getProcessComponent_Process(),
				this.getProcess(),
				null,
				"process", null, 1, 1, ProcessComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				processPackageEClass,
				ProcessPackage.class,
				"ProcessPackage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getProcessPackage_ProcessElements(),
				this.getProcessElement(),
				null,
				"processElements", null, 0, -1, ProcessPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getProcessPackage_Diagrams(),
				this.getDiagram(),
				null,
				"diagrams", null, 0, -1, ProcessPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				diagramEClass,
				Diagram.class,
				"Diagram", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getDiagram_DiagramLink(),
				this.getDiagramLink(),
				this.getDiagramLink_Diagram(),
				"diagramLink", null, 0, -1, Diagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getDiagram_Namespace(),
				this.getSemanticModelBridge(),
				this.getSemanticModelBridge_Diagram(),
				"namespace", null, 1, 1, Diagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEAttribute(
				getDiagram_Zoom(),
				this.getDouble(),
				"zoom", null, 0, 1, Diagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getDiagram_Viewpoint(),
				this.getPoint(),
				null,
				"viewpoint", null, 1, 1, Diagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				graphNodeEClass,
				GraphNode.class,
				"GraphNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getGraphNode_Size(),
				this.getDimension(),
				null,
				"size", null, 1, 1, GraphNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				graphElementEClass,
				GraphElement.class,
				"GraphElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getGraphElement_Contained(),
				this.getDiagramElement(),
				this.getDiagramElement_Container(),
				"contained", null, 0, -1, GraphElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getGraphElement_Position(),
				this.getPoint(),
				null,
				"position", null, 1, 1, GraphElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getGraphElement_Link(),
				this.getDiagramLink(),
				this.getDiagramLink_GraphElement(),
				"link", null, 0, -1, GraphElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getGraphElement_Anchorage(),
				this.getGraphConnector(),
				this.getGraphConnector_GraphElement(),
				"anchorage", null, 0, -1, GraphElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getGraphElement_SemanticModel(),
				this.getSemanticModelBridge(),
				this.getSemanticModelBridge_GraphElement(),
				"semanticModel", null, 1, 1, GraphElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				diagramElementEClass,
				DiagramElement.class,
				"DiagramElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getDiagramElement_IsVisible(),
				this.getBoolean(),
				"isVisible", "true", 1, 1, DiagramElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(
				getDiagramElement_Container(),
				this.getGraphElement(),
				this.getGraphElement_Contained(),
				"container", null, 0, 1, DiagramElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getDiagramElement_Reference(),
				this.getReference(),
				this.getReference_Referenced(),
				"reference", null, 0, -1, DiagramElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getDiagramElement_Property(),
				this.getProperty(),
				null,
				"property", null, 0, -1, DiagramElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				referenceEClass,
				Reference.class,
				"Reference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getReference_IsIndividualRepresentation(),
				this.getBoolean(),
				"isIndividualRepresentation", "false", 1, 1, Reference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(
				getReference_Referenced(),
				this.getDiagramElement(),
				this.getDiagramElement_Reference(),
				"referenced", null, 1, 1, Reference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				propertyEClass,
				Property.class,
				"Property", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getProperty_Key(),
				this.getString(),
				"key", "", 0, 1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(
				getProperty_Value(),
				this.getString(),
				"value", "", 0, 1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(
				pointEClass,
				Point.class,
				"Point", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getPoint_X(),
				this.getDouble(),
				"x", null, 1, 1, Point.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEAttribute(
				getPoint_Y(),
				this.getDouble(),
				"y", null, 1, 1, Point.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				diagramLinkEClass,
				DiagramLink.class,
				"DiagramLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getDiagramLink_Zoom(),
				this.getDouble(),
				"zoom", null, 1, 1, DiagramLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getDiagramLink_Viewport(),
				this.getPoint(),
				null,
				"viewport", null, 1, 1, DiagramLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getDiagramLink_Diagram(),
				this.getDiagram(),
				this.getDiagram_DiagramLink(),
				"diagram", null, 1, 1, DiagramLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getDiagramLink_GraphElement(),
				this.getGraphElement(),
				this.getGraphElement_Link(),
				"graphElement", null, 1, 1, DiagramLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				graphConnectorEClass,
				GraphConnector.class,
				"GraphConnector", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getGraphConnector_GraphEdge(),
				this.getGraphEdge(),
				this.getGraphEdge_Anchor(),
				"graphEdge", null, 0, -1, GraphConnector.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getGraphConnector_GraphElement(),
				this.getGraphElement(),
				this.getGraphElement_Anchorage(),
				"graphElement", null, 1, 1, GraphConnector.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				graphEdgeEClass,
				GraphEdge.class,
				"GraphEdge", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getGraphEdge_Waypoints(),
				this.getPoint(),
				null,
				"waypoints", null, 2, -1, GraphEdge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getGraphEdge_Anchor(),
				this.getGraphConnector(),
				this.getGraphConnector_GraphEdge(),
				"anchor", null, 2, 2, GraphEdge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				semanticModelBridgeEClass,
				SemanticModelBridge.class,
				"SemanticModelBridge", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getSemanticModelBridge_Presentation(),
				this.getString(),
				"presentation", "", 0, 1, SemanticModelBridge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(
				getSemanticModelBridge_Diagram(),
				this.getDiagram(),
				this.getDiagram_Namespace(),
				"diagram", null, 0, 1, SemanticModelBridge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getSemanticModelBridge_GraphElement(),
				this.getGraphElement(),
				this.getGraphElement_SemanticModel(),
				"graphElement", null, 0, 1, SemanticModelBridge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				dimensionEClass,
				Dimension.class,
				"Dimension", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getDimension_Width(),
				this.getDouble(),
				"width", null, 1, 1, Dimension.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEAttribute(
				getDimension_Height(),
				this.getDouble(),
				"height", null, 1, 1, Dimension.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				processComponentInterfaceEClass,
				ProcessComponentInterface.class,
				"ProcessComponentInterface", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getProcessComponentInterface_InterfaceSpecifications(),
				this.getTaskDescriptor(),
				null,
				"interfaceSpecifications", null, 0, -1, ProcessComponentInterface.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getProcessComponentInterface_InterfaceIO(),
				this.getWorkProductDescriptor(),
				null,
				"interfaceIO", null, 0, -1, ProcessComponentInterface.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				simpleSemanticModelElementEClass,
				SimpleSemanticModelElement.class,
				"SimpleSemanticModelElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getSimpleSemanticModelElement_TypeInfo(),
				this.getString(),
				"typeInfo", "", 0, 1, SimpleSemanticModelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(
				umaSemanticModelBridgeEClass,
				UMASemanticModelBridge.class,
				"UMASemanticModelBridge", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getUMASemanticModelBridge_Element(),
				this.getMethodElement(),
				null,
				"element", null, 1, 1, UMASemanticModelBridge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				coreSemanticModelBridgeEClass,
				CoreSemanticModelBridge.class,
				"CoreSemanticModelBridge", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getCoreSemanticModelBridge_Element(),
				this.getElement(),
				null,
				"element", null, 1, 1, CoreSemanticModelBridge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				leafElementEClass,
				LeafElement.class,
				"LeafElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				textElementEClass,
				TextElement.class,
				"TextElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getTextElement_Text(),
				this.getString(),
				"text", "", 0, 1, TextElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(
				imageEClass,
				Image.class,
				"Image", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getImage_Uri(),
				this.getUri(),
				"uri", null, 0, 1, Image.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEAttribute(
				getImage_MimeType(),
				this.getString(),
				"mimeType", "", 0, 1, Image.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(
				graphicPrimitiveEClass,
				GraphicPrimitive.class,
				"GraphicPrimitive", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				polylineEClass,
				Polyline.class,
				"Polyline", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getPolyline_Closed(),
				this.getBoolean(),
				"closed", "true", 1, 1, Polyline.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(
				getPolyline_Waypoints(),
				this.getPoint(),
				null,
				"waypoints", null, 2, -1, Polyline.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				ellipseEClass,
				Ellipse.class,
				"Ellipse", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getEllipse_Center(),
				this.getPoint(),
				null,
				"center", null, 1, 1, Ellipse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEAttribute(
				getEllipse_RadiusX(),
				this.getDouble(),
				"radiusX", null, 1, 1, Ellipse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEAttribute(
				getEllipse_RadiusY(),
				this.getDouble(),
				"radiusY", null, 1, 1, Ellipse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEAttribute(
				getEllipse_Rotation(),
				this.getDouble(),
				"rotation", null, 1, 1, Ellipse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEAttribute(
				getEllipse_StartAngle(),
				this.getDouble(),
				"startAngle", null, 1, 1, Ellipse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEAttribute(
				getEllipse_EndAngle(),
				this.getDouble(),
				"endAngle", null, 1, 1, Ellipse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				processFamilyEClass,
				ProcessFamily.class,
				"ProcessFamily", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getProcessFamily_DeliveryProcesses(),
				this.getDeliveryProcess(),
				null,
				"deliveryProcesses", null, 0, -1, ProcessFamily.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(
				methodLibraryEClass,
				MethodLibrary.class,
				"MethodLibrary", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getMethodLibrary_MethodPlugins(),
				this.getMethodPlugin(),
				null,
				"methodPlugins", null, 0, -1, MethodLibrary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getMethodLibrary_PredefinedConfigurations(),
				this.getMethodConfiguration(),
				null,
				"predefinedConfigurations", null, 0, -1, MethodLibrary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		// Initialize enums and add enum literals
		initEEnum(variabilityTypeEEnum, VariabilityType.class,
				"VariabilityType"); //$NON-NLS-1$
		addEEnumLiteral(variabilityTypeEEnum, VariabilityType.NA);
		addEEnumLiteral(variabilityTypeEEnum, VariabilityType.CONTRIBUTES);
		addEEnumLiteral(variabilityTypeEEnum, VariabilityType.EXTENDS);
		addEEnumLiteral(variabilityTypeEEnum, VariabilityType.REPLACES);
		addEEnumLiteral(variabilityTypeEEnum,
				VariabilityType.LOCAL_CONTRIBUTION);
		addEEnumLiteral(variabilityTypeEEnum, VariabilityType.LOCAL_REPLACEMENT);
		addEEnumLiteral(variabilityTypeEEnum, VariabilityType.EXTENDS_REPLACES);

		initEEnum(workOrderTypeEEnum, WorkOrderType.class, "WorkOrderType"); //$NON-NLS-1$
		addEEnumLiteral(workOrderTypeEEnum, WorkOrderType.FINISH_TO_START);
		addEEnumLiteral(workOrderTypeEEnum, WorkOrderType.FINISH_TO_FINISH);
		addEEnumLiteral(workOrderTypeEEnum, WorkOrderType.START_TO_START);
		addEEnumLiteral(workOrderTypeEEnum, WorkOrderType.START_TO_FINISH);

		// Initialize data types
		initEDataType(stringEDataType, String.class,
				"String", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(booleanEDataType, Boolean.class,
				"Boolean", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(dateEDataType, Date.class,
				"Date", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(uriEDataType, java.net.URI.class,
				"Uri", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(setEDataType, Set.class,
				"Set", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(sequenceEDataType, List.class,
				"Sequence", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(integerEDataType, int.class,
				"Integer", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(doubleEDataType, Double.class,
				"Double", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		// Create resource
		createResource(eNS_URI);
	}

} //UmaPackageImpl