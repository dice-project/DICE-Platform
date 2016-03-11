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
package org.eclipse.epf.export.xml.wizards;

import org.eclipse.epf.export.ExportResources;
import org.eclipse.epf.export.xml.ExportXMLPlugin;
import org.eclipse.epf.export.xml.ExportXMLResources;
import org.eclipse.epf.export.xml.preferences.ExportXMLPreferences;
import org.eclipse.epf.export.xml.services.ExportXMLData;
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
 * A wizard page that displays the type of method library content to export.
 * 
 * @author Jinhua Xi
 * @author Kelvin Low
 * @since 1.0
 */
public class SelectExportTypePage extends BaseWizardPage implements Listener {

	public static final String PAGE_NAME = SelectExportTypePage.class.getName();

	private Button exportLibraryRadioButton;

	private Button exportPluginsRadioButton;

	// Postponed to next release.
	private Button exportConfigsRadioButton;

	/**
	 * Creates a new instance.
	 */
	public SelectExportTypePage() {
		super(PAGE_NAME);
		setTitle(ExportResources.selectExportTypePage_title);
		setDescription(ExportXMLResources.selectExportTypePage_desc);
		setImageDescriptor(ExportXMLPlugin.getDefault().getImageDescriptor(
				"full/wizban/ExportXML.gif")); //$NON-NLS-1$
	}

	/**
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(Composite)
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout());

		exportLibraryRadioButton = createRadioButton(container,
				ExportXMLResources.exportLibraryRadioButton_text, 1, false);

		exportPluginsRadioButton = createRadioButton(container,
				ExportXMLResources.exportPluginsRadioButton_text, 1, false);

		// Postponed to next release.
		exportConfigsRadioButton = createRadioButton(container,
				ExportXMLResources.exportConfigsRadioButton_text, 1, false);
		
		switch (ExportXMLPreferences.getExportType()) {
		case ExportXMLData.EXPORT_METHOD_LIBRARY:
			exportLibraryRadioButton.setSelection(true);
			break;
		case ExportXMLData.EXPORT_METHOD_PLUGINS:
			exportPluginsRadioButton.setSelection(true);
			break;
		// Postponed to next release.			
		case ExportXMLData.EXPORT_METHOD_CONFIGS:
			exportConfigsRadioButton.setSelection(true);
			break;
		}

		addListeners();

		setControl(container);
		setPageComplete(true);
	}

	private void addListeners() {
		exportLibraryRadioButton.addListener(SWT.Selection, this);
		exportPluginsRadioButton.addListener(SWT.Selection, this);
		// Postponed to next release.
		exportConfigsRadioButton.addListener(SWT.Selection, this);
	}

	/**
	 * @see org.eclipse.swt.widgets.Listener#handleEvent(Event)
	 */
	public void handleEvent(Event event) {
		setPageComplete(isPageComplete());
		getWizard().getContainer().updateButtons();
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
	 * @see org.eclipse.jface.wizard.WizardPage#isCompleted()
	 */
	public IWizardPage getNextPage() {
		ExportXMLWizard wizard = (ExportXMLWizard) getWizard();

		if (exportLibraryRadioButton.getSelection()) {
			wizard.xmlData.setExportType(ExportXMLData.EXPORT_METHOD_LIBRARY);
			return wizard.selectXMLFilePage;
		} else if (exportPluginsRadioButton.getSelection()) {
			wizard.xmlData.setExportType(ExportXMLData.EXPORT_METHOD_PLUGINS);
			return wizard.selectPluginPage;
		}
		// Postponed to next release.
		else if (exportConfigsRadioButton.getSelection()) {
			wizard.xmlData.setExportType(ExportXMLData.EXPORT_METHOD_CONFIGS);
			return wizard.selectConfigPage;
		}
		return null;
	}

}
