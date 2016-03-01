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
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.epf.authoring.gef.commands.ChangeBoundsCommand;
import org.eclipse.epf.diagram.model.Node;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.StructuredSelection;

/**
 * Service class to allow updating diagram to a default layout. Most of the code
 * are moved from the Editor class.
 * 
 * @author Jinhua Xi
 * @since 1.0
 */
public class DiagramUpdateService {

	private DefaultEditDomain editDomain;

	private GraphicalViewer graphicalViewer;

	private ActionRegistry actionRegistry;

	public DiagramUpdateService(
			GraphicalViewer graphicalViewer, DefaultEditDomain editDomain,
			ActionRegistry actionRegistry) {
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
			if (!children.isEmpty()) {
				Comparator comparator = new Comparator() {

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
				dep.clearRecentlyAddedParts();
				getGraphicalViewer().setSelection(new StructuredSelection());
			}
		}
	}

	/*
	private void resetSizes(AbstractGraphicalEditPart ep, AbstractGraphicalEditPart parent) {
		if (!(ep instanceof WorkProductCompositeEditPart)) {
			List sourceLinks = ep.getSourceConnections();
			if (sourceLinks.size() > 0) {
				for (int b = 0; b < sourceLinks.size(); b++) {
					LinkEditPart link = (LinkEditPart) sourceLinks.get(b);
					AbstractGraphicalEditPart target = (AbstractGraphicalEditPart) link
							.getTarget();
					int w = target.getFigure().getBounds().width;
					int ew = ep.getFigure().getPreferredSize().width;
					if (w > ew) {
						Point location = ep.getFigure().getBounds()
						.getLocation();
						location.x+=(w-ew)/2;
						Command c = new ChangeBoundsCommand(((Node) ep
								.getModel()), ep.getFigure().getBounds()
								.getLocation(), w);
						getCommandStack().execute(c);
						int h = ep.getFigure().getPreferredSize().height;
						ep.getFigure()
								.setPreferredSize(new Dimension(w, h));
						ep.getFigure().setSize(new Dimension(w, h));
					}
					getGraphicalViewer()
							.setSelection(
									new StructuredSelection(new Object[] { ep,
											target }));
					IAction valign = getActionRegistry().getAction(
							DiagramActionService.ALIGN_VERT_FIRST_SELECTED);
					valign.run();
				}
			}
			List targetLinks = ep.getTargetConnections();
			if (targetLinks.size() > 0) {
				for (int b = 0; b < targetLinks.size(); b++) {
					LinkEditPart link = (LinkEditPart) targetLinks.get(b);
					AbstractGraphicalEditPart source = (AbstractGraphicalEditPart) link
							.getSource();
					int w = source.getFigure().getBounds().width;
					int ew = ep.getFigure().getPreferredSize().width;
					if (w > ew) {
						Command c = new ChangeBoundsCommand(((Node) ep
								.getModel()), ep.getFigure().getBounds()
								.getLocation(), w);
						getCommandStack().execute(c);
						int h = ep.getFigure().getPreferredSize().height;
						ep.getFigure()
								.setPreferredSize(new Dimension(w, h));
						ep.getFigure().setSize(new Dimension(w, h));
					}
					getGraphicalViewer()
							.setSelection(
									new StructuredSelection(new Object[] { ep,
											source }));
					IAction valign = getActionRegistry().getAction(
							DiagramActionService.ALIGN_VERT_FIRST_SELECTED);
					valign.run();
				}
			}
			parent.getFigure().getLayoutManager().layout(parent.getFigure());
			if (ep instanceof NodeContainerEditPart) {
				List children = ep.getChildren();
				for (int d = 0; d < children.size(); d++) {
					AbstractGraphicalEditPart p = (AbstractGraphicalEditPart) children
							.get(d);
					resetSizes(p, ep);
				}
			}
		}
	}
	*/

