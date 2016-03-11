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
package org.eclipse.epf.library.edit.configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.epf.library.edit.ILibraryItemProvider;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.util.UmaUtil;

/**
 * The item provider adapter for the "Custom Categories" folder in the
 * Configuration view.
 * 
 * @author Phong Nguyen Le
 * @author Kelvin Low
 * @since 1.0
 */
public class CustomCategoriesItemProvider extends CategoriesItemProvider {

	public CustomCategoriesItemProvider(AdapterFactory adapterFactory,
			MethodConfiguration methodConfig, String name, Object image,
			String[] categoryPkgPath) {
		super(adapterFactory, methodConfig, name, image, categoryPkgPath);
	}

	public Collection getChildren(Object object) {
		if (children == null) {
			children = new ArrayList();
		} else {
			children.clear();
		}
		// MethodConfiguration methodConfig = (MethodConfiguration) target;
		for (Iterator iter = methodConfig.getMethodPluginSelection().iterator(); iter
				.hasNext();) {
			MethodPlugin plugin = (MethodPlugin) iter.next();
			ContentPackage pkg = UmaUtil.findContentPackage(plugin,
					categoryPkgPath);
			CustomCategory root = TngUtil.getRootCustomCategory(plugin);
			if (root != null && pkg != null
					&& methodConfig.getMethodPackageSelection().contains(pkg)) {
				for (Iterator iterator = root.getCategorizedElements()
						.iterator(); iterator.hasNext();) {
					Object element = iterator.next();
					if (filter != null ? filter.accept(element) : true) {
						children.add(element);
					}
				}
			}
		}

		// set parent
		//
		for (Iterator iter = children.iterator(); iter.hasNext();) {
			Object adapter = (TngAdapterFactory.INSTANCE)
					.getConfigurationView_ComposedAdapterFactory().adapt(
							iter.next(), ITreeItemContentProvider.class);
			if (adapter instanceof ILibraryItemProvider) {
				((ILibraryItemProvider) adapter).setParent(this);
			}
		}

		return children;
	}

}
