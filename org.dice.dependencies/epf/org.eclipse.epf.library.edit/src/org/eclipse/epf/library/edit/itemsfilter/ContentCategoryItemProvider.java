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
package org.eclipse.epf.library.edit.itemsfilter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.epf.library.edit.IConfigurable;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.ILibraryItemProvider;
import org.eclipse.epf.library.edit.category.TransientCategoryPackageItemProvider;
import org.eclipse.epf.uma.UmaPackage;

/**
 * The item provider adapter for a content category in the method element
 * selection dialogs.
 * 
 * @author Phong Nguyen Le
 * @author Shashidhar Kannoori
 * @since 1.0
 */
public class ContentCategoryItemProvider extends
		TransientCategoryPackageItemProvider implements IConfigurable {

	IFilter filter;

	/**
	 * Creates a new instance.
	 * 
	 * @param adapterFactory
	 * @param parent
	 * @param name
	 */
	public ContentCategoryItemProvider(AdapterFactory adapterFactory,
			Notifier parent, String name) {
		super(adapterFactory, parent, name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getChildrenFeatures(java.lang.Object)
	 */
	public Collection getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			childrenFeatures = new ArrayList();
			// childrenFeatures.add(UmaPackage.eINSTANCE.getDisciplineGrouping_Disciplines());
			childrenFeatures.add(UmaPackage.eINSTANCE
					.getContentPackage_ContentElements());
		}
		return childrenFeatures;
	}

	protected boolean acceptAsChild(Object obj) {
		return filter.accept(obj);
	}

	public void setFilter(IFilter filter) {
		this.filter = filter;
	}

	public Collection getChildren(Object object) {
		Collection children = super.getChildren(object);
		for (Iterator itor = children.iterator(); itor.hasNext();) {
			Object obj = itor.next();
			ItemProviderAdapter contentAdapter = (ItemProviderAdapter) adapterFactory
					.adapt(obj, ITreeItemContentProvider.class);
			if (contentAdapter instanceof ILibraryItemProvider) {
				((ILibraryItemProvider) contentAdapter).setParent(object);
			}
		}
		return children;
	}
}
