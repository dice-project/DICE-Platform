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
 * A representation of the model object '<em><b>Polyline</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.Polyline#getClosed <em>Closed</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.Polyline#getWaypoints <em>Waypoints</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.uma.UmaPackage#getPolyline()
 * @model
 * @generated
 */
public interface Polyline extends GraphicPrimitive {
	/**
	 * Returns the value of the '<em><b>Closed</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Closed</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Closed</em>' attribute.
	 * @see #setClosed(Boolean)
	 * @see org.eclipse.epf.uma.UmaPackage#getPolyline_Closed()
	 * @model default="true" dataType="org.eclipse.epf.uma.Boolean" required="true" ordered="false"
	 * @generated
	 */
	Boolean getClosed();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.Polyline#getClosed <em>Closed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Closed</em>' attribute.
	 * @see #getClosed()
	 * @generated
	 */
	void setClosed(Boolean value);

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
	 * @see org.eclipse.epf.uma.UmaPackage#getPolyline_Waypoints()
	 * @model containment="true" resolveProxies="true" lower="2" ordered="false"
	 * @generated
	 */
	List<Point> getWaypoints();

} // Polyline
