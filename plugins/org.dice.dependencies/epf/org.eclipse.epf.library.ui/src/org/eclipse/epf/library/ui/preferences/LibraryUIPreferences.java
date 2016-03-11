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
package org.eclipse.epf.library.ui.preferences;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.common.ui.util.PreferenceUtil;
import org.eclipse.epf.common.utils.FileUtil;
import org.eclipse.epf.library.configuration.SupportingElementData;
import org.eclipse.epf.library.edit.util.LibraryEditUtil;
import org.eclipse.epf.library.edit.validation.IValidationManager;
import org.eclipse.epf.library.ui.LibraryUIPlugin;
import org.eclipse.epf.persistence.MultiFileSaveUtil;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.ecore.util.DefaultValueManager;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * Manages the Library UI preferences.
 * 
 * @author Kelvin Low
 * @author Phong Nguyen Le
 * 
 * @since 1.0
 */
public class LibraryUIPreferences {

	private static final String DEFAULT_LIBRARY_PATH = "defaultLibraryPath"; //$NON-NLS-1$

	private static final String SAVED_LIBRARY_PATH = "savedLibraryPath"; //$NON-NLS-1$

	private static final String PUBLISH_UNOPEN_ACTIVITY_DD = "publishUnopenActivityDetailDiagram"; //$NON-NLS-1$

	private static final String PREF_SWITCH_CONFIG = "switchConfigurationOnProcessActivate"; //$NON-NLS-1$

	private static final String PUBLISH_AD_FOR_ACTIVITY_EXTENSION = "publishActivityDiagramforActivityExtension"; //$NON-NLS-1$

	private static final String APPLICATION_SHORT_NAME = "appname"; //$NON-NLS-1$

	private static final String NEW_LIBRARY_PATHS = "newLibraryPaths"; //$NON-NLS-1$

	private static final String OPEN_LIBRARY_PATHS = "openLibraryPaths"; //$NON-NLS-1$		

	private static final String OPEN_LIBRARY_URIS = "openLibraryURIs"; //$NON-NLS-1$

	private static final String WORK_BREAKDOWNELEMENT_OPTIONAL = "work_breakdownelement_optional"; //$NON-NLS-1$

	private static final String WORK_BREAKDOWNELEMENT_MULTIPLE_OCCURRANCES = "work_breakdownelement_multiple_occurrances"; //$NON-NLS-1$

	private static final String WORK_BREAKDOWNELEMENT_PLANNED = "work_breakdownelement_planned"; //$NON-NLS-1$

	private static final String WORK_BREAKDOWNELEMENT_EVENT_DRIVEN = "work_breakdownelement_event_driven"; //$NON-NLS-1$

	private static final String WORK_BREAKDOWNELEMENT_ONGOING = "work_breakdownelement_multiple_ongoing"; //$NON-NLS-1$

	private static final String WORK_BREAKDOWNELEMENT_REPEATABLE = "work_breakdownelement_repeatable"; //$NON-NLS-1$

	private static final String DESCRIPTOR_OPTIONAL = "descriptor_optional"; //$NON-NLS-1$

	private static final String DESCRIPTOR_MULTIPLE_OCCURRANCES = "descriptor_multiple_occurrances"; //$NON-NLS-1$

	private static final String DESCRIPTOR_PLANNED = "descriptor_planned"; //$NON-NLS-1$

	private static final String DESCRIPTOR_EVENT_DRIVEN = "descriptor_event_driven"; //$NON-NLS-1$

	private static final String DESCRIPTOR_ONGOING = "descriptor_multiple_ongoing"; //$NON-NLS-1$

	private static final String INCLUDE_DESCRIPTORS = "include descriptors"; //$NON-NLS-1$
	
	private static final String NAME_VALIDATION = "name validation"; //$NON-NLS-1$
	
	private static final String CIRCULAR_VALIDATION = "circular validation"; //$NON-NLS-1$

	private static final String UNDECLARED_VALIDATION = "undeclared validation"; //$NON-NLS-1$

