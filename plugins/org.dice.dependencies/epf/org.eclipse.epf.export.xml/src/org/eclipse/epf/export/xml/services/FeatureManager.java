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
package org.eclipse.epf.export.xml.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.epf.uma.UmaFactory;


/**
 * manage the features of EClass.
 * maintains a map of feature name to the EStructuralFeature object for easier access
 * 
 * @author Jinhua Xi
 *
 */
public class FeatureManager {

	public static FeatureManager INSTANCE = new FeatureManager();
	
	//private Map classMap = new HashMap();
	
	// map of uma feature name to the corresponding xml feature name
	private Map featureNameMap = new TreeMap();
	
	// uma features that should be ignored
	private List unneededRmcFeatures = new ArrayList();

	// xml features that can be ignored
	private List unneededXmlFeatures = new ArrayList();

	// map of EClass name to EClass Object for xml model
	// note: we can't use feature.getEType() to get the EClass from feature
	// the problem is the above returned EClass may not be the concreate EClass, instead, it is the base class
	// for example, it returns MethodPackage instead of ProcessPackage or ContentPackage
	// so we have to maintain a map manually
	private Map xmlEClassMap = new TreeMap();
	
	// map of Eclas name to EClass object for uma model
	private Map umaEClassMap = new TreeMap();
	
