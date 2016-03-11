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
package org.eclipse.graphiti.palette;

import java.util.List;

import org.eclipse.graphiti.features.IFeature;


/**
 * The Interface IConnectionCreationToolEntry.
 */
public interface IConnectionCreationToolEntry extends ICreationToolEntry {

	/**
	 * Gets the creates the connection features.
	 * 
	 * @return the the List<IFeature> connection features
	 */
	public List<IFeature> getCreateConnectionFeatures();
}
