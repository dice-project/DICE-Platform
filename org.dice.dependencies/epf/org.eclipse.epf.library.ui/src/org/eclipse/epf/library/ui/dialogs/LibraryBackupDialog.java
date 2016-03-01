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
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

/**
 * Utility class to back up library.
 * 
 * @author Jinhua Xi
 * @since 1.0
 */
public class LibraryBackupDialog extends PathSelectionDialog {

	public LibraryBackupDialog(Shell parentShell, String dialogTitle,
			String dialogMessage, String defaultPath) {
		super(parentShell, dialogTitle, dialogMessage, defaultPath);
	}

	
	/**
	 * @see org.eclipse.jface.dialogs.Dialog#createButton(org.eclipse.swt.widgets.Composite, int, java.lang.String, boolean)
	 */
	protected Button createButton(Composite parent, int id, String label,
			boolean defaultButton) {
		Button b = super.createButton(parent, id, label, defaultButton);
		if (id == IDialogConstants.OK_ID) {
			b.setText(LibraryUIResources.okButton_text);

		} else if (id == IDialogConstants.CANCEL_ID) {
			b.setText(LibraryUIResources.skipButton_text);
		}

		return b;
	}
}
