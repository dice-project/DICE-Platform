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
package org.eclipse.epf.library.ui.preferences;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Models a recently opened method library preference.
 * 
 * @author Kelvin Low
 */
public class RecentlyOpenedLibrary {

	private URI uri;
	
	private String name;
	
	private String path;

	/**
	 * Creates a new instance.
	 */
	public RecentlyOpenedLibrary(String uri) {
		try {
			this.uri = new URI(uri);
			File file = new File(this.uri);
			this.name = file.getName();
			this.path = file.getAbsolutePath();
		} catch (URISyntaxException e) {
		}
	}

	/**
	 * Gets the library name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the library URI.
	 */
	public URI getURI() {
		return uri;
	}
	
	/**
	 * Gets the library path.
	 */
	public String getPath() {
		return path;
	}	

}
