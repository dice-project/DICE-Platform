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
 * A representation of the model object '<em><b>Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A generalized Method Element that represents a condition or restriction expressed in natural language text or in a machine readable language for the purpose of declaring some of the semantics of a Method Element.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.xml.uma.Constraint#getMainDescription <em>Main Description</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.xml.uma.UmaPackage#getConstraint()
 * @model extendedMetaData="name='Constraint' kind='elementOnly'"
 * @generated
 */
public interface Constraint extends MethodElement {
	/**
	 * Returns the value of the '<em><b>Main Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Stores the main definition of the constraint.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Main Description</em>' attribute.
	 * @see #setMainDescription(String)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getConstraint_MainDescription()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='mainDescription'"
	 * @generated
	 */
	String getMainDescription();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.Constraint#getMainDescription <em>Main Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Main Description</em>' attribute.
	 * @see #getMainDescription()
	 * @generated
	 */
	void setMainDescription(String value);

} // Constraint
