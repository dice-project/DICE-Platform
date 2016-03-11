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

import org.eclipse.graphiti.datatypes.ILocation;
import org.eclipse.graphiti.datatypes.IRectangle;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.util.ILocationInfo;

/**
 * The interface IPeLayoutService provides services (e.g. query location info
 * for a pictogram element) which makes it easier to write layout features.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 */
public interface IPeLayoutService {

	/**
	 * Gets the connection midpoint.
	 * 
	 * @param c
	 *            the connection
	 * @param d
	 *            a relative location on the connection (must be between 0 and
	 *            1)
	 * @return the connection midpoint
	 */
	ILocation getConnectionMidpoint(Connection c, double d);

	/**
	 * Returns the bounds of the graphics algorithm, which is referenced by the
	 * anchor.
	 * 
	 * @param anchor
	 *            the anchor
	 * @return the bounds of the anchor graphics algorithm
	 */
	IRectangle getGaBoundsForAnchor(Anchor anchor);

	/**
	 * Returns the location info for a specific position inside a shape. The
	 * location info contains the shape and the graphics algorithm at the given
	 * position.
	 * 
	 * @param shape
	 *            the shape
	 * @param x
	 *            x coordinate
	 * @param y
	 *            y coordinate
	 * @return the location info
	 * @see ILocationInfo
	 */
	ILocationInfo getLocationInfo(Shape shape, int x, int y);

	/**
	 * Returns the location of the anchor relative to the diagram.
	 * 
	 * @param anchor
	 *            the given anchor
	 * @return the relative location
	 */
	ILocation getLocationRelativeToDiagram(Anchor anchor);

	/**
	 * Returns the location of the shape relative to the diagram.
	 * 
	 * @param shape
	 *            the given shape
	 * @return the relative location
	 */
	ILocation getLocationRelativeToDiagram(Shape shape);

}
