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
package org.eclipse.epf.authoring.ui.preferences;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * Manages the Authoring UI preferences.
 * 
 * @author Kelvin Low
 * @since 1.2
 */
public class AuthoringUIPreferences {

	public static final String ENABLE_LIBRARY_VALIDATION = "enabledLibraryValidation"; //$NON-NLS-1$
	
	public static final String ENABLE_UI_FIELDS = "enableUIFields"; //$NON-NLS-1$
	
	public static final String ENABLE_AUTO_NAME_GEN = "autoNameGen"; //$NON-NLS-1$
	
	public static final String RTE_URL_DECODING_OPTION = "rteUrlDecodingOption"; //$NON-NLS-1$
	
	public static final String RTE_URL_DECODING_HEX_NUMBERS = "rteUrlDecodingHexNumbers"; //$NON-NLS-1$

	public static final String ADD_TASKS_PER_ROW = "ACTIVITY_DETAIL_DIAGRAM_TASKS_PER_ROW"; //$NON-NLS-1$
	
	private static final int DEFAULT_ADD_TASKS_PER_ROW = 10; 
	
	private static final boolean DEFAULT_ENABLE_LIBRARY_VALIDATION = false;
	
	private static final boolean DEFAULT_ENABLE_UI_FIELDS = false;
	
	private static final boolean DEFAULT_ENABLE_AUTO_NAME_GEN = true;

	private static final int DEFAULT_RTE_URL_DECODING_OPTION = 2;
	
	private static final String DEFAULT_RTE_URL_DECODING_HEX_NUMBERS = "%" + AuthoringUIResources.hex_20;	//$NON-NLS-1$ 
	
	// The plug-in specific preference store.
	private static IPreferenceStore prefStore = AuthoringUIPlugin.getDefault()
			.getPreferenceStore();
	
	private static boolean toClearStrUtilOptionsCache = false;
	
	static {
		// Initialize the default preference values.
		prefStore.setDefault(ENABLE_LIBRARY_VALIDATION,
				DEFAULT_ENABLE_LIBRARY_VALIDATION);
		
		prefStore.setDefault(ENABLE_UI_FIELDS,
				DEFAULT_ENABLE_UI_FIELDS);
		
		prefStore.setDefault(ENABLE_AUTO_NAME_GEN,
				DEFAULT_ENABLE_AUTO_NAME_GEN);
		
		prefStore.setDefault(ADD_TASKS_PER_ROW, DEFAULT_ADD_TASKS_PER_ROW);
		
		prefStore.setDefault(RTE_URL_DECODING_OPTION, DEFAULT_RTE_URL_DECODING_OPTION);
		
		prefStore.setDefault(RTE_URL_DECODING_HEX_NUMBERS, DEFAULT_RTE_URL_DECODING_HEX_NUMBERS);
				
		StrUtil.StrUtilOptions strUtilOptions = new StrUtil.StrUtilOptions() {
			private Map<String, String> cachedMap;
			public int getRteUrlDecodingOption() {
				return AuthoringUIPreferences.getRteUrlDecodingOption();
			}
			public Map<String, String> getRteUrlDecodingHexMap() {
				if (cachedMap == null || toClearStrUtilOptionsCache) {
					toClearStrUtilOptionsCache = false;
					cachedMap =  AuthoringUIPreferences.getRteUrlDecodingHexMap();
				}
				return cachedMap;
			}
		};
		
		StrUtil.setOptions(strUtilOptions);
	}

	/**
	 * Gets the default enable method library validation preference.
	 */
	public static boolean getDefaultEnableLibraryValidation() {
		return DEFAULT_ENABLE_LIBRARY_VALIDATION;
	}

	/**
	 * Gets the method library validation preference.
	 */
	public static boolean getEnableLibraryValidation() {
		return prefStore.getBoolean(ENABLE_LIBRARY_VALIDATION);
	}

	/**
	 * Sets the method library validation preference.
	 */
	public static void setEnableLibraryValidation(boolean value) {
		prefStore.setValue(ENABLE_LIBRARY_VALIDATION, value);
	}
	
