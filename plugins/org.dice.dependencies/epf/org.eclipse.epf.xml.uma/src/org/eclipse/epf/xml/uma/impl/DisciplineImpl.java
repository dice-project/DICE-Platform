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
import org.eclipse.epf.xml.uma.Discipline;
import org.eclipse.epf.xml.uma.UmaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Discipline</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.DisciplineImpl#getGroup2 <em>Group2</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.DisciplineImpl#getTask <em>Task</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.DisciplineImpl#getSubDiscipline <em>Sub Discipline</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.DisciplineImpl#getReferenceWorkflow <em>Reference Workflow</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DisciplineImpl extends ContentCategoryImpl implements Discipline {
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
	protected DisciplineImpl() {
		super();
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
	public FeatureMap getGroup2() {
		if (group2 == null) {
			group2 = new BasicFeatureMap(this, UmaPackage.DISCIPLINE__GROUP2);
		}
		return group2;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getTask() {
		return getGroup2().list(UmaPackage.Literals.DISCIPLINE__TASK);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Discipline> getSubDiscipline() {
		return getGroup2().list(UmaPackage.Literals.DISCIPLINE__SUB_DISCIPLINE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getReferenceWorkflow() {
		return getGroup2().list(UmaPackage.Literals.DISCIPLINE__REFERENCE_WORKFLOW);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UmaPackage.DISCIPLINE__GROUP2:
				return ((InternalEList<?>)getGroup2()).basicRemove(otherEnd, msgs);
			case UmaPackage.DISCIPLINE__SUB_DISCIPLINE:
				return ((InternalEList<?>)getSubDiscipline()).basicRemove(otherEnd, msgs);
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
			case UmaPackage.DISCIPLINE__GROUP2:
				if (coreType) return getGroup2();
				return ((FeatureMap.Internal)getGroup2()).getWrapper();
			case UmaPackage.DISCIPLINE__TASK:
				return getTask();
			case UmaPackage.DISCIPLINE__SUB_DISCIPLINE:
				return getSubDiscipline();
			case UmaPackage.DISCIPLINE__REFERENCE_WORKFLOW:
				return getReferenceWorkflow();
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
			case UmaPackage.DISCIPLINE__GROUP2:
				((FeatureMap.Internal)getGroup2()).set(newValue);
				return;
			case UmaPackage.DISCIPLINE__TASK:
				getTask().clear();
				getTask().addAll((Collection<? extends String>)newValue);
				return;
			case UmaPackage.DISCIPLINE__SUB_DISCIPLINE:
				getSubDiscipline().clear();
				getSubDiscipline().addAll((Collection<? extends Discipline>)newValue);
				return;
			case UmaPackage.DISCIPLINE__REFERENCE_WORKFLOW:
				getReferenceWorkflow().clear();
				getReferenceWorkflow().addAll((Collection<? extends String>)newValue);
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
			case UmaPackage.DISCIPLINE__GROUP2:
				getGroup2().clear();
				return;
			case UmaPackage.DISCIPLINE__TASK:
				getTask().clear();
				return;
			case UmaPackage.DISCIPLINE__SUB_DISCIPLINE:
				getSubDiscipline().clear();
				return;
			case UmaPackage.DISCIPLINE__REFERENCE_WORKFLOW:
				getReferenceWorkflow().clear();
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
			case UmaPackage.DISCIPLINE__GROUP2:
				return group2 != null && !group2.isEmpty();
			case UmaPackage.DISCIPLINE__TASK:
				return !getTask().isEmpty();
			case UmaPackage.DISCIPLINE__SUB_DISCIPLINE:
				return !getSubDiscipline().isEmpty();
			case UmaPackage.DISCIPLINE__REFERENCE_WORKFLOW:
				return !getReferenceWorkflow().isEmpty();
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

} //DisciplineImpl
