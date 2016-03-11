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
package org.eclipse.graphiti.mm.algorithms.styles.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.graphiti.mm.MmPackage;

import org.eclipse.graphiti.mm.algorithms.AlgorithmsPackage;

import org.eclipse.graphiti.mm.algorithms.impl.AlgorithmsPackageImpl;

import org.eclipse.graphiti.mm.algorithms.styles.AbstractStyle;
import org.eclipse.graphiti.mm.algorithms.styles.AdaptedGradientColoredAreas;
import org.eclipse.graphiti.mm.algorithms.styles.Color;
import org.eclipse.graphiti.mm.algorithms.styles.Font;
import org.eclipse.graphiti.mm.algorithms.styles.GradientColoredArea;
import org.eclipse.graphiti.mm.algorithms.styles.GradientColoredAreas;
import org.eclipse.graphiti.mm.algorithms.styles.GradientColoredLocation;
import org.eclipse.graphiti.mm.algorithms.styles.LineStyle;
import org.eclipse.graphiti.mm.algorithms.styles.LocationType;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.algorithms.styles.Point;
import org.eclipse.graphiti.mm.algorithms.styles.RenderingStyle;
import org.eclipse.graphiti.mm.algorithms.styles.Style;
import org.eclipse.graphiti.mm.algorithms.styles.StylesFactory;
import org.eclipse.graphiti.mm.algorithms.styles.StylesPackage;

import org.eclipse.graphiti.mm.impl.MmPackageImpl;

import org.eclipse.graphiti.mm.pictograms.PictogramsPackage;

