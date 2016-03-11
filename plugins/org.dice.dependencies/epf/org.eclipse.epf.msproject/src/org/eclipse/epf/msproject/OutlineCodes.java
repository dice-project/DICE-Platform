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
package org.eclipse.epf.msproject;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Outline Codes</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.msproject.OutlineCodes#getOutlineCode <em>Outline Code</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.msproject.MsprojectPackage#getOutlineCodes()
 * @model extendedMetaData="name='OutlineCodes_._type' kind='elementOnly'"
 * @generated
 */
public interface OutlineCodes extends EObject {
	/**
	 * Returns the value of the '<em><b>Outline Code</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.epf.msproject.OutlineCode}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The individual outline codes.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Outline Code</em>' containment reference list.
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getOutlineCodes_OutlineCode()
	 * @model type="org.eclipse.epf.msproject.OutlineCode" containment="true" resolveProxies="false"
	 *        extendedMetaData="kind='element' name='OutlineCode' namespace='##targetNamespace'"
	 * @generated
	 */
	EList getOutlineCode();

} // OutlineCodes
