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
package org.eclipse.epf.common.service.versioning;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.epf.common.service.ServicePlugin;
import org.eclipse.epf.common.utils.FileUtil;
import org.eclipse.epf.common.utils.StrUtil;
import org.osgi.framework.Bundle;
import org.osgi.framework.Version;

/**
 * Provides utilities to query a method library schema and the version of the
 * tool that created it.
 * 
 * @author Jeff Hardy
 * @since 1.0
 */
public class VersionUtil {

	public static class VersionCheckInfo {
		public VersionCheckInfo(String id, String toolVersion,
				String currentMinToolVersion, int result) {
			this.toolID = id;
			this.toolVersion = toolVersion;
			this.currentMinToolVersion = currentMinToolVersion;
			this.result = result;
		}

		public String toolID;

		public String toolVersion;

		public String currentMinToolVersion;

		/**
		 * < 0: fileToolVersion is older than currentMinToolVersion <br/> == 0:
		 * match <br/> > 0 : fileToolVersion is newer than currentMinToolVersion
		 * <br/>
		 */
		public int result;
	}

	/**
	 * The extension point namespace.
	 */
	public static final String EXTENSION_POINT_NAMESPACE = ServicePlugin.class
			.getPackage().getName();

	/**
	 * The extension point name.
	 */
	public static final String EXTENSION_POINT_NAME = "version"; //$NON-NLS-1$

	public static final String EXTENSION_VERSIONS_INFO = "versions"; //$NON-NLS-1$

	public static final String EXTENSION_LIB_EXTENSION_CHECK = "libraryExtensionVersionCheck"; //$NON-NLS-1$

	private static final String DISABLE_VERSION_CHECKING_PREF = "disable_version_checking"; //$NON-NLS-1$

	public static Map<String, EPFVersions> versions = new LinkedHashMap<String, EPFVersions>();

	public static Map<String, ILibraryExtensionVersionCheck> libExtCheckers = new LinkedHashMap<String, ILibraryExtensionVersionCheck>();

	protected static boolean versionCheckingDisabled = false;

	/**
	 * List of toolIDs to check, in order
	 */
	private static List<String> toolIDsCheckList = new ArrayList<String>();

	static {
		toolIDsCheckList.add(EPFVersions.TOOL_ID);

		// read properties file
		try {
			String valueStr = ServicePlugin.getDefault().getPreferenceStore()
					.getString(DISABLE_VERSION_CHECKING_PREF);
			versionCheckingDisabled = Boolean.valueOf(valueStr).booleanValue();
		} catch (MissingResourceException e) {
			versionCheckingDisabled = false;
		}

		// Process the "org.eclipse.epf.common.version" extension point
		// contributors.
		IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();
		IExtensionPoint extensionPoint = extensionRegistry.getExtensionPoint(
				EXTENSION_POINT_NAMESPACE, EXTENSION_POINT_NAME);
		if (extensionPoint != null) {
			IExtension[] extensions = extensionPoint.getExtensions();
			for (int i = 0; i < extensions.length; i++) {
				IExtension extension = extensions[i];
				initExtension(extension);
			}
		}

	}

	protected static void initExtension(IExtension extension) {
		String extensionID = extension.getSimpleIdentifier();
		String pluginId = extension.getNamespaceIdentifier();
		Bundle bundle = Platform.getBundle(pluginId);
		IConfigurationElement[] configElements = extension
				.getConfigurationElements();
		for (int j = 0; j < configElements.length; j++) {
			IConfigurationElement configElement = configElements[j];
			try {
				if (EXTENSION_VERSIONS_INFO.equals(configElement.getName())) {
					String className = configElement.getAttribute("class"); //$NON-NLS-1$
					if (className != null) {
						versions.put(extensionID, (EPFVersions) bundle
								.loadClass(className).newInstance());
						break;
					}
				}
				if (EXTENSION_LIB_EXTENSION_CHECK.equals(configElement
						.getName())) {
					String className = configElement.getAttribute("class"); //$NON-NLS-1$
					if (className != null) {
						libExtCheckers.put(extensionID,
								(ILibraryExtensionVersionCheck) bundle
										.loadClass(className).newInstance());
						break;
					}
				}
			} catch (Exception e) {
				ServicePlugin.getDefault().getLogger().logError(e);
			}
		}
	}

	/**
	 * 
	 * @return the map of Tool IDs-to-Versions class
	 */
	public static Map getVersionsMap() {
		return Collections.unmodifiableMap(versions);
	}

	/**
	 * 
	 * @return a Set of all known Tool IDs
	 */
	public static Set<String> getAllToolIDs() {
		return Collections.unmodifiableSet(versions.keySet());
	}

	/**
	 * 
	 * @param toolID
	 * @return The Versions class for the given Tool ID
	 */
	public static EPFVersions getVersions(String toolID) {
		return (EPFVersions) versions.get(toolID);
	}

	/**
	 * 
	 * @return true iff version checking is disabled
	 */
	public static boolean isVersionCheckingDisabled() {
		return versionCheckingDisabled;
	}

