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
package org.eclipse.graphiti.mm.algorithms.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.graphiti.mm.algorithms.AlgorithmsPackage;
import org.eclipse.graphiti.mm.algorithms.RoundedRectangle;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Rounded Rectangle</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.impl.RoundedRectangleImpl#getCornerHeight <em>Corner Height</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.impl.RoundedRectangleImpl#getCornerWidth <em>Corner Width</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RoundedRectangleImpl extends GraphicsAlgorithmImpl implements RoundedRectangle {
	/**
	 * The default value of the '{@link #getCornerHeight() <em>Corner Height</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCornerHeight()
	 * @generated
	 * @ordered
	 */
	protected static final int CORNER_HEIGHT_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getCornerHeight() <em>Corner Height</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCornerHeight()
	 * @generated
	 * @ordered
	 */
	protected int cornerHeight = CORNER_HEIGHT_EDEFAULT;

	/**
	 * The default value of the '{@link #getCornerWidth() <em>Corner Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCornerWidth()
	 * @generated
	 * @ordered
	 */
	protected static final int CORNER_WIDTH_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getCornerWidth() <em>Corner Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCornerWidth()
	 * @generated
	 * @ordered
	 */
	protected int cornerWidth = CORNER_WIDTH_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RoundedRectangleImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AlgorithmsPackage.Literals.ROUNDED_RECTANGLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getCornerHeight() {
		return cornerHeight;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCornerHeight(int newCornerHeight) {
		int oldCornerHeight = cornerHeight;
		cornerHeight = newCornerHeight;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AlgorithmsPackage.ROUNDED_RECTANGLE__CORNER_HEIGHT, oldCornerHeight, cornerHeight));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getCornerWidth() {
		return cornerWidth;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCornerWidth(int newCornerWidth) {
		int oldCornerWidth = cornerWidth;
		cornerWidth = newCornerWidth;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AlgorithmsPackage.ROUNDED_RECTANGLE__CORNER_WIDTH, oldCornerWidth, cornerWidth));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case AlgorithmsPackage.ROUNDED_RECTANGLE__CORNER_HEIGHT:
				return getCornerHeight();
			case AlgorithmsPackage.ROUNDED_RECTANGLE__CORNER_WIDTH:
				return getCornerWidth();
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
			case AlgorithmsPackage.ROUNDED_RECTANGLE__CORNER_HEIGHT:
				setCornerHeight((Integer)newValue);
				return;
			case AlgorithmsPackage.ROUNDED_RECTANGLE__CORNER_WIDTH:
				setCornerWidth((Integer)newValue);
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
			case AlgorithmsPackage.ROUNDED_RECTANGLE__CORNER_HEIGHT:
				setCornerHeight(CORNER_HEIGHT_EDEFAULT);
				return;
			case AlgorithmsPackage.ROUNDED_RECTANGLE__CORNER_WIDTH:
				setCornerWidth(CORNER_WIDTH_EDEFAULT);
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
			case AlgorithmsPackage.ROUNDED_RECTANGLE__CORNER_HEIGHT:
				return cornerHeight != CORNER_HEIGHT_EDEFAULT;
			case AlgorithmsPackage.ROUNDED_RECTANGLE__CORNER_WIDTH:
				return cornerWidth != CORNER_WIDTH_EDEFAULT;
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
		result.append(" (cornerHeight: ");
		result.append(cornerHeight);
		result.append(", cornerWidth: ");
		result.append(cornerWidth);
		result.append(')');
		return result.toString();
	}

} //RoundedRectangleImpl
