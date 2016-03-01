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
package org.eclipse.epf.importing.xml.services;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.epf.common.service.versioning.VersionUtil;
import org.eclipse.epf.common.ui.util.MsgBox;
import org.eclipse.epf.common.ui.util.MsgDialog;
import org.eclipse.epf.common.utils.ExtensionHelper;
import org.eclipse.epf.dataexchange.util.ContentProcessor;
import org.eclipse.epf.dataexchange.util.IResourceHandler;
import org.eclipse.epf.export.services.DiagramHandler;
import org.eclipse.epf.export.xml.ExportXMLResources;
import org.eclipse.epf.export.xml.services.FeatureManager;
import org.eclipse.epf.export.xml.services.XMLLibrary;
import org.eclipse.epf.importing.ImportPlugin;
import org.eclipse.epf.importing.ImportResources;
import org.eclipse.epf.importing.services.ConfigurationImportService;
import org.eclipse.epf.importing.services.FileModifyChecker;
import org.eclipse.epf.importing.services.LibraryImportManager;
import org.eclipse.epf.importing.wizards.PluginModifyInfo;
import org.eclipse.epf.importing.wizards.SelectImportConfigurationSource;
import org.eclipse.epf.importing.xml.ImportXMLPlugin;
import org.eclipse.epf.importing.xml.ImportXMLResources;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.edit.util.DescriptorPropUtil;
import org.eclipse.epf.library.edit.util.MethodLibraryPropUtil;
import org.eclipse.epf.library.edit.util.MethodPluginPropUtil;
import org.eclipse.epf.library.services.SafeUpdateController;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.persistence.MultiFileResourceSetImpl;
import org.eclipse.epf.persistence.migration.IMigrator;
import org.eclipse.epf.persistence.migration.MappingUtil;
import org.eclipse.epf.persistence.refresh.RefreshJob;
import org.eclipse.epf.services.IFileBasedLibraryPersister;
import org.eclipse.epf.services.ILibraryPersister;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.ecore.IModelObject;
import org.eclipse.epf.xml.uma.Activity;
import org.eclipse.epf.xml.uma.BreakdownElement;
import org.eclipse.epf.xml.uma.CapabilityPattern;
import org.eclipse.epf.xml.uma.ContentCategory;
import org.eclipse.epf.xml.uma.ContentCategoryPackage;
import org.eclipse.epf.xml.uma.ContentPackage;
import org.eclipse.epf.xml.uma.DeliveryProcess;
import org.eclipse.epf.xml.uma.Discipline;
import org.eclipse.epf.xml.uma.Domain;
import org.eclipse.epf.xml.uma.Guidance;
import org.eclipse.epf.xml.uma.MethodElement;
import org.eclipse.epf.xml.uma.MethodElementProperty;
import org.eclipse.epf.xml.uma.MethodLibrary;
import org.eclipse.epf.xml.uma.MethodPackage;
import org.eclipse.epf.xml.uma.MethodPlugin;
import org.eclipse.epf.xml.uma.ProcessComponent;
import org.eclipse.epf.xml.uma.ProcessPackage;
import org.eclipse.epf.xml.uma.TaskDescriptor;
import org.eclipse.epf.xml.uma.UmaPackage;
import org.eclipse.epf.xml.uma.WorkOrder;
import org.eclipse.epf.xml.uma.WorkOrderType;
import org.eclipse.osgi.util.NLS;

/**
 * Xml import service class to process the importing task
 * 
 * @author Jinhua Xi
 * @since 1.0
 */
public class ImportXMLService {

	private List unlockedPlugins; 
	
	private boolean error = false;
	
	private Map renameElementMap = new HashMap();
	
	private boolean overwrite = false;
	
	private int mergeLevel = 0;
	
	private IStatus fileCheckedOutStatus = null;
	
	private String xmlPath;

	protected XMLLibrary xmlLib;

	private UmaLibrary umaLib;

	private ImportXMLogger logger = new ImportXMLogger();

	private List discardedElements = new ArrayList();

	private DiagramHandler diagramHandler;
	
	//tds with steps
	private Map<String, TaskDescriptor> tdMap;
	
	// map xml WorkOrder to uma WorkOrder
	Map workOrderMap = new HashMap();
	
	private IMigrator migrator;
	
	private boolean checkBasePlugins = true;
	
	private boolean isBaseLibSynFree = false;

	/**
	 * The constructor
	 */
	public ImportXMLService() {

	}

