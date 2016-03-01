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

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.epf.xml.uma.ProcessComponentInterface;
import org.eclipse.epf.xml.uma.TaskDescriptor;
import org.eclipse.epf.xml.uma.UmaPackage;
import org.eclipse.epf.xml.uma.WorkProductDescriptor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Process Component Interface</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.ProcessComponentInterfaceImpl#getGroup2 <em>Group2</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.ProcessComponentInterfaceImpl#getInterfaceSpecification <em>Interface Specification</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.ProcessComponentInterfaceImpl#getInterfaceIO <em>Interface IO</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ProcessComponentInterfaceImpl extends BreakdownElementImpl implements ProcessComponentInterface {
	/**
	 * The cached value of the '{@link #getGroup2() <em>Group2</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroup2()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap group2;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProcessComponentInterfaceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UmaPackage.Literals.PROCESS_COMPONENT_INTERFACE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getGroup2() {
		if (group2 == null) {
			group2 = new BasicFeatureMap(this, UmaPackage.PROCESS_COMPONENT_INTERFACE__GROUP2);
		}
		return group2;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TaskDescriptor> getInterfaceSpecification() {
		return getGroup2().list(UmaPackage.Literals.PROCESS_COMPONENT_INTERFACE__INTERFACE_SPECIFICATION);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<WorkProductDescriptor> getInterfaceIO() {
		return getGroup2().list(UmaPackage.Literals.PROCESS_COMPONENT_INTERFACE__INTERFACE_IO);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UmaPackage.PROCESS_COMPONENT_INTERFACE__GROUP2:
				return ((InternalEList<?>)getGroup2()).basicRemove(otherEnd, msgs);
			case UmaPackage.PROCESS_COMPONENT_INTERFACE__INTERFACE_SPECIFICATION:
				return ((InternalEList<?>)getInterfaceSpecification()).basicRemove(otherEnd, msgs);
			case UmaPackage.PROCESS_COMPONENT_INTERFACE__INTERFACE_IO:
				return ((InternalEList<?>)getInterfaceIO()).basicRemove(otherEnd, msgs);
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
			case UmaPackage.PROCESS_COMPONENT_INTERFACE__GROUP2:
				if (coreType) return getGroup2();
				return ((FeatureMap.Internal)getGroup2()).getWrapper();
			case UmaPackage.PROCESS_COMPONENT_INTERFACE__INTERFACE_SPECIFICATION:
				return getInterfaceSpecification();
			case UmaPackage.PROCESS_COMPONENT_INTERFACE__INTERFACE_IO:
				return getInterfaceIO();
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
			case UmaPackage.PROCESS_COMPONENT_INTERFACE__GROUP2:
				((FeatureMap.Internal)getGroup2()).set(newValue);
				return;
			case UmaPackage.PROCESS_COMPONENT_INTERFACE__INTERFACE_SPECIFICATION:
				getInterfaceSpecification().clear();
				getInterfaceSpecification().addAll((Collection<? extends TaskDescriptor>)newValue);
				return;
			case UmaPackage.PROCESS_COMPONENT_INTERFACE__INTERFACE_IO:
				getInterfaceIO().clear();
				getInterfaceIO().addAll((Collection<? extends WorkProductDescriptor>)newValue);
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
			case UmaPackage.PROCESS_COMPONENT_INTERFACE__GROUP2:
				getGroup2().clear();
				return;
			case UmaPackage.PROCESS_COMPONENT_INTERFACE__INTERFACE_SPECIFICATION:
				getInterfaceSpecification().clear();
				return;
			case UmaPackage.PROCESS_COMPONENT_INTERFACE__INTERFACE_IO:
				getInterfaceIO().clear();
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
			case UmaPackage.PROCESS_COMPONENT_INTERFACE__GROUP2:
				return group2 != null && !group2.isEmpty();
			case UmaPackage.PROCESS_COMPONENT_INTERFACE__INTERFACE_SPECIFICATION:
				return !getInterfaceSpecification().isEmpty();
			case UmaPackage.PROCESS_COMPONENT_INTERFACE__INTERFACE_IO:
				return !getInterfaceIO().isEmpty();
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
		result.append(" (group2: ");
		result.append(group2);
		result.append(')');
		return result.toString();
	}

} //ProcessComponentInterfaceImpl
