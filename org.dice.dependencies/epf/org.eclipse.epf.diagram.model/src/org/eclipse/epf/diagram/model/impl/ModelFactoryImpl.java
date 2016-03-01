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
package org.eclipse.epf.diagram.model.impl;

import org.eclipse.draw2d.AbsoluteBendpoint;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.epf.diagram.model.*;

import org.eclipse.epf.diagram.model.ActivityDetailDiagram;
import org.eclipse.epf.diagram.model.ActivityDiagram;
import org.eclipse.epf.diagram.model.Diagram;
import org.eclipse.epf.diagram.model.Link;
import org.eclipse.epf.diagram.model.ModelFactory;
import org.eclipse.epf.diagram.model.ModelPackage;
import org.eclipse.epf.diagram.model.NamedNode;
import org.eclipse.epf.diagram.model.RoleNode;
import org.eclipse.epf.diagram.model.RoleTaskComposite;
import org.eclipse.epf.diagram.model.TaskNode;
import org.eclipse.epf.diagram.model.TypedNode;
import org.eclipse.epf.diagram.model.WorkBreakdownElementNode;
import org.eclipse.epf.diagram.model.WorkProductComposite;
import org.eclipse.epf.diagram.model.WorkProductDependencyDiagram;
import org.eclipse.epf.diagram.model.WorkProductDescriptorNode;
import org.eclipse.epf.diagram.model.WorkProductNode;
import org.eclipse.epf.uma.RoleDescriptor;


/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!--
 * end-user-doc -->
 * @generated
 */
public class ModelFactoryImpl extends EFactoryImpl implements ModelFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ModelFactory init() {
		try {
			ModelFactory theModelFactory = (ModelFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.eclipse.org/epf/diagram/1.0.0/diagram.ecore"); //$NON-NLS-1$ 
			if (theModelFactory != null) {
				return theModelFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ModelFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	public ModelFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case ModelPackage.DIAGRAM: return createDiagram();
			case ModelPackage.LINK: return createLink();
			case ModelPackage.NAMED_NODE: return createNamedNode();
			case ModelPackage.ACTIVITY_DIAGRAM: return createActivityDiagram();
			case ModelPackage.TYPED_NODE: return createTypedNode();
			case ModelPackage.WORK_PRODUCT_DEPENDENCY_DIAGRAM: return createWorkProductDependencyDiagram();
			case ModelPackage.WORK_PRODUCT_NODE: return createWorkProductNode();
			case ModelPackage.ACTIVITY_DETAIL_DIAGRAM: return createActivityDetailDiagram();
			case ModelPackage.ROLE_NODE: return createRoleNode();
			case ModelPackage.ROLE_TASK_COMPOSITE: return createRoleTaskComposite();
			case ModelPackage.TASK_NODE: return createTaskNode();
			case ModelPackage.WORK_PRODUCT_DESCRIPTOR_NODE: return createWorkProductDescriptorNode();
			case ModelPackage.WORK_BREAKDOWN_ELEMENT_NODE: return createWorkBreakdownElementNode();
			case ModelPackage.WORK_PRODUCT_COMPOSITE: return createWorkProductComposite();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case ModelPackage.ABSOLUTE_BENDPOINT:
				return createAbsoluteBendpointFromString(eDataType, initialValue);
			case ModelPackage.POINT:
				return createPointFromString(eDataType, initialValue);
			case ModelPackage.ROLE_DESCRIPTOR:
				return createRoleDescriptorFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case ModelPackage.ABSOLUTE_BENDPOINT:
				return convertAbsoluteBendpointToString(eDataType, instanceValue);
			case ModelPackage.POINT:
				return convertPointToString(eDataType, instanceValue);
			case ModelPackage.ROLE_DESCRIPTOR:
				return convertRoleDescriptorToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Diagram createDiagram() {
		DiagramImpl diagram = new DiagramImpl();
		return diagram;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Link createLink() {
		LinkImpl link = new LinkImpl();
		return link;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NamedNode createNamedNode() {
		NamedNodeImpl namedNode = new NamedNodeImpl();
		return namedNode;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public ActivityDiagram createActivityDiagram() {
		ActivityDiagramImpl activityDiagram = new ActivityDiagramImpl();
		return activityDiagram;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public TypedNode createTypedNode() {
		TypedNodeImpl typedNode = new TypedNodeImpl();
		return typedNode;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public WorkProductDependencyDiagram createWorkProductDependencyDiagram() {
		WorkProductDependencyDiagramImpl workProductDependencyDiagram = new WorkProductDependencyDiagramImpl();
		return workProductDependencyDiagram;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public WorkProductNode createWorkProductNode() {
		WorkProductNodeImpl workProductNode = new WorkProductNodeImpl();
		return workProductNode;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public ActivityDetailDiagram createActivityDetailDiagram() {
		ActivityDetailDiagramImpl activityDetailDiagram = new ActivityDetailDiagramImpl();
		return activityDetailDiagram;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public RoleNode createRoleNode() {
		RoleNodeImpl roleNode = new RoleNodeImpl();
		return roleNode;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public RoleTaskComposite createRoleTaskComposite() {
		RoleTaskCompositeImpl roleTaskComposite = new RoleTaskCompositeImpl();
		return roleTaskComposite;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public TaskNode createTaskNode() {
		TaskNodeImpl taskNode = new TaskNodeImpl();
		return taskNode;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public WorkProductDescriptorNode createWorkProductDescriptorNode() {
		WorkProductDescriptorNodeImpl workProductDescriptorNode = new WorkProductDescriptorNodeImpl();
		return workProductDescriptorNode;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public WorkBreakdownElementNode createWorkBreakdownElementNode() {
		WorkBreakdownElementNodeImpl workBreakdownElementNode = new WorkBreakdownElementNodeImpl();
		return workBreakdownElementNode;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public WorkProductComposite createWorkProductComposite() {
		WorkProductCompositeImpl workProductComposite = new WorkProductCompositeImpl();
		return workProductComposite;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public AbsoluteBendpoint createAbsoluteBendpointFromString(EDataType eDataType, String initialValue) {
		return (AbsoluteBendpoint)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String convertAbsoluteBendpointToString(EDataType eDataType,
			Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Point createPointFromString(EDataType eDataType, String initialValue) {
		return (Point)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPointToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public RoleDescriptor createRoleDescriptorFromString(EDataType eDataType, String initialValue) {
		return (RoleDescriptor)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String convertRoleDescriptorToString(EDataType eDataType,
			Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public ModelPackage getModelPackage() {
		return (ModelPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ModelPackage getPackage() {
		return ModelPackage.eINSTANCE;
	}

} // ModelFactoryImpl
