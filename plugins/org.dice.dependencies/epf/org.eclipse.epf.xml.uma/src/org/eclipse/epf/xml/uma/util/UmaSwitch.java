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
package org.eclipse.epf.xml.uma.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
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
import org.eclipse.epf.xml.uma.UmaPackage;
import org.eclipse.epf.xml.uma.Whitepaper;
import org.eclipse.epf.xml.uma.WorkBreakdownElement;
import org.eclipse.epf.xml.uma.WorkDefinition;
import org.eclipse.epf.xml.uma.WorkOrder;
import org.eclipse.epf.xml.uma.WorkProduct;
import org.eclipse.epf.xml.uma.WorkProductDescription;
import org.eclipse.epf.xml.uma.WorkProductDescriptor;
import org.eclipse.epf.xml.uma.WorkProductType;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.eclipse.epf.xml.uma.UmaPackage
 * @generated
 */
public class UmaSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static UmaPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UmaSwitch() {
		if (modelPackage == null) {
			modelPackage = UmaPackage.eINSTANCE;
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public T doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List<EClass> eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch(eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case UmaPackage.ACTIVITY: {
				Activity activity = (Activity)theEObject;
				T result = caseActivity(activity);
				if (result == null) result = caseWorkBreakdownElement(activity);
				if (result == null) result = caseBreakdownElement(activity);
				if (result == null) result = caseProcessElement(activity);
				if (result == null) result = caseDescribableElement(activity);
				if (result == null) result = caseMethodElement(activity);
				if (result == null) result = casePackageableElement(activity);
				if (result == null) result = caseNamedElement(activity);
				if (result == null) result = caseElement(activity);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.ACTIVITY_DESCRIPTION: {
				ActivityDescription activityDescription = (ActivityDescription)theEObject;
				T result = caseActivityDescription(activityDescription);
				if (result == null) result = caseBreakdownElementDescription(activityDescription);
				if (result == null) result = caseContentDescription(activityDescription);
				if (result == null) result = caseMethodUnit(activityDescription);
				if (result == null) result = caseMethodElement(activityDescription);
				if (result == null) result = casePackageableElement(activityDescription);
				if (result == null) result = caseNamedElement(activityDescription);
				if (result == null) result = caseElement(activityDescription);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.APPLICABLE_META_CLASS_INFO: {
				ApplicableMetaClassInfo applicableMetaClassInfo = (ApplicableMetaClassInfo)theEObject;
				T result = caseApplicableMetaClassInfo(applicableMetaClassInfo);
				if (result == null) result = casePackageableElement(applicableMetaClassInfo);
				if (result == null) result = caseNamedElement(applicableMetaClassInfo);
				if (result == null) result = caseElement(applicableMetaClassInfo);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.ARTIFACT: {
				Artifact artifact = (Artifact)theEObject;
				T result = caseArtifact(artifact);
				if (result == null) result = caseWorkProduct(artifact);
				if (result == null) result = caseContentElement(artifact);
				if (result == null) result = caseDescribableElement(artifact);
				if (result == null) result = caseMethodElement(artifact);
				if (result == null) result = casePackageableElement(artifact);
				if (result == null) result = caseNamedElement(artifact);
				if (result == null) result = caseElement(artifact);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.ARTIFACT_DESCRIPTION: {
				ArtifactDescription artifactDescription = (ArtifactDescription)theEObject;
				T result = caseArtifactDescription(artifactDescription);
				if (result == null) result = caseWorkProductDescription(artifactDescription);
				if (result == null) result = caseContentDescription(artifactDescription);
				if (result == null) result = caseMethodUnit(artifactDescription);
				if (result == null) result = caseMethodElement(artifactDescription);
				if (result == null) result = casePackageableElement(artifactDescription);
				if (result == null) result = caseNamedElement(artifactDescription);
				if (result == null) result = caseElement(artifactDescription);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.BREAKDOWN_ELEMENT: {
				BreakdownElement breakdownElement = (BreakdownElement)theEObject;
				T result = caseBreakdownElement(breakdownElement);
				if (result == null) result = caseProcessElement(breakdownElement);
				if (result == null) result = caseDescribableElement(breakdownElement);
				if (result == null) result = caseMethodElement(breakdownElement);
				if (result == null) result = casePackageableElement(breakdownElement);
				if (result == null) result = caseNamedElement(breakdownElement);
				if (result == null) result = caseElement(breakdownElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.BREAKDOWN_ELEMENT_DESCRIPTION: {
				BreakdownElementDescription breakdownElementDescription = (BreakdownElementDescription)theEObject;
				T result = caseBreakdownElementDescription(breakdownElementDescription);
				if (result == null) result = caseContentDescription(breakdownElementDescription);
				if (result == null) result = caseMethodUnit(breakdownElementDescription);
				if (result == null) result = caseMethodElement(breakdownElementDescription);
				if (result == null) result = casePackageableElement(breakdownElementDescription);
				if (result == null) result = caseNamedElement(breakdownElementDescription);
				if (result == null) result = caseElement(breakdownElementDescription);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.CAPABILITY_PATTERN: {
				CapabilityPattern capabilityPattern = (CapabilityPattern)theEObject;
				T result = caseCapabilityPattern(capabilityPattern);
				if (result == null) result = caseProcess(capabilityPattern);
				if (result == null) result = caseActivity(capabilityPattern);
				if (result == null) result = caseWorkBreakdownElement(capabilityPattern);
				if (result == null) result = caseBreakdownElement(capabilityPattern);
				if (result == null) result = caseProcessElement(capabilityPattern);
				if (result == null) result = caseDescribableElement(capabilityPattern);
				if (result == null) result = caseMethodElement(capabilityPattern);
				if (result == null) result = casePackageableElement(capabilityPattern);
				if (result == null) result = caseNamedElement(capabilityPattern);
				if (result == null) result = caseElement(capabilityPattern);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.CHECKLIST: {
				Checklist checklist = (Checklist)theEObject;
				T result = caseChecklist(checklist);
				if (result == null) result = caseGuidance(checklist);
				if (result == null) result = caseContentElement(checklist);
				if (result == null) result = caseDescribableElement(checklist);
				if (result == null) result = caseMethodElement(checklist);
				if (result == null) result = casePackageableElement(checklist);
				if (result == null) result = caseNamedElement(checklist);
				if (result == null) result = caseElement(checklist);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.COMPOSITE_ROLE: {
				CompositeRole compositeRole = (CompositeRole)theEObject;
				T result = caseCompositeRole(compositeRole);
				if (result == null) result = caseRoleDescriptor(compositeRole);
				if (result == null) result = caseDescriptor(compositeRole);
				if (result == null) result = caseBreakdownElement(compositeRole);
				if (result == null) result = caseProcessElement(compositeRole);
				if (result == null) result = caseDescribableElement(compositeRole);
				if (result == null) result = caseMethodElement(compositeRole);
				if (result == null) result = casePackageableElement(compositeRole);
				if (result == null) result = caseNamedElement(compositeRole);
				if (result == null) result = caseElement(compositeRole);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.CONCEPT: {
				Concept concept = (Concept)theEObject;
				T result = caseConcept(concept);
				if (result == null) result = caseGuidance(concept);
				if (result == null) result = caseContentElement(concept);
				if (result == null) result = caseDescribableElement(concept);
				if (result == null) result = caseMethodElement(concept);
				if (result == null) result = casePackageableElement(concept);
				if (result == null) result = caseNamedElement(concept);
				if (result == null) result = caseElement(concept);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.CONSTRAINT: {
				Constraint constraint = (Constraint)theEObject;
				T result = caseConstraint(constraint);
				if (result == null) result = caseMethodElement(constraint);
				if (result == null) result = casePackageableElement(constraint);
				if (result == null) result = caseNamedElement(constraint);
				if (result == null) result = caseElement(constraint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.CONTENT_CATEGORY: {
				ContentCategory contentCategory = (ContentCategory)theEObject;
				T result = caseContentCategory(contentCategory);
				if (result == null) result = caseContentElement(contentCategory);
				if (result == null) result = caseDescribableElement(contentCategory);
				if (result == null) result = caseMethodElement(contentCategory);
				if (result == null) result = casePackageableElement(contentCategory);
				if (result == null) result = caseNamedElement(contentCategory);
				if (result == null) result = caseElement(contentCategory);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.CONTENT_CATEGORY_PACKAGE: {
				ContentCategoryPackage contentCategoryPackage = (ContentCategoryPackage)theEObject;
				T result = caseContentCategoryPackage(contentCategoryPackage);
				if (result == null) result = caseMethodPackage(contentCategoryPackage);
				if (result == null) result = caseMethodElement(contentCategoryPackage);
				if (result == null) result = casePackageableElement(contentCategoryPackage);
				if (result == null) result = caseNamedElement(contentCategoryPackage);
				if (result == null) result = caseElement(contentCategoryPackage);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.CONTENT_DESCRIPTION: {
				ContentDescription contentDescription = (ContentDescription)theEObject;
				T result = caseContentDescription(contentDescription);
				if (result == null) result = caseMethodUnit(contentDescription);
				if (result == null) result = caseMethodElement(contentDescription);
				if (result == null) result = casePackageableElement(contentDescription);
				if (result == null) result = caseNamedElement(contentDescription);
				if (result == null) result = caseElement(contentDescription);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.CONTENT_ELEMENT: {
				ContentElement contentElement = (ContentElement)theEObject;
				T result = caseContentElement(contentElement);
				if (result == null) result = caseDescribableElement(contentElement);
				if (result == null) result = caseMethodElement(contentElement);
				if (result == null) result = casePackageableElement(contentElement);
				if (result == null) result = caseNamedElement(contentElement);
				if (result == null) result = caseElement(contentElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.CONTENT_PACKAGE: {
				ContentPackage contentPackage = (ContentPackage)theEObject;
				T result = caseContentPackage(contentPackage);
				if (result == null) result = caseMethodPackage(contentPackage);
				if (result == null) result = caseMethodElement(contentPackage);
				if (result == null) result = casePackageableElement(contentPackage);
				if (result == null) result = caseNamedElement(contentPackage);
				if (result == null) result = caseElement(contentPackage);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.CUSTOM_CATEGORY: {
				CustomCategory customCategory = (CustomCategory)theEObject;
				T result = caseCustomCategory(customCategory);
				if (result == null) result = caseContentCategory(customCategory);
				if (result == null) result = caseContentElement(customCategory);
				if (result == null) result = caseDescribableElement(customCategory);
				if (result == null) result = caseMethodElement(customCategory);
				if (result == null) result = casePackageableElement(customCategory);
				if (result == null) result = caseNamedElement(customCategory);
				if (result == null) result = caseElement(customCategory);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.DELIVERABLE: {
				Deliverable deliverable = (Deliverable)theEObject;
				T result = caseDeliverable(deliverable);
				if (result == null) result = caseWorkProduct(deliverable);
				if (result == null) result = caseContentElement(deliverable);
				if (result == null) result = caseDescribableElement(deliverable);
				if (result == null) result = caseMethodElement(deliverable);
				if (result == null) result = casePackageableElement(deliverable);
				if (result == null) result = caseNamedElement(deliverable);
				if (result == null) result = caseElement(deliverable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.DELIVERABLE_DESCRIPTION: {
				DeliverableDescription deliverableDescription = (DeliverableDescription)theEObject;
				T result = caseDeliverableDescription(deliverableDescription);
				if (result == null) result = caseWorkProductDescription(deliverableDescription);
				if (result == null) result = caseContentDescription(deliverableDescription);
				if (result == null) result = caseMethodUnit(deliverableDescription);
				if (result == null) result = caseMethodElement(deliverableDescription);
				if (result == null) result = casePackageableElement(deliverableDescription);
				if (result == null) result = caseNamedElement(deliverableDescription);
				if (result == null) result = caseElement(deliverableDescription);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.DELIVERY_PROCESS: {
				DeliveryProcess deliveryProcess = (DeliveryProcess)theEObject;
				T result = caseDeliveryProcess(deliveryProcess);
				if (result == null) result = caseProcess(deliveryProcess);
				if (result == null) result = caseActivity(deliveryProcess);
				if (result == null) result = caseWorkBreakdownElement(deliveryProcess);
				if (result == null) result = caseBreakdownElement(deliveryProcess);
				if (result == null) result = caseProcessElement(deliveryProcess);
				if (result == null) result = caseDescribableElement(deliveryProcess);
				if (result == null) result = caseMethodElement(deliveryProcess);
				if (result == null) result = casePackageableElement(deliveryProcess);
				if (result == null) result = caseNamedElement(deliveryProcess);
				if (result == null) result = caseElement(deliveryProcess);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.DELIVERY_PROCESS_DESCRIPTION: {
				DeliveryProcessDescription deliveryProcessDescription = (DeliveryProcessDescription)theEObject;
				T result = caseDeliveryProcessDescription(deliveryProcessDescription);
				if (result == null) result = caseProcessDescription(deliveryProcessDescription);
				if (result == null) result = caseActivityDescription(deliveryProcessDescription);
				if (result == null) result = caseBreakdownElementDescription(deliveryProcessDescription);
				if (result == null) result = caseContentDescription(deliveryProcessDescription);
				if (result == null) result = caseMethodUnit(deliveryProcessDescription);
				if (result == null) result = caseMethodElement(deliveryProcessDescription);
				if (result == null) result = casePackageableElement(deliveryProcessDescription);
				if (result == null) result = caseNamedElement(deliveryProcessDescription);
				if (result == null) result = caseElement(deliveryProcessDescription);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.DESCRIBABLE_ELEMENT: {
				DescribableElement describableElement = (DescribableElement)theEObject;
				T result = caseDescribableElement(describableElement);
				if (result == null) result = caseMethodElement(describableElement);
				if (result == null) result = casePackageableElement(describableElement);
				if (result == null) result = caseNamedElement(describableElement);
				if (result == null) result = caseElement(describableElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.DESCRIPTOR: {
				Descriptor descriptor = (Descriptor)theEObject;
				T result = caseDescriptor(descriptor);
				if (result == null) result = caseBreakdownElement(descriptor);
				if (result == null) result = caseProcessElement(descriptor);
				if (result == null) result = caseDescribableElement(descriptor);
				if (result == null) result = caseMethodElement(descriptor);
				if (result == null) result = casePackageableElement(descriptor);
				if (result == null) result = caseNamedElement(descriptor);
				if (result == null) result = caseElement(descriptor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.DESCRIPTOR_DESCRIPTION: {
				DescriptorDescription descriptorDescription = (DescriptorDescription)theEObject;
				T result = caseDescriptorDescription(descriptorDescription);
				if (result == null) result = caseBreakdownElementDescription(descriptorDescription);
				if (result == null) result = caseContentDescription(descriptorDescription);
				if (result == null) result = caseMethodUnit(descriptorDescription);
				if (result == null) result = caseMethodElement(descriptorDescription);
				if (result == null) result = casePackageableElement(descriptorDescription);
				if (result == null) result = caseNamedElement(descriptorDescription);
				if (result == null) result = caseElement(descriptorDescription);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.DISCIPLINE: {
				Discipline discipline = (Discipline)theEObject;
				T result = caseDiscipline(discipline);
				if (result == null) result = caseContentCategory(discipline);
				if (result == null) result = caseContentElement(discipline);
				if (result == null) result = caseDescribableElement(discipline);
				if (result == null) result = caseMethodElement(discipline);
				if (result == null) result = casePackageableElement(discipline);
				if (result == null) result = caseNamedElement(discipline);
				if (result == null) result = caseElement(discipline);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.DISCIPLINE_GROUPING: {
				DisciplineGrouping disciplineGrouping = (DisciplineGrouping)theEObject;
				T result = caseDisciplineGrouping(disciplineGrouping);
				if (result == null) result = caseContentCategory(disciplineGrouping);
				if (result == null) result = caseContentElement(disciplineGrouping);
				if (result == null) result = caseDescribableElement(disciplineGrouping);
				if (result == null) result = caseMethodElement(disciplineGrouping);
				if (result == null) result = casePackageableElement(disciplineGrouping);
				if (result == null) result = caseNamedElement(disciplineGrouping);
				if (result == null) result = caseElement(disciplineGrouping);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.DOCUMENT_ROOT: {
				DocumentRoot documentRoot = (DocumentRoot)theEObject;
				T result = caseDocumentRoot(documentRoot);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.DOMAIN: {
				Domain domain = (Domain)theEObject;
				T result = caseDomain(domain);
				if (result == null) result = caseContentCategory(domain);
				if (result == null) result = caseContentElement(domain);
				if (result == null) result = caseDescribableElement(domain);
				if (result == null) result = caseMethodElement(domain);
				if (result == null) result = casePackageableElement(domain);
				if (result == null) result = caseNamedElement(domain);
				if (result == null) result = caseElement(domain);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.ELEMENT: {
				Element element = (Element)theEObject;
				T result = caseElement(element);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.ESTIMATE: {
				Estimate estimate = (Estimate)theEObject;
				T result = caseEstimate(estimate);
				if (result == null) result = caseGuidance(estimate);
				if (result == null) result = caseContentElement(estimate);
				if (result == null) result = caseDescribableElement(estimate);
				if (result == null) result = caseMethodElement(estimate);
				if (result == null) result = casePackageableElement(estimate);
				if (result == null) result = caseNamedElement(estimate);
				if (result == null) result = caseElement(estimate);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.ESTIMATING_METRIC: {
				EstimatingMetric estimatingMetric = (EstimatingMetric)theEObject;
				T result = caseEstimatingMetric(estimatingMetric);
				if (result == null) result = caseGuidance(estimatingMetric);
				if (result == null) result = caseContentElement(estimatingMetric);
				if (result == null) result = caseDescribableElement(estimatingMetric);
				if (result == null) result = caseMethodElement(estimatingMetric);
				if (result == null) result = casePackageableElement(estimatingMetric);
				if (result == null) result = caseNamedElement(estimatingMetric);
				if (result == null) result = caseElement(estimatingMetric);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.ESTIMATION_CONSIDERATIONS: {
				EstimationConsiderations estimationConsiderations = (EstimationConsiderations)theEObject;
				T result = caseEstimationConsiderations(estimationConsiderations);
				if (result == null) result = caseGuidance(estimationConsiderations);
				if (result == null) result = caseContentElement(estimationConsiderations);
				if (result == null) result = caseDescribableElement(estimationConsiderations);
				if (result == null) result = caseMethodElement(estimationConsiderations);
				if (result == null) result = casePackageableElement(estimationConsiderations);
				if (result == null) result = caseNamedElement(estimationConsiderations);
				if (result == null) result = caseElement(estimationConsiderations);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.EXAMPLE: {
				Example example = (Example)theEObject;
				T result = caseExample(example);
				if (result == null) result = caseGuidance(example);
				if (result == null) result = caseContentElement(example);
				if (result == null) result = caseDescribableElement(example);
				if (result == null) result = caseMethodElement(example);
				if (result == null) result = casePackageableElement(example);
				if (result == null) result = caseNamedElement(example);
				if (result == null) result = caseElement(example);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.GUIDANCE: {
				Guidance guidance = (Guidance)theEObject;
				T result = caseGuidance(guidance);
				if (result == null) result = caseContentElement(guidance);
				if (result == null) result = caseDescribableElement(guidance);
				if (result == null) result = caseMethodElement(guidance);
				if (result == null) result = casePackageableElement(guidance);
				if (result == null) result = caseNamedElement(guidance);
				if (result == null) result = caseElement(guidance);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.GUIDANCE_DESCRIPTION: {
				GuidanceDescription guidanceDescription = (GuidanceDescription)theEObject;
				T result = caseGuidanceDescription(guidanceDescription);
				if (result == null) result = caseContentDescription(guidanceDescription);
				if (result == null) result = caseMethodUnit(guidanceDescription);
				if (result == null) result = caseMethodElement(guidanceDescription);
				if (result == null) result = casePackageableElement(guidanceDescription);
				if (result == null) result = caseNamedElement(guidanceDescription);
				if (result == null) result = caseElement(guidanceDescription);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.GUIDELINE: {
				Guideline guideline = (Guideline)theEObject;
				T result = caseGuideline(guideline);
				if (result == null) result = caseGuidance(guideline);
				if (result == null) result = caseContentElement(guideline);
				if (result == null) result = caseDescribableElement(guideline);
				if (result == null) result = caseMethodElement(guideline);
				if (result == null) result = casePackageableElement(guideline);
				if (result == null) result = caseNamedElement(guideline);
				if (result == null) result = caseElement(guideline);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.ITERATION: {
				Iteration iteration = (Iteration)theEObject;
				T result = caseIteration(iteration);
				if (result == null) result = caseActivity(iteration);
				if (result == null) result = caseWorkBreakdownElement(iteration);
				if (result == null) result = caseBreakdownElement(iteration);
				if (result == null) result = caseProcessElement(iteration);
				if (result == null) result = caseDescribableElement(iteration);
				if (result == null) result = caseMethodElement(iteration);
				if (result == null) result = casePackageableElement(iteration);
				if (result == null) result = caseNamedElement(iteration);
				if (result == null) result = caseElement(iteration);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.KIND: {
				Kind kind = (Kind)theEObject;
				T result = caseKind(kind);
				if (result == null) result = caseContentElement(kind);
				if (result == null) result = caseDescribableElement(kind);
				if (result == null) result = caseMethodElement(kind);
				if (result == null) result = casePackageableElement(kind);
				if (result == null) result = caseNamedElement(kind);
				if (result == null) result = caseElement(kind);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.METHOD_CONFIGURATION: {
				MethodConfiguration methodConfiguration = (MethodConfiguration)theEObject;
				T result = caseMethodConfiguration(methodConfiguration);
				if (result == null) result = caseMethodUnit(methodConfiguration);
				if (result == null) result = caseMethodElement(methodConfiguration);
				if (result == null) result = casePackageableElement(methodConfiguration);
				if (result == null) result = caseNamedElement(methodConfiguration);
				if (result == null) result = caseElement(methodConfiguration);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.METHOD_ELEMENT: {
				MethodElement methodElement = (MethodElement)theEObject;
				T result = caseMethodElement(methodElement);
				if (result == null) result = casePackageableElement(methodElement);
				if (result == null) result = caseNamedElement(methodElement);
				if (result == null) result = caseElement(methodElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.METHOD_ELEMENT_PROPERTY: {
				MethodElementProperty methodElementProperty = (MethodElementProperty)theEObject;
				T result = caseMethodElementProperty(methodElementProperty);
				if (result == null) result = casePackageableElement(methodElementProperty);
				if (result == null) result = caseNamedElement(methodElementProperty);
				if (result == null) result = caseElement(methodElementProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.METHOD_LIBRARY: {
				MethodLibrary methodLibrary = (MethodLibrary)theEObject;
				T result = caseMethodLibrary(methodLibrary);
				if (result == null) result = caseMethodUnit(methodLibrary);
				if (result == null) result = caseMethodElement(methodLibrary);
				if (result == null) result = casePackageableElement(methodLibrary);
				if (result == null) result = caseNamedElement(methodLibrary);
				if (result == null) result = caseElement(methodLibrary);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.METHOD_PACKAGE: {
				MethodPackage methodPackage = (MethodPackage)theEObject;
				T result = caseMethodPackage(methodPackage);
				if (result == null) result = caseMethodElement(methodPackage);
				if (result == null) result = casePackageableElement(methodPackage);
				if (result == null) result = caseNamedElement(methodPackage);
				if (result == null) result = caseElement(methodPackage);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.METHOD_PLUGIN: {
				MethodPlugin methodPlugin = (MethodPlugin)theEObject;
				T result = caseMethodPlugin(methodPlugin);
				if (result == null) result = caseMethodUnit(methodPlugin);
				if (result == null) result = caseMethodElement(methodPlugin);
				if (result == null) result = casePackageableElement(methodPlugin);
				if (result == null) result = caseNamedElement(methodPlugin);
				if (result == null) result = caseElement(methodPlugin);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.METHOD_UNIT: {
				MethodUnit methodUnit = (MethodUnit)theEObject;
				T result = caseMethodUnit(methodUnit);
				if (result == null) result = caseMethodElement(methodUnit);
				if (result == null) result = casePackageableElement(methodUnit);
				if (result == null) result = caseNamedElement(methodUnit);
				if (result == null) result = caseElement(methodUnit);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.MILESTONE: {
				Milestone milestone = (Milestone)theEObject;
				T result = caseMilestone(milestone);
				if (result == null) result = caseWorkBreakdownElement(milestone);
				if (result == null) result = caseBreakdownElement(milestone);
				if (result == null) result = caseProcessElement(milestone);
				if (result == null) result = caseDescribableElement(milestone);
				if (result == null) result = caseMethodElement(milestone);
				if (result == null) result = casePackageableElement(milestone);
				if (result == null) result = caseNamedElement(milestone);
				if (result == null) result = caseElement(milestone);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.NAMED_ELEMENT: {
				NamedElement namedElement = (NamedElement)theEObject;
				T result = caseNamedElement(namedElement);
				if (result == null) result = caseElement(namedElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.OUTCOME: {
				Outcome outcome = (Outcome)theEObject;
				T result = caseOutcome(outcome);
				if (result == null) result = caseWorkProduct(outcome);
				if (result == null) result = caseContentElement(outcome);
				if (result == null) result = caseDescribableElement(outcome);
				if (result == null) result = caseMethodElement(outcome);
				if (result == null) result = casePackageableElement(outcome);
				if (result == null) result = caseNamedElement(outcome);
				if (result == null) result = caseElement(outcome);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.PACKAGEABLE_ELEMENT: {
				PackageableElement packageableElement = (PackageableElement)theEObject;
				T result = casePackageableElement(packageableElement);
				if (result == null) result = caseNamedElement(packageableElement);
				if (result == null) result = caseElement(packageableElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.PHASE: {
				Phase phase = (Phase)theEObject;
				T result = casePhase(phase);
				if (result == null) result = caseActivity(phase);
				if (result == null) result = caseWorkBreakdownElement(phase);
				if (result == null) result = caseBreakdownElement(phase);
				if (result == null) result = caseProcessElement(phase);
				if (result == null) result = caseDescribableElement(phase);
				if (result == null) result = caseMethodElement(phase);
				if (result == null) result = casePackageableElement(phase);
				if (result == null) result = caseNamedElement(phase);
				if (result == null) result = caseElement(phase);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.PLANNING_DATA: {
				PlanningData planningData = (PlanningData)theEObject;
				T result = casePlanningData(planningData);
				if (result == null) result = caseProcessElement(planningData);
				if (result == null) result = caseDescribableElement(planningData);
				if (result == null) result = caseMethodElement(planningData);
				if (result == null) result = casePackageableElement(planningData);
				if (result == null) result = caseNamedElement(planningData);
				if (result == null) result = caseElement(planningData);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.PRACTICE: {
				Practice practice = (Practice)theEObject;
				T result = casePractice(practice);
				if (result == null) result = caseGuidance(practice);
				if (result == null) result = caseContentElement(practice);
				if (result == null) result = caseDescribableElement(practice);
				if (result == null) result = caseMethodElement(practice);
				if (result == null) result = casePackageableElement(practice);
				if (result == null) result = caseNamedElement(practice);
				if (result == null) result = caseElement(practice);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.PRACTICE_DESCRIPTION: {
				PracticeDescription practiceDescription = (PracticeDescription)theEObject;
				T result = casePracticeDescription(practiceDescription);
				if (result == null) result = caseContentDescription(practiceDescription);
				if (result == null) result = caseMethodUnit(practiceDescription);
				if (result == null) result = caseMethodElement(practiceDescription);
				if (result == null) result = casePackageableElement(practiceDescription);
				if (result == null) result = caseNamedElement(practiceDescription);
				if (result == null) result = caseElement(practiceDescription);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.PROCESS: {
				org.eclipse.epf.xml.uma.Process process = (org.eclipse.epf.xml.uma.Process)theEObject;
				T result = caseProcess(process);
				if (result == null) result = caseActivity(process);
				if (result == null) result = caseWorkBreakdownElement(process);
				if (result == null) result = caseBreakdownElement(process);
				if (result == null) result = caseProcessElement(process);
				if (result == null) result = caseDescribableElement(process);
				if (result == null) result = caseMethodElement(process);
				if (result == null) result = casePackageableElement(process);
				if (result == null) result = caseNamedElement(process);
				if (result == null) result = caseElement(process);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.PROCESS_COMPONENT: {
				ProcessComponent processComponent = (ProcessComponent)theEObject;
				T result = caseProcessComponent(processComponent);
				if (result == null) result = caseProcessPackage(processComponent);
				if (result == null) result = caseMethodPackage(processComponent);
				if (result == null) result = caseMethodElement(processComponent);
				if (result == null) result = casePackageableElement(processComponent);
				if (result == null) result = caseNamedElement(processComponent);
				if (result == null) result = caseElement(processComponent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.PROCESS_COMPONENT_INTERFACE: {
				ProcessComponentInterface processComponentInterface = (ProcessComponentInterface)theEObject;
				T result = caseProcessComponentInterface(processComponentInterface);
				if (result == null) result = caseBreakdownElement(processComponentInterface);
				if (result == null) result = caseProcessElement(processComponentInterface);
				if (result == null) result = caseDescribableElement(processComponentInterface);
				if (result == null) result = caseMethodElement(processComponentInterface);
				if (result == null) result = casePackageableElement(processComponentInterface);
				if (result == null) result = caseNamedElement(processComponentInterface);
				if (result == null) result = caseElement(processComponentInterface);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.PROCESS_DESCRIPTION: {
				ProcessDescription processDescription = (ProcessDescription)theEObject;
				T result = caseProcessDescription(processDescription);
				if (result == null) result = caseActivityDescription(processDescription);
				if (result == null) result = caseBreakdownElementDescription(processDescription);
				if (result == null) result = caseContentDescription(processDescription);
				if (result == null) result = caseMethodUnit(processDescription);
				if (result == null) result = caseMethodElement(processDescription);
				if (result == null) result = casePackageableElement(processDescription);
				if (result == null) result = caseNamedElement(processDescription);
				if (result == null) result = caseElement(processDescription);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.PROCESS_ELEMENT: {
				ProcessElement processElement = (ProcessElement)theEObject;
				T result = caseProcessElement(processElement);
				if (result == null) result = caseDescribableElement(processElement);
				if (result == null) result = caseMethodElement(processElement);
				if (result == null) result = casePackageableElement(processElement);
				if (result == null) result = caseNamedElement(processElement);
				if (result == null) result = caseElement(processElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.PROCESS_PACKAGE: {
				ProcessPackage processPackage = (ProcessPackage)theEObject;
				T result = caseProcessPackage(processPackage);
				if (result == null) result = caseMethodPackage(processPackage);
				if (result == null) result = caseMethodElement(processPackage);
				if (result == null) result = casePackageableElement(processPackage);
				if (result == null) result = caseNamedElement(processPackage);
				if (result == null) result = caseElement(processPackage);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.PROCESS_PLANNING_TEMPLATE: {
				ProcessPlanningTemplate processPlanningTemplate = (ProcessPlanningTemplate)theEObject;
				T result = caseProcessPlanningTemplate(processPlanningTemplate);
				if (result == null) result = caseProcess(processPlanningTemplate);
				if (result == null) result = caseActivity(processPlanningTemplate);
				if (result == null) result = caseWorkBreakdownElement(processPlanningTemplate);
				if (result == null) result = caseBreakdownElement(processPlanningTemplate);
				if (result == null) result = caseProcessElement(processPlanningTemplate);
				if (result == null) result = caseDescribableElement(processPlanningTemplate);
				if (result == null) result = caseMethodElement(processPlanningTemplate);
				if (result == null) result = casePackageableElement(processPlanningTemplate);
				if (result == null) result = caseNamedElement(processPlanningTemplate);
				if (result == null) result = caseElement(processPlanningTemplate);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.REPORT: {
				Report report = (Report)theEObject;
				T result = caseReport(report);
				if (result == null) result = caseGuidance(report);
				if (result == null) result = caseContentElement(report);
				if (result == null) result = caseDescribableElement(report);
				if (result == null) result = caseMethodElement(report);
				if (result == null) result = casePackageableElement(report);
				if (result == null) result = caseNamedElement(report);
				if (result == null) result = caseElement(report);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.REUSABLE_ASSET: {
				ReusableAsset reusableAsset = (ReusableAsset)theEObject;
				T result = caseReusableAsset(reusableAsset);
				if (result == null) result = caseGuidance(reusableAsset);
				if (result == null) result = caseContentElement(reusableAsset);
				if (result == null) result = caseDescribableElement(reusableAsset);
				if (result == null) result = caseMethodElement(reusableAsset);
				if (result == null) result = casePackageableElement(reusableAsset);
				if (result == null) result = caseNamedElement(reusableAsset);
				if (result == null) result = caseElement(reusableAsset);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.ROADMAP: {
				Roadmap roadmap = (Roadmap)theEObject;
				T result = caseRoadmap(roadmap);
				if (result == null) result = caseGuidance(roadmap);
				if (result == null) result = caseContentElement(roadmap);
				if (result == null) result = caseDescribableElement(roadmap);
				if (result == null) result = caseMethodElement(roadmap);
				if (result == null) result = casePackageableElement(roadmap);
				if (result == null) result = caseNamedElement(roadmap);
				if (result == null) result = caseElement(roadmap);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.ROLE: {
				Role role = (Role)theEObject;
				T result = caseRole(role);
				if (result == null) result = caseContentElement(role);
				if (result == null) result = caseDescribableElement(role);
				if (result == null) result = caseMethodElement(role);
				if (result == null) result = casePackageableElement(role);
				if (result == null) result = caseNamedElement(role);
				if (result == null) result = caseElement(role);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.ROLE_DESCRIPTION: {
				RoleDescription roleDescription = (RoleDescription)theEObject;
				T result = caseRoleDescription(roleDescription);
				if (result == null) result = caseContentDescription(roleDescription);
				if (result == null) result = caseMethodUnit(roleDescription);
				if (result == null) result = caseMethodElement(roleDescription);
				if (result == null) result = casePackageableElement(roleDescription);
				if (result == null) result = caseNamedElement(roleDescription);
				if (result == null) result = caseElement(roleDescription);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.ROLE_DESCRIPTOR: {
				RoleDescriptor roleDescriptor = (RoleDescriptor)theEObject;
				T result = caseRoleDescriptor(roleDescriptor);
				if (result == null) result = caseDescriptor(roleDescriptor);
				if (result == null) result = caseBreakdownElement(roleDescriptor);
				if (result == null) result = caseProcessElement(roleDescriptor);
				if (result == null) result = caseDescribableElement(roleDescriptor);
				if (result == null) result = caseMethodElement(roleDescriptor);
				if (result == null) result = casePackageableElement(roleDescriptor);
				if (result == null) result = caseNamedElement(roleDescriptor);
				if (result == null) result = caseElement(roleDescriptor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.ROLE_SET: {
				RoleSet roleSet = (RoleSet)theEObject;
				T result = caseRoleSet(roleSet);
				if (result == null) result = caseContentCategory(roleSet);
				if (result == null) result = caseContentElement(roleSet);
				if (result == null) result = caseDescribableElement(roleSet);
				if (result == null) result = caseMethodElement(roleSet);
				if (result == null) result = casePackageableElement(roleSet);
				if (result == null) result = caseNamedElement(roleSet);
				if (result == null) result = caseElement(roleSet);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.ROLE_SET_GROUPING: {
				RoleSetGrouping roleSetGrouping = (RoleSetGrouping)theEObject;
				T result = caseRoleSetGrouping(roleSetGrouping);
				if (result == null) result = caseContentCategory(roleSetGrouping);
				if (result == null) result = caseContentElement(roleSetGrouping);
				if (result == null) result = caseDescribableElement(roleSetGrouping);
				if (result == null) result = caseMethodElement(roleSetGrouping);
				if (result == null) result = casePackageableElement(roleSetGrouping);
				if (result == null) result = caseNamedElement(roleSetGrouping);
				if (result == null) result = caseElement(roleSetGrouping);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.SECTION: {
				Section section = (Section)theEObject;
				T result = caseSection(section);
				if (result == null) result = caseMethodElement(section);
				if (result == null) result = casePackageableElement(section);
				if (result == null) result = caseNamedElement(section);
				if (result == null) result = caseElement(section);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.SUPPORTING_MATERIAL: {
				SupportingMaterial supportingMaterial = (SupportingMaterial)theEObject;
				T result = caseSupportingMaterial(supportingMaterial);
				if (result == null) result = caseGuidance(supportingMaterial);
				if (result == null) result = caseContentElement(supportingMaterial);
				if (result == null) result = caseDescribableElement(supportingMaterial);
				if (result == null) result = caseMethodElement(supportingMaterial);
				if (result == null) result = casePackageableElement(supportingMaterial);
				if (result == null) result = caseNamedElement(supportingMaterial);
				if (result == null) result = caseElement(supportingMaterial);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.TASK: {
				Task task = (Task)theEObject;
				T result = caseTask(task);
				if (result == null) result = caseContentElement(task);
				if (result == null) result = caseDescribableElement(task);
				if (result == null) result = caseMethodElement(task);
				if (result == null) result = casePackageableElement(task);
				if (result == null) result = caseNamedElement(task);
				if (result == null) result = caseElement(task);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.TASK_DESCRIPTION: {
				TaskDescription taskDescription = (TaskDescription)theEObject;
				T result = caseTaskDescription(taskDescription);
				if (result == null) result = caseContentDescription(taskDescription);
				if (result == null) result = caseMethodUnit(taskDescription);
				if (result == null) result = caseMethodElement(taskDescription);
				if (result == null) result = casePackageableElement(taskDescription);
				if (result == null) result = caseNamedElement(taskDescription);
				if (result == null) result = caseElement(taskDescription);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.TASK_DESCRIPTOR: {
				TaskDescriptor taskDescriptor = (TaskDescriptor)theEObject;
				T result = caseTaskDescriptor(taskDescriptor);
				if (result == null) result = caseWorkBreakdownElement(taskDescriptor);
				if (result == null) result = caseBreakdownElement(taskDescriptor);
				if (result == null) result = caseProcessElement(taskDescriptor);
				if (result == null) result = caseDescribableElement(taskDescriptor);
				if (result == null) result = caseMethodElement(taskDescriptor);
				if (result == null) result = casePackageableElement(taskDescriptor);
				if (result == null) result = caseNamedElement(taskDescriptor);
				if (result == null) result = caseElement(taskDescriptor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.TEAM_PROFILE: {
				TeamProfile teamProfile = (TeamProfile)theEObject;
				T result = caseTeamProfile(teamProfile);
				if (result == null) result = caseBreakdownElement(teamProfile);
				if (result == null) result = caseProcessElement(teamProfile);
				if (result == null) result = caseDescribableElement(teamProfile);
				if (result == null) result = caseMethodElement(teamProfile);
				if (result == null) result = casePackageableElement(teamProfile);
				if (result == null) result = caseNamedElement(teamProfile);
				if (result == null) result = caseElement(teamProfile);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.TEMPLATE: {
				Template template = (Template)theEObject;
				T result = caseTemplate(template);
				if (result == null) result = caseGuidance(template);
				if (result == null) result = caseContentElement(template);
				if (result == null) result = caseDescribableElement(template);
				if (result == null) result = caseMethodElement(template);
				if (result == null) result = casePackageableElement(template);
				if (result == null) result = caseNamedElement(template);
				if (result == null) result = caseElement(template);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.TERM_DEFINITION: {
				TermDefinition termDefinition = (TermDefinition)theEObject;
				T result = caseTermDefinition(termDefinition);
				if (result == null) result = caseGuidance(termDefinition);
				if (result == null) result = caseContentElement(termDefinition);
				if (result == null) result = caseDescribableElement(termDefinition);
				if (result == null) result = caseMethodElement(termDefinition);
				if (result == null) result = casePackageableElement(termDefinition);
				if (result == null) result = caseNamedElement(termDefinition);
				if (result == null) result = caseElement(termDefinition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.TOOL: {
				Tool tool = (Tool)theEObject;
				T result = caseTool(tool);
				if (result == null) result = caseContentCategory(tool);
				if (result == null) result = caseContentElement(tool);
				if (result == null) result = caseDescribableElement(tool);
				if (result == null) result = caseMethodElement(tool);
				if (result == null) result = casePackageableElement(tool);
				if (result == null) result = caseNamedElement(tool);
				if (result == null) result = caseElement(tool);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.TOOL_MENTOR: {
				ToolMentor toolMentor = (ToolMentor)theEObject;
				T result = caseToolMentor(toolMentor);
				if (result == null) result = caseGuidance(toolMentor);
				if (result == null) result = caseContentElement(toolMentor);
				if (result == null) result = caseDescribableElement(toolMentor);
				if (result == null) result = caseMethodElement(toolMentor);
				if (result == null) result = casePackageableElement(toolMentor);
				if (result == null) result = caseNamedElement(toolMentor);
				if (result == null) result = caseElement(toolMentor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.WHITEPAPER: {
				Whitepaper whitepaper = (Whitepaper)theEObject;
				T result = caseWhitepaper(whitepaper);
				if (result == null) result = caseConcept(whitepaper);
				if (result == null) result = caseGuidance(whitepaper);
				if (result == null) result = caseContentElement(whitepaper);
				if (result == null) result = caseDescribableElement(whitepaper);
				if (result == null) result = caseMethodElement(whitepaper);
				if (result == null) result = casePackageableElement(whitepaper);
				if (result == null) result = caseNamedElement(whitepaper);
				if (result == null) result = caseElement(whitepaper);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.WORK_BREAKDOWN_ELEMENT: {
				WorkBreakdownElement workBreakdownElement = (WorkBreakdownElement)theEObject;
				T result = caseWorkBreakdownElement(workBreakdownElement);
				if (result == null) result = caseBreakdownElement(workBreakdownElement);
				if (result == null) result = caseProcessElement(workBreakdownElement);
				if (result == null) result = caseDescribableElement(workBreakdownElement);
				if (result == null) result = caseMethodElement(workBreakdownElement);
				if (result == null) result = casePackageableElement(workBreakdownElement);
				if (result == null) result = caseNamedElement(workBreakdownElement);
				if (result == null) result = caseElement(workBreakdownElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.WORK_DEFINITION: {
				WorkDefinition workDefinition = (WorkDefinition)theEObject;
				T result = caseWorkDefinition(workDefinition);
				if (result == null) result = caseMethodElement(workDefinition);
				if (result == null) result = casePackageableElement(workDefinition);
				if (result == null) result = caseNamedElement(workDefinition);
				if (result == null) result = caseElement(workDefinition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.WORK_ORDER: {
				WorkOrder workOrder = (WorkOrder)theEObject;
				T result = caseWorkOrder(workOrder);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.WORK_PRODUCT: {
				WorkProduct workProduct = (WorkProduct)theEObject;
				T result = caseWorkProduct(workProduct);
				if (result == null) result = caseContentElement(workProduct);
				if (result == null) result = caseDescribableElement(workProduct);
				if (result == null) result = caseMethodElement(workProduct);
				if (result == null) result = casePackageableElement(workProduct);
				if (result == null) result = caseNamedElement(workProduct);
				if (result == null) result = caseElement(workProduct);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.WORK_PRODUCT_DESCRIPTION: {
				WorkProductDescription workProductDescription = (WorkProductDescription)theEObject;
				T result = caseWorkProductDescription(workProductDescription);
				if (result == null) result = caseContentDescription(workProductDescription);
				if (result == null) result = caseMethodUnit(workProductDescription);
				if (result == null) result = caseMethodElement(workProductDescription);
				if (result == null) result = casePackageableElement(workProductDescription);
				if (result == null) result = caseNamedElement(workProductDescription);
				if (result == null) result = caseElement(workProductDescription);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.WORK_PRODUCT_DESCRIPTOR: {
				WorkProductDescriptor workProductDescriptor = (WorkProductDescriptor)theEObject;
				T result = caseWorkProductDescriptor(workProductDescriptor);
				if (result == null) result = caseDescriptor(workProductDescriptor);
				if (result == null) result = caseBreakdownElement(workProductDescriptor);
				if (result == null) result = caseProcessElement(workProductDescriptor);
				if (result == null) result = caseDescribableElement(workProductDescriptor);
				if (result == null) result = caseMethodElement(workProductDescriptor);
				if (result == null) result = casePackageableElement(workProductDescriptor);
				if (result == null) result = caseNamedElement(workProductDescriptor);
				if (result == null) result = caseElement(workProductDescriptor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UmaPackage.WORK_PRODUCT_TYPE: {
				WorkProductType workProductType = (WorkProductType)theEObject;
				T result = caseWorkProductType(workProductType);
				if (result == null) result = caseContentCategory(workProductType);
				if (result == null) result = caseContentElement(workProductType);
				if (result == null) result = caseDescribableElement(workProductType);
				if (result == null) result = caseMethodElement(workProductType);
				if (result == null) result = casePackageableElement(workProductType);
				if (result == null) result = caseNamedElement(workProductType);
				if (result == null) result = caseElement(workProductType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Activity</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Activity</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseActivity(Activity object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Activity Description</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Activity Description</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseActivityDescription(ActivityDescription object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Applicable Meta Class Info</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Applicable Meta Class Info</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseApplicableMetaClassInfo(ApplicableMetaClassInfo object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Artifact</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Artifact</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseArtifact(Artifact object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Artifact Description</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Artifact Description</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseArtifactDescription(ArtifactDescription object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Breakdown Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Breakdown Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBreakdownElement(BreakdownElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Breakdown Element Description</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Breakdown Element Description</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBreakdownElementDescription(BreakdownElementDescription object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Capability Pattern</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Capability Pattern</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCapabilityPattern(CapabilityPattern object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Checklist</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Checklist</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseChecklist(Checklist object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Composite Role</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Composite Role</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCompositeRole(CompositeRole object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Concept</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Concept</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConcept(Concept object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Constraint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConstraint(Constraint object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Content Category</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Content Category</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseContentCategory(ContentCategory object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Content Category Package</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Content Category Package</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseContentCategoryPackage(ContentCategoryPackage object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Content Description</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Content Description</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseContentDescription(ContentDescription object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Content Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Content Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseContentElement(ContentElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Content Package</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Content Package</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseContentPackage(ContentPackage object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Custom Category</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Custom Category</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCustomCategory(CustomCategory object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Deliverable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Deliverable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDeliverable(Deliverable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Deliverable Description</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Deliverable Description</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDeliverableDescription(DeliverableDescription object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Delivery Process</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Delivery Process</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDeliveryProcess(DeliveryProcess object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Delivery Process Description</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Delivery Process Description</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDeliveryProcessDescription(DeliveryProcessDescription object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Describable Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Describable Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDescribableElement(DescribableElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDescriptor(Descriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Descriptor Description</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Descriptor Description</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDescriptorDescription(DescriptorDescription object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Discipline</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Discipline</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDiscipline(Discipline object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Discipline Grouping</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Discipline Grouping</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDisciplineGrouping(DisciplineGrouping object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Document Root</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Document Root</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDocumentRoot(DocumentRoot object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Domain</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Domain</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDomain(Domain object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseElement(Element object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Estimate</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Estimate</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEstimate(Estimate object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Estimating Metric</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Estimating Metric</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEstimatingMetric(EstimatingMetric object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Estimation Considerations</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Estimation Considerations</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEstimationConsiderations(EstimationConsiderations object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Example</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Example</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExample(Example object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Guidance</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Guidance</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGuidance(Guidance object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Guidance Description</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Guidance Description</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGuidanceDescription(GuidanceDescription object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Guideline</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Guideline</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGuideline(Guideline object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Iteration</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Iteration</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIteration(Iteration object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Kind</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Kind</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseKind(Kind object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Method Configuration</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Method Configuration</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMethodConfiguration(MethodConfiguration object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Method Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Method Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMethodElement(MethodElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Method Element Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Method Element Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMethodElementProperty(MethodElementProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Method Library</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Method Library</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMethodLibrary(MethodLibrary object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Method Package</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Method Package</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMethodPackage(MethodPackage object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Method Plugin</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Method Plugin</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMethodPlugin(MethodPlugin object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Method Unit</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Method Unit</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMethodUnit(MethodUnit object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Milestone</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Milestone</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMilestone(Milestone object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Named Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Named Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNamedElement(NamedElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Outcome</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Outcome</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOutcome(Outcome object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Packageable Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Packageable Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePackageableElement(PackageableElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Phase</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Phase</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePhase(Phase object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Planning Data</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Planning Data</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePlanningData(PlanningData object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Practice</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Practice</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePractice(Practice object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Practice Description</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Practice Description</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePracticeDescription(PracticeDescription object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Process</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Process</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProcess(org.eclipse.epf.xml.uma.Process object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Process Component</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Process Component</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProcessComponent(ProcessComponent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Process Component Interface</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Process Component Interface</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProcessComponentInterface(ProcessComponentInterface object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Process Description</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Process Description</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProcessDescription(ProcessDescription object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Process Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Process Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProcessElement(ProcessElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Process Package</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Process Package</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProcessPackage(ProcessPackage object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Process Planning Template</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Process Planning Template</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProcessPlanningTemplate(ProcessPlanningTemplate object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Report</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Report</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseReport(Report object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Reusable Asset</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Reusable Asset</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseReusableAsset(ReusableAsset object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Roadmap</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Roadmap</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRoadmap(Roadmap object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Role</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Role</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRole(Role object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Role Description</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Role Description</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRoleDescription(RoleDescription object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Role Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Role Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRoleDescriptor(RoleDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Role Set</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Role Set</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRoleSet(RoleSet object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Role Set Grouping</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Role Set Grouping</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRoleSetGrouping(RoleSetGrouping object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Section</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Section</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSection(Section object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Supporting Material</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Supporting Material</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSupportingMaterial(SupportingMaterial object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Task</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Task</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTask(Task object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Task Description</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Task Description</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTaskDescription(TaskDescription object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Task Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Task Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTaskDescriptor(TaskDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Team Profile</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Team Profile</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTeamProfile(TeamProfile object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Template</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Template</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTemplate(Template object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Term Definition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Term Definition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTermDefinition(TermDefinition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Tool</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tool</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTool(Tool object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Tool Mentor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tool Mentor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseToolMentor(ToolMentor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Whitepaper</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Whitepaper</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWhitepaper(Whitepaper object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Work Breakdown Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Work Breakdown Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWorkBreakdownElement(WorkBreakdownElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Work Definition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Work Definition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWorkDefinition(WorkDefinition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Work Order</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Work Order</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWorkOrder(WorkOrder object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Work Product</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Work Product</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWorkProduct(WorkProduct object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Work Product Description</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Work Product Description</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWorkProductDescription(WorkProductDescription object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Work Product Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Work Product Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWorkProductDescriptor(WorkProductDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Work Product Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Work Product Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWorkProductType(WorkProductType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	public T defaultCase(EObject object) {
		return null;
	}

} //UmaSwitch
