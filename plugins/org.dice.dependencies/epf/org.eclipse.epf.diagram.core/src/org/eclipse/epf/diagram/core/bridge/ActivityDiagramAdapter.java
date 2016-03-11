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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.epf.diagram.model.util.IAdapterFactoryFilter;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.process.ActivityWrapperItemProvider;
import org.eclipse.epf.library.edit.process.BSActivityItemProvider;
import org.eclipse.epf.library.edit.process.BreakdownElementWrapperItemProvider;
import org.eclipse.epf.library.edit.process.IBSItemProvider;
import org.eclipse.epf.library.edit.util.ConfigurableComposedAdapterFactory;
import org.eclipse.epf.library.edit.util.DescriptorPropUtil;
import org.eclipse.epf.library.edit.util.PredecessorList;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Milestone;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.WorkBreakdownElement;
import org.eclipse.epf.uma.WorkOrder;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ActivityParameterNode;
import org.eclipse.uml2.uml.ControlNode;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.eclipse.uml2.uml.UMLFactory;

/**
 * @author Phong Nguyen Le
 * @author Shilpa Toraskar
 *
 * @since 1.2
 */
public class ActivityDiagramAdapter extends DiagramAdapter {
	
	public ActivityDiagramAdapter(BreakdownElementWrapperItemProvider wrapper) {
		super(wrapper);
	}

	/**
	 * @param e
	 */
	public ActivityDiagramAdapter(Activity e) {
		super(e);
	}

	protected ActivityNode addNode(Collection nodes, Object obj) {
		if (obj instanceof Activity) {
			Activity act = (Activity) obj;
			VariabilityElement base = act.getVariabilityBasedOnElement();
			if (base != null) {
				// find existing node for base and link it to the activity
				//
				org.eclipse.uml2.uml.Activity diagram = getDiagram();
				ActivityNode baseNode = BridgeHelper.findNode(diagram, base);
				if (baseNode == null) {
					baseNode = BridgeHelper.findNode(diagram, act);
				}
				if (baseNode != null) {
					// disassociate with the base
					//
					NodeAdapter nodeAdapter = BridgeHelper.getNodeAdapter(baseNode);
					if(nodeAdapter != null) {
						nodeAdapter.dispose();
					}
					
					// associate base node with the activity
					//
					nodeAdapter = createNodeAdapter(act);
					if(nodeAdapter != null) {
						initializeNodeAdapter(nodeAdapter);
						baseNode.eAdapters().add(nodeAdapter);
						nodeAdapter.updateView();
//						if(ProcessUtil.isExtendingOrLocallyContributing(act)) {
							BridgeHelper.associate(baseNode, act);
							if(BridgeHelper.isInherited(nodeAdapter.getView())){
								BridgeHelper.unmarkInHerited(nodeAdapter.getView());
							}
							nodeAdapter.setTargetReadOnly(false);
//						}
					}
					
					return null;
				}
			}
		}
		return super.addNode(nodes, obj);
	}
	
	protected NodeAdapter createNodeAdapter(MethodElement e) {
		if(e instanceof WorkBreakdownElement) {
			return new WorkBreakdownElementNodeAdapter(e);
		}
		return null;
	}
	
	protected void addToUmaModel(int position, ActivityNode addedNode) {
		MethodElement e = BridgeHelper.getMethodElement(addedNode);
		if(e == null) {
			return;
		}
		Activity act = (Activity) getElement();

		// translate ActivityNode index to Activity index
		//
		int i = toActivityIndex(position);

		NodeAdapter nodeAdapter = BridgeHelper.getNodeAdapter(addedNode);
		boolean notify = nodeAdapter.notificationEnabled;
		try {
			nodeAdapter.notificationEnabled = false;
			getActionManager().doAction(IActionManager.ADD, act, UmaPackage.Literals.ACTIVITY__BREAKDOWN_ELEMENTS, e, i);
			BridgeHelper.addEAnnotation(addedNode, e);
		}
		finally {
			nodeAdapter.notificationEnabled = notify;
		}

		super.addToUmaModel(position, addedNode);
	}
	
	private int toActivityIndex(int index) {
		if (index == -1)
			return index;
		int size = getNodes().size();
		int i = index + 1;
		if (i == size)
			return -1;
		ActivityNode node = (ActivityNode) getNodes().get(i);
		if (node == null)
			return -1;

		for (; !(BridgeHelper.getMethodElement(node) instanceof BreakdownElement) && i < size; i++) {
			node = (ActivityNode) getNodes().get(i);
		}
		if (i == size)
			return -1;
		Activity act = (Activity) getElement();
		return act.getBreakdownElements().indexOf(BridgeHelper.getMethodElement(node));
	}

