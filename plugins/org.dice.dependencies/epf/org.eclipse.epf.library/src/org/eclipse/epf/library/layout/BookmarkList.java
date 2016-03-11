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
package org.eclipse.epf.library.layout;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.epf.library.layout.util.XmlElement;


/**
 * This class manages a list of bookmarks
 * 
 * @author Jinhua Xi
 * @since 1.0
 */
public class BookmarkList {

	// a list of bookmark items
	private List bookmarks = new ArrayList();

	/**
	 * default constructor
	 *
	 */
	public BookmarkList() {
	}

	/**
	 * add a new bookmark  entry to the list
	 * @param name String the Bookmark name
	 * @param fileName String the file name of the bookmark
	 * @param setAsDefault boolean set the bookmark as default if true.
	 */
	public void addBookmark(String name, String fileName, boolean setAsDefault) {
		_BookmarkEntry entry = new _BookmarkEntry(name, fileName, setAsDefault);
		bookmarks.add(entry);
	}

	/**
	 * get a list of bookmark entries
	 * @return List
	 */
	public List getBookmarks() {
		return bookmarks;
	}

	/**
	 * get the xml document for the bookmark entries.
	 * 
	 * @return XmlElement
	 */
	public XmlElement getXmlElement() {
		XmlElement elementXml = new XmlElement("Bookmarks"); //$NON-NLS-1$

		for (Iterator it = bookmarks.iterator(); it.hasNext();) {
			_BookmarkEntry entry = (_BookmarkEntry) it.next();
			String bookmark = entry.fileName;

			// String tabName="";
			String url = ""; //$NON-NLS-1$
			int indx = bookmark.lastIndexOf("."); //$NON-NLS-1$
			if (indx > 0) {
				// tabName = bookmark.substring(0, indx).replace('_', ' ');
				url = bookmark.substring(0, indx) + ".html"; //$NON-NLS-1$
			}
			elementXml.newChild("Bookmark") //$NON-NLS-1$
					.setAttribute("name", bookmark) //$NON-NLS-1$
					.setAttribute("tabName", entry.tabName) //$NON-NLS-1$
					.setAttribute("url", url) //$NON-NLS-1$
					.setAttribute(
							"default", (entry.isDefault) ? "true" : "false"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}

		return elementXml;
	}

	private class _BookmarkEntry {
		private String fileName;

		private String tabName;

		boolean isDefault;

		_BookmarkEntry(String name, String fileName, boolean isDefault) {
			this.tabName = name.trim();
			this.fileName = fileName;
			this.isDefault = isDefault;
		}
	}

}
