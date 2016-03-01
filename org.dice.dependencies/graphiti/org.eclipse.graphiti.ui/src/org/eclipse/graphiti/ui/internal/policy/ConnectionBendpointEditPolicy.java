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
package org.eclipse.graphiti.ui.internal.policy;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.AbsoluteBendpoint;
import org.eclipse.draw2d.Bendpoint;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.handles.BendpointHandle;
import org.eclipse.gef.requests.BendpointRequest;
import org.eclipse.graphiti.features.IAddBendpointFeature;
import org.eclipse.graphiti.features.IMoveBendpointFeature;
import org.eclipse.graphiti.features.IRemoveBendpointFeature;
import org.eclipse.graphiti.features.context.impl.AddBendpointContext;
import org.eclipse.graphiti.features.context.impl.MoveBendpointContext;
import org.eclipse.graphiti.features.context.impl.RemoveBendpointContext;
import org.eclipse.graphiti.internal.command.GenericFeatureCommandWithContext;
import org.eclipse.graphiti.mm.algorithms.styles.Point;
import org.eclipse.graphiti.mm.pictograms.FreeFormConnection;
import org.eclipse.graphiti.ui.internal.command.GefCommandWrapper;
import org.eclipse.graphiti.ui.internal.config.IConfigurationProvider;
import org.eclipse.graphiti.ui.internal.figures.GFPolylineConnection;
import org.eclipse.graphiti.ui.internal.util.draw2d.GFBendpointHandle;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class ConnectionBendpointEditPolicy extends BendpointEditPolicyFixed {

	/** The tolerance for the snap-to adjacent bendpoint feedback. */
	private static final int SNAP_TO_TOLERANCE = 15;

	public ConnectionBendpointEditPolicy(IConfigurationProvider configurationProvider) {
		super(configurationProvider);
	}

	@Override
	protected Command getCreateBendpointCommand(BendpointRequest request) {
		Command ret = null;

		Object model = request.getSource().getModel();
		if (model instanceof FreeFormConnection) {
			FreeFormConnection freeFormConnection = (FreeFormConnection) model;
			int bendpointIndex = request.getIndex();
			org.eclipse.draw2d.geometry.Point location = request.getLocation();

			// Enable snap-to-bendpoints support for adjacent bendpoints.
			snapToAdjacentBendpoints(request, location);

			// Need to call translateToRelative to make this work as seen at
			// http://www.koders.com/java/fid363E5D238D7C1D6660EBA03C7944827E823C6B42.aspx
			getConnection().translateToRelative(location);

			AddBendpointContext context = new AddBendpointContext(freeFormConnection, location.x, location.y, bendpointIndex);
			IAddBendpointFeature addBendpointFeature = getFeatureProvider().getAddBendpointFeature(context);
			if (addBendpointFeature != null) {
				ret = new GefCommandWrapper(new GenericFeatureCommandWithContext(addBendpointFeature, context), getConfigurationProvider()
						.getDiagramEditor().getEditingDomain());
			}
		}
		// workaround: no snapping to original position
		saveOriginalConstraint();
		return ret;
	}

	@Override
	protected Command getDeleteBendpointCommand(BendpointRequest request) {
		Command ret = null;

		Object model = request.getSource().getModel();
		if (model instanceof FreeFormConnection) {
			FreeFormConnection freeFormConnection = (FreeFormConnection) model;
			int bendpointIndex = request.getIndex();
			Point bendpoint = freeFormConnection.getBendpoints().get(bendpointIndex);
			RemoveBendpointContext context = new RemoveBendpointContext(freeFormConnection, bendpoint);
			context.setBendpointIndex(bendpointIndex);
			IRemoveBendpointFeature removeBendpointFeature = getFeatureProvider().getRemoveBendpointFeature(context);
			if (removeBendpointFeature != null) {
				ret = new GefCommandWrapper(new GenericFeatureCommandWithContext(removeBendpointFeature, context),
						getConfigurationProvider().getDiagramEditor().getEditingDomain());
			}
		}

		return ret;
	}

	@Override
	protected Command getMoveBendpointCommand(BendpointRequest request) {
		Command ret = null;

		ConnectionEditPart source = request.getSource();
		Object model = source.getModel();
		if (model instanceof FreeFormConnection) {
			FreeFormConnection freeFormConnection = (FreeFormConnection) model;
			int bendpointIndex = request.getIndex();
			Point bendpoint = freeFormConnection.getBendpoints().get(bendpointIndex);

			org.eclipse.draw2d.geometry.Point location = request.getLocation();

			// Enable snap-to-bendpoints support for adjacent bendpoints.
			snapToAdjacentBendpoints(request, location);

			// Need to call translateToRelative to make this work as seen at
			// http://www.koders.com/java/fid363E5D238D7C1D6660EBA03C7944827E823C6B42.aspx
			getConnection().translateToRelative(location);

			int requestX = location.x;
			int requestY = location.y;
			MoveBendpointContext context = new MoveBendpointContext(bendpoint);
			context.setBendpointIndex(bendpointIndex);
			context.setConnection(freeFormConnection);
			context.setX(requestX);
			context.setY(requestY);

			IMoveBendpointFeature moveBendpointFeature = getFeatureProvider().getMoveBendpointFeature(context);
			if (moveBendpointFeature != null) {
				ret = new GefCommandWrapper(new GenericFeatureCommandWithContext(moveBendpointFeature, context), getConfigurationProvider()
						.getDiagramEditor().getEditingDomain());
			}
		}

		// workaround: no snapping to original position
		saveOriginalConstraint();

		return ret;
	}

	@Override
	protected void showCreateBendpointFeedback(BendpointRequest request) {
		// ADDED: keep points and bendpoints in sync
		refreshConnectionBendpoints();

		org.eclipse.draw2d.geometry.Point p = new org.eclipse.draw2d.geometry.Point(request.getLocation());
		List<Bendpoint> constraint;
		getConnection().translateToRelative(p);
		Bendpoint bp = new AbsoluteBendpoint(p);

		if (originalConstraint == null) {
			saveOriginalConstraint();
			constraint = getConnectionRoutingConstraint();
			constraint.add(request.getIndex(), bp);
		} else {
			constraint = getConnectionRoutingConstraint();
		}

		// ADDED: enable snap-to adjacent bendpoints.
		snapToAdjacentBendpoints(request, bp.getLocation());

		// <sw 23022009> add: "request.getIndex() < constraint.size()" to fix CSN 0120061532 0001148754 2009
		if (constraint.size() > 0 && request.getIndex() < constraint.size()) {
			constraint.set(request.getIndex(), bp);
		} else {
			constraint.add(bp);
		}
		getConnection().setRoutingConstraint(constraint);
	}

	@Override
	protected void showMoveBendpointFeedback(BendpointRequest request) {
		// ADDED: keep points and bendpoints in sync
		refreshConnectionBendpoints();

		// ADDED: enable snap-to adjacent bendpoints.
		snapToAdjacentBendpoints(request, request.getLocation());

		super.showMoveBendpointFeedback(request);
	}

	@Override
	protected List<BendpointHandle> createHandlesForAutomaticBendpoints() {
		List<BendpointHandle> list = new ArrayList<BendpointHandle>();
		ConnectionEditPart connEP = (ConnectionEditPart) getHost();
		PointList points = getConnection().getPoints();
		for (int i = 0; i < points.size() - 1; i++) {
			// CHANGED: create GFBendpointCreationHandle instead of
			// BendpointCreationHandle
			list.add(new GFBendpointHandle(connEP, 0, i, getConfigurationProvider(), GFBendpointHandle.Type.CREATE));
		}

		return list;
	}

	@Override
	protected List<BendpointHandle> createHandlesForUserBendpoints() {
		List<BendpointHandle> list = new ArrayList<BendpointHandle>();
		ConnectionEditPart connEP = (ConnectionEditPart) getHost();
		PointList points = getConnection().getPoints();
		List<Bendpoint> bendPoints = getConnectionRoutingConstraint();
		int bendPointIndex = 0;
		org.eclipse.draw2d.geometry.Point currBendPoint = null;

		if (bendPoints == null)
			bendPoints = NULL_CONSTRAINT;
		else if (!bendPoints.isEmpty())
			currBendPoint = bendPoints.get(0).getLocation();

		for (int i = 0; i < points.size() - 1; i++) {
			// Put a create handle on the middle of every segment
			// CHANGED: create GFBendpointCreationHandle instead of BendpointCreationHandle
			list.add(new GFBendpointHandle(connEP, bendPointIndex, i, getConfigurationProvider(), GFBendpointHandle.Type.CREATE));

			// If the current user bendpoint matches a bend location, show a
			// move handle
			if (i < points.size() - 1 && bendPointIndex < bendPoints.size() && currBendPoint.equals(points.getPoint(i + 1))) {
				// CHANGED: create GFBendpointMoveHandle instead of BendpointMoveHandle
				list.add(new GFBendpointHandle(connEP, bendPointIndex, i + 1, getConfigurationProvider(), GFBendpointHandle.Type.MOVE));

				// Go to the next user bendpoint
				bendPointIndex++;
				if (bendPointIndex < bendPoints.size())
					currBendPoint = bendPoints.get(bendPointIndex).getLocation();
			}
		}

		return list;
	}

	@Override
	protected void setReferencePoints(BendpointRequest request) {
		PointList points = getConnection().getPoints();

		// CHANGED: bpIndex is the position of the bendpoint in points
		int bpIndex = request.getIndex() + 1;

		points.getPoint(ref1, bpIndex - 1);
		getConnection().translateToAbsolute(ref1);
		points.getPoint(ref2, bpIndex + 1);
		getConnection().translateToAbsolute(ref2);
	}

	private void snapToAdjacentBendpoints(BendpointRequest request, org.eclipse.draw2d.geometry.Point p) {
		IFigure f = request.getSource().getFigure();
		if (!(f instanceof GFPolylineConnection))
			return;

		GFPolylineConnection plc = (GFPolylineConnection) f;
		PointList points = plc.getPoints();

		// determine candidate points for snap-to
		// .. the predecessor bendpoint
		org.eclipse.draw2d.geometry.Point predecessor = points.getPoint(request.getIndex()).getCopy();
		getConnection().translateToAbsolute(predecessor);
		int deltaPredecessor = getDistanceBetweenPoints(p, predecessor);
		// .. the successor bendpoint
		// the successorIndex can be out of bounds, if the last bend-point is
		// moved onto the line,
		// so that the bendpoint is deleted. In that case just use the next
		// point, which is the connections end-point.
		int successorIndex = (request.getIndex() + 2 >= points.size()) ? request.getIndex() + 1 : request.getIndex() + 2;
		org.eclipse.draw2d.geometry.Point successor = points.getPoint(successorIndex).getCopy();
		getConnection().translateToAbsolute(successor);
		int deltaSuccessor = getDistanceBetweenPoints(p, successor);

		// Make sure not to snap to the handles of the connection (the first or
		// last point)
		// If we did we would no longer be able to reconnect the connection.
		if (request.getIndex() == 0) {
			deltaPredecessor = Integer.MAX_VALUE; // never snap because of
			// huge distance
		}
		if (request.getIndex() == ((List<?>) getConnection().getRoutingConstraint()).size() - 1) {
			deltaSuccessor = Integer.MAX_VALUE; // never snap because of huge
			// distance
		}

		// first try to snap to predecessor or successor (whichever is nearest)
		if (deltaPredecessor < SNAP_TO_TOLERANCE && deltaPredecessor <= deltaSuccessor) {
			p.setLocation(predecessor);
			return;
		} else if (deltaSuccessor < SNAP_TO_TOLERANCE) {
			p.setLocation(successor);
			return;
		}

		// .. the deltas to the lines through the predecessor and successor
		// bendpoint
		int xDeltaPredecessor = Math.abs(predecessor.x - p.x);
		int yDeltaPredecessor = Math.abs(predecessor.y - p.y);
		int xDeltaSuccessor = Math.abs(successor.x - p.x);
		int yDeltaSuccessor = Math.abs(successor.y - p.y);

		// then try to snap to the rectangular points (whichever is nearest)
		// snap to y-line and x-line can be done both (snap to rectangular
		// point)
		if (xDeltaPredecessor < SNAP_TO_TOLERANCE && xDeltaPredecessor <= xDeltaSuccessor) {
			p.setLocation(predecessor.x, p.y);
		} else if (xDeltaSuccessor < SNAP_TO_TOLERANCE) {
			p.setLocation(successor.x, p.y);
		}
		if (yDeltaPredecessor < SNAP_TO_TOLERANCE && yDeltaPredecessor <= yDeltaSuccessor) {
			p.setLocation(p.x, predecessor.y);
		} else if (yDeltaSuccessor < SNAP_TO_TOLERANCE) {
			p.setLocation(p.x, successor.y);
		}
	}

	private int getDistanceBetweenPoints(org.eclipse.draw2d.geometry.Point p1, org.eclipse.draw2d.geometry.Point p2) {
		// Determine the deltas between the coordinates
		int dx = Math.abs(p1.x - p2.x);
		int dy = Math.abs(p1.y - p2.y);
		// calculate the complete distance: simplified instead of sqrt(x*x +
		// y*y)
		int distance = dx + dy;
		return distance;
	}

	/**
	 * This method refreshes the points of the connection with the bendpoints of
	 * the connection-router. The reason for this is, that sometimes the router
	 * already has different bendpoints set, but the points of the connection
	 * were not updated yet. If now the implementation expects, that those are
	 * already in sync, this can lead to exceptions. This is especially a
	 * problem, if the number of bendpoints does not match the number of
	 * bendpoints in the connections => ArrayIndexOutOfBounds.
	 */
	private void refreshConnectionBendpoints() {
		getConnection().getConnectionRouter().route(getConnection());
	}

	@Override
	protected void showSelection() {
		IMoveBendpointFeature moveBendpointFeature = getFeatureProvider().getMoveBendpointFeature(new MoveBendpointContext(null));
		if (moveBendpointFeature != null) {
			super.showSelection();
		}
	}
}
