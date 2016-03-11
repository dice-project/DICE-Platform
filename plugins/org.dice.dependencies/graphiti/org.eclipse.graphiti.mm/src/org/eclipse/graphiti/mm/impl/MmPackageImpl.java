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
package org.eclipse.graphiti.mm.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.graphiti.mm.GraphicsAlgorithmContainer;
import org.eclipse.graphiti.mm.MmFactory;
import org.eclipse.graphiti.mm.MmPackage;
import org.eclipse.graphiti.mm.Property;
import org.eclipse.graphiti.mm.PropertyContainer;
import org.eclipse.graphiti.mm.StyleContainer;

import org.eclipse.graphiti.mm.algorithms.AlgorithmsPackage;

import org.eclipse.graphiti.mm.algorithms.impl.AlgorithmsPackageImpl;

import org.eclipse.graphiti.mm.algorithms.styles.StylesPackage;

import org.eclipse.graphiti.mm.algorithms.styles.impl.StylesPackageImpl;

import org.eclipse.graphiti.mm.pictograms.PictogramsPackage;

import org.eclipse.graphiti.mm.pictograms.impl.PictogramsPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class MmPackageImpl extends EPackageImpl implements MmPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass propertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass graphicsAlgorithmContainerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass propertyContainerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass styleContainerEClass = null;

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
	 * @see org.eclipse.graphiti.mm.MmPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private MmPackageImpl() {
		super(eNS_URI, MmFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link MmPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static MmPackage init() {
		if (isInited) return (MmPackage)EPackage.Registry.INSTANCE.getEPackage(MmPackage.eNS_URI);

		// Obtain or create and register package
		MmPackageImpl theMmPackage = (MmPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof MmPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new MmPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		PictogramsPackageImpl thePictogramsPackage = (PictogramsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(PictogramsPackage.eNS_URI) instanceof PictogramsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(PictogramsPackage.eNS_URI) : PictogramsPackage.eINSTANCE);
		AlgorithmsPackageImpl theAlgorithmsPackage = (AlgorithmsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(AlgorithmsPackage.eNS_URI) instanceof AlgorithmsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(AlgorithmsPackage.eNS_URI) : AlgorithmsPackage.eINSTANCE);
		StylesPackageImpl theStylesPackage = (StylesPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(StylesPackage.eNS_URI) instanceof StylesPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(StylesPackage.eNS_URI) : StylesPackage.eINSTANCE);

		// Create package meta-data objects
		theMmPackage.createPackageContents();
		thePictogramsPackage.createPackageContents();
		theAlgorithmsPackage.createPackageContents();
		theStylesPackage.createPackageContents();

		// Initialize created meta-data
		theMmPackage.initializePackageContents();
		thePictogramsPackage.initializePackageContents();
		theAlgorithmsPackage.initializePackageContents();
		theStylesPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theMmPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(MmPackage.eNS_URI, theMmPackage);
		return theMmPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProperty() {
		return propertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProperty_Key() {
		return (EAttribute)propertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProperty_Value() {
		return (EAttribute)propertyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGraphicsAlgorithmContainer() {
		return graphicsAlgorithmContainerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPropertyContainer() {
		return propertyContainerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPropertyContainer_Properties() {
		return (EReference)propertyContainerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStyleContainer() {
		return styleContainerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStyleContainer_Styles() {
		return (EReference)styleContainerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MmFactory getMmFactory() {
		return (MmFactory)getEFactoryInstance();
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
		propertyEClass = createEClass(PROPERTY);
		createEAttribute(propertyEClass, PROPERTY__KEY);
		createEAttribute(propertyEClass, PROPERTY__VALUE);

		graphicsAlgorithmContainerEClass = createEClass(GRAPHICS_ALGORITHM_CONTAINER);

		propertyContainerEClass = createEClass(PROPERTY_CONTAINER);
		createEReference(propertyContainerEClass, PROPERTY_CONTAINER__PROPERTIES);

		styleContainerEClass = createEClass(STYLE_CONTAINER);
		createEReference(styleContainerEClass, STYLE_CONTAINER__STYLES);
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
		PictogramsPackage thePictogramsPackage = (PictogramsPackage)EPackage.Registry.INSTANCE.getEPackage(PictogramsPackage.eNS_URI);
		AlgorithmsPackage theAlgorithmsPackage = (AlgorithmsPackage)EPackage.Registry.INSTANCE.getEPackage(AlgorithmsPackage.eNS_URI);
		StylesPackage theStylesPackage = (StylesPackage)EPackage.Registry.INSTANCE.getEPackage(StylesPackage.eNS_URI);

		// Add subpackages
		getESubpackages().add(thePictogramsPackage);
		getESubpackages().add(theAlgorithmsPackage);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		graphicsAlgorithmContainerEClass.getESuperTypes().add(this.getPropertyContainer());

		// Initialize classes and features; add operations and parameters
		initEClass(propertyEClass, Property.class, "Property", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getProperty_Key(), ecorePackage.getEString(), "key", null, 1, 1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProperty_Value(), ecorePackage.getEString(), "value", null, 1, 1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(graphicsAlgorithmContainerEClass, GraphicsAlgorithmContainer.class, "GraphicsAlgorithmContainer", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(propertyContainerEClass, PropertyContainer.class, "PropertyContainer", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPropertyContainer_Properties(), this.getProperty(), null, "properties", null, 0, -1, PropertyContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(styleContainerEClass, StyleContainer.class, "StyleContainer", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStyleContainer_Styles(), theStylesPackage.getStyle(), theStylesPackage.getStyle_StyleContainer(), "styles", null, 0, -1, StyleContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //MmPackageImpl
