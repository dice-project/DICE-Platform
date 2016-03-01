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
package org.eclipse.epf.diagram.add.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.Transaction;
import org.eclipse.emf.transaction.impl.InternalTransactionalEditingDomain;
import org.eclipse.epf.diagram.add.edit.parts.ActivityDetailDiagramEditPart;
import org.eclipse.epf.diagram.add.edit.parts.RoleNodeEditPart;
import org.eclipse.epf.diagram.add.edit.parts.RoleTaskCompositeEditPart;
import org.eclipse.epf.diagram.add.edit.parts.TaskNodeEditPart;
import org.eclipse.epf.diagram.add.edit.parts.WorkProductCompositeEditPart;
import org.eclipse.epf.diagram.core.commands.ChangeBoundsCommand;
import org.eclipse.epf.diagram.core.util.DiagramConstants;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.graphics.Font;

/**
 * Service class for reset the Activity Detail Diagram.
 * Code migrated from old diagram code.   
 * 
 * @author Shashidhar Kannoori
 * @author Phong Nguyen Le
 * @since 1.2
 **/
public class DiagramResetService {

	private DefaultEditDomain editDomain;

	private GraphicalViewer graphicalViewer;

	private ActionRegistry actionRegistry;

	private InternalTransactionalEditingDomain transactionDomain;
	
	int delta = 0;
	
	private static final int IMG_HEIGHT = 32;

	public DiagramResetService(InternalTransactionalEditingDomain transactionDomain, 
			GraphicalViewer graphicalViewer, DefaultEditDomain editDomain,
			ActionRegistry actionRegistry) {
		this.transactionDomain = transactionDomain;
		this.graphicalViewer = graphicalViewer;
		this.editDomain = editDomain;
		this.actionRegistry = actionRegistry;
	}

	private GraphicalViewer getGraphicalViewer() {
		return graphicalViewer;
	}

	/**
	 * Returns the command stack.
	 * 
	 * @return the command stack
	 */
	protected CommandStack getCommandStack() {
		return getEditDomain().getCommandStack();
	}

	/**
	 * Returns the edit domain.
	 * 
	 * @return the edit domain
	 */
	protected DefaultEditDomain getEditDomain() {
		return editDomain;
	}

	/**
	 * Lazily creates and returns the action registry.
	 * 
	 * @return the action registry
	 */
	protected ActionRegistry getActionRegistry() {
		return actionRegistry;
	}

	/**
	 * Method used only in ActivityDetailDiagram or any diagram need auto-layout.
	 * Method will verify recently added editparts in the Activitydetaildiagram editpart
	 * indirectly from WorkBreakdownStructure (means any descriptors created in wbs, corresponding
	 * EditPart will get created in ActivityDetailDiagram Viewer) and does auto-layout
	 * all the new added editparts and existing ones. 
	 * 
	 */
	public void cleanUpDiagram() {
		if (getGraphicalViewer().getContents() instanceof ActivityDetailDiagramEditPart) {
			ActivityDetailDiagramEditPart dep = (ActivityDetailDiagramEditPart) getGraphicalViewer()
					.getContents();
			List children = dep.getRecentlyAddedParts();
			
			// If any oblique links rectify them to straight. 
//			anyObliqueLinks(dep, children);
			
			if (!children.isEmpty()) {
				Comparator<Object> comparator = new Comparator<Object>() {

					public int compare(Object arg0, Object arg1) {
						int rc = 0;
						if (arg0 instanceof RoleTaskCompositeEditPart) {
							if (!(arg1 instanceof RoleTaskCompositeEditPart))
								rc = -1;
						} else if (arg1 instanceof RoleTaskCompositeEditPart)
							rc = 1;
						return rc;
					}
					
				};

				Object[] array = children.toArray();
				Arrays.sort(array, comparator);
				for (int a = 0; a < array.length; a++) {
					AbstractGraphicalEditPart ep = (AbstractGraphicalEditPart) array[a];
					adjustSize(ep);
				}
				
				// force relayout after size adjustment
				//
				dep.getViewport().validate();
				
				for (int b = 0; b < array.length; b++) {
					AbstractGraphicalEditPart ep = (AbstractGraphicalEditPart) array[b];
					cleanUp(ep);
				}
				for (int c = 0; c < array.length; c++) {
					AbstractGraphicalEditPart ep = (AbstractGraphicalEditPart) array[c];
					reduceLinkLength(ep);
				}
//				for (int c = 0; c < array.length; c++) {
//					AbstractGraphicalEditPart ep = (AbstractGraphicalEditPart) array[c];
//					if(ep instanceof RoleTaskCompositeEditPart){
//						resetSizes(ep, ep);
//					}
//				}
				packDiagram();
				dep.refresh();
				dep.clearRecentlyAddedParts();
				getGraphicalViewer().setSelection(new StructuredSelection());
			}
		}
	}

