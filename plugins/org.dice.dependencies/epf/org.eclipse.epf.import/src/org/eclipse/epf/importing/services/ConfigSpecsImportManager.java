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
import java.util.Iterator;
import java.util.List;

import org.eclipse.epf.export.services.ConfigurationSpec;
import org.eclipse.epf.export.services.LibraryDocument;
import org.eclipse.epf.importing.ImportPlugin;
import org.eclipse.epf.library.ILibraryManager;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.LibraryServiceUtil;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.persistence.MultiFileSaveUtil;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodElementProperty;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.UmaFactory;
import org.w3c.dom.Element;

/**
 * Manages the import of library configuration specifications.
 * 
 * @author Jinhua Xi
 * @since 1.0
 */
public class ConfigSpecsImportManager {

	/**
	 * Creates a new instance.
	 */
	ConfigSpecsImportManager() {
	}

	/**
	 * Returns the config specs in the document.
	 */
	public ConfigSpecs getConfigSpecs(final LibraryDocument document) {
		final ConfigSpecs configSpecs = new ConfigSpecs();

		// Remove the unneeded configurations.
		File configDir = new File(document.getFile().getParent(), MultiFileSaveUtil.METHOD_CONFIGURATION_FOLDER_NAME);			
		LibraryDocument.ConfigDocVisitor visitor = new LibraryDocument.ConfigDocVisitor() {
			public void visit(File file, Element node) {
				ConfigSpecs.Entry entry = configSpecs.newEntry();
				entry.configSpec = document.getConfigurationSpec(node);
				entry.existingConfig = getExistingConfig(entry.configSpec.guid);
			}
		};			
		LibraryDocument.visitConfigFiles(configDir, visitor);			
		return configSpecs;
	}

	private MethodConfiguration getExistingConfig(String guid) {
		MethodConfiguration[] configs = LibraryServiceUtil
				.getMethodConfigurations(LibraryService.getInstance()
						.getCurrentMethodLibrary());
		if (configs == null || configs.length == 0) {
			return null;
		}

		for (int i = 0; i < configs.length; i++) {
			MethodConfiguration config = configs[i];
			if (config.getGuid().equals(guid)) {
				return config;
			}
		}

		return null;
	}

	/**
	 * Imports the selected library configuration specifications.
	 * 
	 * @param specs
	 *            A library configuration object.
	 */
	public void doImport(ConfigSpecs specs) {
		try {
			MethodLibrary lib = LibraryService.getInstance()
					.getCurrentMethodLibrary();

			LibraryUtil.loadAll(lib);

			for (Iterator it = specs.iterator(); it.hasNext();) {
				ConfigSpecs.Entry entry = (ConfigSpecs.Entry) it.next();
				if (entry.selected) {
					MethodConfiguration config = createConfig(entry.configSpec);

					if (entry.existingConfig != null) {
						// Merge the new plug-ins and packages into the existing
						// one,
						// anything in the current configuation should be kept.
						// EcoreUtil.replace(entry.existingConfig, config);
						List plugins = entry.existingConfig
								.getMethodPluginSelection();
						List pkgs = entry.existingConfig
								.getMethodPackageSelection();
						List views = entry.existingConfig.getProcessViews();
						List added = entry.existingConfig.getAddedCategory();
						List substracted = entry.existingConfig.getSubtractedCategory();

						for (Iterator itp = config.getMethodPluginSelection()
								.iterator(); itp.hasNext();) {
							Object e = itp.next();
							if (!plugins.contains(e)) {
								plugins.add(e);
							}
						}

						for (Iterator itp = config.getMethodPackageSelection()
								.iterator(); itp.hasNext();) {
							Object e = itp.next();
							if (!pkgs.contains(e)) {
								pkgs.add(e);
							}
						}

						for (Iterator itp = config.getProcessViews().iterator(); itp
								.hasNext();) {
							Object e = itp.next();
							if (!views.contains(e)) {
								views.add(e);
							}
						}
						
						for (Iterator itp = config.getAddedCategory().iterator(); itp
								.hasNext();) {
							Object e = itp.next();
							if (!added.contains(e)) {
								added.add(e);
							}
						}
						
						for (Iterator itp = config.getSubtractedCategory()
								.iterator(); itp.hasNext();) {
							Object e = itp.next();
							if (!substracted.contains(e)) {
								substracted.add(e);
							}
						}
						
						if (config.getDefaultView() != null && config.getDefaultView() != entry.existingConfig.getDefaultView()) {
							entry.existingConfig.setDefaultView(config.getDefaultView());
						}
						
						setMepFeatureValue(
								entry.existingConfig,
								entry.existingConfig.getMethodElementProperty(),
								config.getMethodElementProperty());
						
					} else {
						// Add the configuration.
						lib.getPredefinedConfigurations().add(config);
					}
				}
			}

			LibraryUtil.saveLibrary(lib, false, false);

		} catch (Exception e) {
			ImportPlugin.getDefault().getLogger().logError(e);
		}

	}

