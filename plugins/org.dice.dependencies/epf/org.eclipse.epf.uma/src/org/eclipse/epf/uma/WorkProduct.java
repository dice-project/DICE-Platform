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
 * A representation of the model object '<em><b>Work Product</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Work Product is an abstract class which provides a generalization for the content element types Artifact, Outcome, and Deliverable.  The meta-model class Work Product actually represents work product types, i.e. an instance of Work Product is a description of a specific type of work product and not an individual work product instance.  However, for simplicity reasons and because of low risk of misinterpretation we did not append the word 'type' to every meta-class.
 * A work product is an abstraction for descriptions of content elements that are used to define anything used, produced, or modified by a task.
 * 
 * This is the Guidance Types package's extension of Work Product (defined in Content Elements) providing additonal associations.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.WorkProduct#getReports <em>Reports</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.WorkProduct#getTemplates <em>Templates</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.WorkProduct#getToolMentors <em>Tool Mentors</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.WorkProduct#getEstimationConsiderations <em>Estimation Considerations</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.uma.UmaPackage#getWorkProduct()
 * @model
 * @generated
 */
public interface WorkProduct extends ContentElement, FulfillableElement {
	/**
	 * Returns the value of the '<em><b>Reports</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.Report}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reports</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reports</em>' reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getWorkProduct_Reports()
	 * @model ordered="false"
	 * @generated
	 */
	List<Report> getReports();

	/**
	 * Returns the value of the '<em><b>Templates</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.Template}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Templates</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Templates</em>' reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getWorkProduct_Templates()
	 * @model ordered="false"
	 * @generated
	 */
	List<Template> getTemplates();

	/**
	 * Returns the value of the '<em><b>Tool Mentors</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.ToolMentor}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tool Mentors</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tool Mentors</em>' reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getWorkProduct_ToolMentors()
	 * @model ordered="false"
	 * @generated
	 */
	List<ToolMentor> getToolMentors();

	/**
	 * Returns the value of the '<em><b>Estimation Considerations</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.EstimationConsiderations}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Estimation Considerations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Estimation Considerations</em>' reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getWorkProduct_EstimationConsiderations()
	 * @model ordered="false"
	 * @generated
	 */
	List<EstimationConsiderations> getEstimationConsiderations();

} // WorkProduct
