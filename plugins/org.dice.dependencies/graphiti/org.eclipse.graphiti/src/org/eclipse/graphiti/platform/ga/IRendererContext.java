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
package org.eclipse.graphiti.platform.ga;

import org.eclipse.graphiti.IGraphicsAlgorithmHolder;
import org.eclipse.graphiti.dt.IDiagramTypeProviderHolder;
import org.eclipse.graphiti.features.IMappingProvider;
import org.eclipse.graphiti.mm.algorithms.PlatformGraphicsAlgorithm;

/**
 * The Interface IRendererContext.
 */
public interface IRendererContext extends IDiagramTypeProviderHolder, IGraphicsAlgorithmHolder {

	/**
	 * Gets the mapping provider.
	 * 
	 * @return the mapping provider
	 */
	IMappingProvider getMappingProvider();

	/**
	 * Gets the platform graphics algorithm.
	 * 
	 * @return the platform graphics algorithm
	 */
	PlatformGraphicsAlgorithm getPlatformGraphicsAlgorithm();
}
