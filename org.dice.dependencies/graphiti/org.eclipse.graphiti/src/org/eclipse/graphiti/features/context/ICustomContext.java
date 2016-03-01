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
/**
 * 
 */
package org.eclipse.graphiti.features.context;

import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

/**
 * The Interface ICustomContext.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 */
public interface ICustomContext extends ILocationContext {

	/**
	 * Gets the pictogram elements.
	 * 
	 * @return the currently selected pictogram elements
	 */
	PictogramElement[] getPictogramElements();

	/**
	 * Gets the inner pictogram element.
	 * 
	 * @return the pictogram element at the mouse click position
	 */
	PictogramElement getInnerPictogramElement();

	/**
	 * Gets the inner graphics algorithm.
	 * 
	 * @return the graphics algorithm at the mouse click position
	 */
	GraphicsAlgorithm getInnerGraphicsAlgorithm();
}
