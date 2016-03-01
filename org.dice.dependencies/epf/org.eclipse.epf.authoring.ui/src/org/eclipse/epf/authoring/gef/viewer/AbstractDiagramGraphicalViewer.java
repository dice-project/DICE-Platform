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
package org.eclipse.epf.authoring.gef.viewer;

import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.SWTGraphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.epf.authoring.gef.edit.DiagramEditPart;
import org.eclipse.epf.authoring.gef.edit.NodeContainerEditPart;
import org.eclipse.epf.authoring.gef.figures.SelectableLabel;
import org.eclipse.epf.diagram.model.Diagram;
import org.eclipse.epf.diagram.model.LinkedObject;
import org.eclipse.epf.diagram.model.NamedNode;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.util.Suppression;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.layout.diagram.DiagramInfo;
import org.eclipse.epf.library.util.ResourceHelper;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.DiagramElement;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.editparts.LayerManager;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;


/**
 * The abstract base class for a graphical viewer of the element diagram.
 * 
 * @author Jinhua Xi
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
	protected GraphicalViewer getGraphicalViewer() {
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
	 * Creates diagram for given object (only for {@link Activity} and packs the bounds.
	 * 
	 * @param wrapper 	{@link Object} 	
	 * @param needReset boolean
	 * @param filter 	{@link IFilter}
	 * @param sup		{@link Suppression}
	 */
	public EditPart loadDiagram(Object wrapper, boolean needReset,
			IFilter filter, Suppression sup) {
		// initialize the viewer with the edit part
		EditPart editPart = createEditPart(wrapper, filter, sup);
		this.graphicalViewer.setContents(editPart);

		// ask for immediate update of the control so that the diagram figure
		// will be computed
		parent.pack(true);

		// This check is needed for browsing, in order to render activity contributing's
		// children.  In processAuthoring we don't do realization, in order to displayed 
		// realized elements, we have to cleanup ADD diagram and recreate. needtoReset or cleanupdiagram.
		// Only for activity detail diagram.
		Object o = TngUtil.unwrap(wrapper);
		if(o instanceof VariabilityElement && getDiagramType() != null && 
				getDiagramType().equalsIgnoreCase(ResourceHelper.DIAGRAM_TYPE_ACTIVITY_DETAIL)){
			if(TngUtil.hasContributor((VariabilityElement)o)){
				needReset = true;
			}
		}
		
		if (needReset) {
			cleanUpDiagram();
		}

		IFigure figure = getFigure();
		if (figure != null) {
			Rectangle bounds = figure.getBounds();

			//System.out.println("Before update: Diagram Bounds = " + bounds); //$NON-NLS-1$
			if (bounds.x < 0 || bounds.y < 0) {
				((DiagramEditPart) editPart).moveFigure(-bounds.x, -bounds.y);

				parent.pack(true);

				bounds = figure.getBounds();
				//System.out.println("After update: Diagram Bounds = " + bounds); //$NON-NLS-1$
			}
		}
		return editPart;
	}

	protected void loadDiagramInfo(GraphicalEditPart part, DiagramInfo diagramInfo) {
		List children = part.getChildren();
		for (Iterator it = children.iterator(); it.hasNext();) {
			Object o = it.next();
			if (o instanceof NodeContainerEditPart) {
				loadDiagramInfo((GraphicalEditPart) o, diagramInfo);
			} else if (o instanceof GraphicalEditPart) {
				part = (GraphicalEditPart) o;
				Object model = part.getModel();
				Object e = model;
				if (e instanceof LinkedObject) {
					e = ((LinkedObject) e).getObject();
				}

				if (e instanceof DiagramElement) {
					continue;
				}

				if (e instanceof MethodElement) {
					boolean suppressed;
					if (model instanceof NamedNode) {
						suppressed = ((NamedNode) model).isSuppressed();
					} else {
						suppressed = ((MethodElement) e).getSuppressed()
								.booleanValue();
					}

						IFigure f = part.getFigure();
						Rectangle bounds = f.getBounds();

						String altTag = null;
						if (f instanceof SelectableLabel) {
							altTag = ((SelectableLabel) f).getText();
						}
						diagramInfo.addArea((MethodElement) e, bounds.x,
							bounds.y, bounds.width, bounds.height, altTag, suppressed);

				}
			} else {
				// System.out.println("EditPart not handled: " + o);
				// //$NON-NLS-1$
			}
		}
	}

	/**
	 * returns {@link DiagramInfo}
	 * 
	 */
	public DiagramInfo getDiagramInfo() {
		DiagramInfo diagramInfo = null;

		GraphicalEditPart part = (GraphicalEditPart) this.graphicalViewer
				.getContents();
		Object element = part.getModel();
		if (element instanceof LinkedObject) {
			element = ((LinkedObject) element).getObject();
		}
		if (element instanceof MethodElement) {
			diagramInfo = new DiagramInfo(getDiagramType(),
					(MethodElement) element);
			loadDiagramInfo(part, diagramInfo);
		}

		return diagramInfo;
	}

	/**
	 * Creates diagram image from figure created in loadDiagram method.
	 * 
	 */
	public Image createDiagramImage() {
		IFigure figure = getFigure();

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

}
