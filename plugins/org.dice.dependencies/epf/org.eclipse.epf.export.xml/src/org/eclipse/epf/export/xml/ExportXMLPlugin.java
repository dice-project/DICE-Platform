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
package org.eclipse.epf.export.xml;

import org.eclipse.epf.common.ui.AbstractPlugin;
import org.osgi.framework.BundleContext;

/**
 * The Export XML plug-in class.
 * 
 * @author Jinhua Xi
 * @author Kelvin Low
 * @since 1.0
 */
public class ExportXMLPlugin extends AbstractPlugin {

	// The shared plug-in instance.
	private static ExportXMLPlugin plugin;

	/**
	 * Creates a new instance.
	 */
	public ExportXMLPlugin() {
		plugin = this;
	}

	/**
	 * @see AbstractPlugin#start(BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
	}

	/**
	 * @see AbstractPlugin#stop(BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
		plugin = null;
	}

	/**
	 * Returns the shared plug-in instance.
	 */
	public static ExportXMLPlugin getDefault() {
		return plugin;
	}

}
