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
package org.eclipse.epf.importing.wizards;

import org.eclipse.epf.importing.ImportResources;
import org.eclipse.epf.importing.services.ConfigurationImportData;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;

/**
 * Displays the before and after method library differences for an import
 * operation.
 * 
 * @author Jinhua Xi
 * @author Kelvin Low
 * @since 1.0
 */
public class DiffReportViewer {

	protected Composite parent;

	protected CheckboxTreeViewer treeViewer;

	ConfigurationImportData data;
	
	/**
	 * Creates a new instance.
	 */
	DiffReportViewer(Composite parent, ConfigurationImportData data) {
		this.parent = parent;
		this.data = data;
		
		treeViewer = new CheckboxTreeViewer(parent);
		treeViewer.getControl()
				.setLayoutData(new GridData(GridData.FILL_BOTH));
		treeViewer.setContentProvider(new DiffReportContentProvider());
		treeViewer.setLabelProvider(new DiffReportLabelProvider());
		createTableColumns();
		
		addListeners();
		
	}

	/**
	 * Creates the table columns.
	 */
	private void createTableColumns() {
		Tree tree = treeViewer.getTree();
		tree.setLayoutData(new GridData(GridData.FILL_BOTH));
		tree.setHeaderVisible(true);
		tree.setLinesVisible(true);

		TreeColumn column = new TreeColumn(tree, SWT.NONE, 0);
		column.setText(ImportResources.DiffReportViewer_content); 
		column.setWidth(200);

		column = new TreeColumn(tree, SWT.NONE, 1);
		column.setText(ImportResources.DiffReportViewer_type); 
		column.setWidth(150);

		column = new TreeColumn(tree, SWT.NONE, 2);
		column.setText(ImportResources.DiffReportViewer_in_import); 
		column.setWidth(75);

		column = new TreeColumn(tree, SWT.NONE, 3);
		column.setText(ImportResources.DiffReportViewer_in_lib); 
		column.setWidth(75);

		column = new TreeColumn(tree, SWT.NONE, 4);
		column.setText(ImportResources.DiffReportViewer_status); 
		column.setWidth(100);
	}

	/**
	 * Displays the reviw.
	 */
	public void showReport(Object input) {
		treeViewer.getTree().setVisible(false);
		treeViewer.setInput(input);
		setCheckStates();
		treeViewer.expandAll();
		treeViewer.getTree().setVisible(true);
	}

	private void addListeners() {
		// add a check state change listener
		treeViewer.addCheckStateListener(new ICheckStateListener() {
			public void checkStateChanged(CheckStateChangedEvent event) {
				boolean checked = event.getChecked();
				updateCheckStates(event.getElement(), checked);
			}
		});
	}
	
	private void updateCheckStates(Object element, boolean checked) {

		// Object element = event.getElement();

		if (checked == true) {
			ITreeContentProvider cp = (ITreeContentProvider) treeViewer
					.getContentProvider();
			checkParent(cp, element);
			// treeViewer.setChecked(element, true);
		}
		else
			treeViewer.setChecked(element, false);
			
		selectionChildren(element, checked);

		setData(treeViewer.getCheckedElements());

	}

	/**
	 * @param element
	 * @param checked
	 */
	public void selectionChildren(Object element, boolean checked) {
		ITreeContentProvider cp = (ITreeContentProvider) treeViewer
				.getContentProvider();
		Object[] childs = cp.getChildren(element);
		for (int i = 0; i < childs.length; i++) {
			treeViewer.setChecked(childs[i], checked);
			selectionChildren(childs[i], checked);
		}
	}


	private void checkParent(ITreeContentProvider cp, Object element) {
		if (element == null || element instanceof MethodLibrary /*
																 * || element ==
																 * currentRootNode
																 */) {
			return;
		}
		Object parent = LibraryUtil.unwrap(cp.getParent(element));
		if (parent != null) {
			treeViewer.setChecked(parent, true);
			// configFactory.getCurrentConfiguration().add((EObject)parent,
			// false);
			checkParent(cp, parent);
		}

	}
	
	private void setCheckStates() {
		treeViewer.getTree().setVisible(false);
		try {
			Object element = treeViewer.getInput();
			selectionChildren(element, true);
		} finally {
			treeViewer.getTree().setVisible(true);
		}

		setData(treeViewer.getCheckedElements());
	}
	
	private void setData(Object[] sels) {
		if ( sels == null ) {
			return;
		}
		
		data.importList.clear();
		for ( int i = 0; i < sels.length; i++ ) {
			data.importList.add(sels[i]);
		}
	}
}
