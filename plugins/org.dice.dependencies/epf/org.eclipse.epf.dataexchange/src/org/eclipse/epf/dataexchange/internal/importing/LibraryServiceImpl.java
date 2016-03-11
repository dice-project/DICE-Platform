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
package org.eclipse.epf.dataexchange.internal.importing;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.dataexchange.importing.LibraryService;
import org.eclipse.epf.dataexchange.importing.PluginService;
import org.eclipse.epf.library.util.ModelStorage;
import org.eclipse.epf.persistence.MethodLibraryPersister;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.UmaFactory;

/**
 * Library service for importing external library/plugins into the current
 * library.
 * 
 * @author Jinhua Xi
 * @since 1.0
 * 
 * TODO: Merge with org.eclipse.epf.library.LibraryService
 */
public class LibraryServiceImpl implements LibraryService {

	public LibraryServiceImpl() {
	}

	public MethodPlugin createPlugin(String name, String guid) throws Exception {
		return createPlugin(name, guid, null);
	}
		
	public MethodPlugin createPlugin(String name, String guid, Map options) throws Exception {	
		MethodPlugin plugin = UmaFactory.eINSTANCE.createMethodPlugin();
		plugin.setName(name);
		plugin.setGuid(guid);

		// initialize the plugin
		plugin = ModelStorage.initialize(plugin);

		// MUST save the plugin before process anything else
		// this method call will cause resource refresh which is not desired in
		// the importing process
		MethodLibrary library = org.eclipse.epf.library.LibraryService
				.getInstance().getCurrentMethodLibrary();
				
		library.getMethodPlugins().add(plugin);
		
		if (options != null) {
			Map renameElementMap = (Map) options.get("renameElementMap");//$NON-NLS-1$
			if (renameElementMap != null) {
				ensureUniqueName(library, plugin, renameElementMap);	
			}
		}
		
		// 150063 - XML import: CP/DPs are unresovled after XML plugin import 
		// need to save tht library and plugin resources specifically, can't use saveLibrary
//		LibraryUtil.saveLibrary(library, false, false);
		MethodLibraryPersister.INSTANCE.save(library.eResource());
		MethodLibraryPersister.INSTANCE.save(plugin.eResource());

		// still mark the resource as dirty
		library.eResource().setModified(true);
		plugin.eResource().setModified(true);
		return plugin;
	}

	/**
	 * @see org.eclipse.epf.dataexchange.importing.LibraryService#createConfiguration(String, String)
	 */
	public MethodConfiguration createConfiguration(String name, String guid) {
		MethodConfiguration config = UmaFactory.eINSTANCE
				.createMethodConfiguration();
		config.setName(name);
		config.setGuid(guid);

		MethodLibrary library = org.eclipse.epf.library.LibraryService
				.getInstance().getCurrentMethodLibrary();
		boolean oldNotify = library.eDeliver();
		try {
			library.eSetDeliver(false);
			library.getPredefinedConfigurations().add(config);
		} finally {
			library.eSetDeliver(oldNotify);
		}

		return config;
	}

	/**
	 * create a plugin service for the specified plugin
	 * 
	 * @param plugin
	 *            MethodPlugin
	 * @return PluginService
	 */
	public PluginService createPluginService(MethodPlugin plugin) {
		return new PluginServiceImpl(plugin);
	}
	
	//This is identical to LibraryImportManager.ensureUniqueName
	//Should move to this code to a commom place so that both packages can access it.
	private static void ensureUniqueName(EObject owner, MethodElement newObj, Map renameElementMap) {
		if (owner == null) {
			return;
		}
		Class cls = newObj.getClass();
		Map nameMap = new HashMap();
		for (int i=0; i < owner.eContents().size(); i++) {
			Object oldObj = owner.eContents().get(i);
			if (oldObj.getClass() == cls && oldObj != newObj) {
				MethodElement oldElem = (MethodElement) oldObj;
				nameMap.put(oldElem.getName(), oldElem);
			}
		}
		String name = newObj.getName();
		String renamed = name;
		while (nameMap.containsKey(renamed)) {
			renamed += "_renamed"; //$NON-NLS-1$
		}
		if (renamed != name) {
			newObj.setName(renamed);
			
			Object[] entryVal = new Object[4];
			entryVal[0] = newObj;
			entryVal[1] = name;
			entryVal[2] = nameMap.get(name);
			entryVal[3] = renamed;
			renameElementMap.put(newObj.getGuid(), entryVal);			
		}
	}
}