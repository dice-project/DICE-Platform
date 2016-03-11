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
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * 
 * @author James Thario
 * @since  1.0
 * @deprecated
 */
public class NoteFigure
        extends TextFigure {
    private static final int CORNER_SIZE = 4;

    public NoteFigure() {
        setBackgroundColor(ColorConstants.tooltipBackground);
        setForegroundColor(ColorConstants.black);

        setBorder(new MarginBorder(CORNER_SIZE));
    }

    protected void paintFigure(Graphics graphics) {
        Rectangle rect = getBounds().getCopy();

        graphics.translate(getLocation());

        // fill the note
        PointList outline = new PointList();

        outline.addPoint(0, 0);
        outline.addPoint(rect.width - CORNER_SIZE, 0);
        outline.addPoint(rect.width - 1, CORNER_SIZE);
        outline.addPoint(rect.width - 1, rect.height - 1);
        outline.addPoint(0, rect.height - 1);

        graphics.fillPolygon(outline);

        // draw the inner outline
        PointList innerLine = new PointList();

        innerLine.addPoint(rect.width - CORNER_SIZE - 1, 0);
        innerLine.addPoint(rect.width - CORNER_SIZE - 1, CORNER_SIZE);
        innerLine.addPoint(rect.width - 1, CORNER_SIZE);
        innerLine.addPoint(rect.width - CORNER_SIZE - 1, 0);
        innerLine.addPoint(0, 0);
        innerLine.addPoint(0, rect.height - 1);
        innerLine.addPoint(rect.width - 1, rect.height - 1);
        innerLine.addPoint(rect.width - 1, CORNER_SIZE);

        graphics.drawPolygon(innerLine);

        graphics.translate(getLocation().getNegated());
    }
}
