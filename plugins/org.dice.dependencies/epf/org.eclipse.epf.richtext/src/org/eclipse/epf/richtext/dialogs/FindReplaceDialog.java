/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
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
package org.eclipse.epf.richtext.dialogs;

import org.eclipse.epf.richtext.RichTextResources;
import org.eclipse.epf.richtext.actions.FindReplaceAction;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * Prompts the user to specify the search and replace strings and options.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class FindReplaceDialog extends BaseDialog {

	private FindReplaceAction findReplaceAction;

	private boolean findOnly;

	private Text findText;

	private Text replaceText;

	private Button searchForwardRadioButton;

	private Button searchBackwardRadioButton;

	private Button caseSensitiveCheckbox;

	private Button wholeWordCheckbox;

	private Button findButton;

	private Button replaceButton;

	private Button replaceFindButton;

	private Button replaceAllButton;

	private Label statusLabel;

	/**
	 * Creates a new instance.
	 * 
	 * @param parent
	 *            the parent shell
	 * @param findReplaceAction
	 *            the Find and Replace action
	 * @param findOnly
	 *            if <code>true</code>, disable the replace and replace all
	 *            functionalities
	 */
	public FindReplaceDialog(Shell parent, FindReplaceAction findReplaceAction,
			boolean findOnly) {
		super(parent);
		setShellStyle(SWT.DIALOG_TRIM | SWT.MODELESS | getDefaultOrientation());
		setBlockOnOpen(false);
		this.findReplaceAction = findReplaceAction;
		this.findOnly = findOnly;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);
		GridLayout layout = (GridLayout) composite.getLayout();
		layout.numColumns = 1;

		Composite textComposite = new Composite(composite, SWT.NONE);
		textComposite.setLayout(new GridLayout(2, false));
		textComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label findLabel = new Label(textComposite, SWT.NONE);
		findLabel.setText(RichTextResources.findLabel_text);
		findText = new Text(textComposite, SWT.BORDER);
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.widthHint = 200;
		findText.setLayoutData(gridData);
		findText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				if (findButton != null) {
					findButton
							.setEnabled(findText.getText().trim().length() > 0);
				}
				if (replaceAllButton != null && !findOnly) {
					replaceAllButton.setEnabled(findText.getText().trim()
							.length() > 0);
				}
			}
		});

		Label replaceLabel = new Label(textComposite, SWT.NONE);
		replaceLabel.setText(RichTextResources.replaceLabel_text);
		replaceText = new Text(textComposite, SWT.BORDER);
		replaceText.setLayoutData(gridData);
		if (findOnly) {
			replaceText.setEnabled(false);
		} else {
			replaceText.addModifyListener(new ModifyListener() {
				public void modifyText(ModifyEvent e) {
					if (replaceButton != null) {
						replaceButton.setEnabled(findReplaceAction
								.getFoundMatch());
					}
					if (replaceFindButton != null) {
						replaceFindButton.setEnabled(findReplaceAction
								.getFoundMatch());
					}
				}
			});
		}

		Composite optionsComposite = new Composite(composite, SWT.NONE);
		optionsComposite.setLayout(new GridLayout(2, true));
		optionsComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Group directionGroup = new Group(optionsComposite, SWT.NONE);
		directionGroup.setText(RichTextResources.directionGroup_text);
		directionGroup.setLayout(new GridLayout(1, false));
		directionGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		searchForwardRadioButton = new Button(directionGroup, SWT.RADIO);
		searchForwardRadioButton
				.setText(RichTextResources.forwardRadioButton_text);
		searchForwardRadioButton.setSelection(true);
		searchBackwardRadioButton = new Button(directionGroup, SWT.RADIO);
		searchBackwardRadioButton
				.setText(RichTextResources.backwardRadioButton_text);

		Group optionsGroup = new Group(optionsComposite, SWT.NONE);
		optionsGroup.setText(RichTextResources.optionsGroup_text);
		optionsGroup.setLayout(new GridLayout(1, false));
		optionsGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		caseSensitiveCheckbox = new Button(optionsGroup, SWT.CHECK);
		caseSensitiveCheckbox
				.setText(RichTextResources.caseSensitiveCheckbox_text);
		wholeWordCheckbox = new Button(optionsGroup, SWT.CHECK);
		wholeWordCheckbox.setText(RichTextResources.wholeWordCheckbox_text);

		statusLabel = new Label(composite, SWT.NONE);
		statusLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		super.getShell().setText(RichTextResources.findReplaceDialog_title);

		return composite;
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.CLIENT_ID + 1,
				RichTextResources.findButton_text, true);
		findButton = super.getButton(IDialogConstants.CLIENT_ID + 1);
		findButton.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent event) {
				findReplaceAction.run(FindReplaceAction.FIND_TEXT,
						getFindText(), getReplaceText(), getMatchDirection(),
						getMatchOptions());
				if (!findOnly) {
					replaceButton.setEnabled(findReplaceAction.getFoundMatch());
					replaceFindButton.setEnabled(findReplaceAction
							.getFoundMatch());
				}
				if (findReplaceAction.getFoundMatch()) {
					statusLabel.setText(""); //$NON-NLS-1$
				} else {
					statusLabel
							.setText(RichTextResources.FindReplace_Status_noMatch_label);
				}
				findButton.setFocus();
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		createButton(parent, IDialogConstants.CLIENT_ID + 2,
				RichTextResources.replaceButton_text, false);
		replaceButton = super.getButton(IDialogConstants.CLIENT_ID + 2);
		if (!findOnly) {
			replaceButton.addSelectionListener(new SelectionListener() {
				public void widgetSelected(SelectionEvent event) {
					findReplaceAction.run(FindReplaceAction.REPLACE_TEXT,
							getFindText(), getReplaceText(),
							getMatchDirection(), getMatchOptions());
					replaceButton.setFocus();
				}

				public void widgetDefaultSelected(SelectionEvent e) {
				}
			});
		}

		createButton(parent, IDialogConstants.CLIENT_ID + 3,
				RichTextResources.replaceFindButton_text, false);
		replaceFindButton = super.getButton(IDialogConstants.CLIENT_ID + 3);
		if (!findOnly) {
			replaceFindButton.addSelectionListener(new SelectionListener() {
				public void widgetSelected(SelectionEvent event) {
					findReplaceAction.run(FindReplaceAction.REPLACE_FIND_TEXT,
							getFindText(), getReplaceText(),
							getMatchDirection(), getMatchOptions());
					replaceButton.setEnabled(findReplaceAction.getFoundMatch());
					replaceFindButton.setEnabled(findReplaceAction
							.getFoundMatch());
					replaceFindButton.setFocus();
				}

				public void widgetDefaultSelected(SelectionEvent e) {
				}
			});
		}

		createButton(parent, IDialogConstants.CLIENT_ID + 4,
				RichTextResources.replaceallButton_text, false);
		replaceAllButton = super.getButton(IDialogConstants.CLIENT_ID + 4);
		if (!findOnly) {
			replaceAllButton.addSelectionListener(new SelectionListener() {
				public void widgetSelected(SelectionEvent event) {
					findReplaceAction.run(FindReplaceAction.REPLACE_ALL_TEXT,
							getFindText(), getReplaceText(),
							getMatchDirection(), getMatchOptions());
					replaceButton.setEnabled(false);
					replaceFindButton.setEnabled(false);
					replaceAllButton.setFocus();
				}

				public void widgetDefaultSelected(SelectionEvent e) {
				}
			});
		}

		// Create the Cancel button.
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
		cancelButton = super.getButton(IDialogConstants.CANCEL_ID);

		findButton.setEnabled(false);
		replaceButton.setEnabled(false);
		replaceFindButton.setEnabled(false);
		replaceAllButton.setEnabled(false);
	}

	/**
	 * Gets the user specified find text.
	 * 
	 * @return the find text
	 */
	public String getFindText() {
		return findText.getText();
	}

	/**
	 * Gets the user specified replace text.
	 * 
	 * @return the replace text
	 */
	public String getReplaceText() {
		return replaceText.getText();
	}

	/**
	 * Gets the text match direction.
	 * 
	 * @return <code>FIND_FORWARD</code> or <code>FIND_BACKWARD</code>
	 */
	public int getMatchDirection() {
		return searchForwardRadioButton.getSelection() ? FindReplaceAction.FORWARD_MATCH
				: FindReplaceAction.BACKWARD_MATCH;
	}

	/**
	 * Gets the text match options.
	 * 
	 * @return the text match options
	 */
	public int getMatchOptions() {
		int options = 0;
		if (wholeWordCheckbox.getSelection() == true) {
			options |= FindReplaceAction.WHOLE_WORD_MATCH;
		}
		if (caseSensitiveCheckbox.getSelection() == true) {
			options |= FindReplaceAction.CASE_SENSITIVE_MATCH;
		}
		return options;
	}

	/**
	 * Checks the find only option.
	 * 
	 * @return <code>true</code> if find only option is enabled
	 */
	public boolean isFindOnly() {
		return findOnly;
	}

	/**
	 * Sets the find only option.
	 * 
	 * @param findOnly,
	 *            if <code>true</code>, enable the find only option
	 */
	public void setFindOnly(boolean findOnly) {
		this.findOnly = findOnly;
	}

}