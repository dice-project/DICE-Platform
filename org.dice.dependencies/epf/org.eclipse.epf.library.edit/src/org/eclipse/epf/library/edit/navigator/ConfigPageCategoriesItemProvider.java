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
import java.util.Collections;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.epf.library.edit.LibraryEditPlugin;

/**
 * The item provider adapter for categories in configuration plugin/package selection page.
 * 
 * @author Shilpa Toraskar
 * @since 7.5
 */
public class ConfigPageCategoriesItemProvider extends	ItemProviderAdapter implements IItemLabelProvider {

	/**
	 * Creates a new instance.
	 */
	public ConfigPageCategoriesItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	protected void collectNewChildDescriptors(Collection newChildDescriptors,
			Object object) {

	}

	public Collection getChildrenFeatures(Object object) {
		return Collections.EMPTY_LIST;
	}

	public String getText(Object object) {
		return LibraryEditPlugin.INSTANCE.getString("_UI_Categories_group");
	}
	
	public Object getImage(Object object) {
		return LibraryEditPlugin.INSTANCE
				.getImage("full/obj16/StandardCategories"); //$NON-NLS-1$
	}

}
