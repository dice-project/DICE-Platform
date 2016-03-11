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
package org.eclipse.epf.authoring.ui.views;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

/**
 * Displays the selectable plug-ins and packages of a method configuration in a
 * tree viewer.
 * 
 * @author Jinhua Xi
 * @author Kelvin Low
 * @since 1.0
 */
public class ConfigurationViewer extends CheckboxTreeViewer {

	/**
	 * Creates a new instance.
	 */
	public ConfigurationViewer(Composite parent) {
		super(parent);
	}

	/**
	 * Creates a new instance.
	 */
	public ConfigurationViewer(Composite parent, int style) {
		super(parent, style);
	}

	/**
	 * Selects the given element in the tree.
	 */
	public void setSelection(Object element) {
		super.expandToLevel(element, 0);
		Widget ctrl = super.findItem(element);
		if (ctrl != null) {
			List items = new ArrayList();
			items.add(ctrl);
			super.setSelection(items);
			this.getTree().setFocus();
		}
	}

	/**
	 * Refreshes the content of the viewer.
	 */
	public void refresh(final Object element, final boolean updateLabels) {
		Object[] checked = this.getCheckedElements();
		super.refresh(element, updateLabels);
		this.setCheckedElements(checked);
	}

}
