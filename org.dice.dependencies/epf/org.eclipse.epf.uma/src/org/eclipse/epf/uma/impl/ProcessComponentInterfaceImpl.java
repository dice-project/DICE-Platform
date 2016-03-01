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
import org.eclipse.epf.uma.ProcessComponentInterface;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.WorkProductDescriptor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Process Component Interface</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.impl.ProcessComponentInterfaceImpl#getInterfaceSpecifications <em>Interface Specifications</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.ProcessComponentInterfaceImpl#getInterfaceIO <em>Interface IO</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ProcessComponentInterfaceImpl extends BreakdownElementImpl
		implements ProcessComponentInterface {
	/**
	 * The cached value of the '{@link #getInterfaceSpecifications() <em>Interface Specifications</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInterfaceSpecifications()
	 * @generated
	 * @ordered
	 */
	protected EList<TaskDescriptor> interfaceSpecifications;

	/**
	 * The cached value of the '{@link #getInterfaceIO() <em>Interface IO</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInterfaceIO()
	 * @generated
	 * @ordered
	 */
	protected EList<WorkProductDescriptor> interfaceIO;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProcessComponentInterfaceImpl() {
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
		return UmaPackage.Literals.PROCESS_COMPONENT_INTERFACE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<TaskDescriptor> getInterfaceSpecifications() {
		if (interfaceSpecifications == null) {
			interfaceSpecifications = new EObjectContainmentEList.Resolving<TaskDescriptor>(
					TaskDescriptor.class,
					this,
					UmaPackage.PROCESS_COMPONENT_INTERFACE__INTERFACE_SPECIFICATIONS);
		}
		return interfaceSpecifications;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<WorkProductDescriptor> getInterfaceIO() {
		if (interfaceIO == null) {
			interfaceIO = new EObjectContainmentEList.Resolving<WorkProductDescriptor>(
					WorkProductDescriptor.class, this,
					UmaPackage.PROCESS_COMPONENT_INTERFACE__INTERFACE_IO);
		}
		return interfaceIO;
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
		case UmaPackage.PROCESS_COMPONENT_INTERFACE__INTERFACE_SPECIFICATIONS:
			return ((InternalEList<?>) getInterfaceSpecifications())
					.basicRemove(otherEnd, msgs);
		case UmaPackage.PROCESS_COMPONENT_INTERFACE__INTERFACE_IO:
			return ((InternalEList<?>) getInterfaceIO()).basicRemove(otherEnd,
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
		case UmaPackage.PROCESS_COMPONENT_INTERFACE__INTERFACE_SPECIFICATIONS:
			return getInterfaceSpecifications();
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
		case UmaPackage.PROCESS_COMPONENT_INTERFACE__INTERFACE_SPECIFICATIONS:
			getInterfaceSpecifications().clear();
			getInterfaceSpecifications().addAll(
					(Collection<? extends TaskDescriptor>) newValue);
			return;
		case UmaPackage.PROCESS_COMPONENT_INTERFACE__INTERFACE_IO:
			getInterfaceIO().clear();
			getInterfaceIO().addAll(
					(Collection<? extends WorkProductDescriptor>) newValue);
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
		case UmaPackage.PROCESS_COMPONENT_INTERFACE__INTERFACE_SPECIFICATIONS:
			getInterfaceSpecifications().clear();
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
		//UMA-->
		EStructuralFeature feature = getFeatureWithOverridenDefaultValue(featureID);
		if (feature != null) {
			return isFeatureWithOverridenDefaultValueSet(feature);
		}
		//UMA<--		
		switch (featureID) {
		case UmaPackage.PROCESS_COMPONENT_INTERFACE__INTERFACE_SPECIFICATIONS:
			return interfaceSpecifications != null
					&& !interfaceSpecifications.isEmpty();
		case UmaPackage.PROCESS_COMPONENT_INTERFACE__INTERFACE_IO:
			return interfaceIO != null && !interfaceIO.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ProcessComponentInterfaceImpl
