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
package org.eclipse.epf.authoring.ui.wizards;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.AuthoringUIText;
import org.eclipse.epf.library.LibraryManagerFactory;
import org.eclipse.epf.library.ui.preferences.LibraryUIPreferences;
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
import org.eclipse.swt.widgets.Text;

/**
 * A wizard page that prompts the user to enter the name and brief description
 * for a new method library.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class NewLibraryMainPage extends BaseWizardPage {

	/**
	 * The wizard page name.
	 */
	public static final String PAGE_NAME = NewLibraryMainPage.class.getName();

	private Shell shell;

	private Combo libraryPathCombo;

	private Button browseButton;

	private Text briefDescText;

	private String libraryType;
	
	private Button dummyButton;
	private Button synFreeButton;

	/**
	 * Creates a new instance.
	 */
	public NewLibraryMainPage() {
		super(PAGE_NAME);
		setTitle(AuthoringUIResources.newLibraryWizardMainPage_title);
		setDescription(AuthoringUIResources.newLibraryWizardMainPage_description);
		setImageDescriptor(AuthoringUIPlugin.getDefault().getImageDescriptor(
				"full/wizban/New.gif")); //$NON-NLS-1$
	}

	/**
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(Composite)
	 */
	public void createControl(Composite parent) {
		shell = parent.getShell();

		Composite composite = createGridLayoutComposite(parent, 3);

		createLabel(composite, AuthoringUIResources.libraryPathLabel_text);

		libraryPathCombo = createCombobox(composite);

		browseButton = createButton(composite,
				AuthoringUIText.BROWSE_BUTTON_TEXT);

		createVerticallyAlignedLabel(composite,
				AuthoringUIText.DESCRIPTION_TEXT);

		briefDescText = createEditableText(composite, 400, 80, 1);
		
		dummyButton = new Button(composite, SWT.CHECK);
		dummyButton.setVisible(false);
		
		
		synFreeButton = createCheckbox(composite, 
				AuthoringUIResources.ProcessEditorPreferencePage_synchronizationFree, 3);
		synFreeButton.setSelection(true);

		Map types = LibraryManagerFactory.getInstance().getLibraryTypes();
		for (Iterator it = types.keySet().iterator(); it.hasNext();) {
			libraryType = (String) it.next();
			break;
		}

		initControls();

		addListeners();

		setControl(composite);
	}

	/**
	 * Initializes the wizard page controls with data.
	 */
	protected void initControls() {
		String defaultLibraryPath = LibraryUIPreferences
				.getDefaultLibraryPath();
		File defaultLibraryDir = new File(defaultLibraryPath);
		String newLibraryPath = null;
		for (int i = 1;; i++) {
			File newLibraryDir = new File(defaultLibraryDir, "Library" + i); //$NON-NLS-1$
			if (!newLibraryDir.exists()) {
				newLibraryPath = newLibraryDir.getAbsolutePath();
				break;
			}
		}

		List<String> newLibraryPathsList = LibraryUIPreferences
				.getNewLibraryPathsList();
		if (newLibraryPath != null && newLibraryPath.length() > 0) {
			newLibraryPathsList.add(0, newLibraryPath);
		}
		if (newLibraryPathsList.size() > 0) {
			String[] newLibraryPaths = new String[newLibraryPathsList.size()];
			newLibraryPathsList.toArray(newLibraryPaths);
			libraryPathCombo.setItems(newLibraryPaths);
			libraryPathCombo.setText(newLibraryPaths[0]);
		}
	}

	/**
	 * Adds event listeners to the wizard page controls.
	 */
	protected void addListeners() {
		libraryPathCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				setPageComplete(isPageComplete());
				getWizard().getContainer().updateButtons();
			}
		});

		libraryPathCombo.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				setPageComplete(isPageComplete());
				getWizard().getContainer().updateButtons();
			}
		});

		browseButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				DirectoryDialog dialog = new DirectoryDialog(shell, SWT.NONE);
				dialog.setFilterPath(getLibraryPath());
				String selectedDir = dialog.open();
				if (selectedDir != null) {
					libraryPathCombo.add(selectedDir, 0);
					libraryPathCombo.setText(selectedDir);
				}
			}
		});
	}

	/**
	 * @see org.eclipse.jface.wizard.WizardPage#isCompleted()
	 */
	public boolean isPageComplete() {
		return getLibraryName().length() > 0;
	}

	/**
	 * Gets the library name.
	 */
	public String getLibraryName() {
		String libPath = getLibraryPath();
		String libName = new File(libPath).getName();
		if (!libPath.equals(libName)) {
			return libName;
		} else {
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * Gets the user specified library path.
	 */
	public String getLibraryPath() {
		return libraryPathCombo.getText().trim();
	}

	/**
	 * Gets the user specified method library description.
	 */
	public String getLibraryDescription() {
		return briefDescText.getText().trim();
	}

	/**
	 * Gets the user specified method library type.
	 */
	public String getLibraryType() {
		return libraryType;
	}

	public boolean isSynFree() {
		return synFreeButton.getSelection();
	}
	
}
