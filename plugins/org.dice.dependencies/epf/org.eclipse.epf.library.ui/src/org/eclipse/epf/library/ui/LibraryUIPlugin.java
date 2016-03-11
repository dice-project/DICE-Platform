//------------------------------------------------------------------------------
// Copyright (c) 2005, 2007 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.library.ui;

import org.eclipse.epf.common.ui.AbstractPlugin;
import org.eclipse.epf.library.configuration.SupportingElementData;
import org.eclipse.epf.library.layout.BrowsingLayoutSettings;
import org.eclipse.epf.library.preferences.LibraryPreferences;
import org.eclipse.epf.library.ui.preferences.LibraryUIPreferences;
import org.osgi.framework.BundleContext;

/**
 * The Library UI plug-in class.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class LibraryUIPlugin extends AbstractPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = LibraryUIPlugin.class.getName();

	// The shared plug-in instance.
	private static LibraryUIPlugin plugin;

	/**
	 * Creates a new instance.
	 */
	public LibraryUIPlugin() {
		super();
		plugin = this;
	}

	/**
	 * @see org.eclipse.epf.common.ui.AbstractPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		SupportingElementData.setDescriptorExclusiveOption(! LibraryUIPreferences.getIncludeDescriptors());
	}

	/**
	 * @see org.eclipse.epf.common.ui.AbstractPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
		plugin = null;
	}

	/**
	 * Gets the shared plug-in instance.
	 */
	public static LibraryUIPlugin getDefault() {
		return plugin;
	}

	/**
	 * update the publishing/browsing layout settings.
	 * Call this method when ever publishing/browsing related preferences are changed.
	 *
	 */
	public void updateLayoutSettings() 
	{
		// get the skin setting from preference
		boolean publishUnopenActivitydd = LibraryUIPreferences.getPublishUnopenActivitydd();	
		boolean publishADForActivityExtension = LibraryUIPreferences.getPublishADForActivityExtension();
		boolean useNewExtends = LibraryPreferences.getUseNewExtendsSemantics();
		
		BrowsingLayoutSettings.INSTANCE.setPublishADForActivityExtension(publishADForActivityExtension);
		BrowsingLayoutSettings.INSTANCE.setPublishUnopenActivitydd(publishUnopenActivitydd);
		BrowsingLayoutSettings.INSTANCE.setUseNewExtendSemantics(useNewExtends);
		
	}
}