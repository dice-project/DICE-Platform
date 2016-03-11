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
 * A representation of the model object '<em><b>Graph Connector</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.GraphConnector#getGraphEdge <em>Graph Edge</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.GraphConnector#getGraphElement <em>Graph Element</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.uma.UmaPackage#getGraphConnector()
 * @model
 * @generated
 */
public interface GraphConnector extends GraphElement {
	/**
	 * Returns the value of the '<em><b>Graph Element</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.epf.uma.GraphElement#getAnchorage <em>Anchorage</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Graph Element</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Graph Element</em>' container reference.
	 * @see #setGraphElement(GraphElement)
	 * @see org.eclipse.epf.uma.UmaPackage#getGraphConnector_GraphElement()
	 * @see org.eclipse.epf.uma.GraphElement#getAnchorage
	 * @model opposite="anchorage" required="true" transient="false" ordered="false"
	 * @generated
	 */
	GraphElement getGraphElement();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.GraphConnector#getGraphElement <em>Graph Element</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Graph Element</em>' container reference.
	 * @see #getGraphElement()
	 * @generated
	 */
	void setGraphElement(GraphElement value);

	/**
	 * Returns the value of the '<em><b>Graph Edge</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.GraphEdge}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.epf.uma.GraphEdge#getAnchor <em>Anchor</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Graph Edge</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Graph Edge</em>' reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getGraphConnector_GraphEdge()
	 * @see org.eclipse.epf.uma.GraphEdge#getAnchor
	 * @model opposite="anchor" ordered="false"
	 * @generated
	 */
	List<GraphEdge> getGraphEdge();

} // GraphConnector
