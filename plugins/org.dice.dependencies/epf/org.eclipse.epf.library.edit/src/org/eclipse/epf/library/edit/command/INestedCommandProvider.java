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
package org.eclipse.epf.library.edit.command;

import java.util.Collection;

import org.eclipse.emf.common.command.Command;

/**
 * Provides nested commands to execute right after the execution of the main command
 *  
 * @author Phong Nguyen Le
 * @since  1.0
 */
public interface INestedCommandProvider {
	Command createRelatedObjects(Collection<?> createdElements, Command createCommand);
	Command removeRelatedObjects(Collection<?> deletedElements, Command deleteCommand);
	Command createNestedCommand(Command command);
}
