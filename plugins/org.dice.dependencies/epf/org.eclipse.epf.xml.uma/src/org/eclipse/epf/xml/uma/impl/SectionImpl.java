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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.epf.xml.uma.Section;
import org.eclipse.epf.xml.uma.UmaPackage;
import org.eclipse.epf.xml.uma.VariabilityType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Section</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.SectionImpl#getSubSection <em>Sub Section</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.SectionImpl#getPredecessor <em>Predecessor</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.SectionImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.SectionImpl#getSectionName <em>Section Name</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.SectionImpl#getVariabilityBasedOnElement <em>Variability Based On Element</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.SectionImpl#getVariabilityType <em>Variability Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SectionImpl extends MethodElementImpl implements Section {
	/**
	 * The cached value of the '{@link #getSubSection() <em>Sub Section</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubSection()
	 * @generated
	 * @ordered
	 */
	protected Section subSection;

	/**
	 * The default value of the '{@link #getPredecessor() <em>Predecessor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPredecessor()
	 * @generated
	 * @ordered
	 */
	protected static final String PREDECESSOR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPredecessor() <em>Predecessor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPredecessor()
	 * @generated
	 * @ordered
	 */
	protected String predecessor = PREDECESSOR_EDEFAULT;

	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected String description = DESCRIPTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getSectionName() <em>Section Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSectionName()
	 * @generated
	 * @ordered
	 */
	protected static final String SECTION_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSectionName() <em>Section Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSectionName()
	 * @generated
	 * @ordered
	 */
	protected String sectionName = SECTION_NAME_EDEFAULT;

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
	protected SectionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UmaPackage.Literals.SECTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Section getSubSection() {
		return subSection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSubSection(Section newSubSection, NotificationChain msgs) {
		Section oldSubSection = subSection;
		subSection = newSubSection;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UmaPackage.SECTION__SUB_SECTION, oldSubSection, newSubSection);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSubSection(Section newSubSection) {
		if (newSubSection != subSection) {
			NotificationChain msgs = null;
			if (subSection != null)
				msgs = ((InternalEObject)subSection).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - UmaPackage.SECTION__SUB_SECTION, null, msgs);
			if (newSubSection != null)
				msgs = ((InternalEObject)newSubSection).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - UmaPackage.SECTION__SUB_SECTION, null, msgs);
			msgs = basicSetSubSection(newSubSection, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UmaPackage.SECTION__SUB_SECTION, newSubSection, newSubSection));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPredecessor() {
		return predecessor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPredecessor(String newPredecessor) {
		String oldPredecessor = predecessor;
		predecessor = newPredecessor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UmaPackage.SECTION__PREDECESSOR, oldPredecessor, predecessor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UmaPackage.SECTION__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSectionName() {
		return sectionName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSectionName(String newSectionName) {
		String oldSectionName = sectionName;
		sectionName = newSectionName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UmaPackage.SECTION__SECTION_NAME, oldSectionName, sectionName));
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
			eNotify(new ENotificationImpl(this, Notification.SET, UmaPackage.SECTION__VARIABILITY_BASED_ON_ELEMENT, oldVariabilityBasedOnElement, variabilityBasedOnElement));
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
			eNotify(new ENotificationImpl(this, Notification.SET, UmaPackage.SECTION__VARIABILITY_TYPE, oldVariabilityType, variabilityType, !oldVariabilityTypeESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, UmaPackage.SECTION__VARIABILITY_TYPE, oldVariabilityType, VARIABILITY_TYPE_EDEFAULT, oldVariabilityTypeESet));
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
			case UmaPackage.SECTION__SUB_SECTION:
				return basicSetSubSection(null, msgs);
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
			case UmaPackage.SECTION__SUB_SECTION:
				return getSubSection();
			case UmaPackage.SECTION__PREDECESSOR:
				return getPredecessor();
			case UmaPackage.SECTION__DESCRIPTION:
				return getDescription();
			case UmaPackage.SECTION__SECTION_NAME:
				return getSectionName();
			case UmaPackage.SECTION__VARIABILITY_BASED_ON_ELEMENT:
				return getVariabilityBasedOnElement();
			case UmaPackage.SECTION__VARIABILITY_TYPE:
				return getVariabilityType();
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
			case UmaPackage.SECTION__SUB_SECTION:
				setSubSection((Section)newValue);
				return;
			case UmaPackage.SECTION__PREDECESSOR:
				setPredecessor((String)newValue);
				return;
			case UmaPackage.SECTION__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case UmaPackage.SECTION__SECTION_NAME:
				setSectionName((String)newValue);
				return;
			case UmaPackage.SECTION__VARIABILITY_BASED_ON_ELEMENT:
				setVariabilityBasedOnElement((String)newValue);
				return;
			case UmaPackage.SECTION__VARIABILITY_TYPE:
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
			case UmaPackage.SECTION__SUB_SECTION:
				setSubSection((Section)null);
				return;
			case UmaPackage.SECTION__PREDECESSOR:
				setPredecessor(PREDECESSOR_EDEFAULT);
				return;
			case UmaPackage.SECTION__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case UmaPackage.SECTION__SECTION_NAME:
				setSectionName(SECTION_NAME_EDEFAULT);
				return;
			case UmaPackage.SECTION__VARIABILITY_BASED_ON_ELEMENT:
				setVariabilityBasedOnElement(VARIABILITY_BASED_ON_ELEMENT_EDEFAULT);
				return;
			case UmaPackage.SECTION__VARIABILITY_TYPE:
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
			case UmaPackage.SECTION__SUB_SECTION:
				return subSection != null;
			case UmaPackage.SECTION__PREDECESSOR:
				return PREDECESSOR_EDEFAULT == null ? predecessor != null : !PREDECESSOR_EDEFAULT.equals(predecessor);
			case UmaPackage.SECTION__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case UmaPackage.SECTION__SECTION_NAME:
				return SECTION_NAME_EDEFAULT == null ? sectionName != null : !SECTION_NAME_EDEFAULT.equals(sectionName);
			case UmaPackage.SECTION__VARIABILITY_BASED_ON_ELEMENT:
				return VARIABILITY_BASED_ON_ELEMENT_EDEFAULT == null ? variabilityBasedOnElement != null : !VARIABILITY_BASED_ON_ELEMENT_EDEFAULT.equals(variabilityBasedOnElement);
			case UmaPackage.SECTION__VARIABILITY_TYPE:
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
		result.append(" (predecessor: ");
		result.append(predecessor);
		result.append(", description: ");
		result.append(description);
		result.append(", sectionName: ");
		result.append(sectionName);
		result.append(", variabilityBasedOnElement: ");
		result.append(variabilityBasedOnElement);
		result.append(", variabilityType: ");
		if (variabilityTypeESet) result.append(variabilityType); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //SectionImpl
