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

import org.eclipse.epf.importing.ImportPlugin;
import org.eclipse.epf.importing.ImportResources;
import org.eclipse.epf.importing.services.ConfigurationImportService;
import org.eclipse.epf.ui.wizards.BaseWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

/**
 * A wizard page that displays the library configuration elements that will be
 * imported.
 * 
 * @author Jeff Hardy
 * @author Kelvin Low
 * @since 1.0
 */
public class SelectConfigsToImport extends BaseWizardPage {

	public static final String PAGE_NAME = SelectConfigsToImport.class
			.getName();

	private ConfigurationImportService service;

	private Composite container;

	private DiffReportViewer diff;

	/**
	 * Creates a new instance.
	 */
	public SelectConfigsToImport(ConfigurationImportService service) {
		super(PAGE_NAME);
		setTitle(ImportResources.reviewChangesWizardPage_title);
		setDescription(ImportResources.reviewChangesWizardPage_text);
		setImageDescriptor(ImportPlugin.getDefault().getImageDescriptor(
				"full/wizban/imp_lib_conf_wizban.gif")); //$NON-NLS-1$		
		this.service = service;
	}

	/**
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(Composite)
	 */
	public void createControl(Composite parent) {
		container = new Composite(parent, SWT.NONE);
		container.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		container.setLayout(new GridLayout(1, false));

		createLabel(container, ImportResources.SelectConfigsToImport_label1, 1);
		diff = new DiffReportViewer(container, service.getImportData());

		setControl(container);
		setPageComplete(false);
	}

	/**
	 * @see org.eclipse.jface.wizard.WizardPage#isCompleted()
	 */
	public boolean isPageComplete() {
		((ImportConfigurationWizard) getWizard()).okToComplete = true;
		return false;
	}

	/**
	 * @see org.eclipse.epf.ui.wizards.BaseWizardPage#onEnterPage(Object)
	 */
	public void onEnterPage(Object obj) {
		diff.showReport(service.getDiffTree());
		((ImportConfigurationWizard) getWizard()).okToComplete = true;
	}

}
