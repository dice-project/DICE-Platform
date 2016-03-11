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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.graphiti.mm.algorithms.AlgorithmsPackage;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;

import org.eclipse.graphiti.mm.impl.GraphicsAlgorithmContainerImpl;

import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.PictogramLink;
import org.eclipse.graphiti.mm.pictograms.PictogramsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Pictogram Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.impl.PictogramElementImpl#isVisible <em>Visible</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.impl.PictogramElementImpl#getGraphicsAlgorithm <em>Graphics Algorithm</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.impl.PictogramElementImpl#isActive <em>Active</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.impl.PictogramElementImpl#getLink <em>Link</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class PictogramElementImpl extends GraphicsAlgorithmContainerImpl implements PictogramElement {
	/**
	 * The default value of the '{@link #isVisible() <em>Visible</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isVisible()
	 * @generated
	 * @ordered
	 */
	protected static final boolean VISIBLE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isVisible() <em>Visible</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isVisible()
	 * @generated
	 * @ordered
	 */
	protected boolean visible = VISIBLE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getGraphicsAlgorithm() <em>Graphics Algorithm</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGraphicsAlgorithm()
	 * @generated
	 * @ordered
	 */
	protected GraphicsAlgorithm graphicsAlgorithm;

	/**
	 * The default value of the '{@link #isActive() <em>Active</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isActive()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ACTIVE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isActive() <em>Active</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isActive()
	 * @generated
	 * @ordered
	 */
	protected boolean active = ACTIVE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getLink() <em>Link</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLink()
	 * @generated
	 * @ordered
	 */
	protected PictogramLink link;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PictogramElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PictogramsPackage.Literals.PICTOGRAM_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVisible(boolean newVisible) {
		boolean oldVisible = visible;
		visible = newVisible;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PictogramsPackage.PICTOGRAM_ELEMENT__VISIBLE, oldVisible, visible));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GraphicsAlgorithm getGraphicsAlgorithm() {
		if (graphicsAlgorithm != null && graphicsAlgorithm.eIsProxy()) {
			InternalEObject oldGraphicsAlgorithm = (InternalEObject)graphicsAlgorithm;
			graphicsAlgorithm = (GraphicsAlgorithm)eResolveProxy(oldGraphicsAlgorithm);
			if (graphicsAlgorithm != oldGraphicsAlgorithm) {
				InternalEObject newGraphicsAlgorithm = (InternalEObject)graphicsAlgorithm;
				NotificationChain msgs =  oldGraphicsAlgorithm.eInverseRemove(this, AlgorithmsPackage.GRAPHICS_ALGORITHM__PICTOGRAM_ELEMENT, GraphicsAlgorithm.class, null);
				if (newGraphicsAlgorithm.eInternalContainer() == null) {
					msgs =  newGraphicsAlgorithm.eInverseAdd(this, AlgorithmsPackage.GRAPHICS_ALGORITHM__PICTOGRAM_ELEMENT, GraphicsAlgorithm.class, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PictogramsPackage.PICTOGRAM_ELEMENT__GRAPHICS_ALGORITHM, oldGraphicsAlgorithm, graphicsAlgorithm));
			}
		}
		return graphicsAlgorithm;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GraphicsAlgorithm basicGetGraphicsAlgorithm() {
		return graphicsAlgorithm;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGraphicsAlgorithm(GraphicsAlgorithm newGraphicsAlgorithm, NotificationChain msgs) {
		GraphicsAlgorithm oldGraphicsAlgorithm = graphicsAlgorithm;
		graphicsAlgorithm = newGraphicsAlgorithm;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PictogramsPackage.PICTOGRAM_ELEMENT__GRAPHICS_ALGORITHM, oldGraphicsAlgorithm, newGraphicsAlgorithm);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGraphicsAlgorithm(GraphicsAlgorithm newGraphicsAlgorithm) {
		if (newGraphicsAlgorithm != graphicsAlgorithm) {
			NotificationChain msgs = null;
			if (graphicsAlgorithm != null)
				msgs = ((InternalEObject)graphicsAlgorithm).eInverseRemove(this, AlgorithmsPackage.GRAPHICS_ALGORITHM__PICTOGRAM_ELEMENT, GraphicsAlgorithm.class, msgs);
			if (newGraphicsAlgorithm != null)
				msgs = ((InternalEObject)newGraphicsAlgorithm).eInverseAdd(this, AlgorithmsPackage.GRAPHICS_ALGORITHM__PICTOGRAM_ELEMENT, GraphicsAlgorithm.class, msgs);
			msgs = basicSetGraphicsAlgorithm(newGraphicsAlgorithm, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PictogramsPackage.PICTOGRAM_ELEMENT__GRAPHICS_ALGORITHM, newGraphicsAlgorithm, newGraphicsAlgorithm));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActive(boolean newActive) {
		boolean oldActive = active;
		active = newActive;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PictogramsPackage.PICTOGRAM_ELEMENT__ACTIVE, oldActive, active));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PictogramLink getLink() {
		if (link != null && link.eIsProxy()) {
			InternalEObject oldLink = (InternalEObject)link;
			link = (PictogramLink)eResolveProxy(oldLink);
			if (link != oldLink) {
				InternalEObject newLink = (InternalEObject)link;
				NotificationChain msgs =  oldLink.eInverseRemove(this, PictogramsPackage.PICTOGRAM_LINK__PICTOGRAM_ELEMENT, PictogramLink.class, null);
				if (newLink.eInternalContainer() == null) {
					msgs =  newLink.eInverseAdd(this, PictogramsPackage.PICTOGRAM_LINK__PICTOGRAM_ELEMENT, PictogramLink.class, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PictogramsPackage.PICTOGRAM_ELEMENT__LINK, oldLink, link));
			}
		}
		return link;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PictogramLink basicGetLink() {
		return link;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLink(PictogramLink newLink, NotificationChain msgs) {
		PictogramLink oldLink = link;
		link = newLink;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PictogramsPackage.PICTOGRAM_ELEMENT__LINK, oldLink, newLink);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLink(PictogramLink newLink) {
		if (newLink != link) {
			NotificationChain msgs = null;
			if (link != null)
				msgs = ((InternalEObject)link).eInverseRemove(this, PictogramsPackage.PICTOGRAM_LINK__PICTOGRAM_ELEMENT, PictogramLink.class, msgs);
			if (newLink != null)
				msgs = ((InternalEObject)newLink).eInverseAdd(this, PictogramsPackage.PICTOGRAM_LINK__PICTOGRAM_ELEMENT, PictogramLink.class, msgs);
			msgs = basicSetLink(newLink, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PictogramsPackage.PICTOGRAM_ELEMENT__LINK, newLink, newLink));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PictogramsPackage.PICTOGRAM_ELEMENT__GRAPHICS_ALGORITHM:
				if (graphicsAlgorithm != null)
					msgs = ((InternalEObject)graphicsAlgorithm).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PictogramsPackage.PICTOGRAM_ELEMENT__GRAPHICS_ALGORITHM, null, msgs);
				return basicSetGraphicsAlgorithm((GraphicsAlgorithm)otherEnd, msgs);
			case PictogramsPackage.PICTOGRAM_ELEMENT__LINK:
				if (link != null)
					msgs = ((InternalEObject)link).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PictogramsPackage.PICTOGRAM_ELEMENT__LINK, null, msgs);
				return basicSetLink((PictogramLink)otherEnd, msgs);
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
			case PictogramsPackage.PICTOGRAM_ELEMENT__GRAPHICS_ALGORITHM:
				return basicSetGraphicsAlgorithm(null, msgs);
			case PictogramsPackage.PICTOGRAM_ELEMENT__LINK:
				return basicSetLink(null, msgs);
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
			case PictogramsPackage.PICTOGRAM_ELEMENT__VISIBLE:
				return isVisible();
			case PictogramsPackage.PICTOGRAM_ELEMENT__GRAPHICS_ALGORITHM:
				if (resolve) return getGraphicsAlgorithm();
				return basicGetGraphicsAlgorithm();
			case PictogramsPackage.PICTOGRAM_ELEMENT__ACTIVE:
				return isActive();
			case PictogramsPackage.PICTOGRAM_ELEMENT__LINK:
				if (resolve) return getLink();
				return basicGetLink();
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
			case PictogramsPackage.PICTOGRAM_ELEMENT__VISIBLE:
				setVisible((Boolean)newValue);
				return;
			case PictogramsPackage.PICTOGRAM_ELEMENT__GRAPHICS_ALGORITHM:
				setGraphicsAlgorithm((GraphicsAlgorithm)newValue);
				return;
			case PictogramsPackage.PICTOGRAM_ELEMENT__ACTIVE:
				setActive((Boolean)newValue);
				return;
			case PictogramsPackage.PICTOGRAM_ELEMENT__LINK:
				setLink((PictogramLink)newValue);
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
			case PictogramsPackage.PICTOGRAM_ELEMENT__VISIBLE:
				setVisible(VISIBLE_EDEFAULT);
				return;
			case PictogramsPackage.PICTOGRAM_ELEMENT__GRAPHICS_ALGORITHM:
				setGraphicsAlgorithm((GraphicsAlgorithm)null);
				return;
			case PictogramsPackage.PICTOGRAM_ELEMENT__ACTIVE:
				setActive(ACTIVE_EDEFAULT);
				return;
			case PictogramsPackage.PICTOGRAM_ELEMENT__LINK:
				setLink((PictogramLink)null);
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
			case PictogramsPackage.PICTOGRAM_ELEMENT__VISIBLE:
				return visible != VISIBLE_EDEFAULT;
			case PictogramsPackage.PICTOGRAM_ELEMENT__GRAPHICS_ALGORITHM:
				return graphicsAlgorithm != null;
			case PictogramsPackage.PICTOGRAM_ELEMENT__ACTIVE:
				return active != ACTIVE_EDEFAULT;
			case PictogramsPackage.PICTOGRAM_ELEMENT__LINK:
				return link != null;
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
		result.append(" (visible: ");
		result.append(visible);
		result.append(", active: ");
		result.append(active);
		result.append(')');
		return result.toString();
	}

} //PictogramElementImpl