	private FeatureManager() {
		
		featureNameMap.put("OptionalInputTo", "optionalInputTo"); //$NON-NLS-1$ //$NON-NLS-2$
		featureNameMap.put("Role", "role"); //$NON-NLS-1$ //$NON-NLS-2$
		featureNameMap.put("Task", "task"); //$NON-NLS-1$ //$NON-NLS-2$
		featureNameMap.put("activityReferences", "activityReference"); //$NON-NLS-1$ //$NON-NLS-2$
		featureNameMap.put("aggregatedRoles", "aggregatedRole"); //$NON-NLS-1$ //$NON-NLS-2$
		featureNameMap.put("assets", "reusableAsset"); //$NON-NLS-1$ //$NON-NLS-2$
		featureNameMap.put("attachments", "attachment"); //$NON-NLS-1$ //$NON-NLS-2$
		featureNameMap.put("baseConfigurations", "baseConfiguration"); //$NON-NLS-1$ //$NON-NLS-2$
		featureNameMap.put("basedOnProcesses", "baseProcess"); //$NON-NLS-1$ //$NON-NLS-2$
		featureNameMap.put("bases", "referencedMethodPlugin"); //$NON-NLS-1$ //$NON-NLS-2$
		featureNameMap.put("body", "mainDescription"); //$NON-NLS-1$ //$NON-NLS-2$
		featureNameMap.put("breakdownElements", "breakdownElement"); //$NON-NLS-1$ //$NON-NLS-2$
		featureNameMap.put("categorizedElements", "categorizedElement"); //$NON-NLS-1$ //$NON-NLS-2$
		featureNameMap.put("checklists", "checklist"); //$NON-NLS-1$ //$NON-NLS-2$
		featureNameMap.put("childPackages", "methodPackage"); //$NON-NLS-1$ //$NON-NLS-2$
		featureNameMap.put("communicationsMaterials", "communicationsMaterial"); //$NON-NLS-1$ //$NON-NLS-2$
		featureNameMap.put("concepts", "concept,whitepaper"); //$NON-NLS-1$ //$NON-NLS-2$
		featureNameMap.put("conceptsAndPapers", "concept,whitepaper"); //$NON-NLS-1$ //$NON-NLS-2$
		featureNameMap.put("containedArtifacts", "containedArtifact"); //$NON-NLS-1$ //$NON-NLS-2$
		featureNameMap.put("contentElements", "contentElement"); //$NON-NLS-1$ //$NON-NLS-2$
		featureNameMap.put("contentReferences", "contentReference"); //$NON-NLS-1$ //$NON-NLS-2$
		featureNameMap.put("copyrightStatement", "copyright"); //$NON-NLS-1$ //$NON-NLS-2$
		featureNameMap.put("deliveredWorkProducts", "deliveredWorkProduct"); //$NON-NLS-1$ //$NON-NLS-2$
		featureNameMap.put("disciplines", "discipline"); //$NON-NLS-1$ //$NON-NLS-2$
		featureNameMap.put("educationMaterials", "educationMaterial"); //$NON-NLS-1$ //$NON-NLS-2$
		featureNameMap.put("estimationConderations", "estimationConsiderations"); //$NON-NLS-1$ //$NON-NLS-2$
		featureNameMap.put("examples", "example"); //$NON-NLS-1$ //$NON-NLS-2$
		featureNameMap.put("guid", "id"); //$NON-NLS-1$ //$NON-NLS-2$
		featureNameMap.put("guidelines", "guideline"); //$NON-NLS-1$ //$NON-NLS-2$
		featureNameMap.put("howtoStaff", "howToStaff"); //$NON-NLS-1$ //$NON-NLS-2$
		featureNameMap.put("includesPatterns", "includesPattern"); //$NON-NLS-1$ //$NON-NLS-2$
		featureNameMap.put("interfaces", "interface"); //$NON-NLS-1$ //$NON-NLS-2$
		featureNameMap.put("linkToPredecessor", "predecessor"); //$NON-NLS-1$ //$NON-NLS-2$
		featureNameMap.put("methodPackages", "methodPackage"); //$NON-NLS-1$ //$NON-NLS-2$
		featureNameMap.put("methodPlugins", "methodPlugin"); //$NON-NLS-1$ //$NON-NLS-2$
		featureNameMap.put("ownedRules", "ownedRule"); //$NON-NLS-1$ //$NON-NLS-2$
		featureNameMap.put("predefinedConfigurations", "methodConfiguration"); //$NON-NLS-1$ //$NON-NLS-2$
		featureNameMap.put("processElements", "processElement"); //$NON-NLS-1$ //$NON-NLS-2$
		featureNameMap.put("processViews", "processView"); //$NON-NLS-1$ //$NON-NLS-2$
		featureNameMap.put("referenceWorkflows", "referenceWorkflow"); //$NON-NLS-1$ //$NON-NLS-2$
		featureNameMap.put("reports", "report"); //$NON-NLS-1$ //$NON-NLS-2$
		featureNameMap.put("reusableAssets", "reusableAsset"); //$NON-NLS-1$ //$NON-NLS-2$
		featureNameMap.put("reusedPackages", "reusedPackage"); //$NON-NLS-1$ //$NON-NLS-2$
		featureNameMap.put("roadmaps", "roadmap"); //$NON-NLS-1$ //$NON-NLS-2$
		featureNameMap.put("roleSets", "roleSet"); //$NON-NLS-1$ //$NON-NLS-2$
		featureNameMap.put("roles", "role"); //$NON-NLS-1$ //$NON-NLS-2$
		featureNameMap.put("sectionDescription", "description"); //$NON-NLS-1$ //$NON-NLS-2$
		featureNameMap.put("sections", "section"); //$NON-NLS-1$ //$NON-NLS-2$
		featureNameMap.put("selectedSteps", "step"); //$NON-NLS-1$ //$NON-NLS-2$
		featureNameMap.put("subCategories", "subCategory"); //$NON-NLS-1$ //$NON-NLS-2$
		featureNameMap.put("subdiscipline", "subDiscipline"); //$NON-NLS-1$ //$NON-NLS-2$
		featureNameMap.put("subPractices", "subPractice"); //$NON-NLS-1$ //$NON-NLS-2$
		featureNameMap.put("subSections", "subSection"); //$NON-NLS-1$ //$NON-NLS-2$
		featureNameMap.put("subdomains", "subdomain"); //$NON-NLS-1$ //$NON-NLS-2$
		featureNameMap.put("superActivities", "superActivity"); //$NON-NLS-1$ //$NON-NLS-2$
		featureNameMap.put("supportingMaterials", "supportingMaterial"); //$NON-NLS-1$ //$NON-NLS-2$
		featureNameMap.put("tasks", "task"); //$NON-NLS-1$ //$NON-NLS-2$
		featureNameMap.put("teamRoles", "role"); //$NON-NLS-1$ //$NON-NLS-2$
		featureNameMap.put("templates", "template"); //$NON-NLS-1$ //$NON-NLS-2$
		featureNameMap.put("toolMentors", "toolMentor"); //$NON-NLS-1$ //$NON-NLS-2$
		featureNameMap.put("WorkProduct", "workProduct"); //$NON-NLS-1$ //$NON-NLS-2$
		featureNameMap.put("workProducts", "workProduct"); //$NON-NLS-1$ //$NON-NLS-2$
		featureNameMap.put("pred", "value"); //$NON-NLS-1$ //$NON-NLS-2$
		featureNameMap.put("methodElementProperty", "methodElementProperty"); //$NON-NLS-1$ //$NON-NLS-2$
		featureNameMap.put("requiredResults", "requiredResult"); //$NON-NLS-1$ //$NON-NLS-2$
		featureNameMap.put("fulfills", "fulfill"); //$NON-NLS-1$ //$NON-NLS-2$
		
		unneededRmcFeatures.add("containerArtifact"); //$NON-NLS-1$
		unneededRmcFeatures.add("modifies"); //$NON-NLS-1$
		unneededRmcFeatures.add("parentPackage"); //$NON-NLS-1$
		unneededRmcFeatures.add("diagrams"); //$NON-NLS-1$
		unneededRmcFeatures.add("modifies"); //$NON-NLS-1$
		unneededRmcFeatures.add("steps"); //$NON-NLS-1$
		unneededRmcFeatures.add("workedOnBy"); //$NON-NLS-1$
		unneededRmcFeatures.add("diagrams"); //$NON-NLS-1$
		
		unneededXmlFeatures.add("group"); //$NON-NLS-1$
		unneededXmlFeatures.add("group1"); //$NON-NLS-1$
		unneededXmlFeatures.add("group2"); //$NON-NLS-1$
		unneededXmlFeatures.add("predecessor1"); //$NON-NLS-1$
		
		loadXmlClasses();
		loadRmcClasses();
		
//		// print out the EClasses and feature map, for testing purpose
//		// don't delete this code segment
//		System.out.println("============ Loading EClasses for XML Uma model =================");
//		for (Iterator it = xmlEClassMap.keySet().iterator(); it.hasNext(); ) {
//			System.out.println("\t" + it.next());			
//		}
//		System.out.println("=================================================================");
//
//		for (Iterator it = featureNameMap.entrySet().iterator(); it.hasNext(); ) {
//			Map.Entry entry = (Map.Entry) it.next();
//			String key = (String)entry.getKey();
//			String value = (String)entry.getValue();
//			if (value != null && value.length() > 0 ) {
//				System.out.println("\tfeatureNameMap.put(\"" + key + "\", \"" + value + "\");");	
//			}
//		}
	}
	
