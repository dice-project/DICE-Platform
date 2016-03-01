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
 * A representation of the model object '<em><b>Delivery Process Description</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A generalized Process Description that is used to store the textual description for a Delivery Process.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.xml.uma.DeliveryProcessDescription#getScale <em>Scale</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.DeliveryProcessDescription#getProjectCharacteristics <em>Project Characteristics</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.DeliveryProcessDescription#getRiskLevel <em>Risk Level</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.DeliveryProcessDescription#getEstimatingTechnique <em>Estimating Technique</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.DeliveryProcessDescription#getProjectMemberExpertise <em>Project Member Expertise</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.DeliveryProcessDescription#getTypeOfContract <em>Type Of Contract</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.xml.uma.UmaPackage#getDeliveryProcessDescription()
 * @model extendedMetaData="name='DeliveryProcessDescription' kind='elementOnly'"
 * @generated
 */
public interface DeliveryProcessDescription extends ProcessDescription {
	/**
	 * Returns the value of the '<em><b>Scale</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Outlines characteristics about the size of a typical project that performs this project expressed in team size, man years, etc.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Scale</em>' attribute.
	 * @see #setScale(String)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getDeliveryProcessDescription_Scale()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='Scale'"
	 * @generated
	 */
	String getScale();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.DeliveryProcessDescription#getScale <em>Scale</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Scale</em>' attribute.
	 * @see #getScale()
	 * @generated
	 */
	void setScale(String value);

	/**
	 * Returns the value of the '<em><b>Project Characteristics</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Characterizes the project that would typically perform this Process
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Project Characteristics</em>' attribute.
	 * @see #setProjectCharacteristics(String)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getDeliveryProcessDescription_ProjectCharacteristics()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='ProjectCharacteristics'"
	 * @generated
	 */
	String getProjectCharacteristics();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.DeliveryProcessDescription#getProjectCharacteristics <em>Project Characteristics</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Project Characteristics</em>' attribute.
	 * @see #getProjectCharacteristics()
	 * @generated
	 */
	void setProjectCharacteristics(String value);

	/**
	 * Returns the value of the '<em><b>Risk Level</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Outlines typical project risks that are addressed with this process.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Risk Level</em>' attribute.
	 * @see #setRiskLevel(String)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getDeliveryProcessDescription_RiskLevel()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='RiskLevel'"
	 * @generated
	 */
	String getRiskLevel();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.DeliveryProcessDescription#getRiskLevel <em>Risk Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Risk Level</em>' attribute.
	 * @see #getRiskLevel()
	 * @generated
	 */
	void setRiskLevel(String value);

	/**
	 * Returns the value of the '<em><b>Estimating Technique</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Describes the Estimation Techniques provided for this Process.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Estimating Technique</em>' attribute.
	 * @see #setEstimatingTechnique(String)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getDeliveryProcessDescription_EstimatingTechnique()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='EstimatingTechnique'"
	 * @generated
	 */
	String getEstimatingTechnique();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.DeliveryProcessDescription#getEstimatingTechnique <em>Estimating Technique</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Estimating Technique</em>' attribute.
	 * @see #getEstimatingTechnique()
	 * @generated
	 */
	void setEstimatingTechnique(String value);

	/**
	 * Returns the value of the '<em><b>Project Member Expertise</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Provides a profile of a typical project team, the distribution of roles, skills required for a team performs a project based on this process.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Project Member Expertise</em>' attribute.
	 * @see #setProjectMemberExpertise(String)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getDeliveryProcessDescription_ProjectMemberExpertise()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='ProjectMemberExpertise'"
	 * @generated
	 */
	String getProjectMemberExpertise();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.DeliveryProcessDescription#getProjectMemberExpertise <em>Project Member Expertise</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Project Member Expertise</em>' attribute.
	 * @see #getProjectMemberExpertise()
	 * @generated
	 */
	void setProjectMemberExpertise(String value);

	/**
	 * Returns the value of the '<em><b>Type Of Contract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Provides background information about the coI'm chaI'm ntracts that need to be established between a project team that performs this process and a client (e.g. for an IGS engagement).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Type Of Contract</em>' attribute.
	 * @see #setTypeOfContract(String)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getDeliveryProcessDescription_TypeOfContract()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='TypeOfContract'"
	 * @generated
	 */
	String getTypeOfContract();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.DeliveryProcessDescription#getTypeOfContract <em>Type Of Contract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type Of Contract</em>' attribute.
	 * @see #getTypeOfContract()
	 * @generated
	 */
	void setTypeOfContract(String value);

} // DeliveryProcessDescription
