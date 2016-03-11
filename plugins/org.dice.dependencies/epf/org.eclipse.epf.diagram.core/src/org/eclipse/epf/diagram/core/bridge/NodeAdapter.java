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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.transaction.impl.InternalTransactionalEditingDomain;
import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.diagram.model.util.TxUtil;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.process.BreakdownElementWrapperItemProvider;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.WorkBreakdownElement;
import org.eclipse.epf.uma.WorkOrder;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.gmf.runtime.notation.FontStyle;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * @author Phong Nguyen Le
 *
 * @since 1.2
 */
public abstract class NodeAdapter extends AdapterImpl {
	
	protected class MethodElementAdapter extends AdapterImpl //implements INodeChangeListener 
	{
		/**
		 * 
		 * @param msg
		 * @return New nodes whose views need refresh after they have been created
		 */
		protected Collection handleNotification(Notification msg) {
			switch (msg.getFeatureID(DescribableElement.class)) {
			case UmaPackage.DESCRIBABLE_ELEMENT__PRESENTATION_NAME:
				setName(msg.getNewStringValue());
				break;
			case UmaPackage.DESCRIBABLE_ELEMENT__SHAPEICON:
			case UmaPackage.DESCRIBABLE_ELEMENT__NODEICON:
			   // Create a new notify when we've detected that the icons may have changed,
			   // by just forcing a notify on the name, we cause the icons to get 
			   // refreshed as well.
				ActivityNode e = getNode();
				e.eNotify(new ENotificationImpl((InternalEObject) e, Notification.SET,
						UMLPackage.NAMED_ELEMENT__NAME, e.getName(), e.getName(), true));
				e.eNotify(msg);
				break;
			}
			return Collections.EMPTY_LIST;
		}
		
		public void notifyChanged(final Notification msg) {
			if(domain == null || domain.getChangeRecorder() == null) {
				return;
			}

			if (!notificationEnabled)
				return;
			notificationEnabled = false;
			try {
				final Collection<ActivityNode> newNodesToRefresh = new ArrayList<ActivityNode>();
				TxUtil.runInTransaction(domain, new Runnable() {

					public void run() {
						newNodesToRefresh.addAll(handleNotification(msg));
					}
					
				});
				if(!newNodesToRefresh.isEmpty()) {
					TxUtil.runInTransaction(domain, new Runnable() {

						public void run() {
							for (ActivityNode node : newNodesToRefresh) {
								BridgeHelper.getNodeAdapter(node).updateView();
							}
						}
						
					});
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			} finally {
				notificationEnabled = true;
			}
		}

		public NodeAdapter getNodeAdapter() {
			return NodeAdapter.this;
		}

	}
	
	protected MethodElement element;
	protected boolean notificationEnabled = true;
	private Collection consumers = new ArrayList();
	protected MethodElementAdapter methodElementAdapter;
	private boolean targetReadOnly;
	protected BreakdownElementWrapperItemProvider wrapper;
	protected InternalTransactionalEditingDomain domain;
	private View view;
	protected IActionManager actionManager;
	
	protected NodeAdapter() {
		
	}
	
	public IActionManager getActionManager() {
		if(actionManager != null) {
			return actionManager;
		}
		NodeAdapter nodeAdapter = BridgeHelper.getNodeAdapter(getNode().getActivity());
		return nodeAdapter.getActionManager();
	}
	
//	public NodeAdapter(BreakdownElementWrapperItemProvider wrapper) {
//		this((MethodElement) TngUtil.unwrap(wrapper));
//		this.wrapper = wrapper;
//		targetReadOnly = wrapper.isReadOnly();
//	}

	protected NodeAdapter(MethodElement e) {
		element = e;
		methodElementAdapter = createMethodElementAdapter();
		e.eAdapters().add(methodElementAdapter);		
	}			
	
	public View getView() {
		if(view != null) {
			return view;
		}
		View diagramView = getDiagramView();
		if(diagramView != null) {
			return BridgeHelper.getView(diagramView, getNode());
		}
		return null;
	}
	
