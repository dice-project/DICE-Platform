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
package org.eclipse.epf.authoring.ui.wizards;

import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.ui.wizards.BaseWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

/**
 * The wizard page which describes custom synchronization.
 * 
 * @author BingXue Xu
 * @since 1.0
 *
 */
public class SynchronizationDescriptionPage extends BaseWizardPage {

	private SynchronizationChoices syncChoices = null;
	
	private Text ctrl_explainText;
	
	/**
	 * Creates a new instance.
	 */
	public SynchronizationDescriptionPage(String pageName,
			SynchronizationChoices choices) {
		super(pageName);
		setTitle(AuthoringUIResources.synchronizationWizard_descriptionPage_title); 
		setDescription(AuthoringUIResources.synchronizationWizard_descriptionPage_text); 
		this.syncChoices = choices;
	}
	
	/**
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl(Composite parent) {
		// Create the composite to hold the widgets.
		Composite composite = new Composite(parent, SWT.NULL);
		composite.setLayout(new GridLayout(1, false));
		
		String explainText = AuthoringUIResources.synchronizationWizard_descriptionPage_explainText; 
		ctrl_explainText = createMultiLineText(composite, " ", 400, 240, 1); //$NON-NLS-1$
		ctrl_explainText.setText(explainText);
		
		setControl(composite);

	}

}
