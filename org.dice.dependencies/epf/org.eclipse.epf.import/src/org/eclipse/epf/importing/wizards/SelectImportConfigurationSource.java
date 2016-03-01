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
package org.eclipse.epf.importing.wizards;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.epf.export.wizards.DestinationCommonPage;
import org.eclipse.epf.importing.ImportPlugin;
import org.eclipse.epf.importing.ImportResources;
import org.eclipse.epf.importing.services.ConfigurationImportData;
import org.eclipse.epf.importing.services.ConfigurationImportService;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.ui.LibraryUIResources;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.ui.wizards.BaseWizardPage;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.widgets.Shell;

/**
 * A wizard page that prompts the user to select a directory that contains the
 * configurations to import.
 * 
 * @author Jeff Hardy
 * @author Kelvin Low
 * @since 1.0
 */
public class SelectImportConfigurationSource extends DestinationCommonPage {

	public static final String PAGE_NAME = SelectImportConfigurationSource.class
			.getName();

	private static final Status okStatus = new Status(IStatus.OK,
			ImportResources.SelectImportConfigurationSource_not_used, 0,
			"", null); //$NON-NLS-1$

	private ConfigurationImportData data;

	private ConfigurationImportService service;

	private Status status = okStatus;

	protected SelectImportConfigurationSource() {
		super(PAGE_NAME, null);
		setTitle(ImportResources.selectConfigDirWizardPage_title);
		setDescription(ImportResources.selectConfigDirWizardPage_text);
		setImageDescriptor(ImportPlugin.getDefault().getImageDescriptor(
				"full/wizban/imp_lib_conf_wizban.gif")); //$NON-NLS-1$	
	}
	
	/**
	 * Creates a new instance.
	 */
	public SelectImportConfigurationSource(ConfigurationImportData data,
			ConfigurationImportService service) {
		super(PAGE_NAME, data.llData);
		setTitle(ImportResources.selectConfigDirWizardPage_title);
		setDescription(ImportResources.selectConfigDirWizardPage_text);
		setImageDescriptor(ImportPlugin.getDefault().getImageDescriptor(
				"full/wizban/imp_lib_conf_wizban.gif")); //$NON-NLS-1$		
		this.service = service;
		this.data = data;
	}

	public void setData(ConfigurationImportData data) {
		this.data = data;
		this.llData = data.llData;
	}
	
	public void setService(ConfigurationImportService service) {
		this.service = service;
	}
	
	/**
	 * Initializes the wizard page controls with data.
	 */
	protected void initControls() {
		String[] exportDirs = ImportUIPreferences.getImportConfigDirs();
		if (exportDirs != null && exportDirs.length > 0) {
			exportPathCombo.setItems(exportDirs);
			exportPathCombo.setText(exportDirs[0]);
		}
		super.initControls();
	}

	/**
	 * @see org.eclipse.jface.wizard.WizardPage#isCompleted()
	 */
	public boolean isPageComplete() {
		if (LibraryService.getInstance().getCurrentMethodLibrary() == null) {
			setErrorMessage(LibraryUIResources.noOpenLibraryWarning_msg);
			return false;
		}
		boolean returnValue = false;
		status = okStatus;
		if (exportPathCombo.getText().length() > 0) {
			saveToDataModel();
			File libDir = new File(exportPathCombo.getText());
			if (!libDir.exists()) {
				status = new Status(
						IStatus.ERROR,
						ImportResources.SelectImportConfigurationSource_not_used,
						0,
						ImportResources.SelectImportConfigurationSource_no_path,
						null);
			} else {
				returnValue = true;
			}
		}
		applyToStatusLine();
		return returnValue;
	}

