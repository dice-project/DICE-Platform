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

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.epf.uma.DiagramElement;
import org.eclipse.epf.uma.GraphElement;
import org.eclipse.epf.uma.Property;
import org.eclipse.epf.uma.Reference;
import org.eclipse.epf.uma.UmaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Diagram Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.impl.DiagramElementImpl#getIsVisible <em>Is Visible</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.DiagramElementImpl#getContainer <em>Container</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.DiagramElementImpl#getReference <em>Reference</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.DiagramElementImpl#getProperty <em>Property</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class DiagramElementImpl extends MethodElementImpl implements
		DiagramElement {
	/**
	 * The default value of the '{@link #getIsVisible() <em>Is Visible</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsVisible()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_VISIBLE_EDEFAULT = Boolean.TRUE;

	/**
	 * The cached value of the '{@link #getIsVisible() <em>Is Visible</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsVisible()
	 * @generated
	 * @ordered
	 */
	protected Boolean isVisible = IS_VISIBLE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getReference() <em>Reference</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReference()
	 * @generated
	 * @ordered
	 */
	protected EList<Reference> reference;

	/**
	 * The cached value of the '{@link #getProperty() <em>Property</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProperty()
	 * @generated
	 * @ordered
	 */
	protected EList<Property> property;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DiagramElementImpl() {
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
		return UmaPackage.Literals.DIAGRAM_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getIsVisible() {
		return isVisible;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsVisible(Boolean newIsVisible) {
		Boolean oldIsVisible = isVisible;
		isVisible = newIsVisible;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.DIAGRAM_ELEMENT__IS_VISIBLE, oldIsVisible,
					isVisible));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GraphElement getContainer() {
		if (eContainerFeatureID != UmaPackage.DIAGRAM_ELEMENT__CONTAINER)
			return null;
		return (GraphElement) eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GraphElement basicGetContainer() {
		if (eContainerFeatureID != UmaPackage.DIAGRAM_ELEMENT__CONTAINER)
			return null;
		return (GraphElement) eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetContainer(GraphElement newContainer,
			NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject) newContainer,
				UmaPackage.DIAGRAM_ELEMENT__CONTAINER, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setContainer(GraphElement newContainer) {
		if (newContainer != eInternalContainer()
				|| (eContainerFeatureID != UmaPackage.DIAGRAM_ELEMENT__CONTAINER && newContainer != null)) {
			if (EcoreUtil.isAncestor(this, (EObject) newContainer))
				throw new IllegalArgumentException(
						"Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newContainer != null)
				msgs = ((InternalEObject) newContainer).eInverseAdd(this,
						UmaPackage.GRAPH_ELEMENT__CONTAINED,
						GraphElement.class, msgs);
			msgs = basicSetContainer(newContainer, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.DIAGRAM_ELEMENT__CONTAINER, newContainer,
					newContainer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Reference> getReference() {
		if (reference == null) {
			reference = new EObjectWithInverseResolvingEList<Reference>(
					Reference.class, this,
					UmaPackage.DIAGRAM_ELEMENT__REFERENCE,
					UmaPackage.REFERENCE__REFERENCED);
		}
		return reference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Property> getProperty() {
		if (property == null) {
			property = new EObjectContainmentEList.Resolving<Property>(
					Property.class, this, UmaPackage.DIAGRAM_ELEMENT__PROPERTY);
		}
		return property;
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
		case UmaPackage.DIAGRAM_ELEMENT__CONTAINER:
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			return basicSetContainer((GraphElement) otherEnd, msgs);
		case UmaPackage.DIAGRAM_ELEMENT__REFERENCE:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getReference())
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
		case UmaPackage.DIAGRAM_ELEMENT__CONTAINER:
			return basicSetContainer(null, msgs);
		case UmaPackage.DIAGRAM_ELEMENT__REFERENCE:
			return ((InternalEList<?>) getReference()).basicRemove(otherEnd,
					msgs);
		case UmaPackage.DIAGRAM_ELEMENT__PROPERTY:
			return ((InternalEList<?>) getProperty()).basicRemove(otherEnd,
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
	public NotificationChain eBasicRemoveFromContainerFeature(
			NotificationChain msgs) {
		switch (eContainerFeatureID) {
		case UmaPackage.DIAGRAM_ELEMENT__CONTAINER:
			return eInternalContainer().eInverseRemove(this,
					UmaPackage.GRAPH_ELEMENT__CONTAINED, GraphElement.class,
					msgs);
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
		case UmaPackage.DIAGRAM_ELEMENT__IS_VISIBLE:
			return getIsVisible();
		case UmaPackage.DIAGRAM_ELEMENT__CONTAINER:
			if (resolve)
				return getContainer();
			return basicGetContainer();
		case UmaPackage.DIAGRAM_ELEMENT__REFERENCE:
			return getReference();
		case UmaPackage.DIAGRAM_ELEMENT__PROPERTY:
			return getProperty();
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
		case UmaPackage.DIAGRAM_ELEMENT__IS_VISIBLE:
			setIsVisible((Boolean) newValue);
			return;
		case UmaPackage.DIAGRAM_ELEMENT__CONTAINER:
			setContainer((GraphElement) newValue);
			return;
		case UmaPackage.DIAGRAM_ELEMENT__REFERENCE:
			getReference().clear();
			getReference().addAll((Collection<? extends Reference>) newValue);
			return;
		case UmaPackage.DIAGRAM_ELEMENT__PROPERTY:
			getProperty().clear();
			getProperty().addAll((Collection<? extends Property>) newValue);
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
		case UmaPackage.DIAGRAM_ELEMENT__IS_VISIBLE:
			setIsVisible(IS_VISIBLE_EDEFAULT);
			return;
		case UmaPackage.DIAGRAM_ELEMENT__CONTAINER:
			setContainer((GraphElement) null);
			return;
		case UmaPackage.DIAGRAM_ELEMENT__REFERENCE:
			getReference().clear();
			return;
		case UmaPackage.DIAGRAM_ELEMENT__PROPERTY:
			getProperty().clear();
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
		case UmaPackage.DIAGRAM_ELEMENT__IS_VISIBLE:
			return IS_VISIBLE_EDEFAULT == null ? isVisible != null
					: !IS_VISIBLE_EDEFAULT.equals(isVisible);
		case UmaPackage.DIAGRAM_ELEMENT__CONTAINER:
			return basicGetContainer() != null;
		case UmaPackage.DIAGRAM_ELEMENT__REFERENCE:
			return reference != null && !reference.isEmpty();
		case UmaPackage.DIAGRAM_ELEMENT__PROPERTY:
			return property != null && !property.isEmpty();
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
		result.append(" (isVisible: "); //$NON-NLS-1$
		result.append(isVisible);
		result.append(')');
		return result.toString();
	}

} //DiagramElementImpl
