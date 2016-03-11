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
package org.eclipse.epf.diagram.core.bridge;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.epf.diagram.core.DiagramCorePlugin;
import org.eclipse.epf.diagram.model.util.TxUtil;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.command.ActionManager;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.process.BSActivityItemProvider;
import org.eclipse.epf.library.edit.process.BreakdownElementWrapperItemProvider;
import org.eclipse.epf.library.edit.process.IBSItemProvider;
import org.eclipse.epf.library.edit.util.DescriptorPropUtil;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.Suppression;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ActivityPartition;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * @author Phong Nguyen Le
 * 
 * @since 1.2
 */
public class DiagramAdapter extends NodeAdapter {

	private Activity baseAct;

	protected IFilter filter;

	private Suppression suppression;

	protected long umaLastModified;


	public DiagramAdapter(BreakdownElementWrapperItemProvider wrapper) {
		this((Activity) TngUtil.unwrap(wrapper));
		this.wrapper = wrapper;
		basicSetTargetReadOnly(wrapper.isReadOnly());
	}

	public DiagramAdapter(Activity e) {
		super(e);

		// listens to change in the base activity if there is any
		//
		if (ProcessUtil.isExtendingOrLocallyContributing(e)) {
			baseAct = (Activity) e.getVariabilityBasedOnElement();
			baseAct.eAdapters().add(methodElementAdapter);
		}
	}

	public void dispose() {
		// dispose all node adapters of the child nodes
		//
		for (Iterator<?> iter = getNodes().iterator(); iter.hasNext();) {
			ActivityNode node = (ActivityNode) iter.next();
			NodeAdapter nodeAdapter = BridgeHelper.getNodeAdapter(node);
			if (nodeAdapter != null) {
				nodeAdapter.dispose();
			}
		}

		super.dispose();
		
		if (baseAct != null) {
			baseAct.eAdapters().remove(methodElementAdapter);
		}

	}

	protected class ActivityAdapter extends MethodElementAdapter {
		protected Collection handleNotification(Notification msg) {
			Collection newNodesToRefresh = new ArrayList();
			switch (msg.getFeatureID(Activity.class)) {
			case UmaPackage.ACTIVITY__BREAKDOWN_ELEMENTS:
				switch (msg.getEventType()) {
				case Notification.ADD:
					if (msg.getNewValue() instanceof TaskDescriptor) {
						TaskDescriptor td = (TaskDescriptor) msg.getNewValue();
						if (DescriptorPropUtil.getDesciptorPropUtil()
								.getGreenParentDescriptor(td) != null) {
							break;
						}
					}
					ActivityNode node = (ActivityNode) addNode(msg
							.getNewValue());
					if (node != null) {
						if (msg.getNotifier() == baseAct) {
							BridgeHelper.getNodeAdapter(node)
									.basicSetTargetReadOnly(true);
							newNodesToRefresh.add(node);
						}
					}
					break;
				case Notification.REMOVE:
					removeNode(msg.getOldValue());
					break;
				case Notification.ADD_MANY:
					Collection nodes = addNodes((Collection) msg.getNewValue());
					if (msg.getNotifier() == baseAct) {
						for (Iterator iter = nodes.iterator(); iter.hasNext();) {
							node = (ActivityNode) iter.next();
							BridgeHelper.getNodeAdapter(node)
									.basicSetTargetReadOnly(true);
							newNodesToRefresh.add(node);
						}
					}
					break;
				case Notification.REMOVE_MANY:
					removeNodes((Collection) msg.getOldValue());
					break;
				case Notification.MOVE:
					moveNode(msg.getNewValue());
					break;
				case Notification.SET:
					replaceNode(msg.getOldValue(), msg.getNewValue());
					break;
				}
				break;
			default:
				return super.handleNotification(msg);
			}
			return newNodesToRefresh;
		}
	}
	
