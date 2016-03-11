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
package org.eclipse.epf.search.ui.internal;

import java.text.MessageFormat;

import org.eclipse.epf.search.ui.SearchUIPlugin;
import org.eclipse.epf.search.ui.SearchUIResources;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.search.ui.ISearchQuery;
import org.eclipse.search.ui.text.AbstractTextSearchResult;
import org.eclipse.search.ui.text.IEditorMatchAdapter;
import org.eclipse.search.ui.text.IFileMatchAdapter;
import org.eclipse.search.ui.text.Match;
import org.eclipse.ui.IEditorPart;

/**
 * The method search result.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class MethodSearchResult extends AbstractTextSearchResult implements
		IEditorMatchAdapter {

	private static final String MATCH_LABEL = SearchUIResources.searchResult_match; 

	private static final String MATCHES_LABEL = SearchUIResources.searchResult_matches; 

	// The search query.
	private MethodSearchQuery searchQuery;

	/**
	 * Creates a new instance.
	 * 
	 * @param searchQuery
	 *            a method search query
	 */
	public MethodSearchResult(MethodSearchQuery searchQuery) {
		this.searchQuery = searchQuery;
	}

	/**
	 * @see org.eclipse.search.ui.ISearchResult#getQuery()
	 */
	public ISearchQuery getQuery() {
		return searchQuery;
	}

	/**
	 * @see org.eclipse.search.ui.ISearchResult#getImageDescriptor()
	 */
	public ImageDescriptor getImageDescriptor() {
		return SearchUIPlugin.getDefault().getImageDescriptor(
				"full/obj16/MethodSearch.gif"); //$NON-NLS-1$
	}

	/**
	 * @see org.eclipse.search.ui.ISearchResult#getLabel()
	 */
	public String getLabel() {
		// TODO: Display search result in the form:
		//    'candidate' - 6 matches in 4 elements.
		String searchString = searchQuery.getSearchInput().getSearchString();
		int matchCount = getMatchCount();
		if (matchCount == 1) {
			return MessageFormat.format(MATCH_LABEL,
					new Object[] { searchString });		
		} else {
			return MessageFormat.format(MATCHES_LABEL, new Object[] {
					searchString, "" + matchCount }); //$NON-NLS-1$
		}
	}

	/**
	 * @see org.eclipse.search.ui.ISearchResult#getTooltip()
	 */
	public String getTooltip() {
		return getLabel();
	}

	/**
	 * @see org.eclipse.search.ui.text.AbstractTextSearchResult#getEditorMatchAdapter()
	 */
	public IEditorMatchAdapter getEditorMatchAdapter() {
		return this;
	}

	/**
	 * @see org.eclipse.search.ui.text.AbstractTextSearchResult#getFileMatchAdapter()
	 */
	public IFileMatchAdapter getFileMatchAdapter() {
		return null;
	}

	/**
	 * @see org.eclipse.search.ui.text.IEditorMatchAdapter#isShownInEditor(Match,
	 *      IEditorPart)
	 */
	public boolean isShownInEditor(Match match, IEditorPart editor) {
		return false;
	}

	/**
	 * @see org.eclipse.search.ui.text.IEditorMatchAdapter#computeContainedMatches(AbstractTextSearchResult,
	 *      IEditorPart)
	 */
	public Match[] computeContainedMatches(AbstractTextSearchResult result,
			IEditorPart editor) {
		return null;
	}

}
