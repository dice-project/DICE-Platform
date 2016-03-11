/**
 * <copyright>
 * 
 * Copyright (c) 2005, 2010 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 * 
 * </copyright>
 */
package org.eclipse.graphiti.mm.pictograms.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.graphiti.mm.GraphicsAlgorithmContainer;
import org.eclipse.graphiti.mm.PropertyContainer;
import org.eclipse.graphiti.mm.StyleContainer;

import org.eclipse.graphiti.mm.pictograms.*;

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
 * @see org.eclipse.graphiti.mm.pictograms.PictogramsPackage
 * @generated
 */
public class PictogramsSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static PictogramsPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PictogramsSwitch() {
		if (modelPackage == null) {
			modelPackage = PictogramsPackage.eINSTANCE;
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
			case PictogramsPackage.SHAPE: {
				Shape shape = (Shape)theEObject;
				T result = caseShape(shape);
				if (result == null) result = caseAnchorContainer(shape);
				if (result == null) result = casePictogramElement(shape);
				if (result == null) result = caseGraphicsAlgorithmContainer(shape);
				if (result == null) result = casePropertyContainer(shape);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PictogramsPackage.CONTAINER_SHAPE: {
				ContainerShape containerShape = (ContainerShape)theEObject;
				T result = caseContainerShape(containerShape);
				if (result == null) result = caseShape(containerShape);
				if (result == null) result = caseAnchorContainer(containerShape);
				if (result == null) result = casePictogramElement(containerShape);
				if (result == null) result = caseGraphicsAlgorithmContainer(containerShape);
				if (result == null) result = casePropertyContainer(containerShape);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PictogramsPackage.DIAGRAM: {
				Diagram diagram = (Diagram)theEObject;
				T result = caseDiagram(diagram);
				if (result == null) result = caseContainerShape(diagram);
				if (result == null) result = caseStyleContainer(diagram);
				if (result == null) result = caseShape(diagram);
				if (result == null) result = caseAnchorContainer(diagram);
				if (result == null) result = casePictogramElement(diagram);
				if (result == null) result = caseGraphicsAlgorithmContainer(diagram);
				if (result == null) result = casePropertyContainer(diagram);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PictogramsPackage.PICTOGRAM_ELEMENT: {
				PictogramElement pictogramElement = (PictogramElement)theEObject;
				T result = casePictogramElement(pictogramElement);
				if (result == null) result = caseGraphicsAlgorithmContainer(pictogramElement);
				if (result == null) result = casePropertyContainer(pictogramElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PictogramsPackage.CONNECTION: {
				Connection connection = (Connection)theEObject;
				T result = caseConnection(connection);
				if (result == null) result = caseAnchorContainer(connection);
				if (result == null) result = casePictogramElement(connection);
				if (result == null) result = caseGraphicsAlgorithmContainer(connection);
				if (result == null) result = casePropertyContainer(connection);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PictogramsPackage.ANCHOR: {
				Anchor anchor = (Anchor)theEObject;
				T result = caseAnchor(anchor);
				if (result == null) result = casePictogramElement(anchor);
				if (result == null) result = caseGraphicsAlgorithmContainer(anchor);
				if (result == null) result = casePropertyContainer(anchor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PictogramsPackage.ANCHOR_CONTAINER: {
				AnchorContainer anchorContainer = (AnchorContainer)theEObject;
				T result = caseAnchorContainer(anchorContainer);
				if (result == null) result = casePictogramElement(anchorContainer);
				if (result == null) result = caseGraphicsAlgorithmContainer(anchorContainer);
				if (result == null) result = casePropertyContainer(anchorContainer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PictogramsPackage.FIX_POINT_ANCHOR: {
				FixPointAnchor fixPointAnchor = (FixPointAnchor)theEObject;
				T result = caseFixPointAnchor(fixPointAnchor);
				if (result == null) result = caseAnchor(fixPointAnchor);
				if (result == null) result = casePictogramElement(fixPointAnchor);
				if (result == null) result = caseGraphicsAlgorithmContainer(fixPointAnchor);
				if (result == null) result = casePropertyContainer(fixPointAnchor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PictogramsPackage.BOX_RELATIVE_ANCHOR: {
				BoxRelativeAnchor boxRelativeAnchor = (BoxRelativeAnchor)theEObject;
				T result = caseBoxRelativeAnchor(boxRelativeAnchor);
				if (result == null) result = caseAnchor(boxRelativeAnchor);
				if (result == null) result = casePictogramElement(boxRelativeAnchor);
				if (result == null) result = caseGraphicsAlgorithmContainer(boxRelativeAnchor);
				if (result == null) result = casePropertyContainer(boxRelativeAnchor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PictogramsPackage.CHOPBOX_ANCHOR: {
				ChopboxAnchor chopboxAnchor = (ChopboxAnchor)theEObject;
				T result = caseChopboxAnchor(chopboxAnchor);
				if (result == null) result = caseAnchor(chopboxAnchor);
				if (result == null) result = casePictogramElement(chopboxAnchor);
				if (result == null) result = caseGraphicsAlgorithmContainer(chopboxAnchor);
				if (result == null) result = casePropertyContainer(chopboxAnchor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PictogramsPackage.CONNECTION_DECORATOR: {
				ConnectionDecorator connectionDecorator = (ConnectionDecorator)theEObject;
				T result = caseConnectionDecorator(connectionDecorator);
				if (result == null) result = caseShape(connectionDecorator);
				if (result == null) result = caseAnchorContainer(connectionDecorator);
				if (result == null) result = casePictogramElement(connectionDecorator);
				if (result == null) result = caseGraphicsAlgorithmContainer(connectionDecorator);
				if (result == null) result = casePropertyContainer(connectionDecorator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PictogramsPackage.FREE_FORM_CONNECTION: {
				FreeFormConnection freeFormConnection = (FreeFormConnection)theEObject;
				T result = caseFreeFormConnection(freeFormConnection);
				if (result == null) result = caseConnection(freeFormConnection);
				if (result == null) result = caseAnchorContainer(freeFormConnection);
				if (result == null) result = casePictogramElement(freeFormConnection);
				if (result == null) result = caseGraphicsAlgorithmContainer(freeFormConnection);
				if (result == null) result = casePropertyContainer(freeFormConnection);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PictogramsPackage.MANHATTAN_CONNECTION: {
				ManhattanConnection manhattanConnection = (ManhattanConnection)theEObject;
				T result = caseManhattanConnection(manhattanConnection);
				if (result == null) result = caseConnection(manhattanConnection);
				if (result == null) result = caseAnchorContainer(manhattanConnection);
				if (result == null) result = casePictogramElement(manhattanConnection);
				if (result == null) result = caseGraphicsAlgorithmContainer(manhattanConnection);
				if (result == null) result = casePropertyContainer(manhattanConnection);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PictogramsPackage.PICTOGRAM_LINK: {
				PictogramLink pictogramLink = (PictogramLink)theEObject;
				T result = casePictogramLink(pictogramLink);
				if (result == null) result = casePropertyContainer(pictogramLink);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Shape</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Shape</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseShape(Shape object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Container Shape</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Container Shape</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseContainerShape(ContainerShape object) {
		return null;
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
	 * Returns the result of interpreting the object as an instance of '<em>Pictogram Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Pictogram Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePictogramElement(PictogramElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Connection</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Connection</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConnection(Connection object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Anchor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Anchor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAnchor(Anchor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Anchor Container</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Anchor Container</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAnchorContainer(AnchorContainer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Fix Point Anchor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Fix Point Anchor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFixPointAnchor(FixPointAnchor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Box Relative Anchor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Box Relative Anchor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBoxRelativeAnchor(BoxRelativeAnchor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Chopbox Anchor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Chopbox Anchor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseChopboxAnchor(ChopboxAnchor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Connection Decorator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Connection Decorator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConnectionDecorator(ConnectionDecorator object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Free Form Connection</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Free Form Connection</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFreeFormConnection(FreeFormConnection object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Manhattan Connection</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Manhattan Connection</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseManhattanConnection(ManhattanConnection object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Pictogram Link</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Pictogram Link</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePictogramLink(PictogramLink object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Property Container</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Property Container</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePropertyContainer(PropertyContainer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Graphics Algorithm Container</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Graphics Algorithm Container</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGraphicsAlgorithmContainer(GraphicsAlgorithmContainer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Style Container</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Style Container</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStyleContainer(StyleContainer object) {
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

} //PictogramsSwitch
