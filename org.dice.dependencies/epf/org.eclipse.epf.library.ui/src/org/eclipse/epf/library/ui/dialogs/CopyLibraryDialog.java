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
package org.eclipse.epf.library.ui.dialogs;

import org.eclipse.epf.library.ui.LibraryUIResources;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

/**
 * Dialog to prompt the user to copy a library to another location
 * @author Jeff Hardy
 *
 */
public class CopyLibraryDialog extends PathSelectionDialog {
	
	public CopyLibraryDialog(Shell parentShell, String dialogTitle,
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
			b.setText(LibraryUIResources.copyLibraryDialog_copyButton_text);

		} else if (id == IDialogConstants.CANCEL_ID) {
			b.setText(LibraryUIResources.skipButton_text);
		}

		return b;
	}
	
	

}
