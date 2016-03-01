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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.epf.common.utils.ExtensionHelper;
import org.eclipse.epf.export.ExportPlugin;
import org.eclipse.epf.export.ExportResources;
import org.eclipse.epf.library.IConfigurationClosure;
import org.eclipse.epf.library.ILibraryResourceManager;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.LibraryServiceUtil;
import org.eclipse.epf.library.configuration.closure.ConfigurationClosure;
import org.eclipse.epf.library.edit.util.MethodElementUtil;
import org.eclipse.epf.library.layout.LayoutResources;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.persistence.refresh.RefreshJob;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPlugin;

/**
 * Exports a library configuration.
 * 
 * @author Jinhua Xi
 * @author Weiping Lu
 * @since 1.0
 */
public class ConfigurationExportService {

	public static String excludes = ".copyarea.dat, **/.copyarea.dat, .copyarea.db, **/.copyarea.db"; //$NON-NLS-1$
	private ConfigurationExportData data;
	protected File exportLibFolder;

	/**
	 * Creates a new instance.
	 */
	public ConfigurationExportService(ConfigurationExportData data) {
		this.data = data;
	}
	
	public static ConfigurationExportService newInstance(ConfigurationExportData data) {
		Object obj = ExtensionHelper.create(ConfigurationExportService.class, data);
		if (obj instanceof ConfigurationExportService) {
			return (ConfigurationExportService) obj;
		}		
		return new ConfigurationExportService(data);
	}
	
	/**
	 * Run to export a configuration closure
	 */
	public void run(IProgressMonitor monitor) {
		try {
			if (data.selectedConfigs == null
					|| data.selectedConfigs.size() == 0) {
				return;
			}
			monitor.setTaskName(ExportResources.ConfigurationExportService_MSG0); 
			String exportLibPath = data.llData.getParentFolder();
			exportLibFolder = new File(exportLibPath);
			if (!exportLibFolder.exists()) {
				exportLibFolder.mkdir();
			}
			MethodConfiguration config = (MethodConfiguration) data.selectedConfigs
					.get(0);
			exportConfig(config.getName(), exportLibFolder.getAbsolutePath(),
					monitor);
		} catch (Exception e) {
			ExportPlugin.getDefault().getLogger().logError(e);
		}
	}

	/**
	 * Export a configuration closure
	 */
	public void exportConfig(String selectedConfigName, String filePath,
			IProgressMonitor monitor) throws Exception {

		// need to disable the workspace refreshing
		boolean refresh = RefreshJob.getInstance().isEnabled();
		try {
			if (refresh) {
				// disable resource refreshing during import
				//
				RefreshJob.getInstance().setEnabled(false);
			}
			doEexportConfig(selectedConfigName, filePath, monitor);
		} finally {
			if (refresh) {
				// re-enable resource refreshing
				//
				RefreshJob.getInstance().setEnabled(true);
			}
		}
	}

