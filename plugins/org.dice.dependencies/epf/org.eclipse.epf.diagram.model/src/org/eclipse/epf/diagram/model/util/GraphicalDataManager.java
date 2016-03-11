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
package org.eclipse.epf.diagram.model.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.epf.library.edit.ICommandListener;
import org.eclipse.epf.library.edit.process.command.VaryActivityCommand;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.Diagram;
import org.eclipse.epf.uma.GraphConnector;
import org.eclipse.epf.uma.GraphEdge;
import org.eclipse.epf.uma.GraphNode;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.SemanticModelBridge;
import org.eclipse.epf.uma.UMASemanticModelBridge;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.VariabilityType;
import org.eclipse.epf.uma.WorkOrder;
import org.eclipse.epf.uma.WorkProductDescriptor;
import org.eclipse.epf.uma.util.UmaUtil;


/**
 * Diagram data stored in UMA: SemanticModelBridge .Presentation diagram type,
 * its value can be one of following constants defined in GraphicalDataHelper
 * DIAGRAM_WORKFLOW, DIAGRAM_WORK_PRODUCT_DEPENDENCY, DIAGRAM_ACTIVITY_DETAIL
 * 
 * UMASemanticModelBridge .Element BreakdownElement that the DiagramElement of
 * this SemanticModelBridge represents
 * 
 * SimpleSemanticModelElement .TypeInfo type information for TypedNode, its
 * value can be one of the constants defined in GraphicalDataHelper
 * wpCompositeType WorkProductComposite type
 * 
 * DiagramElement .Property (list of properties)
 * 
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public final class GraphicalDataManager {
	private static final String[] diagramPresentations = new String[] {
			GraphicalDataHelper.DIAGRAM_WORKFLOW,
			GraphicalDataHelper.DIAGRAM_WORK_PRODUCT_DEPENDENCY,
			GraphicalDataHelper.DIAGRAM_ACTIVITY_DETAIL };

	private static final Class[] childTypes = new Class[] { Activity.class,
			WorkProductDescriptor.class, Descriptor.class };

	private static final Class[] diagramChangeListenerClasses = new Class[] {
			IActivityDiagramChangeListener.class,
			IWPDDiagramChangeListener.class,
			IActivityDetailDiagramChangeListener.class };

	private static GraphicalDataManager instance = null;

	//private Map graphicalDataMap = new HashMap();

	private List<ICommandListener> commandListeners;

	private GraphicalDataManager() {
	}

	public static GraphicalDataManager getInstance() {
		if (instance == null) {
			synchronized (GraphicalDataManager.class) {
				if (instance == null) {
					instance = new GraphicalDataManager();
				}
			}
		}
		return instance;
	}

	// public GraphNode getGraphNode(Activity act, int diagramType, Activity
	// parent) {
	// // ItemProviderAdapter adapter = (ItemProviderAdapter)
	// TngAdapterFactory.INSTANCE.getWBS_ComposedAdapterFactory().adapt(act,
	// ITreeItemContentProvider.class);
	// // Object parent = adapter.getParent(act);
	// // if(parent instanceof Activity) {
	// if(parent == null) {
	// // new Activity
	// //
	// return createGraphNode(act);
	// }
	// if(!act.getSuperActivities().contains(parent)) {
	// throw new IllegalArgumentException("Invalid super activity for " + act +
	// ": " + parent);
	// }
	// Activity parentAct = (Activity) parent;
	// Diagram diagram = getDiagram(parentAct, diagramType).getUMADiagram();
	// return getGraphNode(diagram, act);
	// }

	/**
	 * Gets the GraphNode of the given method element for the given diagram.
	 * Create the GraphNode if it does not exist yet.
	 * 
	 * @param diagram
	 * @param methodElement
	 * @return
	 */
	public GraphNode getGraphNode(Diagram diagram, MethodElement methodElement) {
		if (diagram == null) {
			// new MethodElement
			//
			return createGraphNode(methodElement);
		}
		GraphNode node = findGraphNode(diagram, methodElement);
		if (node == null) {
			node = createGraphNode(methodElement);
			createGraphConnectors(node, diagram);
			diagram.getContained().add(node);
		}
		return node;
	}

	public static GraphNode findGraphNode(Diagram diagram, Object methodElement) {
		for (Iterator iter = diagram.getContained().iterator(); iter.hasNext();) {
			GraphNode element = (GraphNode) iter.next();
			if (element.getSemanticModel() instanceof UMASemanticModelBridge
					&& methodElement == ((UMASemanticModelBridge) element
							.getSemanticModel()).getElement()) {
				return element;
			}
		}
		return null;
	}

	// public DiagramElement getDiagramElement(Object object) {
	// if(object instanceof Activity) {
	// return getGraphNode((Activity) object);
	// }
	// else if(object instanceof WorkOrder) {
	// WorkOrder workOrder = (WorkOrder) object;
	// BreakdownElement be = workOrder.getPred();
	// if(be instanceof Activity) {
	// GraphNode graphNode = getGraphNode((Activity) be);
	// List list = graphNode.getContained();
	// Polyline polyline = null;
	// for(int i = 0; i < list.size(); i++) {
	// Object obj = list.get(i);
	// if(obj instanceof Polyline) {
	// polyline = (Polyline) obj;
	// break;
	// }
	// }
	// if(polyline == null) {
	// polyline = UmaFactory.eINSTANCE.createPolyline();
	// list.add(0, polyline);
	// }
	// return polyline;
	// }
	// }
	// return null;
	// }

	/**
	 * @param object
	 * @return
	 */
	private Diagram createDiagram(Activity act, int diagramType) {
		// check if this activity contributes/extends other activity and try
		// copy
		// the existing diagram from the base
		//
		if (ProcessUtil.isExtendingOrLocallyContributing(act)) {
			Diagram baseDiagram = getUMADiagram((Activity) act
					.getVariabilityBasedOnElement(), diagramType, false);
			if (baseDiagram != null) {
				Diagram copy = GraphicalDataHelper.copyDiagram(baseDiagram);

				UMASemanticModelBridge modelBridge = (UMASemanticModelBridge) copy
						.getSemanticModel();
				modelBridge.setElement(act);
				return copy;
			}
		}

		Diagram diagram = UmaFactory.eINSTANCE.createDiagram();
		UMASemanticModelBridge modelBridge = UmaFactory.eINSTANCE
				.createUMASemanticModelBridge();
		modelBridge.setElement(act);
		modelBridge.setPresentation(diagramPresentations[diagramType]);
		diagram.setSemanticModel(modelBridge);

		// getDiagram(act, diagramType).setUMADiagram(diagram);

		populateDiagram(diagram, act, diagramType);

		return diagram;
	}

	private void populateDiagram(Diagram diagram, Activity act, int diagramType) {
		// create GraphNode for each BreakdownElement and add it to the diagram
		//
		List nodes = new ArrayList();
		for (Iterator iter = act.getBreakdownElements().iterator(); iter
				.hasNext();) {
			Object element = iter.next();
			if (childTypes[diagramType].isInstance(element)) {
				GraphNode node = createGraphNode((MethodElement) element);
				diagram.getContained().add(node);
				nodes.add(node);
			}
		}

		// create GraphConnectors for each BreakdownElement
		//
		for (Iterator iter = nodes.iterator(); iter.hasNext();) {
			GraphNode node = (GraphNode) iter.next();
			createGraphConnectors(node, diagram, diagramType);
		}
	}

