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

import java.io.File;
import java.io.IOException;

import org.eclipse.core.runtime.Platform;
import org.eclipse.epf.authoring.ui.dialogs.MethodAddLinkDialog;
import org.eclipse.epf.authoring.ui.richtext.IMethodRichText;
import org.eclipse.epf.common.utils.NetUtil;
import org.eclipse.epf.library.ui.LibraryUIUtil;
import org.eclipse.epf.richtext.IRichText;
import org.eclipse.epf.richtext.RichTextCommand;
import org.eclipse.epf.richtext.RichTextEditor;
import org.eclipse.epf.richtext.actions.AddLinkAction;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;

/**
 * Adds a link to a Rich Text editor.
 * 
 * @author Jeff Hardy
 * @author Kelvin Low
 * @since 1.0
 */
public class MethodAddLinkAction extends AddLinkAction {

	/**
	 * Creates a new instance.
	 */
	public MethodAddLinkAction(IRichText richText) {
		super(richText);
	}

	/**
	 * Executes the action.
	 * 
	 * @param richText
	 *            A Rich Text control.
	 */
	public void execute(IRichText richText) {
		if (richText != null) {
			if (richText instanceof IMethodRichText) {
				MethodAddLinkDialog dialog = new MethodAddLinkDialog(Display
						.getCurrent().getActiveShell(),
						(IMethodRichText) richText);
				dialog.open();
				if (dialog.getReturnCode() == Window.OK) {
					String linkURL = dialog.getLink().getURL();
					if (linkURL.length() > 0) {
						if (dialog.getFileToCopy() != null) {
							// link to a file, create the URL
							try {
								linkURL = LibraryUIUtil.getURLForAttachment(Display
										.getCurrent().getActiveShell(), dialog
										.getFileToCopy(),
										((IMethodRichText) richText)
												.getMethodElement(), true);
								if (linkURL == null) {
									// user hit cancel on RenameFileConflictDialog
									return;
								}
								String url = "<a href=\""; //$NON-NLS-1$
								if (Platform.getOS().equals(Platform.WS_WIN32)) {
									url += NetUtil
											.encodeFileURL(NetUtil.FILE_URI_PREFIX
													+ linkURL);
								} else {
									url += NetUtil.encodeFileURL("./" + linkURL); //$NON-NLS-1$
								}
								url += "\"" + (dialog.getOpenLinkInNewWindow() ? " " + //$NON-NLS-1$ //$NON-NLS-2$
										MethodAddLinkDialog.OPEN_LINK_IN_NEW_WINDOW_ATTRIBUTE : "") +  //$NON-NLS-1$
										">" + new File(linkURL).getName() + "</a>"; //$NON-NLS-1$ //$NON-NLS-2$
								linkURL = url;

							} catch (IOException ex) {
								ex.printStackTrace();
							}
						}
						if (richText instanceof RichTextEditor) {
							((RichTextEditor) richText).addHTML(linkURL);
						} else {
							richText.executeCommand(RichTextCommand.ADD_HTML,
									linkURL);
						}
					}
				}
			} else {
				super.execute(richText);
			}
		}
	}

}
