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

import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.process.command.ProcessCommandUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.Task;
import org.eclipse.jface.viewers.TableViewer;


/**
 * Filters {@link Process}'s {@link Task} within {@link MethodConfiguration}
 * @author Shashidhar Kannoori
 * @since 1.0
 */
public class VariabilityProcessTaskFilter extends ProcessTaskFilter {

	Activity activity = null;

	public VariabilityProcessTaskFilter(MethodConfiguration config,
			TableViewer viewer, String tabName, Activity activity) {
		super(config, viewer, tabName);
		this.activity = activity;
	}

	public boolean childAccept(Object obj) {
		if (super.childAccept(obj)) {
//			if (ProcessUtil.getVariabilityElement(this.activity).contains(obj)) {
//				return false;
//			} else
//				return true;
			return ProcessCommandUtil.getValidDescriptor(obj, activity, TngAdapterFactory.INSTANCE.getWBS_ComposedAdapterFactory()) == null;
		} else {
			return false;
		}
	}

}
