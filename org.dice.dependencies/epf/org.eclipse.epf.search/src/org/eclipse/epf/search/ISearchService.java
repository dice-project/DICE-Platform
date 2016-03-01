//------------------------------------------------------------------------------
// Copyright (c) 2005, 2006 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.search;

import org.eclipse.epf.search.configuration.ConfigurationHitEntry;
import org.eclipse.epf.search.configuration.ConfigurationSearchQuery;

/**
 * The Search Service API.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public interface ISearchService {

	/**
	 * Searches a published configuration.
	 * 
	 * @param path
	 *            an absolute path to a published configuration
	 * @param searchQuery
	 *            a configuration search query
	 * @return an array of <code>ConfigurationHitEntry</code> objects
	 * @throws SearchServiceException
	 *             if an error occurs while executing the operation
	 */
	public ConfigurationHitEntry[] searchConfiguration(String path,
			ConfigurationSearchQuery searchQuery) throws SearchServiceException;

}
