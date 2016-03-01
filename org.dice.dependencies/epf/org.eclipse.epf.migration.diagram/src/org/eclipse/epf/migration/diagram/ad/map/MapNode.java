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
package org.eclipse.epf.migration.diagram.ad.map;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.AbsoluteBendpoint;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.epf.diagram.ad.edit.parts.ActivityFinalNodeEditPart;
import org.eclipse.epf.diagram.ad.edit.parts.DecisionNodeEditPart;
import org.eclipse.epf.diagram.ad.edit.parts.ForkNodeEditPart;
import org.eclipse.epf.diagram.ad.edit.parts.JoinNodeEditPart;
import org.eclipse.epf.diagram.ad.edit.parts.MergeNodeEditPart;
import org.eclipse.epf.diagram.core.bridge.BridgeHelper;
import org.eclipse.epf.diagram.core.util.DiagramCoreUtil;
import org.eclipse.epf.diagram.model.Link;
import org.eclipse.epf.diagram.model.NamedNode;
import org.eclipse.epf.diagram.model.Node;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.IdentityAnchor;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.RelativeBendpoints;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.runtime.notation.datatype.RelativeBendpoint;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ControlFlow;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * @author Shashidhar Kannoori
 * @author Shilpa Toraskar
 * @since 1.2
 * 
 */
public class MapNode implements IMapConstants {

	protected Node node;

	//private ResourceSet resourceSet;

	protected org.eclipse.gmf.runtime.notation.Node notationNode;

	private Activity activity;
	
	private Diagram diagram;

	protected String name;

	protected ActivityNode umlNode;

	private static final int IMAGE_HEIGHT = 32;
	
	public MapNode(Node node) {
		this.node = node;
	}

	public MapNode() {
	}

	/**
	 * Update resource
	 * 
	 * @param resourceSet
	 * @return
	 */
	public org.eclipse.gmf.runtime.notation.Node updateResource() {
		Diagram diagram = getDiagram();

		// Check if notation element already created.
		if (graphUMLNodeMap.get(node) != null) {
			org.eclipse.uml2.uml.ActivityNode tempUMLNode = (ActivityNode) graphUMLNodeMap
					.get(node);
			org.eclipse.gmf.runtime.notation.Node tempNotationNode = findNotationNode(
					diagram, tempUMLNode);
			if (tempNotationNode != null)
				return tempNotationNode;

		}

		// Create Activity Node
		updateNode();

		// Create Notation nnode
		notationNode = MapUtil.createNotationNode_AD(diagram, umlNode);
		diagram.insertChild(notationNode);

		// set size
		setLayoutConstraint(node, notationNode);

		// set element
		notationNode.setElement(umlNode);

		// mark inherited
		setInheritedAttribute(node, notationNode);
		
		return notationNode;
	}

	
	/**
	 * @param node
	 * @param notationNode
	 */
	private void setLayoutConstraint(org.eclipse.epf.diagram.model.Node node,
			org.eclipse.gmf.runtime.notation.Node notationNode) {
		Bounds bounds = NotationFactory.eINSTANCE.createBounds();
		
		int type = new Integer(notationNode.getType()).intValue();
		if (node.getHeight() == -1) {
			 if (type == DecisionNodeEditPart.VISUAL_ID || type == MergeNodeEditPart.VISUAL_ID)	{
				 bounds.setHeight(24);
			 }
			 else if (type == ForkNodeEditPart.VISUAL_ID || type == JoinNodeEditPart.VISUAL_ID)	{
				 bounds.setHeight(5);
			 }
			 else if (type == ActivityFinalNodeEditPart.VISUAL_ID){
				 bounds.setHeight(24);
			 }
		} else {
			bounds.setHeight(node.getHeight());
		}
		
		// Due to bug in old diagram, some of the decisionNode width is -1
		if ((node.getWidth() == -1) && (type == DecisionNodeEditPart.VISUAL_ID
					|| type == MergeNodeEditPart.VISUAL_ID)) {
				bounds.setWidth(48);
		} else {
			bounds.setWidth(node.getWidth());
		}
		
		int w = bounds.getWidth();
		int h = bounds.getHeight();
		Dimension dim = DiagramCoreUtil.getTextSizeInWrapLabel(name, null, w, h, null);
		int dimw = dim.width;
		if (w < dimw && dimw != 0) {
			bounds.setWidth(dimw);
		}
		
//		bounds.setHeight(node.getHeight());
//		bounds.setWidth(node.getWidth());
		bounds.setX(node.getLocation().x);
		bounds.setY(node.getLocation().y);
		notationNode.setLayoutConstraint(bounds);
	}

