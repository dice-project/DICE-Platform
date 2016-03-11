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

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
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
import org.eclipse.epf.uma.Descriptor;
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
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.Whitepaper;
import org.eclipse.epf.uma.WorkBreakdownElement;
import org.eclipse.epf.uma.WorkDefinition;
import org.eclipse.epf.uma.WorkOrder;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.WorkProductDescription;
import org.eclipse.epf.uma.WorkProductDescriptor;
import org.eclipse.epf.uma.WorkProductType;
import org.eclipse.epf.uma.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.epf.uma.UmaPackage
 * @generated
 */
public class UmaAdapterFactory extends AdapterFactoryImpl {

	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static UmaPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UmaAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = UmaPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject) object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UmaSwitch<Adapter> modelSwitch = new UmaSwitch<Adapter>() {
		@Override
		public Adapter caseClassifier(Classifier object) {
			return createClassifierAdapter();
		}

		@Override
		public Adapter caseType(Type object) {
			return createTypeAdapter();
		}

		@Override
		public Adapter casePackageableElement(PackageableElement object) {
			return createPackageableElementAdapter();
		}

		@Override
		public Adapter caseNamedElement(NamedElement object) {
			return createNamedElementAdapter();
		}

		@Override
		public Adapter caseElement(Element object) {
			return createElementAdapter();
		}

		@Override
		public Adapter casePackage(org.eclipse.epf.uma.Package object) {
			return createPackageAdapter();
		}

		@Override
		public Adapter caseNamespace(Namespace object) {
			return createNamespaceAdapter();
		}

		@Override
		public Adapter caseConstraint(Constraint object) {
			return createConstraintAdapter();
		}

		@Override
		public Adapter caseMethodElement(MethodElement object) {
			return createMethodElementAdapter();
		}

		@Override
		public Adapter caseMethodElementProperty(MethodElementProperty object) {
			return createMethodElementPropertyAdapter();
		}

		@Override
		public Adapter caseKind(Kind object) {
			return createKindAdapter();
		}

		@Override
		public Adapter caseContentElement(ContentElement object) {
			return createContentElementAdapter();
		}

		@Override
		public Adapter caseDescribableElement(DescribableElement object) {
			return createDescribableElementAdapter();
		}

		@Override
		public Adapter caseContentDescription(ContentDescription object) {
			return createContentDescriptionAdapter();
		}

		@Override
		public Adapter caseMethodUnit(MethodUnit object) {
			return createMethodUnitAdapter();
		}

		@Override
		public Adapter caseSupportingMaterial(SupportingMaterial object) {
			return createSupportingMaterialAdapter();
		}

		@Override
		public Adapter caseGuidance(Guidance object) {
			return createGuidanceAdapter();
		}

		@Override
		public Adapter caseSection(Section object) {
			return createSectionAdapter();
		}

		@Override
		public Adapter caseVariabilityElement(VariabilityElement object) {
			return createVariabilityElementAdapter();
		}

		@Override
		public Adapter caseConcept(Concept object) {
			return createConceptAdapter();
		}

		@Override
		public Adapter caseChecklist(Checklist object) {
			return createChecklistAdapter();
		}

		@Override
		public Adapter caseGuideline(Guideline object) {
			return createGuidelineAdapter();
		}

		@Override
		public Adapter caseExample(Example object) {
			return createExampleAdapter();
		}

		@Override
		public Adapter caseReusableAsset(ReusableAsset object) {
			return createReusableAssetAdapter();
		}

		@Override
		public Adapter caseTermDefinition(TermDefinition object) {
			return createTermDefinitionAdapter();
		}

		@Override
		public Adapter caseApplicableMetaClassInfo(
				ApplicableMetaClassInfo object) {
			return createApplicableMetaClassInfoAdapter();
		}

		@Override
		public Adapter caseArtifact(Artifact object) {
			return createArtifactAdapter();
		}

		@Override
		public Adapter caseWorkProduct(WorkProduct object) {
			return createWorkProductAdapter();
		}

		@Override
		public Adapter caseFulfillableElement(FulfillableElement object) {
			return createFulfillableElementAdapter();
		}

		@Override
		public Adapter caseReport(Report object) {
			return createReportAdapter();
		}

		@Override
		public Adapter caseTemplate(Template object) {
			return createTemplateAdapter();
		}

		@Override
		public Adapter caseToolMentor(ToolMentor object) {
			return createToolMentorAdapter();
		}

		@Override
		public Adapter caseEstimationConsiderations(
				EstimationConsiderations object) {
			return createEstimationConsiderationsAdapter();
		}

		@Override
		public Adapter caseDeliverable(Deliverable object) {
			return createDeliverableAdapter();
		}

		@Override
		public Adapter caseOutcome(Outcome object) {
			return createOutcomeAdapter();
		}

		@Override
		public Adapter caseStep(Step object) {
			return createStepAdapter();
		}

		@Override
		public Adapter caseWorkDefinition(WorkDefinition object) {
			return createWorkDefinitionAdapter();
		}

		@Override
		public Adapter caseWhitepaper(Whitepaper object) {
			return createWhitepaperAdapter();
		}

		@Override
		public Adapter caseTask(Task object) {
			return createTaskAdapter();
		}

		@Override
		public Adapter caseRole(Role object) {
			return createRoleAdapter();
		}

		@Override
		public Adapter caseArtifactDescription(ArtifactDescription object) {
			return createArtifactDescriptionAdapter();
		}

		@Override
		public Adapter caseWorkProductDescription(WorkProductDescription object) {
			return createWorkProductDescriptionAdapter();
		}

		@Override
		public Adapter caseDeliverableDescription(DeliverableDescription object) {
			return createDeliverableDescriptionAdapter();
		}

		@Override
		public Adapter caseRoleDescription(RoleDescription object) {
			return createRoleDescriptionAdapter();
		}

		@Override
		public Adapter caseTaskDescription(TaskDescription object) {
			return createTaskDescriptionAdapter();
		}

		@Override
		public Adapter caseGuidanceDescription(GuidanceDescription object) {
			return createGuidanceDescriptionAdapter();
		}

		@Override
		public Adapter casePracticeDescription(PracticeDescription object) {
			return createPracticeDescriptionAdapter();
		}

		@Override
		public Adapter caseRoleSet(RoleSet object) {
			return createRoleSetAdapter();
		}

		@Override
		public Adapter caseContentCategory(ContentCategory object) {
			return createContentCategoryAdapter();
		}

		@Override
		public Adapter caseDomain(Domain object) {
			return createDomainAdapter();
		}

		@Override
		public Adapter caseWorkProductType(WorkProductType object) {
			return createWorkProductTypeAdapter();
		}

		@Override
		public Adapter caseDisciplineGrouping(DisciplineGrouping object) {
			return createDisciplineGroupingAdapter();
		}

		@Override
		public Adapter caseDiscipline(Discipline object) {
			return createDisciplineAdapter();
		}

		@Override
		public Adapter caseActivity(Activity object) {
			return createActivityAdapter();
		}

		@Override
		public Adapter caseWorkBreakdownElement(WorkBreakdownElement object) {
			return createWorkBreakdownElementAdapter();
		}

		@Override
		public Adapter caseBreakdownElement(BreakdownElement object) {
			return createBreakdownElementAdapter();
		}

		@Override
		public Adapter caseProcessElement(ProcessElement object) {
			return createProcessElementAdapter();
		}

		@Override
		public Adapter casePlanningData(PlanningData object) {
			return createPlanningDataAdapter();
		}

		@Override
		public Adapter caseWorkOrder(WorkOrder object) {
			return createWorkOrderAdapter();
		}

		@Override
		public Adapter caseRoadmap(Roadmap object) {
			return createRoadmapAdapter();
		}

		@Override
		public Adapter caseTool(Tool object) {
			return createToolAdapter();
		}

		@Override
		public Adapter caseRoleSetGrouping(RoleSetGrouping object) {
			return createRoleSetGroupingAdapter();
		}

		@Override
		public Adapter caseCustomCategory(CustomCategory object) {
			return createCustomCategoryAdapter();
		}

		@Override
		public Adapter caseMethodPackage(MethodPackage object) {
			return createMethodPackageAdapter();
		}

		@Override
		public Adapter caseContentPackage(ContentPackage object) {
			return createContentPackageAdapter();
		}

		@Override
		public Adapter caseMilestone(Milestone object) {
			return createMilestoneAdapter();
		}

		@Override
		public Adapter caseWorkProductDescriptor(WorkProductDescriptor object) {
			return createWorkProductDescriptorAdapter();
		}

		@Override
		public Adapter caseDescriptor(Descriptor object) {
			return createDescriptorAdapter();
		}

		@Override
		public Adapter caseIteration(Iteration object) {
			return createIterationAdapter();
		}

		@Override
		public Adapter casePhase(Phase object) {
			return createPhaseAdapter();
		}

		@Override
		public Adapter caseTeamProfile(TeamProfile object) {
			return createTeamProfileAdapter();
		}

		@Override
		public Adapter caseRoleDescriptor(RoleDescriptor object) {
			return createRoleDescriptorAdapter();
		}

		@Override
		public Adapter caseTaskDescriptor(TaskDescriptor object) {
			return createTaskDescriptorAdapter();
		}

		@Override
		public Adapter caseCompositeRole(CompositeRole object) {
			return createCompositeRoleAdapter();
		}

		@Override
		public Adapter caseDeliveryProcess(DeliveryProcess object) {
			return createDeliveryProcessAdapter();
		}

		@Override
		public Adapter caseProcess(org.eclipse.epf.uma.Process object) {
			return createProcessAdapter();
		}

		@Override
		public Adapter caseCapabilityPattern(CapabilityPattern object) {
			return createCapabilityPatternAdapter();
		}

		@Override
		public Adapter caseMethodConfiguration(MethodConfiguration object) {
			return createMethodConfigurationAdapter();
		}

		@Override
		public Adapter caseMethodPlugin(MethodPlugin object) {
			return createMethodPluginAdapter();
		}

		@Override
		public Adapter caseProcessPlanningTemplate(
				ProcessPlanningTemplate object) {
			return createProcessPlanningTemplateAdapter();
		}

		@Override
		public Adapter casePractice(Practice object) {
			return createPracticeAdapter();
		}

		@Override
		public Adapter caseBreakdownElementDescription(
				BreakdownElementDescription object) {
			return createBreakdownElementDescriptionAdapter();
		}

		@Override
		public Adapter caseActivityDescription(ActivityDescription object) {
			return createActivityDescriptionAdapter();
		}

		@Override
		public Adapter caseDeliveryProcessDescription(
				DeliveryProcessDescription object) {
			return createDeliveryProcessDescriptionAdapter();
		}

		@Override
		public Adapter caseProcessDescription(ProcessDescription object) {
			return createProcessDescriptionAdapter();
		}

		@Override
		public Adapter caseDescriptorDescription(DescriptorDescription object) {
			return createDescriptorDescriptionAdapter();
		}

		@Override
		public Adapter caseProcessComponentDescriptor(
				ProcessComponentDescriptor object) {
			return createProcessComponentDescriptorAdapter();
		}

		@Override
		public Adapter caseProcessComponent(ProcessComponent object) {
			return createProcessComponentAdapter();
		}

		@Override
		public Adapter caseProcessPackage(ProcessPackage object) {
			return createProcessPackageAdapter();
		}

		@Override
		public Adapter caseDiagram(Diagram object) {
			return createDiagramAdapter();
		}

		@Override
		public Adapter caseGraphNode(GraphNode object) {
			return createGraphNodeAdapter();
		}

		@Override
		public Adapter caseGraphElement(GraphElement object) {
			return createGraphElementAdapter();
		}

		@Override
		public Adapter caseDiagramElement(DiagramElement object) {
			return createDiagramElementAdapter();
		}

		@Override
		public Adapter caseReference(Reference object) {
			return createReferenceAdapter();
		}

		@Override
		public Adapter caseProperty(Property object) {
			return createPropertyAdapter();
		}

		@Override
		public Adapter casePoint(Point object) {
			return createPointAdapter();
		}

		@Override
		public Adapter caseDiagramLink(DiagramLink object) {
			return createDiagramLinkAdapter();
		}

		@Override
		public Adapter caseGraphConnector(GraphConnector object) {
			return createGraphConnectorAdapter();
		}

		@Override
		public Adapter caseGraphEdge(GraphEdge object) {
			return createGraphEdgeAdapter();
		}

		@Override
		public Adapter caseSemanticModelBridge(SemanticModelBridge object) {
			return createSemanticModelBridgeAdapter();
		}

		@Override
		public Adapter caseDimension(Dimension object) {
			return createDimensionAdapter();
		}

		@Override
		public Adapter caseProcessComponentInterface(
				ProcessComponentInterface object) {
			return createProcessComponentInterfaceAdapter();
		}

		@Override
		public Adapter caseSimpleSemanticModelElement(
				SimpleSemanticModelElement object) {
			return createSimpleSemanticModelElementAdapter();
		}

		@Override
		public Adapter caseUMASemanticModelBridge(UMASemanticModelBridge object) {
			return createUMASemanticModelBridgeAdapter();
		}

		@Override
		public Adapter caseCoreSemanticModelBridge(
				CoreSemanticModelBridge object) {
			return createCoreSemanticModelBridgeAdapter();
		}

		@Override
		public Adapter caseLeafElement(LeafElement object) {
			return createLeafElementAdapter();
		}

		@Override
		public Adapter caseTextElement(TextElement object) {
			return createTextElementAdapter();
		}

		@Override
		public Adapter caseImage(Image object) {
			return createImageAdapter();
		}

		@Override
		public Adapter caseGraphicPrimitive(GraphicPrimitive object) {
			return createGraphicPrimitiveAdapter();
		}

		@Override
		public Adapter casePolyline(Polyline object) {
			return createPolylineAdapter();
		}

		@Override
		public Adapter caseEllipse(Ellipse object) {
			return createEllipseAdapter();
		}

		@Override
		public Adapter caseProcessFamily(ProcessFamily object) {
			return createProcessFamilyAdapter();
		}

		@Override
		public Adapter caseMethodLibrary(MethodLibrary object) {
			return createMethodLibraryAdapter();
		}

		@Override
		public Adapter defaultCase(EObject object) {
			return createEObjectAdapter();
		}
	};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject) target);
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.Classifier <em>Classifier</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.Classifier
	 * @generated
	 */
	public Adapter createClassifierAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.Type <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.Type
	 * @generated
	 */
	public Adapter createTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.Element <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.Element
	 * @generated
	 */
	public Adapter createElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.NamedElement <em>Named Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.NamedElement
	 * @generated
	 */
	public Adapter createNamedElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.PackageableElement <em>Packageable Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.PackageableElement
	 * @generated
	 */
	public Adapter createPackageableElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.Package <em>Package</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.Package
	 * @generated
	 */
	public Adapter createPackageAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.Namespace <em>Namespace</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.Namespace
	 * @generated
	 */
	public Adapter createNamespaceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.MethodElement <em>Method Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.MethodElement
	 * @generated
	 */
	public Adapter createMethodElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.Constraint <em>Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.Constraint
	 * @generated
	 */
	public Adapter createConstraintAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.MethodElementProperty <em>Method Element Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.MethodElementProperty
	 * @generated
	 */
	public Adapter createMethodElementPropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.Kind <em>Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.Kind
	 * @generated
	 */
	public Adapter createKindAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.ContentElement <em>Content Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.ContentElement
	 * @generated
	 */
	public Adapter createContentElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.DescribableElement <em>Describable Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.DescribableElement
	 * @generated
	 */
	public Adapter createDescribableElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.ContentDescription <em>Content Description</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.ContentDescription
	 * @generated
	 */
	public Adapter createContentDescriptionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.Section <em>Section</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.Section
	 * @generated
	 */
	public Adapter createSectionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.Role <em>Role</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.Role
	 * @generated
	 */
	public Adapter createRoleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.WorkProduct <em>Work Product</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.WorkProduct
	 * @generated
	 */
	public Adapter createWorkProductAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.FulfillableElement <em>Fulfillable Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.FulfillableElement
	 * @generated
	 */
	public Adapter createFulfillableElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.Task <em>Task</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.Task
	 * @generated
	 */
	public Adapter createTaskAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.WorkDefinition <em>Work Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.WorkDefinition
	 * @generated
	 */
	public Adapter createWorkDefinitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.Step <em>Step</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.Step
	 * @generated
	 */
	public Adapter createStepAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.Guidance <em>Guidance</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.Guidance
	 * @generated
	 */
	public Adapter createGuidanceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.Artifact <em>Artifact</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.Artifact
	 * @generated
	 */
	public Adapter createArtifactAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.Deliverable <em>Deliverable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.Deliverable
	 * @generated
	 */
	public Adapter createDeliverableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.Outcome <em>Outcome</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.Outcome
	 * @generated
	 */
	public Adapter createOutcomeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.MethodPackage <em>Method Package</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.MethodPackage
	 * @generated
	 */
	public Adapter createMethodPackageAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.ContentPackage <em>Content Package</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.ContentPackage
	 * @generated
	 */
	public Adapter createContentPackageAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.ArtifactDescription <em>Artifact Description</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.ArtifactDescription
	 * @generated
	 */
	public Adapter createArtifactDescriptionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.WorkProductDescription <em>Work Product Description</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.WorkProductDescription
	 * @generated
	 */
	public Adapter createWorkProductDescriptionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.DeliverableDescription <em>Deliverable Description</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.DeliverableDescription
	 * @generated
	 */
	public Adapter createDeliverableDescriptionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.RoleDescription <em>Role Description</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.RoleDescription
	 * @generated
	 */
	public Adapter createRoleDescriptionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.TaskDescription <em>Task Description</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.TaskDescription
	 * @generated
	 */
	public Adapter createTaskDescriptionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.GuidanceDescription <em>Guidance Description</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.GuidanceDescription
	 * @generated
	 */
	public Adapter createGuidanceDescriptionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.PracticeDescription <em>Practice Description</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.PracticeDescription
	 * @generated
	 */
	public Adapter createPracticeDescriptionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.Activity <em>Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.Activity
	 * @generated
	 */
	public Adapter createActivityAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.WorkBreakdownElement <em>Work Breakdown Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.WorkBreakdownElement
	 * @generated
	 */
	public Adapter createWorkBreakdownElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.BreakdownElement <em>Breakdown Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.BreakdownElement
	 * @generated
	 */
	public Adapter createBreakdownElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.Milestone <em>Milestone</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.Milestone
	 * @generated
	 */
	public Adapter createMilestoneAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.Iteration <em>Iteration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.Iteration
	 * @generated
	 */
	public Adapter createIterationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.Phase <em>Phase</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.Phase
	 * @generated
	 */
	public Adapter createPhaseAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.TeamProfile <em>Team Profile</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.TeamProfile
	 * @generated
	 */
	public Adapter createTeamProfileAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.RoleDescriptor <em>Role Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.RoleDescriptor
	 * @generated
	 */
	public Adapter createRoleDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.WorkOrder <em>Work Order</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.WorkOrder
	 * @generated
	 */
	public Adapter createWorkOrderAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.ProcessElement <em>Process Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.ProcessElement
	 * @generated
	 */
	public Adapter createProcessElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.PlanningData <em>Planning Data</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.PlanningData
	 * @generated
	 */
	public Adapter createPlanningDataAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.Descriptor <em>Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.Descriptor
	 * @generated
	 */
	public Adapter createDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.TaskDescriptor <em>Task Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.TaskDescriptor
	 * @generated
	 */
	public Adapter createTaskDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.WorkProductDescriptor <em>Work Product Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.WorkProductDescriptor
	 * @generated
	 */
	public Adapter createWorkProductDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.CompositeRole <em>Composite Role</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.CompositeRole
	 * @generated
	 */
	public Adapter createCompositeRoleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.BreakdownElementDescription <em>Breakdown Element Description</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.BreakdownElementDescription
	 * @generated
	 */
	public Adapter createBreakdownElementDescriptionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.ActivityDescription <em>Activity Description</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.ActivityDescription
	 * @generated
	 */
	public Adapter createActivityDescriptionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.DeliveryProcessDescription <em>Delivery Process Description</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.DeliveryProcessDescription
	 * @generated
	 */
	public Adapter createDeliveryProcessDescriptionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.ProcessDescription <em>Process Description</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.ProcessDescription
	 * @generated
	 */
	public Adapter createProcessDescriptionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.DescriptorDescription <em>Descriptor Description</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.DescriptorDescription
	 * @generated
	 */
	public Adapter createDescriptorDescriptionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.Concept <em>Concept</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.Concept
	 * @generated
	 */
	public Adapter createConceptAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.Checklist <em>Checklist</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.Checklist
	 * @generated
	 */
	public Adapter createChecklistAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.Example <em>Example</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.Example
	 * @generated
	 */
	public Adapter createExampleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.Guideline <em>Guideline</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.Guideline
	 * @generated
	 */
	public Adapter createGuidelineAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.EstimationConsiderations <em>Estimation Considerations</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.EstimationConsiderations
	 * @generated
	 */
	public Adapter createEstimationConsiderationsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.Report <em>Report</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.Report
	 * @generated
	 */
	public Adapter createReportAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.Template <em>Template</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.Template
	 * @generated
	 */
	public Adapter createTemplateAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.SupportingMaterial <em>Supporting Material</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.SupportingMaterial
	 * @generated
	 */
	public Adapter createSupportingMaterialAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.ToolMentor <em>Tool Mentor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.ToolMentor
	 * @generated
	 */
	public Adapter createToolMentorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.Whitepaper <em>Whitepaper</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.Whitepaper
	 * @generated
	 */
	public Adapter createWhitepaperAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.TermDefinition <em>Term Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.TermDefinition
	 * @generated
	 */
	public Adapter createTermDefinitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.ApplicableMetaClassInfo <em>Applicable Meta Class Info</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.ApplicableMetaClassInfo
	 * @generated
	 */
	public Adapter createApplicableMetaClassInfoAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.Practice <em>Practice</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.Practice
	 * @generated
	 */
	public Adapter createPracticeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.ReusableAsset <em>Reusable Asset</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.ReusableAsset
	 * @generated
	 */
	public Adapter createReusableAssetAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.Discipline <em>Discipline</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.Discipline
	 * @generated
	 */
	public Adapter createDisciplineAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.ContentCategory <em>Content Category</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.ContentCategory
	 * @generated
	 */
	public Adapter createContentCategoryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.RoleSet <em>Role Set</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.RoleSet
	 * @generated
	 */
	public Adapter createRoleSetAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.Domain <em>Domain</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.Domain
	 * @generated
	 */
	public Adapter createDomainAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.WorkProductType <em>Work Product Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.WorkProductType
	 * @generated
	 */
	public Adapter createWorkProductTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.DisciplineGrouping <em>Discipline Grouping</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.DisciplineGrouping
	 * @generated
	 */
	public Adapter createDisciplineGroupingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.Tool <em>Tool</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.Tool
	 * @generated
	 */
	public Adapter createToolAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.RoleSetGrouping <em>Role Set Grouping</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.RoleSetGrouping
	 * @generated
	 */
	public Adapter createRoleSetGroupingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.CustomCategory <em>Custom Category</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.CustomCategory
	 * @generated
	 */
	public Adapter createCustomCategoryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.DeliveryProcess <em>Delivery Process</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.DeliveryProcess
	 * @generated
	 */
	public Adapter createDeliveryProcessAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.Process <em>Process</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.Process
	 * @generated
	 */
	public Adapter createProcessAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.CapabilityPattern <em>Capability Pattern</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.CapabilityPattern
	 * @generated
	 */
	public Adapter createCapabilityPatternAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.ProcessPlanningTemplate <em>Process Planning Template</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.ProcessPlanningTemplate
	 * @generated
	 */
	public Adapter createProcessPlanningTemplateAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.Roadmap <em>Roadmap</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.Roadmap
	 * @generated
	 */
	public Adapter createRoadmapAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.ProcessComponent <em>Process Component</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.ProcessComponent
	 * @generated
	 */
	public Adapter createProcessComponentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.ProcessPackage <em>Process Package</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.ProcessPackage
	 * @generated
	 */
	public Adapter createProcessPackageAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.ProcessComponentInterface <em>Process Component Interface</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.ProcessComponentInterface
	 * @generated
	 */
	public Adapter createProcessComponentInterfaceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.ProcessComponentDescriptor <em>Process Component Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.ProcessComponentDescriptor
	 * @generated
	 */
	public Adapter createProcessComponentDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.MethodPlugin <em>Method Plugin</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.MethodPlugin
	 * @generated
	 */
	public Adapter createMethodPluginAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.VariabilityElement <em>Variability Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.VariabilityElement
	 * @generated
	 */
	public Adapter createVariabilityElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.MethodUnit <em>Method Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.MethodUnit
	 * @generated
	 */
	public Adapter createMethodUnitAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.MethodConfiguration <em>Method Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.MethodConfiguration
	 * @generated
	 */
	public Adapter createMethodConfigurationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.ProcessFamily <em>Process Family</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.ProcessFamily
	 * @generated
	 */
	public Adapter createProcessFamilyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.MethodLibrary <em>Method Library</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.MethodLibrary
	 * @generated
	 */
	public Adapter createMethodLibraryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.Point <em>Point</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.Point
	 * @generated
	 */
	public Adapter createPointAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.GraphElement <em>Graph Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.GraphElement
	 * @generated
	 */
	public Adapter createGraphElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.DiagramElement <em>Diagram Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.DiagramElement
	 * @generated
	 */
	public Adapter createDiagramElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.DiagramLink <em>Diagram Link</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.DiagramLink
	 * @generated
	 */
	public Adapter createDiagramLinkAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.GraphConnector <em>Graph Connector</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.GraphConnector
	 * @generated
	 */
	public Adapter createGraphConnectorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.SemanticModelBridge <em>Semantic Model Bridge</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.SemanticModelBridge
	 * @generated
	 */
	public Adapter createSemanticModelBridgeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.Dimension <em>Dimension</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.Dimension
	 * @generated
	 */
	public Adapter createDimensionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.Reference <em>Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.Reference
	 * @generated
	 */
	public Adapter createReferenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.Property <em>Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.Property
	 * @generated
	 */
	public Adapter createPropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.GraphEdge <em>Graph Edge</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.GraphEdge
	 * @generated
	 */
	public Adapter createGraphEdgeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.Diagram <em>Diagram</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.Diagram
	 * @generated
	 */
	public Adapter createDiagramAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.GraphNode <em>Graph Node</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.GraphNode
	 * @generated
	 */
	public Adapter createGraphNodeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.SimpleSemanticModelElement <em>Simple Semantic Model Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.SimpleSemanticModelElement
	 * @generated
	 */
	public Adapter createSimpleSemanticModelElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.UMASemanticModelBridge <em>UMA Semantic Model Bridge</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.UMASemanticModelBridge
	 * @generated
	 */
	public Adapter createUMASemanticModelBridgeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.CoreSemanticModelBridge <em>Core Semantic Model Bridge</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.CoreSemanticModelBridge
	 * @generated
	 */
	public Adapter createCoreSemanticModelBridgeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.LeafElement <em>Leaf Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.LeafElement
	 * @generated
	 */
	public Adapter createLeafElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.TextElement <em>Text Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.TextElement
	 * @generated
	 */
	public Adapter createTextElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.Image <em>Image</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.Image
	 * @generated
	 */
	public Adapter createImageAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.GraphicPrimitive <em>Graphic Primitive</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.GraphicPrimitive
	 * @generated
	 */
	public Adapter createGraphicPrimitiveAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.Polyline <em>Polyline</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.Polyline
	 * @generated
	 */
	public Adapter createPolylineAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.uma.Ellipse <em>Ellipse</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.uma.Ellipse
	 * @generated
	 */
	public Adapter createEllipseAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //UmaAdapterFactory