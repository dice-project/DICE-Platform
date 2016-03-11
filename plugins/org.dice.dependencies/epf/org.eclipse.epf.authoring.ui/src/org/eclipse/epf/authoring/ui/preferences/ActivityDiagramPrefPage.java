//------------------------------------------------------------------------------
// Copyright (c) 2005, 2009 IBM Corporation and others.
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
 * Activity diagram authoring preference page
 * 
 * @author Shilpa Toraskar
 * @since 1.5.0.4
 */
public class ActivityDiagramPrefPage extends PreferencePage implements
		IWorkbenchPreferencePage {

	public ActivityDiagramPrefPage() {
		super();
		noDefaultAndApplyButton();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse.swt.widgets.Composite)
	 */
	protected Control createContents(Composite parent) {
		Composite composite = new Composite(parent, SWT.NULL);
		composite.setLayout(new GridLayout());
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		initializeValues();
		return composite;
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

	
}
