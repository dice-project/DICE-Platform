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
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Discipline;
import org.eclipse.epf.uma.Task;
import org.eclipse.epf.uma.UmaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Discipline</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.impl.DisciplineImpl#getTasks <em>Tasks</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.DisciplineImpl#getSubdiscipline <em>Subdiscipline</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.DisciplineImpl#getReferenceWorkflows <em>Reference Workflows</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DisciplineImpl extends ContentCategoryImpl implements Discipline {
	/**
	 * The cached value of the '{@link #getTasks() <em>Tasks</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTasks()
	 * @generated
	 * @ordered
	 */
	protected EList<Task> tasks;

	/**
	 * The cached value of the '{@link #getSubdiscipline() <em>Subdiscipline</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubdiscipline()
	 * @generated
	 * @ordered
	 */
	protected EList<Discipline> subdiscipline;

	/**
	 * The cached value of the '{@link #getReferenceWorkflows() <em>Reference Workflows</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferenceWorkflows()
	 * @generated
	 * @ordered
	 */
	protected EList<Activity> referenceWorkflows;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DisciplineImpl() {
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
		return UmaPackage.Literals.DISCIPLINE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Task> getTasks() {
		if (tasks == null) {
			tasks = new EObjectResolvingEList<Task>(Task.class, this,
					UmaPackage.DISCIPLINE__TASKS);
		}
		return tasks;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Discipline> getSubdiscipline() {
		if (subdiscipline == null) {
			subdiscipline = new EObjectContainmentEList.Resolving<Discipline>(
					Discipline.class, this,
					UmaPackage.DISCIPLINE__SUBDISCIPLINE);
		}
		return subdiscipline;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Activity> getReferenceWorkflows() {
		if (referenceWorkflows == null) {
			referenceWorkflows = new EObjectResolvingEList<Activity>(
					Activity.class, this,
					UmaPackage.DISCIPLINE__REFERENCE_WORKFLOWS);
		}
		return referenceWorkflows;
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
		case UmaPackage.DISCIPLINE__SUBDISCIPLINE:
			return ((InternalEList<?>) getSubdiscipline()).basicRemove(
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
		case UmaPackage.DISCIPLINE__TASKS:
			return getTasks();
		case UmaPackage.DISCIPLINE__SUBDISCIPLINE:
			return getSubdiscipline();
		case UmaPackage.DISCIPLINE__REFERENCE_WORKFLOWS:
			return getReferenceWorkflows();
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
		case UmaPackage.DISCIPLINE__TASKS:
			getTasks().clear();
			getTasks().addAll((Collection<? extends Task>) newValue);
			return;
		case UmaPackage.DISCIPLINE__SUBDISCIPLINE:
			getSubdiscipline().clear();
			getSubdiscipline().addAll(
					(Collection<? extends Discipline>) newValue);
			return;
		case UmaPackage.DISCIPLINE__REFERENCE_WORKFLOWS:
			getReferenceWorkflows().clear();
			getReferenceWorkflows().addAll(
					(Collection<? extends Activity>) newValue);
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
		case UmaPackage.DISCIPLINE__TASKS:
			getTasks().clear();
			return;
		case UmaPackage.DISCIPLINE__SUBDISCIPLINE:
			getSubdiscipline().clear();
			return;
		case UmaPackage.DISCIPLINE__REFERENCE_WORKFLOWS:
			getReferenceWorkflows().clear();
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
		case UmaPackage.DISCIPLINE__TASKS:
			return tasks != null && !tasks.isEmpty();
		case UmaPackage.DISCIPLINE__SUBDISCIPLINE:
			return subdiscipline != null && !subdiscipline.isEmpty();
		case UmaPackage.DISCIPLINE__REFERENCE_WORKFLOWS:
			return referenceWorkflows != null && !referenceWorkflows.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //DisciplineImpl
