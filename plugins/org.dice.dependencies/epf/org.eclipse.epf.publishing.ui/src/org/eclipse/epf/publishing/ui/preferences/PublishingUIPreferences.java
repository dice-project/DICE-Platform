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
package org.eclipse.epf.publishing.ui.preferences;

import java.util.List;

import org.eclipse.epf.common.utils.FileUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.layout.elements.ActivityLayout;
import org.eclipse.epf.library.ui.preferences.LibraryUIPreferences;
import org.eclipse.epf.publishing.ui.PublishingUIPlugin;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * Manages the Publishing UI preferences.
 * 
 * @author Kelvin Low
 * @author Jinhua Xi
 * @since 1.0
 */
public class PublishingUIPreferences {

	private static final String PUBLISH_ENTIRE_CONFIG = "publishEntireConfig"; //$NON-NLS-1$
	
	private static final String PUBLISH_PROCESSES = "publishProcesses"; //$NON-NLS-1$
	
	private static final String PUBLISH_INCLUDE_BASE_PROCESS = "publishIncludeBaseProcess"; //$NON-NLS-1$

	private static final String TITLE = "title"; //$NON-NLS-1$

	private static final String BANNER_IMAGE = "bannerImage"; //$NON-NLS-1$

	private static final String ABOUT_HTML = "aboutHTML"; //$NON-NLS-1$

	private static final String FEEDBACK_URL = "feedbackURL"; //$NON-NLS-1$	

	private static final String FORBIDDEN_CHARS = "fobiddenChars"; //$NON-NLS-1$	

	private static final String INCLUDE_GLOSSARY = "includeGlossary"; //$NON-NLS-1$

	private static final String INCLUDE_INDEX = "includeIndex"; //$NON-NLS-1$

	private static final String CHECK_EXTERNAL_LINKS = "checkExternalLinks"; //$NON-NLS-1$

	private static final String CONVERT_BROKEN_LINKS = "convertBrokenLinks"; //$NON-NLS-1$

	private static final String LIGHT_WEIGHT_TREE = "lightWeightTree"; //$NON-NLS-1$

	private static final String EXTRA_DESCRIPTOR_INFO = "extraDescriptorInfo"; //$NON-NLS-1$
	
	private static final String SHOW_LINKED_ELEMENT_FOR_DESCRIPTOR = "showLinkedElementForDescriptor"; //$NON-NLS-1$
	
	private static final String Ignore_Dynamic_Parents = "ignoreDynamicParents"; //$NON-NLS-1$	
	
	private static final String Exclude_Unused_WPDs = "excludeUnusedWPDs"; //$NON-NLS-1$	
	
	private static final String FULFILL_DESCRIPTOR_SLOT_BY_CONTENT = "fulfillDescriptorSlotByContent"; //$NON-NLS-1$

	private static final String PUBLISH_UNOPEN_ACTIVITY_DD = "publishUnopenActivityDD"; //$NON-NLS-1$

	private static final String PUBLISH_AD_FOR_ACTIVITY_EXTENSION = "publishADForActivityExtension"; //$NON-NLS-1$

	private static final String PUBLISH_PATH = "publishPath"; //$NON-NLS-1$

	private static final String PUBLISH_STATIC_WEB_SITE = "publishStaticWebSite"; //$NON-NLS-1$

	private static final String INCLUDE_SERVLET_SEARCH = "includeServletSearch"; //$NON-NLS-1$

	private static final String WEBAPP_NAME = "webAppName"; //$NON-NLS-1$

	private static final String INCLUDE_SEARCH = "includeSearch"; //$NON-NLS-1$

	private static final String INITIALIZED = "initialized"; //$NON-NLS-1$

	private static final String SHOW_RELATED_DESCRIPTORS = "showRelatedDescriptors"; //$NON-NLS-1$
	
	private static final String SHOW_RELATED_LINKS = "showRelatedLinks"; //$NON-NLS-1$

	private static final String SHOW_RELATED_DESCRIPTORS_OPTION = "showRelatedDescriptorsOption"; //$NON-NLS-1$

	private static final String SHOW_DESCRIPTORS_IN_NAVIGATIONTREE = "showDescriptorsInNavigationTree"; //$NON-NLS-1$

	private static final String DEFAULT_ACTIVITY_TAB = "defaultActivityTab"; //$NON-NLS-1$

	private static final String DEFAULT_PUBLISH_FOLDER_NAME = "Publish"; //$NON-NLS-1$

	private static String defaultTitle = null;

	private static String defaultBannerImage = null;

	private static String defaultAboutHTML = null;

	private static String defaultFeedbackURL = null;

	private static String defaultPublishPath = null;

	// The plug-in specific preference store.
	private static IPreferenceStore prefStore = PublishingUIPlugin.getDefault()
			.getPreferenceStore();

