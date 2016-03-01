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
package org.eclipse.epf.library.ui.xmi;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.epf.library.ui.wizards.OpenLibraryWizardPage;
import org.eclipse.epf.library.xmi.XMILibraryManager;
import org.eclipse.epf.library.xmi.preferences.XMILibraryPreferences;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Shell;

/**
 * Prompts the user to select a path for a XMI method library.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class OpenXMILibraryWizardPage extends OpenLibraryWizardPage {

	private Combo locationCombo;

	private ModifyListener modifyListener = new ModifyListener() {
		public void modifyText(ModifyEvent e) {
			setPageComplete(isPageComplete());
			getWizard().getContainer().updateButtons();
		}
	};

	/**
	 * Creates a new instance.
	 */
	public OpenXMILibraryWizardPage(String pageId) {
		super(pageId);
	}

	/**
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(Composite)
	 */
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(3, false));

		createLabel(composite, XMILibraryUIResources.locationLabel_text);

		locationCombo = createCombobox(composite);
		locationCombo.addModifyListener(modifyListener);
		locationCombo.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				setPageComplete(isPageComplete());
				getWizard().getContainer().updateButtons();
			}
		});

		final Shell shell = parent.getShell();

		final Button browseButton = createButton(composite,
				XMILibraryUIResources.browseButton_text);
		browseButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				try {
					DirectoryDialog dialog = new DirectoryDialog(shell,
							SWT.NONE);
					dialog.setFilterPath(getLibraryPath());
					String selectedDir = dialog.open();
					if (selectedDir != null) {
						locationCombo.add(selectedDir, 0);
						locationCombo.setText(selectedDir);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		setPageComplete(true);
		setControl(composite);
	}

	/**
	 * @see org.eclipse.epf.ui.wizards.BaseWizardPage#onEnterPage(Object)
	 */
	public void onEnterPage(Object obj) {
		if (getLibraryPath().length() == 0) {
			locationCombo.removeModifyListener(modifyListener);
			locationCombo
					.setText(XMILibraryPreferences.getDefaultLibraryPath());
			locationCombo.addModifyListener(modifyListener);
		}
	}

	/**
	 * @see org.eclipse.jface.wizard.WizardPage#isCompleted()
	 */
	public boolean isPageComplete() {
		return getLibraryPath().length() > 0;
	}

	/**
	 * @see org.eclipse.jface.wizard.WizardPage#getNextPage()
	 */
	public IWizardPage getNextPage() {
		return null;
	}

	/**
	 * Returns the library path.
	 */
	public String getLibraryPath() {
		return locationCombo.getText().trim();
	}

	/**
	 * Returns the library specific user selections.
	 */
	public Map getSelections() {
		Map options = new HashMap();
		File libraryPath = new File(getLibraryPath());
		options.put(XMILibraryManager.ARG_LIBRARY_PATH, libraryPath
				.getAbsolutePath());
		return options;
	}

}
