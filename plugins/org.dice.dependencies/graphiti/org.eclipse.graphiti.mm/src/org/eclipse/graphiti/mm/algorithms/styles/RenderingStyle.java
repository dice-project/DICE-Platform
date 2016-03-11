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
 * A representation of the model object '<em><b>Rendering Style</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.styles.RenderingStyle#getAdaptedGradientColoredAreas <em>Adapted Gradient Colored Areas</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.graphiti.mm.algorithms.styles.StylesPackage#getRenderingStyle()
 * @model
 * @generated
 */
public interface RenderingStyle extends EObject {
	/**
	 * Returns the value of the '<em><b>Adapted Gradient Colored Areas</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Adapted Gradient Colored Areas</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Adapted Gradient Colored Areas</em>' containment reference.
	 * @see #setAdaptedGradientColoredAreas(AdaptedGradientColoredAreas)
	 * @see org.eclipse.graphiti.mm.algorithms.styles.StylesPackage#getRenderingStyle_AdaptedGradientColoredAreas()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	AdaptedGradientColoredAreas getAdaptedGradientColoredAreas();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.algorithms.styles.RenderingStyle#getAdaptedGradientColoredAreas <em>Adapted Gradient Colored Areas</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Adapted Gradient Colored Areas</em>' containment reference.
	 * @see #getAdaptedGradientColoredAreas()
	 * @generated
	 */
	void setAdaptedGradientColoredAreas(AdaptedGradientColoredAreas value);

} // RenderingStyle