	private View getDiagramView() {
		Activity diagram = getDiagram();
		if(diagram != null) {
			NodeAdapter adapter = BridgeHelper.getNodeAdapter(diagram);
			if(adapter != null) {
				return adapter.getView();
			}
		}
		return null;
	}

	public void setView(View view) {
		this.view = view;
	}

	public void setEditingDomain(InternalTransactionalEditingDomain domain) {
		this.domain = domain;
	}
			
	public boolean isTargetReadOnly() {
		if(targetReadOnly)  {
			return targetReadOnly;
		}
		Activity activity = getDiagram();
		if(activity != target) {
			NodeAdapter diagramAdapter = BridgeHelper.getNodeAdapter(activity);
			if(diagramAdapter != null && diagramAdapter.isTargetReadOnly()) {
				return true;
			}
		}
		return false;
	}
	
	protected void basicSetTargetReadOnly(boolean b) {
		targetReadOnly = b;
	}

	protected void setTargetReadOnly(boolean targetReadOnly) {
		boolean updateView = this.targetReadOnly != targetReadOnly;
		this.targetReadOnly = targetReadOnly;
		if(updateView) {
			updateView();
		}
	}		

	protected void updateView() {
		View node = getView();
		if(node != null) {
//			node.setMutable(!isTargetReadOnly());
			FontStyle style = (FontStyle) node.getStyle(NotationPackage.Literals.FONT_STYLE);
//			if(style != null) {
//				Color color = isTargetReadOnly() ? DiagramConstants.READ_ONLY_FONT_COLOR : DiagramConstants.DEFAULT_FONT_COLOR;
//				style.setFontColor(FigureUtilities.RGBToInteger(color.getRGB()).intValue());
//			}
			if(style == null) {
				node.createStyle(NotationPackage.Literals.FONT_STYLE);
			}
		}
	}

	protected MethodElementAdapter createMethodElementAdapter() {
		return new MethodElementAdapter();
	}
	
	protected ActivityNode getNode() {
		if(target instanceof ActivityNode) {
			return (ActivityNode) target;
		}
		return null;
	}
	
	protected Activity getDiagram() {
		if(target instanceof Activity) {
			return (Activity) target;
		}
		ActivityNode node = getNode();
		return node != null ? node.getActivity() : null;
	}
	
	protected ActivityEdge addIncomingConnection(MethodElement source) {
		ActivityNode srcNode = BridgeHelper.findNode(getDiagram(), source, true);
		if (srcNode == null)
			return null;

		// disable notification of srcNode before associate it with the link
		// so it will not create duplicate UMA data
		//
		NodeAdapter srcNodeAdapter = BridgeHelper.getNodeAdapter(srcNode);
		boolean notify = srcNodeAdapter != null ? srcNodeAdapter.notificationEnabled : false;
		try {
			if(srcNodeAdapter != null) srcNodeAdapter.notificationEnabled = false;
			ActivityEdge edge = UMLFactory.eINSTANCE.createControlFlow();
			edge.setSource(srcNode);
			edge.setTarget(getNode());
			getDiagram().getEdges().add(edge);
			return edge;
		} finally {
			if(srcNodeAdapter != null) srcNodeAdapter.notificationEnabled = notify;
		}
	}

	protected ActivityEdge addOutgoingConnection(MethodElement target) {
		ActivityNode targetNode = BridgeHelper.findNode(getDiagram(), target);
		if (targetNode == null)
			return null;
			
		// disable notification of targetNode before associate it with the link
		// so it will not create duplicate UMA data
		//
		NodeAdapter nodeAdapter = BridgeHelper.getNodeAdapter(targetNode);
		boolean notify = nodeAdapter != null ? nodeAdapter.notificationEnabled : false;
		try {
			if(nodeAdapter != null) nodeAdapter.notificationEnabled = false;
			ActivityEdge edge = getDiagram().createEdge("", UMLPackage.Literals.CONTROL_FLOW); //$NON-NLS-1$
			edge.setSource(getNode());
			edge.setTarget(targetNode);
			return edge;
		} finally {
			if(nodeAdapter != null) nodeAdapter.notificationEnabled = notify;
		}
	}
	
