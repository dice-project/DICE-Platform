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

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.search.ui.SearchUIResources;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.search.ui.ISearchQuery;
import org.eclipse.search.ui.ISearchResult;
import org.eclipse.search.ui.text.Match;

/**
 * Encapsulates the search strings and options for a method search.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class MethodSearchQuery implements ISearchQuery {

	private MethodSearchInput searchInput;

	private MethodSearchResult searchResult;

	/**
	 * Creates a new instance.
	 * 
	 * @param searchString
	 *            a search string
	 * @param namePattern
	 *            a method element name pattern
	 */
	public MethodSearchQuery(MethodSearchInput searchInput) {
		this.searchInput = searchInput;
	}

	/**
	 * @see org.eclipse.search.ui.ISearchQuery#run(IProgressMonitor)
	 */
	public IStatus run(IProgressMonitor processMonitor) {
		final MethodSearchResult searchResult = (MethodSearchResult) getSearchResult();
		searchResult.removeAll();
		ISearchResultCollector result = new ISearchResultCollector() {
			public void accept(Object match) {
				if (match instanceof MethodElement
						&& !TngUtil.isPredefined((MethodElement) match)) {
					Object[] matches = searchResult.getMatches(match);
					if (matches == null || matches.length == 0) {
						searchResult.addMatch(new Match(match, Match.UNIT_LINE,
								1, 1));
					}
				}
			}
		};
//		MethodSearchOperation operation = new MethodSearchOperation(
//				searchInput, result);
//		operation.execute(processMonitor);
		IMethodSearchOperation op = MethodSearchHelper.newSearchOperation();
		op.execute(searchInput, result, processMonitor);
		processMonitor.done();
		return Status.OK_STATUS;
	}

	/**
	 * @see org.eclipse.search.ui.ISearchQuery#getLabel()
	 */
	public String getLabel() {
		return SearchUIResources.searchQuery_text; 
	}

	/**
	 * @see org.eclipse.search.ui.ISearchQuery#canRerun()
	 */
	public boolean canRerun() {
		return true;
	}

	/**
	 * @see org.eclipse.search.ui.ISearchQuery#canRunInBackground()
	 */
	public boolean canRunInBackground() {
		return true;
	}

	/**
	 * @see org.eclipse.search.ui.ISearchQuery#getSearchResult()
	 */
	public ISearchResult getSearchResult() {
		if (searchResult == null) {
			searchResult = new MethodSearchResult(this);
		}
		return searchResult;
	}

	/**
	 * @see org.eclipse.search.ui.ISearchQuery#getSearchResult()
	 */
	public MethodSearchInput getSearchInput() {
		return searchInput;
	}

}
