//------------------------------------------------------------------------------
// Copyright (c) 2005, 2008 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.library.edit.navigator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.epf.library.edit.ILibraryItemProvider;
import org.eclipse.epf.library.edit.util.Comparators;
import org.eclipse.epf.library.edit.util.PluginUIPackagesMap;
import org.eclipse.epf.uma.MethodPlugin;

/**
 * @since 1.5
 */
public class PluginUIPackageBuilder {
	private AdapterFactory adapterFactory;
	private Map<String, PluginUIPackagesItemProvider> pluginPackagesItemProvidersMap = new HashMap<String, PluginUIPackagesItemProvider>();

	public PluginUIPackageBuilder(AdapterFactory adapterFactory) {
		this.adapterFactory = adapterFactory;
	}
	
	public PluginUIPackagesItemProvider getPluginUIPackagesItemProvider(String pluginPkgName) {
		return pluginPackagesItemProvidersMap.get(pluginPkgName);
	}
	
	public Collection<Object> getChildren(Collection<MethodPlugin> plugins) {
		ArrayList<Object> children = new ArrayList<Object>();
		addChildren(children, plugins, null);
		Collections.sort(children, Comparators.PLUGINPACKAGE_COMPARATOR);
		return children;
	}
	
	private void retainPluginPackagesItemProviders(Collection<String> names) {
		for (Iterator<String> iter = pluginPackagesItemProvidersMap.keySet().iterator();iter.hasNext();) {
			String keyName = iter.next();
			if (!names.contains(keyName)) {
				PluginUIPackagesItemProvider provider = pluginPackagesItemProvidersMap.get(keyName);
				if (provider != null) {
					provider.dispose();
				}
				iter.remove();
			}
		}
	}

	protected void addChildren(Collection<Object> children, Collection<MethodPlugin> plugins, Object parent) {
		PluginUIPackagesMap map = new PluginUIPackagesMap();
		for (Iterator<MethodPlugin> iter = plugins.iterator();iter.hasNext();) {
			MethodPlugin plugin = iter.next();
			String pluginName = plugin.getName();
			int dotIdx = pluginName.indexOf(PluginUIPackagesItemProvider.PLUGIN_PACKAGE_SEPARATOR);
			if (dotIdx != -1) {
				map.add(pluginName.substring(0, dotIdx), plugin);
			} else {
				children.add(plugin);
				Object adapter = adapterFactory.adapt(plugin,
						ITreeItemContentProvider.class);
				if (adapter instanceof ILibraryItemProvider) {
					((ILibraryItemProvider) adapter).setParent(parent);
				}
			}
		}
		Collection<String> existingNames = new HashSet<String>();
		for (Iterator<Map.Entry<String, Set<MethodPlugin>>> iter = map.entrySet().iterator();iter.hasNext();) {
			Map.Entry<String, Set<MethodPlugin>> entry = iter.next();
			String name = entry.getKey();
			Set<MethodPlugin> plugSet = entry.getValue();
			PluginUIPackagesItemProvider provider = pluginPackagesItemProvidersMap.get(name);
			if (provider == null) {
				provider = new PluginUIPackagesItemProvider(adapterFactory, name, plugSet);
				pluginPackagesItemProvidersMap.put(name, provider);
			} else {
				provider.setPlugins(plugSet);
			}
			existingNames.add(name);
			provider.setParent(parent);
			children.add(provider);
		}
		retainPluginPackagesItemProviders(existingNames);
	}

	public void dispose() {
		if (pluginPackagesItemProvidersMap != null) {
			pluginPackagesItemProvidersMap.clear();
			pluginPackagesItemProvidersMap = null;
		}
	}
}
