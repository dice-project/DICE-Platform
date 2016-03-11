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
package org.eclipse.epf.authoring.ui.preferences;

import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.common.preferences.IPreferenceStoreWrapper;
import org.eclipse.epf.common.ui.PreferenceStoreWrapper;
import org.eclipse.epf.library.LibraryPlugin;
import org.eclipse.epf.library.ui.LibraryUIPlugin;
import org.eclipse.epf.library.ui.preferences.LibraryUIPreferences;
import org.eclipse.epf.ui.preferences.BasePreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * Preference page for breakdown element attributes
 * 
 * @author Shilpa Toraskar
 * @since 1.2
 * 
 */
public class BreakdownElementPrefPage extends BasePreferencePage implements
		IWorkbenchPreferencePage, SelectionListener {

	Composite composite;

	private Button workBreakdownElementOptionalButton,
			workBreakdownElementMultipleOccurrancesButton,
			workBreakdownElementPlannedButton,
			workBreakdownElementEventDrivenButton,
			workBreakdownElementOngoingButton,
			workBreakdownElementRepetableButton;

	private Button descriptorOptionalButton,
			descriptorMultipleOccurrancesButton, descriptorPlannedButton,
			descriptorEventDrivenButton, descriptorOngoingButton,
			descriptorRepetableButton;

	public BreakdownElementPrefPage() {
		super();
	}

	protected Control createContents(Composite parent) {

		composite = new Composite(parent, SWT.NULL);
		composite.setLayout(new GridLayout(1, false));

		createLabel(composite,
				AuthoringUIResources.preference_breakDownElement_Description);

		createLabel(composite, ""); //$NON-NLS-1$

		// create group for activity elements
		Group workBreakdownElementGroup = createGridLayoutGroup(composite,
				AuthoringUIResources.preference_WorkBreakdownElements, 1, true);

		workBreakdownElementOptionalButton = createCheckbox(
				workBreakdownElementGroup,
				AuthoringUIResources.ProcessEditor_Optional);
		workBreakdownElementMultipleOccurrancesButton = createCheckbox(
				workBreakdownElementGroup,
				AuthoringUIResources.ProcessEditor_MultipleOccurrences);
		workBreakdownElementPlannedButton = createCheckbox(
				workBreakdownElementGroup,
				AuthoringUIResources.ProcessEditor_Planned);
		workBreakdownElementEventDrivenButton = createCheckbox(
				workBreakdownElementGroup,
				AuthoringUIResources.ProcessEditor_EventDriven);
		workBreakdownElementOngoingButton = createCheckbox(
				workBreakdownElementGroup,
				AuthoringUIResources.ProcessEditor_Ongoing);
		workBreakdownElementRepetableButton = createCheckbox(
				workBreakdownElementGroup,
				AuthoringUIResources.ProcessEditor_Repetable);

		// create group for descriptor elements
		Group descriptorGroup = createGridLayoutGroup(composite,
				AuthoringUIResources.preference_DescriptorElements, 1, true);
		descriptorOptionalButton = createCheckbox(descriptorGroup,
				AuthoringUIResources.ProcessEditor_Optional);
		descriptorMultipleOccurrancesButton = createCheckbox(descriptorGroup,
				AuthoringUIResources.ProcessEditor_MultipleOccurrences);
		descriptorPlannedButton = createCheckbox(descriptorGroup,
				AuthoringUIResources.ProcessEditor_Planned);
		descriptorEventDrivenButton = createCheckbox(descriptorGroup,
				AuthoringUIResources.ProcessEditor_EventDriven);
		descriptorOngoingButton = createCheckbox(descriptorGroup,
				AuthoringUIResources.ProcessEditor_Ongoing);
		descriptorRepetableButton = createCheckbox(descriptorGroup,
				AuthoringUIResources.ProcessEditor_Repetable);

		// Initialize values
		initializeValues();

		return composite;
	}

	public void init(IWorkbench workbench) {

	}

	public void widgetSelected(SelectionEvent e) {

	}

	public void widgetDefaultSelected(SelectionEvent e) {
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
		LibraryUIPlugin.getDefault().savePluginPreferences();
		LibraryUIPreferences.applyDefaultValuesForBreakdownElementAttributes();
		return true;
	}

	/**
	 * Stores the values of the controls back to the preference store.
	 */
	private void storeValues() {
		//
		LibraryUIPreferences
				.setWorkBreakdownElementOptional(workBreakdownElementOptionalButton
						.getSelection());
		LibraryUIPreferences
				.setWorkBreakdownElementMultipleOccurrances(workBreakdownElementMultipleOccurrancesButton
						.getSelection());
		LibraryUIPreferences
				.setWorkBreakdownElementPlanned(workBreakdownElementPlannedButton
						.getSelection());
		LibraryUIPreferences
				.setWorkBreakdownElementEventDriven(workBreakdownElementEventDrivenButton
						.getSelection());
		LibraryUIPreferences
				.setWorkBreakdownElementOngoing(workBreakdownElementOngoingButton
						.getSelection());
		LibraryUIPreferences
				.setWorkBreakdownElementRepeatable(workBreakdownElementRepetableButton
						.getSelection());

		LibraryUIPreferences.setDescriptorOptional(descriptorOptionalButton
				.getSelection());
		LibraryUIPreferences
				.setDescriptorMultipleOccurrances(descriptorMultipleOccurrancesButton
						.getSelection());
		LibraryUIPreferences.setDescriptorPlanned(descriptorPlannedButton
				.getSelection());
		LibraryUIPreferences
				.setDescriptorEventDriven(descriptorEventDrivenButton
						.getSelection());
		LibraryUIPreferences.setDescriptorOngoing(descriptorOngoingButton
				.getSelection());
		LibraryUIPreferences.setDescriptorRepeatable(descriptorRepetableButton
				.getSelection());

	}

	/**
	 * Initializes default states of the controls from the preference store.
	 */
	private void initializeDefaults() {
		
		workBreakdownElementOptionalButton.setSelection(false);
		workBreakdownElementMultipleOccurrancesButton.setSelection(false);
		workBreakdownElementPlannedButton.setSelection(true);
		workBreakdownElementEventDrivenButton.setSelection(false);
		workBreakdownElementOngoingButton.setSelection(false);
		workBreakdownElementRepetableButton.setSelection(false);

		descriptorOptionalButton.setSelection(false);
		descriptorMultipleOccurrancesButton.setSelection(false);
		descriptorPlannedButton.setSelection(false);
		descriptorEventDrivenButton.setSelection(false);
		descriptorOngoingButton.setSelection(false);
		descriptorRepetableButton.setSelection(false);
	}

	/**
	 * Initializes states of the controls from the preference store.
	 */
	private void initializeValues() {
	
		workBreakdownElementOptionalButton.setSelection(LibraryUIPreferences
				.getWorkBreakDownElementOptional());
		workBreakdownElementMultipleOccurrancesButton
				.setSelection(LibraryUIPreferences
						.getWorkBreakDownElementMultipleOccurrances());
		workBreakdownElementPlannedButton.setSelection(LibraryUIPreferences
				.getWorkBreakDownElementPlanned());
		workBreakdownElementEventDrivenButton.setSelection(LibraryUIPreferences
				.getWorkBreakDownElementEventDriven());
		workBreakdownElementOngoingButton.setSelection(LibraryUIPreferences
				.getWorkBreakDownElementOngoing());
		workBreakdownElementRepetableButton.setSelection(LibraryUIPreferences
				.getWorkBreakDownElementRepeatable());

		descriptorOptionalButton.setSelection(LibraryUIPreferences
				.getDescriptorOptional());
		descriptorMultipleOccurrancesButton.setSelection(LibraryUIPreferences
				.getDescriptorMultipleOccurrances());
		descriptorPlannedButton.setSelection(LibraryUIPreferences
				.getDescriptorPlanned());
		descriptorEventDrivenButton.setSelection(LibraryUIPreferences
				.getDescriptorEventDriven());
		descriptorOngoingButton.setSelection(LibraryUIPreferences
				.getDescriptorOngoing());
		descriptorRepetableButton.setSelection(LibraryUIPreferences
				.getDescriptorRepeatable());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.authoring.ui.preferences.CommonPrefPage#doGetPreferenceStore()
	 */
	protected IPreferenceStore doGetPreferenceStore() {
		IPreferenceStoreWrapper wrapper = LibraryPlugin.getDefault().getPreferenceStore();
		if ( wrapper instanceof PreferenceStoreWrapper ) {
			return ((PreferenceStoreWrapper)wrapper).getStore();
		}
		return null;
	}

}
