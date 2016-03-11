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
package org.eclipse.epf.search;

import org.eclipse.epf.common.AbstractActivator;
import org.osgi.framework.BundleContext;

/**
 * The Search plug-in activator.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public final class SearchPlugin extends AbstractActivator {

	// The shared plug-in instance.
	private static SearchPlugin plugin;

	/**
	 * Creates a new instance.
	 */
	public SearchPlugin() {
		super();
		plugin = this;
	}

	/**
	 * @see AbstractPlugin#start(BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
	}

	/**
	 * @see AbstractPlugin#start(BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
		plugin = null;
	}

	/**
	 * Returns the shared plug-in instance.
	 */
	public static SearchPlugin getDefault() {	
		return plugin;
	}

}
