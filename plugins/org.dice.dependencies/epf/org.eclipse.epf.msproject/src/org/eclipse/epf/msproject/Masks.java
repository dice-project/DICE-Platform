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
 * A representation of the model object '<em><b>Masks</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.msproject.Masks#getMask <em>Mask</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.msproject.MsprojectPackage#getMasks()
 * @model extendedMetaData="name='Masks_._type' kind='elementOnly'"
 * @generated
 */
public interface Masks extends EObject {
	/**
	 * Returns the value of the '<em><b>Mask</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.epf.msproject.Mask}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The four elements of the mask constitute the format in which the outline code must appear.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Mask</em>' containment reference list.
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getMasks_Mask()
	 * @model type="org.eclipse.epf.msproject.Mask" containment="true" resolveProxies="false"
	 *        extendedMetaData="kind='element' name='Mask' namespace='##targetNamespace'"
	 * @generated
	 */
	EList getMask();

} // Masks
