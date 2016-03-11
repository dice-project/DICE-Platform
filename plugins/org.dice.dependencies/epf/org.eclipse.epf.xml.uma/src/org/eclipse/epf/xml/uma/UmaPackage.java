/*******************************************************************************
 * Copyright (c) 2005, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial implementation
 *******************************************************************************/
package org.eclipse.epf.xml.uma;

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
 * @see org.eclipse.epf.xml.uma.UmaFactory
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
	String eNS_URI = "http://www.eclipse.org/epf/uma/1.0.6";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "uma";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	UmaPackage eINSTANCE = org.eclipse.epf.xml.uma.impl.UmaPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.ElementImpl <em>Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.ElementImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getElement()
	 * @generated
	 */
	int ELEMENT = 29;

	/**
	 * The number of structural features of the '<em>Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.NamedElementImpl <em>Named Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.NamedElementImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getNamedElement()
	 * @generated
	 */
	int NAMED_ELEMENT = 47;

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
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.PackageableElementImpl <em>Packageable Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.PackageableElementImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getPackageableElement()
	 * @generated
	 */
	int PACKAGEABLE_ELEMENT = 49;

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
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.MethodElementImpl <em>Method Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.MethodElementImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getMethodElement()
	 * @generated
	 */
	int METHOD_ELEMENT = 40;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_ELEMENT__NAME = PACKAGEABLE_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_ELEMENT__GROUP = PACKAGEABLE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_ELEMENT__OWNED_RULE = PACKAGEABLE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_ELEMENT__METHOD_ELEMENT_PROPERTY = PACKAGEABLE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_ELEMENT__BRIEF_DESCRIPTION = PACKAGEABLE_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_ELEMENT__ID = PACKAGEABLE_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_ELEMENT__ORDERING_GUIDE = PACKAGEABLE_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_ELEMENT__PRESENTATION_NAME = PACKAGEABLE_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_ELEMENT__SUPPRESSED = PACKAGEABLE_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The number of structural features of the '<em>Method Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_ELEMENT_FEATURE_COUNT = PACKAGEABLE_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.DescribableElementImpl <em>Describable Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.DescribableElementImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getDescribableElement()
	 * @generated
	 */
	int DESCRIBABLE_ELEMENT = 22;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIBABLE_ELEMENT__NAME = METHOD_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIBABLE_ELEMENT__GROUP = METHOD_ELEMENT__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIBABLE_ELEMENT__OWNED_RULE = METHOD_ELEMENT__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIBABLE_ELEMENT__METHOD_ELEMENT_PROPERTY = METHOD_ELEMENT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIBABLE_ELEMENT__BRIEF_DESCRIPTION = METHOD_ELEMENT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIBABLE_ELEMENT__ID = METHOD_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIBABLE_ELEMENT__ORDERING_GUIDE = METHOD_ELEMENT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIBABLE_ELEMENT__PRESENTATION_NAME = METHOD_ELEMENT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIBABLE_ELEMENT__SUPPRESSED = METHOD_ELEMENT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIBABLE_ELEMENT__PRESENTATION = METHOD_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Fulfill</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIBABLE_ELEMENT__FULFILL = METHOD_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIBABLE_ELEMENT__IS_ABSTRACT = METHOD_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIBABLE_ELEMENT__NODEICON = METHOD_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIBABLE_ELEMENT__SHAPEICON = METHOD_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Describable Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIBABLE_ELEMENT_FEATURE_COUNT = METHOD_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.ProcessElementImpl <em>Process Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.ProcessElementImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getProcessElement()
	 * @generated
	 */
	int PROCESS_ELEMENT = 58;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_ELEMENT__NAME = DESCRIBABLE_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_ELEMENT__GROUP = DESCRIBABLE_ELEMENT__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_ELEMENT__OWNED_RULE = DESCRIBABLE_ELEMENT__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_ELEMENT__METHOD_ELEMENT_PROPERTY = DESCRIBABLE_ELEMENT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_ELEMENT__BRIEF_DESCRIPTION = DESCRIBABLE_ELEMENT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_ELEMENT__ID = DESCRIBABLE_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_ELEMENT__ORDERING_GUIDE = DESCRIBABLE_ELEMENT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_ELEMENT__PRESENTATION_NAME = DESCRIBABLE_ELEMENT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_ELEMENT__SUPPRESSED = DESCRIBABLE_ELEMENT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_ELEMENT__PRESENTATION = DESCRIBABLE_ELEMENT__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Fulfill</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_ELEMENT__FULFILL = DESCRIBABLE_ELEMENT__FULFILL;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_ELEMENT__IS_ABSTRACT = DESCRIBABLE_ELEMENT__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_ELEMENT__NODEICON = DESCRIBABLE_ELEMENT__NODEICON;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_ELEMENT__SHAPEICON = DESCRIBABLE_ELEMENT__SHAPEICON;

	/**
	 * The number of structural features of the '<em>Process Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_ELEMENT_FEATURE_COUNT = DESCRIBABLE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.BreakdownElementImpl <em>Breakdown Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.BreakdownElementImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getBreakdownElement()
	 * @generated
	 */
	int BREAKDOWN_ELEMENT = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT__NAME = PROCESS_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT__GROUP = PROCESS_ELEMENT__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT__OWNED_RULE = PROCESS_ELEMENT__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT__METHOD_ELEMENT_PROPERTY = PROCESS_ELEMENT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT__BRIEF_DESCRIPTION = PROCESS_ELEMENT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT__ID = PROCESS_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT__ORDERING_GUIDE = PROCESS_ELEMENT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT__PRESENTATION_NAME = PROCESS_ELEMENT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT__SUPPRESSED = PROCESS_ELEMENT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT__PRESENTATION = PROCESS_ELEMENT__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Fulfill</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT__FULFILL = PROCESS_ELEMENT__FULFILL;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT__IS_ABSTRACT = PROCESS_ELEMENT__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT__NODEICON = PROCESS_ELEMENT__NODEICON;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT__SHAPEICON = PROCESS_ELEMENT__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Presented After</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT__PRESENTED_AFTER = PROCESS_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Presented Before</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT__PRESENTED_BEFORE = PROCESS_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Planning Data</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT__PLANNING_DATA = PROCESS_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Super Activity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT__SUPER_ACTIVITY = PROCESS_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Group1</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT__GROUP1 = PROCESS_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Checklist</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT__CHECKLIST = PROCESS_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Concept</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT__CONCEPT = PROCESS_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Example</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT__EXAMPLE = PROCESS_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Guideline</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT__GUIDELINE = PROCESS_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Reusable Asset</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT__REUSABLE_ASSET = PROCESS_ELEMENT_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Supporting Material</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT__SUPPORTING_MATERIAL = PROCESS_ELEMENT_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Whitepaper</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT__WHITEPAPER = PROCESS_ELEMENT_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Has Multiple Occurrences</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT__HAS_MULTIPLE_OCCURRENCES = PROCESS_ELEMENT_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Is Optional</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT__IS_OPTIONAL = PROCESS_ELEMENT_FEATURE_COUNT + 13;

	/**
	 * The feature id for the '<em><b>Is Planned</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT__IS_PLANNED = PROCESS_ELEMENT_FEATURE_COUNT + 14;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT__PREFIX = PROCESS_ELEMENT_FEATURE_COUNT + 15;

	/**
	 * The number of structural features of the '<em>Breakdown Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT_FEATURE_COUNT = PROCESS_ELEMENT_FEATURE_COUNT + 16;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.WorkBreakdownElementImpl <em>Work Breakdown Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.WorkBreakdownElementImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getWorkBreakdownElement()
	 * @generated
	 */
	int WORK_BREAKDOWN_ELEMENT = 80;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__NAME = BREAKDOWN_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__GROUP = BREAKDOWN_ELEMENT__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__OWNED_RULE = BREAKDOWN_ELEMENT__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__METHOD_ELEMENT_PROPERTY = BREAKDOWN_ELEMENT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__BRIEF_DESCRIPTION = BREAKDOWN_ELEMENT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__ID = BREAKDOWN_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__ORDERING_GUIDE = BREAKDOWN_ELEMENT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__PRESENTATION_NAME = BREAKDOWN_ELEMENT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__SUPPRESSED = BREAKDOWN_ELEMENT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__PRESENTATION = BREAKDOWN_ELEMENT__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Fulfill</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__FULFILL = BREAKDOWN_ELEMENT__FULFILL;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__IS_ABSTRACT = BREAKDOWN_ELEMENT__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__NODEICON = BREAKDOWN_ELEMENT__NODEICON;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__SHAPEICON = BREAKDOWN_ELEMENT__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Presented After</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__PRESENTED_AFTER = BREAKDOWN_ELEMENT__PRESENTED_AFTER;

	/**
	 * The feature id for the '<em><b>Presented Before</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__PRESENTED_BEFORE = BREAKDOWN_ELEMENT__PRESENTED_BEFORE;

	/**
	 * The feature id for the '<em><b>Planning Data</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__PLANNING_DATA = BREAKDOWN_ELEMENT__PLANNING_DATA;

	/**
	 * The feature id for the '<em><b>Super Activity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__SUPER_ACTIVITY = BREAKDOWN_ELEMENT__SUPER_ACTIVITY;

	/**
	 * The feature id for the '<em><b>Group1</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__GROUP1 = BREAKDOWN_ELEMENT__GROUP1;

	/**
	 * The feature id for the '<em><b>Checklist</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__CHECKLIST = BREAKDOWN_ELEMENT__CHECKLIST;

	/**
	 * The feature id for the '<em><b>Concept</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__CONCEPT = BREAKDOWN_ELEMENT__CONCEPT;

	/**
	 * The feature id for the '<em><b>Example</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__EXAMPLE = BREAKDOWN_ELEMENT__EXAMPLE;

	/**
	 * The feature id for the '<em><b>Guideline</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__GUIDELINE = BREAKDOWN_ELEMENT__GUIDELINE;

	/**
	 * The feature id for the '<em><b>Reusable Asset</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__REUSABLE_ASSET = BREAKDOWN_ELEMENT__REUSABLE_ASSET;

	/**
	 * The feature id for the '<em><b>Supporting Material</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__SUPPORTING_MATERIAL = BREAKDOWN_ELEMENT__SUPPORTING_MATERIAL;

	/**
	 * The feature id for the '<em><b>Whitepaper</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__WHITEPAPER = BREAKDOWN_ELEMENT__WHITEPAPER;

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
	 * The feature id for the '<em><b>Is Planned</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__IS_PLANNED = BREAKDOWN_ELEMENT__IS_PLANNED;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__PREFIX = BREAKDOWN_ELEMENT__PREFIX;

	/**
	 * The feature id for the '<em><b>Group2</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__GROUP2 = BREAKDOWN_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Predecessor</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__PREDECESSOR = BREAKDOWN_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Is Event Driven</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__IS_EVENT_DRIVEN = BREAKDOWN_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Is Ongoing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__IS_ONGOING = BREAKDOWN_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Is Repeatable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT__IS_REPEATABLE = BREAKDOWN_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Work Breakdown Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_BREAKDOWN_ELEMENT_FEATURE_COUNT = BREAKDOWN_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.ActivityImpl <em>Activity</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.ActivityImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getActivity()
	 * @generated
	 */
	int ACTIVITY = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__NAME = WORK_BREAKDOWN_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__GROUP = WORK_BREAKDOWN_ELEMENT__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__OWNED_RULE = WORK_BREAKDOWN_ELEMENT__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__METHOD_ELEMENT_PROPERTY = WORK_BREAKDOWN_ELEMENT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__BRIEF_DESCRIPTION = WORK_BREAKDOWN_ELEMENT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__ID = WORK_BREAKDOWN_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__ORDERING_GUIDE = WORK_BREAKDOWN_ELEMENT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__PRESENTATION_NAME = WORK_BREAKDOWN_ELEMENT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__SUPPRESSED = WORK_BREAKDOWN_ELEMENT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__PRESENTATION = WORK_BREAKDOWN_ELEMENT__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Fulfill</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__FULFILL = WORK_BREAKDOWN_ELEMENT__FULFILL;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__IS_ABSTRACT = WORK_BREAKDOWN_ELEMENT__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__NODEICON = WORK_BREAKDOWN_ELEMENT__NODEICON;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__SHAPEICON = WORK_BREAKDOWN_ELEMENT__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Presented After</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__PRESENTED_AFTER = WORK_BREAKDOWN_ELEMENT__PRESENTED_AFTER;

	/**
	 * The feature id for the '<em><b>Presented Before</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__PRESENTED_BEFORE = WORK_BREAKDOWN_ELEMENT__PRESENTED_BEFORE;

	/**
	 * The feature id for the '<em><b>Planning Data</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__PLANNING_DATA = WORK_BREAKDOWN_ELEMENT__PLANNING_DATA;

	/**
	 * The feature id for the '<em><b>Super Activity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__SUPER_ACTIVITY = WORK_BREAKDOWN_ELEMENT__SUPER_ACTIVITY;

	/**
	 * The feature id for the '<em><b>Group1</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__GROUP1 = WORK_BREAKDOWN_ELEMENT__GROUP1;

	/**
	 * The feature id for the '<em><b>Checklist</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__CHECKLIST = WORK_BREAKDOWN_ELEMENT__CHECKLIST;

	/**
	 * The feature id for the '<em><b>Concept</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__CONCEPT = WORK_BREAKDOWN_ELEMENT__CONCEPT;

	/**
	 * The feature id for the '<em><b>Example</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__EXAMPLE = WORK_BREAKDOWN_ELEMENT__EXAMPLE;

	/**
	 * The feature id for the '<em><b>Guideline</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__GUIDELINE = WORK_BREAKDOWN_ELEMENT__GUIDELINE;

	/**
	 * The feature id for the '<em><b>Reusable Asset</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__REUSABLE_ASSET = WORK_BREAKDOWN_ELEMENT__REUSABLE_ASSET;

	/**
	 * The feature id for the '<em><b>Supporting Material</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__SUPPORTING_MATERIAL = WORK_BREAKDOWN_ELEMENT__SUPPORTING_MATERIAL;

	/**
	 * The feature id for the '<em><b>Whitepaper</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__WHITEPAPER = WORK_BREAKDOWN_ELEMENT__WHITEPAPER;

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
	 * The feature id for the '<em><b>Is Planned</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__IS_PLANNED = WORK_BREAKDOWN_ELEMENT__IS_PLANNED;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__PREFIX = WORK_BREAKDOWN_ELEMENT__PREFIX;

	/**
	 * The feature id for the '<em><b>Group2</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__GROUP2 = WORK_BREAKDOWN_ELEMENT__GROUP2;

	/**
	 * The feature id for the '<em><b>Predecessor</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__PREDECESSOR = WORK_BREAKDOWN_ELEMENT__PREDECESSOR;

	/**
	 * The feature id for the '<em><b>Is Event Driven</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__IS_EVENT_DRIVEN = WORK_BREAKDOWN_ELEMENT__IS_EVENT_DRIVEN;

	/**
	 * The feature id for the '<em><b>Is Ongoing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__IS_ONGOING = WORK_BREAKDOWN_ELEMENT__IS_ONGOING;

	/**
	 * The feature id for the '<em><b>Is Repeatable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__IS_REPEATABLE = WORK_BREAKDOWN_ELEMENT__IS_REPEATABLE;

	/**
	 * The feature id for the '<em><b>Precondition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__PRECONDITION = WORK_BREAKDOWN_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Postcondition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__POSTCONDITION = WORK_BREAKDOWN_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Group3</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__GROUP3 = WORK_BREAKDOWN_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Breakdown Element</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__BREAKDOWN_ELEMENT = WORK_BREAKDOWN_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Roadmap</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__ROADMAP = WORK_BREAKDOWN_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Is Enactable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__IS_ENACTABLE = WORK_BREAKDOWN_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__VARIABILITY_BASED_ON_ELEMENT = WORK_BREAKDOWN_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__VARIABILITY_TYPE = WORK_BREAKDOWN_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The number of structural features of the '<em>Activity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_FEATURE_COUNT = WORK_BREAKDOWN_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.MethodUnitImpl <em>Method Unit</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.MethodUnitImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getMethodUnit()
	 * @generated
	 */
	int METHOD_UNIT = 45;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_UNIT__NAME = METHOD_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_UNIT__GROUP = METHOD_ELEMENT__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_UNIT__OWNED_RULE = METHOD_ELEMENT__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_UNIT__METHOD_ELEMENT_PROPERTY = METHOD_ELEMENT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_UNIT__BRIEF_DESCRIPTION = METHOD_ELEMENT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_UNIT__ID = METHOD_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_UNIT__ORDERING_GUIDE = METHOD_ELEMENT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_UNIT__PRESENTATION_NAME = METHOD_ELEMENT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_UNIT__SUPPRESSED = METHOD_ELEMENT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Copyright</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_UNIT__COPYRIGHT = METHOD_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Authors</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_UNIT__AUTHORS = METHOD_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Change Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_UNIT__CHANGE_DATE = METHOD_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Change Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_UNIT__CHANGE_DESCRIPTION = METHOD_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_UNIT__VERSION = METHOD_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Method Unit</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_UNIT_FEATURE_COUNT = METHOD_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.ContentDescriptionImpl <em>Content Description</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.ContentDescriptionImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getContentDescription()
	 * @generated
	 */
	int CONTENT_DESCRIPTION = 14;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_DESCRIPTION__NAME = METHOD_UNIT__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_DESCRIPTION__GROUP = METHOD_UNIT__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_DESCRIPTION__OWNED_RULE = METHOD_UNIT__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_DESCRIPTION__METHOD_ELEMENT_PROPERTY = METHOD_UNIT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_DESCRIPTION__BRIEF_DESCRIPTION = METHOD_UNIT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_DESCRIPTION__ID = METHOD_UNIT__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_DESCRIPTION__ORDERING_GUIDE = METHOD_UNIT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_DESCRIPTION__PRESENTATION_NAME = METHOD_UNIT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_DESCRIPTION__SUPPRESSED = METHOD_UNIT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Copyright</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_DESCRIPTION__COPYRIGHT = METHOD_UNIT__COPYRIGHT;

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
	 * The feature id for the '<em><b>Main Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_DESCRIPTION__MAIN_DESCRIPTION = METHOD_UNIT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Key Considerations</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_DESCRIPTION__KEY_CONSIDERATIONS = METHOD_UNIT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Section</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_DESCRIPTION__SECTION = METHOD_UNIT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>External Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_DESCRIPTION__EXTERNAL_ID = METHOD_UNIT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Content Description</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_DESCRIPTION_FEATURE_COUNT = METHOD_UNIT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.BreakdownElementDescriptionImpl <em>Breakdown Element Description</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.BreakdownElementDescriptionImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getBreakdownElementDescription()
	 * @generated
	 */
	int BREAKDOWN_ELEMENT_DESCRIPTION = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT_DESCRIPTION__NAME = CONTENT_DESCRIPTION__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT_DESCRIPTION__GROUP = CONTENT_DESCRIPTION__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT_DESCRIPTION__OWNED_RULE = CONTENT_DESCRIPTION__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT_DESCRIPTION__METHOD_ELEMENT_PROPERTY = CONTENT_DESCRIPTION__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT_DESCRIPTION__BRIEF_DESCRIPTION = CONTENT_DESCRIPTION__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT_DESCRIPTION__ID = CONTENT_DESCRIPTION__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT_DESCRIPTION__ORDERING_GUIDE = CONTENT_DESCRIPTION__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT_DESCRIPTION__PRESENTATION_NAME = CONTENT_DESCRIPTION__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT_DESCRIPTION__SUPPRESSED = CONTENT_DESCRIPTION__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Copyright</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT_DESCRIPTION__COPYRIGHT = CONTENT_DESCRIPTION__COPYRIGHT;

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
	 * The feature id for the '<em><b>Main Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT_DESCRIPTION__MAIN_DESCRIPTION = CONTENT_DESCRIPTION__MAIN_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Key Considerations</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT_DESCRIPTION__KEY_CONSIDERATIONS = CONTENT_DESCRIPTION__KEY_CONSIDERATIONS;

	/**
	 * The feature id for the '<em><b>Section</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT_DESCRIPTION__SECTION = CONTENT_DESCRIPTION__SECTION;

	/**
	 * The feature id for the '<em><b>External Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAKDOWN_ELEMENT_DESCRIPTION__EXTERNAL_ID = CONTENT_DESCRIPTION__EXTERNAL_ID;

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
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.ActivityDescriptionImpl <em>Activity Description</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.ActivityDescriptionImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getActivityDescription()
	 * @generated
	 */
	int ACTIVITY_DESCRIPTION = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_DESCRIPTION__NAME = BREAKDOWN_ELEMENT_DESCRIPTION__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_DESCRIPTION__GROUP = BREAKDOWN_ELEMENT_DESCRIPTION__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_DESCRIPTION__OWNED_RULE = BREAKDOWN_ELEMENT_DESCRIPTION__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_DESCRIPTION__METHOD_ELEMENT_PROPERTY = BREAKDOWN_ELEMENT_DESCRIPTION__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_DESCRIPTION__BRIEF_DESCRIPTION = BREAKDOWN_ELEMENT_DESCRIPTION__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_DESCRIPTION__ID = BREAKDOWN_ELEMENT_DESCRIPTION__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_DESCRIPTION__ORDERING_GUIDE = BREAKDOWN_ELEMENT_DESCRIPTION__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_DESCRIPTION__PRESENTATION_NAME = BREAKDOWN_ELEMENT_DESCRIPTION__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_DESCRIPTION__SUPPRESSED = BREAKDOWN_ELEMENT_DESCRIPTION__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Copyright</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_DESCRIPTION__COPYRIGHT = BREAKDOWN_ELEMENT_DESCRIPTION__COPYRIGHT;

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
	 * The feature id for the '<em><b>Main Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_DESCRIPTION__MAIN_DESCRIPTION = BREAKDOWN_ELEMENT_DESCRIPTION__MAIN_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Key Considerations</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_DESCRIPTION__KEY_CONSIDERATIONS = BREAKDOWN_ELEMENT_DESCRIPTION__KEY_CONSIDERATIONS;

	/**
	 * The feature id for the '<em><b>Section</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_DESCRIPTION__SECTION = BREAKDOWN_ELEMENT_DESCRIPTION__SECTION;

	/**
	 * The feature id for the '<em><b>External Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_DESCRIPTION__EXTERNAL_ID = BREAKDOWN_ELEMENT_DESCRIPTION__EXTERNAL_ID;

	/**
	 * The feature id for the '<em><b>Usage Guidance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_DESCRIPTION__USAGE_GUIDANCE = BREAKDOWN_ELEMENT_DESCRIPTION__USAGE_GUIDANCE;

	/**
	 * The feature id for the '<em><b>Alternatives</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_DESCRIPTION__ALTERNATIVES = BREAKDOWN_ELEMENT_DESCRIPTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>How To Staff</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_DESCRIPTION__HOW_TO_STAFF = BREAKDOWN_ELEMENT_DESCRIPTION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Purpose</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_DESCRIPTION__PURPOSE = BREAKDOWN_ELEMENT_DESCRIPTION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Activity Description</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_DESCRIPTION_FEATURE_COUNT = BREAKDOWN_ELEMENT_DESCRIPTION_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.ApplicableMetaClassInfoImpl <em>Applicable Meta Class Info</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.ApplicableMetaClassInfoImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getApplicableMetaClassInfo()
	 * @generated
	 */
	int APPLICABLE_META_CLASS_INFO = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICABLE_META_CLASS_INFO__NAME = PACKAGEABLE_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Is Primary Extension</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICABLE_META_CLASS_INFO__IS_PRIMARY_EXTENSION = PACKAGEABLE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Applicable Meta Class Info</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICABLE_META_CLASS_INFO_FEATURE_COUNT = PACKAGEABLE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.ContentElementImpl <em>Content Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.ContentElementImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getContentElement()
	 * @generated
	 */
	int CONTENT_ELEMENT = 15;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ELEMENT__NAME = DESCRIBABLE_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ELEMENT__GROUP = DESCRIBABLE_ELEMENT__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ELEMENT__OWNED_RULE = DESCRIBABLE_ELEMENT__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ELEMENT__METHOD_ELEMENT_PROPERTY = DESCRIBABLE_ELEMENT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ELEMENT__BRIEF_DESCRIPTION = DESCRIBABLE_ELEMENT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ELEMENT__ID = DESCRIBABLE_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ELEMENT__ORDERING_GUIDE = DESCRIBABLE_ELEMENT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ELEMENT__PRESENTATION_NAME = DESCRIBABLE_ELEMENT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ELEMENT__SUPPRESSED = DESCRIBABLE_ELEMENT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ELEMENT__PRESENTATION = DESCRIBABLE_ELEMENT__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Fulfill</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ELEMENT__FULFILL = DESCRIBABLE_ELEMENT__FULFILL;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ELEMENT__IS_ABSTRACT = DESCRIBABLE_ELEMENT__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ELEMENT__NODEICON = DESCRIBABLE_ELEMENT__NODEICON;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ELEMENT__SHAPEICON = DESCRIBABLE_ELEMENT__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Group1</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ELEMENT__GROUP1 = DESCRIBABLE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Checklist</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ELEMENT__CHECKLIST = DESCRIBABLE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Concept</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ELEMENT__CONCEPT = DESCRIBABLE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Example</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ELEMENT__EXAMPLE = DESCRIBABLE_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Guideline</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ELEMENT__GUIDELINE = DESCRIBABLE_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Reusable Asset</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ELEMENT__REUSABLE_ASSET = DESCRIBABLE_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Supporting Material</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ELEMENT__SUPPORTING_MATERIAL = DESCRIBABLE_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Whitepaper</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ELEMENT__WHITEPAPER = DESCRIBABLE_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ELEMENT__VARIABILITY_BASED_ON_ELEMENT = DESCRIBABLE_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ELEMENT__VARIABILITY_TYPE = DESCRIBABLE_ELEMENT_FEATURE_COUNT + 9;

	/**
	 * The number of structural features of the '<em>Content Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ELEMENT_FEATURE_COUNT = DESCRIBABLE_ELEMENT_FEATURE_COUNT + 10;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.WorkProductImpl <em>Work Product</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.WorkProductImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getWorkProduct()
	 * @generated
	 */
	int WORK_PRODUCT = 83;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT__NAME = CONTENT_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT__GROUP = CONTENT_ELEMENT__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT__OWNED_RULE = CONTENT_ELEMENT__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT__METHOD_ELEMENT_PROPERTY = CONTENT_ELEMENT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT__BRIEF_DESCRIPTION = CONTENT_ELEMENT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT__ID = CONTENT_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT__ORDERING_GUIDE = CONTENT_ELEMENT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT__PRESENTATION_NAME = CONTENT_ELEMENT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT__SUPPRESSED = CONTENT_ELEMENT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT__PRESENTATION = CONTENT_ELEMENT__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Fulfill</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT__FULFILL = CONTENT_ELEMENT__FULFILL;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT__IS_ABSTRACT = CONTENT_ELEMENT__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT__NODEICON = CONTENT_ELEMENT__NODEICON;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT__SHAPEICON = CONTENT_ELEMENT__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Group1</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT__GROUP1 = CONTENT_ELEMENT__GROUP1;

	/**
	 * The feature id for the '<em><b>Checklist</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT__CHECKLIST = CONTENT_ELEMENT__CHECKLIST;

	/**
	 * The feature id for the '<em><b>Concept</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT__CONCEPT = CONTENT_ELEMENT__CONCEPT;

	/**
	 * The feature id for the '<em><b>Example</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT__EXAMPLE = CONTENT_ELEMENT__EXAMPLE;

	/**
	 * The feature id for the '<em><b>Guideline</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT__GUIDELINE = CONTENT_ELEMENT__GUIDELINE;

	/**
	 * The feature id for the '<em><b>Reusable Asset</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT__REUSABLE_ASSET = CONTENT_ELEMENT__REUSABLE_ASSET;

	/**
	 * The feature id for the '<em><b>Supporting Material</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT__SUPPORTING_MATERIAL = CONTENT_ELEMENT__SUPPORTING_MATERIAL;

	/**
	 * The feature id for the '<em><b>Whitepaper</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT__WHITEPAPER = CONTENT_ELEMENT__WHITEPAPER;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT__VARIABILITY_BASED_ON_ELEMENT = CONTENT_ELEMENT__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT__VARIABILITY_TYPE = CONTENT_ELEMENT__VARIABILITY_TYPE;

	/**
	 * The feature id for the '<em><b>Group2</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT__GROUP2 = CONTENT_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Estimate</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT__ESTIMATE = CONTENT_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Estimation Considerations</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT__ESTIMATION_CONSIDERATIONS = CONTENT_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Report</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT__REPORT = CONTENT_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Template</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT__TEMPLATE = CONTENT_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Tool Mentor</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT__TOOL_MENTOR = CONTENT_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Work Product</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_FEATURE_COUNT = CONTENT_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.ArtifactImpl <em>Artifact</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.ArtifactImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getArtifact()
	 * @generated
	 */
	int ARTIFACT = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__NAME = WORK_PRODUCT__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__GROUP = WORK_PRODUCT__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__OWNED_RULE = WORK_PRODUCT__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__METHOD_ELEMENT_PROPERTY = WORK_PRODUCT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__BRIEF_DESCRIPTION = WORK_PRODUCT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__ID = WORK_PRODUCT__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__ORDERING_GUIDE = WORK_PRODUCT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__PRESENTATION_NAME = WORK_PRODUCT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__SUPPRESSED = WORK_PRODUCT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__PRESENTATION = WORK_PRODUCT__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Fulfill</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__FULFILL = WORK_PRODUCT__FULFILL;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__IS_ABSTRACT = WORK_PRODUCT__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__NODEICON = WORK_PRODUCT__NODEICON;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__SHAPEICON = WORK_PRODUCT__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Group1</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__GROUP1 = WORK_PRODUCT__GROUP1;

	/**
	 * The feature id for the '<em><b>Checklist</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__CHECKLIST = WORK_PRODUCT__CHECKLIST;

	/**
	 * The feature id for the '<em><b>Concept</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__CONCEPT = WORK_PRODUCT__CONCEPT;

	/**
	 * The feature id for the '<em><b>Example</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__EXAMPLE = WORK_PRODUCT__EXAMPLE;

	/**
	 * The feature id for the '<em><b>Guideline</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__GUIDELINE = WORK_PRODUCT__GUIDELINE;

	/**
	 * The feature id for the '<em><b>Reusable Asset</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__REUSABLE_ASSET = WORK_PRODUCT__REUSABLE_ASSET;

	/**
	 * The feature id for the '<em><b>Supporting Material</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__SUPPORTING_MATERIAL = WORK_PRODUCT__SUPPORTING_MATERIAL;

	/**
	 * The feature id for the '<em><b>Whitepaper</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__WHITEPAPER = WORK_PRODUCT__WHITEPAPER;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__VARIABILITY_BASED_ON_ELEMENT = WORK_PRODUCT__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__VARIABILITY_TYPE = WORK_PRODUCT__VARIABILITY_TYPE;

	/**
	 * The feature id for the '<em><b>Group2</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__GROUP2 = WORK_PRODUCT__GROUP2;

	/**
	 * The feature id for the '<em><b>Estimate</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__ESTIMATE = WORK_PRODUCT__ESTIMATE;

	/**
	 * The feature id for the '<em><b>Estimation Considerations</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__ESTIMATION_CONSIDERATIONS = WORK_PRODUCT__ESTIMATION_CONSIDERATIONS;

	/**
	 * The feature id for the '<em><b>Report</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__REPORT = WORK_PRODUCT__REPORT;

	/**
	 * The feature id for the '<em><b>Template</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__TEMPLATE = WORK_PRODUCT__TEMPLATE;

	/**
	 * The feature id for the '<em><b>Tool Mentor</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__TOOL_MENTOR = WORK_PRODUCT__TOOL_MENTOR;

	/**
	 * The feature id for the '<em><b>Group3</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__GROUP3 = WORK_PRODUCT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Contained Artifact</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__CONTAINED_ARTIFACT = WORK_PRODUCT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Artifact</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_FEATURE_COUNT = WORK_PRODUCT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.WorkProductDescriptionImpl <em>Work Product Description</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.WorkProductDescriptionImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getWorkProductDescription()
	 * @generated
	 */
	int WORK_PRODUCT_DESCRIPTION = 84;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTION__NAME = CONTENT_DESCRIPTION__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTION__GROUP = CONTENT_DESCRIPTION__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTION__OWNED_RULE = CONTENT_DESCRIPTION__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTION__METHOD_ELEMENT_PROPERTY = CONTENT_DESCRIPTION__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTION__BRIEF_DESCRIPTION = CONTENT_DESCRIPTION__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTION__ID = CONTENT_DESCRIPTION__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTION__ORDERING_GUIDE = CONTENT_DESCRIPTION__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTION__PRESENTATION_NAME = CONTENT_DESCRIPTION__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTION__SUPPRESSED = CONTENT_DESCRIPTION__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Copyright</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTION__COPYRIGHT = CONTENT_DESCRIPTION__COPYRIGHT;

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
	 * The feature id for the '<em><b>Main Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTION__MAIN_DESCRIPTION = CONTENT_DESCRIPTION__MAIN_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Key Considerations</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTION__KEY_CONSIDERATIONS = CONTENT_DESCRIPTION__KEY_CONSIDERATIONS;

	/**
	 * The feature id for the '<em><b>Section</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTION__SECTION = CONTENT_DESCRIPTION__SECTION;

	/**
	 * The feature id for the '<em><b>External Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTION__EXTERNAL_ID = CONTENT_DESCRIPTION__EXTERNAL_ID;

	/**
	 * The feature id for the '<em><b>Impact Of Not Having</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTION__IMPACT_OF_NOT_HAVING = CONTENT_DESCRIPTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Purpose</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTION__PURPOSE = CONTENT_DESCRIPTION_FEATURE_COUNT + 1;

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
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.ArtifactDescriptionImpl <em>Artifact Description</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.ArtifactDescriptionImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getArtifactDescription()
	 * @generated
	 */
	int ARTIFACT_DESCRIPTION = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_DESCRIPTION__NAME = WORK_PRODUCT_DESCRIPTION__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_DESCRIPTION__GROUP = WORK_PRODUCT_DESCRIPTION__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_DESCRIPTION__OWNED_RULE = WORK_PRODUCT_DESCRIPTION__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_DESCRIPTION__METHOD_ELEMENT_PROPERTY = WORK_PRODUCT_DESCRIPTION__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_DESCRIPTION__BRIEF_DESCRIPTION = WORK_PRODUCT_DESCRIPTION__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_DESCRIPTION__ID = WORK_PRODUCT_DESCRIPTION__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_DESCRIPTION__ORDERING_GUIDE = WORK_PRODUCT_DESCRIPTION__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_DESCRIPTION__PRESENTATION_NAME = WORK_PRODUCT_DESCRIPTION__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_DESCRIPTION__SUPPRESSED = WORK_PRODUCT_DESCRIPTION__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Copyright</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_DESCRIPTION__COPYRIGHT = WORK_PRODUCT_DESCRIPTION__COPYRIGHT;

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
	 * The feature id for the '<em><b>Main Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_DESCRIPTION__MAIN_DESCRIPTION = WORK_PRODUCT_DESCRIPTION__MAIN_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Key Considerations</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_DESCRIPTION__KEY_CONSIDERATIONS = WORK_PRODUCT_DESCRIPTION__KEY_CONSIDERATIONS;

	/**
	 * The feature id for the '<em><b>Section</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_DESCRIPTION__SECTION = WORK_PRODUCT_DESCRIPTION__SECTION;

	/**
	 * The feature id for the '<em><b>External Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_DESCRIPTION__EXTERNAL_ID = WORK_PRODUCT_DESCRIPTION__EXTERNAL_ID;

	/**
	 * The feature id for the '<em><b>Impact Of Not Having</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_DESCRIPTION__IMPACT_OF_NOT_HAVING = WORK_PRODUCT_DESCRIPTION__IMPACT_OF_NOT_HAVING;

	/**
	 * The feature id for the '<em><b>Purpose</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_DESCRIPTION__PURPOSE = WORK_PRODUCT_DESCRIPTION__PURPOSE;

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
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.ProcessImpl <em>Process</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.ProcessImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getProcess()
	 * @generated
	 */
	int PROCESS = 54;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__NAME = ACTIVITY__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__GROUP = ACTIVITY__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__OWNED_RULE = ACTIVITY__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__METHOD_ELEMENT_PROPERTY = ACTIVITY__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__BRIEF_DESCRIPTION = ACTIVITY__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__ID = ACTIVITY__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__ORDERING_GUIDE = ACTIVITY__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__PRESENTATION_NAME = ACTIVITY__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__SUPPRESSED = ACTIVITY__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__PRESENTATION = ACTIVITY__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Fulfill</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__FULFILL = ACTIVITY__FULFILL;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__IS_ABSTRACT = ACTIVITY__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__NODEICON = ACTIVITY__NODEICON;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__SHAPEICON = ACTIVITY__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Presented After</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__PRESENTED_AFTER = ACTIVITY__PRESENTED_AFTER;

	/**
	 * The feature id for the '<em><b>Presented Before</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__PRESENTED_BEFORE = ACTIVITY__PRESENTED_BEFORE;

	/**
	 * The feature id for the '<em><b>Planning Data</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__PLANNING_DATA = ACTIVITY__PLANNING_DATA;

	/**
	 * The feature id for the '<em><b>Super Activity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__SUPER_ACTIVITY = ACTIVITY__SUPER_ACTIVITY;

	/**
	 * The feature id for the '<em><b>Group1</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__GROUP1 = ACTIVITY__GROUP1;

	/**
	 * The feature id for the '<em><b>Checklist</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__CHECKLIST = ACTIVITY__CHECKLIST;

	/**
	 * The feature id for the '<em><b>Concept</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__CONCEPT = ACTIVITY__CONCEPT;

	/**
	 * The feature id for the '<em><b>Example</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__EXAMPLE = ACTIVITY__EXAMPLE;

	/**
	 * The feature id for the '<em><b>Guideline</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__GUIDELINE = ACTIVITY__GUIDELINE;

	/**
	 * The feature id for the '<em><b>Reusable Asset</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__REUSABLE_ASSET = ACTIVITY__REUSABLE_ASSET;

	/**
	 * The feature id for the '<em><b>Supporting Material</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__SUPPORTING_MATERIAL = ACTIVITY__SUPPORTING_MATERIAL;

	/**
	 * The feature id for the '<em><b>Whitepaper</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__WHITEPAPER = ACTIVITY__WHITEPAPER;

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
	 * The feature id for the '<em><b>Is Planned</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__IS_PLANNED = ACTIVITY__IS_PLANNED;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__PREFIX = ACTIVITY__PREFIX;

	/**
	 * The feature id for the '<em><b>Group2</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__GROUP2 = ACTIVITY__GROUP2;

	/**
	 * The feature id for the '<em><b>Predecessor</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__PREDECESSOR = ACTIVITY__PREDECESSOR;

	/**
	 * The feature id for the '<em><b>Is Event Driven</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__IS_EVENT_DRIVEN = ACTIVITY__IS_EVENT_DRIVEN;

	/**
	 * The feature id for the '<em><b>Is Ongoing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__IS_ONGOING = ACTIVITY__IS_ONGOING;

	/**
	 * The feature id for the '<em><b>Is Repeatable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__IS_REPEATABLE = ACTIVITY__IS_REPEATABLE;

	/**
	 * The feature id for the '<em><b>Precondition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__PRECONDITION = ACTIVITY__PRECONDITION;

	/**
	 * The feature id for the '<em><b>Postcondition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__POSTCONDITION = ACTIVITY__POSTCONDITION;

	/**
	 * The feature id for the '<em><b>Group3</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__GROUP3 = ACTIVITY__GROUP3;

	/**
	 * The feature id for the '<em><b>Breakdown Element</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__BREAKDOWN_ELEMENT = ACTIVITY__BREAKDOWN_ELEMENT;

	/**
	 * The feature id for the '<em><b>Roadmap</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__ROADMAP = ACTIVITY__ROADMAP;

	/**
	 * The feature id for the '<em><b>Is Enactable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__IS_ENACTABLE = ACTIVITY__IS_ENACTABLE;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__VARIABILITY_BASED_ON_ELEMENT = ACTIVITY__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__VARIABILITY_TYPE = ACTIVITY__VARIABILITY_TYPE;

	/**
	 * The feature id for the '<em><b>Includes Pattern</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__INCLUDES_PATTERN = ACTIVITY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Default Context</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__DEFAULT_CONTEXT = ACTIVITY_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Valid Context</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__VALID_CONTEXT = ACTIVITY_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Diagram URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__DIAGRAM_URI = ACTIVITY_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Process</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_FEATURE_COUNT = ACTIVITY_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.CapabilityPatternImpl <em>Capability Pattern</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.CapabilityPatternImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getCapabilityPattern()
	 * @generated
	 */
	int CAPABILITY_PATTERN = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__NAME = PROCESS__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__GROUP = PROCESS__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__OWNED_RULE = PROCESS__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__METHOD_ELEMENT_PROPERTY = PROCESS__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__BRIEF_DESCRIPTION = PROCESS__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__ID = PROCESS__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__ORDERING_GUIDE = PROCESS__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__PRESENTATION_NAME = PROCESS__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__SUPPRESSED = PROCESS__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__PRESENTATION = PROCESS__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Fulfill</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__FULFILL = PROCESS__FULFILL;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__IS_ABSTRACT = PROCESS__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__NODEICON = PROCESS__NODEICON;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__SHAPEICON = PROCESS__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Presented After</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__PRESENTED_AFTER = PROCESS__PRESENTED_AFTER;

	/**
	 * The feature id for the '<em><b>Presented Before</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__PRESENTED_BEFORE = PROCESS__PRESENTED_BEFORE;

	/**
	 * The feature id for the '<em><b>Planning Data</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__PLANNING_DATA = PROCESS__PLANNING_DATA;

	/**
	 * The feature id for the '<em><b>Super Activity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__SUPER_ACTIVITY = PROCESS__SUPER_ACTIVITY;

	/**
	 * The feature id for the '<em><b>Group1</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__GROUP1 = PROCESS__GROUP1;

	/**
	 * The feature id for the '<em><b>Checklist</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__CHECKLIST = PROCESS__CHECKLIST;

	/**
	 * The feature id for the '<em><b>Concept</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__CONCEPT = PROCESS__CONCEPT;

	/**
	 * The feature id for the '<em><b>Example</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__EXAMPLE = PROCESS__EXAMPLE;

	/**
	 * The feature id for the '<em><b>Guideline</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__GUIDELINE = PROCESS__GUIDELINE;

	/**
	 * The feature id for the '<em><b>Reusable Asset</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__REUSABLE_ASSET = PROCESS__REUSABLE_ASSET;

	/**
	 * The feature id for the '<em><b>Supporting Material</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__SUPPORTING_MATERIAL = PROCESS__SUPPORTING_MATERIAL;

	/**
	 * The feature id for the '<em><b>Whitepaper</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__WHITEPAPER = PROCESS__WHITEPAPER;

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
	 * The feature id for the '<em><b>Is Planned</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__IS_PLANNED = PROCESS__IS_PLANNED;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__PREFIX = PROCESS__PREFIX;

	/**
	 * The feature id for the '<em><b>Group2</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__GROUP2 = PROCESS__GROUP2;

	/**
	 * The feature id for the '<em><b>Predecessor</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__PREDECESSOR = PROCESS__PREDECESSOR;

	/**
	 * The feature id for the '<em><b>Is Event Driven</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__IS_EVENT_DRIVEN = PROCESS__IS_EVENT_DRIVEN;

	/**
	 * The feature id for the '<em><b>Is Ongoing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__IS_ONGOING = PROCESS__IS_ONGOING;

	/**
	 * The feature id for the '<em><b>Is Repeatable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__IS_REPEATABLE = PROCESS__IS_REPEATABLE;

	/**
	 * The feature id for the '<em><b>Precondition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__PRECONDITION = PROCESS__PRECONDITION;

	/**
	 * The feature id for the '<em><b>Postcondition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__POSTCONDITION = PROCESS__POSTCONDITION;

	/**
	 * The feature id for the '<em><b>Group3</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__GROUP3 = PROCESS__GROUP3;

	/**
	 * The feature id for the '<em><b>Breakdown Element</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__BREAKDOWN_ELEMENT = PROCESS__BREAKDOWN_ELEMENT;

	/**
	 * The feature id for the '<em><b>Roadmap</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__ROADMAP = PROCESS__ROADMAP;

	/**
	 * The feature id for the '<em><b>Is Enactable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__IS_ENACTABLE = PROCESS__IS_ENACTABLE;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__VARIABILITY_BASED_ON_ELEMENT = PROCESS__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__VARIABILITY_TYPE = PROCESS__VARIABILITY_TYPE;

	/**
	 * The feature id for the '<em><b>Includes Pattern</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__INCLUDES_PATTERN = PROCESS__INCLUDES_PATTERN;

	/**
	 * The feature id for the '<em><b>Default Context</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__DEFAULT_CONTEXT = PROCESS__DEFAULT_CONTEXT;

	/**
	 * The feature id for the '<em><b>Valid Context</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__VALID_CONTEXT = PROCESS__VALID_CONTEXT;

	/**
	 * The feature id for the '<em><b>Diagram URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN__DIAGRAM_URI = PROCESS__DIAGRAM_URI;

	/**
	 * The number of structural features of the '<em>Capability Pattern</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PATTERN_FEATURE_COUNT = PROCESS_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.GuidanceImpl <em>Guidance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.GuidanceImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getGuidance()
	 * @generated
	 */
	int GUIDANCE = 34;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE__NAME = CONTENT_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE__GROUP = CONTENT_ELEMENT__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE__OWNED_RULE = CONTENT_ELEMENT__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE__METHOD_ELEMENT_PROPERTY = CONTENT_ELEMENT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE__BRIEF_DESCRIPTION = CONTENT_ELEMENT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE__ID = CONTENT_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE__ORDERING_GUIDE = CONTENT_ELEMENT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE__PRESENTATION_NAME = CONTENT_ELEMENT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE__SUPPRESSED = CONTENT_ELEMENT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE__PRESENTATION = CONTENT_ELEMENT__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Fulfill</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE__FULFILL = CONTENT_ELEMENT__FULFILL;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE__IS_ABSTRACT = CONTENT_ELEMENT__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE__NODEICON = CONTENT_ELEMENT__NODEICON;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE__SHAPEICON = CONTENT_ELEMENT__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Group1</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE__GROUP1 = CONTENT_ELEMENT__GROUP1;

	/**
	 * The feature id for the '<em><b>Checklist</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE__CHECKLIST = CONTENT_ELEMENT__CHECKLIST;

	/**
	 * The feature id for the '<em><b>Concept</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE__CONCEPT = CONTENT_ELEMENT__CONCEPT;

	/**
	 * The feature id for the '<em><b>Example</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE__EXAMPLE = CONTENT_ELEMENT__EXAMPLE;

	/**
	 * The feature id for the '<em><b>Guideline</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE__GUIDELINE = CONTENT_ELEMENT__GUIDELINE;

	/**
	 * The feature id for the '<em><b>Reusable Asset</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE__REUSABLE_ASSET = CONTENT_ELEMENT__REUSABLE_ASSET;

	/**
	 * The feature id for the '<em><b>Supporting Material</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE__SUPPORTING_MATERIAL = CONTENT_ELEMENT__SUPPORTING_MATERIAL;

	/**
	 * The feature id for the '<em><b>Whitepaper</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE__WHITEPAPER = CONTENT_ELEMENT__WHITEPAPER;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE__VARIABILITY_BASED_ON_ELEMENT = CONTENT_ELEMENT__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE__VARIABILITY_TYPE = CONTENT_ELEMENT__VARIABILITY_TYPE;

	/**
	 * The number of structural features of the '<em>Guidance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE_FEATURE_COUNT = CONTENT_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.ChecklistImpl <em>Checklist</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.ChecklistImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getChecklist()
	 * @generated
	 */
	int CHECKLIST = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKLIST__NAME = GUIDANCE__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKLIST__GROUP = GUIDANCE__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKLIST__OWNED_RULE = GUIDANCE__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKLIST__METHOD_ELEMENT_PROPERTY = GUIDANCE__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKLIST__BRIEF_DESCRIPTION = GUIDANCE__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKLIST__ID = GUIDANCE__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKLIST__ORDERING_GUIDE = GUIDANCE__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKLIST__PRESENTATION_NAME = GUIDANCE__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKLIST__SUPPRESSED = GUIDANCE__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKLIST__PRESENTATION = GUIDANCE__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Fulfill</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKLIST__FULFILL = GUIDANCE__FULFILL;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKLIST__IS_ABSTRACT = GUIDANCE__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKLIST__NODEICON = GUIDANCE__NODEICON;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKLIST__SHAPEICON = GUIDANCE__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Group1</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKLIST__GROUP1 = GUIDANCE__GROUP1;

	/**
	 * The feature id for the '<em><b>Checklist</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKLIST__CHECKLIST = GUIDANCE__CHECKLIST;

	/**
	 * The feature id for the '<em><b>Concept</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKLIST__CONCEPT = GUIDANCE__CONCEPT;

	/**
	 * The feature id for the '<em><b>Example</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKLIST__EXAMPLE = GUIDANCE__EXAMPLE;

	/**
	 * The feature id for the '<em><b>Guideline</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKLIST__GUIDELINE = GUIDANCE__GUIDELINE;

	/**
	 * The feature id for the '<em><b>Reusable Asset</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKLIST__REUSABLE_ASSET = GUIDANCE__REUSABLE_ASSET;

	/**
	 * The feature id for the '<em><b>Supporting Material</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKLIST__SUPPORTING_MATERIAL = GUIDANCE__SUPPORTING_MATERIAL;

	/**
	 * The feature id for the '<em><b>Whitepaper</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKLIST__WHITEPAPER = GUIDANCE__WHITEPAPER;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKLIST__VARIABILITY_BASED_ON_ELEMENT = GUIDANCE__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKLIST__VARIABILITY_TYPE = GUIDANCE__VARIABILITY_TYPE;

	/**
	 * The number of structural features of the '<em>Checklist</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKLIST_FEATURE_COUNT = GUIDANCE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.DescriptorImpl <em>Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.DescriptorImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getDescriptor()
	 * @generated
	 */
	int DESCRIPTOR = 23;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__NAME = BREAKDOWN_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__GROUP = BREAKDOWN_ELEMENT__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__OWNED_RULE = BREAKDOWN_ELEMENT__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__METHOD_ELEMENT_PROPERTY = BREAKDOWN_ELEMENT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__BRIEF_DESCRIPTION = BREAKDOWN_ELEMENT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__ID = BREAKDOWN_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__ORDERING_GUIDE = BREAKDOWN_ELEMENT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__PRESENTATION_NAME = BREAKDOWN_ELEMENT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__SUPPRESSED = BREAKDOWN_ELEMENT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__PRESENTATION = BREAKDOWN_ELEMENT__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Fulfill</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__FULFILL = BREAKDOWN_ELEMENT__FULFILL;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__IS_ABSTRACT = BREAKDOWN_ELEMENT__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__NODEICON = BREAKDOWN_ELEMENT__NODEICON;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__SHAPEICON = BREAKDOWN_ELEMENT__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Presented After</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__PRESENTED_AFTER = BREAKDOWN_ELEMENT__PRESENTED_AFTER;

	/**
	 * The feature id for the '<em><b>Presented Before</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__PRESENTED_BEFORE = BREAKDOWN_ELEMENT__PRESENTED_BEFORE;

	/**
	 * The feature id for the '<em><b>Planning Data</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__PLANNING_DATA = BREAKDOWN_ELEMENT__PLANNING_DATA;

	/**
	 * The feature id for the '<em><b>Super Activity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__SUPER_ACTIVITY = BREAKDOWN_ELEMENT__SUPER_ACTIVITY;

	/**
	 * The feature id for the '<em><b>Group1</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__GROUP1 = BREAKDOWN_ELEMENT__GROUP1;

	/**
	 * The feature id for the '<em><b>Checklist</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__CHECKLIST = BREAKDOWN_ELEMENT__CHECKLIST;

	/**
	 * The feature id for the '<em><b>Concept</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__CONCEPT = BREAKDOWN_ELEMENT__CONCEPT;

	/**
	 * The feature id for the '<em><b>Example</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__EXAMPLE = BREAKDOWN_ELEMENT__EXAMPLE;

	/**
	 * The feature id for the '<em><b>Guideline</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__GUIDELINE = BREAKDOWN_ELEMENT__GUIDELINE;

	/**
	 * The feature id for the '<em><b>Reusable Asset</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__REUSABLE_ASSET = BREAKDOWN_ELEMENT__REUSABLE_ASSET;

	/**
	 * The feature id for the '<em><b>Supporting Material</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__SUPPORTING_MATERIAL = BREAKDOWN_ELEMENT__SUPPORTING_MATERIAL;

	/**
	 * The feature id for the '<em><b>Whitepaper</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__WHITEPAPER = BREAKDOWN_ELEMENT__WHITEPAPER;

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
	 * The feature id for the '<em><b>Is Planned</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__IS_PLANNED = BREAKDOWN_ELEMENT__IS_PLANNED;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__PREFIX = BREAKDOWN_ELEMENT__PREFIX;

	/**
	 * The feature id for the '<em><b>Is Synchronized With Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR__IS_SYNCHRONIZED_WITH_SOURCE = BREAKDOWN_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR_FEATURE_COUNT = BREAKDOWN_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.RoleDescriptorImpl <em>Role Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.RoleDescriptorImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getRoleDescriptor()
	 * @generated
	 */
	int ROLE_DESCRIPTOR = 66;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__NAME = DESCRIPTOR__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__GROUP = DESCRIPTOR__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__OWNED_RULE = DESCRIPTOR__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__METHOD_ELEMENT_PROPERTY = DESCRIPTOR__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__BRIEF_DESCRIPTION = DESCRIPTOR__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__ID = DESCRIPTOR__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__ORDERING_GUIDE = DESCRIPTOR__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__PRESENTATION_NAME = DESCRIPTOR__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__SUPPRESSED = DESCRIPTOR__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__PRESENTATION = DESCRIPTOR__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Fulfill</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__FULFILL = DESCRIPTOR__FULFILL;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__IS_ABSTRACT = DESCRIPTOR__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__NODEICON = DESCRIPTOR__NODEICON;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__SHAPEICON = DESCRIPTOR__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Presented After</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__PRESENTED_AFTER = DESCRIPTOR__PRESENTED_AFTER;

	/**
	 * The feature id for the '<em><b>Presented Before</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__PRESENTED_BEFORE = DESCRIPTOR__PRESENTED_BEFORE;

	/**
	 * The feature id for the '<em><b>Planning Data</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__PLANNING_DATA = DESCRIPTOR__PLANNING_DATA;

	/**
	 * The feature id for the '<em><b>Super Activity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__SUPER_ACTIVITY = DESCRIPTOR__SUPER_ACTIVITY;

	/**
	 * The feature id for the '<em><b>Group1</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__GROUP1 = DESCRIPTOR__GROUP1;

	/**
	 * The feature id for the '<em><b>Checklist</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__CHECKLIST = DESCRIPTOR__CHECKLIST;

	/**
	 * The feature id for the '<em><b>Concept</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__CONCEPT = DESCRIPTOR__CONCEPT;

	/**
	 * The feature id for the '<em><b>Example</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__EXAMPLE = DESCRIPTOR__EXAMPLE;

	/**
	 * The feature id for the '<em><b>Guideline</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__GUIDELINE = DESCRIPTOR__GUIDELINE;

	/**
	 * The feature id for the '<em><b>Reusable Asset</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__REUSABLE_ASSET = DESCRIPTOR__REUSABLE_ASSET;

	/**
	 * The feature id for the '<em><b>Supporting Material</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__SUPPORTING_MATERIAL = DESCRIPTOR__SUPPORTING_MATERIAL;

	/**
	 * The feature id for the '<em><b>Whitepaper</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__WHITEPAPER = DESCRIPTOR__WHITEPAPER;

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
	 * The feature id for the '<em><b>Is Planned</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__IS_PLANNED = DESCRIPTOR__IS_PLANNED;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__PREFIX = DESCRIPTOR__PREFIX;

	/**
	 * The feature id for the '<em><b>Is Synchronized With Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__IS_SYNCHRONIZED_WITH_SOURCE = DESCRIPTOR__IS_SYNCHRONIZED_WITH_SOURCE;

	/**
	 * The feature id for the '<em><b>Role</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__ROLE = DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Responsible For</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR__RESPONSIBLE_FOR = DESCRIPTOR_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Role Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTOR_FEATURE_COUNT = DESCRIPTOR_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.CompositeRoleImpl <em>Composite Role</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.CompositeRoleImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getCompositeRole()
	 * @generated
	 */
	int COMPOSITE_ROLE = 9;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__NAME = ROLE_DESCRIPTOR__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__GROUP = ROLE_DESCRIPTOR__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__OWNED_RULE = ROLE_DESCRIPTOR__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__METHOD_ELEMENT_PROPERTY = ROLE_DESCRIPTOR__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__BRIEF_DESCRIPTION = ROLE_DESCRIPTOR__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__ID = ROLE_DESCRIPTOR__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__ORDERING_GUIDE = ROLE_DESCRIPTOR__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__PRESENTATION_NAME = ROLE_DESCRIPTOR__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__SUPPRESSED = ROLE_DESCRIPTOR__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__PRESENTATION = ROLE_DESCRIPTOR__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Fulfill</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__FULFILL = ROLE_DESCRIPTOR__FULFILL;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__IS_ABSTRACT = ROLE_DESCRIPTOR__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__NODEICON = ROLE_DESCRIPTOR__NODEICON;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__SHAPEICON = ROLE_DESCRIPTOR__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Presented After</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__PRESENTED_AFTER = ROLE_DESCRIPTOR__PRESENTED_AFTER;

	/**
	 * The feature id for the '<em><b>Presented Before</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__PRESENTED_BEFORE = ROLE_DESCRIPTOR__PRESENTED_BEFORE;

	/**
	 * The feature id for the '<em><b>Planning Data</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__PLANNING_DATA = ROLE_DESCRIPTOR__PLANNING_DATA;

	/**
	 * The feature id for the '<em><b>Super Activity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__SUPER_ACTIVITY = ROLE_DESCRIPTOR__SUPER_ACTIVITY;

	/**
	 * The feature id for the '<em><b>Group1</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__GROUP1 = ROLE_DESCRIPTOR__GROUP1;

	/**
	 * The feature id for the '<em><b>Checklist</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__CHECKLIST = ROLE_DESCRIPTOR__CHECKLIST;

	/**
	 * The feature id for the '<em><b>Concept</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__CONCEPT = ROLE_DESCRIPTOR__CONCEPT;

	/**
	 * The feature id for the '<em><b>Example</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__EXAMPLE = ROLE_DESCRIPTOR__EXAMPLE;

	/**
	 * The feature id for the '<em><b>Guideline</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__GUIDELINE = ROLE_DESCRIPTOR__GUIDELINE;

	/**
	 * The feature id for the '<em><b>Reusable Asset</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__REUSABLE_ASSET = ROLE_DESCRIPTOR__REUSABLE_ASSET;

	/**
	 * The feature id for the '<em><b>Supporting Material</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__SUPPORTING_MATERIAL = ROLE_DESCRIPTOR__SUPPORTING_MATERIAL;

	/**
	 * The feature id for the '<em><b>Whitepaper</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__WHITEPAPER = ROLE_DESCRIPTOR__WHITEPAPER;

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
	 * The feature id for the '<em><b>Is Planned</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__IS_PLANNED = ROLE_DESCRIPTOR__IS_PLANNED;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__PREFIX = ROLE_DESCRIPTOR__PREFIX;

	/**
	 * The feature id for the '<em><b>Is Synchronized With Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__IS_SYNCHRONIZED_WITH_SOURCE = ROLE_DESCRIPTOR__IS_SYNCHRONIZED_WITH_SOURCE;

	/**
	 * The feature id for the '<em><b>Role</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__ROLE = ROLE_DESCRIPTOR__ROLE;

	/**
	 * The feature id for the '<em><b>Responsible For</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__RESPONSIBLE_FOR = ROLE_DESCRIPTOR__RESPONSIBLE_FOR;

	/**
	 * The feature id for the '<em><b>Group2</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__GROUP2 = ROLE_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Aggregated Role</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE__AGGREGATED_ROLE = ROLE_DESCRIPTOR_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Composite Role</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_ROLE_FEATURE_COUNT = ROLE_DESCRIPTOR_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.ConceptImpl <em>Concept</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.ConceptImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getConcept()
	 * @generated
	 */
	int CONCEPT = 10;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__NAME = GUIDANCE__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__GROUP = GUIDANCE__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__OWNED_RULE = GUIDANCE__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__METHOD_ELEMENT_PROPERTY = GUIDANCE__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__BRIEF_DESCRIPTION = GUIDANCE__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__ID = GUIDANCE__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__ORDERING_GUIDE = GUIDANCE__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__PRESENTATION_NAME = GUIDANCE__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__SUPPRESSED = GUIDANCE__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__PRESENTATION = GUIDANCE__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Fulfill</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__FULFILL = GUIDANCE__FULFILL;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__IS_ABSTRACT = GUIDANCE__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__NODEICON = GUIDANCE__NODEICON;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__SHAPEICON = GUIDANCE__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Group1</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__GROUP1 = GUIDANCE__GROUP1;

	/**
	 * The feature id for the '<em><b>Checklist</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__CHECKLIST = GUIDANCE__CHECKLIST;

	/**
	 * The feature id for the '<em><b>Concept</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__CONCEPT = GUIDANCE__CONCEPT;

	/**
	 * The feature id for the '<em><b>Example</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__EXAMPLE = GUIDANCE__EXAMPLE;

	/**
	 * The feature id for the '<em><b>Guideline</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__GUIDELINE = GUIDANCE__GUIDELINE;

	/**
	 * The feature id for the '<em><b>Reusable Asset</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__REUSABLE_ASSET = GUIDANCE__REUSABLE_ASSET;

	/**
	 * The feature id for the '<em><b>Supporting Material</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__SUPPORTING_MATERIAL = GUIDANCE__SUPPORTING_MATERIAL;

	/**
	 * The feature id for the '<em><b>Whitepaper</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__WHITEPAPER = GUIDANCE__WHITEPAPER;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__VARIABILITY_BASED_ON_ELEMENT = GUIDANCE__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__VARIABILITY_TYPE = GUIDANCE__VARIABILITY_TYPE;

	/**
	 * The number of structural features of the '<em>Concept</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT_FEATURE_COUNT = GUIDANCE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.ConstraintImpl <em>Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.ConstraintImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getConstraint()
	 * @generated
	 */
	int CONSTRAINT = 11;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT__NAME = METHOD_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT__GROUP = METHOD_ELEMENT__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT__OWNED_RULE = METHOD_ELEMENT__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT__METHOD_ELEMENT_PROPERTY = METHOD_ELEMENT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT__BRIEF_DESCRIPTION = METHOD_ELEMENT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT__ID = METHOD_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT__ORDERING_GUIDE = METHOD_ELEMENT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT__PRESENTATION_NAME = METHOD_ELEMENT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT__SUPPRESSED = METHOD_ELEMENT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Main Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT__MAIN_DESCRIPTION = METHOD_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_FEATURE_COUNT = METHOD_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.ContentCategoryImpl <em>Content Category</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.ContentCategoryImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getContentCategory()
	 * @generated
	 */
	int CONTENT_CATEGORY = 12;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_CATEGORY__NAME = CONTENT_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_CATEGORY__GROUP = CONTENT_ELEMENT__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_CATEGORY__OWNED_RULE = CONTENT_ELEMENT__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_CATEGORY__METHOD_ELEMENT_PROPERTY = CONTENT_ELEMENT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_CATEGORY__BRIEF_DESCRIPTION = CONTENT_ELEMENT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_CATEGORY__ID = CONTENT_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_CATEGORY__ORDERING_GUIDE = CONTENT_ELEMENT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_CATEGORY__PRESENTATION_NAME = CONTENT_ELEMENT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_CATEGORY__SUPPRESSED = CONTENT_ELEMENT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_CATEGORY__PRESENTATION = CONTENT_ELEMENT__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Fulfill</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_CATEGORY__FULFILL = CONTENT_ELEMENT__FULFILL;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_CATEGORY__IS_ABSTRACT = CONTENT_ELEMENT__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_CATEGORY__NODEICON = CONTENT_ELEMENT__NODEICON;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_CATEGORY__SHAPEICON = CONTENT_ELEMENT__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Group1</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_CATEGORY__GROUP1 = CONTENT_ELEMENT__GROUP1;

	/**
	 * The feature id for the '<em><b>Checklist</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_CATEGORY__CHECKLIST = CONTENT_ELEMENT__CHECKLIST;

	/**
	 * The feature id for the '<em><b>Concept</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_CATEGORY__CONCEPT = CONTENT_ELEMENT__CONCEPT;

	/**
	 * The feature id for the '<em><b>Example</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_CATEGORY__EXAMPLE = CONTENT_ELEMENT__EXAMPLE;

	/**
	 * The feature id for the '<em><b>Guideline</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_CATEGORY__GUIDELINE = CONTENT_ELEMENT__GUIDELINE;

	/**
	 * The feature id for the '<em><b>Reusable Asset</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_CATEGORY__REUSABLE_ASSET = CONTENT_ELEMENT__REUSABLE_ASSET;

	/**
	 * The feature id for the '<em><b>Supporting Material</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_CATEGORY__SUPPORTING_MATERIAL = CONTENT_ELEMENT__SUPPORTING_MATERIAL;

	/**
	 * The feature id for the '<em><b>Whitepaper</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_CATEGORY__WHITEPAPER = CONTENT_ELEMENT__WHITEPAPER;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_CATEGORY__VARIABILITY_BASED_ON_ELEMENT = CONTENT_ELEMENT__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_CATEGORY__VARIABILITY_TYPE = CONTENT_ELEMENT__VARIABILITY_TYPE;

	/**
	 * The number of structural features of the '<em>Content Category</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_CATEGORY_FEATURE_COUNT = CONTENT_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.MethodPackageImpl <em>Method Package</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.MethodPackageImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getMethodPackage()
	 * @generated
	 */
	int METHOD_PACKAGE = 43;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PACKAGE__NAME = METHOD_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PACKAGE__GROUP = METHOD_ELEMENT__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PACKAGE__OWNED_RULE = METHOD_ELEMENT__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PACKAGE__METHOD_ELEMENT_PROPERTY = METHOD_ELEMENT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PACKAGE__BRIEF_DESCRIPTION = METHOD_ELEMENT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PACKAGE__ID = METHOD_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PACKAGE__ORDERING_GUIDE = METHOD_ELEMENT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PACKAGE__PRESENTATION_NAME = METHOD_ELEMENT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PACKAGE__SUPPRESSED = METHOD_ELEMENT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Group1</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PACKAGE__GROUP1 = METHOD_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Reused Package</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PACKAGE__REUSED_PACKAGE = METHOD_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Method Package</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PACKAGE__METHOD_PACKAGE = METHOD_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Global</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PACKAGE__GLOBAL = METHOD_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Method Package</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PACKAGE_FEATURE_COUNT = METHOD_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.ContentCategoryPackageImpl <em>Content Category Package</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.ContentCategoryPackageImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getContentCategoryPackage()
	 * @generated
	 */
	int CONTENT_CATEGORY_PACKAGE = 13;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_CATEGORY_PACKAGE__NAME = METHOD_PACKAGE__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_CATEGORY_PACKAGE__GROUP = METHOD_PACKAGE__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_CATEGORY_PACKAGE__OWNED_RULE = METHOD_PACKAGE__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_CATEGORY_PACKAGE__METHOD_ELEMENT_PROPERTY = METHOD_PACKAGE__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_CATEGORY_PACKAGE__BRIEF_DESCRIPTION = METHOD_PACKAGE__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_CATEGORY_PACKAGE__ID = METHOD_PACKAGE__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_CATEGORY_PACKAGE__ORDERING_GUIDE = METHOD_PACKAGE__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_CATEGORY_PACKAGE__PRESENTATION_NAME = METHOD_PACKAGE__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_CATEGORY_PACKAGE__SUPPRESSED = METHOD_PACKAGE__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Group1</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_CATEGORY_PACKAGE__GROUP1 = METHOD_PACKAGE__GROUP1;

	/**
	 * The feature id for the '<em><b>Reused Package</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_CATEGORY_PACKAGE__REUSED_PACKAGE = METHOD_PACKAGE__REUSED_PACKAGE;

	/**
	 * The feature id for the '<em><b>Method Package</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_CATEGORY_PACKAGE__METHOD_PACKAGE = METHOD_PACKAGE__METHOD_PACKAGE;

	/**
	 * The feature id for the '<em><b>Global</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_CATEGORY_PACKAGE__GLOBAL = METHOD_PACKAGE__GLOBAL;

	/**
	 * The feature id for the '<em><b>Group2</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_CATEGORY_PACKAGE__GROUP2 = METHOD_PACKAGE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Content Category</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_CATEGORY_PACKAGE__CONTENT_CATEGORY = METHOD_PACKAGE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Content Category Package</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_CATEGORY_PACKAGE_FEATURE_COUNT = METHOD_PACKAGE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.ContentPackageImpl <em>Content Package</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.ContentPackageImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getContentPackage()
	 * @generated
	 */
	int CONTENT_PACKAGE = 16;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_PACKAGE__NAME = METHOD_PACKAGE__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_PACKAGE__GROUP = METHOD_PACKAGE__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_PACKAGE__OWNED_RULE = METHOD_PACKAGE__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_PACKAGE__METHOD_ELEMENT_PROPERTY = METHOD_PACKAGE__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_PACKAGE__BRIEF_DESCRIPTION = METHOD_PACKAGE__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_PACKAGE__ID = METHOD_PACKAGE__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_PACKAGE__ORDERING_GUIDE = METHOD_PACKAGE__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_PACKAGE__PRESENTATION_NAME = METHOD_PACKAGE__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_PACKAGE__SUPPRESSED = METHOD_PACKAGE__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Group1</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_PACKAGE__GROUP1 = METHOD_PACKAGE__GROUP1;

	/**
	 * The feature id for the '<em><b>Reused Package</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_PACKAGE__REUSED_PACKAGE = METHOD_PACKAGE__REUSED_PACKAGE;

	/**
	 * The feature id for the '<em><b>Method Package</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_PACKAGE__METHOD_PACKAGE = METHOD_PACKAGE__METHOD_PACKAGE;

	/**
	 * The feature id for the '<em><b>Global</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_PACKAGE__GLOBAL = METHOD_PACKAGE__GLOBAL;

	/**
	 * The feature id for the '<em><b>Group2</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_PACKAGE__GROUP2 = METHOD_PACKAGE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Content Element</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_PACKAGE__CONTENT_ELEMENT = METHOD_PACKAGE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Content Package</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_PACKAGE_FEATURE_COUNT = METHOD_PACKAGE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.CustomCategoryImpl <em>Custom Category</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.CustomCategoryImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getCustomCategory()
	 * @generated
	 */
	int CUSTOM_CATEGORY = 17;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CATEGORY__NAME = CONTENT_CATEGORY__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CATEGORY__GROUP = CONTENT_CATEGORY__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CATEGORY__OWNED_RULE = CONTENT_CATEGORY__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CATEGORY__METHOD_ELEMENT_PROPERTY = CONTENT_CATEGORY__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CATEGORY__BRIEF_DESCRIPTION = CONTENT_CATEGORY__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CATEGORY__ID = CONTENT_CATEGORY__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CATEGORY__ORDERING_GUIDE = CONTENT_CATEGORY__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CATEGORY__PRESENTATION_NAME = CONTENT_CATEGORY__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CATEGORY__SUPPRESSED = CONTENT_CATEGORY__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CATEGORY__PRESENTATION = CONTENT_CATEGORY__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Fulfill</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CATEGORY__FULFILL = CONTENT_CATEGORY__FULFILL;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CATEGORY__IS_ABSTRACT = CONTENT_CATEGORY__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CATEGORY__NODEICON = CONTENT_CATEGORY__NODEICON;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CATEGORY__SHAPEICON = CONTENT_CATEGORY__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Group1</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CATEGORY__GROUP1 = CONTENT_CATEGORY__GROUP1;

	/**
	 * The feature id for the '<em><b>Checklist</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CATEGORY__CHECKLIST = CONTENT_CATEGORY__CHECKLIST;

	/**
	 * The feature id for the '<em><b>Concept</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CATEGORY__CONCEPT = CONTENT_CATEGORY__CONCEPT;

	/**
	 * The feature id for the '<em><b>Example</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CATEGORY__EXAMPLE = CONTENT_CATEGORY__EXAMPLE;

	/**
	 * The feature id for the '<em><b>Guideline</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CATEGORY__GUIDELINE = CONTENT_CATEGORY__GUIDELINE;

	/**
	 * The feature id for the '<em><b>Reusable Asset</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CATEGORY__REUSABLE_ASSET = CONTENT_CATEGORY__REUSABLE_ASSET;

	/**
	 * The feature id for the '<em><b>Supporting Material</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CATEGORY__SUPPORTING_MATERIAL = CONTENT_CATEGORY__SUPPORTING_MATERIAL;

	/**
	 * The feature id for the '<em><b>Whitepaper</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CATEGORY__WHITEPAPER = CONTENT_CATEGORY__WHITEPAPER;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CATEGORY__VARIABILITY_BASED_ON_ELEMENT = CONTENT_CATEGORY__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CATEGORY__VARIABILITY_TYPE = CONTENT_CATEGORY__VARIABILITY_TYPE;

	/**
	 * The feature id for the '<em><b>Group2</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CATEGORY__GROUP2 = CONTENT_CATEGORY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Categorized Element</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CATEGORY__CATEGORIZED_ELEMENT = CONTENT_CATEGORY_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Sub Category</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CATEGORY__SUB_CATEGORY = CONTENT_CATEGORY_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Custom Category</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CATEGORY_FEATURE_COUNT = CONTENT_CATEGORY_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.DeliverableImpl <em>Deliverable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.DeliverableImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getDeliverable()
	 * @generated
	 */
	int DELIVERABLE = 18;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE__NAME = WORK_PRODUCT__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE__GROUP = WORK_PRODUCT__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE__OWNED_RULE = WORK_PRODUCT__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE__METHOD_ELEMENT_PROPERTY = WORK_PRODUCT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE__BRIEF_DESCRIPTION = WORK_PRODUCT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE__ID = WORK_PRODUCT__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE__ORDERING_GUIDE = WORK_PRODUCT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE__PRESENTATION_NAME = WORK_PRODUCT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE__SUPPRESSED = WORK_PRODUCT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE__PRESENTATION = WORK_PRODUCT__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Fulfill</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE__FULFILL = WORK_PRODUCT__FULFILL;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE__IS_ABSTRACT = WORK_PRODUCT__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE__NODEICON = WORK_PRODUCT__NODEICON;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE__SHAPEICON = WORK_PRODUCT__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Group1</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE__GROUP1 = WORK_PRODUCT__GROUP1;

	/**
	 * The feature id for the '<em><b>Checklist</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE__CHECKLIST = WORK_PRODUCT__CHECKLIST;

	/**
	 * The feature id for the '<em><b>Concept</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE__CONCEPT = WORK_PRODUCT__CONCEPT;

	/**
	 * The feature id for the '<em><b>Example</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE__EXAMPLE = WORK_PRODUCT__EXAMPLE;

	/**
	 * The feature id for the '<em><b>Guideline</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE__GUIDELINE = WORK_PRODUCT__GUIDELINE;

	/**
	 * The feature id for the '<em><b>Reusable Asset</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE__REUSABLE_ASSET = WORK_PRODUCT__REUSABLE_ASSET;

	/**
	 * The feature id for the '<em><b>Supporting Material</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE__SUPPORTING_MATERIAL = WORK_PRODUCT__SUPPORTING_MATERIAL;

	/**
	 * The feature id for the '<em><b>Whitepaper</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE__WHITEPAPER = WORK_PRODUCT__WHITEPAPER;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE__VARIABILITY_BASED_ON_ELEMENT = WORK_PRODUCT__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE__VARIABILITY_TYPE = WORK_PRODUCT__VARIABILITY_TYPE;

	/**
	 * The feature id for the '<em><b>Group2</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE__GROUP2 = WORK_PRODUCT__GROUP2;

	/**
	 * The feature id for the '<em><b>Estimate</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE__ESTIMATE = WORK_PRODUCT__ESTIMATE;

	/**
	 * The feature id for the '<em><b>Estimation Considerations</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE__ESTIMATION_CONSIDERATIONS = WORK_PRODUCT__ESTIMATION_CONSIDERATIONS;

	/**
	 * The feature id for the '<em><b>Report</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE__REPORT = WORK_PRODUCT__REPORT;

	/**
	 * The feature id for the '<em><b>Template</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE__TEMPLATE = WORK_PRODUCT__TEMPLATE;

	/**
	 * The feature id for the '<em><b>Tool Mentor</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE__TOOL_MENTOR = WORK_PRODUCT__TOOL_MENTOR;

	/**
	 * The feature id for the '<em><b>Group3</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE__GROUP3 = WORK_PRODUCT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Delivered Work Product</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE__DELIVERED_WORK_PRODUCT = WORK_PRODUCT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Deliverable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE_FEATURE_COUNT = WORK_PRODUCT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.DeliverableDescriptionImpl <em>Deliverable Description</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.DeliverableDescriptionImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getDeliverableDescription()
	 * @generated
	 */
	int DELIVERABLE_DESCRIPTION = 19;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE_DESCRIPTION__NAME = WORK_PRODUCT_DESCRIPTION__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE_DESCRIPTION__GROUP = WORK_PRODUCT_DESCRIPTION__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE_DESCRIPTION__OWNED_RULE = WORK_PRODUCT_DESCRIPTION__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE_DESCRIPTION__METHOD_ELEMENT_PROPERTY = WORK_PRODUCT_DESCRIPTION__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE_DESCRIPTION__BRIEF_DESCRIPTION = WORK_PRODUCT_DESCRIPTION__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE_DESCRIPTION__ID = WORK_PRODUCT_DESCRIPTION__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE_DESCRIPTION__ORDERING_GUIDE = WORK_PRODUCT_DESCRIPTION__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE_DESCRIPTION__PRESENTATION_NAME = WORK_PRODUCT_DESCRIPTION__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE_DESCRIPTION__SUPPRESSED = WORK_PRODUCT_DESCRIPTION__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Copyright</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE_DESCRIPTION__COPYRIGHT = WORK_PRODUCT_DESCRIPTION__COPYRIGHT;

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
	 * The feature id for the '<em><b>Main Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE_DESCRIPTION__MAIN_DESCRIPTION = WORK_PRODUCT_DESCRIPTION__MAIN_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Key Considerations</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE_DESCRIPTION__KEY_CONSIDERATIONS = WORK_PRODUCT_DESCRIPTION__KEY_CONSIDERATIONS;

	/**
	 * The feature id for the '<em><b>Section</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE_DESCRIPTION__SECTION = WORK_PRODUCT_DESCRIPTION__SECTION;

	/**
	 * The feature id for the '<em><b>External Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE_DESCRIPTION__EXTERNAL_ID = WORK_PRODUCT_DESCRIPTION__EXTERNAL_ID;

	/**
	 * The feature id for the '<em><b>Impact Of Not Having</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE_DESCRIPTION__IMPACT_OF_NOT_HAVING = WORK_PRODUCT_DESCRIPTION__IMPACT_OF_NOT_HAVING;

	/**
	 * The feature id for the '<em><b>Purpose</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE_DESCRIPTION__PURPOSE = WORK_PRODUCT_DESCRIPTION__PURPOSE;

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
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.DeliveryProcessImpl <em>Delivery Process</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.DeliveryProcessImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getDeliveryProcess()
	 * @generated
	 */
	int DELIVERY_PROCESS = 20;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__NAME = PROCESS__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__GROUP = PROCESS__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__OWNED_RULE = PROCESS__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__METHOD_ELEMENT_PROPERTY = PROCESS__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__BRIEF_DESCRIPTION = PROCESS__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__ID = PROCESS__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__ORDERING_GUIDE = PROCESS__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__PRESENTATION_NAME = PROCESS__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__SUPPRESSED = PROCESS__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__PRESENTATION = PROCESS__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Fulfill</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__FULFILL = PROCESS__FULFILL;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__IS_ABSTRACT = PROCESS__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__NODEICON = PROCESS__NODEICON;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__SHAPEICON = PROCESS__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Presented After</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__PRESENTED_AFTER = PROCESS__PRESENTED_AFTER;

	/**
	 * The feature id for the '<em><b>Presented Before</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__PRESENTED_BEFORE = PROCESS__PRESENTED_BEFORE;

	/**
	 * The feature id for the '<em><b>Planning Data</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__PLANNING_DATA = PROCESS__PLANNING_DATA;

	/**
	 * The feature id for the '<em><b>Super Activity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__SUPER_ACTIVITY = PROCESS__SUPER_ACTIVITY;

	/**
	 * The feature id for the '<em><b>Group1</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__GROUP1 = PROCESS__GROUP1;

	/**
	 * The feature id for the '<em><b>Checklist</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__CHECKLIST = PROCESS__CHECKLIST;

	/**
	 * The feature id for the '<em><b>Concept</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__CONCEPT = PROCESS__CONCEPT;

	/**
	 * The feature id for the '<em><b>Example</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__EXAMPLE = PROCESS__EXAMPLE;

	/**
	 * The feature id for the '<em><b>Guideline</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__GUIDELINE = PROCESS__GUIDELINE;

	/**
	 * The feature id for the '<em><b>Reusable Asset</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__REUSABLE_ASSET = PROCESS__REUSABLE_ASSET;

	/**
	 * The feature id for the '<em><b>Supporting Material</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__SUPPORTING_MATERIAL = PROCESS__SUPPORTING_MATERIAL;

	/**
	 * The feature id for the '<em><b>Whitepaper</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__WHITEPAPER = PROCESS__WHITEPAPER;

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
	 * The feature id for the '<em><b>Is Planned</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__IS_PLANNED = PROCESS__IS_PLANNED;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__PREFIX = PROCESS__PREFIX;

	/**
	 * The feature id for the '<em><b>Group2</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__GROUP2 = PROCESS__GROUP2;

	/**
	 * The feature id for the '<em><b>Predecessor</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__PREDECESSOR = PROCESS__PREDECESSOR;

	/**
	 * The feature id for the '<em><b>Is Event Driven</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__IS_EVENT_DRIVEN = PROCESS__IS_EVENT_DRIVEN;

	/**
	 * The feature id for the '<em><b>Is Ongoing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__IS_ONGOING = PROCESS__IS_ONGOING;

	/**
	 * The feature id for the '<em><b>Is Repeatable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__IS_REPEATABLE = PROCESS__IS_REPEATABLE;

	/**
	 * The feature id for the '<em><b>Precondition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__PRECONDITION = PROCESS__PRECONDITION;

	/**
	 * The feature id for the '<em><b>Postcondition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__POSTCONDITION = PROCESS__POSTCONDITION;

	/**
	 * The feature id for the '<em><b>Group3</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__GROUP3 = PROCESS__GROUP3;

	/**
	 * The feature id for the '<em><b>Breakdown Element</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__BREAKDOWN_ELEMENT = PROCESS__BREAKDOWN_ELEMENT;

	/**
	 * The feature id for the '<em><b>Roadmap</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__ROADMAP = PROCESS__ROADMAP;

	/**
	 * The feature id for the '<em><b>Is Enactable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__IS_ENACTABLE = PROCESS__IS_ENACTABLE;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__VARIABILITY_BASED_ON_ELEMENT = PROCESS__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__VARIABILITY_TYPE = PROCESS__VARIABILITY_TYPE;

	/**
	 * The feature id for the '<em><b>Includes Pattern</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__INCLUDES_PATTERN = PROCESS__INCLUDES_PATTERN;

	/**
	 * The feature id for the '<em><b>Default Context</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__DEFAULT_CONTEXT = PROCESS__DEFAULT_CONTEXT;

	/**
	 * The feature id for the '<em><b>Valid Context</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__VALID_CONTEXT = PROCESS__VALID_CONTEXT;

	/**
	 * The feature id for the '<em><b>Diagram URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__DIAGRAM_URI = PROCESS__DIAGRAM_URI;

	/**
	 * The feature id for the '<em><b>Group4</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__GROUP4 = PROCESS_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Communications Material</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__COMMUNICATIONS_MATERIAL = PROCESS_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Education Material</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS__EDUCATION_MATERIAL = PROCESS_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Delivery Process</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS_FEATURE_COUNT = PROCESS_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.ProcessDescriptionImpl <em>Process Description</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.ProcessDescriptionImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getProcessDescription()
	 * @generated
	 */
	int PROCESS_DESCRIPTION = 57;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DESCRIPTION__NAME = ACTIVITY_DESCRIPTION__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DESCRIPTION__GROUP = ACTIVITY_DESCRIPTION__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DESCRIPTION__OWNED_RULE = ACTIVITY_DESCRIPTION__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DESCRIPTION__METHOD_ELEMENT_PROPERTY = ACTIVITY_DESCRIPTION__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DESCRIPTION__BRIEF_DESCRIPTION = ACTIVITY_DESCRIPTION__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DESCRIPTION__ID = ACTIVITY_DESCRIPTION__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DESCRIPTION__ORDERING_GUIDE = ACTIVITY_DESCRIPTION__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DESCRIPTION__PRESENTATION_NAME = ACTIVITY_DESCRIPTION__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DESCRIPTION__SUPPRESSED = ACTIVITY_DESCRIPTION__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Copyright</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DESCRIPTION__COPYRIGHT = ACTIVITY_DESCRIPTION__COPYRIGHT;

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
	 * The feature id for the '<em><b>Main Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DESCRIPTION__MAIN_DESCRIPTION = ACTIVITY_DESCRIPTION__MAIN_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Key Considerations</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DESCRIPTION__KEY_CONSIDERATIONS = ACTIVITY_DESCRIPTION__KEY_CONSIDERATIONS;

	/**
	 * The feature id for the '<em><b>Section</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DESCRIPTION__SECTION = ACTIVITY_DESCRIPTION__SECTION;

	/**
	 * The feature id for the '<em><b>External Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DESCRIPTION__EXTERNAL_ID = ACTIVITY_DESCRIPTION__EXTERNAL_ID;

	/**
	 * The feature id for the '<em><b>Usage Guidance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DESCRIPTION__USAGE_GUIDANCE = ACTIVITY_DESCRIPTION__USAGE_GUIDANCE;

	/**
	 * The feature id for the '<em><b>Alternatives</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DESCRIPTION__ALTERNATIVES = ACTIVITY_DESCRIPTION__ALTERNATIVES;

	/**
	 * The feature id for the '<em><b>How To Staff</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DESCRIPTION__HOW_TO_STAFF = ACTIVITY_DESCRIPTION__HOW_TO_STAFF;

	/**
	 * The feature id for the '<em><b>Purpose</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DESCRIPTION__PURPOSE = ACTIVITY_DESCRIPTION__PURPOSE;

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
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.DeliveryProcessDescriptionImpl <em>Delivery Process Description</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.DeliveryProcessDescriptionImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getDeliveryProcessDescription()
	 * @generated
	 */
	int DELIVERY_PROCESS_DESCRIPTION = 21;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS_DESCRIPTION__NAME = PROCESS_DESCRIPTION__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS_DESCRIPTION__GROUP = PROCESS_DESCRIPTION__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS_DESCRIPTION__OWNED_RULE = PROCESS_DESCRIPTION__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS_DESCRIPTION__METHOD_ELEMENT_PROPERTY = PROCESS_DESCRIPTION__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS_DESCRIPTION__BRIEF_DESCRIPTION = PROCESS_DESCRIPTION__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS_DESCRIPTION__ID = PROCESS_DESCRIPTION__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS_DESCRIPTION__ORDERING_GUIDE = PROCESS_DESCRIPTION__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS_DESCRIPTION__PRESENTATION_NAME = PROCESS_DESCRIPTION__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS_DESCRIPTION__SUPPRESSED = PROCESS_DESCRIPTION__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Copyright</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS_DESCRIPTION__COPYRIGHT = PROCESS_DESCRIPTION__COPYRIGHT;

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
	 * The feature id for the '<em><b>Main Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS_DESCRIPTION__MAIN_DESCRIPTION = PROCESS_DESCRIPTION__MAIN_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Key Considerations</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS_DESCRIPTION__KEY_CONSIDERATIONS = PROCESS_DESCRIPTION__KEY_CONSIDERATIONS;

	/**
	 * The feature id for the '<em><b>Section</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS_DESCRIPTION__SECTION = PROCESS_DESCRIPTION__SECTION;

	/**
	 * The feature id for the '<em><b>External Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS_DESCRIPTION__EXTERNAL_ID = PROCESS_DESCRIPTION__EXTERNAL_ID;

	/**
	 * The feature id for the '<em><b>Usage Guidance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS_DESCRIPTION__USAGE_GUIDANCE = PROCESS_DESCRIPTION__USAGE_GUIDANCE;

	/**
	 * The feature id for the '<em><b>Alternatives</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS_DESCRIPTION__ALTERNATIVES = PROCESS_DESCRIPTION__ALTERNATIVES;

	/**
	 * The feature id for the '<em><b>How To Staff</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS_DESCRIPTION__HOW_TO_STAFF = PROCESS_DESCRIPTION__HOW_TO_STAFF;

	/**
	 * The feature id for the '<em><b>Purpose</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERY_PROCESS_DESCRIPTION__PURPOSE = PROCESS_DESCRIPTION__PURPOSE;

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
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.DescriptorDescriptionImpl <em>Descriptor Description</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.DescriptorDescriptionImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getDescriptorDescription()
	 * @generated
	 */
	int DESCRIPTOR_DESCRIPTION = 24;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR_DESCRIPTION__NAME = BREAKDOWN_ELEMENT_DESCRIPTION__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR_DESCRIPTION__GROUP = BREAKDOWN_ELEMENT_DESCRIPTION__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR_DESCRIPTION__OWNED_RULE = BREAKDOWN_ELEMENT_DESCRIPTION__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR_DESCRIPTION__METHOD_ELEMENT_PROPERTY = BREAKDOWN_ELEMENT_DESCRIPTION__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR_DESCRIPTION__BRIEF_DESCRIPTION = BREAKDOWN_ELEMENT_DESCRIPTION__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR_DESCRIPTION__ID = BREAKDOWN_ELEMENT_DESCRIPTION__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR_DESCRIPTION__ORDERING_GUIDE = BREAKDOWN_ELEMENT_DESCRIPTION__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR_DESCRIPTION__PRESENTATION_NAME = BREAKDOWN_ELEMENT_DESCRIPTION__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR_DESCRIPTION__SUPPRESSED = BREAKDOWN_ELEMENT_DESCRIPTION__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Copyright</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR_DESCRIPTION__COPYRIGHT = BREAKDOWN_ELEMENT_DESCRIPTION__COPYRIGHT;

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
	 * The feature id for the '<em><b>Main Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR_DESCRIPTION__MAIN_DESCRIPTION = BREAKDOWN_ELEMENT_DESCRIPTION__MAIN_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Key Considerations</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR_DESCRIPTION__KEY_CONSIDERATIONS = BREAKDOWN_ELEMENT_DESCRIPTION__KEY_CONSIDERATIONS;

	/**
	 * The feature id for the '<em><b>Section</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR_DESCRIPTION__SECTION = BREAKDOWN_ELEMENT_DESCRIPTION__SECTION;

	/**
	 * The feature id for the '<em><b>External Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTOR_DESCRIPTION__EXTERNAL_ID = BREAKDOWN_ELEMENT_DESCRIPTION__EXTERNAL_ID;

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
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.DisciplineImpl <em>Discipline</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.DisciplineImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getDiscipline()
	 * @generated
	 */
	int DISCIPLINE = 25;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE__NAME = CONTENT_CATEGORY__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE__GROUP = CONTENT_CATEGORY__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE__OWNED_RULE = CONTENT_CATEGORY__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE__METHOD_ELEMENT_PROPERTY = CONTENT_CATEGORY__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE__BRIEF_DESCRIPTION = CONTENT_CATEGORY__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE__ID = CONTENT_CATEGORY__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE__ORDERING_GUIDE = CONTENT_CATEGORY__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE__PRESENTATION_NAME = CONTENT_CATEGORY__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE__SUPPRESSED = CONTENT_CATEGORY__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE__PRESENTATION = CONTENT_CATEGORY__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Fulfill</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE__FULFILL = CONTENT_CATEGORY__FULFILL;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE__IS_ABSTRACT = CONTENT_CATEGORY__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE__NODEICON = CONTENT_CATEGORY__NODEICON;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE__SHAPEICON = CONTENT_CATEGORY__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Group1</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE__GROUP1 = CONTENT_CATEGORY__GROUP1;

	/**
	 * The feature id for the '<em><b>Checklist</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE__CHECKLIST = CONTENT_CATEGORY__CHECKLIST;

	/**
	 * The feature id for the '<em><b>Concept</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE__CONCEPT = CONTENT_CATEGORY__CONCEPT;

	/**
	 * The feature id for the '<em><b>Example</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE__EXAMPLE = CONTENT_CATEGORY__EXAMPLE;

	/**
	 * The feature id for the '<em><b>Guideline</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE__GUIDELINE = CONTENT_CATEGORY__GUIDELINE;

	/**
	 * The feature id for the '<em><b>Reusable Asset</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE__REUSABLE_ASSET = CONTENT_CATEGORY__REUSABLE_ASSET;

	/**
	 * The feature id for the '<em><b>Supporting Material</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE__SUPPORTING_MATERIAL = CONTENT_CATEGORY__SUPPORTING_MATERIAL;

	/**
	 * The feature id for the '<em><b>Whitepaper</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE__WHITEPAPER = CONTENT_CATEGORY__WHITEPAPER;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE__VARIABILITY_BASED_ON_ELEMENT = CONTENT_CATEGORY__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE__VARIABILITY_TYPE = CONTENT_CATEGORY__VARIABILITY_TYPE;

	/**
	 * The feature id for the '<em><b>Group2</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE__GROUP2 = CONTENT_CATEGORY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Task</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE__TASK = CONTENT_CATEGORY_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Sub Discipline</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE__SUB_DISCIPLINE = CONTENT_CATEGORY_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Reference Workflow</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE__REFERENCE_WORKFLOW = CONTENT_CATEGORY_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Discipline</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE_FEATURE_COUNT = CONTENT_CATEGORY_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.DisciplineGroupingImpl <em>Discipline Grouping</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.DisciplineGroupingImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getDisciplineGrouping()
	 * @generated
	 */
	int DISCIPLINE_GROUPING = 26;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE_GROUPING__NAME = CONTENT_CATEGORY__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE_GROUPING__GROUP = CONTENT_CATEGORY__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE_GROUPING__OWNED_RULE = CONTENT_CATEGORY__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE_GROUPING__METHOD_ELEMENT_PROPERTY = CONTENT_CATEGORY__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE_GROUPING__BRIEF_DESCRIPTION = CONTENT_CATEGORY__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE_GROUPING__ID = CONTENT_CATEGORY__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE_GROUPING__ORDERING_GUIDE = CONTENT_CATEGORY__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE_GROUPING__PRESENTATION_NAME = CONTENT_CATEGORY__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE_GROUPING__SUPPRESSED = CONTENT_CATEGORY__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE_GROUPING__PRESENTATION = CONTENT_CATEGORY__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Fulfill</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE_GROUPING__FULFILL = CONTENT_CATEGORY__FULFILL;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE_GROUPING__IS_ABSTRACT = CONTENT_CATEGORY__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE_GROUPING__NODEICON = CONTENT_CATEGORY__NODEICON;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE_GROUPING__SHAPEICON = CONTENT_CATEGORY__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Group1</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE_GROUPING__GROUP1 = CONTENT_CATEGORY__GROUP1;

	/**
	 * The feature id for the '<em><b>Checklist</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE_GROUPING__CHECKLIST = CONTENT_CATEGORY__CHECKLIST;

	/**
	 * The feature id for the '<em><b>Concept</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE_GROUPING__CONCEPT = CONTENT_CATEGORY__CONCEPT;

	/**
	 * The feature id for the '<em><b>Example</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE_GROUPING__EXAMPLE = CONTENT_CATEGORY__EXAMPLE;

	/**
	 * The feature id for the '<em><b>Guideline</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE_GROUPING__GUIDELINE = CONTENT_CATEGORY__GUIDELINE;

	/**
	 * The feature id for the '<em><b>Reusable Asset</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE_GROUPING__REUSABLE_ASSET = CONTENT_CATEGORY__REUSABLE_ASSET;

	/**
	 * The feature id for the '<em><b>Supporting Material</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE_GROUPING__SUPPORTING_MATERIAL = CONTENT_CATEGORY__SUPPORTING_MATERIAL;

	/**
	 * The feature id for the '<em><b>Whitepaper</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE_GROUPING__WHITEPAPER = CONTENT_CATEGORY__WHITEPAPER;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE_GROUPING__VARIABILITY_BASED_ON_ELEMENT = CONTENT_CATEGORY__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE_GROUPING__VARIABILITY_TYPE = CONTENT_CATEGORY__VARIABILITY_TYPE;

	/**
	 * The feature id for the '<em><b>Group2</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE_GROUPING__GROUP2 = CONTENT_CATEGORY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Discipline</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE_GROUPING__DISCIPLINE = CONTENT_CATEGORY_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Discipline Grouping</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCIPLINE_GROUPING_FEATURE_COUNT = CONTENT_CATEGORY_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.DocumentRootImpl <em>Document Root</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.DocumentRootImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getDocumentRoot()
	 * @generated
	 */
	int DOCUMENT_ROOT = 27;

	/**
	 * The feature id for the '<em><b>Mixed</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__MIXED = 0;

	/**
	 * The feature id for the '<em><b>XMLNS Prefix Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__XMLNS_PREFIX_MAP = 1;

	/**
	 * The feature id for the '<em><b>XSI Schema Location</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = 2;

	/**
	 * The feature id for the '<em><b>Method Configuration</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__METHOD_CONFIGURATION = 3;

	/**
	 * The feature id for the '<em><b>Method Library</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__METHOD_LIBRARY = 4;

	/**
	 * The feature id for the '<em><b>Method Plugin</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__METHOD_PLUGIN = 5;

	/**
	 * The number of structural features of the '<em>Document Root</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.DomainImpl <em>Domain</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.DomainImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getDomain()
	 * @generated
	 */
	int DOMAIN = 28;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN__NAME = CONTENT_CATEGORY__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN__GROUP = CONTENT_CATEGORY__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN__OWNED_RULE = CONTENT_CATEGORY__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN__METHOD_ELEMENT_PROPERTY = CONTENT_CATEGORY__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN__BRIEF_DESCRIPTION = CONTENT_CATEGORY__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN__ID = CONTENT_CATEGORY__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN__ORDERING_GUIDE = CONTENT_CATEGORY__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN__PRESENTATION_NAME = CONTENT_CATEGORY__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN__SUPPRESSED = CONTENT_CATEGORY__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN__PRESENTATION = CONTENT_CATEGORY__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Fulfill</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN__FULFILL = CONTENT_CATEGORY__FULFILL;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN__IS_ABSTRACT = CONTENT_CATEGORY__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN__NODEICON = CONTENT_CATEGORY__NODEICON;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN__SHAPEICON = CONTENT_CATEGORY__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Group1</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN__GROUP1 = CONTENT_CATEGORY__GROUP1;

	/**
	 * The feature id for the '<em><b>Checklist</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN__CHECKLIST = CONTENT_CATEGORY__CHECKLIST;

	/**
	 * The feature id for the '<em><b>Concept</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN__CONCEPT = CONTENT_CATEGORY__CONCEPT;

	/**
	 * The feature id for the '<em><b>Example</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN__EXAMPLE = CONTENT_CATEGORY__EXAMPLE;

	/**
	 * The feature id for the '<em><b>Guideline</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN__GUIDELINE = CONTENT_CATEGORY__GUIDELINE;

	/**
	 * The feature id for the '<em><b>Reusable Asset</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN__REUSABLE_ASSET = CONTENT_CATEGORY__REUSABLE_ASSET;

	/**
	 * The feature id for the '<em><b>Supporting Material</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN__SUPPORTING_MATERIAL = CONTENT_CATEGORY__SUPPORTING_MATERIAL;

	/**
	 * The feature id for the '<em><b>Whitepaper</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN__WHITEPAPER = CONTENT_CATEGORY__WHITEPAPER;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN__VARIABILITY_BASED_ON_ELEMENT = CONTENT_CATEGORY__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN__VARIABILITY_TYPE = CONTENT_CATEGORY__VARIABILITY_TYPE;

	/**
	 * The feature id for the '<em><b>Group2</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN__GROUP2 = CONTENT_CATEGORY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Work Product</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN__WORK_PRODUCT = CONTENT_CATEGORY_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Subdomain</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN__SUBDOMAIN = CONTENT_CATEGORY_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Domain</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_FEATURE_COUNT = CONTENT_CATEGORY_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.EstimateImpl <em>Estimate</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.EstimateImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getEstimate()
	 * @generated
	 */
	int ESTIMATE = 30;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATE__NAME = GUIDANCE__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATE__GROUP = GUIDANCE__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATE__OWNED_RULE = GUIDANCE__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATE__METHOD_ELEMENT_PROPERTY = GUIDANCE__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATE__BRIEF_DESCRIPTION = GUIDANCE__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATE__ID = GUIDANCE__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATE__ORDERING_GUIDE = GUIDANCE__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATE__PRESENTATION_NAME = GUIDANCE__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATE__SUPPRESSED = GUIDANCE__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATE__PRESENTATION = GUIDANCE__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Fulfill</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATE__FULFILL = GUIDANCE__FULFILL;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATE__IS_ABSTRACT = GUIDANCE__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATE__NODEICON = GUIDANCE__NODEICON;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATE__SHAPEICON = GUIDANCE__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Group1</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATE__GROUP1 = GUIDANCE__GROUP1;

	/**
	 * The feature id for the '<em><b>Checklist</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATE__CHECKLIST = GUIDANCE__CHECKLIST;

	/**
	 * The feature id for the '<em><b>Concept</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATE__CONCEPT = GUIDANCE__CONCEPT;

	/**
	 * The feature id for the '<em><b>Example</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATE__EXAMPLE = GUIDANCE__EXAMPLE;

	/**
	 * The feature id for the '<em><b>Guideline</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATE__GUIDELINE = GUIDANCE__GUIDELINE;

	/**
	 * The feature id for the '<em><b>Reusable Asset</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATE__REUSABLE_ASSET = GUIDANCE__REUSABLE_ASSET;

	/**
	 * The feature id for the '<em><b>Supporting Material</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATE__SUPPORTING_MATERIAL = GUIDANCE__SUPPORTING_MATERIAL;

	/**
	 * The feature id for the '<em><b>Whitepaper</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATE__WHITEPAPER = GUIDANCE__WHITEPAPER;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATE__VARIABILITY_BASED_ON_ELEMENT = GUIDANCE__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATE__VARIABILITY_TYPE = GUIDANCE__VARIABILITY_TYPE;

	/**
	 * The feature id for the '<em><b>Group2</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATE__GROUP2 = GUIDANCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Estimation Metric</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATE__ESTIMATION_METRIC = GUIDANCE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Estimation Considerations</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATE__ESTIMATION_CONSIDERATIONS = GUIDANCE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Estimate</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATE_FEATURE_COUNT = GUIDANCE_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.EstimatingMetricImpl <em>Estimating Metric</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.EstimatingMetricImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getEstimatingMetric()
	 * @generated
	 */
	int ESTIMATING_METRIC = 31;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATING_METRIC__NAME = GUIDANCE__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATING_METRIC__GROUP = GUIDANCE__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATING_METRIC__OWNED_RULE = GUIDANCE__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATING_METRIC__METHOD_ELEMENT_PROPERTY = GUIDANCE__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATING_METRIC__BRIEF_DESCRIPTION = GUIDANCE__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATING_METRIC__ID = GUIDANCE__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATING_METRIC__ORDERING_GUIDE = GUIDANCE__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATING_METRIC__PRESENTATION_NAME = GUIDANCE__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATING_METRIC__SUPPRESSED = GUIDANCE__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATING_METRIC__PRESENTATION = GUIDANCE__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Fulfill</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATING_METRIC__FULFILL = GUIDANCE__FULFILL;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATING_METRIC__IS_ABSTRACT = GUIDANCE__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATING_METRIC__NODEICON = GUIDANCE__NODEICON;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATING_METRIC__SHAPEICON = GUIDANCE__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Group1</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATING_METRIC__GROUP1 = GUIDANCE__GROUP1;

	/**
	 * The feature id for the '<em><b>Checklist</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATING_METRIC__CHECKLIST = GUIDANCE__CHECKLIST;

	/**
	 * The feature id for the '<em><b>Concept</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATING_METRIC__CONCEPT = GUIDANCE__CONCEPT;

	/**
	 * The feature id for the '<em><b>Example</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATING_METRIC__EXAMPLE = GUIDANCE__EXAMPLE;

	/**
	 * The feature id for the '<em><b>Guideline</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATING_METRIC__GUIDELINE = GUIDANCE__GUIDELINE;

	/**
	 * The feature id for the '<em><b>Reusable Asset</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATING_METRIC__REUSABLE_ASSET = GUIDANCE__REUSABLE_ASSET;

	/**
	 * The feature id for the '<em><b>Supporting Material</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATING_METRIC__SUPPORTING_MATERIAL = GUIDANCE__SUPPORTING_MATERIAL;

	/**
	 * The feature id for the '<em><b>Whitepaper</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATING_METRIC__WHITEPAPER = GUIDANCE__WHITEPAPER;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATING_METRIC__VARIABILITY_BASED_ON_ELEMENT = GUIDANCE__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATING_METRIC__VARIABILITY_TYPE = GUIDANCE__VARIABILITY_TYPE;

	/**
	 * The number of structural features of the '<em>Estimating Metric</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATING_METRIC_FEATURE_COUNT = GUIDANCE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.EstimationConsiderationsImpl <em>Estimation Considerations</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.EstimationConsiderationsImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getEstimationConsiderations()
	 * @generated
	 */
	int ESTIMATION_CONSIDERATIONS = 32;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATION_CONSIDERATIONS__NAME = GUIDANCE__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATION_CONSIDERATIONS__GROUP = GUIDANCE__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATION_CONSIDERATIONS__OWNED_RULE = GUIDANCE__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATION_CONSIDERATIONS__METHOD_ELEMENT_PROPERTY = GUIDANCE__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATION_CONSIDERATIONS__BRIEF_DESCRIPTION = GUIDANCE__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATION_CONSIDERATIONS__ID = GUIDANCE__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATION_CONSIDERATIONS__ORDERING_GUIDE = GUIDANCE__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATION_CONSIDERATIONS__PRESENTATION_NAME = GUIDANCE__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATION_CONSIDERATIONS__SUPPRESSED = GUIDANCE__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATION_CONSIDERATIONS__PRESENTATION = GUIDANCE__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Fulfill</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATION_CONSIDERATIONS__FULFILL = GUIDANCE__FULFILL;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATION_CONSIDERATIONS__IS_ABSTRACT = GUIDANCE__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATION_CONSIDERATIONS__NODEICON = GUIDANCE__NODEICON;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATION_CONSIDERATIONS__SHAPEICON = GUIDANCE__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Group1</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATION_CONSIDERATIONS__GROUP1 = GUIDANCE__GROUP1;

	/**
	 * The feature id for the '<em><b>Checklist</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATION_CONSIDERATIONS__CHECKLIST = GUIDANCE__CHECKLIST;

	/**
	 * The feature id for the '<em><b>Concept</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATION_CONSIDERATIONS__CONCEPT = GUIDANCE__CONCEPT;

	/**
	 * The feature id for the '<em><b>Example</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATION_CONSIDERATIONS__EXAMPLE = GUIDANCE__EXAMPLE;

	/**
	 * The feature id for the '<em><b>Guideline</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATION_CONSIDERATIONS__GUIDELINE = GUIDANCE__GUIDELINE;

	/**
	 * The feature id for the '<em><b>Reusable Asset</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATION_CONSIDERATIONS__REUSABLE_ASSET = GUIDANCE__REUSABLE_ASSET;

	/**
	 * The feature id for the '<em><b>Supporting Material</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATION_CONSIDERATIONS__SUPPORTING_MATERIAL = GUIDANCE__SUPPORTING_MATERIAL;

	/**
	 * The feature id for the '<em><b>Whitepaper</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATION_CONSIDERATIONS__WHITEPAPER = GUIDANCE__WHITEPAPER;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATION_CONSIDERATIONS__VARIABILITY_BASED_ON_ELEMENT = GUIDANCE__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATION_CONSIDERATIONS__VARIABILITY_TYPE = GUIDANCE__VARIABILITY_TYPE;

	/**
	 * The number of structural features of the '<em>Estimation Considerations</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTIMATION_CONSIDERATIONS_FEATURE_COUNT = GUIDANCE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.ExampleImpl <em>Example</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.ExampleImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getExample()
	 * @generated
	 */
	int EXAMPLE = 33;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXAMPLE__NAME = GUIDANCE__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXAMPLE__GROUP = GUIDANCE__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXAMPLE__OWNED_RULE = GUIDANCE__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXAMPLE__METHOD_ELEMENT_PROPERTY = GUIDANCE__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXAMPLE__BRIEF_DESCRIPTION = GUIDANCE__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXAMPLE__ID = GUIDANCE__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXAMPLE__ORDERING_GUIDE = GUIDANCE__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXAMPLE__PRESENTATION_NAME = GUIDANCE__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXAMPLE__SUPPRESSED = GUIDANCE__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXAMPLE__PRESENTATION = GUIDANCE__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Fulfill</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXAMPLE__FULFILL = GUIDANCE__FULFILL;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXAMPLE__IS_ABSTRACT = GUIDANCE__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXAMPLE__NODEICON = GUIDANCE__NODEICON;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXAMPLE__SHAPEICON = GUIDANCE__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Group1</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXAMPLE__GROUP1 = GUIDANCE__GROUP1;

	/**
	 * The feature id for the '<em><b>Checklist</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXAMPLE__CHECKLIST = GUIDANCE__CHECKLIST;

	/**
	 * The feature id for the '<em><b>Concept</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXAMPLE__CONCEPT = GUIDANCE__CONCEPT;

	/**
	 * The feature id for the '<em><b>Example</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXAMPLE__EXAMPLE = GUIDANCE__EXAMPLE;

	/**
	 * The feature id for the '<em><b>Guideline</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXAMPLE__GUIDELINE = GUIDANCE__GUIDELINE;

	/**
	 * The feature id for the '<em><b>Reusable Asset</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXAMPLE__REUSABLE_ASSET = GUIDANCE__REUSABLE_ASSET;

	/**
	 * The feature id for the '<em><b>Supporting Material</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXAMPLE__SUPPORTING_MATERIAL = GUIDANCE__SUPPORTING_MATERIAL;

	/**
	 * The feature id for the '<em><b>Whitepaper</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXAMPLE__WHITEPAPER = GUIDANCE__WHITEPAPER;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXAMPLE__VARIABILITY_BASED_ON_ELEMENT = GUIDANCE__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXAMPLE__VARIABILITY_TYPE = GUIDANCE__VARIABILITY_TYPE;

	/**
	 * The number of structural features of the '<em>Example</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXAMPLE_FEATURE_COUNT = GUIDANCE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.GuidanceDescriptionImpl <em>Guidance Description</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.GuidanceDescriptionImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getGuidanceDescription()
	 * @generated
	 */
	int GUIDANCE_DESCRIPTION = 35;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE_DESCRIPTION__NAME = CONTENT_DESCRIPTION__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE_DESCRIPTION__GROUP = CONTENT_DESCRIPTION__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE_DESCRIPTION__OWNED_RULE = CONTENT_DESCRIPTION__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE_DESCRIPTION__METHOD_ELEMENT_PROPERTY = CONTENT_DESCRIPTION__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE_DESCRIPTION__BRIEF_DESCRIPTION = CONTENT_DESCRIPTION__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE_DESCRIPTION__ID = CONTENT_DESCRIPTION__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE_DESCRIPTION__ORDERING_GUIDE = CONTENT_DESCRIPTION__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE_DESCRIPTION__PRESENTATION_NAME = CONTENT_DESCRIPTION__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE_DESCRIPTION__SUPPRESSED = CONTENT_DESCRIPTION__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Copyright</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE_DESCRIPTION__COPYRIGHT = CONTENT_DESCRIPTION__COPYRIGHT;

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
	 * The feature id for the '<em><b>Main Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE_DESCRIPTION__MAIN_DESCRIPTION = CONTENT_DESCRIPTION__MAIN_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Key Considerations</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE_DESCRIPTION__KEY_CONSIDERATIONS = CONTENT_DESCRIPTION__KEY_CONSIDERATIONS;

	/**
	 * The feature id for the '<em><b>Section</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE_DESCRIPTION__SECTION = CONTENT_DESCRIPTION__SECTION;

	/**
	 * The feature id for the '<em><b>External Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE_DESCRIPTION__EXTERNAL_ID = CONTENT_DESCRIPTION__EXTERNAL_ID;

	/**
	 * The feature id for the '<em><b>Attachment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE_DESCRIPTION__ATTACHMENT = CONTENT_DESCRIPTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Guidance Description</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDANCE_DESCRIPTION_FEATURE_COUNT = CONTENT_DESCRIPTION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.GuidelineImpl <em>Guideline</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.GuidelineImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getGuideline()
	 * @generated
	 */
	int GUIDELINE = 36;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDELINE__NAME = GUIDANCE__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDELINE__GROUP = GUIDANCE__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDELINE__OWNED_RULE = GUIDANCE__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDELINE__METHOD_ELEMENT_PROPERTY = GUIDANCE__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDELINE__BRIEF_DESCRIPTION = GUIDANCE__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDELINE__ID = GUIDANCE__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDELINE__ORDERING_GUIDE = GUIDANCE__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDELINE__PRESENTATION_NAME = GUIDANCE__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDELINE__SUPPRESSED = GUIDANCE__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDELINE__PRESENTATION = GUIDANCE__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Fulfill</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDELINE__FULFILL = GUIDANCE__FULFILL;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDELINE__IS_ABSTRACT = GUIDANCE__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDELINE__NODEICON = GUIDANCE__NODEICON;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDELINE__SHAPEICON = GUIDANCE__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Group1</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDELINE__GROUP1 = GUIDANCE__GROUP1;

	/**
	 * The feature id for the '<em><b>Checklist</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDELINE__CHECKLIST = GUIDANCE__CHECKLIST;

	/**
	 * The feature id for the '<em><b>Concept</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDELINE__CONCEPT = GUIDANCE__CONCEPT;

	/**
	 * The feature id for the '<em><b>Example</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDELINE__EXAMPLE = GUIDANCE__EXAMPLE;

	/**
	 * The feature id for the '<em><b>Guideline</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDELINE__GUIDELINE = GUIDANCE__GUIDELINE;

	/**
	 * The feature id for the '<em><b>Reusable Asset</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDELINE__REUSABLE_ASSET = GUIDANCE__REUSABLE_ASSET;

	/**
	 * The feature id for the '<em><b>Supporting Material</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDELINE__SUPPORTING_MATERIAL = GUIDANCE__SUPPORTING_MATERIAL;

	/**
	 * The feature id for the '<em><b>Whitepaper</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDELINE__WHITEPAPER = GUIDANCE__WHITEPAPER;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDELINE__VARIABILITY_BASED_ON_ELEMENT = GUIDANCE__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDELINE__VARIABILITY_TYPE = GUIDANCE__VARIABILITY_TYPE;

	/**
	 * The number of structural features of the '<em>Guideline</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDELINE_FEATURE_COUNT = GUIDANCE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.IterationImpl <em>Iteration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.IterationImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getIteration()
	 * @generated
	 */
	int ITERATION = 37;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__NAME = ACTIVITY__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__GROUP = ACTIVITY__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__OWNED_RULE = ACTIVITY__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__METHOD_ELEMENT_PROPERTY = ACTIVITY__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__BRIEF_DESCRIPTION = ACTIVITY__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__ID = ACTIVITY__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__ORDERING_GUIDE = ACTIVITY__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__PRESENTATION_NAME = ACTIVITY__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__SUPPRESSED = ACTIVITY__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__PRESENTATION = ACTIVITY__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Fulfill</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__FULFILL = ACTIVITY__FULFILL;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__IS_ABSTRACT = ACTIVITY__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__NODEICON = ACTIVITY__NODEICON;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__SHAPEICON = ACTIVITY__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Presented After</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__PRESENTED_AFTER = ACTIVITY__PRESENTED_AFTER;

	/**
	 * The feature id for the '<em><b>Presented Before</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__PRESENTED_BEFORE = ACTIVITY__PRESENTED_BEFORE;

	/**
	 * The feature id for the '<em><b>Planning Data</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__PLANNING_DATA = ACTIVITY__PLANNING_DATA;

	/**
	 * The feature id for the '<em><b>Super Activity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__SUPER_ACTIVITY = ACTIVITY__SUPER_ACTIVITY;

	/**
	 * The feature id for the '<em><b>Group1</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__GROUP1 = ACTIVITY__GROUP1;

	/**
	 * The feature id for the '<em><b>Checklist</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__CHECKLIST = ACTIVITY__CHECKLIST;

	/**
	 * The feature id for the '<em><b>Concept</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__CONCEPT = ACTIVITY__CONCEPT;

	/**
	 * The feature id for the '<em><b>Example</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__EXAMPLE = ACTIVITY__EXAMPLE;

	/**
	 * The feature id for the '<em><b>Guideline</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__GUIDELINE = ACTIVITY__GUIDELINE;

	/**
	 * The feature id for the '<em><b>Reusable Asset</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__REUSABLE_ASSET = ACTIVITY__REUSABLE_ASSET;

	/**
	 * The feature id for the '<em><b>Supporting Material</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__SUPPORTING_MATERIAL = ACTIVITY__SUPPORTING_MATERIAL;

	/**
	 * The feature id for the '<em><b>Whitepaper</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__WHITEPAPER = ACTIVITY__WHITEPAPER;

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
	 * The feature id for the '<em><b>Is Planned</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__IS_PLANNED = ACTIVITY__IS_PLANNED;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__PREFIX = ACTIVITY__PREFIX;

	/**
	 * The feature id for the '<em><b>Group2</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__GROUP2 = ACTIVITY__GROUP2;

	/**
	 * The feature id for the '<em><b>Predecessor</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__PREDECESSOR = ACTIVITY__PREDECESSOR;

	/**
	 * The feature id for the '<em><b>Is Event Driven</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__IS_EVENT_DRIVEN = ACTIVITY__IS_EVENT_DRIVEN;

	/**
	 * The feature id for the '<em><b>Is Ongoing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__IS_ONGOING = ACTIVITY__IS_ONGOING;

	/**
	 * The feature id for the '<em><b>Is Repeatable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__IS_REPEATABLE = ACTIVITY__IS_REPEATABLE;

	/**
	 * The feature id for the '<em><b>Precondition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__PRECONDITION = ACTIVITY__PRECONDITION;

	/**
	 * The feature id for the '<em><b>Postcondition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__POSTCONDITION = ACTIVITY__POSTCONDITION;

	/**
	 * The feature id for the '<em><b>Group3</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__GROUP3 = ACTIVITY__GROUP3;

	/**
	 * The feature id for the '<em><b>Breakdown Element</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__BREAKDOWN_ELEMENT = ACTIVITY__BREAKDOWN_ELEMENT;

	/**
	 * The feature id for the '<em><b>Roadmap</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__ROADMAP = ACTIVITY__ROADMAP;

	/**
	 * The feature id for the '<em><b>Is Enactable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__IS_ENACTABLE = ACTIVITY__IS_ENACTABLE;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__VARIABILITY_BASED_ON_ELEMENT = ACTIVITY__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION__VARIABILITY_TYPE = ACTIVITY__VARIABILITY_TYPE;

	/**
	 * The number of structural features of the '<em>Iteration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATION_FEATURE_COUNT = ACTIVITY_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.KindImpl <em>Kind</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.KindImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getKind()
	 * @generated
	 */
	int KIND = 38;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KIND__NAME = CONTENT_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KIND__GROUP = CONTENT_ELEMENT__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KIND__OWNED_RULE = CONTENT_ELEMENT__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KIND__METHOD_ELEMENT_PROPERTY = CONTENT_ELEMENT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KIND__BRIEF_DESCRIPTION = CONTENT_ELEMENT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KIND__ID = CONTENT_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KIND__ORDERING_GUIDE = CONTENT_ELEMENT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KIND__PRESENTATION_NAME = CONTENT_ELEMENT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KIND__SUPPRESSED = CONTENT_ELEMENT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KIND__PRESENTATION = CONTENT_ELEMENT__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Fulfill</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KIND__FULFILL = CONTENT_ELEMENT__FULFILL;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KIND__IS_ABSTRACT = CONTENT_ELEMENT__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KIND__NODEICON = CONTENT_ELEMENT__NODEICON;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KIND__SHAPEICON = CONTENT_ELEMENT__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Group1</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KIND__GROUP1 = CONTENT_ELEMENT__GROUP1;

	/**
	 * The feature id for the '<em><b>Checklist</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KIND__CHECKLIST = CONTENT_ELEMENT__CHECKLIST;

	/**
	 * The feature id for the '<em><b>Concept</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KIND__CONCEPT = CONTENT_ELEMENT__CONCEPT;

	/**
	 * The feature id for the '<em><b>Example</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KIND__EXAMPLE = CONTENT_ELEMENT__EXAMPLE;

	/**
	 * The feature id for the '<em><b>Guideline</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KIND__GUIDELINE = CONTENT_ELEMENT__GUIDELINE;

	/**
	 * The feature id for the '<em><b>Reusable Asset</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KIND__REUSABLE_ASSET = CONTENT_ELEMENT__REUSABLE_ASSET;

	/**
	 * The feature id for the '<em><b>Supporting Material</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KIND__SUPPORTING_MATERIAL = CONTENT_ELEMENT__SUPPORTING_MATERIAL;

	/**
	 * The feature id for the '<em><b>Whitepaper</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KIND__WHITEPAPER = CONTENT_ELEMENT__WHITEPAPER;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KIND__VARIABILITY_BASED_ON_ELEMENT = CONTENT_ELEMENT__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KIND__VARIABILITY_TYPE = CONTENT_ELEMENT__VARIABILITY_TYPE;

	/**
	 * The feature id for the '<em><b>Applicable Meta Class Info</b></em>' attribute list.
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
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.MethodConfigurationImpl <em>Method Configuration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.MethodConfigurationImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getMethodConfiguration()
	 * @generated
	 */
	int METHOD_CONFIGURATION = 39;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_CONFIGURATION__NAME = METHOD_UNIT__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_CONFIGURATION__GROUP = METHOD_UNIT__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_CONFIGURATION__OWNED_RULE = METHOD_UNIT__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_CONFIGURATION__METHOD_ELEMENT_PROPERTY = METHOD_UNIT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_CONFIGURATION__BRIEF_DESCRIPTION = METHOD_UNIT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_CONFIGURATION__ID = METHOD_UNIT__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_CONFIGURATION__ORDERING_GUIDE = METHOD_UNIT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_CONFIGURATION__PRESENTATION_NAME = METHOD_UNIT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_CONFIGURATION__SUPPRESSED = METHOD_UNIT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Copyright</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_CONFIGURATION__COPYRIGHT = METHOD_UNIT__COPYRIGHT;

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
	 * The feature id for the '<em><b>Base Configuration</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_CONFIGURATION__BASE_CONFIGURATION = METHOD_UNIT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Method Plugin Selection</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_CONFIGURATION__METHOD_PLUGIN_SELECTION = METHOD_UNIT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Method Package Selection</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_CONFIGURATION__METHOD_PACKAGE_SELECTION = METHOD_UNIT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Default View</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_CONFIGURATION__DEFAULT_VIEW = METHOD_UNIT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Process View</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_CONFIGURATION__PROCESS_VIEW = METHOD_UNIT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Subtracted Category</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_CONFIGURATION__SUBTRACTED_CATEGORY = METHOD_UNIT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Added Category</b></em>' attribute list.
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
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.MethodElementPropertyImpl <em>Method Element Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.MethodElementPropertyImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getMethodElementProperty()
	 * @generated
	 */
	int METHOD_ELEMENT_PROPERTY = 41;

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
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.MethodLibraryImpl <em>Method Library</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.MethodLibraryImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getMethodLibrary()
	 * @generated
	 */
	int METHOD_LIBRARY = 42;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_LIBRARY__NAME = METHOD_UNIT__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_LIBRARY__GROUP = METHOD_UNIT__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_LIBRARY__OWNED_RULE = METHOD_UNIT__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_LIBRARY__METHOD_ELEMENT_PROPERTY = METHOD_UNIT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_LIBRARY__BRIEF_DESCRIPTION = METHOD_UNIT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_LIBRARY__ID = METHOD_UNIT__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_LIBRARY__ORDERING_GUIDE = METHOD_UNIT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_LIBRARY__PRESENTATION_NAME = METHOD_UNIT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_LIBRARY__SUPPRESSED = METHOD_UNIT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Copyright</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_LIBRARY__COPYRIGHT = METHOD_UNIT__COPYRIGHT;

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
	 * The feature id for the '<em><b>Method Plugin</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_LIBRARY__METHOD_PLUGIN = METHOD_UNIT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Method Configuration</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_LIBRARY__METHOD_CONFIGURATION = METHOD_UNIT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Tool</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_LIBRARY__TOOL = METHOD_UNIT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Method Library</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_LIBRARY_FEATURE_COUNT = METHOD_UNIT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.MethodPluginImpl <em>Method Plugin</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.MethodPluginImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getMethodPlugin()
	 * @generated
	 */
	int METHOD_PLUGIN = 44;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PLUGIN__NAME = METHOD_UNIT__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PLUGIN__GROUP = METHOD_UNIT__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PLUGIN__OWNED_RULE = METHOD_UNIT__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PLUGIN__METHOD_ELEMENT_PROPERTY = METHOD_UNIT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PLUGIN__BRIEF_DESCRIPTION = METHOD_UNIT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PLUGIN__ID = METHOD_UNIT__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PLUGIN__ORDERING_GUIDE = METHOD_UNIT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PLUGIN__PRESENTATION_NAME = METHOD_UNIT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PLUGIN__SUPPRESSED = METHOD_UNIT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Copyright</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PLUGIN__COPYRIGHT = METHOD_UNIT__COPYRIGHT;

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
	 * The feature id for the '<em><b>Referenced Method Plugin</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PLUGIN__REFERENCED_METHOD_PLUGIN = METHOD_UNIT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Method Package</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PLUGIN__METHOD_PACKAGE = METHOD_UNIT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Supporting</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PLUGIN__SUPPORTING = METHOD_UNIT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>User Changeable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PLUGIN__USER_CHANGEABLE = METHOD_UNIT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Method Plugin</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PLUGIN_FEATURE_COUNT = METHOD_UNIT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.MilestoneImpl <em>Milestone</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.MilestoneImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getMilestone()
	 * @generated
	 */
	int MILESTONE = 46;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__NAME = WORK_BREAKDOWN_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__GROUP = WORK_BREAKDOWN_ELEMENT__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__OWNED_RULE = WORK_BREAKDOWN_ELEMENT__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__METHOD_ELEMENT_PROPERTY = WORK_BREAKDOWN_ELEMENT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__BRIEF_DESCRIPTION = WORK_BREAKDOWN_ELEMENT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__ID = WORK_BREAKDOWN_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__ORDERING_GUIDE = WORK_BREAKDOWN_ELEMENT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__PRESENTATION_NAME = WORK_BREAKDOWN_ELEMENT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__SUPPRESSED = WORK_BREAKDOWN_ELEMENT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__PRESENTATION = WORK_BREAKDOWN_ELEMENT__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Fulfill</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__FULFILL = WORK_BREAKDOWN_ELEMENT__FULFILL;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__IS_ABSTRACT = WORK_BREAKDOWN_ELEMENT__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__NODEICON = WORK_BREAKDOWN_ELEMENT__NODEICON;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__SHAPEICON = WORK_BREAKDOWN_ELEMENT__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Presented After</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__PRESENTED_AFTER = WORK_BREAKDOWN_ELEMENT__PRESENTED_AFTER;

	/**
	 * The feature id for the '<em><b>Presented Before</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__PRESENTED_BEFORE = WORK_BREAKDOWN_ELEMENT__PRESENTED_BEFORE;

	/**
	 * The feature id for the '<em><b>Planning Data</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__PLANNING_DATA = WORK_BREAKDOWN_ELEMENT__PLANNING_DATA;

	/**
	 * The feature id for the '<em><b>Super Activity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__SUPER_ACTIVITY = WORK_BREAKDOWN_ELEMENT__SUPER_ACTIVITY;

	/**
	 * The feature id for the '<em><b>Group1</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__GROUP1 = WORK_BREAKDOWN_ELEMENT__GROUP1;

	/**
	 * The feature id for the '<em><b>Checklist</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__CHECKLIST = WORK_BREAKDOWN_ELEMENT__CHECKLIST;

	/**
	 * The feature id for the '<em><b>Concept</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__CONCEPT = WORK_BREAKDOWN_ELEMENT__CONCEPT;

	/**
	 * The feature id for the '<em><b>Example</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__EXAMPLE = WORK_BREAKDOWN_ELEMENT__EXAMPLE;

	/**
	 * The feature id for the '<em><b>Guideline</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__GUIDELINE = WORK_BREAKDOWN_ELEMENT__GUIDELINE;

	/**
	 * The feature id for the '<em><b>Reusable Asset</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__REUSABLE_ASSET = WORK_BREAKDOWN_ELEMENT__REUSABLE_ASSET;

	/**
	 * The feature id for the '<em><b>Supporting Material</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__SUPPORTING_MATERIAL = WORK_BREAKDOWN_ELEMENT__SUPPORTING_MATERIAL;

	/**
	 * The feature id for the '<em><b>Whitepaper</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__WHITEPAPER = WORK_BREAKDOWN_ELEMENT__WHITEPAPER;

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
	 * The feature id for the '<em><b>Is Planned</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__IS_PLANNED = WORK_BREAKDOWN_ELEMENT__IS_PLANNED;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__PREFIX = WORK_BREAKDOWN_ELEMENT__PREFIX;

	/**
	 * The feature id for the '<em><b>Group2</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__GROUP2 = WORK_BREAKDOWN_ELEMENT__GROUP2;

	/**
	 * The feature id for the '<em><b>Predecessor</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__PREDECESSOR = WORK_BREAKDOWN_ELEMENT__PREDECESSOR;

	/**
	 * The feature id for the '<em><b>Is Event Driven</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__IS_EVENT_DRIVEN = WORK_BREAKDOWN_ELEMENT__IS_EVENT_DRIVEN;

	/**
	 * The feature id for the '<em><b>Is Ongoing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__IS_ONGOING = WORK_BREAKDOWN_ELEMENT__IS_ONGOING;

	/**
	 * The feature id for the '<em><b>Is Repeatable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__IS_REPEATABLE = WORK_BREAKDOWN_ELEMENT__IS_REPEATABLE;

	/**
	 * The feature id for the '<em><b>Required Result</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE__REQUIRED_RESULT = WORK_BREAKDOWN_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Milestone</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE_FEATURE_COUNT = WORK_BREAKDOWN_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.OutcomeImpl <em>Outcome</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.OutcomeImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getOutcome()
	 * @generated
	 */
	int OUTCOME = 48;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__NAME = WORK_PRODUCT__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__GROUP = WORK_PRODUCT__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__OWNED_RULE = WORK_PRODUCT__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__METHOD_ELEMENT_PROPERTY = WORK_PRODUCT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__BRIEF_DESCRIPTION = WORK_PRODUCT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__ID = WORK_PRODUCT__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__ORDERING_GUIDE = WORK_PRODUCT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__PRESENTATION_NAME = WORK_PRODUCT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__SUPPRESSED = WORK_PRODUCT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__PRESENTATION = WORK_PRODUCT__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Fulfill</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__FULFILL = WORK_PRODUCT__FULFILL;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__IS_ABSTRACT = WORK_PRODUCT__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__NODEICON = WORK_PRODUCT__NODEICON;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__SHAPEICON = WORK_PRODUCT__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Group1</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__GROUP1 = WORK_PRODUCT__GROUP1;

	/**
	 * The feature id for the '<em><b>Checklist</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__CHECKLIST = WORK_PRODUCT__CHECKLIST;

	/**
	 * The feature id for the '<em><b>Concept</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__CONCEPT = WORK_PRODUCT__CONCEPT;

	/**
	 * The feature id for the '<em><b>Example</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__EXAMPLE = WORK_PRODUCT__EXAMPLE;

	/**
	 * The feature id for the '<em><b>Guideline</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__GUIDELINE = WORK_PRODUCT__GUIDELINE;

	/**
	 * The feature id for the '<em><b>Reusable Asset</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__REUSABLE_ASSET = WORK_PRODUCT__REUSABLE_ASSET;

	/**
	 * The feature id for the '<em><b>Supporting Material</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__SUPPORTING_MATERIAL = WORK_PRODUCT__SUPPORTING_MATERIAL;

	/**
	 * The feature id for the '<em><b>Whitepaper</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__WHITEPAPER = WORK_PRODUCT__WHITEPAPER;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__VARIABILITY_BASED_ON_ELEMENT = WORK_PRODUCT__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__VARIABILITY_TYPE = WORK_PRODUCT__VARIABILITY_TYPE;

	/**
	 * The feature id for the '<em><b>Group2</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__GROUP2 = WORK_PRODUCT__GROUP2;

	/**
	 * The feature id for the '<em><b>Estimate</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__ESTIMATE = WORK_PRODUCT__ESTIMATE;

	/**
	 * The feature id for the '<em><b>Estimation Considerations</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__ESTIMATION_CONSIDERATIONS = WORK_PRODUCT__ESTIMATION_CONSIDERATIONS;

	/**
	 * The feature id for the '<em><b>Report</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__REPORT = WORK_PRODUCT__REPORT;

	/**
	 * The feature id for the '<em><b>Template</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__TEMPLATE = WORK_PRODUCT__TEMPLATE;

	/**
	 * The feature id for the '<em><b>Tool Mentor</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__TOOL_MENTOR = WORK_PRODUCT__TOOL_MENTOR;

	/**
	 * The number of structural features of the '<em>Outcome</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME_FEATURE_COUNT = WORK_PRODUCT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.PhaseImpl <em>Phase</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.PhaseImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getPhase()
	 * @generated
	 */
	int PHASE = 50;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__NAME = ACTIVITY__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__GROUP = ACTIVITY__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__OWNED_RULE = ACTIVITY__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__METHOD_ELEMENT_PROPERTY = ACTIVITY__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__BRIEF_DESCRIPTION = ACTIVITY__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__ID = ACTIVITY__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__ORDERING_GUIDE = ACTIVITY__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__PRESENTATION_NAME = ACTIVITY__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__SUPPRESSED = ACTIVITY__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__PRESENTATION = ACTIVITY__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Fulfill</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__FULFILL = ACTIVITY__FULFILL;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__IS_ABSTRACT = ACTIVITY__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__NODEICON = ACTIVITY__NODEICON;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__SHAPEICON = ACTIVITY__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Presented After</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__PRESENTED_AFTER = ACTIVITY__PRESENTED_AFTER;

	/**
	 * The feature id for the '<em><b>Presented Before</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__PRESENTED_BEFORE = ACTIVITY__PRESENTED_BEFORE;

	/**
	 * The feature id for the '<em><b>Planning Data</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__PLANNING_DATA = ACTIVITY__PLANNING_DATA;

	/**
	 * The feature id for the '<em><b>Super Activity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__SUPER_ACTIVITY = ACTIVITY__SUPER_ACTIVITY;

	/**
	 * The feature id for the '<em><b>Group1</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__GROUP1 = ACTIVITY__GROUP1;

	/**
	 * The feature id for the '<em><b>Checklist</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__CHECKLIST = ACTIVITY__CHECKLIST;

	/**
	 * The feature id for the '<em><b>Concept</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__CONCEPT = ACTIVITY__CONCEPT;

	/**
	 * The feature id for the '<em><b>Example</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__EXAMPLE = ACTIVITY__EXAMPLE;

	/**
	 * The feature id for the '<em><b>Guideline</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__GUIDELINE = ACTIVITY__GUIDELINE;

	/**
	 * The feature id for the '<em><b>Reusable Asset</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__REUSABLE_ASSET = ACTIVITY__REUSABLE_ASSET;

	/**
	 * The feature id for the '<em><b>Supporting Material</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__SUPPORTING_MATERIAL = ACTIVITY__SUPPORTING_MATERIAL;

	/**
	 * The feature id for the '<em><b>Whitepaper</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__WHITEPAPER = ACTIVITY__WHITEPAPER;

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
	 * The feature id for the '<em><b>Is Planned</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__IS_PLANNED = ACTIVITY__IS_PLANNED;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__PREFIX = ACTIVITY__PREFIX;

	/**
	 * The feature id for the '<em><b>Group2</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__GROUP2 = ACTIVITY__GROUP2;

	/**
	 * The feature id for the '<em><b>Predecessor</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__PREDECESSOR = ACTIVITY__PREDECESSOR;

	/**
	 * The feature id for the '<em><b>Is Event Driven</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__IS_EVENT_DRIVEN = ACTIVITY__IS_EVENT_DRIVEN;

	/**
	 * The feature id for the '<em><b>Is Ongoing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__IS_ONGOING = ACTIVITY__IS_ONGOING;

	/**
	 * The feature id for the '<em><b>Is Repeatable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__IS_REPEATABLE = ACTIVITY__IS_REPEATABLE;

	/**
	 * The feature id for the '<em><b>Precondition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__PRECONDITION = ACTIVITY__PRECONDITION;

	/**
	 * The feature id for the '<em><b>Postcondition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__POSTCONDITION = ACTIVITY__POSTCONDITION;

	/**
	 * The feature id for the '<em><b>Group3</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__GROUP3 = ACTIVITY__GROUP3;

	/**
	 * The feature id for the '<em><b>Breakdown Element</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__BREAKDOWN_ELEMENT = ACTIVITY__BREAKDOWN_ELEMENT;

	/**
	 * The feature id for the '<em><b>Roadmap</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__ROADMAP = ACTIVITY__ROADMAP;

	/**
	 * The feature id for the '<em><b>Is Enactable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__IS_ENACTABLE = ACTIVITY__IS_ENACTABLE;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__VARIABILITY_BASED_ON_ELEMENT = ACTIVITY__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE__VARIABILITY_TYPE = ACTIVITY__VARIABILITY_TYPE;

	/**
	 * The number of structural features of the '<em>Phase</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE_FEATURE_COUNT = ACTIVITY_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.PlanningDataImpl <em>Planning Data</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.PlanningDataImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getPlanningData()
	 * @generated
	 */
	int PLANNING_DATA = 51;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLANNING_DATA__NAME = PROCESS_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLANNING_DATA__GROUP = PROCESS_ELEMENT__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLANNING_DATA__OWNED_RULE = PROCESS_ELEMENT__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLANNING_DATA__METHOD_ELEMENT_PROPERTY = PROCESS_ELEMENT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLANNING_DATA__BRIEF_DESCRIPTION = PROCESS_ELEMENT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLANNING_DATA__ID = PROCESS_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLANNING_DATA__ORDERING_GUIDE = PROCESS_ELEMENT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLANNING_DATA__PRESENTATION_NAME = PROCESS_ELEMENT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLANNING_DATA__SUPPRESSED = PROCESS_ELEMENT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLANNING_DATA__PRESENTATION = PROCESS_ELEMENT__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Fulfill</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLANNING_DATA__FULFILL = PROCESS_ELEMENT__FULFILL;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLANNING_DATA__IS_ABSTRACT = PROCESS_ELEMENT__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLANNING_DATA__NODEICON = PROCESS_ELEMENT__NODEICON;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLANNING_DATA__SHAPEICON = PROCESS_ELEMENT__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Finish Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLANNING_DATA__FINISH_DATE = PROCESS_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Rank</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLANNING_DATA__RANK = PROCESS_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Start Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLANNING_DATA__START_DATE = PROCESS_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Planning Data</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLANNING_DATA_FEATURE_COUNT = PROCESS_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.PracticeImpl <em>Practice</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.PracticeImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getPractice()
	 * @generated
	 */
	int PRACTICE = 52;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE__NAME = GUIDANCE__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE__GROUP = GUIDANCE__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE__OWNED_RULE = GUIDANCE__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE__METHOD_ELEMENT_PROPERTY = GUIDANCE__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE__BRIEF_DESCRIPTION = GUIDANCE__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE__ID = GUIDANCE__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE__ORDERING_GUIDE = GUIDANCE__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE__PRESENTATION_NAME = GUIDANCE__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE__SUPPRESSED = GUIDANCE__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE__PRESENTATION = GUIDANCE__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Fulfill</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE__FULFILL = GUIDANCE__FULFILL;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE__IS_ABSTRACT = GUIDANCE__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE__NODEICON = GUIDANCE__NODEICON;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE__SHAPEICON = GUIDANCE__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Group1</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE__GROUP1 = GUIDANCE__GROUP1;

	/**
	 * The feature id for the '<em><b>Checklist</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE__CHECKLIST = GUIDANCE__CHECKLIST;

	/**
	 * The feature id for the '<em><b>Concept</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE__CONCEPT = GUIDANCE__CONCEPT;

	/**
	 * The feature id for the '<em><b>Example</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE__EXAMPLE = GUIDANCE__EXAMPLE;

	/**
	 * The feature id for the '<em><b>Guideline</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE__GUIDELINE = GUIDANCE__GUIDELINE;

	/**
	 * The feature id for the '<em><b>Reusable Asset</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE__REUSABLE_ASSET = GUIDANCE__REUSABLE_ASSET;

	/**
	 * The feature id for the '<em><b>Supporting Material</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE__SUPPORTING_MATERIAL = GUIDANCE__SUPPORTING_MATERIAL;

	/**
	 * The feature id for the '<em><b>Whitepaper</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE__WHITEPAPER = GUIDANCE__WHITEPAPER;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE__VARIABILITY_BASED_ON_ELEMENT = GUIDANCE__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE__VARIABILITY_TYPE = GUIDANCE__VARIABILITY_TYPE;

	/**
	 * The feature id for the '<em><b>Group2</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE__GROUP2 = GUIDANCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Activity Reference</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE__ACTIVITY_REFERENCE = GUIDANCE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Content Reference</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE__CONTENT_REFERENCE = GUIDANCE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Sub Practice</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE__SUB_PRACTICE = GUIDANCE_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Practice</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE_FEATURE_COUNT = GUIDANCE_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.PracticeDescriptionImpl <em>Practice Description</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.PracticeDescriptionImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getPracticeDescription()
	 * @generated
	 */
	int PRACTICE_DESCRIPTION = 53;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE_DESCRIPTION__NAME = CONTENT_DESCRIPTION__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE_DESCRIPTION__GROUP = CONTENT_DESCRIPTION__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE_DESCRIPTION__OWNED_RULE = CONTENT_DESCRIPTION__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE_DESCRIPTION__METHOD_ELEMENT_PROPERTY = CONTENT_DESCRIPTION__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE_DESCRIPTION__BRIEF_DESCRIPTION = CONTENT_DESCRIPTION__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE_DESCRIPTION__ID = CONTENT_DESCRIPTION__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE_DESCRIPTION__ORDERING_GUIDE = CONTENT_DESCRIPTION__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE_DESCRIPTION__PRESENTATION_NAME = CONTENT_DESCRIPTION__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE_DESCRIPTION__SUPPRESSED = CONTENT_DESCRIPTION__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Copyright</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE_DESCRIPTION__COPYRIGHT = CONTENT_DESCRIPTION__COPYRIGHT;

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
	 * The feature id for the '<em><b>Main Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE_DESCRIPTION__MAIN_DESCRIPTION = CONTENT_DESCRIPTION__MAIN_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Key Considerations</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE_DESCRIPTION__KEY_CONSIDERATIONS = CONTENT_DESCRIPTION__KEY_CONSIDERATIONS;

	/**
	 * The feature id for the '<em><b>Section</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE_DESCRIPTION__SECTION = CONTENT_DESCRIPTION__SECTION;

	/**
	 * The feature id for the '<em><b>External Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE_DESCRIPTION__EXTERNAL_ID = CONTENT_DESCRIPTION__EXTERNAL_ID;

	/**
	 * The feature id for the '<em><b>Additional Info</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE_DESCRIPTION__ADDITIONAL_INFO = CONTENT_DESCRIPTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Application</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE_DESCRIPTION__APPLICATION = CONTENT_DESCRIPTION_FEATURE_COUNT + 1;

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
	 * The feature id for the '<em><b>Levels Of Adoption</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE_DESCRIPTION__LEVELS_OF_ADOPTION = CONTENT_DESCRIPTION_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Problem</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE_DESCRIPTION__PROBLEM = CONTENT_DESCRIPTION_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Practice Description</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRACTICE_DESCRIPTION_FEATURE_COUNT = CONTENT_DESCRIPTION_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.ProcessPackageImpl <em>Process Package</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.ProcessPackageImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getProcessPackage()
	 * @generated
	 */
	int PROCESS_PACKAGE = 59;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PACKAGE__NAME = METHOD_PACKAGE__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PACKAGE__GROUP = METHOD_PACKAGE__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PACKAGE__OWNED_RULE = METHOD_PACKAGE__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PACKAGE__METHOD_ELEMENT_PROPERTY = METHOD_PACKAGE__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PACKAGE__BRIEF_DESCRIPTION = METHOD_PACKAGE__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PACKAGE__ID = METHOD_PACKAGE__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PACKAGE__ORDERING_GUIDE = METHOD_PACKAGE__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PACKAGE__PRESENTATION_NAME = METHOD_PACKAGE__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PACKAGE__SUPPRESSED = METHOD_PACKAGE__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Group1</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PACKAGE__GROUP1 = METHOD_PACKAGE__GROUP1;

	/**
	 * The feature id for the '<em><b>Reused Package</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PACKAGE__REUSED_PACKAGE = METHOD_PACKAGE__REUSED_PACKAGE;

	/**
	 * The feature id for the '<em><b>Method Package</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PACKAGE__METHOD_PACKAGE = METHOD_PACKAGE__METHOD_PACKAGE;

	/**
	 * The feature id for the '<em><b>Global</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PACKAGE__GLOBAL = METHOD_PACKAGE__GLOBAL;

	/**
	 * The feature id for the '<em><b>Group2</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PACKAGE__GROUP2 = METHOD_PACKAGE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Process Element</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PACKAGE__PROCESS_ELEMENT = METHOD_PACKAGE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Process Package</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PACKAGE_FEATURE_COUNT = METHOD_PACKAGE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.ProcessComponentImpl <em>Process Component</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.ProcessComponentImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getProcessComponent()
	 * @generated
	 */
	int PROCESS_COMPONENT = 55;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT__NAME = PROCESS_PACKAGE__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT__GROUP = PROCESS_PACKAGE__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT__OWNED_RULE = PROCESS_PACKAGE__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT__METHOD_ELEMENT_PROPERTY = PROCESS_PACKAGE__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT__BRIEF_DESCRIPTION = PROCESS_PACKAGE__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT__ID = PROCESS_PACKAGE__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT__ORDERING_GUIDE = PROCESS_PACKAGE__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT__PRESENTATION_NAME = PROCESS_PACKAGE__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT__SUPPRESSED = PROCESS_PACKAGE__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Group1</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT__GROUP1 = PROCESS_PACKAGE__GROUP1;

	/**
	 * The feature id for the '<em><b>Reused Package</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT__REUSED_PACKAGE = PROCESS_PACKAGE__REUSED_PACKAGE;

	/**
	 * The feature id for the '<em><b>Method Package</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT__METHOD_PACKAGE = PROCESS_PACKAGE__METHOD_PACKAGE;

	/**
	 * The feature id for the '<em><b>Global</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT__GLOBAL = PROCESS_PACKAGE__GLOBAL;

	/**
	 * The feature id for the '<em><b>Group2</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT__GROUP2 = PROCESS_PACKAGE__GROUP2;

	/**
	 * The feature id for the '<em><b>Process Element</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT__PROCESS_ELEMENT = PROCESS_PACKAGE__PROCESS_ELEMENT;

	/**
	 * The feature id for the '<em><b>Copyright</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT__COPYRIGHT = PROCESS_PACKAGE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Interface</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT__INTERFACE = PROCESS_PACKAGE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Process</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT__PROCESS = PROCESS_PACKAGE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Authors</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT__AUTHORS = PROCESS_PACKAGE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Change Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT__CHANGE_DATE = PROCESS_PACKAGE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Change Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT__CHANGE_DESCRIPTION = PROCESS_PACKAGE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT__VERSION = PROCESS_PACKAGE_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>Process Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_FEATURE_COUNT = PROCESS_PACKAGE_FEATURE_COUNT + 7;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.ProcessComponentInterfaceImpl <em>Process Component Interface</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.ProcessComponentInterfaceImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getProcessComponentInterface()
	 * @generated
	 */
	int PROCESS_COMPONENT_INTERFACE = 56;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE__NAME = BREAKDOWN_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE__GROUP = BREAKDOWN_ELEMENT__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE__OWNED_RULE = BREAKDOWN_ELEMENT__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE__METHOD_ELEMENT_PROPERTY = BREAKDOWN_ELEMENT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE__BRIEF_DESCRIPTION = BREAKDOWN_ELEMENT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE__ID = BREAKDOWN_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE__ORDERING_GUIDE = BREAKDOWN_ELEMENT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE__PRESENTATION_NAME = BREAKDOWN_ELEMENT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE__SUPPRESSED = BREAKDOWN_ELEMENT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE__PRESENTATION = BREAKDOWN_ELEMENT__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Fulfill</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE__FULFILL = BREAKDOWN_ELEMENT__FULFILL;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE__IS_ABSTRACT = BREAKDOWN_ELEMENT__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE__NODEICON = BREAKDOWN_ELEMENT__NODEICON;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE__SHAPEICON = BREAKDOWN_ELEMENT__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Presented After</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE__PRESENTED_AFTER = BREAKDOWN_ELEMENT__PRESENTED_AFTER;

	/**
	 * The feature id for the '<em><b>Presented Before</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE__PRESENTED_BEFORE = BREAKDOWN_ELEMENT__PRESENTED_BEFORE;

	/**
	 * The feature id for the '<em><b>Planning Data</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE__PLANNING_DATA = BREAKDOWN_ELEMENT__PLANNING_DATA;

	/**
	 * The feature id for the '<em><b>Super Activity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE__SUPER_ACTIVITY = BREAKDOWN_ELEMENT__SUPER_ACTIVITY;

	/**
	 * The feature id for the '<em><b>Group1</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE__GROUP1 = BREAKDOWN_ELEMENT__GROUP1;

	/**
	 * The feature id for the '<em><b>Checklist</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE__CHECKLIST = BREAKDOWN_ELEMENT__CHECKLIST;

	/**
	 * The feature id for the '<em><b>Concept</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE__CONCEPT = BREAKDOWN_ELEMENT__CONCEPT;

	/**
	 * The feature id for the '<em><b>Example</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE__EXAMPLE = BREAKDOWN_ELEMENT__EXAMPLE;

	/**
	 * The feature id for the '<em><b>Guideline</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE__GUIDELINE = BREAKDOWN_ELEMENT__GUIDELINE;

	/**
	 * The feature id for the '<em><b>Reusable Asset</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE__REUSABLE_ASSET = BREAKDOWN_ELEMENT__REUSABLE_ASSET;

	/**
	 * The feature id for the '<em><b>Supporting Material</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE__SUPPORTING_MATERIAL = BREAKDOWN_ELEMENT__SUPPORTING_MATERIAL;

	/**
	 * The feature id for the '<em><b>Whitepaper</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE__WHITEPAPER = BREAKDOWN_ELEMENT__WHITEPAPER;

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
	 * The feature id for the '<em><b>Is Planned</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE__IS_PLANNED = BREAKDOWN_ELEMENT__IS_PLANNED;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE__PREFIX = BREAKDOWN_ELEMENT__PREFIX;

	/**
	 * The feature id for the '<em><b>Group2</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE__GROUP2 = BREAKDOWN_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Interface Specification</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE__INTERFACE_SPECIFICATION = BREAKDOWN_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Interface IO</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE__INTERFACE_IO = BREAKDOWN_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Process Component Interface</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_COMPONENT_INTERFACE_FEATURE_COUNT = BREAKDOWN_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.ProcessPlanningTemplateImpl <em>Process Planning Template</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.ProcessPlanningTemplateImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getProcessPlanningTemplate()
	 * @generated
	 */
	int PROCESS_PLANNING_TEMPLATE = 60;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__NAME = PROCESS__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__GROUP = PROCESS__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__OWNED_RULE = PROCESS__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__METHOD_ELEMENT_PROPERTY = PROCESS__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__BRIEF_DESCRIPTION = PROCESS__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__ID = PROCESS__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__ORDERING_GUIDE = PROCESS__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__PRESENTATION_NAME = PROCESS__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__SUPPRESSED = PROCESS__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__PRESENTATION = PROCESS__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Fulfill</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__FULFILL = PROCESS__FULFILL;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__IS_ABSTRACT = PROCESS__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__NODEICON = PROCESS__NODEICON;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__SHAPEICON = PROCESS__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Presented After</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__PRESENTED_AFTER = PROCESS__PRESENTED_AFTER;

	/**
	 * The feature id for the '<em><b>Presented Before</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__PRESENTED_BEFORE = PROCESS__PRESENTED_BEFORE;

	/**
	 * The feature id for the '<em><b>Planning Data</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__PLANNING_DATA = PROCESS__PLANNING_DATA;

	/**
	 * The feature id for the '<em><b>Super Activity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__SUPER_ACTIVITY = PROCESS__SUPER_ACTIVITY;

	/**
	 * The feature id for the '<em><b>Group1</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__GROUP1 = PROCESS__GROUP1;

	/**
	 * The feature id for the '<em><b>Checklist</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__CHECKLIST = PROCESS__CHECKLIST;

	/**
	 * The feature id for the '<em><b>Concept</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__CONCEPT = PROCESS__CONCEPT;

	/**
	 * The feature id for the '<em><b>Example</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__EXAMPLE = PROCESS__EXAMPLE;

	/**
	 * The feature id for the '<em><b>Guideline</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__GUIDELINE = PROCESS__GUIDELINE;

	/**
	 * The feature id for the '<em><b>Reusable Asset</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__REUSABLE_ASSET = PROCESS__REUSABLE_ASSET;

	/**
	 * The feature id for the '<em><b>Supporting Material</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__SUPPORTING_MATERIAL = PROCESS__SUPPORTING_MATERIAL;

	/**
	 * The feature id for the '<em><b>Whitepaper</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__WHITEPAPER = PROCESS__WHITEPAPER;

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
	 * The feature id for the '<em><b>Is Planned</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__IS_PLANNED = PROCESS__IS_PLANNED;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__PREFIX = PROCESS__PREFIX;

	/**
	 * The feature id for the '<em><b>Group2</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__GROUP2 = PROCESS__GROUP2;

	/**
	 * The feature id for the '<em><b>Predecessor</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__PREDECESSOR = PROCESS__PREDECESSOR;

	/**
	 * The feature id for the '<em><b>Is Event Driven</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__IS_EVENT_DRIVEN = PROCESS__IS_EVENT_DRIVEN;

	/**
	 * The feature id for the '<em><b>Is Ongoing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__IS_ONGOING = PROCESS__IS_ONGOING;

	/**
	 * The feature id for the '<em><b>Is Repeatable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__IS_REPEATABLE = PROCESS__IS_REPEATABLE;

	/**
	 * The feature id for the '<em><b>Precondition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__PRECONDITION = PROCESS__PRECONDITION;

	/**
	 * The feature id for the '<em><b>Postcondition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__POSTCONDITION = PROCESS__POSTCONDITION;

	/**
	 * The feature id for the '<em><b>Group3</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__GROUP3 = PROCESS__GROUP3;

	/**
	 * The feature id for the '<em><b>Breakdown Element</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__BREAKDOWN_ELEMENT = PROCESS__BREAKDOWN_ELEMENT;

	/**
	 * The feature id for the '<em><b>Roadmap</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__ROADMAP = PROCESS__ROADMAP;

	/**
	 * The feature id for the '<em><b>Is Enactable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__IS_ENACTABLE = PROCESS__IS_ENACTABLE;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__VARIABILITY_BASED_ON_ELEMENT = PROCESS__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__VARIABILITY_TYPE = PROCESS__VARIABILITY_TYPE;

	/**
	 * The feature id for the '<em><b>Includes Pattern</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__INCLUDES_PATTERN = PROCESS__INCLUDES_PATTERN;

	/**
	 * The feature id for the '<em><b>Default Context</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__DEFAULT_CONTEXT = PROCESS__DEFAULT_CONTEXT;

	/**
	 * The feature id for the '<em><b>Valid Context</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__VALID_CONTEXT = PROCESS__VALID_CONTEXT;

	/**
	 * The feature id for the '<em><b>Diagram URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__DIAGRAM_URI = PROCESS__DIAGRAM_URI;

	/**
	 * The feature id for the '<em><b>Group4</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__GROUP4 = PROCESS_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Base Process</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE__BASE_PROCESS = PROCESS_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Process Planning Template</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PLANNING_TEMPLATE_FEATURE_COUNT = PROCESS_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.ReportImpl <em>Report</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.ReportImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getReport()
	 * @generated
	 */
	int REPORT = 61;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT__NAME = GUIDANCE__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT__GROUP = GUIDANCE__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT__OWNED_RULE = GUIDANCE__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT__METHOD_ELEMENT_PROPERTY = GUIDANCE__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT__BRIEF_DESCRIPTION = GUIDANCE__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT__ID = GUIDANCE__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT__ORDERING_GUIDE = GUIDANCE__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT__PRESENTATION_NAME = GUIDANCE__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT__SUPPRESSED = GUIDANCE__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT__PRESENTATION = GUIDANCE__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Fulfill</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT__FULFILL = GUIDANCE__FULFILL;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT__IS_ABSTRACT = GUIDANCE__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT__NODEICON = GUIDANCE__NODEICON;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT__SHAPEICON = GUIDANCE__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Group1</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT__GROUP1 = GUIDANCE__GROUP1;

	/**
	 * The feature id for the '<em><b>Checklist</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT__CHECKLIST = GUIDANCE__CHECKLIST;

	/**
	 * The feature id for the '<em><b>Concept</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT__CONCEPT = GUIDANCE__CONCEPT;

	/**
	 * The feature id for the '<em><b>Example</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT__EXAMPLE = GUIDANCE__EXAMPLE;

	/**
	 * The feature id for the '<em><b>Guideline</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT__GUIDELINE = GUIDANCE__GUIDELINE;

	/**
	 * The feature id for the '<em><b>Reusable Asset</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT__REUSABLE_ASSET = GUIDANCE__REUSABLE_ASSET;

	/**
	 * The feature id for the '<em><b>Supporting Material</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT__SUPPORTING_MATERIAL = GUIDANCE__SUPPORTING_MATERIAL;

	/**
	 * The feature id for the '<em><b>Whitepaper</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT__WHITEPAPER = GUIDANCE__WHITEPAPER;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT__VARIABILITY_BASED_ON_ELEMENT = GUIDANCE__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT__VARIABILITY_TYPE = GUIDANCE__VARIABILITY_TYPE;

	/**
	 * The number of structural features of the '<em>Report</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT_FEATURE_COUNT = GUIDANCE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.ReusableAssetImpl <em>Reusable Asset</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.ReusableAssetImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getReusableAsset()
	 * @generated
	 */
	int REUSABLE_ASSET = 62;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REUSABLE_ASSET__NAME = GUIDANCE__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REUSABLE_ASSET__GROUP = GUIDANCE__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REUSABLE_ASSET__OWNED_RULE = GUIDANCE__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REUSABLE_ASSET__METHOD_ELEMENT_PROPERTY = GUIDANCE__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REUSABLE_ASSET__BRIEF_DESCRIPTION = GUIDANCE__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REUSABLE_ASSET__ID = GUIDANCE__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REUSABLE_ASSET__ORDERING_GUIDE = GUIDANCE__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REUSABLE_ASSET__PRESENTATION_NAME = GUIDANCE__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REUSABLE_ASSET__SUPPRESSED = GUIDANCE__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REUSABLE_ASSET__PRESENTATION = GUIDANCE__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Fulfill</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REUSABLE_ASSET__FULFILL = GUIDANCE__FULFILL;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REUSABLE_ASSET__IS_ABSTRACT = GUIDANCE__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REUSABLE_ASSET__NODEICON = GUIDANCE__NODEICON;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REUSABLE_ASSET__SHAPEICON = GUIDANCE__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Group1</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REUSABLE_ASSET__GROUP1 = GUIDANCE__GROUP1;

	/**
	 * The feature id for the '<em><b>Checklist</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REUSABLE_ASSET__CHECKLIST = GUIDANCE__CHECKLIST;

	/**
	 * The feature id for the '<em><b>Concept</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REUSABLE_ASSET__CONCEPT = GUIDANCE__CONCEPT;

	/**
	 * The feature id for the '<em><b>Example</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REUSABLE_ASSET__EXAMPLE = GUIDANCE__EXAMPLE;

	/**
	 * The feature id for the '<em><b>Guideline</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REUSABLE_ASSET__GUIDELINE = GUIDANCE__GUIDELINE;

	/**
	 * The feature id for the '<em><b>Reusable Asset</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REUSABLE_ASSET__REUSABLE_ASSET = GUIDANCE__REUSABLE_ASSET;

	/**
	 * The feature id for the '<em><b>Supporting Material</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REUSABLE_ASSET__SUPPORTING_MATERIAL = GUIDANCE__SUPPORTING_MATERIAL;

	/**
	 * The feature id for the '<em><b>Whitepaper</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REUSABLE_ASSET__WHITEPAPER = GUIDANCE__WHITEPAPER;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REUSABLE_ASSET__VARIABILITY_BASED_ON_ELEMENT = GUIDANCE__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REUSABLE_ASSET__VARIABILITY_TYPE = GUIDANCE__VARIABILITY_TYPE;

	/**
	 * The number of structural features of the '<em>Reusable Asset</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REUSABLE_ASSET_FEATURE_COUNT = GUIDANCE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.RoadmapImpl <em>Roadmap</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.RoadmapImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getRoadmap()
	 * @generated
	 */
	int ROADMAP = 63;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROADMAP__NAME = GUIDANCE__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROADMAP__GROUP = GUIDANCE__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROADMAP__OWNED_RULE = GUIDANCE__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROADMAP__METHOD_ELEMENT_PROPERTY = GUIDANCE__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROADMAP__BRIEF_DESCRIPTION = GUIDANCE__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROADMAP__ID = GUIDANCE__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROADMAP__ORDERING_GUIDE = GUIDANCE__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROADMAP__PRESENTATION_NAME = GUIDANCE__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROADMAP__SUPPRESSED = GUIDANCE__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROADMAP__PRESENTATION = GUIDANCE__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Fulfill</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROADMAP__FULFILL = GUIDANCE__FULFILL;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROADMAP__IS_ABSTRACT = GUIDANCE__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROADMAP__NODEICON = GUIDANCE__NODEICON;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROADMAP__SHAPEICON = GUIDANCE__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Group1</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROADMAP__GROUP1 = GUIDANCE__GROUP1;

	/**
	 * The feature id for the '<em><b>Checklist</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROADMAP__CHECKLIST = GUIDANCE__CHECKLIST;

	/**
	 * The feature id for the '<em><b>Concept</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROADMAP__CONCEPT = GUIDANCE__CONCEPT;

	/**
	 * The feature id for the '<em><b>Example</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROADMAP__EXAMPLE = GUIDANCE__EXAMPLE;

	/**
	 * The feature id for the '<em><b>Guideline</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROADMAP__GUIDELINE = GUIDANCE__GUIDELINE;

	/**
	 * The feature id for the '<em><b>Reusable Asset</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROADMAP__REUSABLE_ASSET = GUIDANCE__REUSABLE_ASSET;

	/**
	 * The feature id for the '<em><b>Supporting Material</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROADMAP__SUPPORTING_MATERIAL = GUIDANCE__SUPPORTING_MATERIAL;

	/**
	 * The feature id for the '<em><b>Whitepaper</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROADMAP__WHITEPAPER = GUIDANCE__WHITEPAPER;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROADMAP__VARIABILITY_BASED_ON_ELEMENT = GUIDANCE__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROADMAP__VARIABILITY_TYPE = GUIDANCE__VARIABILITY_TYPE;

	/**
	 * The number of structural features of the '<em>Roadmap</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROADMAP_FEATURE_COUNT = GUIDANCE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.RoleImpl <em>Role</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.RoleImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getRole()
	 * @generated
	 */
	int ROLE = 64;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__NAME = CONTENT_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__GROUP = CONTENT_ELEMENT__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__OWNED_RULE = CONTENT_ELEMENT__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__METHOD_ELEMENT_PROPERTY = CONTENT_ELEMENT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__BRIEF_DESCRIPTION = CONTENT_ELEMENT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__ID = CONTENT_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__ORDERING_GUIDE = CONTENT_ELEMENT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__PRESENTATION_NAME = CONTENT_ELEMENT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__SUPPRESSED = CONTENT_ELEMENT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__PRESENTATION = CONTENT_ELEMENT__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Fulfill</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__FULFILL = CONTENT_ELEMENT__FULFILL;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__IS_ABSTRACT = CONTENT_ELEMENT__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__NODEICON = CONTENT_ELEMENT__NODEICON;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__SHAPEICON = CONTENT_ELEMENT__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Group1</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__GROUP1 = CONTENT_ELEMENT__GROUP1;

	/**
	 * The feature id for the '<em><b>Checklist</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__CHECKLIST = CONTENT_ELEMENT__CHECKLIST;

	/**
	 * The feature id for the '<em><b>Concept</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__CONCEPT = CONTENT_ELEMENT__CONCEPT;

	/**
	 * The feature id for the '<em><b>Example</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__EXAMPLE = CONTENT_ELEMENT__EXAMPLE;

	/**
	 * The feature id for the '<em><b>Guideline</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__GUIDELINE = CONTENT_ELEMENT__GUIDELINE;

	/**
	 * The feature id for the '<em><b>Reusable Asset</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__REUSABLE_ASSET = CONTENT_ELEMENT__REUSABLE_ASSET;

	/**
	 * The feature id for the '<em><b>Supporting Material</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__SUPPORTING_MATERIAL = CONTENT_ELEMENT__SUPPORTING_MATERIAL;

	/**
	 * The feature id for the '<em><b>Whitepaper</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__WHITEPAPER = CONTENT_ELEMENT__WHITEPAPER;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__VARIABILITY_BASED_ON_ELEMENT = CONTENT_ELEMENT__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__VARIABILITY_TYPE = CONTENT_ELEMENT__VARIABILITY_TYPE;

	/**
	 * The feature id for the '<em><b>Group2</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__GROUP2 = CONTENT_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Responsible For</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__RESPONSIBLE_FOR = CONTENT_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Role</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_FEATURE_COUNT = CONTENT_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.RoleDescriptionImpl <em>Role Description</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.RoleDescriptionImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getRoleDescription()
	 * @generated
	 */
	int ROLE_DESCRIPTION = 65;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTION__NAME = CONTENT_DESCRIPTION__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTION__GROUP = CONTENT_DESCRIPTION__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTION__OWNED_RULE = CONTENT_DESCRIPTION__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTION__METHOD_ELEMENT_PROPERTY = CONTENT_DESCRIPTION__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTION__BRIEF_DESCRIPTION = CONTENT_DESCRIPTION__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTION__ID = CONTENT_DESCRIPTION__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTION__ORDERING_GUIDE = CONTENT_DESCRIPTION__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTION__PRESENTATION_NAME = CONTENT_DESCRIPTION__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTION__SUPPRESSED = CONTENT_DESCRIPTION__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Copyright</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTION__COPYRIGHT = CONTENT_DESCRIPTION__COPYRIGHT;

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
	 * The feature id for the '<em><b>Main Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTION__MAIN_DESCRIPTION = CONTENT_DESCRIPTION__MAIN_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Key Considerations</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTION__KEY_CONSIDERATIONS = CONTENT_DESCRIPTION__KEY_CONSIDERATIONS;

	/**
	 * The feature id for the '<em><b>Section</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTION__SECTION = CONTENT_DESCRIPTION__SECTION;

	/**
	 * The feature id for the '<em><b>External Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTION__EXTERNAL_ID = CONTENT_DESCRIPTION__EXTERNAL_ID;

	/**
	 * The feature id for the '<em><b>Assignment Approaches</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTION__ASSIGNMENT_APPROACHES = CONTENT_DESCRIPTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Skills</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_DESCRIPTION__SKILLS = CONTENT_DESCRIPTION_FEATURE_COUNT + 1;

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
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.RoleSetImpl <em>Role Set</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.RoleSetImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getRoleSet()
	 * @generated
	 */
	int ROLE_SET = 67;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET__NAME = CONTENT_CATEGORY__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET__GROUP = CONTENT_CATEGORY__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET__OWNED_RULE = CONTENT_CATEGORY__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET__METHOD_ELEMENT_PROPERTY = CONTENT_CATEGORY__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET__BRIEF_DESCRIPTION = CONTENT_CATEGORY__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET__ID = CONTENT_CATEGORY__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET__ORDERING_GUIDE = CONTENT_CATEGORY__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET__PRESENTATION_NAME = CONTENT_CATEGORY__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET__SUPPRESSED = CONTENT_CATEGORY__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET__PRESENTATION = CONTENT_CATEGORY__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Fulfill</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET__FULFILL = CONTENT_CATEGORY__FULFILL;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET__IS_ABSTRACT = CONTENT_CATEGORY__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET__NODEICON = CONTENT_CATEGORY__NODEICON;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET__SHAPEICON = CONTENT_CATEGORY__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Group1</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET__GROUP1 = CONTENT_CATEGORY__GROUP1;

	/**
	 * The feature id for the '<em><b>Checklist</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET__CHECKLIST = CONTENT_CATEGORY__CHECKLIST;

	/**
	 * The feature id for the '<em><b>Concept</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET__CONCEPT = CONTENT_CATEGORY__CONCEPT;

	/**
	 * The feature id for the '<em><b>Example</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET__EXAMPLE = CONTENT_CATEGORY__EXAMPLE;

	/**
	 * The feature id for the '<em><b>Guideline</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET__GUIDELINE = CONTENT_CATEGORY__GUIDELINE;

	/**
	 * The feature id for the '<em><b>Reusable Asset</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET__REUSABLE_ASSET = CONTENT_CATEGORY__REUSABLE_ASSET;

	/**
	 * The feature id for the '<em><b>Supporting Material</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET__SUPPORTING_MATERIAL = CONTENT_CATEGORY__SUPPORTING_MATERIAL;

	/**
	 * The feature id for the '<em><b>Whitepaper</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET__WHITEPAPER = CONTENT_CATEGORY__WHITEPAPER;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET__VARIABILITY_BASED_ON_ELEMENT = CONTENT_CATEGORY__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET__VARIABILITY_TYPE = CONTENT_CATEGORY__VARIABILITY_TYPE;

	/**
	 * The feature id for the '<em><b>Group2</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET__GROUP2 = CONTENT_CATEGORY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Role</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET__ROLE = CONTENT_CATEGORY_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Role Set</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET_FEATURE_COUNT = CONTENT_CATEGORY_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.RoleSetGroupingImpl <em>Role Set Grouping</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.RoleSetGroupingImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getRoleSetGrouping()
	 * @generated
	 */
	int ROLE_SET_GROUPING = 68;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET_GROUPING__NAME = CONTENT_CATEGORY__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET_GROUPING__GROUP = CONTENT_CATEGORY__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET_GROUPING__OWNED_RULE = CONTENT_CATEGORY__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET_GROUPING__METHOD_ELEMENT_PROPERTY = CONTENT_CATEGORY__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET_GROUPING__BRIEF_DESCRIPTION = CONTENT_CATEGORY__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET_GROUPING__ID = CONTENT_CATEGORY__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET_GROUPING__ORDERING_GUIDE = CONTENT_CATEGORY__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET_GROUPING__PRESENTATION_NAME = CONTENT_CATEGORY__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET_GROUPING__SUPPRESSED = CONTENT_CATEGORY__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET_GROUPING__PRESENTATION = CONTENT_CATEGORY__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Fulfill</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET_GROUPING__FULFILL = CONTENT_CATEGORY__FULFILL;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET_GROUPING__IS_ABSTRACT = CONTENT_CATEGORY__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET_GROUPING__NODEICON = CONTENT_CATEGORY__NODEICON;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET_GROUPING__SHAPEICON = CONTENT_CATEGORY__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Group1</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET_GROUPING__GROUP1 = CONTENT_CATEGORY__GROUP1;

	/**
	 * The feature id for the '<em><b>Checklist</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET_GROUPING__CHECKLIST = CONTENT_CATEGORY__CHECKLIST;

	/**
	 * The feature id for the '<em><b>Concept</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET_GROUPING__CONCEPT = CONTENT_CATEGORY__CONCEPT;

	/**
	 * The feature id for the '<em><b>Example</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET_GROUPING__EXAMPLE = CONTENT_CATEGORY__EXAMPLE;

	/**
	 * The feature id for the '<em><b>Guideline</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET_GROUPING__GUIDELINE = CONTENT_CATEGORY__GUIDELINE;

	/**
	 * The feature id for the '<em><b>Reusable Asset</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET_GROUPING__REUSABLE_ASSET = CONTENT_CATEGORY__REUSABLE_ASSET;

	/**
	 * The feature id for the '<em><b>Supporting Material</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET_GROUPING__SUPPORTING_MATERIAL = CONTENT_CATEGORY__SUPPORTING_MATERIAL;

	/**
	 * The feature id for the '<em><b>Whitepaper</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET_GROUPING__WHITEPAPER = CONTENT_CATEGORY__WHITEPAPER;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET_GROUPING__VARIABILITY_BASED_ON_ELEMENT = CONTENT_CATEGORY__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET_GROUPING__VARIABILITY_TYPE = CONTENT_CATEGORY__VARIABILITY_TYPE;

	/**
	 * The feature id for the '<em><b>Group2</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET_GROUPING__GROUP2 = CONTENT_CATEGORY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Role Set</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET_GROUPING__ROLE_SET = CONTENT_CATEGORY_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Role Set Grouping</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SET_GROUPING_FEATURE_COUNT = CONTENT_CATEGORY_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.SectionImpl <em>Section</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.SectionImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getSection()
	 * @generated
	 */
	int SECTION = 69;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__NAME = METHOD_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__GROUP = METHOD_ELEMENT__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__OWNED_RULE = METHOD_ELEMENT__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__METHOD_ELEMENT_PROPERTY = METHOD_ELEMENT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__BRIEF_DESCRIPTION = METHOD_ELEMENT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__ID = METHOD_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__ORDERING_GUIDE = METHOD_ELEMENT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__PRESENTATION_NAME = METHOD_ELEMENT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__SUPPRESSED = METHOD_ELEMENT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Sub Section</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__SUB_SECTION = METHOD_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Predecessor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__PREDECESSOR = METHOD_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__DESCRIPTION = METHOD_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Section Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__SECTION_NAME = METHOD_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__VARIABILITY_BASED_ON_ELEMENT = METHOD_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__VARIABILITY_TYPE = METHOD_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Section</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION_FEATURE_COUNT = METHOD_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.SupportingMaterialImpl <em>Supporting Material</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.SupportingMaterialImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getSupportingMaterial()
	 * @generated
	 */
	int SUPPORTING_MATERIAL = 70;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPORTING_MATERIAL__NAME = GUIDANCE__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPORTING_MATERIAL__GROUP = GUIDANCE__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPORTING_MATERIAL__OWNED_RULE = GUIDANCE__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPORTING_MATERIAL__METHOD_ELEMENT_PROPERTY = GUIDANCE__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPORTING_MATERIAL__BRIEF_DESCRIPTION = GUIDANCE__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPORTING_MATERIAL__ID = GUIDANCE__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPORTING_MATERIAL__ORDERING_GUIDE = GUIDANCE__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPORTING_MATERIAL__PRESENTATION_NAME = GUIDANCE__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPORTING_MATERIAL__SUPPRESSED = GUIDANCE__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPORTING_MATERIAL__PRESENTATION = GUIDANCE__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Fulfill</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPORTING_MATERIAL__FULFILL = GUIDANCE__FULFILL;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPORTING_MATERIAL__IS_ABSTRACT = GUIDANCE__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPORTING_MATERIAL__NODEICON = GUIDANCE__NODEICON;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPORTING_MATERIAL__SHAPEICON = GUIDANCE__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Group1</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPORTING_MATERIAL__GROUP1 = GUIDANCE__GROUP1;

	/**
	 * The feature id for the '<em><b>Checklist</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPORTING_MATERIAL__CHECKLIST = GUIDANCE__CHECKLIST;

	/**
	 * The feature id for the '<em><b>Concept</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPORTING_MATERIAL__CONCEPT = GUIDANCE__CONCEPT;

	/**
	 * The feature id for the '<em><b>Example</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPORTING_MATERIAL__EXAMPLE = GUIDANCE__EXAMPLE;

	/**
	 * The feature id for the '<em><b>Guideline</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPORTING_MATERIAL__GUIDELINE = GUIDANCE__GUIDELINE;

	/**
	 * The feature id for the '<em><b>Reusable Asset</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPORTING_MATERIAL__REUSABLE_ASSET = GUIDANCE__REUSABLE_ASSET;

	/**
	 * The feature id for the '<em><b>Supporting Material</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPORTING_MATERIAL__SUPPORTING_MATERIAL = GUIDANCE__SUPPORTING_MATERIAL;

	/**
	 * The feature id for the '<em><b>Whitepaper</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPORTING_MATERIAL__WHITEPAPER = GUIDANCE__WHITEPAPER;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPORTING_MATERIAL__VARIABILITY_BASED_ON_ELEMENT = GUIDANCE__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPORTING_MATERIAL__VARIABILITY_TYPE = GUIDANCE__VARIABILITY_TYPE;

	/**
	 * The number of structural features of the '<em>Supporting Material</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPORTING_MATERIAL_FEATURE_COUNT = GUIDANCE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.TaskImpl <em>Task</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.TaskImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getTask()
	 * @generated
	 */
	int TASK = 71;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__NAME = CONTENT_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__GROUP = CONTENT_ELEMENT__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__OWNED_RULE = CONTENT_ELEMENT__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__METHOD_ELEMENT_PROPERTY = CONTENT_ELEMENT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__BRIEF_DESCRIPTION = CONTENT_ELEMENT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__ID = CONTENT_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__ORDERING_GUIDE = CONTENT_ELEMENT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__PRESENTATION_NAME = CONTENT_ELEMENT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__SUPPRESSED = CONTENT_ELEMENT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__PRESENTATION = CONTENT_ELEMENT__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Fulfill</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__FULFILL = CONTENT_ELEMENT__FULFILL;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__IS_ABSTRACT = CONTENT_ELEMENT__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__NODEICON = CONTENT_ELEMENT__NODEICON;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__SHAPEICON = CONTENT_ELEMENT__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Group1</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__GROUP1 = CONTENT_ELEMENT__GROUP1;

	/**
	 * The feature id for the '<em><b>Checklist</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__CHECKLIST = CONTENT_ELEMENT__CHECKLIST;

	/**
	 * The feature id for the '<em><b>Concept</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__CONCEPT = CONTENT_ELEMENT__CONCEPT;

	/**
	 * The feature id for the '<em><b>Example</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__EXAMPLE = CONTENT_ELEMENT__EXAMPLE;

	/**
	 * The feature id for the '<em><b>Guideline</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__GUIDELINE = CONTENT_ELEMENT__GUIDELINE;

	/**
	 * The feature id for the '<em><b>Reusable Asset</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__REUSABLE_ASSET = CONTENT_ELEMENT__REUSABLE_ASSET;

	/**
	 * The feature id for the '<em><b>Supporting Material</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__SUPPORTING_MATERIAL = CONTENT_ELEMENT__SUPPORTING_MATERIAL;

	/**
	 * The feature id for the '<em><b>Whitepaper</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__WHITEPAPER = CONTENT_ELEMENT__WHITEPAPER;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__VARIABILITY_BASED_ON_ELEMENT = CONTENT_ELEMENT__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__VARIABILITY_TYPE = CONTENT_ELEMENT__VARIABILITY_TYPE;

	/**
	 * The feature id for the '<em><b>Precondition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__PRECONDITION = CONTENT_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Postcondition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__POSTCONDITION = CONTENT_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Performed By</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__PERFORMED_BY = CONTENT_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Group2</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__GROUP2 = CONTENT_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Mandatory Input</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__MANDATORY_INPUT = CONTENT_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Output</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__OUTPUT = CONTENT_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Additionally Performed By</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__ADDITIONALLY_PERFORMED_BY = CONTENT_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Optional Input</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__OPTIONAL_INPUT = CONTENT_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Estimate</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__ESTIMATE = CONTENT_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Estimation Considerations</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__ESTIMATION_CONSIDERATIONS = CONTENT_ELEMENT_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Tool Mentor</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__TOOL_MENTOR = CONTENT_ELEMENT_FEATURE_COUNT + 10;

	/**
	 * The number of structural features of the '<em>Task</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_FEATURE_COUNT = CONTENT_ELEMENT_FEATURE_COUNT + 11;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.TaskDescriptionImpl <em>Task Description</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.TaskDescriptionImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getTaskDescription()
	 * @generated
	 */
	int TASK_DESCRIPTION = 72;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTION__NAME = CONTENT_DESCRIPTION__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTION__GROUP = CONTENT_DESCRIPTION__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTION__OWNED_RULE = CONTENT_DESCRIPTION__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTION__METHOD_ELEMENT_PROPERTY = CONTENT_DESCRIPTION__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTION__BRIEF_DESCRIPTION = CONTENT_DESCRIPTION__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTION__ID = CONTENT_DESCRIPTION__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTION__ORDERING_GUIDE = CONTENT_DESCRIPTION__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTION__PRESENTATION_NAME = CONTENT_DESCRIPTION__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTION__SUPPRESSED = CONTENT_DESCRIPTION__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Copyright</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTION__COPYRIGHT = CONTENT_DESCRIPTION__COPYRIGHT;

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
	 * The feature id for the '<em><b>Main Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTION__MAIN_DESCRIPTION = CONTENT_DESCRIPTION__MAIN_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Key Considerations</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTION__KEY_CONSIDERATIONS = CONTENT_DESCRIPTION__KEY_CONSIDERATIONS;

	/**
	 * The feature id for the '<em><b>Section</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTION__SECTION = CONTENT_DESCRIPTION__SECTION;

	/**
	 * The feature id for the '<em><b>External Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTION__EXTERNAL_ID = CONTENT_DESCRIPTION__EXTERNAL_ID;

	/**
	 * The feature id for the '<em><b>Alternatives</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTION__ALTERNATIVES = CONTENT_DESCRIPTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Purpose</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTION__PURPOSE = CONTENT_DESCRIPTION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Task Description</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTION_FEATURE_COUNT = CONTENT_DESCRIPTION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.TaskDescriptorImpl <em>Task Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.TaskDescriptorImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getTaskDescriptor()
	 * @generated
	 */
	int TASK_DESCRIPTOR = 73;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__NAME = WORK_BREAKDOWN_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__GROUP = WORK_BREAKDOWN_ELEMENT__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__OWNED_RULE = WORK_BREAKDOWN_ELEMENT__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__METHOD_ELEMENT_PROPERTY = WORK_BREAKDOWN_ELEMENT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__BRIEF_DESCRIPTION = WORK_BREAKDOWN_ELEMENT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__ID = WORK_BREAKDOWN_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__ORDERING_GUIDE = WORK_BREAKDOWN_ELEMENT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__PRESENTATION_NAME = WORK_BREAKDOWN_ELEMENT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__SUPPRESSED = WORK_BREAKDOWN_ELEMENT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__PRESENTATION = WORK_BREAKDOWN_ELEMENT__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Fulfill</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__FULFILL = WORK_BREAKDOWN_ELEMENT__FULFILL;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__IS_ABSTRACT = WORK_BREAKDOWN_ELEMENT__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__NODEICON = WORK_BREAKDOWN_ELEMENT__NODEICON;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__SHAPEICON = WORK_BREAKDOWN_ELEMENT__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Presented After</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__PRESENTED_AFTER = WORK_BREAKDOWN_ELEMENT__PRESENTED_AFTER;

	/**
	 * The feature id for the '<em><b>Presented Before</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__PRESENTED_BEFORE = WORK_BREAKDOWN_ELEMENT__PRESENTED_BEFORE;

	/**
	 * The feature id for the '<em><b>Planning Data</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__PLANNING_DATA = WORK_BREAKDOWN_ELEMENT__PLANNING_DATA;

	/**
	 * The feature id for the '<em><b>Super Activity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__SUPER_ACTIVITY = WORK_BREAKDOWN_ELEMENT__SUPER_ACTIVITY;

	/**
	 * The feature id for the '<em><b>Group1</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__GROUP1 = WORK_BREAKDOWN_ELEMENT__GROUP1;

	/**
	 * The feature id for the '<em><b>Checklist</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__CHECKLIST = WORK_BREAKDOWN_ELEMENT__CHECKLIST;

	/**
	 * The feature id for the '<em><b>Concept</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__CONCEPT = WORK_BREAKDOWN_ELEMENT__CONCEPT;

	/**
	 * The feature id for the '<em><b>Example</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__EXAMPLE = WORK_BREAKDOWN_ELEMENT__EXAMPLE;

	/**
	 * The feature id for the '<em><b>Guideline</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__GUIDELINE = WORK_BREAKDOWN_ELEMENT__GUIDELINE;

	/**
	 * The feature id for the '<em><b>Reusable Asset</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__REUSABLE_ASSET = WORK_BREAKDOWN_ELEMENT__REUSABLE_ASSET;

	/**
	 * The feature id for the '<em><b>Supporting Material</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__SUPPORTING_MATERIAL = WORK_BREAKDOWN_ELEMENT__SUPPORTING_MATERIAL;

	/**
	 * The feature id for the '<em><b>Whitepaper</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__WHITEPAPER = WORK_BREAKDOWN_ELEMENT__WHITEPAPER;

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
	 * The feature id for the '<em><b>Is Planned</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__IS_PLANNED = WORK_BREAKDOWN_ELEMENT__IS_PLANNED;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__PREFIX = WORK_BREAKDOWN_ELEMENT__PREFIX;

	/**
	 * The feature id for the '<em><b>Group2</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__GROUP2 = WORK_BREAKDOWN_ELEMENT__GROUP2;

	/**
	 * The feature id for the '<em><b>Predecessor</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__PREDECESSOR = WORK_BREAKDOWN_ELEMENT__PREDECESSOR;

	/**
	 * The feature id for the '<em><b>Is Event Driven</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__IS_EVENT_DRIVEN = WORK_BREAKDOWN_ELEMENT__IS_EVENT_DRIVEN;

	/**
	 * The feature id for the '<em><b>Is Ongoing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__IS_ONGOING = WORK_BREAKDOWN_ELEMENT__IS_ONGOING;

	/**
	 * The feature id for the '<em><b>Is Repeatable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__IS_REPEATABLE = WORK_BREAKDOWN_ELEMENT__IS_REPEATABLE;

	/**
	 * The feature id for the '<em><b>Task</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__TASK = WORK_BREAKDOWN_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Group3</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__GROUP3 = WORK_BREAKDOWN_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Performed Primarily By</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__PERFORMED_PRIMARILY_BY = WORK_BREAKDOWN_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Additionally Performed By</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__ADDITIONALLY_PERFORMED_BY = WORK_BREAKDOWN_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Assisted By</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__ASSISTED_BY = WORK_BREAKDOWN_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>External Input</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__EXTERNAL_INPUT = WORK_BREAKDOWN_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Mandatory Input</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__MANDATORY_INPUT = WORK_BREAKDOWN_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Optional Input</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__OPTIONAL_INPUT = WORK_BREAKDOWN_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Output</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__OUTPUT = WORK_BREAKDOWN_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Step</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__STEP = WORK_BREAKDOWN_ELEMENT_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Is Synchronized With Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR__IS_SYNCHRONIZED_WITH_SOURCE = WORK_BREAKDOWN_ELEMENT_FEATURE_COUNT + 10;

	/**
	 * The number of structural features of the '<em>Task Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_DESCRIPTOR_FEATURE_COUNT = WORK_BREAKDOWN_ELEMENT_FEATURE_COUNT + 11;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.TeamProfileImpl <em>Team Profile</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.TeamProfileImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getTeamProfile()
	 * @generated
	 */
	int TEAM_PROFILE = 74;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__NAME = BREAKDOWN_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__GROUP = BREAKDOWN_ELEMENT__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__OWNED_RULE = BREAKDOWN_ELEMENT__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__METHOD_ELEMENT_PROPERTY = BREAKDOWN_ELEMENT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__BRIEF_DESCRIPTION = BREAKDOWN_ELEMENT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__ID = BREAKDOWN_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__ORDERING_GUIDE = BREAKDOWN_ELEMENT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__PRESENTATION_NAME = BREAKDOWN_ELEMENT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__SUPPRESSED = BREAKDOWN_ELEMENT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__PRESENTATION = BREAKDOWN_ELEMENT__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Fulfill</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__FULFILL = BREAKDOWN_ELEMENT__FULFILL;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__IS_ABSTRACT = BREAKDOWN_ELEMENT__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__NODEICON = BREAKDOWN_ELEMENT__NODEICON;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__SHAPEICON = BREAKDOWN_ELEMENT__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Presented After</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__PRESENTED_AFTER = BREAKDOWN_ELEMENT__PRESENTED_AFTER;

	/**
	 * The feature id for the '<em><b>Presented Before</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__PRESENTED_BEFORE = BREAKDOWN_ELEMENT__PRESENTED_BEFORE;

	/**
	 * The feature id for the '<em><b>Planning Data</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__PLANNING_DATA = BREAKDOWN_ELEMENT__PLANNING_DATA;

	/**
	 * The feature id for the '<em><b>Super Activity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__SUPER_ACTIVITY = BREAKDOWN_ELEMENT__SUPER_ACTIVITY;

	/**
	 * The feature id for the '<em><b>Group1</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__GROUP1 = BREAKDOWN_ELEMENT__GROUP1;

	/**
	 * The feature id for the '<em><b>Checklist</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__CHECKLIST = BREAKDOWN_ELEMENT__CHECKLIST;

	/**
	 * The feature id for the '<em><b>Concept</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__CONCEPT = BREAKDOWN_ELEMENT__CONCEPT;

	/**
	 * The feature id for the '<em><b>Example</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__EXAMPLE = BREAKDOWN_ELEMENT__EXAMPLE;

	/**
	 * The feature id for the '<em><b>Guideline</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__GUIDELINE = BREAKDOWN_ELEMENT__GUIDELINE;

	/**
	 * The feature id for the '<em><b>Reusable Asset</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__REUSABLE_ASSET = BREAKDOWN_ELEMENT__REUSABLE_ASSET;

	/**
	 * The feature id for the '<em><b>Supporting Material</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__SUPPORTING_MATERIAL = BREAKDOWN_ELEMENT__SUPPORTING_MATERIAL;

	/**
	 * The feature id for the '<em><b>Whitepaper</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__WHITEPAPER = BREAKDOWN_ELEMENT__WHITEPAPER;

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
	 * The feature id for the '<em><b>Is Planned</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__IS_PLANNED = BREAKDOWN_ELEMENT__IS_PLANNED;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__PREFIX = BREAKDOWN_ELEMENT__PREFIX;

	/**
	 * The feature id for the '<em><b>Group2</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__GROUP2 = BREAKDOWN_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Role</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__ROLE = BREAKDOWN_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Super Team</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__SUPER_TEAM = BREAKDOWN_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Sub Team</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE__SUB_TEAM = BREAKDOWN_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Team Profile</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_PROFILE_FEATURE_COUNT = BREAKDOWN_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.TemplateImpl <em>Template</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.TemplateImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getTemplate()
	 * @generated
	 */
	int TEMPLATE = 75;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE__NAME = GUIDANCE__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE__GROUP = GUIDANCE__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE__OWNED_RULE = GUIDANCE__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE__METHOD_ELEMENT_PROPERTY = GUIDANCE__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE__BRIEF_DESCRIPTION = GUIDANCE__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE__ID = GUIDANCE__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE__ORDERING_GUIDE = GUIDANCE__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE__PRESENTATION_NAME = GUIDANCE__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE__SUPPRESSED = GUIDANCE__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE__PRESENTATION = GUIDANCE__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Fulfill</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE__FULFILL = GUIDANCE__FULFILL;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE__IS_ABSTRACT = GUIDANCE__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE__NODEICON = GUIDANCE__NODEICON;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE__SHAPEICON = GUIDANCE__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Group1</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE__GROUP1 = GUIDANCE__GROUP1;

	/**
	 * The feature id for the '<em><b>Checklist</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE__CHECKLIST = GUIDANCE__CHECKLIST;

	/**
	 * The feature id for the '<em><b>Concept</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE__CONCEPT = GUIDANCE__CONCEPT;

	/**
	 * The feature id for the '<em><b>Example</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE__EXAMPLE = GUIDANCE__EXAMPLE;

	/**
	 * The feature id for the '<em><b>Guideline</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE__GUIDELINE = GUIDANCE__GUIDELINE;

	/**
	 * The feature id for the '<em><b>Reusable Asset</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE__REUSABLE_ASSET = GUIDANCE__REUSABLE_ASSET;

	/**
	 * The feature id for the '<em><b>Supporting Material</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE__SUPPORTING_MATERIAL = GUIDANCE__SUPPORTING_MATERIAL;

	/**
	 * The feature id for the '<em><b>Whitepaper</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE__WHITEPAPER = GUIDANCE__WHITEPAPER;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE__VARIABILITY_BASED_ON_ELEMENT = GUIDANCE__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE__VARIABILITY_TYPE = GUIDANCE__VARIABILITY_TYPE;

	/**
	 * The number of structural features of the '<em>Template</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE_FEATURE_COUNT = GUIDANCE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.TermDefinitionImpl <em>Term Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.TermDefinitionImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getTermDefinition()
	 * @generated
	 */
	int TERM_DEFINITION = 76;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERM_DEFINITION__NAME = GUIDANCE__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERM_DEFINITION__GROUP = GUIDANCE__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERM_DEFINITION__OWNED_RULE = GUIDANCE__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERM_DEFINITION__METHOD_ELEMENT_PROPERTY = GUIDANCE__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERM_DEFINITION__BRIEF_DESCRIPTION = GUIDANCE__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERM_DEFINITION__ID = GUIDANCE__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERM_DEFINITION__ORDERING_GUIDE = GUIDANCE__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERM_DEFINITION__PRESENTATION_NAME = GUIDANCE__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERM_DEFINITION__SUPPRESSED = GUIDANCE__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERM_DEFINITION__PRESENTATION = GUIDANCE__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Fulfill</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERM_DEFINITION__FULFILL = GUIDANCE__FULFILL;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERM_DEFINITION__IS_ABSTRACT = GUIDANCE__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERM_DEFINITION__NODEICON = GUIDANCE__NODEICON;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERM_DEFINITION__SHAPEICON = GUIDANCE__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Group1</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERM_DEFINITION__GROUP1 = GUIDANCE__GROUP1;

	/**
	 * The feature id for the '<em><b>Checklist</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERM_DEFINITION__CHECKLIST = GUIDANCE__CHECKLIST;

	/**
	 * The feature id for the '<em><b>Concept</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERM_DEFINITION__CONCEPT = GUIDANCE__CONCEPT;

	/**
	 * The feature id for the '<em><b>Example</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERM_DEFINITION__EXAMPLE = GUIDANCE__EXAMPLE;

	/**
	 * The feature id for the '<em><b>Guideline</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERM_DEFINITION__GUIDELINE = GUIDANCE__GUIDELINE;

	/**
	 * The feature id for the '<em><b>Reusable Asset</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERM_DEFINITION__REUSABLE_ASSET = GUIDANCE__REUSABLE_ASSET;

	/**
	 * The feature id for the '<em><b>Supporting Material</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERM_DEFINITION__SUPPORTING_MATERIAL = GUIDANCE__SUPPORTING_MATERIAL;

	/**
	 * The feature id for the '<em><b>Whitepaper</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERM_DEFINITION__WHITEPAPER = GUIDANCE__WHITEPAPER;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERM_DEFINITION__VARIABILITY_BASED_ON_ELEMENT = GUIDANCE__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERM_DEFINITION__VARIABILITY_TYPE = GUIDANCE__VARIABILITY_TYPE;

	/**
	 * The number of structural features of the '<em>Term Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERM_DEFINITION_FEATURE_COUNT = GUIDANCE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.ToolImpl <em>Tool</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.ToolImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getTool()
	 * @generated
	 */
	int TOOL = 77;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__NAME = CONTENT_CATEGORY__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__GROUP = CONTENT_CATEGORY__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__OWNED_RULE = CONTENT_CATEGORY__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__METHOD_ELEMENT_PROPERTY = CONTENT_CATEGORY__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__BRIEF_DESCRIPTION = CONTENT_CATEGORY__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__ID = CONTENT_CATEGORY__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__ORDERING_GUIDE = CONTENT_CATEGORY__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__PRESENTATION_NAME = CONTENT_CATEGORY__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__SUPPRESSED = CONTENT_CATEGORY__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__PRESENTATION = CONTENT_CATEGORY__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Fulfill</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__FULFILL = CONTENT_CATEGORY__FULFILL;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__IS_ABSTRACT = CONTENT_CATEGORY__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__NODEICON = CONTENT_CATEGORY__NODEICON;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__SHAPEICON = CONTENT_CATEGORY__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Group1</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__GROUP1 = CONTENT_CATEGORY__GROUP1;

	/**
	 * The feature id for the '<em><b>Checklist</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__CHECKLIST = CONTENT_CATEGORY__CHECKLIST;

	/**
	 * The feature id for the '<em><b>Concept</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__CONCEPT = CONTENT_CATEGORY__CONCEPT;

	/**
	 * The feature id for the '<em><b>Example</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__EXAMPLE = CONTENT_CATEGORY__EXAMPLE;

	/**
	 * The feature id for the '<em><b>Guideline</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__GUIDELINE = CONTENT_CATEGORY__GUIDELINE;

	/**
	 * The feature id for the '<em><b>Reusable Asset</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__REUSABLE_ASSET = CONTENT_CATEGORY__REUSABLE_ASSET;

	/**
	 * The feature id for the '<em><b>Supporting Material</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__SUPPORTING_MATERIAL = CONTENT_CATEGORY__SUPPORTING_MATERIAL;

	/**
	 * The feature id for the '<em><b>Whitepaper</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__WHITEPAPER = CONTENT_CATEGORY__WHITEPAPER;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__VARIABILITY_BASED_ON_ELEMENT = CONTENT_CATEGORY__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__VARIABILITY_TYPE = CONTENT_CATEGORY__VARIABILITY_TYPE;

	/**
	 * The feature id for the '<em><b>Group2</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__GROUP2 = CONTENT_CATEGORY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Tool Mentor</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__TOOL_MENTOR = CONTENT_CATEGORY_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Tool</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_FEATURE_COUNT = CONTENT_CATEGORY_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.ToolMentorImpl <em>Tool Mentor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.ToolMentorImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getToolMentor()
	 * @generated
	 */
	int TOOL_MENTOR = 78;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_MENTOR__NAME = GUIDANCE__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_MENTOR__GROUP = GUIDANCE__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_MENTOR__OWNED_RULE = GUIDANCE__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_MENTOR__METHOD_ELEMENT_PROPERTY = GUIDANCE__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_MENTOR__BRIEF_DESCRIPTION = GUIDANCE__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_MENTOR__ID = GUIDANCE__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_MENTOR__ORDERING_GUIDE = GUIDANCE__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_MENTOR__PRESENTATION_NAME = GUIDANCE__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_MENTOR__SUPPRESSED = GUIDANCE__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_MENTOR__PRESENTATION = GUIDANCE__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Fulfill</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_MENTOR__FULFILL = GUIDANCE__FULFILL;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_MENTOR__IS_ABSTRACT = GUIDANCE__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_MENTOR__NODEICON = GUIDANCE__NODEICON;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_MENTOR__SHAPEICON = GUIDANCE__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Group1</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_MENTOR__GROUP1 = GUIDANCE__GROUP1;

	/**
	 * The feature id for the '<em><b>Checklist</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_MENTOR__CHECKLIST = GUIDANCE__CHECKLIST;

	/**
	 * The feature id for the '<em><b>Concept</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_MENTOR__CONCEPT = GUIDANCE__CONCEPT;

	/**
	 * The feature id for the '<em><b>Example</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_MENTOR__EXAMPLE = GUIDANCE__EXAMPLE;

	/**
	 * The feature id for the '<em><b>Guideline</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_MENTOR__GUIDELINE = GUIDANCE__GUIDELINE;

	/**
	 * The feature id for the '<em><b>Reusable Asset</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_MENTOR__REUSABLE_ASSET = GUIDANCE__REUSABLE_ASSET;

	/**
	 * The feature id for the '<em><b>Supporting Material</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_MENTOR__SUPPORTING_MATERIAL = GUIDANCE__SUPPORTING_MATERIAL;

	/**
	 * The feature id for the '<em><b>Whitepaper</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_MENTOR__WHITEPAPER = GUIDANCE__WHITEPAPER;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_MENTOR__VARIABILITY_BASED_ON_ELEMENT = GUIDANCE__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_MENTOR__VARIABILITY_TYPE = GUIDANCE__VARIABILITY_TYPE;

	/**
	 * The number of structural features of the '<em>Tool Mentor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_MENTOR_FEATURE_COUNT = GUIDANCE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.WhitepaperImpl <em>Whitepaper</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.WhitepaperImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getWhitepaper()
	 * @generated
	 */
	int WHITEPAPER = 79;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHITEPAPER__NAME = CONCEPT__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHITEPAPER__GROUP = CONCEPT__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHITEPAPER__OWNED_RULE = CONCEPT__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHITEPAPER__METHOD_ELEMENT_PROPERTY = CONCEPT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHITEPAPER__BRIEF_DESCRIPTION = CONCEPT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHITEPAPER__ID = CONCEPT__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHITEPAPER__ORDERING_GUIDE = CONCEPT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHITEPAPER__PRESENTATION_NAME = CONCEPT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHITEPAPER__SUPPRESSED = CONCEPT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHITEPAPER__PRESENTATION = CONCEPT__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Fulfill</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHITEPAPER__FULFILL = CONCEPT__FULFILL;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHITEPAPER__IS_ABSTRACT = CONCEPT__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHITEPAPER__NODEICON = CONCEPT__NODEICON;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHITEPAPER__SHAPEICON = CONCEPT__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Group1</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHITEPAPER__GROUP1 = CONCEPT__GROUP1;

	/**
	 * The feature id for the '<em><b>Checklist</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHITEPAPER__CHECKLIST = CONCEPT__CHECKLIST;

	/**
	 * The feature id for the '<em><b>Concept</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHITEPAPER__CONCEPT = CONCEPT__CONCEPT;

	/**
	 * The feature id for the '<em><b>Example</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHITEPAPER__EXAMPLE = CONCEPT__EXAMPLE;

	/**
	 * The feature id for the '<em><b>Guideline</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHITEPAPER__GUIDELINE = CONCEPT__GUIDELINE;

	/**
	 * The feature id for the '<em><b>Reusable Asset</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHITEPAPER__REUSABLE_ASSET = CONCEPT__REUSABLE_ASSET;

	/**
	 * The feature id for the '<em><b>Supporting Material</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHITEPAPER__SUPPORTING_MATERIAL = CONCEPT__SUPPORTING_MATERIAL;

	/**
	 * The feature id for the '<em><b>Whitepaper</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHITEPAPER__WHITEPAPER = CONCEPT__WHITEPAPER;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHITEPAPER__VARIABILITY_BASED_ON_ELEMENT = CONCEPT__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHITEPAPER__VARIABILITY_TYPE = CONCEPT__VARIABILITY_TYPE;

	/**
	 * The number of structural features of the '<em>Whitepaper</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHITEPAPER_FEATURE_COUNT = CONCEPT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.WorkDefinitionImpl <em>Work Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.WorkDefinitionImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getWorkDefinition()
	 * @generated
	 */
	int WORK_DEFINITION = 81;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_DEFINITION__NAME = METHOD_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_DEFINITION__GROUP = METHOD_ELEMENT__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_DEFINITION__OWNED_RULE = METHOD_ELEMENT__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_DEFINITION__METHOD_ELEMENT_PROPERTY = METHOD_ELEMENT__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_DEFINITION__BRIEF_DESCRIPTION = METHOD_ELEMENT__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_DEFINITION__ID = METHOD_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_DEFINITION__ORDERING_GUIDE = METHOD_ELEMENT__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_DEFINITION__PRESENTATION_NAME = METHOD_ELEMENT__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_DEFINITION__SUPPRESSED = METHOD_ELEMENT__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Precondition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_DEFINITION__PRECONDITION = METHOD_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Postcondition</b></em>' attribute.
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
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.WorkOrderImpl <em>Work Order</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.WorkOrderImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getWorkOrder()
	 * @generated
	 */
	int WORK_ORDER = 82;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_ORDER__VALUE = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_ORDER__ID = 1;

	/**
	 * The feature id for the '<em><b>Link Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_ORDER__LINK_TYPE = 2;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_ORDER__PROPERTIES = 3;

	/**
	 * The number of structural features of the '<em>Work Order</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_ORDER_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.WorkProductDescriptorImpl <em>Work Product Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.WorkProductDescriptorImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getWorkProductDescriptor()
	 * @generated
	 */
	int WORK_PRODUCT_DESCRIPTOR = 85;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__NAME = DESCRIPTOR__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__GROUP = DESCRIPTOR__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__OWNED_RULE = DESCRIPTOR__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__METHOD_ELEMENT_PROPERTY = DESCRIPTOR__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__BRIEF_DESCRIPTION = DESCRIPTOR__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__ID = DESCRIPTOR__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__ORDERING_GUIDE = DESCRIPTOR__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__PRESENTATION_NAME = DESCRIPTOR__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__SUPPRESSED = DESCRIPTOR__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__PRESENTATION = DESCRIPTOR__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Fulfill</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__FULFILL = DESCRIPTOR__FULFILL;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__IS_ABSTRACT = DESCRIPTOR__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__NODEICON = DESCRIPTOR__NODEICON;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__SHAPEICON = DESCRIPTOR__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Presented After</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__PRESENTED_AFTER = DESCRIPTOR__PRESENTED_AFTER;

	/**
	 * The feature id for the '<em><b>Presented Before</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__PRESENTED_BEFORE = DESCRIPTOR__PRESENTED_BEFORE;

	/**
	 * The feature id for the '<em><b>Planning Data</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__PLANNING_DATA = DESCRIPTOR__PLANNING_DATA;

	/**
	 * The feature id for the '<em><b>Super Activity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__SUPER_ACTIVITY = DESCRIPTOR__SUPER_ACTIVITY;

	/**
	 * The feature id for the '<em><b>Group1</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__GROUP1 = DESCRIPTOR__GROUP1;

	/**
	 * The feature id for the '<em><b>Checklist</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__CHECKLIST = DESCRIPTOR__CHECKLIST;

	/**
	 * The feature id for the '<em><b>Concept</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__CONCEPT = DESCRIPTOR__CONCEPT;

	/**
	 * The feature id for the '<em><b>Example</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__EXAMPLE = DESCRIPTOR__EXAMPLE;

	/**
	 * The feature id for the '<em><b>Guideline</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__GUIDELINE = DESCRIPTOR__GUIDELINE;

	/**
	 * The feature id for the '<em><b>Reusable Asset</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__REUSABLE_ASSET = DESCRIPTOR__REUSABLE_ASSET;

	/**
	 * The feature id for the '<em><b>Supporting Material</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__SUPPORTING_MATERIAL = DESCRIPTOR__SUPPORTING_MATERIAL;

	/**
	 * The feature id for the '<em><b>Whitepaper</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__WHITEPAPER = DESCRIPTOR__WHITEPAPER;

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
	 * The feature id for the '<em><b>Is Planned</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__IS_PLANNED = DESCRIPTOR__IS_PLANNED;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__PREFIX = DESCRIPTOR__PREFIX;

	/**
	 * The feature id for the '<em><b>Is Synchronized With Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__IS_SYNCHRONIZED_WITH_SOURCE = DESCRIPTOR__IS_SYNCHRONIZED_WITH_SOURCE;

	/**
	 * The feature id for the '<em><b>Work Product</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__WORK_PRODUCT = DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Responsible Role</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__RESPONSIBLE_ROLE = DESCRIPTOR_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Group2</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__GROUP2 = DESCRIPTOR_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>External Input To</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__EXTERNAL_INPUT_TO = DESCRIPTOR_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Impacted By</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__IMPACTED_BY = DESCRIPTOR_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Impacts</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__IMPACTS = DESCRIPTOR_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Mandatory Input To</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__MANDATORY_INPUT_TO = DESCRIPTOR_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Optional Input To</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__OPTIONAL_INPUT_TO = DESCRIPTOR_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Output From</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__OUTPUT_FROM = DESCRIPTOR_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Deliverable Parts</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__DELIVERABLE_PARTS = DESCRIPTOR_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Activity Entry State</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__ACTIVITY_ENTRY_STATE = DESCRIPTOR_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Activity Exit State</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR__ACTIVITY_EXIT_STATE = DESCRIPTOR_FEATURE_COUNT + 11;

	/**
	 * The number of structural features of the '<em>Work Product Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_DESCRIPTOR_FEATURE_COUNT = DESCRIPTOR_FEATURE_COUNT + 12;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.impl.WorkProductTypeImpl <em>Work Product Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.impl.WorkProductTypeImpl
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getWorkProductType()
	 * @generated
	 */
	int WORK_PRODUCT_TYPE = 86;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_TYPE__NAME = CONTENT_CATEGORY__NAME;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_TYPE__GROUP = CONTENT_CATEGORY__GROUP;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_TYPE__OWNED_RULE = CONTENT_CATEGORY__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Method Element Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_TYPE__METHOD_ELEMENT_PROPERTY = CONTENT_CATEGORY__METHOD_ELEMENT_PROPERTY;

	/**
	 * The feature id for the '<em><b>Brief Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_TYPE__BRIEF_DESCRIPTION = CONTENT_CATEGORY__BRIEF_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_TYPE__ID = CONTENT_CATEGORY__ID;

	/**
	 * The feature id for the '<em><b>Ordering Guide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_TYPE__ORDERING_GUIDE = CONTENT_CATEGORY__ORDERING_GUIDE;

	/**
	 * The feature id for the '<em><b>Presentation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_TYPE__PRESENTATION_NAME = CONTENT_CATEGORY__PRESENTATION_NAME;

	/**
	 * The feature id for the '<em><b>Suppressed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_TYPE__SUPPRESSED = CONTENT_CATEGORY__SUPPRESSED;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_TYPE__PRESENTATION = CONTENT_CATEGORY__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Fulfill</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_TYPE__FULFILL = CONTENT_CATEGORY__FULFILL;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_TYPE__IS_ABSTRACT = CONTENT_CATEGORY__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Nodeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_TYPE__NODEICON = CONTENT_CATEGORY__NODEICON;

	/**
	 * The feature id for the '<em><b>Shapeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_TYPE__SHAPEICON = CONTENT_CATEGORY__SHAPEICON;

	/**
	 * The feature id for the '<em><b>Group1</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_TYPE__GROUP1 = CONTENT_CATEGORY__GROUP1;

	/**
	 * The feature id for the '<em><b>Checklist</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_TYPE__CHECKLIST = CONTENT_CATEGORY__CHECKLIST;

	/**
	 * The feature id for the '<em><b>Concept</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_TYPE__CONCEPT = CONTENT_CATEGORY__CONCEPT;

	/**
	 * The feature id for the '<em><b>Example</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_TYPE__EXAMPLE = CONTENT_CATEGORY__EXAMPLE;

	/**
	 * The feature id for the '<em><b>Guideline</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_TYPE__GUIDELINE = CONTENT_CATEGORY__GUIDELINE;

	/**
	 * The feature id for the '<em><b>Reusable Asset</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_TYPE__REUSABLE_ASSET = CONTENT_CATEGORY__REUSABLE_ASSET;

	/**
	 * The feature id for the '<em><b>Supporting Material</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_TYPE__SUPPORTING_MATERIAL = CONTENT_CATEGORY__SUPPORTING_MATERIAL;

	/**
	 * The feature id for the '<em><b>Whitepaper</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_TYPE__WHITEPAPER = CONTENT_CATEGORY__WHITEPAPER;

	/**
	 * The feature id for the '<em><b>Variability Based On Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_TYPE__VARIABILITY_BASED_ON_ELEMENT = CONTENT_CATEGORY__VARIABILITY_BASED_ON_ELEMENT;

	/**
	 * The feature id for the '<em><b>Variability Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_TYPE__VARIABILITY_TYPE = CONTENT_CATEGORY__VARIABILITY_TYPE;

	/**
	 * The feature id for the '<em><b>Group2</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_TYPE__GROUP2 = CONTENT_CATEGORY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Work Product</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_TYPE__WORK_PRODUCT = CONTENT_CATEGORY_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Work Product Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_PRODUCT_TYPE_FEATURE_COUNT = CONTENT_CATEGORY_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.VariabilityType <em>Variability Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.VariabilityType
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getVariabilityType()
	 * @generated
	 */
	int VARIABILITY_TYPE = 87;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.xml.uma.WorkOrderType <em>Work Order Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.WorkOrderType
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getWorkOrderType()
	 * @generated
	 */
	int WORK_ORDER_TYPE = 88;

	/**
	 * The meta object id for the '<em>Variability Type Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.VariabilityType
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getVariabilityTypeObject()
	 * @generated
	 */
	int VARIABILITY_TYPE_OBJECT = 89;

	/**
	 * The meta object id for the '<em>Work Order Type Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.xml.uma.WorkOrderType
	 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getWorkOrderTypeObject()
	 * @generated
	 */
	int WORK_ORDER_TYPE_OBJECT = 90;


	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.Activity <em>Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Activity</em>'.
	 * @see org.eclipse.epf.xml.uma.Activity
	 * @generated
	 */
	EClass getActivity();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.Activity#getPrecondition <em>Precondition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Precondition</em>'.
	 * @see org.eclipse.epf.xml.uma.Activity#getPrecondition()
	 * @see #getActivity()
	 * @generated
	 */
	EAttribute getActivity_Precondition();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.Activity#getPostcondition <em>Postcondition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Postcondition</em>'.
	 * @see org.eclipse.epf.xml.uma.Activity#getPostcondition()
	 * @see #getActivity()
	 * @generated
	 */
	EAttribute getActivity_Postcondition();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.Activity#getGroup3 <em>Group3</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group3</em>'.
	 * @see org.eclipse.epf.xml.uma.Activity#getGroup3()
	 * @see #getActivity()
	 * @generated
	 */
	EAttribute getActivity_Group3();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.xml.uma.Activity#getBreakdownElement <em>Breakdown Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Breakdown Element</em>'.
	 * @see org.eclipse.epf.xml.uma.Activity#getBreakdownElement()
	 * @see #getActivity()
	 * @generated
	 */
	EReference getActivity_BreakdownElement();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.Activity#getRoadmap <em>Roadmap</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Roadmap</em>'.
	 * @see org.eclipse.epf.xml.uma.Activity#getRoadmap()
	 * @see #getActivity()
	 * @generated
	 */
	EAttribute getActivity_Roadmap();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.Activity#isIsEnactable <em>Is Enactable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Enactable</em>'.
	 * @see org.eclipse.epf.xml.uma.Activity#isIsEnactable()
	 * @see #getActivity()
	 * @generated
	 */
	EAttribute getActivity_IsEnactable();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.Activity#getVariabilityBasedOnElement <em>Variability Based On Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Variability Based On Element</em>'.
	 * @see org.eclipse.epf.xml.uma.Activity#getVariabilityBasedOnElement()
	 * @see #getActivity()
	 * @generated
	 */
	EAttribute getActivity_VariabilityBasedOnElement();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.Activity#getVariabilityType <em>Variability Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Variability Type</em>'.
	 * @see org.eclipse.epf.xml.uma.Activity#getVariabilityType()
	 * @see #getActivity()
	 * @generated
	 */
	EAttribute getActivity_VariabilityType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.ActivityDescription <em>Activity Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Activity Description</em>'.
	 * @see org.eclipse.epf.xml.uma.ActivityDescription
	 * @generated
	 */
	EClass getActivityDescription();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.ActivityDescription#getAlternatives <em>Alternatives</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Alternatives</em>'.
	 * @see org.eclipse.epf.xml.uma.ActivityDescription#getAlternatives()
	 * @see #getActivityDescription()
	 * @generated
	 */
	EAttribute getActivityDescription_Alternatives();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.ActivityDescription#getHowToStaff <em>How To Staff</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>How To Staff</em>'.
	 * @see org.eclipse.epf.xml.uma.ActivityDescription#getHowToStaff()
	 * @see #getActivityDescription()
	 * @generated
	 */
	EAttribute getActivityDescription_HowToStaff();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.ActivityDescription#getPurpose <em>Purpose</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Purpose</em>'.
	 * @see org.eclipse.epf.xml.uma.ActivityDescription#getPurpose()
	 * @see #getActivityDescription()
	 * @generated
	 */
	EAttribute getActivityDescription_Purpose();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.ApplicableMetaClassInfo <em>Applicable Meta Class Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Applicable Meta Class Info</em>'.
	 * @see org.eclipse.epf.xml.uma.ApplicableMetaClassInfo
	 * @generated
	 */
	EClass getApplicableMetaClassInfo();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.ApplicableMetaClassInfo#isIsPrimaryExtension <em>Is Primary Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Primary Extension</em>'.
	 * @see org.eclipse.epf.xml.uma.ApplicableMetaClassInfo#isIsPrimaryExtension()
	 * @see #getApplicableMetaClassInfo()
	 * @generated
	 */
	EAttribute getApplicableMetaClassInfo_IsPrimaryExtension();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.Artifact <em>Artifact</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Artifact</em>'.
	 * @see org.eclipse.epf.xml.uma.Artifact
	 * @generated
	 */
	EClass getArtifact();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.Artifact#getGroup3 <em>Group3</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group3</em>'.
	 * @see org.eclipse.epf.xml.uma.Artifact#getGroup3()
	 * @see #getArtifact()
	 * @generated
	 */
	EAttribute getArtifact_Group3();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.xml.uma.Artifact#getContainedArtifact <em>Contained Artifact</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Contained Artifact</em>'.
	 * @see org.eclipse.epf.xml.uma.Artifact#getContainedArtifact()
	 * @see #getArtifact()
	 * @generated
	 */
	EReference getArtifact_ContainedArtifact();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.ArtifactDescription <em>Artifact Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Artifact Description</em>'.
	 * @see org.eclipse.epf.xml.uma.ArtifactDescription
	 * @generated
	 */
	EClass getArtifactDescription();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.ArtifactDescription#getBriefOutline <em>Brief Outline</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Brief Outline</em>'.
	 * @see org.eclipse.epf.xml.uma.ArtifactDescription#getBriefOutline()
	 * @see #getArtifactDescription()
	 * @generated
	 */
	EAttribute getArtifactDescription_BriefOutline();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.ArtifactDescription#getRepresentationOptions <em>Representation Options</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Representation Options</em>'.
	 * @see org.eclipse.epf.xml.uma.ArtifactDescription#getRepresentationOptions()
	 * @see #getArtifactDescription()
	 * @generated
	 */
	EAttribute getArtifactDescription_RepresentationOptions();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.ArtifactDescription#getRepresentation <em>Representation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Representation</em>'.
	 * @see org.eclipse.epf.xml.uma.ArtifactDescription#getRepresentation()
	 * @see #getArtifactDescription()
	 * @generated
	 */
	EAttribute getArtifactDescription_Representation();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.ArtifactDescription#getNotation <em>Notation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Notation</em>'.
	 * @see org.eclipse.epf.xml.uma.ArtifactDescription#getNotation()
	 * @see #getArtifactDescription()
	 * @generated
	 */
	EAttribute getArtifactDescription_Notation();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.BreakdownElement <em>Breakdown Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Breakdown Element</em>'.
	 * @see org.eclipse.epf.xml.uma.BreakdownElement
	 * @generated
	 */
	EClass getBreakdownElement();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.BreakdownElement#getPresentedAfter <em>Presented After</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Presented After</em>'.
	 * @see org.eclipse.epf.xml.uma.BreakdownElement#getPresentedAfter()
	 * @see #getBreakdownElement()
	 * @generated
	 */
	EAttribute getBreakdownElement_PresentedAfter();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.BreakdownElement#getPresentedBefore <em>Presented Before</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Presented Before</em>'.
	 * @see org.eclipse.epf.xml.uma.BreakdownElement#getPresentedBefore()
	 * @see #getBreakdownElement()
	 * @generated
	 */
	EAttribute getBreakdownElement_PresentedBefore();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.BreakdownElement#getPlanningData <em>Planning Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Planning Data</em>'.
	 * @see org.eclipse.epf.xml.uma.BreakdownElement#getPlanningData()
	 * @see #getBreakdownElement()
	 * @generated
	 */
	EAttribute getBreakdownElement_PlanningData();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.BreakdownElement#getSuperActivity <em>Super Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Super Activity</em>'.
	 * @see org.eclipse.epf.xml.uma.BreakdownElement#getSuperActivity()
	 * @see #getBreakdownElement()
	 * @generated
	 */
	EAttribute getBreakdownElement_SuperActivity();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.BreakdownElement#getGroup1 <em>Group1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group1</em>'.
	 * @see org.eclipse.epf.xml.uma.BreakdownElement#getGroup1()
	 * @see #getBreakdownElement()
	 * @generated
	 */
	EAttribute getBreakdownElement_Group1();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.BreakdownElement#getChecklist <em>Checklist</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Checklist</em>'.
	 * @see org.eclipse.epf.xml.uma.BreakdownElement#getChecklist()
	 * @see #getBreakdownElement()
	 * @generated
	 */
	EAttribute getBreakdownElement_Checklist();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.BreakdownElement#getConcept <em>Concept</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Concept</em>'.
	 * @see org.eclipse.epf.xml.uma.BreakdownElement#getConcept()
	 * @see #getBreakdownElement()
	 * @generated
	 */
	EAttribute getBreakdownElement_Concept();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.BreakdownElement#getExample <em>Example</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Example</em>'.
	 * @see org.eclipse.epf.xml.uma.BreakdownElement#getExample()
	 * @see #getBreakdownElement()
	 * @generated
	 */
	EAttribute getBreakdownElement_Example();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.BreakdownElement#getGuideline <em>Guideline</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Guideline</em>'.
	 * @see org.eclipse.epf.xml.uma.BreakdownElement#getGuideline()
	 * @see #getBreakdownElement()
	 * @generated
	 */
	EAttribute getBreakdownElement_Guideline();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.BreakdownElement#getReusableAsset <em>Reusable Asset</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Reusable Asset</em>'.
	 * @see org.eclipse.epf.xml.uma.BreakdownElement#getReusableAsset()
	 * @see #getBreakdownElement()
	 * @generated
	 */
	EAttribute getBreakdownElement_ReusableAsset();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.BreakdownElement#getSupportingMaterial <em>Supporting Material</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Supporting Material</em>'.
	 * @see org.eclipse.epf.xml.uma.BreakdownElement#getSupportingMaterial()
	 * @see #getBreakdownElement()
	 * @generated
	 */
	EAttribute getBreakdownElement_SupportingMaterial();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.BreakdownElement#getWhitepaper <em>Whitepaper</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Whitepaper</em>'.
	 * @see org.eclipse.epf.xml.uma.BreakdownElement#getWhitepaper()
	 * @see #getBreakdownElement()
	 * @generated
	 */
	EAttribute getBreakdownElement_Whitepaper();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.BreakdownElement#isHasMultipleOccurrences <em>Has Multiple Occurrences</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Has Multiple Occurrences</em>'.
	 * @see org.eclipse.epf.xml.uma.BreakdownElement#isHasMultipleOccurrences()
	 * @see #getBreakdownElement()
	 * @generated
	 */
	EAttribute getBreakdownElement_HasMultipleOccurrences();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.BreakdownElement#isIsOptional <em>Is Optional</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Optional</em>'.
	 * @see org.eclipse.epf.xml.uma.BreakdownElement#isIsOptional()
	 * @see #getBreakdownElement()
	 * @generated
	 */
	EAttribute getBreakdownElement_IsOptional();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.BreakdownElement#isIsPlanned <em>Is Planned</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Planned</em>'.
	 * @see org.eclipse.epf.xml.uma.BreakdownElement#isIsPlanned()
	 * @see #getBreakdownElement()
	 * @generated
	 */
	EAttribute getBreakdownElement_IsPlanned();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.BreakdownElement#getPrefix <em>Prefix</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Prefix</em>'.
	 * @see org.eclipse.epf.xml.uma.BreakdownElement#getPrefix()
	 * @see #getBreakdownElement()
	 * @generated
	 */
	EAttribute getBreakdownElement_Prefix();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.BreakdownElementDescription <em>Breakdown Element Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Breakdown Element Description</em>'.
	 * @see org.eclipse.epf.xml.uma.BreakdownElementDescription
	 * @generated
	 */
	EClass getBreakdownElementDescription();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.BreakdownElementDescription#getUsageGuidance <em>Usage Guidance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Usage Guidance</em>'.
	 * @see org.eclipse.epf.xml.uma.BreakdownElementDescription#getUsageGuidance()
	 * @see #getBreakdownElementDescription()
	 * @generated
	 */
	EAttribute getBreakdownElementDescription_UsageGuidance();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.CapabilityPattern <em>Capability Pattern</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Capability Pattern</em>'.
	 * @see org.eclipse.epf.xml.uma.CapabilityPattern
	 * @generated
	 */
	EClass getCapabilityPattern();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.Checklist <em>Checklist</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Checklist</em>'.
	 * @see org.eclipse.epf.xml.uma.Checklist
	 * @generated
	 */
	EClass getChecklist();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.CompositeRole <em>Composite Role</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Composite Role</em>'.
	 * @see org.eclipse.epf.xml.uma.CompositeRole
	 * @generated
	 */
	EClass getCompositeRole();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.CompositeRole#getGroup2 <em>Group2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group2</em>'.
	 * @see org.eclipse.epf.xml.uma.CompositeRole#getGroup2()
	 * @see #getCompositeRole()
	 * @generated
	 */
	EAttribute getCompositeRole_Group2();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.xml.uma.CompositeRole#getAggregatedRole <em>Aggregated Role</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Aggregated Role</em>'.
	 * @see org.eclipse.epf.xml.uma.CompositeRole#getAggregatedRole()
	 * @see #getCompositeRole()
	 * @generated
	 */
	EReference getCompositeRole_AggregatedRole();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.Concept <em>Concept</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Concept</em>'.
	 * @see org.eclipse.epf.xml.uma.Concept
	 * @generated
	 */
	EClass getConcept();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.Constraint <em>Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Constraint</em>'.
	 * @see org.eclipse.epf.xml.uma.Constraint
	 * @generated
	 */
	EClass getConstraint();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.Constraint#getMainDescription <em>Main Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Main Description</em>'.
	 * @see org.eclipse.epf.xml.uma.Constraint#getMainDescription()
	 * @see #getConstraint()
	 * @generated
	 */
	EAttribute getConstraint_MainDescription();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.ContentCategory <em>Content Category</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Content Category</em>'.
	 * @see org.eclipse.epf.xml.uma.ContentCategory
	 * @generated
	 */
	EClass getContentCategory();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.ContentCategoryPackage <em>Content Category Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Content Category Package</em>'.
	 * @see org.eclipse.epf.xml.uma.ContentCategoryPackage
	 * @generated
	 */
	EClass getContentCategoryPackage();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.ContentCategoryPackage#getGroup2 <em>Group2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group2</em>'.
	 * @see org.eclipse.epf.xml.uma.ContentCategoryPackage#getGroup2()
	 * @see #getContentCategoryPackage()
	 * @generated
	 */
	EAttribute getContentCategoryPackage_Group2();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.xml.uma.ContentCategoryPackage#getContentCategory <em>Content Category</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Content Category</em>'.
	 * @see org.eclipse.epf.xml.uma.ContentCategoryPackage#getContentCategory()
	 * @see #getContentCategoryPackage()
	 * @generated
	 */
	EReference getContentCategoryPackage_ContentCategory();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.ContentDescription <em>Content Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Content Description</em>'.
	 * @see org.eclipse.epf.xml.uma.ContentDescription
	 * @generated
	 */
	EClass getContentDescription();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.ContentDescription#getMainDescription <em>Main Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Main Description</em>'.
	 * @see org.eclipse.epf.xml.uma.ContentDescription#getMainDescription()
	 * @see #getContentDescription()
	 * @generated
	 */
	EAttribute getContentDescription_MainDescription();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.ContentDescription#getKeyConsiderations <em>Key Considerations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key Considerations</em>'.
	 * @see org.eclipse.epf.xml.uma.ContentDescription#getKeyConsiderations()
	 * @see #getContentDescription()
	 * @generated
	 */
	EAttribute getContentDescription_KeyConsiderations();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.xml.uma.ContentDescription#getSection <em>Section</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Section</em>'.
	 * @see org.eclipse.epf.xml.uma.ContentDescription#getSection()
	 * @see #getContentDescription()
	 * @generated
	 */
	EReference getContentDescription_Section();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.ContentDescription#getExternalId <em>External Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>External Id</em>'.
	 * @see org.eclipse.epf.xml.uma.ContentDescription#getExternalId()
	 * @see #getContentDescription()
	 * @generated
	 */
	EAttribute getContentDescription_ExternalId();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.ContentElement <em>Content Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Content Element</em>'.
	 * @see org.eclipse.epf.xml.uma.ContentElement
	 * @generated
	 */
	EClass getContentElement();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.ContentElement#getGroup1 <em>Group1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group1</em>'.
	 * @see org.eclipse.epf.xml.uma.ContentElement#getGroup1()
	 * @see #getContentElement()
	 * @generated
	 */
	EAttribute getContentElement_Group1();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.ContentElement#getChecklist <em>Checklist</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Checklist</em>'.
	 * @see org.eclipse.epf.xml.uma.ContentElement#getChecklist()
	 * @see #getContentElement()
	 * @generated
	 */
	EAttribute getContentElement_Checklist();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.ContentElement#getConcept <em>Concept</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Concept</em>'.
	 * @see org.eclipse.epf.xml.uma.ContentElement#getConcept()
	 * @see #getContentElement()
	 * @generated
	 */
	EAttribute getContentElement_Concept();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.ContentElement#getExample <em>Example</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Example</em>'.
	 * @see org.eclipse.epf.xml.uma.ContentElement#getExample()
	 * @see #getContentElement()
	 * @generated
	 */
	EAttribute getContentElement_Example();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.ContentElement#getGuideline <em>Guideline</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Guideline</em>'.
	 * @see org.eclipse.epf.xml.uma.ContentElement#getGuideline()
	 * @see #getContentElement()
	 * @generated
	 */
	EAttribute getContentElement_Guideline();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.ContentElement#getReusableAsset <em>Reusable Asset</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Reusable Asset</em>'.
	 * @see org.eclipse.epf.xml.uma.ContentElement#getReusableAsset()
	 * @see #getContentElement()
	 * @generated
	 */
	EAttribute getContentElement_ReusableAsset();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.ContentElement#getSupportingMaterial <em>Supporting Material</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Supporting Material</em>'.
	 * @see org.eclipse.epf.xml.uma.ContentElement#getSupportingMaterial()
	 * @see #getContentElement()
	 * @generated
	 */
	EAttribute getContentElement_SupportingMaterial();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.ContentElement#getWhitepaper <em>Whitepaper</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Whitepaper</em>'.
	 * @see org.eclipse.epf.xml.uma.ContentElement#getWhitepaper()
	 * @see #getContentElement()
	 * @generated
	 */
	EAttribute getContentElement_Whitepaper();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.ContentElement#getVariabilityBasedOnElement <em>Variability Based On Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Variability Based On Element</em>'.
	 * @see org.eclipse.epf.xml.uma.ContentElement#getVariabilityBasedOnElement()
	 * @see #getContentElement()
	 * @generated
	 */
	EAttribute getContentElement_VariabilityBasedOnElement();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.ContentElement#getVariabilityType <em>Variability Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Variability Type</em>'.
	 * @see org.eclipse.epf.xml.uma.ContentElement#getVariabilityType()
	 * @see #getContentElement()
	 * @generated
	 */
	EAttribute getContentElement_VariabilityType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.ContentPackage <em>Content Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Content Package</em>'.
	 * @see org.eclipse.epf.xml.uma.ContentPackage
	 * @generated
	 */
	EClass getContentPackage();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.ContentPackage#getGroup2 <em>Group2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group2</em>'.
	 * @see org.eclipse.epf.xml.uma.ContentPackage#getGroup2()
	 * @see #getContentPackage()
	 * @generated
	 */
	EAttribute getContentPackage_Group2();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.xml.uma.ContentPackage#getContentElement <em>Content Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Content Element</em>'.
	 * @see org.eclipse.epf.xml.uma.ContentPackage#getContentElement()
	 * @see #getContentPackage()
	 * @generated
	 */
	EReference getContentPackage_ContentElement();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.CustomCategory <em>Custom Category</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Custom Category</em>'.
	 * @see org.eclipse.epf.xml.uma.CustomCategory
	 * @generated
	 */
	EClass getCustomCategory();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.CustomCategory#getGroup2 <em>Group2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group2</em>'.
	 * @see org.eclipse.epf.xml.uma.CustomCategory#getGroup2()
	 * @see #getCustomCategory()
	 * @generated
	 */
	EAttribute getCustomCategory_Group2();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.CustomCategory#getCategorizedElement <em>Categorized Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Categorized Element</em>'.
	 * @see org.eclipse.epf.xml.uma.CustomCategory#getCategorizedElement()
	 * @see #getCustomCategory()
	 * @generated
	 */
	EAttribute getCustomCategory_CategorizedElement();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.CustomCategory#getSubCategory <em>Sub Category</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Sub Category</em>'.
	 * @see org.eclipse.epf.xml.uma.CustomCategory#getSubCategory()
	 * @see #getCustomCategory()
	 * @generated
	 */
	EAttribute getCustomCategory_SubCategory();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.Deliverable <em>Deliverable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Deliverable</em>'.
	 * @see org.eclipse.epf.xml.uma.Deliverable
	 * @generated
	 */
	EClass getDeliverable();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.Deliverable#getGroup3 <em>Group3</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group3</em>'.
	 * @see org.eclipse.epf.xml.uma.Deliverable#getGroup3()
	 * @see #getDeliverable()
	 * @generated
	 */
	EAttribute getDeliverable_Group3();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.Deliverable#getDeliveredWorkProduct <em>Delivered Work Product</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Delivered Work Product</em>'.
	 * @see org.eclipse.epf.xml.uma.Deliverable#getDeliveredWorkProduct()
	 * @see #getDeliverable()
	 * @generated
	 */
	EAttribute getDeliverable_DeliveredWorkProduct();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.DeliverableDescription <em>Deliverable Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Deliverable Description</em>'.
	 * @see org.eclipse.epf.xml.uma.DeliverableDescription
	 * @generated
	 */
	EClass getDeliverableDescription();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.DeliverableDescription#getExternalDescription <em>External Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>External Description</em>'.
	 * @see org.eclipse.epf.xml.uma.DeliverableDescription#getExternalDescription()
	 * @see #getDeliverableDescription()
	 * @generated
	 */
	EAttribute getDeliverableDescription_ExternalDescription();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.DeliverableDescription#getPackagingGuidance <em>Packaging Guidance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Packaging Guidance</em>'.
	 * @see org.eclipse.epf.xml.uma.DeliverableDescription#getPackagingGuidance()
	 * @see #getDeliverableDescription()
	 * @generated
	 */
	EAttribute getDeliverableDescription_PackagingGuidance();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.DeliveryProcess <em>Delivery Process</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Delivery Process</em>'.
	 * @see org.eclipse.epf.xml.uma.DeliveryProcess
	 * @generated
	 */
	EClass getDeliveryProcess();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.DeliveryProcess#getGroup4 <em>Group4</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group4</em>'.
	 * @see org.eclipse.epf.xml.uma.DeliveryProcess#getGroup4()
	 * @see #getDeliveryProcess()
	 * @generated
	 */
	EAttribute getDeliveryProcess_Group4();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.DeliveryProcess#getCommunicationsMaterial <em>Communications Material</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Communications Material</em>'.
	 * @see org.eclipse.epf.xml.uma.DeliveryProcess#getCommunicationsMaterial()
	 * @see #getDeliveryProcess()
	 * @generated
	 */
	EAttribute getDeliveryProcess_CommunicationsMaterial();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.DeliveryProcess#getEducationMaterial <em>Education Material</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Education Material</em>'.
	 * @see org.eclipse.epf.xml.uma.DeliveryProcess#getEducationMaterial()
	 * @see #getDeliveryProcess()
	 * @generated
	 */
	EAttribute getDeliveryProcess_EducationMaterial();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.DeliveryProcessDescription <em>Delivery Process Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Delivery Process Description</em>'.
	 * @see org.eclipse.epf.xml.uma.DeliveryProcessDescription
	 * @generated
	 */
	EClass getDeliveryProcessDescription();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.DeliveryProcessDescription#getScale <em>Scale</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Scale</em>'.
	 * @see org.eclipse.epf.xml.uma.DeliveryProcessDescription#getScale()
	 * @see #getDeliveryProcessDescription()
	 * @generated
	 */
	EAttribute getDeliveryProcessDescription_Scale();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.DeliveryProcessDescription#getProjectCharacteristics <em>Project Characteristics</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Project Characteristics</em>'.
	 * @see org.eclipse.epf.xml.uma.DeliveryProcessDescription#getProjectCharacteristics()
	 * @see #getDeliveryProcessDescription()
	 * @generated
	 */
	EAttribute getDeliveryProcessDescription_ProjectCharacteristics();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.DeliveryProcessDescription#getRiskLevel <em>Risk Level</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Risk Level</em>'.
	 * @see org.eclipse.epf.xml.uma.DeliveryProcessDescription#getRiskLevel()
	 * @see #getDeliveryProcessDescription()
	 * @generated
	 */
	EAttribute getDeliveryProcessDescription_RiskLevel();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.DeliveryProcessDescription#getEstimatingTechnique <em>Estimating Technique</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Estimating Technique</em>'.
	 * @see org.eclipse.epf.xml.uma.DeliveryProcessDescription#getEstimatingTechnique()
	 * @see #getDeliveryProcessDescription()
	 * @generated
	 */
	EAttribute getDeliveryProcessDescription_EstimatingTechnique();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.DeliveryProcessDescription#getProjectMemberExpertise <em>Project Member Expertise</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Project Member Expertise</em>'.
	 * @see org.eclipse.epf.xml.uma.DeliveryProcessDescription#getProjectMemberExpertise()
	 * @see #getDeliveryProcessDescription()
	 * @generated
	 */
	EAttribute getDeliveryProcessDescription_ProjectMemberExpertise();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.DeliveryProcessDescription#getTypeOfContract <em>Type Of Contract</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type Of Contract</em>'.
	 * @see org.eclipse.epf.xml.uma.DeliveryProcessDescription#getTypeOfContract()
	 * @see #getDeliveryProcessDescription()
	 * @generated
	 */
	EAttribute getDeliveryProcessDescription_TypeOfContract();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.DescribableElement <em>Describable Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Describable Element</em>'.
	 * @see org.eclipse.epf.xml.uma.DescribableElement
	 * @generated
	 */
	EClass getDescribableElement();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.epf.xml.uma.DescribableElement#getPresentation <em>Presentation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Presentation</em>'.
	 * @see org.eclipse.epf.xml.uma.DescribableElement#getPresentation()
	 * @see #getDescribableElement()
	 * @generated
	 */
	EReference getDescribableElement_Presentation();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.DescribableElement#getFulfill <em>Fulfill</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Fulfill</em>'.
	 * @see org.eclipse.epf.xml.uma.DescribableElement#getFulfill()
	 * @see #getDescribableElement()
	 * @generated
	 */
	EAttribute getDescribableElement_Fulfill();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.DescribableElement#isIsAbstract <em>Is Abstract</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Abstract</em>'.
	 * @see org.eclipse.epf.xml.uma.DescribableElement#isIsAbstract()
	 * @see #getDescribableElement()
	 * @generated
	 */
	EAttribute getDescribableElement_IsAbstract();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.DescribableElement#getNodeicon <em>Nodeicon</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Nodeicon</em>'.
	 * @see org.eclipse.epf.xml.uma.DescribableElement#getNodeicon()
	 * @see #getDescribableElement()
	 * @generated
	 */
	EAttribute getDescribableElement_Nodeicon();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.DescribableElement#getShapeicon <em>Shapeicon</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Shapeicon</em>'.
	 * @see org.eclipse.epf.xml.uma.DescribableElement#getShapeicon()
	 * @see #getDescribableElement()
	 * @generated
	 */
	EAttribute getDescribableElement_Shapeicon();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.Descriptor <em>Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Descriptor</em>'.
	 * @see org.eclipse.epf.xml.uma.Descriptor
	 * @generated
	 */
	EClass getDescriptor();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.Descriptor#isIsSynchronizedWithSource <em>Is Synchronized With Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Synchronized With Source</em>'.
	 * @see org.eclipse.epf.xml.uma.Descriptor#isIsSynchronizedWithSource()
	 * @see #getDescriptor()
	 * @generated
	 */
	EAttribute getDescriptor_IsSynchronizedWithSource();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.DescriptorDescription <em>Descriptor Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Descriptor Description</em>'.
	 * @see org.eclipse.epf.xml.uma.DescriptorDescription
	 * @generated
	 */
	EClass getDescriptorDescription();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.DescriptorDescription#getRefinedDescription <em>Refined Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Refined Description</em>'.
	 * @see org.eclipse.epf.xml.uma.DescriptorDescription#getRefinedDescription()
	 * @see #getDescriptorDescription()
	 * @generated
	 */
	EAttribute getDescriptorDescription_RefinedDescription();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.Discipline <em>Discipline</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Discipline</em>'.
	 * @see org.eclipse.epf.xml.uma.Discipline
	 * @generated
	 */
	EClass getDiscipline();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.Discipline#getGroup2 <em>Group2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group2</em>'.
	 * @see org.eclipse.epf.xml.uma.Discipline#getGroup2()
	 * @see #getDiscipline()
	 * @generated
	 */
	EAttribute getDiscipline_Group2();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.Discipline#getTask <em>Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Task</em>'.
	 * @see org.eclipse.epf.xml.uma.Discipline#getTask()
	 * @see #getDiscipline()
	 * @generated
	 */
	EAttribute getDiscipline_Task();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.xml.uma.Discipline#getSubDiscipline <em>Sub Discipline</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Sub Discipline</em>'.
	 * @see org.eclipse.epf.xml.uma.Discipline#getSubDiscipline()
	 * @see #getDiscipline()
	 * @generated
	 */
	EReference getDiscipline_SubDiscipline();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.Discipline#getReferenceWorkflow <em>Reference Workflow</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Reference Workflow</em>'.
	 * @see org.eclipse.epf.xml.uma.Discipline#getReferenceWorkflow()
	 * @see #getDiscipline()
	 * @generated
	 */
	EAttribute getDiscipline_ReferenceWorkflow();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.DisciplineGrouping <em>Discipline Grouping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Discipline Grouping</em>'.
	 * @see org.eclipse.epf.xml.uma.DisciplineGrouping
	 * @generated
	 */
	EClass getDisciplineGrouping();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.DisciplineGrouping#getGroup2 <em>Group2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group2</em>'.
	 * @see org.eclipse.epf.xml.uma.DisciplineGrouping#getGroup2()
	 * @see #getDisciplineGrouping()
	 * @generated
	 */
	EAttribute getDisciplineGrouping_Group2();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.DisciplineGrouping#getDiscipline <em>Discipline</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Discipline</em>'.
	 * @see org.eclipse.epf.xml.uma.DisciplineGrouping#getDiscipline()
	 * @see #getDisciplineGrouping()
	 * @generated
	 */
	EAttribute getDisciplineGrouping_Discipline();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.DocumentRoot <em>Document Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Document Root</em>'.
	 * @see org.eclipse.epf.xml.uma.DocumentRoot
	 * @generated
	 */
	EClass getDocumentRoot();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.DocumentRoot#getMixed <em>Mixed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Mixed</em>'.
	 * @see org.eclipse.epf.xml.uma.DocumentRoot#getMixed()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Mixed();

	/**
	 * Returns the meta object for the map '{@link org.eclipse.epf.xml.uma.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>XMLNS Prefix Map</em>'.
	 * @see org.eclipse.epf.xml.uma.DocumentRoot#getXMLNSPrefixMap()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XMLNSPrefixMap();

	/**
	 * Returns the meta object for the map '{@link org.eclipse.epf.xml.uma.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>XSI Schema Location</em>'.
	 * @see org.eclipse.epf.xml.uma.DocumentRoot#getXSISchemaLocation()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XSISchemaLocation();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.epf.xml.uma.DocumentRoot#getMethodConfiguration <em>Method Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Method Configuration</em>'.
	 * @see org.eclipse.epf.xml.uma.DocumentRoot#getMethodConfiguration()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_MethodConfiguration();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.epf.xml.uma.DocumentRoot#getMethodLibrary <em>Method Library</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Method Library</em>'.
	 * @see org.eclipse.epf.xml.uma.DocumentRoot#getMethodLibrary()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_MethodLibrary();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.epf.xml.uma.DocumentRoot#getMethodPlugin <em>Method Plugin</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Method Plugin</em>'.
	 * @see org.eclipse.epf.xml.uma.DocumentRoot#getMethodPlugin()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_MethodPlugin();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.Domain <em>Domain</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Domain</em>'.
	 * @see org.eclipse.epf.xml.uma.Domain
	 * @generated
	 */
	EClass getDomain();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.Domain#getGroup2 <em>Group2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group2</em>'.
	 * @see org.eclipse.epf.xml.uma.Domain#getGroup2()
	 * @see #getDomain()
	 * @generated
	 */
	EAttribute getDomain_Group2();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.Domain#getWorkProduct <em>Work Product</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Work Product</em>'.
	 * @see org.eclipse.epf.xml.uma.Domain#getWorkProduct()
	 * @see #getDomain()
	 * @generated
	 */
	EAttribute getDomain_WorkProduct();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.xml.uma.Domain#getSubdomain <em>Subdomain</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Subdomain</em>'.
	 * @see org.eclipse.epf.xml.uma.Domain#getSubdomain()
	 * @see #getDomain()
	 * @generated
	 */
	EReference getDomain_Subdomain();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.Element <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Element</em>'.
	 * @see org.eclipse.epf.xml.uma.Element
	 * @generated
	 */
	EClass getElement();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.Estimate <em>Estimate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Estimate</em>'.
	 * @see org.eclipse.epf.xml.uma.Estimate
	 * @generated
	 */
	EClass getEstimate();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.Estimate#getGroup2 <em>Group2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group2</em>'.
	 * @see org.eclipse.epf.xml.uma.Estimate#getGroup2()
	 * @see #getEstimate()
	 * @generated
	 */
	EAttribute getEstimate_Group2();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.Estimate#getEstimationMetric <em>Estimation Metric</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Estimation Metric</em>'.
	 * @see org.eclipse.epf.xml.uma.Estimate#getEstimationMetric()
	 * @see #getEstimate()
	 * @generated
	 */
	EAttribute getEstimate_EstimationMetric();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.Estimate#getEstimationConsiderations <em>Estimation Considerations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Estimation Considerations</em>'.
	 * @see org.eclipse.epf.xml.uma.Estimate#getEstimationConsiderations()
	 * @see #getEstimate()
	 * @generated
	 */
	EAttribute getEstimate_EstimationConsiderations();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.EstimatingMetric <em>Estimating Metric</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Estimating Metric</em>'.
	 * @see org.eclipse.epf.xml.uma.EstimatingMetric
	 * @generated
	 */
	EClass getEstimatingMetric();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.EstimationConsiderations <em>Estimation Considerations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Estimation Considerations</em>'.
	 * @see org.eclipse.epf.xml.uma.EstimationConsiderations
	 * @generated
	 */
	EClass getEstimationConsiderations();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.Example <em>Example</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Example</em>'.
	 * @see org.eclipse.epf.xml.uma.Example
	 * @generated
	 */
	EClass getExample();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.Guidance <em>Guidance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Guidance</em>'.
	 * @see org.eclipse.epf.xml.uma.Guidance
	 * @generated
	 */
	EClass getGuidance();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.GuidanceDescription <em>Guidance Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Guidance Description</em>'.
	 * @see org.eclipse.epf.xml.uma.GuidanceDescription
	 * @generated
	 */
	EClass getGuidanceDescription();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.GuidanceDescription#getAttachment <em>Attachment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attachment</em>'.
	 * @see org.eclipse.epf.xml.uma.GuidanceDescription#getAttachment()
	 * @see #getGuidanceDescription()
	 * @generated
	 */
	EAttribute getGuidanceDescription_Attachment();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.Guideline <em>Guideline</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Guideline</em>'.
	 * @see org.eclipse.epf.xml.uma.Guideline
	 * @generated
	 */
	EClass getGuideline();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.Iteration <em>Iteration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Iteration</em>'.
	 * @see org.eclipse.epf.xml.uma.Iteration
	 * @generated
	 */
	EClass getIteration();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.Kind <em>Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Kind</em>'.
	 * @see org.eclipse.epf.xml.uma.Kind
	 * @generated
	 */
	EClass getKind();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.Kind#getApplicableMetaClassInfo <em>Applicable Meta Class Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Applicable Meta Class Info</em>'.
	 * @see org.eclipse.epf.xml.uma.Kind#getApplicableMetaClassInfo()
	 * @see #getKind()
	 * @generated
	 */
	EAttribute getKind_ApplicableMetaClassInfo();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.MethodConfiguration <em>Method Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Method Configuration</em>'.
	 * @see org.eclipse.epf.xml.uma.MethodConfiguration
	 * @generated
	 */
	EClass getMethodConfiguration();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.MethodConfiguration#getBaseConfiguration <em>Base Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Base Configuration</em>'.
	 * @see org.eclipse.epf.xml.uma.MethodConfiguration#getBaseConfiguration()
	 * @see #getMethodConfiguration()
	 * @generated
	 */
	EAttribute getMethodConfiguration_BaseConfiguration();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.MethodConfiguration#getMethodPluginSelection <em>Method Plugin Selection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Method Plugin Selection</em>'.
	 * @see org.eclipse.epf.xml.uma.MethodConfiguration#getMethodPluginSelection()
	 * @see #getMethodConfiguration()
	 * @generated
	 */
	EAttribute getMethodConfiguration_MethodPluginSelection();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.MethodConfiguration#getMethodPackageSelection <em>Method Package Selection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Method Package Selection</em>'.
	 * @see org.eclipse.epf.xml.uma.MethodConfiguration#getMethodPackageSelection()
	 * @see #getMethodConfiguration()
	 * @generated
	 */
	EAttribute getMethodConfiguration_MethodPackageSelection();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.MethodConfiguration#getDefaultView <em>Default View</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default View</em>'.
	 * @see org.eclipse.epf.xml.uma.MethodConfiguration#getDefaultView()
	 * @see #getMethodConfiguration()
	 * @generated
	 */
	EAttribute getMethodConfiguration_DefaultView();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.MethodConfiguration#getProcessView <em>Process View</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Process View</em>'.
	 * @see org.eclipse.epf.xml.uma.MethodConfiguration#getProcessView()
	 * @see #getMethodConfiguration()
	 * @generated
	 */
	EAttribute getMethodConfiguration_ProcessView();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.MethodConfiguration#getSubtractedCategory <em>Subtracted Category</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Subtracted Category</em>'.
	 * @see org.eclipse.epf.xml.uma.MethodConfiguration#getSubtractedCategory()
	 * @see #getMethodConfiguration()
	 * @generated
	 */
	EAttribute getMethodConfiguration_SubtractedCategory();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.MethodConfiguration#getAddedCategory <em>Added Category</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Added Category</em>'.
	 * @see org.eclipse.epf.xml.uma.MethodConfiguration#getAddedCategory()
	 * @see #getMethodConfiguration()
	 * @generated
	 */
	EAttribute getMethodConfiguration_AddedCategory();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.MethodElement <em>Method Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Method Element</em>'.
	 * @see org.eclipse.epf.xml.uma.MethodElement
	 * @generated
	 */
	EClass getMethodElement();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.MethodElement#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.eclipse.epf.xml.uma.MethodElement#getGroup()
	 * @see #getMethodElement()
	 * @generated
	 */
	EAttribute getMethodElement_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.xml.uma.MethodElement#getOwnedRule <em>Owned Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Rule</em>'.
	 * @see org.eclipse.epf.xml.uma.MethodElement#getOwnedRule()
	 * @see #getMethodElement()
	 * @generated
	 */
	EReference getMethodElement_OwnedRule();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.xml.uma.MethodElement#getMethodElementProperty <em>Method Element Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Method Element Property</em>'.
	 * @see org.eclipse.epf.xml.uma.MethodElement#getMethodElementProperty()
	 * @see #getMethodElement()
	 * @generated
	 */
	EReference getMethodElement_MethodElementProperty();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.MethodElement#getBriefDescription <em>Brief Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Brief Description</em>'.
	 * @see org.eclipse.epf.xml.uma.MethodElement#getBriefDescription()
	 * @see #getMethodElement()
	 * @generated
	 */
	EAttribute getMethodElement_BriefDescription();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.MethodElement#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.epf.xml.uma.MethodElement#getId()
	 * @see #getMethodElement()
	 * @generated
	 */
	EAttribute getMethodElement_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.MethodElement#getOrderingGuide <em>Ordering Guide</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ordering Guide</em>'.
	 * @see org.eclipse.epf.xml.uma.MethodElement#getOrderingGuide()
	 * @see #getMethodElement()
	 * @generated
	 */
	EAttribute getMethodElement_OrderingGuide();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.MethodElement#getPresentationName <em>Presentation Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Presentation Name</em>'.
	 * @see org.eclipse.epf.xml.uma.MethodElement#getPresentationName()
	 * @see #getMethodElement()
	 * @generated
	 */
	EAttribute getMethodElement_PresentationName();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.MethodElement#isSuppressed <em>Suppressed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Suppressed</em>'.
	 * @see org.eclipse.epf.xml.uma.MethodElement#isSuppressed()
	 * @see #getMethodElement()
	 * @generated
	 */
	EAttribute getMethodElement_Suppressed();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.MethodElementProperty <em>Method Element Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Method Element Property</em>'.
	 * @see org.eclipse.epf.xml.uma.MethodElementProperty
	 * @generated
	 */
	EClass getMethodElementProperty();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.MethodElementProperty#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.epf.xml.uma.MethodElementProperty#getValue()
	 * @see #getMethodElementProperty()
	 * @generated
	 */
	EAttribute getMethodElementProperty_Value();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.MethodLibrary <em>Method Library</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Method Library</em>'.
	 * @see org.eclipse.epf.xml.uma.MethodLibrary
	 * @generated
	 */
	EClass getMethodLibrary();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.xml.uma.MethodLibrary#getMethodPlugin <em>Method Plugin</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Method Plugin</em>'.
	 * @see org.eclipse.epf.xml.uma.MethodLibrary#getMethodPlugin()
	 * @see #getMethodLibrary()
	 * @generated
	 */
	EReference getMethodLibrary_MethodPlugin();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.xml.uma.MethodLibrary#getMethodConfiguration <em>Method Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Method Configuration</em>'.
	 * @see org.eclipse.epf.xml.uma.MethodLibrary#getMethodConfiguration()
	 * @see #getMethodLibrary()
	 * @generated
	 */
	EReference getMethodLibrary_MethodConfiguration();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.MethodLibrary#getTool <em>Tool</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Tool</em>'.
	 * @see org.eclipse.epf.xml.uma.MethodLibrary#getTool()
	 * @see #getMethodLibrary()
	 * @generated
	 */
	EAttribute getMethodLibrary_Tool();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.MethodPackage <em>Method Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Method Package</em>'.
	 * @see org.eclipse.epf.xml.uma.MethodPackage
	 * @generated
	 */
	EClass getMethodPackage();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.MethodPackage#getGroup1 <em>Group1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group1</em>'.
	 * @see org.eclipse.epf.xml.uma.MethodPackage#getGroup1()
	 * @see #getMethodPackage()
	 * @generated
	 */
	EAttribute getMethodPackage_Group1();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.MethodPackage#getReusedPackage <em>Reused Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Reused Package</em>'.
	 * @see org.eclipse.epf.xml.uma.MethodPackage#getReusedPackage()
	 * @see #getMethodPackage()
	 * @generated
	 */
	EAttribute getMethodPackage_ReusedPackage();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.xml.uma.MethodPackage#getMethodPackage <em>Method Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Method Package</em>'.
	 * @see org.eclipse.epf.xml.uma.MethodPackage#getMethodPackage()
	 * @see #getMethodPackage()
	 * @generated
	 */
	EReference getMethodPackage_MethodPackage();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.MethodPackage#isGlobal <em>Global</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Global</em>'.
	 * @see org.eclipse.epf.xml.uma.MethodPackage#isGlobal()
	 * @see #getMethodPackage()
	 * @generated
	 */
	EAttribute getMethodPackage_Global();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.MethodPlugin <em>Method Plugin</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Method Plugin</em>'.
	 * @see org.eclipse.epf.xml.uma.MethodPlugin
	 * @generated
	 */
	EClass getMethodPlugin();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.MethodPlugin#getReferencedMethodPlugin <em>Referenced Method Plugin</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Referenced Method Plugin</em>'.
	 * @see org.eclipse.epf.xml.uma.MethodPlugin#getReferencedMethodPlugin()
	 * @see #getMethodPlugin()
	 * @generated
	 */
	EAttribute getMethodPlugin_ReferencedMethodPlugin();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.xml.uma.MethodPlugin#getMethodPackage <em>Method Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Method Package</em>'.
	 * @see org.eclipse.epf.xml.uma.MethodPlugin#getMethodPackage()
	 * @see #getMethodPlugin()
	 * @generated
	 */
	EReference getMethodPlugin_MethodPackage();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.MethodPlugin#isSupporting <em>Supporting</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Supporting</em>'.
	 * @see org.eclipse.epf.xml.uma.MethodPlugin#isSupporting()
	 * @see #getMethodPlugin()
	 * @generated
	 */
	EAttribute getMethodPlugin_Supporting();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.MethodPlugin#isUserChangeable <em>User Changeable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>User Changeable</em>'.
	 * @see org.eclipse.epf.xml.uma.MethodPlugin#isUserChangeable()
	 * @see #getMethodPlugin()
	 * @generated
	 */
	EAttribute getMethodPlugin_UserChangeable();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.MethodUnit <em>Method Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Method Unit</em>'.
	 * @see org.eclipse.epf.xml.uma.MethodUnit
	 * @generated
	 */
	EClass getMethodUnit();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.MethodUnit#getCopyright <em>Copyright</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Copyright</em>'.
	 * @see org.eclipse.epf.xml.uma.MethodUnit#getCopyright()
	 * @see #getMethodUnit()
	 * @generated
	 */
	EAttribute getMethodUnit_Copyright();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.MethodUnit#getAuthors <em>Authors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Authors</em>'.
	 * @see org.eclipse.epf.xml.uma.MethodUnit#getAuthors()
	 * @see #getMethodUnit()
	 * @generated
	 */
	EAttribute getMethodUnit_Authors();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.MethodUnit#getChangeDate <em>Change Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Change Date</em>'.
	 * @see org.eclipse.epf.xml.uma.MethodUnit#getChangeDate()
	 * @see #getMethodUnit()
	 * @generated
	 */
	EAttribute getMethodUnit_ChangeDate();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.MethodUnit#getChangeDescription <em>Change Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Change Description</em>'.
	 * @see org.eclipse.epf.xml.uma.MethodUnit#getChangeDescription()
	 * @see #getMethodUnit()
	 * @generated
	 */
	EAttribute getMethodUnit_ChangeDescription();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.MethodUnit#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see org.eclipse.epf.xml.uma.MethodUnit#getVersion()
	 * @see #getMethodUnit()
	 * @generated
	 */
	EAttribute getMethodUnit_Version();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.Milestone <em>Milestone</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Milestone</em>'.
	 * @see org.eclipse.epf.xml.uma.Milestone
	 * @generated
	 */
	EClass getMilestone();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.Milestone#getRequiredResult <em>Required Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Required Result</em>'.
	 * @see org.eclipse.epf.xml.uma.Milestone#getRequiredResult()
	 * @see #getMilestone()
	 * @generated
	 */
	EAttribute getMilestone_RequiredResult();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.NamedElement <em>Named Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Named Element</em>'.
	 * @see org.eclipse.epf.xml.uma.NamedElement
	 * @generated
	 */
	EClass getNamedElement();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.NamedElement#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.epf.xml.uma.NamedElement#getName()
	 * @see #getNamedElement()
	 * @generated
	 */
	EAttribute getNamedElement_Name();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.Outcome <em>Outcome</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Outcome</em>'.
	 * @see org.eclipse.epf.xml.uma.Outcome
	 * @generated
	 */
	EClass getOutcome();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.PackageableElement <em>Packageable Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Packageable Element</em>'.
	 * @see org.eclipse.epf.xml.uma.PackageableElement
	 * @generated
	 */
	EClass getPackageableElement();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.Phase <em>Phase</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Phase</em>'.
	 * @see org.eclipse.epf.xml.uma.Phase
	 * @generated
	 */
	EClass getPhase();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.PlanningData <em>Planning Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Planning Data</em>'.
	 * @see org.eclipse.epf.xml.uma.PlanningData
	 * @generated
	 */
	EClass getPlanningData();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.PlanningData#getFinishDate <em>Finish Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Finish Date</em>'.
	 * @see org.eclipse.epf.xml.uma.PlanningData#getFinishDate()
	 * @see #getPlanningData()
	 * @generated
	 */
	EAttribute getPlanningData_FinishDate();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.PlanningData#getRank <em>Rank</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rank</em>'.
	 * @see org.eclipse.epf.xml.uma.PlanningData#getRank()
	 * @see #getPlanningData()
	 * @generated
	 */
	EAttribute getPlanningData_Rank();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.PlanningData#getStartDate <em>Start Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Start Date</em>'.
	 * @see org.eclipse.epf.xml.uma.PlanningData#getStartDate()
	 * @see #getPlanningData()
	 * @generated
	 */
	EAttribute getPlanningData_StartDate();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.Practice <em>Practice</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Practice</em>'.
	 * @see org.eclipse.epf.xml.uma.Practice
	 * @generated
	 */
	EClass getPractice();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.Practice#getGroup2 <em>Group2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group2</em>'.
	 * @see org.eclipse.epf.xml.uma.Practice#getGroup2()
	 * @see #getPractice()
	 * @generated
	 */
	EAttribute getPractice_Group2();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.Practice#getActivityReference <em>Activity Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Activity Reference</em>'.
	 * @see org.eclipse.epf.xml.uma.Practice#getActivityReference()
	 * @see #getPractice()
	 * @generated
	 */
	EAttribute getPractice_ActivityReference();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.Practice#getContentReference <em>Content Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Content Reference</em>'.
	 * @see org.eclipse.epf.xml.uma.Practice#getContentReference()
	 * @see #getPractice()
	 * @generated
	 */
	EAttribute getPractice_ContentReference();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.xml.uma.Practice#getSubPractice <em>Sub Practice</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Sub Practice</em>'.
	 * @see org.eclipse.epf.xml.uma.Practice#getSubPractice()
	 * @see #getPractice()
	 * @generated
	 */
	EReference getPractice_SubPractice();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.PracticeDescription <em>Practice Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Practice Description</em>'.
	 * @see org.eclipse.epf.xml.uma.PracticeDescription
	 * @generated
	 */
	EClass getPracticeDescription();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.PracticeDescription#getAdditionalInfo <em>Additional Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Additional Info</em>'.
	 * @see org.eclipse.epf.xml.uma.PracticeDescription#getAdditionalInfo()
	 * @see #getPracticeDescription()
	 * @generated
	 */
	EAttribute getPracticeDescription_AdditionalInfo();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.PracticeDescription#getApplication <em>Application</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Application</em>'.
	 * @see org.eclipse.epf.xml.uma.PracticeDescription#getApplication()
	 * @see #getPracticeDescription()
	 * @generated
	 */
	EAttribute getPracticeDescription_Application();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.PracticeDescription#getBackground <em>Background</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Background</em>'.
	 * @see org.eclipse.epf.xml.uma.PracticeDescription#getBackground()
	 * @see #getPracticeDescription()
	 * @generated
	 */
	EAttribute getPracticeDescription_Background();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.PracticeDescription#getGoals <em>Goals</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Goals</em>'.
	 * @see org.eclipse.epf.xml.uma.PracticeDescription#getGoals()
	 * @see #getPracticeDescription()
	 * @generated
	 */
	EAttribute getPracticeDescription_Goals();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.PracticeDescription#getLevelsOfAdoption <em>Levels Of Adoption</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Levels Of Adoption</em>'.
	 * @see org.eclipse.epf.xml.uma.PracticeDescription#getLevelsOfAdoption()
	 * @see #getPracticeDescription()
	 * @generated
	 */
	EAttribute getPracticeDescription_LevelsOfAdoption();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.PracticeDescription#getProblem <em>Problem</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Problem</em>'.
	 * @see org.eclipse.epf.xml.uma.PracticeDescription#getProblem()
	 * @see #getPracticeDescription()
	 * @generated
	 */
	EAttribute getPracticeDescription_Problem();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.Process <em>Process</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Process</em>'.
	 * @see org.eclipse.epf.xml.uma.Process
	 * @generated
	 */
	EClass getProcess();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.Process#getIncludesPattern <em>Includes Pattern</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Includes Pattern</em>'.
	 * @see org.eclipse.epf.xml.uma.Process#getIncludesPattern()
	 * @see #getProcess()
	 * @generated
	 */
	EAttribute getProcess_IncludesPattern();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.Process#getDefaultContext <em>Default Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default Context</em>'.
	 * @see org.eclipse.epf.xml.uma.Process#getDefaultContext()
	 * @see #getProcess()
	 * @generated
	 */
	EAttribute getProcess_DefaultContext();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.Process#getValidContext <em>Valid Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Valid Context</em>'.
	 * @see org.eclipse.epf.xml.uma.Process#getValidContext()
	 * @see #getProcess()
	 * @generated
	 */
	EAttribute getProcess_ValidContext();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.Process#getDiagramURI <em>Diagram URI</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Diagram URI</em>'.
	 * @see org.eclipse.epf.xml.uma.Process#getDiagramURI()
	 * @see #getProcess()
	 * @generated
	 */
	EAttribute getProcess_DiagramURI();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.ProcessComponent <em>Process Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Process Component</em>'.
	 * @see org.eclipse.epf.xml.uma.ProcessComponent
	 * @generated
	 */
	EClass getProcessComponent();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.ProcessComponent#getCopyright <em>Copyright</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Copyright</em>'.
	 * @see org.eclipse.epf.xml.uma.ProcessComponent#getCopyright()
	 * @see #getProcessComponent()
	 * @generated
	 */
	EAttribute getProcessComponent_Copyright();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.epf.xml.uma.ProcessComponent#getInterface <em>Interface</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Interface</em>'.
	 * @see org.eclipse.epf.xml.uma.ProcessComponent#getInterface()
	 * @see #getProcessComponent()
	 * @generated
	 */
	EReference getProcessComponent_Interface();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.epf.xml.uma.ProcessComponent#getProcess <em>Process</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Process</em>'.
	 * @see org.eclipse.epf.xml.uma.ProcessComponent#getProcess()
	 * @see #getProcessComponent()
	 * @generated
	 */
	EReference getProcessComponent_Process();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.ProcessComponent#getAuthors <em>Authors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Authors</em>'.
	 * @see org.eclipse.epf.xml.uma.ProcessComponent#getAuthors()
	 * @see #getProcessComponent()
	 * @generated
	 */
	EAttribute getProcessComponent_Authors();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.ProcessComponent#getChangeDate <em>Change Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Change Date</em>'.
	 * @see org.eclipse.epf.xml.uma.ProcessComponent#getChangeDate()
	 * @see #getProcessComponent()
	 * @generated
	 */
	EAttribute getProcessComponent_ChangeDate();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.ProcessComponent#getChangeDescription <em>Change Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Change Description</em>'.
	 * @see org.eclipse.epf.xml.uma.ProcessComponent#getChangeDescription()
	 * @see #getProcessComponent()
	 * @generated
	 */
	EAttribute getProcessComponent_ChangeDescription();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.ProcessComponent#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see org.eclipse.epf.xml.uma.ProcessComponent#getVersion()
	 * @see #getProcessComponent()
	 * @generated
	 */
	EAttribute getProcessComponent_Version();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.ProcessComponentInterface <em>Process Component Interface</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Process Component Interface</em>'.
	 * @see org.eclipse.epf.xml.uma.ProcessComponentInterface
	 * @generated
	 */
	EClass getProcessComponentInterface();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.ProcessComponentInterface#getGroup2 <em>Group2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group2</em>'.
	 * @see org.eclipse.epf.xml.uma.ProcessComponentInterface#getGroup2()
	 * @see #getProcessComponentInterface()
	 * @generated
	 */
	EAttribute getProcessComponentInterface_Group2();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.xml.uma.ProcessComponentInterface#getInterfaceSpecification <em>Interface Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Interface Specification</em>'.
	 * @see org.eclipse.epf.xml.uma.ProcessComponentInterface#getInterfaceSpecification()
	 * @see #getProcessComponentInterface()
	 * @generated
	 */
	EReference getProcessComponentInterface_InterfaceSpecification();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.xml.uma.ProcessComponentInterface#getInterfaceIO <em>Interface IO</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Interface IO</em>'.
	 * @see org.eclipse.epf.xml.uma.ProcessComponentInterface#getInterfaceIO()
	 * @see #getProcessComponentInterface()
	 * @generated
	 */
	EReference getProcessComponentInterface_InterfaceIO();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.ProcessDescription <em>Process Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Process Description</em>'.
	 * @see org.eclipse.epf.xml.uma.ProcessDescription
	 * @generated
	 */
	EClass getProcessDescription();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.ProcessDescription#getScope <em>Scope</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Scope</em>'.
	 * @see org.eclipse.epf.xml.uma.ProcessDescription#getScope()
	 * @see #getProcessDescription()
	 * @generated
	 */
	EAttribute getProcessDescription_Scope();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.ProcessDescription#getUsageNotes <em>Usage Notes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Usage Notes</em>'.
	 * @see org.eclipse.epf.xml.uma.ProcessDescription#getUsageNotes()
	 * @see #getProcessDescription()
	 * @generated
	 */
	EAttribute getProcessDescription_UsageNotes();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.ProcessElement <em>Process Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Process Element</em>'.
	 * @see org.eclipse.epf.xml.uma.ProcessElement
	 * @generated
	 */
	EClass getProcessElement();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.ProcessPackage <em>Process Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Process Package</em>'.
	 * @see org.eclipse.epf.xml.uma.ProcessPackage
	 * @generated
	 */
	EClass getProcessPackage();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.ProcessPackage#getGroup2 <em>Group2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group2</em>'.
	 * @see org.eclipse.epf.xml.uma.ProcessPackage#getGroup2()
	 * @see #getProcessPackage()
	 * @generated
	 */
	EAttribute getProcessPackage_Group2();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.xml.uma.ProcessPackage#getProcessElement <em>Process Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Process Element</em>'.
	 * @see org.eclipse.epf.xml.uma.ProcessPackage#getProcessElement()
	 * @see #getProcessPackage()
	 * @generated
	 */
	EReference getProcessPackage_ProcessElement();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.ProcessPlanningTemplate <em>Process Planning Template</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Process Planning Template</em>'.
	 * @see org.eclipse.epf.xml.uma.ProcessPlanningTemplate
	 * @generated
	 */
	EClass getProcessPlanningTemplate();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.ProcessPlanningTemplate#getGroup4 <em>Group4</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group4</em>'.
	 * @see org.eclipse.epf.xml.uma.ProcessPlanningTemplate#getGroup4()
	 * @see #getProcessPlanningTemplate()
	 * @generated
	 */
	EAttribute getProcessPlanningTemplate_Group4();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.ProcessPlanningTemplate#getBaseProcess <em>Base Process</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Base Process</em>'.
	 * @see org.eclipse.epf.xml.uma.ProcessPlanningTemplate#getBaseProcess()
	 * @see #getProcessPlanningTemplate()
	 * @generated
	 */
	EAttribute getProcessPlanningTemplate_BaseProcess();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.Report <em>Report</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Report</em>'.
	 * @see org.eclipse.epf.xml.uma.Report
	 * @generated
	 */
	EClass getReport();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.ReusableAsset <em>Reusable Asset</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Reusable Asset</em>'.
	 * @see org.eclipse.epf.xml.uma.ReusableAsset
	 * @generated
	 */
	EClass getReusableAsset();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.Roadmap <em>Roadmap</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Roadmap</em>'.
	 * @see org.eclipse.epf.xml.uma.Roadmap
	 * @generated
	 */
	EClass getRoadmap();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.Role <em>Role</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Role</em>'.
	 * @see org.eclipse.epf.xml.uma.Role
	 * @generated
	 */
	EClass getRole();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.Role#getGroup2 <em>Group2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group2</em>'.
	 * @see org.eclipse.epf.xml.uma.Role#getGroup2()
	 * @see #getRole()
	 * @generated
	 */
	EAttribute getRole_Group2();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.Role#getResponsibleFor <em>Responsible For</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Responsible For</em>'.
	 * @see org.eclipse.epf.xml.uma.Role#getResponsibleFor()
	 * @see #getRole()
	 * @generated
	 */
	EAttribute getRole_ResponsibleFor();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.RoleDescription <em>Role Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Role Description</em>'.
	 * @see org.eclipse.epf.xml.uma.RoleDescription
	 * @generated
	 */
	EClass getRoleDescription();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.RoleDescription#getAssignmentApproaches <em>Assignment Approaches</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Assignment Approaches</em>'.
	 * @see org.eclipse.epf.xml.uma.RoleDescription#getAssignmentApproaches()
	 * @see #getRoleDescription()
	 * @generated
	 */
	EAttribute getRoleDescription_AssignmentApproaches();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.RoleDescription#getSkills <em>Skills</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Skills</em>'.
	 * @see org.eclipse.epf.xml.uma.RoleDescription#getSkills()
	 * @see #getRoleDescription()
	 * @generated
	 */
	EAttribute getRoleDescription_Skills();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.RoleDescription#getSynonyms <em>Synonyms</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Synonyms</em>'.
	 * @see org.eclipse.epf.xml.uma.RoleDescription#getSynonyms()
	 * @see #getRoleDescription()
	 * @generated
	 */
	EAttribute getRoleDescription_Synonyms();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.RoleDescriptor <em>Role Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Role Descriptor</em>'.
	 * @see org.eclipse.epf.xml.uma.RoleDescriptor
	 * @generated
	 */
	EClass getRoleDescriptor();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.RoleDescriptor#getRole <em>Role</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Role</em>'.
	 * @see org.eclipse.epf.xml.uma.RoleDescriptor#getRole()
	 * @see #getRoleDescriptor()
	 * @generated
	 */
	EAttribute getRoleDescriptor_Role();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.RoleDescriptor#getResponsibleFor <em>Responsible For</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Responsible For</em>'.
	 * @see org.eclipse.epf.xml.uma.RoleDescriptor#getResponsibleFor()
	 * @see #getRoleDescriptor()
	 * @generated
	 */
	EAttribute getRoleDescriptor_ResponsibleFor();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.RoleSet <em>Role Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Role Set</em>'.
	 * @see org.eclipse.epf.xml.uma.RoleSet
	 * @generated
	 */
	EClass getRoleSet();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.RoleSet#getGroup2 <em>Group2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group2</em>'.
	 * @see org.eclipse.epf.xml.uma.RoleSet#getGroup2()
	 * @see #getRoleSet()
	 * @generated
	 */
	EAttribute getRoleSet_Group2();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.RoleSet#getRole <em>Role</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Role</em>'.
	 * @see org.eclipse.epf.xml.uma.RoleSet#getRole()
	 * @see #getRoleSet()
	 * @generated
	 */
	EAttribute getRoleSet_Role();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.RoleSetGrouping <em>Role Set Grouping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Role Set Grouping</em>'.
	 * @see org.eclipse.epf.xml.uma.RoleSetGrouping
	 * @generated
	 */
	EClass getRoleSetGrouping();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.RoleSetGrouping#getGroup2 <em>Group2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group2</em>'.
	 * @see org.eclipse.epf.xml.uma.RoleSetGrouping#getGroup2()
	 * @see #getRoleSetGrouping()
	 * @generated
	 */
	EAttribute getRoleSetGrouping_Group2();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.RoleSetGrouping#getRoleSet <em>Role Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Role Set</em>'.
	 * @see org.eclipse.epf.xml.uma.RoleSetGrouping#getRoleSet()
	 * @see #getRoleSetGrouping()
	 * @generated
	 */
	EAttribute getRoleSetGrouping_RoleSet();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.Section <em>Section</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Section</em>'.
	 * @see org.eclipse.epf.xml.uma.Section
	 * @generated
	 */
	EClass getSection();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.epf.xml.uma.Section#getSubSection <em>Sub Section</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Sub Section</em>'.
	 * @see org.eclipse.epf.xml.uma.Section#getSubSection()
	 * @see #getSection()
	 * @generated
	 */
	EReference getSection_SubSection();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.Section#getPredecessor <em>Predecessor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Predecessor</em>'.
	 * @see org.eclipse.epf.xml.uma.Section#getPredecessor()
	 * @see #getSection()
	 * @generated
	 */
	EAttribute getSection_Predecessor();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.Section#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.eclipse.epf.xml.uma.Section#getDescription()
	 * @see #getSection()
	 * @generated
	 */
	EAttribute getSection_Description();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.Section#getSectionName <em>Section Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Section Name</em>'.
	 * @see org.eclipse.epf.xml.uma.Section#getSectionName()
	 * @see #getSection()
	 * @generated
	 */
	EAttribute getSection_SectionName();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.Section#getVariabilityBasedOnElement <em>Variability Based On Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Variability Based On Element</em>'.
	 * @see org.eclipse.epf.xml.uma.Section#getVariabilityBasedOnElement()
	 * @see #getSection()
	 * @generated
	 */
	EAttribute getSection_VariabilityBasedOnElement();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.Section#getVariabilityType <em>Variability Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Variability Type</em>'.
	 * @see org.eclipse.epf.xml.uma.Section#getVariabilityType()
	 * @see #getSection()
	 * @generated
	 */
	EAttribute getSection_VariabilityType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.SupportingMaterial <em>Supporting Material</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Supporting Material</em>'.
	 * @see org.eclipse.epf.xml.uma.SupportingMaterial
	 * @generated
	 */
	EClass getSupportingMaterial();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.Task <em>Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Task</em>'.
	 * @see org.eclipse.epf.xml.uma.Task
	 * @generated
	 */
	EClass getTask();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.Task#getPrecondition <em>Precondition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Precondition</em>'.
	 * @see org.eclipse.epf.xml.uma.Task#getPrecondition()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_Precondition();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.Task#getPostcondition <em>Postcondition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Postcondition</em>'.
	 * @see org.eclipse.epf.xml.uma.Task#getPostcondition()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_Postcondition();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.Task#getPerformedBy <em>Performed By</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Performed By</em>'.
	 * @see org.eclipse.epf.xml.uma.Task#getPerformedBy()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_PerformedBy();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.Task#getGroup2 <em>Group2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group2</em>'.
	 * @see org.eclipse.epf.xml.uma.Task#getGroup2()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_Group2();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.Task#getMandatoryInput <em>Mandatory Input</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Mandatory Input</em>'.
	 * @see org.eclipse.epf.xml.uma.Task#getMandatoryInput()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_MandatoryInput();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.Task#getOutput <em>Output</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Output</em>'.
	 * @see org.eclipse.epf.xml.uma.Task#getOutput()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_Output();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.Task#getAdditionallyPerformedBy <em>Additionally Performed By</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Additionally Performed By</em>'.
	 * @see org.eclipse.epf.xml.uma.Task#getAdditionallyPerformedBy()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_AdditionallyPerformedBy();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.Task#getOptionalInput <em>Optional Input</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Optional Input</em>'.
	 * @see org.eclipse.epf.xml.uma.Task#getOptionalInput()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_OptionalInput();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.Task#getEstimate <em>Estimate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Estimate</em>'.
	 * @see org.eclipse.epf.xml.uma.Task#getEstimate()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_Estimate();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.Task#getEstimationConsiderations <em>Estimation Considerations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Estimation Considerations</em>'.
	 * @see org.eclipse.epf.xml.uma.Task#getEstimationConsiderations()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_EstimationConsiderations();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.Task#getToolMentor <em>Tool Mentor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Tool Mentor</em>'.
	 * @see org.eclipse.epf.xml.uma.Task#getToolMentor()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_ToolMentor();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.TaskDescription <em>Task Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Task Description</em>'.
	 * @see org.eclipse.epf.xml.uma.TaskDescription
	 * @generated
	 */
	EClass getTaskDescription();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.TaskDescription#getAlternatives <em>Alternatives</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Alternatives</em>'.
	 * @see org.eclipse.epf.xml.uma.TaskDescription#getAlternatives()
	 * @see #getTaskDescription()
	 * @generated
	 */
	EAttribute getTaskDescription_Alternatives();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.TaskDescription#getPurpose <em>Purpose</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Purpose</em>'.
	 * @see org.eclipse.epf.xml.uma.TaskDescription#getPurpose()
	 * @see #getTaskDescription()
	 * @generated
	 */
	EAttribute getTaskDescription_Purpose();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.TaskDescriptor <em>Task Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Task Descriptor</em>'.
	 * @see org.eclipse.epf.xml.uma.TaskDescriptor
	 * @generated
	 */
	EClass getTaskDescriptor();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.TaskDescriptor#getTask <em>Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Task</em>'.
	 * @see org.eclipse.epf.xml.uma.TaskDescriptor#getTask()
	 * @see #getTaskDescriptor()
	 * @generated
	 */
	EAttribute getTaskDescriptor_Task();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.TaskDescriptor#getPerformedPrimarilyBy <em>Performed Primarily By</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Performed Primarily By</em>'.
	 * @see org.eclipse.epf.xml.uma.TaskDescriptor#getPerformedPrimarilyBy()
	 * @see #getTaskDescriptor()
	 * @generated
	 */
	EAttribute getTaskDescriptor_PerformedPrimarilyBy();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.TaskDescriptor#getGroup3 <em>Group3</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group3</em>'.
	 * @see org.eclipse.epf.xml.uma.TaskDescriptor#getGroup3()
	 * @see #getTaskDescriptor()
	 * @generated
	 */
	EAttribute getTaskDescriptor_Group3();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.TaskDescriptor#getAdditionallyPerformedBy <em>Additionally Performed By</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Additionally Performed By</em>'.
	 * @see org.eclipse.epf.xml.uma.TaskDescriptor#getAdditionallyPerformedBy()
	 * @see #getTaskDescriptor()
	 * @generated
	 */
	EAttribute getTaskDescriptor_AdditionallyPerformedBy();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.TaskDescriptor#getAssistedBy <em>Assisted By</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Assisted By</em>'.
	 * @see org.eclipse.epf.xml.uma.TaskDescriptor#getAssistedBy()
	 * @see #getTaskDescriptor()
	 * @generated
	 */
	EAttribute getTaskDescriptor_AssistedBy();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.TaskDescriptor#getExternalInput <em>External Input</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>External Input</em>'.
	 * @see org.eclipse.epf.xml.uma.TaskDescriptor#getExternalInput()
	 * @see #getTaskDescriptor()
	 * @generated
	 */
	EAttribute getTaskDescriptor_ExternalInput();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.TaskDescriptor#getMandatoryInput <em>Mandatory Input</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Mandatory Input</em>'.
	 * @see org.eclipse.epf.xml.uma.TaskDescriptor#getMandatoryInput()
	 * @see #getTaskDescriptor()
	 * @generated
	 */
	EAttribute getTaskDescriptor_MandatoryInput();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.TaskDescriptor#getOptionalInput <em>Optional Input</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Optional Input</em>'.
	 * @see org.eclipse.epf.xml.uma.TaskDescriptor#getOptionalInput()
	 * @see #getTaskDescriptor()
	 * @generated
	 */
	EAttribute getTaskDescriptor_OptionalInput();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.TaskDescriptor#getOutput <em>Output</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Output</em>'.
	 * @see org.eclipse.epf.xml.uma.TaskDescriptor#getOutput()
	 * @see #getTaskDescriptor()
	 * @generated
	 */
	EAttribute getTaskDescriptor_Output();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.xml.uma.TaskDescriptor#getStep <em>Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Step</em>'.
	 * @see org.eclipse.epf.xml.uma.TaskDescriptor#getStep()
	 * @see #getTaskDescriptor()
	 * @generated
	 */
	EReference getTaskDescriptor_Step();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.TaskDescriptor#isIsSynchronizedWithSource <em>Is Synchronized With Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Synchronized With Source</em>'.
	 * @see org.eclipse.epf.xml.uma.TaskDescriptor#isIsSynchronizedWithSource()
	 * @see #getTaskDescriptor()
	 * @generated
	 */
	EAttribute getTaskDescriptor_IsSynchronizedWithSource();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.TeamProfile <em>Team Profile</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Team Profile</em>'.
	 * @see org.eclipse.epf.xml.uma.TeamProfile
	 * @generated
	 */
	EClass getTeamProfile();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.TeamProfile#getGroup2 <em>Group2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group2</em>'.
	 * @see org.eclipse.epf.xml.uma.TeamProfile#getGroup2()
	 * @see #getTeamProfile()
	 * @generated
	 */
	EAttribute getTeamProfile_Group2();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.TeamProfile#getRole <em>Role</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Role</em>'.
	 * @see org.eclipse.epf.xml.uma.TeamProfile#getRole()
	 * @see #getTeamProfile()
	 * @generated
	 */
	EAttribute getTeamProfile_Role();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.TeamProfile#getSuperTeam <em>Super Team</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Super Team</em>'.
	 * @see org.eclipse.epf.xml.uma.TeamProfile#getSuperTeam()
	 * @see #getTeamProfile()
	 * @generated
	 */
	EAttribute getTeamProfile_SuperTeam();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.TeamProfile#getSubTeam <em>Sub Team</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Sub Team</em>'.
	 * @see org.eclipse.epf.xml.uma.TeamProfile#getSubTeam()
	 * @see #getTeamProfile()
	 * @generated
	 */
	EAttribute getTeamProfile_SubTeam();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.Template <em>Template</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Template</em>'.
	 * @see org.eclipse.epf.xml.uma.Template
	 * @generated
	 */
	EClass getTemplate();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.TermDefinition <em>Term Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Term Definition</em>'.
	 * @see org.eclipse.epf.xml.uma.TermDefinition
	 * @generated
	 */
	EClass getTermDefinition();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.Tool <em>Tool</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tool</em>'.
	 * @see org.eclipse.epf.xml.uma.Tool
	 * @generated
	 */
	EClass getTool();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.Tool#getGroup2 <em>Group2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group2</em>'.
	 * @see org.eclipse.epf.xml.uma.Tool#getGroup2()
	 * @see #getTool()
	 * @generated
	 */
	EAttribute getTool_Group2();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.Tool#getToolMentor <em>Tool Mentor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Tool Mentor</em>'.
	 * @see org.eclipse.epf.xml.uma.Tool#getToolMentor()
	 * @see #getTool()
	 * @generated
	 */
	EAttribute getTool_ToolMentor();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.ToolMentor <em>Tool Mentor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tool Mentor</em>'.
	 * @see org.eclipse.epf.xml.uma.ToolMentor
	 * @generated
	 */
	EClass getToolMentor();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.Whitepaper <em>Whitepaper</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Whitepaper</em>'.
	 * @see org.eclipse.epf.xml.uma.Whitepaper
	 * @generated
	 */
	EClass getWhitepaper();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.WorkBreakdownElement <em>Work Breakdown Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Work Breakdown Element</em>'.
	 * @see org.eclipse.epf.xml.uma.WorkBreakdownElement
	 * @generated
	 */
	EClass getWorkBreakdownElement();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.WorkBreakdownElement#getGroup2 <em>Group2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group2</em>'.
	 * @see org.eclipse.epf.xml.uma.WorkBreakdownElement#getGroup2()
	 * @see #getWorkBreakdownElement()
	 * @generated
	 */
	EAttribute getWorkBreakdownElement_Group2();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.xml.uma.WorkBreakdownElement#getPredecessor <em>Predecessor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Predecessor</em>'.
	 * @see org.eclipse.epf.xml.uma.WorkBreakdownElement#getPredecessor()
	 * @see #getWorkBreakdownElement()
	 * @generated
	 */
	EReference getWorkBreakdownElement_Predecessor();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.WorkBreakdownElement#isIsEventDriven <em>Is Event Driven</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Event Driven</em>'.
	 * @see org.eclipse.epf.xml.uma.WorkBreakdownElement#isIsEventDriven()
	 * @see #getWorkBreakdownElement()
	 * @generated
	 */
	EAttribute getWorkBreakdownElement_IsEventDriven();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.WorkBreakdownElement#isIsOngoing <em>Is Ongoing</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Ongoing</em>'.
	 * @see org.eclipse.epf.xml.uma.WorkBreakdownElement#isIsOngoing()
	 * @see #getWorkBreakdownElement()
	 * @generated
	 */
	EAttribute getWorkBreakdownElement_IsOngoing();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.WorkBreakdownElement#isIsRepeatable <em>Is Repeatable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Repeatable</em>'.
	 * @see org.eclipse.epf.xml.uma.WorkBreakdownElement#isIsRepeatable()
	 * @see #getWorkBreakdownElement()
	 * @generated
	 */
	EAttribute getWorkBreakdownElement_IsRepeatable();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.WorkDefinition <em>Work Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Work Definition</em>'.
	 * @see org.eclipse.epf.xml.uma.WorkDefinition
	 * @generated
	 */
	EClass getWorkDefinition();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.WorkDefinition#getPrecondition <em>Precondition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Precondition</em>'.
	 * @see org.eclipse.epf.xml.uma.WorkDefinition#getPrecondition()
	 * @see #getWorkDefinition()
	 * @generated
	 */
	EAttribute getWorkDefinition_Precondition();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.WorkDefinition#getPostcondition <em>Postcondition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Postcondition</em>'.
	 * @see org.eclipse.epf.xml.uma.WorkDefinition#getPostcondition()
	 * @see #getWorkDefinition()
	 * @generated
	 */
	EAttribute getWorkDefinition_Postcondition();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.WorkOrder <em>Work Order</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Work Order</em>'.
	 * @see org.eclipse.epf.xml.uma.WorkOrder
	 * @generated
	 */
	EClass getWorkOrder();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.WorkOrder#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.epf.xml.uma.WorkOrder#getValue()
	 * @see #getWorkOrder()
	 * @generated
	 */
	EAttribute getWorkOrder_Value();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.WorkOrder#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.epf.xml.uma.WorkOrder#getId()
	 * @see #getWorkOrder()
	 * @generated
	 */
	EAttribute getWorkOrder_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.WorkOrder#getLinkType <em>Link Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Link Type</em>'.
	 * @see org.eclipse.epf.xml.uma.WorkOrder#getLinkType()
	 * @see #getWorkOrder()
	 * @generated
	 */
	EAttribute getWorkOrder_LinkType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.WorkOrder#getProperties <em>Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Properties</em>'.
	 * @see org.eclipse.epf.xml.uma.WorkOrder#getProperties()
	 * @see #getWorkOrder()
	 * @generated
	 */
	EAttribute getWorkOrder_Properties();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.WorkProduct <em>Work Product</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Work Product</em>'.
	 * @see org.eclipse.epf.xml.uma.WorkProduct
	 * @generated
	 */
	EClass getWorkProduct();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.WorkProduct#getGroup2 <em>Group2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group2</em>'.
	 * @see org.eclipse.epf.xml.uma.WorkProduct#getGroup2()
	 * @see #getWorkProduct()
	 * @generated
	 */
	EAttribute getWorkProduct_Group2();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.WorkProduct#getEstimate <em>Estimate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Estimate</em>'.
	 * @see org.eclipse.epf.xml.uma.WorkProduct#getEstimate()
	 * @see #getWorkProduct()
	 * @generated
	 */
	EAttribute getWorkProduct_Estimate();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.WorkProduct#getEstimationConsiderations <em>Estimation Considerations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Estimation Considerations</em>'.
	 * @see org.eclipse.epf.xml.uma.WorkProduct#getEstimationConsiderations()
	 * @see #getWorkProduct()
	 * @generated
	 */
	EAttribute getWorkProduct_EstimationConsiderations();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.WorkProduct#getReport <em>Report</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Report</em>'.
	 * @see org.eclipse.epf.xml.uma.WorkProduct#getReport()
	 * @see #getWorkProduct()
	 * @generated
	 */
	EAttribute getWorkProduct_Report();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.WorkProduct#getTemplate <em>Template</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Template</em>'.
	 * @see org.eclipse.epf.xml.uma.WorkProduct#getTemplate()
	 * @see #getWorkProduct()
	 * @generated
	 */
	EAttribute getWorkProduct_Template();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.WorkProduct#getToolMentor <em>Tool Mentor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Tool Mentor</em>'.
	 * @see org.eclipse.epf.xml.uma.WorkProduct#getToolMentor()
	 * @see #getWorkProduct()
	 * @generated
	 */
	EAttribute getWorkProduct_ToolMentor();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.WorkProductDescription <em>Work Product Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Work Product Description</em>'.
	 * @see org.eclipse.epf.xml.uma.WorkProductDescription
	 * @generated
	 */
	EClass getWorkProductDescription();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.WorkProductDescription#getImpactOfNotHaving <em>Impact Of Not Having</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Impact Of Not Having</em>'.
	 * @see org.eclipse.epf.xml.uma.WorkProductDescription#getImpactOfNotHaving()
	 * @see #getWorkProductDescription()
	 * @generated
	 */
	EAttribute getWorkProductDescription_ImpactOfNotHaving();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.WorkProductDescription#getPurpose <em>Purpose</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Purpose</em>'.
	 * @see org.eclipse.epf.xml.uma.WorkProductDescription#getPurpose()
	 * @see #getWorkProductDescription()
	 * @generated
	 */
	EAttribute getWorkProductDescription_Purpose();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.WorkProductDescription#getReasonsForNotNeeding <em>Reasons For Not Needing</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Reasons For Not Needing</em>'.
	 * @see org.eclipse.epf.xml.uma.WorkProductDescription#getReasonsForNotNeeding()
	 * @see #getWorkProductDescription()
	 * @generated
	 */
	EAttribute getWorkProductDescription_ReasonsForNotNeeding();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.WorkProductDescriptor <em>Work Product Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Work Product Descriptor</em>'.
	 * @see org.eclipse.epf.xml.uma.WorkProductDescriptor
	 * @generated
	 */
	EClass getWorkProductDescriptor();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.WorkProductDescriptor#getWorkProduct <em>Work Product</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Work Product</em>'.
	 * @see org.eclipse.epf.xml.uma.WorkProductDescriptor#getWorkProduct()
	 * @see #getWorkProductDescriptor()
	 * @generated
	 */
	EAttribute getWorkProductDescriptor_WorkProduct();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.WorkProductDescriptor#getResponsibleRole <em>Responsible Role</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Responsible Role</em>'.
	 * @see org.eclipse.epf.xml.uma.WorkProductDescriptor#getResponsibleRole()
	 * @see #getWorkProductDescriptor()
	 * @generated
	 */
	EAttribute getWorkProductDescriptor_ResponsibleRole();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.WorkProductDescriptor#getGroup2 <em>Group2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group2</em>'.
	 * @see org.eclipse.epf.xml.uma.WorkProductDescriptor#getGroup2()
	 * @see #getWorkProductDescriptor()
	 * @generated
	 */
	EAttribute getWorkProductDescriptor_Group2();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.WorkProductDescriptor#getExternalInputTo <em>External Input To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>External Input To</em>'.
	 * @see org.eclipse.epf.xml.uma.WorkProductDescriptor#getExternalInputTo()
	 * @see #getWorkProductDescriptor()
	 * @generated
	 */
	EAttribute getWorkProductDescriptor_ExternalInputTo();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.WorkProductDescriptor#getImpactedBy <em>Impacted By</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Impacted By</em>'.
	 * @see org.eclipse.epf.xml.uma.WorkProductDescriptor#getImpactedBy()
	 * @see #getWorkProductDescriptor()
	 * @generated
	 */
	EAttribute getWorkProductDescriptor_ImpactedBy();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.WorkProductDescriptor#getImpacts <em>Impacts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Impacts</em>'.
	 * @see org.eclipse.epf.xml.uma.WorkProductDescriptor#getImpacts()
	 * @see #getWorkProductDescriptor()
	 * @generated
	 */
	EAttribute getWorkProductDescriptor_Impacts();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.WorkProductDescriptor#getMandatoryInputTo <em>Mandatory Input To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Mandatory Input To</em>'.
	 * @see org.eclipse.epf.xml.uma.WorkProductDescriptor#getMandatoryInputTo()
	 * @see #getWorkProductDescriptor()
	 * @generated
	 */
	EAttribute getWorkProductDescriptor_MandatoryInputTo();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.WorkProductDescriptor#getOptionalInputTo <em>Optional Input To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Optional Input To</em>'.
	 * @see org.eclipse.epf.xml.uma.WorkProductDescriptor#getOptionalInputTo()
	 * @see #getWorkProductDescriptor()
	 * @generated
	 */
	EAttribute getWorkProductDescriptor_OptionalInputTo();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.WorkProductDescriptor#getOutputFrom <em>Output From</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Output From</em>'.
	 * @see org.eclipse.epf.xml.uma.WorkProductDescriptor#getOutputFrom()
	 * @see #getWorkProductDescriptor()
	 * @generated
	 */
	EAttribute getWorkProductDescriptor_OutputFrom();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.WorkProductDescriptor#getDeliverableParts <em>Deliverable Parts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Deliverable Parts</em>'.
	 * @see org.eclipse.epf.xml.uma.WorkProductDescriptor#getDeliverableParts()
	 * @see #getWorkProductDescriptor()
	 * @generated
	 */
	EAttribute getWorkProductDescriptor_DeliverableParts();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.WorkProductDescriptor#getActivityEntryState <em>Activity Entry State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Activity Entry State</em>'.
	 * @see org.eclipse.epf.xml.uma.WorkProductDescriptor#getActivityEntryState()
	 * @see #getWorkProductDescriptor()
	 * @generated
	 */
	EAttribute getWorkProductDescriptor_ActivityEntryState();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.xml.uma.WorkProductDescriptor#getActivityExitState <em>Activity Exit State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Activity Exit State</em>'.
	 * @see org.eclipse.epf.xml.uma.WorkProductDescriptor#getActivityExitState()
	 * @see #getWorkProductDescriptor()
	 * @generated
	 */
	EAttribute getWorkProductDescriptor_ActivityExitState();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.xml.uma.WorkProductType <em>Work Product Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Work Product Type</em>'.
	 * @see org.eclipse.epf.xml.uma.WorkProductType
	 * @generated
	 */
	EClass getWorkProductType();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.WorkProductType#getGroup2 <em>Group2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group2</em>'.
	 * @see org.eclipse.epf.xml.uma.WorkProductType#getGroup2()
	 * @see #getWorkProductType()
	 * @generated
	 */
	EAttribute getWorkProductType_Group2();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.xml.uma.WorkProductType#getWorkProduct <em>Work Product</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Work Product</em>'.
	 * @see org.eclipse.epf.xml.uma.WorkProductType#getWorkProduct()
	 * @see #getWorkProductType()
	 * @generated
	 */
	EAttribute getWorkProductType_WorkProduct();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.epf.xml.uma.VariabilityType <em>Variability Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Variability Type</em>'.
	 * @see org.eclipse.epf.xml.uma.VariabilityType
	 * @generated
	 */
	EEnum getVariabilityType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.epf.xml.uma.WorkOrderType <em>Work Order Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Work Order Type</em>'.
	 * @see org.eclipse.epf.xml.uma.WorkOrderType
	 * @generated
	 */
	EEnum getWorkOrderType();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.epf.xml.uma.VariabilityType <em>Variability Type Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Variability Type Object</em>'.
	 * @see org.eclipse.epf.xml.uma.VariabilityType
	 * @model instanceClass="org.eclipse.epf.xml.uma.VariabilityType"
	 *        extendedMetaData="name='VariabilityType:Object' baseType='VariabilityType'"
	 * @generated
	 */
	EDataType getVariabilityTypeObject();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.epf.xml.uma.WorkOrderType <em>Work Order Type Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Work Order Type Object</em>'.
	 * @see org.eclipse.epf.xml.uma.WorkOrderType
	 * @model instanceClass="org.eclipse.epf.xml.uma.WorkOrderType"
	 *        extendedMetaData="name='WorkOrderType:Object' baseType='WorkOrderType'"
	 * @generated
	 */
	EDataType getWorkOrderTypeObject();

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
	interface Literals  {
		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.ActivityImpl <em>Activity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.ActivityImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getActivity()
		 * @generated
		 */
		EClass ACTIVITY = eINSTANCE.getActivity();

		/**
		 * The meta object literal for the '<em><b>Precondition</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTIVITY__PRECONDITION = eINSTANCE.getActivity_Precondition();

		/**
		 * The meta object literal for the '<em><b>Postcondition</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTIVITY__POSTCONDITION = eINSTANCE.getActivity_Postcondition();

		/**
		 * The meta object literal for the '<em><b>Group3</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTIVITY__GROUP3 = eINSTANCE.getActivity_Group3();

		/**
		 * The meta object literal for the '<em><b>Breakdown Element</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTIVITY__BREAKDOWN_ELEMENT = eINSTANCE.getActivity_BreakdownElement();

		/**
		 * The meta object literal for the '<em><b>Roadmap</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTIVITY__ROADMAP = eINSTANCE.getActivity_Roadmap();

		/**
		 * The meta object literal for the '<em><b>Is Enactable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTIVITY__IS_ENACTABLE = eINSTANCE.getActivity_IsEnactable();

		/**
		 * The meta object literal for the '<em><b>Variability Based On Element</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTIVITY__VARIABILITY_BASED_ON_ELEMENT = eINSTANCE.getActivity_VariabilityBasedOnElement();

		/**
		 * The meta object literal for the '<em><b>Variability Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTIVITY__VARIABILITY_TYPE = eINSTANCE.getActivity_VariabilityType();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.ActivityDescriptionImpl <em>Activity Description</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.ActivityDescriptionImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getActivityDescription()
		 * @generated
		 */
		EClass ACTIVITY_DESCRIPTION = eINSTANCE.getActivityDescription();

		/**
		 * The meta object literal for the '<em><b>Alternatives</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTIVITY_DESCRIPTION__ALTERNATIVES = eINSTANCE.getActivityDescription_Alternatives();

		/**
		 * The meta object literal for the '<em><b>How To Staff</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTIVITY_DESCRIPTION__HOW_TO_STAFF = eINSTANCE.getActivityDescription_HowToStaff();

		/**
		 * The meta object literal for the '<em><b>Purpose</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTIVITY_DESCRIPTION__PURPOSE = eINSTANCE.getActivityDescription_Purpose();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.ApplicableMetaClassInfoImpl <em>Applicable Meta Class Info</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.ApplicableMetaClassInfoImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getApplicableMetaClassInfo()
		 * @generated
		 */
		EClass APPLICABLE_META_CLASS_INFO = eINSTANCE.getApplicableMetaClassInfo();

		/**
		 * The meta object literal for the '<em><b>Is Primary Extension</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute APPLICABLE_META_CLASS_INFO__IS_PRIMARY_EXTENSION = eINSTANCE.getApplicableMetaClassInfo_IsPrimaryExtension();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.ArtifactImpl <em>Artifact</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.ArtifactImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getArtifact()
		 * @generated
		 */
		EClass ARTIFACT = eINSTANCE.getArtifact();

		/**
		 * The meta object literal for the '<em><b>Group3</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARTIFACT__GROUP3 = eINSTANCE.getArtifact_Group3();

		/**
		 * The meta object literal for the '<em><b>Contained Artifact</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARTIFACT__CONTAINED_ARTIFACT = eINSTANCE.getArtifact_ContainedArtifact();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.ArtifactDescriptionImpl <em>Artifact Description</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.ArtifactDescriptionImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getArtifactDescription()
		 * @generated
		 */
		EClass ARTIFACT_DESCRIPTION = eINSTANCE.getArtifactDescription();

		/**
		 * The meta object literal for the '<em><b>Brief Outline</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARTIFACT_DESCRIPTION__BRIEF_OUTLINE = eINSTANCE.getArtifactDescription_BriefOutline();

		/**
		 * The meta object literal for the '<em><b>Representation Options</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARTIFACT_DESCRIPTION__REPRESENTATION_OPTIONS = eINSTANCE.getArtifactDescription_RepresentationOptions();

		/**
		 * The meta object literal for the '<em><b>Representation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARTIFACT_DESCRIPTION__REPRESENTATION = eINSTANCE.getArtifactDescription_Representation();

		/**
		 * The meta object literal for the '<em><b>Notation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARTIFACT_DESCRIPTION__NOTATION = eINSTANCE.getArtifactDescription_Notation();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.BreakdownElementImpl <em>Breakdown Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.BreakdownElementImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getBreakdownElement()
		 * @generated
		 */
		EClass BREAKDOWN_ELEMENT = eINSTANCE.getBreakdownElement();

		/**
		 * The meta object literal for the '<em><b>Presented After</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BREAKDOWN_ELEMENT__PRESENTED_AFTER = eINSTANCE.getBreakdownElement_PresentedAfter();

		/**
		 * The meta object literal for the '<em><b>Presented Before</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BREAKDOWN_ELEMENT__PRESENTED_BEFORE = eINSTANCE.getBreakdownElement_PresentedBefore();

		/**
		 * The meta object literal for the '<em><b>Planning Data</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BREAKDOWN_ELEMENT__PLANNING_DATA = eINSTANCE.getBreakdownElement_PlanningData();

		/**
		 * The meta object literal for the '<em><b>Super Activity</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BREAKDOWN_ELEMENT__SUPER_ACTIVITY = eINSTANCE.getBreakdownElement_SuperActivity();

		/**
		 * The meta object literal for the '<em><b>Group1</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BREAKDOWN_ELEMENT__GROUP1 = eINSTANCE.getBreakdownElement_Group1();

		/**
		 * The meta object literal for the '<em><b>Checklist</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BREAKDOWN_ELEMENT__CHECKLIST = eINSTANCE.getBreakdownElement_Checklist();

		/**
		 * The meta object literal for the '<em><b>Concept</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BREAKDOWN_ELEMENT__CONCEPT = eINSTANCE.getBreakdownElement_Concept();

		/**
		 * The meta object literal for the '<em><b>Example</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BREAKDOWN_ELEMENT__EXAMPLE = eINSTANCE.getBreakdownElement_Example();

		/**
		 * The meta object literal for the '<em><b>Guideline</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BREAKDOWN_ELEMENT__GUIDELINE = eINSTANCE.getBreakdownElement_Guideline();

		/**
		 * The meta object literal for the '<em><b>Reusable Asset</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BREAKDOWN_ELEMENT__REUSABLE_ASSET = eINSTANCE.getBreakdownElement_ReusableAsset();

		/**
		 * The meta object literal for the '<em><b>Supporting Material</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BREAKDOWN_ELEMENT__SUPPORTING_MATERIAL = eINSTANCE.getBreakdownElement_SupportingMaterial();

		/**
		 * The meta object literal for the '<em><b>Whitepaper</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BREAKDOWN_ELEMENT__WHITEPAPER = eINSTANCE.getBreakdownElement_Whitepaper();

		/**
		 * The meta object literal for the '<em><b>Has Multiple Occurrences</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BREAKDOWN_ELEMENT__HAS_MULTIPLE_OCCURRENCES = eINSTANCE.getBreakdownElement_HasMultipleOccurrences();

		/**
		 * The meta object literal for the '<em><b>Is Optional</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BREAKDOWN_ELEMENT__IS_OPTIONAL = eINSTANCE.getBreakdownElement_IsOptional();

		/**
		 * The meta object literal for the '<em><b>Is Planned</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BREAKDOWN_ELEMENT__IS_PLANNED = eINSTANCE.getBreakdownElement_IsPlanned();

		/**
		 * The meta object literal for the '<em><b>Prefix</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BREAKDOWN_ELEMENT__PREFIX = eINSTANCE.getBreakdownElement_Prefix();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.BreakdownElementDescriptionImpl <em>Breakdown Element Description</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.BreakdownElementDescriptionImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getBreakdownElementDescription()
		 * @generated
		 */
		EClass BREAKDOWN_ELEMENT_DESCRIPTION = eINSTANCE.getBreakdownElementDescription();

		/**
		 * The meta object literal for the '<em><b>Usage Guidance</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BREAKDOWN_ELEMENT_DESCRIPTION__USAGE_GUIDANCE = eINSTANCE.getBreakdownElementDescription_UsageGuidance();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.CapabilityPatternImpl <em>Capability Pattern</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.CapabilityPatternImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getCapabilityPattern()
		 * @generated
		 */
		EClass CAPABILITY_PATTERN = eINSTANCE.getCapabilityPattern();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.ChecklistImpl <em>Checklist</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.ChecklistImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getChecklist()
		 * @generated
		 */
		EClass CHECKLIST = eINSTANCE.getChecklist();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.CompositeRoleImpl <em>Composite Role</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.CompositeRoleImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getCompositeRole()
		 * @generated
		 */
		EClass COMPOSITE_ROLE = eINSTANCE.getCompositeRole();

		/**
		 * The meta object literal for the '<em><b>Group2</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPOSITE_ROLE__GROUP2 = eINSTANCE.getCompositeRole_Group2();

		/**
		 * The meta object literal for the '<em><b>Aggregated Role</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPOSITE_ROLE__AGGREGATED_ROLE = eINSTANCE.getCompositeRole_AggregatedRole();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.ConceptImpl <em>Concept</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.ConceptImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getConcept()
		 * @generated
		 */
		EClass CONCEPT = eINSTANCE.getConcept();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.ConstraintImpl <em>Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.ConstraintImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getConstraint()
		 * @generated
		 */
		EClass CONSTRAINT = eINSTANCE.getConstraint();

		/**
		 * The meta object literal for the '<em><b>Main Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONSTRAINT__MAIN_DESCRIPTION = eINSTANCE.getConstraint_MainDescription();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.ContentCategoryImpl <em>Content Category</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.ContentCategoryImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getContentCategory()
		 * @generated
		 */
		EClass CONTENT_CATEGORY = eINSTANCE.getContentCategory();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.ContentCategoryPackageImpl <em>Content Category Package</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.ContentCategoryPackageImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getContentCategoryPackage()
		 * @generated
		 */
		EClass CONTENT_CATEGORY_PACKAGE = eINSTANCE.getContentCategoryPackage();

		/**
		 * The meta object literal for the '<em><b>Group2</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTENT_CATEGORY_PACKAGE__GROUP2 = eINSTANCE.getContentCategoryPackage_Group2();

		/**
		 * The meta object literal for the '<em><b>Content Category</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTENT_CATEGORY_PACKAGE__CONTENT_CATEGORY = eINSTANCE.getContentCategoryPackage_ContentCategory();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.ContentDescriptionImpl <em>Content Description</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.ContentDescriptionImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getContentDescription()
		 * @generated
		 */
		EClass CONTENT_DESCRIPTION = eINSTANCE.getContentDescription();

		/**
		 * The meta object literal for the '<em><b>Main Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTENT_DESCRIPTION__MAIN_DESCRIPTION = eINSTANCE.getContentDescription_MainDescription();

		/**
		 * The meta object literal for the '<em><b>Key Considerations</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTENT_DESCRIPTION__KEY_CONSIDERATIONS = eINSTANCE.getContentDescription_KeyConsiderations();

		/**
		 * The meta object literal for the '<em><b>Section</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTENT_DESCRIPTION__SECTION = eINSTANCE.getContentDescription_Section();

		/**
		 * The meta object literal for the '<em><b>External Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTENT_DESCRIPTION__EXTERNAL_ID = eINSTANCE.getContentDescription_ExternalId();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.ContentElementImpl <em>Content Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.ContentElementImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getContentElement()
		 * @generated
		 */
		EClass CONTENT_ELEMENT = eINSTANCE.getContentElement();

		/**
		 * The meta object literal for the '<em><b>Group1</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTENT_ELEMENT__GROUP1 = eINSTANCE.getContentElement_Group1();

		/**
		 * The meta object literal for the '<em><b>Checklist</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTENT_ELEMENT__CHECKLIST = eINSTANCE.getContentElement_Checklist();

		/**
		 * The meta object literal for the '<em><b>Concept</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTENT_ELEMENT__CONCEPT = eINSTANCE.getContentElement_Concept();

		/**
		 * The meta object literal for the '<em><b>Example</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTENT_ELEMENT__EXAMPLE = eINSTANCE.getContentElement_Example();

		/**
		 * The meta object literal for the '<em><b>Guideline</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTENT_ELEMENT__GUIDELINE = eINSTANCE.getContentElement_Guideline();

		/**
		 * The meta object literal for the '<em><b>Reusable Asset</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTENT_ELEMENT__REUSABLE_ASSET = eINSTANCE.getContentElement_ReusableAsset();

		/**
		 * The meta object literal for the '<em><b>Supporting Material</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTENT_ELEMENT__SUPPORTING_MATERIAL = eINSTANCE.getContentElement_SupportingMaterial();

		/**
		 * The meta object literal for the '<em><b>Whitepaper</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTENT_ELEMENT__WHITEPAPER = eINSTANCE.getContentElement_Whitepaper();

		/**
		 * The meta object literal for the '<em><b>Variability Based On Element</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTENT_ELEMENT__VARIABILITY_BASED_ON_ELEMENT = eINSTANCE.getContentElement_VariabilityBasedOnElement();

		/**
		 * The meta object literal for the '<em><b>Variability Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTENT_ELEMENT__VARIABILITY_TYPE = eINSTANCE.getContentElement_VariabilityType();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.ContentPackageImpl <em>Content Package</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.ContentPackageImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getContentPackage()
		 * @generated
		 */
		EClass CONTENT_PACKAGE = eINSTANCE.getContentPackage();

		/**
		 * The meta object literal for the '<em><b>Group2</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTENT_PACKAGE__GROUP2 = eINSTANCE.getContentPackage_Group2();

		/**
		 * The meta object literal for the '<em><b>Content Element</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTENT_PACKAGE__CONTENT_ELEMENT = eINSTANCE.getContentPackage_ContentElement();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.CustomCategoryImpl <em>Custom Category</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.CustomCategoryImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getCustomCategory()
		 * @generated
		 */
		EClass CUSTOM_CATEGORY = eINSTANCE.getCustomCategory();

		/**
		 * The meta object literal for the '<em><b>Group2</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOM_CATEGORY__GROUP2 = eINSTANCE.getCustomCategory_Group2();

		/**
		 * The meta object literal for the '<em><b>Categorized Element</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOM_CATEGORY__CATEGORIZED_ELEMENT = eINSTANCE.getCustomCategory_CategorizedElement();

		/**
		 * The meta object literal for the '<em><b>Sub Category</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOM_CATEGORY__SUB_CATEGORY = eINSTANCE.getCustomCategory_SubCategory();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.DeliverableImpl <em>Deliverable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.DeliverableImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getDeliverable()
		 * @generated
		 */
		EClass DELIVERABLE = eINSTANCE.getDeliverable();

		/**
		 * The meta object literal for the '<em><b>Group3</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DELIVERABLE__GROUP3 = eINSTANCE.getDeliverable_Group3();

		/**
		 * The meta object literal for the '<em><b>Delivered Work Product</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DELIVERABLE__DELIVERED_WORK_PRODUCT = eINSTANCE.getDeliverable_DeliveredWorkProduct();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.DeliverableDescriptionImpl <em>Deliverable Description</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.DeliverableDescriptionImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getDeliverableDescription()
		 * @generated
		 */
		EClass DELIVERABLE_DESCRIPTION = eINSTANCE.getDeliverableDescription();

		/**
		 * The meta object literal for the '<em><b>External Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DELIVERABLE_DESCRIPTION__EXTERNAL_DESCRIPTION = eINSTANCE.getDeliverableDescription_ExternalDescription();

		/**
		 * The meta object literal for the '<em><b>Packaging Guidance</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DELIVERABLE_DESCRIPTION__PACKAGING_GUIDANCE = eINSTANCE.getDeliverableDescription_PackagingGuidance();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.DeliveryProcessImpl <em>Delivery Process</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.DeliveryProcessImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getDeliveryProcess()
		 * @generated
		 */
		EClass DELIVERY_PROCESS = eINSTANCE.getDeliveryProcess();

		/**
		 * The meta object literal for the '<em><b>Group4</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DELIVERY_PROCESS__GROUP4 = eINSTANCE.getDeliveryProcess_Group4();

		/**
		 * The meta object literal for the '<em><b>Communications Material</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DELIVERY_PROCESS__COMMUNICATIONS_MATERIAL = eINSTANCE.getDeliveryProcess_CommunicationsMaterial();

		/**
		 * The meta object literal for the '<em><b>Education Material</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DELIVERY_PROCESS__EDUCATION_MATERIAL = eINSTANCE.getDeliveryProcess_EducationMaterial();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.DeliveryProcessDescriptionImpl <em>Delivery Process Description</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.DeliveryProcessDescriptionImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getDeliveryProcessDescription()
		 * @generated
		 */
		EClass DELIVERY_PROCESS_DESCRIPTION = eINSTANCE.getDeliveryProcessDescription();

		/**
		 * The meta object literal for the '<em><b>Scale</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DELIVERY_PROCESS_DESCRIPTION__SCALE = eINSTANCE.getDeliveryProcessDescription_Scale();

		/**
		 * The meta object literal for the '<em><b>Project Characteristics</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DELIVERY_PROCESS_DESCRIPTION__PROJECT_CHARACTERISTICS = eINSTANCE.getDeliveryProcessDescription_ProjectCharacteristics();

		/**
		 * The meta object literal for the '<em><b>Risk Level</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DELIVERY_PROCESS_DESCRIPTION__RISK_LEVEL = eINSTANCE.getDeliveryProcessDescription_RiskLevel();

		/**
		 * The meta object literal for the '<em><b>Estimating Technique</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DELIVERY_PROCESS_DESCRIPTION__ESTIMATING_TECHNIQUE = eINSTANCE.getDeliveryProcessDescription_EstimatingTechnique();

		/**
		 * The meta object literal for the '<em><b>Project Member Expertise</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DELIVERY_PROCESS_DESCRIPTION__PROJECT_MEMBER_EXPERTISE = eINSTANCE.getDeliveryProcessDescription_ProjectMemberExpertise();

		/**
		 * The meta object literal for the '<em><b>Type Of Contract</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DELIVERY_PROCESS_DESCRIPTION__TYPE_OF_CONTRACT = eINSTANCE.getDeliveryProcessDescription_TypeOfContract();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.DescribableElementImpl <em>Describable Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.DescribableElementImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getDescribableElement()
		 * @generated
		 */
		EClass DESCRIBABLE_ELEMENT = eINSTANCE.getDescribableElement();

		/**
		 * The meta object literal for the '<em><b>Presentation</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DESCRIBABLE_ELEMENT__PRESENTATION = eINSTANCE.getDescribableElement_Presentation();

		/**
		 * The meta object literal for the '<em><b>Fulfill</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DESCRIBABLE_ELEMENT__FULFILL = eINSTANCE.getDescribableElement_Fulfill();

		/**
		 * The meta object literal for the '<em><b>Is Abstract</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DESCRIBABLE_ELEMENT__IS_ABSTRACT = eINSTANCE.getDescribableElement_IsAbstract();

		/**
		 * The meta object literal for the '<em><b>Nodeicon</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DESCRIBABLE_ELEMENT__NODEICON = eINSTANCE.getDescribableElement_Nodeicon();

		/**
		 * The meta object literal for the '<em><b>Shapeicon</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DESCRIBABLE_ELEMENT__SHAPEICON = eINSTANCE.getDescribableElement_Shapeicon();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.DescriptorImpl <em>Descriptor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.DescriptorImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getDescriptor()
		 * @generated
		 */
		EClass DESCRIPTOR = eINSTANCE.getDescriptor();

		/**
		 * The meta object literal for the '<em><b>Is Synchronized With Source</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DESCRIPTOR__IS_SYNCHRONIZED_WITH_SOURCE = eINSTANCE.getDescriptor_IsSynchronizedWithSource();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.DescriptorDescriptionImpl <em>Descriptor Description</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.DescriptorDescriptionImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getDescriptorDescription()
		 * @generated
		 */
		EClass DESCRIPTOR_DESCRIPTION = eINSTANCE.getDescriptorDescription();

		/**
		 * The meta object literal for the '<em><b>Refined Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DESCRIPTOR_DESCRIPTION__REFINED_DESCRIPTION = eINSTANCE.getDescriptorDescription_RefinedDescription();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.DisciplineImpl <em>Discipline</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.DisciplineImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getDiscipline()
		 * @generated
		 */
		EClass DISCIPLINE = eINSTANCE.getDiscipline();

		/**
		 * The meta object literal for the '<em><b>Group2</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DISCIPLINE__GROUP2 = eINSTANCE.getDiscipline_Group2();

		/**
		 * The meta object literal for the '<em><b>Task</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DISCIPLINE__TASK = eINSTANCE.getDiscipline_Task();

		/**
		 * The meta object literal for the '<em><b>Sub Discipline</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DISCIPLINE__SUB_DISCIPLINE = eINSTANCE.getDiscipline_SubDiscipline();

		/**
		 * The meta object literal for the '<em><b>Reference Workflow</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DISCIPLINE__REFERENCE_WORKFLOW = eINSTANCE.getDiscipline_ReferenceWorkflow();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.DisciplineGroupingImpl <em>Discipline Grouping</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.DisciplineGroupingImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getDisciplineGrouping()
		 * @generated
		 */
		EClass DISCIPLINE_GROUPING = eINSTANCE.getDisciplineGrouping();

		/**
		 * The meta object literal for the '<em><b>Group2</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DISCIPLINE_GROUPING__GROUP2 = eINSTANCE.getDisciplineGrouping_Group2();

		/**
		 * The meta object literal for the '<em><b>Discipline</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DISCIPLINE_GROUPING__DISCIPLINE = eINSTANCE.getDisciplineGrouping_Discipline();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.DocumentRootImpl <em>Document Root</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.DocumentRootImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getDocumentRoot()
		 * @generated
		 */
		EClass DOCUMENT_ROOT = eINSTANCE.getDocumentRoot();

		/**
		 * The meta object literal for the '<em><b>Mixed</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__MIXED = eINSTANCE.getDocumentRoot_Mixed();

		/**
		 * The meta object literal for the '<em><b>XMLNS Prefix Map</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__XMLNS_PREFIX_MAP = eINSTANCE.getDocumentRoot_XMLNSPrefixMap();

		/**
		 * The meta object literal for the '<em><b>XSI Schema Location</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = eINSTANCE.getDocumentRoot_XSISchemaLocation();

		/**
		 * The meta object literal for the '<em><b>Method Configuration</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__METHOD_CONFIGURATION = eINSTANCE.getDocumentRoot_MethodConfiguration();

		/**
		 * The meta object literal for the '<em><b>Method Library</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__METHOD_LIBRARY = eINSTANCE.getDocumentRoot_MethodLibrary();

		/**
		 * The meta object literal for the '<em><b>Method Plugin</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__METHOD_PLUGIN = eINSTANCE.getDocumentRoot_MethodPlugin();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.DomainImpl <em>Domain</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.DomainImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getDomain()
		 * @generated
		 */
		EClass DOMAIN = eINSTANCE.getDomain();

		/**
		 * The meta object literal for the '<em><b>Group2</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOMAIN__GROUP2 = eINSTANCE.getDomain_Group2();

		/**
		 * The meta object literal for the '<em><b>Work Product</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOMAIN__WORK_PRODUCT = eINSTANCE.getDomain_WorkProduct();

		/**
		 * The meta object literal for the '<em><b>Subdomain</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOMAIN__SUBDOMAIN = eINSTANCE.getDomain_Subdomain();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.ElementImpl <em>Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.ElementImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getElement()
		 * @generated
		 */
		EClass ELEMENT = eINSTANCE.getElement();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.EstimateImpl <em>Estimate</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.EstimateImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getEstimate()
		 * @generated
		 */
		EClass ESTIMATE = eINSTANCE.getEstimate();

		/**
		 * The meta object literal for the '<em><b>Group2</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ESTIMATE__GROUP2 = eINSTANCE.getEstimate_Group2();

		/**
		 * The meta object literal for the '<em><b>Estimation Metric</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ESTIMATE__ESTIMATION_METRIC = eINSTANCE.getEstimate_EstimationMetric();

		/**
		 * The meta object literal for the '<em><b>Estimation Considerations</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ESTIMATE__ESTIMATION_CONSIDERATIONS = eINSTANCE.getEstimate_EstimationConsiderations();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.EstimatingMetricImpl <em>Estimating Metric</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.EstimatingMetricImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getEstimatingMetric()
		 * @generated
		 */
		EClass ESTIMATING_METRIC = eINSTANCE.getEstimatingMetric();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.EstimationConsiderationsImpl <em>Estimation Considerations</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.EstimationConsiderationsImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getEstimationConsiderations()
		 * @generated
		 */
		EClass ESTIMATION_CONSIDERATIONS = eINSTANCE.getEstimationConsiderations();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.ExampleImpl <em>Example</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.ExampleImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getExample()
		 * @generated
		 */
		EClass EXAMPLE = eINSTANCE.getExample();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.GuidanceImpl <em>Guidance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.GuidanceImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getGuidance()
		 * @generated
		 */
		EClass GUIDANCE = eINSTANCE.getGuidance();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.GuidanceDescriptionImpl <em>Guidance Description</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.GuidanceDescriptionImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getGuidanceDescription()
		 * @generated
		 */
		EClass GUIDANCE_DESCRIPTION = eINSTANCE.getGuidanceDescription();

		/**
		 * The meta object literal for the '<em><b>Attachment</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GUIDANCE_DESCRIPTION__ATTACHMENT = eINSTANCE.getGuidanceDescription_Attachment();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.GuidelineImpl <em>Guideline</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.GuidelineImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getGuideline()
		 * @generated
		 */
		EClass GUIDELINE = eINSTANCE.getGuideline();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.IterationImpl <em>Iteration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.IterationImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getIteration()
		 * @generated
		 */
		EClass ITERATION = eINSTANCE.getIteration();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.KindImpl <em>Kind</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.KindImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getKind()
		 * @generated
		 */
		EClass KIND = eINSTANCE.getKind();

		/**
		 * The meta object literal for the '<em><b>Applicable Meta Class Info</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute KIND__APPLICABLE_META_CLASS_INFO = eINSTANCE.getKind_ApplicableMetaClassInfo();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.MethodConfigurationImpl <em>Method Configuration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.MethodConfigurationImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getMethodConfiguration()
		 * @generated
		 */
		EClass METHOD_CONFIGURATION = eINSTANCE.getMethodConfiguration();

		/**
		 * The meta object literal for the '<em><b>Base Configuration</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METHOD_CONFIGURATION__BASE_CONFIGURATION = eINSTANCE.getMethodConfiguration_BaseConfiguration();

		/**
		 * The meta object literal for the '<em><b>Method Plugin Selection</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METHOD_CONFIGURATION__METHOD_PLUGIN_SELECTION = eINSTANCE.getMethodConfiguration_MethodPluginSelection();

		/**
		 * The meta object literal for the '<em><b>Method Package Selection</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METHOD_CONFIGURATION__METHOD_PACKAGE_SELECTION = eINSTANCE.getMethodConfiguration_MethodPackageSelection();

		/**
		 * The meta object literal for the '<em><b>Default View</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METHOD_CONFIGURATION__DEFAULT_VIEW = eINSTANCE.getMethodConfiguration_DefaultView();

		/**
		 * The meta object literal for the '<em><b>Process View</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METHOD_CONFIGURATION__PROCESS_VIEW = eINSTANCE.getMethodConfiguration_ProcessView();

		/**
		 * The meta object literal for the '<em><b>Subtracted Category</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METHOD_CONFIGURATION__SUBTRACTED_CATEGORY = eINSTANCE.getMethodConfiguration_SubtractedCategory();

		/**
		 * The meta object literal for the '<em><b>Added Category</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METHOD_CONFIGURATION__ADDED_CATEGORY = eINSTANCE.getMethodConfiguration_AddedCategory();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.MethodElementImpl <em>Method Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.MethodElementImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getMethodElement()
		 * @generated
		 */
		EClass METHOD_ELEMENT = eINSTANCE.getMethodElement();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METHOD_ELEMENT__GROUP = eINSTANCE.getMethodElement_Group();

		/**
		 * The meta object literal for the '<em><b>Owned Rule</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METHOD_ELEMENT__OWNED_RULE = eINSTANCE.getMethodElement_OwnedRule();

		/**
		 * The meta object literal for the '<em><b>Method Element Property</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METHOD_ELEMENT__METHOD_ELEMENT_PROPERTY = eINSTANCE.getMethodElement_MethodElementProperty();

		/**
		 * The meta object literal for the '<em><b>Brief Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METHOD_ELEMENT__BRIEF_DESCRIPTION = eINSTANCE.getMethodElement_BriefDescription();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METHOD_ELEMENT__ID = eINSTANCE.getMethodElement_Id();

		/**
		 * The meta object literal for the '<em><b>Ordering Guide</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METHOD_ELEMENT__ORDERING_GUIDE = eINSTANCE.getMethodElement_OrderingGuide();

		/**
		 * The meta object literal for the '<em><b>Presentation Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METHOD_ELEMENT__PRESENTATION_NAME = eINSTANCE.getMethodElement_PresentationName();

		/**
		 * The meta object literal for the '<em><b>Suppressed</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METHOD_ELEMENT__SUPPRESSED = eINSTANCE.getMethodElement_Suppressed();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.MethodElementPropertyImpl <em>Method Element Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.MethodElementPropertyImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getMethodElementProperty()
		 * @generated
		 */
		EClass METHOD_ELEMENT_PROPERTY = eINSTANCE.getMethodElementProperty();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METHOD_ELEMENT_PROPERTY__VALUE = eINSTANCE.getMethodElementProperty_Value();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.MethodLibraryImpl <em>Method Library</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.MethodLibraryImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getMethodLibrary()
		 * @generated
		 */
		EClass METHOD_LIBRARY = eINSTANCE.getMethodLibrary();

		/**
		 * The meta object literal for the '<em><b>Method Plugin</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METHOD_LIBRARY__METHOD_PLUGIN = eINSTANCE.getMethodLibrary_MethodPlugin();

		/**
		 * The meta object literal for the '<em><b>Method Configuration</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METHOD_LIBRARY__METHOD_CONFIGURATION = eINSTANCE.getMethodLibrary_MethodConfiguration();

		/**
		 * The meta object literal for the '<em><b>Tool</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METHOD_LIBRARY__TOOL = eINSTANCE.getMethodLibrary_Tool();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.MethodPackageImpl <em>Method Package</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.MethodPackageImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getMethodPackage()
		 * @generated
		 */
		EClass METHOD_PACKAGE = eINSTANCE.getMethodPackage();

		/**
		 * The meta object literal for the '<em><b>Group1</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METHOD_PACKAGE__GROUP1 = eINSTANCE.getMethodPackage_Group1();

		/**
		 * The meta object literal for the '<em><b>Reused Package</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METHOD_PACKAGE__REUSED_PACKAGE = eINSTANCE.getMethodPackage_ReusedPackage();

		/**
		 * The meta object literal for the '<em><b>Method Package</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METHOD_PACKAGE__METHOD_PACKAGE = eINSTANCE.getMethodPackage_MethodPackage();

		/**
		 * The meta object literal for the '<em><b>Global</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METHOD_PACKAGE__GLOBAL = eINSTANCE.getMethodPackage_Global();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.MethodPluginImpl <em>Method Plugin</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.MethodPluginImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getMethodPlugin()
		 * @generated
		 */
		EClass METHOD_PLUGIN = eINSTANCE.getMethodPlugin();

		/**
		 * The meta object literal for the '<em><b>Referenced Method Plugin</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METHOD_PLUGIN__REFERENCED_METHOD_PLUGIN = eINSTANCE.getMethodPlugin_ReferencedMethodPlugin();

		/**
		 * The meta object literal for the '<em><b>Method Package</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METHOD_PLUGIN__METHOD_PACKAGE = eINSTANCE.getMethodPlugin_MethodPackage();

		/**
		 * The meta object literal for the '<em><b>Supporting</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METHOD_PLUGIN__SUPPORTING = eINSTANCE.getMethodPlugin_Supporting();

		/**
		 * The meta object literal for the '<em><b>User Changeable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METHOD_PLUGIN__USER_CHANGEABLE = eINSTANCE.getMethodPlugin_UserChangeable();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.MethodUnitImpl <em>Method Unit</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.MethodUnitImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getMethodUnit()
		 * @generated
		 */
		EClass METHOD_UNIT = eINSTANCE.getMethodUnit();

		/**
		 * The meta object literal for the '<em><b>Copyright</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METHOD_UNIT__COPYRIGHT = eINSTANCE.getMethodUnit_Copyright();

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
		EAttribute METHOD_UNIT__CHANGE_DATE = eINSTANCE.getMethodUnit_ChangeDate();

		/**
		 * The meta object literal for the '<em><b>Change Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METHOD_UNIT__CHANGE_DESCRIPTION = eINSTANCE.getMethodUnit_ChangeDescription();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METHOD_UNIT__VERSION = eINSTANCE.getMethodUnit_Version();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.MilestoneImpl <em>Milestone</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.MilestoneImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getMilestone()
		 * @generated
		 */
		EClass MILESTONE = eINSTANCE.getMilestone();

		/**
		 * The meta object literal for the '<em><b>Required Result</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MILESTONE__REQUIRED_RESULT = eINSTANCE.getMilestone_RequiredResult();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.NamedElementImpl <em>Named Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.NamedElementImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getNamedElement()
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
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.OutcomeImpl <em>Outcome</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.OutcomeImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getOutcome()
		 * @generated
		 */
		EClass OUTCOME = eINSTANCE.getOutcome();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.PackageableElementImpl <em>Packageable Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.PackageableElementImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getPackageableElement()
		 * @generated
		 */
		EClass PACKAGEABLE_ELEMENT = eINSTANCE.getPackageableElement();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.PhaseImpl <em>Phase</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.PhaseImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getPhase()
		 * @generated
		 */
		EClass PHASE = eINSTANCE.getPhase();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.PlanningDataImpl <em>Planning Data</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.PlanningDataImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getPlanningData()
		 * @generated
		 */
		EClass PLANNING_DATA = eINSTANCE.getPlanningData();

		/**
		 * The meta object literal for the '<em><b>Finish Date</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PLANNING_DATA__FINISH_DATE = eINSTANCE.getPlanningData_FinishDate();

		/**
		 * The meta object literal for the '<em><b>Rank</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PLANNING_DATA__RANK = eINSTANCE.getPlanningData_Rank();

		/**
		 * The meta object literal for the '<em><b>Start Date</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PLANNING_DATA__START_DATE = eINSTANCE.getPlanningData_StartDate();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.PracticeImpl <em>Practice</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.PracticeImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getPractice()
		 * @generated
		 */
		EClass PRACTICE = eINSTANCE.getPractice();

		/**
		 * The meta object literal for the '<em><b>Group2</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRACTICE__GROUP2 = eINSTANCE.getPractice_Group2();

		/**
		 * The meta object literal for the '<em><b>Activity Reference</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRACTICE__ACTIVITY_REFERENCE = eINSTANCE.getPractice_ActivityReference();

		/**
		 * The meta object literal for the '<em><b>Content Reference</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRACTICE__CONTENT_REFERENCE = eINSTANCE.getPractice_ContentReference();

		/**
		 * The meta object literal for the '<em><b>Sub Practice</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRACTICE__SUB_PRACTICE = eINSTANCE.getPractice_SubPractice();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.PracticeDescriptionImpl <em>Practice Description</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.PracticeDescriptionImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getPracticeDescription()
		 * @generated
		 */
		EClass PRACTICE_DESCRIPTION = eINSTANCE.getPracticeDescription();

		/**
		 * The meta object literal for the '<em><b>Additional Info</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRACTICE_DESCRIPTION__ADDITIONAL_INFO = eINSTANCE.getPracticeDescription_AdditionalInfo();

		/**
		 * The meta object literal for the '<em><b>Application</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRACTICE_DESCRIPTION__APPLICATION = eINSTANCE.getPracticeDescription_Application();

		/**
		 * The meta object literal for the '<em><b>Background</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRACTICE_DESCRIPTION__BACKGROUND = eINSTANCE.getPracticeDescription_Background();

		/**
		 * The meta object literal for the '<em><b>Goals</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRACTICE_DESCRIPTION__GOALS = eINSTANCE.getPracticeDescription_Goals();

		/**
		 * The meta object literal for the '<em><b>Levels Of Adoption</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRACTICE_DESCRIPTION__LEVELS_OF_ADOPTION = eINSTANCE.getPracticeDescription_LevelsOfAdoption();

		/**
		 * The meta object literal for the '<em><b>Problem</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRACTICE_DESCRIPTION__PROBLEM = eINSTANCE.getPracticeDescription_Problem();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.ProcessImpl <em>Process</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.ProcessImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getProcess()
		 * @generated
		 */
		EClass PROCESS = eINSTANCE.getProcess();

		/**
		 * The meta object literal for the '<em><b>Includes Pattern</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROCESS__INCLUDES_PATTERN = eINSTANCE.getProcess_IncludesPattern();

		/**
		 * The meta object literal for the '<em><b>Default Context</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROCESS__DEFAULT_CONTEXT = eINSTANCE.getProcess_DefaultContext();

		/**
		 * The meta object literal for the '<em><b>Valid Context</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROCESS__VALID_CONTEXT = eINSTANCE.getProcess_ValidContext();

		/**
		 * The meta object literal for the '<em><b>Diagram URI</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROCESS__DIAGRAM_URI = eINSTANCE.getProcess_DiagramURI();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.ProcessComponentImpl <em>Process Component</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.ProcessComponentImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getProcessComponent()
		 * @generated
		 */
		EClass PROCESS_COMPONENT = eINSTANCE.getProcessComponent();

		/**
		 * The meta object literal for the '<em><b>Copyright</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROCESS_COMPONENT__COPYRIGHT = eINSTANCE.getProcessComponent_Copyright();

		/**
		 * The meta object literal for the '<em><b>Interface</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_COMPONENT__INTERFACE = eINSTANCE.getProcessComponent_Interface();

		/**
		 * The meta object literal for the '<em><b>Process</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_COMPONENT__PROCESS = eINSTANCE.getProcessComponent_Process();

		/**
		 * The meta object literal for the '<em><b>Authors</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROCESS_COMPONENT__AUTHORS = eINSTANCE.getProcessComponent_Authors();

		/**
		 * The meta object literal for the '<em><b>Change Date</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROCESS_COMPONENT__CHANGE_DATE = eINSTANCE.getProcessComponent_ChangeDate();

		/**
		 * The meta object literal for the '<em><b>Change Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROCESS_COMPONENT__CHANGE_DESCRIPTION = eINSTANCE.getProcessComponent_ChangeDescription();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROCESS_COMPONENT__VERSION = eINSTANCE.getProcessComponent_Version();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.ProcessComponentInterfaceImpl <em>Process Component Interface</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.ProcessComponentInterfaceImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getProcessComponentInterface()
		 * @generated
		 */
		EClass PROCESS_COMPONENT_INTERFACE = eINSTANCE.getProcessComponentInterface();

		/**
		 * The meta object literal for the '<em><b>Group2</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROCESS_COMPONENT_INTERFACE__GROUP2 = eINSTANCE.getProcessComponentInterface_Group2();

		/**
		 * The meta object literal for the '<em><b>Interface Specification</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_COMPONENT_INTERFACE__INTERFACE_SPECIFICATION = eINSTANCE.getProcessComponentInterface_InterfaceSpecification();

		/**
		 * The meta object literal for the '<em><b>Interface IO</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_COMPONENT_INTERFACE__INTERFACE_IO = eINSTANCE.getProcessComponentInterface_InterfaceIO();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.ProcessDescriptionImpl <em>Process Description</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.ProcessDescriptionImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getProcessDescription()
		 * @generated
		 */
		EClass PROCESS_DESCRIPTION = eINSTANCE.getProcessDescription();

		/**
		 * The meta object literal for the '<em><b>Scope</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROCESS_DESCRIPTION__SCOPE = eINSTANCE.getProcessDescription_Scope();

		/**
		 * The meta object literal for the '<em><b>Usage Notes</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROCESS_DESCRIPTION__USAGE_NOTES = eINSTANCE.getProcessDescription_UsageNotes();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.ProcessElementImpl <em>Process Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.ProcessElementImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getProcessElement()
		 * @generated
		 */
		EClass PROCESS_ELEMENT = eINSTANCE.getProcessElement();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.ProcessPackageImpl <em>Process Package</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.ProcessPackageImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getProcessPackage()
		 * @generated
		 */
		EClass PROCESS_PACKAGE = eINSTANCE.getProcessPackage();

		/**
		 * The meta object literal for the '<em><b>Group2</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROCESS_PACKAGE__GROUP2 = eINSTANCE.getProcessPackage_Group2();

		/**
		 * The meta object literal for the '<em><b>Process Element</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_PACKAGE__PROCESS_ELEMENT = eINSTANCE.getProcessPackage_ProcessElement();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.ProcessPlanningTemplateImpl <em>Process Planning Template</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.ProcessPlanningTemplateImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getProcessPlanningTemplate()
		 * @generated
		 */
		EClass PROCESS_PLANNING_TEMPLATE = eINSTANCE.getProcessPlanningTemplate();

		/**
		 * The meta object literal for the '<em><b>Group4</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROCESS_PLANNING_TEMPLATE__GROUP4 = eINSTANCE.getProcessPlanningTemplate_Group4();

		/**
		 * The meta object literal for the '<em><b>Base Process</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROCESS_PLANNING_TEMPLATE__BASE_PROCESS = eINSTANCE.getProcessPlanningTemplate_BaseProcess();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.ReportImpl <em>Report</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.ReportImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getReport()
		 * @generated
		 */
		EClass REPORT = eINSTANCE.getReport();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.ReusableAssetImpl <em>Reusable Asset</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.ReusableAssetImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getReusableAsset()
		 * @generated
		 */
		EClass REUSABLE_ASSET = eINSTANCE.getReusableAsset();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.RoadmapImpl <em>Roadmap</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.RoadmapImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getRoadmap()
		 * @generated
		 */
		EClass ROADMAP = eINSTANCE.getRoadmap();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.RoleImpl <em>Role</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.RoleImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getRole()
		 * @generated
		 */
		EClass ROLE = eINSTANCE.getRole();

		/**
		 * The meta object literal for the '<em><b>Group2</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ROLE__GROUP2 = eINSTANCE.getRole_Group2();

		/**
		 * The meta object literal for the '<em><b>Responsible For</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ROLE__RESPONSIBLE_FOR = eINSTANCE.getRole_ResponsibleFor();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.RoleDescriptionImpl <em>Role Description</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.RoleDescriptionImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getRoleDescription()
		 * @generated
		 */
		EClass ROLE_DESCRIPTION = eINSTANCE.getRoleDescription();

		/**
		 * The meta object literal for the '<em><b>Assignment Approaches</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ROLE_DESCRIPTION__ASSIGNMENT_APPROACHES = eINSTANCE.getRoleDescription_AssignmentApproaches();

		/**
		 * The meta object literal for the '<em><b>Skills</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ROLE_DESCRIPTION__SKILLS = eINSTANCE.getRoleDescription_Skills();

		/**
		 * The meta object literal for the '<em><b>Synonyms</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ROLE_DESCRIPTION__SYNONYMS = eINSTANCE.getRoleDescription_Synonyms();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.RoleDescriptorImpl <em>Role Descriptor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.RoleDescriptorImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getRoleDescriptor()
		 * @generated
		 */
		EClass ROLE_DESCRIPTOR = eINSTANCE.getRoleDescriptor();

		/**
		 * The meta object literal for the '<em><b>Role</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ROLE_DESCRIPTOR__ROLE = eINSTANCE.getRoleDescriptor_Role();

		/**
		 * The meta object literal for the '<em><b>Responsible For</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ROLE_DESCRIPTOR__RESPONSIBLE_FOR = eINSTANCE.getRoleDescriptor_ResponsibleFor();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.RoleSetImpl <em>Role Set</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.RoleSetImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getRoleSet()
		 * @generated
		 */
		EClass ROLE_SET = eINSTANCE.getRoleSet();

		/**
		 * The meta object literal for the '<em><b>Group2</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ROLE_SET__GROUP2 = eINSTANCE.getRoleSet_Group2();

		/**
		 * The meta object literal for the '<em><b>Role</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ROLE_SET__ROLE = eINSTANCE.getRoleSet_Role();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.RoleSetGroupingImpl <em>Role Set Grouping</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.RoleSetGroupingImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getRoleSetGrouping()
		 * @generated
		 */
		EClass ROLE_SET_GROUPING = eINSTANCE.getRoleSetGrouping();

		/**
		 * The meta object literal for the '<em><b>Group2</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ROLE_SET_GROUPING__GROUP2 = eINSTANCE.getRoleSetGrouping_Group2();

		/**
		 * The meta object literal for the '<em><b>Role Set</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ROLE_SET_GROUPING__ROLE_SET = eINSTANCE.getRoleSetGrouping_RoleSet();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.SectionImpl <em>Section</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.SectionImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getSection()
		 * @generated
		 */
		EClass SECTION = eINSTANCE.getSection();

		/**
		 * The meta object literal for the '<em><b>Sub Section</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SECTION__SUB_SECTION = eINSTANCE.getSection_SubSection();

		/**
		 * The meta object literal for the '<em><b>Predecessor</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SECTION__PREDECESSOR = eINSTANCE.getSection_Predecessor();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SECTION__DESCRIPTION = eINSTANCE.getSection_Description();

		/**
		 * The meta object literal for the '<em><b>Section Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SECTION__SECTION_NAME = eINSTANCE.getSection_SectionName();

		/**
		 * The meta object literal for the '<em><b>Variability Based On Element</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SECTION__VARIABILITY_BASED_ON_ELEMENT = eINSTANCE.getSection_VariabilityBasedOnElement();

		/**
		 * The meta object literal for the '<em><b>Variability Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SECTION__VARIABILITY_TYPE = eINSTANCE.getSection_VariabilityType();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.SupportingMaterialImpl <em>Supporting Material</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.SupportingMaterialImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getSupportingMaterial()
		 * @generated
		 */
		EClass SUPPORTING_MATERIAL = eINSTANCE.getSupportingMaterial();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.TaskImpl <em>Task</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.TaskImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getTask()
		 * @generated
		 */
		EClass TASK = eINSTANCE.getTask();

		/**
		 * The meta object literal for the '<em><b>Precondition</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK__PRECONDITION = eINSTANCE.getTask_Precondition();

		/**
		 * The meta object literal for the '<em><b>Postcondition</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK__POSTCONDITION = eINSTANCE.getTask_Postcondition();

		/**
		 * The meta object literal for the '<em><b>Performed By</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK__PERFORMED_BY = eINSTANCE.getTask_PerformedBy();

		/**
		 * The meta object literal for the '<em><b>Group2</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK__GROUP2 = eINSTANCE.getTask_Group2();

		/**
		 * The meta object literal for the '<em><b>Mandatory Input</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK__MANDATORY_INPUT = eINSTANCE.getTask_MandatoryInput();

		/**
		 * The meta object literal for the '<em><b>Output</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK__OUTPUT = eINSTANCE.getTask_Output();

		/**
		 * The meta object literal for the '<em><b>Additionally Performed By</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK__ADDITIONALLY_PERFORMED_BY = eINSTANCE.getTask_AdditionallyPerformedBy();

		/**
		 * The meta object literal for the '<em><b>Optional Input</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK__OPTIONAL_INPUT = eINSTANCE.getTask_OptionalInput();

		/**
		 * The meta object literal for the '<em><b>Estimate</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK__ESTIMATE = eINSTANCE.getTask_Estimate();

		/**
		 * The meta object literal for the '<em><b>Estimation Considerations</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK__ESTIMATION_CONSIDERATIONS = eINSTANCE.getTask_EstimationConsiderations();

		/**
		 * The meta object literal for the '<em><b>Tool Mentor</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK__TOOL_MENTOR = eINSTANCE.getTask_ToolMentor();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.TaskDescriptionImpl <em>Task Description</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.TaskDescriptionImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getTaskDescription()
		 * @generated
		 */
		EClass TASK_DESCRIPTION = eINSTANCE.getTaskDescription();

		/**
		 * The meta object literal for the '<em><b>Alternatives</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK_DESCRIPTION__ALTERNATIVES = eINSTANCE.getTaskDescription_Alternatives();

		/**
		 * The meta object literal for the '<em><b>Purpose</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK_DESCRIPTION__PURPOSE = eINSTANCE.getTaskDescription_Purpose();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.TaskDescriptorImpl <em>Task Descriptor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.TaskDescriptorImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getTaskDescriptor()
		 * @generated
		 */
		EClass TASK_DESCRIPTOR = eINSTANCE.getTaskDescriptor();

		/**
		 * The meta object literal for the '<em><b>Task</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK_DESCRIPTOR__TASK = eINSTANCE.getTaskDescriptor_Task();

		/**
		 * The meta object literal for the '<em><b>Performed Primarily By</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK_DESCRIPTOR__PERFORMED_PRIMARILY_BY = eINSTANCE.getTaskDescriptor_PerformedPrimarilyBy();

		/**
		 * The meta object literal for the '<em><b>Group3</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK_DESCRIPTOR__GROUP3 = eINSTANCE.getTaskDescriptor_Group3();

		/**
		 * The meta object literal for the '<em><b>Additionally Performed By</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK_DESCRIPTOR__ADDITIONALLY_PERFORMED_BY = eINSTANCE.getTaskDescriptor_AdditionallyPerformedBy();

		/**
		 * The meta object literal for the '<em><b>Assisted By</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK_DESCRIPTOR__ASSISTED_BY = eINSTANCE.getTaskDescriptor_AssistedBy();

		/**
		 * The meta object literal for the '<em><b>External Input</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK_DESCRIPTOR__EXTERNAL_INPUT = eINSTANCE.getTaskDescriptor_ExternalInput();

		/**
		 * The meta object literal for the '<em><b>Mandatory Input</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK_DESCRIPTOR__MANDATORY_INPUT = eINSTANCE.getTaskDescriptor_MandatoryInput();

		/**
		 * The meta object literal for the '<em><b>Optional Input</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK_DESCRIPTOR__OPTIONAL_INPUT = eINSTANCE.getTaskDescriptor_OptionalInput();

		/**
		 * The meta object literal for the '<em><b>Output</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK_DESCRIPTOR__OUTPUT = eINSTANCE.getTaskDescriptor_Output();

		/**
		 * The meta object literal for the '<em><b>Step</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TASK_DESCRIPTOR__STEP = eINSTANCE.getTaskDescriptor_Step();

		/**
		 * The meta object literal for the '<em><b>Is Synchronized With Source</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK_DESCRIPTOR__IS_SYNCHRONIZED_WITH_SOURCE = eINSTANCE.getTaskDescriptor_IsSynchronizedWithSource();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.TeamProfileImpl <em>Team Profile</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.TeamProfileImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getTeamProfile()
		 * @generated
		 */
		EClass TEAM_PROFILE = eINSTANCE.getTeamProfile();

		/**
		 * The meta object literal for the '<em><b>Group2</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEAM_PROFILE__GROUP2 = eINSTANCE.getTeamProfile_Group2();

		/**
		 * The meta object literal for the '<em><b>Role</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEAM_PROFILE__ROLE = eINSTANCE.getTeamProfile_Role();

		/**
		 * The meta object literal for the '<em><b>Super Team</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEAM_PROFILE__SUPER_TEAM = eINSTANCE.getTeamProfile_SuperTeam();

		/**
		 * The meta object literal for the '<em><b>Sub Team</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEAM_PROFILE__SUB_TEAM = eINSTANCE.getTeamProfile_SubTeam();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.TemplateImpl <em>Template</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.TemplateImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getTemplate()
		 * @generated
		 */
		EClass TEMPLATE = eINSTANCE.getTemplate();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.TermDefinitionImpl <em>Term Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.TermDefinitionImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getTermDefinition()
		 * @generated
		 */
		EClass TERM_DEFINITION = eINSTANCE.getTermDefinition();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.ToolImpl <em>Tool</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.ToolImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getTool()
		 * @generated
		 */
		EClass TOOL = eINSTANCE.getTool();

		/**
		 * The meta object literal for the '<em><b>Group2</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TOOL__GROUP2 = eINSTANCE.getTool_Group2();

		/**
		 * The meta object literal for the '<em><b>Tool Mentor</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TOOL__TOOL_MENTOR = eINSTANCE.getTool_ToolMentor();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.ToolMentorImpl <em>Tool Mentor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.ToolMentorImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getToolMentor()
		 * @generated
		 */
		EClass TOOL_MENTOR = eINSTANCE.getToolMentor();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.WhitepaperImpl <em>Whitepaper</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.WhitepaperImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getWhitepaper()
		 * @generated
		 */
		EClass WHITEPAPER = eINSTANCE.getWhitepaper();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.WorkBreakdownElementImpl <em>Work Breakdown Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.WorkBreakdownElementImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getWorkBreakdownElement()
		 * @generated
		 */
		EClass WORK_BREAKDOWN_ELEMENT = eINSTANCE.getWorkBreakdownElement();

		/**
		 * The meta object literal for the '<em><b>Group2</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_BREAKDOWN_ELEMENT__GROUP2 = eINSTANCE.getWorkBreakdownElement_Group2();

		/**
		 * The meta object literal for the '<em><b>Predecessor</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORK_BREAKDOWN_ELEMENT__PREDECESSOR = eINSTANCE.getWorkBreakdownElement_Predecessor();

		/**
		 * The meta object literal for the '<em><b>Is Event Driven</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_BREAKDOWN_ELEMENT__IS_EVENT_DRIVEN = eINSTANCE.getWorkBreakdownElement_IsEventDriven();

		/**
		 * The meta object literal for the '<em><b>Is Ongoing</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_BREAKDOWN_ELEMENT__IS_ONGOING = eINSTANCE.getWorkBreakdownElement_IsOngoing();

		/**
		 * The meta object literal for the '<em><b>Is Repeatable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_BREAKDOWN_ELEMENT__IS_REPEATABLE = eINSTANCE.getWorkBreakdownElement_IsRepeatable();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.WorkDefinitionImpl <em>Work Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.WorkDefinitionImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getWorkDefinition()
		 * @generated
		 */
		EClass WORK_DEFINITION = eINSTANCE.getWorkDefinition();

		/**
		 * The meta object literal for the '<em><b>Precondition</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_DEFINITION__PRECONDITION = eINSTANCE.getWorkDefinition_Precondition();

		/**
		 * The meta object literal for the '<em><b>Postcondition</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_DEFINITION__POSTCONDITION = eINSTANCE.getWorkDefinition_Postcondition();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.WorkOrderImpl <em>Work Order</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.WorkOrderImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getWorkOrder()
		 * @generated
		 */
		EClass WORK_ORDER = eINSTANCE.getWorkOrder();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_ORDER__VALUE = eINSTANCE.getWorkOrder_Value();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_ORDER__ID = eINSTANCE.getWorkOrder_Id();

		/**
		 * The meta object literal for the '<em><b>Link Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_ORDER__LINK_TYPE = eINSTANCE.getWorkOrder_LinkType();

		/**
		 * The meta object literal for the '<em><b>Properties</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_ORDER__PROPERTIES = eINSTANCE.getWorkOrder_Properties();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.WorkProductImpl <em>Work Product</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.WorkProductImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getWorkProduct()
		 * @generated
		 */
		EClass WORK_PRODUCT = eINSTANCE.getWorkProduct();

		/**
		 * The meta object literal for the '<em><b>Group2</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_PRODUCT__GROUP2 = eINSTANCE.getWorkProduct_Group2();

		/**
		 * The meta object literal for the '<em><b>Estimate</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_PRODUCT__ESTIMATE = eINSTANCE.getWorkProduct_Estimate();

		/**
		 * The meta object literal for the '<em><b>Estimation Considerations</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_PRODUCT__ESTIMATION_CONSIDERATIONS = eINSTANCE.getWorkProduct_EstimationConsiderations();

		/**
		 * The meta object literal for the '<em><b>Report</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_PRODUCT__REPORT = eINSTANCE.getWorkProduct_Report();

		/**
		 * The meta object literal for the '<em><b>Template</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_PRODUCT__TEMPLATE = eINSTANCE.getWorkProduct_Template();

		/**
		 * The meta object literal for the '<em><b>Tool Mentor</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_PRODUCT__TOOL_MENTOR = eINSTANCE.getWorkProduct_ToolMentor();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.WorkProductDescriptionImpl <em>Work Product Description</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.WorkProductDescriptionImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getWorkProductDescription()
		 * @generated
		 */
		EClass WORK_PRODUCT_DESCRIPTION = eINSTANCE.getWorkProductDescription();

		/**
		 * The meta object literal for the '<em><b>Impact Of Not Having</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_PRODUCT_DESCRIPTION__IMPACT_OF_NOT_HAVING = eINSTANCE.getWorkProductDescription_ImpactOfNotHaving();

		/**
		 * The meta object literal for the '<em><b>Purpose</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_PRODUCT_DESCRIPTION__PURPOSE = eINSTANCE.getWorkProductDescription_Purpose();

		/**
		 * The meta object literal for the '<em><b>Reasons For Not Needing</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_PRODUCT_DESCRIPTION__REASONS_FOR_NOT_NEEDING = eINSTANCE.getWorkProductDescription_ReasonsForNotNeeding();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.WorkProductDescriptorImpl <em>Work Product Descriptor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.WorkProductDescriptorImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getWorkProductDescriptor()
		 * @generated
		 */
		EClass WORK_PRODUCT_DESCRIPTOR = eINSTANCE.getWorkProductDescriptor();

		/**
		 * The meta object literal for the '<em><b>Work Product</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_PRODUCT_DESCRIPTOR__WORK_PRODUCT = eINSTANCE.getWorkProductDescriptor_WorkProduct();

		/**
		 * The meta object literal for the '<em><b>Responsible Role</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_PRODUCT_DESCRIPTOR__RESPONSIBLE_ROLE = eINSTANCE.getWorkProductDescriptor_ResponsibleRole();

		/**
		 * The meta object literal for the '<em><b>Group2</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_PRODUCT_DESCRIPTOR__GROUP2 = eINSTANCE.getWorkProductDescriptor_Group2();

		/**
		 * The meta object literal for the '<em><b>External Input To</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_PRODUCT_DESCRIPTOR__EXTERNAL_INPUT_TO = eINSTANCE.getWorkProductDescriptor_ExternalInputTo();

		/**
		 * The meta object literal for the '<em><b>Impacted By</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_PRODUCT_DESCRIPTOR__IMPACTED_BY = eINSTANCE.getWorkProductDescriptor_ImpactedBy();

		/**
		 * The meta object literal for the '<em><b>Impacts</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_PRODUCT_DESCRIPTOR__IMPACTS = eINSTANCE.getWorkProductDescriptor_Impacts();

		/**
		 * The meta object literal for the '<em><b>Mandatory Input To</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_PRODUCT_DESCRIPTOR__MANDATORY_INPUT_TO = eINSTANCE.getWorkProductDescriptor_MandatoryInputTo();

		/**
		 * The meta object literal for the '<em><b>Optional Input To</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_PRODUCT_DESCRIPTOR__OPTIONAL_INPUT_TO = eINSTANCE.getWorkProductDescriptor_OptionalInputTo();

		/**
		 * The meta object literal for the '<em><b>Output From</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_PRODUCT_DESCRIPTOR__OUTPUT_FROM = eINSTANCE.getWorkProductDescriptor_OutputFrom();

		/**
		 * The meta object literal for the '<em><b>Deliverable Parts</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_PRODUCT_DESCRIPTOR__DELIVERABLE_PARTS = eINSTANCE.getWorkProductDescriptor_DeliverableParts();

		/**
		 * The meta object literal for the '<em><b>Activity Entry State</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_PRODUCT_DESCRIPTOR__ACTIVITY_ENTRY_STATE = eINSTANCE.getWorkProductDescriptor_ActivityEntryState();

		/**
		 * The meta object literal for the '<em><b>Activity Exit State</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_PRODUCT_DESCRIPTOR__ACTIVITY_EXIT_STATE = eINSTANCE.getWorkProductDescriptor_ActivityExitState();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.impl.WorkProductTypeImpl <em>Work Product Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.impl.WorkProductTypeImpl
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getWorkProductType()
		 * @generated
		 */
		EClass WORK_PRODUCT_TYPE = eINSTANCE.getWorkProductType();

		/**
		 * The meta object literal for the '<em><b>Group2</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_PRODUCT_TYPE__GROUP2 = eINSTANCE.getWorkProductType_Group2();

		/**
		 * The meta object literal for the '<em><b>Work Product</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_PRODUCT_TYPE__WORK_PRODUCT = eINSTANCE.getWorkProductType_WorkProduct();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.VariabilityType <em>Variability Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.VariabilityType
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getVariabilityType()
		 * @generated
		 */
		EEnum VARIABILITY_TYPE = eINSTANCE.getVariabilityType();

		/**
		 * The meta object literal for the '{@link org.eclipse.epf.xml.uma.WorkOrderType <em>Work Order Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.WorkOrderType
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getWorkOrderType()
		 * @generated
		 */
		EEnum WORK_ORDER_TYPE = eINSTANCE.getWorkOrderType();

		/**
		 * The meta object literal for the '<em>Variability Type Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.VariabilityType
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getVariabilityTypeObject()
		 * @generated
		 */
		EDataType VARIABILITY_TYPE_OBJECT = eINSTANCE.getVariabilityTypeObject();

		/**
		 * The meta object literal for the '<em>Work Order Type Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epf.xml.uma.WorkOrderType
		 * @see org.eclipse.epf.xml.uma.impl.UmaPackageImpl#getWorkOrderTypeObject()
		 * @generated
		 */
		EDataType WORK_ORDER_TYPE_OBJECT = eINSTANCE.getWorkOrderTypeObject();

	}

} //UmaPackage
