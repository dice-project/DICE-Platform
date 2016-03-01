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
package org.eclipse.epf.diagram.core.actions;

import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.transaction.impl.InternalTransactionalEditingDomain;
import org.eclipse.epf.diagram.core.DiagramCoreResources;
import org.eclipse.epf.diagram.core.commands.ChangeBoundsCommand;
import org.eclipse.epf.diagram.core.commands.ReconnectLinkCommand;
import org.eclipse.epf.diagram.core.util.DiagramCoreUtil;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.actions.ActionIds;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;

/**
 * Service class to allow create and register common actions to the action
 * register. Code is migrate from old method of diagramming.
 * 
 * @author Shashidhar Kannoori
 * @since 1.2
 */
public class DiagramActionsService {

	public static final String ALIGN_HORZ_AVERAGE = "align_horizontal_to_average"; //$NON-NLS-1$

	public static final String ALIGN_HORZ_FIRST_SELECTED = "align_horizontal_to_first_selection"; //$NON-NLS-1$

	public static final String ALIGN_VERT_AVERAGE = "align_vertical_to_average"; //$NON-NLS-1$

	public static final String ALIGN_VERT_FIRST_SELECTED = "align_vertical_to_first_selection"; //$NON-NLS-1$

	private DefaultEditDomain editDomain;

	private GraphicalViewer graphicalViewer;

	private ActionRegistry actionRegistry;
	
	private InternalTransactionalEditingDomain transactionDomain;

	/**
	 * Constructor 
	 * @param graphicalViewer {@link GraphicalViewer}
	 * @param editDomain 	{@link DefaultEditDomain}
	 * @param actionRegistry 	{@link ActionRegistry}
	 */
	public DiagramActionsService(InternalTransactionalEditingDomain transactionDomain, GraphicalViewer graphicalViewer,
			DefaultEditDomain editDomain, ActionRegistry actionRegistry) {
		this.transactionDomain = transactionDomain;
		this.graphicalViewer = graphicalViewer;
		this.editDomain = editDomain;
		this.actionRegistry = actionRegistry;
	}

	/**
	 * Sets the GraphicalViewer.
	 */
	public void setGraphicalViewer(GraphicalViewer graphicalViewer) {
		this.graphicalViewer = graphicalViewer;
	}

	/**
	 * returns GraphicalViewer
	 */
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

	protected ActionRegistry getActionRegistry() {
		return actionRegistry;
	}

	/**
	 * Register HorizontalAlignAverageAction action. 
	 * HorizontalAignAverage Action aligns selected editparts to horizontal average.  
	 */
	public IAction registerHorizontalAlignAverageAction() {
		// align horizontally to average y-value of all nodes
		IAction hAlignAverageAction = new Action(
				DiagramCoreResources.AbstractDiagramEditor_hAlignAverageAction_text) { //$NON-NLS-1$
			public void run() {
				horizAlignToAverageSelected();
			}

			public String getId() {
				return ALIGN_HORZ_AVERAGE;
			}
		};
		getActionRegistry().registerAction(hAlignAverageAction);

		return hAlignAverageAction;
	}
	
	/**
	 * Register HorizontalAlignFirstSelectedAction action. 
	 * HorizontalAlignFirstSelectedAction aligns selected editparts with 
	 * respect to first selected editpart. 
	 */

	public IAction registerHorizontalAlignFirstSelectedAction() {
		// align horizontally to y-value of first selected node
		IAction hAlignFirstSelectedAction = new Action(
				DiagramCoreResources.AbstractDiagramEditor_hAlignFirstSelectedAction_text) { //$NON-NLS-1$
			public void run() {
				horzAlignToFirstSelected();
			}

			public String getId() {
				return ALIGN_HORZ_FIRST_SELECTED;
			}
		};
		getActionRegistry().registerAction(hAlignFirstSelectedAction);

		return hAlignFirstSelectedAction;
	}
	
