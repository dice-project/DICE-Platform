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
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.Checklist;
import org.eclipse.epf.uma.Concept;
import org.eclipse.epf.uma.Constraint;
import org.eclipse.epf.uma.FulfillableElement;
import org.eclipse.epf.uma.Example;
import org.eclipse.epf.uma.Guideline;
import org.eclipse.epf.uma.ReusableAsset;
import org.eclipse.epf.uma.Roadmap;
import org.eclipse.epf.uma.SupportingMaterial;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.VariabilityType;
import org.eclipse.epf.uma.WorkDefinition;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Activity</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.impl.ActivityImpl#getFulfills <em>Fulfills</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.ActivityImpl#getVariabilityType <em>Variability Type</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.ActivityImpl#getVariabilityBasedOnElement <em>Variability Based On Element</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.ActivityImpl#getPrecondition <em>Precondition</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.ActivityImpl#getPostcondition <em>Postcondition</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.ActivityImpl#getBreakdownElements <em>Breakdown Elements</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.ActivityImpl#getRoadmaps <em>Roadmaps</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ActivityImpl extends WorkBreakdownElementImpl implements Activity {
	/**
	 * The cached value of the '{@link #getFulfills() <em>Fulfills</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFulfills()
	 * @generated
	 * @ordered
	 */
	protected EList<FulfillableElement> fulfills;

	/**
	 * The default value of the '{@link #getVariabilityType() <em>Variability Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVariabilityType()
	 * @generated
	 * @ordered
	 */
	protected static final VariabilityType VARIABILITY_TYPE_EDEFAULT = VariabilityType.NA;

	/**
	 * The cached value of the '{@link #getVariabilityType() <em>Variability Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVariabilityType()
	 * @generated
	 * @ordered
	 */
	protected VariabilityType variabilityType = VARIABILITY_TYPE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getVariabilityBasedOnElement() <em>Variability Based On Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVariabilityBasedOnElement()
	 * @generated
	 * @ordered
	 */
	protected VariabilityElement variabilityBasedOnElement;

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
	 * The cached value of the '{@link #getBreakdownElements() <em>Breakdown Elements</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBreakdownElements()
	 * @generated
	 * @ordered
	 */
	protected EList<BreakdownElement> breakdownElements;

	/**
	 * The cached value of the '{@link #getRoadmaps() <em>Roadmaps</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRoadmaps()
	 * @generated
	 * @ordered
	 */
	protected EList<Roadmap> roadmaps;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ActivityImpl() {
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
		return UmaPackage.Literals.ACTIVITY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<FulfillableElement> getFulfills() {
		if (fulfills == null) {
			fulfills = new EObjectResolvingEList<FulfillableElement>(
					FulfillableElement.class, this,
					UmaPackage.ACTIVITY__FULFILLS);
		}
		return fulfills;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariabilityType getVariabilityType() {
		return variabilityType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVariabilityType(VariabilityType newVariabilityType) {
		VariabilityType oldVariabilityType = variabilityType;
		variabilityType = newVariabilityType == null ? VARIABILITY_TYPE_EDEFAULT
				: newVariabilityType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.ACTIVITY__VARIABILITY_TYPE, oldVariabilityType,
					variabilityType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariabilityElement getVariabilityBasedOnElement() {
		if (variabilityBasedOnElement != null
				&& ((EObject) variabilityBasedOnElement).eIsProxy()) {
			InternalEObject oldVariabilityBasedOnElement = (InternalEObject) variabilityBasedOnElement;
			variabilityBasedOnElement = (VariabilityElement) eResolveProxy(oldVariabilityBasedOnElement);
			if (variabilityBasedOnElement != oldVariabilityBasedOnElement) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							UmaPackage.ACTIVITY__VARIABILITY_BASED_ON_ELEMENT,
							oldVariabilityBasedOnElement,
							variabilityBasedOnElement));
			}
		}
		return variabilityBasedOnElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariabilityElement basicGetVariabilityBasedOnElement() {
		return variabilityBasedOnElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVariabilityBasedOnElement(
			VariabilityElement newVariabilityBasedOnElement) {
		VariabilityElement oldVariabilityBasedOnElement = variabilityBasedOnElement;
		variabilityBasedOnElement = newVariabilityBasedOnElement;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.ACTIVITY__VARIABILITY_BASED_ON_ELEMENT,
					oldVariabilityBasedOnElement, variabilityBasedOnElement));
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
				NotificationChain msgs = oldPrecondition
						.eInverseRemove(this, EOPPOSITE_FEATURE_BASE
								- UmaPackage.ACTIVITY__PRECONDITION, null, null);
				if (newPrecondition.eInternalContainer() == null) {
					msgs = newPrecondition.eInverseAdd(this,
							EOPPOSITE_FEATURE_BASE
									- UmaPackage.ACTIVITY__PRECONDITION, null,
							msgs);
				}
				if (msgs != null)
					msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							UmaPackage.ACTIVITY__PRECONDITION, oldPrecondition,
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
					Notification.SET, UmaPackage.ACTIVITY__PRECONDITION,
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
				msgs = ((InternalEObject) precondition)
						.eInverseRemove(this, EOPPOSITE_FEATURE_BASE
								- UmaPackage.ACTIVITY__PRECONDITION, null, msgs);
			if (newPrecondition != null)
				msgs = ((InternalEObject) newPrecondition)
						.eInverseAdd(this, EOPPOSITE_FEATURE_BASE
								- UmaPackage.ACTIVITY__PRECONDITION, null, msgs);
			msgs = basicSetPrecondition(newPrecondition, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.ACTIVITY__PRECONDITION, newPrecondition,
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
				NotificationChain msgs = oldPostcondition.eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE
								- UmaPackage.ACTIVITY__POSTCONDITION, null,
						null);
				if (newPostcondition.eInternalContainer() == null) {
					msgs = newPostcondition.eInverseAdd(this,
							EOPPOSITE_FEATURE_BASE
									- UmaPackage.ACTIVITY__POSTCONDITION, null,
							msgs);
				}
				if (msgs != null)
					msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							UmaPackage.ACTIVITY__POSTCONDITION,
							oldPostcondition, postcondition));
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
					Notification.SET, UmaPackage.ACTIVITY__POSTCONDITION,
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
				msgs = ((InternalEObject) postcondition).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE
								- UmaPackage.ACTIVITY__POSTCONDITION, null,
						msgs);
			if (newPostcondition != null)
				msgs = ((InternalEObject) newPostcondition).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE
								- UmaPackage.ACTIVITY__POSTCONDITION, null,
						msgs);
			msgs = basicSetPostcondition(newPostcondition, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.ACTIVITY__POSTCONDITION, newPostcondition,
					newPostcondition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<BreakdownElement> getBreakdownElements() {
		if (breakdownElements == null) {
			breakdownElements = new EObjectWithInverseResolvingEList<BreakdownElement>(
					BreakdownElement.class, this,
					UmaPackage.ACTIVITY__BREAKDOWN_ELEMENTS,
					UmaPackage.BREAKDOWN_ELEMENT__SUPER_ACTIVITIES);
		}
		return breakdownElements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Roadmap> getRoadmaps() {
		if (roadmaps == null) {
			roadmaps = new EObjectResolvingEList<Roadmap>(Roadmap.class, this,
					UmaPackage.ACTIVITY__ROADMAPS);
		}
		return roadmaps;
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
		case UmaPackage.ACTIVITY__BREAKDOWN_ELEMENTS:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getBreakdownElements())
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
		case UmaPackage.ACTIVITY__PRECONDITION:
			return basicSetPrecondition(null, msgs);
		case UmaPackage.ACTIVITY__POSTCONDITION:
			return basicSetPostcondition(null, msgs);
		case UmaPackage.ACTIVITY__BREAKDOWN_ELEMENTS:
			return ((InternalEList<?>) getBreakdownElements()).basicRemove(
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
		case UmaPackage.ACTIVITY__FULFILLS:
			return getFulfills();
		case UmaPackage.ACTIVITY__VARIABILITY_TYPE:
			return getVariabilityType();
		case UmaPackage.ACTIVITY__VARIABILITY_BASED_ON_ELEMENT:
			if (resolve)
				return getVariabilityBasedOnElement();
			return basicGetVariabilityBasedOnElement();
		case UmaPackage.ACTIVITY__PRECONDITION:
			if (resolve)
				return getPrecondition();
			return basicGetPrecondition();
		case UmaPackage.ACTIVITY__POSTCONDITION:
			if (resolve)
				return getPostcondition();
			return basicGetPostcondition();
		case UmaPackage.ACTIVITY__BREAKDOWN_ELEMENTS:
			return getBreakdownElements();
		case UmaPackage.ACTIVITY__ROADMAPS:
			return getRoadmaps();
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
		case UmaPackage.ACTIVITY__FULFILLS:
			getFulfills().clear();
			getFulfills().addAll(
					(Collection<? extends FulfillableElement>) newValue);
			return;
		case UmaPackage.ACTIVITY__VARIABILITY_TYPE:
			setVariabilityType((VariabilityType) newValue);
			return;
		case UmaPackage.ACTIVITY__VARIABILITY_BASED_ON_ELEMENT:
			setVariabilityBasedOnElement((VariabilityElement) newValue);
			return;
		case UmaPackage.ACTIVITY__PRECONDITION:
			setPrecondition((Constraint) newValue);
			return;
		case UmaPackage.ACTIVITY__POSTCONDITION:
			setPostcondition((Constraint) newValue);
			return;
		case UmaPackage.ACTIVITY__BREAKDOWN_ELEMENTS:
			getBreakdownElements().clear();
			getBreakdownElements().addAll(
					(Collection<? extends BreakdownElement>) newValue);
			return;
		case UmaPackage.ACTIVITY__ROADMAPS:
			getRoadmaps().clear();
			getRoadmaps().addAll((Collection<? extends Roadmap>) newValue);
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
		case UmaPackage.ACTIVITY__FULFILLS:
			getFulfills().clear();
			return;
		case UmaPackage.ACTIVITY__VARIABILITY_TYPE:
			setVariabilityType(VARIABILITY_TYPE_EDEFAULT);
			return;
		case UmaPackage.ACTIVITY__VARIABILITY_BASED_ON_ELEMENT:
			setVariabilityBasedOnElement((VariabilityElement) null);
			return;
		case UmaPackage.ACTIVITY__PRECONDITION:
			setPrecondition((Constraint) null);
			return;
		case UmaPackage.ACTIVITY__POSTCONDITION:
			setPostcondition((Constraint) null);
			return;
		case UmaPackage.ACTIVITY__BREAKDOWN_ELEMENTS:
			getBreakdownElements().clear();
			return;
		case UmaPackage.ACTIVITY__ROADMAPS:
			getRoadmaps().clear();
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
		case UmaPackage.ACTIVITY__FULFILLS:
			return fulfills != null && !fulfills.isEmpty();
		case UmaPackage.ACTIVITY__VARIABILITY_TYPE:
			return variabilityType != VARIABILITY_TYPE_EDEFAULT;
		case UmaPackage.ACTIVITY__VARIABILITY_BASED_ON_ELEMENT:
			return variabilityBasedOnElement != null;
		case UmaPackage.ACTIVITY__PRECONDITION:
			return precondition != null;
		case UmaPackage.ACTIVITY__POSTCONDITION:
			return postcondition != null;
		case UmaPackage.ACTIVITY__BREAKDOWN_ELEMENTS:
			return breakdownElements != null && !breakdownElements.isEmpty();
		case UmaPackage.ACTIVITY__ROADMAPS:
			return roadmaps != null && !roadmaps.isEmpty();
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
		if (baseClass == FulfillableElement.class) {
			switch (derivedFeatureID) {
			case UmaPackage.ACTIVITY__FULFILLS:
				return UmaPackage.FULFILLABLE_ELEMENT__FULFILLS;
			default:
				return -1;
			}
		}
		if (baseClass == VariabilityElement.class) {
			switch (derivedFeatureID) {
			case UmaPackage.ACTIVITY__VARIABILITY_TYPE:
				return UmaPackage.VARIABILITY_ELEMENT__VARIABILITY_TYPE;
			case UmaPackage.ACTIVITY__VARIABILITY_BASED_ON_ELEMENT:
				return UmaPackage.VARIABILITY_ELEMENT__VARIABILITY_BASED_ON_ELEMENT;
			default:
				return -1;
			}
		}
		if (baseClass == WorkDefinition.class) {
			switch (derivedFeatureID) {
			case UmaPackage.ACTIVITY__PRECONDITION:
				return UmaPackage.WORK_DEFINITION__PRECONDITION;
			case UmaPackage.ACTIVITY__POSTCONDITION:
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
		if (baseClass == FulfillableElement.class) {
			switch (baseFeatureID) {
			case UmaPackage.FULFILLABLE_ELEMENT__FULFILLS:
				return UmaPackage.ACTIVITY__FULFILLS;
			default:
				return -1;
			}
		}
		if (baseClass == VariabilityElement.class) {
			switch (baseFeatureID) {
			case UmaPackage.VARIABILITY_ELEMENT__VARIABILITY_TYPE:
				return UmaPackage.ACTIVITY__VARIABILITY_TYPE;
			case UmaPackage.VARIABILITY_ELEMENT__VARIABILITY_BASED_ON_ELEMENT:
				return UmaPackage.ACTIVITY__VARIABILITY_BASED_ON_ELEMENT;
			default:
				return -1;
			}
		}
		if (baseClass == WorkDefinition.class) {
			switch (baseFeatureID) {
			case UmaPackage.WORK_DEFINITION__PRECONDITION:
				return UmaPackage.ACTIVITY__PRECONDITION;
			case UmaPackage.WORK_DEFINITION__POSTCONDITION:
				return UmaPackage.ACTIVITY__POSTCONDITION;
			default:
				return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
		result.append(" (variabilityType: "); //$NON-NLS-1$
		result.append(variabilityType);
		result.append(')');
		return result.toString();
	}

} //ActivityImpl
