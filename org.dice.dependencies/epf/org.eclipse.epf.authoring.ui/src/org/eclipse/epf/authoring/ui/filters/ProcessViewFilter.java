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
package org.eclipse.epf.authoring.ui.filters;

import org.eclipse.epf.authoring.ui.dialogs.ItemsFilterDialog;
import org.eclipse.epf.library.edit.configuration.CategoriesItemProvider;
import org.eclipse.epf.library.edit.itemsfilter.FilterConstants;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.Discipline;
import org.eclipse.epf.uma.DisciplineGrouping;
import org.eclipse.epf.uma.Domain;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.RoleSet;
import org.eclipse.epf.uma.RoleSetGrouping;
import org.eclipse.epf.uma.Tool;
import org.eclipse.epf.uma.WorkProductType;
import org.eclipse.jface.viewers.Viewer;


/**
 * Filter {@link Process}es within {@link MethodConfiguration} used in
 * {@link ItemsFilterDialog}.
 * @author Shashidhar Kannoori
 * @since 1.0
 */
public class ProcessViewFilter extends DescriptorConfigurationFilter {

	public ProcessViewFilter(MethodConfiguration methodConfig, Viewer viewer,
			String filterStr) {
		super(methodConfig, viewer, filterStr);
	}

	public boolean childAccept(Object obj) {
		String filterType = helper.getFilterTypeStr();
		if (obj instanceof CategoriesItemProvider) {
			return true;
		}
		if (filterType.equalsIgnoreCase(FilterConstants.ROLESETS)) {
			return (obj instanceof RoleSet || obj instanceof RoleSetGrouping);
		} else if (filterType.equalsIgnoreCase(FilterConstants.DISCIPLINES)) {
			return (obj instanceof Discipline || obj instanceof DisciplineGrouping);
		} else if (filterType.equalsIgnoreCase(FilterConstants.DOMAINS)) {
			return (obj instanceof Domain);
		} else if (filterType
				.equalsIgnoreCase(FilterConstants.WORKPRODUCTTYPES)) {
			return (obj instanceof WorkProductType);
		} else if (filterType.equalsIgnoreCase(FilterConstants.TOOLS)) {
			return (obj instanceof Tool);
		} else if (filterType
				.equalsIgnoreCase(FilterConstants.CUSTOM_CATEGORIES)) {
			return obj instanceof CustomCategory;
		} else if (filterType.equalsIgnoreCase(FilterConstants.ALL_ELEMENTS)) {
			return obj instanceof RoleSet || obj instanceof Discipline
					|| obj instanceof Domain || obj instanceof WorkProductType
					|| obj instanceof Tool || obj instanceof CustomCategory
					|| obj instanceof DisciplineGrouping
					|| obj instanceof RoleSetGrouping;
		}
		return false;
	}

}