	private static final String DESCRIPTOR_REPEATABLE = "descriptor_repeatable"; //$NON-NLS-1$

	private static final String DEFAULT_LIBRARY_FOLDER_NAME = "Method Libraries"; //$NON-NLS-1$

	private static String defaultLibraryPath = null;

	// The plug-in specific preference store.
	private static IPreferenceStore prefStore = LibraryUIPlugin.getDefault()
			.getPreferenceStore();

	static {
		// Initialize the default preference values.
		prefStore.setDefault(DEFAULT_LIBRARY_PATH,
				getInitialDefaultLibraryPath());

		prefStore.setDefault(SAVED_LIBRARY_PATH, ""); //$NON-NLS-1$	
		prefStore
				.setDefault(PREF_SWITCH_CONFIG, MessageDialogWithToggle.PROMPT);
		prefStore.setDefault(NEW_LIBRARY_PATHS, ""); //$NON-NLS-1$	
		prefStore.setDefault(OPEN_LIBRARY_PATHS, ""); //$NON-NLS-1$
		prefStore.setDefault(OPEN_LIBRARY_URIS, ""); //$NON-NLS-1$	

		prefStore.setDefault(WORK_BREAKDOWNELEMENT_OPTIONAL, false);
		prefStore.setDefault(WORK_BREAKDOWNELEMENT_MULTIPLE_OCCURRANCES, false);
		prefStore.setDefault(WORK_BREAKDOWNELEMENT_PLANNED, true);
		prefStore.setDefault(WORK_BREAKDOWNELEMENT_EVENT_DRIVEN, false);
		prefStore.setDefault(WORK_BREAKDOWNELEMENT_ONGOING, false);
		prefStore.setDefault(WORK_BREAKDOWNELEMENT_REPEATABLE, false);

		prefStore.setDefault(DESCRIPTOR_OPTIONAL, false);
		prefStore.setDefault(DESCRIPTOR_MULTIPLE_OCCURRANCES, false);
		prefStore.setDefault(DESCRIPTOR_PLANNED, false);
		prefStore.setDefault(DESCRIPTOR_EVENT_DRIVEN, false);
		prefStore.setDefault(DESCRIPTOR_ONGOING, false);
		prefStore.setDefault(INCLUDE_DESCRIPTORS, !SupportingElementData.isDescriptorExclusiveOption());
		prefStore.setDefault(DESCRIPTOR_REPEATABLE, false);
		
		prefStore.setDefault(NAME_VALIDATION, false);
		prefStore.setDefault(CIRCULAR_VALIDATION, false);
		prefStore.setDefault(UNDECLARED_VALIDATION, true);
		prefStore.setDefault(PUBLISH_AD_FOR_ACTIVITY_EXTENSION, true);
		
		SupportingElementData.setDescriptorExclusiveOption(! LibraryUIPreferences.getIncludeDescriptors());
	}

	/**
	 * Gets the initial default method library path preference.
	 */
	public static String getInitialDefaultLibraryPath() {
		if (defaultLibraryPath == null) {
			defaultLibraryPath = System.getProperty("user.home"); //$NON-NLS-1$
			String appName = LibraryUIPreferences.getApplicationShortName();
			if (appName != null && appName.length() > 0) {
				defaultLibraryPath = defaultLibraryPath + FileUtil.FILE_SEP
						+ appName + FileUtil.FILE_SEP
						+ DEFAULT_LIBRARY_FOLDER_NAME;
			} else {
				defaultLibraryPath = defaultLibraryPath + FileUtil.FILE_SEP
						+ DEFAULT_LIBRARY_FOLDER_NAME;
			}
		}
		return defaultLibraryPath;
	}

	/**
	 * Gets the default method library path preference.
	 */
	public static String getDefaultLibraryPath() {
		return prefStore.getString(DEFAULT_LIBRARY_PATH);
	}

