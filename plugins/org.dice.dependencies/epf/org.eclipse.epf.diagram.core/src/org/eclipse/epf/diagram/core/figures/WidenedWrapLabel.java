//------------------------------------------------------------------------------
// Copyright (c) 2005, 2007 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
//------------------------------------------------------------------------------
//Copyright (c) 2005, 2007 IBM Corporation and others.
//All rights reserved. This program and the accompanying materials
//are made available under the terms of the Eclipse Public License v1.0
//which accompanies this distribution, and is available at
//http://www.eclipse.org/legal/epl-v10.html
//
//Contributors:
//IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.diagram.core.figures;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel;

/**
 * Temporary replacement for {@link WrapLabel} until the text cut-off issue is fixed.
 * 
 * @author Phong Nguyen Le
 * @since 1.2
 */
public class WidenedWrapLabel extends WrapLabel {
	public static final float DELTA_FACTOR = 0.05f;
	
	private Dimension textSize;
	private Dimension adjustedTextSize;
	
	public WidenedWrapLabel() {
		super();
	}
	
	
	@Override
	public Dimension getTextSize(int wHint, int hHint) {
		Dimension newTextSize = super.getTextSize(wHint, hHint);
		if(newTextSize != null && newTextSize != textSize) {
			textSize = newTextSize;
			
			// work-around: increase the text size to avoid text cut-off
			//
			adjustedTextSize = textSize.getExpanded((int) Math.ceil(textSize.width * DELTA_FACTOR), 4);
		}
		return adjustedTextSize;
	}
	
	@Override
	protected Point getTextLocation() {
		Point p = super.getTextLocation();
		Rectangle r = getBounds();
		Dimension ts = getTextSize(r.width, r.height);
		if(r.width <= ts.width) {
			p.x = 0;
			if(!getText().equalsIgnoreCase(getSubStringText())) {
				invalidate();
			}
		}
		return p;
	}
}
