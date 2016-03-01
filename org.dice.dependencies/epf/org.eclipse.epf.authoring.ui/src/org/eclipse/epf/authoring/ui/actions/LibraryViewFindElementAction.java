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

import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.views.LibraryView;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.BaseSelectionListenerAction;

/**
 * 
 * Finds an element in library view
 * 
 * @author Shilpa Toraskar
 * @since 1.0
 *
 */
public class LibraryViewFindElementAction extends BaseSelectionListenerAction {

	/**
	 * The ID for this action.
	 */
	public static final String ACTION_ID = LibraryViewFindElementAction.class
			.getName();
	
	private LibraryView targetView;
	private IStructuredSelection selected;

	
	public LibraryViewFindElementAction(String text) {
		super(text);
	}

	/**
	 * @see org.eclipse.jface.action.Action#run()
	 */
	public void run() {
		if (targetView == null)
			targetView = LibraryView.getView();

		// select currentSelection in Lib View
		if (targetView != null && selected != null && !selected.isEmpty()) {

			try {
				targetView.getSite().getPage().showView(LibraryView.VIEW_ID);
			} catch (PartInitException pe) {
				AuthoringUIPlugin.getDefault().getLogger().logError(pe);
			}

			final Object selectedObject = selected.getFirstElement();
			if (selectedObject != null) {
				Display.getCurrent().asyncExec(new Runnable() {
					public void run() {
						LibraryView.getView().setSelectionToViewer(
								selectedObject);
					}

				});

			}
		}
	}

	/**
	 * @see org.eclipse.ui.actions.BaseSelectionListenerAction#updateSelection(org.eclipse.jface.viewers.IStructuredSelection)
	 */
	protected boolean updateSelection(IStructuredSelection selection) {
		selected = selection;
		if (selection != null && !selection.isEmpty()) {
			Object element = selection.getFirstElement();
			Object realObj = TngUtil.unwrap(element);
			if (realObj instanceof MethodElement) {
				return true;
			}
		}
		return false;
	}
}
