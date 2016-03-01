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
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.epf.authoring.gef.edit.RaisedMarginBorder;
import org.eclipse.epf.diagram.model.RoleTaskComposite;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

/**
 * Figure for {@link RoleTaskComposite}.
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class RoleTaskCompositeFigure extends Figure {

	private static final Color BG_COLOR = new Color(Display.getCurrent(), 255,
			255, 156);

	private static final int MAX_WIDTH = 500;

	private static final int MINOR_SPACING = 20;

	private Figure tasksFigure;

	/**
	 * 
	 */
	public RoleTaskCompositeFigure() {
		FlowLayout layout = new FlowLayout(true);
		layout.setMinorAlignment(FlowLayout.ALIGN_CENTER);
		layout.setMajorAlignment(FlowLayout.ALIGN_LEFTTOP);
		layout.setStretchMinorAxis(false);
		layout.setMinorSpacing(MINOR_SPACING);
		layout.setObserveVisibility(true);

		setLayoutManager(layout);
		setBorder(new RaisedMarginBorder());
		setBackgroundColor(BG_COLOR);
		setOpaque(true);

		tasksFigure = new Figure() {
			/**
			 * @see org.eclipse.draw2d.Figure#getPreferredSize(int, int)
			 */
			public Dimension getPreferredSize(int wHint, int hHint) {
				IFigure firstChild = (IFigure) getChildren().get(0);
				if (firstChild != this) {
					int offset = firstChild.getSize().width; // firstChild.getSize().width
					// +
					// RoleTaskCompositeFigure.this.getInsets().getWidth()
					// +
					// MINOR_SPACING;
					if (wHint < offset) {

					}
					wHint = wHint - offset;
					if (wHint > MAX_WIDTH) {
						wHint = MAX_WIDTH;
					}
				}
				Dimension dim = super.getPreferredSize(wHint, hHint);
				return dim;
			}
		};
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setMinorSpacing(10);
		flowLayout.setMinorAlignment(FlowLayout.ALIGN_CENTER);
		tasksFigure.setLayoutManager(flowLayout);
		tasksFigure.setOpaque(true);
		super.add(tasksFigure, null, -1);
	}

	/**
	 * @see org.eclipse.draw2d.Figure#add(IFigure, Object, int)
	 */
	public void add(IFigure figure, Object constraint, int index) {
		if (getChildren().size() == 1) {
			super.add(figure, constraint, 0);
		} else {
			tasksFigure.add(figure, constraint);
		}
	}

}
