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

import org.eclipse.osgi.util.NLS;

/**
 * The Search resource bundle.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public final class SearchResources extends NLS {

	private static String BUNDLE_NAME = SearchResources.class.getPackage()
			.getName()
			+ ".Resources"; //$NON-NLS-1$

	public static String indexConfigFilesTask_name;
	public static String createSearchIndexError;

	static {
		NLS.initializeMessages(BUNDLE_NAME, SearchResources.class);
	}

	private SearchResources() {
		// Do not instantiate
	}

}