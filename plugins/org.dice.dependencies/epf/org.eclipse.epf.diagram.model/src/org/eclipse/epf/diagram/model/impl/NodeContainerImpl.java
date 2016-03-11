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
package org.eclipse.epf.diagram.model.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.epf.diagram.model.ModelPackage;
import org.eclipse.epf.diagram.model.Node;
import org.eclipse.epf.diagram.model.NodeContainer;


/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Node Container</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.diagram.model.impl.NodeContainerImpl#getNodes <em>Nodes</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class NodeContainerImpl extends NodeImpl implements
		NodeContainer {
	/**
	 * The cached value of the '{@link #getNodes() <em>Nodes</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getNodes()
	 * @generated
	 * @ordered
	 */
	protected EList<Node> nodes;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected NodeContainerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.NODE_CONTAINER;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Node> getNodes() {
		if (nodes == null) {
			nodes = new EObjectContainmentEList<Node>(Node.class, this, ModelPackage.NODE_CONTAINER__NODES);
		}
		return nodes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.NODE_CONTAINER__NODES:
				return ((InternalEList<?>)getNodes()).basicRemove(otherEnd, msgs);
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
			case ModelPackage.NODE_CONTAINER__NODES:
				return getNodes();
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
			case ModelPackage.NODE_CONTAINER__NODES:
				getNodes().clear();
				getNodes().addAll((Collection<? extends Node>)newValue);
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
			case ModelPackage.NODE_CONTAINER__NODES:
				getNodes().clear();
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
			case ModelPackage.NODE_CONTAINER__NODES:
				return nodes != null && !nodes.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.diagram.model.impl.NodeImpl#setReadOnly(boolean)
	 */
	public void setReadOnly(boolean newReadOnly) {
		super.setReadOnly(newReadOnly);
		if (newReadOnly) {
			for (Iterator iter = getNodes().iterator(); iter.hasNext();) {
				Node child = (Node) iter.next();
				child.setReadOnly(newReadOnly);
			}
		}
	}

	/**
	 * @param removedNodes
	 */
	protected void pushBackToDiagram(Collection removedNodes) {
		ActivityDetailDiagramImpl diagram = (ActivityDetailDiagramImpl) getDiagram();
		Collection breakdownElements = diagram.getAllBreakdownElements(true);
		for (Iterator iter = removedNodes.iterator(); iter.hasNext();) {
			Node node = (Node) iter.next();
			if (!breakdownElements.contains(node.getObject())) {
				iter.remove();
			}
		}
		// for (Iterator iter = diagram.getNodes().iterator(); iter.hasNext();)
		// {
		// Object node = iter.next();
		// if(node instanceof RoleTaskComposite){
		// List list = ((RoleTaskComposite)node).getNodes();
		// boolean destroy = true;
		// for(Iterator iterator = list.iterator(); iterator.hasNext();){
		// Object obj = iterator.next();
		// if(obj instanceof TaskNode) destroy = false;
		// }
		// if(destroy) iter.remove();
		// }
		// }
		if (!removedNodes.isEmpty()) {
			diagram.getNodes().addAll(removedNodes);
		}
	}
	
	protected void selectNodes(Collection nodes) {
		if (!nodes.isEmpty()) {
			for (Iterator iter = nodes.iterator(); iter.hasNext();) {
				NodeImpl node = (NodeImpl) iter.next();
				node.visible = true;
			}
			for (Iterator iter = getNodes().iterator(); iter.hasNext();) {
				NodeImpl node = (NodeImpl) iter.next();
				if(!nodes.contains(node)) {
					node.visible = false;
				}
			}
			getNodes().addAll(nodes);
		}
		else {
			for (Iterator iter = getNodes().iterator(); iter.hasNext();) {
				NodeImpl node = (NodeImpl) iter.next();
				node.visible = false;
			}
		}
	}
	
	@Override
	protected void dispose() {
		// dispose all nodes
		//
		for (Object node : new ArrayList<Node>(nodes)) {		
			((NodeImpl)node).dispose();
		}
		
		super.dispose();
	}
	
} // NodeContainerImpl