	static {
		// Initialize the default preference values.
		prefStore.setDefault(PUBLISH_ENTIRE_CONFIG, true);

		prefStore.setDefault(TITLE, getDefaultTitle());
		prefStore.setDefault(BANNER_IMAGE, getDefaultBannerImage());
		prefStore.setDefault(ABOUT_HTML, getDefaultAboutHTML());
		prefStore.setDefault(FEEDBACK_URL, getDefaultFeedbackURL());
		prefStore.setDefault(FORBIDDEN_CHARS, getDefaultForbiddenChars());
		prefStore.setDefault(INCLUDE_GLOSSARY, false);
		prefStore.setDefault(INCLUDE_INDEX, false);
		prefStore.setDefault(CHECK_EXTERNAL_LINKS, false);
		prefStore.setDefault(CONVERT_BROKEN_LINKS, false);
		prefStore.setDefault(PUBLISH_UNOPEN_ACTIVITY_DD, false);
		prefStore.setDefault(PUBLISH_AD_FOR_ACTIVITY_EXTENSION, true);
		prefStore.setDefault(LIGHT_WEIGHT_TREE, true);
		prefStore.setDefault(EXTRA_DESCRIPTOR_INFO,
				getDefaultExtraDescriptorInfo());
		prefStore.setDefault(SHOW_LINKED_ELEMENT_FOR_DESCRIPTOR,
				getDefaultShowLinkedElementForDescriptor());				
		prefStore.setDefault(Ignore_Dynamic_Parents,
				getDefaultIgnoreDynamicParents());
		prefStore.setDefault(Exclude_Unused_WPDs,
				getDefaultExcludeUnusedWPDs());
		prefStore.setDefault(FULFILL_DESCRIPTOR_SLOT_BY_CONTENT,
				getDefaultFulfillDescriptorSlotByContent());
		
		prefStore.setDefault(DEFAULT_ACTIVITY_TAB,
				ActivityLayout.TAB_NAME_ACTIVITY_WBS);

		prefStore.setDefault(PUBLISH_PATH, getDefaultPublishPath());
		prefStore.setDefault(PUBLISH_STATIC_WEB_SITE, false);
		prefStore.setDefault(INCLUDE_SERVLET_SEARCH, true);
		prefStore.setDefault(WEBAPP_NAME, "rmcwebsite"); //$NON-NLS-1$			
		prefStore.setDefault(INCLUDE_SEARCH, true);		
	}

	/**
	 * Gets the publish entire configuration preference value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 * @return <code>true</code> if the preference is set
	 */
	public static boolean getPublishEntireConfig(String configId) {
		if (getConfigPrefInitialized(configId)) {
			return prefStore.getBoolean(getConfigPrefPrefix(configId)
					+ PUBLISH_ENTIRE_CONFIG);
		} else {
			return prefStore.getBoolean(PUBLISH_ENTIRE_CONFIG);
		}
	}

	/**
	 * Sets the publish entire configurationpreference value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 * @param value
	 *            the preference value
	 */
	public static void setPublishEntireConfig(String configId, boolean value) {
		prefStore.setValue(getConfigPrefPrefix(configId)
				+ PUBLISH_ENTIRE_CONFIG, value);
	}

	/**
	 * Sets the publish entire configuration preference to be default value
	 * 
	 * @param configId
	 *            a method configuration GUID
	 */
	public static void removePublishEntireConfig(String configId) {
		prefStore.setToDefault(getConfigPrefPrefix(configId)
				+ PUBLISH_ENTIRE_CONFIG);
	}
	
	public static List<String> getProcesses(String configId) {
		String guidStr = prefStore.getString(configId + PUBLISH_PROCESSES);
		return TngUtil.convertStringsToList(guidStr);
	}

	public static void setProcesses(String configId, List<String> guidList) {
		String guidStr = TngUtil.convertListToString(guidList);
		prefStore.setValue(configId + PUBLISH_PROCESSES, guidStr);
	}
	
	public static Boolean getIncludeBaseProcesses(String configId) {
		return prefStore.getBoolean(configId + PUBLISH_INCLUDE_BASE_PROCESS);
	}
	
	public static void setIncludeBaseProcesses(String configId, boolean value) {
		prefStore.setValue(configId + PUBLISH_INCLUDE_BASE_PROCESS, value);
	}

	/**
	 * Gets the default title for a published website.
	 * 
	 * @return the default title for a published website
	 */
	public static String getDefaultTitle() {
		if (defaultTitle == null) {
			String url = PublishingUIPlugin.getDefault().getString("title"); //$NON-NLS-1$
			if (url == null || url.length() == 0 || url.startsWith("[")) { //$NON-NLS-1$
				url = ""; //$NON-NLS-1$
			}
			defaultTitle = url;
		}
		return defaultTitle;
	}

	/**
	 * Gets the web site title preference value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 * @return the web site title
	 */
	public static String getTitle(String configId) {
		if (getConfigPrefInitialized(configId)) {
			return prefStore.getString(getConfigPrefPrefix(configId) + TITLE);
		} else {
			return prefStore.getString(TITLE);
		}
	}

