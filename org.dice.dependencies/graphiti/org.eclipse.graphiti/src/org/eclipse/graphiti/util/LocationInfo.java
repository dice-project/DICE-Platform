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
package org.eclipse.graphiti.util;

import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.Shape;

/**
 * The Class LocationInfo.
 */
public class LocationInfo implements ILocationInfo {

	private Shape shape;

	private GraphicsAlgorithm graphicsAlgorithm;

	/**
	 * Creates a new {@link LocationInfo} with given shape and graphics
	 * algorithm.
	 * 
	 * @param shape
	 *            the shape
	 * @param graphicsAlgorithm
	 *            the graphics algorithm
	 */
	public LocationInfo(Shape shape, GraphicsAlgorithm graphicsAlgorithm) {
		this.shape = shape;
		this.graphicsAlgorithm = graphicsAlgorithm;
	}

	public Shape getShape() {
		return this.shape;
	}

	public GraphicsAlgorithm getGraphicsAlgorithm() {
		return this.graphicsAlgorithm;
	}
}