	/**
	 * Sets the default method library path preference.
	 */
	public static void setDefaultLibraryPath(String value) {
		prefStore.setValue(DEFAULT_LIBRARY_PATH, value);
	}

	public static void setPublishUnopenActivitydd(boolean enabled) {
		prefStore.setValue(PUBLISH_UNOPEN_ACTIVITY_DD, enabled);
	}

	public static boolean getPublishUnopenActivitydd() {
		return prefStore.getBoolean(PUBLISH_UNOPEN_ACTIVITY_DD);
	}

	/**
	 * Setter method for Publish Activity Diagram for Activity Extension,
	 * 
	 * @param enabled
	 */
	public static void setPublishADForActivityExtension(boolean enabled) {
		prefStore.setValue(PUBLISH_AD_FOR_ACTIVITY_EXTENSION, enabled);
	}

	/**
	 * getter method for Publish Activity Diagram for Activity Extension flag
	 * from preference store,
	 */
	public static boolean getPublishADForActivityExtension() {
		return prefStore.getBoolean(PUBLISH_AD_FOR_ACTIVITY_EXTENSION);
	}

	/**
	 * Returns switch configuration value store in preference store
	 * 
	 * @return value - could be ALWAYS, NEVER, PROMPT
	 */
	public static String getSwitchConfig() {
		return prefStore.getString(PREF_SWITCH_CONFIG);
	}

	/**
	 * Saves switch configuration value in preference store
	 * 
	 * @param value
	 *            Value could be ALWAYS, NEVER, PROMPT
	 */
	public static void setSwitchConfig(String value) {
		prefStore.setValue(PREF_SWITCH_CONFIG, value);
	}

	
	/**
	 * Returns switch configuration preference key
	 * @return 
	 */
	public static String getSwitchConfigPreferenceKey()	{
		return PREF_SWITCH_CONFIG;		
	}
	
	/**
	 * Returns the Method Library path that was saved in a previous session.
	 * 
	 * @return The saved library path.
	 */
	public static String getSavedLibraryPath() {
		return prefStore.getString(SAVED_LIBRARY_PATH);
	}

	/**
	 * Saves a Method Library path to the Library UI preference store.
	 * 
	 * @param libPath
	 *            Path to a Method Library.
	 */
	public static void setSavedLibraryPath(String libPath) {
		String path = libPath;
		if (path.endsWith(MultiFileSaveUtil.DEFAULT_LIBRARY_MODEL_FILENAME)) {
			path = FileUtil.getParentDirectory(path);
		}
		prefStore.setValue(SAVED_LIBRARY_PATH, path);
		LibraryUIPlugin.getDefault().savePluginPreferences();
	}

	/**
	 * Returns the application short name passed in the main feature's
	 * plugin_customization.ini.
	 * 
	 * @return The passed-in application short name.
	 */
	public static String getApplicationShortName() {
		return prefStore.getString(APPLICATION_SHORT_NAME);
	}

	/**
	 * Gets the new library paths preference.
	 * 
	 * @return an array of method library paths
	 */
	public static String[] getNewLibraryPaths() {
		return PreferenceUtil.getStringValues(prefStore, NEW_LIBRARY_PATHS);
	}

	/**
	 * Gets the new library paths preference.
	 * 
	 * @return a collection of method library paths
	 */
	public static List<String> getNewLibraryPathsList() {
		return PreferenceUtil.getList(prefStore, NEW_LIBRARY_PATHS);
	}

	/**
	 * Adds a method library path to the new library paths preference.
	 * 
	 * @param path
	 *            a method library path
	 */
	public static void addNewLibraryPath(String path) {
		PreferenceUtil.addToList(prefStore, NEW_LIBRARY_PATHS, path,
				getDefaultLibraryPath());
	}

	/**
	 * Gets the open library paths preference.
	 * 
	 * @return an array of method library paths
	 */
	public static String[] getOpenLibraryPaths() {
		return PreferenceUtil.getStringValues(prefStore, OPEN_LIBRARY_PATHS);
	}

