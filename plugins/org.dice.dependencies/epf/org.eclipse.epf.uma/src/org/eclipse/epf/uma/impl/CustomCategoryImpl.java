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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.epf.uma.ContentCategory;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.UmaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Custom Category</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.impl.CustomCategoryImpl#getCategorizedElements <em>Categorized Elements</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.CustomCategoryImpl#getSubCategories <em>Sub Categories</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CustomCategoryImpl extends ContentCategoryImpl implements
		CustomCategory {
	/**
	 * The cached value of the '{@link #getCategorizedElements() <em>Categorized Elements</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCategorizedElements()
	 * @generated
	 * @ordered
	 */
	protected EList<DescribableElement> categorizedElements;

	/**
	 * The cached value of the '{@link #getSubCategories() <em>Sub Categories</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubCategories()
	 * @generated
	 * @ordered
	 */
	protected EList<ContentCategory> subCategories;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CustomCategoryImpl() {
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
		return UmaPackage.Literals.CUSTOM_CATEGORY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<DescribableElement> getCategorizedElements() {
		if (categorizedElements == null) {
			categorizedElements = new EObjectResolvingEList<DescribableElement>(
					DescribableElement.class, this,
					UmaPackage.CUSTOM_CATEGORY__CATEGORIZED_ELEMENTS);
		}
		return categorizedElements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<ContentCategory> getSubCategories() {
		if (subCategories == null) {
			subCategories = new EObjectResolvingEList<ContentCategory>(
					ContentCategory.class, this,
					UmaPackage.CUSTOM_CATEGORY__SUB_CATEGORIES);
		}
		return subCategories;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case UmaPackage.CUSTOM_CATEGORY__CATEGORIZED_ELEMENTS:
			return getCategorizedElements();
		case UmaPackage.CUSTOM_CATEGORY__SUB_CATEGORIES:
			return getSubCategories();
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
		case UmaPackage.CUSTOM_CATEGORY__CATEGORIZED_ELEMENTS:
			getCategorizedElements().clear();
			getCategorizedElements().addAll(
					(Collection<? extends DescribableElement>) newValue);
			return;
		case UmaPackage.CUSTOM_CATEGORY__SUB_CATEGORIES:
			getSubCategories().clear();
			getSubCategories().addAll(
					(Collection<? extends ContentCategory>) newValue);
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
		case UmaPackage.CUSTOM_CATEGORY__CATEGORIZED_ELEMENTS:
			getCategorizedElements().clear();
			return;
		case UmaPackage.CUSTOM_CATEGORY__SUB_CATEGORIES:
			getSubCategories().clear();
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
		case UmaPackage.CUSTOM_CATEGORY__CATEGORIZED_ELEMENTS:
			return categorizedElements != null
					&& !categorizedElements.isEmpty();
		case UmaPackage.CUSTOM_CATEGORY__SUB_CATEGORIES:
			return subCategories != null && !subCategories.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //CustomCategoryImpl
