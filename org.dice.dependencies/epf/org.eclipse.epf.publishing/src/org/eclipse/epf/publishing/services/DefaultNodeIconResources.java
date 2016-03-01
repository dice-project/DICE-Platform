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

package org.eclipse.epf.publishing.services;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author Jinhua Xi
 * @since 1.0
 */
public class DefaultNodeIconResources {

	private static ResourceBundle resourceBundle;

	private static String DEFAULT_ICON_NAME = ""; //$NON-NLS-1$
	static {
		try {
			String resource = DefaultNodeIconResources.class.getName();
			resourceBundle = ResourceBundle.getBundle(resource);
			DEFAULT_ICON_NAME = getString("folder"); //$NON-NLS-1$

		} catch (MissingResourceException x) {
			x.printStackTrace();
			resourceBundle = null;
		}
	}

	private static String getString(String key) {
		try {
			return (resourceBundle != null) ? resourceBundle.getString(key)
					: null;
		} catch (MissingResourceException e) {
			return null;
		}
	}

	/**
	 * get the icon name for the specified key
	 * 
	 * @param key String
	 * @return String the icon name.
	 */
	public static String getIconName(String key) {
		String iconName = getString(key);
		if (iconName == null) {
			iconName = DEFAULT_ICON_NAME;
		}

		return iconName;
	}

}