	/**
	 * Gets the open library paths preference.
	 * 
	 * @return a collection of method library paths
	 */
	public static List<String> getOpenLibraryPathsList() {
		return PreferenceUtil.getList(prefStore, OPEN_LIBRARY_PATHS);
	}

	/**
	 * Adds a method library path to the open library paths preference.
	 * 
	 * @param path
	 *            a method library path
	 */
	public static void addOpenLibraryPath(String path) {
		PreferenceUtil.addToList(prefStore, OPEN_LIBRARY_PATHS, path,
				getDefaultLibraryPath());
	}

	/**
	 * Gets the open library URIs preference.
	 * 
	 * @return a collection of method library URIs
	 */
	public static String[] getOpenLibraryURIs() {
		return PreferenceUtil.getStringValues(prefStore, OPEN_LIBRARY_URIS);
	}

	/**
	 * Gets the open library URIs preference.
	 * 
	 * @return a collection of method library URIs
	 */
	public static List getOpenLibraryURIsList() {
		return PreferenceUtil.getList(prefStore, OPEN_LIBRARY_URIS);
	}

	/**
	 * Adds a method library URI to the open library URIs preference.
	 * 
	 * @param uri
	 *            a method library URI
	 */
	public static void addOpenLibraryURI(String uri) {
		PreferenceUtil.addToList(prefStore, OPEN_LIBRARY_URIS, uri);
	}

	/**
	 * Save all preferences
	 * 
	 */
	public static void saveAllPreferences() {
		LibraryUIPlugin.getDefault().savePluginPreferences();
	}

	/**
	 * Returns optional feature value of work breakdown element in preference
	 * store
	 * 
	 * @return value - boolean
	 */
	public static boolean getWorkBreakDownElementOptional() {
		return prefStore.getBoolean(WORK_BREAKDOWNELEMENT_OPTIONAL);
	}

	/**
	 * Saves value for Optional feature of work breakdown element in preference
	 * store
	 * 
	 * @param value -
	 *            boolean true/false
	 * 
	 */
	public static void setWorkBreakdownElementOptional(boolean value) {
		prefStore.setValue(WORK_BREAKDOWNELEMENT_OPTIONAL, value);
	}

	/**
	 * Returns multiple occurrances feature value of work breakdown element in
	 * preference store
	 * 
	 * @return value - boolean
	 */
	public static boolean getWorkBreakDownElementMultipleOccurrances() {
		return prefStore.getBoolean(WORK_BREAKDOWNELEMENT_MULTIPLE_OCCURRANCES);
	}

	/**
	 * Saves value for Multiple Occurrances feature of work breakdown element in
	 * preference store
	 * 
	 * @param value -
	 *            boolean true/false
	 * 
	 */
	public static void setWorkBreakdownElementMultipleOccurrances(boolean value) {
		prefStore.setValue(WORK_BREAKDOWNELEMENT_MULTIPLE_OCCURRANCES, value);
	}

	/**
	 * Returns planned feature value of work breakdown element in preference
	 * store
	 * 
	 * @return value - boolean
	 */
	public static boolean getWorkBreakDownElementPlanned() {
		return prefStore.getBoolean(WORK_BREAKDOWNELEMENT_PLANNED);
	}

	/**
	 * Saves value for Planned feature of work breakdown element in preference
	 * store
	 * 
	 * @param value -
	 *            boolean true/false
	 * 
	 */
	public static void setWorkBreakdownElementPlanned(boolean value) {
		prefStore.setValue(WORK_BREAKDOWNELEMENT_PLANNED, value);
	}

	/**
	 * Returns EventDriven feature value of work breakdown element in preference
	 * store
	 * 
	 * @return value - boolean
	 */
	public static boolean getWorkBreakDownElementEventDriven() {
		return prefStore.getBoolean(WORK_BREAKDOWNELEMENT_EVENT_DRIVEN);
	}

