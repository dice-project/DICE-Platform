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
package org.eclipse.graphiti.features.context.impl;

import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

/**
 * The Class CustomContext.
 */
public class CustomContext extends LocationContext implements ICustomContext {

	private GraphicsAlgorithm innerGraphicsAlgorithm;

	private PictogramElement innerPictogramElement;

	private PictogramElement pictogramElements[];

	/**
	 * Creates a new {@link CustomContext}.
	 */
	public CustomContext() {
		super();
	}

	/**
	 * Creates a new {@link CustomContext}.
	 * 
	 * @param pictogramElements
	 *            the pictogram elements
	 */
	public CustomContext(PictogramElement[] pictogramElements) {
		this();
		setPictogramElements(pictogramElements);
	}

	public GraphicsAlgorithm getInnerGraphicsAlgorithm() {
		return this.innerGraphicsAlgorithm;
	}

	public PictogramElement getInnerPictogramElement() {
		return this.innerPictogramElement;
	}

	public PictogramElement[] getPictogramElements() {
		return this.pictogramElements;
	}

	/**
	 * Sets the inner graphics algorithm.
	 * 
	 * @param innerGraphicsAlgorithm
	 *            the new inner graphics algorithm
	 */
	public void setInnerGraphicsAlgorithm(GraphicsAlgorithm innerGraphicsAlgorithm) {
		this.innerGraphicsAlgorithm = innerGraphicsAlgorithm;
	}

	/**
	 * Sets the inner pictogram element.
	 * 
	 * @param innerPictogramElement
	 *            the new inner pictogram element
	 */
	public void setInnerPictogramElement(PictogramElement innerPictogramElement) {
		this.innerPictogramElement = innerPictogramElement;
	}

	/**
	 * Sets the pictogram elements.
	 * 
	 * @param pictogramElements
	 *            The pictogramElements to set.
	 */
	public void setPictogramElements(PictogramElement[] pictogramElements) {
		this.pictogramElements = pictogramElements;
	}

	@Override
	public String toString() {
		String ret = super.toString();
		return ret + " innerGraphicsAlgorithm: " + getInnerGraphicsAlgorithm() + " innerPictogramElement: " + getInnerPictogramElement() //$NON-NLS-1$ //$NON-NLS-2$
				+ " pictogramElements: " + getPictogramElements(); //$NON-NLS-1$
	}

}
