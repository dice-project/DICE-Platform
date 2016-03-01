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
package org.eclipse.epf.library.edit.util.model;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains
 * accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * 
 * @see org.eclipse.epf.library.edit.util.model.ModelFactory
 * @generated
 */
public interface ModelPackage extends EPackage {
	/**
	 * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNAME = "model"; //$NON-NLS-1$

	/**
	 * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_URI = "http:///com/ibm/uma/edit/tng/util/model.ecore"; //$NON-NLS-1$

	/**
	 * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_PREFIX = "org.eclipse.epf.uma.edit.tng.util.model"; //$NON-NLS-1$

	/**
	 * The singleton instance of the package. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	ModelPackage eINSTANCE = org.eclipse.epf.library.edit.util.model.impl.ModelPackageImpl
			.init();

	/**
	 * The meta object id for the '{@link org.eclipse.epf.library.edit.util.model.impl.OrderInfoImpl <em>Order Info</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.epf.library.edit.util.model.impl.OrderInfoImpl
	 * @see org.eclipse.epf.library.edit.util.model.impl.ModelPackageImpl#getOrderInfo()
	 * @generated
	 */
	int ORDER_INFO = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ORDER_INFO__NAME = 0;

	/**
	 * The feature id for the '<em><b>GUI Ds</b></em>' attribute list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ORDER_INFO__GUI_DS = 1;

	/**
	 * The feature id for the '<em><b>Timestamp</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ORDER_INFO__TIMESTAMP = 2;

	/**
	 * The number of structural features of the the '<em>Order Info</em>'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ORDER_INFO_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.library.edit.util.model.impl.OrderInfoCollectionImpl <em>Order Info Collection</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.epf.library.edit.util.model.impl.OrderInfoCollectionImpl
	 * @see org.eclipse.epf.library.edit.util.model.impl.ModelPackageImpl#getOrderInfoCollection()
	 * @generated
	 */
	int ORDER_INFO_COLLECTION = 1;

	/**
	 * The feature id for the '<em><b>Order Infos</b></em>' containment
	 * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ORDER_INFO_COLLECTION__ORDER_INFOS = 0;

	/**
	 * The number of structural features of the the '<em>Order Info Collection</em>'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ORDER_INFO_COLLECTION_FEATURE_COUNT = 1;

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.library.edit.util.model.OrderInfo <em>Order Info</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Order Info</em>'.
	 * @see org.eclipse.epf.library.edit.util.model.OrderInfo
	 * @generated
	 */
	EClass getOrderInfo();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.library.edit.util.model.OrderInfo#getName <em>Name</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.epf.library.edit.util.model.OrderInfo#getName()
	 * @see #getOrderInfo()
	 * @generated
	 */
	EAttribute getOrderInfo_Name();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.library.edit.util.model.OrderInfo#getGUIDs <em>GUI Ds</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute list '<em>GUI Ds</em>'.
	 * @see org.eclipse.epf.library.edit.util.model.OrderInfo#getGUIDs()
	 * @see #getOrderInfo()
	 * @generated
	 */
	EAttribute getOrderInfo_GUIDs();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.library.edit.util.model.OrderInfo#getTimestamp <em>Timestamp</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Timestamp</em>'.
	 * @see org.eclipse.epf.library.edit.util.model.OrderInfo#getTimestamp()
	 * @see #getOrderInfo()
	 * @generated
	 */
	EAttribute getOrderInfo_Timestamp();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.library.edit.util.model.OrderInfoCollection <em>Order Info Collection</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Order Info Collection</em>'.
	 * @see org.eclipse.epf.library.edit.util.model.OrderInfoCollection
	 * @generated
	 */
	EClass getOrderInfoCollection();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.library.edit.util.model.OrderInfoCollection#getOrderInfos <em>Order Infos</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Order Infos</em>'.
	 * @see org.eclipse.epf.library.edit.util.model.OrderInfoCollection#getOrderInfos()
	 * @see #getOrderInfoCollection()
	 * @generated
	 */
	EReference getOrderInfoCollection_OrderInfos();

	/**
	 * Returns the factory that creates the instances of the model. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ModelFactory getModelFactory();

} // ModelPackage
