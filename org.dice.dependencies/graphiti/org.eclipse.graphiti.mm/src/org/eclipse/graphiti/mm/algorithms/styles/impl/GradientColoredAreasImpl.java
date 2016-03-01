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

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.graphiti.mm.algorithms.styles.GradientColoredArea;
import org.eclipse.graphiti.mm.algorithms.styles.GradientColoredAreas;
import org.eclipse.graphiti.mm.algorithms.styles.StylesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Gradient Colored Areas</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.styles.impl.GradientColoredAreasImpl#getGradientColor <em>Gradient Color</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.styles.impl.GradientColoredAreasImpl#getStyleAdaption <em>Style Adaption</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GradientColoredAreasImpl extends EObjectImpl implements GradientColoredAreas {
	/**
	 * The cached value of the '{@link #getGradientColor() <em>Gradient Color</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGradientColor()
	 * @generated
	 * @ordered
	 */
	protected EList<GradientColoredArea> gradientColor;

	/**
	 * The default value of the '{@link #getStyleAdaption() <em>Style Adaption</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStyleAdaption()
	 * @generated
	 * @ordered
	 */
	protected static final Integer STYLE_ADAPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStyleAdaption() <em>Style Adaption</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStyleAdaption()
	 * @generated
	 * @ordered
	 */
	protected Integer styleAdaption = STYLE_ADAPTION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GradientColoredAreasImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StylesPackage.Literals.GRADIENT_COLORED_AREAS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<GradientColoredArea> getGradientColor() {
		if (gradientColor == null) {
			gradientColor = new EObjectContainmentEList.Resolving<GradientColoredArea>(GradientColoredArea.class, this, StylesPackage.GRADIENT_COLORED_AREAS__GRADIENT_COLOR);
		}
		return gradientColor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer getStyleAdaption() {
		return styleAdaption;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStyleAdaption(Integer newStyleAdaption) {
		Integer oldStyleAdaption = styleAdaption;
		styleAdaption = newStyleAdaption;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StylesPackage.GRADIENT_COLORED_AREAS__STYLE_ADAPTION, oldStyleAdaption, styleAdaption));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case StylesPackage.GRADIENT_COLORED_AREAS__GRADIENT_COLOR:
				return ((InternalEList<?>)getGradientColor()).basicRemove(otherEnd, msgs);
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
			case StylesPackage.GRADIENT_COLORED_AREAS__GRADIENT_COLOR:
				return getGradientColor();
			case StylesPackage.GRADIENT_COLORED_AREAS__STYLE_ADAPTION:
				return getStyleAdaption();
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
			case StylesPackage.GRADIENT_COLORED_AREAS__GRADIENT_COLOR:
				getGradientColor().clear();
				getGradientColor().addAll((Collection<? extends GradientColoredArea>)newValue);
				return;
			case StylesPackage.GRADIENT_COLORED_AREAS__STYLE_ADAPTION:
				setStyleAdaption((Integer)newValue);
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
			case StylesPackage.GRADIENT_COLORED_AREAS__GRADIENT_COLOR:
				getGradientColor().clear();
				return;
			case StylesPackage.GRADIENT_COLORED_AREAS__STYLE_ADAPTION:
				setStyleAdaption(STYLE_ADAPTION_EDEFAULT);
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
			case StylesPackage.GRADIENT_COLORED_AREAS__GRADIENT_COLOR:
				return gradientColor != null && !gradientColor.isEmpty();
			case StylesPackage.GRADIENT_COLORED_AREAS__STYLE_ADAPTION:
				return STYLE_ADAPTION_EDEFAULT == null ? styleAdaption != null : !STYLE_ADAPTION_EDEFAULT.equals(styleAdaption);
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
		result.append(" (styleAdaption: ");
		result.append(styleAdaption);
		result.append(')');
		return result.toString();
	}

} //GradientColoredAreasImpl
