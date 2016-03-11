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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Describable Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * An abstract generalization of Method Elements for which external presentation names as well as content descriptions have been defined, such as Roles or Work Products.  Presentation Name and Content Descriptions are typically localized using a resource allocation mechanism for its String type attributes.
 * This abstraction represents all elements in the Method Content as well as Process space for which concrete textual descriptions are defined in the form of documenting attributes grouped in a matching Content Description instance.  Describable Elements are intended to be published in method or process publications (similar to the IBM Rational Unified Process web).  Describable Element defines that the element it represents will have content 'attached' to it.  Content Description is the abstraction for the actual places in which the content is being represented.  This separation allows a distinction between core method model elements describing the structure of the model from the actual description container providing, for example, the documentation of the content element in different alternatives languages, audiences, licensing levels, etc.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.xml.uma.DescribableElement#getPresentation <em>Presentation</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.DescribableElement#getFulfill <em>Fulfill</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.DescribableElement#isIsAbstract <em>Is Abstract</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.DescribableElement#getNodeicon <em>Nodeicon</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.DescribableElement#getShapeicon <em>Shapeicon</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.xml.uma.UmaPackage#getDescribableElement()
 * @model extendedMetaData="name='DescribableElement' kind='elementOnly'"
 * @generated
 */
public interface DescribableElement extends MethodElement {
	/**
	 * Returns the value of the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Presentation</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Presentation</em>' containment reference.
	 * @see #setPresentation(ContentDescription)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getDescribableElement_Presentation()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='Presentation'"
	 * @generated
	 */
	ContentDescription getPresentation();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.DescribableElement#getPresentation <em>Presentation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Presentation</em>' containment reference.
	 * @see #getPresentation()
	 * @generated
	 */
	void setPresentation(ContentDescription value);

	/**
	 * Returns the value of the '<em><b>Fulfill</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fulfill</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fulfill</em>' attribute list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getDescribableElement_Fulfill()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='Fulfill'"
	 * @generated
	 */
	EList<String> getFulfill();

	/**
	 * Returns the value of the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Abstract</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Abstract</em>' attribute.
	 * @see #isSetIsAbstract()
	 * @see #unsetIsAbstract()
	 * @see #setIsAbstract(boolean)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getDescribableElement_IsAbstract()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='isAbstract'"
	 * @generated
	 */
	boolean isIsAbstract();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.DescribableElement#isIsAbstract <em>Is Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Abstract</em>' attribute.
	 * @see #isSetIsAbstract()
	 * @see #unsetIsAbstract()
	 * @see #isIsAbstract()
	 * @generated
	 */
	void setIsAbstract(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.xml.uma.DescribableElement#isIsAbstract <em>Is Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetIsAbstract()
	 * @see #isIsAbstract()
	 * @see #setIsAbstract(boolean)
	 * @generated
	 */
	void unsetIsAbstract();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.xml.uma.DescribableElement#isIsAbstract <em>Is Abstract</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Is Abstract</em>' attribute is set.
	 * @see #unsetIsAbstract()
	 * @see #isIsAbstract()
	 * @see #setIsAbstract(boolean)
	 * @generated
	 */
	boolean isSetIsAbstract();

	/**
	 * Returns the value of the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A reference to an icon that can be used in tree browser presentations and breakdown structures.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Nodeicon</em>' attribute.
	 * @see #setNodeicon(String)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getDescribableElement_Nodeicon()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='nodeicon'"
	 * @generated
	 */
	String getNodeicon();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.DescribableElement#getNodeicon <em>Nodeicon</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Nodeicon</em>' attribute.
	 * @see #getNodeicon()
	 * @generated
	 */
	void setNodeicon(String value);

	/**
	 * Returns the value of the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A reference to an icon that can be used for modeling with specific Content Element instances (as graphical stereotypes, e.g. a use case symbol for a use case artifact) as well as publication of content.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Shapeicon</em>' attribute.
	 * @see #setShapeicon(String)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getDescribableElement_Shapeicon()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='shapeicon'"
	 * @generated
	 */
	String getShapeicon();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.DescribableElement#getShapeicon <em>Shapeicon</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Shapeicon</em>' attribute.
	 * @see #getShapeicon()
	 * @generated
	 */
	void setShapeicon(String value);

} // DescribableElement
