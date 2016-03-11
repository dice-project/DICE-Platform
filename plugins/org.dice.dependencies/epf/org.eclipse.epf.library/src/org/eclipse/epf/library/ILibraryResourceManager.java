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
package org.eclipse.epf.library;

import org.eclipse.epf.uma.MethodElement;

/**
 * interface to manage library resources
 * 
 * @author Jinhua Xi
 * @since 1.0
 *
 */
public interface ILibraryResourceManager {

	/**
	 * get the logical path of the element in the library,
	 * the path should start with the plugin name. 
	 * i.e. <plugin name>/path/to/element
	 * 
	 * The implementation class should encapsulate the physical storage 
	 * of the resource and return the path of the element start with it's plugin name
	 * 
	 * @param element MethodElement
	 * @return String
	 */
	public String getLogicalPath(MethodElement element);

	/**
	 * get the element's back path of the element based on the logical path, for example,
	 * "OpenUP\guidance\concept\c1.xml", the back path is "./../../../"
	 * 
	 * @param element MethodElement
	 * @return String
	 */
	public String getBackPath(MethodElement element);
	
	/**
	 * get the logical resource path of the element in the
	 * library. the path should start with the plugin name. 
	 * i.e. <plugin name>/path/to/element/resources
	 * 
	 * @param element MethodElement
	 * @return String
	 */
	public String getLogicalResourcePath(MethodElement element);
	
	/**
	 * Gets the physical path of a method element
	 * 
	 * @param element
	 * @return the physical path of the element
	 */
	public String getPhysicalPath(MethodElement element);

	/**
	 * Gets the absolute path of the resource folder of the element in the library.
	 * 
	 * @param e
	 * @return the physical resource path of the element
	 */
	public String getPhysicalResourcePath(MethodElement element);

	/**
	 * get the physical path of the plugin associated with the element
	 * 
	 * @param element
	 * @return String
	 */
	public String getPhysicalPluginPath(MethodElement element);

	/**
	 * get the logical path of the plugin 
	 * 
	 * @param element
	 * @return String
	 */
	public String getLogicalPluginPath(MethodElement element);

	
	/**
	 * get the physical path associated with the given logical path
	 * 
	 * @param element MethodElement an element in the library to access the library resource
	 * usually this is the owner element that references the logicalPath
	 * @param logicalPath String
	 * @return String the physical path resolved from the logical path
	 */
	public String resolve(MethodElement element, String logicalPath);

}
