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

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.epf.richtext.IRichText;
import org.eclipse.epf.richtext.RichTextCommand;
import org.eclipse.epf.richtext.RichTextResources;
import org.eclipse.epf.richtext.html.BlockTag;

/**
 * Sets the font name for the selected text in a rich text control.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class BlockTagAction extends RichTextComboAction {

	/**
	 * Creates a new instance.
	 */
	public BlockTagAction(final IRichText richText) {
		super(richText);
		setToolTipText(RichTextResources.blockTagAction_toolTipText); 
	}
		
	@Override
	public Collection<String> getInput() {
		Collection<String> returnList = new ArrayList<String>();
		
		returnList.add(BlockTag.PARAGRAPH.getName());
		returnList.add(BlockTag.HEADING_1.getName());
		returnList.add(BlockTag.HEADING_2.getName());
		returnList.add(BlockTag.HEADING_3.getName());
		returnList.add(BlockTag.HEADING_4.getName());
		returnList.add(BlockTag.HEADING_5.getName());
		returnList.add(BlockTag.HEADING_6.getName());
		returnList.add(BlockTag.ADDRESS.getName());
		returnList.add(BlockTag.PREFORMATTED_TEXT.getName());

		return returnList;
	}
	
	

	/**
	 * Executes the action.
	 * 
	 * @param richText
	 *            a rich text control
	 * @param index
	 *            the index of the selected item
	 */
	public void execute(IRichText richText) {
		if (richText != null) {
			String selected = getCComboSelection();
			richText.executeCommand(RichTextCommand.FORMAT_BLOCK, selected);
		}
	}

}