	protected List getBreakdownElementTypes() {
		return Collections.singletonList(WorkBreakdownElement.class);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.diagram.core.bridge.DiagramAdapter#newNode(org.eclipse.epf.uma.MethodElement)
	 */
	protected ActivityNode newNode(MethodElement e) {
		ActivityNode node = null;
		if(e instanceof Activity ) {
			node = UMLFactory.eINSTANCE.createStructuredActivityNode();
		}
		else if(e instanceof TaskDescriptor || e instanceof Milestone) {
			node = UMLFactory.eINSTANCE.createActivityParameterNode();
		}
		else if(e instanceof WorkBreakdownElement) {
			node = UMLFactory.eINSTANCE.createStructuredActivityNode();
		}
		if(node != null) {
			BridgeHelper.associate(node, e);
		}
		return node;
	}
	
	
	private boolean isValid(ActivityEdge link) {
		if (link.getSource() == null || link.getTarget() == null
				|| !getNodes().contains(link.getSource())
				|| !getNodes().contains(link.getTarget())) {
			return false;
		}
		return true;
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
	
	/**
	 * Checks if Node src can reach Node target as the first ActivityNode in the
	 * diagram
	 * 
	 * @param predNode
	 * @param node
	 * @return
	 */
	private static boolean canReachAsFirstActivityNode(ActivityNode predNode, ActivityNode node) {
		for (Iterator iter = predNode.getOutgoings().iterator(); iter
				.hasNext();) {
			ActivityEdge link = (ActivityEdge) iter.next();
			if (link.getTarget() == node) {
				return true;
			}
			if (link.getTarget() instanceof ControlNode
					&& canReachAsFirstActivityNode(link.getTarget(), node)) {
				return true;
			}
		}
		return false;
	}
	
	protected List<WorkBreakdownElement> getLocalPredecessors(ActivityNode node) {
		Activity diagramActivity = (Activity) BridgeHelper.getMethodElement(getDiagram());
		AdapterFactory adapterFactory = getAdapterFactory();
		ITreeItemContentProvider adapter = (ITreeItemContentProvider) adapterFactory.adapt(diagramActivity, ITreeItemContentProvider.class);
		Collection<?> children = adapter.getChildren(diagramActivity);
		return getLocalPredecessors(node, children);
	}
	
	private List<WorkBreakdownElement> getLocalPredecessors(ActivityNode node, Collection<?> activityChildren) {
		MethodElement e = BridgeHelper.getMethodElement(node);
		if(e instanceof WorkBreakdownElement) {
			// find item provider
			//
			Object object = null;
			for (Object child : activityChildren) {
				if(e == TngUtil.unwrap(child)) {
					object = child;
					break;
				}
			}
			if(object != null) {
				Object ip = getAdapterFactory().adapt(object, ITreeItemContentProvider.class);
				if(ip instanceof IBSItemProvider) {
					Object parent = ((ITreeItemContentProvider) ip).getParent(object);
					ArrayList<WorkBreakdownElement> preds = new ArrayList<WorkBreakdownElement>();
					PredecessorList predList = ((IBSItemProvider) ip).getPredecessors();
					for (Object predIp : predList) {
						Object pred = TngUtil.unwrap(predIp);
						
						if (ProcessUtil.isSynFree() && pred instanceof TaskDescriptor) {
							DescriptorPropUtil propUtil = DescriptorPropUtil.getDesciptorPropUtil();
							List<? extends Descriptor> childList = propUtil.getCustomizingChildren((TaskDescriptor) pred);
							if (childList != null && ! childList.isEmpty()) {
								boolean processed = false;
								for (Descriptor des : childList) {
									if (des.getSuperActivities() == parent) {
										preds.add((WorkBreakdownElement) des);
										processed = true;
										break;
									}
								}
								if (processed) {
									continue;
								}
							}
						}
						// make sure that predecessors are sibling
						//
						if(((ITreeItemContentProvider) predIp).getParent(pred) == parent) {
							if(pred instanceof WorkBreakdownElement) {
								preds.add((WorkBreakdownElement) pred);
							} else if (pred instanceof Adapter) {
								pred = ((Adapter) pred).getTarget();
								if(pred instanceof WorkBreakdownElement) {
									preds.add((WorkBreakdownElement) pred);
								}
							}
						} else if (ProcessUtil.isSynFree() && pred instanceof Adapter) {
							pred = ((Adapter) pred).getTarget();
							if (pred instanceof TaskDescriptor) {
								TaskDescriptor td = (TaskDescriptor) pred;
								if (td.getSuperActivities() == parent) {
									preds.add(td);
								}
							}
						}
					}
					return preds;
				}
			}
		}
		return Collections.emptyList();
	}

	AdapterFactory getAdapterFactory(){
		AdapterFactory adapterFactory = null;
		if (filter == null) {
			adapterFactory = TngAdapterFactory.INSTANCE.getWBS_ComposedAdapterFactory();
		} else if (filter instanceof IAdapterFactoryFilter) {
			adapterFactory = (ConfigurableComposedAdapterFactory) ((IAdapterFactoryFilter) filter)
					.getWBSAdapterFactory();
		}
		return adapterFactory;
	}
	
	@Override
	protected void populateLinks(List<ActivityNode> selectedNodes) {
		if(selectedNodes.isEmpty()) {
			return;
		}
		
		super.populateLinks(selectedNodes);

		// remove the invalid links
		//
		for (ActivityNode node : selectedNodes) {
			WorkBreakdownElement wbe = null;
			MethodElement me;
			if ((me = BridgeHelper.getMethodElement(node)) instanceof WorkBreakdownElement) {
				wbe = (WorkBreakdownElement) me;
			}
			ArrayList<ActivityEdge> linksToRemove = new ArrayList<ActivityEdge>();
			linkListWalk: for (Iterator iterator = node
					.getIncomings().iterator(); iterator.hasNext();) {
				ActivityEdge link = (ActivityEdge) iterator.next();
				if (!isValid(link)) {
					linksToRemove.add(link);
				}

				if (wbe != null) {
					// TODO: need revisit to check the valid connection from a
					// ForkNode or JoinNode
					//
					
					if (link.getSource() != null && link.getSource() instanceof ControlNode)
						continue linkListWalk;
					
					boolean workOrderFound = false;
					WorkOrder workOrder = (WorkOrder) BridgeHelper.getMethodElement(link);
					if(workOrder == null) {
						Object pred = BridgeHelper.getMethodElement(link.getSource());
						find_WorkOrder: for (Iterator iterator1 = wbe
								.getLinkToPredecessor().iterator(); iterator1
								.hasNext();) {
							WorkOrder wo = (WorkOrder) iterator1.next();
							if (isValidWorkOrder(wo, pred)) {
								workOrderFound = true;
								break find_WorkOrder;
							}
						}
					} else {
						workOrderFound = true;
					}
					if (!workOrderFound) {
						// invalid link, remove it
						//
						linksToRemove.add(link);
					}
				}
			}

			for (ActivityEdge link : linksToRemove) {
				BridgeHelper.removeLink(link);
			}
			linksToRemove.clear();

			for (Iterator iterator = node.getOutgoings().iterator(); iterator
					.hasNext();) {
				ActivityEdge link = (ActivityEdge) iterator.next();
				if (!isValid(link)) {
					linksToRemove.add(link);
				}
			}

			for (Iterator iterator = linksToRemove.iterator(); iterator
					.hasNext();) {
				ActivityEdge link = (ActivityEdge) iterator.next();
				BridgeHelper.removeLink(link);
			}
			linksToRemove.clear();
			linksToRemove = null;
		}

		// Check the links of each ActivityNode
		// set the WorkOrder to the link if needed
		// add new link for those WorkOrders that still don't have the
		// corresponding link
		//
		Activity diagramActivity = (Activity) BridgeHelper.getMethodElement(getDiagram());
		AdapterFactory adapterFactory = getAdapterFactory();
		ITreeItemContentProvider adapter = (ITreeItemContentProvider) adapterFactory.adapt(diagramActivity, ITreeItemContentProvider.class);
		Collection<?> children = adapter.getChildren(diagramActivity);
		
		for (ActivityNode node : selectedNodes) {
			List<WorkBreakdownElement> preds = getLocalPredecessors(node, children);

			// Iterate work orders and create links.
			for (WorkBreakdownElement pred : preds) {
				ActivityNode predNode = BridgeHelper.findNode(getDiagram(),
						pred, true);
				if (predNode != null) {
					// check if there is a link for this work order
					// already
					//
					boolean linkFound = false;
					find_link: for (Iterator<?> iterator1 = node
							.getIncomings().iterator(); iterator1
							.hasNext();) {
						ActivityEdge link = (ActivityEdge) iterator1.next();
						if (link.getSource() == predNode) {
							// link already exists
							// check if work order is set to this link
							//
							linkFound = true;
//							MethodElement me = BridgeHelper.getMethodElement(link);
//							if (link != null) {
//								BridgeHelper.setSemanticModel(
//										link, workOrder);
//							}
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
							NodeAdapter nodeAdapter = BridgeHelper.getNodeAdapter(node);
							NodeAdapter predNodeAdapter = BridgeHelper.getNodeAdapter(predNode);
							if(nodeAdapter != null && predNodeAdapter != null) {
								boolean oldNotify = nodeAdapter.notificationEnabled;
								boolean predNodeNotify = predNodeAdapter.notificationEnabled;
								try {
									nodeAdapter.notificationEnabled = false;
									predNodeAdapter.notificationEnabled = false;
									ActivityEdge edge = nodeAdapter.addIncomingConnection(pred);
									if(BridgeHelper.isInherited(node)) {
										// target node is inherited, must find the custom work order and associate it with the edge
										//
										WorkBreakdownElement inheritedChild = (WorkBreakdownElement) BridgeHelper.getMethodElement(node);
										if(inheritedChild != null) {
											WorkOrder wo = ProcessUtil.findWorkOrder(diagramActivity, inheritedChild, pred);
											if(wo != null) {
												BridgeHelper.associate(edge, wo);
											}
										}
									}
								} finally {
									nodeAdapter.notificationEnabled = oldNotify;
									predNodeAdapter.notificationEnabled = predNodeNotify;
								}
							}
						}
					}
				}
			}
		}
		cleanDuplicateLinks(selectedNodes);
	}

	private void cleanDuplicateLinks(List<ActivityNode> selectedNodes) {
		List<ActivityEdge> duplicateLinks = new ArrayList<ActivityEdge>();
		for (ActivityNode wbNode : selectedNodes) {
			if(wbNode.getIncomings() != null){
				findDuplicateLinks(duplicateLinks, wbNode.getIncomings());
			}
			if(wbNode.getOutgoings() !=null){
				findDuplicateLinks(duplicateLinks, wbNode.getOutgoings());
			}
		}
		for (ActivityEdge link : duplicateLinks) {
			BridgeHelper.removeLink(link);
		}
		duplicateLinks.clear();
		duplicateLinks = null;
	}

	private void findDuplicateLinks(List duplicateLinks, List links) {
		for (int i = 0; i < links.size(); i++) {
			ActivityEdge link = (ActivityEdge) links.get(i);
			if(!duplicateLinks.contains(link)){
				for (int j = i + 1; j < links.size(); j++) {
					ActivityEdge link1 = (ActivityEdge) links.get(j);
					if (link1.getSource() == link.getSource()
							&& link1.getTarget() == link.getTarget()) {
						duplicateLinks.add(link1);
					}
				}
			}
		}
	}

	protected Collection populateNodes() {
		HashSet selectedNodes = new HashSet();
		Collection children = getChildren();

		// associate control nodes
		//
		selectedNodes.addAll(associateControlNodes(children));
		
		// add WorkBreakdownElementNodes
		//
		selectedNodes.addAll(addElementNodes(children));
		
		return selectedNodes;
	}
	
	@Override
	protected void updateView(Collection<?> selectedNodes)
			throws InterruptedException, RollbackException {
		super.updateView(selectedNodes);
		hideUnusedSyncBars();
	}
	
	protected void hideUnusedSyncBars() {
		View diagram = getView();
		for (Object child : diagram.getVisibleChildren()) {
			View childView = (View) child;
			// hide a sync bar that is not connected to any visible target node
			// or source node, which is associated with a work breakdown element
			//
			if(childView.getElement() instanceof ControlNode && 
					BridgeHelper.isSynchBar((ActivityNode) childView.getElement()) &&
					!isConnectedToVisibleWBENode(childView)) {
				childView.setVisible(false);
			}
		}
	}
	
	private static boolean isConnectedToVisibleWBENode(View view) {
		// check source nodes
		//
		for(Object object : view.getTargetEdges()) {
			View connectedView = ((Edge) object).getSource();
//			if(connectedView != null && connectedView.isVisible() && 
//					connectedView.getElement() instanceof ActivityNode &&
//					BridgeHelper.getMethodElement(connectedView) instanceof WorkBreakdownElement) {
//				return true;
//			}
			if(connectedView != null && connectedView.isVisible() && 
					connectedView.getElement() instanceof ActivityNode ) {
				return true;
			}
		}
		
		// check target nodes
		//
		for(Object object : view.getSourceEdges()) {
			View connectedView = ((Edge) object).getTarget();
//			if(connectedView != null && connectedView.isVisible() && 
//					connectedView.getElement() instanceof ActivityNode &&
//					BridgeHelper.getMethodElement(connectedView) instanceof WorkBreakdownElement) {
//				return true;
//			}
			if(connectedView != null && connectedView.isVisible() && 
					connectedView.getElement() instanceof ActivityNode) {
				return true;
			}
		}
		
		return false;
	}
	
	private List<? extends ControlNode> associateControlNodes(Collection<?> methodElements) {
		List<ControlNode> ctrlNodes = new ArrayList<ControlNode>();
		for (Iterator<?> iter = getDiagram().getNodes().iterator(); iter.hasNext();) {
			ActivityNode node = (ActivityNode) iter.next();
			if(node instanceof ControlNode) {
				if(addNodeAdapterTo(node) != null) {
					ctrlNodes.add((ControlNode) node);
				}
			}
		}
		return ctrlNodes;
	}

	/**
	 * Adds element nodes for the specified method elements if they don't exist yet.
	 * 
	 * @param methodElements
	 */
	private List addElementNodes(Collection methodElements) {	
		ArrayList nodes = new ArrayList();
		ArrayList newNodes = new ArrayList();
		for (Iterator iter = methodElements.iterator(); iter.hasNext();) {
			Object e = iter.next();
			Object child = TngUtil.unwrap(e);
			if (child instanceof WorkBreakdownElement) {
				ActivityNode node = BridgeHelper.findNode(getDiagram(), child);
				if(node == null) {
					node = toNode((MethodElement) child);
					newNodes.add(node);
				}
				else {
					// synchronize name with linked element
					//
					String name = BridgeHelper.getNodeName((MethodElement) child);
					if(!name.equals(node.getName())) {
						node.setName(name);
					}
					
					addNodeAdapterTo(node);
				}
				nodes.add(node);
				if (e instanceof BreakdownElementWrapperItemProvider && !isTargetReadOnly()) {
					BreakdownElementWrapperItemProvider wrapper = ((BreakdownElementWrapperItemProvider) e);
					if (wrapper.isReadOnly()) {
						NodeAdapter nodeAdapter = BridgeHelper.getNodeAdapter(node);
						nodeAdapter.wrapper = wrapper;
						// readOnly attribute is still needed
						// after introduction of wrapper. 
						// For example, we can have a node adapter for a read-only base activity
						// without supplying wrapper for this base activity.
						//
						nodeAdapter.basicSetTargetReadOnly(true);
					}
				}
			}
		}
		getNodes().addAll(newNodes);		
		return nodes;
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
			adapter = (ITreeItemContentProvider) getAdapterFactory().adapt(
					getElement(), ITreeItemContentProvider.class);
		}
		return adapter;
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
			extractChildren(adapter, getElement(), children);
			
			// reset variabilityinfo status back
			if(obj instanceof BSActivityItemProvider){
				((BSActivityItemProvider)obj).setEnableVariabilityInfo(enableVariabilityInfo);
			}
			
			return children;
		} else {
			return Collections.EMPTY_LIST;
		}
	}

