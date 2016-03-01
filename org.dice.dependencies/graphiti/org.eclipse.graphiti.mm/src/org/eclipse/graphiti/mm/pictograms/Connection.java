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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Connection</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.Connection#getStart <em>Start</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.Connection#getEnd <em>End</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.Connection#getParent <em>Parent</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.Connection#getConnectionDecorators <em>Connection Decorators</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.graphiti.mm.pictograms.PictogramsPackage#getConnection()
 * @model
 * @generated
 */
public interface Connection extends AnchorContainer {
	/**
	 * Returns the value of the '<em><b>Start</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.graphiti.mm.pictograms.Anchor#getOutgoingConnections <em>Outgoing Connections</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Start</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Start</em>' reference.
	 * @see #setStart(Anchor)
	 * @see org.eclipse.graphiti.mm.pictograms.PictogramsPackage#getConnection_Start()
	 * @see org.eclipse.graphiti.mm.pictograms.Anchor#getOutgoingConnections
	 * @model opposite="outgoingConnections" required="true" ordered="false"
	 * @generated
	 */
	Anchor getStart();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.pictograms.Connection#getStart <em>Start</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Start</em>' reference.
	 * @see #getStart()
	 * @generated
	 */
	void setStart(Anchor value);

	/**
	 * Returns the value of the '<em><b>End</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.graphiti.mm.pictograms.Anchor#getIncomingConnections <em>Incoming Connections</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>End</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>End</em>' reference.
	 * @see #setEnd(Anchor)
	 * @see org.eclipse.graphiti.mm.pictograms.PictogramsPackage#getConnection_End()
	 * @see org.eclipse.graphiti.mm.pictograms.Anchor#getIncomingConnections
	 * @model opposite="incomingConnections" required="true" ordered="false"
	 * @generated
	 */
	Anchor getEnd();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.pictograms.Connection#getEnd <em>End</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>End</em>' reference.
	 * @see #getEnd()
	 * @generated
	 */
	void setEnd(Anchor value);

	/**
	 * Returns the value of the '<em><b>Parent</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.graphiti.mm.pictograms.Diagram#getConnections <em>Connections</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent</em>' container reference.
	 * @see #setParent(Diagram)
	 * @see org.eclipse.graphiti.mm.pictograms.PictogramsPackage#getConnection_Parent()
	 * @see org.eclipse.graphiti.mm.pictograms.Diagram#getConnections
	 * @model opposite="connections" required="true" transient="false" ordered="false"
	 * @generated
	 */
	Diagram getParent();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.pictograms.Connection#getParent <em>Parent</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent</em>' container reference.
	 * @see #getParent()
	 * @generated
	 */
	void setParent(Diagram value);

	/**
	 * Returns the value of the '<em><b>Connection Decorators</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.graphiti.mm.pictograms.ConnectionDecorator}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.graphiti.mm.pictograms.ConnectionDecorator#getConnection <em>Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Connection Decorators</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Connection Decorators</em>' containment reference list.
	 * @see org.eclipse.graphiti.mm.pictograms.PictogramsPackage#getConnection_ConnectionDecorators()
	 * @see org.eclipse.graphiti.mm.pictograms.ConnectionDecorator#getConnection
	 * @model opposite="connection" containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<ConnectionDecorator> getConnectionDecorators();

} // Connection
