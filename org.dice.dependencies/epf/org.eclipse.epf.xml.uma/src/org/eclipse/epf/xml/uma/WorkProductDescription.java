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
 * A representation of the model object '<em><b>Work Product Description</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A generalized Content Description that is used to store the textual description for a Work Product.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.xml.uma.WorkProductDescription#getImpactOfNotHaving <em>Impact Of Not Having</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.WorkProductDescription#getPurpose <em>Purpose</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.WorkProductDescription#getReasonsForNotNeeding <em>Reasons For Not Needing</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.xml.uma.UmaPackage#getWorkProductDescription()
 * @model extendedMetaData="name='WorkProductDescription' kind='elementOnly'"
 * @generated
 */
public interface WorkProductDescription extends ContentDescription {
	/**
	 * Returns the value of the '<em><b>Impact Of Not Having</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Describes the consequences of not producing the work product.  This is intended to aid in the tailoring the method/process to the needs of a specific project.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Impact Of Not Having</em>' attribute.
	 * @see #setImpactOfNotHaving(String)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getWorkProductDescription_ImpactOfNotHaving()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='ImpactOfNotHaving'"
	 * @generated
	 */
	String getImpactOfNotHaving();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.WorkProductDescription#getImpactOfNotHaving <em>Impact Of Not Having</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Impact Of Not Having</em>' attribute.
	 * @see #getImpactOfNotHaving()
	 * @generated
	 */
	void setImpactOfNotHaving(String value);

	/**
	 * Returns the value of the '<em><b>Purpose</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Describes why the work product is produced and to what use it will be put.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Purpose</em>' attribute.
	 * @see #setPurpose(String)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getWorkProductDescription_Purpose()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='Purpose'"
	 * @generated
	 */
	String getPurpose();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.WorkProductDescription#getPurpose <em>Purpose</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Purpose</em>' attribute.
	 * @see #getPurpose()
	 * @generated
	 */
	void setPurpose(String value);

	/**
	 * Returns the value of the '<em><b>Reasons For Not Needing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Describes the circumstances in which it is reasonable not to produce the work product.  This is intended to aid in the tailoring of the method/process to the needs of a specific project.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Reasons For Not Needing</em>' attribute.
	 * @see #setReasonsForNotNeeding(String)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getWorkProductDescription_ReasonsForNotNeeding()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='ReasonsForNotNeeding'"
	 * @generated
	 */
	String getReasonsForNotNeeding();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.WorkProductDescription#getReasonsForNotNeeding <em>Reasons For Not Needing</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reasons For Not Needing</em>' attribute.
	 * @see #getReasonsForNotNeeding()
	 * @generated
	 */
	void setReasonsForNotNeeding(String value);

} // WorkProductDescription
