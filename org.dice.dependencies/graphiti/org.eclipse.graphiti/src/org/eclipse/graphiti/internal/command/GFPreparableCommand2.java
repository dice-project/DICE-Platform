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

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class GFPreparableCommand2 extends RecordingCommand {

	private Command command;

	public GFPreparableCommand2(TransactionalEditingDomain editingDomain, Command command) {
		super(editingDomain, command.getDescription());
		this.setCommand(command);
	}

	@Override
	protected void doExecute() {
		getCommand().execute();
	}

	private void setCommand(Command command) {
		this.command = command;
	}

	public Command getCommand() {
		return command;
	}
}
