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
package org.eclipse.graphiti.func;

import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.context.IUpdateContext;

/**
 * The Interface IUpdate.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 */
public interface IUpdate {

	/**
	 * Checks whether the values of the current pictogram element of the given
	 * context can be updated.
	 * 
	 * @param context
	 *            the context
	 * 
	 * @return true if update is possible
	 */
	boolean canUpdate(IUpdateContext context);

	/**
	 * Check whether the values in the pictogram element are up to date, that
	 * means whether the graphics algorithm of this pictogram element contain
	 * the latest values from the business objects.
	 * 
	 * @param context
	 *            the context
	 * 
	 * @return true if parts of the pictogram model needs to be updated with the
	 *         latest values from the business model
	 */
	IReason updateNeeded(IUpdateContext context);

	/**
	 * Updates the pictogram element. It copies the latest values from the
	 * business model to the graphics algorithm of this pictogram elements.
	 * 
	 * @param context
	 *            the context
	 * 
	 * @return true, if update process was successfull
	 */
	boolean update(IUpdateContext context);
}