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
package org.eclipse.epf.library.edit;

import org.eclipse.emf.common.command.Command;

/**
 * This interface is used by commands that want to notify their listeners of
 * certain events.
 * 
 * @author Phong Nguyen Le
 * @author skannoor
 * @since 1.0
 */
public interface ICommandListener {
	/**
	 * Receives notification prior to the execution the given command
	 * @param command
	 */
	public void preExecute(Command command);

	/**
	 * Receives notification that a command has been executed.
	 * 
	 * @param command
	 *            a command
	 */
	public void notifyExecuted(Command command);

	/**
	 * Receives notification prior to the undo of the given command
	 * 
	 * @param command
	 *            an undo command
	 */
	public void preUndo(Command command);

	
	/**
	 * Receives notification after undo
	 */
	
	public void postUndo(Command command);
	/**
	 * Gets the command class.
	 * 
	 * @return a command class.
	 */
	public Class getCommandType();

}
