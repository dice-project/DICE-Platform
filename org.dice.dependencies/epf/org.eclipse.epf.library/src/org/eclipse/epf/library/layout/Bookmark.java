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
import java.util.List;

import org.eclipse.epf.library.layout.util.XmlElement;
import org.eclipse.epf.uma.util.UmaUtil;

/**
 * This class defines the bookmark data structure for the published bookmark entries. 
 * 
 * @author Jinhua Xi
 * @since 1.0
 */
public class Bookmark {

	static final long serialVersionUID = -3652582791326323863L;

	// points to the description file in the file system
	private String _fileName = ""; //$NON-NLS-1$

	// used as "closed" or "default" icon
	private String _closedIconName = ""; //$NON-NLS-1$

	// used as "open" icon
	private String _openIconName = ""; //$NON-NLS-1$

	// presentation name of the entry
	private String _presentationName = ""; //$NON-NLS-1$

	// whether mouse is over the bookmark
	// this is for backwards compatibility
	// private boolean _isMouseOver = false;

	// whether the file exists in the file system
	private boolean _isExist = true;

	// whether the file is from content library
	private boolean _isFromContentLibrary = true;

	// unique id.
	// A bookmark may not have a uniqueID in which case the
	// bookmark has no relation to any layout elements.
	private String _uniqueId = null;

	// whether the bookmark is visible
	private boolean _isVisible = true;

	// whether the bookmark is enabled
	// this is to support a special case where bookmarks
	// are to remain hidden and cannot be unhidden as
	// well as transparency
	private boolean _isEnabled = true;

	// whether the bookmark is transparent
	private boolean _isTransparent = false;

	// whether the bookmark is a default one
	// default means that it is published and is read-only
	private boolean _isDefault = false;

	// whether the bookmark is the current one
	// current means the first one to be focused on
	private boolean _isCurrent = false;

	private String queryString = ""; //$NON-NLS-1$
	
	private List children = new ArrayList();

	private Bookmark parent = null;
	
	// the owner element of the node
	private Object owner = null;
	
	/**
	 * Default constructor. Takes in the presentation name of the process layout
	 * entry.
	 */
	public Bookmark(String name) {
		_uniqueId = null;
		_presentationName = name;
		if (_presentationName == null) {
			_presentationName = ""; //$NON-NLS-1$
		}
	}

	/**
	 * Name and uniqueId for a layout node.
	 */
	public Bookmark(String presentationName, String uniqueId) {
		this(presentationName);
		_uniqueId = uniqueId;

	}

	public void setOwner(Object element) {
		this.owner = element;
	}
	
	public Object getOwner() {
		return this.owner;
	}
	
	/**
	 * Override to return presentation name as the user object.
	 */
	public Object getUserObject() {
		return (_presentationName);
	}

	/**
	 * set the user object
	 * @param userObject Object
	 */
	public void setUserObject(Object userObject) {
		_presentationName = (String) userObject;
		if (_presentationName == null) {
			_presentationName = ""; //$NON-NLS-1$
		}
	}

	/**
	 * set the presentation name
	 * 
	 * @param name String
	 */
	public void setPresentationName(String name) {
		_presentationName = name;
		if (_presentationName == null) {
			_presentationName = ""; //$NON-NLS-1$
		}
	}

	/**
	 * get the presentation name
	 * 
	 * @return String
	 */
	public String getPresentationName() {
		return (_presentationName);
	}

	/**
	 * set the file name
	 * 
	 * @param name String
	 */
	public void setFileName(String name) {
		_fileName = name;
		if (_fileName == null) {
			_fileName = ""; //$NON-NLS-1$
		}
	}

	/**
	 * get the file name
	 * 
	 * @return String
	 */
	public String getFileName() {
		return (_fileName);
	}

	/**
	 * set the icon name for the closed icon
	 * @param name String
	 */
	public void setClosedIconName(String name) {
		_closedIconName = name;
	}

	/**
	 * get the closed icon name
	 * 
	 * @return String
	 */
	public String getClosedIconName() {
		return (_closedIconName);
	}

	/**
	 * get the open icon name
	 * 
	 * @return String the open icon name
	 */
	public String getOpenIconName() {
		return (_openIconName);
	}

