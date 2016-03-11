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
package org.eclipse.epf.authoring.ui;

import org.eclipse.epf.authoring.ui.internal.ImageDescriptorRegistry;
import org.eclipse.epf.authoring.ui.internal.ProblemMarkerManager;
import org.eclipse.epf.authoring.ui.preferences.LibraryViewPrefPage;
import org.eclipse.epf.common.serviceability.Logger;
import org.eclipse.epf.common.ui.AbstractPlugin;
import org.osgi.framework.BundleContext;

/**
 * The Authoring UI plug-in class.
 * 
 * @author Kelvin Low
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class AuthoringUIPlugin extends AbstractPlugin {

	// The shared plug-in instance.
	private static AuthoringUIPlugin plugin;

	private ProblemMarkerManager fProblemMarkerManager;
	private ImageDescriptorRegistry fImageDescriptorRegistry;

	/**
	 * Creates a new instance.
	 */
	public AuthoringUIPlugin() {
		super();
		plugin = this;
	}

	
	/**
	 * @see org.eclipse.epf.common.ui.AbstractPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		
		// Initialize the Authoring UI service.
		AuthoringUIService.getInstance().start();
		Logger.SeeLogFileForMoreDetails = AuthoringUIResources.editors_MethodElementEditor_saveErrorReason1;
	}

	/**
	 * @see org.eclipse.epf.common.ui.AbstractPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
		plugin = null;
	}
	
	@Override
	protected void init(BundleContext context) throws Exception {
		super.init(context);
		
		LibraryViewPrefPage.initDefaults(getDefault().getPreferenceStore());		
	}

	/**
	 * Returns the shared plug-in instance.
	 */
	public static AuthoringUIPlugin getDefault() {
		return plugin;
	}
	
	public synchronized ProblemMarkerManager getProblemMarkerManager() {
		if (fProblemMarkerManager == null)
			fProblemMarkerManager= new ProblemMarkerManager();
		return fProblemMarkerManager;
	}
	
	public static ImageDescriptorRegistry getImageDescriptorRegistry() {
		return getDefault().internalGetImageDescriptorRegistry();
	}

	private synchronized ImageDescriptorRegistry internalGetImageDescriptorRegistry() {
		if (fImageDescriptorRegistry == null)
			fImageDescriptorRegistry= new ImageDescriptorRegistry();
		return fImageDescriptorRegistry;
	}
	
	public static void log(Throwable t) {
		getDefault().getLogger().logError(t);
	}

}