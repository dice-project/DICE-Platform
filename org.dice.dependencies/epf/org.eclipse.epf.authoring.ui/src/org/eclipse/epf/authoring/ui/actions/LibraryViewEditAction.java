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

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import org.eclipse.epf.authoring.ui.editors.EditorChooser;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.actions.BaseSelectionListenerAction;


/**
 * Edits an element in the Library view.
 * 
 * @author Bingxue Xu
 * @author Kelvin Low
 * @since 1.0
 */
public class LibraryViewEditAction extends BaseSelectionListenerAction {

	/**
	 * The ID for this action.
	 */
	public static final String ACTION_ID = LibraryViewEditAction.class.getName();
	
	private Collection selected;

	/**
	 * Creates a new instance.
	 */
	public LibraryViewEditAction(String text) {
		super(text);
	}

	/**
	 * @see org.eclipse.jface.action.Action#run()
	 */
	public void run() {
		for (Iterator objects = selected.iterator(); objects.hasNext();) {
			Object element = objects.next();
			EditorChooser.getInstance().openEditor(element);
		}
	}

	/**
	 * Updates this action in response to the given selection changes.
	 */
	public boolean updateSelection(IStructuredSelection selection) {
		if (selection.isEmpty()) {
			return false;
		}

		selected = new HashSet();

		for (Iterator objects = selection.iterator(); objects.hasNext();) {
			Object element = objects.next();
			Object realObj = TngUtil.unwrap(element);
			if (realObj instanceof MethodElement
					&& !TngUtil.isPredefined((MethodElement) realObj)
					&& !(realObj instanceof ProcessPackage && !(realObj instanceof ProcessComponent))) {
				selected.add(realObj);
			}
		}

		return !selected.isEmpty();
	}

}
