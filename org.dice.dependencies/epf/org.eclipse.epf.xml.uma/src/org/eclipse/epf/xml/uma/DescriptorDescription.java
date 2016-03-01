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
 * A representation of the model object '<em><b>Descriptor Description</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A generalized Breakdown Element Description that is used to store the textual description for a Descriptor.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.xml.uma.DescriptorDescription#getRefinedDescription <em>Refined Description</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.xml.uma.UmaPackage#getDescriptorDescription()
 * @model extendedMetaData="name='DescriptorDescription' kind='elementOnly'"
 * @generated
 */
public interface DescriptorDescription extends BreakdownElementDescription {
	/**
	 * Returns the value of the '<em><b>Refined Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A Descriptor might add refinements to the main description of the Content Element it refers to.  For example, it could provide additional information about a Work Product relevant for the specific point in time in the process this Work Product type is being used.  It could describe additional skills needed for a Role at that particular point in time in a process, etc. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Refined Description</em>' attribute.
	 * @see #setRefinedDescription(String)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getDescriptorDescription_RefinedDescription()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='RefinedDescription'"
	 * @generated
	 */
	String getRefinedDescription();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.DescriptorDescription#getRefinedDescription <em>Refined Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Refined Description</em>' attribute.
	 * @see #getRefinedDescription()
	 * @generated
	 */
	void setRefinedDescription(String value);

} // DescriptorDescription
