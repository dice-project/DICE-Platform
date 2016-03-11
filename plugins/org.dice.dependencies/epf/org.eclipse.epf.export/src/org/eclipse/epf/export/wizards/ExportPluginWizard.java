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
package org.eclipse.epf.export.wizards;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.epf.authoring.ui.wizards.SaveAllEditorsPage;
import org.eclipse.epf.export.ExportPlugin;
import org.eclipse.epf.export.ExportResources;
import org.eclipse.epf.export.services.PluginExportData;
import org.eclipse.epf.export.services.PluginExportService;
import org.eclipse.epf.library.ui.LibraryUIImages;
import org.eclipse.epf.library.ui.wizards.DirectoryValidator;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;

/**
 * The Export Method Plug-in wizard.
 * 
 * @author Jinhua Xi
 * @author Kelvin Low
 * @since 1.0
 */
public class ExportPluginWizard extends Wizard implements IExportWizard {

	protected SelectPluginPage page1;

	protected PluginInfoPage page2;

	protected ExportPluginSummaryPage page3;

	protected SavePluginPage page4;

	protected boolean okToComplete = false;

	protected PluginExportData data = new PluginExportData();

	/**
	 * Creates a new instance.
	 */
	public ExportPluginWizard() {
		setWindowTitle(ExportResources.exportPluginsWizard_title);
		setNeedsProgressMonitor(true);
	}

	/**
	 * @see org.eclipse.ui.IWorkbenchWizard#init(IWorkbench,
	 *      IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
	}

	/**
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	public void addPages() {
		SaveAllEditorsPage.addPageIfNeeded(this, false, null, null,
				ExportPlugin.getDefault().getImageDescriptor(
						"full/wizban/ExportMethodPlugins.gif")); //$NON-NLS-1$

		page1 = new SelectPluginPage(data);
		addPage(page1);

		page2 = new PluginInfoPage(data);
		addPage(page2);

		page3 = new ExportPluginSummaryPage(data);
		addPage(page3);

		page4 = new SavePluginPage(data);
		addPage(page4);
	}

	/**
	 * @see org.eclipse.jface.wizard.Wizard#createPageControls(Composite)
	 */
	public void createPageControls(Composite pageContainer) {
		super.createPageControls(pageContainer);
		pageContainer.getShell().setImage(LibraryUIImages.IMG_METHOD_PLUGIN);
	}

	/**
	 * @see org.eclipse.jface.wizard.IWizard#canFinish()
	 */
	public boolean canFinish() {
		if (this.getContainer().getCurrentPage() != page4)
			return false;
		return okToComplete;
	}

	/**
	 * @see org.eclipse.jface.wizard.IWizard#performFinish()
	 */
	public boolean performFinish() {
		String exportLibPath = data.llData.getParentFolder();
		if (checkAndCreateDir(exportLibPath) == false) {
			return false;
		}

		IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor)
					throws InvocationTargetException {
				try {
					monitor.beginTask(ExportResources.ExportPluginWizard_MSG1,
							IProgressMonitor.UNKNOWN);

					if (data.validate()) {
						PluginExportService.newInstance(data).run(monitor);
					}

				} catch (Exception e) {
					throw new InvocationTargetException(e);
				} finally {
					monitor.done();
				}
			}
		};

		try {
			getContainer().run(true, false, op);
		} catch (InterruptedException e) {
			return false;
		} catch (InvocationTargetException e) {
			Throwable realException = e.getTargetException();
			ExportPlugin.getDefault().getMsgDialog().displayError(
					ExportResources.ExportPluginWizard_error,
					realException.getMessage());
			return false;
		}

		// Save the export path to preference store.
		ExportUIPreferences.addExportPluginDir(exportLibPath);

		return true;
	}

	private boolean checkAndCreateDir(String dir) {
		return DirectoryValidator.checkAndCreateDir(dir,
				ExportResources.ExportPluginWizard_title,
				ExportResources.ExportPluginError_msg, false);
	}

}
