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
package org.eclipse.epf.publishing.ui.preferences;

import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.preferences.AuthoringUIPreferences;
import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.diagram.model.util.DiagramModelPreference;
import org.eclipse.epf.library.ui.LibraryUIPlugin;
import org.eclipse.epf.library.ui.preferences.LibraryUIPreferences;
import org.eclipse.epf.publishing.ui.PublishingUIPlugin;
import org.eclipse.epf.ui.preferences.BasePreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * Preference page for diagram options
 * 
 * @author Phong Le
 * @author Shilpa Toraskar
 * @since 1.0
 * 
 */
public class ActivityDiagramPreferencePage extends BasePreferencePage implements
		IWorkbenchPreferencePage, SelectionListener, ModifyListener {
	Composite composite;

	int NUM_COLUMN = 3;

	private static int MIN_TASKS_PER_ROW = 1;

	private static int MAX_TASKS_PER_ROW = 20;

	private Button ctrl_publish_unopen_activitydd;

	private Button ctrl_publish_ad_for_activity_extension;

	private Text tasksPerRowText;
	
	private Button ctrl_use_state_on_workproduct;

	public ActivityDiagramPreferencePage() {
		super();
	}

	protected Control createContents(Composite parent) {

		composite = new Composite(parent, SWT.NULL);
		composite.setLayout(new GridLayout(1, false));

		// Create activity diagram group.
		Group activityDiagramGroup = createGridLayoutGroup(composite,
				AuthoringUIResources.preference_Activity_Diagram, 1);

		ctrl_publish_ad_for_activity_extension = createCheckbox(
				activityDiagramGroup,
				AuthoringUIResources.prompt_for_publish_extend_activity_diagram);

		// Create activity detatil diagram group.

		Group activityDetailDiagramGroup = createGridLayoutGroup(composite,
				AuthoringUIResources.preference_Activity_Detail_Diagram, 2);

		ctrl_publish_unopen_activitydd = createCheckbox(
				activityDetailDiagramGroup,
				AuthoringUIResources.promptfor_publish_unopen_activitydd_text,
				2);

		createLabel(activityDetailDiagramGroup,
				AuthoringUIResources.add_TasksperRow);
		tasksPerRowText = createEditableText(activityDetailDiagramGroup, ""); //$NON-NLS-1$
		
		ctrl_use_state_on_workproduct = createCheckbox(
				activityDetailDiagramGroup,
				AuthoringUIResources.use_state_on_workproduct_text,
				2);

		initializeValues();

		addListeners();

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

		// update the settings for browsing
		PublishingUIPlugin.getDefault().updateLayoutSettings();

		AuthoringUIPlugin.getDefault().savePluginPreferences();

		return true;
	}

	/**
	 * Stores the values of the controls back to the preference store.
	 */
	private void storeValues() {
		// set ADD tasks per row
		AuthoringUIPreferences.setADDTasksPerRow(getTasksPerRow());

		LibraryUIPreferences
				.setPublishUnopenActivitydd(ctrl_publish_unopen_activitydd
						.getSelection());
		LibraryUIPreferences
				.setPublishADForActivityExtension(ctrl_publish_ad_for_activity_extension
						.getSelection());
		
		DiagramModelPreference.setUseStateOnWorkproduct(ctrl_use_state_on_workproduct.getSelection());
	}

	private void initializeDefaults() {
		// IPreferenceStore store = getPreferenceStore();
		ctrl_publish_unopen_activitydd.setSelection(false);
		ctrl_publish_ad_for_activity_extension.setSelection(true);

		tasksPerRowText.setText(String.valueOf(AuthoringUIPreferences
				.getDefaultADDTasksPerRow()));
		
		ctrl_use_state_on_workproduct.setSelection(false);
	}

	/**
	 * Initializes states of the controls from the preference store.
	 */
	private void initializeValues() {
		ctrl_publish_unopen_activitydd.setSelection(LibraryUIPreferences
				.getPublishUnopenActivitydd());
		ctrl_publish_ad_for_activity_extension
				.setSelection(LibraryUIPreferences
						.getPublishADForActivityExtension());

		tasksPerRowText.setText(String.valueOf(AuthoringUIPreferences
				.getADD_TasksPerRow()));
		
		ctrl_use_state_on_workproduct.setSelection(DiagramModelPreference.getUseStateOnWorkproduct());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.authoring.ui.preferences.CommonPrefPage#doGetPreferenceStore()
	 */
	protected IPreferenceStore doGetPreferenceStore() {
		return LibraryUIPlugin.getDefault().getPreferenceStore();
	}

	/**
	 * Adds event listeners to the preference page controls.
	 */
	protected void addListeners() {
		tasksPerRowText.addModifyListener(this);
	}

	/**
	 * @see org.eclipse.swt.events.ModifyListener#modifyText(ModifyEvent)
	 */
	public void modifyText(ModifyEvent e) {
		setErrorMessage(null);
		setValid(true);

		int value = 0;
		if (e.widget == tasksPerRowText) {
			value = getTasksPerRow();
			if (value < MIN_TASKS_PER_ROW || value > MAX_TASKS_PER_ROW) {
				setErrorMessage(AuthoringUIResources.bind(
						AuthoringUIResources.invalidTaskperRow_msg,
						new Object[] { new Integer(MIN_TASKS_PER_ROW),
								new Integer(MAX_TASKS_PER_ROW) }));
				setValid(false);
			}
		}

		updateApplyButton();
	}

	/**
	 * Gets the user specified tasks per rown
	 */
	protected int getTasksPerRow() {
		return StrUtil.getIntValue(tasksPerRowText.getText().trim(), 0);
	}
}
