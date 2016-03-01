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
package org.eclipse.epf.library.edit.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.AbstractTreeIterator;
import org.eclipse.epf.uma.Section;
import org.eclipse.epf.uma.Task;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.util.AssociationHelper;


/**
 * This class defines static helper methods that are related to content element
 * inheritance.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public final class ContentElementInheritanceHelper {
	
	private static List descendingSortByCreationTime(List tasks) {
		return tasks;
	}

	// private static void addSteps(List steps, Task base) {
	// Object first = null;
	// if(!steps.isEmpty()) {
	// first = steps.get(0);
	// }
	// List tasks = AssociationHelper.getImmediateVarieties(base);
	// int size = tasks.size();
	// for(int i = 0; i < size; i++) {
	// Task task = (Task) tasks.get(i);
	// List sections = task.getPresentation().getSections();
	// int numOfSections = sections.size();
	// Section prev = null;
	// for (int j = 0; j < numOfSections; j++) {
	// Section section = (Section) sections.get(j);
	// Section pred = section.getPredecessor();
	// if(pred == null) {
	// }
	// }
	// }
	// }

	/**
	 * Gets all steps in the task tree that the given task belongs to
	 * 
	 * @param task
	 * @return
	 */
	public static List getAllTaskSteps(Task task) {
		Task base = (Task) TngUtil.getBase(task);
		List steps = new ArrayList(base.getPresentation().getSections());
		Iterator tasks = new AbstractTreeIterator(base, false) {

			protected Iterator getChildren(Object object) {
				return AssociationHelper.getImmediateVarieties(
						(VariabilityElement) object).iterator();
			}

		};
		Task currentBase = base;
		while (tasks.hasNext()) {
			Task t = (Task) tasks.next();
			if (currentBase != t.getVariabilityBasedOnElement()) {
				// new inheritance level
				//
				currentBase = (Task) t.getVariabilityBasedOnElement();

			}
			for (Iterator iter = t.getPresentation().getSections().iterator(); iter
					.hasNext();) {
				Section s = (Section) iter.next();
				Section pred = s.getPredecessor();
				if (pred != null) {

				}
			}
		}
		return steps;
	}

}
