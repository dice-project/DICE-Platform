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

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.epf.authoring.ui.AuthoringPerspective;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.PerspectiveListUtil;
import org.eclipse.epf.authoring.ui.wizards.NewPluginWizard;
import org.eclipse.epf.common.ui.util.PerspectiveUtil;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.edit.ui.UserInteractionHelper;
import org.eclipse.epf.library.ui.actions.LibraryLockingOperationRunner;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;

/**
 * Displays the New Method Plug-in wizard and creates a new plug-in when the
 * user selects the Finish button.
 * 
 * @author Kelvin Low
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class NewPluginAction extends Action implements
		IWorkbenchWindowActionDelegate {

	// The cached window object.
	private IWorkbenchWindow window;

	/**
	 * Creates a new instance.
	 */
	public NewPluginAction() {
		super();
	}

	/**
	 * Creates a new instance.
	 */
	public NewPluginAction(String text) {
		super(text);
	}

	/**
	 * @see org.eclipse.ui.IWorkbenchWindowActionDelegate#init(IWorkbenchWindow)
	 */
	public void init(IWorkbenchWindow window) {
		this.window = window;
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
		MethodLibrary targetLib = LibraryService.getInstance()
				.getCurrentMethodLibrary();
		if (targetLib == null) {
			AuthoringUIPlugin.getDefault().getMsgDialog().displayError(
					AuthoringUIResources.newPluginWizard_title, 
					AuthoringUIResources.noOpenLibraryError_msg); 
			return;
		}

		Shell shell = Display.getCurrent().getActiveShell();
		IStatus status = UserInteractionHelper.checkModify(targetLib, shell);
		if (!status.isOK()) {
			AuthoringUIPlugin.getDefault().getMsgDialog().display(
					AuthoringUIResources.newPluginWizard_title, 
					AuthoringUIResources.newPluginWizard_createError, 
					status);
			return;
		}

		final IWorkbench workbench = window != null ? (window.getWorkbench() == null ? PlatformUI
				.getWorkbench()
				: window.getWorkbench())
				: PlatformUI.getWorkbench();
		LibraryLockingOperationRunner runner = new LibraryLockingOperationRunner();
		runner.run(new IRunnableWithProgress() {

			public void run(IProgressMonitor monitor)
					throws InvocationTargetException, InterruptedException {
				execute(workbench, null);
			}

		});
	}

	/**
	 * @see IWorkbenchWindowActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
	}

	/**
	 * @see org.eclipse.ui.IWorkbenchWindowActionDelegate#dispose()
	 */
	public void dispose() {
	}

	/**
	 * Executes the action.
	 * 
	 * @param workbench
	 *            The active workbench.
	 * @param selection
	 *            The active selection.
	 */
	public static void execute(IWorkbench workbench, ISelection selection) {
		NewPluginWizard wizard = new NewPluginWizard();

		if (selection instanceof IStructuredSelection || selection == null) {
			wizard.init(workbench, (IStructuredSelection) selection);
		}

		WizardDialog dialog = new WizardDialog(Display.getCurrent()
				.getActiveShell(), wizard);
		dialog.create();
		dialog.open();

		if (!PerspectiveListUtil.isAuthoringPerspective()) {
			PerspectiveUtil
					.openPerspective(AuthoringPerspective.PERSPECTIVE_ID);
		}
	}

}