	/**
	 * Sets the web site title preference value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 * @param title
	 *            the web site title
	 */
	public static void setTitle(String configId, String title) {
		prefStore.setValue(getConfigPrefPrefix(configId) + TITLE, title);
	}

	/**
	 * Sets the web site title preference to be default value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 */
	public static void removeTitle(String configId) {
		prefStore.setToDefault(getConfigPrefPrefix(configId) + TITLE);
	}

	/**
	 * Gets the default banner image for a published website.
	 * 
	 * @return the default banner image for a published website
	 */
	public static String getDefaultBannerImage() {
		if (defaultBannerImage == null) {
			String image = PublishingUIPlugin.getDefault().getString(
					"bannerImage"); //$NON-NLS-1$
			if (image == null || image.length() == 0 || image.startsWith("[")) { //$NON-NLS-1$
				image = ""; //$NON-NLS-1$
			}
			defaultBannerImage = image;
		}
		return defaultBannerImage;
	}

	/**
	 * Gets the banner image preference value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 * @return the banner image
	 */
	public static String getBannerImage(String configId) {
		if (getConfigPrefInitialized(configId)) {
			return prefStore.getString(getConfigPrefPrefix(configId)
					+ BANNER_IMAGE);
		} else {
			return prefStore.getString(BANNER_IMAGE);
		}
	}

	/**
	 * Sets the banner image preference value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 * @param image
	 *            the banner image URL
	 */
	public static void setBannerImage(String configId, String image) {
		prefStore.setValue(getConfigPrefPrefix(configId) + BANNER_IMAGE, image);
	}

	/**
	 * Sets the banner image preference to be default value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 */
	public static void removeBannerImage(String configId) {
		prefStore.setToDefault(getConfigPrefPrefix(configId) + BANNER_IMAGE);
	}

	/**
	 * Gets the default about HTML for a published website.
	 * 
	 * @return the default about HTML for a published website
	 */
	public static String getDefaultAboutHTML() {
		if (defaultAboutHTML == null) {
			String file = PublishingUIPlugin.getDefault()
					.getString("aboutHTML"); //$NON-NLS-1$
			if (file == null || file.length() == 0 || file.startsWith("[")) { //$NON-NLS-1$
				file = ""; //$NON-NLS-1$
			}
			defaultAboutHTML = file;
		}
		return defaultAboutHTML;
	}

	/**
	 * Gets the about HTML preference value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 * @return the about HTML file path
	 */
	public static String getAboutHTML(String configId) {
		if (getConfigPrefInitialized(configId)) {
			return prefStore.getString(getConfigPrefPrefix(configId)
					+ ABOUT_HTML);
		} else {
			return prefStore.getString(ABOUT_HTML);
		}
	}

	/**
	 * Sets the about HTML preference value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 * @param path
	 *            the about HTML file path
	 */
	public static void setAboutHTML(String configId, String path) {
		prefStore.setValue(getConfigPrefPrefix(configId) + ABOUT_HTML, path);
	}

	/**
	 * Sets the about HTML preference to be default value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 */
	public static void removeAboutHTML(String configId) {
		prefStore.setToDefault(getConfigPrefPrefix(configId) + ABOUT_HTML);
	}

	/**
	 * Gets the default feedback URL for a published website.
	 * 
	 * @return the default feedback URL for a published website
	 */
	public static String getDefaultFeedbackURL() {
		if (defaultFeedbackURL == null) {
			defaultFeedbackURL = "http://www.published_website.com/feedback"; //$NON-NLS-1$
		}
		return defaultFeedbackURL;
	}

	/**
	 * Gets the default forbidden chars string
	 * 
	 * @return the default forbidden chars string
	 */
	public static String getDefaultForbiddenChars() {
			return ""; 	//$NON-NLS-1$
	}
	
	/**
	 * Gets the forbidden chars string
	 * 
	 * @return the forbidden chars string
	 */
	public static String getForbiddenChars() {
		return prefStore.getString(FORBIDDEN_CHARS);
	}

	/**
	 * Sets the forbidden chars string value.
	 * 
	 * @param value
	 *            the forbidden chars string value
	 */
	public static void setForbiddenChars(String value) {
		prefStore.setValue(FORBIDDEN_CHARS, value);
	}
	
	/**
	 * Gets the feedback URL preference value.
	 * 
	 * @return the feedback URL
	 */
	public static String getFeedbackURL() {
		return prefStore.getString(FEEDBACK_URL);
	}

	/**
	 * Sets the feedback URL preference value.
	 * 
	 * @param url
	 *            the feedback URL
	 */
	public static void setFeedbackURL(String url) {
		prefStore.setValue(FEEDBACK_URL, url);
	}

	/**
	 * Gets the feedback URL preference value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 * @return the feedback URL
	 */
	public static String getFeedbackURL(String configId) {
		if (getConfigPrefInitialized(configId)) {
			return prefStore.getString(getConfigPrefPrefix(configId)
					+ FEEDBACK_URL);
		} else {
			return prefStore.getString(FEEDBACK_URL);
		}
	}

