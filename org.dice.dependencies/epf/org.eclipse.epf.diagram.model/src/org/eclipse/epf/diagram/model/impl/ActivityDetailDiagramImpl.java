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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.edit.provider.AdapterFactoryTreeIterator;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.epf.diagram.model.ActivityDetailDiagram;
import org.eclipse.epf.diagram.model.Link;
import org.eclipse.epf.diagram.model.ModelFactory;
import org.eclipse.epf.diagram.model.ModelPackage;
import org.eclipse.epf.diagram.model.Node;
import org.eclipse.epf.diagram.model.NodeContainer;
import org.eclipse.epf.diagram.model.RoleTaskComposite;
import org.eclipse.epf.diagram.model.TaskNode;
import org.eclipse.epf.diagram.model.WorkProductComposite;
import org.eclipse.epf.diagram.model.WorkProductDescriptorNode;
import org.eclipse.epf.diagram.model.util.DiagramModelPreference;
import org.eclipse.epf.diagram.model.util.GraphicalDataHelper;
import org.eclipse.epf.diagram.model.util.IActivityDetailDiagramChangeListener;
import org.eclipse.epf.diagram.model.util.IAdapterFactoryFilter;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.process.BSActivityItemProvider;
import org.eclipse.epf.library.edit.process.BreakdownElementWrapperItemProvider;
import org.eclipse.epf.library.edit.process.IBSItemProvider;
import org.eclipse.epf.library.edit.util.ConfigurableComposedAdapterFactory;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.TaskDescriptorPropUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Artifact;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.Constraint;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.WorkProductDescriptor;
import org.eclipse.epf.uma.util.AssociationHelper;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Activity Detail Diagram</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.diagram.model.impl.ActivityDetailDiagramImpl#isAutoLayout <em>Auto Layout</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ActivityDetailDiagramImpl extends DiagramImpl implements
		ActivityDetailDiagram {
	/**
	 * The default value of the '{@link #isAutoLayout() <em>Auto Layout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAutoLayout()
	 * @generated
	 * @ordered
	 */
	protected static final boolean AUTO_LAYOUT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isAutoLayout() <em>Auto Layout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAutoLayout()
	 * @generated
	 * @ordered
	 */
	protected boolean autoLayout = AUTO_LAYOUT_EDEFAULT;

	private class ActivityDetailDiagramChangeListener extends ActivityAdapter
			implements IActivityDetailDiagramChangeListener {
		// /* (non-Javadoc)
		// * @see
		// org.eclipse.epf.diagram.model.impl.DiagramImpl.ActivityAdapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
		// */
		// public void notifyChanged(Notification msg) {
		// if(!notificationEnabled) return;
		// notificationEnabled = false;
		// try {
		// Object obj;
		// switch(msg.getFeatureID(Activity.class)) {
		// case UmaPackage.ACTIVITY__BREAKDOWN_ELEMENTS:
		// repopulateDiagram();
		// break;
		// }
		// }
		// finally {
		// notificationEnabled = true;
		// }
		//
		// }

	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 */
	protected ActivityDetailDiagramImpl() {
		super();

		diagramChangeListener = new ActivityDetailDiagramChangeListener();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelPackage.ACTIVITY_DETAIL_DIAGRAM__AUTO_LAYOUT:
				return isAutoLayout() ? Boolean.TRUE : Boolean.FALSE;
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ModelPackage.ACTIVITY_DETAIL_DIAGRAM__AUTO_LAYOUT:
				setAutoLayout(((Boolean)newValue).booleanValue());
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ModelPackage.ACTIVITY_DETAIL_DIAGRAM__AUTO_LAYOUT:
				setAutoLayout(AUTO_LAYOUT_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ModelPackage.ACTIVITY_DETAIL_DIAGRAM__AUTO_LAYOUT:
				return autoLayout != AUTO_LAYOUT_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (autoLayout: "); //$NON-NLS-1$
		result.append(autoLayout);
		result.append(')');
		return result.toString();
	}

	/**
	 * 
	 */
	public void repopulateDiagram() {
		// clear diagram
		//

		int size = getNodes().size();

		// disable notification for all nodes in this diagram to avoid unwanted
		// concurrent modification of their connection list
		//
		for (int i = 0; i < size; i++) {
			NodeImpl node = ((NodeImpl) getNodes().get(i));
			node.notificationEnabled = false;
		}
		for (Iterator iteration = getNodes().iterator(); iteration.hasNext();) {
			Node node = (Node) iteration.next();

			// clear all links
			//
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
		}

		getNodes().clear();

		populateDiagram();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.ACTIVITY_DETAIL_DIAGRAM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public boolean isAutoLayout() {
		if(isGraphicalDataRequired()) {
			return GraphicalDataHelper.PROP_AUTO_LAYOUT_VALUE_TRUE.equals(
					GraphicalDataHelper.getAutoLayoutFlag(this));
		}
		return autoLayout;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAutoLayout(boolean newAutoLayout) {
		boolean oldAutoLayout = autoLayout;
		autoLayout = newAutoLayout;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.ACTIVITY_DETAIL_DIAGRAM__AUTO_LAYOUT, oldAutoLayout, autoLayout));
	}
	
	public void doSetAutoLayout(boolean newAutoLayout) {
		if(isGraphicalDataRequired()) {
			GraphicalDataHelper.createProperty(this.graphNode, 
					GraphicalDataHelper.PROP_AUTO_LAYOUT, 
					String.valueOf(newAutoLayout));
		}
		else {
			setAutoLayout(newAutoLayout);
		}
	}
	
	@Override
	public Collection getChildren() {
		return getAllBreakdownElements(true);
	}
	
	private static boolean isValid(IFilter filter) {
		if(filter instanceof IAdapterFactoryFilter) {
			IAdapterFactoryFilter adapterFactoryFilter = (IAdapterFactoryFilter) filter;
			return adapterFactoryFilter.getWBSAdapterFactory() != null && adapterFactoryFilter.getTBSAdapterFactory() != null &&
			adapterFactoryFilter.getWPBSAdapterFactory() != null;
		}
		return filter != null;
	}

	/**
	 * Gets all breakdown elements or their wrappers for the activity of this
	 * diagram
	 * 
	 * @return
	 */
	Collection getAllBreakdownElements(boolean filterSuppressed) {
		ArrayList breakdownElements = new ArrayList();
		AdapterFactory[] adapterFactories = null;
		if (filter instanceof IAdapterFactoryFilter) {
			IAdapterFactoryFilter adapterFactoryFilter = (IAdapterFactoryFilter) filter;
			// make sure the filter is valid
			if(isValid(adapterFactoryFilter)) {
				adapterFactories = new AdapterFactory[] {
						adapterFactoryFilter.getWBSAdapterFactory(),
						adapterFactoryFilter.getTBSAdapterFactory(),
						adapterFactoryFilter.getWPBSAdapterFactory() };
			}
		}
		if(adapterFactories == null) {
			adapterFactories = DEFAULT_ADAPTER_FACTORIES;
		}
		if (wrapper != null) {
			List<?> wrappers = ProcessUtil.getWrappers(wrapper, adapterFactories);
			for (Iterator<?> iter = wrappers.iterator(); iter.hasNext();) {
				BreakdownElementWrapperItemProvider w = (BreakdownElementWrapperItemProvider) iter
						.next();
				extractChildren(w, w, breakdownElements, filterSuppressed);
			}
		} else {
			for (int i = 0; i < adapterFactories.length; i++) {
				AdapterFactory adapterFactory = adapterFactories[i];
				if (getObject() != null) {
					if (adapterFactory != null) {
						ITreeItemContentProvider adapter = (ITreeItemContentProvider) adapterFactory
								.adapt(getObject(),
										ITreeItemContentProvider.class);
						extractChildren(adapter, getObject(),
								breakdownElements, filterSuppressed);
					}
				}
			}
		}
		
		// add descriptors of subartifacts
		//
		AdapterFactory wpbsAdapterFactory = adapterFactories[2];
		for (Iterator<?> iterator = new ArrayList(breakdownElements).iterator(); iterator
				.hasNext();) {
			Object child = iterator.next();
			Object e = TngUtil.unwrap(child);
			if(e instanceof WorkProductDescriptor) {
				WorkProduct wp = ((WorkProductDescriptor)e).getWorkProduct();
				if(wp instanceof Artifact
						// FIXME: need to used realized list of subartifacts after similar fix is made in
						// org.eclipse.epf.library.edit.process.WorkProductDescriptorItemProvider.java
						//
						&& !((Artifact)wp).getContainedArtifacts().isEmpty() 
				) {
					collectSubartifactDescriptors(child, filterSuppressed, breakdownElements, wpbsAdapterFactory);
				}
			}
		}
		
		return breakdownElements;
	}

	private void collectSubartifactDescriptors(Object child,
			boolean filterSuppressed, ArrayList<Object> breakdownElements,
			AdapterFactory wpbsAdapterFactory) {
		// disable rollup before getting the children
		//
		boolean oldRolledUp = false;
		IBSItemProvider adapter = null;
		if(child instanceof BSActivityItemProvider) {
			BSActivityItemProvider itemProvider = (BSActivityItemProvider) child;
			oldRolledUp = itemProvider.isRolledUp();
			itemProvider.basicSetRolledUp(false);
			adapter = itemProvider;
		}
		else if(adapter instanceof IBSItemProvider){
			IBSItemProvider itemProvider = (IBSItemProvider) child;
			oldRolledUp = itemProvider.isRolledUp();
			itemProvider.setRolledUp(false);
			adapter = itemProvider;
		}
		
		try {

			AdapterFactoryTreeIterator<Object> iter = new AdapterFactoryTreeIterator<Object>(
					wpbsAdapterFactory, child, false);
			while (iter.hasNext()) {
				Object o = iter.next();
				if(filterSuppressed) {
					if(!getSuppression().isSuppressed(o)) {
						breakdownElements.add(o);
					}
				}
				else {
					breakdownElements.add(o);
				}
			}
		} finally {
			// restore rolled-up flag
			//
			if (adapter != null) {
				adapter.setRolledUp(oldRolledUp);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.diagram.model.impl.DiagramImpl#populateNodes()
	 */
	protected void populateNodes() {
		boolean oldNotify = notificationEnabled;
		try {
			notificationEnabled = false;
			Activity act = (Activity) getObject();
			if (act == null)
				return;

			if (isGraphicalDataRequired()) {
				// to populate the typednode.
				super.populateNodes();
			}

			if (!isValid(filter)) {
				filter = ((ConfigurableComposedAdapterFactory) DEFAULT_ADAPTER_FACTORIES[0])
						.getFilter();
			}

			// Node newNode = null;
			ArrayList<Node> nodes = new ArrayList<Node>();
			Collection<?> breakdownElements = getAllBreakdownElements(true);
			Collection<RoleDescriptor> roleDescriptors = new HashSet<RoleDescriptor>();
			for (Iterator<?> iterator = breakdownElements.iterator(); iterator
					.hasNext();) {
				Object object = iterator.next();
				Object element = TngUtil.unwrap(object);
				if (element instanceof TaskDescriptor) {
					TaskDescriptor descriptor = (TaskDescriptor) element;
					List<RoleDescriptor> performers = descriptor
							.getPerformedPrimarilyBy();
					for (RoleDescriptor roleDescriptor : performers)	{
						if (roleDescriptor != null
								&& filter.accept(roleDescriptor)
								// TODO: need to check if the role descriptor is
								// inherited and locally suppressed
								// if locally suppressed, check the wrapper of the
								// role descriptor
								//
								&& !roleDescriptor.getSuppressed().booleanValue()
								&& !roleDescriptors.contains(roleDescriptor)) {
							roleDescriptors.add(roleDescriptor);
							// newNode = createRoleTaskComposite(roleDescriptor);
							// if(newNode != null) {
							// if(ProcessUtil.isInherited(object)) {
							// // task descriptor is inherited, its primary
							// performer is not local
							// // set role node of the RoleTaskComposite to
							// read-only
							// //
							// Node roleNode = (Node)
							// ((NodeContainer)newNode).getNodes().get(0);
							// roleNode.setReadOnly(true);
							// }
							// nodes.add(newNode);
							// }
							createRoleTaskCompositeRows(roleDescriptor, object,
									nodes);
						}
//						if (roleDescriptors.contains(roleDescriptor)) {
//							createTaskInputOutputNodes(descriptor, nodes);
//						}
					}					
				}
			}
			
			for (Node node : new ArrayList<Node>(nodes)) {
				if(node instanceof RoleTaskComposite) {
					for(Node childNode : ((RoleTaskComposite) node).getNodes()) {
						if(childNode instanceof TaskNode) {
							createTaskInputOutputNodes((TaskNode) childNode, nodes);
						}
					}
				}
			}

			selectNodes(nodes);
		} finally {
			notificationEnabled = oldNotify;
		}
	}
	
	private static int getNumberOfRows(int totalTasks, int tasksPerRow) {
		int n = totalTasks / tasksPerRow;
		return (tasksPerRow * n) == totalTasks ? n : n + 1;
	}

    private void createRoleTaskCompositeRows(RoleDescriptor roleDescriptor, 
    		Object object, List nodes) {
    	
    	int tasksPerRow = GraphicalDataHelper.getTasksPerRow();
    	List<?> primaryTaskDescriptors = //AssociationHelper.getPrimaryTaskDescriptors(roleDescriptor);
    		getRealizedPrimaryTaskDescriptors(roleDescriptor);
    	int totalTasks = primaryTaskDescriptors.size();
    	
    	if (tasksPerRow > 0 && totalTasks > 0 && totalTasks > tasksPerRow
				&& isAutoLayout()) {
			for (int i = 0; i < getNumberOfRows(totalTasks, tasksPerRow); i++) {
				Node newNode = createRoleTaskComposite(roleDescriptor, i);
				if (newNode != null) {
					if (ProcessUtil.isInherited(object)) {
						// task descriptor is inherited, its primary performer
						// is not local
						// set role node of the RoleTaskComposite to read-only
						//
						List<Node> nods = ((NodeContainer) newNode).getNodes();
						if (!nods.isEmpty()) {
							Node roleNode = (Node) nods.get(0);
							roleNode.setReadOnly(true);
						}
					}
					nodes.add(newNode);
				}
			}
		} else {
			Node newNode = createRoleTaskComposite(roleDescriptor);
			if (newNode != null) {
				if (ProcessUtil.isInherited(object)) {
					// task descriptor is inherited, its primary performer is
					// not local
					// set role node of the RoleTaskComposite to read-only
					//
					List<Node> nods = ((NodeContainer) newNode).getNodes();
					if (!nods.isEmpty()) {
						Node roleNode = (Node) nods.get(0);
						roleNode.setReadOnly(true);
					}
				}
				nodes.add(newNode);
			}
		}
    	
	}

	/**
	 * @param taskDescriptor
	 * @return
	 */
	Node createNode(TaskDescriptor taskDescriptor) {
		Node node = ModelFactory.eINSTANCE.createTaskNode();
		node.setUMAContainer(getGraphNode());
		node.setDiagram(this);
		node.setObject(taskDescriptor);
		return node;
	}

	/**
	 * @param descriptor
	 * @return
	 */
	Node createNode(WorkProductDescriptor descriptor) {
		Node node = ModelFactory.eINSTANCE.createWorkProductDescriptorNode();
		node.setUMAContainer(getGraphNode());
		node.setDiagram(this);
		node.setObject(descriptor);
		return node;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.diagram.model.impl.DiagramImpl#populateLinks()
	 */
	protected void populateLinks() {
		populateLinks(getNodes(), true);
	}

	/**
	 * @param newNodes
	 * @param disableNotification
	 */
	protected void populateLinks(Collection newNodes, boolean disableNotification) {
		// fill outgoing/incoming connection lists of all nodes
		//
		int size = getNodes().size();
		boolean[] notifies = new boolean[size];
		try {
			if (disableNotification)
				// disable notification for all nodes in this diagram to avoid
				// unwanted concurrent modification of their connection list
				//
				for (int i = 0; i < size; i++) {
					Node node = ((Node) getNodes().get(i));
					notifies[i] = node.eDeliver();
					node.eSetDeliver(false);
				}

			for (Iterator iter = newNodes.iterator(); iter.hasNext();) {
				Node child = (Node) iter.next();
				if (child instanceof WorkProductComposite) {
					// WorkProductComposite workproductComposite =
					// (WorkProductComposite) child.getObject();
					WorkProductCompositeImpl workproductComposite = (WorkProductCompositeImpl) child;
					if (workproductComposite.getType() == WorkProductComposite.INPUTS) {
						Object object = workproductComposite.getObject();
						if (object instanceof TaskDescriptor) {
//							Node node = GraphicalDataHelper.findNode(this,
//									object, TaskNode.class);
							TaskNode node = workproductComposite.getTaskNode();
							if(node != null && GraphicalDataHelper.findLink(child, object) == null) {
								Link link = ModelFactory.eINSTANCE.createLink();
								link.setSource(child);
								link.setTarget(node);
								if(isGraphicalDataRequired()) {
									GraphicalDataHelper.addGraphicalData(link);
								}
							}
						}
					}
					if (workproductComposite.getType() == WorkProductComposite.OUTPUTS) {
						Object object = workproductComposite.getObject();
						if (object instanceof TaskDescriptor) {
//							Node node = GraphicalDataHelper.findNode(this,
//									object, TaskNode.class);
							TaskNode node = workproductComposite.getTaskNode();
							if(node != null && GraphicalDataHelper.findLink(node, object) == null) {
								Link link = ModelFactory.eINSTANCE.createLink();
								link.setSource(node);
								link.setTarget(child);
								if(isGraphicalDataRequired()) {
									GraphicalDataHelper.addGraphicalData(link);
								}
							}
						}
					}
				}
			}
 		} finally {
			if (disableNotification)
				// restore notification flag
				//
				for (int i = 0; i < size; i++) {
					((EObject) getNodes().get(i)).eSetDeliver(notifies[i]);
				}
		}

	}
	
	private RoleTaskComposite doCreateRoleTaskComposite(RoleDescriptor roleDescriptor, int index) {
		RoleTaskComposite roleTaskComposite = null;
		for (Iterator iter = getNodes().iterator(); iter.hasNext();) {
			Node element = (Node) iter.next();
			if (element instanceof RoleTaskComposite) {
				RoleTaskComposite rtc = (RoleTaskComposite) element;
				if(rtc.getLinkedElement() == roleDescriptor && rtc.getRowIndex() == index) {
					roleTaskComposite = rtc;
					break;
				}
			}
		}

		if (roleTaskComposite == null) {
			roleTaskComposite = ModelFactory.eINSTANCE
					.createRoleTaskComposite();
			roleTaskComposite.setUMAContainer(getGraphNode());
			roleTaskComposite.setDiagram(this);
		}
		return roleTaskComposite;
	}

	public RoleTaskComposite createRoleTaskComposite(
			RoleDescriptor roleDescriptor) {
		RoleTaskComposite roleTaskComposite = doCreateRoleTaskComposite(roleDescriptor,0);
		roleTaskComposite.setObject(roleDescriptor);
		return roleTaskComposite;
	}
	
	public RoleTaskComposite createRoleTaskComposite(
			RoleDescriptor roleDescriptor, int rowIndex) {
		RoleTaskComposite roleTaskComposite = doCreateRoleTaskComposite(roleDescriptor, rowIndex);
		((RoleTaskCompositeImpl)roleTaskComposite).setRowIndex(rowIndex);
		roleTaskComposite.setObject(roleDescriptor);
		return roleTaskComposite;
	}

	public WorkProductComposite createWorkProductComposite(
			TaskDescriptor taskDescriptor, int type) {
		WorkProductComposite workproductComposite = null;
		for (Node element : getNodes()) {
			if (element instanceof WorkProductComposite) {
				WorkProductComposite wpc = (WorkProductComposite) element;
				if(wpc.getLinkedElement() == taskDescriptor && wpc.getType() == type) {					
					workproductComposite = wpc;
					break;
				}
			}
		}
		if(workproductComposite == null) {
			workproductComposite = ModelFactory.eINSTANCE
			.createWorkProductComposite();
			workproductComposite.setType(type);
		}
		workproductComposite.setUMAContainer(getGraphNode());
		workproductComposite.setDiagram(this);
		workproductComposite.setObject(taskDescriptor);
		setState(type, workproductComposite);
		return workproductComposite;
	}

	public WorkProductComposite createWorkProductComposite(
			TaskNode taskNode, int type) {
		TaskDescriptor taskDescriptor = (TaskDescriptor) taskNode.getLinkedElement();
		// find existing WorkProductComposite for the given TaskNode
		//
		List<Link> links = type == WorkProductComposite.OUTPUTS ? taskNode.getOutgoingConnections() : taskNode.getIncomingConnections();
		WorkProductComposite workproductComposite = null;
		for (Link link : links) {
			Node node = type == WorkProductComposite.OUTPUTS ? link.getTarget() : link.getSource();
			if(node instanceof WorkProductComposite) {
				WorkProductComposite wpc = (WorkProductComposite) node;
				if(wpc.getLinkedElement() == taskDescriptor && wpc.getType() == type) {					
					workproductComposite = wpc;
					break;
				}				
			}
		}
		if(workproductComposite == null) {
			workproductComposite = ModelFactory.eINSTANCE
			.createWorkProductComposite();
			workproductComposite.setType(type);
			((WorkProductCompositeImpl) workproductComposite).setTaskNode(taskNode);
		}
		workproductComposite.setUMAContainer(getGraphNode());
		workproductComposite.setDiagram(this);
		workproductComposite.setObject(taskDescriptor);
		
		setState(type, workproductComposite);
		return workproductComposite;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.diagram.model.impl.DiagramImpl#getType()
	 */
	protected int getType() {
		return GraphicalDataHelper.ACTIVITY_DETAIL_DIAGRAM;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.diagram.model.impl.DiagramImpl#getDiagramChangeListenerType()
	 */
	protected Class getDiagramChangeListenerType() {
		return IActivityDetailDiagramChangeListener.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.diagram.model.impl.DiagramImpl#getBreakdownElementType()
	 */
	protected Class getBreakdownElementType() {
		return Descriptor.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.diagram.model.impl.DiagramImpl#toNode(org.eclipse.epf.uma.MethodElement)
	 */
	protected Node toNode(MethodElement e) {
		if (e instanceof RoleDescriptor) {
			List list = //AssociationHelper.getPrimaryTaskDescriptors((RoleDescriptor) e);
				getRealizedPrimaryTaskDescriptors((RoleDescriptor)e);
			if(!list.isEmpty()) {
				for (Iterator iterator = list.iterator(); iterator.hasNext();) {
					Object obj = iterator.next();
					if (obj instanceof TaskDescriptor) {
						createTaskInputOutputNodes((TaskDescriptor) obj, getNodes());
					}
				}
				return createRoleTaskComposite((RoleDescriptor) e);
			}
		} else if (e instanceof TaskDescriptor) {
			createTaskInputOutputNodes((TaskDescriptor) e, getNodes());
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.diagram.model.impl.DiagramImpl#addNode(java.lang.Object)
	 */
	protected Node addNode(Object obj) {
		if (!isValid(filter)) {
			filter = ((ConfigurableComposedAdapterFactory) DEFAULT_ADAPTER_FACTORIES[0])
					.getFilter();
		}
		Node node = null;
		if (obj instanceof TaskDescriptor) {
			List<RoleDescriptor> descList = ((TaskDescriptor) obj).getPerformedPrimarilyBy();
			find_node:
			for (RoleDescriptor roleDescriptor : descList)	{
				if (filter.accept(roleDescriptor)) {
					node = findNode(this, roleDescriptor);
					if (node == null) {
						node = createRoleTaskComposite(roleDescriptor);
						getNodes().add(node);
					} else {
						node = super.addNode(getNodes(), obj);
					}
					if(node != null) {
						break find_node;
					}
				}
			}
		} else {
			node = super.addNode(getNodes(), obj);
		}
		if (node != null) {
			populateLinks(Collections.singletonList(node), false);
		}
		return node;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.diagram.model.impl.DiagramImpl#addNodes(java.util.Collection)
	 */
	protected Collection addNodes(Collection collection) {
		Collection nodes = super.addNodes(collection);
		if (!nodes.isEmpty()) {
			populateLinks(nodes, false);
		}
		return nodes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.diagram.model.impl.DiagramImpl#removeFromUmaModel(org.eclipse.epf.diagram.model.Node)
	 */
	protected void removeFromUmaModel(Node removedNode) {
		// if the removed node is a TaskNode that had been moved to a
		// RoleTaskComposite
		// then its TaskDescriptor will not be removed from model
		//
		if (GraphicalDataHelper.findNode(this, removedNode.getObject()) != null) {
			return;
		}
		if (removedNode instanceof RoleTaskComposite
				|| removedNode instanceof WorkProductComposite)
			return;

		super.removeFromUmaModel(removedNode);
	}

	protected void addToUmaModel(int position, Node addedNode) {
		super.addToUmaModel(position, addedNode);

		if (addedNode.getObject() instanceof BreakdownElement) {
			Activity act = (Activity) getObject();
			// translate Node index to Activity index
			int i = toActivityIndex(position);
			System.out
					.println("ActivityDetailDiagram.addToUmaModel(): WorkBreakdownElement index: " + i); //$NON-NLS-1$
			if (i == -1) {
				act.getBreakdownElements().add((BreakdownElement) addedNode.getObject());
			} else {
				act.getBreakdownElements().add(i, (BreakdownElement) addedNode.getObject());
			}
		}

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

	public Node findNode(ActivityDetailDiagram diagram, Object object) {
		for (Iterator iter = diagram.getNodes().iterator(); iter.hasNext();) {
			Node element = (Node) iter.next();
			if (object == element.getObject()) {
				return element;
			}
		}
		return null;
	}
	
	private static final AdapterFactory[] DEFAULT_ADAPTER_FACTORIES = {			
		TngAdapterFactory.INSTANCE.getWBS_ComposedAdapterFactory(),
		TngAdapterFactory.INSTANCE.getOBS_ComposedAdapterFactory(),
		TngAdapterFactory.INSTANCE.getPBS_ComposedAdapterFactory() 
	}; 
	
	public void createTaskInputOutputNodes(TaskDescriptor descriptor, Collection nodes) {
		Node newNode = null;
		if (!descriptor.getMandatoryInput().isEmpty()) {
			newNode = createWorkProductComposite(descriptor,
					WorkProductComposite.INPUTS);
			nodes.add(newNode);
		}
		if (!descriptor.getOutput().isEmpty()) {
			newNode = createWorkProductComposite(descriptor,
					WorkProductComposite.OUTPUTS);
			nodes.add(newNode);
		}
	}
	
	public void createTaskInputOutputNodes(TaskNode taskNode, Collection nodes) {
		TaskDescriptor descriptor = (TaskDescriptor) taskNode.getLinkedElement();
		Node newNode = null;
		if (!descriptor.getMandatoryInput().isEmpty()) {
			newNode = createWorkProductComposite(taskNode,
					WorkProductComposite.INPUTS);
			nodes.add(newNode);
		}
		if (!descriptor.getOutput().isEmpty()) {
			newNode = createWorkProductComposite(taskNode,
					WorkProductComposite.OUTPUTS);
			nodes.add(newNode);
		}		
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.epf.diagram.model.impl.DiagramImpl#setNew(boolean)
	 */
	public void setNew(boolean n) {
		super.setNew(n);
		if(n){
			doSetAutoLayout(true);
		}
	}
	
	@Override
	/**
	 * Enable this code, once the task index is fixed in the roletaskcomposite.
	 */
	public void moveNode(Object movedObject, int position, Object oldPosition) {
		if(!(movedObject instanceof TaskDescriptor))
			return;
		TaskDescriptor taskDescriptor = (TaskDescriptor) movedObject;
		List<RoleDescriptor> descList = ((TaskDescriptor) movedObject)
				.getPerformedPrimarilyBy();
		find_node:
		for (RoleDescriptor roleDescriptor : descList) {
			if (filter.accept(roleDescriptor)) {
				Node roleNode = findNode(this, roleDescriptor);
				if (roleNode != null) {
					int oldPos = 0;
					if (oldPosition instanceof Integer) {
						oldPos = ((Integer) oldPosition).intValue();
					}
					TaskNode taskNode = (TaskNode) GraphicalDataHelper
							.findNode((NodeContainer) roleNode, taskDescriptor,
									TaskNode.class);

					// move up
					if (oldPos > position) {
						int i = taskNode.getIndex();
						Node prevNode = findTaskNode(
								(RoleTaskComposite) roleNode, i - 1);
						if (prevNode != null) {
							taskNode.setIndex(i - 1);
							((TaskNode) prevNode).setIndex(i);
						}

					} else {
						// move down
						int i = taskNode.getIndex();
						Node nextNode = findTaskNode(
								(RoleTaskComposite) roleNode, i + 1);
						if (nextNode != null) {
							taskNode.setIndex(i + 1);
							((TaskNode) nextNode).setIndex(i);
						}
					}
					
					break find_node;
				}
			}
		}
	}
	
	private Node findTaskNode(RoleTaskComposite container, int index) {
		for (Iterator iter = container.getNodes().iterator(); iter.hasNext();) {
			Node element = (Node) iter.next();
			if(element instanceof TaskNode){
				if(((TaskNode)element).getIndex() == index){
					return element;
				}
			}
		}
		return null;
	}
	
	public List getRealizedPrimaryTaskDescriptors(RoleDescriptor r){
		List list = new ArrayList();
		if(r != null){
			List actualList = AssociationHelper.getPrimaryTaskDescriptors(r);
			Collection collection = getAllBreakdownElements(true);
			for(Iterator iterator = actualList.iterator(); iterator.hasNext();){
				Object e = iterator.next();
				if(TngUtil.contains(collection, e)){
					list.add(e);
				}
			}
		}
		return list;
	}
	
	private void setState(int type, Node node) {
		TaskDescriptor td = null;
		
		if (node instanceof WorkProductComposite) {
			td = (TaskDescriptor)((WorkProductComposite)node).getLinkedElement();
		}
		
		if (node instanceof NodeContainer) {
			for (Node element : ((NodeContainer)node).getNodes()) {
				if (element instanceof WorkProductDescriptorNode) {
					WorkProductDescriptorNode wpNode = (WorkProductDescriptorNode) element;
					MethodElement methodElement = wpNode.getLinkedElement();
					if (methodElement instanceof WorkProductDescriptor){
						String stateText = null;

						boolean useNewWorkproductState = DiagramModelPreference.getUseStateOnWorkproduct();
						if (useNewWorkproductState) {
							WorkProductDescriptor wpd = (WorkProductDescriptor)methodElement;
							EReference ref = null;
							
							if (type == WorkProductComposite.INPUTS) {
								ref = UmaPackage.eINSTANCE.getTaskDescriptor_MandatoryInput();
							} else if (type == WorkProductComposite.OUTPUTS) {
								ref = UmaPackage.eINSTANCE.getTaskDescriptor_Output();
							}
							
							List<Constraint> states = TaskDescriptorPropUtil.getTaskDescriptorPropUtil().getWpStates(td, wpd, ref);
							if (states.size() > 0) {
								stateText = states.get(0).getBody();
							}							
						} else {
							if (type == WorkProductComposite.INPUTS)
								stateText = ((WorkProductDescriptor) methodElement).getActivityEntryState();
							else if (type == WorkProductComposite.OUTPUTS)
								stateText = ((WorkProductDescriptor) methodElement).getActivityExitState();							
						}
						
						wpNode.setState(stateText);
					}					
				}
			}	
		}
	}
	
} // ActivityDetailDiagramImpl
