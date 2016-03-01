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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.epf.uma.Constraint;
import org.eclipse.epf.uma.Step;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.WorkDefinition;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Step</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.impl.StepImpl#getPrecondition <em>Precondition</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.StepImpl#getPostcondition <em>Postcondition</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StepImpl extends SectionImpl implements Step {
	/**
	 * The cached value of the '{@link #getPrecondition() <em>Precondition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrecondition()
	 * @generated
	 * @ordered
	 */
	protected Constraint precondition;

	/**
	 * The cached value of the '{@link #getPostcondition() <em>Postcondition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPostcondition()
	 * @generated
	 * @ordered
	 */
	protected Constraint postcondition;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StepImpl() {
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
		return UmaPackage.Literals.STEP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Constraint getPrecondition() {
		if (precondition != null && ((EObject) precondition).eIsProxy()) {
			InternalEObject oldPrecondition = (InternalEObject) precondition;
			precondition = (Constraint) eResolveProxy(oldPrecondition);
			if (precondition != oldPrecondition) {
				InternalEObject newPrecondition = (InternalEObject) precondition;
				NotificationChain msgs = oldPrecondition.eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - UmaPackage.STEP__PRECONDITION,
						null, null);
				if (newPrecondition.eInternalContainer() == null) {
					msgs = newPrecondition
							.eInverseAdd(this, EOPPOSITE_FEATURE_BASE
									- UmaPackage.STEP__PRECONDITION, null, msgs);
				}
				if (msgs != null)
					msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							UmaPackage.STEP__PRECONDITION, oldPrecondition,
							precondition));
			}
		}
		return precondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Constraint basicGetPrecondition() {
		return precondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPrecondition(Constraint newPrecondition,
			NotificationChain msgs) {
		Constraint oldPrecondition = precondition;
		precondition = newPrecondition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
					Notification.SET, UmaPackage.STEP__PRECONDITION,
					oldPrecondition, newPrecondition);
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
	public void setPrecondition(Constraint newPrecondition) {
		if (newPrecondition != precondition) {
			NotificationChain msgs = null;
			if (precondition != null)
				msgs = ((InternalEObject) precondition).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - UmaPackage.STEP__PRECONDITION,
						null, msgs);
			if (newPrecondition != null)
				msgs = ((InternalEObject) newPrecondition).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - UmaPackage.STEP__PRECONDITION,
						null, msgs);
			msgs = basicSetPrecondition(newPrecondition, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.STEP__PRECONDITION, newPrecondition,
					newPrecondition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Constraint getPostcondition() {
		if (postcondition != null && ((EObject) postcondition).eIsProxy()) {
			InternalEObject oldPostcondition = (InternalEObject) postcondition;
			postcondition = (Constraint) eResolveProxy(oldPostcondition);
			if (postcondition != oldPostcondition) {
				InternalEObject newPostcondition = (InternalEObject) postcondition;
				NotificationChain msgs = oldPostcondition
						.eInverseRemove(this, EOPPOSITE_FEATURE_BASE
								- UmaPackage.STEP__POSTCONDITION, null, null);
				if (newPostcondition.eInternalContainer() == null) {
					msgs = newPostcondition.eInverseAdd(this,
							EOPPOSITE_FEATURE_BASE
									- UmaPackage.STEP__POSTCONDITION, null,
							msgs);
				}
				if (msgs != null)
					msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							UmaPackage.STEP__POSTCONDITION, oldPostcondition,
							postcondition));
			}
		}
		return postcondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Constraint basicGetPostcondition() {
		return postcondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPostcondition(Constraint newPostcondition,
			NotificationChain msgs) {
		Constraint oldPostcondition = postcondition;
		postcondition = newPostcondition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
					Notification.SET, UmaPackage.STEP__POSTCONDITION,
					oldPostcondition, newPostcondition);
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
	public void setPostcondition(Constraint newPostcondition) {
		if (newPostcondition != postcondition) {
			NotificationChain msgs = null;
			if (postcondition != null)
				msgs = ((InternalEObject) postcondition)
						.eInverseRemove(this, EOPPOSITE_FEATURE_BASE
								- UmaPackage.STEP__POSTCONDITION, null, msgs);
			if (newPostcondition != null)
				msgs = ((InternalEObject) newPostcondition)
						.eInverseAdd(this, EOPPOSITE_FEATURE_BASE
								- UmaPackage.STEP__POSTCONDITION, null, msgs);
			msgs = basicSetPostcondition(newPostcondition, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.STEP__POSTCONDITION, newPostcondition,
					newPostcondition));
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
		case UmaPackage.STEP__PRECONDITION:
			return basicSetPrecondition(null, msgs);
		case UmaPackage.STEP__POSTCONDITION:
			return basicSetPostcondition(null, msgs);
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
		case UmaPackage.STEP__PRECONDITION:
			if (resolve)
				return getPrecondition();
			return basicGetPrecondition();
		case UmaPackage.STEP__POSTCONDITION:
			if (resolve)
				return getPostcondition();
			return basicGetPostcondition();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case UmaPackage.STEP__PRECONDITION:
			setPrecondition((Constraint) newValue);
			return;
		case UmaPackage.STEP__POSTCONDITION:
			setPostcondition((Constraint) newValue);
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
		case UmaPackage.STEP__PRECONDITION:
			setPrecondition((Constraint) null);
			return;
		case UmaPackage.STEP__POSTCONDITION:
			setPostcondition((Constraint) null);
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
		case UmaPackage.STEP__PRECONDITION:
			return precondition != null;
		case UmaPackage.STEP__POSTCONDITION:
			return postcondition != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == WorkDefinition.class) {
			switch (derivedFeatureID) {
			case UmaPackage.STEP__PRECONDITION:
				return UmaPackage.WORK_DEFINITION__PRECONDITION;
			case UmaPackage.STEP__POSTCONDITION:
				return UmaPackage.WORK_DEFINITION__POSTCONDITION;
			default:
				return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == WorkDefinition.class) {
			switch (baseFeatureID) {
			case UmaPackage.WORK_DEFINITION__PRECONDITION:
				return UmaPackage.STEP__PRECONDITION;
			case UmaPackage.WORK_DEFINITION__POSTCONDITION:
				return UmaPackage.STEP__POSTCONDITION;
			default:
				return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //StepImpl
