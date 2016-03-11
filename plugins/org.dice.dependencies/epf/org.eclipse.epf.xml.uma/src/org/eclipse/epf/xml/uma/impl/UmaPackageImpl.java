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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

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
	private EClass activityEClass = null;

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
	private EClass applicableMetaClassInfoEClass = null;

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
	private EClass artifactDescriptionEClass = null;

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
	private EClass breakdownElementDescriptionEClass = null;

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
	private EClass checklistEClass = null;

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
	private EClass conceptEClass = null;

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
	private EClass contentCategoryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass contentCategoryPackageEClass = null;

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
	private EClass contentElementEClass = null;

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
	private EClass customCategoryEClass = null;

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
	private EClass deliverableDescriptionEClass = null;

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
	private EClass deliveryProcessDescriptionEClass = null;

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
	private EClass descriptorEClass = null;

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
	private EClass disciplineEClass = null;

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
	private EClass documentRootEClass = null;

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
	private EClass elementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass estimateEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass estimatingMetricEClass = null;

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
	private EClass exampleEClass = null;

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
	private EClass guidanceDescriptionEClass = null;

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
	private EClass iterationEClass = null;

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
	private EClass methodConfigurationEClass = null;

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
	private EClass methodElementPropertyEClass = null;

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
	private EClass methodPackageEClass = null;

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
	private EClass methodUnitEClass = null;

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
	private EClass namedElementEClass = null;

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
	private EClass packageableElementEClass = null;

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
	private EClass planningDataEClass = null;

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
	private EClass practiceDescriptionEClass = null;

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
	private EClass processComponentEClass = null;

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
	private EClass processDescriptionEClass = null;

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
	private EClass processPackageEClass = null;

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
	private EClass reportEClass = null;

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
	private EClass roadmapEClass = null;

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
	private EClass roleDescriptionEClass = null;

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
	private EClass roleSetEClass = null;

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
	private EClass sectionEClass = null;

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
	private EClass taskEClass = null;

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
	private EClass taskDescriptorEClass = null;

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
	private EClass templateEClass = null;

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
	private EClass toolEClass = null;

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
	private EClass workBreakdownElementEClass = null;

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
	private EClass workOrderEClass = null;

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
	private EClass workProductDescriptionEClass = null;

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
	private EClass workProductTypeEClass = null;

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
	private EEnum workOrderTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType variabilityTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType workOrderTypeObjectEDataType = null;

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
	 * @see org.eclipse.epf.xml.uma.UmaPackage#eNS_URI
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
	 * Creates, registers, and initializes the <b>Package</b> for this
	 * model, and for any others upon which it depends.  Simple
	 * dependencies are satisfied by calling this method on all
	 * dependent packages before doing anything else.  This method drives
	 * initialization for interdependent packages directly, in parallel
	 * with this package, itself.
	 * <p>Of this package and its interdependencies, all packages which
	 * have not yet been registered by their URI values are first created
	 * and registered.  The packages are then initialized in two steps:
	 * meta-model objects for all of the packages are created before any
	 * are initialized, since one package's meta-model objects may refer to
	 * those of another.
	 * <p>Invocation of this method will not affect any packages that have
	 * already been initialized.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static UmaPackage init() {
		if (isInited) return (UmaPackage)EPackage.Registry.INSTANCE.getEPackage(UmaPackage.eNS_URI);

		// Obtain or create and register package
		UmaPackageImpl theUmaPackage = (UmaPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof UmaPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new UmaPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		XMLTypePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theUmaPackage.createPackageContents();

		// Initialize created meta-data
		theUmaPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theUmaPackage.freeze();

		return theUmaPackage;
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
	public EAttribute getActivity_Precondition() {
		return (EAttribute)activityEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getActivity_Postcondition() {
		return (EAttribute)activityEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getActivity_Group3() {
		return (EAttribute)activityEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActivity_BreakdownElement() {
		return (EReference)activityEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getActivity_Roadmap() {
		return (EAttribute)activityEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getActivity_IsEnactable() {
		return (EAttribute)activityEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getActivity_VariabilityBasedOnElement() {
		return (EAttribute)activityEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getActivity_VariabilityType() {
		return (EAttribute)activityEClass.getEStructuralFeatures().get(7);
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
	public EAttribute getActivityDescription_Alternatives() {
		return (EAttribute)activityDescriptionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getActivityDescription_HowToStaff() {
		return (EAttribute)activityDescriptionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getActivityDescription_Purpose() {
		return (EAttribute)activityDescriptionEClass.getEStructuralFeatures().get(2);
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
		return (EAttribute)applicableMetaClassInfoEClass.getEStructuralFeatures().get(0);
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
	public EAttribute getArtifact_Group3() {
		return (EAttribute)artifactEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArtifact_ContainedArtifact() {
		return (EReference)artifactEClass.getEStructuralFeatures().get(1);
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
		return (EAttribute)artifactDescriptionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArtifactDescription_RepresentationOptions() {
		return (EAttribute)artifactDescriptionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArtifactDescription_Representation() {
		return (EAttribute)artifactDescriptionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArtifactDescription_Notation() {
		return (EAttribute)artifactDescriptionEClass.getEStructuralFeatures().get(3);
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
	public EAttribute getBreakdownElement_PresentedAfter() {
		return (EAttribute)breakdownElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBreakdownElement_PresentedBefore() {
		return (EAttribute)breakdownElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBreakdownElement_PlanningData() {
		return (EAttribute)breakdownElementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBreakdownElement_SuperActivity() {
		return (EAttribute)breakdownElementEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBreakdownElement_Group1() {
		return (EAttribute)breakdownElementEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBreakdownElement_Checklist() {
		return (EAttribute)breakdownElementEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBreakdownElement_Concept() {
		return (EAttribute)breakdownElementEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBreakdownElement_Example() {
		return (EAttribute)breakdownElementEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBreakdownElement_Guideline() {
		return (EAttribute)breakdownElementEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBreakdownElement_ReusableAsset() {
		return (EAttribute)breakdownElementEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBreakdownElement_SupportingMaterial() {
		return (EAttribute)breakdownElementEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBreakdownElement_Whitepaper() {
		return (EAttribute)breakdownElementEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBreakdownElement_HasMultipleOccurrences() {
		return (EAttribute)breakdownElementEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBreakdownElement_IsOptional() {
		return (EAttribute)breakdownElementEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBreakdownElement_IsPlanned() {
		return (EAttribute)breakdownElementEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBreakdownElement_Prefix() {
		return (EAttribute)breakdownElementEClass.getEStructuralFeatures().get(15);
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
		return (EAttribute)breakdownElementDescriptionEClass.getEStructuralFeatures().get(0);
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
	public EClass getChecklist() {
		return checklistEClass;
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
	public EAttribute getCompositeRole_Group2() {
		return (EAttribute)compositeRoleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompositeRole_AggregatedRole() {
		return (EReference)compositeRoleEClass.getEStructuralFeatures().get(1);
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
	public EClass getConstraint() {
		return constraintEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConstraint_MainDescription() {
		return (EAttribute)constraintEClass.getEStructuralFeatures().get(0);
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
	public EClass getContentCategoryPackage() {
		return contentCategoryPackageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getContentCategoryPackage_Group2() {
		return (EAttribute)contentCategoryPackageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContentCategoryPackage_ContentCategory() {
		return (EReference)contentCategoryPackageEClass.getEStructuralFeatures().get(1);
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
		return (EAttribute)contentDescriptionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getContentDescription_KeyConsiderations() {
		return (EAttribute)contentDescriptionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContentDescription_Section() {
		return (EReference)contentDescriptionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getContentDescription_ExternalId() {
		return (EAttribute)contentDescriptionEClass.getEStructuralFeatures().get(3);
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
	public EAttribute getContentElement_Group1() {
		return (EAttribute)contentElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getContentElement_Checklist() {
		return (EAttribute)contentElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getContentElement_Concept() {
		return (EAttribute)contentElementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getContentElement_Example() {
		return (EAttribute)contentElementEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getContentElement_Guideline() {
		return (EAttribute)contentElementEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getContentElement_ReusableAsset() {
		return (EAttribute)contentElementEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getContentElement_SupportingMaterial() {
		return (EAttribute)contentElementEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getContentElement_Whitepaper() {
		return (EAttribute)contentElementEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getContentElement_VariabilityBasedOnElement() {
		return (EAttribute)contentElementEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getContentElement_VariabilityType() {
		return (EAttribute)contentElementEClass.getEStructuralFeatures().get(9);
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
	public EAttribute getContentPackage_Group2() {
		return (EAttribute)contentPackageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContentPackage_ContentElement() {
		return (EReference)contentPackageEClass.getEStructuralFeatures().get(1);
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
	public EAttribute getCustomCategory_Group2() {
		return (EAttribute)customCategoryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCustomCategory_CategorizedElement() {
		return (EAttribute)customCategoryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCustomCategory_SubCategory() {
		return (EAttribute)customCategoryEClass.getEStructuralFeatures().get(2);
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
	public EAttribute getDeliverable_Group3() {
		return (EAttribute)deliverableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDeliverable_DeliveredWorkProduct() {
		return (EAttribute)deliverableEClass.getEStructuralFeatures().get(1);
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
		return (EAttribute)deliverableDescriptionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDeliverableDescription_PackagingGuidance() {
		return (EAttribute)deliverableDescriptionEClass.getEStructuralFeatures().get(1);
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
	public EAttribute getDeliveryProcess_Group4() {
		return (EAttribute)deliveryProcessEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDeliveryProcess_CommunicationsMaterial() {
		return (EAttribute)deliveryProcessEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDeliveryProcess_EducationMaterial() {
		return (EAttribute)deliveryProcessEClass.getEStructuralFeatures().get(2);
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
		return (EAttribute)deliveryProcessDescriptionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDeliveryProcessDescription_ProjectCharacteristics() {
		return (EAttribute)deliveryProcessDescriptionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDeliveryProcessDescription_RiskLevel() {
		return (EAttribute)deliveryProcessDescriptionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDeliveryProcessDescription_EstimatingTechnique() {
		return (EAttribute)deliveryProcessDescriptionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDeliveryProcessDescription_ProjectMemberExpertise() {
		return (EAttribute)deliveryProcessDescriptionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDeliveryProcessDescription_TypeOfContract() {
		return (EAttribute)deliveryProcessDescriptionEClass.getEStructuralFeatures().get(5);
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
		return (EReference)describableElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDescribableElement_Fulfill() {
		return (EAttribute)describableElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDescribableElement_IsAbstract() {
		return (EAttribute)describableElementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDescribableElement_Nodeicon() {
		return (EAttribute)describableElementEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDescribableElement_Shapeicon() {
		return (EAttribute)describableElementEClass.getEStructuralFeatures().get(4);
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
		return (EAttribute)descriptorEClass.getEStructuralFeatures().get(0);
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
		return (EAttribute)descriptorDescriptionEClass.getEStructuralFeatures().get(0);
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
	public EAttribute getDiscipline_Group2() {
		return (EAttribute)disciplineEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDiscipline_Task() {
		return (EAttribute)disciplineEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDiscipline_SubDiscipline() {
		return (EReference)disciplineEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDiscipline_ReferenceWorkflow() {
		return (EAttribute)disciplineEClass.getEStructuralFeatures().get(3);
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
	public EAttribute getDisciplineGrouping_Group2() {
		return (EAttribute)disciplineGroupingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDisciplineGrouping_Discipline() {
		return (EAttribute)disciplineGroupingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDocumentRoot() {
		return documentRootEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_Mixed() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_XMLNSPrefixMap() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_XSISchemaLocation() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_MethodConfiguration() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_MethodLibrary() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_MethodPlugin() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(5);
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
	public EAttribute getDomain_Group2() {
		return (EAttribute)domainEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDomain_WorkProduct() {
		return (EAttribute)domainEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDomain_Subdomain() {
		return (EReference)domainEClass.getEStructuralFeatures().get(2);
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
	public EClass getEstimate() {
		return estimateEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEstimate_Group2() {
		return (EAttribute)estimateEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEstimate_EstimationMetric() {
		return (EAttribute)estimateEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEstimate_EstimationConsiderations() {
		return (EAttribute)estimateEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEstimatingMetric() {
		return estimatingMetricEClass;
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
	public EClass getExample() {
		return exampleEClass;
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
	public EClass getGuidanceDescription() {
		return guidanceDescriptionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGuidanceDescription_Attachment() {
		return (EAttribute)guidanceDescriptionEClass.getEStructuralFeatures().get(0);
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
	public EClass getIteration() {
		return iterationEClass;
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
	public EAttribute getKind_ApplicableMetaClassInfo() {
		return (EAttribute)kindEClass.getEStructuralFeatures().get(0);
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
	public EAttribute getMethodConfiguration_BaseConfiguration() {
		return (EAttribute)methodConfigurationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethodConfiguration_MethodPluginSelection() {
		return (EAttribute)methodConfigurationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethodConfiguration_MethodPackageSelection() {
		return (EAttribute)methodConfigurationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethodConfiguration_DefaultView() {
		return (EAttribute)methodConfigurationEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethodConfiguration_ProcessView() {
		return (EAttribute)methodConfigurationEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethodConfiguration_SubtractedCategory() {
		return (EAttribute)methodConfigurationEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethodConfiguration_AddedCategory() {
		return (EAttribute)methodConfigurationEClass.getEStructuralFeatures().get(6);
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
	public EAttribute getMethodElement_Group() {
		return (EAttribute)methodElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodElement_OwnedRule() {
		return (EReference)methodElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodElement_MethodElementProperty() {
		return (EReference)methodElementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethodElement_BriefDescription() {
		return (EAttribute)methodElementEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethodElement_Id() {
		return (EAttribute)methodElementEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethodElement_OrderingGuide() {
		return (EAttribute)methodElementEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethodElement_PresentationName() {
		return (EAttribute)methodElementEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethodElement_Suppressed() {
		return (EAttribute)methodElementEClass.getEStructuralFeatures().get(7);
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
		return (EAttribute)methodElementPropertyEClass.getEStructuralFeatures().get(0);
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
	public EReference getMethodLibrary_MethodPlugin() {
		return (EReference)methodLibraryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodLibrary_MethodConfiguration() {
		return (EReference)methodLibraryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethodLibrary_Tool() {
		return (EAttribute)methodLibraryEClass.getEStructuralFeatures().get(2);
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
	public EAttribute getMethodPackage_Group1() {
		return (EAttribute)methodPackageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethodPackage_ReusedPackage() {
		return (EAttribute)methodPackageEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodPackage_MethodPackage() {
		return (EReference)methodPackageEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethodPackage_Global() {
		return (EAttribute)methodPackageEClass.getEStructuralFeatures().get(3);
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
	public EAttribute getMethodPlugin_ReferencedMethodPlugin() {
		return (EAttribute)methodPluginEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodPlugin_MethodPackage() {
		return (EReference)methodPluginEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethodPlugin_Supporting() {
		return (EAttribute)methodPluginEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethodPlugin_UserChangeable() {
		return (EAttribute)methodPluginEClass.getEStructuralFeatures().get(3);
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
	public EAttribute getMethodUnit_Copyright() {
		return (EAttribute)methodUnitEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethodUnit_Authors() {
		return (EAttribute)methodUnitEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethodUnit_ChangeDate() {
		return (EAttribute)methodUnitEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethodUnit_ChangeDescription() {
		return (EAttribute)methodUnitEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethodUnit_Version() {
		return (EAttribute)methodUnitEClass.getEStructuralFeatures().get(4);
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
	public EAttribute getMilestone_RequiredResult() {
		return (EAttribute)milestoneEClass.getEStructuralFeatures().get(0);
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
		return (EAttribute)namedElementEClass.getEStructuralFeatures().get(0);
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
	public EClass getPackageableElement() {
		return packageableElementEClass;
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
	public EClass getPlanningData() {
		return planningDataEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPlanningData_FinishDate() {
		return (EAttribute)planningDataEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPlanningData_Rank() {
		return (EAttribute)planningDataEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPlanningData_StartDate() {
		return (EAttribute)planningDataEClass.getEStructuralFeatures().get(2);
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
	public EAttribute getPractice_Group2() {
		return (EAttribute)practiceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPractice_ActivityReference() {
		return (EAttribute)practiceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPractice_ContentReference() {
		return (EAttribute)practiceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPractice_SubPractice() {
		return (EReference)practiceEClass.getEStructuralFeatures().get(3);
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
		return (EAttribute)practiceDescriptionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPracticeDescription_Application() {
		return (EAttribute)practiceDescriptionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPracticeDescription_Background() {
		return (EAttribute)practiceDescriptionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPracticeDescription_Goals() {
		return (EAttribute)practiceDescriptionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPracticeDescription_LevelsOfAdoption() {
		return (EAttribute)practiceDescriptionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPracticeDescription_Problem() {
		return (EAttribute)practiceDescriptionEClass.getEStructuralFeatures().get(5);
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
	public EAttribute getProcess_IncludesPattern() {
		return (EAttribute)processEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProcess_DefaultContext() {
		return (EAttribute)processEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProcess_ValidContext() {
		return (EAttribute)processEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProcess_DiagramURI() {
		return (EAttribute)processEClass.getEStructuralFeatures().get(3);
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
	public EAttribute getProcessComponent_Copyright() {
		return (EAttribute)processComponentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessComponent_Interface() {
		return (EReference)processComponentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessComponent_Process() {
		return (EReference)processComponentEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProcessComponent_Authors() {
		return (EAttribute)processComponentEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProcessComponent_ChangeDate() {
		return (EAttribute)processComponentEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProcessComponent_ChangeDescription() {
		return (EAttribute)processComponentEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProcessComponent_Version() {
		return (EAttribute)processComponentEClass.getEStructuralFeatures().get(6);
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
	public EAttribute getProcessComponentInterface_Group2() {
		return (EAttribute)processComponentInterfaceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessComponentInterface_InterfaceSpecification() {
		return (EReference)processComponentInterfaceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessComponentInterface_InterfaceIO() {
		return (EReference)processComponentInterfaceEClass.getEStructuralFeatures().get(2);
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
		return (EAttribute)processDescriptionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProcessDescription_UsageNotes() {
		return (EAttribute)processDescriptionEClass.getEStructuralFeatures().get(1);
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
	public EClass getProcessPackage() {
		return processPackageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProcessPackage_Group2() {
		return (EAttribute)processPackageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessPackage_ProcessElement() {
		return (EReference)processPackageEClass.getEStructuralFeatures().get(1);
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
	public EAttribute getProcessPlanningTemplate_Group4() {
		return (EAttribute)processPlanningTemplateEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProcessPlanningTemplate_BaseProcess() {
		return (EAttribute)processPlanningTemplateEClass.getEStructuralFeatures().get(1);
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
	public EClass getReusableAsset() {
		return reusableAssetEClass;
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
	public EClass getRole() {
		return roleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRole_Group2() {
		return (EAttribute)roleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRole_ResponsibleFor() {
		return (EAttribute)roleEClass.getEStructuralFeatures().get(1);
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
	public EAttribute getRoleDescription_AssignmentApproaches() {
		return (EAttribute)roleDescriptionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRoleDescription_Skills() {
		return (EAttribute)roleDescriptionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRoleDescription_Synonyms() {
		return (EAttribute)roleDescriptionEClass.getEStructuralFeatures().get(2);
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
	public EAttribute getRoleDescriptor_Role() {
		return (EAttribute)roleDescriptorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRoleDescriptor_ResponsibleFor() {
		return (EAttribute)roleDescriptorEClass.getEStructuralFeatures().get(1);
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
	public EAttribute getRoleSet_Group2() {
		return (EAttribute)roleSetEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRoleSet_Role() {
		return (EAttribute)roleSetEClass.getEStructuralFeatures().get(1);
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
	public EAttribute getRoleSetGrouping_Group2() {
		return (EAttribute)roleSetGroupingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRoleSetGrouping_RoleSet() {
		return (EAttribute)roleSetGroupingEClass.getEStructuralFeatures().get(1);
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
	public EReference getSection_SubSection() {
		return (EReference)sectionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSection_Predecessor() {
		return (EAttribute)sectionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSection_Description() {
		return (EAttribute)sectionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSection_SectionName() {
		return (EAttribute)sectionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSection_VariabilityBasedOnElement() {
		return (EAttribute)sectionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSection_VariabilityType() {
		return (EAttribute)sectionEClass.getEStructuralFeatures().get(5);
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
	public EClass getTask() {
		return taskEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_Precondition() {
		return (EAttribute)taskEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_Postcondition() {
		return (EAttribute)taskEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_PerformedBy() {
		return (EAttribute)taskEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_Group2() {
		return (EAttribute)taskEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_MandatoryInput() {
		return (EAttribute)taskEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_Output() {
		return (EAttribute)taskEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_AdditionallyPerformedBy() {
		return (EAttribute)taskEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_OptionalInput() {
		return (EAttribute)taskEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_Estimate() {
		return (EAttribute)taskEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_EstimationConsiderations() {
		return (EAttribute)taskEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_ToolMentor() {
		return (EAttribute)taskEClass.getEStructuralFeatures().get(10);
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
	public EAttribute getTaskDescription_Alternatives() {
		return (EAttribute)taskDescriptionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTaskDescription_Purpose() {
		return (EAttribute)taskDescriptionEClass.getEStructuralFeatures().get(1);
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
	public EAttribute getTaskDescriptor_Task() {
		return (EAttribute)taskDescriptorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTaskDescriptor_PerformedPrimarilyBy() {
		return (EAttribute)taskDescriptorEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTaskDescriptor_Group3() {
		return (EAttribute)taskDescriptorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTaskDescriptor_AdditionallyPerformedBy() {
		return (EAttribute)taskDescriptorEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTaskDescriptor_AssistedBy() {
		return (EAttribute)taskDescriptorEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTaskDescriptor_ExternalInput() {
		return (EAttribute)taskDescriptorEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTaskDescriptor_MandatoryInput() {
		return (EAttribute)taskDescriptorEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTaskDescriptor_OptionalInput() {
		return (EAttribute)taskDescriptorEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTaskDescriptor_Output() {
		return (EAttribute)taskDescriptorEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTaskDescriptor_Step() {
		return (EReference)taskDescriptorEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTaskDescriptor_IsSynchronizedWithSource() {
		return (EAttribute)taskDescriptorEClass.getEStructuralFeatures().get(10);
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
	public EAttribute getTeamProfile_Group2() {
		return (EAttribute)teamProfileEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTeamProfile_Role() {
		return (EAttribute)teamProfileEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTeamProfile_SuperTeam() {
		return (EAttribute)teamProfileEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTeamProfile_SubTeam() {
		return (EAttribute)teamProfileEClass.getEStructuralFeatures().get(3);
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
	public EClass getTermDefinition() {
		return termDefinitionEClass;
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
	public EAttribute getTool_Group2() {
		return (EAttribute)toolEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTool_ToolMentor() {
		return (EAttribute)toolEClass.getEStructuralFeatures().get(1);
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
	public EClass getWorkBreakdownElement() {
		return workBreakdownElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkBreakdownElement_Group2() {
		return (EAttribute)workBreakdownElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWorkBreakdownElement_Predecessor() {
		return (EReference)workBreakdownElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkBreakdownElement_IsEventDriven() {
		return (EAttribute)workBreakdownElementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkBreakdownElement_IsOngoing() {
		return (EAttribute)workBreakdownElementEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkBreakdownElement_IsRepeatable() {
		return (EAttribute)workBreakdownElementEClass.getEStructuralFeatures().get(4);
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
	public EAttribute getWorkDefinition_Precondition() {
		return (EAttribute)workDefinitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkDefinition_Postcondition() {
		return (EAttribute)workDefinitionEClass.getEStructuralFeatures().get(1);
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
	public EAttribute getWorkOrder_Value() {
		return (EAttribute)workOrderEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkOrder_Id() {
		return (EAttribute)workOrderEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkOrder_LinkType() {
		return (EAttribute)workOrderEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkOrder_Properties() {
		return (EAttribute)workOrderEClass.getEStructuralFeatures().get(3);
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
	public EAttribute getWorkProduct_Group2() {
		return (EAttribute)workProductEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkProduct_Estimate() {
		return (EAttribute)workProductEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkProduct_EstimationConsiderations() {
		return (EAttribute)workProductEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkProduct_Report() {
		return (EAttribute)workProductEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkProduct_Template() {
		return (EAttribute)workProductEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkProduct_ToolMentor() {
		return (EAttribute)workProductEClass.getEStructuralFeatures().get(5);
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
	public EAttribute getWorkProductDescription_ImpactOfNotHaving() {
		return (EAttribute)workProductDescriptionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkProductDescription_Purpose() {
		return (EAttribute)workProductDescriptionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkProductDescription_ReasonsForNotNeeding() {
		return (EAttribute)workProductDescriptionEClass.getEStructuralFeatures().get(2);
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
	public EAttribute getWorkProductDescriptor_WorkProduct() {
		return (EAttribute)workProductDescriptorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkProductDescriptor_ResponsibleRole() {
		return (EAttribute)workProductDescriptorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkProductDescriptor_Group2() {
		return (EAttribute)workProductDescriptorEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkProductDescriptor_ExternalInputTo() {
		return (EAttribute)workProductDescriptorEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkProductDescriptor_ImpactedBy() {
		return (EAttribute)workProductDescriptorEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkProductDescriptor_Impacts() {
		return (EAttribute)workProductDescriptorEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkProductDescriptor_MandatoryInputTo() {
		return (EAttribute)workProductDescriptorEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkProductDescriptor_OptionalInputTo() {
		return (EAttribute)workProductDescriptorEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkProductDescriptor_OutputFrom() {
		return (EAttribute)workProductDescriptorEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkProductDescriptor_DeliverableParts() {
		return (EAttribute)workProductDescriptorEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkProductDescriptor_ActivityEntryState() {
		return (EAttribute)workProductDescriptorEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkProductDescriptor_ActivityExitState() {
		return (EAttribute)workProductDescriptorEClass.getEStructuralFeatures().get(11);
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
	public EAttribute getWorkProductType_Group2() {
		return (EAttribute)workProductTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkProductType_WorkProduct() {
		return (EAttribute)workProductTypeEClass.getEStructuralFeatures().get(1);
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
	public EEnum getWorkOrderType() {
		return workOrderTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getVariabilityTypeObject() {
		return variabilityTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getWorkOrderTypeObject() {
		return workOrderTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UmaFactory getUmaFactory() {
		return (UmaFactory)getEFactoryInstance();
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
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		activityEClass = createEClass(ACTIVITY);
		createEAttribute(activityEClass, ACTIVITY__PRECONDITION);
		createEAttribute(activityEClass, ACTIVITY__POSTCONDITION);
		createEAttribute(activityEClass, ACTIVITY__GROUP3);
		createEReference(activityEClass, ACTIVITY__BREAKDOWN_ELEMENT);
		createEAttribute(activityEClass, ACTIVITY__ROADMAP);
		createEAttribute(activityEClass, ACTIVITY__IS_ENACTABLE);
		createEAttribute(activityEClass, ACTIVITY__VARIABILITY_BASED_ON_ELEMENT);
		createEAttribute(activityEClass, ACTIVITY__VARIABILITY_TYPE);

		activityDescriptionEClass = createEClass(ACTIVITY_DESCRIPTION);
		createEAttribute(activityDescriptionEClass, ACTIVITY_DESCRIPTION__ALTERNATIVES);
		createEAttribute(activityDescriptionEClass, ACTIVITY_DESCRIPTION__HOW_TO_STAFF);
		createEAttribute(activityDescriptionEClass, ACTIVITY_DESCRIPTION__PURPOSE);

		applicableMetaClassInfoEClass = createEClass(APPLICABLE_META_CLASS_INFO);
		createEAttribute(applicableMetaClassInfoEClass, APPLICABLE_META_CLASS_INFO__IS_PRIMARY_EXTENSION);

		artifactEClass = createEClass(ARTIFACT);
		createEAttribute(artifactEClass, ARTIFACT__GROUP3);
		createEReference(artifactEClass, ARTIFACT__CONTAINED_ARTIFACT);

		artifactDescriptionEClass = createEClass(ARTIFACT_DESCRIPTION);
		createEAttribute(artifactDescriptionEClass, ARTIFACT_DESCRIPTION__BRIEF_OUTLINE);
		createEAttribute(artifactDescriptionEClass, ARTIFACT_DESCRIPTION__REPRESENTATION_OPTIONS);
		createEAttribute(artifactDescriptionEClass, ARTIFACT_DESCRIPTION__REPRESENTATION);
		createEAttribute(artifactDescriptionEClass, ARTIFACT_DESCRIPTION__NOTATION);

		breakdownElementEClass = createEClass(BREAKDOWN_ELEMENT);
		createEAttribute(breakdownElementEClass, BREAKDOWN_ELEMENT__PRESENTED_AFTER);
		createEAttribute(breakdownElementEClass, BREAKDOWN_ELEMENT__PRESENTED_BEFORE);
		createEAttribute(breakdownElementEClass, BREAKDOWN_ELEMENT__PLANNING_DATA);
		createEAttribute(breakdownElementEClass, BREAKDOWN_ELEMENT__SUPER_ACTIVITY);
		createEAttribute(breakdownElementEClass, BREAKDOWN_ELEMENT__GROUP1);
		createEAttribute(breakdownElementEClass, BREAKDOWN_ELEMENT__CHECKLIST);
		createEAttribute(breakdownElementEClass, BREAKDOWN_ELEMENT__CONCEPT);
		createEAttribute(breakdownElementEClass, BREAKDOWN_ELEMENT__EXAMPLE);
		createEAttribute(breakdownElementEClass, BREAKDOWN_ELEMENT__GUIDELINE);
		createEAttribute(breakdownElementEClass, BREAKDOWN_ELEMENT__REUSABLE_ASSET);
		createEAttribute(breakdownElementEClass, BREAKDOWN_ELEMENT__SUPPORTING_MATERIAL);
		createEAttribute(breakdownElementEClass, BREAKDOWN_ELEMENT__WHITEPAPER);
		createEAttribute(breakdownElementEClass, BREAKDOWN_ELEMENT__HAS_MULTIPLE_OCCURRENCES);
		createEAttribute(breakdownElementEClass, BREAKDOWN_ELEMENT__IS_OPTIONAL);
		createEAttribute(breakdownElementEClass, BREAKDOWN_ELEMENT__IS_PLANNED);
		createEAttribute(breakdownElementEClass, BREAKDOWN_ELEMENT__PREFIX);

		breakdownElementDescriptionEClass = createEClass(BREAKDOWN_ELEMENT_DESCRIPTION);
		createEAttribute(breakdownElementDescriptionEClass, BREAKDOWN_ELEMENT_DESCRIPTION__USAGE_GUIDANCE);

		capabilityPatternEClass = createEClass(CAPABILITY_PATTERN);

		checklistEClass = createEClass(CHECKLIST);

		compositeRoleEClass = createEClass(COMPOSITE_ROLE);
		createEAttribute(compositeRoleEClass, COMPOSITE_ROLE__GROUP2);
		createEReference(compositeRoleEClass, COMPOSITE_ROLE__AGGREGATED_ROLE);

		conceptEClass = createEClass(CONCEPT);

		constraintEClass = createEClass(CONSTRAINT);
		createEAttribute(constraintEClass, CONSTRAINT__MAIN_DESCRIPTION);

		contentCategoryEClass = createEClass(CONTENT_CATEGORY);

		contentCategoryPackageEClass = createEClass(CONTENT_CATEGORY_PACKAGE);
		createEAttribute(contentCategoryPackageEClass, CONTENT_CATEGORY_PACKAGE__GROUP2);
		createEReference(contentCategoryPackageEClass, CONTENT_CATEGORY_PACKAGE__CONTENT_CATEGORY);

		contentDescriptionEClass = createEClass(CONTENT_DESCRIPTION);
		createEAttribute(contentDescriptionEClass, CONTENT_DESCRIPTION__MAIN_DESCRIPTION);
		createEAttribute(contentDescriptionEClass, CONTENT_DESCRIPTION__KEY_CONSIDERATIONS);
		createEReference(contentDescriptionEClass, CONTENT_DESCRIPTION__SECTION);
		createEAttribute(contentDescriptionEClass, CONTENT_DESCRIPTION__EXTERNAL_ID);

		contentElementEClass = createEClass(CONTENT_ELEMENT);
		createEAttribute(contentElementEClass, CONTENT_ELEMENT__GROUP1);
		createEAttribute(contentElementEClass, CONTENT_ELEMENT__CHECKLIST);
		createEAttribute(contentElementEClass, CONTENT_ELEMENT__CONCEPT);
		createEAttribute(contentElementEClass, CONTENT_ELEMENT__EXAMPLE);
		createEAttribute(contentElementEClass, CONTENT_ELEMENT__GUIDELINE);
		createEAttribute(contentElementEClass, CONTENT_ELEMENT__REUSABLE_ASSET);
		createEAttribute(contentElementEClass, CONTENT_ELEMENT__SUPPORTING_MATERIAL);
		createEAttribute(contentElementEClass, CONTENT_ELEMENT__WHITEPAPER);
		createEAttribute(contentElementEClass, CONTENT_ELEMENT__VARIABILITY_BASED_ON_ELEMENT);
		createEAttribute(contentElementEClass, CONTENT_ELEMENT__VARIABILITY_TYPE);

		contentPackageEClass = createEClass(CONTENT_PACKAGE);
		createEAttribute(contentPackageEClass, CONTENT_PACKAGE__GROUP2);
		createEReference(contentPackageEClass, CONTENT_PACKAGE__CONTENT_ELEMENT);

		customCategoryEClass = createEClass(CUSTOM_CATEGORY);
		createEAttribute(customCategoryEClass, CUSTOM_CATEGORY__GROUP2);
		createEAttribute(customCategoryEClass, CUSTOM_CATEGORY__CATEGORIZED_ELEMENT);
		createEAttribute(customCategoryEClass, CUSTOM_CATEGORY__SUB_CATEGORY);

		deliverableEClass = createEClass(DELIVERABLE);
		createEAttribute(deliverableEClass, DELIVERABLE__GROUP3);
		createEAttribute(deliverableEClass, DELIVERABLE__DELIVERED_WORK_PRODUCT);

		deliverableDescriptionEClass = createEClass(DELIVERABLE_DESCRIPTION);
		createEAttribute(deliverableDescriptionEClass, DELIVERABLE_DESCRIPTION__EXTERNAL_DESCRIPTION);
		createEAttribute(deliverableDescriptionEClass, DELIVERABLE_DESCRIPTION__PACKAGING_GUIDANCE);

		deliveryProcessEClass = createEClass(DELIVERY_PROCESS);
		createEAttribute(deliveryProcessEClass, DELIVERY_PROCESS__GROUP4);
		createEAttribute(deliveryProcessEClass, DELIVERY_PROCESS__COMMUNICATIONS_MATERIAL);
		createEAttribute(deliveryProcessEClass, DELIVERY_PROCESS__EDUCATION_MATERIAL);

		deliveryProcessDescriptionEClass = createEClass(DELIVERY_PROCESS_DESCRIPTION);
		createEAttribute(deliveryProcessDescriptionEClass, DELIVERY_PROCESS_DESCRIPTION__SCALE);
		createEAttribute(deliveryProcessDescriptionEClass, DELIVERY_PROCESS_DESCRIPTION__PROJECT_CHARACTERISTICS);
		createEAttribute(deliveryProcessDescriptionEClass, DELIVERY_PROCESS_DESCRIPTION__RISK_LEVEL);
		createEAttribute(deliveryProcessDescriptionEClass, DELIVERY_PROCESS_DESCRIPTION__ESTIMATING_TECHNIQUE);
		createEAttribute(deliveryProcessDescriptionEClass, DELIVERY_PROCESS_DESCRIPTION__PROJECT_MEMBER_EXPERTISE);
		createEAttribute(deliveryProcessDescriptionEClass, DELIVERY_PROCESS_DESCRIPTION__TYPE_OF_CONTRACT);

		describableElementEClass = createEClass(DESCRIBABLE_ELEMENT);
		createEReference(describableElementEClass, DESCRIBABLE_ELEMENT__PRESENTATION);
		createEAttribute(describableElementEClass, DESCRIBABLE_ELEMENT__FULFILL);
		createEAttribute(describableElementEClass, DESCRIBABLE_ELEMENT__IS_ABSTRACT);
		createEAttribute(describableElementEClass, DESCRIBABLE_ELEMENT__NODEICON);
		createEAttribute(describableElementEClass, DESCRIBABLE_ELEMENT__SHAPEICON);

		descriptorEClass = createEClass(DESCRIPTOR);
		createEAttribute(descriptorEClass, DESCRIPTOR__IS_SYNCHRONIZED_WITH_SOURCE);

		descriptorDescriptionEClass = createEClass(DESCRIPTOR_DESCRIPTION);
		createEAttribute(descriptorDescriptionEClass, DESCRIPTOR_DESCRIPTION__REFINED_DESCRIPTION);

		disciplineEClass = createEClass(DISCIPLINE);
		createEAttribute(disciplineEClass, DISCIPLINE__GROUP2);
		createEAttribute(disciplineEClass, DISCIPLINE__TASK);
		createEReference(disciplineEClass, DISCIPLINE__SUB_DISCIPLINE);
		createEAttribute(disciplineEClass, DISCIPLINE__REFERENCE_WORKFLOW);

		disciplineGroupingEClass = createEClass(DISCIPLINE_GROUPING);
		createEAttribute(disciplineGroupingEClass, DISCIPLINE_GROUPING__GROUP2);
		createEAttribute(disciplineGroupingEClass, DISCIPLINE_GROUPING__DISCIPLINE);

		documentRootEClass = createEClass(DOCUMENT_ROOT);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__MIXED);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
		createEReference(documentRootEClass, DOCUMENT_ROOT__METHOD_CONFIGURATION);
		createEReference(documentRootEClass, DOCUMENT_ROOT__METHOD_LIBRARY);
		createEReference(documentRootEClass, DOCUMENT_ROOT__METHOD_PLUGIN);

		domainEClass = createEClass(DOMAIN);
		createEAttribute(domainEClass, DOMAIN__GROUP2);
		createEAttribute(domainEClass, DOMAIN__WORK_PRODUCT);
		createEReference(domainEClass, DOMAIN__SUBDOMAIN);

		elementEClass = createEClass(ELEMENT);

		estimateEClass = createEClass(ESTIMATE);
		createEAttribute(estimateEClass, ESTIMATE__GROUP2);
		createEAttribute(estimateEClass, ESTIMATE__ESTIMATION_METRIC);
		createEAttribute(estimateEClass, ESTIMATE__ESTIMATION_CONSIDERATIONS);

		estimatingMetricEClass = createEClass(ESTIMATING_METRIC);

		estimationConsiderationsEClass = createEClass(ESTIMATION_CONSIDERATIONS);

		exampleEClass = createEClass(EXAMPLE);

		guidanceEClass = createEClass(GUIDANCE);

		guidanceDescriptionEClass = createEClass(GUIDANCE_DESCRIPTION);
		createEAttribute(guidanceDescriptionEClass, GUIDANCE_DESCRIPTION__ATTACHMENT);

		guidelineEClass = createEClass(GUIDELINE);

		iterationEClass = createEClass(ITERATION);

		kindEClass = createEClass(KIND);
		createEAttribute(kindEClass, KIND__APPLICABLE_META_CLASS_INFO);

		methodConfigurationEClass = createEClass(METHOD_CONFIGURATION);
		createEAttribute(methodConfigurationEClass, METHOD_CONFIGURATION__BASE_CONFIGURATION);
		createEAttribute(methodConfigurationEClass, METHOD_CONFIGURATION__METHOD_PLUGIN_SELECTION);
		createEAttribute(methodConfigurationEClass, METHOD_CONFIGURATION__METHOD_PACKAGE_SELECTION);
		createEAttribute(methodConfigurationEClass, METHOD_CONFIGURATION__DEFAULT_VIEW);
		createEAttribute(methodConfigurationEClass, METHOD_CONFIGURATION__PROCESS_VIEW);
		createEAttribute(methodConfigurationEClass, METHOD_CONFIGURATION__SUBTRACTED_CATEGORY);
		createEAttribute(methodConfigurationEClass, METHOD_CONFIGURATION__ADDED_CATEGORY);

		methodElementEClass = createEClass(METHOD_ELEMENT);
		createEAttribute(methodElementEClass, METHOD_ELEMENT__GROUP);
		createEReference(methodElementEClass, METHOD_ELEMENT__OWNED_RULE);
		createEReference(methodElementEClass, METHOD_ELEMENT__METHOD_ELEMENT_PROPERTY);
		createEAttribute(methodElementEClass, METHOD_ELEMENT__BRIEF_DESCRIPTION);
		createEAttribute(methodElementEClass, METHOD_ELEMENT__ID);
		createEAttribute(methodElementEClass, METHOD_ELEMENT__ORDERING_GUIDE);
		createEAttribute(methodElementEClass, METHOD_ELEMENT__PRESENTATION_NAME);
		createEAttribute(methodElementEClass, METHOD_ELEMENT__SUPPRESSED);

		methodElementPropertyEClass = createEClass(METHOD_ELEMENT_PROPERTY);
		createEAttribute(methodElementPropertyEClass, METHOD_ELEMENT_PROPERTY__VALUE);

		methodLibraryEClass = createEClass(METHOD_LIBRARY);
		createEReference(methodLibraryEClass, METHOD_LIBRARY__METHOD_PLUGIN);
		createEReference(methodLibraryEClass, METHOD_LIBRARY__METHOD_CONFIGURATION);
		createEAttribute(methodLibraryEClass, METHOD_LIBRARY__TOOL);

		methodPackageEClass = createEClass(METHOD_PACKAGE);
		createEAttribute(methodPackageEClass, METHOD_PACKAGE__GROUP1);
		createEAttribute(methodPackageEClass, METHOD_PACKAGE__REUSED_PACKAGE);
		createEReference(methodPackageEClass, METHOD_PACKAGE__METHOD_PACKAGE);
		createEAttribute(methodPackageEClass, METHOD_PACKAGE__GLOBAL);

		methodPluginEClass = createEClass(METHOD_PLUGIN);
		createEAttribute(methodPluginEClass, METHOD_PLUGIN__REFERENCED_METHOD_PLUGIN);
		createEReference(methodPluginEClass, METHOD_PLUGIN__METHOD_PACKAGE);
		createEAttribute(methodPluginEClass, METHOD_PLUGIN__SUPPORTING);
		createEAttribute(methodPluginEClass, METHOD_PLUGIN__USER_CHANGEABLE);

		methodUnitEClass = createEClass(METHOD_UNIT);
		createEAttribute(methodUnitEClass, METHOD_UNIT__COPYRIGHT);
		createEAttribute(methodUnitEClass, METHOD_UNIT__AUTHORS);
		createEAttribute(methodUnitEClass, METHOD_UNIT__CHANGE_DATE);
		createEAttribute(methodUnitEClass, METHOD_UNIT__CHANGE_DESCRIPTION);
		createEAttribute(methodUnitEClass, METHOD_UNIT__VERSION);

		milestoneEClass = createEClass(MILESTONE);
		createEAttribute(milestoneEClass, MILESTONE__REQUIRED_RESULT);

		namedElementEClass = createEClass(NAMED_ELEMENT);
		createEAttribute(namedElementEClass, NAMED_ELEMENT__NAME);

		outcomeEClass = createEClass(OUTCOME);

		packageableElementEClass = createEClass(PACKAGEABLE_ELEMENT);

		phaseEClass = createEClass(PHASE);

		planningDataEClass = createEClass(PLANNING_DATA);
		createEAttribute(planningDataEClass, PLANNING_DATA__FINISH_DATE);
		createEAttribute(planningDataEClass, PLANNING_DATA__RANK);
		createEAttribute(planningDataEClass, PLANNING_DATA__START_DATE);

		practiceEClass = createEClass(PRACTICE);
		createEAttribute(practiceEClass, PRACTICE__GROUP2);
		createEAttribute(practiceEClass, PRACTICE__ACTIVITY_REFERENCE);
		createEAttribute(practiceEClass, PRACTICE__CONTENT_REFERENCE);
		createEReference(practiceEClass, PRACTICE__SUB_PRACTICE);

		practiceDescriptionEClass = createEClass(PRACTICE_DESCRIPTION);
		createEAttribute(practiceDescriptionEClass, PRACTICE_DESCRIPTION__ADDITIONAL_INFO);
		createEAttribute(practiceDescriptionEClass, PRACTICE_DESCRIPTION__APPLICATION);
		createEAttribute(practiceDescriptionEClass, PRACTICE_DESCRIPTION__BACKGROUND);
		createEAttribute(practiceDescriptionEClass, PRACTICE_DESCRIPTION__GOALS);
		createEAttribute(practiceDescriptionEClass, PRACTICE_DESCRIPTION__LEVELS_OF_ADOPTION);
		createEAttribute(practiceDescriptionEClass, PRACTICE_DESCRIPTION__PROBLEM);

		processEClass = createEClass(PROCESS);
		createEAttribute(processEClass, PROCESS__INCLUDES_PATTERN);
		createEAttribute(processEClass, PROCESS__DEFAULT_CONTEXT);
		createEAttribute(processEClass, PROCESS__VALID_CONTEXT);
		createEAttribute(processEClass, PROCESS__DIAGRAM_URI);

		processComponentEClass = createEClass(PROCESS_COMPONENT);
		createEAttribute(processComponentEClass, PROCESS_COMPONENT__COPYRIGHT);
		createEReference(processComponentEClass, PROCESS_COMPONENT__INTERFACE);
		createEReference(processComponentEClass, PROCESS_COMPONENT__PROCESS);
		createEAttribute(processComponentEClass, PROCESS_COMPONENT__AUTHORS);
		createEAttribute(processComponentEClass, PROCESS_COMPONENT__CHANGE_DATE);
		createEAttribute(processComponentEClass, PROCESS_COMPONENT__CHANGE_DESCRIPTION);
		createEAttribute(processComponentEClass, PROCESS_COMPONENT__VERSION);

		processComponentInterfaceEClass = createEClass(PROCESS_COMPONENT_INTERFACE);
		createEAttribute(processComponentInterfaceEClass, PROCESS_COMPONENT_INTERFACE__GROUP2);
		createEReference(processComponentInterfaceEClass, PROCESS_COMPONENT_INTERFACE__INTERFACE_SPECIFICATION);
		createEReference(processComponentInterfaceEClass, PROCESS_COMPONENT_INTERFACE__INTERFACE_IO);

		processDescriptionEClass = createEClass(PROCESS_DESCRIPTION);
		createEAttribute(processDescriptionEClass, PROCESS_DESCRIPTION__SCOPE);
		createEAttribute(processDescriptionEClass, PROCESS_DESCRIPTION__USAGE_NOTES);

		processElementEClass = createEClass(PROCESS_ELEMENT);

		processPackageEClass = createEClass(PROCESS_PACKAGE);
		createEAttribute(processPackageEClass, PROCESS_PACKAGE__GROUP2);
		createEReference(processPackageEClass, PROCESS_PACKAGE__PROCESS_ELEMENT);

		processPlanningTemplateEClass = createEClass(PROCESS_PLANNING_TEMPLATE);
		createEAttribute(processPlanningTemplateEClass, PROCESS_PLANNING_TEMPLATE__GROUP4);
		createEAttribute(processPlanningTemplateEClass, PROCESS_PLANNING_TEMPLATE__BASE_PROCESS);

		reportEClass = createEClass(REPORT);

		reusableAssetEClass = createEClass(REUSABLE_ASSET);

		roadmapEClass = createEClass(ROADMAP);

		roleEClass = createEClass(ROLE);
		createEAttribute(roleEClass, ROLE__GROUP2);
		createEAttribute(roleEClass, ROLE__RESPONSIBLE_FOR);

		roleDescriptionEClass = createEClass(ROLE_DESCRIPTION);
		createEAttribute(roleDescriptionEClass, ROLE_DESCRIPTION__ASSIGNMENT_APPROACHES);
		createEAttribute(roleDescriptionEClass, ROLE_DESCRIPTION__SKILLS);
		createEAttribute(roleDescriptionEClass, ROLE_DESCRIPTION__SYNONYMS);

		roleDescriptorEClass = createEClass(ROLE_DESCRIPTOR);
		createEAttribute(roleDescriptorEClass, ROLE_DESCRIPTOR__ROLE);
		createEAttribute(roleDescriptorEClass, ROLE_DESCRIPTOR__RESPONSIBLE_FOR);

		roleSetEClass = createEClass(ROLE_SET);
		createEAttribute(roleSetEClass, ROLE_SET__GROUP2);
		createEAttribute(roleSetEClass, ROLE_SET__ROLE);

		roleSetGroupingEClass = createEClass(ROLE_SET_GROUPING);
		createEAttribute(roleSetGroupingEClass, ROLE_SET_GROUPING__GROUP2);
		createEAttribute(roleSetGroupingEClass, ROLE_SET_GROUPING__ROLE_SET);

		sectionEClass = createEClass(SECTION);
		createEReference(sectionEClass, SECTION__SUB_SECTION);
		createEAttribute(sectionEClass, SECTION__PREDECESSOR);
		createEAttribute(sectionEClass, SECTION__DESCRIPTION);
		createEAttribute(sectionEClass, SECTION__SECTION_NAME);
		createEAttribute(sectionEClass, SECTION__VARIABILITY_BASED_ON_ELEMENT);
		createEAttribute(sectionEClass, SECTION__VARIABILITY_TYPE);

		supportingMaterialEClass = createEClass(SUPPORTING_MATERIAL);

		taskEClass = createEClass(TASK);
		createEAttribute(taskEClass, TASK__PRECONDITION);
		createEAttribute(taskEClass, TASK__POSTCONDITION);
		createEAttribute(taskEClass, TASK__PERFORMED_BY);
		createEAttribute(taskEClass, TASK__GROUP2);
		createEAttribute(taskEClass, TASK__MANDATORY_INPUT);
		createEAttribute(taskEClass, TASK__OUTPUT);
		createEAttribute(taskEClass, TASK__ADDITIONALLY_PERFORMED_BY);
		createEAttribute(taskEClass, TASK__OPTIONAL_INPUT);
		createEAttribute(taskEClass, TASK__ESTIMATE);
		createEAttribute(taskEClass, TASK__ESTIMATION_CONSIDERATIONS);
		createEAttribute(taskEClass, TASK__TOOL_MENTOR);

		taskDescriptionEClass = createEClass(TASK_DESCRIPTION);
		createEAttribute(taskDescriptionEClass, TASK_DESCRIPTION__ALTERNATIVES);
		createEAttribute(taskDescriptionEClass, TASK_DESCRIPTION__PURPOSE);

		taskDescriptorEClass = createEClass(TASK_DESCRIPTOR);
		createEAttribute(taskDescriptorEClass, TASK_DESCRIPTOR__TASK);
		createEAttribute(taskDescriptorEClass, TASK_DESCRIPTOR__GROUP3);
		createEAttribute(taskDescriptorEClass, TASK_DESCRIPTOR__PERFORMED_PRIMARILY_BY);
		createEAttribute(taskDescriptorEClass, TASK_DESCRIPTOR__ADDITIONALLY_PERFORMED_BY);
		createEAttribute(taskDescriptorEClass, TASK_DESCRIPTOR__ASSISTED_BY);
		createEAttribute(taskDescriptorEClass, TASK_DESCRIPTOR__EXTERNAL_INPUT);
		createEAttribute(taskDescriptorEClass, TASK_DESCRIPTOR__MANDATORY_INPUT);
		createEAttribute(taskDescriptorEClass, TASK_DESCRIPTOR__OPTIONAL_INPUT);
		createEAttribute(taskDescriptorEClass, TASK_DESCRIPTOR__OUTPUT);
		createEReference(taskDescriptorEClass, TASK_DESCRIPTOR__STEP);
		createEAttribute(taskDescriptorEClass, TASK_DESCRIPTOR__IS_SYNCHRONIZED_WITH_SOURCE);

		teamProfileEClass = createEClass(TEAM_PROFILE);
		createEAttribute(teamProfileEClass, TEAM_PROFILE__GROUP2);
		createEAttribute(teamProfileEClass, TEAM_PROFILE__ROLE);
		createEAttribute(teamProfileEClass, TEAM_PROFILE__SUPER_TEAM);
		createEAttribute(teamProfileEClass, TEAM_PROFILE__SUB_TEAM);

		templateEClass = createEClass(TEMPLATE);

		termDefinitionEClass = createEClass(TERM_DEFINITION);

		toolEClass = createEClass(TOOL);
		createEAttribute(toolEClass, TOOL__GROUP2);
		createEAttribute(toolEClass, TOOL__TOOL_MENTOR);

		toolMentorEClass = createEClass(TOOL_MENTOR);

		whitepaperEClass = createEClass(WHITEPAPER);

		workBreakdownElementEClass = createEClass(WORK_BREAKDOWN_ELEMENT);
		createEAttribute(workBreakdownElementEClass, WORK_BREAKDOWN_ELEMENT__GROUP2);
		createEReference(workBreakdownElementEClass, WORK_BREAKDOWN_ELEMENT__PREDECESSOR);
		createEAttribute(workBreakdownElementEClass, WORK_BREAKDOWN_ELEMENT__IS_EVENT_DRIVEN);
		createEAttribute(workBreakdownElementEClass, WORK_BREAKDOWN_ELEMENT__IS_ONGOING);
		createEAttribute(workBreakdownElementEClass, WORK_BREAKDOWN_ELEMENT__IS_REPEATABLE);

		workDefinitionEClass = createEClass(WORK_DEFINITION);
		createEAttribute(workDefinitionEClass, WORK_DEFINITION__PRECONDITION);
		createEAttribute(workDefinitionEClass, WORK_DEFINITION__POSTCONDITION);

		workOrderEClass = createEClass(WORK_ORDER);
		createEAttribute(workOrderEClass, WORK_ORDER__VALUE);
		createEAttribute(workOrderEClass, WORK_ORDER__ID);
		createEAttribute(workOrderEClass, WORK_ORDER__LINK_TYPE);
		createEAttribute(workOrderEClass, WORK_ORDER__PROPERTIES);

		workProductEClass = createEClass(WORK_PRODUCT);
		createEAttribute(workProductEClass, WORK_PRODUCT__GROUP2);
		createEAttribute(workProductEClass, WORK_PRODUCT__ESTIMATE);
		createEAttribute(workProductEClass, WORK_PRODUCT__ESTIMATION_CONSIDERATIONS);
		createEAttribute(workProductEClass, WORK_PRODUCT__REPORT);
		createEAttribute(workProductEClass, WORK_PRODUCT__TEMPLATE);
		createEAttribute(workProductEClass, WORK_PRODUCT__TOOL_MENTOR);

		workProductDescriptionEClass = createEClass(WORK_PRODUCT_DESCRIPTION);
		createEAttribute(workProductDescriptionEClass, WORK_PRODUCT_DESCRIPTION__IMPACT_OF_NOT_HAVING);
		createEAttribute(workProductDescriptionEClass, WORK_PRODUCT_DESCRIPTION__PURPOSE);
		createEAttribute(workProductDescriptionEClass, WORK_PRODUCT_DESCRIPTION__REASONS_FOR_NOT_NEEDING);

		workProductDescriptorEClass = createEClass(WORK_PRODUCT_DESCRIPTOR);
		createEAttribute(workProductDescriptorEClass, WORK_PRODUCT_DESCRIPTOR__WORK_PRODUCT);
		createEAttribute(workProductDescriptorEClass, WORK_PRODUCT_DESCRIPTOR__RESPONSIBLE_ROLE);
		createEAttribute(workProductDescriptorEClass, WORK_PRODUCT_DESCRIPTOR__GROUP2);
		createEAttribute(workProductDescriptorEClass, WORK_PRODUCT_DESCRIPTOR__EXTERNAL_INPUT_TO);
		createEAttribute(workProductDescriptorEClass, WORK_PRODUCT_DESCRIPTOR__IMPACTED_BY);
		createEAttribute(workProductDescriptorEClass, WORK_PRODUCT_DESCRIPTOR__IMPACTS);
		createEAttribute(workProductDescriptorEClass, WORK_PRODUCT_DESCRIPTOR__MANDATORY_INPUT_TO);
		createEAttribute(workProductDescriptorEClass, WORK_PRODUCT_DESCRIPTOR__OPTIONAL_INPUT_TO);
		createEAttribute(workProductDescriptorEClass, WORK_PRODUCT_DESCRIPTOR__OUTPUT_FROM);
		createEAttribute(workProductDescriptorEClass, WORK_PRODUCT_DESCRIPTOR__DELIVERABLE_PARTS);
		createEAttribute(workProductDescriptorEClass, WORK_PRODUCT_DESCRIPTOR__ACTIVITY_ENTRY_STATE);
		createEAttribute(workProductDescriptorEClass, WORK_PRODUCT_DESCRIPTOR__ACTIVITY_EXIT_STATE);

		workProductTypeEClass = createEClass(WORK_PRODUCT_TYPE);
		createEAttribute(workProductTypeEClass, WORK_PRODUCT_TYPE__GROUP2);
		createEAttribute(workProductTypeEClass, WORK_PRODUCT_TYPE__WORK_PRODUCT);

		// Create enums
		variabilityTypeEEnum = createEEnum(VARIABILITY_TYPE);
		workOrderTypeEEnum = createEEnum(WORK_ORDER_TYPE);

		// Create data types
		variabilityTypeObjectEDataType = createEDataType(VARIABILITY_TYPE_OBJECT);
		workOrderTypeObjectEDataType = createEDataType(WORK_ORDER_TYPE_OBJECT);
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
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		XMLTypePackage theXMLTypePackage = (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		activityEClass.getESuperTypes().add(this.getWorkBreakdownElement());
		activityDescriptionEClass.getESuperTypes().add(this.getBreakdownElementDescription());
		applicableMetaClassInfoEClass.getESuperTypes().add(this.getPackageableElement());
		artifactEClass.getESuperTypes().add(this.getWorkProduct());
		artifactDescriptionEClass.getESuperTypes().add(this.getWorkProductDescription());
		breakdownElementEClass.getESuperTypes().add(this.getProcessElement());
		breakdownElementDescriptionEClass.getESuperTypes().add(this.getContentDescription());
		capabilityPatternEClass.getESuperTypes().add(this.getProcess());
		checklistEClass.getESuperTypes().add(this.getGuidance());
		compositeRoleEClass.getESuperTypes().add(this.getRoleDescriptor());
		conceptEClass.getESuperTypes().add(this.getGuidance());
		constraintEClass.getESuperTypes().add(this.getMethodElement());
		contentCategoryEClass.getESuperTypes().add(this.getContentElement());
		contentCategoryPackageEClass.getESuperTypes().add(this.getMethodPackage());
		contentDescriptionEClass.getESuperTypes().add(this.getMethodUnit());
		contentElementEClass.getESuperTypes().add(this.getDescribableElement());
		contentPackageEClass.getESuperTypes().add(this.getMethodPackage());
		customCategoryEClass.getESuperTypes().add(this.getContentCategory());
		deliverableEClass.getESuperTypes().add(this.getWorkProduct());
		deliverableDescriptionEClass.getESuperTypes().add(this.getWorkProductDescription());
		deliveryProcessEClass.getESuperTypes().add(this.getProcess());
		deliveryProcessDescriptionEClass.getESuperTypes().add(this.getProcessDescription());
		describableElementEClass.getESuperTypes().add(this.getMethodElement());
		descriptorEClass.getESuperTypes().add(this.getBreakdownElement());
		descriptorDescriptionEClass.getESuperTypes().add(this.getBreakdownElementDescription());
		disciplineEClass.getESuperTypes().add(this.getContentCategory());
		disciplineGroupingEClass.getESuperTypes().add(this.getContentCategory());
		domainEClass.getESuperTypes().add(this.getContentCategory());
		estimateEClass.getESuperTypes().add(this.getGuidance());
		estimatingMetricEClass.getESuperTypes().add(this.getGuidance());
		estimationConsiderationsEClass.getESuperTypes().add(this.getGuidance());
		exampleEClass.getESuperTypes().add(this.getGuidance());
		guidanceEClass.getESuperTypes().add(this.getContentElement());
		guidanceDescriptionEClass.getESuperTypes().add(this.getContentDescription());
		guidelineEClass.getESuperTypes().add(this.getGuidance());
		iterationEClass.getESuperTypes().add(this.getActivity());
		kindEClass.getESuperTypes().add(this.getContentElement());
		methodConfigurationEClass.getESuperTypes().add(this.getMethodUnit());
		methodElementEClass.getESuperTypes().add(this.getPackageableElement());
		methodElementPropertyEClass.getESuperTypes().add(this.getPackageableElement());
		methodLibraryEClass.getESuperTypes().add(this.getMethodUnit());
		methodPackageEClass.getESuperTypes().add(this.getMethodElement());
		methodPluginEClass.getESuperTypes().add(this.getMethodUnit());
		methodUnitEClass.getESuperTypes().add(this.getMethodElement());
		milestoneEClass.getESuperTypes().add(this.getWorkBreakdownElement());
		namedElementEClass.getESuperTypes().add(this.getElement());
		outcomeEClass.getESuperTypes().add(this.getWorkProduct());
		packageableElementEClass.getESuperTypes().add(this.getNamedElement());
		phaseEClass.getESuperTypes().add(this.getActivity());
		planningDataEClass.getESuperTypes().add(this.getProcessElement());
		practiceEClass.getESuperTypes().add(this.getGuidance());
		practiceDescriptionEClass.getESuperTypes().add(this.getContentDescription());
		processEClass.getESuperTypes().add(this.getActivity());
		processComponentEClass.getESuperTypes().add(this.getProcessPackage());
		processComponentInterfaceEClass.getESuperTypes().add(this.getBreakdownElement());
		processDescriptionEClass.getESuperTypes().add(this.getActivityDescription());
		processElementEClass.getESuperTypes().add(this.getDescribableElement());
		processPackageEClass.getESuperTypes().add(this.getMethodPackage());
		processPlanningTemplateEClass.getESuperTypes().add(this.getProcess());
		reportEClass.getESuperTypes().add(this.getGuidance());
		reusableAssetEClass.getESuperTypes().add(this.getGuidance());
		roadmapEClass.getESuperTypes().add(this.getGuidance());
		roleEClass.getESuperTypes().add(this.getContentElement());
		roleDescriptionEClass.getESuperTypes().add(this.getContentDescription());
		roleDescriptorEClass.getESuperTypes().add(this.getDescriptor());
		roleSetEClass.getESuperTypes().add(this.getContentCategory());
		roleSetGroupingEClass.getESuperTypes().add(this.getContentCategory());
		sectionEClass.getESuperTypes().add(this.getMethodElement());
		supportingMaterialEClass.getESuperTypes().add(this.getGuidance());
		taskEClass.getESuperTypes().add(this.getContentElement());
		taskDescriptionEClass.getESuperTypes().add(this.getContentDescription());
		taskDescriptorEClass.getESuperTypes().add(this.getWorkBreakdownElement());
		teamProfileEClass.getESuperTypes().add(this.getBreakdownElement());
		templateEClass.getESuperTypes().add(this.getGuidance());
		termDefinitionEClass.getESuperTypes().add(this.getGuidance());
		toolEClass.getESuperTypes().add(this.getContentCategory());
		toolMentorEClass.getESuperTypes().add(this.getGuidance());
		whitepaperEClass.getESuperTypes().add(this.getConcept());
		workBreakdownElementEClass.getESuperTypes().add(this.getBreakdownElement());
		workDefinitionEClass.getESuperTypes().add(this.getMethodElement());
		workProductEClass.getESuperTypes().add(this.getContentElement());
		workProductDescriptionEClass.getESuperTypes().add(this.getContentDescription());
		workProductDescriptorEClass.getESuperTypes().add(this.getDescriptor());
		workProductTypeEClass.getESuperTypes().add(this.getContentCategory());

		// Initialize classes and features; add operations and parameters
		initEClass(activityEClass, Activity.class, "Activity", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getActivity_Precondition(), theXMLTypePackage.getString(), "precondition", null, 0, 1, Activity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getActivity_Postcondition(), theXMLTypePackage.getString(), "postcondition", null, 0, 1, Activity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getActivity_Group3(), ecorePackage.getEFeatureMapEntry(), "group3", null, 0, -1, Activity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getActivity_BreakdownElement(), this.getBreakdownElement(), null, "breakdownElement", null, 0, -1, Activity.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getActivity_Roadmap(), theXMLTypePackage.getString(), "roadmap", null, 0, -1, Activity.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getActivity_IsEnactable(), theXMLTypePackage.getBoolean(), "isEnactable", null, 0, 1, Activity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getActivity_VariabilityBasedOnElement(), theXMLTypePackage.getString(), "variabilityBasedOnElement", null, 0, 1, Activity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getActivity_VariabilityType(), this.getVariabilityType(), "variabilityType", "na", 0, 1, Activity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(activityDescriptionEClass, ActivityDescription.class, "ActivityDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getActivityDescription_Alternatives(), theXMLTypePackage.getString(), "alternatives", null, 0, 1, ActivityDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getActivityDescription_HowToStaff(), theXMLTypePackage.getString(), "howToStaff", null, 0, 1, ActivityDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getActivityDescription_Purpose(), theXMLTypePackage.getString(), "purpose", null, 0, 1, ActivityDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(applicableMetaClassInfoEClass, ApplicableMetaClassInfo.class, "ApplicableMetaClassInfo", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getApplicableMetaClassInfo_IsPrimaryExtension(), theXMLTypePackage.getBoolean(), "isPrimaryExtension", null, 0, 1, ApplicableMetaClassInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(artifactEClass, Artifact.class, "Artifact", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getArtifact_Group3(), ecorePackage.getEFeatureMapEntry(), "group3", null, 0, -1, Artifact.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getArtifact_ContainedArtifact(), this.getArtifact(), null, "containedArtifact", null, 0, -1, Artifact.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(artifactDescriptionEClass, ArtifactDescription.class, "ArtifactDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getArtifactDescription_BriefOutline(), theXMLTypePackage.getString(), "briefOutline", null, 0, 1, ArtifactDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getArtifactDescription_RepresentationOptions(), theXMLTypePackage.getString(), "representationOptions", null, 0, 1, ArtifactDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getArtifactDescription_Representation(), theXMLTypePackage.getString(), "representation", null, 0, 1, ArtifactDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getArtifactDescription_Notation(), theXMLTypePackage.getString(), "notation", null, 0, 1, ArtifactDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(breakdownElementEClass, BreakdownElement.class, "BreakdownElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBreakdownElement_PresentedAfter(), theXMLTypePackage.getString(), "presentedAfter", null, 0, 1, BreakdownElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBreakdownElement_PresentedBefore(), theXMLTypePackage.getString(), "presentedBefore", null, 0, 1, BreakdownElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBreakdownElement_PlanningData(), theXMLTypePackage.getString(), "planningData", null, 0, 1, BreakdownElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBreakdownElement_SuperActivity(), theXMLTypePackage.getString(), "superActivity", null, 0, 1, BreakdownElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBreakdownElement_Group1(), ecorePackage.getEFeatureMapEntry(), "group1", null, 0, -1, BreakdownElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBreakdownElement_Checklist(), theXMLTypePackage.getString(), "checklist", null, 0, -1, BreakdownElement.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getBreakdownElement_Concept(), theXMLTypePackage.getString(), "concept", null, 0, -1, BreakdownElement.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getBreakdownElement_Example(), theXMLTypePackage.getString(), "example", null, 0, -1, BreakdownElement.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getBreakdownElement_Guideline(), theXMLTypePackage.getString(), "guideline", null, 0, -1, BreakdownElement.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getBreakdownElement_ReusableAsset(), theXMLTypePackage.getString(), "reusableAsset", null, 0, -1, BreakdownElement.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getBreakdownElement_SupportingMaterial(), theXMLTypePackage.getString(), "supportingMaterial", null, 0, -1, BreakdownElement.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getBreakdownElement_Whitepaper(), theXMLTypePackage.getString(), "whitepaper", null, 0, -1, BreakdownElement.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getBreakdownElement_HasMultipleOccurrences(), theXMLTypePackage.getBoolean(), "hasMultipleOccurrences", null, 0, 1, BreakdownElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBreakdownElement_IsOptional(), theXMLTypePackage.getBoolean(), "isOptional", null, 0, 1, BreakdownElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBreakdownElement_IsPlanned(), theXMLTypePackage.getBoolean(), "isPlanned", null, 0, 1, BreakdownElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBreakdownElement_Prefix(), theXMLTypePackage.getString(), "prefix", null, 0, 1, BreakdownElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(breakdownElementDescriptionEClass, BreakdownElementDescription.class, "BreakdownElementDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBreakdownElementDescription_UsageGuidance(), theXMLTypePackage.getString(), "usageGuidance", null, 0, 1, BreakdownElementDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(capabilityPatternEClass, CapabilityPattern.class, "CapabilityPattern", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(checklistEClass, Checklist.class, "Checklist", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(compositeRoleEClass, CompositeRole.class, "CompositeRole", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCompositeRole_Group2(), ecorePackage.getEFeatureMapEntry(), "group2", null, 0, -1, CompositeRole.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCompositeRole_AggregatedRole(), this.getRole(), null, "aggregatedRole", null, 0, -1, CompositeRole.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(conceptEClass, Concept.class, "Concept", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(constraintEClass, Constraint.class, "Constraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getConstraint_MainDescription(), theXMLTypePackage.getString(), "mainDescription", null, 0, 1, Constraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(contentCategoryEClass, ContentCategory.class, "ContentCategory", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(contentCategoryPackageEClass, ContentCategoryPackage.class, "ContentCategoryPackage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getContentCategoryPackage_Group2(), ecorePackage.getEFeatureMapEntry(), "group2", null, 0, -1, ContentCategoryPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContentCategoryPackage_ContentCategory(), this.getContentCategory(), null, "contentCategory", null, 0, -1, ContentCategoryPackage.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(contentDescriptionEClass, ContentDescription.class, "ContentDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getContentDescription_MainDescription(), theXMLTypePackage.getString(), "mainDescription", null, 0, 1, ContentDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getContentDescription_KeyConsiderations(), theXMLTypePackage.getString(), "keyConsiderations", null, 0, 1, ContentDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContentDescription_Section(), this.getSection(), null, "section", null, 0, -1, ContentDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getContentDescription_ExternalId(), theXMLTypePackage.getString(), "externalId", null, 0, 1, ContentDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(contentElementEClass, ContentElement.class, "ContentElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getContentElement_Group1(), ecorePackage.getEFeatureMapEntry(), "group1", null, 0, -1, ContentElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getContentElement_Checklist(), theXMLTypePackage.getString(), "checklist", null, 0, -1, ContentElement.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getContentElement_Concept(), theXMLTypePackage.getString(), "concept", null, 0, -1, ContentElement.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getContentElement_Example(), theXMLTypePackage.getString(), "example", null, 0, -1, ContentElement.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getContentElement_Guideline(), theXMLTypePackage.getString(), "guideline", null, 0, -1, ContentElement.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getContentElement_ReusableAsset(), theXMLTypePackage.getString(), "reusableAsset", null, 0, -1, ContentElement.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getContentElement_SupportingMaterial(), theXMLTypePackage.getString(), "supportingMaterial", null, 0, -1, ContentElement.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getContentElement_Whitepaper(), theXMLTypePackage.getString(), "whitepaper", null, 0, -1, ContentElement.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getContentElement_VariabilityBasedOnElement(), theXMLTypePackage.getString(), "variabilityBasedOnElement", null, 0, 1, ContentElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getContentElement_VariabilityType(), this.getVariabilityType(), "variabilityType", "na", 0, 1, ContentElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(contentPackageEClass, ContentPackage.class, "ContentPackage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getContentPackage_Group2(), ecorePackage.getEFeatureMapEntry(), "group2", null, 0, -1, ContentPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContentPackage_ContentElement(), this.getContentElement(), null, "contentElement", null, 0, -1, ContentPackage.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(customCategoryEClass, CustomCategory.class, "CustomCategory", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCustomCategory_Group2(), ecorePackage.getEFeatureMapEntry(), "group2", null, 0, -1, CustomCategory.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomCategory_CategorizedElement(), theXMLTypePackage.getString(), "categorizedElement", null, 0, -1, CustomCategory.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomCategory_SubCategory(), theXMLTypePackage.getString(), "subCategory", null, 0, -1, CustomCategory.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(deliverableEClass, Deliverable.class, "Deliverable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDeliverable_Group3(), ecorePackage.getEFeatureMapEntry(), "group3", null, 0, -1, Deliverable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDeliverable_DeliveredWorkProduct(), theXMLTypePackage.getString(), "deliveredWorkProduct", null, 0, -1, Deliverable.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(deliverableDescriptionEClass, DeliverableDescription.class, "DeliverableDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDeliverableDescription_ExternalDescription(), theXMLTypePackage.getString(), "externalDescription", null, 0, 1, DeliverableDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDeliverableDescription_PackagingGuidance(), theXMLTypePackage.getString(), "packagingGuidance", null, 0, 1, DeliverableDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(deliveryProcessEClass, DeliveryProcess.class, "DeliveryProcess", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDeliveryProcess_Group4(), ecorePackage.getEFeatureMapEntry(), "group4", null, 0, -1, DeliveryProcess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDeliveryProcess_CommunicationsMaterial(), theXMLTypePackage.getString(), "communicationsMaterial", null, 0, -1, DeliveryProcess.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDeliveryProcess_EducationMaterial(), theXMLTypePackage.getString(), "educationMaterial", null, 0, -1, DeliveryProcess.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(deliveryProcessDescriptionEClass, DeliveryProcessDescription.class, "DeliveryProcessDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDeliveryProcessDescription_Scale(), theXMLTypePackage.getString(), "scale", null, 0, 1, DeliveryProcessDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDeliveryProcessDescription_ProjectCharacteristics(), theXMLTypePackage.getString(), "projectCharacteristics", null, 0, 1, DeliveryProcessDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDeliveryProcessDescription_RiskLevel(), theXMLTypePackage.getString(), "riskLevel", null, 0, 1, DeliveryProcessDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDeliveryProcessDescription_EstimatingTechnique(), theXMLTypePackage.getString(), "estimatingTechnique", null, 0, 1, DeliveryProcessDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDeliveryProcessDescription_ProjectMemberExpertise(), theXMLTypePackage.getString(), "projectMemberExpertise", null, 0, 1, DeliveryProcessDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDeliveryProcessDescription_TypeOfContract(), theXMLTypePackage.getString(), "typeOfContract", null, 0, 1, DeliveryProcessDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(describableElementEClass, DescribableElement.class, "DescribableElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDescribableElement_Presentation(), this.getContentDescription(), null, "presentation", null, 0, 1, DescribableElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDescribableElement_Fulfill(), theXMLTypePackage.getString(), "fulfill", null, 0, -1, DescribableElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDescribableElement_IsAbstract(), theXMLTypePackage.getBoolean(), "isAbstract", null, 0, 1, DescribableElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDescribableElement_Nodeicon(), theXMLTypePackage.getString(), "nodeicon", null, 0, 1, DescribableElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDescribableElement_Shapeicon(), theXMLTypePackage.getString(), "shapeicon", null, 0, 1, DescribableElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(descriptorEClass, org.eclipse.epf.xml.uma.Descriptor.class, "Descriptor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDescriptor_IsSynchronizedWithSource(), theXMLTypePackage.getBoolean(), "isSynchronizedWithSource", null, 0, 1, org.eclipse.epf.xml.uma.Descriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(descriptorDescriptionEClass, DescriptorDescription.class, "DescriptorDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDescriptorDescription_RefinedDescription(), theXMLTypePackage.getString(), "refinedDescription", null, 0, 1, DescriptorDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(disciplineEClass, Discipline.class, "Discipline", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDiscipline_Group2(), ecorePackage.getEFeatureMapEntry(), "group2", null, 0, -1, Discipline.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDiscipline_Task(), theXMLTypePackage.getString(), "task", null, 0, -1, Discipline.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDiscipline_SubDiscipline(), this.getDiscipline(), null, "subDiscipline", null, 0, -1, Discipline.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDiscipline_ReferenceWorkflow(), theXMLTypePackage.getString(), "referenceWorkflow", null, 0, -1, Discipline.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(disciplineGroupingEClass, DisciplineGrouping.class, "DisciplineGrouping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDisciplineGrouping_Group2(), ecorePackage.getEFeatureMapEntry(), "group2", null, 0, -1, DisciplineGrouping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDisciplineGrouping_Discipline(), theXMLTypePackage.getString(), "discipline", null, 0, -1, DisciplineGrouping.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(documentRootEClass, DocumentRoot.class, "DocumentRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDocumentRoot_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_XMLNSPrefixMap(), ecorePackage.getEStringToStringMapEntry(), null, "xMLNSPrefixMap", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_XSISchemaLocation(), ecorePackage.getEStringToStringMapEntry(), null, "xSISchemaLocation", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_MethodConfiguration(), this.getMethodConfiguration(), null, "methodConfiguration", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_MethodLibrary(), this.getMethodLibrary(), null, "methodLibrary", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_MethodPlugin(), this.getMethodPlugin(), null, "methodPlugin", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(domainEClass, Domain.class, "Domain", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDomain_Group2(), ecorePackage.getEFeatureMapEntry(), "group2", null, 0, -1, Domain.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDomain_WorkProduct(), theXMLTypePackage.getString(), "workProduct", null, 0, -1, Domain.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDomain_Subdomain(), this.getDomain(), null, "subdomain", null, 0, -1, Domain.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(elementEClass, Element.class, "Element", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(estimateEClass, Estimate.class, "Estimate", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEstimate_Group2(), ecorePackage.getEFeatureMapEntry(), "group2", null, 0, -1, Estimate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEstimate_EstimationMetric(), theXMLTypePackage.getString(), "estimationMetric", null, 0, -1, Estimate.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getEstimate_EstimationConsiderations(), theXMLTypePackage.getString(), "estimationConsiderations", null, 0, -1, Estimate.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(estimatingMetricEClass, EstimatingMetric.class, "EstimatingMetric", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(estimationConsiderationsEClass, EstimationConsiderations.class, "EstimationConsiderations", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(exampleEClass, Example.class, "Example", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(guidanceEClass, Guidance.class, "Guidance", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(guidanceDescriptionEClass, GuidanceDescription.class, "GuidanceDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getGuidanceDescription_Attachment(), theXMLTypePackage.getString(), "attachment", null, 0, 1, GuidanceDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(guidelineEClass, Guideline.class, "Guideline", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(iterationEClass, Iteration.class, "Iteration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(kindEClass, Kind.class, "Kind", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getKind_ApplicableMetaClassInfo(), theXMLTypePackage.getString(), "applicableMetaClassInfo", null, 0, -1, Kind.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(methodConfigurationEClass, MethodConfiguration.class, "MethodConfiguration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMethodConfiguration_BaseConfiguration(), theXMLTypePackage.getString(), "baseConfiguration", null, 0, -1, MethodConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMethodConfiguration_MethodPluginSelection(), theXMLTypePackage.getString(), "methodPluginSelection", null, 0, -1, MethodConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMethodConfiguration_MethodPackageSelection(), theXMLTypePackage.getString(), "methodPackageSelection", null, 0, -1, MethodConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMethodConfiguration_DefaultView(), theXMLTypePackage.getString(), "defaultView", null, 0, 1, MethodConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMethodConfiguration_ProcessView(), theXMLTypePackage.getString(), "processView", null, 0, -1, MethodConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMethodConfiguration_SubtractedCategory(), theXMLTypePackage.getString(), "subtractedCategory", null, 0, -1, MethodConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMethodConfiguration_AddedCategory(), theXMLTypePackage.getString(), "addedCategory", null, 0, -1, MethodConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(methodElementEClass, MethodElement.class, "MethodElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMethodElement_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, MethodElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMethodElement_OwnedRule(), this.getConstraint(), null, "ownedRule", null, 0, -1, MethodElement.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getMethodElement_MethodElementProperty(), this.getMethodElementProperty(), null, "methodElementProperty", null, 0, -1, MethodElement.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getMethodElement_BriefDescription(), theXMLTypePackage.getString(), "briefDescription", null, 0, 1, MethodElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMethodElement_Id(), theXMLTypePackage.getString(), "id", null, 0, 1, MethodElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMethodElement_OrderingGuide(), theXMLTypePackage.getString(), "orderingGuide", null, 0, 1, MethodElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMethodElement_PresentationName(), theXMLTypePackage.getString(), "presentationName", null, 0, 1, MethodElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMethodElement_Suppressed(), theXMLTypePackage.getBoolean(), "suppressed", null, 0, 1, MethodElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(methodElementPropertyEClass, MethodElementProperty.class, "MethodElementProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMethodElementProperty_Value(), theXMLTypePackage.getString(), "value", null, 0, 1, MethodElementProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(methodLibraryEClass, MethodLibrary.class, "MethodLibrary", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMethodLibrary_MethodPlugin(), this.getMethodPlugin(), null, "methodPlugin", null, 0, -1, MethodLibrary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMethodLibrary_MethodConfiguration(), this.getMethodConfiguration(), null, "methodConfiguration", null, 0, -1, MethodLibrary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMethodLibrary_Tool(), theXMLTypePackage.getString(), "tool", null, 0, 1, MethodLibrary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(methodPackageEClass, MethodPackage.class, "MethodPackage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMethodPackage_Group1(), ecorePackage.getEFeatureMapEntry(), "group1", null, 0, -1, MethodPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMethodPackage_ReusedPackage(), theXMLTypePackage.getString(), "reusedPackage", null, 0, -1, MethodPackage.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getMethodPackage_MethodPackage(), this.getMethodPackage(), null, "methodPackage", null, 0, -1, MethodPackage.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getMethodPackage_Global(), theXMLTypePackage.getBoolean(), "global", null, 0, 1, MethodPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(methodPluginEClass, MethodPlugin.class, "MethodPlugin", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMethodPlugin_ReferencedMethodPlugin(), theXMLTypePackage.getString(), "referencedMethodPlugin", null, 0, -1, MethodPlugin.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMethodPlugin_MethodPackage(), this.getMethodPackage(), null, "methodPackage", null, 0, -1, MethodPlugin.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMethodPlugin_Supporting(), theXMLTypePackage.getBoolean(), "supporting", null, 0, 1, MethodPlugin.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMethodPlugin_UserChangeable(), theXMLTypePackage.getBoolean(), "userChangeable", null, 0, 1, MethodPlugin.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(methodUnitEClass, MethodUnit.class, "MethodUnit", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMethodUnit_Copyright(), theXMLTypePackage.getString(), "copyright", null, 0, 1, MethodUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMethodUnit_Authors(), theXMLTypePackage.getString(), "authors", null, 0, 1, MethodUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMethodUnit_ChangeDate(), theXMLTypePackage.getDateTime(), "changeDate", null, 0, 1, MethodUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMethodUnit_ChangeDescription(), theXMLTypePackage.getString(), "changeDescription", null, 0, 1, MethodUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMethodUnit_Version(), theXMLTypePackage.getString(), "version", null, 0, 1, MethodUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(milestoneEClass, Milestone.class, "Milestone", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMilestone_RequiredResult(), theXMLTypePackage.getString(), "requiredResult", null, 0, -1, Milestone.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(namedElementEClass, NamedElement.class, "NamedElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNamedElement_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, NamedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(outcomeEClass, Outcome.class, "Outcome", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(packageableElementEClass, PackageableElement.class, "PackageableElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(phaseEClass, Phase.class, "Phase", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(planningDataEClass, PlanningData.class, "PlanningData", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPlanningData_FinishDate(), theXMLTypePackage.getDateTime(), "finishDate", null, 0, 1, PlanningData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPlanningData_Rank(), theXMLTypePackage.getString(), "rank", null, 0, 1, PlanningData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPlanningData_StartDate(), theXMLTypePackage.getDateTime(), "startDate", null, 0, 1, PlanningData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(practiceEClass, Practice.class, "Practice", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPractice_Group2(), ecorePackage.getEFeatureMapEntry(), "group2", null, 0, -1, Practice.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPractice_ActivityReference(), theXMLTypePackage.getString(), "activityReference", null, 0, -1, Practice.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getPractice_ContentReference(), theXMLTypePackage.getString(), "contentReference", null, 0, -1, Practice.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getPractice_SubPractice(), this.getPractice(), null, "subPractice", null, 0, -1, Practice.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(practiceDescriptionEClass, PracticeDescription.class, "PracticeDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPracticeDescription_AdditionalInfo(), theXMLTypePackage.getString(), "additionalInfo", null, 0, 1, PracticeDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPracticeDescription_Application(), theXMLTypePackage.getString(), "application", null, 0, 1, PracticeDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPracticeDescription_Background(), theXMLTypePackage.getString(), "background", null, 0, 1, PracticeDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPracticeDescription_Goals(), theXMLTypePackage.getString(), "goals", null, 0, 1, PracticeDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPracticeDescription_LevelsOfAdoption(), theXMLTypePackage.getString(), "levelsOfAdoption", null, 0, 1, PracticeDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPracticeDescription_Problem(), theXMLTypePackage.getString(), "problem", null, 0, 1, PracticeDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(processEClass, org.eclipse.epf.xml.uma.Process.class, "Process", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getProcess_IncludesPattern(), theXMLTypePackage.getString(), "includesPattern", null, 0, -1, org.eclipse.epf.xml.uma.Process.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProcess_DefaultContext(), theXMLTypePackage.getString(), "defaultContext", null, 0, 1, org.eclipse.epf.xml.uma.Process.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProcess_ValidContext(), theXMLTypePackage.getString(), "validContext", null, 0, -1, org.eclipse.epf.xml.uma.Process.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProcess_DiagramURI(), theXMLTypePackage.getString(), "diagramURI", null, 0, 1, org.eclipse.epf.xml.uma.Process.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(processComponentEClass, ProcessComponent.class, "ProcessComponent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getProcessComponent_Copyright(), theXMLTypePackage.getString(), "copyright", null, 0, 1, ProcessComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProcessComponent_Interface(), this.getProcessComponentInterface(), null, "interface", null, 0, 1, ProcessComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProcessComponent_Process(), this.getProcess(), null, "process", null, 1, 1, ProcessComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProcessComponent_Authors(), theXMLTypePackage.getString(), "authors", null, 0, 1, ProcessComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProcessComponent_ChangeDate(), theXMLTypePackage.getDateTime(), "changeDate", null, 0, 1, ProcessComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProcessComponent_ChangeDescription(), theXMLTypePackage.getString(), "changeDescription", null, 0, 1, ProcessComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProcessComponent_Version(), theXMLTypePackage.getString(), "version", null, 0, 1, ProcessComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(processComponentInterfaceEClass, ProcessComponentInterface.class, "ProcessComponentInterface", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getProcessComponentInterface_Group2(), ecorePackage.getEFeatureMapEntry(), "group2", null, 0, -1, ProcessComponentInterface.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProcessComponentInterface_InterfaceSpecification(), this.getTaskDescriptor(), null, "interfaceSpecification", null, 0, -1, ProcessComponentInterface.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getProcessComponentInterface_InterfaceIO(), this.getWorkProductDescriptor(), null, "interfaceIO", null, 0, -1, ProcessComponentInterface.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(processDescriptionEClass, ProcessDescription.class, "ProcessDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getProcessDescription_Scope(), theXMLTypePackage.getString(), "scope", null, 0, 1, ProcessDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProcessDescription_UsageNotes(), theXMLTypePackage.getString(), "usageNotes", null, 0, 1, ProcessDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(processElementEClass, ProcessElement.class, "ProcessElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(processPackageEClass, ProcessPackage.class, "ProcessPackage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getProcessPackage_Group2(), ecorePackage.getEFeatureMapEntry(), "group2", null, 0, -1, ProcessPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProcessPackage_ProcessElement(), this.getProcessElement(), null, "processElement", null, 0, -1, ProcessPackage.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(processPlanningTemplateEClass, ProcessPlanningTemplate.class, "ProcessPlanningTemplate", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getProcessPlanningTemplate_Group4(), ecorePackage.getEFeatureMapEntry(), "group4", null, 0, -1, ProcessPlanningTemplate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProcessPlanningTemplate_BaseProcess(), theXMLTypePackage.getString(), "baseProcess", null, 0, -1, ProcessPlanningTemplate.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(reportEClass, Report.class, "Report", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(reusableAssetEClass, ReusableAsset.class, "ReusableAsset", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(roadmapEClass, Roadmap.class, "Roadmap", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(roleEClass, Role.class, "Role", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRole_Group2(), ecorePackage.getEFeatureMapEntry(), "group2", null, 0, -1, Role.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRole_ResponsibleFor(), theXMLTypePackage.getString(), "responsibleFor", null, 0, -1, Role.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(roleDescriptionEClass, RoleDescription.class, "RoleDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRoleDescription_AssignmentApproaches(), theXMLTypePackage.getString(), "assignmentApproaches", null, 0, 1, RoleDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRoleDescription_Skills(), theXMLTypePackage.getString(), "skills", null, 0, 1, RoleDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRoleDescription_Synonyms(), theXMLTypePackage.getString(), "synonyms", null, 0, 1, RoleDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(roleDescriptorEClass, RoleDescriptor.class, "RoleDescriptor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRoleDescriptor_Role(), theXMLTypePackage.getString(), "role", null, 0, 1, RoleDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRoleDescriptor_ResponsibleFor(), theXMLTypePackage.getString(), "responsibleFor", null, 0, -1, RoleDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(roleSetEClass, RoleSet.class, "RoleSet", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRoleSet_Group2(), ecorePackage.getEFeatureMapEntry(), "group2", null, 0, -1, RoleSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRoleSet_Role(), theXMLTypePackage.getString(), "role", null, 0, -1, RoleSet.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(roleSetGroupingEClass, RoleSetGrouping.class, "RoleSetGrouping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRoleSetGrouping_Group2(), ecorePackage.getEFeatureMapEntry(), "group2", null, 0, -1, RoleSetGrouping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRoleSetGrouping_RoleSet(), theXMLTypePackage.getString(), "roleSet", null, 0, -1, RoleSetGrouping.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(sectionEClass, Section.class, "Section", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSection_SubSection(), this.getSection(), null, "subSection", null, 0, 1, Section.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSection_Predecessor(), theXMLTypePackage.getString(), "predecessor", null, 0, 1, Section.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSection_Description(), theXMLTypePackage.getString(), "description", null, 0, 1, Section.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSection_SectionName(), theXMLTypePackage.getString(), "sectionName", null, 0, 1, Section.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSection_VariabilityBasedOnElement(), theXMLTypePackage.getString(), "variabilityBasedOnElement", null, 0, 1, Section.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSection_VariabilityType(), this.getVariabilityType(), "variabilityType", "na", 0, 1, Section.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(supportingMaterialEClass, SupportingMaterial.class, "SupportingMaterial", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(taskEClass, Task.class, "Task", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTask_Precondition(), theXMLTypePackage.getString(), "precondition", null, 0, 1, Task.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTask_Postcondition(), theXMLTypePackage.getString(), "postcondition", null, 0, 1, Task.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTask_PerformedBy(), theXMLTypePackage.getString(), "performedBy", null, 0, -1, Task.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTask_Group2(), ecorePackage.getEFeatureMapEntry(), "group2", null, 0, -1, Task.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTask_MandatoryInput(), theXMLTypePackage.getString(), "mandatoryInput", null, 0, -1, Task.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getTask_Output(), theXMLTypePackage.getString(), "output", null, 0, -1, Task.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getTask_AdditionallyPerformedBy(), theXMLTypePackage.getString(), "additionallyPerformedBy", null, 0, -1, Task.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getTask_OptionalInput(), theXMLTypePackage.getString(), "optionalInput", null, 0, -1, Task.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getTask_Estimate(), theXMLTypePackage.getString(), "estimate", null, 0, -1, Task.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getTask_EstimationConsiderations(), theXMLTypePackage.getString(), "estimationConsiderations", null, 0, -1, Task.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getTask_ToolMentor(), theXMLTypePackage.getString(), "toolMentor", null, 0, -1, Task.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(taskDescriptionEClass, TaskDescription.class, "TaskDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTaskDescription_Alternatives(), theXMLTypePackage.getString(), "alternatives", null, 0, 1, TaskDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTaskDescription_Purpose(), theXMLTypePackage.getString(), "purpose", null, 0, 1, TaskDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(taskDescriptorEClass, TaskDescriptor.class, "TaskDescriptor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTaskDescriptor_Task(), theXMLTypePackage.getString(), "task", null, 0, 1, TaskDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTaskDescriptor_Group3(), ecorePackage.getEFeatureMapEntry(), "group3", null, 0, -1, TaskDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTaskDescriptor_PerformedPrimarilyBy(), theXMLTypePackage.getString(), "performedPrimarilyBy", null, 0, -1, TaskDescriptor.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getTaskDescriptor_AdditionallyPerformedBy(), theXMLTypePackage.getString(), "additionallyPerformedBy", null, 0, -1, TaskDescriptor.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getTaskDescriptor_AssistedBy(), theXMLTypePackage.getString(), "assistedBy", null, 0, -1, TaskDescriptor.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getTaskDescriptor_ExternalInput(), theXMLTypePackage.getString(), "externalInput", null, 0, -1, TaskDescriptor.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getTaskDescriptor_MandatoryInput(), theXMLTypePackage.getString(), "mandatoryInput", null, 0, -1, TaskDescriptor.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getTaskDescriptor_OptionalInput(), theXMLTypePackage.getString(), "optionalInput", null, 0, -1, TaskDescriptor.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getTaskDescriptor_Output(), theXMLTypePackage.getString(), "output", null, 0, -1, TaskDescriptor.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getTaskDescriptor_Step(), this.getSection(), null, "step", null, 0, -1, TaskDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTaskDescriptor_IsSynchronizedWithSource(), theXMLTypePackage.getBoolean(), "isSynchronizedWithSource", null, 0, 1, TaskDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(teamProfileEClass, TeamProfile.class, "TeamProfile", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTeamProfile_Group2(), ecorePackage.getEFeatureMapEntry(), "group2", null, 0, -1, TeamProfile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTeamProfile_Role(), theXMLTypePackage.getString(), "role", null, 0, -1, TeamProfile.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getTeamProfile_SuperTeam(), theXMLTypePackage.getString(), "superTeam", null, 0, -1, TeamProfile.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getTeamProfile_SubTeam(), theXMLTypePackage.getString(), "subTeam", null, 0, -1, TeamProfile.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(templateEClass, Template.class, "Template", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(termDefinitionEClass, TermDefinition.class, "TermDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(toolEClass, Tool.class, "Tool", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTool_Group2(), ecorePackage.getEFeatureMapEntry(), "group2", null, 0, -1, Tool.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTool_ToolMentor(), theXMLTypePackage.getString(), "toolMentor", null, 0, -1, Tool.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(toolMentorEClass, ToolMentor.class, "ToolMentor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(whitepaperEClass, Whitepaper.class, "Whitepaper", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(workBreakdownElementEClass, WorkBreakdownElement.class, "WorkBreakdownElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getWorkBreakdownElement_Group2(), ecorePackage.getEFeatureMapEntry(), "group2", null, 0, -1, WorkBreakdownElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getWorkBreakdownElement_Predecessor(), this.getWorkOrder(), null, "predecessor", null, 0, -1, WorkBreakdownElement.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkBreakdownElement_IsEventDriven(), theXMLTypePackage.getBoolean(), "isEventDriven", null, 0, 1, WorkBreakdownElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkBreakdownElement_IsOngoing(), theXMLTypePackage.getBoolean(), "isOngoing", null, 0, 1, WorkBreakdownElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkBreakdownElement_IsRepeatable(), theXMLTypePackage.getBoolean(), "isRepeatable", null, 0, 1, WorkBreakdownElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(workDefinitionEClass, WorkDefinition.class, "WorkDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getWorkDefinition_Precondition(), theXMLTypePackage.getString(), "precondition", null, 0, 1, WorkDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkDefinition_Postcondition(), theXMLTypePackage.getString(), "postcondition", null, 0, 1, WorkDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(workOrderEClass, WorkOrder.class, "WorkOrder", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getWorkOrder_Value(), theXMLTypePackage.getString(), "value", null, 0, 1, WorkOrder.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkOrder_Id(), theXMLTypePackage.getString(), "id", null, 0, 1, WorkOrder.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkOrder_LinkType(), this.getWorkOrderType(), "linkType", "finishToStart", 0, 1, WorkOrder.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkOrder_Properties(), theXMLTypePackage.getString(), "properties", null, 0, 1, WorkOrder.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(workProductEClass, WorkProduct.class, "WorkProduct", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getWorkProduct_Group2(), ecorePackage.getEFeatureMapEntry(), "group2", null, 0, -1, WorkProduct.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkProduct_Estimate(), theXMLTypePackage.getString(), "estimate", null, 0, -1, WorkProduct.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkProduct_EstimationConsiderations(), theXMLTypePackage.getString(), "estimationConsiderations", null, 0, -1, WorkProduct.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkProduct_Report(), theXMLTypePackage.getString(), "report", null, 0, -1, WorkProduct.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkProduct_Template(), theXMLTypePackage.getString(), "template", null, 0, -1, WorkProduct.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkProduct_ToolMentor(), theXMLTypePackage.getString(), "toolMentor", null, 0, -1, WorkProduct.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(workProductDescriptionEClass, WorkProductDescription.class, "WorkProductDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getWorkProductDescription_ImpactOfNotHaving(), theXMLTypePackage.getString(), "impactOfNotHaving", null, 0, 1, WorkProductDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkProductDescription_Purpose(), theXMLTypePackage.getString(), "purpose", null, 0, 1, WorkProductDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkProductDescription_ReasonsForNotNeeding(), theXMLTypePackage.getString(), "reasonsForNotNeeding", null, 0, 1, WorkProductDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(workProductDescriptorEClass, WorkProductDescriptor.class, "WorkProductDescriptor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getWorkProductDescriptor_WorkProduct(), theXMLTypePackage.getString(), "workProduct", null, 0, 1, WorkProductDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkProductDescriptor_ResponsibleRole(), theXMLTypePackage.getString(), "responsibleRole", null, 0, 1, WorkProductDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkProductDescriptor_Group2(), ecorePackage.getEFeatureMapEntry(), "group2", null, 0, -1, WorkProductDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkProductDescriptor_ExternalInputTo(), theXMLTypePackage.getString(), "externalInputTo", null, 0, -1, WorkProductDescriptor.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkProductDescriptor_ImpactedBy(), theXMLTypePackage.getString(), "impactedBy", null, 0, -1, WorkProductDescriptor.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkProductDescriptor_Impacts(), theXMLTypePackage.getString(), "impacts", null, 0, -1, WorkProductDescriptor.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkProductDescriptor_MandatoryInputTo(), theXMLTypePackage.getString(), "mandatoryInputTo", null, 0, -1, WorkProductDescriptor.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkProductDescriptor_OptionalInputTo(), theXMLTypePackage.getString(), "optionalInputTo", null, 0, -1, WorkProductDescriptor.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkProductDescriptor_OutputFrom(), theXMLTypePackage.getString(), "outputFrom", null, 0, -1, WorkProductDescriptor.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkProductDescriptor_DeliverableParts(), theXMLTypePackage.getString(), "deliverableParts", null, 0, -1, WorkProductDescriptor.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkProductDescriptor_ActivityEntryState(), theXMLTypePackage.getString(), "activityEntryState", null, 0, 1, WorkProductDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkProductDescriptor_ActivityExitState(), theXMLTypePackage.getString(), "activityExitState", null, 0, 1, WorkProductDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(workProductTypeEClass, WorkProductType.class, "WorkProductType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getWorkProductType_Group2(), ecorePackage.getEFeatureMapEntry(), "group2", null, 0, -1, WorkProductType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkProductType_WorkProduct(), theXMLTypePackage.getString(), "workProduct", null, 0, -1, WorkProductType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(variabilityTypeEEnum, VariabilityType.class, "VariabilityType");
		addEEnumLiteral(variabilityTypeEEnum, VariabilityType.NA);
		addEEnumLiteral(variabilityTypeEEnum, VariabilityType.CONTRIBUTES);
		addEEnumLiteral(variabilityTypeEEnum, VariabilityType.EXTENDS);
		addEEnumLiteral(variabilityTypeEEnum, VariabilityType.REPLACES);
		addEEnumLiteral(variabilityTypeEEnum, VariabilityType.LOCAL_CONTRIBUTION);
		addEEnumLiteral(variabilityTypeEEnum, VariabilityType.LOCAL_REPLACEMENT);
		addEEnumLiteral(variabilityTypeEEnum, VariabilityType.EXTENDS_REPLACES);

		initEEnum(workOrderTypeEEnum, WorkOrderType.class, "WorkOrderType");
		addEEnumLiteral(workOrderTypeEEnum, WorkOrderType.FINISH_TO_START);
		addEEnumLiteral(workOrderTypeEEnum, WorkOrderType.FINISH_TO_FINISH);
		addEEnumLiteral(workOrderTypeEEnum, WorkOrderType.START_TO_START);
		addEEnumLiteral(workOrderTypeEEnum, WorkOrderType.START_TO_FINISH);

		// Initialize data types
		initEDataType(variabilityTypeObjectEDataType, VariabilityType.class, "VariabilityTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
		initEDataType(workOrderTypeObjectEDataType, WorkOrderType.class, "WorkOrderTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http:///org/eclipse/emf/ecore/util/ExtendedMetaData
		createExtendedMetaDataAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createExtendedMetaDataAnnotations() {
		String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";			
		addAnnotation
		  (activityEClass, 
		   source, 
		   new String[] {
			 "name", "Activity",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getActivity_Precondition(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Precondition"
		   });		
		addAnnotation
		  (getActivity_Postcondition(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Postcondition"
		   });		
		addAnnotation
		  (getActivity_Group3(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:37"
		   });		
		addAnnotation
		  (getActivity_BreakdownElement(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "BreakdownElement",
			 "group", "#group:37"
		   });		
		addAnnotation
		  (getActivity_Roadmap(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Roadmap",
			 "group", "#group:37"
		   });		
		addAnnotation
		  (getActivity_IsEnactable(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "IsEnactable"
		   });		
		addAnnotation
		  (getActivity_VariabilityBasedOnElement(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "variabilityBasedOnElement"
		   });		
		addAnnotation
		  (getActivity_VariabilityType(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "variabilityType"
		   });			
		addAnnotation
		  (activityDescriptionEClass, 
		   source, 
		   new String[] {
			 "name", "ActivityDescription",
			 "kind", "elementOnly"
		   });			
		addAnnotation
		  (getActivityDescription_Alternatives(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Alternatives"
		   });			
		addAnnotation
		  (getActivityDescription_HowToStaff(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "HowToStaff"
		   });			
		addAnnotation
		  (getActivityDescription_Purpose(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Purpose"
		   });		
		addAnnotation
		  (applicableMetaClassInfoEClass, 
		   source, 
		   new String[] {
			 "name", "ApplicableMetaClassInfo",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getApplicableMetaClassInfo_IsPrimaryExtension(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "isPrimaryExtension"
		   });			
		addAnnotation
		  (artifactEClass, 
		   source, 
		   new String[] {
			 "name", "Artifact",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getArtifact_Group3(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:30"
		   });		
		addAnnotation
		  (getArtifact_ContainedArtifact(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ContainedArtifact",
			 "group", "#group:30"
		   });			
		addAnnotation
		  (artifactDescriptionEClass, 
		   source, 
		   new String[] {
			 "name", "ArtifactDescription",
			 "kind", "elementOnly"
		   });			
		addAnnotation
		  (getArtifactDescription_BriefOutline(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "BriefOutline"
		   });			
		addAnnotation
		  (getArtifactDescription_RepresentationOptions(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "RepresentationOptions"
		   });		
		addAnnotation
		  (getArtifactDescription_Representation(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Representation"
		   });		
		addAnnotation
		  (getArtifactDescription_Notation(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Notation"
		   });			
		addAnnotation
		  (breakdownElementEClass, 
		   source, 
		   new String[] {
			 "name", "BreakdownElement",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getBreakdownElement_PresentedAfter(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "PresentedAfter"
		   });		
		addAnnotation
		  (getBreakdownElement_PresentedBefore(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "PresentedBefore"
		   });		
		addAnnotation
		  (getBreakdownElement_PlanningData(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "PlanningData"
		   });		
		addAnnotation
		  (getBreakdownElement_SuperActivity(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "SuperActivity"
		   });		
		addAnnotation
		  (getBreakdownElement_Group1(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:18"
		   });		
		addAnnotation
		  (getBreakdownElement_Checklist(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Checklist",
			 "group", "#group:18"
		   });		
		addAnnotation
		  (getBreakdownElement_Concept(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Concept",
			 "group", "#group:18"
		   });		
		addAnnotation
		  (getBreakdownElement_Example(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Example",
			 "group", "#group:18"
		   });		
		addAnnotation
		  (getBreakdownElement_Guideline(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Guideline",
			 "group", "#group:18"
		   });		
		addAnnotation
		  (getBreakdownElement_ReusableAsset(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ReusableAsset",
			 "group", "#group:18"
		   });		
		addAnnotation
		  (getBreakdownElement_SupportingMaterial(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "SupportingMaterial",
			 "group", "#group:18"
		   });		
		addAnnotation
		  (getBreakdownElement_Whitepaper(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Whitepaper",
			 "group", "#group:18"
		   });			
		addAnnotation
		  (getBreakdownElement_HasMultipleOccurrences(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "hasMultipleOccurrences"
		   });			
		addAnnotation
		  (getBreakdownElement_IsOptional(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "isOptional"
		   });			
		addAnnotation
		  (getBreakdownElement_IsPlanned(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "isPlanned"
		   });			
		addAnnotation
		  (getBreakdownElement_Prefix(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "prefix"
		   });			
		addAnnotation
		  (breakdownElementDescriptionEClass, 
		   source, 
		   new String[] {
			 "name", "BreakdownElementDescription",
			 "kind", "elementOnly"
		   });			
		addAnnotation
		  (getBreakdownElementDescription_UsageGuidance(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "usageGuidance"
		   });			
		addAnnotation
		  (capabilityPatternEClass, 
		   source, 
		   new String[] {
			 "name", "CapabilityPattern",
			 "kind", "elementOnly"
		   });			
		addAnnotation
		  (checklistEClass, 
		   source, 
		   new String[] {
			 "name", "Checklist",
			 "kind", "elementOnly"
		   });			
		addAnnotation
		  (compositeRoleEClass, 
		   source, 
		   new String[] {
			 "name", "CompositeRole",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getCompositeRole_Group2(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:33"
		   });		
		addAnnotation
		  (getCompositeRole_AggregatedRole(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "AggregatedRole",
			 "group", "#group:33"
		   });			
		addAnnotation
		  (conceptEClass, 
		   source, 
		   new String[] {
			 "name", "Concept",
			 "kind", "elementOnly"
		   });			
		addAnnotation
		  (constraintEClass, 
		   source, 
		   new String[] {
			 "name", "Constraint",
			 "kind", "elementOnly"
		   });			
		addAnnotation
		  (getConstraint_MainDescription(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "mainDescription"
		   });			
		addAnnotation
		  (contentCategoryEClass, 
		   source, 
		   new String[] {
			 "name", "ContentCategory",
			 "kind", "elementOnly"
		   });			
		addAnnotation
		  (contentCategoryPackageEClass, 
		   source, 
		   new String[] {
			 "name", "ContentCategoryPackage",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getContentCategoryPackage_Group2(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:13"
		   });		
		addAnnotation
		  (getContentCategoryPackage_ContentCategory(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ContentCategory",
			 "group", "#group:13"
		   });			
		addAnnotation
		  (contentDescriptionEClass, 
		   source, 
		   new String[] {
			 "name", "ContentDescription",
			 "kind", "elementOnly"
		   });			
		addAnnotation
		  (getContentDescription_MainDescription(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "MainDescription"
		   });			
		addAnnotation
		  (getContentDescription_KeyConsiderations(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "KeyConsiderations"
		   });		
		addAnnotation
		  (getContentDescription_Section(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Section"
		   });			
		addAnnotation
		  (getContentDescription_ExternalId(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "externalId"
		   });			
		addAnnotation
		  (contentElementEClass, 
		   source, 
		   new String[] {
			 "name", "ContentElement",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getContentElement_Group1(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:14"
		   });		
		addAnnotation
		  (getContentElement_Checklist(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Checklist",
			 "group", "#group:14"
		   });		
		addAnnotation
		  (getContentElement_Concept(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Concept",
			 "group", "#group:14"
		   });		
		addAnnotation
		  (getContentElement_Example(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Example",
			 "group", "#group:14"
		   });		
		addAnnotation
		  (getContentElement_Guideline(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Guideline",
			 "group", "#group:14"
		   });		
		addAnnotation
		  (getContentElement_ReusableAsset(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ReusableAsset",
			 "group", "#group:14"
		   });		
		addAnnotation
		  (getContentElement_SupportingMaterial(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "SupportingMaterial",
			 "group", "#group:14"
		   });		
		addAnnotation
		  (getContentElement_Whitepaper(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Whitepaper",
			 "group", "#group:14"
		   });		
		addAnnotation
		  (getContentElement_VariabilityBasedOnElement(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "variabilityBasedOnElement"
		   });		
		addAnnotation
		  (getContentElement_VariabilityType(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "variabilityType"
		   });			
		addAnnotation
		  (contentPackageEClass, 
		   source, 
		   new String[] {
			 "name", "ContentPackage",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getContentPackage_Group2(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:13"
		   });		
		addAnnotation
		  (getContentPackage_ContentElement(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ContentElement",
			 "group", "#group:13"
		   });			
		addAnnotation
		  (customCategoryEClass, 
		   source, 
		   new String[] {
			 "name", "CustomCategory",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getCustomCategory_Group2(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:24"
		   });		
		addAnnotation
		  (getCustomCategory_CategorizedElement(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "CategorizedElement",
			 "group", "#group:24"
		   });		
		addAnnotation
		  (getCustomCategory_SubCategory(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "SubCategory",
			 "group", "#group:24"
		   });			
		addAnnotation
		  (deliverableEClass, 
		   source, 
		   new String[] {
			 "name", "Deliverable",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getDeliverable_Group3(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:30"
		   });		
		addAnnotation
		  (getDeliverable_DeliveredWorkProduct(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "DeliveredWorkProduct",
			 "group", "#group:30"
		   });			
		addAnnotation
		  (deliverableDescriptionEClass, 
		   source, 
		   new String[] {
			 "name", "DeliverableDescription",
			 "kind", "elementOnly"
		   });			
		addAnnotation
		  (getDeliverableDescription_ExternalDescription(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ExternalDescription"
		   });			
		addAnnotation
		  (getDeliverableDescription_PackagingGuidance(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "PackagingGuidance"
		   });			
		addAnnotation
		  (deliveryProcessEClass, 
		   source, 
		   new String[] {
			 "name", "DeliveryProcess",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getDeliveryProcess_Group4(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:47"
		   });		
		addAnnotation
		  (getDeliveryProcess_CommunicationsMaterial(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "CommunicationsMaterial",
			 "group", "#group:47"
		   });		
		addAnnotation
		  (getDeliveryProcess_EducationMaterial(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "EducationMaterial",
			 "group", "#group:47"
		   });			
		addAnnotation
		  (deliveryProcessDescriptionEClass, 
		   source, 
		   new String[] {
			 "name", "DeliveryProcessDescription",
			 "kind", "elementOnly"
		   });			
		addAnnotation
		  (getDeliveryProcessDescription_Scale(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Scale"
		   });			
		addAnnotation
		  (getDeliveryProcessDescription_ProjectCharacteristics(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ProjectCharacteristics"
		   });			
		addAnnotation
		  (getDeliveryProcessDescription_RiskLevel(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "RiskLevel"
		   });			
		addAnnotation
		  (getDeliveryProcessDescription_EstimatingTechnique(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "EstimatingTechnique"
		   });			
		addAnnotation
		  (getDeliveryProcessDescription_ProjectMemberExpertise(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ProjectMemberExpertise"
		   });			
		addAnnotation
		  (getDeliveryProcessDescription_TypeOfContract(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "TypeOfContract"
		   });			
		addAnnotation
		  (describableElementEClass, 
		   source, 
		   new String[] {
			 "name", "DescribableElement",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getDescribableElement_Presentation(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Presentation"
		   });		
		addAnnotation
		  (getDescribableElement_Fulfill(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Fulfill"
		   });		
		addAnnotation
		  (getDescribableElement_IsAbstract(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "isAbstract"
		   });			
		addAnnotation
		  (getDescribableElement_Nodeicon(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "nodeicon"
		   });			
		addAnnotation
		  (getDescribableElement_Shapeicon(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "shapeicon"
		   });			
		addAnnotation
		  (descriptorEClass, 
		   source, 
		   new String[] {
			 "name", "Descriptor",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getDescriptor_IsSynchronizedWithSource(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "isSynchronizedWithSource"
		   });			
		addAnnotation
		  (descriptorDescriptionEClass, 
		   source, 
		   new String[] {
			 "name", "DescriptorDescription",
			 "kind", "elementOnly"
		   });			
		addAnnotation
		  (getDescriptorDescription_RefinedDescription(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "RefinedDescription"
		   });			
		addAnnotation
		  (disciplineEClass, 
		   source, 
		   new String[] {
			 "name", "Discipline",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getDiscipline_Group2(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:24"
		   });		
		addAnnotation
		  (getDiscipline_Task(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Task",
			 "group", "#group:24"
		   });		
		addAnnotation
		  (getDiscipline_SubDiscipline(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "SubDiscipline",
			 "group", "#group:24"
		   });		
		addAnnotation
		  (getDiscipline_ReferenceWorkflow(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ReferenceWorkflow",
			 "group", "#group:24"
		   });			
		addAnnotation
		  (disciplineGroupingEClass, 
		   source, 
		   new String[] {
			 "name", "DisciplineGrouping",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getDisciplineGrouping_Group2(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:24"
		   });		
		addAnnotation
		  (getDisciplineGrouping_Discipline(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Discipline",
			 "group", "#group:24"
		   });		
		addAnnotation
		  (documentRootEClass, 
		   source, 
		   new String[] {
			 "name", "",
			 "kind", "mixed"
		   });		
		addAnnotation
		  (getDocumentRoot_Mixed(), 
		   source, 
		   new String[] {
			 "kind", "elementWildcard",
			 "name", ":mixed"
		   });		
		addAnnotation
		  (getDocumentRoot_XMLNSPrefixMap(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "xmlns:prefix"
		   });		
		addAnnotation
		  (getDocumentRoot_XSISchemaLocation(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "xsi:schemaLocation"
		   });			
		addAnnotation
		  (getDocumentRoot_MethodConfiguration(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "MethodConfiguration",
			 "namespace", "##targetNamespace"
		   });			
		addAnnotation
		  (getDocumentRoot_MethodLibrary(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "MethodLibrary",
			 "namespace", "##targetNamespace"
		   });			
		addAnnotation
		  (getDocumentRoot_MethodPlugin(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "MethodPlugin",
			 "namespace", "##targetNamespace"
		   });			
		addAnnotation
		  (domainEClass, 
		   source, 
		   new String[] {
			 "name", "Domain",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getDomain_Group2(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:24"
		   });		
		addAnnotation
		  (getDomain_WorkProduct(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "WorkProduct",
			 "group", "#group:24"
		   });		
		addAnnotation
		  (getDomain_Subdomain(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Subdomain",
			 "group", "#group:24"
		   });			
		addAnnotation
		  (elementEClass, 
		   source, 
		   new String[] {
			 "name", "Element",
			 "kind", "empty"
		   });			
		addAnnotation
		  (estimateEClass, 
		   source, 
		   new String[] {
			 "name", "Estimate",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getEstimate_Group2(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:24"
		   });		
		addAnnotation
		  (getEstimate_EstimationMetric(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "EstimationMetric",
			 "group", "#group:24"
		   });		
		addAnnotation
		  (getEstimate_EstimationConsiderations(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "EstimationConsiderations",
			 "group", "#group:24"
		   });			
		addAnnotation
		  (estimatingMetricEClass, 
		   source, 
		   new String[] {
			 "name", "EstimatingMetric",
			 "kind", "elementOnly"
		   });			
		addAnnotation
		  (estimationConsiderationsEClass, 
		   source, 
		   new String[] {
			 "name", "EstimationConsiderations",
			 "kind", "elementOnly"
		   });			
		addAnnotation
		  (exampleEClass, 
		   source, 
		   new String[] {
			 "name", "Example",
			 "kind", "elementOnly"
		   });			
		addAnnotation
		  (guidanceEClass, 
		   source, 
		   new String[] {
			 "name", "Guidance",
			 "kind", "elementOnly"
		   });			
		addAnnotation
		  (guidanceDescriptionEClass, 
		   source, 
		   new String[] {
			 "name", "GuidanceDescription",
			 "kind", "elementOnly"
		   });			
		addAnnotation
		  (getGuidanceDescription_Attachment(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Attachment"
		   });			
		addAnnotation
		  (guidelineEClass, 
		   source, 
		   new String[] {
			 "name", "Guideline",
			 "kind", "elementOnly"
		   });			
		addAnnotation
		  (iterationEClass, 
		   source, 
		   new String[] {
			 "name", "Iteration",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (kindEClass, 
		   source, 
		   new String[] {
			 "name", "Kind",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getKind_ApplicableMetaClassInfo(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ApplicableMetaClassInfo"
		   });			
		addAnnotation
		  (methodConfigurationEClass, 
		   source, 
		   new String[] {
			 "name", "MethodConfiguration",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getMethodConfiguration_BaseConfiguration(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "BaseConfiguration"
		   });		
		addAnnotation
		  (getMethodConfiguration_MethodPluginSelection(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "MethodPluginSelection"
		   });		
		addAnnotation
		  (getMethodConfiguration_MethodPackageSelection(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "MethodPackageSelection"
		   });		
		addAnnotation
		  (getMethodConfiguration_DefaultView(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "DefaultView"
		   });		
		addAnnotation
		  (getMethodConfiguration_ProcessView(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ProcessView"
		   });		
		addAnnotation
		  (getMethodConfiguration_SubtractedCategory(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "SubtractedCategory"
		   });		
		addAnnotation
		  (getMethodConfiguration_AddedCategory(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "AddedCategory"
		   });			
		addAnnotation
		  (methodElementEClass, 
		   source, 
		   new String[] {
			 "name", "MethodElement",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getMethodElement_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:1"
		   });			
		addAnnotation
		  (getMethodElement_OwnedRule(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "OwnedRule",
			 "group", "#group:1"
		   });		
		addAnnotation
		  (getMethodElement_MethodElementProperty(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "MethodElementProperty",
			 "group", "#group:1"
		   });			
		addAnnotation
		  (getMethodElement_BriefDescription(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "briefDescription"
		   });			
		addAnnotation
		  (getMethodElement_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "id"
		   });			
		addAnnotation
		  (getMethodElement_OrderingGuide(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "orderingGuide"
		   });			
		addAnnotation
		  (getMethodElement_PresentationName(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "presentationName"
		   });			
		addAnnotation
		  (getMethodElement_Suppressed(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "suppressed"
		   });		
		addAnnotation
		  (methodElementPropertyEClass, 
		   source, 
		   new String[] {
			 "name", "MethodElementProperty",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getMethodElementProperty_Value(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "value"
		   });			
		addAnnotation
		  (methodLibraryEClass, 
		   source, 
		   new String[] {
			 "name", "MethodLibrary",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getMethodLibrary_MethodPlugin(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "MethodPlugin"
		   });		
		addAnnotation
		  (getMethodLibrary_MethodConfiguration(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "MethodConfiguration"
		   });			
		addAnnotation
		  (getMethodLibrary_Tool(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "tool"
		   });			
		addAnnotation
		  (methodPackageEClass, 
		   source, 
		   new String[] {
			 "name", "MethodPackage",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getMethodPackage_Group1(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:9"
		   });		
		addAnnotation
		  (getMethodPackage_ReusedPackage(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ReusedPackage",
			 "group", "#group:9"
		   });		
		addAnnotation
		  (getMethodPackage_MethodPackage(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "MethodPackage",
			 "group", "#group:9"
		   });			
		addAnnotation
		  (getMethodPackage_Global(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "global"
		   });			
		addAnnotation
		  (methodPluginEClass, 
		   source, 
		   new String[] {
			 "name", "MethodPlugin",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getMethodPlugin_ReferencedMethodPlugin(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ReferencedMethodPlugin"
		   });		
		addAnnotation
		  (getMethodPlugin_MethodPackage(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "MethodPackage"
		   });		
		addAnnotation
		  (getMethodPlugin_Supporting(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "supporting"
		   });		
		addAnnotation
		  (getMethodPlugin_UserChangeable(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "userChangeable"
		   });			
		addAnnotation
		  (methodUnitEClass, 
		   source, 
		   new String[] {
			 "name", "MethodUnit",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getMethodUnit_Copyright(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Copyright"
		   });			
		addAnnotation
		  (getMethodUnit_Authors(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "authors"
		   });			
		addAnnotation
		  (getMethodUnit_ChangeDate(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "changeDate"
		   });			
		addAnnotation
		  (getMethodUnit_ChangeDescription(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "changeDescription"
		   });			
		addAnnotation
		  (getMethodUnit_Version(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "version"
		   });			
		addAnnotation
		  (milestoneEClass, 
		   source, 
		   new String[] {
			 "name", "Milestone",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getMilestone_RequiredResult(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "RequiredResult"
		   });			
		addAnnotation
		  (namedElementEClass, 
		   source, 
		   new String[] {
			 "name", "NamedElement",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getNamedElement_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });			
		addAnnotation
		  (outcomeEClass, 
		   source, 
		   new String[] {
			 "name", "Outcome",
			 "kind", "elementOnly"
		   });			
		addAnnotation
		  (packageableElementEClass, 
		   source, 
		   new String[] {
			 "name", "PackageableElement",
			 "kind", "empty"
		   });			
		addAnnotation
		  (phaseEClass, 
		   source, 
		   new String[] {
			 "name", "Phase",
			 "kind", "elementOnly"
		   });			
		addAnnotation
		  (planningDataEClass, 
		   source, 
		   new String[] {
			 "name", "PlanningData",
			 "kind", "elementOnly"
		   });			
		addAnnotation
		  (getPlanningData_FinishDate(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "finishDate"
		   });			
		addAnnotation
		  (getPlanningData_Rank(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "rank"
		   });			
		addAnnotation
		  (getPlanningData_StartDate(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "startDate"
		   });			
		addAnnotation
		  (practiceEClass, 
		   source, 
		   new String[] {
			 "name", "Practice",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getPractice_Group2(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:24"
		   });		
		addAnnotation
		  (getPractice_ActivityReference(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ActivityReference",
			 "group", "#group:24"
		   });		
		addAnnotation
		  (getPractice_ContentReference(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ContentReference",
			 "group", "#group:24"
		   });		
		addAnnotation
		  (getPractice_SubPractice(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "SubPractice",
			 "group", "#group:24"
		   });			
		addAnnotation
		  (practiceDescriptionEClass, 
		   source, 
		   new String[] {
			 "name", "PracticeDescription",
			 "kind", "elementOnly"
		   });			
		addAnnotation
		  (getPracticeDescription_AdditionalInfo(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "AdditionalInfo"
		   });			
		addAnnotation
		  (getPracticeDescription_Application(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Application"
		   });			
		addAnnotation
		  (getPracticeDescription_Background(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Background"
		   });			
		addAnnotation
		  (getPracticeDescription_Goals(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Goals"
		   });			
		addAnnotation
		  (getPracticeDescription_LevelsOfAdoption(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "LevelsOfAdoption"
		   });			
		addAnnotation
		  (getPracticeDescription_Problem(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Problem"
		   });			
		addAnnotation
		  (processEClass, 
		   source, 
		   new String[] {
			 "name", "Process",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getProcess_IncludesPattern(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "IncludesPattern"
		   });		
		addAnnotation
		  (getProcess_DefaultContext(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "DefaultContext"
		   });		
		addAnnotation
		  (getProcess_ValidContext(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ValidContext"
		   });			
		addAnnotation
		  (getProcess_DiagramURI(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "diagramURI"
		   });			
		addAnnotation
		  (processComponentEClass, 
		   source, 
		   new String[] {
			 "name", "ProcessComponent",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getProcessComponent_Copyright(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Copyright"
		   });		
		addAnnotation
		  (getProcessComponent_Interface(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Interface"
		   });		
		addAnnotation
		  (getProcessComponent_Process(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Process"
		   });			
		addAnnotation
		  (getProcessComponent_Authors(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "authors"
		   });			
		addAnnotation
		  (getProcessComponent_ChangeDate(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "changeDate"
		   });			
		addAnnotation
		  (getProcessComponent_ChangeDescription(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "changeDescription"
		   });			
		addAnnotation
		  (getProcessComponent_Version(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "version"
		   });			
		addAnnotation
		  (processComponentInterfaceEClass, 
		   source, 
		   new String[] {
			 "name", "ProcessComponentInterface",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getProcessComponentInterface_Group2(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:30"
		   });		
		addAnnotation
		  (getProcessComponentInterface_InterfaceSpecification(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "InterfaceSpecification",
			 "group", "#group:30"
		   });		
		addAnnotation
		  (getProcessComponentInterface_InterfaceIO(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "InterfaceIO",
			 "group", "#group:30"
		   });			
		addAnnotation
		  (processDescriptionEClass, 
		   source, 
		   new String[] {
			 "name", "ProcessDescription",
			 "kind", "elementOnly"
		   });			
		addAnnotation
		  (getProcessDescription_Scope(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Scope"
		   });			
		addAnnotation
		  (getProcessDescription_UsageNotes(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "UsageNotes"
		   });			
		addAnnotation
		  (processElementEClass, 
		   source, 
		   new String[] {
			 "name", "ProcessElement",
			 "kind", "elementOnly"
		   });			
		addAnnotation
		  (processPackageEClass, 
		   source, 
		   new String[] {
			 "name", "ProcessPackage",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getProcessPackage_Group2(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:13"
		   });		
		addAnnotation
		  (getProcessPackage_ProcessElement(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ProcessElement",
			 "group", "#group:13"
		   });			
		addAnnotation
		  (processPlanningTemplateEClass, 
		   source, 
		   new String[] {
			 "name", "ProcessPlanningTemplate",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getProcessPlanningTemplate_Group4(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:47"
		   });		
		addAnnotation
		  (getProcessPlanningTemplate_BaseProcess(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "BaseProcess",
			 "group", "#group:47"
		   });			
		addAnnotation
		  (reportEClass, 
		   source, 
		   new String[] {
			 "name", "Report",
			 "kind", "elementOnly"
		   });			
		addAnnotation
		  (reusableAssetEClass, 
		   source, 
		   new String[] {
			 "name", "ReusableAsset",
			 "kind", "elementOnly"
		   });			
		addAnnotation
		  (roadmapEClass, 
		   source, 
		   new String[] {
			 "name", "Roadmap",
			 "kind", "elementOnly"
		   });			
		addAnnotation
		  (roleEClass, 
		   source, 
		   new String[] {
			 "name", "Role",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getRole_Group2(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:24"
		   });		
		addAnnotation
		  (getRole_ResponsibleFor(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ResponsibleFor",
			 "group", "#group:24"
		   });			
		addAnnotation
		  (roleDescriptionEClass, 
		   source, 
		   new String[] {
			 "name", "RoleDescription",
			 "kind", "elementOnly"
		   });			
		addAnnotation
		  (getRoleDescription_AssignmentApproaches(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "AssignmentApproaches"
		   });			
		addAnnotation
		  (getRoleDescription_Skills(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Skills"
		   });			
		addAnnotation
		  (getRoleDescription_Synonyms(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Synonyms"
		   });			
		addAnnotation
		  (roleDescriptorEClass, 
		   source, 
		   new String[] {
			 "name", "RoleDescriptor",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getRoleDescriptor_Role(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Role"
		   });		
		addAnnotation
		  (getRoleDescriptor_ResponsibleFor(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ResponsibleFor"
		   });			
		addAnnotation
		  (roleSetEClass, 
		   source, 
		   new String[] {
			 "name", "RoleSet",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getRoleSet_Group2(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:24"
		   });		
		addAnnotation
		  (getRoleSet_Role(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Role",
			 "group", "#group:24"
		   });			
		addAnnotation
		  (roleSetGroupingEClass, 
		   source, 
		   new String[] {
			 "name", "RoleSetGrouping",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getRoleSetGrouping_Group2(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:24"
		   });		
		addAnnotation
		  (getRoleSetGrouping_RoleSet(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "RoleSet",
			 "group", "#group:24"
		   });			
		addAnnotation
		  (sectionEClass, 
		   source, 
		   new String[] {
			 "name", "Section",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getSection_SubSection(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "SubSection"
		   });		
		addAnnotation
		  (getSection_Predecessor(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Predecessor"
		   });			
		addAnnotation
		  (getSection_Description(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Description"
		   });			
		addAnnotation
		  (getSection_SectionName(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "sectionName"
		   });		
		addAnnotation
		  (getSection_VariabilityBasedOnElement(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "variabilityBasedOnElement"
		   });		
		addAnnotation
		  (getSection_VariabilityType(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "variabilityType"
		   });			
		addAnnotation
		  (supportingMaterialEClass, 
		   source, 
		   new String[] {
			 "name", "SupportingMaterial",
			 "kind", "elementOnly"
		   });			
		addAnnotation
		  (taskEClass, 
		   source, 
		   new String[] {
			 "name", "Task",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getTask_Precondition(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Precondition"
		   });		
		addAnnotation
		  (getTask_Postcondition(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Postcondition"
		   });		
		addAnnotation
		  (getTask_PerformedBy(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "PerformedBy"
		   });		
		addAnnotation
		  (getTask_Group2(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:27"
		   });		
		addAnnotation
		  (getTask_MandatoryInput(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "MandatoryInput",
			 "group", "#group:27"
		   });		
		addAnnotation
		  (getTask_Output(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Output",
			 "group", "#group:27"
		   });		
		addAnnotation
		  (getTask_AdditionallyPerformedBy(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "AdditionallyPerformedBy",
			 "group", "#group:27"
		   });		
		addAnnotation
		  (getTask_OptionalInput(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "OptionalInput",
			 "group", "#group:27"
		   });		
		addAnnotation
		  (getTask_Estimate(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Estimate",
			 "group", "#group:27"
		   });		
		addAnnotation
		  (getTask_EstimationConsiderations(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "EstimationConsiderations",
			 "group", "#group:27"
		   });		
		addAnnotation
		  (getTask_ToolMentor(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ToolMentor",
			 "group", "#group:27"
		   });			
		addAnnotation
		  (taskDescriptionEClass, 
		   source, 
		   new String[] {
			 "name", "TaskDescription",
			 "kind", "elementOnly"
		   });			
		addAnnotation
		  (getTaskDescription_Alternatives(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Alternatives"
		   });			
		addAnnotation
		  (getTaskDescription_Purpose(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Purpose"
		   });			
		addAnnotation
		  (taskDescriptorEClass, 
		   source, 
		   new String[] {
			 "name", "TaskDescriptor",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getTaskDescriptor_Task(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Task"
		   });		
		addAnnotation
		  (getTaskDescriptor_Group3(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:36"
		   });		
		addAnnotation
		  (getTaskDescriptor_PerformedPrimarilyBy(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "PerformedPrimarilyBy",
			 "group", "#group:36"
		   });		
		addAnnotation
		  (getTaskDescriptor_AdditionallyPerformedBy(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "AdditionallyPerformedBy",
			 "group", "#group:36"
		   });		
		addAnnotation
		  (getTaskDescriptor_AssistedBy(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "AssistedBy",
			 "group", "#group:36"
		   });		
		addAnnotation
		  (getTaskDescriptor_ExternalInput(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ExternalInput",
			 "group", "#group:36"
		   });		
		addAnnotation
		  (getTaskDescriptor_MandatoryInput(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "MandatoryInput",
			 "group", "#group:36"
		   });		
		addAnnotation
		  (getTaskDescriptor_OptionalInput(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "OptionalInput",
			 "group", "#group:36"
		   });		
		addAnnotation
		  (getTaskDescriptor_Output(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Output",
			 "group", "#group:36"
		   });		
		addAnnotation
		  (getTaskDescriptor_Step(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Step"
		   });		
		addAnnotation
		  (getTaskDescriptor_IsSynchronizedWithSource(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "isSynchronizedWithSource"
		   });			
		addAnnotation
		  (teamProfileEClass, 
		   source, 
		   new String[] {
			 "name", "TeamProfile",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getTeamProfile_Group2(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:30"
		   });		
		addAnnotation
		  (getTeamProfile_Role(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Role",
			 "group", "#group:30"
		   });		
		addAnnotation
		  (getTeamProfile_SuperTeam(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "SuperTeam",
			 "group", "#group:30"
		   });		
		addAnnotation
		  (getTeamProfile_SubTeam(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "SubTeam",
			 "group", "#group:30"
		   });			
		addAnnotation
		  (templateEClass, 
		   source, 
		   new String[] {
			 "name", "Template",
			 "kind", "elementOnly"
		   });			
		addAnnotation
		  (termDefinitionEClass, 
		   source, 
		   new String[] {
			 "name", "TermDefinition",
			 "kind", "elementOnly"
		   });			
		addAnnotation
		  (toolEClass, 
		   source, 
		   new String[] {
			 "name", "Tool",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getTool_Group2(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:24"
		   });		
		addAnnotation
		  (getTool_ToolMentor(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ToolMentor",
			 "group", "#group:24"
		   });			
		addAnnotation
		  (toolMentorEClass, 
		   source, 
		   new String[] {
			 "name", "ToolMentor",
			 "kind", "elementOnly"
		   });			
		addAnnotation
		  (variabilityTypeEEnum, 
		   source, 
		   new String[] {
			 "name", "VariabilityType"
		   });		
		addAnnotation
		  (variabilityTypeObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "VariabilityType:Object",
			 "baseType", "VariabilityType"
		   });			
		addAnnotation
		  (whitepaperEClass, 
		   source, 
		   new String[] {
			 "name", "Whitepaper",
			 "kind", "elementOnly"
		   });			
		addAnnotation
		  (workBreakdownElementEClass, 
		   source, 
		   new String[] {
			 "name", "WorkBreakdownElement",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getWorkBreakdownElement_Group2(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:30"
		   });		
		addAnnotation
		  (getWorkBreakdownElement_Predecessor(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Predecessor",
			 "group", "#group:30"
		   });			
		addAnnotation
		  (getWorkBreakdownElement_IsEventDriven(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "isEventDriven"
		   });			
		addAnnotation
		  (getWorkBreakdownElement_IsOngoing(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "isOngoing"
		   });			
		addAnnotation
		  (getWorkBreakdownElement_IsRepeatable(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "isRepeatable"
		   });			
		addAnnotation
		  (workDefinitionEClass, 
		   source, 
		   new String[] {
			 "name", "WorkDefinition",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getWorkDefinition_Precondition(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Precondition"
		   });		
		addAnnotation
		  (getWorkDefinition_Postcondition(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Postcondition"
		   });			
		addAnnotation
		  (workOrderEClass, 
		   source, 
		   new String[] {
			 "name", "WorkOrder",
			 "kind", "simple"
		   });		
		addAnnotation
		  (getWorkOrder_Value(), 
		   source, 
		   new String[] {
			 "name", ":0",
			 "kind", "simple"
		   });			
		addAnnotation
		  (getWorkOrder_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "id"
		   });			
		addAnnotation
		  (getWorkOrder_LinkType(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "linkType"
		   });			
		addAnnotation
		  (getWorkOrder_Properties(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "properties"
		   });			
		addAnnotation
		  (workOrderTypeEEnum, 
		   source, 
		   new String[] {
			 "name", "WorkOrderType"
		   });		
		addAnnotation
		  (workOrderTypeObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "WorkOrderType:Object",
			 "baseType", "WorkOrderType"
		   });			
		addAnnotation
		  (workProductEClass, 
		   source, 
		   new String[] {
			 "name", "WorkProduct",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getWorkProduct_Group2(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:24"
		   });		
		addAnnotation
		  (getWorkProduct_Estimate(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Estimate",
			 "group", "#group:24"
		   });		
		addAnnotation
		  (getWorkProduct_EstimationConsiderations(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "EstimationConsiderations",
			 "group", "#group:24"
		   });		
		addAnnotation
		  (getWorkProduct_Report(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Report",
			 "group", "#group:24"
		   });		
		addAnnotation
		  (getWorkProduct_Template(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Template",
			 "group", "#group:24"
		   });		
		addAnnotation
		  (getWorkProduct_ToolMentor(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ToolMentor",
			 "group", "#group:24"
		   });			
		addAnnotation
		  (workProductDescriptionEClass, 
		   source, 
		   new String[] {
			 "name", "WorkProductDescription",
			 "kind", "elementOnly"
		   });			
		addAnnotation
		  (getWorkProductDescription_ImpactOfNotHaving(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ImpactOfNotHaving"
		   });			
		addAnnotation
		  (getWorkProductDescription_Purpose(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Purpose"
		   });			
		addAnnotation
		  (getWorkProductDescription_ReasonsForNotNeeding(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ReasonsForNotNeeding"
		   });			
		addAnnotation
		  (workProductDescriptorEClass, 
		   source, 
		   new String[] {
			 "name", "WorkProductDescriptor",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getWorkProductDescriptor_WorkProduct(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "WorkProduct"
		   });		
		addAnnotation
		  (getWorkProductDescriptor_ResponsibleRole(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ResponsibleRole"
		   });		
		addAnnotation
		  (getWorkProductDescriptor_Group2(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:33"
		   });		
		addAnnotation
		  (getWorkProductDescriptor_ExternalInputTo(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ExternalInputTo",
			 "group", "#group:33"
		   });		
		addAnnotation
		  (getWorkProductDescriptor_ImpactedBy(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ImpactedBy",
			 "group", "#group:33"
		   });		
		addAnnotation
		  (getWorkProductDescriptor_Impacts(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Impacts",
			 "group", "#group:33"
		   });		
		addAnnotation
		  (getWorkProductDescriptor_MandatoryInputTo(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "MandatoryInputTo",
			 "group", "#group:33"
		   });		
		addAnnotation
		  (getWorkProductDescriptor_OptionalInputTo(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "OptionalInputTo",
			 "group", "#group:33"
		   });		
		addAnnotation
		  (getWorkProductDescriptor_OutputFrom(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "OutputFrom",
			 "group", "#group:33"
		   });		
		addAnnotation
		  (getWorkProductDescriptor_DeliverableParts(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "DeliverableParts",
			 "group", "#group:33"
		   });			
		addAnnotation
		  (getWorkProductDescriptor_ActivityEntryState(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "activityEntryState"
		   });			
		addAnnotation
		  (getWorkProductDescriptor_ActivityExitState(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "activityExitState"
		   });			
		addAnnotation
		  (workProductTypeEClass, 
		   source, 
		   new String[] {
			 "name", "WorkProductType",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getWorkProductType_Group2(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:24"
		   });		
		addAnnotation
		  (getWorkProductType_WorkProduct(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "WorkProduct",
			 "group", "#group:24"
		   });
	}

} //UmaPackageImpl