	/**
	 * set the open icon name
	 * 
	 * @param name String
	 */
	public void setOpenIconName(String name) {
		_openIconName = name;
	}

	/**
	 * set the exist flag
	 * 
	 * @param isExist boolean
	 */
	public void setExist(boolean isExist) {
		_isExist = isExist;
	}

	/**
	 * get the exist flag
	 * 
	 * @return boolean
	 */
	public boolean isExist() {
		return (_isExist);
	}

	/**
	 * this is a legacy method, not used any more
	 * @deprecated
	 * @param flag boolean
	 */
	public void setFromContentLibrary(boolean flag) {
		_isFromContentLibrary = flag;
	}

	/**
	 * this is a legacy method, not used any more
	 * @deprecated
	 * @return boolean
	 */
	public boolean isFromContentLibrary() {
		return (_isFromContentLibrary);
	}

	/**
	 * get the unique id of the bookmark
	 * 
	 * @return String
	 */
	public String getUniqueId() {
		return (_uniqueId);
	}

	/**
	 * set the unique id of the bookmark
	 * 
	 * @param uniqueId String
	 */
	public void setUniqueId(String uniqueId) {
		_uniqueId = uniqueId;
	}

	/**
	 * set the visible flag
	 * 
	 * @param isVisible boolean
	 */
	public void setVisible(boolean isVisible) {
		_isVisible = isVisible;
		// special case where all children need to change as well
		for (int i = 0; i < getChildCount(); i++) {
			Bookmark child = (Bookmark) ((Bookmark) getChildAt(i));
			child.setVisible(isVisible);
		}
	}

	/**
	 * get the visible flag
	 * 
	 * @return boolean
	 */
	public boolean isVisible() {
		return (_isVisible);
	}

	/**
	 * set the enabled flag
	 * 
	 * @param isEnabled boolean
	 */
	public void setEnabled(boolean isEnabled) {
		_isEnabled = isEnabled;
		// special case where all children need to change as well
		for (int i = 0; i < getChildCount(); i++) {
			Bookmark child = (Bookmark) ((Bookmark) getChildAt(i));
			child.setEnabled(isEnabled);
		}
	}

	/**
	 * get the enabled flag
	 * 
	 * @return boolean
	 */
	public boolean isEnabled() {
		return (_isEnabled);
	}

	/**
	 * set the transparant flag
	 * 
	 * @param isTransparent boolean
	 */
	public void setTransparency(boolean isTransparent) {
		// set at this bookmark only, not children
		_isTransparent = isTransparent;
	}

	/**
	 * get the transparent flag
	 * 
	 * @return boolean
	 */
	public boolean isTransparent() {
		return (_isTransparent);
	}

	/**
	 * set the bookmark as default
	 * 
	 * @param isDefault boolean
	 */
	public void setDefault(boolean isDefault) {
		_isDefault = isDefault;
	}

	/**
	 * check if this bookmark is set as default
	 * 
	 * @return boolean
	 */
	public boolean isDefault() {
		return (_isDefault);
	}

	/**
	 * set the current flag
	 * 
	 * @param isCurrent boolean
	 */
	public void setCurrent(boolean isCurrent) {
		_isCurrent = isCurrent;
	}

	/**
	 * get the current flag
	 * 
	 * @return boolean
	 */
	public boolean isCurrent() {
		return (_isCurrent);
	}

	/**
	 * get the number of children
	 * 
	 * @return int
	 */
	public int getChildCount() {
		return children.size();
	}

	/**
	 * Retrieves real child count regardless of visibility.
	 */
	public int getActualChildCount() {
		return (getChildCount());
	}

	/**
	 * get the specified child
	 * @param i int position of the child
	 * @return Object the child bookmark
	 */
	public Object getChildAt(int i) {
		return children.get(i);
	}

	/**
	 * same as getChildAt
	 * 
	 * @param i int position of the child
	 * @return Object the child
	 */
	public Object getActualChildAt(int i) {
		return children.get(i);
	}

	/**
	 * add a new child bookmark
	 * 
	 * @param child Bookmark
	 */
	public void addChild(Bookmark child) {
		children.add(child);
		child.parent = this;
	}

	public Bookmark getParent() {
		return this.parent;
	}
	
	/**
	 * get the query string to this bookmark.
	 * 
	 * @return String
	 */
	public String getQueryString()
	{
		return queryString;
	}
	
