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
package org.eclipse.epf.library.ui.xmi;

import org.eclipse.osgi.util.NLS;

public class XMILibraryUIResources extends NLS {

	private static String BUNDLE_NAME = XMILibraryUIResources.class
			.getPackage().getName()
			+ ".Resources"; //$NON-NLS-1$

	public static String nameLabel_text;
	
	public static String locationLabel_text;

	public static String browseButton_text;
	
	public static String defaultPathCheckboxLabel_text;

	static {
		NLS.initializeMessages(BUNDLE_NAME, XMILibraryUIResources.class);
	}

	/**
	 * Returns the localized string associated with a resource key and formatted
	 * with a given object.
	 * 
	 * @param key
	 *            A resource key.
	 * @param data
	 *            An object.
	 * @return A formatted localized string.
	 */
	public static String bind(String key, Object data) {
		return NLS.bind(key, new Object[] { data });
	}

}