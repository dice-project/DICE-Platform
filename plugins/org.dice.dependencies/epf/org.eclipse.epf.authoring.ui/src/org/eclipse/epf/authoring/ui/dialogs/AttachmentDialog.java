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
package org.eclipse.epf.authoring.ui.dialogs;

import java.io.File;

import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.AuthoringUIText;
import org.eclipse.jface.dialogs.Dialog;
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
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * Opens dialog to attach attachments
 * 
 * @author Shashidhar Kannoori
 * @since 1.0
 */
public class AttachmentDialog extends Dialog {

	/**
	 * The OK button.
	 */
	protected Button okButton;

	/**
	 * The Cancel button.
	 */
	protected Button cancelButton;

	/**
	 * @param parent
	 */

	private Text attachment;

	/**
	 * Creates an instance
	 * @param parent
	 */
	public AttachmentDialog(Shell parent) {
		super(parent);
	}

	/**
	 * @see Dialog#createDialogArea(Composite parent)
	 */
	protected Control createDialogArea(Composite parent) {

		Composite dialogArea = (Composite) super.createDialogArea(parent);
		GridLayout layout = (GridLayout) dialogArea.getLayout();
		layout.marginWidth = 10;
		layout.marginHeight = 10;
		layout.numColumns = 2;
		GridData gridData = (GridData) dialogArea.getLayoutData();
		gridData.verticalIndent = 10;
		layout.numColumns = 3;

		Label urlLabel = new Label(dialogArea, SWT.NONE);
		urlLabel.setText(AuthoringUIResources.AttachmentDialogFileURL_text); 
		attachment = new Text(dialogArea, SWT.BORDER);
		gridData.widthHint = 300;
		attachment.setLayoutData(gridData);
		attachment.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				if (okButton != null) {
					okButton
							.setEnabled(attachment.getText().trim().length() > 0);
				}
			}
		});

		Button browseButton = new Button(dialogArea, SWT.NONE);
		browseButton.setText(AuthoringUIText.BROWSE_BUTTON_TEXT);
		browseButton.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent event) {
				FileDialog dialog = new FileDialog(Display.getCurrent()
						.getActiveShell(), SWT.OPEN);
				String imageFile = dialog.open();
				if (imageFile != null && imageFile.length() > 0) {
					File file = new File(imageFile);
					try {
						String url = file.toURL().toExternalForm();
						attachment.setText(url);
					} catch (Exception e) {
					}
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		super
				.getShell()
				.setText(
						AuthoringUIResources.AttachmentDialogattachFile_text); 

		return dialogArea;

	}

	/**
	 * Creates the dialog buttons.
	 */
	protected void createButtonsForButtonBar(Composite parent) {
		// Create the OK button.
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);

		// Create the Cancel button.
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);

		// Set help context for the OK button.
		okButton = super.getButton(IDialogConstants.OK_ID);

		// Set help context for the Cancel button.
		cancelButton = super.getButton(IDialogConstants.CANCEL_ID);

		okButton.setEnabled(false);
	}

	/**
	 * Called when the OK button is selected.
	 */
	protected void okPressed() {
		// String url = attachment.getText();
		// if (url != null && url.length() > 0) {
		//			
		// }
		super.okPressed();
	}

	/**
	 * Returns the File Name
	 */
	public String getAttachmentPath() {
		return attachment.getText();
	}
}