	/**
	 * This method does a vertical alignment of all nodes with links
	 */
	private void cleanUp(AbstractGraphicalEditPart ep) {
		if (!(ep instanceof WorkProductCompositeEditPart)) {
			List sourceLinks = ep.getSourceConnections();
			if (sourceLinks.size() > 0) {
				for (int b = 0; b < sourceLinks.size(); b++) {
					ConnectionNodeEditPart link = (ConnectionNodeEditPart) sourceLinks.get(b);
					AbstractGraphicalEditPart target = (AbstractGraphicalEditPart) link
							.getTarget();
					int w = target.getFigure().getBounds().width;
					int ew = ep.getFigure().getPreferredSize().width;
					if (w < ew) {
						Command c = new ChangeBoundsCommand(transactionDomain, ((Node) ep
								.getModel()), target.getFigure().getBounds()
								.getLocation(), w, target.getFigure().getPreferredSize().height);
						getCommandStack().execute(c);
						int h = target.getFigure().getPreferredSize().height;
						target.getFigure()
								.setPreferredSize(new Dimension(w, h));
						target.getFigure().setSize(new Dimension(w, h));
					}
					List list = new ArrayList();
					list.add(ep);
					list.add(target);
					verticalAlignToFirstSelected(list);
				}
			}
			List targetLinks = ep.getTargetConnections();
			if (targetLinks.size() > 0) {
				for (int b = 0; b < targetLinks.size(); b++) {
					ConnectionNodeEditPart link = (ConnectionNodeEditPart) targetLinks.get(b);
					AbstractGraphicalEditPart source = (AbstractGraphicalEditPart) link
							.getSource();
					int w = source.getFigure().getBounds().width;
					int ew = ep.getFigure().getPreferredSize().width;
					if (w < ew) {
						Command c = new ChangeBoundsCommand(transactionDomain,((Node) ep
								.getModel()), source.getFigure().getBounds()
								.getLocation(), w,-1);
						getCommandStack().execute(c);
						int h = source.getFigure().getPreferredSize().height;
						source.getFigure()
								.setPreferredSize(new Dimension(w, h));
						source.getFigure().setSize(new Dimension(w, h));
					}
					List list = new ArrayList();
					list.add(ep);
					list.add(source);
					verticalAlignToFirstSelected(list);
				}
			}
			if (ep instanceof WorkProductCompositeEditPart || 
					ep instanceof RoleTaskCompositeEditPart) {
				List children = ep.getChildren();
				for (int d = 0; d < children.size(); d++) {
					AbstractGraphicalEditPart p = (AbstractGraphicalEditPart) children
							.get(d);
					cleanUp(p);
				}
			}
		}
	}

