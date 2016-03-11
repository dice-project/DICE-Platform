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
import org.eclipse.epf.xml.uma.TeamProfile;
import org.eclipse.epf.xml.uma.UmaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Team Profile</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.TeamProfileImpl#getGroup2 <em>Group2</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.TeamProfileImpl#getRole <em>Role</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.TeamProfileImpl#getSuperTeam <em>Super Team</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.TeamProfileImpl#getSubTeam <em>Sub Team</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TeamProfileImpl extends BreakdownElementImpl implements TeamProfile {
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
	protected TeamProfileImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UmaPackage.Literals.TEAM_PROFILE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getGroup2() {
		if (group2 == null) {
			group2 = new BasicFeatureMap(this, UmaPackage.TEAM_PROFILE__GROUP2);
		}
		return group2;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getRole() {
		return getGroup2().list(UmaPackage.Literals.TEAM_PROFILE__ROLE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getSuperTeam() {
		return getGroup2().list(UmaPackage.Literals.TEAM_PROFILE__SUPER_TEAM);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getSubTeam() {
		return getGroup2().list(UmaPackage.Literals.TEAM_PROFILE__SUB_TEAM);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UmaPackage.TEAM_PROFILE__GROUP2:
				return ((InternalEList<?>)getGroup2()).basicRemove(otherEnd, msgs);
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
			case UmaPackage.TEAM_PROFILE__GROUP2:
				if (coreType) return getGroup2();
				return ((FeatureMap.Internal)getGroup2()).getWrapper();
			case UmaPackage.TEAM_PROFILE__ROLE:
				return getRole();
			case UmaPackage.TEAM_PROFILE__SUPER_TEAM:
				return getSuperTeam();
			case UmaPackage.TEAM_PROFILE__SUB_TEAM:
				return getSubTeam();
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
			case UmaPackage.TEAM_PROFILE__GROUP2:
				((FeatureMap.Internal)getGroup2()).set(newValue);
				return;
			case UmaPackage.TEAM_PROFILE__ROLE:
				getRole().clear();
				getRole().addAll((Collection<? extends String>)newValue);
				return;
			case UmaPackage.TEAM_PROFILE__SUPER_TEAM:
				getSuperTeam().clear();
				getSuperTeam().addAll((Collection<? extends String>)newValue);
				return;
			case UmaPackage.TEAM_PROFILE__SUB_TEAM:
				getSubTeam().clear();
				getSubTeam().addAll((Collection<? extends String>)newValue);
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
			case UmaPackage.TEAM_PROFILE__GROUP2:
				getGroup2().clear();
				return;
			case UmaPackage.TEAM_PROFILE__ROLE:
				getRole().clear();
				return;
			case UmaPackage.TEAM_PROFILE__SUPER_TEAM:
				getSuperTeam().clear();
				return;
			case UmaPackage.TEAM_PROFILE__SUB_TEAM:
				getSubTeam().clear();
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
			case UmaPackage.TEAM_PROFILE__GROUP2:
				return group2 != null && !group2.isEmpty();
			case UmaPackage.TEAM_PROFILE__ROLE:
				return !getRole().isEmpty();
			case UmaPackage.TEAM_PROFILE__SUPER_TEAM:
				return !getSuperTeam().isEmpty();
			case UmaPackage.TEAM_PROFILE__SUB_TEAM:
				return !getSubTeam().isEmpty();
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

} //TeamProfileImpl
