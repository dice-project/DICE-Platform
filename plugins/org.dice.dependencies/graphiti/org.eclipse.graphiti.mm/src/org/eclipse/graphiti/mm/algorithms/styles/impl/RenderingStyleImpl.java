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

import org.eclipse.graphiti.mm.algorithms.styles.AdaptedGradientColoredAreas;
import org.eclipse.graphiti.mm.algorithms.styles.RenderingStyle;
import org.eclipse.graphiti.mm.algorithms.styles.StylesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Rendering Style</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.styles.impl.RenderingStyleImpl#getAdaptedGradientColoredAreas <em>Adapted Gradient Colored Areas</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RenderingStyleImpl extends EObjectImpl implements RenderingStyle {
	/**
	 * The cached value of the '{@link #getAdaptedGradientColoredAreas() <em>Adapted Gradient Colored Areas</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAdaptedGradientColoredAreas()
	 * @generated
	 * @ordered
	 */
	protected AdaptedGradientColoredAreas adaptedGradientColoredAreas;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RenderingStyleImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StylesPackage.Literals.RENDERING_STYLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AdaptedGradientColoredAreas getAdaptedGradientColoredAreas() {
		if (adaptedGradientColoredAreas != null && adaptedGradientColoredAreas.eIsProxy()) {
			InternalEObject oldAdaptedGradientColoredAreas = (InternalEObject)adaptedGradientColoredAreas;
			adaptedGradientColoredAreas = (AdaptedGradientColoredAreas)eResolveProxy(oldAdaptedGradientColoredAreas);
			if (adaptedGradientColoredAreas != oldAdaptedGradientColoredAreas) {
				InternalEObject newAdaptedGradientColoredAreas = (InternalEObject)adaptedGradientColoredAreas;
				NotificationChain msgs = oldAdaptedGradientColoredAreas.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - StylesPackage.RENDERING_STYLE__ADAPTED_GRADIENT_COLORED_AREAS, null, null);
				if (newAdaptedGradientColoredAreas.eInternalContainer() == null) {
					msgs = newAdaptedGradientColoredAreas.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - StylesPackage.RENDERING_STYLE__ADAPTED_GRADIENT_COLORED_AREAS, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, StylesPackage.RENDERING_STYLE__ADAPTED_GRADIENT_COLORED_AREAS, oldAdaptedGradientColoredAreas, adaptedGradientColoredAreas));
			}
		}
		return adaptedGradientColoredAreas;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AdaptedGradientColoredAreas basicGetAdaptedGradientColoredAreas() {
		return adaptedGradientColoredAreas;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAdaptedGradientColoredAreas(AdaptedGradientColoredAreas newAdaptedGradientColoredAreas, NotificationChain msgs) {
		AdaptedGradientColoredAreas oldAdaptedGradientColoredAreas = adaptedGradientColoredAreas;
		adaptedGradientColoredAreas = newAdaptedGradientColoredAreas;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, StylesPackage.RENDERING_STYLE__ADAPTED_GRADIENT_COLORED_AREAS, oldAdaptedGradientColoredAreas, newAdaptedGradientColoredAreas);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAdaptedGradientColoredAreas(AdaptedGradientColoredAreas newAdaptedGradientColoredAreas) {
		if (newAdaptedGradientColoredAreas != adaptedGradientColoredAreas) {
			NotificationChain msgs = null;
			if (adaptedGradientColoredAreas != null)
				msgs = ((InternalEObject)adaptedGradientColoredAreas).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - StylesPackage.RENDERING_STYLE__ADAPTED_GRADIENT_COLORED_AREAS, null, msgs);
			if (newAdaptedGradientColoredAreas != null)
				msgs = ((InternalEObject)newAdaptedGradientColoredAreas).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - StylesPackage.RENDERING_STYLE__ADAPTED_GRADIENT_COLORED_AREAS, null, msgs);
			msgs = basicSetAdaptedGradientColoredAreas(newAdaptedGradientColoredAreas, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StylesPackage.RENDERING_STYLE__ADAPTED_GRADIENT_COLORED_AREAS, newAdaptedGradientColoredAreas, newAdaptedGradientColoredAreas));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case StylesPackage.RENDERING_STYLE__ADAPTED_GRADIENT_COLORED_AREAS:
				return basicSetAdaptedGradientColoredAreas(null, msgs);
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
			case StylesPackage.RENDERING_STYLE__ADAPTED_GRADIENT_COLORED_AREAS:
				if (resolve) return getAdaptedGradientColoredAreas();
				return basicGetAdaptedGradientColoredAreas();
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
			case StylesPackage.RENDERING_STYLE__ADAPTED_GRADIENT_COLORED_AREAS:
				setAdaptedGradientColoredAreas((AdaptedGradientColoredAreas)newValue);
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
			case StylesPackage.RENDERING_STYLE__ADAPTED_GRADIENT_COLORED_AREAS:
				setAdaptedGradientColoredAreas((AdaptedGradientColoredAreas)null);
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
			case StylesPackage.RENDERING_STYLE__ADAPTED_GRADIENT_COLORED_AREAS:
				return adaptedGradientColoredAreas != null;
		}
		return super.eIsSet(featureID);
	}

} //RenderingStyleImpl
