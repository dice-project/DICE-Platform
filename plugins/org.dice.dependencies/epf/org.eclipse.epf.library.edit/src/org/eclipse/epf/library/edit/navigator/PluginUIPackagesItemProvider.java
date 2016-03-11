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
package org.eclipse.epf.library.edit.navigator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.epf.library.edit.ILibraryItemProvider;
import org.eclipse.epf.library.edit.IStatefulItemProvider;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.util.Comparators;
import org.eclipse.epf.library.edit.util.PluginUIPackagesMap;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.provider.MethodPluginItemProvider;

/**
 * Item Provider Adapter that represents a plugin-package.  This is a UI folder only
 * @author Jeff Hardy
 *
 */
public class PluginUIPackagesItemProvider extends ItemProviderAdapter implements IStructuredItemContentProvider,
	ITreeItemContentProvider, IItemLabelProvider, ILibraryItemProvider, IStatefulItemProvider {

	public static final String PLUGIN_PACKAGE_SEPARATOR = "."; //$NON-NLS-1$

	private Object parent;
	
	private String name;
	
	private Collection<MethodPlugin> plugins;

	private Map<String, PluginUIPackagesItemProvider> pluginPackagesItemProvidersMap = new HashMap<String, PluginUIPackagesItemProvider>();


	/**
	 * Creates a new instance.
	 */
	public PluginUIPackagesItemProvider(AdapterFactory adapterFactory, String packageName, Collection<MethodPlugin> plugins) {
		super(adapterFactory);
		this.name = packageName;
		this.plugins = plugins;
	}

	public void setParent(Object parent) {
		this.parent = parent;
	}
	
	/**
	 * 
	 * @return the parent of this ItemProvider
	 */
	public Object getParent() {
		return parent;
	}
	
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		// TODO Auto-generated method stub
		return super.getChildrenFeatures(object);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getText(java.lang.Object)
	 */
	public String getText(Object object) {
		return name; 
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getImage(java.lang.Object)
	 */
	public Object getImage(Object object) {
		return LibraryEditPlugin.INSTANCE.getImage("full/obj16/package_obj"); //$NON-NLS-1$
	}

	public static String getNameDelta(PluginUIPackagesItemProvider topObject, Object bottomObject) {
		
		String topName= topObject.getFullName();
		String bottomName = null;
		if (bottomObject instanceof PluginUIPackagesItemProvider) {
			bottomName = ((PluginUIPackagesItemProvider)bottomObject).getFullName();
			
		} else if (bottomObject instanceof MethodPlugin) {
			bottomName = ((MethodPlugin)bottomObject).getName();
		}
		
		if(bottomName == null || topName.equals(bottomName))
			return topName;
		try {
			String deltaname= bottomName.substring(topName.length()+1);
			return deltaname;
		} catch (StringIndexOutOfBoundsException e) {
			return bottomName;
		}
	}

	
	public Collection<Object> getChildren(Object parentElement) {
		return addChildren(plugins);
	}
	
	private Collection<Object> addChildren(Collection<MethodPlugin> plugins) {
		List children = new ArrayList();
		PluginUIPackagesMap map = new PluginUIPackagesMap();
		for (Iterator<MethodPlugin> iter = plugins.iterator();iter.hasNext();) {
			MethodPlugin plugin = iter.next();
			String deltaName = getNameDelta(this, plugin);
			int dotIdx = deltaName.indexOf(PLUGIN_PACKAGE_SEPARATOR);
			if (dotIdx != -1) {
				map.add(deltaName.substring(0, dotIdx), plugin);
			} else {
				children.add(plugin);
				Object adapter = adapterFactory.adapt(plugin,
						ITreeItemContentProvider.class);
				if (adapter instanceof ILibraryItemProvider) {
					((ILibraryItemProvider) adapter).setParent(this);
				}
			}
		}
		Collection<String> existingNames = new HashSet<String>();
		for (Iterator<Map.Entry<String, Set<MethodPlugin>>> iter = map.entrySet().iterator();iter.hasNext();) {
			Map.Entry<String, Set<MethodPlugin>> entry = iter.next();
			String name = entry.getKey();
			Set<MethodPlugin> plugSet = entry.getValue();
			PluginUIPackagesItemProvider provider = pluginPackagesItemProvidersMap.get(getFullName() + PLUGIN_PACKAGE_SEPARATOR + name);
			if (provider == null) {
				provider = new PluginUIPackagesItemProvider(adapterFactory, name, plugSet);
				pluginPackagesItemProvidersMap.put(getFullName() + PLUGIN_PACKAGE_SEPARATOR + name, provider);
			} else {
				provider.setPlugins(plugSet);
			}
			existingNames.add(getFullName() + PLUGIN_PACKAGE_SEPARATOR + name);
			provider.setParent(this);
			children.add(provider);
		}
		retainPluginPackagesItemProviders(existingNames);
		Collections.sort(children, Comparators.PLUGINPACKAGE_COMPARATOR);
		return children;
	}
	
	/**
	 * 
	 * @return full name of this package
	 */
	public String getFullName() {
		String fullName = getName();
		Object parent = getParent();
		while (parent instanceof PluginUIPackagesItemProvider) {
			fullName = ((PluginUIPackagesItemProvider)parent).getName() + PLUGIN_PACKAGE_SEPARATOR + fullName;
			parent = ((PluginUIPackagesItemProvider)parent).getParent();
		}
		return fullName;
		
	}

	public String getName() {
		return name;
	}
	
	@Override
	public Object getParent(Object object) {
		if (parent != null)
			return parent;
		return target;
	}
	
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(MethodPlugin.class)) {
		case UmaPackage.METHOD_PLUGIN__NAME:
			fireNotifyChanged(new ViewerNotification(notification, notification
					.getNotifier(), true, true));
			break;
		}

		super.notifyChanged(notification);
	}

	@Override
	public void dispose() {
		// remove this from the plugins' adapter list
		if (pluginPackagesItemProvidersMap != null) {
			pluginPackagesItemProvidersMap.clear();
			pluginPackagesItemProvidersMap = null;
		}
		super.dispose();
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
	
	public Collection<MethodPlugin> getPlugins() {
		return plugins;
	}

	public void setPlugins(Collection<MethodPlugin> plugins) {
		this.plugins = plugins;
	}
	
	/**
	 * 
	 * @param plugin
	 * @return the ItemProviderAdapter for the plugin - either a MethodPluginItemProvider or a PluginUIPackagesItemProvider. null if none found
	 */
	public ItemProviderAdapter getPluginItemProvider(MethodPlugin plugin) {
		String deltaName = getNameDelta(this, plugin);
		int dotIdx = deltaName.indexOf(PluginUIPackagesItemProvider.PLUGIN_PACKAGE_SEPARATOR);
		if (dotIdx != -1) {
			String packageProviderName = deltaName.substring(0, dotIdx);
			if (pluginPackagesItemProvidersMap != null) {
				PluginUIPackagesItemProvider provider = pluginPackagesItemProvidersMap.get(getFullName() + PLUGIN_PACKAGE_SEPARATOR + packageProviderName);
				if (provider != null) {
					return provider;
				}
			}			
		} else {
			return (MethodPluginItemProvider) TngUtil
				.getAdapter(plugin, MethodPluginItemProvider.class);
		}
		return null;
	}
	
	/**
	 * 
	 * @param itemName name of item to find
	 * @param lib MethodLibrary
	 * @return the MethodPlugin or PluginUIPackagesItemProvider with the specified name, or null if not found
	 */
	public static Object findPluginItemProvider(String itemName, MethodLibrary lib) {
		ITreeItemContentProvider adapter = (ITreeItemContentProvider)TngAdapterFactory.INSTANCE
				.getNavigatorView_ComposedAdapterFactory().adapt(lib, ITreeItemContentProvider.class);
		if (adapter instanceof MethodLibraryItemProvider) {
			MethodLibraryItemProvider libraryAdapter = (MethodLibraryItemProvider)adapter;
			List<MethodPlugin> pluginList = lib.getMethodPlugins();
			for (MethodPlugin plugin : pluginList) {
				if (itemName.equals(plugin.getName())) {
					return plugin;
				}
				Collection<?> children = libraryAdapter.getChildren(lib);
				ItemProviderAdapter pluginTreeAdapter = libraryAdapter.getPluginItemProvider(plugin);
				while (pluginTreeAdapter instanceof PluginUIPackagesItemProvider) {
					if (itemName.equals(((PluginUIPackagesItemProvider)pluginTreeAdapter).getFullName())) {
						return pluginTreeAdapter;
					}
					children = pluginTreeAdapter.getChildren(pluginTreeAdapter);
					pluginTreeAdapter = ((PluginUIPackagesItemProvider)pluginTreeAdapter).getPluginItemProvider(plugin);
				}
			}
		}
		return null;
	}
}
