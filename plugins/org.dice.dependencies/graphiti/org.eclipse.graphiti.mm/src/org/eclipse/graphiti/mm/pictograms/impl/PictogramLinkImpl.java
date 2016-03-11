/**
 * <copyright>
 * 
 * Copyright (c) 2005, 2010 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 * 
 * </copyright>
 */
package org.eclipse.graphiti.mm.pictograms.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.eclipse.graphiti.mm.impl.PropertyContainerImpl;

import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.PictogramLink;
import org.eclipse.graphiti.mm.pictograms.PictogramsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Pictogram Link</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.impl.PictogramLinkImpl#getPictogramElement <em>Pictogram Element</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.impl.PictogramLinkImpl#getBusinessObjects <em>Business Objects</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PictogramLinkImpl extends PropertyContainerImpl implements PictogramLink {
	/**
	 * The cached value of the '{@link #getBusinessObjects() <em>Business Objects</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBusinessObjects()
	 * @generated
	 * @ordered
	 */
	protected EList<EObject> businessObjects;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PictogramLinkImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PictogramsPackage.Literals.PICTOGRAM_LINK;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PictogramElement getPictogramElement() {
		if (eContainerFeatureID() != PictogramsPackage.PICTOGRAM_LINK__PICTOGRAM_ELEMENT) return null;
		return (PictogramElement)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PictogramElement basicGetPictogramElement() {
		if (eContainerFeatureID() != PictogramsPackage.PICTOGRAM_LINK__PICTOGRAM_ELEMENT) return null;
		return (PictogramElement)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPictogramElement(PictogramElement newPictogramElement, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newPictogramElement, PictogramsPackage.PICTOGRAM_LINK__PICTOGRAM_ELEMENT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPictogramElement(PictogramElement newPictogramElement) {
		if (newPictogramElement != eInternalContainer() || (eContainerFeatureID() != PictogramsPackage.PICTOGRAM_LINK__PICTOGRAM_ELEMENT && newPictogramElement != null)) {
			if (EcoreUtil.isAncestor(this, newPictogramElement))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newPictogramElement != null)
				msgs = ((InternalEObject)newPictogramElement).eInverseAdd(this, PictogramsPackage.PICTOGRAM_ELEMENT__LINK, PictogramElement.class, msgs);
			msgs = basicSetPictogramElement(newPictogramElement, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PictogramsPackage.PICTOGRAM_LINK__PICTOGRAM_ELEMENT, newPictogramElement, newPictogramElement));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EObject> getBusinessObjects() {
		if (businessObjects == null) {
			businessObjects = new EObjectResolvingEList<EObject>(EObject.class, this, PictogramsPackage.PICTOGRAM_LINK__BUSINESS_OBJECTS);
		}
		return businessObjects;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PictogramsPackage.PICTOGRAM_LINK__PICTOGRAM_ELEMENT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetPictogramElement((PictogramElement)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PictogramsPackage.PICTOGRAM_LINK__PICTOGRAM_ELEMENT:
				return basicSetPictogramElement(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case PictogramsPackage.PICTOGRAM_LINK__PICTOGRAM_ELEMENT:
				return eInternalContainer().eInverseRemove(this, PictogramsPackage.PICTOGRAM_ELEMENT__LINK, PictogramElement.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PictogramsPackage.PICTOGRAM_LINK__PICTOGRAM_ELEMENT:
				if (resolve) return getPictogramElement();
				return basicGetPictogramElement();
			case PictogramsPackage.PICTOGRAM_LINK__BUSINESS_OBJECTS:
				return getBusinessObjects();
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
			case PictogramsPackage.PICTOGRAM_LINK__PICTOGRAM_ELEMENT:
				setPictogramElement((PictogramElement)newValue);
				return;
			case PictogramsPackage.PICTOGRAM_LINK__BUSINESS_OBJECTS:
				getBusinessObjects().clear();
				getBusinessObjects().addAll((Collection<? extends EObject>)newValue);
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
			case PictogramsPackage.PICTOGRAM_LINK__PICTOGRAM_ELEMENT:
				setPictogramElement((PictogramElement)null);
				return;
			case PictogramsPackage.PICTOGRAM_LINK__BUSINESS_OBJECTS:
				getBusinessObjects().clear();
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
			case PictogramsPackage.PICTOGRAM_LINK__PICTOGRAM_ELEMENT:
				return basicGetPictogramElement() != null;
			case PictogramsPackage.PICTOGRAM_LINK__BUSINESS_OBJECTS:
				return businessObjects != null && !businessObjects.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //PictogramLinkImpl
