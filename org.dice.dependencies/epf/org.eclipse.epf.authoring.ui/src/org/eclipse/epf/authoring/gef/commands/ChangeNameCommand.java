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
package org.eclipse.epf.authoring.gef.commands;

import org.eclipse.epf.authoring.gef.edit.NamedNodeEditPart;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.common.ui.util.MsgDialog;
import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.diagram.model.NamedNode;
import org.eclipse.gef.commands.Command;
import org.eclipse.swt.widgets.Display;

/**
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class ChangeNameCommand extends Command {

	private NamedNode element;

	private String newName, oldName;

	private static final String LABEL = AuthoringUIResources.gef_changeNameCommand_label;
	private static final String UNDO_MESSAGE = AuthoringUIResources.gef_changeNameCommand_undoMessage;

	public ChangeNameCommand(NamedNode elem, String newName) {
		super(LABEL);
		element = elem;
		oldName = elem.getName();
		this.newName = newName.trim();
	}

	public boolean canExecute() {
		return element != null;
	}

	public void execute() {
		element.setName(newName);
	}

	public void undo() {
		if (!StrUtil.isBlank(oldName)) {
			element.setName(oldName);
		} else {
			Display.getCurrent().asyncExec(new PromptEdit(null, UNDO_MESSAGE));
		}
	}

	static class PromptEdit implements Runnable {
		
		private String msg;

		PromptEdit(NamedNodeEditPart part, String msg) {
			this.msg = msg;
		}

		/**
		 * @see java.lang.Runnable#run()
		 */
		public void run() {
			String title = AuthoringUIResources.warningDialog_title;
			MsgDialog dialog = AuthoringUIPlugin.getDefault().getMsgDialog();
			dialog.displayError(title, msg, ""); //$NON-NLS-1$
		}

	}

}