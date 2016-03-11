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

import org.eclipse.graphiti.IDescription;
import org.eclipse.graphiti.features.IFeatureProviderHolder;

/**
 * The Interface ICommand. Defines the general GF Command
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public interface ICommand extends IDescription, IFeatureProviderHolder {

	/**
	 * Can execute.
	 * 
	 * @return true, if successful
	 */
	boolean canExecute();

	/**
	 * Execute.
	 * 
	 * @return true, if successful
	 */
	boolean execute();

	/**
	 * Can undo.
	 * 
	 * @return true, if successful
	 */
	boolean canUndo();

	/**
	 * Undo.
	 * 
	 * @return true, if successful
	 */
	boolean undo();
}
