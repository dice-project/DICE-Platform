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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Deliverable Description</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.DeliverableDescription#getExternalDescription <em>External Description</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.DeliverableDescription#getPackagingGuidance <em>Packaging Guidance</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.uma.UmaPackage#getDeliverableDescription()
 * @model
 * @generated
 */
public interface DeliverableDescription extends WorkProductDescription {
	/**
	 * Returns the value of the '<em><b>External Description</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The description of the Deliverable used for client documents (proposal, statements of work or contractual agreements).  It might use a different language and follow legal constraints.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>External Description</em>' attribute.
	 * @see #setExternalDescription(String)
	 * @see org.eclipse.epf.uma.UmaPackage#getDeliverableDescription_ExternalDescription()
	 * @model default="" dataType="org.eclipse.epf.uma.String" ordered="false"
	 * @generated
	 */
	String getExternalDescription();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.DeliverableDescription#getExternalDescription <em>External Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>External Description</em>' attribute.
	 * @see #getExternalDescription()
	 * @generated
	 */
	void setExternalDescription(String value);

	/**
	 * Returns the value of the '<em><b>Packaging Guidance</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Provides guidance on how to assemble the deliverable from all its required inputs.  This section describes the most common content medium and format.  Distribution of the deliverable is addressed in this section, if necessary.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Packaging Guidance</em>' attribute.
	 * @see #setPackagingGuidance(String)
	 * @see org.eclipse.epf.uma.UmaPackage#getDeliverableDescription_PackagingGuidance()
	 * @model default="" dataType="org.eclipse.epf.uma.String" ordered="false"
	 * @generated
	 */
	String getPackagingGuidance();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.DeliverableDescription#getPackagingGuidance <em>Packaging Guidance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Packaging Guidance</em>' attribute.
	 * @see #getPackagingGuidance()
	 * @generated
	 */
	void setPackagingGuidance(String value);

} // DeliverableDescription
