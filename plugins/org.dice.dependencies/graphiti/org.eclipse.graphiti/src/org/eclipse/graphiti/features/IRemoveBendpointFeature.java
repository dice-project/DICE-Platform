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
package org.eclipse.graphiti.features;

import org.eclipse.graphiti.features.context.IRemoveBendpointContext;
import org.eclipse.graphiti.features.impl.DefaultRemoveBendpointFeature;

/**
 * The Interface IRemoveBendpointFeature.
 * 
 * @noextend This interface is not intended to be extended by clients.
 * @noimplement This interface is not intended to be implemented by clients,
 *              extend {@link DefaultRemoveBendpointFeature} instead.
 */
public interface IRemoveBendpointFeature extends IFeature {

	/**
	 * Can remove bendpoint.
	 * 
	 * @param context
	 *            the context
	 * 
	 * @return true, if successful
	 */
	boolean canRemoveBendpoint(IRemoveBendpointContext context);

	/**
	 * Removes the bendpoint.
	 * 
	 * @param context
	 *            the context
	 */
	void removeBendpoint(IRemoveBendpointContext context);
}
