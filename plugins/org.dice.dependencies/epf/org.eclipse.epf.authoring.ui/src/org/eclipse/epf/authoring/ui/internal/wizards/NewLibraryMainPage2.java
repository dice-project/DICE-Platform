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
package org.eclipse.epf.authoring.ui.internal.wizards;

import java.util.Iterator;
import java.util.Map;

import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.AuthoringUIText;
import org.eclipse.epf.library.LibraryManagerFactory;
import org.eclipse.epf.library.ui.wizards.NewLibraryWizardPage;
import org.eclipse.epf.library.ui.wizards.NewLibraryWizardPageFactory;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * A wizard page that prompts the user to enter the name, brief description and
 * type for a new method library.
 * 
 * @author Kelvin Low
 * @since 1.2
 * @deprecated
 */
public class NewLibraryMainPage2 extends WizardPage {

	/**
	 * The wizard page name.
	 */
	public static final String PAGE_NAME = NewLibraryMainPage2.class
			.getName();

	private static final String TYPE_ID = "typeId"; //$NON-NLS-1$	

	private Text nameText;

	private Text briefDescText;

	private Button[] radioButtons;

	/**
	 * Creates a new instance.
	 */
	public NewLibraryMainPage2() {
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
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(3, false));

		Label nameLabel = new Label(composite, SWT.NORMAL);
		nameLabel.setText(AuthoringUIText.NAME_TEXT);

		nameText = new Text(composite, SWT.BORDER);
		nameText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		((GridData) nameText.getLayoutData()).horizontalSpan = 2;

		Label briefDescLabel = new Label(composite, SWT.NORMAL);
		briefDescLabel.setText(AuthoringUIText.DESCRIPTION_TEXT);

		briefDescText = new Text(composite, SWT.BORDER | SWT.MULTI | SWT.WRAP
				| SWT.V_SCROLL);
		briefDescText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		((GridData) briefDescText.getLayoutData()).horizontalSpan = 2;
		((GridData) briefDescText.getLayoutData()).heightHint = 70;
		((GridData) briefDescText.getLayoutData()).widthHint = 480;

		Label libraryTypeLabel = new Label(composite, SWT.NORMAL);
		libraryTypeLabel.setText(AuthoringUIResources.libraryTypeLabel_text);

		Composite typeComposite = new Composite(composite, SWT.NORMAL);
		typeComposite.setLayout(new GridLayout(2, false));
		typeComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Map types = LibraryManagerFactory.getInstance().getLibraryTypes();
		radioButtons = new Button[types.size()];
		int radioButtonIndex = 0;
		for (Iterator it = types.keySet().iterator(); it.hasNext();) {
			String typeId = (String) it.next();
			String typeName = (String) types.get(typeId);
			Button radioButton = new Button(typeComposite, SWT.RADIO);
			radioButton.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent event) {
					setPageComplete(isPageComplete());
					getWizard().getContainer().updateButtons();
				}
			});
			radioButton.setText(typeName);
			radioButton.setData(TYPE_ID, typeId);
			radioButtons[radioButtonIndex++] = radioButton;
		}

		if (radioButtons.length > 0) {
			radioButtons[0].setSelection(true);
		}

		addListeners();

		setPageComplete(isPageComplete());

		setControl(composite);
	}

	/**
	 * @see org.eclipse.jface.wizard.WizardPage#getNextPage()
	 */
	public IWizardPage getNextPage() {
		NewLibraryWizardPage wizardPage = NewLibraryWizardPageFactory
				.getInstance().getWizardPage(getLibraryType());
		if (wizardPage != null) {
			wizardPage.onEnterPage(getLibraryName());
		}
		return wizardPage;
	}

	/**
	 * @see org.eclipse.jface.wizard.WizardPage#isCompleted()
	 */
	public boolean isPageComplete() {
		return getLibraryName().length() > 0;
	}

	/**
	 * Adds listeners to the wizard controls.
	 */
	private void addListeners() {
		nameText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				setPageComplete(isPageComplete());
				getWizard().getContainer().updateButtons();
			}
		});
	}

	/**
	 * Returns the library name.
	 */
	public String getLibraryName() {
		return nameText.getText().trim();
	}

	/**
	 * Returns the library type selected by the user.
	 */
	public String getLibraryType() {
		for (int i = 0; i < radioButtons.length; i++) {
			Button radioButton = radioButtons[i];
			if (radioButton.getSelection()) {
				return (String) radioButton.getData(TYPE_ID);
			}
		}
		return (String) radioButtons[0].getData(TYPE_ID);
	}

}
