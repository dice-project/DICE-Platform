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
package org.eclipse.graphiti.util;

import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.Shape;

/**
 * The Interface ILocationInfo.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 */
public interface ILocationInfo {

	/**
	 * Gets the shape.
	 * 
	 * @return the shape of this location information
	 */
	Shape getShape();

	/**
	 * Gets the graphics algorithm.
	 * 
	 * @return the graphics algorithm of this location information
	 */
	GraphicsAlgorithm getGraphicsAlgorithm();
}
