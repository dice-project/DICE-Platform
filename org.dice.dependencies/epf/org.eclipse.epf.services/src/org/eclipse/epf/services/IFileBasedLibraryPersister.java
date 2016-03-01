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
package org.eclipse.epf.services;

import java.io.File;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodLibrary;

/**
 * The interface for a file-based Method Library Persister.
 * <p>
 * A Method Library Persister is responsible for persisting the method library
 * content.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public interface IFileBasedLibraryPersister extends ILibraryPersister {

	/**
	 * Gets the path of a method element's folder relative to its plug-in or
	 * library folder.
	 * 
	 * @param e
	 *            a method element
	 * @return a relative path name
	 */
	public String getFolderRelativePath(MethodElement e);

	/**
	 * Gets the absolute path of a method element's folder.
	 * 
	 * @param e a method element
	 * @return the absolute path
	 */
	public String getFolderAbsolutePath(MethodElement e);
	
	/**
	 * Gets the file extension of a method element's resource file.
	 * 
	 * @param e
	 *            a method element
	 * @return a file extension name
	 */
	public String getFileExtension(Object e);

	/**
	 * Checks whether a method element stores its content in a folder named
	 * after it.
	 * 
	 * @param e
	 *            a method element
	 * @return <code>true</code> if the method element stores its content in a
	 *         folder named after it
	 */
	public boolean hasOwnFolder(Object e);
	
	/**
	 * Creates folder for the method plugin with the specified name in the given method library.
	 *  
	 * @param pluginName the name of method plugin
	 * @param library the library where the method plugin folder will be created
	 * @return the method plugin folder
	 * @exception RuntimeException if folder creation failed
	 */
	public File createMethodPluginFolder(String pluginName, MethodLibrary library);
	
	/**
	 * Gets the default folder to store method configuration in the specified method library.
	 * 
	 * @param library the method library
	 * @return the default folder to store method configuration or null
	 * @exception RuntimeException if failed
	 */
	public File getDefaultMethodConfigurationFolder(MethodLibrary library);
	
	public File getDefaultMethodConfigurationFolder(MethodLibrary library, boolean create);
	
	/**
	 * Sets the default folder to store method configuration in the specified method library.
	 * 
	 * @param library the method library
	 * @param file the default folder to store method configuration
	 */
	public void setDefaultMethodConfigurationFolder(MethodLibrary library, File file);
	
	/**
	 * Gets the absolute path of the resource folder of the element in the library.
	 * 
	 * @param e
	 * @return absolute path of the element's resource folder
	 */
	public String getResourceFolderPath(MethodElement e);
	
	/**
	 * Gets file in local file system of the specified resource
	 *  
	 * @param resource
	 * @return the file in local file system
	 */
	public File getFile(Resource resource);
}