	/**
	 * This method does a vertical alignment of all nodes with links
	 */
	private void cleanUp(AbstractGraphicalEditPart ep) {
		if (!(ep instanceof WorkProductCompositeEditPart)) {
			List sourceLinks = ep.getSourceConnections();
			if (sourceLinks.size() > 0) {
				for (int b = 0; b < sourceLinks.size(); b++) {
					LinkEditPart link = (LinkEditPart) sourceLinks.get(b);
					AbstractGraphicalEditPart target = (AbstractGraphicalEditPart) link
							.getTarget();
					int w = target.getFigure().getBounds().width;
					int ew = ep.getFigure().getPreferredSize().width;
					if (w < ew) {
						Command c = new ChangeBoundsCommand(((Node) ep
								.getModel()), target.getFigure().getBounds()
								.getLocation(), w);
						getCommandStack().execute(c);
						int h = target.getFigure().getPreferredSize().height;
						target.getFigure()
								.setPreferredSize(new Dimension(w, h));
						target.getFigure().setSize(new Dimension(w, h));
					}
					getGraphicalViewer()
							.setSelection(
									new StructuredSelection(new Object[] { ep,
											target }));
					IAction valign = getActionRegistry().getAction(
							DiagramActionService.ALIGN_VERT_FIRST_SELECTED);
					valign.run();
				}
			}
			List targetLinks = ep.getTargetConnections();
			if (targetLinks.size() > 0) {
				for (int b = 0; b < targetLinks.size(); b++) {
					LinkEditPart link = (LinkEditPart) targetLinks.get(b);
					AbstractGraphicalEditPart source = (AbstractGraphicalEditPart) link
							.getSource();
					int w = source.getFigure().getBounds().width;
					int ew = ep.getFigure().getPreferredSize().width;
					if (w < ew) {
						Command c = new ChangeBoundsCommand(((Node) ep
								.getModel()), source.getFigure().getBounds()
								.getLocation(), w);
						getCommandStack().execute(c);
						int h = source.getFigure().getPreferredSize().height;
						source.getFigure()
								.setPreferredSize(new Dimension(w, h));
						source.getFigure().setSize(new Dimension(w, h));
					}
					getGraphicalViewer()
							.setSelection(
									new StructuredSelection(new Object[] { ep,
											source }));
					IAction valign = getActionRegistry().getAction(
							DiagramActionService.ALIGN_VERT_FIRST_SELECTED);
					valign.run();
				}
			}
			if (ep instanceof NodeContainerEditPart) {
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
				linkedPart = (AbstractGraphicalEditPart) ((LinkEditPart) part
						.getTargetConnections().get(0)).getSource();
				position = PositionConstants.NORTH;
			} else if (part.getSourceConnections().size() > 0) {
				linkedPart = (AbstractGraphicalEditPart) ((LinkEditPart) part
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
				Command c = new ChangeBoundsCommand((Node) part.getModel(), np,
						partBounds.width);
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
			adjustSize((NodeContainerEditPart) ep, 1);
		}
		// Below Code generic NodeContainerEditPart modification.
		else if (ep instanceof NodeContainerEditPart) {
			// This is to allow user customizable 
//			String count = AuthoringUIPlugin.getDefault().getPreferenceStore()
//							.getString(TemplateConstants.ADD_ROLE_TASKS_COUNT);
//			if(count != null || count != ""){
//				int i = Integer.parseInt(count);
//				if(i > 0){
//					adjustSize((NodeContainerEditPart) ep, i);
//				}
//			}else{
//				adjustSize((NodeContainerEditPart) ep, 50);
//			}
			adjustSize((NodeContainerEditPart) ep, 50);
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
	private void adjustSize(NodeContainerEditPart ep, int horizCount) {
		int requiredWidth = 0;
		int rowMaxHeight = 0;
		int rows = 0;
		int widthSum = 0;
		int heightSum = 0;
		int column = 0;
		//int originalWidth = ep.getFigure().getSize().width;
		List children = ep.getChildren();
		for (int p = 0; p < children.size();) {
			if (column == 0) {
				rows++;
			}
			if (column < horizCount) {
				AbstractGraphicalEditPart child = (AbstractGraphicalEditPart) children
						.get(p);
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
		if (widthSum > requiredWidth) {
			requiredWidth = widthSum;
		}
		heightSum += rowMaxHeight;
		requiredWidth += HORIZ_PIX_PADDING
				* Math.min(horizCount, children.size());
		int requiredHeight = heightSum + (VERT_PIX_PADDING * rows);
		Command cmd = new ChangeBoundsCommand(((Node) ep.getModel()), ep
				.getFigure().getBounds().getLocation(), requiredWidth);
		getCommandStack().execute(cmd);
		ep.getFigure().setPreferredSize(
				new Dimension(requiredWidth, requiredHeight));
		((Node) ep.getModel()).setHeight(requiredHeight);
		ep.getFigure().setSize(new Dimension(requiredWidth, requiredHeight));
			ep.getFigure().getLayoutManager().layout(ep.getFigure());
		// after layout, check the bottom-most child figure against the bottom
		// of the container, adjust the bottom of the container is necessary
		if (ep instanceof WorkProductCompositeEditPart && children.size() > 0) {
			GraphicalEditPart last = (GraphicalEditPart) children.get(children
					.size() - 1);
			Point childBottom = last.getFigure().getBounds().getBottom();
			Point parentBottom = ep.getFigure().getBounds().getBottom();
			int delta = parentBottom.y - childBottom.y - (VERT_PIX_PADDING / 2);
			ep.getFigure().setPreferredSize(
					new Dimension(requiredWidth, requiredHeight - delta));
			((Node) ep.getModel()).setHeight(requiredHeight - delta);
			ep.getFigure().setSize(
					new Dimension(requiredWidth, requiredHeight - delta));
		}
	}

	protected void enumerateConnectedParts(AbstractGraphicalEditPart part,
			List connected) {
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
				linkedPart = (AbstractGraphicalEditPart) ((LinkEditPart) part
						.getTargetConnections().get(x)).getSource();
				enumerateConnectedParts(linkedPart, connected);
			}
			for (int x = 0; x < part.getSourceConnections().size(); x++) {
				linkedPart = (AbstractGraphicalEditPart) ((LinkEditPart) part
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
		List stragglers = new ArrayList();
		// these are groups of elements connected with links
		List groups = new ArrayList();
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
				List connected = new ArrayList();
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
				ChangeBoundsCommand cbc = new ChangeBoundsCommand((Node) p
						.getModel(), new Point(bounds.x + horizontalDelta,
						bounds.y + verticalDelta), bounds.width);
				getCommandStack().execute(cbc);
				p.getFigure().setLocation(
						new Point(bounds.x + horizontalDelta, bounds.y
								+ verticalDelta));
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
			ChangeBoundsCommand cbc = new ChangeBoundsCommand((Node) p
					.getModel(), new Point(next, limit), bounds.width);
			getCommandStack().execute(cbc);
			p.getFigure().setLocation(new Point(next, limit));
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

}
