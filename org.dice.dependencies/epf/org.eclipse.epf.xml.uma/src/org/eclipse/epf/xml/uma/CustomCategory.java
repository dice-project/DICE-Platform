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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Custom Category</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A category introduced by a method content author to structure any number of method Content Elements of any subtype based on user-defined criteria.  Because Content Categories (and therefore Custom Categories, too) are Content Elements themselves, Custom Categories can be used to recursively categorize Content Categories as well.  Custom Categories can also be nested with any Content Category.  Custom categories can be used to categorize content based on the user's criteria as well as to define whole tree-structures of nested categories allowing the user to systematically navigate and browse method content and processes based on these categories.  For example, one could create a custom category to logically organize content relevant for the user's development organization departments; e.g. a "Testing" category that groups together all roles, work products, tasks, and guidance element relevant to testing.  Another example would be categories that express licensing levels of the content grouping freely distributable method content versus content that represent intellectual property and requires a license to be purchased to be able to use it.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.xml.uma.CustomCategory#getGroup2 <em>Group2</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.CustomCategory#getCategorizedElement <em>Categorized Element</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.CustomCategory#getSubCategory <em>Sub Category</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.xml.uma.UmaPackage#getCustomCategory()
 * @model extendedMetaData="name='CustomCategory' kind='elementOnly'"
 * @generated
 */
public interface CustomCategory extends ContentCategory {
	/**
	 * Returns the value of the '<em><b>Group2</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Group2</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Group2</em>' attribute list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getCustomCategory_Group2()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='group:24'"
	 * @generated
	 */
	FeatureMap getGroup2();

	/**
	 * Returns the value of the '<em><b>Categorized Element</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Categorized Element</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Categorized Element</em>' attribute list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getCustomCategory_CategorizedElement()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='CategorizedElement' group='#group:24'"
	 * @generated
	 */
	EList<String> getCategorizedElement();

	/**
	 * Returns the value of the '<em><b>Sub Category</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub Category</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sub Category</em>' attribute list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getCustomCategory_SubCategory()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='SubCategory' group='#group:24'"
	 * @generated
	 */
	EList<String> getSubCategory();

} // CustomCategory
