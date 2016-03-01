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

/**
 * A generic filter for filtering out method elements in the method element
 * selection dialogs.
 * 
 * @author Shashidhar Kannoori
 * @author Jinhua Xi
 * @since 1.0
 */
public interface IFilter extends org.eclipse.epf.library.edit.IFilter {

	/*
	 * getObject()
	 */
	public Object getObject();

	/*
	 * setHelper()
	 */
	public void setHelper(FilterHelper helper);

	/**
	 * Added for futher filtering. For example, we may set a filer to prevent
	 * generalizers as base element to avoid circular dependency
	 * 
	 * @param additionalFilters
	 */
	public void setAdditionalFilters(
			org.eclipse.epf.library.edit.IFilter[] additionalFilters);

}
