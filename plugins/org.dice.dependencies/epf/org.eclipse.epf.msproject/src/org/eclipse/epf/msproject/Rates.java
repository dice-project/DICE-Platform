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
 * A representation of the model object '<em><b>Rates</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.msproject.Rates#getRate <em>Rate</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.msproject.MsprojectPackage#getRates()
 * @model extendedMetaData="name='Rates_._type' kind='elementOnly'"
 * @generated
 */
public interface Rates extends EObject {
	/**
	 * Returns the value of the '<em><b>Rate</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.epf.msproject.Rate}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The definition of a time period, and the rates applicable for 
	 *                                             the resource during that period.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Rate</em>' containment reference list.
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getRates_Rate()
	 * @model type="org.eclipse.epf.msproject.Rate" containment="true" resolveProxies="false" upper="25"
	 *        extendedMetaData="kind='element' name='Rate' namespace='##targetNamespace'"
	 * @generated
	 */
	EList getRate();

} // Rates
