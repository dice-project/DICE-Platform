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
import org.eclipse.epf.library.ui.preferences.LibraryUIPreferences;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Shell;

/**
 * Promps the user to select a method library folder.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class SelectLibraryDirectoryDialog {

	private DirectoryDialog dialog;

	/**
	 * Creates a new instance.
	 * 
	 * @param parent
	 *            The parent shell.
	 */
	public SelectLibraryDirectoryDialog(Shell parent) {
		this(parent, SWT.NONE);
	}

	/**
	 * Creates a new instance.
	 * 
	 * @param parent
	 *            The parent shell.
	 * @param style
	 *            The dialog style.
	 */
	public SelectLibraryDirectoryDialog(Shell parent, int style) {
		dialog = new DirectoryDialog(parent, style);
		dialog.setText(LibraryUIResources.selectLibraryFolderDialog_title);
		dialog.setMessage(LibraryUIResources.selectLibraryFolderDialog_text);
		dialog.setFilterPath(LibraryUIPreferences.getDefaultLibraryPath());
	}

	/**
	 * Sets the filter path.
	 * 
	 * @param path
	 *            The filter path.
	 */
	public void setFilterPath(String path) {
		dialog.setFilterPath(path);
	}

	/**
	 * Makes the dialog visible and brings it to the front of the display.
	 */
	public String open() {
		return dialog.open();
	}

}
