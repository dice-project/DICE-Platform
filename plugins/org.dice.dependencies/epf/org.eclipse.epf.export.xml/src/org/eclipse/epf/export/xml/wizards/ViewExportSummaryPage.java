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
import org.eclipse.epf.export.wizards.ExportPluginSummaryPage;
import org.eclipse.jface.wizard.IWizardPage;

/**
 * A wizard page that displays a summary of the selected method plug-ins that
 * will be exported.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class ViewExportSummaryPage extends ExportPluginSummaryPage {

	/**
	 * Creates a new instance.
	 */
	public ViewExportSummaryPage(PluginExportData data) {
		super(data);
	}

	/**
	 * @see org.eclipse.jface.wizard.WizardPage#getNextPage()
	 */
	public IWizardPage getNextPage() {
		ExportXMLWizard wizard = (ExportXMLWizard) getWizard();
		return wizard.selectXMLFilePage;
	}

}
