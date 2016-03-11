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
package org.eclipse.graphiti.features.context.impl;

import org.eclipse.graphiti.features.context.IDirectEditingContext;
import org.eclipse.graphiti.internal.features.context.impl.base.DetailedPictogramElementContext;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

/**
 * The Class DirectEditingContext.
 */
public class DirectEditingContext extends DetailedPictogramElementContext implements IDirectEditingContext {

	/**
	 * Creates a new {@link DirectEditingContext}.
	 * 
	 * @param pictogramElement
	 *            the pictogram element
	 * @param graphicsAlgorithm
	 *            the graphics algorithm
	 */
	public DirectEditingContext(PictogramElement pictogramElement, GraphicsAlgorithm graphicsAlgorithm) {
		super(pictogramElement, graphicsAlgorithm);

	}

}
