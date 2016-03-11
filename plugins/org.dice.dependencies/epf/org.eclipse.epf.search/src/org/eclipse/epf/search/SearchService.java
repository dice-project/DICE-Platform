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
package org.eclipse.epf.search;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.epf.common.utils.FileUtil;
import org.eclipse.epf.common.utils.WarUtil;
import org.eclipse.epf.search.configuration.ConfigurationHitEntry;
import org.eclipse.epf.search.configuration.ConfigurationSearchQuery;
import org.eclipse.epf.search.configuration.internal.ConfigurationSearchService;

/**
 * The Search Service implementation.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class SearchService implements ISearchService {

	// The shared instance.
	private static SearchService instance = null;

	/**
	 * Returns the shared instance.
	 */
	public static SearchService getInstance() {
		if (instance == null) {
			synchronized (SearchService.class) {
				if (instance == null) {
					instance = new SearchService();
				}
			}
		}
		return instance;
	}

	/**
	 * Private constructor to prevent this class from being instantiated.
	 */
	private SearchService() {
	}

	/**
	 * Searches a published Configuration.
	 * 
	 * @param path
	 *            the absolute path to the published configuration
	 * @param searchQuery
	 *            the configuration search query
	 * @return an array of <code>ConfigurationHitEntry</code> objects
	 * @throws SearchServiceException
	 *             if an error occurs while executing the operation
	 */
	public ConfigurationHitEntry[] searchConfiguration(String path,
			ConfigurationSearchQuery searchQuery) throws SearchServiceException {
		ConfigurationSearchService service = new ConfigurationSearchService(
				path);
		service.index();
		return service.search(searchQuery);
	}

	public boolean createIndex(String publishDir) throws SearchServiceException {
		IndexBuilder service = new IndexBuilder(publishDir);
		return service.createIndex(true);
	}

	public boolean createIndex(String publishDir, boolean jarIt)
			throws SearchServiceException {
		IndexBuilder service = new IndexBuilder(publishDir);
		return service.createIndex(jarIt);
	}

	public void createWAR(String publishDir, String webAppName) {
		try {
			File warFile = null;
			if (webAppName.endsWith(".war")) { //$NON-NLS-1$
				warFile = new File(publishDir, webAppName);
			} else {
				warFile = new File(publishDir, webAppName + ".war"); //$NON-NLS-1$
			}
			WarUtil.buildWarFile(warFile.getAbsolutePath(), publishDir, null);
			File logsDir = new File(publishDir, "logs"); //$NON-NLS-1$
			File cssDir = new File(publishDir, "css"); //$NON-NLS-1$
			File scriptsDir = new File(publishDir, "scripts"); //$NON-NLS-1$
			File imagesDir = new File(publishDir, "images"); //$NON-NLS-1$
			List<File> filesNotToDelete = new ArrayList<File>();
			filesNotToDelete.add(logsDir);
			filesNotToDelete.add(cssDir);
			filesNotToDelete.add(scriptsDir);
			filesNotToDelete.add(imagesDir);
			filesNotToDelete.add(warFile);
			FileUtil.deleteAllFiles(publishDir, filesNotToDelete);
			filesNotToDelete.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
