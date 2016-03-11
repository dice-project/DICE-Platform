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
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Copy;
import org.apache.tools.ant.types.FileSet;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.epf.common.utils.ExtensionHelper;
import org.eclipse.epf.common.utils.FileUtil;
import org.eclipse.epf.export.ExportResources;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.persistence.MultiFileSaveUtil;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPackage;
import org.eclipse.epf.uma.MethodPlugin;

/**
 * Exports one or more method plug-ins to a target directory.
 * 
 * @author Jinhua Xi
 * @author Kelvin Low
 * @since 1.0
 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=162153
 */
public class PluginExportService extends BaseExportService {

	PluginExportData data;
	protected File exportLibFolder;

	/**
	 * Creates a new instance.
	 */
	public PluginExportService(PluginExportData data) {
		this.data = data;
	}

	public static PluginExportService newInstance(PluginExportData data) {
		Object obj = ExtensionHelper.create(PluginExportService.class, data);
		if (obj instanceof PluginExportService) {
			return (PluginExportService) obj;
		}		
		return new PluginExportService(data);
	}
	
	/**
	 * Run to export plugins
	 */
	public void run(IProgressMonitor monitor) {
		monitor.setTaskName(ExportResources.PluginExportService_MSG0); 

		Collection<MethodPlugin> plugins = data.selectedPlugins;
		if (plugins == null || plugins.size() == 0) {
			return;
		}

		monitor.setTaskName(ExportResources.PluginExportService_MSG1); 

		// Create the library folder in the target directory.
		String exportLibPath = (new File(data.llData.getParentFolder())).getAbsolutePath();
		exportLibFolder = new File(exportLibPath);
		if (!exportLibFolder.exists()) {
			exportLibFolder.mkdir();
		}

		monitor.setTaskName(ExportResources.PluginExportService_MSG2); 

		MethodLibrary currLib = LibraryService.getInstance().getCurrentMethodLibrary();
		String currLibPath = currLib.eResource().getURI().toFileString();
		File currLibFile = new File(currLibPath);
		
		// Iterate the selected plug-ins and copy their associated model files.
		for (Iterator it = plugins.iterator(); it.hasNext();) {
			MethodPlugin plugin = (MethodPlugin) it.next();
			if (plugin == null) {
				continue;
			}

			// Step 1: Create the plug-in folder.
			URI uri = plugin.eResource().getURI();
			String path = uri.toFileString();

			File pluginFolder = (new File(path)).getParentFile();

			String pluginPath = plugin.getName() + File.separator;
			File exportPluginFolder = new File(exportLibFolder, pluginPath);

			// Step 2: Copy the plug-in files.
			copyDir(pluginFolder, exportPluginFolder);			
		}

		monitor.setTaskName(ExportResources.PluginExportService_MSG3); 

		// Get the selected plug-in and configuration ids so we can remove the
		// unneeded stuff.
		getSelectedIds(data.selectedPlugins, data.associatedConfigMap);

		// Step 3: Load the library.xmi and the resmgr.xmi, remove the unneeded
		// stuff.
		File libFolder = new File(LibraryService.getInstance().getCurrentMethodLibraryLocation());
		LibraryDocument document = processLibraryFile(currLibFile, new File(
				exportLibFolder, exportFile));

		if ( document != null ) {
			// copy the conifuration files
			// 142379 - update plugin importing and exporting to match with the new file format
			copyConfigurationFiles(document, libFolder, exportLibFolder);
		}
		
		monitor.setTaskName(ExportResources.PluginExportService_MSG4); 
	}

	
	private void copyConfigurationFiles(LibraryDocument document, File libFolder, File exportLibFolder) {
		// // 142379 - update plugin importing and exporting to match with the new file format
		// Get the selected configuration ids.
		for (Iterator itc = data.associatedConfigMap.values().iterator(); itc.hasNext();) {
			List configs = (List) itc.next();
			if (configs == null || configs.size() == 0) {
				continue;
			}

			for (Iterator it = configs.iterator(); it.hasNext();) {
				MethodConfiguration config = (MethodConfiguration) it.next();
				String guid = config.getGuid();
				String uri = document.getResourceUri(guid);
				File src = null;
				if (uri == null) {
					//if a process refers to a config that does not exist
					//then there would be no resource for the config
					if (config.eResource() == null) {
						continue;
					}
					URI resUri = config.eResource().getURI();
					uri = MultiFileSaveUtil.METHOD_CONFIGURATION_FOLDER_NAME + File.separator + resUri.lastSegment();
					uri = document.decodeUri(uri);
					src = new File(resUri.toFileString());
				}				
				if ( uri != null ) {
					//File src = new File(libFolder, uri);
					if (src == null) {
						src = new File(libFolder, uri);
					}
					File target =  new File(exportLibFolder, uri);
					FileUtil.copyFile(src, target);
				}
			}
		}
	}

	/**
	 * Copies directories from "fromDir" to "toDir".
	 */
	public static void copyDir(File fromDir, File toDir) {
		Copy cp = new Copy();
		cp.setOverwrite(true);
		FileSet set = new FileSet();
		set.setExcludes(ConfigurationExportService.excludes);
		set.setDir(fromDir);
		cp.addFileset(set);
		cp.setTodir(toDir);
		cp.setProject(new Project());
		cp.setPreserveLastModified(true);
		cp.execute();
	}

	private void getSelectedIds(Collection<MethodPlugin> plugins, Map configsMap) {
		selectedIds.clear();
		selectedPluginNames.clear();

		// Get the plug-in ids and names.
		MethodPlugin plugin = null;
		for (Iterator it = plugins.iterator(); it.hasNext();) {
			plugin = (MethodPlugin) it.next();
			String guid = plugin.getGuid();
			selectedPluginNames.add(plugin.getName());
			if (!selectedIds.contains(guid)) {
				selectedIds.add(guid);
			}
		}

		// Save the library guid so that the entry in resgr.xmi will be kept.
		if (plugin != null) {
			MethodLibrary lib = (MethodLibrary) plugin.eContainer();
			if (lib != null) {
				selectedIds.add(lib.getGuid());
			}
		}

		// Get the selected configuration ids.
		for (Iterator itc = configsMap.values().iterator(); itc.hasNext();) {
			List configs = (List) itc.next();
			if (configs == null || configs.size() == 0) {
				continue;
			}

			for (Iterator it = configs.iterator(); it.hasNext();) {
				MethodConfiguration config = (MethodConfiguration) it.next();
				String guid = config.getGuid();
				if (selectedIds.contains(guid)) {
					continue;
				}

				// Add the configuration.
				selectedIds.add(guid);

				// For each configuration, save the method plug-in and package
				// ids so that their entries in the resource file can be kept.
				List refs = config.eCrossReferences();
				for (Iterator itr = refs.iterator(); itr.hasNext();) {
					Object o = itr.next();
					if (o instanceof MethodPlugin || o instanceof MethodPackage) {
						guid = ((MethodElement) o).getGuid();
						if (!selectedIds.contains(guid)) {
							selectedIds.add(guid);
						}
					} else {
						System.out
								.println("Unexpected element type '" + ((MethodElement) o).getType().getName() //$NON-NLS-1$
										+ "' in configuration '" + config.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$
					}
				}
			}
		}
	}

}
