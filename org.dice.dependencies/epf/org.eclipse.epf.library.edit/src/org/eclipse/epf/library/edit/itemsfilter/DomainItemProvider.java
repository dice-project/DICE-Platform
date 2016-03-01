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

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.epf.library.edit.IConfigurable;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.configuration.CategoriesItemProvider;
import org.eclipse.epf.uma.Domain;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.util.UmaUtil;

/**
 * The item provider adapter for a domain in the method element
 * selection dialogs.
 * 
 * @author Shashidhar Kannoori
 * @since 1.0
 */
public class DomainItemProvider extends
		org.eclipse.epf.library.edit.configuration.DomainItemProvider implements
		IConfigurable {
	Object parent;
	private IFilter filter;

	/**
	 * @param adapterFactory
	 */
	public DomainItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	public Object getParent(Object object) {
		Domain domain = (Domain) object;
		if (domain.eContainer() instanceof Domain) {
			return super.getParent(object);
		}
		MethodPlugin model = UmaUtil.getMethodPlugin(domain);
		if (model == null)
			return null;
		return parent;
	}

	public void setParent(Object parent) {
		super.setParent(parent);
		this.parent = parent;
	}

	public void setFilter(IFilter filter) {
		// TODO Auto-generated method stub
		this.filter = filter;
	}

	public void setLabel(String label) {
		// TODO Auto-generated method stub
		
	}

	public Collection getChildren(Object object) {
		Collection col;
		Object parent = getParent(object);
		
		if (parent instanceof CategoriesItemProvider) {
			col = super.getChildren(object);
			
		} else {
			ItemProviderAdapter adapter = (ItemProviderAdapter) (TngAdapterFactory.INSTANCE)
					.getNavigatorView_ComposedAdapterFactory().adapt(object,
							ITreeItemContentProvider.class);
			col = adapter.getChildren(object);
		}
		// If filter not null iterate collection.
		if (filter != null) {
			FilterUtil.iterateCollection(col, filter);
		}

		return col;
	}
}
