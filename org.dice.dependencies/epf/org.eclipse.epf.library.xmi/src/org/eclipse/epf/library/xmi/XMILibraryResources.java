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
package org.eclipse.epf.library.xmi;

import org.eclipse.osgi.util.NLS;

/**
 * The XMI Library resource bundle.
 * 
 * @author Phong Nguyen Le
 * @author Kelvin Low
 * @since 1.0
 */
public class XMILibraryResources extends NLS {

	private static String BUNDLE_NAME = XMILibraryResources.class.getPackage()
			.getName()
			+ ".Resources"; //$NON-NLS-1$

	public static String cannotWriteToFiles;

	public static String elementPath;

	public static String filePath;

	public static String filesWithLoadErrors_title;

	public static String loadError;

	public static String missingFiles_title;

	public static String promptRemoveReferencesToFilesWithLoadErrors_msg;

	public static String promptRemoveReferencesToMissingFiles_msg;

	public static String readOnlyFiles_msg;

	public static String readOnlyFiles_title;
	
	public static String libraryAlreadyExistsError_msg;
	
	public static String migrateXMLLibrary_taskName;
	
	public static String convertToSynFree_taskName;

	static {
		NLS.initializeMessages(BUNDLE_NAME, XMILibraryResources.class);
	}

	private XMILibraryResources() {
		// Do not instantiate
	}

}
