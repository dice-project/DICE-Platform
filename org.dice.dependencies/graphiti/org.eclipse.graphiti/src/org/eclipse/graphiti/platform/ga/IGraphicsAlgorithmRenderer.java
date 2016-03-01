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

import org.eclipse.graphiti.dt.IDiagramTypeProvider;

/**
 * The Interface IGraphicsAlgorithmRenderer.
 * 
 * A marker interface enabling the framework to distinguish between customer
 * draw 2d figures and draw 2d figures created by the framework based on the
 * Graphiti model. If a user of Graphiti needs to display custom draw 2d figures
 * these figures need to implement this interface.
 * 
 * @see IGraphicsAlgorithmRendererFactory,
 * @see IDiagramTypeProvider#getGraphicsAlgorithmRendererFactory
 */
public interface IGraphicsAlgorithmRenderer {
}
