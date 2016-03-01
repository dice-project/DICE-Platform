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
package org.eclipse.epf.richtext;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.epf.richtext.actions.IRichTextAction;
import org.eclipse.epf.richtext.actions.RichTextAction;
import org.eclipse.epf.richtext.actions.RichTextComboAction;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ToolItem;

/**
 * The default rich text tool bar implementation.
 * 
 * Split into 2 toolbars (one for CCombos, one for buttons) for tabbing purposes.
 * 
 * @author Kelvin Low
 * @author Jeff Hardy
 * @since 1.0
 */
public class RichTextToolBar implements IRichTextToolBar {

	// The parent rich text control.
	private IRichText richText;

	// If true, add a new tool bar.
	private boolean addToolBar = true;

	// The current tool bar manager used to populate the tool actions.
	private ToolBarManager toolbarMgr;
	
	// The current tool bar manager used to populate the tool actions.
	private ToolBarManager toolbarMgrCombo;

	// The action items in the tool bar(s).
	private List<Object> actionItems = new ArrayList<Object>();

	private static int lastWidth = -1;
	
	protected Composite parent;

	/**
	 * Creates a new instance.
	 * 
	 * @param parent
	 *            the parent composite
	 * @param style
	 *            the tool bar style
	 * @param richText
	 *            the parent rich text control
	 */
	public RichTextToolBar(Composite parent, int style, IRichText richText) {
		this.parent = parent;
		this.richText = richText;

		addToolBar();
	}

	public void addAction(final IAction action) {
		if (action != null) {
			ActionContributionItem item = new ActionContributionItem(action);
			toolbarMgr.add(item);
			toolbarMgr.update(true);
			actionItems.add(item);
		}
	}

	/**
	 * Adds a combo action to the tool bar.
	 * 
	 * @param action
	 *            the action to add
	 */
	public void addAction(final RichTextComboAction item) {
		if (item != null) {
			item.init();
			toolbarMgrCombo.add(item);            
			toolbarMgrCombo.update(true);
			actionItems.add(item);           
		}
	}

	/**
	 * Adds a separator to the tool bar.
	 */
	public void addSeparator() {
		toolbarMgr.add(new Separator());
	}

	/**
	 * Updates the toolbar state.
	 * <p>
	 * Enables/disables actions depending on the currently selected
	 * RichTextEditor tab (RichText vs. HTML)
	 * 
	 * @param editable
	 *            specifies whether to enable non-ReadOnly commands
	 */
	public void updateToolBar(boolean editable) {
		boolean richTextMode = true;
		if (richText instanceof RichTextEditor
				&& ((RichTextEditor) richText).isHTMLTabSelected()) {
			richTextMode = false;
		}
		for (Iterator i = actionItems.iterator(); i.hasNext();) {
			Object item = i.next();
			if (item instanceof ToolItem) {
				boolean enabledToolItem = true;
				ToolItem toolItem = (ToolItem) item;
				IRichTextAction action = (IRichTextAction) toolItem.getData();
				if (action != null && !editable
						&& action.disableInReadOnlyMode()) {
					enabledToolItem = false;
				}
				if (action != null && !richTextMode
						&& action.disableInSourceMode()) {
					enabledToolItem = false;
				}
				toolItem.setEnabled(enabledToolItem);
			} else if (item instanceof Combo) {
				if (richTextMode && editable) {
					((Combo) item).setEnabled(true);
				} else {
					((Combo) item).setEnabled(false);
				}
			} else if (item instanceof ActionContributionItem) {
				boolean enabledToolItem = true;
				RichTextAction action = (RichTextAction) ((ActionContributionItem) item)
						.getAction();
				if (action != null && !editable
						&& action.disableInReadOnlyMode()) {
					enabledToolItem = false;
				}
				if (action != null && !richTextMode
						&& action.disableInSourceMode()) {
					enabledToolItem = false;
				}
				action.setEnabled(enabledToolItem);
			}
		}
	}

	/**
	 * Adds a tool bar, if necessary, to contain a button action or separator.
	 */
	protected void addToolBar() {
		if (addToolBar) {
			toolbarMgrCombo = new ToolBarManager(SWT.WRAP | SWT.FLAT | parent.getStyle());
			toolbarMgrCombo.createControl(parent);
			toolbarMgr = new ToolBarManager(SWT.WRAP | SWT.FLAT | parent.getStyle());
			toolbarMgr.createControl(parent);
			addToolBar = false;
		}
	}

	public ToolBarManager getToolbarMgr() {
		return toolbarMgr;
	}

	public ToolBarManager getToolbarMgrCombo() {
		return toolbarMgrCombo;
	}

}
