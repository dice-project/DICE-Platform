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
package org.eclipse.epf.search.ui;

import org.eclipse.osgi.util.NLS;

/**
 * The Search UI resource bundle.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public final class SearchUIResources extends NLS {

	private static String BUNDLE_NAME = SearchUIResources.class.getPackage()
			.getName()
			+ ".Resources"; //$NON-NLS-1$

	public static String searchStringLabel_text;

	public static String caseSensitiveCheckbox_text;

	public static String elementNameLabel_text;

	public static String scopeGroup_text;

	public static String roleCheckbox_text;

	public static String taskCheckbox_text;

	public static String workProductCheckbox_text;

	public static String searchQuery_text;

	public static String scanLibraryTask_name;

	public static String scanElementTask_name;

	public static String searchError_title;

	public static String searchError_msg;

	public static String searchError_reason;

	public static String searchResult_methodContent;

	public static String searchResult_contentPackages;

	public static String searchResult_standardCategories;

	public static String searchResult_disciplines;

	public static String searchResult_domains;

	public static String searchResult_workProductTypes;

	public static String searchResult_roleSets;

	public static String searchResult_tools;

	public static String searchResult_customCategories;

	public static String searchResult_processes;

	public static String searchResult_capabilityPatterns;

	public static String searchResult_deliveryProcesses;

	public static String searchResult_match;

	public static String searchResult_matches;

	static {
		NLS.initializeMessages(BUNDLE_NAME, SearchUIResources.class);
	}

	private SearchUIResources() {
		// Do not instantiate.
	}

}