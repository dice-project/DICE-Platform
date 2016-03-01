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
 * A representation of the model object '<em><b>Variability Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Variability Element is an abstract class derived from Method Element that provides new capabilities for content variation and extension to Content Elements or any other Method Element that derives from it.  It has been placed in-between the classes Method Element and Content Element in the overall UMA taxonomy of classes using the UML 2.0 package merge mechanism.  The association Variability Specialization shall only be instantiated between two subclasses of Variability Element of the same type.  The element on varaibilitySpecialElement side of the relationship defines a value for the attribute variabilityType defining the nature of the relationship using a literal from the enumeration Variability Type.
 * Variability Element of the meta-model package Method Plugins adds the capabilities of variation and extension to Method Elements that derive from it.  By default all Content Elements such as Role, Task, Guidance Types, or Activities are defined to be Variability Elements.
 * Variability and extension provides unique mechanisms for customizing method content without actually directly modifying the original content, but by just be able to describe with separate objects the differences (additions, changes, omissions) relative to the original.  This plug-in concept allows users to factor their method content and processes in interrelated units and even to architect method content and processes in layers that extend each other with new capabilities.  The resulting method and process design can be dynamically combined and applied on demand using the interpretation rules defined for Variability Element Specializations assembling to process practitioners the most accurate method and process descriptions possible.  It also allows process practitioners to extends and tailor method content and processes they do not own and to easily upgrade to newer versions by simply reapply their personal changes to these upgrades.
 * Variability Element defines two types of variability and one type of extension which are formally defined for the enumeration Variability Type.
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.VariabilityElement#getVariabilityType <em>Variability Type</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.VariabilityElement#getVariabilityBasedOnElement <em>Variability Based On Element</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.uma.UmaPackage#getVariabilityElement()
 * @model abstract="true"
 * @generated
 */
public interface VariabilityElement extends MethodElement {
	/**
	 * Returns the value of the '<em><b>Variability Type</b></em>' attribute.
	 * The default value is <code>"na"</code>.
	 * The literals are from the enumeration {@link org.eclipse.epf.uma.VariabilityType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If in instance of the variabilitySpecialization association between two Variability Elements of the same type exists, then the variabilityType attribute specifies how the element at the variabilitySpecialElement end of the association changes the Content Element at the variabilityBasedOnElement end. See the Variability Type enumeration class for definitions for the different types of variability.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Variability Type</em>' attribute.
	 * @see org.eclipse.epf.uma.VariabilityType
	 * @see #setVariabilityType(VariabilityType)
	 * @see org.eclipse.epf.uma.UmaPackage#getVariabilityElement_VariabilityType()
	 * @model default="na" required="true" ordered="false"
	 * @generated
	 */
	VariabilityType getVariabilityType();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.VariabilityElement#getVariabilityType <em>Variability Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Variability Type</em>' attribute.
	 * @see org.eclipse.epf.uma.VariabilityType
	 * @see #getVariabilityType()
	 * @generated
	 */
	void setVariabilityType(VariabilityType value);

	/**
	 * Returns the value of the '<em><b>Variability Based On Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Variability Based On Element</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variability Based On Element</em>' reference.
	 * @see #setVariabilityBasedOnElement(VariabilityElement)
	 * @see org.eclipse.epf.uma.UmaPackage#getVariabilityElement_VariabilityBasedOnElement()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	VariabilityElement getVariabilityBasedOnElement();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.VariabilityElement#getVariabilityBasedOnElement <em>Variability Based On Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Variability Based On Element</em>' reference.
	 * @see #getVariabilityBasedOnElement()
	 * @generated
	 */
	void setVariabilityBasedOnElement(VariabilityElement value);

} // VariabilityElement
