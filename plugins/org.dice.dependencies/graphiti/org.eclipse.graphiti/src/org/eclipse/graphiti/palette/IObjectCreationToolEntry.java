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

import org.eclipse.graphiti.features.ICreateFeature;

/**
 * The Interface IObjectCreationToolEntry.
 */
public interface IObjectCreationToolEntry extends ICreationToolEntry {

	/**
	 * Gets the creates the feature.
	 * 
	 * @return the ICreateFeature feature
	 */
	public ICreateFeature getCreateFeature();
}