	public static ImportXMLService newInstance() {
		Object obj = ExtensionHelper.create(ImportXMLService.class, null);
		if (obj instanceof ImportXMLService) {
			return (ImportXMLService) obj;
		}		
		return new ImportXMLService();
	}
	
	/**
	 * Loads the xml file.
	 * @param xmlPath
	 * @return true if succeeds.
	 */
	public boolean loadXml(final String xmlPath) {
		String versionError = versionCheck(xmlPath,	ImportXMLResources.importXMLWizard_title);		
		if (versionError != null) {
			return false;
		}
				
		this.xmlPath = xmlPath;

		logger.logMessage("Loading xml library ..."); //$NON-NLS-1$
		
		boolean ret = false;
		try {
			this.xmlLib = new XMLLibrary(null, logger, xmlPath);
			this.xmlLib.load();
			ret = checkOldPlugins();
		} catch (Exception e) {
			logger.logError(NLS.bind(
					ExportXMLResources.xmlLibrary_error_load_xml, xmlPath), e);
			final String errStr = e.toString();
			SafeUpdateController.syncExec(new Runnable() {	
				public void run() {	
					ImportXMLPlugin.getDefault().getMsgDialog().displayError(
						NLS.bind(ExportXMLResources.xmlLibrary_error_load_xml, xmlPath), errStr);
				}
			});
		}
		
		return ret;
	}
	