	/**
	 * Saves value for EventDriven feature of work breakdown element in
	 * preference store
	 * 
	 * @param value -
	 *            boolean true/false
	 * 
	 */
	public static void setWorkBreakdownElementEventDriven(boolean value) {
		prefStore.setValue(WORK_BREAKDOWNELEMENT_EVENT_DRIVEN, value);
	}

	/**
	 * Returns Ongoing feature value of work breakdown element in preference
	 * store
	 * 
	 * @return value - boolean
	 */
	public static boolean getWorkBreakDownElementOngoing() {
		return prefStore.getBoolean(WORK_BREAKDOWNELEMENT_ONGOING);
	}

	/**
	 * Saves value for Ongoing feature of work breakdown element in preference
	 * store
	 * 
	 * @param value -
	 *            boolean true/false
	 * 
	 */
	public static void setWorkBreakdownElementOngoing(boolean value) {
		prefStore.setValue(WORK_BREAKDOWNELEMENT_ONGOING, value);
	}

	/**
	 * Returns Repeatable feature value of work breakdown element in preference
	 * store
	 * 
	 * @return value - boolean
	 */
	public static boolean getWorkBreakDownElementRepeatable() {
		return prefStore.getBoolean(WORK_BREAKDOWNELEMENT_REPEATABLE);
	}

	/**
	 * Saves value for Repeatable feature of work breakdown element in
	 * preference store
	 * 
	 * @param value -
	 *            boolean true/false
	 * 
	 */
	public static void setWorkBreakdownElementRepeatable(boolean value) {
		prefStore.setValue(WORK_BREAKDOWNELEMENT_REPEATABLE, value);
	}

	/**
	 * Returns optional feature value of work breakdown element in preference
	 * store
	 * 
	 * @return value - boolean
	 */
	public static boolean getDescriptorOptional() {
		return prefStore.getBoolean(DESCRIPTOR_OPTIONAL);
	}

	/**
	 * Saves value for Optional feature of descriptor in preference store
	 * 
	 * @param value -
	 *            boolean true/false
	 * 
	 */
	public static void setDescriptorOptional(boolean value) {
		prefStore.setValue(DESCRIPTOR_OPTIONAL, value);
	}

	/**
	 * Returns multiple occurrances feature value of descriptor in preference
	 * store
	 * 
	 * @return value - boolean
	 */
	public static boolean getDescriptorMultipleOccurrances() {
		return prefStore.getBoolean(DESCRIPTOR_MULTIPLE_OCCURRANCES);
	}

	/**
	 * Saves value for Multiple Occurrances feature of descriptor in preference
	 * store
	 * 
	 * @param value -
	 *            boolean true/false
	 * 
	 */
	public static void setDescriptorMultipleOccurrances(boolean value) {
		prefStore.setValue(DESCRIPTOR_MULTIPLE_OCCURRANCES, value);
	}

	/**
	 * Returns planned feature value of descriptor in preference store
	 * 
	 * @return value - boolean
	 */
	public static boolean getDescriptorPlanned() {
		return prefStore.getBoolean(DESCRIPTOR_PLANNED);
	}

	/**
	 * Saves value for Planned feature of descriptor in preference store
	 * 
	 * @param value -
	 *            boolean true/false
	 * 
	 */
	public static void setDescriptorPlanned(boolean value) {
		prefStore.setValue(DESCRIPTOR_PLANNED, value);
	}

	/**
	 * Returns EventDriven feature value of descriptor in preference store
	 * 
	 * @return value - boolean
	 */
	public static boolean getDescriptorEventDriven() {
		return prefStore.getBoolean(DESCRIPTOR_EVENT_DRIVEN);
	}

	/**
	 * Saves value for EventDriven feature of descriptor in preference store
	 * 
	 * @param value -
	 *            boolean true/false
	 * 
	 */
	public static void setDescriptorEventDriven(boolean value) {
		prefStore.setValue(DESCRIPTOR_EVENT_DRIVEN, value);
	}

