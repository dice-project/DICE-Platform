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

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.epf.library.edit.IConfigurable;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.uma.MethodConfiguration;

/**
 * The item provider adapter for the guidance folders in the method element
 * selection dialogs.
 * 
 * @author Shashidhar Kannoori
 * @since 1.0
 */
public class GuidanceGroupingItemProvider extends
		org.eclipse.epf.library.edit.configuration.GuidanceGroupingItemProvider
		implements IConfigurable {

	private IFilter filter;

	/**
	 * Creates a new instance.
	 * 
	 * @param adapterFactory
	 * @param methodConfig
	 */
	public GuidanceGroupingItemProvider(AdapterFactory adapterFactory,
			MethodConfiguration methodConfig) {
		super(adapterFactory, methodConfig);
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
	 * @see org.eclipse.epf.library.edit.IConfigurable#setLabel(java.lang.String)
	 */
	public void setLabel(String label) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.library.edit.IConfigurable#setParent(java.lang.Object)
	 */
	public void setParent(Object parent) {
	}

	public Collection getChildren(Object object) {
		Collection col = super.getChildren(object);
		for (Iterator itor = col.iterator(); itor.hasNext();) {
			Object obj = itor.next();
			if (!filter.accept(obj))
				itor.remove();
		}
		return col;
	}

}
