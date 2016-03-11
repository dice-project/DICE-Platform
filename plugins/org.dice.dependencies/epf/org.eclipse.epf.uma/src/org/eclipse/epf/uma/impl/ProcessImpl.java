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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.epf.uma.CapabilityPattern;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.UmaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Process</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.impl.ProcessImpl#getIncludesPatterns <em>Includes Patterns</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.ProcessImpl#getDefaultContext <em>Default Context</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.ProcessImpl#getValidContext <em>Valid Context</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ProcessImpl extends ActivityImpl implements
		org.eclipse.epf.uma.Process {
	/**
	 * The cached value of the '{@link #getIncludesPatterns() <em>Includes Patterns</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIncludesPatterns()
	 * @generated
	 * @ordered
	 */
	protected EList<CapabilityPattern> includesPatterns;

	/**
	 * The cached value of the '{@link #getDefaultContext() <em>Default Context</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultContext()
	 * @generated
	 * @ordered
	 */
	protected MethodConfiguration defaultContext;

	/**
	 * The cached value of the '{@link #getValidContext() <em>Valid Context</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValidContext()
	 * @generated
	 * @ordered
	 */
	protected EList<MethodConfiguration> validContext;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProcessImpl() {
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
		return UmaPackage.Literals.PROCESS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<CapabilityPattern> getIncludesPatterns() {
		if (includesPatterns == null) {
			includesPatterns = new EObjectResolvingEList<CapabilityPattern>(
					CapabilityPattern.class, this,
					UmaPackage.PROCESS__INCLUDES_PATTERNS);
		}
		return includesPatterns;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MethodConfiguration getDefaultContext() {
		if (defaultContext != null && ((EObject) defaultContext).eIsProxy()) {
			InternalEObject oldDefaultContext = (InternalEObject) defaultContext;
			defaultContext = (MethodConfiguration) eResolveProxy(oldDefaultContext);
			if (defaultContext != oldDefaultContext) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							UmaPackage.PROCESS__DEFAULT_CONTEXT,
							oldDefaultContext, defaultContext));
			}
		}
		return defaultContext;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MethodConfiguration basicGetDefaultContext() {
		return defaultContext;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefaultContext(MethodConfiguration newDefaultContext) {
		MethodConfiguration oldDefaultContext = defaultContext;
		defaultContext = newDefaultContext;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.PROCESS__DEFAULT_CONTEXT, oldDefaultContext,
					defaultContext));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<MethodConfiguration> getValidContext() {
		if (validContext == null) {
			validContext = new EObjectResolvingEList<MethodConfiguration>(
					MethodConfiguration.class, this,
					UmaPackage.PROCESS__VALID_CONTEXT);
		}
		return validContext;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case UmaPackage.PROCESS__INCLUDES_PATTERNS:
			return getIncludesPatterns();
		case UmaPackage.PROCESS__DEFAULT_CONTEXT:
			if (resolve)
				return getDefaultContext();
			return basicGetDefaultContext();
		case UmaPackage.PROCESS__VALID_CONTEXT:
			return getValidContext();
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
		case UmaPackage.PROCESS__INCLUDES_PATTERNS:
			getIncludesPatterns().clear();
			getIncludesPatterns().addAll(
					(Collection<? extends CapabilityPattern>) newValue);
			return;
		case UmaPackage.PROCESS__DEFAULT_CONTEXT:
			setDefaultContext((MethodConfiguration) newValue);
			return;
		case UmaPackage.PROCESS__VALID_CONTEXT:
			getValidContext().clear();
			getValidContext().addAll(
					(Collection<? extends MethodConfiguration>) newValue);
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
		case UmaPackage.PROCESS__INCLUDES_PATTERNS:
			getIncludesPatterns().clear();
			return;
		case UmaPackage.PROCESS__DEFAULT_CONTEXT:
			setDefaultContext((MethodConfiguration) null);
			return;
		case UmaPackage.PROCESS__VALID_CONTEXT:
			getValidContext().clear();
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
		case UmaPackage.PROCESS__INCLUDES_PATTERNS:
			return includesPatterns != null && !includesPatterns.isEmpty();
		case UmaPackage.PROCESS__DEFAULT_CONTEXT:
			return defaultContext != null;
		case UmaPackage.PROCESS__VALID_CONTEXT:
			return validContext != null && !validContext.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ProcessImpl
