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

import java.util.Iterator;

import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.PerspectiveListUtil;
import org.eclipse.epf.authoring.ui.UIActionDispatcher;
import org.eclipse.epf.authoring.ui.editors.EditorChooser;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.actions.BaseSelectionListenerAction;


/**
 * Open method element editor delegation action 
 * 
 * @author BingXue Xu
 * @since 1.0
 */
public class OpenElementWorkbenchActionDelegate extends
		BaseSelectionListenerAction implements IWorkbenchWindowActionDelegate {

	// private IWorkbenchWindow window;

	private ISelection selection;

	/**
	 * Creates an instance
	 *
	 */
	public OpenElementWorkbenchActionDelegate() {
		this(
				AuthoringUIResources.OpenElementWorkbenchActionDelegate_edit_text0); 
	}

	protected OpenElementWorkbenchActionDelegate(String text) {
		super(text);
	}

	/**
	 * @see org.eclipse.ui.IWorkbenchWindowActionDelegate#dispose()
	 */
	public void dispose() {

	}

	/**
	 * @see org.eclipse.ui.IWorkbenchWindowActionDelegate#init(org.eclipse.ui.IWorkbenchWindow)
	 */
	public void init(IWorkbenchWindow window) {
		// this.window = window;

	}

	/**
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	public void run(IAction action) {
		// System.out.println("$$$ Open element action!");

		// System.out.println("$$$ 5: " + selection);
		IStructuredSelection selected = (IStructuredSelection) selection;

		if (PerspectiveListUtil.isBrowsingPerspective()) {
			UIActionDispatcher.openAuthoringPerspective();
		}

		for (Iterator objects = selected.iterator(); objects.hasNext();) {
			Object element = TngUtil.unwrap(objects.next());
			// System.out.println("$$$ 6: " + element);
			if (element instanceof MethodElement) {
				EditorChooser.getInstance().openEditor(element);
			}
		}

		action.setEnabled(false);
	}


	/**
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;

		IStructuredSelection selected = (IStructuredSelection) selection;

		// if (!PerspectiveListUitl.isAuthoringPerspective() ||
		// selected.isEmpty()) {
		if (selected.isEmpty()) {
			action.setEnabled(false);
			return;
		}

		boolean enableMenu = true;
		for (Iterator objects = selected.iterator(); objects.hasNext();) {
			Object element = TngUtil.unwrap(objects.next());
			if (!(element instanceof MethodElement)) {
				enableMenu = false;
			}
		}

		action.setEnabled(enableMenu);
	}

}
