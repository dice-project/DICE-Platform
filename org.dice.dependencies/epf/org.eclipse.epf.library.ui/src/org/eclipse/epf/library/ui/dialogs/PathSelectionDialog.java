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
package org.eclipse.epf.library.ui.dialogs;

import org.eclipse.epf.library.ui.LibraryUIResources;
import org.eclipse.epf.library.ui.wizards.DirectoryValidator;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * 
 * Utility class to select a path
 * 
 * @author Jinhua Xi
 * 
 */
public class PathSelectionDialog extends Dialog {

	protected Text ctrl_path;

	protected Button ctrl_browse_button;

	protected String title;

	protected String message;

	protected String value;

	public PathSelectionDialog(Shell parentShell, String dialogTitle,
			String dialogMessage, String defaultPath) {
		super(parentShell);

		title = dialogTitle;
		message = dialogMessage;
		value = defaultPath;
	}

	/**
	 * @see org.eclipse.jface.dialogs.Dialog#buttonPressed(int)
	 */
	protected void buttonPressed(int buttonId) {
		if (buttonId == IDialogConstants.OK_ID) {
			value = ctrl_path.getText();

			// make sure the path is valid
			String msg = LibraryUIResources.pathSelectionError_msg;
			if (DirectoryValidator.checkAndCreateDir(value, title, msg) == false) {
				return;
			}
		} else {
			value = null;
		}
		super.buttonPressed(buttonId);
	}

	/**
	 * 
	 * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
	 */
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		if (title != null)
			shell.setText(title);
	}

	/**
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#createButtonsForButtonBar(org.eclipse.swt.widgets.Composite)
	 */
	protected void createButtonsForButtonBar(Composite parent) {
		// create OK and Cancel buttons by default
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);

		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);

		// do this here because setting the text will set enablement on the ok
		// button
		ctrl_path.setFocus();
		if (value != null) {
			ctrl_path.setText(value);
			ctrl_path.selectAll();
		}
	}

	/**
	 * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
	 */
	protected Control createDialogArea(Composite parent) {
		// create composite
		Composite composite = (Composite) super.createDialogArea(parent);
		// create message
		if (message != null) {
			Label label = new Label(composite, SWT.WRAP);
			label.setText(message);
			GridData data = new GridData(GridData.GRAB_HORIZONTAL
					| GridData.GRAB_VERTICAL | GridData.HORIZONTAL_ALIGN_FILL
					| GridData.VERTICAL_ALIGN_CENTER);
			data.widthHint = convertHorizontalDLUsToPixels(IDialogConstants.MINIMUM_MESSAGE_AREA_WIDTH);
			label.setLayoutData(data);
			label.setFont(parent.getFont());
		}
		ctrl_path = new Text(composite, SWT.SINGLE | SWT.BORDER);
		ctrl_path.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL
				| GridData.HORIZONTAL_ALIGN_FILL));

		ctrl_browse_button = new Button(composite, SWT.NONE);
		ctrl_browse_button.setLayoutData(new GridData(
				GridData.HORIZONTAL_ALIGN_END));
		ctrl_browse_button.setText(LibraryUIResources.browseButton_text);
		ctrl_browse_button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				openDirectoryDialog();
			}
		});

		applyDialogFont(composite);
		return composite;
	}

	/**
	 * Open directory dialog
	 *
	 */
	private void openDirectoryDialog() {
		try {
			// Text ctrl_path = super.getText();
			DirectoryDialog dd = new DirectoryDialog(this.getShell(), SWT.NONE);
			dd.setFilterPath(ctrl_path.getText());

			String destination = dd.open();
			if (destination != null) {
				ctrl_path.setText(destination);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Returns the path
	 * @return path
	 */
	public String getPath() {
		return value;
	}
}
