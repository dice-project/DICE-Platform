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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Deliverable Description</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A generalized Work Product Description that is used to store the textual description for a Deliverable.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.xml.uma.DeliverableDescription#getExternalDescription <em>External Description</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.DeliverableDescription#getPackagingGuidance <em>Packaging Guidance</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.xml.uma.UmaPackage#getDeliverableDescription()
 * @model extendedMetaData="name='DeliverableDescription' kind='elementOnly'"
 * @generated
 */
public interface DeliverableDescription extends WorkProductDescription {
	/**
	 * Returns the value of the '<em><b>External Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The description of the Deliverable used for client documents (proposal, statements of work or contractual agreements).  It might use a different language and follow legal constraints.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>External Description</em>' attribute.
	 * @see #setExternalDescription(String)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getDeliverableDescription_ExternalDescription()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='ExternalDescription'"
	 * @generated
	 */
	String getExternalDescription();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.DeliverableDescription#getExternalDescription <em>External Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>External Description</em>' attribute.
	 * @see #getExternalDescription()
	 * @generated
	 */
	void setExternalDescription(String value);

	/**
	 * Returns the value of the '<em><b>Packaging Guidance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Provides guidance on how to assemble the deliverable from all its required inputs.  This section describes the most common content medium and format.  Distribution of the deliverable is addressed in this section, if necessary.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Packaging Guidance</em>' attribute.
	 * @see #setPackagingGuidance(String)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getDeliverableDescription_PackagingGuidance()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='PackagingGuidance'"
	 * @generated
	 */
	String getPackagingGuidance();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.DeliverableDescription#getPackagingGuidance <em>Packaging Guidance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Packaging Guidance</em>' attribute.
	 * @see #getPackagingGuidance()
	 * @generated
	 */
	void setPackagingGuidance(String value);

} // DeliverableDescription
