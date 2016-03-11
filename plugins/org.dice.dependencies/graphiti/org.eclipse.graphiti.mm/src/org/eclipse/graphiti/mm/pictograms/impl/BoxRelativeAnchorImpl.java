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

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor;
import org.eclipse.graphiti.mm.pictograms.PictogramsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Box Relative Anchor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.impl.BoxRelativeAnchorImpl#getRelativeWidth <em>Relative Width</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.impl.BoxRelativeAnchorImpl#getRelativeHeight <em>Relative Height</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BoxRelativeAnchorImpl extends AnchorImpl implements BoxRelativeAnchor {
	/**
	 * The default value of the '{@link #getRelativeWidth() <em>Relative Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRelativeWidth()
	 * @generated
	 * @ordered
	 */
	protected static final double RELATIVE_WIDTH_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getRelativeWidth() <em>Relative Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRelativeWidth()
	 * @generated
	 * @ordered
	 */
	protected double relativeWidth = RELATIVE_WIDTH_EDEFAULT;

	/**
	 * The default value of the '{@link #getRelativeHeight() <em>Relative Height</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRelativeHeight()
	 * @generated
	 * @ordered
	 */
	protected static final double RELATIVE_HEIGHT_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getRelativeHeight() <em>Relative Height</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRelativeHeight()
	 * @generated
	 * @ordered
	 */
	protected double relativeHeight = RELATIVE_HEIGHT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BoxRelativeAnchorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PictogramsPackage.Literals.BOX_RELATIVE_ANCHOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getRelativeWidth() {
		return relativeWidth;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRelativeWidth(double newRelativeWidth) {
		double oldRelativeWidth = relativeWidth;
		relativeWidth = newRelativeWidth;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PictogramsPackage.BOX_RELATIVE_ANCHOR__RELATIVE_WIDTH, oldRelativeWidth, relativeWidth));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getRelativeHeight() {
		return relativeHeight;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRelativeHeight(double newRelativeHeight) {
		double oldRelativeHeight = relativeHeight;
		relativeHeight = newRelativeHeight;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PictogramsPackage.BOX_RELATIVE_ANCHOR__RELATIVE_HEIGHT, oldRelativeHeight, relativeHeight));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PictogramsPackage.BOX_RELATIVE_ANCHOR__RELATIVE_WIDTH:
				return getRelativeWidth();
			case PictogramsPackage.BOX_RELATIVE_ANCHOR__RELATIVE_HEIGHT:
				return getRelativeHeight();
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
			case PictogramsPackage.BOX_RELATIVE_ANCHOR__RELATIVE_WIDTH:
				setRelativeWidth((Double)newValue);
				return;
			case PictogramsPackage.BOX_RELATIVE_ANCHOR__RELATIVE_HEIGHT:
				setRelativeHeight((Double)newValue);
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
			case PictogramsPackage.BOX_RELATIVE_ANCHOR__RELATIVE_WIDTH:
				setRelativeWidth(RELATIVE_WIDTH_EDEFAULT);
				return;
			case PictogramsPackage.BOX_RELATIVE_ANCHOR__RELATIVE_HEIGHT:
				setRelativeHeight(RELATIVE_HEIGHT_EDEFAULT);
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
			case PictogramsPackage.BOX_RELATIVE_ANCHOR__RELATIVE_WIDTH:
				return relativeWidth != RELATIVE_WIDTH_EDEFAULT;
			case PictogramsPackage.BOX_RELATIVE_ANCHOR__RELATIVE_HEIGHT:
				return relativeHeight != RELATIVE_HEIGHT_EDEFAULT;
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
		result.append(" (relativeWidth: ");
		result.append(relativeWidth);
		result.append(", relativeHeight: ");
		result.append(relativeHeight);
		result.append(')');
		return result.toString();
	}

} //BoxRelativeAnchorImpl
