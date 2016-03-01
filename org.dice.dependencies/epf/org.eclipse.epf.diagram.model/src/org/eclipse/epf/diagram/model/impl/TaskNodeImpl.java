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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.epf.diagram.model.Diagram;
import org.eclipse.epf.diagram.model.Link;
import org.eclipse.epf.diagram.model.ModelPackage;
import org.eclipse.epf.diagram.model.Node;
import org.eclipse.epf.diagram.model.TaskNode;
import org.eclipse.epf.diagram.model.util.GraphicalDataHelper;
import org.eclipse.epf.uma.Property;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.UmaPackage;


/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Task Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.diagram.model.impl.TaskNodeImpl#getIndex <em>Index</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TaskNodeImpl extends NamedNodeImpl implements TaskNode {
	/**
	 * The default value of the '{@link #getIndex() <em>Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIndex()
	 * @generated
	 * @ordered
	 */
	protected static final int INDEX_EDEFAULT = -1;

	/**
	 * The cached value of the '{@link #getIndex() <em>Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIndex()
	 * @generated
	 * @ordered
	 */
	protected int index = INDEX_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 */
	protected TaskNodeImpl() {
		super();

		methodElementAdapter = new TaskDescriptorAdapter();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.TASK_NODE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getIndex() {
		return index;
	}
	
	public int doGetIndex() {
		if(isGraphicalDataRequired()) {
			Property prop = GraphicalDataHelper.getProperty(this, GraphicalDataHelper.PROP_INDEX);
			if(prop != null) {
				try {
					int i = Integer.parseInt(prop.getValue());
					return i;
				}
				catch(NumberFormatException e) {
					//
				}
			}
		}
		return getIndex();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIndex(int newIndex) {
		int oldIndex = index;
		index = newIndex;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TASK_NODE__INDEX, oldIndex, index));
	}
	
	public void doSetIndex(int newIndex) {
		if(isGraphicalDataRequired()) {
			GraphicalDataHelper.createProperty(getGraphNode(), 
					GraphicalDataHelper.PROP_INDEX, String.valueOf(newIndex));
		}
		else {
			setIndex(newIndex);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelPackage.TASK_NODE__INDEX:
				return new Integer(getIndex());
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
			case ModelPackage.TASK_NODE__INDEX:
				setIndex(((Integer)newValue).intValue());
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
			case ModelPackage.TASK_NODE__INDEX:
				setIndex(INDEX_EDEFAULT);
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
			case ModelPackage.TASK_NODE__INDEX:
				return index != INDEX_EDEFAULT;
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
		result.append(" (index: "); //$NON-NLS-1$
		result.append(index);
		result.append(')');
		return result.toString();
	}

	private class TaskDescriptorAdapter extends MethodElementAdapter {
		
		@Override
		protected void handleNotification(Notification msg) {
			boolean handled = false;
			switch (msg.getFeatureID(TaskDescriptor.class)) {
			case UmaPackage.TASK_DESCRIPTOR__MANDATORY_INPUT:
				handled = true;
				break;
			case UmaPackage.TASK_DESCRIPTOR__OUTPUT:
				handled = true;
				break;				
			}
			if(handled && (msg.getEventType() == Notification.ADD || msg.getEventType() == Notification.ADD_MANY)) { 
				ActivityDetailDiagramImpl diagram = (ActivityDetailDiagramImpl) getDiagram();
				if(diagram != null) {
//					diagram.setObject(diagram.getObject());
					Collection inputOuputNodes = new ArrayList();
					diagram.createTaskInputOutputNodes((TaskDescriptor) msg.getNotifier(), inputOuputNodes);
					if(!inputOuputNodes.isEmpty()) {
						diagram.getNodes().addAll(inputOuputNodes);
						diagram.populateLinks(inputOuputNodes, false);
					}
				}
				return;
			}

//			MethodElement obj;
//			switch (msg.getFeatureID(TaskDescriptor.class)) {
//			case UmaPackage.TASK_DESCRIPTOR__MANDATORY_INPUT:
//			case UmaPackage.TASK_DESCRIPTOR__OPTIONAL_INPUT:
//			case UmaPackage.TASK_DESCRIPTOR__EXTERNAL_INPUT:
//				switch (msg.getEventType()) {
//				case Notification.ADD:
//					obj = (MethodElement) msg.getNewValue();
//					addIncomingConnection(obj);
//					return;
//				case Notification.REMOVE:
//					obj = (MethodElement) msg.getOldValue();
//					removeIncomingConnection(obj);
//					return;
//				case Notification.ADD_MANY:
//					Collection collection = (Collection) msg.getNewValue();
//					for (Iterator iter = collection.iterator(); iter
//							.hasNext();) {
//						obj = (MethodElement) iter.next();
//						addIncomingConnection(obj);
//					}
//					return;
//				case Notification.REMOVE_MANY:
//					collection = (Collection) msg.getOldValue();
//					for (Iterator iter = collection.iterator(); iter
//							.hasNext();) {
//						obj = (MethodElement) iter.next();
//						removeIncomingConnection(obj);
//					}
//					return;
//				}
//				break;
//			case UmaPackage.TASK_DESCRIPTOR__OUTPUT:
//				switch (msg.getEventType()) {
//				case Notification.ADD:
//					obj = (MethodElement) msg.getNewValue();
//					addOutgoingConnection(obj);
//					return;
//				case Notification.REMOVE:
//					obj = (MethodElement) msg.getOldValue();
//					removeOutgoingConnection(obj);
//					return;
//				case Notification.ADD_MANY:
//					Collection collection = (Collection) msg.getNewValue();
//					for (Iterator iter = collection.iterator(); iter
//							.hasNext();) {
//						obj = (MethodElement) iter.next();
//						addOutgoingConnection(obj);
//					}
//					return;
//				case Notification.REMOVE_MANY:
//					collection = (Collection) msg.getOldValue();
//					for (Iterator iter = collection.iterator(); iter
//							.hasNext();) {
//						obj = (MethodElement) iter.next();
//						removeOutgoingConnection(obj);
//					}
//					return;
//				}
//				break;
//			}

			super.handleNotification(msg);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.epf.diagram.model.util.INodeChangeListener#getNode()
		 */
		public Node getNode() {
			return TaskNodeImpl.this;
		}
	}

	// /* (non-Javadoc)
	// * @see
	// org.eclipse.epf.diagram.model.impl.NamedNodeImpl#setObject(java.lang.Object)
	// */
	// public void setObject(Object newObject) {
	// Object oldObject = object;
	// object = newObject;
	// if (eNotificationRequired())
	// eNotify(new ENotificationImpl(this, Notification.SET,
	// ModelPackage.LINKED_OBJECT__OBJECT, oldObject, object));
	//
	// if(newObject == null) return;
	//		
	// MethodElement element = (MethodElement) newObject;
	// if(element != null && getMethodElementAdapterType() != null) {
	// // remove old methodElementAdapter
	// //
	// Object oldAdapter = UmaUtil.getAdapter(element,
	// getMethodElementAdapterType());
	// if(oldAdapter != null) {
	// element.eAdapters().remove(oldAdapter);
	// }
	// }
	// graphNode =
	// GraphicalDataManager.getInstance().getGraphNode(getUMADiagram(),
	// element);
	//        
	// // System.out.println("NodeImpl.setObject(): element: '" +
	// element.getName() + "', graphNode: " + graphNode);
	//        
	// // populate data
	// //
	// setHeight(graphNode.getSize().getHeight().intValue());
	// setLocation(GraphicalDataHelper.toPoint(graphNode.getPosition()));
	// setWidth(graphNode.getSize().getWidth().intValue());
	//        
	// // add methodElementAdapter
	// //
	// if(methodElementAdapter != null) {
	// element.eAdapters().add(methodElementAdapter);
	// }
	//        
	// this.eAdapters().add(createNodeAdapter());
	//
	// setName(element.getName());
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.diagram.model.impl.NodeImpl#addToUMA(org.eclipse.epf.diagram.model.Link)
	 */
	protected boolean addToUMA(Link link) {
		if (!super.addToUMA(link))
			return false;

		//TaskDescriptor taskDesc = (TaskDescriptor) getObject();
		NodeImpl nodeImpl = (NodeImpl) link.getSource();
		boolean notify = notificationEnabled;
		boolean notifySource = nodeImpl.notificationEnabled;
		try {
			notificationEnabled = false;
			nodeImpl.notificationEnabled = false;
			// taskDesc.getMandatoryInput().add(link.getSource().getObject());
		} finally {
			notificationEnabled = notify;
			nodeImpl.notificationEnabled = notifySource;
		}

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.diagram.model.impl.NodeImpl#removeFromUMA(org.eclipse.epf.diagram.model.Link,
	 *      org.eclipse.epf.diagram.model.Node, org.eclipse.epf.diagram.model.Node)
	 */
	protected void removeFromUMA(Link link, Node oldSource, Node oldTarget) {
		TaskDescriptor taskDesc = (TaskDescriptor) getObject();
		taskDesc.getOutput().remove(link.getTarget().getObject());

		super.removeFromUMA(link, oldSource, oldTarget);
	}

} // TaskNodeImpl
