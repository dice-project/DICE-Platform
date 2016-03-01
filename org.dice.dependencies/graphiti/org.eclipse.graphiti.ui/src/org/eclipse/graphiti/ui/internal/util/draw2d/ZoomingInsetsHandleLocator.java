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
package org.eclipse.graphiti.ui.internal.util.draw2d;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Locator;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.handles.HandleBounds;
import org.eclipse.graphiti.ui.internal.config.IConfigurationProvider;

/**
 * A Locator, which sets the target bounds to the reference figure bounds.
 * Insets are applied to expand/shrink the reference figure bounds at the
 * beginning of the calculation. The insets are adjusted with the current
 * zoom-level.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class ZoomingInsetsHandleLocator implements Locator {

	private IFigure reference;
	private IConfigurationProvider configurationProvider;
	private Insets handleInsets;

	/**
	 * Creates a new ZoomingInsetsHandleLocator.
	 * 
	 * @param reference
	 *            The target bounds are calculated depending on this reference
	 *            figure.
	 * @param configurationProvider
	 *            The configuration provider which can be used to access the
	 *            environment.
	 * @param handleInsets
	 *            The insets to apply to the reference figure bounds.
	 */
	public ZoomingInsetsHandleLocator(IFigure reference, IConfigurationProvider configurationProvider, Insets handleInsets) {
		this.reference = reference;
		this.configurationProvider = configurationProvider;
		this.handleInsets = handleInsets;
	}

	/**
	 * Sets the bounds of the target figure as described above.
	 * 
	 * @param target
	 *            The target figure for which to set the bounds.
	 */
	public void relocate(IFigure target) {
		Insets insets = new Insets(handleInsets);
		double zoom = GFHandleHelper.getZoomLevel(configurationProvider);
		insets.top *= zoom;
		insets.bottom *= zoom;
		insets.left *= zoom;
		insets.right *= zoom;

		Rectangle bounds;
		if (reference instanceof HandleBounds)
			bounds = ((HandleBounds) reference).getHandleBounds();
		else
			bounds = reference.getBounds();

		bounds = new PrecisionRectangle(bounds.getCopy());
		reference.translateToAbsolute(bounds);
		target.translateToRelative(bounds);
		bounds.translate(-insets.left, -insets.top);
		bounds.resize(insets.getWidth(), insets.getHeight());

		target.setBounds(bounds);
	}
}
