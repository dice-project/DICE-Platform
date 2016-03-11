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
 * Created on 17.11.2005
 */
package org.eclipse.graphiti.features.context.impl;

import org.eclipse.graphiti.features.context.ICopyContext;
import org.eclipse.graphiti.internal.features.context.impl.base.PictogramElementsContext;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

/**
 * The Class CopyContext.
 */
public class CopyContext extends PictogramElementsContext implements ICopyContext {

	/**
	 * Creates a new {@link CopyContext}.
	 * 
	 * @param pictogramElements
	 *            the pictogram elements
	 */
	public CopyContext(PictogramElement[] pictogramElements) {
		super(pictogramElements);
	}
}
