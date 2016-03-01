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
package org.eclipse.graphiti.ui.internal.editor;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.AccessibleEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.ExposeHelper;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.graphiti.ui.internal.fixed.FixedScalableRootEditPart;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * The Class GFScrollingGraphicalViewer.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class GFScrollingGraphicalViewer extends GraphitiScrollingGraphicalViewer {

	/**
	 * The root figure.
	 */
	IFigure rootFigure;

	/**
	 * The diagram editor.
	 */
	/**
	 * Constructs a ScrollingGraphicalViewer;.
	 * 
	 * @param diagramEditor
	 *            the diagram editor
	 */
	public GFScrollingGraphicalViewer(DiagramEditorInternal diagramEditor) {
		super(diagramEditor);
	}

	/**
	 * Creates the default root editpart. Called during construction.
	 */
	@Override
	protected void createDefaultRoot() {
		setRootEditPart(new FixedScalableRootEditPart());
	}

	/**
	 * Creates the gf control.
	 * 
	 * @param parent
	 *            the parent
	 * @return the control
	 */
	public final Control createGFControl(Composite parent) {
		GFFigureCanvas canvas = new GFFigureCanvas(parent, getLightweightSystem(), getDiagramEditor());
		canvas.setData(new String("name"), canvas.getClass().toString()); //$NON-NLS-1$
		setControl(canvas);
		installRootFigure();
		return canvas;
	}

	private void installRootFigure() {
		if (getGFFigureCanvas() == null)
			return;
		if (rootFigure instanceof Viewport)
			getGFFigureCanvas().setViewport((Viewport) rootFigure);
		else
			getGFFigureCanvas().setContents(rootFigure);
	}

	/**
	 * Gets the figure canvas.
	 * 
	 * @return the figure canvas
	 */
	protected GFFigureCanvas getGFFigureCanvas() {
		return (GFFigureCanvas) getControl();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.gef.ui.parts.ScrollingGraphicalViewer#setRootFigure(org.eclipse
	 * .draw2d.IFigure)
	 */
	@Override
	protected void setRootFigure(IFigure figure) {
		rootFigure = figure;
		installRootFigure();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.gef.ui.parts.ScrollingGraphicalViewer#reveal(org.eclipse.
	 * gef.EditPart)
	 */
	@Override
	public void reveal(EditPart part) {

		if (part == null)
			return;
		EditPart current = part.getParent();
		while (current != null) {
			ExposeHelper helper = (ExposeHelper) current.getAdapter(ExposeHelper.class);
			if (helper != null)
				helper.exposeDescendant(part);
			current = current.getParent();
		}
		AccessibleEditPart acc = (AccessibleEditPart) part.getAdapter(AccessibleEditPart.class);
		if (acc != null)
			getControl().getAccessible().setFocus(acc.getAccessibleID());

		Viewport port = getGFFigureCanvas().getViewport();
		IFigure target = ((GraphicalEditPart) part).getFigure();
		Rectangle exposeRegion = target.getBounds().getCopy();
		target = target.getParent();
		while (target != null && target != port) {
			target.translateToParent(exposeRegion);
			target = target.getParent();
		}
		exposeRegion.expand(5, 5);

		Dimension viewportSize = port.getClientArea().getSize();

		Point topLeft = exposeRegion.getTopLeft();
		Point bottomRight = exposeRegion.getBottomRight().translate(viewportSize.getNegated());
		Point finalLocation = new Point();
		if (viewportSize.width < exposeRegion.width)
			finalLocation.x = Math.min(bottomRight.x, Math.max(topLeft.x, port.getViewLocation().x));
		else
			finalLocation.x = Math.min(topLeft.x, Math.max(bottomRight.x, port.getViewLocation().x));

		if (viewportSize.height < exposeRegion.height)
			finalLocation.y = Math.min(bottomRight.y, Math.max(topLeft.y, port.getViewLocation().y));
		else
			finalLocation.y = Math.min(topLeft.y, Math.max(bottomRight.y, port.getViewLocation().y));

		getGFFigureCanvas().scrollSmoothTo(finalLocation.x, finalLocation.y);
	}
}
