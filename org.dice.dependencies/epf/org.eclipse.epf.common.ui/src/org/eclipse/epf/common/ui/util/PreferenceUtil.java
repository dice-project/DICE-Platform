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
package org.eclipse.epf.common.ui.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.preference.IPreferenceStore;

import com.ibm.icu.util.StringTokenizer;

/**
 * Utility class for accessing preferences.
 * 
 * @author Kelvin Low
 * @since 1.2
 */
public class PreferenceUtil {

	// The multi-string preference delimiter.
	private static final String PREFERENCE_DELIMITER = ";"; //$NON-NLS-1$

	/**
	 * Gets a list containing string values associated with a named preference.
	 * 
	 * @param prefStore
	 *            a preference store
	 * @param name
	 *            the preference name
	 * @return a list of string values
	 */
	public static List<String> getList(IPreferenceStore prefStore, String name) {
		List<String> values = new ArrayList<String>();
		if (prefStore != null && name != null) {
			String value = prefStore.getString(name);
			StringTokenizer tokenizer = new StringTokenizer(value,
					PREFERENCE_DELIMITER);
			int tokenCount = tokenizer.countTokens();
			for (int i = 0; i < tokenCount; i++) {
				values.add(tokenizer.nextToken());
			}
		}
		return values;
	}

	/**
	 * Gets the string values associated with a named preference.
	 * 
	 * @param prefStore
	 *            a preference store
	 * @param name
	 *            the preference name
	 * @return a list of string preference values
	 */
	public static String[] getStringValues(IPreferenceStore prefStore,
			String name) {
		List<String> list = getList(prefStore, name);
		String[] values = new String[list.size()];
		list.toArray(values);
		return values;
	}

	/**
	 * Saves a list containing string values associated with a named preference.
	 * 
	 * @param prefStore
	 *            a preference store
	 * @param name
	 *            the preference name
	 * @param values
	 *            a list of string values
	 */
	public static void setList(IPreferenceStore prefStore, String name,
			List values) {
		if (prefStore != null && name != null && values != null) {
			StringBuffer buffer = new StringBuffer();
			for (Iterator it = values.iterator(); it.hasNext();) {
				String value = (String) it.next();
				buffer.append(value);
				buffer.append(PREFERENCE_DELIMITER);
			}
			prefStore.setValue(name, buffer.toString());
		}
	}

	/**
	 * Adds a string value to a list containing string values associated with a
	 * named preference.
	 * 
	 * @param prefStore
	 *            a preference store
	 * @param name
	 *            the preference name
	 * @param value
	 *            a string value
	 * @param defaultValue
	 *            a default string value that will be added to the end of the
	 *            list
	 */
	public static void addToList(IPreferenceStore prefStore, String name,
			String value, String defaultValue) {
		if (prefStore != null && name != null && value != null) {
			List<String> list = getList(prefStore, name);
			list.remove(value);
			list.add(0, value);

			//adjust the list according to the history size
			int preferenceHistorySize = CommonPreferences.getPreferenceHistorySize();			
			if (list.size() > preferenceHistorySize) {
				list = list.subList(0, preferenceHistorySize - 1);
			}			
			if (defaultValue != null && defaultValue.length() > 0) {
				if (!list.contains(defaultValue)) {
					if (list.size() == preferenceHistorySize) {
						list.add(list.size() - 1, defaultValue);
					} else {
						list.add(defaultValue);
					}
				}
			}
			
			setList(prefStore, name, list);
		}
	}

	/**
	 * Adds a string value to a list containing string values associated with a
	 * named preference.
	 * 
	 * @param prefStore
	 *            a preference store
	 * @param name
	 *            the preference name
	 * @param value
	 *            a string value
	 */
	public static void addToList(IPreferenceStore prefStore, String name,
			String value) {
		addToList(prefStore, name, value, null);
	}

	/**
	 * Saves an array of string values associated with a named preference.
	 * 
	 * @param prefStore
	 *            a preference store
	 * @param name
	 *            the preference name
	 * @param values
	 *            an array of string values
	 */
	public static void setStringValues(IPreferenceStore prefStore, String name,
			String[] values) {
		if (prefStore != null && name != null && values != null) {
			StringBuffer buffer = new StringBuffer();
			for (int i = 0; i < values.length; i++) {
				buffer.append(values[i]);
				buffer.append(PREFERENCE_DELIMITER);
			}
			prefStore.setValue(name, buffer.toString());
		}
	}

}
