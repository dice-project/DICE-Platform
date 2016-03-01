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
package org.eclipse.epf.library;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.library.configuration.ConfigurationHelper;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.configuration.MethodConfigurationElementList;
import org.eclipse.epf.library.edit.util.ModelStructure;
import org.eclipse.epf.library.edit.util.ProcessScopeUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.persistence.ILibraryResourceSet;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.library.util.ModelStorage;
import org.eclipse.epf.services.ILibraryPersister;
import org.eclipse.epf.uma.CapabilityPattern;
import org.eclipse.epf.uma.Checklist;
import org.eclipse.epf.uma.DeliveryProcess;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPackage;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.ProcessFamily;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.util.Scope;
import org.eclipse.epf.uma.util.UmaUtil;

/**
 * Helper utilities for accessing and managing a method library.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class LibraryServiceUtil {

	// A checklist filter.
	private static final IFilter CHECKLIST_FILTER = new IFilter() {
		public boolean accept(Object obj) {
			return obj instanceof Checklist;
		}
	};

	/**
	 * Gets the absolute path to a method library.
	 * 
	 * @param library
	 *            a method library
	 * @return an absolute path to the method library
	 */
	public static String getMethodLibraryPath(MethodLibrary library) {
		if (library == null) {
			return null;
		}
		Resource resource = library.eResource();
		if (resource != null) {
			URI resourceURI = resource.getURI();
			if (resourceURI != null && resourceURI.isFile()) {
				String path = resourceURI.toFileString();
				return new File(path).getParentFile().getAbsolutePath();
			}
		}
		return null;
	}

	/**
	 * Gets the absolute path to the current method library.
	 * 
	 * @param library
	 *            a method library
	 * @return an absolute path to the method library
	 */
	public static String getCurrentMethodLibraryPath() {
		return getMethodLibraryPath(LibraryService.getInstance()
				.getCurrentMethodLibrary());
	}

	/**
	 * Gets the parent method library of a method element.
	 * 
	 * @param element
	 *            a method element
	 * @return a method library
	 */
	public static MethodLibrary getMethodLibrary(MethodElement element) {
		for (EObject obj = element; obj != null; obj = obj.eContainer()) {
			if (obj instanceof MethodLibrary) {
				return (MethodLibrary) obj;
			}
		}
		return null;
	}

	/**
	 * Gets a specific method configuration in a method library.
	 * 
	 * @param library
	 *            a method library
	 * @param name
	 *            the method configuration name
	 * @return a method configuration
	 */
	public static MethodConfiguration getMethodConfiguration(
			MethodLibrary library, String name) {
		if (library != null) {
			MethodConfiguration config;
			List<MethodConfiguration> configs = library
					.getPredefinedConfigurations();
			for (Iterator<MethodConfiguration> it = configs.iterator(); it
					.hasNext();) {
				config = it.next();
				if (!(config instanceof ProcessFamily)
						&& config.getName().equals(name)) {
					return config;
				}
			}
		}
		return null;
	}

	/**
	 * Gets all the method configurations in a method library.
	 * 
	 * @param library
	 *            a method library
	 * @return an array of method configurations
	 */
	public static MethodConfiguration[] getMethodConfigurations(
			MethodLibrary library) {
		List<MethodConfiguration> configs;
		if (library != null) {
			MethodConfiguration config;
			configs = new ArrayList<MethodConfiguration>(library
					.getPredefinedConfigurations());
			for (Iterator<MethodConfiguration> i = configs.iterator(); i
					.hasNext();) {
				config = i.next();
				if (config == null || config instanceof ProcessFamily) {
					i.remove();
				}
			}
		} else {
			configs = Collections.EMPTY_LIST;
		}
		MethodConfiguration[] result = new MethodConfiguration[configs.size()];
		configs.toArray(result);
		return result;
	}

	/**
	 * Gets the names of all method configurations in a method library.
	 * 
	 * @param library
	 *            a method library
	 * @param name
	 *            the method configuration name
	 * @return an array of method configuration names
	 */
	public static String[] getMethodConfigurationNames(MethodLibrary library) {
		List<String> configNames = new ArrayList<String>();
		if (library != null) {
			MethodConfiguration config;
			List<MethodConfiguration> configs = library
					.getPredefinedConfigurations();
			for (Iterator<MethodConfiguration> i = configs.iterator(); i
					.hasNext();) {
				config = (MethodConfiguration) i.next();
				if (!(config instanceof ProcessFamily)) {
					configNames.add(config.getName());
				}
			}
		}
		String[] result = new String[configNames.size()];
		configNames.toArray(result);
		return result;
	}

	/**
	 * Gets all the processes in the current method library.
	 * 
	 * @retun a collection of <code>CapabilityPattern</code> and
	 *        <code>DeliveryProcess</code>
	 */
	public static Map<String, Process> getProcesses() {
		MethodLibrary library = LibraryService.getInstance()
				.getCurrentMethodLibrary();
		if (library == null) {
			return null;
		}

		Map<String, Process> result = new TreeMap<String, Process>();
		List<MethodPlugin> methodPlugins = LibraryUtil
				.getMethodPlugins(library);
		for (Iterator<MethodPlugin> i = methodPlugins.iterator(); i.hasNext();) {
			MethodPlugin methodPlugin = i.next();
			String capabilityPatternPaths[] = ModelStructure.DEFAULT.capabilityPatternPath;
			MethodPackage methodPackage = UmaUtil.findMethodPackage(
					methodPlugin, capabilityPatternPaths);
			if (methodPackage instanceof ProcessPackage) {
				if (methodPackage instanceof ProcessPackage) {
					getCapabilityPatterns((ProcessPackage) methodPackage,
							result);
				}
			}
			String deliveryProcessPaths[] = ModelStructure.DEFAULT.deliveryProcessPath;
			methodPackage = UmaUtil.findMethodPackage(methodPlugin,
					deliveryProcessPaths);
			if (methodPackage instanceof ProcessPackage) {
				if (methodPackage instanceof ProcessPackage) {
					getDeliveryProcesses((ProcessPackage) methodPackage, result);
				}
			}
		}
		return result;
	}

	/**
	 * Gets all the capability patterns in the current method library.
	 * 
	 * @retun a collection of <code>CapabilityPattern</code>
	 */
	public static Map<String, Process> getCapabilityPatterns() {
		MethodLibrary library = LibraryService.getInstance()
				.getCurrentMethodLibrary();
		if (library == null) {
			return null;
		}

		Map<String, Process> result = new TreeMap<String, Process>();
		List<MethodPlugin> methodPlugins = LibraryUtil.getMethodPlugins(library);
		for (Iterator<MethodPlugin> i = methodPlugins.iterator(); i.hasNext();) {
			MethodPlugin methodPlugin = i.next();
			String capabilityPatternPaths[] = ModelStructure.DEFAULT.capabilityPatternPath;
			MethodPackage methodPackage = UmaUtil.findMethodPackage(
					methodPlugin, capabilityPatternPaths);
			if (methodPackage instanceof ProcessPackage) {
				getCapabilityPatterns((ProcessPackage) methodPackage, result);
			}
		}
		return result;
	}

	/**
	 * Gets all the capability patterns in a method plug-in.
	 * 
	 * @param plugin
	 *            a method plug-in
	 * @retun a collection of <code>CapabilityPattern</code>
	 */
	public static List<CapabilityPattern> getCapabilityPatterns(
			MethodPlugin plugin) {
		List<CapabilityPattern> capabilityPatterns = new ArrayList<CapabilityPattern>();
		List<Process> items = TngUtil.getAllProcesses(plugin);
		for (Iterator<Process> it = items.iterator(); it.hasNext();) {
			Process process = it.next();
			if (process instanceof CapabilityPattern) {
				capabilityPatterns.add((CapabilityPattern) process);
			}
		}
		return capabilityPatterns;
	}

	/**
	 * Gets all the delivery processes in the current method library.
	 * 
	 * @retun a collection of <code>DeliveryProcess</code>
	 */
	public static Map<String, Process> getDeliveryProcesses() {
		MethodLibrary library = LibraryService.getInstance()
				.getCurrentMethodLibrary();
		if (library == null) {
			return null;
		}

		Map<String, Process> result = new TreeMap<String, Process>();
		List<MethodPlugin> methodPlugins = LibraryUtil.getMethodPlugins(library);
		for (Iterator<MethodPlugin> i = methodPlugins.iterator(); i.hasNext();) {
			MethodPlugin methodPlugin = i.next();
			String deliveryProcessPaths[] = ModelStructure.DEFAULT.deliveryProcessPath;
			MethodPackage methodPackage = UmaUtil.findMethodPackage(
					methodPlugin, deliveryProcessPaths);
			if (methodPackage instanceof ProcessPackage) {
				getDeliveryProcesses((ProcessPackage) methodPackage, result);
			}
		}
		return result;
	}

	/**
	 * Gets all the delivery processes in a method plug-in.
	 * 
	 * @param plugin
	 *            a method plug-in
	 * @retun a collection of <code>DeliveryProcess</code>
	 */
	public static List<DeliveryProcess> getDeliveryProcesses(MethodPlugin plugin) {
		List<DeliveryProcess> deliveryProcesses = new ArrayList<DeliveryProcess>();
		List<Process> items = TngUtil.getAllProcesses(plugin);
		for (Iterator<Process> it = items.iterator(); it.hasNext();) {
			Process process = it.next();
			if (process instanceof DeliveryProcess) {
				deliveryProcesses.add((DeliveryProcess) process);
			}
		}
		return deliveryProcesses;
	}

	/**
	 * Gets all the capability patterns in a process package.
	 * 
	 * @param processPackage
	 *            a process package
	 * @param result
	 *            a map
	 * @return an updated map containing a collection of
	 *         <code>CapabilityPattern</code>
	 */
	protected static void getCapabilityPatterns(ProcessPackage processPackage,
			Map<String, Process> result) {
		List<MethodPackage> childPackages = processPackage.getChildPackages();
		for (Iterator<MethodPackage> i = childPackages.iterator(); i.hasNext();) {
			MethodPackage pkg = i.next();
			if (pkg instanceof ProcessComponent) {
				ProcessComponent processComponent = (ProcessComponent) pkg;
				org.eclipse.epf.uma.Process process = processComponent
						.getProcess();
				if (process instanceof CapabilityPattern) {
					String name = process.getName();
					result.put(name, process);
				}
			} else if (pkg instanceof ProcessPackage) {
				getCapabilityPatterns((ProcessPackage) pkg, result);
			}
		}
	}

	/**
	 * Gets all the delivery processes in a process package.
	 * 
	 * @param processPackage
	 *            a process package
	 * @param result
	 *            a map
	 * @return an updated map containing a collection of
	 *         <code>DeliveryProcess</code>
	 */
	protected static void getDeliveryProcesses(ProcessPackage processPackage,
			Map<String, Process> result) {
		List<MethodPackage> childPackages = processPackage.getChildPackages();
		for (Iterator<MethodPackage> i = childPackages.iterator(); i.hasNext();) {
			MethodPackage pkg = i.next();
			if (pkg instanceof ProcessComponent) {
				ProcessComponent processComponent = (ProcessComponent) pkg;
				org.eclipse.epf.uma.Process process = processComponent
						.getProcess();
				if (process instanceof DeliveryProcess) {
					String name = process.getName();
					result.put(name, process);
				}
			} else if (pkg instanceof ProcessPackage) {
				getDeliveryProcesses((ProcessPackage) pkg, result);
			}
		}
	}

	/**
	 * Gets the names of all process families in a method library.
	 * 
	 * @param library
	 *            a method library
	 * @return an array of process family names
	 */
	public static String[] getProcessFamilyNames(MethodLibrary library) {
		List<String> processFamilyNames = new ArrayList<String>();
		if (library != null) {
			MethodConfiguration config;
			List<MethodConfiguration> configs = library.getPredefinedConfigurations();
			for (Iterator<MethodConfiguration> i = configs.iterator(); i.hasNext();) {
				config = (MethodConfiguration) i.next();
				if (config instanceof ProcessFamily) {
					processFamilyNames.add(config.getName());
				}
			}
		}

		String[] result = new String[processFamilyNames.size()];
		processFamilyNames.toArray(result);

		return result;
	}

	/**
	 * Gets the names of all contexts (method configurations) assigned to a
	 * process.
	 * 
	 * @param process
	 *            a capability pattern or delivery process
	 * @return an array of context (method configuration) names
	 */
	public static String[] getContexts(Process process) {
		List<String> contextNames = new ArrayList<String>();
		if (process != null) {
			Scope scope = ProcessScopeUtil.getInstance().loadScope(process);

			List<MethodConfiguration> contexts = process.getValidContext();
			for (Iterator<MethodConfiguration> it = contexts.iterator(); it.hasNext();) {
				MethodConfiguration context = it.next();
				contextNames.add(context.getName());
			}

			if (scope != null) {
				MethodPlugin plugin = UmaUtil.getMethodPlugin(process);
				MethodLibrary lib = UmaUtil.getMethodLibrary(plugin);
				for (MethodConfiguration config : lib.getPredefinedConfigurations()) {
					if (ConfigurationHelper.inConfig(process, config)) {
						contextNames.add(config.getName());
					}
				}
			}			
		}
		String[] result = new String[contextNames.size()];
		contextNames.toArray(result);
		return result;
	}

	/**
	 * Gets all the method configurations assigned to a process.
	 * 
	 * @param process
	 *            a capability pattern or delivery process
	 * @return a collection of <code>MethodConfiguration</code>
	 */
	public static Map<String, MethodConfiguration> getMethodConfigurations(Process process) {
		if (process == null) {
			return null;
		}

		Map<String, MethodConfiguration> result = new TreeMap<String, MethodConfiguration>();
		MethodConfiguration defaultContext = process.getDefaultContext();
		if (defaultContext != null) {
			result.put(defaultContext.getName(), defaultContext);
		}

		List<MethodConfiguration> contexts = process.getValidContext();
		for (Iterator<MethodConfiguration> i = contexts.iterator(); i.hasNext();) {
			MethodConfiguration context = i.next();
			if (context != null) {
				result.put(context.getName(), context);
			}
		}

		return result;
	}

	/**
	 * Gets all the checklists in a method configuration.
	 * 
	 * @param config
	 *            a method configuration
	 * @return a collection of <code>Checklist</code>
	 */
	public static Map<String, Checklist> getChecklists(
			MethodConfiguration config) {
		if (config == null) {
			return null;
		}

		Map<String, Checklist> result = new HashMap<String, Checklist>();

		ArrayList<IFilter> filterList = new ArrayList<IFilter>();
		filterList.add(CHECKLIST_FILTER);
		MethodConfigurationElementList elementList = new MethodConfigurationElementList(
				config, filterList);
		List elements = elementList.getList();
		for (Iterator it = elements.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (obj instanceof Checklist) {
				Checklist checklist = (Checklist) obj;
				result.put(checklist.getName(), checklist);
			}
		}

		return result;
	}

	/**
	 * Sets the GUID for a method element.
	 * 
	 * @param element
	 *            a method element
	 */
	public void setGUID(MethodElement element) {
		String guid = element.getGuid();
		if (guid == null || guid.length() == 0) {
			guid = EcoreUtil.generateUUID();
			boolean oldNotify = element.eDeliver();
			try {
				element.eSetDeliver(false);
				element.setGuid(guid);
			} finally {
				element.eSetDeliver(oldNotify);
			}
		}

		List children = element.eContents();
		if (children != null && children.size() > 0) {
			for (Iterator it = children.iterator(); it.hasNext();) {
				Object child = it.next();
				if (child instanceof MethodElement) {
					setGUID((MethodElement) child);
				}
			}
		}
	}

	/**
	 * Gets the debug string for a method element.
	 * 
	 * @param element
	 *            a method element
	 * @return a string containing the method element class name, name and GUID
	 */
	public static String getDebugString(MethodElement element) {
		if (element == null) {
			return "null"; //$NON-NLS-1$
		}
		return element.getClass().getName() + "[name: " + element.getName() //$NON-NLS-1$
				+ ", guid=" + element.getGuid() + "]"; //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * Gets the current method library persister.
	 * 
	 * @return the current method library persister
	 */
	public static ILibraryPersister getCurrentPersister() {
		try {
			ResourceSet resourceSet = LibraryService.getInstance()
					.getCurrentLibraryManager().getEditingDomain()
					.getResourceSet();
			if (resourceSet instanceof ILibraryResourceSet) {
				return ((ILibraryResourceSet) resourceSet).getPersister();
			}
		} catch (NullPointerException e) {
		}
		return null;
	}

	/**
	 * Gets the method library persister for a given resource.
	 * 
	 * @return a method library persister
	 */
	public static ILibraryPersister getPersisterFor(Resource resource) {
		ResourceSet resourceSet = resource.getResourceSet();
		if (resourceSet instanceof ILibraryResourceSet) {
			return ((ILibraryResourceSet) resourceSet).getPersister();
		}
		return null;
	}

	/**
	 * Creates a new method plug-in.
	 * 
	 * @param name
	 *            a name for the new plug-in
	 * @param briefDesc
	 *            a brief description for the new plug-in
	 * @param authors
	 *            the authors of the new plug-in
	 * @param referencedPlugins
	 *            the plug-ins referenced by the new plug-in
	 */
	public static MethodPlugin createMethodPlugin(String name,
			String briefDesc, String authors, Object[] referencedPlugins)
			throws Exception {
		if (name == null || name.length() == 0) {
			throw new IllegalArgumentException();
		}

		MethodPlugin plugin = UmaFactory.eINSTANCE.createMethodPlugin();

		plugin.setName(StrUtil.makeValidFileName(name));
		if (briefDesc != null) {
			plugin.setBriefDescription(briefDesc);
		}
		if (authors != null) {
			plugin.setAuthors(authors);
		}
		if (referencedPlugins != null) {
			for (int i = 0; i < referencedPlugins.length; i++) {
				MethodPlugin referencedPlugin = (MethodPlugin) referencedPlugins[i];
				if (plugin != null) {
					plugin.getBases().add(referencedPlugin);
				}
			}
		}

		ModelStorage.initialize(plugin);

		return plugin;
	}

}
