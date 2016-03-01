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
package org.eclipse.epf.authoring.ui.editors;

import org.eclipse.epf.authoring.gef.util.DiagramUIResources;
import org.eclipse.epf.uma.MethodConfiguration;


/**
 * An editor input for the configuration editor
 * 
 * @author Shilpa Toraskar
 * @since 1.0
 */
public class ConfigurationEditorInput extends MethodElementEditorInput {

	/**
	 * Creates an instance
	 * @param config
	 */
	public ConfigurationEditorInput(MethodConfiguration config) {
		// methodConfiguration = config;
		super(config);
	}

	/**
	 * Returns method configuration
	 */
	public MethodConfiguration getConfiguration() {
		// return methodConfiguration;
		return (MethodConfiguration) super.getMethodElement();
	}


	/**
	 * 
	 * @see org.eclipse.ui.IEditorInput#getToolTipText()
	 */
	public String getToolTipText() {
		return DiagramUIResources.ConfigurationEditorInput_configeditor0; 
	}
}
