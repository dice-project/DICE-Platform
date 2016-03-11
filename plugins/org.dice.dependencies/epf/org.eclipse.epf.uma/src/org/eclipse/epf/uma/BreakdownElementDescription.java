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
 * A representation of the model object '<em><b>Breakdown Element Description</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.BreakdownElementDescription#getUsageGuidance <em>Usage Guidance</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.uma.UmaPackage#getBreakdownElementDescription()
 * @model
 * @generated
 */
public interface BreakdownElementDescription extends ContentDescription {
	/**
	 * Returns the value of the '<em><b>Usage Guidance</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Provides information and guidance on the meaning of the Boolean flag values and under what circumstances they should be overridden. For example, it describes why the breakdown element is optional or considerations for repeating it and differences in the individual occurrences of this Breakdown Element across the lifecycle.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Usage Guidance</em>' attribute.
	 * @see #setUsageGuidance(String)
	 * @see org.eclipse.epf.uma.UmaPackage#getBreakdownElementDescription_UsageGuidance()
	 * @model default="" dataType="org.eclipse.epf.uma.String" ordered="false"
	 * @generated
	 */
	String getUsageGuidance();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.BreakdownElementDescription#getUsageGuidance <em>Usage Guidance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Usage Guidance</em>' attribute.
	 * @see #getUsageGuidance()
	 * @generated
	 */
	void setUsageGuidance(String value);

} // BreakdownElementDescription
