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

import java.util.Map;

import org.eclipse.core.commands.operations.IOperationHistory;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.workspace.impl.WorkspaceCommandStackImpl;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class GFWorkspaceCommandStackImpl extends WorkspaceCommandStackImpl {

	private volatile boolean topLevelCommand = true;

	public GFWorkspaceCommandStackImpl(IOperationHistory history) {
		super(history);
	}

	@Override
	public void execute(Command command) {
		super.execute(command);
	}

	@Override
	public void execute(Command command, Map<?, ?> options) throws InterruptedException, RollbackException {
		if (topLevelCommand) {
			topLevelCommand = false;
			try {
				super.execute(command, options);
			} finally {
				topLevelCommand = true;
			}
		} else {
			command.execute();
			if (getMostRecentCommand() != null) {
				getMostRecentCommand().chain(command);
			}
		}
	}
}
