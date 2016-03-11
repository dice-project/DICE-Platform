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
 * A representation of the model object '<em><b>Diagram</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.Diagram#getDiagramLink <em>Diagram Link</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.Diagram#getNamespace <em>Namespace</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.Diagram#getZoom <em>Zoom</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.Diagram#getViewpoint <em>Viewpoint</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.uma.UmaPackage#getDiagram()
 * @model
 * @generated
 */
public interface Diagram extends GraphNode {
	/**
	 * Returns the value of the '<em><b>Zoom</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Zoom</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Zoom</em>' attribute.
	 * @see #setZoom(Double)
	 * @see org.eclipse.epf.uma.UmaPackage#getDiagram_Zoom()
	 * @model dataType="org.eclipse.epf.uma.Double" ordered="false"
	 * @generated
	 */
	Double getZoom();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.Diagram#getZoom <em>Zoom</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Zoom</em>' attribute.
	 * @see #getZoom()
	 * @generated
	 */
	void setZoom(Double value);

	/**
	 * Returns the value of the '<em><b>Viewpoint</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Viewpoint</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Viewpoint</em>' reference.
	 * @see #setViewpoint(Point)
	 * @see org.eclipse.epf.uma.UmaPackage#getDiagram_Viewpoint()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Point getViewpoint();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.Diagram#getViewpoint <em>Viewpoint</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Viewpoint</em>' reference.
	 * @see #getViewpoint()
	 * @generated
	 */
	void setViewpoint(Point value);

	/**
	 * Returns the value of the '<em><b>Diagram Link</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epf.uma.DiagramLink}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.epf.uma.DiagramLink#getDiagram <em>Diagram</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Diagram Link</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Diagram Link</em>' reference list.
	 * @see org.eclipse.epf.uma.UmaPackage#getDiagram_DiagramLink()
	 * @see org.eclipse.epf.uma.DiagramLink#getDiagram
	 * @model opposite="diagram" ordered="false"
	 * @generated
	 */
	List<DiagramLink> getDiagramLink();

	/**
	 * Returns the value of the '<em><b>Namespace</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.epf.uma.SemanticModelBridge#getDiagram <em>Diagram</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Namespace</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Namespace</em>' containment reference.
	 * @see #setNamespace(SemanticModelBridge)
	 * @see org.eclipse.epf.uma.UmaPackage#getDiagram_Namespace()
	 * @see org.eclipse.epf.uma.SemanticModelBridge#getDiagram
	 * @model opposite="diagram" containment="true" resolveProxies="true" required="true" ordered="false"
	 * @generated
	 */
	SemanticModelBridge getNamespace();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.Diagram#getNamespace <em>Namespace</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Namespace</em>' containment reference.
	 * @see #getNamespace()
	 * @generated
	 */
	void setNamespace(SemanticModelBridge value);

} // Diagram
