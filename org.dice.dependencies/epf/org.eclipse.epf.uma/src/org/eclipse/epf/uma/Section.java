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

import java.util.List;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Section</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A Section is a special Method Element that represents structural subsections of a Content Description's sectionDescription attribute.  It is used for either large scale documentation of Content Elements organized into sections as well as to flexibly add new Sections to Content Elements using contribution variability added to the Section concept for Method Plug-ins.
 * Section in the package Method Plugin inherits from Variability Element and extends Section defined in Method Core :: Basic Elements with new capabilities for variability. 
 * For example, when a Task contributes to another Task its Presentation association is contributed including its Sections (i.e. its Steps), which are modeled as parts of the Content Description instance.  Sections can be nested and therefore Task Descriptions can be flexibly organized in Steps with sub-Steps.  Sections are Variability Elements themselves, so they can contribute to each other.  For example, one could model a Task step as a Section instance without relating it to a Task Description that directly contributes to (or replaces) another Section which is part of a Task Description.  This contribution (or replacement) would add new description text to the original step description (or replace the original step description).  Another example would be to contribute new Check List items organized as Sections to an existing Check List (defined as guidance).  
 * 
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.Section#getSectionName <em>Section Name</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.Section#getSectionDescription <em>Section Description</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.Section#getSubSections <em>Sub Sections</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.Section#getPredecessor <em>Predecessor</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.uma.UmaPackage#getSection()
 * @model
 * @generated
 */
public interface Section extends VariabilityElement {
	/**
	 * Returns the value of the '<em><b>Section Name</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Every Section has a name used for external presentation of the section, e.g. when published or when section heading are listed in a table of contents.  This attribute is similar to Presentation Name for Content Elements.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Section Name</em>' attribute.
	 * @see #setSectionName(String)
	 * @see org.eclipse.epf.uma.UmaPackage#getSection_SectionName()
	 * @model default="" dataType="org.eclipse.epf.uma.String" ordered="false"
	 * @generated
	 */
	String getSectionName();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.Section#getSectionName <em>Section Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Section Name</em>' attribute.
	 * @see #getSectionName()
	 * @generated
	 */
	void setSectionName(String value);

	/**
	 * Returns the value of the '<em><b>Section Description</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * This attributes store the description text for a Content Description's Section.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Section Description</em>' attribute.
	 * @see #setSectionDescription(String)
	 * @see org.eclipse.epf.uma.UmaPackage#getSection_SectionDescription()
	 * @model default="" dataType="org.eclipse.epf.uma.String" ordered="false"
	 * @generated
	 */
	String getSectionDescription();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.Section#getSectionDescription <em>Section Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Section Description</em>' attribute.
	 * @see #getSectionDescription()
	 * @generated
	 */
	void setSectionDescription(String value);

	/**
	 * Returns the value of the '<em><b>Sub Sections</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.Section}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub Sections</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sub Sections</em>' containment reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getSection_SubSections()
	 * @model containment="true" resolveProxies="true" ordered="false"
	 * @generated
	 */
	List<Section> getSubSections();

	/**
	 * Returns the value of the '<em><b>Predecessor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Predecessor</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Predecessor</em>' reference.
	 * @see #setPredecessor(Section)
	 * @see org.eclipse.epf.uma.UmaPackage#getSection_Predecessor()
	 * @model ordered="false"
	 * @generated
	 */
	Section getPredecessor();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.Section#getPredecessor <em>Predecessor</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Predecessor</em>' reference.
	 * @see #getPredecessor()
	 * @generated
	 */
	void setPredecessor(Section value);

} // Section