	/**
	 * @see org.eclipse.jface.wizard.WizardPage#getNextPage()
	 */
	public IWizardPage getNextPage() {
		saveToDataModel();
		IRunnableWithProgress operation = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor)
					throws InvocationTargetException, InterruptedException {
				monitor.beginTask(
						ImportResources.SelectImportConfigurationSource_MSG1,
						IProgressMonitor.UNKNOWN);
				service.analyze(monitor);
				monitor.done();
			}
		};
		try {
			// This runs the options, and shows progress.
			getWizard().getContainer().run(true, false, operation);
		} catch (Exception exception) {
			// Something went wrong that shouldn't.
			exception.printStackTrace();
		}

		String error = data.getErrorInfo().getError();
		if (error != null && error.length() > 0) {
			status = new Status(IStatus.ERROR,
					ImportResources.SelectImportConfigurationSource_not_used,
					0, error, null);
			applyToStatusLine();
			return this;
		}
		BaseWizardPage page = null;
		if (data.specs != null)
			page = ((ImportConfigurationWizard) getWizard()).specsPage;
		else {
			PluginModifyInfo modifyInfo = checkModify();

			// if only locked, we can auto-unlock and relock the plugins
			// if the pluin is readonly, user need to manually make it updatable
			// customers need to manually unlock and lock plugins during content
			// library upgrade

			if (modifyInfo.readonlyPlugins.size() > 0) {

				error = modifyInfo.getReadonlyMessage().toString();

				// show error message box
				ImportPlugin.getDefault().getMsgDialog().displayError(
						ImportResources.ImportConfigurationWizard_error, error);

				status = new Status(
						IStatus.ERROR,
						ImportResources.SelectImportConfigurationSource_not_used,
						0,
						ImportResources.SelectImportConfigurationSource_error_not_updatable,
						null);
				applyToStatusLine();
				return this;
			}

			// allow user to proceed with locked plugins
			if (modifyInfo.lockedPlugins.size() > 0) {
				String WIZARD_TITLE = ImportResources.importConfigWizard_title;
				String message = modifyInfo.getLockedMessage().toString()
						+ ImportResources.ImportPluginWizard_confirm_continue;
				boolean yes = ImportPlugin.getDefault().getMsgDialog()
						.displayConfirmation(WIZARD_TITLE, message);
				if (!yes) {
					status = new Status(
							IStatus.ERROR,
							ImportResources.SelectImportConfigurationSource_not_used,
							0,
							ImportResources.SelectImportConfigurationSource_error_not_updatable,
							null);
					applyToStatusLine();
					return this;
				}
			}
			page = ((ImportConfigurationWizard) getWizard()).configPage;
		}
		page.onEnterPage(null);
		return page;
	}

	/**
	 * Returns a PluginModifyInfo object.
	 */
	public PluginModifyInfo checkModify() {
		return checkModify(LibraryUtil.getMethodPluginGuids(service
				.getImportingLibrary()), getShell(), true);
	}

	/**
	 * Returns a PluginModifyInfo object.
	 */
	public static PluginModifyInfo checkModify(List guids, Shell shell,
			boolean checkEdit) {
		PluginModifyInfo modifyInfo = new PluginModifyInfo();
		// StringBuffer error = new StringBuffer();
		// List guids = LibraryUtil.getMethodPluginGuids(service
		// .getImportingLibrary());

		List plugins = LibraryService.getInstance().getCurrentMethodLibrary()
				.getMethodPlugins();
		for (Iterator it = plugins.iterator(); it.hasNext();) {
			MethodPlugin plugin = (MethodPlugin) it.next();
			if (guids.contains(plugin.getGuid())) {
				if (plugin.getUserChangeable().booleanValue() == false) {
					// plugin is locked
					modifyInfo.lockedPlugins.add(plugin);
				} else if (checkEdit) {
					// plugin exists in the current library, make sure the
					// plugin is updatable
					// IStatus status = TngUtil.checkEdit(plugin, getShell());
					IStatus status = TngUtil.checkEdit(plugin, shell);
					if (!status.isOK()) {
						modifyInfo.readonlyPlugins.add(plugin);
					}
				}
			}
		}

		return modifyInfo;
	}

	/**
	 * @see org.eclipse.jface.wizard.WizardPage#canFlipToNextPage()
	 */
	public boolean canFlipToNextPage() {
		return isPageComplete();
	}

	private void applyToStatusLine() {
		if (status != okStatus)
			setErrorMessage(status.getMessage());
		else {
			setErrorMessage(null);
		}
	}

}
