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
package org.eclipse.epf.library.ui.actions;

import org.eclipse.epf.library.ui.LibraryUIUtil;
import org.eclipse.epf.library.ui.wizards.OpenLibraryWizard;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;

/**
 * Implements the Open Library action.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class OpenLibraryAction implements IWorkbenchWindowActionDelegate {

	private IWorkbenchWindow window;

	/**
	 * @see IWorkbenchWindowActionDelegate#init
	 */
	public void init(IWorkbenchWindow window) {
		this.window = window;
	}

	/**
	 * @see IWorkbenchWindowActionDelegate#selectionChanged
	 */
	public void selectionChanged(IAction action, ISelection selection) {
	}

	/**
	 * @see IWorkbenchWindowActionDelegate#run
	 */
	public void run(IAction action) {
		int dirtyCount = 0;
		IEditorReference[] list = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage()
				.getEditorReferences();
		for (int i = 0; i < list.length; i++) {
			if (list[i].isDirty()) {
				dirtyCount++;
			}
		}

		if (dirtyCount == 1) {
			boolean rc = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
					.getActivePage().closeAllEditors(true);
			if (!rc) {
				return;
			}
		} else if (dirtyCount > 1) {
			switch (LibraryUIUtil.displaySaveDirtyEditorsDialog()) {
			case SWT.YES:
				PlatformUI.getWorkbench().getActiveWorkbenchWindow()
						.getActivePage().closeAllEditors(true);
				break;
			case SWT.NO:
				PlatformUI.getWorkbench().getActiveWorkbenchWindow()
						.getActivePage().closeAllEditors(false);
				break;
			case SWT.CANCEL:
				return;
			}
		} else if (list.length > 0 ){
			PlatformUI.getWorkbench().getActiveWorkbenchWindow()
			.getActivePage().closeAllEditors(false);
		}

		OpenLibraryWizard wizard = new OpenLibraryWizard();
		wizard.init(window.getWorkbench(), null);
		Shell shell = Display.getCurrent().getActiveShell();
		WizardDialog dialog = new WizardDialog(shell, wizard);
		dialog.create();
		dialog.open();
	}

	/**
	 * @see IWorkbenchWindowActionDelegate#dispose
	 */
	public void dispose() {
	}

}