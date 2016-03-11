/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2010 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.command;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.graphiti.internal.command.ICommand;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class GefCommandWrapper extends Command {
	private ICommand command;

	public GefCommandWrapper(ICommand command, TransactionalEditingDomain editingDomain) {
		super();
		setCommand(command);
	}

	@Override
	public boolean canExecute() {
		return getCommand().canExecute() && super.canExecute();
	}

	@Override
	public boolean canUndo() {
		ICommand c = getCommand();
		boolean commandCanUndo = c.canUndo();
		boolean superCanUndo = super.canUndo();
		return commandCanUndo && superCanUndo;
	}

	@Override
	public void execute() {
		ICommand c = getCommand();
		//CommandExec.getSingleton().executeCommand(c, editingDomain);
		c.execute();
		super.execute();
	}

	@Override
	public String getLabel() {
		String ret = null;
		final ICommand c = getCommand();
		if (c != null) {
			ret = c.getDescription();
		}
		if (ret != null) {
			return ret;
		}
		return super.getLabel();
	}

	@Override
	public void undo() {
		getCommand().undo();
		super.undo();
	}

	public ICommand getCommand() {
		return command;
	}

	private void setCommand(ICommand command) {
		this.command = command;
	}

	@Override
	public void redo() {
		super.redo();
	}
}
