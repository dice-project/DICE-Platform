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

import org.eclipse.graphiti.features.context.ISaveImageContext;
import org.eclipse.graphiti.internal.features.context.impl.base.DefaultContext;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

/**
 * The Class SaveImageContext.
 */
public class SaveImageContext extends DefaultContext implements ISaveImageContext {

	private PictogramElement pictogramElements[];

	/**
	 * Creates a new {@link SaveImageContext}.
	 * 
	 * @param pictogramElements
	 *            the pictogram elements
	 */
	public SaveImageContext(PictogramElement[] pictogramElements) {
		this();
		setPictogramElements(pictogramElements);
	}

	/**
	 * Creates a new {@link SaveImageContext}.
	 */
	public SaveImageContext() {
		super();
	}

	public PictogramElement[] getPictogramElements() {
		return this.pictogramElements;
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
		return ret + " pictogramElements: " + getPictogramElements(); //$NON-NLS-1$
	}

}
