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
package org.eclipse.epf.authoring.ui.actions;

import java.io.IOException;

import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.dialogs.MethodAddImageDialog;
import org.eclipse.epf.authoring.ui.richtext.IMethodRichText;
import org.eclipse.epf.common.utils.NetUtil;
import org.eclipse.epf.library.ui.LibraryUIUtil;
import org.eclipse.epf.richtext.IRichText;
import org.eclipse.epf.richtext.RichTextCommand;
import org.eclipse.epf.richtext.RichTextEditor;
import org.eclipse.epf.richtext.RichTextImages;
import org.eclipse.epf.richtext.RichTextResources;
import org.eclipse.epf.richtext.actions.RichTextAction;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;


/**
 * Displays the Add Image dialog.
 * 
 * @author Kelvin Low
 * @author Jeff Hardy
 * @since 1.0
 */
public class MethodAddImageAction extends RichTextAction {

	/**
	 * Creates a new instance.
	 */
	public MethodAddImageAction(IRichText richText) {
		super(richText, IAction.AS_PUSH_BUTTON);
		setImageDescriptor(RichTextImages.IMG_DESC_ADD_IMAGE);
		setToolTipText(RichTextResources.addImageAction_toolTipText); 
		setEnabled(true);
	}

	/**
	 * Executes the action.
	 * 
	 * @param richText
	 *            A Rich Text control.
	 */
	public void execute(IRichText richText) {
		if (richText != null && richText instanceof IMethodRichText) {
			MethodAddImageDialog dialog = new MethodAddImageDialog(Display
					.getCurrent().getActiveShell(),
					((IMethodRichText) richText).getMethodElement());
			dialog.open();
			if (dialog.getReturnCode() == Window.OK) {
				if (dialog.getFileToCopy() != null) {
					// Copy the image to the resource folder if necessary.
					try {
						String imageLink = NetUtil.decodedFileUrl(LibraryUIUtil.getURLForAttachment(Display
								.getCurrent().getActiveShell(), dialog
								.getFileToCopy(),
								((IMethodRichText) richText)
										.getMethodElement(), true));
						if (imageLink == null) {
							// user hit cancel on RenameFileConflictDialog
							return;
						}
						if (richText instanceof RichTextEditor) {
							((RichTextEditor) richText).setFocus();
							((RichTextEditor) richText).addImage(imageLink, "" + dialog.getHeight(), "" + dialog.getWidth(), dialog.getAltTag()); //$NON-NLS-1$ //$NON-NLS-2$
						} else {
							richText
							.executeCommand(
									RichTextCommand.ADD_IMAGE,
									new String[] {
											imageLink,
											"" + dialog.getHeight(), "" + dialog.getWidth(), dialog.getAltTag() }); //$NON-NLS-1$ //$NON-NLS-2$
						}
					} catch (IOException ex) {
						AuthoringUIPlugin.getDefault().getLogger().logError(ex);
					}
				}
			}
		}
	}
	
	public boolean disableInSourceMode() {
		return false;
	}

}
