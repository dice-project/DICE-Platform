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

import java.util.List;

import org.eclipse.epf.authoring.ui.preferences.LibraryLocationData;
import org.eclipse.epf.uma.MethodConfiguration;


/**
 * Encapsulates the input data required to export a library configuration.
 * 
 * @author Jinhua Xi
 * @since 1.0
 */
public class ConfigurationExportData {

	public boolean exportOneConfig;

	public boolean exportConfigSpecs;

	public List selectedConfigs;

	/**
	 * Indicates if the broken references should be removed or not.
	 */
	public boolean removeBrokenReferences = false;

	public LibraryLocationData llData = null;

	public String errorMsg = ""; //$NON-NLS-1$

	/**
	 * Creates a nes instance.
	 */
	public ConfigurationExportData() {
		llData = new LibraryLocationData();
		llData.loadFromPreferenceStore();
	}

	public MethodConfiguration getFirstConfguration() {
		if (selectedConfigs == null || selectedConfigs.isEmpty()) {
			return null;
		}
		Object obj = selectedConfigs.get(0);
		if (obj instanceof MethodConfiguration) {
			return (MethodConfiguration) obj;
		}
		
		return null;
	}
	
	/**
	 * Validate attribute
	 */
	public boolean validate() {
		return true;
	}

}
