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
package org.eclipse.epf.library.ui.wizards;

import java.io.File;
import java.util.List;

import org.eclipse.epf.common.utils.FileUtil;
import org.eclipse.epf.library.ui.LibraryUIPlugin;
import org.eclipse.epf.library.ui.LibraryUIResources;
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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

/**
 * A wizard page that prompts the user to select a path to open a method
 * library.
 * 
 * @author Kelvin Low
 * @since 1.2
 */
public class OpenLibraryMainPage extends BaseWizardPage {

	/**
	 * The wizard page name.
	 */
	public static final String PAGE_NAME = OpenLibraryMainPage.class.getName();

	protected static final String TYPE_ID = "typeId"; //$NON-NLS-1$	

	protected Shell shell;

	protected Composite composite;

	protected Label pathLabel;

	protected Combo libraryPathCombo;

	protected Button browseButton;

	protected String libraryType;

	/**
	 * Creates a new instance.
	 * 
	 * @param pageName
	 *            the wizard page name
	 */
	public OpenLibraryMainPage(String pageName) {
		super(pageName);
		setTitle(LibraryUIResources.openLibraryWizard_title);
		setDescription(LibraryUIResources.openLibraryMainWizardPage_title);
		setImageDescriptor(LibraryUIPlugin.getDefault().getImageDescriptor(
				"full/wizban/Open.gif")); //$NON-NLS-1$
	}

	/**
	 * Creates a new instance.
	 */
	public OpenLibraryMainPage() {
		this(PAGE_NAME);
	}

	/**
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(Composite)
	 */
	public void createControl(Composite parent) {
		shell = parent.getShell();

		composite = createGridLayoutComposite(parent, 3);

		pathLabel = createLabel(composite, LibraryUIResources.pathLabel_text);

		libraryPathCombo = createCombobox(composite);

		browseButton = createButton(composite,
				LibraryUIResources.browseButton_text);

		initControls();

		addListeners();

		setControl(composite);
	}

	/**
	 * Initializes the wizard page controls with data.
	 */
	protected void initControls() {
		List<String> openLibraryPathsList = LibraryUIPreferences
				.getOpenLibraryPathsList();		
		if (openLibraryPathsList.size() > 0) {
			String[] openLibraryPaths = LibraryUIPreferences
					.getOpenLibraryPaths();
			libraryPathCombo.setItems(openLibraryPaths);
			if (openLibraryPaths.length > 0) {
				libraryPathCombo.setText(openLibraryPaths[0]);
			}			
		} else {
			String libraryPath = LibraryUIPreferences
					.getDefaultLibraryPath() + FileUtil.FILE_SEP + "Library1"; //$NON-NLS-1$
			openLibraryPathsList.add(0, libraryPath);
			String[] openLibraryPaths = new String[openLibraryPathsList.size()];
			openLibraryPathsList.toArray(openLibraryPaths);
			libraryPathCombo.setItems(openLibraryPaths);
			libraryPathCombo.setText(openLibraryPaths[0]);
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
	 * Gets the user specified method library type.
	 */
	public String getLibraryType() {
		return libraryType;
	}

}