	/**
	 * Gets the default enable UI fields preference (long presentation name, external ID)
	 */
	public static boolean getDefaultEnableUIFields() {
		return DEFAULT_ENABLE_UI_FIELDS;
	}
	
	/**
	 * Gets the enable UI fields preference (long presentation name, external ID)
	 */
	public static boolean getEnableUIFields() {
		return prefStore.getBoolean(ENABLE_UI_FIELDS);
	}

	/**
	 * Sets the enable UI fields preference (long presentation name, external ID)
	 */
	public static void setEnableUIFields(boolean value) {
		prefStore.setValue(ENABLE_UI_FIELDS, value);
	}
	
	/**
	 * Gets the default enable auto name gen fields preference 
	 */
	public static boolean getDefaultEnableAutoNameGen() {
		return DEFAULT_ENABLE_AUTO_NAME_GEN;
	}
	
	/**
	 * Gets the enable enable auto name gen preference 
	 */
	public static boolean getEnableAutoNameGen() {
		return prefStore.getBoolean(ENABLE_AUTO_NAME_GEN);
	}

	/**
	 * Sets the enable enable auto name gen preference 
	 */
	public static void setEnableAutoNameGen(boolean value) {
		prefStore.setValue(ENABLE_AUTO_NAME_GEN, value);
	}

	/**
	 * Gets the default rte url decoding option
	 */
	public static int getDefaultRteUrlDecodingOption() {
		return DEFAULT_RTE_URL_DECODING_OPTION;
	}
	
	/**
	 * Gets the rte url decoding option
	 */
	public static int getRteUrlDecodingOption() {
		return prefStore.getInt(RTE_URL_DECODING_OPTION);
	}

	/**
	 * Sets the rte url decoding option
	 */
	public static void setgetRteUrlDecodingOption(int value) {
		prefStore.setValue(RTE_URL_DECODING_OPTION, value);
	}
	
	
	/**
	 * Gets the default rte url decoding hex numbers
	 */
	public static String getDefaultRteUrlDecodingHexNumbers() {
		return DEFAULT_RTE_URL_DECODING_HEX_NUMBERS;
	}
	
	/**
	 * Gets the rte url decoding hex numbers
	 */
	public static String getRteUrlDecodingHexNumbers() {
		return prefStore.getString(RTE_URL_DECODING_HEX_NUMBERS);
	}

	/**
	 * Sets the rte url decoding hex numbers
	 */
	public static void setRteUrlDecodingHexNumbers(String value) {
		toClearStrUtilOptionsCache = true;
		prefStore.setValue(RTE_URL_DECODING_HEX_NUMBERS, value);
	}
	
	/**
	 * Gets the rte url decoding hex number set
	 */
	public static Map<String, String> getRteUrlDecodingHexMap() {
		Map<String, String> map = new HashMap<String, String>();
		String str = getRteUrlDecodingHexNumbers();		
		List<String> strValues = TngUtil.convertStringsToList(str, "\n");//$NON-NLS-1$
		for (String strValue : strValues) {
			String key = StrUtil.getHexStr(strValue);
			if (key != null) {
				map.put(key, strValue);
			}
		}		
		return map;
	}
	
	/**
	 * Gets the ADD_TASKS_PER_ROW preference.
	 */
	public static int getADD_TasksPerRow()  {
		int value = prefStore.getInt(ADD_TASKS_PER_ROW);
		return value > 0 ? value : DEFAULT_ADD_TASKS_PER_ROW;
	}

	/**
	 * Sets the ADD_TASKS_PER_ROW preference.
	 */
	public static void setADDTasksPerRow(int value) {
		prefStore.setValue(ADD_TASKS_PER_ROW, value);
	}
	
	/**
	 * Gets the default ADD_TASKS_PER_ROW preference.
	 */
	public static int getDefaultADDTasksPerRow() {
		return DEFAULT_ADD_TASKS_PER_ROW;
	}

}
