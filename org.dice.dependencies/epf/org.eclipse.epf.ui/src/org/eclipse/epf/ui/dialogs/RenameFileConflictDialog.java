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
package org.eclipse.epf.ui.dialogs;

import java.io.File;

import org.eclipse.epf.ui.EPFUIResources;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * Displays a dialog that prompts the user to rename a file path.
 * 
 * @author Shashidhar Kannoori
 * @since 1.2
 */
public class RenameFileConflictDialog extends Dialog {

	/**
	 * The OK button.
	 */
	protected Button okButton;

	/**
	 * The Cancel button.
	 */
	protected Button cancelButton;

	/**
	 * The overwrite button
	 */
	protected Button overWriteButton;

	private Text ctrl_fileName;
	private int OVER_WRITE_ID = 22;
	private String messageStr;
	private String fileName;
	private Button button_replace;
	private Button button_unique;

	private String destination;

	/**
	 * Creates an instance
	 * 
	 * @param parent
	 */
	public RenameFileConflictDialog(Shell parent) {
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
		GridData gridData = (GridData) dialogArea.getLayoutData();
		gridData.verticalIndent = 10;
		gridData.widthHint = 450;
		layout.numColumns = 3;

		// Create message area.

		Image image = Display.getCurrent().getSystemImage(SWT.ICON_WARNING);
		if (image != null) {
			Label imageLabel = new Label(dialogArea, SWT.NULL);
			image.setBackground(imageLabel.getBackground());
			imageLabel.setImage(image);
			imageLabel.setLayoutData(new GridData(
					GridData.HORIZONTAL_ALIGN_CENTER
							| GridData.VERTICAL_ALIGN_BEGINNING));
		}

		Label message = new Label(dialogArea, SWT.WRAP);
		if (messageStr != null) {
			message.setText(messageStr);
		}
		GridData data1 = new GridData(GridData.FILL_HORIZONTAL);
		data1.horizontalSpan = 2;
		message.setLayoutData(data1);

		Label empty = new Label(dialogArea, SWT.NONE);
		empty.setText(""); //$NON-NLS-1$
		Label note = new Label(dialogArea, SWT.NONE);
		note.setText(EPFUIResources.Dialog_fileNameConflict_note);
		GridData data2 = new GridData(GridData.FILL_HORIZONTAL);
		data2.horizontalSpan = 2;
		note.setLayoutData(data2);

		empty = new Label(dialogArea, SWT.NONE);
		empty.setText(""); //$NON-NLS-1$
		GridData data3 = new GridData(GridData.FILL_HORIZONTAL);
		data3.horizontalSpan = 3;
		data3.heightHint = 10;
		empty.setLayoutData(data3);

		empty = new Label(dialogArea, SWT.NONE);
		empty.setText(""); //$NON-NLS-1$
		Label selectAction = new Label(dialogArea, SWT.NONE);
		selectAction.setText(EPFUIResources.Dialog_fileName_selectAction_label);
		GridData data4 = new GridData(GridData.FILL_HORIZONTAL);
		data4.horizontalSpan = 2;
		selectAction.setLayoutData(data4);

		empty = new Label(dialogArea, SWT.NONE);
		empty.setText(""); //$NON-NLS-1$
		empty = new Label(dialogArea, SWT.NONE);
		empty.setText(""); //$NON-NLS-1$
		button_replace = new Button(dialogArea, SWT.RADIO);
		// Label replace_label = new Label(dialogArea, SWT.NONE);
		// replace_label.setText(EPFUIResources.Dialog_fileName_replace_msg);
		button_replace.setText(EPFUIResources.Dialog_fileName_replace_msg);
		button_replace.setSelection(true);

		empty = new Label(dialogArea, SWT.NONE);
		empty.setText(""); //$NON-NLS-1$
		empty = new Label(dialogArea, SWT.NONE);
		empty.setText(""); //$NON-NLS-1$
		button_unique = new Button(dialogArea, SWT.RADIO);
		// Label uniqueLabel = new Label(dialogArea, SWT.NONE);
		// uniqueLabel.setText(EPFUIResources.Dialog_fileName_unique_msg);
		button_unique.setText(EPFUIResources.Dialog_fileName_unique_msg);
		button_unique.setSelection(false);

		// Create label area. -- TODO: find different way instead of empty
		// labels.
		empty = new Label(dialogArea, SWT.NONE);
		empty.setText(""); //$NON-NLS-1$
		empty = new Label(dialogArea, SWT.NONE);
		empty.setText(""); //$NON-NLS-1$
		ctrl_fileName = new Text(dialogArea, SWT.BORDER);
		GridData gridData1 = new GridData(GridData.BEGINNING);
		gridData1.widthHint = 200;
		gridData1.verticalAlignment = GridData.VERTICAL_ALIGN_CENTER;
		ctrl_fileName.setLayoutData(gridData1);
		if (fileName != null) {
			ctrl_fileName.setText(fileName);
			ctrl_fileName.setEnabled(false);
		}
		ctrl_fileName.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				if (okButton != null) {
					okButton
							.setEnabled(ctrl_fileName.getText().trim().length() > 0);
				}
				fileName = ctrl_fileName.getText();
				File file = new File(destination + File.separator + fileName);
				if (file.exists()) {
					okButton.setEnabled(false);
				} else {
					okButton.setEnabled(true);
				}
			}
		});

		button_replace.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent event) {
				ctrl_fileName.setEnabled(false);
				okButton.setEnabled(true);
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		button_unique.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent event) {
				ctrl_fileName.setEnabled(true);
				fileName = ctrl_fileName.getText();
				File file = new File(destination + File.separator + fileName);
				if (file.exists()) {
					okButton.setEnabled(false);
				} else {
					okButton.setEnabled(true);
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		super.getShell().setText(EPFUIResources.Dialog_fileNameConflict_title); 

		return dialogArea;

	}

	/**
	 * Creates the dialog buttons.
	 */
	protected void createButtonsForButtonBar(Composite parent) {
		// Create the OK button.
		// createButton(parent, OVER_WRITE_ID,
		// EPFUIResources.Dialog_fileName_overWrite, true);

		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);

		// Create the Cancel button.
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);

		// Set help context for the OK button.
		okButton = super.getButton(IDialogConstants.OK_ID);

		// Set help context for the Cancel button.
		cancelButton = super.getButton(IDialogConstants.CANCEL_ID);

		// Set help context for the overwrite button
		// overWriteButton = super.getButton(OVER_WRITE_ID);

		// okButton.setEnabled(false);
	}

	/**
	 * Called when the OK button is selected.
	 */
	protected void okPressed() {
		super.okPressed();
	}

	/**
	 * Returns the File Name
	 */
	public String getFilePath() {
		return fileName;
	}

	public void setFilePath(String fileName) {
		this.fileName = fileName;
	}

	@Override
	protected Control createButtonBar(Composite parent) {
		return super.createButtonBar(parent);
	}

	@Override
	protected void buttonPressed(int buttonId) {
		if (buttonId == OVER_WRITE_ID) {
			overWritePressed();
		}
		super.buttonPressed(buttonId);
	}

	private void overWritePressed() {
		setReturnCode(OVER_WRITE_ID);
		close();
	}

	@Override
	protected void cancelPressed() {
		fileName = null;
		super.cancelPressed();
	}

	public String getMessageStr() {
		return messageStr;
	}

	public void setMessageStr(String messageStr) {
		this.messageStr = messageStr;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

}
