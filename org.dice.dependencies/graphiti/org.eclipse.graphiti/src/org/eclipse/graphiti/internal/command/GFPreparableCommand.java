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
package org.eclipse.graphiti.internal.command;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class GFPreparableCommand extends RecordingCommand {

	private ICommand command;
	private boolean executionResult;

	public GFPreparableCommand(TransactionalEditingDomain editingDomain, ICommand command) {
		super(editingDomain, command.getDescription());
		this.setCommand(command);
	}

	@Override
	protected void doExecute() {
		setExecutionResult(getCommand().execute());
	}

	private void setExecutionResult(boolean executionResult) {
		this.executionResult = executionResult;
	}

	public boolean getExecutionResult() {
		return executionResult;
	}

	private void setCommand(ICommand command) {
		this.command = command;
	}

	private ICommand getCommand() {
		return command;
	}
}