	protected boolean removeNode(Object obj) {
		if (obj instanceof Activity) {
			Activity act = (Activity) obj;
			VariabilityElement base = act.getVariabilityBasedOnElement();
			if (base != null) {
				org.eclipse.uml2.uml.Activity diagram = getDiagram();
				
				// find existing node for the old activity and relink it to its
				// base
				//
				ActivityNode node = BridgeHelper.findNode(diagram, act);
				if(node == null) {
					node = BridgeHelper.findNode(diagram, base);
				}
				if (node != null) {
					// disassociate with the old activity
					//
					NodeAdapter nodeAdapter = BridgeHelper.getNodeAdapter(node);
					if(nodeAdapter != null) {
						nodeAdapter.dispose();
					}
					
					// associate node with the base activity
					//
					nodeAdapter = createNodeAdapter(base);
					if(nodeAdapter != null) {
						initializeNodeAdapter(nodeAdapter);
						nodeAdapter.basicSetTargetReadOnly(true);
						node.eAdapters().add(nodeAdapter);
						nodeAdapter.updateView();
						if(!BridgeHelper.isInherited(nodeAdapter.getView())){
							BridgeHelper.markInherited(nodeAdapter.getView());
						}
					}

					return false;
				}
			}
		}
		return super.removeNode(obj);
	}

