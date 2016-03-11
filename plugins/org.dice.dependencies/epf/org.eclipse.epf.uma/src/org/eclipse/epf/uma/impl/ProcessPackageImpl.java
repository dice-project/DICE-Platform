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
import org.eclipse.epf.uma.Diagram;
import org.eclipse.epf.uma.ProcessElement;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.UmaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Process Package</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.impl.ProcessPackageImpl#getProcessElements <em>Process Elements</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.ProcessPackageImpl#getDiagrams <em>Diagrams</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ProcessPackageImpl extends MethodPackageImpl implements
		ProcessPackage {
	/**
	 * The cached value of the '{@link #getProcessElements() <em>Process Elements</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProcessElements()
	 * @generated
	 * @ordered
	 */
	protected EList<ProcessElement> processElements;

	/**
	 * The cached value of the '{@link #getDiagrams() <em>Diagrams</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDiagrams()
	 * @generated
	 * @ordered
	 */
	protected EList<Diagram> diagrams;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProcessPackageImpl() {
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
		return UmaPackage.Literals.PROCESS_PACKAGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<ProcessElement> getProcessElements() {
		if (processElements == null) {
			processElements = new EObjectContainmentEList.Resolving<ProcessElement>(
					ProcessElement.class, this,
					UmaPackage.PROCESS_PACKAGE__PROCESS_ELEMENTS);
		}
		return processElements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Diagram> getDiagrams() {
		if (diagrams == null) {
			diagrams = new EObjectContainmentEList.Resolving<Diagram>(
					Diagram.class, this, UmaPackage.PROCESS_PACKAGE__DIAGRAMS);
		}
		return diagrams;
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
		case UmaPackage.PROCESS_PACKAGE__PROCESS_ELEMENTS:
			return ((InternalEList<?>) getProcessElements()).basicRemove(
					otherEnd, msgs);
		case UmaPackage.PROCESS_PACKAGE__DIAGRAMS:
			return ((InternalEList<?>) getDiagrams()).basicRemove(otherEnd,
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
		case UmaPackage.PROCESS_PACKAGE__PROCESS_ELEMENTS:
			return getProcessElements();
		case UmaPackage.PROCESS_PACKAGE__DIAGRAMS:
			return getDiagrams();
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
		case UmaPackage.PROCESS_PACKAGE__PROCESS_ELEMENTS:
			getProcessElements().clear();
			getProcessElements().addAll(
					(Collection<? extends ProcessElement>) newValue);
			return;
		case UmaPackage.PROCESS_PACKAGE__DIAGRAMS:
			getDiagrams().clear();
			getDiagrams().addAll((Collection<? extends Diagram>) newValue);
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
		case UmaPackage.PROCESS_PACKAGE__PROCESS_ELEMENTS:
			getProcessElements().clear();
			return;
		case UmaPackage.PROCESS_PACKAGE__DIAGRAMS:
			getDiagrams().clear();
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
		case UmaPackage.PROCESS_PACKAGE__PROCESS_ELEMENTS:
			return processElements != null && !processElements.isEmpty();
		case UmaPackage.PROCESS_PACKAGE__DIAGRAMS:
			return diagrams != null && !diagrams.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ProcessPackageImpl
