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
package org.eclipse.epf.export.xml.services;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodPlugin;

/**
 * Stores the selections and settings made by the user in the Export XML wizard.
 * 
 * @author Jinhua Xi
 * @author Kelvin Low
 * @since 1.0
 */
public class ExportXMLData {

	public static final int EXPORT_METHOD_LIBRARY = 1;

	public static final int EXPORT_METHOD_PLUGINS = 2;

	public static final int EXPORT_METHOD_CONFIGS = 3;

	protected int exportType = EXPORT_METHOD_LIBRARY;

	protected Collection<MethodPlugin> selectedPlugins;
	
	protected HashSet associatedConfigs;

	protected List selectedConfigs;

	protected String xmlFile;

	/**
	 * Gets the user specified export type.
	 * 
	 * @return the type of export operation to perform.
	 */
	public int getExportType() {
		return exportType;
	}

	/**
	 * Sets the user specified export type.
	 * 
	 * @param exporType
	 *            the type of export operation to perform
	 */
	public void setExportType(int exportType) {
		this.exportType = exportType;
	}

	/**
	 * Gets the user selected method plug-ins.
	 * 
	 * @return a list of method plug-ins
	 */
	public Collection<MethodPlugin> getSelectedPlugins() {
		return selectedPlugins;
	}

	/**
	 * Sets the associated configs with respected to selected plug-ins
	 * 
	 * @param associatedConfigMap
	 *           Map of selected plug-ins to associated configs
	 */
	public void setAssociatedConfigs(Map associatedConfigMap) {
		if (associatedConfigMap == null || associatedConfigMap.isEmpty()) {
			return;
		}
		associatedConfigs = new HashSet(); 
		for (Iterator it = associatedConfigMap.values().iterator(); it.hasNext();) {
			List configList = (List) it.next();
			int sz = configList == null ? 0 : configList.size();
			for (int i=0; i<sz; i++) {
				MethodConfiguration config = (MethodConfiguration) configList.get(i);
				if (!associatedConfigs.contains(config.getGuid())) {
					associatedConfigs.add(config.getGuid());
				}
			}
		}
	}

	/**
	 * Sets the user selected method plug-ins.
	 * 
	 * @param plugins
	 *            a list of method plug-ins
	 */
	public void setSelectedPlugins(Collection<MethodPlugin> plugins) {
		selectedPlugins = plugins;
	}
	
	/**
	 * Gets the user selected method configurations.
	 * 
	 * @return a list of method configurations
	 */
	public List getSelectedConfigs() {
		return selectedConfigs;
	}

	/**
	 * Sets the user selected method configurations.
	 * 
	 * @param configs
	 *            a list of method configurations
	 */
	public void setSelectedConfigs(List configs) {
		selectedConfigs = configs;
	}

	/**
	 * Gets the user specified destination XML file.
	 * 
	 * @return an absolute path to the XML file
	 */
	public String getXMLFile() {
		return xmlFile;
	}

	/**
	 * Sets the user specified destination XML file.
	 * 
	 * @param xmlFile
	 *            an absolute path to the XML file
	 */
	public void setXMLFile(String xmlFile) {
		this.xmlFile = xmlFile;
	}

}
