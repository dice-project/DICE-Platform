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
import org.eclipse.epf.library.edit.FeatureValueWrapperItemProvider;
import org.eclipse.epf.library.edit.IConfigurable;
import org.eclipse.epf.library.edit.IFilter;

/**
 * The item provider adapter for a custom category in the method element
 * selection dialogs.
 * 
 * @author Phong Nguyen Le
 * @author Shashidhar Kannoori
 * @since 1.0
 */
public class CustomCategoryItemProvider extends
		org.eclipse.epf.library.edit.category.CustomCategoryItemProvider implements
		IConfigurable {

	IFilter filter;

	/**
	 * Creates a new instance.
	 */
	public CustomCategoryItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getChildren(java.lang.Object)
	 */
	public Collection getChildren(Object object) {
		// Set the childrenStoreMap to null, to get the updated customcategories
		childrenStoreMap = null;
		Collection children = super.getChildren(object);
		if (this.filter != null) {
			for (Iterator iter = children.iterator(); iter.hasNext();) {
				FeatureValueWrapperItemProvider obj = (FeatureValueWrapperItemProvider) iter
						.next();
				if (!this.filter.accept(obj.getValue())) {
					iter.remove();
				}

			}
		}
		return children;
	}

}
