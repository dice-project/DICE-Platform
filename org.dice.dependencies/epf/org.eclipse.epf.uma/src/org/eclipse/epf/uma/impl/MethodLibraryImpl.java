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
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.UmaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Method Library</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.impl.MethodLibraryImpl#getMethodPlugins <em>Method Plugins</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.MethodLibraryImpl#getPredefinedConfigurations <em>Predefined Configurations</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MethodLibraryImpl extends MethodUnitImpl implements MethodLibrary {
	/**
	 * The cached value of the '{@link #getMethodPlugins() <em>Method Plugins</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMethodPlugins()
	 * @generated
	 * @ordered
	 */
	protected EList<MethodPlugin> methodPlugins;

	/**
	 * The cached value of the '{@link #getPredefinedConfigurations() <em>Predefined Configurations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPredefinedConfigurations()
	 * @generated
	 * @ordered
	 */
	protected EList<MethodConfiguration> predefinedConfigurations;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MethodLibraryImpl() {
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
		return UmaPackage.Literals.METHOD_LIBRARY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<MethodPlugin> getMethodPlugins() {
		if (methodPlugins == null) {
			methodPlugins = new EObjectContainmentEList.Resolving<MethodPlugin>(
					MethodPlugin.class, this,
					UmaPackage.METHOD_LIBRARY__METHOD_PLUGINS);
		}
		return methodPlugins;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<MethodConfiguration> getPredefinedConfigurations() {
		if (predefinedConfigurations == null) {
			predefinedConfigurations = new EObjectContainmentEList.Resolving<MethodConfiguration>(
					MethodConfiguration.class, this,
					UmaPackage.METHOD_LIBRARY__PREDEFINED_CONFIGURATIONS);
		}
		return predefinedConfigurations;
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
		case UmaPackage.METHOD_LIBRARY__METHOD_PLUGINS:
			return ((InternalEList<?>) getMethodPlugins()).basicRemove(
					otherEnd, msgs);
		case UmaPackage.METHOD_LIBRARY__PREDEFINED_CONFIGURATIONS:
			return ((InternalEList<?>) getPredefinedConfigurations())
					.basicRemove(otherEnd, msgs);
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
		case UmaPackage.METHOD_LIBRARY__METHOD_PLUGINS:
			return getMethodPlugins();
		case UmaPackage.METHOD_LIBRARY__PREDEFINED_CONFIGURATIONS:
			return getPredefinedConfigurations();
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
		case UmaPackage.METHOD_LIBRARY__METHOD_PLUGINS:
			getMethodPlugins().clear();
			getMethodPlugins().addAll(
					(Collection<? extends MethodPlugin>) newValue);
			return;
		case UmaPackage.METHOD_LIBRARY__PREDEFINED_CONFIGURATIONS:
			getPredefinedConfigurations().clear();
			getPredefinedConfigurations().addAll(
					(Collection<? extends MethodConfiguration>) newValue);
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
		case UmaPackage.METHOD_LIBRARY__METHOD_PLUGINS:
			getMethodPlugins().clear();
			return;
		case UmaPackage.METHOD_LIBRARY__PREDEFINED_CONFIGURATIONS:
			getPredefinedConfigurations().clear();
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
		case UmaPackage.METHOD_LIBRARY__METHOD_PLUGINS:
			return methodPlugins != null && !methodPlugins.isEmpty();
		case UmaPackage.METHOD_LIBRARY__PREDEFINED_CONFIGURATIONS:
			return predefinedConfigurations != null
					&& !predefinedConfigurations.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //MethodLibraryImpl
