//------------------------------------------------------------------------------
// Copyright (c) 2005, 2007 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.migration.diagram.addwpd.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.AbsoluteBendpoint;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.epf.diagram.core.services.DiagramManager;
import org.eclipse.epf.diagram.model.Link;
import org.eclipse.epf.diagram.model.Node;
import org.eclipse.epf.migration.diagram.ad.map.MapUtil;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.IdentityAnchor;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.RelativeBendpoints;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.runtime.notation.datatype.RelativeBendpoint;

/**
 * 
 * @author Shilpa Toraskar
 * @since 1.2
 * 
 */
public class ConnectionFactory {

	private HashMap map = null;

	private Diagram diagram = null;

	private String kind;

	private static final int IMAGE_HEIGHT = 32;

	public ConnectionFactory(Diagram diagram, HashMap map, String kind) {
		this.map = map;
		this.diagram = diagram;
		this.kind = kind;
	}

	/**
	 * Create outgoing connections
	 * 
	 * @param node
	 */
	public void outgoingConnections(Node node) {
		List connections = node.getOutgoingConnections();

		if (connections != null && connections.size() > 0) {
			for (Iterator iterator = connections.iterator(); iterator.hasNext();) {
				Link link = (Link) iterator.next();

				// create connection
				Edge edge = createConnection(link);

				// get source node
				org.eclipse.gmf.runtime.notation.Node sourceNotationNode = (org.eclipse.gmf.runtime.notation.Node) map
						.get(node);

				// add the edge as source edge
				sourceNotationNode.getSourceEdges().add(edge);
				sourceNotationNode.getDiagram().insertEdge(edge);

			}
		}
	}

	/**
	 * Create incoming connections
	 * 
	 * @param node
	 */
	public void incomingConnections(Node node) {
		List connections = node.getIncomingConnections();

		if (connections != null && connections.size() > 0) {
			for (Iterator iterator = connections.iterator(); iterator.hasNext();) {
				Link link = (Link) iterator.next();

				// create edge
				Edge edge = createConnection(link);

				// get source node
				org.eclipse.gmf.runtime.notation.Node sourceNotationNode = (org.eclipse.gmf.runtime.notation.Node) map
						.get(node);

				// add the edge as target edge
				sourceNotationNode.getTargetEdges().add(edge);
				sourceNotationNode.getDiagram().insertEdge(edge);
			}
		}
	}

	/**
	 * Create notation edge for the given link
	 * 
	 * @param link
	 * @return
	 */
	private Edge createConnection(Link link) {
		// get source and target objects of the link
		Node sourceObj = link.getSource();
		Node targetObj = link.getTarget();

		// get notation nodes
		org.eclipse.gmf.runtime.notation.Node sourceNotationNode = (org.eclipse.gmf.runtime.notation.Node) map
				.get(sourceObj);
		org.eclipse.gmf.runtime.notation.Node targetNotationNode = (org.eclipse.gmf.runtime.notation.Node) map
				.get(targetObj);

		Edge edge = findEdge(sourceNotationNode, targetNotationNode);
		if (edge == null && targetNotationNode != null) {
			// create notation edge
			if (kind == DiagramManager.ADD_kind)
				edge = MapUtil.createEdge_WPD(sourceNotationNode,
						targetNotationNode, link);
			else if (kind == DiagramManager.WPD_kind)
				edge = MapUtil.createEdge_WPD(sourceNotationNode,
						targetNotationNode, link);

			if (edge == null)
				return null;

			edge.setElement(link);
			loadSourceAndTargetEndPoint(edge, link);
			loadBendPoints(edge, link);
		}
		return edge;
	}

