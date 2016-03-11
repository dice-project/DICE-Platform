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

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.epf.authoring.gef.edit.RaisedMarginBorder;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

/**
 * @author Phong Nguyen Le
 * @since 1.0
 * @deprecated
 */
public class CompositeFigure extends Figure {

	private static final Color BG_COLOR = new Color(Display.getCurrent(), 255,
			255, 205);

	private static final int DEFAULT_MAX_WIDTH = 50;

	private static final int SPACING = 10;

	private int maxWidth = DEFAULT_MAX_WIDTH;

	/**
	 * 
	 */
	public CompositeFigure() {
		ToolbarLayout layout = new ToolbarLayout();
		layout.setMinorAlignment(FlowLayout.ALIGN_CENTER);
		layout.setStretchMinorAxis(false);
		layout.setSpacing(SPACING);
		layout.setObserveVisibility(true);
		setLayoutManager(layout);
		setBorder(new RaisedMarginBorder());
		setBackgroundColor(BG_COLOR);
		setOpaque(true);
	}

	public void setMaxWidth(int w) {
		maxWidth = w;
	}

	/**
	 * @see org.eclipse.draw2d.Figure#getPreferredSize(int, int)
	 */
	public Dimension getPreferredSize(int wHint, int hHint) {
		if (wHint > maxWidth) {
			wHint = maxWidth;
		}
		return super.getPreferredSize(wHint, hHint);
	}

}
