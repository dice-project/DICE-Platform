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
package org.eclipse.epf.library.edit;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.epf.common.AbstractActivator;
import org.eclipse.epf.library.edit.util.UmaUtilProvider;
import org.osgi.framework.BundleContext;

/**
 * The Library Edit plug-in class.
 * 
 * @author Kelvin Low
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class LibraryEditPlugin extends AbstractActivator {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.epf.library.edit"; //$NON-NLS-1$
	
	// The shared plug-in instance.
	private static LibraryEditPlugin plugin;

	public static LibraryEditPlugin INSTANCE;

	// The shared image hash map.
	protected Map<String, Object> images = new HashMap<String, Object>();
	
	/**
	 * Creates a new instance.
	 */
	public LibraryEditPlugin() {
		super();
		plugin = this;
		INSTANCE = this;
	}

	/**
	 * @see org.eclipse.epf.uma.core.plugin.AbstractPlugin#start(BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		
		UmaUtilProvider.init();
	}

	/**
	 * @see org.eclipse.epf.uma.core.plugin.AbstractPlugin#start(BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
		plugin = null;
	}

	/**
	 * Returns the shared plug-in instance.
	 */
	public static LibraryEditPlugin getDefault() {
		return plugin;
	}

	/**
	 * Returns the shared plug-in instance.
	 */
	public static LibraryEditPlugin getPlugin() {
		return plugin;
	}

	/**
	 * Returns the symbolic name of this plug-in.
	 * 
	 * @return The symbolic name of this plug-in.
	 */
	public String getSymbolicName() {
		return getId();
	}

	public Object getImage(String key) {
	    Object result = images.get(key);
		if (result == null) {
			try {
				result = doGetImage(key);
			} catch (Exception exception) {
				throw new WrappedException(exception);
			}
			if(result != null) {
				images.put(key, result);
			}
		}

		return result;
	}
	
	private Object doGetImage(String key) throws IOException {
	    URL url = new URL(iconURL + key + ".gif"); //$NON-NLS-1$
	    InputStream inputStream = url.openStream(); 
	    inputStream.close();
	    return url;
	}

	/**
	 * Returns the shared image given the URI.
	 * 
	 * @param imageURI
	 *            The image's URI.
	 * @return The image.
	 */
	public Object getSharedImage(URI imageURI) {
		if(imageURI == null) {
			return null;
		}
		try {
			return imageURI.toURL();
		} catch (MalformedURLException e) {
			return null;
		}
	}

}