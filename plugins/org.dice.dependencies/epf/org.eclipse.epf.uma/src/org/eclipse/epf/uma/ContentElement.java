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
 * A representation of the model object '<em><b>Content Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Content Element is a Describable Element that represents an abstract generalization for all elements that are considered to be and managed as Method Content.
 * Content Elements represents reusable Method Content that is supposed to be managed in Content Packages.  The separation of Content Element from Process Element allows to clearly distinguish between pure method content from content that is represented in processes.
 * 
 * This is the Guidance Types package's extension of Content Element (defined in Content Elements) providing additonal associations.
 * Content Element in the package Method Plugin inherits from Variability Element and not directly from Method Element anymore.  This is achieved using UML 2.0 package merge semantics.  Only if an adopter of this meta-model decides to realize Method Plugins, he would get the variability and extension capabilities for all Content Elements.
 * Content Element inherits the semantics of Variability Element.
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.ContentElement#getSupportingMaterials <em>Supporting Materials</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.ContentElement#getConceptsAndPapers <em>Concepts And Papers</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.ContentElement#getChecklists <em>Checklists</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.ContentElement#getGuidelines <em>Guidelines</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.ContentElement#getExamples <em>Examples</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.ContentElement#getAssets <em>Assets</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.ContentElement#getTermdefinition <em>Termdefinition</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.uma.UmaPackage#getContentElement()
 * @model abstract="true"
 * @generated
 */
public interface ContentElement extends DescribableElement, VariabilityElement {
	/**
	 * Returns the value of the '<em><b>Supporting Materials</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.SupportingMaterial}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Supporting Materials</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Supporting Materials</em>' reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getContentElement_SupportingMaterials()
	 * @model ordered="false"
	 * @generated
	 */
	List<SupportingMaterial> getSupportingMaterials();

	/**
	 * Returns the value of the '<em><b>Concepts And Papers</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.Concept}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Concepts And Papers</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Concepts And Papers</em>' reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getContentElement_ConceptsAndPapers()
	 * @model ordered="false"
	 * @generated
	 */
	List<Concept> getConceptsAndPapers();

	/**
	 * Returns the value of the '<em><b>Checklists</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.Checklist}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Checklists</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Checklists</em>' reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getContentElement_Checklists()
	 * @model ordered="false"
	 * @generated
	 */
	List<Checklist> getChecklists();

	/**
	 * Returns the value of the '<em><b>Guidelines</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.Guideline}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Guidelines</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Guidelines</em>' reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getContentElement_Guidelines()
	 * @model ordered="false"
	 * @generated
	 */
	List<Guideline> getGuidelines();

	/**
	 * Returns the value of the '<em><b>Examples</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.Example}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Examples</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Examples</em>' reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getContentElement_Examples()
	 * @model ordered="false"
	 * @generated
	 */
	List<Example> getExamples();

	/**
	 * Returns the value of the '<em><b>Assets</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.ReusableAsset}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Assets</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Assets</em>' reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getContentElement_Assets()
	 * @model ordered="false"
	 * @generated
	 */
	List<ReusableAsset> getAssets();

	/**
	 * Returns the value of the '<em><b>Termdefinition</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.TermDefinition}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Termdefinition</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Termdefinition</em>' reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getContentElement_Termdefinition()
	 * @model ordered="false"
	 * @generated
	 */
	List<TermDefinition> getTermdefinition();

} // ContentElement
