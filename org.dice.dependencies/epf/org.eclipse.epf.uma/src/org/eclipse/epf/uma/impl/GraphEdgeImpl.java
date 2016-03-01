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
package org.eclipse.epf.uma.impl;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.epf.uma.GraphConnector;
import org.eclipse.epf.uma.GraphEdge;
import org.eclipse.epf.uma.Point;
import org.eclipse.epf.uma.UmaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Graph Edge</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.impl.GraphEdgeImpl#getWaypoints <em>Waypoints</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.GraphEdgeImpl#getAnchor <em>Anchor</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GraphEdgeImpl extends GraphElementImpl implements GraphEdge {
	/**
	 * The cached value of the '{@link #getWaypoints() <em>Waypoints</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWaypoints()
	 * @generated
	 * @ordered
	 */
	protected EList<Point> waypoints;

	/**
	 * The cached value of the '{@link #getAnchor() <em>Anchor</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAnchor()
	 * @generated
	 * @ordered
	 */
	protected EList<GraphConnector> anchor;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GraphEdgeImpl() {
		super();

		//UMA-->
		reassignDefaultValues();
		//UMA<--  
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UmaPackage.Literals.GRAPH_EDGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<GraphConnector> getAnchor() {
		if (anchor == null) {
			anchor = new EObjectWithInverseResolvingEList.ManyInverse<GraphConnector>(
					GraphConnector.class, this, UmaPackage.GRAPH_EDGE__ANCHOR,
					UmaPackage.GRAPH_CONNECTOR__GRAPH_EDGE);
		}
		return anchor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Point> getWaypoints() {
		if (waypoints == null) {
			waypoints = new EObjectContainmentEList.Resolving<Point>(
					Point.class, this, UmaPackage.GRAPH_EDGE__WAYPOINTS);
		}
		return waypoints;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
		case UmaPackage.GRAPH_EDGE__ANCHOR:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getAnchor())
					.basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
		case UmaPackage.GRAPH_EDGE__WAYPOINTS:
			return ((InternalEList<?>) getWaypoints()).basicRemove(otherEnd,
					msgs);
		case UmaPackage.GRAPH_EDGE__ANCHOR:
			return ((InternalEList<?>) getAnchor()).basicRemove(otherEnd, msgs);
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
		case UmaPackage.GRAPH_EDGE__WAYPOINTS:
			return getWaypoints();
		case UmaPackage.GRAPH_EDGE__ANCHOR:
			return getAnchor();
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
		case UmaPackage.GRAPH_EDGE__WAYPOINTS:
			getWaypoints().clear();
			getWaypoints().addAll((Collection<? extends Point>) newValue);
			return;
		case UmaPackage.GRAPH_EDGE__ANCHOR:
			getAnchor().clear();
			getAnchor().addAll((Collection<? extends GraphConnector>) newValue);
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
		case UmaPackage.GRAPH_EDGE__WAYPOINTS:
			getWaypoints().clear();
			return;
		case UmaPackage.GRAPH_EDGE__ANCHOR:
			getAnchor().clear();
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
		//UMA-->
		EStructuralFeature feature = getFeatureWithOverridenDefaultValue(featureID);
		if (feature != null) {
			return isFeatureWithOverridenDefaultValueSet(feature);
		}
		//UMA<--		
		switch (featureID) {
		case UmaPackage.GRAPH_EDGE__WAYPOINTS:
			return waypoints != null && !waypoints.isEmpty();
		case UmaPackage.GRAPH_EDGE__ANCHOR:
			return anchor != null && !anchor.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //GraphEdgeImpl
