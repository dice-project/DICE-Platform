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
package org.eclipse.epf.diagram.model.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.diagram.model.*;

import org.eclipse.epf.diagram.model.ActivityDetailDiagram;
import org.eclipse.epf.diagram.model.ActivityDiagram;
import org.eclipse.epf.diagram.model.Diagram;
import org.eclipse.epf.diagram.model.Link;
import org.eclipse.epf.diagram.model.LinkedObject;
import org.eclipse.epf.diagram.model.ModelPackage;
import org.eclipse.epf.diagram.model.NamedNode;
import org.eclipse.epf.diagram.model.Node;
import org.eclipse.epf.diagram.model.NodeContainer;
import org.eclipse.epf.diagram.model.RoleNode;
import org.eclipse.epf.diagram.model.RoleTaskComposite;
import org.eclipse.epf.diagram.model.TaskNode;
import org.eclipse.epf.diagram.model.TypedNode;
import org.eclipse.epf.diagram.model.WorkBreakdownElementNode;
import org.eclipse.epf.diagram.model.WorkProductComposite;
import org.eclipse.epf.diagram.model.WorkProductDependencyDiagram;
import org.eclipse.epf.diagram.model.WorkProductDescriptorNode;
import org.eclipse.epf.diagram.model.WorkProductNode;


/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.eclipse.epf.diagram.model.ModelPackage
 * @generated
 */
