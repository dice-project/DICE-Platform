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
package org.eclipse.epf.richtext;

import org.eclipse.epf.richtext.actions.RichTextComboAction;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.ToolBarManager;

/**
 * The interface for a rich text editor toolbar.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public interface IRichTextToolBar {

	
	/**
	 * Adds a button action to the tool bar.
	 * 
	 * @param action
	 *            the button action to add
	 */
	public void addAction(IAction action);

	/**
	 * Adds a combo action to the tool bar.
	 * 
	 * @param action
	 *            the combo action to add
	 */
	public void addAction(RichTextComboAction item);

	/**
	 * Adds a separator to the tool bar.
	 */
	public void addSeparator();

	/**
	 * Updates the toolbar state.
	 * <p>
	 * Enables/disables actions depending on the currently selected
	 * RichTextEditor tab (RichText vs. HTML)
	 * 
	 * @param editable specifies whether to enable non-ReadOnly commands
	 */
	public void updateToolBar(boolean enabled);
	
	/**
	 * 
	 * @return the toolbar manager
	 */
	public ToolBarManager getToolbarMgr();
}