	/**
	 *  For auto-layout, need to adjust connection(or link) length, based on
	 *  connecting editparts(figures).
	 * 
	 */
	private boolean reduceLinkLength(AbstractGraphicalEditPart part) {
		boolean moved = false;
		// only move new WorkProductComposite elements
		if (part instanceof WorkProductCompositeEditPart) {
			AbstractGraphicalEditPart linkedPart = null;
			int position = PositionConstants.CENTER;
			// is this element linked to another item?
			if (part.getTargetConnections().size() > 0) {
				linkedPart = (AbstractGraphicalEditPart) ((ConnectionNodeEditPart) part
						.getTargetConnections().get(0)).getSource();
				position = PositionConstants.NORTH;
			} else if (part.getSourceConnections().size() > 0) {
				linkedPart = (AbstractGraphicalEditPart) ((ConnectionNodeEditPart) part
						.getSourceConnections().get(0)).getTarget();
				position = PositionConstants.SOUTH;
			}
			if (linkedPart != null) {
				if (!(linkedPart.getParent() instanceof DiagramEditPart)) {
					linkedPart = (AbstractGraphicalEditPart) linkedPart
							.getParent();
				}
				// get the part's position
				Rectangle partBounds = part.getFigure().getBounds();
				// get the linked part's position
				Rectangle linkedPartBounds = linkedPart.getFigure().getBounds();
				// determine in which direction is the linked part
				// int position = partBounds.getPosition(linkedPartBounds
				// .getLocation());
				// assume it is below us, so this part moves up
				int direction = -1;
				if ((position & PositionConstants.NORTH) == PositionConstants.NORTH
						|| (position & PositionConstants.CENTER) == PositionConstants.CENTER) {
					// above us, move down
					direction = 1;
				}
				// set new bounds for the part so that it sites just outside of
				// the linked part
				Rectangle testBounds = partBounds.getCopy();
				if (direction == 1) {
					testBounds.y = linkedPartBounds.y + linkedPartBounds.height
							+ VERT_PIX_PADDING;
				} else {
					testBounds.y = linkedPartBounds.y - VERT_PIX_PADDING
							- partBounds.height;
				}
				// set the new location for the part in the model
				Point np = new Point(testBounds.x, testBounds.y);
				part.getFigure().setLocation(np);
				Command c = new ChangeBoundsCommand(transactionDomain,(Node) part.getModel(), np,
						partBounds.width, -1);
				getCommandStack().execute(c);
			}
		}
		return moved;
	}

	/**
	 * Method to adjust the size of editpart.  
	 * 
	 */
	private void adjustSize(AbstractGraphicalEditPart ep) {
		if (ep instanceof WorkProductCompositeEditPart) {
			adjustSize((WorkProductCompositeEditPart) ep, 1);
		}
		else if (ep instanceof RoleTaskCompositeEditPart) {
			adjustSize((RoleTaskCompositeEditPart) ep, 50);
		}
	}

	protected final static int HORIZ_PIX_PADDING = 30;

	protected final static int VERT_PIX_PADDING = 20;

