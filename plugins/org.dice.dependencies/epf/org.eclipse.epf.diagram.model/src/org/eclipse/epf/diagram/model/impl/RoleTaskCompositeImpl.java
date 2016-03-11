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
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.epf.diagram.model.ActivityDetailDiagram;
import org.eclipse.epf.diagram.model.ModelFactory;
import org.eclipse.epf.diagram.model.ModelPackage;
import org.eclipse.epf.diagram.model.Node;
import org.eclipse.epf.diagram.model.RoleNode;
import org.eclipse.epf.diagram.model.RoleTaskComposite;
import org.eclipse.epf.diagram.model.TaskNode;
import org.eclipse.epf.diagram.model.WorkProductComposite;
import org.eclipse.epf.diagram.model.util.GraphicalDataHelper;
import org.eclipse.epf.diagram.model.util.GraphicalDataManager;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Diagram;
import org.eclipse.epf.uma.GraphNode;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Property;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.UMASemanticModelBridge;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.ecore.util.OppositeFeatureNotification;
import org.eclipse.epf.uma.util.AssociationHelper;


/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Role Task Composite</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.diagram.model.impl.RoleTaskCompositeImpl#getRowIndex <em>Row Index</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RoleTaskCompositeImpl extends NodeContainerImpl implements
		RoleTaskComposite {
	
	/**
	 * The default value of the '{@link #getRowIndex() <em>Row Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRowIndex()
	 * @generated
	 * @ordered
	 */
	protected static final int ROW_INDEX_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getRowIndex() <em>Row Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRowIndex()
	 * @generated
	 * @ordered
	 */
	protected int rowIndex = ROW_INDEX_EDEFAULT;

//	private int rowIndex;
	
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 */
	protected RoleTaskCompositeImpl() {
		super();

		methodElementAdapter = new RoleDescriptorAdapter();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.ROLE_TASK_COMPOSITE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getRowIndex() {
		return rowIndex;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRowIndex(int newRowIndex) {
		int oldRowIndex = rowIndex;
		rowIndex = newRowIndex;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.ROLE_TASK_COMPOSITE__ROW_INDEX, oldRowIndex, rowIndex));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelPackage.ROLE_TASK_COMPOSITE__ROW_INDEX:
				return new Integer(getRowIndex());
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
			case ModelPackage.ROLE_TASK_COMPOSITE__ROW_INDEX:
				setRowIndex(((Integer)newValue).intValue());
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
			case ModelPackage.ROLE_TASK_COMPOSITE__ROW_INDEX:
				setRowIndex(ROW_INDEX_EDEFAULT);
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
			case ModelPackage.ROLE_TASK_COMPOSITE__ROW_INDEX:
				return rowIndex != ROW_INDEX_EDEFAULT;
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
		result.append(" (rowIndex: "); //$NON-NLS-1$
		result.append(rowIndex);
		result.append(')');
		return result.toString();
	}

	private static boolean contains(Collection mixedCollection, Object element) {
		for (Iterator iter = mixedCollection.iterator(); iter.hasNext();) {
			if( element == TngUtil.unwrap(iter.next()) ) {
				return true;
			}		
		}
		return false;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.diagram.model.impl.NodeImpl#setObject(java.lang.Object)
	 */
	public void setObject(Object newObject) {
		super.setObject(newObject);
		populateNodes(newObject);
	}
	
	public GraphNode getGraphNode(MethodElement methodElement) {
		if(isGraphicalDataRequired()) {
			if (getUMADiagram() == null) {
				// new MethodElement
				//
				return GraphicalDataManager.getInstance().createGraphNode(methodElement);
			}
			GraphNode node = findGraphNode(getUMADiagram(), methodElement);
			if (node == null) {
				node = GraphicalDataManager.getInstance().createGraphNode(methodElement);
				// createGraphConnectors(node, getUMADiagram());
				getUMADiagram().getContained().add(node);
			}
			String autolayout = GraphicalDataHelper.getAutoLayoutFlag((ActivityDetailDiagram)getDiagram());
			if(autolayout != null){
				GraphicalDataHelper.createProperty(node, GraphicalDataHelper.PROP_INDEX, new Integer(rowIndex).toString());
			}
			return node;
		}
		return null;
	}

	public GraphNode findGraphNode(Diagram diagram, Object methodElement) {
		for (Iterator iter = diagram.getContained().iterator(); iter.hasNext();) {
			GraphNode element = (GraphNode) iter.next();
			if (element.getSemanticModel() instanceof UMASemanticModelBridge
					&& methodElement == ((UMASemanticModelBridge) element
							.getSemanticModel()).getElement()) {

				Property property = GraphicalDataHelper.findProperty(element, GraphicalDataHelper.PROP_INDEX);
				
				if (property != null) {
					String index = property.getValue();
					if (index != null) {
						if (getRowIndex() == new Integer(index).intValue())
							return element;
					}
				}else{
					return element;
				}
			}
		}
		return null;
	}
	
//	public GraphNode createGraphNode(MethodElement element) {
//		GraphNode node = GraphicalDataManager.getInstance().createGraphNode(element);
//		GraphicalDataHelper.createProperty(node,
//				GraphicalDataHelper.PROP_INDEX, new Integer(rowIndex)
//						.toString());
//		return node;
//	}
	
	private class RoleDescriptorAdapter extends TransactionalNodeLink {

		@Override
		protected void handleNotification(Notification msg) {
			if(msg instanceof OppositeFeatureNotification) {
				Object feature = ((OppositeFeatureNotification)msg).getOppsiteFeature();
				if(feature == AssociationHelper.RoleDescriptor_PrimaryTaskDescriptors) {
					switch (msg.getEventType()) {
					case Notification.ADD:
						addNode((TaskDescriptor) msg.getNewValue());
						break;
					case Notification.REMOVE:
						removeNode(msg.getOldValue());
						break;
					case Notification.ADD_MANY:
						addNode((Collection) msg.getNewValue());
						break;
					case Notification.REMOVE_MANY:
						removeNode((Collection) msg.getOldValue());
						break;						
					}
					return;
				}
			}
			switch (msg.getFeatureID(Activity.class)) {
			case UmaPackage.ACTIVITY__BREAKDOWN_ELEMENTS:
				switch (msg.getEventType()) {
				case Notification.MOVE:
//					System.out.println("MOVEEEEEEED");
					break;
				}
				break;
			}
		}

	}

	/**
	 * @param oldValue
	 */
	private void removeNode(Object oldValue) {
		org.eclipse.epf.diagram.model.Diagram diagram = getDiagram();
		if(diagram == null) {
			System.err.println("RoleTaskCompositeImpl.removeNode(): [ERROR] no diagram"); //$NON-NLS-1$
		}
		Collection removedNodes = new HashSet();
		for (Iterator iter = nodes.iterator(); iter.hasNext();) {
			Node node = (Node) iter.next();
			if (oldValue instanceof TaskDescriptor) {
				if (node instanceof RoleNode) {
					ActivityDetailDiagramImpl d = (ActivityDetailDiagramImpl)getDiagram();
					List list = d.getRealizedPrimaryTaskDescriptors((RoleDescriptor) node.getObject());
					if (list == null || list.size() < 1) {
						iter.remove();
						removedNodes.add(node);
					}
				}
			}
			if (node.getObject() == oldValue) {
				iter.remove();
				removedNodes.add(node);
				if (oldValue instanceof TaskDescriptor) {
					Node wpnode = GraphicalDataHelper.findNode(
							(ActivityDetailDiagram) getDiagram(), oldValue);
					getDiagram().getNodes().remove(wpnode);
					wpnode = GraphicalDataHelper.findNode(
							(ActivityDetailDiagram) getDiagram(), oldValue);
					getDiagram().getNodes().remove(wpnode);
				}
			}
		}
		if (nodes.isEmpty()) {
			getDiagram().getNodes().remove(this);
		}
		// pushBackToDiagram(removedNodes);
	}

	/**
	 * @param descriptor
	 */
	private void addNode(TaskDescriptor descriptor) {
		addNode(getNodes(), descriptor);
	}

	private void addNode(Collection nodes, TaskDescriptor descriptor) {
		if(descriptor.getSuperActivities() == null) {
			return;
		}
		ActivityDetailDiagramImpl diagram = (ActivityDetailDiagramImpl) getDiagram();
		Node node = GraphicalDataHelper.findNode(this,
				descriptor, TaskNode.class);
		if (node == null) {
			node = diagram.createNode(descriptor);
			if(node != null) {
				int i = getNextIndex(getNodes());
				if (node instanceof TaskNodeImpl) {
					((TaskNodeImpl) node).doSetIndex(i + 1);
				}
				nodes.add(node);
				Collection inputOutputNodes = new ArrayList();
				diagram.createTaskInputOutputNodes(descriptor, inputOutputNodes);				
				if (!inputOutputNodes.isEmpty()) {
					diagram.getNodes().addAll(inputOutputNodes);
					diagram.populateLinks(inputOutputNodes, false);
				}
			}
 		}
	}

	/**
	 * @param oldValue
	 */
	private void removeNode(Collection elements) {
		Collection nodes = new ArrayList();
		for (Iterator iter = nodes.iterator(); iter.hasNext();) {
			Node node = (Node) iter.next();
			if (elements.contains(node.getObject())) {
				nodes.add(node);
			}
		}
		getNodes().removeAll(nodes);
		// pushBackToDiagram(nodes);
	}

	/**
	 * @param newValue
	 */
	private void addNode(Collection elements) {
		Collection nodes = new ArrayList();
		for (Iterator iter = elements.iterator(); iter.hasNext();) {
			TaskDescriptor descriptor = (TaskDescriptor) iter.next();
			addNode(nodes, descriptor);
		}

		getNodes().addAll(nodes);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.diagram.model.impl.NodeImpl#getMethodElementAdapterType()
	 */
	public Class getMethodElementAdapterType() {
		return RoleDescriptorAdapter.class;
	}
	
	/**
	 * @return
	 */
	private int getNextIndex(List nodes) {
		int index = 0;
		for(Iterator iterator = nodes.iterator(); iterator.hasNext();){
			Object obj = iterator.next();			
//			if(obj instanceof Node){
//				Node node = (Node)obj;
//				//String temp = GraphicalDataHelper.findProperty(node.getGraphNode(), GraphicalDataHelper.PROP_INDEX).getValue();
//				List list = node.getGraphNode().getList(UmaPackage.GRAPH_NODE__PROPERTY);
//				if(list != null && !list.isEmpty()){
//					for (int i = list.size() - 1; i > -1; i--) {
//						Property prop = (Property) list.get(i);
//						if (GraphicalDataHelper.PROP_INDEX.equals(prop.getKey())){
//							int temp  = Integer.parseInt(prop.getValue());
//							if(temp > index ) index = temp;
//						}
//					}
//				}
//			}
			if(obj instanceof TaskNodeImpl) {
				int id = ((TaskNodeImpl)obj).doGetIndex();
				if(id > index) {
					index = id;
				}
			}
		}
		return index;
	}
	
	private static final Comparator comparator = new Comparator() {

		public int compare(Object obj1, Object obj2) {
			if (obj1 == obj2)
				return 0;
			if(obj1 instanceof TaskNodeImpl && obj2 instanceof TaskNodeImpl){
				TaskNodeImpl node1 = (TaskNodeImpl)obj1;
				TaskNodeImpl node2 = (TaskNodeImpl)obj2;
				if(node1.doGetIndex() >  node2.doGetIndex())
					return 1;
			}
			return 0;
		}

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
	
	public WorkProductComposite createWorkProductComposite(
			TaskDescriptor taskDescriptor, int type) {
		WorkProductComposite workproductComposite = ModelFactory.eINSTANCE
				.createWorkProductComposite();
		workproductComposite.setUMAContainer(getGraphNode());
		workproductComposite.setDiagram(getDiagram());
		workproductComposite.setType(type);
		workproductComposite.setObject(taskDescriptor);
		return workproductComposite;
	}
	
	private TaskNode createTaskNode(TaskDescriptor td) {
		TaskNode taskNode = null;
		for (Iterator iter = getNodes().iterator(); iter.hasNext();) {
			Node node = (Node) iter.next();
			if(node instanceof TaskNode) {
				TaskNode tn = (TaskNode) node;
				if(tn.getLinkedElement() == td) {
					taskNode = tn;
					break;
				}
			}
		}
		if(taskNode == null) {
			taskNode = ModelFactory.eINSTANCE.createTaskNode();
			taskNode.setDiagram(getDiagram());
		}
		taskNode.setObject(td);
		return taskNode;
	}
	
	private RoleNode createRoleNode(RoleDescriptor rd) {
		RoleNode roleNode = null;
		for (Iterator iter = getNodes().iterator(); iter.hasNext();) {
			Node node = (Node) iter.next();
			if(node instanceof RoleNode) {
				RoleNode rn = (RoleNode) node;
				if(rn.getLinkedElement() == rd) {
					roleNode = rn;
					break;
				}
			}
		}
		if(roleNode == null) {
			roleNode = ModelFactory.eINSTANCE.createRoleNode();
			roleNode.setDiagram(getDiagram());
			roleNode.setObject(rd);
		}
		return roleNode;
	}
	
	public void populateNodes(Object newObject){
		
		if (newObject instanceof RoleDescriptor) {
			RoleDescriptor roleDescriptor = (RoleDescriptor) newObject;
			ActivityDetailDiagramImpl diagram = (ActivityDetailDiagramImpl) getDiagram();
			Collection allElements = diagram.getAllBreakdownElements(true);
			List taskNodes = new ArrayList();
			List primaryTaskDescriptors = AssociationHelper.getPrimaryTaskDescriptors(roleDescriptor);
			if (!primaryTaskDescriptors.isEmpty()) { // overriding
																	
				List taskDescriptors = new ArrayList();
				taskDescriptors.addAll(primaryTaskDescriptors);
				HashSet selectedDescriptors = new HashSet();
				boolean autoLayout = ((ActivityDetailDiagramImpl)getDiagram()).isAutoLayout();
				if(autoLayout) {
					int index = 0;
					int rowTasksCount = 0;
					int startCount = 0;
					
					for (Iterator iter = allElements.iterator(); iter.hasNext();) {
						Object obj = iter.next();
						obj = TngUtil.unwrap(obj);
						if (obj instanceof TaskDescriptor
								&& TngUtil.contains(taskDescriptors, obj)) {
							TaskDescriptor taskDescriptor = (TaskDescriptor) obj;
							
							if(startCount < rowIndex * GraphicalDataHelper.getTasksPerRow()){
								startCount++;
							} else {

								if (!selectedDescriptors
										.contains(taskDescriptor)) {
									selectedDescriptors.add(taskDescriptor);
									Node node = GraphicalDataHelper.findNode(
											this, taskDescriptor,
											TaskNode.class);
									if (node == null) {
//										node = createTaskNode(taskDescriptor,
//												index, allElements);
										node = createTaskNode(taskDescriptor);
										TaskNodeImpl tn = (TaskNodeImpl) node;
										if (autoLayout || tn.doGetIndex() == -1) {
											tn.doSetIndex(index);
//											index++;
										}
										//return node;
									}
									else {
										// this will make sure that link between the node and linked element is established
										//
										node.setObject(taskDescriptor);
									}
									((NamedNodeImpl) node).setItemProvider(allElements);

									if (rowTasksCount < GraphicalDataHelper
											.getTasksPerRow()) {
										taskNodes.add(node);
										if(node instanceof TaskNode){
											((TaskNode)node).setIndex(index);
											index++;
										}
										rowTasksCount++;
									} else {
										break;
									}
								}
							}
						}
					}
					// Sort the taskNode based on their index if autolayout is
					// false
					if (!autoLayout) {
						Collections.sort(taskNodes, comparator);
					}
				} else {
					List oldTaskDescriptors = new ArrayList();
					Diagram umaDiagram = getUMADiagram();
					if(umaDiagram != null) {
						// Lame way of doing this, to fix a defect of 1.0 to 1.0.1 migration				  
						List graphNodes = umaDiagram.getContained();
						for (Iterator iter = graphNodes.iterator(); iter.hasNext();) {
							GraphNode element = (GraphNode) iter.next();
							Object bridge = element.getSemanticModel();
							if(bridge instanceof UMASemanticModelBridge){
								Object taskObject = ((UMASemanticModelBridge)bridge).getElement();
								List list = element.getList(UmaPackage.GRAPH_NODE__PROPERTY);
								Property property = GraphicalDataHelper.getPropertyByKey(list,
										GraphicalDataHelper.PROP_WORK_PRODUCT_COMPOSITE_TYPE);
								if (taskDescriptors.contains(taskObject) && property == null) {
									oldTaskDescriptors.add(taskObject);
								}
							}
						}
					}
					else {
						for (Iterator iter = getNodes().iterator(); iter
								.hasNext();) {
							Node node = (Node) iter.next();
							if(node.getLinkedElement() instanceof TaskDescriptor) {
								oldTaskDescriptors.add(node.getLinkedElement());
							}
						}
					}
					for (Iterator iter = oldTaskDescriptors.iterator(); iter.hasNext();) {
						TaskDescriptor taskDescriptor = (TaskDescriptor) iter.next();

						// eliminate descriptor that is duplicate, suppressed or filtered out
						//
						if(!selectedDescriptors.contains(taskDescriptor)
								&& contains(allElements, taskDescriptor) // make sure that task descriptor is of the owning activity
						) {
							selectedDescriptors.add(taskDescriptor);					
							Node node = GraphicalDataHelper.findNode(getDiagram(),
									taskDescriptor, TaskNode.class);
							if (node == null) {
								node = createTaskNode(taskDescriptor);
								((NamedNodeImpl) node).setItemProvider(allElements);
							}
							taskNodes.add(node);
						}
					}
				}
			}
			if(!taskNodes.isEmpty()) {
				// add role node
				//
				NamedNodeImpl namedNode = ((NamedNodeImpl) createRoleNode(roleDescriptor));
				namedNode.visible = true;
				namedNode.setItemProvider(allElements);
				getNodes().add(namedNode);		
				taskNodes.add(namedNode);
			}
			selectNodes(taskNodes);
		}
		
	}
	
//	public Node createTaskNode(TaskDescriptor taskDescriptor, int index, Collection allElements) {
//		Node node = ModelFactory.eINSTANCE.createTaskNode();
//		node.setDiagram(getDiagram());
//		node.setObject(taskDescriptor);
//		((NamedNodeImpl) node).setItemProvider(allElements);
//		if (GraphicalDataHelper.findProperty(node.getGraphNode(),
//				GraphicalDataHelper.PROP_INDEX) == null
//				|| GraphicalDataHelper
//						.isAutoLayout((ActivityDetailDiagram) getDiagram())) {
//			GraphicalDataHelper.createProperty(node.getGraphNode(),
//					GraphicalDataHelper.PROP_INDEX, new Integer(index)
//							.toString());
//			index++;
//		}
//		return node;
//	}
	
} // RoleTaskCompositeImpl
