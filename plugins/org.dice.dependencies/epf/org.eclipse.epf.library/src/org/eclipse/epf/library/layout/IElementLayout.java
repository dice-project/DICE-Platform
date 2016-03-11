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

import java.util.List;

import org.eclipse.epf.library.layout.util.XmlElement;
import org.eclipse.epf.uma.MethodElement;


/**
 * This is the interface for element layout. any element layout should implement this interface.
 * An element layout defines how the element should be rendered in browsing and publishing.
 * 
 * @author Jinhua Xi
 * @since 1.0
 */
public interface IElementLayout {

	/**
	 * get the element id, which is the element's GUID
	 * 
	 * @return
	 */
	public String getId();

	/**
	 * the type of the element
	 * 
	 * @return String
	 */
	public String getType();

	/**
	 * the name of the element
	 * @return String
	 */
	public String getName();

	/**
	 * the realized presentation name of the element
	 * @return String
	 */
	public String getDisplayName();

	/**
	 * the url of the element
	 * @return String
	 */
	public String getUrl();

	/**
	 * the url for shape icon of the element
	 * @return String
	 */
	public String getShapeiconUrl();

	/**
	 * the url for node icon of the element
	 * @return String
	 */
	public String getNodeiconUrl();

	/**
	 * the url for diagram icon of the element, 
	 * for example, the icon for a role in the role-task-wp relationship diagram.
	 * @return String
	 */
	public String getDiagramiconUrl(); // icon for diagram such as Role diagram

	/**
	 * the layout xsl url
	 * @return String
	 */
	public String getXslUrl();

	/**
	 * the back path of the element relative to the publishing root dir.
	 * @return String
	 */
	public String getBackPath();

	/**
	 * get the file path relative to the publishing root
	 * 
	 * @return String
	 */
	public String getFilePath();

	/**
	 * get the file path relative to another element. This is the
	 * relativeTo.backpath + this.elementpath
	 * 
	 * @param relativeTo
	 * @return String
	 */
	public String getFilePath(IElementLayout relativeTo);

	/**
	 * get the file name of the element, with the given extension.
	 * @param ext String extension, such as ".html"
	 * @return String
	 */
	public String getFileName(String ext);

	/**
	 * get the element 
	 * @return MethodElement
	 */
	public MethodElement getElement();

	/**
	 * get the layout manager for randering the layout of the element.
	 * 
	 * @return ElementLayoutManager
	 */
	public ElementLayoutManager getLayoutMgr();

	/**
	 * get the XmlElement for the element layout.
	 * 
	 * @param includeReferences
	 *            true to include the xml of the referenced element. false to
	 *            ignore the xml of the references.
	 * @return XmlElement
	 */
	public XmlElement getXmlElement(boolean includeReferences);

	/**
	 * return a list of LayoutInfo objects
	 * 
	 * @return a list of LayoutInfo objects
	 */
	public List getLayouts();


	/**
	 * if the element's content is target for another element, set it here. for
	 * example, step content cat target for a Task or a task descriptor
	 * copyright content can target to different elements.
	 * 
	 * The purpose of this is that the system will fix the links in the content
	 * to relative to the target element.
	 */
	public void setContentTarget(MethodElement targetElement);

	/**
	 * need to set the owner of the current layout element. In most cases this
	 * should be the eContainer of the element. This is needed because in some
	 * situation the element does not have an owner when the object is created.
	 * For example, the ContentDescription object's eContiner is null if the
	 * content file is not saved.
	 * 
	 * @param owner
	 *            MethodElement
	 */
	public void setElementOwner(MethodElement owner);

	/**
	 * check if the html content generated from this xsl file needs to be scanned or not
	 * scan the content is for identifying element references in the content and copy over resource files
	 * in some cases we don't need to scan the content, for example, the activity breakdown structure
	 * 
	 * @param xslUrl the xsl that html is generated from, null for the default xsl layout
	 * @return boolean
	 */
	public boolean needContentScan(String xslUrl);
	
	/**
	 * 
	 * @param show
	 * @deprecated this method is not needed any more
	 */
	public void setShowElementLink(boolean show);
	

	public String getNoAdjustedElementPath();
	
}
