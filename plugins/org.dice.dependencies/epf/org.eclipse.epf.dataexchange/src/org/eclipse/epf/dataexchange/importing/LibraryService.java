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
package org.eclipse.epf.dataexchange.importing;

import java.util.Map;

import org.eclipse.epf.dataexchange.internal.importing.LibraryServiceImpl;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodPlugin;


/**
 * Library service for importing external library/plugins into the current library.
 * 
 * @author Jinhua Xi
 * @since 1.0
 *
 */
public interface LibraryService {

	public static LibraryService INSTANCE = new LibraryServiceImpl();
	/**
	 * create a new MethodPlugin and add to the library, perform initialization as needed
	 * @param name
	 * @return MethodPlugin 
	 */
	public MethodPlugin createPlugin(String name, String guid) throws Exception;

	/**
	 * create a new MethodPlugin and add to the library, perform initialization as needed
	 * @param name
	 * @param guid
	 * @param options
	 * @return MethodPlugin
	 */
	public MethodPlugin createPlugin(String name, String guid, Map options) throws Exception;

	
	/**
	 * create a MethodConfiguration for this library
	 * @param name
	 * @return MethodConfiguration
	 */
	public MethodConfiguration createConfiguration(String name, String guid);

	/**
	 * create a plugin service for the specified plugin
	 * @param plugin MethodPlugin
	 * @return PluginService
	 */
	public PluginService createPluginService(MethodPlugin plugin);
}