	/**
	 * Returns Ongoing feature value of descriptor in preference store
	 * 
	 * @return value - boolean
	 */
	public static boolean getDescriptorOngoing() {
		return prefStore.getBoolean(DESCRIPTOR_ONGOING);
	}

	/**
	 * Saves value for Ongoing feature of descriptor in preference store
	 * 
	 * @param value -
	 *            boolean true/false
	 * 
	 */
	public static void setDescriptorOngoing(boolean value) {
		prefStore.setValue(DESCRIPTOR_ONGOING, value);
	}

	/**
	 * Returns "include descriptors" feature value in preference store
	 * 
	 * @return value - boolean
	 */
	public static boolean getIncludeDescriptors() {
		return prefStore.getBoolean(INCLUDE_DESCRIPTORS);
	}

	/**
	 * Saves value for "include descriptors"  feature in preference store
	 * 
	 * @param value -
	 *            boolean true/false
	 * 
	 */
	public static void setIncludeDescriptors(boolean value) {
		prefStore.setValue(INCLUDE_DESCRIPTORS, value);
		SupportingElementData.setDescriptorExclusiveOption(!value);
	}
	
	/**
	 * Returns Repeatable feature value of descriptor in preference store
	 * 
	 * @return value - boolean
	 */
	public static boolean getDescriptorRepeatable() {
		return prefStore.getBoolean(DESCRIPTOR_REPEATABLE);
	}

	/**
	 * Saves value for Repeatable feature of descriptor in preference store
	 * 
	 * @param value -
	 *            boolean true/false
	 * 
	 */
	public static void setDescriptorRepeatable(boolean value) {
		prefStore.setValue(DESCRIPTOR_REPEATABLE, value);
	}

	public static void applyDefaultValuesForBreakdownElementAttributes() {
		Object[] nonDescriptorDefaultValueDefs = {
				new Object[] {
						UmaPackage.eINSTANCE
								.getWorkBreakdownElement_IsEventDriven(),
						Boolean.valueOf(LibraryUIPreferences
								.getWorkBreakDownElementEventDriven()) },
				new Object[] {
						UmaPackage.eINSTANCE
								.getBreakdownElement_HasMultipleOccurrences(),
						Boolean.valueOf(LibraryUIPreferences
								.getWorkBreakDownElementMultipleOccurrances()) },
				new Object[] {
						UmaPackage.eINSTANCE
								.getWorkBreakdownElement_IsOngoing(),
						Boolean.valueOf(LibraryUIPreferences
								.getWorkBreakDownElementOngoing()) },
				new Object[] {
						UmaPackage.eINSTANCE.getBreakdownElement_IsOptional(),
						Boolean.valueOf(LibraryUIPreferences
								.getWorkBreakDownElementOptional()) },
				new Object[] {
						UmaPackage.eINSTANCE.getBreakdownElement_IsPlanned(),
						Boolean.valueOf(LibraryUIPreferences
								.getWorkBreakDownElementPlanned()) },
				new Object[] {
						UmaPackage.eINSTANCE
								.getWorkBreakdownElement_IsRepeatable(),
						Boolean.valueOf(LibraryUIPreferences
								.getWorkBreakDownElementRepeatable()) } };
		EClass[] nonDescriptorTypes = { UmaPackage.eINSTANCE.getPhase(),
				UmaPackage.eINSTANCE.getIteration(),
				UmaPackage.eINSTANCE.getActivity(),
				UmaPackage.eINSTANCE.getMilestone() };

		Object[] descriptorDefaultValueDefs = {
				new Object[] {
						UmaPackage.eINSTANCE
								.getWorkBreakdownElement_IsEventDriven(),
						Boolean.valueOf(LibraryUIPreferences
								.getDescriptorEventDriven()) },
				new Object[] {
						UmaPackage.eINSTANCE
								.getBreakdownElement_HasMultipleOccurrences(),
						Boolean.valueOf(LibraryUIPreferences
								.getDescriptorMultipleOccurrances()) },
				new Object[] {
						UmaPackage.eINSTANCE
								.getWorkBreakdownElement_IsOngoing(),
						Boolean.valueOf(LibraryUIPreferences
								.getDescriptorOngoing()) },
				new Object[] {
						UmaPackage.eINSTANCE.getBreakdownElement_IsOptional(),
						Boolean.valueOf(LibraryUIPreferences
								.getDescriptorOptional()) },
				new Object[] {
						UmaPackage.eINSTANCE.getBreakdownElement_IsPlanned(),
						Boolean.valueOf(LibraryUIPreferences
								.getDescriptorPlanned()) },
				new Object[] {
						UmaPackage.eINSTANCE
								.getWorkBreakdownElement_IsRepeatable(),
						Boolean.valueOf(LibraryUIPreferences
								.getDescriptorRepeatable()) } };
		EClass[] descriptorTypes = { UmaPackage.eINSTANCE.getRoleDescriptor(),
				UmaPackage.eINSTANCE.getTaskDescriptor(),
				UmaPackage.eINSTANCE.getWorkProductDescriptor() };

		Object[] defaultValueDefs = {
				new Object[] { nonDescriptorTypes,
						nonDescriptorDefaultValueDefs },
				new Object[] { descriptorTypes, descriptorDefaultValueDefs } };

		for (int i = 0; i < defaultValueDefs.length; i++) {
			Object[] defaultValueDef = (Object[]) defaultValueDefs[i];
			EClass[] types = (EClass[]) defaultValueDef[0];
			Object[] featureDefaultValueDefs = (Object[]) defaultValueDef[1];
			for (int k = 0; k < featureDefaultValueDefs.length; k++) {
				Object[] featureDefaultValue = (Object[]) featureDefaultValueDefs[k];
				EStructuralFeature feature = (EStructuralFeature) featureDefaultValue[0];
				Object defaultValue = featureDefaultValue[1];
				for (int j = 0; j < types.length; j++) {
					EClass type = types[j];
					DefaultValueManager.INSTANCE.setDefaultValue(type, feature,
							defaultValue);
				}
			}
		}
	}


