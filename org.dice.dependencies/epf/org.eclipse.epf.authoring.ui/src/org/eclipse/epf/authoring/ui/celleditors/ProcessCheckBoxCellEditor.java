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
package org.eclipse.epf.authoring.ui.celleditors;

import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

/**
 * Check Box cell editor for process form page, WBS, TA, WPU
 * 
 * @author An Ming
 * @author Shilpa Toraskar
 * @since 1.2
 */
public class ProcessCheckBoxCellEditor extends AbstractCheckBoxCellEditor {
	
	// checkbox images
	private final Image checkImage = AuthoringUIPlugin.getDefault().getSharedImage(
			"full/obj16/Check.gif"); //$NON-NLS-1$

	private final Image uncheckImage = AuthoringUIPlugin.getDefault().getSharedImage(
			"full/obj16/Uncheck.gif"); //$NON-NLS-1$

	private final Image disableCheckImage = AuthoringUIPlugin.getDefault().getSharedImage(
		"full/obj16/DisableCheck.gif"); //$NON-NLS-1$
	
	private final Image disableUncheckImage = AuthoringUIPlugin.getDefault().getSharedImage(
		"full/obj16/DisableUncheck.gif"); //$NON-NLS-1$
	
	private SelectionListener listener;

	/**
	 * Constructor
	 * @param tree
	 */
	public ProcessCheckBoxCellEditor(Composite tree) {
		super(tree);
	}


	/* (non-Javadoc)
	 * @see org.eclipse.epf.authoring.ui.celleditors.AbstractCheckBoxCellEditor#getImage(org.eclipse.swt.widgets.TreeItem, java.lang.String)
	 */
	public Image getImage(TreeItem item, String columnproperty) {
		final ICellModifier modifier = getCellModifier();
		Object element = item.getData();
		if (element == null)
			return null;
//		if (columnproperty == IBSItemProvider.COL_IS_ONGOING
//				|| columnproperty == IBSItemProvider.COL_IS_REPEATABLE
//				|| columnproperty == IBSItemProvider.COL_IS_EVENT_DRIVEN) {
//			if (!(TngUtil.unwrap(element) instanceof WorkBreakdownElement)) {
//				return null;
//			}
//		}
		Object value = modifier.getValue(element, columnproperty);
		if (value == null || !(value instanceof Boolean))
			return null;
		boolean canModify = modifier.canModify(element, columnproperty);

		if (((Boolean) value).booleanValue()) {
			return canModify ? checkImage : disableCheckImage;
		} else {
			return canModify ? uncheckImage : disableUncheckImage;
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.authoring.ui.celleditors.AbstractCheckBoxCellEditor#modify(org.eclipse.swt.widgets.TreeItem, java.lang.String)
	 */
	public void modify(TreeItem item, String columnproperty) {
		final ICellModifier modifier = getCellModifier();
		if (!modifier.canModify(item.getData(), columnproperty))
			return;
		modifier.modify(item, columnproperty, new Boolean(!((Boolean) modifier
				.getValue(item.getData(), columnproperty)).booleanValue()));
	}

	/*
	 * =======in fact below codes are for the dynamic cell editor (real control
	 * "button")=====
	 */
	/* (non-Javadoc)
	 * @see org.eclipse.epf.authoring.ui.celleditors.AbstractCheckBoxCellEditor#createControl(org.eclipse.swt.widgets.Tree)
	 */
	protected Control createControl(Tree parent) {
		Button button = new Button(parent, SWT.CHECK);
		button.pack();
		return button;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.authoring.ui.celleditors.AbstractCheckBoxCellEditor#hookControl(org.eclipse.swt.widgets.Control, org.eclipse.swt.widgets.TreeItem, java.lang.String)
	 */
	protected void hookControl(Control control, TreeItem item,
			final String columnproperty) {
		final ICellModifier modifier = getCellModifier();
		final Object element = item;
		final Button checkbox = (Button) control;
		if (listener != null)
			checkbox.removeSelectionListener(listener);
		listener = new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				modifier.modify(element, columnproperty, new Boolean(checkbox
						.getSelection()));
			}
		};
		checkbox.addSelectionListener(listener);
	}
}