	/**
	 * Adds the given consumer to the consumer list of this node.
	 * 
	 * @param consumer
	 */
	public void addConsumer(Object consumer) {
		if (!consumers.contains(consumer)) {
			consumers.add(consumer);
		}
	}
	
	/**
	 * Removes the given consumer from the consumer list of this node. Disposes
	 * the node if it does not have any more consumer after this call. Disposing
	 * a node will take care of removing this node's listener from the UMA
	 * object and all the adapters that had been added to the adapter list of
	 * this node.
	 * 
	 * @param consumer
	 */
	public void removeConsumer(Object consumer) {
		consumers.remove(consumer);
		if (consumers.isEmpty()) {
			// no more consumer of this node, it should be disposed
			dispose();
		}
	}
	
	public void dispose() {
		// set domain to null before removing methodElementAdapter from the
		// method element so methodElement can skip handling REMOVE_ADAPTER event
		// that sometimes requires new transaction to be created and causes dead-lock
		//
		domain = null;
		EObject obj = element;
		if (obj != null) {
			if (methodElementAdapter != null) {
				obj.eAdapters().remove(methodElementAdapter);
			}
		}
		if(target != null) {
			target.eAdapters().remove(this);
		}
		
		element = null;
	}
	
	public void setName(String newStringValue) {
		ActivityNode e = getNode();
		if(e != null) {
			e.setName(newStringValue);
		}
	}
	
