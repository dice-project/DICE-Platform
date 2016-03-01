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
package org.eclipse.epf.diagram.model.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.util.AbstractTreeIterator;
import org.eclipse.epf.diagram.model.ActivityDetailDiagram;
import org.eclipse.epf.diagram.model.DiagramResources;
import org.eclipse.epf.diagram.model.Link;
import org.eclipse.epf.diagram.model.ModelFactory;
import org.eclipse.epf.diagram.model.Node;
import org.eclipse.epf.diagram.model.NodeContainer;
import org.eclipse.epf.diagram.model.RoleTaskComposite;
import org.eclipse.epf.diagram.model.TypedNode;
import org.eclipse.epf.diagram.model.WorkBreakdownElementNode;
import org.eclipse.epf.diagram.model.WorkProductComposite;
import org.eclipse.epf.diagram.model.impl.NamedNodeImpl;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.Providers;
import org.eclipse.epf.library.edit.process.WorkProductDescriptorWrapperItemProvider;
import org.eclipse.epf.library.edit.util.IDiagramManager;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Diagram;
import org.eclipse.epf.uma.DiagramElement;
import org.eclipse.epf.uma.Dimension;
import org.eclipse.epf.uma.GraphConnector;
import org.eclipse.epf.uma.GraphEdge;
import org.eclipse.epf.uma.GraphNode;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.Property;
import org.eclipse.epf.uma.SemanticModelBridge;
import org.eclipse.epf.uma.SimpleSemanticModelElement;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.UMASemanticModelBridge;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.VariabilityType;
import org.eclipse.epf.uma.WorkBreakdownElement;
import org.eclipse.epf.uma.WorkOrder;
import org.eclipse.epf.uma.util.UmaUtil;

