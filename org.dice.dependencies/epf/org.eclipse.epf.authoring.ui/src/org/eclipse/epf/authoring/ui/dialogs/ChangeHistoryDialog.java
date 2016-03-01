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
/**
 * 
 */
package org.eclipse.epf.authoring.ui.dialogs;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.uma.ContentDescription;
import org.eclipse.epf.uma.ContentElement;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import com.ibm.icu.util.StringTokenizer;


/**
 * Dialog to show change history of an element
 * 
 * @author Shilpa Toraskar
 * @since 1.0
 */
public class ChangeHistoryDialog extends Dialog {

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

	private MethodElement element;

	private Table table;

	/**
	 * Creates an instance
	 * 
	 * @param parent
	 * @param element
	 */
	public ChangeHistoryDialog(Shell parent, MethodElement element) {
		super(parent);

		this.element = element;
	}

	/**
	 * @see Dialog#createDialogArea(Composite parent)
	 */
	protected Control createDialogArea(Composite parent) {

		Composite dialogArea = (Composite) super.createDialogArea(parent);
		GridLayout layout = (GridLayout) dialogArea.getLayout();
		layout.marginWidth = 10;
		layout.marginHeight = 10;
		layout.numColumns = 1;
		GridData gd = (GridData) dialogArea.getLayoutData();
		gd.verticalIndent = 10;

		Label label = new Label(dialogArea, SWT.NONE);
		label.setText(AuthoringUIResources.ChangeHistoryDialog_label); 

		table = new Table(dialogArea, SWT.BORDER | SWT.MULTI);
		{
			GridData gridData = new GridData(GridData.FILL_BOTH);
			gridData.horizontalSpan = 2;
			gridData.widthHint = 400;
			gridData.heightHint = 300;
			table.setLayoutData(gridData);
			table.setLinesVisible(true);

		}

		initChangeHistoryTable();
		super.getShell().setText(
				AuthoringUIResources.ChangeHistoryDialog_title); 

		return dialogArea;

	}

	private void initChangeHistoryTable() {
		String changeDesc = null;
		if (element instanceof ContentElement) {
			ContentDescription contentDescription = ((ContentElement) element)
					.getPresentation();
			changeDesc = contentDescription.getChangeDescription();
		}
		if (element instanceof MethodPlugin) {
			changeDesc = ((MethodPlugin) element).getChangeDescription();
		}

		if (changeDesc != null) {
			StringTokenizer tok = new StringTokenizer(
					changeDesc,
					AuthoringUIResources.ChangeHistoryDialog_delimiter); 
			List strList = new ArrayList();

			while (tok.hasMoreTokens()) {
				strList.add(tok.nextToken());
			}

			for (int i = strList.size() - 1; i >= 0; i--) {
				TableItem item = new TableItem(table, SWT.NONE);
				item.setText((String) strList.get(i));
			}
		}

	}

	/**
	 * Creates the dialog buttons.
	 */
	protected void createButtonsForButtonBar(Composite parent) {
		// Create the OK button.
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);

		// Set help context for the OK button.
		okButton = super.getButton(IDialogConstants.OK_ID);
	}

	/**
	 * Called when the OK button is selected.
	 */
	protected void okPressed() {

		super.okPressed();
	}

}
