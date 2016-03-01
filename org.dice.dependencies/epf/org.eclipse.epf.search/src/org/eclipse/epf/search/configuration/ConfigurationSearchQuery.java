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
package org.eclipse.epf.search.configuration;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * The published configuration search query.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class ConfigurationSearchQuery {

	private final static String CATEGORY_NAME = "name"; //$NON-NLS-1$

	private final static String CATEGORY_BRIEF_DESC = "briefDescription"; //$NON-NLS-1$

	private final static String CATEGORY_FULL_DESC = "fullDescription"; //$NON-NLS-1$

	private final static String CATEGORY_TYPE = "type"; //$NON-NLS-1$

	private Hashtable query;

	/**
	 * Creates a new instance.
	 */
	public ConfigurationSearchQuery() {
		query = new Hashtable();
		query.clear();
	}

	/**
	 * Returns the search query string.
	 * 
	 * @return the search query string
	 */
	public String getQueryString() {
		StringBuffer sb = new StringBuffer();
		Enumeration keys = query.keys();

		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();
			sb.append(key + ":(" + query.get(key) + ") "); //$NON-NLS-1$ //$NON-NLS-2$
			if (keys.hasMoreElements()) {
				sb.append("AND "); //$NON-NLS-1$
			}
		}

		return sb.toString();
	}

	private void setTerm(String term, String crit) {
		query.put(crit, term);
	}

	public void setType(String type) {
		setTerm(type, CATEGORY_TYPE);
	}

	public void setName(String name) {
		setTerm(name, CATEGORY_NAME);
	}

	public void setBriefDesc(String desc) {
		setTerm(desc, CATEGORY_BRIEF_DESC);
	}

	public void setFullDesc(String desc) {
		setTerm(desc, CATEGORY_FULL_DESC);
	}

}