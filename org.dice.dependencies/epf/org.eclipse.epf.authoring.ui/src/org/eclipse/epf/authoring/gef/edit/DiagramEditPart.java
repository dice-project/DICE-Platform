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
package org.eclipse.epf.authoring.gef.edit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.authoring.gef.commands.ChangeBoundsCommand;
import org.eclipse.epf.authoring.gef.edit.policies.DiagramLayoutEditPolicy;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.diagram.model.Diagram;
import org.eclipse.epf.diagram.model.Link;
import org.eclipse.epf.diagram.model.Node;
import org.eclipse.epf.diagram.model.NodeContainer;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.editpolicies.RootComponentEditPolicy;

/**
 * Provides EditPart for Diagram. Handles the basic layout mechanism
 * if the children location is not set in the diagram.  
 *   
 * @author Phong Nguyen Le
 * @author skannoor
 * @since 1.0
 */
public class DiagramEditPart extends NodeContainerEditPart {

	Point cachedPoint = new Point(-1, -1);

	protected boolean debug;

	private boolean markDirty = false;

	static int cachedWidth = 10;

	static int cachedHeight = 10;

	public DiagramEditPart(Diagram model) {
		super(model);
		debug = AuthoringUIPlugin.getDefault().isDebugging();
	}

	protected IFigure createFigure() {
		Figure f = new FreeformLayer() {
			protected void layout() {
				if (hasMisplacedChildren()) {
					resetChildrenLocations();
				}
				super.layout();
			}

		};

		f.setBorder(new MarginBorder(20));
		f.setLayoutManager(new FreeformLayout());
		return f;
	}

	protected void resetChildrenLocations() {
		int y = 10;
		final int spacing = 40;
		int occupied = 0;
		int xelements = 0;
		int x = 10;
		boolean locked = TngUtil.isLocked((EObject)((Diagram)getModel()).getObject());
		for (Iterator iter = getChildren().iterator(); iter.hasNext();) {
			Object child = iter.next();
			if (child instanceof GraphicalEditPart) {
				GraphicalEditPart part = (GraphicalEditPart) child;
				int width = part.getRoot().getViewer().getControl().getBounds().width;
				if (part.getModel() instanceof Node) {
					Node node = (Node) part.getModel();
					Point p = node.getLocation().getCopy();
					if (p == null || p.x == -1) {
						if (x + part.getFigure().getPreferredSize().width > width) {
							y = cachedPoint.y + cachedHeight + spacing;
							xelements = 0;
							x = 10;
							cachedPoint.x = -1;
							occupied = 0;
						}
						if (cachedPoint.x != -1) {
							x = cachedPoint.x + cachedWidth + spacing;
						}
						++xelements;
						if (p == null) {
							p = new Point(-1, -1);
						}
						p.x = x;
						p.y = y;
						//node.setLocation(p);
						//On opening a AD diagram, editor should be marked dirty.
						//If diagram is not saved, cannot be browsed. Forcing to save the diagram once.
						if (!locked && ((Diagram) getModel()).isNew()
								&& !((Diagram) getModel()).isReadOnly()
								&& markDirty) {
							ChangeBoundsCommand cbc = new ChangeBoundsCommand(node, p, part.getFigure()
											.getPreferredSize().width);
							getViewer().getEditDomain().getCommandStack()
									.execute(cbc);
						} else {
							// If Plugin is Locked: Do auto-layout and donot
							// save to model.
							node.setLocation(p);
						}
						
						cachedPoint = p;
						cachedWidth = part.getFigure().getPreferredSize().width;
						cachedHeight = part.getFigure().getPreferredSize().height;
						if (debug) {
							System.out
									.println("Point : " + new Point(x, y).toString()); //$NON-NLS-1$
							System.out
									.println("Cached : width: " + cachedWidth + " Height : " + cachedHeight); //$NON-NLS-1$ //$NON-NLS-2$
						}
						occupied += x;
					}
				}
			}
		}
	}

	protected boolean hasMisplacedChildren() {
		for (Iterator iter = getChildren().iterator(); iter.hasNext();) {
			Object child = iter.next();
			if (child instanceof EditPart) {
				Object model = ((EditPart) child).getModel();
				if (model instanceof Node) {
					Node node = (Node) model;
					Point p = node.getLocation();
					if (p == null || p.x == -1){
						return true;
					}
				}
			}
		}
		return false;
	}

	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.COMPONENT_ROLE,
				new RootComponentEditPolicy());
		installEditPolicy(EditPolicy.CONTAINER_ROLE,
				new DiagramLayoutEditPolicy());
	}

	/**
	 * @see org.eclipse.epf.authoring.gef.edit.NodeContainerEditPart#addPartToEdit(Collection,
	 *      Object)
	 */
	protected void addPartToEdit(Collection partsToEdit, Object node) {
		if (node instanceof NodeContainer) {
			NodeContainerEditPart editPart = (NodeContainerEditPart) findChildByModel(node);
			for (Iterator iter = ((NodeContainer) node).getNodes().iterator(); iter
					.hasNext();) {
				editPart.addPartToEdit(partsToEdit, iter.next());
			}
		} else {
			super.addPartToEdit(partsToEdit, node);
		}
	}

	/**
	 * Moves complete diagram children to given delta. 
	 * @param int  (X-axis delta)
	 * @param int  (Y-axis delta)
	 * 
	 */
	public void moveFigure(int moveX, int moveY) {
		for (Iterator iter = this.getChildren().iterator(); iter.hasNext();) {
			Object child = iter.next();
			if (child instanceof GraphicalEditPart) {
				GraphicalEditPart part = (GraphicalEditPart) child;
				Rectangle bounds = part.getFigure().getBounds();
				Rectangle r = new Rectangle(new Point(bounds.x + moveX,
						bounds.y + moveY), bounds.getSize());

				// also need to move the bend points
				List list = part.getSourceConnections();
				for (Iterator itor = list.iterator(); itor.hasNext();) {
					LinkEditPart linkpart = (LinkEditPart) itor.next();
					Link link = (Link) linkpart.getModel();
					if (link.getBendpoints().size() > 0) {
						PointList plist = ((PolylineConnection) linkpart
								.getFigure()).getPoints();
						List points = new ArrayList();
						for (int i = 1; i < plist.size() - 1; i++) {
							Point p = plist.getPoint(i);
							p = p.translate(new Point(moveX, moveY));

							org.eclipse.draw2d.AbsoluteBendpoint bp = new org.eclipse.draw2d.AbsoluteBendpoint(
									p);
							points.add(bp);
						}

						linkpart.getConnectionFigure().setRoutingConstraint(
								points);
					}
				}

				this.setLayoutConstraint(part, part.getFigure(), r);

			} else {
				// System.out.println("Part not moved: " + child); //$NON-NLS-1$
			}
		}

	}
	/**
	 * On opening a diagram, editor should be marked dirty.
	 * Invoker of DiagramEditPart should explicitly set the flag to mark editor dirty
	 * or not. Withou handling this will cause browsing perspective in consistent.    
	 * For defect: https://bugs.eclipse.org/bugs/show_bug.cgi?id=156029
	 */
	public void markDirty(boolean markDirty){
		this.markDirty  = markDirty;
	}
}