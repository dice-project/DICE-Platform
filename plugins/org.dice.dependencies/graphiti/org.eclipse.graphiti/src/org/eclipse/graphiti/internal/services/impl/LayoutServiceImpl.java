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
package org.eclipse.graphiti.internal.services.impl;

import org.eclipse.graphiti.datatypes.IDimension;
import org.eclipse.graphiti.datatypes.ILocation;
import org.eclipse.graphiti.datatypes.IRectangle;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.ILayoutService;
import org.eclipse.graphiti.util.ILocationInfo;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public final class LayoutServiceImpl extends AbstractServiceHolder implements ILayoutService {
	/**
	 * @param ga
	 * @return
	 * @see org.eclipse.graphiti.services.IGaLayoutService#calculateSize(org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithm)
	 */
	public IDimension calculateSize(GraphicsAlgorithm ga) {
		return getGaService().calculateSize(ga);
	}

	/**
	 * @param ga
	 * @param considerLineWidth
	 * @return
	 * @see org.eclipse.graphiti.services.IGaLayoutService#calculateSize(org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithm,
	 *      boolean)
	 */
	public IDimension calculateSize(GraphicsAlgorithm ga, boolean considerLineWidth) {
		return getGaService().calculateSize(ga, considerLineWidth);
	}

	/**
	 * @param c
	 * @param d
	 * @return
	 * @see org.eclipse.graphiti.services.IPeLayoutService#getConnectionMidpoint(org.eclipse.graphiti.mm.pictograms.Connection,
	 *      double)
	 */
	public ILocation getConnectionMidpoint(Connection c, double d) {
		return getPeService().getConnectionMidpoint(c, d);
	}

	/**
	 * @param anchor
	 * @return
	 * @see org.eclipse.graphiti.services.IPeLayoutService#getGaBoundsForAnchor(org.eclipse.graphiti.mm.pictograms.Anchor)
	 */
	public IRectangle getGaBoundsForAnchor(Anchor anchor) {
		return getPeService().getGaBoundsForAnchor(anchor);
	}

	/**
	 * @param shape
	 * @param x
	 * @param y
	 * @return
	 * @see org.eclipse.graphiti.services.IPeLayoutService#getLocationInfo(org.eclipse.graphiti.mm.pictograms.Shape,
	 *      int, int)
	 */
	public ILocationInfo getLocationInfo(Shape shape, int x, int y) {
		return getPeService().getLocationInfo(shape, x, y);
	}

	/**
	 * @param anchor
	 * @return
	 * @see org.eclipse.graphiti.services.IPeLayoutService#getLocationRelativeToDiagram(org.eclipse.graphiti.mm.pictograms.Anchor)
	 */
	public ILocation getLocationRelativeToDiagram(Anchor anchor) {
		return getPeService().getLocationRelativeToDiagram(anchor);
	}

	/**
	 * @param shape
	 * @return
	 * @see org.eclipse.graphiti.services.IPeLayoutService#getLocationRelativeToDiagram(org.eclipse.graphiti.mm.pictograms.Shape)
	 */
	public ILocation getLocationRelativeToDiagram(Shape shape) {
		return getPeService().getLocationRelativeToDiagram(shape);
	}

	/**
	 * @param ga
	 * @param height
	 * @see org.eclipse.graphiti.services.IGaLayoutService#setHeight(org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithm,
	 *      int)
	 */
	public void setHeight(GraphicsAlgorithm ga, int height) {
		getGaService().setHeight(ga, height);
	}

	/**
	 * @param ga
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @see org.eclipse.graphiti.services.IGaLayoutService#setLocationAndSize(org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithm,
	 *      int, int, int, int)
	 */
	public void setLocationAndSize(GraphicsAlgorithm ga, int x, int y, int width, int height) {
		getGaService().setLocationAndSize(ga, x, y, width, height);
	}

	/**
	 * @param ga
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param avoidNegativeCoordinates
	 * @see org.eclipse.graphiti.services.IGaLayoutService#setLocationAndSize(org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithm,
	 *      int, int, int, int, boolean)
	 */
	public void setLocationAndSize(GraphicsAlgorithm ga, int x, int y, int width, int height, boolean avoidNegativeCoordinates) {
		getGaService().setLocationAndSize(ga, x, y, width, height, avoidNegativeCoordinates);
	}

	/**
	 * @param ga
	 * @param x
	 * @param y
	 * @see org.eclipse.graphiti.services.IGaLayoutService#setLocation(org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithm,
	 *      int, int)
	 */
	public void setLocation(GraphicsAlgorithm ga, int x, int y) {
		getGaService().setLocation(ga, x, y);
	}

	/**
	 * @param ga
	 * @param x
	 * @param y
	 * @param avoidNegativeCoordinates
	 * @see org.eclipse.graphiti.services.IGaLayoutService#setLocation(org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithm,
	 *      int, int, boolean)
	 */
	public void setLocation(GraphicsAlgorithm ga, int x, int y, boolean avoidNegativeCoordinates) {
		getGaService().setLocation(ga, x, y, avoidNegativeCoordinates);
	}

	/**
	 * @param ga
	 * @param width
	 * @param height
	 * @see org.eclipse.graphiti.services.IGaLayoutService#setSize(org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithm,
	 *      int, int)
	 */
	public void setSize(GraphicsAlgorithm ga, int width, int height) {
		getGaService().setSize(ga, width, height);
	}

	/**
	 * @param ga
	 * @param width
	 * @see org.eclipse.graphiti.services.IGaLayoutService#setWidth(org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithm,
	 *      int)
	 */
	public void setWidth(GraphicsAlgorithm ga, int width) {
		getGaService().setWidth(ga, width);
	}
}
