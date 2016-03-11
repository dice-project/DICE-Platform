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
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.Guidance;
import org.eclipse.epf.uma.UmaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Descriptor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.impl.DescriptorImpl#getIsSynchronizedWithSource <em>Is Synchronized With Source</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.DescriptorImpl#getGuidanceExclude <em>Guidance Exclude</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.DescriptorImpl#getGuidanceAdditional <em>Guidance Additional</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class DescriptorImpl extends BreakdownElementImpl implements
		Descriptor {
	/**
	 * The default value of the '{@link #getIsSynchronizedWithSource() <em>Is Synchronized With Source</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsSynchronizedWithSource()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_SYNCHRONIZED_WITH_SOURCE_EDEFAULT = Boolean.TRUE;

	/**
	 * The cached value of the '{@link #getIsSynchronizedWithSource() <em>Is Synchronized With Source</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsSynchronizedWithSource()
	 * @generated
	 * @ordered
	 */
	protected Boolean isSynchronizedWithSource = IS_SYNCHRONIZED_WITH_SOURCE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getGuidanceExclude() <em>Guidance Exclude</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGuidanceExclude()
	 * @generated
	 * @ordered
	 */
	protected EList<Guidance> guidanceExclude;

	/**
	 * The cached value of the '{@link #getGuidanceAdditional() <em>Guidance Additional</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGuidanceAdditional()
	 * @generated
	 * @ordered
	 */
	protected EList<Guidance> guidanceAdditional;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DescriptorImpl() {
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
		return UmaPackage.Literals.DESCRIPTOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getIsSynchronizedWithSource() {
		return isSynchronizedWithSource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsSynchronizedWithSource(Boolean newIsSynchronizedWithSource) {
		Boolean oldIsSynchronizedWithSource = isSynchronizedWithSource;
		isSynchronizedWithSource = newIsSynchronizedWithSource;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.DESCRIPTOR__IS_SYNCHRONIZED_WITH_SOURCE,
					oldIsSynchronizedWithSource, isSynchronizedWithSource));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Guidance> getGuidanceExclude() {
		if (guidanceExclude == null) {
			guidanceExclude = new EObjectResolvingEList<Guidance>(
					Guidance.class, this,
					UmaPackage.DESCRIPTOR__GUIDANCE_EXCLUDE);
		}
		return guidanceExclude;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Guidance> getGuidanceAdditional() {
		if (guidanceAdditional == null) {
			guidanceAdditional = new EObjectResolvingEList<Guidance>(
					Guidance.class, this,
					UmaPackage.DESCRIPTOR__GUIDANCE_ADDITIONAL);
		}
		return guidanceAdditional;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case UmaPackage.DESCRIPTOR__IS_SYNCHRONIZED_WITH_SOURCE:
			return getIsSynchronizedWithSource();
		case UmaPackage.DESCRIPTOR__GUIDANCE_EXCLUDE:
			return getGuidanceExclude();
		case UmaPackage.DESCRIPTOR__GUIDANCE_ADDITIONAL:
			return getGuidanceAdditional();
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
		case UmaPackage.DESCRIPTOR__IS_SYNCHRONIZED_WITH_SOURCE:
			setIsSynchronizedWithSource((Boolean) newValue);
			return;
		case UmaPackage.DESCRIPTOR__GUIDANCE_EXCLUDE:
			getGuidanceExclude().clear();
			getGuidanceExclude().addAll(
					(Collection<? extends Guidance>) newValue);
			return;
		case UmaPackage.DESCRIPTOR__GUIDANCE_ADDITIONAL:
			getGuidanceAdditional().clear();
			getGuidanceAdditional().addAll(
					(Collection<? extends Guidance>) newValue);
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
		case UmaPackage.DESCRIPTOR__IS_SYNCHRONIZED_WITH_SOURCE:
			setIsSynchronizedWithSource(IS_SYNCHRONIZED_WITH_SOURCE_EDEFAULT);
			return;
		case UmaPackage.DESCRIPTOR__GUIDANCE_EXCLUDE:
			getGuidanceExclude().clear();
			return;
		case UmaPackage.DESCRIPTOR__GUIDANCE_ADDITIONAL:
			getGuidanceAdditional().clear();
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
		case UmaPackage.DESCRIPTOR__IS_SYNCHRONIZED_WITH_SOURCE:
			return IS_SYNCHRONIZED_WITH_SOURCE_EDEFAULT == null ? isSynchronizedWithSource != null
					: !IS_SYNCHRONIZED_WITH_SOURCE_EDEFAULT
							.equals(isSynchronizedWithSource);
		case UmaPackage.DESCRIPTOR__GUIDANCE_EXCLUDE:
			return guidanceExclude != null && !guidanceExclude.isEmpty();
		case UmaPackage.DESCRIPTOR__GUIDANCE_ADDITIONAL:
			return guidanceAdditional != null && !guidanceAdditional.isEmpty();
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
		if (eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (isSynchronizedWithSource: "); //$NON-NLS-1$
		result.append(isSynchronizedWithSource);
		result.append(')');
		return result.toString();
	}

} //DescriptorImpl
