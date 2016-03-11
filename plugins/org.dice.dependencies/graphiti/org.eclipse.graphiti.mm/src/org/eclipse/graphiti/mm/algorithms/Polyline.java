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
package org.eclipse.graphiti.mm.algorithms;

import org.eclipse.emf.common.util.EList;

import org.eclipse.graphiti.mm.algorithms.styles.Point;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Polyline</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.Polyline#getPoints <em>Points</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.graphiti.mm.algorithms.AlgorithmsPackage#getPolyline()
 * @model
 * @generated
 */
public interface Polyline extends GraphicsAlgorithm {
	/**
	 * Returns the value of the '<em><b>Points</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.graphiti.mm.algorithms.styles.Point}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Points</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Points</em>' containment reference list.
	 * @see org.eclipse.graphiti.mm.algorithms.AlgorithmsPackage#getPolyline_Points()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<Point> getPoints();

} // Polyline