	/**
	 * Sets the feedback URL preference value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 * @param url
	 *            the feedback URL
	 */
	public static void setFeedbackURL(String configId, String url) {
		prefStore.setValue(getConfigPrefPrefix(configId) + FEEDBACK_URL, url);
	}

	/**
	 * Sets the feedback URL preference to be default value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 */
	public static void removeFeedbackURL(String configId) {
		prefStore.setToDefault(getConfigPrefPrefix(configId) + FEEDBACK_URL);
	}

	/**
	 * Gets the include glossary preference value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 * @return <code>true</code> if the preference is set
	 */
	public static boolean getIncludeGlossary(String configId) {
		if (getConfigPrefInitialized(configId)) {
			return prefStore.getBoolean(getConfigPrefPrefix(configId)
					+ INCLUDE_GLOSSARY);
		} else {
			return prefStore.getBoolean(INCLUDE_GLOSSARY);
		}
	}

	/**
	 * Sets the include glossary preference value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 * @param value
	 *            the preference value
	 */
	public static void setIncludeGlossary(String configId, boolean value) {
		prefStore.setValue(getConfigPrefPrefix(configId) + INCLUDE_GLOSSARY,
				value);
	}

	/**
	 * Sets the include glossary preference to be default value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 */
	public static void removeIncludeGlossary(String configId) {
		prefStore
				.setToDefault(getConfigPrefPrefix(configId) + INCLUDE_GLOSSARY);
	}

	/**
	 * Gets the include index preference value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 * @return <code>true</code> if the preference is set
	 */
	public static boolean getIncludeIndex(String configId) {
		if (getConfigPrefInitialized(configId)) {
			return prefStore.getBoolean(getConfigPrefPrefix(configId)
					+ INCLUDE_INDEX);
		} else {
			return prefStore.getBoolean(INCLUDE_INDEX);
		}
	}

	/**
	 * Sets the include index preference value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 * @param value
	 *            the preference value
	 */
	public static void setIncludeIndex(String configId, boolean value) {
		prefStore
				.setValue(getConfigPrefPrefix(configId) + INCLUDE_INDEX, value);
	}

	/**
	 * Sets the include index preference to be default value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 */
	public static void removeIncludeIndex(String configId) {
		prefStore.setToDefault(getConfigPrefPrefix(configId) + INCLUDE_INDEX);
	}

	/**
	 * Gets the check external links preference value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 * @return <code>true</code> if the preference is set
	 */
	public static boolean getCheckExternalLinks(String configId) {
		if (getConfigPrefInitialized(configId)) {
			return prefStore.getBoolean(getConfigPrefPrefix(configId)
					+ CHECK_EXTERNAL_LINKS);
		} else {
			return prefStore.getBoolean(CHECK_EXTERNAL_LINKS);
		}
	}

	/**
	 * Sets the check external links preference value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 * @param value
	 *            the preference value
	 */
	public static void setCheckExternalLinks(String configId, boolean value) {
		prefStore.setValue(
				getConfigPrefPrefix(configId) + CHECK_EXTERNAL_LINKS, value);
	}

	/**
	 * Sets the check external links preference to be default value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 */
	public static void removeCheckExternalLinks(String configId) {
		prefStore.setToDefault(getConfigPrefPrefix(configId)
				+ CHECK_EXTERNAL_LINKS);
	}

	/**
	 * Gets the convert broken links preference value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 * @return <code>true</code> if the preference is set
	 */
	public static boolean getConvertBrokenLinks(String configId) {
		if (getConfigPrefInitialized(configId)) {
			return prefStore.getBoolean(getConfigPrefPrefix(configId)
					+ CONVERT_BROKEN_LINKS);
		} else {
			return prefStore.getBoolean(CONVERT_BROKEN_LINKS);
		}
	}

	/**
	 * Sets the convert broken links preference value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 * @param value
	 *            the preference value
	 */
	public static void setConvertBrokenLinks(String configId, boolean value) {
		prefStore.setValue(
				getConfigPrefPrefix(configId) + CONVERT_BROKEN_LINKS, value);
	}

	/**
	 * Sets the convert broken links preference to be default value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 */
	public static void removeConvertBrokenLinks(String configId) {
		prefStore.setToDefault(getConfigPrefPrefix(configId)
				+ CONVERT_BROKEN_LINKS);
	}

	/**
	 * Gets the publish unopen activity detail diagrams preference value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 * @return <code>true</code> if the preference is set
	 */
	public static boolean getPublishUnopenActivityDD(String configId) {
		if (getConfigPrefInitialized(configId)) {
			return prefStore.getBoolean(getConfigPrefPrefix(configId)
					+ PUBLISH_UNOPEN_ACTIVITY_DD);
		} else {
			return prefStore.getBoolean(PUBLISH_UNOPEN_ACTIVITY_DD);
		}
	}

