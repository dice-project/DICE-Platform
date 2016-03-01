//------------------------------------------------------------------------------
// Copyright (c) 2005, 2006 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.export.xml.wizards;

import org.eclipse.epf.export.services.PluginExportData;
import org.eclipse.epf.export.wizards.PluginInfoPage;
import org.eclipse.jface.wizard.IWizardPage;

/**
 * A wizard page that prompts the user to review the dependencies of the method
 * plug-ins that have been selected for export.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class ViewPluginInfoPage extends PluginInfoPage {

	/**
	 * Creates a new instance.
	 */
	public ViewPluginInfoPage(PluginExportData data) {
		super(data);
	}

	/**
	 * @see org.eclipse.jface.wizard.WizardPage#getNextPage()
	 */
	public IWizardPage getNextPage() {
		super.saveDataToModel();
		ExportXMLWizard wizard = (ExportXMLWizard) getWizard();
		wizard.xmlData.setAssociatedConfigs(wizard.pluginData.associatedConfigMap);
		ViewExportSummaryPage page = wizard.viewExportSummaryPage;
		page.onEnterPage(null);
		return page;
	}

}
