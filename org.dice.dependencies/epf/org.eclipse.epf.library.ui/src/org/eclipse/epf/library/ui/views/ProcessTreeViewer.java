//------------------------------------------------------------------------------
// Copyright (c) 2005, 2007 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.library.ui.views;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

/**
 * Displays a tree view that allows a user to select a capability pattern or
 * delivery process.
 * 
 * @author Kelvin Low
 * @since 1.2
 */
public class ProcessTreeViewer extends TreeViewer {

	/**
	 * Creates a new instance.
	 * 
	 * @param parent
	 *            the parent composite
	 */
	public ProcessTreeViewer(Composite parent) {
		super(parent, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		setContentProvider(new ProcessTreeContentProvider());
		setLabelProvider(new ProcessTreeLabelProvider());
	}

}
