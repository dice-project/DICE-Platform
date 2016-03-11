/*******************************************************************************
 * Copyright (c) 2005, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial implementation
 *******************************************************************************/
package org.eclipse.epf.diagram.core.actions;

import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.AbsoluteBendpoint;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.epf.diagram.core.DiagramCoreResources;
import org.eclipse.epf.diagram.core.bridge.BridgeHelper;
import org.eclipse.epf.diagram.core.bridge.DiagramAdapter;
import org.eclipse.epf.diagram.core.commands.ChangeBoundsCommand;
import org.eclipse.epf.diagram.core.part.AbstractDiagramEditor;
import org.eclipse.epf.diagram.core.util.DiagramConstants;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction;
import org.eclipse.gmf.runtime.diagram.ui.commands.CommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.RelativeBendpoints;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;

/**
 * Action class to move diagram elements in diagram area using keyboard up, down, left, right keys.
 * Useful for Accessibility, and also does move bendpoints of control flow.
 * 
 * @author Shashidhar Kannoori
 */
public class AccessibilityMoveAction extends DiagramAction {

	public static final String MOVE_LEFT_ACTION = "move_left_action";		//$NON-NLS-1$
	public static final String MOVE_RIGHT_ACTION = "move_right_action";		//$NON-NLS-1$
	public static final String MOVE_DOWN_ACTION = "move_down_action";		//$NON-NLS-1$		
	public static final String MOVE_UP_ACTION = "move_up_action";			//$NON-NLS-1$
	
	private int direction;
	

	/**
	 * @param workbenchPage
	 */
	public AccessibilityMoveAction(IWorkbenchPage workbenchPage) {
		super(workbenchPage);
	}

	/**
	 * @param workbenchpart
	 */
	public AccessibilityMoveAction(IWorkbenchPart workbenchpart, int direction) {
		super(workbenchpart);
		this.direction = direction;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction#createTargetRequest()
	 */
	@Override
	protected Request createTargetRequest() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction#isSelectionListener()
	 */
	@Override
	protected boolean isSelectionListener() {
		return true;
	}

	@Override
	public void init() {
		super.init();
		setId(DiagramConstants.MOVE_ACTION);
		
	}
	
	public void setDirection(int direction){
		this.direction = direction;
	}
	
	@Override
	protected Command getCommand() {
		List list = getSelectedObjects();
		CompositeCommand cc = new CompositeCommand(DiagramCoreResources.AbstractDiagramEditor_MoveNode_label);
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object part = iterator.next();
			if(part instanceof GraphicalEditPart){
				Command c = move(direction, (GraphicalEditPart)part);
				if(c != null){
					cc.compose(new CommandProxy(c));
				}
			}
		}
		return cc.isEmpty() ? null : new ICommandProxy(cc.reduce());
	}
	
	public Command move(int direction, GraphicalEditPart part) {
		if (part.getModel() instanceof Node) {
			View view = (View) part.getModel();
			Object x = ViewUtil.getStructuralFeatureValue(view,
					NotationPackage.eINSTANCE.getLocation_X());
			Object y = ViewUtil.getStructuralFeatureValue(view,
					NotationPackage.eINSTANCE.getLocation_Y());
			Point point = new Point(((Integer) x).intValue(), ((Integer) y).intValue());
			int newx = point.x;
			int newy = point.y;
			int presslength = 1;
			switch (direction) {
			case PositionConstants.EAST:
				newx = point.x + presslength;
				break;
			case PositionConstants.SOUTH:
				newy += presslength;
				break;
			case PositionConstants.NORTH:
				newy -= presslength;
				break;
			case PositionConstants.WEST:
				newx -= presslength;
				break;
			default:
				break;
			}
			org.eclipse.gef.commands.Command c = new ChangeBoundsCommand(
					getEditingDomain(),
					view, new Point(newx, newy), -1, -1);

			return c;
		}
		return null;
	}

	public Command moveBendPoint(int direction, ConnectionEditPart part){
		// TODO- check adding more bendpoint, capture bendpoint
		// index on fly
		if (part.getModel() instanceof Edge) {
			Edge edge = (Edge) part.getModel();
			RelativeBendpoints bPoints = (RelativeBendpoints)edge.getBendpoints();
			List blist = bPoints.getPoints();
			int index = 0;
			if (blist != null && blist.size() > 0) {
				AbsoluteBendpoint point = (AbsoluteBendpoint) blist
						.get(index);
				int newx = point.x;
				int newy = point.y;
				int presslength = 5;
				switch (direction) {
				case 1:
					newx = point.x + presslength;
					break;
				case 2:
					newy += presslength;
					break;
				case 3:
					newy -= presslength;
					break;
				case 4:
					newx -= presslength;
					break;
				default:
					break;
				}
				//TODO: implement the movebendpoint command.
//				org.eclipse.gef.commands.Command c = new MoveBendpointCommand(
//						link, new Point(newx, newy), index);
//				getCommandStack().execute(c);
			}
		}
		return null;
	}
	public TransactionalEditingDomain getEditingDomain(){
		return ((AbstractDiagramEditor)getDiagramWorkbenchPart()).getEditingDomain();
	}
	
	@Override
	public String getId() {
		switch(direction){
		case PositionConstants.EAST:
			return MOVE_RIGHT_ACTION;
		case PositionConstants.SOUTH:
			return MOVE_DOWN_ACTION;
		case PositionConstants.NORTH:
			return MOVE_UP_ACTION;
		case PositionConstants.WEST:
			return MOVE_LEFT_ACTION;
		}
		return DiagramConstants.MOVE_ACTION;
	}
	
	@Override
	public boolean isEnabled() {
		if(TngUtil.isLocked(getOwningProcess()))
				return false;
		if(BridgeHelper.isReadOnly(((AbstractDiagramEditor)getDiagramWorkbenchPart()).getDiagram()))
				return false;
		if(getSelectedObjects().isEmpty()) return false;
		return super.isEnabled();
	}
	
	private org.eclipse.epf.uma.Process getOwningProcess(){
		IWorkbenchPart targetPart = getDiagramWorkbenchPart();
		if(targetPart != null){
			Diagram d = ((AbstractDiagramEditor)targetPart).getDiagram();
			DiagramAdapter adapter = BridgeHelper.getDiagramAdapter(d.getElement());
			if(adapter != null){
				return TngUtil.getOwningProcess(adapter.getActivity());
			}
		}
		return null;
	}
}
