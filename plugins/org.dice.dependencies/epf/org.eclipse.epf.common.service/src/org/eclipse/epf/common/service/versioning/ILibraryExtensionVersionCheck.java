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

import java.io.File;

import org.eclipse.epf.common.service.versioning.VersionUtil.VersionCheckInfo;

/**
 * The interface for performing method library version check.
 * 
 * @author Jeff Hardy
 * @since 1.0
 */
public interface ILibraryExtensionVersionCheck {

	/**
	 * Checks and returns the version information associated with a method
	 * library.
	 * 
	 * @param file
	 *            a method library XMI file
	 * @return <code>null</code> if the library XMI file does not contain the
	 *         version information of the tool that created it; or
	 *         <code>VersionCheckInfo</code> object
	 */
	public VersionCheckInfo checkLibraryVersion(File libPath);

}
