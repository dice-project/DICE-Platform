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
import org.eclipse.epf.library.edit.IConfigurable;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.TransientGroupItemProvider;
import org.eclipse.epf.uma.UmaPackage;

/**
 * The item provider adapter for the content element folders in the method
 * element selection dialogs.
 * 
 * @author Shashidhar Kannoori
 * @since 1.0
 */
public class ContentElementsItemProvider extends TransientGroupItemProvider
		implements IConfigurable {

	IFilter filter;

	/**
	 * Creates a new instance.
	 * 
	 * @param adapterFactory
	 * @param parent
	 */
	public ContentElementsItemProvider(AdapterFactory adapterFactory,
			Notifier parent) {
		super(adapterFactory, parent);
	}

	/**
	 * Creates a new instance.
	 * 
	 * @param adapterFactory
	 * @param parent
	 * @param name
	 */
	public ContentElementsItemProvider(AdapterFactory adapterFactory,
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
			childrenFeatures.add(UmaPackage.eINSTANCE
					.getMethodPackage_ChildPackages());
			childrenFeatures.add(UmaPackage.eINSTANCE
					.getContentPackage_ContentElements());
		}
		return childrenFeatures;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getChildren(java.lang.Object)
	 */
	public Collection getChildren(Object object) {
		Collection childrenCol = super.getChildren(object);
		for (Iterator iter1 = childrenCol.iterator(); iter1.hasNext();) {
			Object child = iter1.next();
			if (child instanceof IConfigurable)
				((IConfigurable) child).setFilter(filter);
			if (!filter.accept(child))
				iter1.remove();
		}
		return childrenCol;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.library.edit.IConfigurable#setFilter(com.ibm.library.edit.IFilter)
	 */
	public void setFilter(IFilter filter) {
		this.filter = filter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.library.edit.TransientGroupItemProvider#acceptAsChild(java.lang.Object)
	 */
	protected boolean acceptAsChild(Object obj) {
		if (!super.acceptAsChild(obj))
			return false;
		return true;
	}

}
