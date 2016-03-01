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
package org.eclipse.graphiti.mm.algorithms.styles.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.graphiti.mm.algorithms.styles.Color;
import org.eclipse.graphiti.mm.algorithms.styles.GradientColoredLocation;
import org.eclipse.graphiti.mm.algorithms.styles.LocationType;
import org.eclipse.graphiti.mm.algorithms.styles.StylesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Gradient Colored Location</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.styles.impl.GradientColoredLocationImpl#getLocationType <em>Location Type</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.styles.impl.GradientColoredLocationImpl#getLocationValue <em>Location Value</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.styles.impl.GradientColoredLocationImpl#getColor <em>Color</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GradientColoredLocationImpl extends EObjectImpl implements GradientColoredLocation {
	/**
	 * The default value of the '{@link #getLocationType() <em>Location Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocationType()
	 * @generated
	 * @ordered
	 */
	protected static final LocationType LOCATION_TYPE_EDEFAULT = LocationType.LOCATION_TYPE_RELATIVE;

	/**
	 * The cached value of the '{@link #getLocationType() <em>Location Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocationType()
	 * @generated
	 * @ordered
	 */
	protected LocationType locationType = LOCATION_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getLocationValue() <em>Location Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocationValue()
	 * @generated
	 * @ordered
	 */
	protected static final Integer LOCATION_VALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLocationValue() <em>Location Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocationValue()
	 * @generated
	 * @ordered
	 */
	protected Integer locationValue = LOCATION_VALUE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getColor() <em>Color</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColor()
	 * @generated
	 * @ordered
	 */
	protected Color color;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GradientColoredLocationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StylesPackage.Literals.GRADIENT_COLORED_LOCATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LocationType getLocationType() {
		return locationType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLocationType(LocationType newLocationType) {
		LocationType oldLocationType = locationType;
		locationType = newLocationType == null ? LOCATION_TYPE_EDEFAULT : newLocationType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StylesPackage.GRADIENT_COLORED_LOCATION__LOCATION_TYPE, oldLocationType, locationType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer getLocationValue() {
		return locationValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLocationValue(Integer newLocationValue) {
		Integer oldLocationValue = locationValue;
		locationValue = newLocationValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StylesPackage.GRADIENT_COLORED_LOCATION__LOCATION_VALUE, oldLocationValue, locationValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Color getColor() {
		if (color != null && color.eIsProxy()) {
			InternalEObject oldColor = (InternalEObject)color;
			color = (Color)eResolveProxy(oldColor);
			if (color != oldColor) {
				InternalEObject newColor = (InternalEObject)color;
				NotificationChain msgs = oldColor.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - StylesPackage.GRADIENT_COLORED_LOCATION__COLOR, null, null);
				if (newColor.eInternalContainer() == null) {
					msgs = newColor.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - StylesPackage.GRADIENT_COLORED_LOCATION__COLOR, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, StylesPackage.GRADIENT_COLORED_LOCATION__COLOR, oldColor, color));
			}
		}
		return color;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Color basicGetColor() {
		return color;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetColor(Color newColor, NotificationChain msgs) {
		Color oldColor = color;
		color = newColor;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, StylesPackage.GRADIENT_COLORED_LOCATION__COLOR, oldColor, newColor);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setColor(Color newColor) {
		if (newColor != color) {
			NotificationChain msgs = null;
			if (color != null)
				msgs = ((InternalEObject)color).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - StylesPackage.GRADIENT_COLORED_LOCATION__COLOR, null, msgs);
			if (newColor != null)
				msgs = ((InternalEObject)newColor).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - StylesPackage.GRADIENT_COLORED_LOCATION__COLOR, null, msgs);
			msgs = basicSetColor(newColor, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StylesPackage.GRADIENT_COLORED_LOCATION__COLOR, newColor, newColor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case StylesPackage.GRADIENT_COLORED_LOCATION__COLOR:
				return basicSetColor(null, msgs);
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
			case StylesPackage.GRADIENT_COLORED_LOCATION__LOCATION_TYPE:
				return getLocationType();
			case StylesPackage.GRADIENT_COLORED_LOCATION__LOCATION_VALUE:
				return getLocationValue();
			case StylesPackage.GRADIENT_COLORED_LOCATION__COLOR:
				if (resolve) return getColor();
				return basicGetColor();
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
			case StylesPackage.GRADIENT_COLORED_LOCATION__LOCATION_TYPE:
				setLocationType((LocationType)newValue);
				return;
			case StylesPackage.GRADIENT_COLORED_LOCATION__LOCATION_VALUE:
				setLocationValue((Integer)newValue);
				return;
			case StylesPackage.GRADIENT_COLORED_LOCATION__COLOR:
				setColor((Color)newValue);
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
			case StylesPackage.GRADIENT_COLORED_LOCATION__LOCATION_TYPE:
				setLocationType(LOCATION_TYPE_EDEFAULT);
				return;
			case StylesPackage.GRADIENT_COLORED_LOCATION__LOCATION_VALUE:
				setLocationValue(LOCATION_VALUE_EDEFAULT);
				return;
			case StylesPackage.GRADIENT_COLORED_LOCATION__COLOR:
				setColor((Color)null);
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
			case StylesPackage.GRADIENT_COLORED_LOCATION__LOCATION_TYPE:
				return locationType != LOCATION_TYPE_EDEFAULT;
			case StylesPackage.GRADIENT_COLORED_LOCATION__LOCATION_VALUE:
				return LOCATION_VALUE_EDEFAULT == null ? locationValue != null : !LOCATION_VALUE_EDEFAULT.equals(locationValue);
			case StylesPackage.GRADIENT_COLORED_LOCATION__COLOR:
				return color != null;
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
		result.append(" (locationType: ");
		result.append(locationType);
		result.append(", locationValue: ");
		result.append(locationValue);
		result.append(')');
		return result.toString();
	}

} //GradientColoredLocationImpl