	private boolean checkOldPlugins() {
		
		unlockedPlugins = new ArrayList();
		List fileNameToCheck = new ArrayList();
		Map map = new HashMap();
		Map guidMap = new HashMap();
		List basePlugins = LibraryService.getInstance().getCurrentMethodLibrary()
											.getMethodPlugins();
		for (int i = 0; i < basePlugins.size(); i++) {
			org.eclipse.epf.uma.MethodPlugin plugin = (org.eclipse.epf.uma.MethodPlugin) basePlugins.get(i);
			map.put(plugin.getName(), plugin);
			guidMap.put(plugin.getGuid(), plugin);
		}
		
		List plugins = ((MethodLibrary) xmlLib.getRoot()).getMethodPlugin();
		List guids = new ArrayList();
		for (int i=0; i<plugins.size(); i++) {
			MethodElement elem = (MethodElement) plugins.get(i);
			guids.add(elem.getId());
			
			org.eclipse.epf.uma.MethodPlugin basePlugin = 
				(org.eclipse.epf.uma.MethodPlugin) map.get(elem.getName());
			boolean toAdd = false;
			if (basePlugin != null && ! basePlugin.getGuid().equals(elem.getId())
					&& ! basePlugin.getUserChangeable()) {
				toAdd = true;
			} else {
				basePlugin = 
					(org.eclipse.epf.uma.MethodPlugin) guidMap.get(elem.getId());
				if (basePlugin != null /*&& ! basePlugin.getName().equals(elem.getName())*/
						&& ! basePlugin.getUserChangeable()) {
					toAdd = true;
				} 
			}
			if (toAdd) {
				unlockedPlugins.add(basePlugin.getGuid());
				basePlugin.setUserChangeable(new Boolean(true));
				Resource res = basePlugin.eResource();
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
		
		if (! unlockedPlugins.isEmpty() && (fileCheckedOutStatus != null)
				&& !fileCheckedOutStatus.isOK()) {
			// log error
			SafeUpdateController.syncExec(new Runnable() {
				public void run() {
					String title = ImportXMLResources.importXMLWizard_title; 
					String msg = ImportXMLResources.importXMLService_import_failed; 
					new MsgDialog(ImportPlugin.getDefault())
						.displayError(title, msg, fileCheckedOutStatus);
					}
			});
			return false;
		}
		
		final PluginModifyInfo modifyInfo = SelectImportConfigurationSource
										.checkModify(guids, MsgBox.getDefaultShell(), false);		

		// allow user to proceed with locked plugins
		if ( modifyInfo.lockedPlugins.size() > 0 ) {
			final boolean ret[] = new boolean[1];
			SafeUpdateController.syncExec(new Runnable() {
				public void run() {
					String WIZARD_TITLE = ImportXMLResources.importXMLWizard_title; 
					String message = modifyInfo.getLockedMessage().toString() +
						ImportResources.ImportPluginWizard_confirm_continue; 
					ret[0] = ImportXMLPlugin
							.getDefault().getMsgDialog().displayConfirmation(WIZARD_TITLE, message);
				}
			});		
			return ret[0];
		} 			
		return true;
	}
	
	/**
	 * @return the log file.
	 */
	public File getLogPath() {
		return logger.getLogPath();
	}

	/**
	 * Dispose
	 */
	public void dispose() {

		discardedElements.clear();
		workOrderMap.clear();
		logger.dispose();
	}

	/**
	 * Does XML export.
	 * @param monitor
	 * @throws Exception
	 */
	public void doImport(IProgressMonitor monitor) throws Exception {
		isBaseLibSynFree = MethodLibraryPropUtil.getMethodLibraryPropUtil()
				.isSynFree(
						LibraryService.getInstance().getCurrentMethodLibrary());
		
		boolean refresh = RefreshJob.getInstance().isEnabled();
		try {
			if (refresh) {
				// disable resource refreshing during import
				//
				RefreshJob.getInstance().setEnabled(false);
			}
			
			handleTypeChanges();
			
			IResourceHandler handler = new ImportResourceHandler(new File(
					xmlPath).getParentFile(), new File(LibraryService
					.getInstance().getCurrentMethodLibraryLocation()));

			ContentProcessor contentProc = new ContentProcessor(handler,
					this.logger);
			
			diagramHandler = new DiagramHandler(new File(
					xmlPath).getParentFile(), new File(LibraryService
							.getInstance().getCurrentMethodLibraryLocation())) {				
				//[0]: sourceFile
				//[1]: targetFile
				protected File[] getFiles(org.eclipse.epf.uma.MethodElement elem) {
					File[] files = super.getFiles(elem, false);
										
					IModelObject xmlObj = xmlLib.getElement(elem.getGuid());
					if (xmlObj instanceof org.eclipse.epf.xml.uma.Process) {
						org.eclipse.epf.xml.uma.Process proc = 
								(org.eclipse.epf.xml.uma.Process) xmlObj;
						String uri = proc.getDiagramURI();
						if (uri != null && uri.length() > 0) {
							File file = new File(getSourceLibRoot(), uri);
							//System.out.println("LD: file: " + file);
							if (file.exists()) {
								files[0] = file;
								files[1] = new File(getTargetLibRoot(), uri);
							} else {
								//System.out.println("LD: files[0]: " + files[0]);
							}
						}					
					}
					
					return files;
				}
				
			};

			this.xmlLib.fixLibraryForImport();

			// make sure all the dependent plugins are there
			List referencedPlugins = xmlLib.getReferencedPlugins();
			if (referencedPlugins.size() > 0) {
				org.eclipse.epf.uma.MethodLibrary library = org.eclipse.epf.library.LibraryService
						.getInstance().getCurrentMethodLibrary();
				if (library != null) {
					List plugins = library.getMethodPlugins();
					for (Iterator it = plugins.iterator(); it.hasNext();) {
						String id = ((org.eclipse.epf.uma.MethodPlugin) it
								.next()).getGuid();
						referencedPlugins.remove(id);
					}
				}
			}

			if (isCheckBasePlugins() && referencedPlugins.size() > 0) {
				 Map<String, String> map = xmlLib.getGuidToPlugNameMap();
				for (Iterator it = referencedPlugins.iterator(); it.hasNext();) {
					String nameAndId = "";		//$NON-NLS-1$
					String id = (String) it.next();
					if (map != null) {
						String name = map.get(id);
						if (name != null) {
							nameAndId += name + ", ";//$NON-NLS-1$
						}
					}
					nameAndId += id;
					logger.logError(NLS.bind(ImportXMLResources.importXMLService_missing_plugin, nameAndId),
							null);			
				}

				throw new Exception(
						ImportXMLResources.importXMLService_error_missing_plugins);
			}

			this.umaLib = new UmaLibrary(renameElementMap, contentProc, logger, overwrite);
			this.umaLib.setMergeLevel(mergeLevel);

			IModelObject xmlRoot = this.xmlLib.getRoot();
			IModelObject umaRoot = this.umaLib.getRoot();
			
			logger.logMessage("create target library elements ..."); //$NON-NLS-1$
			creatEDataObjectTree(xmlRoot, umaRoot);

			logger
					.logMessage("copying all feature values for target library elements ..."); //$NON-NLS-1$
			iteratEDataObject((MethodElement) xmlRoot);

			logger.logMessage("fix the target library ..."); //$NON-NLS-1$
			umaLib.fixLibrary();
			umaLib.fixTaskDescriptors(tdMap);

			LibraryImportManager.handleNameReplace(renameElementMap);
			
			diagramHandler.postRegisterElements();
			
			// delete the un-needed elements
			logger.logMessage("delete unneeded elements ..."); //$NON-NLS-1$
			umaLib.deleteElements();
			
			final List modifiedFiles = FileModifyChecker.getModifiedFiles(LibraryService.getInstance().
										getCurrentMethodLibrary().eResource().getResourceSet());
			modifiedFiles.addAll(diagramHandler.getModifiedFiles());
			final IStatus fileCheckedOutStatus = FileModifyChecker.syncExecCheckModify(modifiedFiles);
			
			if (fileCheckedOutStatus.isOK()) {
			
				logger.logMessage("saving target library ..."); //$NON-NLS-1$
	
				List configs = LibraryService.getInstance()
				.getCurrentMethodLibrary().getPredefinedConfigurations();
				int configSz = configs == null ? 0 : configs.size();
				for (int i=0; i<configSz; i++) {
					org.eclipse.epf.uma.MethodConfiguration config = 
						(org.eclipse.epf.uma.MethodConfiguration ) configs.get(i);
					LibraryUtil.validateMethodConfiguration(null, config);
				}
				
				if (configSz != 0) {
					org.eclipse.epf.uma.MethodLibrary currLib = LibraryService.getInstance().getCurrentMethodLibrary();
					ILibraryPersister persister = ((MultiFileResourceSetImpl) currLib.eResource().getResourceSet()).getPersister();
					if (persister instanceof IFileBasedLibraryPersister) {
						IFileBasedLibraryPersister ip = (IFileBasedLibraryPersister) persister;
						File configFolder = ip.getDefaultMethodConfigurationFolder(currLib);
						if (configFolder != null) {
							ip.setDefaultMethodConfigurationFolder(currLib, configFolder);
						} else {
							throw new Exception(
									ImportXMLResources.importXMLService_import_failed);
						}
					}
				}
				
				// don't refresh the workspace
				if (unlockedPlugins != null && ! unlockedPlugins.isEmpty()) {
					LibraryImportManager.lockUnlockedPlugins(unlockedPlugins);
				}

				if (isBaseLibSynFree && !isSynFreeLib()) {
					MethodLibraryPropUtil libPropUtil = MethodLibraryPropUtil
							.getMethodLibraryPropUtil();
					org.eclipse.epf.uma.MethodLibrary lib = LibraryService
							.getInstance().getCurrentMethodLibrary();
					if (!libPropUtil.isSynFree(lib)) {
						libPropUtil.setSynFree(lib, true);
					}
					MethodPluginPropUtil pluginPropUtil = MethodPluginPropUtil
							.getMethodPluginPropUtil();
					for (org.eclipse.epf.uma.MethodPlugin p : lib
							.getMethodPlugins()) {
						if (!pluginPropUtil.isSynFree(p)) {
							pluginPropUtil.setSynFree(p, true);
						}
					}
				}		
				
				LibraryUtil.saveLibrary(LibraryService.getInstance()
						.getCurrentMethodLibrary(), false, false);
				diagramHandler.execute();				
	
				logger.logMessage("re-open target library ..."); //$NON-NLS-1$
//				LibraryService.getInstance().reopenCurrentMethodLibrary();
				postImportOperation(monitor);
				
				if (migrator != null) {
					org.eclipse.epf.uma.MethodLibrary lib = LibraryService.getInstance().getCurrentMethodLibrary();
					migrator.migrateXmlImportedLib(lib, monitor);					
					LibraryService.getInstance().reopenCurrentMethodLibrary();
				}
					
				logger.logMessage("import completed successfully ..."); //$NON-NLS-1$
			} else {
				SafeUpdateController.syncExec(new Runnable() {
					public void run() {
						String title = ImportXMLResources.importXMLWizard_title; 
						String msg = ImportXMLResources.importXMLService_import_failed; 
						new MsgDialog(ImportXMLPlugin.getDefault())
							.displayError(title, msg, fileCheckedOutStatus);
						}
				});
			}	
						
			//ResourceUtil.refreshResources(LibraryService.getInstance()
			//		.getCurrentMethodLibrary(), monitor);

		} catch (Exception e) {
			logger.logError(NLS.bind(ImportXMLResources.importXMLService_import_failed, e.getMessage()), e);

			throw e;

		} finally {
			if (refresh) {
				// re-enable resource refreshing
				//
				RefreshJob.getInstance().setEnabled(true);
			}
		}
	}

	protected void postImportOperation(IProgressMonitor monitor) throws Exception {
		// Reopen the library.
		LibraryService.getInstance().reopenCurrentMethodLibrary();	
	}
	
	private void creatEDataObjectTree(IModelObject xmlObj, IModelObject umaObj) {

		for (Iterator it = xmlObj.eContents().iterator(); it.hasNext();) {
			IModelObject child = (IModelObject) it.next();
			createRmcObject(child, umaObj);
		}
	}

	private void createRmcObject(IModelObject xmlElement,
			IModelObject targetContainer) {
		if (xmlElement instanceof MethodElementProperty) {
			return;
		}
		// get the targetContainer's containing feature
		EStructuralFeature feature = xmlElement.eContainmentFeature();
		createRmcObject(xmlElement, targetContainer, feature.getName());
	}

	private void createRmcObject(IModelObject xmlElement,
			IModelObject targetContainer, String containmentXmlFeature) {

		try {
			if (xmlElement == null) {
				return;
			}

			IModelObject umaElement = null;

			// don't create content category package since in uma, it's
			// seperated based on type
			if (xmlElement instanceof MethodPlugin) {
				MethodPlugin plugin = (MethodPlugin) xmlElement;
				umaElement = umaLib.createMethodPlugin(plugin.getId(), plugin
						.getName());
			} else if (xmlElement instanceof ContentCategoryPackage) {
				// don't create, do nothing
				// mark as discarded so we don't need to copy it's attributes
				setDiscarded(((ContentCategoryPackage) xmlElement).getId());

			} else if ((xmlElement instanceof ContentCategory)
					&& !(xmlElement.eContainer() instanceof Domain 
							|| xmlElement.eContainer() instanceof Discipline)
					|| (xmlElement.eContainer() instanceof ContentCategoryPackage)) {
				// create the content categories for root content categories,
				// not for sub-domain and sub-discipline
				// 154143 - import xml did not reserve the sub-discipline structure
				String pluginId = getPlugin(xmlElement).getId();
				umaElement = umaLib.createContentCategory(pluginId, xmlElement
						.eClass().getName(), xmlElement.getType().getName(),
						xmlLib.getElementId(xmlElement));
			} else {

				// if this is root level package, find the corresponding
				// container for the UMA object
				if (xmlElement instanceof MethodPackage
						&& xmlElement.eContainer() instanceof MethodPlugin) {
					if (xmlElement instanceof ContentPackage) {
						targetContainer = umaLib
								.getContentRootPackage(targetContainer);
					} else if (xmlElement instanceof ProcessComponent) {
						Object proc = ((ProcessComponent) xmlElement)
								.getProcess();
						if (proc instanceof CapabilityPattern) {
							targetContainer = umaLib
									.getCapabilityPatternRootPackage(targetContainer);
						} else if (proc instanceof DeliveryProcess) {
							targetContainer = umaLib
									.getDeliveryProcessRootPackage(targetContainer);
						}
					} else if (xmlElement instanceof ProcessPackage) {
						List procs = xmlLib
								.getAllProcesses((MethodPackage) xmlElement);
						if (procs != null && procs.size() > 0) {
							Object proc = procs.get(0);
							if (proc instanceof CapabilityPattern) {
								targetContainer = umaLib
										.getCapabilityPatternRootPackage(targetContainer);
							} else if (proc instanceof DeliveryProcess) {
								targetContainer = umaLib
										.getDeliveryProcessRootPackage(targetContainer);
							}
						}
					}
				}
				
				if (targetContainer instanceof org.eclipse.epf.uma.TaskDescriptor &&
					containmentXmlFeature.equals("step")) {	//$NON-NLS-1$
					return;
				}
				
				// get the containment feature so we can create the object of
				// the same type
				umaElement = umaLib.createElement(targetContainer,
						containmentXmlFeature, xmlElement.eClass().getName(),
						xmlElement.getType().getName(), xmlLib
								.getElementId(xmlElement));
				
				if (xmlElement instanceof TaskDescriptor) {
					TaskDescriptor td = (TaskDescriptor) xmlElement;
					if (td.getTask() != null) {
						List steps = td.getStep();
						if (steps != null && !steps.isEmpty()) {
							if (tdMap == null) {
								tdMap = new HashMap<String, TaskDescriptor>();
							}
							tdMap.put(td.getId(), td);
						}
					}
				}

				// xml WorkOrder does not have an id, need to map the uma Object
				if (xmlElement instanceof WorkOrder) {
					workOrderMap.put(xmlElement, umaElement);
				}
			}

			// recursive,
			// note, uma element might be null since we can't determine the
			// owner of the element yet
			creatEDataObjectTree(xmlElement, umaElement);

		} catch (Exception e) {
			// log the error and continue???
			e.printStackTrace();
		}
	}

	private MethodPlugin getPlugin(EObject element) {
		while ((element != null) && !(element instanceof MethodPlugin)) {
			element = element.eContainer();
		}

		return (MethodPlugin) element;
	}

	private IModelObject getRmcObject(MethodElement xmlObj) {
		if (xmlObj instanceof MethodLibrary) {
			return umaLib.getRoot();
		} else {
			String id = xmlObj.getId();
			return this.umaLib.getElement(id);
		}
	}

	private void iteratEDataObject(MethodElement srcObj) {
		iteratEDataObject_(srcObj);
		IModelObject targetObj = getRmcObject(srcObj);
		if (targetObj instanceof org.eclipse.epf.uma.MethodElement) {
			diagramHandler.registerElement((org.eclipse.epf.uma.MethodElement) targetObj); 
		}
	}
	
	private void iteratEDataObject_(MethodElement srcObj) {

		logger
				.logMessage(" === processing element " + srcObj.getType().getName() + ": " + srcObj.getName()); //$NON-NLS-1$ //$NON-NLS-2$

		if (isProcessed(srcObj.getId())) {
			return;
		}
		setProcessed(srcObj.getId());

		// if object is not created, it's xml wrapper package
		if (srcObj instanceof ContentCategoryPackage) {

			// iterate the child packages
			for (Iterator it = ((ContentCategoryPackage) srcObj)
					.getContentCategory().iterator(); it.hasNext();) {
				iteratEDataObject((MethodElement) it.next());
			}
			return;
		}

		IModelObject targetObj = getRmcObject(srcObj);
		EClass eClass = srcObj.eClass();

		handleDescriptorExtraReferences(srcObj, targetObj);
		
		EList features = eClass.getEAllStructuralFeatures();
		boolean isNewElement = umaLib.isNewElement(srcObj.getId()) || srcObj instanceof MethodLibrary;
		boolean isOldPlugin = !isNewElement && (srcObj instanceof MethodPlugin);
		
		Set<EStructuralFeature> seenRmcFeatures = new HashSet<EStructuralFeature>();
		for (Iterator it = features.iterator(); it.hasNext();) {
			EStructuralFeature feature = (EStructuralFeature) it.next();
						
			if (FeatureManager.INSTANCE.isUnneededXmlFeature(feature.getName())) {
				continue;
			}
			if (isOldPlugin && UmaPackage.eINSTANCE.getMethodPlugin_UserChangeable() == feature) {
				continue;
			}

			Object value = srcObj.eGet(feature);

			try {
				if (value == null) {
					umaLib.handleNullXmlValue(srcObj, targetObj, feature.getName());
					continue;
				}
				
				boolean isMep = feature.getName().equals("methodElementProperty"); //$NON-NLS-1$	
				if (value instanceof List && ! isMep) {
					umaLib.initListValueMerge(srcObj, targetObj, feature.getName(), 
							(List) value, seenRmcFeatures);
					
					for (Iterator itv = ((List) value).iterator(); itv
							.hasNext();) {
						Object src_value = itv.next();						

						if (src_value instanceof String) {

							// it's an id reference
							umaLib.setReferenceValue(targetObj, feature
									.getName(), (String) src_value);
						} else if (src_value instanceof MethodElement) {
							// set the reference
							MethodElement e = (MethodElement) src_value;
							String guid = e.getId();

							// don't set the containment feature value since
							// it's already handled
							// when creating the library structure
							if (!isDiscarded(guid) && e.eContainer() != srcObj) {
								umaLib.setReferenceValue(targetObj, feature
										.getName(), guid);
							}

							iteratEDataObject((MethodElement) src_value);
						} else if (src_value instanceof WorkOrder) {
							// WorkOrder is not method element and does not have
							// an id, need special handling
							// set the prede
							WorkOrder wr = (WorkOrder) src_value;
							Object umaWr = workOrderMap.get(wr);							
							//umaLib.setWorkOrder(umaWr, wr.getValue());
							if (umaWr instanceof org.eclipse.epf.uma.WorkOrder) {
								umaLib.handleWorkOrder(wr, (org.eclipse.epf.uma.WorkOrder) umaWr);
							}
						} else {
							logger
									.logMessage(NLS.bind(ImportXMLResources.importXMLService_element_not_handled, src_value));
						}
					}
				} else if (feature instanceof EReference && ! isMep) {

					// get the xml object and iterate again
					Object src_value = value;

					if (src_value instanceof WorkOrder) {
						logger.logMessage("workorder value"); //$NON-NLS-1$
					} else if (src_value instanceof String
							&& !isDiscarded((String) src_value)) {
						// it's an id reference
						umaLib.setReferenceValue(targetObj, feature.getName(),
								(String) src_value);
					} else if (src_value instanceof MethodElement) {
						// set the reference
						String guid = ((MethodElement) src_value).getId();
						if (guid == null) {
							logger
									.logWarning(NLS.bind(ImportXMLResources.importXMLService_element_without_id, ((MethodElement) src_value)
									.getName()));
						}
						if (!isDiscarded(guid)) {
							umaLib.setReferenceValue(targetObj, feature
									.getName(), guid);
						}
						iteratEDataObject((MethodElement) src_value);
					} else {
						logger
								.logWarning(NLS.bind(ImportXMLResources.importXMLService_element_not_handled, src_value));
					}

				} else {
					if (targetObj instanceof ContentCategoryPackage) {
						logger.logMessage("hrre"); //$NON-NLS-1$
					}

					if (value instanceof WorkOrderType) {
						value = ((WorkOrderType) value).getName();
					}
					
					boolean rename = false;
					if (!isNewElement && feature == org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getNamedElement_Name()) {
						String oldName = (String)targetObj.eGet(org.eclipse.epf.uma.UmaPackage.eINSTANCE.getNamedElement_Name());
						String newName = (String)srcObj.eGet(feature);				
						if (!newName.equals(oldName)) {
							LibraryImportManager.ensureUniqueNameForExistingElement
								((org.eclipse.epf.uma.MethodElement) targetObj, oldName, newName, renameElementMap);
							rename = true;
						}
					}
					if (!rename) {
						umaLib.setAtributeFeatureValue(targetObj,
								feature.getName(), value);
					}
					if (isNewElement && feature == org.eclipse.epf.xml.uma.UmaPackage.eINSTANCE.getNamedElement_Name()) {
						org.eclipse.epf.uma.MethodElement elem = (org.eclipse.epf.uma.MethodElement) targetObj;
						EObject ceobj = elem.eContainer();						
						if (ceobj instanceof org.eclipse.epf.uma.MethodElement && 
								! umaLib.isNewElement(((org.eclipse.epf.uma.MethodElement) ceobj).getGuid())) {
							LibraryImportManager.ensureUniqueName(ceobj, elem, renameElementMap);
						}
					}

				}
			} catch (Exception e) {
				// log the error and continue???
				e.printStackTrace();
			}

		}

	}

	// goid of processed element
	private List processedElements = new ArrayList();

	private boolean isProcessed(String guid) {
		return processedElements.contains(guid);
	}

	private void setProcessed(String guid) {
		if (!processedElements.contains(guid)) {
			processedElements.add(guid);
		}
	}

	private boolean isDiscarded(String id) {
		return discardedElements.contains(id);
	}

	private void setDiscarded(String id) {
		discardedElements.add(id);
	}
	
	/**
	 * Sets overwrite attribute.
	 * @param b
	 */
	public void setOverwrite(boolean b) {
		overwrite = b;
	}	
	
	private String versionCheck(final String xmlPath, final String title) {
		final String[] ret = new String[1];
		ret[0] = null;
		SafeUpdateController.syncExec(new Runnable() {	
			public void run() {				
				VersionUtil.VersionCheckInfo info = VersionUtil.checkXMLVersion(new File(xmlPath));
				if (info == null) {
					ret[0] = NLS.bind(ImportXMLResources.versionMismatch_oldData_unknown, new Object[] {
							Platform.getProduct().getName()});					
				} else {
					if (info.result < 0) {
						try {
							migrator = MappingUtil.getMigratorByLibPath(xmlPath);
						} catch (Exception e) {
							ret[0] = e.toString();
						}
						if (migrator == null && ret[0] == null) {
							if (info.toolID.equals(VersionUtil.getPrimaryToolID())) {
								ret[0] = NLS.bind(ImportXMLResources.versionMismatch_oldData, new Object[] {
										info.toolVersion, Platform.getProduct().getName()});
							} else {
								ret[0] = NLS.bind(ImportXMLResources.versionMismatch_oldData_unknown, new Object[] {
										Platform.getProduct().getName()});					
							}
						}
					} else if (info.result > 0) {
						if (info.toolID.equals(VersionUtil.getPrimaryToolID())) {
							ret[0] = NLS.bind(ImportXMLResources.versionMismatch_oldTool, new Object[] {
								info.toolVersion, Platform.getProduct().getName()});
						} else {
							ret[0] = NLS.bind(ImportXMLResources.versionMismatch_oldTool_unknown, new Object[] {
									Platform.getProduct().getName()});
						}
					} 
				}
				if (ret[0] != null) {
					ImportXMLPlugin.getDefault().getMsgDialog().displayError(title, ret[0]);					
				}
			}
		});		
		return ret[0];
	}
	
	 public void handleTypeChanges() {
		org.eclipse.epf.uma.MethodLibrary baseLib = LibraryService.getInstance()
												.getCurrentMethodLibrary();
		HashMap baseMap = new HashMap();
		ConfigurationImportService.collectPotentialTypeChanged(baseLib, baseMap);
		
		HashMap eClassObjMap = new HashMap();
		HashMap importMap = new HashMap();
		for (Iterator it = xmlLib.getRoot().eAllContents(); it.hasNext();) {
			Object obj = it.next();
			if (obj instanceof Guidance ||
				obj instanceof Activity) {
				MethodElement xmlElement = (MethodElement) obj;
				String eClassName = xmlElement.eClass().getName();
				Object eClassObj = eClassObjMap.get(eClassName);
				if (eClassObj == null) {
					EClass objEClass = FeatureManager.INSTANCE.getRmcEClass(eClassName);
					if (objEClass != null) {
						eClassObj = UmaFactory.eINSTANCE.create(objEClass);
						eClassObjMap.put(eClassName, eClassObj);	
					}
				}
				if (eClassObj != null) {
					importMap.put(xmlElement.getId(), eClassObj);
				}
			}
		}
		
		ConfigurationImportService.handleTypeChanges(baseMap, importMap);	
	}
	 
	private boolean isCheckBasePlugins() {
		return checkBasePlugins;
	}

	public void setCheckBasePlugins(boolean checkBasePlugins) {
		this.checkBasePlugins = checkBasePlugins;
	}

	public void setMergeLevel(int mergeLevel) {
		this.mergeLevel = mergeLevel;
	}
	
	public boolean isSynFreeLib() {
		return xmlLib == null ? false : xmlLib.isSynFreeLib();
	}
	
	private void handleDescriptorExtraReferences(MethodElement srcObj,
			IModelObject targetObj) {
		try {
			handleDescriptorExtraReferences_(srcObj, targetObj);
		} catch (Exception e) {
			logger.logError(e.getMessage(), e);
		}
	}
	
	private void handleDescriptorExtraReferences_(MethodElement srcObj,
			IModelObject targetObj) {
		if (!(targetObj instanceof org.eclipse.epf.uma.Descriptor)) {
			return;
		}

		boolean ok = srcObj instanceof org.eclipse.epf.xml.uma.RoleDescriptor
				|| srcObj instanceof org.eclipse.epf.xml.uma.WorkProductDescriptor
				|| srcObj instanceof org.eclipse.epf.xml.uma.TaskDescriptor;

		if (!ok) {
			return;
		}

		org.eclipse.epf.uma.Descriptor tgtDes = (org.eclipse.epf.uma.Descriptor) targetObj;
		BreakdownElement srcDes = (BreakdownElement) srcObj;

		EList<MethodElementProperty> mepPropList = srcDes.getMethodElementProperty();
		if (mepPropList == null || mepPropList.isEmpty()) {
			return;
		}

		for (int i = mepPropList.size() - 1; i >=0; i--) {
			MethodElementProperty mep = mepPropList.get(i);
			if (mep.getName().startsWith("XML_")) {		//$NON-NLS-1$
				handleExtraRef(srcDes, tgtDes, mep);
				mepPropList.remove(i);
			}
		}				

	}
	
	private void handleExtraRef(BreakdownElement srcDes,
			org.eclipse.epf.uma.Descriptor tgtDes, MethodElementProperty mep) {
		String refName = mep.getName().substring(4);
		EStructuralFeature feature = tgtDes.eClass().getEStructuralFeature(
				refName);
		if (feature instanceof EReference) {
			EReference ref = (EReference) feature;
			if (ref.isMany()) {
				List<org.eclipse.epf.uma.MethodElement> list = (List<org.eclipse.epf.uma.MethodElement>) tgtDes
						.eGet(ref);
				list.clear();
				String[] guidStrList = mep.getValue().split(
						DescriptorPropUtil.infoSeperator);
				if (guidStrList == null || guidStrList.length == 0) {
					return;
				}
				for (String guid : guidStrList) {
					org.eclipse.epf.uma.MethodElement element = (org.eclipse.epf.uma.MethodElement) umaLib
							.getElement(guid);
					if (element != null) {
						list.add(element);
					}
				}

			}
		}
	}
	
}
