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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.epf.export.wizards.DestinationCommonPage;
import org.eclipse.epf.importing.ImportPlugin;
import org.eclipse.epf.importing.ImportResources;
import org.eclipse.epf.importing.services.PluginImportData;
import org.eclipse.epf.importing.services.PluginImportingService;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.ui.LibraryUIResources;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

/**
 * A wizard page that prompts the user to select a directory that contains the
 * method plug-ins to import.
 * 
 * @author Jeff Hardy
 * @author Kelvin Low
 * @since 1.0
 */
public class SelectImportPluginSource extends DestinationCommonPage {

	public static final String PAGE_NAME = SelectImportPluginSource.class
			.getName();

	private static final Status OK_STATUS = new Status(IStatus.OK,
			ImportResources.SelectImportPluginSource_not_used, 0, "", null); //$NON-NLS-1$

	private PluginImportData data;

	private PluginImportingService service;

	private Status status = OK_STATUS;

	protected SelectImportPluginSource() {
		super(PAGE_NAME, null);
		setTitle(ImportResources.selectPluginsDirWizardPage_title);
		setDescription(ImportResources.selectPluginsDirWizardPage_text);
		setImageDescriptor(ImportPlugin.getDefault().getImageDescriptor(
				"full/wizban/imp_meth_plugin_wizban.gif")); //$NON-NLS-1$
	}
	
	/**
	 * Creates a new instance.
	 */
	public SelectImportPluginSource(PluginImportData data,
			PluginImportingService service) {
		super(PAGE_NAME, data.llData);
		setTitle(ImportResources.selectPluginsDirWizardPage_title);
		setDescription(ImportResources.selectPluginsDirWizardPage_text);
		setImageDescriptor(ImportPlugin.getDefault().getImageDescriptor(
				"full/wizban/imp_meth_plugin_wizban.gif")); //$NON-NLS-1$
		this.service = service;
		this.data = data;
	}

	public void setData(PluginImportData data) {
		this.data = data;
		this.llData = data.llData;
	}
	
	public void setService(PluginImportingService service) {
		this.service = service;
	}
	
	/**
	 * Initializes the wizard page controls with data.
	 */
	protected void initControls() {
		String[] exportDirs = ImportUIPreferences.getImportPluginDirs();
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
		status = OK_STATUS;
		if (exportPathCombo.getText().length() > 0) {
			saveToDataModel();
			File libDir = new File(exportPathCombo.getText());
			if (!libDir.exists()) {
				status = new Status(IStatus.ERROR,
						ImportResources.SelectImportPluginSource_not_used, 0,
						ImportResources.SelectImportPluginSource_no_path, null);
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

		// Validate first before going to the next page.
		service.validate(null);
		String error = data.getErrorInfo().getError();
		if (error != null && error.length() > 0) {
			super.setErrorMessage(error);
			return this;
		} else {
			SelectPluginsToImport page = ((ImportPluginWizard) getWizard()).page2;
			page.onEnterPage(null);
			return page;
		}
	}

	/**
	 * @see org.eclipse.jface.wizard.WizardPage#canFlipToNextPage()
	 */
	public boolean canFlipToNextPage() {
		return isPageComplete();
	}

	private void applyToStatusLine() {
		if (status != OK_STATUS)
			setErrorMessage(status.getMessage());
		else {
			setErrorMessage(null);
		}
	}

	protected void createAdditionalControls(Composite composite) {
		Group optionGroup = new Group(composite, SWT.NONE);
		optionGroup.setLayout(new GridLayout(1, false));
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan = 3;
		optionGroup.setLayoutData(gridData);
		optionGroup.setText(ImportResources.optionGroup_text);

		boolean checkBasePlugins = ImportUIPreferences.getCheckBasePlugins();
		setCheckBasePlugins(checkBasePlugins);

		Button checkBaseRadioButton = createRadioButton(optionGroup,
				ImportResources.checkBaseRadioButton_text, 1, checkBasePlugins);
		checkBaseRadioButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				setCheckBasePlugins(true);
			}
		});

		Button ignoreRemoveRadioButton = createRadioButton(optionGroup,
				ImportResources.ignoreRemoveRadioButton_text, 1,
				!checkBasePlugins);
		ignoreRemoveRadioButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				setCheckBasePlugins(false);
			}
		});

	}

	private void setCheckBasePlugins(boolean checkBasePlugins) {
		service.setCheckBasePlugins(checkBasePlugins);
	}

}
