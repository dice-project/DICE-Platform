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

import org.eclipse.graphiti.mm.algorithms.styles.Point;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Fix Point Anchor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.FixPointAnchor#getLocation <em>Location</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.graphiti.mm.pictograms.PictogramsPackage#getFixPointAnchor()
 * @model
 * @generated
 */
public interface FixPointAnchor extends Anchor {
	/**
	 * Returns the value of the '<em><b>Location</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Location</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Location</em>' containment reference.
	 * @see #setLocation(Point)
	 * @see org.eclipse.graphiti.mm.pictograms.PictogramsPackage#getFixPointAnchor_Location()
	 * @model containment="true" resolveProxies="true" required="true" ordered="false"
	 * @generated
	 */
	Point getLocation();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.pictograms.FixPointAnchor#getLocation <em>Location</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Location</em>' containment reference.
	 * @see #getLocation()
	 * @generated
	 */
	void setLocation(Point value);

} // FixPointAnchor
