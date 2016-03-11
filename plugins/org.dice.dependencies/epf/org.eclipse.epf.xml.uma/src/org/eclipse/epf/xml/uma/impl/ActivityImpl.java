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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.epf.xml.uma.Activity;
import org.eclipse.epf.xml.uma.BreakdownElement;
import org.eclipse.epf.xml.uma.UmaPackage;
import org.eclipse.epf.xml.uma.VariabilityType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Activity</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.ActivityImpl#getPrecondition <em>Precondition</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.ActivityImpl#getPostcondition <em>Postcondition</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.ActivityImpl#getGroup3 <em>Group3</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.ActivityImpl#getBreakdownElement <em>Breakdown Element</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.ActivityImpl#getRoadmap <em>Roadmap</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.ActivityImpl#isIsEnactable <em>Is Enactable</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.ActivityImpl#getVariabilityBasedOnElement <em>Variability Based On Element</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.ActivityImpl#getVariabilityType <em>Variability Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ActivityImpl extends WorkBreakdownElementImpl implements Activity {
	/**
	 * The default value of the '{@link #getPrecondition() <em>Precondition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrecondition()
	 * @generated
	 * @ordered
	 */
	protected static final String PRECONDITION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPrecondition() <em>Precondition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrecondition()
	 * @generated
	 * @ordered
	 */
	protected String precondition = PRECONDITION_EDEFAULT;

	/**
	 * The default value of the '{@link #getPostcondition() <em>Postcondition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPostcondition()
	 * @generated
	 * @ordered
	 */
	protected static final String POSTCONDITION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPostcondition() <em>Postcondition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPostcondition()
	 * @generated
	 * @ordered
	 */
	protected String postcondition = POSTCONDITION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getGroup3() <em>Group3</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroup3()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap group3;

	/**
	 * The default value of the '{@link #isIsEnactable() <em>Is Enactable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsEnactable()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_ENACTABLE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsEnactable() <em>Is Enactable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsEnactable()
	 * @generated
	 * @ordered
	 */
	protected boolean isEnactable = IS_ENACTABLE_EDEFAULT;

	/**
	 * This is true if the Is Enactable attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean isEnactableESet;

	/**
	 * The default value of the '{@link #getVariabilityBasedOnElement() <em>Variability Based On Element</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVariabilityBasedOnElement()
	 * @generated
	 * @ordered
	 */
	protected static final String VARIABILITY_BASED_ON_ELEMENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVariabilityBasedOnElement() <em>Variability Based On Element</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVariabilityBasedOnElement()
	 * @generated
	 * @ordered
	 */
	protected String variabilityBasedOnElement = VARIABILITY_BASED_ON_ELEMENT_EDEFAULT;

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
	 * This is true if the Variability Type attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean variabilityTypeESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ActivityImpl() {
		super();
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
	public String getPrecondition() {
		return precondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPrecondition(String newPrecondition) {
		String oldPrecondition = precondition;
		precondition = newPrecondition;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UmaPackage.ACTIVITY__PRECONDITION, oldPrecondition, precondition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPostcondition() {
		return postcondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPostcondition(String newPostcondition) {
		String oldPostcondition = postcondition;
		postcondition = newPostcondition;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UmaPackage.ACTIVITY__POSTCONDITION, oldPostcondition, postcondition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getGroup3() {
		if (group3 == null) {
			group3 = new BasicFeatureMap(this, UmaPackage.ACTIVITY__GROUP3);
		}
		return group3;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<BreakdownElement> getBreakdownElement() {
		return getGroup3().list(UmaPackage.Literals.ACTIVITY__BREAKDOWN_ELEMENT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getRoadmap() {
		return getGroup3().list(UmaPackage.Literals.ACTIVITY__ROADMAP);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsEnactable() {
		return isEnactable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsEnactable(boolean newIsEnactable) {
		boolean oldIsEnactable = isEnactable;
		isEnactable = newIsEnactable;
		boolean oldIsEnactableESet = isEnactableESet;
		isEnactableESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UmaPackage.ACTIVITY__IS_ENACTABLE, oldIsEnactable, isEnactable, !oldIsEnactableESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetIsEnactable() {
		boolean oldIsEnactable = isEnactable;
		boolean oldIsEnactableESet = isEnactableESet;
		isEnactable = IS_ENACTABLE_EDEFAULT;
		isEnactableESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, UmaPackage.ACTIVITY__IS_ENACTABLE, oldIsEnactable, IS_ENACTABLE_EDEFAULT, oldIsEnactableESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetIsEnactable() {
		return isEnactableESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getVariabilityBasedOnElement() {
		return variabilityBasedOnElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVariabilityBasedOnElement(String newVariabilityBasedOnElement) {
		String oldVariabilityBasedOnElement = variabilityBasedOnElement;
		variabilityBasedOnElement = newVariabilityBasedOnElement;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UmaPackage.ACTIVITY__VARIABILITY_BASED_ON_ELEMENT, oldVariabilityBasedOnElement, variabilityBasedOnElement));
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
		variabilityType = newVariabilityType == null ? VARIABILITY_TYPE_EDEFAULT : newVariabilityType;
		boolean oldVariabilityTypeESet = variabilityTypeESet;
		variabilityTypeESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UmaPackage.ACTIVITY__VARIABILITY_TYPE, oldVariabilityType, variabilityType, !oldVariabilityTypeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetVariabilityType() {
		VariabilityType oldVariabilityType = variabilityType;
		boolean oldVariabilityTypeESet = variabilityTypeESet;
		variabilityType = VARIABILITY_TYPE_EDEFAULT;
		variabilityTypeESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, UmaPackage.ACTIVITY__VARIABILITY_TYPE, oldVariabilityType, VARIABILITY_TYPE_EDEFAULT, oldVariabilityTypeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetVariabilityType() {
		return variabilityTypeESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UmaPackage.ACTIVITY__GROUP3:
				return ((InternalEList<?>)getGroup3()).basicRemove(otherEnd, msgs);
			case UmaPackage.ACTIVITY__BREAKDOWN_ELEMENT:
				return ((InternalEList<?>)getBreakdownElement()).basicRemove(otherEnd, msgs);
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
			case UmaPackage.ACTIVITY__PRECONDITION:
				return getPrecondition();
			case UmaPackage.ACTIVITY__POSTCONDITION:
				return getPostcondition();
			case UmaPackage.ACTIVITY__GROUP3:
				if (coreType) return getGroup3();
				return ((FeatureMap.Internal)getGroup3()).getWrapper();
			case UmaPackage.ACTIVITY__BREAKDOWN_ELEMENT:
				return getBreakdownElement();
			case UmaPackage.ACTIVITY__ROADMAP:
				return getRoadmap();
			case UmaPackage.ACTIVITY__IS_ENACTABLE:
				return isIsEnactable() ? Boolean.TRUE : Boolean.FALSE;
			case UmaPackage.ACTIVITY__VARIABILITY_BASED_ON_ELEMENT:
				return getVariabilityBasedOnElement();
			case UmaPackage.ACTIVITY__VARIABILITY_TYPE:
				return getVariabilityType();
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
			case UmaPackage.ACTIVITY__PRECONDITION:
				setPrecondition((String)newValue);
				return;
			case UmaPackage.ACTIVITY__POSTCONDITION:
				setPostcondition((String)newValue);
				return;
			case UmaPackage.ACTIVITY__GROUP3:
				((FeatureMap.Internal)getGroup3()).set(newValue);
				return;
			case UmaPackage.ACTIVITY__BREAKDOWN_ELEMENT:
				getBreakdownElement().clear();
				getBreakdownElement().addAll((Collection<? extends BreakdownElement>)newValue);
				return;
			case UmaPackage.ACTIVITY__ROADMAP:
				getRoadmap().clear();
				getRoadmap().addAll((Collection<? extends String>)newValue);
				return;
			case UmaPackage.ACTIVITY__IS_ENACTABLE:
				setIsEnactable(((Boolean)newValue).booleanValue());
				return;
			case UmaPackage.ACTIVITY__VARIABILITY_BASED_ON_ELEMENT:
				setVariabilityBasedOnElement((String)newValue);
				return;
			case UmaPackage.ACTIVITY__VARIABILITY_TYPE:
				setVariabilityType((VariabilityType)newValue);
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
			case UmaPackage.ACTIVITY__PRECONDITION:
				setPrecondition(PRECONDITION_EDEFAULT);
				return;
			case UmaPackage.ACTIVITY__POSTCONDITION:
				setPostcondition(POSTCONDITION_EDEFAULT);
				return;
			case UmaPackage.ACTIVITY__GROUP3:
				getGroup3().clear();
				return;
			case UmaPackage.ACTIVITY__BREAKDOWN_ELEMENT:
				getBreakdownElement().clear();
				return;
			case UmaPackage.ACTIVITY__ROADMAP:
				getRoadmap().clear();
				return;
			case UmaPackage.ACTIVITY__IS_ENACTABLE:
				unsetIsEnactable();
				return;
			case UmaPackage.ACTIVITY__VARIABILITY_BASED_ON_ELEMENT:
				setVariabilityBasedOnElement(VARIABILITY_BASED_ON_ELEMENT_EDEFAULT);
				return;
			case UmaPackage.ACTIVITY__VARIABILITY_TYPE:
				unsetVariabilityType();
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
			case UmaPackage.ACTIVITY__PRECONDITION:
				return PRECONDITION_EDEFAULT == null ? precondition != null : !PRECONDITION_EDEFAULT.equals(precondition);
			case UmaPackage.ACTIVITY__POSTCONDITION:
				return POSTCONDITION_EDEFAULT == null ? postcondition != null : !POSTCONDITION_EDEFAULT.equals(postcondition);
			case UmaPackage.ACTIVITY__GROUP3:
				return group3 != null && !group3.isEmpty();
			case UmaPackage.ACTIVITY__BREAKDOWN_ELEMENT:
				return !getBreakdownElement().isEmpty();
			case UmaPackage.ACTIVITY__ROADMAP:
				return !getRoadmap().isEmpty();
			case UmaPackage.ACTIVITY__IS_ENACTABLE:
				return isSetIsEnactable();
			case UmaPackage.ACTIVITY__VARIABILITY_BASED_ON_ELEMENT:
				return VARIABILITY_BASED_ON_ELEMENT_EDEFAULT == null ? variabilityBasedOnElement != null : !VARIABILITY_BASED_ON_ELEMENT_EDEFAULT.equals(variabilityBasedOnElement);
			case UmaPackage.ACTIVITY__VARIABILITY_TYPE:
				return isSetVariabilityType();
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
		result.append(" (precondition: ");
		result.append(precondition);
		result.append(", postcondition: ");
		result.append(postcondition);
		result.append(", group3: ");
		result.append(group3);
		result.append(", isEnactable: ");
		if (isEnactableESet) result.append(isEnactable); else result.append("<unset>");
		result.append(", variabilityBasedOnElement: ");
		result.append(variabilityBasedOnElement);
		result.append(", variabilityType: ");
		if (variabilityTypeESet) result.append(variabilityType); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //ActivityImpl
