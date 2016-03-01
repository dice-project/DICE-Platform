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
import java.io.FileFilter;
import java.io.IOException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Copy;
import org.apache.tools.ant.types.FileSet;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.epf.authoring.ui.views.LibraryView;
import org.eclipse.epf.common.ui.util.MsgDialog;
import org.eclipse.epf.common.utils.ExtensionHelper;
import org.eclipse.epf.common.utils.FileUtil;
import org.eclipse.epf.common.utils.XMLUtil;
import org.eclipse.epf.export.services.ConfigurationExportService;
import org.eclipse.epf.export.services.LibraryDocument;
import org.eclipse.epf.importing.ImportPlugin;
import org.eclipse.epf.importing.ImportResources;
import org.eclipse.epf.importing.services.PluginImportData.ConfiguarationInfo;
import org.eclipse.epf.importing.services.PluginImportData.PluginInfo;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.LibraryServiceUtil;
import org.eclipse.epf.library.edit.util.MethodElementUtil;
import org.eclipse.epf.library.edit.util.MethodPluginPropUtil;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.services.SafeUpdateController;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.library.util.ResourceUtil;
import org.eclipse.epf.persistence.MultiFileResourceSetImpl;
import org.eclipse.epf.persistence.MultiFileSaveUtil;
import org.eclipse.epf.persistence.MultiFileXMIResourceImpl;
import org.eclipse.epf.persistence.MultiFileXMISaveImpl;
import org.eclipse.epf.persistence.migration.UpgradeCallerInfo;
import org.eclipse.epf.persistence.refresh.RefreshJob;
import org.eclipse.epf.persistence.util.PersistenceUtil;
import org.eclipse.epf.resourcemanager.ResourceManager;
import org.eclipse.epf.services.IFileBasedLibraryPersister;
import org.eclipse.epf.services.ILibraryPersister;
import org.eclipse.epf.uma.ContentDescription;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.osgi.util.NLS;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Imports a method plug-in into the current library.
 * 
 * @author Jinhua Xi
 * @author Kelvin Low
 * @author Weiping Lu
 * @since 1.0
 */
public class PluginImportingService {

	private UpgradeCallerInfo upGradeInfo;
	
	//Temp flag indicating use of the new merge scheme
	private static boolean newMergeScheme = true; 
	
	private static boolean localDebug = false;
	
	protected PluginImportData data;
	
	private Object validateHookData;

	protected LibraryDocument importingLibDoc;
	LibraryDocument targetLibDoc;

	// Flag to indicate the file checkout status.
	IStatus fileCheckedOutStatus = null;
	
	private boolean checkBasePlugins = true;
	
	private File defaultConfigFolder;
	private Map<String, File> targetFileMap;

	/**
	 * Creates a new instance.
	 */
	public PluginImportingService(PluginImportData data) {
		this.data = data;
	}
	
	public static PluginImportingService newInstance(PluginImportData data) {
		Object obj = ExtensionHelper.create(PluginImportingService.class, data);
		if (obj instanceof PluginImportingService) {
			return (PluginImportingService) obj;
		}		
		return new PluginImportingService(data);
	}

