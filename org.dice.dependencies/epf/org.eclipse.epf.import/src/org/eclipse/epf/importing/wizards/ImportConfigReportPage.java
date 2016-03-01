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

import org.eclipse.epf.importing.ImportResources;
import org.eclipse.epf.importing.services.ConfigurationImportService;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

/**
 * A wizard page that displays the result of an import operation.
 * 
 * @author Jinhua Xi
 * @since 1.0
 */
public class ImportConfigReportPage extends WizardPage {

	ConfigurationImportService importingService;

	DiffReportViewer viewer;

	/**
	 * Creates a new instance.
	 */
	public ImportConfigReportPage(ConfigurationImportService importingService) {
		super(ImportResources.ImportConfigReportPage_title);
		this.importingService = importingService;
	}

	/**
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(Composite)
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		parent.setLayoutData(new GridData(GridData.FILL_BOTH
				| GridData.GRAB_VERTICAL));
		container.setLayoutData(new GridData(GridData.FILL_BOTH
				| GridData.GRAB_VERTICAL));

		viewer = new DiffReportViewer(container, importingService
				.getImportData());

		setControl(container);

		setPageComplete(false);
	}

	/**
	 * Show result.
	 */
	public void showResult() {
		// Analyze the importing configuration and report result.
		importingService.analyze(null);

		viewer.showReport(importingService.getDiffTree());

		setPageComplete(true);
	}

}
