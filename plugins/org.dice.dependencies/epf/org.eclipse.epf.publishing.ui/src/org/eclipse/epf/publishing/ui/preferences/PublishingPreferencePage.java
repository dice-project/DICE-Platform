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
package org.eclipse.epf.publishing.ui.preferences;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.epf.authoring.ui.AuthoringUIText;
import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.publishing.ui.PublishingUIPlugin;
import org.eclipse.epf.publishing.ui.PublishingUIResources;
import org.eclipse.epf.ui.preferences.BasePreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;

/**
 * The Publishing preference page.
 * 
 * @author Kelvin Low
 * @since 1.0
 * 
 * Bugs fixed:
 * <ul>
 * <li>https://bugs.eclipse.org/bugs/show_bug.cgi?id=156957</li>
 * </ul>
 */
public class PublishingPreferencePage extends BasePreferencePage {

	private Composite composite;

	private Text destinationPathText;

	private Text feedbackURLText;

	private Button browseButton;

	private Button extraDescriptorInfoCheckbox;
	
	private Button showLinkedPageForDescriptorCheckbox;
	
	private Button ignoreDynamicParentsCheckbox;
	
	private Button excludeUnusedWPDsCheckbox;
	
	private Button fulfillDescriptorSlotByContentCheckbox;
	
	private Text forbiddenURLChars;

	/**
	 * Creates and returns the SWT control for the customized body of this
	 * preference page under the given parent composite.
	 * 
	 * @param parent
	 *            the parent composite
	 * @return the new control
	 */
	protected Control createContents(Composite parent) {
		composite = createGridLayoutComposite(parent, 1);

		// Create the Destination path group.
		/*
		 * Group destinationGroup = createGridLayoutGroup(composite,
		 * PublishingUIResources.destinationDirectoryGroup_text, 3);
		 */

		// Create the Published Website group.
		Group webSiteGroup = createGridLayoutGroup(composite,
				PublishingUIResources.publishWebSiteGroup_text, 3);
		createLabel(webSiteGroup,
				PublishingUIResources.preferencePage_defaultPath_text);
		destinationPathText = createEditableText(webSiteGroup);
		browseButton = createButton(webSiteGroup,
				AuthoringUIText.BROWSE_BUTTON_TEXT);
		createLabel(webSiteGroup, PublishingUIResources.feedbackURLLabel_text);
		feedbackURLText = createEditableText(webSiteGroup, 2);

		// Create the Browsing group.
		Group browsingGroup = createGridLayoutGroup(composite, PublishingUIResources.browsingLayoutGroup_text, 1);
		extraDescriptorInfoCheckbox = createCheckbox(browsingGroup,
				PublishingUIResources.publishExtraDescriptorInfoLabel_text, 1);
		
		showLinkedPageForDescriptorCheckbox = createCheckbox(browsingGroup,
				PublishingUIResources.showLinkedPageForDescriptorLabel_text, 1);
				
		ignoreDynamicParentsCheckbox = createCheckbox(browsingGroup,
				PublishingUIResources.ignoreDynamicParentsCheckbox_text, 1);		
				
		excludeUnusedWPDsCheckbox = createCheckbox(browsingGroup,
				PublishingUIResources.excludeUnusedWPDsCheckbox_text, 1);			
		
		// Create the slot fulfillment rule group.
		Group wpSlotDpRuleGroup = createGridLayoutGroup(composite, PublishingUIResources.wpSlotDpRuleGroup_text, 1);
		fulfillDescriptorSlotByContentCheckbox = createCheckbox(wpSlotDpRuleGroup,
				PublishingUIResources.fulfillDescriptorSlotByContentCheckbox_text, 1);
		
		// Create the forbidden URL chars group.
		Group forbiddenURLCharsGroup = createGridLayoutGroup(composite, PublishingUIResources.forbiddenURLCharsGroup_text, 2);
		forbiddenURLChars = createEditableText(forbiddenURLCharsGroup, 1);
		createLabel(forbiddenURLCharsGroup, PublishingUIResources.forbiddenURLCharsText_label);
		
		initControls();

		addListeners();

		return composite;
	}

	/**
	 * Initializes the preference page controls with data.
	 */
	protected void initControls() {
		destinationPathText.setText(PublishingUIPreferences.getPublishPath());
		feedbackURLText.setText(PublishingUIPreferences.getFeedbackURL());
		extraDescriptorInfoCheckbox.setSelection(PublishingUIPreferences
				.getExtraDescriptorInfo());	
		showLinkedPageForDescriptorCheckbox.setSelection(PublishingUIPreferences
				.getShowLinkedElementForDescriptor());		
		ignoreDynamicParentsCheckbox.setSelection(PublishingUIPreferences
				.getIgnoreDynamicParents());
		excludeUnusedWPDsCheckbox.setSelection(PublishingUIPreferences
				.getExcludeUnusedWPDs());
		fulfillDescriptorSlotByContentCheckbox.setSelection(PublishingUIPreferences
				.getFulfillDescriptorSlotByContent());
		forbiddenURLChars.setText(PublishingUIPreferences.getForbiddenChars());
	}

