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
package org.eclipse.epf.common;

import org.eclipse.epf.common.preferences.IPreferenceStoreWrapper;
import org.osgi.framework.BundleContext;

/**
 * The Common plug-in activator.
 */
public final class CommonPlugin extends AbstractActivator {

	// The shared plug-in instance.
	private static CommonPlugin plugin;

	/**
	 * Creates a new instance.
	 */
	public CommonPlugin() {
		super();
		plugin = this;
	}

	/**
	 * @see org.eclipse.epf.common.ui.AbstractPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
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
	public static CommonPlugin getDefault() {
		return plugin;
	}

//	/**
//	 * set the store wrapper before it's being used.
//	 * @param storeWrapper
//	 */
	private IPreferenceStoreWrapper storeWrapper = null;
	
	public void setCommonPreferenceStore(IPreferenceStoreWrapper storeWrapper) {
		this.storeWrapper = storeWrapper;
	}
	
	public IPreferenceStoreWrapper getCommonPreferenceStore() {
		return storeWrapper;
	}
	
	// this is the context for message callback
	// for eclipse client, this is the Shell object
	IContextProvider contextProvider = null;

	public void setContextProvider(IContextProvider contextProvider) {
		this.contextProvider = contextProvider;
	}

	IMessageCallback msgCallback = null;
	public void setMsgCallback(IMessageCallback msgCallback) {
		this.msgCallback = msgCallback;
	}
}