	/**
	 * set the query string
	 * 
	 * @param queryStr String
	 */
	public void setQueryString(String queryStr)
	{
		this.queryString = queryStr;
	}
	
	
	/**
	 * get the xml document for old look-and-feel bookmark.
	 * 
	 * @return XmlElement
	 */
	//paste the code copied from getXmlElement(), the only change is construct XmlElement with the ID generated by UmaUtil.generateGUID()  
	public XmlElement getXmlElementForOldLAF() {
		XmlElement elementXml = new XmlElement("Element", UmaUtil.generateGUID()) //$NON-NLS-1$
				.setAttribute("guid", getUniqueId()) //$NON-NLS-1$
				.setAttribute("name", getPresentationName().trim()) //$NON-NLS-1$
				.setAttribute("closedIconName", getClosedIconName()) //$NON-NLS-1$
				.setAttribute("openIconName", getOpenIconName()) //$NON-NLS-1$
				.setAttribute("exist", isExist() ? "true" : "false") //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				.setAttribute(
						"fromContentLibrary", isFromContentLibrary() ? "true" : "false") //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				.setAttribute("visible", isVisible() ? "true" : "false") //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				.setAttribute("enabled", isEnabled() ? "true" : "false") //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				.setAttribute("transparent", isTransparent() ? "true" : "false") //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				.setAttribute("default", isDefault() ? "true" : "false") //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				.setAttribute("current", isCurrent() ? "true" : "false"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		elementXml.setAttribute("closedIconId", getIconId(getClosedIconName())); //$NON-NLS-1$

		String qStr = getQueryString();
		if (qStr == null || qStr.length() == 0) {
			qStr = "?nodeId=" + elementXml.getId(); //$NON-NLS-1$
		} else {
			qStr += "&nodeId=" + elementXml.getId(); //$NON-NLS-1$
		}

		elementXml.setAttribute("url", getFileName() + qStr); //$NON-NLS-1$

		for (int i = 0; i < getChildCount(); i++) {
			Bookmark child = (Bookmark) getActualChildAt(i);
			elementXml.addChild(child.getXmlElementForOldLAF());
		}

		return elementXml;
	}
	
	/**
	 * get the xml document for this bookmark.
	 * 
	 * @return XmlElement
	 */
	public XmlElement getXmlElement() {
		XmlElement elementXml = new XmlElement("Element") //$NON-NLS-1$
				.setAttribute("guid", getUniqueId()) //$NON-NLS-1$
				.setAttribute("name", getPresentationName().trim()) //$NON-NLS-1$
				.setAttribute("closedIconName", getClosedIconName()) //$NON-NLS-1$
				.setAttribute("openIconName", getOpenIconName()) //$NON-NLS-1$
				.setAttribute("exist", isExist() ? "true" : "false") //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				.setAttribute(
						"fromContentLibrary", isFromContentLibrary() ? "true" : "false") //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				.setAttribute("visible", isVisible() ? "true" : "false") //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				.setAttribute("enabled", isEnabled() ? "true" : "false") //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				.setAttribute("transparent", isTransparent() ? "true" : "false") //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				.setAttribute("default", isDefault() ? "true" : "false") //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				.setAttribute("current", isCurrent() ? "true" : "false"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		elementXml.setAttribute("closedIconId", getIconId(getClosedIconName())); //$NON-NLS-1$
		
		String qStr = getQueryString();
		if ( qStr == null || qStr.length() == 0 ) {
			qStr = "?nodeId=" + elementXml.getId(); //$NON-NLS-1$
		} else {
			qStr += "&nodeId=" + elementXml.getId();  //$NON-NLS-1$
		}
		
		elementXml.setAttribute("url", getFileName() + qStr); //$NON-NLS-1$
	
		for (int i = 0; i < getChildCount(); i++) {
			Bookmark child = (Bookmark) getActualChildAt(i);
			elementXml.addChild(child.getXmlElement());
		}

		return elementXml;
	}

	public String getUrl() {
		return getFileName() + getQueryString();
	}
	
	public String getIconId(String iconFileName) {
		if ( iconFileName == null ) {
			return ""; //$NON-NLS-1$
		}

		return Integer.toHexString(iconFileName.hashCode());
	}
}