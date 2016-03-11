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
package org.eclipse.graphiti.mm.algorithms.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.graphiti.mm.GraphicsAlgorithmContainer;
import org.eclipse.graphiti.mm.PropertyContainer;

import org.eclipse.graphiti.mm.algorithms.*;

import org.eclipse.graphiti.mm.algorithms.styles.AbstractStyle;

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
 * @see org.eclipse.graphiti.mm.algorithms.AlgorithmsPackage
 * @generated
 */
public class AlgorithmsSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static AlgorithmsPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AlgorithmsSwitch() {
		if (modelPackage == null) {
			modelPackage = AlgorithmsPackage.eINSTANCE;
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
			case AlgorithmsPackage.GRAPHICS_ALGORITHM: {
				GraphicsAlgorithm graphicsAlgorithm = (GraphicsAlgorithm)theEObject;
				T result = caseGraphicsAlgorithm(graphicsAlgorithm);
				if (result == null) result = caseGraphicsAlgorithmContainer(graphicsAlgorithm);
				if (result == null) result = caseAbstractStyle(graphicsAlgorithm);
				if (result == null) result = casePropertyContainer(graphicsAlgorithm);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AlgorithmsPackage.POLYLINE: {
				Polyline polyline = (Polyline)theEObject;
				T result = casePolyline(polyline);
				if (result == null) result = caseGraphicsAlgorithm(polyline);
				if (result == null) result = caseGraphicsAlgorithmContainer(polyline);
				if (result == null) result = caseAbstractStyle(polyline);
				if (result == null) result = casePropertyContainer(polyline);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AlgorithmsPackage.ELLIPSE: {
				Ellipse ellipse = (Ellipse)theEObject;
				T result = caseEllipse(ellipse);
				if (result == null) result = caseGraphicsAlgorithm(ellipse);
				if (result == null) result = caseGraphicsAlgorithmContainer(ellipse);
				if (result == null) result = caseAbstractStyle(ellipse);
				if (result == null) result = casePropertyContainer(ellipse);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AlgorithmsPackage.TEXT: {
				Text text = (Text)theEObject;
				T result = caseText(text);
				if (result == null) result = caseAbstractText(text);
				if (result == null) result = caseGraphicsAlgorithm(text);
				if (result == null) result = caseGraphicsAlgorithmContainer(text);
				if (result == null) result = caseAbstractStyle(text);
				if (result == null) result = casePropertyContainer(text);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AlgorithmsPackage.POLYGON: {
				Polygon polygon = (Polygon)theEObject;
				T result = casePolygon(polygon);
				if (result == null) result = casePolyline(polygon);
				if (result == null) result = caseGraphicsAlgorithm(polygon);
				if (result == null) result = caseGraphicsAlgorithmContainer(polygon);
				if (result == null) result = caseAbstractStyle(polygon);
				if (result == null) result = casePropertyContainer(polygon);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AlgorithmsPackage.RECTANGLE: {
				Rectangle rectangle = (Rectangle)theEObject;
				T result = caseRectangle(rectangle);
				if (result == null) result = caseGraphicsAlgorithm(rectangle);
				if (result == null) result = caseGraphicsAlgorithmContainer(rectangle);
				if (result == null) result = caseAbstractStyle(rectangle);
				if (result == null) result = casePropertyContainer(rectangle);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AlgorithmsPackage.ROUNDED_RECTANGLE: {
				RoundedRectangle roundedRectangle = (RoundedRectangle)theEObject;
				T result = caseRoundedRectangle(roundedRectangle);
				if (result == null) result = caseGraphicsAlgorithm(roundedRectangle);
				if (result == null) result = caseGraphicsAlgorithmContainer(roundedRectangle);
				if (result == null) result = caseAbstractStyle(roundedRectangle);
				if (result == null) result = casePropertyContainer(roundedRectangle);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AlgorithmsPackage.IMAGE: {
				Image image = (Image)theEObject;
				T result = caseImage(image);
				if (result == null) result = caseGraphicsAlgorithm(image);
				if (result == null) result = caseGraphicsAlgorithmContainer(image);
				if (result == null) result = caseAbstractStyle(image);
				if (result == null) result = casePropertyContainer(image);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AlgorithmsPackage.PLATFORM_GRAPHICS_ALGORITHM: {
				PlatformGraphicsAlgorithm platformGraphicsAlgorithm = (PlatformGraphicsAlgorithm)theEObject;
				T result = casePlatformGraphicsAlgorithm(platformGraphicsAlgorithm);
				if (result == null) result = caseGraphicsAlgorithm(platformGraphicsAlgorithm);
				if (result == null) result = caseGraphicsAlgorithmContainer(platformGraphicsAlgorithm);
				if (result == null) result = caseAbstractStyle(platformGraphicsAlgorithm);
				if (result == null) result = casePropertyContainer(platformGraphicsAlgorithm);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AlgorithmsPackage.ABSTRACT_TEXT: {
				AbstractText abstractText = (AbstractText)theEObject;
				T result = caseAbstractText(abstractText);
				if (result == null) result = caseGraphicsAlgorithm(abstractText);
				if (result == null) result = caseGraphicsAlgorithmContainer(abstractText);
				if (result == null) result = caseAbstractStyle(abstractText);
				if (result == null) result = casePropertyContainer(abstractText);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AlgorithmsPackage.MULTI_TEXT: {
				MultiText multiText = (MultiText)theEObject;
				T result = caseMultiText(multiText);
				if (result == null) result = caseAbstractText(multiText);
				if (result == null) result = caseGraphicsAlgorithm(multiText);
				if (result == null) result = caseGraphicsAlgorithmContainer(multiText);
				if (result == null) result = caseAbstractStyle(multiText);
				if (result == null) result = casePropertyContainer(multiText);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Graphics Algorithm</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Graphics Algorithm</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGraphicsAlgorithm(GraphicsAlgorithm object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Polyline</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Polyline</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePolyline(Polyline object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ellipse</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ellipse</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEllipse(Ellipse object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Text</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Text</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseText(Text object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Polygon</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Polygon</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePolygon(Polygon object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Rectangle</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Rectangle</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRectangle(Rectangle object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Rounded Rectangle</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Rounded Rectangle</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRoundedRectangle(RoundedRectangle object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Image</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Image</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseImage(Image object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Platform Graphics Algorithm</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Platform Graphics Algorithm</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePlatformGraphicsAlgorithm(PlatformGraphicsAlgorithm object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Text</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Text</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractText(AbstractText object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Multi Text</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Multi Text</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMultiText(MultiText object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Style</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Style</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractStyle(AbstractStyle object) {
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

} //AlgorithmsSwitch
