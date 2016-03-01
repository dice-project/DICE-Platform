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
package org.eclipse.epf.richtext.actions;

import java.util.Collection;

import org.eclipse.epf.common.ui.actions.CComboContributionItem;
import org.eclipse.epf.richtext.IRichText;
import org.eclipse.swt.SWT;

/**
 * The abstract implementation of a rich text combo action.
 * 
 * @author Kelvin Low
 * @author Jeff Hardy
 * @since 1.0
 */
public abstract class RichTextComboAction extends CComboContributionItem {
	
	protected IRichText richText;
	
	protected String toolTipText;

	protected boolean enabled = true;
	
	protected boolean notifyListeners = false;

	
	/**
	 * Creates a new instance.
	 * 
	 * @param richText
	 *            a rich text control
	 */
	public RichTextComboAction(IRichText richText) {
		super(SWT.READ_ONLY | SWT.FLAT | SWT.BORDER);
		this.richText = richText;
	}

	/**
	 * Creates a new instance.
	 * 
	 * @param richText
	 *            a rich text control
	 */
	public RichTextComboAction(IRichText richText, int style) {
		super(SWT.READ_ONLY | SWT.FLAT | SWT.BORDER | style);
		this.richText = richText;
	}


	/**
	 * Executes the action.
	 * 
	 * @param richText
	 *            a rich text control
	 * @param index
	 *            the index of the selected item
	 */
	public abstract void execute(IRichText richText);
	
	public abstract Collection<String> getInput();
	
	public void init() {
		setInput(getInput());
		setNotifyListeners(true);
	}
	
	protected String getCComboSelection() {
		if (getCCombo() != null) {
			int index = getSelectionIndex();
			return getCCombo().getItem(index);
		}
		return null;
	}
	
	/**
	 * Returns the tool tip for the action.
	 * 
	 * @return the tool tip text
	 */
	public String getToolTipText() {
		return toolTipText;
	}

	/**
	 * Sets the tool tip for the action.
	 * 
	 * @param toolTipText
	 *            the tool tip text
	 */
	public void setToolTipText(String toolTipText) {
		this.toolTipText = toolTipText;
	}

	/**
	 * Returns the enabled status of the action.
	 * 
	 * @return <code>true</code> if enabled, <code>false</code> if not
	 */
	public boolean getEnabled() {
		return enabled;
	}

	/**
	 * Enables or disables the action.
	 * 
	 * @param enabled
	 *            if <code>true</code>, enable the action. if
	 *            <code>false</code>, disable it.
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	@Override
	protected void performSelectionChanged() {
		if (notifyListeners) {
			execute(richText);
		}
	}

	public boolean isNotifyListeners() {
		return notifyListeners;
	}

	public void setNotifyListeners(boolean notifyListeners) {
		this.notifyListeners = notifyListeners;
	}
}
