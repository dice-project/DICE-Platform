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
import org.eclipse.jface.wizard.IWizardPage;

/**
 * A wizard page that prompts the user to select one or more method plug-ins to
 * export.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class SelectPluginPage extends
		org.eclipse.epf.export.wizards.SelectPluginPage {

	/**
	 * Creates a new instance.
	 */
	public SelectPluginPage(PluginExportData data) {
		super(data);
	}

	/**
	 * @see org.eclipse.jface.wizard.WizardPage#getNextPage()
	 */
	public IWizardPage getNextPage() {
		super.saveDataToModel();
		ExportXMLWizard wizard = (ExportXMLWizard) getWizard();
		wizard.xmlData.setSelectedPlugins(wizard.pluginData.selectedPlugins);
		ViewPluginInfoPage page = wizard.viewPluginInfoPage;
		page.onEnterPage(null);
		return page;
	}

}