	/**
	 * Register vertialAlignAverageAction action. 
	 * vertialAlignAverageAction aligns selected editparts vertically w.r.t average.
	 * 
	 */
	
	public IAction registerVerticalAlignAverageAction() {
		// align vertically to average x-value of all selected nodes
		IAction vAlignAverageAction = new Action(
				DiagramCoreResources.AbstractDiagramEditor_vAlignAverageAction_text) { //$NON-NLS-1$
			public void run() {
				verticalAlignToAverageSelected();
			}

			public String getId() {
				return ALIGN_VERT_AVERAGE;
			}
		};
		getActionRegistry().registerAction(vAlignAverageAction);

		return vAlignAverageAction;
	}

	/**
	 * Register vertialAlignFirstSelectedAction action. 
	 * vertialAlignFirstSelectedAction aligns selected editparts vertically w.r.t first selected.
	 * 
	 */

	public IAction registerVerticalAlignFirstSelectedAction() {
		// align vertically to x-value of first selected node
		IAction vAlignFirstSelectedAction = new Action(
				DiagramCoreResources.AbstractDiagramEditor_vAlignFirstSelectedAction_text) { //$NON-NLS-1$
			public void run() {
				verticalAlignToFirstSelected();
			}

			public String getId() {
				return ALIGN_VERT_FIRST_SELECTED;
			}
		};
		getActionRegistry().registerAction(vAlignFirstSelectedAction);

		return vAlignFirstSelectedAction;
	}

	// /////////////////////////////////////////////////////////////////////////////////////////////////
	// action methods
	// /////////////////////////////////////////////////////////////////////////////////////////////////
	
	/** 
	 * Action implementation method for horizontalAlignFirsteSElectedAction.
	 * 
	 */
	public void horzAlignToFirstSelected() {
		List editParts = getGraphicalViewer().getSelectedEditParts();
		AbstractGraphicalEditPart firstPart = null;
		// loop through all selected items, remember the y-value of the
		// first node,
		// set the following nodes to the same y-value
		for (int a = 0; a < editParts.size(); a++) {
			if (editParts.get(a) instanceof ShapeNodeEditPart) {
				AbstractGraphicalEditPart ep = (AbstractGraphicalEditPart) editParts
						.get(a);
				// remember the first part we move
				if (firstPart == null) {
					firstPart = ep;
				} else {
					// calculate the offset for centerline alignment
					Rectangle bounds = ep.getFigure().getBounds();
					int offset = (int) ((double) (bounds.height - firstPart
							.getFigure().getBounds().height) / 2.0);
					// is this node in a container?
					if (!(ep.getParent() instanceof DiagramEditPart)) {
						// need to move the container, adjust the offset
						ep = (AbstractGraphicalEditPart) ep.getParent();
						Rectangle parentBounds = ep.getFigure().getBounds();
						offset += bounds.y - parentBounds.y;
						bounds = parentBounds;
					}
					// send the command to move the node
					org.eclipse.gef.commands.Command c = new ChangeBoundsCommand(transactionDomain,
							(Node) ep.getModel(), new Point(bounds.x, firstPart
									.getFigure().getBounds().y
									- offset), bounds.width, -1);
					getCommandStack().execute(c);
				}
			}
		}
	}
	
