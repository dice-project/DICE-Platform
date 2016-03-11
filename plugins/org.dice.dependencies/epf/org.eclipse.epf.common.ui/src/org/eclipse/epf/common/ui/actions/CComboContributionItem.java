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
package org.eclipse.epf.common.ui.actions;

import java.util.Collection;

import org.eclipse.jface.action.ContributionItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.widgets.CoolItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

/**
 * Wraps a CCombo in a ContributionItem for use in a toolbar
 * 
 * Does not use a ComboViewer because of tabbing issues - see bug 78885
 * @author Jeff Hardy
 *
 */
public class CComboContributionItem extends ContributionItem {

	protected CCombo CCombo;

	protected ToolItem toolItem;

	protected CoolItem coolItem;
	
	protected int style;
	
	protected Collection<String> input;

	/**
	 * Creates a new instance.
	 */
	public CComboContributionItem(int style) {
		super();
		this.style = style;
	}

	/*
	 * @see org.eclipse.jface.action.ContributionItem#fill(org.eclipse.swt.widgets.ToolBar,
	 *      int)
	 */
	public void fill(ToolBar parent, int index) {
		toolItem = new ToolItem(parent, SWT.SEPARATOR);
		Control box = createControl(parent);
		toolItem.setControl(box);
		Point preferredSize = CCombo.computeSize(SWT.DEFAULT, SWT.DEFAULT, true);
		toolItem.setWidth(preferredSize.x);
	}

	/*
	 * @see org.eclipse.jface.action.ContributionItem#fill(org.eclipse.swt.widgets.CoolBar,
	 *      int)
	 */
	public void fill(CoolBar coolBar, int index) {
		Control box = createControl(coolBar);

		if (index >= 0) {
			coolItem = new CoolItem(coolBar, SWT.DROP_DOWN, index);
		} else {
			coolItem = new CoolItem(coolBar, SWT.DROP_DOWN);
		}

		// Set the back reference.
		coolItem.setData(this);

		// Add the toolbar to the CoolItem widget.
		coolItem.setControl(box);

		// If the toolbar item exists then adjust the size of the cool item.
		Point toolBarSize = box.computeSize(SWT.DEFAULT, SWT.DEFAULT);

		// Set the preferred size to the size of the toolbar plus trim.
		coolItem.setMinimumSize(toolBarSize);
		coolItem.setPreferredSize(toolBarSize);
		coolItem.setSize(toolBarSize);
	}

	/*
	 * @see org.eclipse.jface.action.ContributionItem#fill(org.eclipse.swt.widgets.Composite)
	 */
	public void fill(Composite parent) {
		createControl(parent);
	}

	/**
	 * Creates the control.
	 */
	protected Control createControl(final Composite parent) {
		CCombo = new CCombo(parent, style);
		CCombo.setVisibleItemCount(10);
		CCombo.setEnabled(true);
		CCombo.setItems(input.toArray(new String[0]));
		CCombo.addDisposeListener(
				new DisposeListener() {
					public void widgetDisposed(DisposeEvent event) {
						dispose();
					}
				});

		CCombo.addSelectionListener(new SelectionListener() {
					public void widgetDefaultSelected(SelectionEvent e) {
					}

					public void widgetSelected(SelectionEvent e) {
						performSelectionChanged();
					}
				});

		return CCombo;
	}

	/**
	 * Returns the currently selected method configuration
	 */
	protected int getSelectionIndex() {
		return CCombo.getSelectionIndex();
	}
	
	protected void setInput(Collection<String> input) {
		this.input = input;
	}


	protected void performSelectionChanged() {
	}

	/*
	 * @see org.eclipse.jface.action.ContributionItem#dispose()
	 */
	public void dispose() {
		super.dispose();
	}

	public CCombo getCCombo() {
		return CCombo;
	}

	public ToolItem getToolItem() {
		return toolItem;
	}

	public CoolItem getCoolItem() {
		return coolItem;
	}
}
