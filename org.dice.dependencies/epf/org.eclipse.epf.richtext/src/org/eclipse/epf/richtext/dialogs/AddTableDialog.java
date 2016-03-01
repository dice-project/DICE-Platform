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
import org.eclipse.epf.richtext.html.Table;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * Prompts the user to specify the attributes that will be used to create a HTML
 * &lt;table&gt; tag in the rich text editor.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class AddTableDialog extends BaseDialog {

	private static final int DEFAULT_ROWS = 2;

	private static final int DEFAULT_COLUMNS = 2;

	private static final String DEFAULT_WIDTH = "85%"; //$NON-NLS-1$

	private static final String[] TABLE_STYLE_LABELS = {
			RichTextResources.tableHeaderNone_text,
			RichTextResources.tableHeaderCols_text,
			RichTextResources.tableHeaderRows_text,
			RichTextResources.tableHeaderBoth_text, };

	private Table table = new Table();

	private Text rowsText;

	private Text colsText;

	private Text widthText;

	private Combo tableTypeCombo;

	private Text summaryText;

	private Text captionText;

	private ModifyListener modifyListener = new ModifyListener() {
		public void modifyText(ModifyEvent event) {
			if (okButton != null) {
				try {
					int rows = Integer.parseInt(rowsText.getText().trim());
					int cols = Integer.parseInt(colsText.getText().trim());
					String width = widthText.getText().trim();
					okButton.setEnabled(rows > 0 && cols > 0
							&& width.length() > 0);
				} catch (Exception e) {
					okButton.setEnabled(false);
				}
			}
		}
	};

	/**
	 * Creates a new instance.
	 * 
	 * @param parent
	 *            the parent shell
	 */
	public AddTableDialog(Shell parent) {
		super(parent);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);

		Label rowsLabel = new Label(composite, SWT.NONE);
		rowsLabel.setText(RichTextResources.rowsLabel_text);

		rowsText = new Text(composite, SWT.BORDER);
		rowsText.setTextLimit(2);
		rowsText.setText("" + DEFAULT_ROWS); //$NON-NLS-1$
		{
			GridData gridData = new GridData(GridData.FILL_HORIZONTAL
					| GridData.GRAB_HORIZONTAL);
			rowsText.setLayoutData(gridData);
		}
		rowsText.addModifyListener(modifyListener);

		Label colsLabel = new Label(composite, SWT.NONE);
		colsLabel.setText(RichTextResources.columnsLabel_text);

		colsText = new Text(composite, SWT.BORDER);
		colsText.setTextLimit(2);
		colsText.setText("" + DEFAULT_COLUMNS); //$NON-NLS-1$
		{
			GridData gridData = new GridData(GridData.FILL_HORIZONTAL
					| GridData.GRAB_HORIZONTAL);
			colsText.setLayoutData(gridData);
		}
		colsText.addModifyListener(modifyListener);

		Label widthLabel = new Label(composite, SWT.NONE);
		widthLabel.setText(RichTextResources.widthLabel_text);

		widthText = new Text(composite, SWT.BORDER);
		widthText.setText("" + DEFAULT_WIDTH); //$NON-NLS-1$
		{
			GridData gridData = new GridData(GridData.FILL_HORIZONTAL
					| GridData.GRAB_HORIZONTAL);
			widthText.setLayoutData(gridData);
		}
		widthText.addModifyListener(modifyListener);

		Label headerTypeLabel = new Label(composite, SWT.NONE);
		headerTypeLabel.setText(RichTextResources.tableStyleLabel_text);

		tableTypeCombo = new Combo(composite, SWT.BORDER | SWT.READ_ONLY);
		tableTypeCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		tableTypeCombo.setItems(TABLE_STYLE_LABELS);
		tableTypeCombo.setText(TABLE_STYLE_LABELS[0]);

		Label summaryLabel = new Label(composite, SWT.NONE);
		summaryLabel.setText(RichTextResources.summaryLabel_text);
		summaryText = new Text(composite, SWT.BORDER);
		{
			GridData gridData = new GridData(GridData.FILL_HORIZONTAL
					| GridData.GRAB_HORIZONTAL);
			summaryText.setLayoutData(gridData);
		}

		Label captionLabel = new Label(composite, SWT.NONE);
		captionLabel.setText(RichTextResources.captionLabel_text);
		captionText = new Text(composite, SWT.BORDER);
		{
			GridData gridData = new GridData(GridData.FILL_HORIZONTAL
					| GridData.GRAB_HORIZONTAL);
			captionText.setLayoutData(gridData);
		}

		super.getShell().setText(RichTextResources.addTableDialog_title);

		return composite;
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		super.createButtonsForButtonBar(parent);
	}

	@Override
	protected void okPressed() {
		String rowsValue = rowsText.getText();
		if (rowsValue != null && rowsValue.length() > 0) {
			try {
				int rows = Integer.parseInt(rowsValue);
				table.setRows(rows);
			} catch (Exception e) {
				table.setRows(DEFAULT_ROWS);
			}
		}

		String colsValue = colsText.getText();
		if (colsValue != null && colsValue.length() > 0) {
			try {
				int cols = Integer.parseInt(colsValue);
				table.setColumns(cols);
			} catch (Exception e) {
				table.setColumns(DEFAULT_COLUMNS);
			}
		}

		String widthValue = widthText.getText();
		if (widthValue != null && widthValue.length() > 0) {
			table.setWidth(widthValue);
		} else {
			table.setWidth(DEFAULT_WIDTH);
		}

		table.setSummary(summaryText.getText().trim());
		table.setCaption(captionText.getText().trim());
		table.setTableHeaders(tableTypeCombo.getSelectionIndex());

		super.okPressed();
	}

	/**
	 * Gets the user specified table.
	 * 
	 * @return an <code>Table</code> object
	 */
	public Table getTable() {
		return table;
	}

}