	/**
	 * Adds event listeners to the preference page controls.
	 */
	protected void addListeners() {
		destinationPathText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				if (!isPathValid(destinationPathText.getText())) {
					setValid(false);
				} else {
					setValid(true);
				}
			}
		});

		browseButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				openDirectoryDialog();
			}
		});
	}

	/**
	 * @see org.eclipse.jface.preference.PreferencePage#performDefaults()
	 */
	protected void performDefaults() {
		super.performDefaults();
		initializeDefaults();
	}

	/**
	 * @see org.eclipse.jface.preference.PreferencePage#performOk()
	 */
	public boolean performOk() {
		storeValues();
		PublishingUIPreferences.saveAllPreferences();
		
		// update the settings for browsing
		PublishingUIPlugin.getDefault().updateLayoutSettings();

		return true;
	}

	/**
	 * Stores the values of the controls back to the preference store.
	 */
	private void storeValues() {
		PublishingUIPreferences.setPublishPath(destinationPathText.getText()
				.trim());
		PublishingUIPreferences
				.setFeedbackURL(feedbackURLText.getText().trim());
		PublishingUIPreferences
				.setExtraDescriptorInfo(extraDescriptorInfoCheckbox
						.getSelection());
		PublishingUIPreferences
				.setShowLinkedElementForDescriptor(showLinkedPageForDescriptorCheckbox
						.getSelection());

		PublishingUIPreferences
				.setIgnoreDynamicParents(ignoreDynamicParentsCheckbox
						.getSelection());
		
		PublishingUIPreferences.setExcludeUnusedWPDs(excludeUnusedWPDsCheckbox
				.getSelection());
		
		PublishingUIPreferences
			.setFulfillDescriptorSlotByContent(fulfillDescriptorSlotByContentCheckbox
				.getSelection());
		
		PublishingUIPreferences.setForbiddenChars(forbiddenURLChars.getText()
				.trim());
		
//		// FIXME! Review implementation.
//		// allow to pass the value to the library browsing
//		// System.setProperty(EXTRA_DESCRIPTOR_INFO,
//		// extraDescriptorInfoCtr.getSelection() ? "true" : "false");
//		DefaultContentValidator
//				.setDefaultShowExtraInfoForDescriptors(extraDescriptorInfoCheckbox
//						.getSelection());
	}

	private void initializeDefaults() {
		destinationPathText.setText(PublishingUIPreferences
				.getDefaultPublishPath());
		feedbackURLText
				.setText(PublishingUIPreferences.getDefaultFeedbackURL());
		extraDescriptorInfoCheckbox.setSelection(PublishingUIPreferences
				.getDefaultExtraDescriptorInfo());
		showLinkedPageForDescriptorCheckbox.setSelection(PublishingUIPreferences
				.getDefaultShowLinkedElementForDescriptor());		
		ignoreDynamicParentsCheckbox.setSelection(PublishingUIPreferences
				.getDefaultIgnoreDynamicParents());
		PublishingUIPreferences
			.setIgnoreDynamicParents(ignoreDynamicParentsCheckbox
				.getSelection());
		excludeUnusedWPDsCheckbox.setSelection(PublishingUIPreferences
				.getDefaultExcludeUnusedWPDs());
		PublishingUIPreferences
			.setExcludeUnusedWPDs(excludeUnusedWPDsCheckbox
				.getSelection());
		fulfillDescriptorSlotByContentCheckbox.setSelection(PublishingUIPreferences
				.getDefaultFulfillDescriptorSlotByContent());
		forbiddenURLChars
			.setText(PublishingUIPreferences.getForbiddenChars());
	}

	/**
	 * @see org.eclipse.jface.preference.PreferencePage#doGetPreferenceStore()
	 */
	protected IPreferenceStore doGetPreferenceStore() {
		return PublishingUIPlugin.getDefault().getPreferenceStore();
	}

	/**
	 * Validates the user specified destination path.
	 */
	private boolean isPathValid(String path) {
		if (path == null || path.length() <= 0) {
			setErrorMessage(PublishingUIResources.invalidPathError_msg); //$NON-NLS-1$
			return false;
		}

		IPath ecPath = Path.fromOSString(path);
		boolean isValid = ecPath.isValidPath(path);
		if (!isValid) {
			setErrorMessage(PublishingUIResources.invalidPathError_msg); //$NON-NLS-1$
			return false;
		} else if (!StrUtil.isValidPublishPath(path)) {
			setErrorMessage(PublishingUIResources.invalidPathCharsError_msg); //$NON-NLS-1$
			return false;
		}

		setErrorMessage(null);
		return true;
	}

	/**
	 * Opens the directory dialog.
	 */
	private void openDirectoryDialog() {
		try {
			DirectoryDialog dd = new DirectoryDialog(composite.getShell(),
					SWT.NONE);
			String destination = dd.open();
			if (destination != null) {
				destinationPathText.setText(destination);
			}
		} catch (Exception e) {
			PublishingUIPlugin.getDefault().getLogger().logError(e);
		}
	}

}
