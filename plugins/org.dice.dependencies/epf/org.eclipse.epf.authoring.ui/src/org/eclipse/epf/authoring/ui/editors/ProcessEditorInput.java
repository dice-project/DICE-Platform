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
package org.eclipse.epf.authoring.ui.editors;

import org.eclipse.epf.uma.MethodElement;
import org.eclipse.jface.viewers.ISelection;

/**
 * An editor input for process editor
 * 
 * @author Phong Nguyen Le
 */
public class ProcessEditorInput extends MethodElementEditorInput {
	private ISelection initialSelection;
	private int activePage = -1;

	public ProcessEditorInput(MethodElement e) {
		super(e);
	}

	/**
	 * Return active page of the editor
	 */
	public int getActivePage() {
		return activePage;
	}

	/**
	 * Set active page for the editor
	 * @param activePage
	 */
	public void setActivePage(int activePage) {
		this.activePage = activePage;
	}

	/**
	 * Return initial selection
	 * @return
	 * 			Selection
	 */
	public ISelection getInitialSelection() {
		return initialSelection;
	}

	/**
	 * Set initial selection 
	 * @param initialSelection
	 */
	public void setInitialSelection(ISelection initialSelection) {
		this.initialSelection = initialSelection;
	}
}
