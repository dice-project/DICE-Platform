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
package org.eclipse.epf.library.edit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.epf.common.preferences.IPreferenceStoreWrapper;
import org.osgi.framework.Bundle;


/**
 * @author Phong Nguyen Le
 * @since 1.0
 */
public final class Providers {

	private static IConfiguratorFactory configuratorFactory;

	private static IConfigurationApplicator configurationApplicator;

	private static IPreferenceStoreWrapper preferenceStore;

	private static IPreferenceStoreWrapper authoringPluginPreferenceStore;

	private static Map commandTypeToListenersMap = new HashMap();

	private Providers() {
		super();
	}

	public static IConfiguratorFactory getConfiguratorFactory() {
		return configuratorFactory;
	}

	public static void setConfiguratorFactory(
			IConfiguratorFactory configuratorFactory) {
		Providers.configuratorFactory = configuratorFactory;
	}

	public static IConfigurationApplicator getConfigurationApplicator() {
		return configurationApplicator;
	}

	public static void setConfigurationApplicator(
			IConfigurationApplicator configurationApplicator) {
		Providers.configurationApplicator = configurationApplicator;
	}

	public static IPreferenceStoreWrapper getPreferenceStore() {
		return preferenceStore;
	}

	public static void setPreferenceStore(IPreferenceStoreWrapper preferenceStore) {
		Providers.preferenceStore = preferenceStore;
	}

	public static IPreferenceStoreWrapper getAuthoringPluginPreferenceStore() {
		return authoringPluginPreferenceStore;
	}

	public static void setAuthoringPluginPreferenceStore(IPreferenceStoreWrapper store) {
		authoringPluginPreferenceStore = store;
	}

	public static void registerCommandListener(ICommandListener listener) {
		List listeners = (List) commandTypeToListenersMap.get(listener
				.getCommandType());
		if (listeners == null) {
			listeners = new ArrayList();
			commandTypeToListenersMap.put(listener.getCommandType(), listeners);
		} else if (listeners.contains(listener)) {
			return;
		}
		listeners.add(listener);
	}

	public static void removeCommandListener(ICommandListener listener) {
		List listeners = (List) commandTypeToListenersMap.get(listener
				.getCommandType());
		if (listeners != null) {
			listeners.remove(listener);
			if (listeners.isEmpty()) {
				commandTypeToListenersMap.remove(listener.getCommandType());
			}
		}
	}

	public static List getCommandListeners(Class commandType) {
		return (List) commandTypeToListenersMap.get(commandType);
	}

	static {
		// register command listeners that have been contributed via extension point org.eclipse.epf.library.edit.commandListeners
		//
		try {
			String namespace = LibraryEditPlugin.getDefault().getId();
			String extensionPointName = "commandListeners"; //$NON-NLS-1$
			IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();
			IExtensionPoint extensionPoint = extensionRegistry.getExtensionPoint(namespace, extensionPointName);
			if (extensionPoint != null) {
				IExtension[] extensions = extensionPoint.getExtensions();
				for (int i = 0; i < extensions.length; i++) {
					IExtension extension = extensions[i];
					String pluginId = extension.getNamespaceIdentifier();
					Bundle bundle = Platform.getBundle(pluginId);
					IConfigurationElement[] configElements = extension
					.getConfigurationElements();
					for (int j = 0; j < configElements.length; j++) {
						IConfigurationElement configElement = configElements[j];
						try {
							String className = configElement.getAttribute("class"); //$NON-NLS-1$
							if(className != null) {
								Object ext = bundle.loadClass(className).newInstance();
								if(ext instanceof ICommandListener) {
									registerCommandListener((ICommandListener) ext);
								}
							}
						} catch (Exception e) {
							LibraryEditPlugin.INSTANCE.log(e);
						}
					}
				}
			}
		}
		catch(Exception e) {
			LibraryEditPlugin.getDefault().getLogger().logError(e);
		}

	}
	
}
