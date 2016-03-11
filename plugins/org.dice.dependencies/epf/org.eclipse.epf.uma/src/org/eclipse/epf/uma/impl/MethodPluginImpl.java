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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.epf.uma.MethodPackage;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.UmaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Method Plugin</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.impl.MethodPluginImpl#getUserChangeable <em>User Changeable</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.MethodPluginImpl#getMethodPackages <em>Method Packages</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.MethodPluginImpl#getBases <em>Bases</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.MethodPluginImpl#isSupporting <em>Supporting</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MethodPluginImpl extends MethodUnitImpl implements MethodPlugin {
	/**
	 * The default value of the '{@link #getUserChangeable() <em>User Changeable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUserChangeable()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean USER_CHANGEABLE_EDEFAULT = Boolean.TRUE;

	/**
	 * The cached value of the '{@link #getUserChangeable() <em>User Changeable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUserChangeable()
	 * @generated
	 * @ordered
	 */
	protected Boolean userChangeable = USER_CHANGEABLE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getMethodPackages() <em>Method Packages</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMethodPackages()
	 * @generated
	 * @ordered
	 */
	protected EList<MethodPackage> methodPackages;

	/**
	 * The cached value of the '{@link #getBases() <em>Bases</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBases()
	 * @generated
	 * @ordered
	 */
	protected EList<MethodPlugin> bases;

	/**
	 * The default value of the '{@link #isSupporting() <em>Supporting</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSupporting()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SUPPORTING_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isSupporting() <em>Supporting</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSupporting()
	 * @generated
	 * @ordered
	 */
	protected boolean supporting = SUPPORTING_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MethodPluginImpl() {
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
		return UmaPackage.Literals.METHOD_PLUGIN;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getUserChangeable() {
		return userChangeable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUserChangeable(Boolean newUserChangeable) {
		Boolean oldUserChangeable = userChangeable;
		userChangeable = newUserChangeable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.METHOD_PLUGIN__USER_CHANGEABLE,
					oldUserChangeable, userChangeable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<MethodPackage> getMethodPackages() {
		if (methodPackages == null) {
			methodPackages = new EObjectContainmentEList.Resolving<MethodPackage>(
					MethodPackage.class, this,
					UmaPackage.METHOD_PLUGIN__METHOD_PACKAGES);
		}
		return methodPackages;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<MethodPlugin> getBases() {
		if (bases == null) {
			bases = new EObjectResolvingEList<MethodPlugin>(MethodPlugin.class,
					this, UmaPackage.METHOD_PLUGIN__BASES);
		}
		return bases;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSupporting() {
		return supporting;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSupporting(boolean newSupporting) {
		boolean oldSupporting = supporting;
		supporting = newSupporting;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.METHOD_PLUGIN__SUPPORTING, oldSupporting,
					supporting));
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
		case UmaPackage.METHOD_PLUGIN__METHOD_PACKAGES:
			return ((InternalEList<?>) getMethodPackages()).basicRemove(
					otherEnd, msgs);
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
		case UmaPackage.METHOD_PLUGIN__USER_CHANGEABLE:
			return getUserChangeable();
		case UmaPackage.METHOD_PLUGIN__METHOD_PACKAGES:
			return getMethodPackages();
		case UmaPackage.METHOD_PLUGIN__BASES:
			return getBases();
		case UmaPackage.METHOD_PLUGIN__SUPPORTING:
			return isSupporting() ? Boolean.TRUE : Boolean.FALSE;
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
		case UmaPackage.METHOD_PLUGIN__USER_CHANGEABLE:
			setUserChangeable((Boolean) newValue);
			return;
		case UmaPackage.METHOD_PLUGIN__METHOD_PACKAGES:
			getMethodPackages().clear();
			getMethodPackages().addAll(
					(Collection<? extends MethodPackage>) newValue);
			return;
		case UmaPackage.METHOD_PLUGIN__BASES:
			getBases().clear();
			getBases().addAll((Collection<? extends MethodPlugin>) newValue);
			return;
		case UmaPackage.METHOD_PLUGIN__SUPPORTING:
			setSupporting(((Boolean) newValue).booleanValue());
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
		case UmaPackage.METHOD_PLUGIN__USER_CHANGEABLE:
			setUserChangeable(USER_CHANGEABLE_EDEFAULT);
			return;
		case UmaPackage.METHOD_PLUGIN__METHOD_PACKAGES:
			getMethodPackages().clear();
			return;
		case UmaPackage.METHOD_PLUGIN__BASES:
			getBases().clear();
			return;
		case UmaPackage.METHOD_PLUGIN__SUPPORTING:
			setSupporting(SUPPORTING_EDEFAULT);
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
		case UmaPackage.METHOD_PLUGIN__USER_CHANGEABLE:
			return USER_CHANGEABLE_EDEFAULT == null ? userChangeable != null
					: !USER_CHANGEABLE_EDEFAULT.equals(userChangeable);
		case UmaPackage.METHOD_PLUGIN__METHOD_PACKAGES:
			return methodPackages != null && !methodPackages.isEmpty();
		case UmaPackage.METHOD_PLUGIN__BASES:
			return bases != null && !bases.isEmpty();
		case UmaPackage.METHOD_PLUGIN__SUPPORTING:
			return supporting != SUPPORTING_EDEFAULT;
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
		if (eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (userChangeable: "); //$NON-NLS-1$
		result.append(userChangeable);
		result.append(", supporting: "); //$NON-NLS-1$
		result.append(supporting);
		result.append(')');
		return result.toString();
	}

} //MethodPluginImpl
