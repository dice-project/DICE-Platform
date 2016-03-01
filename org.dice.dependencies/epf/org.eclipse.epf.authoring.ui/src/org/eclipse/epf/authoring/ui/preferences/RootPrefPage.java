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
package org.eclipse.epf.authoring.ui.preferences;

import org.eclipse.epf.common.preferences.IPreferenceStoreWrapper;
import org.eclipse.epf.common.ui.CommonUIPlugin;
import org.eclipse.epf.common.ui.PreferenceStoreWrapper;
import org.eclipse.epf.library.LibraryPlugin;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;


/**
 * Root preference page
 * 
 * @author Bingxue Xu
 * @since 1.0
 */
public class RootPrefPage extends PreferencePage implements
		IWorkbenchPreferencePage {

	private Composite composite_tab;

	// private IWorkbench workbench;

	// Disableing enable process contribution preference for now.
	// private Button enableProcessContribution;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse.swt.widgets.Composite)
	 */
	protected Control createContents(Composite parent) {

		// composite_tab << parent
		composite_tab = createComposite(parent, 3);

		// enableProcessContribution = new Button(composite_tab, SWT.CHECK);
		// enableProcessContribution.setText(AuthoringUIResources.getString("AuthoringUI.RootPrefPage.enableprocesscontribution.text"));
		// //$NON-NLS-1$

		initializeValues();

		// font = null;
		return composite_tab;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
		// this.workbench = workbench;
	}

	/*
	 * (non-Javadoc) Method declared on PreferencePage
	 */
	protected void performDefaults() {
		super.performDefaults();
		initializeDefaults();
	}

	/*
	 * (non-Javadoc) Method declared on PreferencePage
	 */
	public boolean performOk() {
		storeValues();
		LibraryPlugin.getDefault().savePluginPreferences();
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#doGetPreferenceStore()
	 */
	protected IPreferenceStore doGetPreferenceStore() {
		IPreferenceStoreWrapper wrapper = LibraryPlugin.getDefault().getPreferenceStore();
		if ( wrapper instanceof PreferenceStoreWrapper ) {
			return ((PreferenceStoreWrapper)wrapper).getStore();
		}
		return null;
	}

	/**
	 * Stores the values of the controls back to the preference store.
	 */
	private void storeValues() {
	}

	/**
	 * Initializes states of the controls using default values in the preference
	 * store.
	 */
	private void initializeDefaults() {
	}

	/**
	 * Initializes states of the controls from the preference store.
	 */
	private void initializeValues() {
	}

	private Composite createComposite(Composite parent, int numColumns) {

		Composite composite = new Composite(parent, SWT.NULL);

		// GridLayout
		GridLayout layout = new GridLayout();
		layout.numColumns = numColumns;
		layout.marginHeight = layout.marginWidth = 0;
		composite.setLayout(layout);

		// GridData
		GridData data = new GridData();
		data.verticalAlignment = GridData.FILL;
		data.horizontalAlignment = GridData.FILL;
		composite.setLayoutData(data);

		return composite;
	}

	// private Label createLabel(Composite parent, String text, int span) {
	// Label label = new Label(parent, SWT.LEFT);
	// label.setText(text);
	// GridData data = new GridData();
	// data.horizontalSpan = span;
	// data.horizontalAlignment = GridData.FILL;
	// label.setLayoutData(data);
	// return label;
	// }

}
