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
package org.eclipse.epf.export;

import org.eclipse.epf.common.ui.AbstractPlugin;
import org.osgi.framework.BundleContext;


/**
 * The Export plugin class.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class ExportPlugin extends AbstractPlugin {

	// The shared plug-in instance.
	private static ExportPlugin plugin;

	/**
	 * Default constructor.
	 */
	public ExportPlugin() {
		super();
		plugin = this;
	}

	/**
	 * @see org.eclipse.epf.uma.core.plugin.AbstractPlugin#start(BundleContext context)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
	}

	/**
	 * @see org.eclipse.epf.uma.core.plugin.AbstractPlugin#start(BundleContext context)
	 */
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
		plugin = null;
	}

	/**
	 * Returns the shared plug-in instance.
	 */
	public static ExportPlugin getDefault() {
		return plugin;
	}

}
