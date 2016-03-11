//------------------------------------------------------------------------------
// Copyright (c) 2005, 2007 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.library.ui.xmi.internal.migration;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.epf.common.utils.FileUtil;
import org.eclipse.epf.common.utils.NetUtil;
import org.eclipse.epf.library.edit.util.ExtensionManager;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.layout.LinkInfo;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.library.util.ResourceUtil;
import org.eclipse.epf.library.xmi.IDiagramMigration;
import org.eclipse.epf.library.xmi.XMILibraryPlugin;
import org.eclipse.epf.library.xmi.XMILibraryResources;
import org.eclipse.epf.persistence.MultiFileResourceSetImpl;
import org.eclipse.epf.persistence.migration.UpgradeCallerInfo;
import org.eclipse.epf.persistence.util.PersistenceResources;
import org.eclipse.epf.persistence.util.PersistenceUtil;
import org.eclipse.epf.uma.CapabilityPattern;
import org.eclipse.epf.uma.ContentDescription;
import org.eclipse.epf.uma.DeliveryProcess;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.Example;
import org.eclipse.epf.uma.Guidance;
import org.eclipse.epf.uma.GuidanceDescription;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.ReusableAsset;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.Whitepaper;
import org.eclipse.epf.uma.util.UmaUtil;

/**
 * @author Weiping Lu - Feb 12, 2007
 * @since 1.2
 */
public class Migrator103 extends MigratorBase {
	
	private Map<String, Process> diagramElemMap;
	private static boolean xmlMigrate = false;
	
	public void migrate(String libPath, IProgressMonitor monitor) throws Exception {
		migrate(libPath, monitor, null);
	}
	
	public void migrate(String libPath, IProgressMonitor monitor, UpgradeCallerInfo info) throws Exception {
		setCallerInfo(info);
		
		File libFile = new File(libPath);
		
		boolean toVerify = true;
		if (info != null && info.getIsExportedPluginLib()) {
			toVerify = false;
		}
		xmlMigrate = false;
		ResourceUtil.open(libFile.getParent(), monitor);

		MultiFileResourceSetImpl resourceSet = null;
		
		diagramElemMap = new LinkedHashMap<String, Process>();
		try {
			// load the library
			//
			updateStatus(monitor, PersistenceResources.loadLibraryTask_name);

			if (toVerify) {
				resourceSet = new MultiFileResourceSetImpl(false);
			} else {
				resourceSet = PersistenceUtil.getImportPluginResourceSet();
			}
						
			resourceSet.getLoadOptions().put(
					XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);

			MethodLibrary lib = resourceSet.loadLibrary(libPath);
						
			LinkInfo.setLibrary(lib);
			// load all elements in memory
			//
			updateStatus(monitor, PersistenceResources.loadResourcesTask_name);
			
			updateAllContents(monitor, lib);					
			
			// save all files
			//
			updateStatus(monitor, PersistenceResources.saveLibraryTask_name);
			Map saveOptions = resourceSet.getDefaultSaveOptions();
			resourceSet.save(saveOptions, true);

			updateStatus(monitor,
					PersistenceResources.refreshLibraryFilesTask_name);
			
			
			ResourceUtil.refreshResources(lib, monitor);
			
			diagramMigrate(diagramElemMap, monitor);	
			
			ResourceUtil.refreshResources(lib, monitor);
			
		} finally {
			LinkInfo.setLibrary(null);
			
			if (resourceSet != null) {
				resourceSet.reset();
				resourceSet = null;
			}
		}

	}