	protected NodeAdapter addNodeAdapterTo(ActivityNode node) {
		NodeAdapter adapter = BridgeHelper.getNodeAdapter(node);
		if(adapter != null) {
			return adapter;
		}
		if(node instanceof ControlNode) {
			adapter = new ControlNodeAdapter();
		}
		else {
			MethodElement e = BridgeHelper.getMethodElement(node);
			if(e == null) {
				if(node instanceof StructuredActivityNode) {
					String type = BridgeHelper.getType((StructuredActivityNode)node);
					if (BridgeHelper.UMA_PHASE.equals(type)) {
						e = UmaFactory.eINSTANCE.createPhase();
					}
					else if (BridgeHelper.UMA_ITERATION.equals(type)) {
						e = UmaFactory.eINSTANCE.createIteration();
					}
					else {
						e = UmaFactory.eINSTANCE.createActivity();
					}
				}
				else if(node instanceof ActivityParameterNode) {
					String type = BridgeHelper.getType((ActivityParameterNode)node);
					if(BridgeHelper.UMA_MILESTONE.equals(type)) {
						e = UmaFactory.eINSTANCE.createMilestone();
					}
					else {
						e = UmaFactory.eINSTANCE.createTaskDescriptor();
					}
				}
			}
			if(e != null) {
				adapter = new WorkBreakdownElementNodeAdapter(e);
			}
		}
		if(adapter != null) {
			initializeNodeAdapter(adapter);
			node.eAdapters().add(adapter);
			return adapter;
		}
		return null;
	}
	
