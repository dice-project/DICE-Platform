/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2010 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The main-class of the plugin. It is mostly used to access the 'environment'
 * of this plugin.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class GraphitiUIPlugin extends AbstractUIPlugin {

	public static final String PLUGIN_ID = "org.eclipse.graphiti.ui"; //$NON-NLS-1$

	private static GraphitiUIPlugin _plugin;

	/**
	 * Creates the Plugin and caches its default instance.
	 */
	public GraphitiUIPlugin() {
		super();
		_plugin = this;
	}

	// ============ overwritten methods of AbstractUIPlugin ====================

	/**
	 * This method is called upon plug-in activation.
	 * 
	 * @param context
	 *            the context
	 * @throws Exception
	 *             the exception
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
	}

	/**
	 * This method is called when the plug-in is stopped.
	 * 
	 * @param context
	 *            the context
	 * @throws Exception
	 *             the exception
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
	}

	/**
	 * Gets the default-instance of this plugin. Actually the default-instance
	 * should always be the only instance -> Singleton.
	 * 
	 * @return the default
	 */
	public static GraphitiUIPlugin getDefault() {
		return _plugin;
	}
}