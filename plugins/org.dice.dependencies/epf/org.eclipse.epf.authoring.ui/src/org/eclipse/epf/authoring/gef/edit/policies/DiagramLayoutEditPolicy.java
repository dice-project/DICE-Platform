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
package org.eclipse.epf.authoring.gef.edit.policies;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.AbsoluteBendpoint;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.epf.authoring.gef.commands.ChangeBoundsCommand;
import org.eclipse.epf.authoring.gef.commands.ChangeHeightCommand;
import org.eclipse.epf.authoring.gef.commands.CreateNodeCommand;
import org.eclipse.epf.authoring.gef.commands.MoveBendpointCommand;
import org.eclipse.epf.authoring.gef.edit.ActivityDetailDiagramEditPart;
import org.eclipse.epf.authoring.gef.edit.DiagramEditPart;
import org.eclipse.epf.authoring.gef.edit.LinkEditPart;
import org.eclipse.epf.authoring.gef.edit.RoleTaskCompositeEditPart;
import org.eclipse.epf.authoring.gef.edit.WorkProductCompositeEditPart;
import org.eclipse.epf.diagram.model.ActivityDetailDiagram;
import org.eclipse.epf.diagram.model.Diagram;
import org.eclipse.epf.diagram.model.Link;
import org.eclipse.epf.diagram.model.Node;
import org.eclipse.epf.diagram.model.util.GraphicalDataHelper;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.editpolicies.ConstrainedLayoutEditPolicy;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;

/**
 * An EditPolicy for use with <code>Figures</code> in {@link XYLayout} of diagram. The constraint for
 * XYLayout is a {@link org.eclipse.draw2d.geometry.Rectangle}. Implements the changeconstraint, delete,
 * resize commands of {@link ConstrainedLayoutEditPolicy}
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class DiagramLayoutEditPolicy extends XYLayoutEditPolicy {

	protected Command createAddCommand(EditPart child, Object constraint) {
		return UnexecutableCommand.INSTANCE;
	}

	protected Command createChangeConstraintCommand(EditPart child,
			Object constraint) {
		return null;
	}

	protected Command createChangeConstraintCommand(
			ChangeBoundsRequest request, EditPart child, Object constraint) {
		
		// special for ADD
		EditPart parent = child.getParent();
		if(parent instanceof ActivityDetailDiagramEditPart){
			String autolayout = GraphicalDataHelper.getAutoLayoutFlag((ActivityDetailDiagram)(Node)parent.getModel());
			if(autolayout != null){
				GraphicalDataHelper.createProperty(((Node)parent.getModel()).getGraphNode(),
					GraphicalDataHelper.PROP_AUTO_LAYOUT, 
					GraphicalDataHelper.PROP_AUTO_LAYOUT_VALUE_FALSE);
			}
		}
		
		Node node = (Node) child.getModel();
		int directions = request.getResizeDirection();
		
		if ((directions & PositionConstants.NORTH_SOUTH) 
				== PositionConstants.SOUTH){
			Point newLocation = node.getLocation().getTranslated(
					request.getMoveDelta());
			int newHeight = request.getSizeDelta().height;
			if (newHeight != 0) {
				newHeight += ((GraphicalEditPart) child).getFigure().getBounds().height;
				if (newHeight < 10)
					return UnexecutableCommand.INSTANCE;
			} else
				newHeight = ((GraphicalEditPart) child).getFigure().getBounds().height;
			return new ChangeHeightCommand(node, newLocation, newHeight);
		}
		else{
			// Other directions are EAST WEST. 
			Point newLocation = node.getLocation().getTranslated(
					request.getMoveDelta());
			int newWidth = request.getSizeDelta().width;
			if (newWidth != 0) {
				newWidth += ((GraphicalEditPart) child).getFigure().getBounds().width;
				if (newWidth < 10)
					return UnexecutableCommand.INSTANCE;
			} else
				newWidth = ((GraphicalEditPart) child).getFigure().getBounds().width;
			return new ChangeBoundsCommand(node, newLocation, newWidth);
		} 
	}

	protected EditPolicy createChildEditPolicy(EditPart child) {
		ResizableEditPolicy childPolicy = new ResizableEditPolicy();
		if(child instanceof RoleTaskCompositeEditPart ||
				child instanceof WorkProductCompositeEditPart){
			childPolicy.setResizeDirections(PositionConstants.NSEW);
		}else{
			childPolicy.setResizeDirections(PositionConstants.EAST_WEST);
		}
		return childPolicy;
	}

	protected Command getCreateCommand(CreateRequest request) {
		if (request.getNewObject() instanceof Node) {
			Point loc = request.getLocation();
			getHostFigure().translateToRelative(loc);
			return new CreateNodeCommand((Node) request.getNewObject(),
					(Diagram) getHost().getModel(), loc);
		}
		return UnexecutableCommand.INSTANCE;
	}

	protected Command getDeleteDependantCommand(Request request) {
		return null;
	}

	protected Command getResizeChildrenCommand(ChangeBoundsRequest request) {
		CompoundCommand resize = new CompoundCommand();
		Command c;
		GraphicalEditPart child;
		List children = request.getEditParts();

		for (int i = 0; i < children.size(); i++) {
			child = (GraphicalEditPart) children.get(i);
			c = createChangeConstraintCommand(
					request,
					child,
					translateToModelConstraint(getConstraintFor(request, child)));
			resize.add(c);
			if (!child.getSourceConnections().isEmpty()) {
				List list = child.getSourceConnections();
				for (Iterator itor = list.iterator(); itor.hasNext();) {
					Object obj = itor.next();
					if (obj instanceof LinkEditPart) {
						EditPart rootedit = child.getParent();
						List sel = new ArrayList();
						if (rootedit instanceof DiagramEditPart) {
							sel = ((DiagramEditPart) rootedit).getViewer()
									.getSelectedEditParts();
						}
						EditPart part = ((LinkEditPart) obj).getTarget();
						if (sel.contains(part)) {
							Link link = (Link) ((LinkEditPart) obj).getModel();
							((DiagramEditPart) rootedit).getViewer()
									.appendSelection((LinkEditPart) obj);
							List blist = link.getBendpoints();
							for (int j = 0; j < blist.size(); j++) {
								AbsoluteBendpoint abpoint = (AbsoluteBendpoint) blist
										.get(j);
								Point deltaPoint = request.getMoveDelta();
								((LinkEditPart) obj).getFigure()
										.translateToAbsolute(deltaPoint);
								Point p = abpoint.getTranslated(request
										.getMoveDelta());
								Command bendcommand = new MoveBendpointCommand(
										link, p, j);
								resize.add(bendcommand);
							}
						}
					}
				}
			}
		}
		return resize;
	}

}