	private void loadRmcClasses() {
		
		// load EClass map
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getActivity());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getActivityDescription());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getArtifact());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getArtifactDescription());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getBreakdownElement());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getBreakdownElementDescription());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getCapabilityPattern());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getChecklist());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getCompositeRole());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getConcept());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getConstraint());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getContentCategory());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getContentDescription());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getContentElement());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getContentPackage());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getCustomCategory());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getDeliverable());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getDeliverableDescription());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getDeliveryProcess());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getDeliveryProcessDescription());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getDescribableElement());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getDescriptor());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getDescriptorDescription());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getDiscipline());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getDisciplineGrouping());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getDomain());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getEstimationConsiderations());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getExample());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getGuidance());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getGuidanceDescription());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getGuideline());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getIteration());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getMethodConfiguration());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getMethodElement());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getMethodLibrary());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getMethodPackage());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getMethodPlugin());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getMethodUnit());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getMilestone());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getNamedElement());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getOutcome());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getPackageableElement());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getPhase());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getPlanningData());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getPractice());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getPracticeDescription());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getProcess());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getProcessComponent());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getProcessComponentInterface());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getProcessDescription());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getProcessElement());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getProcessPackage());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getProcessPlanningTemplate());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getReport());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getReusableAsset());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getRoadmap());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getRole());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getRoleDescription());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getRoleDescriptor());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getRoleSet());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getRoleSetGrouping());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getSection());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getSupportingMaterial());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getTask());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getTaskDescription());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getTaskDescriptor());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getTeamProfile());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getTemplate());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getTermDefinition());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getTool());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getToolMentor());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getWhitepaper());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getWorkBreakdownElement());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getWorkDefinition());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getWorkOrder());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getWorkProduct());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getWorkProductDescription());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getWorkProductDescriptor());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getWorkProductType());
		loadModelClasses(umaEClassMap, org.eclipse.epf.uma.UmaPackage.eINSTANCE.getMethodElementProperty());	
	}
	
	
	private void loadXmlClasses() {
		
		// load EClass map
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getActivity());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getActivityDescription());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getArtifact());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getArtifactDescription());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getBreakdownElement());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getBreakdownElementDescription());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getCapabilityPattern());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getChecklist());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getCompositeRole());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getConcept());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getConstraint());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getContentCategory());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getContentCategoryPackage());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getContentDescription());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getContentElement());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getContentPackage());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getCustomCategory());
//		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getCategoryType());
//		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getCategoryTypeObject());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getDeliverable());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getDeliverableDescription());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getDeliveryProcess());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getDeliveryProcessDescription());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getDescribableElement());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getDescriptor());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getDescriptorDescription());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getDiscipline());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getDisciplineGrouping());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getDomain());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getEstimate());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getEstimatingMetric());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getEstimationConsiderations());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getExample());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getGuidance());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getGuidanceDescription());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getGuideline());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getIteration());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getMethodConfiguration());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getMethodElement());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getMethodLibrary());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getMethodPackage());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getMethodPlugin());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getMethodUnit());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getMilestone());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getNamedElement());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getOutcome());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getPackageableElement());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getPhase());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getPlanningData());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getPractice());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getPracticeDescription());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getProcess());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getProcessComponent());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getProcessComponentInterface());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getProcessDescription());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getProcessElement());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getProcessPackage());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getProcessPlanningTemplate());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getReport());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getReusableAsset());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getRoadmap());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getRole());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getRoleDescription());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getRoleDescriptor());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getRoleSet());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getRoleSetGrouping());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getSection());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getSupportingMaterial());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getTask());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getTaskDescription());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getTaskDescriptor());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getTeamProfile());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getTemplate());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getTermDefinition());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getTool());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getToolMentor());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getWhitepaper());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getWorkBreakdownElement());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getWorkDefinition());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getWorkOrder());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getWorkProduct());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getWorkProductDescription());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getWorkProductDescriptor());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getWorkProductType());
		loadModelClasses(xmlEClassMap, org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getMethodElementProperty());	
	}
	
	private void loadModelClasses(Map classMap, EClass clazz) {

		String name = clazz.getName();
		if( classMap.containsKey(name) ) {
			return;
		}
		
		classMap.put(name, clazz);
			
		//for (Iterator it = clazz.getEAllStructuralFeatures().iterator(); it.hasNext(); ) {
		for (Iterator elements = ExtendedMetaData.INSTANCE.getAllElements(clazz).iterator(); 
				elements.hasNext(); ) {
			EStructuralFeature eStructuralFeature = (EStructuralFeature)elements.next();
			EClassifier eClassifier = eStructuralFeature.getEType();
			if (eClassifier instanceof EClass) {
				EClass eClass = (EClass)eClassifier;				
				// recursively find the nestedt features
				loadModelClasses(classMap, eClass);
			}
		}
	}

	/**
	 * @param clazz
	 * @param umaFeatureName
	 * @return the corresponding xml feature.
	 */
	public EStructuralFeature getXmlFeature(EClass clazz, String umaFeatureName) {
		return getXmlFeature(clazz, umaFeatureName, null);
	}
	
	/**
	 * @param umaClazz
	 * @param xmlFeatureName
	 * @return the corresponding EStructuralFeature object.
	 */
	public EStructuralFeature getRmcFeature(EClass umaClazz, String xmlFeatureName) {
		for ( Iterator it = umaClazz.getEAllStructuralFeatures().iterator(); it.hasNext(); )
		{
			EStructuralFeature feature = (EStructuralFeature)it.next();
			String umaFeaturename = feature.getName();
			
			// if the feature name is the same, got it
			if ( umaFeaturename.equals(xmlFeatureName) ) {
				return feature;
			}
			
			// check if the feature name is mapped to another xml feature name
			String featureName = (String)featureNameMap.get(umaFeaturename);
			if ( featureName != null ) {
				int i = featureName.indexOf(","); //$NON-NLS-1$
				if ( i > 0 ) {
					if ( featureName.substring(0,i).equals(xmlFeatureName) || 
						 featureName.substring(i+1).equals(xmlFeatureName)	) {
						return feature;
					}
				} else {
					if ( featureName.equals(xmlFeatureName) ) {
						return feature;
					}
				}				
			}
		}

		//System.out.print("FeatureManager: Unable to find uma feature for xml feature" + xmlFeatureName);
		
		return null;
	}
	
	/**
	 * @param clazz
	 * @param umaFeatureName
	 * @param objectType
	 * @return the corrsponding xml feature.
	 */
	public EStructuralFeature getXmlFeature(EClass clazz, String umaFeatureName, String objectType) {

		String featureName = umaFeatureName;
		if ( featureNameMap.containsKey(featureName) ) {
			featureName = (String)featureNameMap.get(featureName);
			
			// if the feature is not unique, use the first one
			int i = featureName.indexOf(","); //$NON-NLS-1$
			if ( i > 0 ) {
				String name = featureName.substring(0, i);
				if ( objectType != null ) {				
					// pick the feature based on object type
					if ( !name.equalsIgnoreCase(objectType) ) {
						name = featureName.substring(i+1);
					}
					if ( !name.equalsIgnoreCase(objectType) ) {
						name = null;
					}
				}
				
				featureName = name;
			}
		}
		
		if ( featureName == null ) {
			return null;
		}
		
		EStructuralFeature feature = clazz.getEStructuralFeature(featureName);
		if ( (feature==null) && (clazz.getName().equals("ContentCategoryPackage")) && featureName.equals("contentElement") ) { //$NON-NLS-1$ //$NON-NLS-2$
			feature = org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getContentCategoryPackage_ContentCategory();
		}

//		if ( feature == null ) {
//			System.out.println("No xml feature " + featureName + " for class " + clazz.getName());
//		}
		
		return feature;
	}
	
	/**
	 * @param umaEClassName
	 * @return the corresponding xml eclass
	 */
	public EClass getXmlEClass(String umaEClassName) {
//		EClass rootClass = ExtendedMetaData.INSTANCE.getDocumentRoot(org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE);
//		EClass eClass = (EClass)rootClass.getEStructuralFeature(umaEClassName);
//		return eClass;
		
		EClass cls = (EClass)xmlEClassMap.get(umaEClassName);
//		if ( cls == null ) {
//			System.out.println("No xml EClass for uma class " + umaEClassName);
//		}
		
		return cls;
	}
	
	/**
	 * @param xmlEClassName
	 * @return the corresponding EClass object.
	 */
	public EClass getRmcEClass(String xmlEClassName) {
		EClass cls = (EClass)umaEClassMap.get(xmlEClassName);
//		if ( cls == null ) {
//			System.out.println("No uma EClass for xml class " + xmlEClassName);
//		}
		
		return cls;
	}
	
	/**
	 * @param featurename
	 * @return true if the feature is unneeded given by featurename.
	 */
	public boolean isUnneededRmcFeature(String featurename) {
		return unneededRmcFeatures.contains(featurename);
	}
	
	/**
	 * @param featurename
	 * @return true if the xml feature is unneeded given by featurename.
	 */
	public boolean isUnneededXmlFeature(String featurename) {
		return unneededXmlFeatures.contains(featurename);
	}
	
	/**
	 * Prints the feature-name map.
	 */
	public void printFeatureNameMapping() {
		printFeature(UmaFactory.eINSTANCE.createActivity(), org.eclipse.epf.xml.uma.UmaFactory.eINSTANCE.createActivity());
		printFeature(UmaFactory.eINSTANCE.createActivityDescription(), org.eclipse.epf.xml.uma.UmaFactory.eINSTANCE.createActivityDescription());
		printFeature(UmaFactory.eINSTANCE.createArtifact(), org.eclipse.epf.xml.uma.UmaFactory.eINSTANCE.createArtifact());
		printFeature(UmaFactory.eINSTANCE.createArtifactDescription(), org.eclipse.epf.xml.uma.UmaFactory.eINSTANCE.createArtifactDescription());
		printFeature(UmaFactory.eINSTANCE.createBreakdownElementDescription(), org.eclipse.epf.xml.uma.UmaFactory.eINSTANCE.createBreakdownElementDescription());
		printFeature(UmaFactory.eINSTANCE.createCapabilityPattern(), org.eclipse.epf.xml.uma.UmaFactory.eINSTANCE.createCapabilityPattern());
		printFeature(UmaFactory.eINSTANCE.createChecklist(), org.eclipse.epf.xml.uma.UmaFactory.eINSTANCE.createChecklist());
		printFeature(UmaFactory.eINSTANCE.createCompositeRole(), org.eclipse.epf.xml.uma.UmaFactory.eINSTANCE.createCompositeRole());
		printFeature(UmaFactory.eINSTANCE.createConcept(), org.eclipse.epf.xml.uma.UmaFactory.eINSTANCE.createConcept());
		printFeature(UmaFactory.eINSTANCE.createConstraint(), org.eclipse.epf.xml.uma.UmaFactory.eINSTANCE.createConstraint());
		printFeature(UmaFactory.eINSTANCE.createContentDescription(), org.eclipse.epf.xml.uma.UmaFactory.eINSTANCE.createContentDescription());
		printFeature(UmaFactory.eINSTANCE.createContentPackage(), org.eclipse.epf.xml.uma.UmaFactory.eINSTANCE.createContentPackage());
		printFeature(UmaFactory.eINSTANCE.createCustomCategory(), org.eclipse.epf.xml.uma.UmaFactory.eINSTANCE.createCustomCategory());
		printFeature(UmaFactory.eINSTANCE.createDeliverable(), org.eclipse.epf.xml.uma.UmaFactory.eINSTANCE.createDeliverable());
		printFeature(UmaFactory.eINSTANCE.createDeliverableDescription(), org.eclipse.epf.xml.uma.UmaFactory.eINSTANCE.createDeliverableDescription());
		printFeature(UmaFactory.eINSTANCE.createDeliveryProcess(), org.eclipse.epf.xml.uma.UmaFactory.eINSTANCE.createDeliveryProcess());
		printFeature(UmaFactory.eINSTANCE.createDeliveryProcessDescription(), org.eclipse.epf.xml.uma.UmaFactory.eINSTANCE.createDeliveryProcessDescription());
		printFeature(UmaFactory.eINSTANCE.createDescriptorDescription(), org.eclipse.epf.xml.uma.UmaFactory.eINSTANCE.createDescriptorDescription());
		printFeature(UmaFactory.eINSTANCE.createDiscipline(), org.eclipse.epf.xml.uma.UmaFactory.eINSTANCE.createDiscipline());
		printFeature(UmaFactory.eINSTANCE.createDisciplineGrouping(), org.eclipse.epf.xml.uma.UmaFactory.eINSTANCE.createDisciplineGrouping());
		printFeature(UmaFactory.eINSTANCE.createDomain(), org.eclipse.epf.xml.uma.UmaFactory.eINSTANCE.createDomain());
		printFeature(UmaFactory.eINSTANCE.createEstimationConsiderations(), org.eclipse.epf.xml.uma.UmaFactory.eINSTANCE.createEstimationConsiderations());
		printFeature(UmaFactory.eINSTANCE.createExample(), org.eclipse.epf.xml.uma.UmaFactory.eINSTANCE.createExample());
		printFeature(UmaFactory.eINSTANCE.createGuidanceDescription(), org.eclipse.epf.xml.uma.UmaFactory.eINSTANCE.createGuidanceDescription());
		printFeature(UmaFactory.eINSTANCE.createGuideline(), org.eclipse.epf.xml.uma.UmaFactory.eINSTANCE.createGuideline());
		printFeature(UmaFactory.eINSTANCE.createIteration(), org.eclipse.epf.xml.uma.UmaFactory.eINSTANCE.createIteration());
		printFeature(UmaFactory.eINSTANCE.createMethodConfiguration(), org.eclipse.epf.xml.uma.UmaFactory.eINSTANCE.createMethodConfiguration());
		printFeature(UmaFactory.eINSTANCE.createMethodLibrary(), org.eclipse.epf.xml.uma.UmaFactory.eINSTANCE.createMethodLibrary());
		printFeature(UmaFactory.eINSTANCE.createMethodPlugin(), org.eclipse.epf.xml.uma.UmaFactory.eINSTANCE.createMethodPlugin());
		printFeature(UmaFactory.eINSTANCE.createMilestone(), org.eclipse.epf.xml.uma.UmaFactory.eINSTANCE.createMilestone());
		printFeature(UmaFactory.eINSTANCE.createOutcome(), org.eclipse.epf.xml.uma.UmaFactory.eINSTANCE.createOutcome());
		printFeature(UmaFactory.eINSTANCE.createPhase(), org.eclipse.epf.xml.uma.UmaFactory.eINSTANCE.createPhase());
		printFeature(UmaFactory.eINSTANCE.createPlanningData(), org.eclipse.epf.xml.uma.UmaFactory.eINSTANCE.createPlanningData());
		printFeature(UmaFactory.eINSTANCE.createPractice(), org.eclipse.epf.xml.uma.UmaFactory.eINSTANCE.createPractice());
		printFeature(UmaFactory.eINSTANCE.createPracticeDescription(), org.eclipse.epf.xml.uma.UmaFactory.eINSTANCE.createPracticeDescription());
		printFeature(UmaFactory.eINSTANCE.createProcessComponent(), org.eclipse.epf.xml.uma.UmaFactory.eINSTANCE.createProcessComponent());
		printFeature(UmaFactory.eINSTANCE.createProcessDescription(), org.eclipse.epf.xml.uma.UmaFactory.eINSTANCE.createProcessDescription());
		printFeature(UmaFactory.eINSTANCE.createProcessPackage(), org.eclipse.epf.xml.uma.UmaFactory.eINSTANCE.createProcessPackage());
		printFeature(UmaFactory.eINSTANCE.createProcessPlanningTemplate(), org.eclipse.epf.xml.uma.UmaFactory.eINSTANCE.createProcessPlanningTemplate());
		printFeature(UmaFactory.eINSTANCE.createReport(), org.eclipse.epf.xml.uma.UmaFactory.eINSTANCE.createReport());
		printFeature(UmaFactory.eINSTANCE.createReusableAsset(), org.eclipse.epf.xml.uma.UmaFactory.eINSTANCE.createReusableAsset());
		printFeature(UmaFactory.eINSTANCE.createRoadmap(), org.eclipse.epf.xml.uma.UmaFactory.eINSTANCE.createRoadmap());
		printFeature(UmaFactory.eINSTANCE.createRole(), org.eclipse.epf.xml.uma.UmaFactory.eINSTANCE.createRole());
		printFeature(UmaFactory.eINSTANCE.createRoleDescription(), org.eclipse.epf.xml.uma.UmaFactory.eINSTANCE.createRoleDescription());
		printFeature(UmaFactory.eINSTANCE.createRoleDescriptor(), org.eclipse.epf.xml.uma.UmaFactory.eINSTANCE.createRoleDescriptor());
		printFeature(UmaFactory.eINSTANCE.createRoleSet(), org.eclipse.epf.xml.uma.UmaFactory.eINSTANCE.createRoleSet());
		printFeature(UmaFactory.eINSTANCE.createRoleSetGrouping(), org.eclipse.epf.xml.uma.UmaFactory.eINSTANCE.createRoleSetGrouping());
		printFeature(UmaFactory.eINSTANCE.createSection(), org.eclipse.epf.xml.uma.UmaFactory.eINSTANCE.createSection());
		printFeature(UmaFactory.eINSTANCE.createSupportingMaterial(), org.eclipse.epf.xml.uma.UmaFactory.eINSTANCE.createSupportingMaterial());
		printFeature(UmaFactory.eINSTANCE.createTask(), org.eclipse.epf.xml.uma.UmaFactory.eINSTANCE.createTask());
		printFeature(UmaFactory.eINSTANCE.createTaskDescription(), org.eclipse.epf.xml.uma.UmaFactory.eINSTANCE.createTaskDescription());
		printFeature(UmaFactory.eINSTANCE.createTaskDescriptor(), org.eclipse.epf.xml.uma.UmaFactory.eINSTANCE.createTaskDescriptor());
		printFeature(UmaFactory.eINSTANCE.createTeamProfile(), org.eclipse.epf.xml.uma.UmaFactory.eINSTANCE.createTeamProfile());
		printFeature(UmaFactory.eINSTANCE.createTemplate(), org.eclipse.epf.xml.uma.UmaFactory.eINSTANCE.createTemplate());
		printFeature(UmaFactory.eINSTANCE.createTermDefinition(), org.eclipse.epf.xml.uma.UmaFactory.eINSTANCE.createTermDefinition());
		printFeature(UmaFactory.eINSTANCE.createTool(), org.eclipse.epf.xml.uma.UmaFactory.eINSTANCE.createTool());
		printFeature(UmaFactory.eINSTANCE.createToolMentor(), org.eclipse.epf.xml.uma.UmaFactory.eINSTANCE.createToolMentor());
		printFeature(UmaFactory.eINSTANCE.createWhitepaper(), org.eclipse.epf.xml.uma.UmaFactory.eINSTANCE.createWhitepaper());
		printFeature(UmaFactory.eINSTANCE.createWorkOrder(), org.eclipse.epf.xml.uma.UmaFactory.eINSTANCE.createWorkOrder());
		printFeature(UmaFactory.eINSTANCE.createWorkProductDescription(), org.eclipse.epf.xml.uma.UmaFactory.eINSTANCE.createWorkProductDescription());
		printFeature(UmaFactory.eINSTANCE.createWorkProductDescriptor(), org.eclipse.epf.xml.uma.UmaFactory.eINSTANCE.createWorkProductDescriptor());
		printFeature(UmaFactory.eINSTANCE.createWorkProductType(), org.eclipse.epf.xml.uma.UmaFactory.eINSTANCE.createWorkProductType());
//		printFeature(UmaFactory.eINSTANCE.create(), org.eclipse.epf.xml.uma.UmaFactory.eINSTANCE.create());
//		printFeature(UmaFactory.eINSTANCE.create(), org.eclipse.epf.xml.uma.UmaFactory.eINSTANCE.create());


	}
	
	
	private void printFeature(EObject obj1, EObject obj2) {
		List matched = new ArrayList();
		List unmatched = new ArrayList();
		List umaFeatures = new ArrayList();
				
		EList features = obj1.eClass().getEAllStructuralFeatures();
		for ( Iterator it = features.iterator(); it.hasNext(); ) {
			EStructuralFeature feature = (EStructuralFeature)it.next();
			String name = feature.getName();
			if ( featureNameMap.containsKey(name) ) {
				String xml_name = (String) featureNameMap.get(name);
				int indx = xml_name.indexOf(","); //$NON-NLS-1$
				if ( indx > 0 ) {
					matched.add(xml_name.substring(0, indx));
					matched.add(xml_name.substring(indx+1).trim());					
				}
				else if ( !matched.contains(xml_name) ) {
					matched.add(xml_name);
				}
			}
			else if (!unneededRmcFeatures.contains(name) && !umaFeatures.contains(name) ) {
				umaFeatures.add(name);
			}
		}			
		
		features = obj2.eClass().getEAllStructuralFeatures();
		for ( Iterator it = features.iterator(); it.hasNext(); ) {
			EStructuralFeature feature = (EStructuralFeature)it.next();
			String name = feature.getName();
			if ( umaFeatures.contains(name) ) {
				umaFeatures.remove(name);
			} else if (!unneededXmlFeatures.contains(name) && !matched.contains(name) && !unmatched.contains(name) ) {
				unmatched.add(name);
			}
		}	
		
		if ( umaFeatures.size() + unmatched.size() == 0 ) {
			return;
		}
		
		System.out.println("============== un-matched features for " + obj1.eClass().getName() + " =================="); //$NON-NLS-1$ //$NON-NLS-2$
		for ( Iterator it = umaFeatures.iterator(); it.hasNext(); ) {
			String name = (String)it.next();
			System.out.println("\tfeatureNameMap.put(\"" + name + "\", \"\");"); //$NON-NLS-1$ //$NON-NLS-2$
			//System.out.println(name);
		}
		for ( Iterator it = unmatched.iterator(); it.hasNext(); ) {
			String name = (String)it.next();
			System.out.println("\t\t" + name); //$NON-NLS-1$
		}

		System.out.println(""); //$NON-NLS-1$
		
	}
	
//	public class FeatureMap {
//		private Map featureMap = new HashMap();
//		
//		FeatureMap(EClass clazz) {
//			EList features = clazz.getEAllStructuralFeatures();
//			for ( Iterator it = features.iterator(); it.hasNext(); ) {
//				EStructuralFeature feature = (EStructuralFeature)it.next();
//				featureMap.put(feature.getName(), feature);
//			}			
//		}
//		
//		public EStructuralFeature getFeature(String featureName) {
//			return (EStructuralFeature) featureMap.get(featureName);			
//		}
//	}
}
