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
package org.eclipse.epf.diagram.model.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.epf.diagram.model.ActivityDiagram;
import org.eclipse.epf.diagram.model.Link;
import org.eclipse.epf.diagram.model.ModelFactory;
import org.eclipse.epf.diagram.model.ModelPackage;
import org.eclipse.epf.diagram.model.Node;
import org.eclipse.epf.diagram.model.TypedNode;
import org.eclipse.epf.diagram.model.util.GraphicalDataHelper;
import org.eclipse.epf.diagram.model.util.IActivityDiagramChangeListener;
import org.eclipse.epf.diagram.model.util.IAdapterFactoryFilter;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.process.ActivityWrapperItemProvider;
import org.eclipse.epf.library.edit.process.BSActivityItemProvider;
import org.eclipse.epf.library.edit.process.BreakdownElementWrapperItemProvider;
import org.eclipse.epf.library.edit.process.IBSItemProvider;
import org.eclipse.epf.library.edit.process.WBSActivityItemProvider;
import org.eclipse.epf.library.edit.util.ConfigurableComposedAdapterFactory;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.GraphEdge;
import org.eclipse.epf.uma.GraphNode;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.SemanticModelBridge;
import org.eclipse.epf.uma.SimpleSemanticModelElement;
import org.eclipse.epf.uma.UMASemanticModelBridge;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.WorkBreakdownElement;
import org.eclipse.epf.uma.WorkOrder;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Activity Diagram</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class ActivityDiagramImpl extends DiagramImpl implements ActivityDiagram {
	private class ActivityDiagramChangeListener extends ActivityAdapter
			implements IActivityDiagramChangeListener {

	};

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 */
	protected ActivityDiagramImpl() {
		super();

		diagramChangeListener = new ActivityDiagramChangeListener();
	}

	protected Class getDiagramChangeListenerType() {
		return IActivityDiagramChangeListener.class;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.ACTIVITY_DIAGRAM;
	}

	protected void populateNodes() {
		// Activity act = (Activity) getObject();
		org.eclipse.epf.uma.Diagram diagram = getUMADiagram();

		// add TypedNodes
		//
		List typedNodes = new ArrayList();
		for (Iterator iter = diagram.getContained().iterator(); iter.hasNext();) {
			Object element = iter.next();
			int type = getType(element);
			if (type > 0) {
				TypedNode node = ModelFactory.eINSTANCE.createTypedNode();
				node.setType(type);
				node.setObject(element);
				typedNodes.add(node);
			}
		}
		getNodes().addAll(typedNodes);

		// add WorkBreakdownElementNodes
		//
		addElementNodes();
	}

	private static boolean isValidWorkOrder(WorkOrder wo, Object pred) {
		if (wo.getPred() == pred) {
			return true;
		}

		if (pred instanceof Activity) {
			Activity act = (Activity) pred;
			// check if predecessor reference in the given work order is base of
			// pred
			//
			for (VariabilityElement ve = act.getVariabilityBasedOnElement(); ve != null; ve = ve
					.getVariabilityBasedOnElement()) {
				if (ve == wo.getPred()) {
					return true;
				}
			}
		}

		return false;
	}

	private boolean isValid(Link link) {
		if (link.getSource() == null || link.getTarget() == null
				|| !getNodes().contains(link.getSource())
				|| !getNodes().contains(link.getTarget())) {
			return false;
		}
		return true;
	}

	protected void populateLinks() {
		super.populateLinks();

		// remove the invalid links
		//
		for (Iterator iter = getNodes().iterator(); iter.hasNext();) {
			Node node = ((Node) iter.next());
			WorkBreakdownElement wbe = null;
			if (node.getObject() instanceof WorkBreakdownElement) {
				wbe = (WorkBreakdownElement) node.getObject();
			}
			ArrayList linksToRemove = new ArrayList();
			linkListWalk: for (Iterator iterator = node
					.getIncomingConnections().iterator(); iterator.hasNext();) {
				Link link = (Link) iterator.next();
				if (!isValid(link)) {
					linksToRemove.add(link);
				}

				if (wbe != null) {
					// TODO: need revisit to check the valid connection from a
					// TypedNode
					//
					if (link.getSource() instanceof TypedNode)
						continue linkListWalk;

					Object pred = link.getSource().getObject();
					boolean workOrderFound = false;
					find_WorkOrder: for (Iterator iterator1 = wbe
							.getLinkToPredecessor().iterator(); iterator1
							.hasNext();) {
						WorkOrder wo = (WorkOrder) iterator1.next();
						if (isValidWorkOrder(wo, pred)) {
							workOrderFound = true;
							break find_WorkOrder;
						}
					}
					if (!workOrderFound) {
						// invalid link, remove it
						//
						linksToRemove.add(link);
					}
				}
			}

			for (Iterator iterator = linksToRemove.iterator(); iterator
					.hasNext();) {
				Link link = (Link) iterator.next();
				GraphicalDataHelper.removeLink(link);
			}
			linksToRemove.clear();

			for (Iterator iterator = node.getOutgoingConnections().iterator(); iterator
					.hasNext();) {
				Link link = (Link) iterator.next();
				if (!isValid(link)) {
					linksToRemove.add(link);
				}
			}

			for (Iterator iterator = linksToRemove.iterator(); iterator
					.hasNext();) {
				Link link = (Link) iterator.next();
				GraphicalDataHelper.removeLink(link);
			}
			linksToRemove.clear();
			linksToRemove = null;
		}

		// Check the links of each ActivityNode
		// set the WorkOrder to the link if needed
		// add new link for those WorkOrders that still don't have the
		// corresponding link
		//
		AdapterFactory adapterFactory = getAdapterFactory();
		
		for (Iterator iter = getNodes().iterator(); iter.hasNext();) {
			Node node = ((Node) iter.next());
			if (node.getObject() instanceof WorkBreakdownElement) {
				List list = new ArrayList(); 
				// Get the raw data of workorders for object.
				WorkBreakdownElement local = (WorkBreakdownElement) node.getObject();
				list.addAll(local.getLinkToPredecessor());
				
				// Get the Predecessor List on top of raw data, this is need for in case of extend.
				ITreeItemContentProvider adapter = null;
				adapter = (ITreeItemContentProvider)adapterFactory
									.adapt(local, ITreeItemContentProvider.class);
				if(adapter instanceof IBSItemProvider){
					list.addAll(((IBSItemProvider)adapter).getPredecessors());
				}
				
				// Iterate work orders and create links.
				for (Iterator iterator = list.iterator(); iterator
						.hasNext();) {
					Object next = iterator.next();
					WorkOrder workOrder = null;
					BreakdownElement pred = null;
					if(next instanceof WorkOrder){
						workOrder = (WorkOrder)next;
						pred = workOrder.getPred();
					}
					if(next instanceof WBSActivityItemProvider){
						pred = (BreakdownElement)((WBSActivityItemProvider)next).getTarget(); 
					}
					
					if (pred != null && pred instanceof WorkBreakdownElement) {
						Node predNode = GraphicalDataHelper.findNode(this,
								pred, true);
						if (predNode != null) {
							// check if there is a link for this work order
							// already
							//
							boolean linkFound = false;
							find_link: for (Iterator iterator1 = node
									.getIncomingConnections().iterator(); iterator1
									.hasNext();) {
								Link link = (Link) iterator1.next();
								if (link.getSource() == predNode) {
									// link already exists
									// check if work order is set to this link
									//
									linkFound = true;
									GraphEdge edge = (GraphEdge) link
											.getObject();
									if (edge.getSemanticModel() == null) {
										GraphicalDataHelper.setSemanticModel(
												link, workOrder);
									}
									break find_link;
								}
							}
							if (!linkFound) {
								// check if this WorkOrder can be represented
								// via links of TypedNodes
								//
								if (!canReachAsFirstActivityNode(predNode, node)) {
									// add new link for this work order
									//
									NamedNodeImpl nodeImpl = ((NamedNodeImpl) node);
									NamedNodeImpl predNodeImpl = (NamedNodeImpl) predNode;
									boolean oldNotify = nodeImpl.notificationEnabled;
									boolean predNodeNotify = predNodeImpl.notificationEnabled;
									try {
										nodeImpl.notificationEnabled = false;
										predNodeImpl.notificationEnabled = false;
										nodeImpl.addIncomingConnection(pred); 
									} finally {
										nodeImpl.notificationEnabled = oldNotify;
										predNodeImpl.notificationEnabled = predNodeNotify;
									}
								}
							}
						}
					}
				}
			}
		}
		cleanDuplicateLinks();
	}

	private void cleanDuplicateLinks() {
		List duplicateLinks = new ArrayList();
		for (Iterator iter = getNodes().iterator(); iter.hasNext();) {
			Object node = iter.next();
			if (node instanceof Node) {
				Node wbNode = (Node) node;
				if(wbNode.getIncomingConnections() != null){
					findDuplicateLinks(duplicateLinks, wbNode.getIncomingConnections());
				}
				if(wbNode.getOutgoingConnections() !=null){
					findDuplicateLinks(duplicateLinks, wbNode.getOutgoingConnections());
				}
			}
			
		}
		for (Iterator iterator = duplicateLinks.iterator(); iterator.hasNext();) {
			Link link = (Link) iterator.next();
			GraphicalDataHelper.removeLink(link);
		}
		duplicateLinks.clear();
		duplicateLinks = null;
	}

	private void findDuplicateLinks(List duplicateLinks, List links) {
		for (int i = 0; i < links.size(); i++) {
			Link link = (Link) links.get(i);
			if(!duplicateLinks.contains(link)){
				for (int j = i + 1; j < links.size(); j++) {
					Link link1 = (Link) links.get(j);
					if (link1.getSource() == link.getSource()
							&& link1.getTarget() == link.getTarget()) {
						duplicateLinks.add(link1);
					}
				}
			}
		}
	}

	/**
	 * Checks if Node src can reach Node target as the first ActivityNode in the
	 * diagram
	 * 
	 * @param src
	 * @param target
	 * @return
	 */
	private static boolean canReachAsFirstActivityNode(Node src, Node target) {
		for (Iterator iter = src.getOutgoingConnections().iterator(); iter
				.hasNext();) {
			Link link = (Link) iter.next();
			if (link.getTarget() == target) {
				return true;
			}
			if (link.getTarget() instanceof TypedNode
					&& canReachAsFirstActivityNode(link.getTarget(), target)) {
				return true;
			}
		}
		return false;
	}

	// private List toNodes(List activities) {
	// List list = new ArrayList();
	// for (Iterator iter = activities.iterator(); iter.hasNext();) {
	// list.add(toNode((MethodElement) iter.next()));
	// }
	// return list;
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.diagram.model.impl.DiagramImpl#newNode()
	 */
	protected Node newNode() {
		return ModelFactory.eINSTANCE.createWorkBreakdownElementNode();
	}

	public Collection getChildren() {
		ITreeItemContentProvider adapter = getAdapter();
		if (adapter != null) {
			
			//Turn off the VariabilityInfo for the activity diagram.
			Object obj = null;
			boolean enableVariabilityInfo = false;
			if(adapter instanceof ActivityWrapperItemProvider){
				obj = ((ActivityWrapperItemProvider)adapter).getDelegatingItemProvider();
			}
			if(adapter instanceof BSActivityItemProvider){
				obj = adapter;
			}
			
			if(obj instanceof BSActivityItemProvider){
				enableVariabilityInfo = ((BSActivityItemProvider)obj).isEnableVariabilityInfo();
				((BSActivityItemProvider)obj).setEnableVariabilityInfo(false);
			}
			// end of variability info 
			
			
			// return adapter.getChildren(getObject());
			// commented above line, For diagrams - rollup should be false, for
			// handling rollup state below code.
			List children = new ArrayList();
			extractChildren(adapter, getObject(), children, true);
			
			// reset variabilityinfo status back
			if(obj instanceof BSActivityItemProvider){
				((BSActivityItemProvider)obj).setEnableVariabilityInfo(enableVariabilityInfo);
			}
			
			return children;
		} else {
			return Collections.EMPTY_LIST;
		}
	}

	private void addElementNodes() {

		ArrayList nodes = new ArrayList();
		for (Iterator iter = getChildren().iterator(); iter.hasNext();) {
			Object e = iter.next();
			Object child = TngUtil.unwrap(e);
			if (child instanceof WorkBreakdownElement) {
				NamedNodeImpl node = (NamedNodeImpl) toNode((MethodElement) child);
				if (e instanceof BreakdownElementWrapperItemProvider) {
					BreakdownElementWrapperItemProvider wrapper = ((BreakdownElementWrapperItemProvider) e);
					if (wrapper.isReadOnly()) {
						node.itemProvider = wrapper;
						// TODO: check if readOnly attribute is still needed
						// after introduction of itemProvider
						//
						node.readOnly = true;
					}
				}
				nodes.add(node);
			}
		}

		getNodes().addAll(nodes);
	}

	private static int getType(Object obj) {
		if (obj instanceof GraphNode) {
			GraphNode node = (GraphNode) obj;
			SemanticModelBridge modelBridge = node.getSemanticModel();
			if (modelBridge instanceof SimpleSemanticModelElement) {
				String type = ((SimpleSemanticModelElement) modelBridge)
						.getTypeInfo();
				if (GraphicalDataHelper.GRAPH_NODE_SYNCH_BAR.equals(type)) {
					return TypedNode.SYNCH_BAR;
				} else if (GraphicalDataHelper.GRAPH_NODE_DECISION.equals(type)) {
					return TypedNode.DECISION;
				} else if (GraphicalDataHelper.GRAPH_NODE_END.equals(type)) {
					return TypedNode.END;
				} else if (GraphicalDataHelper.GRAPH_NODE_START.equals(type)) {
					return TypedNode.START;
				} else if (GraphicalDataHelper.GRAPH_NODE_FREE_TEXT
						.equals(type)) {
					return TypedNode.FREE_TEXT;
				}
			}
		}
		return -1;
	}

	protected int getType() {
		return GraphicalDataHelper.ACTIVITY_DIAGRAM;
	}

	protected List getBreakdownElementTypes() {
		return Collections.singletonList(WorkBreakdownElement.class);
	}

	private int toActivityIndex(int index) {
		if (index == -1)
			return index;
		int size = getNodes().size();
		int i = index + 1;
		if (i == size)
			return -1;
		Node node = (Node) getNodes().get(i);
		if (node == null)
			return -1;

		for (; !(node.getObject() instanceof BreakdownElement) && i < size; i++) {
			node = (Node) getNodes().get(i);
		}
		if (i == size)
			return -1;
		Activity act = (Activity) getObject();
		return act.getBreakdownElements().indexOf(node.getObject());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.diagram.model.impl.DiagramImpl#addToUmaModel(int,
	 *      org.eclipse.epf.diagram.model.Node)
	 */
	protected void addToUmaModel(int position, Node addedNode) {
		if (addedNode.getObject() instanceof BreakdownElement) {
			Activity act = (Activity) getObject();

			// translate ActivityNode index to Activity index
			//
			int i = toActivityIndex(position);
			// System.out.println("DiagramImpl.addToUmaModel():
			// WorkBreakdownElement index: " + i);
			if (i == -1) {
				act.getBreakdownElements().add((BreakdownElement) addedNode.getObject());
			} else {
				act.getBreakdownElements().add(i, (BreakdownElement) addedNode.getObject());
			}
		}

		super.addToUmaModel(position, addedNode);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.diagram.model.impl.DiagramImpl#addNode(java.util.Collection,
	 *      java.lang.Object)
	 */
	protected Node addNode(Collection nodes, Object obj) {
		if (obj instanceof Activity) {
			Activity act = (Activity) obj;
			VariabilityElement base = act.getVariabilityBasedOnElement();
			if (base != null) {
				// find existing node for base and link it to the activity
				//
				NodeImpl baseNode = (NodeImpl) GraphicalDataHelper.findNode(
						this, base);
				if (baseNode != null) {
					GraphNode graphNode = baseNode.getGraphNode();
					UMASemanticModelBridge bridge = (UMASemanticModelBridge) graphNode
							.getSemanticModel();
					if (bridge.getElement() != act) {
						bridge.setElement(act);
					}
				} else {
					baseNode = (NodeImpl) GraphicalDataHelper.findNode(this,
							act);
				}
				if (baseNode != null) {
					// disassociate with the base
					//
					if (baseNode.methodElementAdapter != null) {
						base.eAdapters().remove(baseNode.methodElementAdapter);
					}

					baseNode.basicSetObject(act);
					baseNode.setReadOnly(false);

					return null;
				}
			}
		}
		return super.addNode(nodes, obj);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.diagram.model.impl.DiagramImpl#removeNode(java.lang.Object)
	 */
	protected boolean removeNode(Object obj) {
		if (obj instanceof Activity) {
			Activity act = (Activity) obj;
			VariabilityElement base = act.getVariabilityBasedOnElement();
			if (base != null) {
				// find existing node for the old activity and relink it to its
				// base
				//
				NodeImpl node = (NodeImpl) GraphicalDataHelper.findNode(this,
						act);
				if (node != null) {
					GraphNode graphNode = node.getGraphNode();
					UMASemanticModelBridge bridge = (UMASemanticModelBridge) graphNode
							.getSemanticModel();
					if (bridge.getElement() != base) {
						bridge.setElement(base);
					}
				} else {
					node = (NodeImpl) GraphicalDataHelper.findNode(this, base);
				}
				if (node != null) {
					// disassociate with the old activity
					//
					if (node.methodElementAdapter != null) {
						act.eAdapters().remove(node.methodElementAdapter);
					}

					node.basicSetObject(base);
					node.setReadOnly(true);

					return false;
				}
			}
		}
		return super.removeNode(obj);
	}
	/**
	 * Moved code from getChildren. getAdapter() will return Adapter,
	 * which will allow us to find itemprovider for the children. 
	 * 
	 * @return
	 */
	private ITreeItemContentProvider getAdapter(){
		ITreeItemContentProvider adapter = null;
		if (wrapper != null) {
			adapter = wrapper;
		} else {
			
			adapter =(ITreeItemContentProvider)getAdapterFactory().adapt(
					getObject(), ITreeItemContentProvider.class);
		}
		return adapter;
	}
	
	private AdapterFactory getAdapterFactory(){
		AdapterFactory adapterFactory = null;
		if (filter == null) {
			adapterFactory = TngAdapterFactory.INSTANCE.getWBS_ComposedAdapterFactory();
		} else if (filter instanceof IAdapterFactoryFilter) {
			adapterFactory = (ConfigurableComposedAdapterFactory) ((IAdapterFactoryFilter) filter)
					.getWBSAdapterFactory();
		}
		return adapterFactory;
	}
} // ActivityDiagramImpl
