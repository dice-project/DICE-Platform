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

import org.eclipse.epf.authoring.ui.editors.EditorChooser;
import org.eclipse.epf.authoring.ui.views.LibraryView;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPart;

/**
 * Links editor with an element in library view
 * @author Jeff Hardy
 * @since 1.0
 *
 */
public class LinkWithEditor implements IViewActionDelegate {

	private LibraryView targetView;

	private static boolean enabled = false;

	private IPartListener editorPartListener = new IPartListener() {

		public void partOpened(IWorkbenchPart part) {
		}

		public void partDeactivated(IWorkbenchPart part) {
		}

		public void partClosed(IWorkbenchPart part) {
		}

		public void partBroughtToTop(IWorkbenchPart part) {
		}

		public void partActivated(IWorkbenchPart part) {
			if (part instanceof IEditorPart) {
				// Select the method element associated with the currently
				// selected editor.
				Object[] objects = new Object[1];
				objects[0] = EditorChooser.getInstance()
						.getActiveMethodEditorInput();
				if (objects[0] != null) {
					// disable the selectionChanged method from firing from this selection change
					enabled = false;
					targetView.setSelectionToViewer(objects[0]);
					enabled = true;
				}
			}

		}
	};

	/**
	 * @see org.eclipse.ui.IViewActionDelegate#init(org.eclipse.ui.IViewPart)
	 */
	public void init(IViewPart view) {
		targetView = (LibraryView) view;
	}

	/**
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	public void run(IAction action) {
		if (enabled) {
			// remove listeners from editorpart
			targetView.getSite().getPage().removePartListener(
					editorPartListener);

			enabled = false;
		} else {
			// select element of currently selected editor
			Object[] objects = new Object[1];
			objects[0] = EditorChooser.getInstance().getActiveMethodEditorInput();
			if (objects[0] != null) {
				targetView.setSelectionToViewer(objects[0]);
			}

			// add listeners to editorpart
			targetView.getSite().getPage().addPartListener(editorPartListener);

			enabled = true;
		}
	}

	/**
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		// System.out.println("LinkWithEditor selection is " + selection);
		if (enabled) {
			IStructuredSelection sel = (IStructuredSelection) selection;
			EditorChooser.getInstance().selectEditor(sel.getFirstElement());
		}
	}
}
