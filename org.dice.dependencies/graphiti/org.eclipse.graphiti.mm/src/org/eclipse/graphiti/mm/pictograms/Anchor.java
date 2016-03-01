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

import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Anchor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.Anchor#getParent <em>Parent</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.Anchor#getOutgoingConnections <em>Outgoing Connections</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.Anchor#getIncomingConnections <em>Incoming Connections</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.Anchor#getReferencedGraphicsAlgorithm <em>Referenced Graphics Algorithm</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.graphiti.mm.pictograms.PictogramsPackage#getAnchor()
 * @model abstract="true"
 * @generated
 */
public interface Anchor extends PictogramElement {
	/**
	 * Returns the value of the '<em><b>Parent</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.graphiti.mm.pictograms.AnchorContainer#getAnchors <em>Anchors</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent</em>' container reference.
	 * @see #setParent(AnchorContainer)
	 * @see org.eclipse.graphiti.mm.pictograms.PictogramsPackage#getAnchor_Parent()
	 * @see org.eclipse.graphiti.mm.pictograms.AnchorContainer#getAnchors
	 * @model opposite="anchors" required="true" transient="false" ordered="false"
	 * @generated
	 */
	AnchorContainer getParent();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.pictograms.Anchor#getParent <em>Parent</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent</em>' container reference.
	 * @see #getParent()
	 * @generated
	 */
	void setParent(AnchorContainer value);

	/**
	 * Returns the value of the '<em><b>Outgoing Connections</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.graphiti.mm.pictograms.Connection}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.graphiti.mm.pictograms.Connection#getStart <em>Start</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Outgoing Connections</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Outgoing Connections</em>' reference list.
	 * @see org.eclipse.graphiti.mm.pictograms.PictogramsPackage#getAnchor_OutgoingConnections()
	 * @see org.eclipse.graphiti.mm.pictograms.Connection#getStart
	 * @model opposite="start" ordered="false"
	 * @generated
	 */
	EList<Connection> getOutgoingConnections();

	/**
	 * Returns the value of the '<em><b>Incoming Connections</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.graphiti.mm.pictograms.Connection}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.graphiti.mm.pictograms.Connection#getEnd <em>End</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Incoming Connections</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Incoming Connections</em>' reference list.
	 * @see org.eclipse.graphiti.mm.pictograms.PictogramsPackage#getAnchor_IncomingConnections()
	 * @see org.eclipse.graphiti.mm.pictograms.Connection#getEnd
	 * @model opposite="end" ordered="false"
	 * @generated
	 */
	EList<Connection> getIncomingConnections();

	/**
	 * Returns the value of the '<em><b>Referenced Graphics Algorithm</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Referenced Graphics Algorithm</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Referenced Graphics Algorithm</em>' reference.
	 * @see #setReferencedGraphicsAlgorithm(GraphicsAlgorithm)
	 * @see org.eclipse.graphiti.mm.pictograms.PictogramsPackage#getAnchor_ReferencedGraphicsAlgorithm()
	 * @model ordered="false"
	 * @generated
	 */
	GraphicsAlgorithm getReferencedGraphicsAlgorithm();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.pictograms.Anchor#getReferencedGraphicsAlgorithm <em>Referenced Graphics Algorithm</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Referenced Graphics Algorithm</em>' reference.
	 * @see #getReferencedGraphicsAlgorithm()
	 * @generated
	 */
	void setReferencedGraphicsAlgorithm(GraphicsAlgorithm value);

} // Anchor
