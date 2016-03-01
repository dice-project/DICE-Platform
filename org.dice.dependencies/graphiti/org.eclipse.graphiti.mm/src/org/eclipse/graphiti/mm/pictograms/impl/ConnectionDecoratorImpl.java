/**
 * <copyright>
 * 
 * Copyright (c) 2005, 2010 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 * 
 * </copyright>
 */
package org.eclipse.graphiti.mm.pictograms.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.PictogramsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Connection Decorator</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.impl.ConnectionDecoratorImpl#isLocationRelative <em>Location Relative</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.impl.ConnectionDecoratorImpl#getLocation <em>Location</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.impl.ConnectionDecoratorImpl#getConnection <em>Connection</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConnectionDecoratorImpl extends ShapeImpl implements ConnectionDecorator {
	/**
	 * The default value of the '{@link #isLocationRelative() <em>Location Relative</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLocationRelative()
	 * @generated
	 * @ordered
	 */
	protected static final boolean LOCATION_RELATIVE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isLocationRelative() <em>Location Relative</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLocationRelative()
	 * @generated
	 * @ordered
	 */
	protected boolean locationRelative = LOCATION_RELATIVE_EDEFAULT;

	/**
	 * The default value of the '{@link #getLocation() <em>Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocation()
	 * @generated
	 * @ordered
	 */
	protected static final double LOCATION_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getLocation() <em>Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocation()
	 * @generated
	 * @ordered
	 */
	protected double location = LOCATION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConnectionDecoratorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PictogramsPackage.Literals.CONNECTION_DECORATOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isLocationRelative() {
		return locationRelative;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLocationRelative(boolean newLocationRelative) {
		boolean oldLocationRelative = locationRelative;
		locationRelative = newLocationRelative;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PictogramsPackage.CONNECTION_DECORATOR__LOCATION_RELATIVE, oldLocationRelative, locationRelative));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getLocation() {
		return location;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLocation(double newLocation) {
		double oldLocation = location;
		location = newLocation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PictogramsPackage.CONNECTION_DECORATOR__LOCATION, oldLocation, location));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Connection getConnection() {
		if (eContainerFeatureID() != PictogramsPackage.CONNECTION_DECORATOR__CONNECTION) return null;
		return (Connection)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Connection basicGetConnection() {
		if (eContainerFeatureID() != PictogramsPackage.CONNECTION_DECORATOR__CONNECTION) return null;
		return (Connection)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetConnection(Connection newConnection, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newConnection, PictogramsPackage.CONNECTION_DECORATOR__CONNECTION, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConnection(Connection newConnection) {
		if (newConnection != eInternalContainer() || (eContainerFeatureID() != PictogramsPackage.CONNECTION_DECORATOR__CONNECTION && newConnection != null)) {
			if (EcoreUtil.isAncestor(this, newConnection))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newConnection != null)
				msgs = ((InternalEObject)newConnection).eInverseAdd(this, PictogramsPackage.CONNECTION__CONNECTION_DECORATORS, Connection.class, msgs);
			msgs = basicSetConnection(newConnection, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PictogramsPackage.CONNECTION_DECORATOR__CONNECTION, newConnection, newConnection));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PictogramsPackage.CONNECTION_DECORATOR__CONNECTION:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetConnection((Connection)otherEnd, msgs);
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
			case PictogramsPackage.CONNECTION_DECORATOR__CONNECTION:
				return basicSetConnection(null, msgs);
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
		switch (eContainerFeatureID()) {
			case PictogramsPackage.CONNECTION_DECORATOR__CONNECTION:
				return eInternalContainer().eInverseRemove(this, PictogramsPackage.CONNECTION__CONNECTION_DECORATORS, Connection.class, msgs);
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
			case PictogramsPackage.CONNECTION_DECORATOR__LOCATION_RELATIVE:
				return isLocationRelative();
			case PictogramsPackage.CONNECTION_DECORATOR__LOCATION:
				return getLocation();
			case PictogramsPackage.CONNECTION_DECORATOR__CONNECTION:
				if (resolve) return getConnection();
				return basicGetConnection();
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
			case PictogramsPackage.CONNECTION_DECORATOR__LOCATION_RELATIVE:
				setLocationRelative((Boolean)newValue);
				return;
			case PictogramsPackage.CONNECTION_DECORATOR__LOCATION:
				setLocation((Double)newValue);
				return;
			case PictogramsPackage.CONNECTION_DECORATOR__CONNECTION:
				setConnection((Connection)newValue);
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
			case PictogramsPackage.CONNECTION_DECORATOR__LOCATION_RELATIVE:
				setLocationRelative(LOCATION_RELATIVE_EDEFAULT);
				return;
			case PictogramsPackage.CONNECTION_DECORATOR__LOCATION:
				setLocation(LOCATION_EDEFAULT);
				return;
			case PictogramsPackage.CONNECTION_DECORATOR__CONNECTION:
				setConnection((Connection)null);
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
			case PictogramsPackage.CONNECTION_DECORATOR__LOCATION_RELATIVE:
				return locationRelative != LOCATION_RELATIVE_EDEFAULT;
			case PictogramsPackage.CONNECTION_DECORATOR__LOCATION:
				return location != LOCATION_EDEFAULT;
			case PictogramsPackage.CONNECTION_DECORATOR__CONNECTION:
				return basicGetConnection() != null;
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
		result.append(" (locationRelative: ");
		result.append(locationRelative);
		result.append(", location: ");
		result.append(location);
		result.append(')');
		return result.toString();
	}

} //ConnectionDecoratorImpl
