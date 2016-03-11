/*******************************************************************************
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
 *
 *******************************************************************************/
package org.eclipse.graphiti.services;

import org.eclipse.graphiti.mm.pictograms.AnchorContainer;
import org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor;
import org.eclipse.graphiti.mm.pictograms.ChopboxAnchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.FixPointAnchor;
import org.eclipse.graphiti.mm.pictograms.FreeFormConnection;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;

/**
 * The interface IPeCreateService provides services for the creation of all
 * available pictogram elements. E.g. Shapes, Connections, Anchors, ...
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 */
public interface IPeCreateService {

	/**
	 * Creates a box relative anchor inside the given anchor container.
	 * 
	 * @param anchorContainer
	 *            the anchors parent
	 * @return the new box relative anchor
	 */
	BoxRelativeAnchor createBoxRelativeAnchor(AnchorContainer anchorContainer);

	/**
	 * Creates a chop box anchor inside the given anchor container.
	 * 
	 * @param anchorContainer
	 *            the anchors parent
	 * @return the new chop box anchor
	 */
	ChopboxAnchor createChopboxAnchor(AnchorContainer anchorContainer);

	/**
	 * Creates a connection decorator and adds it to the given connection.
	 * 
	 * @param connection
	 *            the connection
	 * @param active
	 *            TRUE, if decorator is active, FALSE otherwise
	 * @param location
	 *            location of the decorator (must be between 0 and 1)
	 * @param isRelative
	 *            true if the decorator should be positioned relative to the
	 *            connection's midpoint
	 * @return the new connection decorator
	 */
	ConnectionDecorator createConnectionDecorator(Connection connection, boolean active, double location, boolean isRelative);

	/**
	 * Creates a container shape inside the given parent container shape.
	 * 
	 * @param parentContainerShape
	 *            the parent container shape
	 * @param active
	 *            <code>true</code>, if the created shape should be active,
	 *            <code>false</code> otherwise. An active shape can be selected
	 *            in the diagram editor and it is also relevant for layouting:
	 *            an active shape opens a coordinate system which can be used
	 *            for layouting its {@link PictogramElement} children, while an
	 *            inactive one does not provide one but uses the coordinate
	 *            system of its next active parent for layouting its children.
	 *            <p>
	 *            By default all shapes should be active, inactive shapes should
	 *            be used for grouping purposes or for linking a group of
	 *            graphical objects to the domain world only.
	 *            <p>
	 *            For those familiar with GEF: only for active shapes a GEF
	 *            EditPart will be created by the Graphiti framework, not for
	 *            inactive ones.
	 * @return the new container shape
	 */
	ContainerShape createContainerShape(ContainerShape parentContainerShape, boolean active);

	/**
	 * Creates a diagram.
	 * 
	 * @param diagramTypeId
	 *            the type id of the diagram
	 * @param diagramName
	 *            the name of the diagram
	 * @param snap
	 *            TRUE enables snap to grid
	 * @return the new diagram
	 * @see #createDiagram(String diagramTypeId, String diagramName, int
	 *      gridUnit, boolean snap)
	 */
	Diagram createDiagram(String diagramTypeId, String diagramName, boolean snap);

	/**
	 * Creates a diagram.
	 * 
	 * @param diagramTypeId
	 *            the type id of the diagram
	 * @param diagramName
	 *            the name of the diagram
	 * @param gridUnit
	 *            grid size in pixel; if 0 then no grid will be drawn
	 * @param snap
	 *            TRUE enables snap to grid
	 * @return the new diagram
	 */
	Diagram createDiagram(String diagramTypeId, String diagramName, int gridUnit, boolean snap);

	/**
	 * Creates a fix point anchor inside the given anchor container.
	 * 
	 * @param anchorContainer
	 *            the anchors parent
	 * @return the new fix point anchor
	 */
	FixPointAnchor createFixPointAnchor(AnchorContainer anchorContainer);

	/**
	 * Creates a free form connection inside the given diagram.
	 * 
	 * @param diagram
	 *            the diagram
	 * @return the new free form connection
	 */
	FreeFormConnection createFreeFormConnection(Diagram diagram);

	/**
	 * Creates a shape inside the given parent container shape.
	 * 
	 * @param parentContainerShape
	 *            the parent container shape
	 * @param active
	 *            <code>true</code>, if the created shape should be active,
	 *            <code>false</code> otherwise. An active shape can be selected
	 *            in the diagram editor and it is also relevant for layouting:
	 *            an active shape opens a coordinate system which can be used
	 *            for layouting its {@link PictogramElement} children, while an
	 *            inactive one does not provide one but uses the coordinate
	 *            system of its next active parent for layouting its children.
	 *            <p>
	 *            By default all shapes should be active, inactive shapes should
	 *            be used for grouping purposes or for linking a group of
	 *            graphical objects to the domain world only.
	 *            <p>
	 *            For those familiar with GEF: only for active shapes a GEF
	 *            EditPart will be created by the Graphiti framework, not for
	 *            inactive ones.
	 * @return the new shape
	 */
	Shape createShape(ContainerShape parentContainerShape, boolean active);

}
