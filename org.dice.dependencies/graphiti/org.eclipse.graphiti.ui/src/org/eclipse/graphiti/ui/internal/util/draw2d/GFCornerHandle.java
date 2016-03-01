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
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gef.handles.AbstractHandle;
import org.eclipse.gef.tools.DragEditPartsTracker;
import org.eclipse.gef.tools.ResizeTracker;
import org.eclipse.graphiti.ui.internal.config.IConfigurationProvider;
import org.eclipse.graphiti.ui.internal.figures.GFFigureUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;

/**
 * A rectangular handle which is placed at a corner or at a side of a shape
 * edit-part. It can be used to move or resize the shape edit-part.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class GFCornerHandle extends AbstractHandle {

	/**
	 * The line-width of the handle.
	 */
	private static int LINE_WIDTH = 1;

	/**
	 * The handle-dimension is used to locate the handle as described in
	 * {@link ZoomingRelativeHandleLocator}.
	 */
	private static Dimension HANDLE_DIMENSION = new Dimension(5, 5);

	/**
	 * The handle-insets are used to locate the handle as described in
	 * {@link ZoomingRelativeHandleLocator}.
	 */
	private static Dimension HANDLE_INSETS = new Dimension(3, 3);

	/**
	 * The foreground color for resizable directions.
	 */
	private static Color FG_COLOR_RESIZABLE;

	/**
	 * The foreground color for not-resizable directions.
	 */
	private static Color FG_COLOR_NOT_RESIZABLE;

	/**
	 * The background color for primary-selected, resizable directions.
	 */
	private static Color BG_COLOR_PRIMARY_RESIZABLE;

	/**
	 * The background color for secondary-selected, resizable directions.
	 */
	private static Color BG_COLOR_SECONDARY_RESIZABLE;

	/**
	 * The background color for primary-selected, not-resizable directions.
	 */
	private static Color BG_COLOR_PRIMARY_NOT_RESIZABLE;

	/**
	 * The background color for secondary-selected, not-resizable directions.
	 */
	private static Color BG_COLOR_SECONDARY_NOT_RESIZABLE;

	// ========================================================================

	/**
	 * The configuration provider, which can be used to access the environment.
	 */
	private IConfigurationProvider configurationProvider;

	/**
	 * The supported resize direction (see
	 * {@link ResizableEditPolicy#getResizeDirections()}). Is 0 if resizing is
	 * not allowed.
	 */
	private int resizeDirection;

	/**
	 * Indicates, if moving the shape edit-part via this handle is supported.
	 */
	private boolean movable;

	/**
	 * Creates a new GFCornerHandle.
	 * 
	 * @param owner
	 *            The shape edit-part associated with this handle.
	 * @param configurationProvider
	 *            The configuration provider, which can be used to access the
	 *            environment.
	 * @param location
	 *            The location at which to locate the handle. If the location is
	 *            part of the supported resize directions, then resizing is
	 *            supported for this handle.
	 * @param supportedResizeDirections
	 *            The supported resize directions (see
	 *            {@link ResizableEditPolicy#getResizeDirections()})
	 * @param movable
	 *            Indicates, if moving the shape edit-part via this handle is
	 *            supported.
	 */
	public GFCornerHandle(GraphicalEditPart owner, IConfigurationProvider configurationProvider, int location,
			int supportedResizeDirections, boolean movable) {
		this.configurationProvider = configurationProvider;
		this.resizeDirection = supportedResizeDirections & location;
		this.movable = movable;

		setOwner(owner);
		setLocator(new ZoomingRelativeHandleLocator(owner.getFigure(), configurationProvider, location, HANDLE_DIMENSION, HANDLE_INSETS));

		setOpaque(false);

		if (isResizable()) {
			setCursor(Cursors.getDirectionalCursor(resizeDirection, owner.getFigure().isMirrored()));
		} else if (movable) {
			setCursor(Cursors.SIZEALL);
		} else {
			setCursor(null);
		}
	}

	/**
	 * @return the fG_COLOR_RESIZABLE
	 */
	public Color getFG_COLOR_RESIZABLE() {
		if (FG_COLOR_RESIZABLE == null || FG_COLOR_RESIZABLE.isDisposed())
			FG_COLOR_RESIZABLE = configurationProvider.getResourceRegistry().getSwtColor("f17d00"); //$NON-NLS-1$
		return FG_COLOR_RESIZABLE;
	}

	/**
	 * @return the fG_COLOR_NOT_RESIZABLE
	 */
	public Color getFG_COLOR_NOT_RESIZABLE() {
		if (FG_COLOR_NOT_RESIZABLE == null || FG_COLOR_NOT_RESIZABLE.isDisposed())
			FG_COLOR_NOT_RESIZABLE = configurationProvider.getResourceRegistry().getSwtColor("b3b6bb"); //$NON-NLS-1$
		return FG_COLOR_NOT_RESIZABLE;
	}

	/**
	 * @return the bG_COLOR_PRIMARY_RESIZABLE
	 */
	public Color getBG_COLOR_PRIMARY_RESIZABLE() {
		if (BG_COLOR_PRIMARY_RESIZABLE == null || BG_COLOR_PRIMARY_RESIZABLE.isDisposed())
			BG_COLOR_PRIMARY_RESIZABLE = configurationProvider.getResourceRegistry().getSwtColor("ff8400"); //$NON-NLS-1$
		return BG_COLOR_PRIMARY_RESIZABLE;
	}

	/**
	 * @return the bG_COLOR_SECONDARY_RESIZABLE
	 */
	public Color getBG_COLOR_SECONDARY_RESIZABLE() {
		if (BG_COLOR_SECONDARY_RESIZABLE == null || BG_COLOR_SECONDARY_RESIZABLE.isDisposed())
			BG_COLOR_SECONDARY_RESIZABLE = configurationProvider.getResourceRegistry().getSwtColor("ffffff"); //$NON-NLS-1$
		return BG_COLOR_SECONDARY_RESIZABLE;
	}

	/**
	 * @return the bG_COLOR_PRIMARY_NOT_RESIZABLE
	 */
	public Color getBG_COLOR_PRIMARY_NOT_RESIZABLE() {
		if (BG_COLOR_PRIMARY_NOT_RESIZABLE == null || BG_COLOR_PRIMARY_NOT_RESIZABLE.isDisposed())
			BG_COLOR_PRIMARY_NOT_RESIZABLE = configurationProvider.getResourceRegistry().getSwtColor("b3b6bb"); //$NON-NLS-1$
		return BG_COLOR_PRIMARY_NOT_RESIZABLE;
	}

	/**
	 * @return the bG_COLOR_SECONDARY_NOT_RESIZABLE
	 */
	public Color getBG_COLOR_SECONDARY_NOT_RESIZABLE() {
		if (BG_COLOR_SECONDARY_NOT_RESIZABLE == null || BG_COLOR_SECONDARY_NOT_RESIZABLE.isDisposed())
			BG_COLOR_SECONDARY_NOT_RESIZABLE = configurationProvider.getResourceRegistry().getSwtColor("edf4ff"); //$NON-NLS-1$
		return BG_COLOR_SECONDARY_NOT_RESIZABLE;
	}

	/**
	 * Overridden to create a {@link ResizeTracker}, if resizing is supported,
	 * or to create a {@link DragEditPartsTracker}, if moving is supported.
	 */
	@Override
	protected DragTracker createDragTracker() {
		if (isResizable()) {
			return new ResizeTracker(getOwner(), resizeDirection);
		} else if (movable) {
			DragEditPartsTracker tracker = new DragEditPartsTracker(getOwner());
			tracker.setDefaultCursor(getCursor());
			return tracker;
		} else {
			return null;
		}
	}

	/**
	 * Returns <code>true</code> if the handles owner is the primary selection.
	 * If not, it must be the secondary selection.
	 * 
	 * @return <code>true</code> if the handles owner is the primary selection.
	 */
	private boolean isPrimarySelected() {
		return getOwner().getSelected() == EditPart.SELECTED_PRIMARY;
	}

	/**
	 * Paints a rectangular handle which is placed at a corner or at a side of
	 * the shape edit-part.
	 */
	@Override
	public void paintFigure(Graphics g) {
		g.setAntialias(SWT.ON);
		g.setLineWidth(getLineWidth());

		boolean primary = isPrimarySelected();
		Color fg;
		Color bg;
		if (primary && isResizable()) {
			fg = getFG_COLOR_RESIZABLE();
			bg = getBG_COLOR_PRIMARY_RESIZABLE();
		} else if (primary && !isResizable()) {
			fg = getFG_COLOR_NOT_RESIZABLE();
			bg = getBG_COLOR_PRIMARY_NOT_RESIZABLE();
		} else if (!primary && isResizable()) {
			fg = getFG_COLOR_RESIZABLE();
			bg = getBG_COLOR_SECONDARY_RESIZABLE();
		} else { // (!primary && !isResizable())
			fg = getFG_COLOR_NOT_RESIZABLE();
			bg = getBG_COLOR_SECONDARY_NOT_RESIZABLE();
		}
		if (fg != null)
			g.setForegroundColor(fg);
		if (bg != null)
			g.setBackgroundColor(bg);

		Rectangle r = GFFigureUtil.getAdjustedRectangle(getBounds(), 1.0, getLineWidth());
		g.fillRectangle(r);
		g.drawRectangle(r);
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

	/**
	 * Returns true, if the handle can be used to resize the shape edit-part.
	 * 
	 * @return true, if the handle can be used to resize the shape edit-part.
	 */
	private boolean isResizable() {
		return resizeDirection != 0;
	}
}
