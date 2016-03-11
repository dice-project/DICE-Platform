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
package org.eclipse.epf.diagram.model;

import org.eclipse.draw2d.AbsoluteBendpoint;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.util.EList;

/**
 * This interface represents model object for a link between diagram nodes
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 * @model
 */
public interface Link extends LinkedObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.epf.diagram.model.ModelPackage#getLink_Name()
	 * @model default=""
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.diagram.model.Link#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Gets the source node of this link.
	 * 
	 * @model
	 */
	Node getSource();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.diagram.model.Link#getSource <em>Source</em>}' container reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' container reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(Node value);

	/**
	 * Gets the target node of this link
	 * 
	 * @model
	 */
	Node getTarget();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.diagram.model.Link#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(Node value);

	/**
	 * Gets the list of bend points in this link
	 * 
	 * @model type="org.eclipse.draw2d.AbsoluteBendpoint" lower="0" transient="true"
	 */
	EList<AbsoluteBendpoint> getBendpoints();

	/**
	 * Gets the end point of this link in the source node
	 * 
	 * @model transient="true"
	 * @return
	 */
	Point getSourceEndPoint();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.diagram.model.Link#getSourceEndPoint <em>Source End Point</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source End Point</em>' attribute.
	 * @see #getSourceEndPoint()
	 * @generated
	 */
	void setSourceEndPoint(Point value);

	/**
	 * Gets the end point of this link in target node
	 * 
	 * @model transient="true"
	 * @return
	 */
	Point getTargetEndPoint();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.diagram.model.Link#getTargetEndPoint <em>Target End Point</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target End Point</em>' attribute.
	 * @see #getTargetEndPoint()
	 * @generated
	 */
	void setTargetEndPoint(Point value);

}