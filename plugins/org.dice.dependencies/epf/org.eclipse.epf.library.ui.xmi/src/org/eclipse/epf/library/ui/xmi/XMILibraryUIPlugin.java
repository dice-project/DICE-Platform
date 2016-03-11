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
package org.eclipse.epf.library.ui.xmi;

import org.eclipse.epf.common.ui.AbstractPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class for the <code>org.eclipse.epf.library.ui.xmi</code>
 * plug-in.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class XMILibraryUIPlugin extends AbstractPlugin {

	/**
	 * The plug-in ID.
	 */
	public static final String PLUGIN_ID = XMILibraryUIPlugin.class
			.getPackage().getName();

	// The shared instance
	private static XMILibraryUIPlugin plugin;

	/**
	 * The constructor
	 */
	public XMILibraryUIPlugin() {
		plugin = this;
	}

	/*
	 * @see org.eclipse.epf.common.plugin.AbstractPlugin#start(BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
	}

	/*
	 * @see org.eclipse.epf.common.plugin.AbstractPlugin#stop(BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return The shared instance
	 */
	public static XMILibraryUIPlugin getDefault() {
		return plugin;
	}

}
