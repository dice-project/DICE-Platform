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
package org.eclipse.graphiti.features;

import org.eclipse.graphiti.features.impl.DefaultDirectEditingInfo;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

/**
 * The information provided by the <i>IDirectEditingInfo</i> is needed to switch
 * automatically<br>
 * into the direct editing mode. (e.g. after creation of a new object) <br>
 * <br>
 * The direct editing field will be opened on the figure<br>
 * which represents the graphics algorithm. <br>
 * Pictogram element and graphics algorithm will be used to find the correct
 * direct editing feature.<br>
 * The main pictogram element is used by the editor to locate the graphical
 * shape on the diagram.
 * 
 * @noextend This interface is not intended to be extended by clients.
 * @noimplement This interface is not intended to be implemented by clients,
 *              extend {@link DefaultDirectEditingInfo} instead.
 */
public interface IDirectEditingInfo {

	/**
	 * Sets the main pictogram element.
	 * 
	 * @param pictogramElement
	 *            the pictogram element
	 */
	void setMainPictogramElement(PictogramElement pictogramElement);

	/**
	 * Sets the pictogram element.
	 * 
	 * @param pictogramElement
	 *            the pictogram element
	 */
	void setPictogramElement(PictogramElement pictogramElement);

	/**
	 * Sets the graphics algorithm.
	 * 
	 * @param graphicsAlgorithm
	 *            the graphics algorithm
	 */
	void setGraphicsAlgorithm(GraphicsAlgorithm graphicsAlgorithm);

	/**
	 * Gets the main pictogram element.
	 * 
	 * @return the main pictgram element
	 */
	PictogramElement getMainPictogramElement();

	/**
	 * Gets the pictogram element.
	 * 
	 * @return the pictogram element
	 */
	PictogramElement getPictogramElement();

	/**
	 * Gets the graphics algorithm.
	 * 
	 * @return the graphics algorithm
	 */
	GraphicsAlgorithm getGraphicsAlgorithm();

	/**
	 * sets all the infos to null.
	 */
	void reset();

	/**
	 * Checks if is active.
	 * 
	 * @return TRUE, if this info should be used; FALSE otherwise
	 */
	boolean isActive();

	/**
	 * Sets the active state.
	 * 
	 * @param isActive
	 *            the is active
	 */
	void setActive(boolean isActive);
}