	/**
	 * Loads source and target end points for synchronization bar and decision
	 * node
	 * 
	 * @param edge
	 * @param flow
	 */
	private void loadSourceAndTargetEndPoint(Edge edge, Link flow) {
		Point srcPoint = flow.getSourceEndPoint();
		Point targetPoint = flow.getTargetEndPoint();

		View srcView = edge.getSource();
		View targetView = edge.getTarget();

		if (srcPoint != null
				&& srcView instanceof org.eclipse.gmf.runtime.notation.Node) {
			Bounds bounds = (Bounds) ((org.eclipse.gmf.runtime.notation.Node) srcView)
					.getLayoutConstraint();
			// for WPD, height is not stored and it's always same.
			// We do need correct height to calculate identity anchor in GMF
			if (bounds.getHeight() == -1)
				bounds.setHeight(40);
			IdentityAnchor anchor = createIdentityAnchor(srcPoint, bounds);
			if (anchor != null)
				edge.setSourceAnchor(anchor);
		}

		if (targetPoint != null
				&& targetView instanceof org.eclipse.gmf.runtime.notation.Node) {
			Bounds bounds = (Bounds) ((org.eclipse.gmf.runtime.notation.Node) targetView)
					.getLayoutConstraint();
			// For WPD, height is not stored and it's always same.
			// We do need correct height to calculate identity anchor in GMF
			if (bounds.getHeight() == -1)
				bounds.setHeight(40);
			IdentityAnchor anchor = createIdentityAnchor(targetPoint, bounds);
			if (anchor != null)
				edge.setTargetAnchor(anchor);
		}
	}

	/**
	 * Create identity anchor
	 * 
	 * @param point
	 * @param bounds
	 * @return
	 */
	private IdentityAnchor createIdentityAnchor(Point point, Bounds bounds) {
		PrecisionPoint prePoint = MapUtil.getAnchorRelativeLocation(point,
				bounds);
		String id = MapUtil.composeTerminalString(prePoint);

		IdentityAnchor value = NotationFactory.eINSTANCE.createIdentityAnchor();
		value.setId(id);

		return value;
	}

	/**
	 * Load relative bend points
	 * 
	 * @param edge
	 * @param flow
	 */
	private void loadBendPoints(Edge edge, Link flow) {
		List list = flow.getBendpoints();
		List<RelativeBendpoint> relativePoints = new ArrayList<RelativeBendpoint>();

		RelativeBendpoint rbp = new RelativeBendpoint(0, 0, 0, 0);

		relativePoints.add(rbp);
		if (list != null && list.size() > 0) {
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				AbsoluteBendpoint abp = (AbsoluteBendpoint) iterator.next();

				org.eclipse.gmf.runtime.notation.Node srcNode = (org.eclipse.gmf.runtime.notation.Node) edge
						.getSource();
				org.eclipse.gmf.runtime.notation.Node targetNode = (org.eclipse.gmf.runtime.notation.Node) edge
						.getTarget();

				Bounds sourceBounds = (Bounds) srcNode.getLayoutConstraint();
				Bounds targetBounds = (Bounds) targetNode.getLayoutConstraint();

				int srcLine = MapUtil.getNoOfLines(srcNode);
				int targetLine = MapUtil.getNoOfLines(targetNode);
				int srcHeight = MapUtil.getTextHeight(srcNode);
				int targetHeight = MapUtil.getTextHeight(targetNode);

				int srcX = sourceBounds.getX() + sourceBounds.getWidth() / 2;
				int srcY = sourceBounds.getY()
						+ (IMAGE_HEIGHT + (srcLine * srcHeight)) / 2;
				int tarX = targetBounds.getX() + targetBounds.getWidth() / 2;
				int tarY = targetBounds.getY()
						+ (IMAGE_HEIGHT + (targetLine * targetHeight)) / 2;

				int rel1 = abp.x - srcX;
				int rel2 = abp.y - srcY;
				int rel3 = abp.x - tarX;
				int rel4 = abp.y - tarY;

				RelativeBendpoint rbp1 = new RelativeBendpoint(rel1, rel2,
						rel3, rel4);
				relativePoints.add(rbp1);
			}
		}
		relativePoints.add(rbp);

		if (relativePoints.size() > 0) {
			RelativeBendpoints bendpoints = NotationFactory.eINSTANCE
					.createRelativeBendpoints();
			bendpoints.setPoints(relativePoints);
			edge.setBendpoints(bendpoints);
		}
	}

	/**
	 * Find edge in diagram given source and target nodes
	 * 
	 * @param sourceNode
	 * @param targetNode
	 * @return
	 */
	private Edge findEdge(org.eclipse.gmf.runtime.notation.Node sourceNode,
			org.eclipse.gmf.runtime.notation.Node targetNode) {
		List list = diagram.getEdges();
		if (list != null && list.size() > 0) {
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Edge edge = (Edge) iterator.next();
				if ((edge.getTarget() != null)
						&& targetNode.equals(edge.getTarget())
						&& (edge.getSource() != null)
						&& sourceNode.equals(edge.getSource())) {
					return edge;
				}
			}
		}
		return null;
	}

}
