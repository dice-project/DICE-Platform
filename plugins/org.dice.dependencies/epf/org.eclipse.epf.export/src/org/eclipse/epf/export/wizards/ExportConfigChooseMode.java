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

import org.eclipse.epf.export.ExportPlugin;
import org.eclipse.epf.export.ExportResources;
import org.eclipse.epf.export.services.ConfigurationExportData;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.ui.LibraryUIResources;
import org.eclipse.epf.ui.wizards.BaseWizardPage;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

/**
 * A wizard page that displays the types of configuration that can be exported.
 * 
 * @author Bingxue Xu
 * @author Kelvin Low
 * @since 1.0
 */
public class ExportConfigChooseMode extends BaseWizardPage implements Listener {

	public static final String PAGE_NAME = ExportConfigChooseMode.class
			.getName();

	private Button oneConfigButton;

	private Button configSpecButton;

	private ConfigurationExportData data;

	/**
	 * Creates a new instance.
	 */
	public ExportConfigChooseMode(ConfigurationExportData data) {
		super(PAGE_NAME);
		setTitle(ExportResources.selectExportTypePage_title);
		setDescription(ExportResources.selectExportTypePage_desc);
		setImageDescriptor(ExportPlugin.getDefault().getImageDescriptor(
				"full/wizban/exp_lib_conf_wizban.gif")); //$NON-NLS-1$
		this.data = data;
	}

	/**
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(Composite)
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout());

		oneConfigButton = createRadioButton(container,
				ExportResources.selectExportTypePage_configLabel_text, 1, true);

		configSpecButton = createRadioButton(container,
				ExportResources.selectExportTypePage_configSpecsLabel_text, 1,
				false);

		addListeners();

		setControl(container);
		setPageComplete(true);
	}

	private void addListeners() {
		oneConfigButton.addListener(SWT.Selection, this);
		configSpecButton.addListener(SWT.Selection, this);
	}

	/**
	 * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
	 */
	public void handleEvent(Event event) {
		setPageComplete(isPageComplete());
		getWizard().getContainer().updateButtons();
	}

	private void saveDataToModel() {
		data.exportOneConfig = oneConfigButton.getSelection();
		data.exportConfigSpecs = configSpecButton.getSelection();
	}

	/**
	 * @see org.eclipse.jface.wizard.WizardPage#isPageComplete()
	 */
	public boolean isPageComplete() {
		if (LibraryService.getInstance().getCurrentMethodLibrary() == null) {
			setErrorMessage(LibraryUIResources.noOpenLibraryWarning_msg);
			return false;
		}

		return getErrorMessage() == null;
	}

	/**
	 * @see org.eclipse.jface.wizard.WizardPage#getNextPage()
	 */
	public IWizardPage getNextPage() {
		saveDataToModel();
		if (data.exportOneConfig) {
			ExportConfigSelectConfigPage page = ((ExportConfigurationWizard) getWizard()).selectConfigPage;
			return page;
		} else if (data.exportConfigSpecs) {
			ExportConfigSelectSpecsPage page = ((ExportConfigurationWizard) getWizard()).selectSpecsPage;
			return page;
		}
		return null;
	}

}