	private MethodConfiguration createConfig(ConfigurationSpec spec) {
		MethodConfiguration config = UmaFactory.eINSTANCE
				.createMethodConfiguration();

		// Set the attributes.
		config.setName(spec.name);
		config.setBriefDescription(spec.brief_desc);
		config.setGuid(spec.guid);
		config.getMethodElementProperty().addAll(spec.mepList);

		List plugins = config.getMethodPluginSelection();
		List pkgs = config.getMethodPackageSelection();
		List views = config.getProcessViews();
		List added = config.getAddedCategory();
		List substracted = config.getSubtractedCategory();

		ILibraryManager manager = LibraryService.getInstance()
				.getCurrentLibraryManager();
		if (manager != null) {
			for (Iterator it = spec.pluginIds.iterator(); it.hasNext();) {
				String guid = (String) it.next();
				Object element = manager.getMethodElement(guid);
				if (element != null && !plugins.contains(element)) {
					plugins.add(element);
				}
			}
			
			for (Iterator it = spec.packageIds.iterator(); it.hasNext();) {
				String guid = (String) it.next();
				Object element = manager.getMethodElement(guid);
				if (element != null && !pkgs.contains(element)) {
					pkgs.add(element);
				}
			}

			for (Iterator it = spec.viewIds.iterator(); it.hasNext();) {
				String guid = (String) it.next();
				Object element = manager.getMethodElement(guid);
				if (element != null && !views.contains(element)) {
					views.add(element);
				}
			}
			
			for (Iterator it = spec.addedCCIds.iterator(); it.hasNext();) {
				String guid = (String) it.next();
				Object element = manager.getMethodElement(guid);
				if (element != null && !added.contains(element)) {
					added.add(element);
				}
			}
			
			for (Iterator it = spec.substractCCIds.iterator(); it.hasNext();) {
				String guid = (String) it.next();
				Object element = manager.getMethodElement(guid);
				if (element != null && !substracted.contains(element)) {
					substracted.add(element);
				}
			}
			
			if (spec.defaultView != null) {
				Object element = manager.getMethodElement(spec.defaultView);
				if (element instanceof CustomCategory) {
					config.setDefaultView((CustomCategory) element);
				}
			}
		}

		return config;
	}
	
	private static void setMepFeatureValue(MethodElement element, List oldValue, List newValue) {
		int sz = newValue.size();
		if (oldValue.size() == sz) {
			if (sz == 0) {
				return;
			}
			boolean same = true;
			for (int i=0; i < sz; i++) {
				MethodElementProperty oldMep = (MethodElementProperty) oldValue.get(i);
				MethodElementProperty newMep = (MethodElementProperty) newValue.get(i);
				if (! oldMep.getName().equals(newMep.getName()) ||
					! oldMep.getValue().equals(newMep.getValue())) {
					same = false;
					break;
				}
			}
			if (same) {
				return;
			}					
		}
		oldValue.removeAll(oldValue);
		oldValue.addAll(newValue);		
	}

}
