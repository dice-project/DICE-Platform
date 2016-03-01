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

import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class EndNodeFigure extends Ellipse {

	/**
	 * Fills the ellipse.
	 * 
	 * @see org.eclipse.draw2d.Shape#fillShape(org.eclipse.draw2d.Graphics)
	 */
	protected void fillShape(Graphics graphics) {
		Rectangle r = getBounds().getCopy();
		r.shrink(r.width / 8, r.height / 8);
		graphics.fillOval(r);
	}

	/**
	 * Outlines the ellipse.
	 * 
	 * @see org.eclipse.draw2d.Shape#outlineShape(org.eclipse.draw2d.Graphics)
	 */
	protected void outlineShape(Graphics graphics) {
		super.outlineShape(graphics);

		Rectangle r = Rectangle.SINGLETON;
		r.setBounds(getBounds());
		r.shrink(getBounds().width / 8, getBounds().height / 8);
		r.width--;
		r.height--;
		r.shrink((lineWidth - 1) / 2, (lineWidth - 1) / 2);
		graphics.drawOval(r);
	}

}