	private void doEexportConfig(String selectedConfigName, String filePath,
			IProgressMonitor monitor) throws Exception {

		MethodLibrary currentLib = LibraryService.getInstance()
				.getCurrentMethodLibrary();

		try {
			// Load the whole Method Library.
			LibraryUtil.loadAll(currentLib);
		} catch (Throwable e) {
			ExportPlugin.getDefault().getLogger().logError(
					"Error loading library", e); //$NON-NLS-1$
			data.errorMsg = ExportResources.ConfigurationExportService_MSG2; 
			return;
		}

		// Copy the current library to a new library,
		// since we need to make changes when packaging the new library.
		MethodLibrary newLibrary = null;
		try {
			newLibrary = (MethodLibrary) EcoreUtil.copy(currentLib);
		} catch (Throwable e) {
			ExportPlugin.getDefault().getLogger().logError(e);
			data.errorMsg = ExportResources.ConfigurationExportService_MSG4; 
			return;
		}

		IConfigurationClosure closure = null;
		try {
			// Detach the new library from the current resource so it can be
			// added to a new Library Processor instance.
			LibraryUtil.detachFromResource(newLibrary);

			// Create a new Library Manager and create a new resource for the
			// new library.
			// TODO: Update the UI to prompt for the method library type.
			Map params = new HashMap();
			params.put("library.path", filePath); 	//$NON-NLS-1$
			params.put("libraryRegisterType", "ConfigExport"); 		//$NON-NLS-1$		//$NON-NLS-2$		
			
			LibraryService.getInstance().registerMethodLibrary(newLibrary, "xmi", params);		//$NON-NLS-1$
			
			// Begin packaging the new library for export.
			MethodConfiguration config = LibraryServiceUtil
					.getMethodConfiguration(newLibrary, selectedConfigName);

			// Validate the configuration and make sure the global packages are
			// selected. If global packages are missing, the exported library
			// can't be loaded.
			LibraryUtil.validateMethodConfiguration(null, config);

			closure = new ConfigurationClosure(null, config);
			closure.packageLibrary(data.removeBrokenReferences);
		} catch (Throwable e) {
			ExportPlugin.getDefault().getLogger().logError(
					"Error making library configuration closure", e); //$NON-NLS-1$
			data.errorMsg = ExportResources.ConfigurationExportService_MSG1; 

			if (closure != null)
				closure.dispose();
			
			LibraryService.getInstance().unRegisterMethodLibrary(newLibrary);
			
			return;
		}

		try {
			LibraryUtil.saveAll(newLibrary);

			// the first round of save will create the resource structure,
			// need to save again to make sure all references are saved.
			// This step is critical since the first round only created the data
			// structure
			// some of the cross-referenced elements might be lost on the first
			// saving
			// for example, when create a method configuration with references
			// to a new plugin,
			// which are not saved yet, those references will be lost.
			// 145891 - Import Configuration: default config is loss after
			// import
			LibraryUtil.saveAll(newLibrary);

		} catch (Throwable e) {
			ExportPlugin.getDefault().getLogger().logError(
					"Error saving library", e); //$NON-NLS-1$
			data.errorMsg = ExportResources.ConfigurationExportService_MSG9; 

			if (closure != null)
				closure.dispose();

			LibraryService.getInstance().unRegisterMethodLibrary(newLibrary);

			return;
		}

		// Copy the resource files in the current library to the new library.
		// For simplicity sake, copy all resource files if the files do not
		// exist in the target library or if the files are newer
		//String includes = "resources/**, **/resources/**"; //$NON-NLS-1$
		String includes = "resources/**, **/resources/**, **/diagram.xmi"; //$NON-NLS-1$

		File srcDir = LibraryUtil.getLibraryRootPath(currentLib);
		File destDir = LibraryUtil.getLibraryRootPath(newLibrary);

		LayoutResources.copyDir(srcDir, destDir, includes, excludes);
		handleExtraResourceCopy(currentLib, newLibrary, srcDir, destDir, includes);

		// Close the newly created library.
		if (closure != null)
			closure.dispose();
		
		LibraryService.getInstance().unRegisterMethodLibrary(newLibrary);

	}
	
	private void handleExtraResourceCopy(MethodLibrary baseLib, MethodLibrary exportedLib, File baseLibDir, File exportedLibDir, String includes) {
		ILibraryResourceManager libResMgr = LibraryService.getInstance().getCurrentLibraryManager().getResourceManager();
		Map exportedPluginMap = MethodElementUtil.buildMap(baseLib.getMethodPlugins());
		
		List<MethodPlugin> basePlugins = baseLib.getMethodPlugins();
		for (int i = 0; i < basePlugins.size(); i++) {
			MethodPlugin plugin = basePlugins.get(i);
			File pluginFolder = new File(libResMgr.getPhysicalPath(plugin));
			boolean processed = baseLibDir.equals(pluginFolder.getParentFile());			
			//System.out.println("LD pluginFolder: " + pluginFolder + ", processed: " + processed);
			if (! processed) {
				MethodPlugin exportedPlugin = (MethodPlugin) exportedPluginMap.get(plugin.getGuid());
				if (exportedPlugin == null) {
					continue;
				}
				File destDir = new File(exportedLibDir, exportedPlugin.getName());
				LayoutResources.copyDir(pluginFolder, destDir, includes, excludes);
			}

		}		
	}

}