	/**
	 * Adjusts the size of ContainerEditPart to accomodate number of editparts in a row,
	 * push the remaining the editparts in two second row. 
	 * This is useful for {@link ToolbarLayout}
	 * 
	 */
	private void adjustSize(ShapeNodeEditPart ep, int horizCount) {
		int requiredWidth = 0;
		int rowMaxHeight = 0;
		int rows = 0;
		int widthSum = 0;
		int heightSum = 0;
		int column = 0;

		delta = 0;
		List children = ep.getChildren();
		IFigure parentFigure  = ep.getFigure();
		//TODO: need revisit transaction usage
		try{
		Transaction tx = ((InternalTransactionalEditingDomain)transactionDomain).startTransaction(false, Collections.EMPTY_MAP);
		for (int p = 0; p < children.size();) {
			if (column == 0) {
				rows++;
			}
			if (column < horizCount) {
				AbstractGraphicalEditPart child = (AbstractGraphicalEditPart) children
						.get(p);
				if(child instanceof RoleNodeEditPart || child instanceof TaskNodeEditPart){
					resetSize((ShapeNodeEditPart)child, parentFigure);
//					List list = new ArrayList();
//					list.add(child.getModel());
//					PersistViewsCommand cmd = new PersistViewsCommand(transactionDomain, list);
//					cmd.execute(new NullProgressMonitor(), null);
				}
				Dimension d = child.getFigure().getPreferredSize();
				widthSum += d.width;
				if (d.height > rowMaxHeight) {
					rowMaxHeight = d.height;
				}
				p++;
				column++;
			} else {
				if (widthSum > requiredWidth) {
					requiredWidth = widthSum;
				}
				heightSum += rowMaxHeight;
				rowMaxHeight = 0;
				widthSum = 0;
				column = 0;
			}
		}
		tx.commit();
		}catch(Exception e){
			
		}
		if (widthSum > requiredWidth) {
			requiredWidth = widthSum;
		}
		heightSum += rowMaxHeight;
		requiredWidth += HORIZ_PIX_PADDING
				* Math.min(horizCount, children.size());
		int requiredHeight = heightSum + (VERT_PIX_PADDING * rows);
		Command cmd = new ChangeBoundsCommand(transactionDomain, ((Node) ep.getModel()), ep
				.getFigure().getBounds().getLocation(), requiredWidth, requiredHeight);
		getCommandStack().execute(cmd);
		ep.getFigure().setPreferredSize(
				new Dimension(requiredWidth, requiredHeight));
		//((Node) ep.getModel()).setHeight(requiredHeight);
		ep.getFigure().setSize(new Dimension(requiredWidth, requiredHeight));
		ep.getFigure().getLayoutManager().layout(ep.getFigure());
		// after layout, check the bottom-most child figure against the bottom
		// of the container, adjust the bottom of the container is necessary
		if (ep instanceof WorkProductCompositeEditPart && children.size() > 0) {
			int totalCalculatedHeight = 0;
			for (int i=0; i < children.size(); i++)		{
				GraphicalEditPart part = (GraphicalEditPart) children.get(i);
				Node nodeObj = (Node) part.getModel();
				EObject o = nodeObj.getElement();				
				totalCalculatedHeight += part.getFigure().getPreferredSize().height;
			}
			int height = totalCalculatedHeight + 20 + 5 * (children.size() - 1);
			GraphicalEditPart last = (GraphicalEditPart) children.get(children
					.size() - 1);
			Point childBottom = last.getFigure().getBounds().getBottom();
			Point parentBottom = ep.getFigure().getBounds().getBottom();
			int delta = parentBottom.y - childBottom.y - (VERT_PIX_PADDING / 2);
//			ep.getFigure().setPreferredSize(
//					new Dimension(requiredWidth, requiredHeight - delta));
//			cmd = new ChangeBoundsCommand(transactionDomain, ((Node) ep.getModel()), ep
//					.getFigure().getBounds().getLocation(), -1, requiredHeight-delta);
//			getCommandStack().execute(cmd);
//			ep.getFigure().setSize(
//					new Dimension(requiredWidth, requiredHeight - delta));
			ep.getFigure().setPreferredSize(
					new Dimension(requiredWidth, height));
			cmd = new ChangeBoundsCommand(transactionDomain, ((Node) ep.getModel()), ep
					.getFigure().getBounds().getLocation(), -1, height);
			getCommandStack().execute(cmd);			
			ep.getFigure().setSize(
					new Dimension(requiredWidth, height));
		}
	}
	
	
	
	
	private void resetSize(ShapeNodeEditPart ep, IFigure parentFigure){
		
		Node model = (Node)ep.getModel();
		IGraphicalEditPart child = (IGraphicalEditPart)ep.getChildren().get(0);
		NodeFigure nodeFigure = (NodeFigure)ep.getFigure();
		List nodeFigureChildren =  nodeFigure.getChildren();
		IFigure childFigure = (IFigure)nodeFigureChildren.get(0);
		if(childFigure instanceof RectangleFigure){
			IFigure labelFigure = (IFigure)((RectangleFigure)childFigure).getChildren().get(0);
			if(labelFigure instanceof WrapLabel){
				Dimension figDimension = labelFigure.getSize();
				Dimension d = labelFigure.getPreferredSize();
				Point p = nodeFigure.getLocation().getCopy();
				p.x = p.x + delta;
				nodeFigure.setLocation(p);
				if (d.width > nodeFigure.getSize().width) {
					// System.out.println("Node figure width: "+nodeFigure.getSize().width);
					// System.out.println("Text width: "+ d.width);
					delta += d.width - nodeFigure.getSize().width;
				}
				if(d.width > figDimension.width){
					int width = d.width;
					View view = ((View)child.getModel());
					ViewUtil.setStructuralFeatureValue(view,
							NotationPackage.eINSTANCE.getSize_Width(), new Integer(
									width));
					ViewUtil.setStructuralFeatureValue(model,
							NotationPackage.eINSTANCE.getSize_Width(), new Integer(
									width));
					nodeFigure.setPreferredSize(new Dimension(width, nodeFigure.getPreferredSize().height));
					if (childFigure.getPreferredSize().height > nodeFigure.getPreferredSize().height) 
						nodeFigure.setPreferredSize(new Dimension(width, childFigure.getPreferredSize().height));
					childFigure.setPreferredSize(new Dimension(width, childFigure.getPreferredSize().height));
					labelFigure.setPreferredSize(new Dimension(width, labelFigure.getPreferredSize().height));
					
					nodeFigure.setSize(new Dimension(width, nodeFigure.getPreferredSize().height));
					if (childFigure.getPreferredSize().height > nodeFigure.getPreferredSize().height) 
						nodeFigure.setPreferredSize(new Dimension(width, childFigure.getPreferredSize().height));
					childFigure.setSize(new Dimension(width, childFigure.getPreferredSize().height));
					labelFigure.setSize(new Dimension(width, labelFigure.getPreferredSize().height));

//					nodeFigure.setConstraint(childFigure, new Rectangle(nodeFigure.getLocation().x,
//							nodeFigure.getLocation().y, width, childFigure.getBounds().height));
					// Change the location 
//					System.out.println("child bounds before: " + nodeFigure.getBounds() +"delta: " + delta);					
				}				
			}
		}
	}
	

