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
package org.eclipse.epf.richtext.actions;

import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.richtext.IRichText;
import org.eclipse.epf.richtext.RichTextCommand;
import org.eclipse.epf.richtext.RichTextEditor;
import org.eclipse.epf.richtext.RichTextImages;
import org.eclipse.epf.richtext.RichTextResources;
import org.eclipse.jface.action.IAction;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.widgets.Display;

public class PastePlainTextAction extends RichTextAction {
	
	/**
	 * Creates a new instance.
	 */
	public PastePlainTextAction(IRichText richText) {
		super(richText, IAction.AS_PUSH_BUTTON);
		setImageDescriptor(RichTextImages.IMG_DESC_PASTE_PLAIN_TEXT);
		setDisabledImageDescriptor(RichTextImages.DISABLED_IMG_DESC_PASTE_PLAIN_TEXT);
		setToolTipText(RichTextResources.pastePlainTextAction_toolTipText);
		setEnabled(true);
	}
	
	/**
	 * Returns <code>true</code> if this action should be disabled when the
	 * rich text editor is in source edit mode.
	 */
	public boolean disableInSourceMode() {
		return false;
	}
	
	public void execute(IRichText richText) {
		if (richText != null) {
			// get text from clipboard
			Clipboard clipboard = new Clipboard(Display.getCurrent());
			String text = (String) clipboard.getContents(TextTransfer
					.getInstance());
			if (text != null && text.length() > 0) {
				text = StrUtil.convertNewlinesToHTML(text);
				if (richText instanceof RichTextEditor) {
					((RichTextEditor)richText).addHTML(text);
				} else {
					richText.executeCommand(RichTextCommand.ADD_HTML, text);
				}
			}
		}
	}

}