	private ActivityNode reassociateBaseNode(Activity act) {
		VariabilityElement base = act.getVariabilityBasedOnElement();
		if (base != null) {
			// find existing node for base and link it to the activity
			//
			org.eclipse.uml2.uml.Activity diagram = getDiagram();
			ActivityNode baseNode = BridgeHelper.findNode(diagram, base);
			if (baseNode == null) {
				baseNode = BridgeHelper.findNode(diagram, act);
			}
			if (baseNode != null) {
				// disassociate with the base
				//
				NodeAdapter nodeAdapter = BridgeHelper.getNodeAdapter(baseNode);
				if(nodeAdapter != null) {
					nodeAdapter.dispose();
				}
				
				// associate base node with the activity
				//
				nodeAdapter = createNodeAdapter(act);
				if(nodeAdapter != null) {
					initializeNodeAdapter(nodeAdapter);
					baseNode.eAdapters().add(nodeAdapter);
					nodeAdapter.updateView();
//					if(ProcessUtil.isExtendingOrLocallyContributing(act)) {
						BridgeHelper.associate(baseNode, act);
						if(BridgeHelper.isInherited(nodeAdapter.getView())){
							BridgeHelper.unmarkInHerited(nodeAdapter.getView());
						}
						nodeAdapter.setTargetReadOnly(false);
//					}
				}
				
				return baseNode;
			}
		}
		
		return null;
	}

	@Override
	protected void replaceNode(Object oldElement, Object newElement) {
		if(newElement instanceof MethodElement) {
			// find existing node for old element and link it to the new element
			//
			org.eclipse.uml2.uml.Activity diagram = getDiagram();
			ActivityNode oldNode = BridgeHelper.findNode(diagram, oldElement);
			if (oldNode != null) {
				// disassociate with the old element
				//
				NodeAdapter nodeAdapter = BridgeHelper.getNodeAdapter(oldNode);
				if(nodeAdapter != null) {
					nodeAdapter.dispose();
				}

				// associate base node with the new element
				//
				MethodElement e = (MethodElement) newElement;
				nodeAdapter = createNodeAdapter(e);
				if(nodeAdapter != null) {
					initializeNodeAdapter(nodeAdapter);
					oldNode.eAdapters().add(nodeAdapter);
					nodeAdapter.updateView();
					BridgeHelper.associate(oldNode, e);
				}
			}
		}
	}
}