	protected void enumerateConnectedParts(AbstractGraphicalEditPart part,
			List<EditPart> connected) {
		// only add parts directly on the diagram surface
		if (!connected.contains(part)) {
			if (!(part.getParent() instanceof DiagramEditPart)) {
				if (!connected.contains(part.getParent())) {
					connected.add(part.getParent());
				}
			} else {
				connected.add(part);
			}
			AbstractGraphicalEditPart linkedPart = null;
			List children = part.getChildren();
			for (int x = 0; x < children.size(); x++) {
				AbstractGraphicalEditPart p = (AbstractGraphicalEditPart) children
						.get(x);
				enumerateConnectedParts(p, connected);
			}
			// is this element linked to another item?
			for (int x = 0; x < part.getTargetConnections().size(); x++) {
				linkedPart = (AbstractGraphicalEditPart) ((ConnectionNodeEditPart) part
						.getTargetConnections().get(x)).getSource();
				enumerateConnectedParts(linkedPart, connected);
			}
			for (int x = 0; x < part.getSourceConnections().size(); x++) {
				linkedPart = (AbstractGraphicalEditPart) ((ConnectionNodeEditPart) part
						.getSourceConnections().get(x)).getTarget();
				enumerateConnectedParts(linkedPart, connected);
			}
		}
	}

	/**
	 * This method finds single elements and groups of elements, and positions
	 * them starting from the top of the diagram and continuously down the page.
	 */
	private void packDiagram() {
		ActivityDetailDiagramEditPart diagram = (ActivityDetailDiagramEditPart) getGraphicalViewer()
				.getContents();
		// start at the top of the diagram
		// Dimension diagramSize = diagram.getFigure().getSize();
		// no new shapes can be moved above this y-axis value
		int reserved = 0;
		// these are elements without connections
		List<EditPart> stragglers = new ArrayList<EditPart>();
		// these are groups of elements connected with links
		List<List> groups = new ArrayList<List>();
		// these are top level parts on the diagram that have been added
		List diagramParts = diagram.getRecentlyAddedParts();
		for (int x = 0; x < diagramParts.size(); x++) {
			AbstractGraphicalEditPart p = (AbstractGraphicalEditPart) diagramParts
					.get(x);
			boolean found = false;
			for (int g = 0; g < groups.size() && found == false; g++) {
				List group = (List) groups.get(g);
				if (group.contains(p)) {
					found = true;
					break;
				}
			}
			if (!found) {
				List<EditPart> connected = new ArrayList<EditPart>();
				enumerateConnectedParts(p, connected);
				if (connected.size() == 1) {
					stragglers.add(p);
				} else {
					groups.add(connected);
				}
			}
		}
		// process each group closest to the top (min y value)
		while (groups.size() > 0) {
			List top = (List) groups.get(0);
			int topY = getVerticalMinPart(top).getFigure().getBounds().y;
			for (int x = 0; x < groups.size(); x++) {
				List g = (List) groups.get(x);
				int y = getVerticalMinPart(g).getFigure().getBounds().y;
				if (y < topY) {
					top = g;
					topY = y;
				}
			}
			int limit = reserved + 1;
			int verticalDelta = limit - topY;
			int leftX = getHorizontalMinPart(top).getFigure().getBounds().x;
			int horizontalDelta = HORIZ_PIX_PADDING - leftX;
			for (int x = 0; x < top.size(); x++) {
				AbstractGraphicalEditPart p = (AbstractGraphicalEditPart) top
						.get(x);
				Rectangle bounds = p.getFigure().getBounds();
				ChangeBoundsCommand cbc = new ChangeBoundsCommand(transactionDomain,(Node) p
						.getModel(), new Point(bounds.x + horizontalDelta,
						bounds.y + verticalDelta), bounds.width, -1);
				getCommandStack().execute(cbc);
				p.getFigure().setLocation(
						new Point(bounds.x + horizontalDelta, bounds.y
								+ verticalDelta));
				p.refresh();
			}
			int bottomY = getVerticalMaxPart(top).getFigure().getBounds()
					.bottom();
			reserved = bottomY + (VERT_PIX_PADDING * 2);
			groups.remove(top);
		}
		int next = HORIZ_PIX_PADDING;
		for (int x = 0; x < stragglers.size(); x++) {
			int limit = reserved + 1;
			AbstractGraphicalEditPart p = (AbstractGraphicalEditPart) stragglers
					.get(x);
			Rectangle bounds = p.getFigure().getBounds();
			ChangeBoundsCommand cbc = new ChangeBoundsCommand(transactionDomain, (Node) p
					.getModel(), new Point(next, limit), bounds.width, -1);
			getCommandStack().execute(cbc);
			p.getFigure().setLocation(new Point(next, limit));
			p.refresh();
			next += bounds.width + HORIZ_PIX_PADDING;
		}
	}

