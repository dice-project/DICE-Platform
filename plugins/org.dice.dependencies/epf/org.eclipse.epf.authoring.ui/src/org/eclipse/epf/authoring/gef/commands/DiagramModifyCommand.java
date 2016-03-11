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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.authoring.gef.util.DiagramUIResources;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.common.ui.util.MsgBox;
import org.eclipse.epf.diagram.model.Diagram;
import org.eclipse.epf.library.edit.ui.UserInteractionHelper;
import org.eclipse.gef.commands.Command;

/**
 * @author Phong Nguyen Le
 * @since 1.0
 */
public abstract class DiagramModifyCommand extends Command {

	private EObject object;

	private boolean canUndo = true;

	public DiagramModifyCommand(String label) {
		super(label);
	}

	protected void setDiagram(Diagram diagram) {
		Object obj = diagram.getObject();
		if (obj instanceof EObject) {
			object = (EObject) obj;
		}
	}

	protected boolean prepareExecute() {
		IStatus status = UserInteractionHelper.checkModify(object, MsgBox.getDefaultShell());
		if (status.isOK()) {
			return true;
		} else {
			AuthoringUIPlugin.getDefault().getMsgDialog()
					.displayWarning(
							DiagramUIResources.errorDialog_title, 
							DiagramUIResources.command_cannotEdit, 
							status);
			return false;
		}
	}

	/**
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		if (prepareExecute()) {
			doExecute();
			canUndo = true;
		} else {
			canUndo = false;
		}
	}

	/**
	 * @see org.eclipse.gef.commands.Command#canUndo()
	 */
	public boolean canUndo() {
		return canUndo;
	}

	protected abstract void doExecute();

}
