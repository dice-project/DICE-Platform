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
 * The Interface ILocation.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 */
public interface ILocation {

	/**
	 * Gets the y value.
	 * 
	 * @return the y value of this location
	 */
	int getY();

	/**
	 * Sets the y coordinate of this location.
	 * 
	 * @param y
	 *            the new y coordinate
	 */
	void setY(int y);

	/**
	 * Gets the x value.
	 * 
	 * @return the x value of this location
	 */
	int getX();

	/**
	 * Sets the x coordinate of this location.
	 * 
	 * @param x
	 *            the new x coordinate
	 */
	void setX(int x);
}
