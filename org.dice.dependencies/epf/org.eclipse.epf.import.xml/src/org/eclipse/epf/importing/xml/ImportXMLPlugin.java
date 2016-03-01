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
package org.eclipse.epf.importing.xml;

import org.eclipse.epf.common.ui.AbstractPlugin;
import org.eclipse.epf.xml.uma.UmaPackage;
import org.osgi.framework.BundleContext;

/**
 * The main plugin class to be used in the desktop.
 * 
 * @author Jinhua Xi
 * @since 1.0
 *
 */
public class ImportXMLPlugin extends AbstractPlugin {

	//The shared instance.
	private static ImportXMLPlugin plugin;
	private UmaPackage umaPackage;
	
	/**
	 * The constructor.
	 */
	public ImportXMLPlugin() {
		plugin = this;
		
		//Allow UmaPackage be initialized before any import action
		umaPackage = UmaPackage.eINSTANCE;
	}

	/**
	 * This method is called upon plug-in activation
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		
		// set this to allow parse large xml file
		System.setProperty("entityExpansionLimit", "100000000"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * This method is called when the plug-in is stopped
	 */
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
		plugin = null;
	}

	/**
	 * Returns the shared instance.
	 */
	public static ImportXMLPlugin getDefault() {
		return plugin;
	}

}
