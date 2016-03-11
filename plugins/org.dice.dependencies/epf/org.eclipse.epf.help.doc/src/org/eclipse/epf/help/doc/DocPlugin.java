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
package org.eclipse.epf.help.doc;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The main plugin class to be used in the desktop.
 */
public class DocPlugin extends AbstractUIPlugin {

	// The shared instance.
	private static DocPlugin plugin;

	/**
	 * The constructor.
	 */
	public DocPlugin() {
		super();
		plugin = this;
	}

	/**
	 * This method is called upon plug-in activation
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
	}

	/**
	 * This method is called when the plug-in is stopped
	 */
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
		plugin = null;
	}

	/**
	 * Returns the shared instance.
	 */
	public static DocPlugin getDefault() {
		return plugin;
	}

	/**
	 * Returns the string from the plugin's resource bundle,
	 * or 'key' if not found.
	 */
//	public static String getResourceString(String key) {
//		ResourceBundle bundle = DocPlugin.getDefault().getResourceBundle();
//		try {
//			return (bundle != null) ? bundle.getString(key) : key;
//		} catch (MissingResourceException e) {
//			return key;
//		}
//	}

	/**
	 * Returns the plugin's resource bundle,
	 */
//	public ResourceBundle getResourceBundle() {
//		try {
//			if (resourceBundle == null)
//				resourceBundle = ResourceBundle.getBundle("org.eclipse.epf.help.doc.DocPluginResources");
//		} catch (MissingResourceException x) {
//			resourceBundle = null;
//		}
//		return resourceBundle;
//	}

	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path.
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
//	public static ImageDescriptor getImageDescriptor(String path) {
//		return AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.epf.help.doc", path);
//	}
	
}
