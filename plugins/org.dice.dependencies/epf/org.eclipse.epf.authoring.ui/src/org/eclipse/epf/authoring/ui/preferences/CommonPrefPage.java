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
package org.eclipse.epf.authoring.ui.preferences;

import org.eclipse.epf.common.preferences.IPreferenceStoreWrapper;
import org.eclipse.epf.common.ui.PreferenceStoreWrapper;
import org.eclipse.epf.library.LibraryPlugin;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * Preference page for common options
 * 
 * @author Shilpa Toraskar
 * @since 1.0
 *
 */
public class CommonPrefPage extends PreferencePage {

	/**
	 * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse.swt.widgets.Composite)
	 */
	protected Control createContents(Composite parent) {
		// do nothing in this common preference page
		return null;
	}

	protected Composite createGridComposite(Composite parent, int numColumns) {

		Composite composite = new Composite(parent, SWT.NULL);

		// GridLayout
		GridLayout layout = new GridLayout();
		layout.numColumns = numColumns;
		composite.setLayout(layout);

		// GridData
		GridData data = new GridData();
		data.verticalAlignment = GridData.FILL;
		data.horizontalAlignment = GridData.FILL;
		composite.setLayoutData(data);

		return composite;
	}

	protected void openDirectoryDialog(Composite parent, Text path_ctr) {
		try {
			DirectoryDialog dd = new DirectoryDialog(parent.getShell(),
					SWT.NONE);

			String destination = dd.open();
			if (destination != null) {
				path_ctr.setText(destination);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Method to create a Label
	 * @param parent parent composite of the Label
	 * @param text Label text
	 * @param span horizontal span
	 * @return the newly created Label
	 */
	protected Label createLabel(Composite parent, String text, int span) {
		Label label = new Label(parent, SWT.LEFT);
		label.setText(text);
		GridData data = new GridData();
		data.horizontalSpan = span;
		data.horizontalAlignment = GridData.FILL;
		label.setLayoutData(data);
		return label;
	}

	/**
	 * Method to create a radio button
	 * @param parent parent composite of the button
	 * @param label the button label
	 * @return the newly created Radio Button
	 */
	protected Button createRadioButton(Composite parent, String label) {
		Button button = new Button(parent, SWT.RADIO | SWT.LEFT);
		button.setText(label);
		GridData data = new GridData();
		button.setLayoutData(data);
		return button;
	}

	/**
	 * Method to create a line
	 * @param parent parent composite of the line
	 * @param ncol horizontal span
	 */
	protected void createLine(Composite parent, int ncol) {
		Label line = new Label(parent, SWT.SEPARATOR | SWT.HORIZONTAL
				| SWT.BOLD);
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan = ncol;
		line.setLayoutData(gridData);
	}

	/**
	 * return the preference store
	 */
	protected IPreferenceStore doGetPreferenceStore() {

		IPreferenceStoreWrapper wrapper = LibraryPlugin.getDefault().getPreferenceStore();
		if ( wrapper instanceof PreferenceStoreWrapper ) {
			return ((PreferenceStoreWrapper)wrapper).getStore();
		}
		return null;
		// return PlatformUI.getWorkbench().getPreferenceStore();

	}

}
