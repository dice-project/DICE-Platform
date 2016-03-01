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
package org.eclipse.epf.library.prefs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.eclipse.epf.common.preferences.IPreferenceStoreWrapper;
import org.eclipse.epf.library.LibraryPlugin;
import org.eclipse.epf.library.edit.process.IBSItemProvider;
import org.eclipse.epf.publish.layout.LayoutPlugin;

import com.ibm.icu.util.StringTokenizer;

/**
 * utility class to handle the process element layout preferences
 * 
 * @author Jinhua Xi
 * @since 1.0
 *
 */
public class PreferenceUtil {
		
	private static Properties xslParams;
	private static Map colIdLabelMap = new HashMap();
	
	// quick solution, the team column for the WBS view.
	// 162783 - Publish team information in WBS for TDs
	private static BSColumn teamCol = null;

	private static final String ID_STEPS = "steps"; //$NON-NLS-1$
	static {
		try {
			Properties params = LayoutPlugin.getDefault().getProperties(
					"/layout/xsl/resources.properties"); //$NON-NLS-1$
			
			setXslProperties(params);
			
		} catch (IOException e) {
		}
	}
	
	public static void setXslProperties(Properties newXslParams) {
		xslParams = newXslParams;	
		
		colIdLabelMap.put(IBSItemProvider.COL_ID, getLabel("indexText")); //$NON-NLS-1$
		colIdLabelMap.put(IBSItemProvider.COL_NAME, getLabel("nameText")); //$NON-NLS-1$
		colIdLabelMap.put(IBSItemProvider.COL_PRESENTATION_NAME, getLabel("breakdownElementText")); //$NON-NLS-1$
		colIdLabelMap.put(ID_STEPS, getLabel("stepsText"));		 //$NON-NLS-1$
		colIdLabelMap.put(IBSItemProvider.COL_PREFIX, getLabel("prefixText")); //$NON-NLS-1$
		colIdLabelMap.put(IBSItemProvider.COL_TYPE, getLabel("typeText")); //$NON-NLS-1$
		colIdLabelMap.put(IBSItemProvider.COL_MODEL_INFO, getLabel("modelInfoText")); //$NON-NLS-1$
		colIdLabelMap.put(IBSItemProvider.COL_PREDECESSORS, getLabel("predecessorsText")); //$NON-NLS-1$
		colIdLabelMap.put(IBSItemProvider.COL_IS_REPEATABLE, getLabel("repeatableText")); //$NON-NLS-1$
		colIdLabelMap.put(IBSItemProvider.COL_IS_ONGOING, getLabel("ongoingText")); //$NON-NLS-1$
		colIdLabelMap.put(IBSItemProvider.COL_IS_EVENT_DRIVEN, getLabel("eventDrivenText")); //$NON-NLS-1$
		colIdLabelMap.put(IBSItemProvider.COL_IS_OPTIONAL, getLabel("optionalText")); //$NON-NLS-1$
		colIdLabelMap.put(IBSItemProvider.COL_IS_PLANNED, getLabel("plannedText")); //$NON-NLS-1$
		colIdLabelMap.put(IBSItemProvider.COL_HAS_MULTIPLE_OCCURRENCES, getLabel("multipleOccurrencesText")); //$NON-NLS-1$

		colIdLabelMap.put(IBSItemProvider.COL_TEAMS, getLabel("teamText")); //$NON-NLS-1$

		colIdLabelMap.put(IBSItemProvider.COL_DELIVERABLE, getLabel("deliverableText")); //$NON-NLS-1$
		colIdLabelMap.put(IBSItemProvider.COL_ENTRY_STATE, getLabel("entryStateText")); //$NON-NLS-1$
		colIdLabelMap.put(IBSItemProvider.COL_EXIT_STATE, getLabel("exitStateText")); //$NON-NLS-1$

	}
	
	public static String getLabel(String key) {
		String label = xslParams.getProperty(key);
		if ( label == null ) {
			System.out.println("Can't find property entry for " + key); //$NON-NLS-1$
			label = key;
		}
		return label;
	}
	