	protected void handleNotification(Notification msg) {
		switch(msg.getFeatureID(ActivityNode.class)) {
		case UMLPackage.ACTIVITY_NODE__NAME:
			if (msg.getEventType() == Notification.SET
					&& getElement() instanceof BreakdownElement) {
				String newName = msg.getNewStringValue();
				BreakdownElement e = ((BreakdownElement) getElement());
				e.setPresentationName(newName);
				if (StrUtil.isBlank(e.getName())) {
					e.setName(newName);
				}
			}
			return;
		case UMLPackage.ACTIVITY_NODE__INCOMING: // incoming connections
			ActivityEdge link;
			switch (msg.getEventType()) {
			case Notification.ADD:
				link = (ActivityEdge) msg.getNewValue();
				addToUMA(link);
				return;
			case Notification.ADD_MANY:
				for (Iterator iter = ((Collection) msg.getNewValue())
						.iterator(); iter.hasNext();) {
					addToUMA((ActivityEdge) iter.next());
				}
				break;
			case Notification.REMOVE:
				link = (ActivityEdge) msg.getOldValue();
				if (link.getSource() != null) {
					NodeAdapter nodeAdapter = BridgeHelper.getNodeAdapter(link.getSource());
					if(nodeAdapter != null) {
						nodeAdapter.removeFromUMA(link, link
								.getSource(), (ActivityNode) msg.getNotifier());
					}
				}
				break;
			case Notification.REMOVE_MANY:
				for (Iterator iter = ((Collection) msg.getOldValue())
						.iterator(); iter.hasNext();) {
					link = (ActivityEdge) iter.next();
					if (link.getSource() != null) {
						NodeAdapter nodeAdapter = BridgeHelper.getNodeAdapter(link.getSource());
						if(nodeAdapter != null) {
							nodeAdapter.removeFromUMA(link,
									link.getSource(), (ActivityNode) msg.getNotifier());
						}
					}
				}
				break;
			}
			return;
		case UMLPackage.ACTIVITY_NODE__OUTGOING:
			switch (msg.getEventType()) {
			case Notification.ADD:
				link = (ActivityEdge) msg.getNewValue();
				if (link.getTarget() != null) {
					NodeAdapter nodeAdapter = BridgeHelper.getNodeAdapter(link.getTarget());
					if(nodeAdapter != null) {
						nodeAdapter.addToUMA(link);
					}
				}
				break;
			case Notification.ADD_MANY:
				for (Iterator iter = ((Collection) msg.getNewValue())
						.iterator(); iter.hasNext();) {
					link = (ActivityEdge) iter.next();
					NodeAdapter nodeAdapter = BridgeHelper.getNodeAdapter(link.getTarget());
					if(nodeAdapter != null) {
						nodeAdapter.addToUMA(link);
					}
				}
				break;
			case Notification.REMOVE:
				link = (ActivityEdge) msg.getOldValue();
				if (link.getTarget() != null) {
					removeFromUMA(link, (ActivityNode) msg.getNotifier(), link
							.getTarget());
				}
				break;
			case Notification.REMOVE_MANY:
				for (Iterator iter = ((Collection) msg.getOldValue())
						.iterator(); iter.hasNext();) {
					link = (ActivityEdge) iter.next();
					if (link.getTarget() != null) {
						removeFromUMA(link, (ActivityNode) msg.getNotifier(), link
								.getTarget());
					}
				}
				break;
			}
		}
	}

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
			handleNotification(msg);
		} finally {
			notificationEnabled = true;
		}
	}
	
	/**
	 * Adds the data for the newly added node to the UMA model Subclass should
	 * override this method.
	 * 
	 * @param position
	 * @param newValue
	 */
	protected void addToUmaModel(int position, ActivityNode node) {		
//TODO: review
//		if (addedNode.getGraphNode() == null) {
//			// this node is readded after undo
//			//
//			((NodeImpl) addedNode).basicSetObject(addedNode.getObject());
//		}
	}

	protected void nodeAdded(int index, ActivityNode node) {
		NodeAdapter nodeAdapter = BridgeHelper.getNodeAdapter(node);
		if(nodeAdapter == null) {
			nodeAdapter = addNodeAdapterTo(node);
		}
		addToUmaModel(index, node);		
		if(nodeAdapter != null) {
			nodeAdapter.addConsumer(this);
		}
	}
	
	protected NodeAdapter addNodeAdapterTo(ActivityNode node) {
		return null;
	}
	
	/**
	 * Removes the data for the removed node from the UMA model Subclass should
	 * override this method.
	 * 
	 * @param node
	 */
	protected void removeFromUmaModel(ActivityNode node) {
	}
	
	protected void nodeRemoved(ActivityNode node) {
		removeFromUmaModel(node);
		NodeAdapter nodeAdapter = BridgeHelper.getNodeAdapter(node);
		if(nodeAdapter != null) {
			nodeAdapter.removeConsumer(this);
		}
	}

	protected boolean addToUMA(ActivityEdge link) {
		if (link.getTarget() == null || link.getSource() == null)
			return false;		
		return true;
	}
	
	protected void removeFromUMA(ActivityEdge link, ActivityNode oldSource, ActivityNode oldTarget) {
	}
	
	/**
	 * addDefaultWorkOrder is moved from
	 * GraphicalDataHelper.addDefaultWorkOrder(NamedNodeImpl,
	 * WorkbreakdownElement) Method will set notificationEnabled flag to false
	 * of all adapters of type MethodElementAdapter of Successor object before
	 * creating a WorkOrder object. After creation of WorkOrder (Predecessor
	 * link) restores the notificationEnabled flag of Successor's adapters of
	 * type MethodElementAdapter.
	 * 
	 * eg: If an Activity "actA" is extended in another CP. Base "actA" AD
	 * diagram is opened, and Extended "actA" AD Diagram is opened. On creating
	 * predecessor link between any two child activities, creates a extra link
	 * in Extended "actA" AD diagram, because creating workorder will notify the
	 * extended "actA" diagram to draw a link. To avoid duplicate links in the
	 * extended activity diagram, we have to set notificationEnabled flag to
	 * false, before creating a WorkOrder object.
	 * 
	 */
	private WorkOrder addDefaultWorkOrder(ActivityNode node,
			WorkBreakdownElement predBreakdownElement) {
		NodeAdapter nodeAdapter = BridgeHelper.getNodeAdapter(node);
		boolean notify = nodeAdapter.notificationEnabled;
		Map map = new HashMap();
		MethodElement e = nodeAdapter.getElement();
		List list = e.eAdapters();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object obj = iterator.next();
			if (obj instanceof MethodElementAdapter) {
				NodeAdapter otherNodeAdapter = (NodeAdapter) ((MethodElementAdapter) obj)
						.getNodeAdapter();
				boolean predNotification = nodeAdapter.notificationEnabled;
				otherNodeAdapter.notificationEnabled = false;
				map.put(otherNodeAdapter, new Boolean(predNotification));
			}
		}
		try {
			nodeAdapter.notificationEnabled = false;
			WorkOrder wo = UmaUtil.createDefaultWorkOrder((WorkBreakdownElement) e, predBreakdownElement, false);
			getActionManager().doAction(IActionManager.ADD, e, 
					UmaPackage.Literals.WORK_BREAKDOWN_ELEMENT__LINK_TO_PREDECESSOR, wo, -1);
			return wo;
		} finally {
			nodeAdapter.setNotificationEnabled(notify);
			Set set = map.keySet();
			for (Iterator iter = set.iterator(); iter.hasNext();) {
				Object object = iter.next();
				Object obj = map.get(object);
				if (obj instanceof Boolean) {
					boolean prednot = ((Boolean) obj).booleanValue();
					((NodeAdapter) object).notificationEnabled = prednot;
				}
			}
		}
	}
	
	private WorkOrder addCustomWorkOrder(org.eclipse.epf.uma.Activity diagramActivity, WorkBreakdownElement succ, WorkBreakdownElement pred, ActivityEdge link) {
		ProcessPackage pkg = (ProcessPackage) diagramActivity.eContainer();
		WorkOrder order = ProcessUtil.findWorkOrder(diagramActivity, succ, pred);
		if(order == null) {
			order = ProcessUtil.createDefaultWorkOrderForInheritedChild(diagramActivity, succ, pred);
			getActionManager().doAction(IActionManager.ADD, pkg, 
					UmaPackage.Literals.PROCESS_PACKAGE__PROCESS_ELEMENTS, order, -1);
		}
		if(link != null) {
			BridgeHelper.associate(link, order);
		}
		return order;
	}
	
	protected WorkOrder addWorkOrder(ActivityNode successorNode, WorkBreakdownElement successor, 
			ActivityNode predecessorNode, WorkBreakdownElement predecessor, org.eclipse.epf.uma.Activity diagramActivity, ActivityEdge link) {
		WorkOrder order = null;
		if(diagramActivity == successor.getSuperActivities()) {
			order = UmaUtil.findWorkOrder(successor, predecessor);
			if (order == null) {
				NodeAdapter sourceNodeAdapter = predecessorNode != null ? BridgeHelper.getNodeAdapter(predecessorNode) : null;	
				// node adapter can be NULL if the node is not currently visible in the diagram
				//
				boolean sourceNotify = sourceNodeAdapter != null ? sourceNodeAdapter.notificationEnabled : false;
				try {
					// disable notification and create a work order
					if(sourceNodeAdapter != null) sourceNodeAdapter.notificationEnabled = false;
					order = addDefaultWorkOrder(successorNode, predecessor);
				} finally {
					if(sourceNodeAdapter != null) sourceNodeAdapter.notificationEnabled = sourceNotify;
				}
			}
		} else {
			order = addCustomWorkOrder(diagramActivity, successor, predecessor, link);
		}
		return order;
	}
	
	public void setNotificationEnabled(boolean notify) {
		notificationEnabled = notify;
	}
	
	public boolean isNotificationEnabled() {
		return notificationEnabled;
	}
	
	public MethodElement getElement() {
		return element;
	}

	public void setTarget(Notifier newTarget) {
		if(newTarget == null) {
			dispose();
		}
		super.setTarget(newTarget);
	}
	
	public BreakdownElementWrapperItemProvider getWrapper() {
		return wrapper;
	}
}