/**
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class GraphicalDataHelper {
	/** Diagram type constants */
	public static final int ACTIVITY_DIAGRAM = IDiagramManager.ACTIVITY_DIAGRAM;

	public static final int WORK_PRODUCT_DEPENDENCY_DIAGRAM = IDiagramManager.WORK_PRODUCT_DEPENDENCY_DIAGRAM;

	public static final int ACTIVITY_DETAIL_DIAGRAM = IDiagramManager.ACTIVITY_DETAIL_DIAGRAM;

	static final int[] DIAGRAM_TYPES = { ACTIVITY_DIAGRAM,
			ACTIVITY_DETAIL_DIAGRAM, WORK_PRODUCT_DEPENDENCY_DIAGRAM };

	static final String[] DIAGRAM_TYPE_TEXTS = {
			DiagramResources.type_activity, DiagramResources.type_WPD, DiagramResources.type_activityDetail }; 

	/** Property names */
	public static final String PROP_TYPE = "type"; //$NON-NLS-1$

	public static final String PROP_WORK_PRODUCT_COMPOSITE_TYPE = "wpCompositeType"; //$NON-NLS-1$
	
	/** property names for activity detail diagram **/
	public static final String PROP_AUTO_LAYOUT = "autolayout"; //$NON-NLS-1$
	
	public static final String PROP_INDEX = "index"; //$NON-NLS-1$
	
	/** property values constants for autolayout **/
	
	public static final String PROP_AUTO_LAYOUT_VALUE_TRUE = "true"; //$NON-NLS-1$
	
	public static final String PROP_AUTO_LAYOUT_VALUE_FALSE = "false"; //$NON-NLS-1$
	
	/** Type info for UI node (@see TypedNode) */
	public static final String GRAPH_NODE_SYNCH_BAR = "synchnonization bar"; //$NON-NLS-1$

	public static final String GRAPH_NODE_START = "start node"; //$NON-NLS-1$

	public static final String GRAPH_NODE_END = "end node"; //$NON-NLS-1$

	public static final String GRAPH_NODE_DECISION = "decision node"; //$NON-NLS-1$

	public static final String GRAPH_NODE_FREE_TEXT = "free text"; //$NON-NLS-1$

	/** Diagram type string constants */
	public static final String DIAGRAM_WORKFLOW = "Workflow"; //$NON-NLS-1$

	public static final String DIAGRAM_WORK_PRODUCT_DEPENDENCY = "Work Product Dependency"; //$NON-NLS-1$

	public static final String DIAGRAM_ACTIVITY_DETAIL = "Activity Detail"; //$NON-NLS-1$
	
	public static final String ADD_DIAGRAM_TASKS_PER_ROW = "ACTIVITY_DETAIL_DIAGRAM_TASKS_PER_ROW"; //$NON-NLS-1$

	public static Point toPoint(org.eclipse.epf.uma.Point p) {
		return new Point(p.getX().doubleValue(), p.getY().doubleValue());
	}

	public static org.eclipse.epf.uma.Point newModelPoint(int x, int y) {
		org.eclipse.epf.uma.Point p = UmaFactory.eINSTANCE.createPoint();
		p.setX(new Double(x));
		p.setY(new Double(y));
		return p;
	}

	public static Property findProperty(GraphNode node, String propKey) {
		for (int i = node.getProperty().size() - 1; i > -1; i--) {
			Property prop = (Property) node.getProperty().get(i);
			if (propKey.equals(prop.getKey()))
				return prop;
		}
		return null;
	}

	/**
	 * @param width
	 * @param height
	 * @return
	 */
	public static Dimension newModelDimension(int width, int height) {
		Dimension size = UmaFactory.eINSTANCE.createDimension();
		size.setWidth(new Double(width));
		size.setHeight(new Double(height));
		return size;
	}

	public static void fillConnections(Node node, GraphNode graphNode) {
		boolean old = node.eDeliver();
		try {
			node.eSetDeliver(false);
			org.eclipse.epf.diagram.model.Diagram diagram = (org.eclipse.epf.diagram.model.Diagram) node
					.eContainer();
			for (Iterator iter = graphNode.getAnchorage().iterator(); iter
					.hasNext();) {
				GraphConnector conn = ((GraphConnector) iter.next());
				for (Iterator iterator = conn.getGraphEdge().iterator(); iterator
						.hasNext();) {
					GraphEdge edge = (GraphEdge) iterator.next();
					if(edge.getAnchor().size() > 1) { 
						GraphConnector targetConnector;
						Link link = null;
						if (edge.eContainer() == graphNode) {
							targetConnector = (GraphConnector) edge.getAnchor()
							.get(1);
							GraphNode targetGraphNode = (GraphNode) targetConnector
							.eContainer();
							if (graphNode.eContainer() == targetGraphNode
									.eContainer()) {
								Object linkedObject = targetGraphNode;
								if (targetGraphNode.getSemanticModel() instanceof UMASemanticModelBridge) {
									linkedObject = ((UMASemanticModelBridge) targetGraphNode
											.getSemanticModel()).getElement();
								}
								if(linkedObject != null){
									Node targetNode = findNode(diagram, linkedObject);
									link = ModelFactory.eINSTANCE.createLink();
									link.setSource(node);
									link.setTarget(targetNode);
									link.setObject(edge);
								}else{
									if(LibraryEditPlugin.getDefault().isDebugging()){
										System.out.println("Linked object is null: for: "+ node.getGraphNode() + //$NON-NLS-1$
												": for graphConnector: "+	targetConnector); //$NON-NLS-1$
									}
								}
							}
						}
					}
				}

			}
		} finally {
			node.eSetDeliver(old);
		}
	}

	public static Node findNode(org.eclipse.epf.diagram.model.Diagram diagram,
			GraphNode gNode, Class adapterType) {
		SemanticModelBridge bridge = gNode.getSemanticModel();
		if (bridge instanceof UMASemanticModelBridge) {
			INodeChangeListener listener = (INodeChangeListener) UmaUtil
					.getAdapter(((UMASemanticModelBridge) bridge).getElement(),
							adapterType);
			return listener.getNode();
		}

		// gNode is not a activity's GraphNode. Try to find a Node with gNode as
		// a linked object in the diagram.
		//
		for (Iterator iter = diagram.getNodes().iterator(); iter.hasNext();) {
			Node element = (Node) iter.next();
			if (gNode == element.getObject()) {
				return element;
			}
		}
		return null;
	}

	/**
	 * Finds node whose linked object is the given object
	 * 
	 * @return
	 */
	public static Node findNode(NodeContainer container, Object object) {
		for (Iterator iter = container.getNodes().iterator(); iter.hasNext();) {
			Node element = (Node) iter.next();
			if (object == element.getObject() || object == element.getLinkedElement()) {
				return element;
			}
		}
		return null;
	}

	/**
	 * Finds node whose linked object or one of its base is the given object
	 * 
	 * @param container
	 * @param object
	 * @return
	 */
	public static Node findNode(NodeContainer container, Object object,
			boolean checkBase) {
		for (Iterator iter = container.getNodes().iterator(); iter.hasNext();) {
			Node node = (Node) iter.next();
			if (object == node.getObject()) {
				return node;
			} else if (checkBase
					&& node.getObject() instanceof VariabilityElement) {
				for (VariabilityElement ve = ((VariabilityElement) node
						.getObject()).getVariabilityBasedOnElement(); ve != null; ve = ve
						.getVariabilityBasedOnElement()) {
					if (ve == object) {
						return node;
					}
				}
			}
		}
		return null;
	}

	public static Node findNode(ActivityDetailDiagram diagram, Object object) {
		Node node = null;
		for (Iterator iter = diagram.getNodes().iterator(); iter.hasNext();) {
			Node element = (Node) iter.next();
			if (element instanceof RoleTaskComposite 
					|| element instanceof WorkProductComposite) {
				node = findNode((NodeContainer) element, object);
				if (node != null)
					return node;
			}
			if (object == element.getObject()) {
				return element;
			}
		}
		return null;
	}
	
	public static Node findNode(ActivityDetailDiagram diagram, Object object, Class type) {
		Node node = null;
		for (Iterator iter = diagram.getNodes().iterator(); iter.hasNext();) {
			Node element = (Node) iter.next();
			if (element instanceof RoleTaskComposite 
					|| element instanceof WorkProductComposite) {
				node = findNode((NodeContainer) element, object);
				if (node != null)
					return node;
			}
			if (object == element.getObject() && type.isInstance(element)) {
				return element;
			}
		}
		return null;
	}
	
	public static Link findLink(Node source, Object targetLinkedElement) {
		for (Iterator iter = source.getOutgoingConnections().iterator(); iter.hasNext();) {
			Link link = (Link) iter.next();
			Node target = link.getTarget();
			if(target != null && target.getLinkedElement() == targetLinkedElement) {
				return link;
			}
		}
		return null;
	}

	private static String getTypeString(int type) {
		switch (type) {
		case TypedNode.DECISION:
			return GRAPH_NODE_DECISION;
		case TypedNode.END:
			return GRAPH_NODE_END;
		case TypedNode.START:
			return GRAPH_NODE_START;
		case TypedNode.SYNCH_BAR:
			return GRAPH_NODE_SYNCH_BAR;
		case TypedNode.FREE_TEXT:
			return GRAPH_NODE_FREE_TEXT;
		}
		return null;
	}

	public static GraphNode newTypedGraphNode(int type) {
		GraphNode graphNode = UmaFactory.eINSTANCE.createGraphNode();
		SimpleSemanticModelElement bridge = UmaFactory.eINSTANCE
				.createSimpleSemanticModelElement();
		bridge.setTypeInfo(getTypeString(type));
		graphNode.setSemanticModel(bridge);
		org.eclipse.epf.uma.Point point = UmaFactory.eINSTANCE.createPoint();
		point.setX(new Double(-1));
		point.setY(new Double(-1));
		graphNode.setPosition(point);
		org.eclipse.epf.uma.Dimension dim = UmaFactory.eINSTANCE.createDimension();
		dim.setWidth(new Double(-1));
		dim.setHeight(new Double(-1));
		graphNode.setSize(dim);

		if (type == TypedNode.FREE_TEXT) {
			graphNode.setName(DiagramResources.addFreeTxt); 
		}
		if (type == TypedNode.FREE_TEXT) {
			Property property = UmaFactory.eINSTANCE.createProperty();
			property.setKey(GRAPH_NODE_FREE_TEXT);
			property.setValue(DiagramResources.addFreeTxt); 
			List list = new ArrayList();
			list.add(property);
			graphNode.set(UmaPackage.GRAPH_NODE__PROPERTY, list);
		}

		return graphNode;
	}

	/**
	 * @param link
	 */
	public static void removeGraphicalData(Link link) {
		// GraphicalDataManager.getInstance().removeGraphConnectorPair(link.getSource().getGraphNode(),
		// link.getTarget().getGraphNode());
		
		GraphEdge edge = (GraphEdge) link.getObject();
		if(edge != null) {
			GraphicalDataManager.getInstance().removeGraphEdge(edge);
		}
	}

	/**
	 * @param link
	 */
	public static void addGraphicalData(Link link) {
		GraphEdge edge = (GraphEdge) link.getObject();
		if (edge == null) {
			// new link
			//
			edge = GraphicalDataManager.getInstance().addGraphConnectorPair(
					link.getSource().getGraphNode(),
					link.getTarget().getGraphNode());
			link.setObject(edge);

			// save the source end point and target end point from the given
			// link
			//
			link.setSourceEndPoint(link.getSourceEndPoint());
			link.setTargetEndPoint(link.getTargetEndPoint());
		} else {
			// undo deleted link
			//
			GraphNode srcNode = link.getSource().getGraphNode();
			GraphNode targetNode = link.getTarget().getGraphNode();
			Object srcConnector = edge.getAnchor().get(0);
			srcNode.getAnchorage().add((GraphConnector) srcConnector);
			srcNode.getContained().add(edge);
			Object targetConnector = edge.getAnchor().get(1);
			targetNode.getAnchorage().add((GraphConnector) targetConnector);
		}
	}

	public static void setSemanticModel(Link link, WorkOrder workOrder) {
		// set the WorkOrder as the element of the SemanticModel of the link's
		// GraphEdge
		//
		GraphEdge edge = (GraphEdge) link.getObject();
		UMASemanticModelBridge bridge = UmaFactory.eINSTANCE
				.createUMASemanticModelBridge();
		bridge.setElement(workOrder);
		edge.setSemanticModel(bridge);
	}

	// public static Collection getSourceActivityNodes(TypedNode typedNode) {
	// List actNodes = new ArrayList();
	// getSourceActivityNodes(actNodes, typedNode);
	// return actNodes;
	// }

	public static Collection getSourceNodes(TypedNode typedNode, Class type) {
		List actNodes = new ArrayList();
		getSourceNodes(actNodes, typedNode, type);
		return actNodes;
	}

	// public static void getSourceActivityNodes(Collection actNodes, TypedNode
	// typedNode) {
	// for (Iterator iter = typedNode.getIncomingConnections().iterator();
	// iter.hasNext();) {
	// Link link = (Link)iter.next();
	// Node source = link.getSource();
	// if(source instanceof ActivityNode) {
	// actNodes.add(source);
	// }
	// else if(source instanceof TypedNode) {
	// getSourceActivityNodes(actNodes, (TypedNode) source);
	// }
	// }
	// }

	// public static Collection getTargetActivityNodes(TypedNode typedNode) {
	// List actNodes = new ArrayList();
	// getTargetActivityNodes(actNodes, typedNode);
	// return actNodes;
	// }

	public static Collection getTargetNodes(TypedNode typedNode, Class type) {
		List actNodes = new ArrayList();
		getTargetNodes(actNodes, typedNode, type);
		return actNodes;
	}

	// public static void getTargetActivityNodes(Collection actNodes, TypedNode
	// typedNode) {
	// for (Iterator iter = typedNode.getOutgoingConnections().iterator();
	// iter.hasNext();) {
	// Link link = (Link)iter.next();
	// Node target = link.getTarget();
	// if(target instanceof ActivityNode) {
	// actNodes.add(target);
	// }
	// else if(target instanceof TypedNode) {
	// getTargetActivityNodes(actNodes, (TypedNode) target);
	// }
	// }
	// }

	/**
	 * Gets all nodes with the given type that are direct or indirect targets of
	 * the given source typedNode.
	 * 
	 * @param actNodes
	 * @param typedNode
	 * @param type
	 */
	public static void getTargetNodes(Collection actNodes, TypedNode typedNode,
			Class type) {
		for (Iterator iter = typedNode.getOutgoingConnections().iterator(); iter
				.hasNext();) {
			Link link = (Link) iter.next();
			Node target = link.getTarget();
			if (type.isInstance(target)) {
				actNodes.add(target);
			} else if (target instanceof TypedNode) {
				getTargetNodes(actNodes, (TypedNode) target, type);
			}
		}
	}

	public static void getSourceNodes(Collection actNodes, TypedNode typedNode,
			Class type) {
		for (Iterator iter = typedNode.getIncomingConnections().iterator(); iter
				.hasNext();) {
			Link link = (Link) iter.next();
			Node source = link.getSource();
			if (type.isInstance(source)) {
				actNodes.add(source);
			} else if (source instanceof TypedNode) {
				getSourceNodes(actNodes, (TypedNode) source, type);
			}
		}
	}

	public static Node getFirstSourceNode(TypedNode typedNode, Class type) {
		for (Iterator iter = typedNode.getIncomingConnections().iterator(); iter
				.hasNext();) {
			Link link = (Link) iter.next();
			Node source = link.getSource();
			if (type.isInstance(source)) {
				return source;
			} else if (source instanceof TypedNode) {
				source = getFirstSourceNode((TypedNode) source, type);
				if (source != null)
					return source;
			}
		}
		return null;
	}

	public static WorkOrder removeWorkOrder(NamedNodeImpl node,
			Object predBreakdownElement) {
		boolean notify = node.isNotificationEnabled();
		try {
			node.setNotificationEnabled(false);
			return UmaUtil.removeWorkOrder((WorkBreakdownElement) node
					.getObject(), predBreakdownElement);
		} finally {
			node.setNotificationEnabled(notify);
		}
	}

	public static WorkOrder addDefaultWorkOrder(NamedNodeImpl node,
			WorkBreakdownElement predBreakdownElement) {
		boolean notify = node.isNotificationEnabled();
		try {
			node.setNotificationEnabled(false);
			return UmaUtil.createDefaultWorkOrder((WorkBreakdownElement) node
					.getObject(), predBreakdownElement);
		} finally {
			node.setNotificationEnabled(notify);
		}

	}

	/**
	 * @param taskDescriptor
	 * @return
	 */
	public static boolean hasNoRoleDescriptorAssociated(
			TaskDescriptor taskDescriptor) {
		return taskDescriptor.getPerformedPrimarilyBy().isEmpty();
		// commented - requirements not to allow additional performer and
		// assisted by in diagrams.
		// && taskDescriptor.getAdditionallyPerformedBy().isEmpty();
		// && taskDescriptor.getAssistedBy().isEmpty();
	}

	/**
	 * @param diagram
	 * @param node
	 * @return
	 */
	public static boolean contains(NodeContainer container, Node node) {
		for (Iterator iter = container.getNodes().iterator(); iter.hasNext();) {
			Object child = iter.next();
			if (child == node)
				return true;
			if (child instanceof NodeContainer
					&& contains((NodeContainer) child, node)) {
				return true;
			}
		}
		return false;
	}

	public static boolean refreshFromBase(Diagram diagram) {
		// find the base diagram
		//
		SemanticModelBridge modelBridge = diagram.getSemanticModel();
		if (modelBridge instanceof UMASemanticModelBridge) {
			UMASemanticModelBridge umaModelBridge = ((UMASemanticModelBridge) modelBridge);
			Activity act = (Activity) umaModelBridge.getElement();
			Activity base = (Activity) act.getVariabilityBasedOnElement();
			if (base == null
					|| act.getVariabilityType() == VariabilityType.LOCAL_REPLACEMENT) {
				return false;
			}
			int diagramType = GraphicalDataManager.getInstance()
					.getDiagramType(diagram);
			switch (diagramType) {
			case ACTIVITY_DIAGRAM: {
				Diagram baseDiagram = GraphicalDataManager.getInstance()
						.getUMADiagram(base, diagramType, false);
				if (baseDiagram == null)
					return false;
				List oldNodes = new ArrayList();
				Diagram copy = copyDiagram(baseDiagram);
				for (Iterator iter = baseDiagram.getContained().iterator(); iter
						.hasNext();) {
					GraphNode baseNode = (GraphNode) iter.next();
					modelBridge = baseNode.getSemanticModel();
					if (modelBridge instanceof UMASemanticModelBridge) {
						// this is a element's node
						MethodElement e = ((UMASemanticModelBridge) modelBridge)
								.getElement();
						GraphNode node = GraphicalDataManager.findGraphNode(
								diagram, e);
						if (node != null) {
							oldNodes.add(node);
						}
					} else if (isUIGraphNode(baseNode)) {
						GraphNode node = findUIGraphNode(diagram, baseNode
								.getGuid());
						if (node != null) {
							oldNodes.add(node);
						}
					}
				}

				// // remove all the GraphEdges of the old nodes
				// //
				// List removeEdges = new ArrayList();
				// for (Iterator iter = oldNodes.iterator(); iter.hasNext();) {
				// GraphNode node = (GraphNode) iter.next();
				// for (Iterator iterator = node.getContained().iterator();
				// iterator.hasNext();) {
				// Object element = iterator.next();
				// if(element instanceof GraphEdge) {
				// removeEdges.add(element);
				// }
				// }
				// }
				// if(!removeEdges.isEmpty()) {
				// for (Iterator iter = removeEdges.iterator(); iter
				// .hasNext();) {
				// GraphicalDataManager.getInstance().removeGraphEdge((GraphEdge)
				// iter.next());
				// }
				// }

				// remove old nodes
				//
				diagram.getContained().removeAll(oldNodes);

				// remove unused old UI nodes && nodes of contributor/replacer
				for (Iterator iter = diagram.getContained().iterator(); iter
						.hasNext();) {
					GraphNode node = (GraphNode) iter.next();
					if (isUIGraphNode(node)
							&& node.getBriefDescription() != null
							&& node.getBriefDescription().length() > 0
					// && node.getContained().isEmpty()
					) {
						iter.remove();
					} else {
						SemanticModelBridge bridge = node.getSemanticModel();
						if (bridge instanceof UMASemanticModelBridge) {
							MethodElement e = ((UMASemanticModelBridge) bridge)
									.getElement();
							if (e instanceof Activity
									&& ((Activity) e)
											.getVariabilityBasedOnElement() != null) {
								iter.remove();
							}
						}
					}
				}

				// replace associated base element with contributing/replacing
				// element
				//
				for (Iterator iter = act.getBreakdownElements().iterator(); iter
						.hasNext();) {
					Object element = iter.next();
					if (element instanceof Activity) {
						VariabilityElement baseElement = ((Activity) element)
								.getVariabilityBasedOnElement();
						GraphNode node = GraphicalDataManager.findGraphNode(
								copy, baseElement);
						if (node != null) {
							UMASemanticModelBridge bridge = (UMASemanticModelBridge) node
									.getSemanticModel();
							bridge.setElement((MethodElement) element);
						}
					}
				}

				// add new nodes
				//
				diagram.getContained().addAll(copy.getContained());

				break;
			}
			default: {
				Diagram baseDiagram = GraphicalDataManager.getInstance()
						.getUMADiagram(base, diagramType, false);
				if (baseDiagram == null)
					return false;
				List oldNodes = new ArrayList();
				Diagram copy = copyDiagram(baseDiagram);
				for (Iterator iter = baseDiagram.getContained().iterator(); iter
						.hasNext();) {
					GraphNode baseNode = (GraphNode) iter.next();
					modelBridge = baseNode.getSemanticModel();
					if (modelBridge instanceof UMASemanticModelBridge) {
						// this is a element's node
						MethodElement e = ((UMASemanticModelBridge) modelBridge)
								.getElement();
						GraphNode node = GraphicalDataManager.findGraphNode(
								diagram, e);
						if (node != null) {
							oldNodes.add(node);
						}
					}
				}

				// remove old nodes
				//
				diagram.getContained().removeAll(oldNodes);

				// add new nodes
				//
				diagram.getContained().addAll(copy.getContained());

				break;
			}
			}
		}
		return false;
	}

	/**
	 * @param diagram
	 * @param baseGuid
	 *            the GUID of the base UI GraphNode
	 * @return
	 */
	private static GraphNode findUIGraphNode(Diagram diagram, String baseGuid) {
		for (Iterator iter = diagram.getContained().iterator(); iter.hasNext();) {
			GraphNode node = (GraphNode) iter.next();
			if (isUIGraphNode(node)
					&& baseGuid.equals(node.getBriefDescription())) {
				return node;
			}
		}
		return null;
	}

	public static boolean isUIGraphNode(GraphNode gNode) {
		SemanticModelBridge modelBridge = gNode.getSemanticModel();
		if (modelBridge instanceof SimpleSemanticModelElement) {
			String typeInfo = ((SimpleSemanticModelElement) modelBridge)
					.getTypeInfo();
			if (typeInfo.equals(GRAPH_NODE_DECISION)
					|| typeInfo.equals(GRAPH_NODE_END)
					|| typeInfo.equals(GRAPH_NODE_START)
					|| typeInfo.equals(GRAPH_NODE_SYNCH_BAR)
					|| typeInfo.equals(GRAPH_NODE_FREE_TEXT)) {
				return true;
			}
		}
		return false;
	}

	public static Diagram copyDiagram(Diagram baseDiagram) {
		Diagram copy = (Diagram) TngUtil.copy(baseDiagram);

		// HACK:
		// go thru the nodes of the diagram copy, if any node is a UI-only node
		// (see TypedNode)
		// save the GUID of the original one in its briefDescription to remember
		// who is base.
		//
		int size = copy.getContained().size();
		for (int i = 0; i < size; i++) {
			GraphNode gNode = (GraphNode) copy.getContained().get(i);
			if (GraphicalDataHelper.isUIGraphNode(gNode)) {
				gNode.setBriefDescription(((DiagramElement) baseDiagram
						.getContained().get(i)).getGuid());
			}
		}

		return copy;
	}

	/**
	 * Gets all diagrams of this activities.
	 * 
	 * @param selectedActivity
	 * @return
	 */
	public static Collection getDiagrams(Activity act) {
		return getDiagrams(act, false);
	}

	public static Collection getDiagrams(Activity act, boolean create) {
		ArrayList diagrams = new ArrayList();
		for (int i = 0; i < DIAGRAM_TYPES.length; i++) {
			Diagram diagram = GraphicalDataManager.getInstance().getUMADiagram(
					act, DIAGRAM_TYPES[i], create);
			if (diagram != null) {
				diagrams.add(diagram);
			}
		}
		return diagrams;
	}

	public static String getDiagramTypeText(Diagram diagram) {
		int type = GraphicalDataManager.getInstance().getDiagramType(diagram);
		if (type != -1) {
			return DIAGRAM_TYPE_TEXTS[type];
		}
		return DiagramResources.type_unknown; 
	}

	/**
	 * Sets or unsets suppressed flag of all diagrams in the given process
	 * 
	 * @param proc
	 */
	public static void setAllDiagramSuppressed(Process proc, boolean suppressed) {
		Iterator iter = new AbstractTreeIterator(proc) {

			/**
			 * Comment for <code>serialVersionUID</code>
			 */
			private static final long serialVersionUID = -618949014476371114L;

			protected Iterator getChildren(Object object) {
				Activity act = (Activity) object;
				ArrayList children = new ArrayList();
				for (Iterator iterator = act.getBreakdownElements().iterator(); iterator
						.hasNext();) {
					Object element = iterator.next();
					if (element instanceof Activity) {
						children.add(element);
					}
				}
				return children.iterator();
			}

		};

		while (iter.hasNext()) {
			Collection diagrams = getDiagrams((Activity) iter.next(),
					suppressed);
			for (Iterator iterator = diagrams.iterator(); iterator.hasNext();) {
				Diagram diagram = (Diagram) iterator.next();
				diagram.setSuppressed(Boolean.valueOf(suppressed));
			}
		}
	}

	// public static boolean removeUMAPoint(Collection umaPoints, int x, int y)
	// {
	// for (Iterator iter = umaPoints.iterator(); iter.hasNext();) {
	// org.eclipse.epf.uma.Point p = (org.eclipse.epf.uma.Point) iter.next();
	// if(p.getX().intValue() == x && p.getY().intValue() == y) {
	// iter.remove();
	// return true;
	// }
	// }
	// return false;
	// }

	public static void removeLink(Link link) {
		Node sourceNode = link.getSource();
		Node targetNode = link.getTarget();
		boolean srcNotify = sourceNode != null ? sourceNode.eDeliver() : false;
		boolean targetNotify = targetNode != null ? targetNode.eDeliver()
				: false;
		try {
			if (sourceNode != null) {
				sourceNode.eSetDeliver(false);
			}
			if (targetNode != null) {
				targetNode.eSetDeliver(false);
			}
			link.setSource(null);
			link.setTarget(null);
		} finally {
			if (sourceNode != null) {
				sourceNode.eSetDeliver(srcNotify);
			}
			if (targetNode != null) {
				targetNode.eSetDeliver(targetNotify);
			}
		}
	}

	/**
	 * Finds the object or its wrapper in the given collection
	 * 
	 * @param allElements
	 * @param e
	 * @return
	 */
	public static Object findElement(Collection allElements, Object e) {
		for (Iterator iter = allElements.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (e == TngUtil.unwrap(element)) {
				return element;
			}
			// If object (e) is a workproduct descriptor( sub-artifact)
			// passed collection donot have wrapper for sub-artifact, check the
			// container artifact's wrapper.
			//https://bugs.eclipse.org/bugs/show_bug.cgi?id=155914
			if(element instanceof WorkProductDescriptorWrapperItemProvider){
				Object childElement = findElement(((WorkProductDescriptorWrapperItemProvider)element).getChildren(element), e);
				if(childElement != null){
					return childElement;
				}
			}
		}
		return null;
	}

	/*
	 * Method to check if any duplicate predecessor in the predecessors list of
	 * WorkBreakdownElement.
	 */
	public static boolean anyDuplicatePredecessors(WorkBreakdownElement e) {
		List list = new ArrayList();
		List predlist = new ArrayList();
		getPreds(e, predlist);
		for (Iterator itor = predlist.iterator(); itor.hasNext();) {
			Object obj = itor.next();
			if (!list.contains(obj)) {
				list.add(obj);
			} else {
				return true;
			}
		}
		return false;
	}

	/*
	 * Utility method to get all the predecessors activities instead of
	 * workorders.
	 */
	public static void getPreds(WorkBreakdownElement e, List list) {
		List predlist = e.getLinkToPredecessor();
		for (Iterator itor = predlist.iterator(); itor.hasNext();) {
			WorkOrder workorder = (WorkOrder) itor.next();
			list.add(workorder.getPred());
		}
	}

	/*
	 * Method to check before deleting a link. If duplicate predecessor exists
	 * in the legacy data, check if deleting link should remove all the
	 * predecessors or not by verifying if target or indirect target have direct
	 * or indirect links.
	 */
	public static boolean canRemoveAllPreds(Link link, Node oldSource,
			Node oldTarget) {

		if (oldTarget instanceof WorkBreakdownElementNode) {
			List inlist = oldTarget.getIncomingConnections();
			for (Iterator itor = inlist.iterator(); itor.hasNext();) {
				Link incominglink = (Link) itor.next();
				// RATLC00384245 : Predecessor changes should be done only in
				// case of Synchronization Bar.
				if (incominglink.getSource() instanceof TypedNode
						&& ((TypedNode) incominglink.getSource()).getType() == TypedNode.SYNCH_BAR) {
					Collection col = GraphicalDataHelper.getSourceNodes(
							(TypedNode) incominglink.getSource(),
							WorkBreakdownElementNode.class);
					if (col.contains(oldSource)) {
						return false;
					}
				} else if (incominglink.getSource() instanceof WorkBreakdownElementNode) {
					if (incominglink.getSource().equals(oldSource))
						return false;
				}
			}
		}
		return true;
	}

	/*
	 * Method to get the sources of SyncBar inComming connections
	 * and if syncbar have incoming connection from decision point, 
	 * and decision point have incomming connections (workbreaddown elemtns)
	 * collections will ignore all the incoming connection from decision point.  
	 * @return
	 */
	public static void getSyncBarSourceNodes(TypedNode typedNode, Collection actNodes){
		for (Iterator iter = typedNode.getIncomingConnections().iterator(); iter.hasNext();) {
				Link link = (Link) iter.next();
				Node source = link.getSource();
				if(source instanceof WorkBreakdownElementNode){
					actNodes.add(source);
				}else if(source instanceof TypedNode){
					if(((TypedNode)source).getType() == TypedNode.SYNCH_BAR)
						getSyncBarSourceNodes((TypedNode)source,actNodes);
				}
		}
	}
	/*
	 * Method to collect synchronization bar outgoing connection
	 * except any connection going from decision points.  
	 */
	public static void getSyncBarTargetNodes(TypedNode typedNode, Collection actNodes){
		for (Iterator iter = typedNode.getOutgoingConnections().iterator(); iter.hasNext();) {
				Link link = (Link) iter.next();
				Node target = link.getTarget();
				if(target instanceof WorkBreakdownElementNode){
					actNodes.add(target);
				}else if(target instanceof TypedNode){
					if(((TypedNode)target).getType() == TypedNode.SYNCH_BAR)
						getSyncBarTargetNodes((TypedNode)target, actNodes);
				}
		}
	}
	
	public static Node findNode(NodeContainer container, Object object, Class nodeType) {
		for (Iterator iter = container.getNodes().iterator(); iter.hasNext();) {
			Node element = (Node) iter.next();
			if (object == element.getObject() && nodeType.isInstance(element)) {
				return element;
			}
		}
		return null;
	}
	

	/**
	 * Convenient method to add a property to GraphNode property list
	 * (UmaPackage.GRAPH_NODE__PROPERTY) Verifies property exists or not, if not
	 * creates a property with given key and value. and set into graph node
	 * property list, if exists just sets value for property.
	 * 
	 * @param graphNode
	 * @param key
	 * @param value
	 * @return Property
	 * @author skannoor
	 */
	public static Property createProperty(GraphNode graphNode, String key,
			String value) {
		Property property = null;
		if (graphNode != null) {
			property = findProperty(graphNode, key);
		}
		if (property == null) {
			property = UmaFactory.eINSTANCE.createProperty();
			property.setKey(key);
			property.setValue(value);
			List list = graphNode.getList(UmaPackage.GRAPH_NODE__PROPERTY);
			if (list == null) {
				list = new ArrayList();
				graphNode.set(UmaPackage.GRAPH_NODE__PROPERTY, list);
			}
			list.add(property);
		} else {
			property.setValue(value);
		}
		return property;
	}
	
	/**
	 * Accesible method for ActivityDetailDiagram, to get a autolayout flag from
	 * GraphhNode property list. return string can be
	 * GraphicalDataHelper.PROP_AUTOLAYOUT_VALUE_TRUE,
	 * GraphicalDataHelper.PROP_AUTOLAYOUT_VALUE_FALSE, or null.
	 * 
	 * @param diagram
	 * @return String
	 */
	public static String getAutoLayoutFlag(ActivityDetailDiagram diagram) {
		String flag = null;
		GraphNode graphNode = diagram.getGraphNode();
		if (graphNode != null) {
			List propList = graphNode.getList(UmaPackage.GRAPH_NODE__PROPERTY);
			if (propList != null && propList.size() > 0) {
				Property property = GraphicalDataHelper.findProperty(graphNode,
						GraphicalDataHelper.PROP_AUTO_LAYOUT);
				if (property != null) {
					flag = property.getValue();
				}
			}
		}
		return flag;
	}
	
	public static boolean isAutoLayout(ActivityDetailDiagram diagram){
		GraphNode graphNode = diagram.getGraphNode();
		if(graphNode != null){
			List propList = graphNode.getList(UmaPackage.GRAPH_NODE__PROPERTY);
			if(propList != null && propList.size() > 0){
				Property property = GraphicalDataHelper.findProperty(
					graphNode, GraphicalDataHelper.PROP_AUTO_LAYOUT);
				if(property != null){
					String temp = property.getValue();
					if(temp != null && temp != "" &&  //$NON-NLS-1$
							(GraphicalDataHelper.PROP_AUTO_LAYOUT_VALUE_TRUE.equals(temp)
									|| GraphicalDataHelper.PROP_AUTO_LAYOUT_VALUE_FALSE.equals(temp))){
						return new Boolean(temp).booleanValue();
					}
				}
			}
		}
		return false;
	}
	
	public static int getTasksPerRow(){
//		String count = LibraryEditPlugin.getDefault().getPreferenceStore()
//				.getString(GraphicalDataHelper.ADD_DIAGRAM_TASKS_PER_ROW);
//		if (count != null && count.length() > 0) {
//			try {
//				int i = Integer.parseInt(count);
//				return i;
//			} catch (NumberFormatException ne) {
//				return 10;
//			}
//		}
//		return 10;
		
		int count = Providers.getAuthoringPluginPreferenceStore().getInt(GraphicalDataHelper.ADD_DIAGRAM_TASKS_PER_ROW);
		if (count <= 0)
			return 10;
		return count;
	}

	/**
	 * @param list the list of {@link Property} objects
	 * @param key
	 * @return
	 */
	public static Property getPropertyByKey(List list, String key) {
		if (!list.isEmpty()) {
			for (Iterator iror = list.iterator(); iror.hasNext();) {
				Property property = (Property) iror.next();
				if (property != null) {
					if (property.getKey().equals(key)) {
						return property;
					}
//						else {
//						return null;
//					}
				}
			}
		}
		return null;
	}

	public static Property getProperty(Node node, String key) {
		GraphNode graphNode = node.getGraphNode();
		if (graphNode != null) {
			List list = graphNode.getProperty();
			if (list != null && !list.isEmpty()) {
				for (int i = list.size() - 1; i > -1; i--) {
					Property prop = (Property) list.get(i);
					if (key.equals(prop.getKey())) {
						return prop;
					}
				}
			}
		}
		return null;
	}
}
