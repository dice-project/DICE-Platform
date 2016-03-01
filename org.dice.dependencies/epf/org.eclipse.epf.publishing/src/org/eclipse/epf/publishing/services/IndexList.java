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
package org.eclipse.epf.publishing.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.epf.library.layout.util.XmlElement;

/**
 * Holds all glossary items and sort them in desired order grouped by first
 * letter of the glossary name TODO: Add a brief description for this class.
 * 
 * @author Jinhua Xi
 * @since 1.0
 */
public class IndexList {

	// using the default order from the configuration explorer.
	// maybe a special sorted list later,
	// TreeMap of the first letter of the glossary map to a (Sorted) List of
	// glossary Items
	private TreeMap itemMap = new TreeMap();

	/**
	 * default constructor
	 *
	 */
	public IndexList() {
	}

	/**
	 * inislize the list
	 *
	 */
	public void clear() {
		itemMap.clear();
	}

	/**
	 * add an entry to the list
	 * 
	 * @param term String
	 * @param content String
	 * @param url String
	 */
	public void add(String term, String content, String url) {
		IndexItem item = new IndexItem(term, content, url);
		String group = "" + item.termName.charAt(0); //$NON-NLS-1$
		getItemList(group).add(item);
	}

	private List getItemList(String group) {
		group = group.toUpperCase();
		List l = (List) itemMap.get(group);
		if (l == null) {
			l = new ArrayList();
			itemMap.put(group, l);
		}

		return l;
	}

	/**
	 * get the xml document for the list
	 * 
	 * @return StringBuffer
	 */
	public StringBuffer getXml() {
		XmlElement xe = new XmlElement("Index"); //$NON-NLS-1$

		for (Iterator it = itemMap.entrySet().iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			String group = (String) entry.getKey();
			List items = (List) entry.getValue();

			// create a bookmark for the group
			xe.newChild("item") //$NON-NLS-1$
					.setAttribute("name", group) //$NON-NLS-1$
					.setAttribute("presentationName", group) //$NON-NLS-1$
					.setAttribute("navigation-mark", "true") //$NON-NLS-1$ //$NON-NLS-2$
					.setAttribute("content", ""); //$NON-NLS-1$ //$NON-NLS-2$

			// then create entry for each item
			for (Iterator itItem = items.iterator(); itItem.hasNext();) {
				IndexItem item = (IndexItem) itItem.next();
				xe.newChild("item") //$NON-NLS-1$
						.setAttribute("name", item.termName) //$NON-NLS-1$
						.setAttribute("presentationName", item.termName) //$NON-NLS-1$
						.setAttribute("content", item.content) //$NON-NLS-1$
						.setAttribute("url", item.url); //$NON-NLS-1$
			}
		}

		return xe.toXml();
	}

	class IndexItem {
		public String termName;

		public String content;

		public String url;

		public IndexItem(String termName, String content, String url) {
			this.termName = termName;
			this.content = content;
			this.url = url;
		}
	}

}