//	private void populateActivityDiagram(Diagram diagram, Activity act) {
//		// create GraphNode for each child activity and add it to the diagram
//		// 
//		List nodes = new ArrayList();
//		for (Iterator iter = act.getBreakdownElements().iterator(); iter
//				.hasNext();) {
//			Object element = (Object) iter.next();
//			if (element instanceof Activity) {
//				Activity childAct = (Activity) element;
//				GraphNode node = createGraphNode(childAct);
//				diagram.getContained().add(node);
//				nodes.add(node);
//			}
//		}
//
//		// create GraphConnectors for each child activity
//		//
//		for (Iterator iter = nodes.iterator(); iter.hasNext();) {
//			GraphNode node = (GraphNode) iter.next();
//			createGraphConnectors(node, diagram);
//		}
//	}

	/**
	 * @param act
	 * @param diagramType
	 * @return
	 */
	public IDiagramChangeListener getDiagramChangeListener(Activity act,
			int diagramType) {
		for (Iterator iter = act.eAdapters().iterator(); iter.hasNext();) {
			Object element = (Object) iter.next();
			if (diagramChangeListenerClasses[diagramType].isInstance(element)) {
				return (IDiagramChangeListener) element;
			}
		}

		return null;
	}

	public GraphNode createGraphNode(MethodElement element) {
		GraphNode graphNode = UmaFactory.eINSTANCE.createGraphNode();
		UMASemanticModelBridge modelBridge = UmaFactory.eINSTANCE
				.createUMASemanticModelBridge();
		modelBridge.setElement(element);
		graphNode.setSemanticModel(modelBridge);
		org.eclipse.epf.uma.Point point = UmaFactory.eINSTANCE.createPoint();
		point.setX(new Double(-1));
		point.setY(new Double(-1));
		graphNode.setPosition(point);
		org.eclipse.epf.uma.Dimension dim = UmaFactory.eINSTANCE.createDimension();
		dim.setWidth(new Double(-1));
		dim.setHeight(new Double(-1));
		graphNode.setSize(dim);

		return graphNode;
	}

	public GraphEdge addGraphConnectorPair(Diagram diagram,
			GraphNode graphNode, WorkOrder element) {
		GraphConnector srcConnector = UmaFactory.eINSTANCE
				.createGraphConnector();
		graphNode.getAnchorage().add(srcConnector);
		GraphEdge edge = UmaFactory.eINSTANCE.createGraphEdge();
		UMASemanticModelBridge bridge = UmaFactory.eINSTANCE
				.createUMASemanticModelBridge();
		bridge.setElement(element);
		edge.setSemanticModel(bridge);
		graphNode.getContained().add(edge);
		edge.getAnchor().add(srcConnector);

		GraphNode succNode = getGraphNode(diagram, UmaUtil
				.getOwningActivity(element));
		GraphConnector targetConnector = UmaFactory.eINSTANCE
				.createGraphConnector();
		succNode.getAnchorage().add(targetConnector);
		edge.getAnchor().add(targetConnector);
		return edge;
	}

	public GraphEdge addGraphConnectorPair(GraphNode srcNode,
			GraphNode targetNode) {
		GraphConnector srcConnector = UmaFactory.eINSTANCE
				.createGraphConnector();
		srcNode.getAnchorage().add(srcConnector);
		GraphEdge edge = UmaFactory.eINSTANCE.createGraphEdge();
		srcNode.getContained().add(edge);
		edge.getAnchor().add(srcConnector);

		GraphConnector targetConnector = UmaFactory.eINSTANCE
				.createGraphConnector();
		targetNode.getAnchorage().add(targetConnector);
		edge.getAnchor().add(targetConnector);
		return edge;
	}

	public void removeGraphEdge(GraphEdge edge) {
		EcoreUtil.remove(edge);
		for (Iterator iter = edge.getAnchor().iterator(); iter.hasNext();) {
			GraphConnector conn = (GraphConnector) iter.next();
			EcoreUtil.remove(conn);
		}
	}

	// public void removeGraphConnectorPair(GraphNode srcNode, GraphNode
	// targetNode) {
	// // find the source GraphConnector and remove it, its GraphEdge, and its
	// target GraphConnector
	// //
	// for (Iterator iter = srcNode.getAnchorage().iterator(); iter.hasNext();)
	// {
	// GraphConnector connector = (GraphConnector) iter.next();
	// for (Iterator iterator = connector.getGraphEdge().iterator(); iterator
	// .hasNext();) {
	// GraphEdge edge = (GraphEdge) iterator.next();
	// Object targetConnector = edge.getAnchor().get(1);
	// if(targetNode.getAnchorage().remove(targetConnector)) {
	// srcNode.getContained().remove(edge);
	// // don't remove the edge from connector to support undo
	// //
	// // iterator.remove();
	// }
	// }
	// if(connector.getGraphEdge().isEmpty()) {
	// iter.remove();
	// }
	// }
	// }

	public void removeGraphConnectionPair(GraphNode graphNode,
			WorkOrder workOrder) {
		for (Iterator iter = graphNode.getAnchorage().iterator(); iter
				.hasNext();) {
			GraphConnector conn = (GraphConnector) iter.next();
			GraphEdge edge = (GraphEdge) conn.getGraphEdge().get(0);
			if (edge.eContainer() == graphNode) {
				GraphConnector conn2 = (GraphConnector) edge.getAnchor().get(1);
				SemanticModelBridge bridge = conn2.getGraphElement()
						.getSemanticModel();
				if (bridge instanceof UMASemanticModelBridge
						&& ((UMASemanticModelBridge) bridge).getElement() == UmaUtil
								.getOwningActivity(workOrder)) {
					conn2.getGraphElement().getAnchorage().remove(conn2);
					iter.remove();
					graphNode.getContained().remove(edge);
					return;
				}
			}
		}
	}

	private void createGraphConnectors(GraphNode graphNode, Diagram diagram) {
		createGraphConnectors(graphNode, diagram, getDiagramType(diagram));
	}

	int getDiagramType(Diagram diagram) {
		String typeStr = diagram.getSemanticModel().getPresentation();
		for (int i = 0; i < diagramPresentations.length; i++) {
			if (diagramPresentations[i].equals(typeStr))
				return i;
		}
		return -1;
	}

	private void createGraphConnectors(GraphNode graphNode, Diagram diagram,
			int diagramType) {
		MethodElement e = ((UMASemanticModelBridge) graphNode
				.getSemanticModel()).getElement();
		switch (diagramType) {
		case GraphicalDataHelper.ACTIVITY_DIAGRAM:
			if (e instanceof Activity) {
				Activity act = (Activity) e;
				// create connectors
				//
				// List list = act.getLinkToPredecessor();
				// int size = list.size();
				//				
				// Activity parent = (Activity)
				// ((UMASemanticModelBridge)diagram.getSemanticModel()).getElement();
				// List breakdownElements = parent.getBreakdownElements();
				// for(int i = 0; i < size; i++) {
				// WorkOrder workOrder = (WorkOrder) list.get(i);
				// if(breakdownElements.contains(workOrder.getPred())) {
				// addGraphConnectorPair(diagram, graphNode, workOrder);
				// }
				// }

				for (Iterator iter = act.getLinkToPredecessor().iterator(); iter
						.hasNext();) {
					WorkOrder workOrder = (WorkOrder) iter.next();
					GraphNode srcNode = findGraphNode(diagram, workOrder
							.getPred());
					if (srcNode != null) {
						addGraphConnectorPair(srcNode, graphNode);
					}
				}
			}

			break;
		case GraphicalDataHelper.WORK_PRODUCT_DEPENDENCY_DIAGRAM:
			if (e instanceof WorkProductDescriptor) {
				WorkProductDescriptor descriptor = (WorkProductDescriptor) e;
				for (Iterator iter = descriptor.getImpacts().iterator(); iter
						.hasNext();) {
					WorkProductDescriptor impactedDescriptor = (WorkProductDescriptor) iter
							.next();
					GraphNode targetNode = GraphicalDataManager.findGraphNode(
							diagram, impactedDescriptor);
					if (targetNode != null) {
						addGraphConnectorPair(graphNode, targetNode);
					}
				}
			}

			break;
		}

	}

	/**
	 * Gets the UMA diagram from the UMA model for the given activity.
	 * 
	 * @param e
	 * @param diagramType
	 *            one of the diagram type constants defined in
	 *            {@link GraphicalDataHelper GraphicalDataHelper }
	 * @param create
	 *            if true, new UMA diagram will be created if the activity does
	 *            not have one already.
	 * @return
	 * @see GraphicalDataHelper#ACTIVITY_DIAGRAM
	 * @see GraphicalDataHelper#ACTIVITY_DETAIL_DIAGRAM
	 * @see GraphicalDataHelper#WORK_PRODUCT_DEPENDENCY_DIAGRAM
	 */
	public Diagram getUMADiagram(Activity e, int diagramType, boolean create) {
		Diagram diagram = null;

		// // look for IDiagramChangeListener in activity's adapter list first
		// IDiagramChangeListener listener = (IDiagramChangeListener)
		// UmaUtil.getAdapter(e, diagramListenerType);
		// if(listener != null) {
		// diagram = listener.getDiagram().getUMADiagram();
		// if(diagram != null) return diagram;
		// }

		Object container = e.eContainer();
		if (container != null) {
			ProcessPackage pkg = (ProcessPackage) container;
			for (Iterator iter = pkg.getDiagrams().iterator(); iter.hasNext();) {
				diagram = (Diagram) iter.next();
				SemanticModelBridge modelBridge = diagram.getSemanticModel();
				if (modelBridge != null
						&& modelBridge instanceof UMASemanticModelBridge
						&& e == ((UMASemanticModelBridge) modelBridge)
								.getElement()
						&& diagramPresentations[diagramType]
								.equals(((UMASemanticModelBridge) modelBridge)
										.getPresentation())) {
					return diagram;
				}
			}
		}

		if (create) {
			// could not find it, create new one
			//
			diagram = createDiagram(e, diagramType);
			if (container != null) {
				((ProcessPackage) container).getDiagrams().add(diagram);
			}
			return diagram;
		}
		return null;
	}

	// public Diagram getUMADiagram(Activity act, int diagramType) {
	// Diagram diagram = null;
	//        
	// // look for IDiagramChangeListener in activity's adapter list first
	// IDiagramChangeListener listener = getDiagramChangeListener(act,
	// diagramType);
	// if(listener != null) {
	// diagram = listener.getDiagram().getUMADiagram();
	// if(diagram != null) return diagram;
	// }
	// Object container = act.eContainer();
	// if(container != null) {
	// ProcessPackage pkg = (ProcessPackage) container;
	// for (Iterator iter = pkg.getDiagrams().iterator(); iter.hasNext();) {
	// diagram = (Diagram) iter.next();
	// SemanticModelBridge modelBridge = diagram.getSemanticModel();
	// if(modelBridge != null && modelBridge instanceof UMASemanticModelBridge
	// && act == ((UMASemanticModelBridge)modelBridge).getElement()
	// &&
	// diagramPresentations[diagramType].equals(((UMASemanticModelBridge)modelBridge).getPresentation()))
	// {
	// return diagram;
	// }
	// }
	// }
	//        
	// // could not find it, create new one
	// //
	// return createDiagram(act, diagramType);
	// }

	/**
	 * @param act
	 * @return
	 */
	public org.eclipse.epf.diagram.model.Diagram getDiagram(Activity act,
			int diagramType) {
		IDiagramChangeListener listener = getDiagramChangeListener(act,
				diagramType);
		return listener == null ? null : listener.getDiagram();
	}

	/**
	 * Gets list of command listeners that will handle the diagram related changes of the commands of interesses
	 * 
	 * @return
	 * @see ICommandListener
	 */
	public List<ICommandListener> getCommandListeners() {
		if (commandListeners == null) {
			commandListeners = new ArrayList<ICommandListener>();
		}

		commandListeners.add(new ICommandListener() {
			
			public void notifyExecuted(Command command) {
				// get contributor/replacer of the activity
				//
				Activity act = (Activity) command.getResult().iterator().next();
				Activity base = (Activity) act.getVariabilityBasedOnElement();
				if (base != null) {
					Activity parentAct = act.getSuperActivities();
					Diagram diagram = getUMADiagram(parentAct,
							GraphicalDataHelper.ACTIVITY_DIAGRAM, false);
					if (diagram != null) {
						// find existing node for base and link it to the
						// contributore/replacer of the activity
						//
						GraphNode graphNode = findGraphNode(diagram, base);
						if (graphNode != null) {
							UMASemanticModelBridge bridge = (UMASemanticModelBridge) graphNode
									.getSemanticModel();
							if (bridge.getElement() != act) {
								bridge.setElement(act);
							}
						}
					}
					
					if(act.getVariabilityType() == VariabilityType.LOCAL_CONTRIBUTION) {
						// copy diagrams and their publishing options
						//
						for (int i = 0; i < GraphicalDataHelper.DIAGRAM_TYPES.length; i++) {
							int diagramType = GraphicalDataHelper.DIAGRAM_TYPES[i];
							diagram = getUMADiagram(base, diagramType, false);
							if (diagram != null) {
								Diagram copy = getUMADiagram(act, diagramType, true);
								copy.setSuppressed(diagram.getSuppressed());
							}
						}
					}
				}
			}

			public Class getCommandType() {
				return VaryActivityCommand.class;
			}

			public void preUndo(Command command) {
				// get old contributor/replacer of the activity
				//
				Activity act = (Activity) command.getResult().iterator().next();
//				VaryActivityCommand cmd = (VaryActivityCommand) command;
//				BreakdownElementWrapperItemProvider adapter = cmd.getWrapper();
				VariabilityElement baseAct = act.getVariabilityBasedOnElement();
				if (baseAct != null) {
					Activity parentAct = act.getSuperActivities();
					Diagram diagram = getUMADiagram(parentAct,
							GraphicalDataHelper.ACTIVITY_DIAGRAM, false);
					if (diagram != null) {
						// find existing node for the old contributore/replacer
						// old and relink it to the base activity
						//
						GraphNode graphNode = findGraphNode(diagram, act);
						if (graphNode != null) {
							UMASemanticModelBridge bridge = (UMASemanticModelBridge) graphNode
									.getSemanticModel();
							if (bridge.getElement() != baseAct) {
								bridge.setElement(baseAct);
							}
						}
					}
				}
			}

			public void preExecute(Command command) {
				
			}

			public void postUndo(Command command) {
				// TODO Auto-generated method stub
				
			}

		});

		return commandListeners;
	}
}
