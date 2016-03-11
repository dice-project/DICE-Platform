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

import java.util.Collection;

import org.eclipse.epf.library.edit.configuration.GuidanceGroupingItemProvider;
import org.eclipse.epf.library.edit.configuration.GuidanceItemProvider;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.jface.viewers.TableViewer;


/**
 * Guidance Filter for Work breakdown structure. 
 * @author Shashidhar Kannoori
 * @since 1.0
 */
public class ProcessGuidanceFilter extends DescriptorConfigurationFilter {

	public ProcessGuidanceFilter(MethodConfiguration config,
			TableViewer viewer, String tabName) {
		super(config, viewer, tabName);
	}

	public boolean accept(Object obj) {
		return super.accept(obj);
	}

	public boolean childAccept(Object obj) {
		if (obj instanceof GuidanceGroupingItemProvider)
			return true;
		if ((obj instanceof GuidanceItemProvider)) {
			Collection col = ((GuidanceItemProvider) obj).getChildren(obj);
			if (col.isEmpty())
				return false;
			else
				return true;
		}
		return false;
	}

}