	/**
	 * Sets the publish unopen activity detail diagrams preference value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 * @param value
	 *            the preference value
	 */
	public static void setPublishUnopenActivityDD(String configId, boolean value) {
		prefStore.setValue(getConfigPrefPrefix(configId)
				+ PUBLISH_UNOPEN_ACTIVITY_DD, value);
	}

	/**
	 * Sets the publish unopen activity detail diagrams preference to be default
	 * value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 */
	public static void removePublishUnopenActivityDD(String configId) {
		prefStore.setToDefault(getConfigPrefPrefix(configId)
				+ PUBLISH_UNOPEN_ACTIVITY_DD);
	}

	/**
	 * Gets the publish activiy diagrams for base activities with unmodified
	 * extension preference value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 * @return <code>true</code> if the preference is set
	 */
	public static boolean getPublishADForActivityExtension(String configId) {
		if (getConfigPrefInitialized(configId)) {
			return prefStore.getBoolean(getConfigPrefPrefix(configId)
					+ PUBLISH_AD_FOR_ACTIVITY_EXTENSION);
		} else {
			return prefStore.getBoolean(PUBLISH_AD_FOR_ACTIVITY_EXTENSION);
		}
	}

	/**
	 * Sets the publish activiy diagrams for base activities with unmodified
	 * extension preference value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 * @param value
	 *            the preference value
	 */
	public static void setPublishADForActivityExtension(String configId,
			boolean value) {
		prefStore.setValue(getConfigPrefPrefix(configId)
				+ PUBLISH_AD_FOR_ACTIVITY_EXTENSION, value);
	}

	/**
	 * Sets the publish activiy diagrams for base activities with unmodified
	 * extension preference to be default value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 */
	public static void removePublishADForActivityExtension(String configId) {
		prefStore.setToDefault(getConfigPrefPrefix(configId)
				+ PUBLISH_AD_FOR_ACTIVITY_EXTENSION);
	}

	/**
	 * Gets the light weight tree preference value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 * @return <code>true</code> if the preference is set
	 */
	public static boolean getLightWeightTree(String configId) {
		if (getConfigPrefInitialized(configId)) {
			return prefStore.getBoolean(getConfigPrefPrefix(configId)
					+ LIGHT_WEIGHT_TREE);
		} else {
			return prefStore.getBoolean(LIGHT_WEIGHT_TREE);
		}
	}

	/**
	 * Sets the light weight tree preference value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 * @param value
	 *            the preference value
	 */
	public static void setLightWeightTree(String configId, boolean value) {
		prefStore.setValue(getConfigPrefPrefix(configId) + LIGHT_WEIGHT_TREE,
				value);
	}

	/**
	 * Sets the light weight tree preference to be default value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 */
	public static void removeLightWeightTree(String configId) {
		prefStore.setToDefault(getConfigPrefPrefix(configId)
				+ LIGHT_WEIGHT_TREE);
	}

	/**
	 * Gets the default extra descriptor info preference value.
	 * 
	 * @return <code>true</code> if the preference is set
	 */
	public static boolean getDefaultExtraDescriptorInfo() {
		return false;
	}
	
	/**
	 * Gets the extra descriptor info preference value.
	 * 
	 * @return the preference value
	 */
	public static boolean getExtraDescriptorInfo() {
		return prefStore.getBoolean(EXTRA_DESCRIPTOR_INFO);
	}

	/**
	 * Gets the default linked element for descriptor preference value.
	 * 
	 * @return <code>true</code> if the preference is set
	 */
	public static boolean getDefaultShowLinkedElementForDescriptor() {
		return false;
	}
	
	/**
	 * Sets linked element for descriptor preference value.
	 * 
	 * @param value
	 *            the preference value
	 */
	public static void setShowLinkedElementForDescriptor(boolean value) {
		prefStore.setValue(SHOW_LINKED_ELEMENT_FOR_DESCRIPTOR, value);
	}
	
	/**
	 * Gets the linked element for descriptor preference value.
	 * 
	 * @return the preference value
	 */
	public static boolean getShowLinkedElementForDescriptor() {
		return prefStore.getBoolean(SHOW_LINKED_ELEMENT_FOR_DESCRIPTOR);
	}

	
	public static void setIgnoreDynamicParents(boolean value) {
		prefStore.setValue(Ignore_Dynamic_Parents, value);
	}
	
	public static boolean getDefaultIgnoreDynamicParents() {
		return true;
	}
	
	public static boolean getIgnoreDynamicParents() {
		return prefStore.getBoolean(Ignore_Dynamic_Parents);
	}
	
	public static void setExcludeUnusedWPDs(boolean value) {
		prefStore.setValue(Exclude_Unused_WPDs, value);
	}
	
	public static boolean getDefaultExcludeUnusedWPDs() {
		return false;
	}
	
	public static boolean getExcludeUnusedWPDs() {
		return prefStore.getBoolean(Exclude_Unused_WPDs);
	}
	
