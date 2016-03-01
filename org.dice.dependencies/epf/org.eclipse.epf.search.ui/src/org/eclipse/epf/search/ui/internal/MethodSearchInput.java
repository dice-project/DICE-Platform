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

import java.util.HashMap;
import java.util.Map;

/**
 * Encapsulates the search strings and options that are used as inputs to a
 * method search.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class MethodSearchInput {

	private String searchString;

	private String namePattern;

	private boolean caseSensitive;

	private boolean wholeWord;

	private boolean regExp;

	private MethodSearchScope searchScope;

	private Map<Object, Object> additionalInput;

	/**
	 * Creates a new instance.
	 * 
	 * @param searchString
	 *            a search string
	 * @param namePattern
	 *            a method element name pattern
	 * @param caseSensitive
	 *            if <code>true</code>, make the search case sensitive
	 * @param wholeWord
	 *            if <code>true</code>, search for whole word only
	 * @param regExp
	 *            if <code>true</code>, search using regular expression
	 * @param searchScope
	 *            a search scope
	 */
	public MethodSearchInput(String searchString, String namePattern,
			boolean caseSensitive, boolean wholeWord, boolean regExp,
			MethodSearchScope searchScope) {
		this.searchString = searchString;
		this.namePattern = namePattern;
		this.caseSensitive = caseSensitive;
		this.wholeWord = wholeWord;
		this.regExp = regExp;
		this.searchScope = searchScope;
	}

	/**
	 * Returns the search string.
	 * 
	 * @return the search string to look for the Method element content
	 */
	public String getSearchString() {
		return searchString;
	}

	/**
	 * Returns the name pattern.
	 * 
	 * @return the name pattern used to match the Method element names
	 */
	public String getNamePattern() {
		return namePattern;
	}

	/**
	 * Returns the case sensitivity of the search.
	 * 
	 * @return <code>true</code> if the search is case sensitive
	 */
	public boolean getCaseSensitive() {
		return caseSensitive;
	}

	/**
	 * Returns the whole word option of the search.
	 * 
	 * @return <code>true</code> if the search is based on whole word
	 */
	public boolean getWholeWord() {
		return wholeWord;
	}

	/**
	 * Returns the regular expression option of the search.
	 * 
	 * @return <code>true</code> if the search is based on regular expression
	 */
	public boolean getRegularExpression() {
		return regExp;
	}

	/**
	 * Returns the search scope.
	 * 
	 * @return the search scope
	 */
	public MethodSearchScope getSearchScope() {
		return searchScope;
	}

	public Map<Object, Object> getAdditionalInput() {
		if(additionalInput == null) {
			additionalInput = new HashMap<Object, Object>();
		}
		return additionalInput;
	}
}
