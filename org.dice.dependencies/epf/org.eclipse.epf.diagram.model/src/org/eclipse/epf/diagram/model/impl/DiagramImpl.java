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

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.epf.diagram.model.Diagram;
import org.eclipse.epf.diagram.model.DiagramResources;
import org.eclipse.epf.diagram.model.Link;
import org.eclipse.epf.diagram.model.ModelFactory;
import org.eclipse.epf.diagram.model.ModelPackage;
import org.eclipse.epf.diagram.model.NamedNode;
import org.eclipse.epf.diagram.model.Node;
import org.eclipse.epf.diagram.model.TypedNode;
import org.eclipse.epf.diagram.model.util.GraphicalDataHelper;
import org.eclipse.epf.diagram.model.util.GraphicalDataManager;
import org.eclipse.epf.diagram.model.util.IDiagramChangeListener;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.process.BSActivityItemProvider;
import org.eclipse.epf.library.edit.process.BreakdownElementWrapperItemProvider;
import org.eclipse.epf.library.edit.process.IBSItemProvider;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.Suppression;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.GraphNode;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.SemanticModelBridge;
import org.eclipse.epf.uma.SimpleSemanticModelElement;
import org.eclipse.epf.uma.UmaPackage;


/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Diagram</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class DiagramImpl extends NodeContainerImpl implements Diagram {

	private boolean newDiagram;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected DiagramImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.DIAGRAM;
	}

	// -------------------//
	// Start custom code //
	// -------------------//

	protected class ActivityAdapter extends TransactionalNodeLink implements
			IDiagramChangeListener {
		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.epf.diagram.model.util.IDiagramChangeListener#getDiagram()
		 */
		public Diagram getDiagram() {
			return DiagramImpl.this;
		}

		@Override
		protected void handleNotification(Notification msg) {
			switch (msg.getFeatureID(Activity.class)) {
			case UmaPackage.ACTIVITY__BREAKDOWN_ELEMENTS:
				switch (msg.getEventType()) {
				case Notification.ADD:
					Node node = (Node) addNode(msg.getNewValue());
					if (node != null) {
					if (msg.getNotifier() == baseAct) {
						node.setReadOnly(true);
						}
					}
					break;
				case Notification.REMOVE:
					removeNode(msg.getOldValue());
					break;
				case Notification.ADD_MANY:
					Collection nodes = addNodes((Collection) msg
							.getNewValue());
					if (msg.getNotifier() == baseAct) {
						for (Iterator iter = nodes.iterator(); iter
								.hasNext();) {
							node = (NodeImpl) iter.next();
							node.setReadOnly(true);
						}
					}
					break;
				case Notification.REMOVE_MANY:
					removeNodes((Collection) msg.getOldValue());
					break;
				case Notification.MOVE:
					moveNode(msg.getNewValue(), msg.getPosition(), msg.getOldValue());
					break;
				}
				break;
			}
		}

	}

	protected IDiagramChangeListener diagramChangeListener;

	private Activity baseAct;

	private Object diagramAdapter = new AdapterImpl() {
		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.emf.common.notify.impl.AdapterImpl#notifyChanged(org.eclipse.emf.common.notify.Notification)
		 */
		public void notifyChanged(Notification msg) {
			if (!notificationEnabled)
				return;
			notificationEnabled = false;
			try {
				//Activity act = (Activity) getObject();
				switch (msg.getFeatureID(Diagram.class)) {
				case ModelPackage.DIAGRAM__NODES:
					// System.out.println(".notifyChanged(): index=" +
					// msg.getPosition());
					Collection collection;
					switch (msg.getEventType()) {
					case Notification.ADD:
						nodeAdded(msg.getPosition(), (Node) msg
								.getNewValue());
						return;
					case Notification.REMOVE:
						nodeRemoved((Node) msg.getOldValue());
						return;
					case Notification.ADD_MANY:
						collection = (Collection) msg.getNewValue();
						for (Iterator iter = collection.iterator(); iter
								.hasNext();) {
							Node node = (Node) iter.next();
							nodeAdded(msg.getPosition(), node);
						}

						return;
					case Notification.REMOVE_MANY:
						collection = (Collection) msg.getOldValue();
						for (Iterator iter = collection.iterator(); iter
								.hasNext();) {
							nodeRemoved((Node) iter.next());
						}
						return;
					}

				}
			} finally {
				notificationEnabled = true;
			}
		}
	};

	protected Node addNode(Object obj) {
		Node node = addNode(getNodes(), obj);
		if (node == null)
			return node;
		populateLinks(node, true);
		return node;
	}

	protected Node addNode(Collection nodes, Object obj) {
		if (TngUtil.isInstanceOf(getBreakdownElementTypes(), obj)) {
			Node node = toNode((MethodElement) obj);
			if (node != null) {
				nodes.add(node);
				return node;
			}
		}
		return null;
	}

	protected Collection addNodes(Collection collection) {
		List nodes = new ArrayList();
		for (Iterator iter = collection.iterator(); iter.hasNext();) {
			addNode(nodes, iter.next());
		}

		// use addAll() to avoid unnecessary notifications
		//
		getNodes().addAll(nodes);
		return nodes;
	}

	/**
	 * @param obj
	 */
	protected boolean removeNode(Object obj) {
		if (!TngUtil.isInstanceOf(getBreakdownElementTypes(), obj))
			return false;

		Node node = GraphicalDataHelper.findNode(this, obj);
		if (node == null)
			return false;

		for (Iterator iter = node.getOutgoingConnections().iterator(); iter
				.hasNext();) {
			Link link = (Link) iter.next();
			link.setTarget(null);
		}

		for (Iterator iter = node.getIncomingConnections().iterator(); iter
				.hasNext();) {
			Link link = (Link) iter.next();
			link.setSource(null);
		}
		node.getOutgoingConnections().clear();
		node.getIncomingConnections().clear();

		getNodes().remove(node);
		return true;
	}

	protected void removeNodes(Collection collection) {
		for (Iterator iter = collection.iterator(); iter.hasNext();) {
			removeNode(iter.next());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.diagram.model.Diagram#getUMADiagram()
	 */
	public org.eclipse.epf.uma.Diagram getUMADiagram() {
		return (org.eclipse.epf.uma.Diagram) getGraphNode();
	}

	protected Class getDiagramChangeListenerType() {
		return IDiagramChangeListener.class;
	}

	protected List getBreakdownElementTypes() {
		return Collections.singletonList(BreakdownElement.class);
	}

	protected int getType() {
		return -1;
	}

	protected void nodeAdded(int index, Node node) {
		addToUmaModel(index, node);
		node.addConsumer(this);
	}

	protected void nodeRemoved(Node node) {
		removeFromUmaModel(node);
		node.removeConsumer(this);
	}

	public boolean isNew() {
		return this.newDiagram;
	}

	public void setNew(boolean n) {
		this.newDiagram = n;
	}

	/**
	 * NOTE: this method is re-entrant, make sure to handle the data correctly to avoid memory leak and data corruption
	 * 
	 * @param newObject
	 *            must be an Activity
	 */
	public void setObject(Object newObject) {
		if (newObject instanceof BreakdownElementWrapperItemProvider
				&& ((BreakdownElementWrapperItemProvider) newObject)
						.isReadOnly()) {
			wrapper = (BreakdownElementWrapperItemProvider) newObject;
			setReadOnly(true);
		}
		newObject = TngUtil.unwrap(newObject);

		object = newObject;

		// add diagram change listener to the linked object. Remove the old
		// listener if there is any.
		// But first keep the old listener to help find the UMA diagram of the
		// activity quickly.
		//
		Activity e = (Activity) newObject;		
		if(ProcessUtil.isExtendingOrLocallyContributing(e)) {
			baseAct = (Activity) e.getVariabilityBasedOnElement();
		}
		baseAct = null;
		
		if(newObject == null) {
			return;
		}

		// set the UMA diagram for this diagram
		//
		if(isGraphicalDataRequired()) {
			graphNode = GraphicalDataManager.getInstance().getUMADiagram(e,
					getType(), false);
			// set the flag to indicate this is a new diagram or not
			if (graphNode == null) {
				graphNode = GraphicalDataManager.getInstance().getUMADiagram(e,
						getType(), true);
				setNew(true);
			}
		}

		if(!e.eAdapters().contains(diagramChangeListener)) {
			e.eAdapters().add(diagramChangeListener);
		}
		if (baseAct != null && !baseAct.eAdapters().contains(diagramChangeListener)) {
			baseAct.eAdapters().add(diagramChangeListener);
		}

		populateDiagram();

		if(!eAdapters().contains(diagramAdapter)) {
			this.eAdapters().add((Adapter) diagramAdapter);
		}

	}

	/**
	 * Populates the diagram with the data from the UMA model. Subclass should
	 * override this method.
	 */
	protected void populateDiagram() {
		populateNodes();
		populateLinks();

		// add this diagram to the consumer list of all nodes so they will not
		// be disposed
		// before this diagram.
		//
		for (Iterator iter = getNodes().iterator(); iter.hasNext();) {
			NodeImpl node = (NodeImpl) iter.next();
			node.addConsumer(this);
		}
	}

	/**
	 * Populates the incoming/outgoing links of the given node
	 * 
	 * @param node
	 */
	protected void populateLinks(Node node, boolean disableNotification) {
		int size = 0;
		boolean[] notifies = null;
		try {
			if (disableNotification) {
				size = getNodes().size();
				notifies = new boolean[size];
				// disable notification for all nodes in this diagram to avoid
				// unwanted concurrent modification of their connection list
				//
				for (int i = 0; i < size; i++) {
					Node child = ((Node) getNodes().get(i));
					notifies[i] = child.eDeliver();
					child.eSetDeliver(false);
				}
			}

			GraphNode graphNode = node.getGraphNode();
			if (graphNode != null) {
				GraphicalDataHelper.fillConnections(node, graphNode);
			}
		} finally {
			if (disableNotification) {
				// restore notification flag
				//
				for (int i = 0; i < size; i++) {
					((EObject) getNodes().get(i)).eSetDeliver(notifies[i]);
				}
			}
		}
	}

	protected void populateNodes() {		
		org.eclipse.epf.uma.Diagram diagram = getUMADiagram();
		if(diagram != null) {
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
		}
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

	protected void populateLinks() {
		// fill outgoing/incoming connection lists of all nodes
		//
		int size = getNodes().size();
		boolean[] notifies = new boolean[size];
		try {
			// disable notification for all nodes in this diagram to avoid
			// unwanted concurrent modification of their connection list
			//
			for (int i = 0; i < size; i++) {
				Node node = ((Node) getNodes().get(i));
				notifies[i] = node.eDeliver();
				node.eSetDeliver(false);
			}
			for (Iterator iter = getNodes().iterator(); iter.hasNext();) {
				Node node = (Node) iter.next();
				populateLinks(node, false);
			}
		} finally {
			// restore notification flag
			//
			for (int i = 0; i < size; i++) {
				((EObject) getNodes().get(i)).eSetDeliver(notifies[i]);
			}
		}

	}

	/**
	 * Removes the data for the removed node from the UMA model Subclass should
	 * override this method.
	 * 
	 * @param removedNode
	 */
	protected void removeFromUmaModel(Node removedNode) {
		Activity act = (Activity) getObject();
		if (removedNode.getObject() instanceof BreakdownElement) {
			act.getBreakdownElements().remove(removedNode.getObject());
		}
		
		if(getUMADiagram() != null) {
			getUMADiagram().getContained().remove(removedNode.getGraphNode());
		}
	}

	/**
	 * Adds the data for the newly added node to the UMA model Subclass should
	 * override this method.
	 * 
	 * @param position
	 * @param newValue
	 */
	protected void addToUmaModel(int position, Node addedNode) {

		if (addedNode.getGraphNode() == null) {
			// this node is readded after undo
			//
			((NodeImpl) addedNode).basicSetObject(addedNode.getObject());
		}
		
		if(getUMADiagram() != null) {
			getUMADiagram().getContained().add(addedNode.getGraphNode());
		}
	}

	/**
	 * Creates new node for this diagram for the given MethodElement.
	 * 
	 * @param e
	 * @return
	 */
	protected Node toNode(MethodElement e) {
		Node node = newNode();
		if (node == null)
			return null;
		node.setUMADiagram(getUMADiagram());
		node.setDiagram(this);
		node.setObject(e);
		return node;
	}

	protected Node newNode() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.diagram.model.Node#getMethodElementAdapterType()
	 */
	public Class getMethodElementAdapterType() {
		return getDiagramChangeListenerType();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.diagram.model.impl.NodeImpl#dispose()
	 */
	protected void dispose() {
		if (diagramChangeListener != null) {
			Activity e = (Activity) getObject();
			if (baseAct != null) {
				baseAct.eAdapters().remove(diagramChangeListener);
			}
			if(e != null) {
				e.eAdapters().remove(diagramChangeListener);
			}
		}

		super.dispose();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.diagram.model.Diagram#setDefaultName(org.eclipse.epf.diagram.model.NamedNode)
	 */
	public void setDefaultName(NamedNode newNode) {
		MethodElement obj = (MethodElement) newNode.getObject();
		int classID = obj.eClass().getClassifierID();
		Activity act = (Activity) getObject();
		ArrayList siblings = new ArrayList();
		for (Iterator iter = act.getBreakdownElements().iterator(); iter
				.hasNext();) {
			BreakdownElement e = (BreakdownElement) iter.next();
			if (e.eClass().getClassifierID() == classID) {
				siblings.add(e);
			}
		}
		String baseName = MessageFormat
				.format(
						DiagramResources.defaultBaseName, new Object[] { TngUtil.getTypeText(obj.eClass().getName()) }); 
		TngUtil.setDefaultName(siblings, obj, baseName);
		newNode.setName(obj.getName());
	}
	
	protected void extractChildren(ITreeItemContentProvider adapter, Object object, Collection children,
			boolean filterSuppressed) {
		// disable rollup before getting the children
		//
		boolean oldRolledUp = false;
		if(adapter instanceof BSActivityItemProvider) {
			BSActivityItemProvider itemProvider = (BSActivityItemProvider)adapter;
			oldRolledUp = itemProvider.isRolledUp();
			itemProvider.basicSetRolledUp(false);
		}
		else if(adapter instanceof IBSItemProvider){
			IBSItemProvider itemProvider = (IBSItemProvider)adapter;
			oldRolledUp = itemProvider.isRolledUp();
			itemProvider.setRolledUp(false);
		}
		
		try {
			// filter out the suppressed elements
			//
			if (adapter != null) {
				if(filterSuppressed){
					for (Iterator iter = adapter.getChildren(object).iterator(); iter.hasNext();) {
						Object child = iter.next();
						if(!getSuppression().isSuppressed(child)) {
							children.add(child);
						}
					}
				}else{
					// don't filter suppressed elements
					//
					children.addAll(adapter.getChildren(object));
				}
			}
		}
		finally {
			// restore the rolled-up flag
			//
			if(adapter instanceof IBSItemProvider) {
				((IBSItemProvider)adapter).setRolledUp(oldRolledUp);
			}
		}
	}

	public Suppression getSuppression() {
		return suppression;
	}

	public void setSuppression(Suppression suppression) {
		this.suppression = suppression;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.diagram.model.Diagram#setFilter(com.ibm.library.edit.IFilter)
	 */
	public void setFilter(IFilter filter) {
		this.filter = filter;
	}

	/**
	 * In Process WBS, if breakdownelement is moved
	 * up or down, diagram should be updated accordingly. 
	 * Sub-class should override this method if diagram needs update on move. 
	 * @param newValue
	 * @param newposition int
	 * @param oldPosition Object(Integer)
	 */
	public void moveNode(Object movedObject, int position, Object oldPosition) {
	}
		
	public boolean isGraphicalDataRequired() {
		return graphicalDataRequired;
	}

	public void setGraphicalDataRequired(boolean graphicalDataRequired) {
		this.graphicalDataRequired = graphicalDataRequired;
	}
		
	public IFilter getFilter() {
		return filter;
	}
	
	public void setWrapper(BreakdownElementWrapperItemProvider wrapper) {
		this.wrapper = wrapper;
	}
	
	public Collection getChildren() {
		return Collections.EMPTY_LIST;
	}
	
	private Suppression suppression;

	protected BreakdownElementWrapperItemProvider wrapper;

	protected IFilter filter;
	
	private boolean graphicalDataRequired = true;

} // DiagramImpl
