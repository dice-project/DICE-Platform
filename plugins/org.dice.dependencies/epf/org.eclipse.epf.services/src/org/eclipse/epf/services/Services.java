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
package org.eclipse.epf.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.CommonPlugin;
import org.osgi.framework.Bundle;

/**
 * Helper class to access the extensions contributed to extenstion point
 * "org.eclipse.epf.uma.services".
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public final class Services {
	private static class PersisterDescriptor {
		String pluginId;
		String type;
		String className;
		boolean isDefault;
		int version;
		ILibraryPersister persister;
	}
	
	/**
	 * Persistence type for XMI-file-based library
	 */
	public static final String XMI_PERSISTENCE_TYPE = "xmi"; //$NON-NLS-1$
		
	private static final String namespace = Activator.PLUGIN_ID; 
	private static final String extensionPointName = "serviceProviders"; //$NON-NLS-1$

	private static IAccessController accessController;
	private static IFileManager fileManager;
	private static List<PersisterDescriptor> persisterDescriptors = new ArrayList<PersisterDescriptor>();	
	private static Map<String, PersisterDescriptor> typeToLibraryPersisterDescriptorMap;

	private static String defaultPersistenceType;

	private static Object getService(String serviceName) {
		IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();
		IExtensionPoint extensionPoint = extensionRegistry.getExtensionPoint(namespace, extensionPointName);
		if (extensionPoint != null) {
			IExtension[] extensions = extensionPoint.getExtensions();
			Bundle bundle = null;
			String className = null;
			int version = -1;
			for (int i = 0; i < extensions.length; i++) {
				IExtension extension = extensions[i];
				String pluginId = extension.getNamespaceIdentifier();
				IConfigurationElement[] configElements = extension
						.getConfigurationElements();
				for (int j = 0; j < configElements.length; j++) {
					IConfigurationElement configElement = configElements[j];
					if(serviceName.equals(configElement.getName())) {
						int otherVersion = parseVersion(configElement.getAttribute("version")); //$NON-NLS-1$
						if(otherVersion > version) {
							className = configElement.getAttribute("class"); //$NON-NLS-1$
							bundle = Platform.getBundle(pluginId);
							version = otherVersion;
						}
					}
				}
			}
			if(className != null) {
				try {
					return bundle.loadClass(className).newInstance();
				} catch (Exception e) {
					CommonPlugin.INSTANCE.log(e);
				}
			}
		}
		return null;
	}
	
	public static int parseVersion(String str) {
		try {
			return Integer.parseInt(str);
		}
		catch(NumberFormatException e) {
			return 0;
		}		
	}
	
	private static Map<String, PersisterDescriptor> getTypeToLibraryPersisterDescriptorMap() {
		Map<String, PersisterDescriptor> typeToPersisterDescriptorMap = new HashMap<String, PersisterDescriptor>();
		String serviceName = "libraryPersister"; //$NON-NLS-1$
		IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();
		IExtensionPoint extensionPoint = extensionRegistry.getExtensionPoint(namespace, extensionPointName);
		if (extensionPoint != null) {
			IExtension[] extensions = extensionPoint.getExtensions();
			for (int i = 0; i < extensions.length; i++) {
				IExtension extension = extensions[i];
				String pluginId = extension.getNamespaceIdentifier();
				IConfigurationElement[] configElements = extension
						.getConfigurationElements();
				for (int j = 0; j < configElements.length; j++) {
					IConfigurationElement configElement = configElements[j];
					if(serviceName.equals(configElement.getName())) {
						try {
							PersisterDescriptor descriptor = new PersisterDescriptor();
							descriptor.pluginId = pluginId;
							descriptor.type = configElement.getAttribute("type"); //$NON-NLS-1$							
							descriptor.className = configElement.getAttribute("class"); //$NON-NLS-1$
							descriptor.isDefault = Boolean.parseBoolean(configElement.getAttribute("default")); //$NON-NLS-1$							
							descriptor.version = parseVersion(configElement.getAttribute("version")); //$NON-NLS-1$							
							persisterDescriptors.add(descriptor);
							if(descriptor.type != null) {
								PersisterDescriptor desc = typeToPersisterDescriptorMap.get(descriptor.type);
								if(desc != null) {
									if(desc.version < descriptor.version) {
										typeToPersisterDescriptorMap.put(descriptor.type, descriptor);
										if(descriptor.isDefault) {
											defaultPersistenceType = descriptor.type;
										}
										else if(desc.isDefault) {
											defaultPersistenceType = null;
										}
									}
								}
								else {
									typeToPersisterDescriptorMap.put(descriptor.type, descriptor);
									if(descriptor.isDefault) {
										defaultPersistenceType = descriptor.type;
									}
								}
							}
						} catch (Exception e) {
							CommonPlugin.INSTANCE.log(e);
						}
					}
				}
			}
		}
		return typeToPersisterDescriptorMap;
	}
	
	public static final IAccessController getAccessController() {
		if(accessController == null) {
			accessController = (IAccessController) getService("accessController"); //$NON-NLS-1$
		}
		return accessController;
	}
	
	/**
	 * Gets the library persister of the given type
	 * 
	 * @param type
	 * @return
	 */
	public static final ILibraryPersister getLibraryPersister(String type) {
		if(typeToLibraryPersisterDescriptorMap == null) {
			typeToLibraryPersisterDescriptorMap = getTypeToLibraryPersisterDescriptorMap();
		}
		PersisterDescriptor descriptor = typeToLibraryPersisterDescriptorMap.get(type);
		if(descriptor != null) {
			if(descriptor.persister == null) {
				Bundle bundle = Platform.getBundle(descriptor.pluginId);
				try {
					descriptor.persister = (ILibraryPersister) bundle.loadClass(descriptor.className).newInstance();
				} catch (Exception e) {
					CommonPlugin.INSTANCE.log(e);
				}
			}
			return descriptor.persister;
		}
		return null;
	}
	
	public static final ILibraryPersister getDefaultLibraryPersister() {
		return getLibraryPersister(getDefaultLibraryPersistenceType());
	}
	
	public static final String getDefaultLibraryPersistenceType() {
		if(defaultPersistenceType == null) {
			getTypeToLibraryPersisterDescriptorMap();
			if(defaultPersistenceType == null && getLibraryPersister(XMI_PERSISTENCE_TYPE) != null) {
				// no persister is set as default, choose XMI as default persistence type
				//
				defaultPersistenceType = XMI_PERSISTENCE_TYPE;
			}
		}
		return defaultPersistenceType;
	}

	public static final IFileManager getFileManager() {
		if(fileManager == null) {
			fileManager = (IFileManager) getService("fileManager"); //$NON-NLS-1$
		}
		return fileManager;
	}
}
