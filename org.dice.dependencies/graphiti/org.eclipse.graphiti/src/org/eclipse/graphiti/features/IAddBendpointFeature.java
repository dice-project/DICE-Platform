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

import org.eclipse.graphiti.features.context.IAddBendpointContext;
import org.eclipse.graphiti.features.impl.DefaultAddBendpointFeature;

/**
 * The Interface IAddBendpointFeature. Feature, that can add a bendpoint to a
 * {@link org.eclipse.graphiti.mm.pictograms.FreeFormConnection}.
 * 
 * @noextend This interface is not intended to be extended by clients.
 * @noimplement This interface is not intended to be implemented by clients,
 *              extend {@link DefaultAddBendpointFeature} instead.
 */
public interface IAddBendpointFeature extends IFeature {

	/**
	 * Checks if bendpoint can be added.
	 * 
	 * @param context
	 *            the context
	 * 
	 * @return true, if can add bendpoint
	 */
	boolean canAddBendpoint(IAddBendpointContext context);

	/**
	 * Adds a bendpoint.
	 * 
	 * @param context
	 *            the context
	 */
	void addBendpoint(IAddBendpointContext context);
}
