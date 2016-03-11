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
package org.eclipse.epf.library.edit.process.command;

import org.eclipse.emf.common.command.AbstractCommand;

/**
 * Command to synchronize descriptor's attributes and own references
 * 
 * @author Phong Nguyen Le - Feb 1, 2006
 * @since  1.0
 */
public class DescriptorSynchronizeCommand extends AbstractCommand {

	/**
	 * 
	 */
	public DescriptorSynchronizeCommand() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param label
	 */
	public DescriptorSynchronizeCommand(String label) {
		super(label);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param label
	 * @param description
	 */
	public DescriptorSynchronizeCommand(String label, String description) {
		super(label, description);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.command.Command#execute()
	 */
	public void execute() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.command.Command#redo()
	 */
	public void redo() {
		// TODO Auto-generated method stub

	}

}