	public static final Pattern p_XMIVersionPattern = Pattern.compile(
			"(\\w+?):version=\"(.+?)\"", Pattern.DOTALL); //$NON-NLS-1$

	public static final Pattern p_XMLVersionAttributePattern = Pattern.compile(
			"tool=\"(.*?)\"", Pattern.DOTALL); //$NON-NLS-1$

	public static final Pattern p_XMI_ELEMENT_START_TAG = Pattern
			.compile("<?xml version=\".+?\" encoding=\".+?\"?>"); //$NON-NLS-1$

	public static final String XML_ELEMENT_END_TAG = ">"; //$NON-NLS-1$

	public static final String XMI_ATTRIBUTE_TAG = "xmi"; //$NON-NLS-1$

	public static final String XML_ELEMENT_START_TAG = "<uma:MethodLibrary"; //$NON-NLS-1$

	public static final String XML_VERSIONS_SEPARATOR = ";"; //$NON-NLS-1$

	public static final String XML_TOOL_VERSION_SEPARATOR = "="; //$NON-NLS-1$

	/**
	 * Given an XML file, will read the tool="foo=a.b.c.d;bar=w.x.y.z"
	 * attributes and return a map of the id-to-versions.
	 * 
	 * @param xmlFile
	 *            file to process
	 * @return null if no versions found; a map of the id-to-versions otherwise
	 */
	protected static Map readVersionsFromXMLFile(File xmlFile) {
		Map<String, String> versions = new LinkedHashMap<String, String>();
		StringBuffer buf = null;
		try {
			buf = FileUtil.readFile(xmlFile, FileUtil.ENCODING_UTF_8);
		} catch (Exception ex) {
			ServicePlugin.getDefault().getLogger().logError(ex);
		}
		if (buf != null) {
			int xmiElementStartIdx = buf.indexOf(XML_ELEMENT_START_TAG);
			if (xmiElementStartIdx != -1) {
				int xmiElementEndIdx = buf.indexOf(XML_ELEMENT_END_TAG,
						xmiElementStartIdx + XML_ELEMENT_START_TAG.length());
				if (xmiElementEndIdx != -1) {
					String rootElement = buf.substring(xmiElementStartIdx,
							xmiElementEndIdx);
					Matcher mAttribute = p_XMLVersionAttributePattern
							.matcher(rootElement);
					if (mAttribute.find()) {
						String toolsAttribute = mAttribute.group(1);
						if (toolsAttribute != null
								&& toolsAttribute.length() > 0) {
							String[] versionsArray = StrUtil.split(
									toolsAttribute, XML_VERSIONS_SEPARATOR);
							for (int i = 0; i < versionsArray.length; i++) {
								if (versionsArray[i] != null
										&& versionsArray[i].length() > 0) {
									String[] versionInfoArray = StrUtil.split(
											versionsArray[i],
											XML_TOOL_VERSION_SEPARATOR);
									if (versionInfoArray.length != 2)
										continue;
									String toolID = versionInfoArray[0];
									String toolVersion = versionInfoArray[1];
									if (toolID != null
											&& toolID.trim().length() > 0
											&& toolVersion != null
											&& toolVersion.trim().length() > 0)
										versions.put(toolID, toolVersion);
								}
							}
						}
					}
				}
			}
		}
		if (versions.size() == 0) {
			return null;
		} else {
			return versions;
		}
	}

	/**
	 * Given an XMI file, will read the foo:version="x.x.x" attributes and
	 * return a map of the id-to-versions.
	 * 
	 * @param xmlFile
	 *            file to process
	 * @return null if no versions found; a map of the id-to-versions otherwise
	 */
	protected static Map readVersionsFromXMIFile(File xmiFile) {
		Map<String, String> versions = new LinkedHashMap<String, String>();
		StringBuffer buf = null;
		try {
			buf = FileUtil.readFile(xmiFile, FileUtil.ENCODING_UTF_8);
		} catch (Exception ex) {
			ServicePlugin.getDefault().getLogger().logError(ex);
		}
		if (buf != null) {
			Matcher docStartMatcher = p_XMI_ELEMENT_START_TAG.matcher(buf);
			if (docStartMatcher.find()) {
				int docHeaderEndIdx = docStartMatcher.end();
				int xmiElementEndIdx = buf.indexOf(XML_ELEMENT_END_TAG,
						docHeaderEndIdx);
				if (xmiElementEndIdx != -1) {
					String rootElement = buf.substring(docHeaderEndIdx,
							xmiElementEndIdx + 1);
					Matcher m = p_XMIVersionPattern.matcher(rootElement);
					while (m.find()) {
						String toolID = m.group(1);
						if (toolID.equals(XMI_ATTRIBUTE_TAG))
							continue;
						String toolVersion = m.group(2);
						if (toolID != null && toolID.trim().length() > 0
								&& toolVersion != null
								&& toolVersion.trim().length() > 0)
							versions.put(toolID, toolVersion);
					}
				}
			}
		}
		if (versions.size() == 0) {
			return null;
		} else {
			return versions;
		}
	}

