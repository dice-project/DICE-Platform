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
package org.eclipse.epf.search.ui.internal;

import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.ContentCategory;
import org.eclipse.epf.uma.ContentElement;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.Guidance;
import org.eclipse.epf.uma.Iteration;
import org.eclipse.epf.uma.MethodPackage;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.Milestone;
import org.eclipse.epf.uma.Phase;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessElement;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.Task;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.WorkProductDescriptor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

/**
 * Sorts the method elements in a specific order for display in the Search
 * view.
 * <p>
 * The method elements are sorted by types. Within each type, the elements are
 * sorted alphabetically by names.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class MethodElementViewSorter extends ViewerSorter {

	/**
	 * Creates a new instance.
	 */
	public MethodElementViewSorter() {
	}

	/**
	 * @see org.eclipse.jface.viewers.ViewerSorter#compare(Viewer, Object,
	 *      Object)
	 */
	public int compare(Viewer viewer, Object e1, Object e2) {
		if (e1 instanceof MethodPlugin) {
			if (!(e2 instanceof MethodPlugin)) {
				return -1;
			}
		} else if (e1 instanceof ContentPackage) {
			if (e2 instanceof MethodPlugin) {
				return 1;
			} else if (!(e2 instanceof ContentPackage)) {
				return -1;
			}
		} else if (e1 instanceof Role) {
			if (e2 instanceof MethodPlugin || e2 instanceof ContentPackage) {
				return 1;
			} else if (!(e2 instanceof Role)) {
				return -1;
			}
		} else if (e1 instanceof Task) {
			if (e2 instanceof MethodPlugin || e2 instanceof ContentPackage
					|| e2 instanceof Role) {
				return 1;
			} else if (!(e2 instanceof Task)) {
				return -1;
			}
		} else if (e1 instanceof WorkProduct) {
			if (e2 instanceof MethodPlugin || e2 instanceof ContentPackage
					|| e2 instanceof Role || e2 instanceof Task) {
				return 1;
			} else if (!(e2 instanceof WorkProduct)) {
				return -1;
			}
		} else if (e1 instanceof Guidance) {
			if (e2 instanceof MethodPlugin || e2 instanceof ContentPackage
					|| e2 instanceof Role || e2 instanceof Task
					|| e2 instanceof WorkProduct) {
				return 1;
			} else if (e2 instanceof Guidance) {
				String name1 = e1.getClass().getName();
				String name2 = e2.getClass().getName();
				if (!name1.equals(name2)) {
					return collator.compare(name1, name2);
				}
			} else {
				return -1;
			}
		} else if (e1 instanceof ContentCategory) {
			if (e2 instanceof MethodPlugin || e2 instanceof ContentPackage
					|| e2 instanceof Role || e2 instanceof Task
					|| e2 instanceof WorkProduct || e2 instanceof Guidance) {
				return 1;
			} else if (e1 instanceof CustomCategory
					&& !(e2 instanceof CustomCategory)) {
				return 1;
			} else if (!(e1 instanceof CustomCategory)
					&& e2 instanceof CustomCategory) {
				return -1;
			} else if (e2 instanceof ContentCategory) {
				String name1 = e1.getClass().getName();
				String name2 = e2.getClass().getName();
				if (!name1.equals(name2)) {
					return collator.compare(name1, name2);
				}
			} else {
				return -1;
			}
		} else if (e1 instanceof Process) {
			if (e2 instanceof MethodPlugin || e2 instanceof ContentPackage
					|| e2 instanceof ContentElement) {
				return 1;
			} else if (e2 instanceof ProcessPackage && !(e2 instanceof Process)) {
				return 1;
			} else if (!(e2 instanceof Process)) {
				return -1;
			}
		} else if (e1 instanceof ProcessPackage) {
			if (e2 instanceof MethodPlugin || e2 instanceof ContentPackage
					|| e2 instanceof ContentElement) {
				return 1;
			} else if (e2 instanceof Process && !(e1 instanceof Process)) {
				return -1;
			} else if (!(e2 instanceof ProcessPackage)) {
				return -1;
			}
		} else if (e1 instanceof ProcessElement) {
			if (e2 instanceof MethodPlugin || e2 instanceof MethodPackage
					|| e2 instanceof ContentElement || e2 instanceof Process) {
				return 1;
			} else if (e1 instanceof Phase) {
				if (!(e2 instanceof Phase)) {
					return -1;
				}
			} else if (e1 instanceof Iteration) {
				if (e2 instanceof Phase) {
					return 1;
				}
				if (!(e2 instanceof Iteration)) {
					return -1;
				}
			} else if (e1 instanceof Activity) {
				if (e2 instanceof Phase || e2 instanceof Iteration) {
					return 1;
				}
				if (!(e2 instanceof Activity)) {
					return -1;
				}
			} else if (e1 instanceof Milestone) {
				if (e2 instanceof Activity) {
					return 1;
				}
				if (!(e2 instanceof Milestone)) {
					return -1;
				}
			} else if (e1 instanceof RoleDescriptor) {
				if (e2 instanceof Activity || e2 instanceof Milestone) {
					return 1;
				}
				if (!(e2 instanceof RoleDescriptor)) {
					return -1;
				}
			} else if (e1 instanceof TaskDescriptor) {
				if (e2 instanceof Activity || e2 instanceof Milestone
						|| e2 instanceof RoleDescriptor) {
					return 1;
				}
				if (!(e2 instanceof TaskDescriptor)) {
					return -1;
				}
			} else if (e1 instanceof WorkProductDescriptor) {
				if (e2 instanceof Activity || e2 instanceof Milestone
						|| e2 instanceof RoleDescriptor
						|| e2 instanceof TaskDescriptor) {
					return 1;
				}
				if (!(e2 instanceof WorkProductDescriptor)) {
					return -1;
				}
			}
		}
		return super.compare(viewer, e1, e2);
	}

}
