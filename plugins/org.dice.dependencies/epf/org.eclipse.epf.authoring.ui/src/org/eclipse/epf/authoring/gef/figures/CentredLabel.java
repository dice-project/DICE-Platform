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
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Image;

/**
 * @author Shashidhar Kannoori
 * @since 1.0
 * @deprecated
 */
public class CentredLabel extends Label {

	public CentredLabel() {
		super();
	}

	public CentredLabel(String s) {
		super(s);
	}

	public CentredLabel(Image i) {
		super(i);
	}

	public CentredLabel(String s, Image i) {
		super(s, i);
	}

	/**
	 * @see org.eclipse.draw2d.Figure#paintFigure(Graphics)
	 */
	protected void paintFigure(Graphics graphics) {
		if (isOpaque())
			super.paintFigure(graphics);
		Rectangle bounds = getBounds();
		graphics.setForegroundColor(ColorConstants.yellow);
		graphics.translate(bounds.x, bounds.y);
		if (!isEnabled()) {
			graphics.translate(1, 1);
			graphics.setBackgroundColor(ColorConstants.yellow);
			graphics.setForegroundColor(ColorConstants.buttonLightest);
			graphics.drawText(getSubStringText(), getTextLocation());
			graphics.translate(-1, -1);
			graphics.setForegroundColor(ColorConstants.buttonDarker);
		}
		graphics.drawText(getSubStringText(), getTextLocation());
		graphics.setBackgroundColor(ColorConstants.yellow);
		graphics.translate(-bounds.x, -bounds.y);
		graphics.setBackgroundColor(ColorConstants.blue);
	}

}
