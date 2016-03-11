/*******************************************************************************
 * Copyright (c) 2005, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial implementation
 *******************************************************************************/
package org.eclipse.epf.library.xmi;

import org.eclipse.epf.common.AbstractActivator;
import org.osgi.framework.BundleContext;

/**
 * The XMI Library plug-in activator.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class XMILibraryPlugin extends AbstractActivator {

	// The shared plug-in instance.
	private static XMILibraryPlugin plugin;

	/**
	 * Default constructor.
	 */
	public XMILibraryPlugin() {
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
	public static XMILibraryPlugin getDefault() {
		return plugin;
	}

}
