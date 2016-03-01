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

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.epf.library.edit.IFilter;

/**
 * The item provider adapter for processes in the Team Allocation page
 * of a Process editor.
 * 
 * @author Shashidhar Kannoori
 * @since 1.0
 */
public class OBSProcessItemProvider extends OBSActivityItemProvider {

	private ItemProviderAdapter delegateItemProvider;

	private IFilter filter;

	public OBSProcessItemProvider(AdapterFactory adapterFactory,
			ItemProviderAdapter delegateItemProvider) {
		super(adapterFactory);
		this.delegateItemProvider = delegateItemProvider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.ActivityItemProvider#getPropertyDescriptors(java.lang.Object)
	 */
	public List getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			itemPropertyDescriptors = delegateItemProvider
					.getPropertyDescriptors(object);
		}
		return itemPropertyDescriptors;
	}

	public Collection getChildren(Object object) {
		Collection col = super.getChildren(object);
		for (Iterator iter = col.iterator(); iter.hasNext();) {
			Object child = iter.next();
			if (filter != null) {
				if (!filter.accept(child))
					iter.remove();
			}
		}
		return col;
	}

	public void setFilter(IFilter filter) {
		super.setFilter(filter);
		this.filter = filter;
	}

}
