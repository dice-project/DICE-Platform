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

import org.eclipse.epf.richtext.IRichText;
import org.eclipse.epf.richtext.RichTextCommand;
import org.eclipse.epf.richtext.RichTextEditor;
import org.eclipse.epf.richtext.RichTextImages;
import org.eclipse.epf.richtext.RichTextResources;
import org.eclipse.jface.action.IAction;
import org.eclipse.swt.custom.StyledText;

/**
 * Cuts the selected text in the rich text control to the clipboard.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class CutAction extends RichTextAction {

	/**
	 * Creates a new instance.
	 */
	public CutAction(IRichText richText) {
		super(richText, IAction.AS_PUSH_BUTTON);
		setImageDescriptor(RichTextImages.IMG_DESC_CUT);
		setDisabledImageDescriptor(RichTextImages.DISABLED_IMG_DESC_CUT);
		setToolTipText(RichTextResources.cutAction_toolTipText); 
	}

	/**
	 * Returns <code>true</code> if this action should be disabled when the
	 * rich text editor is in source edit mode.
	 */
	public boolean disableInSourceMode() {
		return false;
	}

	/**
	 * Executes the action.
	 * 
	 * @param richText
	 *            a rich text control
	 */
	public void execute(IRichText richText) {
		if (richText != null) {
			if (richText instanceof RichTextEditor
					&& ((RichTextEditor) richText).isHTMLTabSelected()) {
				StyledText styledText = ((RichTextEditor) richText)
						.getSourceEdit();
				styledText.cut();
			} else {
				richText.setCopyURL();
				richText.executeCommand(RichTextCommand.CUT);
			}
		}
	}
}
