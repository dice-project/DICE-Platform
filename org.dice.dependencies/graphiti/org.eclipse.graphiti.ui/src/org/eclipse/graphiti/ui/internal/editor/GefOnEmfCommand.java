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
package org.eclipse.graphiti.ui.internal.editor;

import org.eclipse.gef.commands.Command;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class GefOnEmfCommand extends Command {
	private org.eclipse.emf.common.command.Command emfCommand;

	public GefOnEmfCommand(org.eclipse.emf.common.command.Command emfCommand) {
		super();
		setEmfCommand(emfCommand);
	}

	@Override
	public boolean canExecute() {
		return getEmfCommand().canExecute();
	}

	@Override
	public boolean canUndo() {
		return getEmfCommand().canUndo();
	}

	@Override
	public Command chain(Command command) {
		throw new IllegalArgumentException();
	}

	@Override
	public void dispose() {
		getEmfCommand().dispose();
		super.dispose();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public void execute() {
		getEmfCommand().execute();
	}

	@Override
	public String getLabel() {
		return getEmfCommand().getLabel();
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public void redo() {
		getEmfCommand().redo();
	}

	@Override
	public void setLabel(String label) {
		throw new IllegalArgumentException();
	}

	@Override
	public void undo() {
		getEmfCommand().undo();
	}

	private void setEmfCommand(org.eclipse.emf.common.command.Command emfCommand) {
		this.emfCommand = emfCommand;
	}

	org.eclipse.emf.common.command.Command getEmfCommand() {
		return emfCommand;
	}
}
