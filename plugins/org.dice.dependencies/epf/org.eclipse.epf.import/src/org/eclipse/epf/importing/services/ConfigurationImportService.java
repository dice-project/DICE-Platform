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
package org.eclipse.epf.importing.services;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.epf.common.service.versioning.VersionUtil;
import org.eclipse.epf.common.ui.util.MsgBox;
import org.eclipse.epf.common.utils.ExtensionHelper;
import org.eclipse.epf.common.utils.FileUtil;
import org.eclipse.epf.export.services.LibraryDocument;
import org.eclipse.epf.export.services.PluginExportService;
import org.eclipse.epf.importing.ImportPlugin;
import org.eclipse.epf.importing.ImportResources;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.project.MethodLibraryProject;
import org.eclipse.epf.library.services.SafeUpdateController;
import org.eclipse.epf.library.ui.util.ConvertGuidanceType;
import org.eclipse.epf.library.ui.util.TypeConverter;
import org.eclipse.epf.library.ui.wizards.OpenLibraryWizard;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.library.util.ResourceUtil;
import org.eclipse.epf.persistence.migration.UpgradeCallerInfo;
import org.eclipse.epf.persistence.refresh.RefreshJob;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Guidance;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Display;

import com.ibm.icu.util.Calendar;


/**
 * Imports a library configuration.
 * 
 * @author Jinhua Xi
 * @since 1.0
 */
public class ConfigurationImportService {

	private boolean localDebug = false;
	
	private UpgradeCallerInfo upGradeInfo;
	
	protected ConfigurationImportData data;

	protected LibraryDocument importingLibDoc = null;

	LibraryDiffManager diffMgr = null;

	ConfigSpecsImportManager specsMgr = null;
	
	/**
	 * Creates a new instance.
	 */
	public ConfigurationImportService(ConfigurationImportData data) {
		this.data = data;
	}

	public static ConfigurationImportService newInstance(ConfigurationImportData data) {
		Object obj = ExtensionHelper.create(ConfigurationImportService.class, data);
		if (obj instanceof ConfigurationImportService) {
			return (ConfigurationImportService) obj;
		}		
		return new ConfigurationImportService(data);
	}
	
	protected LibraryDocument getImportingLibDoc() {
		return importingLibDoc;
	}
	