	protected static void diagramMigrate(Map<String, Process> diagramElemMap, IProgressMonitor monitor) {
		// Convert diagrams 
		updateStatus(monitor, PersistenceResources.migratingDiagram_name);
		
		IDiagramMigration idm = (IDiagramMigration) ExtensionManager.getExtension("org.eclipse.epf.library.xmi", "diagramMigration"); 			//$NON-NLS-1$ //$NON-NLS-2$
		if (idm != null && !diagramElemMap.isEmpty()) {
			try {
				idm.migrate(diagramElemMap.values());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	protected void updateElement(MethodElement element, IProgressMonitor monitor) throws Exception {
		updateElement(element, diagramElemMap, monitor);
	}

	protected static void updateElement(MethodElement element, Map<String, Process> diagramElemMap, IProgressMonitor monitor) throws Exception {
		if (diagramElemMap != null) {
			if (element instanceof CapabilityPattern ||
				element instanceof DeliveryProcess) {
				Process process = (Process) element;
				if (process.eContainer() instanceof ProcessComponent) {
					String guid = element.getGuid();
					Process proc = diagramElemMap.get(guid);
					if (proc == null) {
						diagramElemMap.put(element.getGuid(), (Process) element);
					} else {
						assert(proc == element);
					}
				}
			}
		}
		MigrationUtil.formatValue(element);
		handleTypeConvert(element);
		if (! xmlMigrate) {
			handleShapeAndNodeIcons(element);
		}
	}
	
	private static void handleTypeConvert(MethodElement element) throws Exception {
		if (	element instanceof Example 			||
				element instanceof ReusableAsset 	||
				element instanceof Whitepaper) {			
			Guidance guidance = (Guidance) element;
			ContentDescription pres = convert(guidance.getPresentation());
			if (pres != null) {
				guidance.setPresentation(pres);
			}			
		}
	}
	
	private static GuidanceDescription convert(ContentDescription oldObj) {
		if (oldObj == null) {
			return null;
		}
		GuidanceDescription newObj = UmaFactory.eINSTANCE.createGuidanceDescription();		
		Resource res = oldObj.eResource();
		if (res != null) {
			res.getContents().remove(oldObj);
			res.getContents().add(newObj);
		}
		List features = LibraryUtil.getStructuralFeatures(oldObj);
//		List properties = oldObj.getInstanceProperties();		
		if (features != null) {
			for (int i = 0; i < features.size(); i++) {
				EStructuralFeature feature = (EStructuralFeature) features.get(i);
				Object value = oldObj.eGet(feature);
				newObj.eSet(feature, value);
			}
		}
				
		return newObj;
	}
	
	private static void handleShapeAndNodeIcons(MethodElement element) throws Exception {
		if (element instanceof DescribableElement) {			
			DescribableElement describableElement = (DescribableElement) element;
			URI fixedURI = fixIconURI(describableElement, describableElement.getShapeicon());
			if (fixedURI != null) {
				describableElement.setShapeicon(fixedURI);
			}
			fixedURI = fixIconURI(describableElement, describableElement.getNodeicon());
			if (fixedURI != null) {
				describableElement.setNodeicon(fixedURI);
			}
		}
	}

	private static URI fixIconURI(DescribableElement e, URI uri) {
		if (uri == null) return null;
		
		File path = new File(NetUtil.decodedFileUrl(uri.toString()));
		Stack<String> stack = new Stack<String>();
		File temp = path;
		String resultPath = ""; //$NON-NLS-1$
		while (temp != null) {
			stack.push(temp.getName());
			temp = temp.getParentFile();
		}
		if (!stack.isEmpty()) {
			// now skip first stack entry and reconstruct path
			String pluginName = stack.pop();
			while (!stack.isEmpty()) {
				if (resultPath.length() > 0) {
					resultPath += '/';
				}
				resultPath = resultPath + (String)stack.pop();
			}

			MethodPlugin plugin = UmaUtil.getMethodPlugin(e);
			try {
				URI resultURI = new URI(resultPath);
				if (!pluginName.equals(plugin.getName())) {
					// uri was refering a file in another plugin
					File libRoot = TngUtil.getLibraryRootPath(e);
					File oldFile = new File(libRoot, NetUtil.decodedFileUrl(uri.toString()));
					File newFile = new File(libRoot, plugin.getName() + File.separator + NetUtil.decodedFileUrl(resultURI.toString()));
					if (!newFile.exists()) {
	      					FileUtil.copyFile(oldFile, newFile);
					}
				}
				return resultURI;
			} catch (URISyntaxException ex) {
				XMILibraryPlugin.getDefault().getLogger().logError(ex);
				return null;
			}
		}
		return null;
	}
	
	
	public void migrateXmlImportedLib(MethodLibrary lib, IProgressMonitor monitor) throws Exception {
		xmlMigrate = true;
		updateStatus(monitor, XMILibraryResources.migrateXMLLibrary_taskName);
		updateAllContents(monitor, lib);
		LibraryUtil.saveLibrary(lib, false, false);
		xmlMigrate = false;
	}

}