public class ModelSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected static ModelPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ModelSwitch() {
		if (modelPackage == null) {
			modelPackage = ModelPackage.eINSTANCE;
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
    public T doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
    protected T doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List<EClass> eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch(eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
    protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case ModelPackage.DIAGRAM: {
				Diagram diagram = (Diagram)theEObject;
				T result = caseDiagram(diagram);
				if (result == null) result = caseNodeContainer(diagram);
				if (result == null) result = caseNode(diagram);
				if (result == null) result = caseLinkedObject(diagram);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.LINK: {
				Link link = (Link)theEObject;
				T result = caseLink(link);
				if (result == null) result = caseLinkedObject(link);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.LINKED_OBJECT: {
				LinkedObject linkedObject = (LinkedObject)theEObject;
				T result = caseLinkedObject(linkedObject);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.NAMED_NODE: {
				NamedNode namedNode = (NamedNode)theEObject;
				T result = caseNamedNode(namedNode);
				if (result == null) result = caseNode(namedNode);
				if (result == null) result = caseLinkedObject(namedNode);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.NODE: {
				Node node = (Node)theEObject;
				T result = caseNode(node);
				if (result == null) result = caseLinkedObject(node);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.ACTIVITY_DIAGRAM: {
				ActivityDiagram activityDiagram = (ActivityDiagram)theEObject;
				T result = caseActivityDiagram(activityDiagram);
				if (result == null) result = caseDiagram(activityDiagram);
				if (result == null) result = caseNodeContainer(activityDiagram);
				if (result == null) result = caseNode(activityDiagram);
				if (result == null) result = caseLinkedObject(activityDiagram);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TYPED_NODE: {
				TypedNode typedNode = (TypedNode)theEObject;
				T result = caseTypedNode(typedNode);
				if (result == null) result = caseNode(typedNode);
				if (result == null) result = caseLinkedObject(typedNode);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.WORK_PRODUCT_DEPENDENCY_DIAGRAM: {
				WorkProductDependencyDiagram workProductDependencyDiagram = (WorkProductDependencyDiagram)theEObject;
				T result = caseWorkProductDependencyDiagram(workProductDependencyDiagram);
				if (result == null) result = caseDiagram(workProductDependencyDiagram);
				if (result == null) result = caseNodeContainer(workProductDependencyDiagram);
				if (result == null) result = caseNode(workProductDependencyDiagram);
				if (result == null) result = caseLinkedObject(workProductDependencyDiagram);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.WORK_PRODUCT_NODE: {
				WorkProductNode workProductNode = (WorkProductNode)theEObject;
				T result = caseWorkProductNode(workProductNode);
				if (result == null) result = caseNamedNode(workProductNode);
				if (result == null) result = caseNode(workProductNode);
				if (result == null) result = caseLinkedObject(workProductNode);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.ACTIVITY_DETAIL_DIAGRAM: {
				ActivityDetailDiagram activityDetailDiagram = (ActivityDetailDiagram)theEObject;
				T result = caseActivityDetailDiagram(activityDetailDiagram);
				if (result == null) result = caseDiagram(activityDetailDiagram);
				if (result == null) result = caseNodeContainer(activityDetailDiagram);
				if (result == null) result = caseNode(activityDetailDiagram);
				if (result == null) result = caseLinkedObject(activityDetailDiagram);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.NODE_CONTAINER: {
				NodeContainer nodeContainer = (NodeContainer)theEObject;
				T result = caseNodeContainer(nodeContainer);
				if (result == null) result = caseNode(nodeContainer);
				if (result == null) result = caseLinkedObject(nodeContainer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.ROLE_NODE: {
				RoleNode roleNode = (RoleNode)theEObject;
				T result = caseRoleNode(roleNode);
				if (result == null) result = caseNamedNode(roleNode);
				if (result == null) result = caseNode(roleNode);
				if (result == null) result = caseLinkedObject(roleNode);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.ROLE_TASK_COMPOSITE: {
				RoleTaskComposite roleTaskComposite = (RoleTaskComposite)theEObject;
				T result = caseRoleTaskComposite(roleTaskComposite);
				if (result == null) result = caseNodeContainer(roleTaskComposite);
				if (result == null) result = caseNode(roleTaskComposite);
				if (result == null) result = caseLinkedObject(roleTaskComposite);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TASK_NODE: {
				TaskNode taskNode = (TaskNode)theEObject;
				T result = caseTaskNode(taskNode);
				if (result == null) result = caseNamedNode(taskNode);
				if (result == null) result = caseNode(taskNode);
				if (result == null) result = caseLinkedObject(taskNode);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.WORK_PRODUCT_DESCRIPTOR_NODE: {
				WorkProductDescriptorNode workProductDescriptorNode = (WorkProductDescriptorNode)theEObject;
				T result = caseWorkProductDescriptorNode(workProductDescriptorNode);
				if (result == null) result = caseNamedNode(workProductDescriptorNode);
				if (result == null) result = caseNode(workProductDescriptorNode);
				if (result == null) result = caseLinkedObject(workProductDescriptorNode);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.WORK_BREAKDOWN_ELEMENT_NODE: {
				WorkBreakdownElementNode workBreakdownElementNode = (WorkBreakdownElementNode)theEObject;
				T result = caseWorkBreakdownElementNode(workBreakdownElementNode);
				if (result == null) result = caseNamedNode(workBreakdownElementNode);
				if (result == null) result = caseNode(workBreakdownElementNode);
				if (result == null) result = caseLinkedObject(workBreakdownElementNode);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.WORK_PRODUCT_COMPOSITE: {
				WorkProductComposite workProductComposite = (WorkProductComposite)theEObject;
				T result = caseWorkProductComposite(workProductComposite);
				if (result == null) result = caseNodeContainer(workProductComposite);
				if (result == null) result = caseNode(workProductComposite);
				if (result == null) result = caseLinkedObject(workProductComposite);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Diagram</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Diagram</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public T caseDiagram(Diagram object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Link</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Link</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public T caseLink(Link object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Linked Object</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Linked Object</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public T caseLinkedObject(LinkedObject object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Named Node</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Named Node</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public T caseNamedNode(NamedNode object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Node</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Node</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public T caseNode(Node object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Activity Diagram</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Activity Diagram</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public T caseActivityDiagram(ActivityDiagram object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Typed Node</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Typed Node</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public T caseTypedNode(TypedNode object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Work Product Dependency Diagram</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Work Product Dependency Diagram</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWorkProductDependencyDiagram(WorkProductDependencyDiagram object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Work Product Node</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Work Product Node</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWorkProductNode(WorkProductNode object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Activity Detail Diagram</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Activity Detail Diagram</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseActivityDetailDiagram(ActivityDetailDiagram object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Node Container</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Node Container</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNodeContainer(NodeContainer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Role Node</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Role Node</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRoleNode(RoleNode object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Role Task Composite</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Role Task Composite</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRoleTaskComposite(RoleTaskComposite object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Task Node</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Task Node</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTaskNode(TaskNode object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Work Product Descriptor Node</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Work Product Descriptor Node</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWorkProductDescriptorNode(WorkProductDescriptorNode object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Work Breakdown Element Node</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Work Breakdown Element Node</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWorkBreakdownElementNode(WorkBreakdownElementNode object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Work Product Composite</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Work Product Composite</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWorkProductComposite(WorkProductComposite object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch, but this is the last case anyway.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
    public T defaultCase(EObject object) {
		return null;
	}

} //ModelSwitch
