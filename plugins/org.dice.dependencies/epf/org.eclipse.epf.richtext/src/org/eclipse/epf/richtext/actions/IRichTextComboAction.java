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

import org.eclipse.epf.common.ui.actions.CComboContributionItem;
import org.eclipse.epf.richtext.IRichText;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;

/**
 * The interface for a rich text combo action.
 * 
 * @author Kelvin Low
 * @author Jeff Hardy
 * @since 1.0
 * 
 * @deprecated unused
 */
public interface IRichTextComboAction extends IBaseRichTextAction {

//	/**
//	 * Adds an item.
//	 * 
//	 * @param item
//	 *            the item to be added
//	 */
//	public void addItem(String item);
//
//	/**
//	 * Returns all items.
//	 * 
//	 * @return an array of items
//	 */
//	public String[] getItems();

	/**
	 * Executes the action.
	 * 
	 * @param richText
	 *            a rich text control
	 * @param index
	 *            the index of the selected item
	 */
	public void execute(IRichText richText, int index);

	public IStructuredContentProvider getContentProvider();
	
	public ILabelProvider getLabelProvider();
	
	public Object getInput();
	
	/**
	 * Sets the action's ContributionItem
	 * 
	 */
	public void setContributionItem(CComboContributionItem item);

}
