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
package org.eclipse.epf.common.ui;

import org.eclipse.epf.common.CommonPlugin;
import org.eclipse.epf.common.IMessageCallback;
import org.eclipse.epf.common.ui.util.MsgBox;
import org.osgi.framework.BundleContext;

public class CommonUIPlugin extends AbstractPlugin {

	// The shared plug-in instance.
	private static CommonUIPlugin plugin;

	/**
	 * Creates a new instance.
	 */
	public CommonUIPlugin() {
		super();
		plugin = this;
	}

	/**
	 * @see org.eclipse.epf.common.ui.AbstractPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		
//		// set the preference store for the common plugin
		PreferenceStoreWrapper storeWrapper = new PreferenceStoreWrapper(getPreferenceStore());
		CommonPlugin.getDefault().setCommonPreferenceStore(storeWrapper);
		
		// create the message callback context for the non-ui plugins
		// this is the context for message callback
		// for eclipse client, this is the Shell object
		// shell object can't be shared acrooss thread, use a context provider instead
		//CommonPlugin.getDefault().setContext(MsgBox.getDefaultShell());
		
		CommonPlugin.getDefault().setContextProvider(new ContextProvider());
	
		// create the MessageCallback to be accessible to the non-ui plugin
		IMessageCallback msgCallback = new MessageCallback();
	}

	/**
	 * @see org.eclipse.epf.common.ui.AbstractPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
		plugin = null;
	}

	/**
	 * Gets the shared instance.
	 * 
	 * @return the shared plug-in instance
	 */
	public static CommonUIPlugin getDefault() {
		return plugin;
	}


}