import org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class StylesPackageImpl extends EPackageImpl implements StylesPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass renderingStyleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass styleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractStyleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass gradientColoredLocationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass gradientColoredAreaEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass gradientColoredAreasEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass adaptedGradientColoredAreasEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass fontEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass pointEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass colorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum lineStyleEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum orientationEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum locationTypeEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.graphiti.mm.algorithms.styles.StylesPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private StylesPackageImpl() {
		super(eNS_URI, StylesFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link StylesPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static StylesPackage init() {
		if (isInited) return (StylesPackage)EPackage.Registry.INSTANCE.getEPackage(StylesPackage.eNS_URI);

		// Obtain or create and register package
		StylesPackageImpl theStylesPackage = (StylesPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof StylesPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new StylesPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		MmPackageImpl theMmPackage = (MmPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(MmPackage.eNS_URI) instanceof MmPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(MmPackage.eNS_URI) : MmPackage.eINSTANCE);
		PictogramsPackageImpl thePictogramsPackage = (PictogramsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(PictogramsPackage.eNS_URI) instanceof PictogramsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(PictogramsPackage.eNS_URI) : PictogramsPackage.eINSTANCE);
		AlgorithmsPackageImpl theAlgorithmsPackage = (AlgorithmsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(AlgorithmsPackage.eNS_URI) instanceof AlgorithmsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(AlgorithmsPackage.eNS_URI) : AlgorithmsPackage.eINSTANCE);

		// Create package meta-data objects
		theStylesPackage.createPackageContents();
		theMmPackage.createPackageContents();
		thePictogramsPackage.createPackageContents();
		theAlgorithmsPackage.createPackageContents();

		// Initialize created meta-data
		theStylesPackage.initializePackageContents();
		theMmPackage.initializePackageContents();
		thePictogramsPackage.initializePackageContents();
		theAlgorithmsPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theStylesPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(StylesPackage.eNS_URI, theStylesPackage);
		return theStylesPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRenderingStyle() {
		return renderingStyleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRenderingStyle_AdaptedGradientColoredAreas() {
		return (EReference)renderingStyleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStyle() {
		return styleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStyle_Id() {
		return (EAttribute)styleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStyle_Description() {
		return (EAttribute)styleEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStyle_Font() {
		return (EReference)styleEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStyle_HorizontalAlignment() {
		return (EAttribute)styleEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStyle_VerticalAlignment() {
		return (EAttribute)styleEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStyle_Angle() {
		return (EAttribute)styleEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStyle_StretchH() {
		return (EAttribute)styleEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStyle_StretchV() {
		return (EAttribute)styleEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStyle_Proportional() {
		return (EAttribute)styleEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStyle_StyleContainer() {
		return (EReference)styleEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAbstractStyle() {
		return abstractStyleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractStyle_Background() {
		return (EReference)abstractStyleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractStyle_Foreground() {
		return (EReference)abstractStyleEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractStyle_LineWidth() {
		return (EAttribute)abstractStyleEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractStyle_LineStyle() {
		return (EAttribute)abstractStyleEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractStyle_Filled() {
		return (EAttribute)abstractStyleEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractStyle_LineVisible() {
		return (EAttribute)abstractStyleEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractStyle_RenderingStyle() {
		return (EReference)abstractStyleEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractStyle_Transparency() {
		return (EAttribute)abstractStyleEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGradientColoredLocation() {
		return gradientColoredLocationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGradientColoredLocation_LocationType() {
		return (EAttribute)gradientColoredLocationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGradientColoredLocation_LocationValue() {
		return (EAttribute)gradientColoredLocationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGradientColoredLocation_Color() {
		return (EReference)gradientColoredLocationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGradientColoredArea() {
		return gradientColoredAreaEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGradientColoredArea_Start() {
		return (EReference)gradientColoredAreaEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGradientColoredArea_End() {
		return (EReference)gradientColoredAreaEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGradientColoredAreas() {
		return gradientColoredAreasEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGradientColoredAreas_GradientColor() {
		return (EReference)gradientColoredAreasEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGradientColoredAreas_StyleAdaption() {
		return (EAttribute)gradientColoredAreasEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAdaptedGradientColoredAreas() {
		return adaptedGradientColoredAreasEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAdaptedGradientColoredAreas_DefinedStyleId() {
		return (EAttribute)adaptedGradientColoredAreasEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAdaptedGradientColoredAreas_AdaptedGradientColoredAreas() {
		return (EReference)adaptedGradientColoredAreasEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAdaptedGradientColoredAreas_GradientType() {
		return (EAttribute)adaptedGradientColoredAreasEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFont() {
		return fontEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFont_Name() {
		return (EAttribute)fontEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFont_Size() {
		return (EAttribute)fontEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFont_Italic() {
		return (EAttribute)fontEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFont_Bold() {
		return (EAttribute)fontEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPoint() {
		return pointEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPoint_X() {
		return (EAttribute)pointEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPoint_Y() {
		return (EAttribute)pointEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPoint_Before() {
		return (EAttribute)pointEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPoint_After() {
		return (EAttribute)pointEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getColor() {
		return colorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getColor_Red() {
		return (EAttribute)colorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getColor_Green() {
		return (EAttribute)colorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getColor_Blue() {
		return (EAttribute)colorEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getLineStyle() {
		return lineStyleEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getOrientation() {
		return orientationEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getLocationType() {
		return locationTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StylesFactory getStylesFactory() {
		return (StylesFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		renderingStyleEClass = createEClass(RENDERING_STYLE);
		createEReference(renderingStyleEClass, RENDERING_STYLE__ADAPTED_GRADIENT_COLORED_AREAS);

		styleEClass = createEClass(STYLE);
		createEAttribute(styleEClass, STYLE__ID);
		createEAttribute(styleEClass, STYLE__DESCRIPTION);
		createEReference(styleEClass, STYLE__FONT);
		createEAttribute(styleEClass, STYLE__HORIZONTAL_ALIGNMENT);
		createEAttribute(styleEClass, STYLE__VERTICAL_ALIGNMENT);
		createEAttribute(styleEClass, STYLE__ANGLE);
		createEAttribute(styleEClass, STYLE__STRETCH_H);
		createEAttribute(styleEClass, STYLE__STRETCH_V);
		createEAttribute(styleEClass, STYLE__PROPORTIONAL);
		createEReference(styleEClass, STYLE__STYLE_CONTAINER);

		abstractStyleEClass = createEClass(ABSTRACT_STYLE);
		createEReference(abstractStyleEClass, ABSTRACT_STYLE__BACKGROUND);
		createEReference(abstractStyleEClass, ABSTRACT_STYLE__FOREGROUND);
		createEAttribute(abstractStyleEClass, ABSTRACT_STYLE__LINE_WIDTH);
		createEAttribute(abstractStyleEClass, ABSTRACT_STYLE__LINE_STYLE);
		createEAttribute(abstractStyleEClass, ABSTRACT_STYLE__FILLED);
		createEAttribute(abstractStyleEClass, ABSTRACT_STYLE__LINE_VISIBLE);
		createEReference(abstractStyleEClass, ABSTRACT_STYLE__RENDERING_STYLE);
		createEAttribute(abstractStyleEClass, ABSTRACT_STYLE__TRANSPARENCY);

		gradientColoredLocationEClass = createEClass(GRADIENT_COLORED_LOCATION);
		createEAttribute(gradientColoredLocationEClass, GRADIENT_COLORED_LOCATION__LOCATION_TYPE);
		createEAttribute(gradientColoredLocationEClass, GRADIENT_COLORED_LOCATION__LOCATION_VALUE);
		createEReference(gradientColoredLocationEClass, GRADIENT_COLORED_LOCATION__COLOR);

		gradientColoredAreaEClass = createEClass(GRADIENT_COLORED_AREA);
		createEReference(gradientColoredAreaEClass, GRADIENT_COLORED_AREA__START);
		createEReference(gradientColoredAreaEClass, GRADIENT_COLORED_AREA__END);

		gradientColoredAreasEClass = createEClass(GRADIENT_COLORED_AREAS);
		createEReference(gradientColoredAreasEClass, GRADIENT_COLORED_AREAS__GRADIENT_COLOR);
		createEAttribute(gradientColoredAreasEClass, GRADIENT_COLORED_AREAS__STYLE_ADAPTION);

		adaptedGradientColoredAreasEClass = createEClass(ADAPTED_GRADIENT_COLORED_AREAS);
		createEAttribute(adaptedGradientColoredAreasEClass, ADAPTED_GRADIENT_COLORED_AREAS__DEFINED_STYLE_ID);
		createEReference(adaptedGradientColoredAreasEClass, ADAPTED_GRADIENT_COLORED_AREAS__ADAPTED_GRADIENT_COLORED_AREAS);
		createEAttribute(adaptedGradientColoredAreasEClass, ADAPTED_GRADIENT_COLORED_AREAS__GRADIENT_TYPE);

		fontEClass = createEClass(FONT);
		createEAttribute(fontEClass, FONT__NAME);
		createEAttribute(fontEClass, FONT__SIZE);
		createEAttribute(fontEClass, FONT__ITALIC);
		createEAttribute(fontEClass, FONT__BOLD);

		pointEClass = createEClass(POINT);
		createEAttribute(pointEClass, POINT__X);
		createEAttribute(pointEClass, POINT__Y);
		createEAttribute(pointEClass, POINT__BEFORE);
		createEAttribute(pointEClass, POINT__AFTER);

		colorEClass = createEClass(COLOR);
		createEAttribute(colorEClass, COLOR__RED);
		createEAttribute(colorEClass, COLOR__GREEN);
		createEAttribute(colorEClass, COLOR__BLUE);

		// Create enums
		lineStyleEEnum = createEEnum(LINE_STYLE);
		orientationEEnum = createEEnum(ORIENTATION);
		locationTypeEEnum = createEEnum(LOCATION_TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		MmPackage theMmPackage = (MmPackage)EPackage.Registry.INSTANCE.getEPackage(MmPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		styleEClass.getESuperTypes().add(theMmPackage.getStyleContainer());
		styleEClass.getESuperTypes().add(this.getAbstractStyle());

		// Initialize classes and features; add operations and parameters
		initEClass(renderingStyleEClass, RenderingStyle.class, "RenderingStyle", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRenderingStyle_AdaptedGradientColoredAreas(), this.getAdaptedGradientColoredAreas(), null, "adaptedGradientColoredAreas", null, 0, 1, RenderingStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(styleEClass, Style.class, "Style", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStyle_Id(), ecorePackage.getEString(), "id", null, 1, 1, Style.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getStyle_Description(), ecorePackage.getEString(), "description", null, 0, 1, Style.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getStyle_Font(), this.getFont(), null, "font", null, 0, 1, Style.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getStyle_HorizontalAlignment(), this.getOrientation(), "horizontalAlignment", null, 0, 1, Style.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getStyle_VerticalAlignment(), this.getOrientation(), "verticalAlignment", null, 0, 1, Style.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getStyle_Angle(), ecorePackage.getEIntegerObject(), "angle", null, 0, 1, Style.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getStyle_StretchH(), ecorePackage.getEBooleanObject(), "stretchH", null, 0, 1, Style.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getStyle_StretchV(), ecorePackage.getEBooleanObject(), "stretchV", null, 0, 1, Style.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getStyle_Proportional(), ecorePackage.getEBooleanObject(), "proportional", null, 0, 1, Style.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getStyle_StyleContainer(), theMmPackage.getStyleContainer(), theMmPackage.getStyleContainer_Styles(), "styleContainer", null, 1, 1, Style.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(abstractStyleEClass, AbstractStyle.class, "AbstractStyle", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAbstractStyle_Background(), this.getColor(), null, "background", null, 0, 1, AbstractStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getAbstractStyle_Foreground(), this.getColor(), null, "foreground", null, 0, 1, AbstractStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getAbstractStyle_LineWidth(), ecorePackage.getEIntegerObject(), "lineWidth", "1", 0, 1, AbstractStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getAbstractStyle_LineStyle(), this.getLineStyle(), "lineStyle", "SOLID", 0, 1, AbstractStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getAbstractStyle_Filled(), ecorePackage.getEBooleanObject(), "filled", "true", 0, 1, AbstractStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getAbstractStyle_LineVisible(), ecorePackage.getEBooleanObject(), "lineVisible", "true", 0, 1, AbstractStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getAbstractStyle_RenderingStyle(), this.getRenderingStyle(), null, "renderingStyle", null, 0, 1, AbstractStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getAbstractStyle_Transparency(), ecorePackage.getEDoubleObject(), "transparency", "0", 0, 1, AbstractStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(gradientColoredLocationEClass, GradientColoredLocation.class, "GradientColoredLocation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getGradientColoredLocation_LocationType(), this.getLocationType(), "locationType", null, 1, 1, GradientColoredLocation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGradientColoredLocation_LocationValue(), ecorePackage.getEIntegerObject(), "locationValue", null, 1, 1, GradientColoredLocation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGradientColoredLocation_Color(), this.getColor(), null, "color", null, 1, 1, GradientColoredLocation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(gradientColoredAreaEClass, GradientColoredArea.class, "GradientColoredArea", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getGradientColoredArea_Start(), this.getGradientColoredLocation(), null, "start", null, 0, 1, GradientColoredArea.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGradientColoredArea_End(), this.getGradientColoredLocation(), null, "end", null, 0, 1, GradientColoredArea.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(gradientColoredAreasEClass, GradientColoredAreas.class, "GradientColoredAreas", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getGradientColoredAreas_GradientColor(), this.getGradientColoredArea(), null, "gradientColor", null, 0, -1, GradientColoredAreas.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGradientColoredAreas_StyleAdaption(), ecorePackage.getEIntegerObject(), "styleAdaption", null, 0, 1, GradientColoredAreas.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(adaptedGradientColoredAreasEClass, AdaptedGradientColoredAreas.class, "AdaptedGradientColoredAreas", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAdaptedGradientColoredAreas_DefinedStyleId(), ecorePackage.getEString(), "definedStyleId", null, 0, 1, AdaptedGradientColoredAreas.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAdaptedGradientColoredAreas_AdaptedGradientColoredAreas(), this.getGradientColoredAreas(), null, "adaptedGradientColoredAreas", null, 0, -1, AdaptedGradientColoredAreas.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAdaptedGradientColoredAreas_GradientType(), ecorePackage.getEIntegerObject(), "gradientType", null, 0, 1, AdaptedGradientColoredAreas.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(fontEClass, Font.class, "Font", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFont_Name(), ecorePackage.getEString(), "name", null, 1, 1, Font.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getFont_Size(), ecorePackage.getEInt(), "size", null, 1, 1, Font.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getFont_Italic(), ecorePackage.getEBoolean(), "italic", null, 1, 1, Font.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getFont_Bold(), ecorePackage.getEBoolean(), "bold", null, 1, 1, Font.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(pointEClass, Point.class, "Point", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPoint_X(), ecorePackage.getEInt(), "x", null, 1, 1, Point.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPoint_Y(), ecorePackage.getEInt(), "y", null, 1, 1, Point.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPoint_Before(), ecorePackage.getEInt(), "before", null, 1, 1, Point.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPoint_After(), ecorePackage.getEInt(), "after", null, 1, 1, Point.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(colorEClass, Color.class, "Color", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getColor_Red(), ecorePackage.getEInt(), "red", null, 1, 1, Color.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getColor_Green(), ecorePackage.getEInt(), "green", null, 1, 1, Color.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getColor_Blue(), ecorePackage.getEInt(), "blue", null, 1, 1, Color.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(lineStyleEEnum, LineStyle.class, "LineStyle");
		addEEnumLiteral(lineStyleEEnum, LineStyle.SOLID);
		addEEnumLiteral(lineStyleEEnum, LineStyle.DASH);
		addEEnumLiteral(lineStyleEEnum, LineStyle.DASHDOT);
		addEEnumLiteral(lineStyleEEnum, LineStyle.DASHDOTDOT);
		addEEnumLiteral(lineStyleEEnum, LineStyle.DOT);
		addEEnumLiteral(lineStyleEEnum, LineStyle.UNSPECIFIED);

		initEEnum(orientationEEnum, Orientation.class, "Orientation");
		addEEnumLiteral(orientationEEnum, Orientation.ALIGNMENT_CENTER);
		addEEnumLiteral(orientationEEnum, Orientation.ALIGNMENT_LEFT);
		addEEnumLiteral(orientationEEnum, Orientation.ALIGNMENT_TOP);
		addEEnumLiteral(orientationEEnum, Orientation.ALIGNMENT_RIGHT);
		addEEnumLiteral(orientationEEnum, Orientation.ALIGNMENT_BOTTOM);
		addEEnumLiteral(orientationEEnum, Orientation.ALIGNMENT_MIDDLE);
		addEEnumLiteral(orientationEEnum, Orientation.UNSPECIFIED);

		initEEnum(locationTypeEEnum, LocationType.class, "LocationType");
		addEEnumLiteral(locationTypeEEnum, LocationType.LOCATION_TYPE_RELATIVE);
		addEEnumLiteral(locationTypeEEnum, LocationType.LOCATION_TYPE_ABSOLUTE_START);
		addEEnumLiteral(locationTypeEEnum, LocationType.LOCATION_TYPE_ABSOLUTE_END);
	}

} //StylesPackageImpl
