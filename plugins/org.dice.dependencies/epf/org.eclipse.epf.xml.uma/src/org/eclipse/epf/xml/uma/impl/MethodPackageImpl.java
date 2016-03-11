/*******************************************************************************
 * Copyright (c) 2005, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial implementation
 *******************************************************************************/
package org.eclipse.epf.xml.uma.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.epf.xml.uma.MethodPackage;
import org.eclipse.epf.xml.uma.UmaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Method Package</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.MethodPackageImpl#getGroup1 <em>Group1</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.MethodPackageImpl#getReusedPackage <em>Reused Package</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.MethodPackageImpl#getMethodPackage <em>Method Package</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.MethodPackageImpl#isGlobal <em>Global</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MethodPackageImpl extends MethodElementImpl implements MethodPackage {
	/**
	 * The cached value of the '{@link #getGroup1() <em>Group1</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroup1()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap group1;

	/**
	 * The default value of the '{@link #isGlobal() <em>Global</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isGlobal()
	 * @generated
	 * @ordered
	 */
	protected static final boolean GLOBAL_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isGlobal() <em>Global</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isGlobal()
	 * @generated
	 * @ordered
	 */
	protected boolean global = GLOBAL_EDEFAULT;

	/**
	 * This is true if the Global attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean globalESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MethodPackageImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UmaPackage.Literals.METHOD_PACKAGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getGroup1() {
		if (group1 == null) {
			group1 = new BasicFeatureMap(this, UmaPackage.METHOD_PACKAGE__GROUP1);
		}
		return group1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getReusedPackage() {
		return getGroup1().list(UmaPackage.Literals.METHOD_PACKAGE__REUSED_PACKAGE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<MethodPackage> getMethodPackage() {
		return getGroup1().list(UmaPackage.Literals.METHOD_PACKAGE__METHOD_PACKAGE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isGlobal() {
		return global;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGlobal(boolean newGlobal) {
		boolean oldGlobal = global;
		global = newGlobal;
		boolean oldGlobalESet = globalESet;
		globalESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UmaPackage.METHOD_PACKAGE__GLOBAL, oldGlobal, global, !oldGlobalESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetGlobal() {
		boolean oldGlobal = global;
		boolean oldGlobalESet = globalESet;
		global = GLOBAL_EDEFAULT;
		globalESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, UmaPackage.METHOD_PACKAGE__GLOBAL, oldGlobal, GLOBAL_EDEFAULT, oldGlobalESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetGlobal() {
		return globalESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UmaPackage.METHOD_PACKAGE__GROUP1:
				return ((InternalEList<?>)getGroup1()).basicRemove(otherEnd, msgs);
			case UmaPackage.METHOD_PACKAGE__METHOD_PACKAGE:
				return ((InternalEList<?>)getMethodPackage()).basicRemove(otherEnd, msgs);
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
			case UmaPackage.METHOD_PACKAGE__GROUP1:
				if (coreType) return getGroup1();
				return ((FeatureMap.Internal)getGroup1()).getWrapper();
			case UmaPackage.METHOD_PACKAGE__REUSED_PACKAGE:
				return getReusedPackage();
			case UmaPackage.METHOD_PACKAGE__METHOD_PACKAGE:
				return getMethodPackage();
			case UmaPackage.METHOD_PACKAGE__GLOBAL:
				return isGlobal() ? Boolean.TRUE : Boolean.FALSE;
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
			case UmaPackage.METHOD_PACKAGE__GROUP1:
				((FeatureMap.Internal)getGroup1()).set(newValue);
				return;
			case UmaPackage.METHOD_PACKAGE__REUSED_PACKAGE:
				getReusedPackage().clear();
				getReusedPackage().addAll((Collection<? extends String>)newValue);
				return;
			case UmaPackage.METHOD_PACKAGE__METHOD_PACKAGE:
				getMethodPackage().clear();
				getMethodPackage().addAll((Collection<? extends MethodPackage>)newValue);
				return;
			case UmaPackage.METHOD_PACKAGE__GLOBAL:
				setGlobal(((Boolean)newValue).booleanValue());
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
			case UmaPackage.METHOD_PACKAGE__GROUP1:
				getGroup1().clear();
				return;
			case UmaPackage.METHOD_PACKAGE__REUSED_PACKAGE:
				getReusedPackage().clear();
				return;
			case UmaPackage.METHOD_PACKAGE__METHOD_PACKAGE:
				getMethodPackage().clear();
				return;
			case UmaPackage.METHOD_PACKAGE__GLOBAL:
				unsetGlobal();
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
			case UmaPackage.METHOD_PACKAGE__GROUP1:
				return group1 != null && !group1.isEmpty();
			case UmaPackage.METHOD_PACKAGE__REUSED_PACKAGE:
				return !getReusedPackage().isEmpty();
			case UmaPackage.METHOD_PACKAGE__METHOD_PACKAGE:
				return !getMethodPackage().isEmpty();
			case UmaPackage.METHOD_PACKAGE__GLOBAL:
				return isSetGlobal();
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
		result.append(" (group1: ");
		result.append(group1);
		result.append(", global: ");
		if (globalESet) result.append(global); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //MethodPackageImpl
