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
package org.eclipse.epf.authoring.ui.actions;

import org.eclipse.emf.edit.ui.action.CutAction;
import org.eclipse.jface.viewers.IStructuredSelection;

/**
 * Cuts an element in the Library view to the clipboard.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class LibraryViewCutAction extends CutAction {

	/**
	 * @see org.eclipse.emf.edit.ui.action.CommandActionHandler#run()
	 */
	public void run() {
		if (command == null)
			return;
		else
			super.run();
	}

	/**
	 * @see org.eclipse.emf.edit.ui.action.CommandActionHandler#updateSelection(org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public boolean updateSelection(IStructuredSelection selection) {
		return false;
	}
	
}
