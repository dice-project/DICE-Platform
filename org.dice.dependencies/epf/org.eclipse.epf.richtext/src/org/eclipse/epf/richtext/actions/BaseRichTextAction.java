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
package org.eclipse.epf.richtext.actions;

/**
 * The abstract base class for all rich text action.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public abstract class BaseRichTextAction implements IBaseRichTextAction {

	protected String toolTipText;

	protected boolean enabled = true;

	/**
	 * Creates a new instance.
	 * 
	 * @param richText
	 *            a rich text control
	 */
	public BaseRichTextAction() {
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

}
