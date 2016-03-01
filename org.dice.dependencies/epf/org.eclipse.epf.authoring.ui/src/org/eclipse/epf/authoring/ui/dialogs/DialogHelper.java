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
package org.eclipse.epf.authoring.ui.dialogs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.epf.authoring.ui.filters.ProcessActivityFilter;
import org.eclipse.epf.authoring.ui.filters.ProcessRoleFilter;
import org.eclipse.epf.authoring.ui.filters.VariabilityProcessRoleFilter;
import org.eclipse.epf.authoring.ui.filters.VariabilityProcessTaskFilter;
import org.eclipse.epf.authoring.ui.filters.VariabilityProcessWorkProductFilter;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.itemsfilter.FilterConstants;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.TeamProfile;
import org.eclipse.ui.PlatformUI;


/**
 * Helper class to collect input data from users using dialog boxes
 * 
 * @author Phong Nguyen Le
 * @author Shilpa Toraskar
 * @since 1.0
 */
public class DialogHelper {

	public static List selectElementsFor(BreakdownElement e, IFilter filter,
			MethodConfiguration config, String tabName) {
		ItemsFilterDialog fd = new ItemsFilterDialog(PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getShell(), filter, tabName, null,
				config);
		fd.setBlockOnOpen(true);
		fd.setViewerSelectionSingle(false);
		fd.setTitle(tabName);
		fd.open();
		return fd.getSelectedItems();
	}

	public static List selectTasksFor(Activity act, MethodConfiguration config) {
		String tabName = FilterConstants.TASKS;
		IFilter filter = new VariabilityProcessTaskFilter(config, null,
				FilterConstants.TASKS, act);

		return selectElementsFor(act, filter, config, tabName);
	}

	public static List selectElementsFor(Object object,
			MethodConfiguration config, AdapterFactory adapterFactory) {
		if (object instanceof Activity) {
			if (adapterFactory == TngAdapterFactory.INSTANCE
					.getWBS_ComposedAdapterFactory()) {
				return selectTasksFor((Activity) object, config);
			} else if (adapterFactory == TngAdapterFactory.INSTANCE
					.getOBS_ComposedAdapterFactory()) {
				String tabName = FilterConstants.ROLES;
				IFilter filter = new VariabilityProcessRoleFilter(config, null,
						FilterConstants.ROLES, (Activity) object);
				return selectElementsFor((BreakdownElement) object, filter,
						config, tabName);
			} else if (adapterFactory == TngAdapterFactory.INSTANCE
					.getPBS_ComposedAdapterFactory()) {
				String tabName = FilterConstants.WORKPRODUCTS;
				IFilter filter = new VariabilityProcessWorkProductFilter(
						config, null, FilterConstants.WORKPRODUCTS,
						(Activity) object);
				return selectElementsFor((BreakdownElement) object, filter,
						config, tabName);
			}
		}
		if (object instanceof TeamProfile) {
			if (adapterFactory == TngAdapterFactory.INSTANCE
					.getOBS_ComposedAdapterFactory()) {
				String tabName = FilterConstants.ROLES;
				IFilter filter = new ProcessRoleFilter(config, null,
						FilterConstants.ROLES);
				return selectElementsFor((BreakdownElement) object, filter,
						config, tabName);
			}
		}

		return Collections.EMPTY_LIST;
	}

	public static List selectActivitiesFor(Activity act,
			MethodConfiguration config, int command) {
		// return null;
		String tabName = FilterConstants.PROCESSES;
		IFilter filter = new ProcessActivityFilter(config, null,
				FilterConstants.PROCESSES, act, command);
		List selection = selectElementsFor(act, filter, config, tabName);
		ArrayList activities = new ArrayList();
		for (Iterator iter = selection.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (element instanceof Activity) {
				activities.add(element);
			} else if (element instanceof ProcessComponent) {
				Process proc = ((ProcessComponent) element).getProcess();
				if (proc != null) {
					activities.add(proc);
				}
			}
		}
		return activities;
	}
	
}
