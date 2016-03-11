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

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.graphiti.mm.GraphicsAlgorithmContainer;
import org.eclipse.graphiti.mm.PropertyContainer;
import org.eclipse.graphiti.mm.StyleContainer;

import org.eclipse.graphiti.mm.pictograms.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.graphiti.mm.pictograms.PictogramsPackage
 * @generated
 */
public class PictogramsAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static PictogramsPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PictogramsAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = PictogramsPackage.eINSTANCE;
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
	protected PictogramsSwitch<Adapter> modelSwitch =
		new PictogramsSwitch<Adapter>() {
			@Override
			public Adapter caseShape(Shape object) {
				return createShapeAdapter();
			}
			@Override
			public Adapter caseContainerShape(ContainerShape object) {
				return createContainerShapeAdapter();
			}
			@Override
			public Adapter caseDiagram(Diagram object) {
				return createDiagramAdapter();
			}
			@Override
			public Adapter casePictogramElement(PictogramElement object) {
				return createPictogramElementAdapter();
			}
			@Override
			public Adapter caseConnection(Connection object) {
				return createConnectionAdapter();
			}
			@Override
			public Adapter caseAnchor(Anchor object) {
				return createAnchorAdapter();
			}
			@Override
			public Adapter caseAnchorContainer(AnchorContainer object) {
				return createAnchorContainerAdapter();
			}
			@Override
			public Adapter caseFixPointAnchor(FixPointAnchor object) {
				return createFixPointAnchorAdapter();
			}
			@Override
			public Adapter caseBoxRelativeAnchor(BoxRelativeAnchor object) {
				return createBoxRelativeAnchorAdapter();
			}
			@Override
			public Adapter caseChopboxAnchor(ChopboxAnchor object) {
				return createChopboxAnchorAdapter();
			}
			@Override
			public Adapter caseConnectionDecorator(ConnectionDecorator object) {
				return createConnectionDecoratorAdapter();
			}
			@Override
			public Adapter caseFreeFormConnection(FreeFormConnection object) {
				return createFreeFormConnectionAdapter();
			}
			@Override
			public Adapter caseManhattanConnection(ManhattanConnection object) {
				return createManhattanConnectionAdapter();
			}
			@Override
			public Adapter casePictogramLink(PictogramLink object) {
				return createPictogramLinkAdapter();
			}
			@Override
			public Adapter casePropertyContainer(PropertyContainer object) {
				return createPropertyContainerAdapter();
			}
			@Override
			public Adapter caseGraphicsAlgorithmContainer(GraphicsAlgorithmContainer object) {
				return createGraphicsAlgorithmContainerAdapter();
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
	 * Creates a new adapter for an object of class '{@link org.eclipse.graphiti.mm.pictograms.Shape <em>Shape</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.graphiti.mm.pictograms.Shape
	 * @generated
	 */
	public Adapter createShapeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.graphiti.mm.pictograms.ContainerShape <em>Container Shape</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.graphiti.mm.pictograms.ContainerShape
	 * @generated
	 */
	public Adapter createContainerShapeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.graphiti.mm.pictograms.Diagram <em>Diagram</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.graphiti.mm.pictograms.Diagram
	 * @generated
	 */
	public Adapter createDiagramAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.graphiti.mm.pictograms.PictogramElement <em>Pictogram Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.graphiti.mm.pictograms.PictogramElement
	 * @generated
	 */
	public Adapter createPictogramElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.graphiti.mm.pictograms.Connection <em>Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.graphiti.mm.pictograms.Connection
	 * @generated
	 */
	public Adapter createConnectionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.graphiti.mm.pictograms.Anchor <em>Anchor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.graphiti.mm.pictograms.Anchor
	 * @generated
	 */
	public Adapter createAnchorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.graphiti.mm.pictograms.AnchorContainer <em>Anchor Container</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.graphiti.mm.pictograms.AnchorContainer
	 * @generated
	 */
	public Adapter createAnchorContainerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.graphiti.mm.pictograms.FixPointAnchor <em>Fix Point Anchor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.graphiti.mm.pictograms.FixPointAnchor
	 * @generated
	 */
	public Adapter createFixPointAnchorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor <em>Box Relative Anchor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor
	 * @generated
	 */
	public Adapter createBoxRelativeAnchorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.graphiti.mm.pictograms.ChopboxAnchor <em>Chopbox Anchor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.graphiti.mm.pictograms.ChopboxAnchor
	 * @generated
	 */
	public Adapter createChopboxAnchorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.graphiti.mm.pictograms.ConnectionDecorator <em>Connection Decorator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.graphiti.mm.pictograms.ConnectionDecorator
	 * @generated
	 */
	public Adapter createConnectionDecoratorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.graphiti.mm.pictograms.FreeFormConnection <em>Free Form Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.graphiti.mm.pictograms.FreeFormConnection
	 * @generated
	 */
	public Adapter createFreeFormConnectionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.graphiti.mm.pictograms.ManhattanConnection <em>Manhattan Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.graphiti.mm.pictograms.ManhattanConnection
	 * @generated
	 */
	public Adapter createManhattanConnectionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.graphiti.mm.pictograms.PictogramLink <em>Pictogram Link</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.graphiti.mm.pictograms.PictogramLink
	 * @generated
	 */
	public Adapter createPictogramLinkAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.graphiti.mm.PropertyContainer <em>Property Container</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.graphiti.mm.PropertyContainer
	 * @generated
	 */
	public Adapter createPropertyContainerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.graphiti.mm.GraphicsAlgorithmContainer <em>Graphics Algorithm Container</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.graphiti.mm.GraphicsAlgorithmContainer
	 * @generated
	 */
	public Adapter createGraphicsAlgorithmContainerAdapter() {
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

} //PictogramsAdapterFactory