	protected void handleNotification(Notification msg) {
		switch(msg.getFeatureID(org.eclipse.uml2.uml.Activity.class)) {
		case UMLPackage.ACTIVITY__NODE:
			Collection collection;
			switch (msg.getEventType()) {
			case Notification.ADD:
				nodeAdded(msg.getPosition(), (ActivityNode) msg
						.getNewValue());
				return;
			case Notification.REMOVE:
				nodeRemoved((ActivityNode) msg.getOldValue());
				return;
			case Notification.ADD_MANY:
				collection = (Collection) msg.getNewValue();
				for (Iterator iter = collection.iterator(); iter
				.hasNext();) {
					ActivityNode node = (ActivityNode) iter.next();
					nodeAdded(msg.getPosition(), node);
				}

				return;
			case Notification.REMOVE_MANY:
				collection = (Collection) msg.getOldValue();
				for (Iterator iter = collection.iterator(); iter
				.hasNext();) {
					nodeRemoved((ActivityNode) iter.next());
				}
				return;
			}
		}
		
		super.handleNotification(msg);
	}

	protected void replaceNode(Object oldElement, Object newElement) {
		
	}

	protected MethodElementAdapter createMethodElementAdapter() {
		return new ActivityAdapter();
	}

	protected final void populateLinks() {
		populateLinks(getNodes());
	}
	
	protected void populateLinks(List<ActivityNode> selectedNodes) {
		// fill outgoing/incoming connection lists of all nodes
		//
		int size = selectedNodes.size();
		boolean[] notifies = new boolean[size];
		try {
			// disable notification for all nodes in this diagram to avoid
			// unwanted concurrent modification of their connection list
			//
			for (int i = 0; i < size; i++) {
				ActivityNode node = selectedNodes.get(i);
				notifies[i] = node.eDeliver();
				node.eSetDeliver(false);
			}
			for (ActivityNode node : selectedNodes) {
				populateLinks(node, false);
			}
		} finally {
			// restore notification flag
			//
			for (int i = 0; i < size; i++) {
				selectedNodes.get(i).eSetDeliver(notifies[i]);
			}
		}
	}

	protected List getNodes() {
		org.eclipse.uml2.uml.Activity diagram = getDiagram();
		return diagram != null ? getDiagram().getNodes() : Collections.emptyList();
	}
	
	protected org.eclipse.uml2.uml.Activity getDiagram() {
		return (org.eclipse.uml2.uml.Activity) getTarget();
	}

	protected boolean removeNode(Object obj) {
		if (!TngUtil.isInstanceOf(getBreakdownElementTypes(), obj))
			return false;

		ActivityNode node = BridgeHelper.findNode(getDiagram(), obj);
		if (node == null)
			return false;

		for (Iterator iter = node.getOutgoings().iterator(); iter.hasNext();) {
			ActivityEdge link = (ActivityEdge) iter.next();
			link.setTarget(null);
		}

		for (Iterator iter = node.getIncomings().iterator(); iter.hasNext();) {
			ActivityEdge link = (ActivityEdge) iter.next();
			link.setSource(null);
		}
		node.getOutgoings().clear();
		node.getIncomings().clear();

		getNodes().remove(node);
		return true;
	}

	protected void removeNodes(Collection collection) {
		for (Iterator iter = collection.iterator(); iter.hasNext();) {
			removeNode(iter.next());
		}
	}

