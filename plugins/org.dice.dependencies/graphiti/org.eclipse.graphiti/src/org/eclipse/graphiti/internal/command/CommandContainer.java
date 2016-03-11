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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.internal.Messages;

/**
 * The Class CommandContainer.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class CommandContainer implements ICommand {

	/**
	 * The commands.
	 */
	List<ICommand> commands = new ArrayList<ICommand>();

	/**
	 * The feature provider.
	 */
	IFeatureProvider featureProvider;

	/**
	 * Instantiates a new command container.
	 * 
	 * @param featureProvider
	 *            the feature provider
	 */
	public CommandContainer(IFeatureProvider featureProvider) {
		super();
		this.featureProvider = featureProvider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.internal.command.ICommand#canExecute()
	 */
	public boolean canExecute() {
		boolean ret = true;
		for (ICommand c : commands) {
			ret = ret && c.canExecute();
			if (ret == false) {
				break;
			}
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.internal.command.ICommand#execute()
	 */
	public boolean execute() {
		if (!canExecute()) {
			return false;
		}

		boolean ret = true;
		for (ICommand c : commands) {
			ret = ret && c.execute();
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.internal.command.ICommand#canUndo()
	 */
	public boolean canUndo() {
		boolean ret = true;
		for (ICommand c : commands) {
			ret = ret && c.canUndo();
			if (ret == false) {
				break;
			}
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.internal.command.ICommand#undo()
	 */
	public boolean undo() {
		if (!canUndo()) {
			return false;
		}

		boolean ret = true;
		for (ICommand c : commands) {
			ret = ret && c.undo();
		}
		return ret;
	}

	/**
	 * Adds the.
	 * 
	 * @param command
	 *            the command
	 */
	public void add(ICommand command) {
		commands.add(command);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.IDescription#getDescription()
	 */
	public String getDescription() {
		final int size = commands.size();
		String ret = ""; //$NON-NLS-1$
		if (size > 0) {
			if (size > 1) {
				ret = size + " " + Messages.CommandContainer_0_xfld; //$NON-NLS-1$
				ret = ret + " ("; //$NON-NLS-1$
				boolean begin = true;
				for (ICommand c : commands) {
					if (!begin) {
						ret = ret + ", "; //$NON-NLS-1$
					}
					ret = ret + c.getDescription();
					begin = false;
				}
				ret = ret + ")"; //$NON-NLS-1$
			} else {
				final ICommand firstCommand = commands.get(0);
				if (firstCommand != null) {
					ret = firstCommand.getDescription();
				}
			}
		}

		return ret;
	}

	/**
	 * Contains commands.
	 * 
	 * @return true, if successful
	 */
	public boolean containsCommands() {
		return commands != null && commands.size() > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.IFeatureProviderHolder#getFeatureProvider()
	 */
	public IFeatureProvider getFeatureProvider() {
		return featureProvider;
	}

	/**
	 * Gets the commands.
	 * 
	 * @return the commands
	 */
	public ICommand[] getCommands() {
		return commands.toArray(new ICommand[0]);
	}
}
