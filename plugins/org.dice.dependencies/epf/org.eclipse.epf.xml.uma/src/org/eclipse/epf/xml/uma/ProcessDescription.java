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
 * A representation of the model object '<em><b>Process Description</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A generalized Activity Description that is used to store the textual description for a Process.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.xml.uma.ProcessDescription#getScope <em>Scope</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.ProcessDescription#getUsageNotes <em>Usage Notes</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.xml.uma.UmaPackage#getProcessDescription()
 * @model extendedMetaData="name='ProcessDescription' kind='elementOnly'"
 * @generated
 */
public interface ProcessDescription extends ActivityDescription {
	/**
	 * Returns the value of the '<em><b>Scope</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Defines the scope of the Process, i.e. which types of projects does it address and which not.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Scope</em>' attribute.
	 * @see #setScope(String)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getProcessDescription_Scope()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='Scope'"
	 * @generated
	 */
	String getScope();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.ProcessDescription#getScope <em>Scope</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Scope</em>' attribute.
	 * @see #getScope()
	 * @generated
	 */
	void setScope(String value);

	/**
	 * Returns the value of the '<em><b>Usage Notes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Any additional notes on how to apply and instantiate this process for a project.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Usage Notes</em>' attribute.
	 * @see #setUsageNotes(String)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getProcessDescription_UsageNotes()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='UsageNotes'"
	 * @generated
	 */
	String getUsageNotes();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.ProcessDescription#getUsageNotes <em>Usage Notes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Usage Notes</em>' attribute.
	 * @see #getUsageNotes()
	 * @generated
	 */
	void setUsageNotes(String value);

} // ProcessDescription
