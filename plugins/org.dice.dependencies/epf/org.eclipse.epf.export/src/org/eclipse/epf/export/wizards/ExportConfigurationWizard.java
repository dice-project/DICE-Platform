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
import org.eclipse.epf.export.services.ConfigurationExportData;
import org.eclipse.epf.export.services.ConfigurationExportService;
import org.eclipse.epf.export.services.ConfigurationSpecsExportService;
import org.eclipse.epf.library.ui.LibraryUIImages;
import org.eclipse.epf.library.ui.wizards.DirectoryValidator;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;

/**
 * The Export Library Configuration wizard.
 * 
 * @author Bingxue Xu
 * @author Kelvin Low
 * @since 1.0
 */
public class ExportConfigurationWizard extends Wizard implements IExportWizard {

	protected ExportConfigChooseMode modePage;

	protected ExportConfigSelectSpecsPage selectSpecsPage;

	protected ExportConfigSelectConfigPage selectConfigPage;

	protected ExportConfigCheckingPage configCheckingPage;

	protected ExportConfigDestinationPage destinationPage;

	protected boolean okToComplete = false;

	protected ConfigurationExportData data = new ConfigurationExportData();

	/**
	 * Creates a new instance.
	 */
	public ExportConfigurationWizard() {
		setWindowTitle(ExportResources.exportConfigWizard_title);
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
						"full/wizban/ExportLibraryConfiguration.gif")); //$NON-NLS-1$

		modePage = new ExportConfigChooseMode(data);
		addPage(modePage);

		selectSpecsPage = new ExportConfigSelectSpecsPage(data);
		addPage(selectSpecsPage);

		selectConfigPage = new ExportConfigSelectConfigPage(data);
		addPage(selectConfigPage);

		configCheckingPage = new ExportConfigCheckingPage(data);
		addPage(configCheckingPage);

		destinationPage = new ExportConfigDestinationPage(data);
		addPage(destinationPage);
	}

	/**
	 * @see org.eclipse.jface.wizard.Wizard#createPageControls(Composite)
	 */
	public void createPageControls(Composite pageContainer) {
		super.createPageControls(pageContainer);
		pageContainer.getShell().setImage(
				LibraryUIImages.IMG_METHOD_CONFIGURATON);
	}

	/**
	 * @see org.eclipse.jface.wizard.IWizard#canFinish()
	 */
	public boolean canFinish() {
		// Cannot complete the wizard if it's not the last page.
		if (this.getContainer().getCurrentPage() != destinationPage)
			return false;
		return okToComplete;
	}

	/**
	 * @see org.eclipse.jface.wizard.IWizard#performFinish()
	 */
	public boolean performFinish() {
		String exportLibPath = data.llData.getParentFolder();
		if (!checkAndCreateDir(exportLibPath)) {
			return false;
		}

		IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor)
					throws InvocationTargetException {
				try {
					monitor.beginTask(
							ExportResources.exportConfigErrorDialog_title,
							IProgressMonitor.UNKNOWN);

					if (data.validate()) {
						if (data.exportConfigSpecs) {
							// Export configuration specs only.
							(new ConfigurationSpecsExportService(data))
									.run(monitor);
						} else {
							// Export a configuration closure.
							(ConfigurationExportService.newInstance(data)).run(monitor);
						}
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
					ExportResources.exportConfigErrorDialog_title,
					realException.getMessage());
			return false;
		}

		if (data.errorMsg != null && data.errorMsg.length() > 0) {
			ExportPlugin.getDefault().getMsgDialog().displayError(
					ExportResources.exportConfigErrorDialog_title,
					data.errorMsg);
		}

		// Save the export path to preference store.
		ExportUIPreferences.addExportConfigDir(exportLibPath);

		return true;
	}

	private boolean checkAndCreateDir(String dir) {
		return DirectoryValidator.checkAndCreateDir(dir,
				ExportResources.exportConfigErrorDialog_title,
				ExportResources.ExportPluginError_msg, false);
	}

}
