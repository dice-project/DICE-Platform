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
import java.util.Collections;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.epf.library.edit.IConfigurable;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.ILibraryItemProvider;
import org.eclipse.epf.library.edit.configuration.CategoriesItemProvider;

/**
 * The item provider adapter for a role set in the method element
 * selection dialogs.
 * 
 * @author Shashidhar Kannoori
 * @since 1.0
 */
public class RoleSetItemProvider extends
		org.eclipse.epf.library.edit.configuration.RoleSetItemProvider implements
		IConfigurable, ILibraryItemProvider {

	private IFilter filter;

	private Object parent;

	/**
	 * Creates a new instance.
	 * 
	 * @param adapterFactory
	 */
	public RoleSetItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	public Collection getChildren(Object object) {
		Collection col = Collections.EMPTY_LIST;
		Object parent = getParent(getParent(object));
		if (parent instanceof CategoriesItemProvider) {
			col = super.getChildren(object);
			if (filter != null) {
				FilterUtil.iterateCollection(col, filter);
			}
		} else {
			// col = super.getChildren(object);
			if (filter instanceof IAllFilter
					|| filter instanceof ICategoryFilter) {
				return col;
			}
			// ItemProviderAdapter adapter =
			// (ItemProviderAdapter)(TngAdapterFactory.INSTANCE).getNavigatorView_ComposedAdapterFactory()
			// .adapt(object, ITreeItemContentProvider.class);
			// col = adapter.getChildren(object);
		}
		return col;
	}

	public void setFilter(IFilter filter) {
		this.filter = filter;
	}

	public void setLabel(String label) {
	}

	public void setParent(Object parent) {
		this.parent = parent;
		super.setParent(parent);
	}

	public Object getParent(Object object) {
		return parent;
		// return super.getParent(object);
	}
	
}