	/** 
	 * Action implementation method for horizontalAlignAverageAction.
	 * 
	 */
	public void horizAlignToAverageSelected() {
		List editParts = getGraphicalViewer().getSelectedEditParts();
		AbstractGraphicalEditPart firstPart = null;
		int total = 0;
		int ysum = 0;
		for (int a = 0; a < editParts.size(); a++) {
			if (editParts.get(a) instanceof ShapeNodeEditPart) {
				AbstractGraphicalEditPart ep = (AbstractGraphicalEditPart) editParts
						.get(a);
				// remember the first part we move
				if (firstPart == null) {
					firstPart = ep;
				}
				ysum += ep.getFigure().getBounds().y;
				total++;
			}
		}
		// calculate the average y-value for all nodes
		int yave = (int) ((double) ysum / (double) total);
		// loop through all the nodes again and set them
		for (int a = 0; a < editParts.size(); a++) {
			if (editParts.get(a) instanceof ShapeNodeEditPart) {
				AbstractGraphicalEditPart ep = (AbstractGraphicalEditPart) editParts
						.get(a);
				// calculate the height difference, so that elements are aligned
				// through the centerline
				Rectangle bounds = ep.getFigure().getBounds();
				int offset = (int) ((double) (bounds.height - firstPart
						.getFigure().getBounds().height) / 2.0);
				// is this node inside a container?
				if (!(ep.getParent() instanceof DiagramEditPart)) {
					// we need to move the container, adjust the offset
					ep = (AbstractGraphicalEditPart) ep.getParent();
					Rectangle parentBounds = ep.getFigure().getBounds();
					offset += bounds.y - parentBounds.y;
					bounds = parentBounds;
				}
				// send the command to move the node
				org.eclipse.gef.commands.Command c = new ChangeBoundsCommand(transactionDomain,
						(Node) ep.getModel(),
						new Point(bounds.x, yave - offset), bounds.width, -1);
				getCommandStack().execute(c);
			}
		}

	}
	/** 
	 * Action implementation method for verticalAlignToAverageAction.
	 * 
	 */

	public void verticalAlignToAverageSelected() {
		List editParts = getGraphicalViewer().getSelectedEditParts();
		AbstractGraphicalEditPart firstPart = null;
		int total = 0;
		int xsum = 0;
		// loop through the selected parts
		for (int a = 0; a < editParts.size(); a++) {
			// we are only interested in nodes and containers
			if (editParts.get(a) instanceof ShapeNodeEditPart) {
				AbstractGraphicalEditPart ep = (AbstractGraphicalEditPart) editParts
						.get(a);
				if (firstPart == null) {
					firstPart = ep;
				}
				// sum the x coordinates of each element
				xsum += ep.getFigure().getBounds().x;
				// remember how many selected elements applied to this operation
				total++;
			}
		}
		// calculate the average x-value for all nodes
		int xave = (int) ((double) xsum / (double) total);
		// loop through all the nodes again and set them
		for (int a = 0; a < editParts.size(); a++) {
			if (editParts.get(a) instanceof ShapeNodeEditPart) {
				AbstractGraphicalEditPart ep = (AbstractGraphicalEditPart) editParts
						.get(a);
				// calculate the offset for centerline alignment
				Rectangle bounds = ep.getFigure().getBounds();
				int offset = (int) ((double) (bounds.width - firstPart
						.getFigure().getBounds().width) / 2.0);
				// are we inside a container?
				if (!(ep.getParent() instanceof DiagramEditPart)) {
					// adjust the offset since we have to move the container
					ep = (AbstractGraphicalEditPart) ep.getParent();
					Rectangle parentBounds = ep.getFigure().getBounds();
					offset += bounds.x - parentBounds.x;
					bounds = parentBounds;
				}
				// send the command to move the element
				org.eclipse.gef.commands.Command c = new ChangeBoundsCommand(transactionDomain,
						(Node) ep.getModel(),
						new Point(xave - offset, bounds.y), bounds.width, -1);
				getCommandStack().execute(c);
			}
		}

	}
	/** 
	 * Action implementation method for verticalAlignToFirstSelectedAction.
	 * 
	 */