	/**
	 * Returns "name validation" feature value in preference store
	 * 
	 * @return value - boolean
	 */
	public static boolean getNameValidation() {
		return prefStore.getBoolean(NAME_VALIDATION);
	}

	/**
	 * Saves value for "name validation"  feature in preference store
	 * 
	 * @param value -
	 *            boolean true/false
	 * 
	 */
	public static void setNameValidation(boolean value) {
		prefStore.setValue(NAME_VALIDATION, value);
	}
	
	/**
	 * Returns "circular validation" feature value in preference store
	 * 
	 * @return value - boolean
	 */
	public static boolean getCircularValidation() {
		return prefStore.getBoolean(CIRCULAR_VALIDATION);
	}

	/**
	 * Saves value for "circular validation"  feature in preference store
	 * 
	 * @param value -
	 *            boolean true/false
	 * 
	 */
	public static void setCircularValidation(boolean value) {
		prefStore.setValue(CIRCULAR_VALIDATION, value);

	}
	
	/**
	 * Returns "Undeclared validation" feature value in preference store
	 * 
	 * @return value - boolean
	 */
	public static boolean getUndeclaredValidation() {
		return prefStore.getBoolean(UNDECLARED_VALIDATION);
	}

	/**
	 * Saves value for "Undeclared validation"  feature in preference store
	 * 
	 * @param value -
	 *            boolean true/false
	 * 
	 */
	public static void setUndeclaredValidation(boolean value) {
		prefStore.setValue(UNDECLARED_VALIDATION, value);
	}
	
	public static void update(IValidationManager mgr) {
		if (mgr != null) {
			mgr.setNameCheck(getNameValidation());
			mgr.setCircularDependancyCheck(getCircularValidation());
			mgr.setUndeclaredDependancyCheck(getUndeclaredValidation());
		}
	}
	
}
