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

import java.util.Iterator;

import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.configuration.CategoriesItemProvider;
import org.eclipse.epf.library.edit.configuration.UncategorizedItemProvider;
import org.eclipse.epf.library.edit.element.RoleItemProvider;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.RoleSet;
import org.eclipse.epf.uma.RoleSetGrouping;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.jface.viewers.TableViewer;


/**
 * Filters {@link RoleSet}, {@link RoleSetGrouping} in specific {@link MethodConfiguration}
 * based on search pattern. 
 * 
 * @author Shashidhar Kannoori
 * @since 1.0
 */
public class ProcessRoleFilter extends DescriptorConfigurationFilter {

	public ProcessRoleFilter(MethodConfiguration config, TableViewer viewer,
			String tabName) {
		super(config, viewer, tabName);
	}

	public boolean childAccept(Object obj) {
		if (obj instanceof CategoriesItemProvider) {
			if (((CategoriesItemProvider) obj).getText(obj).equalsIgnoreCase(
					LibraryEditPlugin.INSTANCE.getString("_UI_Role_Sets_group"))) //$NON-NLS-1$
				return true;
		}
		if ((obj instanceof RoleItemProvider) || (obj instanceof Role)
				|| (obj instanceof UncategorizedItemProvider))
			return true;
		
		if(obj instanceof RoleSetGrouping){
			Iterator iter = (TngUtil.getContributors((VariabilityElement)obj));
			if(iter != null){
				while(iter.hasNext()){
					RoleSetGrouping grouping = (RoleSetGrouping)iter.next();
					// TODO : Check for (recursive)RoleSets is empty, 
					// if roleset's roles are empty donot show roleset grouping 
					if(!grouping.getRoleSets().isEmpty()) return true;
				}
			}
			// TODO : Check for (recursive)RoleSets is empty, 
			// if roleset's roles are empty donot show Discipline grouping 
			return !((RoleSetGrouping)obj).getRoleSets().isEmpty();
		}
		if(obj instanceof RoleSet){
			// get roleset roles first 
			if (!((RoleSet)obj).getRoles().isEmpty()) 
				// if there are roles, return true
				return true;
			else {
				// else check if there are any contributor roles
				Iterator roleSetContributors = TngUtil.getContributors((RoleSet) obj);
				if (roleSetContributors != null) {
					while (roleSetContributors.hasNext()) {
						RoleSet roleSetContributor = (RoleSet) roleSetContributors.next();
						if (!(roleSetContributor.getRoles().isEmpty())) 
							return true;
					}
				}
				return false;
			}
		}
		else
			return false;
	}
}