	/**
	 * Analyzes the imported library with respect to the base library.
	 */
	public void analyze(IProgressMonitor monitor) {
		try {
			if (monitor != null) {
				monitor.setTaskName(ImportResources.ConfigurationImportService_MSG0);
			}

			data.getErrorInfo().clear();

			// Prepare the library files.
			String path = data.llData.getParentFolder();
			if (path.indexOf(File.separator + LibraryDocument.libraryFile) < 0) {
				path += File.separator + LibraryDocument.libraryFile;
			}
			File importingLibPath = new File(path);
			
			boolean isLibraryFile = true;
			if (!importingLibPath.exists()) {
				importingLibPath = new File(importingLibPath.getParentFile(),
						LibraryDocument.exportFile);
				isLibraryFile = false;
			}

			if (!importingLibPath.exists()) {
				data
						.getErrorInfo()
						.addError(
								NLS.bind(ImportResources.ConfigurationImportService_MSG1, importingLibPath.getParent())); 
				return;
			}

			boolean handleVersion = isLibraryFile;
			if (handleVersion) {
				upGradeInfo = new ConfigurationImportService.UpgradeInfo(UpgradeCallerInfo.upgradeImportConfig, importingLibPath);
				if (! handleToolVersion(importingLibPath, upGradeInfo)) {
					String errMsg = upGradeInfo.getErrorMsg();
					if (errMsg == null || errMsg.length() == 0) {
						errMsg = ImportResources.ImportConfigurationWizard_ERR_Import_configuration;
					}
					data
					.getErrorInfo()
					.addError(
							NLS.bind(errMsg, importingLibPath.getParent())); 
					return;
				}
				if (upGradeInfo.getCopiedLibFile() != null) {
					importingLibPath = upGradeInfo.getCopiedLibFile();
				}
			}
						
			importingLibDoc = new LibraryDocument(importingLibPath);

			boolean isConfigSpecs = importingLibDoc.isConfigSpecsOnly();

			if (isConfigSpecs) {

				specsMgr = new ConfigSpecsImportManager();

				// Scan the library file for configuration information.
				data.specs = specsMgr.getConfigSpecs(importingLibDoc);

			} else {
				if (!isLibraryFile) {
					data
					.getErrorInfo()
					.addError(
							NLS.bind(ImportResources.ConfigurationImportService_MSG1, importingLibPath.getParent())); 
					return;
				}
				data.specs = null;

				// Open the library and compare the difference.
				
				// need to open and close the library to have the project resources initialized properly
				String libDir = importingLibPath.getParentFile().getAbsolutePath();

				String projectName = "Configuration Import Project (" //$NON-NLS-1$
						+ Integer.toHexString(libDir.hashCode()) + ")"; //$NON-NLS-1$
				
				MethodLibraryProject.openProject(libDir, projectName, monitor);
				try {
					MethodLibrary importLibraty = LibraryUtil
							.loadLibrary(importingLibPath.getAbsolutePath());
					MethodLibrary baseLibrary = LibraryService.getInstance()
							.getCurrentMethodLibrary();
					
					handleTypeChanges(baseLibrary, importLibraty);
					
					String baseLibDir = null;
					try {	//Not neccessary, but prevent introducing any potential regression due to this change
						File bFile = new File(baseLibrary.eResource().getURI().toFileString()).getParentFile();;
						baseLibDir = bFile.getAbsolutePath();
					} catch (Throwable e) {						
					}
					if (libDir.equalsIgnoreCase(baseLibDir)) {
						data
								.getErrorInfo()
								.addError(
										NLS.bind(ImportResources.ConfigurationImportService_MSG2, importingLibPath.getParent())); 
						return;
					}
					
					fixImportLibrarySystemPackageGUIDs(baseLibrary, importLibraty);
					
					diffMgr = new LibraryDiffManager(baseLibrary, importLibraty);
					diffMgr.buildDiffTree();
					
					if (localDebug) {
						diffMgr.rootDiffTree.debugDump();
					}
					
				} catch (RuntimeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				MethodLibraryProject.closeProject(libDir, monitor);
				MethodLibraryProject.deleteProject(libDir, monitor);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * Returns the import data.
	 */
	public ConfigurationImportData getImportData() {
		return data;
	}

	/**
	 * Returns spec only attribute.
	 */
	public boolean isSpecsOnly() {
		return (data.specs != null);
	}

	/**
	 * Returns the diff tree.
	 */
	public ElementDiffTree getDiffTree() {
		return diffMgr.getDiffTree();
	}

	/**
	 * Returns the import library.
	 */
	public MethodLibrary getImportingLibrary() {
		return diffMgr.getImportingLibrary();
	}

	/**
	 * Performs the import.
	 */
	public void performImport(final IProgressMonitor monitor) {
		
		// need to disable the workspace refreshing
		boolean refresh = RefreshJob.getInstance().isEnabled();
		if(refresh) {
			// disable resource refreshing during import
			//
			RefreshJob.getInstance().setEnabled(false);
		}
		
		try {
			if (monitor != null) {
				monitor.setTaskName(ImportResources.ConfigurationImportService_MSG3);
			}

			if (isSpecsOnly()) {
				specsMgr.doImport(data.specs);
			} else {
				LibraryImportManager importingMgr = new LibraryImportManager(diffMgr, data.importList);			
				importingMgr.doMerge(data.replaceExisting, monitor);
			}
			
			// refresh library files in workspace
			//
			MethodLibrary lib = LibraryService.getInstance().getCurrentMethodLibrary();
			ResourceUtil.refreshResources(lib, monitor);
			
		} catch (Exception e) {
			ImportPlugin.getDefault().getLogger().logError(e);
		} finally {
			if(refresh) {
				// re-enable resource refreshing 
				//
				RefreshJob.getInstance().setEnabled(true);
			}
			if (upGradeInfo != null) {
				upGradeInfo.removeCopiedLibrary();
				upGradeInfo = null;
			}
		}	
		
		try {
			postImportOperation(monitor);
		} catch (Exception e) {
			ImportPlugin.getDefault().getLogger().logError(e);
		}
	}
	
	protected void postImportOperation(IProgressMonitor monitor) throws Exception {
		// Reopen the library.
		LibraryService.getInstance().reopenCurrentMethodLibrary();	
	}
	
	/**
	 * Checks the tool version.
	 */
	public static String versionCheck(final String xmlPath, final String title) {
		final String[] ret = new String[1];
		ret[0] = null;
		SafeUpdateController.syncExec(new Runnable() {	
			public void run() {				
				VersionUtil.VersionCheckInfo info = VersionUtil.checkLibraryVersion(new File(xmlPath));
				if (info == null) {
					ret[0] = NLS.bind(ImportResources.versionMismatch_oldData_unknown, new Object[] {
							Platform.getProduct().getName()});					
				} else {
					if (info.result < 0) {
						if (info.toolID.equals(VersionUtil.getPrimaryToolID())) {
							ret[0] = NLS.bind(ImportResources.versionMismatch_oldData, new Object[] {
									info.toolVersion, Platform.getProduct().getName()});
						} else {
							ret[0] = NLS.bind(ImportResources.versionMismatch_oldData_unknown, new Object[] {
									Platform.getProduct().getName()});					
						}
					} else if (info.result > 0) {
						if (info.toolID.equals(VersionUtil.getPrimaryToolID())) {
							ret[0] = NLS.bind(ImportResources.versionMismatch_oldTool, new Object[] {
									info.toolVersion, Platform.getProduct().getName()});
						} else {
							ret[0] = NLS.bind(ImportResources.versionMismatch_oldTool_unknown, new Object[] {
									Platform.getProduct().getName()});
						}
					} 
				}
				if (ret[0] != null) {
					ImportPlugin.getDefault().getMsgDialog().displayError(title, ret[0]);					
				}
			}
		});		
		return ret[0];
	}
	
	/**
	 * Fixes the imported library's system package guids with those base's.
	 */
	public static void fixImportLibrarySystemPackageGUIDs(MethodLibrary baseLibrary, MethodLibrary importLibraty) {
		HashMap pluginsMap = new HashMap();
		List plugins = baseLibrary.getMethodPlugins();
		for (int i=0; i < plugins.size(); i++) {
			MethodPlugin plugin = (MethodPlugin) plugins.get(i);
			pluginsMap.put(plugin.getGuid(), plugin);
		}
		if (pluginsMap.isEmpty()) {
			return;
		}
		
		List importPluginsToFix = new ArrayList();
		List importPlugins = importLibraty.getMethodPlugins();
		for (int i=0; i < importPlugins.size(); i++) {
			MethodPlugin plugin = (MethodPlugin) importPlugins.get(i);
			if (pluginsMap.containsKey(plugin.getGuid())) {
				importPluginsToFix.add(plugin);
			}
		}
		
		for (int i=0; i < importPluginsToFix.size(); i++) {
			MethodPlugin importPlugin = (MethodPlugin) importPluginsToFix.get(i);
			MethodPlugin basePlugin = (MethodPlugin) pluginsMap.get(importPlugin.getGuid());
			if (basePlugin == null) {
				continue;
			}
			List importPackages = TngUtil.getAllSystemPackages(importPlugin);
			HashMap importPackageMap = new HashMap();
			for (int j=0; j < importPackages.size(); j++) {
				MethodElement importPackage = (MethodElement) importPackages.get(j);
				importPackageMap.put(importPackage.getName(), importPackage);
			}
			
			List basePackages = TngUtil.getAllSystemPackages(basePlugin);			
			for (int j=0; j < basePackages.size(); j++) {
				MethodElement basePackage = (MethodElement) basePackages.get(j);
				MethodElement importPackage = (MethodElement) importPackageMap.get(basePackage.getName());
				if (importPackage == null) {
					continue;
				}
				String guid = basePackage.getGuid();
				if (! importPackage.getGuid().equals(guid)) {
					importPackage.setGuid(guid);
				}
			}
		}			
	}
	
	private void handleTypeChanges(MethodLibrary baseLib, MethodLibrary importLib) {
		HashMap baseMap = new HashMap();
		collectPotentialTypeChanged(baseLib, baseMap);
		HashMap importMap = new HashMap();
		collectPotentialTypeChanged(importLib, importMap);
		handleTypeChanges(baseMap, importMap);
	}

	/**
	 * Handles MethodElement type changes. Note: for MethodElement objects in importMap
	 * only eClass type is used in this method.
	 */
	public static void handleTypeChanges(HashMap baseMap, HashMap importMap) {
		ArrayList toChangeList = new ArrayList();
		for (Iterator it = importMap.entrySet().iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			Object guid = entry.getKey();
			MethodElement baseElem = (MethodElement) baseMap.get(guid);
			if (baseElem != null) {
				MethodElement elem = (MethodElement) entry.getValue();
				if (! elem.eClass().equals(baseElem.eClass())) {
					MethodElement elemPair[] = new MethodElement[] {baseElem, elem};
					toChangeList.add(elemPair);
				}
			}			
		}
		for (int i=0; i<toChangeList.size(); i++) {
			MethodElement elemPair[] = (MethodElement[]) toChangeList.get(i);
			final MethodElement baseElem = elemPair[0];
			final MethodElement importElem = elemPair[1];
			if (baseElem instanceof Guidance) {
				Display.getDefault().syncExec(new Runnable() {
					public void run() {
						ConvertGuidanceType.convertGuidance((Guidance) baseElem, MsgBox.getDefaultShell(), null, importElem.eClass());
					}
				});
			} else if (baseElem instanceof Activity) {
				TypeConverter.convertActivity((Activity) baseElem, importElem.eClass()); 
			}
		}
	}

	/**
	 * Collects elements in lib that may have type changes 
	 */
	public static void collectPotentialTypeChanged(MethodLibrary lib, HashMap map) {
		for (Iterator it = lib.eAllContents(); it.hasNext();) {
			Object obj = it.next();
			if (obj instanceof Guidance ||
				obj instanceof Activity) {
				MethodElement elem = (MethodElement) obj;
				map.put(elem.getGuid(), elem);
			}
		}
	}
	
	public static boolean handleToolVersion(File libFile, final UpgradeCallerInfo info) {
		final String libFolderPath = libFile.getParentFile().getAbsolutePath();
		final boolean ret[] = new boolean[1];
		SafeUpdateController.syncExec(new Runnable() {	
			public void run() {
				ret[0] = OpenLibraryWizard.handleToolVersion(libFolderPath, info);
			}
		});								
		return ret[0];
	}	

	public static class UpgradeInfo extends UpgradeCallerInfo {
		
		public UpgradeInfo(int callerType, File libFile) {
			super(callerType, libFile);
		}
		
		public void copyLibrary() {
			String userHome = System.getProperty("user.home"); //$NON-NLS-1$
			String desLibFolderPath = userHome + File.separator
					+ "EPF" + File.separator + "Export" + File.separator //$NON-NLS-1$ //$NON-NLS-2$
					+ Long.toHexString(Calendar.getInstance().getTimeInMillis()) + File.separator;		
			File desLibFolder = new File(desLibFolderPath);
			if (!desLibFolder.exists()) {
				desLibFolder.mkdirs();
			} else {
				FileUtil.deleteAllFiles(desLibFolder.getAbsolutePath());
			}
			
			PluginExportService.copyDir(getLibFile().getParentFile(), desLibFolder);
			setCopiedLibFile(new File(desLibFolderPath + getLibFile().getName()));
		}
		
		public void removeCopiedLibrary() {
			if (getCopiedLibFile() == null) {
				return;
			}
			FileUtil.deleteAllFiles(getCopiedLibFile().getParentFile().getAbsolutePath());
			getCopiedLibFile().getParentFile().delete();
			setCopiedLibFile(null);
		}
	};	
	
	public boolean isSynFreeLib() {
		return importingLibDoc.isSynFreeLib();
	}
	
}