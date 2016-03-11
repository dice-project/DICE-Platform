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
package org.eclipse.graphiti.features.impl;

/**
 * The Interface IIndependenceSolver.
 */
public interface IIndependenceSolver {

	/**
	 * Provides the unique key for the given business object.
	 * 
	 * @param bo
	 *            the given business object
	 * 
	 * @return unique key
	 */
	String getKeyForBusinessObject(Object bo);

	/**
	 * Provides the business object for the given key.
	 * 
	 * @param key
	 *            the unique key
	 * 
	 * @return the business object
	 */
	Object getBusinessObjectForKey(String key);
}
