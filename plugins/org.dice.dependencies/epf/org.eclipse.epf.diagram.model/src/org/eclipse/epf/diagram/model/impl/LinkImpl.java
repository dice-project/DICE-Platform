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
import java.util.Iterator;

import org.eclipse.draw2d.AbsoluteBendpoint;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.epf.diagram.model.Link;
import org.eclipse.epf.diagram.model.ModelPackage;
import org.eclipse.epf.diagram.model.Node;
import org.eclipse.epf.diagram.model.util.GraphicalDataHelper;
import org.eclipse.epf.uma.GraphConnector;
import org.eclipse.epf.uma.GraphEdge;


/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Link</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.diagram.model.impl.LinkImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.epf.diagram.model.impl.LinkImpl#getSource <em>Source</em>}</li>
 *   <li>{@link org.eclipse.epf.diagram.model.impl.LinkImpl#getTarget <em>Target</em>}</li>
 *   <li>{@link org.eclipse.epf.diagram.model.impl.LinkImpl#getBendpoints <em>Bendpoints</em>}</li>
 *   <li>{@link org.eclipse.epf.diagram.model.impl.LinkImpl#getSourceEndPoint <em>Source End Point</em>}</li>
 *   <li>{@link org.eclipse.epf.diagram.model.impl.LinkImpl#getTargetEndPoint <em>Target End Point</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LinkImpl extends LinkedObjectImpl implements Link {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = ""; //$NON-NLS-1$

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getTarget()
	 * @generated
	 * @ordered
	 */
	protected Node target;

	/**
	 * The cached value of the '{@link #getBendpoints() <em>Bendpoints</em>}' attribute list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getBendpoints()
	 * @generated
	 * @ordered
	 */
	protected EList<AbsoluteBendpoint> bendpoints;

	/**
	 * The default value of the '{@link #getSourceEndPoint() <em>Source End Point</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getSourceEndPoint()
	 * @generated
	 * @ordered
	 */
	protected static final Point SOURCE_END_POINT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSourceEndPoint() <em>Source End Point</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getSourceEndPoint()
	 * @generated
	 * @ordered
	 */
	protected Point sourceEndPoint = SOURCE_END_POINT_EDEFAULT;

	/**
	 * The default value of the '{@link #getTargetEndPoint() <em>Target End Point</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getTargetEndPoint()
	 * @generated
	 * @ordered
	 */
	protected static final Point TARGET_END_POINT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTargetEndPoint() <em>Target End Point</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getTargetEndPoint()
	 * @generated
	 * @ordered
	 */
	protected Point targetEndPoint = TARGET_END_POINT_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected LinkImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.LINK;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.LINK__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Node getSource() {
		if (eContainerFeatureID != ModelPackage.LINK__SOURCE) return null;
		return (Node)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSource(Node newSource, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newSource, ModelPackage.LINK__SOURCE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setSource(Node newSource) {
		if (newSource != eInternalContainer() || (eContainerFeatureID != ModelPackage.LINK__SOURCE && newSource != null)) {
			if (EcoreUtil.isAncestor(this, newSource))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newSource != null)
				msgs = ((InternalEObject)newSource).eInverseAdd(this, ModelPackage.NODE__OUTGOING_CONNECTIONS, Node.class, msgs);
			msgs = basicSetSource(newSource, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.LINK__SOURCE, newSource, newSource));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Node getTarget() {
		if (target != null && target.eIsProxy()) {
			InternalEObject oldTarget = (InternalEObject)target;
			target = (Node)eResolveProxy(oldTarget);
			if (target != oldTarget) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelPackage.LINK__TARGET, oldTarget, target));
			}
		}
		return target;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Node basicGetTarget() {
		return target;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTarget(Node newTarget, NotificationChain msgs) {
		Node oldTarget = target;
		target = newTarget;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.LINK__TARGET, oldTarget, newTarget);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setTarget(Node newTarget) {
		if (newTarget != target) {
			NotificationChain msgs = null;
			if (target != null)
				msgs = ((InternalEObject)target).eInverseRemove(this, ModelPackage.NODE__INCOMING_CONNECTIONS, Node.class, msgs);
			if (newTarget != null)
				msgs = ((InternalEObject)newTarget).eInverseAdd(this, ModelPackage.NODE__INCOMING_CONNECTIONS, Node.class, msgs);
			msgs = basicSetTarget(newTarget, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.LINK__TARGET, newTarget, newTarget));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AbsoluteBendpoint> getBendpoints() {
		if (bendpoints == null) {
			bendpoints = new EDataTypeUniqueEList<AbsoluteBendpoint>(AbsoluteBendpoint.class, this, ModelPackage.LINK__BENDPOINTS);
		}
		return bendpoints;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Point getSourceEndPoint() {
		return sourceEndPoint;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setSourceEndPoint(Point newSourceEndPoint) {
		Point oldSourceEndPoint = sourceEndPoint;
		sourceEndPoint = newSourceEndPoint;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.LINK__SOURCE_END_POINT, oldSourceEndPoint, sourceEndPoint));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Point getTargetEndPoint() {
		return targetEndPoint;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setTargetEndPoint(Point newTargetEndPoint) {
		Point oldTargetEndPoint = targetEndPoint;
		targetEndPoint = newTargetEndPoint;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.LINK__TARGET_END_POINT, oldTargetEndPoint, targetEndPoint));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.LINK__SOURCE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetSource((Node)otherEnd, msgs);
			case ModelPackage.LINK__TARGET:
				if (target != null)
					msgs = ((InternalEObject)target).eInverseRemove(this, ModelPackage.NODE__INCOMING_CONNECTIONS, Node.class, msgs);
				return basicSetTarget((Node)otherEnd, msgs);
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
			case ModelPackage.LINK__SOURCE:
				return basicSetSource(null, msgs);
			case ModelPackage.LINK__TARGET:
				return basicSetTarget(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID) {
			case ModelPackage.LINK__SOURCE:
				return eInternalContainer().eInverseRemove(this, ModelPackage.NODE__OUTGOING_CONNECTIONS, Node.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelPackage.LINK__NAME:
				return getName();
			case ModelPackage.LINK__SOURCE:
				return getSource();
			case ModelPackage.LINK__TARGET:
				if (resolve) return getTarget();
				return basicGetTarget();
			case ModelPackage.LINK__BENDPOINTS:
				return getBendpoints();
			case ModelPackage.LINK__SOURCE_END_POINT:
				return getSourceEndPoint();
			case ModelPackage.LINK__TARGET_END_POINT:
				return getTargetEndPoint();
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
			case ModelPackage.LINK__NAME:
				setName((String)newValue);
				return;
			case ModelPackage.LINK__SOURCE:
				setSource((Node)newValue);
				return;
			case ModelPackage.LINK__TARGET:
				setTarget((Node)newValue);
				return;
			case ModelPackage.LINK__BENDPOINTS:
				getBendpoints().clear();
				getBendpoints().addAll((Collection<? extends AbsoluteBendpoint>)newValue);
				return;
			case ModelPackage.LINK__SOURCE_END_POINT:
				setSourceEndPoint((Point)newValue);
				return;
			case ModelPackage.LINK__TARGET_END_POINT:
				setTargetEndPoint((Point)newValue);
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
			case ModelPackage.LINK__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ModelPackage.LINK__SOURCE:
				setSource((Node)null);
				return;
			case ModelPackage.LINK__TARGET:
				setTarget((Node)null);
				return;
			case ModelPackage.LINK__BENDPOINTS:
				getBendpoints().clear();
				return;
			case ModelPackage.LINK__SOURCE_END_POINT:
				setSourceEndPoint(SOURCE_END_POINT_EDEFAULT);
				return;
			case ModelPackage.LINK__TARGET_END_POINT:
				setTargetEndPoint(TARGET_END_POINT_EDEFAULT);
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
			case ModelPackage.LINK__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ModelPackage.LINK__SOURCE:
				return getSource() != null;
			case ModelPackage.LINK__TARGET:
				return target != null;
			case ModelPackage.LINK__BENDPOINTS:
				return bendpoints != null && !bendpoints.isEmpty();
			case ModelPackage.LINK__SOURCE_END_POINT:
				return SOURCE_END_POINT_EDEFAULT == null ? sourceEndPoint != null : !SOURCE_END_POINT_EDEFAULT.equals(sourceEndPoint);
			case ModelPackage.LINK__TARGET_END_POINT:
				return TARGET_END_POINT_EDEFAULT == null ? targetEndPoint != null : !TARGET_END_POINT_EDEFAULT.equals(targetEndPoint);
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
		result.append(" (name: "); //$NON-NLS-1$
		result.append(name);
		result.append(", bendpoints: "); //$NON-NLS-1$
		result.append(bendpoints);
		result.append(", sourceEndPoint: "); //$NON-NLS-1$
		result.append(sourceEndPoint);
		result.append(", targetEndPoint: "); //$NON-NLS-1$
		result.append(targetEndPoint);
		result.append(')');
		return result.toString();
	}

	public void setObject(Object newObject) {
		super.setObject(newObject);

		if (newObject == null)
			return;

		GraphEdge edge = (GraphEdge) newObject;

		// copy bendpoints from edge
		//
		for (Iterator iter = edge.getWaypoints().iterator(); iter.hasNext();) {
			getBendpoints().add(
					new AbsoluteBendpoint(GraphicalDataHelper
							.toPoint((org.eclipse.epf.uma.Point) iter.next())));
		}

		// copy end points from GraphConnectors
		//
		if (getSourceGraphConnector().getPosition() != null) {
			sourceEndPoint = GraphicalDataHelper
					.toPoint(getSourceGraphConnector().getPosition());
		}
		if (getTargetGraphConnector().getPosition() != null) {
			targetEndPoint = GraphicalDataHelper
					.toPoint(getTargetGraphConnector().getPosition());
		}
				
		eAdapters().add(new AdapterImpl() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.emf.common.notify.impl.AdapterImpl#notifyChanged(org.eclipse.emf.common.notify.Notification)
			 */
			public void notifyChanged(Notification msg) {
				Point p;
				GraphEdge edge = (GraphEdge) getObject();
				switch (msg.getFeatureID(Link.class)) {
				case ModelPackage.LINK__BENDPOINTS:
					switch (msg.getEventType()) {
					case Notification.ADD:
						p = (AbsoluteBendpoint) msg.getNewValue();
						int index = msg.getPosition();
						org.eclipse.epf.uma.Point umaPoint = GraphicalDataHelper
								.newModelPoint(p.x, p.y);
						if (index == Notification.NO_INDEX) {
							edge.getWaypoints().add(umaPoint);
						} else {
							edge.getWaypoints().add(index, umaPoint);
						}
						return;
					case Notification.REMOVE:
						// p = (AbsoluteBendpoint) msg.getOldValue();
						// edge.getWaypoints().remove(GraphicalDataHelper.newModelPoint(p.x,
						// p.y));

						index = msg.getPosition();
						if (index == Notification.NO_INDEX) {
							edge.getWaypoints().remove(msg.getPosition());
						} else {
							edge.getWaypoints().remove(index);
						}

						return;
					case Notification.ADD_MANY:
						Collection collection = (Collection) msg.getNewValue();
						ArrayList umaPoints = new ArrayList();
						for (Iterator iter = collection.iterator(); iter
								.hasNext();) {
							p = (AbsoluteBendpoint) iter.next();
							umaPoints.add(GraphicalDataHelper.newModelPoint(
									p.x, p.y));
						}
						index = msg.getPosition();
						if (index == Notification.NO_INDEX) {
							edge.getWaypoints().addAll(umaPoints);
						} else {
							edge.getWaypoints().addAll(index, umaPoints);
						}
						return;
					// case Notification.REMOVE_MANY:
					// collection = (Collection) msg.getOldValue();
					// for (Iterator iter = collection.iterator(); iter
					// .hasNext();) {
					// p = (AbsoluteBendpoint) msg.getOldValue();
					// edge.getWaypoints().remove(GraphicalDataHelper.newModelPoint(p.x,
					// p.y));
					// }
					// return;
					case Notification.SET:
						p = (AbsoluteBendpoint) msg.getNewValue();
//						Point oldp = (AbsoluteBendpoint) msg.getOldValue();
						int pointIndex = msg.getPosition();
						org.eclipse.epf.uma.Point newUmaPoint = GraphicalDataHelper
								.newModelPoint(p.x, p.y);
//						org.eclipse.epf.uma.Point oldUmaPoint = GraphicalDataHelper
//								.newModelPoint(oldp.x, oldp.y);
						if (pointIndex == Notification.NO_INDEX) {
							System.out
									.println("Index for bendpoint is -1, Cannot set the bend point location."); //$NON-NLS-1$
						} else {
							edge.getWaypoints().remove(pointIndex);
							edge.getWaypoints().add(pointIndex, newUmaPoint);
						}
						return;
					}
					return;
				case ModelPackage.LINK__SOURCE_END_POINT:
					p = (Point) msg.getNewValue();
					// System.out.println(".notifyChanged(): new source end
					// point: " + p + ". " + getSource());
					if (p != null) {
						// translate to relative position
						//
						// p.translate(getSource().getLocation().getNegated());
						getSourceGraphConnector().setPosition(
								GraphicalDataHelper.newModelPoint(p.x, p.y));
					}
					return;
				case ModelPackage.LINK__TARGET_END_POINT:
					p = (Point) msg.getNewValue();
					// System.out.println(".notifyChanged(): new target end
					// point: " + p + ". " + getTarget());
					if (p != null) {
						// translate to relative position
						//
						// p.translate(LinkImpl.this.getTarget().getLocation().getNegated());
						getTargetGraphConnector().setPosition(
								GraphicalDataHelper.newModelPoint(p.x, p.y));
					}
					return;
				case ModelPackage.LINK__SOURCE:
					// TODO
					return;
				case ModelPackage.LINK__TARGET:
					// TODO
					return;
				}
			}
		});
	}

	private GraphConnector getSourceGraphConnector() {
		return (GraphConnector) ((GraphEdge) getObject()).getAnchor().get(0);
	}

	private GraphConnector getTargetGraphConnector() {
		return (GraphConnector) ((GraphEdge) getObject()).getAnchor().get(1);
	}

} // LinkImpl
