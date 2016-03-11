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

import java.util.EventListener;

import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodLibrary;

/**
 * The interface for a Library Service listener.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public interface ILibraryServiceListener extends EventListener {

	/**
	 * Receives notification when a method library has been created.
	 * 
	 * @param library
	 *            a method library
	 */
	public void libraryCreated(MethodLibrary library);

	/**
	 * Receives notification when a method library has been opened.
	 * 
	 * @param library
	 *            a method library
	 */
	public void libraryOpened(MethodLibrary library);

	/**
	 * Receives notification when a method library has been reopened.
	 * 
	 * @param library
	 *            a method library
	 */
	public void libraryReopened(MethodLibrary library);

	/**
	 * Receives notification when a method library has been closed.
	 * 
	 * @param library
	 *            a method library
	 */
	public void libraryClosed(MethodLibrary library);

	/**
	 * Receives notification when a method library has been set as the current
	 * method library.
	 * 
	 * @param library
	 *            a method library
	 */
	public void librarySet(MethodLibrary library);

	/**
	 * Receives notification when a method configuration has been set as the
	 * current method configuration.
	 * 
	 * @param config
	 *            a method configuration
	 */
	public void configurationSet(MethodConfiguration config);

}
