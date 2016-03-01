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

import java.util.Collection;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.epf.diagram.model.ModelPackage;
import org.eclipse.epf.diagram.model.RoleNode;


/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Role Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class RoleNodeImpl extends NamedNodeImpl implements RoleNode {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 */
	protected RoleNodeImpl() {
		super();

		methodElementAdapter = new RoleDescriptorAdapter();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.ROLE_NODE;
	}

	private class RoleDescriptorAdapter extends MethodElementAdapter {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.diagram.model.impl.NodeImpl#getMethodElementAdapterType()
	 */
	public Class getMethodElementAdapterType() {
		return RoleDescriptorAdapter.class;
	}
} // RoleNodeImpl