	private AbstractGraphicalEditPart getVerticalMinPart(List parts) {
		AbstractGraphicalEditPart rc = parts.size() > 0 ? (AbstractGraphicalEditPart) parts
				.get(0)
				: null;
		for (int x = 0; x < parts.size(); x++) {
			AbstractGraphicalEditPart p = (AbstractGraphicalEditPart) parts
					.get(x);
			if (p.getFigure().getBounds().y < rc.getFigure().getBounds().y) {
				rc = p;
			}
		}
		return rc;
	}

	private AbstractGraphicalEditPart getHorizontalMinPart(List parts) {
		AbstractGraphicalEditPart rc = parts.size() > 0 ? (AbstractGraphicalEditPart) parts
				.get(0)
				: null;
		for (int x = 0; x < parts.size(); x++) {
			AbstractGraphicalEditPart p = (AbstractGraphicalEditPart) parts
					.get(x);
			if (p.getFigure().getBounds().x < rc.getFigure().getBounds().x) {
				rc = p;
			}
		}
		return rc;
	}

	private AbstractGraphicalEditPart getVerticalMaxPart(List parts) {
		AbstractGraphicalEditPart rc = parts.size() > 0 ? (AbstractGraphicalEditPart) parts
				.get(0)
				: null;
		for (int x = 0; x < parts.size(); x++) {
			AbstractGraphicalEditPart p = (AbstractGraphicalEditPart) parts
					.get(x);
			if (p.getFigure().getBounds().bottom() > rc.getFigure().getBounds()
					.bottom()) {
				rc = p;
			}
		}
		return rc;
	}

