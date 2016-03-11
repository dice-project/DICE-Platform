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
package org.eclipse.graphiti.datatypes;

/**
 * An interface for the rectangle data type.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 */
public interface IRectangle extends IDimension, ILocation {

	/**
	 * 
	 * @return An exact copy of the current rectangle instance.
	 */
	public IRectangle getRectangleCopy();

	/**
	 * Sets the location and dimension of the rectangle.
	 * 
	 * @param x
	 *            the x coordinate of the rectangle
	 * @param y
	 *            the y coordinate of the rectangle
	 * @param width
	 *            the width of the rectangle
	 * @param height
	 *            the height of the rectangle
	 */
	public void setRectangle(int x, int y, int width, int height);

	/**
	 * Sets the location and dimension of the rectangle to the values of the
	 * given rectangle.
	 * 
	 * @param rectangle
	 *            The rectangle which contains new location and dimension.
	 */
	public void setRectangle(IRectangle rectangle);

	/**
	 * Checks whether the point with (x,y) is inside the rectangle.
	 * 
	 * @param x
	 *            the x coordinate of the point to be tested
	 * @param y
	 *            the y coordinate of the point to be tested
	 * @return TRUE, if the point with (x,y) is inside the rectangle; FALSE
	 *         otherwise
	 */
	public boolean contains(int x, int y);

	/**
	 * Checks whether the point with the given location is inside the rectangle.
	 * 
	 * @param location
	 *            the location of the point to be tested
	 * @return TRUE, if the location is inside the rectangle; FALSE otherwise
	 */
	public boolean contains(ILocation location);
}
