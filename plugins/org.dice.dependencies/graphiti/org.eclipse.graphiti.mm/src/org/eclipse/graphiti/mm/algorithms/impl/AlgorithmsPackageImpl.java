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
package org.eclipse.graphiti.mm.algorithms.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.graphiti.mm.MmPackage;

import org.eclipse.graphiti.mm.algorithms.AbstractText;
import org.eclipse.graphiti.mm.algorithms.AlgorithmsFactory;
import org.eclipse.graphiti.mm.algorithms.AlgorithmsPackage;
import org.eclipse.graphiti.mm.algorithms.Ellipse;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Image;
import org.eclipse.graphiti.mm.algorithms.MultiText;
import org.eclipse.graphiti.mm.algorithms.PlatformGraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Polygon;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.algorithms.RoundedRectangle;
import org.eclipse.graphiti.mm.algorithms.Text;

import org.eclipse.graphiti.mm.algorithms.styles.StylesPackage;

import org.eclipse.graphiti.mm.algorithms.styles.impl.StylesPackageImpl;

import org.eclipse.graphiti.mm.impl.MmPackageImpl;

import org.eclipse.graphiti.mm.pictograms.PictogramsPackage;

import org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class AlgorithmsPackageImpl extends EPackageImpl implements AlgorithmsPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass graphicsAlgorithmEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass polylineEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ellipseEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass textEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass polygonEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass rectangleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass roundedRectangleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass imageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass platformGraphicsAlgorithmEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractTextEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass multiTextEClass = null;

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
	 * @see org.eclipse.graphiti.mm.algorithms.AlgorithmsPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private AlgorithmsPackageImpl() {
		super(eNS_URI, AlgorithmsFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link AlgorithmsPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static AlgorithmsPackage init() {
		if (isInited) return (AlgorithmsPackage)EPackage.Registry.INSTANCE.getEPackage(AlgorithmsPackage.eNS_URI);

		// Obtain or create and register package
		AlgorithmsPackageImpl theAlgorithmsPackage = (AlgorithmsPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof AlgorithmsPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new AlgorithmsPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		MmPackageImpl theMmPackage = (MmPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(MmPackage.eNS_URI) instanceof MmPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(MmPackage.eNS_URI) : MmPackage.eINSTANCE);
		PictogramsPackageImpl thePictogramsPackage = (PictogramsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(PictogramsPackage.eNS_URI) instanceof PictogramsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(PictogramsPackage.eNS_URI) : PictogramsPackage.eINSTANCE);
		StylesPackageImpl theStylesPackage = (StylesPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(StylesPackage.eNS_URI) instanceof StylesPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(StylesPackage.eNS_URI) : StylesPackage.eINSTANCE);

		// Create package meta-data objects
		theAlgorithmsPackage.createPackageContents();
		theMmPackage.createPackageContents();
		thePictogramsPackage.createPackageContents();
		theStylesPackage.createPackageContents();

		// Initialize created meta-data
		theAlgorithmsPackage.initializePackageContents();
		theMmPackage.initializePackageContents();
		thePictogramsPackage.initializePackageContents();
		theStylesPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theAlgorithmsPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(AlgorithmsPackage.eNS_URI, theAlgorithmsPackage);
		return theAlgorithmsPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGraphicsAlgorithm() {
		return graphicsAlgorithmEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGraphicsAlgorithm_GraphicsAlgorithmChildren() {
		return (EReference)graphicsAlgorithmEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGraphicsAlgorithm_ParentGraphicsAlgorithm() {
		return (EReference)graphicsAlgorithmEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGraphicsAlgorithm_PictogramElement() {
		return (EReference)graphicsAlgorithmEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGraphicsAlgorithm_Width() {
		return (EAttribute)graphicsAlgorithmEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGraphicsAlgorithm_Height() {
		return (EAttribute)graphicsAlgorithmEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGraphicsAlgorithm_X() {
		return (EAttribute)graphicsAlgorithmEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGraphicsAlgorithm_Y() {
		return (EAttribute)graphicsAlgorithmEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGraphicsAlgorithm_Style() {
		return (EReference)graphicsAlgorithmEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPolyline() {
		return polylineEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPolyline_Points() {
		return (EReference)polylineEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEllipse() {
		return ellipseEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getText() {
		return textEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPolygon() {
		return polygonEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRectangle() {
		return rectangleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRoundedRectangle() {
		return roundedRectangleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRoundedRectangle_CornerHeight() {
		return (EAttribute)roundedRectangleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRoundedRectangle_CornerWidth() {
		return (EAttribute)roundedRectangleEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getImage() {
		return imageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getImage_Id() {
		return (EAttribute)imageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getImage_StretchH() {
		return (EAttribute)imageEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getImage_StretchV() {
		return (EAttribute)imageEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getImage_Proportional() {
		return (EAttribute)imageEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPlatformGraphicsAlgorithm() {
		return platformGraphicsAlgorithmEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPlatformGraphicsAlgorithm_Id() {
		return (EAttribute)platformGraphicsAlgorithmEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAbstractText() {
		return abstractTextEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractText_Font() {
		return (EReference)abstractTextEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractText_HorizontalAlignment() {
		return (EAttribute)abstractTextEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractText_VerticalAlignment() {
		return (EAttribute)abstractTextEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractText_Angle() {
		return (EAttribute)abstractTextEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractText_Value() {
		return (EAttribute)abstractTextEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMultiText() {
		return multiTextEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AlgorithmsFactory getAlgorithmsFactory() {
		return (AlgorithmsFactory)getEFactoryInstance();
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
		graphicsAlgorithmEClass = createEClass(GRAPHICS_ALGORITHM);
		createEReference(graphicsAlgorithmEClass, GRAPHICS_ALGORITHM__GRAPHICS_ALGORITHM_CHILDREN);
		createEReference(graphicsAlgorithmEClass, GRAPHICS_ALGORITHM__PARENT_GRAPHICS_ALGORITHM);
		createEReference(graphicsAlgorithmEClass, GRAPHICS_ALGORITHM__PICTOGRAM_ELEMENT);
		createEAttribute(graphicsAlgorithmEClass, GRAPHICS_ALGORITHM__WIDTH);
		createEAttribute(graphicsAlgorithmEClass, GRAPHICS_ALGORITHM__HEIGHT);
		createEAttribute(graphicsAlgorithmEClass, GRAPHICS_ALGORITHM__X);
		createEAttribute(graphicsAlgorithmEClass, GRAPHICS_ALGORITHM__Y);
		createEReference(graphicsAlgorithmEClass, GRAPHICS_ALGORITHM__STYLE);

		polylineEClass = createEClass(POLYLINE);
		createEReference(polylineEClass, POLYLINE__POINTS);

		ellipseEClass = createEClass(ELLIPSE);

		textEClass = createEClass(TEXT);

		polygonEClass = createEClass(POLYGON);

		rectangleEClass = createEClass(RECTANGLE);

		roundedRectangleEClass = createEClass(ROUNDED_RECTANGLE);
		createEAttribute(roundedRectangleEClass, ROUNDED_RECTANGLE__CORNER_HEIGHT);
		createEAttribute(roundedRectangleEClass, ROUNDED_RECTANGLE__CORNER_WIDTH);

		imageEClass = createEClass(IMAGE);
		createEAttribute(imageEClass, IMAGE__ID);
		createEAttribute(imageEClass, IMAGE__STRETCH_H);
		createEAttribute(imageEClass, IMAGE__STRETCH_V);
		createEAttribute(imageEClass, IMAGE__PROPORTIONAL);

		platformGraphicsAlgorithmEClass = createEClass(PLATFORM_GRAPHICS_ALGORITHM);
		createEAttribute(platformGraphicsAlgorithmEClass, PLATFORM_GRAPHICS_ALGORITHM__ID);

		abstractTextEClass = createEClass(ABSTRACT_TEXT);
		createEReference(abstractTextEClass, ABSTRACT_TEXT__FONT);
		createEAttribute(abstractTextEClass, ABSTRACT_TEXT__HORIZONTAL_ALIGNMENT);
		createEAttribute(abstractTextEClass, ABSTRACT_TEXT__VERTICAL_ALIGNMENT);
		createEAttribute(abstractTextEClass, ABSTRACT_TEXT__ANGLE);
		createEAttribute(abstractTextEClass, ABSTRACT_TEXT__VALUE);

		multiTextEClass = createEClass(MULTI_TEXT);
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
		StylesPackage theStylesPackage = (StylesPackage)EPackage.Registry.INSTANCE.getEPackage(StylesPackage.eNS_URI);
		MmPackage theMmPackage = (MmPackage)EPackage.Registry.INSTANCE.getEPackage(MmPackage.eNS_URI);
		PictogramsPackage thePictogramsPackage = (PictogramsPackage)EPackage.Registry.INSTANCE.getEPackage(PictogramsPackage.eNS_URI);

		// Add subpackages
		getESubpackages().add(theStylesPackage);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		graphicsAlgorithmEClass.getESuperTypes().add(theMmPackage.getGraphicsAlgorithmContainer());
		graphicsAlgorithmEClass.getESuperTypes().add(theStylesPackage.getAbstractStyle());
		polylineEClass.getESuperTypes().add(this.getGraphicsAlgorithm());
		ellipseEClass.getESuperTypes().add(this.getGraphicsAlgorithm());
		textEClass.getESuperTypes().add(this.getAbstractText());
		polygonEClass.getESuperTypes().add(this.getPolyline());
		rectangleEClass.getESuperTypes().add(this.getGraphicsAlgorithm());
		roundedRectangleEClass.getESuperTypes().add(this.getGraphicsAlgorithm());
		imageEClass.getESuperTypes().add(this.getGraphicsAlgorithm());
		platformGraphicsAlgorithmEClass.getESuperTypes().add(this.getGraphicsAlgorithm());
		abstractTextEClass.getESuperTypes().add(this.getGraphicsAlgorithm());
		multiTextEClass.getESuperTypes().add(this.getAbstractText());

		// Initialize classes and features; add operations and parameters
		initEClass(graphicsAlgorithmEClass, GraphicsAlgorithm.class, "GraphicsAlgorithm", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getGraphicsAlgorithm_GraphicsAlgorithmChildren(), this.getGraphicsAlgorithm(), this.getGraphicsAlgorithm_ParentGraphicsAlgorithm(), "graphicsAlgorithmChildren", null, 0, -1, GraphicsAlgorithm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGraphicsAlgorithm_ParentGraphicsAlgorithm(), this.getGraphicsAlgorithm(), this.getGraphicsAlgorithm_GraphicsAlgorithmChildren(), "parentGraphicsAlgorithm", null, 0, 1, GraphicsAlgorithm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getGraphicsAlgorithm_PictogramElement(), thePictogramsPackage.getPictogramElement(), thePictogramsPackage.getPictogramElement_GraphicsAlgorithm(), "pictogramElement", null, 0, 1, GraphicsAlgorithm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getGraphicsAlgorithm_Width(), ecorePackage.getEInt(), "width", null, 1, 1, GraphicsAlgorithm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getGraphicsAlgorithm_Height(), ecorePackage.getEInt(), "height", null, 1, 1, GraphicsAlgorithm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getGraphicsAlgorithm_X(), ecorePackage.getEInt(), "x", null, 1, 1, GraphicsAlgorithm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getGraphicsAlgorithm_Y(), ecorePackage.getEInt(), "y", null, 1, 1, GraphicsAlgorithm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getGraphicsAlgorithm_Style(), theStylesPackage.getStyle(), null, "style", null, 0, 1, GraphicsAlgorithm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(polylineEClass, Polyline.class, "Polyline", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPolyline_Points(), theStylesPackage.getPoint(), null, "points", null, 0, -1, Polyline.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(ellipseEClass, Ellipse.class, "Ellipse", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(textEClass, Text.class, "Text", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(polygonEClass, Polygon.class, "Polygon", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(rectangleEClass, Rectangle.class, "Rectangle", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(roundedRectangleEClass, RoundedRectangle.class, "RoundedRectangle", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRoundedRectangle_CornerHeight(), ecorePackage.getEInt(), "cornerHeight", null, 1, 1, RoundedRectangle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getRoundedRectangle_CornerWidth(), ecorePackage.getEInt(), "cornerWidth", null, 1, 1, RoundedRectangle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(imageEClass, Image.class, "Image", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getImage_Id(), ecorePackage.getEString(), "id", null, 1, 1, Image.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getImage_StretchH(), ecorePackage.getEBooleanObject(), "stretchH", null, 0, 1, Image.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getImage_StretchV(), ecorePackage.getEBooleanObject(), "stretchV", null, 0, 1, Image.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getImage_Proportional(), ecorePackage.getEBooleanObject(), "proportional", null, 0, 1, Image.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(platformGraphicsAlgorithmEClass, PlatformGraphicsAlgorithm.class, "PlatformGraphicsAlgorithm", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPlatformGraphicsAlgorithm_Id(), ecorePackage.getEString(), "id", null, 1, 1, PlatformGraphicsAlgorithm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(abstractTextEClass, AbstractText.class, "AbstractText", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAbstractText_Font(), theStylesPackage.getFont(), null, "font", null, 0, 1, AbstractText.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getAbstractText_HorizontalAlignment(), theStylesPackage.getOrientation(), "horizontalAlignment", "ALIGNMENT_LEFT", 0, 1, AbstractText.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getAbstractText_VerticalAlignment(), theStylesPackage.getOrientation(), "verticalAlignment", "ALIGNMENT_CENTER", 0, 1, AbstractText.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getAbstractText_Angle(), ecorePackage.getEIntegerObject(), "angle", "0", 0, 1, AbstractText.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getAbstractText_Value(), ecorePackage.getEString(), "value", null, 1, 1, AbstractText.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(multiTextEClass, MultiText.class, "MultiText", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
	}

} //AlgorithmsPackageImpl
