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
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.epf.uma.Domain;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.WorkProduct;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Domain</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.impl.DomainImpl#getWorkProducts <em>Work Products</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.DomainImpl#getSubdomains <em>Subdomains</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DomainImpl extends ContentCategoryImpl implements Domain {
	/**
	 * The cached value of the '{@link #getWorkProducts() <em>Work Products</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWorkProducts()
	 * @generated
	 * @ordered
	 */
	protected EList<WorkProduct> workProducts;

	/**
	 * The cached value of the '{@link #getSubdomains() <em>Subdomains</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubdomains()
	 * @generated
	 * @ordered
	 */
	protected EList<Domain> subdomains;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DomainImpl() {
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
		return UmaPackage.Literals.DOMAIN;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<WorkProduct> getWorkProducts() {
		if (workProducts == null) {
			workProducts = new EObjectResolvingEList<WorkProduct>(
					WorkProduct.class, this, UmaPackage.DOMAIN__WORK_PRODUCTS);
		}
		return workProducts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Domain> getSubdomains() {
		if (subdomains == null) {
			subdomains = new EObjectContainmentEList.Resolving<Domain>(
					Domain.class, this, UmaPackage.DOMAIN__SUBDOMAINS);
		}
		return subdomains;
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
		case UmaPackage.DOMAIN__SUBDOMAINS:
			return ((InternalEList<?>) getSubdomains()).basicRemove(otherEnd,
					msgs);
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
		case UmaPackage.DOMAIN__WORK_PRODUCTS:
			return getWorkProducts();
		case UmaPackage.DOMAIN__SUBDOMAINS:
			return getSubdomains();
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
		case UmaPackage.DOMAIN__WORK_PRODUCTS:
			getWorkProducts().clear();
			getWorkProducts().addAll(
					(Collection<? extends WorkProduct>) newValue);
			return;
		case UmaPackage.DOMAIN__SUBDOMAINS:
			getSubdomains().clear();
			getSubdomains().addAll((Collection<? extends Domain>) newValue);
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
		case UmaPackage.DOMAIN__WORK_PRODUCTS:
			getWorkProducts().clear();
			return;
		case UmaPackage.DOMAIN__SUBDOMAINS:
			getSubdomains().clear();
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
		case UmaPackage.DOMAIN__WORK_PRODUCTS:
			return workProducts != null && !workProducts.isEmpty();
		case UmaPackage.DOMAIN__SUBDOMAINS:
			return subdomains != null && !subdomains.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //DomainImpl
