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

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.mm.Property;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.PictogramLink;

/**
 * The interface ILinkService provides services for the link handling between
 * the graphical representation (pictogram elements) and the domain model
 * (business objects).
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 */
public interface ILinkService {

	/**
	 * Returns all business objects which are linked to the given pictogram
	 * element.
	 * 
	 * @param pictogramElement
	 *            The pictogram element for which to return the business
	 *            objects.
	 * @return The business objects which are linked to the given pictogram
	 *         element. Can be empty but not null.
	 */
	EObject[] getAllBusinessObjectsForLinkedPictogramElement(PictogramElement pictogramElement);

	/**
	 * Returns the first of possibly several business objects which are linked
	 * to the given pictogram element. This is a convenience method for
	 * {@link #getAllBusinessObjectsForPictogramElement(PictogramElement)},
	 * because in many use cases only a single business object is linked.
	 * 
	 * @param pictogramElement
	 *            The pictogram element for which to return the business
	 *            objects.
	 * @return The first of possibly several business objects which are linked
	 *         to the given pictogram element. Can be null.
	 */
	EObject getBusinessObjectForLinkedPictogramElement(PictogramElement pictogramElement);

	/**
	 * Returns the pictogram link referencing the given pictogram element.
	 * 
	 * @param pictogramElement
	 *            the pictogram element
	 * @return the pictogram link referencing the given pictogram element
	 */
	PictogramLink getLinkForPictogramElement(PictogramElement pictogramElement);

	/**
	 * Gets all pictogram elements which references the given business object.
	 * 
	 * @param diagram
	 *            the diagram
	 * @param eObject
	 *            the referenced business object
	 * @return all pictogram elements in the diagram which references the given
	 *         business object
	 */
	List<PictogramElement> getPictogramElements(Diagram diagram, EObject eObject);

	/**
	 * Gets all pictogram elements which references at least one of the given
	 * business objects.
	 * 
	 * @param diagram
	 *            the diagram
	 * @param eObjects
	 *            the referenced business objects
	 * @param onlyActive
	 *            if true, then only active pictogram elements of the diagram
	 *            will be considered; if false all pictogram elements will be
	 *            considered
	 * @return all (active) pictogram elements in the diagram which have at
	 *         least one reference to one of the business objects
	 */
	List<PictogramElement> getPictogramElements(Diagram diagram, List<EObject> eObjects, boolean onlyActive);

	/**
	 * Checks existence and value of the link property to a given pictogram
	 * element. It is intended to use this property to be able to disinguish
	 * multiple pictogram elements linked to same domain model object.
	 * 
	 * @param pictogramElement
	 *            the pictogram element
	 * @param propertyValue
	 *            the value to check against the property
	 * @return true if link property exists an has the given value; false if not
	 */
	boolean hasLinkProperty(PictogramElement pictogramElement, String propertyValue);

	/**
	 * Adds or modifies the link property to a given pictogram element. It is
	 * intended to use this property to be able to disinguish multiple pictogram
	 * elements linked to same domain model object.
	 * 
	 * @param pictogramElement
	 *            the pictogram element
	 * @param propertyValue
	 *            the new value for the link property
	 */
	void setLinkProperty(PictogramElement pictogramElement, String propertyValue);

	/**
	 * Gets the link property to a given pictogram element.
	 * 
	 * @param pictogramElement
	 *            the pictogram element
	 * @return the link property
	 */
	Property getLinkProperty(PictogramElement pictogramElement);
}
