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
import org.eclipse.epf.xml.uma.ContentElement;
import org.eclipse.epf.xml.uma.UmaPackage;
import org.eclipse.epf.xml.uma.VariabilityType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Content Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.ContentElementImpl#getGroup1 <em>Group1</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.ContentElementImpl#getChecklist <em>Checklist</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.ContentElementImpl#getConcept <em>Concept</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.ContentElementImpl#getExample <em>Example</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.ContentElementImpl#getGuideline <em>Guideline</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.ContentElementImpl#getReusableAsset <em>Reusable Asset</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.ContentElementImpl#getSupportingMaterial <em>Supporting Material</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.ContentElementImpl#getWhitepaper <em>Whitepaper</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.ContentElementImpl#getVariabilityBasedOnElement <em>Variability Based On Element</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.ContentElementImpl#getVariabilityType <em>Variability Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ContentElementImpl extends DescribableElementImpl implements ContentElement {
	/**
	 * The cached value of the '{@link #getGroup1() <em>Group1</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroup1()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap group1;

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
	protected ContentElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UmaPackage.Literals.CONTENT_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getGroup1() {
		if (group1 == null) {
			group1 = new BasicFeatureMap(this, UmaPackage.CONTENT_ELEMENT__GROUP1);
		}
		return group1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getChecklist() {
		return getGroup1().list(UmaPackage.Literals.CONTENT_ELEMENT__CHECKLIST);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getConcept() {
		return getGroup1().list(UmaPackage.Literals.CONTENT_ELEMENT__CONCEPT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getExample() {
		return getGroup1().list(UmaPackage.Literals.CONTENT_ELEMENT__EXAMPLE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getGuideline() {
		return getGroup1().list(UmaPackage.Literals.CONTENT_ELEMENT__GUIDELINE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getReusableAsset() {
		return getGroup1().list(UmaPackage.Literals.CONTENT_ELEMENT__REUSABLE_ASSET);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getSupportingMaterial() {
		return getGroup1().list(UmaPackage.Literals.CONTENT_ELEMENT__SUPPORTING_MATERIAL);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getWhitepaper() {
		return getGroup1().list(UmaPackage.Literals.CONTENT_ELEMENT__WHITEPAPER);
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
			eNotify(new ENotificationImpl(this, Notification.SET, UmaPackage.CONTENT_ELEMENT__VARIABILITY_BASED_ON_ELEMENT, oldVariabilityBasedOnElement, variabilityBasedOnElement));
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
			eNotify(new ENotificationImpl(this, Notification.SET, UmaPackage.CONTENT_ELEMENT__VARIABILITY_TYPE, oldVariabilityType, variabilityType, !oldVariabilityTypeESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, UmaPackage.CONTENT_ELEMENT__VARIABILITY_TYPE, oldVariabilityType, VARIABILITY_TYPE_EDEFAULT, oldVariabilityTypeESet));
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
			case UmaPackage.CONTENT_ELEMENT__GROUP1:
				return ((InternalEList<?>)getGroup1()).basicRemove(otherEnd, msgs);
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
			case UmaPackage.CONTENT_ELEMENT__GROUP1:
				if (coreType) return getGroup1();
				return ((FeatureMap.Internal)getGroup1()).getWrapper();
			case UmaPackage.CONTENT_ELEMENT__CHECKLIST:
				return getChecklist();
			case UmaPackage.CONTENT_ELEMENT__CONCEPT:
				return getConcept();
			case UmaPackage.CONTENT_ELEMENT__EXAMPLE:
				return getExample();
			case UmaPackage.CONTENT_ELEMENT__GUIDELINE:
				return getGuideline();
			case UmaPackage.CONTENT_ELEMENT__REUSABLE_ASSET:
				return getReusableAsset();
			case UmaPackage.CONTENT_ELEMENT__SUPPORTING_MATERIAL:
				return getSupportingMaterial();
			case UmaPackage.CONTENT_ELEMENT__WHITEPAPER:
				return getWhitepaper();
			case UmaPackage.CONTENT_ELEMENT__VARIABILITY_BASED_ON_ELEMENT:
				return getVariabilityBasedOnElement();
			case UmaPackage.CONTENT_ELEMENT__VARIABILITY_TYPE:
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
			case UmaPackage.CONTENT_ELEMENT__GROUP1:
				((FeatureMap.Internal)getGroup1()).set(newValue);
				return;
			case UmaPackage.CONTENT_ELEMENT__CHECKLIST:
				getChecklist().clear();
				getChecklist().addAll((Collection<? extends String>)newValue);
				return;
			case UmaPackage.CONTENT_ELEMENT__CONCEPT:
				getConcept().clear();
				getConcept().addAll((Collection<? extends String>)newValue);
				return;
			case UmaPackage.CONTENT_ELEMENT__EXAMPLE:
				getExample().clear();
				getExample().addAll((Collection<? extends String>)newValue);
				return;
			case UmaPackage.CONTENT_ELEMENT__GUIDELINE:
				getGuideline().clear();
				getGuideline().addAll((Collection<? extends String>)newValue);
				return;
			case UmaPackage.CONTENT_ELEMENT__REUSABLE_ASSET:
				getReusableAsset().clear();
				getReusableAsset().addAll((Collection<? extends String>)newValue);
				return;
			case UmaPackage.CONTENT_ELEMENT__SUPPORTING_MATERIAL:
				getSupportingMaterial().clear();
				getSupportingMaterial().addAll((Collection<? extends String>)newValue);
				return;
			case UmaPackage.CONTENT_ELEMENT__WHITEPAPER:
				getWhitepaper().clear();
				getWhitepaper().addAll((Collection<? extends String>)newValue);
				return;
			case UmaPackage.CONTENT_ELEMENT__VARIABILITY_BASED_ON_ELEMENT:
				setVariabilityBasedOnElement((String)newValue);
				return;
			case UmaPackage.CONTENT_ELEMENT__VARIABILITY_TYPE:
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
			case UmaPackage.CONTENT_ELEMENT__GROUP1:
				getGroup1().clear();
				return;
			case UmaPackage.CONTENT_ELEMENT__CHECKLIST:
				getChecklist().clear();
				return;
			case UmaPackage.CONTENT_ELEMENT__CONCEPT:
				getConcept().clear();
				return;
			case UmaPackage.CONTENT_ELEMENT__EXAMPLE:
				getExample().clear();
				return;
			case UmaPackage.CONTENT_ELEMENT__GUIDELINE:
				getGuideline().clear();
				return;
			case UmaPackage.CONTENT_ELEMENT__REUSABLE_ASSET:
				getReusableAsset().clear();
				return;
			case UmaPackage.CONTENT_ELEMENT__SUPPORTING_MATERIAL:
				getSupportingMaterial().clear();
				return;
			case UmaPackage.CONTENT_ELEMENT__WHITEPAPER:
				getWhitepaper().clear();
				return;
			case UmaPackage.CONTENT_ELEMENT__VARIABILITY_BASED_ON_ELEMENT:
				setVariabilityBasedOnElement(VARIABILITY_BASED_ON_ELEMENT_EDEFAULT);
				return;
			case UmaPackage.CONTENT_ELEMENT__VARIABILITY_TYPE:
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
			case UmaPackage.CONTENT_ELEMENT__GROUP1:
				return group1 != null && !group1.isEmpty();
			case UmaPackage.CONTENT_ELEMENT__CHECKLIST:
				return !getChecklist().isEmpty();
			case UmaPackage.CONTENT_ELEMENT__CONCEPT:
				return !getConcept().isEmpty();
			case UmaPackage.CONTENT_ELEMENT__EXAMPLE:
				return !getExample().isEmpty();
			case UmaPackage.CONTENT_ELEMENT__GUIDELINE:
				return !getGuideline().isEmpty();
			case UmaPackage.CONTENT_ELEMENT__REUSABLE_ASSET:
				return !getReusableAsset().isEmpty();
			case UmaPackage.CONTENT_ELEMENT__SUPPORTING_MATERIAL:
				return !getSupportingMaterial().isEmpty();
			case UmaPackage.CONTENT_ELEMENT__WHITEPAPER:
				return !getWhitepaper().isEmpty();
			case UmaPackage.CONTENT_ELEMENT__VARIABILITY_BASED_ON_ELEMENT:
				return VARIABILITY_BASED_ON_ELEMENT_EDEFAULT == null ? variabilityBasedOnElement != null : !VARIABILITY_BASED_ON_ELEMENT_EDEFAULT.equals(variabilityBasedOnElement);
			case UmaPackage.CONTENT_ELEMENT__VARIABILITY_TYPE:
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
		result.append(" (group1: ");
		result.append(group1);
		result.append(", variabilityBasedOnElement: ");
		result.append(variabilityBasedOnElement);
		result.append(", variabilityType: ");
		if (variabilityTypeESet) result.append(variabilityType); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //ContentElementImpl
