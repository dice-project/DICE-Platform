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

import org.eclipse.epf.authoring.ui.preferences.LibraryLocationData;
import org.eclipse.epf.export.ExportResources;
import org.eclipse.epf.ui.wizards.BaseWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

/**
 * A wizard page that prompts the user to select a destination directory for an
 * export or import operation.
 * 
 * @author Bingxue Xu
 * @author Kelvin Low
 * @since 1.0
 */
public class DestinationCommonPage extends BaseWizardPage implements Listener {

	protected Composite composite;

	protected Combo exportPathCombo;

	protected Button browseButton;

	protected LibraryLocationData llData;

	/**
	 * Creates a new instance.
	 */
	public DestinationCommonPage(String pageName, LibraryLocationData llData) {
		super(pageName);
		setTitle(ExportResources.DestinationCommonPage_title);
		setDescription(ExportResources.DestinationCommonPage_desc);
		this.llData = llData;
	}

	/**
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl(Composite parent) {
		composite = createGridLayoutComposite(parent, 3);

		createLabel(composite, ExportResources.DestinationCommonPage_label_dir);
		
		exportPathCombo = createCombobox(composite, 1);
		
		browseButton = createButton(composite,
				ExportResources.DestinationCommonPage_label_browse);

		createLabel(composite, ""); //$NON-NLS-1$		

		initControls();
		
		addListeners();

		setControl(composite);

		createAdditionalControls(composite);

		setPageComplete(false);
	}

	/**
	 * Initializes the wizard page controls with data.
	 */
	protected void initControls() {
		exportPathCombo.setEnabled(true);
		browseButton.setEnabled(true);
	}
	
	/**
	 * Adds event handlers to the wizard page controls.
	 */
	protected void addListeners() {	
		exportPathCombo.addListener(SWT.FocusIn, this);
		exportPathCombo.addListener(SWT.Modify, this);
		exportPathCombo.addListener(SWT.FocusOut, this);
		exportPathCombo.addListener(SWT.Selection, this);
		
		browseButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				openDirectoryDialog();
			}
		});		
	}
	
	/**
	 * Adds additional controls to this wizard page.
	 * 
	 * @param composite the parent composite
	 */
	protected void createAdditionalControls(Composite composite) {
	}

	/**
	 * @see org.eclipse.jface.dialogs.DialogPage#setVisible(boolean)
	 */
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible) {
			Display display = composite.getDisplay();
			if (!(display == null || display.isDisposed())) {
				display.asyncExec(new Runnable() {
					public void run() {
						exportPathCombo.setFocus();
					}
				});
			}
		}
	}

	/**
	 * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
	 */
	public void handleEvent(Event event) {
		Wizard wizard = (Wizard) getWizard();
		setPageComplete(isPageComplete());
		wizard.getContainer().updateButtons();
	}

	protected void saveToDataModel() {
		String libName = exportPathCombo.getText();
		if (libName.length() > 0) {
			if (!libName.endsWith(File.separator)) {
				libName = libName.substring(
						libName.lastIndexOf(File.separator) + 1, libName
								.length());
			} else
				libName = ""; //$NON-NLS-1$
		}
		llData.setLibName(libName);
		llData.setParentFolder(exportPathCombo.getText());
	}

	private void openDirectoryDialog() {
		try {
			DirectoryDialog dd = new DirectoryDialog(composite.getShell(),
					SWT.NONE);
			dd.setFilterPath(exportPathCombo.getText());

			String destination = dd.open();
			if (destination != null) {
				exportPathCombo.setText(destination);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * use the opened dirs to init combo list
	 */
	protected void initComboItems() {
		return;
	}

}
