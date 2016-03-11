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
package org.eclipse.epf.diagram.ui.viewer;

import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.SWTGraphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.epf.diagram.core.figures.WidenedWrapLabel;
import org.eclipse.epf.diagram.model.Diagram;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.util.Suppression;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.layout.diagram.DiagramInfo;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.Process;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.editparts.LayerManager;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

/**
 * The abstract base class for a graphical viewer of the element diagram.
 * 
 * @author Jinhua Xi
 * @author Shashidhar Kannoori
 * @since 1.0
 */
public abstract class AbstractDiagramGraphicalViewer {

	protected Composite parent;

	protected GraphicalViewer graphicalViewer;

	protected Object wrapper;

	public AbstractDiagramGraphicalViewer(Composite parent) {
		this.parent = parent;
		createGraphicalViewer();
	}

	public AbstractDiagramGraphicalViewer(Composite parent, Object wrapper) {
		this.parent = parent;
		this.wrapper = wrapper;
		createGraphicalViewer();
	}

	/**
	 * Creates the GraphicalViewer on the specified <code>Composite</code>
	 * 
	 */
	protected void createGraphicalViewer() {
		this.graphicalViewer = new ScrollingGraphicalViewer();
		this.graphicalViewer.createControl(parent);
		configureGraphicalViewer();
	}

	/**
	 * Returns the graphical viewer.
	 * 
	 * @return the graphical viewer
	 */
	public GraphicalViewer getGraphicalViewer() {
		return graphicalViewer;
	}

	protected void configureGraphicalViewer() {
		getGraphicalViewer().getControl().setBackground(
				ColorConstants.listBackground);
		ScrollingGraphicalViewer viewer = (ScrollingGraphicalViewer) getGraphicalViewer();

		ScalableFreeformRootEditPart root = new ScalableFreeformRootEditPart();
		viewer.setRootEditPart(root);
		viewer.setEditPartFactory(createEditPartFactory());
	}

	protected IFigure getFigure() {
		GraphicalViewer viewer = getGraphicalViewer();

		// find the figure to be saved
		LayerManager lm = (LayerManager) viewer.getEditPartRegistry().get(
				LayerManager.ID);
		IFigure figure = lm.getLayer(LayerConstants.PRINTABLE_LAYERS);
		return figure;
	}

	protected void setSuppressionToDiagram(Diagram diagram) {
		Object e = diagram.getObject();
		if (e instanceof BreakdownElement) {
			Process proc = TngUtil.getOwningProcess((BreakdownElement) e);
			if (proc != null) {
				diagram.setSuppression(Suppression.getSuppression(proc));
			}
		}
	}

	/**
	 * Creates diagram image from figure created in loadDiagram method.
	 * 
	 */
	public Image createDiagramImage() {
		IFigure figure = getFigure();

		IFigure borderItemAwareFreeFormLayer = (IFigure) figure.getChildren()
				.get(0);

		IFigure tempFigure = (IFigure) borderItemAwareFreeFormLayer
				.getChildren().get(0);
		List defaultSizeNodeFigureList = null;
		if (tempFigure != null)
			defaultSizeNodeFigureList = tempFigure.getChildren();

		for (int i = 0; i < defaultSizeNodeFigureList.size(); i++) {
			IFigure activityNodeFigure = null;
			if (defaultSizeNodeFigureList.get(i) != null)
				activityNodeFigure = (IFigure) ((IFigure) defaultSizeNodeFigureList
						.get(i)).getChildren().get(0);
			IFigure widenedWrapLabel = null;
			if (activityNodeFigure != null) {
				List children = ((IFigure) activityNodeFigure).getChildren();
				if (children != null && !children.isEmpty()) {
					widenedWrapLabel = (IFigure) activityNodeFigure
							.getChildren().get(0);
					if (widenedWrapLabel instanceof WidenedWrapLabel) {
						widenedWrapLabel.setForegroundColor(new Color(null, 0, 0, 0));
					}
				}
			}			
		}

		SWTGraphics graphics = null;
		GC gc = null;
		Image image = null;

		try {
			Rectangle bounds = figure.getBounds();
			int height = bounds.height;
			int width = bounds.width;

			// create a new image and repaint the graph
			final Display display = Display.getDefault();
			image = new Image(display, width, height);
			gc = new GC(image);
			graphics = new SWTGraphics(gc);
			figure.paint(graphics);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// release resources
			if (graphics != null)
				graphics.dispose();
			if (gc != null)
				gc.dispose();
		}

		return image;
	}

	public void dispose() {
		try {
			if (graphicalViewer != null) {
				Control ctrl = graphicalViewer.getControl();
				if (ctrl != null) {
					ctrl.dispose();
				}

				EditPart editPart = graphicalViewer.getContents();
				if (editPart != null) {
					editPart.setModel(null);
				}

				graphicalViewer = null;
			}

		} catch (RuntimeException e) {
			e.printStackTrace();
		}

	}

	protected abstract EditPartFactory createEditPartFactory();

	protected abstract EditPart createEditPart(Object e, IFilter filter,
			Suppression sup);

	protected abstract String getDiagramType();

	protected void cleanUpDiagram() {
		// default implementation does nothing
	}

	public abstract DiagramInfo getDiagramInfo();

	public abstract EditPart loadDiagram(Object wrapper, boolean needReset,
			IFilter filter, Suppression sup);

	public abstract void setMethodConfiguration(MethodConfiguration config);
}
