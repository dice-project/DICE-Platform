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
package org.eclipse.graphiti.services;

import org.eclipse.graphiti.datatypes.IDimension;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;

/**
 * The interface IGaLayoutService provides convenient services for the
 * calculation and change of the size and/or location of graphics algorithm.
 * This makes it easier to write layout features.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 */
public interface IGaLayoutService {

	/**
	 * Calculates the size of the given graphics algorithm. If the graphics
	 * algorithm is a polyline or polygon then the size will be calculated.
	 * Otherwise the size of the graphics algorithm is simply returned.
	 * 
	 * @param ga
	 *            graphics algorithm
	 * @return the dimension of the given graphics algorithm
	 */
	public IDimension calculateSize(GraphicsAlgorithm ga);

	/**
	 * Calculates the size of the given graphics algorithm. If the graphics
	 * algorithm is a polyline or polygon then the size will be calculated.
	 * Otherwise the size of the graphics algorithm is simply returned.
	 * 
	 * @param ga
	 *            graphics algorithm
	 * @param considerLineWidth
	 *            if TRUE, the line width will be considered in the dimension
	 * @return the dimension of the given graphics algorithm
	 */
	public IDimension calculateSize(GraphicsAlgorithm ga, boolean considerLineWidth);

	/**
	 * Sets the height of the given graphics algorithm.
	 * 
	 * @param ga
	 *            graphics algorithm
	 * @param height
	 *            the new height
	 */
	public void setHeight(GraphicsAlgorithm ga, int height);

	/**
	 * Sets location and size of the given graphics algorithm.
	 * 
	 * @param ga
	 *            graphics algorithm
	 * @param x
	 *            the new x coordinate
	 * @param y
	 *            the new y coordinate
	 * @param width
	 *            the new width
	 * @param height
	 *            the new height
	 */
	public void setLocationAndSize(GraphicsAlgorithm ga, int x, int y, int width, int height);

	/**
	 * Sets location and size of the given graphics algorithm.
	 * 
	 * @param ga
	 *            graphics algorithm
	 * @param x
	 *            the new x coordinate
	 * @param y
	 *            the new y coordinate
	 * @param width
	 *            the new width
	 * @param height
	 *            the new height
	 * @param avoidNegativeCoordinates
	 *            if TRUE, a negative coordinate will automatically set to 0.
	 */
	public void setLocationAndSize(GraphicsAlgorithm ga, int x, int y, int width, int height, boolean avoidNegativeCoordinates);

	/**
	 * Sets the location of the given graphics algorithm.
	 * 
	 * @param ga
	 *            graphics algorithm
	 * @param x
	 *            the new x coordinate
	 * @param y
	 *            the new y coordinate
	 */
	public void setLocation(GraphicsAlgorithm ga, int x, int y);

	/**
	 * Sets the location of the given graphics algorithm.
	 * 
	 * @param ga
	 *            graphics algorithm
	 * @param x
	 *            the new x coordinate
	 * @param y
	 *            the new y coordinate
	 * @param avoidNegativeCoordinates
	 *            if TRUE, a negative coordinate will automatically set to 0.
	 */
	public void setLocation(GraphicsAlgorithm ga, int x, int y, boolean avoidNegativeCoordinates);

	/**
	 * Sets the size of the given graphics algorithm.
	 * 
	 * @param ga
	 *            graphics algorithm
	 * @param width
	 *            the new width
	 * @param height
	 *            the new height
	 */
	public void setSize(GraphicsAlgorithm ga, int width, int height);

	/**
	 * Sets the width of the given graphics algorithm.
	 * 
	 * @param ga
	 *            graphics algorithm
	 * @param width
	 *            the new width
	 */
	public void setWidth(GraphicsAlgorithm ga, int width);

}
