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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.epf.diagram.model.ActivityDetailDiagram;
import org.eclipse.epf.diagram.model.ModelFactory;
import org.eclipse.epf.diagram.model.ModelPackage;
import org.eclipse.epf.diagram.model.Node;
import org.eclipse.epf.diagram.model.TaskNode;
import org.eclipse.epf.diagram.model.WorkProductComposite;
import org.eclipse.epf.diagram.model.WorkProductDescriptorNode;
import org.eclipse.epf.diagram.model.util.GraphicalDataHelper;
import org.eclipse.epf.diagram.model.util.IAdapterFactoryFilter;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.util.ConfigurableComposedAdapterFactory;
import org.eclipse.epf.library.edit.util.Suppression;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Diagram;
import org.eclipse.epf.uma.GraphNode;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Property;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.UMASemanticModelBridge;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.WorkProductDescriptor;


/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Work Product Composite</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.diagram.model.impl.WorkProductCompositeImpl#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class WorkProductCompositeImpl extends NodeContainerImpl implements
		WorkProductComposite {
	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final int TYPE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected int type = TYPE_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected WorkProductCompositeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.WORK_PRODUCT_COMPOSITE;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public int getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(int newType) {
		int oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.WORK_PRODUCT_COMPOSITE__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelPackage.WORK_PRODUCT_COMPOSITE__TYPE:
				return new Integer(getType());
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
			case ModelPackage.WORK_PRODUCT_COMPOSITE__TYPE:
				setType(((Integer)newValue).intValue());
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
			case ModelPackage.WORK_PRODUCT_COMPOSITE__TYPE:
				setType(TYPE_EDEFAULT);
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
			case ModelPackage.WORK_PRODUCT_COMPOSITE__TYPE:
				return type != TYPE_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (type: "); //$NON-NLS-1$
		result.append(type);
		result.append(')');
		return result.toString();
	}

	private class TaskDescriptorAdapter extends TransactionalNodeLink {
		protected void handleNotification(Notification msg) {
			doHandleNotification(msg);
			if(WorkProductCompositeImpl.this.getNodes().isEmpty()) {				
				// refresh diagram
				//
				org.eclipse.epf.diagram.model.Diagram diagram = getDiagram();
				if(diagram != null) {
					diagram.getNodes().remove(WorkProductCompositeImpl.this);
					diagram.setObject(diagram.getObject());
				}				
			}
		}
		
		private void doHandleNotification(Notification msg) {
			switch (msg.getFeatureID(TaskDescriptor.class)) {
			case UmaPackage.TASK_DESCRIPTOR__OUTPUT:
				switch (msg.getEventType()) {
				case Notification.ADD:
					Object obj = msg.getNewValue();
					if (obj != null && type == WorkProductComposite.OUTPUTS) {
						addNode(obj);
					}
					return;
				case Notification.REMOVE:
					obj = msg.getOldValue();
					if (obj != null && type == WorkProductComposite.OUTPUTS) {
						removeNode(obj);
					}
					return;
				}
				break;
			case UmaPackage.TASK_DESCRIPTOR__MANDATORY_INPUT:
				switch (msg.getEventType()) {
				case Notification.ADD:
					Object obj = msg.getNewValue();
					if (obj != null && type == WorkProductComposite.INPUTS) {
						addNode(obj);
					}
					return;
				case Notification.REMOVE:
					obj = msg.getOldValue();
					if (obj != null && type == WorkProductComposite.INPUTS) {
						removeNode(obj);
					}
					return;
				}
				break;
			}
		}
		
	}

	private IFilter getFilter() {
		DiagramImpl diagram = (DiagramImpl) getDiagram();
		if (diagram.filter == null) {
			ConfigurableComposedAdapterFactory adapterFactory = (ConfigurableComposedAdapterFactory) TngAdapterFactory.INSTANCE
					.getPBS_ComposedAdapterFactory();
			return adapterFactory.getFilter();
		} else {
			return diagram.filter;
		}
	}
	public void setObject(Object newObject) {
		
		super.setObject(newObject);
		populateNodes(newObject);
		
	}

	protected void basicSetObject(Object newObject) {

		super.basicSetObject(newObject);
		
		if(isGraphicalDataRequired()) {
			// Set the Type information for workproduct composite.
			if (type == TYPE_EDEFAULT) {
				List list = graphNode.getList(UmaPackage.GRAPH_NODE__PROPERTY);
				Property property = GraphicalDataHelper.getPropertyByKey(list,
						GraphicalDataHelper.PROP_WORK_PRODUCT_COMPOSITE_TYPE);
				if (property != null) {
					property.setValue(property.getValue());
					list.add(property);
				}
			} else {
				List list = graphNode.getList(UmaPackage.GRAPH_NODE__PROPERTY);
				Property property = GraphicalDataHelper.getPropertyByKey(list,
						GraphicalDataHelper.PROP_WORK_PRODUCT_COMPOSITE_TYPE);
				if (property != null) {
					property.setValue(new Integer(getType()).toString());
					list.add(property);
				}
			}
		}
	}
	
	private WorkProductDescriptorNode createWorkProductDescriptorNode(WorkProductDescriptor wpd) {
		WorkProductDescriptorNode wpdNode = null;
		for (Iterator iter = getNodes().iterator(); iter.hasNext();) {
			Node node = (Node) iter.next();
			if(node instanceof WorkProductDescriptorNode && node.getLinkedElement() == wpd) {
				wpdNode = (WorkProductDescriptorNode) node;
			}
		}
		if(wpdNode == null) {
			wpdNode = ModelFactory.eINSTANCE.createWorkProductDescriptorNode();
			wpdNode.setDiagram(getDiagram());
		}
		wpdNode.setObject(wpd);
		return wpdNode;
	}
	
	public void populateNodes(Object newObject){
		if (newObject instanceof TaskDescriptor) {
			List wplist = new ArrayList();
			if (getType() == WorkProductComposite.INPUTS) {
				if (!((TaskDescriptor) newObject).getMandatoryInput().isEmpty())
					wplist.addAll(((TaskDescriptor) newObject)
							.getMandatoryInput());
			} else {
				if (!((TaskDescriptor) newObject).getOutput().isEmpty())
					wplist.addAll(((TaskDescriptor) newObject).getOutput());
			}

			IFilter filter = getFilter();
			Collection allElements = ((ActivityDetailDiagramImpl)getDiagram())
										.getAllBreakdownElements(false);
			AdapterFactory adapterFactory = getAdapterFactory();
			
			ArrayList nodes = new ArrayList();
			Suppression suppression = getDiagram().getSuppression();
//			Suppression suppression =  new Suppression(TngUtil.getOwningProcess(getObject()));
			for (Iterator iter = wplist.iterator(); iter.hasNext();) {
				WorkProductDescriptor wpDescriptor = (WorkProductDescriptor) iter
						.next();			
				if (filter == null || filter.accept(wpDescriptor)) {
					
					Object wrapper = null;
//					if(adapterFactory != null){
//						wrapper = adapterFactory.adapt(wpDescriptor, ITreeItemContentProvider.class);
//					}
					for (Iterator iterator = allElements.iterator(); iterator
							.hasNext();) {
						Object element = (Object) iterator.next();
						if(TngUtil.unwrap(element) == wpDescriptor){
							wrapper = element;
						}
					}
					if(wrapper == null && adapterFactory != null){
						wrapper = adapterFactory.adapt(wpDescriptor, ITreeItemContentProvider.class);
					}
					if(wrapper == null){
						wrapper = wpDescriptor;
					}
					if (!suppression.isSuppressed(wrapper)) {
						Node node = GraphicalDataHelper.findNode(this,
								wpDescriptor);
						if (node == null) {
							node = createWorkProductDescriptorNode(wpDescriptor);
						}
						else {
							// this will make sure that link between the node and linked element is established
							//
							node.setObject(wpDescriptor);
						}
						((NamedNodeImpl) node).setItemProvider(allElements);
						nodes.add(node);
					}
				}
			}

			selectNodes(nodes);
		}
	}
	
	public void addNode(Collection collection) {
		Collection nodes = new ArrayList();
		for (Iterator iter = collection.iterator(); iter.hasNext();) {
			WorkProductDescriptor descriptor = (WorkProductDescriptor) iter
					.next();
			addNode(nodes, descriptor);
		}

		getNodes().addAll(nodes);
	}

	public void removeNode(Object oldValue) {
		Collection removedNodes = new HashSet();
		if (oldValue instanceof TaskDescriptor) {
			Node node = findNode((ActivityDetailDiagram) getDiagram(),
					getObject());
			getDiagram().getNodes().remove(node);
		}
		for (Iterator iter = nodes.iterator(); iter.hasNext();) {
			Node node = (Node) iter.next();
			if (node.getObject() == oldValue) {
				iter.remove();
				removedNodes.add(node);
			}
		}
		// pushBackToDiagram(removedNodes);
	}

	public void addNode(Object descriptor) {
		addNode(getNodes(), descriptor);
	}

	private void addNode(Collection nodes, Object descriptor) {
		
		if (descriptor instanceof TaskDescriptor) {
			Node node = findNode((ActivityDetailDiagram) getDiagram(),
					descriptor);
			if (node == null) {
				node = ((ActivityDetailDiagramImpl) getDiagram())
						.createWorkProductComposite(
								(TaskDescriptor) descriptor, this.type);
				getDiagram().getNodes().add(node);
			}
		}
		if(descriptor instanceof WorkProductDescriptor){
			ActivityDetailDiagramImpl diagram = (ActivityDetailDiagramImpl) getDiagram();
			Node node = GraphicalDataHelper.findNode(this ,
					descriptor);
			if (node == null) {
				if (descriptor instanceof WorkProductDescriptor){
					node = diagram.createNode((WorkProductDescriptor) descriptor);
				}
				nodes.add(node);
			}
		}
	}

	/**
	 * @param oldValue
	 */
//	private void removeNode(Collection elements) {
//		Collection nodes = new ArrayList();
//		for (Iterator iter = nodes.iterator(); iter.hasNext();) {
//			Node node = (Node) iter.next();
//			if (elements.contains(node.getObject())) {
//				nodes.add(node);
//			}
//		}
//		getNodes().removeAll(nodes);
//		pushBackToDiagram(nodes);
//	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.diagram.model.impl.NodeImpl#getMethodElementAdapterType()
	 */
	public Class getMethodElementAdapterType() {
		return TaskDescriptorAdapter.class;
	}

	public Point getLocation() {
		if (getType() == WorkProductComposite.INPUTS) {
			return super.getLocation();
		}
		if (getType() == WorkProductComposite.OUTPUTS) {
			return super.getLocation();
		} else {
			return new Point(-1, -1);
		}
	}

	public GraphNode getGraphNode(MethodElement methodElement) {
		if(isGraphicalDataRequired()) {
			if (getUMADiagram() == null) {
				// new MethodElement
				//
				return createGraphNode(methodElement);
			}
			GraphNode node = findGraphNode(getUMADiagram(), methodElement);
			if (node == null) {
				node = createGraphNode(methodElement);
				// createGraphConnectors(node, getUMADiagram());
				getUMADiagram().getContained().add(node);
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

				List list = element.getList(UmaPackage.GRAPH_NODE__PROPERTY);
				Property property = GraphicalDataHelper.getPropertyByKey(list,
						GraphicalDataHelper.PROP_WORK_PRODUCT_COMPOSITE_TYPE);
				if (property != null) {
					String typeString = property.getValue();
					if (typeString != null) {
						if (getType() == new Integer(typeString).intValue())
							return element;
					}
				}
			}
		}
		return null;
	}

	private GraphNode createGraphNode(MethodElement element) {
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

		// For setting type of WorkProductComposite
		Property property = UmaFactory.eINSTANCE.createProperty();
		property.setKey(GraphicalDataHelper.PROP_WORK_PRODUCT_COMPOSITE_TYPE);
		property.setValue(new Integer(getType()).toString()); //$NON-NLS-1$
		List list = new ArrayList();
		list.add(property);
		graphNode.set(UmaPackage.GRAPH_NODE__PROPERTY, list);
		return graphNode;
	}

	public Node findNode(ActivityDetailDiagram diagram, Object object) {
		for (Iterator iter = diagram.getNodes().iterator(); iter.hasNext();) {
			Node element = (Node) iter.next();
			if (object == element.getObject()
					&& element instanceof WorkProductComposite) {
				if (((WorkProductComposite) element).getType() == this.type)
					return element;
			}
		}
		return null;
	}
	
	@Override
	protected Object getMethodElementAdapter() {
		if(methodElementAdapter == null) {
			methodElementAdapter = new TaskDescriptorAdapter();
		}
		return methodElementAdapter;
	}
	
	private AdapterFactory getAdapterFactory(){
		AdapterFactory adapterFactory = null;
		IFilter filter = getFilter();
		if (filter == null) {
			adapterFactory = TngAdapterFactory.INSTANCE.getPBS_ComposedAdapterFactory();
		} else if (filter instanceof IAdapterFactoryFilter) {
			adapterFactory = (ConfigurableComposedAdapterFactory) ((IAdapterFactoryFilter) filter)
					.getWPBSAdapterFactory();
		}
		return adapterFactory;
	}
	
	private TaskNode taskNode;

	protected TaskNode getTaskNode() {
		return taskNode;
	}

	protected void setTaskNode(TaskNode taskNode) {
		this.taskNode = taskNode;
	}
	
} // WorkProductCompositeImpl
