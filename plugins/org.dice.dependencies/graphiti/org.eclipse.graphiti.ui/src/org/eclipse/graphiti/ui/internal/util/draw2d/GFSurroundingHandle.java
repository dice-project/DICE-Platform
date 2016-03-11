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

import org.eclipse.draw2d.Cursors;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gef.handles.AbstractHandle;
import org.eclipse.gef.tools.DragEditPartsTracker;
import org.eclipse.graphiti.ui.internal.config.IConfigurationProvider;
import org.eclipse.graphiti.ui.internal.figures.GFFigureUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;

/**
 * A rectangular handle, which completely surrounds the owner edit-part. It
 * serves as selection highlighting and can also be used to move the owner
 * edit-part.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class GFSurroundingHandle extends AbstractHandle {

	/**
	 * The line-width of the handle.
	 */
	private static int LINE_WIDTH = 1;

	/**
	 * The handle-insets are used to locate the handle as described in
	 * {@link ZoomingInsetsHandleLocator}.
	 */
	private static Insets HANDLE_INSETS = new Insets(LINE_WIDTH, LINE_WIDTH, LINE_WIDTH, LINE_WIDTH);

	/**
	 * The line-style to use for resizable directions.
	 */
	private static int[] LINE_STYLE_RESIZABLE = new int[] { 2, 2 };

	/**
	 * The line-style to use for not-resizable directions.
	 */
	private static int[] LINE_STYLE_NOT_RESIZABLE = new int[] { 2, 2 };

	/**
	 * The foreground color to use for resizable directions.
	 */
	private static Color FG_COLOR_RESIZABLE;

	/**
	 * The foreground color to use for not-resizable directions.
	 */
	private static Color FG_COLOR_NOT_RESIZABLE;

	// ========================================================================

	/**
	 * @return the fG_COLOR_RESIZABLE
	 */
	public Color getFG_COLOR_RESIZABLE() {
		if (FG_COLOR_RESIZABLE == null || FG_COLOR_RESIZABLE.isDisposed())
			FG_COLOR_RESIZABLE = configurationProvider.getResourceRegistry().getSwtColor("ff850f"); //$NON-NLS-1$
		return FG_COLOR_RESIZABLE;
	}

	/**
	 * @return the fG_COLOR_NOT_RESIZABLE
	 */
	public Color getFG_COLOR_NOT_RESIZABLE() {
		if (FG_COLOR_NOT_RESIZABLE == null || FG_COLOR_NOT_RESIZABLE.isDisposed())
			FG_COLOR_NOT_RESIZABLE = configurationProvider.getResourceRegistry().getSwtColor("d9dadd"); //$NON-NLS-1$
		return FG_COLOR_NOT_RESIZABLE;
	}

	/**
	 * The configuration provider, which can be used to access the environment.
	 */
	private IConfigurationProvider configurationProvider;

	/**
	 * The supported resize directions (see
	 * {@link ResizableEditPolicy#getResizeDirections()})
	 */
	private int supportedResizeDirections;

	/**
	 * Indicates, if moving the owner edit-part via this handle is supported.
	 */
	private boolean movable;

	/**
	 * Creates a new GFSurroundingHandle.
	 * 
	 * @param owner
	 *            The owner editpart associated with this handle.
	 * @param configurationProvider
	 *            The configuration provider, which can be used to access the
	 *            environment.
	 * @param supportedResizeDirections
	 *            The supported resize directions (see
	 *            {@link ResizableEditPolicy#getResizeDirections()})
	 * @param movable
	 *            Indicates, if moving the owner edit-part via this handle is
	 *            supported.
	 */
	public GFSurroundingHandle(GraphicalEditPart owner, IConfigurationProvider configurationProvider, int supportedResizeDirections,
			boolean movable) {
		this.configurationProvider = configurationProvider;
		this.supportedResizeDirections = supportedResizeDirections;
		this.movable = movable;

		setOwner(owner);
		setLocator(new ZoomingInsetsHandleLocator(owner.getFigure(), configurationProvider, HANDLE_INSETS));

		setOpaque(false);

		if (movable) {
			setCursor(Cursors.SIZEALL);
		} else {
			setCursor(null);
		}
	}

	/**
	 * Overridden to create a {@link DragEditPartsTracker}, if moving is
	 * supported.
	 */
	@Override
	protected DragTracker createDragTracker() {
		if (movable) {
			DragEditPartsTracker tracker = new DragEditPartsTracker(getOwner());
			tracker.setDefaultCursor(getCursor());
			return tracker;
		} else {
			return null;
		}
	}

	/**
	 * Returns <code>true</code> if the point (x,y) is contained within this
	 * handle. This means, that the point is on the outline of the handle, not
	 * inside the handle.
	 * 
	 * @return <code>true</code> if the point (x,y) is contained within this
	 *         handle.
	 */
	@Override
	public boolean containsPoint(int x, int y) {
		// true, if inside bounds but not inside inner rectangle
		if (!getBounds().contains(x, y))
			return false;
		Rectangle inner = GFFigureUtil.getAdjustedRectangle(getBounds(), 1.0, 2 * getLineWidth());
		return !inner.contains(x, y);
	}

	/**
	 * Returns a point along the right edge of the handle.
	 * 
	 * @see org.eclipse.gef.Handle#getAccessibleLocation()
	 */
	@Override
	public Point getAccessibleLocation() {
		Point p = getBounds().getTopRight().translate(-1, getBounds().height / 4);
		translateToAbsolute(p);
		return p;
	}

	/**
	 * Paints a rectangular handle surrounding the owner edit-part.
	 */
	@Override
	public void paintFigure(Graphics g) {
		g.setAntialias(SWT.ON);
		g.setLineWidth(getLineWidth());

		Rectangle r = GFFigureUtil.getAdjustedRectangle(getBounds(), 1.0, getLineWidth());

		prepareForDrawing(g, PositionConstants.NORTH);
		g.drawLine(r.getTopLeft(), r.getTopRight());
		prepareForDrawing(g, PositionConstants.SOUTH);
		g.drawLine(r.getBottomLeft(), r.getBottomRight());
		prepareForDrawing(g, PositionConstants.EAST);
		g.drawLine(r.getTopRight(), r.getBottomRight());
		prepareForDrawing(g, PositionConstants.WEST);
		g.drawLine(r.getTopLeft(), r.getBottomLeft());
	}

	/**
	 * Prepares the graphics to paint the rectangle-side for the given
	 * direction. This will set the line-style and foreground color.
	 * 
	 * @param g
	 *            The graphics which to prepare.
	 * @param direction
	 *            The direction, for which to prepare the graphics.
	 */
	private void prepareForDrawing(Graphics g, int direction) {
		boolean resizable = (supportedResizeDirections & direction) != 0;

		int dash[];
		Color fg;
		if (resizable) {
			dash = LINE_STYLE_RESIZABLE;
			fg = getFG_COLOR_RESIZABLE();
		} else {
			dash = LINE_STYLE_NOT_RESIZABLE;
			fg = getFG_COLOR_NOT_RESIZABLE();
		}

		int dashZoomed[];
		double zoom = GFHandleHelper.getZoomLevel(configurationProvider);
		if (zoom == 1.0) {
			dashZoomed = dash;
		} else {
			dashZoomed = new int[dash.length];
			for (int i = 0; i < dashZoomed.length; i++) {
				dashZoomed[i] = Math.max(1, (int) (zoom * dash[i]));
			}
		}

		g.setLineStyle(Graphics.LINE_CUSTOM);
		g.setLineDash(dashZoomed);
		g.setForegroundColor(fg);
	}

	/**
	 * Returns the line-width adjusted with the current zoom-level.
	 * 
	 * @return The line-width adjusted with the current zoom-level.
	 */
	private int getLineWidth() {
		double zoom = GFHandleHelper.getZoomLevel(configurationProvider);
		return Math.max(1, (int) (zoom * LINE_WIDTH));
	}
}
