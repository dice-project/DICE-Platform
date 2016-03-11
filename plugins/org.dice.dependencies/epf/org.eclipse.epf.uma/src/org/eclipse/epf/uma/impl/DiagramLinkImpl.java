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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

import org.eclipse.epf.uma.Diagram;
import org.eclipse.epf.uma.DiagramLink;
import org.eclipse.epf.uma.GraphElement;
import org.eclipse.epf.uma.Point;
import org.eclipse.epf.uma.UmaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Diagram Link</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.impl.DiagramLinkImpl#getZoom <em>Zoom</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.DiagramLinkImpl#getViewport <em>Viewport</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.DiagramLinkImpl#getDiagram <em>Diagram</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.DiagramLinkImpl#getGraphElement <em>Graph Element</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DiagramLinkImpl extends DiagramElementImpl implements DiagramLink {
	/**
	 * The default value of the '{@link #getZoom() <em>Zoom</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getZoom()
	 * @generated
	 * @ordered
	 */
	protected static final Double ZOOM_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getZoom() <em>Zoom</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getZoom()
	 * @generated
	 * @ordered
	 */
	protected Double zoom = ZOOM_EDEFAULT;

	/**
	 * The cached value of the '{@link #getViewport() <em>Viewport</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getViewport()
	 * @generated
	 * @ordered
	 */
	protected Point viewport;

	/**
	 * The cached value of the '{@link #getDiagram() <em>Diagram</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDiagram()
	 * @generated
	 * @ordered
	 */
	protected Diagram diagram;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DiagramLinkImpl() {
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
		return UmaPackage.Literals.DIAGRAM_LINK;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Double getZoom() {
		return zoom;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setZoom(Double newZoom) {
		Double oldZoom = zoom;
		zoom = newZoom;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.DIAGRAM_LINK__ZOOM, oldZoom, zoom));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Point getViewport() {
		if (viewport != null && ((EObject) viewport).eIsProxy()) {
			InternalEObject oldViewport = (InternalEObject) viewport;
			viewport = (Point) eResolveProxy(oldViewport);
			if (viewport != oldViewport) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							UmaPackage.DIAGRAM_LINK__VIEWPORT, oldViewport,
							viewport));
			}
		}
		return viewport;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Point basicGetViewport() {
		return viewport;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setViewport(Point newViewport) {
		Point oldViewport = viewport;
		viewport = newViewport;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.DIAGRAM_LINK__VIEWPORT, oldViewport, viewport));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GraphElement getGraphElement() {
		if (eContainerFeatureID != UmaPackage.DIAGRAM_LINK__GRAPH_ELEMENT)
			return null;
		return (GraphElement) eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GraphElement basicGetGraphElement() {
		if (eContainerFeatureID != UmaPackage.DIAGRAM_LINK__GRAPH_ELEMENT)
			return null;
		return (GraphElement) eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGraphElement(GraphElement newGraphElement,
			NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject) newGraphElement,
				UmaPackage.DIAGRAM_LINK__GRAPH_ELEMENT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGraphElement(GraphElement newGraphElement) {
		if (newGraphElement != eInternalContainer()
				|| (eContainerFeatureID != UmaPackage.DIAGRAM_LINK__GRAPH_ELEMENT && newGraphElement != null)) {
			if (EcoreUtil.isAncestor(this, (EObject) newGraphElement))
				throw new IllegalArgumentException(
						"Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newGraphElement != null)
				msgs = ((InternalEObject) newGraphElement).eInverseAdd(this,
						UmaPackage.GRAPH_ELEMENT__LINK, GraphElement.class,
						msgs);
			msgs = basicSetGraphElement(newGraphElement, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.DIAGRAM_LINK__GRAPH_ELEMENT, newGraphElement,
					newGraphElement));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Diagram getDiagram() {
		if (diagram != null && ((EObject) diagram).eIsProxy()) {
			InternalEObject oldDiagram = (InternalEObject) diagram;
			diagram = (Diagram) eResolveProxy(oldDiagram);
			if (diagram != oldDiagram) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							UmaPackage.DIAGRAM_LINK__DIAGRAM, oldDiagram,
							diagram));
			}
		}
		return diagram;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Diagram basicGetDiagram() {
		return diagram;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDiagram(Diagram newDiagram,
			NotificationChain msgs) {
		Diagram oldDiagram = diagram;
		diagram = newDiagram;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
					Notification.SET, UmaPackage.DIAGRAM_LINK__DIAGRAM,
					oldDiagram, newDiagram);
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
	public void setDiagram(Diagram newDiagram) {
		if (newDiagram != diagram) {
			NotificationChain msgs = null;
			if (diagram != null)
				msgs = ((InternalEObject) diagram).eInverseRemove(this,
						UmaPackage.DIAGRAM__DIAGRAM_LINK, Diagram.class, msgs);
			if (newDiagram != null)
				msgs = ((InternalEObject) newDiagram).eInverseAdd(this,
						UmaPackage.DIAGRAM__DIAGRAM_LINK, Diagram.class, msgs);
			msgs = basicSetDiagram(newDiagram, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.DIAGRAM_LINK__DIAGRAM, newDiagram, newDiagram));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
		case UmaPackage.DIAGRAM_LINK__DIAGRAM:
			if (diagram != null)
				msgs = ((InternalEObject) diagram).eInverseRemove(this,
						UmaPackage.DIAGRAM__DIAGRAM_LINK, Diagram.class, msgs);
			return basicSetDiagram((Diagram) otherEnd, msgs);
		case UmaPackage.DIAGRAM_LINK__GRAPH_ELEMENT:
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			return basicSetGraphElement((GraphElement) otherEnd, msgs);
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
		case UmaPackage.DIAGRAM_LINK__DIAGRAM:
			return basicSetDiagram(null, msgs);
		case UmaPackage.DIAGRAM_LINK__GRAPH_ELEMENT:
			return basicSetGraphElement(null, msgs);
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
		case UmaPackage.DIAGRAM_LINK__GRAPH_ELEMENT:
			return eInternalContainer().eInverseRemove(this,
					UmaPackage.GRAPH_ELEMENT__LINK, GraphElement.class, msgs);
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
		case UmaPackage.DIAGRAM_LINK__ZOOM:
			return getZoom();
		case UmaPackage.DIAGRAM_LINK__VIEWPORT:
			if (resolve)
				return getViewport();
			return basicGetViewport();
		case UmaPackage.DIAGRAM_LINK__DIAGRAM:
			if (resolve)
				return getDiagram();
			return basicGetDiagram();
		case UmaPackage.DIAGRAM_LINK__GRAPH_ELEMENT:
			if (resolve)
				return getGraphElement();
			return basicGetGraphElement();
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
		case UmaPackage.DIAGRAM_LINK__ZOOM:
			setZoom((Double) newValue);
			return;
		case UmaPackage.DIAGRAM_LINK__VIEWPORT:
			setViewport((Point) newValue);
			return;
		case UmaPackage.DIAGRAM_LINK__DIAGRAM:
			setDiagram((Diagram) newValue);
			return;
		case UmaPackage.DIAGRAM_LINK__GRAPH_ELEMENT:
			setGraphElement((GraphElement) newValue);
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
		case UmaPackage.DIAGRAM_LINK__ZOOM:
			setZoom(ZOOM_EDEFAULT);
			return;
		case UmaPackage.DIAGRAM_LINK__VIEWPORT:
			setViewport((Point) null);
			return;
		case UmaPackage.DIAGRAM_LINK__DIAGRAM:
			setDiagram((Diagram) null);
			return;
		case UmaPackage.DIAGRAM_LINK__GRAPH_ELEMENT:
			setGraphElement((GraphElement) null);
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
		case UmaPackage.DIAGRAM_LINK__ZOOM:
			return ZOOM_EDEFAULT == null ? zoom != null : !ZOOM_EDEFAULT
					.equals(zoom);
		case UmaPackage.DIAGRAM_LINK__VIEWPORT:
			return viewport != null;
		case UmaPackage.DIAGRAM_LINK__DIAGRAM:
			return diagram != null;
		case UmaPackage.DIAGRAM_LINK__GRAPH_ELEMENT:
			return basicGetGraphElement() != null;
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
		result.append(" (zoom: "); //$NON-NLS-1$
		result.append(zoom);
		result.append(')');
		return result.toString();
	}

} //DiagramLinkImpl
