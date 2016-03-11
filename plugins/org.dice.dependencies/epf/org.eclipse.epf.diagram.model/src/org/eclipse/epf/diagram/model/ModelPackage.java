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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.epf.diagram.model.ModelFactory
 * @model kind="package"
 * @generated
 */
public interface ModelPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    String eNAME = "model"; //$NON-NLS-1$

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    String eNS_URI = "http://www.eclipse.org/epf/diagram/1.0.0/diagram.ecore"; //$NON-NLS-1$

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    String eNS_PREFIX = "org.eclipse.epf.diagram.model"; //$NON-NLS-1$

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    ModelPackage eINSTANCE = org.eclipse.epf.diagram.model.impl.ModelPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.epf.diagram.model.impl.NodeContainerImpl <em>Node Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.diagram.model.impl.NodeContainerImpl
	 * @see org.eclipse.epf.diagram.model.impl.ModelPackageImpl#getNodeContainer()
	 * @generated
	 */
	int NODE_CONTAINER = 10;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.diagram.model.impl.LinkedObjectImpl <em>Linked Object</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.epf.diagram.model.impl.LinkedObjectImpl
	 * @see org.eclipse.epf.diagram.model.impl.ModelPackageImpl#getLinkedObject()
	 * @generated
	 */
    int LINKED_OBJECT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.diagram.model.impl.DiagramImpl <em>Diagram</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.epf.diagram.model.impl.DiagramImpl
	 * @see org.eclipse.epf.diagram.model.impl.ModelPackageImpl#getDiagram()
	 * @generated
	 */
    int DIAGRAM = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.diagram.model.impl.LinkImpl <em>Link</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.epf.diagram.model.impl.LinkImpl
	 * @see org.eclipse.epf.diagram.model.impl.ModelPackageImpl#getLink()
	 * @generated
	 */
    int LINK = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.diagram.model.impl.NodeImpl <em>Node</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.epf.diagram.model.impl.NodeImpl
	 * @see org.eclipse.epf.diagram.model.impl.ModelPackageImpl#getNode()
	 * @generated
	 */
    int NODE = 4;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.diagram.model.impl.NamedNodeImpl <em>Named Node</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.epf.diagram.model.impl.NamedNodeImpl
	 * @see org.eclipse.epf.diagram.model.impl.ModelPackageImpl#getNamedNode()
	 * @generated
	 */
    int NAMED_NODE = 3;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.diagram.model.impl.ActivityDiagramImpl <em>Activity Diagram</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.epf.diagram.model.impl.ActivityDiagramImpl
	 * @see org.eclipse.epf.diagram.model.impl.ModelPackageImpl#getActivityDiagram()
	 * @generated
	 */
    int ACTIVITY_DIAGRAM = 5;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.diagram.model.impl.TypedNodeImpl <em>Typed Node</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.epf.diagram.model.impl.TypedNodeImpl
	 * @see org.eclipse.epf.diagram.model.impl.ModelPackageImpl#getTypedNode()
	 * @generated
	 */
    int TYPED_NODE = 6;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.diagram.model.impl.WorkProductDependencyDiagramImpl <em>Work Product Dependency Diagram</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.diagram.model.impl.WorkProductDependencyDiagramImpl
	 * @see org.eclipse.epf.diagram.model.impl.ModelPackageImpl#getWorkProductDependencyDiagram()
	 * @generated
	 */
	int WORK_PRODUCT_DEPENDENCY_DIAGRAM = 7;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.diagram.model.impl.WorkProductNodeImpl <em>Work Product Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.diagram.model.impl.WorkProductNodeImpl
	 * @see org.eclipse.epf.diagram.model.impl.ModelPackageImpl#getWorkProductNode()
	 * @generated
	 */
	int WORK_PRODUCT_NODE = 8;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.diagram.model.impl.ActivityDetailDiagramImpl <em>Activity Detail Diagram</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.diagram.model.impl.ActivityDetailDiagramImpl
	 * @see org.eclipse.epf.diagram.model.impl.ModelPackageImpl#getActivityDetailDiagram()
	 * @generated
	 */
	int ACTIVITY_DETAIL_DIAGRAM = 9;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.diagram.model.impl.RoleNodeImpl <em>Role Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.diagram.model.impl.RoleNodeImpl
	 * @see org.eclipse.epf.diagram.model.impl.ModelPackageImpl#getRoleNode()
	 * @generated
	 */
	int ROLE_NODE = 11;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.diagram.model.impl.RoleTaskCompositeImpl <em>Role Task Composite</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.diagram.model.impl.RoleTaskCompositeImpl
	 * @see org.eclipse.epf.diagram.model.impl.ModelPackageImpl#getRoleTaskComposite()
	 * @generated
	 */
	int ROLE_TASK_COMPOSITE = 12;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.diagram.model.impl.TaskNodeImpl <em>Task Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.diagram.model.impl.TaskNodeImpl
	 * @see org.eclipse.epf.diagram.model.impl.ModelPackageImpl#getTaskNode()
	 * @generated
	 */
	int TASK_NODE = 13;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.diagram.model.impl.WorkProductDescriptorNodeImpl <em>Work Product Descriptor Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.diagram.model.impl.WorkProductDescriptorNodeImpl
	 * @see org.eclipse.epf.diagram.model.impl.ModelPackageImpl#getWorkProductDescriptorNode()
	 * @generated
	 */
	int WORK_PRODUCT_DESCRIPTOR_NODE = 14;

	/**
	 * The feature id for the '<em><b>Linked Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINKED_OBJECT__LINKED_ELEMENT = 0;

	/**
	 * The number of structural features of the '<em>Linked Object</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int LINKED_OBJECT_FEATURE_COUNT = 1;

	/**
	 * The feature id for the '<em><b>Linked Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__LINKED_ELEMENT = LINKED_OBJECT__LINKED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int NODE__LOCATION = LINKED_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int NODE__WIDTH = LINKED_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int NODE__HEIGHT = LINKED_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Incoming Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int NODE__INCOMING_CONNECTIONS = LINKED_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Outgoing Connections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int NODE__OUTGOING_CONNECTIONS = LINKED_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Read Only</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__READ_ONLY = LINKED_OBJECT_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Node</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int NODE_FEATURE_COUNT = LINKED_OBJECT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Linked Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_CONTAINER__LINKED_ELEMENT = NODE__LINKED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_CONTAINER__LOCATION = NODE__LOCATION;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_CONTAINER__WIDTH = NODE__WIDTH;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_CONTAINER__HEIGHT = NODE__HEIGHT;

	/**
	 * The feature id for the '<em><b>Incoming Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_CONTAINER__INCOMING_CONNECTIONS = NODE__INCOMING_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Outgoing Connections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_CONTAINER__OUTGOING_CONNECTIONS = NODE__OUTGOING_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Read Only</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_CONTAINER__READ_ONLY = NODE__READ_ONLY;

	/**
	 * The feature id for the '<em><b>Nodes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_CONTAINER__NODES = NODE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Node Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_CONTAINER_FEATURE_COUNT = NODE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Linked Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__LINKED_ELEMENT = NODE_CONTAINER__LINKED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__LOCATION = NODE_CONTAINER__LOCATION;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__WIDTH = NODE_CONTAINER__WIDTH;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__HEIGHT = NODE_CONTAINER__HEIGHT;

	/**
	 * The feature id for the '<em><b>Incoming Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__INCOMING_CONNECTIONS = NODE_CONTAINER__INCOMING_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Outgoing Connections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__OUTGOING_CONNECTIONS = NODE_CONTAINER__OUTGOING_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Read Only</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__READ_ONLY = NODE_CONTAINER__READ_ONLY;

	/**
	 * The feature id for the '<em><b>Nodes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DIAGRAM__NODES = NODE_CONTAINER__NODES;

	/**
	 * The number of structural features of the '<em>Diagram</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DIAGRAM_FEATURE_COUNT = NODE_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Linked Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__LINKED_ELEMENT = LINKED_OBJECT__LINKED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__NAME = LINKED_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Source</b></em>' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int LINK__SOURCE = LINKED_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int LINK__TARGET = LINKED_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Bendpoints</b></em>' attribute list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int LINK__BENDPOINTS = LINKED_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Source End Point</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__SOURCE_END_POINT = LINKED_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Target End Point</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__TARGET_END_POINT = LINKED_OBJECT_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Link</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int LINK_FEATURE_COUNT = LINKED_OBJECT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Linked Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_NODE__LINKED_ELEMENT = NODE__LINKED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int NAMED_NODE__LOCATION = NODE__LOCATION;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int NAMED_NODE__WIDTH = NODE__WIDTH;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int NAMED_NODE__HEIGHT = NODE__HEIGHT;

	/**
	 * The feature id for the '<em><b>Incoming Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int NAMED_NODE__INCOMING_CONNECTIONS = NODE__INCOMING_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Outgoing Connections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int NAMED_NODE__OUTGOING_CONNECTIONS = NODE__OUTGOING_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Read Only</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_NODE__READ_ONLY = NODE__READ_ONLY;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int NAMED_NODE__NAME = NODE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Named Node</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int NAMED_NODE_FEATURE_COUNT = NODE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Linked Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_DIAGRAM__LINKED_ELEMENT = DIAGRAM__LINKED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_DIAGRAM__LOCATION = DIAGRAM__LOCATION;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_DIAGRAM__WIDTH = DIAGRAM__WIDTH;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_DIAGRAM__HEIGHT = DIAGRAM__HEIGHT;

	/**
	 * The feature id for the '<em><b>Incoming Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_DIAGRAM__INCOMING_CONNECTIONS = DIAGRAM__INCOMING_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Outgoing Connections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_DIAGRAM__OUTGOING_CONNECTIONS = DIAGRAM__OUTGOING_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Read Only</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_DIAGRAM__READ_ONLY = DIAGRAM__READ_ONLY;

	/**
	 * The feature id for the '<em><b>Nodes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ACTIVITY_DIAGRAM__NODES = DIAGRAM__NODES;

	/**
	 * The number of structural features of the '<em>Activity Diagram</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ACTIVITY_DIAGRAM_FEATURE_COUNT = DIAGRAM_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Linked Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPED_NODE__LINKED_ELEMENT = NODE__LINKED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int TYPED_NODE__LOCATION = NODE__LOCATION;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int TYPED_NODE__WIDTH = NODE__WIDTH;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int TYPED_NODE__HEIGHT = NODE__HEIGHT;

	/**
	 * The feature id for the '<em><b>Incoming Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int TYPED_NODE__INCOMING_CONNECTIONS = NODE__INCOMING_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Outgoing Connections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int TYPED_NODE__OUTGOING_CONNECTIONS = NODE__OUTGOING_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Read Only</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPED_NODE__READ_ONLY = NODE__READ_ONLY;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int TYPED_NODE__TYPE = NODE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Typed Node</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int TYPED_NODE_FEATURE_COUNT = NODE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Linked Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DEPENDENCY_DIAGRAM__LINKED_ELEMENT = DIAGRAM__LINKED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DEPENDENCY_DIAGRAM__LOCATION = DIAGRAM__LOCATION;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DEPENDENCY_DIAGRAM__WIDTH = DIAGRAM__WIDTH;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DEPENDENCY_DIAGRAM__HEIGHT = DIAGRAM__HEIGHT;

	/**
	 * The feature id for the '<em><b>Incoming Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DEPENDENCY_DIAGRAM__INCOMING_CONNECTIONS = DIAGRAM__INCOMING_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Outgoing Connections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DEPENDENCY_DIAGRAM__OUTGOING_CONNECTIONS = DIAGRAM__OUTGOING_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Read Only</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DEPENDENCY_DIAGRAM__READ_ONLY = DIAGRAM__READ_ONLY;

	/**
	 * The feature id for the '<em><b>Nodes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DEPENDENCY_DIAGRAM__NODES = DIAGRAM__NODES;

	/**
	 * The number of structural features of the '<em>Work Product Dependency Diagram</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DEPENDENCY_DIAGRAM_FEATURE_COUNT = DIAGRAM_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Linked Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_NODE__LINKED_ELEMENT = NAMED_NODE__LINKED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_NODE__LOCATION = NAMED_NODE__LOCATION;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_NODE__WIDTH = NAMED_NODE__WIDTH;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_NODE__HEIGHT = NAMED_NODE__HEIGHT;

	/**
	 * The feature id for the '<em><b>Incoming Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_NODE__INCOMING_CONNECTIONS = NAMED_NODE__INCOMING_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Outgoing Connections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_NODE__OUTGOING_CONNECTIONS = NAMED_NODE__OUTGOING_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Read Only</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_NODE__READ_ONLY = NAMED_NODE__READ_ONLY;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_NODE__NAME = NAMED_NODE__NAME;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_NODE__TYPE = NAMED_NODE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Work Product Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_NODE_FEATURE_COUNT = NAMED_NODE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Linked Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_DETAIL_DIAGRAM__LINKED_ELEMENT = DIAGRAM__LINKED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_DETAIL_DIAGRAM__LOCATION = DIAGRAM__LOCATION;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_DETAIL_DIAGRAM__WIDTH = DIAGRAM__WIDTH;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_DETAIL_DIAGRAM__HEIGHT = DIAGRAM__HEIGHT;

	/**
	 * The feature id for the '<em><b>Incoming Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_DETAIL_DIAGRAM__INCOMING_CONNECTIONS = DIAGRAM__INCOMING_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Outgoing Connections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_DETAIL_DIAGRAM__OUTGOING_CONNECTIONS = DIAGRAM__OUTGOING_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Read Only</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_DETAIL_DIAGRAM__READ_ONLY = DIAGRAM__READ_ONLY;

	/**
	 * The feature id for the '<em><b>Nodes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_DETAIL_DIAGRAM__NODES = DIAGRAM__NODES;

	/**
	 * The feature id for the '<em><b>Auto Layout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_DETAIL_DIAGRAM__AUTO_LAYOUT = DIAGRAM_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Activity Detail Diagram</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_DETAIL_DIAGRAM_FEATURE_COUNT = DIAGRAM_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Linked Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_NODE__LINKED_ELEMENT = NAMED_NODE__LINKED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_NODE__LOCATION = NAMED_NODE__LOCATION;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_NODE__WIDTH = NAMED_NODE__WIDTH;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_NODE__HEIGHT = NAMED_NODE__HEIGHT;

	/**
	 * The feature id for the '<em><b>Incoming Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_NODE__INCOMING_CONNECTIONS = NAMED_NODE__INCOMING_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Outgoing Connections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_NODE__OUTGOING_CONNECTIONS = NAMED_NODE__OUTGOING_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Read Only</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_NODE__READ_ONLY = NAMED_NODE__READ_ONLY;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_NODE__NAME = NAMED_NODE__NAME;

	/**
	 * The number of structural features of the '<em>Role Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_NODE_FEATURE_COUNT = NAMED_NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Linked Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_TASK_COMPOSITE__LINKED_ELEMENT = NODE_CONTAINER__LINKED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_TASK_COMPOSITE__LOCATION = NODE_CONTAINER__LOCATION;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_TASK_COMPOSITE__WIDTH = NODE_CONTAINER__WIDTH;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_TASK_COMPOSITE__HEIGHT = NODE_CONTAINER__HEIGHT;

	/**
	 * The feature id for the '<em><b>Incoming Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_TASK_COMPOSITE__INCOMING_CONNECTIONS = NODE_CONTAINER__INCOMING_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Outgoing Connections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_TASK_COMPOSITE__OUTGOING_CONNECTIONS = NODE_CONTAINER__OUTGOING_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Read Only</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_TASK_COMPOSITE__READ_ONLY = NODE_CONTAINER__READ_ONLY;

	/**
	 * The feature id for the '<em><b>Nodes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_TASK_COMPOSITE__NODES = NODE_CONTAINER__NODES;

	/**
	 * The feature id for the '<em><b>Row Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_TASK_COMPOSITE__ROW_INDEX = NODE_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Role Task Composite</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_TASK_COMPOSITE_FEATURE_COUNT = NODE_CONTAINER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Linked Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_NODE__LINKED_ELEMENT = NAMED_NODE__LINKED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_NODE__LOCATION = NAMED_NODE__LOCATION;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_NODE__WIDTH = NAMED_NODE__WIDTH;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_NODE__HEIGHT = NAMED_NODE__HEIGHT;

	/**
	 * The feature id for the '<em><b>Incoming Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_NODE__INCOMING_CONNECTIONS = NAMED_NODE__INCOMING_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Outgoing Connections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_NODE__OUTGOING_CONNECTIONS = NAMED_NODE__OUTGOING_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Read Only</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_NODE__READ_ONLY = NAMED_NODE__READ_ONLY;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_NODE__NAME = NAMED_NODE__NAME;

	/**
	 * The feature id for the '<em><b>Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_NODE__INDEX = NAMED_NODE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Task Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_NODE_FEATURE_COUNT = NAMED_NODE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Linked Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR_NODE__LINKED_ELEMENT = NAMED_NODE__LINKED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR_NODE__LOCATION = NAMED_NODE__LOCATION;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR_NODE__WIDTH = NAMED_NODE__WIDTH;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR_NODE__HEIGHT = NAMED_NODE__HEIGHT;

	/**
	 * The feature id for the '<em><b>Incoming Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR_NODE__INCOMING_CONNECTIONS = NAMED_NODE__INCOMING_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Outgoing Connections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR_NODE__OUTGOING_CONNECTIONS = NAMED_NODE__OUTGOING_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Read Only</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR_NODE__READ_ONLY = NAMED_NODE__READ_ONLY;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR_NODE__NAME = NAMED_NODE__NAME;

	/**
	 * The feature id for the '<em><b>State</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR_NODE__STATE = NAMED_NODE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Work Product Descriptor Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR_NODE_FEATURE_COUNT = NAMED_NODE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.diagram.model.impl.WorkBreakdownElementNodeImpl <em>Work Breakdown Element Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.diagram.model.impl.WorkBreakdownElementNodeImpl
	 * @see org.eclipse.epf.diagram.model.impl.ModelPackageImpl#getWorkBreakdownElementNode()
	 * @generated
	 */
	int WORK_BREAKDOWN_ELEMENT_NODE = 15;

	/**
	 * The feature id for the '<em><b>Linked Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT_NODE__LINKED_ELEMENT = NAMED_NODE__LINKED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT_NODE__LOCATION = NAMED_NODE__LOCATION;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT_NODE__WIDTH = NAMED_NODE__WIDTH;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT_NODE__HEIGHT = NAMED_NODE__HEIGHT;

	/**
	 * The feature id for the '<em><b>Incoming Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT_NODE__INCOMING_CONNECTIONS = NAMED_NODE__INCOMING_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Outgoing Connections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT_NODE__OUTGOING_CONNECTIONS = NAMED_NODE__OUTGOING_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Read Only</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT_NODE__READ_ONLY = NAMED_NODE__READ_ONLY;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT_NODE__NAME = NAMED_NODE__NAME;

	/**
	 * The number of structural features of the '<em>Work Breakdown Element Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT_NODE_FEATURE_COUNT = NAMED_NODE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.diagram.model.impl.WorkProductCompositeImpl <em>Work Product Composite</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.diagram.model.impl.WorkProductCompositeImpl
	 * @see org.eclipse.epf.diagram.model.impl.ModelPackageImpl#getWorkProductComposite()
	 * @generated
	 */
	int WORK_PRODUCT_COMPOSITE = 16;

	/**
	 * The feature id for the '<em><b>Linked Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_COMPOSITE__LINKED_ELEMENT = NODE_CONTAINER__LINKED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_COMPOSITE__LOCATION = NODE_CONTAINER__LOCATION;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_COMPOSITE__WIDTH = NODE_CONTAINER__WIDTH;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_COMPOSITE__HEIGHT = NODE_CONTAINER__HEIGHT;

	/**
	 * The feature id for the '<em><b>Incoming Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_COMPOSITE__INCOMING_CONNECTIONS = NODE_CONTAINER__INCOMING_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Outgoing Connections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_COMPOSITE__OUTGOING_CONNECTIONS = NODE_CONTAINER__OUTGOING_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Read Only</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_COMPOSITE__READ_ONLY = NODE_CONTAINER__READ_ONLY;

	/**
	 * The feature id for the '<em><b>Nodes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_COMPOSITE__NODES = NODE_CONTAINER__NODES;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_COMPOSITE__TYPE = NODE_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Work Product Composite</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_COMPOSITE_FEATURE_COUNT = NODE_CONTAINER_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '<em>Absolute Bendpoint</em>' data type.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.draw2d.AbsoluteBendpoint
	 * @see org.eclipse.epf.diagram.model.impl.ModelPackageImpl#getAbsoluteBendpoint()
	 * @generated
	 */
    int ABSOLUTE_BENDPOINT = 17;

	/**
	 * The meta object id for the '<em>Point</em>' data type.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.draw2d.geometry.Point
	 * @see org.eclipse.epf.diagram.model.impl.ModelPackageImpl#getPoint()
	 * @generated
	 */
    int POINT = 18;


	/**
	 * The meta object id for the '<em>Role Descriptor</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.RoleDescriptor
	 * @see org.eclipse.epf.diagram.model.impl.ModelPackageImpl#getRoleDescriptor()
	 * @generated
	 */
	int ROLE_DESCRIPTOR = 19;


	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.diagram.model.Diagram <em>Diagram</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Diagram</em>'.
	 * @see org.eclipse.epf.diagram.model.Diagram
	 * @generated
	 */
    EClass getDiagram();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.diagram.model.Link <em>Link</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Link</em>'.
	 * @see org.eclipse.epf.diagram.model.Link
	 * @generated
	 */
    EClass getLink();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.diagram.model.Link#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.epf.diagram.model.Link#getName()
	 * @see #getLink()
	 * @generated
	 */
	EAttribute getLink_Name();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.epf.diagram.model.Link#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Source</em>'.
	 * @see org.eclipse.epf.diagram.model.Link#getSource()
	 * @see #getLink()
	 * @generated
	 */
    EReference getLink_Source();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.epf.diagram.model.Link#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see org.eclipse.epf.diagram.model.Link#getTarget()
	 * @see #getLink()
	 * @generated
	 */
    EReference getLink_Target();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.diagram.model.Link#getBendpoints <em>Bendpoints</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Bendpoints</em>'.
	 * @see org.eclipse.epf.diagram.model.Link#getBendpoints()
	 * @see #getLink()
	 * @generated
	 */
    EAttribute getLink_Bendpoints();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.diagram.model.Link#getSourceEndPoint <em>Source End Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Source End Point</em>'.
	 * @see org.eclipse.epf.diagram.model.Link#getSourceEndPoint()
	 * @see #getLink()
	 * @generated
	 */
	EAttribute getLink_SourceEndPoint();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.diagram.model.Link#getTargetEndPoint <em>Target End Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Target End Point</em>'.
	 * @see org.eclipse.epf.diagram.model.Link#getTargetEndPoint()
	 * @see #getLink()
	 * @generated
	 */
	EAttribute getLink_TargetEndPoint();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.diagram.model.LinkedObject <em>Linked Object</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Linked Object</em>'.
	 * @see org.eclipse.epf.diagram.model.LinkedObject
	 * @generated
	 */
    EClass getLinkedObject();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.epf.diagram.model.LinkedObject#getLinkedElement <em>Linked Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Linked Element</em>'.
	 * @see org.eclipse.epf.diagram.model.LinkedObject#getLinkedElement()
	 * @see #getLinkedObject()
	 * @generated
	 */
	EReference getLinkedObject_LinkedElement();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.diagram.model.NamedNode <em>Named Node</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Named Node</em>'.
	 * @see org.eclipse.epf.diagram.model.NamedNode
	 * @generated
	 */
    EClass getNamedNode();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.diagram.model.NamedNode#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.epf.diagram.model.NamedNode#getName()
	 * @see #getNamedNode()
	 * @generated
	 */
    EAttribute getNamedNode_Name();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.diagram.model.Node <em>Node</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Node</em>'.
	 * @see org.eclipse.epf.diagram.model.Node
	 * @generated
	 */
    EClass getNode();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.diagram.model.Node#getLocation <em>Location</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Location</em>'.
	 * @see org.eclipse.epf.diagram.model.Node#getLocation()
	 * @see #getNode()
	 * @generated
	 */
    EAttribute getNode_Location();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.diagram.model.Node#getWidth <em>Width</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Width</em>'.
	 * @see org.eclipse.epf.diagram.model.Node#getWidth()
	 * @see #getNode()
	 * @generated
	 */
    EAttribute getNode_Width();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.diagram.model.Node#getHeight <em>Height</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Height</em>'.
	 * @see org.eclipse.epf.diagram.model.Node#getHeight()
	 * @see #getNode()
	 * @generated
	 */
    EAttribute getNode_Height();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.diagram.model.Node#getIncomingConnections <em>Incoming Connections</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Incoming Connections</em>'.
	 * @see org.eclipse.epf.diagram.model.Node#getIncomingConnections()
	 * @see #getNode()
	 * @generated
	 */
    EReference getNode_IncomingConnections();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.diagram.model.Node#getOutgoingConnections <em>Outgoing Connections</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Outgoing Connections</em>'.
	 * @see org.eclipse.epf.diagram.model.Node#getOutgoingConnections()
	 * @see #getNode()
	 * @generated
	 */
    EReference getNode_OutgoingConnections();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.diagram.model.Node#isReadOnly <em>Read Only</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Read Only</em>'.
	 * @see org.eclipse.epf.diagram.model.Node#isReadOnly()
	 * @see #getNode()
	 * @generated
	 */
	EAttribute getNode_ReadOnly();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.diagram.model.ActivityDiagram <em>Activity Diagram</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Activity Diagram</em>'.
	 * @see org.eclipse.epf.diagram.model.ActivityDiagram
	 * @generated
	 */
    EClass getActivityDiagram();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.diagram.model.TypedNode <em>Typed Node</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Typed Node</em>'.
	 * @see org.eclipse.epf.diagram.model.TypedNode
	 * @generated
	 */
    EClass getTypedNode();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.diagram.model.TypedNode#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.eclipse.epf.diagram.model.TypedNode#getType()
	 * @see #getTypedNode()
	 * @generated
	 */
    EAttribute getTypedNode_Type();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.diagram.model.WorkProductDependencyDiagram <em>Work Product Dependency Diagram</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Work Product Dependency Diagram</em>'.
	 * @see org.eclipse.epf.diagram.model.WorkProductDependencyDiagram
	 * @generated
	 */
	EClass getWorkProductDependencyDiagram();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.diagram.model.WorkProductNode <em>Work Product Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Work Product Node</em>'.
	 * @see org.eclipse.epf.diagram.model.WorkProductNode
	 * @generated
	 */
	EClass getWorkProductNode();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.diagram.model.WorkProductNode#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.eclipse.epf.diagram.model.WorkProductNode#getType()
	 * @see #getWorkProductNode()
	 * @generated
	 */
	EAttribute getWorkProductNode_Type();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.diagram.model.ActivityDetailDiagram <em>Activity Detail Diagram</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Activity Detail Diagram</em>'.
	 * @see org.eclipse.epf.diagram.model.ActivityDetailDiagram
	 * @generated
	 */
	EClass getActivityDetailDiagram();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.diagram.model.ActivityDetailDiagram#isAutoLayout <em>Auto Layout</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Auto Layout</em>'.
	 * @see org.eclipse.epf.diagram.model.ActivityDetailDiagram#isAutoLayout()
	 * @see #getActivityDetailDiagram()
	 * @generated
	 */
	EAttribute getActivityDetailDiagram_AutoLayout();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.diagram.model.NodeContainer <em>Node Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Node Container</em>'.
	 * @see org.eclipse.epf.diagram.model.NodeContainer
	 * @generated
	 */
	EClass getNodeContainer();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.diagram.model.NodeContainer#getNodes <em>Nodes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Nodes</em>'.
	 * @see org.eclipse.epf.diagram.model.NodeContainer#getNodes()
	 * @see #getNodeContainer()
	 * @generated
	 */
	EReference getNodeContainer_Nodes();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.diagram.model.RoleNode <em>Role Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Role Node</em>'.
	 * @see org.eclipse.epf.diagram.model.RoleNode
	 * @generated
	 */
	EClass getRoleNode();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.diagram.model.RoleTaskComposite <em>Role Task Composite</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Role Task Composite</em>'.
	 * @see org.eclipse.epf.diagram.model.RoleTaskComposite
	 * @generated
	 */
	EClass getRoleTaskComposite();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.diagram.model.RoleTaskComposite#getRowIndex <em>Row Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Row Index</em>'.
	 * @see org.eclipse.epf.diagram.model.RoleTaskComposite#getRowIndex()
	 * @see #getRoleTaskComposite()
	 * @generated
	 */
	EAttribute getRoleTaskComposite_RowIndex();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.diagram.model.TaskNode <em>Task Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Task Node</em>'.
	 * @see org.eclipse.epf.diagram.model.TaskNode
	 * @generated
	 */
	EClass getTaskNode();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.diagram.model.TaskNode#getIndex <em>Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Index</em>'.
	 * @see org.eclipse.epf.diagram.model.TaskNode#getIndex()
	 * @see #getTaskNode()
	 * @generated
	 */
	EAttribute getTaskNode_Index();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.diagram.model.WorkProductDescriptorNode <em>Work Product Descriptor Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Work Product Descriptor Node</em>'.
	 * @see org.eclipse.epf.diagram.model.WorkProductDescriptorNode
	 * @generated
	 */
	EClass getWorkProductDescriptorNode();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.diagram.model.WorkProductDescriptorNode#getState <em>State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>State</em>'.
	 * @see org.eclipse.epf.diagram.model.WorkProductDescriptorNode#getState()
	 * @see #getWorkProductDescriptorNode()
	 * @generated
	 */
	EAttribute getWorkProductDescriptorNode_State();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.diagram.model.WorkBreakdownElementNode <em>Work Breakdown Element Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Work Breakdown Element Node</em>'.
	 * @see org.eclipse.epf.diagram.model.WorkBreakdownElementNode
	 * @generated
	 */
	EClass getWorkBreakdownElementNode();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.diagram.model.WorkProductComposite <em>Work Product Composite</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Work Product Composite</em>'.
	 * @see org.eclipse.epf.diagram.model.WorkProductComposite
	 * @generated
	 */
	EClass getWorkProductComposite();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.diagram.model.WorkProductComposite#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.eclipse.epf.diagram.model.WorkProductComposite#getType()
	 * @see #getWorkProductComposite()
	 * @generated
	 */
	EAttribute getWorkProductComposite_Type();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.draw2d.AbsoluteBendpoint <em>Absolute Bendpoint</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Absolute Bendpoint</em>'.
	 * @see org.eclipse.draw2d.AbsoluteBendpoint
	 * @model instanceClass="org.eclipse.draw2d.AbsoluteBendpoint"
	 * @generated
	 */
    EDataType getAbsoluteBendpoint();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.draw2d.geometry.Point <em>Point</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Point</em>'.
	 * @see org.eclipse.draw2d.geometry.Point
	 * @model instanceClass="org.eclipse.draw2d.geometry.Point"
	 * @generated
	 */
    EDataType getPoint();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.epf.uma.RoleDescriptor <em>Role Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Role Descriptor</em>'.
	 * @see org.eclipse.epf.uma.RoleDescriptor
	 * @model instanceClass="org.eclipse.epf.uma.RoleDescriptor"
	 * @generated
	 */
	EDataType getRoleDescriptor();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
    ModelFactory getModelFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals  {
		/**
		 * The meta object literal for the '{@link org.eclipse.epf.diagram.model.impl.DiagramImpl <em>Diagram</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.diagram.model.impl.DiagramImpl
		 * @see org.eclipse.epf.diagram.model.impl.ModelPackageImpl#getDiagram()
		 * @generated
		 */
		EClass DIAGRAM = eINSTANCE.getDiagram();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.diagram.model.impl.LinkImpl <em>Link</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.diagram.model.impl.LinkImpl
		 * @see org.eclipse.epf.diagram.model.impl.ModelPackageImpl#getLink()
		 * @generated
		 */
		EClass LINK = eINSTANCE.getLink();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINK__NAME = eINSTANCE.getLink_Name();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LINK__SOURCE = eINSTANCE.getLink_Source();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LINK__TARGET = eINSTANCE.getLink_Target();

		/**
		 * The meta object literal for the '<em><b>Bendpoints</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINK__BENDPOINTS = eINSTANCE.getLink_Bendpoints();

		/**
		 * The meta object literal for the '<em><b>Source End Point</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINK__SOURCE_END_POINT = eINSTANCE.getLink_SourceEndPoint();

		/**
		 * The meta object literal for the '<em><b>Target End Point</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINK__TARGET_END_POINT = eINSTANCE.getLink_TargetEndPoint();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.diagram.model.impl.LinkedObjectImpl <em>Linked Object</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.diagram.model.impl.LinkedObjectImpl
		 * @see org.eclipse.epf.diagram.model.impl.ModelPackageImpl#getLinkedObject()
		 * @generated
		 */
		EClass LINKED_OBJECT = eINSTANCE.getLinkedObject();

		/**
		 * The meta object literal for the '<em><b>Linked Element</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LINKED_OBJECT__LINKED_ELEMENT = eINSTANCE.getLinkedObject_LinkedElement();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.diagram.model.impl.NamedNodeImpl <em>Named Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.diagram.model.impl.NamedNodeImpl
		 * @see org.eclipse.epf.diagram.model.impl.ModelPackageImpl#getNamedNode()
		 * @generated
		 */
		EClass NAMED_NODE = eINSTANCE.getNamedNode();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAMED_NODE__NAME = eINSTANCE.getNamedNode_Name();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.diagram.model.impl.NodeImpl <em>Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.diagram.model.impl.NodeImpl
		 * @see org.eclipse.epf.diagram.model.impl.ModelPackageImpl#getNode()
		 * @generated
		 */
		EClass NODE = eINSTANCE.getNode();

		/**
		 * The meta object literal for the '<em><b>Location</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NODE__LOCATION = eINSTANCE.getNode_Location();

		/**
		 * The meta object literal for the '<em><b>Width</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NODE__WIDTH = eINSTANCE.getNode_Width();

		/**
		 * The meta object literal for the '<em><b>Height</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NODE__HEIGHT = eINSTANCE.getNode_Height();

		/**
		 * The meta object literal for the '<em><b>Incoming Connections</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODE__INCOMING_CONNECTIONS = eINSTANCE.getNode_IncomingConnections();

		/**
		 * The meta object literal for the '<em><b>Outgoing Connections</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODE__OUTGOING_CONNECTIONS = eINSTANCE.getNode_OutgoingConnections();

		/**
		 * The meta object literal for the '<em><b>Read Only</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NODE__READ_ONLY = eINSTANCE.getNode_ReadOnly();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.diagram.model.impl.ActivityDiagramImpl <em>Activity Diagram</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.diagram.model.impl.ActivityDiagramImpl
		 * @see org.eclipse.epf.diagram.model.impl.ModelPackageImpl#getActivityDiagram()
		 * @generated
		 */
		EClass ACTIVITY_DIAGRAM = eINSTANCE.getActivityDiagram();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.diagram.model.impl.TypedNodeImpl <em>Typed Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.diagram.model.impl.TypedNodeImpl
		 * @see org.eclipse.epf.diagram.model.impl.ModelPackageImpl#getTypedNode()
		 * @generated
		 */
		EClass TYPED_NODE = eINSTANCE.getTypedNode();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TYPED_NODE__TYPE = eINSTANCE.getTypedNode_Type();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.diagram.model.impl.WorkProductDependencyDiagramImpl <em>Work Product Dependency Diagram</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.diagram.model.impl.WorkProductDependencyDiagramImpl
		 * @see org.eclipse.epf.diagram.model.impl.ModelPackageImpl#getWorkProductDependencyDiagram()
		 * @generated
		 */
		EClass WORK_PRODUCT_DEPENDENCY_DIAGRAM = eINSTANCE.getWorkProductDependencyDiagram();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.diagram.model.impl.WorkProductNodeImpl <em>Work Product Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.diagram.model.impl.WorkProductNodeImpl
		 * @see org.eclipse.epf.diagram.model.impl.ModelPackageImpl#getWorkProductNode()
		 * @generated
		 */
		EClass WORK_PRODUCT_NODE = eINSTANCE.getWorkProductNode();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_PRODUCT_NODE__TYPE = eINSTANCE.getWorkProductNode_Type();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.diagram.model.impl.ActivityDetailDiagramImpl <em>Activity Detail Diagram</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.diagram.model.impl.ActivityDetailDiagramImpl
		 * @see org.eclipse.epf.diagram.model.impl.ModelPackageImpl#getActivityDetailDiagram()
		 * @generated
		 */
		EClass ACTIVITY_DETAIL_DIAGRAM = eINSTANCE.getActivityDetailDiagram();

		/**
		 * The meta object literal for the '<em><b>Auto Layout</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTIVITY_DETAIL_DIAGRAM__AUTO_LAYOUT = eINSTANCE.getActivityDetailDiagram_AutoLayout();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.diagram.model.impl.NodeContainerImpl <em>Node Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.diagram.model.impl.NodeContainerImpl
		 * @see org.eclipse.epf.diagram.model.impl.ModelPackageImpl#getNodeContainer()
		 * @generated
		 */
		EClass NODE_CONTAINER = eINSTANCE.getNodeContainer();

		/**
		 * The meta object literal for the '<em><b>Nodes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODE_CONTAINER__NODES = eINSTANCE.getNodeContainer_Nodes();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.diagram.model.impl.RoleNodeImpl <em>Role Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.diagram.model.impl.RoleNodeImpl
		 * @see org.eclipse.epf.diagram.model.impl.ModelPackageImpl#getRoleNode()
		 * @generated
		 */
		EClass ROLE_NODE = eINSTANCE.getRoleNode();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.diagram.model.impl.RoleTaskCompositeImpl <em>Role Task Composite</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.diagram.model.impl.RoleTaskCompositeImpl
		 * @see org.eclipse.epf.diagram.model.impl.ModelPackageImpl#getRoleTaskComposite()
		 * @generated
		 */
		EClass ROLE_TASK_COMPOSITE = eINSTANCE.getRoleTaskComposite();

		/**
		 * The meta object literal for the '<em><b>Row Index</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ROLE_TASK_COMPOSITE__ROW_INDEX = eINSTANCE.getRoleTaskComposite_RowIndex();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.diagram.model.impl.TaskNodeImpl <em>Task Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.diagram.model.impl.TaskNodeImpl
		 * @see org.eclipse.epf.diagram.model.impl.ModelPackageImpl#getTaskNode()
		 * @generated
		 */
		EClass TASK_NODE = eINSTANCE.getTaskNode();

		/**
		 * The meta object literal for the '<em><b>Index</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK_NODE__INDEX = eINSTANCE.getTaskNode_Index();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.diagram.model.impl.WorkProductDescriptorNodeImpl <em>Work Product Descriptor Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.diagram.model.impl.WorkProductDescriptorNodeImpl
		 * @see org.eclipse.epf.diagram.model.impl.ModelPackageImpl#getWorkProductDescriptorNode()
		 * @generated
		 */
		EClass WORK_PRODUCT_DESCRIPTOR_NODE = eINSTANCE.getWorkProductDescriptorNode();

		/**
		 * The meta object literal for the '<em><b>State</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_PRODUCT_DESCRIPTOR_NODE__STATE = eINSTANCE.getWorkProductDescriptorNode_State();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.diagram.model.impl.WorkBreakdownElementNodeImpl <em>Work Breakdown Element Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.diagram.model.impl.WorkBreakdownElementNodeImpl
		 * @see org.eclipse.epf.diagram.model.impl.ModelPackageImpl#getWorkBreakdownElementNode()
		 * @generated
		 */
		EClass WORK_BREAKDOWN_ELEMENT_NODE = eINSTANCE.getWorkBreakdownElementNode();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.diagram.model.impl.WorkProductCompositeImpl <em>Work Product Composite</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.diagram.model.impl.WorkProductCompositeImpl
		 * @see org.eclipse.epf.diagram.model.impl.ModelPackageImpl#getWorkProductComposite()
		 * @generated
		 */
		EClass WORK_PRODUCT_COMPOSITE = eINSTANCE.getWorkProductComposite();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_PRODUCT_COMPOSITE__TYPE = eINSTANCE.getWorkProductComposite_Type();

		/**
		 * The meta object literal for the '<em>Absolute Bendpoint</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.draw2d.AbsoluteBendpoint
		 * @see org.eclipse.epf.diagram.model.impl.ModelPackageImpl#getAbsoluteBendpoint()
		 * @generated
		 */
		EDataType ABSOLUTE_BENDPOINT = eINSTANCE.getAbsoluteBendpoint();

		/**
		 * The meta object literal for the '<em>Point</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.draw2d.geometry.Point
		 * @see org.eclipse.epf.diagram.model.impl.ModelPackageImpl#getPoint()
		 * @generated
		 */
		EDataType POINT = eINSTANCE.getPoint();

		/**
		 * The meta object literal for the '<em>Role Descriptor</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.RoleDescriptor
		 * @see org.eclipse.epf.diagram.model.impl.ModelPackageImpl#getRoleDescriptor()
		 * @generated
		 */
		EDataType ROLE_DESCRIPTOR = eINSTANCE.getRoleDescriptor();

	}

} //ModelPackage
