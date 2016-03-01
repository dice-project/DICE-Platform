//------------------------------------------------------------------------------
// Copyright (c) 2005, 2007 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.search.ui.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.library.edit.util.PracticePropUtil;
import org.eclipse.epf.library.ui.LibraryUIText;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.UmaPackage;

/**
 * The method search scope.
 * 
 * @author Kelvin Low
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class MethodSearchScope {

	public static final String ROOT = "Root"; //$NON-NLS-1$

	public static final String METHOD_CONTENT = LibraryUIText.TEXT_METHOD_CONTENT;

	public static final String ROLE = LibraryUIText.TEXT_ROLE;

	public static final String TASK = LibraryUIText.TEXT_TASK;

	public static final String WORK_PRODUCT = LibraryUIText.TEXT_WORK_PRODUCT;

	public static final String GUIDANCE = LibraryUIText.TEXT_GUIDANCE;

	public static final String UDT = LibraryUIText.TEXT_UDT;
	
	public static final String CHECKLIST = LibraryUIText.TEXT_CHECKLIST;

	public static final String CONCEPT = LibraryUIText.TEXT_CONCEPT;

	public static final String ESTIMATION_CONSIDERATIONS = LibraryUIText.TEXT_ESTIMATION_CONSIDERATIONS;

	public static final String EXAMPLE = LibraryUIText.TEXT_EXAMPLE;

	public static final String GUIDELINE = LibraryUIText.TEXT_GUIDELINE;

	public static final String PRACTICE = LibraryUIText.TEXT_PRACTICE;

	public static final String REPORT = LibraryUIText.TEXT_REPORT;

	public static final String REUSABLE_ASSET = LibraryUIText.TEXT_REUSABLE_ASSET;

	public static final String ROADMAP = LibraryUIText.TEXT_ROADMAP;

	public static final String SUPPORTING_MATERIAL = LibraryUIText.TEXT_SUPPORTING_MATERIAL;

	public static final String TEMPLATE = LibraryUIText.TEXT_TEMPLATE;

	public static final String TERM_DEFINITION = LibraryUIText.TEXT_TERM_DEFINITION;

	public static final String TOOL_MENTOR = LibraryUIText.TEXT_TOOL_MENTOR;

	public static final String WHITEPAPER = LibraryUIText.TEXT_WHITEPAPER;

	public static final String STANDARD_CATEGORY = LibraryUIText.TEXT_STANDARD_CATEGORY;

	public static final String CUSTOM_CATEGORY = LibraryUIText.TEXT_CUSTOM_CATEGORY;

	public static final String PROCESS = LibraryUIText.TEXT_PROCESS;

	public static final String CAPABILITY_PATTERN = LibraryUIText.TEXT_CAPABILITY_PATTERN;

	public static final String DELIVERY_PROCESS = LibraryUIText.TEXT_DELIVERY_PROCESS;

	public static final Map<EClass, String> elementSearchScope = new HashMap<EClass, String>();
	
	private static final Map<String, Collection<EClass>> scopeToEClassesMap = new HashMap<String, Collection<EClass>>();

	public static EClass UDTeClass = EcoreFactory.eINSTANCE.createEClass();
	
	static {
		elementSearchScope.put(UDTeClass, UDT);
		elementSearchScope.put(UmaPackage.eINSTANCE.getMethodPlugin(), ROOT);
		elementSearchScope.put(UmaPackage.eINSTANCE.getContentPackage(), METHOD_CONTENT);
		elementSearchScope.put(UmaPackage.eINSTANCE.getRole(), ROLE);
		elementSearchScope.put(UmaPackage.eINSTANCE.getTask(), TASK);
		elementSearchScope.put(UmaPackage.eINSTANCE.getArtifact(), WORK_PRODUCT);
		elementSearchScope.put(UmaPackage.eINSTANCE.getDeliverable(), WORK_PRODUCT);
		elementSearchScope.put(UmaPackage.eINSTANCE.getOutcome(), WORK_PRODUCT);
		elementSearchScope.put(UmaPackage.eINSTANCE.getGuidance(), GUIDANCE);
		elementSearchScope.put(UmaPackage.eINSTANCE.getChecklist(), CHECKLIST);
		elementSearchScope.put(UmaPackage.eINSTANCE.getConcept(), CONCEPT);
		elementSearchScope.put(UmaPackage.eINSTANCE.getEstimationConsiderations(),
				ESTIMATION_CONSIDERATIONS);
		elementSearchScope.put(UmaPackage.eINSTANCE.getExample(), EXAMPLE);
		elementSearchScope.put(UmaPackage.eINSTANCE.getGuideline(), GUIDELINE);
		elementSearchScope.put(UmaPackage.eINSTANCE.getPractice(), PRACTICE);
		elementSearchScope.put(UmaPackage.eINSTANCE.getReport(), REPORT);
		elementSearchScope.put(UmaPackage.eINSTANCE.getReusableAsset(), REUSABLE_ASSET);
		elementSearchScope.put(UmaPackage.eINSTANCE.getRoadmap(), ROADMAP);
		elementSearchScope.put(UmaPackage.eINSTANCE.getSupportingMaterial(),
				SUPPORTING_MATERIAL);
		elementSearchScope.put(UmaPackage.eINSTANCE.getTemplate(), TEMPLATE);
		elementSearchScope.put(UmaPackage.eINSTANCE.getTermDefinition(), TERM_DEFINITION);
		elementSearchScope.put(UmaPackage.eINSTANCE.getToolMentor(), TOOL_MENTOR);
		elementSearchScope.put(UmaPackage.eINSTANCE.getWhitepaper(), WHITEPAPER);
		elementSearchScope.put(UmaPackage.eINSTANCE.getContentCategory(), STANDARD_CATEGORY);
		elementSearchScope.put(UmaPackage.eINSTANCE.getDiscipline(), STANDARD_CATEGORY);
		elementSearchScope.put(UmaPackage.eINSTANCE.getDisciplineGrouping(), STANDARD_CATEGORY);
		elementSearchScope.put(UmaPackage.eINSTANCE.getDomain(), STANDARD_CATEGORY);
		elementSearchScope.put(UmaPackage.eINSTANCE.getWorkProductType(), STANDARD_CATEGORY);
		elementSearchScope.put(UmaPackage.eINSTANCE.getRoleSet(), STANDARD_CATEGORY);
		elementSearchScope.put(UmaPackage.eINSTANCE.getRoleSetGrouping(), STANDARD_CATEGORY);
		elementSearchScope.put(UmaPackage.eINSTANCE.getTool(), STANDARD_CATEGORY);
		elementSearchScope.put(UmaPackage.eINSTANCE.getCustomCategory(), CUSTOM_CATEGORY);
		elementSearchScope.put(UmaPackage.eINSTANCE.getProcessPackage(), PROCESS);
		elementSearchScope.put(UmaPackage.eINSTANCE.getProcessComponent(), PROCESS);
		elementSearchScope.put(UmaPackage.eINSTANCE.getPhase(), PROCESS);
		elementSearchScope.put(UmaPackage.eINSTANCE.getIteration(), PROCESS);
		elementSearchScope.put(UmaPackage.eINSTANCE.getMilestone(), PROCESS);
		elementSearchScope.put(UmaPackage.eINSTANCE.getActivity(), PROCESS);
		elementSearchScope.put(UmaPackage.eINSTANCE.getRoleDescriptor(), PROCESS);
		elementSearchScope.put(UmaPackage.eINSTANCE.getTaskDescriptor(), PROCESS);
		elementSearchScope.put(UmaPackage.eINSTANCE.getWorkProductDescriptor(), PROCESS);
		elementSearchScope.put(UmaPackage.eINSTANCE.getCapabilityPattern(), CAPABILITY_PATTERN);
		elementSearchScope.put(UmaPackage.eINSTANCE.getDeliveryProcess(), DELIVERY_PROCESS);
		
		scopeToEClassesMap.put(ROLE, Collections.singleton(UmaPackage.eINSTANCE.getRole()));
		scopeToEClassesMap.put(TASK, Collections.singleton(UmaPackage.eINSTANCE.getTask()));
		scopeToEClassesMap.put(WORK_PRODUCT, Arrays.asList(new EClass[] {
				UmaPackage.eINSTANCE.getArtifact(),
				UmaPackage.eINSTANCE.getDeliverable(),
				UmaPackage.eINSTANCE.getOutcome()
		}));
		scopeToEClassesMap.put(CHECKLIST, Collections.singleton(UmaPackage.eINSTANCE.getChecklist()));
		scopeToEClassesMap.put(CONCEPT, Collections.singleton(UmaPackage.eINSTANCE.getConcept()));
		scopeToEClassesMap.put(ESTIMATION_CONSIDERATIONS, Collections.singleton(UmaPackage.eINSTANCE.getEstimationConsiderations()));
		scopeToEClassesMap.put(EXAMPLE, Collections.singleton(UmaPackage.eINSTANCE.getEstimationConsiderations()));
		scopeToEClassesMap.put(GUIDELINE,  Collections.singleton(UmaPackage.eINSTANCE.getGuideline()));
		scopeToEClassesMap.put(PRACTICE, Collections.singleton(UmaPackage.eINSTANCE.getPractice()));
		scopeToEClassesMap.put(REPORT, Collections.singleton(UmaPackage.eINSTANCE.getReport()));
		scopeToEClassesMap.put(REUSABLE_ASSET, Collections.singleton(UmaPackage.eINSTANCE.getReusableAsset()));
		scopeToEClassesMap.put(ROADMAP, Collections.singleton(UmaPackage.eINSTANCE.getRoadmap()));
		scopeToEClassesMap.put(SUPPORTING_MATERIAL, Collections.singleton(UmaPackage.eINSTANCE.getSupportingMaterial()));
		scopeToEClassesMap.put(TEMPLATE, Collections.singleton(UmaPackage.eINSTANCE.getTemplate()));
		scopeToEClassesMap.put(TERM_DEFINITION, Collections.singleton(UmaPackage.eINSTANCE.getTermDefinition()));
		scopeToEClassesMap.put(TOOL_MENTOR, Collections.singleton(UmaPackage.eINSTANCE.getToolMentor()));
		scopeToEClassesMap.put(WHITEPAPER, Collections.singleton(UmaPackage.eINSTANCE.getWhitepaper()));
		scopeToEClassesMap.put(STANDARD_CATEGORY, Arrays.asList(new EClass[] {
				UmaPackage.eINSTANCE.getDiscipline(),
				UmaPackage.eINSTANCE.getDisciplineGrouping(),
				UmaPackage.eINSTANCE.getDomain(),
				UmaPackage.eINSTANCE.getWorkProductType(),
				UmaPackage.eINSTANCE.getRoleSet(),
				UmaPackage.eINSTANCE.getRoleSetGrouping(),
				UmaPackage.eINSTANCE.getTool()
		}));
		scopeToEClassesMap.put(CUSTOM_CATEGORY, Collections.singleton(UmaPackage.eINSTANCE.getCustomCategory()));
		scopeToEClassesMap.put(CAPABILITY_PATTERN, Collections.singleton(UmaPackage.eINSTANCE.getCapabilityPattern()));
		scopeToEClassesMap.put(DELIVERY_PROCESS, Collections.singleton(UmaPackage.eINSTANCE.getDeliveryProcess()));

	}
	
	public static final MethodSearchScope INCLUDE_ALL_SEARCH_SCOPE = new MethodSearchScope(scopeToEClassesMap.keySet().toArray());

	private List<Object> searchScope = new ArrayList<Object>();

	private ArrayList<EClass> selectedTypes;
	
	/**
	 * Creates a new instance.
	 */
	public MethodSearchScope(Object[] elementTypes) {
		this(elementTypes, true);
	}
	
	public MethodSearchScope(Object[] elementTypes, boolean includeRoot) {
		if(includeRoot) {
			searchScope.add(ROOT);
		}
		for (int i = 0; i < elementTypes.length; i++) {
			Object elementType = elementTypes[i];
			searchScope.add(elementType);
		}
	}
	
	/**
	 * Checks whether a core method element is included in the search scope.
	 * 
	 * @return <code>true</code> if a core method element is included in the
	 *         search scope, <code>false</code> otherwise
	 */
	public boolean includeCoreContent() {
		return searchScope.contains(ROLE) || searchScope.contains(TASK)
				|| searchScope.contains(WORK_PRODUCT)
				|| searchScope.contains(GUIDANCE)
				|| searchScope.contains(UDT)
				|| searchScope.contains(CHECKLIST)
				|| searchScope.contains(CONCEPT)
				|| searchScope.contains(ESTIMATION_CONSIDERATIONS)
				|| searchScope.contains(EXAMPLE)
				|| searchScope.contains(GUIDELINE)
				|| searchScope.contains(PRACTICE)
				|| searchScope.contains(REPORT)
				|| searchScope.contains(REUSABLE_ASSET)
				|| searchScope.contains(ROADMAP)
				|| searchScope.contains(SUPPORTING_MATERIAL)
				|| searchScope.contains(TEMPLATE)
				|| searchScope.contains(TERM_DEFINITION)
				|| searchScope.contains(TOOL_MENTOR)
				|| searchScope.contains(WHITEPAPER);
	}

	/**
	 * Checks whether the given Method element is included in the search scope.
	 * 
	 * @param element
	 *            a method element
	 * @return <code>true</code> if the method element is included in the
	 *         search scope, <code>false</code> otherwise
	 */
	public boolean include(MethodElement element) {
		if (element == null)
			return false;
		String searchScopeName = (String) elementSearchScope.get(element
				.eClass());
		if (PracticePropUtil.getPracticePropUtil().isUdtType(element)) {
			searchScopeName = UDT;
		}
		return searchScope.contains(searchScopeName);
	}

	/**
	 * Checks whether the given method element type is included in the search
	 * scope.
	 * 
	 * @return <code>true</code> if the given method element type is included
	 *         in the search scope, <code>false</code> otherwise
	 */
	public boolean include(String searchScopeName) {
		return searchScope.contains(searchScopeName);
	}

	public Collection<EClass> getSelectedTypes() {
		if (selectedTypes == null) {
			selectedTypes = new ArrayList<EClass>();
			for (Map.Entry<String, Collection<EClass>> entry : scopeToEClassesMap
					.entrySet()) {
				if (searchScope.contains(entry.getKey())) {
					selectedTypes.addAll(entry.getValue());
				}
			}
		}
		return selectedTypes;
	}
	
	public static String getTypeText(String typeName) {
		EClassifier cls = UmaPackage.eINSTANCE.getEClassifier(typeName);
		if (typeName.equals(UDT)) {
			return UDT;
		}
		return elementSearchScope.get(cls);
	}
	
	@Override
	public String toString() {
		if(searchScope == null && searchScope.isEmpty()) {
			return StrUtil.EMPTY_STRING;
		}
		StringBuffer strBuffer = new StringBuffer();
		int max = searchScope.size() - 1;
		for(int i = 0; i < max; i++) {
			strBuffer.append(searchScope.get(i)).append(',');
		}
		return strBuffer.append(searchScope.get(max)).toString();
	}
}
