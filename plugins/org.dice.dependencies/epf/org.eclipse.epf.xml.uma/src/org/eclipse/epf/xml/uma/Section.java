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
 * A representation of the model object '<em><b>Section</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A special Method Element that represents structural subsections of a Content Description's sectionDescription attribute.  It is used for either large scale documentation of Content Elements organized into sections as well as to flexibly add new Sections to Content Elements using contribution variability added to the Section concept for Method Plug-ins.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.xml.uma.Section#getSubSection <em>Sub Section</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.Section#getPredecessor <em>Predecessor</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.Section#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.Section#getSectionName <em>Section Name</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.Section#getVariabilityBasedOnElement <em>Variability Based On Element</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.Section#getVariabilityType <em>Variability Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.xml.uma.UmaPackage#getSection()
 * @model extendedMetaData="name='Section' kind='elementOnly'"
 * @generated
 */
public interface Section extends MethodElement {
	/**
	 * Returns the value of the '<em><b>Sub Section</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub Section</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sub Section</em>' containment reference.
	 * @see #setSubSection(Section)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getSection_SubSection()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='SubSection'"
	 * @generated
	 */
	Section getSubSection();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.Section#getSubSection <em>Sub Section</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sub Section</em>' containment reference.
	 * @see #getSubSection()
	 * @generated
	 */
	void setSubSection(Section value);

	/**
	 * Returns the value of the '<em><b>Predecessor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Predecessor</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Predecessor</em>' attribute.
	 * @see #setPredecessor(String)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getSection_Predecessor()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='Predecessor'"
	 * @generated
	 */
	String getPredecessor();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.Section#getPredecessor <em>Predecessor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Predecessor</em>' attribute.
	 * @see #getPredecessor()
	 * @generated
	 */
	void setPredecessor(String value);

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * This attributes store the description text for a Content Description's Section.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getSection_Description()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='Description'"
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.Section#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Section Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Every Section has a name used for external presentation of the section, e.g. when published or when section heading are listed in a table of contents.  This attribute is similar to Presentation Name for Content Elements.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Section Name</em>' attribute.
	 * @see #setSectionName(String)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getSection_SectionName()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='sectionName'"
	 * @generated
	 */
	String getSectionName();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.Section#getSectionName <em>Section Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Section Name</em>' attribute.
	 * @see #getSectionName()
	 * @generated
	 */
	void setSectionName(String value);

	/**
	 * Returns the value of the '<em><b>Variability Based On Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Variability Based On Element</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variability Based On Element</em>' attribute.
	 * @see #setVariabilityBasedOnElement(String)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getSection_VariabilityBasedOnElement()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='variabilityBasedOnElement'"
	 * @generated
	 */
	String getVariabilityBasedOnElement();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.Section#getVariabilityBasedOnElement <em>Variability Based On Element</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Variability Based On Element</em>' attribute.
	 * @see #getVariabilityBasedOnElement()
	 * @generated
	 */
	void setVariabilityBasedOnElement(String value);

	/**
	 * Returns the value of the '<em><b>Variability Type</b></em>' attribute.
	 * The default value is <code>"na"</code>.
	 * The literals are from the enumeration {@link org.eclipse.epf.xml.uma.VariabilityType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Variability Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variability Type</em>' attribute.
	 * @see org.eclipse.epf.xml.uma.VariabilityType
	 * @see #isSetVariabilityType()
	 * @see #unsetVariabilityType()
	 * @see #setVariabilityType(VariabilityType)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getSection_VariabilityType()
	 * @model default="na" unsettable="true"
	 *        extendedMetaData="kind='attribute' name='variabilityType'"
	 * @generated
	 */
	VariabilityType getVariabilityType();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.Section#getVariabilityType <em>Variability Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Variability Type</em>' attribute.
	 * @see org.eclipse.epf.xml.uma.VariabilityType
	 * @see #isSetVariabilityType()
	 * @see #unsetVariabilityType()
	 * @see #getVariabilityType()
	 * @generated
	 */
	void setVariabilityType(VariabilityType value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.xml.uma.Section#getVariabilityType <em>Variability Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetVariabilityType()
	 * @see #getVariabilityType()
	 * @see #setVariabilityType(VariabilityType)
	 * @generated
	 */
	void unsetVariabilityType();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.xml.uma.Section#getVariabilityType <em>Variability Type</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Variability Type</em>' attribute is set.
	 * @see #unsetVariabilityType()
	 * @see #getVariabilityType()
	 * @see #setVariabilityType(VariabilityType)
	 * @generated
	 */
	boolean isSetVariabilityType();

} // Section
