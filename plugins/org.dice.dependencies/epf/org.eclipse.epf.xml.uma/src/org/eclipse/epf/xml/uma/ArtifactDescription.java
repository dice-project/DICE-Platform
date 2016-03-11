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
 * A representation of the model object '<em><b>Artifact Description</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A generalized Work Product Description that is used to store the textual description for an Artifact.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.xml.uma.ArtifactDescription#getBriefOutline <em>Brief Outline</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.ArtifactDescription#getRepresentationOptions <em>Representation Options</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.ArtifactDescription#getRepresentation <em>Representation</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.ArtifactDescription#getNotation <em>Notation</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.xml.uma.UmaPackage#getArtifactDescription()
 * @model extendedMetaData="name='ArtifactDescription' kind='elementOnly'"
 * @generated
 */
public interface ArtifactDescription extends WorkProductDescription {
	/**
	 * Returns the value of the '<em><b>Brief Outline</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Provides a brief description of the information that can be found in this artifact. For example, discusses the contents for key chapters of a document artifact or the key packages and modules of a model artifact.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Brief Outline</em>' attribute.
	 * @see #setBriefOutline(String)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getArtifactDescription_BriefOutline()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='BriefOutline'"
	 * @generated
	 */
	String getBriefOutline();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.ArtifactDescription#getBriefOutline <em>Brief Outline</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Brief Outline</em>' attribute.
	 * @see #getBriefOutline()
	 * @generated
	 */
	void setBriefOutline(String value);

	/**
	 * Returns the value of the '<em><b>Representation Options</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Discusses different possible alternative representations for the artifact. For example a design model can be represented as a UML model or an informal block diagram or by textual description only.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Representation Options</em>' attribute.
	 * @see #setRepresentationOptions(String)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getArtifactDescription_RepresentationOptions()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='RepresentationOptions'"
	 * @generated
	 */
	String getRepresentationOptions();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.ArtifactDescription#getRepresentationOptions <em>Representation Options</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Representation Options</em>' attribute.
	 * @see #getRepresentationOptions()
	 * @generated
	 */
	void setRepresentationOptions(String value);

	/**
	 * Returns the value of the '<em><b>Representation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Representation</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Representation</em>' attribute.
	 * @see #setRepresentation(String)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getArtifactDescription_Representation()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='Representation'"
	 * @generated
	 */
	String getRepresentation();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.ArtifactDescription#getRepresentation <em>Representation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Representation</em>' attribute.
	 * @see #getRepresentation()
	 * @generated
	 */
	void setRepresentation(String value);

	/**
	 * Returns the value of the '<em><b>Notation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Notation</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Notation</em>' attribute.
	 * @see #setNotation(String)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getArtifactDescription_Notation()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='Notation'"
	 * @generated
	 */
	String getNotation();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.ArtifactDescription#getNotation <em>Notation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Notation</em>' attribute.
	 * @see #getNotation()
	 * @generated
	 */
	void setNotation(String value);

} // ArtifactDescription
