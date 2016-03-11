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

/**
 * A wizard page that prompts the user to select the publishing options for the
 * process web site.
 * 
 * @author Kelvin Low
 * @since 1.2
 */
public class SelectPublishingOptionsPage extends
		org.eclipse.epf.publishing.ui.wizards.SelectPublishingOptionsPage {

	public static final String PAGE_NAME = SelectPublishingOptionsPage.class
			.getName();

	/**
	 * Creates a new instance.
	 */
	public SelectPublishingOptionsPage(String pageName) {
		super(pageName);
		setTitle(ExportMSPUIResources.selectPublishOptionsWizardPage_title);
		setDescription(ExportMSPUIResources.selectPublishOptionsWizardPage_text);
		setImageDescriptor(ExportMSPUIPlugin.getDefault().getImageDescriptor(
				"full/wizban/exp_ms_prj_wizban.gif")); //$NON-NLS-1$
	}

	/**
	 * Creates a new instance.
	 */
	public SelectPublishingOptionsPage() {
		this(PAGE_NAME);
	}

}
