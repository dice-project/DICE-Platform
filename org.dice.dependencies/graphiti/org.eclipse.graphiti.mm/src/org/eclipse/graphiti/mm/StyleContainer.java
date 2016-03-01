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
package org.eclipse.graphiti.mm;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.graphiti.mm.algorithms.styles.Style;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Style Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.graphiti.mm.StyleContainer#getStyles <em>Styles</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.graphiti.mm.MmPackage#getStyleContainer()
 * @model abstract="true"
 * @generated
 */
public interface StyleContainer extends EObject {
	/**
	 * Returns the value of the '<em><b>Styles</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.graphiti.mm.algorithms.styles.Style}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.graphiti.mm.algorithms.styles.Style#getStyleContainer <em>Style Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Styles</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Styles</em>' containment reference list.
	 * @see org.eclipse.graphiti.mm.MmPackage#getStyleContainer_Styles()
	 * @see org.eclipse.graphiti.mm.algorithms.styles.Style#getStyleContainer
	 * @model opposite="styleContainer" containment="true" resolveProxies="true" ordered="false"
	 * @generated
	 */
	EList<Style> getStyles();

} // StyleContainer
