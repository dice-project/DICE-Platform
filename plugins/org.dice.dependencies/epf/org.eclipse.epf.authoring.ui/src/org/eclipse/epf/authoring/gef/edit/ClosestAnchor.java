//------------------------------------------------------------------------------
// Copyright (c) 2005, 2006 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.authoring.gef.edit;

import org.eclipse.draw2d.AbstractConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.epf.authoring.gef.util.AnchorUtil;

/**
 * Provides support to find the closest location in figure
 * for anchor. 
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class ClosestAnchor extends AbstractConnectionAnchor {

	public ClosestAnchor(IFigure figure) {
		super(figure);
	}

	/**
	 * @see org.eclipse.draw2d.ConnectionAnchor#getLocation(Point)
	 */
	public Point getLocation(Point reference) {
		return getLocation(getOwner(), reference);
	}

	/**
	 * Finds the location in the closest location in figure, based on 
	 * figure bounds and the reference point.   
	 * 
	 * @param figure {@link IFigure}  Figure to which connection is establishing.
	 * @param reference {@link Point}
	 */
	public static Point getLocation(IFigure figure, Point reference) {
		Rectangle r = figure.getBounds().getCopy();
		figure.translateToAbsolute(r);
		Rectangle absoluteBounds = r.getCopy();
		Point p = null;
		switch (r.getPosition(reference)) {
		case PositionConstants.NORTH:
			p = r.getTopLeft().translate(reference.x - r.x, 0);
			break;
		case PositionConstants.NORTH_EAST:
			p = r.getTopRight();
			break;
		case PositionConstants.EAST:
			p = r.getTopRight().translate(0, reference.y - r.y);
			break;
		case PositionConstants.SOUTH_EAST:
			p = r.getBottomRight();
			break;
		case PositionConstants.SOUTH:
			p = r.getBottomLeft().translate(reference.x - r.x, 0);
			break;
		case PositionConstants.SOUTH_WEST:
			p = r.getBottomLeft();
			break;
		case PositionConstants.WEST:
			p = r.getTopLeft().translate(0, reference.y - r.y);
			break;
		case PositionConstants.NORTH_WEST:
			p = r.getTopLeft();
			break;
		default:
			int topDistance = reference.y - r.y;
			int rightDistance = r.right() - reference.x;
			int bottomDistance = r.bottom() - reference.y;
			int leftDistance = reference.x - r.x;
			int[] distances = { topDistance, rightDistance, bottomDistance,
					leftDistance };
			int id = AnchorUtil.min(distances);
			switch (id) {
			case 0:
				p = r.getTopLeft().translate(leftDistance, 0);
				break;
			case 1:
				p = r.getTopRight().translate(0, topDistance);
				break;
			case 2:
				p = r.getBottomLeft().translate(leftDistance, 0);
				break;
			case 4:
				p = r.getTopLeft().translate(topDistance, 0);
				break;
			}
		}
		if (p != null) {
			p.performTranslate(-absoluteBounds.x, -absoluteBounds.y);
		}
		return p;
	}

}
