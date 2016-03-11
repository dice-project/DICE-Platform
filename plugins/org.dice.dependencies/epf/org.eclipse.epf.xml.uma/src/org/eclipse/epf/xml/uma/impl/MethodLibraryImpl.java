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

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.epf.xml.uma.MethodConfiguration;
import org.eclipse.epf.xml.uma.MethodLibrary;
import org.eclipse.epf.xml.uma.MethodPlugin;
import org.eclipse.epf.xml.uma.UmaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Method Library</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.MethodLibraryImpl#getMethodPlugin <em>Method Plugin</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.MethodLibraryImpl#getMethodConfiguration <em>Method Configuration</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.MethodLibraryImpl#getTool <em>Tool</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MethodLibraryImpl extends MethodUnitImpl implements MethodLibrary {
	/**
	 * The cached value of the '{@link #getMethodPlugin() <em>Method Plugin</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMethodPlugin()
	 * @generated
	 * @ordered
	 */
	protected EList<MethodPlugin> methodPlugin;

	/**
	 * The cached value of the '{@link #getMethodConfiguration() <em>Method Configuration</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMethodConfiguration()
	 * @generated
	 * @ordered
	 */
	protected EList<MethodConfiguration> methodConfiguration;

	/**
	 * The default value of the '{@link #getTool() <em>Tool</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTool()
	 * @generated
	 * @ordered
	 */
	protected static final String TOOL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTool() <em>Tool</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTool()
	 * @generated
	 * @ordered
	 */
	protected String tool = TOOL_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MethodLibraryImpl() {
		super();
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
	public EList<MethodPlugin> getMethodPlugin() {
		if (methodPlugin == null) {
			methodPlugin = new EObjectContainmentEList<MethodPlugin>(MethodPlugin.class, this, UmaPackage.METHOD_LIBRARY__METHOD_PLUGIN);
		}
		return methodPlugin;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<MethodConfiguration> getMethodConfiguration() {
		if (methodConfiguration == null) {
			methodConfiguration = new EObjectContainmentEList<MethodConfiguration>(MethodConfiguration.class, this, UmaPackage.METHOD_LIBRARY__METHOD_CONFIGURATION);
		}
		return methodConfiguration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTool() {
		return tool;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTool(String newTool) {
		String oldTool = tool;
		tool = newTool;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UmaPackage.METHOD_LIBRARY__TOOL, oldTool, tool));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UmaPackage.METHOD_LIBRARY__METHOD_PLUGIN:
				return ((InternalEList<?>)getMethodPlugin()).basicRemove(otherEnd, msgs);
			case UmaPackage.METHOD_LIBRARY__METHOD_CONFIGURATION:
				return ((InternalEList<?>)getMethodConfiguration()).basicRemove(otherEnd, msgs);
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
			case UmaPackage.METHOD_LIBRARY__METHOD_PLUGIN:
				return getMethodPlugin();
			case UmaPackage.METHOD_LIBRARY__METHOD_CONFIGURATION:
				return getMethodConfiguration();
			case UmaPackage.METHOD_LIBRARY__TOOL:
				return getTool();
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
			case UmaPackage.METHOD_LIBRARY__METHOD_PLUGIN:
				getMethodPlugin().clear();
				getMethodPlugin().addAll((Collection<? extends MethodPlugin>)newValue);
				return;
			case UmaPackage.METHOD_LIBRARY__METHOD_CONFIGURATION:
				getMethodConfiguration().clear();
				getMethodConfiguration().addAll((Collection<? extends MethodConfiguration>)newValue);
				return;
			case UmaPackage.METHOD_LIBRARY__TOOL:
				setTool((String)newValue);
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
			case UmaPackage.METHOD_LIBRARY__METHOD_PLUGIN:
				getMethodPlugin().clear();
				return;
			case UmaPackage.METHOD_LIBRARY__METHOD_CONFIGURATION:
				getMethodConfiguration().clear();
				return;
			case UmaPackage.METHOD_LIBRARY__TOOL:
				setTool(TOOL_EDEFAULT);
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
			case UmaPackage.METHOD_LIBRARY__METHOD_PLUGIN:
				return methodPlugin != null && !methodPlugin.isEmpty();
			case UmaPackage.METHOD_LIBRARY__METHOD_CONFIGURATION:
				return methodConfiguration != null && !methodConfiguration.isEmpty();
			case UmaPackage.METHOD_LIBRARY__TOOL:
				return TOOL_EDEFAULT == null ? tool != null : !TOOL_EDEFAULT.equals(tool);
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
		result.append(" (tool: ");
		result.append(tool);
		result.append(')');
		return result.toString();
	}

} //MethodLibraryImpl
