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
package org.eclipse.graphiti.mm.pictograms;

import org.eclipse.emf.common.util.EList;

import org.eclipse.graphiti.mm.algorithms.styles.Point;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Free Form Connection</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.FreeFormConnection#getBendpoints <em>Bendpoints</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.graphiti.mm.pictograms.PictogramsPackage#getFreeFormConnection()
 * @model
 * @generated
 */
public interface FreeFormConnection extends Connection {
	/**
	 * Returns the value of the '<em><b>Bendpoints</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.graphiti.mm.algorithms.styles.Point}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bendpoints</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Bendpoints</em>' containment reference list.
	 * @see org.eclipse.graphiti.mm.pictograms.PictogramsPackage#getFreeFormConnection_Bendpoints()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<Point> getBendpoints();

} // FreeFormConnection
