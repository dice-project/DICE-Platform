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
// Copyright (c) 2005, 2006 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.richtext.preferences;

import org.eclipse.epf.richtext.RichTextPlugin;
import org.eclipse.epf.richtext.RichTextResources;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * The Preference page for the rich text editor.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class RichTextPreferencePage extends PreferencePage implements
		IWorkbenchPreferencePage, SelectionListener, ModifyListener {

	private IPreferenceStore store;

	private Text lineWidthText;

	private Button indentCheckbox;

	private Text indentSizeText;

	/**
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
		store = RichTextPlugin.getDefault().getPreferenceStore();
	}

	/**
	 * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse.swt.widgets.Composite)
	 */
	protected Control createContents(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Composite widthComposite = new Composite(composite, SWT.NONE);
		widthComposite.setLayout(new GridLayout(2, false));
		widthComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label lineWidthLabel = new Label(widthComposite, SWT.NONE);
		lineWidthLabel.setText(RichTextResources.maxCharsPerLineLabel_text); 

		lineWidthText = new Text(widthComposite, SWT.BORDER);
		lineWidthText.setText(store.getString(RichTextPreferences.LINE_WIDTH));
		lineWidthText.setTextLimit(3);
		GridData gridData = new GridData();
		gridData.widthHint = 25;
		lineWidthText.setLayoutData(gridData);
		lineWidthText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
			}
		});

		Composite indentComposite = new Composite(composite, SWT.NONE);
		indentComposite.setLayout(new GridLayout(2, false));
		indentComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		indentCheckbox = new Button(indentComposite, SWT.CHECK);
		indentCheckbox.setText(RichTextResources.indentHTMLCheckbox_text); 
		indentCheckbox.setSelection(store
				.getBoolean(RichTextPreferences.INDENT));

		new Label(indentComposite, SWT.NONE);

		Label indentSizeLabel = new Label(indentComposite, SWT.NONE);
		gridData = new GridData();
		gridData.horizontalIndent = 20;
		indentSizeLabel.setLayoutData(gridData);
		indentSizeLabel.setText(RichTextResources.indentSizeLabel_text); 

		indentSizeText = new Text(indentComposite, SWT.BORDER);
		indentSizeText
				.setText(store.getString(RichTextPreferences.INDENT_SIZE));
		indentSizeText.setTextLimit(1);
		gridData = new GridData();
		gridData.widthHint = 10;
		indentSizeText.setLayoutData(gridData);
		indentSizeText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
			}
		});

		return composite;
	}

	/**
	 * @see org.eclipse.jface.preference.PreferencePage.performDefaults
	 */
	protected void performDefaults() {
		super.performDefaults();
		lineWidthText.setText(store
				.getDefaultString(RichTextPreferences.LINE_WIDTH));
		indentCheckbox.setSelection(store
				.getDefaultBoolean(RichTextPreferences.INDENT));
		indentSizeText.setText(store
				.getDefaultString(RichTextPreferences.INDENT_SIZE));
	}

	/**
	 * @see org.eclipse.jface.preference.PreferencePage.performOk
	 */
	public boolean performOk() {
		String lineWidthValue = lineWidthText.getText();
		if (lineWidthValue != null && lineWidthValue.length() > 0) {
			try {
				int lineWidth = Integer.parseInt(lineWidthValue);
				store.setValue(RichTextPreferences.LINE_WIDTH, lineWidth);
			} catch (Exception e) {
			}
		}

		boolean indentValue = indentCheckbox.getSelection();
		store.setValue(RichTextPreferences.INDENT, indentValue);

		String indentSizeValue = indentSizeText.getText();
		if (indentSizeValue != null && indentSizeValue.length() > 0) {
			try {
				int indentSize = Integer.parseInt(indentSizeValue);
				store.setValue(RichTextPreferences.INDENT_SIZE, indentSize);
			} catch (Exception e) {
			}
		}

		return true;
	}

	/**
	 * @see org.eclipse.swt.events.SelectionListener#widgetSelected(SelectionEvent)
	 */
	public void widgetSelected(SelectionEvent e) {
	}

	/**
	 * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(SelectionEvent)
	 */
	public void widgetDefaultSelected(SelectionEvent e) {
	}

	/**
	 * @see org.eclipse.swt.events.ModifyListener#modifyText(ModifyEvent)
	 */
	public void modifyText(ModifyEvent e) {
	}

}
