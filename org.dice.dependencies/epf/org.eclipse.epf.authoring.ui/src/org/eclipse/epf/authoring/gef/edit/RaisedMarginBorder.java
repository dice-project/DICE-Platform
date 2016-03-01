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
package  org.eclipse.epf.authoring.gef.edit;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 *  A border with a raised margin.
 *  
 * @author Kelvin Low
 * @since 1.0
 */
public class RaisedMarginBorder extends MarginBorder {

	private static final Insets DEFAULT_INSETS = new Insets(10, 10, 10, 10);

	public RaisedMarginBorder() {
		this(DEFAULT_INSETS);
	}

	public RaisedMarginBorder(Insets insets) {
		super(insets);
	}

	public RaisedMarginBorder(int t, int l, int b, int r) {
		super(t, l, b, r);
	}

	/**
	 * @see org.eclipse.draw2d.Border#getInsets(IFigure)
	 */
	public Insets getInsets(IFigure figure) {
		return insets;
	}
	
	/**
	 * @see org.eclipse.draw2d.Border#isOpaque()
	 */	
	public boolean isOpaque() {
		return true;
	}

	/**
	 * @see org.eclipse.draw2d.Border#paint(IFigure, Graphics, Insets)
	 */
	public void paint(IFigure figure, Graphics g, Insets insets) {
		g.setLineStyle(Graphics.LINE_SOLID);
		g.setLineWidth(1);
		g.setForegroundColor(ColorConstants.buttonLightest);
		Rectangle r = getPaintRectangle(figure, insets);
		r.resize(-1, -1);
		g.drawLine(r.x, r.y, r.right(), r.y);
		g.drawLine(r.x, r.y, r.x, r.bottom());
		g.setForegroundColor(ColorConstants.buttonDarker);
		g.drawLine(r.x, r.bottom(), r.right(), r.bottom());
		g.drawLine(r.right(), r.y, r.right(), r.bottom());
	}

}