	/**
	 * Sets the default extra descriptor info preference value.
	 * 
	 * @param value
	 *            the preference value
	 */
	public static void setExtraDescriptorInfo(boolean value) {
		prefStore.setValue(EXTRA_DESCRIPTOR_INFO, value);
	}
	
	/**
	 * Gets the default fulfill descriptor slot by content preference value.
	 * 
	 * @return <code>true</code> if the preference is set
	 */
	public static boolean getDefaultFulfillDescriptorSlotByContent() {
		return false;
	}
	
	/**
	 * Gets the fulfill descriptor slot by content preference value.
	 * 
	 * @return the preference value
	 */
	public static boolean getFulfillDescriptorSlotByContent() {
		return prefStore.getBoolean(FULFILL_DESCRIPTOR_SLOT_BY_CONTENT);
	}

	/**
	 * Sets the fulfill descriptor slot by content preference value.
	 * 
	 * @param value
	 *            the preference value
	 */
	public static void setFulfillDescriptorSlotByContent(boolean value) {
		prefStore.setValue(FULFILL_DESCRIPTOR_SLOT_BY_CONTENT, value);
	}

	/**
	 * Gets the extra descriptor info preference value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 * @return <code>true</code> if the preference is set
	 */
	public static boolean getExtraDescriptorInfo(String configId) {
		if (getConfigPrefInitialized(configId)) {
			return prefStore.getBoolean(getConfigPrefPrefix(configId)
					+ EXTRA_DESCRIPTOR_INFO);
		} else {
			return prefStore.getBoolean(EXTRA_DESCRIPTOR_INFO);
		}
	}

	/**
	 * Sets the extra descriptor info preference value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 * @param value
	 *            the preference value
	 */
	public static void setExtraDescriptorInfo(String configId, boolean value) {
		prefStore.setValue(getConfigPrefPrefix(configId)
				+ EXTRA_DESCRIPTOR_INFO, value);
	}

	/**
	 * Sets the extra descriptor info preference to be default value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 */
	public static void removeExtraDescriptorInfo(String configId) {
		prefStore.setToDefault(getConfigPrefPrefix(configId)
				+ EXTRA_DESCRIPTOR_INFO);
	}

	/**
	 * Gets the default path for a published web site.
	 * 
	 * @return the default path for a published web site.
	 */
	public static String getDefaultPublishPath() {
		if (defaultPublishPath == null) {
			defaultPublishPath = System.getProperty("user.home"); //$NON-NLS-1$
			String appName = LibraryUIPreferences.getApplicationShortName();
			if (appName != null && appName.length() > 0) {
				defaultPublishPath = defaultPublishPath + FileUtil.FILE_SEP
						+ appName + FileUtil.FILE_SEP
						+ DEFAULT_PUBLISH_FOLDER_NAME;
			} else {
				defaultPublishPath = defaultPublishPath + FileUtil.FILE_SEP
						+ DEFAULT_PUBLISH_FOLDER_NAME;
			}
		}
		return defaultPublishPath;
	}

	/**
	 * Gets the publish path preference value.
	 * 
	 * @return the publish path
	 */
	public static String getPublishPath() {
		return prefStore.getString(PUBLISH_PATH);
	}

	/**
	 * Sets the publish path preference value.
	 * 
	 * @param path
	 *            the publish path
	 */
	public static void setPublishPath(String path) {
		prefStore.setValue(PUBLISH_PATH, path);
	}

	/**
	 * Gets the publish path preference value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 * @return the publish path
	 */
	public static String getPublishPath(String configId) {
		if (getConfigPrefInitialized(configId)) {
			return prefStore.getString(getConfigPrefPrefix(configId)
					+ PUBLISH_PATH);
		} else {
			return prefStore.getString(PUBLISH_PATH);
		}
	}

	/**
	 * Sets the publish path preference value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 * @param path
	 *            the publish path
	 */
	public static void setPublishPath(String configId, String path) {
		prefStore.setValue(getConfigPrefPrefix(configId) + PUBLISH_PATH, path);
	}

	/**
	 * Sets the publish path preference to be default value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 */
	public static void removePublishPath(String configId) {
		prefStore.setToDefault(getConfigPrefPrefix(configId) + PUBLISH_PATH);
	}

	/**
	 * Gets the publish static web site preference value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 * @return <code>true</code> if the preference is set
	 */
	public static boolean getPublishStaticWebSite(String configId) {
		if (getConfigPrefInitialized(configId)) {
			return prefStore.getBoolean(getConfigPrefPrefix(configId)
					+ PUBLISH_STATIC_WEB_SITE);
		} else {
			return prefStore.getBoolean(PUBLISH_STATIC_WEB_SITE);
		}
	}

	/**
	 * Sets the publish static web site preference value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 * @param value
	 *            the preference value
	 */
	public static void setPublishStaticWebSite(String configId, boolean value) {
		prefStore.setValue(getConfigPrefPrefix(configId)
				+ PUBLISH_STATIC_WEB_SITE, value);
	}

