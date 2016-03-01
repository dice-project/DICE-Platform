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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.CompoundCommand;

/**
 * A compound command that is resource aware.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class ResourceAwareCompoundCommand extends CompoundCommand implements
		IResourceAwareCommand {

	public ResourceAwareCompoundCommand() {
		super();
	}

	/**
	 * @param label
	 */
	public ResourceAwareCompoundCommand(String label) {
		super(label);
	}

	/**
	 * @param label
	 * @param description
	 */
	public ResourceAwareCompoundCommand(String label, String description) {
		super(label, description);
	}

	/**
	 * @param commandList
	 */
	public ResourceAwareCompoundCommand(List commandList) {
		super(commandList);
	}

	/**
	 * @param label
	 * @param commandList
	 */
	public ResourceAwareCompoundCommand(String label, List commandList) {
		super(label, commandList);
	}

	/**
	 * @param label
	 * @param description
	 * @param commandList
	 */
	public ResourceAwareCompoundCommand(String label, String description,
			List commandList) {
		super(label, description, commandList);
	}

	/**
	 * @param resultIndex
	 */
	public ResourceAwareCompoundCommand(int resultIndex) {
		super(resultIndex);
	}

	/**
	 * @param resultIndex
	 * @param label
	 */
	public ResourceAwareCompoundCommand(int resultIndex, String label) {
		super(resultIndex, label);
	}

	/**
	 * @param resultIndex
	 * @param label
	 * @param description
	 */
	public ResourceAwareCompoundCommand(int resultIndex, String label,
			String description) {
		super(resultIndex, label, description);
	}

	/**
	 * @param resultIndex
	 * @param commandList
	 */
	public ResourceAwareCompoundCommand(int resultIndex, List commandList) {
		super(resultIndex, commandList);
	}

	/**
	 * @param resultIndex
	 * @param label
	 * @param commandList
	 */
	public ResourceAwareCompoundCommand(int resultIndex, String label,
			List commandList) {
		super(resultIndex, label, commandList);
	}

	/**
	 * @param resultIndex
	 * @param label
	 * @param description
	 * @param commandList
	 */
	public ResourceAwareCompoundCommand(int resultIndex, String label,
			String description, List commandList) {
		super(resultIndex, label, description, commandList);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.command.IResourceAwareCommand#getModifiedResources()
	 */
	public Collection getModifiedResources() {
		Collection modifiedResources = new HashSet();
		for (Iterator iter = commandList.iterator(); iter.hasNext();) {
			Object cmd = iter.next();
			if (cmd instanceof IResourceAwareCommand) {
				modifiedResources.addAll(((IResourceAwareCommand) cmd)
						.getModifiedResources());
			}
		}
		return modifiedResources;
	}

}
