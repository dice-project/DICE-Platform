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
package org.eclipse.epf.library.ui.xmi.internal.migration;

import java.util.List;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.dialogs.ListSelectionDialog;

/**
 * Displays a dialog that lists missing files in a method library to be upgraded.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class SelectionDialog extends ListSelectionDialog {

	private String[] columnProperties;

	/**
	 * Creates a new instance.
	 */
	public SelectionDialog(Shell parentShell, Object input,
			IStructuredContentProvider contentProvider,
			ILabelProvider labelProvider, String message) {
		super(parentShell, input, contentProvider, labelProvider, message);
	}

	/**
	 * Creates a new instance.
	 */
	public SelectionDialog(Shell parentShell, List input,
			ILabelProvider labelProvider, String message) {
		super(parentShell, input, new IStructuredContentProvider() {
			public Object[] getElements(Object object) {
				return ((List) object).toArray();
			}

			public void dispose() {
			}

			public void inputChanged(Viewer viewer, Object oldInput,
					Object newInput) {
			}
		}, labelProvider, message);
	}

	public void setColumnProperties(String[] columnProperties) {
		this.columnProperties = columnProperties;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.dialogs.ListSelectionDialog#createDialogArea(org.eclipse.swt.widgets.Composite)
	 */
	protected Control createDialogArea(Composite parent) {
		Control ctrl = super.createDialogArea(parent);

		// create table columns
		//
		Table table = getViewer().getTable();
		table.setHeaderVisible(true);
		for (int i = 0; i < columnProperties.length; i++) {
			TableColumn col = new TableColumn(table, SWT.LEFT);
			col.setText(columnProperties[i]);
			col.setResizable(true);
			col.setWidth(200);
		}
		getViewer().setColumnProperties(columnProperties);
		getViewer().refresh();

		return ctrl;
	}

}
