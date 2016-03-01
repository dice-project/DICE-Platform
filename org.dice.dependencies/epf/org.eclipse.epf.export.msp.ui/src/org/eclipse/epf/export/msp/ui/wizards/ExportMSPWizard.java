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
package org.eclipse.epf.export.msp.ui.wizards;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.epf.authoring.ui.wizards.SelectProcessPage;
import org.eclipse.epf.export.msp.ExportMSPOptions;
import org.eclipse.epf.export.msp.ExportMSPService;
import org.eclipse.epf.export.msp.ExportMSPServiceException;
import org.eclipse.epf.export.msp.IExportMSPService;
import org.eclipse.epf.export.msp.ui.ExportMSPUIPlugin;
import org.eclipse.epf.export.msp.ui.ExportMSPUIResources;
import org.eclipse.epf.publishing.services.PublishOptions;
import org.eclipse.epf.publishing.util.PublishingUtil;
import org.eclipse.epf.ui.wizards.BaseWizard;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.Process;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;

/**
 * The Export Microsoft Project wizard.
 * <p>
 * This wizard exports a capability pattern or delivery process to a Microsoft
 * Project. The current implementation generates a XML file that adheres to the
 * Microsoft Project 2003 XML Schema.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class ExportMSPWizard extends BaseWizard implements IExportWizard {

	/**
	 * The wizard ID.
	 */
	public static final String WIZARD_ID = ExportMSPWizard.class.getName();

	/**
	 * The Publish Configuration wizard extension point ID.
	 */
	public static final String WIZARD_EXTENSION_POINT_ID = "org.eclipse.epf.export.msp.ui.exportMSPWizard"; //$NON-NLS-1$	

	// The wizard page that prompts the user to select a process.
	protected SelectProcessPage selectProcessPage;

	// The wizard page that prompts the user to specify the export options.
	protected SelectExportOptionsPage selectExportOptionsPage;

	// The wizard page that prompts the user to specify the publishing options.
	protected SelectPublishingOptionsPage selectPublishingOptionsPage;

	// The wizard page that prompts the user to specify the export directory.
	protected SelectExportDirectoryPage selectExportDirPage;

	// The export options.
	protected ExportMSPOptions exportOptions = new ExportMSPOptions();

	/**
	 * Creates a new instance.
	 */
	public ExportMSPWizard() {
	}

	/**
	 * @see org.eclipse.epf.ui.wizards.BaseWizard#init(IWorkbench,
	 *      IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		super.init(workbench, selection);
		setWindowTitle(ExportMSPUIResources.exportMSPWizard_title);
	}

	/**
	 * @see org.eclipse.epf.ui.wizards.BaseWizard#getWizardExtenderExtensionPointId()
	 */
	public String getWizardExtenderExtensionPointId() {
		return WIZARD_EXTENSION_POINT_ID;
	}

	/**
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	public void addPages() {
		if (wizardExtender == null) {
			selectProcessPage = new SelectProcessPage();
			selectExportOptionsPage = new SelectExportOptionsPage();
			selectPublishingOptionsPage = new SelectPublishingOptionsPage();
			selectExportDirPage = new SelectExportDirectoryPage();
			super.addPage(selectProcessPage);
			super.addPage(selectExportOptionsPage);
			super.addPage(selectPublishingOptionsPage);
			super.addPage(selectExportDirPage);
		} else {
			List<IWizardPage> wizardPages = new ArrayList<IWizardPage>();

			IWizardPage page = wizardExtender
					.getReplaceWizardPage(SelectProcessPage.PAGE_NAME);
			if (page != null) {
				wizardPages.add(page);
			} else {
				selectProcessPage = new SelectProcessPage();
				wizardPages.add(selectProcessPage);
			}

			page = wizardExtender
					.getReplaceWizardPage(SelectExportOptionsPage.PAGE_NAME);
			if (page != null) {
				wizardPages.add(page);
			} else {
				selectExportOptionsPage = new SelectExportOptionsPage();
				wizardPages.add(selectExportOptionsPage);
			}

			page = wizardExtender
					.getReplaceWizardPage(SelectPublishingOptionsPage.PAGE_NAME);
			if (page != null) {
				wizardPages.add(page);
			} else {
				selectPublishingOptionsPage = new SelectPublishingOptionsPage();
				wizardPages.add(selectPublishingOptionsPage);
			}

			page = wizardExtender
					.getReplaceWizardPage(SelectExportDirectoryPage.PAGE_NAME);
			if (page != null) {
				wizardPages.add(page);
			} else {
				selectExportDirPage = new SelectExportDirectoryPage();
				wizardPages.add(selectExportDirPage);
			}

			super.getNewWizardPages(wizardPages);

			for (Iterator<IWizardPage> it = wizardPages.iterator(); it
					.hasNext();) {
				IWizardPage wizardPage = it.next();
				super.addPage(wizardPage);
			}

			wizardExtender.initWizardPages(wizardPages);
		}
	}

	/**
	 * @see org.eclipse.jface.wizard.Wizard#createPageControls(Composite)
	 */
	public void createPageControls(Composite pageContainer) {
		super.createPageControls(pageContainer);
		pageContainer.getShell().setImage(
				ExportMSPUIPlugin.getDefault().getSharedImage(
						"full/wizban/exp_ms_prj_wizban.gif")); //$NON-NLS-1$
	}

	/**
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	public boolean canFinish() {
		if (wizardExtender != null) {
			if (! wizardExtender.canFinish()) {
				return false;
			}
		}
		return getContainer().getCurrentPage() == selectExportDirPage
				&& selectExportDirPage.isPageComplete();
	}

	/**
	 * @see org.eclipse.epf.ui.wizards.BaseWizard#doFinish()
	 */
	public boolean doFinish() {
		return exportMSP(selectProcessPage.getProcess(), getExportOptions(),
				ExportMSPService.getInstance());
	}

	/**
	 * Exports the selected process.
	 */
	public boolean exportMSP(Process process, ExportMSPOptions exportOptions,
			IExportMSPService service) {
		if (process == null || exportOptions == null || service == null) {
			throw new IllegalArgumentException();
		}

		File exportDir;
		String msprojectName;

		try {
			MethodConfiguration config = exportOptions.getMethodConfiguration();
			if (config == null) {
				throw new IllegalArgumentException(
						"exportOptions.getMethodConfiguration()"); //$NON-NLS-1$
			}
			if (exportOptions.getPublishWebSite()) {
				if (selectPublishingOptionsPage != null) {
					selectPublishingOptionsPage.savePreferences();
				}

				if (!PublishingUtil.hasValidProcessView(config, process)) {
					boolean ok = ExportMSPUIPlugin
							.getDefault()
							.getMsgDialog()
							.displayPrompt(
									ExportMSPUIResources.exportMSPWizard_title,
									ExportMSPUIResources.missingProcessContentWarning_msg);
					if (!ok) {
						return false;
					}
				}
			}

			exportDir = exportOptions.getExportDir();
			if (exportDir == null) {
				throw new IllegalArgumentException(
						"exportOptions.getExportDir()"); //$NON-NLS-1$
			}

			msprojectName = exportOptions.getMSProjectName();
			if (msprojectName == null) {
				throw new IllegalArgumentException(
						"exportOptions.getMSProjectName()"); //$NON-NLS-1$
			}
		} catch (IllegalArgumentException e) {
			displayIllegalArgumentError(e.getMessage());
			return true;
		}

		String msprojectFileName = msprojectName + ".xml"; //$NON-NLS-1$
		File msprojectFile = new File(exportDir, msprojectFileName);
		if (msprojectFile.exists()) {
			boolean ok = ExportMSPUIPlugin.getDefault().getMsgDialog()
					.displayPrompt(
							ExportMSPUIResources.exportMSPWizard_title,
							NLS.bind(ExportMSPUIResources.overwriteText_msg,
									(new String[] { msprojectFileName,
											exportDir.getAbsolutePath() })));
			if (!ok) {
				return false;
			}
		}

		try {
			boolean success = service.exportMSProject(process, exportOptions);
			if (success) {
				ExportMSPUIPlugin.getDefault().getMsgDialog().displayInfo(
						ExportMSPUIResources.exportMSPWizard_title,
						NLS.bind(ExportMSPUIResources.completedText_msg,
								(new String[] { process.getName(),
										msprojectFile.getAbsolutePath() })));
			}
		} catch (ExportMSPServiceException e) {
			ExportMSPUIPlugin.getDefault().getMsgDialog().displayError(
					ExportMSPUIResources.exportMSPWizard_title,
					NLS.bind(ExportMSPUIResources.exportMSPError_msg,
							(new String[] { process.getName(),
									msprojectFile.getAbsolutePath() })),
					ExportMSPUIResources.exportMSPError_reason, e);
		}

		return true;
	}

	/**
	 * Returns the wizard page that prompts the user to select a process to
	 * export.
	 */
	public SelectProcessPage getSelectProcessPage() {
		return selectProcessPage;
	}

	/**
	 * Returns the wizard page that prompts the user the export options.
	 */
	public SelectExportOptionsPage getExportOptionsPage() {
		return selectExportOptionsPage;
	}

	/**
	 * Returns the wizard page that prompts the user to specify the publishing
	 * options.
	 */
	public SelectPublishingOptionsPage selectPublishingOptionsPage() {
		return selectPublishingOptionsPage;
	}

	/**
	 * Returns the wizard page that prompts the user to specify the export
	 * directory.
	 */
	public SelectExportDirectoryPage getSelectExportDirectoryPage() {
		return selectExportDirPage;
	}

	/**
	 * Returns the export options.
	 */
	public ExportMSPOptions getExportOptions() {
		exportOptions.setMethodConfiguration(selectExportOptionsPage
				.getMethodConfiguration());
		exportOptions.setExportDir(new File(selectExportDirPage
				.getExportDirectory()));
		exportOptions.setMSProjectName(selectExportDirPage.getMSProjectName());
		exportOptions.setExportOnlyPlannedWBSElements(selectExportOptionsPage
				.getExportOnlyPlannedWBSElementsSelection());

		if (selectExportOptionsPage.getPublishWebSiteSelection()) {
			PublishOptions publishingOptions = selectPublishingOptionsPage
					.getPublishingOptions();
			boolean publishConfig = selectExportOptionsPage
					.getPublishConfigSelection();
			boolean publishProcess = selectExportOptionsPage
					.getPublishProcessSelection();
			publishingOptions.setPublishConfiguration(publishConfig);
			publishingOptions.setPublishProcess(publishProcess);
			exportOptions.setPublishWebSite(publishingOptions != null
					&& (publishConfig || publishProcess));
			exportOptions.setPublishingOptions(publishingOptions);
		} else {
			exportOptions.setPublishWebSite(false);
			exportOptions.setPublishingOptions(null);
		}

		return exportOptions;
	}

	/**
	 * Displays an error dialog about an illegal argument.
	 * 
	 * @param name
	 *            name of the argument
	 */
	protected void displayIllegalArgumentError(String name) {
		String msg = NLS.bind(ExportMSPUIResources.illegalArgument_msg,
				(new String[] { name }));
		ExportMSPUIPlugin.getDefault().getMsgDialog().displayError(
				ExportMSPUIResources.exportMSPWizard_title,
				ExportMSPUIResources.exportMSPInternalError_msg,
				ExportMSPUIResources.exportMSPError_reason,
				new IllegalArgumentException(msg));
	}

}
