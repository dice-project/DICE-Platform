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
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.epf.authoring.gef.figures.Diamond;
import org.eclipse.epf.authoring.gef.util.AnchorUtil;
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
 * EditPart for  DecisionNode, used in activity diagram.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class DecisionNodeEditPart extends NodeEditPart implements
		ConnectionAnchorLocator {

	private static final Color BG_COLOR = new Color(null, 254, 204, 153);

	private Point recentSourceEndPoint;

	private Point recentTargetEndPoint;

	public DecisionNodeEditPart(TypedNode model) {
		super(model);
	}

	/**
	 * @see org.eclipse.epf.authoring.gef.edit.BaseEditPart#createDirectEditPolicy()
	 */
	protected DirectEditPolicy createDirectEditPolicy() {
		return null;
	}

	/**
	 * @see org.eclipse.epf.authoring.gef.edit.BaseEditPart#createFigure()
	 */
	protected IFigure createFigure() {
		Diamond figure = new Diamond();
		figure.setBackgroundColor(BG_COLOR);
		figure.setSize(48, 24);
		return figure;
	}
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.epf.authoring.gef.edit.NodeEditPart#getSourceConnectionAnchor(org.eclipse.gef.ConnectionEditPart)
	 */
	public ConnectionAnchor getSourceConnectionAnchor(
			ConnectionEditPart connection) {
		boolean isRelative = false;
		Point sourceEndPoint;
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

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.epf.authoring.gef.edit.NodeEditPart#getSourceConnectionAnchor(org.eclipse.gef.Request)
	 */
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

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.epf.authoring.gef.edit.NodeEditPart#getTargetConnectionAnchor(org.eclipse.gef.ConnectionEditPart)
	 */
	public ConnectionAnchor getTargetConnectionAnchor(
			ConnectionEditPart connection) {
		boolean isRelative = false;
		Point targetEndPoint;
		if (recentTargetEndPoint == null) {
			// get target end point from model
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

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.epf.authoring.gef.edit.NodeEditPart#getTargetConnectionAnchor(org.eclipse.gef.Request)
	 */
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
		Diamond f = ((Diamond) getFigure());

		// translate points to absolute
		Point[] points = new Point[f.getPoints().size()];
		for (int i = f.getPoints().size() - 1; i > -1; i--) {
			Point p = f.getPoints().getPoint(i);
			f.translateToAbsolute(p);
			points[i] = p;
		}

		Point p = AnchorUtil.getClosestPoint(points, reference);
		if (p != null) {
			Rectangle r = f.getBounds().getCopy();
			f.translateToAbsolute(r);
			p.performTranslate(-r.x, -r.y);
		}
		return p;
	}

}