	protected ActivityNode addNode(Object obj) {
		ActivityNode node = addNode(getNodes(), obj);
		if (node == null)
			return node;
		populateLinks(node, true);
		return node;
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
	 * In Process WBS, if breakdownelement is moved up or down, diagram should
	 * be updated accordingly. Sub-class should override this method if diagram
	 * needs update on move.
	 * 
	 * @param newValue
	 */
	public void moveNode(Object oldValue) {
	}

	/**
	 * Populates the incoming/outgoing links of the given node
	 * 
	 * @param node
	 */
	protected void populateLinks(ActivityNode node, boolean disableNotification) {
		// int size = 0;
		// boolean[] notifies = null;
		// try {
		// if (disableNotification) {
		// size = getNodes().size();
		// notifies = new boolean[size];
		// // disable notification for all nodes in this diagram to avoid
		// // unwanted concurrent modification of their connection list
		// //
		// for (int i = 0; i < size; i++) {
		// Node child = ((Node) getNodes().get(i));
		// notifies[i] = child.eDeliver();
		// child.eSetDeliver(false);
		// }
		// }
		//
		// GraphNode graphNode = node.getGraphNode();
		// if (graphNode != null) {
		// GraphicalDataHelper.fillConnections(node, graphNode);
		// }
		// } finally {
		// if (disableNotification) {
		// // restore notification flag
		// //
		// for (int i = 0; i < size; i++) {
		// ((EObject) getNodes().get(i)).eSetDeliver(notifies[i]);
		// }
		// }
		// }
	}

	/**
	 * Creates new node for this diagram for the given MethodElement.
	 * 
	 * @param e
	 * @return
	 */
	protected ActivityNode toNode(MethodElement e) {
		ActivityNode node = newNode(e);
		if (node == null)
			return null;
		initializeNode(node, e);
		return node;
	}
	
	protected void initializeNodeAdapter(NodeAdapter nodeAdapter) {
		nodeAdapter.setEditingDomain(domain);
		nodeAdapter.actionManager = actionManager;
	}
	
	private void initializeNode(ActivityNode node, MethodElement e) {
		String name = BridgeHelper.getNodeName(e);
		node.setName(name);		

		NodeAdapter nodeAdapter = BridgeHelper.getNodeAdapter(node);
		if (nodeAdapter != null && nodeAdapter.getElement() != e) {
			nodeAdapter.dispose();
			nodeAdapter = null;
		}
		if (nodeAdapter == null) {
			nodeAdapter = createNodeAdapter(e);
			initializeNodeAdapter(nodeAdapter);
			node.eAdapters().add(nodeAdapter);
		}
	}

	protected NodeAdapter createNodeAdapter(MethodElement e) {
		return null;
	}

	protected ActivityNode newNode(MethodElement e) {
		return null;
	}

	/**
	 * Creates a new node for the given method element <code>obj</code> and
	 * add it to the given collection of nodes
	 * 
	 * @param nodes
	 * @param obj
	 *            method element
	 * @return
	 */
	protected ActivityNode addNode(Collection nodes, Object obj) {
		if (TngUtil.isInstanceOf(getBreakdownElementTypes(), obj)) {
			ActivityNode node = toNode((MethodElement) obj);
			if (node != null) {
				nodes.add(node);
				return node;
			}
		}
		return null;
	}

	protected List getBreakdownElementTypes() {
		return Collections.singletonList(BreakdownElement.class);
	}

	public Activity getActivity() {
		return (Activity) element;
	}

	protected void removeFromUmaModel(ActivityNode removedNode) {
		Object e;
		if ((e = BridgeHelper.getMethodElement(removedNode)) instanceof BreakdownElement) {
			getActionManager().doAction(IActionManager.REMOVE, getActivity(), 
					UmaPackage.Literals.ACTIVITY__BREAKDOWN_ELEMENTS, e, -1);
		}

		super.removeFromUmaModel(removedNode);
	}

	protected void extractChildren(ITreeItemContentProvider adapter, Object object, Collection children) {
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
			for (Iterator iter = adapter.getChildren(object).iterator(); iter.hasNext();) {
				Object child = iter.next();
				if(!getSuppression().isSuppressed(child)) {
					children.add(child);
				}
			}
			
			// don't filter suppressed elements
			//
			//children.addAll(adapter.getChildren(object));
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
	
	protected void updateView(Collection<?> selectedNodes) throws InterruptedException, RollbackException {
		updateView(getView(), selectedNodes);
	}
	
	private static boolean isWorkBreakdownElementType(View node) {
		// hack by using the visual IDs defined in AD model
		//  StructuredActivityNodeEditPart 1007 Activity
		//  StructuredActivityNode2EditPart 1010 Phase
		//  StructuredActivityNode3EditPart 1011 Iteration
		//  ActivityParameterNodeEditPart 1009 TaskDescriptor
		//  ActivityParameterNode2EditPart 1012 Milestone
		//
		
		String type = node.getType();
		return type != null && ("1007".equals(type) //$NON-NLS-1$
				|| "1010".equals(type) //$NON-NLS-1$
				|| "1011".equals(type) //$NON-NLS-1$
				|| "1009".equals(type) //$NON-NLS-1$
				|| "1012".equals(type)); //$NON-NLS-1$
	}
	
	private static void updateView(View view, Collection<?> selectedNodes) {
		// show the selected nodes and hide all the other
		//	
		for (Iterator<?> iter = view.getChildren().iterator(); iter.hasNext();) {
			View node = (View) iter.next();
			if(selectedNodes.contains(node.getElement())) {
				NodeAdapter adapter = BridgeHelper.getNodeAdapter(node.getElement());
				if(adapter != null) {
					adapter.updateView();
				}
//				if(!node.isVisible()) {
					node.setVisible(true);
//				}
			}
			else {
				if(node.getElement() instanceof ActivityNode) {
					if(node.isVisible()) {
						node.setVisible(false);
					}
				}
				else if(node.getElement() instanceof ActivityPartition) {
					updateView(node, selectedNodes);
				}
				// this is a work around to not show any work breakdown element
				// node that does not have any model reference (View.element)
				// GMF returns the container view's element if the child node's element
				// is not set. Therefore, if the child node is shown, it will be displayed
				// as a node of parent activity. Deleting this node in editor will delete
				// the parent activity as result.
				//
				else if(isWorkBreakdownElementType(node) && (!node.isSetElement() || node.getElement() == view.getElement())) {
					if(node.isVisible()) {
						node.setVisible(false);
					}
				}
				else {
					if(!node.isVisible()) {
						node.setVisible(true);
					}
				}
			}
		}	
	}
	
	protected void updateEdges(Collection selectedNodes) throws InterruptedException, RollbackException {
		for (Iterator iter = getView().getChildren().iterator(); iter.hasNext();) {
			View node = (View) iter.next();
			if(selectedNodes.contains(node.getElement())) {
				NodeAdapter adapter = BridgeHelper.getNodeAdapter(node.getElement());
				adapter.updateView();
//				if(!node.isVisible()) {
					node.setVisible(true);
					setEdgeVisibility(node, true);
//				}
			}
			else {
				if(node.getElement() instanceof ActivityNode) {
					if(node.isVisible()) {
						setEdgeVisibility(node, false);
					}
				}
				else {
					if(!node.isVisible()) {
						node.setVisible(true);
						setEdgeVisibility(node, true);
					}
				}
			}
		}	
	}
	
	private void setEdgeVisibility(View view, boolean visibility){
		Diagram diagram = view.getDiagram();
		for (Iterator iterator = diagram.getEdges().iterator(); iterator
				.hasNext();) {
			Edge edge = (Edge) iterator.next();
			if(edge.getSource() == view || edge.getTarget() == view){
				if(visibility){
					view.setVisible(true);
				}
				edge.setVisible(visibility);
			}
		}
	}
	/**
	 * Populates the diagram with the data from the UMA model. Subclass should
	 * override this method.
	 */
	public void populateDiagram() {
		boolean notify = notificationEnabled;
		try {
			notificationEnabled = false;
			final List<ActivityNode> selectedNodes = new ArrayList<ActivityNode>();
			org.eclipse.uml2.uml.Activity diagram = getDiagram();			
			TxUtil.runInTransaction(diagram, new Runnable() {

				public void run() {
					selectedNodes.addAll(populateNodes());
					populateLinks(selectedNodes);
				}
				
			});
			
			// add this diagram to the consumer list of all nodes so they will not
			// be disposed
			// before this diagram.
			//
			for (ActivityNode node : selectedNodes) {
				NodeAdapter nodeAdapter = BridgeHelper.getNodeAdapter(node);
				if(nodeAdapter != null) {
					nodeAdapter.addConsumer(this);
				}
			}
			
			TxUtil.runInTransaction(diagram, new Runnable() {

				public void run() {
					try {
						updateEdges(selectedNodes);
						updateView(selectedNodes);
					} catch(Exception e) {
						DiagramCorePlugin.getDefault().getLogger().logError(e);
					}
				}
				
			});
		}
		catch(Exception e) {
			DiagramCorePlugin.getDefault().getLogger().logError(e);
		}
		finally {
			notificationEnabled = notify;
		}
	}
	
	protected Collection populateNodes() {
		return Collections.EMPTY_LIST;
	}

	public void setFilter(IFilter filter) {
		this.filter = filter;
	}
	
	public IActionManager getActionManager() {
		if(actionManager == null) {
			actionManager = new ActionManager() {
				public boolean doAction(int actionType, EObject object, EStructuralFeature feature, Object value, int index) {
					boolean ret = super.doAction(actionType, object, feature, value, index);
					if(ret) {
						umaLastModified = System.currentTimeMillis();
					}
					return ret;
				}
			};
		}
		return actionManager;
	}
	
	public long getUmaLastModified() {
		return umaLastModified;
	}

	public IFilter getFilter() {
		return filter;
	}
}
