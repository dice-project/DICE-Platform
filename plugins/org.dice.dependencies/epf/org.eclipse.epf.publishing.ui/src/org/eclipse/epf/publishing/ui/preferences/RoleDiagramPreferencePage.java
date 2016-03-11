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

import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.library.preferences.LibraryPreferences;
import org.eclipse.epf.publishing.ui.PublishingUIResources;
import org.eclipse.epf.ui.preferences.BasePreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
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
public class RoleDiagramPreferencePage extends BasePreferencePage implements
		ModifyListener {

	private static int MIN_HORIZONTAL_SPACING = 50;

	private static int MAX_HORIZONTAL_SPACING = 200;

	private static int MIN_VERTICAL_SPACING = 25;

	private static int MAX_VERTICAL_SPACING = 100;

	private static int MIN_TEXT_LINES = 1;

	private static int MAX_TEXT_LINES = 5;

	private Composite composite;

	private Text horizonalSpacingText;

	private Text verticalSpacingText;

	private Text textLinesText;

	/**
	 * Creates and returns the SWT control for the customized body of this
	 * preference page under the given parent composite.
	 * 
	 * @param parent
	 *            the parent composite
	 * @return the new control
	 */
	protected Control createContents(Composite parent) {
		composite = new Composite(parent, SWT.NULL);
		composite.setLayout(new GridLayout(1, false));

		// Create Spacing between task and work product elements group.
		Group elementSpacingGroup = createGridLayoutGroup(composite,
				PublishingUIResources.elementSpacingGroup_text, 2);

		createLabel(elementSpacingGroup,
				PublishingUIResources.tasksHorizonalSpacingLabel_text);
		horizonalSpacingText = createEditableText(elementSpacingGroup, "", 25); //$NON-NLS-1$

		createLabel(elementSpacingGroup,
				PublishingUIResources.tasksVerticalSpacingLabel_text);
		verticalSpacingText = createEditableText(elementSpacingGroup, "", 25); //$NON-NLS-1$

		// Create Element text label group.
		Group elementTextLabelGroup = createGridLayoutGroup(composite,
				PublishingUIResources.elementTextLabelGroup_text, 2);

		createLabel(elementTextLabelGroup,
				PublishingUIResources.maxLineOfTextLabel_text);
		textLinesText = createEditableText(elementTextLabelGroup, "", 15); //$NON-NLS-1$

		initControls();

		addListeners();

		return composite;
	}

	/**
	 * Initializes the preference page controls with data.
	 */
	protected void initControls() {
		horizonalSpacingText.setText(String.valueOf(LibraryPreferences
				.getRoleDiagramHorizontalSpacing()));
		verticalSpacingText.setText(String.valueOf(LibraryPreferences
				.getRoleDiagramVerticalSpacing()));
		textLinesText.setText(String.valueOf(LibraryPreferences
				.getRoleDiagramMaximumTextLines()));
	}

	/**
	 * Adds event listeners to the preference page controls.
	 */
	protected void addListeners() {
		horizonalSpacingText.addModifyListener(this);
		verticalSpacingText.addModifyListener(this);
		textLinesText.addModifyListener(this);
	}

	/**
	 * @see org.eclipse.swt.events.ModifyListener#modifyText(ModifyEvent)
	 */
	public void modifyText(ModifyEvent e) {
		setErrorMessage(null);
		setValid(true);

		int value = 0;
		if (e.widget == horizonalSpacingText) {
			value = getHorizontalSpacing();
			if (value < MIN_HORIZONTAL_SPACING
					|| value > MAX_HORIZONTAL_SPACING) {
				setErrorMessage(PublishingUIResources.bind(
						PublishingUIResources.invalidHorizonalSpacingError_msg,
						new Object[] { new Integer(MIN_HORIZONTAL_SPACING),
								new Integer(MAX_HORIZONTAL_SPACING) }));
				setValid(false);
			}
		} else if (e.widget == verticalSpacingText) {
			value = getVerticalSpacing();
			if (value < MIN_VERTICAL_SPACING || value > MAX_VERTICAL_SPACING) {
				setErrorMessage(PublishingUIResources.bind(
						PublishingUIResources.invalidVerticalSpacingError_msg,
						new Object[] { new Integer(MIN_VERTICAL_SPACING),
								new Integer(MAX_VERTICAL_SPACING) }));
				setValid(false);
			}
		} else if (e.widget == textLinesText) {
			value = getMaximumTextLines();
			if (value < MIN_TEXT_LINES || value > MAX_TEXT_LINES) {
				setErrorMessage(PublishingUIResources.bind(
						PublishingUIResources.invalidTextLinesError_msg,
						new Object[] { new Integer(MIN_TEXT_LINES),
								new Integer(MAX_TEXT_LINES) }));
				setValid(false);
			}
		}

		updateApplyButton();
	}

	/**
	 * Performs special processing when this page's Defaults button has been
	 * pressed.
	 */
	protected void performDefaults() {
		super.performDefaults();
		horizonalSpacingText.setText("" //$NON-NLS-1$
				+ LibraryPreferences.getDefaultRoleDiagramHorizontalSpacing());
		verticalSpacingText.setText("" //$NON-NLS-1$
				+ LibraryPreferences.getDefaultRoleDiagramVerticalSpacing());
		textLinesText.setText("" //$NON-NLS-1$
				+ LibraryPreferences.getDefaultRoleDiagramMaximumTextLines());
	}

	/**
	 * @see org.eclipse.jface.preference.PreferencePage#performOk()
	 */
	public boolean performOk() {
		LibraryPreferences
				.setRoleDiagramHorizontalSpacing(getHorizontalSpacing());
		LibraryPreferences.setRoleDiagramVerticalSpacing(getVerticalSpacing());
		LibraryPreferences
				.setRoleDiagramMaximumTextLines(getMaximumTextLines());
		return true;
	}

	/**
	 * Gets the user specified horizontal spacing between tasks and roles.
	 */
	protected int getHorizontalSpacing() {
		return StrUtil.getIntValue(horizonalSpacingText.getText().trim(), 0);
	}

	/**
	 * Gets the user specified vertical spacing between tasks and roles.
	 */
	protected int getVerticalSpacing() {
		return StrUtil.getIntValue(verticalSpacingText.getText().trim(), 0);
	}

	/**
	 * Gets the user specified element label text lines.
	 */
	protected int getMaximumTextLines() {
		return StrUtil.getIntValue(textLinesText.getText().trim(), 0);
	}

}
