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
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RelativeLocator;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.handles.HandleBounds;
import org.eclipse.graphiti.ui.internal.config.IConfigurationProvider;

/**
 * A Locator, which sets the target bounds depending on the reference figure
 * bounds according to the following rules:
 * <ul>
 * <li>The handle-insets are used to expand/shrink the reference figure bounds,
 * at the beginning of the calculation. They are adjusted with the current
 * zoom-level.</li>
 * <li>The location can be one of the north-south-east-west constants in
 * {@link PositionConstants}. It describes the side/corner of the reference
 * figure bounds at which the target is located.</li>
 * <li>The handle-dimension defines the dimension of the target bounds. They are
 * adjusted with the current zoom-level.</li>
 * </ul>
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class ZoomingRelativeHandleLocator extends RelativeLocator implements Locator {

	private IFigure reference;
	private IConfigurationProvider configurationProvider;
	private Dimension handleDimension;

	/**
	 * Creates a new ZoomingInsetsHandleLocator.
	 * 
	 * @param reference
	 *            The target bounds are calculated depending on this reference
	 *            figure.
	 * @param configurationProvider
	 *            The configuration provider which can be used to access the
	 *            environment.
	 * @param location
	 *            The location can be one of the north-south-east-west constants
	 *            in {@link PositionConstants}.
	 * @param handleDimension
	 *            The dimension of the target bounds.
	 * @param handleInsets
	 *            The insets to apply to the reference figure bounds.
	 */
	public ZoomingRelativeHandleLocator(IFigure reference, IConfigurationProvider configurationProvider, int location,
			Dimension handleDimension, Dimension handleInsets) {
		this(reference, location, handleInsets);
		this.configurationProvider = configurationProvider;
		this.handleDimension = handleDimension;
	}

	/**
	 * Sets the relative and absolute x/y coordinates depending on the location.
	 */
	private ZoomingRelativeHandleLocator(IFigure reference, int location, Dimension handleInsets) {
		super(reference, location);
		this.reference = reference;
		switch (location & PositionConstants.NORTH_SOUTH) {
		case PositionConstants.NORTH:
			break;
		case PositionConstants.SOUTH:
			break;
		default:
		}

		switch (location & PositionConstants.EAST_WEST) {
		case PositionConstants.WEST:
			break;
		case PositionConstants.EAST:
			break;
		default:
		}
	}

	/**
	 * Returns the reference figure bounds adjusted by the insets.
	 * 
	 * @return The reference figure bounds adjusted by the insets.
	 */
	protected Rectangle getReferenceBox() {
		Rectangle result;
		if (reference instanceof HandleBounds)
			result = ((HandleBounds) reference).getHandleBounds();
		else
			result = reference.getBounds();
		return result;
	}

	/**
	 * Sets the bounds of the target figure as described above.
	 * 
	 * @param target
	 *            The target figure for which to set the bounds.
	 */
	public void relocate(IFigure target) {
		double zoom = GFHandleHelper.getZoomLevel(configurationProvider);
		Dimension zoomedTargetDimension = handleDimension.getCopy();
		if (zoom != 1.0) {
			zoomedTargetDimension = handleDimension.getCopy().scale(zoom);
		}

		Dimension preferredSize = target.getPreferredSize();
		preferredSize.width = zoomedTargetDimension.width;
		preferredSize.height = zoomedTargetDimension.height;
		target.setPreferredSize(preferredSize);

		super.relocate(target);
	}
}