	/**
	 * Sets the publish static web site preference to be default value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 */
	public static void removePublishStaticWebSite(String configId) {
		prefStore.setToDefault(getConfigPrefPrefix(configId)
				+ PUBLISH_STATIC_WEB_SITE);
	}

	/**
	 * Gets the include applet search preference value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 * @return <code>true</code> if the preference is set
	 */
	public static boolean getIncludeServletSearch(String configId) {
		if (getConfigPrefInitialized(configId)) {
			return prefStore.getBoolean(getConfigPrefPrefix(configId)
					+ INCLUDE_SERVLET_SEARCH);
		} else {
			return prefStore.getBoolean(INCLUDE_SERVLET_SEARCH);
		}
	}

	/**
	 * Sets the include servlet search preference value..
	 * 
	 * @param configId
	 *            a method configuration GUID
	 * @param value
	 *            the preference value
	 */
	public static void setIncludeServletSearch(String configId, boolean value) {
		prefStore.setValue(getConfigPrefPrefix(configId)
				+ INCLUDE_SERVLET_SEARCH, value);
	}

	/**
	 * Sets the include servlet search preference to be default value..
	 * 
	 * @param configId
	 *            a method configuration GUID
	 */
	public static void removeIncludeServletSearch(String configId) {
		prefStore.setToDefault(getConfigPrefPrefix(configId)
				+ INCLUDE_SERVLET_SEARCH);
	}

	/**
	 * Gets the include search preference value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 * @return <code>true</code> if the preference is set
	 */
	public static boolean getIncludeSearch(String configId) {
		if (getConfigPrefInitialized(configId)) {
			return prefStore.getBoolean(getConfigPrefPrefix(configId)
					+ INCLUDE_SEARCH);
		} else {
			return prefStore.getBoolean(INCLUDE_SEARCH);
		}
	}

	/**
	 * Sets the include search preference value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 * @param value
	 *            the preference value
	 */
	public static void setIncludeSearch(String configId, boolean value) {
		prefStore.setValue(getConfigPrefPrefix(configId) + INCLUDE_SEARCH,
				value);
	}

	/**
	 * Sets the include search preference to be default value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 */
	public static void removeIncludeSearch(String configId) {
		prefStore.setToDefault(getConfigPrefPrefix(configId) + INCLUDE_SEARCH);
	}

	/**
	 * Gets the web application name preference value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 * @return the preference value
	 */
	public static String getWebAppName(String configId) {
		if (getConfigPrefInitialized(configId)) {
			return prefStore.getString(getConfigPrefPrefix(configId)
					+ WEBAPP_NAME);
		} else {
			return prefStore.getString(WEBAPP_NAME);
		}
	}

	/**
	 * Sets the web application name preference value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 * @param name
	 *            the web application name
	 */
	public static void setWebAppName(String configId, String name) {
		prefStore.setValue(getConfigPrefPrefix(configId) + WEBAPP_NAME, name);
	}

	/**
	 * Sets the web application name preference to be default value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 */
	public static void removeWebAppName(String configId) {
		prefStore.setToDefault(getConfigPrefPrefix(configId) + WEBAPP_NAME);
	}

	/**
	 * Gets the method configuration specific preference prefix.
	 * 
	 * @param configId
	 *            the name of a method configuration
	 * @return a preference prefix assigned to the configuration
	 */
	public static String getConfigPrefPrefix(String configId) {
		return configId + "."; //$NON-NLS-1$
	}

	/**
	 * Gets the configuration specific preferences initialized preference value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 * @return the preference value
	 */
	public static boolean getConfigPrefInitialized(String configId) {
		return prefStore
				.getBoolean(getConfigPrefPrefix(configId) + INITIALIZED);
	}

	/**
	 * Sets the configuration specific preferences initialized preference value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 * @param value
	 *            the preference value
	 */
	public static void setConfigPrefInitialized(String configId, boolean value) {
		prefStore.setValue(getConfigPrefPrefix(configId) + INITIALIZED, value);
	}

	/**
	 * Sets the configuration specific preferences initialized preference to be
	 * default value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 */
	public static void removeConfigPrefInitialized(String configId) {
		prefStore.setToDefault(getConfigPrefPrefix(configId) + INITIALIZED);
	}

	/**
	 * Gets the show related descriptors in content page preference value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 * @return the preference value
	 */
	public static boolean getShowRelatedDescriptors(String configId) {
		return prefStore.getBoolean(getConfigPrefPrefix(configId)
				+ SHOW_RELATED_DESCRIPTORS);
	}

	/**
	 * Sets the show related descriptors in content page preference value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 * @param value
	 *            the preference value
	 */
	public static void setShowRelatedDescriptors(String configId, boolean value) {
		prefStore.setValue(getConfigPrefPrefix(configId)
				+ SHOW_RELATED_DESCRIPTORS, value);
	}
	