	public void verticalAlignToFirstSelected() {
		// get the selected items
		List editParts = getGraphicalViewer().getSelectedEditParts();
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
				if (ep != alignToPart
						&& !(DiagramCoreUtil.isSyncBar(alignToPart))) {
					// calculate the offset for centerline alignment
					if (!(DiagramCoreUtil.isSyncBar(ep))) {
						Rectangle bounds = ep.getFigure().getBounds();
						int offset = (int) ((double) (bounds.width - alignToPart
								.getFigure().getBounds().width) / 2.0);
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
						org.eclipse.gef.commands.Command c = new ChangeBoundsCommand(transactionDomain,
								(Node) ep.getModel(), new Point(alignToPart
										.getFigure().getBounds().x
										- offset, bounds.y), bounds.width, -1);
						getCommandStack().execute(c);
						ep.getFigure().setLocation(
								new Point(alignToPart.getFigure().getBounds().x
										- offset, bounds.y));
					} else {
						Point tp = ep.getFigure().getBounds().getCenter();
						boolean connected = false;
						boolean source = false;
						Point endPoint = null;
						ConnectionNodeEditPart lp = null;
						// check target connections
						List list = ((ShapeNodeEditPart) ep).getTargetConnections();
						for (Iterator itor = list.iterator(); itor.hasNext();) {
							lp = (ConnectionNodeEditPart) itor.next();
							if (lp.getSource().equals(alignToPart)) {
								connected = true;
								break;
							}
						}
						// check source connections if not found in target connections
						if (!connected) {
							list = ((ShapeNodeEditPart) ep).getSourceConnections();
							for (Iterator itor = list.iterator(); itor
									.hasNext();) {
								lp = (ConnectionNodeEditPart) itor.next();
								if (lp.getTarget().equals(alignToPart)) {
									connected = true;
									source = true;
									break;
								}
							}
						}
						if (connected && (alignToPart instanceof ShapeNodeEditPart)) {
									Point centerPoint = alignToPart.getFigure()
											.getBounds().getCenter();
									int delta = centerPoint.x - tp.x;
							endPoint.x += delta;
									ReconnectLinkCommand c = new ReconnectLinkCommand(transactionDomain,
									(Edge) lp.getModel(), (Node) ep.getModel(),
									source);
							c.setEndPoint(endPoint);
									getCommandStack().execute(c);
						}

					}
				}
				// If first selected edit part is syncbarnodeeditpart, need to
				// handle differently
				if (ep != alignToPart
						&& !(DiagramCoreUtil.isSyncBar(alignToPart))) {

					Point sp = alignToPart.getFigure().getBounds()
							.getCenter();
					boolean connected = false;
					// check if the part is connected via source connections
					List list = ((ShapeNodeEditPart) alignToPart)
							.getSourceConnections();
					for (Iterator itor = list.iterator(); itor.hasNext();) {
						ConnectionNodeEditPart lp = (ConnectionNodeEditPart) itor.next();
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
							ConnectionNodeEditPart lp = (ConnectionNodeEditPart) itor.next();
							if (lp.getSource().equals(ep)) {
								connected = true;
								break;
							}
						}
					}
					if (DiagramCoreUtil.isSyncBar(ep)) {
						Point p = new Point();
						p.x = ((Integer) ViewUtil.getStructuralFeatureValue((View)ep.getModel(),
								NotationPackage.eINSTANCE.getLocation_X())).intValue();
						p.y = ((Integer) ViewUtil.getStructuralFeatureValue((View)ep.getModel(),
								NotationPackage.eINSTANCE.getLocation_Y())).intValue();
						Point tp = p.getCopy();
						Point alignToCenter = alignToPart.getFigure().getBounds().getCenter();
						Point centerPoint = ep.getFigure().getBounds().getCenter();
						int delta = alignToCenter.x - centerPoint.x;
						tp.x = tp.x + delta;
						org.eclipse.gef.commands.Command c = new ChangeBoundsCommand(transactionDomain,
								(Node) ep.getModel(), tp, ep.getFigure()
										.getBounds().width, -1);
						getCommandStack().execute(c);
						ep.getFigure().setLocation(new Point(tp.x, tp.y));
					} else if (ep instanceof ShapeNodeEditPart) {
						Point p = new Point();
						p.x = ((Integer) ViewUtil.getStructuralFeatureValue((View)ep.getModel(),
								NotationPackage.eINSTANCE.getLocation_X())).intValue();
						p.y = ((Integer) ViewUtil.getStructuralFeatureValue((View)ep.getModel(),
								NotationPackage.eINSTANCE.getLocation_Y())).intValue();
						Point tp = p.getCopy();
						Point centerPoint = ep.getFigure().getBounds()
								.getCenter();
						int delta = sp.x - centerPoint.x;
						tp.x = tp.x + delta;
						org.eclipse.gef.commands.Command c = new ChangeBoundsCommand(transactionDomain,
								(Node) ep.getModel(), tp, ep.getFigure()
										.getBounds().width, -1);
						getCommandStack().execute(c);
						ep.getFigure().setLocation(new Point(tp.x, tp.y));
					}
				}
			}
		}
	}
	
	public void createAlignMenu(IMenuManager menu, boolean canModify) {
		// create a new menu manager for the cascading menu
		
		IContributionItem formatMenu = menu.find(ActionIds.MENU_FORMAT);
		
		MenuManager alignMenuManager = null;
		if (alignMenuManager == null) {
			alignMenuManager = new MenuManager(
					DiagramCoreResources.AbstractDiagramEditor_alignMenu_text) { //$NON-NLS-1$

				public boolean isEnabled() {
					int total = 0;
					// enable the menu only if 2 or more nodes or node
					// containers
					// are selected
					List editParts = graphicalViewer.getSelectedEditParts();
					// need at least two things selected to align
					if (editParts.size() > 1) {
						for (int a = 0; a < editParts.size(); a++) {
							EditPart editPart = (EditPart) editParts.get(a);
							// we can align nodes and containers
							if (editPart instanceof ShapeNodeEditPart) {
								// add up the elements we need, there may be
								// more
								// elements selected (links, etc.)
								total++;
								if (total > 1) {
									// we only need to know there is more than
									// 1, so
									// we can stop here
									break;
								}
							}
						}
					}
					return total > 1;
				}
			};
		}
		if (formatMenu != null && formatMenu instanceof IMenuManager 
				&& alignMenuManager.isEnabled() && canModify) {
			IContributionItem item =  ((IMenuManager)formatMenu).find(ActionIds.MENU_ALIGN);
			if(item != null && item instanceof IMenuManager){
				IMenuManager alignMenu = (IMenuManager)item;
				alignMenu.add(getActionRegistry().getAction(
						DiagramActionsService.ALIGN_HORZ_AVERAGE));
				alignMenu.add(getActionRegistry().getAction(
						DiagramActionsService.ALIGN_HORZ_FIRST_SELECTED));
				alignMenu.add(getActionRegistry().getAction(
						DiagramActionsService.ALIGN_VERT_AVERAGE));
				alignMenu.add(getActionRegistry().getAction(
						DiagramActionsService.ALIGN_VERT_FIRST_SELECTED));
			}
		}else{
			alignMenuManager.add(getActionRegistry().getAction(
					DiagramActionsService.ALIGN_HORZ_AVERAGE));
			alignMenuManager.add(getActionRegistry().getAction(
					DiagramActionsService.ALIGN_HORZ_FIRST_SELECTED));
			alignMenuManager.add(getActionRegistry().getAction(
					DiagramActionsService.ALIGN_VERT_AVERAGE));
			alignMenuManager.add(getActionRegistry().getAction(
					DiagramActionsService.ALIGN_VERT_FIRST_SELECTED));
			if (alignMenuManager.isEnabled() && canModify) {
				menu.appendToGroup(GEFActionConstants.MB_ADDITIONS,
								new Separator());
				menu.appendToGroup(GEFActionConstants.MB_ADDITIONS, alignMenuManager);
				menu.appendToGroup(GEFActionConstants.MB_ADDITIONS,
								new Separator());
			}
		}
	}
	
}
