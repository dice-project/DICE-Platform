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
package org.eclipse.epf.richtext;

import org.eclipse.epf.richtext.actions.FindReplaceAction;

/**
 * The interface for a rich text editor.
 * <p>
 * A rich text editor is a composite user interface object that includes a tool
 * bar, a tab folder for entering the rich text content, and a tab folder for
 * viewing and modifying the rich text content in a markup language.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public interface IRichTextEditor extends IRichText {

	/**
	 * Fills the tool bar with rich text action items.
	 * 
	 * @param toolBar
	 *            a rich text editor tool bar
	 */
	public void fillToolBar(IRichTextToolBar toolBar);

	/**
	 * Selects the rich text or the markup language tab.
	 * 
	 * @param index
	 *            <code>0</code> for the rich text tab, <code>1</code> for
	 *            the markup language tab
	 */
	public void setSelection(int index);
	
	/**
	 * Sets the FindReplaceAction to use
	 * @param findReplaceAction
	 */
	public void setFindReplaceAction(FindReplaceAction findReplaceAction);

}
