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
package org.eclipse.epf.authoring.ui.providers;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.ui.dialogs.ContainerCheckedTreeViewer;

/**
 * Filter for viewers of configuration page.
 * If hideUnchecked is set, it will filter unchecked elements
 * 
 * @author Jeff Hardy
 */
public class HideUncheckedViewerFilter extends ViewerFilter {
	
	private ContainerCheckedTreeViewer viewer;
	
	private boolean hideUnchecked = false;
	
	public HideUncheckedViewerFilter(ContainerCheckedTreeViewer viewer) {
		this.viewer = viewer;
	}

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if (hideUnchecked && !isChecked(element))
			return false;
		return true;
	}

	public boolean isHideUnchecked() {
		return hideUnchecked;
	}

	public void toggleHideUnchecked() {
		hideUnchecked = !hideUnchecked;
	}
	
	private boolean isChecked(Object element) {
		return viewer.getChecked(element);
	}
}
