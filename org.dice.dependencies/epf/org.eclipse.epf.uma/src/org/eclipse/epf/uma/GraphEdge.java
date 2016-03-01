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
package org.eclipse.epf.uma;

import java.util.List;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Graph Edge</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.GraphEdge#getWaypoints <em>Waypoints</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.GraphEdge#getAnchor <em>Anchor</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.uma.UmaPackage#getGraphEdge()
 * @model
 * @generated
 */
public interface GraphEdge extends GraphElement {
	/**
	 * Returns the value of the '<em><b>Anchor</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.GraphConnector}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.epf.uma.GraphConnector#getGraphEdge <em>Graph Edge</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Anchor</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Anchor</em>' reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getGraphEdge_Anchor()
	 * @see org.eclipse.epf.uma.GraphConnector#getGraphEdge
	 * @model opposite="graphEdge" lower="2" upper="2" ordered="false"
	 * @generated
	 */
	List<GraphConnector> getAnchor();

	/**
	 * Returns the value of the '<em><b>Waypoints</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.Point}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Waypoints</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Waypoints</em>' containment reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getGraphEdge_Waypoints()
	 * @model containment="true" resolveProxies="true" lower="2" ordered="false"
	 * @generated
	 */
	List<Point> getWaypoints();

} // GraphEdge
