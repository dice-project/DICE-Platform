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

package org.eclipse.epf.library.edit.itemsfilter;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.epf.uma.Checklist;
import org.eclipse.epf.uma.Concept;
import org.eclipse.epf.uma.Discipline;
import org.eclipse.epf.uma.DisciplineGrouping;
import org.eclipse.epf.uma.Domain;
import org.eclipse.epf.uma.EstimationConsiderations;
import org.eclipse.epf.uma.Example;
import org.eclipse.epf.uma.Guideline;
import org.eclipse.epf.uma.Practice;
import org.eclipse.epf.uma.Report;
import org.eclipse.epf.uma.ReusableAsset;
import org.eclipse.epf.uma.Roadmap;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.RoleSet;
import org.eclipse.epf.uma.RoleSetGrouping;
import org.eclipse.epf.uma.SupportingMaterial;
import org.eclipse.epf.uma.Task;
import org.eclipse.epf.uma.Template;
import org.eclipse.epf.uma.TermDefinition;
import org.eclipse.epf.uma.ToolMentor;
import org.eclipse.epf.uma.Whitepaper;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.WorkProductType;

/**
 * A helper class for setting up the necessary maps used for element filtering
 * in the method element selection dialogs.
 * 
 * @author Shashidhar Kannoori
 * @since 1.0
 */
public final class FilterInitializer {

	private static FilterInitializer initializer;

	private static Map map;

	/**
	 * Creates a new instance.
	 */
	public FilterInitializer() {
		super();
		loadTypeClass();
	}

	/*
	 * Singleton.
	 */
	public static FilterInitializer getInstance() {
		if (initializer == null) {
			synchronized (FilterInitializer.class) {
				if (initializer == null) {
					initializer = new FilterInitializer();
				}
			}
		}
		return initializer;
	}

	/*
	 * Load the Filter Types and respective classes. This need in case of i118n -
	 * Constants values are different.
	 * 
	 */
	private void loadTypeClass() {
		map = new HashMap();
		map.put(FilterConstants.WORKPRODUCTTYPES, WorkProductType.class);
		map.put(FilterConstants.DOMAINS, Domain.class);
		map.put(FilterConstants.WORKPRODUCTS, WorkProduct.class);
		map.put(FilterConstants.WORKPRODUCTTYPES, WorkProductType.class);
		map.put(FilterConstants.DOMAINS, Domain.class);
		map.put(FilterConstants.WORKPRODUCTS, WorkProduct.class);
		map.put(FilterConstants.DISCIPLINE_GROUPINGS, DisciplineGrouping.class);
		map.put(FilterConstants.DISCIPLINES, Discipline.class);
		map.put(FilterConstants.TASKS, Task.class);
		map.put(FilterConstants.ROLE_SET_GROUPINGS, RoleSetGrouping.class);
		map.put(FilterConstants.ROLESETS, RoleSet.class);
		map.put(FilterConstants.ROLES, Role.class);
		map.put(FilterConstants.CHECKLISTS, Checklist.class);
		map.put(FilterConstants.CONCEPTS, Concept.class);
		map.put(FilterConstants.ESTIMATE_CONSIDERATIONS,
				EstimationConsiderations.class);
		map.put(FilterConstants.EXAMPLES, Example.class);
		map.put(FilterConstants.GUIDELINES, Guideline.class);
		map.put(FilterConstants.PRACTICES, Practice.class);
		map.put(FilterConstants.REPORTS, Report.class);
		map.put(FilterConstants.REUSABLE_ASSETS, ReusableAsset.class);
		map.put(FilterConstants.ROADMAP, Roadmap.class);
		map.put(FilterConstants.SUPPORTING_MATERIALS, SupportingMaterial.class);
		map.put(FilterConstants.TEMPLATES, Template.class);
		map.put(FilterConstants.TERM_DEFINITIONS, TermDefinition.class);
		map.put(FilterConstants.TOOL_MENTORS, ToolMentor.class);
		map.put(FilterConstants.WHITE_PAPERS, Whitepaper.class);
		// map.put(FilterConstants.GUIDANCE, Guidance.class);
	}

	/*
	 * Returns the Class for a type from the map.
	 */
	public Class getClassForType(String type) {
		Object obj = map.get(type);
		return (Class) obj;
	}
}
