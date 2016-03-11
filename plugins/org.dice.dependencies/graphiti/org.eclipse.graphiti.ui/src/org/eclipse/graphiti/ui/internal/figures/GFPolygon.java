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
package org.eclipse.graphiti.ui.internal.figures;

import java.util.List;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.ui.internal.parts.IPictogramElementDelegate;
import org.eclipse.swt.graphics.Path;

/**
 * A Graphiti Polygon Figure. Most functionality is handled in the super-class. This
 * class only has to define the outline-path and fill-path for the
 * figure-specific painting.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class GFPolygon extends GFPolyline {

	/**
	 * Creates a new GFPolygon.
	 * 
	 * @param pictogramElementDelegate
	 *            The PictogramElementDelegate which provides the
	 *            GraphicsAlgorithm.
	 * @param graphicsAlgorithm
	 *            The GraphicsAlgorithm which provides the values to paint this
	 *            Shape.
	 */
	public GFPolygon(IPictogramElementDelegate pictogramElementDelegate, GraphicsAlgorithm graphicsAlgorithm) {
		super(pictogramElementDelegate, graphicsAlgorithm);

		// reset to the default-value, because the super-class sets it to true
		setSuppressFilling(false);
	}

	// =================== overwritten functional methods =====================

	/**
	 * Returns true, if the given point is contained in this Polygon. This is
	 * the case if
	 * <ul>
	 * <li>The point is located in the Polygon</li>
	 * <li>The point is contained in a child of this Shape</li>
	 * </ul>
	 * 
	 * @param x
	 *            The x-coordinate of the point to check.
	 * @param y
	 *            The y-coordinate of the point to check.
	 * 
	 * @return true, if the given point is contained in this Polygon.
	 */
	@Override
	public boolean containsPointInFigure(int x, int y) {
		Boolean inFigure = GFFigureUtil.containsPointInPolygon(getPoints(), x, y);
		if (inFigure != null) // clear result, no need for further checking
			return inFigure.booleanValue();

		// check if point inside children (if existing)
		List<?> children = getChildren();
		for (int i = 0; i < children.size(); i++) {
			if (((IFigure) children.get(i)).containsPoint(x, y)) {
				return true;
			}
		}

		return false;
	}

	// ==================== overwritten drawing methods =======================

	/**
	 * Returns the Path specifying a polygon for the adjusted point-list (see
	 * {@link #getAdjustedPointList(PointList, double, double)}) of this Shape.
	 * Note, that the outer bounds are ignored to calculate the Path, in
	 * contrast to most other Shapes. It does not differenciate between a fill
	 * Path and an outline Path.
	 * 
	 * @param outerBounds
	 *            The outer bounds which shall contain the Path. They are
	 *            calculated from the bounds of this figure by
	 *            {@link GFFigureUtil#getAdjustedRectangle(Rectangle, double, int)}
	 *            . Note, that those outline-bounds are just a suggestion which
	 *            works fine for many cases.
	 * @param graphics
	 *            The Graphics on which the outline Path shall be painted. It
	 *            can be used to react on Graphics specific values, like the
	 *            zoom-level of the Graphics.
	 * @param isFill
	 *            if true, the Path is used for filling the Shape, otherwise for
	 *            outlining the Shape.
	 * 
	 * @return The Path specifying a polygon for the point-list of this Shape.
	 */
	@Override
	protected Path createPath(Rectangle outerBoundss, Graphics graphics, boolean isFill) {
		// instead of just zooming the translated-points (see
		// getTranslatedPoints()),
		// better do the calculation again by first zooming and then translating
		// to avoid rounding errors.
		double zoom = getZoomLevel(graphics);
		double lw = getLineWidth(graphics);
		PointList points = getAdjustedPointList(getPoints(), zoom, lw);

		List<BezierPoint> bezierPoints = getBezierPoints(points, zoom);
		boolean isClosed = true; // polygons are always closed

		Path path = GFFigureUtil.getBezierPath(bezierPoints, isClosed);
		return path;
	}
}