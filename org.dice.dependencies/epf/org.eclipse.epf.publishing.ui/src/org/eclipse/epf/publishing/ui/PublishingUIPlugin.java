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
package org.eclipse.epf.publishing.ui;

import org.eclipse.epf.common.ui.AbstractPlugin;
import org.eclipse.epf.library.layout.BrowsingLayoutSettings;
import org.eclipse.epf.library.ui.LibraryUIPlugin;
import org.eclipse.epf.publishing.ui.preferences.PublishingUIPreferences;
import org.osgi.framework.BundleContext;


/**
 * The main plugin class for the publishing UI plugin.
 * 
 * @author Jinhua Xi
 * @since 1.0
 */
public class PublishingUIPlugin extends AbstractPlugin {

	// The shared instance.
	private static PublishingUIPlugin plugin;

	/**
	 * Creates a new instance.
	 */
	public PublishingUIPlugin() {
		super();
		plugin = this;
	}

	/**
	 * @see org.eclipse.epf.uma.core.plugin.AbstractPlugin#start(BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		
		updateLayoutSettings();
	}

	/**
	 * @see org.eclipse.epf.uma.core.plugin.AbstractPlugin#start(BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
		plugin = null;
	}

	/**
	 * Returns the shared instance.
	 */
	public static PublishingUIPlugin getDefault() {
		return plugin;
	}

	/**
	 * update the publishing/browsing layout settings.
	 * Call this method when ever publishing/browsing related preferences are changed.
	 *
	 */
	public void updateLayoutSettings() 
	{
		// call the library UI to update the settings first
		LibraryUIPlugin.getDefault().updateLayoutSettings();
		
		// get the skin setting from preference
		//boolean publishUnopenActivitydd = LibraryUIPreferences.getPublishUnopenActivitydd();	
		//boolean publishADForActivityExtension = LibraryUIPreferences.getPublishADForActivityExtension();
		boolean extraDescriptorInfo = PublishingUIPreferences.getExtraDescriptorInfo();
		boolean showLinedElementPage = PublishingUIPreferences.getShowLinkedElementForDescriptor();
		//boolean usenewExtends = LibraryPreferences.getUseNewExtendsSemantics();
		
		//BrowsingLayoutSettings.INSTANCE.setPublishADForActivityExtension(publishADForActivityExtension);
		//BrowsingLayoutSettings.INSTANCE.setPublishUnopenActivitydd(publishUnopenActivitydd);
		BrowsingLayoutSettings.INSTANCE.setShowExtraInfoForDescriptors(extraDescriptorInfo);
		BrowsingLayoutSettings.INSTANCE.setShowLinkedPageForDescriptor(showLinedElementPage);
		
		boolean fulfillDescriptorSlotByContent = PublishingUIPreferences.getFulfillDescriptorSlotByContent();
		BrowsingLayoutSettings.INSTANCE.setFulfillDescriptorSlotByContent(fulfillDescriptorSlotByContent);
		
		String forbiddenUrlChars = PublishingUIPreferences.getForbiddenChars();
		BrowsingLayoutSettings.INSTANCE.setForbiddenUrlChars(forbiddenUrlChars);
		
		boolean ignoreDynamicParents = PublishingUIPreferences.getIgnoreDynamicParents();
		BrowsingLayoutSettings.INSTANCE.setIgnoreDynamicParents(ignoreDynamicParents);
		
		boolean excludeUnusedWPDs = PublishingUIPreferences.getExcludeUnusedWPDs();
		BrowsingLayoutSettings.INSTANCE.setExcludeUnusedWPDs(excludeUnusedWPDs);
	}
}
