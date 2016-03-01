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
import org.eclipse.epf.library.edit.element.TaskItemProvider;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Discipline;
import org.eclipse.epf.uma.DisciplineGrouping;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.Task;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.jface.viewers.TableViewer;


/**
 * Filters {@link Task}s in {@link MethodConfiguration} based on search pattern.
 * 
 * @author Shashidhar Kannoori
 * @since 1.0
 */
public class ProcessTaskFilter extends DescriptorConfigurationFilter {

	public ProcessTaskFilter(MethodConfiguration config, TableViewer viewer,
			String tabName) {
		super(config, viewer, tabName);
	}

	public boolean childAccept(Object obj) {
		if (obj instanceof CategoriesItemProvider) {
			if (((CategoriesItemProvider) obj).getText(obj).equalsIgnoreCase(
					LibraryEditPlugin.INSTANCE
							.getString("_UI_Disciplines_group"))) //$NON-NLS-1$
				return true;
		}
		if ((obj instanceof TaskItemProvider) || (obj instanceof Task)
				|| (obj instanceof UncategorizedItemProvider))
			return true;
		if(obj instanceof DisciplineGrouping){
			Iterator iter = (TngUtil.getContributors((VariabilityElement)obj));
			if(iter != null){
				while(iter.hasNext()){
					DisciplineGrouping grouping = (DisciplineGrouping)iter.next();
					// TODO : Check for (recursive)Discipline is empty, 
					// if discipline's tasks are empty donot show Discipline grouping 
					if(!grouping.getDisciplines().isEmpty()) return true;
				}
			}
			// TODO : Check for (recursive)Discipline is empty, 
			// if discipline's tasks are empty donot show Discipline grouping 
			return !((DisciplineGrouping)obj).getDisciplines().isEmpty();
		}
		if(obj instanceof Discipline){
			Iterator iter = (TngUtil.getContributors((VariabilityElement)obj));
			if(iter != null){
				while(iter.hasNext()){
					Discipline discipline = (Discipline)iter.next();
					// TODO : Check for (recursive)Discipline is empty, 
					// if discipline's tasks are empty donot show Discipline 
					if(!discipline.getTasks().isEmpty()) return true;
				}
			}
			return !((Discipline)obj).getTasks().isEmpty();
		}
			return false;
	}

}
