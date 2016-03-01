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
package org.eclipse.epf.library.ui.internal.wizards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.epf.library.LibraryManagerFactory;
import org.eclipse.epf.library.ui.LibraryUIPlugin;
import org.eclipse.epf.library.ui.LibraryUIResources;
import org.eclipse.epf.library.ui.preferences.LibraryUIPreferences;
import org.eclipse.epf.library.ui.preferences.RecentlyOpenedLibrary;
import org.eclipse.epf.library.ui.wizards.OpenLibraryWizardPage;
import org.eclipse.epf.ui.wizards.BaseWizardPage;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
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
public class OpenLibraryMainPage2 extends BaseWizardPage {

	/**
	 * The wizard page name.
	 */
	public static final String PAGE_NAME = OpenLibraryMainPage2.class.getName();

	protected static final String TYPE_ID = "typeId"; //$NON-NLS-1$	

	protected Combo nameCombo;

	protected Text uriText;

	protected Button openUnlistedLibraryCheckbox;

	protected boolean openUnlistedLibrary = false;

	protected Button[] radioButtons;

	protected Map<String, RecentlyOpenedLibrary> recentlyOpenedLibraries = new HashMap<String, RecentlyOpenedLibrary>();

	/**
	 * Creates a new instance.
	 */
	public OpenLibraryMainPage2() {
		super(PAGE_NAME);
		setTitle(LibraryUIResources.openLibraryWizard_title);
		setDescription(LibraryUIResources.openLibraryMainWizardPage_title_2);
		setImageDescriptor(LibraryUIPlugin.getDefault().getImageDescriptor(
				"full/wizban/Open.gif")); //$NON-NLS-1$
	}

	/**
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(Composite)
	 */
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));

		Label nameLabel = new Label(composite, SWT.NORMAL);
		nameLabel.setText(LibraryUIResources.nameLabel_text);

		nameCombo = new Combo(composite, SWT.BORDER | SWT.READ_ONLY);
		nameCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		// Set the configuration list.
		List libraryList = getRecentlyOpenedLibraries();
		for (Iterator it = libraryList.iterator(); it.hasNext();) {
			RecentlyOpenedLibrary library = (RecentlyOpenedLibrary) it.next();
			recentlyOpenedLibraries.put(library.getName(), library);
		}
		String[] libraryNames = new String[recentlyOpenedLibraries.size()];
		recentlyOpenedLibraries.keySet().toArray(libraryNames);
		nameCombo.setItems(libraryNames);

		Label uriLabel = new Label(composite, SWT.NORMAL);
		uriLabel.setText(LibraryUIResources.uriLabel_text);

		uriText = new Text(composite, SWT.BORDER);
		uriText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		uriText.setEditable(false);

		openUnlistedLibraryCheckbox = new Button(composite, SWT.CHECK);
		openUnlistedLibraryCheckbox
				.setText(LibraryUIResources.openUnlistedLibraryCheckbox_text);
		openUnlistedLibraryCheckbox.setLayoutData(new GridData(
				GridData.FILL_HORIZONTAL));
		((GridData) openUnlistedLibraryCheckbox.getLayoutData()).horizontalSpan = 2;

		Label libraryTypeLabel = new Label(composite, SWT.NORMAL);
		libraryTypeLabel.setText(LibraryUIResources.libraryTypeLabel_text);

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
		OpenLibraryWizardPage wizardPage = OpenLibraryWizardPageFactory
				.getInstance().getWizardPage(getLibraryType());
		if (wizardPage != null) {
			wizardPage.onEnterPage(getLibraryName());
		}
		if (!openUnlistedLibrary) {
			return null;
		}
		return wizardPage;
	}

	/**
	 * @see org.eclipse.jface.wizard.WizardPage#isCompleted()
	 */
	public boolean isPageComplete() {
		return getLibraryName().length() > 0 || openUnlistedLibrary;
	}

	/**
	 * Adds listeners to the wizard controls.
	 */
	private void addListeners() {
		nameCombo.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent event) {
				getWizard().getContainer().updateButtons();
			}

			public void widgetDefaultSelected(SelectionEvent event) {
			}
		});

		openUnlistedLibraryCheckbox
				.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent event) {
						openUnlistedLibrary = openUnlistedLibraryCheckbox
								.getSelection();
						getWizard().getContainer().updateButtons();
					}
				});
	}

	/**
	 * Gets the user selected method library name.
	 */
	public String getLibraryName() {
		return nameCombo.getText().trim();
	}

	/**
	 * Gets the URI of the user selected method library.
	 */
	public String getLibraryURI() {
		RecentlyOpenedLibrary library = (RecentlyOpenedLibrary) recentlyOpenedLibraries
				.get(getLibraryName());
		return library.getURI().toString();
	}

	/**
	 * Gets the path of the user selected method library.
	 */
	public String getLibraryPath() {
		RecentlyOpenedLibrary library = (RecentlyOpenedLibrary) recentlyOpenedLibraries
				.get(getLibraryName());
		return library.getPath();
	}

	/**
	 * Gets the open unlisted method librray option.
	 */
	public boolean isOpenUnlistedLibrary() {
		return openUnlistedLibrary;
	}

	/**
	 * Returns the user selected method library type.
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

	/**
	 * Gets a list of recently opened method libraries.
	 * 
	 * @return a collection of <code>RecentlyOpenedLibrary</code> objects
	 */
	protected static List getRecentlyOpenedLibraries() {
		List libraryURIs = LibraryUIPreferences.getOpenLibraryURIsList();
		List<RecentlyOpenedLibrary> libraries = new ArrayList<RecentlyOpenedLibrary>();
		if (libraryURIs.size() > 0) {
			for (Iterator it = libraryURIs.iterator(); it.hasNext();) {
				String libraryURI = (String) it.next();
				libraries.add(new RecentlyOpenedLibrary(libraryURI));
			}
		}
		return libraries;
	}

}
