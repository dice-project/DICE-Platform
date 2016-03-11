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
package org.eclipse.epf.publishing.ui.actions;

import org.eclipse.epf.publishing.ui.PublishingUIPlugin;
import org.eclipse.epf.publishing.ui.wizards.PublishConfigWizard;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;

/**
 * Invokes the Publish Method Configuration wizard.
 * 
 * @author Kelvin Low
 * @author Jinhua Xi
 * @since 1.0
 */
public class PublishConfigAction implements IWorkbenchWindowActionDelegate {

	/**
	 * @see org.eclipse.ui.IWorkbenchWindowActionDelegate#init(IWorkbenchWindow)
	 */
	public void init(IWorkbenchWindow window) {
	}

	/**
	 * @see org.eclipse.ui.IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		try {
			// Instantiate and initialize the Publish Method Configuraiton
			// wizard.
			PublishConfigWizard wizard = new PublishConfigWizard();
			wizard.init(PlatformUI.getWorkbench(), null);

			// Instantiate the wizard container with the wizard and open it.
			WizardDialog dialog = new WizardDialog(Display.getCurrent()
					.getActiveShell(), wizard);
			dialog.create();
			dialog.open();
		} catch (Exception e) {
			PublishingUIPlugin.getDefault().getLogger().logError(
					"Error publishing content", e); //$NON-NLS-1$
		}
	}

	/**
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
	}

	/**
	 * @see org.eclipse.ui.IWorkbenchWindowActionDelegate#dispose()
	 */
	public void dispose() {
	}

}
