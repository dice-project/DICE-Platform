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

/**
 * The Interface IGraphicsAlgorithmRendererFactory.
 * 
 * A factory for creating IGraphicsAlgorithmRenderer objects.
 */
public interface IGraphicsAlgorithmRendererFactory {

	/**
	 * Creates a new IGraphicsAlgorithmRenderer object.
	 * 
	 * @param rendererContext
	 *            the renderer context
	 * 
	 * @return the IGraphics algorithm renderer
	 */
	IGraphicsAlgorithmRenderer createGraphicsAlgorithmRenderer(IRendererContext rendererContext);
}
