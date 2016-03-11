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

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Pattern contructor for a method search.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class MethodSearchPattern {

	/**
	 * Private constructor to prevent this class from being instantiated. All
	 * methods in this class should be static.
	 */
	private MethodSearchPattern() {
	}

	/**
	 * Creates a
	 * <code>Pattern<code> object from the pattern string used in Method Search.
	 * 
	 * @param pattern
	 *            a search pattern string
	 * @param isCaseSensitive
	 *            if <code>true</code>, create a case insensitve pattern
	 * @param isRegexSearch
	 *            if <code>true</code>, treat the pattern as a regular expression pattern
	 * @return a new <code>Pattern<code> object
	 * @throws <code>PatternSyntaxException</code> if the specified pattern has a syntax error
	 */
	public static Pattern createPattern(String pattern,
			boolean isCaseSensitive, boolean isRegexSearch)
			throws PatternSyntaxException {
		if (!isRegexSearch) {
			pattern = toRegexPattern(pattern);
		}

		if (!isCaseSensitive) {
			return Pattern.compile(pattern, Pattern.CASE_INSENSITIVE
					| Pattern.UNICODE_CASE | Pattern.MULTILINE);
		}

		return Pattern.compile(pattern, Pattern.MULTILINE);
	}

	/**
	 * Converts a pattern string with '*' and '?' into a regular expression
	 * pattern string.
	 * 
	 * @param pattern
	 *            a pattern string
	 * @return a regular expression pattern string
	 */
	private static String toRegexPattern(String pattern) {
		int patternLength = pattern.length();
		StringBuffer result = new StringBuffer(patternLength);

		boolean escaped = false;
		boolean quoting = false;

		for (int i = 0; i < patternLength; i++) {
			char ch = pattern.charAt(i);
			switch (ch) {
			case '*':
				if (!escaped) {
					if (quoting) {
						result.append("\\E"); //$NON-NLS-1$
						quoting = false;
					}
				}
				result.append(".*"); //$NON-NLS-1$
				escaped = false;
				break;
			case '?':
				if (!escaped) {
					if (quoting) {
						result.append("\\E"); //$NON-NLS-1$
						quoting = false;
					}
				}
				result.append("."); //$NON-NLS-1$
				escaped = false;
				break;
			case '\\':
				if (!escaped) {
					escaped = true;
				} else {
					escaped = false;
					if (quoting) {
						result.append("\\E"); //$NON-NLS-1$
						quoting = false;
					}
					result.append("\\\\"); //$NON-NLS-1$
				}
				break;
			default:
				if (!quoting) {
					result.append("\\Q"); //$NON-NLS-1$
					quoting = true;
				}
				if (escaped && ch != '*' && ch != '?' && ch != '\\') {
					result.append('\\');
				}
				result.append(ch);
				escaped = (ch == '\\');
			}
		}

		if (quoting) {
			result.append("\\E"); //$NON-NLS-1$
		}

		return result.toString();
	}

}
