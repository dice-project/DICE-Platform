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
package org.eclipse.epf.export.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.eclipse.epf.authoring.ui.preferences.LibraryLocationData;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.uma.MethodPlugin;


/**
 * Encapsulates the input data required to export a method plug-in.
 * 
 * @author Jinhua Xi
 * @author Kelvin Low
 * @since 1.0
 */
public class PluginExportData {

	public Collection<MethodPlugin> selectedPlugins = null;

	public LibraryLocationData llData = null;

	public Hashtable associatedConfigMap = new Hashtable();

	// don't need it after the service code changes
	public List associatedConfigs = new ArrayList();

	/**
	 * Creates a new instance.
	 */
	public PluginExportData() {
		llData = new LibraryLocationData();
		llData.loadFromPreferenceStore();
	}

	/**
	 * Returns the list of selected plugins.
	 */
	public Collection<MethodPlugin> getSelectedPlugins() {
		return selectedPlugins;
	}

	/**
	 * Sets the list of selected plugins.
	 */
	public void setSelectedPlugins(Collection<MethodPlugin> selectedPlugins) {
		this.selectedPlugins = selectedPlugins;
	};

	/**
	 * validate attribute
	 */
	public boolean validate() {
		return true;
	}
	
	/**
	 * Builds associatedConfigMap.
	 */
	public void buildAssociatedConfigMap() {
		if (getSelectedPlugins() != null) {
			associatedConfigMap.clear();
			for (Iterator iter = getSelectedPlugins().iterator(); iter
					.hasNext();) {
				MethodPlugin element = (MethodPlugin) iter.next();
				associatedConfigMap.put(element, LibraryUtil
						.getAssociatedConfigurations(element));
			}
		}
	}

}