	/**
	 * Sets the show related descriptors in content page preference to be
	 * default value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 */
	public static void removeShowRelatedDescriptors(String configId) {
		prefStore.setToDefault(getConfigPrefPrefix(configId)
				+ SHOW_RELATED_DESCRIPTORS);
	}
	
	/**
	 * Gets the show related links of role, task and work product in navigate page.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 * @return the preference value
	 */
	public static boolean getShowRelatedLinks(String configId) {
		return prefStore.getBoolean(getConfigPrefPrefix(configId)
				+ SHOW_RELATED_LINKS);
	}

	/**
	 * Sets the show related links of role, task and work product in navigate page.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 * @param value
	 *            the preference value
	 */
	public static void setShowRelatedLinks(String configId, boolean value) {
		prefStore.setValue(getConfigPrefPrefix(configId)
				+ SHOW_RELATED_LINKS, value);
	}
	
	/**
	 * Sets the show related links in navigate page preference to be
	 * default value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 */
	public static void removeShowRelatedLinks(String configId) {
		prefStore.setToDefault(getConfigPrefPrefix(configId)
				+ SHOW_RELATED_LINKS);
	}

	/**
	 * Gets the show related descriptors option in content page preference value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 * @return the preference value
	 */
	public static boolean getShowRelatedDescriptorsOption(String configId) {
		return prefStore.getBoolean(getConfigPrefPrefix(configId)
				+ SHOW_RELATED_DESCRIPTORS_OPTION);
	}

	/**
	 * Sets the show related descriptors option in content page preference value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 * @param value
	 *            the preference value
	 */
	public static void setShowRelatedDescriptorsOption(String configId, boolean value) {
		prefStore.setValue(getConfigPrefPrefix(configId)
				+ SHOW_RELATED_DESCRIPTORS_OPTION, value);
	}

	/**
	 * Sets the show related descriptors option in content page preference to be
	 * default value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 */
	public static void removeShowRelatedDescriptorsOption(String configId) {
		prefStore.setToDefault(getConfigPrefPrefix(configId)
				+ SHOW_RELATED_DESCRIPTORS_OPTION);
	}

	/**
	 * Gets the show descriptors in navigation tree preference value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 * @return the preference value
	 */
	public static boolean getShowDescriptorsInNavigationTree(String configId) {
		return prefStore.getBoolean(getConfigPrefPrefix(configId)
				+ SHOW_DESCRIPTORS_IN_NAVIGATIONTREE);
	}

	/**
	 * Sets the show descriptors in navigation tree preference value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 * @param value
	 *            the preference value
	 */
	public static void setShowDescriptorsInNavigationTree(String configId,
			boolean value) {
		prefStore.setValue(getConfigPrefPrefix(configId)
				+ SHOW_DESCRIPTORS_IN_NAVIGATIONTREE, value);
	}

	/**
	 * Sets the show descriptors in navigation tree preference to be default
	 * value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 */
	public static void removeShowDescriptorsInNavigationTree(String configId) {
		prefStore.setToDefault(getConfigPrefPrefix(configId)
				+ SHOW_DESCRIPTORS_IN_NAVIGATIONTREE);
	}

	/**
	 * Gets the default activity tab preference value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 * @return the default activity tab name
	 */
	public static String getDefaultActivityTab(String configId) {
		if (getConfigPrefInitialized(configId)) {
			return prefStore.getString(getConfigPrefPrefix(configId)
					+ DEFAULT_ACTIVITY_TAB);
		} else {
			return prefStore.getString(DEFAULT_ACTIVITY_TAB);
		}
	}

	/**
	 * Sets the default activity tab preference value.
	 * 
	 * @param configId
	 *            a method configuration GUID
	 * @param tabName
	 *            the default activity tab name
	 */
	public static void setDefaultActivityTab(String configId, String tabName) {
		prefStore.setValue(
				getConfigPrefPrefix(configId) + DEFAULT_ACTIVITY_TAB, tabName);
	}
	
	public static void setShowLinkedElementForDescriptor(String configId, boolean value) {
		prefStore.setValue(getConfigPrefPrefix(configId)
				+ SHOW_LINKED_ELEMENT_FOR_DESCRIPTOR, value);
	}
	
	public static boolean getShowLinkedElementForDescriptor(String configId) {
		if (getConfigPrefInitialized(configId)) {
			return prefStore.getBoolean(getConfigPrefPrefix(configId)
					+ SHOW_LINKED_ELEMENT_FOR_DESCRIPTOR);
		} else {
			return prefStore.getBoolean(SHOW_LINKED_ELEMENT_FOR_DESCRIPTOR);
		}
	}
	
	public static void removeShowLinkedElementForDescriptor(String configId) {
		prefStore.setToDefault(getConfigPrefPrefix(configId)
				+ SHOW_LINKED_ELEMENT_FOR_DESCRIPTOR);
	}
	
	/**
	 * Saves all the preferences.
	 */
	public static void saveAllPreferences() {
		PublishingUIPlugin.getDefault().savePluginPreferences();
	}

}
