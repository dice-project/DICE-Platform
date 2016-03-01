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

import org.eclipse.draw2d.AbstractBorder;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * @author Shashidhar Kannoori
 * @since 1.0
 * @deprecated
 */
public class WPFigure extends Figure {

	public Label label = new Label();

	public WPFigure(Label l) {
		super();
		add(l);
	}

	protected void paintFigure(Graphics g) {
		Rectangle clientArea = getClientArea();
		Rectangle bar = clientArea.getCopy();
		bar.crop(new Insets(0, 5, 5, 5));
		g.setBackgroundColor(ColorConstants.gray);
		g.fillRectangle(bar.getTranslated(6, 6));
		g.setBackgroundColor(ColorConstants.yellow);
		g.fillRectangle(bar);
		g.setLineStyle(2);
		g.setLineWidth(1);
		super.paintFigure(g);
	}

	public Label getFigure() {
		return label;
	}

	public void setLabelName(String name) {
		label.setText(name);
	}

	public void setLabel(Label label) {
		this.label = label;
	}

	class Box extends Figure {
		public Box(Insets insets) {
			setBorder(new BoxBorder(insets));
			ToolbarLayout toolbarLayout = new ToolbarLayout();
			toolbarLayout.setStretchMinorAxis(false);
			setLayoutManager(toolbarLayout);
			setBackgroundColor(ColorConstants.red);

		}

		private class BoxBorder extends AbstractBorder {

			public Insets insets = new Insets(1, 0, 0, 0);

			public BoxBorder(Insets insets) {
				this.insets = insets;
			}

			/**
			 * @see org.eclipse.draw2d.Border#getInsets(IFigure)
			 */
			public Insets getInsets(IFigure figure) {
				return insets;
			}

			/**
			 * @see org.eclipse.draw2d.Border#paint(IFigure, Graphics, Insets)
			 */
			public void paint(IFigure figure, Graphics graphics, Insets insets) {
				Rectangle rect = getPaintRectangle(figure, insets);
				graphics.drawLine(rect.getTopLeft(), rect.getTopRight());
			}

		}
	}

}
