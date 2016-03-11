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
package org.eclipse.graphiti.ui.internal.fixed;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.ScalableFreeformLayeredPane;

/**
 * The Class FixedScalableFreeformLayeredPane.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class FixedScalableFreeformLayeredPane extends ScalableFreeformLayeredPane {
	/**
	 * Paint client area.
	 * 
	 * @param graphics
	 *            the graphics
	 * 
	 * @see org.eclipse.draw2d.Figure#paintClientArea(Graphics)
	 */
	@SuppressWarnings("unused")
	@Override
	protected void paintClientArea(Graphics graphics) {
		if (getChildren().isEmpty())
			return;
		if (false && getScale() == 1.0) { // deactivated; always use
			// FixedScaledGraphics for
			// consistency
			super.paintClientArea(graphics);
		} else {
			Graphics g = new FixedScaledGraphics(graphics);
			boolean optimizeClip = getBorder() == null || getBorder().isOpaque();
			if (!optimizeClip)
				g.clipRect(getBounds().getCropped(getInsets()));
			g.scale(getScale());
			g.pushState();
			paintChildren(g);
			g.dispose();
			graphics.restoreState();
		}
	}
}
