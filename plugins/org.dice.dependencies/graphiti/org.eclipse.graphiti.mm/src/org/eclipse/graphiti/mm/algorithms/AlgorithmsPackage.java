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
package org.eclipse.graphiti.mm.algorithms;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.graphiti.mm.MmPackage;

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
 * @see org.eclipse.graphiti.mm.algorithms.AlgorithmsFactory
 * @model kind="package"
 * @generated
 */
public interface AlgorithmsPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "algorithms";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://eclipse.org/graphiti/mm/algorithms";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "al";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	AlgorithmsPackage eINSTANCE = org.eclipse.graphiti.mm.algorithms.impl.AlgorithmsPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.mm.algorithms.impl.GraphicsAlgorithmImpl <em>Graphics Algorithm</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.mm.algorithms.impl.GraphicsAlgorithmImpl
	 * @see org.eclipse.graphiti.mm.algorithms.impl.AlgorithmsPackageImpl#getGraphicsAlgorithm()
	 * @generated
	 */
	int GRAPHICS_ALGORITHM = 0;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHICS_ALGORITHM__PROPERTIES = MmPackage.GRAPHICS_ALGORITHM_CONTAINER__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Background</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHICS_ALGORITHM__BACKGROUND = MmPackage.GRAPHICS_ALGORITHM_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Foreground</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHICS_ALGORITHM__FOREGROUND = MmPackage.GRAPHICS_ALGORITHM_CONTAINER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Line Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHICS_ALGORITHM__LINE_WIDTH = MmPackage.GRAPHICS_ALGORITHM_CONTAINER_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Line Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHICS_ALGORITHM__LINE_STYLE = MmPackage.GRAPHICS_ALGORITHM_CONTAINER_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Filled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHICS_ALGORITHM__FILLED = MmPackage.GRAPHICS_ALGORITHM_CONTAINER_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Line Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHICS_ALGORITHM__LINE_VISIBLE = MmPackage.GRAPHICS_ALGORITHM_CONTAINER_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Rendering Style</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHICS_ALGORITHM__RENDERING_STYLE = MmPackage.GRAPHICS_ALGORITHM_CONTAINER_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Transparency</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHICS_ALGORITHM__TRANSPARENCY = MmPackage.GRAPHICS_ALGORITHM_CONTAINER_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Graphics Algorithm Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHICS_ALGORITHM__GRAPHICS_ALGORITHM_CHILDREN = MmPackage.GRAPHICS_ALGORITHM_CONTAINER_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Parent Graphics Algorithm</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHICS_ALGORITHM__PARENT_GRAPHICS_ALGORITHM = MmPackage.GRAPHICS_ALGORITHM_CONTAINER_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Pictogram Element</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHICS_ALGORITHM__PICTOGRAM_ELEMENT = MmPackage.GRAPHICS_ALGORITHM_CONTAINER_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHICS_ALGORITHM__WIDTH = MmPackage.GRAPHICS_ALGORITHM_CONTAINER_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHICS_ALGORITHM__HEIGHT = MmPackage.GRAPHICS_ALGORITHM_CONTAINER_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHICS_ALGORITHM__X = MmPackage.GRAPHICS_ALGORITHM_CONTAINER_FEATURE_COUNT + 13;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHICS_ALGORITHM__Y = MmPackage.GRAPHICS_ALGORITHM_CONTAINER_FEATURE_COUNT + 14;

	/**
	 * The feature id for the '<em><b>Style</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHICS_ALGORITHM__STYLE = MmPackage.GRAPHICS_ALGORITHM_CONTAINER_FEATURE_COUNT + 15;

	/**
	 * The number of structural features of the '<em>Graphics Algorithm</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHICS_ALGORITHM_FEATURE_COUNT = MmPackage.GRAPHICS_ALGORITHM_CONTAINER_FEATURE_COUNT + 16;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.mm.algorithms.impl.PolylineImpl <em>Polyline</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.mm.algorithms.impl.PolylineImpl
	 * @see org.eclipse.graphiti.mm.algorithms.impl.AlgorithmsPackageImpl#getPolyline()
	 * @generated
	 */
	int POLYLINE = 1;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYLINE__PROPERTIES = GRAPHICS_ALGORITHM__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Background</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYLINE__BACKGROUND = GRAPHICS_ALGORITHM__BACKGROUND;

	/**
	 * The feature id for the '<em><b>Foreground</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYLINE__FOREGROUND = GRAPHICS_ALGORITHM__FOREGROUND;

	/**
	 * The feature id for the '<em><b>Line Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYLINE__LINE_WIDTH = GRAPHICS_ALGORITHM__LINE_WIDTH;

	/**
	 * The feature id for the '<em><b>Line Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYLINE__LINE_STYLE = GRAPHICS_ALGORITHM__LINE_STYLE;

	/**
	 * The feature id for the '<em><b>Filled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYLINE__FILLED = GRAPHICS_ALGORITHM__FILLED;

	/**
	 * The feature id for the '<em><b>Line Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYLINE__LINE_VISIBLE = GRAPHICS_ALGORITHM__LINE_VISIBLE;

	/**
	 * The feature id for the '<em><b>Rendering Style</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYLINE__RENDERING_STYLE = GRAPHICS_ALGORITHM__RENDERING_STYLE;

	/**
	 * The feature id for the '<em><b>Transparency</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYLINE__TRANSPARENCY = GRAPHICS_ALGORITHM__TRANSPARENCY;

	/**
	 * The feature id for the '<em><b>Graphics Algorithm Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYLINE__GRAPHICS_ALGORITHM_CHILDREN = GRAPHICS_ALGORITHM__GRAPHICS_ALGORITHM_CHILDREN;

	/**
	 * The feature id for the '<em><b>Parent Graphics Algorithm</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYLINE__PARENT_GRAPHICS_ALGORITHM = GRAPHICS_ALGORITHM__PARENT_GRAPHICS_ALGORITHM;

	/**
	 * The feature id for the '<em><b>Pictogram Element</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYLINE__PICTOGRAM_ELEMENT = GRAPHICS_ALGORITHM__PICTOGRAM_ELEMENT;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYLINE__WIDTH = GRAPHICS_ALGORITHM__WIDTH;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYLINE__HEIGHT = GRAPHICS_ALGORITHM__HEIGHT;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYLINE__X = GRAPHICS_ALGORITHM__X;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYLINE__Y = GRAPHICS_ALGORITHM__Y;

	/**
	 * The feature id for the '<em><b>Style</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYLINE__STYLE = GRAPHICS_ALGORITHM__STYLE;

	/**
	 * The feature id for the '<em><b>Points</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYLINE__POINTS = GRAPHICS_ALGORITHM_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Polyline</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYLINE_FEATURE_COUNT = GRAPHICS_ALGORITHM_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.mm.algorithms.impl.EllipseImpl <em>Ellipse</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.mm.algorithms.impl.EllipseImpl
	 * @see org.eclipse.graphiti.mm.algorithms.impl.AlgorithmsPackageImpl#getEllipse()
	 * @generated
	 */
	int ELLIPSE = 2;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELLIPSE__PROPERTIES = GRAPHICS_ALGORITHM__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Background</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELLIPSE__BACKGROUND = GRAPHICS_ALGORITHM__BACKGROUND;

	/**
	 * The feature id for the '<em><b>Foreground</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELLIPSE__FOREGROUND = GRAPHICS_ALGORITHM__FOREGROUND;

	/**
	 * The feature id for the '<em><b>Line Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELLIPSE__LINE_WIDTH = GRAPHICS_ALGORITHM__LINE_WIDTH;

	/**
	 * The feature id for the '<em><b>Line Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELLIPSE__LINE_STYLE = GRAPHICS_ALGORITHM__LINE_STYLE;

	/**
	 * The feature id for the '<em><b>Filled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELLIPSE__FILLED = GRAPHICS_ALGORITHM__FILLED;

	/**
	 * The feature id for the '<em><b>Line Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELLIPSE__LINE_VISIBLE = GRAPHICS_ALGORITHM__LINE_VISIBLE;

	/**
	 * The feature id for the '<em><b>Rendering Style</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELLIPSE__RENDERING_STYLE = GRAPHICS_ALGORITHM__RENDERING_STYLE;

	/**
	 * The feature id for the '<em><b>Transparency</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELLIPSE__TRANSPARENCY = GRAPHICS_ALGORITHM__TRANSPARENCY;

	/**
	 * The feature id for the '<em><b>Graphics Algorithm Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELLIPSE__GRAPHICS_ALGORITHM_CHILDREN = GRAPHICS_ALGORITHM__GRAPHICS_ALGORITHM_CHILDREN;

	/**
	 * The feature id for the '<em><b>Parent Graphics Algorithm</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELLIPSE__PARENT_GRAPHICS_ALGORITHM = GRAPHICS_ALGORITHM__PARENT_GRAPHICS_ALGORITHM;

	/**
	 * The feature id for the '<em><b>Pictogram Element</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELLIPSE__PICTOGRAM_ELEMENT = GRAPHICS_ALGORITHM__PICTOGRAM_ELEMENT;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELLIPSE__WIDTH = GRAPHICS_ALGORITHM__WIDTH;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELLIPSE__HEIGHT = GRAPHICS_ALGORITHM__HEIGHT;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELLIPSE__X = GRAPHICS_ALGORITHM__X;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELLIPSE__Y = GRAPHICS_ALGORITHM__Y;

	/**
	 * The feature id for the '<em><b>Style</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELLIPSE__STYLE = GRAPHICS_ALGORITHM__STYLE;

	/**
	 * The number of structural features of the '<em>Ellipse</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELLIPSE_FEATURE_COUNT = GRAPHICS_ALGORITHM_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.mm.algorithms.impl.AbstractTextImpl <em>Abstract Text</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.mm.algorithms.impl.AbstractTextImpl
	 * @see org.eclipse.graphiti.mm.algorithms.impl.AlgorithmsPackageImpl#getAbstractText()
	 * @generated
	 */
	int ABSTRACT_TEXT = 9;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TEXT__PROPERTIES = GRAPHICS_ALGORITHM__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Background</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TEXT__BACKGROUND = GRAPHICS_ALGORITHM__BACKGROUND;

	/**
	 * The feature id for the '<em><b>Foreground</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TEXT__FOREGROUND = GRAPHICS_ALGORITHM__FOREGROUND;

	/**
	 * The feature id for the '<em><b>Line Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TEXT__LINE_WIDTH = GRAPHICS_ALGORITHM__LINE_WIDTH;

	/**
	 * The feature id for the '<em><b>Line Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TEXT__LINE_STYLE = GRAPHICS_ALGORITHM__LINE_STYLE;

	/**
	 * The feature id for the '<em><b>Filled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TEXT__FILLED = GRAPHICS_ALGORITHM__FILLED;

	/**
	 * The feature id for the '<em><b>Line Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TEXT__LINE_VISIBLE = GRAPHICS_ALGORITHM__LINE_VISIBLE;

	/**
	 * The feature id for the '<em><b>Rendering Style</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TEXT__RENDERING_STYLE = GRAPHICS_ALGORITHM__RENDERING_STYLE;

	/**
	 * The feature id for the '<em><b>Transparency</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TEXT__TRANSPARENCY = GRAPHICS_ALGORITHM__TRANSPARENCY;

	/**
	 * The feature id for the '<em><b>Graphics Algorithm Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TEXT__GRAPHICS_ALGORITHM_CHILDREN = GRAPHICS_ALGORITHM__GRAPHICS_ALGORITHM_CHILDREN;

	/**
	 * The feature id for the '<em><b>Parent Graphics Algorithm</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TEXT__PARENT_GRAPHICS_ALGORITHM = GRAPHICS_ALGORITHM__PARENT_GRAPHICS_ALGORITHM;

	/**
	 * The feature id for the '<em><b>Pictogram Element</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TEXT__PICTOGRAM_ELEMENT = GRAPHICS_ALGORITHM__PICTOGRAM_ELEMENT;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TEXT__WIDTH = GRAPHICS_ALGORITHM__WIDTH;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TEXT__HEIGHT = GRAPHICS_ALGORITHM__HEIGHT;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TEXT__X = GRAPHICS_ALGORITHM__X;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TEXT__Y = GRAPHICS_ALGORITHM__Y;

	/**
	 * The feature id for the '<em><b>Style</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TEXT__STYLE = GRAPHICS_ALGORITHM__STYLE;

	/**
	 * The feature id for the '<em><b>Font</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TEXT__FONT = GRAPHICS_ALGORITHM_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Horizontal Alignment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TEXT__HORIZONTAL_ALIGNMENT = GRAPHICS_ALGORITHM_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Vertical Alignment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TEXT__VERTICAL_ALIGNMENT = GRAPHICS_ALGORITHM_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Angle</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TEXT__ANGLE = GRAPHICS_ALGORITHM_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TEXT__VALUE = GRAPHICS_ALGORITHM_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Abstract Text</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TEXT_FEATURE_COUNT = GRAPHICS_ALGORITHM_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.mm.algorithms.impl.TextImpl <em>Text</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.mm.algorithms.impl.TextImpl
	 * @see org.eclipse.graphiti.mm.algorithms.impl.AlgorithmsPackageImpl#getText()
	 * @generated
	 */
	int TEXT = 3;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__PROPERTIES = ABSTRACT_TEXT__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Background</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__BACKGROUND = ABSTRACT_TEXT__BACKGROUND;

	/**
	 * The feature id for the '<em><b>Foreground</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__FOREGROUND = ABSTRACT_TEXT__FOREGROUND;

	/**
	 * The feature id for the '<em><b>Line Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__LINE_WIDTH = ABSTRACT_TEXT__LINE_WIDTH;

	/**
	 * The feature id for the '<em><b>Line Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__LINE_STYLE = ABSTRACT_TEXT__LINE_STYLE;

	/**
	 * The feature id for the '<em><b>Filled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__FILLED = ABSTRACT_TEXT__FILLED;

	/**
	 * The feature id for the '<em><b>Line Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__LINE_VISIBLE = ABSTRACT_TEXT__LINE_VISIBLE;

	/**
	 * The feature id for the '<em><b>Rendering Style</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__RENDERING_STYLE = ABSTRACT_TEXT__RENDERING_STYLE;

	/**
	 * The feature id for the '<em><b>Transparency</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__TRANSPARENCY = ABSTRACT_TEXT__TRANSPARENCY;

	/**
	 * The feature id for the '<em><b>Graphics Algorithm Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__GRAPHICS_ALGORITHM_CHILDREN = ABSTRACT_TEXT__GRAPHICS_ALGORITHM_CHILDREN;

	/**
	 * The feature id for the '<em><b>Parent Graphics Algorithm</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__PARENT_GRAPHICS_ALGORITHM = ABSTRACT_TEXT__PARENT_GRAPHICS_ALGORITHM;

	/**
	 * The feature id for the '<em><b>Pictogram Element</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__PICTOGRAM_ELEMENT = ABSTRACT_TEXT__PICTOGRAM_ELEMENT;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__WIDTH = ABSTRACT_TEXT__WIDTH;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__HEIGHT = ABSTRACT_TEXT__HEIGHT;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__X = ABSTRACT_TEXT__X;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__Y = ABSTRACT_TEXT__Y;

	/**
	 * The feature id for the '<em><b>Style</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__STYLE = ABSTRACT_TEXT__STYLE;

	/**
	 * The feature id for the '<em><b>Font</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__FONT = ABSTRACT_TEXT__FONT;

	/**
	 * The feature id for the '<em><b>Horizontal Alignment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__HORIZONTAL_ALIGNMENT = ABSTRACT_TEXT__HORIZONTAL_ALIGNMENT;

	/**
	 * The feature id for the '<em><b>Vertical Alignment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__VERTICAL_ALIGNMENT = ABSTRACT_TEXT__VERTICAL_ALIGNMENT;

	/**
	 * The feature id for the '<em><b>Angle</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__ANGLE = ABSTRACT_TEXT__ANGLE;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__VALUE = ABSTRACT_TEXT__VALUE;

	/**
	 * The number of structural features of the '<em>Text</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_FEATURE_COUNT = ABSTRACT_TEXT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.mm.algorithms.impl.PolygonImpl <em>Polygon</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.mm.algorithms.impl.PolygonImpl
	 * @see org.eclipse.graphiti.mm.algorithms.impl.AlgorithmsPackageImpl#getPolygon()
	 * @generated
	 */
	int POLYGON = 4;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYGON__PROPERTIES = POLYLINE__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Background</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYGON__BACKGROUND = POLYLINE__BACKGROUND;

	/**
	 * The feature id for the '<em><b>Foreground</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYGON__FOREGROUND = POLYLINE__FOREGROUND;

	/**
	 * The feature id for the '<em><b>Line Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYGON__LINE_WIDTH = POLYLINE__LINE_WIDTH;

	/**
	 * The feature id for the '<em><b>Line Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYGON__LINE_STYLE = POLYLINE__LINE_STYLE;

	/**
	 * The feature id for the '<em><b>Filled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYGON__FILLED = POLYLINE__FILLED;

	/**
	 * The feature id for the '<em><b>Line Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYGON__LINE_VISIBLE = POLYLINE__LINE_VISIBLE;

	/**
	 * The feature id for the '<em><b>Rendering Style</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYGON__RENDERING_STYLE = POLYLINE__RENDERING_STYLE;

	/**
	 * The feature id for the '<em><b>Transparency</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYGON__TRANSPARENCY = POLYLINE__TRANSPARENCY;

	/**
	 * The feature id for the '<em><b>Graphics Algorithm Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYGON__GRAPHICS_ALGORITHM_CHILDREN = POLYLINE__GRAPHICS_ALGORITHM_CHILDREN;

	/**
	 * The feature id for the '<em><b>Parent Graphics Algorithm</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYGON__PARENT_GRAPHICS_ALGORITHM = POLYLINE__PARENT_GRAPHICS_ALGORITHM;

	/**
	 * The feature id for the '<em><b>Pictogram Element</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYGON__PICTOGRAM_ELEMENT = POLYLINE__PICTOGRAM_ELEMENT;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYGON__WIDTH = POLYLINE__WIDTH;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYGON__HEIGHT = POLYLINE__HEIGHT;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYGON__X = POLYLINE__X;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYGON__Y = POLYLINE__Y;

	/**
	 * The feature id for the '<em><b>Style</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYGON__STYLE = POLYLINE__STYLE;

	/**
	 * The feature id for the '<em><b>Points</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYGON__POINTS = POLYLINE__POINTS;

	/**
	 * The number of structural features of the '<em>Polygon</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYGON_FEATURE_COUNT = POLYLINE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.mm.algorithms.impl.RectangleImpl <em>Rectangle</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.mm.algorithms.impl.RectangleImpl
	 * @see org.eclipse.graphiti.mm.algorithms.impl.AlgorithmsPackageImpl#getRectangle()
	 * @generated
	 */
	int RECTANGLE = 5;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECTANGLE__PROPERTIES = GRAPHICS_ALGORITHM__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Background</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECTANGLE__BACKGROUND = GRAPHICS_ALGORITHM__BACKGROUND;

	/**
	 * The feature id for the '<em><b>Foreground</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECTANGLE__FOREGROUND = GRAPHICS_ALGORITHM__FOREGROUND;

	/**
	 * The feature id for the '<em><b>Line Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECTANGLE__LINE_WIDTH = GRAPHICS_ALGORITHM__LINE_WIDTH;

	/**
	 * The feature id for the '<em><b>Line Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECTANGLE__LINE_STYLE = GRAPHICS_ALGORITHM__LINE_STYLE;

	/**
	 * The feature id for the '<em><b>Filled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECTANGLE__FILLED = GRAPHICS_ALGORITHM__FILLED;

	/**
	 * The feature id for the '<em><b>Line Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECTANGLE__LINE_VISIBLE = GRAPHICS_ALGORITHM__LINE_VISIBLE;

	/**
	 * The feature id for the '<em><b>Rendering Style</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECTANGLE__RENDERING_STYLE = GRAPHICS_ALGORITHM__RENDERING_STYLE;

	/**
	 * The feature id for the '<em><b>Transparency</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECTANGLE__TRANSPARENCY = GRAPHICS_ALGORITHM__TRANSPARENCY;

	/**
	 * The feature id for the '<em><b>Graphics Algorithm Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECTANGLE__GRAPHICS_ALGORITHM_CHILDREN = GRAPHICS_ALGORITHM__GRAPHICS_ALGORITHM_CHILDREN;

	/**
	 * The feature id for the '<em><b>Parent Graphics Algorithm</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECTANGLE__PARENT_GRAPHICS_ALGORITHM = GRAPHICS_ALGORITHM__PARENT_GRAPHICS_ALGORITHM;

	/**
	 * The feature id for the '<em><b>Pictogram Element</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECTANGLE__PICTOGRAM_ELEMENT = GRAPHICS_ALGORITHM__PICTOGRAM_ELEMENT;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECTANGLE__WIDTH = GRAPHICS_ALGORITHM__WIDTH;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECTANGLE__HEIGHT = GRAPHICS_ALGORITHM__HEIGHT;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECTANGLE__X = GRAPHICS_ALGORITHM__X;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECTANGLE__Y = GRAPHICS_ALGORITHM__Y;

	/**
	 * The feature id for the '<em><b>Style</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECTANGLE__STYLE = GRAPHICS_ALGORITHM__STYLE;

	/**
	 * The number of structural features of the '<em>Rectangle</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECTANGLE_FEATURE_COUNT = GRAPHICS_ALGORITHM_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.mm.algorithms.impl.RoundedRectangleImpl <em>Rounded Rectangle</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.mm.algorithms.impl.RoundedRectangleImpl
	 * @see org.eclipse.graphiti.mm.algorithms.impl.AlgorithmsPackageImpl#getRoundedRectangle()
	 * @generated
	 */
	int ROUNDED_RECTANGLE = 6;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUNDED_RECTANGLE__PROPERTIES = GRAPHICS_ALGORITHM__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Background</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUNDED_RECTANGLE__BACKGROUND = GRAPHICS_ALGORITHM__BACKGROUND;

	/**
	 * The feature id for the '<em><b>Foreground</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUNDED_RECTANGLE__FOREGROUND = GRAPHICS_ALGORITHM__FOREGROUND;

	/**
	 * The feature id for the '<em><b>Line Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUNDED_RECTANGLE__LINE_WIDTH = GRAPHICS_ALGORITHM__LINE_WIDTH;

	/**
	 * The feature id for the '<em><b>Line Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUNDED_RECTANGLE__LINE_STYLE = GRAPHICS_ALGORITHM__LINE_STYLE;

	/**
	 * The feature id for the '<em><b>Filled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUNDED_RECTANGLE__FILLED = GRAPHICS_ALGORITHM__FILLED;

	/**
	 * The feature id for the '<em><b>Line Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUNDED_RECTANGLE__LINE_VISIBLE = GRAPHICS_ALGORITHM__LINE_VISIBLE;

	/**
	 * The feature id for the '<em><b>Rendering Style</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUNDED_RECTANGLE__RENDERING_STYLE = GRAPHICS_ALGORITHM__RENDERING_STYLE;

	/**
	 * The feature id for the '<em><b>Transparency</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUNDED_RECTANGLE__TRANSPARENCY = GRAPHICS_ALGORITHM__TRANSPARENCY;

	/**
	 * The feature id for the '<em><b>Graphics Algorithm Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUNDED_RECTANGLE__GRAPHICS_ALGORITHM_CHILDREN = GRAPHICS_ALGORITHM__GRAPHICS_ALGORITHM_CHILDREN;

	/**
	 * The feature id for the '<em><b>Parent Graphics Algorithm</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUNDED_RECTANGLE__PARENT_GRAPHICS_ALGORITHM = GRAPHICS_ALGORITHM__PARENT_GRAPHICS_ALGORITHM;

	/**
	 * The feature id for the '<em><b>Pictogram Element</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUNDED_RECTANGLE__PICTOGRAM_ELEMENT = GRAPHICS_ALGORITHM__PICTOGRAM_ELEMENT;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUNDED_RECTANGLE__WIDTH = GRAPHICS_ALGORITHM__WIDTH;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUNDED_RECTANGLE__HEIGHT = GRAPHICS_ALGORITHM__HEIGHT;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUNDED_RECTANGLE__X = GRAPHICS_ALGORITHM__X;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUNDED_RECTANGLE__Y = GRAPHICS_ALGORITHM__Y;

	/**
	 * The feature id for the '<em><b>Style</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUNDED_RECTANGLE__STYLE = GRAPHICS_ALGORITHM__STYLE;

	/**
	 * The feature id for the '<em><b>Corner Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUNDED_RECTANGLE__CORNER_HEIGHT = GRAPHICS_ALGORITHM_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Corner Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUNDED_RECTANGLE__CORNER_WIDTH = GRAPHICS_ALGORITHM_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Rounded Rectangle</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUNDED_RECTANGLE_FEATURE_COUNT = GRAPHICS_ALGORITHM_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.mm.algorithms.impl.ImageImpl <em>Image</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.mm.algorithms.impl.ImageImpl
	 * @see org.eclipse.graphiti.mm.algorithms.impl.AlgorithmsPackageImpl#getImage()
	 * @generated
	 */
	int IMAGE = 7;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__PROPERTIES = GRAPHICS_ALGORITHM__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Background</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__BACKGROUND = GRAPHICS_ALGORITHM__BACKGROUND;

	/**
	 * The feature id for the '<em><b>Foreground</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__FOREGROUND = GRAPHICS_ALGORITHM__FOREGROUND;

	/**
	 * The feature id for the '<em><b>Line Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__LINE_WIDTH = GRAPHICS_ALGORITHM__LINE_WIDTH;

	/**
	 * The feature id for the '<em><b>Line Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__LINE_STYLE = GRAPHICS_ALGORITHM__LINE_STYLE;

	/**
	 * The feature id for the '<em><b>Filled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__FILLED = GRAPHICS_ALGORITHM__FILLED;

	/**
	 * The feature id for the '<em><b>Line Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__LINE_VISIBLE = GRAPHICS_ALGORITHM__LINE_VISIBLE;

	/**
	 * The feature id for the '<em><b>Rendering Style</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__RENDERING_STYLE = GRAPHICS_ALGORITHM__RENDERING_STYLE;

	/**
	 * The feature id for the '<em><b>Transparency</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__TRANSPARENCY = GRAPHICS_ALGORITHM__TRANSPARENCY;

	/**
	 * The feature id for the '<em><b>Graphics Algorithm Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__GRAPHICS_ALGORITHM_CHILDREN = GRAPHICS_ALGORITHM__GRAPHICS_ALGORITHM_CHILDREN;

	/**
	 * The feature id for the '<em><b>Parent Graphics Algorithm</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__PARENT_GRAPHICS_ALGORITHM = GRAPHICS_ALGORITHM__PARENT_GRAPHICS_ALGORITHM;

	/**
	 * The feature id for the '<em><b>Pictogram Element</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__PICTOGRAM_ELEMENT = GRAPHICS_ALGORITHM__PICTOGRAM_ELEMENT;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__WIDTH = GRAPHICS_ALGORITHM__WIDTH;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__HEIGHT = GRAPHICS_ALGORITHM__HEIGHT;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__X = GRAPHICS_ALGORITHM__X;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__Y = GRAPHICS_ALGORITHM__Y;

	/**
	 * The feature id for the '<em><b>Style</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__STYLE = GRAPHICS_ALGORITHM__STYLE;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__ID = GRAPHICS_ALGORITHM_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Stretch H</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__STRETCH_H = GRAPHICS_ALGORITHM_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Stretch V</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__STRETCH_V = GRAPHICS_ALGORITHM_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Proportional</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__PROPORTIONAL = GRAPHICS_ALGORITHM_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Image</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE_FEATURE_COUNT = GRAPHICS_ALGORITHM_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.mm.algorithms.impl.PlatformGraphicsAlgorithmImpl <em>Platform Graphics Algorithm</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.mm.algorithms.impl.PlatformGraphicsAlgorithmImpl
	 * @see org.eclipse.graphiti.mm.algorithms.impl.AlgorithmsPackageImpl#getPlatformGraphicsAlgorithm()
	 * @generated
	 */
	int PLATFORM_GRAPHICS_ALGORITHM = 8;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_GRAPHICS_ALGORITHM__PROPERTIES = GRAPHICS_ALGORITHM__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Background</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_GRAPHICS_ALGORITHM__BACKGROUND = GRAPHICS_ALGORITHM__BACKGROUND;

	/**
	 * The feature id for the '<em><b>Foreground</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_GRAPHICS_ALGORITHM__FOREGROUND = GRAPHICS_ALGORITHM__FOREGROUND;

	/**
	 * The feature id for the '<em><b>Line Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_GRAPHICS_ALGORITHM__LINE_WIDTH = GRAPHICS_ALGORITHM__LINE_WIDTH;

	/**
	 * The feature id for the '<em><b>Line Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_GRAPHICS_ALGORITHM__LINE_STYLE = GRAPHICS_ALGORITHM__LINE_STYLE;

	/**
	 * The feature id for the '<em><b>Filled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_GRAPHICS_ALGORITHM__FILLED = GRAPHICS_ALGORITHM__FILLED;

	/**
	 * The feature id for the '<em><b>Line Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_GRAPHICS_ALGORITHM__LINE_VISIBLE = GRAPHICS_ALGORITHM__LINE_VISIBLE;

	/**
	 * The feature id for the '<em><b>Rendering Style</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_GRAPHICS_ALGORITHM__RENDERING_STYLE = GRAPHICS_ALGORITHM__RENDERING_STYLE;

	/**
	 * The feature id for the '<em><b>Transparency</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_GRAPHICS_ALGORITHM__TRANSPARENCY = GRAPHICS_ALGORITHM__TRANSPARENCY;

	/**
	 * The feature id for the '<em><b>Graphics Algorithm Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_GRAPHICS_ALGORITHM__GRAPHICS_ALGORITHM_CHILDREN = GRAPHICS_ALGORITHM__GRAPHICS_ALGORITHM_CHILDREN;

	/**
	 * The feature id for the '<em><b>Parent Graphics Algorithm</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_GRAPHICS_ALGORITHM__PARENT_GRAPHICS_ALGORITHM = GRAPHICS_ALGORITHM__PARENT_GRAPHICS_ALGORITHM;

	/**
	 * The feature id for the '<em><b>Pictogram Element</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_GRAPHICS_ALGORITHM__PICTOGRAM_ELEMENT = GRAPHICS_ALGORITHM__PICTOGRAM_ELEMENT;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_GRAPHICS_ALGORITHM__WIDTH = GRAPHICS_ALGORITHM__WIDTH;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_GRAPHICS_ALGORITHM__HEIGHT = GRAPHICS_ALGORITHM__HEIGHT;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_GRAPHICS_ALGORITHM__X = GRAPHICS_ALGORITHM__X;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_GRAPHICS_ALGORITHM__Y = GRAPHICS_ALGORITHM__Y;

	/**
	 * The feature id for the '<em><b>Style</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_GRAPHICS_ALGORITHM__STYLE = GRAPHICS_ALGORITHM__STYLE;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_GRAPHICS_ALGORITHM__ID = GRAPHICS_ALGORITHM_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Platform Graphics Algorithm</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_GRAPHICS_ALGORITHM_FEATURE_COUNT = GRAPHICS_ALGORITHM_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.mm.algorithms.impl.MultiTextImpl <em>Multi Text</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.mm.algorithms.impl.MultiTextImpl
	 * @see org.eclipse.graphiti.mm.algorithms.impl.AlgorithmsPackageImpl#getMultiText()
	 * @generated
	 */
	int MULTI_TEXT = 10;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_TEXT__PROPERTIES = ABSTRACT_TEXT__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Background</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_TEXT__BACKGROUND = ABSTRACT_TEXT__BACKGROUND;

	/**
	 * The feature id for the '<em><b>Foreground</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_TEXT__FOREGROUND = ABSTRACT_TEXT__FOREGROUND;

	/**
	 * The feature id for the '<em><b>Line Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_TEXT__LINE_WIDTH = ABSTRACT_TEXT__LINE_WIDTH;

	/**
	 * The feature id for the '<em><b>Line Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_TEXT__LINE_STYLE = ABSTRACT_TEXT__LINE_STYLE;

	/**
	 * The feature id for the '<em><b>Filled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_TEXT__FILLED = ABSTRACT_TEXT__FILLED;

	/**
	 * The feature id for the '<em><b>Line Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_TEXT__LINE_VISIBLE = ABSTRACT_TEXT__LINE_VISIBLE;

	/**
	 * The feature id for the '<em><b>Rendering Style</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_TEXT__RENDERING_STYLE = ABSTRACT_TEXT__RENDERING_STYLE;

	/**
	 * The feature id for the '<em><b>Transparency</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_TEXT__TRANSPARENCY = ABSTRACT_TEXT__TRANSPARENCY;

	/**
	 * The feature id for the '<em><b>Graphics Algorithm Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_TEXT__GRAPHICS_ALGORITHM_CHILDREN = ABSTRACT_TEXT__GRAPHICS_ALGORITHM_CHILDREN;

	/**
	 * The feature id for the '<em><b>Parent Graphics Algorithm</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_TEXT__PARENT_GRAPHICS_ALGORITHM = ABSTRACT_TEXT__PARENT_GRAPHICS_ALGORITHM;

	/**
	 * The feature id for the '<em><b>Pictogram Element</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_TEXT__PICTOGRAM_ELEMENT = ABSTRACT_TEXT__PICTOGRAM_ELEMENT;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_TEXT__WIDTH = ABSTRACT_TEXT__WIDTH;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_TEXT__HEIGHT = ABSTRACT_TEXT__HEIGHT;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_TEXT__X = ABSTRACT_TEXT__X;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_TEXT__Y = ABSTRACT_TEXT__Y;

	/**
	 * The feature id for the '<em><b>Style</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_TEXT__STYLE = ABSTRACT_TEXT__STYLE;

	/**
	 * The feature id for the '<em><b>Font</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_TEXT__FONT = ABSTRACT_TEXT__FONT;

	/**
	 * The feature id for the '<em><b>Horizontal Alignment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_TEXT__HORIZONTAL_ALIGNMENT = ABSTRACT_TEXT__HORIZONTAL_ALIGNMENT;

	/**
	 * The feature id for the '<em><b>Vertical Alignment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_TEXT__VERTICAL_ALIGNMENT = ABSTRACT_TEXT__VERTICAL_ALIGNMENT;

	/**
	 * The feature id for the '<em><b>Angle</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_TEXT__ANGLE = ABSTRACT_TEXT__ANGLE;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_TEXT__VALUE = ABSTRACT_TEXT__VALUE;

	/**
	 * The number of structural features of the '<em>Multi Text</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_TEXT_FEATURE_COUNT = ABSTRACT_TEXT_FEATURE_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm <em>Graphics Algorithm</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Graphics Algorithm</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm
	 * @generated
	 */
	EClass getGraphicsAlgorithm();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm#getGraphicsAlgorithmChildren <em>Graphics Algorithm Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Graphics Algorithm Children</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm#getGraphicsAlgorithmChildren()
	 * @see #getGraphicsAlgorithm()
	 * @generated
	 */
	EReference getGraphicsAlgorithm_GraphicsAlgorithmChildren();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm#getParentGraphicsAlgorithm <em>Parent Graphics Algorithm</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Parent Graphics Algorithm</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm#getParentGraphicsAlgorithm()
	 * @see #getGraphicsAlgorithm()
	 * @generated
	 */
	EReference getGraphicsAlgorithm_ParentGraphicsAlgorithm();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm#getPictogramElement <em>Pictogram Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Pictogram Element</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm#getPictogramElement()
	 * @see #getGraphicsAlgorithm()
	 * @generated
	 */
	EReference getGraphicsAlgorithm_PictogramElement();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm#getWidth <em>Width</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Width</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm#getWidth()
	 * @see #getGraphicsAlgorithm()
	 * @generated
	 */
	EAttribute getGraphicsAlgorithm_Width();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm#getHeight <em>Height</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Height</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm#getHeight()
	 * @see #getGraphicsAlgorithm()
	 * @generated
	 */
	EAttribute getGraphicsAlgorithm_Height();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm#getX <em>X</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>X</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm#getX()
	 * @see #getGraphicsAlgorithm()
	 * @generated
	 */
	EAttribute getGraphicsAlgorithm_X();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm#getY <em>Y</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Y</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm#getY()
	 * @see #getGraphicsAlgorithm()
	 * @generated
	 */
	EAttribute getGraphicsAlgorithm_Y();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm#getStyle <em>Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Style</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm#getStyle()
	 * @see #getGraphicsAlgorithm()
	 * @generated
	 */
	EReference getGraphicsAlgorithm_Style();

	/**
	 * Returns the meta object for class '{@link org.eclipse.graphiti.mm.algorithms.Polyline <em>Polyline</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Polyline</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.Polyline
	 * @generated
	 */
	EClass getPolyline();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.graphiti.mm.algorithms.Polyline#getPoints <em>Points</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Points</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.Polyline#getPoints()
	 * @see #getPolyline()
	 * @generated
	 */
	EReference getPolyline_Points();

	/**
	 * Returns the meta object for class '{@link org.eclipse.graphiti.mm.algorithms.Ellipse <em>Ellipse</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ellipse</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.Ellipse
	 * @generated
	 */
	EClass getEllipse();

	/**
	 * Returns the meta object for class '{@link org.eclipse.graphiti.mm.algorithms.Text <em>Text</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Text</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.Text
	 * @generated
	 */
	EClass getText();

	/**
	 * Returns the meta object for class '{@link org.eclipse.graphiti.mm.algorithms.Polygon <em>Polygon</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Polygon</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.Polygon
	 * @generated
	 */
	EClass getPolygon();

	/**
	 * Returns the meta object for class '{@link org.eclipse.graphiti.mm.algorithms.Rectangle <em>Rectangle</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Rectangle</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.Rectangle
	 * @generated
	 */
	EClass getRectangle();

	/**
	 * Returns the meta object for class '{@link org.eclipse.graphiti.mm.algorithms.RoundedRectangle <em>Rounded Rectangle</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Rounded Rectangle</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.RoundedRectangle
	 * @generated
	 */
	EClass getRoundedRectangle();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.algorithms.RoundedRectangle#getCornerHeight <em>Corner Height</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Corner Height</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.RoundedRectangle#getCornerHeight()
	 * @see #getRoundedRectangle()
	 * @generated
	 */
	EAttribute getRoundedRectangle_CornerHeight();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.algorithms.RoundedRectangle#getCornerWidth <em>Corner Width</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Corner Width</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.RoundedRectangle#getCornerWidth()
	 * @see #getRoundedRectangle()
	 * @generated
	 */
	EAttribute getRoundedRectangle_CornerWidth();

	/**
	 * Returns the meta object for class '{@link org.eclipse.graphiti.mm.algorithms.Image <em>Image</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Image</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.Image
	 * @generated
	 */
	EClass getImage();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.algorithms.Image#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.Image#getId()
	 * @see #getImage()
	 * @generated
	 */
	EAttribute getImage_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.algorithms.Image#getStretchH <em>Stretch H</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Stretch H</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.Image#getStretchH()
	 * @see #getImage()
	 * @generated
	 */
	EAttribute getImage_StretchH();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.algorithms.Image#getStretchV <em>Stretch V</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Stretch V</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.Image#getStretchV()
	 * @see #getImage()
	 * @generated
	 */
	EAttribute getImage_StretchV();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.algorithms.Image#getProportional <em>Proportional</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Proportional</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.Image#getProportional()
	 * @see #getImage()
	 * @generated
	 */
	EAttribute getImage_Proportional();

	/**
	 * Returns the meta object for class '{@link org.eclipse.graphiti.mm.algorithms.PlatformGraphicsAlgorithm <em>Platform Graphics Algorithm</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Platform Graphics Algorithm</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.PlatformGraphicsAlgorithm
	 * @generated
	 */
	EClass getPlatformGraphicsAlgorithm();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.algorithms.PlatformGraphicsAlgorithm#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.PlatformGraphicsAlgorithm#getId()
	 * @see #getPlatformGraphicsAlgorithm()
	 * @generated
	 */
	EAttribute getPlatformGraphicsAlgorithm_Id();

	/**
	 * Returns the meta object for class '{@link org.eclipse.graphiti.mm.algorithms.AbstractText <em>Abstract Text</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Text</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.AbstractText
	 * @generated
	 */
	EClass getAbstractText();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.graphiti.mm.algorithms.AbstractText#getFont <em>Font</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Font</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.AbstractText#getFont()
	 * @see #getAbstractText()
	 * @generated
	 */
	EReference getAbstractText_Font();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.algorithms.AbstractText#getHorizontalAlignment <em>Horizontal Alignment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Horizontal Alignment</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.AbstractText#getHorizontalAlignment()
	 * @see #getAbstractText()
	 * @generated
	 */
	EAttribute getAbstractText_HorizontalAlignment();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.algorithms.AbstractText#getVerticalAlignment <em>Vertical Alignment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Vertical Alignment</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.AbstractText#getVerticalAlignment()
	 * @see #getAbstractText()
	 * @generated
	 */
	EAttribute getAbstractText_VerticalAlignment();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.algorithms.AbstractText#getAngle <em>Angle</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Angle</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.AbstractText#getAngle()
	 * @see #getAbstractText()
	 * @generated
	 */
	EAttribute getAbstractText_Angle();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.algorithms.AbstractText#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.AbstractText#getValue()
	 * @see #getAbstractText()
	 * @generated
	 */
	EAttribute getAbstractText_Value();

	/**
	 * Returns the meta object for class '{@link org.eclipse.graphiti.mm.algorithms.MultiText <em>Multi Text</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Multi Text</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.MultiText
	 * @generated
	 */
	EClass getMultiText();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	AlgorithmsFactory getAlgorithmsFactory();

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
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.mm.algorithms.impl.GraphicsAlgorithmImpl <em>Graphics Algorithm</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.mm.algorithms.impl.GraphicsAlgorithmImpl
		 * @see org.eclipse.graphiti.mm.algorithms.impl.AlgorithmsPackageImpl#getGraphicsAlgorithm()
		 * @generated
		 */
		EClass GRAPHICS_ALGORITHM = eINSTANCE.getGraphicsAlgorithm();

		/**
		 * The meta object literal for the '<em><b>Graphics Algorithm Children</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GRAPHICS_ALGORITHM__GRAPHICS_ALGORITHM_CHILDREN = eINSTANCE.getGraphicsAlgorithm_GraphicsAlgorithmChildren();

		/**
		 * The meta object literal for the '<em><b>Parent Graphics Algorithm</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GRAPHICS_ALGORITHM__PARENT_GRAPHICS_ALGORITHM = eINSTANCE.getGraphicsAlgorithm_ParentGraphicsAlgorithm();

		/**
		 * The meta object literal for the '<em><b>Pictogram Element</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GRAPHICS_ALGORITHM__PICTOGRAM_ELEMENT = eINSTANCE.getGraphicsAlgorithm_PictogramElement();

		/**
		 * The meta object literal for the '<em><b>Width</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GRAPHICS_ALGORITHM__WIDTH = eINSTANCE.getGraphicsAlgorithm_Width();

		/**
		 * The meta object literal for the '<em><b>Height</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GRAPHICS_ALGORITHM__HEIGHT = eINSTANCE.getGraphicsAlgorithm_Height();

		/**
		 * The meta object literal for the '<em><b>X</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GRAPHICS_ALGORITHM__X = eINSTANCE.getGraphicsAlgorithm_X();

		/**
		 * The meta object literal for the '<em><b>Y</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GRAPHICS_ALGORITHM__Y = eINSTANCE.getGraphicsAlgorithm_Y();

		/**
		 * The meta object literal for the '<em><b>Style</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GRAPHICS_ALGORITHM__STYLE = eINSTANCE.getGraphicsAlgorithm_Style();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.mm.algorithms.impl.PolylineImpl <em>Polyline</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.mm.algorithms.impl.PolylineImpl
		 * @see org.eclipse.graphiti.mm.algorithms.impl.AlgorithmsPackageImpl#getPolyline()
		 * @generated
		 */
		EClass POLYLINE = eINSTANCE.getPolyline();

		/**
		 * The meta object literal for the '<em><b>Points</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference POLYLINE__POINTS = eINSTANCE.getPolyline_Points();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.mm.algorithms.impl.EllipseImpl <em>Ellipse</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.mm.algorithms.impl.EllipseImpl
		 * @see org.eclipse.graphiti.mm.algorithms.impl.AlgorithmsPackageImpl#getEllipse()
		 * @generated
		 */
		EClass ELLIPSE = eINSTANCE.getEllipse();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.mm.algorithms.impl.TextImpl <em>Text</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.mm.algorithms.impl.TextImpl
		 * @see org.eclipse.graphiti.mm.algorithms.impl.AlgorithmsPackageImpl#getText()
		 * @generated
		 */
		EClass TEXT = eINSTANCE.getText();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.mm.algorithms.impl.PolygonImpl <em>Polygon</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.mm.algorithms.impl.PolygonImpl
		 * @see org.eclipse.graphiti.mm.algorithms.impl.AlgorithmsPackageImpl#getPolygon()
		 * @generated
		 */
		EClass POLYGON = eINSTANCE.getPolygon();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.mm.algorithms.impl.RectangleImpl <em>Rectangle</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.mm.algorithms.impl.RectangleImpl
		 * @see org.eclipse.graphiti.mm.algorithms.impl.AlgorithmsPackageImpl#getRectangle()
		 * @generated
		 */
		EClass RECTANGLE = eINSTANCE.getRectangle();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.mm.algorithms.impl.RoundedRectangleImpl <em>Rounded Rectangle</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.mm.algorithms.impl.RoundedRectangleImpl
		 * @see org.eclipse.graphiti.mm.algorithms.impl.AlgorithmsPackageImpl#getRoundedRectangle()
		 * @generated
		 */
		EClass ROUNDED_RECTANGLE = eINSTANCE.getRoundedRectangle();

		/**
		 * The meta object literal for the '<em><b>Corner Height</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ROUNDED_RECTANGLE__CORNER_HEIGHT = eINSTANCE.getRoundedRectangle_CornerHeight();

		/**
		 * The meta object literal for the '<em><b>Corner Width</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ROUNDED_RECTANGLE__CORNER_WIDTH = eINSTANCE.getRoundedRectangle_CornerWidth();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.mm.algorithms.impl.ImageImpl <em>Image</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.mm.algorithms.impl.ImageImpl
		 * @see org.eclipse.graphiti.mm.algorithms.impl.AlgorithmsPackageImpl#getImage()
		 * @generated
		 */
		EClass IMAGE = eINSTANCE.getImage();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IMAGE__ID = eINSTANCE.getImage_Id();

		/**
		 * The meta object literal for the '<em><b>Stretch H</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IMAGE__STRETCH_H = eINSTANCE.getImage_StretchH();

		/**
		 * The meta object literal for the '<em><b>Stretch V</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IMAGE__STRETCH_V = eINSTANCE.getImage_StretchV();

		/**
		 * The meta object literal for the '<em><b>Proportional</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IMAGE__PROPORTIONAL = eINSTANCE.getImage_Proportional();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.mm.algorithms.impl.PlatformGraphicsAlgorithmImpl <em>Platform Graphics Algorithm</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.mm.algorithms.impl.PlatformGraphicsAlgorithmImpl
		 * @see org.eclipse.graphiti.mm.algorithms.impl.AlgorithmsPackageImpl#getPlatformGraphicsAlgorithm()
		 * @generated
		 */
		EClass PLATFORM_GRAPHICS_ALGORITHM = eINSTANCE.getPlatformGraphicsAlgorithm();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PLATFORM_GRAPHICS_ALGORITHM__ID = eINSTANCE.getPlatformGraphicsAlgorithm_Id();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.mm.algorithms.impl.AbstractTextImpl <em>Abstract Text</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.mm.algorithms.impl.AbstractTextImpl
		 * @see org.eclipse.graphiti.mm.algorithms.impl.AlgorithmsPackageImpl#getAbstractText()
		 * @generated
		 */
		EClass ABSTRACT_TEXT = eINSTANCE.getAbstractText();

		/**
		 * The meta object literal for the '<em><b>Font</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_TEXT__FONT = eINSTANCE.getAbstractText_Font();

		/**
		 * The meta object literal for the '<em><b>Horizontal Alignment</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_TEXT__HORIZONTAL_ALIGNMENT = eINSTANCE.getAbstractText_HorizontalAlignment();

		/**
		 * The meta object literal for the '<em><b>Vertical Alignment</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_TEXT__VERTICAL_ALIGNMENT = eINSTANCE.getAbstractText_VerticalAlignment();

		/**
		 * The meta object literal for the '<em><b>Angle</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_TEXT__ANGLE = eINSTANCE.getAbstractText_Angle();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_TEXT__VALUE = eINSTANCE.getAbstractText_Value();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.mm.algorithms.impl.MultiTextImpl <em>Multi Text</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.mm.algorithms.impl.MultiTextImpl
		 * @see org.eclipse.graphiti.mm.algorithms.impl.AlgorithmsPackageImpl#getMultiText()
		 * @generated
		 */
		EClass MULTI_TEXT = eINSTANCE.getMultiText();

	}

} //AlgorithmsPackage
