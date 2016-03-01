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
package org.eclipse.epf.uma;

import java.util.List;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Process Family</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A Delivery Model Family is a convenient grouping of Delivery Processes and Capability Patterns of interest to some specific user community.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.ProcessFamily#getDeliveryProcesses <em>Delivery Processes</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.uma.UmaPackage#getProcessFamily()
 * @model
 * @generated
 */
public interface ProcessFamily extends MethodConfiguration {
	/**
	 * Returns the value of the '<em><b>Delivery Processes</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.DeliveryProcess}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Delivery Processes</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Delivery Processes</em>' reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getProcessFamily_DeliveryProcesses()
	 * @model ordered="false"
	 * @generated
	 */
	List<DeliveryProcess> getDeliveryProcesses();

} // ProcessFamily
