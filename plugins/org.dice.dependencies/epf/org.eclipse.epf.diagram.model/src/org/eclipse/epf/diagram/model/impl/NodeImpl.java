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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.epf.common.CommonPlugin;
import org.eclipse.epf.diagram.model.Diagram;
import org.eclipse.epf.diagram.model.Link;
import org.eclipse.epf.diagram.model.ModelPackage;
import org.eclipse.epf.diagram.model.Node;
import org.eclipse.epf.diagram.model.impl.NamedNodeImpl.MethodElementAdapter;
import org.eclipse.epf.diagram.model.util.GraphicalDataHelper;
import org.eclipse.epf.diagram.model.util.GraphicalDataManager;
import org.eclipse.epf.diagram.model.util.INodeChangeListener;
import org.eclipse.epf.diagram.model.util.TxUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.GraphNode;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.WorkBreakdownElement;
import org.eclipse.epf.uma.WorkOrder;
import org.eclipse.epf.uma.util.UmaUtil;


/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.diagram.model.impl.NodeImpl#getLocation <em>Location</em>}</li>
 *   <li>{@link org.eclipse.epf.diagram.model.impl.NodeImpl#getWidth <em>Width</em>}</li>
 *   <li>{@link org.eclipse.epf.diagram.model.impl.NodeImpl#getHeight <em>Height</em>}</li>
 *   <li>{@link org.eclipse.epf.diagram.model.impl.NodeImpl#getIncomingConnections <em>Incoming Connections</em>}</li>
 *   <li>{@link org.eclipse.epf.diagram.model.impl.NodeImpl#getOutgoingConnections <em>Outgoing Connections</em>}</li>
 *   <li>{@link org.eclipse.epf.diagram.model.impl.NodeImpl#isReadOnly <em>Read Only</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class NodeImpl extends LinkedObjectImpl implements Node {
	/**
	 * The default value of the '{@link #getLocation() <em>Location</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getLocation()
	 * @generated
	 * @ordered
	 */
	protected static final Point LOCATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLocation() <em>Location</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getLocation()
	 * @generated
	 * @ordered
	 */
	protected Point location = LOCATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getWidth() <em>Width</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getWidth()
	 * @generated
	 * @ordered
	 */
	protected static final int WIDTH_EDEFAULT = -1;

	/**
	 * The cached value of the '{@link #getWidth() <em>Width</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getWidth()
	 * @generated
	 * @ordered
	 */
	protected int width = WIDTH_EDEFAULT;

	/**
	 * The default value of the '{@link #getHeight() <em>Height</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getHeight()
	 * @generated
	 * @ordered
	 */
	protected static final int HEIGHT_EDEFAULT = -1;

	/**
	 * The cached value of the '{@link #getHeight() <em>Height</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getHeight()
	 * @generated
	 * @ordered
	 */
	protected int height = HEIGHT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getIncomingConnections() <em>Incoming Connections</em>}' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getIncomingConnections()
	 * @generated
	 * @ordered
	 */
	protected EList<Link> incomingConnections;

	/**
	 * The cached value of the '{@link #getOutgoingConnections() <em>Outgoing Connections</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getOutgoingConnections()
	 * @generated
	 * @ordered
	 */
	protected EList<Link> outgoingConnections;

	/**
	 * The default value of the '{@link #isReadOnly() <em>Read Only</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isReadOnly()
	 * @generated
	 * @ordered
	 */
	protected static final boolean READ_ONLY_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isReadOnly() <em>Read Only</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isReadOnly()
	 * @generated
	 * @ordered
	 */
	protected boolean readOnly = READ_ONLY_EDEFAULT;

	private Adapter nodeAdapter;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected NodeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.NODE;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Point getLocation() {
		return location;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setLocation(Point newLocation) {
		Point oldLocation = location;
		location = newLocation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.NODE__LOCATION, oldLocation, location));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setWidth(int newWidth) {
		int oldWidth = width;
		width = newWidth;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.NODE__WIDTH, oldWidth, width));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setHeight(int newHeight) {
		int oldHeight = height;
		height = newHeight;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.NODE__HEIGHT, oldHeight, height));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Link> getIncomingConnections() {
		if (incomingConnections == null) {
			incomingConnections = new EObjectWithInverseResolvingEList<Link>(Link.class, this, ModelPackage.NODE__INCOMING_CONNECTIONS, ModelPackage.LINK__TARGET);
		}
		return incomingConnections;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Link> getOutgoingConnections() {
		if (outgoingConnections == null) {
			outgoingConnections = new EObjectContainmentWithInverseEList<Link>(Link.class, this, ModelPackage.NODE__OUTGOING_CONNECTIONS, ModelPackage.LINK__SOURCE);
		}
		return outgoingConnections;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isReadOnly() {
		return readOnly;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (location: "); //$NON-NLS-1$
		result.append(location);
		result.append(", width: "); //$NON-NLS-1$
		result.append(width);
		result.append(", height: "); //$NON-NLS-1$
		result.append(height);
		result.append(", readOnly: "); //$NON-NLS-1$
		result.append(readOnly);
		result.append(')');
		return result.toString();
	}

	/**
	 * Adds incoming connection
	 * 
	 * @param link
	 * @return
	 */
	protected boolean addToUMA(Link link) {
		if (link.getTarget() == null || link.getSource() == null)
			return false;
		if(isGraphicalDataRequired()) {
			GraphicalDataHelper.addGraphicalData(link);
		}
		return true;
	}

	/**
	 * Removes outgoing connection
	 * 
	 * @param link
	 * @param oldSource
	 * @param oldTarget
	 */
	protected void removeFromUMA(Link link, Node oldSource, Node oldTarget) {
		// GraphicalDataManager.getInstance().removeGraphConnectorPair(oldSource.getGraphNode(),
		// oldTarget.getGraphNode());
		
		GraphicalDataHelper.removeGraphicalData(link);
	}

	protected org.eclipse.epf.uma.Diagram getUMADiagram() {
		if (getDiagram() == null)
			return umaDiagram;
		return getDiagram().getUMADiagram();
	}

	protected GraphNode getUMAContainer() {
		Node container = (Node) eContainer();
		if (container == null)
			return umaContainer;
		return container.getGraphNode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.diagram.model.Node#setUMAContainer(org.eclipse.epf.uma.GraphNode)
	 */
	public void setUMAContainer(GraphNode umaContainer) {
		this.umaContainer = umaContainer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.diagram.model.ActivityNode#setUMADiagram(org.eclipse.epf.uma.Diagram)
	 */
	public void setUMADiagram(org.eclipse.epf.uma.Diagram umaDiagram) {
		this.umaDiagram = umaDiagram;
		umaContainer = umaDiagram;
	}

	protected class NodeAdapter extends AdapterImpl {
		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.emf.common.notify.impl.AdapterImpl#notifyChanged(org.eclipse.emf.common.notify.Notification)
		 */
		public void notifyChanged(Notification msg) {
			switch (msg.getFeatureID(Node.class)) {
			case ModelPackage.NODE__WIDTH:
				getGraphNode().getSize().setWidth(
						new Double(msg.getNewIntValue()));
				return;

			case ModelPackage.NODE__HEIGHT:
				if (getGraphNode() != null) {
					getGraphNode().getSize().setHeight(
							new Double(msg.getNewIntValue()));
				}
				return;

			case ModelPackage.NODE__LOCATION:
				if (getGraphNode() != null && msg.getNewValue() != null) {
					Point p = (Point) msg.getNewValue();
					getGraphNode().getPosition().setX(new Double(p.x));
					getGraphNode().getPosition().setY(new Double(p.y));
				}
				return;

			case ModelPackage.NODE__INCOMING_CONNECTIONS:
				Link link;
				switch (msg.getEventType()) {
				case Notification.ADD:
					link = (Link) msg.getNewValue();
					addToUMA(link);
					return;
				case Notification.ADD_MANY:
					for (Iterator iter = ((Collection) msg.getNewValue())
							.iterator(); iter.hasNext();) {
						addToUMA((Link) iter.next());
					}
					break;
				case Notification.REMOVE:
					link = (Link) msg.getOldValue();
					if (link.getSource() != null) {
						((NodeImpl) link.getSource()).removeFromUMA(link, link
								.getSource(), (Node) msg.getNotifier());
					}
					break;
				case Notification.REMOVE_MANY:
					for (Iterator iter = ((Collection) msg.getOldValue())
							.iterator(); iter.hasNext();) {
						link = (Link) iter.next();
						if (link.getSource() != null) {
							((NodeImpl) link.getSource()).removeFromUMA(link,
									link.getSource(), (Node) msg.getNotifier());
						}
					}
					break;
				}
				return;
			case ModelPackage.NODE__OUTGOING_CONNECTIONS:
				switch (msg.getEventType()) {
				case Notification.ADD:
					link = (Link) msg.getNewValue();
					if (link.getTarget() != null) {
						((NodeImpl) link.getTarget()).addToUMA(link);
					}
					break;
				case Notification.ADD_MANY:
					for (Iterator iter = ((Collection) msg.getNewValue())
							.iterator(); iter.hasNext();) {
						link = (Link) iter.next();
						if (link.getTarget() != null) {
							((NodeImpl) link.getTarget()).addToUMA(link);
						}
					}
					break;
				case Notification.REMOVE:
					link = (Link) msg.getOldValue();
					if (link.getTarget() != null) {
						removeFromUMA(link, (Node) msg.getNotifier(), link
								.getTarget());
					}
					break;
				case Notification.REMOVE_MANY:
					for (Iterator iter = ((Collection) msg.getOldValue())
							.iterator(); iter.hasNext();) {
						link = (Link) iter.next();
						if (link.getTarget() != null) {
							removeFromUMA(link, (Node) msg.getNotifier(), link
									.getTarget());
						}
					}
					break;
				}
				return;

			}
		}

	}
	
	/**
	 * Loads graphnode data and associates the adapters.
	 * @param newObject
	 */
	protected void basicSetObject(Object newObject) {
		if (newObject == null)
			return;

		this.object = newObject;
		MethodElement element = (MethodElement) newObject;
		
		graphNode = getGraphNode(element);

		// System.out.println("NodeImpl.setObject(): element: '" +
		// element.getName() + "', graphNode: " + graphNode);

		if(graphNode != null) {
			// populate/synch data
			//
			if (height == HEIGHT_EDEFAULT) {
				setHeight(graphNode.getSize().getHeight().intValue());
			} else {
				graphNode.getSize().setHeight(new Double(height));
			}

			if (width == WIDTH_EDEFAULT) {
				setWidth(graphNode.getSize().getWidth().intValue());
			} else {
				graphNode.getSize().setWidth(new Double(width));
			}

			if (location == LOCATION_EDEFAULT) {
				setLocation(GraphicalDataHelper.toPoint(graphNode.getPosition()));
			} else {
				graphNode.getPosition().setX(new Double(location.x));
				graphNode.getPosition().setY(new Double(location.y));
			}
		}

		// add methodElementAdapter
		//
		getMethodElementAdapter();
		if (methodElementAdapter != null && !element.eAdapters().contains(methodElementAdapter)) {
			element.eAdapters().add(methodElementAdapter);
		}

		if(nodeAdapter == null) {
			nodeAdapter = createNodeAdapter();
		}
		if(nodeAdapter != null && !eAdapters().contains(nodeAdapter)) {
			eAdapters().add(nodeAdapter);
		}
				
		Object e = TngUtil.unwrap(object);
		if(e instanceof MethodElement) {
			setLinkedElement((MethodElement) e);
		}
	}
	
	protected abstract class TransactionalNodeLink extends AdapterImpl 
	implements INodeChangeListener
	{
		public void notifyChanged(final Notification msg) {
			if(msg.getEventType() == Notification.REMOVING_ADAPTER) {
				return;
			}
			if (!notificationEnabled)
				return;
			notificationEnabled = false;
			try {
				TxUtil.runInTransaction(getNode(), 
						new Runnable() {

							public void run() {
								handleNotification(msg);
							}
					
				});
			} catch (Exception e) {
				CommonPlugin.getDefault().getLogger().logError(e);
			} finally {
				notificationEnabled = true;
			}
		}

		protected abstract void handleNotification(Notification msg);

		public Node getNode() {
			return NodeImpl.this;
		}
	}

	
	/**
	 * Subclass can override this method to provide MethodElementAdapter that will synchronize
	 * the change from method element to the node.
	 * 
	 * @return the method element adapter of this node
	 */
	protected Object getMethodElementAdapter() {
		return methodElementAdapter;
	}

	public GraphNode getGraphNode(MethodElement methodElement){
		if(isGraphicalDataRequired()) {
			return GraphicalDataManager.getInstance().getGraphNode(
					getUMADiagram(), methodElement);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.diagram.model.impl.LinkedObjectImpl#setObject(java.lang.Object)
	 */
	public void setObject(Object newObject) {
		super.setObject(newObject);
		
		basicSetObject(newObject);
	}

	/**
	 * @return
	 */
	protected Adapter createNodeAdapter() {
		return new NodeAdapter();
	}

	public GraphNode getGraphNode() {
		return graphNode;
	}

	// TODO: force subclass to implement this method
	public Class getMethodElementAdapterType() {
		return INodeChangeListener.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.diagram.model.Node#getDiagram()
	 */
	public Diagram getDiagram() {
		EObject parent = eContainer;
		for (; parent != null && !(parent instanceof Diagram); parent = parent
				.eContainer())
			;
		if (parent instanceof Diagram) {
			return (Diagram) parent;
		}
		return diagram;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.diagram.model.Node#setDiagram(org.eclipse.epf.diagram.model.Diagram)
	 */
	public void setDiagram(Diagram diagram) {
		this.diagram = diagram;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.diagram.model.Node#addConsumer(java.lang.Object)
	 */
	public void addConsumer(Object consumer) {
		if (!consumers.contains(consumer)) {
			consumers.add(consumer);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.diagram.model.Node#removeConsumer(java.lang.Object)
	 */
	public void removeConsumer(Object consumer) {
		consumers.remove(consumer);
		if (consumers.isEmpty()) {
			// no more consumer of this node, it should be disposed
			dispose();
		}
	}

	protected void dispose() {
		EObject obj = (EObject) getObject();
		if (obj != null) {
			if (methodElementAdapter != null) {
				obj.eAdapters().remove(methodElementAdapter);
			}
		}

		if(nodeAdapter != null) {
			eAdapters().remove(nodeAdapter);
		}

		umaContainer = null;
		umaDiagram = null;
		graphNode = null;
		
		if(outgoingConnections != null) {
			for (Object link : outgoingConnections) {
				((Link)link).eAdapters().clear();
			}
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setReadOnly(boolean newReadOnly) {
		boolean oldReadOnly = readOnly;
		readOnly = newReadOnly;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.NODE__READ_ONLY, oldReadOnly, readOnly));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
		@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.NODE__INCOMING_CONNECTIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getIncomingConnections()).basicAdd(otherEnd, msgs);
			case ModelPackage.NODE__OUTGOING_CONNECTIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOutgoingConnections()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.NODE__INCOMING_CONNECTIONS:
				return ((InternalEList<?>)getIncomingConnections()).basicRemove(otherEnd, msgs);
			case ModelPackage.NODE__OUTGOING_CONNECTIONS:
				return ((InternalEList<?>)getOutgoingConnections()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelPackage.NODE__LOCATION:
				return getLocation();
			case ModelPackage.NODE__WIDTH:
				return new Integer(getWidth());
			case ModelPackage.NODE__HEIGHT:
				return new Integer(getHeight());
			case ModelPackage.NODE__INCOMING_CONNECTIONS:
				return getIncomingConnections();
			case ModelPackage.NODE__OUTGOING_CONNECTIONS:
				return getOutgoingConnections();
			case ModelPackage.NODE__READ_ONLY:
				return isReadOnly() ? Boolean.TRUE : Boolean.FALSE;
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
		@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ModelPackage.NODE__LOCATION:
				setLocation((Point)newValue);
				return;
			case ModelPackage.NODE__WIDTH:
				setWidth(((Integer)newValue).intValue());
				return;
			case ModelPackage.NODE__HEIGHT:
				setHeight(((Integer)newValue).intValue());
				return;
			case ModelPackage.NODE__INCOMING_CONNECTIONS:
				getIncomingConnections().clear();
				getIncomingConnections().addAll((Collection<? extends Link>)newValue);
				return;
			case ModelPackage.NODE__OUTGOING_CONNECTIONS:
				getOutgoingConnections().clear();
				getOutgoingConnections().addAll((Collection<? extends Link>)newValue);
				return;
			case ModelPackage.NODE__READ_ONLY:
				setReadOnly(((Boolean)newValue).booleanValue());
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
			case ModelPackage.NODE__LOCATION:
				setLocation(LOCATION_EDEFAULT);
				return;
			case ModelPackage.NODE__WIDTH:
				setWidth(WIDTH_EDEFAULT);
				return;
			case ModelPackage.NODE__HEIGHT:
				setHeight(HEIGHT_EDEFAULT);
				return;
			case ModelPackage.NODE__INCOMING_CONNECTIONS:
				getIncomingConnections().clear();
				return;
			case ModelPackage.NODE__OUTGOING_CONNECTIONS:
				getOutgoingConnections().clear();
				return;
			case ModelPackage.NODE__READ_ONLY:
				setReadOnly(READ_ONLY_EDEFAULT);
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
			case ModelPackage.NODE__LOCATION:
				return LOCATION_EDEFAULT == null ? location != null : !LOCATION_EDEFAULT.equals(location);
			case ModelPackage.NODE__WIDTH:
				return width != WIDTH_EDEFAULT;
			case ModelPackage.NODE__HEIGHT:
				return height != HEIGHT_EDEFAULT;
			case ModelPackage.NODE__INCOMING_CONNECTIONS:
				return incomingConnections != null && !incomingConnections.isEmpty();
			case ModelPackage.NODE__OUTGOING_CONNECTIONS:
				return outgoingConnections != null && !outgoingConnections.isEmpty();
			case ModelPackage.NODE__READ_ONLY:
				return readOnly != READ_ONLY_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	private Collection consumers = new ArrayList();

	private GraphNode umaContainer;

	private org.eclipse.epf.uma.Diagram umaDiagram;

	private Diagram diagram;

	protected GraphNode graphNode;

	protected boolean notificationEnabled = true;

	protected INodeChangeListener methodElementAdapter;

	protected boolean visible = true;
	
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
	public WorkOrder addDefaultWorkOrder(NamedNodeImpl node,
			WorkBreakdownElement predBreakdownElement) {
		boolean notify = node.isNotificationEnabled();
		Map map = new HashMap();
		List list = ((WorkBreakdownElement) node.getObject()).eAdapters();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object obj = iterator.next();
			if (obj instanceof MethodElementAdapter) {
				NodeImpl nodeobj = (NodeImpl) ((MethodElementAdapter) obj)
						.getNode();
				boolean predNotification = node.notificationEnabled;
				((NodeImpl) (((MethodElementAdapter) obj).getNode())).notificationEnabled = false;
				map.put(nodeobj, new Boolean(predNotification));
			}
		}
		try {
			node.setNotificationEnabled(false);
			return UmaUtil.createDefaultWorkOrder((WorkBreakdownElement) node
					.getObject(), predBreakdownElement);
		} finally {
			node.setNotificationEnabled(notify);
			Set set = map.keySet();
			for (Iterator iter = set.iterator(); iter.hasNext();) {
				Object object = iter.next();
				Object obj = map.get(object);
				if (obj instanceof Boolean) {
					boolean prednot = ((Boolean) obj).booleanValue();
					((NodeImpl) object).notificationEnabled = prednot;
				}
			}
		}
	}
	
	protected boolean isGraphicalDataRequired() {
		DiagramImpl diagram = (DiagramImpl) getDiagram();
		return diagram.isGraphicalDataRequired();
	}
	
	public boolean isVisible() {
		return visible ;
	}

	public boolean isNotificationEnabled() {
		return notificationEnabled;
	}

	public void setNotificationEnabled(boolean notificationEnabled) {
		this.notificationEnabled = notificationEnabled;
	}
	
} // NodeImpl
