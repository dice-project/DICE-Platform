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
package org.eclipse.epf.authoring.ui.actions;

import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.wizards.NewConfigurationWizard;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;

/**
 * Displays the New Method Configuration wizard.
 * 
 * @author Kelvin Low
 * @since 1.2
 */
public class NewConfigurationAction extends Action implements
		IWorkbenchWindowActionDelegate {

	// The current workbench window.
	private IWorkbenchWindow window;

	// The current selection object.
	private ISelection selection;

	/**
	 * Creates a new instance.
	 */
	public NewConfigurationAction() {
		super();
	}

	/**
	 * Creates a new instance.
	 */
	public NewConfigurationAction(String text) {
		super(text);
	}

	/**
	 * @see org.eclipse.ui.IWorkbenchWindowActionDelegate#init(IWorkbenchWindow)
	 */
	public void init(IWorkbenchWindow window) {
		this.window = window;
	}

	/**
	 * @see IWorkbenchWindowActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
	}

	/**
	 * @see org.eclipse.ui.IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		run();
	}

	/**
	 * @see org.eclipse.jface.action.Action#run()
	 */
	public void run() {
		IWorkbench workbench = null;
		if (window != null) {
			workbench = window.getWorkbench();
		}
		if (workbench == null) {
			workbench = PlatformUI.getWorkbench();
		}

		try {
			NewConfigurationWizard wizard = new NewConfigurationWizard();
			if (selection != null && selection instanceof IStructuredSelection) {
				wizard.init(workbench, (IStructuredSelection) selection);
			} else {
				wizard.init(workbench, null);
			}
			WizardDialog dialog = new WizardDialog(Display.getCurrent()
					.getActiveShell(), wizard);
			dialog.create();
			dialog.open();
		} catch (Exception e) {
			AuthoringUIPlugin.getDefault().getMsgDialog().displayError(
					AuthoringUIResources.newConfigurationWizard_title,
					AuthoringUIResources.openConfigWizardError_msg,
					AuthoringUIResources.internalError_reason, "", e); //$NON-NLS-1$
		}
	}

	/**
	 * @see org.eclipse.ui.IWorkbenchWindowActionDelegate#dispose()
	 */
	public void dispose() {
	}

}