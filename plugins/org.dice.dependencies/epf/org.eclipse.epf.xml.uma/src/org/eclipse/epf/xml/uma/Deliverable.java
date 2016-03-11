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
package org.eclipse.epf.xml.uma;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Deliverable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A Work Product that provides a description and definition for packaging other Work Products, and may be delivered to an internal or external party.  Therefore, a Deliverable aggregates other Work Products. Therefore, a Deliverable aggregates other Work Products.  A Deliverable is used to pre-define typical or recommended content in the form or work products that would be packaged for delivery.  The actual packaging of the Deliverable in an actual process or even project could be a modification of this recommendation.  Deliverables are used to represent an output from a process that has value, material or otherwise, to a client, customer or other stakeholder. 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.xml.uma.Deliverable#getGroup3 <em>Group3</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.Deliverable#getDeliveredWorkProduct <em>Delivered Work Product</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.xml.uma.UmaPackage#getDeliverable()
 * @model extendedMetaData="name='Deliverable' kind='elementOnly'"
 * @generated
 */
public interface Deliverable extends WorkProduct {
	/**
	 * Returns the value of the '<em><b>Group3</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Group3</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Group3</em>' attribute list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getDeliverable_Group3()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='group:30'"
	 * @generated
	 */
	FeatureMap getGroup3();

	/**
	 * Returns the value of the '<em><b>Delivered Work Product</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Delivered Work Product</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Delivered Work Product</em>' attribute list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getDeliverable_DeliveredWorkProduct()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='DeliveredWorkProduct' group='#group:30'"
	 * @generated
	 */
	EList<String> getDeliveredWorkProduct();

} // Deliverable
