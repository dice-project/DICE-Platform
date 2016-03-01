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
package org.eclipse.epf.library.edit.util.model.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.epf.library.edit.util.model.ModelPackage;
import org.eclipse.epf.library.edit.util.model.OrderInfo;
import org.eclipse.epf.library.edit.util.model.OrderInfoCollection;


/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Order Info Collection</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.epf.library.edit.util.model.impl.OrderInfoCollectionImpl#getOrderInfos <em>Order Infos</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class OrderInfoCollectionImpl extends EObjectImpl implements
		OrderInfoCollection {
	/**
	 * The cached value of the '{@link #getOrderInfos() <em>Order Infos</em>}'
	 * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getOrderInfos()
	 * @generated
	 * @ordered
	 */
	protected EList orderInfos = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected OrderInfoCollectionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected EClass eStaticClass() {
		return ModelPackage.eINSTANCE.getOrderInfoCollection();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList getOrderInfos() {
		if (orderInfos == null) {
			orderInfos = new EObjectContainmentEList(OrderInfo.class, this,
					ModelPackage.ORDER_INFO_COLLECTION__ORDER_INFOS);
		}
		return orderInfos;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
			case ModelPackage.ORDER_INFO_COLLECTION__ORDER_INFOS:
				return ((InternalEList) getOrderInfos()).basicRemove(otherEnd,
						msgs);
			default:
				return eDynamicInverseRemove(otherEnd, featureID, baseClass,
						msgs);
			}
		}
		return eBasicSetContainer(null, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
		case ModelPackage.ORDER_INFO_COLLECTION__ORDER_INFOS:
			return getOrderInfos();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
		case ModelPackage.ORDER_INFO_COLLECTION__ORDER_INFOS:
			getOrderInfos().clear();
			getOrderInfos().addAll((Collection) newValue);
			return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
		case ModelPackage.ORDER_INFO_COLLECTION__ORDER_INFOS:
			getOrderInfos().clear();
			return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
		case ModelPackage.ORDER_INFO_COLLECTION__ORDER_INFOS:
			return orderInfos != null && !orderInfos.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

} // OrderInfoCollectionImpl
