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
import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Method Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The root generalization for all UMA Method Elements.  Defines a common set of attributes inherited by all UMA Method Elements.  Method Element itself is derived from Packageable Element from the UML 2.0 Infrastructure.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.xml.uma.MethodElement#getGroup <em>Group</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.MethodElement#getOwnedRule <em>Owned Rule</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.MethodElement#getMethodElementProperty <em>Method Element Property</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.MethodElement#getBriefDescription <em>Brief Description</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.MethodElement#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.MethodElement#getOrderingGuide <em>Ordering Guide</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.MethodElement#getPresentationName <em>Presentation Name</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.MethodElement#isSuppressed <em>Suppressed</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.xml.uma.UmaPackage#getMethodElement()
 * @model extendedMetaData="name='MethodElement' kind='elementOnly'"
 * @generated
 */
public interface MethodElement extends PackageableElement {
	/**
	 * Returns the value of the '<em><b>Group</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Group</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Group</em>' attribute list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getMethodElement_Group()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='group:1'"
	 * @generated
	 */
	FeatureMap getGroup();

	/**
	 * Returns the value of the '<em><b>Owned Rule</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.epf.xml.uma.Constraint}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Defines the packaging rules for this Method Element.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Owned Rule</em>' containment reference list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getMethodElement_OwnedRule()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='OwnedRule' group='#group:1'"
	 * @generated
	 */
	EList<Constraint> getOwnedRule();

	/**
	 * Returns the value of the '<em><b>Method Element Property</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.epf.xml.uma.MethodElementProperty}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Method Element Property</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Method Element Property</em>' containment reference list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getMethodElement_MethodElementProperty()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='MethodElementProperty' group='#group:1'"
	 * @generated
	 */
	EList<MethodElementProperty> getMethodElementProperty();

	/**
	 * Returns the value of the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Every instance of Method Element shall be briefly described with one or two sentences summarizing the element.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Brief Description</em>' attribute.
	 * @see #setBriefDescription(String)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getMethodElement_BriefDescription()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='briefDescription'"
	 * @generated
	 */
	String getBriefDescription();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.MethodElement#getBriefDescription <em>Brief Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Brief Description</em>' attribute.
	 * @see #getBriefDescription()
	 * @generated
	 */
	void setBriefDescription(String value);

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Every instance of Method Element has a global unique id.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getMethodElement_Id()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='id'"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.MethodElement#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Used for CASE tool realizations of this model to contain information about layout and ordering of the method element and its parts.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Ordering Guide</em>' attribute.
	 * @see #setOrderingGuide(String)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getMethodElement_OrderingGuide()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='orderingGuide'"
	 * @generated
	 */
	String getOrderingGuide();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.MethodElement#getOrderingGuide <em>Ordering Guide</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ordering Guide</em>' attribute.
	 * @see #getOrderingGuide()
	 * @generated
	 */
	void setOrderingGuide(String value);

	/**
	 * Returns the value of the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Every Describable Element has a presentation name, which is used for external presentation of the element.  For example, name (the internal representation) might be set to "rup_architecture_document" to differentiate from a "j2ee_architcture_document" whereas the external presentation would always be "Architecture Document".
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Presentation Name</em>' attribute.
	 * @see #setPresentationName(String)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getMethodElement_PresentationName()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='presentationName'"
	 * @generated
	 */
	String getPresentationName();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.MethodElement#getPresentationName <em>Presentation Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Presentation Name</em>' attribute.
	 * @see #getPresentationName()
	 * @generated
	 */
	void setPresentationName(String value);

	/**
	 * Returns the value of the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If a Variability Element is derived from another Variability Element using the "extends" Variability Specialization, then this attribute can be used to suppress inherited Method Elements that were part of the based-on Variability Element, which can be any type of Method Element.  In other words, if this attribute is set to true on a Method Element that has the same name than an inherited method element then it will not be regarded as inherited at all.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Suppressed</em>' attribute.
	 * @see #isSetSuppressed()
	 * @see #unsetSuppressed()
	 * @see #setSuppressed(boolean)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getMethodElement_Suppressed()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='suppressed'"
	 * @generated
	 */
	boolean isSuppressed();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.MethodElement#isSuppressed <em>Suppressed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Suppressed</em>' attribute.
	 * @see #isSetSuppressed()
	 * @see #unsetSuppressed()
	 * @see #isSuppressed()
	 * @generated
	 */
	void setSuppressed(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.xml.uma.MethodElement#isSuppressed <em>Suppressed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetSuppressed()
	 * @see #isSuppressed()
	 * @see #setSuppressed(boolean)
	 * @generated
	 */
	void unsetSuppressed();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.xml.uma.MethodElement#isSuppressed <em>Suppressed</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Suppressed</em>' attribute is set.
	 * @see #unsetSuppressed()
	 * @see #isSuppressed()
	 * @see #setSuppressed(boolean)
	 * @generated
	 */
	boolean isSetSuppressed();

} // MethodElement
