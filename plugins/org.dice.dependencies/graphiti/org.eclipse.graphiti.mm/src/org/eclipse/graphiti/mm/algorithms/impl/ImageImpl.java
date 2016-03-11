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
import org.eclipse.graphiti.mm.algorithms.Image;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Image</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.impl.ImageImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.impl.ImageImpl#getStretchH <em>Stretch H</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.impl.ImageImpl#getStretchV <em>Stretch V</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.impl.ImageImpl#getProportional <em>Proportional</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ImageImpl extends GraphicsAlgorithmImpl implements Image {
	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getStretchH() <em>Stretch H</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStretchH()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean STRETCH_H_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStretchH() <em>Stretch H</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStretchH()
	 * @generated
	 * @ordered
	 */
	protected Boolean stretchH = STRETCH_H_EDEFAULT;

	/**
	 * The default value of the '{@link #getStretchV() <em>Stretch V</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStretchV()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean STRETCH_V_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStretchV() <em>Stretch V</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStretchV()
	 * @generated
	 * @ordered
	 */
	protected Boolean stretchV = STRETCH_V_EDEFAULT;

	/**
	 * The default value of the '{@link #getProportional() <em>Proportional</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProportional()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean PROPORTIONAL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getProportional() <em>Proportional</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProportional()
	 * @generated
	 * @ordered
	 */
	protected Boolean proportional = PROPORTIONAL_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ImageImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AlgorithmsPackage.Literals.IMAGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AlgorithmsPackage.IMAGE__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getStretchH() {
		return stretchH;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStretchH(Boolean newStretchH) {
		Boolean oldStretchH = stretchH;
		stretchH = newStretchH;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AlgorithmsPackage.IMAGE__STRETCH_H, oldStretchH, stretchH));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getStretchV() {
		return stretchV;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStretchV(Boolean newStretchV) {
		Boolean oldStretchV = stretchV;
		stretchV = newStretchV;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AlgorithmsPackage.IMAGE__STRETCH_V, oldStretchV, stretchV));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getProportional() {
		return proportional;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProportional(Boolean newProportional) {
		Boolean oldProportional = proportional;
		proportional = newProportional;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AlgorithmsPackage.IMAGE__PROPORTIONAL, oldProportional, proportional));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case AlgorithmsPackage.IMAGE__ID:
				return getId();
			case AlgorithmsPackage.IMAGE__STRETCH_H:
				return getStretchH();
			case AlgorithmsPackage.IMAGE__STRETCH_V:
				return getStretchV();
			case AlgorithmsPackage.IMAGE__PROPORTIONAL:
				return getProportional();
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
			case AlgorithmsPackage.IMAGE__ID:
				setId((String)newValue);
				return;
			case AlgorithmsPackage.IMAGE__STRETCH_H:
				setStretchH((Boolean)newValue);
				return;
			case AlgorithmsPackage.IMAGE__STRETCH_V:
				setStretchV((Boolean)newValue);
				return;
			case AlgorithmsPackage.IMAGE__PROPORTIONAL:
				setProportional((Boolean)newValue);
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
			case AlgorithmsPackage.IMAGE__ID:
				setId(ID_EDEFAULT);
				return;
			case AlgorithmsPackage.IMAGE__STRETCH_H:
				setStretchH(STRETCH_H_EDEFAULT);
				return;
			case AlgorithmsPackage.IMAGE__STRETCH_V:
				setStretchV(STRETCH_V_EDEFAULT);
				return;
			case AlgorithmsPackage.IMAGE__PROPORTIONAL:
				setProportional(PROPORTIONAL_EDEFAULT);
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
			case AlgorithmsPackage.IMAGE__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case AlgorithmsPackage.IMAGE__STRETCH_H:
				return STRETCH_H_EDEFAULT == null ? stretchH != null : !STRETCH_H_EDEFAULT.equals(stretchH);
			case AlgorithmsPackage.IMAGE__STRETCH_V:
				return STRETCH_V_EDEFAULT == null ? stretchV != null : !STRETCH_V_EDEFAULT.equals(stretchV);
			case AlgorithmsPackage.IMAGE__PROPORTIONAL:
				return PROPORTIONAL_EDEFAULT == null ? proportional != null : !PROPORTIONAL_EDEFAULT.equals(proportional);
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
		result.append(" (id: ");
		result.append(id);
		result.append(", stretchH: ");
		result.append(stretchH);
		result.append(", stretchV: ");
		result.append(stretchV);
		result.append(", proportional: ");
		result.append(proportional);
		result.append(')');
		return result.toString();
	}

} //ImageImpl
