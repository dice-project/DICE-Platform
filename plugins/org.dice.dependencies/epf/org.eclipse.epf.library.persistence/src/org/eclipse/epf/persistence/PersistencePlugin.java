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
package org.eclipse.epf.persistence;

import org.eclipse.epf.common.AbstractActivator;
import org.osgi.framework.BundleContext;

/**
 * The activator class for the Library Persistence plug-in.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class PersistencePlugin extends AbstractActivator {

	// The shared plug-in instance.
	private static PersistencePlugin plugin;

	/**
	 * Creates a new instance.
	 */
	public PersistencePlugin() {
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
	 * @see AbstractPlugin#stop(BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
		plugin = null;
	}

	/**
	 * Returns the shared plug-in instance.
	 */
	public static PersistencePlugin getDefault() {
		return plugin;
	}

}