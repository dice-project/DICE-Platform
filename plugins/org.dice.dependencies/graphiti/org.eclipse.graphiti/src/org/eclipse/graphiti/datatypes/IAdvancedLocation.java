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
 * The Interface IAdvancedLocation. It provides additional methods for the
 * location data type.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 */
public interface IAdvancedLocation extends ILocation {
	/**
	 * 
	 * @return An exact copy of the current advanced location instance.
	 */
	ILocation getLocationCopy();

	/**
	 * Sets the location.
	 * 
	 * @param x
	 *            the new x coordinate of the location
	 * @param y
	 *            the new y coordinate of the location
	 * @return the current instance
	 */
	ILocation setLocation(int x, int y);

	/**
	 * Sets the location.
	 * 
	 * @param location
	 *            the location which contains the new x and y coordinates
	 * @return the current instance
	 */
	ILocation setLocation(ILocation location);

	/**
	 * Changes the current location.
	 * 
	 * @param amount
	 *            x and y coordinate of the location will be multiplied with
	 *            this amount
	 */
	void scale(double amount);

	/**
	 * Translates the current location.
	 * 
	 * @param dx
	 *            this value will be added to the x coordinate
	 * @param dy
	 *            this value will be added to the y coordinate
	 * @return the current instance
	 */
	ILocation translate(int dx, int dy);
}
