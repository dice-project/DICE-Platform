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
package org.eclipse.epf.importing.xml.wizards;

import java.io.File;

import org.eclipse.epf.importing.ImportResources;
import org.eclipse.epf.importing.xml.ImportXMLPlugin;
import org.eclipse.epf.importing.xml.ImportXMLResources;
import org.eclipse.epf.importing.xml.preferences.ImportXMLPreferences;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.ui.LibraryUIResources;
import org.eclipse.epf.ui.wizards.BaseWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * A wizard page that prompts the user to select a XML file to import.
 * 
 * @author Jinhua Xi
 * @author Kelvin Low
 * @since 1.0
 */
public class SelectXMLFilePage extends BaseWizardPage {

	public static final String PAGE_NAME = SelectXMLFilePage.class.getName();

	private Text pathText;

	private String path;

	private boolean mergeOption = true;
	
	private int mergeLevel = 0;	//0: undetermined, 1: default merge, 2: fine grade merge
	
	private boolean checkBasePlugins = true;

	/**
	 * Creates a new instance.
	 */
	public SelectXMLFilePage() {
		super(PAGE_NAME);
		setTitle(ImportXMLResources.selectXMLFilePage_title);
		setDescription(ImportXMLResources.selectXMLFilePage_desc);
		setImageDescriptor(ImportXMLPlugin.getDefault().getImageDescriptor(
				"full/wizban/imp_xml_wizban.gif")); //$NON-NLS-1$
	}

	/**
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(Composite)
	 */
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(3, false));
		composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label fileLabel = new Label(composite, SWT.NONE);
		fileLabel.setText(ImportXMLResources.fileLabel_text); 

		pathText = new Text(composite, SWT.BORDER);
		pathText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		pathText.setText(ImportXMLPreferences.getXMLFile());		
		pathText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				if (isValidPath(pathText.getText().trim())) {
					setPageComplete(true);
					setErrorMessage(null);
				} else {
					setPageComplete(false);
					setErrorMessage(ImportXMLResources.invalidXMLFile_error);
				}
			}
		});

		Button browseButton = new Button(composite, SWT.PUSH);
		browseButton.setLayoutData(new GridData(GridData.END));
		browseButton.setText(ImportXMLResources.browseButton_text);
		browseButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(Display.getCurrent()
						.getActiveShell(), SWT.OPEN);
				fd.setFilterExtensions(new String[] { "*.xml", "*.*" }); //$NON-NLS-1$ //$NON-NLS-2$
				String path = fd.open();
				boolean ok = false;
				if (path != null) {
					pathText.setText(path);
					ok = isValidPath(path);
				}
				setPageComplete(ok);
			}
		});
		
		Group optionGroup = new Group(composite, SWT.NONE);
		optionGroup.setLayout(new GridLayout(1, false));
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan = 3;
		optionGroup.setLayoutData(gridData);
		optionGroup.setText(ImportXMLResources.optionGroup_text);

		mergeOption = ImportXMLPreferences.getMergeOption();
		mergeLevel = ImportXMLPreferences.getMergeLevel();
		
		final Button overwriteRadioButton = createRadioButton(optionGroup,
				ImportXMLResources.overwriteRadioButton_text, 1, !mergeOption);
		overwriteRadioButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (!overwriteRadioButton.getSelection()) {
					return;
				}
				mergeLevel = 0;
				mergeOption = false;
			}
		});
		
		final Button mergeRadioButton = createRadioButton(optionGroup,
				ImportXMLResources.mergeRadioButton_text, 1, mergeOption && mergeLevel != 2);
		mergeRadioButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (!mergeRadioButton.getSelection()) {
					return;
				}
				mergeLevel = 1;
				mergeOption = true;
			}
		});
		
		final Button mergeRadioButton2 = createRadioButton(optionGroup,
				ImportXMLResources.mergeRadioButton2_text, 1, mergeOption
						&& mergeLevel == 2);
		mergeRadioButton2.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (!mergeRadioButton2.getSelection()) {
					return;
				}
				mergeOption = true;
				mergeLevel = 2;
			}
		});
					
		Group optionGroup1 = new Group(composite, SWT.NONE);
		optionGroup1.setLayout(new GridLayout(1, false));
		GridData gridData1 = new GridData(GridData.FILL_HORIZONTAL);
		gridData1.horizontalSpan = 3;
		optionGroup1.setLayoutData(gridData1);
		optionGroup1.setText(ImportXMLResources.optionGroup1_text);
		checkBasePlugins = ImportXMLPreferences.getCheckBasePluginsOption();
			
		Button checkBaseRadioButton = createRadioButton(optionGroup1,
				ImportResources.checkBaseRadioButton_text, 1, checkBasePlugins);	
		checkBaseRadioButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				checkBasePlugins = true;
			}
		});
		
		Button ignoreRemoveRadioButton = createRadioButton(optionGroup1,
				ImportResources.ignoreRemoveRadioButton_text, 1, !checkBasePlugins);
		ignoreRemoveRadioButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				checkBasePlugins = false;
			}
		});

		setControl(composite);

		setPageComplete(isValidPath(pathText.getText().trim()));
		
		createAdditionalControls(composite);
	}

	/**
	 * Adds additional controls to this wizard page.
	 * 
	 * @param composite the parent composite
	 */
	protected void createAdditionalControls(Composite composite) {
	}
	
	/**
	 * Checks whether the user specific path is valid.
	 * 
	 * @param path
	 *            the user specific path
	 * @return <code>true</code> if the user specified path is valid.
	 */
	private boolean isValidPath(String path) {
		File file = new File(path);
		if (file.exists() && file.isFile()) {
			this.path = path;
			return true;
		}
		return false;
	}

	/**
	 * Gets the user specified XML file.
	 * 
	 * @return an absolute path to the XML file
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Gets the user specified import option.
	 * 
	 * @return <code>true</code> if the user selected the merge option,
	 *         <code>false</code> if the user selected the overwrite option
	 */
	public boolean getMergeOption() {
		return mergeOption;
	}

	/**
	 * @see org.eclipse.jface.wizard.WizardPage#isPageComplete()
	 */
	public boolean isPageComplete() {
		if (LibraryService.getInstance().getCurrentMethodLibrary() == null) {
			setErrorMessage(LibraryUIResources.noOpenLibraryWarning_msg);
			return false;
		}

		return super.isPageComplete();
	}	
	
	/**
	 * Gets the user specified check base plugin option.
	 * 
	 * @return <code>true</code> if the user selected the check base plugin option,
	 *         <code>false</code> if the user selected the ignore check base plugin option
	 */
	public boolean getCheckBasePlugins() {
		return checkBasePlugins;
	}

	public int getMergerLevel() {
		return mergeLevel;
	}
	
}
