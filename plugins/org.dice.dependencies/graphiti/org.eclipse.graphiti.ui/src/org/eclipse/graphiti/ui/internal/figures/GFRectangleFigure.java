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

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.ui.internal.parts.IPictogramElementDelegate;
import org.eclipse.swt.graphics.Path;

/**
 * A Graphiti Rectangle Figure. Most functionality is handled in the super-class.
 * This class only has to define the outline-path and fill-path for the
 * figure-specific painting.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class GFRectangleFigure extends GFAbstractShape {

	/**
	 * Creates a new GFRectangleFigure.
	 * 
	 * @param pictogramElementDelegate
	 *            The PictogramElementDelegate which provides the
	 *            GraphicsAlgorithm.
	 * @param graphicsAlgorithm
	 *            The GraphicsAlgorithm which provides the values to paint this
	 *            Shape.
	 */
	public GFRectangleFigure(IPictogramElementDelegate pictogramElementDelegate, GraphicsAlgorithm graphicsAlgorithm) {
		super(pictogramElementDelegate, graphicsAlgorithm);
	}

	/**
	 * Returns the Path specifying a rectangle for the given outer bounds. It
	 * does not differenciate between a fill Path and an outline Path.
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
	 * @return The Path specifying a rectangle for the given outer bounds.
	 */
	@Override
	protected Path createPath(Rectangle outerBounds, Graphics graphics, boolean isFill) {
		Path path = new Path(null);
		float x = outerBounds.x;
		float y = outerBounds.y;
		float height = outerBounds.height;
		float width = outerBounds.width;
		path.moveTo(x, y);
		path.addRectangle(x, y, width, height);
		return path;
	}
}