	/**
	 * Validates the plug-in against the current library, collects all the
	 * informations including plug-in info, configuation info and error info.
	 * Call this method, then call getError(), getPlugins(), getConfigs().
	 */
	public void validate(IProgressMonitor monitor) {
		try {
			if (monitor != null) {
				monitor.setTaskName(ImportResources.PluginImportingService_MSG0); 
			}

			if (this.data == null) {
				return;
			}

			this.data.clear();

			// Prepare the lib files.
			File importingLibPath = new File(data.llData.getParentFolder()
					+ File.separator + LibraryDocument.exportFile);
			if (!importingLibPath.exists()) {
				data
						.getErrorInfo()
						.addError(
								NLS.bind(ImportResources.PluginImportingService_MSG1, importingLibPath.toString())); 
				return;
			}
			
			boolean handleVersion = true;
			if (handleVersion) {
				upGradeInfo = new ConfigurationImportService.UpgradeInfo(UpgradeCallerInfo.upgradeImportPlugin, importingLibPath);
				if (! ConfigurationImportService.handleToolVersion(importingLibPath, upGradeInfo)) {
					data
					.getErrorInfo()
					.addError(
							NLS.bind(ImportResources.importPluginsWizard_ERR_Import_plugin, importingLibPath.toString())); 
					return;
				}
				if (upGradeInfo.getCopiedLibFile() != null) {
					importingLibPath = upGradeInfo.getCopiedLibFile();
				}
			} else {
				String versionError = ConfigurationImportService.versionCheck(importingLibPath.getAbsolutePath(), 
						ImportResources.importPluginsWizard_title);
				if (versionError != null) {
					data.getErrorInfo().addError(versionError);
					return;
				}
			}
			
			validateHook(monitor, importingLibPath, validateHookData);

			importingLibDoc = new LibraryDocument(importingLibPath);
			
			File libFile = new File(LibraryService.getInstance()
					.getCurrentMethodLibrary().eResource().getURI()
					.toFileString());
			targetLibDoc = new LibraryDocument(libFile);

			scanLibraryFile(importingLibDoc);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	protected void validateHook(IProgressMonitor monitor, File importingLibPath, Object object) {
	}
	
	/**
	 * Performs import.
	 */
	public void performImport(IProgressMonitor monitor) throws Exception {

		// need to disable the workspace refreshing
		boolean refresh = RefreshJob.getInstance().isEnabled();
		try {
			if (refresh) {
				// disable resource refreshing during import
				//
				RefreshJob.getInstance().setEnabled(false);
			}

			__doImport(monitor);

		} finally {
			if (refresh) {
				// re-enable resource refreshing
				//
				RefreshJob.getInstance().setEnabled(true);
			}
			if (upGradeInfo != null) {
				upGradeInfo.removeCopiedLibrary();
				upGradeInfo = null;
			}
			defaultConfigFolder = null;
			targetFileMap = null;
		}
	}

	private void __doImport(IProgressMonitor monitor) {
		try {
			if (monitor != null) {
				monitor.setTaskName(ImportResources.PluginImportingService_MSG3); 
			}

			List unlockedPlugins = unlockPlugins();
			if ((fileCheckedOutStatus != null)
					&& !fileCheckedOutStatus.isOK()) {
				// log error
				displayCheckOutError();
				return;
			}

			// To import the plug-ins, we need to do the following:
			// 1. Delete the plug-ins in the current library if user specify
			// remove
			// 2. Copy the selected plugin files to the destination
			// 3. Update the library.xmi
			// 4. Reload the library
			
			File libFile = targetLibDoc.getFile();
			defaultConfigFolder = null;
			targetFileMap = new HashMap<String, File>();
			
			if (newMergeScheme) {			
				if (! merge(targetLibDoc)) {
					SafeUpdateController.syncExec(new Runnable() {
						public void run() {
							String title = ImportResources.importPluginsWizard_title;
							String msg = ImportResources.importPluginsWizard_ERR_Import_plugin; 
							new MsgDialog(ImportPlugin.getDefault())
									.displayError(title, msg);
						}
					});
					return;
				}
				
				
				boolean toSave = false;
				if (ProcessUtil.isSynFree() && ! isSynFreeLib()) {
					MethodPluginPropUtil propUtil = MethodPluginPropUtil.getMethodPluginPropUtil();
					MethodLibrary lib = LibraryService.getInstance().getCurrentMethodLibrary();
					for (MethodPlugin plugin : lib.getMethodPlugins()) {
						if (! propUtil.isSynFree(plugin)) {
							propUtil.setSynFree(plugin, true);
						}
					}					
					toSave = true;
				}
								
				if (unlockedPlugins.size() > 0) {
					lockUnlockedPlugins(unlockedPlugins);
					toSave = true;
				}
				
				if (toSave) {
					LibraryService.getInstance().saveCurrentMethodLibrary();
//					LibraryService.getInstance().reopenCurrentMethodLibrary();
				}
				postImportOperation(monitor);
				MethodLibrary lib = LibraryService.getInstance().getCurrentMethodLibrary();
				ResourceUtil.refreshResources(lib, monitor);
				return;
			}			

			// // Remove existing entries.
			// removeExistingEntries(targetLibDoc);

			// Import entries and copy files.
			if (importEntries(targetLibDoc)) {
				// Save the updated library file.
				targetLibDoc.save();

				// Replace the guid of the old MethodLibrary with the new one.
				fixLibraryGuid(libFile.getParentFile(), importingLibDoc
						.getLibraryGuid(), targetLibDoc.getLibraryGuid());

				// Reopen the library.
				LibraryService.getInstance().reopenCurrentMethodLibrary();

				// Finally, re-lock the unlocked plugins and save the library
				// again.
				if (unlockedPlugins.size() > 0) {
					lockUnlockedPlugins(unlockedPlugins);
					LibraryService.getInstance().saveCurrentMethodLibrary();
				}
			}

			// Re-open library and fresh the workspace.
			LibraryService.getInstance().reopenCurrentMethodLibrary();

			// refresh library files in workspace
			//
			MethodLibrary lib = LibraryService.getInstance().getCurrentMethodLibrary();
			ResourceUtil.refreshResources(lib, monitor);

		} catch (Exception e) {
			ImportPlugin.getDefault().getLogger().logError(e);
		}
	}

	protected void postImportOperation(IProgressMonitor monitor) throws Exception {
		// Reopen the library.
		LibraryService.getInstance().reopenCurrentMethodLibrary();	
	}
	
	private List unlockPlugins() {

		List pluginIds = new ArrayList();
		
		Map<String, MethodPlugin> map = new HashMap<String, MethodPlugin>();
		List<MethodPlugin> basePlugins = LibraryService.getInstance().getCurrentMethodLibrary()
											.getMethodPlugins();
		for (int i = 0; i < basePlugins.size(); i++) {
			MethodPlugin plugin = basePlugins.get(i);
			map.put(plugin.getName(), plugin);
		}
		List<MethodPlugin> possibleExtraUnlockPlugins = new ArrayList<MethodPlugin>();

		PluginImportData.PluginInfo info;
		for (Iterator it = data.getPlugins().iterator(); it.hasNext();) {
			info = (PluginImportData.PluginInfo) it.next();
			if ((info.existingPlugin != null) && info.selected) {
				if (info.existingPlugin.getUserChangeable().booleanValue() == false) {
					info.existingPlugin.setUserChangeable(new Boolean(true));
					pluginIds.add(info.existingPlugin.getGuid());
				}
			}
			if (info.selected) {
				MethodPlugin basePlugin = map.get(info.name);
				if (basePlugin != null && ! basePlugin.getGuid().equals(info.guid)
						&& ! basePlugin.getUserChangeable()) {
					possibleExtraUnlockPlugins.add(basePlugin);
				}
			}
		}
		
		handleExtraUnlockPlugins(pluginIds, possibleExtraUnlockPlugins);
		
		return pluginIds;
	}

	private void handleExtraUnlockPlugins(List pluginIds,
			List<MethodPlugin> possibleExtraUnlockPlugins) {
		if (possibleExtraUnlockPlugins == null || possibleExtraUnlockPlugins.isEmpty()) {
			return;
		}
		
		List fileNameToCheck = new ArrayList();
		for (int i = 0; i < possibleExtraUnlockPlugins.size(); i++) {
			MethodPlugin plugin = possibleExtraUnlockPlugins.get(i);
			String guid = plugin.getGuid();
			if (! pluginIds.contains(guid)) {
				plugin.setUserChangeable(new Boolean(true));
				pluginIds.add(guid);
				Resource res = plugin.eResource();
				if (res != null && res.getURI() != null) {
					String fileName = res.getURI().toFileString();
					fileNameToCheck.add(fileName);
				}
			}
		}

		if (fileNameToCheck.size() > 0) {
			final List modifiedFiles = fileNameToCheck;	
			SafeUpdateController.syncExec(new Runnable() {
				public void run() {
					fileCheckedOutStatus = FileModifyChecker.checkModify(modifiedFiles);
				}
			});
		}
	}

	private void lockUnlockedPlugins(List unlockedPlugins) {
		List plugins = LibraryService.getInstance().getCurrentMethodLibrary()
				.getMethodPlugins();
		for (Iterator it = plugins.iterator(); it.hasNext();) {
			MethodPlugin plugin = (MethodPlugin) it.next();
			if (unlockedPlugins.contains(plugin.getGuid())) {
				plugin.setUserChangeable(new Boolean(false));
			}
		}
	}

	private boolean importEntries(LibraryDocument targetLibDoc) {
		// 1. Find the entries to be removed.
		List importList = new ArrayList();
		List newList = new ArrayList();
		PluginImportData.PluginInfo info;
		for (Iterator it = data.getPlugins().iterator(); it.hasNext();) {
			info = (PluginImportData.PluginInfo) it.next();
			if (info.selected) {
				if (info.existingPlugin == null) {
					newList.add(info.guid);
				}

				importList.add(info.guid);
			}
		}

		PluginImportData.ConfiguarationInfo cinfo;
		for (Iterator it = data.getConfigs().iterator(); it.hasNext();) {
			cinfo = (PluginImportData.ConfiguarationInfo) it.next();
			if (cinfo.selected) {
				if (cinfo.existingConfig == null) {
					newList.add(cinfo.guid);
				}

				importList.add(cinfo.guid);

			}
		}

		// 2. Iterate the docuemnt and add the new entries.
		if (!newMergeScheme) {
			importLibEntries(targetLibDoc, newList);
		} else {
			MethodLibrary lib = LibraryService.getInstance().getCurrentMethodLibrary();
			ensureUniqueNames(lib.getMethodPlugins());
			ensureUniqueNames(lib.getPredefinedConfigurations());
		}
		return copyFiles(targetLibDoc, importList, newList);
	}

	private void importLibEntries(LibraryDocument targetLibDoc, List newList) {

		if (newList == null || newList.size() == 0) {
			return;
		}

		// Add plug-ins.
		NodeList nodes = importingLibDoc.getPlugins();
		for (int i = 0; i < nodes.getLength(); i++) {
			Element node = (Element) nodes.item(i);
			String guid = LibraryDocument.getGuid(node);
			if (newList.contains(guid)) {
				targetLibDoc.addPlugin(node);
			}
		}

		// Add configurations.
		nodes = importingLibDoc.getConfigurations();
		for (int i = 0; i < nodes.getLength(); i++) {
			Element node = (Element) nodes.item(i);
			String guid = LibraryDocument.getGuid(node);
			if (newList.contains(guid)) {
				targetLibDoc.addConfiguration(node);
			}
		}

		// add resource entries
		nodes = importingLibDoc.getResourceDescriptors();
		for (int i = 0; i < nodes.getLength(); i++) {
			Element node = (Element) nodes.item(i);
			String guid = node.getAttribute(LibraryDocument.ATTR_id);
			if (newList.contains(guid)) {
				targetLibDoc.addResource(node);
			}
		}

		// Add the resource sub managers.
		nodes = importingLibDoc.getResourceSubManagers();
		for (int i = 0; i < nodes.getLength(); i++) {
			Element node = (Element) nodes.item(i);
			String guid = LibraryDocument.getSubManagerBaseGuid(node
					.getAttribute(LibraryDocument.ATTR_href));
			if (newList.contains(guid)) {
				targetLibDoc.addResource(node);
			}
		}

	}

	private boolean copyFiles(LibraryDocument targetLibDoc, List importList,
			List newList) {
		final MethodLibrary lib = LibraryService.getInstance().getCurrentMethodLibrary();
		
		for (Iterator it = importList.iterator(); it.hasNext();) {
			String guid = (String) it.next();
			String src_uri = importingLibDoc.getResourceUri(guid);
			String target_uri;
			if (src_uri == null || src_uri.length() == 0) {
				continue;
			}

			target_uri = src_uri;
			/*
			if (newList.contains(guid)) {
				target_uri = src_uri;
			} else {
				target_uri = targetLibDoc.getResourceUri(guid); // the resource
				// might be
				// renamed
			}
			*/

			// Check the plugin.xmi file. If it exists, copy the folder to
			// the destination directory.
			final File src_file = importingLibDoc.getFileFromUri(src_uri);
			if (src_file.exists()) {
/*				final File target_file = targetLibDoc
						.getFileFromUri(target_uri);*/
				
				final File target_file = getTargetFile(lib, guid);
				if (target_file == null) {
					return false;
				}
				targetFileMap.put(guid, target_file);

				// if it's a configuration, only copy the file,
				// if it's a plugin, copy the whole directory

				if (data.getPluginInfo(guid) != null) {
					if (target_file.exists()) {
						SafeUpdateController.syncExec(new Runnable() {
							public void run() {
								DirCopy copy = new DirCopy(src_file
										.getParentFile(), target_file
										.getParentFile());
								fileCheckedOutStatus = copy.execute();
							}
						});
					} else {
						copyDir(src_file.getParentFile(), target_file
								.getParentFile());
					}
				} else if (data.getConfigInfo(guid) != null && DirCopy.needCopy(src_file, target_file)) {
					final List files = new ArrayList();
					if (target_file.exists()) {
						files.add(target_file.getAbsolutePath());
						SafeUpdateController.syncExec(new Runnable() {
							public void run() {
								fileCheckedOutStatus = FileModifyChecker
										.checkModify(files);
							}
						});
					}

					if (fileCheckedOutStatus == null
							|| fileCheckedOutStatus.isOK()) {
						FileUtil.copyFile(src_file, target_file);
					}
				}

				if ((fileCheckedOutStatus != null)
						&& !fileCheckedOutStatus.isOK()) {
					// log error
					displayCheckOutError();

					return false;
				}
			}
		}

		return true;
	}

	private void displayCheckOutError() {
		SafeUpdateController.syncExec(new Runnable() {
			public void run() {
				String title = ImportResources.importPluginsWizard_title; 
				String msg = ImportResources.importPluginsWizard_ERR_Import_plugin; 
				new MsgDialog(ImportPlugin.getDefault())
						.displayError(title, msg,
								fileCheckedOutStatus);
			}
		});
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
		cp.execute();

	}

	// /**
	// * remove the plugin entry from the library document
	// *
	// * @param document
	// * LibraryDocument
	// * @param guid
	// * String guid of the plugin
	// */
	// private void removeExistingEntries(LibraryDocument document) {
	// // 1. Find the entries to be removed.
	// List removeList = new ArrayList();
	// PluginImportData.PluginInfo info;
	// for (Iterator it = data.getPlugins().iterator(); it.hasNext();) {
	// info = (PluginImportData.PluginInfo) it.next();
	// if ((info.existingPlugin != null) && info.selected) {
	// removeList.add(info.guid);
	// }
	// }
	//
	// PluginImportData.ConfiguarationInfo cinfo;
	// for (Iterator it = data.getConfigs().iterator(); it.hasNext();) {
	// cinfo = (PluginImportData.ConfiguarationInfo) it.next();
	// if ((cinfo.existingConfig != null) && cinfo.selected) {
	// removeList.add(cinfo.guid);
	// }
	// }
	//
	// // 2. Iterate the docuemnt and remove the entries.
	// document.removePlugins(removeList);
	// document.removeConfigurations(removeList);
	// document.removeResourceEntries(removeList);
	// }

	 /** 
	  * Validates selection.
	 */
	public String validateSelection() {
		if (! isCheckBasePlugins()) {
			return null;
		}
		data.getErrorInfo().clear();

		// Iterate the new plugins, make sure the base is included
		// either as an importing plugin, or is in the current library
		// get the method plugins in the current library.
		MethodLibrary library = LibraryService.getInstance()
				.getCurrentMethodLibrary();
		List plugins = (library == null) ? new ArrayList() : library
				.getMethodPlugins();
		Map pluginids = new HashMap();
		for (Iterator it = plugins.iterator(); it.hasNext();) {
			MethodPlugin plugin = (MethodPlugin) it.next();
			pluginids.put(plugin.getGuid(), plugin);
		}

		// The base plug-ins MUST be either a selected one or an existing one
		// otherwise, can't import
		Set basePlugins = new HashSet();

		Set newPlugins = new HashSet();
		for (Iterator it = data.getPlugins().iterator(); it.hasNext();) {
			PluginImportData.PluginInfo info = (PluginImportData.PluginInfo) it
					.next();
			//if (info.selected && (info.existingPlugin == null)) {
			if (info.selected) {
				newPlugins.add(info.guid);
				for (Iterator itb = info.usedPlugins.iterator(); itb.hasNext();) {
					Object base = itb.next();
					if (!basePlugins.contains(base)) {
						basePlugins.add(base);
					}
				}
			}
		}

		if (newPlugins.size() > 0) {
			for (Iterator it = basePlugins.iterator(); it.hasNext();) {
				String guid = (String) it.next();
				String uri = (String) data.basePluginUrlMap.get(guid);
				if (!newPlugins.contains(guid) && !pluginids.containsKey(guid)) {
					String message;
					if (uri != null && uri.length() > 0) {
						message = NLS.bind(ImportResources.PluginImportingService_MSG5, uri); 
					} else {
						Map<String, String> guidToPlugNameMap = importingLibDoc.getGuidToPlugNameMap();
						String pluginName = guidToPlugNameMap == null ? null : guidToPlugNameMap.get(guid);
						if (pluginName == null || pluginName.length() == 0) {
							message = NLS.bind(ImportResources.PluginImportingService_MSG5, guid); 		
						} else {
							message = NLS.bind(ImportResources.PluginImportingService_MSG5, 
									pluginName + ", " + guid); 		//$NON-NLS-1$
						}
					}
					data.getErrorInfo().addError(message);

					if (uri == null || uri.length() == 0) {
						break;
					}
				}
			}
		}

		return data.getErrorInfo().getError();
	}

	private void scanLibraryFile(LibraryDocument document) {
		visitLibTag(document);
		visitResourceTag(document);
		data.validatePlugins();

		// Check base plug-in dependencies.
		for (Iterator it = data.getPlugins().iterator(); it.hasNext();) {
			PluginImportData.PluginInfo info = (PluginImportData.PluginInfo) it
					.next();
			if (info.existingPlugin == null) {
				for (Iterator itbase = info.usedPlugins.iterator(); itbase
						.hasNext();) {
					String guid = (String) itbase.next();
					if (!data.basePluginUrlMap.containsKey(guid)) {
						data.basePluginUrlMap.put(guid, ""); //$NON-NLS-1$
					}
				}
			}
		}
		
		if (data.getPlugins().size() > 1) {
			Comparator comparator = new Comparator<PluginImportData.PluginInfo>() {

				public int compare(PluginImportData.PluginInfo o1,
						PluginImportData.PluginInfo o2) {
					if (o1 == o2) {
						return 0;
					}
					Collator collator = Collator.getInstance();

					return collator.compare(o1.name, o2.name);

				}

			};

			Collections.<PluginImportData.PluginInfo> sort(data.getPlugins(),
					comparator);
		}
	}

	private void visitLibTag(LibraryDocument document) {
		// Reload the element mapping.
		NodeList nodes = document.getPlugins();
		for (int i = 0; i < nodes.getLength(); i++) {
			Element node = (Element) nodes.item(i);
			PluginImportData.PluginInfo pi = data.new PluginInfo();
			pi.guid = LibraryDocument.getGuid(node);
			data.getPlugins().add(pi);

			// Check with the current library, get the related information.
			loadExistingPluginInfo(pi);
		}

		// Remove the unneeded configurations.
		buildConfigInfoFromFiles();
	}

	private void visitResourceTag(LibraryDocument document) {
		NodeList nodes = document.getResourceDescriptors();
		for (int i = 0; i < nodes.getLength(); i++) {
			Element node = (Element) nodes.item(i);
			String guid = node.getAttribute(LibraryDocument.ATTR_id);
			String uri = node.getAttribute(LibraryDocument.ATTR_uri);

			// Load the plugin.xmi file for detail information.
			File file = document.getFileFromUri(uri);

			PluginImportData.PluginInfo pi = data.getPluginInfo(guid);
			if (pi != null) {
				if (file.exists()) {
					loadPluginInfo(file, pi);
				} else {
					// Remove the plug-in info entry since thias is not
					// a valid plug-in to import.
					data.removePluginInfo(guid);
				}
			} else {
				// if not plugin, might be a configuration
				PluginImportData.ConfiguarationInfo ci = data
						.getConfigInfo(guid);
				if (ci != null) {
					if (file.exists()) {
						loadConfigInfo(file, ci);
					}
				}
			}
		}
	}

	protected void loadConfigInfo(File source,
			PluginImportData.ConfiguarationInfo info) {
		try {
			Document document = XMLUtil.loadXml(source);
			Element root = document.getDocumentElement();

			Element configTag = null;
			if (root.getTagName().equals(
					"org.eclipse.epf.uma:MethodConfiguration")) //$NON-NLS-1$
			{
				configTag = root;
			} else {
				NodeList nodes = root
						.getElementsByTagName("org.eclipse.epf.uma:MethodConfiguration"); //$NON-NLS-1$
				if (nodes.getLength() > 0) {
					configTag = (Element) nodes.item(0);
				}
			}

			if (configTag != null) {
				info.name = configTag.getAttribute("name"); //$NON-NLS-1$
			}
		} catch (Exception e) {
			ImportPlugin.getDefault().getLogger().logError(e);
		}
	}

	protected void loadPluginInfo(File source, PluginImportData.PluginInfo info) {
		try {
			Document document = XMLUtil.loadXml(source);
			Element root = document.getDocumentElement();

			Element pluginTag = null;
			if (root.getTagName().equals("org.eclipse.epf.uma:MethodPlugin")) //$NON-NLS-1$
			{
				pluginTag = root;
			} else {
				NodeList nodes = root
						.getElementsByTagName("org.eclipse.epf.uma:MethodPlugin"); //$NON-NLS-1$
				if (nodes.getLength() > 0) {
					pluginTag = (Element) nodes.item(0);
				}
			}

			if (pluginTag != null) {
				info.name = pluginTag.getAttribute("name"); //$NON-NLS-1$
				info.version = LibraryDocument.getChildValue(pluginTag,
						"version"); //$NON-NLS-1$
				info.brief_desc = LibraryDocument.getChildValue(pluginTag,
						"briefDescription"); //$NON-NLS-1$
				info.authors = LibraryDocument.getChildValue(pluginTag,
						"authors"); //$NON-NLS-1$
				info.changeDate = LibraryDocument.getChildValue(pluginTag,
						"changeDate"); //$NON-NLS-1$
				info.url = source.toString();

				// Get the base plug-ins.
				NodeList nodes = pluginTag.getElementsByTagName("bases"); //$NON-NLS-1$
				for (int i = 0; i < nodes.getLength(); i++) {
					Element node = (Element) nodes.item(i);
					String guid = node.getAttribute(LibraryDocument.ATTR_href);

					int indx = guid.indexOf("#"); //$NON-NLS-1$
					if (indx > 0) {
						guid = guid.substring(indx + 1);
					} else {
						indx = guid.indexOf("uma://"); //$NON-NLS-1$
						if (indx >= 0) {
							guid = guid.substring(indx + 6);
						}
					}
					info.usedPlugins.add(guid);
				}
			}
		} catch (Exception e) {
			ImportPlugin.getDefault().getLogger().logError(e);
		}
	}

	private void loadExistingPluginInfo(PluginImportData.PluginInfo info) {
		MethodLibrary library = LibraryService.getInstance()
				.getCurrentMethodLibrary();
		List plugins = (library == null) ? new ArrayList() : library
				.getMethodPlugins();
		for (Iterator it = plugins.iterator(); it.hasNext();) {
			MethodPlugin plugin = (MethodPlugin) it.next();
			if (plugin.getGuid().equals(info.guid)) {
				info.existingPlugin = plugin;
			}
		}
	}

	private void loadExistingConfigInfo(PluginImportData.ConfiguarationInfo info) {
		MethodConfiguration[] configs = LibraryServiceUtil
				.getMethodConfigurations(LibraryService.getInstance()
						.getCurrentMethodLibrary());
		if (configs == null || configs.length == 0) {
			return;
		}

		for (int i = 0; i < configs.length; i++) {
			MethodConfiguration config = configs[i];
			if (config.getGuid().equals(info.guid)) {
				info.existingConfig = config;
			}
		}
	}

	/**
	 * Replaces the guid of the old method library with the new one.
	 * <p>
	 * Search for the following files: library.xmi, plugin.xmi, model.xmi.
	 */
	private void fixLibraryGuid(File path, String oldGuid, String newGuid) {
		if (!path.isDirectory()) {
			return;
		}

		File[] files = path.listFiles(new FileFilter() {

			public boolean accept(File f) {

				if (f.isDirectory()) {
					return true;
				}

				String name = f.getName();
				return name.equals("library.xmi") || name.equals("plugin.xmi") || name.equals("model.xmi"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			}
		});

		if (files == null || files.length == 0) {
			return;
		}

		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				fixLibraryGuid(files[i], oldGuid, newGuid);
			} else {
				// Read in the file, update it and save the file.
				try {
					String source = FileUtil.readFile(files[i],
							FileUtil.ENCODING_UTF_8).toString();
					if (source.indexOf(oldGuid) >= 0) {
						// TODO: This is a regexp repalcement, is it safe?
						source = source.replaceAll(oldGuid, newGuid);

						FileUtil.writeUTF8File(files[i].getAbsolutePath(),
								source);
					}

				} catch (IOException e) {
					ImportPlugin.getDefault().getLogger().logError(e);
				}
			}
		}
	}
	
	private boolean merge(LibraryDocument targetLibDoc) throws Exception {
		//Copy files only as newMergeScheme = true
		if (! importEntries(targetLibDoc)) {
			return false;
		}
		
		MethodLibrary lib = LibraryService.getInstance().getCurrentMethodLibrary();
		Resource res0 = lib.eResource();
		ResourceManager resMgr0 = (ResourceManager) MultiFileSaveUtil.getResourceManager(res0);
		ResourceSet resSet = res0.getResourceSet();
		
		mergePlugins(targetLibDoc, lib, resMgr0, resSet);		
		mergeConfigs(targetLibDoc, lib, resMgr0, resSet);
		
		lib.eResource().setModified(true);
		
		save(lib, null);				
		
		//fixLibraryGuid(targetLibDoc.getFile().getParentFile(), importingLibDoc
		//		.getLibraryGuid(), targetLibDoc.getLibraryGuid());
		
		LibraryService.getInstance().reopenCurrentMethodLibrary();
		
		if (! isCheckBasePlugins()) {
			lib = LibraryService.getInstance().getCurrentMethodLibrary();
			LibraryUtil.loadAll(lib);
			List<MethodPlugin> missingBasePlugins = collectMissingBasePlugins(lib);
			List<MethodConfiguration> configs = lib.getPredefinedConfigurations();// collectImportedConfigurations(lib);			
			checkModify(configs);
			if (! missingBasePlugins.isEmpty() || ! configs.isEmpty()) {			
				lib.eResource().setModified(true);				
				Set<Resource> resouresToSave = new LinkedHashSet<Resource>();
				collectResourcestoSave(resouresToSave, missingBasePlugins);
				collectResourcestoSave(resouresToSave, configs);
				save(lib, resouresToSave);				
				LibraryService.getInstance().reopenCurrentMethodLibrary();
			}
		}
		
		return true;
	}

	private void checkModify(List<MethodConfiguration> configs) {
		if (configs == null) {
			return;
		}
		final List configFiles = new ArrayList();
		for (int i = 0; i < configs.size(); i++) {
			MethodConfiguration config = configs.get(i);
			Resource res = config.eResource();
			if (res != null) {
				String file = res.getURI().toFileString();
				configFiles.add(file);
			}
		}
		SafeUpdateController.syncExec(new Runnable() {
			public void run() {
				fileCheckedOutStatus = FileModifyChecker.checkModify(configFiles);
			}
		});
	}
	
	private List<MethodPlugin> collectMissingBasePlugins(MethodLibrary lib) {
		List<MethodPlugin> ret = new ArrayList<MethodPlugin>();
		
		Map map = MethodElementUtil.buildMap(lib.getMethodPlugins());
		for (Iterator it = data.getPlugins().iterator(); it.hasNext();) {
			PluginImportData.PluginInfo info = (PluginImportData.PluginInfo) it.next();
			if (info.selected ) {
				MethodPlugin plugin = (MethodPlugin) map.get(info.guid);
				if (plugin != null) {
					List<MethodPlugin> bases = plugin.getBases();
					for (int i=0; i < bases.size(); i++) {
						MethodPlugin base = bases.get(i);
						if (! map.containsKey(base.getGuid())) {
							ret.add(plugin);
							break;
						}
					}
				}
			}
		}
		
		return ret;
	}	
	
	private void collectResourcestoSave(Set<Resource> resources, List<? extends MethodElement> elements) {
		if (elements == null || elements.isEmpty()) {
			return;
		}		
		for (int i=0; i < elements.size(); i++) {
			collectResourcestoSave(resources, elements.get(i));
		}
	}
	
	private void collectResourcestoSave(Set<Resource> resources, MethodElement parent) {
		if (parent instanceof MethodPlugin) {		
			for (Iterator it = parent.eAllContents(); it.hasNext();) {
				EObject element = (EObject) it.next();
				if (element instanceof ContentDescription) {
					continue;
				}
				Resource res = element.eResource();
				if (res != null) {
					resources.add(res);
					res.setModified(true);
				}
			}
		}
		Resource res = parent.eResource();
		resources.add(res);
		res.setModified(true);
	}
	
	private void save(ILibraryPersister.FailSafeMethodLibraryPersister persister, Set<Resource> resouresToSave) 
		throws Exception {
		if (resouresToSave == null) {
			return;
		}
		for (Iterator<Resource> it = resouresToSave.iterator(); it.hasNext();) {
			MultiFileXMIResourceImpl res = (MultiFileXMIResourceImpl) it.next();
			try {
				if (! res.isSynchronized()) {
					res.load(res.getResourceSet().getLoadOptions());
				}
				persister.save(res);
			} catch (Exception e){
				e.printStackTrace();
			}
		}
	}	

	private void save(MethodLibrary lib, Set<Resource> resouresToSave) {
		//LibraryUtil.saveLibrary(lib, false, false);
		
		//This is temp, and can be revomved after a fix is done for passing persister's option to save
		MultiFileResourceSetImpl resourceSet = (MultiFileResourceSetImpl) lib.eResource().getResourceSet();
		Map defaultSaveOptions = resourceSet.getDefaultSaveOptions();
		Object oldDefaultOptionVal = defaultSaveOptions.get(MultiFileXMISaveImpl.DISCARD_UNRESOLVED_REFERENCES);
		
		ILibraryPersister.FailSafeMethodLibraryPersister persister = LibraryServiceUtil.getCurrentPersister().getFailSafePersister();
		Map saveOptions = persister.getSaveOptions();
		if (! isCheckBasePlugins() && resouresToSave != null) {
			saveOptions.put(MultiFileXMISaveImpl.DISCARD_UNRESOLVED_REFERENCES, Boolean.TRUE);
			defaultSaveOptions.put(MultiFileXMISaveImpl.DISCARD_UNRESOLVED_REFERENCES, Boolean.TRUE);
		}

		try {
			save(persister, resouresToSave);
			persister.save(lib.eResource());
			persister.commit();
			
		} catch (Exception e) {
			persister.rollback();
			e.printStackTrace();
			
		} finally {
			if (! isCheckBasePlugins() && resouresToSave != null) {				
				if (oldDefaultOptionVal == null) {
					defaultSaveOptions.remove(MultiFileXMISaveImpl.DISCARD_UNRESOLVED_REFERENCES);
				} else {
					defaultSaveOptions.put(MultiFileXMISaveImpl.DISCARD_UNRESOLVED_REFERENCES, oldDefaultOptionVal);
				}
			}
		}
	}

	private void mergePlugins(LibraryDocument targetLibDoc, MethodLibrary lib, ResourceManager resMgr0, ResourceSet resSet) {
		List existingPluginGuids = null;
		for (Iterator it = data.getPlugins().iterator(); it.hasNext();) {
			PluginImportData.PluginInfo info = (PluginImportData.PluginInfo) it.next();
			if (info.selected && info.existingPlugin != null) {
				if (existingPluginGuids == null) {
					existingPluginGuids = new ArrayList();
				}
				existingPluginGuids.add(info.guid);
			}
		}
		
		Map pgToRgMap = importingLibDoc.buildPluginGuidToResMgrGuidMap(existingPluginGuids);				
		
		for (Iterator it = data.getPlugins().iterator(); it.hasNext();) {
			PluginImportData.PluginInfo info = (PluginImportData.PluginInfo) it.next();
			if (info.selected && info.existingPlugin == null) {
				mergeElement(info.guid, targetLibDoc, lib, resMgr0, resSet, lib.getMethodPlugins());
			} else if (info.selected && pgToRgMap != null) {	//151786
				Resource res = info.existingPlugin.eResource();
				ResourceManager resMgr = (ResourceManager) MultiFileSaveUtil.getResourceManager(res);
				String existingGuid = resMgr.getGuid();
				String importedGuid = (String) pgToRgMap.get(info.guid);
				if (importedGuid != null && !importedGuid.equals(existingGuid)) {
					resMgr.setGuid(importedGuid);
				}
			}
		}
	}
	
	private void mergeConfigs(LibraryDocument targetLibDoc, MethodLibrary lib, ResourceManager resMgr0, ResourceSet resSet) {
		for (Iterator it = data.getConfigs().iterator(); it.hasNext();) {
			PluginImportData.ConfiguarationInfo info = (PluginImportData.ConfiguarationInfo) it.next();
			if (info.selected && info.existingConfig == null) {
				mergeElement(info.guid, targetLibDoc, lib, resMgr0, resSet, lib.getPredefinedConfigurations());
			}					
		}
	}
	
	private void mergeElement(String guid, LibraryDocument targetLibDoc, 
			MethodLibrary lib, ResourceManager resMgr0, ResourceSet resSet, List elements){
		String src_uri = importingLibDoc.getResourceUri(guid);
		if (src_uri == null || src_uri.length() == 0) {
			return;
		}
		//File target_file = targetLibDoc.getFileFromUri(src_uri);
		File target_file = targetFileMap.get(guid);
		
		String path = target_file.getAbsolutePath();
		
		URI uri = URI.createFileURI(path);
		Resource res = resSet.getResource(uri, true);
		MethodElement element = PersistenceUtil.getMethodElement(res);
		elements.add(element);		
		
		MultiFileSaveUtil.registerWithResourceManager(resMgr0, element, uri);
		ResourceManager resMgr = (ResourceManager) MultiFileSaveUtil.getResourceManager(res);
		if (resMgr != null) {
			resMgr0.getSubManagers().add(resMgr);
		}
	}		
	
	private void buildConfigInfoFromFiles() {	
		File copiedLibPath = upGradeInfo == null ? null : upGradeInfo.getCopiedLibFile();
		File importingLibPath = copiedLibPath == null ? new File(data.llData.getParentFolder()
				+ File.separator + LibraryDocument.exportFile) : copiedLibPath;
		File configDir = new File(importingLibPath.getParent(), MultiFileSaveUtil.METHOD_CONFIGURATION_FOLDER_NAME);
		
		LibraryDocument.ConfigDocVisitor visitor = new LibraryDocument.ConfigDocVisitor() {
			public void visit(File file, Element node) {
				PluginImportData.ConfiguarationInfo ci = data.new ConfiguarationInfo();
				ci.guid = node.getAttribute(LibraryDocument.ATTR_guid);
				ci.name = node.getAttribute("name"); //$NON-NLS-1$
				String uri = MultiFileSaveUtil.METHOD_CONFIGURATION_FOLDER_NAME + File.separator + file.getName();
				importingLibDoc.addToGuidToUriMap(ci.guid, uri);
				data.getConfigs().add(ci);
				// Check with the current library, get the related information.
				loadExistingConfigInfo(ci);
				if (ci.existingConfig != null) {
					URI resUri = ci.existingConfig.eResource().getURI();
					uri = MultiFileSaveUtil.METHOD_CONFIGURATION_FOLDER_NAME + File.separator + resUri.lastSegment();
					uri = targetLibDoc.decodeUri(uri);
					targetLibDoc.addToGuidToUriMap(ci.guid, uri);
				}
			}
		};
		
		LibraryDocument.visitConfigFiles(configDir, visitor);				
	}
	
	private void ensureUniqueNames(List elements) {
		if (elements.isEmpty()) {
			return;
		}
		Map nameMap = new HashMap();
		for (int i=0; i < elements.size(); i++) {
			MethodElement elem = (MethodElement) elements.get(i);
			nameMap.put(elem.getName().toUpperCase(), elem);
		}
		
		List importedList = new ArrayList();
		List importedExistList = new ArrayList();
		if (elements.get(0) instanceof MethodPlugin) {
			for (Iterator it = data.getPlugins().iterator(); it.hasNext();) {
				PluginImportData.PluginInfo info = (PluginImportData.PluginInfo) it.next();
				if (info.selected) {
					if (info.existingPlugin == null || 
							!info.existingPlugin.getName().equals(info.name)) {
						importedList.add(info.name);
						if (info.existingPlugin != null) {
							importedExistList.add(info);
						}
					}
				}
			}
		} else {
			for (Iterator it = data.getConfigs().iterator(); it.hasNext();) {
				PluginImportData.ConfiguarationInfo info = (PluginImportData.ConfiguarationInfo) it.next();
				if (info.selected) {
					if (info.existingConfig == null || 
							!info.existingConfig.getName().equals(info.name)) {
						importedList.add(info.name);
						if (info.existingConfig != null) {
							importedExistList.add(info);
						}
					}
				}				
			}
		}
		for (int i=0; i<importedList.size(); i++) {
			String name = (String) importedList.get(i);
			String renamed = name;
			while (nameMap.containsKey(renamed.toUpperCase())) {
				renamed += "_renamed"; //$NON-NLS-1$
			}
			if (renamed != name) {
				MethodElement elem = (MethodElement) nameMap.get(name.toUpperCase());
				LibraryView.runRename(elem, renamed);
			}			
		}				
		for (int i=0; i<importedExistList.size(); i++) {
			Object info = importedExistList.get(i);
			MethodElement elem = info instanceof PluginImportData.PluginInfo ? 
					(MethodElement)((PluginImportData.PluginInfo) info).existingPlugin :
					(MethodElement)((PluginImportData.ConfiguarationInfo) info).existingConfig;
			String newName = info instanceof PluginImportData.PluginInfo ? 
					(String)((PluginImportData.PluginInfo) info).name :
					(String)((PluginImportData.ConfiguarationInfo) info).name;								
			LibraryView.runRename(elem, newName);		
		}	
		
		
		
	}
	
	private File getTargetFile(final MethodLibrary lib, String guid) {
		File parent = null;
		final File[] pfiles = new File[1]; 
		final IFileBasedLibraryPersister persister = (IFileBasedLibraryPersister) LibraryServiceUtil.getCurrentPersister();
		ConfiguarationInfo cinfo = data.getConfigInfo(guid);
		if (cinfo != null) {
			File file = getResourceFile(cinfo.existingConfig);
			if (file != null) {
				return file;
			}
			if (defaultConfigFolder == null) {
				SafeUpdateController.syncExec(new Runnable() {
					public void run() {
						pfiles[0] = persister.getDefaultMethodConfigurationFolder(lib);
					}
				});
				parent = pfiles[0];
				if (parent == null) {
					return null;
				}
				defaultConfigFolder = parent;
			} else {
				parent = defaultConfigFolder;
			}
			if (localDebug) {
				System.out.println("LD> defaultConfigFolder: " + parent); //$NON-NLS-1$
			}
		} else {
			PluginInfo pinfo = data.getPluginInfo(guid);
			if (pinfo != null) {
				File file = getResourceFile(pinfo.existingPlugin);
				if (file != null) {
					return file;
				}
				parent = persister.createMethodPluginFolder(pinfo.name, lib);
			} else {
				throw new UnsupportedOperationException();
			}
		}

		String fileName = cinfo == null ? MultiFileSaveUtil.DEFAULT_PLUGIN_MODEL_FILENAME : 
									cinfo.name + MultiFileSaveUtil.DEFAULT_FILE_EXTENSION;	
		return new File(parent, fileName);
	}

	public boolean isCheckBasePlugins() {
		return checkBasePlugins;
	}

	public void setCheckBasePlugins(boolean checkBasePlugins) {
		this.checkBasePlugins = checkBasePlugins;
	}
	
	private File getResourceFile(MethodElement element) {
		if (element == null) {
			return null;
		}
		Resource res = element.eResource();
		URI uri = res.getURI();
		return new File(uri.toFileString());
	}

	public Object getValidateHookData() {
		return validateHookData;
	}

	public void setValidateHookData(Object validateHookData) {
		this.validateHookData = validateHookData;
	}
	
	public boolean isSynFreeLib() {
		return importingLibDoc.isSynFreeLib();
	}
	
	
}