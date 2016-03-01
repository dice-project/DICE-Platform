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
 * A representation of the model object '<em><b>Descriptor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * An abstract generalization for special Breakdown Elements that references one concrete Content Element.  A descriptor provides a representation of a Content Element within breakdown structures.  In addition to just referencing Content Elements it allows overriding the Content Elements structural relationships by defining its own sets of associations.
 * Descriptors are the key concept for realizing the separation of processes from method content.  A Descriptor can be characterized as a reference object for one particular Content Element, which has its own relationships and properties.  When a Descriptor is created it shall be provided with congruent copies of the relationships defined for the referenced content element.  However, a user can modify these relationships for the particular process situation for which the descriptor has been created. 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.xml.uma.Descriptor#isIsSynchronizedWithSource <em>Is Synchronized With Source</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.xml.uma.UmaPackage#getDescriptor()
 * @model extendedMetaData="name='Descriptor' kind='elementOnly'"
 * @generated
 */
public interface Descriptor extends BreakdownElement {
	/**
	 * Returns the value of the '<em><b>Is Synchronized With Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Synchronized With Source</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Synchronized With Source</em>' attribute.
	 * @see #isSetIsSynchronizedWithSource()
	 * @see #unsetIsSynchronizedWithSource()
	 * @see #setIsSynchronizedWithSource(boolean)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getDescriptor_IsSynchronizedWithSource()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='isSynchronizedWithSource'"
	 * @generated
	 */
	boolean isIsSynchronizedWithSource();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.Descriptor#isIsSynchronizedWithSource <em>Is Synchronized With Source</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Synchronized With Source</em>' attribute.
	 * @see #isSetIsSynchronizedWithSource()
	 * @see #unsetIsSynchronizedWithSource()
	 * @see #isIsSynchronizedWithSource()
	 * @generated
	 */
	void setIsSynchronizedWithSource(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.xml.uma.Descriptor#isIsSynchronizedWithSource <em>Is Synchronized With Source</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetIsSynchronizedWithSource()
	 * @see #isIsSynchronizedWithSource()
	 * @see #setIsSynchronizedWithSource(boolean)
	 * @generated
	 */
	void unsetIsSynchronizedWithSource();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.xml.uma.Descriptor#isIsSynchronizedWithSource <em>Is Synchronized With Source</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Is Synchronized With Source</em>' attribute is set.
	 * @see #unsetIsSynchronizedWithSource()
	 * @see #isIsSynchronizedWithSource()
	 * @see #setIsSynchronizedWithSource(boolean)
	 * @generated
	 */
	boolean isSetIsSynchronizedWithSource();

} // Descriptor
