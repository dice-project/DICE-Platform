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
package org.eclipse.graphiti.internal.features.context.impl.base;

import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

/**
 * The Class DetailedPictogramElementContext.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public abstract class DetailedPictogramElementContext extends PictogramElementContext {
	private GraphicsAlgorithm graphicsAlgorithm;

	/**
	 * The Constructor.
	 * 
	 * @param pictogramElement
	 *            the pictogram element
	 * @param graphicsAlgorithm
	 *            the graphics algorithm
	 */
	public DetailedPictogramElementContext(PictogramElement pictogramElement, GraphicsAlgorithm graphicsAlgorithm) {
		super(pictogramElement);
		this.graphicsAlgorithm = graphicsAlgorithm;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.eclipse.graphiti.features.context.IDirectEditingContext#
	 * getGraphicsAlgorithm()
	 */
	/**
	 * Gets the graphics algorithm.
	 * 
	 * @return the graphics algorithm
	 */
	public GraphicsAlgorithm getGraphicsAlgorithm() {
		return graphicsAlgorithm;
	}

	@Override
	public String toString() {
		String ret = super.toString();
		return ret + " graphicsAlgorithm: " + getGraphicsAlgorithm(); //$NON-NLS-1$
	}

}
