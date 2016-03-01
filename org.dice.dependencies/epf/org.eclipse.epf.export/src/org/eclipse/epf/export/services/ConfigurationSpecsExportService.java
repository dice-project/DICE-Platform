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
package org.eclipse.epf.export.services;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.epf.common.utils.FileUtil;
import org.eclipse.epf.export.ExportPlugin;
import org.eclipse.epf.export.ExportResources;
import org.eclipse.epf.persistence.MultiFileSaveUtil;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPackage;
import org.eclipse.epf.uma.MethodPlugin;


/**
 * Exports a library configuration specification.
 * 
 * @author Jinhua Xi
 * @since 1.0
 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=162153
 */
public class ConfigurationSpecsExportService extends BaseExportService {

	private ConfigurationExportData data;

	/**
	 * Creates a new instance.
	 */
	public ConfigurationSpecsExportService(ConfigurationExportData data) {
		this.data = data;
	}

	/**
	 * Run to export a configuration specificaiton.
	 */
	public void run(IProgressMonitor monitor) {
		if (data.selectedConfigs == null || data.selectedConfigs.size() == 0) {
			return;
		}

		monitor.setTaskName(ExportResources.ConfigurationSpecsExportService_MSG0); 

		// Create the export library folder.
		File libFolder;
		File exportLibFolder;

		String exportLibPath = (new File(data.llData.getParentFolder())).getAbsolutePath();
		exportLibFolder = new File(exportLibPath);
		if (!exportLibFolder.exists()) {
			exportLibFolder.mkdir();
		}

		MethodConfiguration config = (MethodConfiguration) data.selectedConfigs
				.get(0);
		MethodLibrary lib = (MethodLibrary) config.eContainer();
		URI uri = lib.eResource().getURI();
		String path = uri.toFileString();
		libFolder = (new File(path)).getParentFile();

		// Prepare the id selections,
		getSelectedIds(data.selectedConfigs);

		// Load the library.xmi and remove unneeded elements.
		LibraryDocument document = processLibraryFile(new File(libFolder, libraryFile), new File(
				exportLibFolder, exportFile));

		if ( document != null ) {
			// copy the conifuration files
			// 143033 - update config specs' importing and exporting to match with the new file format
			copyConfigurationFiles(document, libFolder, exportLibFolder);
		}
		
		monitor.setTaskName(ExportResources.ConfigurationSpecsExportService_MSG1); 

	}

	private void copyConfigurationFiles(LibraryDocument document, File libFolder, File exportLibFolder) {
		// 143033 - update config specs' importing and exporting to match with the new file format
		for (Iterator it = data.selectedConfigs.iterator(); it.hasNext();) {
			MethodConfiguration config = (MethodConfiguration) it.next();
			String guid = config.getGuid();
			String uri = document.getResourceUri(guid);
			String srcUri = null;
			if (uri == null) {
				URI resUri = config.eResource().getURI();
				srcUri = resUri.toFileString();
				uri = MultiFileSaveUtil.METHOD_CONFIGURATION_FOLDER_NAME + File.separator + resUri.lastSegment();
				uri = document.decodeUri(uri);
			}
			if ( uri != null ) {
				File src = srcUri == null ? new File(libFolder, uri) : new File(srcUri);
				File target =  new File(exportLibFolder, uri);
				FileUtil.copyFile(src, target);
			}
		}
	}
	
	private void getSelectedIds(List configs) {
		selectedIds.clear();
		selectedPluginNames.clear();

		// Get selected config ids.
		if (configs == null || configs.size() == 0) {
			return;
		}

		for (Iterator it = configs.iterator(); it.hasNext();) {
			MethodConfiguration config = (MethodConfiguration) it.next();
			String guid = config.getGuid();
			if (selectedIds.contains(guid)) {
				continue;
			}

			// Add the configuration.
			selectedIds.add(guid);

			// For each configuration, we need to save the method plugin ids and
			// method package ids so that their entries in the resource file can
			// be kept.
			List refs = config.eCrossReferences();
			for (Iterator itr = refs.iterator(); itr.hasNext();) {
				Object o = itr.next();
				if (o instanceof MethodPlugin || o instanceof MethodPackage) {
					guid = ((MethodElement) o).getGuid();
					if (!selectedIds.contains(guid)) {
						selectedIds.add(guid);
					}
				} else {
					ExportPlugin
							.getDefault()
							.getLogger()
							.logError(
									"Unexpected element type '" + ((MethodElement) o).getType().getName() //$NON-NLS-1$
											+ "' in configuration '" + config.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$
				}
			}
		}
	}
}