	public void verticalAlignToFirstSelected(List editParts) {
		// get the selected items
		//		List editParts = getGraphicalViewer().getSelectedEditParts();
		AbstractGraphicalEditPart alignToPart = null;
		// find the first diagram part we can align to
		for (int a = 0; a < editParts.size(); a++) {
			if (editParts.get(a) instanceof ShapeNodeEditPart) {
				AbstractGraphicalEditPart ep = (AbstractGraphicalEditPart) editParts
						.get(a);
				if (alignToPart == null) {
					// remember it and stop
					alignToPart = ep;
					break;
				}
			}
		}
		// loop through the elements and set their new position
		for (int a = 0; a < editParts.size(); a++) {
			if (editParts.get(a) instanceof ShapeNodeEditPart) {
				AbstractGraphicalEditPart ep = (AbstractGraphicalEditPart) editParts
						.get(a);
				// we can skip the first one since this is an align-to
				if (ep != alignToPart) {
					// calculate the offset for centerline alignment
					Rectangle bounds = ep.getFigure().getBounds();
					Rectangle alignToPartBounds = alignToPart.getFigure().getBounds();					
					Dimension ps = alignToPart.getFigure().getPreferredSize();					
					int offset =  bounds.getCenter().x - alignToPartBounds.getCenter().x;
					// is this inside a container?
					if (!(ep.getParent() instanceof DiagramEditPart)) {
						// adjust the offset since we are moving the
						// container
						ep = (AbstractGraphicalEditPart) ep.getParent();
						Rectangle parentBounds = ep.getFigure().getBounds();
						offset += bounds.x - parentBounds.x;
						bounds = parentBounds;
					}
					// send the command to move the element
					org.eclipse.gef.commands.Command c = new ChangeBoundsCommand(
							transactionDomain, (Node) ep.getModel(), new Point(
									alignToPart.getFigure().getBounds().x
											- offset, bounds.y), bounds.width,
							-1);
					getCommandStack().execute(c);
					ep.getFigure().setLocation(
							new Point(alignToPart.getFigure().getBounds().x
									- offset, bounds.y));
					Point sp = alignToPart.getFigure().getBounds().getCenter();
					boolean connected = false;
					// check if the part is connected via source connections
					List list = ((ShapeNodeEditPart) alignToPart)
							.getSourceConnections();
					for (Iterator itor = list.iterator(); itor.hasNext();) {
						ConnectionNodeEditPart lp = (ConnectionNodeEditPart) itor
								.next();
						if (lp.getTarget().equals(ep)) {
							connected = true;
							break;
						}
					}
					// if not found above, check if it is connected via target connections
					if (!connected) {
						list = ((ShapeNodeEditPart) alignToPart)
								.getTargetConnections();
						for (Iterator itor = list.iterator(); itor.hasNext();) {
							ConnectionNodeEditPart lp = (ConnectionNodeEditPart) itor
									.next();
							if (lp.getSource().equals(ep)) {
								connected = true;
								break;
							}
						}
					}
					Point p = new Point();
					p.x = ((Integer) ViewUtil.getStructuralFeatureValue(
							(View) ep.getModel(), NotationPackage.eINSTANCE
									.getLocation_X())).intValue();
					p.y = ((Integer) ViewUtil.getStructuralFeatureValue(
							(View) ep.getModel(), NotationPackage.eINSTANCE
									.getLocation_Y())).intValue();
					Point tp = p.getCopy();
					Point centerPoint = ep.getFigure().getBounds().getCenter();
					int delta = sp.x - centerPoint.x;
					tp.x = tp.x + delta;
					c = new ChangeBoundsCommand(transactionDomain, (Node) ep
							.getModel(), tp, ep.getFigure().getBounds().width,
							-1);
					getCommandStack().execute(c);
					ep.getFigure().setLocation(new Point(tp.x, tp.y));
				}
			}
		}
	}
	

	/**
	 * Return height of text of th enode
	 * 
	 * @param node
	 * @return
	 */
	public static Dimension getTextDimension(String text) {
		if (text != null){
			Font f = DiagramConstants.DEFAULT_FONT;
			Dimension d = FigureUtilities.getTextExtents(text, f);

			if (d != null)
				return d;
		}
		return null;
	}
	
	private void anyObliqueLinks(ActivityDetailDiagramEditPart dep,
			List recentlyAddedChildren) {
		List children = dep.getChildren();
		for (Iterator iterator = children.iterator(); iterator.hasNext();) {
			IGraphicalEditPart part = (IGraphicalEditPart) iterator.next();
			if(part instanceof RoleTaskCompositeEditPart){
				List grandChildren = ((RoleTaskCompositeEditPart)part).getChildren();
				for (Iterator iterator2 = grandChildren.iterator(); iterator2
						.hasNext();) {
					ShapeNodeEditPart child = (ShapeNodeEditPart) iterator2
							.next();
					if (child instanceof TaskNodeEditPart) {
						Point p = child.getFigure().getBounds().getCenter();
						List connected = child.getTargetConnections();
						if (!connected.isEmpty()) {
							AbstractGraphicalEditPart linkedPart = (AbstractGraphicalEditPart) ((ConnectionNodeEditPart) connected
									.get(0)).getSource();
							Point pt = linkedPart.getFigure().getBounds()
									.getCenter();
							if (p.x != pt.x) {
								if (!recentlyAddedChildren.contains(part)) {
									recentlyAddedChildren.add(part);
								}
							}
						}
						connected = child.getSourceConnections();
						if (!connected.isEmpty()) {
							AbstractGraphicalEditPart linkedPart = (AbstractGraphicalEditPart) ((ConnectionNodeEditPart)connected
									.get(0)).getSource();
							Point ps = linkedPart.getFigure().getBounds()
									.getCenter();
							if (p.x != ps.x) {
								if (!recentlyAddedChildren.contains(part)) {
									recentlyAddedChildren.add(part);
								}
							}
						}
					}
				}
			}
		}
		
	}
}
