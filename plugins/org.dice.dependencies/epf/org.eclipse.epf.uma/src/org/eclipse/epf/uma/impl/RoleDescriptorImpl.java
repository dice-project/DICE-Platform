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
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.BasicInternalEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.WorkProductDescriptor;
import org.eclipse.epf.uma.util.AssociationHelper;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Role Descriptor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.impl.RoleDescriptorImpl#getRole <em>Role</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.RoleDescriptorImpl#getModifies <em>Modifies</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.RoleDescriptorImpl#getResponsibleFor <em>Responsible For</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.RoleDescriptorImpl#getResponsibleForExclude <em>Responsible For Exclude</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RoleDescriptorImpl extends DescriptorImpl implements
		RoleDescriptor {
	/**
	 * The cached value of the '{@link #getRole() <em>Role</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRole()
	 * @generated
	 * @ordered
	 */
	protected Role role;

	/**
	 * The cached value of the '{@link #getResponsibleFor() <em>Responsible For</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResponsibleFor()
	 * @generated
	 * @ordered
	 */
	protected EList<WorkProductDescriptor> responsibleFor;

	/**
	 * The cached value of the '{@link #getResponsibleForExclude() <em>Responsible For Exclude</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResponsibleForExclude()
	 * @generated
	 * @ordered
	 */
	protected EList<WorkProduct> responsibleForExclude;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RoleDescriptorImpl() {
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
		return UmaPackage.Literals.ROLE_DESCRIPTOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Role getRole() {
		if (role != null && ((EObject) role).eIsProxy()) {
			InternalEObject oldRole = (InternalEObject) role;
			role = (Role) eResolveProxy(oldRole);
			if (role != oldRole) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							UmaPackage.ROLE_DESCRIPTOR__ROLE, oldRole, role));
			}
		}
		return role;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Role basicGetRole() {
		return role;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRole(Role newRole) {
		Role oldRole = role;
		role = newRole;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.ROLE_DESCRIPTOR__ROLE, oldRole, role));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public List getModifies() {
		List performs = AssociationHelper.getPrimaryTaskDescriptors(this);
		InternalEList modifies = new BasicInternalEList(
				WorkProductDescriptor.class);
		if (performs != null && performs.size() > 0) {
			for (Iterator i = performs.iterator(); i.hasNext();) {
				TaskDescriptor td = (TaskDescriptor) i.next();

				// for each task, get output work product;
				List outputWPs = td.getOutput();
				if (outputWPs != null && outputWPs.size() > 0) {
					for (Iterator it = outputWPs.iterator(); it.hasNext();) {
						WorkProductDescriptor wpd = (WorkProductDescriptor) it
								.next();
						if (wpd != null) {
							if (!modifies.contains(wpd)) {
								modifies.add(wpd);
							}
						}
					}
				}
			}
		}
		return modifies;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<WorkProductDescriptor> getResponsibleFor() {
		if (responsibleFor == null) {
			responsibleFor = new EObjectResolvingEList<WorkProductDescriptor>(
					WorkProductDescriptor.class, this,
					UmaPackage.ROLE_DESCRIPTOR__RESPONSIBLE_FOR);
		}
		return responsibleFor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<WorkProduct> getResponsibleForExclude() {
		if (responsibleForExclude == null) {
			responsibleForExclude = new EObjectResolvingEList<WorkProduct>(
					WorkProduct.class, this,
					UmaPackage.ROLE_DESCRIPTOR__RESPONSIBLE_FOR_EXCLUDE);
		}
		return responsibleForExclude;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case UmaPackage.ROLE_DESCRIPTOR__ROLE:
			if (resolve)
				return getRole();
			return basicGetRole();
		case UmaPackage.ROLE_DESCRIPTOR__MODIFIES:
			return getModifies();
		case UmaPackage.ROLE_DESCRIPTOR__RESPONSIBLE_FOR:
			return getResponsibleFor();
		case UmaPackage.ROLE_DESCRIPTOR__RESPONSIBLE_FOR_EXCLUDE:
			return getResponsibleForExclude();
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
		case UmaPackage.ROLE_DESCRIPTOR__ROLE:
			setRole((Role) newValue);
			return;
		case UmaPackage.ROLE_DESCRIPTOR__MODIFIES:
			getModifies().clear();
			getModifies().addAll(
					(Collection<? extends WorkProductDescriptor>) newValue);
			return;
		case UmaPackage.ROLE_DESCRIPTOR__RESPONSIBLE_FOR:
			getResponsibleFor().clear();
			getResponsibleFor().addAll(
					(Collection<? extends WorkProductDescriptor>) newValue);
			return;
		case UmaPackage.ROLE_DESCRIPTOR__RESPONSIBLE_FOR_EXCLUDE:
			getResponsibleForExclude().clear();
			getResponsibleForExclude().addAll(
					(Collection<? extends WorkProduct>) newValue);
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
		case UmaPackage.ROLE_DESCRIPTOR__ROLE:
			setRole((Role) null);
			return;
		case UmaPackage.ROLE_DESCRIPTOR__MODIFIES:
			getModifies().clear();
			return;
		case UmaPackage.ROLE_DESCRIPTOR__RESPONSIBLE_FOR:
			getResponsibleFor().clear();
			return;
		case UmaPackage.ROLE_DESCRIPTOR__RESPONSIBLE_FOR_EXCLUDE:
			getResponsibleForExclude().clear();
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
		case UmaPackage.ROLE_DESCRIPTOR__ROLE:
			return role != null;
		case UmaPackage.ROLE_DESCRIPTOR__MODIFIES:
			return !getModifies().isEmpty();
		case UmaPackage.ROLE_DESCRIPTOR__RESPONSIBLE_FOR:
			return responsibleFor != null && !responsibleFor.isEmpty();
		case UmaPackage.ROLE_DESCRIPTOR__RESPONSIBLE_FOR_EXCLUDE:
			return responsibleForExclude != null
					&& !responsibleForExclude.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //RoleDescriptorImpl