	/**
	 * get a list of columns for WBS layout
	 * @return List of BSColumn objects
	 */
	public static List getWBSColumns() {
		List cols = getPreferenceItems(PreferenceConstants.PREF_WBS_COLUMNS);
		
		// if the TBS columns has team, also add to the WBS
		// 162783 - Publish team information in WBS for TDs
		teamCol = null;
		getTBSColumns();
		if ( teamCol != null ) {
			cols.add(cols.size(), teamCol);
		}
		return cols;
	}
	
	/**
	 * get a list of columns for TBS layout
	 * @return List of BSColumn objects
	 */
	public static List getTBSColumns() {
		return getPreferenceItems(PreferenceConstants.PREF_TBS_COLUMNS);
	}
	
	/**
	 * get a list of columns for WPBS layout
	 * @return List of BSColumn objects
	 */
	public static List getWPBSColumns() {
		return getPreferenceItems(PreferenceConstants.PREF_WPBS_COLUMNS);
	}
	
	/**
	 * validate the columns
	 */
	public static void validatePreferences() {
		// call the method to make sure the default is loaded
		getWBSColumns();
		getTBSColumns();
		getWPBSColumns();
	}
	
	private static List getPreferenceItems(String key) {
		
		IPreferenceStoreWrapper store = LibraryPlugin.getDefault().getPreferenceStore();		
		String str = store.getString(key);
		if (str == null) {
			str = store.getDefaultString(key);
		}
		
		if ( str == null || str.length() == 0 ) {
			str = getDefault(key);
			store.setValue(key, str);
		}
		
		List items = new ArrayList();
		StringTokenizer tokens = new StringTokenizer(str, ","); //$NON-NLS-1$
		while (tokens.hasMoreTokens()) {
			String id = tokens.nextToken().trim();		
			if (id != null && id.length() != 0 ) {
				
				BSColumn col = new BSColumn(id, getColText(id));
				items.add(col);
				// add steps column for WBS
				if ( key.equals(PreferenceConstants.PREF_WBS_COLUMNS) 
						&& id.equals(IBSItemProvider.COL_PRESENTATION_NAME) ) {
					col = new BSColumn(ID_STEPS, getColText(ID_STEPS));
					items.add(col);
				}
				
				// save the team column for WBS
				// 162783 - Publish team information in WBS for TDs
				if ( IBSItemProvider.COL_TEAMS.equals(id) ) {
					teamCol = col;
				}
			}
		}
		
		return items;
	}
	
	private static String getColText(String key) {
		String text = (String)colIdLabelMap.get(key);
		if ( text == null ) {
			System.out.println("No label defined for column " + key);  //$NON-NLS-1$
			text = key;
		}
		
		return text;
	}
	
	private static  String getDefault(String key) {
		if ( PreferenceConstants.PREF_WBS_COLUMNS.equals(key) ) {
			return toString(PreferenceConstants.DEFAULT_WBS_COLUMNS);
		}
		
		if ( PreferenceConstants.PREF_TBS_COLUMNS.equals(key) ) {
			return toString(PreferenceConstants.DEFAULT_TBS_COLUMNS);
		}
		
		if ( PreferenceConstants.PREF_WPBS_COLUMNS.equals(key) ) {
			return toString(PreferenceConstants.DEFAULT_WPBS_COLUMNS);
		}
		
		return null;
	}
	
	private static String toString(String[] ids) {
		StringBuffer strBuf = new StringBuffer();
		int max = ids.length - 1;
		for (int i = 0; i < max; i++) {
			strBuf.append(ids[i])
					.append(',');
		}
		strBuf.append(ids[ids.length-1]);
		return strBuf.toString();
	}

	public static void saveSelectedConfigIntoPersistence(String configName) {
		IPreferenceStoreWrapper store = LibraryPlugin.getDefault()
				.getPreferenceStore();
		store
				.setValue(
						LibraryPreferenceConstants.PREF_SELECTED_CONFIG_IN_LAST_SESSION,
						configName);
		LibraryPlugin.getDefault().savePluginPreferences();
	}

	public static String getSavedLastConfig() {
		IPreferenceStoreWrapper store = LibraryPlugin.getDefault()
				.getPreferenceStore();
		return store
				.getString(LibraryPreferenceConstants.PREF_SELECTED_CONFIG_IN_LAST_SESSION);
	}
	
}