	/**
	 * Given a file, will read version information and return a map of the
	 * id-to-versions.
	 * 
	 * @param file
	 *            file to process
	 * @return null if no versions found; a map of the id-to-versions otherwise
	 */
	public static Map readVersionsFromFile(File file) {
		if (file.getName().toUpperCase().endsWith("XMI")) { //$NON-NLS-1$
			return readVersionsFromXMIFile(file);
		} else if (file.getName().toUpperCase().endsWith("XML")) { //$NON-NLS-1$
			return readVersionsFromXMLFile(file);
		} else {
			Map versions = readVersionsFromXMIFile(file);
			if (versions != null)
				return versions;
			versions = readVersionsFromXMLFile(file);
			if (versions != null)
				return versions;
		}
		return null;
	}

	/**
	 * Given a file, will compare with current XML Schema version.
	 * 
	 * @param file
	 * @return null if file tool version can not be found; a VersionCheckInfo
	 *         object otherwise
	 */
	public static VersionCheckInfo checkXMLVersion(File file) {
		Map versionMap = VersionUtil.readVersionsFromFile(file);
		if (versionMap == null) {
			return null;
		} else {
			VersionCheckInfo vci = null;
			for (Iterator iter = toolIDsCheckList.iterator(); iter.hasNext();) {
				String toolID = (String) iter.next();
				if (versionMap.get(toolID) != null) {
					String toolVersion = (String) versionMap.get(toolID);
					vci = _checkXMLVersion(toolID, toolVersion);
					if (vci != null)
						return vci;
				}
			}
		}
		return null;
	}

	private static VersionCheckInfo _checkXMLVersion(String toolID,
			String toolVersion) {
		// get the class that stores all the version info for this tool ID
		EPFVersions versions = getVersions(toolID);
		if (versions != null) {
			EPFVersion minCurrVersion = versions
					.getMinToolVersionForCurrentXMLSchemaVersion();
			int result = minCurrVersion.compareToolVersionTo(new Version(
					toolVersion));
			String currentMinToolVersion = minCurrVersion.getToolVersion()
					.toString();
			return new VersionCheckInfo(toolID, toolVersion,
					currentMinToolVersion, -result);
		}
		return null;
	}

	/**
	 * Given a file, compares with current library schema version
	 * 
	 * @param file
	 * @return null if file tool version can not be found; a VersionCheckInfo
	 *         object otherwise
	 */
	public static VersionCheckInfo checkLibraryVersion(File file) {
		if (file.getName().equals("library.xmi")) { //$NON-NLS-1$
			// check library extensions first
			for (Iterator iter = toolIDsCheckList.iterator(); iter.hasNext();) {
				String toolID = (String) iter.next();
				if (libExtCheckers.get(toolID) != null) {
					VersionCheckInfo vci = null;
					ILibraryExtensionVersionCheck extCheck = (ILibraryExtensionVersionCheck) libExtCheckers
							.get(toolID);
					vci = extCheck.checkLibraryVersion(file.getParentFile());
					if (vci != null)
						return vci;
				}
			}
		}
		Map versionMap = VersionUtil.readVersionsFromFile(file);
		if (versionMap == null) {
			return null;
		} else {
			VersionCheckInfo vci = null;
			for (Iterator iter = toolIDsCheckList.iterator(); iter.hasNext();) {
				String toolID = (String) iter.next();
				if (versionMap.get(toolID) != null) {
					String toolVersion = (String) versionMap.get(toolID);
					vci = _checkLibVersion(toolID, toolVersion);
					if (vci != null)
						return vci;
				}
			}
		}
		return null;
	}

	private static VersionCheckInfo _checkLibVersion(String toolID,
			String toolVersion) {
		// get the class that stores all the version info for this tool ID
		EPFVersions versions = getVersions(toolID);
		if (versions != null) {
			EPFVersion minCurrVersion = versions
					.getMinToolVersionForCurrentLibraryVersion();
			int result = minCurrVersion.compareToolVersionTo(new Version(
					toolVersion));
			String currentMinToolVersion = minCurrVersion.getToolVersion()
					.toString();
			return new VersionCheckInfo(toolID, toolVersion,
					currentMinToolVersion, -result);
		}
		return null;
	}

	/**
	 * 
	 * @return the first toolID that is checked. In most cases, the ID of the
	 *         currently running application
	 */
	public static String getPrimaryToolID() {
		return (String) toolIDsCheckList.get(0);
	}

	/**
	 * Adds a tool ID to the order of tools to check.
	 * 
	 * @param toolID
	 *            toolID to add
	 * @param toolIDfollowing
	 *            if null, adds toolID to front of list. otherwise, adds toolID
	 *            before the specified ID
	 * @return false iff toolIDfollowing is not null and could not be found in
	 *         list; true otherwise
	 */
	public static boolean addToolID(String toolID, String toolIDfollowing) {
		if (toolIDfollowing != null) {
			int idx = toolIDsCheckList.indexOf(toolIDfollowing);
			if (idx != -1) {
				toolIDsCheckList.add(idx, toolID);
				return true;
			} else {
				return false;
			}
		}
		toolIDsCheckList.add(0, toolID);
		return true;
	}

}
