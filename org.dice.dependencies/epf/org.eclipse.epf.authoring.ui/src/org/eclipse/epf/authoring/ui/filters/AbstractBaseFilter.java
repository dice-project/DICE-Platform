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

package org.eclipse.epf.authoring.ui.filters;

import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.itemsfilter.FilterHelper;

/**
 * The base class for UI filters. Supports multiple filters.
 * Support filtering the element based on the pattern and type. 
 * 
 * @author Jinhua Xi
 * @since 1.0
 * 
 */
public class AbstractBaseFilter {

	protected FilterHelper helper;

	protected IFilter[] additionalFilters = null;

	/**
	 * added for futher filtering. For example, we may set a filer to prevent
	 * generalizers as base element to avoid circular dependency
	 * 
	 * @param additionalFilters
	 */
	public void setAdditionalFilters(IFilter[] additionalFilters) {
		this.additionalFilters = additionalFilters;
	}

	public void addAdditionalFilter(IFilter filter) {
		if (additionalFilters == null) {
			additionalFilters = new IFilter[]{filter};
			
		} else {
			int sz = additionalFilters.length + 1;
			IFilter[] newFilters = new IFilter[sz];
			for (int i = 0; i < sz - 1; i++) {
				newFilters[i] = additionalFilters[i];
			}
			newFilters[sz - 1] = filter;
			additionalFilters = newFilters;
		}
	}
	
	public FilterHelper getHelper() {
		return helper;
	}

	public void setHelper(FilterHelper helper) {
		this.helper = helper;
	}

	/*
	 * subclass method should call this before returning true.
	 */
	public boolean accept(Object obj) {

		// Jinhua Xi, added for futher filtering.
		// For example, we may set a filer to prevent generalizers as base
		// element to avoid circular dependency
		if (additionalFilters != null && additionalFilters.length > 0) {
			for (int i = 0; i < additionalFilters.length; i++) {
				if (additionalFilters[i].accept(obj) == false) {
					return false;
				}
			}
		}

		return true;
	}

}
