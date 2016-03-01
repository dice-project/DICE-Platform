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
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.epf.xml.uma.ContentDescription;
import org.eclipse.epf.xml.uma.Section;
import org.eclipse.epf.xml.uma.UmaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Content Description</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.ContentDescriptionImpl#getMainDescription <em>Main Description</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.ContentDescriptionImpl#getKeyConsiderations <em>Key Considerations</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.ContentDescriptionImpl#getSection <em>Section</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.ContentDescriptionImpl#getExternalId <em>External Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ContentDescriptionImpl extends MethodUnitImpl implements ContentDescription {
	/**
	 * The default value of the '{@link #getMainDescription() <em>Main Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMainDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String MAIN_DESCRIPTION_EDEFAULT = null;

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
	 * The default value of the '{@link #getKeyConsiderations() <em>Key Considerations</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKeyConsiderations()
	 * @generated
	 * @ordered
	 */
	protected static final String KEY_CONSIDERATIONS_EDEFAULT = null;

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
	 * The cached value of the '{@link #getSection() <em>Section</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSection()
	 * @generated
	 * @ordered
	 */
	protected EList<Section> section;

	/**
	 * The default value of the '{@link #getExternalId() <em>External Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExternalId()
	 * @generated
	 * @ordered
	 */
	protected static final String EXTERNAL_ID_EDEFAULT = null;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ContentDescriptionImpl() {
		super();
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
			eNotify(new ENotificationImpl(this, Notification.SET, UmaPackage.CONTENT_DESCRIPTION__MAIN_DESCRIPTION, oldMainDescription, mainDescription));
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
			eNotify(new ENotificationImpl(this, Notification.SET, UmaPackage.CONTENT_DESCRIPTION__KEY_CONSIDERATIONS, oldKeyConsiderations, keyConsiderations));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Section> getSection() {
		if (section == null) {
			section = new EObjectContainmentEList<Section>(Section.class, this, UmaPackage.CONTENT_DESCRIPTION__SECTION);
		}
		return section;
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
			eNotify(new ENotificationImpl(this, Notification.SET, UmaPackage.CONTENT_DESCRIPTION__EXTERNAL_ID, oldExternalId, externalId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UmaPackage.CONTENT_DESCRIPTION__SECTION:
				return ((InternalEList<?>)getSection()).basicRemove(otherEnd, msgs);
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
			case UmaPackage.CONTENT_DESCRIPTION__KEY_CONSIDERATIONS:
				return getKeyConsiderations();
			case UmaPackage.CONTENT_DESCRIPTION__SECTION:
				return getSection();
			case UmaPackage.CONTENT_DESCRIPTION__EXTERNAL_ID:
				return getExternalId();
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
				setMainDescription((String)newValue);
				return;
			case UmaPackage.CONTENT_DESCRIPTION__KEY_CONSIDERATIONS:
				setKeyConsiderations((String)newValue);
				return;
			case UmaPackage.CONTENT_DESCRIPTION__SECTION:
				getSection().clear();
				getSection().addAll((Collection<? extends Section>)newValue);
				return;
			case UmaPackage.CONTENT_DESCRIPTION__EXTERNAL_ID:
				setExternalId((String)newValue);
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
			case UmaPackage.CONTENT_DESCRIPTION__KEY_CONSIDERATIONS:
				setKeyConsiderations(KEY_CONSIDERATIONS_EDEFAULT);
				return;
			case UmaPackage.CONTENT_DESCRIPTION__SECTION:
				getSection().clear();
				return;
			case UmaPackage.CONTENT_DESCRIPTION__EXTERNAL_ID:
				setExternalId(EXTERNAL_ID_EDEFAULT);
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
			case UmaPackage.CONTENT_DESCRIPTION__MAIN_DESCRIPTION:
				return MAIN_DESCRIPTION_EDEFAULT == null ? mainDescription != null : !MAIN_DESCRIPTION_EDEFAULT.equals(mainDescription);
			case UmaPackage.CONTENT_DESCRIPTION__KEY_CONSIDERATIONS:
				return KEY_CONSIDERATIONS_EDEFAULT == null ? keyConsiderations != null : !KEY_CONSIDERATIONS_EDEFAULT.equals(keyConsiderations);
			case UmaPackage.CONTENT_DESCRIPTION__SECTION:
				return section != null && !section.isEmpty();
			case UmaPackage.CONTENT_DESCRIPTION__EXTERNAL_ID:
				return EXTERNAL_ID_EDEFAULT == null ? externalId != null : !EXTERNAL_ID_EDEFAULT.equals(externalId);
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
		result.append(" (mainDescription: ");
		result.append(mainDescription);
		result.append(", keyConsiderations: ");
		result.append(keyConsiderations);
		result.append(", externalId: ");
		result.append(externalId);
		result.append(')');
		return result.toString();
	}

} //ContentDescriptionImpl
