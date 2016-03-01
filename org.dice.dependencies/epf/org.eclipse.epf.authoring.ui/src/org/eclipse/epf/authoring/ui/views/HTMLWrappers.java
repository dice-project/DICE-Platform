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
package org.eclipse.epf.authoring.ui.views;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * The resource bundle for the HTML wrappers.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class HTMLWrappers {

	private final static ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(HTMLWrappers.class.getName());

	/**
	 * Default private constructor to prevent this class form being
	 * instantiated.
	 */
	private HTMLWrappers() {
	}

	/**
	 * Returns the localized string given the resource key.
	 * 
	 * @param key
	 *            The resource key.
	 * @return The localized string.
	 */
	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return null;
		}
	}

	/**
	 * Returns the localized formatted message for the given resource key.
	 * 
	 * @param key
	 *            The resource key.
	 * @param args
	 *            The message arguments.
	 * @return The localized formatted message.
	 */
	public static String format(String key, Object[] args) {
		return MessageFormat.format(getString(key), args);
	}

}