	/**
	 * Set inherited attribute
	 * @param node
	 * @param notationNode
	 */
	private void setInheritedAttribute(org.eclipse.epf.diagram.model.Node node,
			org.eclipse.gmf.runtime.notation.Node notationNode) {
		if (node.getLinkedElement() == null) {
			if (node.getGraphNode().getBriefDescription() != null
					&& !(node.getGraphNode().getBriefDescription().trim()
							.equals(""))) //$NON-NLS-1$
				BridgeHelper.markInherited(notationNode);
		} 
	}
	
	
	/**
	 * Find corresponding notation node for the uml node in the diagram
	 * @param diagram
	 * @param umlNode
	 * @return
	 */
	private org.eclipse.gmf.runtime.notation.Node findNotationNode(
			Diagram diagram, ActivityNode umlNode) {
		List list = diagram.getChildren();
		if (list != null && list.size() > 0) {
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				org.eclipse.gmf.runtime.notation.Node node = (org.eclipse.gmf.runtime.notation.Node) iterator
						.next();
				if (umlNode.equals(node.getElement())) {
					return node;
				}
			}
		}
		return null;
	}

	/**
	 * Update Uml node
	 */
	public void updateNode() {
		if (node instanceof NamedNode) {
			name = ((NamedNode) node).getName();
		}
	}

	/**
	 * Create outgoing connections
	 * @param node
	 */
	public void outgoingConnections(Node node) {
		List connections = node.getOutgoingConnections();

		if (connections != null && connections.size() > 0) {
			for (Iterator iterator = connections.iterator(); iterator.hasNext();) {
				Link link = (Link) iterator.next();
				
				// create connection
				Edge edge = createConnection(link);

				ActivityNode sourceNode = (org.eclipse.uml2.uml.ActivityNode) graphUMLNodeMap
						.get(node);
				org.eclipse.gmf.runtime.notation.Node sourceNotationNode = findNotationNode(
						getDiagram(), sourceNode);

				// add the edge as source edge
				sourceNotationNode.getSourceEdges().add(edge);
				sourceNotationNode.getDiagram().insertEdge(edge);

			}
		}
	}

	/**
	 * Create UML control flow
	 * @return
	 */
	private ControlFlow createControlFlow() {
		ControlFlow controlFlow = (org.eclipse.uml2.uml.ControlFlow) getActivity()
				.createEdge("", /*MigrationResources.GRAPH_NODE_LINK,*/ //$NON-NLS-1$
						UMLPackage.eINSTANCE.getControlFlow());

		return controlFlow;
	}

	/**
	 * Loads source and target end points for synchronization bar and decision node
	 * @param edge
	 * @param flow
	 */
	private void loadSourceAndTargetEndPoint(Edge edge, Link flow) {
		Point srcPoint = flow.getSourceEndPoint();
		Point targetPoint = flow.getTargetEndPoint();

		View srcView = edge.getSource();
		View targetView = edge.getTarget();
		int srcType = new Integer(srcView.getType()).intValue();
		int targetType = new Integer(targetView.getType()).intValue();

		if (srcType == ForkNodeEditPart.VISUAL_ID || srcType == JoinNodeEditPart.VISUAL_ID ||
				srcType == DecisionNodeEditPart.VISUAL_ID || srcType == MergeNodeEditPart.VISUAL_ID) {
			if (srcPoint != null
					&& srcView instanceof org.eclipse.gmf.runtime.notation.Node ) {
				Bounds bounds = (Bounds) ((org.eclipse.gmf.runtime.notation.Node) srcView)
						.getLayoutConstraint();
				IdentityAnchor anchor = createIdentityAnchor(srcPoint, bounds);
				if (anchor != null)
					edge.setSourceAnchor(anchor);
			}
		}
		if (targetType == ForkNodeEditPart.VISUAL_ID || targetType == JoinNodeEditPart.VISUAL_ID ||
				targetType == DecisionNodeEditPart.VISUAL_ID || targetType == MergeNodeEditPart.VISUAL_ID) {
			if (targetPoint != null
					&& targetView instanceof org.eclipse.gmf.runtime.notation.Node) {
				Bounds bounds = (Bounds) ((org.eclipse.gmf.runtime.notation.Node) targetView)
						.getLayoutConstraint();
				IdentityAnchor anchor = createIdentityAnchor(targetPoint, bounds);
				if (anchor != null)
					edge.setTargetAnchor(anchor);
			}
		}
	}
	
	
	/**
	 * Create identity anchor
	 * @param point
	 * @param bounds
	 * @return
	 */
	private IdentityAnchor createIdentityAnchor(Point point, Bounds bounds) {
		Point tempPoint = new Point(bounds.getX() + point.x, bounds.getY()
				+ point.y);

		PrecisionPoint prePoint = MapUtil.getAnchorRelativeLocation(tempPoint, bounds);
		String id = MapUtil.composeTerminalString(prePoint);

		IdentityAnchor value = NotationFactory.eINSTANCE.createIdentityAnchor();
		value.setId(id);

		return value;
	}
	
	
	
	/**
	 * Load relative bend points
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
				
				org.eclipse.gmf.runtime.notation.Node srcNode = (org.eclipse.gmf.runtime.notation.Node) edge.getSource();
				org.eclipse.gmf.runtime.notation.Node targetNode = (org.eclipse.gmf.runtime.notation.Node) edge.getTarget();
				int srcType = new Integer(srcNode.getType()).intValue();
				int targetType = new Integer(targetNode.getType()).intValue();
				
				Bounds sourceBounds = (Bounds) srcNode.getLayoutConstraint();				
				Bounds targetBounds = (Bounds) targetNode.getLayoutConstraint();
						
				int srcLine = MapUtil.getNoOfLines(srcNode);
				int targetLine = MapUtil.getNoOfLines(targetNode);
				int srcHeight = MapUtil.getTextHeight(srcNode);
				int targetHeight = MapUtil.getTextHeight(targetNode);
				
				int srcX = sourceBounds.getX() + sourceBounds.getWidth() / 2;
				int srcY = sourceBounds.getY() + (IMAGE_HEIGHT + (srcLine * srcHeight)) / 2;
				int tarX = targetBounds.getX() + targetBounds.getWidth() / 2;
				int tarY = targetBounds.getY() + (IMAGE_HEIGHT + (targetLine * targetHeight)) / 2;

				if (srcType == ForkNodeEditPart.VISUAL_ID
						|| srcType == JoinNodeEditPart.VISUAL_ID
						|| srcType == DecisionNodeEditPart.VISUAL_ID
						|| srcType == MergeNodeEditPart.VISUAL_ID) {
					Point srcPoint = flow.getSourceEndPoint();
					if (srcPoint != null) {
						srcX = sourceBounds.getX() + srcPoint.x;
						srcY = sourceBounds.getY() + srcPoint.y;
					}
				}
				if (targetType == ForkNodeEditPart.VISUAL_ID
						|| targetType == JoinNodeEditPart.VISUAL_ID
						|| targetType == DecisionNodeEditPart.VISUAL_ID
						|| targetType == MergeNodeEditPart.VISUAL_ID) {
					Point targetPoint = flow.getTargetEndPoint();		
					if (targetPoint != null) {
						tarX = targetBounds.getX() + targetPoint.x;
						tarY = targetBounds.getY() + targetPoint.y;
					}			
				}			
				if (targetType == ActivityFinalNodeEditPart.VISUAL_ID){	
					tarX = targetBounds.getX() + targetBounds.getWidth() / 2;
					tarY = targetBounds.getY() + targetBounds.getHeight() / 2;		
				}

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
	 * @param sourceNode
	 * @param targetNode
	 * @return
	 */
	private Edge findEdge(org.eclipse.gmf.runtime.notation.Node sourceNode,
			org.eclipse.gmf.runtime.notation.Node targetNode) {
		List list = notationNode.getDiagram().getEdges();
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


	/**
	 * Create incoming connections
	 * @param node
	 */
	public void incomingConnections(Node node) {
		List connections = node.getIncomingConnections();

		if (connections != null && connections.size() > 0) {
			for (Iterator iterator = connections.iterator(); iterator.hasNext();) {
				Link link = (Link) iterator.next();
				
				// create edge
				Edge edge = createConnection(link);

				ActivityNode sourceNode = (org.eclipse.uml2.uml.ActivityNode) graphUMLNodeMap
						.get(node);
				org.eclipse.gmf.runtime.notation.Node sourceNotationNode = findNotationNode(
						getDiagram(), sourceNode);

				// add the edge as target edge
				sourceNotationNode.getTargetEdges().add(edge);
				sourceNotationNode.getDiagram().insertEdge(edge);
			}
		}
	}

	/**
	 * Create notation edge for the given link
	 * @param link
	 * @return
	 */
	private Edge createConnection(Link link) {
		// get source and target objects of the link
		Node sourceObj = link.getSource();
		Node targetObj = link.getTarget();

		// get uml nodes
		ActivityNode sourceUMLNode = (org.eclipse.uml2.uml.ActivityNode) graphUMLNodeMap
				.get(sourceObj);
		ActivityNode targetUMLNode = (org.eclipse.uml2.uml.ActivityNode) graphUMLNodeMap
				.get(targetObj);

		// get notation nodes
		org.eclipse.gmf.runtime.notation.Node sourceNotationNode = findNotationNode(
				getDiagram(), sourceUMLNode);
		org.eclipse.gmf.runtime.notation.Node targetNotationNode = findNotationNode(
				getDiagram(), targetUMLNode);

		Edge edge = findEdge(sourceNotationNode, targetNotationNode);
		if (edge == null && targetNotationNode != null) {
			ControlFlow controlFlow = createControlFlow();

			if (targetUMLNode instanceof ActivityNode) {
				controlFlow.setSource((ActivityNode) sourceUMLNode);
				controlFlow.setTarget((ActivityNode) targetUMLNode);
			}

			// create notation edge
			edge = MapUtil.createEdge_AD(sourceNotationNode, targetNotationNode,
					controlFlow);
			edge.setElement(controlFlow);
			loadSourceAndTargetEndPoint(edge, link);
			loadBendPoints(edge, link);
		}
		return edge;
	}

	// private Edge findEdge(org.eclipse.uml2.uml.ControlFlow flow, Diagram
	// diagram) {
	// List list = diagram.getChildren();
	// for (Iterator iterator = list.iterator(); iterator.hasNext();) {
	// Object object = iterator.next();
	// if (object instanceof Edge) {
	// Object element = ((Edge) object).getElement();
	// if (flow.equals(element)) {
	// return (Edge) object;
	// }
	// }
	// }
	// return null;
	// }

	private Diagram getDiagram() {
/*		if (diagram == null) {
			Resource resource = (Resource) resourceSet.getResources().get(0);
			diagram = (Diagram) resource.getContents().get(1);
		}*/
		return diagram;
	}

	public Activity getActivity() {
/*		if (activity == null) {
			// return
			// (Activity)((Model)getUMLObject()).allOwnedElements().get(0);
			Resource resource = (Resource) resourceSet.getResources().get(0);
			activity = (Activity) resource.getContents().get(0);
		}*/
		return activity;
	}
	// public EObject getUMLObject(){
	// if(eObject == null){
	//			
	// eObject = (Activity)getResource(0).getContents().get(0);
	// }
	// return eObject;
	// }

	public void setDiagram(Diagram diagram) {
		this.diagram = diagram;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	// private Resource getResource(int i){
	// return (Resource)resourceSet.getResources().get(i);
	// }

}
