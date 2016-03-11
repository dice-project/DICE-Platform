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
package org.eclipse.graphiti.features.context;

import org.eclipse.graphiti.mm.pictograms.PictogramElement;

/**
 * The Interface IPictogramElementsContext.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 */
public interface IPictogramElementsContext extends IContext {

	/**
	 * Gets the pictogram elements.
	 * 
	 * @return the pictogram elements
	 */
	PictogramElement[] getPictogramElements();

	/**
	 * Sets the pictogram elements.
	 * 
	 * @param pes
	 *            the new pictogram elements
	 */
	void setPictogramElements(PictogramElement[] pes);
}
