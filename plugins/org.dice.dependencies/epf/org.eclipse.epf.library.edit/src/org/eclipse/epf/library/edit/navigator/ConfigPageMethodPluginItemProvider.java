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

import java.util.Collection;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.uma.MethodPlugin;

/**
 * The item provider adapter for a method plug-in in the configuration plugin/package selection page
 * view.
 * 
 * @author Shilpa Toraskar
 * @since 1.5
 */
public class ConfigPageMethodPluginItemProvider extends MethodPluginItemProvider {


	/**
	 * Creates a new instance.
	 */
	public ConfigPageMethodPluginItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}


	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.edit.navigator.MethodPluginItemProvider#getChildren(java.lang.Object)
	 */
	public Collection getChildren(Object object) {
		if (children == null) {
			children = super.getChildren(object);

			MethodPlugin plugin = (MethodPlugin) object;
			String name = LibraryEditPlugin.INSTANCE.getString("_UI_Categories_group");
			Object child = new ConfigPageCategoriesItemProvider(adapterFactory);
			children.add(child);
			groupItemProviderMap.put(name, child);
		}
		

		return children;
	}


}