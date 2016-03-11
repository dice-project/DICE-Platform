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
package org.eclipse.epf.importing.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.epf.authoring.ui.preferences.LibraryLocationData;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodPlugin;


/**
 * Encapsulates the input data required to import a method plug-in.
 * 
 * @author Jinhua Xi
 * @since 1.0
 */
public class PluginImportData {

	// these are set by the wizard
	public LibraryLocationData llData = new LibraryLocationData();

	public boolean deleteExistingPlugins;

	// these are filled in by the importing service
	public String libraryVersion;

	private List<PluginInfo> plugins = new ArrayList<PluginInfo>();

	private List configs = new ArrayList();

	private ErrorInfo error = new ErrorInfo();

	// ap the guid to url for base plugin, this is the only formation available
	public Map basePluginUrlMap = new HashMap();

	/**
	 * Creates a new instance.
	 */
	public PluginImportData() {
	}

	/**
	 * Clears the contents.
	 */
	public void clear() {
		plugins.clear();
		configs.clear();
		error.errorMessage.setLength(0);
	}

	/**
	 * Validates the plugins.
	 */
	public void validatePlugins() {
		PluginInfo info;
		int i = 0;
		while (i < plugins.size()) {
			info = (PluginInfo) plugins.get(i);
			if (info.url == null) {
				plugins.remove(i);
			} else {
				i++;
			}
		}
	}

	/**
	 * Returns a PluginInfo object given the guid.
	 */
	public PluginInfo getPluginInfo(String guid) {
		PluginInfo info;
		for (Iterator<PluginInfo> it = plugins.iterator(); it.hasNext();) {
			info = (PluginInfo) it.next();
			if (info.guid.equals(guid)) {
				return info;
			}
		}

		return null;
	}

	/**
	 * Removes a PluginInfo object given the guid.
	 */
	public void removePluginInfo(String guid) {
		PluginInfo info;
		for (Iterator<PluginInfo> it = plugins.iterator(); it.hasNext();) {
			info = (PluginInfo) it.next();
			if (info.guid.equals(guid)) {
				plugins.remove(info);
				break;
			}
		}

	}

	/**
	 * Returns a ConfiguarationInfo object given the guid.
	 */
	public ConfiguarationInfo getConfigInfo(String guid) {

		if ( guid == null ) {
			return null;
		}
		PluginImportData.ConfiguarationInfo cinfo;
		for (Iterator it = getConfigs().iterator(); it.hasNext();) {
			cinfo = (PluginImportData.ConfiguarationInfo) it.next();
			if (guid.equals(cinfo.guid) ) {
				return cinfo;
			}
		}
		
		return null;
	}
	
	
	/**
	 * return a list of PluginInfo object
	 * 
	 * @return List a list of PluginInfo objects
	 */
	public List<PluginInfo> getPlugins() {
		return plugins;
	}

	/**
	 * return a list of ConfigInfo objects
	 * 
	 * @return List
	 */
	public List getConfigs() {
		return configs;
	}

	/**
	 * return the error message if any
	 * 
	 * @return String
	 */
	public ErrorInfo getErrorInfo() {
		return error;
	}

	public class PluginInfo {

		// If not null, this is a valid plug-in.
		public String url = null;

		public String name;

		public String guid;

		public String version;

		public String brief_desc;

		public String authors;

		public String changeDate;

		public List usedPlugins = new ArrayList();

		// If null, this plug-in does not exists in the current library.
		public MethodPlugin existingPlugin = null;

		// If true, replace the current plug-in with the one in the import
		// library. If false, don't import the plug-in.
		public boolean selected = false;

		public PluginInfo() {
		}

		public String toString() {
			return name;
		}
	}

	public class ConfiguarationInfo {

		public String name;

		public String guid;

		public String version;

		// The current configuration.
		public MethodConfiguration existingConfig = null;

		public boolean selected = true;

		public ConfiguarationInfo() {
		}

	}

	public class ErrorInfo {

		private StringBuffer errorMessage = new StringBuffer();

		public ErrorInfo() {

		}

		public void addError(String message) {
			errorMessage.append(message);
		}

		public String getError() {
			return errorMessage.toString();
		}

		public void clear() {
			errorMessage.setLength(0);
		}
	}

}
