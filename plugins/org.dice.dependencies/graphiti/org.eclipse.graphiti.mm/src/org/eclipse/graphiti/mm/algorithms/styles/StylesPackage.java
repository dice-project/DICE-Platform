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
package org.eclipse.graphiti.mm.algorithms.styles;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
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
 * @see org.eclipse.graphiti.mm.algorithms.styles.StylesFactory
 * @model kind="package"
 * @generated
 */
public interface StylesPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "styles";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://eclipse.org/graphiti/mm/algorithms/styles";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "st";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	StylesPackage eINSTANCE = org.eclipse.graphiti.mm.algorithms.styles.impl.StylesPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.mm.algorithms.styles.impl.RenderingStyleImpl <em>Rendering Style</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.mm.algorithms.styles.impl.RenderingStyleImpl
	 * @see org.eclipse.graphiti.mm.algorithms.styles.impl.StylesPackageImpl#getRenderingStyle()
	 * @generated
	 */
	int RENDERING_STYLE = 0;

	/**
	 * The feature id for the '<em><b>Adapted Gradient Colored Areas</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERING_STYLE__ADAPTED_GRADIENT_COLORED_AREAS = 0;

	/**
	 * The number of structural features of the '<em>Rendering Style</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERING_STYLE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.mm.algorithms.styles.impl.StyleImpl <em>Style</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.mm.algorithms.styles.impl.StyleImpl
	 * @see org.eclipse.graphiti.mm.algorithms.styles.impl.StylesPackageImpl#getStyle()
	 * @generated
	 */
	int STYLE = 1;

	/**
	 * The feature id for the '<em><b>Styles</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLE__STYLES = MmPackage.STYLE_CONTAINER__STYLES;

	/**
	 * The feature id for the '<em><b>Background</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLE__BACKGROUND = MmPackage.STYLE_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Foreground</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLE__FOREGROUND = MmPackage.STYLE_CONTAINER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Line Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLE__LINE_WIDTH = MmPackage.STYLE_CONTAINER_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Line Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLE__LINE_STYLE = MmPackage.STYLE_CONTAINER_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Filled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLE__FILLED = MmPackage.STYLE_CONTAINER_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Line Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLE__LINE_VISIBLE = MmPackage.STYLE_CONTAINER_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Rendering Style</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLE__RENDERING_STYLE = MmPackage.STYLE_CONTAINER_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Transparency</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLE__TRANSPARENCY = MmPackage.STYLE_CONTAINER_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLE__ID = MmPackage.STYLE_CONTAINER_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLE__DESCRIPTION = MmPackage.STYLE_CONTAINER_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Font</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLE__FONT = MmPackage.STYLE_CONTAINER_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Horizontal Alignment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLE__HORIZONTAL_ALIGNMENT = MmPackage.STYLE_CONTAINER_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Vertical Alignment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLE__VERTICAL_ALIGNMENT = MmPackage.STYLE_CONTAINER_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Angle</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLE__ANGLE = MmPackage.STYLE_CONTAINER_FEATURE_COUNT + 13;

	/**
	 * The feature id for the '<em><b>Stretch H</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLE__STRETCH_H = MmPackage.STYLE_CONTAINER_FEATURE_COUNT + 14;

	/**
	 * The feature id for the '<em><b>Stretch V</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLE__STRETCH_V = MmPackage.STYLE_CONTAINER_FEATURE_COUNT + 15;

	/**
	 * The feature id for the '<em><b>Proportional</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLE__PROPORTIONAL = MmPackage.STYLE_CONTAINER_FEATURE_COUNT + 16;

	/**
	 * The feature id for the '<em><b>Style Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLE__STYLE_CONTAINER = MmPackage.STYLE_CONTAINER_FEATURE_COUNT + 17;

	/**
	 * The number of structural features of the '<em>Style</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLE_FEATURE_COUNT = MmPackage.STYLE_CONTAINER_FEATURE_COUNT + 18;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.mm.algorithms.styles.impl.AbstractStyleImpl <em>Abstract Style</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.mm.algorithms.styles.impl.AbstractStyleImpl
	 * @see org.eclipse.graphiti.mm.algorithms.styles.impl.StylesPackageImpl#getAbstractStyle()
	 * @generated
	 */
	int ABSTRACT_STYLE = 2;

	/**
	 * The feature id for the '<em><b>Background</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_STYLE__BACKGROUND = 0;

	/**
	 * The feature id for the '<em><b>Foreground</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_STYLE__FOREGROUND = 1;

	/**
	 * The feature id for the '<em><b>Line Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_STYLE__LINE_WIDTH = 2;

	/**
	 * The feature id for the '<em><b>Line Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_STYLE__LINE_STYLE = 3;

	/**
	 * The feature id for the '<em><b>Filled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_STYLE__FILLED = 4;

	/**
	 * The feature id for the '<em><b>Line Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_STYLE__LINE_VISIBLE = 5;

	/**
	 * The feature id for the '<em><b>Rendering Style</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_STYLE__RENDERING_STYLE = 6;

	/**
	 * The feature id for the '<em><b>Transparency</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_STYLE__TRANSPARENCY = 7;

	/**
	 * The number of structural features of the '<em>Abstract Style</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_STYLE_FEATURE_COUNT = 8;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.mm.algorithms.styles.impl.GradientColoredLocationImpl <em>Gradient Colored Location</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.mm.algorithms.styles.impl.GradientColoredLocationImpl
	 * @see org.eclipse.graphiti.mm.algorithms.styles.impl.StylesPackageImpl#getGradientColoredLocation()
	 * @generated
	 */
	int GRADIENT_COLORED_LOCATION = 3;

	/**
	 * The feature id for the '<em><b>Location Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRADIENT_COLORED_LOCATION__LOCATION_TYPE = 0;

	/**
	 * The feature id for the '<em><b>Location Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRADIENT_COLORED_LOCATION__LOCATION_VALUE = 1;

	/**
	 * The feature id for the '<em><b>Color</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRADIENT_COLORED_LOCATION__COLOR = 2;

	/**
	 * The number of structural features of the '<em>Gradient Colored Location</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRADIENT_COLORED_LOCATION_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.mm.algorithms.styles.impl.GradientColoredAreaImpl <em>Gradient Colored Area</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.mm.algorithms.styles.impl.GradientColoredAreaImpl
	 * @see org.eclipse.graphiti.mm.algorithms.styles.impl.StylesPackageImpl#getGradientColoredArea()
	 * @generated
	 */
	int GRADIENT_COLORED_AREA = 4;

	/**
	 * The feature id for the '<em><b>Start</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRADIENT_COLORED_AREA__START = 0;

	/**
	 * The feature id for the '<em><b>End</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRADIENT_COLORED_AREA__END = 1;

	/**
	 * The number of structural features of the '<em>Gradient Colored Area</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRADIENT_COLORED_AREA_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.mm.algorithms.styles.impl.GradientColoredAreasImpl <em>Gradient Colored Areas</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.mm.algorithms.styles.impl.GradientColoredAreasImpl
	 * @see org.eclipse.graphiti.mm.algorithms.styles.impl.StylesPackageImpl#getGradientColoredAreas()
	 * @generated
	 */
	int GRADIENT_COLORED_AREAS = 5;

	/**
	 * The feature id for the '<em><b>Gradient Color</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRADIENT_COLORED_AREAS__GRADIENT_COLOR = 0;

	/**
	 * The feature id for the '<em><b>Style Adaption</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRADIENT_COLORED_AREAS__STYLE_ADAPTION = 1;

	/**
	 * The number of structural features of the '<em>Gradient Colored Areas</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRADIENT_COLORED_AREAS_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.mm.algorithms.styles.impl.AdaptedGradientColoredAreasImpl <em>Adapted Gradient Colored Areas</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.mm.algorithms.styles.impl.AdaptedGradientColoredAreasImpl
	 * @see org.eclipse.graphiti.mm.algorithms.styles.impl.StylesPackageImpl#getAdaptedGradientColoredAreas()
	 * @generated
	 */
	int ADAPTED_GRADIENT_COLORED_AREAS = 6;

	/**
	 * The feature id for the '<em><b>Defined Style Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTED_GRADIENT_COLORED_AREAS__DEFINED_STYLE_ID = 0;

	/**
	 * The feature id for the '<em><b>Adapted Gradient Colored Areas</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTED_GRADIENT_COLORED_AREAS__ADAPTED_GRADIENT_COLORED_AREAS = 1;

	/**
	 * The feature id for the '<em><b>Gradient Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTED_GRADIENT_COLORED_AREAS__GRADIENT_TYPE = 2;

	/**
	 * The number of structural features of the '<em>Adapted Gradient Colored Areas</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTED_GRADIENT_COLORED_AREAS_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.mm.algorithms.styles.impl.FontImpl <em>Font</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.mm.algorithms.styles.impl.FontImpl
	 * @see org.eclipse.graphiti.mm.algorithms.styles.impl.StylesPackageImpl#getFont()
	 * @generated
	 */
	int FONT = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FONT__NAME = 0;

	/**
	 * The feature id for the '<em><b>Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FONT__SIZE = 1;

	/**
	 * The feature id for the '<em><b>Italic</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FONT__ITALIC = 2;

	/**
	 * The feature id for the '<em><b>Bold</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FONT__BOLD = 3;

	/**
	 * The number of structural features of the '<em>Font</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FONT_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.mm.algorithms.styles.impl.PointImpl <em>Point</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.mm.algorithms.styles.impl.PointImpl
	 * @see org.eclipse.graphiti.mm.algorithms.styles.impl.StylesPackageImpl#getPoint()
	 * @generated
	 */
	int POINT = 8;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POINT__X = 0;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POINT__Y = 1;

	/**
	 * The feature id for the '<em><b>Before</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POINT__BEFORE = 2;

	/**
	 * The feature id for the '<em><b>After</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POINT__AFTER = 3;

	/**
	 * The number of structural features of the '<em>Point</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POINT_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.mm.algorithms.styles.impl.ColorImpl <em>Color</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.mm.algorithms.styles.impl.ColorImpl
	 * @see org.eclipse.graphiti.mm.algorithms.styles.impl.StylesPackageImpl#getColor()
	 * @generated
	 */
	int COLOR = 9;

	/**
	 * The feature id for the '<em><b>Red</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLOR__RED = 0;

	/**
	 * The feature id for the '<em><b>Green</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLOR__GREEN = 1;

	/**
	 * The feature id for the '<em><b>Blue</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLOR__BLUE = 2;

	/**
	 * The number of structural features of the '<em>Color</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLOR_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.mm.algorithms.styles.LineStyle <em>Line Style</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.mm.algorithms.styles.LineStyle
	 * @see org.eclipse.graphiti.mm.algorithms.styles.impl.StylesPackageImpl#getLineStyle()
	 * @generated
	 */
	int LINE_STYLE = 10;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.mm.algorithms.styles.Orientation <em>Orientation</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.mm.algorithms.styles.Orientation
	 * @see org.eclipse.graphiti.mm.algorithms.styles.impl.StylesPackageImpl#getOrientation()
	 * @generated
	 */
	int ORIENTATION = 11;

	/**
	 * The meta object id for the '{@link org.eclipse.graphiti.mm.algorithms.styles.LocationType <em>Location Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.graphiti.mm.algorithms.styles.LocationType
	 * @see org.eclipse.graphiti.mm.algorithms.styles.impl.StylesPackageImpl#getLocationType()
	 * @generated
	 */
	int LOCATION_TYPE = 12;


	/**
	 * Returns the meta object for class '{@link org.eclipse.graphiti.mm.algorithms.styles.RenderingStyle <em>Rendering Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Rendering Style</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.RenderingStyle
	 * @generated
	 */
	EClass getRenderingStyle();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.graphiti.mm.algorithms.styles.RenderingStyle#getAdaptedGradientColoredAreas <em>Adapted Gradient Colored Areas</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Adapted Gradient Colored Areas</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.RenderingStyle#getAdaptedGradientColoredAreas()
	 * @see #getRenderingStyle()
	 * @generated
	 */
	EReference getRenderingStyle_AdaptedGradientColoredAreas();

	/**
	 * Returns the meta object for class '{@link org.eclipse.graphiti.mm.algorithms.styles.Style <em>Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Style</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.Style
	 * @generated
	 */
	EClass getStyle();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.algorithms.styles.Style#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.Style#getId()
	 * @see #getStyle()
	 * @generated
	 */
	EAttribute getStyle_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.algorithms.styles.Style#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.Style#getDescription()
	 * @see #getStyle()
	 * @generated
	 */
	EAttribute getStyle_Description();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.graphiti.mm.algorithms.styles.Style#getFont <em>Font</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Font</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.Style#getFont()
	 * @see #getStyle()
	 * @generated
	 */
	EReference getStyle_Font();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.algorithms.styles.Style#getHorizontalAlignment <em>Horizontal Alignment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Horizontal Alignment</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.Style#getHorizontalAlignment()
	 * @see #getStyle()
	 * @generated
	 */
	EAttribute getStyle_HorizontalAlignment();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.algorithms.styles.Style#getVerticalAlignment <em>Vertical Alignment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Vertical Alignment</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.Style#getVerticalAlignment()
	 * @see #getStyle()
	 * @generated
	 */
	EAttribute getStyle_VerticalAlignment();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.algorithms.styles.Style#getAngle <em>Angle</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Angle</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.Style#getAngle()
	 * @see #getStyle()
	 * @generated
	 */
	EAttribute getStyle_Angle();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.algorithms.styles.Style#getStretchH <em>Stretch H</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Stretch H</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.Style#getStretchH()
	 * @see #getStyle()
	 * @generated
	 */
	EAttribute getStyle_StretchH();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.algorithms.styles.Style#getStretchV <em>Stretch V</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Stretch V</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.Style#getStretchV()
	 * @see #getStyle()
	 * @generated
	 */
	EAttribute getStyle_StretchV();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.algorithms.styles.Style#getProportional <em>Proportional</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Proportional</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.Style#getProportional()
	 * @see #getStyle()
	 * @generated
	 */
	EAttribute getStyle_Proportional();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.graphiti.mm.algorithms.styles.Style#getStyleContainer <em>Style Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Style Container</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.Style#getStyleContainer()
	 * @see #getStyle()
	 * @generated
	 */
	EReference getStyle_StyleContainer();

	/**
	 * Returns the meta object for class '{@link org.eclipse.graphiti.mm.algorithms.styles.AbstractStyle <em>Abstract Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Style</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.AbstractStyle
	 * @generated
	 */
	EClass getAbstractStyle();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.graphiti.mm.algorithms.styles.AbstractStyle#getBackground <em>Background</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Background</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.AbstractStyle#getBackground()
	 * @see #getAbstractStyle()
	 * @generated
	 */
	EReference getAbstractStyle_Background();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.graphiti.mm.algorithms.styles.AbstractStyle#getForeground <em>Foreground</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Foreground</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.AbstractStyle#getForeground()
	 * @see #getAbstractStyle()
	 * @generated
	 */
	EReference getAbstractStyle_Foreground();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.algorithms.styles.AbstractStyle#getLineWidth <em>Line Width</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Line Width</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.AbstractStyle#getLineWidth()
	 * @see #getAbstractStyle()
	 * @generated
	 */
	EAttribute getAbstractStyle_LineWidth();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.algorithms.styles.AbstractStyle#getLineStyle <em>Line Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Line Style</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.AbstractStyle#getLineStyle()
	 * @see #getAbstractStyle()
	 * @generated
	 */
	EAttribute getAbstractStyle_LineStyle();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.algorithms.styles.AbstractStyle#getFilled <em>Filled</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Filled</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.AbstractStyle#getFilled()
	 * @see #getAbstractStyle()
	 * @generated
	 */
	EAttribute getAbstractStyle_Filled();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.algorithms.styles.AbstractStyle#getLineVisible <em>Line Visible</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Line Visible</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.AbstractStyle#getLineVisible()
	 * @see #getAbstractStyle()
	 * @generated
	 */
	EAttribute getAbstractStyle_LineVisible();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.graphiti.mm.algorithms.styles.AbstractStyle#getRenderingStyle <em>Rendering Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Rendering Style</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.AbstractStyle#getRenderingStyle()
	 * @see #getAbstractStyle()
	 * @generated
	 */
	EReference getAbstractStyle_RenderingStyle();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.algorithms.styles.AbstractStyle#getTransparency <em>Transparency</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Transparency</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.AbstractStyle#getTransparency()
	 * @see #getAbstractStyle()
	 * @generated
	 */
	EAttribute getAbstractStyle_Transparency();

	/**
	 * Returns the meta object for class '{@link org.eclipse.graphiti.mm.algorithms.styles.GradientColoredLocation <em>Gradient Colored Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Gradient Colored Location</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.GradientColoredLocation
	 * @generated
	 */
	EClass getGradientColoredLocation();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.algorithms.styles.GradientColoredLocation#getLocationType <em>Location Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Location Type</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.GradientColoredLocation#getLocationType()
	 * @see #getGradientColoredLocation()
	 * @generated
	 */
	EAttribute getGradientColoredLocation_LocationType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.algorithms.styles.GradientColoredLocation#getLocationValue <em>Location Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Location Value</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.GradientColoredLocation#getLocationValue()
	 * @see #getGradientColoredLocation()
	 * @generated
	 */
	EAttribute getGradientColoredLocation_LocationValue();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.graphiti.mm.algorithms.styles.GradientColoredLocation#getColor <em>Color</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Color</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.GradientColoredLocation#getColor()
	 * @see #getGradientColoredLocation()
	 * @generated
	 */
	EReference getGradientColoredLocation_Color();

	/**
	 * Returns the meta object for class '{@link org.eclipse.graphiti.mm.algorithms.styles.GradientColoredArea <em>Gradient Colored Area</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Gradient Colored Area</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.GradientColoredArea
	 * @generated
	 */
	EClass getGradientColoredArea();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.graphiti.mm.algorithms.styles.GradientColoredArea#getStart <em>Start</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Start</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.GradientColoredArea#getStart()
	 * @see #getGradientColoredArea()
	 * @generated
	 */
	EReference getGradientColoredArea_Start();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.graphiti.mm.algorithms.styles.GradientColoredArea#getEnd <em>End</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>End</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.GradientColoredArea#getEnd()
	 * @see #getGradientColoredArea()
	 * @generated
	 */
	EReference getGradientColoredArea_End();

	/**
	 * Returns the meta object for class '{@link org.eclipse.graphiti.mm.algorithms.styles.GradientColoredAreas <em>Gradient Colored Areas</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Gradient Colored Areas</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.GradientColoredAreas
	 * @generated
	 */
	EClass getGradientColoredAreas();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.graphiti.mm.algorithms.styles.GradientColoredAreas#getGradientColor <em>Gradient Color</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Gradient Color</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.GradientColoredAreas#getGradientColor()
	 * @see #getGradientColoredAreas()
	 * @generated
	 */
	EReference getGradientColoredAreas_GradientColor();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.algorithms.styles.GradientColoredAreas#getStyleAdaption <em>Style Adaption</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Style Adaption</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.GradientColoredAreas#getStyleAdaption()
	 * @see #getGradientColoredAreas()
	 * @generated
	 */
	EAttribute getGradientColoredAreas_StyleAdaption();

	/**
	 * Returns the meta object for class '{@link org.eclipse.graphiti.mm.algorithms.styles.AdaptedGradientColoredAreas <em>Adapted Gradient Colored Areas</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Adapted Gradient Colored Areas</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.AdaptedGradientColoredAreas
	 * @generated
	 */
	EClass getAdaptedGradientColoredAreas();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.algorithms.styles.AdaptedGradientColoredAreas#getDefinedStyleId <em>Defined Style Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Defined Style Id</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.AdaptedGradientColoredAreas#getDefinedStyleId()
	 * @see #getAdaptedGradientColoredAreas()
	 * @generated
	 */
	EAttribute getAdaptedGradientColoredAreas_DefinedStyleId();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.graphiti.mm.algorithms.styles.AdaptedGradientColoredAreas#getAdaptedGradientColoredAreas <em>Adapted Gradient Colored Areas</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Adapted Gradient Colored Areas</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.AdaptedGradientColoredAreas#getAdaptedGradientColoredAreas()
	 * @see #getAdaptedGradientColoredAreas()
	 * @generated
	 */
	EReference getAdaptedGradientColoredAreas_AdaptedGradientColoredAreas();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.algorithms.styles.AdaptedGradientColoredAreas#getGradientType <em>Gradient Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Gradient Type</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.AdaptedGradientColoredAreas#getGradientType()
	 * @see #getAdaptedGradientColoredAreas()
	 * @generated
	 */
	EAttribute getAdaptedGradientColoredAreas_GradientType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.graphiti.mm.algorithms.styles.Font <em>Font</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Font</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.Font
	 * @generated
	 */
	EClass getFont();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.algorithms.styles.Font#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.Font#getName()
	 * @see #getFont()
	 * @generated
	 */
	EAttribute getFont_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.algorithms.styles.Font#getSize <em>Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Size</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.Font#getSize()
	 * @see #getFont()
	 * @generated
	 */
	EAttribute getFont_Size();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.algorithms.styles.Font#isItalic <em>Italic</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Italic</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.Font#isItalic()
	 * @see #getFont()
	 * @generated
	 */
	EAttribute getFont_Italic();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.algorithms.styles.Font#isBold <em>Bold</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Bold</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.Font#isBold()
	 * @see #getFont()
	 * @generated
	 */
	EAttribute getFont_Bold();

	/**
	 * Returns the meta object for class '{@link org.eclipse.graphiti.mm.algorithms.styles.Point <em>Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Point</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.Point
	 * @generated
	 */
	EClass getPoint();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.algorithms.styles.Point#getX <em>X</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>X</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.Point#getX()
	 * @see #getPoint()
	 * @generated
	 */
	EAttribute getPoint_X();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.algorithms.styles.Point#getY <em>Y</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Y</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.Point#getY()
	 * @see #getPoint()
	 * @generated
	 */
	EAttribute getPoint_Y();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.algorithms.styles.Point#getBefore <em>Before</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Before</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.Point#getBefore()
	 * @see #getPoint()
	 * @generated
	 */
	EAttribute getPoint_Before();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.algorithms.styles.Point#getAfter <em>After</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>After</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.Point#getAfter()
	 * @see #getPoint()
	 * @generated
	 */
	EAttribute getPoint_After();

	/**
	 * Returns the meta object for class '{@link org.eclipse.graphiti.mm.algorithms.styles.Color <em>Color</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Color</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.Color
	 * @generated
	 */
	EClass getColor();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.algorithms.styles.Color#getRed <em>Red</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Red</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.Color#getRed()
	 * @see #getColor()
	 * @generated
	 */
	EAttribute getColor_Red();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.algorithms.styles.Color#getGreen <em>Green</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Green</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.Color#getGreen()
	 * @see #getColor()
	 * @generated
	 */
	EAttribute getColor_Green();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.graphiti.mm.algorithms.styles.Color#getBlue <em>Blue</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Blue</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.Color#getBlue()
	 * @see #getColor()
	 * @generated
	 */
	EAttribute getColor_Blue();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.graphiti.mm.algorithms.styles.LineStyle <em>Line Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Line Style</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.LineStyle
	 * @generated
	 */
	EEnum getLineStyle();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.graphiti.mm.algorithms.styles.Orientation <em>Orientation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Orientation</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.Orientation
	 * @generated
	 */
	EEnum getOrientation();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.graphiti.mm.algorithms.styles.LocationType <em>Location Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Location Type</em>'.
	 * @see org.eclipse.graphiti.mm.algorithms.styles.LocationType
	 * @generated
	 */
	EEnum getLocationType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	StylesFactory getStylesFactory();

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
		 * The meta object literal for the '{@link org.eclipse.graphiti.mm.algorithms.styles.impl.RenderingStyleImpl <em>Rendering Style</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.mm.algorithms.styles.impl.RenderingStyleImpl
		 * @see org.eclipse.graphiti.mm.algorithms.styles.impl.StylesPackageImpl#getRenderingStyle()
		 * @generated
		 */
		EClass RENDERING_STYLE = eINSTANCE.getRenderingStyle();

		/**
		 * The meta object literal for the '<em><b>Adapted Gradient Colored Areas</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RENDERING_STYLE__ADAPTED_GRADIENT_COLORED_AREAS = eINSTANCE.getRenderingStyle_AdaptedGradientColoredAreas();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.mm.algorithms.styles.impl.StyleImpl <em>Style</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.mm.algorithms.styles.impl.StyleImpl
		 * @see org.eclipse.graphiti.mm.algorithms.styles.impl.StylesPackageImpl#getStyle()
		 * @generated
		 */
		EClass STYLE = eINSTANCE.getStyle();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STYLE__ID = eINSTANCE.getStyle_Id();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STYLE__DESCRIPTION = eINSTANCE.getStyle_Description();

		/**
		 * The meta object literal for the '<em><b>Font</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STYLE__FONT = eINSTANCE.getStyle_Font();

		/**
		 * The meta object literal for the '<em><b>Horizontal Alignment</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STYLE__HORIZONTAL_ALIGNMENT = eINSTANCE.getStyle_HorizontalAlignment();

		/**
		 * The meta object literal for the '<em><b>Vertical Alignment</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STYLE__VERTICAL_ALIGNMENT = eINSTANCE.getStyle_VerticalAlignment();

		/**
		 * The meta object literal for the '<em><b>Angle</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STYLE__ANGLE = eINSTANCE.getStyle_Angle();

		/**
		 * The meta object literal for the '<em><b>Stretch H</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STYLE__STRETCH_H = eINSTANCE.getStyle_StretchH();

		/**
		 * The meta object literal for the '<em><b>Stretch V</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STYLE__STRETCH_V = eINSTANCE.getStyle_StretchV();

		/**
		 * The meta object literal for the '<em><b>Proportional</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STYLE__PROPORTIONAL = eINSTANCE.getStyle_Proportional();

		/**
		 * The meta object literal for the '<em><b>Style Container</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STYLE__STYLE_CONTAINER = eINSTANCE.getStyle_StyleContainer();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.mm.algorithms.styles.impl.AbstractStyleImpl <em>Abstract Style</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.mm.algorithms.styles.impl.AbstractStyleImpl
		 * @see org.eclipse.graphiti.mm.algorithms.styles.impl.StylesPackageImpl#getAbstractStyle()
		 * @generated
		 */
		EClass ABSTRACT_STYLE = eINSTANCE.getAbstractStyle();

		/**
		 * The meta object literal for the '<em><b>Background</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_STYLE__BACKGROUND = eINSTANCE.getAbstractStyle_Background();

		/**
		 * The meta object literal for the '<em><b>Foreground</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_STYLE__FOREGROUND = eINSTANCE.getAbstractStyle_Foreground();

		/**
		 * The meta object literal for the '<em><b>Line Width</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_STYLE__LINE_WIDTH = eINSTANCE.getAbstractStyle_LineWidth();

		/**
		 * The meta object literal for the '<em><b>Line Style</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_STYLE__LINE_STYLE = eINSTANCE.getAbstractStyle_LineStyle();

		/**
		 * The meta object literal for the '<em><b>Filled</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_STYLE__FILLED = eINSTANCE.getAbstractStyle_Filled();

		/**
		 * The meta object literal for the '<em><b>Line Visible</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_STYLE__LINE_VISIBLE = eINSTANCE.getAbstractStyle_LineVisible();

		/**
		 * The meta object literal for the '<em><b>Rendering Style</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_STYLE__RENDERING_STYLE = eINSTANCE.getAbstractStyle_RenderingStyle();

		/**
		 * The meta object literal for the '<em><b>Transparency</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_STYLE__TRANSPARENCY = eINSTANCE.getAbstractStyle_Transparency();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.mm.algorithms.styles.impl.GradientColoredLocationImpl <em>Gradient Colored Location</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.mm.algorithms.styles.impl.GradientColoredLocationImpl
		 * @see org.eclipse.graphiti.mm.algorithms.styles.impl.StylesPackageImpl#getGradientColoredLocation()
		 * @generated
		 */
		EClass GRADIENT_COLORED_LOCATION = eINSTANCE.getGradientColoredLocation();

		/**
		 * The meta object literal for the '<em><b>Location Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GRADIENT_COLORED_LOCATION__LOCATION_TYPE = eINSTANCE.getGradientColoredLocation_LocationType();

		/**
		 * The meta object literal for the '<em><b>Location Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GRADIENT_COLORED_LOCATION__LOCATION_VALUE = eINSTANCE.getGradientColoredLocation_LocationValue();

		/**
		 * The meta object literal for the '<em><b>Color</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GRADIENT_COLORED_LOCATION__COLOR = eINSTANCE.getGradientColoredLocation_Color();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.mm.algorithms.styles.impl.GradientColoredAreaImpl <em>Gradient Colored Area</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.mm.algorithms.styles.impl.GradientColoredAreaImpl
		 * @see org.eclipse.graphiti.mm.algorithms.styles.impl.StylesPackageImpl#getGradientColoredArea()
		 * @generated
		 */
		EClass GRADIENT_COLORED_AREA = eINSTANCE.getGradientColoredArea();

		/**
		 * The meta object literal for the '<em><b>Start</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GRADIENT_COLORED_AREA__START = eINSTANCE.getGradientColoredArea_Start();

		/**
		 * The meta object literal for the '<em><b>End</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GRADIENT_COLORED_AREA__END = eINSTANCE.getGradientColoredArea_End();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.mm.algorithms.styles.impl.GradientColoredAreasImpl <em>Gradient Colored Areas</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.mm.algorithms.styles.impl.GradientColoredAreasImpl
		 * @see org.eclipse.graphiti.mm.algorithms.styles.impl.StylesPackageImpl#getGradientColoredAreas()
		 * @generated
		 */
		EClass GRADIENT_COLORED_AREAS = eINSTANCE.getGradientColoredAreas();

		/**
		 * The meta object literal for the '<em><b>Gradient Color</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GRADIENT_COLORED_AREAS__GRADIENT_COLOR = eINSTANCE.getGradientColoredAreas_GradientColor();

		/**
		 * The meta object literal for the '<em><b>Style Adaption</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GRADIENT_COLORED_AREAS__STYLE_ADAPTION = eINSTANCE.getGradientColoredAreas_StyleAdaption();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.mm.algorithms.styles.impl.AdaptedGradientColoredAreasImpl <em>Adapted Gradient Colored Areas</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.mm.algorithms.styles.impl.AdaptedGradientColoredAreasImpl
		 * @see org.eclipse.graphiti.mm.algorithms.styles.impl.StylesPackageImpl#getAdaptedGradientColoredAreas()
		 * @generated
		 */
		EClass ADAPTED_GRADIENT_COLORED_AREAS = eINSTANCE.getAdaptedGradientColoredAreas();

		/**
		 * The meta object literal for the '<em><b>Defined Style Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADAPTED_GRADIENT_COLORED_AREAS__DEFINED_STYLE_ID = eINSTANCE.getAdaptedGradientColoredAreas_DefinedStyleId();

		/**
		 * The meta object literal for the '<em><b>Adapted Gradient Colored Areas</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ADAPTED_GRADIENT_COLORED_AREAS__ADAPTED_GRADIENT_COLORED_AREAS = eINSTANCE.getAdaptedGradientColoredAreas_AdaptedGradientColoredAreas();

		/**
		 * The meta object literal for the '<em><b>Gradient Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADAPTED_GRADIENT_COLORED_AREAS__GRADIENT_TYPE = eINSTANCE.getAdaptedGradientColoredAreas_GradientType();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.mm.algorithms.styles.impl.FontImpl <em>Font</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.mm.algorithms.styles.impl.FontImpl
		 * @see org.eclipse.graphiti.mm.algorithms.styles.impl.StylesPackageImpl#getFont()
		 * @generated
		 */
		EClass FONT = eINSTANCE.getFont();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FONT__NAME = eINSTANCE.getFont_Name();

		/**
		 * The meta object literal for the '<em><b>Size</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FONT__SIZE = eINSTANCE.getFont_Size();

		/**
		 * The meta object literal for the '<em><b>Italic</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FONT__ITALIC = eINSTANCE.getFont_Italic();

		/**
		 * The meta object literal for the '<em><b>Bold</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FONT__BOLD = eINSTANCE.getFont_Bold();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.mm.algorithms.styles.impl.PointImpl <em>Point</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.mm.algorithms.styles.impl.PointImpl
		 * @see org.eclipse.graphiti.mm.algorithms.styles.impl.StylesPackageImpl#getPoint()
		 * @generated
		 */
		EClass POINT = eINSTANCE.getPoint();

		/**
		 * The meta object literal for the '<em><b>X</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POINT__X = eINSTANCE.getPoint_X();

		/**
		 * The meta object literal for the '<em><b>Y</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POINT__Y = eINSTANCE.getPoint_Y();

		/**
		 * The meta object literal for the '<em><b>Before</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POINT__BEFORE = eINSTANCE.getPoint_Before();

		/**
		 * The meta object literal for the '<em><b>After</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POINT__AFTER = eINSTANCE.getPoint_After();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.mm.algorithms.styles.impl.ColorImpl <em>Color</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.mm.algorithms.styles.impl.ColorImpl
		 * @see org.eclipse.graphiti.mm.algorithms.styles.impl.StylesPackageImpl#getColor()
		 * @generated
		 */
		EClass COLOR = eINSTANCE.getColor();

		/**
		 * The meta object literal for the '<em><b>Red</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLOR__RED = eINSTANCE.getColor_Red();

		/**
		 * The meta object literal for the '<em><b>Green</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLOR__GREEN = eINSTANCE.getColor_Green();

		/**
		 * The meta object literal for the '<em><b>Blue</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLOR__BLUE = eINSTANCE.getColor_Blue();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.mm.algorithms.styles.LineStyle <em>Line Style</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.mm.algorithms.styles.LineStyle
		 * @see org.eclipse.graphiti.mm.algorithms.styles.impl.StylesPackageImpl#getLineStyle()
		 * @generated
		 */
		EEnum LINE_STYLE = eINSTANCE.getLineStyle();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.mm.algorithms.styles.Orientation <em>Orientation</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.mm.algorithms.styles.Orientation
		 * @see org.eclipse.graphiti.mm.algorithms.styles.impl.StylesPackageImpl#getOrientation()
		 * @generated
		 */
		EEnum ORIENTATION = eINSTANCE.getOrientation();

		/**
		 * The meta object literal for the '{@link org.eclipse.graphiti.mm.algorithms.styles.LocationType <em>Location Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.graphiti.mm.algorithms.styles.LocationType
		 * @see org.eclipse.graphiti.mm.algorithms.styles.impl.StylesPackageImpl#getLocationType()
		 * @generated
		 */
		EEnum LOCATION_TYPE = eINSTANCE.getLocationType();

	}

} //StylesPackage
