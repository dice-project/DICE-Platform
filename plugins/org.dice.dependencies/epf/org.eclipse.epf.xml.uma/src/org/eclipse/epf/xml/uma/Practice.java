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
 * A representation of the model object '<em><b>Practice</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A specific type of guidance that represents a proven way or strategy of doing work to achieve a goal that has a positive impact on work product or process quality.  Practices are defined orthogonal to methods and processes.  They could summarize aspects that impact many different parts of a method or specific processes.  Examples for practices would be "Manage Risks", "Continuously verify quality", "Architecture-centric and component-based development", etc.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.xml.uma.Practice#getGroup2 <em>Group2</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.Practice#getActivityReference <em>Activity Reference</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.Practice#getContentReference <em>Content Reference</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.Practice#getSubPractice <em>Sub Practice</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.xml.uma.UmaPackage#getPractice()
 * @model extendedMetaData="name='Practice' kind='elementOnly'"
 * @generated
 */
public interface Practice extends Guidance {
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
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getPractice_Group2()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='group:24'"
	 * @generated
	 */
	FeatureMap getGroup2();

	/**
	 * Returns the value of the '<em><b>Activity Reference</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Activity Reference</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Activity Reference</em>' attribute list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getPractice_ActivityReference()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='ActivityReference' group='#group:24'"
	 * @generated
	 */
	EList<String> getActivityReference();

	/**
	 * Returns the value of the '<em><b>Content Reference</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Content Reference</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Content Reference</em>' attribute list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getPractice_ContentReference()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='ContentReference' group='#group:24'"
	 * @generated
	 */
	EList<String> getContentReference();

	/**
	 * Returns the value of the '<em><b>Sub Practice</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.epf.xml.uma.Practice}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub Practice</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sub Practice</em>' containment reference list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getPractice_SubPractice()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='SubPractice' group='#group:24'"
	 * @generated
	 */
	EList<Practice> getSubPractice();

} // Practice
