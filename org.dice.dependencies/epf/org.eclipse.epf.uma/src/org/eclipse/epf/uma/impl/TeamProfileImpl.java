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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.TeamProfile;
import org.eclipse.epf.uma.UmaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Team Profile</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.impl.TeamProfileImpl#getTeamRoles <em>Team Roles</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.TeamProfileImpl#getSuperTeam <em>Super Team</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.TeamProfileImpl#getSubTeam <em>Sub Team</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TeamProfileImpl extends BreakdownElementImpl implements
		TeamProfile {
	/**
	 * The cached value of the '{@link #getTeamRoles() <em>Team Roles</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTeamRoles()
	 * @generated
	 * @ordered
	 */
	protected EList<RoleDescriptor> teamRoles;

	/**
	 * The cached value of the '{@link #getSuperTeam() <em>Super Team</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSuperTeam()
	 * @generated
	 * @ordered
	 */
	protected TeamProfile superTeam;

	/**
	 * The cached value of the '{@link #getSubTeam() <em>Sub Team</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubTeam()
	 * @generated
	 * @ordered
	 */
	protected EList<TeamProfile> subTeam;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TeamProfileImpl() {
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
		return UmaPackage.Literals.TEAM_PROFILE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<RoleDescriptor> getTeamRoles() {
		if (teamRoles == null) {
			teamRoles = new EObjectResolvingEList<RoleDescriptor>(
					RoleDescriptor.class, this,
					UmaPackage.TEAM_PROFILE__TEAM_ROLES);
		}
		return teamRoles;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TeamProfile getSuperTeam() {
		if (superTeam != null && ((EObject) superTeam).eIsProxy()) {
			InternalEObject oldSuperTeam = (InternalEObject) superTeam;
			superTeam = (TeamProfile) eResolveProxy(oldSuperTeam);
			if (superTeam != oldSuperTeam) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							UmaPackage.TEAM_PROFILE__SUPER_TEAM, oldSuperTeam,
							superTeam));
			}
		}
		return superTeam;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TeamProfile basicGetSuperTeam() {
		return superTeam;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSuperTeam(TeamProfile newSuperTeam,
			NotificationChain msgs) {
		TeamProfile oldSuperTeam = superTeam;
		superTeam = newSuperTeam;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
					Notification.SET, UmaPackage.TEAM_PROFILE__SUPER_TEAM,
					oldSuperTeam, newSuperTeam);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSuperTeam(TeamProfile newSuperTeam) {
		if (newSuperTeam != superTeam) {
			NotificationChain msgs = null;
			if (superTeam != null)
				msgs = ((InternalEObject) superTeam).eInverseRemove(this,
						UmaPackage.TEAM_PROFILE__SUB_TEAM, TeamProfile.class,
						msgs);
			if (newSuperTeam != null)
				msgs = ((InternalEObject) newSuperTeam).eInverseAdd(this,
						UmaPackage.TEAM_PROFILE__SUB_TEAM, TeamProfile.class,
						msgs);
			msgs = basicSetSuperTeam(newSuperTeam, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.TEAM_PROFILE__SUPER_TEAM, newSuperTeam,
					newSuperTeam));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<TeamProfile> getSubTeam() {
		if (subTeam == null) {
			subTeam = new EObjectWithInverseResolvingEList<TeamProfile>(
					TeamProfile.class, this, UmaPackage.TEAM_PROFILE__SUB_TEAM,
					UmaPackage.TEAM_PROFILE__SUPER_TEAM);
		}
		return subTeam;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
		case UmaPackage.TEAM_PROFILE__SUPER_TEAM:
			if (superTeam != null)
				msgs = ((InternalEObject) superTeam).eInverseRemove(this,
						UmaPackage.TEAM_PROFILE__SUB_TEAM, TeamProfile.class,
						msgs);
			return basicSetSuperTeam((TeamProfile) otherEnd, msgs);
		case UmaPackage.TEAM_PROFILE__SUB_TEAM:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getSubTeam())
					.basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
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
		case UmaPackage.TEAM_PROFILE__SUPER_TEAM:
			return basicSetSuperTeam(null, msgs);
		case UmaPackage.TEAM_PROFILE__SUB_TEAM:
			return ((InternalEList<?>) getSubTeam())
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
		case UmaPackage.TEAM_PROFILE__TEAM_ROLES:
			return getTeamRoles();
		case UmaPackage.TEAM_PROFILE__SUPER_TEAM:
			if (resolve)
				return getSuperTeam();
			return basicGetSuperTeam();
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
		case UmaPackage.TEAM_PROFILE__TEAM_ROLES:
			getTeamRoles().clear();
			getTeamRoles().addAll(
					(Collection<? extends RoleDescriptor>) newValue);
			return;
		case UmaPackage.TEAM_PROFILE__SUPER_TEAM:
			setSuperTeam((TeamProfile) newValue);
			return;
		case UmaPackage.TEAM_PROFILE__SUB_TEAM:
			getSubTeam().clear();
			getSubTeam().addAll((Collection<? extends TeamProfile>) newValue);
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
		case UmaPackage.TEAM_PROFILE__TEAM_ROLES:
			getTeamRoles().clear();
			return;
		case UmaPackage.TEAM_PROFILE__SUPER_TEAM:
			setSuperTeam((TeamProfile) null);
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
		//UMA-->
		EStructuralFeature feature = getFeatureWithOverridenDefaultValue(featureID);
		if (feature != null) {
			return isFeatureWithOverridenDefaultValueSet(feature);
		}
		//UMA<--		
		switch (featureID) {
		case UmaPackage.TEAM_PROFILE__TEAM_ROLES:
			return teamRoles != null && !teamRoles.isEmpty();
		case UmaPackage.TEAM_PROFILE__SUPER_TEAM:
			return superTeam != null;
		case UmaPackage.TEAM_PROFILE__SUB_TEAM:
			return subTeam != null && !subTeam.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //TeamProfileImpl
