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
package org.eclipse.epf.common.utils;

import java.io.File;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Helper utiltiies for handling internationalization.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class I18nUtil {

	/**
	 * Private constructor to prevent this class from being instantiated. All
	 * methods in this class should be static.
	 */
	private I18nUtil() {
	}

	/**
	 * Returns the localized string associated with a resource key.
	 * 
	 * @param resourceBundle
	 *            A resource bundle.
	 * @param key
	 *            A resource key.
	 * @return A localized string.
	 */
	public static String getString(ResourceBundle resourceBundle, String key) {
		if (resourceBundle != null) {
			try {
				return resourceBundle.getString(key);
			} catch (MissingResourceException e) {
			}
		}
		return '[' + key + ']';
	}

	/**
	 * Returns the localized string associated with a resource key and formatted
	 * with a given string.
	 * 
	 * @param resourceBundle
	 *            A resource bundle.
	 * @param key
	 *            A resource key.
	 * @param data
	 *            An object.
	 * @return A formatted localized string.
	 */
	public static String formatString(ResourceBundle resourceBundle,
			String key, Object data) {
		if (resourceBundle != null) {
			try {
				String localizedStr = resourceBundle.getString(key);
				return MessageFormat
						.format(localizedStr, new Object[] { data });
			} catch (MissingResourceException e) {
			}
		}
		return '[' + key + ']';
	}

	/**
	 * Returns the localized string associated with a resource key and formatted
	 * with two given string.
	 * 
	 * @param resourceBundle
	 *            A resource bundle.
	 * @param key
	 *            A resource key.
	 * @param data1
	 *            An object.
	 * @param data2
	 *            An object.
	 * @return A formatted localized string.
	 */
	public static String formatString(ResourceBundle resourceBundle,
			String key, Object data1, Object data2) {
		if (resourceBundle != null) {
			try {
				String localizedStr = resourceBundle.getString(key);
				return MessageFormat.format(localizedStr, new Object[] { data1,
						data2 });
			} catch (MissingResourceException e) {
			}
		}
		return '[' + key + ']';
	}

	/**
	 * Returns the localized string associated with a resource key and formatted
	 * with a given string.
	 * 
	 * @param resourceBundle
	 *            A resource bundle.
	 * @param key
	 *            A resource key.
	 * @param data
	 *            An array of objects.
	 * @return A formatted localized string.
	 */
	public static String formatString(ResourceBundle resourceBundle,
			String key, Object[] data) {
		if (resourceBundle != null) {
			try {
				String localizedStr = resourceBundle.getString(key);
				return MessageFormat.format(localizedStr, data);
			} catch (MissingResourceException e) {
			}
		}
		return '[' + key + ']';
	}

	/**
	 * Returns the localized integer value associated with a resource key.
	 * 
	 * @param resourceBundle
	 *            A resource bundle.
	 * @param key
	 *            A resource key.
	 * @param defaultValue
	 *            The default value to return if the resource key cannot be
	 *            located.
	 * @return A localized interger value.
	 */
	public static int getInt(String intString,
			int defaultValue) {
		try {
			if (intString != null) {
				return Integer.parseInt(intString);
			}
		} catch (Exception e) {
		}
		return defaultValue;
	}

	/**
	 * Returns the localized version of a US English resource file.
	 * 
	 * @param path
	 *            The absolute path to a resource file.
	 * @param locale
	 *            A locale.
	 * @return The localized resource file. If none exists, return null.
	 */
	public static String getLocalizedFile(String path, Locale locale) {
		if (locale.equals(Locale.US) || path == null) {
			return null;
		}

		// Build the locale string array. This is used to construct the
		// localized file names.
		String language = locale.getLanguage();
		String country = locale.getCountry();
		String variant = locale.getVariant();

		String[] localeStrs = new String[3];
		if (variant != null && variant.length() > 0) {
			localeStrs[0] = locale.toString();
		}
		if (country != null && country.length() > 0) {
			localeStrs[1] = language + "_" + country; //$NON-NLS-1$
		}
		if (language != null) {
			localeStrs[2] = language;
		}

		String dir = path.replace('\\', '/');
		String fileName;

		int pathIndex = dir.lastIndexOf("/"); //$NON-NLS-1$
		if (pathIndex > 0) {
			dir = path.substring(0, pathIndex + 1);
			fileName = path.substring(pathIndex + 1);
		} else {
			fileName = dir;
			dir = ""; //$NON-NLS-1$
		}

		String fileExt = ""; //$NON-NLS-1$		
		int extIndex = fileName.lastIndexOf("."); //$NON-NLS-1$
		if (extIndex > 0) {
			fileExt = fileName.substring(extIndex);
			fileName = fileName.substring(0, extIndex);
		}

		fileName = dir + fileName + "_"; //$NON-NLS-1$

		// Look for the localized file.
		for (int i = 0; i < 3; i++) {
			if (localeStrs[i] != null) {
				// Gets the localized path name.
				String fname = fileName + localeStrs[i] + fileExt;
				File file = new File(fname);
				if (file.exists()) {
					return fname;
				}
			}
		}

		return null;
	}

}