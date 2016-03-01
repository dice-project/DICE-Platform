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
import org.eclipse.epf.xml.uma.Constraint;
import org.eclipse.epf.xml.uma.MethodElement;
import org.eclipse.epf.xml.uma.MethodElementProperty;
import org.eclipse.epf.xml.uma.UmaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Method Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.MethodElementImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.MethodElementImpl#getOwnedRule <em>Owned Rule</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.MethodElementImpl#getMethodElementProperty <em>Method Element Property</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.MethodElementImpl#getBriefDescription <em>Brief Description</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.MethodElementImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.MethodElementImpl#getOrderingGuide <em>Ordering Guide</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.MethodElementImpl#getPresentationName <em>Presentation Name</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.MethodElementImpl#isSuppressed <em>Suppressed</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MethodElementImpl extends PackageableElementImpl implements MethodElement {
	/**
	 * The cached value of the '{@link #getGroup() <em>Group</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroup()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap group;

	/**
	 * The default value of the '{@link #getBriefDescription() <em>Brief Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBriefDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String BRIEF_DESCRIPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBriefDescription() <em>Brief Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBriefDescription()
	 * @generated
	 * @ordered
	 */
	protected String briefDescription = BRIEF_DESCRIPTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getOrderingGuide() <em>Ordering Guide</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrderingGuide()
	 * @generated
	 * @ordered
	 */
	protected static final String ORDERING_GUIDE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOrderingGuide() <em>Ordering Guide</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrderingGuide()
	 * @generated
	 * @ordered
	 */
	protected String orderingGuide = ORDERING_GUIDE_EDEFAULT;

	/**
	 * The default value of the '{@link #getPresentationName() <em>Presentation Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPresentationName()
	 * @generated
	 * @ordered
	 */
	protected static final String PRESENTATION_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPresentationName() <em>Presentation Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPresentationName()
	 * @generated
	 * @ordered
	 */
	protected String presentationName = PRESENTATION_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #isSuppressed() <em>Suppressed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSuppressed()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SUPPRESSED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isSuppressed() <em>Suppressed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSuppressed()
	 * @generated
	 * @ordered
	 */
	protected boolean suppressed = SUPPRESSED_EDEFAULT;

	/**
	 * This is true if the Suppressed attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean suppressedESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MethodElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UmaPackage.Literals.METHOD_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getGroup() {
		if (group == null) {
			group = new BasicFeatureMap(this, UmaPackage.METHOD_ELEMENT__GROUP);
		}
		return group;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Constraint> getOwnedRule() {
		return getGroup().list(UmaPackage.Literals.METHOD_ELEMENT__OWNED_RULE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<MethodElementProperty> getMethodElementProperty() {
		return getGroup().list(UmaPackage.Literals.METHOD_ELEMENT__METHOD_ELEMENT_PROPERTY);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getBriefDescription() {
		return briefDescription;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBriefDescription(String newBriefDescription) {
		String oldBriefDescription = briefDescription;
		briefDescription = newBriefDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UmaPackage.METHOD_ELEMENT__BRIEF_DESCRIPTION, oldBriefDescription, briefDescription));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UmaPackage.METHOD_ELEMENT__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getOrderingGuide() {
		return orderingGuide;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOrderingGuide(String newOrderingGuide) {
		String oldOrderingGuide = orderingGuide;
		orderingGuide = newOrderingGuide;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UmaPackage.METHOD_ELEMENT__ORDERING_GUIDE, oldOrderingGuide, orderingGuide));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPresentationName() {
		return presentationName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPresentationName(String newPresentationName) {
		String oldPresentationName = presentationName;
		presentationName = newPresentationName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UmaPackage.METHOD_ELEMENT__PRESENTATION_NAME, oldPresentationName, presentationName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSuppressed() {
		return suppressed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSuppressed(boolean newSuppressed) {
		boolean oldSuppressed = suppressed;
		suppressed = newSuppressed;
		boolean oldSuppressedESet = suppressedESet;
		suppressedESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UmaPackage.METHOD_ELEMENT__SUPPRESSED, oldSuppressed, suppressed, !oldSuppressedESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetSuppressed() {
		boolean oldSuppressed = suppressed;
		boolean oldSuppressedESet = suppressedESet;
		suppressed = SUPPRESSED_EDEFAULT;
		suppressedESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, UmaPackage.METHOD_ELEMENT__SUPPRESSED, oldSuppressed, SUPPRESSED_EDEFAULT, oldSuppressedESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetSuppressed() {
		return suppressedESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UmaPackage.METHOD_ELEMENT__GROUP:
				return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
			case UmaPackage.METHOD_ELEMENT__OWNED_RULE:
				return ((InternalEList<?>)getOwnedRule()).basicRemove(otherEnd, msgs);
			case UmaPackage.METHOD_ELEMENT__METHOD_ELEMENT_PROPERTY:
				return ((InternalEList<?>)getMethodElementProperty()).basicRemove(otherEnd, msgs);
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
			case UmaPackage.METHOD_ELEMENT__GROUP:
				if (coreType) return getGroup();
				return ((FeatureMap.Internal)getGroup()).getWrapper();
			case UmaPackage.METHOD_ELEMENT__OWNED_RULE:
				return getOwnedRule();
			case UmaPackage.METHOD_ELEMENT__METHOD_ELEMENT_PROPERTY:
				return getMethodElementProperty();
			case UmaPackage.METHOD_ELEMENT__BRIEF_DESCRIPTION:
				return getBriefDescription();
			case UmaPackage.METHOD_ELEMENT__ID:
				return getId();
			case UmaPackage.METHOD_ELEMENT__ORDERING_GUIDE:
				return getOrderingGuide();
			case UmaPackage.METHOD_ELEMENT__PRESENTATION_NAME:
				return getPresentationName();
			case UmaPackage.METHOD_ELEMENT__SUPPRESSED:
				return isSuppressed() ? Boolean.TRUE : Boolean.FALSE;
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
			case UmaPackage.METHOD_ELEMENT__GROUP:
				((FeatureMap.Internal)getGroup()).set(newValue);
				return;
			case UmaPackage.METHOD_ELEMENT__OWNED_RULE:
				getOwnedRule().clear();
				getOwnedRule().addAll((Collection<? extends Constraint>)newValue);
				return;
			case UmaPackage.METHOD_ELEMENT__METHOD_ELEMENT_PROPERTY:
				getMethodElementProperty().clear();
				getMethodElementProperty().addAll((Collection<? extends MethodElementProperty>)newValue);
				return;
			case UmaPackage.METHOD_ELEMENT__BRIEF_DESCRIPTION:
				setBriefDescription((String)newValue);
				return;
			case UmaPackage.METHOD_ELEMENT__ID:
				setId((String)newValue);
				return;
			case UmaPackage.METHOD_ELEMENT__ORDERING_GUIDE:
				setOrderingGuide((String)newValue);
				return;
			case UmaPackage.METHOD_ELEMENT__PRESENTATION_NAME:
				setPresentationName((String)newValue);
				return;
			case UmaPackage.METHOD_ELEMENT__SUPPRESSED:
				setSuppressed(((Boolean)newValue).booleanValue());
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
			case UmaPackage.METHOD_ELEMENT__GROUP:
				getGroup().clear();
				return;
			case UmaPackage.METHOD_ELEMENT__OWNED_RULE:
				getOwnedRule().clear();
				return;
			case UmaPackage.METHOD_ELEMENT__METHOD_ELEMENT_PROPERTY:
				getMethodElementProperty().clear();
				return;
			case UmaPackage.METHOD_ELEMENT__BRIEF_DESCRIPTION:
				setBriefDescription(BRIEF_DESCRIPTION_EDEFAULT);
				return;
			case UmaPackage.METHOD_ELEMENT__ID:
				setId(ID_EDEFAULT);
				return;
			case UmaPackage.METHOD_ELEMENT__ORDERING_GUIDE:
				setOrderingGuide(ORDERING_GUIDE_EDEFAULT);
				return;
			case UmaPackage.METHOD_ELEMENT__PRESENTATION_NAME:
				setPresentationName(PRESENTATION_NAME_EDEFAULT);
				return;
			case UmaPackage.METHOD_ELEMENT__SUPPRESSED:
				unsetSuppressed();
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
			case UmaPackage.METHOD_ELEMENT__GROUP:
				return group != null && !group.isEmpty();
			case UmaPackage.METHOD_ELEMENT__OWNED_RULE:
				return !getOwnedRule().isEmpty();
			case UmaPackage.METHOD_ELEMENT__METHOD_ELEMENT_PROPERTY:
				return !getMethodElementProperty().isEmpty();
			case UmaPackage.METHOD_ELEMENT__BRIEF_DESCRIPTION:
				return BRIEF_DESCRIPTION_EDEFAULT == null ? briefDescription != null : !BRIEF_DESCRIPTION_EDEFAULT.equals(briefDescription);
			case UmaPackage.METHOD_ELEMENT__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case UmaPackage.METHOD_ELEMENT__ORDERING_GUIDE:
				return ORDERING_GUIDE_EDEFAULT == null ? orderingGuide != null : !ORDERING_GUIDE_EDEFAULT.equals(orderingGuide);
			case UmaPackage.METHOD_ELEMENT__PRESENTATION_NAME:
				return PRESENTATION_NAME_EDEFAULT == null ? presentationName != null : !PRESENTATION_NAME_EDEFAULT.equals(presentationName);
			case UmaPackage.METHOD_ELEMENT__SUPPRESSED:
				return isSetSuppressed();
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
		result.append(" (group: ");
		result.append(group);
		result.append(", briefDescription: ");
		result.append(briefDescription);
		result.append(", id: ");
		result.append(id);
		result.append(", orderingGuide: ");
		result.append(orderingGuide);
		result.append(", presentationName: ");
		result.append(presentationName);
		result.append(", suppressed: ");
		if (suppressedESet) result.append(suppressed); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //MethodElementImpl
