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
 * A representation of the model object '<em><b>Deliverable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A deliverable is a Work Product that provides a description and definition for packaging other Work Products, and may be delivered to an internal or external party.  Therefore, a Deliverable aggregates other Work Products. Therefore, a Deliverable aggregates other Work Products.  A Deliverable is used to pre-define typical or recommended content in the form or work products that would be packaged for delivery.  The actual packaging of the Deliverable in an actual process or even project could be a modification of this recommendation.  Deliverables are used to represent an output from a process that has value, material or otherwise, to a client, customer or other stakeholder. 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.Deliverable#getDeliveredWorkProducts <em>Delivered Work Products</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.uma.UmaPackage#getDeliverable()
 * @model
 * @generated
 */
public interface Deliverable extends WorkProduct {
	/**
	 * Returns the value of the '<em><b>Delivered Work Products</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.WorkProduct}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Delivered Work Products</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Delivered Work Products</em>' reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getDeliverable_DeliveredWorkProducts()
	 * @model ordered="false"
	 * @generated
	 */
	List<WorkProduct> getDeliveredWorkProducts();

} // Deliverable
