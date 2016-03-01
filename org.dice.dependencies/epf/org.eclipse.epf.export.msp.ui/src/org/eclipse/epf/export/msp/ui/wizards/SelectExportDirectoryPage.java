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

import org.eclipse.epf.export.msp.ui.ExportMSPUIPlugin;
import org.eclipse.epf.export.msp.ui.ExportMSPUIResources;
import org.eclipse.epf.export.msp.ui.preferences.ExportMSPUIPreferences;
import org.eclipse.epf.ui.wizards.BaseWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Shell;

/**
 * A wizard page that prompts the user to specify the Microsoft Project name and
 * export directory.
 * 
 * @author Kelvin Low
 * @since 1.2
 */
public class SelectExportDirectoryPage extends BaseWizardPage {

	public static final String PAGE_NAME = SelectExportDirectoryPage.class
			.getName();

	protected Shell shell;

	protected Composite composite;

	protected Composite templateComposite;

	protected Combo projectNameCombo;

	protected Combo exportDirCombo;

	protected Button browseButton;

	protected ModifyListener modifyListener = new ModifyListener() {
		public void modifyText(ModifyEvent e) {
			setPageComplete(isPageComplete());
		}
	};

	/**
	 * Creates a new instance.
	 */
	public SelectExportDirectoryPage(String pageName) {
		super(pageName);
		setTitle(ExportMSPUIResources.selectExportDirWizardPage_title);
		setDescription(ExportMSPUIResources.selectExportDirWizardPage_text);
		setImageDescriptor(ExportMSPUIPlugin.getDefault().getImageDescriptor(
				"full/wizban/exp_ms_prj_wizban.gif")); //$NON-NLS-1$
	}

	/**
	 * Creates a new instance.
	 */
	public SelectExportDirectoryPage() {
		this(PAGE_NAME);
	}

	/**
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(Composite)
	 */
	public void createControl(Composite parent) {
		shell = parent.getShell();

		composite = createGridLayoutComposite(parent, 1);

		templateComposite = createGridLayoutComposite(composite, 3);

		createLabel(templateComposite,
				ExportMSPUIResources.projectNameLabel_text);

		projectNameCombo = createCombobox(templateComposite, 2);

		createLabel(templateComposite, ExportMSPUIResources.dirLabel_text);

		exportDirCombo = createCombobox(templateComposite);

		browseButton = createButton(templateComposite,
				ExportMSPUIResources.browseButton_text);

		initControls();

		addListeners();

		setControl(composite);
	}

	/**
	 * Initializes the wizard page controls with data.
	 */
	protected void initControls() {
		String[] templateNames = ExportMSPUIPreferences.getMSProjectNames();
		if (templateNames != null && templateNames.length > 0) {
			projectNameCombo.setItems(templateNames);
			projectNameCombo.setText(templateNames[0]);
		}

		String[] exportDirs = ExportMSPUIPreferences.getExportDirectories();
		if (exportDirs != null && exportDirs.length > 0) {
			exportDirCombo.setItems(exportDirs);
			exportDirCombo.setText(exportDirs[0]);
		}

		browseButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				try {
					DirectoryDialog dialog = new DirectoryDialog(shell,
							SWT.NONE);
					String selectedDir = dialog.open();
					if (selectedDir != null) {
						exportDirCombo.add(selectedDir, 0);
						exportDirCombo.setText(selectedDir);
					}
				} catch (Exception e) {
					ExportMSPUIPlugin.getDefault().getLogger().logError(e);
				}
			}
		});
	}

	/**
	 * Adds event handlers to the wizard page controls.
	 */
	protected void addListeners() {
		projectNameCombo.addModifyListener(modifyListener);
		exportDirCombo.addModifyListener(modifyListener);
	}

	/**
	 * @see org.eclipse.jface.wizard.WizardPage#isPageComplete()
	 */
	public boolean isPageComplete() {
		return getMSProjectName().length() > 0
				&& getExportDirectory().length() > 0;
	}

	/**
	 * Returns the Microsoft Project name.
	 */
	public String getMSProjectName() {
		return projectNameCombo.getText().trim();
	}

	/**
	 * Returns the export directory.
	 */
	public String getExportDirectory() {
		return exportDirCombo.getText().trim();
	}

	/**
	 * @see org.eclipse.jface.wizard.WizardPage#dispose()
	 */
	public void dispose() {
		ExportMSPUIPreferences.addMSProjectName(getMSProjectName());
		ExportMSPUIPreferences.addExportDir(getExportDirectory());
		modifyListener = null;
		super.dispose();
	}

}