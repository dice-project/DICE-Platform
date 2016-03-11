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

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

/**
 * The base class for all Library UI dialogs.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class BaseDialog extends TitleAreaDialog {

	/**
	 * The OK button.
	 */
	protected Button okButton;

	/**
	 * The Cancel button.
	 */
	protected Button cancelButton;

	/**
	 * Creates a new instance given the parent control.
	 * 
	 * @param parent
	 *            The parent control.
	 */
	public BaseDialog(Shell parent) {
		super(parent);
	}

	/**
	 * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(Composite parent)
	 */
	protected Control createDialogArea(Composite parent) {
		return super.createDialogArea(parent);
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
	}

}
