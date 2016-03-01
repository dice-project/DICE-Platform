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

import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TreeEditor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

/**
 * An abstract class for check box cell editor
 * @author An Ming
 * @author Shilpa Toraskar
 * @since 1.2
 */
public abstract class AbstractCheckBoxCellEditor {

	// the cell modifier
	private ICellModifier modifier = null;

	// tree editor
	private TreeEditor treeEditor = null;

	// the tree
	private Tree tree = null;

	// the control editor
	private Control control = null;

	// the column index
	private int columnIndex;

	/**
	 * Construct an instance
	 * @param tree
	 */
	public AbstractCheckBoxCellEditor(Composite tree) {
		this.tree = (Tree) tree;
		this.treeEditor = new TreeEditor(this.tree);
	}


	/**
	 * Set cell modifier
	 * @param modifier
	 */
	public void setCellModifier(ICellModifier modifier) {
		this.modifier = modifier;
	}

	/**
	 * Get the cell modifier
	 * 
	 * @return
	 * 		CellModifier
	 */
	protected ICellModifier getCellModifier() {
		return this.modifier;
	}

	public abstract Image getImage(TreeItem item, String columnproperty);

	public abstract void modify(TreeItem item, String columnproperty);

	/*
	 * =======in fact below codes are for the dynamic cell editor (real control
	 * "button")=====
	 */
	public void setEditorTo(TreeItem item, String columnproperty) {
		if (treeEditor.getItem() != null)
			return;
		if (control == null)
			control = createControl(tree);
		hookControl(control, item, columnproperty);

		treeEditor.minimumWidth = control.getSize().x;
		treeEditor.horizontalAlignment = SWT.CENTER;
		treeEditor.setEditor(control, item, columnIndex);

	}

	public void deleteEditorFrom(TreeItem item, String columnproperty) {
		control.setVisible(false);
		treeEditor.setEditor(null, null, columnIndex);
	}

	protected abstract Control createControl(Tree parent);

	protected abstract void hookControl(Control control, TreeItem item,
			String columnproperty);

	/**
	 * Get column index
	 * @return
	 * 		index
	 */
	public int getColumnIndex() {
		return columnIndex;
	}

	/**
	 * Set index for column
	 * @param columnIndex
	 */
	public void setColumnIndex(int columnIndex) {
		this.columnIndex = columnIndex;
	}
}
