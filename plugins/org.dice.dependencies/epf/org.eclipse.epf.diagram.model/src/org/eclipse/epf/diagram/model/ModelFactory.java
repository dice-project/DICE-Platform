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
package org.eclipse.epf.diagram.model;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.epf.diagram.model.ModelPackage
 * @generated
 */
public interface ModelFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    ModelFactory eINSTANCE = org.eclipse.epf.diagram.model.impl.ModelFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Diagram</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Diagram</em>'.
	 * @generated
	 */
    Diagram createDiagram();

	/**
	 * Returns a new object of class '<em>Link</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Link</em>'.
	 * @generated
	 */
    Link createLink();

	/**
	 * Returns a new object of class '<em>Named Node</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Named Node</em>'.
	 * @generated
	 */
    NamedNode createNamedNode();

	/**
	 * Returns a new object of class '<em>Activity Diagram</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Activity Diagram</em>'.
	 * @generated
	 */
    ActivityDiagram createActivityDiagram();

	/**
	 * Returns a new object of class '<em>Typed Node</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Typed Node</em>'.
	 * @generated
	 */
    TypedNode createTypedNode();

	/**
	 * Returns a new object of class '<em>Work Product Dependency Diagram</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Work Product Dependency Diagram</em>'.
	 * @generated
	 */
	WorkProductDependencyDiagram createWorkProductDependencyDiagram();

	/**
	 * Returns a new object of class '<em>Work Product Node</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Work Product Node</em>'.
	 * @generated
	 */
	WorkProductNode createWorkProductNode();

	/**
	 * Returns a new object of class '<em>Activity Detail Diagram</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Activity Detail Diagram</em>'.
	 * @generated
	 */
	ActivityDetailDiagram createActivityDetailDiagram();

	/**
	 * Returns a new object of class '<em>Role Node</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Role Node</em>'.
	 * @generated
	 */
	RoleNode createRoleNode();

	/**
	 * Returns a new object of class '<em>Role Task Composite</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Role Task Composite</em>'.
	 * @generated
	 */
	RoleTaskComposite createRoleTaskComposite();

	/**
	 * Returns a new object of class '<em>Task Node</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Task Node</em>'.
	 * @generated
	 */
	TaskNode createTaskNode();

	/**
	 * Returns a new object of class '<em>Work Product Descriptor Node</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Work Product Descriptor Node</em>'.
	 * @generated
	 */
	WorkProductDescriptorNode createWorkProductDescriptorNode();

	/**
	 * Returns a new object of class '<em>Work Breakdown Element Node</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Work Breakdown Element Node</em>'.
	 * @generated
	 */
	WorkBreakdownElementNode createWorkBreakdownElementNode();

	/**
	 * Returns a new object of class '<em>Work Product Composite</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Work Product Composite</em>'.
	 * @generated
	 */
	WorkProductComposite createWorkProductComposite();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
    ModelPackage getModelPackage();

} //ModelFactory
