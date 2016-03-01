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
package org.eclipse.epf.common.service.versioning;

import org.osgi.framework.Version;

/**
 * This class represents a single version of EPF Composer.
 * It contains all the versions of the sub-components of EPF.
 * 
 * @author Jeff Hardy
 * @sicne 1.0
 */
public class EPFVersion {

	// the tool version
	protected Version toolVersion;
	
	// the library version
	protected Version libraryVersion;
	
	// the schema version
	protected Version xmlSchemaVersion;
	
	
	public EPFVersion() {
		this.toolVersion = Version.emptyVersion;
		this.libraryVersion = Version.emptyVersion;
		this.xmlSchemaVersion = Version.emptyVersion;
	};
	
	public EPFVersion(String toolVersion, String libraryVersion, String xmlSchemaVersion) {
		this.toolVersion = new Version(toolVersion);
		this.libraryVersion = new Version(libraryVersion);
		this.xmlSchemaVersion = new Version(xmlSchemaVersion);
	}

	/**
	 * @return the tool version
	 */
	public Version getToolVersion() {
		return toolVersion;
	}

	/**
	 * @return the library version
	 */
	public Version getLibraryVersion() {
		return libraryVersion;
	}

	/**
	 * @return the XML schema version
	 */
	public Version getXMLSchemaVersion() {
		return xmlSchemaVersion;
	}
	
	/**
	 * Compares the given tool version to this one
	 * 
	 * @see org.osgi.framework.Version#compareTo()
	 * 
	 * @param schemaVersion The schema version to be compared.
	 * @return A negative integer if this tool version is less than the given one<br />
	 * Zero if the this tool version is the same as the given one<br />
	 * A positive integer if this tool version is greater than the given one<br />
	 * 
	 */
	public int compareToolVersionTo(Version toolVersion) {
		if (!VersionUtil.isVersionCheckingDisabled())
			return getToolVersion().compareTo(toolVersion);
		else 
			return 0;
	}
	
	/**
	 * Compares the given library version to this one
	 * 
	 * @see org.osgi.framework.Version#compareTo()
	 * 
	 * @param schemaVersion The schema version to be compared.
	 * @return A negative integer if this library version is less than the given one<br />
	 * Zero if the this library version is the same as the given one<br />
	 * A positive integer if this library version is greater than the given one<br />
	 * 
	 */
	public int compareLibraryVersionTo(Version libraryVersion) {
		if (!VersionUtil.isVersionCheckingDisabled())
			return getLibraryVersion().compareTo(libraryVersion);
		else 
			return 0;
	}

	/**
	 * Compares the given XML Schema version to this one
	 * 
	 * @see org.osgi.framework.Version#compareTo()
	 * 
	 * @param schemaVersion The schema version to be compared.
	 * @return A negative integer if this XML Schema version is less than the given one<br />
	 * Zero if the this XML Schema version is the same as the given one<br />
	 * A positive integer if this XML Schema version is greater than the given one<br />
	 * 
	 */
	public int compareXMLSchemaVersionTo(Version XMLSchemaVersion) {
		if (!VersionUtil.isVersionCheckingDisabled())
			return getXMLSchemaVersion().compareTo(XMLSchemaVersion);
		else 
			return 0;
	}
	
	public String toString() {
		return getToolVersion().toString() + "," + //$NON-NLS-1$
		getLibraryVersion().toString() + "," + //$NON-NLS-1$
		getXMLSchemaVersion().toString();
	}
	
	public boolean equals(Object object) {
		if (object == this) { // quicktest
			return true;
		}

		if (!(object instanceof EPFVersion)) {
			return false;
		}

		EPFVersion other = (EPFVersion) object;
		return (getToolVersion().equals(other.getToolVersion())) && (getLibraryVersion().equals(other.getLibraryVersion()))
				&& (getXMLSchemaVersion().equals(other.getXMLSchemaVersion()));
	}
	
	@Override
	public int hashCode() {
		int result = toolVersion.hashCode();
		result ^= libraryVersion.hashCode();
		result ^= xmlSchemaVersion.hashCode();
		return result;
	}
}
