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
/*
 * Created on 06.07.2005
 */
package org.eclipse.graphiti.features;

import org.eclipse.graphiti.IDescription;
import org.eclipse.graphiti.IName;
import org.eclipse.graphiti.features.context.IContext;

/**
 * The Interface IDirectEditingInfo.
 * 
 * Similar to actions and commands. Contains check/can methods and (in the
 * future) methods for undo and redo functionality.
 * 
 * @see org.eclipse.graphiti.features.context.IContext
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 */
public interface IFeature extends IName, IDescription, IFeatureProviderHolder {

	/**
	 * Decides if the current feature is available with the given context.
	 * 
	 * @param context
	 *            this is the general input for this method
	 * 
	 * @return true if it is available, false if not
	 * 
	 * @see IContext
	 */
	boolean isAvailable(IContext context);

	/**
	 * Decides if the current feature can execute with the given context.
	 * 
	 * @param context
	 *            this is the general input for this method
	 * 
	 * @return true if the feature can be executed, false if not
	 * 
	 * @see IContext
	 */
	boolean canExecute(IContext context);

	/**
	 * Executes the current feature with the given context.
	 * 
	 * @param context
	 *            this is the general input for this method
	 * 
	 * @see IContext
	 */
	void execute(IContext context);

	/**
	 * Decides if the current feature can be undone - this is the undo of the
	 * execute operation.
	 * 
	 * @param context
	 *            this is the general input for this method
	 * 
	 * @return true if the feature can be undone, false if not
	 * 
	 * @see IContext
	 */
	boolean canUndo(IContext context);

	/**
	 * Is queried by the framework after a feature has been executed to find out
	 * if this feature should appear in the undo stack. By default all features
	 * should appear there (see implementation in AbstractFeature), but single
	 * features may decide to override this behavior. Note that this is a
	 * dynamic attribute of the feature that is queried each time <b>after</b>
	 * the feature has been executed.
	 * <p>
	 * <b>IMPORTANT NOTE:</b> The implementor of the feature is responsible for
	 * correctly implementing this method! It will lead to inconsistencies if
	 * this method returns <code>false</code> although the feature did changes.
	 * 
	 * 
	 * @return <code>true</code> if the feature should appear in the undo stack,
	 *         <code>false</code> otherwise
	 */
	boolean hasDoneChanges();
}