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
package org.eclipse.epf.authoring.gef.figures;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * A figure for diamond shape.  
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class Diamond extends Shape {

	private PointList diamond = new PointList(4);

	/**
	 * @see Shape#fillShape(Graphics)
	 */
	protected void fillShape(Graphics g) {
		g.fillPolygon(diamond);
	}

	/**
	 * @see Shape#outlineShape(Graphics)
	 */
	protected void outlineShape(Graphics g) {
		g.drawPolygon(diamond);
	}

	/**
	 * @see org.eclipse.draw2d.Figure#validate()
	 */
	public void validate() {
		Rectangle r = getBounds().getCopy();
		r.crop(getInsets());
		r.resize(-1, -1);
		diamond.removeAllPoints();
		diamond.addPoint(r.getTop());
		diamond.addPoint(r.getRight());
		diamond.addPoint(r.getBottom());
		diamond.addPoint(r.getLeft());
	}

	/**
	 * @see org.eclipse.draw2d.Polyline#primTranslate(int, int)
	 */
	public void primTranslate(int x, int y) {
		super.primTranslate(x, y);
		diamond.translate(x, y);
	}

	public PointList getPoints() {
		return diamond;
	}

}
