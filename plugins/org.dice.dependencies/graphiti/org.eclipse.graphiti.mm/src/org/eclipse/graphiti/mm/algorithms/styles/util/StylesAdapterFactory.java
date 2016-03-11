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
package org.eclipse.graphiti.mm.algorithms.styles.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.graphiti.mm.StyleContainer;

import org.eclipse.graphiti.mm.algorithms.styles.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.graphiti.mm.algorithms.styles.StylesPackage
 * @generated
 */
public class StylesAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static StylesPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StylesAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = StylesPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StylesSwitch<Adapter> modelSwitch =
		new StylesSwitch<Adapter>() {
			@Override
			public Adapter caseRenderingStyle(RenderingStyle object) {
				return createRenderingStyleAdapter();
			}
			@Override
			public Adapter caseStyle(Style object) {
				return createStyleAdapter();
			}
			@Override
			public Adapter caseAbstractStyle(AbstractStyle object) {
				return createAbstractStyleAdapter();
			}
			@Override
			public Adapter caseGradientColoredLocation(GradientColoredLocation object) {
				return createGradientColoredLocationAdapter();
			}
			@Override
			public Adapter caseGradientColoredArea(GradientColoredArea object) {
				return createGradientColoredAreaAdapter();
			}
			@Override
			public Adapter caseGradientColoredAreas(GradientColoredAreas object) {
				return createGradientColoredAreasAdapter();
			}
			@Override
			public Adapter caseAdaptedGradientColoredAreas(AdaptedGradientColoredAreas object) {
				return createAdaptedGradientColoredAreasAdapter();
			}
			@Override
			public Adapter caseFont(Font object) {
				return createFontAdapter();
			}
			@Override
			public Adapter casePoint(Point object) {
				return createPointAdapter();
			}
			@Override
			public Adapter caseColor(Color object) {
				return createColorAdapter();
			}
			@Override
			public Adapter caseStyleContainer(StyleContainer object) {
				return createStyleContainerAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.graphiti.mm.algorithms.styles.RenderingStyle <em>Rendering Style</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.RenderingStyle
	 * @generated
	 */
	public Adapter createRenderingStyleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.graphiti.mm.algorithms.styles.Style <em>Style</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.Style
	 * @generated
	 */
	public Adapter createStyleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.graphiti.mm.algorithms.styles.AbstractStyle <em>Abstract Style</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.AbstractStyle
	 * @generated
	 */
	public Adapter createAbstractStyleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.graphiti.mm.algorithms.styles.GradientColoredLocation <em>Gradient Colored Location</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.GradientColoredLocation
	 * @generated
	 */
	public Adapter createGradientColoredLocationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.graphiti.mm.algorithms.styles.GradientColoredArea <em>Gradient Colored Area</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.GradientColoredArea
	 * @generated
	 */
	public Adapter createGradientColoredAreaAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.graphiti.mm.algorithms.styles.GradientColoredAreas <em>Gradient Colored Areas</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.GradientColoredAreas
	 * @generated
	 */
	public Adapter createGradientColoredAreasAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.graphiti.mm.algorithms.styles.AdaptedGradientColoredAreas <em>Adapted Gradient Colored Areas</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.AdaptedGradientColoredAreas
	 * @generated
	 */
	public Adapter createAdaptedGradientColoredAreasAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.graphiti.mm.algorithms.styles.Font <em>Font</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.Font
	 * @generated
	 */
	public Adapter createFontAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.graphiti.mm.algorithms.styles.Point <em>Point</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.Point
	 * @generated
	 */
	public Adapter createPointAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.graphiti.mm.algorithms.styles.Color <em>Color</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.Color
	 * @generated
	 */
	public Adapter createColorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.graphiti.mm.StyleContainer <em>Style Container</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.graphiti.mm.StyleContainer
	 * @generated
	 */
	public Adapter createStyleContainerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //StylesAdapterFactory
