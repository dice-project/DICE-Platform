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
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.epf.uma.DiagramElement;
import org.eclipse.epf.uma.DiagramLink;
import org.eclipse.epf.uma.GraphConnector;
import org.eclipse.epf.uma.GraphElement;
import org.eclipse.epf.uma.Point;
import org.eclipse.epf.uma.SemanticModelBridge;
import org.eclipse.epf.uma.UmaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Graph Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.impl.GraphElementImpl#getContained <em>Contained</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.GraphElementImpl#getPosition <em>Position</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.GraphElementImpl#getLink <em>Link</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.GraphElementImpl#getAnchorage <em>Anchorage</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.GraphElementImpl#getSemanticModel <em>Semantic Model</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class GraphElementImpl extends DiagramElementImpl implements
		GraphElement {
	/**
	 * The cached value of the '{@link #getContained() <em>Contained</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContained()
	 * @generated
	 * @ordered
	 */
	protected EList<DiagramElement> contained;

	/**
	 * The cached value of the '{@link #getPosition() <em>Position</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPosition()
	 * @generated
	 * @ordered
	 */
	protected Point position;

	/**
	 * The cached value of the '{@link #getLink() <em>Link</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLink()
	 * @generated
	 * @ordered
	 */
	protected EList<DiagramLink> link;

	/**
	 * The cached value of the '{@link #getAnchorage() <em>Anchorage</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAnchorage()
	 * @generated
	 * @ordered
	 */
	protected EList<GraphConnector> anchorage;

	/**
	 * The cached value of the '{@link #getSemanticModel() <em>Semantic Model</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSemanticModel()
	 * @generated
	 * @ordered
	 */
	protected SemanticModelBridge semanticModel;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GraphElementImpl() {
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
		return UmaPackage.Literals.GRAPH_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Point getPosition() {
		if (position != null && ((EObject) position).eIsProxy()) {
			InternalEObject oldPosition = (InternalEObject) position;
			position = (Point) eResolveProxy(oldPosition);
			if (position != oldPosition) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							UmaPackage.GRAPH_ELEMENT__POSITION, oldPosition,
							position));
			}
		}
		return position;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Point basicGetPosition() {
		return position;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPosition(Point newPosition) {
		Point oldPosition = position;
		position = newPosition;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.GRAPH_ELEMENT__POSITION, oldPosition, position));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<DiagramElement> getContained() {
		if (contained == null) {
			contained = new EObjectContainmentWithInverseEList.Resolving<DiagramElement>(
					DiagramElement.class, this,
					UmaPackage.GRAPH_ELEMENT__CONTAINED,
					UmaPackage.DIAGRAM_ELEMENT__CONTAINER);
		}
		return contained;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<DiagramLink> getLink() {
		if (link == null) {
			link = new EObjectContainmentWithInverseEList.Resolving<DiagramLink>(
					DiagramLink.class, this, UmaPackage.GRAPH_ELEMENT__LINK,
					UmaPackage.DIAGRAM_LINK__GRAPH_ELEMENT);
		}
		return link;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<GraphConnector> getAnchorage() {
		if (anchorage == null) {
			anchorage = new EObjectContainmentWithInverseEList.Resolving<GraphConnector>(
					GraphConnector.class, this,
					UmaPackage.GRAPH_ELEMENT__ANCHORAGE,
					UmaPackage.GRAPH_CONNECTOR__GRAPH_ELEMENT);
		}
		return anchorage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SemanticModelBridge getSemanticModel() {
		if (semanticModel != null && ((EObject) semanticModel).eIsProxy()) {
			InternalEObject oldSemanticModel = (InternalEObject) semanticModel;
			semanticModel = (SemanticModelBridge) eResolveProxy(oldSemanticModel);
			if (semanticModel != oldSemanticModel) {
				InternalEObject newSemanticModel = (InternalEObject) semanticModel;
				NotificationChain msgs = oldSemanticModel.eInverseRemove(this,
						UmaPackage.SEMANTIC_MODEL_BRIDGE__GRAPH_ELEMENT,
						SemanticModelBridge.class, null);
				if (newSemanticModel.eInternalContainer() == null) {
					msgs = newSemanticModel.eInverseAdd(this,
							UmaPackage.SEMANTIC_MODEL_BRIDGE__GRAPH_ELEMENT,
							SemanticModelBridge.class, msgs);
				}
				if (msgs != null)
					msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							UmaPackage.GRAPH_ELEMENT__SEMANTIC_MODEL,
							oldSemanticModel, semanticModel));
			}
		}
		return semanticModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SemanticModelBridge basicGetSemanticModel() {
		return semanticModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSemanticModel(
			SemanticModelBridge newSemanticModel, NotificationChain msgs) {
		SemanticModelBridge oldSemanticModel = semanticModel;
		semanticModel = newSemanticModel;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
					Notification.SET, UmaPackage.GRAPH_ELEMENT__SEMANTIC_MODEL,
					oldSemanticModel, newSemanticModel);
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
	public void setSemanticModel(SemanticModelBridge newSemanticModel) {
		if (newSemanticModel != semanticModel) {
			NotificationChain msgs = null;
			if (semanticModel != null)
				msgs = ((InternalEObject) semanticModel).eInverseRemove(this,
						UmaPackage.SEMANTIC_MODEL_BRIDGE__GRAPH_ELEMENT,
						SemanticModelBridge.class, msgs);
			if (newSemanticModel != null)
				msgs = ((InternalEObject) newSemanticModel).eInverseAdd(this,
						UmaPackage.SEMANTIC_MODEL_BRIDGE__GRAPH_ELEMENT,
						SemanticModelBridge.class, msgs);
			msgs = basicSetSemanticModel(newSemanticModel, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.GRAPH_ELEMENT__SEMANTIC_MODEL, newSemanticModel,
					newSemanticModel));
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
		case UmaPackage.GRAPH_ELEMENT__CONTAINED:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getContained())
					.basicAdd(otherEnd, msgs);
		case UmaPackage.GRAPH_ELEMENT__LINK:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getLink())
					.basicAdd(otherEnd, msgs);
		case UmaPackage.GRAPH_ELEMENT__ANCHORAGE:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getAnchorage())
					.basicAdd(otherEnd, msgs);
		case UmaPackage.GRAPH_ELEMENT__SEMANTIC_MODEL:
			if (semanticModel != null)
				msgs = ((InternalEObject) semanticModel).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE
								- UmaPackage.GRAPH_ELEMENT__SEMANTIC_MODEL,
						null, msgs);
			return basicSetSemanticModel((SemanticModelBridge) otherEnd, msgs);
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
		case UmaPackage.GRAPH_ELEMENT__CONTAINED:
			return ((InternalEList<?>) getContained()).basicRemove(otherEnd,
					msgs);
		case UmaPackage.GRAPH_ELEMENT__LINK:
			return ((InternalEList<?>) getLink()).basicRemove(otherEnd, msgs);
		case UmaPackage.GRAPH_ELEMENT__ANCHORAGE:
			return ((InternalEList<?>) getAnchorage()).basicRemove(otherEnd,
					msgs);
		case UmaPackage.GRAPH_ELEMENT__SEMANTIC_MODEL:
			return basicSetSemanticModel(null, msgs);
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
		case UmaPackage.GRAPH_ELEMENT__CONTAINED:
			return getContained();
		case UmaPackage.GRAPH_ELEMENT__POSITION:
			if (resolve)
				return getPosition();
			return basicGetPosition();
		case UmaPackage.GRAPH_ELEMENT__LINK:
			return getLink();
		case UmaPackage.GRAPH_ELEMENT__ANCHORAGE:
			return getAnchorage();
		case UmaPackage.GRAPH_ELEMENT__SEMANTIC_MODEL:
			if (resolve)
				return getSemanticModel();
			return basicGetSemanticModel();
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
		case UmaPackage.GRAPH_ELEMENT__CONTAINED:
			getContained().clear();
			getContained().addAll(
					(Collection<? extends DiagramElement>) newValue);
			return;
		case UmaPackage.GRAPH_ELEMENT__POSITION:
			setPosition((Point) newValue);
			return;
		case UmaPackage.GRAPH_ELEMENT__LINK:
			getLink().clear();
			getLink().addAll((Collection<? extends DiagramLink>) newValue);
			return;
		case UmaPackage.GRAPH_ELEMENT__ANCHORAGE:
			getAnchorage().clear();
			getAnchorage().addAll(
					(Collection<? extends GraphConnector>) newValue);
			return;
		case UmaPackage.GRAPH_ELEMENT__SEMANTIC_MODEL:
			setSemanticModel((SemanticModelBridge) newValue);
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
		case UmaPackage.GRAPH_ELEMENT__CONTAINED:
			getContained().clear();
			return;
		case UmaPackage.GRAPH_ELEMENT__POSITION:
			setPosition((Point) null);
			return;
		case UmaPackage.GRAPH_ELEMENT__LINK:
			getLink().clear();
			return;
		case UmaPackage.GRAPH_ELEMENT__ANCHORAGE:
			getAnchorage().clear();
			return;
		case UmaPackage.GRAPH_ELEMENT__SEMANTIC_MODEL:
			setSemanticModel((SemanticModelBridge) null);
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
		case UmaPackage.GRAPH_ELEMENT__CONTAINED:
			return contained != null && !contained.isEmpty();
		case UmaPackage.GRAPH_ELEMENT__POSITION:
			return position != null;
		case UmaPackage.GRAPH_ELEMENT__LINK:
			return link != null && !link.isEmpty();
		case UmaPackage.GRAPH_ELEMENT__ANCHORAGE:
			return anchorage != null && !anchorage.isEmpty();
		case UmaPackage.GRAPH_ELEMENT__SEMANTIC_MODEL:
			return semanticModel != null;
		}
		return super.eIsSet(featureID);
	}

} //GraphElementImpl
