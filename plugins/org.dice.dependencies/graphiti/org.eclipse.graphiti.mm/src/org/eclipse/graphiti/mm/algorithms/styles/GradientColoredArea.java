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
package org.eclipse.graphiti.mm.algorithms.styles;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Gradient Colored Area</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.styles.GradientColoredArea#getStart <em>Start</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.styles.GradientColoredArea#getEnd <em>End</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.graphiti.mm.algorithms.styles.StylesPackage#getGradientColoredArea()
 * @model
 * @generated
 */
public interface GradientColoredArea extends EObject {
	/**
	 * Returns the value of the '<em><b>Start</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Start</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Start</em>' containment reference.
	 * @see #setStart(GradientColoredLocation)
	 * @see org.eclipse.graphiti.mm.algorithms.styles.StylesPackage#getGradientColoredArea_Start()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	GradientColoredLocation getStart();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.algorithms.styles.GradientColoredArea#getStart <em>Start</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Start</em>' containment reference.
	 * @see #getStart()
	 * @generated
	 */
	void setStart(GradientColoredLocation value);

	/**
	 * Returns the value of the '<em><b>End</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>End</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>End</em>' containment reference.
	 * @see #setEnd(GradientColoredLocation)
	 * @see org.eclipse.graphiti.mm.algorithms.styles.StylesPackage#getGradientColoredArea_End()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	GradientColoredLocation getEnd();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.algorithms.styles.GradientColoredArea#getEnd <em>End</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>End</em>' containment reference.
	 * @see #getEnd()
	 * @generated
	 */
	void setEnd(GradientColoredLocation value);

} // GradientColoredArea
