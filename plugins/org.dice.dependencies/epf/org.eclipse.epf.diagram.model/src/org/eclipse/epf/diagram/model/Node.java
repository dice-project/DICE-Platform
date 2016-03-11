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

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.EList;
import org.eclipse.epf.uma.GraphNode;

/**
 * The diagram node
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 * @model abstract="true"
 */
public interface Node extends LinkedObject {

	/**
	 * Gets location of this node
	 * 
	 * @model transient="true"
	 */
	Point getLocation();

	/**
	 * Gets width
	 * 
	 * @model default="-1" transient="true"
	 */
	int getWidth();

	/**
	 * Gets height
	 * 
	 * @return the height
	 * @model default="-1" transient="true"
	 */
	int getHeight();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.diagram.model.Node#getHeight <em>Height</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Height</em>' attribute.
	 * @see #getHeight()
	 * @generated
	 */
	void setHeight(int value);

	/**
	 * Sets the value of the '{@link org.eclipse.epf.diagram.model.Node#getWidth <em>Width</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Width</em>' attribute.
	 * @see #getWidth()
	 * @generated
	 */
	void setWidth(int value);

	/**
	 * Sets the value of the '{@link org.eclipse.epf.diagram.model.Node#getLocation <em>Location</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Location</em>' attribute.
	 * @see #getLocation()
	 * @generated
	 */
	void setLocation(Point value);

	/**
	 * Gets incoming links
	 * 
	 * @model type="Link" opposite="target" lower="0"
	 */
	EList<Link> getIncomingConnections();

	/**
	 * Gets outgoing links
	 * 
	 * @model type="Link" containment="true" opposite="source" lower="0"
	 */
	EList<Link> getOutgoingConnections();

	/**
	 * Checks if this node is read-only
	 * 
	 * @model
	 */
	boolean isReadOnly();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.diagram.model.Node#isReadOnly <em>Read Only</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Read Only</em>' attribute.
	 * @see #isReadOnly()
	 * @generated
	 */
	void setReadOnly(boolean value);

	/**
	 * Gets the diagram of this node
	 * 
	 * @return
	 */
	Diagram getDiagram();

	/**
	 * Sets diagram for this node
	 * 
	 * @param diagram
	 */
	void setDiagram(Diagram diagram);

	/**
	 * Gets the GraphNode associated with this node.
	 * 
	 * @return
	 */
	GraphNode getGraphNode();

	/**
	 * Gets the type of method element adapter. Method element adapter is a node
	 * internal {@link Adapter adapter} that is listening on changes of linked
	 * method element or its wrapper to update the node accordingly.
	 * 
	 * @return the class of the method element adapter of this node
	 */
	Class getMethodElementAdapterType();

	/**
	 * This method is used to set the UMA diagram for the new node so it can
	 * find the GraphNode for the linked method element.
	 * 
	 * @param umaDiagram
	 */
	void setUMADiagram(org.eclipse.epf.uma.Diagram umaDiagram);

	/**
	 * Sets the graph node that is the container of the
	 * {@link #getGraphNode() linked graph node}
	 * 
	 * @param umaContainer
	 */
	void setUMAContainer(GraphNode umaContainer);

	/**
	 * Adds the given consumer to the consumer list of this node.
	 * 
	 * @param consumer
	 */
	void addConsumer(Object consumer);

	/**
	 * Removes the given consumer from the consumer list of this node. Disposes
	 * the node if it does not have any more consumer after this call. Disposing
	 * a node will take care of removing this node's listener from the UMA
	 * object and all the adapters that had been added to the adapter list of
	 * this node.
	 * 
	 * @param consumer
	 */
	void removeConsumer(Object consumer);

}