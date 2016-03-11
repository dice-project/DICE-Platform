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

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * A customized Label for selection. Primary selection is denoted by highlight
 * and focus rectangle. Normal selection is denoted by highlight only.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class SelectableLabel extends Label {

	private boolean selected;

	private boolean hasFocus;

	private Rectangle getSelectionRectangle() {
		Rectangle bounds = getTextBounds();
		bounds.expand(new Insets(2, 2, 0, 0));
		translateToParent(bounds);
		bounds.intersect(getBounds());
		return bounds;
	}

	/**
	 * @see org.eclipse.draw2d.Label#paintFigure(Graphics)
	 */
	protected void paintFigure(Graphics graphics) {
		if (selected) {
			graphics.pushState();
			graphics.setBackgroundColor(ColorConstants.menuBackgroundSelected);
			graphics.fillRectangle(getSelectionRectangle());
			graphics.popState();
			graphics.setForegroundColor(ColorConstants.white);
		}
		if (hasFocus) {
			graphics.pushState();
			graphics.setXORMode(true);
			graphics.setForegroundColor(ColorConstants.menuBackgroundSelected);
			graphics.setBackgroundColor(ColorConstants.white);
			graphics.drawFocus(getSelectionRectangle().resize(-1, -1));
			graphics.popState();
		}
		super.paintFigure(graphics);
	}

	/**
	 * Sets the selection state of this SimpleActivityLabel
	 * 
	 * @param b
	 *            true will cause the label to appear selected
	 */
	public void setSelected(boolean b) {
		selected = b;
		repaint();
	}

	/**
	 * Sets the focus state of this SimpleActivityLabel
	 * 
	 * @param b
	 *            true will cause a focus rectangle to be drawn around the text
	 *            of the Label
	 */
	public void setFocus(boolean b) {
		hasFocus = b;
		repaint();
	}

}
