/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2010 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.figures;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.graphiti.internal.services.GraphitiInternal;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.internal.parts.IPictogramElementDelegate;
import org.eclipse.swt.SWT;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class GFText extends Label {
	private GraphicsAlgorithm graphicsAlgorithm;

	private int labelAlignment = CENTER;

	public GFText(IPictogramElementDelegate pictogramElementDelegate, GraphicsAlgorithm graphicsAlgorithm) {
		this.graphicsAlgorithm = graphicsAlgorithm;
	}

	public int getLabelAlignment() {
		return labelAlignment;
	}

	@Override
	public void paintFigure(Graphics graphics) {
		if (graphicsAlgorithm != null && GraphitiInternal.getEmfService().isObjectAlive(graphicsAlgorithm)) {
			double transparency = Graphiti.getGaService().getTransparency(graphicsAlgorithm, true);
			int alpha = (int) ((1.0 - transparency) * 255.0);
			graphics.setAlpha(alpha);

			graphics.setTextAntialias(SWT.ON);

			int angle = 0;
			if (graphicsAlgorithm instanceof Text) {
				Text textGa = (Text) graphicsAlgorithm;
				angle = Graphiti.getGaService().getAngle(textGa, true);
			}

			if (angle != 0) {
				Rectangle rect = new Rectangle();
				graphics.getClip(rect);
				graphics.pushState();
				Rectangle bounds = getBounds();
				int w = bounds.width;
				int h = bounds.height;
				bounds.height = w;
				bounds.width = h;
				graphics.translate(bounds.x, bounds.y + h); // TODO caluclate
				// the offset to x
				// and y based on
				// angle
				graphics.rotate(angle);
				rect = new Rectangle(0, 0, 1000, 1000); // TODO calculate the
				// real clip rectangle
				// from the angle
				graphics.setClip(rect);
				graphics.drawText(getSubStringText(), getTextLocation());

				bounds.height = h;
				bounds.width = w;

				graphics.popState();
				return;

			}
		}

		super.paintFigure(graphics);

	}

	@Override
	public void setLabelAlignment(int align) {
		super.setLabelAlignment(align);
		labelAlignment = align;
	}
}
