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
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.epf.uma.ContentDescription;
import org.eclipse.epf.uma.Section;
import org.eclipse.epf.uma.UmaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Content Description</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.impl.ContentDescriptionImpl#getMainDescription <em>Main Description</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.ContentDescriptionImpl#getSections <em>Sections</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.ContentDescriptionImpl#getExternalId <em>External Id</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.ContentDescriptionImpl#getKeyConsiderations <em>Key Considerations</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.ContentDescriptionImpl#getLongPresentationName <em>Long Presentation Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ContentDescriptionImpl extends MethodUnitImpl implements
		ContentDescription {
	/**
	 * The default value of the '{@link #getMainDescription() <em>Main Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMainDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String MAIN_DESCRIPTION_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getMainDescription() <em>Main Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMainDescription()
	 * @generated
	 * @ordered
	 */
	protected String mainDescription = MAIN_DESCRIPTION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSections() <em>Sections</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSections()
	 * @generated
	 * @ordered
	 */
	protected EList<Section> sections;

	/**
	 * The default value of the '{@link #getExternalId() <em>External Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExternalId()
	 * @generated
	 * @ordered
	 */
	protected static final String EXTERNAL_ID_EDEFAULT = ""; //$NON-NLS-1$

	/**
	 * The cached value of the '{@link #getExternalId() <em>External Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExternalId()
	 * @generated
	 * @ordered
	 */
	protected String externalId = EXTERNAL_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getKeyConsiderations() <em>Key Considerations</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKeyConsiderations()
	 * @generated
	 * @ordered
	 */
	protected static final String KEY_CONSIDERATIONS_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getKeyConsiderations() <em>Key Considerations</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKeyConsiderations()
	 * @generated
	 * @ordered
	 */
	protected String keyConsiderations = KEY_CONSIDERATIONS_EDEFAULT;

	/**
	 * The default value of the '{@link #getLongPresentationName() <em>Long Presentation Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLongPresentationName()
	 * @generated
	 * @ordered
	 */
	protected static final String LONG_PRESENTATION_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLongPresentationName() <em>Long Presentation Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLongPresentationName()
	 * @generated
	 * @ordered
	 */
	protected String longPresentationName = LONG_PRESENTATION_NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ContentDescriptionImpl() {
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
		return UmaPackage.Literals.CONTENT_DESCRIPTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMainDescription() {
		return mainDescription;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMainDescription(String newMainDescription) {
		String oldMainDescription = mainDescription;
		mainDescription = newMainDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.CONTENT_DESCRIPTION__MAIN_DESCRIPTION,
					oldMainDescription, mainDescription));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Section> getSections() {
		if (sections == null) {
			sections = new EObjectContainmentEList.Resolving<Section>(
					Section.class, this,
					UmaPackage.CONTENT_DESCRIPTION__SECTIONS);
		}
		return sections;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getExternalId() {
		return externalId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExternalId(String newExternalId) {
		String oldExternalId = externalId;
		externalId = newExternalId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.CONTENT_DESCRIPTION__EXTERNAL_ID, oldExternalId,
					externalId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getKeyConsiderations() {
		return keyConsiderations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setKeyConsiderations(String newKeyConsiderations) {
		String oldKeyConsiderations = keyConsiderations;
		keyConsiderations = newKeyConsiderations;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.CONTENT_DESCRIPTION__KEY_CONSIDERATIONS,
					oldKeyConsiderations, keyConsiderations));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLongPresentationName() {
		return longPresentationName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLongPresentationName(String newLongPresentationName) {
		String oldLongPresentationName = longPresentationName;
		longPresentationName = newLongPresentationName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.CONTENT_DESCRIPTION__LONG_PRESENTATION_NAME,
					oldLongPresentationName, longPresentationName));
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
		case UmaPackage.CONTENT_DESCRIPTION__SECTIONS:
			return ((InternalEList<?>) getSections()).basicRemove(otherEnd,
					msgs);
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
		case UmaPackage.CONTENT_DESCRIPTION__MAIN_DESCRIPTION:
			return getMainDescription();
		case UmaPackage.CONTENT_DESCRIPTION__SECTIONS:
			return getSections();
		case UmaPackage.CONTENT_DESCRIPTION__EXTERNAL_ID:
			return getExternalId();
		case UmaPackage.CONTENT_DESCRIPTION__KEY_CONSIDERATIONS:
			return getKeyConsiderations();
		case UmaPackage.CONTENT_DESCRIPTION__LONG_PRESENTATION_NAME:
			return getLongPresentationName();
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
		case UmaPackage.CONTENT_DESCRIPTION__MAIN_DESCRIPTION:
			setMainDescription((String) newValue);
			return;
		case UmaPackage.CONTENT_DESCRIPTION__SECTIONS:
			getSections().clear();
			getSections().addAll((Collection<? extends Section>) newValue);
			return;
		case UmaPackage.CONTENT_DESCRIPTION__EXTERNAL_ID:
			setExternalId((String) newValue);
			return;
		case UmaPackage.CONTENT_DESCRIPTION__KEY_CONSIDERATIONS:
			setKeyConsiderations((String) newValue);
			return;
		case UmaPackage.CONTENT_DESCRIPTION__LONG_PRESENTATION_NAME:
			setLongPresentationName((String) newValue);
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
		case UmaPackage.CONTENT_DESCRIPTION__MAIN_DESCRIPTION:
			setMainDescription(MAIN_DESCRIPTION_EDEFAULT);
			return;
		case UmaPackage.CONTENT_DESCRIPTION__SECTIONS:
			getSections().clear();
			return;
		case UmaPackage.CONTENT_DESCRIPTION__EXTERNAL_ID:
			setExternalId(EXTERNAL_ID_EDEFAULT);
			return;
		case UmaPackage.CONTENT_DESCRIPTION__KEY_CONSIDERATIONS:
			setKeyConsiderations(KEY_CONSIDERATIONS_EDEFAULT);
			return;
		case UmaPackage.CONTENT_DESCRIPTION__LONG_PRESENTATION_NAME:
			setLongPresentationName(LONG_PRESENTATION_NAME_EDEFAULT);
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
		case UmaPackage.CONTENT_DESCRIPTION__MAIN_DESCRIPTION:
			return MAIN_DESCRIPTION_EDEFAULT == null ? mainDescription != null
					: !MAIN_DESCRIPTION_EDEFAULT.equals(mainDescription);
		case UmaPackage.CONTENT_DESCRIPTION__SECTIONS:
			return sections != null && !sections.isEmpty();
		case UmaPackage.CONTENT_DESCRIPTION__EXTERNAL_ID:
			return EXTERNAL_ID_EDEFAULT == null ? externalId != null
					: !EXTERNAL_ID_EDEFAULT.equals(externalId);
		case UmaPackage.CONTENT_DESCRIPTION__KEY_CONSIDERATIONS:
			return KEY_CONSIDERATIONS_EDEFAULT == null ? keyConsiderations != null
					: !KEY_CONSIDERATIONS_EDEFAULT.equals(keyConsiderations);
		case UmaPackage.CONTENT_DESCRIPTION__LONG_PRESENTATION_NAME:
			return LONG_PRESENTATION_NAME_EDEFAULT == null ? longPresentationName != null
					: !LONG_PRESENTATION_NAME_EDEFAULT
							.equals(longPresentationName);
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
		result.append(" (mainDescription: "); //$NON-NLS-1$
		result.append(mainDescription);
		result.append(", externalId: "); //$NON-NLS-1$
		result.append(externalId);
		result.append(", keyConsiderations: "); //$NON-NLS-1$
		result.append(keyConsiderations);
		result.append(", longPresentationName: "); //$NON-NLS-1$
		result.append(longPresentationName);
		result.append(')');
		return result.toString();
	}

} //ContentDescriptionImpl
