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
 * A representation of the model object '<em><b>Domain</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A refineable hierarchy grouping related work products.  In other words, Domains can be further divided into sub-domains, with work product elements to be categorized only at the leaf-level of this hierarchy.
 * Domain is a logical grouping of work products that have an affinity to each other based on resources, timing, or relationship.  A Domain may be divided into subdomains.  For example, GS Method uses six predefined Domains for Work Products: Application, Architecture, Business, Engagement, Operations and Organization.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.xml.uma.Domain#getGroup2 <em>Group2</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.Domain#getWorkProduct <em>Work Product</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.Domain#getSubdomain <em>Subdomain</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.xml.uma.UmaPackage#getDomain()
 * @model extendedMetaData="name='Domain' kind='elementOnly'"
 * @generated
 */
public interface Domain extends ContentCategory {
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
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getDomain_Group2()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='group:24'"
	 * @generated
	 */
	FeatureMap getGroup2();

	/**
	 * Returns the value of the '<em><b>Work Product</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Work Product</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Work Product</em>' attribute list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getDomain_WorkProduct()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='WorkProduct' group='#group:24'"
	 * @generated
	 */
	EList<String> getWorkProduct();

	/**
	 * Returns the value of the '<em><b>Subdomain</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.epf.xml.uma.Domain}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Subdomain</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Subdomain</em>' containment reference list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getDomain_Subdomain()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Subdomain' group='#group:24'"
	 * @generated
	 */
	EList<Domain> getSubdomain();

} // Domain
