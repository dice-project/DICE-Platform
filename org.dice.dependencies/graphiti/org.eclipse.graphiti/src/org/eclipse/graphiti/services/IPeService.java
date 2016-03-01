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

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.IExecutionInfo;
import org.eclipse.graphiti.mm.Property;
import org.eclipse.graphiti.mm.PropertyContainer;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.AnchorContainer;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;

/**
 * The interface IPeService provides convenient services for the creation and
 * layout of pictogram elements.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 */
public interface IPeService extends IPeCreateService, IPeLayoutService {

	/**
	 * Deletes the given pictogram element (and with it all aggregated
	 * elements!).
	 * 
	 * @param pe
	 *            the pictogram element to delete
	 */
	void deletePictogramElement(PictogramElement pe);

	/**
	 * Gets the active container pe.
	 * 
	 * @param ga
	 *            the ga
	 * @return the active container pe
	 */
	PictogramElement getActiveContainerPe(GraphicsAlgorithm ga);

	/**
	 * Gets the active container pe.
	 * 
	 * @param pictogramElement
	 *            the pictogram element
	 * @return the active container pe
	 */
	PictogramElement getActiveContainerPe(PictogramElement pictogramElement);

	/**
	 * Returns all connections of an anchor.
	 * 
	 * @param anchor
	 *            the anchor
	 * @return list of connections
	 */
	List<Connection> getAllConnections(Anchor anchor);

	/**
	 * Returns all connections of an anchor container.
	 * 
	 * @param anchorContainer
	 *            the anchor container
	 * @return list of connections
	 */
	List<Connection> getAllConnections(AnchorContainer anchorContainer);

	/**
	 * Gets the all contained pictogram elements.
	 * 
	 * @param pe
	 *            the pe
	 * @return the all contained pictogram elements
	 */
	Collection<PictogramElement> getAllContainedPictogramElements(PictogramElement pe);

	/**
	 * Returns all the contained container shapes. Dives through the whole
	 * shapes tree.
	 * 
	 * @param cs
	 *            the container shape
	 * @return all the contained container shapes
	 */
	Collection<Shape> getAllContainedShapes(ContainerShape cs);

	/**
	 * Gets the chopbox anchor.
	 * 
	 * @param anchorContainer
	 *            the anchor container
	 * @return The chopbox anchor of the anchor container if one exist,
	 *         otherwise null
	 */
	Anchor getChopboxAnchor(AnchorContainer anchorContainer);

	/**
	 * Returns the diagram for the given anchor.
	 * 
	 * @param anchor
	 *            the anchor
	 * @return the diagram
	 */
	Diagram getDiagramForAnchor(Anchor anchor);

	/**
	 * Returns the diagram for the given pictogram element.
	 * 
	 * @param pe
	 *            the pe
	 * @return the diagram
	 */
	Diagram getDiagramForPictogramElement(PictogramElement pe);

	/**
	 * Returns the diagram for the given shape.
	 * 
	 * @param shape
	 *            the shape
	 * @return the diagram
	 */
	Diagram getDiagramForShape(Shape shape);

	/**
	 * From the given elements, returns all elements that are not linked by a
	 * PictogramLink in the given Diagram.
	 * 
	 * @param elements
	 *            the elements
	 * @param diagram
	 *            the diag
	 * @return the elements not in diagram
	 */
	EObject[] getElementsNotInDiagram(EObject[] elements, Diagram diagram);

	/**
	 * Returns the incoming connections of an anchor container.
	 * 
	 * @param anchorContainer
	 *            the anchor container
	 * @return list of incoming connections
	 */
	List<Connection> getIncomingConnections(AnchorContainer anchorContainer);

	/**
	 * Return all the pictogram elements of the given Diagram which have at
	 * least one link to one of the given elements.
	 * 
	 * @param elements
	 *            the elements
	 * @param diagram
	 *            the diag
	 * @return the linked pictogram elements
	 */
	Object[] getLinkedPictogramElements(EObject[] elements, Diagram diagram);

	/**
	 * Returns the outgoing connections of an anchor container.
	 * 
	 * @param anchorContainer
	 *            the anchor container
	 * @return list of outgoing connections
	 */
	List<Connection> getOutgoingConnections(AnchorContainer anchorContainer);

	//	private static ModelPartition getPartition(RefObject object) {
	//		ModelPartition partition = null;
	//		if (object != null) {
	//			partition = object.get___Partition();
	//		}
	//		return partition;
	//	}

	/**
	 * Returns a pictogram element's children. <br>
	 * Some Examples: returns all connections of a diagram, all shapes of a
	 * container shape, all decorators of a connection, all anchors of an anchor
	 * container
	 * 
	 * @param pe
	 *            the given pictogram element
	 * @return all the pictogram element's children
	 */
	Collection<PictogramElement> getPictogramElementChildren(PictogramElement pe);

	/**
	 * Gets the pictogram element parent.
	 * 
	 * @param pe
	 *            the pe
	 * @return the pictogram element parent
	 */
	PictogramElement getPictogramElementParent(PictogramElement pe);

	/**
	 * Returns the property of a given property container for a specific key.
	 * 
	 * @param propertyContainer
	 *            The property container (e.g. PictogramElement or
	 *            GraphicsAlgorithm)
	 * @param key
	 *            The property key
	 * @return The property for the key
	 */
	Property getProperty(PropertyContainer propertyContainer, String key);

	/**
	 * Returns the property value of a given property container for a specific
	 * key.
	 * 
	 * @param propertyContainer
	 *            The property container (e.g. PictogramElement or
	 *            GraphicsAlgorithm)
	 * @param key
	 *            The property key
	 * @return The value of the property for the key
	 */
	String getPropertyValue(PropertyContainer propertyContainer, String key);

	/**
	 * Move bendpoints.
	 * 
	 * @param executionInfo
	 *            the execution info
	 */
	void moveBendpoints(IExecutionInfo executionInfo);

	/**
	 * Removes the property of a given property container for a specific key.
	 * 
	 * @param propertyContainer
	 *            The property container (e.g. PictogramElement or
	 *            GraphicsAlgorithm)
	 * @param key
	 *            The property key
	 * @return True, if the property existed
	 */
	boolean removeProperty(PropertyContainer propertyContainer, String key);

	/**
	 * Reorders parent's children to make the given shape the backmost one. This
	 * is a convenient service to modify the z order. The z order of shapes in
	 * their containers can also be modified by changing the order of the
	 * children in the corresponding container shape directly. The last element
	 * in the list will be painted on top.
	 * 
	 * @param shape
	 *            shape to make the backmost one
	 */
	void sendToBack(Shape shape);

	/**
	 * Reorders parent's children to make the given shape the frontmost one.
	 * This is a convenient service to modify the z order. The z order of shapes
	 * in their containers can also be modified by changing the order of the
	 * children in the corresponding container shape directly. The last element
	 * in the list will be painted on top.
	 * 
	 * @param shape
	 *            shape to make the frontmost one
	 */
	void sendToFront(Shape shape);

	/**
	 * Sets/modifies the property's value of a given property container for a
	 * specific key. <br>
	 * The property object will be created if it does not exist.
	 * 
	 * @param propertyContainer
	 *            The property container (e.g. PictogramElement or
	 *            GraphicsAlgorithm)
	 * @param key
	 *            The property key
	 * @param value
	 *            The new property value
	 */
	void setPropertyValue(PropertyContainer propertyContainer, String key, String value);

}
