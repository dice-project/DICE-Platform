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

import java.util.List;

import org.eclipse.emf.ecore.EClass;
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
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.eclipse.epf.uma.UmaPackage
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
		} else {
			List<EClass> eSuperTypes = theEClass.getESuperTypes();
			return eSuperTypes.isEmpty() ? defaultCase(theEObject) : doSwitch(
					eSuperTypes.get(0), theEObject);
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
		case UmaPackage.CLASSIFIER: {
			Classifier classifier = (Classifier) theEObject;
			T result = caseClassifier(classifier);
			if (result == null)
				result = caseType(classifier);
			if (result == null)
				result = casePackageableElement(classifier);
			if (result == null)
				result = caseNamedElement(classifier);
			if (result == null)
				result = caseElement(classifier);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.TYPE: {
			Type type = (Type) theEObject;
			T result = caseType(type);
			if (result == null)
				result = casePackageableElement(type);
			if (result == null)
				result = caseNamedElement(type);
			if (result == null)
				result = caseElement(type);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.PACKAGEABLE_ELEMENT: {
			PackageableElement packageableElement = (PackageableElement) theEObject;
			T result = casePackageableElement(packageableElement);
			if (result == null)
				result = caseNamedElement(packageableElement);
			if (result == null)
				result = caseElement(packageableElement);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.NAMED_ELEMENT: {
			NamedElement namedElement = (NamedElement) theEObject;
			T result = caseNamedElement(namedElement);
			if (result == null)
				result = caseElement(namedElement);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.ELEMENT: {
			Element element = (Element) theEObject;
			T result = caseElement(element);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.PACKAGE: {
			org.eclipse.epf.uma.Package package_ = (org.eclipse.epf.uma.Package) theEObject;
			T result = casePackage(package_);
			if (result == null)
				result = caseNamespace(package_);
			if (result == null)
				result = casePackageableElement(package_);
			if (result == null)
				result = caseNamedElement(package_);
			if (result == null)
				result = caseElement(package_);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.NAMESPACE: {
			Namespace namespace = (Namespace) theEObject;
			T result = caseNamespace(namespace);
			if (result == null)
				result = caseNamedElement(namespace);
			if (result == null)
				result = caseElement(namespace);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.CONSTRAINT: {
			Constraint constraint = (Constraint) theEObject;
			T result = caseConstraint(constraint);
			if (result == null)
				result = caseMethodElement(constraint);
			if (result == null)
				result = casePackageableElement(constraint);
			if (result == null)
				result = caseNamedElement(constraint);
			if (result == null)
				result = caseElement(constraint);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.METHOD_ELEMENT: {
			MethodElement methodElement = (MethodElement) theEObject;
			T result = caseMethodElement(methodElement);
			if (result == null)
				result = casePackageableElement(methodElement);
			if (result == null)
				result = caseNamedElement(methodElement);
			if (result == null)
				result = caseElement(methodElement);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.METHOD_ELEMENT_PROPERTY: {
			MethodElementProperty methodElementProperty = (MethodElementProperty) theEObject;
			T result = caseMethodElementProperty(methodElementProperty);
			if (result == null)
				result = casePackageableElement(methodElementProperty);
			if (result == null)
				result = caseNamedElement(methodElementProperty);
			if (result == null)
				result = caseElement(methodElementProperty);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.KIND: {
			Kind kind = (Kind) theEObject;
			T result = caseKind(kind);
			if (result == null)
				result = caseContentElement(kind);
			if (result == null)
				result = caseDescribableElement(kind);
			if (result == null)
				result = caseVariabilityElement(kind);
			if (result == null)
				result = caseMethodElement(kind);
			if (result == null)
				result = caseClassifier(kind);
			if (result == null)
				result = caseType(kind);
			if (result == null)
				result = casePackageableElement(kind);
			if (result == null)
				result = caseNamedElement(kind);
			if (result == null)
				result = caseElement(kind);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.CONTENT_ELEMENT: {
			ContentElement contentElement = (ContentElement) theEObject;
			T result = caseContentElement(contentElement);
			if (result == null)
				result = caseDescribableElement(contentElement);
			if (result == null)
				result = caseVariabilityElement(contentElement);
			if (result == null)
				result = caseMethodElement(contentElement);
			if (result == null)
				result = caseClassifier(contentElement);
			if (result == null)
				result = caseType(contentElement);
			if (result == null)
				result = casePackageableElement(contentElement);
			if (result == null)
				result = caseNamedElement(contentElement);
			if (result == null)
				result = caseElement(contentElement);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.DESCRIBABLE_ELEMENT: {
			DescribableElement describableElement = (DescribableElement) theEObject;
			T result = caseDescribableElement(describableElement);
			if (result == null)
				result = caseMethodElement(describableElement);
			if (result == null)
				result = caseClassifier(describableElement);
			if (result == null)
				result = caseType(describableElement);
			if (result == null)
				result = casePackageableElement(describableElement);
			if (result == null)
				result = caseNamedElement(describableElement);
			if (result == null)
				result = caseElement(describableElement);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.CONTENT_DESCRIPTION: {
			ContentDescription contentDescription = (ContentDescription) theEObject;
			T result = caseContentDescription(contentDescription);
			if (result == null)
				result = caseMethodUnit(contentDescription);
			if (result == null)
				result = caseMethodElement(contentDescription);
			if (result == null)
				result = casePackageableElement(contentDescription);
			if (result == null)
				result = caseNamedElement(contentDescription);
			if (result == null)
				result = caseElement(contentDescription);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.METHOD_UNIT: {
			MethodUnit methodUnit = (MethodUnit) theEObject;
			T result = caseMethodUnit(methodUnit);
			if (result == null)
				result = caseMethodElement(methodUnit);
			if (result == null)
				result = casePackageableElement(methodUnit);
			if (result == null)
				result = caseNamedElement(methodUnit);
			if (result == null)
				result = caseElement(methodUnit);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.SUPPORTING_MATERIAL: {
			SupportingMaterial supportingMaterial = (SupportingMaterial) theEObject;
			T result = caseSupportingMaterial(supportingMaterial);
			if (result == null)
				result = caseGuidance(supportingMaterial);
			if (result == null)
				result = caseContentElement(supportingMaterial);
			if (result == null)
				result = caseDescribableElement(supportingMaterial);
			if (result == null)
				result = caseVariabilityElement(supportingMaterial);
			if (result == null)
				result = caseMethodElement(supportingMaterial);
			if (result == null)
				result = caseClassifier(supportingMaterial);
			if (result == null)
				result = caseType(supportingMaterial);
			if (result == null)
				result = casePackageableElement(supportingMaterial);
			if (result == null)
				result = caseNamedElement(supportingMaterial);
			if (result == null)
				result = caseElement(supportingMaterial);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.GUIDANCE: {
			Guidance guidance = (Guidance) theEObject;
			T result = caseGuidance(guidance);
			if (result == null)
				result = caseContentElement(guidance);
			if (result == null)
				result = caseDescribableElement(guidance);
			if (result == null)
				result = caseVariabilityElement(guidance);
			if (result == null)
				result = caseMethodElement(guidance);
			if (result == null)
				result = caseClassifier(guidance);
			if (result == null)
				result = caseType(guidance);
			if (result == null)
				result = casePackageableElement(guidance);
			if (result == null)
				result = caseNamedElement(guidance);
			if (result == null)
				result = caseElement(guidance);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.SECTION: {
			Section section = (Section) theEObject;
			T result = caseSection(section);
			if (result == null)
				result = caseVariabilityElement(section);
			if (result == null)
				result = caseMethodElement(section);
			if (result == null)
				result = casePackageableElement(section);
			if (result == null)
				result = caseNamedElement(section);
			if (result == null)
				result = caseElement(section);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.VARIABILITY_ELEMENT: {
			VariabilityElement variabilityElement = (VariabilityElement) theEObject;
			T result = caseVariabilityElement(variabilityElement);
			if (result == null)
				result = caseMethodElement(variabilityElement);
			if (result == null)
				result = casePackageableElement(variabilityElement);
			if (result == null)
				result = caseNamedElement(variabilityElement);
			if (result == null)
				result = caseElement(variabilityElement);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.CONCEPT: {
			Concept concept = (Concept) theEObject;
			T result = caseConcept(concept);
			if (result == null)
				result = caseGuidance(concept);
			if (result == null)
				result = caseContentElement(concept);
			if (result == null)
				result = caseDescribableElement(concept);
			if (result == null)
				result = caseVariabilityElement(concept);
			if (result == null)
				result = caseMethodElement(concept);
			if (result == null)
				result = caseClassifier(concept);
			if (result == null)
				result = caseType(concept);
			if (result == null)
				result = casePackageableElement(concept);
			if (result == null)
				result = caseNamedElement(concept);
			if (result == null)
				result = caseElement(concept);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.CHECKLIST: {
			Checklist checklist = (Checklist) theEObject;
			T result = caseChecklist(checklist);
			if (result == null)
				result = caseGuidance(checklist);
			if (result == null)
				result = caseContentElement(checklist);
			if (result == null)
				result = caseDescribableElement(checklist);
			if (result == null)
				result = caseVariabilityElement(checklist);
			if (result == null)
				result = caseMethodElement(checklist);
			if (result == null)
				result = caseClassifier(checklist);
			if (result == null)
				result = caseType(checklist);
			if (result == null)
				result = casePackageableElement(checklist);
			if (result == null)
				result = caseNamedElement(checklist);
			if (result == null)
				result = caseElement(checklist);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.GUIDELINE: {
			Guideline guideline = (Guideline) theEObject;
			T result = caseGuideline(guideline);
			if (result == null)
				result = caseGuidance(guideline);
			if (result == null)
				result = caseContentElement(guideline);
			if (result == null)
				result = caseDescribableElement(guideline);
			if (result == null)
				result = caseVariabilityElement(guideline);
			if (result == null)
				result = caseMethodElement(guideline);
			if (result == null)
				result = caseClassifier(guideline);
			if (result == null)
				result = caseType(guideline);
			if (result == null)
				result = casePackageableElement(guideline);
			if (result == null)
				result = caseNamedElement(guideline);
			if (result == null)
				result = caseElement(guideline);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.EXAMPLE: {
			Example example = (Example) theEObject;
			T result = caseExample(example);
			if (result == null)
				result = caseGuidance(example);
			if (result == null)
				result = caseContentElement(example);
			if (result == null)
				result = caseDescribableElement(example);
			if (result == null)
				result = caseVariabilityElement(example);
			if (result == null)
				result = caseMethodElement(example);
			if (result == null)
				result = caseClassifier(example);
			if (result == null)
				result = caseType(example);
			if (result == null)
				result = casePackageableElement(example);
			if (result == null)
				result = caseNamedElement(example);
			if (result == null)
				result = caseElement(example);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.REUSABLE_ASSET: {
			ReusableAsset reusableAsset = (ReusableAsset) theEObject;
			T result = caseReusableAsset(reusableAsset);
			if (result == null)
				result = caseGuidance(reusableAsset);
			if (result == null)
				result = caseContentElement(reusableAsset);
			if (result == null)
				result = caseDescribableElement(reusableAsset);
			if (result == null)
				result = caseVariabilityElement(reusableAsset);
			if (result == null)
				result = caseMethodElement(reusableAsset);
			if (result == null)
				result = caseClassifier(reusableAsset);
			if (result == null)
				result = caseType(reusableAsset);
			if (result == null)
				result = casePackageableElement(reusableAsset);
			if (result == null)
				result = caseNamedElement(reusableAsset);
			if (result == null)
				result = caseElement(reusableAsset);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.TERM_DEFINITION: {
			TermDefinition termDefinition = (TermDefinition) theEObject;
			T result = caseTermDefinition(termDefinition);
			if (result == null)
				result = caseGuidance(termDefinition);
			if (result == null)
				result = caseContentElement(termDefinition);
			if (result == null)
				result = caseDescribableElement(termDefinition);
			if (result == null)
				result = caseVariabilityElement(termDefinition);
			if (result == null)
				result = caseMethodElement(termDefinition);
			if (result == null)
				result = caseClassifier(termDefinition);
			if (result == null)
				result = caseType(termDefinition);
			if (result == null)
				result = casePackageableElement(termDefinition);
			if (result == null)
				result = caseNamedElement(termDefinition);
			if (result == null)
				result = caseElement(termDefinition);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.APPLICABLE_META_CLASS_INFO: {
			ApplicableMetaClassInfo applicableMetaClassInfo = (ApplicableMetaClassInfo) theEObject;
			T result = caseApplicableMetaClassInfo(applicableMetaClassInfo);
			if (result == null)
				result = caseClassifier(applicableMetaClassInfo);
			if (result == null)
				result = caseType(applicableMetaClassInfo);
			if (result == null)
				result = casePackageableElement(applicableMetaClassInfo);
			if (result == null)
				result = caseNamedElement(applicableMetaClassInfo);
			if (result == null)
				result = caseElement(applicableMetaClassInfo);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.ARTIFACT: {
			Artifact artifact = (Artifact) theEObject;
			T result = caseArtifact(artifact);
			if (result == null)
				result = caseWorkProduct(artifact);
			if (result == null)
				result = caseContentElement(artifact);
			if (result == null)
				result = caseFulfillableElement(artifact);
			if (result == null)
				result = caseDescribableElement(artifact);
			if (result == null)
				result = caseVariabilityElement(artifact);
			if (result == null)
				result = caseMethodElement(artifact);
			if (result == null)
				result = caseClassifier(artifact);
			if (result == null)
				result = caseType(artifact);
			if (result == null)
				result = casePackageableElement(artifact);
			if (result == null)
				result = caseNamedElement(artifact);
			if (result == null)
				result = caseElement(artifact);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.WORK_PRODUCT: {
			WorkProduct workProduct = (WorkProduct) theEObject;
			T result = caseWorkProduct(workProduct);
			if (result == null)
				result = caseContentElement(workProduct);
			if (result == null)
				result = caseFulfillableElement(workProduct);
			if (result == null)
				result = caseDescribableElement(workProduct);
			if (result == null)
				result = caseVariabilityElement(workProduct);
			if (result == null)
				result = caseMethodElement(workProduct);
			if (result == null)
				result = caseClassifier(workProduct);
			if (result == null)
				result = caseType(workProduct);
			if (result == null)
				result = casePackageableElement(workProduct);
			if (result == null)
				result = caseNamedElement(workProduct);
			if (result == null)
				result = caseElement(workProduct);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.FULFILLABLE_ELEMENT: {
			FulfillableElement fulfillableElement = (FulfillableElement) theEObject;
			T result = caseFulfillableElement(fulfillableElement);
			if (result == null)
				result = caseDescribableElement(fulfillableElement);
			if (result == null)
				result = caseMethodElement(fulfillableElement);
			if (result == null)
				result = caseClassifier(fulfillableElement);
			if (result == null)
				result = caseType(fulfillableElement);
			if (result == null)
				result = casePackageableElement(fulfillableElement);
			if (result == null)
				result = caseNamedElement(fulfillableElement);
			if (result == null)
				result = caseElement(fulfillableElement);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.REPORT: {
			Report report = (Report) theEObject;
			T result = caseReport(report);
			if (result == null)
				result = caseGuidance(report);
			if (result == null)
				result = caseContentElement(report);
			if (result == null)
				result = caseDescribableElement(report);
			if (result == null)
				result = caseVariabilityElement(report);
			if (result == null)
				result = caseMethodElement(report);
			if (result == null)
				result = caseClassifier(report);
			if (result == null)
				result = caseType(report);
			if (result == null)
				result = casePackageableElement(report);
			if (result == null)
				result = caseNamedElement(report);
			if (result == null)
				result = caseElement(report);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.TEMPLATE: {
			Template template = (Template) theEObject;
			T result = caseTemplate(template);
			if (result == null)
				result = caseGuidance(template);
			if (result == null)
				result = caseContentElement(template);
			if (result == null)
				result = caseDescribableElement(template);
			if (result == null)
				result = caseVariabilityElement(template);
			if (result == null)
				result = caseMethodElement(template);
			if (result == null)
				result = caseClassifier(template);
			if (result == null)
				result = caseType(template);
			if (result == null)
				result = casePackageableElement(template);
			if (result == null)
				result = caseNamedElement(template);
			if (result == null)
				result = caseElement(template);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.TOOL_MENTOR: {
			ToolMentor toolMentor = (ToolMentor) theEObject;
			T result = caseToolMentor(toolMentor);
			if (result == null)
				result = caseGuidance(toolMentor);
			if (result == null)
				result = caseContentElement(toolMentor);
			if (result == null)
				result = caseDescribableElement(toolMentor);
			if (result == null)
				result = caseVariabilityElement(toolMentor);
			if (result == null)
				result = caseMethodElement(toolMentor);
			if (result == null)
				result = caseClassifier(toolMentor);
			if (result == null)
				result = caseType(toolMentor);
			if (result == null)
				result = casePackageableElement(toolMentor);
			if (result == null)
				result = caseNamedElement(toolMentor);
			if (result == null)
				result = caseElement(toolMentor);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.ESTIMATION_CONSIDERATIONS: {
			EstimationConsiderations estimationConsiderations = (EstimationConsiderations) theEObject;
			T result = caseEstimationConsiderations(estimationConsiderations);
			if (result == null)
				result = caseGuidance(estimationConsiderations);
			if (result == null)
				result = caseContentElement(estimationConsiderations);
			if (result == null)
				result = caseDescribableElement(estimationConsiderations);
			if (result == null)
				result = caseVariabilityElement(estimationConsiderations);
			if (result == null)
				result = caseMethodElement(estimationConsiderations);
			if (result == null)
				result = caseClassifier(estimationConsiderations);
			if (result == null)
				result = caseType(estimationConsiderations);
			if (result == null)
				result = casePackageableElement(estimationConsiderations);
			if (result == null)
				result = caseNamedElement(estimationConsiderations);
			if (result == null)
				result = caseElement(estimationConsiderations);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.DELIVERABLE: {
			Deliverable deliverable = (Deliverable) theEObject;
			T result = caseDeliverable(deliverable);
			if (result == null)
				result = caseWorkProduct(deliverable);
			if (result == null)
				result = caseContentElement(deliverable);
			if (result == null)
				result = caseFulfillableElement(deliverable);
			if (result == null)
				result = caseDescribableElement(deliverable);
			if (result == null)
				result = caseVariabilityElement(deliverable);
			if (result == null)
				result = caseMethodElement(deliverable);
			if (result == null)
				result = caseClassifier(deliverable);
			if (result == null)
				result = caseType(deliverable);
			if (result == null)
				result = casePackageableElement(deliverable);
			if (result == null)
				result = caseNamedElement(deliverable);
			if (result == null)
				result = caseElement(deliverable);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.OUTCOME: {
			Outcome outcome = (Outcome) theEObject;
			T result = caseOutcome(outcome);
			if (result == null)
				result = caseWorkProduct(outcome);
			if (result == null)
				result = caseContentElement(outcome);
			if (result == null)
				result = caseFulfillableElement(outcome);
			if (result == null)
				result = caseDescribableElement(outcome);
			if (result == null)
				result = caseVariabilityElement(outcome);
			if (result == null)
				result = caseMethodElement(outcome);
			if (result == null)
				result = caseClassifier(outcome);
			if (result == null)
				result = caseType(outcome);
			if (result == null)
				result = casePackageableElement(outcome);
			if (result == null)
				result = caseNamedElement(outcome);
			if (result == null)
				result = caseElement(outcome);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.STEP: {
			Step step = (Step) theEObject;
			T result = caseStep(step);
			if (result == null)
				result = caseSection(step);
			if (result == null)
				result = caseWorkDefinition(step);
			if (result == null)
				result = caseVariabilityElement(step);
			if (result == null)
				result = caseMethodElement(step);
			if (result == null)
				result = casePackageableElement(step);
			if (result == null)
				result = caseNamedElement(step);
			if (result == null)
				result = caseElement(step);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.WORK_DEFINITION: {
			WorkDefinition workDefinition = (WorkDefinition) theEObject;
			T result = caseWorkDefinition(workDefinition);
			if (result == null)
				result = caseMethodElement(workDefinition);
			if (result == null)
				result = casePackageableElement(workDefinition);
			if (result == null)
				result = caseNamedElement(workDefinition);
			if (result == null)
				result = caseElement(workDefinition);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.WHITEPAPER: {
			Whitepaper whitepaper = (Whitepaper) theEObject;
			T result = caseWhitepaper(whitepaper);
			if (result == null)
				result = caseConcept(whitepaper);
			if (result == null)
				result = caseGuidance(whitepaper);
			if (result == null)
				result = caseContentElement(whitepaper);
			if (result == null)
				result = caseDescribableElement(whitepaper);
			if (result == null)
				result = caseVariabilityElement(whitepaper);
			if (result == null)
				result = caseMethodElement(whitepaper);
			if (result == null)
				result = caseClassifier(whitepaper);
			if (result == null)
				result = caseType(whitepaper);
			if (result == null)
				result = casePackageableElement(whitepaper);
			if (result == null)
				result = caseNamedElement(whitepaper);
			if (result == null)
				result = caseElement(whitepaper);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.TASK: {
			Task task = (Task) theEObject;
			T result = caseTask(task);
			if (result == null)
				result = caseContentElement(task);
			if (result == null)
				result = caseWorkDefinition(task);
			if (result == null)
				result = caseDescribableElement(task);
			if (result == null)
				result = caseVariabilityElement(task);
			if (result == null)
				result = caseMethodElement(task);
			if (result == null)
				result = caseClassifier(task);
			if (result == null)
				result = caseType(task);
			if (result == null)
				result = casePackageableElement(task);
			if (result == null)
				result = caseNamedElement(task);
			if (result == null)
				result = caseElement(task);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.ROLE: {
			Role role = (Role) theEObject;
			T result = caseRole(role);
			if (result == null)
				result = caseContentElement(role);
			if (result == null)
				result = caseFulfillableElement(role);
			if (result == null)
				result = caseDescribableElement(role);
			if (result == null)
				result = caseVariabilityElement(role);
			if (result == null)
				result = caseMethodElement(role);
			if (result == null)
				result = caseClassifier(role);
			if (result == null)
				result = caseType(role);
			if (result == null)
				result = casePackageableElement(role);
			if (result == null)
				result = caseNamedElement(role);
			if (result == null)
				result = caseElement(role);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.ARTIFACT_DESCRIPTION: {
			ArtifactDescription artifactDescription = (ArtifactDescription) theEObject;
			T result = caseArtifactDescription(artifactDescription);
			if (result == null)
				result = caseWorkProductDescription(artifactDescription);
			if (result == null)
				result = caseContentDescription(artifactDescription);
			if (result == null)
				result = caseMethodUnit(artifactDescription);
			if (result == null)
				result = caseMethodElement(artifactDescription);
			if (result == null)
				result = casePackageableElement(artifactDescription);
			if (result == null)
				result = caseNamedElement(artifactDescription);
			if (result == null)
				result = caseElement(artifactDescription);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.WORK_PRODUCT_DESCRIPTION: {
			WorkProductDescription workProductDescription = (WorkProductDescription) theEObject;
			T result = caseWorkProductDescription(workProductDescription);
			if (result == null)
				result = caseContentDescription(workProductDescription);
			if (result == null)
				result = caseMethodUnit(workProductDescription);
			if (result == null)
				result = caseMethodElement(workProductDescription);
			if (result == null)
				result = casePackageableElement(workProductDescription);
			if (result == null)
				result = caseNamedElement(workProductDescription);
			if (result == null)
				result = caseElement(workProductDescription);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.DELIVERABLE_DESCRIPTION: {
			DeliverableDescription deliverableDescription = (DeliverableDescription) theEObject;
			T result = caseDeliverableDescription(deliverableDescription);
			if (result == null)
				result = caseWorkProductDescription(deliverableDescription);
			if (result == null)
				result = caseContentDescription(deliverableDescription);
			if (result == null)
				result = caseMethodUnit(deliverableDescription);
			if (result == null)
				result = caseMethodElement(deliverableDescription);
			if (result == null)
				result = casePackageableElement(deliverableDescription);
			if (result == null)
				result = caseNamedElement(deliverableDescription);
			if (result == null)
				result = caseElement(deliverableDescription);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.ROLE_DESCRIPTION: {
			RoleDescription roleDescription = (RoleDescription) theEObject;
			T result = caseRoleDescription(roleDescription);
			if (result == null)
				result = caseContentDescription(roleDescription);
			if (result == null)
				result = caseMethodUnit(roleDescription);
			if (result == null)
				result = caseMethodElement(roleDescription);
			if (result == null)
				result = casePackageableElement(roleDescription);
			if (result == null)
				result = caseNamedElement(roleDescription);
			if (result == null)
				result = caseElement(roleDescription);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.TASK_DESCRIPTION: {
			TaskDescription taskDescription = (TaskDescription) theEObject;
			T result = caseTaskDescription(taskDescription);
			if (result == null)
				result = caseContentDescription(taskDescription);
			if (result == null)
				result = caseMethodUnit(taskDescription);
			if (result == null)
				result = caseMethodElement(taskDescription);
			if (result == null)
				result = casePackageableElement(taskDescription);
			if (result == null)
				result = caseNamedElement(taskDescription);
			if (result == null)
				result = caseElement(taskDescription);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.GUIDANCE_DESCRIPTION: {
			GuidanceDescription guidanceDescription = (GuidanceDescription) theEObject;
			T result = caseGuidanceDescription(guidanceDescription);
			if (result == null)
				result = caseContentDescription(guidanceDescription);
			if (result == null)
				result = caseMethodUnit(guidanceDescription);
			if (result == null)
				result = caseMethodElement(guidanceDescription);
			if (result == null)
				result = casePackageableElement(guidanceDescription);
			if (result == null)
				result = caseNamedElement(guidanceDescription);
			if (result == null)
				result = caseElement(guidanceDescription);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.PRACTICE_DESCRIPTION: {
			PracticeDescription practiceDescription = (PracticeDescription) theEObject;
			T result = casePracticeDescription(practiceDescription);
			if (result == null)
				result = caseContentDescription(practiceDescription);
			if (result == null)
				result = caseMethodUnit(practiceDescription);
			if (result == null)
				result = caseMethodElement(practiceDescription);
			if (result == null)
				result = casePackageableElement(practiceDescription);
			if (result == null)
				result = caseNamedElement(practiceDescription);
			if (result == null)
				result = caseElement(practiceDescription);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.ROLE_SET: {
			RoleSet roleSet = (RoleSet) theEObject;
			T result = caseRoleSet(roleSet);
			if (result == null)
				result = caseContentCategory(roleSet);
			if (result == null)
				result = caseContentElement(roleSet);
			if (result == null)
				result = caseDescribableElement(roleSet);
			if (result == null)
				result = caseVariabilityElement(roleSet);
			if (result == null)
				result = caseMethodElement(roleSet);
			if (result == null)
				result = caseClassifier(roleSet);
			if (result == null)
				result = caseType(roleSet);
			if (result == null)
				result = casePackageableElement(roleSet);
			if (result == null)
				result = caseNamedElement(roleSet);
			if (result == null)
				result = caseElement(roleSet);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.CONTENT_CATEGORY: {
			ContentCategory contentCategory = (ContentCategory) theEObject;
			T result = caseContentCategory(contentCategory);
			if (result == null)
				result = caseContentElement(contentCategory);
			if (result == null)
				result = caseDescribableElement(contentCategory);
			if (result == null)
				result = caseVariabilityElement(contentCategory);
			if (result == null)
				result = caseMethodElement(contentCategory);
			if (result == null)
				result = caseClassifier(contentCategory);
			if (result == null)
				result = caseType(contentCategory);
			if (result == null)
				result = casePackageableElement(contentCategory);
			if (result == null)
				result = caseNamedElement(contentCategory);
			if (result == null)
				result = caseElement(contentCategory);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.DOMAIN: {
			Domain domain = (Domain) theEObject;
			T result = caseDomain(domain);
			if (result == null)
				result = caseContentCategory(domain);
			if (result == null)
				result = caseContentElement(domain);
			if (result == null)
				result = caseDescribableElement(domain);
			if (result == null)
				result = caseVariabilityElement(domain);
			if (result == null)
				result = caseMethodElement(domain);
			if (result == null)
				result = caseClassifier(domain);
			if (result == null)
				result = caseType(domain);
			if (result == null)
				result = casePackageableElement(domain);
			if (result == null)
				result = caseNamedElement(domain);
			if (result == null)
				result = caseElement(domain);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.WORK_PRODUCT_TYPE: {
			WorkProductType workProductType = (WorkProductType) theEObject;
			T result = caseWorkProductType(workProductType);
			if (result == null)
				result = caseContentCategory(workProductType);
			if (result == null)
				result = caseContentElement(workProductType);
			if (result == null)
				result = caseDescribableElement(workProductType);
			if (result == null)
				result = caseVariabilityElement(workProductType);
			if (result == null)
				result = caseMethodElement(workProductType);
			if (result == null)
				result = caseClassifier(workProductType);
			if (result == null)
				result = caseType(workProductType);
			if (result == null)
				result = casePackageableElement(workProductType);
			if (result == null)
				result = caseNamedElement(workProductType);
			if (result == null)
				result = caseElement(workProductType);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.DISCIPLINE_GROUPING: {
			DisciplineGrouping disciplineGrouping = (DisciplineGrouping) theEObject;
			T result = caseDisciplineGrouping(disciplineGrouping);
			if (result == null)
				result = caseContentCategory(disciplineGrouping);
			if (result == null)
				result = caseContentElement(disciplineGrouping);
			if (result == null)
				result = caseDescribableElement(disciplineGrouping);
			if (result == null)
				result = caseVariabilityElement(disciplineGrouping);
			if (result == null)
				result = caseMethodElement(disciplineGrouping);
			if (result == null)
				result = caseClassifier(disciplineGrouping);
			if (result == null)
				result = caseType(disciplineGrouping);
			if (result == null)
				result = casePackageableElement(disciplineGrouping);
			if (result == null)
				result = caseNamedElement(disciplineGrouping);
			if (result == null)
				result = caseElement(disciplineGrouping);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.DISCIPLINE: {
			Discipline discipline = (Discipline) theEObject;
			T result = caseDiscipline(discipline);
			if (result == null)
				result = caseContentCategory(discipline);
			if (result == null)
				result = caseContentElement(discipline);
			if (result == null)
				result = caseDescribableElement(discipline);
			if (result == null)
				result = caseVariabilityElement(discipline);
			if (result == null)
				result = caseMethodElement(discipline);
			if (result == null)
				result = caseClassifier(discipline);
			if (result == null)
				result = caseType(discipline);
			if (result == null)
				result = casePackageableElement(discipline);
			if (result == null)
				result = caseNamedElement(discipline);
			if (result == null)
				result = caseElement(discipline);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.ACTIVITY: {
			Activity activity = (Activity) theEObject;
			T result = caseActivity(activity);
			if (result == null)
				result = caseWorkBreakdownElement(activity);
			if (result == null)
				result = caseFulfillableElement(activity);
			if (result == null)
				result = caseVariabilityElement(activity);
			if (result == null)
				result = caseWorkDefinition(activity);
			if (result == null)
				result = caseBreakdownElement(activity);
			if (result == null)
				result = caseProcessElement(activity);
			if (result == null)
				result = caseDescribableElement(activity);
			if (result == null)
				result = caseMethodElement(activity);
			if (result == null)
				result = caseClassifier(activity);
			if (result == null)
				result = caseType(activity);
			if (result == null)
				result = casePackageableElement(activity);
			if (result == null)
				result = caseNamedElement(activity);
			if (result == null)
				result = caseElement(activity);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.WORK_BREAKDOWN_ELEMENT: {
			WorkBreakdownElement workBreakdownElement = (WorkBreakdownElement) theEObject;
			T result = caseWorkBreakdownElement(workBreakdownElement);
			if (result == null)
				result = caseBreakdownElement(workBreakdownElement);
			if (result == null)
				result = caseProcessElement(workBreakdownElement);
			if (result == null)
				result = caseDescribableElement(workBreakdownElement);
			if (result == null)
				result = caseMethodElement(workBreakdownElement);
			if (result == null)
				result = caseClassifier(workBreakdownElement);
			if (result == null)
				result = caseType(workBreakdownElement);
			if (result == null)
				result = casePackageableElement(workBreakdownElement);
			if (result == null)
				result = caseNamedElement(workBreakdownElement);
			if (result == null)
				result = caseElement(workBreakdownElement);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.BREAKDOWN_ELEMENT: {
			BreakdownElement breakdownElement = (BreakdownElement) theEObject;
			T result = caseBreakdownElement(breakdownElement);
			if (result == null)
				result = caseProcessElement(breakdownElement);
			if (result == null)
				result = caseDescribableElement(breakdownElement);
			if (result == null)
				result = caseMethodElement(breakdownElement);
			if (result == null)
				result = caseClassifier(breakdownElement);
			if (result == null)
				result = caseType(breakdownElement);
			if (result == null)
				result = casePackageableElement(breakdownElement);
			if (result == null)
				result = caseNamedElement(breakdownElement);
			if (result == null)
				result = caseElement(breakdownElement);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.PROCESS_ELEMENT: {
			ProcessElement processElement = (ProcessElement) theEObject;
			T result = caseProcessElement(processElement);
			if (result == null)
				result = caseDescribableElement(processElement);
			if (result == null)
				result = caseMethodElement(processElement);
			if (result == null)
				result = caseClassifier(processElement);
			if (result == null)
				result = caseType(processElement);
			if (result == null)
				result = casePackageableElement(processElement);
			if (result == null)
				result = caseNamedElement(processElement);
			if (result == null)
				result = caseElement(processElement);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.PLANNING_DATA: {
			PlanningData planningData = (PlanningData) theEObject;
			T result = casePlanningData(planningData);
			if (result == null)
				result = caseProcessElement(planningData);
			if (result == null)
				result = caseDescribableElement(planningData);
			if (result == null)
				result = caseMethodElement(planningData);
			if (result == null)
				result = caseClassifier(planningData);
			if (result == null)
				result = caseType(planningData);
			if (result == null)
				result = casePackageableElement(planningData);
			if (result == null)
				result = caseNamedElement(planningData);
			if (result == null)
				result = caseElement(planningData);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.WORK_ORDER: {
			WorkOrder workOrder = (WorkOrder) theEObject;
			T result = caseWorkOrder(workOrder);
			if (result == null)
				result = caseProcessElement(workOrder);
			if (result == null)
				result = caseDescribableElement(workOrder);
			if (result == null)
				result = caseMethodElement(workOrder);
			if (result == null)
				result = caseClassifier(workOrder);
			if (result == null)
				result = caseType(workOrder);
			if (result == null)
				result = casePackageableElement(workOrder);
			if (result == null)
				result = caseNamedElement(workOrder);
			if (result == null)
				result = caseElement(workOrder);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.ROADMAP: {
			Roadmap roadmap = (Roadmap) theEObject;
			T result = caseRoadmap(roadmap);
			if (result == null)
				result = caseGuidance(roadmap);
			if (result == null)
				result = caseContentElement(roadmap);
			if (result == null)
				result = caseDescribableElement(roadmap);
			if (result == null)
				result = caseVariabilityElement(roadmap);
			if (result == null)
				result = caseMethodElement(roadmap);
			if (result == null)
				result = caseClassifier(roadmap);
			if (result == null)
				result = caseType(roadmap);
			if (result == null)
				result = casePackageableElement(roadmap);
			if (result == null)
				result = caseNamedElement(roadmap);
			if (result == null)
				result = caseElement(roadmap);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.TOOL: {
			Tool tool = (Tool) theEObject;
			T result = caseTool(tool);
			if (result == null)
				result = caseContentCategory(tool);
			if (result == null)
				result = caseContentElement(tool);
			if (result == null)
				result = caseDescribableElement(tool);
			if (result == null)
				result = caseVariabilityElement(tool);
			if (result == null)
				result = caseMethodElement(tool);
			if (result == null)
				result = caseClassifier(tool);
			if (result == null)
				result = caseType(tool);
			if (result == null)
				result = casePackageableElement(tool);
			if (result == null)
				result = caseNamedElement(tool);
			if (result == null)
				result = caseElement(tool);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.ROLE_SET_GROUPING: {
			RoleSetGrouping roleSetGrouping = (RoleSetGrouping) theEObject;
			T result = caseRoleSetGrouping(roleSetGrouping);
			if (result == null)
				result = caseContentCategory(roleSetGrouping);
			if (result == null)
				result = caseContentElement(roleSetGrouping);
			if (result == null)
				result = caseDescribableElement(roleSetGrouping);
			if (result == null)
				result = caseVariabilityElement(roleSetGrouping);
			if (result == null)
				result = caseMethodElement(roleSetGrouping);
			if (result == null)
				result = caseClassifier(roleSetGrouping);
			if (result == null)
				result = caseType(roleSetGrouping);
			if (result == null)
				result = casePackageableElement(roleSetGrouping);
			if (result == null)
				result = caseNamedElement(roleSetGrouping);
			if (result == null)
				result = caseElement(roleSetGrouping);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.CUSTOM_CATEGORY: {
			CustomCategory customCategory = (CustomCategory) theEObject;
			T result = caseCustomCategory(customCategory);
			if (result == null)
				result = caseContentCategory(customCategory);
			if (result == null)
				result = caseContentElement(customCategory);
			if (result == null)
				result = caseDescribableElement(customCategory);
			if (result == null)
				result = caseVariabilityElement(customCategory);
			if (result == null)
				result = caseMethodElement(customCategory);
			if (result == null)
				result = caseClassifier(customCategory);
			if (result == null)
				result = caseType(customCategory);
			if (result == null)
				result = casePackageableElement(customCategory);
			if (result == null)
				result = caseNamedElement(customCategory);
			if (result == null)
				result = caseElement(customCategory);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.METHOD_PACKAGE: {
			MethodPackage methodPackage = (MethodPackage) theEObject;
			T result = caseMethodPackage(methodPackage);
			if (result == null)
				result = caseMethodElement(methodPackage);
			if (result == null)
				result = casePackage(methodPackage);
			if (result == null)
				result = casePackageableElement(methodPackage);
			if (result == null)
				result = caseNamespace(methodPackage);
			if (result == null)
				result = caseNamedElement(methodPackage);
			if (result == null)
				result = caseElement(methodPackage);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.CONTENT_PACKAGE: {
			ContentPackage contentPackage = (ContentPackage) theEObject;
			T result = caseContentPackage(contentPackage);
			if (result == null)
				result = caseMethodPackage(contentPackage);
			if (result == null)
				result = caseMethodElement(contentPackage);
			if (result == null)
				result = casePackage(contentPackage);
			if (result == null)
				result = casePackageableElement(contentPackage);
			if (result == null)
				result = caseNamespace(contentPackage);
			if (result == null)
				result = caseNamedElement(contentPackage);
			if (result == null)
				result = caseElement(contentPackage);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.MILESTONE: {
			Milestone milestone = (Milestone) theEObject;
			T result = caseMilestone(milestone);
			if (result == null)
				result = caseWorkBreakdownElement(milestone);
			if (result == null)
				result = caseBreakdownElement(milestone);
			if (result == null)
				result = caseProcessElement(milestone);
			if (result == null)
				result = caseDescribableElement(milestone);
			if (result == null)
				result = caseMethodElement(milestone);
			if (result == null)
				result = caseClassifier(milestone);
			if (result == null)
				result = caseType(milestone);
			if (result == null)
				result = casePackageableElement(milestone);
			if (result == null)
				result = caseNamedElement(milestone);
			if (result == null)
				result = caseElement(milestone);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.WORK_PRODUCT_DESCRIPTOR: {
			WorkProductDescriptor workProductDescriptor = (WorkProductDescriptor) theEObject;
			T result = caseWorkProductDescriptor(workProductDescriptor);
			if (result == null)
				result = caseDescriptor(workProductDescriptor);
			if (result == null)
				result = caseBreakdownElement(workProductDescriptor);
			if (result == null)
				result = caseProcessElement(workProductDescriptor);
			if (result == null)
				result = caseDescribableElement(workProductDescriptor);
			if (result == null)
				result = caseMethodElement(workProductDescriptor);
			if (result == null)
				result = caseClassifier(workProductDescriptor);
			if (result == null)
				result = caseType(workProductDescriptor);
			if (result == null)
				result = casePackageableElement(workProductDescriptor);
			if (result == null)
				result = caseNamedElement(workProductDescriptor);
			if (result == null)
				result = caseElement(workProductDescriptor);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.DESCRIPTOR: {
			Descriptor descriptor = (Descriptor) theEObject;
			T result = caseDescriptor(descriptor);
			if (result == null)
				result = caseBreakdownElement(descriptor);
			if (result == null)
				result = caseProcessElement(descriptor);
			if (result == null)
				result = caseDescribableElement(descriptor);
			if (result == null)
				result = caseMethodElement(descriptor);
			if (result == null)
				result = caseClassifier(descriptor);
			if (result == null)
				result = caseType(descriptor);
			if (result == null)
				result = casePackageableElement(descriptor);
			if (result == null)
				result = caseNamedElement(descriptor);
			if (result == null)
				result = caseElement(descriptor);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.ITERATION: {
			Iteration iteration = (Iteration) theEObject;
			T result = caseIteration(iteration);
			if (result == null)
				result = caseActivity(iteration);
			if (result == null)
				result = caseWorkBreakdownElement(iteration);
			if (result == null)
				result = caseFulfillableElement(iteration);
			if (result == null)
				result = caseVariabilityElement(iteration);
			if (result == null)
				result = caseWorkDefinition(iteration);
			if (result == null)
				result = caseBreakdownElement(iteration);
			if (result == null)
				result = caseProcessElement(iteration);
			if (result == null)
				result = caseDescribableElement(iteration);
			if (result == null)
				result = caseMethodElement(iteration);
			if (result == null)
				result = caseClassifier(iteration);
			if (result == null)
				result = caseType(iteration);
			if (result == null)
				result = casePackageableElement(iteration);
			if (result == null)
				result = caseNamedElement(iteration);
			if (result == null)
				result = caseElement(iteration);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.PHASE: {
			Phase phase = (Phase) theEObject;
			T result = casePhase(phase);
			if (result == null)
				result = caseActivity(phase);
			if (result == null)
				result = caseWorkBreakdownElement(phase);
			if (result == null)
				result = caseFulfillableElement(phase);
			if (result == null)
				result = caseVariabilityElement(phase);
			if (result == null)
				result = caseWorkDefinition(phase);
			if (result == null)
				result = caseBreakdownElement(phase);
			if (result == null)
				result = caseProcessElement(phase);
			if (result == null)
				result = caseDescribableElement(phase);
			if (result == null)
				result = caseMethodElement(phase);
			if (result == null)
				result = caseClassifier(phase);
			if (result == null)
				result = caseType(phase);
			if (result == null)
				result = casePackageableElement(phase);
			if (result == null)
				result = caseNamedElement(phase);
			if (result == null)
				result = caseElement(phase);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.TEAM_PROFILE: {
			TeamProfile teamProfile = (TeamProfile) theEObject;
			T result = caseTeamProfile(teamProfile);
			if (result == null)
				result = caseBreakdownElement(teamProfile);
			if (result == null)
				result = caseProcessElement(teamProfile);
			if (result == null)
				result = caseDescribableElement(teamProfile);
			if (result == null)
				result = caseMethodElement(teamProfile);
			if (result == null)
				result = caseClassifier(teamProfile);
			if (result == null)
				result = caseType(teamProfile);
			if (result == null)
				result = casePackageableElement(teamProfile);
			if (result == null)
				result = caseNamedElement(teamProfile);
			if (result == null)
				result = caseElement(teamProfile);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.ROLE_DESCRIPTOR: {
			RoleDescriptor roleDescriptor = (RoleDescriptor) theEObject;
			T result = caseRoleDescriptor(roleDescriptor);
			if (result == null)
				result = caseDescriptor(roleDescriptor);
			if (result == null)
				result = caseBreakdownElement(roleDescriptor);
			if (result == null)
				result = caseProcessElement(roleDescriptor);
			if (result == null)
				result = caseDescribableElement(roleDescriptor);
			if (result == null)
				result = caseMethodElement(roleDescriptor);
			if (result == null)
				result = caseClassifier(roleDescriptor);
			if (result == null)
				result = caseType(roleDescriptor);
			if (result == null)
				result = casePackageableElement(roleDescriptor);
			if (result == null)
				result = caseNamedElement(roleDescriptor);
			if (result == null)
				result = caseElement(roleDescriptor);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.TASK_DESCRIPTOR: {
			TaskDescriptor taskDescriptor = (TaskDescriptor) theEObject;
			T result = caseTaskDescriptor(taskDescriptor);
			if (result == null)
				result = caseWorkBreakdownElement(taskDescriptor);
			if (result == null)
				result = caseDescriptor(taskDescriptor);
			if (result == null)
				result = caseBreakdownElement(taskDescriptor);
			if (result == null)
				result = caseProcessElement(taskDescriptor);
			if (result == null)
				result = caseDescribableElement(taskDescriptor);
			if (result == null)
				result = caseMethodElement(taskDescriptor);
			if (result == null)
				result = caseClassifier(taskDescriptor);
			if (result == null)
				result = caseType(taskDescriptor);
			if (result == null)
				result = casePackageableElement(taskDescriptor);
			if (result == null)
				result = caseNamedElement(taskDescriptor);
			if (result == null)
				result = caseElement(taskDescriptor);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.COMPOSITE_ROLE: {
			CompositeRole compositeRole = (CompositeRole) theEObject;
			T result = caseCompositeRole(compositeRole);
			if (result == null)
				result = caseRoleDescriptor(compositeRole);
			if (result == null)
				result = caseDescriptor(compositeRole);
			if (result == null)
				result = caseBreakdownElement(compositeRole);
			if (result == null)
				result = caseProcessElement(compositeRole);
			if (result == null)
				result = caseDescribableElement(compositeRole);
			if (result == null)
				result = caseMethodElement(compositeRole);
			if (result == null)
				result = caseClassifier(compositeRole);
			if (result == null)
				result = caseType(compositeRole);
			if (result == null)
				result = casePackageableElement(compositeRole);
			if (result == null)
				result = caseNamedElement(compositeRole);
			if (result == null)
				result = caseElement(compositeRole);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.DELIVERY_PROCESS: {
			DeliveryProcess deliveryProcess = (DeliveryProcess) theEObject;
			T result = caseDeliveryProcess(deliveryProcess);
			if (result == null)
				result = caseProcess(deliveryProcess);
			if (result == null)
				result = caseActivity(deliveryProcess);
			if (result == null)
				result = caseWorkBreakdownElement(deliveryProcess);
			if (result == null)
				result = caseFulfillableElement(deliveryProcess);
			if (result == null)
				result = caseVariabilityElement(deliveryProcess);
			if (result == null)
				result = caseWorkDefinition(deliveryProcess);
			if (result == null)
				result = caseBreakdownElement(deliveryProcess);
			if (result == null)
				result = caseProcessElement(deliveryProcess);
			if (result == null)
				result = caseDescribableElement(deliveryProcess);
			if (result == null)
				result = caseMethodElement(deliveryProcess);
			if (result == null)
				result = caseClassifier(deliveryProcess);
			if (result == null)
				result = caseType(deliveryProcess);
			if (result == null)
				result = casePackageableElement(deliveryProcess);
			if (result == null)
				result = caseNamedElement(deliveryProcess);
			if (result == null)
				result = caseElement(deliveryProcess);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.PROCESS: {
			org.eclipse.epf.uma.Process process = (org.eclipse.epf.uma.Process) theEObject;
			T result = caseProcess(process);
			if (result == null)
				result = caseActivity(process);
			if (result == null)
				result = caseWorkBreakdownElement(process);
			if (result == null)
				result = caseFulfillableElement(process);
			if (result == null)
				result = caseVariabilityElement(process);
			if (result == null)
				result = caseWorkDefinition(process);
			if (result == null)
				result = caseBreakdownElement(process);
			if (result == null)
				result = caseProcessElement(process);
			if (result == null)
				result = caseDescribableElement(process);
			if (result == null)
				result = caseMethodElement(process);
			if (result == null)
				result = caseClassifier(process);
			if (result == null)
				result = caseType(process);
			if (result == null)
				result = casePackageableElement(process);
			if (result == null)
				result = caseNamedElement(process);
			if (result == null)
				result = caseElement(process);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.CAPABILITY_PATTERN: {
			CapabilityPattern capabilityPattern = (CapabilityPattern) theEObject;
			T result = caseCapabilityPattern(capabilityPattern);
			if (result == null)
				result = caseProcess(capabilityPattern);
			if (result == null)
				result = caseActivity(capabilityPattern);
			if (result == null)
				result = caseWorkBreakdownElement(capabilityPattern);
			if (result == null)
				result = caseFulfillableElement(capabilityPattern);
			if (result == null)
				result = caseVariabilityElement(capabilityPattern);
			if (result == null)
				result = caseWorkDefinition(capabilityPattern);
			if (result == null)
				result = caseBreakdownElement(capabilityPattern);
			if (result == null)
				result = caseProcessElement(capabilityPattern);
			if (result == null)
				result = caseDescribableElement(capabilityPattern);
			if (result == null)
				result = caseMethodElement(capabilityPattern);
			if (result == null)
				result = caseClassifier(capabilityPattern);
			if (result == null)
				result = caseType(capabilityPattern);
			if (result == null)
				result = casePackageableElement(capabilityPattern);
			if (result == null)
				result = caseNamedElement(capabilityPattern);
			if (result == null)
				result = caseElement(capabilityPattern);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.METHOD_CONFIGURATION: {
			MethodConfiguration methodConfiguration = (MethodConfiguration) theEObject;
			T result = caseMethodConfiguration(methodConfiguration);
			if (result == null)
				result = caseMethodUnit(methodConfiguration);
			if (result == null)
				result = caseMethodElement(methodConfiguration);
			if (result == null)
				result = casePackageableElement(methodConfiguration);
			if (result == null)
				result = caseNamedElement(methodConfiguration);
			if (result == null)
				result = caseElement(methodConfiguration);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.METHOD_PLUGIN: {
			MethodPlugin methodPlugin = (MethodPlugin) theEObject;
			T result = caseMethodPlugin(methodPlugin);
			if (result == null)
				result = caseMethodUnit(methodPlugin);
			if (result == null)
				result = casePackage(methodPlugin);
			if (result == null)
				result = caseMethodElement(methodPlugin);
			if (result == null)
				result = caseNamespace(methodPlugin);
			if (result == null)
				result = casePackageableElement(methodPlugin);
			if (result == null)
				result = caseNamedElement(methodPlugin);
			if (result == null)
				result = caseElement(methodPlugin);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.PROCESS_PLANNING_TEMPLATE: {
			ProcessPlanningTemplate processPlanningTemplate = (ProcessPlanningTemplate) theEObject;
			T result = caseProcessPlanningTemplate(processPlanningTemplate);
			if (result == null)
				result = caseProcess(processPlanningTemplate);
			if (result == null)
				result = caseActivity(processPlanningTemplate);
			if (result == null)
				result = caseWorkBreakdownElement(processPlanningTemplate);
			if (result == null)
				result = caseFulfillableElement(processPlanningTemplate);
			if (result == null)
				result = caseVariabilityElement(processPlanningTemplate);
			if (result == null)
				result = caseWorkDefinition(processPlanningTemplate);
			if (result == null)
				result = caseBreakdownElement(processPlanningTemplate);
			if (result == null)
				result = caseProcessElement(processPlanningTemplate);
			if (result == null)
				result = caseDescribableElement(processPlanningTemplate);
			if (result == null)
				result = caseMethodElement(processPlanningTemplate);
			if (result == null)
				result = caseClassifier(processPlanningTemplate);
			if (result == null)
				result = caseType(processPlanningTemplate);
			if (result == null)
				result = casePackageableElement(processPlanningTemplate);
			if (result == null)
				result = caseNamedElement(processPlanningTemplate);
			if (result == null)
				result = caseElement(processPlanningTemplate);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.PRACTICE: {
			Practice practice = (Practice) theEObject;
			T result = casePractice(practice);
			if (result == null)
				result = caseGuidance(practice);
			if (result == null)
				result = caseContentElement(practice);
			if (result == null)
				result = caseDescribableElement(practice);
			if (result == null)
				result = caseVariabilityElement(practice);
			if (result == null)
				result = caseMethodElement(practice);
			if (result == null)
				result = caseClassifier(practice);
			if (result == null)
				result = caseType(practice);
			if (result == null)
				result = casePackageableElement(practice);
			if (result == null)
				result = caseNamedElement(practice);
			if (result == null)
				result = caseElement(practice);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.BREAKDOWN_ELEMENT_DESCRIPTION: {
			BreakdownElementDescription breakdownElementDescription = (BreakdownElementDescription) theEObject;
			T result = caseBreakdownElementDescription(breakdownElementDescription);
			if (result == null)
				result = caseContentDescription(breakdownElementDescription);
			if (result == null)
				result = caseMethodUnit(breakdownElementDescription);
			if (result == null)
				result = caseMethodElement(breakdownElementDescription);
			if (result == null)
				result = casePackageableElement(breakdownElementDescription);
			if (result == null)
				result = caseNamedElement(breakdownElementDescription);
			if (result == null)
				result = caseElement(breakdownElementDescription);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.ACTIVITY_DESCRIPTION: {
			ActivityDescription activityDescription = (ActivityDescription) theEObject;
			T result = caseActivityDescription(activityDescription);
			if (result == null)
				result = caseBreakdownElementDescription(activityDescription);
			if (result == null)
				result = caseContentDescription(activityDescription);
			if (result == null)
				result = caseMethodUnit(activityDescription);
			if (result == null)
				result = caseMethodElement(activityDescription);
			if (result == null)
				result = casePackageableElement(activityDescription);
			if (result == null)
				result = caseNamedElement(activityDescription);
			if (result == null)
				result = caseElement(activityDescription);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.DELIVERY_PROCESS_DESCRIPTION: {
			DeliveryProcessDescription deliveryProcessDescription = (DeliveryProcessDescription) theEObject;
			T result = caseDeliveryProcessDescription(deliveryProcessDescription);
			if (result == null)
				result = caseProcessDescription(deliveryProcessDescription);
			if (result == null)
				result = caseActivityDescription(deliveryProcessDescription);
			if (result == null)
				result = caseBreakdownElementDescription(deliveryProcessDescription);
			if (result == null)
				result = caseContentDescription(deliveryProcessDescription);
			if (result == null)
				result = caseMethodUnit(deliveryProcessDescription);
			if (result == null)
				result = caseMethodElement(deliveryProcessDescription);
			if (result == null)
				result = casePackageableElement(deliveryProcessDescription);
			if (result == null)
				result = caseNamedElement(deliveryProcessDescription);
			if (result == null)
				result = caseElement(deliveryProcessDescription);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.PROCESS_DESCRIPTION: {
			ProcessDescription processDescription = (ProcessDescription) theEObject;
			T result = caseProcessDescription(processDescription);
			if (result == null)
				result = caseActivityDescription(processDescription);
			if (result == null)
				result = caseBreakdownElementDescription(processDescription);
			if (result == null)
				result = caseContentDescription(processDescription);
			if (result == null)
				result = caseMethodUnit(processDescription);
			if (result == null)
				result = caseMethodElement(processDescription);
			if (result == null)
				result = casePackageableElement(processDescription);
			if (result == null)
				result = caseNamedElement(processDescription);
			if (result == null)
				result = caseElement(processDescription);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.DESCRIPTOR_DESCRIPTION: {
			DescriptorDescription descriptorDescription = (DescriptorDescription) theEObject;
			T result = caseDescriptorDescription(descriptorDescription);
			if (result == null)
				result = caseBreakdownElementDescription(descriptorDescription);
			if (result == null)
				result = caseContentDescription(descriptorDescription);
			if (result == null)
				result = caseMethodUnit(descriptorDescription);
			if (result == null)
				result = caseMethodElement(descriptorDescription);
			if (result == null)
				result = casePackageableElement(descriptorDescription);
			if (result == null)
				result = caseNamedElement(descriptorDescription);
			if (result == null)
				result = caseElement(descriptorDescription);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.PROCESS_COMPONENT_DESCRIPTOR: {
			ProcessComponentDescriptor processComponentDescriptor = (ProcessComponentDescriptor) theEObject;
			T result = caseProcessComponentDescriptor(processComponentDescriptor);
			if (result == null)
				result = caseDescriptor(processComponentDescriptor);
			if (result == null)
				result = caseBreakdownElement(processComponentDescriptor);
			if (result == null)
				result = caseProcessElement(processComponentDescriptor);
			if (result == null)
				result = caseDescribableElement(processComponentDescriptor);
			if (result == null)
				result = caseMethodElement(processComponentDescriptor);
			if (result == null)
				result = caseClassifier(processComponentDescriptor);
			if (result == null)
				result = caseType(processComponentDescriptor);
			if (result == null)
				result = casePackageableElement(processComponentDescriptor);
			if (result == null)
				result = caseNamedElement(processComponentDescriptor);
			if (result == null)
				result = caseElement(processComponentDescriptor);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.PROCESS_COMPONENT: {
			ProcessComponent processComponent = (ProcessComponent) theEObject;
			T result = caseProcessComponent(processComponent);
			if (result == null)
				result = caseProcessPackage(processComponent);
			if (result == null)
				result = caseMethodUnit(processComponent);
			if (result == null)
				result = caseMethodPackage(processComponent);
			if (result == null)
				result = caseMethodElement(processComponent);
			if (result == null)
				result = casePackage(processComponent);
			if (result == null)
				result = casePackageableElement(processComponent);
			if (result == null)
				result = caseNamespace(processComponent);
			if (result == null)
				result = caseNamedElement(processComponent);
			if (result == null)
				result = caseElement(processComponent);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.PROCESS_PACKAGE: {
			ProcessPackage processPackage = (ProcessPackage) theEObject;
			T result = caseProcessPackage(processPackage);
			if (result == null)
				result = caseMethodPackage(processPackage);
			if (result == null)
				result = caseMethodElement(processPackage);
			if (result == null)
				result = casePackage(processPackage);
			if (result == null)
				result = casePackageableElement(processPackage);
			if (result == null)
				result = caseNamespace(processPackage);
			if (result == null)
				result = caseNamedElement(processPackage);
			if (result == null)
				result = caseElement(processPackage);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.DIAGRAM: {
			Diagram diagram = (Diagram) theEObject;
			T result = caseDiagram(diagram);
			if (result == null)
				result = caseGraphNode(diagram);
			if (result == null)
				result = caseGraphElement(diagram);
			if (result == null)
				result = caseDiagramElement(diagram);
			if (result == null)
				result = caseMethodElement(diagram);
			if (result == null)
				result = casePackageableElement(diagram);
			if (result == null)
				result = caseNamedElement(diagram);
			if (result == null)
				result = caseElement(diagram);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.GRAPH_NODE: {
			GraphNode graphNode = (GraphNode) theEObject;
			T result = caseGraphNode(graphNode);
			if (result == null)
				result = caseGraphElement(graphNode);
			if (result == null)
				result = caseDiagramElement(graphNode);
			if (result == null)
				result = caseMethodElement(graphNode);
			if (result == null)
				result = casePackageableElement(graphNode);
			if (result == null)
				result = caseNamedElement(graphNode);
			if (result == null)
				result = caseElement(graphNode);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.GRAPH_ELEMENT: {
			GraphElement graphElement = (GraphElement) theEObject;
			T result = caseGraphElement(graphElement);
			if (result == null)
				result = caseDiagramElement(graphElement);
			if (result == null)
				result = caseMethodElement(graphElement);
			if (result == null)
				result = casePackageableElement(graphElement);
			if (result == null)
				result = caseNamedElement(graphElement);
			if (result == null)
				result = caseElement(graphElement);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.DIAGRAM_ELEMENT: {
			DiagramElement diagramElement = (DiagramElement) theEObject;
			T result = caseDiagramElement(diagramElement);
			if (result == null)
				result = caseMethodElement(diagramElement);
			if (result == null)
				result = casePackageableElement(diagramElement);
			if (result == null)
				result = caseNamedElement(diagramElement);
			if (result == null)
				result = caseElement(diagramElement);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.REFERENCE: {
			Reference reference = (Reference) theEObject;
			T result = caseReference(reference);
			if (result == null)
				result = caseDiagramElement(reference);
			if (result == null)
				result = caseMethodElement(reference);
			if (result == null)
				result = casePackageableElement(reference);
			if (result == null)
				result = caseNamedElement(reference);
			if (result == null)
				result = caseElement(reference);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.PROPERTY: {
			Property property = (Property) theEObject;
			T result = caseProperty(property);
			if (result == null)
				result = caseDiagramElement(property);
			if (result == null)
				result = caseMethodElement(property);
			if (result == null)
				result = casePackageableElement(property);
			if (result == null)
				result = caseNamedElement(property);
			if (result == null)
				result = caseElement(property);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.POINT: {
			Point point = (Point) theEObject;
			T result = casePoint(point);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.DIAGRAM_LINK: {
			DiagramLink diagramLink = (DiagramLink) theEObject;
			T result = caseDiagramLink(diagramLink);
			if (result == null)
				result = caseDiagramElement(diagramLink);
			if (result == null)
				result = caseMethodElement(diagramLink);
			if (result == null)
				result = casePackageableElement(diagramLink);
			if (result == null)
				result = caseNamedElement(diagramLink);
			if (result == null)
				result = caseElement(diagramLink);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.GRAPH_CONNECTOR: {
			GraphConnector graphConnector = (GraphConnector) theEObject;
			T result = caseGraphConnector(graphConnector);
			if (result == null)
				result = caseGraphElement(graphConnector);
			if (result == null)
				result = caseDiagramElement(graphConnector);
			if (result == null)
				result = caseMethodElement(graphConnector);
			if (result == null)
				result = casePackageableElement(graphConnector);
			if (result == null)
				result = caseNamedElement(graphConnector);
			if (result == null)
				result = caseElement(graphConnector);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.GRAPH_EDGE: {
			GraphEdge graphEdge = (GraphEdge) theEObject;
			T result = caseGraphEdge(graphEdge);
			if (result == null)
				result = caseGraphElement(graphEdge);
			if (result == null)
				result = caseDiagramElement(graphEdge);
			if (result == null)
				result = caseMethodElement(graphEdge);
			if (result == null)
				result = casePackageableElement(graphEdge);
			if (result == null)
				result = caseNamedElement(graphEdge);
			if (result == null)
				result = caseElement(graphEdge);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.SEMANTIC_MODEL_BRIDGE: {
			SemanticModelBridge semanticModelBridge = (SemanticModelBridge) theEObject;
			T result = caseSemanticModelBridge(semanticModelBridge);
			if (result == null)
				result = caseDiagramElement(semanticModelBridge);
			if (result == null)
				result = caseMethodElement(semanticModelBridge);
			if (result == null)
				result = casePackageableElement(semanticModelBridge);
			if (result == null)
				result = caseNamedElement(semanticModelBridge);
			if (result == null)
				result = caseElement(semanticModelBridge);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.DIMENSION: {
			Dimension dimension = (Dimension) theEObject;
			T result = caseDimension(dimension);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.PROCESS_COMPONENT_INTERFACE: {
			ProcessComponentInterface processComponentInterface = (ProcessComponentInterface) theEObject;
			T result = caseProcessComponentInterface(processComponentInterface);
			if (result == null)
				result = caseBreakdownElement(processComponentInterface);
			if (result == null)
				result = caseProcessElement(processComponentInterface);
			if (result == null)
				result = caseDescribableElement(processComponentInterface);
			if (result == null)
				result = caseMethodElement(processComponentInterface);
			if (result == null)
				result = caseClassifier(processComponentInterface);
			if (result == null)
				result = caseType(processComponentInterface);
			if (result == null)
				result = casePackageableElement(processComponentInterface);
			if (result == null)
				result = caseNamedElement(processComponentInterface);
			if (result == null)
				result = caseElement(processComponentInterface);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.SIMPLE_SEMANTIC_MODEL_ELEMENT: {
			SimpleSemanticModelElement simpleSemanticModelElement = (SimpleSemanticModelElement) theEObject;
			T result = caseSimpleSemanticModelElement(simpleSemanticModelElement);
			if (result == null)
				result = caseSemanticModelBridge(simpleSemanticModelElement);
			if (result == null)
				result = caseDiagramElement(simpleSemanticModelElement);
			if (result == null)
				result = caseMethodElement(simpleSemanticModelElement);
			if (result == null)
				result = casePackageableElement(simpleSemanticModelElement);
			if (result == null)
				result = caseNamedElement(simpleSemanticModelElement);
			if (result == null)
				result = caseElement(simpleSemanticModelElement);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.UMA_SEMANTIC_MODEL_BRIDGE: {
			UMASemanticModelBridge umaSemanticModelBridge = (UMASemanticModelBridge) theEObject;
			T result = caseUMASemanticModelBridge(umaSemanticModelBridge);
			if (result == null)
				result = caseSemanticModelBridge(umaSemanticModelBridge);
			if (result == null)
				result = caseDiagramElement(umaSemanticModelBridge);
			if (result == null)
				result = caseMethodElement(umaSemanticModelBridge);
			if (result == null)
				result = casePackageableElement(umaSemanticModelBridge);
			if (result == null)
				result = caseNamedElement(umaSemanticModelBridge);
			if (result == null)
				result = caseElement(umaSemanticModelBridge);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.CORE_SEMANTIC_MODEL_BRIDGE: {
			CoreSemanticModelBridge coreSemanticModelBridge = (CoreSemanticModelBridge) theEObject;
			T result = caseCoreSemanticModelBridge(coreSemanticModelBridge);
			if (result == null)
				result = caseSemanticModelBridge(coreSemanticModelBridge);
			if (result == null)
				result = caseDiagramElement(coreSemanticModelBridge);
			if (result == null)
				result = caseMethodElement(coreSemanticModelBridge);
			if (result == null)
				result = casePackageableElement(coreSemanticModelBridge);
			if (result == null)
				result = caseNamedElement(coreSemanticModelBridge);
			if (result == null)
				result = caseElement(coreSemanticModelBridge);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.LEAF_ELEMENT: {
			LeafElement leafElement = (LeafElement) theEObject;
			T result = caseLeafElement(leafElement);
			if (result == null)
				result = caseDiagramElement(leafElement);
			if (result == null)
				result = caseMethodElement(leafElement);
			if (result == null)
				result = casePackageableElement(leafElement);
			if (result == null)
				result = caseNamedElement(leafElement);
			if (result == null)
				result = caseElement(leafElement);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.TEXT_ELEMENT: {
			TextElement textElement = (TextElement) theEObject;
			T result = caseTextElement(textElement);
			if (result == null)
				result = caseLeafElement(textElement);
			if (result == null)
				result = caseDiagramElement(textElement);
			if (result == null)
				result = caseMethodElement(textElement);
			if (result == null)
				result = casePackageableElement(textElement);
			if (result == null)
				result = caseNamedElement(textElement);
			if (result == null)
				result = caseElement(textElement);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.IMAGE: {
			Image image = (Image) theEObject;
			T result = caseImage(image);
			if (result == null)
				result = caseLeafElement(image);
			if (result == null)
				result = caseDiagramElement(image);
			if (result == null)
				result = caseMethodElement(image);
			if (result == null)
				result = casePackageableElement(image);
			if (result == null)
				result = caseNamedElement(image);
			if (result == null)
				result = caseElement(image);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.GRAPHIC_PRIMITIVE: {
			GraphicPrimitive graphicPrimitive = (GraphicPrimitive) theEObject;
			T result = caseGraphicPrimitive(graphicPrimitive);
			if (result == null)
				result = caseLeafElement(graphicPrimitive);
			if (result == null)
				result = caseDiagramElement(graphicPrimitive);
			if (result == null)
				result = caseMethodElement(graphicPrimitive);
			if (result == null)
				result = casePackageableElement(graphicPrimitive);
			if (result == null)
				result = caseNamedElement(graphicPrimitive);
			if (result == null)
				result = caseElement(graphicPrimitive);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.POLYLINE: {
			Polyline polyline = (Polyline) theEObject;
			T result = casePolyline(polyline);
			if (result == null)
				result = caseGraphicPrimitive(polyline);
			if (result == null)
				result = caseLeafElement(polyline);
			if (result == null)
				result = caseDiagramElement(polyline);
			if (result == null)
				result = caseMethodElement(polyline);
			if (result == null)
				result = casePackageableElement(polyline);
			if (result == null)
				result = caseNamedElement(polyline);
			if (result == null)
				result = caseElement(polyline);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.ELLIPSE: {
			Ellipse ellipse = (Ellipse) theEObject;
			T result = caseEllipse(ellipse);
			if (result == null)
				result = caseGraphicPrimitive(ellipse);
			if (result == null)
				result = caseLeafElement(ellipse);
			if (result == null)
				result = caseDiagramElement(ellipse);
			if (result == null)
				result = caseMethodElement(ellipse);
			if (result == null)
				result = casePackageableElement(ellipse);
			if (result == null)
				result = caseNamedElement(ellipse);
			if (result == null)
				result = caseElement(ellipse);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.PROCESS_FAMILY: {
			ProcessFamily processFamily = (ProcessFamily) theEObject;
			T result = caseProcessFamily(processFamily);
			if (result == null)
				result = caseMethodConfiguration(processFamily);
			if (result == null)
				result = caseMethodUnit(processFamily);
			if (result == null)
				result = caseMethodElement(processFamily);
			if (result == null)
				result = casePackageableElement(processFamily);
			if (result == null)
				result = caseNamedElement(processFamily);
			if (result == null)
				result = caseElement(processFamily);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UmaPackage.METHOD_LIBRARY: {
			MethodLibrary methodLibrary = (MethodLibrary) theEObject;
			T result = caseMethodLibrary(methodLibrary);
			if (result == null)
				result = caseMethodUnit(methodLibrary);
			if (result == null)
				result = casePackage(methodLibrary);
			if (result == null)
				result = caseMethodElement(methodLibrary);
			if (result == null)
				result = caseNamespace(methodLibrary);
			if (result == null)
				result = casePackageableElement(methodLibrary);
			if (result == null)
				result = caseNamedElement(methodLibrary);
			if (result == null)
				result = caseElement(methodLibrary);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		default:
			return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Classifier</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Classifier</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseClassifier(Classifier object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseType(Type object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Package</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Package</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePackage(org.eclipse.epf.uma.Package object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Namespace</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Namespace</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNamespace(Namespace object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Fulfillable Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Fulfillable Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFulfillableElement(FulfillableElement object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Step</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Step</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStep(Step object) {
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
	public T caseProcess(org.eclipse.epf.uma.Process object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Process Component Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Process Component Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProcessComponentDescriptor(ProcessComponentDescriptor object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Variability Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Variability Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseVariabilityElement(VariabilityElement object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Process Family</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Process Family</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProcessFamily(ProcessFamily object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Point</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Point</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePoint(Point object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Graph Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Graph Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGraphElement(GraphElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Diagram Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Diagram Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDiagramElement(DiagramElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Diagram Link</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Diagram Link</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDiagramLink(DiagramLink object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Graph Connector</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Graph Connector</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGraphConnector(GraphConnector object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Semantic Model Bridge</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Semantic Model Bridge</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSemanticModelBridge(SemanticModelBridge object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Dimension</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Dimension</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDimension(Dimension object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Reference</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Reference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseReference(Reference object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProperty(Property object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Graph Edge</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Graph Edge</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGraphEdge(GraphEdge object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Diagram</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Diagram</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDiagram(Diagram object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Graph Node</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Graph Node</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGraphNode(GraphNode object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Simple Semantic Model Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Simple Semantic Model Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSimpleSemanticModelElement(SimpleSemanticModelElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>UMA Semantic Model Bridge</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>UMA Semantic Model Bridge</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUMASemanticModelBridge(UMASemanticModelBridge object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Core Semantic Model Bridge</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Core Semantic Model Bridge</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCoreSemanticModelBridge(CoreSemanticModelBridge object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Leaf Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Leaf Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLeafElement(LeafElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Text Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Text Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTextElement(TextElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Image</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Image</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseImage(Image object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Graphic Primitive</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Graphic Primitive</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGraphicPrimitive(GraphicPrimitive object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Polyline</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Polyline</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePolyline(Polyline object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ellipse</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ellipse</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEllipse(Ellipse object) {
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