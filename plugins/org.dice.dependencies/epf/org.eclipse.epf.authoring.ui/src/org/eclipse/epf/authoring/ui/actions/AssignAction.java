//------------------------------------------------------------------------------
// Copyright (c) 2005, 2008 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.authoring.ui.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.actions.LibraryViewSimpleAction.CustomeCategoryAction;
import org.eclipse.epf.authoring.ui.dialogs.AssignDialog;
import org.eclipse.epf.authoring.ui.views.LibraryView;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.jface.viewers.IStructuredSelection;

/**
 * Assign method element.
 * 
 * @author Weiping Lu
 * @since  1.5
 */
public class AssignAction extends CustomeCategoryAction {

	/**
	 * Creates an instance
	 * @param text
	 */
	public AssignAction(LibraryView libView) {
		super(libView, AuthoringUIResources.assignAction_text);
	}
	
	protected void doRun() {		
		Collection elementsToAssign = new ArrayList();
		IStructuredSelection selection = (IStructuredSelection) getLibraryView().getSelection();
		for (Iterator iter = selection.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (element instanceof MethodElement
					|| (element = TngUtil.unwrap(element)) instanceof CustomCategory) {
				elementsToAssign.add(element);
			}
		}
		
		AssignDialog dlg = getDialog(elementsToAssign);
		dlg.open();
	}
	
	protected AssignDialog getDialog(Collection elements) {
		return AssignDialog.newAssignDialog(getLibraryView().getSite().getShell(), elements);
	}
	
	/**
	 * @param selection
	 * @return
	 */
	public boolean updateSelection(IStructuredSelection selection) {
		//return false;
		return canAssign(selection);
	}
	
	/**
	 * Returns <code>true</code> if the selected method elements can be assigned.
	 */
	private boolean canAssign(IStructuredSelection selection) {
		if (selection.size() > 1) {
			return false;
		}
		Object element = TngUtil.unwrap(selection.getFirstElement());
		if (element instanceof CustomCategory) {
			return getSelectionParentObject() instanceof CustomCategory;
		}
		
		return false;
	}

}
