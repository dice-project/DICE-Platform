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
package org.eclipse.epf.uma;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

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
 * <!-- begin-model-doc -->
 * 06
 * 05
 * 04
 * 03
 * 01
 * 02
 * <!-- end-model-doc -->
 * @see org.eclipse.epf.uma.UmaFactory
 * @model kind="package"
 * @generated
 */
public interface UmaPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "uma";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/epf/uma/1.0.6/uma.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.eclipse.epf.uma";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	UmaPackage eINSTANCE = org.eclipse.epf.uma.impl.UmaPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.ElementImpl <em>Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.ElementImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getElement()
	 * @generated
	 */
	int ELEMENT = 4;

	/**
	 * The number of structural features of the '<em>Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.NamedElementImpl <em>Named Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.NamedElementImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getNamedElement()
	 * @generated
	 */
	int NAMED_ELEMENT = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT__NAME = ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Named Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT_FEATURE_COUNT = ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.PackageableElementImpl <em>Packageable Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.PackageableElementImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getPackageableElement()
	 * @generated
	 */
	int PACKAGEABLE_ELEMENT = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGEABLE_ELEMENT__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The number of structural features of the '<em>Packageable Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGEABLE_ELEMENT_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.TypeImpl <em>Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.TypeImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getType()
	 * @generated
	 */
	int TYPE = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE__NAME = PACKAGEABLE_ELEMENT__NAME;

	/**
	 * The number of structural features of the '<em>Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_FEATURE_COUNT = PACKAGEABLE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.ClassifierImpl <em>Classifier</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.ClassifierImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getClassifier()
	 * @generated
	 */
	int CLASSIFIER = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER__NAME = TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER__IS_ABSTRACT = TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Classifier</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER_FEATURE_COUNT = TYPE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.NamespaceImpl <em>Namespace</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.NamespaceImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getNamespace()
	 * @generated
	 */
	int NAMESPACE = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMESPACE__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The number of structural features of the '<em>Namespace</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMESPACE_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.PackageImpl <em>Package</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.PackageImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getPackage()
	 * @generated
	 */
	int PACKAGE = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE__NAME = NAMESPACE__NAME;

	/**
	 * The number of structural features of the '<em>Package</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_FEATURE_COUNT = NAMESPACE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.MethodElementImpl <em>Method Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.MethodElementImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getMethodElement()
	 * @generated
	 */
	int METHOD_ELEMENT = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_ELEMENT__NAME = PACKAGEABLE_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_ELEMENT__GUID = PACKAGEABLE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_ELEMENT__PRESENTATION_NAME = PACKAGEABLE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_ELEMENT__BRIEF_DESCRIPTION = PACKAGEABLE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_ELEMENT__OWNED_RULES = PACKAGEABLE_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_ELEMENT__METHOD_ELEMENT_PROPERTY = PACKAGEABLE_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_ELEMENT__KIND = PACKAGEABLE_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_ELEMENT__SUPPRESSED = PACKAGEABLE_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_ELEMENT__ORDERING_GUIDE = PACKAGEABLE_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The number of structural features of the '<em>Method Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_ELEMENT_FEATURE_COUNT = PACKAGEABLE_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.ConstraintImpl <em>Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.ConstraintImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getConstraint()
	 * @generated
	 */
	int CONSTRAINT = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT__NAME = METHOD_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT__GUID = METHOD_ELEMENT__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT__PRESENTATION_NAME = METHOD_ELEMENT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT__BRIEF_DESCRIPTION = METHOD_ELEMENT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT__OWNED_RULES = METHOD_ELEMENT__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT__METHOD_ELEMENT_PROPERTY = METHOD_ELEMENT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT__KIND = METHOD_ELEMENT__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT__SUPPRESSED = METHOD_ELEMENT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT__ORDERING_GUIDE = METHOD_ELEMENT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Body</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT__BODY = METHOD_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_FEATURE_COUNT = METHOD_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.MethodElementPropertyImpl <em>Method Element Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.MethodElementPropertyImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getMethodElementProperty()
	 * @generated
	 */
	int METHOD_ELEMENT_PROPERTY = 9;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_ELEMENT_PROPERTY__NAME = PACKAGEABLE_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_ELEMENT_PROPERTY__VALUE = PACKAGEABLE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Method Element Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_ELEMENT_PROPERTY_FEATURE_COUNT = PACKAGEABLE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.DescribableElementImpl <em>Describable Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.DescribableElementImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getDescribableElement()
	 * @generated
	 */
	int DESCRIBABLE_ELEMENT = 12;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIBABLE_ELEMENT__NAME = METHOD_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIBABLE_ELEMENT__GUID = METHOD_ELEMENT__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIBABLE_ELEMENT__PRESENTATION_NAME = METHOD_ELEMENT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIBABLE_ELEMENT__BRIEF_DESCRIPTION = METHOD_ELEMENT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIBABLE_ELEMENT__OWNED_RULES = METHOD_ELEMENT__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIBABLE_ELEMENT__METHOD_ELEMENT_PROPERTY = METHOD_ELEMENT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIBABLE_ELEMENT__KIND = METHOD_ELEMENT__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIBABLE_ELEMENT__SUPPRESSED = METHOD_ELEMENT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIBABLE_ELEMENT__ORDERING_GUIDE = METHOD_ELEMENT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIBABLE_ELEMENT__IS_ABSTRACT = METHOD_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIBABLE_ELEMENT__PRESENTATION = METHOD_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIBABLE_ELEMENT__SHAPEICON = METHOD_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIBABLE_ELEMENT__NODEICON = METHOD_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Describable Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIBABLE_ELEMENT_FEATURE_COUNT = METHOD_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.VariabilityElementImpl <em>Variability Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.VariabilityElementImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getVariabilityElement()
	 * @generated
	 */
	int VARIABILITY_ELEMENT = 18;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.ContentElementImpl <em>Content Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.ContentElementImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getContentElement()
	 * @generated
	 */
	int CONTENT_ELEMENT = 11;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ELEMENT__NAME = DESCRIBABLE_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ELEMENT__GUID = DESCRIBABLE_ELEMENT__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ELEMENT__PRESENTATION_NAME = DESCRIBABLE_ELEMENT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ELEMENT__BRIEF_DESCRIPTION = DESCRIBABLE_ELEMENT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ELEMENT__OWNED_RULES = DESCRIBABLE_ELEMENT__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ELEMENT__METHOD_ELEMENT_PROPERTY = DESCRIBABLE_ELEMENT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ELEMENT__KIND = DESCRIBABLE_ELEMENT__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ELEMENT__SUPPRESSED = DESCRIBABLE_ELEMENT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ELEMENT__ORDERING_GUIDE = DESCRIBABLE_ELEMENT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ELEMENT__IS_ABSTRACT = DESCRIBABLE_ELEMENT__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ELEMENT__PRESENTATION = DESCRIBABLE_ELEMENT__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ELEMENT__SHAPEICON = DESCRIBABLE_ELEMENT__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ELEMENT__NODEICON = DESCRIBABLE_ELEMENT__NODEICON;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ELEMENT__VARIABILITY_TYPE = DESCRIBABLE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ELEMENT__VARIABILITY_BASED_ON_ELEMENT = DESCRIBABLE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Supporting Materials</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ELEMENT__SUPPORTING_MATERIALS = DESCRIBABLE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Concepts And Papers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ELEMENT__CONCEPTS_AND_PAPERS = DESCRIBABLE_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Checklists</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ELEMENT__CHECKLISTS = DESCRIBABLE_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Guidelines</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ELEMENT__GUIDELINES = DESCRIBABLE_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Examples</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ELEMENT__EXAMPLES = DESCRIBABLE_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Assets</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ELEMENT__ASSETS = DESCRIBABLE_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Termdefinition</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ELEMENT__TERMDEFINITION = DESCRIBABLE_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The number of structural features of the '<em>Content Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ELEMENT_FEATURE_COUNT = DESCRIBABLE_ELEMENT_FEATURE_COUNT + 9;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.KindImpl <em>Kind</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.KindImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getKind()
	 * @generated
	 */
	int KIND = 10;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KIND__NAME = CONTENT_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KIND__GUID = CONTENT_ELEMENT__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KIND__PRESENTATION_NAME = CONTENT_ELEMENT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KIND__BRIEF_DESCRIPTION = CONTENT_ELEMENT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KIND__OWNED_RULES = CONTENT_ELEMENT__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KIND__METHOD_ELEMENT_PROPERTY = CONTENT_ELEMENT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KIND__KIND = CONTENT_ELEMENT__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KIND__SUPPRESSED = CONTENT_ELEMENT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KIND__ORDERING_GUIDE = CONTENT_ELEMENT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KIND__IS_ABSTRACT = CONTENT_ELEMENT__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KIND__PRESENTATION = CONTENT_ELEMENT__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KIND__SHAPEICON = CONTENT_ELEMENT__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KIND__NODEICON = CONTENT_ELEMENT__NODEICON;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KIND__VARIABILITY_TYPE = CONTENT_ELEMENT__VARIABILITY_TYPE;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KIND__VARIABILITY_BASED_ON_ELEMENT = CONTENT_ELEMENT__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Supporting Materials</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KIND__SUPPORTING_MATERIALS = CONTENT_ELEMENT__SUPPORTING_MATERIALS;

	/**
	 * The feature id for the '<em><b>Concepts And Papers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KIND__CONCEPTS_AND_PAPERS = CONTENT_ELEMENT__CONCEPTS_AND_PAPERS;

	/**
	 * The feature id for the '<em><b>Checklists</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KIND__CHECKLISTS = CONTENT_ELEMENT__CHECKLISTS;

	/**
	 * The feature id for the '<em><b>Guidelines</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KIND__GUIDELINES = CONTENT_ELEMENT__GUIDELINES;

	/**
	 * The feature id for the '<em><b>Examples</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KIND__EXAMPLES = CONTENT_ELEMENT__EXAMPLES;

	/**
	 * The feature id for the '<em><b>Assets</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KIND__ASSETS = CONTENT_ELEMENT__ASSETS;

	/**
	 * The feature id for the '<em><b>Termdefinition</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KIND__TERMDEFINITION = CONTENT_ELEMENT__TERMDEFINITION;

	/**
	 * The feature id for the '<em><b>Applicable Meta Class Info</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KIND__APPLICABLE_META_CLASS_INFO = CONTENT_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Kind</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KIND_FEATURE_COUNT = CONTENT_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.MethodUnitImpl <em>Method Unit</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.MethodUnitImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getMethodUnit()
	 * @generated
	 */
	int METHOD_UNIT = 14;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_UNIT__NAME = METHOD_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_UNIT__GUID = METHOD_ELEMENT__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_UNIT__PRESENTATION_NAME = METHOD_ELEMENT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_UNIT__BRIEF_DESCRIPTION = METHOD_ELEMENT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_UNIT__OWNED_RULES = METHOD_ELEMENT__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_UNIT__METHOD_ELEMENT_PROPERTY = METHOD_ELEMENT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_UNIT__KIND = METHOD_ELEMENT__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_UNIT__SUPPRESSED = METHOD_ELEMENT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_UNIT__ORDERING_GUIDE = METHOD_ELEMENT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Authors</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_UNIT__AUTHORS = METHOD_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Change Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_UNIT__CHANGE_DATE = METHOD_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Change Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_UNIT__CHANGE_DESCRIPTION = METHOD_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_UNIT__VERSION = METHOD_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Copyright Statement</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_UNIT__COPYRIGHT_STATEMENT = METHOD_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Method Unit</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_UNIT_FEATURE_COUNT = METHOD_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.ContentDescriptionImpl <em>Content Description</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.ContentDescriptionImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getContentDescription()
	 * @generated
	 */
	int CONTENT_DESCRIPTION = 13;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_DESCRIPTION__NAME = METHOD_UNIT__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_DESCRIPTION__GUID = METHOD_UNIT__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_DESCRIPTION__PRESENTATION_NAME = METHOD_UNIT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_DESCRIPTION__BRIEF_DESCRIPTION = METHOD_UNIT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_DESCRIPTION__OWNED_RULES = METHOD_UNIT__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_DESCRIPTION__METHOD_ELEMENT_PROPERTY = METHOD_UNIT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_DESCRIPTION__KIND = METHOD_UNIT__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_DESCRIPTION__SUPPRESSED = METHOD_UNIT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_DESCRIPTION__ORDERING_GUIDE = METHOD_UNIT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Authors</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_DESCRIPTION__AUTHORS = METHOD_UNIT__AUTHORS;

	/**
	 * The feature id for the '<em><b>Change Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_DESCRIPTION__CHANGE_DATE = METHOD_UNIT__CHANGE_DATE;

	/**
	 * The feature id for the '<em><b>Change Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_DESCRIPTION__CHANGE_DESCRIPTION = METHOD_UNIT__CHANGE_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_DESCRIPTION__VERSION = METHOD_UNIT__VERSION;

	/**
	 * The feature id for the '<em><b>Copyright Statement</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_DESCRIPTION__COPYRIGHT_STATEMENT = METHOD_UNIT__COPYRIGHT_STATEMENT;

	/**
	 * The feature id for the '<em><b>Main Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_DESCRIPTION__MAIN_DESCRIPTION = METHOD_UNIT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Sections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_DESCRIPTION__SECTIONS = METHOD_UNIT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>External Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_DESCRIPTION__EXTERNAL_ID = METHOD_UNIT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Key Considerations</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_DESCRIPTION__KEY_CONSIDERATIONS = METHOD_UNIT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Long Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_DESCRIPTION__LONG_PRESENTATION_NAME = METHOD_UNIT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Content Description</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_DESCRIPTION_FEATURE_COUNT = METHOD_UNIT_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.SectionImpl <em>Section</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.SectionImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getSection()
	 * @generated
	 */
	int SECTION = 17;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.RoleImpl <em>Role</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.RoleImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getRole()
	 * @generated
	 */
	int ROLE = 39;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.WorkProductImpl <em>Work Product</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.WorkProductImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getWorkProduct()
	 * @generated
	 */
	int WORK_PRODUCT = 27;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.TaskImpl <em>Task</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.TaskImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getTask()
	 * @generated
	 */
	int TASK = 38;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.WorkDefinitionImpl <em>Work Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.WorkDefinitionImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getWorkDefinition()
	 * @generated
	 */
	int WORK_DEFINITION = 36;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.StepImpl <em>Step</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.StepImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getStep()
	 * @generated
	 */
	int STEP = 35;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.GuidanceImpl <em>Guidance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.GuidanceImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getGuidance()
	 * @generated
	 */
	int GUIDANCE = 16;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE__NAME = CONTENT_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE__GUID = CONTENT_ELEMENT__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE__PRESENTATION_NAME = CONTENT_ELEMENT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE__BRIEF_DESCRIPTION = CONTENT_ELEMENT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE__OWNED_RULES = CONTENT_ELEMENT__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE__METHOD_ELEMENT_PROPERTY = CONTENT_ELEMENT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE__KIND = CONTENT_ELEMENT__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE__SUPPRESSED = CONTENT_ELEMENT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE__ORDERING_GUIDE = CONTENT_ELEMENT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE__IS_ABSTRACT = CONTENT_ELEMENT__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE__PRESENTATION = CONTENT_ELEMENT__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE__SHAPEICON = CONTENT_ELEMENT__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE__NODEICON = CONTENT_ELEMENT__NODEICON;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE__VARIABILITY_TYPE = CONTENT_ELEMENT__VARIABILITY_TYPE;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE__VARIABILITY_BASED_ON_ELEMENT = CONTENT_ELEMENT__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Supporting Materials</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE__SUPPORTING_MATERIALS = CONTENT_ELEMENT__SUPPORTING_MATERIALS;

	/**
	 * The feature id for the '<em><b>Concepts And Papers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE__CONCEPTS_AND_PAPERS = CONTENT_ELEMENT__CONCEPTS_AND_PAPERS;

	/**
	 * The feature id for the '<em><b>Checklists</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE__CHECKLISTS = CONTENT_ELEMENT__CHECKLISTS;

	/**
	 * The feature id for the '<em><b>Guidelines</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE__GUIDELINES = CONTENT_ELEMENT__GUIDELINES;

	/**
	 * The feature id for the '<em><b>Examples</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE__EXAMPLES = CONTENT_ELEMENT__EXAMPLES;

	/**
	 * The feature id for the '<em><b>Assets</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE__ASSETS = CONTENT_ELEMENT__ASSETS;

	/**
	 * The feature id for the '<em><b>Termdefinition</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE__TERMDEFINITION = CONTENT_ELEMENT__TERMDEFINITION;

	/**
	 * The number of structural features of the '<em>Guidance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE_FEATURE_COUNT = CONTENT_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.ArtifactImpl <em>Artifact</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.ArtifactImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getArtifact()
	 * @generated
	 */
	int ARTIFACT = 26;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.FulfillableElementImpl <em>Fulfillable Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.FulfillableElementImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getFulfillableElement()
	 * @generated
	 */
	int FULFILLABLE_ELEMENT = 28;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.DeliverableImpl <em>Deliverable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.DeliverableImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getDeliverable()
	 * @generated
	 */
	int DELIVERABLE = 33;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.OutcomeImpl <em>Outcome</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.OutcomeImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getOutcome()
	 * @generated
	 */
	int OUTCOME = 34;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.MethodPackageImpl <em>Method Package</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.MethodPackageImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getMethodPackage()
	 * @generated
	 */
	int METHOD_PACKAGE = 63;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.ContentPackageImpl <em>Content Package</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.ContentPackageImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getContentPackage()
	 * @generated
	 */
	int CONTENT_PACKAGE = 64;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.WorkProductDescriptionImpl <em>Work Product Description</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.WorkProductDescriptionImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getWorkProductDescription()
	 * @generated
	 */
	int WORK_PRODUCT_DESCRIPTION = 41;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.ArtifactDescriptionImpl <em>Artifact Description</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.ArtifactDescriptionImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getArtifactDescription()
	 * @generated
	 */
	int ARTIFACT_DESCRIPTION = 40;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.DeliverableDescriptionImpl <em>Deliverable Description</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.DeliverableDescriptionImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getDeliverableDescription()
	 * @generated
	 */
	int DELIVERABLE_DESCRIPTION = 42;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.RoleDescriptionImpl <em>Role Description</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.RoleDescriptionImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getRoleDescription()
	 * @generated
	 */
	int ROLE_DESCRIPTION = 43;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.TaskDescriptionImpl <em>Task Description</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.TaskDescriptionImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getTaskDescription()
	 * @generated
	 */
	int TASK_DESCRIPTION = 44;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.GuidanceDescriptionImpl <em>Guidance Description</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.GuidanceDescriptionImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getGuidanceDescription()
	 * @generated
	 */
	int GUIDANCE_DESCRIPTION = 45;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.PracticeDescriptionImpl <em>Practice Description</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.PracticeDescriptionImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getPracticeDescription()
	 * @generated
	 */
	int PRACTICE_DESCRIPTION = 46;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.ProcessElementImpl <em>Process Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.ProcessElementImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getProcessElement()
	 * @generated
	 */
	int PROCESS_ELEMENT = 56;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.BreakdownElementImpl <em>Breakdown Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.BreakdownElementImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getBreakdownElement()
	 * @generated
	 */
	int BREAKDOWN_ELEMENT = 55;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.WorkBreakdownElementImpl <em>Work Breakdown Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.WorkBreakdownElementImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getWorkBreakdownElement()
	 * @generated
	 */
	int WORK_BREAKDOWN_ELEMENT = 54;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.ActivityImpl <em>Activity</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.ActivityImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getActivity()
	 * @generated
	 */
	int ACTIVITY = 53;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.MilestoneImpl <em>Milestone</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.MilestoneImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getMilestone()
	 * @generated
	 */
	int MILESTONE = 65;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.IterationImpl <em>Iteration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.IterationImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getIteration()
	 * @generated
	 */
	int ITERATION = 68;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.PhaseImpl <em>Phase</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.PhaseImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getPhase()
	 * @generated
	 */
	int PHASE = 69;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.TeamProfileImpl <em>Team Profile</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.TeamProfileImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getTeamProfile()
	 * @generated
	 */
	int TEAM_PROFILE = 70;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.DescriptorImpl <em>Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.DescriptorImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getDescriptor()
	 * @generated
	 */
	int DESCRIPTOR = 67;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.RoleDescriptorImpl <em>Role Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.RoleDescriptorImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getRoleDescriptor()
	 * @generated
	 */
	int ROLE_DESCRIPTOR = 71;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.WorkOrderImpl <em>Work Order</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.WorkOrderImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getWorkOrder()
	 * @generated
	 */
	int WORK_ORDER = 58;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.PlanningDataImpl <em>Planning Data</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.PlanningDataImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getPlanningData()
	 * @generated
	 */
	int PLANNING_DATA = 57;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.TaskDescriptorImpl <em>Task Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.TaskDescriptorImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getTaskDescriptor()
	 * @generated
	 */
	int TASK_DESCRIPTOR = 72;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.WorkProductDescriptorImpl <em>Work Product Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.WorkProductDescriptorImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getWorkProductDescriptor()
	 * @generated
	 */
	int WORK_PRODUCT_DESCRIPTOR = 66;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.CompositeRoleImpl <em>Composite Role</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.CompositeRoleImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getCompositeRole()
	 * @generated
	 */
	int COMPOSITE_ROLE = 73;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.BreakdownElementDescriptionImpl <em>Breakdown Element Description</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.BreakdownElementDescriptionImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getBreakdownElementDescription()
	 * @generated
	 */
	int BREAKDOWN_ELEMENT_DESCRIPTION = 81;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.ActivityDescriptionImpl <em>Activity Description</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.ActivityDescriptionImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getActivityDescription()
	 * @generated
	 */
	int ACTIVITY_DESCRIPTION = 82;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.ProcessDescriptionImpl <em>Process Description</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.ProcessDescriptionImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getProcessDescription()
	 * @generated
	 */
	int PROCESS_DESCRIPTION = 84;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.DeliveryProcessDescriptionImpl <em>Delivery Process Description</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.DeliveryProcessDescriptionImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getDeliveryProcessDescription()
	 * @generated
	 */
	int DELIVERY_PROCESS_DESCRIPTION = 83;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.DescriptorDescriptionImpl <em>Descriptor Description</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.DescriptorDescriptionImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getDescriptorDescription()
	 * @generated
	 */
	int DESCRIPTOR_DESCRIPTION = 85;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.ConceptImpl <em>Concept</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.ConceptImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getConcept()
	 * @generated
	 */
	int CONCEPT = 19;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.ChecklistImpl <em>Checklist</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.ChecklistImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getChecklist()
	 * @generated
	 */
	int CHECKLIST = 20;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.ExampleImpl <em>Example</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.ExampleImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getExample()
	 * @generated
	 */
	int EXAMPLE = 22;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.GuidelineImpl <em>Guideline</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.GuidelineImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getGuideline()
	 * @generated
	 */
	int GUIDELINE = 21;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.EstimationConsiderationsImpl <em>Estimation Considerations</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.EstimationConsiderationsImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getEstimationConsiderations()
	 * @generated
	 */
	int ESTIMATION_CONSIDERATIONS = 32;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.ReportImpl <em>Report</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.ReportImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getReport()
	 * @generated
	 */
	int REPORT = 29;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.TemplateImpl <em>Template</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.TemplateImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getTemplate()
	 * @generated
	 */
	int TEMPLATE = 30;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.SupportingMaterialImpl <em>Supporting Material</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.SupportingMaterialImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getSupportingMaterial()
	 * @generated
	 */
	int SUPPORTING_MATERIAL = 15;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPORTING_MATERIAL__NAME = GUIDANCE__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPORTING_MATERIAL__GUID = GUIDANCE__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPORTING_MATERIAL__PRESENTATION_NAME = GUIDANCE__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPORTING_MATERIAL__BRIEF_DESCRIPTION = GUIDANCE__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPORTING_MATERIAL__OWNED_RULES = GUIDANCE__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPORTING_MATERIAL__METHOD_ELEMENT_PROPERTY = GUIDANCE__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPORTING_MATERIAL__KIND = GUIDANCE__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPORTING_MATERIAL__SUPPRESSED = GUIDANCE__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPORTING_MATERIAL__ORDERING_GUIDE = GUIDANCE__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPORTING_MATERIAL__IS_ABSTRACT = GUIDANCE__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPORTING_MATERIAL__PRESENTATION = GUIDANCE__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPORTING_MATERIAL__SHAPEICON = GUIDANCE__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPORTING_MATERIAL__NODEICON = GUIDANCE__NODEICON;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPORTING_MATERIAL__VARIABILITY_TYPE = GUIDANCE__VARIABILITY_TYPE;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPORTING_MATERIAL__VARIABILITY_BASED_ON_ELEMENT = GUIDANCE__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Supporting Materials</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPORTING_MATERIAL__SUPPORTING_MATERIALS = GUIDANCE__SUPPORTING_MATERIALS;

	/**
	 * The feature id for the '<em><b>Concepts And Papers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPORTING_MATERIAL__CONCEPTS_AND_PAPERS = GUIDANCE__CONCEPTS_AND_PAPERS;

	/**
	 * The feature id for the '<em><b>Checklists</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPORTING_MATERIAL__CHECKLISTS = GUIDANCE__CHECKLISTS;

	/**
	 * The feature id for the '<em><b>Guidelines</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPORTING_MATERIAL__GUIDELINES = GUIDANCE__GUIDELINES;

	/**
	 * The feature id for the '<em><b>Examples</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPORTING_MATERIAL__EXAMPLES = GUIDANCE__EXAMPLES;

	/**
	 * The feature id for the '<em><b>Assets</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPORTING_MATERIAL__ASSETS = GUIDANCE__ASSETS;

	/**
	 * The feature id for the '<em><b>Termdefinition</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPORTING_MATERIAL__TERMDEFINITION = GUIDANCE__TERMDEFINITION;

	/**
	 * The number of structural features of the '<em>Supporting Material</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPORTING_MATERIAL_FEATURE_COUNT = GUIDANCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABILITY_ELEMENT__NAME = METHOD_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABILITY_ELEMENT__GUID = METHOD_ELEMENT__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABILITY_ELEMENT__PRESENTATION_NAME = METHOD_ELEMENT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABILITY_ELEMENT__BRIEF_DESCRIPTION = METHOD_ELEMENT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABILITY_ELEMENT__OWNED_RULES = METHOD_ELEMENT__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABILITY_ELEMENT__METHOD_ELEMENT_PROPERTY = METHOD_ELEMENT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABILITY_ELEMENT__KIND = METHOD_ELEMENT__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABILITY_ELEMENT__SUPPRESSED = METHOD_ELEMENT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABILITY_ELEMENT__ORDERING_GUIDE = METHOD_ELEMENT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABILITY_ELEMENT__VARIABILITY_TYPE = METHOD_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABILITY_ELEMENT__VARIABILITY_BASED_ON_ELEMENT = METHOD_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Variability Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABILITY_ELEMENT_FEATURE_COUNT = METHOD_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__NAME = VARIABILITY_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__GUID = VARIABILITY_ELEMENT__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__PRESENTATION_NAME = VARIABILITY_ELEMENT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__BRIEF_DESCRIPTION = VARIABILITY_ELEMENT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__OWNED_RULES = VARIABILITY_ELEMENT__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__METHOD_ELEMENT_PROPERTY = VARIABILITY_ELEMENT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__KIND = VARIABILITY_ELEMENT__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__SUPPRESSED = VARIABILITY_ELEMENT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__ORDERING_GUIDE = VARIABILITY_ELEMENT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__VARIABILITY_TYPE = VARIABILITY_ELEMENT__VARIABILITY_TYPE;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__VARIABILITY_BASED_ON_ELEMENT = VARIABILITY_ELEMENT__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Section Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__SECTION_NAME = VARIABILITY_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Section Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__SECTION_DESCRIPTION = VARIABILITY_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Sub Sections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__SUB_SECTIONS = VARIABILITY_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Predecessor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__PREDECESSOR = VARIABILITY_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Section</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION_FEATURE_COUNT = VARIABILITY_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__NAME = GUIDANCE__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__GUID = GUIDANCE__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__PRESENTATION_NAME = GUIDANCE__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__BRIEF_DESCRIPTION = GUIDANCE__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__OWNED_RULES = GUIDANCE__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__METHOD_ELEMENT_PROPERTY = GUIDANCE__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__KIND = GUIDANCE__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__SUPPRESSED = GUIDANCE__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__ORDERING_GUIDE = GUIDANCE__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__IS_ABSTRACT = GUIDANCE__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__PRESENTATION = GUIDANCE__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__SHAPEICON = GUIDANCE__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__NODEICON = GUIDANCE__NODEICON;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__VARIABILITY_TYPE = GUIDANCE__VARIABILITY_TYPE;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__VARIABILITY_BASED_ON_ELEMENT = GUIDANCE__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Supporting Materials</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__SUPPORTING_MATERIALS = GUIDANCE__SUPPORTING_MATERIALS;

	/**
	 * The feature id for the '<em><b>Concepts And Papers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__CONCEPTS_AND_PAPERS = GUIDANCE__CONCEPTS_AND_PAPERS;

	/**
	 * The feature id for the '<em><b>Checklists</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__CHECKLISTS = GUIDANCE__CHECKLISTS;

	/**
	 * The feature id for the '<em><b>Guidelines</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__GUIDELINES = GUIDANCE__GUIDELINES;

	/**
	 * The feature id for the '<em><b>Examples</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__EXAMPLES = GUIDANCE__EXAMPLES;

	/**
	 * The feature id for the '<em><b>Assets</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__ASSETS = GUIDANCE__ASSETS;

	/**
	 * The feature id for the '<em><b>Termdefinition</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__TERMDEFINITION = GUIDANCE__TERMDEFINITION;

	/**
	 * The number of structural features of the '<em>Concept</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT_FEATURE_COUNT = GUIDANCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKLIST__NAME = GUIDANCE__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKLIST__GUID = GUIDANCE__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKLIST__PRESENTATION_NAME = GUIDANCE__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKLIST__BRIEF_DESCRIPTION = GUIDANCE__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKLIST__OWNED_RULES = GUIDANCE__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKLIST__METHOD_ELEMENT_PROPERTY = GUIDANCE__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKLIST__KIND = GUIDANCE__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKLIST__SUPPRESSED = GUIDANCE__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKLIST__ORDERING_GUIDE = GUIDANCE__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKLIST__IS_ABSTRACT = GUIDANCE__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKLIST__PRESENTATION = GUIDANCE__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKLIST__SHAPEICON = GUIDANCE__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKLIST__NODEICON = GUIDANCE__NODEICON;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKLIST__VARIABILITY_TYPE = GUIDANCE__VARIABILITY_TYPE;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKLIST__VARIABILITY_BASED_ON_ELEMENT = GUIDANCE__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Supporting Materials</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKLIST__SUPPORTING_MATERIALS = GUIDANCE__SUPPORTING_MATERIALS;

	/**
	 * The feature id for the '<em><b>Concepts And Papers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKLIST__CONCEPTS_AND_PAPERS = GUIDANCE__CONCEPTS_AND_PAPERS;

	/**
	 * The feature id for the '<em><b>Checklists</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKLIST__CHECKLISTS = GUIDANCE__CHECKLISTS;

	/**
	 * The feature id for the '<em><b>Guidelines</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKLIST__GUIDELINES = GUIDANCE__GUIDELINES;

	/**
	 * The feature id for the '<em><b>Examples</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKLIST__EXAMPLES = GUIDANCE__EXAMPLES;

	/**
	 * The feature id for the '<em><b>Assets</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKLIST__ASSETS = GUIDANCE__ASSETS;

	/**
	 * The feature id for the '<em><b>Termdefinition</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKLIST__TERMDEFINITION = GUIDANCE__TERMDEFINITION;

	/**
	 * The number of structural features of the '<em>Checklist</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKLIST_FEATURE_COUNT = GUIDANCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDELINE__NAME = GUIDANCE__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDELINE__GUID = GUIDANCE__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDELINE__PRESENTATION_NAME = GUIDANCE__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDELINE__BRIEF_DESCRIPTION = GUIDANCE__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDELINE__OWNED_RULES = GUIDANCE__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDELINE__METHOD_ELEMENT_PROPERTY = GUIDANCE__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDELINE__KIND = GUIDANCE__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDELINE__SUPPRESSED = GUIDANCE__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDELINE__ORDERING_GUIDE = GUIDANCE__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDELINE__IS_ABSTRACT = GUIDANCE__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDELINE__PRESENTATION = GUIDANCE__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDELINE__SHAPEICON = GUIDANCE__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDELINE__NODEICON = GUIDANCE__NODEICON;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDELINE__VARIABILITY_TYPE = GUIDANCE__VARIABILITY_TYPE;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDELINE__VARIABILITY_BASED_ON_ELEMENT = GUIDANCE__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Supporting Materials</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDELINE__SUPPORTING_MATERIALS = GUIDANCE__SUPPORTING_MATERIALS;

	/**
	 * The feature id for the '<em><b>Concepts And Papers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDELINE__CONCEPTS_AND_PAPERS = GUIDANCE__CONCEPTS_AND_PAPERS;

	/**
	 * The feature id for the '<em><b>Checklists</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDELINE__CHECKLISTS = GUIDANCE__CHECKLISTS;

	/**
	 * The feature id for the '<em><b>Guidelines</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDELINE__GUIDELINES = GUIDANCE__GUIDELINES;

	/**
	 * The feature id for the '<em><b>Examples</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDELINE__EXAMPLES = GUIDANCE__EXAMPLES;

	/**
	 * The feature id for the '<em><b>Assets</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDELINE__ASSETS = GUIDANCE__ASSETS;

	/**
	 * The feature id for the '<em><b>Termdefinition</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDELINE__TERMDEFINITION = GUIDANCE__TERMDEFINITION;

	/**
	 * The number of structural features of the '<em>Guideline</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDELINE_FEATURE_COUNT = GUIDANCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXAMPLE__NAME = GUIDANCE__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXAMPLE__GUID = GUIDANCE__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXAMPLE__PRESENTATION_NAME = GUIDANCE__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXAMPLE__BRIEF_DESCRIPTION = GUIDANCE__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXAMPLE__OWNED_RULES = GUIDANCE__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXAMPLE__METHOD_ELEMENT_PROPERTY = GUIDANCE__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXAMPLE__KIND = GUIDANCE__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXAMPLE__SUPPRESSED = GUIDANCE__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXAMPLE__ORDERING_GUIDE = GUIDANCE__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXAMPLE__IS_ABSTRACT = GUIDANCE__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXAMPLE__PRESENTATION = GUIDANCE__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXAMPLE__SHAPEICON = GUIDANCE__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXAMPLE__NODEICON = GUIDANCE__NODEICON;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXAMPLE__VARIABILITY_TYPE = GUIDANCE__VARIABILITY_TYPE;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXAMPLE__VARIABILITY_BASED_ON_ELEMENT = GUIDANCE__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Supporting Materials</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXAMPLE__SUPPORTING_MATERIALS = GUIDANCE__SUPPORTING_MATERIALS;

	/**
	 * The feature id for the '<em><b>Concepts And Papers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXAMPLE__CONCEPTS_AND_PAPERS = GUIDANCE__CONCEPTS_AND_PAPERS;

	/**
	 * The feature id for the '<em><b>Checklists</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXAMPLE__CHECKLISTS = GUIDANCE__CHECKLISTS;

	/**
	 * The feature id for the '<em><b>Guidelines</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXAMPLE__GUIDELINES = GUIDANCE__GUIDELINES;

	/**
	 * The feature id for the '<em><b>Examples</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXAMPLE__EXAMPLES = GUIDANCE__EXAMPLES;

	/**
	 * The feature id for the '<em><b>Assets</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXAMPLE__ASSETS = GUIDANCE__ASSETS;

	/**
	 * The feature id for the '<em><b>Termdefinition</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXAMPLE__TERMDEFINITION = GUIDANCE__TERMDEFINITION;

	/**
	 * The number of structural features of the '<em>Example</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXAMPLE_FEATURE_COUNT = GUIDANCE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.ToolMentorImpl <em>Tool Mentor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.ToolMentorImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getToolMentor()
	 * @generated
	 */
	int TOOL_MENTOR = 31;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.WhitepaperImpl <em>Whitepaper</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.WhitepaperImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getWhitepaper()
	 * @generated
	 */
	int WHITEPAPER = 37;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.TermDefinitionImpl <em>Term Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.TermDefinitionImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getTermDefinition()
	 * @generated
	 */
	int TERM_DEFINITION = 24;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.PracticeImpl <em>Practice</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.PracticeImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getPractice()
	 * @generated
	 */
	int PRACTICE = 80;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.ReusableAssetImpl <em>Reusable Asset</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.ReusableAssetImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getReusableAsset()
	 * @generated
	 */
	int REUSABLE_ASSET = 23;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.ContentCategoryImpl <em>Content Category</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.ContentCategoryImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getContentCategory()
	 * @generated
	 */
	int CONTENT_CATEGORY = 48;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.DisciplineImpl <em>Discipline</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.DisciplineImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getDiscipline()
	 * @generated
	 */
	int DISCIPLINE = 52;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.RoleSetImpl <em>Role Set</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.RoleSetImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getRoleSet()
	 * @generated
	 */
	int ROLE_SET = 47;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.DomainImpl <em>Domain</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.DomainImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getDomain()
	 * @generated
	 */
	int DOMAIN = 49;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.WorkProductTypeImpl <em>Work Product Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.WorkProductTypeImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getWorkProductType()
	 * @generated
	 */
	int WORK_PRODUCT_TYPE = 50;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.DisciplineGroupingImpl <em>Discipline Grouping</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.DisciplineGroupingImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getDisciplineGrouping()
	 * @generated
	 */
	int DISCIPLINE_GROUPING = 51;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.ToolImpl <em>Tool</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.ToolImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getTool()
	 * @generated
	 */
	int TOOL = 60;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.RoleSetGroupingImpl <em>Role Set Grouping</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.RoleSetGroupingImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getRoleSetGrouping()
	 * @generated
	 */
	int ROLE_SET_GROUPING = 61;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.CustomCategoryImpl <em>Custom Category</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.CustomCategoryImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getCustomCategory()
	 * @generated
	 */
	int CUSTOM_CATEGORY = 62;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.ProcessImpl <em>Process</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.ProcessImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getProcess()
	 * @generated
	 */
	int PROCESS = 75;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.DeliveryProcessImpl <em>Delivery Process</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.DeliveryProcessImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getDeliveryProcess()
	 * @generated
	 */
	int DELIVERY_PROCESS = 74;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.CapabilityPatternImpl <em>Capability Pattern</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.CapabilityPatternImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getCapabilityPattern()
	 * @generated
	 */
	int CAPABILITY_PATTERN = 76;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.ProcessPlanningTemplateImpl <em>Process Planning Template</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.ProcessPlanningTemplateImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getProcessPlanningTemplate()
	 * @generated
	 */
	int PROCESS_PLANNING_TEMPLATE = 79;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.RoadmapImpl <em>Roadmap</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.RoadmapImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getRoadmap()
	 * @generated
	 */
	int ROADMAP = 59;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.ProcessPackageImpl <em>Process Package</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.ProcessPackageImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getProcessPackage()
	 * @generated
	 */
	int PROCESS_PACKAGE = 88;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.ProcessComponentImpl <em>Process Component</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.ProcessComponentImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getProcessComponent()
	 * @generated
	 */
	int PROCESS_COMPONENT = 87;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.ProcessComponentInterfaceImpl <em>Process Component Interface</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.ProcessComponentInterfaceImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getProcessComponentInterface()
	 * @generated
	 */
	int PROCESS_COMPONENT_INTERFACE = 101;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.ProcessComponentDescriptorImpl <em>Process Component Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.ProcessComponentDescriptorImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getProcessComponentDescriptor()
	 * @generated
	 */
	int PROCESS_COMPONENT_DESCRIPTOR = 86;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.MethodPluginImpl <em>Method Plugin</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.MethodPluginImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getMethodPlugin()
	 * @generated
	 */
	int METHOD_PLUGIN = 78;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REUSABLE_ASSET__NAME = GUIDANCE__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REUSABLE_ASSET__GUID = GUIDANCE__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REUSABLE_ASSET__PRESENTATION_NAME = GUIDANCE__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REUSABLE_ASSET__BRIEF_DESCRIPTION = GUIDANCE__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REUSABLE_ASSET__OWNED_RULES = GUIDANCE__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REUSABLE_ASSET__METHOD_ELEMENT_PROPERTY = GUIDANCE__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REUSABLE_ASSET__KIND = GUIDANCE__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REUSABLE_ASSET__SUPPRESSED = GUIDANCE__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REUSABLE_ASSET__ORDERING_GUIDE = GUIDANCE__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REUSABLE_ASSET__IS_ABSTRACT = GUIDANCE__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REUSABLE_ASSET__PRESENTATION = GUIDANCE__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REUSABLE_ASSET__SHAPEICON = GUIDANCE__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REUSABLE_ASSET__NODEICON = GUIDANCE__NODEICON;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REUSABLE_ASSET__VARIABILITY_TYPE = GUIDANCE__VARIABILITY_TYPE;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REUSABLE_ASSET__VARIABILITY_BASED_ON_ELEMENT = GUIDANCE__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Supporting Materials</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REUSABLE_ASSET__SUPPORTING_MATERIALS = GUIDANCE__SUPPORTING_MATERIALS;

	/**
	 * The feature id for the '<em><b>Concepts And Papers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REUSABLE_ASSET__CONCEPTS_AND_PAPERS = GUIDANCE__CONCEPTS_AND_PAPERS;

	/**
	 * The feature id for the '<em><b>Checklists</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REUSABLE_ASSET__CHECKLISTS = GUIDANCE__CHECKLISTS;

	/**
	 * The feature id for the '<em><b>Guidelines</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REUSABLE_ASSET__GUIDELINES = GUIDANCE__GUIDELINES;

	/**
	 * The feature id for the '<em><b>Examples</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REUSABLE_ASSET__EXAMPLES = GUIDANCE__EXAMPLES;

	/**
	 * The feature id for the '<em><b>Assets</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REUSABLE_ASSET__ASSETS = GUIDANCE__ASSETS;

	/**
	 * The feature id for the '<em><b>Termdefinition</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REUSABLE_ASSET__TERMDEFINITION = GUIDANCE__TERMDEFINITION;

	/**
	 * The number of structural features of the '<em>Reusable Asset</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REUSABLE_ASSET_FEATURE_COUNT = GUIDANCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERM_DEFINITION__NAME = GUIDANCE__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERM_DEFINITION__GUID = GUIDANCE__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERM_DEFINITION__PRESENTATION_NAME = GUIDANCE__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERM_DEFINITION__BRIEF_DESCRIPTION = GUIDANCE__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERM_DEFINITION__OWNED_RULES = GUIDANCE__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERM_DEFINITION__METHOD_ELEMENT_PROPERTY = GUIDANCE__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERM_DEFINITION__KIND = GUIDANCE__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERM_DEFINITION__SUPPRESSED = GUIDANCE__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERM_DEFINITION__ORDERING_GUIDE = GUIDANCE__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERM_DEFINITION__IS_ABSTRACT = GUIDANCE__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERM_DEFINITION__PRESENTATION = GUIDANCE__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERM_DEFINITION__SHAPEICON = GUIDANCE__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERM_DEFINITION__NODEICON = GUIDANCE__NODEICON;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERM_DEFINITION__VARIABILITY_TYPE = GUIDANCE__VARIABILITY_TYPE;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERM_DEFINITION__VARIABILITY_BASED_ON_ELEMENT = GUIDANCE__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Supporting Materials</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERM_DEFINITION__SUPPORTING_MATERIALS = GUIDANCE__SUPPORTING_MATERIALS;

	/**
	 * The feature id for the '<em><b>Concepts And Papers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERM_DEFINITION__CONCEPTS_AND_PAPERS = GUIDANCE__CONCEPTS_AND_PAPERS;

	/**
	 * The feature id for the '<em><b>Checklists</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERM_DEFINITION__CHECKLISTS = GUIDANCE__CHECKLISTS;

	/**
	 * The feature id for the '<em><b>Guidelines</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERM_DEFINITION__GUIDELINES = GUIDANCE__GUIDELINES;

	/**
	 * The feature id for the '<em><b>Examples</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERM_DEFINITION__EXAMPLES = GUIDANCE__EXAMPLES;

	/**
	 * The feature id for the '<em><b>Assets</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERM_DEFINITION__ASSETS = GUIDANCE__ASSETS;

	/**
	 * The feature id for the '<em><b>Termdefinition</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERM_DEFINITION__TERMDEFINITION = GUIDANCE__TERMDEFINITION;

	/**
	 * The number of structural features of the '<em>Term Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERM_DEFINITION_FEATURE_COUNT = GUIDANCE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.ApplicableMetaClassInfoImpl <em>Applicable Meta Class Info</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.ApplicableMetaClassInfoImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getApplicableMetaClassInfo()
	 * @generated
	 */
	int APPLICABLE_META_CLASS_INFO = 25;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICABLE_META_CLASS_INFO__NAME = CLASSIFIER__NAME;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICABLE_META_CLASS_INFO__IS_ABSTRACT = CLASSIFIER__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Is Primary Extension</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICABLE_META_CLASS_INFO__IS_PRIMARY_EXTENSION = CLASSIFIER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Applicable Meta Class Info</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICABLE_META_CLASS_INFO_FEATURE_COUNT = CLASSIFIER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT__NAME = CONTENT_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT__GUID = CONTENT_ELEMENT__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT__PRESENTATION_NAME = CONTENT_ELEMENT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT__BRIEF_DESCRIPTION = CONTENT_ELEMENT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT__OWNED_RULES = CONTENT_ELEMENT__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT__METHOD_ELEMENT_PROPERTY = CONTENT_ELEMENT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT__KIND = CONTENT_ELEMENT__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT__SUPPRESSED = CONTENT_ELEMENT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT__ORDERING_GUIDE = CONTENT_ELEMENT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT__IS_ABSTRACT = CONTENT_ELEMENT__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT__PRESENTATION = CONTENT_ELEMENT__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT__SHAPEICON = CONTENT_ELEMENT__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT__NODEICON = CONTENT_ELEMENT__NODEICON;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT__VARIABILITY_TYPE = CONTENT_ELEMENT__VARIABILITY_TYPE;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT__VARIABILITY_BASED_ON_ELEMENT = CONTENT_ELEMENT__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Supporting Materials</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT__SUPPORTING_MATERIALS = CONTENT_ELEMENT__SUPPORTING_MATERIALS;

	/**
	 * The feature id for the '<em><b>Concepts And Papers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT__CONCEPTS_AND_PAPERS = CONTENT_ELEMENT__CONCEPTS_AND_PAPERS;

	/**
	 * The feature id for the '<em><b>Checklists</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT__CHECKLISTS = CONTENT_ELEMENT__CHECKLISTS;

	/**
	 * The feature id for the '<em><b>Guidelines</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT__GUIDELINES = CONTENT_ELEMENT__GUIDELINES;

	/**
	 * The feature id for the '<em><b>Examples</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT__EXAMPLES = CONTENT_ELEMENT__EXAMPLES;

	/**
	 * The feature id for the '<em><b>Assets</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT__ASSETS = CONTENT_ELEMENT__ASSETS;

	/**
	 * The feature id for the '<em><b>Termdefinition</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT__TERMDEFINITION = CONTENT_ELEMENT__TERMDEFINITION;

	/**
	 * The feature id for the '<em><b>Fulfills</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT__FULFILLS = CONTENT_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Reports</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT__REPORTS = CONTENT_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Templates</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT__TEMPLATES = CONTENT_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Tool Mentors</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT__TOOL_MENTORS = CONTENT_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Estimation Considerations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT__ESTIMATION_CONSIDERATIONS = CONTENT_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Work Product</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_FEATURE_COUNT = CONTENT_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__NAME = WORK_PRODUCT__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__GUID = WORK_PRODUCT__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__PRESENTATION_NAME = WORK_PRODUCT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__BRIEF_DESCRIPTION = WORK_PRODUCT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__OWNED_RULES = WORK_PRODUCT__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__METHOD_ELEMENT_PROPERTY = WORK_PRODUCT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__KIND = WORK_PRODUCT__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__SUPPRESSED = WORK_PRODUCT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__ORDERING_GUIDE = WORK_PRODUCT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__IS_ABSTRACT = WORK_PRODUCT__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__PRESENTATION = WORK_PRODUCT__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__SHAPEICON = WORK_PRODUCT__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__NODEICON = WORK_PRODUCT__NODEICON;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__VARIABILITY_TYPE = WORK_PRODUCT__VARIABILITY_TYPE;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__VARIABILITY_BASED_ON_ELEMENT = WORK_PRODUCT__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Supporting Materials</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__SUPPORTING_MATERIALS = WORK_PRODUCT__SUPPORTING_MATERIALS;

	/**
	 * The feature id for the '<em><b>Concepts And Papers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__CONCEPTS_AND_PAPERS = WORK_PRODUCT__CONCEPTS_AND_PAPERS;

	/**
	 * The feature id for the '<em><b>Checklists</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__CHECKLISTS = WORK_PRODUCT__CHECKLISTS;

	/**
	 * The feature id for the '<em><b>Guidelines</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__GUIDELINES = WORK_PRODUCT__GUIDELINES;

	/**
	 * The feature id for the '<em><b>Examples</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__EXAMPLES = WORK_PRODUCT__EXAMPLES;

	/**
	 * The feature id for the '<em><b>Assets</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__ASSETS = WORK_PRODUCT__ASSETS;

	/**
	 * The feature id for the '<em><b>Termdefinition</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__TERMDEFINITION = WORK_PRODUCT__TERMDEFINITION;

	/**
	 * The feature id for the '<em><b>Fulfills</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__FULFILLS = WORK_PRODUCT__FULFILLS;

	/**
	 * The feature id for the '<em><b>Reports</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__REPORTS = WORK_PRODUCT__REPORTS;

	/**
	 * The feature id for the '<em><b>Templates</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__TEMPLATES = WORK_PRODUCT__TEMPLATES;

	/**
	 * The feature id for the '<em><b>Tool Mentors</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__TOOL_MENTORS = WORK_PRODUCT__TOOL_MENTORS;

	/**
	 * The feature id for the '<em><b>Estimation Considerations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__ESTIMATION_CONSIDERATIONS = WORK_PRODUCT__ESTIMATION_CONSIDERATIONS;

	/**
	 * The feature id for the '<em><b>Container Artifact</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__CONTAINER_ARTIFACT = WORK_PRODUCT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Contained Artifacts</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__CONTAINED_ARTIFACTS = WORK_PRODUCT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Artifact</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_FEATURE_COUNT = WORK_PRODUCT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FULFILLABLE_ELEMENT__NAME = DESCRIBABLE_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FULFILLABLE_ELEMENT__GUID = DESCRIBABLE_ELEMENT__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FULFILLABLE_ELEMENT__PRESENTATION_NAME = DESCRIBABLE_ELEMENT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FULFILLABLE_ELEMENT__BRIEF_DESCRIPTION = DESCRIBABLE_ELEMENT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FULFILLABLE_ELEMENT__OWNED_RULES = DESCRIBABLE_ELEMENT__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FULFILLABLE_ELEMENT__METHOD_ELEMENT_PROPERTY = DESCRIBABLE_ELEMENT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FULFILLABLE_ELEMENT__KIND = DESCRIBABLE_ELEMENT__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FULFILLABLE_ELEMENT__SUPPRESSED = DESCRIBABLE_ELEMENT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FULFILLABLE_ELEMENT__ORDERING_GUIDE = DESCRIBABLE_ELEMENT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FULFILLABLE_ELEMENT__IS_ABSTRACT = DESCRIBABLE_ELEMENT__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FULFILLABLE_ELEMENT__PRESENTATION = DESCRIBABLE_ELEMENT__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FULFILLABLE_ELEMENT__SHAPEICON = DESCRIBABLE_ELEMENT__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FULFILLABLE_ELEMENT__NODEICON = DESCRIBABLE_ELEMENT__NODEICON;

	/**
	 * The feature id for the '<em><b>Fulfills</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FULFILLABLE_ELEMENT__FULFILLS = DESCRIBABLE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Fulfillable Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FULFILLABLE_ELEMENT_FEATURE_COUNT = DESCRIBABLE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT__NAME = GUIDANCE__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT__GUID = GUIDANCE__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT__PRESENTATION_NAME = GUIDANCE__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT__BRIEF_DESCRIPTION = GUIDANCE__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT__OWNED_RULES = GUIDANCE__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT__METHOD_ELEMENT_PROPERTY = GUIDANCE__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT__KIND = GUIDANCE__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT__SUPPRESSED = GUIDANCE__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT__ORDERING_GUIDE = GUIDANCE__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT__IS_ABSTRACT = GUIDANCE__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT__PRESENTATION = GUIDANCE__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT__SHAPEICON = GUIDANCE__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT__NODEICON = GUIDANCE__NODEICON;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT__VARIABILITY_TYPE = GUIDANCE__VARIABILITY_TYPE;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT__VARIABILITY_BASED_ON_ELEMENT = GUIDANCE__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Supporting Materials</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT__SUPPORTING_MATERIALS = GUIDANCE__SUPPORTING_MATERIALS;

	/**
	 * The feature id for the '<em><b>Concepts And Papers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT__CONCEPTS_AND_PAPERS = GUIDANCE__CONCEPTS_AND_PAPERS;

	/**
	 * The feature id for the '<em><b>Checklists</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT__CHECKLISTS = GUIDANCE__CHECKLISTS;

	/**
	 * The feature id for the '<em><b>Guidelines</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT__GUIDELINES = GUIDANCE__GUIDELINES;

	/**
	 * The feature id for the '<em><b>Examples</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT__EXAMPLES = GUIDANCE__EXAMPLES;

	/**
	 * The feature id for the '<em><b>Assets</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT__ASSETS = GUIDANCE__ASSETS;

	/**
	 * The feature id for the '<em><b>Termdefinition</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT__TERMDEFINITION = GUIDANCE__TERMDEFINITION;

	/**
	 * The number of structural features of the '<em>Report</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT_FEATURE_COUNT = GUIDANCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE__NAME = GUIDANCE__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE__GUID = GUIDANCE__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE__PRESENTATION_NAME = GUIDANCE__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE__BRIEF_DESCRIPTION = GUIDANCE__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE__OWNED_RULES = GUIDANCE__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE__METHOD_ELEMENT_PROPERTY = GUIDANCE__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE__KIND = GUIDANCE__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE__SUPPRESSED = GUIDANCE__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE__ORDERING_GUIDE = GUIDANCE__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE__IS_ABSTRACT = GUIDANCE__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE__PRESENTATION = GUIDANCE__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE__SHAPEICON = GUIDANCE__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE__NODEICON = GUIDANCE__NODEICON;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE__VARIABILITY_TYPE = GUIDANCE__VARIABILITY_TYPE;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE__VARIABILITY_BASED_ON_ELEMENT = GUIDANCE__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Supporting Materials</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE__SUPPORTING_MATERIALS = GUIDANCE__SUPPORTING_MATERIALS;

	/**
	 * The feature id for the '<em><b>Concepts And Papers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE__CONCEPTS_AND_PAPERS = GUIDANCE__CONCEPTS_AND_PAPERS;

	/**
	 * The feature id for the '<em><b>Checklists</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE__CHECKLISTS = GUIDANCE__CHECKLISTS;

	/**
	 * The feature id for the '<em><b>Guidelines</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE__GUIDELINES = GUIDANCE__GUIDELINES;

	/**
	 * The feature id for the '<em><b>Examples</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE__EXAMPLES = GUIDANCE__EXAMPLES;

	/**
	 * The feature id for the '<em><b>Assets</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE__ASSETS = GUIDANCE__ASSETS;

	/**
	 * The feature id for the '<em><b>Termdefinition</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE__TERMDEFINITION = GUIDANCE__TERMDEFINITION;

	/**
	 * The number of structural features of the '<em>Template</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE_FEATURE_COUNT = GUIDANCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_MENTOR__NAME = GUIDANCE__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_MENTOR__GUID = GUIDANCE__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_MENTOR__PRESENTATION_NAME = GUIDANCE__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_MENTOR__BRIEF_DESCRIPTION = GUIDANCE__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_MENTOR__OWNED_RULES = GUIDANCE__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_MENTOR__METHOD_ELEMENT_PROPERTY = GUIDANCE__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_MENTOR__KIND = GUIDANCE__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_MENTOR__SUPPRESSED = GUIDANCE__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_MENTOR__ORDERING_GUIDE = GUIDANCE__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_MENTOR__IS_ABSTRACT = GUIDANCE__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_MENTOR__PRESENTATION = GUIDANCE__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_MENTOR__SHAPEICON = GUIDANCE__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_MENTOR__NODEICON = GUIDANCE__NODEICON;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_MENTOR__VARIABILITY_TYPE = GUIDANCE__VARIABILITY_TYPE;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_MENTOR__VARIABILITY_BASED_ON_ELEMENT = GUIDANCE__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Supporting Materials</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_MENTOR__SUPPORTING_MATERIALS = GUIDANCE__SUPPORTING_MATERIALS;

	/**
	 * The feature id for the '<em><b>Concepts And Papers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_MENTOR__CONCEPTS_AND_PAPERS = GUIDANCE__CONCEPTS_AND_PAPERS;

	/**
	 * The feature id for the '<em><b>Checklists</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_MENTOR__CHECKLISTS = GUIDANCE__CHECKLISTS;

	/**
	 * The feature id for the '<em><b>Guidelines</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_MENTOR__GUIDELINES = GUIDANCE__GUIDELINES;

	/**
	 * The feature id for the '<em><b>Examples</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_MENTOR__EXAMPLES = GUIDANCE__EXAMPLES;

	/**
	 * The feature id for the '<em><b>Assets</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_MENTOR__ASSETS = GUIDANCE__ASSETS;

	/**
	 * The feature id for the '<em><b>Termdefinition</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_MENTOR__TERMDEFINITION = GUIDANCE__TERMDEFINITION;

	/**
	 * The number of structural features of the '<em>Tool Mentor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_MENTOR_FEATURE_COUNT = GUIDANCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATION_CONSIDERATIONS__NAME = GUIDANCE__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATION_CONSIDERATIONS__GUID = GUIDANCE__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATION_CONSIDERATIONS__PRESENTATION_NAME = GUIDANCE__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATION_CONSIDERATIONS__BRIEF_DESCRIPTION = GUIDANCE__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATION_CONSIDERATIONS__OWNED_RULES = GUIDANCE__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATION_CONSIDERATIONS__METHOD_ELEMENT_PROPERTY = GUIDANCE__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATION_CONSIDERATIONS__KIND = GUIDANCE__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATION_CONSIDERATIONS__SUPPRESSED = GUIDANCE__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATION_CONSIDERATIONS__ORDERING_GUIDE = GUIDANCE__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATION_CONSIDERATIONS__IS_ABSTRACT = GUIDANCE__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATION_CONSIDERATIONS__PRESENTATION = GUIDANCE__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATION_CONSIDERATIONS__SHAPEICON = GUIDANCE__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATION_CONSIDERATIONS__NODEICON = GUIDANCE__NODEICON;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATION_CONSIDERATIONS__VARIABILITY_TYPE = GUIDANCE__VARIABILITY_TYPE;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATION_CONSIDERATIONS__VARIABILITY_BASED_ON_ELEMENT = GUIDANCE__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Supporting Materials</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATION_CONSIDERATIONS__SUPPORTING_MATERIALS = GUIDANCE__SUPPORTING_MATERIALS;

	/**
	 * The feature id for the '<em><b>Concepts And Papers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATION_CONSIDERATIONS__CONCEPTS_AND_PAPERS = GUIDANCE__CONCEPTS_AND_PAPERS;

	/**
	 * The feature id for the '<em><b>Checklists</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATION_CONSIDERATIONS__CHECKLISTS = GUIDANCE__CHECKLISTS;

	/**
	 * The feature id for the '<em><b>Guidelines</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATION_CONSIDERATIONS__GUIDELINES = GUIDANCE__GUIDELINES;

	/**
	 * The feature id for the '<em><b>Examples</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATION_CONSIDERATIONS__EXAMPLES = GUIDANCE__EXAMPLES;

	/**
	 * The feature id for the '<em><b>Assets</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATION_CONSIDERATIONS__ASSETS = GUIDANCE__ASSETS;

	/**
	 * The feature id for the '<em><b>Termdefinition</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATION_CONSIDERATIONS__TERMDEFINITION = GUIDANCE__TERMDEFINITION;

	/**
	 * The number of structural features of the '<em>Estimation Considerations</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATION_CONSIDERATIONS_FEATURE_COUNT = GUIDANCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE__NAME = WORK_PRODUCT__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE__GUID = WORK_PRODUCT__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE__PRESENTATION_NAME = WORK_PRODUCT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE__BRIEF_DESCRIPTION = WORK_PRODUCT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE__OWNED_RULES = WORK_PRODUCT__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE__METHOD_ELEMENT_PROPERTY = WORK_PRODUCT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE__KIND = WORK_PRODUCT__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE__SUPPRESSED = WORK_PRODUCT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE__ORDERING_GUIDE = WORK_PRODUCT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE__IS_ABSTRACT = WORK_PRODUCT__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE__PRESENTATION = WORK_PRODUCT__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE__SHAPEICON = WORK_PRODUCT__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE__NODEICON = WORK_PRODUCT__NODEICON;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE__VARIABILITY_TYPE = WORK_PRODUCT__VARIABILITY_TYPE;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE__VARIABILITY_BASED_ON_ELEMENT = WORK_PRODUCT__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Supporting Materials</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE__SUPPORTING_MATERIALS = WORK_PRODUCT__SUPPORTING_MATERIALS;

	/**
	 * The feature id for the '<em><b>Concepts And Papers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE__CONCEPTS_AND_PAPERS = WORK_PRODUCT__CONCEPTS_AND_PAPERS;

	/**
	 * The feature id for the '<em><b>Checklists</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE__CHECKLISTS = WORK_PRODUCT__CHECKLISTS;

	/**
	 * The feature id for the '<em><b>Guidelines</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE__GUIDELINES = WORK_PRODUCT__GUIDELINES;

	/**
	 * The feature id for the '<em><b>Examples</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE__EXAMPLES = WORK_PRODUCT__EXAMPLES;

	/**
	 * The feature id for the '<em><b>Assets</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE__ASSETS = WORK_PRODUCT__ASSETS;

	/**
	 * The feature id for the '<em><b>Termdefinition</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE__TERMDEFINITION = WORK_PRODUCT__TERMDEFINITION;

	/**
	 * The feature id for the '<em><b>Fulfills</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE__FULFILLS = WORK_PRODUCT__FULFILLS;

	/**
	 * The feature id for the '<em><b>Reports</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE__REPORTS = WORK_PRODUCT__REPORTS;

	/**
	 * The feature id for the '<em><b>Templates</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE__TEMPLATES = WORK_PRODUCT__TEMPLATES;

	/**
	 * The feature id for the '<em><b>Tool Mentors</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE__TOOL_MENTORS = WORK_PRODUCT__TOOL_MENTORS;

	/**
	 * The feature id for the '<em><b>Estimation Considerations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE__ESTIMATION_CONSIDERATIONS = WORK_PRODUCT__ESTIMATION_CONSIDERATIONS;

	/**
	 * The feature id for the '<em><b>Delivered Work Products</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE__DELIVERED_WORK_PRODUCTS = WORK_PRODUCT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Deliverable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE_FEATURE_COUNT = WORK_PRODUCT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__NAME = WORK_PRODUCT__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__GUID = WORK_PRODUCT__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__PRESENTATION_NAME = WORK_PRODUCT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__BRIEF_DESCRIPTION = WORK_PRODUCT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__OWNED_RULES = WORK_PRODUCT__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__METHOD_ELEMENT_PROPERTY = WORK_PRODUCT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__KIND = WORK_PRODUCT__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__SUPPRESSED = WORK_PRODUCT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__ORDERING_GUIDE = WORK_PRODUCT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__IS_ABSTRACT = WORK_PRODUCT__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__PRESENTATION = WORK_PRODUCT__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__SHAPEICON = WORK_PRODUCT__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__NODEICON = WORK_PRODUCT__NODEICON;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__VARIABILITY_TYPE = WORK_PRODUCT__VARIABILITY_TYPE;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__VARIABILITY_BASED_ON_ELEMENT = WORK_PRODUCT__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Supporting Materials</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__SUPPORTING_MATERIALS = WORK_PRODUCT__SUPPORTING_MATERIALS;

	/**
	 * The feature id for the '<em><b>Concepts And Papers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__CONCEPTS_AND_PAPERS = WORK_PRODUCT__CONCEPTS_AND_PAPERS;

	/**
	 * The feature id for the '<em><b>Checklists</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__CHECKLISTS = WORK_PRODUCT__CHECKLISTS;

	/**
	 * The feature id for the '<em><b>Guidelines</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__GUIDELINES = WORK_PRODUCT__GUIDELINES;

	/**
	 * The feature id for the '<em><b>Examples</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__EXAMPLES = WORK_PRODUCT__EXAMPLES;

	/**
	 * The feature id for the '<em><b>Assets</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__ASSETS = WORK_PRODUCT__ASSETS;

	/**
	 * The feature id for the '<em><b>Termdefinition</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__TERMDEFINITION = WORK_PRODUCT__TERMDEFINITION;

	/**
	 * The feature id for the '<em><b>Fulfills</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__FULFILLS = WORK_PRODUCT__FULFILLS;

	/**
	 * The feature id for the '<em><b>Reports</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__REPORTS = WORK_PRODUCT__REPORTS;

	/**
	 * The feature id for the '<em><b>Templates</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__TEMPLATES = WORK_PRODUCT__TEMPLATES;

	/**
	 * The feature id for the '<em><b>Tool Mentors</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__TOOL_MENTORS = WORK_PRODUCT__TOOL_MENTORS;

	/**
	 * The feature id for the '<em><b>Estimation Considerations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__ESTIMATION_CONSIDERATIONS = WORK_PRODUCT__ESTIMATION_CONSIDERATIONS;

	/**
	 * The number of structural features of the '<em>Outcome</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME_FEATURE_COUNT = WORK_PRODUCT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP__NAME = SECTION__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP__GUID = SECTION__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP__PRESENTATION_NAME = SECTION__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP__BRIEF_DESCRIPTION = SECTION__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP__OWNED_RULES = SECTION__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP__METHOD_ELEMENT_PROPERTY = SECTION__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP__KIND = SECTION__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP__SUPPRESSED = SECTION__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP__ORDERING_GUIDE = SECTION__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP__VARIABILITY_TYPE = SECTION__VARIABILITY_TYPE;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP__VARIABILITY_BASED_ON_ELEMENT = SECTION__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Section Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP__SECTION_NAME = SECTION__SECTION_NAME;

	/**
	 * The feature id for the '<em><b>Section Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP__SECTION_DESCRIPTION = SECTION__SECTION_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Sub Sections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP__SUB_SECTIONS = SECTION__SUB_SECTIONS;

	/**
	 * The feature id for the '<em><b>Predecessor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP__PREDECESSOR = SECTION__PREDECESSOR;

	/**
	 * The feature id for the '<em><b>Precondition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP__PRECONDITION = SECTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Postcondition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP__POSTCONDITION = SECTION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP_FEATURE_COUNT = SECTION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_DEFINITION__NAME = METHOD_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_DEFINITION__GUID = METHOD_ELEMENT__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_DEFINITION__PRESENTATION_NAME = METHOD_ELEMENT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_DEFINITION__BRIEF_DESCRIPTION = METHOD_ELEMENT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_DEFINITION__OWNED_RULES = METHOD_ELEMENT__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_DEFINITION__METHOD_ELEMENT_PROPERTY = METHOD_ELEMENT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_DEFINITION__KIND = METHOD_ELEMENT__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_DEFINITION__SUPPRESSED = METHOD_ELEMENT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_DEFINITION__ORDERING_GUIDE = METHOD_ELEMENT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Precondition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_DEFINITION__PRECONDITION = METHOD_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Postcondition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_DEFINITION__POSTCONDITION = METHOD_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Work Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_DEFINITION_FEATURE_COUNT = METHOD_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHITEPAPER__NAME = CONCEPT__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHITEPAPER__GUID = CONCEPT__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHITEPAPER__PRESENTATION_NAME = CONCEPT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHITEPAPER__BRIEF_DESCRIPTION = CONCEPT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHITEPAPER__OWNED_RULES = CONCEPT__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHITEPAPER__METHOD_ELEMENT_PROPERTY = CONCEPT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHITEPAPER__KIND = CONCEPT__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHITEPAPER__SUPPRESSED = CONCEPT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHITEPAPER__ORDERING_GUIDE = CONCEPT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHITEPAPER__IS_ABSTRACT = CONCEPT__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHITEPAPER__PRESENTATION = CONCEPT__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHITEPAPER__SHAPEICON = CONCEPT__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHITEPAPER__NODEICON = CONCEPT__NODEICON;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHITEPAPER__VARIABILITY_TYPE = CONCEPT__VARIABILITY_TYPE;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHITEPAPER__VARIABILITY_BASED_ON_ELEMENT = CONCEPT__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Supporting Materials</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHITEPAPER__SUPPORTING_MATERIALS = CONCEPT__SUPPORTING_MATERIALS;

	/**
	 * The feature id for the '<em><b>Concepts And Papers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHITEPAPER__CONCEPTS_AND_PAPERS = CONCEPT__CONCEPTS_AND_PAPERS;

	/**
	 * The feature id for the '<em><b>Checklists</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHITEPAPER__CHECKLISTS = CONCEPT__CHECKLISTS;

	/**
	 * The feature id for the '<em><b>Guidelines</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHITEPAPER__GUIDELINES = CONCEPT__GUIDELINES;

	/**
	 * The feature id for the '<em><b>Examples</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHITEPAPER__EXAMPLES = CONCEPT__EXAMPLES;

	/**
	 * The feature id for the '<em><b>Assets</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHITEPAPER__ASSETS = CONCEPT__ASSETS;

	/**
	 * The feature id for the '<em><b>Termdefinition</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHITEPAPER__TERMDEFINITION = CONCEPT__TERMDEFINITION;

	/**
	 * The number of structural features of the '<em>Whitepaper</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHITEPAPER_FEATURE_COUNT = CONCEPT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__NAME = CONTENT_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__GUID = CONTENT_ELEMENT__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__PRESENTATION_NAME = CONTENT_ELEMENT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__BRIEF_DESCRIPTION = CONTENT_ELEMENT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__OWNED_RULES = CONTENT_ELEMENT__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__METHOD_ELEMENT_PROPERTY = CONTENT_ELEMENT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__KIND = CONTENT_ELEMENT__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__SUPPRESSED = CONTENT_ELEMENT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__ORDERING_GUIDE = CONTENT_ELEMENT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__IS_ABSTRACT = CONTENT_ELEMENT__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__PRESENTATION = CONTENT_ELEMENT__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__SHAPEICON = CONTENT_ELEMENT__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__NODEICON = CONTENT_ELEMENT__NODEICON;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__VARIABILITY_TYPE = CONTENT_ELEMENT__VARIABILITY_TYPE;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__VARIABILITY_BASED_ON_ELEMENT = CONTENT_ELEMENT__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Supporting Materials</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__SUPPORTING_MATERIALS = CONTENT_ELEMENT__SUPPORTING_MATERIALS;

	/**
	 * The feature id for the '<em><b>Concepts And Papers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__CONCEPTS_AND_PAPERS = CONTENT_ELEMENT__CONCEPTS_AND_PAPERS;

	/**
	 * The feature id for the '<em><b>Checklists</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__CHECKLISTS = CONTENT_ELEMENT__CHECKLISTS;

	/**
	 * The feature id for the '<em><b>Guidelines</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__GUIDELINES = CONTENT_ELEMENT__GUIDELINES;

	/**
	 * The feature id for the '<em><b>Examples</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__EXAMPLES = CONTENT_ELEMENT__EXAMPLES;

	/**
	 * The feature id for the '<em><b>Assets</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__ASSETS = CONTENT_ELEMENT__ASSETS;

	/**
	 * The feature id for the '<em><b>Termdefinition</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__TERMDEFINITION = CONTENT_ELEMENT__TERMDEFINITION;

	/**
	 * The feature id for the '<em><b>Precondition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__PRECONDITION = CONTENT_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Postcondition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__POSTCONDITION = CONTENT_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Performed By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__PERFORMED_BY = CONTENT_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Mandatory Input</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__MANDATORY_INPUT = CONTENT_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Output</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__OUTPUT = CONTENT_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Additionally Performed By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__ADDITIONALLY_PERFORMED_BY = CONTENT_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Optional Input</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__OPTIONAL_INPUT = CONTENT_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Steps</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__STEPS = CONTENT_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Tool Mentors</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__TOOL_MENTORS = CONTENT_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Estimation Considerations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__ESTIMATION_CONSIDERATIONS = CONTENT_ELEMENT_FEATURE_COUNT + 9;

	/**
	 * The number of structural features of the '<em>Task</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_FEATURE_COUNT = CONTENT_ELEMENT_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__NAME = CONTENT_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__GUID = CONTENT_ELEMENT__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__PRESENTATION_NAME = CONTENT_ELEMENT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__BRIEF_DESCRIPTION = CONTENT_ELEMENT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__OWNED_RULES = CONTENT_ELEMENT__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__METHOD_ELEMENT_PROPERTY = CONTENT_ELEMENT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__KIND = CONTENT_ELEMENT__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__SUPPRESSED = CONTENT_ELEMENT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__ORDERING_GUIDE = CONTENT_ELEMENT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__IS_ABSTRACT = CONTENT_ELEMENT__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__PRESENTATION = CONTENT_ELEMENT__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__SHAPEICON = CONTENT_ELEMENT__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__NODEICON = CONTENT_ELEMENT__NODEICON;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__VARIABILITY_TYPE = CONTENT_ELEMENT__VARIABILITY_TYPE;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__VARIABILITY_BASED_ON_ELEMENT = CONTENT_ELEMENT__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Supporting Materials</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__SUPPORTING_MATERIALS = CONTENT_ELEMENT__SUPPORTING_MATERIALS;

	/**
	 * The feature id for the '<em><b>Concepts And Papers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__CONCEPTS_AND_PAPERS = CONTENT_ELEMENT__CONCEPTS_AND_PAPERS;

	/**
	 * The feature id for the '<em><b>Checklists</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__CHECKLISTS = CONTENT_ELEMENT__CHECKLISTS;

	/**
	 * The feature id for the '<em><b>Guidelines</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__GUIDELINES = CONTENT_ELEMENT__GUIDELINES;

	/**
	 * The feature id for the '<em><b>Examples</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__EXAMPLES = CONTENT_ELEMENT__EXAMPLES;

	/**
	 * The feature id for the '<em><b>Assets</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__ASSETS = CONTENT_ELEMENT__ASSETS;

	/**
	 * The feature id for the '<em><b>Termdefinition</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__TERMDEFINITION = CONTENT_ELEMENT__TERMDEFINITION;

	/**
	 * The feature id for the '<em><b>Fulfills</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__FULFILLS = CONTENT_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Modifies</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__MODIFIES = CONTENT_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Responsible For</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__RESPONSIBLE_FOR = CONTENT_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Role</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_FEATURE_COUNT = CONTENT_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTION__NAME = CONTENT_DESCRIPTION__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTION__GUID = CONTENT_DESCRIPTION__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTION__PRESENTATION_NAME = CONTENT_DESCRIPTION__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTION__BRIEF_DESCRIPTION = CONTENT_DESCRIPTION__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTION__OWNED_RULES = CONTENT_DESCRIPTION__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTION__METHOD_ELEMENT_PROPERTY = CONTENT_DESCRIPTION__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTION__KIND = CONTENT_DESCRIPTION__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTION__SUPPRESSED = CONTENT_DESCRIPTION__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTION__ORDERING_GUIDE = CONTENT_DESCRIPTION__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Authors</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTION__AUTHORS = CONTENT_DESCRIPTION__AUTHORS;

	/**
	 * The feature id for the '<em><b>Change Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTION__CHANGE_DATE = CONTENT_DESCRIPTION__CHANGE_DATE;

	/**
	 * The feature id for the '<em><b>Change Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTION__CHANGE_DESCRIPTION = CONTENT_DESCRIPTION__CHANGE_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTION__VERSION = CONTENT_DESCRIPTION__VERSION;

	/**
	 * The feature id for the '<em><b>Copyright Statement</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTION__COPYRIGHT_STATEMENT = CONTENT_DESCRIPTION__COPYRIGHT_STATEMENT;

	/**
	 * The feature id for the '<em><b>Main Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTION__MAIN_DESCRIPTION = CONTENT_DESCRIPTION__MAIN_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Sections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTION__SECTIONS = CONTENT_DESCRIPTION__SECTIONS;

	/**
	 * The feature id for the '<em><b>External Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTION__EXTERNAL_ID = CONTENT_DESCRIPTION__EXTERNAL_ID;

	/**
	 * The feature id for the '<em><b>Key Considerations</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTION__KEY_CONSIDERATIONS = CONTENT_DESCRIPTION__KEY_CONSIDERATIONS;

	/**
	 * The feature id for the '<em><b>Long Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTION__LONG_PRESENTATION_NAME = CONTENT_DESCRIPTION__LONG_PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Purpose</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTION__PURPOSE = CONTENT_DESCRIPTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Impact Of Not Having</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTION__IMPACT_OF_NOT_HAVING = CONTENT_DESCRIPTION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Reasons For Not Needing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTION__REASONS_FOR_NOT_NEEDING = CONTENT_DESCRIPTION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Work Product Description</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTION_FEATURE_COUNT = CONTENT_DESCRIPTION_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_DESCRIPTION__NAME = WORK_PRODUCT_DESCRIPTION__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_DESCRIPTION__GUID = WORK_PRODUCT_DESCRIPTION__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_DESCRIPTION__PRESENTATION_NAME = WORK_PRODUCT_DESCRIPTION__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_DESCRIPTION__BRIEF_DESCRIPTION = WORK_PRODUCT_DESCRIPTION__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_DESCRIPTION__OWNED_RULES = WORK_PRODUCT_DESCRIPTION__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_DESCRIPTION__METHOD_ELEMENT_PROPERTY = WORK_PRODUCT_DESCRIPTION__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_DESCRIPTION__KIND = WORK_PRODUCT_DESCRIPTION__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_DESCRIPTION__SUPPRESSED = WORK_PRODUCT_DESCRIPTION__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_DESCRIPTION__ORDERING_GUIDE = WORK_PRODUCT_DESCRIPTION__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Authors</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_DESCRIPTION__AUTHORS = WORK_PRODUCT_DESCRIPTION__AUTHORS;

	/**
	 * The feature id for the '<em><b>Change Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_DESCRIPTION__CHANGE_DATE = WORK_PRODUCT_DESCRIPTION__CHANGE_DATE;

	/**
	 * The feature id for the '<em><b>Change Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_DESCRIPTION__CHANGE_DESCRIPTION = WORK_PRODUCT_DESCRIPTION__CHANGE_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_DESCRIPTION__VERSION = WORK_PRODUCT_DESCRIPTION__VERSION;

	/**
	 * The feature id for the '<em><b>Copyright Statement</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_DESCRIPTION__COPYRIGHT_STATEMENT = WORK_PRODUCT_DESCRIPTION__COPYRIGHT_STATEMENT;

	/**
	 * The feature id for the '<em><b>Main Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_DESCRIPTION__MAIN_DESCRIPTION = WORK_PRODUCT_DESCRIPTION__MAIN_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Sections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_DESCRIPTION__SECTIONS = WORK_PRODUCT_DESCRIPTION__SECTIONS;

	/**
	 * The feature id for the '<em><b>External Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_DESCRIPTION__EXTERNAL_ID = WORK_PRODUCT_DESCRIPTION__EXTERNAL_ID;

	/**
	 * The feature id for the '<em><b>Key Considerations</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_DESCRIPTION__KEY_CONSIDERATIONS = WORK_PRODUCT_DESCRIPTION__KEY_CONSIDERATIONS;

	/**
	 * The feature id for the '<em><b>Long Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_DESCRIPTION__LONG_PRESENTATION_NAME = WORK_PRODUCT_DESCRIPTION__LONG_PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Purpose</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_DESCRIPTION__PURPOSE = WORK_PRODUCT_DESCRIPTION__PURPOSE;

	/**
	 * The feature id for the '<em><b>Impact Of Not Having</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_DESCRIPTION__IMPACT_OF_NOT_HAVING = WORK_PRODUCT_DESCRIPTION__IMPACT_OF_NOT_HAVING;

	/**
	 * The feature id for the '<em><b>Reasons For Not Needing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_DESCRIPTION__REASONS_FOR_NOT_NEEDING = WORK_PRODUCT_DESCRIPTION__REASONS_FOR_NOT_NEEDING;

	/**
	 * The feature id for the '<em><b>Brief Outline</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_DESCRIPTION__BRIEF_OUTLINE = WORK_PRODUCT_DESCRIPTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Representation Options</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_DESCRIPTION__REPRESENTATION_OPTIONS = WORK_PRODUCT_DESCRIPTION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Representation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_DESCRIPTION__REPRESENTATION = WORK_PRODUCT_DESCRIPTION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Notation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_DESCRIPTION__NOTATION = WORK_PRODUCT_DESCRIPTION_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Artifact Description</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_DESCRIPTION_FEATURE_COUNT = WORK_PRODUCT_DESCRIPTION_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE_DESCRIPTION__NAME = WORK_PRODUCT_DESCRIPTION__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE_DESCRIPTION__GUID = WORK_PRODUCT_DESCRIPTION__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE_DESCRIPTION__PRESENTATION_NAME = WORK_PRODUCT_DESCRIPTION__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE_DESCRIPTION__BRIEF_DESCRIPTION = WORK_PRODUCT_DESCRIPTION__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE_DESCRIPTION__OWNED_RULES = WORK_PRODUCT_DESCRIPTION__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE_DESCRIPTION__METHOD_ELEMENT_PROPERTY = WORK_PRODUCT_DESCRIPTION__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE_DESCRIPTION__KIND = WORK_PRODUCT_DESCRIPTION__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE_DESCRIPTION__SUPPRESSED = WORK_PRODUCT_DESCRIPTION__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE_DESCRIPTION__ORDERING_GUIDE = WORK_PRODUCT_DESCRIPTION__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Authors</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE_DESCRIPTION__AUTHORS = WORK_PRODUCT_DESCRIPTION__AUTHORS;

	/**
	 * The feature id for the '<em><b>Change Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE_DESCRIPTION__CHANGE_DATE = WORK_PRODUCT_DESCRIPTION__CHANGE_DATE;

	/**
	 * The feature id for the '<em><b>Change Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE_DESCRIPTION__CHANGE_DESCRIPTION = WORK_PRODUCT_DESCRIPTION__CHANGE_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE_DESCRIPTION__VERSION = WORK_PRODUCT_DESCRIPTION__VERSION;

	/**
	 * The feature id for the '<em><b>Copyright Statement</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE_DESCRIPTION__COPYRIGHT_STATEMENT = WORK_PRODUCT_DESCRIPTION__COPYRIGHT_STATEMENT;

	/**
	 * The feature id for the '<em><b>Main Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE_DESCRIPTION__MAIN_DESCRIPTION = WORK_PRODUCT_DESCRIPTION__MAIN_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Sections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE_DESCRIPTION__SECTIONS = WORK_PRODUCT_DESCRIPTION__SECTIONS;

	/**
	 * The feature id for the '<em><b>External Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE_DESCRIPTION__EXTERNAL_ID = WORK_PRODUCT_DESCRIPTION__EXTERNAL_ID;

	/**
	 * The feature id for the '<em><b>Key Considerations</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE_DESCRIPTION__KEY_CONSIDERATIONS = WORK_PRODUCT_DESCRIPTION__KEY_CONSIDERATIONS;

	/**
	 * The feature id for the '<em><b>Long Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE_DESCRIPTION__LONG_PRESENTATION_NAME = WORK_PRODUCT_DESCRIPTION__LONG_PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Purpose</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE_DESCRIPTION__PURPOSE = WORK_PRODUCT_DESCRIPTION__PURPOSE;

	/**
	 * The feature id for the '<em><b>Impact Of Not Having</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE_DESCRIPTION__IMPACT_OF_NOT_HAVING = WORK_PRODUCT_DESCRIPTION__IMPACT_OF_NOT_HAVING;

	/**
	 * The feature id for the '<em><b>Reasons For Not Needing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE_DESCRIPTION__REASONS_FOR_NOT_NEEDING = WORK_PRODUCT_DESCRIPTION__REASONS_FOR_NOT_NEEDING;

	/**
	 * The feature id for the '<em><b>External Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE_DESCRIPTION__EXTERNAL_DESCRIPTION = WORK_PRODUCT_DESCRIPTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Packaging Guidance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE_DESCRIPTION__PACKAGING_GUIDANCE = WORK_PRODUCT_DESCRIPTION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Deliverable Description</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE_DESCRIPTION_FEATURE_COUNT = WORK_PRODUCT_DESCRIPTION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTION__NAME = CONTENT_DESCRIPTION__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTION__GUID = CONTENT_DESCRIPTION__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTION__PRESENTATION_NAME = CONTENT_DESCRIPTION__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTION__BRIEF_DESCRIPTION = CONTENT_DESCRIPTION__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTION__OWNED_RULES = CONTENT_DESCRIPTION__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTION__METHOD_ELEMENT_PROPERTY = CONTENT_DESCRIPTION__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTION__KIND = CONTENT_DESCRIPTION__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTION__SUPPRESSED = CONTENT_DESCRIPTION__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTION__ORDERING_GUIDE = CONTENT_DESCRIPTION__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Authors</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTION__AUTHORS = CONTENT_DESCRIPTION__AUTHORS;

	/**
	 * The feature id for the '<em><b>Change Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTION__CHANGE_DATE = CONTENT_DESCRIPTION__CHANGE_DATE;

	/**
	 * The feature id for the '<em><b>Change Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTION__CHANGE_DESCRIPTION = CONTENT_DESCRIPTION__CHANGE_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTION__VERSION = CONTENT_DESCRIPTION__VERSION;

	/**
	 * The feature id for the '<em><b>Copyright Statement</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTION__COPYRIGHT_STATEMENT = CONTENT_DESCRIPTION__COPYRIGHT_STATEMENT;

	/**
	 * The feature id for the '<em><b>Main Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTION__MAIN_DESCRIPTION = CONTENT_DESCRIPTION__MAIN_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Sections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTION__SECTIONS = CONTENT_DESCRIPTION__SECTIONS;

	/**
	 * The feature id for the '<em><b>External Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTION__EXTERNAL_ID = CONTENT_DESCRIPTION__EXTERNAL_ID;

	/**
	 * The feature id for the '<em><b>Key Considerations</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTION__KEY_CONSIDERATIONS = CONTENT_DESCRIPTION__KEY_CONSIDERATIONS;

	/**
	 * The feature id for the '<em><b>Long Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTION__LONG_PRESENTATION_NAME = CONTENT_DESCRIPTION__LONG_PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Skills</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTION__SKILLS = CONTENT_DESCRIPTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Assignment Approaches</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTION__ASSIGNMENT_APPROACHES = CONTENT_DESCRIPTION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Synonyms</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTION__SYNONYMS = CONTENT_DESCRIPTION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Role Description</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTION_FEATURE_COUNT = CONTENT_DESCRIPTION_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTION__NAME = CONTENT_DESCRIPTION__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTION__GUID = CONTENT_DESCRIPTION__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTION__PRESENTATION_NAME = CONTENT_DESCRIPTION__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTION__BRIEF_DESCRIPTION = CONTENT_DESCRIPTION__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTION__OWNED_RULES = CONTENT_DESCRIPTION__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTION__METHOD_ELEMENT_PROPERTY = CONTENT_DESCRIPTION__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTION__KIND = CONTENT_DESCRIPTION__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTION__SUPPRESSED = CONTENT_DESCRIPTION__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTION__ORDERING_GUIDE = CONTENT_DESCRIPTION__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Authors</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTION__AUTHORS = CONTENT_DESCRIPTION__AUTHORS;

	/**
	 * The feature id for the '<em><b>Change Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTION__CHANGE_DATE = CONTENT_DESCRIPTION__CHANGE_DATE;

	/**
	 * The feature id for the '<em><b>Change Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTION__CHANGE_DESCRIPTION = CONTENT_DESCRIPTION__CHANGE_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTION__VERSION = CONTENT_DESCRIPTION__VERSION;

	/**
	 * The feature id for the '<em><b>Copyright Statement</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTION__COPYRIGHT_STATEMENT = CONTENT_DESCRIPTION__COPYRIGHT_STATEMENT;

	/**
	 * The feature id for the '<em><b>Main Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTION__MAIN_DESCRIPTION = CONTENT_DESCRIPTION__MAIN_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Sections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTION__SECTIONS = CONTENT_DESCRIPTION__SECTIONS;

	/**
	 * The feature id for the '<em><b>External Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTION__EXTERNAL_ID = CONTENT_DESCRIPTION__EXTERNAL_ID;

	/**
	 * The feature id for the '<em><b>Key Considerations</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTION__KEY_CONSIDERATIONS = CONTENT_DESCRIPTION__KEY_CONSIDERATIONS;

	/**
	 * The feature id for the '<em><b>Long Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTION__LONG_PRESENTATION_NAME = CONTENT_DESCRIPTION__LONG_PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Purpose</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTION__PURPOSE = CONTENT_DESCRIPTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Alternatives</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTION__ALTERNATIVES = CONTENT_DESCRIPTION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Task Description</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTION_FEATURE_COUNT = CONTENT_DESCRIPTION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE_DESCRIPTION__NAME = CONTENT_DESCRIPTION__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE_DESCRIPTION__GUID = CONTENT_DESCRIPTION__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE_DESCRIPTION__PRESENTATION_NAME = CONTENT_DESCRIPTION__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE_DESCRIPTION__BRIEF_DESCRIPTION = CONTENT_DESCRIPTION__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE_DESCRIPTION__OWNED_RULES = CONTENT_DESCRIPTION__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE_DESCRIPTION__METHOD_ELEMENT_PROPERTY = CONTENT_DESCRIPTION__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE_DESCRIPTION__KIND = CONTENT_DESCRIPTION__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE_DESCRIPTION__SUPPRESSED = CONTENT_DESCRIPTION__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE_DESCRIPTION__ORDERING_GUIDE = CONTENT_DESCRIPTION__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Authors</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE_DESCRIPTION__AUTHORS = CONTENT_DESCRIPTION__AUTHORS;

	/**
	 * The feature id for the '<em><b>Change Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE_DESCRIPTION__CHANGE_DATE = CONTENT_DESCRIPTION__CHANGE_DATE;

	/**
	 * The feature id for the '<em><b>Change Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE_DESCRIPTION__CHANGE_DESCRIPTION = CONTENT_DESCRIPTION__CHANGE_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE_DESCRIPTION__VERSION = CONTENT_DESCRIPTION__VERSION;

	/**
	 * The feature id for the '<em><b>Copyright Statement</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE_DESCRIPTION__COPYRIGHT_STATEMENT = CONTENT_DESCRIPTION__COPYRIGHT_STATEMENT;

	/**
	 * The feature id for the '<em><b>Main Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE_DESCRIPTION__MAIN_DESCRIPTION = CONTENT_DESCRIPTION__MAIN_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Sections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE_DESCRIPTION__SECTIONS = CONTENT_DESCRIPTION__SECTIONS;

	/**
	 * The feature id for the '<em><b>External Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE_DESCRIPTION__EXTERNAL_ID = CONTENT_DESCRIPTION__EXTERNAL_ID;

	/**
	 * The feature id for the '<em><b>Key Considerations</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE_DESCRIPTION__KEY_CONSIDERATIONS = CONTENT_DESCRIPTION__KEY_CONSIDERATIONS;

	/**
	 * The feature id for the '<em><b>Long Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE_DESCRIPTION__LONG_PRESENTATION_NAME = CONTENT_DESCRIPTION__LONG_PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Attachments</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE_DESCRIPTION__ATTACHMENTS = CONTENT_DESCRIPTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Guidance Description</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE_DESCRIPTION_FEATURE_COUNT = CONTENT_DESCRIPTION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE_DESCRIPTION__NAME = CONTENT_DESCRIPTION__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE_DESCRIPTION__GUID = CONTENT_DESCRIPTION__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE_DESCRIPTION__PRESENTATION_NAME = CONTENT_DESCRIPTION__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE_DESCRIPTION__BRIEF_DESCRIPTION = CONTENT_DESCRIPTION__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE_DESCRIPTION__OWNED_RULES = CONTENT_DESCRIPTION__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE_DESCRIPTION__METHOD_ELEMENT_PROPERTY = CONTENT_DESCRIPTION__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE_DESCRIPTION__KIND = CONTENT_DESCRIPTION__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE_DESCRIPTION__SUPPRESSED = CONTENT_DESCRIPTION__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE_DESCRIPTION__ORDERING_GUIDE = CONTENT_DESCRIPTION__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Authors</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE_DESCRIPTION__AUTHORS = CONTENT_DESCRIPTION__AUTHORS;

	/**
	 * The feature id for the '<em><b>Change Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE_DESCRIPTION__CHANGE_DATE = CONTENT_DESCRIPTION__CHANGE_DATE;

	/**
	 * The feature id for the '<em><b>Change Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE_DESCRIPTION__CHANGE_DESCRIPTION = CONTENT_DESCRIPTION__CHANGE_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE_DESCRIPTION__VERSION = CONTENT_DESCRIPTION__VERSION;

	/**
	 * The feature id for the '<em><b>Copyright Statement</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE_DESCRIPTION__COPYRIGHT_STATEMENT = CONTENT_DESCRIPTION__COPYRIGHT_STATEMENT;

	/**
	 * The feature id for the '<em><b>Main Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE_DESCRIPTION__MAIN_DESCRIPTION = CONTENT_DESCRIPTION__MAIN_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Sections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE_DESCRIPTION__SECTIONS = CONTENT_DESCRIPTION__SECTIONS;

	/**
	 * The feature id for the '<em><b>External Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE_DESCRIPTION__EXTERNAL_ID = CONTENT_DESCRIPTION__EXTERNAL_ID;

	/**
	 * The feature id for the '<em><b>Key Considerations</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE_DESCRIPTION__KEY_CONSIDERATIONS = CONTENT_DESCRIPTION__KEY_CONSIDERATIONS;

	/**
	 * The feature id for the '<em><b>Long Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE_DESCRIPTION__LONG_PRESENTATION_NAME = CONTENT_DESCRIPTION__LONG_PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Additional Info</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE_DESCRIPTION__ADDITIONAL_INFO = CONTENT_DESCRIPTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Problem</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE_DESCRIPTION__PROBLEM = CONTENT_DESCRIPTION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Background</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE_DESCRIPTION__BACKGROUND = CONTENT_DESCRIPTION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Goals</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE_DESCRIPTION__GOALS = CONTENT_DESCRIPTION_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Application</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE_DESCRIPTION__APPLICATION = CONTENT_DESCRIPTION_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Levels Of Adoption</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE_DESCRIPTION__LEVELS_OF_ADOPTION = CONTENT_DESCRIPTION_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Practice Description</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE_DESCRIPTION_FEATURE_COUNT = CONTENT_DESCRIPTION_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_CATEGORY__NAME = CONTENT_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_CATEGORY__GUID = CONTENT_ELEMENT__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_CATEGORY__PRESENTATION_NAME = CONTENT_ELEMENT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_CATEGORY__BRIEF_DESCRIPTION = CONTENT_ELEMENT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_CATEGORY__OWNED_RULES = CONTENT_ELEMENT__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_CATEGORY__METHOD_ELEMENT_PROPERTY = CONTENT_ELEMENT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_CATEGORY__KIND = CONTENT_ELEMENT__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_CATEGORY__SUPPRESSED = CONTENT_ELEMENT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_CATEGORY__ORDERING_GUIDE = CONTENT_ELEMENT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_CATEGORY__IS_ABSTRACT = CONTENT_ELEMENT__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_CATEGORY__PRESENTATION = CONTENT_ELEMENT__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_CATEGORY__SHAPEICON = CONTENT_ELEMENT__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_CATEGORY__NODEICON = CONTENT_ELEMENT__NODEICON;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_CATEGORY__VARIABILITY_TYPE = CONTENT_ELEMENT__VARIABILITY_TYPE;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_CATEGORY__VARIABILITY_BASED_ON_ELEMENT = CONTENT_ELEMENT__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Supporting Materials</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_CATEGORY__SUPPORTING_MATERIALS = CONTENT_ELEMENT__SUPPORTING_MATERIALS;

	/**
	 * The feature id for the '<em><b>Concepts And Papers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_CATEGORY__CONCEPTS_AND_PAPERS = CONTENT_ELEMENT__CONCEPTS_AND_PAPERS;

	/**
	 * The feature id for the '<em><b>Checklists</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_CATEGORY__CHECKLISTS = CONTENT_ELEMENT__CHECKLISTS;

	/**
	 * The feature id for the '<em><b>Guidelines</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_CATEGORY__GUIDELINES = CONTENT_ELEMENT__GUIDELINES;

	/**
	 * The feature id for the '<em><b>Examples</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_CATEGORY__EXAMPLES = CONTENT_ELEMENT__EXAMPLES;

	/**
	 * The feature id for the '<em><b>Assets</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_CATEGORY__ASSETS = CONTENT_ELEMENT__ASSETS;

	/**
	 * The feature id for the '<em><b>Termdefinition</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_CATEGORY__TERMDEFINITION = CONTENT_ELEMENT__TERMDEFINITION;

	/**
	 * The number of structural features of the '<em>Content Category</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_CATEGORY_FEATURE_COUNT = CONTENT_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET__NAME = CONTENT_CATEGORY__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET__GUID = CONTENT_CATEGORY__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET__PRESENTATION_NAME = CONTENT_CATEGORY__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET__BRIEF_DESCRIPTION = CONTENT_CATEGORY__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET__OWNED_RULES = CONTENT_CATEGORY__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET__METHOD_ELEMENT_PROPERTY = CONTENT_CATEGORY__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET__KIND = CONTENT_CATEGORY__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET__SUPPRESSED = CONTENT_CATEGORY__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET__ORDERING_GUIDE = CONTENT_CATEGORY__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET__IS_ABSTRACT = CONTENT_CATEGORY__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET__PRESENTATION = CONTENT_CATEGORY__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET__SHAPEICON = CONTENT_CATEGORY__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET__NODEICON = CONTENT_CATEGORY__NODEICON;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET__VARIABILITY_TYPE = CONTENT_CATEGORY__VARIABILITY_TYPE;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET__VARIABILITY_BASED_ON_ELEMENT = CONTENT_CATEGORY__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Supporting Materials</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET__SUPPORTING_MATERIALS = CONTENT_CATEGORY__SUPPORTING_MATERIALS;

	/**
	 * The feature id for the '<em><b>Concepts And Papers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET__CONCEPTS_AND_PAPERS = CONTENT_CATEGORY__CONCEPTS_AND_PAPERS;

	/**
	 * The feature id for the '<em><b>Checklists</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET__CHECKLISTS = CONTENT_CATEGORY__CHECKLISTS;

	/**
	 * The feature id for the '<em><b>Guidelines</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET__GUIDELINES = CONTENT_CATEGORY__GUIDELINES;

	/**
	 * The feature id for the '<em><b>Examples</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET__EXAMPLES = CONTENT_CATEGORY__EXAMPLES;

	/**
	 * The feature id for the '<em><b>Assets</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET__ASSETS = CONTENT_CATEGORY__ASSETS;

	/**
	 * The feature id for the '<em><b>Termdefinition</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET__TERMDEFINITION = CONTENT_CATEGORY__TERMDEFINITION;

	/**
	 * The feature id for the '<em><b>Roles</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET__ROLES = CONTENT_CATEGORY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Role Set</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET_FEATURE_COUNT = CONTENT_CATEGORY_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN__NAME = CONTENT_CATEGORY__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN__GUID = CONTENT_CATEGORY__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN__PRESENTATION_NAME = CONTENT_CATEGORY__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN__BRIEF_DESCRIPTION = CONTENT_CATEGORY__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN__OWNED_RULES = CONTENT_CATEGORY__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN__METHOD_ELEMENT_PROPERTY = CONTENT_CATEGORY__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN__KIND = CONTENT_CATEGORY__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN__SUPPRESSED = CONTENT_CATEGORY__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN__ORDERING_GUIDE = CONTENT_CATEGORY__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN__IS_ABSTRACT = CONTENT_CATEGORY__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN__PRESENTATION = CONTENT_CATEGORY__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN__SHAPEICON = CONTENT_CATEGORY__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN__NODEICON = CONTENT_CATEGORY__NODEICON;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN__VARIABILITY_TYPE = CONTENT_CATEGORY__VARIABILITY_TYPE;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN__VARIABILITY_BASED_ON_ELEMENT = CONTENT_CATEGORY__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Supporting Materials</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN__SUPPORTING_MATERIALS = CONTENT_CATEGORY__SUPPORTING_MATERIALS;

	/**
	 * The feature id for the '<em><b>Concepts And Papers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN__CONCEPTS_AND_PAPERS = CONTENT_CATEGORY__CONCEPTS_AND_PAPERS;

	/**
	 * The feature id for the '<em><b>Checklists</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN__CHECKLISTS = CONTENT_CATEGORY__CHECKLISTS;

	/**
	 * The feature id for the '<em><b>Guidelines</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN__GUIDELINES = CONTENT_CATEGORY__GUIDELINES;

	/**
	 * The feature id for the '<em><b>Examples</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN__EXAMPLES = CONTENT_CATEGORY__EXAMPLES;

	/**
	 * The feature id for the '<em><b>Assets</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN__ASSETS = CONTENT_CATEGORY__ASSETS;

	/**
	 * The feature id for the '<em><b>Termdefinition</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN__TERMDEFINITION = CONTENT_CATEGORY__TERMDEFINITION;

	/**
	 * The feature id for the '<em><b>Work Products</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN__WORK_PRODUCTS = CONTENT_CATEGORY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Subdomains</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN__SUBDOMAINS = CONTENT_CATEGORY_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Domain</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_FEATURE_COUNT = CONTENT_CATEGORY_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_TYPE__NAME = CONTENT_CATEGORY__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_TYPE__GUID = CONTENT_CATEGORY__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_TYPE__PRESENTATION_NAME = CONTENT_CATEGORY__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_TYPE__BRIEF_DESCRIPTION = CONTENT_CATEGORY__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_TYPE__OWNED_RULES = CONTENT_CATEGORY__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_TYPE__METHOD_ELEMENT_PROPERTY = CONTENT_CATEGORY__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_TYPE__KIND = CONTENT_CATEGORY__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_TYPE__SUPPRESSED = CONTENT_CATEGORY__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_TYPE__ORDERING_GUIDE = CONTENT_CATEGORY__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_TYPE__IS_ABSTRACT = CONTENT_CATEGORY__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_TYPE__PRESENTATION = CONTENT_CATEGORY__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_TYPE__SHAPEICON = CONTENT_CATEGORY__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_TYPE__NODEICON = CONTENT_CATEGORY__NODEICON;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_TYPE__VARIABILITY_TYPE = CONTENT_CATEGORY__VARIABILITY_TYPE;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_TYPE__VARIABILITY_BASED_ON_ELEMENT = CONTENT_CATEGORY__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Supporting Materials</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_TYPE__SUPPORTING_MATERIALS = CONTENT_CATEGORY__SUPPORTING_MATERIALS;

	/**
	 * The feature id for the '<em><b>Concepts And Papers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_TYPE__CONCEPTS_AND_PAPERS = CONTENT_CATEGORY__CONCEPTS_AND_PAPERS;

	/**
	 * The feature id for the '<em><b>Checklists</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_TYPE__CHECKLISTS = CONTENT_CATEGORY__CHECKLISTS;

	/**
	 * The feature id for the '<em><b>Guidelines</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_TYPE__GUIDELINES = CONTENT_CATEGORY__GUIDELINES;

	/**
	 * The feature id for the '<em><b>Examples</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_TYPE__EXAMPLES = CONTENT_CATEGORY__EXAMPLES;

	/**
	 * The feature id for the '<em><b>Assets</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_TYPE__ASSETS = CONTENT_CATEGORY__ASSETS;

	/**
	 * The feature id for the '<em><b>Termdefinition</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_TYPE__TERMDEFINITION = CONTENT_CATEGORY__TERMDEFINITION;

	/**
	 * The feature id for the '<em><b>Work Products</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_TYPE__WORK_PRODUCTS = CONTENT_CATEGORY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Work Product Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_TYPE_FEATURE_COUNT = CONTENT_CATEGORY_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE_GROUPING__NAME = CONTENT_CATEGORY__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE_GROUPING__GUID = CONTENT_CATEGORY__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE_GROUPING__PRESENTATION_NAME = CONTENT_CATEGORY__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE_GROUPING__BRIEF_DESCRIPTION = CONTENT_CATEGORY__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE_GROUPING__OWNED_RULES = CONTENT_CATEGORY__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE_GROUPING__METHOD_ELEMENT_PROPERTY = CONTENT_CATEGORY__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE_GROUPING__KIND = CONTENT_CATEGORY__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE_GROUPING__SUPPRESSED = CONTENT_CATEGORY__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE_GROUPING__ORDERING_GUIDE = CONTENT_CATEGORY__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE_GROUPING__IS_ABSTRACT = CONTENT_CATEGORY__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE_GROUPING__PRESENTATION = CONTENT_CATEGORY__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE_GROUPING__SHAPEICON = CONTENT_CATEGORY__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE_GROUPING__NODEICON = CONTENT_CATEGORY__NODEICON;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE_GROUPING__VARIABILITY_TYPE = CONTENT_CATEGORY__VARIABILITY_TYPE;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE_GROUPING__VARIABILITY_BASED_ON_ELEMENT = CONTENT_CATEGORY__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Supporting Materials</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE_GROUPING__SUPPORTING_MATERIALS = CONTENT_CATEGORY__SUPPORTING_MATERIALS;

	/**
	 * The feature id for the '<em><b>Concepts And Papers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE_GROUPING__CONCEPTS_AND_PAPERS = CONTENT_CATEGORY__CONCEPTS_AND_PAPERS;

	/**
	 * The feature id for the '<em><b>Checklists</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE_GROUPING__CHECKLISTS = CONTENT_CATEGORY__CHECKLISTS;

	/**
	 * The feature id for the '<em><b>Guidelines</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE_GROUPING__GUIDELINES = CONTENT_CATEGORY__GUIDELINES;

	/**
	 * The feature id for the '<em><b>Examples</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE_GROUPING__EXAMPLES = CONTENT_CATEGORY__EXAMPLES;

	/**
	 * The feature id for the '<em><b>Assets</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE_GROUPING__ASSETS = CONTENT_CATEGORY__ASSETS;

	/**
	 * The feature id for the '<em><b>Termdefinition</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE_GROUPING__TERMDEFINITION = CONTENT_CATEGORY__TERMDEFINITION;

	/**
	 * The feature id for the '<em><b>Disciplines</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE_GROUPING__DISCIPLINES = CONTENT_CATEGORY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Discipline Grouping</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE_GROUPING_FEATURE_COUNT = CONTENT_CATEGORY_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE__NAME = CONTENT_CATEGORY__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE__GUID = CONTENT_CATEGORY__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE__PRESENTATION_NAME = CONTENT_CATEGORY__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE__BRIEF_DESCRIPTION = CONTENT_CATEGORY__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE__OWNED_RULES = CONTENT_CATEGORY__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE__METHOD_ELEMENT_PROPERTY = CONTENT_CATEGORY__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE__KIND = CONTENT_CATEGORY__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE__SUPPRESSED = CONTENT_CATEGORY__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE__ORDERING_GUIDE = CONTENT_CATEGORY__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE__IS_ABSTRACT = CONTENT_CATEGORY__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE__PRESENTATION = CONTENT_CATEGORY__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE__SHAPEICON = CONTENT_CATEGORY__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE__NODEICON = CONTENT_CATEGORY__NODEICON;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE__VARIABILITY_TYPE = CONTENT_CATEGORY__VARIABILITY_TYPE;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE__VARIABILITY_BASED_ON_ELEMENT = CONTENT_CATEGORY__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Supporting Materials</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE__SUPPORTING_MATERIALS = CONTENT_CATEGORY__SUPPORTING_MATERIALS;

	/**
	 * The feature id for the '<em><b>Concepts And Papers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE__CONCEPTS_AND_PAPERS = CONTENT_CATEGORY__CONCEPTS_AND_PAPERS;

	/**
	 * The feature id for the '<em><b>Checklists</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE__CHECKLISTS = CONTENT_CATEGORY__CHECKLISTS;

	/**
	 * The feature id for the '<em><b>Guidelines</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE__GUIDELINES = CONTENT_CATEGORY__GUIDELINES;

	/**
	 * The feature id for the '<em><b>Examples</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE__EXAMPLES = CONTENT_CATEGORY__EXAMPLES;

	/**
	 * The feature id for the '<em><b>Assets</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE__ASSETS = CONTENT_CATEGORY__ASSETS;

	/**
	 * The feature id for the '<em><b>Termdefinition</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE__TERMDEFINITION = CONTENT_CATEGORY__TERMDEFINITION;

	/**
	 * The feature id for the '<em><b>Tasks</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE__TASKS = CONTENT_CATEGORY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Subdiscipline</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE__SUBDISCIPLINE = CONTENT_CATEGORY_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Reference Workflows</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE__REFERENCE_WORKFLOWS = CONTENT_CATEGORY_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Discipline</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE_FEATURE_COUNT = CONTENT_CATEGORY_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_ELEMENT__NAME = DESCRIBABLE_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_ELEMENT__GUID = DESCRIBABLE_ELEMENT__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_ELEMENT__PRESENTATION_NAME = DESCRIBABLE_ELEMENT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_ELEMENT__BRIEF_DESCRIPTION = DESCRIBABLE_ELEMENT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_ELEMENT__OWNED_RULES = DESCRIBABLE_ELEMENT__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_ELEMENT__METHOD_ELEMENT_PROPERTY = DESCRIBABLE_ELEMENT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_ELEMENT__KIND = DESCRIBABLE_ELEMENT__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_ELEMENT__SUPPRESSED = DESCRIBABLE_ELEMENT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_ELEMENT__ORDERING_GUIDE = DESCRIBABLE_ELEMENT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_ELEMENT__IS_ABSTRACT = DESCRIBABLE_ELEMENT__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_ELEMENT__PRESENTATION = DESCRIBABLE_ELEMENT__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_ELEMENT__SHAPEICON = DESCRIBABLE_ELEMENT__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_ELEMENT__NODEICON = DESCRIBABLE_ELEMENT__NODEICON;

	/**
	 * The number of structural features of the '<em>Process Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_ELEMENT_FEATURE_COUNT = DESCRIBABLE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT__NAME = PROCESS_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT__GUID = PROCESS_ELEMENT__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT__PRESENTATION_NAME = PROCESS_ELEMENT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT__BRIEF_DESCRIPTION = PROCESS_ELEMENT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT__OWNED_RULES = PROCESS_ELEMENT__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT__METHOD_ELEMENT_PROPERTY = PROCESS_ELEMENT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT__KIND = PROCESS_ELEMENT__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT__SUPPRESSED = PROCESS_ELEMENT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT__ORDERING_GUIDE = PROCESS_ELEMENT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT__IS_ABSTRACT = PROCESS_ELEMENT__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT__PRESENTATION = PROCESS_ELEMENT__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT__SHAPEICON = PROCESS_ELEMENT__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT__NODEICON = PROCESS_ELEMENT__NODEICON;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT__PREFIX = PROCESS_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Is Planned</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT__IS_PLANNED = PROCESS_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Has Multiple Occurrences</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT__HAS_MULTIPLE_OCCURRENCES = PROCESS_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Is Optional</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT__IS_OPTIONAL = PROCESS_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Presented After</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT__PRESENTED_AFTER = PROCESS_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Presented Before</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT__PRESENTED_BEFORE = PROCESS_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Planning Data</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT__PLANNING_DATA = PROCESS_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Super Activities</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT__SUPER_ACTIVITIES = PROCESS_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Checklists</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT__CHECKLISTS = PROCESS_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Concepts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT__CONCEPTS = PROCESS_ELEMENT_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Examples</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT__EXAMPLES = PROCESS_ELEMENT_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Guidelines</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT__GUIDELINES = PROCESS_ELEMENT_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Reusable Assets</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT__REUSABLE_ASSETS = PROCESS_ELEMENT_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Supporting Materials</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT__SUPPORTING_MATERIALS = PROCESS_ELEMENT_FEATURE_COUNT + 13;

	/**
	 * The feature id for the '<em><b>Templates</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT__TEMPLATES = PROCESS_ELEMENT_FEATURE_COUNT + 14;

	/**
	 * The feature id for the '<em><b>Reports</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT__REPORTS = PROCESS_ELEMENT_FEATURE_COUNT + 15;

	/**
	 * The feature id for the '<em><b>Estimationconsiderations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT__ESTIMATIONCONSIDERATIONS = PROCESS_ELEMENT_FEATURE_COUNT + 16;

	/**
	 * The feature id for the '<em><b>Toolmentor</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT__TOOLMENTOR = PROCESS_ELEMENT_FEATURE_COUNT + 17;

	/**
	 * The number of structural features of the '<em>Breakdown Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT_FEATURE_COUNT = PROCESS_ELEMENT_FEATURE_COUNT + 18;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__NAME = BREAKDOWN_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__GUID = BREAKDOWN_ELEMENT__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__PRESENTATION_NAME = BREAKDOWN_ELEMENT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__BRIEF_DESCRIPTION = BREAKDOWN_ELEMENT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__OWNED_RULES = BREAKDOWN_ELEMENT__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__METHOD_ELEMENT_PROPERTY = BREAKDOWN_ELEMENT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__KIND = BREAKDOWN_ELEMENT__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__SUPPRESSED = BREAKDOWN_ELEMENT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__ORDERING_GUIDE = BREAKDOWN_ELEMENT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__IS_ABSTRACT = BREAKDOWN_ELEMENT__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__PRESENTATION = BREAKDOWN_ELEMENT__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__SHAPEICON = BREAKDOWN_ELEMENT__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__NODEICON = BREAKDOWN_ELEMENT__NODEICON;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__PREFIX = BREAKDOWN_ELEMENT__PREFIX;

	/**
	 * The feature id for the '<em><b>Is Planned</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__IS_PLANNED = BREAKDOWN_ELEMENT__IS_PLANNED;

	/**
	 * The feature id for the '<em><b>Has Multiple Occurrences</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__HAS_MULTIPLE_OCCURRENCES = BREAKDOWN_ELEMENT__HAS_MULTIPLE_OCCURRENCES;

	/**
	 * The feature id for the '<em><b>Is Optional</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__IS_OPTIONAL = BREAKDOWN_ELEMENT__IS_OPTIONAL;

	/**
	 * The feature id for the '<em><b>Presented After</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__PRESENTED_AFTER = BREAKDOWN_ELEMENT__PRESENTED_AFTER;

	/**
	 * The feature id for the '<em><b>Presented Before</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__PRESENTED_BEFORE = BREAKDOWN_ELEMENT__PRESENTED_BEFORE;

	/**
	 * The feature id for the '<em><b>Planning Data</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__PLANNING_DATA = BREAKDOWN_ELEMENT__PLANNING_DATA;

	/**
	 * The feature id for the '<em><b>Super Activities</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__SUPER_ACTIVITIES = BREAKDOWN_ELEMENT__SUPER_ACTIVITIES;

	/**
	 * The feature id for the '<em><b>Checklists</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__CHECKLISTS = BREAKDOWN_ELEMENT__CHECKLISTS;

	/**
	 * The feature id for the '<em><b>Concepts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__CONCEPTS = BREAKDOWN_ELEMENT__CONCEPTS;

	/**
	 * The feature id for the '<em><b>Examples</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__EXAMPLES = BREAKDOWN_ELEMENT__EXAMPLES;

	/**
	 * The feature id for the '<em><b>Guidelines</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__GUIDELINES = BREAKDOWN_ELEMENT__GUIDELINES;

	/**
	 * The feature id for the '<em><b>Reusable Assets</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__REUSABLE_ASSETS = BREAKDOWN_ELEMENT__REUSABLE_ASSETS;

	/**
	 * The feature id for the '<em><b>Supporting Materials</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__SUPPORTING_MATERIALS = BREAKDOWN_ELEMENT__SUPPORTING_MATERIALS;

	/**
	 * The feature id for the '<em><b>Templates</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__TEMPLATES = BREAKDOWN_ELEMENT__TEMPLATES;

	/**
	 * The feature id for the '<em><b>Reports</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__REPORTS = BREAKDOWN_ELEMENT__REPORTS;

	/**
	 * The feature id for the '<em><b>Estimationconsiderations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__ESTIMATIONCONSIDERATIONS = BREAKDOWN_ELEMENT__ESTIMATIONCONSIDERATIONS;

	/**
	 * The feature id for the '<em><b>Toolmentor</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__TOOLMENTOR = BREAKDOWN_ELEMENT__TOOLMENTOR;

	/**
	 * The feature id for the '<em><b>Is Repeatable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__IS_REPEATABLE = BREAKDOWN_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Is Ongoing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__IS_ONGOING = BREAKDOWN_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Is Event Driven</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__IS_EVENT_DRIVEN = BREAKDOWN_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Link To Predecessor</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__LINK_TO_PREDECESSOR = BREAKDOWN_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Work Breakdown Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT_FEATURE_COUNT = BREAKDOWN_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__NAME = WORK_BREAKDOWN_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__GUID = WORK_BREAKDOWN_ELEMENT__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__PRESENTATION_NAME = WORK_BREAKDOWN_ELEMENT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__BRIEF_DESCRIPTION = WORK_BREAKDOWN_ELEMENT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__OWNED_RULES = WORK_BREAKDOWN_ELEMENT__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__METHOD_ELEMENT_PROPERTY = WORK_BREAKDOWN_ELEMENT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__KIND = WORK_BREAKDOWN_ELEMENT__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__SUPPRESSED = WORK_BREAKDOWN_ELEMENT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__ORDERING_GUIDE = WORK_BREAKDOWN_ELEMENT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__IS_ABSTRACT = WORK_BREAKDOWN_ELEMENT__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__PRESENTATION = WORK_BREAKDOWN_ELEMENT__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__SHAPEICON = WORK_BREAKDOWN_ELEMENT__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__NODEICON = WORK_BREAKDOWN_ELEMENT__NODEICON;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__PREFIX = WORK_BREAKDOWN_ELEMENT__PREFIX;

	/**
	 * The feature id for the '<em><b>Is Planned</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__IS_PLANNED = WORK_BREAKDOWN_ELEMENT__IS_PLANNED;

	/**
	 * The feature id for the '<em><b>Has Multiple Occurrences</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__HAS_MULTIPLE_OCCURRENCES = WORK_BREAKDOWN_ELEMENT__HAS_MULTIPLE_OCCURRENCES;

	/**
	 * The feature id for the '<em><b>Is Optional</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__IS_OPTIONAL = WORK_BREAKDOWN_ELEMENT__IS_OPTIONAL;

	/**
	 * The feature id for the '<em><b>Presented After</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__PRESENTED_AFTER = WORK_BREAKDOWN_ELEMENT__PRESENTED_AFTER;

	/**
	 * The feature id for the '<em><b>Presented Before</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__PRESENTED_BEFORE = WORK_BREAKDOWN_ELEMENT__PRESENTED_BEFORE;

	/**
	 * The feature id for the '<em><b>Planning Data</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__PLANNING_DATA = WORK_BREAKDOWN_ELEMENT__PLANNING_DATA;

	/**
	 * The feature id for the '<em><b>Super Activities</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__SUPER_ACTIVITIES = WORK_BREAKDOWN_ELEMENT__SUPER_ACTIVITIES;

	/**
	 * The feature id for the '<em><b>Checklists</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__CHECKLISTS = WORK_BREAKDOWN_ELEMENT__CHECKLISTS;

	/**
	 * The feature id for the '<em><b>Concepts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__CONCEPTS = WORK_BREAKDOWN_ELEMENT__CONCEPTS;

	/**
	 * The feature id for the '<em><b>Examples</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__EXAMPLES = WORK_BREAKDOWN_ELEMENT__EXAMPLES;

	/**
	 * The feature id for the '<em><b>Guidelines</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__GUIDELINES = WORK_BREAKDOWN_ELEMENT__GUIDELINES;

	/**
	 * The feature id for the '<em><b>Reusable Assets</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__REUSABLE_ASSETS = WORK_BREAKDOWN_ELEMENT__REUSABLE_ASSETS;

	/**
	 * The feature id for the '<em><b>Supporting Materials</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__SUPPORTING_MATERIALS = WORK_BREAKDOWN_ELEMENT__SUPPORTING_MATERIALS;

	/**
	 * The feature id for the '<em><b>Templates</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__TEMPLATES = WORK_BREAKDOWN_ELEMENT__TEMPLATES;

	/**
	 * The feature id for the '<em><b>Reports</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__REPORTS = WORK_BREAKDOWN_ELEMENT__REPORTS;

	/**
	 * The feature id for the '<em><b>Estimationconsiderations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__ESTIMATIONCONSIDERATIONS = WORK_BREAKDOWN_ELEMENT__ESTIMATIONCONSIDERATIONS;

	/**
	 * The feature id for the '<em><b>Toolmentor</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__TOOLMENTOR = WORK_BREAKDOWN_ELEMENT__TOOLMENTOR;

	/**
	 * The feature id for the '<em><b>Is Repeatable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__IS_REPEATABLE = WORK_BREAKDOWN_ELEMENT__IS_REPEATABLE;

	/**
	 * The feature id for the '<em><b>Is Ongoing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__IS_ONGOING = WORK_BREAKDOWN_ELEMENT__IS_ONGOING;

	/**
	 * The feature id for the '<em><b>Is Event Driven</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__IS_EVENT_DRIVEN = WORK_BREAKDOWN_ELEMENT__IS_EVENT_DRIVEN;

	/**
	 * The feature id for the '<em><b>Link To Predecessor</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__LINK_TO_PREDECESSOR = WORK_BREAKDOWN_ELEMENT__LINK_TO_PREDECESSOR;

	/**
	 * The feature id for the '<em><b>Fulfills</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__FULFILLS = WORK_BREAKDOWN_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__VARIABILITY_TYPE = WORK_BREAKDOWN_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__VARIABILITY_BASED_ON_ELEMENT = WORK_BREAKDOWN_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Precondition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__PRECONDITION = WORK_BREAKDOWN_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Postcondition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__POSTCONDITION = WORK_BREAKDOWN_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Breakdown Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__BREAKDOWN_ELEMENTS = WORK_BREAKDOWN_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Roadmaps</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__ROADMAPS = WORK_BREAKDOWN_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>Activity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_FEATURE_COUNT = WORK_BREAKDOWN_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLANNING_DATA__NAME = PROCESS_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLANNING_DATA__GUID = PROCESS_ELEMENT__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLANNING_DATA__PRESENTATION_NAME = PROCESS_ELEMENT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLANNING_DATA__BRIEF_DESCRIPTION = PROCESS_ELEMENT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLANNING_DATA__OWNED_RULES = PROCESS_ELEMENT__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLANNING_DATA__METHOD_ELEMENT_PROPERTY = PROCESS_ELEMENT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLANNING_DATA__KIND = PROCESS_ELEMENT__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLANNING_DATA__SUPPRESSED = PROCESS_ELEMENT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLANNING_DATA__ORDERING_GUIDE = PROCESS_ELEMENT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLANNING_DATA__IS_ABSTRACT = PROCESS_ELEMENT__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLANNING_DATA__PRESENTATION = PROCESS_ELEMENT__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLANNING_DATA__SHAPEICON = PROCESS_ELEMENT__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLANNING_DATA__NODEICON = PROCESS_ELEMENT__NODEICON;

	/**
	 * The feature id for the '<em><b>Start Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLANNING_DATA__START_DATE = PROCESS_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Finish Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLANNING_DATA__FINISH_DATE = PROCESS_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Rank</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLANNING_DATA__RANK = PROCESS_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Planning Data</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLANNING_DATA_FEATURE_COUNT = PROCESS_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_ORDER__NAME = PROCESS_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_ORDER__GUID = PROCESS_ELEMENT__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_ORDER__PRESENTATION_NAME = PROCESS_ELEMENT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_ORDER__BRIEF_DESCRIPTION = PROCESS_ELEMENT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_ORDER__OWNED_RULES = PROCESS_ELEMENT__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_ORDER__METHOD_ELEMENT_PROPERTY = PROCESS_ELEMENT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_ORDER__KIND = PROCESS_ELEMENT__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_ORDER__SUPPRESSED = PROCESS_ELEMENT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_ORDER__ORDERING_GUIDE = PROCESS_ELEMENT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_ORDER__IS_ABSTRACT = PROCESS_ELEMENT__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_ORDER__PRESENTATION = PROCESS_ELEMENT__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_ORDER__SHAPEICON = PROCESS_ELEMENT__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_ORDER__NODEICON = PROCESS_ELEMENT__NODEICON;

	/**
	 * The feature id for the '<em><b>Link Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_ORDER__LINK_TYPE = PROCESS_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Pred</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_ORDER__PRED = PROCESS_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Work Order</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_ORDER_FEATURE_COUNT = PROCESS_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROADMAP__NAME = GUIDANCE__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROADMAP__GUID = GUIDANCE__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROADMAP__PRESENTATION_NAME = GUIDANCE__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROADMAP__BRIEF_DESCRIPTION = GUIDANCE__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROADMAP__OWNED_RULES = GUIDANCE__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROADMAP__METHOD_ELEMENT_PROPERTY = GUIDANCE__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROADMAP__KIND = GUIDANCE__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROADMAP__SUPPRESSED = GUIDANCE__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROADMAP__ORDERING_GUIDE = GUIDANCE__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROADMAP__IS_ABSTRACT = GUIDANCE__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROADMAP__PRESENTATION = GUIDANCE__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROADMAP__SHAPEICON = GUIDANCE__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROADMAP__NODEICON = GUIDANCE__NODEICON;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROADMAP__VARIABILITY_TYPE = GUIDANCE__VARIABILITY_TYPE;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROADMAP__VARIABILITY_BASED_ON_ELEMENT = GUIDANCE__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Supporting Materials</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROADMAP__SUPPORTING_MATERIALS = GUIDANCE__SUPPORTING_MATERIALS;

	/**
	 * The feature id for the '<em><b>Concepts And Papers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROADMAP__CONCEPTS_AND_PAPERS = GUIDANCE__CONCEPTS_AND_PAPERS;

	/**
	 * The feature id for the '<em><b>Checklists</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROADMAP__CHECKLISTS = GUIDANCE__CHECKLISTS;

	/**
	 * The feature id for the '<em><b>Guidelines</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROADMAP__GUIDELINES = GUIDANCE__GUIDELINES;

	/**
	 * The feature id for the '<em><b>Examples</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROADMAP__EXAMPLES = GUIDANCE__EXAMPLES;

	/**
	 * The feature id for the '<em><b>Assets</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROADMAP__ASSETS = GUIDANCE__ASSETS;

	/**
	 * The feature id for the '<em><b>Termdefinition</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROADMAP__TERMDEFINITION = GUIDANCE__TERMDEFINITION;

	/**
	 * The number of structural features of the '<em>Roadmap</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROADMAP_FEATURE_COUNT = GUIDANCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__NAME = CONTENT_CATEGORY__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__GUID = CONTENT_CATEGORY__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__PRESENTATION_NAME = CONTENT_CATEGORY__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__BRIEF_DESCRIPTION = CONTENT_CATEGORY__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__OWNED_RULES = CONTENT_CATEGORY__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__METHOD_ELEMENT_PROPERTY = CONTENT_CATEGORY__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__KIND = CONTENT_CATEGORY__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__SUPPRESSED = CONTENT_CATEGORY__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__ORDERING_GUIDE = CONTENT_CATEGORY__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__IS_ABSTRACT = CONTENT_CATEGORY__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__PRESENTATION = CONTENT_CATEGORY__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__SHAPEICON = CONTENT_CATEGORY__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__NODEICON = CONTENT_CATEGORY__NODEICON;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__VARIABILITY_TYPE = CONTENT_CATEGORY__VARIABILITY_TYPE;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__VARIABILITY_BASED_ON_ELEMENT = CONTENT_CATEGORY__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Supporting Materials</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__SUPPORTING_MATERIALS = CONTENT_CATEGORY__SUPPORTING_MATERIALS;

	/**
	 * The feature id for the '<em><b>Concepts And Papers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__CONCEPTS_AND_PAPERS = CONTENT_CATEGORY__CONCEPTS_AND_PAPERS;

	/**
	 * The feature id for the '<em><b>Checklists</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__CHECKLISTS = CONTENT_CATEGORY__CHECKLISTS;

	/**
	 * The feature id for the '<em><b>Guidelines</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__GUIDELINES = CONTENT_CATEGORY__GUIDELINES;

	/**
	 * The feature id for the '<em><b>Examples</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__EXAMPLES = CONTENT_CATEGORY__EXAMPLES;

	/**
	 * The feature id for the '<em><b>Assets</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__ASSETS = CONTENT_CATEGORY__ASSETS;

	/**
	 * The feature id for the '<em><b>Termdefinition</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__TERMDEFINITION = CONTENT_CATEGORY__TERMDEFINITION;

	/**
	 * The feature id for the '<em><b>Tool Mentors</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__TOOL_MENTORS = CONTENT_CATEGORY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Tool</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_FEATURE_COUNT = CONTENT_CATEGORY_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET_GROUPING__NAME = CONTENT_CATEGORY__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET_GROUPING__GUID = CONTENT_CATEGORY__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET_GROUPING__PRESENTATION_NAME = CONTENT_CATEGORY__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET_GROUPING__BRIEF_DESCRIPTION = CONTENT_CATEGORY__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET_GROUPING__OWNED_RULES = CONTENT_CATEGORY__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET_GROUPING__METHOD_ELEMENT_PROPERTY = CONTENT_CATEGORY__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET_GROUPING__KIND = CONTENT_CATEGORY__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET_GROUPING__SUPPRESSED = CONTENT_CATEGORY__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET_GROUPING__ORDERING_GUIDE = CONTENT_CATEGORY__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET_GROUPING__IS_ABSTRACT = CONTENT_CATEGORY__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET_GROUPING__PRESENTATION = CONTENT_CATEGORY__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET_GROUPING__SHAPEICON = CONTENT_CATEGORY__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET_GROUPING__NODEICON = CONTENT_CATEGORY__NODEICON;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET_GROUPING__VARIABILITY_TYPE = CONTENT_CATEGORY__VARIABILITY_TYPE;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET_GROUPING__VARIABILITY_BASED_ON_ELEMENT = CONTENT_CATEGORY__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Supporting Materials</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET_GROUPING__SUPPORTING_MATERIALS = CONTENT_CATEGORY__SUPPORTING_MATERIALS;

	/**
	 * The feature id for the '<em><b>Concepts And Papers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET_GROUPING__CONCEPTS_AND_PAPERS = CONTENT_CATEGORY__CONCEPTS_AND_PAPERS;

	/**
	 * The feature id for the '<em><b>Checklists</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET_GROUPING__CHECKLISTS = CONTENT_CATEGORY__CHECKLISTS;

	/**
	 * The feature id for the '<em><b>Guidelines</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET_GROUPING__GUIDELINES = CONTENT_CATEGORY__GUIDELINES;

	/**
	 * The feature id for the '<em><b>Examples</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET_GROUPING__EXAMPLES = CONTENT_CATEGORY__EXAMPLES;

	/**
	 * The feature id for the '<em><b>Assets</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET_GROUPING__ASSETS = CONTENT_CATEGORY__ASSETS;

	/**
	 * The feature id for the '<em><b>Termdefinition</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET_GROUPING__TERMDEFINITION = CONTENT_CATEGORY__TERMDEFINITION;

	/**
	 * The feature id for the '<em><b>Role Sets</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET_GROUPING__ROLE_SETS = CONTENT_CATEGORY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Role Set Grouping</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET_GROUPING_FEATURE_COUNT = CONTENT_CATEGORY_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CATEGORY__NAME = CONTENT_CATEGORY__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CATEGORY__GUID = CONTENT_CATEGORY__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CATEGORY__PRESENTATION_NAME = CONTENT_CATEGORY__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CATEGORY__BRIEF_DESCRIPTION = CONTENT_CATEGORY__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CATEGORY__OWNED_RULES = CONTENT_CATEGORY__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CATEGORY__METHOD_ELEMENT_PROPERTY = CONTENT_CATEGORY__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CATEGORY__KIND = CONTENT_CATEGORY__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CATEGORY__SUPPRESSED = CONTENT_CATEGORY__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CATEGORY__ORDERING_GUIDE = CONTENT_CATEGORY__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CATEGORY__IS_ABSTRACT = CONTENT_CATEGORY__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CATEGORY__PRESENTATION = CONTENT_CATEGORY__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CATEGORY__SHAPEICON = CONTENT_CATEGORY__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CATEGORY__NODEICON = CONTENT_CATEGORY__NODEICON;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CATEGORY__VARIABILITY_TYPE = CONTENT_CATEGORY__VARIABILITY_TYPE;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CATEGORY__VARIABILITY_BASED_ON_ELEMENT = CONTENT_CATEGORY__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Supporting Materials</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CATEGORY__SUPPORTING_MATERIALS = CONTENT_CATEGORY__SUPPORTING_MATERIALS;

	/**
	 * The feature id for the '<em><b>Concepts And Papers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CATEGORY__CONCEPTS_AND_PAPERS = CONTENT_CATEGORY__CONCEPTS_AND_PAPERS;

	/**
	 * The feature id for the '<em><b>Checklists</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CATEGORY__CHECKLISTS = CONTENT_CATEGORY__CHECKLISTS;

	/**
	 * The feature id for the '<em><b>Guidelines</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CATEGORY__GUIDELINES = CONTENT_CATEGORY__GUIDELINES;

	/**
	 * The feature id for the '<em><b>Examples</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CATEGORY__EXAMPLES = CONTENT_CATEGORY__EXAMPLES;

	/**
	 * The feature id for the '<em><b>Assets</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CATEGORY__ASSETS = CONTENT_CATEGORY__ASSETS;

	/**
	 * The feature id for the '<em><b>Termdefinition</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CATEGORY__TERMDEFINITION = CONTENT_CATEGORY__TERMDEFINITION;

	/**
	 * The feature id for the '<em><b>Categorized Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CATEGORY__CATEGORIZED_ELEMENTS = CONTENT_CATEGORY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Sub Categories</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CATEGORY__SUB_CATEGORIES = CONTENT_CATEGORY_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Custom Category</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CATEGORY_FEATURE_COUNT = CONTENT_CATEGORY_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PACKAGE__NAME = METHOD_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PACKAGE__GUID = METHOD_ELEMENT__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PACKAGE__PRESENTATION_NAME = METHOD_ELEMENT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PACKAGE__BRIEF_DESCRIPTION = METHOD_ELEMENT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PACKAGE__OWNED_RULES = METHOD_ELEMENT__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PACKAGE__METHOD_ELEMENT_PROPERTY = METHOD_ELEMENT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PACKAGE__KIND = METHOD_ELEMENT__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PACKAGE__SUPPRESSED = METHOD_ELEMENT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PACKAGE__ORDERING_GUIDE = METHOD_ELEMENT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Global</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PACKAGE__GLOBAL = METHOD_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Reused Packages</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PACKAGE__REUSED_PACKAGES = METHOD_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Child Packages</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PACKAGE__CHILD_PACKAGES = METHOD_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Method Package</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PACKAGE_FEATURE_COUNT = METHOD_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_PACKAGE__NAME = METHOD_PACKAGE__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_PACKAGE__GUID = METHOD_PACKAGE__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_PACKAGE__PRESENTATION_NAME = METHOD_PACKAGE__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_PACKAGE__BRIEF_DESCRIPTION = METHOD_PACKAGE__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_PACKAGE__OWNED_RULES = METHOD_PACKAGE__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_PACKAGE__METHOD_ELEMENT_PROPERTY = METHOD_PACKAGE__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_PACKAGE__KIND = METHOD_PACKAGE__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_PACKAGE__SUPPRESSED = METHOD_PACKAGE__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_PACKAGE__ORDERING_GUIDE = METHOD_PACKAGE__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Global</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_PACKAGE__GLOBAL = METHOD_PACKAGE__GLOBAL;

	/**
	 * The feature id for the '<em><b>Reused Packages</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_PACKAGE__REUSED_PACKAGES = METHOD_PACKAGE__REUSED_PACKAGES;

	/**
	 * The feature id for the '<em><b>Child Packages</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_PACKAGE__CHILD_PACKAGES = METHOD_PACKAGE__CHILD_PACKAGES;

	/**
	 * The feature id for the '<em><b>Content Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_PACKAGE__CONTENT_ELEMENTS = METHOD_PACKAGE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Content Package</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_PACKAGE_FEATURE_COUNT = METHOD_PACKAGE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__NAME = WORK_BREAKDOWN_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__GUID = WORK_BREAKDOWN_ELEMENT__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__PRESENTATION_NAME = WORK_BREAKDOWN_ELEMENT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__BRIEF_DESCRIPTION = WORK_BREAKDOWN_ELEMENT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__OWNED_RULES = WORK_BREAKDOWN_ELEMENT__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__METHOD_ELEMENT_PROPERTY = WORK_BREAKDOWN_ELEMENT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__KIND = WORK_BREAKDOWN_ELEMENT__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__SUPPRESSED = WORK_BREAKDOWN_ELEMENT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__ORDERING_GUIDE = WORK_BREAKDOWN_ELEMENT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__IS_ABSTRACT = WORK_BREAKDOWN_ELEMENT__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__PRESENTATION = WORK_BREAKDOWN_ELEMENT__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__SHAPEICON = WORK_BREAKDOWN_ELEMENT__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__NODEICON = WORK_BREAKDOWN_ELEMENT__NODEICON;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__PREFIX = WORK_BREAKDOWN_ELEMENT__PREFIX;

	/**
	 * The feature id for the '<em><b>Is Planned</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__IS_PLANNED = WORK_BREAKDOWN_ELEMENT__IS_PLANNED;

	/**
	 * The feature id for the '<em><b>Has Multiple Occurrences</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__HAS_MULTIPLE_OCCURRENCES = WORK_BREAKDOWN_ELEMENT__HAS_MULTIPLE_OCCURRENCES;

	/**
	 * The feature id for the '<em><b>Is Optional</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__IS_OPTIONAL = WORK_BREAKDOWN_ELEMENT__IS_OPTIONAL;

	/**
	 * The feature id for the '<em><b>Presented After</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__PRESENTED_AFTER = WORK_BREAKDOWN_ELEMENT__PRESENTED_AFTER;

	/**
	 * The feature id for the '<em><b>Presented Before</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__PRESENTED_BEFORE = WORK_BREAKDOWN_ELEMENT__PRESENTED_BEFORE;

	/**
	 * The feature id for the '<em><b>Planning Data</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__PLANNING_DATA = WORK_BREAKDOWN_ELEMENT__PLANNING_DATA;

	/**
	 * The feature id for the '<em><b>Super Activities</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__SUPER_ACTIVITIES = WORK_BREAKDOWN_ELEMENT__SUPER_ACTIVITIES;

	/**
	 * The feature id for the '<em><b>Checklists</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__CHECKLISTS = WORK_BREAKDOWN_ELEMENT__CHECKLISTS;

	/**
	 * The feature id for the '<em><b>Concepts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__CONCEPTS = WORK_BREAKDOWN_ELEMENT__CONCEPTS;

	/**
	 * The feature id for the '<em><b>Examples</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__EXAMPLES = WORK_BREAKDOWN_ELEMENT__EXAMPLES;

	/**
	 * The feature id for the '<em><b>Guidelines</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__GUIDELINES = WORK_BREAKDOWN_ELEMENT__GUIDELINES;

	/**
	 * The feature id for the '<em><b>Reusable Assets</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__REUSABLE_ASSETS = WORK_BREAKDOWN_ELEMENT__REUSABLE_ASSETS;

	/**
	 * The feature id for the '<em><b>Supporting Materials</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__SUPPORTING_MATERIALS = WORK_BREAKDOWN_ELEMENT__SUPPORTING_MATERIALS;

	/**
	 * The feature id for the '<em><b>Templates</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__TEMPLATES = WORK_BREAKDOWN_ELEMENT__TEMPLATES;

	/**
	 * The feature id for the '<em><b>Reports</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__REPORTS = WORK_BREAKDOWN_ELEMENT__REPORTS;

	/**
	 * The feature id for the '<em><b>Estimationconsiderations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__ESTIMATIONCONSIDERATIONS = WORK_BREAKDOWN_ELEMENT__ESTIMATIONCONSIDERATIONS;

	/**
	 * The feature id for the '<em><b>Toolmentor</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__TOOLMENTOR = WORK_BREAKDOWN_ELEMENT__TOOLMENTOR;

	/**
	 * The feature id for the '<em><b>Is Repeatable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__IS_REPEATABLE = WORK_BREAKDOWN_ELEMENT__IS_REPEATABLE;

	/**
	 * The feature id for the '<em><b>Is Ongoing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__IS_ONGOING = WORK_BREAKDOWN_ELEMENT__IS_ONGOING;

	/**
	 * The feature id for the '<em><b>Is Event Driven</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__IS_EVENT_DRIVEN = WORK_BREAKDOWN_ELEMENT__IS_EVENT_DRIVEN;

	/**
	 * The feature id for the '<em><b>Link To Predecessor</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__LINK_TO_PREDECESSOR = WORK_BREAKDOWN_ELEMENT__LINK_TO_PREDECESSOR;

	/**
	 * The feature id for the '<em><b>Required Results</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__REQUIRED_RESULTS = WORK_BREAKDOWN_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Milestone</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE_FEATURE_COUNT = WORK_BREAKDOWN_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__NAME = BREAKDOWN_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__GUID = BREAKDOWN_ELEMENT__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__PRESENTATION_NAME = BREAKDOWN_ELEMENT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__BRIEF_DESCRIPTION = BREAKDOWN_ELEMENT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__OWNED_RULES = BREAKDOWN_ELEMENT__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__METHOD_ELEMENT_PROPERTY = BREAKDOWN_ELEMENT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__KIND = BREAKDOWN_ELEMENT__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__SUPPRESSED = BREAKDOWN_ELEMENT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__ORDERING_GUIDE = BREAKDOWN_ELEMENT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__IS_ABSTRACT = BREAKDOWN_ELEMENT__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__PRESENTATION = BREAKDOWN_ELEMENT__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__SHAPEICON = BREAKDOWN_ELEMENT__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__NODEICON = BREAKDOWN_ELEMENT__NODEICON;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__PREFIX = BREAKDOWN_ELEMENT__PREFIX;

	/**
	 * The feature id for the '<em><b>Is Planned</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__IS_PLANNED = BREAKDOWN_ELEMENT__IS_PLANNED;

	/**
	 * The feature id for the '<em><b>Has Multiple Occurrences</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__HAS_MULTIPLE_OCCURRENCES = BREAKDOWN_ELEMENT__HAS_MULTIPLE_OCCURRENCES;

	/**
	 * The feature id for the '<em><b>Is Optional</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__IS_OPTIONAL = BREAKDOWN_ELEMENT__IS_OPTIONAL;

	/**
	 * The feature id for the '<em><b>Presented After</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__PRESENTED_AFTER = BREAKDOWN_ELEMENT__PRESENTED_AFTER;

	/**
	 * The feature id for the '<em><b>Presented Before</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__PRESENTED_BEFORE = BREAKDOWN_ELEMENT__PRESENTED_BEFORE;

	/**
	 * The feature id for the '<em><b>Planning Data</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__PLANNING_DATA = BREAKDOWN_ELEMENT__PLANNING_DATA;

	/**
	 * The feature id for the '<em><b>Super Activities</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__SUPER_ACTIVITIES = BREAKDOWN_ELEMENT__SUPER_ACTIVITIES;

	/**
	 * The feature id for the '<em><b>Checklists</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__CHECKLISTS = BREAKDOWN_ELEMENT__CHECKLISTS;

	/**
	 * The feature id for the '<em><b>Concepts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__CONCEPTS = BREAKDOWN_ELEMENT__CONCEPTS;

	/**
	 * The feature id for the '<em><b>Examples</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__EXAMPLES = BREAKDOWN_ELEMENT__EXAMPLES;

	/**
	 * The feature id for the '<em><b>Guidelines</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__GUIDELINES = BREAKDOWN_ELEMENT__GUIDELINES;

	/**
	 * The feature id for the '<em><b>Reusable Assets</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__REUSABLE_ASSETS = BREAKDOWN_ELEMENT__REUSABLE_ASSETS;

	/**
	 * The feature id for the '<em><b>Supporting Materials</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__SUPPORTING_MATERIALS = BREAKDOWN_ELEMENT__SUPPORTING_MATERIALS;

	/**
	 * The feature id for the '<em><b>Templates</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__TEMPLATES = BREAKDOWN_ELEMENT__TEMPLATES;

	/**
	 * The feature id for the '<em><b>Reports</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__REPORTS = BREAKDOWN_ELEMENT__REPORTS;

	/**
	 * The feature id for the '<em><b>Estimationconsiderations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__ESTIMATIONCONSIDERATIONS = BREAKDOWN_ELEMENT__ESTIMATIONCONSIDERATIONS;

	/**
	 * The feature id for the '<em><b>Toolmentor</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__TOOLMENTOR = BREAKDOWN_ELEMENT__TOOLMENTOR;

	/**
	 * The feature id for the '<em><b>Is Synchronized With Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__IS_SYNCHRONIZED_WITH_SOURCE = BREAKDOWN_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Guidance Exclude</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__GUIDANCE_EXCLUDE = BREAKDOWN_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Guidance Additional</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__GUIDANCE_ADDITIONAL = BREAKDOWN_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR_FEATURE_COUNT = BREAKDOWN_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__NAME = DESCRIPTOR__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__GUID = DESCRIPTOR__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__PRESENTATION_NAME = DESCRIPTOR__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__BRIEF_DESCRIPTION = DESCRIPTOR__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__OWNED_RULES = DESCRIPTOR__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__METHOD_ELEMENT_PROPERTY = DESCRIPTOR__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__KIND = DESCRIPTOR__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__SUPPRESSED = DESCRIPTOR__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__ORDERING_GUIDE = DESCRIPTOR__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__IS_ABSTRACT = DESCRIPTOR__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__PRESENTATION = DESCRIPTOR__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__SHAPEICON = DESCRIPTOR__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__NODEICON = DESCRIPTOR__NODEICON;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__PREFIX = DESCRIPTOR__PREFIX;

	/**
	 * The feature id for the '<em><b>Is Planned</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__IS_PLANNED = DESCRIPTOR__IS_PLANNED;

	/**
	 * The feature id for the '<em><b>Has Multiple Occurrences</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__HAS_MULTIPLE_OCCURRENCES = DESCRIPTOR__HAS_MULTIPLE_OCCURRENCES;

	/**
	 * The feature id for the '<em><b>Is Optional</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__IS_OPTIONAL = DESCRIPTOR__IS_OPTIONAL;

	/**
	 * The feature id for the '<em><b>Presented After</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__PRESENTED_AFTER = DESCRIPTOR__PRESENTED_AFTER;

	/**
	 * The feature id for the '<em><b>Presented Before</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__PRESENTED_BEFORE = DESCRIPTOR__PRESENTED_BEFORE;

	/**
	 * The feature id for the '<em><b>Planning Data</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__PLANNING_DATA = DESCRIPTOR__PLANNING_DATA;

	/**
	 * The feature id for the '<em><b>Super Activities</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__SUPER_ACTIVITIES = DESCRIPTOR__SUPER_ACTIVITIES;

	/**
	 * The feature id for the '<em><b>Checklists</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__CHECKLISTS = DESCRIPTOR__CHECKLISTS;

	/**
	 * The feature id for the '<em><b>Concepts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__CONCEPTS = DESCRIPTOR__CONCEPTS;

	/**
	 * The feature id for the '<em><b>Examples</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__EXAMPLES = DESCRIPTOR__EXAMPLES;

	/**
	 * The feature id for the '<em><b>Guidelines</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__GUIDELINES = DESCRIPTOR__GUIDELINES;

	/**
	 * The feature id for the '<em><b>Reusable Assets</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__REUSABLE_ASSETS = DESCRIPTOR__REUSABLE_ASSETS;

	/**
	 * The feature id for the '<em><b>Supporting Materials</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__SUPPORTING_MATERIALS = DESCRIPTOR__SUPPORTING_MATERIALS;

	/**
	 * The feature id for the '<em><b>Templates</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__TEMPLATES = DESCRIPTOR__TEMPLATES;

	/**
	 * The feature id for the '<em><b>Reports</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__REPORTS = DESCRIPTOR__REPORTS;

	/**
	 * The feature id for the '<em><b>Estimationconsiderations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__ESTIMATIONCONSIDERATIONS = DESCRIPTOR__ESTIMATIONCONSIDERATIONS;

	/**
	 * The feature id for the '<em><b>Toolmentor</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__TOOLMENTOR = DESCRIPTOR__TOOLMENTOR;

	/**
	 * The feature id for the '<em><b>Is Synchronized With Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__IS_SYNCHRONIZED_WITH_SOURCE = DESCRIPTOR__IS_SYNCHRONIZED_WITH_SOURCE;

	/**
	 * The feature id for the '<em><b>Guidance Exclude</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__GUIDANCE_EXCLUDE = DESCRIPTOR__GUIDANCE_EXCLUDE;

	/**
	 * The feature id for the '<em><b>Guidance Additional</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__GUIDANCE_ADDITIONAL = DESCRIPTOR__GUIDANCE_ADDITIONAL;

	/**
	 * The feature id for the '<em><b>Activity Entry State</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__ACTIVITY_ENTRY_STATE = DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Activity Exit State</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__ACTIVITY_EXIT_STATE = DESCRIPTOR_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Work Product</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__WORK_PRODUCT = DESCRIPTOR_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Impacted By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__IMPACTED_BY = DESCRIPTOR_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Impacts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__IMPACTS = DESCRIPTOR_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Deliverable Parts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__DELIVERABLE_PARTS = DESCRIPTOR_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Deliverable Parts Exclude</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__DELIVERABLE_PARTS_EXCLUDE = DESCRIPTOR_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>Work Product Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR_FEATURE_COUNT = DESCRIPTOR_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__NAME = ACTIVITY__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__GUID = ACTIVITY__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__PRESENTATION_NAME = ACTIVITY__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__BRIEF_DESCRIPTION = ACTIVITY__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__OWNED_RULES = ACTIVITY__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__METHOD_ELEMENT_PROPERTY = ACTIVITY__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__KIND = ACTIVITY__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__SUPPRESSED = ACTIVITY__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__ORDERING_GUIDE = ACTIVITY__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__IS_ABSTRACT = ACTIVITY__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__PRESENTATION = ACTIVITY__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__SHAPEICON = ACTIVITY__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__NODEICON = ACTIVITY__NODEICON;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__PREFIX = ACTIVITY__PREFIX;

	/**
	 * The feature id for the '<em><b>Is Planned</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__IS_PLANNED = ACTIVITY__IS_PLANNED;

	/**
	 * The feature id for the '<em><b>Has Multiple Occurrences</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__HAS_MULTIPLE_OCCURRENCES = ACTIVITY__HAS_MULTIPLE_OCCURRENCES;

	/**
	 * The feature id for the '<em><b>Is Optional</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__IS_OPTIONAL = ACTIVITY__IS_OPTIONAL;

	/**
	 * The feature id for the '<em><b>Presented After</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__PRESENTED_AFTER = ACTIVITY__PRESENTED_AFTER;

	/**
	 * The feature id for the '<em><b>Presented Before</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__PRESENTED_BEFORE = ACTIVITY__PRESENTED_BEFORE;

	/**
	 * The feature id for the '<em><b>Planning Data</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__PLANNING_DATA = ACTIVITY__PLANNING_DATA;

	/**
	 * The feature id for the '<em><b>Super Activities</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__SUPER_ACTIVITIES = ACTIVITY__SUPER_ACTIVITIES;

	/**
	 * The feature id for the '<em><b>Checklists</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__CHECKLISTS = ACTIVITY__CHECKLISTS;

	/**
	 * The feature id for the '<em><b>Concepts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__CONCEPTS = ACTIVITY__CONCEPTS;

	/**
	 * The feature id for the '<em><b>Examples</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__EXAMPLES = ACTIVITY__EXAMPLES;

	/**
	 * The feature id for the '<em><b>Guidelines</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__GUIDELINES = ACTIVITY__GUIDELINES;

	/**
	 * The feature id for the '<em><b>Reusable Assets</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__REUSABLE_ASSETS = ACTIVITY__REUSABLE_ASSETS;

	/**
	 * The feature id for the '<em><b>Supporting Materials</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__SUPPORTING_MATERIALS = ACTIVITY__SUPPORTING_MATERIALS;

	/**
	 * The feature id for the '<em><b>Templates</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__TEMPLATES = ACTIVITY__TEMPLATES;

	/**
	 * The feature id for the '<em><b>Reports</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__REPORTS = ACTIVITY__REPORTS;

	/**
	 * The feature id for the '<em><b>Estimationconsiderations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__ESTIMATIONCONSIDERATIONS = ACTIVITY__ESTIMATIONCONSIDERATIONS;

	/**
	 * The feature id for the '<em><b>Toolmentor</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__TOOLMENTOR = ACTIVITY__TOOLMENTOR;

	/**
	 * The feature id for the '<em><b>Is Repeatable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__IS_REPEATABLE = ACTIVITY__IS_REPEATABLE;

	/**
	 * The feature id for the '<em><b>Is Ongoing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__IS_ONGOING = ACTIVITY__IS_ONGOING;

	/**
	 * The feature id for the '<em><b>Is Event Driven</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__IS_EVENT_DRIVEN = ACTIVITY__IS_EVENT_DRIVEN;

	/**
	 * The feature id for the '<em><b>Link To Predecessor</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__LINK_TO_PREDECESSOR = ACTIVITY__LINK_TO_PREDECESSOR;

	/**
	 * The feature id for the '<em><b>Fulfills</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__FULFILLS = ACTIVITY__FULFILLS;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__VARIABILITY_TYPE = ACTIVITY__VARIABILITY_TYPE;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__VARIABILITY_BASED_ON_ELEMENT = ACTIVITY__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Precondition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__PRECONDITION = ACTIVITY__PRECONDITION;

	/**
	 * The feature id for the '<em><b>Postcondition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__POSTCONDITION = ACTIVITY__POSTCONDITION;

	/**
	 * The feature id for the '<em><b>Breakdown Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__BREAKDOWN_ELEMENTS = ACTIVITY__BREAKDOWN_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Roadmaps</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__ROADMAPS = ACTIVITY__ROADMAPS;

	/**
	 * The number of structural features of the '<em>Iteration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION_FEATURE_COUNT = ACTIVITY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__NAME = ACTIVITY__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__GUID = ACTIVITY__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__PRESENTATION_NAME = ACTIVITY__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__BRIEF_DESCRIPTION = ACTIVITY__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__OWNED_RULES = ACTIVITY__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__METHOD_ELEMENT_PROPERTY = ACTIVITY__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__KIND = ACTIVITY__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__SUPPRESSED = ACTIVITY__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__ORDERING_GUIDE = ACTIVITY__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__IS_ABSTRACT = ACTIVITY__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__PRESENTATION = ACTIVITY__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__SHAPEICON = ACTIVITY__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__NODEICON = ACTIVITY__NODEICON;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__PREFIX = ACTIVITY__PREFIX;

	/**
	 * The feature id for the '<em><b>Is Planned</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__IS_PLANNED = ACTIVITY__IS_PLANNED;

	/**
	 * The feature id for the '<em><b>Has Multiple Occurrences</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__HAS_MULTIPLE_OCCURRENCES = ACTIVITY__HAS_MULTIPLE_OCCURRENCES;

	/**
	 * The feature id for the '<em><b>Is Optional</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__IS_OPTIONAL = ACTIVITY__IS_OPTIONAL;

	/**
	 * The feature id for the '<em><b>Presented After</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__PRESENTED_AFTER = ACTIVITY__PRESENTED_AFTER;

	/**
	 * The feature id for the '<em><b>Presented Before</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__PRESENTED_BEFORE = ACTIVITY__PRESENTED_BEFORE;

	/**
	 * The feature id for the '<em><b>Planning Data</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__PLANNING_DATA = ACTIVITY__PLANNING_DATA;

	/**
	 * The feature id for the '<em><b>Super Activities</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__SUPER_ACTIVITIES = ACTIVITY__SUPER_ACTIVITIES;

	/**
	 * The feature id for the '<em><b>Checklists</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__CHECKLISTS = ACTIVITY__CHECKLISTS;

	/**
	 * The feature id for the '<em><b>Concepts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__CONCEPTS = ACTIVITY__CONCEPTS;

	/**
	 * The feature id for the '<em><b>Examples</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__EXAMPLES = ACTIVITY__EXAMPLES;

	/**
	 * The feature id for the '<em><b>Guidelines</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__GUIDELINES = ACTIVITY__GUIDELINES;

	/**
	 * The feature id for the '<em><b>Reusable Assets</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__REUSABLE_ASSETS = ACTIVITY__REUSABLE_ASSETS;

	/**
	 * The feature id for the '<em><b>Supporting Materials</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__SUPPORTING_MATERIALS = ACTIVITY__SUPPORTING_MATERIALS;

	/**
	 * The feature id for the '<em><b>Templates</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__TEMPLATES = ACTIVITY__TEMPLATES;

	/**
	 * The feature id for the '<em><b>Reports</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__REPORTS = ACTIVITY__REPORTS;

	/**
	 * The feature id for the '<em><b>Estimationconsiderations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__ESTIMATIONCONSIDERATIONS = ACTIVITY__ESTIMATIONCONSIDERATIONS;

	/**
	 * The feature id for the '<em><b>Toolmentor</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__TOOLMENTOR = ACTIVITY__TOOLMENTOR;

	/**
	 * The feature id for the '<em><b>Is Repeatable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__IS_REPEATABLE = ACTIVITY__IS_REPEATABLE;

	/**
	 * The feature id for the '<em><b>Is Ongoing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__IS_ONGOING = ACTIVITY__IS_ONGOING;

	/**
	 * The feature id for the '<em><b>Is Event Driven</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__IS_EVENT_DRIVEN = ACTIVITY__IS_EVENT_DRIVEN;

	/**
	 * The feature id for the '<em><b>Link To Predecessor</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__LINK_TO_PREDECESSOR = ACTIVITY__LINK_TO_PREDECESSOR;

	/**
	 * The feature id for the '<em><b>Fulfills</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__FULFILLS = ACTIVITY__FULFILLS;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__VARIABILITY_TYPE = ACTIVITY__VARIABILITY_TYPE;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__VARIABILITY_BASED_ON_ELEMENT = ACTIVITY__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Precondition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__PRECONDITION = ACTIVITY__PRECONDITION;

	/**
	 * The feature id for the '<em><b>Postcondition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__POSTCONDITION = ACTIVITY__POSTCONDITION;

	/**
	 * The feature id for the '<em><b>Breakdown Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__BREAKDOWN_ELEMENTS = ACTIVITY__BREAKDOWN_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Roadmaps</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__ROADMAPS = ACTIVITY__ROADMAPS;

	/**
	 * The number of structural features of the '<em>Phase</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE_FEATURE_COUNT = ACTIVITY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__NAME = BREAKDOWN_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__GUID = BREAKDOWN_ELEMENT__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__PRESENTATION_NAME = BREAKDOWN_ELEMENT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__BRIEF_DESCRIPTION = BREAKDOWN_ELEMENT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__OWNED_RULES = BREAKDOWN_ELEMENT__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__METHOD_ELEMENT_PROPERTY = BREAKDOWN_ELEMENT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__KIND = BREAKDOWN_ELEMENT__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__SUPPRESSED = BREAKDOWN_ELEMENT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__ORDERING_GUIDE = BREAKDOWN_ELEMENT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__IS_ABSTRACT = BREAKDOWN_ELEMENT__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__PRESENTATION = BREAKDOWN_ELEMENT__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__SHAPEICON = BREAKDOWN_ELEMENT__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__NODEICON = BREAKDOWN_ELEMENT__NODEICON;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__PREFIX = BREAKDOWN_ELEMENT__PREFIX;

	/**
	 * The feature id for the '<em><b>Is Planned</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__IS_PLANNED = BREAKDOWN_ELEMENT__IS_PLANNED;

	/**
	 * The feature id for the '<em><b>Has Multiple Occurrences</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__HAS_MULTIPLE_OCCURRENCES = BREAKDOWN_ELEMENT__HAS_MULTIPLE_OCCURRENCES;

	/**
	 * The feature id for the '<em><b>Is Optional</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__IS_OPTIONAL = BREAKDOWN_ELEMENT__IS_OPTIONAL;

	/**
	 * The feature id for the '<em><b>Presented After</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__PRESENTED_AFTER = BREAKDOWN_ELEMENT__PRESENTED_AFTER;

	/**
	 * The feature id for the '<em><b>Presented Before</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__PRESENTED_BEFORE = BREAKDOWN_ELEMENT__PRESENTED_BEFORE;

	/**
	 * The feature id for the '<em><b>Planning Data</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__PLANNING_DATA = BREAKDOWN_ELEMENT__PLANNING_DATA;

	/**
	 * The feature id for the '<em><b>Super Activities</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__SUPER_ACTIVITIES = BREAKDOWN_ELEMENT__SUPER_ACTIVITIES;

	/**
	 * The feature id for the '<em><b>Checklists</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__CHECKLISTS = BREAKDOWN_ELEMENT__CHECKLISTS;

	/**
	 * The feature id for the '<em><b>Concepts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__CONCEPTS = BREAKDOWN_ELEMENT__CONCEPTS;

	/**
	 * The feature id for the '<em><b>Examples</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__EXAMPLES = BREAKDOWN_ELEMENT__EXAMPLES;

	/**
	 * The feature id for the '<em><b>Guidelines</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__GUIDELINES = BREAKDOWN_ELEMENT__GUIDELINES;

	/**
	 * The feature id for the '<em><b>Reusable Assets</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__REUSABLE_ASSETS = BREAKDOWN_ELEMENT__REUSABLE_ASSETS;

	/**
	 * The feature id for the '<em><b>Supporting Materials</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__SUPPORTING_MATERIALS = BREAKDOWN_ELEMENT__SUPPORTING_MATERIALS;

	/**
	 * The feature id for the '<em><b>Templates</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__TEMPLATES = BREAKDOWN_ELEMENT__TEMPLATES;

	/**
	 * The feature id for the '<em><b>Reports</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__REPORTS = BREAKDOWN_ELEMENT__REPORTS;

	/**
	 * The feature id for the '<em><b>Estimationconsiderations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__ESTIMATIONCONSIDERATIONS = BREAKDOWN_ELEMENT__ESTIMATIONCONSIDERATIONS;

	/**
	 * The feature id for the '<em><b>Toolmentor</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__TOOLMENTOR = BREAKDOWN_ELEMENT__TOOLMENTOR;

	/**
	 * The feature id for the '<em><b>Team Roles</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__TEAM_ROLES = BREAKDOWN_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Super Team</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__SUPER_TEAM = BREAKDOWN_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Sub Team</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__SUB_TEAM = BREAKDOWN_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Team Profile</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE_FEATURE_COUNT = BREAKDOWN_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__NAME = DESCRIPTOR__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__GUID = DESCRIPTOR__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__PRESENTATION_NAME = DESCRIPTOR__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__BRIEF_DESCRIPTION = DESCRIPTOR__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__OWNED_RULES = DESCRIPTOR__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__METHOD_ELEMENT_PROPERTY = DESCRIPTOR__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__KIND = DESCRIPTOR__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__SUPPRESSED = DESCRIPTOR__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__ORDERING_GUIDE = DESCRIPTOR__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__IS_ABSTRACT = DESCRIPTOR__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__PRESENTATION = DESCRIPTOR__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__SHAPEICON = DESCRIPTOR__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__NODEICON = DESCRIPTOR__NODEICON;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__PREFIX = DESCRIPTOR__PREFIX;

	/**
	 * The feature id for the '<em><b>Is Planned</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__IS_PLANNED = DESCRIPTOR__IS_PLANNED;

	/**
	 * The feature id for the '<em><b>Has Multiple Occurrences</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__HAS_MULTIPLE_OCCURRENCES = DESCRIPTOR__HAS_MULTIPLE_OCCURRENCES;

	/**
	 * The feature id for the '<em><b>Is Optional</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__IS_OPTIONAL = DESCRIPTOR__IS_OPTIONAL;

	/**
	 * The feature id for the '<em><b>Presented After</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__PRESENTED_AFTER = DESCRIPTOR__PRESENTED_AFTER;

	/**
	 * The feature id for the '<em><b>Presented Before</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__PRESENTED_BEFORE = DESCRIPTOR__PRESENTED_BEFORE;

	/**
	 * The feature id for the '<em><b>Planning Data</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__PLANNING_DATA = DESCRIPTOR__PLANNING_DATA;

	/**
	 * The feature id for the '<em><b>Super Activities</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__SUPER_ACTIVITIES = DESCRIPTOR__SUPER_ACTIVITIES;

	/**
	 * The feature id for the '<em><b>Checklists</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__CHECKLISTS = DESCRIPTOR__CHECKLISTS;

	/**
	 * The feature id for the '<em><b>Concepts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__CONCEPTS = DESCRIPTOR__CONCEPTS;

	/**
	 * The feature id for the '<em><b>Examples</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__EXAMPLES = DESCRIPTOR__EXAMPLES;

	/**
	 * The feature id for the '<em><b>Guidelines</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__GUIDELINES = DESCRIPTOR__GUIDELINES;

	/**
	 * The feature id for the '<em><b>Reusable Assets</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__REUSABLE_ASSETS = DESCRIPTOR__REUSABLE_ASSETS;

	/**
	 * The feature id for the '<em><b>Supporting Materials</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__SUPPORTING_MATERIALS = DESCRIPTOR__SUPPORTING_MATERIALS;

	/**
	 * The feature id for the '<em><b>Templates</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__TEMPLATES = DESCRIPTOR__TEMPLATES;

	/**
	 * The feature id for the '<em><b>Reports</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__REPORTS = DESCRIPTOR__REPORTS;

	/**
	 * The feature id for the '<em><b>Estimationconsiderations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__ESTIMATIONCONSIDERATIONS = DESCRIPTOR__ESTIMATIONCONSIDERATIONS;

	/**
	 * The feature id for the '<em><b>Toolmentor</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__TOOLMENTOR = DESCRIPTOR__TOOLMENTOR;

	/**
	 * The feature id for the '<em><b>Is Synchronized With Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__IS_SYNCHRONIZED_WITH_SOURCE = DESCRIPTOR__IS_SYNCHRONIZED_WITH_SOURCE;

	/**
	 * The feature id for the '<em><b>Guidance Exclude</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__GUIDANCE_EXCLUDE = DESCRIPTOR__GUIDANCE_EXCLUDE;

	/**
	 * The feature id for the '<em><b>Guidance Additional</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__GUIDANCE_ADDITIONAL = DESCRIPTOR__GUIDANCE_ADDITIONAL;

	/**
	 * The feature id for the '<em><b>Role</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__ROLE = DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Modifies</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__MODIFIES = DESCRIPTOR_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Responsible For</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__RESPONSIBLE_FOR = DESCRIPTOR_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Responsible For Exclude</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__RESPONSIBLE_FOR_EXCLUDE = DESCRIPTOR_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Role Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR_FEATURE_COUNT = DESCRIPTOR_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__NAME = WORK_BREAKDOWN_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__GUID = WORK_BREAKDOWN_ELEMENT__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__PRESENTATION_NAME = WORK_BREAKDOWN_ELEMENT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__BRIEF_DESCRIPTION = WORK_BREAKDOWN_ELEMENT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__OWNED_RULES = WORK_BREAKDOWN_ELEMENT__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__METHOD_ELEMENT_PROPERTY = WORK_BREAKDOWN_ELEMENT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__KIND = WORK_BREAKDOWN_ELEMENT__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__SUPPRESSED = WORK_BREAKDOWN_ELEMENT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__ORDERING_GUIDE = WORK_BREAKDOWN_ELEMENT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__IS_ABSTRACT = WORK_BREAKDOWN_ELEMENT__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__PRESENTATION = WORK_BREAKDOWN_ELEMENT__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__SHAPEICON = WORK_BREAKDOWN_ELEMENT__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__NODEICON = WORK_BREAKDOWN_ELEMENT__NODEICON;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__PREFIX = WORK_BREAKDOWN_ELEMENT__PREFIX;

	/**
	 * The feature id for the '<em><b>Is Planned</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__IS_PLANNED = WORK_BREAKDOWN_ELEMENT__IS_PLANNED;

	/**
	 * The feature id for the '<em><b>Has Multiple Occurrences</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__HAS_MULTIPLE_OCCURRENCES = WORK_BREAKDOWN_ELEMENT__HAS_MULTIPLE_OCCURRENCES;

	/**
	 * The feature id for the '<em><b>Is Optional</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__IS_OPTIONAL = WORK_BREAKDOWN_ELEMENT__IS_OPTIONAL;

	/**
	 * The feature id for the '<em><b>Presented After</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__PRESENTED_AFTER = WORK_BREAKDOWN_ELEMENT__PRESENTED_AFTER;

	/**
	 * The feature id for the '<em><b>Presented Before</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__PRESENTED_BEFORE = WORK_BREAKDOWN_ELEMENT__PRESENTED_BEFORE;

	/**
	 * The feature id for the '<em><b>Planning Data</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__PLANNING_DATA = WORK_BREAKDOWN_ELEMENT__PLANNING_DATA;

	/**
	 * The feature id for the '<em><b>Super Activities</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__SUPER_ACTIVITIES = WORK_BREAKDOWN_ELEMENT__SUPER_ACTIVITIES;

	/**
	 * The feature id for the '<em><b>Checklists</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__CHECKLISTS = WORK_BREAKDOWN_ELEMENT__CHECKLISTS;

	/**
	 * The feature id for the '<em><b>Concepts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__CONCEPTS = WORK_BREAKDOWN_ELEMENT__CONCEPTS;

	/**
	 * The feature id for the '<em><b>Examples</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__EXAMPLES = WORK_BREAKDOWN_ELEMENT__EXAMPLES;

	/**
	 * The feature id for the '<em><b>Guidelines</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__GUIDELINES = WORK_BREAKDOWN_ELEMENT__GUIDELINES;

	/**
	 * The feature id for the '<em><b>Reusable Assets</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__REUSABLE_ASSETS = WORK_BREAKDOWN_ELEMENT__REUSABLE_ASSETS;

	/**
	 * The feature id for the '<em><b>Supporting Materials</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__SUPPORTING_MATERIALS = WORK_BREAKDOWN_ELEMENT__SUPPORTING_MATERIALS;

	/**
	 * The feature id for the '<em><b>Templates</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__TEMPLATES = WORK_BREAKDOWN_ELEMENT__TEMPLATES;

	/**
	 * The feature id for the '<em><b>Reports</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__REPORTS = WORK_BREAKDOWN_ELEMENT__REPORTS;

	/**
	 * The feature id for the '<em><b>Estimationconsiderations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__ESTIMATIONCONSIDERATIONS = WORK_BREAKDOWN_ELEMENT__ESTIMATIONCONSIDERATIONS;

	/**
	 * The feature id for the '<em><b>Toolmentor</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__TOOLMENTOR = WORK_BREAKDOWN_ELEMENT__TOOLMENTOR;

	/**
	 * The feature id for the '<em><b>Is Repeatable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__IS_REPEATABLE = WORK_BREAKDOWN_ELEMENT__IS_REPEATABLE;

	/**
	 * The feature id for the '<em><b>Is Ongoing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__IS_ONGOING = WORK_BREAKDOWN_ELEMENT__IS_ONGOING;

	/**
	 * The feature id for the '<em><b>Is Event Driven</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__IS_EVENT_DRIVEN = WORK_BREAKDOWN_ELEMENT__IS_EVENT_DRIVEN;

	/**
	 * The feature id for the '<em><b>Link To Predecessor</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__LINK_TO_PREDECESSOR = WORK_BREAKDOWN_ELEMENT__LINK_TO_PREDECESSOR;

	/**
	 * The feature id for the '<em><b>Is Synchronized With Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__IS_SYNCHRONIZED_WITH_SOURCE = WORK_BREAKDOWN_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Guidance Exclude</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__GUIDANCE_EXCLUDE = WORK_BREAKDOWN_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Guidance Additional</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__GUIDANCE_ADDITIONAL = WORK_BREAKDOWN_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Task</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__TASK = WORK_BREAKDOWN_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Additionally Performed By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__ADDITIONALLY_PERFORMED_BY = WORK_BREAKDOWN_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Assisted By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__ASSISTED_BY = WORK_BREAKDOWN_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>External Input</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__EXTERNAL_INPUT = WORK_BREAKDOWN_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Mandatory Input</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__MANDATORY_INPUT = WORK_BREAKDOWN_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Optional Input</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__OPTIONAL_INPUT = WORK_BREAKDOWN_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Output</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__OUTPUT = WORK_BREAKDOWN_ELEMENT_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Performed Primarily By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__PERFORMED_PRIMARILY_BY = WORK_BREAKDOWN_ELEMENT_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Selected Steps</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__SELECTED_STEPS = WORK_BREAKDOWN_ELEMENT_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Performed Primarily By Excluded</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__PERFORMED_PRIMARILY_BY_EXCLUDED = WORK_BREAKDOWN_ELEMENT_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Additionally Performed By Exclude</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__ADDITIONALLY_PERFORMED_BY_EXCLUDE = WORK_BREAKDOWN_ELEMENT_FEATURE_COUNT + 13;

	/**
	 * The feature id for the '<em><b>Mandatory Input Exclude</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__MANDATORY_INPUT_EXCLUDE = WORK_BREAKDOWN_ELEMENT_FEATURE_COUNT + 14;

	/**
	 * The feature id for the '<em><b>Optional Input Exclude</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__OPTIONAL_INPUT_EXCLUDE = WORK_BREAKDOWN_ELEMENT_FEATURE_COUNT + 15;

	/**
	 * The feature id for the '<em><b>Output Exclude</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__OUTPUT_EXCLUDE = WORK_BREAKDOWN_ELEMENT_FEATURE_COUNT + 16;

	/**
	 * The feature id for the '<em><b>Selected Steps Exclude</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__SELECTED_STEPS_EXCLUDE = WORK_BREAKDOWN_ELEMENT_FEATURE_COUNT + 17;

	/**
	 * The number of structural features of the '<em>Task Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR_FEATURE_COUNT = WORK_BREAKDOWN_ELEMENT_FEATURE_COUNT + 18;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__NAME = ROLE_DESCRIPTOR__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__GUID = ROLE_DESCRIPTOR__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__PRESENTATION_NAME = ROLE_DESCRIPTOR__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__BRIEF_DESCRIPTION = ROLE_DESCRIPTOR__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__OWNED_RULES = ROLE_DESCRIPTOR__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__METHOD_ELEMENT_PROPERTY = ROLE_DESCRIPTOR__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__KIND = ROLE_DESCRIPTOR__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__SUPPRESSED = ROLE_DESCRIPTOR__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__ORDERING_GUIDE = ROLE_DESCRIPTOR__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__IS_ABSTRACT = ROLE_DESCRIPTOR__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__PRESENTATION = ROLE_DESCRIPTOR__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__SHAPEICON = ROLE_DESCRIPTOR__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__NODEICON = ROLE_DESCRIPTOR__NODEICON;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__PREFIX = ROLE_DESCRIPTOR__PREFIX;

	/**
	 * The feature id for the '<em><b>Is Planned</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__IS_PLANNED = ROLE_DESCRIPTOR__IS_PLANNED;

	/**
	 * The feature id for the '<em><b>Has Multiple Occurrences</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__HAS_MULTIPLE_OCCURRENCES = ROLE_DESCRIPTOR__HAS_MULTIPLE_OCCURRENCES;

	/**
	 * The feature id for the '<em><b>Is Optional</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__IS_OPTIONAL = ROLE_DESCRIPTOR__IS_OPTIONAL;

	/**
	 * The feature id for the '<em><b>Presented After</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__PRESENTED_AFTER = ROLE_DESCRIPTOR__PRESENTED_AFTER;

	/**
	 * The feature id for the '<em><b>Presented Before</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__PRESENTED_BEFORE = ROLE_DESCRIPTOR__PRESENTED_BEFORE;

	/**
	 * The feature id for the '<em><b>Planning Data</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__PLANNING_DATA = ROLE_DESCRIPTOR__PLANNING_DATA;

	/**
	 * The feature id for the '<em><b>Super Activities</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__SUPER_ACTIVITIES = ROLE_DESCRIPTOR__SUPER_ACTIVITIES;

	/**
	 * The feature id for the '<em><b>Checklists</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__CHECKLISTS = ROLE_DESCRIPTOR__CHECKLISTS;

	/**
	 * The feature id for the '<em><b>Concepts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__CONCEPTS = ROLE_DESCRIPTOR__CONCEPTS;

	/**
	 * The feature id for the '<em><b>Examples</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__EXAMPLES = ROLE_DESCRIPTOR__EXAMPLES;

	/**
	 * The feature id for the '<em><b>Guidelines</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__GUIDELINES = ROLE_DESCRIPTOR__GUIDELINES;

	/**
	 * The feature id for the '<em><b>Reusable Assets</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__REUSABLE_ASSETS = ROLE_DESCRIPTOR__REUSABLE_ASSETS;

	/**
	 * The feature id for the '<em><b>Supporting Materials</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__SUPPORTING_MATERIALS = ROLE_DESCRIPTOR__SUPPORTING_MATERIALS;

	/**
	 * The feature id for the '<em><b>Templates</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__TEMPLATES = ROLE_DESCRIPTOR__TEMPLATES;

	/**
	 * The feature id for the '<em><b>Reports</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__REPORTS = ROLE_DESCRIPTOR__REPORTS;

	/**
	 * The feature id for the '<em><b>Estimationconsiderations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__ESTIMATIONCONSIDERATIONS = ROLE_DESCRIPTOR__ESTIMATIONCONSIDERATIONS;

	/**
	 * The feature id for the '<em><b>Toolmentor</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__TOOLMENTOR = ROLE_DESCRIPTOR__TOOLMENTOR;

	/**
	 * The feature id for the '<em><b>Is Synchronized With Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__IS_SYNCHRONIZED_WITH_SOURCE = ROLE_DESCRIPTOR__IS_SYNCHRONIZED_WITH_SOURCE;

	/**
	 * The feature id for the '<em><b>Guidance Exclude</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__GUIDANCE_EXCLUDE = ROLE_DESCRIPTOR__GUIDANCE_EXCLUDE;

	/**
	 * The feature id for the '<em><b>Guidance Additional</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__GUIDANCE_ADDITIONAL = ROLE_DESCRIPTOR__GUIDANCE_ADDITIONAL;

	/**
	 * The feature id for the '<em><b>Role</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__ROLE = ROLE_DESCRIPTOR__ROLE;

	/**
	 * The feature id for the '<em><b>Modifies</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__MODIFIES = ROLE_DESCRIPTOR__MODIFIES;

	/**
	 * The feature id for the '<em><b>Responsible For</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__RESPONSIBLE_FOR = ROLE_DESCRIPTOR__RESPONSIBLE_FOR;

	/**
	 * The feature id for the '<em><b>Responsible For Exclude</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__RESPONSIBLE_FOR_EXCLUDE = ROLE_DESCRIPTOR__RESPONSIBLE_FOR_EXCLUDE;

	/**
	 * The feature id for the '<em><b>Aggregated Roles</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__AGGREGATED_ROLES = ROLE_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Composite Role</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE_FEATURE_COUNT = ROLE_DESCRIPTOR_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__NAME = ACTIVITY__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__GUID = ACTIVITY__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__PRESENTATION_NAME = ACTIVITY__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__BRIEF_DESCRIPTION = ACTIVITY__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__OWNED_RULES = ACTIVITY__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__METHOD_ELEMENT_PROPERTY = ACTIVITY__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__KIND = ACTIVITY__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__SUPPRESSED = ACTIVITY__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__ORDERING_GUIDE = ACTIVITY__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__IS_ABSTRACT = ACTIVITY__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__PRESENTATION = ACTIVITY__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__SHAPEICON = ACTIVITY__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__NODEICON = ACTIVITY__NODEICON;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__PREFIX = ACTIVITY__PREFIX;

	/**
	 * The feature id for the '<em><b>Is Planned</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__IS_PLANNED = ACTIVITY__IS_PLANNED;

	/**
	 * The feature id for the '<em><b>Has Multiple Occurrences</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__HAS_MULTIPLE_OCCURRENCES = ACTIVITY__HAS_MULTIPLE_OCCURRENCES;

	/**
	 * The feature id for the '<em><b>Is Optional</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__IS_OPTIONAL = ACTIVITY__IS_OPTIONAL;

	/**
	 * The feature id for the '<em><b>Presented After</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__PRESENTED_AFTER = ACTIVITY__PRESENTED_AFTER;

	/**
	 * The feature id for the '<em><b>Presented Before</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__PRESENTED_BEFORE = ACTIVITY__PRESENTED_BEFORE;

	/**
	 * The feature id for the '<em><b>Planning Data</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__PLANNING_DATA = ACTIVITY__PLANNING_DATA;

	/**
	 * The feature id for the '<em><b>Super Activities</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__SUPER_ACTIVITIES = ACTIVITY__SUPER_ACTIVITIES;

	/**
	 * The feature id for the '<em><b>Checklists</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__CHECKLISTS = ACTIVITY__CHECKLISTS;

	/**
	 * The feature id for the '<em><b>Concepts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__CONCEPTS = ACTIVITY__CONCEPTS;

	/**
	 * The feature id for the '<em><b>Examples</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__EXAMPLES = ACTIVITY__EXAMPLES;

	/**
	 * The feature id for the '<em><b>Guidelines</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__GUIDELINES = ACTIVITY__GUIDELINES;

	/**
	 * The feature id for the '<em><b>Reusable Assets</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__REUSABLE_ASSETS = ACTIVITY__REUSABLE_ASSETS;

	/**
	 * The feature id for the '<em><b>Supporting Materials</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__SUPPORTING_MATERIALS = ACTIVITY__SUPPORTING_MATERIALS;

	/**
	 * The feature id for the '<em><b>Templates</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__TEMPLATES = ACTIVITY__TEMPLATES;

	/**
	 * The feature id for the '<em><b>Reports</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__REPORTS = ACTIVITY__REPORTS;

	/**
	 * The feature id for the '<em><b>Estimationconsiderations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__ESTIMATIONCONSIDERATIONS = ACTIVITY__ESTIMATIONCONSIDERATIONS;

	/**
	 * The feature id for the '<em><b>Toolmentor</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__TOOLMENTOR = ACTIVITY__TOOLMENTOR;

	/**
	 * The feature id for the '<em><b>Is Repeatable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__IS_REPEATABLE = ACTIVITY__IS_REPEATABLE;

	/**
	 * The feature id for the '<em><b>Is Ongoing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__IS_ONGOING = ACTIVITY__IS_ONGOING;

	/**
	 * The feature id for the '<em><b>Is Event Driven</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__IS_EVENT_DRIVEN = ACTIVITY__IS_EVENT_DRIVEN;

	/**
	 * The feature id for the '<em><b>Link To Predecessor</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__LINK_TO_PREDECESSOR = ACTIVITY__LINK_TO_PREDECESSOR;

	/**
	 * The feature id for the '<em><b>Fulfills</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__FULFILLS = ACTIVITY__FULFILLS;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__VARIABILITY_TYPE = ACTIVITY__VARIABILITY_TYPE;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__VARIABILITY_BASED_ON_ELEMENT = ACTIVITY__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Precondition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__PRECONDITION = ACTIVITY__PRECONDITION;

	/**
	 * The feature id for the '<em><b>Postcondition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__POSTCONDITION = ACTIVITY__POSTCONDITION;

	/**
	 * The feature id for the '<em><b>Breakdown Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__BREAKDOWN_ELEMENTS = ACTIVITY__BREAKDOWN_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Roadmaps</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__ROADMAPS = ACTIVITY__ROADMAPS;

	/**
	 * The feature id for the '<em><b>Includes Patterns</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__INCLUDES_PATTERNS = ACTIVITY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Default Context</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__DEFAULT_CONTEXT = ACTIVITY_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Valid Context</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__VALID_CONTEXT = ACTIVITY_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Process</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_FEATURE_COUNT = ACTIVITY_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__NAME = PROCESS__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__GUID = PROCESS__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__PRESENTATION_NAME = PROCESS__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__BRIEF_DESCRIPTION = PROCESS__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__OWNED_RULES = PROCESS__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__METHOD_ELEMENT_PROPERTY = PROCESS__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__KIND = PROCESS__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__SUPPRESSED = PROCESS__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__ORDERING_GUIDE = PROCESS__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__IS_ABSTRACT = PROCESS__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__PRESENTATION = PROCESS__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__SHAPEICON = PROCESS__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__NODEICON = PROCESS__NODEICON;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__PREFIX = PROCESS__PREFIX;

	/**
	 * The feature id for the '<em><b>Is Planned</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__IS_PLANNED = PROCESS__IS_PLANNED;

	/**
	 * The feature id for the '<em><b>Has Multiple Occurrences</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__HAS_MULTIPLE_OCCURRENCES = PROCESS__HAS_MULTIPLE_OCCURRENCES;

	/**
	 * The feature id for the '<em><b>Is Optional</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__IS_OPTIONAL = PROCESS__IS_OPTIONAL;

	/**
	 * The feature id for the '<em><b>Presented After</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__PRESENTED_AFTER = PROCESS__PRESENTED_AFTER;

	/**
	 * The feature id for the '<em><b>Presented Before</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__PRESENTED_BEFORE = PROCESS__PRESENTED_BEFORE;

	/**
	 * The feature id for the '<em><b>Planning Data</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__PLANNING_DATA = PROCESS__PLANNING_DATA;

	/**
	 * The feature id for the '<em><b>Super Activities</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__SUPER_ACTIVITIES = PROCESS__SUPER_ACTIVITIES;

	/**
	 * The feature id for the '<em><b>Checklists</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__CHECKLISTS = PROCESS__CHECKLISTS;

	/**
	 * The feature id for the '<em><b>Concepts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__CONCEPTS = PROCESS__CONCEPTS;

	/**
	 * The feature id for the '<em><b>Examples</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__EXAMPLES = PROCESS__EXAMPLES;

	/**
	 * The feature id for the '<em><b>Guidelines</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__GUIDELINES = PROCESS__GUIDELINES;

	/**
	 * The feature id for the '<em><b>Reusable Assets</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__REUSABLE_ASSETS = PROCESS__REUSABLE_ASSETS;

	/**
	 * The feature id for the '<em><b>Supporting Materials</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__SUPPORTING_MATERIALS = PROCESS__SUPPORTING_MATERIALS;

	/**
	 * The feature id for the '<em><b>Templates</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__TEMPLATES = PROCESS__TEMPLATES;

	/**
	 * The feature id for the '<em><b>Reports</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__REPORTS = PROCESS__REPORTS;

	/**
	 * The feature id for the '<em><b>Estimationconsiderations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__ESTIMATIONCONSIDERATIONS = PROCESS__ESTIMATIONCONSIDERATIONS;

	/**
	 * The feature id for the '<em><b>Toolmentor</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__TOOLMENTOR = PROCESS__TOOLMENTOR;

	/**
	 * The feature id for the '<em><b>Is Repeatable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__IS_REPEATABLE = PROCESS__IS_REPEATABLE;

	/**
	 * The feature id for the '<em><b>Is Ongoing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__IS_ONGOING = PROCESS__IS_ONGOING;

	/**
	 * The feature id for the '<em><b>Is Event Driven</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__IS_EVENT_DRIVEN = PROCESS__IS_EVENT_DRIVEN;

	/**
	 * The feature id for the '<em><b>Link To Predecessor</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__LINK_TO_PREDECESSOR = PROCESS__LINK_TO_PREDECESSOR;

	/**
	 * The feature id for the '<em><b>Fulfills</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__FULFILLS = PROCESS__FULFILLS;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__VARIABILITY_TYPE = PROCESS__VARIABILITY_TYPE;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__VARIABILITY_BASED_ON_ELEMENT = PROCESS__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Precondition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__PRECONDITION = PROCESS__PRECONDITION;

	/**
	 * The feature id for the '<em><b>Postcondition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__POSTCONDITION = PROCESS__POSTCONDITION;

	/**
	 * The feature id for the '<em><b>Breakdown Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__BREAKDOWN_ELEMENTS = PROCESS__BREAKDOWN_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Roadmaps</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__ROADMAPS = PROCESS__ROADMAPS;

	/**
	 * The feature id for the '<em><b>Includes Patterns</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__INCLUDES_PATTERNS = PROCESS__INCLUDES_PATTERNS;

	/**
	 * The feature id for the '<em><b>Default Context</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__DEFAULT_CONTEXT = PROCESS__DEFAULT_CONTEXT;

	/**
	 * The feature id for the '<em><b>Valid Context</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__VALID_CONTEXT = PROCESS__VALID_CONTEXT;

	/**
	 * The feature id for the '<em><b>Education Materials</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__EDUCATION_MATERIALS = PROCESS_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Communications Materials</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__COMMUNICATIONS_MATERIALS = PROCESS_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Delivery Process</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS_FEATURE_COUNT = PROCESS_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__NAME = PROCESS__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__GUID = PROCESS__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__PRESENTATION_NAME = PROCESS__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__BRIEF_DESCRIPTION = PROCESS__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__OWNED_RULES = PROCESS__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__METHOD_ELEMENT_PROPERTY = PROCESS__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__KIND = PROCESS__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__SUPPRESSED = PROCESS__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__ORDERING_GUIDE = PROCESS__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__IS_ABSTRACT = PROCESS__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__PRESENTATION = PROCESS__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__SHAPEICON = PROCESS__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__NODEICON = PROCESS__NODEICON;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__PREFIX = PROCESS__PREFIX;

	/**
	 * The feature id for the '<em><b>Is Planned</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__IS_PLANNED = PROCESS__IS_PLANNED;

	/**
	 * The feature id for the '<em><b>Has Multiple Occurrences</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__HAS_MULTIPLE_OCCURRENCES = PROCESS__HAS_MULTIPLE_OCCURRENCES;

	/**
	 * The feature id for the '<em><b>Is Optional</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__IS_OPTIONAL = PROCESS__IS_OPTIONAL;

	/**
	 * The feature id for the '<em><b>Presented After</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__PRESENTED_AFTER = PROCESS__PRESENTED_AFTER;

	/**
	 * The feature id for the '<em><b>Presented Before</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__PRESENTED_BEFORE = PROCESS__PRESENTED_BEFORE;

	/**
	 * The feature id for the '<em><b>Planning Data</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__PLANNING_DATA = PROCESS__PLANNING_DATA;

	/**
	 * The feature id for the '<em><b>Super Activities</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__SUPER_ACTIVITIES = PROCESS__SUPER_ACTIVITIES;

	/**
	 * The feature id for the '<em><b>Checklists</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__CHECKLISTS = PROCESS__CHECKLISTS;

	/**
	 * The feature id for the '<em><b>Concepts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__CONCEPTS = PROCESS__CONCEPTS;

	/**
	 * The feature id for the '<em><b>Examples</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__EXAMPLES = PROCESS__EXAMPLES;

	/**
	 * The feature id for the '<em><b>Guidelines</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__GUIDELINES = PROCESS__GUIDELINES;

	/**
	 * The feature id for the '<em><b>Reusable Assets</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__REUSABLE_ASSETS = PROCESS__REUSABLE_ASSETS;

	/**
	 * The feature id for the '<em><b>Supporting Materials</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__SUPPORTING_MATERIALS = PROCESS__SUPPORTING_MATERIALS;

	/**
	 * The feature id for the '<em><b>Templates</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__TEMPLATES = PROCESS__TEMPLATES;

	/**
	 * The feature id for the '<em><b>Reports</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__REPORTS = PROCESS__REPORTS;

	/**
	 * The feature id for the '<em><b>Estimationconsiderations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__ESTIMATIONCONSIDERATIONS = PROCESS__ESTIMATIONCONSIDERATIONS;

	/**
	 * The feature id for the '<em><b>Toolmentor</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__TOOLMENTOR = PROCESS__TOOLMENTOR;

	/**
	 * The feature id for the '<em><b>Is Repeatable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__IS_REPEATABLE = PROCESS__IS_REPEATABLE;

	/**
	 * The feature id for the '<em><b>Is Ongoing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__IS_ONGOING = PROCESS__IS_ONGOING;

	/**
	 * The feature id for the '<em><b>Is Event Driven</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__IS_EVENT_DRIVEN = PROCESS__IS_EVENT_DRIVEN;

	/**
	 * The feature id for the '<em><b>Link To Predecessor</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__LINK_TO_PREDECESSOR = PROCESS__LINK_TO_PREDECESSOR;

	/**
	 * The feature id for the '<em><b>Fulfills</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__FULFILLS = PROCESS__FULFILLS;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__VARIABILITY_TYPE = PROCESS__VARIABILITY_TYPE;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__VARIABILITY_BASED_ON_ELEMENT = PROCESS__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Precondition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__PRECONDITION = PROCESS__PRECONDITION;

	/**
	 * The feature id for the '<em><b>Postcondition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__POSTCONDITION = PROCESS__POSTCONDITION;

	/**
	 * The feature id for the '<em><b>Breakdown Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__BREAKDOWN_ELEMENTS = PROCESS__BREAKDOWN_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Roadmaps</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__ROADMAPS = PROCESS__ROADMAPS;

	/**
	 * The feature id for the '<em><b>Includes Patterns</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__INCLUDES_PATTERNS = PROCESS__INCLUDES_PATTERNS;

	/**
	 * The feature id for the '<em><b>Default Context</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__DEFAULT_CONTEXT = PROCESS__DEFAULT_CONTEXT;

	/**
	 * The feature id for the '<em><b>Valid Context</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__VALID_CONTEXT = PROCESS__VALID_CONTEXT;

	/**
	 * The number of structural features of the '<em>Capability Pattern</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN_FEATURE_COUNT = PROCESS_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.MethodConfigurationImpl <em>Method Configuration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.MethodConfigurationImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getMethodConfiguration()
	 * @generated
	 */
	int METHOD_CONFIGURATION = 77;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_CONFIGURATION__NAME = METHOD_UNIT__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_CONFIGURATION__GUID = METHOD_UNIT__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_CONFIGURATION__PRESENTATION_NAME = METHOD_UNIT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_CONFIGURATION__BRIEF_DESCRIPTION = METHOD_UNIT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_CONFIGURATION__OWNED_RULES = METHOD_UNIT__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_CONFIGURATION__METHOD_ELEMENT_PROPERTY = METHOD_UNIT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_CONFIGURATION__KIND = METHOD_UNIT__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_CONFIGURATION__SUPPRESSED = METHOD_UNIT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_CONFIGURATION__ORDERING_GUIDE = METHOD_UNIT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Authors</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_CONFIGURATION__AUTHORS = METHOD_UNIT__AUTHORS;

	/**
	 * The feature id for the '<em><b>Change Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_CONFIGURATION__CHANGE_DATE = METHOD_UNIT__CHANGE_DATE;

	/**
	 * The feature id for the '<em><b>Change Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_CONFIGURATION__CHANGE_DESCRIPTION = METHOD_UNIT__CHANGE_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_CONFIGURATION__VERSION = METHOD_UNIT__VERSION;

	/**
	 * The feature id for the '<em><b>Copyright Statement</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_CONFIGURATION__COPYRIGHT_STATEMENT = METHOD_UNIT__COPYRIGHT_STATEMENT;

	/**
	 * The feature id for the '<em><b>Method Plugin Selection</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_CONFIGURATION__METHOD_PLUGIN_SELECTION = METHOD_UNIT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Method Package Selection</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_CONFIGURATION__METHOD_PACKAGE_SELECTION = METHOD_UNIT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Process Views</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_CONFIGURATION__PROCESS_VIEWS = METHOD_UNIT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Default View</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_CONFIGURATION__DEFAULT_VIEW = METHOD_UNIT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Base Configurations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_CONFIGURATION__BASE_CONFIGURATIONS = METHOD_UNIT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Subtracted Category</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_CONFIGURATION__SUBTRACTED_CATEGORY = METHOD_UNIT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Added Category</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_CONFIGURATION__ADDED_CATEGORY = METHOD_UNIT_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>Method Configuration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_CONFIGURATION_FEATURE_COUNT = METHOD_UNIT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PLUGIN__NAME = METHOD_UNIT__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PLUGIN__GUID = METHOD_UNIT__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PLUGIN__PRESENTATION_NAME = METHOD_UNIT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PLUGIN__BRIEF_DESCRIPTION = METHOD_UNIT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PLUGIN__OWNED_RULES = METHOD_UNIT__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PLUGIN__METHOD_ELEMENT_PROPERTY = METHOD_UNIT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PLUGIN__KIND = METHOD_UNIT__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PLUGIN__SUPPRESSED = METHOD_UNIT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PLUGIN__ORDERING_GUIDE = METHOD_UNIT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Authors</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PLUGIN__AUTHORS = METHOD_UNIT__AUTHORS;

	/**
	 * The feature id for the '<em><b>Change Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PLUGIN__CHANGE_DATE = METHOD_UNIT__CHANGE_DATE;

	/**
	 * The feature id for the '<em><b>Change Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PLUGIN__CHANGE_DESCRIPTION = METHOD_UNIT__CHANGE_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PLUGIN__VERSION = METHOD_UNIT__VERSION;

	/**
	 * The feature id for the '<em><b>Copyright Statement</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PLUGIN__COPYRIGHT_STATEMENT = METHOD_UNIT__COPYRIGHT_STATEMENT;

	/**
	 * The feature id for the '<em><b>User Changeable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PLUGIN__USER_CHANGEABLE = METHOD_UNIT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Method Packages</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PLUGIN__METHOD_PACKAGES = METHOD_UNIT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Bases</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PLUGIN__BASES = METHOD_UNIT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Supporting</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PLUGIN__SUPPORTING = METHOD_UNIT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Method Plugin</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PLUGIN_FEATURE_COUNT = METHOD_UNIT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__NAME = PROCESS__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__GUID = PROCESS__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__PRESENTATION_NAME = PROCESS__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__BRIEF_DESCRIPTION = PROCESS__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__OWNED_RULES = PROCESS__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__METHOD_ELEMENT_PROPERTY = PROCESS__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__KIND = PROCESS__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__SUPPRESSED = PROCESS__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__ORDERING_GUIDE = PROCESS__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__IS_ABSTRACT = PROCESS__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__PRESENTATION = PROCESS__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__SHAPEICON = PROCESS__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__NODEICON = PROCESS__NODEICON;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__PREFIX = PROCESS__PREFIX;

	/**
	 * The feature id for the '<em><b>Is Planned</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__IS_PLANNED = PROCESS__IS_PLANNED;

	/**
	 * The feature id for the '<em><b>Has Multiple Occurrences</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__HAS_MULTIPLE_OCCURRENCES = PROCESS__HAS_MULTIPLE_OCCURRENCES;

	/**
	 * The feature id for the '<em><b>Is Optional</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__IS_OPTIONAL = PROCESS__IS_OPTIONAL;

	/**
	 * The feature id for the '<em><b>Presented After</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__PRESENTED_AFTER = PROCESS__PRESENTED_AFTER;

	/**
	 * The feature id for the '<em><b>Presented Before</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__PRESENTED_BEFORE = PROCESS__PRESENTED_BEFORE;

	/**
	 * The feature id for the '<em><b>Planning Data</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__PLANNING_DATA = PROCESS__PLANNING_DATA;

	/**
	 * The feature id for the '<em><b>Super Activities</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__SUPER_ACTIVITIES = PROCESS__SUPER_ACTIVITIES;

	/**
	 * The feature id for the '<em><b>Checklists</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__CHECKLISTS = PROCESS__CHECKLISTS;

	/**
	 * The feature id for the '<em><b>Concepts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__CONCEPTS = PROCESS__CONCEPTS;

	/**
	 * The feature id for the '<em><b>Examples</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__EXAMPLES = PROCESS__EXAMPLES;

	/**
	 * The feature id for the '<em><b>Guidelines</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__GUIDELINES = PROCESS__GUIDELINES;

	/**
	 * The feature id for the '<em><b>Reusable Assets</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__REUSABLE_ASSETS = PROCESS__REUSABLE_ASSETS;

	/**
	 * The feature id for the '<em><b>Supporting Materials</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__SUPPORTING_MATERIALS = PROCESS__SUPPORTING_MATERIALS;

	/**
	 * The feature id for the '<em><b>Templates</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__TEMPLATES = PROCESS__TEMPLATES;

	/**
	 * The feature id for the '<em><b>Reports</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__REPORTS = PROCESS__REPORTS;

	/**
	 * The feature id for the '<em><b>Estimationconsiderations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__ESTIMATIONCONSIDERATIONS = PROCESS__ESTIMATIONCONSIDERATIONS;

	/**
	 * The feature id for the '<em><b>Toolmentor</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__TOOLMENTOR = PROCESS__TOOLMENTOR;

	/**
	 * The feature id for the '<em><b>Is Repeatable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__IS_REPEATABLE = PROCESS__IS_REPEATABLE;

	/**
	 * The feature id for the '<em><b>Is Ongoing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__IS_ONGOING = PROCESS__IS_ONGOING;

	/**
	 * The feature id for the '<em><b>Is Event Driven</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__IS_EVENT_DRIVEN = PROCESS__IS_EVENT_DRIVEN;

	/**
	 * The feature id for the '<em><b>Link To Predecessor</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__LINK_TO_PREDECESSOR = PROCESS__LINK_TO_PREDECESSOR;

	/**
	 * The feature id for the '<em><b>Fulfills</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__FULFILLS = PROCESS__FULFILLS;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__VARIABILITY_TYPE = PROCESS__VARIABILITY_TYPE;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__VARIABILITY_BASED_ON_ELEMENT = PROCESS__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Precondition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__PRECONDITION = PROCESS__PRECONDITION;

	/**
	 * The feature id for the '<em><b>Postcondition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__POSTCONDITION = PROCESS__POSTCONDITION;

	/**
	 * The feature id for the '<em><b>Breakdown Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__BREAKDOWN_ELEMENTS = PROCESS__BREAKDOWN_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Roadmaps</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__ROADMAPS = PROCESS__ROADMAPS;

	/**
	 * The feature id for the '<em><b>Includes Patterns</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__INCLUDES_PATTERNS = PROCESS__INCLUDES_PATTERNS;

	/**
	 * The feature id for the '<em><b>Default Context</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__DEFAULT_CONTEXT = PROCESS__DEFAULT_CONTEXT;

	/**
	 * The feature id for the '<em><b>Valid Context</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__VALID_CONTEXT = PROCESS__VALID_CONTEXT;

	/**
	 * The feature id for the '<em><b>Based On Processes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__BASED_ON_PROCESSES = PROCESS_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Process Planning Template</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE_FEATURE_COUNT = PROCESS_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE__NAME = GUIDANCE__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE__GUID = GUIDANCE__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE__PRESENTATION_NAME = GUIDANCE__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE__BRIEF_DESCRIPTION = GUIDANCE__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE__OWNED_RULES = GUIDANCE__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE__METHOD_ELEMENT_PROPERTY = GUIDANCE__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE__KIND = GUIDANCE__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE__SUPPRESSED = GUIDANCE__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE__ORDERING_GUIDE = GUIDANCE__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE__IS_ABSTRACT = GUIDANCE__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE__PRESENTATION = GUIDANCE__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE__SHAPEICON = GUIDANCE__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE__NODEICON = GUIDANCE__NODEICON;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE__VARIABILITY_TYPE = GUIDANCE__VARIABILITY_TYPE;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE__VARIABILITY_BASED_ON_ELEMENT = GUIDANCE__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Supporting Materials</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE__SUPPORTING_MATERIALS = GUIDANCE__SUPPORTING_MATERIALS;

	/**
	 * The feature id for the '<em><b>Concepts And Papers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE__CONCEPTS_AND_PAPERS = GUIDANCE__CONCEPTS_AND_PAPERS;

	/**
	 * The feature id for the '<em><b>Checklists</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE__CHECKLISTS = GUIDANCE__CHECKLISTS;

	/**
	 * The feature id for the '<em><b>Guidelines</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE__GUIDELINES = GUIDANCE__GUIDELINES;

	/**
	 * The feature id for the '<em><b>Examples</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE__EXAMPLES = GUIDANCE__EXAMPLES;

	/**
	 * The feature id for the '<em><b>Assets</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE__ASSETS = GUIDANCE__ASSETS;

	/**
	 * The feature id for the '<em><b>Termdefinition</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE__TERMDEFINITION = GUIDANCE__TERMDEFINITION;

	/**
	 * The feature id for the '<em><b>Sub Practices</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE__SUB_PRACTICES = GUIDANCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Content References</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE__CONTENT_REFERENCES = GUIDANCE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Activity References</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE__ACTIVITY_REFERENCES = GUIDANCE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Practice</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE_FEATURE_COUNT = GUIDANCE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT_DESCRIPTION__NAME = CONTENT_DESCRIPTION__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT_DESCRIPTION__GUID = CONTENT_DESCRIPTION__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT_DESCRIPTION__PRESENTATION_NAME = CONTENT_DESCRIPTION__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT_DESCRIPTION__BRIEF_DESCRIPTION = CONTENT_DESCRIPTION__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT_DESCRIPTION__OWNED_RULES = CONTENT_DESCRIPTION__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT_DESCRIPTION__METHOD_ELEMENT_PROPERTY = CONTENT_DESCRIPTION__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT_DESCRIPTION__KIND = CONTENT_DESCRIPTION__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT_DESCRIPTION__SUPPRESSED = CONTENT_DESCRIPTION__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT_DESCRIPTION__ORDERING_GUIDE = CONTENT_DESCRIPTION__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Authors</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT_DESCRIPTION__AUTHORS = CONTENT_DESCRIPTION__AUTHORS;

	/**
	 * The feature id for the '<em><b>Change Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT_DESCRIPTION__CHANGE_DATE = CONTENT_DESCRIPTION__CHANGE_DATE;

	/**
	 * The feature id for the '<em><b>Change Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT_DESCRIPTION__CHANGE_DESCRIPTION = CONTENT_DESCRIPTION__CHANGE_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT_DESCRIPTION__VERSION = CONTENT_DESCRIPTION__VERSION;

	/**
	 * The feature id for the '<em><b>Copyright Statement</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT_DESCRIPTION__COPYRIGHT_STATEMENT = CONTENT_DESCRIPTION__COPYRIGHT_STATEMENT;

	/**
	 * The feature id for the '<em><b>Main Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT_DESCRIPTION__MAIN_DESCRIPTION = CONTENT_DESCRIPTION__MAIN_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Sections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT_DESCRIPTION__SECTIONS = CONTENT_DESCRIPTION__SECTIONS;

	/**
	 * The feature id for the '<em><b>External Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT_DESCRIPTION__EXTERNAL_ID = CONTENT_DESCRIPTION__EXTERNAL_ID;

	/**
	 * The feature id for the '<em><b>Key Considerations</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT_DESCRIPTION__KEY_CONSIDERATIONS = CONTENT_DESCRIPTION__KEY_CONSIDERATIONS;

	/**
	 * The feature id for the '<em><b>Long Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT_DESCRIPTION__LONG_PRESENTATION_NAME = CONTENT_DESCRIPTION__LONG_PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Usage Guidance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT_DESCRIPTION__USAGE_GUIDANCE = CONTENT_DESCRIPTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Breakdown Element Description</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT_DESCRIPTION_FEATURE_COUNT = CONTENT_DESCRIPTION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_DESCRIPTION__NAME = BREAKDOWN_ELEMENT_DESCRIPTION__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_DESCRIPTION__GUID = BREAKDOWN_ELEMENT_DESCRIPTION__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_DESCRIPTION__PRESENTATION_NAME = BREAKDOWN_ELEMENT_DESCRIPTION__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_DESCRIPTION__BRIEF_DESCRIPTION = BREAKDOWN_ELEMENT_DESCRIPTION__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_DESCRIPTION__OWNED_RULES = BREAKDOWN_ELEMENT_DESCRIPTION__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_DESCRIPTION__METHOD_ELEMENT_PROPERTY = BREAKDOWN_ELEMENT_DESCRIPTION__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_DESCRIPTION__KIND = BREAKDOWN_ELEMENT_DESCRIPTION__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_DESCRIPTION__SUPPRESSED = BREAKDOWN_ELEMENT_DESCRIPTION__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_DESCRIPTION__ORDERING_GUIDE = BREAKDOWN_ELEMENT_DESCRIPTION__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Authors</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_DESCRIPTION__AUTHORS = BREAKDOWN_ELEMENT_DESCRIPTION__AUTHORS;

	/**
	 * The feature id for the '<em><b>Change Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_DESCRIPTION__CHANGE_DATE = BREAKDOWN_ELEMENT_DESCRIPTION__CHANGE_DATE;

	/**
	 * The feature id for the '<em><b>Change Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_DESCRIPTION__CHANGE_DESCRIPTION = BREAKDOWN_ELEMENT_DESCRIPTION__CHANGE_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_DESCRIPTION__VERSION = BREAKDOWN_ELEMENT_DESCRIPTION__VERSION;

	/**
	 * The feature id for the '<em><b>Copyright Statement</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_DESCRIPTION__COPYRIGHT_STATEMENT = BREAKDOWN_ELEMENT_DESCRIPTION__COPYRIGHT_STATEMENT;

	/**
	 * The feature id for the '<em><b>Main Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_DESCRIPTION__MAIN_DESCRIPTION = BREAKDOWN_ELEMENT_DESCRIPTION__MAIN_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Sections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_DESCRIPTION__SECTIONS = BREAKDOWN_ELEMENT_DESCRIPTION__SECTIONS;

	/**
	 * The feature id for the '<em><b>External Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_DESCRIPTION__EXTERNAL_ID = BREAKDOWN_ELEMENT_DESCRIPTION__EXTERNAL_ID;

	/**
	 * The feature id for the '<em><b>Key Considerations</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_DESCRIPTION__KEY_CONSIDERATIONS = BREAKDOWN_ELEMENT_DESCRIPTION__KEY_CONSIDERATIONS;

	/**
	 * The feature id for the '<em><b>Long Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_DESCRIPTION__LONG_PRESENTATION_NAME = BREAKDOWN_ELEMENT_DESCRIPTION__LONG_PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Usage Guidance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_DESCRIPTION__USAGE_GUIDANCE = BREAKDOWN_ELEMENT_DESCRIPTION__USAGE_GUIDANCE;

	/**
	 * The feature id for the '<em><b>Purpose</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_DESCRIPTION__PURPOSE = BREAKDOWN_ELEMENT_DESCRIPTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Alternatives</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_DESCRIPTION__ALTERNATIVES = BREAKDOWN_ELEMENT_DESCRIPTION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Howto Staff</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_DESCRIPTION__HOWTO_STAFF = BREAKDOWN_ELEMENT_DESCRIPTION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Activity Description</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_DESCRIPTION_FEATURE_COUNT = BREAKDOWN_ELEMENT_DESCRIPTION_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DESCRIPTION__NAME = ACTIVITY_DESCRIPTION__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DESCRIPTION__GUID = ACTIVITY_DESCRIPTION__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DESCRIPTION__PRESENTATION_NAME = ACTIVITY_DESCRIPTION__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DESCRIPTION__BRIEF_DESCRIPTION = ACTIVITY_DESCRIPTION__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DESCRIPTION__OWNED_RULES = ACTIVITY_DESCRIPTION__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DESCRIPTION__METHOD_ELEMENT_PROPERTY = ACTIVITY_DESCRIPTION__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DESCRIPTION__KIND = ACTIVITY_DESCRIPTION__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DESCRIPTION__SUPPRESSED = ACTIVITY_DESCRIPTION__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DESCRIPTION__ORDERING_GUIDE = ACTIVITY_DESCRIPTION__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Authors</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DESCRIPTION__AUTHORS = ACTIVITY_DESCRIPTION__AUTHORS;

	/**
	 * The feature id for the '<em><b>Change Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DESCRIPTION__CHANGE_DATE = ACTIVITY_DESCRIPTION__CHANGE_DATE;

	/**
	 * The feature id for the '<em><b>Change Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DESCRIPTION__CHANGE_DESCRIPTION = ACTIVITY_DESCRIPTION__CHANGE_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DESCRIPTION__VERSION = ACTIVITY_DESCRIPTION__VERSION;

	/**
	 * The feature id for the '<em><b>Copyright Statement</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DESCRIPTION__COPYRIGHT_STATEMENT = ACTIVITY_DESCRIPTION__COPYRIGHT_STATEMENT;

	/**
	 * The feature id for the '<em><b>Main Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DESCRIPTION__MAIN_DESCRIPTION = ACTIVITY_DESCRIPTION__MAIN_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Sections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DESCRIPTION__SECTIONS = ACTIVITY_DESCRIPTION__SECTIONS;

	/**
	 * The feature id for the '<em><b>External Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DESCRIPTION__EXTERNAL_ID = ACTIVITY_DESCRIPTION__EXTERNAL_ID;

	/**
	 * The feature id for the '<em><b>Key Considerations</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DESCRIPTION__KEY_CONSIDERATIONS = ACTIVITY_DESCRIPTION__KEY_CONSIDERATIONS;

	/**
	 * The feature id for the '<em><b>Long Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DESCRIPTION__LONG_PRESENTATION_NAME = ACTIVITY_DESCRIPTION__LONG_PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Usage Guidance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DESCRIPTION__USAGE_GUIDANCE = ACTIVITY_DESCRIPTION__USAGE_GUIDANCE;

	/**
	 * The feature id for the '<em><b>Purpose</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DESCRIPTION__PURPOSE = ACTIVITY_DESCRIPTION__PURPOSE;

	/**
	 * The feature id for the '<em><b>Alternatives</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DESCRIPTION__ALTERNATIVES = ACTIVITY_DESCRIPTION__ALTERNATIVES;

	/**
	 * The feature id for the '<em><b>Howto Staff</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DESCRIPTION__HOWTO_STAFF = ACTIVITY_DESCRIPTION__HOWTO_STAFF;

	/**
	 * The feature id for the '<em><b>Scope</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DESCRIPTION__SCOPE = ACTIVITY_DESCRIPTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Usage Notes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DESCRIPTION__USAGE_NOTES = ACTIVITY_DESCRIPTION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Process Description</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DESCRIPTION_FEATURE_COUNT = ACTIVITY_DESCRIPTION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS_DESCRIPTION__NAME = PROCESS_DESCRIPTION__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS_DESCRIPTION__GUID = PROCESS_DESCRIPTION__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS_DESCRIPTION__PRESENTATION_NAME = PROCESS_DESCRIPTION__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS_DESCRIPTION__BRIEF_DESCRIPTION = PROCESS_DESCRIPTION__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS_DESCRIPTION__OWNED_RULES = PROCESS_DESCRIPTION__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS_DESCRIPTION__METHOD_ELEMENT_PROPERTY = PROCESS_DESCRIPTION__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS_DESCRIPTION__KIND = PROCESS_DESCRIPTION__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS_DESCRIPTION__SUPPRESSED = PROCESS_DESCRIPTION__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS_DESCRIPTION__ORDERING_GUIDE = PROCESS_DESCRIPTION__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Authors</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS_DESCRIPTION__AUTHORS = PROCESS_DESCRIPTION__AUTHORS;

	/**
	 * The feature id for the '<em><b>Change Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS_DESCRIPTION__CHANGE_DATE = PROCESS_DESCRIPTION__CHANGE_DATE;

	/**
	 * The feature id for the '<em><b>Change Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS_DESCRIPTION__CHANGE_DESCRIPTION = PROCESS_DESCRIPTION__CHANGE_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS_DESCRIPTION__VERSION = PROCESS_DESCRIPTION__VERSION;

	/**
	 * The feature id for the '<em><b>Copyright Statement</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS_DESCRIPTION__COPYRIGHT_STATEMENT = PROCESS_DESCRIPTION__COPYRIGHT_STATEMENT;

	/**
	 * The feature id for the '<em><b>Main Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS_DESCRIPTION__MAIN_DESCRIPTION = PROCESS_DESCRIPTION__MAIN_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Sections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS_DESCRIPTION__SECTIONS = PROCESS_DESCRIPTION__SECTIONS;

	/**
	 * The feature id for the '<em><b>External Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS_DESCRIPTION__EXTERNAL_ID = PROCESS_DESCRIPTION__EXTERNAL_ID;

	/**
	 * The feature id for the '<em><b>Key Considerations</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS_DESCRIPTION__KEY_CONSIDERATIONS = PROCESS_DESCRIPTION__KEY_CONSIDERATIONS;

	/**
	 * The feature id for the '<em><b>Long Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS_DESCRIPTION__LONG_PRESENTATION_NAME = PROCESS_DESCRIPTION__LONG_PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Usage Guidance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS_DESCRIPTION__USAGE_GUIDANCE = PROCESS_DESCRIPTION__USAGE_GUIDANCE;

	/**
	 * The feature id for the '<em><b>Purpose</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS_DESCRIPTION__PURPOSE = PROCESS_DESCRIPTION__PURPOSE;

	/**
	 * The feature id for the '<em><b>Alternatives</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS_DESCRIPTION__ALTERNATIVES = PROCESS_DESCRIPTION__ALTERNATIVES;

	/**
	 * The feature id for the '<em><b>Howto Staff</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS_DESCRIPTION__HOWTO_STAFF = PROCESS_DESCRIPTION__HOWTO_STAFF;

	/**
	 * The feature id for the '<em><b>Scope</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS_DESCRIPTION__SCOPE = PROCESS_DESCRIPTION__SCOPE;

	/**
	 * The feature id for the '<em><b>Usage Notes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS_DESCRIPTION__USAGE_NOTES = PROCESS_DESCRIPTION__USAGE_NOTES;

	/**
	 * The feature id for the '<em><b>Scale</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS_DESCRIPTION__SCALE = PROCESS_DESCRIPTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Project Characteristics</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS_DESCRIPTION__PROJECT_CHARACTERISTICS = PROCESS_DESCRIPTION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Risk Level</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS_DESCRIPTION__RISK_LEVEL = PROCESS_DESCRIPTION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Estimating Technique</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS_DESCRIPTION__ESTIMATING_TECHNIQUE = PROCESS_DESCRIPTION_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Project Member Expertise</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS_DESCRIPTION__PROJECT_MEMBER_EXPERTISE = PROCESS_DESCRIPTION_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Type Of Contract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS_DESCRIPTION__TYPE_OF_CONTRACT = PROCESS_DESCRIPTION_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Delivery Process Description</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS_DESCRIPTION_FEATURE_COUNT = PROCESS_DESCRIPTION_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR_DESCRIPTION__NAME = BREAKDOWN_ELEMENT_DESCRIPTION__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR_DESCRIPTION__GUID = BREAKDOWN_ELEMENT_DESCRIPTION__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR_DESCRIPTION__PRESENTATION_NAME = BREAKDOWN_ELEMENT_DESCRIPTION__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR_DESCRIPTION__BRIEF_DESCRIPTION = BREAKDOWN_ELEMENT_DESCRIPTION__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR_DESCRIPTION__OWNED_RULES = BREAKDOWN_ELEMENT_DESCRIPTION__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR_DESCRIPTION__METHOD_ELEMENT_PROPERTY = BREAKDOWN_ELEMENT_DESCRIPTION__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR_DESCRIPTION__KIND = BREAKDOWN_ELEMENT_DESCRIPTION__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR_DESCRIPTION__SUPPRESSED = BREAKDOWN_ELEMENT_DESCRIPTION__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR_DESCRIPTION__ORDERING_GUIDE = BREAKDOWN_ELEMENT_DESCRIPTION__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Authors</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR_DESCRIPTION__AUTHORS = BREAKDOWN_ELEMENT_DESCRIPTION__AUTHORS;

	/**
	 * The feature id for the '<em><b>Change Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR_DESCRIPTION__CHANGE_DATE = BREAKDOWN_ELEMENT_DESCRIPTION__CHANGE_DATE;

	/**
	 * The feature id for the '<em><b>Change Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR_DESCRIPTION__CHANGE_DESCRIPTION = BREAKDOWN_ELEMENT_DESCRIPTION__CHANGE_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR_DESCRIPTION__VERSION = BREAKDOWN_ELEMENT_DESCRIPTION__VERSION;

	/**
	 * The feature id for the '<em><b>Copyright Statement</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR_DESCRIPTION__COPYRIGHT_STATEMENT = BREAKDOWN_ELEMENT_DESCRIPTION__COPYRIGHT_STATEMENT;

	/**
	 * The feature id for the '<em><b>Main Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR_DESCRIPTION__MAIN_DESCRIPTION = BREAKDOWN_ELEMENT_DESCRIPTION__MAIN_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Sections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR_DESCRIPTION__SECTIONS = BREAKDOWN_ELEMENT_DESCRIPTION__SECTIONS;

	/**
	 * The feature id for the '<em><b>External Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR_DESCRIPTION__EXTERNAL_ID = BREAKDOWN_ELEMENT_DESCRIPTION__EXTERNAL_ID;

	/**
	 * The feature id for the '<em><b>Key Considerations</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR_DESCRIPTION__KEY_CONSIDERATIONS = BREAKDOWN_ELEMENT_DESCRIPTION__KEY_CONSIDERATIONS;

	/**
	 * The feature id for the '<em><b>Long Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR_DESCRIPTION__LONG_PRESENTATION_NAME = BREAKDOWN_ELEMENT_DESCRIPTION__LONG_PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Usage Guidance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR_DESCRIPTION__USAGE_GUIDANCE = BREAKDOWN_ELEMENT_DESCRIPTION__USAGE_GUIDANCE;

	/**
	 * The feature id for the '<em><b>Refined Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR_DESCRIPTION__REFINED_DESCRIPTION = BREAKDOWN_ELEMENT_DESCRIPTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Descriptor Description</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR_DESCRIPTION_FEATURE_COUNT = BREAKDOWN_ELEMENT_DESCRIPTION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_DESCRIPTOR__NAME = DESCRIPTOR__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_DESCRIPTOR__GUID = DESCRIPTOR__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_DESCRIPTOR__PRESENTATION_NAME = DESCRIPTOR__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_DESCRIPTOR__BRIEF_DESCRIPTION = DESCRIPTOR__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_DESCRIPTOR__OWNED_RULES = DESCRIPTOR__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_DESCRIPTOR__METHOD_ELEMENT_PROPERTY = DESCRIPTOR__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_DESCRIPTOR__KIND = DESCRIPTOR__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_DESCRIPTOR__SUPPRESSED = DESCRIPTOR__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_DESCRIPTOR__ORDERING_GUIDE = DESCRIPTOR__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_DESCRIPTOR__IS_ABSTRACT = DESCRIPTOR__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_DESCRIPTOR__PRESENTATION = DESCRIPTOR__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_DESCRIPTOR__SHAPEICON = DESCRIPTOR__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_DESCRIPTOR__NODEICON = DESCRIPTOR__NODEICON;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_DESCRIPTOR__PREFIX = DESCRIPTOR__PREFIX;

	/**
	 * The feature id for the '<em><b>Is Planned</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_DESCRIPTOR__IS_PLANNED = DESCRIPTOR__IS_PLANNED;

	/**
	 * The feature id for the '<em><b>Has Multiple Occurrences</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_DESCRIPTOR__HAS_MULTIPLE_OCCURRENCES = DESCRIPTOR__HAS_MULTIPLE_OCCURRENCES;

	/**
	 * The feature id for the '<em><b>Is Optional</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_DESCRIPTOR__IS_OPTIONAL = DESCRIPTOR__IS_OPTIONAL;

	/**
	 * The feature id for the '<em><b>Presented After</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_DESCRIPTOR__PRESENTED_AFTER = DESCRIPTOR__PRESENTED_AFTER;

	/**
	 * The feature id for the '<em><b>Presented Before</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_DESCRIPTOR__PRESENTED_BEFORE = DESCRIPTOR__PRESENTED_BEFORE;

	/**
	 * The feature id for the '<em><b>Planning Data</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_DESCRIPTOR__PLANNING_DATA = DESCRIPTOR__PLANNING_DATA;

	/**
	 * The feature id for the '<em><b>Super Activities</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_DESCRIPTOR__SUPER_ACTIVITIES = DESCRIPTOR__SUPER_ACTIVITIES;

	/**
	 * The feature id for the '<em><b>Checklists</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_DESCRIPTOR__CHECKLISTS = DESCRIPTOR__CHECKLISTS;

	/**
	 * The feature id for the '<em><b>Concepts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_DESCRIPTOR__CONCEPTS = DESCRIPTOR__CONCEPTS;

	/**
	 * The feature id for the '<em><b>Examples</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_DESCRIPTOR__EXAMPLES = DESCRIPTOR__EXAMPLES;

	/**
	 * The feature id for the '<em><b>Guidelines</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_DESCRIPTOR__GUIDELINES = DESCRIPTOR__GUIDELINES;

	/**
	 * The feature id for the '<em><b>Reusable Assets</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_DESCRIPTOR__REUSABLE_ASSETS = DESCRIPTOR__REUSABLE_ASSETS;

	/**
	 * The feature id for the '<em><b>Supporting Materials</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_DESCRIPTOR__SUPPORTING_MATERIALS = DESCRIPTOR__SUPPORTING_MATERIALS;

	/**
	 * The feature id for the '<em><b>Templates</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_DESCRIPTOR__TEMPLATES = DESCRIPTOR__TEMPLATES;

	/**
	 * The feature id for the '<em><b>Reports</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_DESCRIPTOR__REPORTS = DESCRIPTOR__REPORTS;

	/**
	 * The feature id for the '<em><b>Estimationconsiderations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_DESCRIPTOR__ESTIMATIONCONSIDERATIONS = DESCRIPTOR__ESTIMATIONCONSIDERATIONS;

	/**
	 * The feature id for the '<em><b>Toolmentor</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_DESCRIPTOR__TOOLMENTOR = DESCRIPTOR__TOOLMENTOR;

	/**
	 * The feature id for the '<em><b>Is Synchronized With Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_DESCRIPTOR__IS_SYNCHRONIZED_WITH_SOURCE = DESCRIPTOR__IS_SYNCHRONIZED_WITH_SOURCE;

	/**
	 * The feature id for the '<em><b>Guidance Exclude</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_DESCRIPTOR__GUIDANCE_EXCLUDE = DESCRIPTOR__GUIDANCE_EXCLUDE;

	/**
	 * The feature id for the '<em><b>Guidance Additional</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_DESCRIPTOR__GUIDANCE_ADDITIONAL = DESCRIPTOR__GUIDANCE_ADDITIONAL;

	/**
	 * The feature id for the '<em><b>process Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_DESCRIPTOR__PROCESS_COMPONENT = DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Process Component Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_DESCRIPTOR_FEATURE_COUNT = DESCRIPTOR_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PACKAGE__NAME = METHOD_PACKAGE__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PACKAGE__GUID = METHOD_PACKAGE__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PACKAGE__PRESENTATION_NAME = METHOD_PACKAGE__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PACKAGE__BRIEF_DESCRIPTION = METHOD_PACKAGE__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PACKAGE__OWNED_RULES = METHOD_PACKAGE__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PACKAGE__METHOD_ELEMENT_PROPERTY = METHOD_PACKAGE__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PACKAGE__KIND = METHOD_PACKAGE__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PACKAGE__SUPPRESSED = METHOD_PACKAGE__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PACKAGE__ORDERING_GUIDE = METHOD_PACKAGE__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Global</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PACKAGE__GLOBAL = METHOD_PACKAGE__GLOBAL;

	/**
	 * The feature id for the '<em><b>Reused Packages</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PACKAGE__REUSED_PACKAGES = METHOD_PACKAGE__REUSED_PACKAGES;

	/**
	 * The feature id for the '<em><b>Child Packages</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PACKAGE__CHILD_PACKAGES = METHOD_PACKAGE__CHILD_PACKAGES;

	/**
	 * The feature id for the '<em><b>Process Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PACKAGE__PROCESS_ELEMENTS = METHOD_PACKAGE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Diagrams</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PACKAGE__DIAGRAMS = METHOD_PACKAGE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Process Package</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PACKAGE_FEATURE_COUNT = METHOD_PACKAGE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT__NAME = PROCESS_PACKAGE__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT__GUID = PROCESS_PACKAGE__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT__PRESENTATION_NAME = PROCESS_PACKAGE__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT__BRIEF_DESCRIPTION = PROCESS_PACKAGE__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT__OWNED_RULES = PROCESS_PACKAGE__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT__METHOD_ELEMENT_PROPERTY = PROCESS_PACKAGE__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT__KIND = PROCESS_PACKAGE__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT__SUPPRESSED = PROCESS_PACKAGE__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT__ORDERING_GUIDE = PROCESS_PACKAGE__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Global</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT__GLOBAL = PROCESS_PACKAGE__GLOBAL;

	/**
	 * The feature id for the '<em><b>Reused Packages</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT__REUSED_PACKAGES = PROCESS_PACKAGE__REUSED_PACKAGES;

	/**
	 * The feature id for the '<em><b>Child Packages</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT__CHILD_PACKAGES = PROCESS_PACKAGE__CHILD_PACKAGES;

	/**
	 * The feature id for the '<em><b>Process Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT__PROCESS_ELEMENTS = PROCESS_PACKAGE__PROCESS_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Diagrams</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT__DIAGRAMS = PROCESS_PACKAGE__DIAGRAMS;

	/**
	 * The feature id for the '<em><b>Authors</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT__AUTHORS = PROCESS_PACKAGE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Change Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT__CHANGE_DATE = PROCESS_PACKAGE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Change Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT__CHANGE_DESCRIPTION = PROCESS_PACKAGE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT__VERSION = PROCESS_PACKAGE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Copyright Statement</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT__COPYRIGHT_STATEMENT = PROCESS_PACKAGE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT__INTERFACES = PROCESS_PACKAGE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Process</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT__PROCESS = PROCESS_PACKAGE_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>Process Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_FEATURE_COUNT = PROCESS_PACKAGE_FEATURE_COUNT + 7;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.ProcessFamilyImpl <em>Process Family</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.ProcessFamilyImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getProcessFamily()
	 * @generated
	 */
	int PROCESS_FAMILY = 111;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.MethodLibraryImpl <em>Method Library</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.MethodLibraryImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getMethodLibrary()
	 * @generated
	 */
	int METHOD_LIBRARY = 112;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.PointImpl <em>Point</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.PointImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getPoint()
	 * @generated
	 */
	int POINT = 95;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.DiagramElementImpl <em>Diagram Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.DiagramElementImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getDiagramElement()
	 * @generated
	 */
	int DIAGRAM_ELEMENT = 92;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_ELEMENT__NAME = METHOD_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_ELEMENT__GUID = METHOD_ELEMENT__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_ELEMENT__PRESENTATION_NAME = METHOD_ELEMENT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_ELEMENT__BRIEF_DESCRIPTION = METHOD_ELEMENT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_ELEMENT__OWNED_RULES = METHOD_ELEMENT__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_ELEMENT__METHOD_ELEMENT_PROPERTY = METHOD_ELEMENT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_ELEMENT__KIND = METHOD_ELEMENT__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_ELEMENT__SUPPRESSED = METHOD_ELEMENT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_ELEMENT__ORDERING_GUIDE = METHOD_ELEMENT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_ELEMENT__IS_VISIBLE = METHOD_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_ELEMENT__CONTAINER = METHOD_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Reference</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_ELEMENT__REFERENCE = METHOD_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_ELEMENT__PROPERTY = METHOD_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Diagram Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_ELEMENT_FEATURE_COUNT = METHOD_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.GraphElementImpl <em>Graph Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.GraphElementImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getGraphElement()
	 * @generated
	 */
	int GRAPH_ELEMENT = 91;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_ELEMENT__NAME = DIAGRAM_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_ELEMENT__GUID = DIAGRAM_ELEMENT__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_ELEMENT__PRESENTATION_NAME = DIAGRAM_ELEMENT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_ELEMENT__BRIEF_DESCRIPTION = DIAGRAM_ELEMENT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_ELEMENT__OWNED_RULES = DIAGRAM_ELEMENT__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_ELEMENT__METHOD_ELEMENT_PROPERTY = DIAGRAM_ELEMENT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_ELEMENT__KIND = DIAGRAM_ELEMENT__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_ELEMENT__SUPPRESSED = DIAGRAM_ELEMENT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_ELEMENT__ORDERING_GUIDE = DIAGRAM_ELEMENT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_ELEMENT__IS_VISIBLE = DIAGRAM_ELEMENT__IS_VISIBLE;

	/**
	 * The feature id for the '<em><b>Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_ELEMENT__CONTAINER = DIAGRAM_ELEMENT__CONTAINER;

	/**
	 * The feature id for the '<em><b>Reference</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_ELEMENT__REFERENCE = DIAGRAM_ELEMENT__REFERENCE;

	/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_ELEMENT__PROPERTY = DIAGRAM_ELEMENT__PROPERTY;

	/**
	 * The feature id for the '<em><b>Contained</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_ELEMENT__CONTAINED = DIAGRAM_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Position</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_ELEMENT__POSITION = DIAGRAM_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Link</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_ELEMENT__LINK = DIAGRAM_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Anchorage</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_ELEMENT__ANCHORAGE = DIAGRAM_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Semantic Model</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_ELEMENT__SEMANTIC_MODEL = DIAGRAM_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Graph Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_ELEMENT_FEATURE_COUNT = DIAGRAM_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.DiagramLinkImpl <em>Diagram Link</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.DiagramLinkImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getDiagramLink()
	 * @generated
	 */
	int DIAGRAM_LINK = 96;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.GraphConnectorImpl <em>Graph Connector</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.GraphConnectorImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getGraphConnector()
	 * @generated
	 */
	int GRAPH_CONNECTOR = 97;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.SemanticModelBridgeImpl <em>Semantic Model Bridge</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.SemanticModelBridgeImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getSemanticModelBridge()
	 * @generated
	 */
	int SEMANTIC_MODEL_BRIDGE = 99;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.DimensionImpl <em>Dimension</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.DimensionImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getDimension()
	 * @generated
	 */
	int DIMENSION = 100;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.ReferenceImpl <em>Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.ReferenceImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getReference()
	 * @generated
	 */
	int REFERENCE = 93;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.PropertyImpl <em>Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.PropertyImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getProperty()
	 * @generated
	 */
	int PROPERTY = 94;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.GraphEdgeImpl <em>Graph Edge</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.GraphEdgeImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getGraphEdge()
	 * @generated
	 */
	int GRAPH_EDGE = 98;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.GraphNodeImpl <em>Graph Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.GraphNodeImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getGraphNode()
	 * @generated
	 */
	int GRAPH_NODE = 90;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_NODE__NAME = GRAPH_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_NODE__GUID = GRAPH_ELEMENT__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_NODE__PRESENTATION_NAME = GRAPH_ELEMENT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_NODE__BRIEF_DESCRIPTION = GRAPH_ELEMENT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_NODE__OWNED_RULES = GRAPH_ELEMENT__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_NODE__METHOD_ELEMENT_PROPERTY = GRAPH_ELEMENT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_NODE__KIND = GRAPH_ELEMENT__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_NODE__SUPPRESSED = GRAPH_ELEMENT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_NODE__ORDERING_GUIDE = GRAPH_ELEMENT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_NODE__IS_VISIBLE = GRAPH_ELEMENT__IS_VISIBLE;

	/**
	 * The feature id for the '<em><b>Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_NODE__CONTAINER = GRAPH_ELEMENT__CONTAINER;

	/**
	 * The feature id for the '<em><b>Reference</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_NODE__REFERENCE = GRAPH_ELEMENT__REFERENCE;

	/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_NODE__PROPERTY = GRAPH_ELEMENT__PROPERTY;

	/**
	 * The feature id for the '<em><b>Contained</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_NODE__CONTAINED = GRAPH_ELEMENT__CONTAINED;

	/**
	 * The feature id for the '<em><b>Position</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_NODE__POSITION = GRAPH_ELEMENT__POSITION;

	/**
	 * The feature id for the '<em><b>Link</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_NODE__LINK = GRAPH_ELEMENT__LINK;

	/**
	 * The feature id for the '<em><b>Anchorage</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_NODE__ANCHORAGE = GRAPH_ELEMENT__ANCHORAGE;

	/**
	 * The feature id for the '<em><b>Semantic Model</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_NODE__SEMANTIC_MODEL = GRAPH_ELEMENT__SEMANTIC_MODEL;

	/**
	 * The feature id for the '<em><b>Size</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_NODE__SIZE = GRAPH_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Graph Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_NODE_FEATURE_COUNT = GRAPH_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.DiagramImpl <em>Diagram</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.DiagramImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getDiagram()
	 * @generated
	 */
	int DIAGRAM = 89;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__NAME = GRAPH_NODE__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__GUID = GRAPH_NODE__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__PRESENTATION_NAME = GRAPH_NODE__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__BRIEF_DESCRIPTION = GRAPH_NODE__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__OWNED_RULES = GRAPH_NODE__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__METHOD_ELEMENT_PROPERTY = GRAPH_NODE__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__KIND = GRAPH_NODE__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__SUPPRESSED = GRAPH_NODE__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__ORDERING_GUIDE = GRAPH_NODE__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__IS_VISIBLE = GRAPH_NODE__IS_VISIBLE;

	/**
	 * The feature id for the '<em><b>Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__CONTAINER = GRAPH_NODE__CONTAINER;

	/**
	 * The feature id for the '<em><b>Reference</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__REFERENCE = GRAPH_NODE__REFERENCE;

	/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__PROPERTY = GRAPH_NODE__PROPERTY;

	/**
	 * The feature id for the '<em><b>Contained</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__CONTAINED = GRAPH_NODE__CONTAINED;

	/**
	 * The feature id for the '<em><b>Position</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__POSITION = GRAPH_NODE__POSITION;

	/**
	 * The feature id for the '<em><b>Link</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__LINK = GRAPH_NODE__LINK;

	/**
	 * The feature id for the '<em><b>Anchorage</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__ANCHORAGE = GRAPH_NODE__ANCHORAGE;

	/**
	 * The feature id for the '<em><b>Semantic Model</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__SEMANTIC_MODEL = GRAPH_NODE__SEMANTIC_MODEL;

	/**
	 * The feature id for the '<em><b>Size</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__SIZE = GRAPH_NODE__SIZE;

	/**
	 * The feature id for the '<em><b>Diagram Link</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__DIAGRAM_LINK = GRAPH_NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__NAMESPACE = GRAPH_NODE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Zoom</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__ZOOM = GRAPH_NODE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Viewpoint</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__VIEWPOINT = GRAPH_NODE_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Diagram</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_FEATURE_COUNT = GRAPH_NODE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__NAME = DIAGRAM_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__GUID = DIAGRAM_ELEMENT__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__PRESENTATION_NAME = DIAGRAM_ELEMENT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__BRIEF_DESCRIPTION = DIAGRAM_ELEMENT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__OWNED_RULES = DIAGRAM_ELEMENT__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__METHOD_ELEMENT_PROPERTY = DIAGRAM_ELEMENT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__KIND = DIAGRAM_ELEMENT__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__SUPPRESSED = DIAGRAM_ELEMENT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__ORDERING_GUIDE = DIAGRAM_ELEMENT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__IS_VISIBLE = DIAGRAM_ELEMENT__IS_VISIBLE;

	/**
	 * The feature id for the '<em><b>Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__CONTAINER = DIAGRAM_ELEMENT__CONTAINER;

	/**
	 * The feature id for the '<em><b>Reference</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__REFERENCE = DIAGRAM_ELEMENT__REFERENCE;

	/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__PROPERTY = DIAGRAM_ELEMENT__PROPERTY;

	/**
	 * The feature id for the '<em><b>Is Individual Representation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__IS_INDIVIDUAL_REPRESENTATION = DIAGRAM_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Referenced</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__REFERENCED = DIAGRAM_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_FEATURE_COUNT = DIAGRAM_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY__NAME = DIAGRAM_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY__GUID = DIAGRAM_ELEMENT__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY__PRESENTATION_NAME = DIAGRAM_ELEMENT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY__BRIEF_DESCRIPTION = DIAGRAM_ELEMENT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY__OWNED_RULES = DIAGRAM_ELEMENT__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY__METHOD_ELEMENT_PROPERTY = DIAGRAM_ELEMENT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY__KIND = DIAGRAM_ELEMENT__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY__SUPPRESSED = DIAGRAM_ELEMENT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY__ORDERING_GUIDE = DIAGRAM_ELEMENT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY__IS_VISIBLE = DIAGRAM_ELEMENT__IS_VISIBLE;

	/**
	 * The feature id for the '<em><b>Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY__CONTAINER = DIAGRAM_ELEMENT__CONTAINER;

	/**
	 * The feature id for the '<em><b>Reference</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY__REFERENCE = DIAGRAM_ELEMENT__REFERENCE;

	/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY__PROPERTY = DIAGRAM_ELEMENT__PROPERTY;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY__KEY = DIAGRAM_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY__VALUE = DIAGRAM_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_FEATURE_COUNT = DIAGRAM_ELEMENT_FEATURE_COUNT + 2;

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
	 * The number of structural features of the '<em>Point</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POINT_FEATURE_COUNT = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_LINK__NAME = DIAGRAM_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_LINK__GUID = DIAGRAM_ELEMENT__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_LINK__PRESENTATION_NAME = DIAGRAM_ELEMENT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_LINK__BRIEF_DESCRIPTION = DIAGRAM_ELEMENT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_LINK__OWNED_RULES = DIAGRAM_ELEMENT__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_LINK__METHOD_ELEMENT_PROPERTY = DIAGRAM_ELEMENT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_LINK__KIND = DIAGRAM_ELEMENT__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_LINK__SUPPRESSED = DIAGRAM_ELEMENT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_LINK__ORDERING_GUIDE = DIAGRAM_ELEMENT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_LINK__IS_VISIBLE = DIAGRAM_ELEMENT__IS_VISIBLE;

	/**
	 * The feature id for the '<em><b>Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_LINK__CONTAINER = DIAGRAM_ELEMENT__CONTAINER;

	/**
	 * The feature id for the '<em><b>Reference</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_LINK__REFERENCE = DIAGRAM_ELEMENT__REFERENCE;

	/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_LINK__PROPERTY = DIAGRAM_ELEMENT__PROPERTY;

	/**
	 * The feature id for the '<em><b>Zoom</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_LINK__ZOOM = DIAGRAM_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Viewport</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_LINK__VIEWPORT = DIAGRAM_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Diagram</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_LINK__DIAGRAM = DIAGRAM_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Graph Element</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_LINK__GRAPH_ELEMENT = DIAGRAM_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Diagram Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_LINK_FEATURE_COUNT = DIAGRAM_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_CONNECTOR__NAME = GRAPH_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_CONNECTOR__GUID = GRAPH_ELEMENT__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_CONNECTOR__PRESENTATION_NAME = GRAPH_ELEMENT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_CONNECTOR__BRIEF_DESCRIPTION = GRAPH_ELEMENT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_CONNECTOR__OWNED_RULES = GRAPH_ELEMENT__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_CONNECTOR__METHOD_ELEMENT_PROPERTY = GRAPH_ELEMENT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_CONNECTOR__KIND = GRAPH_ELEMENT__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_CONNECTOR__SUPPRESSED = GRAPH_ELEMENT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_CONNECTOR__ORDERING_GUIDE = GRAPH_ELEMENT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_CONNECTOR__IS_VISIBLE = GRAPH_ELEMENT__IS_VISIBLE;

	/**
	 * The feature id for the '<em><b>Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_CONNECTOR__CONTAINER = GRAPH_ELEMENT__CONTAINER;

	/**
	 * The feature id for the '<em><b>Reference</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_CONNECTOR__REFERENCE = GRAPH_ELEMENT__REFERENCE;

	/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_CONNECTOR__PROPERTY = GRAPH_ELEMENT__PROPERTY;

	/**
	 * The feature id for the '<em><b>Contained</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_CONNECTOR__CONTAINED = GRAPH_ELEMENT__CONTAINED;

	/**
	 * The feature id for the '<em><b>Position</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_CONNECTOR__POSITION = GRAPH_ELEMENT__POSITION;

	/**
	 * The feature id for the '<em><b>Link</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_CONNECTOR__LINK = GRAPH_ELEMENT__LINK;

	/**
	 * The feature id for the '<em><b>Anchorage</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_CONNECTOR__ANCHORAGE = GRAPH_ELEMENT__ANCHORAGE;

	/**
	 * The feature id for the '<em><b>Semantic Model</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_CONNECTOR__SEMANTIC_MODEL = GRAPH_ELEMENT__SEMANTIC_MODEL;

	/**
	 * The feature id for the '<em><b>Graph Edge</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_CONNECTOR__GRAPH_EDGE = GRAPH_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Graph Element</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_CONNECTOR__GRAPH_ELEMENT = GRAPH_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Graph Connector</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_CONNECTOR_FEATURE_COUNT = GRAPH_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_EDGE__NAME = GRAPH_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_EDGE__GUID = GRAPH_ELEMENT__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_EDGE__PRESENTATION_NAME = GRAPH_ELEMENT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_EDGE__BRIEF_DESCRIPTION = GRAPH_ELEMENT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_EDGE__OWNED_RULES = GRAPH_ELEMENT__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_EDGE__METHOD_ELEMENT_PROPERTY = GRAPH_ELEMENT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_EDGE__KIND = GRAPH_ELEMENT__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_EDGE__SUPPRESSED = GRAPH_ELEMENT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_EDGE__ORDERING_GUIDE = GRAPH_ELEMENT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_EDGE__IS_VISIBLE = GRAPH_ELEMENT__IS_VISIBLE;

	/**
	 * The feature id for the '<em><b>Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_EDGE__CONTAINER = GRAPH_ELEMENT__CONTAINER;

	/**
	 * The feature id for the '<em><b>Reference</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_EDGE__REFERENCE = GRAPH_ELEMENT__REFERENCE;

	/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_EDGE__PROPERTY = GRAPH_ELEMENT__PROPERTY;

	/**
	 * The feature id for the '<em><b>Contained</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_EDGE__CONTAINED = GRAPH_ELEMENT__CONTAINED;

	/**
	 * The feature id for the '<em><b>Position</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_EDGE__POSITION = GRAPH_ELEMENT__POSITION;

	/**
	 * The feature id for the '<em><b>Link</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_EDGE__LINK = GRAPH_ELEMENT__LINK;

	/**
	 * The feature id for the '<em><b>Anchorage</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_EDGE__ANCHORAGE = GRAPH_ELEMENT__ANCHORAGE;

	/**
	 * The feature id for the '<em><b>Semantic Model</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_EDGE__SEMANTIC_MODEL = GRAPH_ELEMENT__SEMANTIC_MODEL;

	/**
	 * The feature id for the '<em><b>Waypoints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_EDGE__WAYPOINTS = GRAPH_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Anchor</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_EDGE__ANCHOR = GRAPH_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Graph Edge</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_EDGE_FEATURE_COUNT = GRAPH_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEMANTIC_MODEL_BRIDGE__NAME = DIAGRAM_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEMANTIC_MODEL_BRIDGE__GUID = DIAGRAM_ELEMENT__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEMANTIC_MODEL_BRIDGE__PRESENTATION_NAME = DIAGRAM_ELEMENT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEMANTIC_MODEL_BRIDGE__BRIEF_DESCRIPTION = DIAGRAM_ELEMENT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEMANTIC_MODEL_BRIDGE__OWNED_RULES = DIAGRAM_ELEMENT__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEMANTIC_MODEL_BRIDGE__METHOD_ELEMENT_PROPERTY = DIAGRAM_ELEMENT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEMANTIC_MODEL_BRIDGE__KIND = DIAGRAM_ELEMENT__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEMANTIC_MODEL_BRIDGE__SUPPRESSED = DIAGRAM_ELEMENT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEMANTIC_MODEL_BRIDGE__ORDERING_GUIDE = DIAGRAM_ELEMENT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEMANTIC_MODEL_BRIDGE__IS_VISIBLE = DIAGRAM_ELEMENT__IS_VISIBLE;

	/**
	 * The feature id for the '<em><b>Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEMANTIC_MODEL_BRIDGE__CONTAINER = DIAGRAM_ELEMENT__CONTAINER;

	/**
	 * The feature id for the '<em><b>Reference</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEMANTIC_MODEL_BRIDGE__REFERENCE = DIAGRAM_ELEMENT__REFERENCE;

	/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEMANTIC_MODEL_BRIDGE__PROPERTY = DIAGRAM_ELEMENT__PROPERTY;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEMANTIC_MODEL_BRIDGE__PRESENTATION = DIAGRAM_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Diagram</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEMANTIC_MODEL_BRIDGE__DIAGRAM = DIAGRAM_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Graph Element</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEMANTIC_MODEL_BRIDGE__GRAPH_ELEMENT = DIAGRAM_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Semantic Model Bridge</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEMANTIC_MODEL_BRIDGE_FEATURE_COUNT = DIAGRAM_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION__WIDTH = 0;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION__HEIGHT = 1;

	/**
	 * The number of structural features of the '<em>Dimension</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION_FEATURE_COUNT = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE__NAME = BREAKDOWN_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE__GUID = BREAKDOWN_ELEMENT__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE__PRESENTATION_NAME = BREAKDOWN_ELEMENT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE__BRIEF_DESCRIPTION = BREAKDOWN_ELEMENT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE__OWNED_RULES = BREAKDOWN_ELEMENT__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE__METHOD_ELEMENT_PROPERTY = BREAKDOWN_ELEMENT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE__KIND = BREAKDOWN_ELEMENT__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE__SUPPRESSED = BREAKDOWN_ELEMENT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE__ORDERING_GUIDE = BREAKDOWN_ELEMENT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE__IS_ABSTRACT = BREAKDOWN_ELEMENT__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE__PRESENTATION = BREAKDOWN_ELEMENT__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE__SHAPEICON = BREAKDOWN_ELEMENT__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE__NODEICON = BREAKDOWN_ELEMENT__NODEICON;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE__PREFIX = BREAKDOWN_ELEMENT__PREFIX;

	/**
	 * The feature id for the '<em><b>Is Planned</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE__IS_PLANNED = BREAKDOWN_ELEMENT__IS_PLANNED;

	/**
	 * The feature id for the '<em><b>Has Multiple Occurrences</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE__HAS_MULTIPLE_OCCURRENCES = BREAKDOWN_ELEMENT__HAS_MULTIPLE_OCCURRENCES;

	/**
	 * The feature id for the '<em><b>Is Optional</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE__IS_OPTIONAL = BREAKDOWN_ELEMENT__IS_OPTIONAL;

	/**
	 * The feature id for the '<em><b>Presented After</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE__PRESENTED_AFTER = BREAKDOWN_ELEMENT__PRESENTED_AFTER;

	/**
	 * The feature id for the '<em><b>Presented Before</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE__PRESENTED_BEFORE = BREAKDOWN_ELEMENT__PRESENTED_BEFORE;

	/**
	 * The feature id for the '<em><b>Planning Data</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE__PLANNING_DATA = BREAKDOWN_ELEMENT__PLANNING_DATA;

	/**
	 * The feature id for the '<em><b>Super Activities</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE__SUPER_ACTIVITIES = BREAKDOWN_ELEMENT__SUPER_ACTIVITIES;

	/**
	 * The feature id for the '<em><b>Checklists</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE__CHECKLISTS = BREAKDOWN_ELEMENT__CHECKLISTS;

	/**
	 * The feature id for the '<em><b>Concepts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE__CONCEPTS = BREAKDOWN_ELEMENT__CONCEPTS;

	/**
	 * The feature id for the '<em><b>Examples</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE__EXAMPLES = BREAKDOWN_ELEMENT__EXAMPLES;

	/**
	 * The feature id for the '<em><b>Guidelines</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE__GUIDELINES = BREAKDOWN_ELEMENT__GUIDELINES;

	/**
	 * The feature id for the '<em><b>Reusable Assets</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE__REUSABLE_ASSETS = BREAKDOWN_ELEMENT__REUSABLE_ASSETS;

	/**
	 * The feature id for the '<em><b>Supporting Materials</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE__SUPPORTING_MATERIALS = BREAKDOWN_ELEMENT__SUPPORTING_MATERIALS;

	/**
	 * The feature id for the '<em><b>Templates</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE__TEMPLATES = BREAKDOWN_ELEMENT__TEMPLATES;

	/**
	 * The feature id for the '<em><b>Reports</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE__REPORTS = BREAKDOWN_ELEMENT__REPORTS;

	/**
	 * The feature id for the '<em><b>Estimationconsiderations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE__ESTIMATIONCONSIDERATIONS = BREAKDOWN_ELEMENT__ESTIMATIONCONSIDERATIONS;

	/**
	 * The feature id for the '<em><b>Toolmentor</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE__TOOLMENTOR = BREAKDOWN_ELEMENT__TOOLMENTOR;

	/**
	 * The feature id for the '<em><b>Interface Specifications</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE__INTERFACE_SPECIFICATIONS = BREAKDOWN_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Interface IO</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE__INTERFACE_IO = BREAKDOWN_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Process Component Interface</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE_FEATURE_COUNT = BREAKDOWN_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.SimpleSemanticModelElementImpl <em>Simple Semantic Model Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.SimpleSemanticModelElementImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getSimpleSemanticModelElement()
	 * @generated
	 */
	int SIMPLE_SEMANTIC_MODEL_ELEMENT = 102;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_SEMANTIC_MODEL_ELEMENT__NAME = SEMANTIC_MODEL_BRIDGE__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_SEMANTIC_MODEL_ELEMENT__GUID = SEMANTIC_MODEL_BRIDGE__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_SEMANTIC_MODEL_ELEMENT__PRESENTATION_NAME = SEMANTIC_MODEL_BRIDGE__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_SEMANTIC_MODEL_ELEMENT__BRIEF_DESCRIPTION = SEMANTIC_MODEL_BRIDGE__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_SEMANTIC_MODEL_ELEMENT__OWNED_RULES = SEMANTIC_MODEL_BRIDGE__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_SEMANTIC_MODEL_ELEMENT__METHOD_ELEMENT_PROPERTY = SEMANTIC_MODEL_BRIDGE__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_SEMANTIC_MODEL_ELEMENT__KIND = SEMANTIC_MODEL_BRIDGE__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_SEMANTIC_MODEL_ELEMENT__SUPPRESSED = SEMANTIC_MODEL_BRIDGE__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_SEMANTIC_MODEL_ELEMENT__ORDERING_GUIDE = SEMANTIC_MODEL_BRIDGE__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_SEMANTIC_MODEL_ELEMENT__IS_VISIBLE = SEMANTIC_MODEL_BRIDGE__IS_VISIBLE;

	/**
	 * The feature id for the '<em><b>Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_SEMANTIC_MODEL_ELEMENT__CONTAINER = SEMANTIC_MODEL_BRIDGE__CONTAINER;

	/**
	 * The feature id for the '<em><b>Reference</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_SEMANTIC_MODEL_ELEMENT__REFERENCE = SEMANTIC_MODEL_BRIDGE__REFERENCE;

	/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_SEMANTIC_MODEL_ELEMENT__PROPERTY = SEMANTIC_MODEL_BRIDGE__PROPERTY;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_SEMANTIC_MODEL_ELEMENT__PRESENTATION = SEMANTIC_MODEL_BRIDGE__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Diagram</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_SEMANTIC_MODEL_ELEMENT__DIAGRAM = SEMANTIC_MODEL_BRIDGE__DIAGRAM;

	/**
	 * The feature id for the '<em><b>Graph Element</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_SEMANTIC_MODEL_ELEMENT__GRAPH_ELEMENT = SEMANTIC_MODEL_BRIDGE__GRAPH_ELEMENT;

	/**
	 * The feature id for the '<em><b>Type Info</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_SEMANTIC_MODEL_ELEMENT__TYPE_INFO = SEMANTIC_MODEL_BRIDGE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Simple Semantic Model Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_SEMANTIC_MODEL_ELEMENT_FEATURE_COUNT = SEMANTIC_MODEL_BRIDGE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.UMASemanticModelBridgeImpl <em>UMA Semantic Model Bridge</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.UMASemanticModelBridgeImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getUMASemanticModelBridge()
	 * @generated
	 */
	int UMA_SEMANTIC_MODEL_BRIDGE = 103;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UMA_SEMANTIC_MODEL_BRIDGE__NAME = SEMANTIC_MODEL_BRIDGE__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UMA_SEMANTIC_MODEL_BRIDGE__GUID = SEMANTIC_MODEL_BRIDGE__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UMA_SEMANTIC_MODEL_BRIDGE__PRESENTATION_NAME = SEMANTIC_MODEL_BRIDGE__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UMA_SEMANTIC_MODEL_BRIDGE__BRIEF_DESCRIPTION = SEMANTIC_MODEL_BRIDGE__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UMA_SEMANTIC_MODEL_BRIDGE__OWNED_RULES = SEMANTIC_MODEL_BRIDGE__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UMA_SEMANTIC_MODEL_BRIDGE__METHOD_ELEMENT_PROPERTY = SEMANTIC_MODEL_BRIDGE__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UMA_SEMANTIC_MODEL_BRIDGE__KIND = SEMANTIC_MODEL_BRIDGE__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UMA_SEMANTIC_MODEL_BRIDGE__SUPPRESSED = SEMANTIC_MODEL_BRIDGE__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UMA_SEMANTIC_MODEL_BRIDGE__ORDERING_GUIDE = SEMANTIC_MODEL_BRIDGE__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UMA_SEMANTIC_MODEL_BRIDGE__IS_VISIBLE = SEMANTIC_MODEL_BRIDGE__IS_VISIBLE;

	/**
	 * The feature id for the '<em><b>Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UMA_SEMANTIC_MODEL_BRIDGE__CONTAINER = SEMANTIC_MODEL_BRIDGE__CONTAINER;

	/**
	 * The feature id for the '<em><b>Reference</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UMA_SEMANTIC_MODEL_BRIDGE__REFERENCE = SEMANTIC_MODEL_BRIDGE__REFERENCE;

	/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UMA_SEMANTIC_MODEL_BRIDGE__PROPERTY = SEMANTIC_MODEL_BRIDGE__PROPERTY;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UMA_SEMANTIC_MODEL_BRIDGE__PRESENTATION = SEMANTIC_MODEL_BRIDGE__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Diagram</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UMA_SEMANTIC_MODEL_BRIDGE__DIAGRAM = SEMANTIC_MODEL_BRIDGE__DIAGRAM;

	/**
	 * The feature id for the '<em><b>Graph Element</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UMA_SEMANTIC_MODEL_BRIDGE__GRAPH_ELEMENT = SEMANTIC_MODEL_BRIDGE__GRAPH_ELEMENT;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UMA_SEMANTIC_MODEL_BRIDGE__ELEMENT = SEMANTIC_MODEL_BRIDGE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>UMA Semantic Model Bridge</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UMA_SEMANTIC_MODEL_BRIDGE_FEATURE_COUNT = SEMANTIC_MODEL_BRIDGE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.CoreSemanticModelBridgeImpl <em>Core Semantic Model Bridge</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.CoreSemanticModelBridgeImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getCoreSemanticModelBridge()
	 * @generated
	 */
	int CORE_SEMANTIC_MODEL_BRIDGE = 104;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_SEMANTIC_MODEL_BRIDGE__NAME = SEMANTIC_MODEL_BRIDGE__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_SEMANTIC_MODEL_BRIDGE__GUID = SEMANTIC_MODEL_BRIDGE__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_SEMANTIC_MODEL_BRIDGE__PRESENTATION_NAME = SEMANTIC_MODEL_BRIDGE__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_SEMANTIC_MODEL_BRIDGE__BRIEF_DESCRIPTION = SEMANTIC_MODEL_BRIDGE__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_SEMANTIC_MODEL_BRIDGE__OWNED_RULES = SEMANTIC_MODEL_BRIDGE__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_SEMANTIC_MODEL_BRIDGE__METHOD_ELEMENT_PROPERTY = SEMANTIC_MODEL_BRIDGE__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_SEMANTIC_MODEL_BRIDGE__KIND = SEMANTIC_MODEL_BRIDGE__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_SEMANTIC_MODEL_BRIDGE__SUPPRESSED = SEMANTIC_MODEL_BRIDGE__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_SEMANTIC_MODEL_BRIDGE__ORDERING_GUIDE = SEMANTIC_MODEL_BRIDGE__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_SEMANTIC_MODEL_BRIDGE__IS_VISIBLE = SEMANTIC_MODEL_BRIDGE__IS_VISIBLE;

	/**
	 * The feature id for the '<em><b>Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_SEMANTIC_MODEL_BRIDGE__CONTAINER = SEMANTIC_MODEL_BRIDGE__CONTAINER;

	/**
	 * The feature id for the '<em><b>Reference</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_SEMANTIC_MODEL_BRIDGE__REFERENCE = SEMANTIC_MODEL_BRIDGE__REFERENCE;

	/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_SEMANTIC_MODEL_BRIDGE__PROPERTY = SEMANTIC_MODEL_BRIDGE__PROPERTY;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_SEMANTIC_MODEL_BRIDGE__PRESENTATION = SEMANTIC_MODEL_BRIDGE__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Diagram</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_SEMANTIC_MODEL_BRIDGE__DIAGRAM = SEMANTIC_MODEL_BRIDGE__DIAGRAM;

	/**
	 * The feature id for the '<em><b>Graph Element</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_SEMANTIC_MODEL_BRIDGE__GRAPH_ELEMENT = SEMANTIC_MODEL_BRIDGE__GRAPH_ELEMENT;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_SEMANTIC_MODEL_BRIDGE__ELEMENT = SEMANTIC_MODEL_BRIDGE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Core Semantic Model Bridge</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_SEMANTIC_MODEL_BRIDGE_FEATURE_COUNT = SEMANTIC_MODEL_BRIDGE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.LeafElementImpl <em>Leaf Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.LeafElementImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getLeafElement()
	 * @generated
	 */
	int LEAF_ELEMENT = 105;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEAF_ELEMENT__NAME = DIAGRAM_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEAF_ELEMENT__GUID = DIAGRAM_ELEMENT__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEAF_ELEMENT__PRESENTATION_NAME = DIAGRAM_ELEMENT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEAF_ELEMENT__BRIEF_DESCRIPTION = DIAGRAM_ELEMENT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEAF_ELEMENT__OWNED_RULES = DIAGRAM_ELEMENT__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEAF_ELEMENT__METHOD_ELEMENT_PROPERTY = DIAGRAM_ELEMENT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEAF_ELEMENT__KIND = DIAGRAM_ELEMENT__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEAF_ELEMENT__SUPPRESSED = DIAGRAM_ELEMENT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEAF_ELEMENT__ORDERING_GUIDE = DIAGRAM_ELEMENT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEAF_ELEMENT__IS_VISIBLE = DIAGRAM_ELEMENT__IS_VISIBLE;

	/**
	 * The feature id for the '<em><b>Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEAF_ELEMENT__CONTAINER = DIAGRAM_ELEMENT__CONTAINER;

	/**
	 * The feature id for the '<em><b>Reference</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEAF_ELEMENT__REFERENCE = DIAGRAM_ELEMENT__REFERENCE;

	/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEAF_ELEMENT__PROPERTY = DIAGRAM_ELEMENT__PROPERTY;

	/**
	 * The number of structural features of the '<em>Leaf Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEAF_ELEMENT_FEATURE_COUNT = DIAGRAM_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.TextElementImpl <em>Text Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.TextElementImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getTextElement()
	 * @generated
	 */
	int TEXT_ELEMENT = 106;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_ELEMENT__NAME = LEAF_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_ELEMENT__GUID = LEAF_ELEMENT__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_ELEMENT__PRESENTATION_NAME = LEAF_ELEMENT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_ELEMENT__BRIEF_DESCRIPTION = LEAF_ELEMENT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_ELEMENT__OWNED_RULES = LEAF_ELEMENT__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_ELEMENT__METHOD_ELEMENT_PROPERTY = LEAF_ELEMENT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_ELEMENT__KIND = LEAF_ELEMENT__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_ELEMENT__SUPPRESSED = LEAF_ELEMENT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_ELEMENT__ORDERING_GUIDE = LEAF_ELEMENT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_ELEMENT__IS_VISIBLE = LEAF_ELEMENT__IS_VISIBLE;

	/**
	 * The feature id for the '<em><b>Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_ELEMENT__CONTAINER = LEAF_ELEMENT__CONTAINER;

	/**
	 * The feature id for the '<em><b>Reference</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_ELEMENT__REFERENCE = LEAF_ELEMENT__REFERENCE;

	/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_ELEMENT__PROPERTY = LEAF_ELEMENT__PROPERTY;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_ELEMENT__TEXT = LEAF_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Text Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_ELEMENT_FEATURE_COUNT = LEAF_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.ImageImpl <em>Image</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.ImageImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getImage()
	 * @generated
	 */
	int IMAGE = 107;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__NAME = LEAF_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__GUID = LEAF_ELEMENT__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__PRESENTATION_NAME = LEAF_ELEMENT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__BRIEF_DESCRIPTION = LEAF_ELEMENT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__OWNED_RULES = LEAF_ELEMENT__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__METHOD_ELEMENT_PROPERTY = LEAF_ELEMENT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__KIND = LEAF_ELEMENT__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__SUPPRESSED = LEAF_ELEMENT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__ORDERING_GUIDE = LEAF_ELEMENT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__IS_VISIBLE = LEAF_ELEMENT__IS_VISIBLE;

	/**
	 * The feature id for the '<em><b>Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__CONTAINER = LEAF_ELEMENT__CONTAINER;

	/**
	 * The feature id for the '<em><b>Reference</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__REFERENCE = LEAF_ELEMENT__REFERENCE;

	/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__PROPERTY = LEAF_ELEMENT__PROPERTY;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__URI = LEAF_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Mime Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__MIME_TYPE = LEAF_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Image</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE_FEATURE_COUNT = LEAF_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.GraphicPrimitiveImpl <em>Graphic Primitive</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.GraphicPrimitiveImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getGraphicPrimitive()
	 * @generated
	 */
	int GRAPHIC_PRIMITIVE = 108;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHIC_PRIMITIVE__NAME = LEAF_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHIC_PRIMITIVE__GUID = LEAF_ELEMENT__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHIC_PRIMITIVE__PRESENTATION_NAME = LEAF_ELEMENT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHIC_PRIMITIVE__BRIEF_DESCRIPTION = LEAF_ELEMENT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHIC_PRIMITIVE__OWNED_RULES = LEAF_ELEMENT__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHIC_PRIMITIVE__METHOD_ELEMENT_PROPERTY = LEAF_ELEMENT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHIC_PRIMITIVE__KIND = LEAF_ELEMENT__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHIC_PRIMITIVE__SUPPRESSED = LEAF_ELEMENT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHIC_PRIMITIVE__ORDERING_GUIDE = LEAF_ELEMENT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHIC_PRIMITIVE__IS_VISIBLE = LEAF_ELEMENT__IS_VISIBLE;

	/**
	 * The feature id for the '<em><b>Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHIC_PRIMITIVE__CONTAINER = LEAF_ELEMENT__CONTAINER;

	/**
	 * The feature id for the '<em><b>Reference</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHIC_PRIMITIVE__REFERENCE = LEAF_ELEMENT__REFERENCE;

	/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHIC_PRIMITIVE__PROPERTY = LEAF_ELEMENT__PROPERTY;

	/**
	 * The number of structural features of the '<em>Graphic Primitive</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHIC_PRIMITIVE_FEATURE_COUNT = LEAF_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.PolylineImpl <em>Polyline</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.PolylineImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getPolyline()
	 * @generated
	 */
	int POLYLINE = 109;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYLINE__NAME = GRAPHIC_PRIMITIVE__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYLINE__GUID = GRAPHIC_PRIMITIVE__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYLINE__PRESENTATION_NAME = GRAPHIC_PRIMITIVE__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYLINE__BRIEF_DESCRIPTION = GRAPHIC_PRIMITIVE__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYLINE__OWNED_RULES = GRAPHIC_PRIMITIVE__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYLINE__METHOD_ELEMENT_PROPERTY = GRAPHIC_PRIMITIVE__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYLINE__KIND = GRAPHIC_PRIMITIVE__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYLINE__SUPPRESSED = GRAPHIC_PRIMITIVE__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYLINE__ORDERING_GUIDE = GRAPHIC_PRIMITIVE__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYLINE__IS_VISIBLE = GRAPHIC_PRIMITIVE__IS_VISIBLE;

	/**
	 * The feature id for the '<em><b>Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYLINE__CONTAINER = GRAPHIC_PRIMITIVE__CONTAINER;

	/**
	 * The feature id for the '<em><b>Reference</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYLINE__REFERENCE = GRAPHIC_PRIMITIVE__REFERENCE;

	/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYLINE__PROPERTY = GRAPHIC_PRIMITIVE__PROPERTY;

	/**
	 * The feature id for the '<em><b>Closed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYLINE__CLOSED = GRAPHIC_PRIMITIVE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Waypoints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYLINE__WAYPOINTS = GRAPHIC_PRIMITIVE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Polyline</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYLINE_FEATURE_COUNT = GRAPHIC_PRIMITIVE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.impl.EllipseImpl <em>Ellipse</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.EllipseImpl
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getEllipse()
	 * @generated
	 */
	int ELLIPSE = 110;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELLIPSE__NAME = GRAPHIC_PRIMITIVE__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELLIPSE__GUID = GRAPHIC_PRIMITIVE__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELLIPSE__PRESENTATION_NAME = GRAPHIC_PRIMITIVE__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELLIPSE__BRIEF_DESCRIPTION = GRAPHIC_PRIMITIVE__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELLIPSE__OWNED_RULES = GRAPHIC_PRIMITIVE__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELLIPSE__METHOD_ELEMENT_PROPERTY = GRAPHIC_PRIMITIVE__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELLIPSE__KIND = GRAPHIC_PRIMITIVE__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELLIPSE__SUPPRESSED = GRAPHIC_PRIMITIVE__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELLIPSE__ORDERING_GUIDE = GRAPHIC_PRIMITIVE__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Is Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELLIPSE__IS_VISIBLE = GRAPHIC_PRIMITIVE__IS_VISIBLE;

	/**
	 * The feature id for the '<em><b>Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELLIPSE__CONTAINER = GRAPHIC_PRIMITIVE__CONTAINER;

	/**
	 * The feature id for the '<em><b>Reference</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELLIPSE__REFERENCE = GRAPHIC_PRIMITIVE__REFERENCE;

	/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELLIPSE__PROPERTY = GRAPHIC_PRIMITIVE__PROPERTY;

	/**
	 * The feature id for the '<em><b>Center</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELLIPSE__CENTER = GRAPHIC_PRIMITIVE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Radius X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELLIPSE__RADIUS_X = GRAPHIC_PRIMITIVE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Radius Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELLIPSE__RADIUS_Y = GRAPHIC_PRIMITIVE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Rotation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELLIPSE__ROTATION = GRAPHIC_PRIMITIVE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Start Angle</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELLIPSE__START_ANGLE = GRAPHIC_PRIMITIVE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>End Angle</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELLIPSE__END_ANGLE = GRAPHIC_PRIMITIVE_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Ellipse</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELLIPSE_FEATURE_COUNT = GRAPHIC_PRIMITIVE_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_FAMILY__NAME = METHOD_CONFIGURATION__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_FAMILY__GUID = METHOD_CONFIGURATION__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_FAMILY__PRESENTATION_NAME = METHOD_CONFIGURATION__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_FAMILY__BRIEF_DESCRIPTION = METHOD_CONFIGURATION__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_FAMILY__OWNED_RULES = METHOD_CONFIGURATION__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_FAMILY__METHOD_ELEMENT_PROPERTY = METHOD_CONFIGURATION__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_FAMILY__KIND = METHOD_CONFIGURATION__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_FAMILY__SUPPRESSED = METHOD_CONFIGURATION__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_FAMILY__ORDERING_GUIDE = METHOD_CONFIGURATION__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Authors</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_FAMILY__AUTHORS = METHOD_CONFIGURATION__AUTHORS;

	/**
	 * The feature id for the '<em><b>Change Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_FAMILY__CHANGE_DATE = METHOD_CONFIGURATION__CHANGE_DATE;

	/**
	 * The feature id for the '<em><b>Change Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_FAMILY__CHANGE_DESCRIPTION = METHOD_CONFIGURATION__CHANGE_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_FAMILY__VERSION = METHOD_CONFIGURATION__VERSION;

	/**
	 * The feature id for the '<em><b>Copyright Statement</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_FAMILY__COPYRIGHT_STATEMENT = METHOD_CONFIGURATION__COPYRIGHT_STATEMENT;

	/**
	 * The feature id for the '<em><b>Method Plugin Selection</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_FAMILY__METHOD_PLUGIN_SELECTION = METHOD_CONFIGURATION__METHOD_PLUGIN_SELECTION;

	/**
	 * The feature id for the '<em><b>Method Package Selection</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_FAMILY__METHOD_PACKAGE_SELECTION = METHOD_CONFIGURATION__METHOD_PACKAGE_SELECTION;

	/**
	 * The feature id for the '<em><b>Process Views</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_FAMILY__PROCESS_VIEWS = METHOD_CONFIGURATION__PROCESS_VIEWS;

	/**
	 * The feature id for the '<em><b>Default View</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_FAMILY__DEFAULT_VIEW = METHOD_CONFIGURATION__DEFAULT_VIEW;

	/**
	 * The feature id for the '<em><b>Base Configurations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_FAMILY__BASE_CONFIGURATIONS = METHOD_CONFIGURATION__BASE_CONFIGURATIONS;

	/**
	 * The feature id for the '<em><b>Subtracted Category</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_FAMILY__SUBTRACTED_CATEGORY = METHOD_CONFIGURATION__SUBTRACTED_CATEGORY;

	/**
	 * The feature id for the '<em><b>Added Category</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_FAMILY__ADDED_CATEGORY = METHOD_CONFIGURATION__ADDED_CATEGORY;

	/**
	 * The feature id for the '<em><b>Delivery Processes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_FAMILY__DELIVERY_PROCESSES = METHOD_CONFIGURATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Process Family</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_FAMILY_FEATURE_COUNT = METHOD_CONFIGURATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_LIBRARY__NAME = METHOD_UNIT__NAME;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_LIBRARY__GUID = METHOD_UNIT__GUID;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_LIBRARY__PRESENTATION_NAME = METHOD_UNIT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_LIBRARY__BRIEF_DESCRIPTION = METHOD_UNIT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Owned Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_LIBRARY__OWNED_RULES = METHOD_UNIT__OWNED_RULES;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_LIBRARY__METHOD_ELEMENT_PROPERTY = METHOD_UNIT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_LIBRARY__KIND = METHOD_UNIT__KIND;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_LIBRARY__SUPPRESSED = METHOD_UNIT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_LIBRARY__ORDERING_GUIDE = METHOD_UNIT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Authors</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_LIBRARY__AUTHORS = METHOD_UNIT__AUTHORS;

	/**
	 * The feature id for the '<em><b>Change Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_LIBRARY__CHANGE_DATE = METHOD_UNIT__CHANGE_DATE;

	/**
	 * The feature id for the '<em><b>Change Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_LIBRARY__CHANGE_DESCRIPTION = METHOD_UNIT__CHANGE_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_LIBRARY__VERSION = METHOD_UNIT__VERSION;

	/**
	 * The feature id for the '<em><b>Copyright Statement</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_LIBRARY__COPYRIGHT_STATEMENT = METHOD_UNIT__COPYRIGHT_STATEMENT;

	/**
	 * The feature id for the '<em><b>Method Plugins</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_LIBRARY__METHOD_PLUGINS = METHOD_UNIT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Predefined Configurations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_LIBRARY__PREDEFINED_CONFIGURATIONS = METHOD_UNIT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Method Library</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_LIBRARY_FEATURE_COUNT = METHOD_UNIT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.WorkOrderType <em>Work Order Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.WorkOrderType
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getWorkOrderType()
	 * @generated
	 */
	int WORK_ORDER_TYPE = 114;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.uma.VariabilityType <em>Variability Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.VariabilityType
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getVariabilityType()
	 * @generated
	 */
	int VARIABILITY_TYPE = 113;

	/**
	 * The meta object id for the '<em>Date</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.util.Date
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getDate()
	 * @generated
	 */
	int DATE = 117;

	/**
	 * The meta object id for the '<em>Uri</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.net.URI
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getUri()
	 * @generated
	 */
	int URI = 118;

	/**
	 * The meta object id for the '<em>String</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.String
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getString()
	 * @generated
	 */
	int STRING = 115;

	/**
	 * The meta object id for the '<em>Boolean</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.Boolean
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getBoolean()
	 * @generated
	 */
	int BOOLEAN = 116;

	/**
	 * The meta object id for the '<em>Set</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.util.Set
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getSet()
	 * @generated
	 */
	int SET = 119;

	/**
	 * The meta object id for the '<em>Sequence</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.util.List
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getSequence()
	 * @generated
	 */
	int SEQUENCE = 120;

	/**
	 * The meta object id for the '<em>Integer</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getInteger()
	 * @generated
	 */
	int INTEGER = 121;

	/**
	 * The meta object id for the '<em>Double</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.Double
	 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getDouble()
	 * @generated
	 */
	int DOUBLE = 122;

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.Classifier <em>Classifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Classifier</em>'.
	 * @see org.eclipse.epf.uma.Classifier
	 * @generated
	 */
	EClass getClassifier();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.Classifier#getIsAbstract <em>Is Abstract</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Abstract</em>'.
	 * @see org.eclipse.epf.uma.Classifier#getIsAbstract()
	 * @see #getClassifier()
	 * @generated
	 */
	EAttribute getClassifier_IsAbstract();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.Type <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Type</em>'.
	 * @see org.eclipse.epf.uma.Type
	 * @generated
	 */
	EClass getType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.Element <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Element</em>'.
	 * @see org.eclipse.epf.uma.Element
	 * @generated
	 */
	EClass getElement();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.NamedElement <em>Named Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Named Element</em>'.
	 * @see org.eclipse.epf.uma.NamedElement
	 * @generated
	 */
	EClass getNamedElement();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.NamedElement#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.epf.uma.NamedElement#getName()
	 * @see #getNamedElement()
	 * @generated
	 */
	EAttribute getNamedElement_Name();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.PackageableElement <em>Packageable Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Packageable Element</em>'.
	 * @see org.eclipse.epf.uma.PackageableElement
	 * @generated
	 */
	EClass getPackageableElement();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.Package <em>Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Package</em>'.
	 * @see org.eclipse.epf.uma.Package
	 * @generated
	 */
	EClass getPackage();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.Namespace <em>Namespace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Namespace</em>'.
	 * @see org.eclipse.epf.uma.Namespace
	 * @generated
	 */
	EClass getNamespace();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.MethodElement <em>Method Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Method Element</em>'.
	 * @see org.eclipse.epf.uma.MethodElement
	 * @generated
	 */
	EClass getMethodElement();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.MethodElement#getGuid <em>Guid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Guid</em>'.
	 * @see org.eclipse.epf.uma.MethodElement#getGuid()
	 * @see #getMethodElement()
	 * @generated
	 */
	EAttribute getMethodElement_Guid();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.MethodElement#getPresentationName <em>Presentation Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Presentation Name</em>'.
	 * @see org.eclipse.epf.uma.MethodElement#getPresentationName()
	 * @see #getMethodElement()
	 * @generated
	 */
	EAttribute getMethodElement_PresentationName();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.MethodElement#getBriefDescription <em>Brief Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Brief Description</em>'.
	 * @see org.eclipse.epf.uma.MethodElement#getBriefDescription()
	 * @see #getMethodElement()
	 * @generated
	 */
	EAttribute getMethodElement_BriefDescription();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.uma.MethodElement#getOwnedRules <em>Owned Rules</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Rules</em>'.
	 * @see org.eclipse.epf.uma.MethodElement#getOwnedRules()
	 * @see #getMethodElement()
	 * @generated
	 */
	EReference getMethodElement_OwnedRules();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.uma.MethodElement#getMethodElementProperty <em>Method Element Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Method Element Property</em>'.
	 * @see org.eclipse.epf.uma.MethodElement#getMethodElementProperty()
	 * @see #getMethodElement()
	 * @generated
	 */
	EReference getMethodElement_MethodElementProperty();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.MethodElement#getKind <em>Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Kind</em>'.
	 * @see org.eclipse.epf.uma.MethodElement#getKind()
	 * @see #getMethodElement()
	 * @generated
	 */
	EReference getMethodElement_Kind();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.MethodElement#getSuppressed <em>Suppressed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Suppressed</em>'.
	 * @see org.eclipse.epf.uma.MethodElement#getSuppressed()
	 * @see #getMethodElement()
	 * @generated
	 */
	EAttribute getMethodElement_Suppressed();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.MethodElement#getOrderingGuide <em>Ordering Guide</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ordering Guide</em>'.
	 * @see org.eclipse.epf.uma.MethodElement#getOrderingGuide()
	 * @see #getMethodElement()
	 * @generated
	 */
	EAttribute getMethodElement_OrderingGuide();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.Constraint <em>Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Constraint</em>'.
	 * @see org.eclipse.epf.uma.Constraint
	 * @generated
	 */
	EClass getConstraint();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.Constraint#getBody <em>Body</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Body</em>'.
	 * @see org.eclipse.epf.uma.Constraint#getBody()
	 * @see #getConstraint()
	 * @generated
	 */
	EAttribute getConstraint_Body();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.MethodElementProperty <em>Method Element Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Method Element Property</em>'.
	 * @see org.eclipse.epf.uma.MethodElementProperty
	 * @generated
	 */
	EClass getMethodElementProperty();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.MethodElementProperty#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.epf.uma.MethodElementProperty#getValue()
	 * @see #getMethodElementProperty()
	 * @generated
	 */
	EAttribute getMethodElementProperty_Value();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.Kind <em>Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Kind</em>'.
	 * @see org.eclipse.epf.uma.Kind
	 * @generated
	 */
	EClass getKind();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.uma.Kind#getApplicableMetaClassInfo <em>Applicable Meta Class Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Applicable Meta Class Info</em>'.
	 * @see org.eclipse.epf.uma.Kind#getApplicableMetaClassInfo()
	 * @see #getKind()
	 * @generated
	 */
	EReference getKind_ApplicableMetaClassInfo();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.ContentElement <em>Content Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Content Element</em>'.
	 * @see org.eclipse.epf.uma.ContentElement
	 * @generated
	 */
	EClass getContentElement();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.ContentElement#getSupportingMaterials <em>Supporting Materials</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Supporting Materials</em>'.
	 * @see org.eclipse.epf.uma.ContentElement#getSupportingMaterials()
	 * @see #getContentElement()
	 * @generated
	 */
	EReference getContentElement_SupportingMaterials();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.ContentElement#getConceptsAndPapers <em>Concepts And Papers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Concepts And Papers</em>'.
	 * @see org.eclipse.epf.uma.ContentElement#getConceptsAndPapers()
	 * @see #getContentElement()
	 * @generated
	 */
	EReference getContentElement_ConceptsAndPapers();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.ContentElement#getChecklists <em>Checklists</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Checklists</em>'.
	 * @see org.eclipse.epf.uma.ContentElement#getChecklists()
	 * @see #getContentElement()
	 * @generated
	 */
	EReference getContentElement_Checklists();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.ContentElement#getGuidelines <em>Guidelines</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Guidelines</em>'.
	 * @see org.eclipse.epf.uma.ContentElement#getGuidelines()
	 * @see #getContentElement()
	 * @generated
	 */
	EReference getContentElement_Guidelines();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.ContentElement#getExamples <em>Examples</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Examples</em>'.
	 * @see org.eclipse.epf.uma.ContentElement#getExamples()
	 * @see #getContentElement()
	 * @generated
	 */
	EReference getContentElement_Examples();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.ContentElement#getAssets <em>Assets</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Assets</em>'.
	 * @see org.eclipse.epf.uma.ContentElement#getAssets()
	 * @see #getContentElement()
	 * @generated
	 */
	EReference getContentElement_Assets();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.ContentElement#getTermdefinition <em>Termdefinition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Termdefinition</em>'.
	 * @see org.eclipse.epf.uma.ContentElement#getTermdefinition()
	 * @see #getContentElement()
	 * @generated
	 */
	EReference getContentElement_Termdefinition();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.DescribableElement <em>Describable Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Describable Element</em>'.
	 * @see org.eclipse.epf.uma.DescribableElement
	 * @generated
	 */
	EClass getDescribableElement();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.epf.uma.DescribableElement#getPresentation <em>Presentation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Presentation</em>'.
	 * @see org.eclipse.epf.uma.DescribableElement#getPresentation()
	 * @see #getDescribableElement()
	 * @generated
	 */
	EReference getDescribableElement_Presentation();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.DescribableElement#getShapeicon <em>Shapeicon</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Shapeicon</em>'.
	 * @see org.eclipse.epf.uma.DescribableElement#getShapeicon()
	 * @see #getDescribableElement()
	 * @generated
	 */
	EAttribute getDescribableElement_Shapeicon();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.DescribableElement#getNodeicon <em>Nodeicon</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Nodeicon</em>'.
	 * @see org.eclipse.epf.uma.DescribableElement#getNodeicon()
	 * @see #getDescribableElement()
	 * @generated
	 */
	EAttribute getDescribableElement_Nodeicon();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.ContentDescription <em>Content Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Content Description</em>'.
	 * @see org.eclipse.epf.uma.ContentDescription
	 * @generated
	 */
	EClass getContentDescription();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.ContentDescription#getMainDescription <em>Main Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Main Description</em>'.
	 * @see org.eclipse.epf.uma.ContentDescription#getMainDescription()
	 * @see #getContentDescription()
	 * @generated
	 */
	EAttribute getContentDescription_MainDescription();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.uma.ContentDescription#getSections <em>Sections</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Sections</em>'.
	 * @see org.eclipse.epf.uma.ContentDescription#getSections()
	 * @see #getContentDescription()
	 * @generated
	 */
	EReference getContentDescription_Sections();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.ContentDescription#getExternalId <em>External Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>External Id</em>'.
	 * @see org.eclipse.epf.uma.ContentDescription#getExternalId()
	 * @see #getContentDescription()
	 * @generated
	 */
	EAttribute getContentDescription_ExternalId();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.ContentDescription#getKeyConsiderations <em>Key Considerations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key Considerations</em>'.
	 * @see org.eclipse.epf.uma.ContentDescription#getKeyConsiderations()
	 * @see #getContentDescription()
	 * @generated
	 */
	EAttribute getContentDescription_KeyConsiderations();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.ContentDescription#getLongPresentationName <em>Long Presentation Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Long Presentation Name</em>'.
	 * @see org.eclipse.epf.uma.ContentDescription#getLongPresentationName()
	 * @see #getContentDescription()
	 * @generated
	 */
	EAttribute getContentDescription_LongPresentationName();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.Section <em>Section</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Section</em>'.
	 * @see org.eclipse.epf.uma.Section
	 * @generated
	 */
	EClass getSection();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.Section#getSectionName <em>Section Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Section Name</em>'.
	 * @see org.eclipse.epf.uma.Section#getSectionName()
	 * @see #getSection()
	 * @generated
	 */
	EAttribute getSection_SectionName();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.Section#getSectionDescription <em>Section Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Section Description</em>'.
	 * @see org.eclipse.epf.uma.Section#getSectionDescription()
	 * @see #getSection()
	 * @generated
	 */
	EAttribute getSection_SectionDescription();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.uma.Section#getSubSections <em>Sub Sections</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Sub Sections</em>'.
	 * @see org.eclipse.epf.uma.Section#getSubSections()
	 * @see #getSection()
	 * @generated
	 */
	EReference getSection_SubSections();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.epf.uma.Section#getPredecessor <em>Predecessor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Predecessor</em>'.
	 * @see org.eclipse.epf.uma.Section#getPredecessor()
	 * @see #getSection()
	 * @generated
	 */
	EReference getSection_Predecessor();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.Role <em>Role</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Role</em>'.
	 * @see org.eclipse.epf.uma.Role
	 * @generated
	 */
	EClass getRole();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.Role#getModifies <em>Modifies</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Modifies</em>'.
	 * @see org.eclipse.epf.uma.Role#getModifies()
	 * @see #getRole()
	 * @generated
	 */
	EReference getRole_Modifies();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.Role#getResponsibleFor <em>Responsible For</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Responsible For</em>'.
	 * @see org.eclipse.epf.uma.Role#getResponsibleFor()
	 * @see #getRole()
	 * @generated
	 */
	EReference getRole_ResponsibleFor();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.WorkProduct <em>Work Product</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Work Product</em>'.
	 * @see org.eclipse.epf.uma.WorkProduct
	 * @generated
	 */
	EClass getWorkProduct();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.WorkProduct#getReports <em>Reports</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Reports</em>'.
	 * @see org.eclipse.epf.uma.WorkProduct#getReports()
	 * @see #getWorkProduct()
	 * @generated
	 */
	EReference getWorkProduct_Reports();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.WorkProduct#getTemplates <em>Templates</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Templates</em>'.
	 * @see org.eclipse.epf.uma.WorkProduct#getTemplates()
	 * @see #getWorkProduct()
	 * @generated
	 */
	EReference getWorkProduct_Templates();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.WorkProduct#getToolMentors <em>Tool Mentors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Tool Mentors</em>'.
	 * @see org.eclipse.epf.uma.WorkProduct#getToolMentors()
	 * @see #getWorkProduct()
	 * @generated
	 */
	EReference getWorkProduct_ToolMentors();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.WorkProduct#getEstimationConsiderations <em>Estimation Considerations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Estimation Considerations</em>'.
	 * @see org.eclipse.epf.uma.WorkProduct#getEstimationConsiderations()
	 * @see #getWorkProduct()
	 * @generated
	 */
	EReference getWorkProduct_EstimationConsiderations();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.FulfillableElement <em>Fulfillable Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Fulfillable Element</em>'.
	 * @see org.eclipse.epf.uma.FulfillableElement
	 * @generated
	 */
	EClass getFulfillableElement();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.FulfillableElement#getFulfills <em>Fulfills</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Fulfills</em>'.
	 * @see org.eclipse.epf.uma.FulfillableElement#getFulfills()
	 * @see #getFulfillableElement()
	 * @generated
	 */
	EReference getFulfillableElement_Fulfills();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.Task <em>Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Task</em>'.
	 * @see org.eclipse.epf.uma.Task
	 * @generated
	 */
	EClass getTask();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.Task#getPerformedBy <em>Performed By</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Performed By</em>'.
	 * @see org.eclipse.epf.uma.Task#getPerformedBy()
	 * @see #getTask()
	 * @generated
	 */
	EReference getTask_PerformedBy();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.Task#getMandatoryInput <em>Mandatory Input</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Mandatory Input</em>'.
	 * @see org.eclipse.epf.uma.Task#getMandatoryInput()
	 * @see #getTask()
	 * @generated
	 */
	EReference getTask_MandatoryInput();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.Task#getOutput <em>Output</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Output</em>'.
	 * @see org.eclipse.epf.uma.Task#getOutput()
	 * @see #getTask()
	 * @generated
	 */
	EReference getTask_Output();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.Task#getAdditionallyPerformedBy <em>Additionally Performed By</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Additionally Performed By</em>'.
	 * @see org.eclipse.epf.uma.Task#getAdditionallyPerformedBy()
	 * @see #getTask()
	 * @generated
	 */
	EReference getTask_AdditionallyPerformedBy();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.Task#getOptionalInput <em>Optional Input</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Optional Input</em>'.
	 * @see org.eclipse.epf.uma.Task#getOptionalInput()
	 * @see #getTask()
	 * @generated
	 */
	EReference getTask_OptionalInput();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.Task#getSteps <em>Steps</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Steps</em>'.
	 * @see org.eclipse.epf.uma.Task#getSteps()
	 * @see #getTask()
	 * @generated
	 */
	EReference getTask_Steps();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.Task#getToolMentors <em>Tool Mentors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Tool Mentors</em>'.
	 * @see org.eclipse.epf.uma.Task#getToolMentors()
	 * @see #getTask()
	 * @generated
	 */
	EReference getTask_ToolMentors();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.Task#getEstimationConsiderations <em>Estimation Considerations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Estimation Considerations</em>'.
	 * @see org.eclipse.epf.uma.Task#getEstimationConsiderations()
	 * @see #getTask()
	 * @generated
	 */
	EReference getTask_EstimationConsiderations();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.WorkDefinition <em>Work Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Work Definition</em>'.
	 * @see org.eclipse.epf.uma.WorkDefinition
	 * @generated
	 */
	EClass getWorkDefinition();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.epf.uma.WorkDefinition#getPrecondition <em>Precondition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Precondition</em>'.
	 * @see org.eclipse.epf.uma.WorkDefinition#getPrecondition()
	 * @see #getWorkDefinition()
	 * @generated
	 */
	EReference getWorkDefinition_Precondition();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.epf.uma.WorkDefinition#getPostcondition <em>Postcondition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Postcondition</em>'.
	 * @see org.eclipse.epf.uma.WorkDefinition#getPostcondition()
	 * @see #getWorkDefinition()
	 * @generated
	 */
	EReference getWorkDefinition_Postcondition();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.Step <em>Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Step</em>'.
	 * @see org.eclipse.epf.uma.Step
	 * @generated
	 */
	EClass getStep();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.Guidance <em>Guidance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Guidance</em>'.
	 * @see org.eclipse.epf.uma.Guidance
	 * @generated
	 */
	EClass getGuidance();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.Artifact <em>Artifact</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Artifact</em>'.
	 * @see org.eclipse.epf.uma.Artifact
	 * @generated
	 */
	EClass getArtifact();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.epf.uma.Artifact#getContainerArtifact <em>Container Artifact</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Container Artifact</em>'.
	 * @see org.eclipse.epf.uma.Artifact#getContainerArtifact()
	 * @see #getArtifact()
	 * @generated
	 */
	EReference getArtifact_ContainerArtifact();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.uma.Artifact#getContainedArtifacts <em>Contained Artifacts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Contained Artifacts</em>'.
	 * @see org.eclipse.epf.uma.Artifact#getContainedArtifacts()
	 * @see #getArtifact()
	 * @generated
	 */
	EReference getArtifact_ContainedArtifacts();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.Deliverable <em>Deliverable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Deliverable</em>'.
	 * @see org.eclipse.epf.uma.Deliverable
	 * @generated
	 */
	EClass getDeliverable();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.Deliverable#getDeliveredWorkProducts <em>Delivered Work Products</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Delivered Work Products</em>'.
	 * @see org.eclipse.epf.uma.Deliverable#getDeliveredWorkProducts()
	 * @see #getDeliverable()
	 * @generated
	 */
	EReference getDeliverable_DeliveredWorkProducts();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.Outcome <em>Outcome</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Outcome</em>'.
	 * @see org.eclipse.epf.uma.Outcome
	 * @generated
	 */
	EClass getOutcome();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.MethodPackage <em>Method Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Method Package</em>'.
	 * @see org.eclipse.epf.uma.MethodPackage
	 * @generated
	 */
	EClass getMethodPackage();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.MethodPackage#getGlobal <em>Global</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Global</em>'.
	 * @see org.eclipse.epf.uma.MethodPackage#getGlobal()
	 * @see #getMethodPackage()
	 * @generated
	 */
	EAttribute getMethodPackage_Global();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.MethodPackage#getReusedPackages <em>Reused Packages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Reused Packages</em>'.
	 * @see org.eclipse.epf.uma.MethodPackage#getReusedPackages()
	 * @see #getMethodPackage()
	 * @generated
	 */
	EReference getMethodPackage_ReusedPackages();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.uma.MethodPackage#getChildPackages <em>Child Packages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Child Packages</em>'.
	 * @see org.eclipse.epf.uma.MethodPackage#getChildPackages()
	 * @see #getMethodPackage()
	 * @generated
	 */
	EReference getMethodPackage_ChildPackages();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.ContentPackage <em>Content Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Content Package</em>'.
	 * @see org.eclipse.epf.uma.ContentPackage
	 * @generated
	 */
	EClass getContentPackage();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.uma.ContentPackage#getContentElements <em>Content Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Content Elements</em>'.
	 * @see org.eclipse.epf.uma.ContentPackage#getContentElements()
	 * @see #getContentPackage()
	 * @generated
	 */
	EReference getContentPackage_ContentElements();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.ArtifactDescription <em>Artifact Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Artifact Description</em>'.
	 * @see org.eclipse.epf.uma.ArtifactDescription
	 * @generated
	 */
	EClass getArtifactDescription();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.ArtifactDescription#getBriefOutline <em>Brief Outline</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Brief Outline</em>'.
	 * @see org.eclipse.epf.uma.ArtifactDescription#getBriefOutline()
	 * @see #getArtifactDescription()
	 * @generated
	 */
	EAttribute getArtifactDescription_BriefOutline();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.ArtifactDescription#getRepresentationOptions <em>Representation Options</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Representation Options</em>'.
	 * @see org.eclipse.epf.uma.ArtifactDescription#getRepresentationOptions()
	 * @see #getArtifactDescription()
	 * @generated
	 */
	EAttribute getArtifactDescription_RepresentationOptions();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.ArtifactDescription#getRepresentation <em>Representation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Representation</em>'.
	 * @see org.eclipse.epf.uma.ArtifactDescription#getRepresentation()
	 * @see #getArtifactDescription()
	 * @generated
	 */
	EAttribute getArtifactDescription_Representation();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.ArtifactDescription#getNotation <em>Notation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Notation</em>'.
	 * @see org.eclipse.epf.uma.ArtifactDescription#getNotation()
	 * @see #getArtifactDescription()
	 * @generated
	 */
	EAttribute getArtifactDescription_Notation();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.WorkProductDescription <em>Work Product Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Work Product Description</em>'.
	 * @see org.eclipse.epf.uma.WorkProductDescription
	 * @generated
	 */
	EClass getWorkProductDescription();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.WorkProductDescription#getPurpose <em>Purpose</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Purpose</em>'.
	 * @see org.eclipse.epf.uma.WorkProductDescription#getPurpose()
	 * @see #getWorkProductDescription()
	 * @generated
	 */
	EAttribute getWorkProductDescription_Purpose();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.WorkProductDescription#getImpactOfNotHaving <em>Impact Of Not Having</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Impact Of Not Having</em>'.
	 * @see org.eclipse.epf.uma.WorkProductDescription#getImpactOfNotHaving()
	 * @see #getWorkProductDescription()
	 * @generated
	 */
	EAttribute getWorkProductDescription_ImpactOfNotHaving();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.WorkProductDescription#getReasonsForNotNeeding <em>Reasons For Not Needing</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Reasons For Not Needing</em>'.
	 * @see org.eclipse.epf.uma.WorkProductDescription#getReasonsForNotNeeding()
	 * @see #getWorkProductDescription()
	 * @generated
	 */
	EAttribute getWorkProductDescription_ReasonsForNotNeeding();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.DeliverableDescription <em>Deliverable Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Deliverable Description</em>'.
	 * @see org.eclipse.epf.uma.DeliverableDescription
	 * @generated
	 */
	EClass getDeliverableDescription();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.DeliverableDescription#getExternalDescription <em>External Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>External Description</em>'.
	 * @see org.eclipse.epf.uma.DeliverableDescription#getExternalDescription()
	 * @see #getDeliverableDescription()
	 * @generated
	 */
	EAttribute getDeliverableDescription_ExternalDescription();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.DeliverableDescription#getPackagingGuidance <em>Packaging Guidance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Packaging Guidance</em>'.
	 * @see org.eclipse.epf.uma.DeliverableDescription#getPackagingGuidance()
	 * @see #getDeliverableDescription()
	 * @generated
	 */
	EAttribute getDeliverableDescription_PackagingGuidance();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.RoleDescription <em>Role Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Role Description</em>'.
	 * @see org.eclipse.epf.uma.RoleDescription
	 * @generated
	 */
	EClass getRoleDescription();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.RoleDescription#getSkills <em>Skills</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Skills</em>'.
	 * @see org.eclipse.epf.uma.RoleDescription#getSkills()
	 * @see #getRoleDescription()
	 * @generated
	 */
	EAttribute getRoleDescription_Skills();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.RoleDescription#getAssignmentApproaches <em>Assignment Approaches</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Assignment Approaches</em>'.
	 * @see org.eclipse.epf.uma.RoleDescription#getAssignmentApproaches()
	 * @see #getRoleDescription()
	 * @generated
	 */
	EAttribute getRoleDescription_AssignmentApproaches();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.RoleDescription#getSynonyms <em>Synonyms</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Synonyms</em>'.
	 * @see org.eclipse.epf.uma.RoleDescription#getSynonyms()
	 * @see #getRoleDescription()
	 * @generated
	 */
	EAttribute getRoleDescription_Synonyms();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.TaskDescription <em>Task Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Task Description</em>'.
	 * @see org.eclipse.epf.uma.TaskDescription
	 * @generated
	 */
	EClass getTaskDescription();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.TaskDescription#getPurpose <em>Purpose</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Purpose</em>'.
	 * @see org.eclipse.epf.uma.TaskDescription#getPurpose()
	 * @see #getTaskDescription()
	 * @generated
	 */
	EAttribute getTaskDescription_Purpose();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.TaskDescription#getAlternatives <em>Alternatives</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Alternatives</em>'.
	 * @see org.eclipse.epf.uma.TaskDescription#getAlternatives()
	 * @see #getTaskDescription()
	 * @generated
	 */
	EAttribute getTaskDescription_Alternatives();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.GuidanceDescription <em>Guidance Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Guidance Description</em>'.
	 * @see org.eclipse.epf.uma.GuidanceDescription
	 * @generated
	 */
	EClass getGuidanceDescription();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.GuidanceDescription#getAttachments <em>Attachments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attachments</em>'.
	 * @see org.eclipse.epf.uma.GuidanceDescription#getAttachments()
	 * @see #getGuidanceDescription()
	 * @generated
	 */
	EAttribute getGuidanceDescription_Attachments();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.PracticeDescription <em>Practice Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Practice Description</em>'.
	 * @see org.eclipse.epf.uma.PracticeDescription
	 * @generated
	 */
	EClass getPracticeDescription();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.PracticeDescription#getAdditionalInfo <em>Additional Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Additional Info</em>'.
	 * @see org.eclipse.epf.uma.PracticeDescription#getAdditionalInfo()
	 * @see #getPracticeDescription()
	 * @generated
	 */
	EAttribute getPracticeDescription_AdditionalInfo();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.PracticeDescription#getProblem <em>Problem</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Problem</em>'.
	 * @see org.eclipse.epf.uma.PracticeDescription#getProblem()
	 * @see #getPracticeDescription()
	 * @generated
	 */
	EAttribute getPracticeDescription_Problem();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.PracticeDescription#getBackground <em>Background</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Background</em>'.
	 * @see org.eclipse.epf.uma.PracticeDescription#getBackground()
	 * @see #getPracticeDescription()
	 * @generated
	 */
	EAttribute getPracticeDescription_Background();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.PracticeDescription#getGoals <em>Goals</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Goals</em>'.
	 * @see org.eclipse.epf.uma.PracticeDescription#getGoals()
	 * @see #getPracticeDescription()
	 * @generated
	 */
	EAttribute getPracticeDescription_Goals();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.PracticeDescription#getApplication <em>Application</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Application</em>'.
	 * @see org.eclipse.epf.uma.PracticeDescription#getApplication()
	 * @see #getPracticeDescription()
	 * @generated
	 */
	EAttribute getPracticeDescription_Application();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.PracticeDescription#getLevelsOfAdoption <em>Levels Of Adoption</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Levels Of Adoption</em>'.
	 * @see org.eclipse.epf.uma.PracticeDescription#getLevelsOfAdoption()
	 * @see #getPracticeDescription()
	 * @generated
	 */
	EAttribute getPracticeDescription_LevelsOfAdoption();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.Activity <em>Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Activity</em>'.
	 * @see org.eclipse.epf.uma.Activity
	 * @generated
	 */
	EClass getActivity();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.Activity#getBreakdownElements <em>Breakdown Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Breakdown Elements</em>'.
	 * @see org.eclipse.epf.uma.Activity#getBreakdownElements()
	 * @see #getActivity()
	 * @generated
	 */
	EReference getActivity_BreakdownElements();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.Activity#getRoadmaps <em>Roadmaps</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Roadmaps</em>'.
	 * @see org.eclipse.epf.uma.Activity#getRoadmaps()
	 * @see #getActivity()
	 * @generated
	 */
	EReference getActivity_Roadmaps();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.WorkBreakdownElement <em>Work Breakdown Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Work Breakdown Element</em>'.
	 * @see org.eclipse.epf.uma.WorkBreakdownElement
	 * @generated
	 */
	EClass getWorkBreakdownElement();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.WorkBreakdownElement#getIsRepeatable <em>Is Repeatable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Repeatable</em>'.
	 * @see org.eclipse.epf.uma.WorkBreakdownElement#getIsRepeatable()
	 * @see #getWorkBreakdownElement()
	 * @generated
	 */
	EAttribute getWorkBreakdownElement_IsRepeatable();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.WorkBreakdownElement#getIsOngoing <em>Is Ongoing</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Ongoing</em>'.
	 * @see org.eclipse.epf.uma.WorkBreakdownElement#getIsOngoing()
	 * @see #getWorkBreakdownElement()
	 * @generated
	 */
	EAttribute getWorkBreakdownElement_IsOngoing();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.WorkBreakdownElement#getIsEventDriven <em>Is Event Driven</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Event Driven</em>'.
	 * @see org.eclipse.epf.uma.WorkBreakdownElement#getIsEventDriven()
	 * @see #getWorkBreakdownElement()
	 * @generated
	 */
	EAttribute getWorkBreakdownElement_IsEventDriven();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.WorkBreakdownElement#getLinkToPredecessor <em>Link To Predecessor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Link To Predecessor</em>'.
	 * @see org.eclipse.epf.uma.WorkBreakdownElement#getLinkToPredecessor()
	 * @see #getWorkBreakdownElement()
	 * @generated
	 */
	EReference getWorkBreakdownElement_LinkToPredecessor();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.BreakdownElement <em>Breakdown Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Breakdown Element</em>'.
	 * @see org.eclipse.epf.uma.BreakdownElement
	 * @generated
	 */
	EClass getBreakdownElement();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.BreakdownElement#getPrefix <em>Prefix</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Prefix</em>'.
	 * @see org.eclipse.epf.uma.BreakdownElement#getPrefix()
	 * @see #getBreakdownElement()
	 * @generated
	 */
	EAttribute getBreakdownElement_Prefix();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.BreakdownElement#getIsPlanned <em>Is Planned</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Planned</em>'.
	 * @see org.eclipse.epf.uma.BreakdownElement#getIsPlanned()
	 * @see #getBreakdownElement()
	 * @generated
	 */
	EAttribute getBreakdownElement_IsPlanned();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.BreakdownElement#getHasMultipleOccurrences <em>Has Multiple Occurrences</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Has Multiple Occurrences</em>'.
	 * @see org.eclipse.epf.uma.BreakdownElement#getHasMultipleOccurrences()
	 * @see #getBreakdownElement()
	 * @generated
	 */
	EAttribute getBreakdownElement_HasMultipleOccurrences();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.BreakdownElement#getIsOptional <em>Is Optional</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Optional</em>'.
	 * @see org.eclipse.epf.uma.BreakdownElement#getIsOptional()
	 * @see #getBreakdownElement()
	 * @generated
	 */
	EAttribute getBreakdownElement_IsOptional();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.epf.uma.BreakdownElement#getPresentedAfter <em>Presented After</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Presented After</em>'.
	 * @see org.eclipse.epf.uma.BreakdownElement#getPresentedAfter()
	 * @see #getBreakdownElement()
	 * @generated
	 */
	EReference getBreakdownElement_PresentedAfter();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.epf.uma.BreakdownElement#getPresentedBefore <em>Presented Before</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Presented Before</em>'.
	 * @see org.eclipse.epf.uma.BreakdownElement#getPresentedBefore()
	 * @see #getBreakdownElement()
	 * @generated
	 */
	EReference getBreakdownElement_PresentedBefore();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.epf.uma.BreakdownElement#getPlanningData <em>Planning Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Planning Data</em>'.
	 * @see org.eclipse.epf.uma.BreakdownElement#getPlanningData()
	 * @see #getBreakdownElement()
	 * @generated
	 */
	EReference getBreakdownElement_PlanningData();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.epf.uma.BreakdownElement#getSuperActivities <em>Super Activities</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Super Activities</em>'.
	 * @see org.eclipse.epf.uma.BreakdownElement#getSuperActivities()
	 * @see #getBreakdownElement()
	 * @generated
	 */
	EReference getBreakdownElement_SuperActivities();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.BreakdownElement#getChecklists <em>Checklists</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Checklists</em>'.
	 * @see org.eclipse.epf.uma.BreakdownElement#getChecklists()
	 * @see #getBreakdownElement()
	 * @generated
	 */
	EReference getBreakdownElement_Checklists();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.BreakdownElement#getConcepts <em>Concepts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Concepts</em>'.
	 * @see org.eclipse.epf.uma.BreakdownElement#getConcepts()
	 * @see #getBreakdownElement()
	 * @generated
	 */
	EReference getBreakdownElement_Concepts();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.BreakdownElement#getExamples <em>Examples</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Examples</em>'.
	 * @see org.eclipse.epf.uma.BreakdownElement#getExamples()
	 * @see #getBreakdownElement()
	 * @generated
	 */
	EReference getBreakdownElement_Examples();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.BreakdownElement#getGuidelines <em>Guidelines</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Guidelines</em>'.
	 * @see org.eclipse.epf.uma.BreakdownElement#getGuidelines()
	 * @see #getBreakdownElement()
	 * @generated
	 */
	EReference getBreakdownElement_Guidelines();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.BreakdownElement#getReusableAssets <em>Reusable Assets</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Reusable Assets</em>'.
	 * @see org.eclipse.epf.uma.BreakdownElement#getReusableAssets()
	 * @see #getBreakdownElement()
	 * @generated
	 */
	EReference getBreakdownElement_ReusableAssets();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.BreakdownElement#getSupportingMaterials <em>Supporting Materials</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Supporting Materials</em>'.
	 * @see org.eclipse.epf.uma.BreakdownElement#getSupportingMaterials()
	 * @see #getBreakdownElement()
	 * @generated
	 */
	EReference getBreakdownElement_SupportingMaterials();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.BreakdownElement#getTemplates <em>Templates</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Templates</em>'.
	 * @see org.eclipse.epf.uma.BreakdownElement#getTemplates()
	 * @see #getBreakdownElement()
	 * @generated
	 */
	EReference getBreakdownElement_Templates();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.BreakdownElement#getReports <em>Reports</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Reports</em>'.
	 * @see org.eclipse.epf.uma.BreakdownElement#getReports()
	 * @see #getBreakdownElement()
	 * @generated
	 */
	EReference getBreakdownElement_Reports();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.BreakdownElement#getEstimationconsiderations <em>Estimationconsiderations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Estimationconsiderations</em>'.
	 * @see org.eclipse.epf.uma.BreakdownElement#getEstimationconsiderations()
	 * @see #getBreakdownElement()
	 * @generated
	 */
	EReference getBreakdownElement_Estimationconsiderations();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.BreakdownElement#getToolmentor <em>Toolmentor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Toolmentor</em>'.
	 * @see org.eclipse.epf.uma.BreakdownElement#getToolmentor()
	 * @see #getBreakdownElement()
	 * @generated
	 */
	EReference getBreakdownElement_Toolmentor();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.Milestone <em>Milestone</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Milestone</em>'.
	 * @see org.eclipse.epf.uma.Milestone
	 * @generated
	 */
	EClass getMilestone();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.Milestone#getRequiredResults <em>Required Results</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Required Results</em>'.
	 * @see org.eclipse.epf.uma.Milestone#getRequiredResults()
	 * @see #getMilestone()
	 * @generated
	 */
	EReference getMilestone_RequiredResults();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.Iteration <em>Iteration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Iteration</em>'.
	 * @see org.eclipse.epf.uma.Iteration
	 * @generated
	 */
	EClass getIteration();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.Phase <em>Phase</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Phase</em>'.
	 * @see org.eclipse.epf.uma.Phase
	 * @generated
	 */
	EClass getPhase();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.TeamProfile <em>Team Profile</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Team Profile</em>'.
	 * @see org.eclipse.epf.uma.TeamProfile
	 * @generated
	 */
	EClass getTeamProfile();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.TeamProfile#getTeamRoles <em>Team Roles</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Team Roles</em>'.
	 * @see org.eclipse.epf.uma.TeamProfile#getTeamRoles()
	 * @see #getTeamProfile()
	 * @generated
	 */
	EReference getTeamProfile_TeamRoles();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.epf.uma.TeamProfile#getSuperTeam <em>Super Team</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Super Team</em>'.
	 * @see org.eclipse.epf.uma.TeamProfile#getSuperTeam()
	 * @see #getTeamProfile()
	 * @generated
	 */
	EReference getTeamProfile_SuperTeam();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.TeamProfile#getSubTeam <em>Sub Team</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Sub Team</em>'.
	 * @see org.eclipse.epf.uma.TeamProfile#getSubTeam()
	 * @see #getTeamProfile()
	 * @generated
	 */
	EReference getTeamProfile_SubTeam();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.RoleDescriptor <em>Role Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Role Descriptor</em>'.
	 * @see org.eclipse.epf.uma.RoleDescriptor
	 * @generated
	 */
	EClass getRoleDescriptor();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.epf.uma.RoleDescriptor#getRole <em>Role</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Role</em>'.
	 * @see org.eclipse.epf.uma.RoleDescriptor#getRole()
	 * @see #getRoleDescriptor()
	 * @generated
	 */
	EReference getRoleDescriptor_Role();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.RoleDescriptor#getModifies <em>Modifies</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Modifies</em>'.
	 * @see org.eclipse.epf.uma.RoleDescriptor#getModifies()
	 * @see #getRoleDescriptor()
	 * @generated
	 */
	EReference getRoleDescriptor_Modifies();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.RoleDescriptor#getResponsibleFor <em>Responsible For</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Responsible For</em>'.
	 * @see org.eclipse.epf.uma.RoleDescriptor#getResponsibleFor()
	 * @see #getRoleDescriptor()
	 * @generated
	 */
	EReference getRoleDescriptor_ResponsibleFor();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.RoleDescriptor#getResponsibleForExclude <em>Responsible For Exclude</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Responsible For Exclude</em>'.
	 * @see org.eclipse.epf.uma.RoleDescriptor#getResponsibleForExclude()
	 * @see #getRoleDescriptor()
	 * @generated
	 */
	EReference getRoleDescriptor_ResponsibleForExclude();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.WorkOrder <em>Work Order</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Work Order</em>'.
	 * @see org.eclipse.epf.uma.WorkOrder
	 * @generated
	 */
	EClass getWorkOrder();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.WorkOrder#getLinkType <em>Link Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Link Type</em>'.
	 * @see org.eclipse.epf.uma.WorkOrder#getLinkType()
	 * @see #getWorkOrder()
	 * @generated
	 */
	EAttribute getWorkOrder_LinkType();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.epf.uma.WorkOrder#getPred <em>Pred</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Pred</em>'.
	 * @see org.eclipse.epf.uma.WorkOrder#getPred()
	 * @see #getWorkOrder()
	 * @generated
	 */
	EReference getWorkOrder_Pred();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.ProcessElement <em>Process Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Process Element</em>'.
	 * @see org.eclipse.epf.uma.ProcessElement
	 * @generated
	 */
	EClass getProcessElement();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.PlanningData <em>Planning Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Planning Data</em>'.
	 * @see org.eclipse.epf.uma.PlanningData
	 * @generated
	 */
	EClass getPlanningData();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.PlanningData#getStartDate <em>Start Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Start Date</em>'.
	 * @see org.eclipse.epf.uma.PlanningData#getStartDate()
	 * @see #getPlanningData()
	 * @generated
	 */
	EAttribute getPlanningData_StartDate();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.PlanningData#getFinishDate <em>Finish Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Finish Date</em>'.
	 * @see org.eclipse.epf.uma.PlanningData#getFinishDate()
	 * @see #getPlanningData()
	 * @generated
	 */
	EAttribute getPlanningData_FinishDate();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.PlanningData#getRank <em>Rank</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rank</em>'.
	 * @see org.eclipse.epf.uma.PlanningData#getRank()
	 * @see #getPlanningData()
	 * @generated
	 */
	EAttribute getPlanningData_Rank();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.Descriptor <em>Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Descriptor</em>'.
	 * @see org.eclipse.epf.uma.Descriptor
	 * @generated
	 */
	EClass getDescriptor();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.Descriptor#getIsSynchronizedWithSource <em>Is Synchronized With Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Synchronized With Source</em>'.
	 * @see org.eclipse.epf.uma.Descriptor#getIsSynchronizedWithSource()
	 * @see #getDescriptor()
	 * @generated
	 */
	EAttribute getDescriptor_IsSynchronizedWithSource();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.Descriptor#getGuidanceExclude <em>Guidance Exclude</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Guidance Exclude</em>'.
	 * @see org.eclipse.epf.uma.Descriptor#getGuidanceExclude()
	 * @see #getDescriptor()
	 * @generated
	 */
	EReference getDescriptor_GuidanceExclude();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.Descriptor#getGuidanceAdditional <em>Guidance Additional</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Guidance Additional</em>'.
	 * @see org.eclipse.epf.uma.Descriptor#getGuidanceAdditional()
	 * @see #getDescriptor()
	 * @generated
	 */
	EReference getDescriptor_GuidanceAdditional();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.TaskDescriptor <em>Task Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Task Descriptor</em>'.
	 * @see org.eclipse.epf.uma.TaskDescriptor
	 * @generated
	 */
	EClass getTaskDescriptor();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.epf.uma.TaskDescriptor#getTask <em>Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Task</em>'.
	 * @see org.eclipse.epf.uma.TaskDescriptor#getTask()
	 * @see #getTaskDescriptor()
	 * @generated
	 */
	EReference getTaskDescriptor_Task();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.TaskDescriptor#getAdditionallyPerformedBy <em>Additionally Performed By</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Additionally Performed By</em>'.
	 * @see org.eclipse.epf.uma.TaskDescriptor#getAdditionallyPerformedBy()
	 * @see #getTaskDescriptor()
	 * @generated
	 */
	EReference getTaskDescriptor_AdditionallyPerformedBy();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.TaskDescriptor#getAssistedBy <em>Assisted By</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Assisted By</em>'.
	 * @see org.eclipse.epf.uma.TaskDescriptor#getAssistedBy()
	 * @see #getTaskDescriptor()
	 * @generated
	 */
	EReference getTaskDescriptor_AssistedBy();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.TaskDescriptor#getExternalInput <em>External Input</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>External Input</em>'.
	 * @see org.eclipse.epf.uma.TaskDescriptor#getExternalInput()
	 * @see #getTaskDescriptor()
	 * @generated
	 */
	EReference getTaskDescriptor_ExternalInput();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.TaskDescriptor#getMandatoryInput <em>Mandatory Input</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Mandatory Input</em>'.
	 * @see org.eclipse.epf.uma.TaskDescriptor#getMandatoryInput()
	 * @see #getTaskDescriptor()
	 * @generated
	 */
	EReference getTaskDescriptor_MandatoryInput();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.TaskDescriptor#getOptionalInput <em>Optional Input</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Optional Input</em>'.
	 * @see org.eclipse.epf.uma.TaskDescriptor#getOptionalInput()
	 * @see #getTaskDescriptor()
	 * @generated
	 */
	EReference getTaskDescriptor_OptionalInput();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.TaskDescriptor#getOutput <em>Output</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Output</em>'.
	 * @see org.eclipse.epf.uma.TaskDescriptor#getOutput()
	 * @see #getTaskDescriptor()
	 * @generated
	 */
	EReference getTaskDescriptor_Output();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.TaskDescriptor#getPerformedPrimarilyBy <em>Performed Primarily By</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Performed Primarily By</em>'.
	 * @see org.eclipse.epf.uma.TaskDescriptor#getPerformedPrimarilyBy()
	 * @see #getTaskDescriptor()
	 * @generated
	 */
	EReference getTaskDescriptor_PerformedPrimarilyBy();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.TaskDescriptor#getSelectedSteps <em>Selected Steps</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Selected Steps</em>'.
	 * @see org.eclipse.epf.uma.TaskDescriptor#getSelectedSteps()
	 * @see #getTaskDescriptor()
	 * @generated
	 */
	EReference getTaskDescriptor_SelectedSteps();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.TaskDescriptor#getPerformedPrimarilyByExcluded <em>Performed Primarily By Excluded</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Performed Primarily By Excluded</em>'.
	 * @see org.eclipse.epf.uma.TaskDescriptor#getPerformedPrimarilyByExcluded()
	 * @see #getTaskDescriptor()
	 * @generated
	 */
	EReference getTaskDescriptor_PerformedPrimarilyByExcluded();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.TaskDescriptor#getAdditionallyPerformedByExclude <em>Additionally Performed By Exclude</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Additionally Performed By Exclude</em>'.
	 * @see org.eclipse.epf.uma.TaskDescriptor#getAdditionallyPerformedByExclude()
	 * @see #getTaskDescriptor()
	 * @generated
	 */
	EReference getTaskDescriptor_AdditionallyPerformedByExclude();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.TaskDescriptor#getMandatoryInputExclude <em>Mandatory Input Exclude</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Mandatory Input Exclude</em>'.
	 * @see org.eclipse.epf.uma.TaskDescriptor#getMandatoryInputExclude()
	 * @see #getTaskDescriptor()
	 * @generated
	 */
	EReference getTaskDescriptor_MandatoryInputExclude();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.TaskDescriptor#getOptionalInputExclude <em>Optional Input Exclude</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Optional Input Exclude</em>'.
	 * @see org.eclipse.epf.uma.TaskDescriptor#getOptionalInputExclude()
	 * @see #getTaskDescriptor()
	 * @generated
	 */
	EReference getTaskDescriptor_OptionalInputExclude();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.TaskDescriptor#getOutputExclude <em>Output Exclude</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Output Exclude</em>'.
	 * @see org.eclipse.epf.uma.TaskDescriptor#getOutputExclude()
	 * @see #getTaskDescriptor()
	 * @generated
	 */
	EReference getTaskDescriptor_OutputExclude();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.TaskDescriptor#getSelectedStepsExclude <em>Selected Steps Exclude</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Selected Steps Exclude</em>'.
	 * @see org.eclipse.epf.uma.TaskDescriptor#getSelectedStepsExclude()
	 * @see #getTaskDescriptor()
	 * @generated
	 */
	EReference getTaskDescriptor_SelectedStepsExclude();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.WorkProductDescriptor <em>Work Product Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Work Product Descriptor</em>'.
	 * @see org.eclipse.epf.uma.WorkProductDescriptor
	 * @generated
	 */
	EClass getWorkProductDescriptor();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.WorkProductDescriptor#getActivityEntryState <em>Activity Entry State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Activity Entry State</em>'.
	 * @see org.eclipse.epf.uma.WorkProductDescriptor#getActivityEntryState()
	 * @see #getWorkProductDescriptor()
	 * @generated
	 */
	EAttribute getWorkProductDescriptor_ActivityEntryState();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.WorkProductDescriptor#getActivityExitState <em>Activity Exit State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Activity Exit State</em>'.
	 * @see org.eclipse.epf.uma.WorkProductDescriptor#getActivityExitState()
	 * @see #getWorkProductDescriptor()
	 * @generated
	 */
	EAttribute getWorkProductDescriptor_ActivityExitState();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.epf.uma.WorkProductDescriptor#getWorkProduct <em>Work Product</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Work Product</em>'.
	 * @see org.eclipse.epf.uma.WorkProductDescriptor#getWorkProduct()
	 * @see #getWorkProductDescriptor()
	 * @generated
	 */
	EReference getWorkProductDescriptor_WorkProduct();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.WorkProductDescriptor#getImpactedBy <em>Impacted By</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Impacted By</em>'.
	 * @see org.eclipse.epf.uma.WorkProductDescriptor#getImpactedBy()
	 * @see #getWorkProductDescriptor()
	 * @generated
	 */
	EReference getWorkProductDescriptor_ImpactedBy();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.WorkProductDescriptor#getImpacts <em>Impacts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Impacts</em>'.
	 * @see org.eclipse.epf.uma.WorkProductDescriptor#getImpacts()
	 * @see #getWorkProductDescriptor()
	 * @generated
	 */
	EReference getWorkProductDescriptor_Impacts();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.WorkProductDescriptor#getDeliverableParts <em>Deliverable Parts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Deliverable Parts</em>'.
	 * @see org.eclipse.epf.uma.WorkProductDescriptor#getDeliverableParts()
	 * @see #getWorkProductDescriptor()
	 * @generated
	 */
	EReference getWorkProductDescriptor_DeliverableParts();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.WorkProductDescriptor#getDeliverablePartsExclude <em>Deliverable Parts Exclude</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Deliverable Parts Exclude</em>'.
	 * @see org.eclipse.epf.uma.WorkProductDescriptor#getDeliverablePartsExclude()
	 * @see #getWorkProductDescriptor()
	 * @generated
	 */
	EReference getWorkProductDescriptor_DeliverablePartsExclude();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.CompositeRole <em>Composite Role</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Composite Role</em>'.
	 * @see org.eclipse.epf.uma.CompositeRole
	 * @generated
	 */
	EClass getCompositeRole();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.CompositeRole#getAggregatedRoles <em>Aggregated Roles</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Aggregated Roles</em>'.
	 * @see org.eclipse.epf.uma.CompositeRole#getAggregatedRoles()
	 * @see #getCompositeRole()
	 * @generated
	 */
	EReference getCompositeRole_AggregatedRoles();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.BreakdownElementDescription <em>Breakdown Element Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Breakdown Element Description</em>'.
	 * @see org.eclipse.epf.uma.BreakdownElementDescription
	 * @generated
	 */
	EClass getBreakdownElementDescription();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.BreakdownElementDescription#getUsageGuidance <em>Usage Guidance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Usage Guidance</em>'.
	 * @see org.eclipse.epf.uma.BreakdownElementDescription#getUsageGuidance()
	 * @see #getBreakdownElementDescription()
	 * @generated
	 */
	EAttribute getBreakdownElementDescription_UsageGuidance();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.ActivityDescription <em>Activity Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Activity Description</em>'.
	 * @see org.eclipse.epf.uma.ActivityDescription
	 * @generated
	 */
	EClass getActivityDescription();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.ActivityDescription#getPurpose <em>Purpose</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Purpose</em>'.
	 * @see org.eclipse.epf.uma.ActivityDescription#getPurpose()
	 * @see #getActivityDescription()
	 * @generated
	 */
	EAttribute getActivityDescription_Purpose();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.ActivityDescription#getAlternatives <em>Alternatives</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Alternatives</em>'.
	 * @see org.eclipse.epf.uma.ActivityDescription#getAlternatives()
	 * @see #getActivityDescription()
	 * @generated
	 */
	EAttribute getActivityDescription_Alternatives();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.ActivityDescription#getHowtoStaff <em>Howto Staff</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Howto Staff</em>'.
	 * @see org.eclipse.epf.uma.ActivityDescription#getHowtoStaff()
	 * @see #getActivityDescription()
	 * @generated
	 */
	EAttribute getActivityDescription_HowtoStaff();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.DeliveryProcessDescription <em>Delivery Process Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Delivery Process Description</em>'.
	 * @see org.eclipse.epf.uma.DeliveryProcessDescription
	 * @generated
	 */
	EClass getDeliveryProcessDescription();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.DeliveryProcessDescription#getScale <em>Scale</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Scale</em>'.
	 * @see org.eclipse.epf.uma.DeliveryProcessDescription#getScale()
	 * @see #getDeliveryProcessDescription()
	 * @generated
	 */
	EAttribute getDeliveryProcessDescription_Scale();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.DeliveryProcessDescription#getProjectCharacteristics <em>Project Characteristics</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Project Characteristics</em>'.
	 * @see org.eclipse.epf.uma.DeliveryProcessDescription#getProjectCharacteristics()
	 * @see #getDeliveryProcessDescription()
	 * @generated
	 */
	EAttribute getDeliveryProcessDescription_ProjectCharacteristics();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.DeliveryProcessDescription#getRiskLevel <em>Risk Level</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Risk Level</em>'.
	 * @see org.eclipse.epf.uma.DeliveryProcessDescription#getRiskLevel()
	 * @see #getDeliveryProcessDescription()
	 * @generated
	 */
	EAttribute getDeliveryProcessDescription_RiskLevel();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.DeliveryProcessDescription#getEstimatingTechnique <em>Estimating Technique</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Estimating Technique</em>'.
	 * @see org.eclipse.epf.uma.DeliveryProcessDescription#getEstimatingTechnique()
	 * @see #getDeliveryProcessDescription()
	 * @generated
	 */
	EAttribute getDeliveryProcessDescription_EstimatingTechnique();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.DeliveryProcessDescription#getProjectMemberExpertise <em>Project Member Expertise</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Project Member Expertise</em>'.
	 * @see org.eclipse.epf.uma.DeliveryProcessDescription#getProjectMemberExpertise()
	 * @see #getDeliveryProcessDescription()
	 * @generated
	 */
	EAttribute getDeliveryProcessDescription_ProjectMemberExpertise();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.DeliveryProcessDescription#getTypeOfContract <em>Type Of Contract</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type Of Contract</em>'.
	 * @see org.eclipse.epf.uma.DeliveryProcessDescription#getTypeOfContract()
	 * @see #getDeliveryProcessDescription()
	 * @generated
	 */
	EAttribute getDeliveryProcessDescription_TypeOfContract();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.ProcessDescription <em>Process Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Process Description</em>'.
	 * @see org.eclipse.epf.uma.ProcessDescription
	 * @generated
	 */
	EClass getProcessDescription();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.ProcessDescription#getScope <em>Scope</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Scope</em>'.
	 * @see org.eclipse.epf.uma.ProcessDescription#getScope()
	 * @see #getProcessDescription()
	 * @generated
	 */
	EAttribute getProcessDescription_Scope();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.ProcessDescription#getUsageNotes <em>Usage Notes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Usage Notes</em>'.
	 * @see org.eclipse.epf.uma.ProcessDescription#getUsageNotes()
	 * @see #getProcessDescription()
	 * @generated
	 */
	EAttribute getProcessDescription_UsageNotes();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.DescriptorDescription <em>Descriptor Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Descriptor Description</em>'.
	 * @see org.eclipse.epf.uma.DescriptorDescription
	 * @generated
	 */
	EClass getDescriptorDescription();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.DescriptorDescription#getRefinedDescription <em>Refined Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Refined Description</em>'.
	 * @see org.eclipse.epf.uma.DescriptorDescription#getRefinedDescription()
	 * @see #getDescriptorDescription()
	 * @generated
	 */
	EAttribute getDescriptorDescription_RefinedDescription();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.Concept <em>Concept</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Concept</em>'.
	 * @see org.eclipse.epf.uma.Concept
	 * @generated
	 */
	EClass getConcept();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.Checklist <em>Checklist</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Checklist</em>'.
	 * @see org.eclipse.epf.uma.Checklist
	 * @generated
	 */
	EClass getChecklist();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.Example <em>Example</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Example</em>'.
	 * @see org.eclipse.epf.uma.Example
	 * @generated
	 */
	EClass getExample();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.Guideline <em>Guideline</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Guideline</em>'.
	 * @see org.eclipse.epf.uma.Guideline
	 * @generated
	 */
	EClass getGuideline();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.EstimationConsiderations <em>Estimation Considerations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Estimation Considerations</em>'.
	 * @see org.eclipse.epf.uma.EstimationConsiderations
	 * @generated
	 */
	EClass getEstimationConsiderations();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.Report <em>Report</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Report</em>'.
	 * @see org.eclipse.epf.uma.Report
	 * @generated
	 */
	EClass getReport();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.Template <em>Template</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Template</em>'.
	 * @see org.eclipse.epf.uma.Template
	 * @generated
	 */
	EClass getTemplate();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.SupportingMaterial <em>Supporting Material</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Supporting Material</em>'.
	 * @see org.eclipse.epf.uma.SupportingMaterial
	 * @generated
	 */
	EClass getSupportingMaterial();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.ToolMentor <em>Tool Mentor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tool Mentor</em>'.
	 * @see org.eclipse.epf.uma.ToolMentor
	 * @generated
	 */
	EClass getToolMentor();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.Whitepaper <em>Whitepaper</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Whitepaper</em>'.
	 * @see org.eclipse.epf.uma.Whitepaper
	 * @generated
	 */
	EClass getWhitepaper();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.TermDefinition <em>Term Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Term Definition</em>'.
	 * @see org.eclipse.epf.uma.TermDefinition
	 * @generated
	 */
	EClass getTermDefinition();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.ApplicableMetaClassInfo <em>Applicable Meta Class Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Applicable Meta Class Info</em>'.
	 * @see org.eclipse.epf.uma.ApplicableMetaClassInfo
	 * @generated
	 */
	EClass getApplicableMetaClassInfo();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.ApplicableMetaClassInfo#getIsPrimaryExtension <em>Is Primary Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Primary Extension</em>'.
	 * @see org.eclipse.epf.uma.ApplicableMetaClassInfo#getIsPrimaryExtension()
	 * @see #getApplicableMetaClassInfo()
	 * @generated
	 */
	EAttribute getApplicableMetaClassInfo_IsPrimaryExtension();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.Practice <em>Practice</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Practice</em>'.
	 * @see org.eclipse.epf.uma.Practice
	 * @generated
	 */
	EClass getPractice();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.uma.Practice#getSubPractices <em>Sub Practices</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Sub Practices</em>'.
	 * @see org.eclipse.epf.uma.Practice#getSubPractices()
	 * @see #getPractice()
	 * @generated
	 */
	EReference getPractice_SubPractices();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.Practice#getContentReferences <em>Content References</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Content References</em>'.
	 * @see org.eclipse.epf.uma.Practice#getContentReferences()
	 * @see #getPractice()
	 * @generated
	 */
	EReference getPractice_ContentReferences();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.Practice#getActivityReferences <em>Activity References</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Activity References</em>'.
	 * @see org.eclipse.epf.uma.Practice#getActivityReferences()
	 * @see #getPractice()
	 * @generated
	 */
	EReference getPractice_ActivityReferences();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.ReusableAsset <em>Reusable Asset</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Reusable Asset</em>'.
	 * @see org.eclipse.epf.uma.ReusableAsset
	 * @generated
	 */
	EClass getReusableAsset();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.Discipline <em>Discipline</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Discipline</em>'.
	 * @see org.eclipse.epf.uma.Discipline
	 * @generated
	 */
	EClass getDiscipline();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.Discipline#getTasks <em>Tasks</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Tasks</em>'.
	 * @see org.eclipse.epf.uma.Discipline#getTasks()
	 * @see #getDiscipline()
	 * @generated
	 */
	EReference getDiscipline_Tasks();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.uma.Discipline#getSubdiscipline <em>Subdiscipline</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Subdiscipline</em>'.
	 * @see org.eclipse.epf.uma.Discipline#getSubdiscipline()
	 * @see #getDiscipline()
	 * @generated
	 */
	EReference getDiscipline_Subdiscipline();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.Discipline#getReferenceWorkflows <em>Reference Workflows</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Reference Workflows</em>'.
	 * @see org.eclipse.epf.uma.Discipline#getReferenceWorkflows()
	 * @see #getDiscipline()
	 * @generated
	 */
	EReference getDiscipline_ReferenceWorkflows();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.ContentCategory <em>Content Category</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Content Category</em>'.
	 * @see org.eclipse.epf.uma.ContentCategory
	 * @generated
	 */
	EClass getContentCategory();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.RoleSet <em>Role Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Role Set</em>'.
	 * @see org.eclipse.epf.uma.RoleSet
	 * @generated
	 */
	EClass getRoleSet();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.RoleSet#getRoles <em>Roles</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Roles</em>'.
	 * @see org.eclipse.epf.uma.RoleSet#getRoles()
	 * @see #getRoleSet()
	 * @generated
	 */
	EReference getRoleSet_Roles();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.Domain <em>Domain</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Domain</em>'.
	 * @see org.eclipse.epf.uma.Domain
	 * @generated
	 */
	EClass getDomain();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.Domain#getWorkProducts <em>Work Products</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Work Products</em>'.
	 * @see org.eclipse.epf.uma.Domain#getWorkProducts()
	 * @see #getDomain()
	 * @generated
	 */
	EReference getDomain_WorkProducts();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.uma.Domain#getSubdomains <em>Subdomains</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Subdomains</em>'.
	 * @see org.eclipse.epf.uma.Domain#getSubdomains()
	 * @see #getDomain()
	 * @generated
	 */
	EReference getDomain_Subdomains();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.WorkProductType <em>Work Product Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Work Product Type</em>'.
	 * @see org.eclipse.epf.uma.WorkProductType
	 * @generated
	 */
	EClass getWorkProductType();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.WorkProductType#getWorkProducts <em>Work Products</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Work Products</em>'.
	 * @see org.eclipse.epf.uma.WorkProductType#getWorkProducts()
	 * @see #getWorkProductType()
	 * @generated
	 */
	EReference getWorkProductType_WorkProducts();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.DisciplineGrouping <em>Discipline Grouping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Discipline Grouping</em>'.
	 * @see org.eclipse.epf.uma.DisciplineGrouping
	 * @generated
	 */
	EClass getDisciplineGrouping();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.DisciplineGrouping#getDisciplines <em>Disciplines</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Disciplines</em>'.
	 * @see org.eclipse.epf.uma.DisciplineGrouping#getDisciplines()
	 * @see #getDisciplineGrouping()
	 * @generated
	 */
	EReference getDisciplineGrouping_Disciplines();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.Tool <em>Tool</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tool</em>'.
	 * @see org.eclipse.epf.uma.Tool
	 * @generated
	 */
	EClass getTool();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.Tool#getToolMentors <em>Tool Mentors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Tool Mentors</em>'.
	 * @see org.eclipse.epf.uma.Tool#getToolMentors()
	 * @see #getTool()
	 * @generated
	 */
	EReference getTool_ToolMentors();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.RoleSetGrouping <em>Role Set Grouping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Role Set Grouping</em>'.
	 * @see org.eclipse.epf.uma.RoleSetGrouping
	 * @generated
	 */
	EClass getRoleSetGrouping();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.RoleSetGrouping#getRoleSets <em>Role Sets</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Role Sets</em>'.
	 * @see org.eclipse.epf.uma.RoleSetGrouping#getRoleSets()
	 * @see #getRoleSetGrouping()
	 * @generated
	 */
	EReference getRoleSetGrouping_RoleSets();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.CustomCategory <em>Custom Category</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Custom Category</em>'.
	 * @see org.eclipse.epf.uma.CustomCategory
	 * @generated
	 */
	EClass getCustomCategory();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.CustomCategory#getCategorizedElements <em>Categorized Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Categorized Elements</em>'.
	 * @see org.eclipse.epf.uma.CustomCategory#getCategorizedElements()
	 * @see #getCustomCategory()
	 * @generated
	 */
	EReference getCustomCategory_CategorizedElements();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.CustomCategory#getSubCategories <em>Sub Categories</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Sub Categories</em>'.
	 * @see org.eclipse.epf.uma.CustomCategory#getSubCategories()
	 * @see #getCustomCategory()
	 * @generated
	 */
	EReference getCustomCategory_SubCategories();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.DeliveryProcess <em>Delivery Process</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Delivery Process</em>'.
	 * @see org.eclipse.epf.uma.DeliveryProcess
	 * @generated
	 */
	EClass getDeliveryProcess();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.DeliveryProcess#getEducationMaterials <em>Education Materials</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Education Materials</em>'.
	 * @see org.eclipse.epf.uma.DeliveryProcess#getEducationMaterials()
	 * @see #getDeliveryProcess()
	 * @generated
	 */
	EReference getDeliveryProcess_EducationMaterials();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.DeliveryProcess#getCommunicationsMaterials <em>Communications Materials</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Communications Materials</em>'.
	 * @see org.eclipse.epf.uma.DeliveryProcess#getCommunicationsMaterials()
	 * @see #getDeliveryProcess()
	 * @generated
	 */
	EReference getDeliveryProcess_CommunicationsMaterials();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.Process <em>Process</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Process</em>'.
	 * @see org.eclipse.epf.uma.Process
	 * @generated
	 */
	EClass getProcess();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.Process#getIncludesPatterns <em>Includes Patterns</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Includes Patterns</em>'.
	 * @see org.eclipse.epf.uma.Process#getIncludesPatterns()
	 * @see #getProcess()
	 * @generated
	 */
	EReference getProcess_IncludesPatterns();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.epf.uma.Process#getDefaultContext <em>Default Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Default Context</em>'.
	 * @see org.eclipse.epf.uma.Process#getDefaultContext()
	 * @see #getProcess()
	 * @generated
	 */
	EReference getProcess_DefaultContext();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.Process#getValidContext <em>Valid Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Valid Context</em>'.
	 * @see org.eclipse.epf.uma.Process#getValidContext()
	 * @see #getProcess()
	 * @generated
	 */
	EReference getProcess_ValidContext();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.CapabilityPattern <em>Capability Pattern</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Capability Pattern</em>'.
	 * @see org.eclipse.epf.uma.CapabilityPattern
	 * @generated
	 */
	EClass getCapabilityPattern();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.ProcessPlanningTemplate <em>Process Planning Template</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Process Planning Template</em>'.
	 * @see org.eclipse.epf.uma.ProcessPlanningTemplate
	 * @generated
	 */
	EClass getProcessPlanningTemplate();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.ProcessPlanningTemplate#getBasedOnProcesses <em>Based On Processes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Based On Processes</em>'.
	 * @see org.eclipse.epf.uma.ProcessPlanningTemplate#getBasedOnProcesses()
	 * @see #getProcessPlanningTemplate()
	 * @generated
	 */
	EReference getProcessPlanningTemplate_BasedOnProcesses();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.Roadmap <em>Roadmap</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Roadmap</em>'.
	 * @see org.eclipse.epf.uma.Roadmap
	 * @generated
	 */
	EClass getRoadmap();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.ProcessComponent <em>Process Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Process Component</em>'.
	 * @see org.eclipse.epf.uma.ProcessComponent
	 * @generated
	 */
	EClass getProcessComponent();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.ProcessComponent#getInterfaces <em>Interfaces</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Interfaces</em>'.
	 * @see org.eclipse.epf.uma.ProcessComponent#getInterfaces()
	 * @see #getProcessComponent()
	 * @generated
	 */
	EReference getProcessComponent_Interfaces();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.epf.uma.ProcessComponent#getProcess <em>Process</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Process</em>'.
	 * @see org.eclipse.epf.uma.ProcessComponent#getProcess()
	 * @see #getProcessComponent()
	 * @generated
	 */
	EReference getProcessComponent_Process();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.ProcessPackage <em>Process Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Process Package</em>'.
	 * @see org.eclipse.epf.uma.ProcessPackage
	 * @generated
	 */
	EClass getProcessPackage();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.uma.ProcessPackage#getProcessElements <em>Process Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Process Elements</em>'.
	 * @see org.eclipse.epf.uma.ProcessPackage#getProcessElements()
	 * @see #getProcessPackage()
	 * @generated
	 */
	EReference getProcessPackage_ProcessElements();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.uma.ProcessPackage#getDiagrams <em>Diagrams</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Diagrams</em>'.
	 * @see org.eclipse.epf.uma.ProcessPackage#getDiagrams()
	 * @see #getProcessPackage()
	 * @generated
	 */
	EReference getProcessPackage_Diagrams();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.ProcessComponentInterface <em>Process Component Interface</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Process Component Interface</em>'.
	 * @see org.eclipse.epf.uma.ProcessComponentInterface
	 * @generated
	 */
	EClass getProcessComponentInterface();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.uma.ProcessComponentInterface#getInterfaceSpecifications <em>Interface Specifications</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Interface Specifications</em>'.
	 * @see org.eclipse.epf.uma.ProcessComponentInterface#getInterfaceSpecifications()
	 * @see #getProcessComponentInterface()
	 * @generated
	 */
	EReference getProcessComponentInterface_InterfaceSpecifications();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.uma.ProcessComponentInterface#getInterfaceIO <em>Interface IO</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Interface IO</em>'.
	 * @see org.eclipse.epf.uma.ProcessComponentInterface#getInterfaceIO()
	 * @see #getProcessComponentInterface()
	 * @generated
	 */
	EReference getProcessComponentInterface_InterfaceIO();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.ProcessComponentDescriptor <em>Process Component Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Process Component Descriptor</em>'.
	 * @see org.eclipse.epf.uma.ProcessComponentDescriptor
	 * @generated
	 */
	EClass getProcessComponentDescriptor();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.epf.uma.ProcessComponentDescriptor#get_processComponent <em>process Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>process Component</em>'.
	 * @see org.eclipse.epf.uma.ProcessComponentDescriptor#get_processComponent()
	 * @see #getProcessComponentDescriptor()
	 * @generated
	 */
	EReference getProcessComponentDescriptor__processComponent();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.MethodPlugin <em>Method Plugin</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Method Plugin</em>'.
	 * @see org.eclipse.epf.uma.MethodPlugin
	 * @generated
	 */
	EClass getMethodPlugin();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.MethodPlugin#getUserChangeable <em>User Changeable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>User Changeable</em>'.
	 * @see org.eclipse.epf.uma.MethodPlugin#getUserChangeable()
	 * @see #getMethodPlugin()
	 * @generated
	 */
	EAttribute getMethodPlugin_UserChangeable();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.uma.MethodPlugin#getMethodPackages <em>Method Packages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Method Packages</em>'.
	 * @see org.eclipse.epf.uma.MethodPlugin#getMethodPackages()
	 * @see #getMethodPlugin()
	 * @generated
	 */
	EReference getMethodPlugin_MethodPackages();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.MethodPlugin#getBases <em>Bases</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Bases</em>'.
	 * @see org.eclipse.epf.uma.MethodPlugin#getBases()
	 * @see #getMethodPlugin()
	 * @generated
	 */
	EReference getMethodPlugin_Bases();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.MethodPlugin#isSupporting <em>Supporting</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Supporting</em>'.
	 * @see org.eclipse.epf.uma.MethodPlugin#isSupporting()
	 * @see #getMethodPlugin()
	 * @generated
	 */
	EAttribute getMethodPlugin_Supporting();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.VariabilityElement <em>Variability Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Variability Element</em>'.
	 * @see org.eclipse.epf.uma.VariabilityElement
	 * @generated
	 */
	EClass getVariabilityElement();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.VariabilityElement#getVariabilityType <em>Variability Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Variability Type</em>'.
	 * @see org.eclipse.epf.uma.VariabilityElement#getVariabilityType()
	 * @see #getVariabilityElement()
	 * @generated
	 */
	EAttribute getVariabilityElement_VariabilityType();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.epf.uma.VariabilityElement#getVariabilityBasedOnElement <em>Variability Based On Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Variability Based On Element</em>'.
	 * @see org.eclipse.epf.uma.VariabilityElement#getVariabilityBasedOnElement()
	 * @see #getVariabilityElement()
	 * @generated
	 */
	EReference getVariabilityElement_VariabilityBasedOnElement();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.MethodUnit <em>Method Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Method Unit</em>'.
	 * @see org.eclipse.epf.uma.MethodUnit
	 * @generated
	 */
	EClass getMethodUnit();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.MethodUnit#getAuthors <em>Authors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Authors</em>'.
	 * @see org.eclipse.epf.uma.MethodUnit#getAuthors()
	 * @see #getMethodUnit()
	 * @generated
	 */
	EAttribute getMethodUnit_Authors();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.MethodUnit#getChangeDate <em>Change Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Change Date</em>'.
	 * @see org.eclipse.epf.uma.MethodUnit#getChangeDate()
	 * @see #getMethodUnit()
	 * @generated
	 */
	EAttribute getMethodUnit_ChangeDate();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.MethodUnit#getChangeDescription <em>Change Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Change Description</em>'.
	 * @see org.eclipse.epf.uma.MethodUnit#getChangeDescription()
	 * @see #getMethodUnit()
	 * @generated
	 */
	EAttribute getMethodUnit_ChangeDescription();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.MethodUnit#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see org.eclipse.epf.uma.MethodUnit#getVersion()
	 * @see #getMethodUnit()
	 * @generated
	 */
	EAttribute getMethodUnit_Version();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.epf.uma.MethodUnit#getCopyrightStatement <em>Copyright Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Copyright Statement</em>'.
	 * @see org.eclipse.epf.uma.MethodUnit#getCopyrightStatement()
	 * @see #getMethodUnit()
	 * @generated
	 */
	EReference getMethodUnit_CopyrightStatement();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.MethodConfiguration <em>Method Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Method Configuration</em>'.
	 * @see org.eclipse.epf.uma.MethodConfiguration
	 * @generated
	 */
	EClass getMethodConfiguration();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.MethodConfiguration#getMethodPluginSelection <em>Method Plugin Selection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Method Plugin Selection</em>'.
	 * @see org.eclipse.epf.uma.MethodConfiguration#getMethodPluginSelection()
	 * @see #getMethodConfiguration()
	 * @generated
	 */
	EReference getMethodConfiguration_MethodPluginSelection();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.MethodConfiguration#getMethodPackageSelection <em>Method Package Selection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Method Package Selection</em>'.
	 * @see org.eclipse.epf.uma.MethodConfiguration#getMethodPackageSelection()
	 * @see #getMethodConfiguration()
	 * @generated
	 */
	EReference getMethodConfiguration_MethodPackageSelection();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.MethodConfiguration#getProcessViews <em>Process Views</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Process Views</em>'.
	 * @see org.eclipse.epf.uma.MethodConfiguration#getProcessViews()
	 * @see #getMethodConfiguration()
	 * @generated
	 */
	EReference getMethodConfiguration_ProcessViews();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.epf.uma.MethodConfiguration#getDefaultView <em>Default View</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Default View</em>'.
	 * @see org.eclipse.epf.uma.MethodConfiguration#getDefaultView()
	 * @see #getMethodConfiguration()
	 * @generated
	 */
	EReference getMethodConfiguration_DefaultView();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.MethodConfiguration#getBaseConfigurations <em>Base Configurations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Base Configurations</em>'.
	 * @see org.eclipse.epf.uma.MethodConfiguration#getBaseConfigurations()
	 * @see #getMethodConfiguration()
	 * @generated
	 */
	EReference getMethodConfiguration_BaseConfigurations();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.MethodConfiguration#getSubtractedCategory <em>Subtracted Category</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Subtracted Category</em>'.
	 * @see org.eclipse.epf.uma.MethodConfiguration#getSubtractedCategory()
	 * @see #getMethodConfiguration()
	 * @generated
	 */
	EReference getMethodConfiguration_SubtractedCategory();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.MethodConfiguration#getAddedCategory <em>Added Category</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Added Category</em>'.
	 * @see org.eclipse.epf.uma.MethodConfiguration#getAddedCategory()
	 * @see #getMethodConfiguration()
	 * @generated
	 */
	EReference getMethodConfiguration_AddedCategory();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.ProcessFamily <em>Process Family</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Process Family</em>'.
	 * @see org.eclipse.epf.uma.ProcessFamily
	 * @generated
	 */
	EClass getProcessFamily();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.ProcessFamily#getDeliveryProcesses <em>Delivery Processes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Delivery Processes</em>'.
	 * @see org.eclipse.epf.uma.ProcessFamily#getDeliveryProcesses()
	 * @see #getProcessFamily()
	 * @generated
	 */
	EReference getProcessFamily_DeliveryProcesses();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.MethodLibrary <em>Method Library</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Method Library</em>'.
	 * @see org.eclipse.epf.uma.MethodLibrary
	 * @generated
	 */
	EClass getMethodLibrary();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.uma.MethodLibrary#getMethodPlugins <em>Method Plugins</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Method Plugins</em>'.
	 * @see org.eclipse.epf.uma.MethodLibrary#getMethodPlugins()
	 * @see #getMethodLibrary()
	 * @generated
	 */
	EReference getMethodLibrary_MethodPlugins();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.uma.MethodLibrary#getPredefinedConfigurations <em>Predefined Configurations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Predefined Configurations</em>'.
	 * @see org.eclipse.epf.uma.MethodLibrary#getPredefinedConfigurations()
	 * @see #getMethodLibrary()
	 * @generated
	 */
	EReference getMethodLibrary_PredefinedConfigurations();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.Point <em>Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Point</em>'.
	 * @see org.eclipse.epf.uma.Point
	 * @generated
	 */
	EClass getPoint();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.Point#getX <em>X</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>X</em>'.
	 * @see org.eclipse.epf.uma.Point#getX()
	 * @see #getPoint()
	 * @generated
	 */
	EAttribute getPoint_X();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.Point#getY <em>Y</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Y</em>'.
	 * @see org.eclipse.epf.uma.Point#getY()
	 * @see #getPoint()
	 * @generated
	 */
	EAttribute getPoint_Y();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.GraphElement <em>Graph Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Graph Element</em>'.
	 * @see org.eclipse.epf.uma.GraphElement
	 * @generated
	 */
	EClass getGraphElement();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.epf.uma.GraphElement#getPosition <em>Position</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Position</em>'.
	 * @see org.eclipse.epf.uma.GraphElement#getPosition()
	 * @see #getGraphElement()
	 * @generated
	 */
	EReference getGraphElement_Position();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.uma.GraphElement#getContained <em>Contained</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Contained</em>'.
	 * @see org.eclipse.epf.uma.GraphElement#getContained()
	 * @see #getGraphElement()
	 * @generated
	 */
	EReference getGraphElement_Contained();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.uma.GraphElement#getLink <em>Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Link</em>'.
	 * @see org.eclipse.epf.uma.GraphElement#getLink()
	 * @see #getGraphElement()
	 * @generated
	 */
	EReference getGraphElement_Link();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.uma.GraphElement#getAnchorage <em>Anchorage</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Anchorage</em>'.
	 * @see org.eclipse.epf.uma.GraphElement#getAnchorage()
	 * @see #getGraphElement()
	 * @generated
	 */
	EReference getGraphElement_Anchorage();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.epf.uma.GraphElement#getSemanticModel <em>Semantic Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Semantic Model</em>'.
	 * @see org.eclipse.epf.uma.GraphElement#getSemanticModel()
	 * @see #getGraphElement()
	 * @generated
	 */
	EReference getGraphElement_SemanticModel();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.DiagramElement <em>Diagram Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Diagram Element</em>'.
	 * @see org.eclipse.epf.uma.DiagramElement
	 * @generated
	 */
	EClass getDiagramElement();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.DiagramElement#getIsVisible <em>Is Visible</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Visible</em>'.
	 * @see org.eclipse.epf.uma.DiagramElement#getIsVisible()
	 * @see #getDiagramElement()
	 * @generated
	 */
	EAttribute getDiagramElement_IsVisible();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.epf.uma.DiagramElement#getContainer <em>Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Container</em>'.
	 * @see org.eclipse.epf.uma.DiagramElement#getContainer()
	 * @see #getDiagramElement()
	 * @generated
	 */
	EReference getDiagramElement_Container();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.DiagramElement#getReference <em>Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Reference</em>'.
	 * @see org.eclipse.epf.uma.DiagramElement#getReference()
	 * @see #getDiagramElement()
	 * @generated
	 */
	EReference getDiagramElement_Reference();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.uma.DiagramElement#getProperty <em>Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Property</em>'.
	 * @see org.eclipse.epf.uma.DiagramElement#getProperty()
	 * @see #getDiagramElement()
	 * @generated
	 */
	EReference getDiagramElement_Property();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.DiagramLink <em>Diagram Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Diagram Link</em>'.
	 * @see org.eclipse.epf.uma.DiagramLink
	 * @generated
	 */
	EClass getDiagramLink();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.DiagramLink#getZoom <em>Zoom</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Zoom</em>'.
	 * @see org.eclipse.epf.uma.DiagramLink#getZoom()
	 * @see #getDiagramLink()
	 * @generated
	 */
	EAttribute getDiagramLink_Zoom();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.epf.uma.DiagramLink#getViewport <em>Viewport</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Viewport</em>'.
	 * @see org.eclipse.epf.uma.DiagramLink#getViewport()
	 * @see #getDiagramLink()
	 * @generated
	 */
	EReference getDiagramLink_Viewport();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.epf.uma.DiagramLink#getGraphElement <em>Graph Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Graph Element</em>'.
	 * @see org.eclipse.epf.uma.DiagramLink#getGraphElement()
	 * @see #getDiagramLink()
	 * @generated
	 */
	EReference getDiagramLink_GraphElement();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.epf.uma.DiagramLink#getDiagram <em>Diagram</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Diagram</em>'.
	 * @see org.eclipse.epf.uma.DiagramLink#getDiagram()
	 * @see #getDiagramLink()
	 * @generated
	 */
	EReference getDiagramLink_Diagram();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.GraphConnector <em>Graph Connector</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Graph Connector</em>'.
	 * @see org.eclipse.epf.uma.GraphConnector
	 * @generated
	 */
	EClass getGraphConnector();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.epf.uma.GraphConnector#getGraphElement <em>Graph Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Graph Element</em>'.
	 * @see org.eclipse.epf.uma.GraphConnector#getGraphElement()
	 * @see #getGraphConnector()
	 * @generated
	 */
	EReference getGraphConnector_GraphElement();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.GraphConnector#getGraphEdge <em>Graph Edge</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Graph Edge</em>'.
	 * @see org.eclipse.epf.uma.GraphConnector#getGraphEdge()
	 * @see #getGraphConnector()
	 * @generated
	 */
	EReference getGraphConnector_GraphEdge();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.SemanticModelBridge <em>Semantic Model Bridge</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Semantic Model Bridge</em>'.
	 * @see org.eclipse.epf.uma.SemanticModelBridge
	 * @generated
	 */
	EClass getSemanticModelBridge();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.SemanticModelBridge#getPresentation <em>Presentation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Presentation</em>'.
	 * @see org.eclipse.epf.uma.SemanticModelBridge#getPresentation()
	 * @see #getSemanticModelBridge()
	 * @generated
	 */
	EAttribute getSemanticModelBridge_Presentation();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.epf.uma.SemanticModelBridge#getGraphElement <em>Graph Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Graph Element</em>'.
	 * @see org.eclipse.epf.uma.SemanticModelBridge#getGraphElement()
	 * @see #getSemanticModelBridge()
	 * @generated
	 */
	EReference getSemanticModelBridge_GraphElement();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.epf.uma.SemanticModelBridge#getDiagram <em>Diagram</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Diagram</em>'.
	 * @see org.eclipse.epf.uma.SemanticModelBridge#getDiagram()
	 * @see #getSemanticModelBridge()
	 * @generated
	 */
	EReference getSemanticModelBridge_Diagram();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.Dimension <em>Dimension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Dimension</em>'.
	 * @see org.eclipse.epf.uma.Dimension
	 * @generated
	 */
	EClass getDimension();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.Dimension#getWidth <em>Width</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Width</em>'.
	 * @see org.eclipse.epf.uma.Dimension#getWidth()
	 * @see #getDimension()
	 * @generated
	 */
	EAttribute getDimension_Width();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.Dimension#getHeight <em>Height</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Height</em>'.
	 * @see org.eclipse.epf.uma.Dimension#getHeight()
	 * @see #getDimension()
	 * @generated
	 */
	EAttribute getDimension_Height();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.Reference <em>Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Reference</em>'.
	 * @see org.eclipse.epf.uma.Reference
	 * @generated
	 */
	EClass getReference();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.Reference#getIsIndividualRepresentation <em>Is Individual Representation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Individual Representation</em>'.
	 * @see org.eclipse.epf.uma.Reference#getIsIndividualRepresentation()
	 * @see #getReference()
	 * @generated
	 */
	EAttribute getReference_IsIndividualRepresentation();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.epf.uma.Reference#getReferenced <em>Referenced</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Referenced</em>'.
	 * @see org.eclipse.epf.uma.Reference#getReferenced()
	 * @see #getReference()
	 * @generated
	 */
	EReference getReference_Referenced();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.Property <em>Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Property</em>'.
	 * @see org.eclipse.epf.uma.Property
	 * @generated
	 */
	EClass getProperty();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.Property#getKey <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see org.eclipse.epf.uma.Property#getKey()
	 * @see #getProperty()
	 * @generated
	 */
	EAttribute getProperty_Key();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.Property#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.epf.uma.Property#getValue()
	 * @see #getProperty()
	 * @generated
	 */
	EAttribute getProperty_Value();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.GraphEdge <em>Graph Edge</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Graph Edge</em>'.
	 * @see org.eclipse.epf.uma.GraphEdge
	 * @generated
	 */
	EClass getGraphEdge();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.GraphEdge#getAnchor <em>Anchor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Anchor</em>'.
	 * @see org.eclipse.epf.uma.GraphEdge#getAnchor()
	 * @see #getGraphEdge()
	 * @generated
	 */
	EReference getGraphEdge_Anchor();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.uma.GraphEdge#getWaypoints <em>Waypoints</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Waypoints</em>'.
	 * @see org.eclipse.epf.uma.GraphEdge#getWaypoints()
	 * @see #getGraphEdge()
	 * @generated
	 */
	EReference getGraphEdge_Waypoints();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.Diagram <em>Diagram</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Diagram</em>'.
	 * @see org.eclipse.epf.uma.Diagram
	 * @generated
	 */
	EClass getDiagram();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.Diagram#getZoom <em>Zoom</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Zoom</em>'.
	 * @see org.eclipse.epf.uma.Diagram#getZoom()
	 * @see #getDiagram()
	 * @generated
	 */
	EAttribute getDiagram_Zoom();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.epf.uma.Diagram#getViewpoint <em>Viewpoint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Viewpoint</em>'.
	 * @see org.eclipse.epf.uma.Diagram#getViewpoint()
	 * @see #getDiagram()
	 * @generated
	 */
	EReference getDiagram_Viewpoint();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epf.uma.Diagram#getDiagramLink <em>Diagram Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Diagram Link</em>'.
	 * @see org.eclipse.epf.uma.Diagram#getDiagramLink()
	 * @see #getDiagram()
	 * @generated
	 */
	EReference getDiagram_DiagramLink();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.epf.uma.Diagram#getNamespace <em>Namespace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Namespace</em>'.
	 * @see org.eclipse.epf.uma.Diagram#getNamespace()
	 * @see #getDiagram()
	 * @generated
	 */
	EReference getDiagram_Namespace();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.GraphNode <em>Graph Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Graph Node</em>'.
	 * @see org.eclipse.epf.uma.GraphNode
	 * @generated
	 */
	EClass getGraphNode();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.epf.uma.GraphNode#getSize <em>Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Size</em>'.
	 * @see org.eclipse.epf.uma.GraphNode#getSize()
	 * @see #getGraphNode()
	 * @generated
	 */
	EReference getGraphNode_Size();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.SimpleSemanticModelElement <em>Simple Semantic Model Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Simple Semantic Model Element</em>'.
	 * @see org.eclipse.epf.uma.SimpleSemanticModelElement
	 * @generated
	 */
	EClass getSimpleSemanticModelElement();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.SimpleSemanticModelElement#getTypeInfo <em>Type Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type Info</em>'.
	 * @see org.eclipse.epf.uma.SimpleSemanticModelElement#getTypeInfo()
	 * @see #getSimpleSemanticModelElement()
	 * @generated
	 */
	EAttribute getSimpleSemanticModelElement_TypeInfo();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.UMASemanticModelBridge <em>UMA Semantic Model Bridge</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>UMA Semantic Model Bridge</em>'.
	 * @see org.eclipse.epf.uma.UMASemanticModelBridge
	 * @generated
	 */
	EClass getUMASemanticModelBridge();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.epf.uma.UMASemanticModelBridge#getElement <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Element</em>'.
	 * @see org.eclipse.epf.uma.UMASemanticModelBridge#getElement()
	 * @see #getUMASemanticModelBridge()
	 * @generated
	 */
	EReference getUMASemanticModelBridge_Element();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.CoreSemanticModelBridge <em>Core Semantic Model Bridge</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Core Semantic Model Bridge</em>'.
	 * @see org.eclipse.epf.uma.CoreSemanticModelBridge
	 * @generated
	 */
	EClass getCoreSemanticModelBridge();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.epf.uma.CoreSemanticModelBridge#getElement <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Element</em>'.
	 * @see org.eclipse.epf.uma.CoreSemanticModelBridge#getElement()
	 * @see #getCoreSemanticModelBridge()
	 * @generated
	 */
	EReference getCoreSemanticModelBridge_Element();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.LeafElement <em>Leaf Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Leaf Element</em>'.
	 * @see org.eclipse.epf.uma.LeafElement
	 * @generated
	 */
	EClass getLeafElement();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.TextElement <em>Text Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Text Element</em>'.
	 * @see org.eclipse.epf.uma.TextElement
	 * @generated
	 */
	EClass getTextElement();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.TextElement#getText <em>Text</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text</em>'.
	 * @see org.eclipse.epf.uma.TextElement#getText()
	 * @see #getTextElement()
	 * @generated
	 */
	EAttribute getTextElement_Text();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.Image <em>Image</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Image</em>'.
	 * @see org.eclipse.epf.uma.Image
	 * @generated
	 */
	EClass getImage();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.Image#getUri <em>Uri</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Uri</em>'.
	 * @see org.eclipse.epf.uma.Image#getUri()
	 * @see #getImage()
	 * @generated
	 */
	EAttribute getImage_Uri();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.Image#getMimeType <em>Mime Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mime Type</em>'.
	 * @see org.eclipse.epf.uma.Image#getMimeType()
	 * @see #getImage()
	 * @generated
	 */
	EAttribute getImage_MimeType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.GraphicPrimitive <em>Graphic Primitive</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Graphic Primitive</em>'.
	 * @see org.eclipse.epf.uma.GraphicPrimitive
	 * @generated
	 */
	EClass getGraphicPrimitive();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.Polyline <em>Polyline</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Polyline</em>'.
	 * @see org.eclipse.epf.uma.Polyline
	 * @generated
	 */
	EClass getPolyline();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.Polyline#getClosed <em>Closed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Closed</em>'.
	 * @see org.eclipse.epf.uma.Polyline#getClosed()
	 * @see #getPolyline()
	 * @generated
	 */
	EAttribute getPolyline_Closed();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.uma.Polyline#getWaypoints <em>Waypoints</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Waypoints</em>'.
	 * @see org.eclipse.epf.uma.Polyline#getWaypoints()
	 * @see #getPolyline()
	 * @generated
	 */
	EReference getPolyline_Waypoints();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.uma.Ellipse <em>Ellipse</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ellipse</em>'.
	 * @see org.eclipse.epf.uma.Ellipse
	 * @generated
	 */
	EClass getEllipse();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.Ellipse#getRadiusX <em>Radius X</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Radius X</em>'.
	 * @see org.eclipse.epf.uma.Ellipse#getRadiusX()
	 * @see #getEllipse()
	 * @generated
	 */
	EAttribute getEllipse_RadiusX();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.Ellipse#getRadiusY <em>Radius Y</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Radius Y</em>'.
	 * @see org.eclipse.epf.uma.Ellipse#getRadiusY()
	 * @see #getEllipse()
	 * @generated
	 */
	EAttribute getEllipse_RadiusY();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.Ellipse#getRotation <em>Rotation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rotation</em>'.
	 * @see org.eclipse.epf.uma.Ellipse#getRotation()
	 * @see #getEllipse()
	 * @generated
	 */
	EAttribute getEllipse_Rotation();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.Ellipse#getStartAngle <em>Start Angle</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Start Angle</em>'.
	 * @see org.eclipse.epf.uma.Ellipse#getStartAngle()
	 * @see #getEllipse()
	 * @generated
	 */
	EAttribute getEllipse_StartAngle();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.uma.Ellipse#getEndAngle <em>End Angle</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>End Angle</em>'.
	 * @see org.eclipse.epf.uma.Ellipse#getEndAngle()
	 * @see #getEllipse()
	 * @generated
	 */
	EAttribute getEllipse_EndAngle();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.epf.uma.Ellipse#getCenter <em>Center</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Center</em>'.
	 * @see org.eclipse.epf.uma.Ellipse#getCenter()
	 * @see #getEllipse()
	 * @generated
	 */
	EReference getEllipse_Center();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.epf.uma.WorkOrderType <em>Work Order Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Work Order Type</em>'.
	 * @see org.eclipse.epf.uma.WorkOrderType
	 * @generated
	 */
	EEnum getWorkOrderType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.epf.uma.VariabilityType <em>Variability Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Variability Type</em>'.
	 * @see org.eclipse.epf.uma.VariabilityType
	 * @generated
	 */
	EEnum getVariabilityType();

	/**
	 * Returns the meta object for data type '{@link java.util.Date <em>Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Date</em>'.
	 * @see java.util.Date
	 * @model instanceClass="java.util.Date"
	 * @generated
	 */
	EDataType getDate();

	/**
	 * Returns the meta object for data type '{@link java.net.URI <em>Uri</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Uri</em>'.
	 * @see java.net.URI
	 * @model instanceClass="java.net.URI"
	 * @generated
	 */
	EDataType getUri();

	/**
	 * Returns the meta object for data type '{@link java.lang.String <em>String</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>String</em>'.
	 * @see java.lang.String
	 * @model instanceClass="java.lang.String"
	 * @generated
	 */
	EDataType getString();

	/**
	 * Returns the meta object for data type '{@link java.lang.Boolean <em>Boolean</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Boolean</em>'.
	 * @see java.lang.Boolean
	 * @model instanceClass="java.lang.Boolean"
	 * @generated
	 */
	EDataType getBoolean();

	/**
	 * Returns the meta object for data type '{@link java.util.Set <em>Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Set</em>'.
	 * @see java.util.Set
	 * @model instanceClass="java.util.Set"
	 * @generated
	 */
	EDataType getSet();

	/**
	 * Returns the meta object for data type '{@link java.util.List <em>Sequence</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Sequence</em>'.
	 * @see java.util.List
	 * @model instanceClass="java.util.List"
	 * @generated
	 */
	EDataType getSequence();

	/**
	 * Returns the meta object for data type '<em>Integer</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Integer</em>'.
	 * @model instanceClass="int"
	 * @generated
	 */
	EDataType getInteger();

	/**
	 * Returns the meta object for data type '{@link java.lang.Double <em>Double</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Double</em>'.
	 * @see java.lang.Double
	 * @model instanceClass="java.lang.Double"
	 * @generated
	 */
	EDataType getDouble();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	UmaFactory getUmaFactory();

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
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.ClassifierImpl <em>Classifier</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.ClassifierImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getClassifier()
		 * @generated
		 */
		EClass CLASSIFIER = eINSTANCE.getClassifier();

		/**
		 * The meta object literal for the '<em><b>Is Abstract</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASSIFIER__IS_ABSTRACT = eINSTANCE
				.getClassifier_IsAbstract();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.TypeImpl <em>Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.TypeImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getType()
		 * @generated
		 */
		EClass TYPE = eINSTANCE.getType();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.ElementImpl <em>Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.ElementImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getElement()
		 * @generated
		 */
		EClass ELEMENT = eINSTANCE.getElement();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.NamedElementImpl <em>Named Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.NamedElementImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getNamedElement()
		 * @generated
		 */
		EClass NAMED_ELEMENT = eINSTANCE.getNamedElement();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAMED_ELEMENT__NAME = eINSTANCE.getNamedElement_Name();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.PackageableElementImpl <em>Packageable Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.PackageableElementImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getPackageableElement()
		 * @generated
		 */
		EClass PACKAGEABLE_ELEMENT = eINSTANCE.getPackageableElement();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.PackageImpl <em>Package</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.PackageImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getPackage()
		 * @generated
		 */
		EClass PACKAGE = eINSTANCE.getPackage();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.NamespaceImpl <em>Namespace</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.NamespaceImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getNamespace()
		 * @generated
		 */
		EClass NAMESPACE = eINSTANCE.getNamespace();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.MethodElementImpl <em>Method Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.MethodElementImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getMethodElement()
		 * @generated
		 */
		EClass METHOD_ELEMENT = eINSTANCE.getMethodElement();

		/**
		 * The meta object literal for the '<em><b>Guid</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METHOD_ELEMENT__GUID = eINSTANCE.getMethodElement_Guid();

		/**
		 * The meta object literal for the '<em><b>Presentation Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METHOD_ELEMENT__PRESENTATION_NAME = eINSTANCE
				.getMethodElement_PresentationName();

		/**
		 * The meta object literal for the '<em><b>Brief Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METHOD_ELEMENT__BRIEF_DESCRIPTION = eINSTANCE
				.getMethodElement_BriefDescription();

		/**
		 * The meta object literal for the '<em><b>Owned Rules</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METHOD_ELEMENT__OWNED_RULES = eINSTANCE
				.getMethodElement_OwnedRules();

		/**
		 * The meta object literal for the '<em><b>Method Element Property</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METHOD_ELEMENT__METHOD_ELEMENT_PROPERTY = eINSTANCE
				.getMethodElement_MethodElementProperty();

		/**
		 * The meta object literal for the '<em><b>Kind</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METHOD_ELEMENT__KIND = eINSTANCE.getMethodElement_Kind();

		/**
		 * The meta object literal for the '<em><b>Suppressed</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METHOD_ELEMENT__SUPPRESSED = eINSTANCE
				.getMethodElement_Suppressed();

		/**
		 * The meta object literal for the '<em><b>Ordering Guide</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METHOD_ELEMENT__ORDERING_GUIDE = eINSTANCE
				.getMethodElement_OrderingGuide();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.ConstraintImpl <em>Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.ConstraintImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getConstraint()
		 * @generated
		 */
		EClass CONSTRAINT = eINSTANCE.getConstraint();

		/**
		 * The meta object literal for the '<em><b>Body</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONSTRAINT__BODY = eINSTANCE.getConstraint_Body();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.MethodElementPropertyImpl <em>Method Element Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.MethodElementPropertyImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getMethodElementProperty()
		 * @generated
		 */
		EClass METHOD_ELEMENT_PROPERTY = eINSTANCE.getMethodElementProperty();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METHOD_ELEMENT_PROPERTY__VALUE = eINSTANCE
				.getMethodElementProperty_Value();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.KindImpl <em>Kind</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.KindImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getKind()
		 * @generated
		 */
		EClass KIND = eINSTANCE.getKind();

		/**
		 * The meta object literal for the '<em><b>Applicable Meta Class Info</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference KIND__APPLICABLE_META_CLASS_INFO = eINSTANCE
				.getKind_ApplicableMetaClassInfo();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.ContentElementImpl <em>Content Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.ContentElementImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getContentElement()
		 * @generated
		 */
		EClass CONTENT_ELEMENT = eINSTANCE.getContentElement();

		/**
		 * The meta object literal for the '<em><b>Supporting Materials</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTENT_ELEMENT__SUPPORTING_MATERIALS = eINSTANCE
				.getContentElement_SupportingMaterials();

		/**
		 * The meta object literal for the '<em><b>Concepts And Papers</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTENT_ELEMENT__CONCEPTS_AND_PAPERS = eINSTANCE
				.getContentElement_ConceptsAndPapers();

		/**
		 * The meta object literal for the '<em><b>Checklists</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTENT_ELEMENT__CHECKLISTS = eINSTANCE
				.getContentElement_Checklists();

		/**
		 * The meta object literal for the '<em><b>Guidelines</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTENT_ELEMENT__GUIDELINES = eINSTANCE
				.getContentElement_Guidelines();

		/**
		 * The meta object literal for the '<em><b>Examples</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTENT_ELEMENT__EXAMPLES = eINSTANCE
				.getContentElement_Examples();

		/**
		 * The meta object literal for the '<em><b>Assets</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTENT_ELEMENT__ASSETS = eINSTANCE
				.getContentElement_Assets();

		/**
		 * The meta object literal for the '<em><b>Termdefinition</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTENT_ELEMENT__TERMDEFINITION = eINSTANCE
				.getContentElement_Termdefinition();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.DescribableElementImpl <em>Describable Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.DescribableElementImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getDescribableElement()
		 * @generated
		 */
		EClass DESCRIBABLE_ELEMENT = eINSTANCE.getDescribableElement();

		/**
		 * The meta object literal for the '<em><b>Presentation</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DESCRIBABLE_ELEMENT__PRESENTATION = eINSTANCE
				.getDescribableElement_Presentation();

		/**
		 * The meta object literal for the '<em><b>Shapeicon</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DESCRIBABLE_ELEMENT__SHAPEICON = eINSTANCE
				.getDescribableElement_Shapeicon();

		/**
		 * The meta object literal for the '<em><b>Nodeicon</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DESCRIBABLE_ELEMENT__NODEICON = eINSTANCE
				.getDescribableElement_Nodeicon();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.ContentDescriptionImpl <em>Content Description</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.ContentDescriptionImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getContentDescription()
		 * @generated
		 */
		EClass CONTENT_DESCRIPTION = eINSTANCE.getContentDescription();

		/**
		 * The meta object literal for the '<em><b>Main Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTENT_DESCRIPTION__MAIN_DESCRIPTION = eINSTANCE
				.getContentDescription_MainDescription();

		/**
		 * The meta object literal for the '<em><b>Sections</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTENT_DESCRIPTION__SECTIONS = eINSTANCE
				.getContentDescription_Sections();

		/**
		 * The meta object literal for the '<em><b>External Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTENT_DESCRIPTION__EXTERNAL_ID = eINSTANCE
				.getContentDescription_ExternalId();

		/**
		 * The meta object literal for the '<em><b>Key Considerations</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTENT_DESCRIPTION__KEY_CONSIDERATIONS = eINSTANCE
				.getContentDescription_KeyConsiderations();

		/**
		 * The meta object literal for the '<em><b>Long Presentation Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTENT_DESCRIPTION__LONG_PRESENTATION_NAME = eINSTANCE
				.getContentDescription_LongPresentationName();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.SectionImpl <em>Section</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.SectionImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getSection()
		 * @generated
		 */
		EClass SECTION = eINSTANCE.getSection();

		/**
		 * The meta object literal for the '<em><b>Section Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SECTION__SECTION_NAME = eINSTANCE.getSection_SectionName();

		/**
		 * The meta object literal for the '<em><b>Section Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SECTION__SECTION_DESCRIPTION = eINSTANCE
				.getSection_SectionDescription();

		/**
		 * The meta object literal for the '<em><b>Sub Sections</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SECTION__SUB_SECTIONS = eINSTANCE.getSection_SubSections();

		/**
		 * The meta object literal for the '<em><b>Predecessor</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SECTION__PREDECESSOR = eINSTANCE.getSection_Predecessor();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.RoleImpl <em>Role</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.RoleImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getRole()
		 * @generated
		 */
		EClass ROLE = eINSTANCE.getRole();

		/**
		 * The meta object literal for the '<em><b>Modifies</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROLE__MODIFIES = eINSTANCE.getRole_Modifies();

		/**
		 * The meta object literal for the '<em><b>Responsible For</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROLE__RESPONSIBLE_FOR = eINSTANCE.getRole_ResponsibleFor();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.WorkProductImpl <em>Work Product</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.WorkProductImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getWorkProduct()
		 * @generated
		 */
		EClass WORK_PRODUCT = eINSTANCE.getWorkProduct();

		/**
		 * The meta object literal for the '<em><b>Reports</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORK_PRODUCT__REPORTS = eINSTANCE.getWorkProduct_Reports();

		/**
		 * The meta object literal for the '<em><b>Templates</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORK_PRODUCT__TEMPLATES = eINSTANCE
				.getWorkProduct_Templates();

		/**
		 * The meta object literal for the '<em><b>Tool Mentors</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORK_PRODUCT__TOOL_MENTORS = eINSTANCE
				.getWorkProduct_ToolMentors();

		/**
		 * The meta object literal for the '<em><b>Estimation Considerations</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORK_PRODUCT__ESTIMATION_CONSIDERATIONS = eINSTANCE
				.getWorkProduct_EstimationConsiderations();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.FulfillableElementImpl <em>Fulfillable Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.FulfillableElementImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getFulfillableElement()
		 * @generated
		 */
		EClass FULFILLABLE_ELEMENT = eINSTANCE.getFulfillableElement();

		/**
		 * The meta object literal for the '<em><b>Fulfills</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FULFILLABLE_ELEMENT__FULFILLS = eINSTANCE
				.getFulfillableElement_Fulfills();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.TaskImpl <em>Task</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.TaskImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getTask()
		 * @generated
		 */
		EClass TASK = eINSTANCE.getTask();

		/**
		 * The meta object literal for the '<em><b>Performed By</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TASK__PERFORMED_BY = eINSTANCE.getTask_PerformedBy();

		/**
		 * The meta object literal for the '<em><b>Mandatory Input</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TASK__MANDATORY_INPUT = eINSTANCE.getTask_MandatoryInput();

		/**
		 * The meta object literal for the '<em><b>Output</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TASK__OUTPUT = eINSTANCE.getTask_Output();

		/**
		 * The meta object literal for the '<em><b>Additionally Performed By</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TASK__ADDITIONALLY_PERFORMED_BY = eINSTANCE
				.getTask_AdditionallyPerformedBy();

		/**
		 * The meta object literal for the '<em><b>Optional Input</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TASK__OPTIONAL_INPUT = eINSTANCE.getTask_OptionalInput();

		/**
		 * The meta object literal for the '<em><b>Steps</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TASK__STEPS = eINSTANCE.getTask_Steps();

		/**
		 * The meta object literal for the '<em><b>Tool Mentors</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TASK__TOOL_MENTORS = eINSTANCE.getTask_ToolMentors();

		/**
		 * The meta object literal for the '<em><b>Estimation Considerations</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TASK__ESTIMATION_CONSIDERATIONS = eINSTANCE
				.getTask_EstimationConsiderations();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.WorkDefinitionImpl <em>Work Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.WorkDefinitionImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getWorkDefinition()
		 * @generated
		 */
		EClass WORK_DEFINITION = eINSTANCE.getWorkDefinition();

		/**
		 * The meta object literal for the '<em><b>Precondition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORK_DEFINITION__PRECONDITION = eINSTANCE
				.getWorkDefinition_Precondition();

		/**
		 * The meta object literal for the '<em><b>Postcondition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORK_DEFINITION__POSTCONDITION = eINSTANCE
				.getWorkDefinition_Postcondition();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.StepImpl <em>Step</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.StepImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getStep()
		 * @generated
		 */
		EClass STEP = eINSTANCE.getStep();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.GuidanceImpl <em>Guidance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.GuidanceImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getGuidance()
		 * @generated
		 */
		EClass GUIDANCE = eINSTANCE.getGuidance();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.ArtifactImpl <em>Artifact</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.ArtifactImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getArtifact()
		 * @generated
		 */
		EClass ARTIFACT = eINSTANCE.getArtifact();

		/**
		 * The meta object literal for the '<em><b>Container Artifact</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARTIFACT__CONTAINER_ARTIFACT = eINSTANCE
				.getArtifact_ContainerArtifact();

		/**
		 * The meta object literal for the '<em><b>Contained Artifacts</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARTIFACT__CONTAINED_ARTIFACTS = eINSTANCE
				.getArtifact_ContainedArtifacts();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.DeliverableImpl <em>Deliverable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.DeliverableImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getDeliverable()
		 * @generated
		 */
		EClass DELIVERABLE = eINSTANCE.getDeliverable();

		/**
		 * The meta object literal for the '<em><b>Delivered Work Products</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DELIVERABLE__DELIVERED_WORK_PRODUCTS = eINSTANCE
				.getDeliverable_DeliveredWorkProducts();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.OutcomeImpl <em>Outcome</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.OutcomeImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getOutcome()
		 * @generated
		 */
		EClass OUTCOME = eINSTANCE.getOutcome();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.MethodPackageImpl <em>Method Package</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.MethodPackageImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getMethodPackage()
		 * @generated
		 */
		EClass METHOD_PACKAGE = eINSTANCE.getMethodPackage();

		/**
		 * The meta object literal for the '<em><b>Global</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METHOD_PACKAGE__GLOBAL = eINSTANCE.getMethodPackage_Global();

		/**
		 * The meta object literal for the '<em><b>Reused Packages</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METHOD_PACKAGE__REUSED_PACKAGES = eINSTANCE
				.getMethodPackage_ReusedPackages();

		/**
		 * The meta object literal for the '<em><b>Child Packages</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METHOD_PACKAGE__CHILD_PACKAGES = eINSTANCE
				.getMethodPackage_ChildPackages();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.ContentPackageImpl <em>Content Package</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.ContentPackageImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getContentPackage()
		 * @generated
		 */
		EClass CONTENT_PACKAGE = eINSTANCE.getContentPackage();

		/**
		 * The meta object literal for the '<em><b>Content Elements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTENT_PACKAGE__CONTENT_ELEMENTS = eINSTANCE
				.getContentPackage_ContentElements();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.ArtifactDescriptionImpl <em>Artifact Description</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.ArtifactDescriptionImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getArtifactDescription()
		 * @generated
		 */
		EClass ARTIFACT_DESCRIPTION = eINSTANCE.getArtifactDescription();

		/**
		 * The meta object literal for the '<em><b>Brief Outline</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARTIFACT_DESCRIPTION__BRIEF_OUTLINE = eINSTANCE
				.getArtifactDescription_BriefOutline();

		/**
		 * The meta object literal for the '<em><b>Representation Options</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARTIFACT_DESCRIPTION__REPRESENTATION_OPTIONS = eINSTANCE
				.getArtifactDescription_RepresentationOptions();

		/**
		 * The meta object literal for the '<em><b>Representation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARTIFACT_DESCRIPTION__REPRESENTATION = eINSTANCE
				.getArtifactDescription_Representation();

		/**
		 * The meta object literal for the '<em><b>Notation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARTIFACT_DESCRIPTION__NOTATION = eINSTANCE
				.getArtifactDescription_Notation();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.WorkProductDescriptionImpl <em>Work Product Description</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.WorkProductDescriptionImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getWorkProductDescription()
		 * @generated
		 */
		EClass WORK_PRODUCT_DESCRIPTION = eINSTANCE.getWorkProductDescription();

		/**
		 * The meta object literal for the '<em><b>Purpose</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_PRODUCT_DESCRIPTION__PURPOSE = eINSTANCE
				.getWorkProductDescription_Purpose();

		/**
		 * The meta object literal for the '<em><b>Impact Of Not Having</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_PRODUCT_DESCRIPTION__IMPACT_OF_NOT_HAVING = eINSTANCE
				.getWorkProductDescription_ImpactOfNotHaving();

		/**
		 * The meta object literal for the '<em><b>Reasons For Not Needing</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_PRODUCT_DESCRIPTION__REASONS_FOR_NOT_NEEDING = eINSTANCE
				.getWorkProductDescription_ReasonsForNotNeeding();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.DeliverableDescriptionImpl <em>Deliverable Description</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.DeliverableDescriptionImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getDeliverableDescription()
		 * @generated
		 */
		EClass DELIVERABLE_DESCRIPTION = eINSTANCE.getDeliverableDescription();

		/**
		 * The meta object literal for the '<em><b>External Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DELIVERABLE_DESCRIPTION__EXTERNAL_DESCRIPTION = eINSTANCE
				.getDeliverableDescription_ExternalDescription();

		/**
		 * The meta object literal for the '<em><b>Packaging Guidance</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DELIVERABLE_DESCRIPTION__PACKAGING_GUIDANCE = eINSTANCE
				.getDeliverableDescription_PackagingGuidance();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.RoleDescriptionImpl <em>Role Description</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.RoleDescriptionImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getRoleDescription()
		 * @generated
		 */
		EClass ROLE_DESCRIPTION = eINSTANCE.getRoleDescription();

		/**
		 * The meta object literal for the '<em><b>Skills</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ROLE_DESCRIPTION__SKILLS = eINSTANCE
				.getRoleDescription_Skills();

		/**
		 * The meta object literal for the '<em><b>Assignment Approaches</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ROLE_DESCRIPTION__ASSIGNMENT_APPROACHES = eINSTANCE
				.getRoleDescription_AssignmentApproaches();

		/**
		 * The meta object literal for the '<em><b>Synonyms</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ROLE_DESCRIPTION__SYNONYMS = eINSTANCE
				.getRoleDescription_Synonyms();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.TaskDescriptionImpl <em>Task Description</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.TaskDescriptionImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getTaskDescription()
		 * @generated
		 */
		EClass TASK_DESCRIPTION = eINSTANCE.getTaskDescription();

		/**
		 * The meta object literal for the '<em><b>Purpose</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK_DESCRIPTION__PURPOSE = eINSTANCE
				.getTaskDescription_Purpose();

		/**
		 * The meta object literal for the '<em><b>Alternatives</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK_DESCRIPTION__ALTERNATIVES = eINSTANCE
				.getTaskDescription_Alternatives();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.GuidanceDescriptionImpl <em>Guidance Description</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.GuidanceDescriptionImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getGuidanceDescription()
		 * @generated
		 */
		EClass GUIDANCE_DESCRIPTION = eINSTANCE.getGuidanceDescription();

		/**
		 * The meta object literal for the '<em><b>Attachments</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GUIDANCE_DESCRIPTION__ATTACHMENTS = eINSTANCE
				.getGuidanceDescription_Attachments();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.PracticeDescriptionImpl <em>Practice Description</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.PracticeDescriptionImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getPracticeDescription()
		 * @generated
		 */
		EClass PRACTICE_DESCRIPTION = eINSTANCE.getPracticeDescription();

		/**
		 * The meta object literal for the '<em><b>Additional Info</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRACTICE_DESCRIPTION__ADDITIONAL_INFO = eINSTANCE
				.getPracticeDescription_AdditionalInfo();

		/**
		 * The meta object literal for the '<em><b>Problem</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRACTICE_DESCRIPTION__PROBLEM = eINSTANCE
				.getPracticeDescription_Problem();

		/**
		 * The meta object literal for the '<em><b>Background</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRACTICE_DESCRIPTION__BACKGROUND = eINSTANCE
				.getPracticeDescription_Background();

		/**
		 * The meta object literal for the '<em><b>Goals</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRACTICE_DESCRIPTION__GOALS = eINSTANCE
				.getPracticeDescription_Goals();

		/**
		 * The meta object literal for the '<em><b>Application</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRACTICE_DESCRIPTION__APPLICATION = eINSTANCE
				.getPracticeDescription_Application();

		/**
		 * The meta object literal for the '<em><b>Levels Of Adoption</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRACTICE_DESCRIPTION__LEVELS_OF_ADOPTION = eINSTANCE
				.getPracticeDescription_LevelsOfAdoption();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.ActivityImpl <em>Activity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.ActivityImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getActivity()
		 * @generated
		 */
		EClass ACTIVITY = eINSTANCE.getActivity();

		/**
		 * The meta object literal for the '<em><b>Breakdown Elements</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTIVITY__BREAKDOWN_ELEMENTS = eINSTANCE
				.getActivity_BreakdownElements();

		/**
		 * The meta object literal for the '<em><b>Roadmaps</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTIVITY__ROADMAPS = eINSTANCE.getActivity_Roadmaps();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.WorkBreakdownElementImpl <em>Work Breakdown Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.WorkBreakdownElementImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getWorkBreakdownElement()
		 * @generated
		 */
		EClass WORK_BREAKDOWN_ELEMENT = eINSTANCE.getWorkBreakdownElement();

		/**
		 * The meta object literal for the '<em><b>Is Repeatable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_BREAKDOWN_ELEMENT__IS_REPEATABLE = eINSTANCE
				.getWorkBreakdownElement_IsRepeatable();

		/**
		 * The meta object literal for the '<em><b>Is Ongoing</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_BREAKDOWN_ELEMENT__IS_ONGOING = eINSTANCE
				.getWorkBreakdownElement_IsOngoing();

		/**
		 * The meta object literal for the '<em><b>Is Event Driven</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_BREAKDOWN_ELEMENT__IS_EVENT_DRIVEN = eINSTANCE
				.getWorkBreakdownElement_IsEventDriven();

		/**
		 * The meta object literal for the '<em><b>Link To Predecessor</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORK_BREAKDOWN_ELEMENT__LINK_TO_PREDECESSOR = eINSTANCE
				.getWorkBreakdownElement_LinkToPredecessor();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.BreakdownElementImpl <em>Breakdown Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.BreakdownElementImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getBreakdownElement()
		 * @generated
		 */
		EClass BREAKDOWN_ELEMENT = eINSTANCE.getBreakdownElement();

		/**
		 * The meta object literal for the '<em><b>Prefix</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BREAKDOWN_ELEMENT__PREFIX = eINSTANCE
				.getBreakdownElement_Prefix();

		/**
		 * The meta object literal for the '<em><b>Is Planned</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BREAKDOWN_ELEMENT__IS_PLANNED = eINSTANCE
				.getBreakdownElement_IsPlanned();

		/**
		 * The meta object literal for the '<em><b>Has Multiple Occurrences</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BREAKDOWN_ELEMENT__HAS_MULTIPLE_OCCURRENCES = eINSTANCE
				.getBreakdownElement_HasMultipleOccurrences();

		/**
		 * The meta object literal for the '<em><b>Is Optional</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BREAKDOWN_ELEMENT__IS_OPTIONAL = eINSTANCE
				.getBreakdownElement_IsOptional();

		/**
		 * The meta object literal for the '<em><b>Presented After</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BREAKDOWN_ELEMENT__PRESENTED_AFTER = eINSTANCE
				.getBreakdownElement_PresentedAfter();

		/**
		 * The meta object literal for the '<em><b>Presented Before</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BREAKDOWN_ELEMENT__PRESENTED_BEFORE = eINSTANCE
				.getBreakdownElement_PresentedBefore();

		/**
		 * The meta object literal for the '<em><b>Planning Data</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BREAKDOWN_ELEMENT__PLANNING_DATA = eINSTANCE
				.getBreakdownElement_PlanningData();

		/**
		 * The meta object literal for the '<em><b>Super Activities</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BREAKDOWN_ELEMENT__SUPER_ACTIVITIES = eINSTANCE
				.getBreakdownElement_SuperActivities();

		/**
		 * The meta object literal for the '<em><b>Checklists</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BREAKDOWN_ELEMENT__CHECKLISTS = eINSTANCE
				.getBreakdownElement_Checklists();

		/**
		 * The meta object literal for the '<em><b>Concepts</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BREAKDOWN_ELEMENT__CONCEPTS = eINSTANCE
				.getBreakdownElement_Concepts();

		/**
		 * The meta object literal for the '<em><b>Examples</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BREAKDOWN_ELEMENT__EXAMPLES = eINSTANCE
				.getBreakdownElement_Examples();

		/**
		 * The meta object literal for the '<em><b>Guidelines</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BREAKDOWN_ELEMENT__GUIDELINES = eINSTANCE
				.getBreakdownElement_Guidelines();

		/**
		 * The meta object literal for the '<em><b>Reusable Assets</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BREAKDOWN_ELEMENT__REUSABLE_ASSETS = eINSTANCE
				.getBreakdownElement_ReusableAssets();

		/**
		 * The meta object literal for the '<em><b>Supporting Materials</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BREAKDOWN_ELEMENT__SUPPORTING_MATERIALS = eINSTANCE
				.getBreakdownElement_SupportingMaterials();

		/**
		 * The meta object literal for the '<em><b>Templates</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BREAKDOWN_ELEMENT__TEMPLATES = eINSTANCE
				.getBreakdownElement_Templates();

		/**
		 * The meta object literal for the '<em><b>Reports</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BREAKDOWN_ELEMENT__REPORTS = eINSTANCE
				.getBreakdownElement_Reports();

		/**
		 * The meta object literal for the '<em><b>Estimationconsiderations</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BREAKDOWN_ELEMENT__ESTIMATIONCONSIDERATIONS = eINSTANCE
				.getBreakdownElement_Estimationconsiderations();

		/**
		 * The meta object literal for the '<em><b>Toolmentor</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BREAKDOWN_ELEMENT__TOOLMENTOR = eINSTANCE
				.getBreakdownElement_Toolmentor();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.MilestoneImpl <em>Milestone</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.MilestoneImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getMilestone()
		 * @generated
		 */
		EClass MILESTONE = eINSTANCE.getMilestone();

		/**
		 * The meta object literal for the '<em><b>Required Results</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MILESTONE__REQUIRED_RESULTS = eINSTANCE
				.getMilestone_RequiredResults();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.IterationImpl <em>Iteration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.IterationImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getIteration()
		 * @generated
		 */
		EClass ITERATION = eINSTANCE.getIteration();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.PhaseImpl <em>Phase</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.PhaseImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getPhase()
		 * @generated
		 */
		EClass PHASE = eINSTANCE.getPhase();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.TeamProfileImpl <em>Team Profile</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.TeamProfileImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getTeamProfile()
		 * @generated
		 */
		EClass TEAM_PROFILE = eINSTANCE.getTeamProfile();

		/**
		 * The meta object literal for the '<em><b>Team Roles</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEAM_PROFILE__TEAM_ROLES = eINSTANCE
				.getTeamProfile_TeamRoles();

		/**
		 * The meta object literal for the '<em><b>Super Team</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEAM_PROFILE__SUPER_TEAM = eINSTANCE
				.getTeamProfile_SuperTeam();

		/**
		 * The meta object literal for the '<em><b>Sub Team</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEAM_PROFILE__SUB_TEAM = eINSTANCE.getTeamProfile_SubTeam();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.RoleDescriptorImpl <em>Role Descriptor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.RoleDescriptorImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getRoleDescriptor()
		 * @generated
		 */
		EClass ROLE_DESCRIPTOR = eINSTANCE.getRoleDescriptor();

		/**
		 * The meta object literal for the '<em><b>Role</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROLE_DESCRIPTOR__ROLE = eINSTANCE.getRoleDescriptor_Role();

		/**
		 * The meta object literal for the '<em><b>Modifies</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROLE_DESCRIPTOR__MODIFIES = eINSTANCE
				.getRoleDescriptor_Modifies();

		/**
		 * The meta object literal for the '<em><b>Responsible For</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROLE_DESCRIPTOR__RESPONSIBLE_FOR = eINSTANCE
				.getRoleDescriptor_ResponsibleFor();

		/**
		 * The meta object literal for the '<em><b>Responsible For Exclude</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROLE_DESCRIPTOR__RESPONSIBLE_FOR_EXCLUDE = eINSTANCE
				.getRoleDescriptor_ResponsibleForExclude();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.WorkOrderImpl <em>Work Order</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.WorkOrderImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getWorkOrder()
		 * @generated
		 */
		EClass WORK_ORDER = eINSTANCE.getWorkOrder();

		/**
		 * The meta object literal for the '<em><b>Link Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_ORDER__LINK_TYPE = eINSTANCE.getWorkOrder_LinkType();

		/**
		 * The meta object literal for the '<em><b>Pred</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORK_ORDER__PRED = eINSTANCE.getWorkOrder_Pred();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.ProcessElementImpl <em>Process Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.ProcessElementImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getProcessElement()
		 * @generated
		 */
		EClass PROCESS_ELEMENT = eINSTANCE.getProcessElement();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.PlanningDataImpl <em>Planning Data</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.PlanningDataImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getPlanningData()
		 * @generated
		 */
		EClass PLANNING_DATA = eINSTANCE.getPlanningData();

		/**
		 * The meta object literal for the '<em><b>Start Date</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PLANNING_DATA__START_DATE = eINSTANCE
				.getPlanningData_StartDate();

		/**
		 * The meta object literal for the '<em><b>Finish Date</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PLANNING_DATA__FINISH_DATE = eINSTANCE
				.getPlanningData_FinishDate();

		/**
		 * The meta object literal for the '<em><b>Rank</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PLANNING_DATA__RANK = eINSTANCE.getPlanningData_Rank();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.DescriptorImpl <em>Descriptor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.DescriptorImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getDescriptor()
		 * @generated
		 */
		EClass DESCRIPTOR = eINSTANCE.getDescriptor();

		/**
		 * The meta object literal for the '<em><b>Is Synchronized With Source</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DESCRIPTOR__IS_SYNCHRONIZED_WITH_SOURCE = eINSTANCE
				.getDescriptor_IsSynchronizedWithSource();

		/**
		 * The meta object literal for the '<em><b>Guidance Exclude</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DESCRIPTOR__GUIDANCE_EXCLUDE = eINSTANCE
				.getDescriptor_GuidanceExclude();

		/**
		 * The meta object literal for the '<em><b>Guidance Additional</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DESCRIPTOR__GUIDANCE_ADDITIONAL = eINSTANCE
				.getDescriptor_GuidanceAdditional();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.TaskDescriptorImpl <em>Task Descriptor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.TaskDescriptorImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getTaskDescriptor()
		 * @generated
		 */
		EClass TASK_DESCRIPTOR = eINSTANCE.getTaskDescriptor();

		/**
		 * The meta object literal for the '<em><b>Task</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TASK_DESCRIPTOR__TASK = eINSTANCE.getTaskDescriptor_Task();

		/**
		 * The meta object literal for the '<em><b>Additionally Performed By</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TASK_DESCRIPTOR__ADDITIONALLY_PERFORMED_BY = eINSTANCE
				.getTaskDescriptor_AdditionallyPerformedBy();

		/**
		 * The meta object literal for the '<em><b>Assisted By</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TASK_DESCRIPTOR__ASSISTED_BY = eINSTANCE
				.getTaskDescriptor_AssistedBy();

		/**
		 * The meta object literal for the '<em><b>External Input</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TASK_DESCRIPTOR__EXTERNAL_INPUT = eINSTANCE
				.getTaskDescriptor_ExternalInput();

		/**
		 * The meta object literal for the '<em><b>Mandatory Input</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TASK_DESCRIPTOR__MANDATORY_INPUT = eINSTANCE
				.getTaskDescriptor_MandatoryInput();

		/**
		 * The meta object literal for the '<em><b>Optional Input</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TASK_DESCRIPTOR__OPTIONAL_INPUT = eINSTANCE
				.getTaskDescriptor_OptionalInput();

		/**
		 * The meta object literal for the '<em><b>Output</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TASK_DESCRIPTOR__OUTPUT = eINSTANCE
				.getTaskDescriptor_Output();

		/**
		 * The meta object literal for the '<em><b>Performed Primarily By</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TASK_DESCRIPTOR__PERFORMED_PRIMARILY_BY = eINSTANCE
				.getTaskDescriptor_PerformedPrimarilyBy();

		/**
		 * The meta object literal for the '<em><b>Selected Steps</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TASK_DESCRIPTOR__SELECTED_STEPS = eINSTANCE
				.getTaskDescriptor_SelectedSteps();

		/**
		 * The meta object literal for the '<em><b>Performed Primarily By Excluded</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TASK_DESCRIPTOR__PERFORMED_PRIMARILY_BY_EXCLUDED = eINSTANCE
				.getTaskDescriptor_PerformedPrimarilyByExcluded();

		/**
		 * The meta object literal for the '<em><b>Additionally Performed By Exclude</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TASK_DESCRIPTOR__ADDITIONALLY_PERFORMED_BY_EXCLUDE = eINSTANCE
				.getTaskDescriptor_AdditionallyPerformedByExclude();

		/**
		 * The meta object literal for the '<em><b>Mandatory Input Exclude</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TASK_DESCRIPTOR__MANDATORY_INPUT_EXCLUDE = eINSTANCE
				.getTaskDescriptor_MandatoryInputExclude();

		/**
		 * The meta object literal for the '<em><b>Optional Input Exclude</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TASK_DESCRIPTOR__OPTIONAL_INPUT_EXCLUDE = eINSTANCE
				.getTaskDescriptor_OptionalInputExclude();

		/**
		 * The meta object literal for the '<em><b>Output Exclude</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TASK_DESCRIPTOR__OUTPUT_EXCLUDE = eINSTANCE
				.getTaskDescriptor_OutputExclude();

		/**
		 * The meta object literal for the '<em><b>Selected Steps Exclude</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TASK_DESCRIPTOR__SELECTED_STEPS_EXCLUDE = eINSTANCE
				.getTaskDescriptor_SelectedStepsExclude();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.WorkProductDescriptorImpl <em>Work Product Descriptor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.WorkProductDescriptorImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getWorkProductDescriptor()
		 * @generated
		 */
		EClass WORK_PRODUCT_DESCRIPTOR = eINSTANCE.getWorkProductDescriptor();

		/**
		 * The meta object literal for the '<em><b>Activity Entry State</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_PRODUCT_DESCRIPTOR__ACTIVITY_ENTRY_STATE = eINSTANCE
				.getWorkProductDescriptor_ActivityEntryState();

		/**
		 * The meta object literal for the '<em><b>Activity Exit State</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_PRODUCT_DESCRIPTOR__ACTIVITY_EXIT_STATE = eINSTANCE
				.getWorkProductDescriptor_ActivityExitState();

		/**
		 * The meta object literal for the '<em><b>Work Product</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORK_PRODUCT_DESCRIPTOR__WORK_PRODUCT = eINSTANCE
				.getWorkProductDescriptor_WorkProduct();

		/**
		 * The meta object literal for the '<em><b>Impacted By</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORK_PRODUCT_DESCRIPTOR__IMPACTED_BY = eINSTANCE
				.getWorkProductDescriptor_ImpactedBy();

		/**
		 * The meta object literal for the '<em><b>Impacts</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORK_PRODUCT_DESCRIPTOR__IMPACTS = eINSTANCE
				.getWorkProductDescriptor_Impacts();

		/**
		 * The meta object literal for the '<em><b>Deliverable Parts</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORK_PRODUCT_DESCRIPTOR__DELIVERABLE_PARTS = eINSTANCE
				.getWorkProductDescriptor_DeliverableParts();

		/**
		 * The meta object literal for the '<em><b>Deliverable Parts Exclude</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORK_PRODUCT_DESCRIPTOR__DELIVERABLE_PARTS_EXCLUDE = eINSTANCE
				.getWorkProductDescriptor_DeliverablePartsExclude();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.CompositeRoleImpl <em>Composite Role</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.CompositeRoleImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getCompositeRole()
		 * @generated
		 */
		EClass COMPOSITE_ROLE = eINSTANCE.getCompositeRole();

		/**
		 * The meta object literal for the '<em><b>Aggregated Roles</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPOSITE_ROLE__AGGREGATED_ROLES = eINSTANCE
				.getCompositeRole_AggregatedRoles();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.BreakdownElementDescriptionImpl <em>Breakdown Element Description</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.BreakdownElementDescriptionImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getBreakdownElementDescription()
		 * @generated
		 */
		EClass BREAKDOWN_ELEMENT_DESCRIPTION = eINSTANCE
				.getBreakdownElementDescription();

		/**
		 * The meta object literal for the '<em><b>Usage Guidance</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BREAKDOWN_ELEMENT_DESCRIPTION__USAGE_GUIDANCE = eINSTANCE
				.getBreakdownElementDescription_UsageGuidance();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.ActivityDescriptionImpl <em>Activity Description</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.ActivityDescriptionImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getActivityDescription()
		 * @generated
		 */
		EClass ACTIVITY_DESCRIPTION = eINSTANCE.getActivityDescription();

		/**
		 * The meta object literal for the '<em><b>Purpose</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTIVITY_DESCRIPTION__PURPOSE = eINSTANCE
				.getActivityDescription_Purpose();

		/**
		 * The meta object literal for the '<em><b>Alternatives</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTIVITY_DESCRIPTION__ALTERNATIVES = eINSTANCE
				.getActivityDescription_Alternatives();

		/**
		 * The meta object literal for the '<em><b>Howto Staff</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTIVITY_DESCRIPTION__HOWTO_STAFF = eINSTANCE
				.getActivityDescription_HowtoStaff();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.DeliveryProcessDescriptionImpl <em>Delivery Process Description</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.DeliveryProcessDescriptionImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getDeliveryProcessDescription()
		 * @generated
		 */
		EClass DELIVERY_PROCESS_DESCRIPTION = eINSTANCE
				.getDeliveryProcessDescription();

		/**
		 * The meta object literal for the '<em><b>Scale</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DELIVERY_PROCESS_DESCRIPTION__SCALE = eINSTANCE
				.getDeliveryProcessDescription_Scale();

		/**
		 * The meta object literal for the '<em><b>Project Characteristics</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DELIVERY_PROCESS_DESCRIPTION__PROJECT_CHARACTERISTICS = eINSTANCE
				.getDeliveryProcessDescription_ProjectCharacteristics();

		/**
		 * The meta object literal for the '<em><b>Risk Level</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DELIVERY_PROCESS_DESCRIPTION__RISK_LEVEL = eINSTANCE
				.getDeliveryProcessDescription_RiskLevel();

		/**
		 * The meta object literal for the '<em><b>Estimating Technique</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DELIVERY_PROCESS_DESCRIPTION__ESTIMATING_TECHNIQUE = eINSTANCE
				.getDeliveryProcessDescription_EstimatingTechnique();

		/**
		 * The meta object literal for the '<em><b>Project Member Expertise</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DELIVERY_PROCESS_DESCRIPTION__PROJECT_MEMBER_EXPERTISE = eINSTANCE
				.getDeliveryProcessDescription_ProjectMemberExpertise();

		/**
		 * The meta object literal for the '<em><b>Type Of Contract</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DELIVERY_PROCESS_DESCRIPTION__TYPE_OF_CONTRACT = eINSTANCE
				.getDeliveryProcessDescription_TypeOfContract();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.ProcessDescriptionImpl <em>Process Description</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.ProcessDescriptionImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getProcessDescription()
		 * @generated
		 */
		EClass PROCESS_DESCRIPTION = eINSTANCE.getProcessDescription();

		/**
		 * The meta object literal for the '<em><b>Scope</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROCESS_DESCRIPTION__SCOPE = eINSTANCE
				.getProcessDescription_Scope();

		/**
		 * The meta object literal for the '<em><b>Usage Notes</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROCESS_DESCRIPTION__USAGE_NOTES = eINSTANCE
				.getProcessDescription_UsageNotes();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.DescriptorDescriptionImpl <em>Descriptor Description</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.DescriptorDescriptionImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getDescriptorDescription()
		 * @generated
		 */
		EClass DESCRIPTOR_DESCRIPTION = eINSTANCE.getDescriptorDescription();

		/**
		 * The meta object literal for the '<em><b>Refined Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DESCRIPTOR_DESCRIPTION__REFINED_DESCRIPTION = eINSTANCE
				.getDescriptorDescription_RefinedDescription();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.ConceptImpl <em>Concept</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.ConceptImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getConcept()
		 * @generated
		 */
		EClass CONCEPT = eINSTANCE.getConcept();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.ChecklistImpl <em>Checklist</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.ChecklistImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getChecklist()
		 * @generated
		 */
		EClass CHECKLIST = eINSTANCE.getChecklist();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.ExampleImpl <em>Example</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.ExampleImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getExample()
		 * @generated
		 */
		EClass EXAMPLE = eINSTANCE.getExample();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.GuidelineImpl <em>Guideline</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.GuidelineImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getGuideline()
		 * @generated
		 */
		EClass GUIDELINE = eINSTANCE.getGuideline();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.EstimationConsiderationsImpl <em>Estimation Considerations</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.EstimationConsiderationsImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getEstimationConsiderations()
		 * @generated
		 */
		EClass ESTIMATION_CONSIDERATIONS = eINSTANCE
				.getEstimationConsiderations();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.ReportImpl <em>Report</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.ReportImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getReport()
		 * @generated
		 */
		EClass REPORT = eINSTANCE.getReport();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.TemplateImpl <em>Template</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.TemplateImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getTemplate()
		 * @generated
		 */
		EClass TEMPLATE = eINSTANCE.getTemplate();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.SupportingMaterialImpl <em>Supporting Material</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.SupportingMaterialImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getSupportingMaterial()
		 * @generated
		 */
		EClass SUPPORTING_MATERIAL = eINSTANCE.getSupportingMaterial();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.ToolMentorImpl <em>Tool Mentor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.ToolMentorImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getToolMentor()
		 * @generated
		 */
		EClass TOOL_MENTOR = eINSTANCE.getToolMentor();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.WhitepaperImpl <em>Whitepaper</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.WhitepaperImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getWhitepaper()
		 * @generated
		 */
		EClass WHITEPAPER = eINSTANCE.getWhitepaper();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.TermDefinitionImpl <em>Term Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.TermDefinitionImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getTermDefinition()
		 * @generated
		 */
		EClass TERM_DEFINITION = eINSTANCE.getTermDefinition();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.ApplicableMetaClassInfoImpl <em>Applicable Meta Class Info</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.ApplicableMetaClassInfoImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getApplicableMetaClassInfo()
		 * @generated
		 */
		EClass APPLICABLE_META_CLASS_INFO = eINSTANCE
				.getApplicableMetaClassInfo();

		/**
		 * The meta object literal for the '<em><b>Is Primary Extension</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute APPLICABLE_META_CLASS_INFO__IS_PRIMARY_EXTENSION = eINSTANCE
				.getApplicableMetaClassInfo_IsPrimaryExtension();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.PracticeImpl <em>Practice</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.PracticeImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getPractice()
		 * @generated
		 */
		EClass PRACTICE = eINSTANCE.getPractice();

		/**
		 * The meta object literal for the '<em><b>Sub Practices</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRACTICE__SUB_PRACTICES = eINSTANCE
				.getPractice_SubPractices();

		/**
		 * The meta object literal for the '<em><b>Content References</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRACTICE__CONTENT_REFERENCES = eINSTANCE
				.getPractice_ContentReferences();

		/**
		 * The meta object literal for the '<em><b>Activity References</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRACTICE__ACTIVITY_REFERENCES = eINSTANCE
				.getPractice_ActivityReferences();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.ReusableAssetImpl <em>Reusable Asset</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.ReusableAssetImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getReusableAsset()
		 * @generated
		 */
		EClass REUSABLE_ASSET = eINSTANCE.getReusableAsset();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.DisciplineImpl <em>Discipline</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.DisciplineImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getDiscipline()
		 * @generated
		 */
		EClass DISCIPLINE = eINSTANCE.getDiscipline();

		/**
		 * The meta object literal for the '<em><b>Tasks</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DISCIPLINE__TASKS = eINSTANCE.getDiscipline_Tasks();

		/**
		 * The meta object literal for the '<em><b>Subdiscipline</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DISCIPLINE__SUBDISCIPLINE = eINSTANCE
				.getDiscipline_Subdiscipline();

		/**
		 * The meta object literal for the '<em><b>Reference Workflows</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DISCIPLINE__REFERENCE_WORKFLOWS = eINSTANCE
				.getDiscipline_ReferenceWorkflows();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.ContentCategoryImpl <em>Content Category</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.ContentCategoryImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getContentCategory()
		 * @generated
		 */
		EClass CONTENT_CATEGORY = eINSTANCE.getContentCategory();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.RoleSetImpl <em>Role Set</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.RoleSetImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getRoleSet()
		 * @generated
		 */
		EClass ROLE_SET = eINSTANCE.getRoleSet();

		/**
		 * The meta object literal for the '<em><b>Roles</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROLE_SET__ROLES = eINSTANCE.getRoleSet_Roles();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.DomainImpl <em>Domain</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.DomainImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getDomain()
		 * @generated
		 */
		EClass DOMAIN = eINSTANCE.getDomain();

		/**
		 * The meta object literal for the '<em><b>Work Products</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOMAIN__WORK_PRODUCTS = eINSTANCE.getDomain_WorkProducts();

		/**
		 * The meta object literal for the '<em><b>Subdomains</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOMAIN__SUBDOMAINS = eINSTANCE.getDomain_Subdomains();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.WorkProductTypeImpl <em>Work Product Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.WorkProductTypeImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getWorkProductType()
		 * @generated
		 */
		EClass WORK_PRODUCT_TYPE = eINSTANCE.getWorkProductType();

		/**
		 * The meta object literal for the '<em><b>Work Products</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORK_PRODUCT_TYPE__WORK_PRODUCTS = eINSTANCE
				.getWorkProductType_WorkProducts();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.DisciplineGroupingImpl <em>Discipline Grouping</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.DisciplineGroupingImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getDisciplineGrouping()
		 * @generated
		 */
		EClass DISCIPLINE_GROUPING = eINSTANCE.getDisciplineGrouping();

		/**
		 * The meta object literal for the '<em><b>Disciplines</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DISCIPLINE_GROUPING__DISCIPLINES = eINSTANCE
				.getDisciplineGrouping_Disciplines();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.ToolImpl <em>Tool</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.ToolImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getTool()
		 * @generated
		 */
		EClass TOOL = eINSTANCE.getTool();

		/**
		 * The meta object literal for the '<em><b>Tool Mentors</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TOOL__TOOL_MENTORS = eINSTANCE.getTool_ToolMentors();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.RoleSetGroupingImpl <em>Role Set Grouping</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.RoleSetGroupingImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getRoleSetGrouping()
		 * @generated
		 */
		EClass ROLE_SET_GROUPING = eINSTANCE.getRoleSetGrouping();

		/**
		 * The meta object literal for the '<em><b>Role Sets</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROLE_SET_GROUPING__ROLE_SETS = eINSTANCE
				.getRoleSetGrouping_RoleSets();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.CustomCategoryImpl <em>Custom Category</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.CustomCategoryImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getCustomCategory()
		 * @generated
		 */
		EClass CUSTOM_CATEGORY = eINSTANCE.getCustomCategory();

		/**
		 * The meta object literal for the '<em><b>Categorized Elements</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CUSTOM_CATEGORY__CATEGORIZED_ELEMENTS = eINSTANCE
				.getCustomCategory_CategorizedElements();

		/**
		 * The meta object literal for the '<em><b>Sub Categories</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CUSTOM_CATEGORY__SUB_CATEGORIES = eINSTANCE
				.getCustomCategory_SubCategories();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.DeliveryProcessImpl <em>Delivery Process</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.DeliveryProcessImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getDeliveryProcess()
		 * @generated
		 */
		EClass DELIVERY_PROCESS = eINSTANCE.getDeliveryProcess();

		/**
		 * The meta object literal for the '<em><b>Education Materials</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DELIVERY_PROCESS__EDUCATION_MATERIALS = eINSTANCE
				.getDeliveryProcess_EducationMaterials();

		/**
		 * The meta object literal for the '<em><b>Communications Materials</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DELIVERY_PROCESS__COMMUNICATIONS_MATERIALS = eINSTANCE
				.getDeliveryProcess_CommunicationsMaterials();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.ProcessImpl <em>Process</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.ProcessImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getProcess()
		 * @generated
		 */
		EClass PROCESS = eINSTANCE.getProcess();

		/**
		 * The meta object literal for the '<em><b>Includes Patterns</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS__INCLUDES_PATTERNS = eINSTANCE
				.getProcess_IncludesPatterns();

		/**
		 * The meta object literal for the '<em><b>Default Context</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS__DEFAULT_CONTEXT = eINSTANCE
				.getProcess_DefaultContext();

		/**
		 * The meta object literal for the '<em><b>Valid Context</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS__VALID_CONTEXT = eINSTANCE.getProcess_ValidContext();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.CapabilityPatternImpl <em>Capability Pattern</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.CapabilityPatternImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getCapabilityPattern()
		 * @generated
		 */
		EClass CAPABILITY_PATTERN = eINSTANCE.getCapabilityPattern();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.ProcessPlanningTemplateImpl <em>Process Planning Template</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.ProcessPlanningTemplateImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getProcessPlanningTemplate()
		 * @generated
		 */
		EClass PROCESS_PLANNING_TEMPLATE = eINSTANCE
				.getProcessPlanningTemplate();

		/**
		 * The meta object literal for the '<em><b>Based On Processes</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_PLANNING_TEMPLATE__BASED_ON_PROCESSES = eINSTANCE
				.getProcessPlanningTemplate_BasedOnProcesses();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.RoadmapImpl <em>Roadmap</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.RoadmapImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getRoadmap()
		 * @generated
		 */
		EClass ROADMAP = eINSTANCE.getRoadmap();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.ProcessComponentImpl <em>Process Component</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.ProcessComponentImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getProcessComponent()
		 * @generated
		 */
		EClass PROCESS_COMPONENT = eINSTANCE.getProcessComponent();

		/**
		 * The meta object literal for the '<em><b>Interfaces</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_COMPONENT__INTERFACES = eINSTANCE
				.getProcessComponent_Interfaces();

		/**
		 * The meta object literal for the '<em><b>Process</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_COMPONENT__PROCESS = eINSTANCE
				.getProcessComponent_Process();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.ProcessPackageImpl <em>Process Package</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.ProcessPackageImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getProcessPackage()
		 * @generated
		 */
		EClass PROCESS_PACKAGE = eINSTANCE.getProcessPackage();

		/**
		 * The meta object literal for the '<em><b>Process Elements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_PACKAGE__PROCESS_ELEMENTS = eINSTANCE
				.getProcessPackage_ProcessElements();

		/**
		 * The meta object literal for the '<em><b>Diagrams</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_PACKAGE__DIAGRAMS = eINSTANCE
				.getProcessPackage_Diagrams();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.ProcessComponentInterfaceImpl <em>Process Component Interface</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.ProcessComponentInterfaceImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getProcessComponentInterface()
		 * @generated
		 */
		EClass PROCESS_COMPONENT_INTERFACE = eINSTANCE
				.getProcessComponentInterface();

		/**
		 * The meta object literal for the '<em><b>Interface Specifications</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_COMPONENT_INTERFACE__INTERFACE_SPECIFICATIONS = eINSTANCE
				.getProcessComponentInterface_InterfaceSpecifications();

		/**
		 * The meta object literal for the '<em><b>Interface IO</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_COMPONENT_INTERFACE__INTERFACE_IO = eINSTANCE
				.getProcessComponentInterface_InterfaceIO();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.ProcessComponentDescriptorImpl <em>Process Component Descriptor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.ProcessComponentDescriptorImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getProcessComponentDescriptor()
		 * @generated
		 */
		EClass PROCESS_COMPONENT_DESCRIPTOR = eINSTANCE
				.getProcessComponentDescriptor();

		/**
		 * The meta object literal for the '<em><b>process Component</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_COMPONENT_DESCRIPTOR__PROCESS_COMPONENT = eINSTANCE
				.getProcessComponentDescriptor__processComponent();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.MethodPluginImpl <em>Method Plugin</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.MethodPluginImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getMethodPlugin()
		 * @generated
		 */
		EClass METHOD_PLUGIN = eINSTANCE.getMethodPlugin();

		/**
		 * The meta object literal for the '<em><b>User Changeable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METHOD_PLUGIN__USER_CHANGEABLE = eINSTANCE
				.getMethodPlugin_UserChangeable();

		/**
		 * The meta object literal for the '<em><b>Method Packages</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METHOD_PLUGIN__METHOD_PACKAGES = eINSTANCE
				.getMethodPlugin_MethodPackages();

		/**
		 * The meta object literal for the '<em><b>Bases</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METHOD_PLUGIN__BASES = eINSTANCE.getMethodPlugin_Bases();

		/**
		 * The meta object literal for the '<em><b>Supporting</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METHOD_PLUGIN__SUPPORTING = eINSTANCE
				.getMethodPlugin_Supporting();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.VariabilityElementImpl <em>Variability Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.VariabilityElementImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getVariabilityElement()
		 * @generated
		 */
		EClass VARIABILITY_ELEMENT = eINSTANCE.getVariabilityElement();

		/**
		 * The meta object literal for the '<em><b>Variability Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VARIABILITY_ELEMENT__VARIABILITY_TYPE = eINSTANCE
				.getVariabilityElement_VariabilityType();

		/**
		 * The meta object literal for the '<em><b>Variability Based On Element</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VARIABILITY_ELEMENT__VARIABILITY_BASED_ON_ELEMENT = eINSTANCE
				.getVariabilityElement_VariabilityBasedOnElement();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.MethodUnitImpl <em>Method Unit</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.MethodUnitImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getMethodUnit()
		 * @generated
		 */
		EClass METHOD_UNIT = eINSTANCE.getMethodUnit();

		/**
		 * The meta object literal for the '<em><b>Authors</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METHOD_UNIT__AUTHORS = eINSTANCE.getMethodUnit_Authors();

		/**
		 * The meta object literal for the '<em><b>Change Date</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METHOD_UNIT__CHANGE_DATE = eINSTANCE
				.getMethodUnit_ChangeDate();

		/**
		 * The meta object literal for the '<em><b>Change Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METHOD_UNIT__CHANGE_DESCRIPTION = eINSTANCE
				.getMethodUnit_ChangeDescription();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METHOD_UNIT__VERSION = eINSTANCE.getMethodUnit_Version();

		/**
		 * The meta object literal for the '<em><b>Copyright Statement</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METHOD_UNIT__COPYRIGHT_STATEMENT = eINSTANCE
				.getMethodUnit_CopyrightStatement();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.MethodConfigurationImpl <em>Method Configuration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.MethodConfigurationImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getMethodConfiguration()
		 * @generated
		 */
		EClass METHOD_CONFIGURATION = eINSTANCE.getMethodConfiguration();

		/**
		 * The meta object literal for the '<em><b>Method Plugin Selection</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METHOD_CONFIGURATION__METHOD_PLUGIN_SELECTION = eINSTANCE
				.getMethodConfiguration_MethodPluginSelection();

		/**
		 * The meta object literal for the '<em><b>Method Package Selection</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METHOD_CONFIGURATION__METHOD_PACKAGE_SELECTION = eINSTANCE
				.getMethodConfiguration_MethodPackageSelection();

		/**
		 * The meta object literal for the '<em><b>Process Views</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METHOD_CONFIGURATION__PROCESS_VIEWS = eINSTANCE
				.getMethodConfiguration_ProcessViews();

		/**
		 * The meta object literal for the '<em><b>Default View</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METHOD_CONFIGURATION__DEFAULT_VIEW = eINSTANCE
				.getMethodConfiguration_DefaultView();

		/**
		 * The meta object literal for the '<em><b>Base Configurations</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METHOD_CONFIGURATION__BASE_CONFIGURATIONS = eINSTANCE
				.getMethodConfiguration_BaseConfigurations();

		/**
		 * The meta object literal for the '<em><b>Subtracted Category</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METHOD_CONFIGURATION__SUBTRACTED_CATEGORY = eINSTANCE
				.getMethodConfiguration_SubtractedCategory();

		/**
		 * The meta object literal for the '<em><b>Added Category</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METHOD_CONFIGURATION__ADDED_CATEGORY = eINSTANCE
				.getMethodConfiguration_AddedCategory();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.ProcessFamilyImpl <em>Process Family</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.ProcessFamilyImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getProcessFamily()
		 * @generated
		 */
		EClass PROCESS_FAMILY = eINSTANCE.getProcessFamily();

		/**
		 * The meta object literal for the '<em><b>Delivery Processes</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_FAMILY__DELIVERY_PROCESSES = eINSTANCE
				.getProcessFamily_DeliveryProcesses();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.MethodLibraryImpl <em>Method Library</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.MethodLibraryImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getMethodLibrary()
		 * @generated
		 */
		EClass METHOD_LIBRARY = eINSTANCE.getMethodLibrary();

		/**
		 * The meta object literal for the '<em><b>Method Plugins</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METHOD_LIBRARY__METHOD_PLUGINS = eINSTANCE
				.getMethodLibrary_MethodPlugins();

		/**
		 * The meta object literal for the '<em><b>Predefined Configurations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METHOD_LIBRARY__PREDEFINED_CONFIGURATIONS = eINSTANCE
				.getMethodLibrary_PredefinedConfigurations();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.PointImpl <em>Point</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.PointImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getPoint()
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
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.GraphElementImpl <em>Graph Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.GraphElementImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getGraphElement()
		 * @generated
		 */
		EClass GRAPH_ELEMENT = eINSTANCE.getGraphElement();

		/**
		 * The meta object literal for the '<em><b>Position</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GRAPH_ELEMENT__POSITION = eINSTANCE
				.getGraphElement_Position();

		/**
		 * The meta object literal for the '<em><b>Contained</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GRAPH_ELEMENT__CONTAINED = eINSTANCE
				.getGraphElement_Contained();

		/**
		 * The meta object literal for the '<em><b>Link</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GRAPH_ELEMENT__LINK = eINSTANCE.getGraphElement_Link();

		/**
		 * The meta object literal for the '<em><b>Anchorage</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GRAPH_ELEMENT__ANCHORAGE = eINSTANCE
				.getGraphElement_Anchorage();

		/**
		 * The meta object literal for the '<em><b>Semantic Model</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GRAPH_ELEMENT__SEMANTIC_MODEL = eINSTANCE
				.getGraphElement_SemanticModel();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.DiagramElementImpl <em>Diagram Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.DiagramElementImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getDiagramElement()
		 * @generated
		 */
		EClass DIAGRAM_ELEMENT = eINSTANCE.getDiagramElement();

		/**
		 * The meta object literal for the '<em><b>Is Visible</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DIAGRAM_ELEMENT__IS_VISIBLE = eINSTANCE
				.getDiagramElement_IsVisible();

		/**
		 * The meta object literal for the '<em><b>Container</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DIAGRAM_ELEMENT__CONTAINER = eINSTANCE
				.getDiagramElement_Container();

		/**
		 * The meta object literal for the '<em><b>Reference</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DIAGRAM_ELEMENT__REFERENCE = eINSTANCE
				.getDiagramElement_Reference();

		/**
		 * The meta object literal for the '<em><b>Property</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DIAGRAM_ELEMENT__PROPERTY = eINSTANCE
				.getDiagramElement_Property();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.DiagramLinkImpl <em>Diagram Link</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.DiagramLinkImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getDiagramLink()
		 * @generated
		 */
		EClass DIAGRAM_LINK = eINSTANCE.getDiagramLink();

		/**
		 * The meta object literal for the '<em><b>Zoom</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DIAGRAM_LINK__ZOOM = eINSTANCE.getDiagramLink_Zoom();

		/**
		 * The meta object literal for the '<em><b>Viewport</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DIAGRAM_LINK__VIEWPORT = eINSTANCE.getDiagramLink_Viewport();

		/**
		 * The meta object literal for the '<em><b>Graph Element</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DIAGRAM_LINK__GRAPH_ELEMENT = eINSTANCE
				.getDiagramLink_GraphElement();

		/**
		 * The meta object literal for the '<em><b>Diagram</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DIAGRAM_LINK__DIAGRAM = eINSTANCE.getDiagramLink_Diagram();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.GraphConnectorImpl <em>Graph Connector</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.GraphConnectorImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getGraphConnector()
		 * @generated
		 */
		EClass GRAPH_CONNECTOR = eINSTANCE.getGraphConnector();

		/**
		 * The meta object literal for the '<em><b>Graph Element</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GRAPH_CONNECTOR__GRAPH_ELEMENT = eINSTANCE
				.getGraphConnector_GraphElement();

		/**
		 * The meta object literal for the '<em><b>Graph Edge</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GRAPH_CONNECTOR__GRAPH_EDGE = eINSTANCE
				.getGraphConnector_GraphEdge();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.SemanticModelBridgeImpl <em>Semantic Model Bridge</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.SemanticModelBridgeImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getSemanticModelBridge()
		 * @generated
		 */
		EClass SEMANTIC_MODEL_BRIDGE = eINSTANCE.getSemanticModelBridge();

		/**
		 * The meta object literal for the '<em><b>Presentation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEMANTIC_MODEL_BRIDGE__PRESENTATION = eINSTANCE
				.getSemanticModelBridge_Presentation();

		/**
		 * The meta object literal for the '<em><b>Graph Element</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SEMANTIC_MODEL_BRIDGE__GRAPH_ELEMENT = eINSTANCE
				.getSemanticModelBridge_GraphElement();

		/**
		 * The meta object literal for the '<em><b>Diagram</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SEMANTIC_MODEL_BRIDGE__DIAGRAM = eINSTANCE
				.getSemanticModelBridge_Diagram();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.DimensionImpl <em>Dimension</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.DimensionImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getDimension()
		 * @generated
		 */
		EClass DIMENSION = eINSTANCE.getDimension();

		/**
		 * The meta object literal for the '<em><b>Width</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DIMENSION__WIDTH = eINSTANCE.getDimension_Width();

		/**
		 * The meta object literal for the '<em><b>Height</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DIMENSION__HEIGHT = eINSTANCE.getDimension_Height();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.ReferenceImpl <em>Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.ReferenceImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getReference()
		 * @generated
		 */
		EClass REFERENCE = eINSTANCE.getReference();

		/**
		 * The meta object literal for the '<em><b>Is Individual Representation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE__IS_INDIVIDUAL_REPRESENTATION = eINSTANCE
				.getReference_IsIndividualRepresentation();

		/**
		 * The meta object literal for the '<em><b>Referenced</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REFERENCE__REFERENCED = eINSTANCE.getReference_Referenced();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.PropertyImpl <em>Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.PropertyImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getProperty()
		 * @generated
		 */
		EClass PROPERTY = eINSTANCE.getProperty();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROPERTY__KEY = eINSTANCE.getProperty_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROPERTY__VALUE = eINSTANCE.getProperty_Value();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.GraphEdgeImpl <em>Graph Edge</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.GraphEdgeImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getGraphEdge()
		 * @generated
		 */
		EClass GRAPH_EDGE = eINSTANCE.getGraphEdge();

		/**
		 * The meta object literal for the '<em><b>Anchor</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GRAPH_EDGE__ANCHOR = eINSTANCE.getGraphEdge_Anchor();

		/**
		 * The meta object literal for the '<em><b>Waypoints</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GRAPH_EDGE__WAYPOINTS = eINSTANCE.getGraphEdge_Waypoints();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.DiagramImpl <em>Diagram</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.DiagramImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getDiagram()
		 * @generated
		 */
		EClass DIAGRAM = eINSTANCE.getDiagram();

		/**
		 * The meta object literal for the '<em><b>Zoom</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DIAGRAM__ZOOM = eINSTANCE.getDiagram_Zoom();

		/**
		 * The meta object literal for the '<em><b>Viewpoint</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DIAGRAM__VIEWPOINT = eINSTANCE.getDiagram_Viewpoint();

		/**
		 * The meta object literal for the '<em><b>Diagram Link</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DIAGRAM__DIAGRAM_LINK = eINSTANCE.getDiagram_DiagramLink();

		/**
		 * The meta object literal for the '<em><b>Namespace</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DIAGRAM__NAMESPACE = eINSTANCE.getDiagram_Namespace();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.GraphNodeImpl <em>Graph Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.GraphNodeImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getGraphNode()
		 * @generated
		 */
		EClass GRAPH_NODE = eINSTANCE.getGraphNode();

		/**
		 * The meta object literal for the '<em><b>Size</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GRAPH_NODE__SIZE = eINSTANCE.getGraphNode_Size();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.SimpleSemanticModelElementImpl <em>Simple Semantic Model Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.SimpleSemanticModelElementImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getSimpleSemanticModelElement()
		 * @generated
		 */
		EClass SIMPLE_SEMANTIC_MODEL_ELEMENT = eINSTANCE
				.getSimpleSemanticModelElement();

		/**
		 * The meta object literal for the '<em><b>Type Info</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SIMPLE_SEMANTIC_MODEL_ELEMENT__TYPE_INFO = eINSTANCE
				.getSimpleSemanticModelElement_TypeInfo();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.UMASemanticModelBridgeImpl <em>UMA Semantic Model Bridge</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.UMASemanticModelBridgeImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getUMASemanticModelBridge()
		 * @generated
		 */
		EClass UMA_SEMANTIC_MODEL_BRIDGE = eINSTANCE
				.getUMASemanticModelBridge();

		/**
		 * The meta object literal for the '<em><b>Element</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UMA_SEMANTIC_MODEL_BRIDGE__ELEMENT = eINSTANCE
				.getUMASemanticModelBridge_Element();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.CoreSemanticModelBridgeImpl <em>Core Semantic Model Bridge</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.CoreSemanticModelBridgeImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getCoreSemanticModelBridge()
		 * @generated
		 */
		EClass CORE_SEMANTIC_MODEL_BRIDGE = eINSTANCE
				.getCoreSemanticModelBridge();

		/**
		 * The meta object literal for the '<em><b>Element</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CORE_SEMANTIC_MODEL_BRIDGE__ELEMENT = eINSTANCE
				.getCoreSemanticModelBridge_Element();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.LeafElementImpl <em>Leaf Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.LeafElementImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getLeafElement()
		 * @generated
		 */
		EClass LEAF_ELEMENT = eINSTANCE.getLeafElement();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.TextElementImpl <em>Text Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.TextElementImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getTextElement()
		 * @generated
		 */
		EClass TEXT_ELEMENT = eINSTANCE.getTextElement();

		/**
		 * The meta object literal for the '<em><b>Text</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEXT_ELEMENT__TEXT = eINSTANCE.getTextElement_Text();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.ImageImpl <em>Image</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.ImageImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getImage()
		 * @generated
		 */
		EClass IMAGE = eINSTANCE.getImage();

		/**
		 * The meta object literal for the '<em><b>Uri</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IMAGE__URI = eINSTANCE.getImage_Uri();

		/**
		 * The meta object literal for the '<em><b>Mime Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IMAGE__MIME_TYPE = eINSTANCE.getImage_MimeType();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.GraphicPrimitiveImpl <em>Graphic Primitive</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.GraphicPrimitiveImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getGraphicPrimitive()
		 * @generated
		 */
		EClass GRAPHIC_PRIMITIVE = eINSTANCE.getGraphicPrimitive();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.PolylineImpl <em>Polyline</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.PolylineImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getPolyline()
		 * @generated
		 */
		EClass POLYLINE = eINSTANCE.getPolyline();

		/**
		 * The meta object literal for the '<em><b>Closed</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POLYLINE__CLOSED = eINSTANCE.getPolyline_Closed();

		/**
		 * The meta object literal for the '<em><b>Waypoints</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference POLYLINE__WAYPOINTS = eINSTANCE.getPolyline_Waypoints();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.impl.EllipseImpl <em>Ellipse</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.EllipseImpl
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getEllipse()
		 * @generated
		 */
		EClass ELLIPSE = eINSTANCE.getEllipse();

		/**
		 * The meta object literal for the '<em><b>Radius X</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ELLIPSE__RADIUS_X = eINSTANCE.getEllipse_RadiusX();

		/**
		 * The meta object literal for the '<em><b>Radius Y</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ELLIPSE__RADIUS_Y = eINSTANCE.getEllipse_RadiusY();

		/**
		 * The meta object literal for the '<em><b>Rotation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ELLIPSE__ROTATION = eINSTANCE.getEllipse_Rotation();

		/**
		 * The meta object literal for the '<em><b>Start Angle</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ELLIPSE__START_ANGLE = eINSTANCE.getEllipse_StartAngle();

		/**
		 * The meta object literal for the '<em><b>End Angle</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ELLIPSE__END_ANGLE = eINSTANCE.getEllipse_EndAngle();

		/**
		 * The meta object literal for the '<em><b>Center</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ELLIPSE__CENTER = eINSTANCE.getEllipse_Center();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.WorkOrderType <em>Work Order Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.WorkOrderType
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getWorkOrderType()
		 * @generated
		 */
		EEnum WORK_ORDER_TYPE = eINSTANCE.getWorkOrderType();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.uma.VariabilityType <em>Variability Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.VariabilityType
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getVariabilityType()
		 * @generated
		 */
		EEnum VARIABILITY_TYPE = eINSTANCE.getVariabilityType();

		/**
		 * The meta object literal for the '<em>Date</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.util.Date
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getDate()
		 * @generated
		 */
		EDataType DATE = eINSTANCE.getDate();

		/**
		 * The meta object literal for the '<em>Uri</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.net.URI
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getUri()
		 * @generated
		 */
		EDataType URI = eINSTANCE.getUri();

		/**
		 * The meta object literal for the '<em>String</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.String
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getString()
		 * @generated
		 */
		EDataType STRING = eINSTANCE.getString();

		/**
		 * The meta object literal for the '<em>Boolean</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.Boolean
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getBoolean()
		 * @generated
		 */
		EDataType BOOLEAN = eINSTANCE.getBoolean();

		/**
		 * The meta object literal for the '<em>Set</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.util.Set
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getSet()
		 * @generated
		 */
		EDataType SET = eINSTANCE.getSet();

		/**
		 * The meta object literal for the '<em>Sequence</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.util.List
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getSequence()
		 * @generated
		 */
		EDataType SEQUENCE = eINSTANCE.getSequence();

		/**
		 * The meta object literal for the '<em>Integer</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getInteger()
		 * @generated
		 */
		EDataType INTEGER = eINSTANCE.getInteger();

		/**
		 * The meta object literal for the '<em>Double</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.Double
		 * @see org.eclipse.epf.uma.impl.UmaPackageImpl#getDouble()
		 * @generated
		 */
		EDataType DOUBLE = eINSTANCE.getDouble();

	}

} //UmaPackage