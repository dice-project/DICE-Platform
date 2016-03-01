//------------------------------------------------------------------------------
// Copyright (c) 2005, 2007 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.library;

import java.net.URI;
import java.util.Map;

import org.eclipse.epf.library.util.LibraryProblemMonitor;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodLibrary;

/**
 * The Library Service API.
 * 
 * @author Kelvin Low
 * @author Jinhua Xi
 * @since 1.0
 */
public interface ILibraryService {

	/**
	 * Creates a new method library.
	 * 
	 * @param name
	 *            a name for the new method library
	 * @param type
	 *            the method library type
	 * @param args
	 *            method library specific arguments
	 * @return a method library
	 * @throw <code>LibraryServiceException</code> if an error occurs while
	 *        performing the operation
	 */
	public MethodLibrary createMethodLibrary(String name, String type,
			Map<String, Object> params) throws LibraryServiceException;

	/**
	 * Opens an existing method library.
	 * 
	 * @param type
	 *            the method library type
	 * @param uri
	 *            the method library URI
	 * @return a method library
	 * @throw <code>LibraryServiceException</code> if an error occurs while
	 *        performing the operation
	 */
	public MethodLibrary openMethodLibrary(String type,
			URI uri) throws LibraryServiceException;

	/**
	 * Opens an existing method library.
	 * 
	 * @param type
	 *            the method library type
	 * @param params
	 *            method library specific arguments
	 * @return a method library
	 * @throw <code>LibraryServiceException</code> if an error occurs while
	 *        performing the operation
	 */
	public MethodLibrary openMethodLibrary(String type,
			Map<String, Object> params) throws LibraryServiceException;

	/**
	 * Opens the last opened method library.
	 * 
	 * @return a method library or <code>null</code>
	 */
	public MethodLibrary openLastOpenedMethodLibrary();

	/**
	 * Reopens a method library.
	 * 
	 * @param library
	 *            a method library
	 * @return a method library
	 * @throw <code>LibraryServiceException</code> if an error occurs while
	 *        performing the operation
	 */
	public MethodLibrary reopenMethodLibrary(MethodLibrary library)
			throws LibraryServiceException;

	/**
	 * Reopens the current method library.
	 * 
	 * @return a method library
	 * @throw <code>LibraryServiceException</code> if an error occurs while
	 *        performing the operation
	 */
	public MethodLibrary reopenCurrentMethodLibrary()
			throws LibraryServiceException;

	/**
	 * Saves a method library.
	 * 
	 * @param library
	 *            a method library
	 * @throw <code>LibraryServiceException</code> if an error occurs while
	 *        performing the operation
	 */
	public void saveMethodLibrary(MethodLibrary library)
			throws LibraryServiceException;

	/**
	 * Saves the current method library.
	 * 
	 * @throw <code>LibraryServiceException</code> if an error occurs while
	 *        performing the operation
	 */
	public void saveCurrentMethodLibrary() throws LibraryServiceException;

	/**
	 * Closes a method library.
	 * <p>
	 * This automatically disposes its library manager and the configuration
	 * managers that manage the method configurations in the method library.
	 * 
	 * @param library
	 *            a method library
	 * @throw <code>LibraryServiceException</code> if an error occurs while
	 *        performing the operation
	 */
	public void closeMethodLibrary(MethodLibrary library)
			throws LibraryServiceException;

	/**
	 * Closes the current method library.
	 * 
	 * @throw <code>LibraryServiceException</code> if an error occurs while
	 *        performing the operation
	 */
	public void closeCurrentMethodLibrary() throws LibraryServiceException;

	/**
	 * Replaces a the method library.
	 * 
	 * @param oldLibrary
	 *            the old method library
	 * @param newLibrary
	 *            the new method library
	 */
	public void replaceMethodLibrary(MethodLibrary oldLibrary,
			MethodLibrary newLibrary);

	/**
	 * Adds a listener to monitor Library Service events.
	 * 
	 * @param listener
	 *            a library service listener
	 */
	public void addListener(ILibraryServiceListener listener);

	/**
	 * Removes a listener that was added to monitor Library Service events.
	 * 
	 * @param listener
	 *            a library service listener
	 */
	public void removeListener(ILibraryServiceListener listener);

	/**
	 * Gets the current method library.
	 * 
	 * @return a method library
	 */
	public MethodLibrary getCurrentMethodLibrary();

	/**
	 * Sets the current method library.
	 * 
	 * @param library
	 *            a method library
	 */
	public void setCurrentMethodLibrary(MethodLibrary library);

	/**
	 * Gets the current method library location path.
	 * <p>
	 * Note: A file-based method library may return <code>null</code>.
	 * 
	 * @return an absolute path to the current method library
	 */
	public String getCurrentMethodLibraryLocation();

	/**
	 * Gets the library manager for a method library.
	 * 
	 * @param library
	 *            a method library
	 * @return a library manager
	 */
	public ILibraryManager getLibraryManager(MethodLibrary library);

	public void setLibraryManager(ILibraryManager libMgr);

	public void removeLibraryManager(ILibraryManager libMgr);

	/**
	 * Gets the library manager for the current method library.
	 * 
	 * @return a library manager
	 */
	public ILibraryManager getCurrentLibraryManager();

	/**
	 * Creates a new method configuration.
	 * 
	 * @param name
	 *            a name for the new method configuration
	 * @param library
	 *            the containing method library
	 * @return a method configuration
	 * @throw <code>LibraryServiceException</code> if an error occurs while
	 *        performing the operation
	 */
	public MethodConfiguration createMethodConfiguration(String name,
			MethodLibrary library) throws LibraryServiceException;

	/**
	 * Gets the current method configuration.
	 * 
	 * @return a method configuration
	 */
	public MethodConfiguration getCurrentMethodConfiguration();

	/**
	 * Sets the current method configuration.
	 * 
	 * @param config
	 *            a method configuration
	 */
	public void setCurrentMethodConfiguration(MethodConfiguration config);

	/**
	 * Gets the configuration manager for a method configuration.
	 * 
	 * @param config
	 *            a method configuration
	 * @return a configuration manager
	 */
	public IConfigurationManager getConfigurationManager(
			MethodConfiguration config);

	public void removeConfigurationManager(MethodConfiguration config);

	public void removeConfigurationManagers(MethodLibrary library);

	/**
	 * Gets the configuration manager for the current method configuration.
	 * 
	 * @return a configuration manager
	 */
	public IConfigurationManager getCurrentConfigurationManager();
	
	/**
	 * Register a loaded method library.
	 * 
	 * @param type
	 *            the given loaded method library
	 * @param type
	 *            the method library type
	 * @param params
	 *            method library specific arguments
	 * @throw <code>LibraryServiceException</code> if an error occurs while
	 *        performing the operation
	 */
	public void  registerMethodLibrary(MethodLibrary lib, String type,
			Map<String, Object> params) throws LibraryServiceException;
	
	/**
	 * Unregister a registered method library.
	 * 
	 * @param type
	 *            the given registered method library
	 * @throw <code>LibraryServiceException</code> if an error occurs while
	 *        performing the operation
	 */
	public void unRegisterMethodLibrary(MethodLibrary lib) throws LibraryServiceException;
	
	/**
	 * @return the libary problem monitor object assocated with this manager
	 */
	public LibraryProblemMonitor getLibraryProblemMonitor();

}
