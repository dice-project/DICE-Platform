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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.osgi.framework.Version;

/**
 * This class stores all of the version information for the known EPF versions.
 * 
 * @author Jeff Hardy
 * @since 1.0
 */
public class EPFVersions {

	public static final String TOOL_ID = "epf"; //$NON-NLS-1$

	// the name space URI string for the TOOL_ID
	private static String nsUri = "http://www.eclipse.org/epf"; //$NON-NLS-1$

	// EPF 1.0
	private static EPFVersion EPF_10 = new EPFVersion("1.0", //$NON-NLS-1$  tool version
			"1.0.3.0", //$NON-NLS-1$  library version
			"1.0.0"); //$NON-NLS-1$  xml schema version

	// EPF 1.1 - This is EPF 1.2 Iteration 1
	private static EPFVersion EPF_11 = new EPFVersion("1.1", //$NON-NLS-1$  tool version
			"1.0.4.0", //$NON-NLS-1$  library version
			"1.0.0"); //$NON-NLS-1$  xml schema version

	// EPF 1.2 release
	private static EPFVersion EPF_12 = new EPFVersion("1.2", //$NON-NLS-1$  tool version
			"1.0.4.1", //$NON-NLS-1$  library version
			"1.0.1"); //$NON-NLS-1$  xml schema version

	// EPF 1.5 release
	private static EPFVersion EPF_15 = new EPFVersion("1.5", //$NON-NLS-1$  tool version
			"1.0.5.0", //$NON-NLS-1$  library version
			"1.0.2"); //$NON-NLS-1$  xml schema version
	
	// EPF 1.5 release
	private static EPFVersion EPF_151 = new EPFVersion("1.5.1", //$NON-NLS-1$  tool version
			"1.0.6.0", //$NON-NLS-1$  library version
			"1.0.2"); //$NON-NLS-1$  xml schema version

	private static EPFVersion currentVersion = EPF_151;

	public EPFVersion getCurrentVersion() {
		return currentVersion;
	}

	public EPFVersion getVersion(String toolVersion) {
		if (toolVersion != null) {
			for (Iterator iter = getAllVersions().iterator(); iter.hasNext();) {
				EPFVersion ver = (EPFVersion) iter.next();
				if (ver.getToolVersion().equals(new Version(toolVersion))) {
					return ver;
				}
			}
		}
		return null;
	}

	public Collection getAllVersions() {
		List<EPFVersion> versions = new ArrayList<EPFVersion>();
		versions.add(EPF_10);
		versions.add(EPF_11);
		versions.add(EPF_12);
		versions.add(EPF_15);
		versions.add(EPF_151);
		return versions;
	}

	/**
	 * 
	 * @param libraryVersion
	 * @return the minimum tool version that uses the specified library version
	 */
	public EPFVersion getMinToolVersionForLibraryVersion(Version libraryVersion) {
		for (Iterator iter = getAllVersions().iterator(); iter.hasNext();) {
			EPFVersion versionInfo = (EPFVersion) iter.next();
			if (versionInfo.getLibraryVersion().compareTo(libraryVersion) == 0) {
				return versionInfo;
			}
		}
		return null;
	}

	/**
	 * 
	 * @return the minimum tool version that uses the current library version
	 */
	public EPFVersion getMinToolVersionForCurrentLibraryVersion() {
		Version libraryVersion = getCurrentVersion().getLibraryVersion();
		return getMinToolVersionForLibraryVersion(libraryVersion);
	}

	/**
	 * 
	 * @param xmlSchemaVersion
	 * @return the minimum tool version that uses the specified XML Schema
	 *         version
	 */
	public EPFVersion getMinToolVersionForXMLSchemaVersion(
			Version xmlSchemaVersion) {
		for (Iterator iter = getAllVersions().iterator(); iter.hasNext();) {
			EPFVersion versionInfo = (EPFVersion) iter.next();
			if (versionInfo.getXMLSchemaVersion().compareTo(xmlSchemaVersion) == 0) {
				return versionInfo;
			}
		}
		return null;
	}

	/**
	 * 
	 * @return the minimum tool version that uses the current XML Schema version
	 */
	public EPFVersion getMinToolVersionForCurrentXMLSchemaVersion() {
		Version xmlSchemaVersion = getCurrentVersion().getXMLSchemaVersion();
		return getMinToolVersionForXMLSchemaVersion(xmlSchemaVersion);
	}

	/**
	 * 
	 * @return the name space URI string for the TOOL_ID
	 */
	public String getNsURI() {
		return nsUri;
	}

}
