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

import java.io.File;

import org.eclipse.epf.export.ExportPlugin;
import org.eclipse.epf.export.ExportResources;
import org.eclipse.epf.export.services.PluginExportData;

/**
 * A wizard page that informs the user that changes to the existing method
 * plug-ins will be saved prior to an export operation.
 * 
 * @author Jinhua Xi
 * @author Kelvin Low
 * @since 1.0
 */
public class SavePluginPage extends DestinationCommonPage {

	public static final String PAGE_NAME = SavePluginPage.class.getName();

	/**
	 * Creates a new instance.
	 */
	public SavePluginPage(PluginExportData data) {
		super(PAGE_NAME, data.llData);
		setTitle(ExportResources.specifyPathPage_title);
		setDescription(ExportResources.specifyPathPage_desc);
		setImageDescriptor(ExportPlugin.getDefault().getImageDescriptor(
				"full/wizban/exp_meth_plugin_wizban.gif")); //$NON-NLS-1$
	}

	/**
	 * Initializes the wizard page controls with data.
	 */
	protected void initControls() {
		String[] exportDirs = ExportUIPreferences.getExportPluginDirs();
		if (exportDirs != null && exportDirs.length > 0) {
			exportPathCombo.setItems(exportDirs);
			exportPathCombo.setText(exportDirs[0]);
		}
		super.initControls();
	}

	/**
	 * @see org.eclipse.jface.wizard.WizardPage#isPageComplete()
	 */
	public boolean isPageComplete() {
		ExportPluginWizard wizard = (ExportPluginWizard) getWizard();
		if (exportPathCombo.getText().length() > 0
				&& !exportPathCombo.getText().endsWith(File.separator)) {
			saveToDataModel();
			wizard.okToComplete = true;
			return true;
		}
		wizard.okToComplete = false;
		return false;
	}

}
