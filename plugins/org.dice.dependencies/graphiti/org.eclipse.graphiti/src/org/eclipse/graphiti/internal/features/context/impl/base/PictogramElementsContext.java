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
/*
 * Created on 12.12.2005
 */
package org.eclipse.graphiti.internal.features.context.impl.base;

import org.eclipse.graphiti.features.context.IPictogramElementsContext;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

/**
 * The Class PictogramElementsContext.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class PictogramElementsContext extends DefaultContext implements IPictogramElementsContext {

	private PictogramElement[] pictogramElements;

	/**
	 * The Constructor.
	 * 
	 * @param pictogramElements
	 *            the pictogram elements
	 */
	public PictogramElementsContext(PictogramElement[] pictogramElements) {
		this();
		setPictogramElements(pictogramElements);
	}

	/**
	 * Instantiates a new pictogram elements context.
	 */
	public PictogramElementsContext() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.context.IDeleteContext#getPictogramElement
	 * ()
	 */
	public PictogramElement[] getPictogramElements() {
		return pictogramElements;
	}

	/**
	 * Sets the pictogram elements.
	 * 
	 * @param pictogramElements
	 *            the pictogram elements
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
