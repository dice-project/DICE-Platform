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
package org.eclipse.epf.migration.diagram.ad.wizards;

import java.io.File;

import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.migration.diagram.DiagramMigrationPlugin;
import org.eclipse.epf.migration.diagram.MigrationExportException;
import org.eclipse.epf.migration.diagram.MigrationResources;
import org.eclipse.epf.migration.diagram.ad.services.WorkflowExportService;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.Process;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;

/**
 * Export wizard for exporting diagrams
 * 
 * @author Shashidhar Kannoori
 * @author Shilpa Toraskar
 * @since 1.2
 * 
 */
public class MigrationExportWizard extends Wizard implements IExportWizard {

	// The workbench instance.
	protected IWorkbench workbench;

	// The workbench selection when the wizard was started.
	protected IStructuredSelection selection;

	// The first wizard page.
	protected MigrationExportWizardPage page1;

	/**
	 * Constructor for diagram migration export
	 */
	public MigrationExportWizard() {
		super();
	}

	/**
	 * 
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	public boolean performFinish() {
		if (page1 != null) {

			Process proc = page1.getProcess();
			Activity activity = page1.getActivity();
			// MethodConfiguration mc = page1.getContext();

			String fileName = page1.getTemplateName();
			String targetDir = page1.getTargetDirectory();

			String templateFileName = fileName + ".xmi"; //$NON-NLS-1$
			File templateFile = new File(targetDir, templateFileName);
			if (templateFile.exists()) {
				boolean ok = DiagramMigrationPlugin
						.getDefault()
						.getMsgDialog()
						.displayPrompt(
								MigrationResources.workflow_overwriteTextDialog_title,
								MigrationResources
										.bind(
												MigrationResources.workflow_overwriteText_msg,
												new Object[] {
														templateFileName,
														targetDir }));

				if (!ok) {
					return false;
				}
			}

			try {
				boolean success = WorkflowExportService.getInstance().export(
						proc, activity, fileName, new File(targetDir));

				if (success) {
					return true;
				}

			} catch (MigrationExportException xe) {
				xe.printStackTrace();
			}

		}

		return false;
	}

	/**
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench,
	 *      org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.workbench = workbench;
		this.selection = selection;
	}

	/**
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	public void addPages() {
		// super.addPages();
		MethodLibrary lib = LibraryService.getInstance()
				.getCurrentMethodLibrary();
		if (lib == null) {
			DiagramMigrationPlugin.getDefault().getMsgDialog()
					.displayError(
							MigrationResources.open_method_library_error_title,
							MigrationResources.open_method_library_error_text);

			return;
		}

		page1 = new MigrationExportWizardPage();
		addPage(page1);
	}

	/**
	 * @see org.eclipse.jface.wizard.Wizard#canFinish()
	 */
	public boolean canFinish() {
		// return super.canFinish();
		return page1.isPageComplete();
	}

	/**
	 * @see org.eclipse.jface.wizard.Wizard#createPageControls(org.eclipse.swt.widgets.Composite)
	 */
	public void createPageControls(Composite pageContainer) {
		super.createPageControls(pageContainer);
	}

}
