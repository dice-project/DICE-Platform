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
import org.eclipse.epf.richtext.RichTextImages;
import org.eclipse.epf.richtext.RichTextResources;
import org.eclipse.epf.richtext.dialogs.AddTableDialog;
import org.eclipse.epf.richtext.html.Table;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;

/**
 * Adds a table to a rich text control.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class AddTableAction extends RichTextAction {

	/**
	 * Creates a new instance.
	 */
	public AddTableAction(IRichText richText) {
		super(richText, IAction.AS_PUSH_BUTTON);
		setImageDescriptor(RichTextImages.IMG_DESC_ADD_TABLE);
		setDisabledImageDescriptor(RichTextImages.DISABLED_IMG_DESC_ADD_TABLE);
		setToolTipText(RichTextResources.addTableAction_toolTipText);
	}

	/**
	 * Executes the action.
	 * 
	 * @param richText
	 *            a rich text control
	 */
	public void execute(IRichText richText) {
		if (richText != null) {
			AddTableDialog dialog = new AddTableDialog(Display.getCurrent()
					.getActiveShell());
			dialog.open();
			if (dialog.getReturnCode() == Window.OK) {
				Table table = dialog.getTable();
				int rows = table.getRows();
				int cols = table.getColumns();
				String width = table.getWidth();
				int tableheaders = table.getTableHeaders();
				String summary = table.getSummary();
				String caption = table.getCaption();
				if (rows > 0 && cols > 0) {
					richText
							.executeCommand(
									RichTextCommand.ADD_TABLE,
									new String[] {
											"" + rows, "" + cols, "" + width, summary, caption, "" + tableheaders }); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				}
			}
		}
	}

}