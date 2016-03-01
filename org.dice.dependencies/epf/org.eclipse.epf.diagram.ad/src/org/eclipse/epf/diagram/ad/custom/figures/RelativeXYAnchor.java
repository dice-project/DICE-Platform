/*
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial implementation
 *
 */
package org.eclipse.epf.diagram.ad.custom.figures;

import org.eclipse.draw2d.AbstractConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * Provides relative anchor for a figure's location. 
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class RelativeXYAnchor extends AbstractConnectionAnchor {

	private Point relativeLocation;

	public RelativeXYAnchor(IFigure figure, Point location, boolean isRelative) {
		super(figure);
		relativeLocation = location.getCopy();
	}

	/**
	 * @see org.eclipse.draw2d.ConnectionAnchor#getLocation(Point)
	 */
	public Point getLocation(Point reference) {
		Point p = relativeLocation.getCopy();
		Rectangle r = getOwner().getBounds().getCopy();
		p.performTranslate(r.x, r.y);
		getOwner().translateToAbsolute(p);
		return p;
	}

	/**
	 * @see org.eclipse.draw2d.AbstractConnectionAnchor#getReferencePoint()
	 */
	public Point getReferencePoint() {
		return getLocation(null);
	}

}
