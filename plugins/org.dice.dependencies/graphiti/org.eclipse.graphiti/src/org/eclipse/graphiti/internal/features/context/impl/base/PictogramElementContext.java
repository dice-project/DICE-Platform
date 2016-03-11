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

import org.eclipse.graphiti.features.context.IPictogramElementContext;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

/**
 * The Class PictogramElementContext.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class PictogramElementContext extends DefaultContext implements IPictogramElementContext {

	private PictogramElement pictogramElement;

	/**
	 * The Constructor.
	 * 
	 * @param pictogramElement
	 *            the pictogram element
	 */
	public PictogramElementContext(PictogramElement pictogramElement) {
		this();
		setPictogramElement(pictogramElement);
	}

	/**
	 * Instantiates a new pictogram element context.
	 */
	public PictogramElementContext() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.context.IDeleteContext#getPictogramElement
	 * ()
	 */
	public PictogramElement getPictogramElement() {
		return pictogramElement;
	}

	/**
	 * Sets the pictogram element.
	 * 
	 * @param pictogramElement
	 *            The pictogramElement to set.
	 */
	protected void setPictogramElement(PictogramElement pictogramElement) {
		this.pictogramElement = pictogramElement;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.internal.features.context.impl.base.DefaultContext
	 * #toString()
	 */
	@Override
	public String toString() {
		String ret = super.toString();
		return ret + " pictogramElement: " + getPictogramElement(); //$NON-NLS-1$
	}

}
