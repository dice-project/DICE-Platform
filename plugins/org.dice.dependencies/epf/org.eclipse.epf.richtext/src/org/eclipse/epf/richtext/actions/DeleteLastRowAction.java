/*******************************************************************************
 * Copyright (c) 2013 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.epf.richtext.actions;

import org.eclipse.epf.richtext.IRichText;
import org.eclipse.epf.richtext.RichTextCommand;
import org.eclipse.epf.richtext.actions.RichTextAction;
import org.eclipse.jface.action.IAction;

/**
 * Delete the last row of the selected table.
 * 
 * @author Shi Jin
 */
public class DeleteLastRowAction extends RichTextAction {
	/**
	 * Creates a new instance.
	 */
	public DeleteLastRowAction(IRichText richText) {
		super(richText, IAction.AS_PUSH_BUTTON);
//		setImageDescriptor(RichTextImages.IMG_DESC_ADD_ROW);
//		setDisabledImageDescriptor(RichTextImages.DISABLED_IMG_DESC_ADD_ROW);
//		setToolTipText(RMCRichTextMessages.deleteLastRowAction_toolTipText);
	}

	/**
	 * Executes the action.
	 * 
	 * @param richText
	 *            a rich text control
	 */
	public void execute(IRichText richText) {
		if (richText != null) {
			richText.executeCommand(RichTextCommand.DELETE_LAST_ROW);
		}
	}
}
