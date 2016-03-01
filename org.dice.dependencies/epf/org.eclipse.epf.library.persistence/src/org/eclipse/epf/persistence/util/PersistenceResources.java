/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.epf.persistence.util;

import org.eclipse.osgi.util.NLS;

/**
 * Message bundle class for library persistence
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public final class PersistenceResources extends NLS {

	private static String BUNDLE_NAME = PersistenceResources.class.getPackage()
			.getName()
			+ ".Resources"; //$NON-NLS-1$

	private PersistenceResources() {
		// Do not instantiate
	}

	public static String cannot_create_dir_msg;

	public static String invalidNameError_msg;

	public static String objNotFoundError_msg;

	public static String loadLibraryError_msg;

	public static String loadLibraryError_details;

	public static String refreshLibraryFilesTask_name;

	public static String resourceAutoRefreshJob_name;

	public static String restoreResourceError_msg;

	public static String FileManager_fileReadOnly;

	public static String ErrMsg_CouldNotDelete;

	public static String renameError_msg;

	public static String backupError_msg;

	public static String modifyFileError_msg;
	
	public static String filePathNameTooLong_msg;

	public static String loadResourceError_msg;

	public static String loadResourceErrorWithReason_msg;

	public static String invalidLibraryFileError_msg;

	public static String fileNotFoundError_msg;

	public static String normalizeURIError_msg;

	public static String moveError_msg;

	public static String modifyReadOnlyFileError_msg;

	public static String moveResourceError_msg;

	public static String modifyFilesError_msg;

	public static String resourceOutOfSynch_msg;

	public static String loadLibraryTask_name;

	public static String moveDataTask_name;

	public static String saveLibraryTask_name;

	public static String loadResourcesTask_name;
	
	public static String migratingDiagram_name;

	public static String migrateContentDescriptionsTask_name;

	public static String fixPresentationNameTask_name;

	public static String unresolvedProxyLoggerJob_name;

	public static String UnresolvedProxyMarkerManager_couldNotResolveProxy;

	public static String loadConfiguration_couldNotLoad_logMsg;

	public static String loadConfiguration_notConfigFile_logMsg;

	static {
		NLS.initializeMessages(BUNDLE_NAME, PersistenceResources.class);
	}
}
