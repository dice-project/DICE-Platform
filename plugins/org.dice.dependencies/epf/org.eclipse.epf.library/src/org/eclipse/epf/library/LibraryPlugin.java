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
package org.eclipse.epf.library;

import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.epf.common.AbstractActivator;
import org.eclipse.epf.library.layout.LayoutResources;
import org.osgi.framework.BundleContext;

/**
 * The Library plug-in class.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class LibraryPlugin extends AbstractActivator {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.epf.library"; //$NON-NLS-1$
	
	// The shared plug-in instance.
	private static LibraryPlugin plugin;

	/**
	 * Creates a new instance.
	 */
	public LibraryPlugin() {
		super();
		plugin = this;
	}

	/**
	 * @see org.eclipse.epf.common.ui.AbstractPlugin#start(BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		
		LibraryService.getInstance().getLibraryProblemMonitor();
	}

	/**
	 * @see org.eclipse.epf.common.ui.AbstractPlugin#start(BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
		plugin = null;

		LayoutResources.clear();
	}

	/**
	 * Returns the shared plug-in instance.
	 */
	public static LibraryPlugin getDefault() {
		return plugin;
	}
}