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

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.epf.authoring.gef.util.ConnectionAnchorLocator;
import org.eclipse.epf.diagram.model.Link;
import org.eclipse.epf.diagram.model.TypedNode;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.LocationRequest;
import org.eclipse.swt.graphics.Color;

/**
 * Provides support for creating figure and creating relative anchors
 * for {@link TypedNode.SYNC_BAR}
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class SynchBarNodeEditPart extends NodeEditPart implements
		ConnectionAnchorLocator {

	private static final Color BG_COLOR = new Color(null, 51, 102, 102);

	private Point recentTargetEndPoint;

	private Point recentSourceEndPoint;

	public SynchBarNodeEditPart(TypedNode model) {
		super(model);
	}

	/**
	 * @see org.eclipse.epf.authoring.gef.edit.BaseEditPart#createDirectEditPolicy()
	 */
	protected DirectEditPolicy createDirectEditPolicy() {
		return null;
	}

	protected IFigure createFigure() {
		RectangleFigure f = new RectangleFigure();
		f.setBackgroundColor(BG_COLOR);
		f.setSize(100, 5);
		return f;
	}

	public ConnectionAnchor getSourceConnectionAnchor(
			ConnectionEditPart connection) {
		boolean isRelative = false;
		Point sourceEndPoint = null;
		if (recentSourceEndPoint == null) {
			// get source end point from the model
			Link link = (Link) connection.getModel();
			sourceEndPoint = link.getSourceEndPoint();
			isRelative = true;
		} else {
			sourceEndPoint = recentSourceEndPoint;
		}
		if (sourceEndPoint != null) {
			return new RelativeXYAnchor(getFigure(), sourceEndPoint, isRelative);
		}
		return super.getSourceConnectionAnchor(connection);
	}

	public ConnectionAnchor getSourceConnectionAnchor(Request request) {
		Point p = null;
		if (request instanceof CreateConnectionRequest) {
			CreateConnectionRequest req = (CreateConnectionRequest) request;
			p = req.getLocation();
		} else if (request instanceof LocationRequest) {
			p = ((LocationRequest) request).getLocation();
		}
		if (p != null) {
			recentSourceEndPoint = getLocation(p);
			return new RelativeXYAnchor(getFigure(), recentSourceEndPoint, true);
		}
		return super.getSourceConnectionAnchor(request);
	}

	public ConnectionAnchor getTargetConnectionAnchor(
			ConnectionEditPart connection) {
		boolean isRelative = false;
		Point targetEndPoint = null;
		if (recentTargetEndPoint == null) {
			Link link = (Link) connection.getModel();
			targetEndPoint = link.getTargetEndPoint();
			isRelative = true;
		} else {
			targetEndPoint = recentTargetEndPoint;
		}
		if (targetEndPoint != null) {
			return new RelativeXYAnchor(getFigure(), targetEndPoint, isRelative);
		}

		return super.getTargetConnectionAnchor(connection);
	}

	public ConnectionAnchor getTargetConnectionAnchor(Request request) {
		Point p = null;
		if (request instanceof CreateConnectionRequest) {
			CreateConnectionRequest req = (CreateConnectionRequest) request;
			p = req.getLocation();
		} else if (request instanceof LocationRequest) {
			p = ((LocationRequest) request).getLocation();
		}
		if (p != null) {
			recentTargetEndPoint = getLocation(p);
			return new RelativeXYAnchor(getFigure(), recentTargetEndPoint, true);
		}

		return super.getTargetConnectionAnchor(request);
	}

	/**
	 * @see org.eclipse.epf.authoring.gef.util.ConnectionAnchorLocator#getLocation(Point)
	 */
	public Point getLocation(Point reference) {
		return ClosestAnchor.getLocation(getFigure(), reference);
	}

}
